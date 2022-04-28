package com.github.tommyettinger.colorful.pure.hsluv;

import com.github.tommyettinger.ds.FloatList;
import com.github.tommyettinger.ds.ObjectFloatOrderedMap;
import com.github.tommyettinger.ds.ObjectList;

import java.util.Comparator;

import static com.github.tommyettinger.colorful.pure.hsluv.ColorTools.*;

/**
 * A palette of predefined colors as packed HSLuv floats, the kind {@link ColorTools} works with, plus a way to describe
 * colors by combinations and adjustments. The description code is probably what you would use this class for; it
 * revolves around {@link #parseDescription(String)}, which takes a color description String and returns a packed float
 * color. The color descriptions look like "darker rich mint yellow", where the order of the words doesn't matter. They
 * can optionally include lightness changes (light/dark), and saturation changes (rich/dull), and must include one or
 * more color names that will be mixed together (repeats are allowed to use a color more heavily). The changes can be
 * suffixed with "er", "est", or "most", such as "duller", "lightest", or "richmost", to progressively increase their
 * effect on lightness or saturation.
 * <br>
 * The rest of this is about the same as in {@code Palette}.
 * <br>
 * You can access colors by their constant name, such as {@code CACTUS}, by the {@link #NAMED} map using
 * {@code NAMED.get("cactus", 0f)}, or by index in the FloatArray called {@link #LIST}. Note that to access a float
 * color from NAMED, you need to give a default value if the name is not found; {@code 0f} is a good default because it
 * will not occur in a valid HSLuv color. You can access the names in a specific order with {@link #NAMES} (which is
 * alphabetical), {@link #NAMES_BY_HUE} (which is sorted by the hue of the matching color, from red to yellow to blue
 * (with gray around here) to purple to red again), or {@link #NAMES_BY_LIGHTNESS} (which is sorted by the intensity of
 * the matching color, from darkest to lightest). Having a name lets you look up the matching color in {@link #NAMED}.
 * <br>
 * Created by Tommy Ettinger on 10/13/2020.
 */
public class SimplePalette {
    /**
     * You can look up colors by name here; the names are lower-case, and the colors are packed floats in Oklab format.
     */
    public static final ObjectFloatOrderedMap<String> NAMED = new ObjectFloatOrderedMap<>(60);
    /**
     * Stores alternative names for colors in {@link #NAMED}, like "grey" as an alias for {@link #GRAY} or "gold" as an
     * alias for {@link #SAFFRON}. Currently, the list of aliases is as follows:
     * <ul>
     * <li>"grey" maps to {@link #GRAY},</li>
     * <li>"gold" maps to {@link #SAFFRON},</li>
     * <li>"puce" maps to {@link #MAUVE},</li>
     * <li>"sand" maps to {@link #TAN},</li>
     * <li>"skin" maps to {@link #PEACH},</li>
     * <li>"coral" maps to {@link #SALMON},</li>
     * <li>"azure" maps to {@link #SKY}, and</li>
     * <li>"ocean" maps to {@link #TEAL}, and</li>
     * <li>"sapphire" maps to {@link #COBALT}.</li>
     * </ul>
     * Note that these aliases are not duplicated in {@link #NAMES}, {@link #NAMES_BY_HUE}, or
     * {@link #NAMES_BY_LIGHTNESS}; they are primarily there so blind attempts to name a color might still work.
     */
    public static final ObjectFloatOrderedMap<String> ALIASES = new ObjectFloatOrderedMap<>(10);
    /**
     * Lists the packed float color values in this, in no particular order. Does not include duplicates from aliases.
     */
    public static final FloatList LIST = new FloatList(50);

