package com.github.tommyettinger.colorful.pure.oklab;

import com.github.tommyettinger.colorful.pure.MathTools;
import com.github.tommyettinger.ds.IntList;
import com.github.tommyettinger.ds.ObjectIntOrderedMap;
import com.github.tommyettinger.ds.ObjectList;
import com.github.tommyettinger.ds.support.BitConversion;
import com.github.tommyettinger.colorful.pure.oklab.Gamut;
import java.io.UnsupportedEncodingException;

import static com.github.tommyettinger.colorful.pure.oklab.Gamut.GAMUT_DATA;

/**
 * A palette of predefined colors as packed Oklab ints, and tools for obtaining Oklab int colors from a description.
 * You can access colors by their constant name, such as {@code cactus}, by the {@link #NAMED} map using
 * {@code NAMED.get("cactus")}, by getting a color by its name's position in alphabetical order with
 * {@code NAMED.getAt(12)}, or by index in the IntList called {@link #LIST}. When accessing a color with
 * {@link ObjectIntOrderedMap#get(Object)}, if the name is not found, get() will return {@link #TRANSPARENT}. If you
 * want to control the not-found value, you can use {@link ObjectIntOrderedMap#getOrDefault(Object, int)}. You can
 * access the names in a specific order with {@link #NAMES} (which is alphabetical), {@link #NAMES_BY_HUE} (which is
 * sorted by the hue of the matching color, with all grayscale colors at the start, then from red to yellow to blue to
 * purple to red again), or {@link #NAMES_BY_LIGHTNESS} (which is sorted by the L channel of the matching color, from
 * darkest to lightest). Having a name lets you look up the matching color in {@link #NAMED}. You can also modify or
 * re-order NAMED if you want to, such as to add more named colors.
 */
public final class AlternatePalette {
    /**
     * No need to extend this.
     */
    private AlternatePalette() {
    }
    public static final ObjectIntOrderedMap<String> NAMED = new ObjectIntOrderedMap<String>(50);
    public static final IntList LIST = new IntList(50);

