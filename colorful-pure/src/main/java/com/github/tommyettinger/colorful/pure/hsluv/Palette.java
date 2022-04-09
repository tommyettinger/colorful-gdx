package com.github.tommyettinger.colorful.pure.hsluv;

import com.github.tommyettinger.ds.FloatList;
import com.github.tommyettinger.ds.ObjectFloatOrderedMap;
import com.github.tommyettinger.ds.ObjectList;

import java.util.Comparator;

import static com.github.tommyettinger.colorful.pure.hsluv.ColorTools.*;

/**
 * A palette of predefined colors as packed HSLuv floats, the kind {@link ColorTools} works with.
 * You can access colors by their constant name, such as {@code OCEAN_BLUE}, by the {@link #NAMED} map using
 * {@code NAMED.get("Ocean Blue", 0f)}, or by index in the FloatArray called {@link #LIST}. Note that to access a float
 * color from NAMED, you need to give a default value if the name is not found; {@code 256f} is a good default because
 * it will not occur in a valid HSLuv color. You can access the names in a specific order with {@link #NAMES} (which is
 * alphabetical), {@link #NAMES_BY_HUE} (which is sorted by the hue of the matching color, from red to yellow to blue
 * (with gray around here) to purple to red again), or {@link #NAMES_BY_LIGHTNESS} (which is sorted by the intensity of
 * the matching color, from darkest to lightest). Having a name lets you look up the matching color in {@link #NAMED}.
 * <br>
 * Created by Tommy Ettinger on 10/13/2020.
 */
public class Palette {
    public static final ObjectFloatOrderedMap<String> NAMED = new ObjectFloatOrderedMap<>(256);
    public static final FloatList LIST = new FloatList(256);

