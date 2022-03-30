package com.github.tommyettinger.colorful.pure.oklab;

import com.github.tommyettinger.colorful.pure.MathTools;
import com.github.tommyettinger.ds.IntList;
import com.github.tommyettinger.ds.ObjectIntOrderedMap;
import com.github.tommyettinger.ds.ObjectList;
import com.github.tommyettinger.ds.support.BitConversion;

import static com.github.tommyettinger.colorful.pure.MathTools.floor;
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
     * It has the encoded Oklab value 0xff7f7f00 .
     * <pre>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #000000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BLACK = 0xff7f7f00;
    static { NAMED.put("black", 0xff7f7f00); LIST.add(0xff7f7f00); }

    /**
     * This color constant "gray" has RGBA8888 code {@code 808080FF}, L 0.5137255, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, saturation 0.0016803193, and chroma 0.0055242716.
     * It has the encoded Oklab value 0xff7f7f83 .
     * <pre>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #808080; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #808080; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #808080; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int GRAY = 0xff7f7f83;
    static { NAMED.put("gray", 0xff7f7f83); LIST.add(0xff7f7f83); }

    /**
     * This color constant "silver" has RGBA8888 code {@code B6B6B6FF}, L 0.7176471, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, saturation 0.0013493, and chroma 0.0055242716.
     * It has the encoded Oklab value 0xff7f7fb7 .
     * <pre>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B6B6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #B6B6B6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B6B6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int SILVER = 0xff7f7fb7;
    static { NAMED.put("silver", 0xff7f7fb7); LIST.add(0xff7f7fb7); }

    /**
     * This color constant "white" has RGBA8888 code {@code FFFFFFFF}, L 1.0, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, saturation 0.0, and chroma 0.0055242716.
     * It has the encoded Oklab value 0xff7f7fff .
     * <pre>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #FFFFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int WHITE = 0xff7f7fff;
    static { NAMED.put("white", 0xff7f7fff); LIST.add(0xff7f7fff); }

    /**
     * This color constant "red" has RGBA8888 code {@code FF0000FF}, L 0.50980395, A 0.6117647, B 0.56078434, alpha 1.0, hue 0.07928106, saturation 0.8972241, and chroma 0.25345513.
     * It has the encoded Oklab value 0xff8f9c82 .
     * <pre>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #FF0000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int RED = 0xff8f9c82;
    static { NAMED.put("red", 0xff8f9c82); LIST.add(0xff8f9c82); }

    /**
     * This color constant "orange" has RGBA8888 code {@code FF7F00FF}, L 0.6509804, A 0.54901963, B 0.57254905, alpha 1.0, hue 0.15541562, saturation 0.8137945, and chroma 0.17443058.
     * It has the encoded Oklab value 0xff928ca6 .
     * <pre>
     * <font style='background-color: #FF7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF7F00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF7F00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF7F00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF7F00'>&nbsp;@&nbsp;</font><font style='background-color: #FF7F00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF7F00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int ORANGE = 0xff928ca6;
    static { NAMED.put("orange", 0xff928ca6); LIST.add(0xff928ca6); }

    /**
     * This color constant "yellow" has RGBA8888 code {@code FFFF00FF}, L 0.92941177, A 0.4627451, B 0.59607846, alpha 1.0, hue 0.30886024, saturation 0.92988807, and chroma 0.20529193.
     * It has the encoded Oklab value 0xff9876ed .
     * <pre>
     * <font style='background-color: #FFFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFF00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFF00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFF00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFF00'>&nbsp;@&nbsp;</font><font style='background-color: #FFFF00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFF00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int YELLOW = 0xff9876ed;
    static { NAMED.put("yellow", 0xff9876ed); LIST.add(0xff9876ed); }

    /**
     * This color constant "green" has RGBA8888 code {@code 00FF00FF}, L 0.8, A 0.38039216, B 0.5882353, alpha 1.0, hue 0.39883053, saturation 0.9818446, and chroma 0.29610303.
     * It has the encoded Oklab value 0xff9661cc .
     * <pre>
     * <font style='background-color: #00FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FF00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FF00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FF00'>&nbsp;@&nbsp;</font><font style='background-color: #00FF00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int GREEN = 0xff9661cc;
    static { NAMED.put("green", 0xff9661cc); LIST.add(0xff9661cc); }

    /**
     * This color constant "blue" has RGBA8888 code {@code 0000FFFF}, L 0.30588236, A 0.48235294, B 0.34117648, alpha 1.0, hue 0.7323789, saturation 0.97586775, and chroma 0.31835338.
     * It has the encoded Oklab value 0xff577b4e .
     * <pre>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0000FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #0000FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0000FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BLUE = 0xff577b4e;
    static { NAMED.put("blue", 0xff577b4e); LIST.add(0xff577b4e); }

    /**
     * This color constant "indigo" has RGBA8888 code {@code 520FE0FF}, L 0.32156864, A 0.53333336, B 0.36862746, alpha 1.0, hue 0.7895446, saturation 0.9090131, and chroma 0.27001202.
     * It has the encoded Oklab value 0xff5e8852 .
     * <pre>
     * <font style='background-color: #520FE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #520FE0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #520FE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #520FE0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #520FE0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #520FE0'>&nbsp;@&nbsp;</font><font style='background-color: #520FE0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #520FE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #520FE0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int INDIGO = 0xff5e8852;
    static { NAMED.put("indigo", 0xff5e8852); LIST.add(0xff5e8852); }

    /**
     * This color constant "violet" has RGBA8888 code {@code 9040EFFF}, L 0.4627451, A 0.56078434, B 0.39607844, alpha 1.0, hue 0.8342395, saturation 0.6792487, and chroma 0.23984502.
     * It has the encoded Oklab value 0xff658f76 .
     * <pre>
     * <font style='background-color: #9040EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9040EF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9040EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9040EF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9040EF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9040EF'>&nbsp;@&nbsp;</font><font style='background-color: #9040EF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9040EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9040EF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int VIOLET = 0xff658f76;
    static { NAMED.put("violet", 0xff658f76); LIST.add(0xff658f76); }

    /**
     * This color constant "purple" has RGBA8888 code {@code C000FFFF}, L 0.49803922, A 0.60784316, B 0.3882353, alpha 1.0, hue 0.8721605, saturation 0.96817255, and chroma 0.30940855.
     * It has the encoded Oklab value 0xff639b7f .
     * <pre>
     * <font style='background-color: #C000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C000FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C000FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C000FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C000FF'>&nbsp;@&nbsp;</font><font style='background-color: #C000FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C000FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int PURPLE = 0xff639b7f;
    static { NAMED.put("purple", 0xff639b7f); LIST.add(0xff639b7f); }

    /**
     * This color constant "brown" has RGBA8888 code {@code 8F573BFF}, L 0.4117647, A 0.5254902, B 0.5294118, alpha 1.0, hue 0.13634071, saturation 0.24020728, and chroma 0.07753685.
     * It has the encoded Oklab value 0xff878669 .
     * <pre>
     * <font style='background-color: #8F573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F573B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F573B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F573B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F573B'>&nbsp;@&nbsp;</font><font style='background-color: #8F573B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F573B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BROWN = 0xff878669;
    static { NAMED.put("brown", 0xff878669); LIST.add(0xff878669); }

    /**
     * This color constant "pink" has RGBA8888 code {@code FFA0E0FF}, L 0.7607843, A 0.5568628, B 0.4745098, alpha 1.0, hue 0.9329364, saturation 0.68139654, and chroma 0.124142565.
     * It has the encoded Oklab value 0xff798ec2 .
     * <pre>
     * <font style='background-color: #FFA0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA0E0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFA0E0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFA0E0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFA0E0'>&nbsp;@&nbsp;</font><font style='background-color: #FFA0E0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA0E0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int PINK = 0xff798ec2;
    static { NAMED.put("pink", 0xff798ec2); LIST.add(0xff798ec2); }

    /**
     * This color constant "magenta" has RGBA8888 code {@code F500F5FF}, L 0.5764706, A 0.6313726, B 0.41568628, alpha 1.0, hue 0.9091779, saturation 0.9090322, and chroma 0.31098264.
     * It has the encoded Oklab value 0xff6aa193 .
     * <pre>
     * <font style='background-color: #F500F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F500F5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F500F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F500F5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F500F5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F500F5'>&nbsp;@&nbsp;</font><font style='background-color: #F500F5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F500F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F500F5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int MAGENTA = 0xff6aa193;
    static { NAMED.put("magenta", 0xff6aa193); LIST.add(0xff6aa193); }

    /**
     * This color constant "brick" has RGBA8888 code {@code D5524AFF}, L 0.5137255, A 0.5686275, B 0.53333336, alpha 1.0, hue 0.07195936, saturation 0.3226597, and chroma 0.15199278.
     * It has the encoded Oklab value 0xff889183 .
     * <pre>
     * <font style='background-color: #D5524A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5524A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5524A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D5524A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D5524A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D5524A'>&nbsp;@&nbsp;</font><font style='background-color: #D5524A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5524A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5524A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRICK = 0xff889183;
    static { NAMED.put("brick", 0xff889183); LIST.add(0xff889183); }

    /**
     * This color constant "ember" has RGBA8888 code {@code F55A32FF}, L 0.57254905, A 0.57254905, B 0.5568628, alpha 1.0, hue 0.10581406, saturation 0.668431, and chroma 0.1836353.
     * It has the encoded Oklab value 0xff8e9292 .
     * <pre>
     * <font style='background-color: #F55A32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F55A32; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F55A32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F55A32'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F55A32'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F55A32'>&nbsp;@&nbsp;</font><font style='background-color: #F55A32; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F55A32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F55A32; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int EMBER = 0xff8e9292;
    static { NAMED.put("ember", 0xff8e9292); LIST.add(0xff8e9292); }

    /**
     * This color constant "salmon" has RGBA8888 code {@code FF6262FF}, L 0.6117647, A 0.5803922, B 0.53333336, alpha 1.0, hue 0.06254671, saturation 0.8039996, and chroma 0.17337766.
     * It has the encoded Oklab value 0xff88949c .
     * <pre>
     * <font style='background-color: #FF6262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6262; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF6262'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF6262'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF6262'>&nbsp;@&nbsp;</font><font style='background-color: #FF6262; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6262; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int SALMON = 0xff88949c;
    static { NAMED.put("salmon", 0xff88949c); LIST.add(0xff88949c); }

    /**
     * This color constant "chocolate" has RGBA8888 code {@code 683818FF}, L 0.27450982, A 0.5254902, B 0.53333336, alpha 1.0, hue 0.14608382, saturation 0.49234077, and chroma 0.0835974.
     * It has the encoded Oklab value 0xff888646 .
     * <pre>
     * <font style='background-color: #683818;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #683818; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #683818;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #683818'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #683818'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #683818'>&nbsp;@&nbsp;</font><font style='background-color: #683818; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #683818;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #683818; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int CHOCOLATE = 0xff888646;
    static { NAMED.put("chocolate", 0xff888646); LIST.add(0xff888646); }

    /**
     * This color constant "tan" has RGBA8888 code {@code D2B48CFF}, L 0.7294118, A 0.5058824, B 0.5254902, alpha 1.0, hue 0.21390288, saturation 0.08232166, and chroma 0.052115876.
     * It has the encoded Oklab value 0xff8681ba .
     * <pre>
     * <font style='background-color: #D2B48C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2B48C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2B48C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D2B48C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D2B48C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D2B48C'>&nbsp;@&nbsp;</font><font style='background-color: #D2B48C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2B48C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2B48C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TAN = 0xff8681ba;
    static { NAMED.put("tan", 0xff8681ba); LIST.add(0xff8681ba); }

    /**
     * This color constant "bronze" has RGBA8888 code {@code CE8E31FF}, L 0.61960787, A 0.5137255, B 0.56078434, alpha 1.0, hue 0.21465261, saturation 0.5864422, and chroma 0.124142565.
     * It has the encoded Oklab value 0xff8f839e .
     * <pre>
     * <font style='background-color: #CE8E31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CE8E31; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CE8E31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CE8E31'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CE8E31'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CE8E31'>&nbsp;@&nbsp;</font><font style='background-color: #CE8E31; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CE8E31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CE8E31; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRONZE = 0xff8f839e;
    static { NAMED.put("bronze", 0xff8f839e); LIST.add(0xff8f839e); }

    /**
     * This color constant "cinnamon" has RGBA8888 code {@code D2691DFF}, L 0.54509807, A 0.54509807, B 0.56078434, alpha 1.0, hue 0.14839543, saturation 0.7197198, and chroma 0.15078327.
     * It has the encoded Oklab value 0xff8f8b8b .
     * <pre>
     * <font style='background-color: #D2691D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2691D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2691D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D2691D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D2691D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D2691D'>&nbsp;@&nbsp;</font><font style='background-color: #D2691D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2691D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2691D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int CINNAMON = 0xff8f8b8b;
    static { NAMED.put("cinnamon", 0xff8f8b8b); LIST.add(0xff8f8b8b); }

    /**
     * This color constant "apricot" has RGBA8888 code {@code FFA828FF}, L 0.7372549, A 0.52156866, B 0.5764706, alpha 1.0, hue 0.20625468, saturation 0.75939417, and chroma 0.1582875.
     * It has the encoded Oklab value 0xff9385bc .
     * <pre>
     * <font style='background-color: #FFA828;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA828; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA828;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFA828'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFA828'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFA828'>&nbsp;@&nbsp;</font><font style='background-color: #FFA828; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA828;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA828; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int APRICOT = 0xff9385bc;
    static { NAMED.put("apricot", 0xff9385bc); LIST.add(0xff9385bc); }

    /**
     * This color constant "peach" has RGBA8888 code {@code FFBF81FF}, L 0.79607844, A 0.5176471, B 0.54509807, alpha 1.0, hue 0.19064914, saturation 0.6557377, and chroma 0.096477255.
     * It has the encoded Oklab value 0xff8b84cb .
     * <pre>
     * <font style='background-color: #FFBF81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFBF81; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFBF81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFBF81'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFBF81'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFBF81'>&nbsp;@&nbsp;</font><font style='background-color: #FFBF81; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFBF81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFBF81; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int PEACH = 0xff8b84cb;
    static { NAMED.put("peach", 0xff8b84cb); LIST.add(0xff8b84cb); }

    /**
     * This color constant "pear" has RGBA8888 code {@code D3E330FF}, L 0.8235294, A 0.45882353, B 0.58431375, alpha 1.0, hue 0.32230157, saturation 0.7434462, and chroma 0.18692946.
     * It has the encoded Oklab value 0xff9575d2 .
     * <pre>
     * <font style='background-color: #D3E330;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3E330; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3E330;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D3E330'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D3E330'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D3E330'>&nbsp;@&nbsp;</font><font style='background-color: #D3E330; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3E330;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3E330; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int PEAR = 0xff9575d2;
    static { NAMED.put("pear", 0xff9575d2); LIST.add(0xff9575d2); }

    /**
     * This color constant "saffron" has RGBA8888 code {@code FFD510FF}, L 0.83137256, A 0.4862745, B 0.5882353, alpha 1.0, hue 0.27457327, saturation 0.8132536, and chroma 0.17789528.
     * It has the encoded Oklab value 0xff967cd4 .
     * <pre>
     * <font style='background-color: #FFD510;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD510; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD510;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFD510'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFD510'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFD510'>&nbsp;@&nbsp;</font><font style='background-color: #FFD510; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD510;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD510; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int SAFFRON = 0xff967cd4;
    static { NAMED.put("saffron", 0xff967cd4); LIST.add(0xff967cd4); }

    /**
     * This color constant "butter" has RGBA8888 code {@code FFF288FF}, L 0.9098039, A 0.4862745, B 0.5568628, alpha 1.0, hue 0.28769454, saturation 0.49273357, and chroma 0.11653464.
     * It has the encoded Oklab value 0xff8e7ce8 .
     * <pre>
     * <font style='background-color: #FFF288;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFF288; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFF288;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFF288'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFF288'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFF288'>&nbsp;@&nbsp;</font><font style='background-color: #FFF288; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFF288;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFF288; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BUTTER = 0xff8e7ce8;
    static { NAMED.put("butter", 0xff8e7ce8); LIST.add(0xff8e7ce8); }

    /**
     * This color constant "chartreuse" has RGBA8888 code {@code C8FF41FF}, L 0.8862745, A 0.4392157, B 0.58431375, alpha 1.0, hue 0.34942675, saturation 0.9817451, and chroma 0.2070681.
     * It has the encoded Oklab value 0xff9570e2 .
     * <pre>
     * <font style='background-color: #C8FF41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8FF41; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8FF41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C8FF41'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C8FF41'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C8FF41'>&nbsp;@&nbsp;</font><font style='background-color: #C8FF41; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8FF41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8FF41; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int CHARTREUSE = 0xff9570e2;
    static { NAMED.put("chartreuse", 0xff9570e2); LIST.add(0xff9570e2); }

    /**
     * This color constant "cactus" has RGBA8888 code {@code 30A000FF}, L 0.5254902, A 0.41960785, B 0.5647059, alpha 1.0, hue 0.39212817, saturation 0.809409, and chroma 0.20558903.
     * It has the encoded Oklab value 0xff906b86 .
     * <pre>
     * <font style='background-color: #30A000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #30A000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #30A000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #30A000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #30A000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #30A000'>&nbsp;@&nbsp;</font><font style='background-color: #30A000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #30A000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #30A000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int CACTUS = 0xff906b86;
    static { NAMED.put("cactus", 0xff906b86); LIST.add(0xff906b86); }

    /**
     * This color constant "lime" has RGBA8888 code {@code 93D300FF}, L 0.7294118, A 0.43529412, B 0.5803922, alpha 1.0, hue 0.3578718, saturation 0.78243065, and chroma 0.20558903.
     * It has the encoded Oklab value 0xff946fba .
     * <pre>
     * <font style='background-color: #93D300;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #93D300; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #93D300;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #93D300'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #93D300'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #93D300'>&nbsp;@&nbsp;</font><font style='background-color: #93D300; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #93D300;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #93D300; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int LIME = 0xff946fba;
    static { NAMED.put("lime", 0xff946fba); LIST.add(0xff946fba); }

    /**
     * This color constant "olive" has RGBA8888 code {@code 818000FF}, L 0.49019608, A 0.4745098, B 0.56078434, alpha 1.0, hue 0.31318712, saturation 0.76235455, and chroma 0.13131043.
     * It has the encoded Oklab value 0xff8f797d .
     * <pre>
     * <font style='background-color: #818000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #818000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #818000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #818000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #818000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #818000'>&nbsp;@&nbsp;</font><font style='background-color: #818000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #818000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #818000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int OLIVE = 0xff8f797d;
    static { NAMED.put("olive", 0xff8f797d); LIST.add(0xff8f797d); }

    /**
     * This color constant "fern" has RGBA8888 code {@code 4E7942FF}, L 0.43137255, A 0.4627451, B 0.5294118, alpha 1.0, hue 0.3936267, saturation 0.22094448, and chroma 0.0945603.
     * It has the encoded Oklab value 0xff87766e .
     * <pre>
     * <font style='background-color: #4E7942;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E7942; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E7942;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4E7942'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4E7942'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4E7942'>&nbsp;@&nbsp;</font><font style='background-color: #4E7942; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E7942;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E7942; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int FERN = 0xff87766e;
    static { NAMED.put("fern", 0xff87766e); LIST.add(0xff87766e); }

    /**
     * This color constant "moss" has RGBA8888 code {@code 204608FF}, L 0.22745098, A 0.45882353, B 0.5372549, alpha 1.0, hue 0.38294512, saturation 0.6363817, and chroma 0.11062346.
     * It has the encoded Oklab value 0xff89753a .
     * <pre>
     * <font style='background-color: #204608;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #204608; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #204608;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #204608'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #204608'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #204608'>&nbsp;@&nbsp;</font><font style='background-color: #204608; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #204608;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #204608; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int MOSS = 0xff89753a;
    static { NAMED.put("moss", 0xff89753a); LIST.add(0xff89753a); }

    /**
     * This color constant "celery" has RGBA8888 code {@code 7DFF73FF}, L 0.84313726, A 0.41960785, B 0.56078434, alpha 1.0, hue 0.39695174, saturation 0.8276294, and chroma 0.20078278.
     * It has the encoded Oklab value 0xff8f6bd7 .
     * <pre>
     * <font style='background-color: #7DFF73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7DFF73; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7DFF73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7DFF73'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7DFF73'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7DFF73'>&nbsp;@&nbsp;</font><font style='background-color: #7DFF73; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7DFF73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7DFF73; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int CELERY = 0xff8f6bd7;
    static { NAMED.put("celery", 0xff8f6bd7); LIST.add(0xff8f6bd7); }

    /**
     * This color constant "sage" has RGBA8888 code {@code ABE3C5FF}, L 0.81960785, A 0.46666667, B 0.50980395, alpha 1.0, hue 0.45448294, saturation 0.118390046, and chroma 0.069218926.
     * It has the encoded Oklab value 0xff8277d1 .
     * <pre>
     * <font style='background-color: #ABE3C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABE3C5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABE3C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ABE3C5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ABE3C5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ABE3C5'>&nbsp;@&nbsp;</font><font style='background-color: #ABE3C5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABE3C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABE3C5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int SAGE = 0xff8277d1;
    static { NAMED.put("sage", 0xff8277d1); LIST.add(0xff8277d1); }

    /**
     * This color constant "jade" has RGBA8888 code {@code 3FBF3FFF}, L 0.6313726, A 0.41568628, B 0.5568628, alpha 1.0, hue 0.4055531, saturation 0.71121687, and chroma 0.20259848.
     * It has the encoded Oklab value 0xff8e6aa1 .
     * <pre>
     * <font style='background-color: #3FBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3FBF3F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3FBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3FBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3FBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3FBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #3FBF3F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3FBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3FBF3F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int JADE = 0xff8e6aa1;
    static { NAMED.put("jade", 0xff8e6aa1); LIST.add(0xff8e6aa1); }

    /**
     * This color constant "cyan" has RGBA8888 code {@code 00FFFFFF}, L 0.8509804, A 0.42352942, B 0.47843137, alpha 1.0, hue 0.54374534, saturation 0.95340395, and chroma 0.1582875.
     * It has the encoded Oklab value 0xff7a6cd9 .
     * <pre>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #00FFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int CYAN = 0xff7a6cd9;
    static { NAMED.put("cyan", 0xff7a6cd9); LIST.add(0xff7a6cd9); }

    /**
     * This color constant "mint" has RGBA8888 code {@code 7FFFD4FF}, L 0.8666667, A 0.4392157, B 0.50980395, alpha 1.0, hue 0.47453672, saturation 0.8283974, and chroma 0.12265874.
     * It has the encoded Oklab value 0xff8270dd .
     * <pre>
     * <font style='background-color: #7FFFD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7FFFD4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7FFFD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7FFFD4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7FFFD4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7FFFD4'>&nbsp;@&nbsp;</font><font style='background-color: #7FFFD4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7FFFD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7FFFD4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int MINT = 0xff8270dd;
    static { NAMED.put("mint", 0xff8270dd); LIST.add(0xff8270dd); }

    /**
     * This color constant "teal" has RGBA8888 code {@code 007F7FFF}, L 0.4392157, A 0.4509804, B 0.4862745, alpha 1.0, hue 0.543443, saturation 0.89123964, and chroma 0.10141215.
     * It has the encoded Oklab value 0xff7c7370 .
     * <pre>
     * <font style='background-color: #007F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007F7F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #007F7F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #007F7F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #007F7F'>&nbsp;@&nbsp;</font><font style='background-color: #007F7F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007F7F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TEAL = 0xff7c7370;
    static { NAMED.put("teal", 0xff7c7370); LIST.add(0xff7c7370); }

    /**
     * This color constant "turquoise" has RGBA8888 code {@code 2ED6C9FF}, L 0.7254902, A 0.43137255, B 0.49019608, alpha 1.0, hue 0.52259654, saturation 0.8433125, and chroma 0.1381068.
     * It has the encoded Oklab value 0xff7d6eb9 .
     * <pre>
     * <font style='background-color: #2ED6C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2ED6C9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2ED6C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2ED6C9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2ED6C9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2ED6C9'>&nbsp;@&nbsp;</font><font style='background-color: #2ED6C9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2ED6C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2ED6C9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TURQUOISE = 0xff7d6eb9;
    static { NAMED.put("turquoise", 0xff7d6eb9); LIST.add(0xff7d6eb9); }

    /**
     * This color constant "sky" has RGBA8888 code {@code 10C0E0FF}, L 0.67058825, A 0.44313726, B 0.4627451, alpha 1.0, hue 0.59232193, saturation 0.8547556, and chroma 0.13542919.
     * It has the encoded Oklab value 0xff7671ab .
     * <pre>
     * <font style='background-color: #10C0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #10C0E0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #10C0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #10C0E0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #10C0E0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #10C0E0'>&nbsp;@&nbsp;</font><font style='background-color: #10C0E0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #10C0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #10C0E0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int SKY = 0xff7671ab;
    static { NAMED.put("sky", 0xff7671ab); LIST.add(0xff7671ab); }

    /**
     * This color constant "cobalt" has RGBA8888 code {@code 0046ABFF}, L 0.30588236, A 0.48235294, B 0.4117647, alpha 1.0, hue 0.71857655, saturation 0.73578477, and chroma 0.1792624.
     * It has the encoded Oklab value 0xff697b4e .
     * <pre>
     * <font style='background-color: #0046AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0046AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0046AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0046AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0046AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0046AB'>&nbsp;@&nbsp;</font><font style='background-color: #0046AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0046AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0046AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int COBALT = 0xff697b4e;
    static { NAMED.put("cobalt", 0xff697b4e); LIST.add(0xff697b4e); }

    /**
     * This color constant "denim" has RGBA8888 code {@code 3088B8FF}, L 0.5058824, A 0.46666667, B 0.45490196, alpha 1.0, hue 0.6486837, saturation 0.5816889, and chroma 0.11172148.
     * It has the encoded Oklab value 0xff747781 .
     * <pre>
     * <font style='background-color: #3088B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3088B8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3088B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3088B8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3088B8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3088B8'>&nbsp;@&nbsp;</font><font style='background-color: #3088B8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3088B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3088B8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DENIM = 0xff747781;
    static { NAMED.put("denim", 0xff747781); LIST.add(0xff747781); }

    /**
     * This color constant "navy" has RGBA8888 code {@code 000080FF}, L 0.14117648, A 0.4862745, B 0.4, alpha 1.0, hue 0.7282781, saturation 0.96145123, and chroma 0.20108652.
     * It has the encoded Oklab value 0xff667c24 .
     * <pre>
     * <font style='background-color: #000080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000080; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000080'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000080'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000080'>&nbsp;@&nbsp;</font><font style='background-color: #000080; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000080; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int NAVY = 0xff667c24;
    static { NAMED.put("navy", 0xff667c24); LIST.add(0xff667c24); }

    /**
     * This color constant "lavender" has RGBA8888 code {@code B991FFFF}, L 0.67058825, A 0.53333336, B 0.43529412, alpha 1.0, hue 0.82570946, saturation 0.84011585, and chroma 0.14500555.
     * It has the encoded Oklab value 0xff6f88ab .
     * <pre>
     * <font style='background-color: #B991FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B991FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B991FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B991FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B991FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B991FF'>&nbsp;@&nbsp;</font><font style='background-color: #B991FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B991FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B991FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int LAVENDER = 0xff6f88ab;
    static { NAMED.put("lavender", 0xff6f88ab); LIST.add(0xff6f88ab); }

    /**
     * This color constant "plum" has RGBA8888 code {@code BE0DC6FF}, L 0.45490196, A 0.6117647, B 0.42352942, alpha 1.0, hue 0.90448654, saturation 0.9074911, and chroma 0.26978588.
     * It has the encoded Oklab value 0xff6c9c74 .
     * <pre>
     * <font style='background-color: #BE0DC6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE0DC6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE0DC6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BE0DC6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BE0DC6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BE0DC6'>&nbsp;@&nbsp;</font><font style='background-color: #BE0DC6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE0DC6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE0DC6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int PLUM = 0xff6c9c74;
    static { NAMED.put("plum", 0xff6c9c74); LIST.add(0xff6c9c74); }

    /**
     * This color constant "mauve" has RGBA8888 code {@code AB73ABFF}, L 0.54901963, A 0.5411765, B 0.47058824, alpha 1.0, hue 0.90127134, saturation 0.09785124, and chroma 0.1008085.
     * It has the encoded Oklab value 0xff788a8c .
     * <pre>
     * <font style='background-color: #AB73AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB73AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB73AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB73AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB73AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB73AB'>&nbsp;@&nbsp;</font><font style='background-color: #AB73AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB73AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB73AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int MAUVE = 0xff788a8c;
    static { NAMED.put("mauve", 0xff788a8c); LIST.add(0xff788a8c); }

    /**
     * This color constant "rose" has RGBA8888 code {@code E61E78FF}, L 0.49411765, A 0.6156863, B 0.49411765, alpha 1.0, hue 0.99192464, saturation 0.7659808, and chroma 0.23076649.
     * It has the encoded Oklab value 0xff7e9d7e .
     * <pre>
     * <font style='background-color: #E61E78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E61E78; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E61E78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E61E78'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E61E78'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E61E78'>&nbsp;@&nbsp;</font><font style='background-color: #E61E78; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E61E78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E61E78; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int ROSE = 0xff7e9d7e;
    static { NAMED.put("rose", 0xff7e9d7e); LIST.add(0xff7e9d7e); }

    /**
     * This color constant "raspberry" has RGBA8888 code {@code 911437FF}, L 0.3019608, A 0.5803922, B 0.5137255, alpha 1.0, hue 0.02692472, saturation 0.73546606, and chroma 0.16247371.
     * It has the encoded Oklab value 0xff83944d .
     * <pre>
     * <font style='background-color: #911437;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #911437; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #911437;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #911437'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #911437'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #911437'>&nbsp;@&nbsp;</font><font style='background-color: #911437; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #911437;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #911437; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int RASPBERRY = 0xff83944d;
    static { NAMED.put("raspberry", 0xff83944d); LIST.add(0xff83944d); }

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
     * Gets an Oklab packed int color that is within the gamut of viable colors by using
     * {@link #limitToGamut(int, int, int, int)}. This method just calls limitToGamut() with the parameters adjusted
     * from the 0 to 1 float range here, to the 0 to 255 int range in limitToGamut.
     * @param L lightness component; will be clamped between 0 and 1 if it isn't already
     * @param A green-to-red chromatic component; will be clamped between 0 and 1 if it isn't already
     * @param B blue-to-yellow chromatic component; will be clamped between 0 and 1 if it isn't already
     * @param alpha alpha component; will be clamped between 0 and 1 if it isn't already
     * @return the first color this finds that is in-gamut, as if it was moving toward a grayscale color with the same L
     * @see #inGamut(int, int, int) You can use inGamut() if you just want to check whether a color is in-gamut.
     */
    public static int oklab(float L, float A, float B, float alpha) {
        return limitToGamut((int)(L * 255.999f), (int)(A * 255.999f), (int)(B * 255.999f), (int)(alpha * 255.999f));
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
     * Gets the red channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
     * @param packed a color as a packed int that can be obtained by {@link #oklab(float, float, float, float)}
     * @return an int from 0 to 255, inclusive, representing the red channel value of the given encoded color
     */
    public static int red(final int packed)
    {
        final float L = reverseLight((packed & 0xff) / 255f);
        final float A = ((packed >>> 8 & 0xff) - 127.5f) / 127.5f;
        final float B = ((packed >>> 16 & 0xff) - 127.5f) / 127.5f;
        final float l = cube(L + 0.3963377774f * A + 0.2158037573f * B);
        final float m = cube(L - 0.1055613458f * A - 0.0638541728f * B);
        final float s = cube(L - 0.0894841775f * A - 1.2914855480f * B);
        return (int)(reverseGamma(Math.min(Math.max(+4.0767245293f * l - 3.3072168827f * m + 0.2307590544f * s, 0f), 1f)) * 255.999f);
    }

    /**
     * Gets the green channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
     * @param packed a color as a packed int that can be obtained by {@link #oklab(float, float, float, float)}
     * @return an int from 0 to 255, inclusive, representing the green channel value of the given encoded color
     */
    public static int green(final int packed)
    {
        final float L = reverseLight((packed & 0xff) / 255f);
        final float A = ((packed >>> 8 & 0xff) - 127.5f) / 127.5f;
        final float B = ((packed >>> 16 & 0xff) - 127.5f) / 127.5f;
        final float l = cube(L + 0.3963377774f * A + 0.2158037573f * B);
        final float m = cube(L - 0.1055613458f * A - 0.0638541728f * B);
        final float s = cube(L - 0.0894841775f * A - 1.2914855480f * B);
        return (int)(reverseGamma(Math.min(Math.max(-1.2681437731f * l + 2.6093323231f * m - 0.3411344290f * s, 0f), 1f)) * 255.999f);
    }

    /**
     * Gets the blue channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
     * @param packed a color as a packed int that can be obtained by {@link #oklab(float, float, float, float)}
     * @return an int from 0 to 255, inclusive, representing the blue channel value of the given encoded color
     */
    public static int blue(final int packed)
    {
        final float L = reverseLight((packed & 0xff) / 255f);
        final float A = ((packed >>> 8 & 0xff) - 127.5f) / 127.5f;
        final float B = ((packed >>> 16 & 0xff) - 127.5f) / 127.5f;
        final float l = cube(L + 0.3963377774f * A + 0.2158037573f * B);
        final float m = cube(L - 0.1055613458f * A - 0.0638541728f * B);
        final float s = cube(L - 0.0894841775f * A - 1.2914855480f * B);
        return (int)(reverseGamma(Math.min(Math.max(-0.0041119885f * l - 0.7034763098f * m + 1.7068625689f * s, 0f), 1f)) * 255.999f);
    }

    /**
     * Gets the alpha channel value of the given encoded color, as an even int ranging from 0 to 254, inclusive. Because
     * of how alpha is stored in libGDX, no odd-number values are possible for alpha.
     * @param encoded a color as a packed int that can be obtained by {@link #oklab(float, float, float, float)}
     * @return an even int from 0 to 254, inclusive, representing the alpha channel value of the given encoded color
     */
    public static int alpha(final int encoded)
    {
        return encoded >>> 24;
    }


    /**
     * The "L" channel of the given packed int in Oklab format, which is its lightness; ranges from 0 to
     * 255 . You can edit the L of a color with {@link #lighten(int, float)} and {@link #darken(int, float)}.
     *
     * @param encoded a color encoded as a packed int, as by {@link #oklab(float, float, float, float)}
     * @return the L value as an int from 0 to 255
     */
    public static int channelL(final int encoded)
    {
        return (encoded) & 0xff;
    }

    /**
     * The "L" channel of the given packed int in Oklab format, which is its lightness; this gets the L channel as a
     * float that ranges from 0.0f to 1.0f . You can edit the L of a color with {@link #lighten(int, float)} and
     * {@link #darken(int, float)}.
     *
     * @param encoded a color encoded as a packed int, as by {@link #oklab(float, float, float, float)}
     * @return the L value as an int from 0.0f to 1.0f
     */
    public static float lightness(final int encoded)
    {
        return (encoded & 0xff) / 255f;
    }

    /**
     * The "A" channel of the given packed int in Oklab format, which when combined with the B channel describes the
     * hue and saturation of a color; ranges from 0f to 1f . If A is 0f, the color will be cooler, more green or
     * blue; if A is 1f, the color will be warmer, from magenta to orange. You can edit the A of a color with
     * {@link #raiseA(int, float)} and {@link #lowerA(int, float)}.
     * @param encoded a color encoded as a packed int, as by {@link #oklab(float, float, float, float)}
     * @return the A value as an int from 0 to 255
     */
    public static int channelA(final int encoded)
    {
        return (((encoded) >>> 8 & 0xff));
    }

    /**
     * The "B" channel of the given packed int in Oklab format, which when combined with the A channel describes the
     * hue and saturation of a color; ranges from 0f to 1f . If B is 0f, the color will be more "artificial", more
     * blue or purple; if B is 1f, the color will be more "natural", from green to yellow to orange. You can edit
     * the B of a color with {@link #raiseB(int, float)} and {@link #lowerB(int, float)}.
     * @param encoded a color encoded as a packed int, as by {@link #oklab(float, float, float, float)}
     * @return the B value as an int from 0 to 255
     */
    public static int channelB(final int encoded)
    {
        return encoded >>> 16 & 0xff;
    }

    /**
     * Gets the "chroma" or "colorfulness" of a given Oklab color. Chroma is similar to saturation in that grayscale
     * values have 0 saturation and 0 chroma, while brighter colors have high saturation and chroma. The difference is
     * that colors that are perceptually more-colorful have higher chroma than colors that are perceptually
     * less-colorful, regardless of hue, whereas saturation changes its meaning depending on the hue and lightness. That
     * is, the most saturated color for a given hue and lightness always has a saturation of 1, but if that color
     * isn't perceptually very colorful (as is the case for very dark and very light colors), it will have a chroma that
     * is much lower than the maximum. The result of this method can't be negative, grayscale values have very close to
     * 0 chroma, and the most colorful values (close to cyan) should have 0.334f chroma.
     * @param oklab a color as a packed int that can be obtained by {@link #oklab(float, float, float, float)}
     * @return a float between 0.0f and 0.334f that represents how colorful the given value is
     */
    public static float chroma(final int oklab) {
        final float a = ((oklab >>> 7 & 0x1FE) - 255) * 0x1p-8f;
        final float b = ((oklab >>> 15 & 0x1FE) - 255) * 0x1p-8f;
        return (float) Math.sqrt(a * a + b * b);
    }

    /**
     * Given a hue and lightness, this gets the (approximate) maximum chroma possible for that hue-lightness
     * combination. This is useful to know the bounds of {@link #chroma(int)}. This should be no greater than 0.334f .
     * This usually takes its hue from {@link #hue(int)} and its lightness from {@link #lightness(int)}.
     * @param hue the hue, typically between 0.0f and 1.0f, to look up
     * @param lightness the lightness, clamped between 0.0f and 1.0f, to look up
     * @return the maximum possible chroma for the given hue and lightness, between 0.0f and 0.334f
     */
    public static float chromaLimit(final float hue, final float lightness){
        final int idx = (int) (Math.min(Math.max(lightness, 0f), 1f) * 255.999f) << 8
                | (int) (256f * (hue - floor(hue)));
        return (GAMUT_DATA[idx] + 1.5f) * 0x1p-8f;
    }

    /**
     * Gets the saturation of the given Oklab float color, but as Oklab understands saturation rather than how HSL does.
     * Saturation here is a fraction of the chroma limit (see {@link #chromaLimit(float, float)}) for a given hue and
     * lightness, and is between 0 and 1 almost all the time. Saturation should always be between 0 (inclusive) and 1
     * (inclusive).
     *
     * @param packed a packed Oklab float color
     * @return a float between 0 (inclusive) and 1 (inclusive) that represents saturation in the Oklab color space
     */
    public static float saturation(final int packed) {
        final float A = ((packed >>> 8 & 0xff) - 127.5f);
        final float B = ((packed >>> 16 & 0xff) - 127.5f);
        final float hue = MathTools.atan2_(B, A);
        final int idx = (packed & 0xff) << 8 | (int) (256f * hue);
        final float dist = GAMUT_DATA[idx] + 1.5f;
        return dist == 3.5f ? 0f : (A * A + B * B) * 4f / (dist * dist);
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
        final int L = oklab & 255, other = oklab & 0xFFFFFF00;
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
        final int i = oklab & 255, other = oklab & 0xFFFFFF00;
        return (((int) (i * (1f - change)) & 255) | other);
    }

    /**
     * Interpolates from the packed float color start towards a warmer color (orange to magenta) by change. While change
     * should be between 0f (return start as-is) and 1f (return fully warmed), start should be a packed color, as from
     * {@link #oklab(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors,
     * and is a little more efficient and clear than using {@link #lerpColors(int, int, float)} to
     * lerp towards a warmer color. Unlike {@link #lerpColors(int, int, float)}, this keeps the
     * alpha and L of start as-is.
     * @see #lowerA(int, float) the counterpart method that cools a float color
     * @param start the starting color as a packed float
     * @param change how much to warm start, as a float between 0 and 1; higher means a warmer result
     * @return a packed float that represents a color between start and a warmer color
     */
    public static int raiseA(final int start, final float change) {
        final int p = start >>> 8 & 0xFF, other = start & 0xFFFF00FF;
        return (((int) (p + (0xFF - p) * change) << 8 & 0xFF00) | other);
    }

    /**
     * Interpolates from the packed float color start towards a cooler color (green to blue) by change. While change
     * should be between 0f (return start as-is) and 1f (return fully cooled), start should be a packed color, as from
     * {@link #oklab(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
     * is a little more efficient and clear than using {@link #lerpColors(int, int, float)} to lerp
     * towards a cooler color. Unlike {@link #lerpColors(int, int, float)}, this keeps the alpha and
     * L of start as-is.
     * @see #raiseA(int, float) the counterpart method that warms a float color
     * @param start the starting color as a packed float
     * @param change how much to cool start, as a float between 0 and 1; higher means a cooler result
     * @return a packed float that represents a color between start and a cooler color
     */
    public static int lowerA(final int start, final float change) {
        final int p = start >>> 8 & 0xFF, other = start & 0xFFFF00FF;
        return (((int) (p * (1f - change)) & 0xFF) << 8 | other);
    }

    /**
     * Interpolates from the packed float color start towards a "natural" color (between green and orange) by change.
     * While change should be between 0f (return start as-is) and 1f (return fully natural), start should be a packed color, as
     * from {@link #oklab(float, float, float, float)}. This is a good way to reduce allocations of temporary
     * Colors, and is a little more efficient and clear than using
     * {@link #lerpColors(int, int, float)} to lerp towards a more natural color. Unlike
     * {@link #lerpColors(int, int, float)}, this keeps the alpha and L of start as-is.
     * @see #lowerB(int, float) the counterpart method that makes a float color less natural
     * @param start the starting color as a packed float
     * @param change how much to change start to a natural color, as a float between 0 and 1; higher means a more natural result
     * @return a packed float that represents a color between start and a more natural color
     */
    public static int raiseB(final int start, final float change) {
        final int t = start >>> 16 & 0xFF, other = start & 0xFF00FFFF;
        return (((int) (t + (0xFF - t) * change) << 16 & 0xFF0000) | other);
    }

    /**
     * Interpolates from the packed float color start towards an "artificial" color (between blue and purple) by change.
     * While change should be between 0f (return start as-is) and 1f (return fully artificial), start should be a packed color, as
     * from {@link #oklab(float, float, float, float)}. This is a good way to reduce allocations of temporary
     * Colors, and is a little more efficient and clear than using {@link #lerpColors(int, int, float)} to lerp
     * towards a more artificial color. Unlike {@link #lerpColors(int, int, float)}, this keeps the
     * alpha and L of start as-is.
     * @see #raiseB(int, float) the counterpart method that makes a float color less artificial
     * @param start the starting color as a packed float
     * @param change how much to change start to a bolder color, as a float between 0 and 1; higher means a more artificial result
     * @return a packed int that represents a color between start and a more artificial color
     */
    public static int lowerB(final int start, final float change) {
        final int t = start >>> 16 & 0xFF, other = start & 0xFF00FFFF;
        return (((int) (t * (1f - change)) & 0xFF) << 16 | other);
    }

    /**
     * Interpolates from the packed float color start towards that color made opaque by change. While change should be
     * between 0f (return start as-is) and 1f (return start with full alpha), start should be a packed color, as from
     * {@link #oklab(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors.
     * This won't change the L, A, or B of the color.
     * @see #fade(int, float) the counterpart method that makes a float color more translucent
     * @param start the starting color as a packed float
     * @param change how much to go from start toward opaque, as a float between 0 and 1; higher means closer to opaque
     * @return a packed float that represents a color between start and its opaque version
     */
    public static int blot(final int start, final float change) {
        final int opacity = start >>> 24, other = start & 0x00FFFFFF;
        return (((int) (opacity + (0xFF - opacity) * change) & 0xFF) << 24 | other);
    }

    /**
     * Interpolates from the packed float color start towards transparent by change. While change should be between 0
     * (return start as-is) and 1f (return the color with 0 alpha), start should be a packed color, as from
     * {@link #oklab(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors.
     * This won't change the L, A, or B of the color.
     * @see #blot(int, float) the counterpart method that makes a float color more opaque
     * @param start the starting color as a packed float
     * @param change how much to go from start toward transparent, as a float between 0 and 1; higher means closer to transparent
     * @return a packed float that represents a color between start and transparent
     */
    public static int fade(final int start, final float change) {
        final int opacity = start >>> 24, other = start & 0x00FFFFFF;
        return (((int) (opacity * (1f - change)) & 0xFF) << 24 | other);
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
                (oklab & 0xFF0000FF);
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
                (oklab & 0xFF0000FF));
    }

    /**
     * A different way to specify an Oklab color, using hue, saturation, lightness, and alpha like a normal HSL(A) color
     * but calculating them directly in the Oklab color space. Most colors between 0.5 and 0.75 hue that also have less
     * than 0.5 lightness are extremely desaturated and close to gray, regardless of what you give for saturation, and
     * these colors suddenly jump to very saturated around 0.75 hue and higher. To avoid this issue, you may prefer
     * using {@link #oklabByHCL(float, float, float, float)}, which takes an absolute chroma as opposed to the
     * saturation here (which is a fraction of the maximum chroma).
     * <br>
     * The saturation here refers to what fraction the chroma should be of the maximum
     * chroma for the given hue and lightness. You can use {@link #hue(int)}, {@link #saturation(int)},
     * and {@link #lightness(int)} to get the hue, saturation, and lightness values from an existing color that
     * this will understand ({@link #alpha(int)} too).
     * @param hue between 0 and 1, usually, but this will automatically wrap if too high or too low
     * @param saturation will be clamped between 0 and 1
     * @param lightness will be clamped between 0 and 1
     * @param alpha will be clamped between 0 and 1
     * @return a packed Oklab float color that tries to match the requested hue, saturation, and lightness
     */
    public static int oklabByHSL(float hue, float saturation, float lightness, float alpha) {
        lightness = Math.min(Math.max(lightness, 0f), 1f);
        saturation = Math.min(Math.max(saturation, 0f), 1f);
        hue -= floor(hue);
        alpha = Math.min(Math.max(alpha, 0f), 1f);
        final int idx = (int) (lightness * 255.999f) << 8 | (int) (256f * hue);
        final float dist = GAMUT_DATA[idx] * saturation * 0.5f;
        return (
                (int) (alpha * 255.999f) << 24 |
                        (int) (MathTools.sin_(hue) * dist + 128f) << 16 |
                        (int) (MathTools.cos_(hue) * dist + 128f) << 8 |
                        (int) (lightness * 255.999f));
    }

    /**
     * A different way to specify an Oklab color, using hue, chroma, lightness, and alpha something like a normal HSL(A)
     * color but calculating them directly in the Oklab color space. This has you specify the desired chroma directly,
     * as obtainable with {@link #chroma(int)}, rather than the saturation, which is a fraction of the maximum chroma
     * (saturation is what {@link #oklabByHSL(float, float, float, float)} uses). The hue is distributed as with
     * {@link #hue(int)}, and the lightness should be equivalent to {@link #lightness(int)}.
     * If you use this to get two colors with the same chroma and lightness, but different
     * hue, then the resulting colors should have similar colorfulness unless one or both chroma values exceeded the
     * gamut limit (you can get this limit with {@link #chromaLimit(float, float)}). If a chroma value given is greater
     * than the chroma limit, this clamps chroma to that limit. You can use {@link #hue(int)},
     * {@link #chroma(int)}, and {@link #lightness(int)} to get the hue, chroma, and lightness values from an
     * existing color that this will understand ({@link #alpha(int)} too).
     * @param hue between 0 and 1, usually, but this will automatically wrap if too high or too low
     * @param chroma will be clamped between 0 and the maximum chroma possible for the given hue and lightness
     * @param lightness will be clamped between 0 and 1
     * @param alpha will be clamped between 0 and 1
     * @return a packed Oklab float color that tries to match the requested hue, chroma, and lightness
     */
    public static int oklabByHCL(float hue, float chroma, float lightness, float alpha) {
        lightness = Math.min(Math.max(lightness, 0f), 1f);
        chroma = Math.max(chroma, 0f);
        hue -= floor(hue);
        alpha = Math.min(Math.max(alpha, 0f), 1f);
        final int idx = (int) (lightness * 255.999f) << 8 | (int) (256f * hue);
        final float dist = Math.min(chroma * 127.5f, GAMUT_DATA[idx] * 0.5f);
        return (
                (int) (alpha * 255.999f) << 24 |
                        (int) (MathTools.sin_(hue) * dist + 128f) << 16 |
                        (int) (MathTools.cos_(hue) * dist + 128f) << 8 |
                        (int) (lightness * 255.999f));
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
        final float A = ((packed >>> 8 & 0xff) - 127.5f);
        final float B = ((packed >>> 16 & 0xff) - 127.5f);
        final float hue = MathTools.atan2_(B, A);
        final int idx = (packed & 0xff) << 8 | (int) (256f * hue);
        final float dist = GAMUT_DATA[idx] * 0.5f;
        if (dist * dist >= (A * A + B * B))
            return packed;
        return (
                (packed & 0xFF0000FF) |
                        (int) (MathTools.cos_(hue) * dist + 128f) << 8 |
                        (int) (MathTools.sin_(hue) * dist + 128f) << 16);
    }

    /**
     * Checks whether the given Oklab color is in-gamut; if it isn't in-gamut, brings the color just inside
     * the gamut at the same lightness, or if it is already in-gamut, returns the color as-is. This always produces
     * an opaque color.
     * @param L lightness component; will be clamped between 0 and 255 if it isn't already
     * @param A green-to-red chromatic component; will be clamped between 0 and 255 if it isn't already
     * @param B blue-to-yellow chromatic component; will be clamped between 0 and 255 if it isn't already
     * @return the first color this finds that is in-gamut, as if it was moving toward a grayscale color with the same L
     * @see #inGamut(int, int, int) You can use inGamut() if you just want to check whether a color is in-gamut.
     */
    public static int limitToGamut(int L, int A, int B) {
        return limitToGamut(L, A, B, 255);
    }

    /**
     * Checks whether the given Oklab color is in-gamut; if it isn't in-gamut, brings the color just inside
     * the gamut at the same lightness, or if it is already in-gamut, returns the color as-is.
     * @param L lightness component; will be clamped between 0 and 255 if it isn't already
     * @param A green-to-red chromatic component; will be clamped between 0 and 255 if it isn't already
     * @param B blue-to-yellow chromatic component; will be clamped between 0 and 255 if it isn't already
     * @param alpha alpha component; will be clamped between 0 and 255 if it isn't already
     * @return the first color this finds that is in-gamut, as if it was moving toward a grayscale color with the same L
     * @see #inGamut(int, int, int) You can use inGamut() if you just want to check whether a color is in-gamut.
     */
    public static int limitToGamut(int L, int A, int B, int alpha) {
        L = Math.min(Math.max(L, 0), 255);
        A = Math.min(Math.max(A, 0), 255);
        B = Math.min(Math.max(B, 0), 255);
        alpha = Math.min(Math.max(alpha, 0), 255);
        final float A2 = (A - 127.5f) / 255f;
        final float B2 = (B - 127.5f) / 255f;
        final float hue = MathTools.atan2_(B2, A2);
        final int idx = L << 8 | (int)(256f * hue);
        final float dist = GAMUT_DATA[idx] * 0.5f;
        if(dist * dist * 0x1p-16f + 0x1p-14f >= (A2 * A2 + B2 * B2))
            return L | A << 8 | B << 16 | alpha << 24;
        return (
                alpha << 24 |
                        (int) (MathTools.sin_(hue) * dist + 128f) << 16 |
                        (int) (MathTools.cos_(hue) * dist + 128f) << 8 |
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
                sL = (s & 0xFF), sA = (s >>> 8) & 0xFF, sB = (s >>> 16) & 0xFF, sAlpha = s >>> 24,
                eL = (e & 0xFF), eA = (e >>> 8) & 0xFF, eB = (e >>> 16) & 0xFF, eAlpha = e >>> 24;
        return (((int) (sL + change * (eL - sL)) & 0xFF)
                | (((int) (sA + change * (eA - sA)) & 0xFF) << 8)
                | (((int) (sB + change * (eB - sB)) & 0xFF) << 16)
                | (((int) (sAlpha + change * (eAlpha - sAlpha)) & 0xFF) << 24));
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