    /**
     * This color constant "transparent" has RGBA8888 code {@code 00000000}, L 0.0, A 0.49803922, B 0.49803922, alpha 0.0, hue 0.625, saturation 0.0, and chroma 0.0055242716.
     * It has the encoded Oklab value 0x007f7f00 .
     * <pre>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #000000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRANSPARENT = 0x007f7f00;
    static { NAMED.put("transparent", 0x007f7f00); LIST.add(0x007f7f00); }

    /**
     * This color constant "black" has RGBA8888 code {@code 000000FF}, L 0.0, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, saturation 0.0, and chroma 0.0055242716.
     * It has the encoded Oklab value 0xfe7f7f00 .
     * <pre>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #000000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BLACK = 0xfe7f7f00;
    static { NAMED.put("black", 0xfe7f7f00); LIST.add(0xfe7f7f00); }

    /**
     * This color constant "gray" has RGBA8888 code {@code 808080FF}, L 0.49411765, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, saturation 0.0016803193, and chroma 0.0055242716.
     * It has the encoded Oklab value 0xfe7f7f7e .
     * <pre>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #808080; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #808080; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #808080; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int GRAY = 0xfe7f7f7e;
    static { NAMED.put("gray", 0xfe7f7f7e); LIST.add(0xfe7f7f7e); }

    /**
     * This color constant "silver" has RGBA8888 code {@code B6B6B6FF}, L 0.69411767, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, saturation 0.0013493, and chroma 0.0055242716.
     * It has the encoded Oklab value 0xfe7f7fb1 .
     * <pre>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B6B6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #B6B6B6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B6B6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int SILVER = 0xfe7f7fb1;
    static { NAMED.put("silver", 0xfe7f7fb1); LIST.add(0xfe7f7fb1); }

    /**
     * This color constant "white" has RGBA8888 code {@code FFFFFFFF}, L 1.0, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, saturation 0.0, and chroma 0.0055242716.
     * It has the encoded Oklab value 0xfe7f7fff .
     * <pre>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #FFFFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int WHITE = 0xfe7f7fff;
    static { NAMED.put("white", 0xfe7f7fff); LIST.add(0xfe7f7fff); }

    /**
     * This color constant "red" has RGBA8888 code {@code FF0000FF}, L 0.49019608, A 0.6117647, B 0.56078434, alpha 1.0, hue 0.07928106, saturation 0.8972241, and chroma 0.25345513.
     * It has the encoded Oklab value 0xfe8f9c7d .
     * <pre>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #FF0000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int RED = 0xfe8f9c7d;
    static { NAMED.put("red", 0xfe8f9c7d); LIST.add(0xfe8f9c7d); }

    /**
     * This color constant "orange" has RGBA8888 code {@code FF7F00FF}, L 0.62352943, A 0.54901963, B 0.57254905, alpha 1.0, hue 0.15541562, saturation 0.8137945, and chroma 0.17443058.
     * It has the encoded Oklab value 0xfe928c9f .
     * <pre>
     * <font style='background-color: #FF7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF7F00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF7F00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF7F00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF7F00'>&nbsp;@&nbsp;</font><font style='background-color: #FF7F00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF7F00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int ORANGE = 0xfe928c9f;
    static { NAMED.put("orange", 0xfe928c9f); LIST.add(0xfe928c9f); }

    /**
     * This color constant "yellow" has RGBA8888 code {@code FFFF00FF}, L 0.94509804, A 0.4627451, B 0.59607846, alpha 1.0, hue 0.30886024, saturation 0.8652204, and chroma 0.20529193.
     * It has the encoded Oklab value 0xfe9876f1 .
     * <pre>
     * <font style='background-color: #FFFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFF00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFF00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFF00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFF00'>&nbsp;@&nbsp;</font><font style='background-color: #FFFF00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFF00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int YELLOW = 0xfe9876f1;
    static { NAMED.put("yellow", 0xfe9876f1); LIST.add(0xfe9876f1); }

    /**
     * This color constant "green" has RGBA8888 code {@code 00FF00FF}, L 0.7882353, A 0.38039216, B 0.5882353, alpha 1.0, hue 0.39883053, saturation 0.9818446, and chroma 0.29610303.
     * It has the encoded Oklab value 0xfe9661c9 .
     * <pre>
     * <font style='background-color: #00FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FF00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FF00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FF00'>&nbsp;@&nbsp;</font><font style='background-color: #00FF00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int GREEN = 0xfe9661c9;
    static { NAMED.put("green", 0xfe9661c9); LIST.add(0xfe9661c9); }

    /**
     * This color constant "blue" has RGBA8888 code {@code 0000FFFF}, L 0.31764707, A 0.48235294, B 0.34117648, alpha 1.0, hue 0.7323789, saturation 0.97586775, and chroma 0.31835338.
     * It has the encoded Oklab value 0xfe577b51 .
     * <pre>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0000FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #0000FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0000FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BLUE = 0xfe577b51;
    static { NAMED.put("blue", 0xfe577b51); LIST.add(0xfe577b51); }

    /**
     * This color constant "indigo" has RGBA8888 code {@code 520FE0FF}, L 0.32941177, A 0.53333336, B 0.36862746, alpha 1.0, hue 0.7895446, saturation 0.9346178, and chroma 0.27001202.
     * It has the encoded Oklab value 0xfe5e8854 .
     * <pre>
     * <font style='background-color: #520FE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #520FE0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #520FE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #520FE0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #520FE0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #520FE0'>&nbsp;@&nbsp;</font><font style='background-color: #520FE0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #520FE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #520FE0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int INDIGO = 0xfe5e8854;
    static { NAMED.put("indigo", 0xfe5e8854); LIST.add(0xfe5e8854); }

    /**
     * This color constant "violet" has RGBA8888 code {@code 9040EFFF}, L 0.4509804, A 0.56078434, B 0.39607844, alpha 1.0, hue 0.8342395, saturation 0.6792487, and chroma 0.23984502.
     * It has the encoded Oklab value 0xfe658f73 .
     * <pre>
     * <font style='background-color: #9040EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9040EF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9040EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9040EF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9040EF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9040EF'>&nbsp;@&nbsp;</font><font style='background-color: #9040EF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9040EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9040EF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int VIOLET = 0xfe658f73;
    static { NAMED.put("violet", 0xfe658f73); LIST.add(0xfe658f73); }

    /**
     * This color constant "purple" has RGBA8888 code {@code C000FFFF}, L 0.47843137, A 0.60784316, B 0.3882353, alpha 1.0, hue 0.8721605, saturation 0.96817255, and chroma 0.30940855.
     * It has the encoded Oklab value 0xfe639b7a .
     * <pre>
     * <font style='background-color: #C000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C000FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C000FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C000FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C000FF'>&nbsp;@&nbsp;</font><font style='background-color: #C000FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C000FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int PURPLE = 0xfe639b7a;
    static { NAMED.put("purple", 0xfe639b7a); LIST.add(0xfe639b7a); }

    /**
     * This color constant "brown" has RGBA8888 code {@code 8F573BFF}, L 0.40392157, A 0.5254902, B 0.5294118, alpha 1.0, hue 0.13634071, saturation 0.24020728, and chroma 0.07753685.
     * It has the encoded Oklab value 0xfe878667 .
     * <pre>
     * <font style='background-color: #8F573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F573B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F573B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F573B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F573B'>&nbsp;@&nbsp;</font><font style='background-color: #8F573B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F573B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BROWN = 0xfe878667;
    static { NAMED.put("brown", 0xfe878667); LIST.add(0xfe878667); }

    /**
     * This color constant "pink" has RGBA8888 code {@code FFA0E0FF}, L 0.7411765, A 0.5568628, B 0.4745098, alpha 1.0, hue 0.9329364, saturation 0.64733213, and chroma 0.124142565.
     * It has the encoded Oklab value 0xfe798ebd .
     * <pre>
     * <font style='background-color: #FFA0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA0E0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFA0E0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFA0E0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFA0E0'>&nbsp;@&nbsp;</font><font style='background-color: #FFA0E0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA0E0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int PINK = 0xfe798ebd;
    static { NAMED.put("pink", 0xfe798ebd); LIST.add(0xfe798ebd); }

    /**
     * This color constant "magenta" has RGBA8888 code {@code F500F5FF}, L 0.54901963, A 0.6313726, B 0.41568628, alpha 1.0, hue 0.9091779, saturation 0.9090322, and chroma 0.31098264.
     * It has the encoded Oklab value 0xfe6aa18c .
     * <pre>
     * <font style='background-color: #F500F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F500F5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F500F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F500F5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F500F5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F500F5'>&nbsp;@&nbsp;</font><font style='background-color: #F500F5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F500F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F500F5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int MAGENTA = 0xfe6aa18c;
    static { NAMED.put("magenta", 0xfe6aa18c); LIST.add(0xfe6aa18c); }

    /**
     * This color constant "brick" has RGBA8888 code {@code D5524AFF}, L 0.49019608, A 0.5686275, B 0.53333336, alpha 1.0, hue 0.07195936, saturation 0.3226597, and chroma 0.15199278.
     * It has the encoded Oklab value 0xfe88917d .
     * <pre>
     * <font style='background-color: #D5524A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5524A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5524A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D5524A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D5524A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D5524A'>&nbsp;@&nbsp;</font><font style='background-color: #D5524A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5524A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5524A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRICK = 0xfe88917d;
    static { NAMED.put("brick", 0xfe88917d); LIST.add(0xfe88917d); }

    /**
     * This color constant "ember" has RGBA8888 code {@code F55A32FF}, L 0.54901963, A 0.57254905, B 0.5568628, alpha 1.0, hue 0.10581406, saturation 0.645774, and chroma 0.1836353.
     * It has the encoded Oklab value 0xfe8e928c .
     * <pre>
     * <font style='background-color: #F55A32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F55A32; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F55A32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F55A32'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F55A32'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F55A32'>&nbsp;@&nbsp;</font><font style='background-color: #F55A32; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F55A32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F55A32; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int EMBER = 0xfe8e928c;
    static { NAMED.put("ember", 0xfe8e928c); LIST.add(0xfe8e928c); }

    /**
     * This color constant "salmon" has RGBA8888 code {@code FF6262FF}, L 0.58431375, A 0.5803922, B 0.53333336, alpha 1.0, hue 0.06254671, saturation 0.7724733, and chroma 0.17337766.
     * It has the encoded Oklab value 0xfe889495 .
     * <pre>
     * <font style='background-color: #FF6262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6262; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF6262'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF6262'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF6262'>&nbsp;@&nbsp;</font><font style='background-color: #FF6262; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6262; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int SALMON = 0xfe889495;
    static { NAMED.put("salmon", 0xfe889495); LIST.add(0xfe889495); }

    /**
     * This color constant "chocolate" has RGBA8888 code {@code 683818FF}, L 0.2901961, A 0.5254902, B 0.53333336, alpha 1.0, hue 0.14608382, saturation 0.5262855, and chroma 0.0835974.
     * It has the encoded Oklab value 0xfe88864a .
     * <pre>
     * <font style='background-color: #683818;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #683818; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #683818;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #683818'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #683818'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #683818'>&nbsp;@&nbsp;</font><font style='background-color: #683818; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #683818;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #683818; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int CHOCOLATE = 0xfe88864a;
    static { NAMED.put("chocolate", 0xfe88864a); LIST.add(0xfe88864a); }

    /**
     * This color constant "tan" has RGBA8888 code {@code D2B48CFF}, L 0.7019608, A 0.5058824, B 0.5254902, alpha 1.0, hue 0.21390288, saturation 0.08232166, and chroma 0.052115876.
     * It has the encoded Oklab value 0xfe8681b3 .
     * <pre>
     * <font style='background-color: #D2B48C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2B48C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2B48C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D2B48C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D2B48C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D2B48C'>&nbsp;@&nbsp;</font><font style='background-color: #D2B48C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2B48C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2B48C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TAN = 0xfe8681b3;
    static { NAMED.put("tan", 0xfe8681b3); LIST.add(0xfe8681b3); }

    /**
     * This color constant "bronze" has RGBA8888 code {@code CE8E31FF}, L 0.5921569, A 0.5137255, B 0.56078434, alpha 1.0, hue 0.21465261, saturation 0.5864422, and chroma 0.124142565.
     * It has the encoded Oklab value 0xfe8f8397 .
     * <pre>
     * <font style='background-color: #CE8E31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CE8E31; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CE8E31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CE8E31'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CE8E31'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CE8E31'>&nbsp;@&nbsp;</font><font style='background-color: #CE8E31; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CE8E31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CE8E31; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRONZE = 0xfe8f8397;
    static { NAMED.put("bronze", 0xfe8f8397); LIST.add(0xfe8f8397); }

    /**
     * This color constant "cinnamon" has RGBA8888 code {@code D2691DFF}, L 0.52156866, A 0.54509807, B 0.56078434, alpha 1.0, hue 0.14839543, saturation 0.7197198, and chroma 0.15078327.
     * It has the encoded Oklab value 0xfe8f8b85 .
     * <pre>
     * <font style='background-color: #D2691D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2691D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2691D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D2691D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D2691D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D2691D'>&nbsp;@&nbsp;</font><font style='background-color: #D2691D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2691D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2691D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int CINNAMON = 0xfe8f8b85;
    static { NAMED.put("cinnamon", 0xfe8f8b85); LIST.add(0xfe8f8b85); }

    /**
     * This color constant "apricot" has RGBA8888 code {@code FFA828FF}, L 0.7137255, A 0.52156866, B 0.5764706, alpha 1.0, hue 0.20625468, saturation 0.75939417, and chroma 0.1582875.
     * It has the encoded Oklab value 0xfe9385b6 .
     * <pre>
     * <font style='background-color: #FFA828;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA828; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA828;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFA828'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFA828'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFA828'>&nbsp;@&nbsp;</font><font style='background-color: #FFA828; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA828;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA828; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int APRICOT = 0xfe9385b6;
    static { NAMED.put("apricot", 0xfe9385b6); LIST.add(0xfe9385b6); }

    /**
     * This color constant "peach" has RGBA8888 code {@code FFBF81FF}, L 0.78039217, A 0.5176471, B 0.54509807, alpha 1.0, hue 0.19064914, saturation 0.61476445, and chroma 0.096477255.
     * It has the encoded Oklab value 0xfe8b84c7 .
     * <pre>
     * <font style='background-color: #FFBF81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFBF81; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFBF81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFBF81'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFBF81'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFBF81'>&nbsp;@&nbsp;</font><font style='background-color: #FFBF81; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFBF81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFBF81; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int PEACH = 0xfe8b84c7;
    static { NAMED.put("peach", 0xfe8b84c7); LIST.add(0xfe8b84c7); }

    /**
     * This color constant "pear" has RGBA8888 code {@code D3E330FF}, L 0.8156863, A 0.45882353, B 0.58431375, alpha 1.0, hue 0.32230157, saturation 0.7434462, and chroma 0.18692946.
     * It has the encoded Oklab value 0xfe9575d0 .
     * <pre>
     * <font style='background-color: #D3E330;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3E330; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3E330;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D3E330'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D3E330'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D3E330'>&nbsp;@&nbsp;</font><font style='background-color: #D3E330; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3E330;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3E330; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int PEAR = 0xfe9575d0;
    static { NAMED.put("pear", 0xfe9575d0); LIST.add(0xfe9575d0); }

    /**
     * This color constant "saffron" has RGBA8888 code {@code FFD510FF}, L 0.8235294, A 0.4862745, B 0.5882353, alpha 1.0, hue 0.27457327, saturation 0.8132536, and chroma 0.17789528.
     * It has the encoded Oklab value 0xfe967cd2 .
     * <pre>
     * <font style='background-color: #FFD510;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD510; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD510;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFD510'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFD510'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFD510'>&nbsp;@&nbsp;</font><font style='background-color: #FFD510; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD510;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD510; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int SAFFRON = 0xfe967cd2;
    static { NAMED.put("saffron", 0xfe967cd2); LIST.add(0xfe967cd2); }

    /**
     * This color constant "butter" has RGBA8888 code {@code FFF288FF}, L 0.92156863, A 0.4862745, B 0.5568628, alpha 1.0, hue 0.28769454, saturation 0.42989978, and chroma 0.11653464.
     * It has the encoded Oklab value 0xfe8e7ceb .
     * <pre>
     * <font style='background-color: #FFF288;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFF288; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFF288;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFF288'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFF288'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFF288'>&nbsp;@&nbsp;</font><font style='background-color: #FFF288; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFF288;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFF288; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BUTTER = 0xfe8e7ceb;
    static { NAMED.put("butter", 0xfe8e7ceb); LIST.add(0xfe8e7ceb); }

    /**
     * This color constant "chartreuse" has RGBA8888 code {@code C8FF41FF}, L 0.8901961, A 0.4392157, B 0.58431375, alpha 1.0, hue 0.34942675, saturation 0.82109725, and chroma 0.2070681.
     * It has the encoded Oklab value 0xfe9570e3 .
     * <pre>
     * <font style='background-color: #C8FF41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8FF41; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8FF41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C8FF41'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C8FF41'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C8FF41'>&nbsp;@&nbsp;</font><font style='background-color: #C8FF41; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8FF41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8FF41; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int CHARTREUSE = 0xfe9570e3;
    static { NAMED.put("chartreuse", 0xfe9570e3); LIST.add(0xfe9570e3); }

    /**
     * This color constant "cactus" has RGBA8888 code {@code 30A000FF}, L 0.5058824, A 0.41960785, B 0.5647059, alpha 1.0, hue 0.39212817, saturation 0.809409, and chroma 0.20558903.
     * It has the encoded Oklab value 0xfe906b81 .
     * <pre>
     * <font style='background-color: #30A000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #30A000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #30A000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #30A000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #30A000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #30A000'>&nbsp;@&nbsp;</font><font style='background-color: #30A000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #30A000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #30A000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int CACTUS = 0xfe906b81;
    static { NAMED.put("cactus", 0xfe906b81); LIST.add(0xfe906b81); }

    /**
     * This color constant "lime" has RGBA8888 code {@code 93D300FF}, L 0.7058824, A 0.43529412, B 0.5803922, alpha 1.0, hue 0.3578718, saturation 0.78243065, and chroma 0.20558903.
     * It has the encoded Oklab value 0xfe946fb4 .
     * <pre>
     * <font style='background-color: #93D300;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #93D300; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #93D300;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #93D300'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #93D300'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #93D300'>&nbsp;@&nbsp;</font><font style='background-color: #93D300; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #93D300;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #93D300; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int LIME = 0xfe946fb4;
    static { NAMED.put("lime", 0xfe946fb4); LIST.add(0xfe946fb4); }

    /**
     * This color constant "olive" has RGBA8888 code {@code 818000FF}, L 0.4745098, A 0.4745098, B 0.56078434, alpha 1.0, hue 0.31318712, saturation 0.76235455, and chroma 0.13131043.
     * It has the encoded Oklab value 0xfe8f7979 .
     * <pre>
     * <font style='background-color: #818000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #818000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #818000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #818000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #818000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #818000'>&nbsp;@&nbsp;</font><font style='background-color: #818000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #818000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #818000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int OLIVE = 0xfe8f7979;
    static { NAMED.put("olive", 0xfe8f7979); LIST.add(0xfe8f7979); }

    /**
     * This color constant "fern" has RGBA8888 code {@code 4E7942FF}, L 0.41960785, A 0.4627451, B 0.5294118, alpha 1.0, hue 0.3936267, saturation 0.22978139, and chroma 0.0945603.
     * It has the encoded Oklab value 0xfe87766b .
     * <pre>
     * <font style='background-color: #4E7942;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E7942; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E7942;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4E7942'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4E7942'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4E7942'>&nbsp;@&nbsp;</font><font style='background-color: #4E7942; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E7942;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E7942; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int FERN = 0xfe87766b;
    static { NAMED.put("fern", 0xfe87766b); LIST.add(0xfe87766b); }

    /**
     * This color constant "moss" has RGBA8888 code {@code 204608FF}, L 0.25490198, A 0.45882353, B 0.5372549, alpha 1.0, hue 0.38294512, saturation 0.6363817, and chroma 0.11062346.
     * It has the encoded Oklab value 0xfe897541 .
     * <pre>
     * <font style='background-color: #204608;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #204608; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #204608;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #204608'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #204608'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #204608'>&nbsp;@&nbsp;</font><font style='background-color: #204608; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #204608;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #204608; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int MOSS = 0xfe897541;
    static { NAMED.put("moss", 0xfe897541); LIST.add(0xfe897541); }

    /**
     * This color constant "celery" has RGBA8888 code {@code 7DFF73FF}, L 0.8392157, A 0.41960785, B 0.56078434, alpha 1.0, hue 0.39695174, saturation 0.77200675, and chroma 0.20078278.
     * It has the encoded Oklab value 0xfe8f6bd6 .
     * <pre>
     * <font style='background-color: #7DFF73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7DFF73; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7DFF73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7DFF73'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7DFF73'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7DFF73'>&nbsp;@&nbsp;</font><font style='background-color: #7DFF73; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7DFF73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7DFF73; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int CELERY = 0xfe8f6bd6;
    static { NAMED.put("celery", 0xfe8f6bd6); LIST.add(0xfe8f6bd6); }

    /**
     * This color constant "sage" has RGBA8888 code {@code ABE3C5FF}, L 0.80784315, A 0.46666667, B 0.50980395, alpha 1.0, hue 0.45448294, saturation 0.118390046, and chroma 0.069218926.
     * It has the encoded Oklab value 0xfe8277ce .
     * <pre>
     * <font style='background-color: #ABE3C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABE3C5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABE3C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ABE3C5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ABE3C5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ABE3C5'>&nbsp;@&nbsp;</font><font style='background-color: #ABE3C5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABE3C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABE3C5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int SAGE = 0xfe8277ce;
    static { NAMED.put("sage", 0xfe8277ce); LIST.add(0xfe8277ce); }

    /**
     * This color constant "jade" has RGBA8888 code {@code 3FBF3FFF}, L 0.6039216, A 0.41568628, B 0.5568628, alpha 1.0, hue 0.4055531, saturation 0.73492247, and chroma 0.20259848.
     * It has the encoded Oklab value 0xfe8e6a9a .
     * <pre>
     * <font style='background-color: #3FBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3FBF3F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3FBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3FBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3FBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3FBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #3FBF3F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3FBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3FBF3F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int JADE = 0xfe8e6a9a;
    static { NAMED.put("jade", 0xfe8e6a9a); LIST.add(0xfe8e6a9a); }

    /**
     * This color constant "cyan" has RGBA8888 code {@code 00FFFFFF}, L 0.84313726, A 0.42352942, B 0.47843137, alpha 1.0, hue 0.54374534, saturation 0.9090657, and chroma 0.1582875.
     * It has the encoded Oklab value 0xfe7a6cd7 .
     * <pre>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #00FFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int CYAN = 0xfe7a6cd7;
    static { NAMED.put("cyan", 0xfe7a6cd7); LIST.add(0xfe7a6cd7); }

    /**
     * This color constant "mint" has RGBA8888 code {@code 7FFFD4FF}, L 0.8666667, A 0.4392157, B 0.50980395, alpha 1.0, hue 0.47453672, saturation 0.74010134, and chroma 0.12265874.
     * It has the encoded Oklab value 0xfe8270dd .
     * <pre>
     * <font style='background-color: #7FFFD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7FFFD4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7FFFD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7FFFD4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7FFFD4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7FFFD4'>&nbsp;@&nbsp;</font><font style='background-color: #7FFFD4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7FFFD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7FFFD4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int MINT = 0xfe8270dd;
    static { NAMED.put("mint", 0xfe8270dd); LIST.add(0xfe8270dd); }

    /**
     * This color constant "teal" has RGBA8888 code {@code 007F7FFF}, L 0.42745098, A 0.4509804, B 0.4862745, alpha 1.0, hue 0.543443, saturation 0.89123964, and chroma 0.10141215.
     * It has the encoded Oklab value 0xfe7c736d .
     * <pre>
     * <font style='background-color: #007F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007F7F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #007F7F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #007F7F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #007F7F'>&nbsp;@&nbsp;</font><font style='background-color: #007F7F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007F7F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TEAL = 0xfe7c736d;
    static { NAMED.put("teal", 0xfe7c736d); LIST.add(0xfe7c736d); }

    /**
     * This color constant "turquoise" has RGBA8888 code {@code 2ED6C9FF}, L 0.7019608, A 0.43137255, B 0.49019608, alpha 1.0, hue 0.52259654, saturation 0.8433125, and chroma 0.1381068.
     * It has the encoded Oklab value 0xfe7d6eb3 .
     * <pre>
     * <font style='background-color: #2ED6C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2ED6C9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2ED6C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2ED6C9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2ED6C9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2ED6C9'>&nbsp;@&nbsp;</font><font style='background-color: #2ED6C9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2ED6C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2ED6C9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TURQUOISE = 0xfe7d6eb3;
    static { NAMED.put("turquoise", 0xfe7d6eb3); LIST.add(0xfe7d6eb3); }

    /**
     * This color constant "sky" has RGBA8888 code {@code 10C0E0FF}, L 0.6431373, A 0.44313726, B 0.4627451, alpha 1.0, hue 0.59232193, saturation 0.8547556, and chroma 0.13542919.
     * It has the encoded Oklab value 0xfe7671a4 .
     * <pre>
     * <font style='background-color: #10C0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #10C0E0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #10C0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #10C0E0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #10C0E0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #10C0E0'>&nbsp;@&nbsp;</font><font style='background-color: #10C0E0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #10C0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #10C0E0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int SKY = 0xfe7671a4;
    static { NAMED.put("sky", 0xfe7671a4); LIST.add(0xfe7671a4); }

    /**
     * This color constant "cobalt" has RGBA8888 code {@code 0046ABFF}, L 0.31764707, A 0.48235294, B 0.4117647, alpha 1.0, hue 0.71857655, saturation 0.73578477, and chroma 0.1792624.
     * It has the encoded Oklab value 0xfe697b51 .
     * <pre>
     * <font style='background-color: #0046AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0046AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0046AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0046AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0046AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0046AB'>&nbsp;@&nbsp;</font><font style='background-color: #0046AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0046AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0046AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int COBALT = 0xfe697b51;
    static { NAMED.put("cobalt", 0xfe697b51); LIST.add(0xfe697b51); }

    /**
     * This color constant "denim" has RGBA8888 code {@code 3088B8FF}, L 0.4862745, A 0.46666667, B 0.45490196, alpha 1.0, hue 0.6486837, saturation 0.5816889, and chroma 0.11172148.
     * It has the encoded Oklab value 0xfe74777c .
     * <pre>
     * <font style='background-color: #3088B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3088B8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3088B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3088B8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3088B8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3088B8'>&nbsp;@&nbsp;</font><font style='background-color: #3088B8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3088B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3088B8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DENIM = 0xfe74777c;
    static { NAMED.put("denim", 0xfe74777c); LIST.add(0xfe74777c); }

    /**
     * This color constant "navy" has RGBA8888 code {@code 000080FF}, L 0.18431373, A 0.4862745, B 0.4, alpha 1.0, hue 0.7282781, saturation 0.96145123, and chroma 0.20108652.
     * It has the encoded Oklab value 0xfe667c2f .
     * <pre>
     * <font style='background-color: #000080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000080; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000080'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000080'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000080'>&nbsp;@&nbsp;</font><font style='background-color: #000080; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000080; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int NAVY = 0xfe667c2f;
    static { NAMED.put("navy", 0xfe667c2f); LIST.add(0xfe667c2f); }

    /**
     * This color constant "lavender" has RGBA8888 code {@code B991FFFF}, L 0.6431373, A 0.53333336, B 0.43529412, alpha 1.0, hue 0.82570946, saturation 0.84011585, and chroma 0.14500555.
     * It has the encoded Oklab value 0xfe6f88a4 .
     * <pre>
     * <font style='background-color: #B991FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B991FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B991FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B991FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B991FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B991FF'>&nbsp;@&nbsp;</font><font style='background-color: #B991FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B991FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B991FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int LAVENDER = 0xfe6f88a4;
    static { NAMED.put("lavender", 0xfe6f88a4); LIST.add(0xfe6f88a4); }

    /**
     * This color constant "plum" has RGBA8888 code {@code BE0DC6FF}, L 0.44313726, A 0.6117647, B 0.42352942, alpha 1.0, hue 0.90448654, saturation 0.9074911, and chroma 0.26978588.
     * It has the encoded Oklab value 0xfe6c9c71 .
     * <pre>
     * <font style='background-color: #BE0DC6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE0DC6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE0DC6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BE0DC6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BE0DC6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BE0DC6'>&nbsp;@&nbsp;</font><font style='background-color: #BE0DC6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE0DC6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE0DC6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int PLUM = 0xfe6c9c71;
    static { NAMED.put("plum", 0xfe6c9c71); LIST.add(0xfe6c9c71); }

    /**
     * This color constant "mauve" has RGBA8888 code {@code AB73ABFF}, L 0.5254902, A 0.5411765, B 0.47058824, alpha 1.0, hue 0.90127134, saturation 0.10026723, and chroma 0.1008085.
     * It has the encoded Oklab value 0xfe788a86 .
     * <pre>
     * <font style='background-color: #AB73AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB73AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB73AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB73AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB73AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB73AB'>&nbsp;@&nbsp;</font><font style='background-color: #AB73AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB73AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB73AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int MAUVE = 0xfe788a86;
    static { NAMED.put("mauve", 0xfe788a86); LIST.add(0xfe788a86); }

    /**
     * This color constant "rose" has RGBA8888 code {@code E61E78FF}, L 0.4745098, A 0.6156863, B 0.49411765, alpha 1.0, hue 0.99192464, saturation 0.789191, and chroma 0.23076649.
     * It has the encoded Oklab value 0xfe7e9d79 .
     * <pre>
     * <font style='background-color: #E61E78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E61E78; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E61E78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E61E78'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E61E78'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E61E78'>&nbsp;@&nbsp;</font><font style='background-color: #E61E78; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E61E78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E61E78; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int ROSE = 0xfe7e9d79;
    static { NAMED.put("rose", 0xfe7e9d79); LIST.add(0xfe7e9d79); }

    /**
     * This color constant "raspberry" has RGBA8888 code {@code 911437FF}, L 0.3137255, A 0.5803922, B 0.5137255, alpha 1.0, hue 0.02692472, saturation 0.73546606, and chroma 0.16247371.
     * It has the encoded Oklab value 0xfe839450 .
     * <pre>
     * <font style='background-color: #911437;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #911437; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #911437;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #911437'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #911437'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #911437'>&nbsp;@&nbsp;</font><font style='background-color: #911437; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #911437;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #911437; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int RASPBERRY = 0xfe839450;
    static { NAMED.put("raspberry", 0xfe839450); LIST.add(0xfe839450); }

    /**
     * All names for colors in this palette, in alphabetical order. You can fetch the corresponding packed int color
     * by looking up a name in {@link #NAMED}.
     */
    public static final ObjectList<String> NAMES = NAMED.order();

