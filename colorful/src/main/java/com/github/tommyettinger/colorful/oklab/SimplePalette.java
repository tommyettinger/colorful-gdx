package com.github.tommyettinger.colorful.oklab;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.ObjectFloatMap;
import com.github.tommyettinger.colorful.FloatColors;

import java.util.Comparator;

import static com.github.tommyettinger.colorful.oklab.ColorTools.*;

/**
 * A palette of predefined colors as packed Oklab floats, the kind {@link ColorTools} works with, plus a way to describe
 * colors by combinations and adjustments. The description code is probably what you would use this class for; it
 * revolves around {@link #parseDescription(String)}, which takes a color description String and returns a packed float
 * color. The color descriptions look like "darker rich mint yellow", where the order of the words doesn't matter. They
 * can optionally include lightness changes (light/dark), and saturation changes (rich/dull), and must include one or
 * more color names that will be mixed together (repeats are allowed to use a color more heavily). The changes can be
 * suffixed with "er", "est", or "most", such as "duller", "lightest", or "richmost", to progressively increase their
 * effect on lightness or saturation.
 * <br>
 * The rest of this is about the same as in {@link Palette}.
 * <br>
 * You can access colors by their constant name, such as {@code CACTUS}, by the {@link #NAMED} map using
 * {@code NAMED.get("cactus", 0f)}, or by index in the FloatArray called {@link #LIST}. Note that to access a float
 * color from NAMED, you need to give a default value if the name is not found; {@code 0f} is a good default because it
 * will not occur in a valid Oklab color. You can access the names in a specific order with {@link #NAMES} (which is
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
    public static final ObjectFloatMap<String> NAMED = new ObjectFloatMap<String>(50);
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
    public static final ObjectFloatMap<String> ALIASES = new ObjectFloatMap<String>(20);
    /**
     * Lists the packed float color values in this, in no particular order. Does not include duplicates from aliases.
     */
    public static final FloatArray LIST = new FloatArray(50);


    /**
     * This color constant "transparent" has RGBA8888 code {@code 00000000}, L 0.0, A 0.49803922, B 0.49803922, alpha 0.0, hue 0.625, saturation 0.0, and chroma 0.0055242716.
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
     * This color constant "black" has RGBA8888 code {@code 000000FF}, L 0.0, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, saturation 0.0, and chroma 0.0055242716.
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
     * This color constant "gray" has RGBA8888 code {@code 808080FF}, L 0.5137255, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, saturation 0.0016803193, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.feff06p125F}.
     * <pre>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #808080; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #808080; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #808080; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY = -0x1.feff06p125F;
    static { NAMED.put("gray", -0x1.feff06p125F); LIST.add(-0x1.feff06p125F); }

    /**
     * This color constant "silver" has RGBA8888 code {@code B6B6B6FF}, L 0.7176471, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, saturation 0.0013493, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.feff6ep125F}.
     * <pre>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B6B6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #B6B6B6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B6B6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER = -0x1.feff6ep125F;
    static { NAMED.put("silver", -0x1.feff6ep125F); LIST.add(-0x1.feff6ep125F); }

    /**
     * This color constant "white" has RGBA8888 code {@code FFFFFFFF}, L 1.0, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.fefffep125F}.
     * <pre>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #FFFFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE = -0x1.fefffep125F;
    static { NAMED.put("white", -0x1.fefffep125F); LIST.add(-0x1.fefffep125F); }

    /**
     * This color constant "red" has RGBA8888 code {@code FF0000FF}, L 0.50980395, A 0.6117647, B 0.56078434, alpha 1.0, hue 0.07928106, saturation 0.8972241, and chroma 0.25345513.
     * It can be represented as a packed float with the constant {@code -0x1.1f3904p126F}.
     * <pre>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #FF0000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float RED = -0x1.1f3904p126F;
    static { NAMED.put("red", -0x1.1f3904p126F); LIST.add(-0x1.1f3904p126F); }

    /**
     * This color constant "orange" has RGBA8888 code {@code FF7F00FF}, L 0.6509804, A 0.54901963, B 0.57254905, alpha 1.0, hue 0.15541562, saturation 0.8137945, and chroma 0.17443058.
     * It can be represented as a packed float with the constant {@code -0x1.25194cp126F}.
     * <pre>
     * <font style='background-color: #FF7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF7F00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF7F00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF7F00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF7F00'>&nbsp;@&nbsp;</font><font style='background-color: #FF7F00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF7F00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ORANGE = -0x1.25194cp126F;
    static { NAMED.put("orange", -0x1.25194cp126F); LIST.add(-0x1.25194cp126F); }

    /**
     * This color constant "yellow" has RGBA8888 code {@code FFFF00FF}, L 0.92941177, A 0.4627451, B 0.59607846, alpha 1.0, hue 0.30886024, saturation 0.92988807, and chroma 0.20529193.
     * It can be represented as a packed float with the constant {@code -0x1.30eddap126F}.
     * <pre>
     * <font style='background-color: #FFFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFF00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFF00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFF00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFF00'>&nbsp;@&nbsp;</font><font style='background-color: #FFFF00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFF00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float YELLOW = -0x1.30eddap126F;
    static { NAMED.put("yellow", -0x1.30eddap126F); LIST.add(-0x1.30eddap126F); }

    /**
     * This color constant "green" has RGBA8888 code {@code 00FF00FF}, L 0.8, A 0.38039216, B 0.5882353, alpha 1.0, hue 0.39883053, saturation 0.9818446, and chroma 0.29610303.
     * It can be represented as a packed float with the constant {@code -0x1.2cc398p126F}.
     * <pre>
     * <font style='background-color: #00FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FF00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FF00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FF00'>&nbsp;@&nbsp;</font><font style='background-color: #00FF00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GREEN = -0x1.2cc398p126F;
    static { NAMED.put("green", -0x1.2cc398p126F); LIST.add(-0x1.2cc398p126F); }

    /**
     * This color constant "blue" has RGBA8888 code {@code 0000FFFF}, L 0.30588236, A 0.48235294, B 0.34117648, alpha 1.0, hue 0.7323789, saturation 0.97586775, and chroma 0.31835338.
     * It can be represented as a packed float with the constant {@code -0x1.aef69cp125F}.
     * <pre>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0000FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #0000FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0000FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUE = -0x1.aef69cp125F;
    static { NAMED.put("blue", -0x1.aef69cp125F); LIST.add(-0x1.aef69cp125F); }

    /**
     * This color constant "indigo" has RGBA8888 code {@code 520FE0FF}, L 0.32156864, A 0.53333336, B 0.36862746, alpha 1.0, hue 0.7895446, saturation 0.9090131, and chroma 0.27001202.
     * It can be represented as a packed float with the constant {@code -0x1.bd10a4p125F}.
     * <pre>
     * <font style='background-color: #520FE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #520FE0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #520FE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #520FE0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #520FE0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #520FE0'>&nbsp;@&nbsp;</font><font style='background-color: #520FE0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #520FE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #520FE0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float INDIGO = -0x1.bd10a4p125F;
    static { NAMED.put("indigo", -0x1.bd10a4p125F); LIST.add(-0x1.bd10a4p125F); }

    /**
     * This color constant "violet" has RGBA8888 code {@code 9040EFFF}, L 0.4627451, A 0.56078434, B 0.39607844, alpha 1.0, hue 0.8342395, saturation 0.6792487, and chroma 0.23984502.
     * It can be represented as a packed float with the constant {@code -0x1.cb1eecp125F}.
     * <pre>
     * <font style='background-color: #9040EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9040EF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9040EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9040EF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9040EF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9040EF'>&nbsp;@&nbsp;</font><font style='background-color: #9040EF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9040EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9040EF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIOLET = -0x1.cb1eecp125F;
    static { NAMED.put("violet", -0x1.cb1eecp125F); LIST.add(-0x1.cb1eecp125F); }

    /**
     * This color constant "purple" has RGBA8888 code {@code C000FFFF}, L 0.49803922, A 0.60784316, B 0.3882353, alpha 1.0, hue 0.8721605, saturation 0.96817255, and chroma 0.30940855.
     * It can be represented as a packed float with the constant {@code -0x1.c736fep125F}.
     * <pre>
     * <font style='background-color: #C000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C000FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C000FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C000FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C000FF'>&nbsp;@&nbsp;</font><font style='background-color: #C000FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C000FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURPLE = -0x1.c736fep125F;
    static { NAMED.put("purple", -0x1.c736fep125F); LIST.add(-0x1.c736fep125F); }

    /**
     * This color constant "brown" has RGBA8888 code {@code 8F573BFF}, L 0.4117647, A 0.5254902, B 0.5294118, alpha 1.0, hue 0.13634071, saturation 0.24020728, and chroma 0.07753685.
     * It can be represented as a packed float with the constant {@code -0x1.0f0cd2p126F}.
     * <pre>
     * <font style='background-color: #8F573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F573B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F573B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F573B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F573B'>&nbsp;@&nbsp;</font><font style='background-color: #8F573B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F573B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BROWN = -0x1.0f0cd2p126F;
    static { NAMED.put("brown", -0x1.0f0cd2p126F); LIST.add(-0x1.0f0cd2p126F); }

    /**
     * This color constant "pink" has RGBA8888 code {@code FFA0E0FF}, L 0.7607843, A 0.5568628, B 0.4745098, alpha 1.0, hue 0.9329364, saturation 0.68139654, and chroma 0.124142565.
     * It can be represented as a packed float with the constant {@code -0x1.f31d84p125F}.
     * <pre>
     * <font style='background-color: #FFA0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA0E0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFA0E0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFA0E0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFA0E0'>&nbsp;@&nbsp;</font><font style='background-color: #FFA0E0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA0E0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINK = -0x1.f31d84p125F;
    static { NAMED.put("pink", -0x1.f31d84p125F); LIST.add(-0x1.f31d84p125F); }

    /**
     * This color constant "magenta" has RGBA8888 code {@code F500F5FF}, L 0.5764706, A 0.6313726, B 0.41568628, alpha 1.0, hue 0.9091779, saturation 0.9090322, and chroma 0.31098264.
     * It can be represented as a packed float with the constant {@code -0x1.d54326p125F}.
     * <pre>
     * <font style='background-color: #F500F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F500F5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F500F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F500F5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F500F5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F500F5'>&nbsp;@&nbsp;</font><font style='background-color: #F500F5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F500F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F500F5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MAGENTA = -0x1.d54326p125F;
    static { NAMED.put("magenta", -0x1.d54326p125F); LIST.add(-0x1.d54326p125F); }

    /**
     * This color constant "brick" has RGBA8888 code {@code D5524AFF}, L 0.5137255, A 0.5686275, B 0.53333336, alpha 1.0, hue 0.07195936, saturation 0.3226597, and chroma 0.15199278.
     * It can be represented as a packed float with the constant {@code -0x1.112306p126F}.
     * <pre>
     * <font style='background-color: #D5524A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5524A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5524A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D5524A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D5524A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D5524A'>&nbsp;@&nbsp;</font><font style='background-color: #D5524A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5524A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5524A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRICK = -0x1.112306p126F;
    static { NAMED.put("brick", -0x1.112306p126F); LIST.add(-0x1.112306p126F); }

    /**
     * This color constant "ember" has RGBA8888 code {@code F55A32FF}, L 0.57254905, A 0.57254905, B 0.5568628, alpha 1.0, hue 0.10581406, saturation 0.668431, and chroma 0.1836353.
     * It can be represented as a packed float with the constant {@code -0x1.1d2524p126F}.
     * <pre>
     * <font style='background-color: #F55A32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F55A32; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F55A32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F55A32'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F55A32'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F55A32'>&nbsp;@&nbsp;</font><font style='background-color: #F55A32; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F55A32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F55A32; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float EMBER = -0x1.1d2524p126F;
    static { NAMED.put("ember", -0x1.1d2524p126F); LIST.add(-0x1.1d2524p126F); }

    /**
     * This color constant "salmon" has RGBA8888 code {@code FF6262FF}, L 0.6117647, A 0.5803922, B 0.53333336, alpha 1.0, hue 0.06254671, saturation 0.8039996, and chroma 0.17337766.
     * It can be represented as a packed float with the constant {@code -0x1.112938p126F}.
     * <pre>
     * <font style='background-color: #FF6262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6262; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF6262'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF6262'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF6262'>&nbsp;@&nbsp;</font><font style='background-color: #FF6262; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6262; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SALMON = -0x1.112938p126F;
    static { NAMED.put("salmon", -0x1.112938p126F); LIST.add(-0x1.112938p126F); }

    /**
     * This color constant "chocolate" has RGBA8888 code {@code 683818FF}, L 0.27450982, A 0.5254902, B 0.53333336, alpha 1.0, hue 0.14608382, saturation 0.49234077, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.110c8cp126F}.
     * <pre>
     * <font style='background-color: #683818;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #683818; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #683818;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #683818'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #683818'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #683818'>&nbsp;@&nbsp;</font><font style='background-color: #683818; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #683818;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #683818; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CHOCOLATE = -0x1.110c8cp126F;
    static { NAMED.put("chocolate", -0x1.110c8cp126F); LIST.add(-0x1.110c8cp126F); }

    /**
     * This color constant "tan" has RGBA8888 code {@code D2B48CFF}, L 0.7294118, A 0.5058824, B 0.5254902, alpha 1.0, hue 0.21390288, saturation 0.08232166, and chroma 0.052115876.
     * It can be represented as a packed float with the constant {@code -0x1.0d0374p126F}.
     * <pre>
     * <font style='background-color: #D2B48C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2B48C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2B48C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D2B48C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D2B48C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D2B48C'>&nbsp;@&nbsp;</font><font style='background-color: #D2B48C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2B48C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2B48C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TAN = -0x1.0d0374p126F;
    static { NAMED.put("tan", -0x1.0d0374p126F); LIST.add(-0x1.0d0374p126F); }

    /**
     * This color constant "bronze" has RGBA8888 code {@code CE8E31FF}, L 0.61960787, A 0.5137255, B 0.56078434, alpha 1.0, hue 0.21465261, saturation 0.5864422, and chroma 0.124142565.
     * It can be represented as a packed float with the constant {@code -0x1.1f073cp126F}.
     * <pre>
     * <font style='background-color: #CE8E31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CE8E31; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CE8E31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CE8E31'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CE8E31'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CE8E31'>&nbsp;@&nbsp;</font><font style='background-color: #CE8E31; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CE8E31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CE8E31; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRONZE = -0x1.1f073cp126F;
    static { NAMED.put("bronze", -0x1.1f073cp126F); LIST.add(-0x1.1f073cp126F); }

    /**
     * This color constant "cinnamon" has RGBA8888 code {@code D2691DFF}, L 0.54509807, A 0.54509807, B 0.56078434, alpha 1.0, hue 0.14839543, saturation 0.7197198, and chroma 0.15078327.
     * It can be represented as a packed float with the constant {@code -0x1.1f1716p126F}.
     * <pre>
     * <font style='background-color: #D2691D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2691D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2691D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D2691D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D2691D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D2691D'>&nbsp;@&nbsp;</font><font style='background-color: #D2691D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2691D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2691D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CINNAMON = -0x1.1f1716p126F;
    static { NAMED.put("cinnamon", -0x1.1f1716p126F); LIST.add(-0x1.1f1716p126F); }

    /**
     * This color constant "apricot" has RGBA8888 code {@code FFA828FF}, L 0.7372549, A 0.52156866, B 0.5764706, alpha 1.0, hue 0.20625468, saturation 0.75939417, and chroma 0.1582875.
     * It can be represented as a packed float with the constant {@code -0x1.270b78p126F}.
     * <pre>
     * <font style='background-color: #FFA828;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA828; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA828;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFA828'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFA828'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFA828'>&nbsp;@&nbsp;</font><font style='background-color: #FFA828; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA828;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA828; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float APRICOT = -0x1.270b78p126F;
    static { NAMED.put("apricot", -0x1.270b78p126F); LIST.add(-0x1.270b78p126F); }

    /**
     * This color constant "peach" has RGBA8888 code {@code FFBF81FF}, L 0.79607844, A 0.5176471, B 0.54509807, alpha 1.0, hue 0.19064914, saturation 0.6557377, and chroma 0.096477255.
     * It can be represented as a packed float with the constant {@code -0x1.170996p126F}.
     * <pre>
     * <font style='background-color: #FFBF81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFBF81; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFBF81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFBF81'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFBF81'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFBF81'>&nbsp;@&nbsp;</font><font style='background-color: #FFBF81; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFBF81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFBF81; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PEACH = -0x1.170996p126F;
    static { NAMED.put("peach", -0x1.170996p126F); LIST.add(-0x1.170996p126F); }

    /**
     * This color constant "pear" has RGBA8888 code {@code D3E330FF}, L 0.8235294, A 0.45882353, B 0.58431375, alpha 1.0, hue 0.32230157, saturation 0.7434462, and chroma 0.18692946.
     * It can be represented as a packed float with the constant {@code -0x1.2aeba4p126F}.
     * <pre>
     * <font style='background-color: #D3E330;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3E330; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3E330;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D3E330'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D3E330'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D3E330'>&nbsp;@&nbsp;</font><font style='background-color: #D3E330; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3E330;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3E330; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PEAR = -0x1.2aeba4p126F;
    static { NAMED.put("pear", -0x1.2aeba4p126F); LIST.add(-0x1.2aeba4p126F); }

    /**
     * This color constant "saffron" has RGBA8888 code {@code FFD510FF}, L 0.83137256, A 0.4862745, B 0.5882353, alpha 1.0, hue 0.27457327, saturation 0.8132536, and chroma 0.17789528.
     * It can be represented as a packed float with the constant {@code -0x1.2cf9a8p126F}.
     * <pre>
     * <font style='background-color: #FFD510;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD510; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD510;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFD510'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFD510'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFD510'>&nbsp;@&nbsp;</font><font style='background-color: #FFD510; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD510;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD510; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SAFFRON = -0x1.2cf9a8p126F;
    static { NAMED.put("saffron", -0x1.2cf9a8p126F); LIST.add(-0x1.2cf9a8p126F); }

    /**
     * This color constant "butter" has RGBA8888 code {@code FFF288FF}, L 0.9098039, A 0.4862745, B 0.5568628, alpha 1.0, hue 0.28769454, saturation 0.49273357, and chroma 0.11653464.
     * It can be represented as a packed float with the constant {@code -0x1.1cf9dp126F}.
     * <pre>
     * <font style='background-color: #FFF288;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFF288; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFF288;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFF288'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFF288'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFF288'>&nbsp;@&nbsp;</font><font style='background-color: #FFF288; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFF288;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFF288; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BUTTER = -0x1.1cf9dp126F;
    static { NAMED.put("butter", -0x1.1cf9dp126F); LIST.add(-0x1.1cf9dp126F); }

    /**
     * This color constant "chartreuse" has RGBA8888 code {@code C8FF41FF}, L 0.8862745, A 0.4392157, B 0.58431375, alpha 1.0, hue 0.34942675, saturation 0.9817451, and chroma 0.2070681.
     * It can be represented as a packed float with the constant {@code -0x1.2ae1c4p126F}.
     * <pre>
     * <font style='background-color: #C8FF41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8FF41; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8FF41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C8FF41'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C8FF41'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C8FF41'>&nbsp;@&nbsp;</font><font style='background-color: #C8FF41; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8FF41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8FF41; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CHARTREUSE = -0x1.2ae1c4p126F;
    static { NAMED.put("chartreuse", -0x1.2ae1c4p126F); LIST.add(-0x1.2ae1c4p126F); }

    /**
     * This color constant "cactus" has RGBA8888 code {@code 30A000FF}, L 0.5254902, A 0.41960785, B 0.5647059, alpha 1.0, hue 0.39212817, saturation 0.809409, and chroma 0.20558903.
     * It can be represented as a packed float with the constant {@code -0x1.20d70cp126F}.
     * <pre>
     * <font style='background-color: #30A000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #30A000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #30A000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #30A000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #30A000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #30A000'>&nbsp;@&nbsp;</font><font style='background-color: #30A000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #30A000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #30A000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CACTUS = -0x1.20d70cp126F;
    static { NAMED.put("cactus", -0x1.20d70cp126F); LIST.add(-0x1.20d70cp126F); }

    /**
     * This color constant "lime" has RGBA8888 code {@code 93D300FF}, L 0.7294118, A 0.43529412, B 0.5803922, alpha 1.0, hue 0.3578718, saturation 0.78243065, and chroma 0.20558903.
     * It can be represented as a packed float with the constant {@code -0x1.28df74p126F}.
     * <pre>
     * <font style='background-color: #93D300;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #93D300; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #93D300;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #93D300'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #93D300'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #93D300'>&nbsp;@&nbsp;</font><font style='background-color: #93D300; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #93D300;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #93D300; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIME = -0x1.28df74p126F;
    static { NAMED.put("lime", -0x1.28df74p126F); LIST.add(-0x1.28df74p126F); }

    /**
     * This color constant "olive" has RGBA8888 code {@code 818000FF}, L 0.49019608, A 0.4745098, B 0.56078434, alpha 1.0, hue 0.31318712, saturation 0.76235455, and chroma 0.13131043.
     * It can be represented as a packed float with the constant {@code -0x1.1ef2fap126F}.
     * <pre>
     * <font style='background-color: #818000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #818000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #818000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #818000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #818000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #818000'>&nbsp;@&nbsp;</font><font style='background-color: #818000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #818000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #818000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float OLIVE = -0x1.1ef2fap126F;
    static { NAMED.put("olive", -0x1.1ef2fap126F); LIST.add(-0x1.1ef2fap126F); }

    /**
     * This color constant "fern" has RGBA8888 code {@code 4E7942FF}, L 0.43137255, A 0.4627451, B 0.5294118, alpha 1.0, hue 0.3936267, saturation 0.22094448, and chroma 0.0945603.
     * It can be represented as a packed float with the constant {@code -0x1.0eecdcp126F}.
     * <pre>
     * <font style='background-color: #4E7942;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E7942; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E7942;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4E7942'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4E7942'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4E7942'>&nbsp;@&nbsp;</font><font style='background-color: #4E7942; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E7942;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E7942; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FERN = -0x1.0eecdcp126F;
    static { NAMED.put("fern", -0x1.0eecdcp126F); LIST.add(-0x1.0eecdcp126F); }

    /**
     * This color constant "moss" has RGBA8888 code {@code 204608FF}, L 0.22745098, A 0.45882353, B 0.5372549, alpha 1.0, hue 0.38294512, saturation 0.6363817, and chroma 0.11062346.
     * It can be represented as a packed float with the constant {@code -0x1.12ea74p126F}.
     * <pre>
     * <font style='background-color: #204608;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #204608; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #204608;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #204608'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #204608'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #204608'>&nbsp;@&nbsp;</font><font style='background-color: #204608; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #204608;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #204608; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MOSS = -0x1.12ea74p126F;
    static { NAMED.put("moss", -0x1.12ea74p126F); LIST.add(-0x1.12ea74p126F); }

    /**
     * This color constant "celery" has RGBA8888 code {@code 7DFF73FF}, L 0.84313726, A 0.41960785, B 0.56078434, alpha 1.0, hue 0.39695174, saturation 0.8276294, and chroma 0.20078278.
     * It can be represented as a packed float with the constant {@code -0x1.1ed7aep126F}.
     * <pre>
     * <font style='background-color: #7DFF73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7DFF73; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7DFF73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7DFF73'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7DFF73'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7DFF73'>&nbsp;@&nbsp;</font><font style='background-color: #7DFF73; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7DFF73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7DFF73; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CELERY = -0x1.1ed7aep126F;
    static { NAMED.put("celery", -0x1.1ed7aep126F); LIST.add(-0x1.1ed7aep126F); }

    /**
     * This color constant "sage" has RGBA8888 code {@code ABE3C5FF}, L 0.81960785, A 0.46666667, B 0.50980395, alpha 1.0, hue 0.45448294, saturation 0.118390046, and chroma 0.069218926.
     * It can be represented as a packed float with the constant {@code -0x1.04efa2p126F}.
     * <pre>
     * <font style='background-color: #ABE3C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABE3C5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABE3C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ABE3C5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ABE3C5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ABE3C5'>&nbsp;@&nbsp;</font><font style='background-color: #ABE3C5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABE3C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABE3C5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SAGE = -0x1.04efa2p126F;
    static { NAMED.put("sage", -0x1.04efa2p126F); LIST.add(-0x1.04efa2p126F); }

    /**
     * This color constant "jade" has RGBA8888 code {@code 3FBF3FFF}, L 0.6313726, A 0.41568628, B 0.5568628, alpha 1.0, hue 0.4055531, saturation 0.71121687, and chroma 0.20259848.
     * It can be represented as a packed float with the constant {@code -0x1.1cd542p126F}.
     * <pre>
     * <font style='background-color: #3FBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3FBF3F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3FBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3FBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3FBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3FBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #3FBF3F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3FBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3FBF3F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float JADE = -0x1.1cd542p126F;
    static { NAMED.put("jade", -0x1.1cd542p126F); LIST.add(-0x1.1cd542p126F); }

    /**
     * This color constant "cyan" has RGBA8888 code {@code 00FFFFFF}, L 0.8509804, A 0.42352942, B 0.47843137, alpha 1.0, hue 0.54374534, saturation 0.95340395, and chroma 0.1582875.
     * It can be represented as a packed float with the constant {@code -0x1.f4d9b2p125F}.
     * <pre>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #00FFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CYAN = -0x1.f4d9b2p125F;
    static { NAMED.put("cyan", -0x1.f4d9b2p125F); LIST.add(-0x1.f4d9b2p125F); }

    /**
     * This color constant "mint" has RGBA8888 code {@code 7FFFD4FF}, L 0.8666667, A 0.4392157, B 0.50980395, alpha 1.0, hue 0.47453672, saturation 0.8283974, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.04e1bap126F}.
     * <pre>
     * <font style='background-color: #7FFFD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7FFFD4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7FFFD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7FFFD4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7FFFD4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7FFFD4'>&nbsp;@&nbsp;</font><font style='background-color: #7FFFD4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7FFFD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7FFFD4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MINT = -0x1.04e1bap126F;
    static { NAMED.put("mint", -0x1.04e1bap126F); LIST.add(-0x1.04e1bap126F); }

    /**
     * This color constant "teal" has RGBA8888 code {@code 007F7FFF}, L 0.4392157, A 0.4509804, B 0.4862745, alpha 1.0, hue 0.543443, saturation 0.89123964, and chroma 0.10141215.
     * It can be represented as a packed float with the constant {@code -0x1.f8e6ep125F}.
     * <pre>
     * <font style='background-color: #007F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007F7F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #007F7F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #007F7F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #007F7F'>&nbsp;@&nbsp;</font><font style='background-color: #007F7F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007F7F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TEAL = -0x1.f8e6ep125F;
    static { NAMED.put("teal", -0x1.f8e6ep125F); LIST.add(-0x1.f8e6ep125F); }

    /**
     * This color constant "turquoise" has RGBA8888 code {@code 2ED6C9FF}, L 0.7254902, A 0.43137255, B 0.49019608, alpha 1.0, hue 0.52259654, saturation 0.8433125, and chroma 0.1381068.
     * It can be represented as a packed float with the constant {@code -0x1.fadd72p125F}.
     * <pre>
     * <font style='background-color: #2ED6C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2ED6C9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2ED6C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2ED6C9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2ED6C9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2ED6C9'>&nbsp;@&nbsp;</font><font style='background-color: #2ED6C9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2ED6C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2ED6C9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TURQUOISE = -0x1.fadd72p125F;
    static { NAMED.put("turquoise", -0x1.fadd72p125F); LIST.add(-0x1.fadd72p125F); }

    /**
     * This color constant "sky" has RGBA8888 code {@code 10C0E0FF}, L 0.67058825, A 0.44313726, B 0.4627451, alpha 1.0, hue 0.59232193, saturation 0.8547556, and chroma 0.13542919.
     * It can be represented as a packed float with the constant {@code -0x1.ece356p125F}.
     * <pre>
     * <font style='background-color: #10C0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #10C0E0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #10C0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #10C0E0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #10C0E0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #10C0E0'>&nbsp;@&nbsp;</font><font style='background-color: #10C0E0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #10C0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #10C0E0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SKY = -0x1.ece356p125F;
    static { NAMED.put("sky", -0x1.ece356p125F); LIST.add(-0x1.ece356p125F); }

    /**
     * This color constant "cobalt" has RGBA8888 code {@code 0046ABFF}, L 0.30588236, A 0.48235294, B 0.4117647, alpha 1.0, hue 0.71857655, saturation 0.73578477, and chroma 0.1792624.
     * It can be represented as a packed float with the constant {@code -0x1.d2f69cp125F}.
     * <pre>
     * <font style='background-color: #0046AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0046AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0046AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0046AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0046AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0046AB'>&nbsp;@&nbsp;</font><font style='background-color: #0046AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0046AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0046AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float COBALT = -0x1.d2f69cp125F;
    static { NAMED.put("cobalt", -0x1.d2f69cp125F); LIST.add(-0x1.d2f69cp125F); }

    /**
     * This color constant "denim" has RGBA8888 code {@code 3088B8FF}, L 0.5058824, A 0.46666667, B 0.45490196, alpha 1.0, hue 0.6486837, saturation 0.5816889, and chroma 0.11172148.
     * It can be represented as a packed float with the constant {@code -0x1.e8ef02p125F}.
     * <pre>
     * <font style='background-color: #3088B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3088B8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3088B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3088B8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3088B8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3088B8'>&nbsp;@&nbsp;</font><font style='background-color: #3088B8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3088B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3088B8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DENIM = -0x1.e8ef02p125F;
    static { NAMED.put("denim", -0x1.e8ef02p125F); LIST.add(-0x1.e8ef02p125F); }

    /**
     * This color constant "navy" has RGBA8888 code {@code 000080FF}, L 0.14117648, A 0.4862745, B 0.4, alpha 1.0, hue 0.7282781, saturation 0.96145123, and chroma 0.20108652.
     * It can be represented as a packed float with the constant {@code -0x1.ccf848p125F}.
     * <pre>
     * <font style='background-color: #000080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000080; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000080'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000080'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000080'>&nbsp;@&nbsp;</font><font style='background-color: #000080; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000080; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float NAVY = -0x1.ccf848p125F;
    static { NAMED.put("navy", -0x1.ccf848p125F); LIST.add(-0x1.ccf848p125F); }

    /**
     * This color constant "lavender" has RGBA8888 code {@code B991FFFF}, L 0.67058825, A 0.53333336, B 0.43529412, alpha 1.0, hue 0.82570946, saturation 0.84011585, and chroma 0.14500555.
     * It can be represented as a packed float with the constant {@code -0x1.df1156p125F}.
     * <pre>
     * <font style='background-color: #B991FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B991FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B991FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B991FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B991FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B991FF'>&nbsp;@&nbsp;</font><font style='background-color: #B991FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B991FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B991FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LAVENDER = -0x1.df1156p125F;
    static { NAMED.put("lavender", -0x1.df1156p125F); LIST.add(-0x1.df1156p125F); }

    /**
     * This color constant "plum" has RGBA8888 code {@code BE0DC6FF}, L 0.45490196, A 0.6117647, B 0.42352942, alpha 1.0, hue 0.90448654, saturation 0.9074911, and chroma 0.26978588.
     * It can be represented as a packed float with the constant {@code -0x1.d938e8p125F}.
     * <pre>
     * <font style='background-color: #BE0DC6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE0DC6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE0DC6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BE0DC6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BE0DC6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BE0DC6'>&nbsp;@&nbsp;</font><font style='background-color: #BE0DC6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE0DC6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE0DC6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PLUM = -0x1.d938e8p125F;
    static { NAMED.put("plum", -0x1.d938e8p125F); LIST.add(-0x1.d938e8p125F); }

    /**
     * This color constant "mauve" has RGBA8888 code {@code AB73ABFF}, L 0.54901963, A 0.5411765, B 0.47058824, alpha 1.0, hue 0.90127134, saturation 0.09785124, and chroma 0.1008085.
     * It can be represented as a packed float with the constant {@code -0x1.f11518p125F}.
     * <pre>
     * <font style='background-color: #AB73AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB73AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB73AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB73AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB73AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB73AB'>&nbsp;@&nbsp;</font><font style='background-color: #AB73AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB73AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB73AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MAUVE = -0x1.f11518p125F;
    static { NAMED.put("mauve", -0x1.f11518p125F); LIST.add(-0x1.f11518p125F); }

    /**
     * This color constant "rose" has RGBA8888 code {@code E61E78FF}, L 0.49411765, A 0.6156863, B 0.49411765, alpha 1.0, hue 0.99192464, saturation 0.7659808, and chroma 0.23076649.
     * It can be represented as a packed float with the constant {@code -0x1.fd3afcp125F}.
     * <pre>
     * <font style='background-color: #E61E78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E61E78; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E61E78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E61E78'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E61E78'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E61E78'>&nbsp;@&nbsp;</font><font style='background-color: #E61E78; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E61E78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E61E78; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ROSE = -0x1.fd3afcp125F;
    static { NAMED.put("rose", -0x1.fd3afcp125F); LIST.add(-0x1.fd3afcp125F); }

    /**
     * This color constant "raspberry" has RGBA8888 code {@code 911437FF}, L 0.3019608, A 0.5803922, B 0.5137255, alpha 1.0, hue 0.02692472, saturation 0.73546606, and chroma 0.16247371.
     * It can be represented as a packed float with the constant {@code -0x1.07289ap126F}.
     * <pre>
     * <font style='background-color: #911437;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #911437; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #911437;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #911437'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #911437'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #911437'>&nbsp;@&nbsp;</font><font style='background-color: #911437; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #911437;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #911437; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float RASPBERRY = -0x1.07289ap126F;
    static { NAMED.put("raspberry", -0x1.07289ap126F); LIST.add(-0x1.07289ap126F); }

    /**
     * All names for colors in this palette, in alphabetical order. You can fetch the corresponding packed float color
     * by looking up a name in {@link #NAMED}.
     */
    public static final Array<String> NAMES = NAMED.keys().toArray();
    static { NAMES.sort(); }
    /**
     * All names for colors in this palette, with grayscale first, then sorted by hue from red to yellow to green to
     * blue. You can fetch the corresponding packed float color by looking up a name in {@link #NAMED}.
     */
    public static final Array<String> NAMES_BY_HUE = new Array<>(NAMES);

    public static final FloatArray COLORS_BY_HUE = new FloatArray(NAMES_BY_HUE.size);
    /**
     * All names for colors in this palette, sorted by lightness from black to white. You can fetch the
     * corresponding packed float color by looking up a name in {@link #NAMED}.
     */
    public static final Array<String> NAMES_BY_LIGHTNESS = new Array<>(NAMES);
    static {
        NAMES_BY_HUE.sort(new Comparator<String>() {
            public int compare(String o1, String o2) {
                final float c1 = NAMED.get(o1, TRANSPARENT), c2 = NAMED.get(o2, TRANSPARENT);
                final float s1 = ColorTools.saturation(c1), s2 = ColorTools.saturation(c2);
                if(alphaInt(c1) < 128) return -10000;
                else if(alphaInt(c2) < 128) return 10000;
                else if(s1 <= 0.05f && s2 > 0.05f)
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
        for(String name : NAMES_BY_HUE) {
            COLORS_BY_HUE.add(NAMED.get(name, TRANSPARENT));
        }
        NAMES_BY_LIGHTNESS.sort(new Comparator<String>() {
            public int compare(String o1, String o2) {
                return Float.compare(channelL(NAMED.get(o1, TRANSPARENT)), channelL(NAMED.get(o2, TRANSPARENT)));
            }
        });
    }

    private static final FloatArray mixing = new FloatArray(4);

    /**
     * Parses a color description and returns the approximate color it describes, as a packed float color.
     * Color descriptions consist of one or more lower-case words, separated by non-alphabetical characters (typically
     * spaces and/or hyphens). Any word that is the name of a color in this SimplePalette will be looked up in
     * {@link #NAMED} and tracked; if there is more than one of these color name words, the colors will be mixed using
     * {@link FloatColors#mix(float[], int, int)}, or if there is just one color name word, then the corresponding color
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
                        mixing.add(NAMED.get(term, TRANSPARENT));
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
                        mixing.add(NAMED.get(term, 0f));
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
                        mixing.add(NAMED.get(term, 0f));
                    }
                    break;
                default:
                    mixing.add(NAMED.get(term, 0f));
                    break;
            }
        }
        float result = FloatColors.mix(mixing.items, 0, mixing.size);
        if(result == 0f) return result;

        if(intensity > 0) result = ColorTools.lighten(result, intensity);
        else if(intensity < 0) result = ColorTools.darken(result, -intensity);

        if(saturation > 0) result = (ColorTools.enrich(result, saturation));
        else if(saturation < 0) result = ColorTools.limitToGamut(ColorTools.dullen(result, -saturation));
        else result = ColorTools.limitToGamut(result);

        return result;
    }
    private static final Array<String> namesByHue = new Array<>(NAMES_BY_HUE);
    private static final FloatArray colorsByHue = new FloatArray(COLORS_BY_HUE);
    static {
        int trn = namesByHue.indexOf("transparent", false);
        namesByHue.removeIndex(trn);
        colorsByHue.removeIndex(trn);
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
     * Given a color as a packed Oklab float, this finds the closest description it can to match the given color while
     * using at most {@code mixCount} colors to mix in. You should only use small numbers for mixCount, like 1 to 3;
     * this can take quite a while to run otherwise. This returns a String description that can be passed to
     * {@link #parseDescription(String)}. It is likely that this will use very contrasting colors if mixCount is 2 or
     * greater and the color to match is desaturated or brownish.
     * @param oklab a packed Oklab float color to attempt to match
     * @param mixCount how many color names this will use in the returned description
     * @return a description that can be fed to {@link #parseDescription(String)} to get a similar color
     */
    public static String bestMatch(final float oklab, int mixCount) {
        mixCount = Math.max(1, mixCount);
        float bestDistance = Float.POSITIVE_INFINITY;
        final int paletteSize = namesByHue.size, colorTries = (int)Math.pow(paletteSize, mixCount), totalTries = colorTries * 81;
        final float targetL = ColorTools.channelL(oklab), targetA = ColorTools.channelA(oklab), targetB = ColorTools.channelB(oklab);
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

            float result = FloatColors.mix(mixing.items, 0, mixCount);
            if(idxI > 0) result = ColorTools.lighten(result, 0.125f * idxI);
            else if(idxI < 0) result = ColorTools.darken(result, -0.15f * idxI);

            if(idxS > 0) result = ColorTools.limitToGamut(ColorTools.enrich(result, 0.2f * idxS));
            else if(idxS < 0) result = ColorTools.dullen(result, -0.2f * idxS);
            else result = ColorTools.limitToGamut(result);

            float dL = ColorTools.channelL(result) - targetL, dA = ColorTools.channelA(result) - targetA, dB = ColorTools.channelB(result) - targetB;
            if(bestDistance > (bestDistance = Math.min(dL * dL + dA * dA + dB * dB, bestDistance)))
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

    /**
     * Changes the existing RGBA Color instances in {@link Colors} to use Oklab and so be able to be shown normally by
     * {@link ColorfulBatch} or a Batch using {@link com.github.tommyettinger.colorful.Shaders#fragmentShaderOklab}.
     * Any colors used in libGDX text markup look up their values in Colors, so calling this can help display fonts
     * where markup is enabled. This only needs to be called once, and if you call {@link #appendToKnownColors()}, then
     * that should be done after this to avoid mixing RGBA and Oklab colors.
     * <br>
     * This is a duplicate of a method with the same name in Palette; you should still only call this method once,
     * regardless of where it was from.
     */
    public static void editKnownColors(){
        for(Color c : Colors.getColors().values()) {
            final float f = ColorTools.fromColor(c);
            c.set(channelL(f), channelA(f), channelB(f), c.a);
        }
    }

    /**
     * Appends Oklab-compatible Color instances to the map in {@link Colors}, using the names in {@link #NAMES} (which
     * are "lower cased" instead of "ALL UPPER CASE"). If you intend to still use the existing values in Colors, you
     * should call {@link #editKnownColors()} first; otherwise you can just always use "lower cased" color names.
     * This does append aliases as well, so some color values will be duplicates.
     * <br>
     * This can be used alongside the method with the same name in Palette, since that uses "Title Cased" names.
     */
    public static void appendToKnownColors(){
        for(ObjectFloatMap.Entry<String> ent : NAMED) {
            final float f = ent.value;
            Colors.put(ent.key, new Color(channelL(f), channelA(f), channelB(f), alpha(f)));
        }
    }
}