    /**
     * This color constant "transparent" has RGBA8888 code {@code 00000000}, H 0.0, S 0.0, L 0.0, alpha 0.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code 0x0.0p0F}.
     * <pre>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #000000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRANSPARENT = 0x0.0p0F;
    static { NAMED.put("transparent", 0x0.0p0F); LIST.add(0x0.0p0F); }

    /**
     * This color constant "black" has RGBA8888 code {@code 000000FF}, H 0.0, S 0.0, L 0.0, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.0p125F}.
     * <pre>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #000000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK = -0x1.0p125F;
    static { NAMED.put("black", -0x1.0p125F); LIST.add(-0x1.0p125F); }

    /**
     * This color constant "gray" has RGBA8888 code {@code 808080FF}, H 0.16078432, S 0.0, L 0.5019608, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.000052p126F}.
     * <pre>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #808080; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #808080; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #808080; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY = -0x1.000052p126F;
    static { NAMED.put("gray", -0x1.000052p126F); LIST.add(-0x1.000052p126F); }

    /**
     * This color constant "silver" has RGBA8888 code {@code B6B6B6FF}, H 0.16078432, S 0.0, L 0.70980394, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.6a0052p126F}.
     * <pre>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B6B6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #B6B6B6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B6B6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER = -0x1.6a0052p126F;
    static { NAMED.put("silver", -0x1.6a0052p126F); LIST.add(-0x1.6a0052p126F); }

    /**
     * This color constant "white" has RGBA8888 code {@code FFFFFFFF}, H 0.16078432, S 0.0, L 1.0, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.fe0052p126F}.
     * <pre>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #FFFFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE = -0x1.fe0052p126F;
    static { NAMED.put("white", -0x1.fe0052p126F); LIST.add(-0x1.fe0052p126F); }

    /**
     * This color constant "red" has RGBA8888 code {@code FF0000FF}, H 0.03137255, S 1.0, L 0.49803922, alpha 1.0, and chroma 1.7587819.
     * It can be represented as a packed float with the constant {@code -0x1.fffe1p125F}.
     * <pre>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #FF0000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float RED = -0x1.fffe1p125F;
    static { NAMED.put("red", -0x1.fffe1p125F); LIST.add(-0x1.fffe1p125F); }

    /**
     * This color constant "orange" has RGBA8888 code {@code FF7F00FF}, H 0.08235294, S 1.0, L 0.63529414, alpha 1.0, and chroma 1.22371.
     * It can be represented as a packed float with the constant {@code -0x1.45fe2ap126F}.
     * <pre>
     * <font style='background-color: #FF7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF7F00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF7F00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF7F00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF7F00'>&nbsp;@&nbsp;</font><font style='background-color: #FF7F00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF7F00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ORANGE = -0x1.45fe2ap126F;
    static { NAMED.put("orange", -0x1.45fe2ap126F); LIST.add(-0x1.45fe2ap126F); }

    /**
     * This color constant "yellow" has RGBA8888 code {@code FFFF00FF}, H 0.23921569, S 1.0, L 0.96862745, alpha 1.0, and chroma 0.96873754.
     * It can be represented as a packed float with the constant {@code -0x1.effe7ap126F}.
     * <pre>
     * <font style='background-color: #FFFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFF00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFF00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFF00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFF00'>&nbsp;@&nbsp;</font><font style='background-color: #FFFF00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFF00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float YELLOW = -0x1.effe7ap126F;
    static { NAMED.put("yellow", -0x1.effe7ap126F); LIST.add(-0x1.effe7ap126F); }

    /**
     * This color constant "green" has RGBA8888 code {@code 00FF00FF}, H 0.3529412, S 1.0, L 0.8627451, alpha 1.0, and chroma 1.3288879.
     * It can be represented as a packed float with the constant {@code -0x1.b9feb4p126F}.
     * <pre>
     * <font style='background-color: #00FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FF00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FF00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FF00'>&nbsp;@&nbsp;</font><font style='background-color: #00FF00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GREEN = -0x1.b9feb4p126F;
    static { NAMED.put("green", -0x1.b9feb4p126F); LIST.add(-0x1.b9feb4p126F); }

    /**
     * This color constant "blue" has RGBA8888 code {@code 0000FFFF}, H 0.7411765, S 1.0, L 0.29803923, alpha 1.0, and chroma 1.2761624.
     * It can be represented as a packed float with the constant {@code -0x1.99ff7ap125F}.
     * <pre>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0000FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #0000FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0000FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUE = -0x1.99ff7ap125F;
    static { NAMED.put("blue", -0x1.99ff7ap125F); LIST.add(-0x1.99ff7ap125F); }

    /**
     * This color constant "indigo" has RGBA8888 code {@code 520FE0FF}, H 0.7529412, S 1.0, L 0.3019608, alpha 1.0, and chroma 1.168924.
     * It can be represented as a packed float with the constant {@code -0x1.9bff8p125F}.
     * <pre>
     * <font style='background-color: #520FE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #520FE0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #520FE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #520FE0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #520FE0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #520FE0'>&nbsp;@&nbsp;</font><font style='background-color: #520FE0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #520FE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #520FE0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float INDIGO = -0x1.9bff8p125F;
    static { NAMED.put("indigo", -0x1.9bff8p125F); LIST.add(-0x1.9bff8p125F); }

    /**
     * This color constant "violet" has RGBA8888 code {@code 9040EFFF}, H 0.77254903, S 0.88235295, L 0.43137255, alpha 1.0, and chroma 1.1538498.
     * It can be represented as a packed float with the constant {@code -0x1.ddc38ap125F}.
     * <pre>
     * <font style='background-color: #9040EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9040EF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9040EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9040EF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9040EF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9040EF'>&nbsp;@&nbsp;</font><font style='background-color: #9040EF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9040EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9040EF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIOLET = -0x1.ddc38ap125F;
    static { NAMED.put("violet", -0x1.ddc38ap125F); LIST.add(-0x1.ddc38ap125F); }

    /**
     * This color constant "purple" has RGBA8888 code {@code C000FFFF}, H 0.8039216, S 1.0, L 0.46666667, alpha 1.0, and chroma 1.3237739.
     * It can be represented as a packed float with the constant {@code -0x1.efff9ap125F}.
     * <pre>
     * <font style='background-color: #C000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C000FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C000FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C000FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C000FF'>&nbsp;@&nbsp;</font><font style='background-color: #C000FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C000FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURPLE = -0x1.efff9ap125F;
    static { NAMED.put("purple", -0x1.efff9ap125F); LIST.add(-0x1.efff9ap125F); }

    /**
     * This color constant "brown" has RGBA8888 code {@code 8F573BFF}, H 0.08235294, S 0.68235296, L 0.39607844, alpha 1.0, and chroma 0.54019076.
     * It can be represented as a packed float with the constant {@code -0x1.cb5c2ap125F}.
     * <pre>
     * <font style='background-color: #8F573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F573B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F573B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F573B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F573B'>&nbsp;@&nbsp;</font><font style='background-color: #8F573B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F573B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BROWN = -0x1.cb5c2ap125F;
    static { NAMED.put("brown", -0x1.cb5c2ap125F); LIST.add(-0x1.cb5c2ap125F); }

    /**
     * This color constant "pink" has RGBA8888 code {@code FFA0E0FF}, H 0.9098039, S 0.8784314, L 0.74509805, alpha 1.0, and chroma 0.5448131.
     * It can be represented as a packed float with the constant {@code -0x1.7dc1dp126F}.
     * <pre>
     * <font style='background-color: #FFA0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA0E0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFA0E0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFA0E0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFA0E0'>&nbsp;@&nbsp;</font><font style='background-color: #FFA0E0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA0E0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINK = -0x1.7dc1dp126F;
    static { NAMED.put("pink", -0x1.7dc1dp126F); LIST.add(-0x1.7dc1dp126F); }

    /**
     * This color constant "magenta" has RGBA8888 code {@code F500F5FF}, H 0.85490197, S 1.0, L 0.54509807, alpha 1.0, and chroma 1.3216946.
     * It can be represented as a packed float with the constant {@code -0x1.17ffb4p126F}.
     * <pre>
     * <font style='background-color: #F500F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F500F5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F500F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F500F5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F500F5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F500F5'>&nbsp;@&nbsp;</font><font style='background-color: #F500F5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F500F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F500F5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MAGENTA = -0x1.17ffb4p126F;
    static { NAMED.put("magenta", -0x1.17ffb4p126F); LIST.add(-0x1.17ffb4p126F); }

    /**
     * This color constant "brick" has RGBA8888 code {@code D5524AFF}, H 0.039215688, S 0.6862745, L 0.49019608, alpha 1.0, and chroma 1.1089423.
     * It can be represented as a packed float with the constant {@code -0x1.fb5e14p125F}.
     * <pre>
     * <font style='background-color: #D5524A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5524A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5524A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D5524A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D5524A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D5524A'>&nbsp;@&nbsp;</font><font style='background-color: #D5524A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5524A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5524A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRICK = -0x1.fb5e14p125F;
    static { NAMED.put("brick", -0x1.fb5e14p125F); LIST.add(-0x1.fb5e14p125F); }

    /**
     * This color constant "ember" has RGBA8888 code {@code F55A32FF}, H 0.050980393, S 0.9254902, L 0.5529412, alpha 1.0, and chroma 1.4069544.
     * It can be represented as a packed float with the constant {@code -0x1.1bd81ap126F}.
     * <pre>
     * <font style='background-color: #F55A32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F55A32; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F55A32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F55A32'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F55A32'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F55A32'>&nbsp;@&nbsp;</font><font style='background-color: #F55A32; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F55A32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F55A32; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float EMBER = -0x1.1bd81ap126F;
    static { NAMED.put("ember", -0x1.1bd81ap126F); LIST.add(-0x1.1bd81ap126F); }

    /**
     * This color constant "salmon" has RGBA8888 code {@code FF6262FF}, H 0.03137255, S 0.8627451, L 0.5882353, alpha 1.0, and chroma 1.0399684.
     * It can be represented as a packed float with the constant {@code -0x1.2db81p126F}.
     * <pre>
     * <font style='background-color: #FF6262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6262; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF6262'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF6262'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF6262'>&nbsp;@&nbsp;</font><font style='background-color: #FF6262; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6262; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SALMON = -0x1.2db81p126F;
    static { NAMED.put("salmon", -0x1.2db81p126F); LIST.add(-0x1.2db81p126F); }

    /**
     * This color constant "chocolate" has RGBA8888 code {@code 683818FF}, H 0.08627451, S 0.89411765, L 0.26666668, alpha 1.0, and chroma 0.46269765.
     * It can be represented as a packed float with the constant {@code -0x1.89c82cp125F}.
     * <pre>
     * <font style='background-color: #683818;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #683818; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #683818;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #683818'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #683818'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #683818'>&nbsp;@&nbsp;</font><font style='background-color: #683818; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #683818;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #683818; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CHOCOLATE = -0x1.89c82cp125F;
    static { NAMED.put("chocolate", -0x1.89c82cp125F); LIST.add(-0x1.89c82cp125F); }

    /**
     * This color constant "tan" has RGBA8888 code {@code D2B48CFF}, H 0.15294118, S 0.43529412, L 0.72156864, alpha 1.0, and chroma 0.40746132.
     * It can be represented as a packed float with the constant {@code -0x1.70de4ep126F}.
     * <pre>
     * <font style='background-color: #D2B48C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2B48C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2B48C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D2B48C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D2B48C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D2B48C'>&nbsp;@&nbsp;</font><font style='background-color: #D2B48C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2B48C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2B48C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TAN = -0x1.70de4ep126F;
    static { NAMED.put("tan", -0x1.70de4ep126F); LIST.add(-0x1.70de4ep126F); }

    /**
     * This color constant "bronze" has RGBA8888 code {@code CE8E31FF}, H 0.12941177, S 0.94509804, L 0.6039216, alpha 1.0, and chroma 0.82591474.
     * It can be represented as a packed float with the constant {@code -0x1.35e242p126F}.
     * <pre>
     * <font style='background-color: #CE8E31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CE8E31; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CE8E31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CE8E31'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CE8E31'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CE8E31'>&nbsp;@&nbsp;</font><font style='background-color: #CE8E31; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CE8E31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CE8E31; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRONZE = -0x1.35e242p126F;
    static { NAMED.put("bronze", -0x1.35e242p126F); LIST.add(-0x1.35e242p126F); }

    /**
     * This color constant "cinnamon" has RGBA8888 code {@code D2691DFF}, H 0.078431375, S 1.0, L 0.5254902, alpha 1.0, and chroma 1.0730556.
     * It can be represented as a packed float with the constant {@code -0x1.0dfe28p126F}.
     * <pre>
     * <font style='background-color: #D2691D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2691D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2691D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D2691D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D2691D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D2691D'>&nbsp;@&nbsp;</font><font style='background-color: #D2691D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2691D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2691D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CINNAMON = -0x1.0dfe28p126F;
    static { NAMED.put("cinnamon", -0x1.0dfe28p126F); LIST.add(-0x1.0dfe28p126F); }

    /**
     * This color constant "apricot" has RGBA8888 code {@code FFA828FF}, H 0.12156863, S 0.99215686, L 0.7294118, alpha 1.0, and chroma 0.96643335.
     * It can be represented as a packed float with the constant {@code -0x1.75fa3ep126F}.
     * <pre>
     * <font style='background-color: #FFA828;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA828; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA828;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFA828'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFA828'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFA828'>&nbsp;@&nbsp;</font><font style='background-color: #FFA828; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA828;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA828; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float APRICOT = -0x1.75fa3ep126F;
    static { NAMED.put("apricot", -0x1.75fa3ep126F); LIST.add(-0x1.75fa3ep126F); }

    /**
     * This color constant "peach" has RGBA8888 code {@code FFBF81FF}, H 0.1254902, S 0.84705883, L 0.79607844, alpha 1.0, and chroma 0.56687677.
     * It can be represented as a packed float with the constant {@code -0x1.97b04p126F}.
     * <pre>
     * <font style='background-color: #FFBF81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFBF81; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFBF81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFBF81'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFBF81'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFBF81'>&nbsp;@&nbsp;</font><font style='background-color: #FFBF81; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFBF81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFBF81; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PEACH = -0x1.97b04p126F;
    static { NAMED.put("peach", -0x1.97b04p126F); LIST.add(-0x1.97b04p126F); }

    /**
     * This color constant "pear" has RGBA8888 code {@code D3E330FF}, H 0.25490198, S 0.972549, L 0.84705883, alpha 1.0, and chroma 0.9383476.
     * It can be represented as a packed float with the constant {@code -0x1.b1f082p126F}.
     * <pre>
     * <font style='background-color: #D3E330;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3E330; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3E330;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D3E330'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D3E330'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D3E330'>&nbsp;@&nbsp;</font><font style='background-color: #D3E330; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3E330;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3E330; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PEAR = -0x1.b1f082p126F;
    static { NAMED.put("pear", -0x1.b1f082p126F); LIST.add(-0x1.b1f082p126F); }

    /**
     * This color constant "saffron" has RGBA8888 code {@code FFD510FF}, H 0.1882353, S 1.0, L 0.84705883, alpha 1.0, and chroma 0.9591871.
     * It can be represented as a packed float with the constant {@code -0x1.b1fe6p126F}.
     * <pre>
     * <font style='background-color: #FFD510;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD510; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD510;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFD510'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFD510'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFD510'>&nbsp;@&nbsp;</font><font style='background-color: #FFD510; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD510;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD510; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SAFFRON = -0x1.b1fe6p126F;
    static { NAMED.put("saffron", -0x1.b1fe6p126F); LIST.add(-0x1.b1fe6p126F); }

    /**
     * This color constant "butter" has RGBA8888 code {@code FFF288FF}, H 0.21960784, S 0.83137256, L 0.9372549, alpha 1.0, and chroma 0.5894948.
     * It can be represented as a packed float with the constant {@code -0x1.dfa87p126F}.
     * <pre>
     * <font style='background-color: #FFF288;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFF288; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFF288;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFF288'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFF288'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFF288'>&nbsp;@&nbsp;</font><font style='background-color: #FFF288; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFF288;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFF288; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BUTTER = -0x1.dfa87p126F;
    static { NAMED.put("butter", -0x1.dfa87p126F); LIST.add(-0x1.dfa87p126F); }

    /**
     * This color constant "chartreuse" has RGBA8888 code {@code C8FF41FF}, H 0.2901961, S 0.9372549, L 0.9254902, alpha 1.0, and chroma 0.98001146.
     * It can be represented as a packed float with the constant {@code -0x1.d9de94p126F}.
     * <pre>
     * <font style='background-color: #C8FF41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8FF41; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8FF41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C8FF41'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C8FF41'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C8FF41'>&nbsp;@&nbsp;</font><font style='background-color: #C8FF41; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8FF41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8FF41; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CHARTREUSE = -0x1.d9de94p126F;
    static { NAMED.put("chartreuse", -0x1.d9de94p126F); LIST.add(-0x1.d9de94p126F); }

    /**
     * This color constant "cactus" has RGBA8888 code {@code 30A000FF}, H 0.34509805, S 1.0, L 0.5411765, alpha 1.0, and chroma 0.8431733.
     * It can be represented as a packed float with the constant {@code -0x1.15febp126F}.
     * <pre>
     * <font style='background-color: #30A000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #30A000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #30A000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #30A000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #30A000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #30A000'>&nbsp;@&nbsp;</font><font style='background-color: #30A000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #30A000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #30A000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CACTUS = -0x1.15febp126F;
    static { NAMED.put("cactus", -0x1.15febp126F); LIST.add(-0x1.15febp126F); }

    /**
     * This color constant "lime" has RGBA8888 code {@code 93D300FF}, H 0.30588236, S 1.0, L 0.7490196, alpha 1.0, and chroma 0.95990366.
     * It can be represented as a packed float with the constant {@code -0x1.7ffe9cp126F}.
     * <pre>
     * <font style='background-color: #93D300;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #93D300; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #93D300;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #93D300'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #93D300'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #93D300'>&nbsp;@&nbsp;</font><font style='background-color: #93D300; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #93D300;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #93D300; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIME = -0x1.7ffe9cp126F;
    static { NAMED.put("lime", -0x1.7ffe9cp126F); LIST.add(-0x1.7ffe9cp126F); }

    /**
     * This color constant "olive" has RGBA8888 code {@code 818000FF}, H 0.23529412, S 1.0, L 0.48235294, alpha 1.0, and chroma 0.57032084.
     * It can be represented as a packed float with the constant {@code -0x1.f7fe78p125F}.
     * <pre>
     * <font style='background-color: #818000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #818000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #818000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #818000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #818000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #818000'>&nbsp;@&nbsp;</font><font style='background-color: #818000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #818000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #818000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float OLIVE = -0x1.f7fe78p125F;
    static { NAMED.put("olive", -0x1.f7fe78p125F); LIST.add(-0x1.f7fe78p125F); }

    /**
     * This color constant "fern" has RGBA8888 code {@code 4E7942FF}, H 0.3372549, S 0.65882355, L 0.43137255, alpha 1.0, and chroma 0.43006435.
     * It can be represented as a packed float with the constant {@code -0x1.dd50acp125F}.
     * <pre>
     * <font style='background-color: #4E7942;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E7942; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E7942;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4E7942'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4E7942'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4E7942'>&nbsp;@&nbsp;</font><font style='background-color: #4E7942; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E7942;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E7942; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FERN = -0x1.dd50acp125F;
    static { NAMED.put("fern", -0x1.dd50acp125F); LIST.add(-0x1.dd50acp125F); }

    /**
     * This color constant "moss" has RGBA8888 code {@code 204608FF}, H 0.33333334, S 1.0, L 0.23921569, alpha 1.0, and chroma 0.35648987.
     * It can be represented as a packed float with the constant {@code -0x1.7bfeaap125F}.
     * <pre>
     * <font style='background-color: #204608;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #204608; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #204608;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #204608'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #204608'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #204608'>&nbsp;@&nbsp;</font><font style='background-color: #204608; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #204608;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #204608; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MOSS = -0x1.7bfeaap125F;
    static { NAMED.put("moss", -0x1.7bfeaap125F); LIST.add(-0x1.7bfeaap125F); }

    /**
     * This color constant "celery" has RGBA8888 code {@code 7DFF73FF}, H 0.34901962, S 0.8352941, L 0.8901961, alpha 1.0, and chroma 0.8465463.
     * It can be represented as a packed float with the constant {@code -0x1.c7aab2p126F}.
     * <pre>
     * <font style='background-color: #7DFF73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7DFF73; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7DFF73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7DFF73'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7DFF73'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7DFF73'>&nbsp;@&nbsp;</font><font style='background-color: #7DFF73; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7DFF73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7DFF73; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CELERY = -0x1.c7aab2p126F;
    static { NAMED.put("celery", -0x1.c7aab2p126F); LIST.add(-0x1.c7aab2p126F); }

    /**
     * This color constant "sage" has RGBA8888 code {@code ABE3C5FF}, H 0.4117647, S 0.3882353, L 0.8392157, alpha 1.0, and chroma 0.33734718.
     * It can be represented as a packed float with the constant {@code -0x1.acc6d2p126F}.
     * <pre>
     * <font style='background-color: #ABE3C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABE3C5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABE3C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ABE3C5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ABE3C5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ABE3C5'>&nbsp;@&nbsp;</font><font style='background-color: #ABE3C5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABE3C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABE3C5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SAGE = -0x1.acc6d2p126F;
    static { NAMED.put("sage", -0x1.acc6d2p126F); LIST.add(-0x1.acc6d2p126F); }

    /**
     * This color constant "jade" has RGBA8888 code {@code 3FBF3FFF}, H 0.3529412, S 0.89411765, L 0.6509804, alpha 1.0, and chroma 0.9355735.
     * It can be represented as a packed float with the constant {@code -0x1.4dc8b4p126F}.
     * <pre>
     * <font style='background-color: #3FBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3FBF3F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3FBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3FBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3FBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3FBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #3FBF3F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3FBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3FBF3F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float JADE = -0x1.4dc8b4p126F;
    static { NAMED.put("jade", -0x1.4dc8b4p126F); LIST.add(-0x1.4dc8b4p126F); }

    /**
     * This color constant "cyan" has RGBA8888 code {@code 00FFFFFF}, H 0.53333336, S 1.0, L 0.8980392, alpha 1.0, and chroma 0.716028.
     * It can be represented as a packed float with the constant {@code -0x1.cbff1p126F}.
     * <pre>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #00FFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CYAN = -0x1.cbff1p126F;
    static { NAMED.put("cyan", -0x1.cbff1p126F); LIST.add(-0x1.cbff1p126F); }

    /**
     * This color constant "mint" has RGBA8888 code {@code 7FFFD4FF}, H 0.4392157, S 0.8509804, L 0.9098039, alpha 1.0, and chroma 0.5010977.
     * It can be represented as a packed float with the constant {@code -0x1.d1b2ep126F}.
     * <pre>
     * <font style='background-color: #7FFFD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7FFFD4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7FFFD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7FFFD4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7FFFD4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7FFFD4'>&nbsp;@&nbsp;</font><font style='background-color: #7FFFD4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7FFFD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7FFFD4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MINT = -0x1.d1b2ep126F;
    static { NAMED.put("mint", -0x1.d1b2ep126F); LIST.add(-0x1.d1b2ep126F); }

    /**
     * This color constant "teal" has RGBA8888 code {@code 007F7FFF}, H 0.53333336, S 1.0, L 0.44313726, alpha 1.0, and chroma 0.37768868.
     * It can be represented as a packed float with the constant {@code -0x1.e3ff1p125F}.
     * <pre>
     * <font style='background-color: #007F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007F7F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #007F7F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #007F7F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #007F7F'>&nbsp;@&nbsp;</font><font style='background-color: #007F7F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007F7F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TEAL = -0x1.e3ff1p125F;
    static { NAMED.put("teal", -0x1.e3ff1p125F); LIST.add(-0x1.e3ff1p125F); }

    /**
     * This color constant "turquoise" has RGBA8888 code {@code 2ED6C9FF}, H 0.5058824, S 0.9843137, L 0.7529412, alpha 1.0, and chroma 0.6077147.
     * It can be represented as a packed float with the constant {@code -0x1.81f702p126F}.
     * <pre>
     * <font style='background-color: #2ED6C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2ED6C9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2ED6C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2ED6C9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2ED6C9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2ED6C9'>&nbsp;@&nbsp;</font><font style='background-color: #2ED6C9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2ED6C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2ED6C9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TURQUOISE = -0x1.81f702p126F;
    static { NAMED.put("turquoise", -0x1.81f702p126F); LIST.add(-0x1.81f702p126F); }

    /**
     * This color constant "sky" has RGBA8888 code {@code 10C0E0FF}, H 0.6039216, S 1.0, L 0.6862745, alpha 1.0, and chroma 0.65476733.
     * It can be represented as a packed float with the constant {@code -0x1.5fff34p126F}.
     * <pre>
     * <font style='background-color: #10C0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #10C0E0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #10C0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #10C0E0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #10C0E0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #10C0E0'>&nbsp;@&nbsp;</font><font style='background-color: #10C0E0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #10C0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #10C0E0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SKY = -0x1.5fff34p126F;
    static { NAMED.put("sky", -0x1.5fff34p126F); LIST.add(-0x1.5fff34p126F); }

    /**
     * This color constant "cobalt" has RGBA8888 code {@code 0046ABFF}, H 0.72156864, S 1.0, L 0.29803923, alpha 1.0, and chroma 0.858981.
     * It can be represented as a packed float with the constant {@code -0x1.99ff7p125F}.
     * <pre>
     * <font style='background-color: #0046AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0046AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0046AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0046AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0046AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0046AB'>&nbsp;@&nbsp;</font><font style='background-color: #0046AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0046AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0046AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float COBALT = -0x1.99ff7p125F;
    static { NAMED.put("cobalt", -0x1.99ff7p125F); LIST.add(-0x1.99ff7p125F); }

    /**
     * This color constant "denim" has RGBA8888 code {@code 3088B8FF}, H 0.65882355, S 0.9490196, L 0.5019608, alpha 1.0, and chroma 0.62615967.
     * It can be represented as a packed float with the constant {@code -0x1.01e55p126F}.
     * <pre>
     * <font style='background-color: #3088B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3088B8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3088B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3088B8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3088B8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3088B8'>&nbsp;@&nbsp;</font><font style='background-color: #3088B8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3088B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3088B8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DENIM = -0x1.01e55p126F;
    static { NAMED.put("denim", -0x1.01e55p126F); LIST.add(-0x1.01e55p126F); }

    /**
     * This color constant "navy" has RGBA8888 code {@code 000080FF}, H 0.7411765, S 1.0, L 0.1254902, alpha 1.0, and chroma 0.51146966.
     * It can be represented as a packed float with the constant {@code -0x1.41ff7ap125F}.
     * <pre>
     * <font style='background-color: #000080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000080; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000080'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000080'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000080'>&nbsp;@&nbsp;</font><font style='background-color: #000080; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000080; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float NAVY = -0x1.41ff7ap125F;
    static { NAMED.put("navy", -0x1.41ff7ap125F); LIST.add(-0x1.41ff7ap125F); }

    /**
     * This color constant "lavender" has RGBA8888 code {@code B991FFFF}, H 0.77254903, S 0.9098039, L 0.64705884, alpha 1.0, and chroma 0.7725085.
     * It can be represented as a packed float with the constant {@code -0x1.4bd18ap126F}.
     * <pre>
     * <font style='background-color: #B991FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B991FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B991FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B991FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B991FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B991FF'>&nbsp;@&nbsp;</font><font style='background-color: #B991FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B991FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B991FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LAVENDER = -0x1.4bd18ap126F;
    static { NAMED.put("lavender", -0x1.4bd18ap126F); LIST.add(-0x1.4bd18ap126F); }

    /**
     * This color constant "plum" has RGBA8888 code {@code BE0DC6FF}, H 0.84705883, S 1.0, L 0.42745098, alpha 1.0, and chroma 1.0652746.
     * It can be represented as a packed float with the constant {@code -0x1.dbffbp125F}.
     * <pre>
     * <font style='background-color: #BE0DC6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE0DC6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE0DC6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BE0DC6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BE0DC6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BE0DC6'>&nbsp;@&nbsp;</font><font style='background-color: #BE0DC6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE0DC6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE0DC6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PLUM = -0x1.dbffbp125F;
    static { NAMED.put("plum", -0x1.dbffbp125F); LIST.add(-0x1.dbffbp125F); }

    /**
     * This color constant "mauve" has RGBA8888 code {@code AB73ABFF}, H 0.85490197, S 0.38039216, L 0.5254902, alpha 1.0, and chroma 0.48581037.
     * It can be represented as a packed float with the constant {@code -0x1.0cc3b4p126F}.
     * <pre>
     * <font style='background-color: #AB73AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB73AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB73AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB73AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB73AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB73AB'>&nbsp;@&nbsp;</font><font style='background-color: #AB73AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB73AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB73AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MAUVE = -0x1.0cc3b4p126F;
    static { NAMED.put("mauve", -0x1.0cc3b4p126F); LIST.add(-0x1.0cc3b4p126F); }

    /**
     * This color constant "rose" has RGBA8888 code {@code E61E78FF}, H 0.9882353, S 1.0, L 0.4745098, alpha 1.0, and chroma 1.3330481.
     * It can be represented as a packed float with the constant {@code -0x1.f3fff8p125F}.
     * <pre>
     * <font style='background-color: #E61E78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E61E78; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E61E78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E61E78'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E61E78'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E61E78'>&nbsp;@&nbsp;</font><font style='background-color: #E61E78; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E61E78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E61E78; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ROSE = -0x1.f3fff8p125F;
    static { NAMED.put("rose", -0x1.f3fff8p125F); LIST.add(-0x1.f3fff8p125F); }

    /**
     * This color constant "raspberry" has RGBA8888 code {@code 911437FF}, H 0.007843138, S 0.972549, L 0.28627452, alpha 1.0, and chroma 0.86503017.
     * It can be represented as a packed float with the constant {@code -0x1.93f004p125F}.
     * <pre>
     * <font style='background-color: #911437;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #911437; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #911437;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #911437'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #911437'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #911437'>&nbsp;@&nbsp;</font><font style='background-color: #911437; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #911437;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #911437; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float RASPBERRY = -0x1.93f004p125F;
    static { NAMED.put("raspberry", -0x1.93f004p125F); LIST.add(-0x1.93f004p125F); }

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
     * The packed Oklab float colors that correspond to items in {@link #NAMES_BY_HUE}, with the same order.
     */
    public static final FloatList COLORS_BY_HUE = new FloatList(NAMES_BY_HUE.size());
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
        for(String name : NAMES_BY_HUE) {
            COLORS_BY_HUE.add(NAMED.get(name));
        }
    }

    private static final FloatList mixing = new FloatList(4);

    /**
     * Parses a color description and returns the approximate color it describes, as a packed float color.
     * Color descriptions consist of one or more lower-case words, separated by non-alphabetical characters (typically
     * spaces and/or hyphens). Any word that is the name of a color in this SimplePalette will be looked up in
     * {@link #NAMED} and tracked; if there is more than one of these color name words, the colors will be mixed using
     * {@link ColorTools#mix(float[], int, int)}, or if there is just one color name word, then the corresponding color
     * will be used. The special adjectives "light" and "dark" change the intensity of the described color; likewise,
     * "rich" and "dull" change the saturation (the difference of the chromatic channels from grayscale). All of these
     * adjectives can have "-er" or "-est" appended to make their effect twice or three times as strong. Technically,
     * the chars appended to an adjective don't matter, only their count, so "lightaa" is the same as "lighter" and
     * "richcat" is the same as "richest". There's an unofficial fourth level as well, used when any 4 characters are
     * appended to an adjective (as in "darkmost"); it has four times the effect of the original adjective. If a color
     * name or adjective is invalid, it is considered the same as adding the color {@link #TRANSPARENT}.
     * <br>
     * Examples of valid descriptions include "blue", "dark green", "duller red", "peach pink", "indigo purple mauve",
     * and "lightest richer apricot-olive".
     * @param description a color description, as a lower-case String matching the above format
     * @return a packed float color as described
     */
    public static float parseDescription(final String description) {
        float intensity = 0f, saturation = 0f;
        final String[] terms = description.split("[^a-zA-Z]+");
        mixing.clear();
        for(String term : terms) {
            if (term == null || term.isEmpty()) continue;
            final int len = term.length();
            switch (term.charAt(0)) {
                case 'l':
                    if (len > 2 && term.charAt(2) == 'g') {
                        switch (len) {
                            case 9:
                                intensity += 0.125f;
                            case 8:
                                intensity += 0.125f;
                            case 7:
                                intensity += 0.125f;
                            case 5:
                                intensity += 0.125f;
                                break;
                            default:
                                mixing.add(TRANSPARENT);
                                break;
                        }
                    } else {
                        mixing.add(NAMED.get(term));
                    }
                    break;
                case 'r':
                    if (len > 1 && term.charAt(1) == 'i') {
                        switch (len) {
                            case 8:
                                saturation += 0.2f;
                            case 7:
                                saturation += 0.2f;
                            case 6:
                                saturation += 0.2f;
                            case 4:
                                saturation += 0.2f;
                                break;
                            default:
                                mixing.add(0f);
                                break;
                        }
                    } else {
                        mixing.add(NAMED.get(term));
                    }
                    break;
                case 'd':
                    if (len > 1 && term.charAt(1) == 'a') {
                        switch (len) {
                            case 8:
                                intensity -= 0.15f;
                            case 7:
                                intensity -= 0.15f;
                            case 6:
                                intensity -= 0.15f;
                            case 4:
                                intensity -= 0.15f;
                                break;
                            default:
                                mixing.add(0f);
                                break;
                        }
                    } else if (len > 1 && term.charAt(1) == 'u') {
                        switch (len) {
                            case 8:
                                saturation -= 0.2f;
                            case 7:
                                saturation -= 0.2f;
                            case 6:
                                saturation -= 0.2f;
                            case 4:
                                saturation -= 0.2f;
                                break;
                            default:
                                mixing.add(0f);
                                break;
                        }
                    } else {
                        mixing.add(NAMED.get(term));
                    }
                    break;
                default:
                    mixing.add(NAMED.get(term));
                    break;
            }
        }
        float result = mix(mixing.items, 0, mixing.size());
        if(result == 0f) return result;

        if(intensity > 0) result = ColorTools.lighten(result, intensity);
        else if(intensity < 0) result = ColorTools.darken(result, -intensity);

        if(saturation > 0) result = (ColorTools.enrich(result, saturation));
        else if(saturation < 0) result = (ColorTools.dullen(result, -saturation));

        return result;
    }

    private static final ObjectList<String> namesByHue = new ObjectList<>(NAMES_BY_HUE);
    private static final FloatList colorsByHue = new FloatList(COLORS_BY_HUE);
    static {
        int trn = namesByHue.indexOf("transparent");
        namesByHue.removeAt(trn);
        colorsByHue.removeAt(trn);
        ALIASES.put("grey", GRAY);
        ALIASES.put("gold", SAFFRON);
        ALIASES.put("puce", MAUVE);
        ALIASES.put("sand", TAN);
        ALIASES.put("skin", PEACH); // Yes, I am aware that there is more than one skin color, but this can only map to one.
        ALIASES.put("coral", SALMON);
        ALIASES.put("azure", SKY);
        ALIASES.put("ocean", TEAL);
        ALIASES.put("sapphire", COBALT);
        NAMED.putAll(ALIASES);
    }
    /**
     * Given a color as a packed HSLuv float, this finds the closest description it can to match the given color while
     * using at most {@code mixCount} colors to mix in. You should only use small numbers for mixCount, like 1 to 3;
     * this can take quite a while to run otherwise. This returns a String description that can be passed to
     * {@link #parseDescription(String)}. It is likely that this will use very contrasting colors if mixCount is 2 or
     * greater and the color to match is desaturated or brownish.
     * @param hsluv a packed HSLuv float color to attempt to match
     * @param mixCount how many color names this will use in the returned description
     * @return a description that can be fed to {@link #parseDescription(String)} to get a similar color
     */
    public static String bestMatch(final float hsluv, int mixCount) {
        mixCount = Math.max(1, mixCount);
        float bestDistance = Float.POSITIVE_INFINITY;
        final int paletteSize = namesByHue.size(), colorTries = (int)Math.pow(paletteSize, mixCount), totalTries = colorTries * 81;
        final float targetH = ColorTools.channelH(hsluv), targetS = ColorTools.channelS(hsluv), targetL = ColorTools.channelL(hsluv);
        final String[] lightAdjectives = {"darkmost ", "darkest ", "darker ", "dark ", "", "light ", "lighter ", "lightest ", "lightmost "};
        final String[] satAdjectives = {"dullmost ", "dullest ", "duller ", "dull ", "", "rich ", "richer ", "richest ", "richmost "};
        mixing.clear();
        for (int i = 0; i < mixCount; i++) {
            mixing.add(colorsByHue.get(0));
        }
        int bestCode = 0;
        for (int c = 0; c < totalTries; c++) {
            for (int i = 0, e = 1; i < mixCount; i++, e *= paletteSize) {
                mixing.set(i, colorsByHue.get((c / e) % paletteSize));
            }
            int idxI = ((c / colorTries) % 9 - 4), idxS = (c / (colorTries * 9) - 4);

            float result = mix(mixing.items, 0, mixCount);
            if(idxI > 0) result = ColorTools.lighten(result, 0.125f * idxI);
            else if(idxI < 0) result = ColorTools.darken(result, -0.15f * idxI);

            if(idxS > 0) result = ColorTools.limitToGamut(ColorTools.enrich(result, 0.2f * idxS));
            else if(idxS < 0) result = ColorTools.dullen(result, -0.2f * idxS);

            float dH = Math.abs(ColorTools.channelH(result) - targetH), dS = ColorTools.channelS(result) - targetS, dL = ColorTools.channelL(result) - targetL;
            if(dH > 0.5f) dH = 1f - dH;
            if(bestDistance > (bestDistance = Math.min(dH * dH + dS * dS + dL * dL, bestDistance)))
                bestCode = c;
        }

        StringBuilder description = new StringBuilder(lightAdjectives[(bestCode / colorTries) % 9] + satAdjectives[bestCode / (colorTries * 9)]);
        for (int i = 0, e = 1; i < mixCount; e *= paletteSize) {
            description.append(namesByHue.get((bestCode / e) % paletteSize));
            if(++i < mixCount)
                description.append(' ');
        }
        return description.toString();
    }
}