    static {
        NAMED.setDefaultValue(TRANSPARENT);
        NAMES.sort(null);
    }

    /**
     * All names for colors in this palette, with grayscale first, then sorted by hue from red to yellow to green to
     * blue. You can fetch the corresponding packed int color by looking up a name in {@link #NAMED}.
     */
    public static final ObjectList<String> NAMES_BY_HUE = new ObjectList<>(NAMES);
    /**
     * The packed Oklab int colors that correspond to items in {@link #NAMES_BY_HUE}, with the same order.
     */
    public static final IntList COLORS_BY_HUE = new IntList(NAMES_BY_HUE.size());
    /**
     * All names for colors in this palette, sorted by lightness from black to white. You can fetch the
     * corresponding packed int color by looking up a name in {@link #NAMED}.
     */
    public static final ObjectList<String> NAMES_BY_LIGHTNESS = new ObjectList<>(NAMES);

    /**
     *  Linearly interpolates between fromValue to toValue on progress position.
     * @param fromValue starting float value; can be any finite float
     * @param toValue ending float value; can be any finite float
     * @param progress how far the interpolation should go, between 0 (equal to fromValue) and 1 (equal to toValue)
     */
    public static float lerp (final float fromValue, final float toValue, final float progress) {
        return fromValue + (toValue - fromValue) * progress;
    }