    /**
     * This color constant "Transparent" has RGBA8888 code {@code 00000000}, H 0.0, S 0.0, L 0.0, alpha 0.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code 0x0.0p0F}.
     * <pre>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #000000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRANSPARENT = 0x0.0p0F;
    static { NAMED.put("Transparent", 0x0.0p0F); LIST.add(0x0.0p0F); }

    /**
     * This color constant "Black" has RGBA8888 code {@code 000000FF}, H 0.0, S 0.0, L 0.0, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.0p125F}.
     * <pre>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #000000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK = -0x1.0p125F;
    static { NAMED.put("Black", -0x1.0p125F); LIST.add(-0x1.0p125F); }

    /**
     * This color constant "Coal Black" has RGBA8888 code {@code 131313FF}, H 0.16078432, S 0.0, L 0.05490196, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.1c0052p125F}.
     * <pre>
     * <font style='background-color: #131313;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #131313; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #131313;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #131313'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #131313'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #131313'>&nbsp;@&nbsp;</font><font style='background-color: #131313; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #131313;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #131313; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float COAL_BLACK = -0x1.1c0052p125F;
    static { NAMED.put("Coal Black", -0x1.1c0052p125F); LIST.add(-0x1.1c0052p125F); }

    /**
     * This color constant "Shadow" has RGBA8888 code {@code 252525FF}, H 0.16078432, S 0.0, L 0.14509805, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.4a0052p125F}.
     * <pre>
     * <font style='background-color: #252525;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #252525; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #252525;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #252525'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #252525'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #252525'>&nbsp;@&nbsp;</font><font style='background-color: #252525; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #252525;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #252525; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SHADOW = -0x1.4a0052p125F;
    static { NAMED.put("Shadow", -0x1.4a0052p125F); LIST.add(-0x1.4a0052p125F); }

    /**
     * This color constant "Graphite" has RGBA8888 code {@code 373737FF}, H 0.16078432, S 0.0, L 0.23137255, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.760052p125F}.
     * <pre>
     * <font style='background-color: #373737;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #373737; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #373737;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #373737'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #373737'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #373737'>&nbsp;@&nbsp;</font><font style='background-color: #373737; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #373737;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #373737; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAPHITE = -0x1.760052p125F;
    static { NAMED.put("Graphite", -0x1.760052p125F); LIST.add(-0x1.760052p125F); }

    /**
     * This color constant "Dark Gray" has RGBA8888 code {@code 494949FF}, H 0.16078432, S 0.0, L 0.30980393, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.9e0052p125F}.
     * <pre>
     * <font style='background-color: #494949;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #494949; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #494949;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #494949'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #494949'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #494949'>&nbsp;@&nbsp;</font><font style='background-color: #494949; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #494949;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #494949; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY = -0x1.9e0052p125F;
    static { NAMED.put("Dark Gray", -0x1.9e0052p125F); LIST.add(-0x1.9e0052p125F); }

    /**
     * This color constant "Lead" has RGBA8888 code {@code 5B5B5BFF}, H 0.16078432, S 0.0, L 0.38431373, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.c40052p125F}.
     * <pre>
     * <font style='background-color: #5B5B5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B5B5B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B5B5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5B5B5B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5B5B5B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5B5B5B'>&nbsp;@&nbsp;</font><font style='background-color: #5B5B5B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B5B5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B5B5B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD = -0x1.c40052p125F;
    static { NAMED.put("Lead", -0x1.c40052p125F); LIST.add(-0x1.c40052p125F); }

    /**
     * This color constant "Iron" has RGBA8888 code {@code 6E6E6EFF}, H 0.16078432, S 0.0, L 0.4627451, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.ec0052p125F}.
     * <pre>
     * <font style='background-color: #6E6E6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E6E6E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E6E6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6E6E6E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6E6E6E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6E6E6E'>&nbsp;@&nbsp;</font><font style='background-color: #6E6E6E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E6E6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E6E6E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float IRON = -0x1.ec0052p125F;
    static { NAMED.put("Iron", -0x1.ec0052p125F); LIST.add(-0x1.ec0052p125F); }

    /**
     * This color constant "Gray" has RGBA8888 code {@code 808080FF}, H 0.16078432, S 0.0, L 0.5372549, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.120052p126F}.
     * <pre>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #808080; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #808080; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #808080; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY = -0x1.120052p126F;
    static { NAMED.put("Gray", -0x1.120052p126F); LIST.add(-0x1.120052p126F); }

    /**
     * This color constant "Chinchilla" has RGBA8888 code {@code 929292FF}, H 0.16078432, S 0.0, L 0.60784316, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.360052p126F}.
     * <pre>
     * <font style='background-color: #929292;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #929292; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #929292;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #929292'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #929292'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #929292'>&nbsp;@&nbsp;</font><font style='background-color: #929292; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #929292;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #929292; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CHINCHILLA = -0x1.360052p126F;
    static { NAMED.put("Chinchilla", -0x1.360052p126F); LIST.add(-0x1.360052p126F); }

    /**
     * This color constant "Greyhound" has RGBA8888 code {@code A4A4A4FF}, H 0.16078432, S 0.0, L 0.6745098, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.580052p126F}.
     * <pre>
     * <font style='background-color: #A4A4A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4A4A4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4A4A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A4A4A4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A4A4A4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A4A4A4'>&nbsp;@&nbsp;</font><font style='background-color: #A4A4A4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4A4A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4A4A4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GREYHOUND = -0x1.580052p126F;
    static { NAMED.put("Greyhound", -0x1.580052p126F); LIST.add(-0x1.580052p126F); }

    /**
     * This color constant "Silver" has RGBA8888 code {@code B6B6B6FF}, H 0.16078432, S 0.0, L 0.7411765, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.7a0052p126F}.
     * <pre>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B6B6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #B6B6B6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B6B6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER = -0x1.7a0052p126F;
    static { NAMED.put("Silver", -0x1.7a0052p126F); LIST.add(-0x1.7a0052p126F); }

    /**
     * This color constant "Light Gray" has RGBA8888 code {@code C9C9C9FF}, H 0.16078432, S 0.0, L 0.8117647, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.9e0052p126F}.
     * <pre>
     * <font style='background-color: #C9C9C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9C9C9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9C9C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C9C9C9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C9C9C9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C9C9C9'>&nbsp;@&nbsp;</font><font style='background-color: #C9C9C9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9C9C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9C9C9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY = -0x1.9e0052p126F;
    static { NAMED.put("Light Gray", -0x1.9e0052p126F); LIST.add(-0x1.9e0052p126F); }

    /**
     * This color constant "Platinum" has RGBA8888 code {@code DBDBDBFF}, H 0.16078432, S 0.0, L 0.8745098, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.be0052p126F}.
     * <pre>
     * <font style='background-color: #DBDBDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBDBDB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBDBDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DBDBDB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DBDBDB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DBDBDB'>&nbsp;@&nbsp;</font><font style='background-color: #DBDBDB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBDBDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBDBDB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PLATINUM = -0x1.be0052p126F;
    static { NAMED.put("Platinum", -0x1.be0052p126F); LIST.add(-0x1.be0052p126F); }

    /**
     * This color constant "Cloud" has RGBA8888 code {@code EDEDEDFF}, H 0.16078432, S 0.0, L 0.9372549, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.de0052p126F}.
     * <pre>
     * <font style='background-color: #EDEDED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDEDED; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDEDED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EDEDED'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EDEDED'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EDEDED'>&nbsp;@&nbsp;</font><font style='background-color: #EDEDED; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDEDED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDEDED; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CLOUD = -0x1.de0052p126F;
    static { NAMED.put("Cloud", -0x1.de0052p126F); LIST.add(-0x1.de0052p126F); }

    /**
     * This color constant "White" has RGBA8888 code {@code FFFFFFFF}, H 0.16078432, S 0.0, L 1.0, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.fe0052p126F}.
     * <pre>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #FFFFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE = -0x1.fe0052p126F;
    static { NAMED.put("White", -0x1.fe0052p126F); LIST.add(-0x1.fe0052p126F); }

    /**
     * This color constant "Seawater" has RGBA8888 code {@code 007F7FFF}, H 0.53333336, S 1.0, L 0.47843137, alpha 1.0, and chroma 0.37840968.
     * It can be represented as a packed float with the constant {@code -0x1.f5ff1p125F}.
     * <pre>
     * <font style='background-color: #007F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007F7F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #007F7F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #007F7F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #007F7F'>&nbsp;@&nbsp;</font><font style='background-color: #007F7F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007F7F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SEAWATER = -0x1.f5ff1p125F;
    static { NAMED.put("Seawater", -0x1.f5ff1p125F); LIST.add(-0x1.f5ff1p125F); }

    /**
     * This color constant "Hospital Green" has RGBA8888 code {@code 3FBFBFFF}, H 0.53333336, S 0.8862745, L 0.70980394, alpha 1.0, and chroma 0.49756423.
     * It can be represented as a packed float with the constant {@code -0x1.6bc51p126F}.
     * <pre>
     * <font style='background-color: #3FBFBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3FBFBF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3FBFBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3FBFBF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3FBFBF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3FBFBF'>&nbsp;@&nbsp;</font><font style='background-color: #3FBFBF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3FBFBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3FBFBF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float HOSPITAL_GREEN = -0x1.6bc51p126F;
    static { NAMED.put("Hospital Green", -0x1.6bc51p126F); LIST.add(-0x1.6bc51p126F); }

    /**
     * This color constant "Cyan" has RGBA8888 code {@code 00FFFFFF}, H 0.53333336, S 1.0, L 0.9137255, alpha 1.0, and chroma 0.69789404.
     * It can be represented as a packed float with the constant {@code -0x1.d3ff1p126F}.
     * <pre>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #00FFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CYAN = -0x1.d3ff1p126F;
    static { NAMED.put("Cyan", -0x1.d3ff1p126F); LIST.add(-0x1.d3ff1p126F); }

    /**
     * This color constant "Bubble" has RGBA8888 code {@code BFFFFFFF}, H 0.53333336, S 1.0, L 0.9607843, alpha 1.0, and chroma 0.31031176.
     * It can be represented as a packed float with the constant {@code -0x1.ebff1p126F}.
     * <pre>
     * <font style='background-color: #BFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFFFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #BFFFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFFFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BUBBLE = -0x1.ebff1p126F;
    static { NAMED.put("Bubble", -0x1.ebff1p126F); LIST.add(-0x1.ebff1p126F); }

    /**
     * This color constant "Periwinkle" has RGBA8888 code {@code 8181FFFF}, H 0.7411765, S 1.0, L 0.59607846, alpha 1.0, and chroma 1.0385381.
     * It can be represented as a packed float with the constant {@code -0x1.31ff7ap126F}.
     * <pre>
     * <font style='background-color: #8181FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8181FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8181FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8181FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8181FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8181FF'>&nbsp;@&nbsp;</font><font style='background-color: #8181FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8181FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8181FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PERIWINKLE = -0x1.31ff7ap126F;
    static { NAMED.put("Periwinkle", -0x1.31ff7ap126F); LIST.add(-0x1.31ff7ap126F); }

    /**
     * This color constant "Blue" has RGBA8888 code {@code 0000FFFF}, H 0.7411765, S 1.0, L 0.32156864, alpha 1.0, and chroma 1.2676661.
     * It can be represented as a packed float with the constant {@code -0x1.a5ff7ap125F}.
     * <pre>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0000FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #0000FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0000FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUE = -0x1.a5ff7ap125F;
    static { NAMED.put("Blue", -0x1.a5ff7ap125F); LIST.add(-0x1.a5ff7ap125F); }

    /**
     * This color constant "Faded Blue" has RGBA8888 code {@code 3F3FBFFF}, H 0.7411765, S 0.70980394, L 0.34509805, alpha 1.0, and chroma 0.94092727.
     * It can be represented as a packed float with the constant {@code -0x1.b16b7ap125F}.
     * <pre>
     * <font style='background-color: #3F3FBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F3FBF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F3FBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3F3FBF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3F3FBF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3F3FBF'>&nbsp;@&nbsp;</font><font style='background-color: #3F3FBF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F3FBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F3FBF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_BLUE = -0x1.b16b7ap125F;
    static { NAMED.put("Faded Blue", -0x1.b16b7ap125F); LIST.add(-0x1.b16b7ap125F); }

    /**
     * This color constant "Ocean Blue" has RGBA8888 code {@code 00007FFF}, H 0.7411765, S 1.0, L 0.1254902, alpha 1.0, and chroma 0.494699.
     * It can be represented as a packed float with the constant {@code -0x1.41ff7ap125F}.
     * <pre>
     * <font style='background-color: #00007F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00007F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00007F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00007F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00007F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00007F'>&nbsp;@&nbsp;</font><font style='background-color: #00007F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00007F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00007F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float OCEAN_BLUE = -0x1.41ff7ap125F;
    static { NAMED.put("Ocean Blue", -0x1.41ff7ap125F); LIST.add(-0x1.41ff7ap125F); }

    /**
     * This color constant "Stygian Blue" has RGBA8888 code {@code 0F0F50FF}, H 0.7411765, S 0.77254903, L 0.09019608, alpha 1.0, and chroma 0.2746913.
     * It can be represented as a packed float with the constant {@code -0x1.2f8b7ap125F}.
     * <pre>
     * <font style='background-color: #0F0F50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F0F50; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F0F50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0F0F50'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0F0F50'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0F0F50'>&nbsp;@&nbsp;</font><font style='background-color: #0F0F50; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F0F50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F0F50; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STYGIAN_BLUE = -0x1.2f8b7ap125F;
    static { NAMED.put("Stygian Blue", -0x1.2f8b7ap125F); LIST.add(-0x1.2f8b7ap125F); }

    /**
     * This color constant "Deep Purple" has RGBA8888 code {@code 7F007FFF}, H 0.85490197, S 1.0, L 0.29411766, alpha 1.0, and chroma 0.6692215.
     * It can be represented as a packed float with the constant {@code -0x1.97ffb4p125F}.
     * <pre>
     * <font style='background-color: #7F007F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F007F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F007F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7F007F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7F007F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7F007F'>&nbsp;@&nbsp;</font><font style='background-color: #7F007F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F007F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F007F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE = -0x1.97ffb4p125F;
    static { NAMED.put("Deep Purple", -0x1.97ffb4p125F); LIST.add(-0x1.97ffb4p125F); }

    /**
     * This color constant "Tyrian Purple" has RGBA8888 code {@code BF3FBFFF}, H 0.85490197, S 0.79607844, L 0.49803922, alpha 1.0, and chroma 0.9021281.
     * It can be represented as a packed float with the constant {@code -0x1.ff97b4p125F}.
     * <pre>
     * <font style='background-color: #BF3FBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF3FBF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF3FBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BF3FBF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BF3FBF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BF3FBF'>&nbsp;@&nbsp;</font><font style='background-color: #BF3FBF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF3FBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF3FBF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TYRIAN_PURPLE = -0x1.ff97b4p125F;
    static { NAMED.put("Tyrian Purple", -0x1.ff97b4p125F); LIST.add(-0x1.ff97b4p125F); }

    /**
     * This color constant "Magenta" has RGBA8888 code {@code F500F5FF}, H 0.85490197, S 1.0, L 0.5803922, alpha 1.0, and chroma 1.3205972.
     * It can be represented as a packed float with the constant {@code -0x1.29ffb4p126F}.
     * <pre>
     * <font style='background-color: #F500F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F500F5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F500F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F500F5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F500F5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F500F5'>&nbsp;@&nbsp;</font><font style='background-color: #F500F5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F500F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F500F5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MAGENTA = -0x1.29ffb4p126F;
    static { NAMED.put("Magenta", -0x1.29ffb4p126F); LIST.add(-0x1.29ffb4p126F); }

    /**
     * This color constant "Bubblegum Pink" has RGBA8888 code {@code FD81FFFF}, H 0.85490197, S 1.0, L 0.72156864, alpha 1.0, and chroma 0.97768134.
     * It can be represented as a packed float with the constant {@code -0x1.71ffb4p126F}.
     * <pre>
     * <font style='background-color: #FD81FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD81FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD81FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FD81FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FD81FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FD81FF'>&nbsp;@&nbsp;</font><font style='background-color: #FD81FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD81FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD81FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BUBBLEGUM_PINK = -0x1.71ffb4p126F;
    static { NAMED.put("Bubblegum Pink", -0x1.71ffb4p126F); LIST.add(-0x1.71ffb4p126F); }

    /**
     * This color constant "Pork Chop" has RGBA8888 code {@code FFC0CBFF}, H 0.0, S 1.0, L 0.8352941, alpha 1.0, and chroma 0.38699868.
     * It can be represented as a packed float with the constant {@code -0x1.abfep126F}.
     * <pre>
     * <font style='background-color: #FFC0CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFC0CB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFC0CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFC0CB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFC0CB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFC0CB'>&nbsp;@&nbsp;</font><font style='background-color: #FFC0CB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFC0CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFC0CB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PORK_CHOP = -0x1.abfep126F;
    static { NAMED.put("Pork Chop", -0x1.abfep126F); LIST.add(-0x1.abfep126F); }

    /**
     * This color constant "Raw Meat" has RGBA8888 code {@code FF8181FF}, H 0.03137255, S 1.0, L 0.6862745, alpha 1.0, and chroma 0.9140242.
     * It can be represented as a packed float with the constant {@code -0x1.5ffe1p126F}.
     * <pre>
     * <font style='background-color: #FF8181;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF8181; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF8181;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF8181'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF8181'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF8181'>&nbsp;@&nbsp;</font><font style='background-color: #FF8181; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF8181;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF8181; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float RAW_MEAT = -0x1.5ffe1p126F;
    static { NAMED.put("Raw Meat", -0x1.5ffe1p126F); LIST.add(-0x1.5ffe1p126F); }

    /**
     * This color constant "Red" has RGBA8888 code {@code FF0000FF}, H 0.03137255, S 1.0, L 0.53333336, alpha 1.0, and chroma 1.760353.
     * It can be represented as a packed float with the constant {@code -0x1.11fe1p126F}.
     * <pre>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #FF0000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float RED = -0x1.11fe1p126F;
    static { NAMED.put("Red", -0x1.11fe1p126F); LIST.add(-0x1.11fe1p126F); }

    /**
     * This color constant "Putty" has RGBA8888 code {@code BF3F3FFF}, H 0.03137255, S 0.6431373, L 0.45490196, alpha 1.0, and chroma 0.96565634.
     * It can be represented as a packed float with the constant {@code -0x1.e9481p125F}.
     * <pre>
     * <font style='background-color: #BF3F3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF3F3F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF3F3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BF3F3F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BF3F3F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BF3F3F'>&nbsp;@&nbsp;</font><font style='background-color: #BF3F3F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF3F3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF3F3F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PUTTY = -0x1.e9481p125F;
    static { NAMED.put("Putty", -0x1.e9481p125F); LIST.add(-0x1.e9481p125F); }

    /**
     * This color constant "Sienna" has RGBA8888 code {@code 7F0000FF}, H 0.03137255, S 1.0, L 0.2509804, alpha 1.0, and chroma 0.82840157.
     * It can be represented as a packed float with the constant {@code -0x1.81fe1p125F}.
     * <pre>
     * <font style='background-color: #7F0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F0000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7F0000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7F0000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7F0000'>&nbsp;@&nbsp;</font><font style='background-color: #7F0000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F0000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SIENNA = -0x1.81fe1p125F;
    static { NAMED.put("Sienna", -0x1.81fe1p125F); LIST.add(-0x1.81fe1p125F); }

    /**
     * This color constant "Seal Brown" has RGBA8888 code {@code 551414FF}, H 0.03137255, S 0.69803923, L 0.1764706, alpha 1.0, and chroma 0.40658674.
     * It can be represented as a packed float with the constant {@code -0x1.5b641p125F}.
     * <pre>
     * <font style='background-color: #551414;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #551414; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #551414;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #551414'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #551414'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #551414'>&nbsp;@&nbsp;</font><font style='background-color: #551414; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #551414;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #551414; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SEAL_BROWN = -0x1.5b641p125F;
    static { NAMED.put("Seal Brown", -0x1.5b641p125F); LIST.add(-0x1.5b641p125F); }

    /**
     * This color constant "Mummy Brown" has RGBA8888 code {@code 7F3F00FF}, H 0.08627451, S 1.0, L 0.34117648, alpha 1.0, and chroma 0.61011684.
     * It can be represented as a packed float with the constant {@code -0x1.affe2cp125F}.
     * <pre>
     * <font style='background-color: #7F3F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F3F00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F3F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7F3F00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7F3F00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7F3F00'>&nbsp;@&nbsp;</font><font style='background-color: #7F3F00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F3F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F3F00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MUMMY_BROWN = -0x1.affe2cp125F;
    static { NAMED.put("Mummy Brown", -0x1.affe2cp125F); LIST.add(-0x1.affe2cp125F); }

    /**
     * This color constant "Fawn" has RGBA8888 code {@code BF7F3FFF}, H 0.11372549, S 0.7921569, L 0.5882353, alpha 1.0, and chroma 0.6911287.
     * It can be represented as a packed float with the constant {@code -0x1.2d943ap126F}.
     * <pre>
     * <font style='background-color: #BF7F3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF7F3F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF7F3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BF7F3F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BF7F3F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BF7F3F'>&nbsp;@&nbsp;</font><font style='background-color: #BF7F3F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF7F3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF7F3F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FAWN = -0x1.2d943ap126F;
    static { NAMED.put("Fawn", -0x1.2d943ap126F); LIST.add(-0x1.2d943ap126F); }

    /**
     * This color constant "Orange" has RGBA8888 code {@code FF7F00FF}, H 0.08235294, S 1.0, L 0.67058825, alpha 1.0, and chroma 1.2154517.
     * It can be represented as a packed float with the constant {@code -0x1.57fe2ap126F}.
     * <pre>
     * <font style='background-color: #FF7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF7F00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF7F00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF7F00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF7F00'>&nbsp;@&nbsp;</font><font style='background-color: #FF7F00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF7F00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ORANGE = -0x1.57fe2ap126F;
    static { NAMED.put("Orange", -0x1.57fe2ap126F); LIST.add(-0x1.57fe2ap126F); }

    /**
     * This color constant "Peach" has RGBA8888 code {@code FFBF81FF}, H 0.1254902, S 1.0, L 0.81960785, alpha 1.0, and chroma 0.67158496.
     * It can be represented as a packed float with the constant {@code -0x1.a3fe4p126F}.
     * <pre>
     * <font style='background-color: #FFBF81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFBF81; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFBF81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFBF81'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFBF81'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFBF81'>&nbsp;@&nbsp;</font><font style='background-color: #FFBF81; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFBF81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFBF81; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PEACH = -0x1.a3fe4p126F;
    static { NAMED.put("Peach", -0x1.a3fe4p126F); LIST.add(-0x1.a3fe4p126F); }

    /**
     * This color constant "Cream" has RGBA8888 code {@code FFFFBFFF}, H 0.23921569, S 1.0, L 0.9882353, alpha 1.0, and chroma 0.38299084.
     * It can be represented as a packed float with the constant {@code -0x1.f9fe7ap126F}.
     * <pre>
     * <font style='background-color: #FFFFBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFBF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFFBF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFFBF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFFBF'>&nbsp;@&nbsp;</font><font style='background-color: #FFFFBF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFBF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CREAM = -0x1.f9fe7ap126F;
    static { NAMED.put("Cream", -0x1.f9fe7ap126F); LIST.add(-0x1.f9fe7ap126F); }

    /**
     * This color constant "Yellow" has RGBA8888 code {@code FFFF00FF}, H 0.23921569, S 1.0, L 0.972549, alpha 1.0, and chroma 0.9927942.
     * It can be represented as a packed float with the constant {@code -0x1.f1fe7ap126F}.
     * <pre>
     * <font style='background-color: #FFFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFF00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFF00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFF00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFF00'>&nbsp;@&nbsp;</font><font style='background-color: #FFFF00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFF00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float YELLOW = -0x1.f1fe7ap126F;
    static { NAMED.put("Yellow", -0x1.f1fe7ap126F); LIST.add(-0x1.f1fe7ap126F); }

    /**
     * This color constant "Earwax" has RGBA8888 code {@code BFBF3FFF}, H 0.23921569, S 0.88235295, L 0.7529412, alpha 1.0, and chroma 0.7325987.
     * It can be represented as a packed float with the constant {@code -0x1.81c27ap126F}.
     * <pre>
     * <font style='background-color: #BFBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFBF3F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BFBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BFBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BFBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #BFBF3F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFBF3F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float EARWAX = -0x1.81c27ap126F;
    static { NAMED.put("Earwax", -0x1.81c27ap126F); LIST.add(-0x1.81c27ap126F); }

    /**
     * This color constant "Umber" has RGBA8888 code {@code 7F7F00FF}, H 0.23921569, S 1.0, L 0.5137255, alpha 1.0, and chroma 0.56649214.
     * It can be represented as a packed float with the constant {@code -0x1.07fe7ap126F}.
     * <pre>
     * <font style='background-color: #7F7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F7F00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7F7F00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7F7F00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7F7F00'>&nbsp;@&nbsp;</font><font style='background-color: #7F7F00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F7F00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float UMBER = -0x1.07fe7ap126F;
    static { NAMED.put("Umber", -0x1.07fe7ap126F); LIST.add(-0x1.07fe7ap126F); }

    /**
     * This color constant "Ivy Green" has RGBA8888 code {@code 007F00FF}, H 0.3529412, S 1.0, L 0.45882353, alpha 1.0, and chroma 0.7015522.
     * It can be represented as a packed float with the constant {@code -0x1.ebfeb4p125F}.
     * <pre>
     * <font style='background-color: #007F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007F00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #007F00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #007F00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #007F00'>&nbsp;@&nbsp;</font><font style='background-color: #007F00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007F00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float IVY_GREEN = -0x1.ebfeb4p125F;
    static { NAMED.put("Ivy Green", -0x1.ebfeb4p125F); LIST.add(-0x1.ebfeb4p125F); }

    /**
     * This color constant "Jade" has RGBA8888 code {@code 3FBF3FFF}, H 0.3529412, S 0.8509804, L 0.6862745, alpha 1.0, and chroma 0.89295936.
     * It can be represented as a packed float with the constant {@code -0x1.5fb2b4p126F}.
     * <pre>
     * <font style='background-color: #3FBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3FBF3F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3FBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3FBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3FBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3FBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #3FBF3F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3FBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3FBF3F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float JADE = -0x1.5fb2b4p126F;
    static { NAMED.put("Jade", -0x1.5fb2b4p126F); LIST.add(-0x1.5fb2b4p126F); }

    /**
     * This color constant "Green" has RGBA8888 code {@code 00FF00FF}, H 0.3529412, S 1.0, L 0.8784314, alpha 1.0, and chroma 1.3431426.
     * It can be represented as a packed float with the constant {@code -0x1.c1feb4p126F}.
     * <pre>
     * <font style='background-color: #00FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FF00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FF00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FF00'>&nbsp;@&nbsp;</font><font style='background-color: #00FF00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GREEN = -0x1.c1feb4p126F;
    static { NAMED.put("Green", -0x1.c1feb4p126F); LIST.add(-0x1.c1feb4p126F); }

    /**
     * This color constant "Celadon" has RGBA8888 code {@code AFFFAFFF}, H 0.3529412, S 1.0, L 0.93333334, alpha 1.0, and chroma 0.64464325.
     * It can be represented as a packed float with the constant {@code -0x1.ddfeb4p126F}.
     * <pre>
     * <font style='background-color: #AFFFAF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AFFFAF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AFFFAF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AFFFAF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AFFFAF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AFFFAF'>&nbsp;@&nbsp;</font><font style='background-color: #AFFFAF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AFFFAF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AFFFAF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CELADON = -0x1.ddfeb4p126F;
    static { NAMED.put("Celadon", -0x1.ddfeb4p126F); LIST.add(-0x1.ddfeb4p126F); }

    /**
     * This color constant "Puce" has RGBA8888 code {@code BCAFC0FF}, H 0.83137256, S 0.16078432, L 0.7294118, alpha 1.0, and chroma 0.13589491.
     * It can be represented as a packed float with the constant {@code -0x1.7453a8p126F}.
     * <pre>
     * <font style='background-color: #BCAFC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BCAFC0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BCAFC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BCAFC0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BCAFC0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BCAFC0'>&nbsp;@&nbsp;</font><font style='background-color: #BCAFC0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BCAFC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BCAFC0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PUCE = -0x1.7453a8p126F;
    static { NAMED.put("Puce", -0x1.7453a8p126F); LIST.add(-0x1.7453a8p126F); }

    /**
     * This color constant "Beige" has RGBA8888 code {@code CBAA89FF}, H 0.13725491, S 0.38431373, L 0.7176471, alpha 1.0, and chroma 0.36446273.
     * It can be represented as a packed float with the constant {@code -0x1.6ec446p126F}.
     * <pre>
     * <font style='background-color: #CBAA89;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBAA89; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBAA89;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CBAA89'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CBAA89'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CBAA89'>&nbsp;@&nbsp;</font><font style='background-color: #CBAA89; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBAA89;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBAA89; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BEIGE = -0x1.6ec446p126F;
    static { NAMED.put("Beige", -0x1.6ec446p126F); LIST.add(-0x1.6ec446p126F); }

    /**
     * This color constant "Wet Stone" has RGBA8888 code {@code A6A090FF}, H 0.19607843, S 0.18431373, L 0.65882355, alpha 1.0, and chroma 0.13690329.
     * It can be represented as a packed float with the constant {@code -0x1.505e64p126F}.
     * <pre>
     * <font style='background-color: #A6A090;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A6A090; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A6A090;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A6A090'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A6A090'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A6A090'>&nbsp;@&nbsp;</font><font style='background-color: #A6A090; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A6A090;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A6A090; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WET_STONE = -0x1.505e64p126F;
    static { NAMED.put("Wet Stone", -0x1.505e64p126F); LIST.add(-0x1.505e64p126F); }

    /**
     * This color constant "Slow Creek" has RGBA8888 code {@code 7E9494FF}, H 0.53333336, S 0.2509804, L 0.59607846, alpha 1.0, and chroma 0.11832751.
     * It can be represented as a packed float with the constant {@code -0x1.30811p126F}.
     * <pre>
     * <font style='background-color: #7E9494;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E9494; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E9494;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7E9494'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7E9494'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7E9494'>&nbsp;@&nbsp;</font><font style='background-color: #7E9494; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E9494;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E9494; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SLOW_CREEK = -0x1.30811p126F;
    static { NAMED.put("Slow Creek", -0x1.30811p126F); LIST.add(-0x1.30811p126F); }

    /**
     * This color constant "Slate Gray" has RGBA8888 code {@code 6E8287FF}, H 0.58431375, S 0.27058825, L 0.5294118, alpha 1.0, and chroma 0.12278095.
     * It can be represented as a packed float with the constant {@code -0x1.0e8b2ap126F}.
     * <pre>
     * <font style='background-color: #6E8287;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E8287; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E8287;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6E8287'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6E8287'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6E8287'>&nbsp;@&nbsp;</font><font style='background-color: #6E8287; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E8287;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E8287; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SLATE_GRAY = -0x1.0e8b2ap126F;
    static { NAMED.put("Slate Gray", -0x1.0e8b2ap126F); LIST.add(-0x1.0e8b2ap126F); }

    /**
     * This color constant "Light Skin 1" has RGBA8888 code {@code 7E6E60FF}, H 0.13333334, S 0.25882354, L 0.4745098, alpha 1.0, and chroma 0.16505033.
     * It can be represented as a packed float with the constant {@code -0x1.f28444p125F}.
     * <pre>
     * <font style='background-color: #7E6E60;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E6E60; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E6E60;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7E6E60'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7E6E60'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7E6E60'>&nbsp;@&nbsp;</font><font style='background-color: #7E6E60; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E6E60;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E6E60; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_SKIN_1 = -0x1.f28444p125F;
    static { NAMED.put("Light Skin 1", -0x1.f28444p125F); LIST.add(-0x1.f28444p125F); }

    /**
     * This color constant "Light Skin 2" has RGBA8888 code {@code A0695FFF}, H 0.05882353, S 0.34901962, L 0.49803922, alpha 1.0, and chroma 0.4067204.
     * It can be represented as a packed float with the constant {@code -0x1.feb21ep125F}.
     * <pre>
     * <font style='background-color: #A0695F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A0695F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A0695F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A0695F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A0695F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A0695F'>&nbsp;@&nbsp;</font><font style='background-color: #A0695F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A0695F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A0695F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_SKIN_2 = -0x1.feb21ep125F;
    static { NAMED.put("Light Skin 2", -0x1.feb21ep125F); LIST.add(-0x1.feb21ep125F); }

    /**
     * This color constant "Light Skin 3" has RGBA8888 code {@code C07872FF}, H 0.043137256, S 0.3372549, L 0.5803922, alpha 1.0, and chroma 0.5126337.
     * It can be represented as a packed float with the constant {@code -0x1.28ac16p126F}.
     * <pre>
     * <font style='background-color: #C07872;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C07872; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C07872;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C07872'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C07872'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C07872'>&nbsp;@&nbsp;</font><font style='background-color: #C07872; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C07872;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C07872; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_SKIN_3 = -0x1.28ac16p126F;
    static { NAMED.put("Light Skin 3", -0x1.28ac16p126F); LIST.add(-0x1.28ac16p126F); }

    /**
     * This color constant "Light Skin 4" has RGBA8888 code {@code D08A74FF}, H 0.07058824, S 0.43529412, L 0.6392157, alpha 1.0, and chroma 0.57291216.
     * It can be represented as a packed float with the constant {@code -0x1.46de24p126F}.
     * <pre>
     * <font style='background-color: #D08A74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D08A74; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D08A74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D08A74'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D08A74'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D08A74'>&nbsp;@&nbsp;</font><font style='background-color: #D08A74; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D08A74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D08A74; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_SKIN_4 = -0x1.46de24p126F;
    static { NAMED.put("Light Skin 4", -0x1.46de24p126F); LIST.add(-0x1.46de24p126F); }

    /**
     * This color constant "Light Skin 5" has RGBA8888 code {@code E19B7DFF}, H 0.08235294, S 0.5529412, L 0.7019608, alpha 1.0, and chroma 0.57249707.
     * It can be represented as a packed float with the constant {@code -0x1.671a2ap126F}.
     * <pre>
     * <font style='background-color: #E19B7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E19B7D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E19B7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E19B7D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E19B7D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E19B7D'>&nbsp;@&nbsp;</font><font style='background-color: #E19B7D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E19B7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E19B7D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_SKIN_5 = -0x1.671a2ap126F;
    static { NAMED.put("Light Skin 5", -0x1.671a2ap126F); LIST.add(-0x1.671a2ap126F); }

    /**
     * This color constant "Light Skin 6" has RGBA8888 code {@code EBAA8CFF}, H 0.09019608, S 0.6509804, L 0.7529412, alpha 1.0, and chroma 0.5333448.
     * It can be represented as a packed float with the constant {@code -0x1.814c2ep126F}.
     * <pre>
     * <font style='background-color: #EBAA8C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBAA8C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBAA8C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EBAA8C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EBAA8C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EBAA8C'>&nbsp;@&nbsp;</font><font style='background-color: #EBAA8C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBAA8C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBAA8C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_SKIN_6 = -0x1.814c2ep126F;
    static { NAMED.put("Light Skin 6", -0x1.814c2ep126F); LIST.add(-0x1.814c2ep126F); }

    /**
     * This color constant "Light Skin 7" has RGBA8888 code {@code F5B99BFF}, H 0.09411765, S 0.7921569, L 0.8, alpha 1.0, and chroma 0.49837765.
     * It can be represented as a packed float with the constant {@code -0x1.99943p126F}.
     * <pre>
     * <font style='background-color: #F5B99B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5B99B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5B99B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F5B99B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F5B99B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F5B99B'>&nbsp;@&nbsp;</font><font style='background-color: #F5B99B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5B99B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5B99B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_SKIN_7 = -0x1.99943p126F;
    static { NAMED.put("Light Skin 7", -0x1.99943p126F); LIST.add(-0x1.99943p126F); }

    /**
     * This color constant "Light Skin 8" has RGBA8888 code {@code F6C8AFFF}, H 0.101960786, S 0.77254903, L 0.8392157, alpha 1.0, and chroma 0.38522178.
     * It can be represented as a packed float with the constant {@code -0x1.ad8a34p126F}.
     * <pre>
     * <font style='background-color: #F6C8AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6C8AF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6C8AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F6C8AF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F6C8AF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F6C8AF'>&nbsp;@&nbsp;</font><font style='background-color: #F6C8AF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6C8AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6C8AF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_SKIN_8 = -0x1.ad8a34p126F;
    static { NAMED.put("Light Skin 8", -0x1.ad8a34p126F); LIST.add(-0x1.ad8a34p126F); }

    /**
     * This color constant "Light Skin 9" has RGBA8888 code {@code F5E1D2FF}, H 0.12941177, S 0.59607846, L 0.9098039, alpha 1.0, and chroma 0.18014328.
     * It can be represented as a packed float with the constant {@code -0x1.d13042p126F}.
     * <pre>
     * <font style='background-color: #F5E1D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5E1D2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5E1D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F5E1D2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F5E1D2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F5E1D2'>&nbsp;@&nbsp;</font><font style='background-color: #F5E1D2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5E1D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5E1D2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_SKIN_9 = -0x1.d13042p126F;
    static { NAMED.put("Light Skin 9", -0x1.d13042p126F); LIST.add(-0x1.d13042p126F); }

    /**
     * This color constant "Dark Skin 1" has RGBA8888 code {@code 573B3BFF}, H 0.03137255, S 0.18431373, L 0.2784314, alpha 1.0, and chroma 0.16938576.
     * It can be represented as a packed float with the constant {@code -0x1.8e5e1p125F}.
     * <pre>
     * <font style='background-color: #573B3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #573B3B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #573B3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #573B3B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #573B3B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #573B3B'>&nbsp;@&nbsp;</font><font style='background-color: #573B3B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #573B3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #573B3B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_SKIN_1 = -0x1.8e5e1p125F;
    static { NAMED.put("Dark Skin 1", -0x1.8e5e1p125F); LIST.add(-0x1.8e5e1p125F); }

    /**
     * This color constant "Dark Skin 2" has RGBA8888 code {@code 73413CFF}, H 0.043137256, S 0.3882353, L 0.33333334, alpha 1.0, and chroma 0.3738482.
     * It can be represented as a packed float with the constant {@code -0x1.aac616p125F}.
     * <pre>
     * <font style='background-color: #73413C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73413C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73413C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #73413C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #73413C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #73413C'>&nbsp;@&nbsp;</font><font style='background-color: #73413C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73413C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73413C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_SKIN_2 = -0x1.aac616p125F;
    static { NAMED.put("Dark Skin 2", -0x1.aac616p125F); LIST.add(-0x1.aac616p125F); }

    /**
     * This color constant "Dark Skin 3" has RGBA8888 code {@code 8E5555FF}, H 0.03137255, S 0.27450982, L 0.42745098, alpha 1.0, and chroma 0.38729802.
     * It can be represented as a packed float with the constant {@code -0x1.da8c1p125F}.
     * <pre>
     * <font style='background-color: #8E5555;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8E5555; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8E5555;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8E5555'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8E5555'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8E5555'>&nbsp;@&nbsp;</font><font style='background-color: #8E5555; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8E5555;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8E5555; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_SKIN_3 = -0x1.da8c1p125F;
    static { NAMED.put("Dark Skin 3", -0x1.da8c1p125F); LIST.add(-0x1.da8c1p125F); }

    /**
     * This color constant "Pink Skin 1" has RGBA8888 code {@code AB7373FF}, H 0.03137255, S 0.21960784, L 0.5411765, alpha 1.0, and chroma 0.37420207.
     * It can be represented as a packed float with the constant {@code -0x1.14701p126F}.
     * <pre>
     * <font style='background-color: #AB7373;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB7373; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB7373;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB7373'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB7373'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB7373'>&nbsp;@&nbsp;</font><font style='background-color: #AB7373; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB7373;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB7373; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINK_SKIN_1 = -0x1.14701p126F;
    static { NAMED.put("Pink Skin 1", -0x1.14701p126F); LIST.add(-0x1.14701p126F); }

    /**
     * This color constant "Pink Skin 2" has RGBA8888 code {@code C78F8FFF}, H 0.03137255, S 0.3372549, L 0.64705884, alpha 1.0, and chroma 0.36723107.
     * It can be represented as a packed float with the constant {@code -0x1.4aac1p126F}.
     * <pre>
     * <font style='background-color: #C78F8F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C78F8F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C78F8F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C78F8F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C78F8F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C78F8F'>&nbsp;@&nbsp;</font><font style='background-color: #C78F8F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C78F8F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C78F8F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINK_SKIN_2 = -0x1.4aac1p126F;
    static { NAMED.put("Pink Skin 2", -0x1.4aac1p126F); LIST.add(-0x1.4aac1p126F); }

    /**
     * This color constant "Pink Skin 3" has RGBA8888 code {@code E3ABABFF}, H 0.03137255, S 0.54509807, L 0.7529412, alpha 1.0, and chroma 0.35973412.
     * It can be represented as a packed float with the constant {@code -0x1.81161p126F}.
     * <pre>
     * <font style='background-color: #E3ABAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3ABAB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3ABAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E3ABAB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E3ABAB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E3ABAB'>&nbsp;@&nbsp;</font><font style='background-color: #E3ABAB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3ABAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3ABAB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINK_SKIN_3 = -0x1.81161p126F;
    static { NAMED.put("Pink Skin 3", -0x1.81161p126F); LIST.add(-0x1.81161p126F); }

    /**
     * This color constant "Pink Skin 4" has RGBA8888 code {@code F8D2DAFF}, H 0.99215686, S 0.7882353, L 0.8784314, alpha 1.0, and chroma 0.21639557.
     * It can be represented as a packed float with the constant {@code -0x1.c193fap126F}.
     * <pre>
     * <font style='background-color: #F8D2DA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F8D2DA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F8D2DA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F8D2DA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F8D2DA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F8D2DA'>&nbsp;@&nbsp;</font><font style='background-color: #F8D2DA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F8D2DA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F8D2DA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINK_SKIN_4 = -0x1.c193fap126F;
    static { NAMED.put("Pink Skin 4", -0x1.c193fap126F); LIST.add(-0x1.c193fap126F); }

    /**
     * This color constant "Bronze Skin 4" has RGBA8888 code {@code E3C7ABFF}, H 0.14117648, S 0.39215687, L 0.81960785, alpha 1.0, and chroma 0.300677.
     * It can be represented as a packed float with the constant {@code -0x1.a2c848p126F}.
     * <pre>
     * <font style='background-color: #E3C7AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3C7AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3C7AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E3C7AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E3C7AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E3C7AB'>&nbsp;@&nbsp;</font><font style='background-color: #E3C7AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3C7AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3C7AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRONZE_SKIN_4 = -0x1.a2c848p126F;
    static { NAMED.put("Bronze Skin 4", -0x1.a2c848p126F); LIST.add(-0x1.a2c848p126F); }

    /**
     * This color constant "Bronze Skin 3" has RGBA8888 code {@code C49E73FF}, H 0.14117648, S 0.5058824, L 0.6784314, alpha 1.0, and chroma 0.4463595.
     * It can be represented as a packed float with the constant {@code -0x1.5b0248p126F}.
     * <pre>
     * <font style='background-color: #C49E73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C49E73; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C49E73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C49E73'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C49E73'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C49E73'>&nbsp;@&nbsp;</font><font style='background-color: #C49E73; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C49E73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C49E73; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRONZE_SKIN_3 = -0x1.5b0248p126F;
    static { NAMED.put("Bronze Skin 3", -0x1.5b0248p126F); LIST.add(-0x1.5b0248p126F); }

    /**
     * This color constant "Bronze Skin 2" has RGBA8888 code {@code 8F7357FF}, H 0.13333334, S 0.45490196, L 0.5058824, alpha 1.0, and chroma 0.30926782.
     * It can be represented as a packed float with the constant {@code -0x1.02e844p126F}.
     * <pre>
     * <font style='background-color: #8F7357;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F7357; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F7357;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F7357'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F7357'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F7357'>&nbsp;@&nbsp;</font><font style='background-color: #8F7357; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F7357;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F7357; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRONZE_SKIN_2 = -0x1.02e844p126F;
    static { NAMED.put("Bronze Skin 2", -0x1.02e844p126F); LIST.add(-0x1.02e844p126F); }

    /**
     * This color constant "Bronze Skin 1" has RGBA8888 code {@code 73573BFF}, H 0.12941177, S 0.5568628, L 0.39215687, alpha 1.0, and chroma 0.29873347.
     * It can be represented as a packed float with the constant {@code -0x1.c91c42p125F}.
     * <pre>
     * <font style='background-color: #73573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73573B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #73573B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #73573B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #73573B'>&nbsp;@&nbsp;</font><font style='background-color: #73573B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73573B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRONZE_SKIN_1 = -0x1.c91c42p125F;
    static { NAMED.put("Bronze Skin 1", -0x1.c91c42p125F); LIST.add(-0x1.c91c42p125F); }

    /**
     * This color constant "Taupe" has RGBA8888 code {@code 3B2D1FFF}, H 0.13333334, S 0.49019608, L 0.19607843, alpha 1.0, and chroma 0.1291716.
     * It can be represented as a packed float with the constant {@code -0x1.64fa44p125F}.
     * <pre>
     * <font style='background-color: #3B2D1F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B2D1F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B2D1F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B2D1F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B2D1F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B2D1F'>&nbsp;@&nbsp;</font><font style='background-color: #3B2D1F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B2D1F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B2D1F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TAUPE = -0x1.64fa44p125F;
    static { NAMED.put("Taupe", -0x1.64fa44p125F); LIST.add(-0x1.64fa44p125F); }

    /**
     * This color constant "Drab Green" has RGBA8888 code {@code 414123FF}, H 0.23921569, S 0.627451, L 0.26666668, alpha 1.0, and chroma 0.18450637.
     * It can be represented as a packed float with the constant {@code -0x1.89407ap125F}.
     * <pre>
     * <font style='background-color: #414123;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #414123; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #414123;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #414123'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #414123'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #414123'>&nbsp;@&nbsp;</font><font style='background-color: #414123; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #414123;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #414123; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_GREEN = -0x1.89407ap125F;
    static { NAMED.put("Drab Green", -0x1.89407ap125F); LIST.add(-0x1.89407ap125F); }

    /**
     * This color constant "Lizard Scales" has RGBA8888 code {@code 73733BFF}, H 0.23921569, S 0.69803923, L 0.47058824, alpha 1.0, and chroma 0.36222935.
     * It can be represented as a packed float with the constant {@code -0x1.f1647ap125F}.
     * <pre>
     * <font style='background-color: #73733B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73733B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73733B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #73733B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #73733B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #73733B'>&nbsp;@&nbsp;</font><font style='background-color: #73733B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73733B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73733B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIZARD_SCALES = -0x1.f1647ap125F;
    static { NAMED.put("Lizard Scales", -0x1.f1647ap125F); LIST.add(-0x1.f1647ap125F); }

    /**
     * This color constant "Cricket" has RGBA8888 code {@code 8F8F57FF}, H 0.23921569, S 0.59607846, L 0.5803922, alpha 1.0, and chroma 0.38149405.
     * It can be represented as a packed float with the constant {@code -0x1.29307ap126F}.
     * <pre>
     * <font style='background-color: #8F8F57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F8F57; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F8F57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F8F57'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F8F57'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F8F57'>&nbsp;@&nbsp;</font><font style='background-color: #8F8F57; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F8F57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F8F57; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CRICKET = -0x1.29307ap126F;
    static { NAMED.put("Cricket", -0x1.29307ap126F); LIST.add(-0x1.29307ap126F); }

    /**
     * This color constant "Olive Oil" has RGBA8888 code {@code A2A255FF}, H 0.23921569, S 0.7019608, L 0.6509804, alpha 1.0, and chroma 0.503899.
     * It can be represented as a packed float with the constant {@code -0x1.4d667ap126F}.
     * <pre>
     * <font style='background-color: #A2A255;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2A255; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2A255;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A2A255'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A2A255'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A2A255'>&nbsp;@&nbsp;</font><font style='background-color: #A2A255; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2A255;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2A255; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float OLIVE_OIL = -0x1.4d667ap126F;
    static { NAMED.put("Olive Oil", -0x1.4d667ap126F); LIST.add(-0x1.4d667ap126F); }

    /**
     * This color constant "Dun" has RGBA8888 code {@code B5B572FF}, H 0.23921569, S 0.5803922, L 0.7254902, alpha 1.0, and chroma 0.46431842.
     * It can be represented as a packed float with the constant {@code -0x1.73287ap126F}.
     * <pre>
     * <font style='background-color: #B5B572;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B5B572; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B5B572;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B5B572'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B5B572'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B5B572'>&nbsp;@&nbsp;</font><font style='background-color: #B5B572; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B5B572;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B5B572; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DUN = -0x1.73287ap126F;
    static { NAMED.put("Dun", -0x1.73287ap126F); LIST.add(-0x1.73287ap126F); }

    /**
     * This color constant "Corn Silk" has RGBA8888 code {@code C7C78FFF}, H 0.23921569, S 0.45882353, L 0.7921569, alpha 1.0, and chroma 0.4007925.
     * It can be represented as a packed float with the constant {@code -0x1.94ea7ap126F}.
     * <pre>
     * <font style='background-color: #C7C78F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7C78F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7C78F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C7C78F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C7C78F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C7C78F'>&nbsp;@&nbsp;</font><font style='background-color: #C7C78F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7C78F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7C78F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CORN_SILK = -0x1.94ea7ap126F;
    static { NAMED.put("Corn Silk", -0x1.94ea7ap126F); LIST.add(-0x1.94ea7ap126F); }

    /**
     * This color constant "Tan" has RGBA8888 code {@code DADAABFF}, H 0.23921569, S 0.36078432, L 0.8627451, alpha 1.0, and chroma 0.34323606.
     * It can be represented as a packed float with the constant {@code -0x1.b8b87ap126F}.
     * <pre>
     * <font style='background-color: #DADAAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DADAAB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DADAAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DADAAB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DADAAB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DADAAB'>&nbsp;@&nbsp;</font><font style='background-color: #DADAAB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DADAAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DADAAB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TAN = -0x1.b8b87ap126F;
    static { NAMED.put("Tan", -0x1.b8b87ap126F); LIST.add(-0x1.b8b87ap126F); }

    /**
     * This color constant "Straw" has RGBA8888 code {@code EDEDC7FF}, H 0.23921569, S 0.27450982, L 0.92941177, alpha 1.0, and chroma 0.28133827.
     * It can be represented as a packed float with the constant {@code -0x1.da8c7ap126F}.
     * <pre>
     * <font style='background-color: #EDEDC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDEDC7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDEDC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EDEDC7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EDEDC7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EDEDC7'>&nbsp;@&nbsp;</font><font style='background-color: #EDEDC7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDEDC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDEDC7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRAW = -0x1.da8c7ap126F;
    static { NAMED.put("Straw", -0x1.da8c7ap126F); LIST.add(-0x1.da8c7ap126F); }

    /**
     * This color constant "Honeydew" has RGBA8888 code {@code C7E3ABFF}, H 0.3019608, S 0.3764706, L 0.87058824, alpha 1.0, and chroma 0.40011.
     * It can be represented as a packed float with the constant {@code -0x1.bcc09ap126F}.
     * <pre>
     * <font style='background-color: #C7E3AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7E3AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7E3AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C7E3AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C7E3AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C7E3AB'>&nbsp;@&nbsp;</font><font style='background-color: #C7E3AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7E3AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7E3AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float HONEYDEW = -0x1.bcc09ap126F;
    static { NAMED.put("Honeydew", -0x1.bcc09ap126F); LIST.add(-0x1.bcc09ap126F); }

    /**
     * This color constant "Tarnish" has RGBA8888 code {@code ABC78FFF}, H 0.30588236, S 0.42352942, L 0.77254903, alpha 1.0, and chroma 0.4043223.
     * It can be represented as a packed float with the constant {@code -0x1.8ad89cp126F}.
     * <pre>
     * <font style='background-color: #ABC78F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABC78F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABC78F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ABC78F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ABC78F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ABC78F'>&nbsp;@&nbsp;</font><font style='background-color: #ABC78F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABC78F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABC78F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TARNISH = -0x1.8ad89cp126F;
    static { NAMED.put("Tarnish", -0x1.8ad89cp126F); LIST.add(-0x1.8ad89cp126F); }

    /**
     * This color constant "Pea Soup" has RGBA8888 code {@code 8EBE55FF}, H 0.30588236, S 0.7607843, L 0.7176471, alpha 1.0, and chroma 0.6746687.
     * It can be represented as a packed float with the constant {@code -0x1.6f849cp126F}.
     * <pre>
     * <font style='background-color: #8EBE55;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8EBE55; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8EBE55;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8EBE55'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8EBE55'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8EBE55'>&nbsp;@&nbsp;</font><font style='background-color: #8EBE55; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8EBE55;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8EBE55; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PEA_SOUP = -0x1.6f849cp126F;
    static { NAMED.put("Pea Soup", -0x1.6f849cp126F); LIST.add(-0x1.6f849cp126F); }

    /**
     * This color constant "Marsh" has RGBA8888 code {@code 738F57FF}, H 0.30588236, S 0.56078434, L 0.56078434, alpha 1.0, and chroma 0.38860625.
     * It can be represented as a packed float with the constant {@code -0x1.1f1e9cp126F}.
     * <pre>
     * <font style='background-color: #738F57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #738F57; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #738F57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #738F57'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #738F57'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #738F57'>&nbsp;@&nbsp;</font><font style='background-color: #738F57; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #738F57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #738F57; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MARSH = -0x1.1f1e9cp126F;
    static { NAMED.put("Marsh", -0x1.1f1e9cp126F); LIST.add(-0x1.1f1e9cp126F); }

    /**
     * This color constant "Asparagus" has RGBA8888 code {@code 587D3EFF}, H 0.32156864, S 0.68235296, L 0.48235294, alpha 1.0, and chroma 0.4303635.
     * It can be represented as a packed float with the constant {@code -0x1.f75ca4p125F}.
     * <pre>
     * <font style='background-color: #587D3E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #587D3E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #587D3E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #587D3E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #587D3E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #587D3E'>&nbsp;@&nbsp;</font><font style='background-color: #587D3E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #587D3E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #587D3E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ASPARAGUS = -0x1.f75ca4p125F;
    static { NAMED.put("Asparagus", -0x1.f75ca4p125F); LIST.add(-0x1.f75ca4p125F); }

    /**
     * This color constant "Peat Bog" has RGBA8888 code {@code 465032FF}, H 0.28627452, S 0.5176471, L 0.32156864, alpha 1.0, and chroma 0.19497654.
     * It can be represented as a packed float with the constant {@code -0x1.a50892p125F}.
     * <pre>
     * <font style='background-color: #465032;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #465032; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #465032;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #465032'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #465032'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #465032'>&nbsp;@&nbsp;</font><font style='background-color: #465032; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #465032;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #465032; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PEAT_BOG = -0x1.a50892p125F;
    static { NAMED.put("Peat Bog", -0x1.a50892p125F); LIST.add(-0x1.a50892p125F); }

    /**
     * This color constant "Deep Jungle" has RGBA8888 code {@code 191E0FFF}, H 0.28627452, S 0.54901963, L 0.101960786, alpha 1.0, and chroma 0.06556862.
     * It can be represented as a packed float with the constant {@code -0x1.351892p125F}.
     * <pre>
     * <font style='background-color: #191E0F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #191E0F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #191E0F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #191E0F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #191E0F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #191E0F'>&nbsp;@&nbsp;</font><font style='background-color: #191E0F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #191E0F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #191E0F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_JUNGLE = -0x1.351892p125F;
    static { NAMED.put("Deep Jungle", -0x1.351892p125F); LIST.add(-0x1.351892p125F); }

    /**
     * This color constant "Pine Green" has RGBA8888 code {@code 235037FF}, H 0.39607844, S 0.7176471, L 0.3019608, alpha 1.0, and chroma 0.23927084.
     * It can be represented as a packed float with the constant {@code -0x1.9b6ecap125F}.
     * <pre>
     * <font style='background-color: #235037;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #235037; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #235037;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #235037'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #235037'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #235037'>&nbsp;@&nbsp;</font><font style='background-color: #235037; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #235037;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #235037; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINE_GREEN = -0x1.9b6ecap125F;
    static { NAMED.put("Pine Green", -0x1.9b6ecap125F); LIST.add(-0x1.9b6ecap125F); }

    /**
     * This color constant "Olive Green" has RGBA8888 code {@code 3B573BFF}, H 0.3529412, S 0.4117647, L 0.34117648, alpha 1.0, and chroma 0.21480407.
     * It can be represented as a packed float with the constant {@code -0x1.aed2b4p125F}.
     * <pre>
     * <font style='background-color: #3B573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B573B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B573B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B573B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B573B'>&nbsp;@&nbsp;</font><font style='background-color: #3B573B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B573B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float OLIVE_GREEN = -0x1.aed2b4p125F;
    static { NAMED.put("Olive Green", -0x1.aed2b4p125F); LIST.add(-0x1.aed2b4p125F); }

    /**
     * This color constant "Gray Green" has RGBA8888 code {@code 506450FF}, H 0.3529412, S 0.25882354, L 0.40392157, alpha 1.0, and chroma 0.15985091.
     * It can be represented as a packed float with the constant {@code -0x1.ce84b4p125F}.
     * <pre>
     * <font style='background-color: #506450;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #506450; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #506450;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #506450'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #506450'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #506450'>&nbsp;@&nbsp;</font><font style='background-color: #506450; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #506450;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #506450; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_GREEN = -0x1.ce84b4p125F;
    static { NAMED.put("Gray Green", -0x1.ce84b4p125F); LIST.add(-0x1.ce84b4p125F); }

    /**
     * This color constant "Maidenhair Fern" has RGBA8888 code {@code 3B7349FF}, H 0.3764706, S 0.6509804, L 0.4392157, alpha 1.0, and chroma 0.36220205.
     * It can be represented as a packed float with the constant {@code -0x1.e14ccp125F}.
     * <pre>
     * <font style='background-color: #3B7349;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B7349; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B7349;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B7349'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B7349'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B7349'>&nbsp;@&nbsp;</font><font style='background-color: #3B7349; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B7349;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B7349; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MAIDENHAIR_FERN = -0x1.e14ccp125F;
    static { NAMED.put("Maidenhair Fern", -0x1.e14ccp125F); LIST.add(-0x1.e14ccp125F); }

    /**
     * This color constant "Kelly Green" has RGBA8888 code {@code 578F57FF}, H 0.3529412, S 0.5294118, L 0.54509807, alpha 1.0, and chroma 0.44124776.
     * It can be represented as a packed float with the constant {@code -0x1.170eb4p126F}.
     * <pre>
     * <font style='background-color: #578F57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #578F57; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #578F57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #578F57'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #578F57'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #578F57'>&nbsp;@&nbsp;</font><font style='background-color: #578F57; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #578F57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #578F57; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float KELLY_GREEN = -0x1.170eb4p126F;
    static { NAMED.put("Kelly Green", -0x1.170eb4p126F); LIST.add(-0x1.170eb4p126F); }

    /**
     * This color constant "Dusty Green" has RGBA8888 code {@code 73AB73FF}, H 0.3529412, S 0.4509804, L 0.6509804, alpha 1.0, and chroma 0.44888988.
     * It can be represented as a packed float with the constant {@code -0x1.4ce6b4p126F}.
     * <pre>
     * <font style='background-color: #73AB73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73AB73; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73AB73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #73AB73'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #73AB73'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #73AB73'>&nbsp;@&nbsp;</font><font style='background-color: #73AB73; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73AB73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73AB73; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DUSTY_GREEN = -0x1.4ce6b4p126F;
    static { NAMED.put("Dusty Green", -0x1.4ce6b4p126F); LIST.add(-0x1.4ce6b4p126F); }

    /**
     * This color constant "Garter Snake" has RGBA8888 code {@code 64C082FF}, H 0.38431373, S 0.67058825, L 0.70980394, alpha 1.0, and chroma 0.5683644.
     * It can be represented as a packed float with the constant {@code -0x1.6b56c4p126F}.
     * <pre>
     * <font style='background-color: #64C082;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #64C082; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #64C082;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #64C082'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #64C082'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #64C082'>&nbsp;@&nbsp;</font><font style='background-color: #64C082; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #64C082;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #64C082; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GARTER_SNAKE = -0x1.6b56c4p126F;
    static { NAMED.put("Garter Snake", -0x1.6b56c4p126F); LIST.add(-0x1.6b56c4p126F); }

    /**
     * This color constant "Silver Green" has RGBA8888 code {@code 8FC78FFF}, H 0.3529412, S 0.39215687, L 0.7529412, alpha 1.0, and chroma 0.45147648.
     * It can be represented as a packed float with the constant {@code -0x1.80c8b4p126F}.
     * <pre>
     * <font style='background-color: #8FC78F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8FC78F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8FC78F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8FC78F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8FC78F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8FC78F'>&nbsp;@&nbsp;</font><font style='background-color: #8FC78F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8FC78F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8FC78F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_GREEN = -0x1.80c8b4p126F;
    static { NAMED.put("Silver Green", -0x1.80c8b4p126F); LIST.add(-0x1.80c8b4p126F); }

    /**
     * This color constant "Pistachio" has RGBA8888 code {@code A2D8A2FF}, H 0.3529412, S 0.34901962, L 0.8156863, alpha 1.0, and chroma 0.4352986.
     * It can be represented as a packed float with the constant {@code -0x1.a0b2b4p126F}.
     * <pre>
     * <font style='background-color: #A2D8A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2D8A2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2D8A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A2D8A2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A2D8A2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A2D8A2'>&nbsp;@&nbsp;</font><font style='background-color: #A2D8A2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2D8A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2D8A2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PISTACHIO = -0x1.a0b2b4p126F;
    static { NAMED.put("Pistachio", -0x1.a0b2b4p126F); LIST.add(-0x1.a0b2b4p126F); }

    /**
     * This color constant "Angel Wing" has RGBA8888 code {@code E1F8FAFF}, H 0.5529412, S 0.56078434, L 0.9607843, alpha 1.0, and chroma 0.12791756.
     * It can be represented as a packed float with the constant {@code -0x1.eb1f1ap126F}.
     * <pre>
     * <font style='background-color: #E1F8FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1F8FA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1F8FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E1F8FA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E1F8FA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E1F8FA'>&nbsp;@&nbsp;</font><font style='background-color: #E1F8FA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1F8FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1F8FA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ANGEL_WING = -0x1.eb1f1ap126F;
    static { NAMED.put("Angel Wing", -0x1.eb1f1ap126F); LIST.add(-0x1.eb1f1ap126F); }

    /**
     * This color constant "Sage Green" has RGBA8888 code {@code B4EECAFF}, H 0.39607844, S 0.39215687, L 0.89411765, alpha 1.0, and chroma 0.36174867.
     * It can be represented as a packed float with the constant {@code -0x1.c8c8cap126F}.
     * <pre>
     * <font style='background-color: #B4EECA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4EECA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4EECA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B4EECA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B4EECA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B4EECA'>&nbsp;@&nbsp;</font><font style='background-color: #B4EECA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4EECA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4EECA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SAGE_GREEN = -0x1.c8c8cap126F;
    static { NAMED.put("Sage Green", -0x1.c8c8cap126F); LIST.add(-0x1.c8c8cap126F); }

    /**
     * This color constant "Dried Sage" has RGBA8888 code {@code ABE3C5FF}, H 0.4117647, S 0.3764706, L 0.85882354, alpha 1.0, and chroma 0.32698917.
     * It can be represented as a packed float with the constant {@code -0x1.b6c0d2p126F}.
     * <pre>
     * <font style='background-color: #ABE3C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABE3C5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABE3C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ABE3C5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ABE3C5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ABE3C5'>&nbsp;@&nbsp;</font><font style='background-color: #ABE3C5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABE3C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABE3C5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRIED_SAGE = -0x1.b6c0d2p126F;
    static { NAMED.put("Dried Sage", -0x1.b6c0d2p126F); LIST.add(-0x1.b6c0d2p126F); }

    /**
     * This color constant "Artichoke" has RGBA8888 code {@code 87B48EFF}, H 0.36862746, S 0.35686275, L 0.69411767, alpha 1.0, and chroma 0.3350176.
     * It can be represented as a packed float with the constant {@code -0x1.62b6bcp126F}.
     * <pre>
     * <font style='background-color: #87B48E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #87B48E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #87B48E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #87B48E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #87B48E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #87B48E'>&nbsp;@&nbsp;</font><font style='background-color: #87B48E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #87B48E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #87B48E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ARTICHOKE = -0x1.62b6bcp126F;
    static { NAMED.put("Artichoke", -0x1.62b6bcp126F); LIST.add(-0x1.62b6bcp126F); }

    /**
     * This color constant "Viridian" has RGBA8888 code {@code 507D5FFF}, H 0.3882353, S 0.5058824, L 0.4862745, alpha 1.0, and chroma 0.28579924.
     * It can be represented as a packed float with the constant {@code -0x1.f902c6p125F}.
     * <pre>
     * <font style='background-color: #507D5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #507D5F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #507D5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #507D5F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #507D5F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #507D5F'>&nbsp;@&nbsp;</font><font style='background-color: #507D5F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #507D5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #507D5F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIRIDIAN = -0x1.f902c6p125F;
    static { NAMED.put("Viridian", -0x1.f902c6p125F); LIST.add(-0x1.f902c6p125F); }

    /**
     * This color constant "Floral Foam" has RGBA8888 code {@code 0F6946FF}, H 0.4117647, S 0.9529412, L 0.3882353, alpha 1.0, and chroma 0.37416178.
     * It can be represented as a packed float with the constant {@code -0x1.c7e6d2p125F}.
     * <pre>
     * <font style='background-color: #0F6946;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F6946; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F6946;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0F6946'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0F6946'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0F6946'>&nbsp;@&nbsp;</font><font style='background-color: #0F6946; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F6946;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F6946; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FLORAL_FOAM = -0x1.c7e6d2p125F;
    static { NAMED.put("Floral Foam", -0x1.c7e6d2p125F); LIST.add(-0x1.c7e6d2p125F); }

    /**
     * This color constant "Hunter Green" has RGBA8888 code {@code 1E2D23FF}, H 0.3882353, S 0.4, L 0.16862746, alpha 1.0, and chroma 0.07836433.
     * It can be represented as a packed float with the constant {@code -0x1.56ccc6p125F}.
     * <pre>
     * <font style='background-color: #1E2D23;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1E2D23; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1E2D23;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1E2D23'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1E2D23'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1E2D23'>&nbsp;@&nbsp;</font><font style='background-color: #1E2D23; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1E2D23;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1E2D23; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float HUNTER_GREEN = -0x1.56ccc6p125F;
    static { NAMED.put("Hunter Green", -0x1.56ccc6p125F); LIST.add(-0x1.56ccc6p125F); }

    /**
     * This color constant "Dark Teal" has RGBA8888 code {@code 234146FF}, H 0.5764706, S 0.6509804, L 0.25490198, alpha 1.0, and chroma 0.13945737.
     * It can be represented as a packed float with the constant {@code -0x1.834d26p125F}.
     * <pre>
     * <font style='background-color: #234146;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #234146; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #234146;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #234146'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #234146'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #234146'>&nbsp;@&nbsp;</font><font style='background-color: #234146; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #234146;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #234146; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_TEAL = -0x1.834d26p125F;
    static { NAMED.put("Dark Teal", -0x1.834d26p125F); LIST.add(-0x1.834d26p125F); }

    /**
     * This color constant "Kyanite" has RGBA8888 code {@code 3B7373FF}, H 0.53333336, S 0.7019608, L 0.44705883, alpha 1.0, and chroma 0.24821049.
     * It can be represented as a packed float with the constant {@code -0x1.e5671p125F}.
     * <pre>
     * <font style='background-color: #3B7373;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B7373; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B7373;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B7373'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B7373'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B7373'>&nbsp;@&nbsp;</font><font style='background-color: #3B7373; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B7373;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B7373; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float KYANITE = -0x1.e5671p125F;
    static { NAMED.put("Kyanite", -0x1.e5671p125F); LIST.add(-0x1.e5671p125F); }

    /**
     * This color constant "Spearmint" has RGBA8888 code {@code 64ABABFF}, H 0.53333336, S 0.6392157, L 0.654902, alpha 1.0, and chroma 0.3311054.
     * It can be represented as a packed float with the constant {@code -0x1.4f471p126F}.
     * <pre>
     * <font style='background-color: #64ABAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #64ABAB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #64ABAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #64ABAB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #64ABAB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #64ABAB'>&nbsp;@&nbsp;</font><font style='background-color: #64ABAB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #64ABAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #64ABAB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SPEARMINT = -0x1.4f471p126F;
    static { NAMED.put("Spearmint", -0x1.4f471p126F); LIST.add(-0x1.4f471p126F); }

    /**
     * This color constant "Amazonite" has RGBA8888 code {@code 8FC7C7FF}, H 0.53333336, S 0.46666667, L 0.7647059, alpha 1.0, and chroma 0.28225636.
     * It can be represented as a packed float with the constant {@code -0x1.86ef1p126F}.
     * <pre>
     * <font style='background-color: #8FC7C7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8FC7C7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8FC7C7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8FC7C7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8FC7C7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8FC7C7'>&nbsp;@&nbsp;</font><font style='background-color: #8FC7C7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8FC7C7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8FC7C7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float AMAZONITE = -0x1.86ef1p126F;
    static { NAMED.put("Amazonite", -0x1.86ef1p126F); LIST.add(-0x1.86ef1p126F); }

    /**
     * This color constant "Pastel Sky" has RGBA8888 code {@code ABE3E3FF}, H 0.53333336, S 0.41568628, L 0.8666667, alpha 1.0, and chroma 0.2849445.
     * It can be represented as a packed float with the constant {@code -0x1.bad51p126F}.
     * <pre>
     * <font style='background-color: #ABE3E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABE3E3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABE3E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ABE3E3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ABE3E3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ABE3E3'>&nbsp;@&nbsp;</font><font style='background-color: #ABE3E3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABE3E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABE3E3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PASTEL_SKY = -0x1.bad51p126F;
    static { NAMED.put("Pastel Sky", -0x1.bad51p126F); LIST.add(-0x1.bad51p126F); }

    /**
     * This color constant "Aquamarine" has RGBA8888 code {@code C7F1F1FF}, H 0.53333336, S 0.35686275, L 0.9254902, alpha 1.0, and chroma 0.21391127.
     * It can be represented as a packed float with the constant {@code -0x1.d8b71p126F}.
     * <pre>
     * <font style='background-color: #C7F1F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7F1F1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7F1F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C7F1F1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C7F1F1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C7F1F1'>&nbsp;@&nbsp;</font><font style='background-color: #C7F1F1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7F1F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7F1F1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float AQUAMARINE = -0x1.d8b71p126F;
    static { NAMED.put("Aquamarine", -0x1.d8b71p126F); LIST.add(-0x1.d8b71p126F); }

    /**
     * This color constant "Dust Bunny" has RGBA8888 code {@code BED2F0FF}, H 0.68235296, S 0.6666667, L 0.8392157, alpha 1.0, and chroma 0.28666228.
     * It can be represented as a packed float with the constant {@code -0x1.ad555cp126F}.
     * <pre>
     * <font style='background-color: #BED2F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BED2F0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BED2F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BED2F0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BED2F0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BED2F0'>&nbsp;@&nbsp;</font><font style='background-color: #BED2F0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BED2F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BED2F0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DUST_BUNNY = -0x1.ad555cp126F;
    static { NAMED.put("Dust Bunny", -0x1.ad555cp126F); LIST.add(-0x1.ad555cp126F); }

    /**
     * This color constant "Patina" has RGBA8888 code {@code ABC7E3FF}, H 0.6666667, S 0.5137255, L 0.7921569, alpha 1.0, and chroma 0.3006122.
     * It can be represented as a packed float with the constant {@code -0x1.950754p126F}.
     * <pre>
     * <font style='background-color: #ABC7E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABC7E3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABC7E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ABC7E3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ABC7E3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ABC7E3'>&nbsp;@&nbsp;</font><font style='background-color: #ABC7E3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABC7E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABC7E3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PATINA = -0x1.950754p126F;
    static { NAMED.put("Patina", -0x1.950754p126F); LIST.add(-0x1.950754p126F); }

    /**
     * This color constant "Chipped Granite" has RGBA8888 code {@code A8B9DCFF}, H 0.69803923, S 0.4862745, L 0.7490196, alpha 1.0, and chroma 0.32365325.
     * It can be represented as a packed float with the constant {@code -0x1.7ef964p126F}.
     * <pre>
     * <font style='background-color: #A8B9DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A8B9DC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A8B9DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A8B9DC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A8B9DC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A8B9DC'>&nbsp;@&nbsp;</font><font style='background-color: #A8B9DC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A8B9DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A8B9DC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CHIPPED_GRANITE = -0x1.7ef964p126F;
    static { NAMED.put("Chipped Granite", -0x1.7ef964p126F); LIST.add(-0x1.7ef964p126F); }

    /**
     * This color constant "Blue Smoke" has RGBA8888 code {@code 8FABC7FF}, H 0.6666667, S 0.34117648, L 0.6901961, alpha 1.0, and chroma 0.30265084.
     * It can be represented as a packed float with the constant {@code -0x1.60af54p126F}.
     * <pre>
     * <font style='background-color: #8FABC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8FABC7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8FABC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8FABC7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8FABC7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8FABC7'>&nbsp;@&nbsp;</font><font style='background-color: #8FABC7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8FABC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8FABC7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUE_SMOKE = -0x1.60af54p126F;
    static { NAMED.put("Blue Smoke", -0x1.60af54p126F); LIST.add(-0x1.60af54p126F); }

    /**
     * This color constant "Air Force Blue" has RGBA8888 code {@code 578FC7FF}, H 0.68235296, S 0.6862745, L 0.5803922, alpha 1.0, and chroma 0.60186404.
     * It can be represented as a packed float with the constant {@code -0x1.295f5cp126F}.
     * <pre>
     * <font style='background-color: #578FC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #578FC7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #578FC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #578FC7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #578FC7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #578FC7'>&nbsp;@&nbsp;</font><font style='background-color: #578FC7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #578FC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #578FC7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float AIR_FORCE_BLUE = -0x1.295f5cp126F;
    static { NAMED.put("Air Force Blue", -0x1.295f5cp126F); LIST.add(-0x1.295f5cp126F); }

    /**
     * This color constant "Cold Iron" has RGBA8888 code {@code 57738FFF}, H 0.67058825, S 0.46666667, L 0.4745098, alpha 1.0, and chroma 0.29910365.
     * It can be represented as a packed float with the constant {@code -0x1.f2ef56p125F}.
     * <pre>
     * <font style='background-color: #57738F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57738F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57738F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #57738F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #57738F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #57738F'>&nbsp;@&nbsp;</font><font style='background-color: #57738F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57738F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57738F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float COLD_IRON = -0x1.f2ef56p125F;
    static { NAMED.put("Cold Iron", -0x1.f2ef56p125F); LIST.add(-0x1.f2ef56p125F); }

    /**
     * This color constant "Dreary Blue" has RGBA8888 code {@code 3B5773FF}, H 0.6745098, S 0.5686275, L 0.36078432, alpha 1.0, and chroma 0.28709158.
     * It can be represented as a packed float with the constant {@code -0x1.b92358p125F}.
     * <pre>
     * <font style='background-color: #3B5773;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B5773; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B5773;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B5773'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B5773'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B5773'>&nbsp;@&nbsp;</font><font style='background-color: #3B5773; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B5773;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B5773; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DREARY_BLUE = -0x1.b92358p125F;
    static { NAMED.put("Dreary Blue", -0x1.b92358p125F); LIST.add(-0x1.b92358p125F); }

    /**
     * This color constant "Murk" has RGBA8888 code {@code 0F192DFF}, H 0.70980394, S 0.6117647, L 0.08627451, alpha 1.0, and chroma 0.11341166.
     * It can be represented as a packed float with the constant {@code -0x1.2d396ap125F}.
     * <pre>
     * <font style='background-color: #0F192D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F192D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F192D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0F192D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0F192D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0F192D'>&nbsp;@&nbsp;</font><font style='background-color: #0F192D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F192D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F192D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MURK = -0x1.2d396ap125F;
    static { NAMED.put("Murk", -0x1.2d396ap125F); LIST.add(-0x1.2d396ap125F); }

    /**
     * This color constant "Ninja" has RGBA8888 code {@code 1F1F3BFF}, H 0.7411765, S 0.31764707, L 0.12941177, alpha 1.0, and chroma 0.16205029.
     * It can be represented as a packed float with the constant {@code -0x1.42a37ap125F}.
     * <pre>
     * <font style='background-color: #1F1F3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1F1F3B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1F1F3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1F1F3B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1F1F3B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1F1F3B'>&nbsp;@&nbsp;</font><font style='background-color: #1F1F3B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1F1F3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1F1F3B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float NINJA = -0x1.42a37ap125F;
    static { NAMED.put("Ninja", -0x1.42a37ap125F); LIST.add(-0x1.42a37ap125F); }

    /**
     * This color constant "Watercolor Black" has RGBA8888 code {@code 3B3B57FF}, H 0.7411765, S 0.2, L 0.25882354, alpha 1.0, and chroma 0.20406333.
     * It can be represented as a packed float with the constant {@code -0x1.84677ap125F}.
     * <pre>
     * <font style='background-color: #3B3B57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B3B57; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B3B57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B3B57'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B3B57'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B3B57'>&nbsp;@&nbsp;</font><font style='background-color: #3B3B57; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B3B57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B3B57; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WATERCOLOR_BLACK = -0x1.84677ap125F;
    static { NAMED.put("Watercolor Black", -0x1.84677ap125F); LIST.add(-0x1.84677ap125F); }

    /**
     * This color constant "Iolite" has RGBA8888 code {@code 494973FF}, H 0.7411765, S 0.25490198, L 0.3254902, alpha 1.0, and chroma 0.3270712.
     * It can be represented as a packed float with the constant {@code -0x1.a6837ap125F}.
     * <pre>
     * <font style='background-color: #494973;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #494973; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #494973;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #494973'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #494973'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #494973'>&nbsp;@&nbsp;</font><font style='background-color: #494973; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #494973;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #494973; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float IOLITE = -0x1.a6837ap125F;
    static { NAMED.put("Iolite", -0x1.a6837ap125F); LIST.add(-0x1.a6837ap125F); }

    /**
     * This color constant "Boysenberry" has RGBA8888 code {@code 57578FFF}, H 0.7411765, S 0.34117648, L 0.39215687, alpha 1.0, and chroma 0.45600078.
     * It can be represented as a packed float with the constant {@code -0x1.c8af7ap125F}.
     * <pre>
     * <font style='background-color: #57578F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57578F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57578F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #57578F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #57578F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #57578F'>&nbsp;@&nbsp;</font><font style='background-color: #57578F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57578F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57578F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOYSENBERRY = -0x1.c8af7ap125F;
    static { NAMED.put("Boysenberry", -0x1.c8af7ap125F); LIST.add(-0x1.c8af7ap125F); }

    /**
     * This color constant "Watercolor Gray" has RGBA8888 code {@code 736EAAFF}, H 0.74509805, S 0.39215687, L 0.49019608, alpha 1.0, and chroma 0.48957497.
     * It can be represented as a packed float with the constant {@code -0x1.fac97cp125F}.
     * <pre>
     * <font style='background-color: #736EAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #736EAA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #736EAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #736EAA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #736EAA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #736EAA'>&nbsp;@&nbsp;</font><font style='background-color: #736EAA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #736EAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #736EAA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WATERCOLOR_GRAY = -0x1.fac97cp125F;
    static { NAMED.put("Watercolor Gray", -0x1.fac97cp125F); LIST.add(-0x1.fac97cp125F); }

    /**
     * This color constant "Blue Steel" has RGBA8888 code {@code 7676CAFF}, H 0.7411765, S 0.5921569, L 0.5294118, alpha 1.0, and chroma 0.69907635.
     * It can be represented as a packed float with the constant {@code -0x1.0f2f7ap126F}.
     * <pre>
     * <font style='background-color: #7676CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7676CA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7676CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7676CA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7676CA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7676CA'>&nbsp;@&nbsp;</font><font style='background-color: #7676CA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7676CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7676CA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUE_STEEL = -0x1.0f2f7ap126F;
    static { NAMED.put("Blue Steel", -0x1.0f2f7ap126F); LIST.add(-0x1.0f2f7ap126F); }

    /**
     * This color constant "Twilight Cloud" has RGBA8888 code {@code 8F8FC7FF}, H 0.7411765, S 0.46666667, L 0.6117647, alpha 1.0, and chroma 0.46733847.
     * It can be represented as a packed float with the constant {@code -0x1.38ef7ap126F}.
     * <pre>
     * <font style='background-color: #8F8FC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F8FC7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F8FC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F8FC7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F8FC7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F8FC7'>&nbsp;@&nbsp;</font><font style='background-color: #8F8FC7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F8FC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F8FC7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TWILIGHT_CLOUD = -0x1.38ef7ap126F;
    static { NAMED.put("Twilight Cloud", -0x1.38ef7ap126F); LIST.add(-0x1.38ef7ap126F); }

    /**
     * This color constant "Smog" has RGBA8888 code {@code ABABE3FF}, H 0.7411765, S 0.63529414, L 0.7176471, alpha 1.0, and chroma 0.46429774.
     * It can be represented as a packed float with the constant {@code -0x1.6f457ap126F}.
     * <pre>
     * <font style='background-color: #ABABE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABABE3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABABE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ABABE3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ABABE3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ABABE3'>&nbsp;@&nbsp;</font><font style='background-color: #ABABE3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABABE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABABE3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SMOG = -0x1.6f457ap126F;
    static { NAMED.put("Smog", -0x1.6f457ap126F); LIST.add(-0x1.6f457ap126F); }

    /**
     * This color constant "Tropic Mist" has RGBA8888 code {@code D0DAF8FF}, H 0.70980394, S 0.8039216, L 0.8745098, alpha 1.0, and chroma 0.255987.
     * It can be represented as a packed float with the constant {@code -0x1.bf9b6ap126F}.
     * <pre>
     * <font style='background-color: #D0DAF8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0DAF8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0DAF8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D0DAF8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D0DAF8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D0DAF8'>&nbsp;@&nbsp;</font><font style='background-color: #D0DAF8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0DAF8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0DAF8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TROPIC_MIST = -0x1.bf9b6ap126F;
    static { NAMED.put("Tropic Mist", -0x1.bf9b6ap126F); LIST.add(-0x1.bf9b6ap126F); }

    /**
     * This color constant "Feather Down" has RGBA8888 code {@code E3E3FFFF}, H 0.7411765, S 1.0, L 0.9098039, alpha 1.0, and chroma 0.22397952.
     * It can be represented as a packed float with the constant {@code -0x1.d1ff7ap126F}.
     * <pre>
     * <font style='background-color: #E3E3FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3E3FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3E3FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E3E3FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E3E3FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E3E3FF'>&nbsp;@&nbsp;</font><font style='background-color: #E3E3FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3E3FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3E3FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FEATHER_DOWN = -0x1.d1ff7ap126F;
    static { NAMED.put("Feather Down", -0x1.d1ff7ap126F); LIST.add(-0x1.d1ff7ap126F); }

    /**
     * This color constant "Mild Violet" has RGBA8888 code {@code AB8FC7FF}, H 0.7921569, S 0.43529412, L 0.6392157, alpha 1.0, and chroma 0.43147454.
     * It can be represented as a packed float with the constant {@code -0x1.46df94p126F}.
     * <pre>
     * <font style='background-color: #AB8FC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB8FC7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB8FC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB8FC7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB8FC7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB8FC7'>&nbsp;@&nbsp;</font><font style='background-color: #AB8FC7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB8FC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB8FC7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MILD_VIOLET = -0x1.46df94p126F;
    static { NAMED.put("Mild Violet", -0x1.46df94p126F); LIST.add(-0x1.46df94p126F); }

    /**
     * This color constant "Violet Cushions" has RGBA8888 code {@code 8F57C7FF}, H 0.78431374, S 0.627451, L 0.47843137, alpha 1.0, and chroma 0.8242641.
     * It can be represented as a packed float with the constant {@code -0x1.f5419p125F}.
     * <pre>
     * <font style='background-color: #8F57C7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F57C7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F57C7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F57C7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F57C7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F57C7'>&nbsp;@&nbsp;</font><font style='background-color: #8F57C7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F57C7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F57C7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIOLET_CUSHIONS = -0x1.f5419p125F;
    static { NAMED.put("Violet Cushions", -0x1.f5419p125F); LIST.add(-0x1.f5419p125F); }

    /**
     * This color constant "Dull Violet" has RGBA8888 code {@code 73578FFF}, H 0.7921569, S 0.35686275, L 0.41960785, alpha 1.0, and chroma 0.41680378.
     * It can be represented as a packed float with the constant {@code -0x1.d6b794p125F}.
     * <pre>
     * <font style='background-color: #73578F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73578F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73578F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #73578F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #73578F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #73578F'>&nbsp;@&nbsp;</font><font style='background-color: #73578F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73578F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73578F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_VIOLET = -0x1.d6b794p125F;
    static { NAMED.put("Dull Violet", -0x1.d6b794p125F); LIST.add(-0x1.d6b794p125F); }

    /**
     * This color constant "Royal Violet" has RGBA8888 code {@code 573B73FF}, H 0.7882353, S 0.45882353, L 0.3019608, alpha 1.0, and chroma 0.3933144.
     * It can be represented as a packed float with the constant {@code -0x1.9aeb92p125F}.
     * <pre>
     * <font style='background-color: #573B73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #573B73; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #573B73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #573B73'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #573B73'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #573B73'>&nbsp;@&nbsp;</font><font style='background-color: #573B73; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #573B73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #573B73; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ROYAL_VIOLET = -0x1.9aeb92p125F;
    static { NAMED.put("Royal Violet", -0x1.9aeb92p125F); LIST.add(-0x1.9aeb92p125F); }

    /**
     * This color constant "Eminence" has RGBA8888 code {@code 3C233CFF}, H 0.85490197, S 0.40392157, L 0.1764706, alpha 1.0, and chroma 0.16218781.
     * It can be represented as a packed float with the constant {@code -0x1.5acfb4p125F}.
     * <pre>
     * <font style='background-color: #3C233C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C233C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C233C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3C233C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3C233C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3C233C'>&nbsp;@&nbsp;</font><font style='background-color: #3C233C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C233C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C233C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float EMINENCE = -0x1.5acfb4p125F;
    static { NAMED.put("Eminence", -0x1.5acfb4p125F); LIST.add(-0x1.5acfb4p125F); }

    /**
     * This color constant "Prune" has RGBA8888 code {@code 463246FF}, H 0.85490197, S 0.27058825, L 0.23529412, alpha 1.0, and chroma 0.14486678.
     * It can be represented as a packed float with the constant {@code -0x1.788bb4p125F}.
     * <pre>
     * <font style='background-color: #463246;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #463246; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #463246;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #463246'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #463246'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #463246'>&nbsp;@&nbsp;</font><font style='background-color: #463246; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #463246;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #463246; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PRUNE = -0x1.788bb4p125F;
    static { NAMED.put("Prune", -0x1.788bb4p125F); LIST.add(-0x1.788bb4p125F); }

    /**
     * This color constant "Dusty Grape" has RGBA8888 code {@code 724072FF}, H 0.85490197, S 0.47843137, L 0.34901962, alpha 1.0, and chroma 0.37994286.
     * It can be represented as a packed float with the constant {@code -0x1.b2f5b4p125F}.
     * <pre>
     * <font style='background-color: #724072;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #724072; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #724072;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #724072'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #724072'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #724072'>&nbsp;@&nbsp;</font><font style='background-color: #724072; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #724072;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #724072; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DUSTY_GRAPE = -0x1.b2f5b4p125F;
    static { NAMED.put("Dusty Grape", -0x1.b2f5b4p125F); LIST.add(-0x1.b2f5b4p125F); }

    /**
     * This color constant "Pink Violet" has RGBA8888 code {@code 8F578FFF}, H 0.85490197, S 0.43137255, L 0.4509804, alpha 1.0, and chroma 0.44264853.
     * It can be represented as a packed float with the constant {@code -0x1.e6ddb4p125F}.
     * <pre>
     * <font style='background-color: #8F578F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F578F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F578F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F578F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F578F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F578F'>&nbsp;@&nbsp;</font><font style='background-color: #8F578F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F578F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F578F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINK_VIOLET = -0x1.e6ddb4p125F;
    static { NAMED.put("Pink Violet", -0x1.e6ddb4p125F); LIST.add(-0x1.e6ddb4p125F); }

    /**
     * This color constant "Ripe Plum" has RGBA8888 code {@code AB57ABFF}, H 0.85490197, S 0.5686275, L 0.49803922, alpha 1.0, and chroma 0.64437723.
     * It can be represented as a packed float with the constant {@code -0x1.ff23b4p125F}.
     * <pre>
     * <font style='background-color: #AB57AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB57AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB57AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB57AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB57AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB57AB'>&nbsp;@&nbsp;</font><font style='background-color: #AB57AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB57AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB57AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float RIPE_PLUM = -0x1.ff23b4p125F;
    static { NAMED.put("Ripe Plum", -0x1.ff23b4p125F); LIST.add(-0x1.ff23b4p125F); }

    /**
     * This color constant "Mauve" has RGBA8888 code {@code AB73ABFF}, H 0.85490197, S 0.35686275, L 0.56078434, alpha 1.0, and chroma 0.45535064.
     * It can be represented as a packed float with the constant {@code -0x1.1eb7b4p126F}.
     * <pre>
     * <font style='background-color: #AB73AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB73AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB73AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB73AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB73AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB73AB'>&nbsp;@&nbsp;</font><font style='background-color: #AB73AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB73AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB73AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MAUVE = -0x1.1eb7b4p126F;
    static { NAMED.put("Mauve", -0x1.1eb7b4p126F); LIST.add(-0x1.1eb7b4p126F); }

    /**
     * This color constant "Ham" has RGBA8888 code {@code EBACE1FF}, H 0.8784314, S 0.67058825, L 0.7764706, alpha 1.0, and chroma 0.4608991.
     * It can be represented as a packed float with the constant {@code -0x1.8d57cp126F}.
     * <pre>
     * <font style='background-color: #EBACE1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBACE1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBACE1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EBACE1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EBACE1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EBACE1'>&nbsp;@&nbsp;</font><font style='background-color: #EBACE1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBACE1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBACE1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float HAM = -0x1.8d57cp126F;
    static { NAMED.put("Ham", -0x1.8d57cp126F); LIST.add(-0x1.8d57cp126F); }

    /**
     * This color constant "Cotton Candy" has RGBA8888 code {@code FFDCF5FF}, H 0.89411765, S 1.0, L 0.9137255, alpha 1.0, and chroma 0.2333344.
     * It can be represented as a packed float with the constant {@code -0x1.d3ffc8p126F}.
     * <pre>
     * <font style='background-color: #FFDCF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFDCF5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFDCF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFDCF5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFDCF5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFDCF5'>&nbsp;@&nbsp;</font><font style='background-color: #FFDCF5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFDCF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFDCF5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float COTTON_CANDY = -0x1.d3ffc8p126F;
    static { NAMED.put("Cotton Candy", -0x1.d3ffc8p126F); LIST.add(-0x1.d3ffc8p126F); }

    /**
     * This color constant "Silver Pink" has RGBA8888 code {@code E3C7E3FF}, H 0.85490197, S 0.4, L 0.8352941, alpha 1.0, and chroma 0.22842677.
     * It can be represented as a packed float with the constant {@code -0x1.aacdb4p126F}.
     * <pre>
     * <font style='background-color: #E3C7E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3C7E3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3C7E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E3C7E3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E3C7E3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E3C7E3'>&nbsp;@&nbsp;</font><font style='background-color: #E3C7E3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3C7E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3C7E3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_PINK = -0x1.aacdb4p126F;
    static { NAMED.put("Silver Pink", -0x1.aacdb4p126F); LIST.add(-0x1.aacdb4p126F); }

    /**
     * This color constant "Tea Rose" has RGBA8888 code {@code E1B9D2FF}, H 0.9098039, S 0.4627451, L 0.7921569, alpha 1.0, and chroma 0.2603289.
     * It can be represented as a packed float with the constant {@code -0x1.94eddp126F}.
     * <pre>
     * <font style='background-color: #E1B9D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1B9D2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1B9D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E1B9D2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E1B9D2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E1B9D2'>&nbsp;@&nbsp;</font><font style='background-color: #E1B9D2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1B9D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1B9D2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TEA_ROSE = -0x1.94eddp126F;
    static { NAMED.put("Tea Rose", -0x1.94eddp126F); LIST.add(-0x1.94eddp126F); }

    /**
     * This color constant "Old Rose" has RGBA8888 code {@code D7A0BEFF}, H 0.92941177, S 0.45490196, L 0.7176471, alpha 1.0, and chroma 0.3465665.
     * It can be represented as a packed float with the constant {@code -0x1.6ee9dap126F}.
     * <pre>
     * <font style='background-color: #D7A0BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7A0BE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7A0BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D7A0BE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D7A0BE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D7A0BE'>&nbsp;@&nbsp;</font><font style='background-color: #D7A0BE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7A0BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7A0BE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float OLD_ROSE = -0x1.6ee9dap126F;
    static { NAMED.put("Old Rose", -0x1.6ee9dap126F); LIST.add(-0x1.6ee9dap126F); }

    /**
     * This color constant "Dusty Pink" has RGBA8888 code {@code C78FB9FF}, H 0.8901961, S 0.3764706, L 0.65882355, alpha 1.0, and chroma 0.39092672.
     * It can be represented as a packed float with the constant {@code -0x1.50c1c6p126F}.
     * <pre>
     * <font style='background-color: #C78FB9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C78FB9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C78FB9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C78FB9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C78FB9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C78FB9'>&nbsp;@&nbsp;</font><font style='background-color: #C78FB9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C78FB9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C78FB9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DUSTY_PINK = -0x1.50c1c6p126F;
    static { NAMED.put("Dusty Pink", -0x1.50c1c6p126F); LIST.add(-0x1.50c1c6p126F); }

    /**
     * This color constant "Roseate Spoonbill" has RGBA8888 code {@code C87DA0FF}, H 0.9490196, S 0.42352942, L 0.6117647, alpha 1.0, and chroma 0.46983334.
     * It can be represented as a packed float with the constant {@code -0x1.38d9e4p126F}.
     * <pre>
     * <font style='background-color: #C87DA0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C87DA0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C87DA0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C87DA0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C87DA0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C87DA0'>&nbsp;@&nbsp;</font><font style='background-color: #C87DA0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C87DA0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C87DA0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ROSEATE_SPOONBILL = -0x1.38d9e4p126F;
    static { NAMED.put("Roseate Spoonbill", -0x1.38d9e4p126F); LIST.add(-0x1.38d9e4p126F); }

    /**
     * This color constant "Thulian Pink" has RGBA8888 code {@code C35A91FF}, H 0.94509804, S 0.54509807, L 0.5294118, alpha 1.0, and chroma 0.6641678.
     * It can be represented as a packed float with the constant {@code -0x1.0f17e2p126F}.
     * <pre>
     * <font style='background-color: #C35A91;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C35A91; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C35A91;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C35A91'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C35A91'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C35A91'>&nbsp;@&nbsp;</font><font style='background-color: #C35A91; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C35A91;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C35A91; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float THULIAN_PINK = -0x1.0f17e2p126F;
    static { NAMED.put("Thulian Pink", -0x1.0f17e2p126F); LIST.add(-0x1.0f17e2p126F); }

    /**
     * This color constant "Brown Velvet" has RGBA8888 code {@code 4B2837FF}, H 0.9607843, S 0.38039216, L 0.21176471, alpha 1.0, and chroma 0.19226596.
     * It can be represented as a packed float with the constant {@code -0x1.6cc3eap125F}.
     * <pre>
     * <font style='background-color: #4B2837;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B2837; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B2837;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4B2837'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4B2837'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4B2837'>&nbsp;@&nbsp;</font><font style='background-color: #4B2837; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B2837;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B2837; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BROWN_VELVET = -0x1.6cc3eap125F;
    static { NAMED.put("Brown Velvet", -0x1.6cc3eap125F); LIST.add(-0x1.6cc3eap125F); }

    /**
     * This color constant "Nightshade" has RGBA8888 code {@code 321623FF}, H 0.95686275, S 0.44313726, L 0.11764706, alpha 1.0, and chroma 0.12317952.
     * It can be represented as a packed float with the constant {@code -0x1.3ce3e8p125F}.
     * <pre>
     * <font style='background-color: #321623;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #321623; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #321623;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #321623'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #321623'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #321623'>&nbsp;@&nbsp;</font><font style='background-color: #321623; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #321623;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #321623; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float NIGHTSHADE = -0x1.3ce3e8p125F;
    static { NAMED.put("Nightshade", -0x1.3ce3e8p125F); LIST.add(-0x1.3ce3e8p125F); }

    /**
     * This color constant "Scribe Ink" has RGBA8888 code {@code 280A1EFF}, H 0.91764706, S 0.6509804, L 0.06666667, alpha 1.0, and chroma 0.0961166.
     * It can be represented as a packed float with the constant {@code -0x1.234dd4p125F}.
     * <pre>
     * <font style='background-color: #280A1E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #280A1E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #280A1E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #280A1E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #280A1E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #280A1E'>&nbsp;@&nbsp;</font><font style='background-color: #280A1E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #280A1E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #280A1E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SCRIBE_INK = -0x1.234dd4p125F;
    static { NAMED.put("Scribe Ink", -0x1.234dd4p125F); LIST.add(-0x1.234dd4p125F); }

    /**
     * This color constant "Varnish" has RGBA8888 code {@code 401811FF}, H 0.047058824, S 0.65882355, L 0.14117648, alpha 1.0, and chroma 0.25357264.
     * It can be represented as a packed float with the constant {@code -0x1.495018p125F}.
     * <pre>
     * <font style='background-color: #401811;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #401811; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #401811;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #401811'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #401811'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #401811'>&nbsp;@&nbsp;</font><font style='background-color: #401811; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #401811;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #401811; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VARNISH = -0x1.495018p125F;
    static { NAMED.put("Varnish", -0x1.495018p125F); LIST.add(-0x1.495018p125F); }

    /**
     * This color constant "Cedar Wood" has RGBA8888 code {@code 621800FF}, H 0.047058824, S 1.0, L 0.20784314, alpha 1.0, and chroma 0.56663924.
     * It can be represented as a packed float with the constant {@code -0x1.6bfe18p125F}.
     * <pre>
     * <font style='background-color: #621800;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #621800; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #621800;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #621800'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #621800'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #621800'>&nbsp;@&nbsp;</font><font style='background-color: #621800; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #621800;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #621800; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CEDAR_WOOD = -0x1.6bfe18p125F;
    static { NAMED.put("Cedar Wood", -0x1.6bfe18p125F); LIST.add(-0x1.6bfe18p125F); }

    /**
     * This color constant "Hot Sauce" has RGBA8888 code {@code A5140AFF}, H 0.03529412, S 0.9607843, L 0.34901962, alpha 1.0, and chroma 1.1019524.
     * It can be represented as a packed float with the constant {@code -0x1.b3ea12p125F}.
     * <pre>
     * <font style='background-color: #A5140A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A5140A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A5140A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A5140A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A5140A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A5140A'>&nbsp;@&nbsp;</font><font style='background-color: #A5140A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A5140A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A5140A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float HOT_SAUCE = -0x1.b3ea12p125F;
    static { NAMED.put("Hot Sauce", -0x1.b3ea12p125F); LIST.add(-0x1.b3ea12p125F); }

    /**
     * This color constant "Lurid Red" has RGBA8888 code {@code DA2010FF}, H 0.03529412, S 0.9607843, L 0.47058824, alpha 1.0, and chroma 1.4857786.
     * It can be represented as a packed float with the constant {@code -0x1.f1ea12p125F}.
     * <pre>
     * <font style='background-color: #DA2010;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA2010; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA2010;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DA2010'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DA2010'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DA2010'>&nbsp;@&nbsp;</font><font style='background-color: #DA2010; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA2010;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA2010; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LURID_RED = -0x1.f1ea12p125F;
    static { NAMED.put("Lurid Red", -0x1.f1ea12p125F); LIST.add(-0x1.f1ea12p125F); }

    /**
     * This color constant "Brick" has RGBA8888 code {@code D5524AFF}, H 0.039215688, S 0.6431373, L 0.5254902, alpha 1.0, and chroma 1.0388439.
     * It can be represented as a packed float with the constant {@code -0x1.0d4814p126F}.
     * <pre>
     * <font style='background-color: #D5524A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5524A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5524A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D5524A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D5524A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D5524A'>&nbsp;@&nbsp;</font><font style='background-color: #D5524A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5524A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5524A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRICK = -0x1.0d4814p126F;
    static { NAMED.put("Brick", -0x1.0d4814p126F); LIST.add(-0x1.0d4814p126F); }

    /**
     * This color constant "Bright Red" has RGBA8888 code {@code FF3C0AFF}, H 0.039215688, S 1.0, L 0.5647059, alpha 1.0, and chroma 1.5982393.
     * It can be represented as a packed float with the constant {@code -0x1.21fe14p126F}.
     * <pre>
     * <font style='background-color: #FF3C0A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF3C0A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF3C0A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF3C0A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF3C0A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF3C0A'>&nbsp;@&nbsp;</font><font style='background-color: #FF3C0A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF3C0A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF3C0A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED = -0x1.21fe14p126F;
    static { NAMED.put("Bright Red", -0x1.21fe14p126F); LIST.add(-0x1.21fe14p126F); }

    /**
     * This color constant "Embers" has RGBA8888 code {@code F55A32FF}, H 0.050980393, S 0.87058824, L 0.5882353, alpha 1.0, and chroma 1.3225114.
     * It can be represented as a packed float with the constant {@code -0x1.2dbc1ap126F}.
     * <pre>
     * <font style='background-color: #F55A32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F55A32; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F55A32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F55A32'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F55A32'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F55A32'>&nbsp;@&nbsp;</font><font style='background-color: #F55A32; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F55A32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F55A32; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float EMBERS = -0x1.2dbc1ap126F;
    static { NAMED.put("Embers", -0x1.2dbc1ap126F); LIST.add(-0x1.2dbc1ap126F); }

    /**
     * This color constant "Salmon" has RGBA8888 code {@code FF6262FF}, H 0.03137255, S 1.0, L 0.62352943, alpha 1.0, and chroma 1.2052094.
     * It can be represented as a packed float with the constant {@code -0x1.3ffe1p126F}.
     * <pre>
     * <font style='background-color: #FF6262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6262; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF6262'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF6262'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF6262'>&nbsp;@&nbsp;</font><font style='background-color: #FF6262; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6262; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SALMON = -0x1.3ffe1p126F;
    static { NAMED.put("Salmon", -0x1.3ffe1p126F); LIST.add(-0x1.3ffe1p126F); }

    /**
     * This color constant "Taxicab Yellow" has RGBA8888 code {@code F6BD31FF}, H 0.16078432, S 0.9372549, L 0.8, alpha 1.0, and chroma 0.91160136.
     * It can be represented as a packed float with the constant {@code -0x1.99de52p126F}.
     * <pre>
     * <font style='background-color: #F6BD31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6BD31; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6BD31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F6BD31'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F6BD31'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F6BD31'>&nbsp;@&nbsp;</font><font style='background-color: #F6BD31; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6BD31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6BD31; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TAXICAB_YELLOW = -0x1.99de52p126F;
    static { NAMED.put("Taxicab Yellow", -0x1.99de52p126F); LIST.add(-0x1.99de52p126F); }

    /**
     * This color constant "Apricot" has RGBA8888 code {@code FFA53CFF}, H 0.11372549, S 1.0, L 0.7529412, alpha 1.0, and chroma 0.9494338.
     * It can be represented as a packed float with the constant {@code -0x1.81fe3ap126F}.
     * <pre>
     * <font style='background-color: #FFA53C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA53C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA53C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFA53C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFA53C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFA53C'>&nbsp;@&nbsp;</font><font style='background-color: #FFA53C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA53C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA53C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float APRICOT = -0x1.81fe3ap126F;
    static { NAMED.put("Apricot", -0x1.81fe3ap126F); LIST.add(-0x1.81fe3ap126F); }

    /**
     * This color constant "Burnt Yellow" has RGBA8888 code {@code D79B0FFF}, H 0.14901961, S 0.9882353, L 0.6784314, alpha 1.0, and chroma 0.846731.
     * It can be represented as a packed float with the constant {@code -0x1.5bf84cp126F}.
     * <pre>
     * <font style='background-color: #D79B0F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D79B0F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D79B0F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D79B0F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D79B0F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D79B0F'>&nbsp;@&nbsp;</font><font style='background-color: #D79B0F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D79B0F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D79B0F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BURNT_YELLOW = -0x1.5bf84cp126F;
    static { NAMED.put("Burnt Yellow", -0x1.5bf84cp126F); LIST.add(-0x1.5bf84cp126F); }

    /**
     * This color constant "Dry Pepper" has RGBA8888 code {@code DA6E0AFF}, H 0.08235294, S 0.9882353, L 0.5803922, alpha 1.0, and chroma 1.0595987.
     * It can be represented as a packed float with the constant {@code -0x1.29f82ap126F}.
     * <pre>
     * <font style='background-color: #DA6E0A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA6E0A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA6E0A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DA6E0A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DA6E0A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DA6E0A'>&nbsp;@&nbsp;</font><font style='background-color: #DA6E0A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA6E0A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA6E0A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRY_PEPPER = -0x1.29f82ap126F;
    static { NAMED.put("Dry Pepper", -0x1.29f82ap126F); LIST.add(-0x1.29f82ap126F); }

    /**
     * This color constant "Redwood" has RGBA8888 code {@code B45A00FF}, H 0.08235294, S 1.0, L 0.48235294, alpha 1.0, and chroma 0.8910959.
     * It can be represented as a packed float with the constant {@code -0x1.f7fe2ap125F}.
     * <pre>
     * <font style='background-color: #B45A00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B45A00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B45A00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B45A00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B45A00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B45A00'>&nbsp;@&nbsp;</font><font style='background-color: #B45A00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B45A00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B45A00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float REDWOOD = -0x1.f7fe2ap125F;
    static { NAMED.put("Redwood", -0x1.f7fe2ap125F); LIST.add(-0x1.f7fe2ap125F); }

    /**
     * This color constant "Koa" has RGBA8888 code {@code A04B05FF}, H 0.078431375, S 0.9882353, L 0.41960785, alpha 1.0, and chroma 0.7927564.
     * It can be represented as a packed float with the constant {@code -0x1.d7f828p125F}.
     * <pre>
     * <font style='background-color: #A04B05;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A04B05; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A04B05;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A04B05'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A04B05'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A04B05'>&nbsp;@&nbsp;</font><font style='background-color: #A04B05; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A04B05;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A04B05; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float KOA = -0x1.d7f828p125F;
    static { NAMED.put("Koa", -0x1.d7f828p125F); LIST.add(-0x1.d7f828p125F); }

    /**
     * This color constant "Ochre" has RGBA8888 code {@code 5F3214FF}, H 0.08627451, S 0.8352941, L 0.25882354, alpha 1.0, and chroma 0.38661358.
     * It can be represented as a packed float with the constant {@code -0x1.85aa2cp125F}.
     * <pre>
     * <font style='background-color: #5F3214;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5F3214; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5F3214;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5F3214'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5F3214'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5F3214'>&nbsp;@&nbsp;</font><font style='background-color: #5F3214; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5F3214;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5F3214; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float OCHRE = -0x1.85aa2cp125F;
    static { NAMED.put("Ochre", -0x1.85aa2cp125F); LIST.add(-0x1.85aa2cp125F); }

    /**
     * This color constant "Dull Green" has RGBA8888 code {@code 53500AFF}, H 0.22745098, S 0.95686275, L 0.32941177, alpha 1.0, and chroma 0.34715477.
     * It can be represented as a packed float with the constant {@code -0x1.a9e874p125F}.
     * <pre>
     * <font style='background-color: #53500A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #53500A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #53500A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #53500A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #53500A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #53500A'>&nbsp;@&nbsp;</font><font style='background-color: #53500A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #53500A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #53500A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_GREEN = -0x1.a9e874p125F;
    static { NAMED.put("Dull Green", -0x1.a9e874p125F); LIST.add(-0x1.a9e874p125F); }

    /**
     * This color constant "Army Green" has RGBA8888 code {@code 626200FF}, H 0.23921569, S 1.0, L 0.4, alpha 1.0, and chroma 0.44108546.
     * It can be represented as a packed float with the constant {@code -0x1.cdfe7ap125F}.
     * <pre>
     * <font style='background-color: #626200;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #626200; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #626200;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #626200'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #626200'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #626200'>&nbsp;@&nbsp;</font><font style='background-color: #626200; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #626200;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #626200; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ARMY_GREEN = -0x1.cdfe7ap125F;
    static { NAMED.put("Army Green", -0x1.cdfe7ap125F); LIST.add(-0x1.cdfe7ap125F); }

    /**
     * This color constant "Driftwood" has RGBA8888 code {@code 8C805AFF}, H 0.19607843, S 0.49019608, L 0.5372549, alpha 1.0, and chroma 0.29691854.
     * It can be represented as a packed float with the constant {@code -0x1.12fa64p126F}.
     * <pre>
     * <font style='background-color: #8C805A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C805A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C805A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8C805A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8C805A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8C805A'>&nbsp;@&nbsp;</font><font style='background-color: #8C805A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C805A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C805A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRIFTWOOD = -0x1.12fa64p126F;
    static { NAMED.put("Driftwood", -0x1.12fa64p126F); LIST.add(-0x1.12fa64p126F); }

    /**
     * This color constant "Dry Brush" has RGBA8888 code {@code AC9400FF}, H 0.19607843, S 1.0, L 0.6156863, alpha 1.0, and chroma 0.6941392.
     * It can be represented as a packed float with the constant {@code -0x1.3bfe64p126F}.
     * <pre>
     * <font style='background-color: #AC9400;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC9400; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC9400;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AC9400'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AC9400'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AC9400'>&nbsp;@&nbsp;</font><font style='background-color: #AC9400; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC9400;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC9400; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRY_BRUSH = -0x1.3bfe64p126F;
    static { NAMED.put("Dry Brush", -0x1.3bfe64p126F); LIST.add(-0x1.3bfe64p126F); }

    /**
     * This color constant "Mush" has RGBA8888 code {@code B1B10AFF}, H 0.23921569, S 0.99215686, L 0.7019608, alpha 1.0, and chroma 0.76799077.
     * It can be represented as a packed float with the constant {@code -0x1.67fa7ap126F}.
     * <pre>
     * <font style='background-color: #B1B10A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1B10A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1B10A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B1B10A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B1B10A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B1B10A'>&nbsp;@&nbsp;</font><font style='background-color: #B1B10A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1B10A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1B10A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MUSH = -0x1.67fa7ap126F;
    static { NAMED.put("Mush", -0x1.67fa7ap126F); LIST.add(-0x1.67fa7ap126F); }

    /**
     * This color constant "Banana Pudding" has RGBA8888 code {@code E6D55AFF}, H 0.21176471, S 0.8235294, L 0.84705883, alpha 1.0, and chroma 0.77353.
     * It can be represented as a packed float with the constant {@code -0x1.b1a46cp126F}.
     * <pre>
     * <font style='background-color: #E6D55A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E6D55A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E6D55A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E6D55A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E6D55A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E6D55A'>&nbsp;@&nbsp;</font><font style='background-color: #E6D55A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E6D55A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E6D55A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BANANA_PUDDING = -0x1.b1a46cp126F;
    static { NAMED.put("Banana Pudding", -0x1.b1a46cp126F); LIST.add(-0x1.b1a46cp126F); }

    /**
     * This color constant "Saffron" has RGBA8888 code {@code FFD510FF}, H 0.1882353, S 1.0, L 0.8666667, alpha 1.0, and chroma 0.9549788.
     * It can be represented as a packed float with the constant {@code -0x1.bbfe6p126F}.
     * <pre>
     * <font style='background-color: #FFD510;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD510; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD510;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFD510'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFD510'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFD510'>&nbsp;@&nbsp;</font><font style='background-color: #FFD510; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD510;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD510; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SAFFRON = -0x1.bbfe6p126F;
    static { NAMED.put("Saffron", -0x1.bbfe6p126F); LIST.add(-0x1.bbfe6p126F); }

    /**
     * This color constant "Pencil Yellow" has RGBA8888 code {@code FFEA4AFF}, H 0.21176471, S 0.99607843, L 0.92156863, alpha 1.0, and chroma 0.8456948.
     * It can be represented as a packed float with the constant {@code -0x1.d7fc6cp126F}.
     * <pre>
     * <font style='background-color: #FFEA4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFEA4A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFEA4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFEA4A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFEA4A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFEA4A'>&nbsp;@&nbsp;</font><font style='background-color: #FFEA4A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFEA4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFEA4A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PENCIL_YELLOW = -0x1.d7fc6cp126F;
    static { NAMED.put("Pencil Yellow", -0x1.d7fc6cp126F); LIST.add(-0x1.d7fc6cp126F); }

    /**
     * This color constant "Chartreuse" has RGBA8888 code {@code C8FF41FF}, H 0.2901961, S 1.0, L 0.9372549, alpha 1.0, and chroma 1.0136933.
     * It can be represented as a packed float with the constant {@code -0x1.dffe94p126F}.
     * <pre>
     * <font style='background-color: #C8FF41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8FF41; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8FF41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C8FF41'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C8FF41'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C8FF41'>&nbsp;@&nbsp;</font><font style='background-color: #C8FF41; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8FF41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8FF41; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CHARTREUSE = -0x1.dffe94p126F;
    static { NAMED.put("Chartreuse", -0x1.dffe94p126F); LIST.add(-0x1.dffe94p126F); }

    /**
     * This color constant "Absinthe" has RGBA8888 code {@code 9BF046FF}, H 0.31764707, S 0.8980392, L 0.87058824, alpha 1.0, and chroma 1.0067054.
     * It can be represented as a packed float with the constant {@code -0x1.bdcaa2p126F}.
     * <pre>
     * <font style='background-color: #9BF046;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9BF046; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9BF046;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9BF046'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9BF046'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9BF046'>&nbsp;@&nbsp;</font><font style='background-color: #9BF046; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9BF046;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9BF046; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ABSINTHE = -0x1.bdcaa2p126F;
    static { NAMED.put("Absinthe", -0x1.bdcaa2p126F); LIST.add(-0x1.bdcaa2p126F); }

    /**
     * This color constant "Infection" has RGBA8888 code {@code 96DC19FF}, H 0.30588236, S 0.9843137, L 0.80784315, alpha 1.0, and chroma 0.9826044.
     * It can be represented as a packed float with the constant {@code -0x1.9df69cp126F}.
     * <pre>
     * <font style='background-color: #96DC19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #96DC19; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #96DC19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #96DC19'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #96DC19'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #96DC19'>&nbsp;@&nbsp;</font><font style='background-color: #96DC19; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #96DC19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #96DC19; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float INFECTION = -0x1.9df69cp126F;
    static { NAMED.put("Infection", -0x1.9df69cp126F); LIST.add(-0x1.9df69cp126F); }

    /**
     * This color constant "Frog Green" has RGBA8888 code {@code 73C805FF}, H 0.32156864, S 1.0, L 0.7294118, alpha 1.0, and chroma 0.95374906.
     * It can be represented as a packed float with the constant {@code -0x1.75fea4p126F}.
     * <pre>
     * <font style='background-color: #73C805;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73C805; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73C805;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #73C805'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #73C805'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #73C805'>&nbsp;@&nbsp;</font><font style='background-color: #73C805; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73C805;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73C805; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FROG_GREEN = -0x1.75fea4p126F;
    static { NAMED.put("Frog Green", -0x1.75fea4p126F); LIST.add(-0x1.75fea4p126F); }

    /**
     * This color constant "Avocado" has RGBA8888 code {@code 6AA805FF}, H 0.3137255, S 0.99607843, L 0.627451, alpha 1.0, and chroma 0.7931614.
     * It can be represented as a packed float with the constant {@code -0x1.41fcap126F}.
     * <pre>
     * <font style='background-color: #6AA805;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6AA805; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6AA805;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6AA805'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6AA805'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6AA805'>&nbsp;@&nbsp;</font><font style='background-color: #6AA805; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6AA805;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6AA805; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float AVOCADO = -0x1.41fcap126F;
    static { NAMED.put("Avocado", -0x1.41fcap126F); LIST.add(-0x1.41fcap126F); }

    /**
     * This color constant "Woodlands" has RGBA8888 code {@code 3C6E14FF}, H 0.3254902, S 0.93333334, L 0.41568628, alpha 1.0, and chroma 0.51558876.
     * It can be represented as a packed float with the constant {@code -0x1.d5dca6p125F}.
     * <pre>
     * <font style='background-color: #3C6E14;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C6E14; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C6E14;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3C6E14'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3C6E14'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3C6E14'>&nbsp;@&nbsp;</font><font style='background-color: #3C6E14; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C6E14;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C6E14; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WOODLANDS = -0x1.d5dca6p125F;
    static { NAMED.put("Woodlands", -0x1.d5dca6p125F); LIST.add(-0x1.d5dca6p125F); }

    /**
     * This color constant "Dark Pine" has RGBA8888 code {@code 283405FF}, H 0.28627452, S 0.9411765, L 0.19607843, alpha 1.0, and chroma 0.21616024.
     * It can be represented as a packed float with the constant {@code -0x1.65e092p125F}.
     * <pre>
     * <font style='background-color: #283405;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #283405; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #283405;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #283405'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #283405'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #283405'>&nbsp;@&nbsp;</font><font style='background-color: #283405; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #283405;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #283405; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_PINE = -0x1.65e092p125F;
    static { NAMED.put("Dark Pine", -0x1.65e092p125F); LIST.add(-0x1.65e092p125F); }

    /**
     * This color constant "Moss Green" has RGBA8888 code {@code 204608FF}, H 0.33333334, S 0.9411765, L 0.25882354, alpha 1.0, and chroma 0.33529222.
     * It can be represented as a packed float with the constant {@code -0x1.85e0aap125F}.
     * <pre>
     * <font style='background-color: #204608;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #204608; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #204608;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #204608'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #204608'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #204608'>&nbsp;@&nbsp;</font><font style='background-color: #204608; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #204608;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #204608; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MOSS_GREEN = -0x1.85e0aap125F;
    static { NAMED.put("Moss Green", -0x1.85e0aap125F); LIST.add(-0x1.85e0aap125F); }

    /**
     * This color constant "Fern Green" has RGBA8888 code {@code 0C5C0CFF}, H 0.3529412, S 0.94509804, L 0.33333334, alpha 1.0, and chroma 0.48169246.
     * It can be represented as a packed float with the constant {@code -0x1.abe2b4p125F}.
     * <pre>
     * <font style='background-color: #0C5C0C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0C5C0C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0C5C0C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0C5C0C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0C5C0C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0C5C0C'>&nbsp;@&nbsp;</font><font style='background-color: #0C5C0C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0C5C0C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0C5C0C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FERN_GREEN = -0x1.abe2b4p125F;
    static { NAMED.put("Fern Green", -0x1.abe2b4p125F); LIST.add(-0x1.abe2b4p125F); }

    /**
     * This color constant "Forest Glen" has RGBA8888 code {@code 149605FF}, H 0.3529412, S 0.99607843, L 0.5411765, alpha 1.0, and chroma 0.82422674.
     * It can be represented as a packed float with the constant {@code -0x1.15fcb4p126F}.
     * <pre>
     * <font style='background-color: #149605;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #149605; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #149605;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #149605'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #149605'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #149605'>&nbsp;@&nbsp;</font><font style='background-color: #149605; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #149605;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #149605; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FOREST_GLEN = -0x1.15fcb4p126F;
    static { NAMED.put("Forest Glen", -0x1.15fcb4p126F); LIST.add(-0x1.15fcb4p126F); }

    /**
     * This color constant "Malachite" has RGBA8888 code {@code 0AD70AFF}, H 0.3529412, S 0.99607843, L 0.7529412, alpha 1.0, and chroma 1.1467502.
     * It can be represented as a packed float with the constant {@code -0x1.81fcb4p126F}.
     * <pre>
     * <font style='background-color: #0AD70A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0AD70A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0AD70A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0AD70A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0AD70A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0AD70A'>&nbsp;@&nbsp;</font><font style='background-color: #0AD70A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0AD70A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0AD70A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MALACHITE = -0x1.81fcb4p126F;
    static { NAMED.put("Malachite", -0x1.81fcb4p126F); LIST.add(-0x1.81fcb4p126F); }

    /**
     * This color constant "Apple Green" has RGBA8888 code {@code 14E60AFF}, H 0.3529412, S 0.99607843, L 0.8, alpha 1.0, and chroma 1.218422.
     * It can be represented as a packed float with the constant {@code -0x1.99fcb4p126F}.
     * <pre>
     * <font style='background-color: #14E60A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #14E60A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #14E60A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #14E60A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #14E60A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #14E60A'>&nbsp;@&nbsp;</font><font style='background-color: #14E60A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #14E60A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #14E60A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float APPLE_GREEN = -0x1.99fcb4p126F;
    static { NAMED.put("Apple Green", -0x1.99fcb4p126F); LIST.add(-0x1.99fcb4p126F); }

    /**
     * This color constant "Celery" has RGBA8888 code {@code 7DFF73FF}, H 0.34901962, S 1.0, L 0.90588236, alpha 1.0, and chroma 0.99702615.
     * It can be represented as a packed float with the constant {@code -0x1.cffeb2p126F}.
     * <pre>
     * <font style='background-color: #7DFF73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7DFF73; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7DFF73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7DFF73'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7DFF73'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7DFF73'>&nbsp;@&nbsp;</font><font style='background-color: #7DFF73; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7DFF73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7DFF73; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CELERY = -0x1.cffeb2p126F;
    static { NAMED.put("Celery", -0x1.cffeb2p126F); LIST.add(-0x1.cffeb2p126F); }

    /**
     * This color constant "Mint Green" has RGBA8888 code {@code 4BF05AFF}, H 0.35686275, S 0.8745098, L 0.84313726, alpha 1.0, and chroma 1.1155387.
     * It can be represented as a packed float with the constant {@code -0x1.afbeb6p126F}.
     * <pre>
     * <font style='background-color: #4BF05A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4BF05A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4BF05A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4BF05A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4BF05A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4BF05A'>&nbsp;@&nbsp;</font><font style='background-color: #4BF05A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4BF05A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4BF05A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MINT_GREEN = -0x1.afbeb6p126F;
    static { NAMED.put("Mint Green", -0x1.afbeb6p126F); LIST.add(-0x1.afbeb6p126F); }

    /**
     * This color constant "Emerald" has RGBA8888 code {@code 00C514FF}, H 0.35686275, S 1.0, L 0.69411767, alpha 1.0, and chroma 1.0501581.
     * It can be represented as a packed float with the constant {@code -0x1.63feb6p126F}.
     * <pre>
     * <font style='background-color: #00C514;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00C514; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00C514;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00C514'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00C514'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00C514'>&nbsp;@&nbsp;</font><font style='background-color: #00C514; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00C514;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00C514; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float EMERALD = -0x1.63feb6p126F;
    static { NAMED.put("Emerald", -0x1.63feb6p126F); LIST.add(-0x1.63feb6p126F); }

    /**
     * This color constant "Prase" has RGBA8888 code {@code 05B450FF}, H 0.37254903, S 0.99607843, L 0.6431373, alpha 1.0, and chroma 0.8378182.
     * It can be represented as a packed float with the constant {@code -0x1.49fcbep126F}.
     * <pre>
     * <font style='background-color: #05B450;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #05B450; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #05B450;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #05B450'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #05B450'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #05B450'>&nbsp;@&nbsp;</font><font style='background-color: #05B450; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #05B450;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #05B450; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PRASE = -0x1.49fcbep126F;
    static { NAMED.put("Prase", -0x1.49fcbep126F); LIST.add(-0x1.49fcbep126F); }

    /**
     * This color constant "Eucalyptus" has RGBA8888 code {@code 1C8C4EFF}, H 0.38431373, S 0.9372549, L 0.5137255, alpha 1.0, and chroma 0.57493854.
     * It can be represented as a packed float with the constant {@code -0x1.07dec4p126F}.
     * <pre>
     * <font style='background-color: #1C8C4E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1C8C4E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1C8C4E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1C8C4E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1C8C4E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1C8C4E'>&nbsp;@&nbsp;</font><font style='background-color: #1C8C4E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1C8C4E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1C8C4E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float EUCALYPTUS = -0x1.07dec4p126F;
    static { NAMED.put("Eucalyptus", -0x1.07dec4p126F); LIST.add(-0x1.07dec4p126F); }

    /**
     * This color constant "Zucchini" has RGBA8888 code {@code 123832FF}, H 0.48235294, S 0.80784315, L 0.20784314, alpha 1.0, and chroma 0.13603386.
     * It can be represented as a packed float with the constant {@code -0x1.6b9cf6p125F}.
     * <pre>
     * <font style='background-color: #123832;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #123832; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #123832;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #123832'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #123832'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #123832'>&nbsp;@&nbsp;</font><font style='background-color: #123832; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #123832;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #123832; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ZUCCHINI = -0x1.6b9cf6p125F;
    static { NAMED.put("Zucchini", -0x1.6b9cf6p125F); LIST.add(-0x1.6b9cf6p125F); }

    /**
     * This color constant "Soft Teal" has RGBA8888 code {@code 129880FF}, H 0.4627451, S 0.9764706, L 0.56078434, alpha 1.0, and chroma 0.46061182.
     * It can be represented as a packed float with the constant {@code -0x1.1ff2ecp126F}.
     * <pre>
     * <font style='background-color: #129880;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #129880; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #129880;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #129880'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #129880'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #129880'>&nbsp;@&nbsp;</font><font style='background-color: #129880; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #129880;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #129880; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOFT_TEAL = -0x1.1ff2ecp126F;
    static { NAMED.put("Soft Teal", -0x1.1ff2ecp126F); LIST.add(-0x1.1ff2ecp126F); }

    /**
     * This color constant "Medium Teal" has RGBA8888 code {@code 06C491FF}, H 0.42745098, S 0.99607843, L 0.7058824, alpha 1.0, and chroma 0.661916.
     * It can be represented as a packed float with the constant {@code -0x1.69fcdap126F}.
     * <pre>
     * <font style='background-color: #06C491;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #06C491; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #06C491;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #06C491'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #06C491'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #06C491'>&nbsp;@&nbsp;</font><font style='background-color: #06C491; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #06C491;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #06C491; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MEDIUM_TEAL = -0x1.69fcdap126F;
    static { NAMED.put("Medium Teal", -0x1.69fcdap126F); LIST.add(-0x1.69fcdap126F); }

    /**
     * This color constant "Spring Green" has RGBA8888 code {@code 00DE6AFF}, H 0.3764706, S 1.0, L 0.78039217, alpha 1.0, and chroma 0.98859453.
     * It can be represented as a packed float with the constant {@code -0x1.8ffecp126F}.
     * <pre>
     * <font style='background-color: #00DE6A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00DE6A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00DE6A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00DE6A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00DE6A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00DE6A'>&nbsp;@&nbsp;</font><font style='background-color: #00DE6A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00DE6A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00DE6A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SPRING_GREEN = -0x1.8ffecp126F;
    static { NAMED.put("Spring Green", -0x1.8ffecp126F); LIST.add(-0x1.8ffecp126F); }

    /**
     * This color constant "Turquoise" has RGBA8888 code {@code 2DEBA8FF}, H 0.41568628, S 0.95686275, L 0.8352941, alpha 1.0, and chroma 0.7928545.
     * It can be represented as a packed float with the constant {@code -0x1.abe8d4p126F}.
     * <pre>
     * <font style='background-color: #2DEBA8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2DEBA8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2DEBA8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2DEBA8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2DEBA8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2DEBA8'>&nbsp;@&nbsp;</font><font style='background-color: #2DEBA8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2DEBA8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2DEBA8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TURQUOISE = -0x1.abe8d4p126F;
    static { NAMED.put("Turquoise", -0x1.abe8d4p126F); LIST.add(-0x1.abe8d4p126F); }

    /**
     * This color constant "Seafoam" has RGBA8888 code {@code 3CFEA5FF}, H 0.4, S 0.9647059, L 0.89411765, alpha 1.0, and chroma 0.8783124.
     * It can be represented as a packed float with the constant {@code -0x1.c9ecccp126F}.
     * <pre>
     * <font style='background-color: #3CFEA5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3CFEA5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3CFEA5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3CFEA5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3CFEA5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3CFEA5'>&nbsp;@&nbsp;</font><font style='background-color: #3CFEA5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3CFEA5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3CFEA5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SEAFOAM = -0x1.c9ecccp126F;
    static { NAMED.put("Seafoam", -0x1.c9ecccp126F); LIST.add(-0x1.c9ecccp126F); }

    /**
     * This color constant "Variscite" has RGBA8888 code {@code 6AFFCDFF}, H 0.43529412, S 1.0, L 0.9137255, alpha 1.0, and chroma 0.6616798.
     * It can be represented as a packed float with the constant {@code -0x1.d3fedep126F}.
     * <pre>
     * <font style='background-color: #6AFFCD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6AFFCD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6AFFCD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6AFFCD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6AFFCD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6AFFCD'>&nbsp;@&nbsp;</font><font style='background-color: #6AFFCD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6AFFCD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6AFFCD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VARISCITE = -0x1.d3fedep126F;
    static { NAMED.put("Variscite", -0x1.d3fedep126F); LIST.add(-0x1.d3fedep126F); }

    /**
     * This color constant "Refreshing Mist" has RGBA8888 code {@code 91EBFFFF}, H 0.5882353, S 1.0, L 0.88235295, alpha 1.0, and chroma 0.48525393.
     * It can be represented as a packed float with the constant {@code -0x1.c3ff2cp126F}.
     * <pre>
     * <font style='background-color: #91EBFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #91EBFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #91EBFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #91EBFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #91EBFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #91EBFF'>&nbsp;@&nbsp;</font><font style='background-color: #91EBFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #91EBFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #91EBFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float REFRESHING_MIST = -0x1.c3ff2cp126F;
    static { NAMED.put("Refreshing Mist", -0x1.c3ff2cp126F); LIST.add(-0x1.c3ff2cp126F); }

    /**
     * This color constant "Shining Sky" has RGBA8888 code {@code 55E6FFFF}, H 0.5882353, S 1.0, L 0.8509804, alpha 1.0, and chroma 0.62148577.
     * It can be represented as a packed float with the constant {@code -0x1.b3ff2cp126F}.
     * <pre>
     * <font style='background-color: #55E6FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #55E6FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #55E6FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #55E6FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #55E6FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #55E6FF'>&nbsp;@&nbsp;</font><font style='background-color: #55E6FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #55E6FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #55E6FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SHINING_SKY = -0x1.b3ff2cp126F;
    static { NAMED.put("Shining Sky", -0x1.b3ff2cp126F); LIST.add(-0x1.b3ff2cp126F); }

    /**
     * This color constant "Steam" has RGBA8888 code {@code 7DD7F0FF}, H 0.6039216, S 0.7058824, L 0.8156863, alpha 1.0, and chroma 0.48760962.
     * It can be represented as a packed float with the constant {@code -0x1.a16934p126F}.
     * <pre>
     * <font style='background-color: #7DD7F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7DD7F0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7DD7F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7DD7F0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7DD7F0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7DD7F0'>&nbsp;@&nbsp;</font><font style='background-color: #7DD7F0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7DD7F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7DD7F0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STEAM = -0x1.a16934p126F;
    static { NAMED.put("Steam", -0x1.a16934p126F); LIST.add(-0x1.a16934p126F); }

    /**
     * This color constant "Robin Egg Blue" has RGBA8888 code {@code 08DED5FF}, H 0.5137255, S 0.99607843, L 0.8039216, alpha 1.0, and chroma 0.6315535.
     * It can be represented as a packed float with the constant {@code -0x1.9bfd06p126F}.
     * <pre>
     * <font style='background-color: #08DED5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #08DED5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #08DED5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #08DED5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #08DED5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #08DED5'>&nbsp;@&nbsp;</font><font style='background-color: #08DED5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #08DED5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #08DED5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ROBIN_EGG_BLUE = -0x1.9bfd06p126F;
    static { NAMED.put("Robin Egg Blue", -0x1.9bfd06p126F); LIST.add(-0x1.9bfd06p126F); }

    /**
     * This color constant "Denim Blue" has RGBA8888 code {@code 109CDEFF}, H 0.6666667, S 0.9882353, L 0.60784316, alpha 1.0, and chroma 0.78456575.
     * It can be represented as a packed float with the constant {@code -0x1.37f954p126F}.
     * <pre>
     * <font style='background-color: #109CDE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #109CDE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #109CDE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #109CDE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #109CDE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #109CDE'>&nbsp;@&nbsp;</font><font style='background-color: #109CDE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #109CDE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #109CDE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DENIM_BLUE = -0x1.37f954p126F;
    static { NAMED.put("Denim Blue", -0x1.37f954p126F); LIST.add(-0x1.37f954p126F); }

    /**
     * This color constant "Deep Teal" has RGBA8888 code {@code 055A5CFF}, H 0.54509807, S 0.9843137, L 0.34117648, alpha 1.0, and chroma 0.26801008.
     * It can be represented as a packed float with the constant {@code -0x1.aff716p125F}.
     * <pre>
     * <font style='background-color: #055A5C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #055A5C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #055A5C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #055A5C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #055A5C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #055A5C'>&nbsp;@&nbsp;</font><font style='background-color: #055A5C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #055A5C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #055A5C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_TEAL = -0x1.aff716p125F;
    static { NAMED.put("Deep Teal", -0x1.aff716p125F); LIST.add(-0x1.aff716p125F); }

    /**
     * This color constant "Navy Blue" has RGBA8888 code {@code 162C52FF}, H 0.70980394, S 0.76862746, L 0.18039216, alpha 1.0, and chroma 0.29793686.
     * It can be represented as a packed float with the constant {@code -0x1.5d896ap125F}.
     * <pre>
     * <font style='background-color: #162C52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #162C52; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #162C52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #162C52'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #162C52'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #162C52'>&nbsp;@&nbsp;</font><font style='background-color: #162C52; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #162C52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #162C52; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float NAVY_BLUE = -0x1.5d896ap125F;
    static { NAMED.put("Navy Blue", -0x1.5d896ap125F); LIST.add(-0x1.5d896ap125F); }

    /**
     * This color constant "Blueberry" has RGBA8888 code {@code 0F377DFF}, H 0.7176471, S 0.92941177, L 0.24705882, alpha 1.0, and chroma 0.56473166.
     * It can be represented as a packed float with the constant {@code -0x1.7fdb6ep125F}.
     * <pre>
     * <font style='background-color: #0F377D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F377D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F377D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0F377D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0F377D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0F377D'>&nbsp;@&nbsp;</font><font style='background-color: #0F377D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F377D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F377D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUEBERRY = -0x1.7fdb6ep125F;
    static { NAMED.put("Blueberry", -0x1.7fdb6ep125F); LIST.add(-0x1.7fdb6ep125F); }

    /**
     * This color constant "Prussian Blue" has RGBA8888 code {@code 004A9CFF}, H 0.7137255, S 1.0, L 0.3254902, alpha 1.0, and chroma 0.74634814.
     * It can be represented as a packed float with the constant {@code -0x1.a7ff6cp125F}.
     * <pre>
     * <font style='background-color: #004A9C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #004A9C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #004A9C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #004A9C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #004A9C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #004A9C'>&nbsp;@&nbsp;</font><font style='background-color: #004A9C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #004A9C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #004A9C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PRUSSIAN_BLUE = -0x1.a7ff6cp125F;
    static { NAMED.put("Prussian Blue", -0x1.a7ff6cp125F); LIST.add(-0x1.a7ff6cp125F); }

    /**
     * This color constant "Desert Rain" has RGBA8888 code {@code 326496FF}, H 0.6862745, S 0.78431374, L 0.4117647, alpha 1.0, and chroma 0.5087606.
     * It can be represented as a packed float with the constant {@code -0x1.d3915ep125F}.
     * <pre>
     * <font style='background-color: #326496;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #326496; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #326496;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #326496'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #326496'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #326496'>&nbsp;@&nbsp;</font><font style='background-color: #326496; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #326496;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #326496; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DESERT_RAIN = -0x1.d3915ep125F;
    static { NAMED.put("Desert Rain", -0x1.d3915ep125F); LIST.add(-0x1.d3915ep125F); }

    /**
     * This color constant "Electric Blue" has RGBA8888 code {@code 0052F6FF}, H 0.7294118, S 1.0, L 0.42352942, alpha 1.0, and chroma 1.3270379.
     * It can be represented as a packed float with the constant {@code -0x1.d9ff74p125F}.
     * <pre>
     * <font style='background-color: #0052F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0052F6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0052F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0052F6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0052F6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0052F6'>&nbsp;@&nbsp;</font><font style='background-color: #0052F6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0052F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0052F6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ELECTRIC_BLUE = -0x1.d9ff74p125F;
    static { NAMED.put("Electric Blue", -0x1.d9ff74p125F); LIST.add(-0x1.d9ff74p125F); }

    /**
     * This color constant "Hidden Blue" has RGBA8888 code {@code 186ABDFF}, H 0.7019608, S 0.9529412, L 0.44313726, alpha 1.0, and chroma 0.8072445.
     * It can be represented as a packed float with the constant {@code -0x1.e3e766p125F}.
     * <pre>
     * <font style='background-color: #186ABD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #186ABD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #186ABD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #186ABD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #186ABD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #186ABD'>&nbsp;@&nbsp;</font><font style='background-color: #186ABD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #186ABD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #186ABD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float HIDDEN_BLUE = -0x1.e3e766p125F;
    static { NAMED.put("Hidden Blue", -0x1.e3e766p125F); LIST.add(-0x1.e3e766p125F); }

    /**
     * This color constant "Dull Azure" has RGBA8888 code {@code 2378DCFF}, H 0.7058824, S 0.9372549, L 0.5058824, alpha 1.0, and chroma 0.95903945.
     * It can be represented as a packed float with the constant {@code -0x1.03df68p126F}.
     * <pre>
     * <font style='background-color: #2378DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2378DC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2378DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2378DC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2378DC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2378DC'>&nbsp;@&nbsp;</font><font style='background-color: #2378DC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2378DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2378DC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_AZURE = -0x1.03df68p126F;
    static { NAMED.put("Dull Azure", -0x1.03df68p126F); LIST.add(-0x1.03df68p126F); }

    /**
     * This color constant "Ripped Denim" has RGBA8888 code {@code 699DC3FF}, H 0.65882355, S 0.5921569, L 0.627451, alpha 1.0, and chroma 0.45599785.
     * It can be represented as a packed float with the constant {@code -0x1.412f5p126F}.
     * <pre>
     * <font style='background-color: #699DC3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #699DC3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #699DC3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #699DC3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #699DC3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #699DC3'>&nbsp;@&nbsp;</font><font style='background-color: #699DC3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #699DC3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #699DC3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float RIPPED_DENIM = -0x1.412f5p126F;
    static { NAMED.put("Ripped Denim", -0x1.412f5p126F); LIST.add(-0x1.412f5p126F); }

    /**
     * This color constant "Calm Sky" has RGBA8888 code {@code 4AA4FFFF}, H 0.6901961, S 1.0, L 0.65882355, alpha 1.0, and chroma 0.9240134.
     * It can be represented as a packed float with the constant {@code -0x1.51ff6p126F}.
     * <pre>
     * <font style='background-color: #4AA4FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4AA4FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4AA4FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4AA4FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4AA4FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4AA4FF'>&nbsp;@&nbsp;</font><font style='background-color: #4AA4FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4AA4FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4AA4FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CALM_SKY = -0x1.51ff6p126F;
    static { NAMED.put("Calm Sky", -0x1.51ff6p126F); LIST.add(-0x1.51ff6p126F); }

    /**
     * This color constant "Vapor" has RGBA8888 code {@code 90B0FFFF}, H 0.70980394, S 1.0, L 0.7254902, alpha 1.0, and chroma 0.7195078.
     * It can be represented as a packed float with the constant {@code -0x1.73ff6ap126F}.
     * <pre>
     * <font style='background-color: #90B0FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #90B0FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #90B0FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #90B0FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #90B0FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #90B0FF'>&nbsp;@&nbsp;</font><font style='background-color: #90B0FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #90B0FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #90B0FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VAPOR = -0x1.73ff6ap126F;
    static { NAMED.put("Vapor", -0x1.73ff6ap126F); LIST.add(-0x1.73ff6ap126F); }

    /**
     * This color constant "Powder Blue" has RGBA8888 code {@code 5AC5FFFF}, H 0.6509804, S 1.0, L 0.75686276, alpha 1.0, and chroma 0.7263502.
     * It can be represented as a packed float with the constant {@code -0x1.83ff4cp126F}.
     * <pre>
     * <font style='background-color: #5AC5FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5AC5FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5AC5FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5AC5FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5AC5FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5AC5FF'>&nbsp;@&nbsp;</font><font style='background-color: #5AC5FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5AC5FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5AC5FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float POWDER_BLUE = -0x1.83ff4cp126F;
    static { NAMED.put("Powder Blue", -0x1.83ff4cp126F); LIST.add(-0x1.83ff4cp126F); }

    /**
     * This color constant "Suds" has RGBA8888 code {@code BEB9FAFF}, H 0.74509805, S 0.91764706, L 0.7764706, alpha 1.0, and chroma 0.5272629.
     * It can be represented as a packed float with the constant {@code -0x1.8dd57cp126F}.
     * <pre>
     * <font style='background-color: #BEB9FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BEB9FA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BEB9FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BEB9FA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BEB9FA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BEB9FA'>&nbsp;@&nbsp;</font><font style='background-color: #BEB9FA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BEB9FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BEB9FA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SUDS = -0x1.8dd57cp126F;
    static { NAMED.put("Suds", -0x1.8dd57cp126F); LIST.add(-0x1.8dd57cp126F); }

    /**
     * This color constant "Strong Cyan" has RGBA8888 code {@code 00BFFFFF}, H 0.6509804, S 1.0, L 0.7254902, alpha 1.0, and chroma 0.82470423.
     * It can be represented as a packed float with the constant {@code -0x1.73ff4cp126F}.
     * <pre>
     * <font style='background-color: #00BFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00BFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00BFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00BFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00BFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00BFFF'>&nbsp;@&nbsp;</font><font style='background-color: #00BFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00BFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00BFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_CYAN = -0x1.73ff4cp126F;
    static { NAMED.put("Strong Cyan", -0x1.73ff4cp126F); LIST.add(-0x1.73ff4cp126F); }

    /**
     * This color constant "Sharp Azure" has RGBA8888 code {@code 007FFFFF}, H 0.70980394, S 1.0, L 0.54509807, alpha 1.0, and chroma 1.1696558.
     * It can be represented as a packed float with the constant {@code -0x1.17ff6ap126F}.
     * <pre>
     * <font style='background-color: #007FFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007FFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007FFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #007FFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #007FFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #007FFF'>&nbsp;@&nbsp;</font><font style='background-color: #007FFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007FFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007FFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SHARP_AZURE = -0x1.17ff6ap126F;
    static { NAMED.put("Sharp Azure", -0x1.17ff6ap126F); LIST.add(-0x1.17ff6ap126F); }

    /**
     * This color constant "Blue Eye" has RGBA8888 code {@code 4B7DC8FF}, H 0.7019608, S 0.7294118, L 0.52156866, alpha 1.0, and chroma 0.72725207.
     * It can be represented as a packed float with the constant {@code -0x1.0b7566p126F}.
     * <pre>
     * <font style='background-color: #4B7DC8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B7DC8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B7DC8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4B7DC8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4B7DC8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4B7DC8'>&nbsp;@&nbsp;</font><font style='background-color: #4B7DC8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B7DC8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B7DC8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUE_EYE = -0x1.0b7566p126F;
    static { NAMED.put("Blue Eye", -0x1.0b7566p126F); LIST.add(-0x1.0b7566p126F); }

    /**
     * This color constant "Subtlety" has RGBA8888 code {@code 786EF0FF}, H 0.74509805, S 0.8901961, L 0.53333336, alpha 1.0, and chroma 1.0448283.
     * It can be represented as a packed float with the constant {@code -0x1.11c77cp126F}.
     * <pre>
     * <font style='background-color: #786EF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #786EF0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #786EF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #786EF0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #786EF0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #786EF0'>&nbsp;@&nbsp;</font><font style='background-color: #786EF0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #786EF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #786EF0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SUBTLETY = -0x1.11c77cp126F;
    static { NAMED.put("Subtlety", -0x1.11c77cp126F); LIST.add(-0x1.11c77cp126F); }

    /**
     * This color constant "Rough Sapphire" has RGBA8888 code {@code 4A5AFFFF}, H 0.73333335, S 1.0, L 0.47058824, alpha 1.0, and chroma 1.2771475.
     * It can be represented as a packed float with the constant {@code -0x1.f1ff76p125F}.
     * <pre>
     * <font style='background-color: #4A5AFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A5AFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A5AFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4A5AFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4A5AFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4A5AFF'>&nbsp;@&nbsp;</font><font style='background-color: #4A5AFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A5AFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A5AFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ROUGH_SAPPHIRE = -0x1.f1ff76p125F;
    static { NAMED.put("Rough Sapphire", -0x1.f1ff76p125F); LIST.add(-0x1.f1ff76p125F); }

    /**
     * This color constant "Iris" has RGBA8888 code {@code 6241F6FF}, H 0.7490196, S 0.95686275, L 0.42745098, alpha 1.0, and chroma 1.2644104.
     * It can be represented as a packed float with the constant {@code -0x1.dbe97ep125F}.
     * <pre>
     * <font style='background-color: #6241F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6241F6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6241F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6241F6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6241F6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6241F6'>&nbsp;@&nbsp;</font><font style='background-color: #6241F6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6241F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6241F6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float IRIS = -0x1.dbe97ep125F;
    static { NAMED.put("Iris", -0x1.dbe97ep125F); LIST.add(-0x1.dbe97ep125F); }

    /**
     * This color constant "Cornflower Blue" has RGBA8888 code {@code 3C3CF5FF}, H 0.7411765, S 0.95686275, L 0.39215687, alpha 1.0, and chroma 1.2788987.
     * It can be represented as a packed float with the constant {@code -0x1.c9e97ap125F}.
     * <pre>
     * <font style='background-color: #3C3CF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C3CF5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C3CF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3C3CF5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3C3CF5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3C3CF5'>&nbsp;@&nbsp;</font><font style='background-color: #3C3CF5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C3CF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C3CF5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CORNFLOWER_BLUE = -0x1.c9e97ap125F;
    static { NAMED.put("Cornflower Blue", -0x1.c9e97ap125F); LIST.add(-0x1.c9e97ap125F); }

    /**
     * This color constant "Polished Sapphire" has RGBA8888 code {@code 101CDAFF}, H 0.7372549, S 0.9647059, L 0.29411766, alpha 1.0, and chroma 1.1073371.
     * It can be represented as a packed float with the constant {@code -0x1.97ed78p125F}.
     * <pre>
     * <font style='background-color: #101CDA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #101CDA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #101CDA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #101CDA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #101CDA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #101CDA'>&nbsp;@&nbsp;</font><font style='background-color: #101CDA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #101CDA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #101CDA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float POLISHED_SAPPHIRE = -0x1.97ed78p125F;
    static { NAMED.put("Polished Sapphire", -0x1.97ed78p125F); LIST.add(-0x1.97ed78p125F); }

    /**
     * This color constant "Royal Blue" has RGBA8888 code {@code 0010BDFF}, H 0.7372549, S 1.0, L 0.23529412, alpha 1.0, and chroma 0.9182794.
     * It can be represented as a packed float with the constant {@code -0x1.79ff78p125F}.
     * <pre>
     * <font style='background-color: #0010BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0010BD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0010BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0010BD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0010BD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0010BD'>&nbsp;@&nbsp;</font><font style='background-color: #0010BD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0010BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0010BD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ROYAL_BLUE = -0x1.79ff78p125F;
    static { NAMED.put("Royal Blue", -0x1.79ff78p125F); LIST.add(-0x1.79ff78p125F); }

    /**
     * This color constant "Indigo" has RGBA8888 code {@code 231094FF}, H 0.74509805, S 0.92941177, L 0.19215687, alpha 1.0, and chroma 0.67952585.
     * It can be represented as a packed float with the constant {@code -0x1.63db7cp125F}.
     * <pre>
     * <font style='background-color: #231094;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #231094; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #231094;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #231094'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #231094'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #231094'>&nbsp;@&nbsp;</font><font style='background-color: #231094; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #231094;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #231094; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float INDIGO = -0x1.63db7cp125F;
    static { NAMED.put("Indigo", -0x1.63db7cp125F); LIST.add(-0x1.63db7cp125F); }

    /**
     * This color constant "Space Blue" has RGBA8888 code {@code 0C2148FF}, H 0.7137255, S 0.84313726, L 0.13333334, alpha 1.0, and chroma 0.25777483.
     * It can be represented as a packed float with the constant {@code -0x1.45af6cp125F}.
     * <pre>
     * <font style='background-color: #0C2148;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0C2148; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0C2148;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0C2148'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0C2148'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0C2148'>&nbsp;@&nbsp;</font><font style='background-color: #0C2148; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0C2148;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0C2148; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SPACE_BLUE = -0x1.45af6cp125F;
    static { NAMED.put("Space Blue", -0x1.45af6cp125F); LIST.add(-0x1.45af6cp125F); }

    /**
     * This color constant "Thick Amethyst" has RGBA8888 code {@code 5010B0FF}, H 0.7607843, S 0.9529412, L 0.27058825, alpha 1.0, and chroma 0.86605996.
     * It can be represented as a packed float with the constant {@code -0x1.8be784p125F}.
     * <pre>
     * <font style='background-color: #5010B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5010B0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5010B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5010B0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5010B0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5010B0'>&nbsp;@&nbsp;</font><font style='background-color: #5010B0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5010B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5010B0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float THICK_AMETHYST = -0x1.8be784p125F;
    static { NAMED.put("Thick Amethyst", -0x1.8be784p125F); LIST.add(-0x1.8be784p125F); }

    /**
     * This color constant "Juicy Grape" has RGBA8888 code {@code 6010D0FF}, H 0.7607843, S 0.96862745, L 0.3254902, alpha 1.0, and chroma 1.0589309.
     * It can be represented as a packed float with the constant {@code -0x1.a7ef84p125F}.
     * <pre>
     * <font style='background-color: #6010D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6010D0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6010D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6010D0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6010D0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6010D0'>&nbsp;@&nbsp;</font><font style='background-color: #6010D0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6010D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6010D0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float JUICY_GRAPE = -0x1.a7ef84p125F;
    static { NAMED.put("Juicy Grape", -0x1.a7ef84p125F); LIST.add(-0x1.a7ef84p125F); }

    /**
     * This color constant "Blacklight Glow" has RGBA8888 code {@code 8732D2FF}, H 0.78039217, S 0.84313726, L 0.4117647, alpha 1.0, and chroma 1.0282933.
     * It can be represented as a packed float with the constant {@code -0x1.d3af8ep125F}.
     * <pre>
     * <font style='background-color: #8732D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8732D2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8732D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8732D2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8732D2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8732D2'>&nbsp;@&nbsp;</font><font style='background-color: #8732D2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8732D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8732D2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACKLIGHT_GLOW = -0x1.d3af8ep125F;
    static { NAMED.put("Blacklight Glow", -0x1.d3af8ep125F); LIST.add(-0x1.d3af8ep125F); }

    /**
     * This color constant "Purple Freesia" has RGBA8888 code {@code 9C41FFFF}, H 0.7764706, S 1.0, L 0.49411765, alpha 1.0, and chroma 1.2741718.
     * It can be represented as a packed float with the constant {@code -0x1.fdff8cp125F}.
     * <pre>
     * <font style='background-color: #9C41FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C41FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C41FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9C41FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9C41FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9C41FF'>&nbsp;@&nbsp;</font><font style='background-color: #9C41FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C41FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C41FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURPLE_FREESIA = -0x1.fdff8cp125F;
    static { NAMED.put("Purple Freesia", -0x1.fdff8cp125F); LIST.add(-0x1.fdff8cp125F); }

    /**
     * This color constant "Thin Amethyst" has RGBA8888 code {@code 7F00FFFF}, H 0.7647059, S 1.0, L 0.40784314, alpha 1.0, and chroma 1.3325449.
     * It can be represented as a packed float with the constant {@code -0x1.d1ff86p125F}.
     * <pre>
     * <font style='background-color: #7F00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F00FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7F00FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7F00FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7F00FF'>&nbsp;@&nbsp;</font><font style='background-color: #7F00FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F00FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float THIN_AMETHYST = -0x1.d1ff86p125F;
    static { NAMED.put("Thin Amethyst", -0x1.d1ff86p125F); LIST.add(-0x1.d1ff86p125F); }

    /**
     * This color constant "Orchid" has RGBA8888 code {@code BD62FFFF}, H 0.7921569, S 1.0, L 0.5882353, alpha 1.0, and chroma 1.1191578.
     * It can be represented as a packed float with the constant {@code -0x1.2dff94p126F}.
     * <pre>
     * <font style='background-color: #BD62FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD62FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD62FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BD62FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BD62FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BD62FF'>&nbsp;@&nbsp;</font><font style='background-color: #BD62FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD62FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD62FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ORCHID = -0x1.2dff94p126F;
    static { NAMED.put("Orchid", -0x1.2dff94p126F); LIST.add(-0x1.2dff94p126F); }

    /**
     * This color constant "Lavender" has RGBA8888 code {@code B991FFFF}, H 0.77254903, S 1.0, L 0.6784314, alpha 1.0, and chroma 0.85476416.
     * It can be represented as a packed float with the constant {@code -0x1.5bff8ap126F}.
     * <pre>
     * <font style='background-color: #B991FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B991FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B991FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B991FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B991FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B991FF'>&nbsp;@&nbsp;</font><font style='background-color: #B991FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B991FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B991FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LAVENDER = -0x1.5bff8ap126F;
    static { NAMED.put("Lavender", -0x1.5bff8ap126F); LIST.add(-0x1.5bff8ap126F); }

    /**
     * This color constant "Lilac" has RGBA8888 code {@code D7A5FFFF}, H 0.8, S 1.0, L 0.7529412, alpha 1.0, and chroma 0.6914161.
     * It can be represented as a packed float with the constant {@code -0x1.81ff98p126F}.
     * <pre>
     * <font style='background-color: #D7A5FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7A5FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7A5FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D7A5FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D7A5FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D7A5FF'>&nbsp;@&nbsp;</font><font style='background-color: #D7A5FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7A5FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7A5FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LILAC = -0x1.81ff98p126F;
    static { NAMED.put("Lilac", -0x1.81ff98p126F); LIST.add(-0x1.81ff98p126F); }

    /**
     * This color constant "Soap" has RGBA8888 code {@code D7C3FAFF}, H 0.7764706, S 0.8980392, L 0.8235294, alpha 1.0, and chroma 0.41648513.
     * It can be represented as a packed float with the constant {@code -0x1.a5cb8cp126F}.
     * <pre>
     * <font style='background-color: #D7C3FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7C3FA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7C3FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D7C3FA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D7C3FA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D7C3FA'>&nbsp;@&nbsp;</font><font style='background-color: #D7C3FA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7C3FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7C3FA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOAP = -0x1.a5cb8cp126F;
    static { NAMED.put("Soap", -0x1.a5cb8cp126F); LIST.add(-0x1.a5cb8cp126F); }

    /**
     * This color constant "Pink Tutu" has RGBA8888 code {@code F8C6FCFF}, H 0.84705883, S 0.9254902, L 0.85882354, alpha 1.0, and chroma 0.43167844.
     * It can be represented as a packed float with the constant {@code -0x1.b7d9bp126F}.
     * <pre>
     * <font style='background-color: #F8C6FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F8C6FC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F8C6FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F8C6FC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F8C6FC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F8C6FC'>&nbsp;@&nbsp;</font><font style='background-color: #F8C6FC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F8C6FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F8C6FC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINK_TUTU = -0x1.b7d9bp126F;
    static { NAMED.put("Pink Tutu", -0x1.b7d9bp126F); LIST.add(-0x1.b7d9bp126F); }

    /**
     * This color constant "Thistle" has RGBA8888 code {@code E673FFFF}, H 0.827451, S 1.0, L 0.6666667, alpha 1.0, and chroma 1.0238898.
     * It can be represented as a packed float with the constant {@code -0x1.55ffa6p126F}.
     * <pre>
     * <font style='background-color: #E673FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E673FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E673FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E673FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E673FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E673FF'>&nbsp;@&nbsp;</font><font style='background-color: #E673FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E673FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E673FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float THISTLE = -0x1.55ffa6p126F;
    static { NAMED.put("Thistle", -0x1.55ffa6p126F); LIST.add(-0x1.55ffa6p126F); }

    /**
     * This color constant "Heliotrope" has RGBA8888 code {@code FF52FFFF}, H 0.85490197, S 1.0, L 0.654902, alpha 1.0, and chroma 1.2072707.
     * It can be represented as a packed float with the constant {@code -0x1.4fffb4p126F}.
     * <pre>
     * <font style='background-color: #FF52FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF52FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF52FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF52FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF52FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF52FF'>&nbsp;@&nbsp;</font><font style='background-color: #FF52FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF52FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF52FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float HELIOTROPE = -0x1.4fffb4p126F;
    static { NAMED.put("Heliotrope", -0x1.4fffb4p126F); LIST.add(-0x1.4fffb4p126F); }

    /**
     * This color constant "Purple" has RGBA8888 code {@code DA20E0FF}, H 0.8509804, S 0.9529412, L 0.53333336, alpha 1.0, and chroma 1.164639.
     * It can be represented as a packed float with the constant {@code -0x1.11e7b2p126F}.
     * <pre>
     * <font style='background-color: #DA20E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA20E0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA20E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DA20E0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DA20E0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DA20E0'>&nbsp;@&nbsp;</font><font style='background-color: #DA20E0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA20E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA20E0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURPLE = -0x1.11e7b2p126F;
    static { NAMED.put("Purple", -0x1.11e7b2p126F); LIST.add(-0x1.11e7b2p126F); }

    /**
     * This color constant "Wisteria" has RGBA8888 code {@code BD29FFFF}, H 0.8, S 1.0, L 0.5137255, alpha 1.0, and chroma 1.3000408.
     * It can be represented as a packed float with the constant {@code -0x1.07ff98p126F}.
     * <pre>
     * <font style='background-color: #BD29FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD29FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD29FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BD29FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BD29FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BD29FF'>&nbsp;@&nbsp;</font><font style='background-color: #BD29FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD29FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD29FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WISTERIA = -0x1.07ff98p126F;
    static { NAMED.put("Wisteria", -0x1.07ff98p126F); LIST.add(-0x1.07ff98p126F); }

    /**
     * This color constant "Medium Plum" has RGBA8888 code {@code BD10C5FF}, H 0.84705883, S 0.98039216, L 0.45882353, alpha 1.0, and chroma 1.0388222.
     * It can be represented as a packed float with the constant {@code -0x1.ebf5bp125F}.
     * <pre>
     * <font style='background-color: #BD10C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD10C5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD10C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BD10C5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BD10C5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BD10C5'>&nbsp;@&nbsp;</font><font style='background-color: #BD10C5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD10C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD10C5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MEDIUM_PLUM = -0x1.ebf5bp125F;
    static { NAMED.put("Medium Plum", -0x1.ebf5bp125F); LIST.add(-0x1.ebf5bp125F); }

    /**
     * This color constant "Violet" has RGBA8888 code {@code 8C14BEFF}, H 0.8, S 0.95686275, L 0.37254903, alpha 1.0, and chroma 0.95663005.
     * It can be represented as a packed float with the constant {@code -0x1.bfe998p125F}.
     * <pre>
     * <font style='background-color: #8C14BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C14BE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C14BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8C14BE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8C14BE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8C14BE'>&nbsp;@&nbsp;</font><font style='background-color: #8C14BE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C14BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C14BE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIOLET = -0x1.bfe998p125F;
    static { NAMED.put("Violet", -0x1.bfe998p125F); LIST.add(-0x1.bfe998p125F); }

    /**
     * This color constant "Grape Lollipop" has RGBA8888 code {@code 5A187BFF}, H 0.8, S 0.8666667, L 0.24313726, alpha 1.0, and chroma 0.5654765.
     * It can be represented as a packed float with the constant {@code -0x1.7dbb98p125F}.
     * <pre>
     * <font style='background-color: #5A187B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A187B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A187B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5A187B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5A187B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5A187B'>&nbsp;@&nbsp;</font><font style='background-color: #5A187B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A187B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A187B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAPE_LOLLIPOP = -0x1.7dbb98p125F;
    static { NAMED.put("Grape Lollipop", -0x1.7dbb98p125F); LIST.add(-0x1.7dbb98p125F); }

    /**
     * This color constant "Mulberry" has RGBA8888 code {@code 641464FF}, H 0.85490197, S 0.8784314, L 0.23921569, alpha 1.0, and chroma 0.47813028.
     * It can be represented as a packed float with the constant {@code -0x1.7bc1b4p125F}.
     * <pre>
     * <font style='background-color: #641464;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #641464; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #641464;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #641464'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #641464'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #641464'>&nbsp;@&nbsp;</font><font style='background-color: #641464; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #641464;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #641464; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MULBERRY = -0x1.7bc1b4p125F;
    static { NAMED.put("Mulberry", -0x1.7bc1b4p125F); LIST.add(-0x1.7bc1b4p125F); }

    /**
     * This color constant "Grape Soda" has RGBA8888 code {@code 410062FF}, H 0.7921569, S 1.0, L 0.15294118, alpha 1.0, and chroma 0.42570746.
     * It can be represented as a packed float with the constant {@code -0x1.4fff94p125F}.
     * <pre>
     * <font style='background-color: #410062;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #410062; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #410062;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #410062'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #410062'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #410062'>&nbsp;@&nbsp;</font><font style='background-color: #410062; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #410062;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #410062; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAPE_SODA = -0x1.4fff94p125F;
    static { NAMED.put("Grape Soda", -0x1.4fff94p125F); LIST.add(-0x1.4fff94p125F); }

    /**
     * This color constant "Eggplant" has RGBA8888 code {@code 320A46FF}, H 0.8, S 0.85490197, L 0.11372549, alpha 1.0, and chroma 0.26090658.
     * It can be represented as a packed float with the constant {@code -0x1.3bb598p125F}.
     * <pre>
     * <font style='background-color: #320A46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #320A46; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #320A46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #320A46'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #320A46'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #320A46'>&nbsp;@&nbsp;</font><font style='background-color: #320A46; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #320A46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #320A46; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float EGGPLANT = -0x1.3bb598p125F;
    static { NAMED.put("Eggplant", -0x1.3bb598p125F); LIST.add(-0x1.3bb598p125F); }

    /**
     * This color constant "Cherry Syrup" has RGBA8888 code {@code 551937FF}, H 0.95686275, S 0.6901961, L 0.19607843, alpha 1.0, and chroma 0.31975806.
     * It can be represented as a packed float with the constant {@code -0x1.6561e8p125F}.
     * <pre>
     * <font style='background-color: #551937;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #551937; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #551937;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #551937'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #551937'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #551937'>&nbsp;@&nbsp;</font><font style='background-color: #551937; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #551937;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #551937; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CHERRY_SYRUP = -0x1.6561e8p125F;
    static { NAMED.put("Cherry Syrup", -0x1.6561e8p125F); LIST.add(-0x1.6561e8p125F); }

    /**
     * This color constant "Plum Juice" has RGBA8888 code {@code A01982FF}, H 0.90588236, S 0.92156863, L 0.37254903, alpha 1.0, and chroma 0.7550519.
     * It can be represented as a packed float with the constant {@code -0x1.bfd7cep125F}.
     * <pre>
     * <font style='background-color: #A01982;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A01982; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A01982;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A01982'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A01982'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A01982'>&nbsp;@&nbsp;</font><font style='background-color: #A01982; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A01982;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A01982; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PLUM_JUICE = -0x1.bfd7cep125F;
    static { NAMED.put("Plum Juice", -0x1.bfd7cep125F); LIST.add(-0x1.bfd7cep125F); }

    /**
     * This color constant "Fruit Punch" has RGBA8888 code {@code C80078FF}, H 0.9647059, S 1.0, L 0.43529412, alpha 1.0, and chroma 1.0502769.
     * It can be represented as a packed float with the constant {@code -0x1.dfffecp125F}.
     * <pre>
     * <font style='background-color: #C80078;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C80078; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C80078;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C80078'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C80078'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C80078'>&nbsp;@&nbsp;</font><font style='background-color: #C80078; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C80078;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C80078; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FRUIT_PUNCH = -0x1.dfffecp125F;
    static { NAMED.put("Fruit Punch", -0x1.dfffecp125F); LIST.add(-0x1.dfffecp125F); }

    /**
     * This color constant "Bubble Gum" has RGBA8888 code {@code FF50BFFF}, H 0.93333334, S 1.0, L 0.62352943, alpha 1.0, and chroma 1.0739204.
     * It can be represented as a packed float with the constant {@code -0x1.3fffdcp126F}.
     * <pre>
     * <font style='background-color: #FF50BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF50BF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF50BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF50BF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF50BF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF50BF'>&nbsp;@&nbsp;</font><font style='background-color: #FF50BF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF50BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF50BF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BUBBLE_GUM = -0x1.3fffdcp126F;
    static { NAMED.put("Bubble Gum", -0x1.3fffdcp126F); LIST.add(-0x1.3fffdcp126F); }

    /**
     * This color constant "Pink Lemonade" has RGBA8888 code {@code FF6AC5FF}, H 0.92941177, S 1.0, L 0.6627451, alpha 1.0, and chroma 0.94235647.
     * It can be represented as a packed float with the constant {@code -0x1.53ffdap126F}.
     * <pre>
     * <font style='background-color: #FF6AC5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6AC5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6AC5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF6AC5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF6AC5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF6AC5'>&nbsp;@&nbsp;</font><font style='background-color: #FF6AC5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6AC5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6AC5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINK_LEMONADE = -0x1.53ffdap126F;
    static { NAMED.put("Pink Lemonade", -0x1.53ffdap126F); LIST.add(-0x1.53ffdap126F); }

    /**
     * This color constant "Shrimp" has RGBA8888 code {@code FAA0B9FF}, H 0.9882353, S 0.91764706, L 0.75686276, alpha 1.0, and chroma 0.5614246.
     * It can be represented as a packed float with the constant {@code -0x1.83d5f8p126F}.
     * <pre>
     * <font style='background-color: #FAA0B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FAA0B9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FAA0B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FAA0B9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FAA0B9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FAA0B9'>&nbsp;@&nbsp;</font><font style='background-color: #FAA0B9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FAA0B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FAA0B9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SHRIMP = -0x1.83d5f8p126F;
    static { NAMED.put("Shrimp", -0x1.83d5f8p126F); LIST.add(-0x1.83d5f8p126F); }

    /**
     * This color constant "Flamingo" has RGBA8888 code {@code FC3A8CFF}, H 0.9843137, S 0.96862745, L 0.5764706, alpha 1.0, and chroma 1.2487497.
     * It can be represented as a packed float with the constant {@code -0x1.27eff6p126F}.
     * <pre>
     * <font style='background-color: #FC3A8C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FC3A8C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FC3A8C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FC3A8C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FC3A8C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FC3A8C'>&nbsp;@&nbsp;</font><font style='background-color: #FC3A8C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FC3A8C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FC3A8C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FLAMINGO = -0x1.27eff6p126F;
    static { NAMED.put("Flamingo", -0x1.27eff6p126F); LIST.add(-0x1.27eff6p126F); }

    /**
     * This color constant "Rose" has RGBA8888 code {@code E61E78FF}, H 0.9882353, S 0.9372549, L 0.50980395, alpha 1.0, and chroma 1.2496047.
     * It can be represented as a packed float with the constant {@code -0x1.05dff8p126F}.
     * <pre>
     * <font style='background-color: #E61E78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E61E78; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E61E78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E61E78'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E61E78'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E61E78'>&nbsp;@&nbsp;</font><font style='background-color: #E61E78; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E61E78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E61E78; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ROSE = -0x1.05dff8p126F;
    static { NAMED.put("Rose", -0x1.05dff8p126F); LIST.add(-0x1.05dff8p126F); }

    /**
     * This color constant "Carmine" has RGBA8888 code {@code BD1039FF}, H 0.015686275, S 0.9529412, L 0.40392157, alpha 1.0, and chroma 1.1495169.
     * It can be represented as a packed float with the constant {@code -0x1.cfe608p125F}.
     * <pre>
     * <font style='background-color: #BD1039;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD1039; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD1039;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BD1039'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BD1039'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BD1039'>&nbsp;@&nbsp;</font><font style='background-color: #BD1039; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD1039;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD1039; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CARMINE = -0x1.cfe608p125F;
    static { NAMED.put("Carmine", -0x1.cfe608p125F); LIST.add(-0x1.cfe608p125F); }

    /**
     * This color constant "Bologna" has RGBA8888 code {@code 98344DFF}, H 0.0, S 0.6431373, L 0.37254903, alpha 1.0, and chroma 0.6594699.
     * It can be represented as a packed float with the constant {@code -0x1.bf48p125F}.
     * <pre>
     * <font style='background-color: #98344D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #98344D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #98344D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #98344D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #98344D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #98344D'>&nbsp;@&nbsp;</font><font style='background-color: #98344D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #98344D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #98344D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLOGNA = -0x1.bf48p125F;
    static { NAMED.put("Bologna", -0x1.bf48p125F); LIST.add(-0x1.bf48p125F); }

    /**
     * This color constant "Raspberry" has RGBA8888 code {@code 911437FF}, H 0.007843138, S 0.8980392, L 0.3137255, alpha 1.0, and chroma 0.80603844.
     * It can be represented as a packed float with the constant {@code -0x1.a1ca04p125F}.
     * <pre>
     * <font style='background-color: #911437;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #911437; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #911437;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #911437'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #911437'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #911437'>&nbsp;@&nbsp;</font><font style='background-color: #911437; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #911437;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #911437; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float RASPBERRY = -0x1.a1ca04p125F;
    static { NAMED.put("Raspberry", -0x1.a1ca04p125F); LIST.add(-0x1.a1ca04p125F); }

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
        NAMES_BY_HUE.sort((o1, o2) -> {
            final float c1 = NAMED.get(o1), c2 = NAMED.get(o2);
            if(alphaInt(c1) < 128) return -10000;
            else if(alphaInt(c2) < 128) return 10000;
            final float s1 = channelS(c1), s2 = channelS(c2);
            if(s1 <= 0.05f && s2 > 0.05f)
                return -1000;
            else if(s1 > 0.05f && s2 <= 0.05f)
                return 1000;
            else if(s1 <= 0.05f && s2 <= 0.05f)
                return (int)Math.signum(channelL(c1) - channelL(c2));
            else
                return 2 * (int)Math.signum(channelH(c1) - channelH(c2))
                        + (int)Math.signum(channelL(c1) - channelL(c2));
        });
        NAMES_BY_LIGHTNESS.sort((o1, o2) ->
                Float.compare(channelL(NAMED.get(o1)), channelL(NAMED.get(o2))));
    }
}