    /**
     * An approximation of the cube-root function for float inputs and outputs.
     * This can be about twice as fast as {@link Math#cbrt(double)}. It
     * correctly returns negative results when given negative inputs.
     * <br>
     * Has very low relative error (less than 1E-9) when inputs are uniformly
     * distributed between -512 and 512, and absolute mean error of less than
     * 1E-6 in the same scenario. Uses a bit-twiddling method similar to one
     * presented in Hacker's Delight and also used in early 3D graphics (see
     * https://en.wikipedia.org/wiki/Fast_inverse_square_root for more, but
     * this code approximates cbrt(x) and not 1/sqrt(x)). This specific code
     * was originally by Marc B. Reynolds, posted in his "Stand-alone-junk"
     * repo: https://github.com/Marc-B-Reynolds/Stand-alone-junk/blob/master/src/Posts/ballcube.c#L182-L197 .
     * <br>
     * This is used when converting from RGB to Oklab, as an intermediate step.
     * @param x any finite float to find the cube root of
     * @return the cube root of x, approximated
     */
    private static float cbrt(float x) {
        int ix = BitConversion.floatToRawIntBits(x);
        final int sign = ix & 0x80000000;
        ix &= 0x7FFFFFFF;
        final float x0 = x;
        ix = (ix>>>2) + (ix>>>4);
        ix += (ix>>>4);
        ix = ix + (ix>>>8) + 0x2A5137A0 | sign;
        x  = BitConversion.intBitsToFloat(ix);
        x  = 0.33333334f*(2f * x + x0/(x*x));
        x  = 0.33333334f*(2f * x + x0/(x*x));
        return x;
    }

    /**
     * Used when converting from Oklab to RGB, as an intermediate step.
     * Really just {@code x * x * x}.
     * @param x one of the LMS Prime channels to be converted to LMS
     * @return an LMS channel value, which can be converted to RGB
     */
    private static float cube(final float x) {
        return x * x * x;
    }

    /**
     * Used when given non-linear sRGB inputs to make them linear, approximating with gamma 2.0.
     * Really just {@code component * component}.
     * @param component any non-linear channel of a color, to be made linear
     * @return a linear version of component
     */
    private static float forwardGamma(final float component) {
        return component * component;
    }

    /**
     * Used to return from a linear, gamma-corrected input to an sRGB, non-linear output, using gamma 2.0.
     * Really just gets the square root of component, as a float.
     * @param component a linear channel of a color, to be made non-linear
     * @return a non-linear version of component
     */
    private static float reverseGamma(final float component) {
        return (float)Math.sqrt(component);
    }

    /**
     * Changes the curve of a requested L value so that it matches the internally-used curve. This takes a curve with a
     * very-dark area similar to sRGB (a very small one), and makes it significantly larger. This is typically used on
     * "to Oklab" conversions.
     * @param L lightness, from 0 to 1 inclusive
     * @return an adjusted L value that can be used internally
     */
    public static float forwardLight(final float L) {
        return (L - 1.004f) / (1f - L * 0.4285714f) + 1.004f;
    }

    /**
     * Changes the curve of the internally-used lightness when it is output to another format. This makes the very-dark
     * area smaller, matching (kind-of) the curve that the standard sRGB lightness uses. This is typically used on "from
     * Oklab" conversions.
     * @param L lightness, from 0 to 1 inclusive
     * @return an adjusted L value that can be fed into a conversion to RGBA or something similar
     */
    public static float reverseLight(final float L) {
        return (L - 0.993f) / (1f + L * 0.75f) + 0.993f;
    }

    /**
     * Converts a packed Oklab int color in the format used by constants in this class to an RGBA8888 int.
     * This format of int can be used with Pixmap and in some other places in libGDX.
     * @param oklab a packed int color, as from a constant in this class
     * @return an RGBA8888 int color
     */
    public static int toRGBA8888(final int oklab)
    {
        final float L = reverseLight((oklab & 0xff) / 255f);
        final float A = ((oklab >>> 8 & 0xff) - 127.5f) / 127.5f;
        final float B = ((oklab >>> 16 & 0xff) - 127.5f) / 127.5f;
        final float l = cube(L + 0.3963377774f * A + 0.2158037573f * B);
        final float m = cube(L - 0.1055613458f * A - 0.0638541728f * B);
        final float s = cube(L - 0.0894841775f * A - 1.2914855480f * B);
        final int r = (int)(reverseGamma(Math.min(Math.max(+4.0767245293f * l - 3.3072168827f * m + 0.2307590544f * s, 0f), 1f)) * 255.999f);
        final int g = (int)(reverseGamma(Math.min(Math.max(-1.2681437731f * l + 2.6093323231f * m - 0.3411344290f * s, 0f), 1f)) * 255.999f);
        final int b = (int)(reverseGamma(Math.min(Math.max(-0.0041119885f * l - 0.7034763098f * m + 1.7068625689f * s, 0f), 1f)) * 255.999f);
        return r << 24 | g << 16 | b << 8 | (oklab & 0xfe000000) >>> 24 | oklab >>> 31;
    }

    /**
     * Gets the chroma or "colorfulness" of the given encoded color, as a non-negative float. This is like the
     * saturation component of HSL or HSV, but where saturation is always 1.0 when a color is the most colorful possible
     * given its combination of hue and lightness, chroma can be lower if the most colorful possible value isn't as
     * colorful as some other combination. This means chroma has a smaller range of values when L is high or low, and a
     * larger range when L is near 0.45 to 0.65, roughly, because high and low L approach white and black, respectively,
     * while mid-range L values are the most colorful.
     *
     * @param oklab a color as an Oklab int that can be obtained from any of the constants in this class.
     * @return the chroma of the color from 0.0 (a grayscale color; inclusive) to at-most the square root of 2 (but probably lower; a bright color)
     */
    public static float chroma(final int oklab) {
        final float a = ((oklab >>> 7 & 0x1FE) - 255) * 0x1p-8f;
        final float b = ((oklab >>> 15 & 0x1FE) - 255) * 0x1p-8f;
        return (float) Math.sqrt(a * a + b * b);
    }

    /**
     * Gets the hue of the given encoded color, as a float from 0f (inclusive, red and approaching orange if increased)
     * to 1f (exclusive, red and approaching purple if decreased). This is not exactly the same as HSL's or HSV's hue.
     *
     * @param oklab a color as an Oklab int that can be obtained from any of the constants in this class.
     * @return The hue of the color from 0.0 (red, inclusive) towards orange, then yellow, and
     * eventually to purple before looping back to almost the same red (1.0, exclusive)
     */
    public static float hue(final int oklab) {
        final float a = ((oklab >>> 7 & 0x1FE) - 255);
        final float b = ((oklab >>> 15 & 0x1FE) - 255);
        // atan2_(y, x) , but inlined so this has no external dependencies other than jdkgdxds
        //if(a == 0.0 && b >= 0.0) return 0f; // not possible
        float ay = Math.abs(a), ax = Math.abs(b);
        boolean invert = ay > ax;
        float z = invert ? ax/ay : ay/ax;
        z = (((((0.022520265292560102f) * z) - (0.054640279287594046f)) * z - (0.0025821297967229097f)) * z + (0.1597659389184251f)) * z - (0.000025146481008519463f);
        if(invert) z = 0.25f - z;
        if(b < 0) z = 0.5f - z;
        return a < 0 ? (int)(1+z) - z : z;

    }

    /**
     * Interpolates from the packed Oklab int color oklab towards white by change. While change should be between 0f
     * (return oklab as-is) and 1f (return white), oklab should be a packed color, as from a constant here. This method
     * does not necessarily keep the resulting color in-gamut; after performing some changes with this or other
     * component-editing methods, you may want to call {@link #limitToGamut(int)} to make sure the color can be rendered
     * correctly.
     *
     * @param oklab      the starting color as an Oklab int
     * @param change how much to go from oklab toward white, as a float between 0 and 1; higher means closer to white
     * @return a packed Oklab int that represents a color between start and white
     * @see #darken(int, float) the counterpart method that darkens a float color
     */
    public static int lighten(final int oklab, final float change) {
        final int L = oklab & 255, other = oklab & 0xFEFFFF00;
        return (((int) (L + (255 - L) * change) & 255) | other);
    }

    /**
     * Interpolates from the packed Oklab int color oklab towards black by change. While change should be between 0f
     * (return oklab as-is) and 1f (return black), oklab should be a packed color, as from a constant here. This method
     * does not necessarily keep the resulting color in-gamut; after performing some changes with this or other
     * component-editing methods, you may want to call {@link #limitToGamut(int)} to make sure the color can be rendered
     * correctly.
     *
     * @param oklab      the starting color as a packed float
     * @param change how much to go from oklab toward black, as a float between 0 and 1; higher means closer to black
     * @return a packed float that represents a color between start and black
     * @see #lighten(int, float) the counterpart method that lightens a float color
     */
    public static int darken(final int oklab, final float change) {
        final int i = oklab & 255, other = oklab & 0xFEFFFF00;
        return (((int) (i * (1f - change)) & 255) | other);
    }

    /**
     * Brings the chromatic components of {@code oklab} closer to grayscale by {@code change} (desaturating them). While
     * change should be between 0f (return oklab as-is) and 1f (return fully gray), oklab should be a packed Oklab int
     * color, as from a constant in this class.
     *
     * @param oklab      the starting color as a packed Oklab int
     * @param change how much to change oklab to a desaturated color, as a float between 0 and 1; higher means a less saturated result
     * @return a packed float that represents a color between start and a desaturated color
     * @see #enrich(int, float) the counterpart method that makes an Oklab int color more saturated
     */
    public static int dullen(final int oklab, final float change) {
        return
                (int) (((oklab >>> 8 & 255) - 127.5f) * (1f - change) + 127.5f) << 8 |
                (int) (((oklab >>> 16 & 255) - 127.5f) * (1f - change) + 127.5f) << 16 |
                (oklab & 0xFE0000FF);
    }

    /**
     * Pushes the chromatic components of {@code oklab} away from grayscale by change (saturating them). While change
     * should be between 0f (return oklab as-is) and 1f (return maximally saturated), oklab should be a packed color, as
     * from a constant in this class. This usually changes only A and B, but higher values for {@code change} can
     * force the color out of the gamut, which this corrects using {@link #limitToGamut(int)} (and that can change L
     * somewhat). If the color stays in-gamut, then L won't change; alpha never changes.
     *
     * @param oklab      the starting color as a packed float
     * @param change how much to change oklab to a saturated color, as a float between 0 and 1; higher means a more saturated result
     * @return a packed float that represents a color between start and a saturated color
     * @see #dullen(int, float) the counterpart method that makes a float color less saturated
     */
    public static int enrich(final int oklab, final float change) {
        return limitToGamut(
                (int) (((oklab >>> 8 & 255) - 127.5f) * (1f - change) + 127.5f) << 8 |
                (int) (((oklab >>> 16 & 255) - 127.5f) * (1f - change) + 127.5f) << 16 |
                (oklab & 0xFE0000FF));
    }

    /**
     * Given a 1D int index between 0 and 65535 (both inclusive), this treats the 1D index as two parts (lightness and
     * hue angle, both from 0 to 255) and gets the distance from grayscale to the edge of the gamut at that lightness
     * and hue. The index can be constructed from a lightness value {@code L} from 0 to 255, and a hue value {@code H}
     * from 0 to 255 with: {@code (L << 8 | H)} or the simpler equivalent {@code (L * 256 + H)}. These assume L and H
     * have been limited to the 0 to 255 range already. This does not bounds-check index. Because hue is not typically
     * measured between 0 and 255, getting that value is a bit different; you can use
     * {@link MathTools#atan2_(float, float)} (with an Oklab color's B for y, then its A for x) and multiply it by 256
     * to get H.
     * <br>
     * The distance this returns is a byte between 0 and 82 (both inclusive), as the Euclidean distance from the center
     * grayscale value at the lightness in the index, to the edge of the gamut at the same lightness, along the hue in
     * the index. This is measured in a space from -1 to 1 for both A and B, with the 0 in the center meaning grayscale,
     * and multiplied by 256 to get a meaningful byte value. To return to the A and B values Oklab uses here, you would
     * need to use some trigonometry on the hue (if it's in the 0 to 1 range, you can call
     * {@link MathTools#cos_(float)} on the hue to almost get A, and {@link MathTools#sin_(float)} to almost get B),
     * then multiply each of those by the distance, divide each by 256.0, and add 0.5.
     * <br>
     * Only intended for the narrow cases where external code needs read-only access to the internal Oklab gamut data.
     * The gamut data is quite large (the Oklab ColorTools file is 236 KB at the time of writing, while the IPT_HQ
     * ColorTools file is just 56 KB, with the main difference being the sizable exact gamut), so it's better to have
     * direct read access to it without being able to accidentally rewrite it.
     * @param index must be between 0 and 65535; the upper 8 bits are lightness and the lower 8 are hue angle.
     * @return a byte (always between 0 and 82, inclusive) representing the Euclidean distance between a grayscale value and the most saturated value possible, using the above measurements
     */
    public static byte getRawGamutValue(int index){
        return GAMUT_DATA[index];
    }

    /**
     * Returns true if the given packed int color, as Oklab, is valid to convert losslessly back to RGBA.
     * @param packed a packed int color as Oklab
     * @return true if the given packed int color can be converted back and forth to RGBA
     */
    public static boolean inGamut(final int packed)
    {
        final float A = ((packed >>> 8 & 0xff) - 127f) / 255f;
        final float B = ((packed >>> 16 & 0xff) - 127f) / 255f;
        final float g = GAMUT_DATA[(packed & 0xff) << 8 | (int)(256f * MathTools.atan2_(B, A))];
        return g * g * 0x1p-18 + 0x1p-14 >= (A * A + B * B);
    }

    /**
     * Returns true if the given Oklab values are valid to convert losslessly back to RGBA.
     * @param L lightness channel, as an int from 0 to 255
     * @param A green-to-red chromatic channel, as an int from 0 to 255
     * @param B blue-to-yellow chromatic channel, as an int from 0 to 255
     * @return true if the given Oklab channels can be converted back and forth to RGBA
     */
    public static boolean inGamut(int L, int A, int B)
    {
        float A2 = (A - 127f) / 255f;
        float B2 = (B - 127f) / 255f;
        final float g = GAMUT_DATA[(L & 0xFF) << 8 | (int)(256f * MathTools.atan2_(B2, A2))];
        return g * g * 0x1p-18 + 0x1p-14 >= (A2 * A2 + B2 * B2);
    }

    /**
     * Gets the color with the same L as the Oklab color stored in the given packed int, but the furthest A
     * B from gray possible for that lightness while keeping the same hue as the given color. This is very
     * similar to calling {@link #enrich(int, float)} with a very large {@code change} value.
     * @param packed a packed int color in Oklab format; does not need to be in-gamut
     * @return the color that is as far from grayscale as this can get while keeping the L and hue of packed
     * @see #limitToGamut(int) You can use limitToGamut() if you only want max saturation for out-of-gamut colors.
     */
    public static int maximizeSaturation(final int packed) {
        final float A = ((packed >>> 8 & 0xff) - 127.5f);
        final float B = ((packed >>> 16 & 0xff) - 127.5f);
        final float hue = MathTools.atan2_(B, A);
        final int idx = (packed & 0xff) << 8 | (int) (256f * hue);
        final float dist = GAMUT_DATA[idx] * 0.5f;
        return ((packed & 0xFF0000FF) |
                        (int) (MathTools.cos_(hue) * dist + 128f) << 8 |
                        (int) (MathTools.sin_(hue) * dist + 128f) << 16);
    }

    /**
     * Gets the color with the same L as in the given Oklab color channels, but the furthest A
     * B from gray possible for that lightness while keeping the same hue as the given color. This is very
     * similar to calling {@link #enrich(int, float)} with a very large {@code change} value.
     * @param L lightness component; will be clamped between 0 and 255 if it isn't already
     * @param A green-to-red chromatic component; will be clamped between 0 and 255 if it isn't already
     * @param B blue-to-yellow chromatic component; will be clamped between 0 and 255 if it isn't already
     * @param alpha alpha component; will be clamped between 0 and 255 if it isn't already
     * @return the color that is as far from grayscale as this can get while keeping the L and hue of packed
     * @see #limitToGamut(int, int, int, int) You can use limitToGamut() if you only want max saturation for out-of-gamut colors.
     */
    public static int maximizeSaturation(int L, int A, int B, int alpha) {
        L = Math.min(Math.max(L, 0), 255);
        A = Math.min(Math.max(A, 0), 255);
        B = Math.min(Math.max(B, 0), 255);
        alpha = Math.min(Math.max(alpha, 0), 255);
        final float A2 = (A - 127.5f);
        final float B2 = (B - 127.5f);
        final float hue = MathTools.atan2_(B2, A2);
        final int idx = L << 8 | (int)(256f * hue);
        final float dist = GAMUT_DATA[idx] * 0.5f;
        return (
                alpha << 24 |
                        (int) (MathTools.sin_(hue) * dist + 128f) << 16 |
                        (int) (MathTools.cos_(hue) * dist + 128f) << 8 |
                        L);
    }
    /**
     * Checks whether the given Oklab color is in-gamut; if it isn't in-gamut, brings the color just inside
     * the gamut at the same lightness, or if it is already in-gamut, returns the color as-is.
     * @param packed a packed int color in Oklab format; often this color is not in-gamut
     * @return the first color this finds that is in-gamut, as if it was moving toward a grayscale color with the same L
     * @see #inGamut(int) You can use inGamut() if you just want to check whether a color is in-gamut.
     */
    public static int limitToGamut(final int packed) {
        final float A = ((packed >>> 8 & 0xff) - 127.5f) / 127.5f;
        final float B = ((packed >>> 16 & 0xff) - 127.5f) / 127.5f;
        final float hue = MathTools.atan2_(B, A);
        final int idx = (packed & 0xff) << 8 | (int) (256f * hue);
        final float dist = GAMUT_DATA[idx] * 0x1p-8f;
        if (dist >= (float) Math.sqrt(A * A + B * B))
            return packed;
        return (
                (packed & 0xFF0000FF) |
                        (int) (MathTools.cos_(hue) * dist * 127.999f + 127.999f) << 8 |
                        (int) (MathTools.sin_(hue) * dist * 127.999f + 127.999f) << 16);
    }

    /**
     * Checks whether the given Oklab color is in-gamut; if it isn't in-gamut, brings the color just inside
     * the gamut at the same lightness, or if it is already in-gamut, returns the color as-is. This always produces
     * an opaque color.
     * @param L lightness component; will be clamped between 0 and 1 if it isn't already
     * @param A green-to-red chromatic component; will be clamped between 0 and 1 if it isn't already
     * @param B blue-to-yellow chromatic component; will be clamped between 0 and 1 if it isn't already
     * @return the first color this finds that is in-gamut, as if it was moving toward a grayscale color with the same L
     * @see #inGamut(int, int, int) You can use inGamut() if you just want to check whether a color is in-gamut.
     */
    public static float limitToGamut(int L, int A, int B) {
        return limitToGamut(L, A, B, 255);
    }

    /**
     * Checks whether the given Oklab color is in-gamut; if it isn't in-gamut, brings the color just inside
     * the gamut at the same lightness, or if it is already in-gamut, returns the color as-is.
     * @param L lightness component; will be clamped between 0 and 1 if it isn't already
     * @param A green-to-red chromatic component; will be clamped between 0 and 1 if it isn't already
     * @param B blue-to-yellow chromatic component; will be clamped between 0 and 1 if it isn't already
     * @param alpha alpha component; will be clamped between 0 and 1 if it isn't already
     * @return the first color this finds that is in-gamut, as if it was moving toward a grayscale color with the same L
     * @see #inGamut(int, int, int) You can use inGamut() if you just want to check whether a color is in-gamut.
     */
    public static float limitToGamut(int L, int A, int B, int alpha) {
        L = Math.min(Math.max(L, 0), 255);
        A = Math.min(Math.max(A, 0), 255);
        B = Math.min(Math.max(B, 0), 255);
        alpha = Math.min(Math.max(alpha, 0), 255);
        final float A2 = (A - 127.5f) / 127.5f;
        final float B2 = (B - 127.5f) / 127.5f;
        final float hue = MathTools.atan2_(B2, A2);
        final int idx = L << 8 | (int)(256f * hue);
        final float dist = GAMUT_DATA[idx] * 0x1p-8f;
        if(dist >= (float) Math.sqrt(A2 * A2 + B2 * B2))
            return (L | A << 8 | B << 16 | alpha << 24);
        return (
                alpha << 24 |
                        (int) (MathTools.sin_(hue) * dist * 127.999f + 127.999f) << 16 |
                        (int) (MathTools.cos_(hue) * dist * 127.999f + 127.999f) << 8 |
                        L);
    }

    /**
     * Interpolates from the packed int color start towards end by change. Both start and end should be packed Oklab
     * ints, and change can be between 0f (keep start) and 1f (only use end). This is a good way to reduce allocations
     * of temporary Colors.
     *
     * @param s      the starting color as a packed int
     * @param e      the end/target color as a packed int
     * @param change how much to go from start toward end, as a float between 0 and 1; higher means closer to end
     * @return a packed Oklab int that represents a color between start and end
     */
    public static int lerpColors(final int s, final int e, final float change) {
        final int
                sL = (s & 0xFF), sA = (s >>> 8) & 0xFF, sB = (s >>> 16) & 0xFF, sAlpha = s >>> 24 & 0xFE,
                eL = (e & 0xFF), eA = (e >>> 8) & 0xFF, eB = (e >>> 16) & 0xFF, eAlpha = e >>> 24 & 0xFE;
        return (((int) (sL + change * (eL - sL)) & 0xFF)
                | (((int) (sA + change * (eA - sA)) & 0xFF) << 8)
                | (((int) (sB + change * (eB - sB)) & 0xFF) << 16)
                | (((int) (sAlpha + change * (eAlpha - sAlpha)) & 0xFE) << 24));
    }

    /**
     * Given several colors, this gets an even mix of all colors in equal measure.
     * If {@code colors} is null or has no items, this returns {@link #TRANSPARENT}.
     *
     * @param colors an array or varargs of packed int colors; all should use the same color space
     * @param offset the index of the first item in {@code colors} to use
     * @param size   how many items from {@code colors} to use
     * @return an even mix of all colors given, as a packed int color
     */
    public static int mix(int[] colors, int offset, int size) {
        if (colors == null || colors.length < offset + size || offset < 0 || size <= 0)
            return TRANSPARENT;
        int result = colors[offset];
        for (int i = offset + 1, o = offset + size, denom = 2; i < o; i++, denom++) {
            result = lerpColors(result, colors[i], 1f / denom);
        }
        return result;
    }


    static {
        NAMES_BY_HUE.sort((o1, o2) -> {
            final int c1 = NAMED.get(o1), c2 = NAMED.get(o2);
            if((c1 & 0x80) == 0) return -10000;
            else if((c2 & 0x80) == 0) return 10000;

            final float s1 = chroma(c1), s2 = chroma(c2);
            if (s1 <= 0x1p-6f && s2 > 0x1p-6f)
                return -1000;
            else if (s1 > 0x1p-6f && s2 <= 0x1p-6f)
                return 1000;
            else if (s1 <= 0x1p-6f && s2 <= 0x1p-6f)
                return (c1 & 255) - (c2 & 255);
            else
                return ((int) Math.signum(hue(c1) - hue(c2)) << 8)
                        + (c1 & 255) - (c2 & 255);
        });
        for(String name : NAMES_BY_HUE) {
            COLORS_BY_HUE.add(NAMED.get(name));
        }
        NAMES_BY_LIGHTNESS.sort((o1, o2) -> (NAMED.get(o1) & 255) - (NAMED.get(o2) & 255));
    }

    private static final IntList mixing = new IntList(4);

    /**
     * Parses a color description and returns the approximate color it describes, as a packed Oklab int color.
     * Color descriptions consist of one or more lower-case words, separated by non-alphabetical characters (typically
     * spaces and/or hyphens). Any word that is the name of a color in this SimplePalette will be looked up in
     * {@link #NAMED} and tracked; if there is more than one of these color name words, the colors will be mixed using
     * {@link #mix(int[], int, int)}, or if there is just one color name word, then the corresponding color
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
     *
     * @param description a color description, as a lower-case String matching the above format
     * @return a packed Oklab int color as described
     */
    public static int parseDescription(final String description) {
        float intensity = 0f, saturation = 0f;
        final String[] terms = description.split("[^a-zA-Z]+");
        mixing.clear();
        for (String term : terms) {
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
                                mixing.add(TRANSPARENT);
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
                                mixing.add(TRANSPARENT);
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
                                mixing.add(TRANSPARENT);
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
        int result = mix(mixing.items, 0, mixing.size());

        if (intensity > 0) result = lighten(result, intensity);
        else if (intensity < 0) result = darken(result, -intensity);

        if (saturation > 0) result = enrich(result, saturation);
        else if (saturation < 0) result = limitToGamut(dullen(result, -saturation));
        else result = limitToGamut(result);

        return result;
    }

    private static final ObjectList<String> namesByHue = new ObjectList<>(NAMES_BY_HUE);
    private static final IntList colorsByHue = new IntList(COLORS_BY_HUE);
    static {
        int trn = namesByHue.indexOf("transparent");
        namesByHue.removeAt(trn);
        colorsByHue.removeAt(trn);
    }
    /**
     * Given a color as a packed Oklab int, this finds the closest description it can to match the given color while
     * using at most {@code mixCount} colors to mix in. You should only use small numbers for mixCount, like 1 to 3;
     * this can take quite a while to run otherwise. This returns a String description that can be passed to
     * {@link #parseDescription(String)}. It is likely that this will use very contrasting colors if mixCount is 2 or
     * greater and the color to match is desaturated or brownish.
     * @param oklab a packed Oklab int color to attempt to match
     * @param mixCount how many color names this will use in the returned description
     * @return a description that can be fed to {@link #parseDescription(String)} to get a similar color
     */
    public static String bestMatch(final int oklab, int mixCount) {
        mixCount = Math.max(1, mixCount);
        long bestDistance = Long.MAX_VALUE;
        final int paletteSize = namesByHue.size(), colorTries = (int)Math.pow(paletteSize, mixCount), totalTries = colorTries * 81;
        final int targetL = oklab >>> 24, targetA = oklab >>> 16 & 255, targetB = oklab >>> 8 & 255;
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

            int result = mix(mixing.items, 0, mixCount);
            if(idxI > 0) result = lighten(result, 0.125f * idxI);
            else if(idxI < 0) result = darken(result, -0.15f * idxI);

            if(idxS > 0) result = (enrich(result, 0.2f * idxS));
            else if(idxS < 0) result = limitToGamut(dullen(result, -0.2f * idxS));
            else result = limitToGamut(result);

            long dL = (result >>> 24) - targetL, dA = (result >>> 16 & 255) - targetA, dB = (result >>> 8 & 255) - targetB;
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
}