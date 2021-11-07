package com.github.tommyettinger.colorful.oklab;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.ObjectFloatMap;

import java.util.Comparator;

import static com.github.tommyettinger.colorful.oklab.ColorTools.*;

/**
 * A palette of predefined colors as packed Oklab floats, the kind {@link ColorTools} works with.
 * You can access colors by their constant name, such as {@code OCEAN_BLUE}, by the {@link #NAMED} map using
 * {@code NAMED.get("Ocean Blue", 0f)}, or by index in the FloatArray called {@link #LIST}. Note that to access a float
 * color from NAMED, you need to give a default value if the name is not found; {@code 0f} is a good default because it
 * will not occur in a valid Oklab color. You can access the names in a specific order with {@link #NAMES} (which is
 * alphabetical), {@link #NAMES_BY_HUE} (which is sorted by the hue of the matching color, from red to yellow to blue
 * (with gray around here) to purple to red again), or {@link #NAMES_BY_LIGHTNESS} (which is sorted by the intensity of
 * the matching color, from darkest to lightest). Having a name lets you look up the matching color in {@link #NAMED}.
 * <br>
 * Created by Tommy Ettinger on 10/13/2020.
 */
public class Palette {
    public static final ObjectFloatMap<String> NAMED = new ObjectFloatMap<String>(256);
    public static final FloatArray LIST = new FloatArray(256);

    /**
     * This color constant "Transparent" has RGBA8888 code {@code 00000000}, L 0.0, A 0.49803922, B 0.49803922, alpha 0.0, hue 0.625, and saturation 0.017527897.
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
     * This color constant "Black" has RGBA8888 code {@code 000000FF}, L 0.0, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, and saturation 0.017527897.
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
     * This color constant "Coal Black" has RGBA8888 code {@code 131313FF}, L 0.10980392, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, and saturation 0.06172868.
     * It can be represented as a packed float with the constant {@code -0x1.fefe38p125F}.
     * <pre>
     * <font style='background-color: #131313;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #131313; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #131313;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #131313'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #131313'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #131313'>&nbsp;@&nbsp;</font><font style='background-color: #131313; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #131313;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #131313; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float COAL_BLACK = -0x1.fefe38p125F;
    static { NAMED.put("Coal Black", -0x1.fefe38p125F); LIST.add(-0x1.fefe38p125F); }

    /**
     * This color constant "Shadow" has RGBA8888 code {@code 252525FF}, L 0.1764706, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, and saturation 0.067607604.
     * It can be represented as a packed float with the constant {@code -0x1.fefe5ap125F}.
     * <pre>
     * <font style='background-color: #252525;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #252525; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #252525;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #252525'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #252525'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #252525'>&nbsp;@&nbsp;</font><font style='background-color: #252525; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #252525;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #252525; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SHADOW = -0x1.fefe5ap125F;
    static { NAMED.put("Shadow", -0x1.fefe5ap125F); LIST.add(-0x1.fefe5ap125F); }

    /**
     * This color constant "Graphite" has RGBA8888 code {@code 373737FF}, L 0.24313726, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, and saturation 0.06172868.
     * It can be represented as a packed float with the constant {@code -0x1.fefe7cp125F}.
     * <pre>
     * <font style='background-color: #373737;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #373737; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #373737;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #373737'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #373737'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #373737'>&nbsp;@&nbsp;</font><font style='background-color: #373737; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #373737;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #373737; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAPHITE = -0x1.fefe7cp125F;
    static { NAMED.put("Graphite", -0x1.fefe7cp125F); LIST.add(-0x1.fefe7cp125F); }

    /**
     * This color constant "Dark Gray" has RGBA8888 code {@code 494949FF}, L 0.30588236, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, and saturation 0.05460614.
     * It can be represented as a packed float with the constant {@code -0x1.fefe9cp125F}.
     * <pre>
     * <font style='background-color: #494949;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #494949; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #494949;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #494949'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #494949'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #494949'>&nbsp;@&nbsp;</font><font style='background-color: #494949; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #494949;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #494949; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY = -0x1.fefe9cp125F;
    static { NAMED.put("Dark Gray", -0x1.fefe9cp125F); LIST.add(-0x1.fefe9cp125F); }

    /**
     * This color constant "Lead" has RGBA8888 code {@code 5B5B5BFF}, L 0.3647059, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, and saturation 0.0507057.
     * It can be represented as a packed float with the constant {@code -0x1.fefebap125F}.
     * <pre>
     * <font style='background-color: #5B5B5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B5B5B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B5B5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5B5B5B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5B5B5B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5B5B5B'>&nbsp;@&nbsp;</font><font style='background-color: #5B5B5B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B5B5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B5B5B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD = -0x1.fefebap125F;
    static { NAMED.put("Lead", -0x1.fefebap125F); LIST.add(-0x1.fefebap125F); }

    /**
     * This color constant "Iron" has RGBA8888 code {@code 6E6E6EFF}, L 0.43137255, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, and saturation 0.045798697.
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
     * This color constant "Gray" has RGBA8888 code {@code 808080FF}, L 0.49411765, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, and saturation 0.041757636.
     * It can be represented as a packed float with the constant {@code -0x1.fefefcp125F}.
     * <pre>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #808080; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #808080; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #808080; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY = -0x1.fefefcp125F;
    static { NAMED.put("Gray", -0x1.fefefcp125F); LIST.add(-0x1.fefefcp125F); }

    /**
     * This color constant "Chinchilla" has RGBA8888 code {@code 929292FF}, L 0.56078434, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, and saturation 0.038371883.
     * It can be represented as a packed float with the constant {@code -0x1.feff1ep125F}.
     * <pre>
     * <font style='background-color: #929292;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #929292; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #929292;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #929292'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #929292'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #929292'>&nbsp;@&nbsp;</font><font style='background-color: #929292; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #929292;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #929292; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CHINCHILLA = -0x1.feff1ep125F;
    static { NAMED.put("Chinchilla", -0x1.feff1ep125F); LIST.add(-0x1.feff1ep125F); }

    /**
     * This color constant "Greyhound" has RGBA8888 code {@code A4A4A4FF}, L 0.627451, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, and saturation 0.035493992.
     * It can be represented as a packed float with the constant {@code -0x1.feff4p125F}.
     * <pre>
     * <font style='background-color: #A4A4A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4A4A4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4A4A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A4A4A4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A4A4A4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A4A4A4'>&nbsp;@&nbsp;</font><font style='background-color: #A4A4A4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4A4A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4A4A4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GREYHOUND = -0x1.feff4p125F;
    static { NAMED.put("Greyhound", -0x1.feff4p125F); LIST.add(-0x1.feff4p125F); }

    /**
     * This color constant "Silver" has RGBA8888 code {@code B6B6B6FF}, L 0.69411767, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, and saturation 0.037362095.
     * It can be represented as a packed float with the constant {@code -0x1.feff62p125F}.
     * <pre>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B6B6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #B6B6B6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B6B6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER = -0x1.feff62p125F;
    static { NAMED.put("Silver", -0x1.feff62p125F); LIST.add(-0x1.feff62p125F); }

    /**
     * This color constant "Light Gray" has RGBA8888 code {@code C9C9C9FF}, L 0.76862746, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, and saturation 0.0507057.
     * It can be represented as a packed float with the constant {@code -0x1.feff88p125F}.
     * <pre>
     * <font style='background-color: #C9C9C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9C9C9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9C9C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C9C9C9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C9C9C9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C9C9C9'>&nbsp;@&nbsp;</font><font style='background-color: #C9C9C9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9C9C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9C9C9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY = -0x1.feff88p125F;
    static { NAMED.put("Light Gray", -0x1.feff88p125F); LIST.add(-0x1.feff88p125F); }

    /**
     * This color constant "Platinum" has RGBA8888 code {@code DBDBDBFF}, L 0.84313726, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, and saturation 0.070987985.
     * It can be represented as a packed float with the constant {@code -0x1.feffaep125F}.
     * <pre>
     * <font style='background-color: #DBDBDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBDBDB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBDBDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DBDBDB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DBDBDB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DBDBDB'>&nbsp;@&nbsp;</font><font style='background-color: #DBDBDB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBDBDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBDBDB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PLATINUM = -0x1.feffaep125F;
    static { NAMED.put("Platinum", -0x1.feffaep125F); LIST.add(-0x1.feffaep125F); }

    /**
     * This color constant "Cloud" has RGBA8888 code {@code EDEDEDFF}, L 0.92156863, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, and saturation 0.12906906.
     * It can be represented as a packed float with the constant {@code -0x1.feffd6p125F}.
     * <pre>
     * <font style='background-color: #EDEDED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDEDED; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDEDED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EDEDED'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EDEDED'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EDEDED'>&nbsp;@&nbsp;</font><font style='background-color: #EDEDED; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDEDED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDEDED; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CLOUD = -0x1.feffd6p125F;
    static { NAMED.put("Cloud", -0x1.feffd6p125F); LIST.add(-0x1.feffd6p125F); }

    /**
     * This color constant "White" has RGBA8888 code {@code FFFFFFFF}, L 1.0, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, and saturation 0.0.
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
     * This color constant "Seawater" has RGBA8888 code {@code 007F7FFF}, L 0.43137255, A 0.4509804, B 0.4862745, alpha 1.0, hue 0.543443, and saturation 0.93083286.
     * It can be represented as a packed float with the constant {@code -0x1.f8e6dcp125F}.
     * <pre>
     * <font style='background-color: #007F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007F7F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #007F7F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #007F7F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #007F7F'>&nbsp;@&nbsp;</font><font style='background-color: #007F7F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007F7F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SEAWATER = -0x1.f8e6dcp125F;
    static { NAMED.put("Seawater", -0x1.f8e6dcp125F); LIST.add(-0x1.f8e6dcp125F); }

    /**
     * This color constant "Hospital Green" has RGBA8888 code {@code 3FBFBFFF}, L 0.6392157, A 0.44313726, B 0.48235294, alpha 1.0, hue 0.54788184, and saturation 0.8467647.
     * It can be represented as a packed float with the constant {@code -0x1.f6e346p125F}.
     * <pre>
     * <font style='background-color: #3FBFBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3FBFBF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3FBFBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3FBFBF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3FBFBF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3FBFBF'>&nbsp;@&nbsp;</font><font style='background-color: #3FBFBF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3FBFBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3FBFBF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float HOSPITAL_GREEN = -0x1.f6e346p125F;
    static { NAMED.put("Hospital Green", -0x1.f6e346p125F); LIST.add(-0x1.f6e346p125F); }

    /**
     * This color constant "Cyan" has RGBA8888 code {@code 00FFFFFF}, L 0.84705883, A 0.42352942, B 0.47843137, alpha 1.0, hue 0.54374534, and saturation 0.94605833.
     * It can be represented as a packed float with the constant {@code -0x1.f4d9bp125F}.
     * <pre>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #00FFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CYAN = -0x1.f4d9bp125F;
    static { NAMED.put("Cyan", -0x1.f4d9bp125F); LIST.add(-0x1.f4d9bp125F); }

    /**
     * This color constant "Bubble" has RGBA8888 code {@code BFFFFFFF}, L 0.9372549, A 0.47058824, B 0.49019608, alpha 1.0, hue 0.5511957, and saturation 0.88185525.
     * It can be represented as a packed float with the constant {@code -0x1.faf1dep125F}.
     * <pre>
     * <font style='background-color: #BFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFFFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #BFFFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFFFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BUBBLE = -0x1.faf1dep125F;
    static { NAMED.put("Bubble", -0x1.faf1dep125F); LIST.add(-0x1.faf1dep125F); }

    /**
     * This color constant "Periwinkle" has RGBA8888 code {@code 8181FFFF}, L 0.5568628, A 0.5137255, B 0.41568628, alpha 1.0, hue 0.77569586, and saturation 0.9508019.
     * It can be represented as a packed float with the constant {@code -0x1.d5071cp125F}.
     * <pre>
     * <font style='background-color: #8181FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8181FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8181FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8181FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8181FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8181FF'>&nbsp;@&nbsp;</font><font style='background-color: #8181FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8181FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8181FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PERIWINKLE = -0x1.d5071cp125F;
    static { NAMED.put("Periwinkle", -0x1.d5071cp125F); LIST.add(-0x1.d5071cp125F); }

    /**
     * This color constant "Blue" has RGBA8888 code {@code 0000FFFF}, L 0.32156864, A 0.48235294, B 0.34117648, alpha 1.0, hue 0.7323789, and saturation 0.98575985.
     * It can be represented as a packed float with the constant {@code -0x1.aef6a4p125F}.
     * <pre>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0000FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #0000FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0000FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUE = -0x1.aef6a4p125F;
    static { NAMED.put("Blue", -0x1.aef6a4p125F); LIST.add(-0x1.aef6a4p125F); }

    /**
     * This color constant "Faded Blue" has RGBA8888 code {@code 3F3FBFFF}, L 0.34117648, A 0.50980395, B 0.4, alpha 1.0, hue 0.7655599, and saturation 0.64306843.
     * It can be represented as a packed float with the constant {@code -0x1.cd04aep125F}.
     * <pre>
     * <font style='background-color: #3F3FBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F3FBF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F3FBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3F3FBF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3F3FBF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3F3FBF'>&nbsp;@&nbsp;</font><font style='background-color: #3F3FBF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F3FBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F3FBF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_BLUE = -0x1.cd04aep125F;
    static { NAMED.put("Faded Blue", -0x1.cd04aep125F); LIST.add(-0x1.cd04aep125F); }

    /**
     * This color constant "Ocean Blue" has RGBA8888 code {@code 00007FFF}, L 0.18431373, A 0.4862745, B 0.4, alpha 1.0, hue 0.7282781, and saturation 0.87593263.
     * It can be represented as a packed float with the constant {@code -0x1.ccf85ep125F}.
     * <pre>
     * <font style='background-color: #00007F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00007F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00007F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00007F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00007F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00007F'>&nbsp;@&nbsp;</font><font style='background-color: #00007F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00007F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00007F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float OCEAN_BLUE = -0x1.ccf85ep125F;
    static { NAMED.put("Ocean Blue", -0x1.ccf85ep125F); LIST.add(-0x1.ccf85ep125F); }

    /**
     * This color constant "Stygian Blue" has RGBA8888 code {@code 0F0F50FF}, L 0.14509805, A 0.49803922, B 0.43137255, alpha 1.0, hue 0.7454664, and saturation 0.7173795.
     * It can be represented as a packed float with the constant {@code -0x1.dcfe4ap125F}.
     * <pre>
     * <font style='background-color: #0F0F50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F0F50; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F0F50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0F0F50'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0F0F50'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0F0F50'>&nbsp;@&nbsp;</font><font style='background-color: #0F0F50; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F0F50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F0F50; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STYGIAN_BLUE = -0x1.dcfe4ap125F;
    static { NAMED.put("Stygian Blue", -0x1.dcfe4ap125F); LIST.add(-0x1.dcfe4ap125F); }

    /**
     * This color constant "Deep Purple" has RGBA8888 code {@code 7F007FFF}, L 0.30980393, A 0.58431375, B 0.44313726, alpha 1.0, hue 0.9055531, and saturation 0.9134843.
     * It can be represented as a packed float with the constant {@code -0x1.e32a9ep125F}.
     * <pre>
     * <font style='background-color: #7F007F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F007F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F007F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7F007F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7F007F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7F007F'>&nbsp;@&nbsp;</font><font style='background-color: #7F007F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F007F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F007F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE = -0x1.e32a9ep125F;
    static { NAMED.put("Deep Purple", -0x1.e32a9ep125F); LIST.add(-0x1.e32a9ep125F); }

    /**
     * This color constant "Tyrian Purple" has RGBA8888 code {@code BF3FBFFF}, L 0.47843137, A 0.5882353, B 0.4392157, alpha 1.0, hue 0.9039804, and saturation 0.72182363.
     * It can be represented as a packed float with the constant {@code -0x1.e12cf4p125F}.
     * <pre>
     * <font style='background-color: #BF3FBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF3FBF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF3FBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BF3FBF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BF3FBF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BF3FBF'>&nbsp;@&nbsp;</font><font style='background-color: #BF3FBF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF3FBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF3FBF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TYRIAN_PURPLE = -0x1.e12cf4p125F;
    static { NAMED.put("Tyrian Purple", -0x1.e12cf4p125F); LIST.add(-0x1.e12cf4p125F); }

    /**
     * This color constant "Magenta" has RGBA8888 code {@code F500F5FF}, L 0.5529412, A 0.6313726, B 0.41568628, alpha 1.0, hue 0.9091779, and saturation 0.9629369.
     * It can be represented as a packed float with the constant {@code -0x1.d5431ap125F}.
     * <pre>
     * <font style='background-color: #F500F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F500F5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F500F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F500F5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F500F5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F500F5'>&nbsp;@&nbsp;</font><font style='background-color: #F500F5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F500F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F500F5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MAGENTA = -0x1.d5431ap125F;
    static { NAMED.put("Magenta", -0x1.d5431ap125F); LIST.add(-0x1.d5431ap125F); }

    /**
     * This color constant "Bubblegum Pink" has RGBA8888 code {@code FD81FFFF}, L 0.69411767, A 0.5803922, B 0.44313726, alpha 1.0, hue 0.9020082, and saturation 1.0083292.
     * It can be represented as a packed float with the constant {@code -0x1.e32962p125F}.
     * <pre>
     * <font style='background-color: #FD81FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD81FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD81FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FD81FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FD81FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FD81FF'>&nbsp;@&nbsp;</font><font style='background-color: #FD81FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD81FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD81FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BUBBLEGUM_PINK = -0x1.e32962p125F;
    static { NAMED.put("Bubblegum Pink", -0x1.e32962p125F); LIST.add(-0x1.e32962p125F); }

    /**
     * This color constant "Pork Chop" has RGBA8888 code {@code FFC0CBFF}, L 0.80784315, A 0.5294118, B 0.5019608, alpha 1.0, hue 0.010589795, and saturation 0.83845836.
     * It can be represented as a packed float with the constant {@code -0x1.010f9cp126F}.
     * <pre>
     * <font style='background-color: #FFC0CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFC0CB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFC0CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFC0CB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFC0CB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFC0CB'>&nbsp;@&nbsp;</font><font style='background-color: #FFC0CB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFC0CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFC0CB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PORK_CHOP = -0x1.010f9cp126F;
    static { NAMED.put("Pork Chop", -0x1.010f9cp126F); LIST.add(-0x1.010f9cp126F); }

    /**
     * This color constant "Raw Meat" has RGBA8888 code {@code FF8181FF}, L 0.64705884, A 0.5647059, B 0.52156866, alpha 1.0, hue 0.05119568, and saturation 0.9438235.
     * It can be represented as a packed float with the constant {@code -0x1.0b214ap126F}.
     * <pre>
     * <font style='background-color: #FF8181;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF8181; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF8181;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF8181'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF8181'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF8181'>&nbsp;@&nbsp;</font><font style='background-color: #FF8181; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF8181;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF8181; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float RAW_MEAT = -0x1.0b214ap126F;
    static { NAMED.put("Raw Meat", -0x1.0b214ap126F); LIST.add(-0x1.0b214ap126F); }

    /**
     * This color constant "Red" has RGBA8888 code {@code FF0000FF}, L 0.49019608, A 0.6117647, B 0.56078434, alpha 1.0, hue 0.07928106, and saturation 0.9722233.
     * It can be represented as a packed float with the constant {@code -0x1.1f38fap126F}.
     * <pre>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #FF0000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float RED = -0x1.1f38fap126F;
    static { NAMED.put("Red", -0x1.1f38fap126F); LIST.add(-0x1.1f38fap126F); }

    /**
     * This color constant "Putty" has RGBA8888 code {@code BF3F3FFF}, L 0.43137255, A 0.57254905, B 0.5294118, alpha 1.0, hue 0.06128862, and saturation 0.668025.
     * It can be represented as a packed float with the constant {@code -0x1.0f24dcp126F}.
     * <pre>
     * <font style='background-color: #BF3F3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF3F3F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF3F3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BF3F3F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BF3F3F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BF3F3F'>&nbsp;@&nbsp;</font><font style='background-color: #BF3F3F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF3F3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF3F3F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PUTTY = -0x1.0f24dcp126F;
    static { NAMED.put("Putty", -0x1.0f24dcp126F); LIST.add(-0x1.0f24dcp126F); }

    /**
     * This color constant "Sienna" has RGBA8888 code {@code 7F0000FF}, L 0.27058825, A 0.5686275, B 0.5372549, alpha 1.0, hue 0.07915777, and saturation 0.88846207.
     * It can be represented as a packed float with the constant {@code -0x1.13228ap126F}.
     * <pre>
     * <font style='background-color: #7F0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F0000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7F0000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7F0000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7F0000'>&nbsp;@&nbsp;</font><font style='background-color: #7F0000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F0000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SIENNA = -0x1.13228ap126F;
    static { NAMED.put("Sienna", -0x1.13228ap126F); LIST.add(-0x1.13228ap126F); }

    /**
     * This color constant "Seal Brown" has RGBA8888 code {@code 551414FF}, L 0.20784314, A 0.54509807, B 0.52156866, alpha 1.0, hue 0.07099588, and saturation 0.67355466.
     * It can be represented as a packed float with the constant {@code -0x1.0b166ap126F}.
     * <pre>
     * <font style='background-color: #551414;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #551414; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #551414;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #551414'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #551414'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #551414'>&nbsp;@&nbsp;</font><font style='background-color: #551414; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #551414;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #551414; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SEAL_BROWN = -0x1.0b166ap126F;
    static { NAMED.put("Seal Brown", -0x1.0b166ap126F); LIST.add(-0x1.0b166ap126F); }

    /**
     * This color constant "Mummy Brown" has RGBA8888 code {@code 7F3F00FF}, L 0.33333334, A 0.5294118, B 0.54509807, alpha 1.0, hue 0.15801254, and saturation 0.86146075.
     * It can be represented as a packed float with the constant {@code -0x1.170eaap126F}.
     * <pre>
     * <font style='background-color: #7F3F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F3F00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F3F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7F3F00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7F3F00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7F3F00'>&nbsp;@&nbsp;</font><font style='background-color: #7F3F00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F3F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F3F00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MUMMY_BROWN = -0x1.170eaap126F;
    static { NAMED.put("Mummy Brown", -0x1.170eaap126F); LIST.add(-0x1.170eaap126F); }

    /**
     * This color constant "Fawn" has RGBA8888 code {@code BF7F3FFF}, L 0.54509807, A 0.52156866, B 0.54901963, alpha 1.0, hue 0.18403731, and saturation 0.68550277.
     * It can be represented as a packed float with the constant {@code -0x1.190b16p126F}.
     * <pre>
     * <font style='background-color: #BF7F3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF7F3F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF7F3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BF7F3F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BF7F3F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BF7F3F'>&nbsp;@&nbsp;</font><font style='background-color: #BF7F3F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF7F3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF7F3F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FAWN = -0x1.190b16p126F;
    static { NAMED.put("Fawn", -0x1.190b16p126F); LIST.add(-0x1.190b16p126F); }

    /**
     * This color constant "Orange" has RGBA8888 code {@code FF7F00FF}, L 0.627451, A 0.54901963, B 0.57254905, alpha 1.0, hue 0.15541562, and saturation 0.9745509.
     * It can be represented as a packed float with the constant {@code -0x1.25194p126F}.
     * <pre>
     * <font style='background-color: #FF7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF7F00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF7F00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF7F00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF7F00'>&nbsp;@&nbsp;</font><font style='background-color: #FF7F00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF7F00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ORANGE = -0x1.25194p126F;
    static { NAMED.put("Orange", -0x1.25194p126F); LIST.add(-0x1.25194p126F); }

    /**
     * This color constant "Peach" has RGBA8888 code {@code FFBF81FF}, L 0.78431374, A 0.5176471, B 0.54509807, alpha 1.0, hue 0.19064914, and saturation 0.9536552.
     * It can be represented as a packed float with the constant {@code -0x1.17099p126F}.
     * <pre>
     * <font style='background-color: #FFBF81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFBF81; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFBF81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFBF81'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFBF81'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFBF81'>&nbsp;@&nbsp;</font><font style='background-color: #FFBF81; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFBF81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFBF81; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PEACH = -0x1.17099p126F;
    static { NAMED.put("Peach", -0x1.17099p126F); LIST.add(-0x1.17099p126F); }

    /**
     * This color constant "Cream" has RGBA8888 code {@code FFFFBFFF}, L 0.9764706, A 0.4862745, B 0.53333336, alpha 1.0, hue 0.31215638, and saturation 0.80247283.
     * It can be represented as a packed float with the constant {@code -0x1.10f9f2p126F}.
     * <pre>
     * <font style='background-color: #FFFFBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFBF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFFBF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFFBF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFFBF'>&nbsp;@&nbsp;</font><font style='background-color: #FFFFBF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFBF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CREAM = -0x1.10f9f2p126F;
    static { NAMED.put("Cream", -0x1.10f9f2p126F); LIST.add(-0x1.10f9f2p126F); }

    /**
     * This color constant "Yellow" has RGBA8888 code {@code FFFF00FF}, L 0.94509804, A 0.4627451, B 0.59607846, alpha 1.0, hue 0.30886024, and saturation 0.9592878.
     * It can be represented as a packed float with the constant {@code -0x1.30ede2p126F}.
     * <pre>
     * <font style='background-color: #FFFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFF00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFF00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFF00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFF00'>&nbsp;@&nbsp;</font><font style='background-color: #FFFF00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFF00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float YELLOW = -0x1.30ede2p126F;
    static { NAMED.put("Yellow", -0x1.30ede2p126F); LIST.add(-0x1.30ede2p126F); }

    /**
     * This color constant "Earwax" has RGBA8888 code {@code BFBF3FFF}, L 0.69803923, A 0.4745098, B 0.5686275, alpha 1.0, hue 0.30658844, and saturation 0.81484187.
     * It can be represented as a packed float with the constant {@code -0x1.22f364p126F}.
     * <pre>
     * <font style='background-color: #BFBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFBF3F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BFBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BFBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BFBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #BFBF3F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFBF3F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float EARWAX = -0x1.22f364p126F;
    static { NAMED.put("Earwax", -0x1.22f364p126F); LIST.add(-0x1.22f364p126F); }

    /**
     * This color constant "Umber" has RGBA8888 code {@code 7F7F00FF}, L 0.47058824, A 0.4745098, B 0.56078434, alpha 1.0, hue 0.31318712, and saturation 0.93742496.
     * It can be represented as a packed float with the constant {@code -0x1.1ef2fp126F}.
     * <pre>
     * <font style='background-color: #7F7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F7F00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7F7F00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7F7F00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7F7F00'>&nbsp;@&nbsp;</font><font style='background-color: #7F7F00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F7F00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float UMBER = -0x1.1ef2fp126F;
    static { NAMED.put("Umber", -0x1.1ef2fp126F); LIST.add(-0x1.1ef2fp126F); }

    /**
     * This color constant "Ivy Green" has RGBA8888 code {@code 007F00FF}, L 0.40392157, A 0.42352942, B 0.5529412, alpha 1.0, hue 0.4036119, and saturation 0.95240366.
     * It can be represented as a packed float with the constant {@code -0x1.1ad8cep126F}.
     * <pre>
     * <font style='background-color: #007F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007F00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #007F00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #007F00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #007F00'>&nbsp;@&nbsp;</font><font style='background-color: #007F00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007F00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float IVY_GREEN = -0x1.1ad8cep126F;
    static { NAMED.put("Ivy Green", -0x1.1ad8cep126F); LIST.add(-0x1.1ad8cep126F); }

    /**
     * This color constant "Jade" has RGBA8888 code {@code 3FBF3FFF}, L 0.6039216, A 0.41568628, B 0.5568628, alpha 1.0, hue 0.4055531, and saturation 0.81357193.
     * It can be represented as a packed float with the constant {@code -0x1.1cd534p126F}.
     * <pre>
     * <font style='background-color: #3FBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3FBF3F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3FBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3FBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3FBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3FBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #3FBF3F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3FBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3FBF3F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float JADE = -0x1.1cd534p126F;
    static { NAMED.put("Jade", -0x1.1cd534p126F); LIST.add(-0x1.1cd534p126F); }

    /**
     * This color constant "Green" has RGBA8888 code {@code 00FF00FF}, L 0.7882353, A 0.38039216, B 0.5882353, alpha 1.0, hue 0.39883053, and saturation 0.9756364.
     * It can be represented as a packed float with the constant {@code -0x1.2cc392p126F}.
     * <pre>
     * <font style='background-color: #00FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FF00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FF00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FF00'>&nbsp;@&nbsp;</font><font style='background-color: #00FF00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GREEN = -0x1.2cc392p126F;
    static { NAMED.put("Green", -0x1.2cc392p126F); LIST.add(-0x1.2cc392p126F); }

    /**
     * This color constant "Celadon" has RGBA8888 code {@code AFFFAFFF}, L 0.8901961, A 0.44705883, B 0.53333336, alpha 1.0, hue 0.41055703, and saturation 0.94209516.
     * It can be represented as a packed float with the constant {@code -0x1.10e5c6p126F}.
     * <pre>
     * <font style='background-color: #AFFFAF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AFFFAF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AFFFAF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AFFFAF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AFFFAF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AFFFAF'>&nbsp;@&nbsp;</font><font style='background-color: #AFFFAF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AFFFAF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AFFFAF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CELADON = -0x1.10e5c6p126F;
    static { NAMED.put("Celadon", -0x1.10e5c6p126F); LIST.add(-0x1.10e5c6p126F); }

    /**
     * This color constant "Puce" has RGBA8888 code {@code BCAFC0FF}, L 0.6862745, A 0.5058824, B 0.49019608, alpha 1.0, hue 0.83601886, and saturation 0.16725196.
     * It can be represented as a packed float with the constant {@code -0x1.fb035ep125F}.
     * <pre>
     * <font style='background-color: #BCAFC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BCAFC0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BCAFC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BCAFC0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BCAFC0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BCAFC0'>&nbsp;@&nbsp;</font><font style='background-color: #BCAFC0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BCAFC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BCAFC0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PUCE = -0x1.fb035ep125F;
    static { NAMED.put("Puce", -0x1.fb035ep125F); LIST.add(-0x1.fb035ep125F); }

    /**
     * This color constant "Beige" has RGBA8888 code {@code CBAA89FF}, L 0.67058825, A 0.50980395, B 0.5254902, alpha 1.0, hue 0.19157475, and saturation 0.31073356.
     * It can be represented as a packed float with the constant {@code -0x1.0d0556p126F}.
     * <pre>
     * <font style='background-color: #CBAA89;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBAA89; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBAA89;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CBAA89'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CBAA89'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CBAA89'>&nbsp;@&nbsp;</font><font style='background-color: #CBAA89; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBAA89;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBAA89; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BEIGE = -0x1.0d0556p126F;
    static { NAMED.put("Beige", -0x1.0d0556p126F); LIST.add(-0x1.0d0556p126F); }

    /**
     * This color constant "Wet Stone" has RGBA8888 code {@code A6A090FF}, L 0.6117647, A 0.49803922, B 0.50980395, alpha 1.0, hue 0.28142345, and saturation 0.12797539.
     * It can be represented as a packed float with the constant {@code -0x1.04ff38p126F}.
     * <pre>
     * <font style='background-color: #A6A090;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A6A090; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A6A090;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A6A090'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A6A090'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A6A090'>&nbsp;@&nbsp;</font><font style='background-color: #A6A090; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A6A090;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A6A090; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WET_STONE = -0x1.04ff38p126F;
    static { NAMED.put("Wet Stone", -0x1.04ff38p126F); LIST.add(-0x1.04ff38p126F); }

    /**
     * This color constant "Slow Creek" has RGBA8888 code {@code 7E9494FF}, L 0.54901963, A 0.4862745, B 0.49411765, alpha 1.0, hue 0.5644313, and saturation 0.23168603.
     * It can be represented as a packed float with the constant {@code -0x1.fcf918p125F}.
     * <pre>
     * <font style='background-color: #7E9494;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E9494; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E9494;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7E9494'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7E9494'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7E9494'>&nbsp;@&nbsp;</font><font style='background-color: #7E9494; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E9494;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E9494; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SLOW_CREEK = -0x1.fcf918p125F;
    static { NAMED.put("Slow Creek", -0x1.fcf918p125F); LIST.add(-0x1.fcf918p125F); }

    /**
     * This color constant "Slate Gray" has RGBA8888 code {@code 6E8287FF}, L 0.4862745, A 0.4862745, B 0.49019608, alpha 1.0, hue 0.59872866, and saturation 0.26987687.
     * It can be represented as a packed float with the constant {@code -0x1.faf8f8p125F}.
     * <pre>
     * <font style='background-color: #6E8287;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E8287; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E8287;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6E8287'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6E8287'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6E8287'>&nbsp;@&nbsp;</font><font style='background-color: #6E8287; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E8287;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E8287; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SLATE_GRAY = -0x1.faf8f8p125F;
    static { NAMED.put("Slate Gray", -0x1.faf8f8p125F); LIST.add(-0x1.faf8f8p125F); }

    /**
     * This color constant "Light Skin 1" has RGBA8888 code {@code 7E6E60FF}, L 0.44313726, A 0.5058824, B 0.50980395, alpha 1.0, hue 0.16398115, and saturation 0.15404785.
     * It can be represented as a packed float with the constant {@code -0x1.0502e2p126F}.
     * <pre>
     * <font style='background-color: #7E6E60;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E6E60; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E6E60;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7E6E60'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7E6E60'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7E6E60'>&nbsp;@&nbsp;</font><font style='background-color: #7E6E60; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E6E60;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E6E60; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_SKIN_1 = -0x1.0502e2p126F;
    static { NAMED.put("Light Skin 1", -0x1.0502e2p126F); LIST.add(-0x1.0502e2p126F); }

    /**
     * This color constant "Light Skin 2" has RGBA8888 code {@code A0695FFF}, L 0.46666667, A 0.5294118, B 0.5176471, alpha 1.0, hue 0.08601887, and saturation 0.28324926.
     * It can be represented as a packed float with the constant {@code -0x1.090eeep126F}.
     * <pre>
     * <font style='background-color: #A0695F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A0695F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A0695F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A0695F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A0695F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A0695F'>&nbsp;@&nbsp;</font><font style='background-color: #A0695F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A0695F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A0695F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_SKIN_2 = -0x1.090eeep126F;
    static { NAMED.put("Light Skin 2", -0x1.090eeep126F); LIST.add(-0x1.090eeep126F); }

    /**
     * This color constant "Light Skin 3" has RGBA8888 code {@code C07872FF}, L 0.5411765, A 0.5372549, B 0.5176471, alpha 1.0, hue 0.070401505, and saturation 0.38374987.
     * It can be represented as a packed float with the constant {@code -0x1.091314p126F}.
     * <pre>
     * <font style='background-color: #C07872;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C07872; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C07872;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C07872'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C07872'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C07872'>&nbsp;@&nbsp;</font><font style='background-color: #C07872; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C07872;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C07872; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_SKIN_3 = -0x1.091314p126F;
    static { NAMED.put("Light Skin 3", -0x1.091314p126F); LIST.add(-0x1.091314p126F); }

    /**
     * This color constant "Light Skin 4" has RGBA8888 code {@code D08A74FF}, L 0.6, A 0.53333336, B 0.5254902, alpha 1.0, hue 0.10391619, and saturation 0.47744137.
     * It can be represented as a packed float with the constant {@code -0x1.0d1132p126F}.
     * <pre>
     * <font style='background-color: #D08A74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D08A74; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D08A74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D08A74'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D08A74'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D08A74'>&nbsp;@&nbsp;</font><font style='background-color: #D08A74; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D08A74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D08A74; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_SKIN_4 = -0x1.0d1132p126F;
    static { NAMED.put("Light Skin 4", -0x1.0d1132p126F); LIST.add(-0x1.0d1132p126F); }

    /**
     * This color constant "Light Skin 5" has RGBA8888 code {@code E19B7DFF}, L 0.65882355, A 0.5294118, B 0.5294118, alpha 1.0, hue 0.125, and saturation 0.57557815.
     * It can be represented as a packed float with the constant {@code -0x1.0f0f5p126F}.
     * <pre>
     * <font style='background-color: #E19B7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E19B7D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E19B7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E19B7D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E19B7D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E19B7D'>&nbsp;@&nbsp;</font><font style='background-color: #E19B7D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E19B7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E19B7D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_SKIN_5 = -0x1.0f0f5p126F;
    static { NAMED.put("Light Skin 5", -0x1.0f0f5p126F); LIST.add(-0x1.0f0f5p126F); }

    /**
     * This color constant "Light Skin 6" has RGBA8888 code {@code EBAA8CFF}, L 0.70980394, A 0.5254902, B 0.5254902, alpha 1.0, hue 0.125, and saturation 0.61522907.
     * It can be represented as a packed float with the constant {@code -0x1.0d0d6ap126F}.
     * <pre>
     * <font style='background-color: #EBAA8C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBAA8C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBAA8C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EBAA8C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EBAA8C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EBAA8C'>&nbsp;@&nbsp;</font><font style='background-color: #EBAA8C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBAA8C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBAA8C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_SKIN_6 = -0x1.0d0d6ap126F;
    static { NAMED.put("Light Skin 6", -0x1.0d0d6ap126F); LIST.add(-0x1.0d0d6ap126F); }

    /**
     * This color constant "Light Skin 7" has RGBA8888 code {@code F5B99BFF}, L 0.7647059, A 0.52156866, B 0.5254902, alpha 1.0, hue 0.138223, and saturation 0.71234035.
     * It can be represented as a packed float with the constant {@code -0x1.0d0b86p126F}.
     * <pre>
     * <font style='background-color: #F5B99B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5B99B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5B99B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F5B99B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F5B99B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F5B99B'>&nbsp;@&nbsp;</font><font style='background-color: #F5B99B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5B99B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5B99B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_SKIN_7 = -0x1.0d0b86p126F;
    static { NAMED.put("Light Skin 7", -0x1.0d0b86p126F); LIST.add(-0x1.0d0b86p126F); }

    /**
     * This color constant "Light Skin 8" has RGBA8888 code {@code F6C8AFFF}, L 0.80784315, A 0.5176471, B 0.52156866, alpha 1.0, hue 0.14085212, and saturation 0.75096875.
     * It can be represented as a packed float with the constant {@code -0x1.0b099cp126F}.
     * <pre>
     * <font style='background-color: #F6C8AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6C8AF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6C8AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F6C8AF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F6C8AF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F6C8AF'>&nbsp;@&nbsp;</font><font style='background-color: #F6C8AF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6C8AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6C8AF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_SKIN_8 = -0x1.0b099cp126F;
    static { NAMED.put("Light Skin 8", -0x1.0b099cp126F); LIST.add(-0x1.0b099cp126F); }

    /**
     * This color constant "Light Skin 9" has RGBA8888 code {@code F5E1D2FF}, L 0.8862745, A 0.5058824, B 0.50980395, alpha 1.0, hue 0.16398115, and saturation 0.5321653.
     * It can be represented as a packed float with the constant {@code -0x1.0503c4p126F}.
     * <pre>
     * <font style='background-color: #F5E1D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5E1D2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5E1D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F5E1D2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F5E1D2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F5E1D2'>&nbsp;@&nbsp;</font><font style='background-color: #F5E1D2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5E1D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5E1D2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_SKIN_9 = -0x1.0503c4p126F;
    static { NAMED.put("Light Skin 9", -0x1.0503c4p126F); LIST.add(-0x1.0503c4p126F); }

    /**
     * This color constant "Dark Skin 1" has RGBA8888 code {@code 573B3BFF}, L 0.28235295, A 0.5176471, B 0.5058824, alpha 1.0, hue 0.05119568, and saturation 0.21164526.
     * It can be represented as a packed float with the constant {@code -0x1.03089p126F}.
     * <pre>
     * <font style='background-color: #573B3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #573B3B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #573B3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #573B3B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #573B3B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #573B3B'>&nbsp;@&nbsp;</font><font style='background-color: #573B3B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #573B3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #573B3B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_SKIN_1 = -0x1.03089p126F;
    static { NAMED.put("Dark Skin 1", -0x1.03089p126F); LIST.add(-0x1.03089p126F); }

    /**
     * This color constant "Dark Skin 2" has RGBA8888 code {@code 73413CFF}, L 0.32941177, A 0.5294118, B 0.5137255, alpha 1.0, hue 0.06948605, and saturation 0.33235717.
     * It can be represented as a packed float with the constant {@code -0x1.070ea8p126F}.
     * <pre>
     * <font style='background-color: #73413C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73413C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73413C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #73413C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #73413C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #73413C'>&nbsp;@&nbsp;</font><font style='background-color: #73413C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73413C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73413C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_SKIN_2 = -0x1.070ea8p126F;
    static { NAMED.put("Dark Skin 2", -0x1.070ea8p126F); LIST.add(-0x1.070ea8p126F); }

    /**
     * This color constant "Dark Skin 3" has RGBA8888 code {@code 8E5555FF}, L 0.40392157, A 0.53333336, B 0.50980395, alpha 1.0, hue 0.045517046, and saturation 0.31209716.
     * It can be represented as a packed float with the constant {@code -0x1.0510cep126F}.
     * <pre>
     * <font style='background-color: #8E5555;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8E5555; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8E5555;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8E5555'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8E5555'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8E5555'>&nbsp;@&nbsp;</font><font style='background-color: #8E5555; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8E5555;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8E5555; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_SKIN_3 = -0x1.0510cep126F;
    static { NAMED.put("Dark Skin 3", -0x1.0510cep126F); LIST.add(-0x1.0510cep126F); }

    /**
     * This color constant "Pink Skin 1" has RGBA8888 code {@code AB7373FF}, L 0.5058824, A 0.5294118, B 0.50980395, alpha 1.0, hue 0.05119568, and saturation 0.24802178.
     * It can be represented as a packed float with the constant {@code -0x1.050f02p126F}.
     * <pre>
     * <font style='background-color: #AB7373;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB7373; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB7373;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB7373'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB7373'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB7373'>&nbsp;@&nbsp;</font><font style='background-color: #AB7373; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB7373;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB7373; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINK_SKIN_1 = -0x1.050f02p126F;
    static { NAMED.put("Pink Skin 1", -0x1.050f02p126F); LIST.add(-0x1.050f02p126F); }

    /**
     * This color constant "Pink Skin 2" has RGBA8888 code {@code C78F8FFF}, L 0.60784316, A 0.5294118, B 0.50980395, alpha 1.0, hue 0.05119568, and saturation 0.3691487.
     * It can be represented as a packed float with the constant {@code -0x1.050f36p126F}.
     * <pre>
     * <font style='background-color: #C78F8F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C78F8F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C78F8F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C78F8F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C78F8F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C78F8F'>&nbsp;@&nbsp;</font><font style='background-color: #C78F8F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C78F8F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C78F8F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINK_SKIN_2 = -0x1.050f36p126F;
    static { NAMED.put("Pink Skin 2", -0x1.050f36p126F); LIST.add(-0x1.050f36p126F); }

    /**
     * This color constant "Pink Skin 3" has RGBA8888 code {@code E3ABABFF}, L 0.7137255, A 0.5254902, B 0.5058824, alpha 1.0, hue 0.03609712, and saturation 0.4783566.
     * It can be represented as a packed float with the constant {@code -0x1.030d6cp126F}.
     * <pre>
     * <font style='background-color: #E3ABAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3ABAB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3ABAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E3ABAB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E3ABAB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E3ABAB'>&nbsp;@&nbsp;</font><font style='background-color: #E3ABAB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3ABAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3ABAB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINK_SKIN_3 = -0x1.030d6cp126F;
    static { NAMED.put("Pink Skin 3", -0x1.030d6cp126F); LIST.add(-0x1.030d6cp126F); }

    /**
     * This color constant "Pink Skin 4" has RGBA8888 code {@code F8D2DAFF}, L 0.8509804, A 0.5176471, B 0.49803922, alpha 1.0, hue 0.9823789, and saturation 0.64934975.
     * It can be represented as a packed float with the constant {@code -0x1.ff09b2p125F}.
     * <pre>
     * <font style='background-color: #F8D2DA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F8D2DA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F8D2DA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F8D2DA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F8D2DA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F8D2DA'>&nbsp;@&nbsp;</font><font style='background-color: #F8D2DA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F8D2DA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F8D2DA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINK_SKIN_4 = -0x1.ff09b2p125F;
    static { NAMED.put("Pink Skin 4", -0x1.ff09b2p125F); LIST.add(-0x1.ff09b2p125F); }

    /**
     * This color constant "Bronze Skin 4" has RGBA8888 code {@code E3C7ABFF}, L 0.78039217, A 0.5058824, B 0.5176471, alpha 1.0, hue 0.19880433, and saturation 0.34014416.
     * It can be represented as a packed float with the constant {@code -0x1.09038ep126F}.
     * <pre>
     * <font style='background-color: #E3C7AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3C7AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3C7AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E3C7AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E3C7AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E3C7AB'>&nbsp;@&nbsp;</font><font style='background-color: #E3C7AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3C7AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3C7AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRONZE_SKIN_4 = -0x1.09038ep126F;
    static { NAMED.put("Bronze Skin 4", -0x1.09038ep126F); LIST.add(-0x1.09038ep126F); }

    /**
     * This color constant "Bronze Skin 3" has RGBA8888 code {@code C49E73FF}, L 0.6313726, A 0.50980395, B 0.5294118, alpha 1.0, hue 0.19880433, and saturation 0.3691487.
     * It can be represented as a packed float with the constant {@code -0x1.0f0542p126F}.
     * <pre>
     * <font style='background-color: #C49E73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C49E73; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C49E73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C49E73'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C49E73'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C49E73'>&nbsp;@&nbsp;</font><font style='background-color: #C49E73; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C49E73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C49E73; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRONZE_SKIN_3 = -0x1.0f0542p126F;
    static { NAMED.put("Bronze Skin 3", -0x1.0f0542p126F); LIST.add(-0x1.0f0542p126F); }

    /**
     * This color constant "Bronze Skin 2" has RGBA8888 code {@code 8F7357FF}, L 0.46666667, A 0.50980395, B 0.52156866, alpha 1.0, hue 0.18210676, and saturation 0.3369564.
     * It can be represented as a packed float with the constant {@code -0x1.0b04eep126F}.
     * <pre>
     * <font style='background-color: #8F7357;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F7357; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F7357;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F7357'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F7357'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F7357'>&nbsp;@&nbsp;</font><font style='background-color: #8F7357; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F7357;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F7357; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRONZE_SKIN_2 = -0x1.0b04eep126F;
    static { NAMED.put("Bronze Skin 2", -0x1.0b04eep126F); LIST.add(-0x1.0b04eep126F); }

    /**
     * This color constant "Bronze Skin 1" has RGBA8888 code {@code 73573BFF}, L 0.37254903, A 0.50980395, B 0.5254902, alpha 1.0, hue 0.19157475, and saturation 0.45106485.
     * It can be represented as a packed float with the constant {@code -0x1.0d04bep126F}.
     * <pre>
     * <font style='background-color: #73573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73573B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #73573B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #73573B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #73573B'>&nbsp;@&nbsp;</font><font style='background-color: #73573B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73573B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRONZE_SKIN_1 = -0x1.0d04bep126F;
    static { NAMED.put("Bronze Skin 1", -0x1.0d04bep126F); LIST.add(-0x1.0d04bep126F); }

    /**
     * This color constant "Taupe" has RGBA8888 code {@code 3B2D1FFF}, L 0.21568628, A 0.5058824, B 0.5137255, alpha 1.0, hue 0.18556869, and saturation 0.3185683.
     * It can be represented as a packed float with the constant {@code -0x1.07026ep126F}.
     * <pre>
     * <font style='background-color: #3B2D1F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B2D1F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B2D1F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B2D1F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B2D1F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B2D1F'>&nbsp;@&nbsp;</font><font style='background-color: #3B2D1F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B2D1F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B2D1F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TAUPE = -0x1.07026ep126F;
    static { NAMED.put("Taupe", -0x1.07026ep126F); LIST.add(-0x1.07026ep126F); }

    /**
     * This color constant "Drab Green" has RGBA8888 code {@code 414123FF}, L 0.27058825, A 0.49019608, B 0.52156866, alpha 1.0, hue 0.31789324, and saturation 0.46655506.
     * It can be represented as a packed float with the constant {@code -0x1.0afa8ap126F}.
     * <pre>
     * <font style='background-color: #414123;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #414123; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #414123;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #414123'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #414123'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #414123'>&nbsp;@&nbsp;</font><font style='background-color: #414123; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #414123;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #414123; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_GREEN = -0x1.0afa8ap126F;
    static { NAMED.put("Drab Green", -0x1.0afa8ap126F); LIST.add(-0x1.0afa8ap126F); }

    /**
     * This color constant "Lizard Scales" has RGBA8888 code {@code 73733BFF}, L 0.43529412, A 0.4862745, B 0.5372549, alpha 1.0, hue 0.30616736, and saturation 0.59787834.
     * It can be represented as a packed float with the constant {@code -0x1.12f8dep126F}.
     * <pre>
     * <font style='background-color: #73733B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73733B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73733B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #73733B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #73733B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #73733B'>&nbsp;@&nbsp;</font><font style='background-color: #73733B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73733B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73733B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIZARD_SCALES = -0x1.12f8dep126F;
    static { NAMED.put("Lizard Scales", -0x1.12f8dep126F); LIST.add(-0x1.12f8dep126F); }

    /**
     * This color constant "Cricket" has RGBA8888 code {@code 8F8F57FF}, L 0.53333336, A 0.4862745, B 0.53333336, alpha 1.0, hue 0.31215638, and saturation 0.4732532.
     * It can be represented as a packed float with the constant {@code -0x1.10f91p126F}.
     * <pre>
     * <font style='background-color: #8F8F57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F8F57; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F8F57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F8F57'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F8F57'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F8F57'>&nbsp;@&nbsp;</font><font style='background-color: #8F8F57; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F8F57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F8F57; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CRICKET = -0x1.10f91p126F;
    static { NAMED.put("Cricket", -0x1.10f91p126F); LIST.add(-0x1.10f91p126F); }

    /**
     * This color constant "Olive Oil" has RGBA8888 code {@code A2A255FF}, L 0.59607846, A 0.48235294, B 0.54509807, alpha 1.0, hue 0.30935085, and saturation 0.59035796.
     * It can be represented as a packed float with the constant {@code -0x1.16f73p126F}.
     * <pre>
     * <font style='background-color: #A2A255;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2A255; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2A255;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A2A255'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A2A255'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A2A255'>&nbsp;@&nbsp;</font><font style='background-color: #A2A255; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2A255;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2A255; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float OLIVE_OIL = -0x1.16f73p126F;
    static { NAMED.put("Olive Oil", -0x1.16f73p126F); LIST.add(-0x1.16f73p126F); }

    /**
     * This color constant "Dun" has RGBA8888 code {@code B5B572FF}, L 0.67058825, A 0.4862745, B 0.5372549, alpha 1.0, hue 0.30616736, and saturation 0.4517303.
     * It can be represented as a packed float with the constant {@code -0x1.12f956p126F}.
     * <pre>
     * <font style='background-color: #B5B572;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B5B572; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B5B572;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B5B572'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B5B572'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B5B572'>&nbsp;@&nbsp;</font><font style='background-color: #B5B572; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B5B572;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B5B572; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DUN = -0x1.12f956p126F;
    static { NAMED.put("Dun", -0x1.12f956p126F); LIST.add(-0x1.12f956p126F); }

    /**
     * This color constant "Corn Silk" has RGBA8888 code {@code C7C78FFF}, L 0.74509805, A 0.4862745, B 0.5294118, alpha 1.0, hue 0.31948605, and saturation 0.33913997.
     * It can be represented as a packed float with the constant {@code -0x1.0ef97cp126F}.
     * <pre>
     * <font style='background-color: #C7C78F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7C78F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7C78F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C7C78F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C7C78F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C7C78F'>&nbsp;@&nbsp;</font><font style='background-color: #C7C78F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7C78F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7C78F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CORN_SILK = -0x1.0ef97cp126F;
    static { NAMED.put("Corn Silk", -0x1.0ef97cp126F); LIST.add(-0x1.0ef97cp126F); }

    /**
     * This color constant "Tan" has RGBA8888 code {@code DADAABFF}, L 0.8235294, A 0.49019608, B 0.5254902, alpha 1.0, hue 0.30842525, and saturation 0.2741767.
     * It can be represented as a packed float with the constant {@code -0x1.0cfba4p126F}.
     * <pre>
     * <font style='background-color: #DADAAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DADAAB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DADAAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DADAAB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DADAAB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DADAAB'>&nbsp;@&nbsp;</font><font style='background-color: #DADAAB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DADAAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DADAAB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TAN = -0x1.0cfba4p126F;
    static { NAMED.put("Tan", -0x1.0cfba4p126F); LIST.add(-0x1.0cfba4p126F); }

    /**
     * This color constant "Straw" has RGBA8888 code {@code EDEDC7FF}, L 0.90588236, A 0.49019608, B 0.5176471, alpha 1.0, hue 0.33071172, and saturation 0.18133344.
     * It can be represented as a packed float with the constant {@code -0x1.08fbcep126F}.
     * <pre>
     * <font style='background-color: #EDEDC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDEDC7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDEDC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EDEDC7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EDEDC7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EDEDC7'>&nbsp;@&nbsp;</font><font style='background-color: #EDEDC7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDEDC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDEDC7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRAW = -0x1.08fbcep126F;
    static { NAMED.put("Straw", -0x1.08fbcep126F); LIST.add(-0x1.08fbcep126F); }

    /**
     * This color constant "Honeydew" has RGBA8888 code {@code C7E3ABFF}, L 0.827451, A 0.4745098, B 0.5254902, alpha 1.0, hue 0.375, and saturation 0.2754757.
     * It can be represented as a packed float with the constant {@code -0x1.0cf3a6p126F}.
     * <pre>
     * <font style='background-color: #C7E3AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7E3AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7E3AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C7E3AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C7E3AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C7E3AB'>&nbsp;@&nbsp;</font><font style='background-color: #C7E3AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7E3AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7E3AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float HONEYDEW = -0x1.0cf3a6p126F;
    static { NAMED.put("Honeydew", -0x1.0cf3a6p126F); LIST.add(-0x1.0cf3a6p126F); }

    /**
     * This color constant "Tarnish" has RGBA8888 code {@code ABC78FFF}, L 0.7176471, A 0.4745098, B 0.5294118, alpha 1.0, hue 0.3636593, and saturation 0.3435737.
     * It can be represented as a packed float with the constant {@code -0x1.0ef36ep126F}.
     * <pre>
     * <font style='background-color: #ABC78F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABC78F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABC78F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ABC78F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ABC78F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ABC78F'>&nbsp;@&nbsp;</font><font style='background-color: #ABC78F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABC78F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABC78F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TARNISH = -0x1.0ef36ep126F;
    static { NAMED.put("Tarnish", -0x1.0ef36ep126F); LIST.add(-0x1.0ef36ep126F); }

    /**
     * This color constant "Pea Soup" has RGBA8888 code {@code 8EBE55FF}, L 0.654902, A 0.45490196, B 0.5529412, alpha 1.0, hue 0.36230367, and saturation 0.6718375.
     * It can be represented as a packed float with the constant {@code -0x1.1ae94ep126F}.
     * <pre>
     * <font style='background-color: #8EBE55;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8EBE55; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8EBE55;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8EBE55'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8EBE55'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8EBE55'>&nbsp;@&nbsp;</font><font style='background-color: #8EBE55; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8EBE55;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8EBE55; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PEA_SOUP = -0x1.1ae94ep126F;
    static { NAMED.put("Pea Soup", -0x1.1ae94ep126F); LIST.add(-0x1.1ae94ep126F); }

    /**
     * This color constant "Marsh" has RGBA8888 code {@code 738F57FF}, L 0.50980395, A 0.47058824, B 0.5294118, alpha 1.0, hue 0.375, and saturation 0.42592785.
     * It can be represented as a packed float with the constant {@code -0x1.0ef104p126F}.
     * <pre>
     * <font style='background-color: #738F57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #738F57; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #738F57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #738F57'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #738F57'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #738F57'>&nbsp;@&nbsp;</font><font style='background-color: #738F57; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #738F57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #738F57; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MARSH = -0x1.0ef104p126F;
    static { NAMED.put("Marsh", -0x1.0ef104p126F); LIST.add(-0x1.0ef104p126F); }

    /**
     * This color constant "Asparagus" has RGBA8888 code {@code 587D3EFF}, L 0.4392157, A 0.4627451, B 0.5372549, alpha 1.0, hue 0.375, and saturation 0.59945405.
     * It can be represented as a packed float with the constant {@code -0x1.12ecep126F}.
     * <pre>
     * <font style='background-color: #587D3E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #587D3E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #587D3E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #587D3E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #587D3E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #587D3E'>&nbsp;@&nbsp;</font><font style='background-color: #587D3E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #587D3E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #587D3E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ASPARAGUS = -0x1.12ecep126F;
    static { NAMED.put("Asparagus", -0x1.12ecep126F); LIST.add(-0x1.12ecep126F); }

    /**
     * This color constant "Peat Bog" has RGBA8888 code {@code 465032FF}, L 0.30980393, A 0.48235294, B 0.52156866, alpha 1.0, hue 0.35914788, and saturation 0.4458877.
     * It can be represented as a packed float with the constant {@code -0x1.0af69ep126F}.
     * <pre>
     * <font style='background-color: #465032;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #465032; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #465032;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #465032'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #465032'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #465032'>&nbsp;@&nbsp;</font><font style='background-color: #465032; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #465032;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #465032; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PEAT_BOG = -0x1.0af69ep126F;
    static { NAMED.put("Peat Bog", -0x1.0af69ep126F); LIST.add(-0x1.0af69ep126F); }

    /**
     * This color constant "Deep Jungle" has RGBA8888 code {@code 191E0FFF}, L 0.14117648, A 0.4862745, B 0.5137255, alpha 1.0, hue 0.375, and saturation 0.2923034.
     * It can be represented as a packed float with the constant {@code -0x1.06f848p126F}.
     * <pre>
     * <font style='background-color: #191E0F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #191E0F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #191E0F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #191E0F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #191E0F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #191E0F'>&nbsp;@&nbsp;</font><font style='background-color: #191E0F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #191E0F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #191E0F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_JUNGLE = -0x1.06f848p126F;
    static { NAMED.put("Deep Jungle", -0x1.06f848p126F); LIST.add(-0x1.06f848p126F); }

    /**
     * This color constant "Pine Green" has RGBA8888 code {@code 235037FF}, L 0.2901961, A 0.4627451, B 0.5137255, alpha 1.0, hue 0.44383267, and saturation 0.67759544.
     * It can be represented as a packed float with the constant {@code -0x1.06ec94p126F}.
     * <pre>
     * <font style='background-color: #235037;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #235037; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #235037;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #235037'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #235037'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #235037'>&nbsp;@&nbsp;</font><font style='background-color: #235037; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #235037;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #235037; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINE_GREEN = -0x1.06ec94p126F;
    static { NAMED.put("Pine Green", -0x1.06ec94p126F); LIST.add(-0x1.06ec94p126F); }

    /**
     * This color constant "Olive Green" has RGBA8888 code {@code 3B573BFF}, L 0.32156864, A 0.4745098, B 0.5137255, alpha 1.0, hue 0.42138404, and saturation 0.40061417.
     * It can be represented as a packed float with the constant {@code -0x1.06f2a4p126F}.
     * <pre>
     * <font style='background-color: #3B573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B573B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B573B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B573B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B573B'>&nbsp;@&nbsp;</font><font style='background-color: #3B573B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B573B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float OLIVE_GREEN = -0x1.06f2a4p126F;
    static { NAMED.put("Olive Green", -0x1.06f2a4p126F); LIST.add(-0x1.06f2a4p126F); }

    /**
     * This color constant "Gray Green" has RGBA8888 code {@code 506450FF}, L 0.3764706, A 0.48235294, B 0.50980395, alpha 1.0, hue 0.41928825, and saturation 0.25209773.
     * It can be represented as a packed float with the constant {@code -0x1.04f6cp126F}.
     * <pre>
     * <font style='background-color: #506450;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #506450; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #506450;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #506450'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #506450'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #506450'>&nbsp;@&nbsp;</font><font style='background-color: #506450; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #506450;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #506450; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_GREEN = -0x1.04f6cp126F;
    static { NAMED.put("Gray Green", -0x1.04f6cp126F); LIST.add(-0x1.04f6cp126F); }

    /**
     * This color constant "Maidenhair Fern" has RGBA8888 code {@code 3B7349FF}, L 0.39607844, A 0.45882353, B 0.52156866, alpha 1.0, hue 0.42320445, and saturation 0.5804758.
     * It can be represented as a packed float with the constant {@code -0x1.0aeacap126F}.
     * <pre>
     * <font style='background-color: #3B7349;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B7349; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B7349;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B7349'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B7349'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B7349'>&nbsp;@&nbsp;</font><font style='background-color: #3B7349; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B7349;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B7349; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MAIDENHAIR_FERN = -0x1.0aeacap126F;
    static { NAMED.put("Maidenhair Fern", -0x1.0aeacap126F); LIST.add(-0x1.0aeacap126F); }

    /**
     * This color constant "Kelly Green" has RGBA8888 code {@code 578F57FF}, L 0.49019608, A 0.45490196, B 0.5294118, alpha 1.0, hue 0.40801254, and saturation 0.51049525.
     * It can be represented as a packed float with the constant {@code -0x1.0ee8fap126F}.
     * <pre>
     * <font style='background-color: #578F57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #578F57; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #578F57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #578F57'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #578F57'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #578F57'>&nbsp;@&nbsp;</font><font style='background-color: #578F57; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #578F57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #578F57; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float KELLY_GREEN = -0x1.0ee8fap126F;
    static { NAMED.put("Kelly Green", -0x1.0ee8fap126F); LIST.add(-0x1.0ee8fap126F); }

    /**
     * This color constant "Dusty Green" has RGBA8888 code {@code 73AB73FF}, L 0.5882353, A 0.45882353, B 0.5254902, alpha 1.0, hue 0.41176948, and saturation 0.4275006.
     * It can be represented as a packed float with the constant {@code -0x1.0ceb2cp126F}.
     * <pre>
     * <font style='background-color: #73AB73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73AB73; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73AB73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #73AB73'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #73AB73'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #73AB73'>&nbsp;@&nbsp;</font><font style='background-color: #73AB73; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73AB73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73AB73; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DUSTY_GREEN = -0x1.0ceb2cp126F;
    static { NAMED.put("Dusty Green", -0x1.0ceb2cp126F); LIST.add(-0x1.0ceb2cp126F); }

    /**
     * This color constant "Garter Snake" has RGBA8888 code {@code 64C082FF}, L 0.6392157, A 0.44313726, B 0.5254902, alpha 1.0, hue 0.43293634, and saturation 0.61356014.
     * It can be represented as a packed float with the constant {@code -0x1.0ce346p126F}.
     * <pre>
     * <font style='background-color: #64C082;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #64C082; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #64C082;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #64C082'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #64C082'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #64C082'>&nbsp;@&nbsp;</font><font style='background-color: #64C082; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #64C082;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #64C082; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GARTER_SNAKE = -0x1.0ce346p126F;
    static { NAMED.put("Garter Snake", -0x1.0ce346p126F); LIST.add(-0x1.0ce346p126F); }

    /**
     * This color constant "Silver Green" has RGBA8888 code {@code 8FC78FFF}, L 0.69411767, A 0.45882353, B 0.5254902, alpha 1.0, hue 0.41176948, and saturation 0.3874224.
     * It can be represented as a packed float with the constant {@code -0x1.0ceb62p126F}.
     * <pre>
     * <font style='background-color: #8FC78F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8FC78F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8FC78F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8FC78F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8FC78F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8FC78F'>&nbsp;@&nbsp;</font><font style='background-color: #8FC78F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8FC78F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8FC78F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_GREEN = -0x1.0ceb62p126F;
    static { NAMED.put("Silver Green", -0x1.0ceb62p126F); LIST.add(-0x1.0ceb62p126F); }

    /**
     * This color constant "Pistachio" has RGBA8888 code {@code A2D8A2FF}, L 0.7607843, A 0.4627451, B 0.52156866, alpha 1.0, hue 0.41646945, and saturation 0.3390861.
     * It can be represented as a packed float with the constant {@code -0x1.0aed84p126F}.
     * <pre>
     * <font style='background-color: #A2D8A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2D8A2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2D8A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A2D8A2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A2D8A2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A2D8A2'>&nbsp;@&nbsp;</font><font style='background-color: #A2D8A2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2D8A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2D8A2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PISTACHIO = -0x1.0aed84p126F;
    static { NAMED.put("Pistachio", -0x1.0aed84p126F); LIST.add(-0x1.0aed84p126F); }

    /**
     * This color constant "Angel Wing" has RGBA8888 code {@code E1F8FAFF}, L 0.94509804, A 0.4862745, B 0.49411765, alpha 1.0, hue 0.5644313, and saturation 0.54611707.
     * It can be represented as a packed float with the constant {@code -0x1.fcf9e2p125F}.
     * <pre>
     * <font style='background-color: #E1F8FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1F8FA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1F8FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E1F8FA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E1F8FA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E1F8FA'>&nbsp;@&nbsp;</font><font style='background-color: #E1F8FA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1F8FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1F8FA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ANGEL_WING = -0x1.fcf9e2p125F;
    static { NAMED.put("Angel Wing", -0x1.fcf9e2p125F); LIST.add(-0x1.fcf9e2p125F); }

    /**
     * This color constant "Sage Green" has RGBA8888 code {@code B4EECAFF}, L 0.85490197, A 0.46666667, B 0.50980395, alpha 1.0, hue 0.45448294, and saturation 0.44473845.
     * It can be represented as a packed float with the constant {@code -0x1.04efb4p126F}.
     * <pre>
     * <font style='background-color: #B4EECA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4EECA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4EECA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B4EECA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B4EECA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B4EECA'>&nbsp;@&nbsp;</font><font style='background-color: #B4EECA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4EECA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4EECA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SAGE_GREEN = -0x1.04efb4p126F;
    static { NAMED.put("Sage Green", -0x1.04efb4p126F); LIST.add(-0x1.04efb4p126F); }

    /**
     * This color constant "Dried Sage" has RGBA8888 code {@code ABE3C5FF}, L 0.8117647, A 0.46666667, B 0.50980395, alpha 1.0, hue 0.45448294, and saturation 0.3421065.
     * It can be represented as a packed float with the constant {@code -0x1.04ef9ep126F}.
     * <pre>
     * <font style='background-color: #ABE3C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABE3C5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABE3C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ABE3C5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ABE3C5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ABE3C5'>&nbsp;@&nbsp;</font><font style='background-color: #ABE3C5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABE3C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABE3C5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRIED_SAGE = -0x1.04ef9ep126F;
    static { NAMED.put("Dried Sage", -0x1.04ef9ep126F); LIST.add(-0x1.04ef9ep126F); }

    /**
     * This color constant "Artichoke" has RGBA8888 code {@code 87B48EFF}, L 0.63529414, A 0.46666667, B 0.5137255, alpha 1.0, hue 0.4378436, and saturation 0.37667093.
     * It can be represented as a packed float with the constant {@code -0x1.06ef44p126F}.
     * <pre>
     * <font style='background-color: #87B48E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #87B48E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #87B48E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #87B48E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #87B48E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #87B48E'>&nbsp;@&nbsp;</font><font style='background-color: #87B48E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #87B48E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #87B48E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ARTICHOKE = -0x1.06ef44p126F;
    static { NAMED.put("Artichoke", -0x1.06ef44p126F); LIST.add(-0x1.06ef44p126F); }

    /**
     * This color constant "Viridian" has RGBA8888 code {@code 507D5FFF}, L 0.4392157, A 0.46666667, B 0.5137255, alpha 1.0, hue 0.4378436, and saturation 0.4732532.
     * It can be represented as a packed float with the constant {@code -0x1.06eeep126F}.
     * <pre>
     * <font style='background-color: #507D5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #507D5F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #507D5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #507D5F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #507D5F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #507D5F'>&nbsp;@&nbsp;</font><font style='background-color: #507D5F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #507D5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #507D5F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIRIDIAN = -0x1.06eeep126F;
    static { NAMED.put("Viridian", -0x1.06eeep126F); LIST.add(-0x1.06eeep126F); }

    /**
     * This color constant "Floral Foam" has RGBA8888 code {@code 0F6946FF}, L 0.3529412, A 0.44705883, B 0.5137255, alpha 1.0, hue 0.4596309, and saturation 0.90329146.
     * It can be represented as a packed float with the constant {@code -0x1.06e4b4p126F}.
     * <pre>
     * <font style='background-color: #0F6946;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F6946; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F6946;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0F6946'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0F6946'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0F6946'>&nbsp;@&nbsp;</font><font style='background-color: #0F6946; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F6946;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F6946; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FLORAL_FOAM = -0x1.06e4b4p126F;
    static { NAMED.put("Floral Foam", -0x1.06e4b4p126F); LIST.add(-0x1.06e4b4p126F); }

    /**
     * This color constant "Hunter Green" has RGBA8888 code {@code 1E2D23FF}, L 0.19215687, A 0.48235294, B 0.5058824, alpha 1.0, hue 0.44880432, and saturation 0.39683485.
     * It can be represented as a packed float with the constant {@code -0x1.02f662p126F}.
     * <pre>
     * <font style='background-color: #1E2D23;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1E2D23; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1E2D23;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1E2D23'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1E2D23'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1E2D23'>&nbsp;@&nbsp;</font><font style='background-color: #1E2D23; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1E2D23;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1E2D23; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float HUNTER_GREEN = -0x1.02f662p126F;
    static { NAMED.put("Hunter Green", -0x1.02f662p126F); LIST.add(-0x1.02f662p126F); }

    /**
     * This color constant "Dark Teal" has RGBA8888 code {@code 234146FF}, L 0.25882354, A 0.47843137, B 0.4862745, alpha 1.0, hue 0.59020853, and saturation 0.59497887.
     * It can be represented as a packed float with the constant {@code -0x1.f8f484p125F}.
     * <pre>
     * <font style='background-color: #234146;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #234146; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #234146;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #234146'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #234146'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #234146'>&nbsp;@&nbsp;</font><font style='background-color: #234146; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #234146;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #234146; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_TEAL = -0x1.f8f484p125F;
    static { NAMED.put("Dark Teal", -0x1.f8f484p125F); LIST.add(-0x1.f8f484p125F); }

    /**
     * This color constant "Kyanite" has RGBA8888 code {@code 3B7373FF}, L 0.40784314, A 0.46666667, B 0.49019608, alpha 1.0, hue 0.545517, and saturation 0.63534063.
     * It can be represented as a packed float with the constant {@code -0x1.faeedp125F}.
     * <pre>
     * <font style='background-color: #3B7373;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B7373; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B7373;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B7373'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B7373'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B7373'>&nbsp;@&nbsp;</font><font style='background-color: #3B7373; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B7373;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B7373; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float KYANITE = -0x1.faeedp125F;
    static { NAMED.put("Kyanite", -0x1.faeedp125F); LIST.add(-0x1.faeedp125F); }

    /**
     * This color constant "Spearmint" has RGBA8888 code {@code 64ABABFF}, L 0.59607846, A 0.4627451, B 0.49019608, alpha 1.0, hue 0.5409493, and saturation 0.5635409.
     * It can be represented as a packed float with the constant {@code -0x1.faed3p125F}.
     * <pre>
     * <font style='background-color: #64ABAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #64ABAB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #64ABAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #64ABAB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #64ABAB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #64ABAB'>&nbsp;@&nbsp;</font><font style='background-color: #64ABAB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #64ABAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #64ABAB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SPEARMINT = -0x1.faed3p125F;
    static { NAMED.put("Spearmint", -0x1.faed3p125F); LIST.add(-0x1.faed3p125F); }

    /**
     * This color constant "Amazonite" has RGBA8888 code {@code 8FC7C7FF}, L 0.70980394, A 0.47058824, B 0.49019608, alpha 1.0, hue 0.5511957, and saturation 0.41772088.
     * It can be represented as a packed float with the constant {@code -0x1.faf16ap125F}.
     * <pre>
     * <font style='background-color: #8FC7C7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8FC7C7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8FC7C7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8FC7C7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8FC7C7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8FC7C7'>&nbsp;@&nbsp;</font><font style='background-color: #8FC7C7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8FC7C7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8FC7C7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float AMAZONITE = -0x1.faf16ap125F;
    static { NAMED.put("Amazonite", -0x1.faf16ap125F); LIST.add(-0x1.faf16ap125F); }

    /**
     * This color constant "Pastel Sky" has RGBA8888 code {@code ABE3E3FF}, L 0.8235294, A 0.4745098, B 0.49019608, alpha 1.0, hue 0.55842525, and saturation 0.34104905.
     * It can be represented as a packed float with the constant {@code -0x1.faf3a4p125F}.
     * <pre>
     * <font style='background-color: #ABE3E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABE3E3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABE3E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ABE3E3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ABE3E3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ABE3E3'>&nbsp;@&nbsp;</font><font style='background-color: #ABE3E3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABE3E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABE3E3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PASTEL_SKY = -0x1.faf3a4p125F;
    static { NAMED.put("Pastel Sky", -0x1.faf3a4p125F); LIST.add(-0x1.faf3a4p125F); }

    /**
     * This color constant "Aquamarine" has RGBA8888 code {@code C7F1F1FF}, L 0.89411765, A 0.47843137, B 0.49411765, alpha 1.0, hue 0.54236877, and saturation 0.39470577.
     * It can be represented as a packed float with the constant {@code -0x1.fcf5c8p125F}.
     * <pre>
     * <font style='background-color: #C7F1F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7F1F1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7F1F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C7F1F1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C7F1F1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C7F1F1'>&nbsp;@&nbsp;</font><font style='background-color: #C7F1F1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7F1F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7F1F1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float AQUAMARINE = -0x1.fcf5c8p125F;
    static { NAMED.put("Aquamarine", -0x1.fcf5c8p125F); LIST.add(-0x1.fcf5c8p125F); }

    /**
     * This color constant "Dust Bunny" has RGBA8888 code {@code BED2F0FF}, L 0.8, A 0.49411765, B 0.47843137, alpha 1.0, hue 0.70763123, and saturation 0.60244566.
     * It can be represented as a packed float with the constant {@code -0x1.f4fd98p125F}.
     * <pre>
     * <font style='background-color: #BED2F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BED2F0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BED2F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BED2F0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BED2F0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BED2F0'>&nbsp;@&nbsp;</font><font style='background-color: #BED2F0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BED2F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BED2F0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DUST_BUNNY = -0x1.f4fd98p125F;
    static { NAMED.put("Dust Bunny", -0x1.f4fd98p125F); LIST.add(-0x1.f4fd98p125F); }

    /**
     * This color constant "Patina" has RGBA8888 code {@code ABC7E3FF}, L 0.74509805, A 0.49019608, B 0.47843137, alpha 1.0, hue 0.68210673, and saturation 0.48521724.
     * It can be represented as a packed float with the constant {@code -0x1.f4fb7cp125F}.
     * <pre>
     * <font style='background-color: #ABC7E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABC7E3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABC7E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ABC7E3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ABC7E3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ABC7E3'>&nbsp;@&nbsp;</font><font style='background-color: #ABC7E3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABC7E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABC7E3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PATINA = -0x1.f4fb7cp125F;
    static { NAMED.put("Patina", -0x1.f4fb7cp125F); LIST.add(-0x1.f4fb7cp125F); }

    /**
     * This color constant "Chipped Granite" has RGBA8888 code {@code A8B9DCFF}, L 0.7058824, A 0.49411765, B 0.4745098, alpha 1.0, hue 0.7139029, and saturation 0.4783566.
     * It can be represented as a packed float with the constant {@code -0x1.f2fd68p125F}.
     * <pre>
     * <font style='background-color: #A8B9DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A8B9DC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A8B9DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A8B9DC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A8B9DC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A8B9DC'>&nbsp;@&nbsp;</font><font style='background-color: #A8B9DC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A8B9DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A8B9DC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CHIPPED_GRANITE = -0x1.f2fd68p125F;
    static { NAMED.put("Chipped Granite", -0x1.f2fd68p125F); LIST.add(-0x1.f2fd68p125F); }

    /**
     * This color constant "Blue Smoke" has RGBA8888 code {@code 8FABC7FF}, L 0.6392157, A 0.49019608, B 0.4745098, alpha 1.0, hue 0.69157475, and saturation 0.3779192.
     * It can be represented as a packed float with the constant {@code -0x1.f2fb46p125F}.
     * <pre>
     * <font style='background-color: #8FABC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8FABC7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8FABC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8FABC7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8FABC7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8FABC7'>&nbsp;@&nbsp;</font><font style='background-color: #8FABC7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8FABC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8FABC7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUE_SMOKE = -0x1.f2fb46p125F;
    static { NAMED.put("Blue Smoke", -0x1.f2fb46p125F); LIST.add(-0x1.f2fb46p125F); }

    /**
     * This color constant "Air Force Blue" has RGBA8888 code {@code 578FC7FF}, L 0.5294118, A 0.47843137, B 0.4509804, alpha 1.0, hue 0.6840373, and saturation 0.5712523.
     * It can be represented as a packed float with the constant {@code -0x1.e6f50ep125F}.
     * <pre>
     * <font style='background-color: #578FC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #578FC7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #578FC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #578FC7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #578FC7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #578FC7'>&nbsp;@&nbsp;</font><font style='background-color: #578FC7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #578FC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #578FC7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float AIR_FORCE_BLUE = -0x1.e6f50ep125F;
    static { NAMED.put("Air Force Blue", -0x1.e6f50ep125F); LIST.add(-0x1.e6f50ep125F); }

    /**
     * This color constant "Cold Iron" has RGBA8888 code {@code 57738FFF}, L 0.43529412, A 0.4862745, B 0.4745098, alpha 1.0, hue 0.67138404, and saturation 0.3900717.
     * It can be represented as a packed float with the constant {@code -0x1.f2f8dep125F}.
     * <pre>
     * <font style='background-color: #57738F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57738F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57738F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #57738F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #57738F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #57738F'>&nbsp;@&nbsp;</font><font style='background-color: #57738F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57738F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57738F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float COLD_IRON = -0x1.f2f8dep125F;
    static { NAMED.put("Cold Iron", -0x1.f2f8dep125F); LIST.add(-0x1.f2f8dep125F); }

    /**
     * This color constant "Dreary Blue" has RGBA8888 code {@code 3B5773FF}, L 0.34117648, A 0.4862745, B 0.47058824, alpha 1.0, hue 0.680514, and saturation 0.4616072.
     * It can be represented as a packed float with the constant {@code -0x1.f0f8aep125F}.
     * <pre>
     * <font style='background-color: #3B5773;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B5773; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B5773;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B5773'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B5773'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B5773'>&nbsp;@&nbsp;</font><font style='background-color: #3B5773; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B5773;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B5773; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DREARY_BLUE = -0x1.f0f8aep125F;
    static { NAMED.put("Dreary Blue", -0x1.f0f8aep125F); LIST.add(-0x1.f0f8aep125F); }

    /**
     * This color constant "Murk" has RGBA8888 code {@code 0F192DFF}, L 0.13333334, A 0.49411765, B 0.47058824, alpha 1.0, hue 0.71857655, and saturation 0.30111858.
     * It can be represented as a packed float with the constant {@code -0x1.f0fc44p125F}.
     * <pre>
     * <font style='background-color: #0F192D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F192D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F192D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0F192D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0F192D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0F192D'>&nbsp;@&nbsp;</font><font style='background-color: #0F192D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F192D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F192D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MURK = -0x1.f0fc44p125F;
    static { NAMED.put("Murk", -0x1.f0fc44p125F); LIST.add(-0x1.f0fc44p125F); }

    /**
     * This color constant "Ninja" has RGBA8888 code {@code 1F1F3BFF}, L 0.16862746, A 0.5058824, B 0.46666667, alpha 1.0, hue 0.7778107, and saturation 0.35368115.
     * It can be represented as a packed float with the constant {@code -0x1.ef0256p125F}.
     * <pre>
     * <font style='background-color: #1F1F3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1F1F3B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1F1F3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1F1F3B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1F1F3B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1F1F3B'>&nbsp;@&nbsp;</font><font style='background-color: #1F1F3B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1F1F3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1F1F3B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float NINJA = -0x1.ef0256p125F;
    static { NAMED.put("Ninja", -0x1.ef0256p125F); LIST.add(-0x1.ef0256p125F); }

    /**
     * This color constant "Watercolor Black" has RGBA8888 code {@code 3B3B57FF}, L 0.26666668, A 0.5058824, B 0.4745098, alpha 1.0, hue 0.7860971, and saturation 0.20928101.
     * It can be represented as a packed float with the constant {@code -0x1.f30288p125F}.
     * <pre>
     * <font style='background-color: #3B3B57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B3B57; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B3B57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B3B57'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B3B57'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B3B57'>&nbsp;@&nbsp;</font><font style='background-color: #3B3B57; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B3B57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B3B57; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WATERCOLOR_BLACK = -0x1.f30288p125F;
    static { NAMED.put("Watercolor Black", -0x1.f30288p125F); LIST.add(-0x1.f30288p125F); }

    /**
     * This color constant "Iolite" has RGBA8888 code {@code 494973FF}, L 0.32156864, A 0.5058824, B 0.4627451, alpha 1.0, hue 0.7749365, and saturation 0.25747758.
     * It can be represented as a packed float with the constant {@code -0x1.ed02a4p125F}.
     * <pre>
     * <font style='background-color: #494973;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #494973; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #494973;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #494973'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #494973'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #494973'>&nbsp;@&nbsp;</font><font style='background-color: #494973; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #494973;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #494973; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float IOLITE = -0x1.ed02a4p125F;
    static { NAMED.put("Iolite", -0x1.ed02a4p125F); LIST.add(-0x1.ed02a4p125F); }

    /**
     * This color constant "Boysenberry" has RGBA8888 code {@code 57578FFF}, L 0.3764706, A 0.50980395, B 0.45490196, alpha 1.0, hue 0.7840724, and saturation 0.3150601.
     * It can be represented as a packed float with the constant {@code -0x1.e904cp125F}.
     * <pre>
     * <font style='background-color: #57578F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57578F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57578F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #57578F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #57578F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #57578F'>&nbsp;@&nbsp;</font><font style='background-color: #57578F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57578F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57578F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOYSENBERRY = -0x1.e904cp125F;
    static { NAMED.put("Boysenberry", -0x1.e904cp125F); LIST.add(-0x1.e904cp125F); }

    /**
     * This color constant "Watercolor Gray" has RGBA8888 code {@code 736EAAFF}, L 0.45882353, A 0.50980395, B 0.45490196, alpha 1.0, hue 0.7840724, and saturation 0.38736898.
     * It can be represented as a packed float with the constant {@code -0x1.e904eap125F}.
     * <pre>
     * <font style='background-color: #736EAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #736EAA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #736EAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #736EAA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #736EAA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #736EAA'>&nbsp;@&nbsp;</font><font style='background-color: #736EAA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #736EAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #736EAA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WATERCOLOR_GRAY = -0x1.e904eap125F;
    static { NAMED.put("Watercolor Gray", -0x1.e904eap125F); LIST.add(-0x1.e904eap125F); }

    /**
     * This color constant "Blue Steel" has RGBA8888 code {@code 7676CAFF}, L 0.49411765, A 0.50980395, B 0.4392157, alpha 1.0, hue 0.77546334, and saturation 0.5731596.
     * It can be represented as a packed float with the constant {@code -0x1.e104fcp125F}.
     * <pre>
     * <font style='background-color: #7676CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7676CA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7676CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7676CA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7676CA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7676CA'>&nbsp;@&nbsp;</font><font style='background-color: #7676CA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7676CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7676CA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUE_STEEL = -0x1.e104fcp125F;
    static { NAMED.put("Blue Steel", -0x1.e104fcp125F); LIST.add(-0x1.e104fcp125F); }

    /**
     * This color constant "Twilight Cloud" has RGBA8888 code {@code 8F8FC7FF}, L 0.57254905, A 0.5058824, B 0.45882353, alpha 1.0, hue 0.77259654, and saturation 0.49526498.
     * It can be represented as a packed float with the constant {@code -0x1.eb0324p125F}.
     * <pre>
     * <font style='background-color: #8F8FC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F8FC7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F8FC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F8FC7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F8FC7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F8FC7'>&nbsp;@&nbsp;</font><font style='background-color: #8F8FC7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F8FC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F8FC7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TWILIGHT_CLOUD = -0x1.eb0324p125F;
    static { NAMED.put("Twilight Cloud", -0x1.eb0324p125F); LIST.add(-0x1.eb0324p125F); }

    /**
     * This color constant "Smog" has RGBA8888 code {@code ABABE3FF}, L 0.6745098, A 0.5058824, B 0.4627451, alpha 1.0, hue 0.7749365, and saturation 0.62292963.
     * It can be represented as a packed float with the constant {@code -0x1.ed0358p125F}.
     * <pre>
     * <font style='background-color: #ABABE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABABE3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABABE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ABABE3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ABABE3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ABABE3'>&nbsp;@&nbsp;</font><font style='background-color: #ABABE3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABABE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABABE3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SMOG = -0x1.ed0358p125F;
    static { NAMED.put("Smog", -0x1.ed0358p125F); LIST.add(-0x1.ed0358p125F); }

    /**
     * This color constant "Tropic Mist" has RGBA8888 code {@code D0DAF8FF}, L 0.84313726, A 0.49803922, B 0.47843137, alpha 1.0, hue 0.735567, and saturation 0.7392451.
     * It can be represented as a packed float with the constant {@code -0x1.f4ffaep125F}.
     * <pre>
     * <font style='background-color: #D0DAF8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0DAF8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0DAF8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D0DAF8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D0DAF8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D0DAF8'>&nbsp;@&nbsp;</font><font style='background-color: #D0DAF8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0DAF8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0DAF8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TROPIC_MIST = -0x1.f4ffaep125F;
    static { NAMED.put("Tropic Mist", -0x1.f4ffaep125F); LIST.add(-0x1.f4ffaep125F); }

    /**
     * This color constant "Feather Down" has RGBA8888 code {@code E3E3FFFF}, L 0.8901961, A 0.5019608, B 0.48235294, alpha 1.0, hue 0.7676211, and saturation 0.82644516.
     * It can be represented as a packed float with the constant {@code -0x1.f701c6p125F}.
     * <pre>
     * <font style='background-color: #E3E3FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3E3FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3E3FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E3E3FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E3E3FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E3E3FF'>&nbsp;@&nbsp;</font><font style='background-color: #E3E3FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3E3FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3E3FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FEATHER_DOWN = -0x1.f701c6p125F;
    static { NAMED.put("Feather Down", -0x1.f701c6p125F); LIST.add(-0x1.f701c6p125F); }

    /**
     * This color constant "Mild Violet" has RGBA8888 code {@code AB8FC7FF}, L 0.59607846, A 0.52156866, B 0.46666667, alpha 1.0, hue 0.84141475, and saturation 0.42349717.
     * It can be represented as a packed float with the constant {@code -0x1.ef0b3p125F}.
     * <pre>
     * <font style='background-color: #AB8FC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB8FC7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB8FC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB8FC7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB8FC7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB8FC7'>&nbsp;@&nbsp;</font><font style='background-color: #AB8FC7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB8FC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB8FC7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MILD_VIOLET = -0x1.ef0b3p125F;
    static { NAMED.put("Mild Violet", -0x1.ef0b3p125F); LIST.add(-0x1.ef0b3p125F); }

    /**
     * This color constant "Violet Cushions" has RGBA8888 code {@code 8F57C7FF}, L 0.45882353, A 0.54509807, B 0.43137255, alpha 1.0, hue 0.8425412, and saturation 0.5839586.
     * It can be represented as a packed float with the constant {@code -0x1.dd16eap125F}.
     * <pre>
     * <font style='background-color: #8F57C7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F57C7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F57C7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F57C7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F57C7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F57C7'>&nbsp;@&nbsp;</font><font style='background-color: #8F57C7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F57C7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F57C7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIOLET_CUSHIONS = -0x1.dd16eap125F;
    static { NAMED.put("Violet Cushions", -0x1.dd16eap125F); LIST.add(-0x1.dd16eap125F); }

    /**
     * This color constant "Dull Violet" has RGBA8888 code {@code 73578FFF}, L 0.4, A 0.5254902, B 0.4627451, alpha 1.0, hue 0.84551346, and saturation 0.30816016.
     * It can be represented as a packed float with the constant {@code -0x1.ed0cccp125F}.
     * <pre>
     * <font style='background-color: #73578F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73578F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73578F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #73578F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #73578F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #73578F'>&nbsp;@&nbsp;</font><font style='background-color: #73578F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73578F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73578F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_VIOLET = -0x1.ed0cccp125F;
    static { NAMED.put("Dull Violet", -0x1.ed0cccp125F); LIST.add(-0x1.ed0cccp125F); }

    /**
     * This color constant "Royal Violet" has RGBA8888 code {@code 573B73FF}, L 0.30588236, A 0.5294118, B 0.45882353, alpha 1.0, hue 0.84872866, and saturation 0.4178739.
     * It can be represented as a packed float with the constant {@code -0x1.eb0e9cp125F}.
     * <pre>
     * <font style='background-color: #573B73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #573B73; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #573B73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #573B73'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #573B73'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #573B73'>&nbsp;@&nbsp;</font><font style='background-color: #573B73; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #573B73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #573B73; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ROYAL_VIOLET = -0x1.eb0e9cp125F;
    static { NAMED.put("Royal Violet", -0x1.eb0e9cp125F); LIST.add(-0x1.eb0e9cp125F); }

    /**
     * This color constant "Eminence" has RGBA8888 code {@code 3C233CFF}, L 0.20784314, A 0.5254902, B 0.48235294, alpha 1.0, hue 0.9036119, and saturation 0.35274208.
     * It can be represented as a packed float with the constant {@code -0x1.f70c6ap125F}.
     * <pre>
     * <font style='background-color: #3C233C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C233C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C233C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3C233C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3C233C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3C233C'>&nbsp;@&nbsp;</font><font style='background-color: #3C233C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C233C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C233C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float EMINENCE = -0x1.f70c6ap125F;
    static { NAMED.put("Eminence", -0x1.f70c6ap125F); LIST.add(-0x1.f70c6ap125F); }

    /**
     * This color constant "Prune" has RGBA8888 code {@code 463246FF}, L 0.2509804, A 0.5176471, B 0.4862745, alpha 1.0, hue 0.8947796, and saturation 0.22892934.
     * It can be represented as a packed float with the constant {@code -0x1.f9088p125F}.
     * <pre>
     * <font style='background-color: #463246;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #463246; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #463246;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #463246'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #463246'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #463246'>&nbsp;@&nbsp;</font><font style='background-color: #463246; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #463246;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #463246; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PRUNE = -0x1.f9088p125F;
    static { NAMED.put("Prune", -0x1.f9088p125F); LIST.add(-0x1.f9088p125F); }

    /**
     * This color constant "Dusty Grape" has RGBA8888 code {@code 724072FF}, L 0.34509805, A 0.5411765, B 0.47058824, alpha 1.0, hue 0.90127134, and saturation 0.42472428.
     * It can be represented as a packed float with the constant {@code -0x1.f114bp125F}.
     * <pre>
     * <font style='background-color: #724072;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #724072; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #724072;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #724072'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #724072'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #724072'>&nbsp;@&nbsp;</font><font style='background-color: #724072; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #724072;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #724072; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DUSTY_GRAPE = -0x1.f114bp125F;
    static { NAMED.put("Dusty Grape", -0x1.f114bp125F); LIST.add(-0x1.f114bp125F); }

    /**
     * This color constant "Pink Violet" has RGBA8888 code {@code 8F578FFF}, L 0.43137255, A 0.5411765, B 0.47058824, alpha 1.0, hue 0.90127134, and saturation 0.36490396.
     * It can be represented as a packed float with the constant {@code -0x1.f114dcp125F}.
     * <pre>
     * <font style='background-color: #8F578F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F578F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F578F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F578F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F578F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F578F'>&nbsp;@&nbsp;</font><font style='background-color: #8F578F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F578F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F578F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINK_VIOLET = -0x1.f114dcp125F;
    static { NAMED.put("Pink Violet", -0x1.f114dcp125F); LIST.add(-0x1.f114dcp125F); }

    /**
     * This color constant "Ripe Plum" has RGBA8888 code {@code AB57ABFF}, L 0.47843137, A 0.56078434, B 0.45882353, alpha 1.0, hue 0.9052252, and saturation 0.4946069.
     * It can be represented as a packed float with the constant {@code -0x1.eb1ef4p125F}.
     * <pre>
     * <font style='background-color: #AB57AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB57AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB57AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB57AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB57AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB57AB'>&nbsp;@&nbsp;</font><font style='background-color: #AB57AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB57AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB57AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float RIPE_PLUM = -0x1.eb1ef4p125F;
    static { NAMED.put("Ripe Plum", -0x1.eb1ef4p125F); LIST.add(-0x1.eb1ef4p125F); }

    /**
     * This color constant "Mauve" has RGBA8888 code {@code AB73ABFF}, L 0.5254902, A 0.5411765, B 0.47058824, alpha 1.0, hue 0.90127134, and saturation 0.3159534.
     * It can be represented as a packed float with the constant {@code -0x1.f1150cp125F}.
     * <pre>
     * <font style='background-color: #AB73AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB73AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB73AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB73AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB73AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB73AB'>&nbsp;@&nbsp;</font><font style='background-color: #AB73AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB73AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB73AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MAUVE = -0x1.f1150cp125F;
    static { NAMED.put("Mauve", -0x1.f1150cp125F); LIST.add(-0x1.f1150cp125F); }

    /**
     * This color constant "Ham" has RGBA8888 code {@code EBACE1FF}, L 0.74509805, A 0.5372549, B 0.4745098, alpha 1.0, hue 0.90448654, and saturation 0.550286.
     * It can be represented as a packed float with the constant {@code -0x1.f3137cp125F}.
     * <pre>
     * <font style='background-color: #EBACE1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBACE1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBACE1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EBACE1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EBACE1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EBACE1'>&nbsp;@&nbsp;</font><font style='background-color: #EBACE1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBACE1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBACE1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float HAM = -0x1.f3137cp125F;
    static { NAMED.put("Ham", -0x1.f3137cp125F); LIST.add(-0x1.f3137cp125F); }

    /**
     * This color constant "Cotton Candy" has RGBA8888 code {@code FFDCF5FF}, L 0.8980392, A 0.5176471, B 0.49019608, alpha 1.0, hue 0.9192883, and saturation 0.7950774.
     * It can be represented as a packed float with the constant {@code -0x1.fb09cap125F}.
     * <pre>
     * <font style='background-color: #FFDCF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFDCF5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFDCF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFDCF5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFDCF5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFDCF5'>&nbsp;@&nbsp;</font><font style='background-color: #FFDCF5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFDCF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFDCF5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float COTTON_CANDY = -0x1.fb09cap125F;
    static { NAMED.put("Cotton Candy", -0x1.fb09cap125F); LIST.add(-0x1.fb09cap125F); }

    /**
     * This color constant "Silver Pink" has RGBA8888 code {@code E3C7E3FF}, L 0.8039216, A 0.5176471, B 0.4862745, alpha 1.0, hue 0.8947796, and saturation 0.3815489.
     * It can be represented as a packed float with the constant {@code -0x1.f9099ap125F}.
     * <pre>
     * <font style='background-color: #E3C7E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3C7E3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3C7E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E3C7E3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E3C7E3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E3C7E3'>&nbsp;@&nbsp;</font><font style='background-color: #E3C7E3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3C7E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3C7E3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_PINK = -0x1.f9099ap125F;
    static { NAMED.put("Silver Pink", -0x1.f9099ap125F); LIST.add(-0x1.f9099ap125F); }

    /**
     * This color constant "Tea Rose" has RGBA8888 code {@code E1B9D2FF}, L 0.75686276, A 0.52156866, B 0.49019608, alpha 1.0, hue 0.93210673, and saturation 0.36758882.
     * It can be represented as a packed float with the constant {@code -0x1.fb0b82p125F}.
     * <pre>
     * <font style='background-color: #E1B9D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1B9D2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1B9D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E1B9D2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E1B9D2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E1B9D2'>&nbsp;@&nbsp;</font><font style='background-color: #E1B9D2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1B9D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1B9D2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TEA_ROSE = -0x1.fb0b82p125F;
    static { NAMED.put("Tea Rose", -0x1.fb0b82p125F); LIST.add(-0x1.fb0b82p125F); }

    /**
     * This color constant "Old Rose" has RGBA8888 code {@code D7A0BEFF}, L 0.68235296, A 0.53333336, B 0.49019608, alpha 1.0, hue 0.954483, and saturation 0.43389115.
     * It can be represented as a packed float with the constant {@code -0x1.fb115cp125F}.
     * <pre>
     * <font style='background-color: #D7A0BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7A0BE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7A0BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D7A0BE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D7A0BE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D7A0BE'>&nbsp;@&nbsp;</font><font style='background-color: #D7A0BE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7A0BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7A0BE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float OLD_ROSE = -0x1.fb115cp125F;
    static { NAMED.put("Old Rose", -0x1.fb115cp125F); LIST.add(-0x1.fb115cp125F); }

    /**
     * This color constant "Dusty Pink" has RGBA8888 code {@code C78FB9FF}, L 0.62352943, A 0.5372549, B 0.48235294, alpha 1.0, hue 0.9295985, and saturation 0.3404233.
     * It can be represented as a packed float with the constant {@code -0x1.f7133ep125F}.
     * <pre>
     * <font style='background-color: #C78FB9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C78FB9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C78FB9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C78FB9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C78FB9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C78FB9'>&nbsp;@&nbsp;</font><font style='background-color: #C78FB9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C78FB9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C78FB9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DUSTY_PINK = -0x1.f7133ep125F;
    static { NAMED.put("Dusty Pink", -0x1.f7133ep125F); LIST.add(-0x1.f7133ep125F); }

    /**
     * This color constant "Roseate Spoonbill" has RGBA8888 code {@code C87DA0FF}, L 0.5764706, A 0.54509807, B 0.49019608, alpha 1.0, hue 0.96592754, and saturation 0.3938251.
     * It can be represented as a packed float with the constant {@code -0x1.fb1726p125F}.
     * <pre>
     * <font style='background-color: #C87DA0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C87DA0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C87DA0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C87DA0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C87DA0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C87DA0'>&nbsp;@&nbsp;</font><font style='background-color: #C87DA0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C87DA0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C87DA0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ROSEATE_SPOONBILL = -0x1.fb1726p125F;
    static { NAMED.put("Roseate Spoonbill", -0x1.fb1726p125F); LIST.add(-0x1.fb1726p125F); }

    /**
     * This color constant "Thulian Pink" has RGBA8888 code {@code C35A91FF}, L 0.5019608, A 0.5686275, B 0.48235294, alpha 1.0, hue 0.95994633, and saturation 0.5109908.
     * It can be represented as a packed float with the constant {@code -0x1.f723p125F}.
     * <pre>
     * <font style='background-color: #C35A91;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C35A91; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C35A91;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C35A91'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C35A91'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C35A91'>&nbsp;@&nbsp;</font><font style='background-color: #C35A91; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C35A91;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C35A91; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float THULIAN_PINK = -0x1.f723p125F;
    static { NAMED.put("Thulian Pink", -0x1.f723p125F); LIST.add(-0x1.f723p125F); }

    /**
     * This color constant "Brown Velvet" has RGBA8888 code {@code 4B2837FF}, L 0.23137255, A 0.5294118, B 0.49411765, alpha 1.0, hue 0.96857655, and saturation 0.36564398.
     * It can be represented as a packed float with the constant {@code -0x1.fd0e76p125F}.
     * <pre>
     * <font style='background-color: #4B2837;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B2837; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B2837;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4B2837'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4B2837'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4B2837'>&nbsp;@&nbsp;</font><font style='background-color: #4B2837; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B2837;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B2837; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BROWN_VELVET = -0x1.fd0e76p125F;
    static { NAMED.put("Brown Velvet", -0x1.fd0e76p125F); LIST.add(-0x1.fd0e76p125F); }

    /**
     * This color constant "Nightshade" has RGBA8888 code {@code 321623FF}, L 0.16078432, A 0.5294118, B 0.49411765, alpha 1.0, hue 0.96857655, and saturation 0.41505533.
     * It can be represented as a packed float with the constant {@code -0x1.fd0e52p125F}.
     * <pre>
     * <font style='background-color: #321623;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #321623; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #321623;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #321623'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #321623'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #321623'>&nbsp;@&nbsp;</font><font style='background-color: #321623; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #321623;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #321623; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float NIGHTSHADE = -0x1.fd0e52p125F;
    static { NAMED.put("Nightshade", -0x1.fd0e52p125F); LIST.add(-0x1.fd0e52p125F); }

    /**
     * This color constant "Scribe Ink" has RGBA8888 code {@code 280A1EFF}, L 0.1254902, A 0.53333336, B 0.4862745, alpha 1.0, hue 0.9378436, and saturation 0.49883446.
     * It can be represented as a packed float with the constant {@code -0x1.f9104p125F}.
     * <pre>
     * <font style='background-color: #280A1E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #280A1E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #280A1E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #280A1E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #280A1E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #280A1E'>&nbsp;@&nbsp;</font><font style='background-color: #280A1E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #280A1E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #280A1E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SCRIBE_INK = -0x1.f9104p125F;
    static { NAMED.put("Scribe Ink", -0x1.f9104p125F); LIST.add(-0x1.f9104p125F); }

    /**
     * This color constant "Varnish" has RGBA8888 code {@code 401811FF}, L 0.18039216, A 0.5294118, B 0.5176471, alpha 1.0, hue 0.08601887, and saturation 0.48781818.
     * It can be represented as a packed float with the constant {@code -0x1.090e5cp126F}.
     * <pre>
     * <font style='background-color: #401811;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #401811; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #401811;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #401811'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #401811'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #401811'>&nbsp;@&nbsp;</font><font style='background-color: #401811; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #401811;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #401811; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VARNISH = -0x1.090e5cp126F;
    static { NAMED.put("Varnish", -0x1.090e5cp126F); LIST.add(-0x1.090e5cp126F); }

    /**
     * This color constant "Cedar Wood" has RGBA8888 code {@code 621800FF}, L 0.23529412, A 0.54901963, B 0.53333336, alpha 1.0, hue 0.095056064, and saturation 0.77823055.
     * It can be represented as a packed float with the constant {@code -0x1.111878p126F}.
     * <pre>
     * <font style='background-color: #621800;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #621800; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #621800;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #621800'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #621800'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #621800'>&nbsp;@&nbsp;</font><font style='background-color: #621800; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #621800;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #621800; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CEDAR_WOOD = -0x1.111878p126F;
    static { NAMED.put("Cedar Wood", -0x1.111878p126F); LIST.add(-0x1.111878p126F); }

    /**
     * This color constant "Hot Sauce" has RGBA8888 code {@code A5140AFF}, L 0.34117648, A 0.5803922, B 0.54509807, alpha 1.0, hue 0.08136994, and saturation 0.907596.
     * It can be represented as a packed float with the constant {@code -0x1.1728aep126F}.
     * <pre>
     * <font style='background-color: #A5140A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A5140A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A5140A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A5140A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A5140A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A5140A'>&nbsp;@&nbsp;</font><font style='background-color: #A5140A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A5140A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A5140A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float HOT_SAUCE = -0x1.1728aep126F;
    static { NAMED.put("Hot Sauce", -0x1.1728aep126F); LIST.add(-0x1.1728aep126F); }

    /**
     * This color constant "Lurid Red" has RGBA8888 code {@code DA2010FF}, L 0.4392157, A 0.5921569, B 0.5529412, alpha 1.0, hue 0.08299518, and saturation 0.87767553.
     * It can be represented as a packed float with the constant {@code -0x1.1b2eep126F}.
     * <pre>
     * <font style='background-color: #DA2010;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA2010; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA2010;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DA2010'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DA2010'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DA2010'>&nbsp;@&nbsp;</font><font style='background-color: #DA2010; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA2010;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA2010; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LURID_RED = -0x1.1b2eep126F;
    static { NAMED.put("Lurid Red", -0x1.1b2eep126F); LIST.add(-0x1.1b2eep126F); }

    /**
     * This color constant "Brick" has RGBA8888 code {@code D5524AFF}, L 0.49411765, A 0.5686275, B 0.53333336, alpha 1.0, hue 0.07195936, and saturation 0.59185976.
     * It can be represented as a packed float with the constant {@code -0x1.1122fcp126F}.
     * <pre>
     * <font style='background-color: #D5524A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5524A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5524A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D5524A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D5524A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D5524A'>&nbsp;@&nbsp;</font><font style='background-color: #D5524A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5524A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5524A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRICK = -0x1.1122fcp126F;
    static { NAMED.put("Brick", -0x1.1122fcp126F); LIST.add(-0x1.1122fcp126F); }

    /**
     * This color constant "Bright Red" has RGBA8888 code {@code FF3C0AFF}, L 0.5254902, A 0.5921569, B 0.5647059, alpha 1.0, hue 0.097439915, and saturation 0.9771767.
     * It can be represented as a packed float with the constant {@code -0x1.212f0cp126F}.
     * <pre>
     * <font style='background-color: #FF3C0A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF3C0A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF3C0A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF3C0A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF3C0A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF3C0A'>&nbsp;@&nbsp;</font><font style='background-color: #FF3C0A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF3C0A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF3C0A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED = -0x1.212f0cp126F;
    static { NAMED.put("Bright Red", -0x1.212f0cp126F); LIST.add(-0x1.212f0cp126F); }

    /**
     * This color constant "Embers" has RGBA8888 code {@code F55A32FF}, L 0.54901963, A 0.57254905, B 0.5568628, alpha 1.0, hue 0.10581406, and saturation 0.85809076.
     * It can be represented as a packed float with the constant {@code -0x1.1d2518p126F}.
     * <pre>
     * <font style='background-color: #F55A32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F55A32; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F55A32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F55A32'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F55A32'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F55A32'>&nbsp;@&nbsp;</font><font style='background-color: #F55A32; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F55A32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F55A32; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float EMBERS = -0x1.1d2518p126F;
    static { NAMED.put("Embers", -0x1.1d2518p126F); LIST.add(-0x1.1d2518p126F); }

    /**
     * This color constant "Salmon" has RGBA8888 code {@code FF6262FF}, L 0.58431375, A 0.5803922, B 0.53333336, alpha 1.0, hue 0.06254671, and saturation 0.94805837.
     * It can be represented as a packed float with the constant {@code -0x1.11292ap126F}.
     * <pre>
     * <font style='background-color: #FF6262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6262; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF6262'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF6262'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF6262'>&nbsp;@&nbsp;</font><font style='background-color: #FF6262; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6262; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SALMON = -0x1.11292ap126F;
    static { NAMED.put("Salmon", -0x1.11292ap126F); LIST.add(-0x1.11292ap126F); }

    /**
     * This color constant "Taxicab Yellow" has RGBA8888 code {@code F6BD31FF}, L 0.7529412, A 0.5019608, B 0.5764706, alpha 1.0, hue 0.24593177, and saturation 0.8703513.
     * It can be represented as a packed float with the constant {@code -0x1.27018p126F}.
     * <pre>
     * <font style='background-color: #F6BD31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6BD31; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6BD31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F6BD31'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F6BD31'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F6BD31'>&nbsp;@&nbsp;</font><font style='background-color: #F6BD31; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6BD31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6BD31; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TAXICAB_YELLOW = -0x1.27018p126F;
    static { NAMED.put("Taxicab Yellow", -0x1.27018p126F); LIST.add(-0x1.27018p126F); }

    /**
     * This color constant "Apricot" has RGBA8888 code {@code FFA53CFF}, L 0.70980394, A 0.5254902, B 0.5686275, alpha 1.0, hue 0.19341156, and saturation 0.9610956.
     * It can be represented as a packed float with the constant {@code -0x1.230d6ap126F}.
     * <pre>
     * <font style='background-color: #FFA53C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA53C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA53C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFA53C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFA53C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFA53C'>&nbsp;@&nbsp;</font><font style='background-color: #FFA53C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA53C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA53C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float APRICOT = -0x1.230d6ap126F;
    static { NAMED.put("Apricot", -0x1.230d6ap126F); LIST.add(-0x1.230d6ap126F); }

    /**
     * This color constant "Burnt Yellow" has RGBA8888 code {@code D79B0FFF}, L 0.6313726, A 0.5058824, B 0.57254905, alpha 1.0, hue 0.23712316, and saturation 0.90895116.
     * It can be represented as a packed float with the constant {@code -0x1.250342p126F}.
     * <pre>
     * <font style='background-color: #D79B0F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D79B0F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D79B0F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D79B0F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D79B0F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D79B0F'>&nbsp;@&nbsp;</font><font style='background-color: #D79B0F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D79B0F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D79B0F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BURNT_YELLOW = -0x1.250342p126F;
    static { NAMED.put("Burnt Yellow", -0x1.250342p126F); LIST.add(-0x1.250342p126F); }

    /**
     * This color constant "Dry Pepper" has RGBA8888 code {@code DA6E0AFF}, L 0.5411765, A 0.5411765, B 0.5647059, alpha 1.0, hue 0.15979148, and saturation 0.91322345.
     * It can be represented as a packed float with the constant {@code -0x1.211514p126F}.
     * <pre>
     * <font style='background-color: #DA6E0A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA6E0A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA6E0A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DA6E0A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DA6E0A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DA6E0A'>&nbsp;@&nbsp;</font><font style='background-color: #DA6E0A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA6E0A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA6E0A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRY_PEPPER = -0x1.211514p126F;
    static { NAMED.put("Dry Pepper", -0x1.211514p126F); LIST.add(-0x1.211514p126F); }

    /**
     * This color constant "Redwood" has RGBA8888 code {@code B45A00FF}, L 0.4509804, A 0.5372549, B 0.5568628, alpha 1.0, hue 0.15767807, and saturation 0.89245725.
     * It can be represented as a packed float with the constant {@code -0x1.1d12e6p126F}.
     * <pre>
     * <font style='background-color: #B45A00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B45A00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B45A00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B45A00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B45A00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B45A00'>&nbsp;@&nbsp;</font><font style='background-color: #B45A00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B45A00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B45A00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float REDWOOD = -0x1.1d12e6p126F;
    static { NAMED.put("Redwood", -0x1.1d12e6p126F); LIST.add(-0x1.1d12e6p126F); }

    /**
     * This color constant "Koa" has RGBA8888 code {@code A04B05FF}, L 0.4, A 0.5372549, B 0.5529412, alpha 1.0, hue 0.15239218, and saturation 0.92068386.
     * It can be represented as a packed float with the constant {@code -0x1.1b12ccp126F}.
     * <pre>
     * <font style='background-color: #A04B05;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A04B05; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A04B05;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A04B05'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A04B05'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A04B05'>&nbsp;@&nbsp;</font><font style='background-color: #A04B05; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A04B05;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A04B05; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float KOA = -0x1.1b12ccp126F;
    static { NAMED.put("Koa", -0x1.1b12ccp126F); LIST.add(-0x1.1b12ccp126F); }

    /**
     * This color constant "Ochre" has RGBA8888 code {@code 5F3214FF}, L 0.27058825, A 0.52156866, B 0.53333336, alpha 1.0, hue 0.15858527, and saturation 0.7259951.
     * It can be represented as a packed float with the constant {@code -0x1.110a8ap126F}.
     * <pre>
     * <font style='background-color: #5F3214;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5F3214; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5F3214;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5F3214'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5F3214'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5F3214'>&nbsp;@&nbsp;</font><font style='background-color: #5F3214; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5F3214;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5F3214; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float OCHRE = -0x1.110a8ap126F;
    static { NAMED.put("Ochre", -0x1.110a8ap126F); LIST.add(-0x1.110a8ap126F); }

    /**
     * This color constant "Dull Green" has RGBA8888 code {@code 53500AFF}, L 0.31764707, A 0.48235294, B 0.54509807, alpha 1.0, hue 0.30935085, and saturation 0.88553697.
     * It can be represented as a packed float with the constant {@code -0x1.16f6a2p126F}.
     * <pre>
     * <font style='background-color: #53500A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #53500A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #53500A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #53500A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #53500A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #53500A'>&nbsp;@&nbsp;</font><font style='background-color: #53500A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #53500A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #53500A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_GREEN = -0x1.16f6a2p126F;
    static { NAMED.put("Dull Green", -0x1.16f6a2p126F); LIST.add(-0x1.16f6a2p126F); }

    /**
     * This color constant "Army Green" has RGBA8888 code {@code 626200FF}, L 0.37254903, A 0.47843137, B 0.54901963, alpha 1.0, hue 0.31596267, and saturation 0.8845197.
     * It can be represented as a packed float with the constant {@code -0x1.18f4bep126F}.
     * <pre>
     * <font style='background-color: #626200;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #626200; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #626200;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #626200'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #626200'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #626200'>&nbsp;@&nbsp;</font><font style='background-color: #626200; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #626200;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #626200; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ARMY_GREEN = -0x1.18f4bep126F;
    static { NAMED.put("Army Green", -0x1.18f4bep126F); LIST.add(-0x1.18f4bep126F); }

    /**
     * This color constant "Driftwood" has RGBA8888 code {@code 8C805AFF}, L 0.49411765, A 0.49803922, B 0.5254902, alpha 1.0, hue 0.26221767, and saturation 0.37398672.
     * It can be represented as a packed float with the constant {@code -0x1.0cfefcp126F}.
     * <pre>
     * <font style='background-color: #8C805A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C805A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C805A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8C805A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8C805A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8C805A'>&nbsp;@&nbsp;</font><font style='background-color: #8C805A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C805A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C805A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRIFTWOOD = -0x1.0cfefcp126F;
    static { NAMED.put("Driftwood", -0x1.0cfefcp126F); LIST.add(-0x1.0cfefcp126F); }

    /**
     * This color constant "Dry Brush" has RGBA8888 code {@code AC9400FF}, L 0.5647059, A 0.4862745, B 0.5686275, alpha 1.0, hue 0.28142345, and saturation 0.9429766.
     * It can be represented as a packed float with the constant {@code -0x1.22f92p126F}.
     * <pre>
     * <font style='background-color: #AC9400;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC9400; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC9400;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AC9400'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AC9400'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AC9400'>&nbsp;@&nbsp;</font><font style='background-color: #AC9400; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC9400;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC9400; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRY_BRUSH = -0x1.22f92p126F;
    static { NAMED.put("Dry Brush", -0x1.22f92p126F); LIST.add(-0x1.22f92p126F); }

    /**
     * This color constant "Mush" has RGBA8888 code {@code B1B10AFF}, L 0.6431373, A 0.47058824, B 0.5764706, alpha 1.0, hue 0.30842525, and saturation 0.9755588.
     * It can be represented as a packed float with the constant {@code -0x1.26f148p126F}.
     * <pre>
     * <font style='background-color: #B1B10A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1B10A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1B10A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B1B10A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B1B10A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B1B10A'>&nbsp;@&nbsp;</font><font style='background-color: #B1B10A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1B10A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1B10A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MUSH = -0x1.26f148p126F;
    static { NAMED.put("Mush", -0x1.26f148p126F); LIST.add(-0x1.26f148p126F); }

    /**
     * This color constant "Banana Pudding" has RGBA8888 code {@code E6D55AFF}, L 0.8039216, A 0.48235294, B 0.5647059, alpha 1.0, hue 0.29236877, and saturation 0.7154043.
     * It can be represented as a packed float with the constant {@code -0x1.20f79ap126F}.
     * <pre>
     * <font style='background-color: #E6D55A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E6D55A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E6D55A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E6D55A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E6D55A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E6D55A'>&nbsp;@&nbsp;</font><font style='background-color: #E6D55A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E6D55A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E6D55A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BANANA_PUDDING = -0x1.20f79ap126F;
    static { NAMED.put("Banana Pudding", -0x1.20f79ap126F); LIST.add(-0x1.20f79ap126F); }

    /**
     * This color constant "Saffron" has RGBA8888 code {@code FFD510FF}, L 0.827451, A 0.4862745, B 0.5882353, alpha 1.0, hue 0.27457327, and saturation 0.9524955.
     * It can be represented as a packed float with the constant {@code -0x1.2cf9a6p126F}.
     * <pre>
     * <font style='background-color: #FFD510;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD510; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD510;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFD510'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFD510'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFD510'>&nbsp;@&nbsp;</font><font style='background-color: #FFD510; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD510;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD510; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SAFFRON = -0x1.2cf9a6p126F;
    static { NAMED.put("Saffron", -0x1.2cf9a6p126F); LIST.add(-0x1.2cf9a6p126F); }

    /**
     * This color constant "Pencil Yellow" has RGBA8888 code {@code FFEA4AFF}, L 0.8901961, A 0.47843137, B 0.5803922, alpha 1.0, hue 0.29171178, and saturation 0.9470321.
     * It can be represented as a packed float with the constant {@code -0x1.28f5c6p126F}.
     * <pre>
     * <font style='background-color: #FFEA4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFEA4A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFEA4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFEA4A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFEA4A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFEA4A'>&nbsp;@&nbsp;</font><font style='background-color: #FFEA4A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFEA4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFEA4A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PENCIL_YELLOW = -0x1.28f5c6p126F;
    static { NAMED.put("Pencil Yellow", -0x1.28f5c6p126F); LIST.add(-0x1.28f5c6p126F); }

    /**
     * This color constant "Chartreuse" has RGBA8888 code {@code C8FF41FF}, L 0.8901961, A 0.4392157, B 0.58431375, alpha 1.0, hue 0.34942675, and saturation 0.93363714.
     * It can be represented as a packed float with the constant {@code -0x1.2ae1c6p126F}.
     * <pre>
     * <font style='background-color: #C8FF41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8FF41; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8FF41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C8FF41'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C8FF41'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C8FF41'>&nbsp;@&nbsp;</font><font style='background-color: #C8FF41; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8FF41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8FF41; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CHARTREUSE = -0x1.2ae1c6p126F;
    static { NAMED.put("Chartreuse", -0x1.2ae1c6p126F); LIST.add(-0x1.2ae1c6p126F); }

    /**
     * This color constant "Absinthe" has RGBA8888 code {@code 9BF046FF}, L 0.80784315, A 0.42745098, B 0.5764706, alpha 1.0, hue 0.37081593, and saturation 0.8566594.
     * It can be represented as a packed float with the constant {@code -0x1.26db9cp126F}.
     * <pre>
     * <font style='background-color: #9BF046;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9BF046; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9BF046;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9BF046'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9BF046'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9BF046'>&nbsp;@&nbsp;</font><font style='background-color: #9BF046; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9BF046;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9BF046; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ABSINTHE = -0x1.26db9cp126F;
    static { NAMED.put("Absinthe", -0x1.26db9cp126F); LIST.add(-0x1.26db9cp126F); }

    /**
     * This color constant "Infection" has RGBA8888 code {@code 96DC19FF}, L 0.7372549, A 0.43137255, B 0.5803922, alpha 1.0, hue 0.3624701, and saturation 0.93308157.
     * It can be represented as a packed float with the constant {@code -0x1.28dd78p126F}.
     * <pre>
     * <font style='background-color: #96DC19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #96DC19; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #96DC19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #96DC19'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #96DC19'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #96DC19'>&nbsp;@&nbsp;</font><font style='background-color: #96DC19; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #96DC19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #96DC19; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float INFECTION = -0x1.28dd78p126F;
    static { NAMED.put("Infection", -0x1.28dd78p126F); LIST.add(-0x1.28dd78p126F); }

    /**
     * This color constant "Frog Green" has RGBA8888 code {@code 73C805FF}, L 0.654902, A 0.42745098, B 0.5764706, alpha 1.0, hue 0.37081593, and saturation 0.96374184.
     * It can be represented as a packed float with the constant {@code -0x1.26db4ep126F}.
     * <pre>
     * <font style='background-color: #73C805;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73C805; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73C805;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #73C805'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #73C805'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #73C805'>&nbsp;@&nbsp;</font><font style='background-color: #73C805; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73C805;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73C805; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FROG_GREEN = -0x1.26db4ep126F;
    static { NAMED.put("Frog Green", -0x1.26db4ep126F); LIST.add(-0x1.26db4ep126F); }

    /**
     * This color constant "Avocado" has RGBA8888 code {@code 6AA805FF}, L 0.56078434, A 0.4392157, B 0.5686275, alpha 1.0, hue 0.3653732, and saturation 0.95791906.
     * It can be represented as a packed float with the constant {@code -0x1.22e11ep126F}.
     * <pre>
     * <font style='background-color: #6AA805;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6AA805; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6AA805;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6AA805'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6AA805'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6AA805'>&nbsp;@&nbsp;</font><font style='background-color: #6AA805; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6AA805;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6AA805; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float AVOCADO = -0x1.22e11ep126F;
    static { NAMED.put("Avocado", -0x1.22e11ep126F); LIST.add(-0x1.22e11ep126F); }

    /**
     * This color constant "Woodlands" has RGBA8888 code {@code 3C6E14FF}, L 0.3764706, A 0.4509804, B 0.54901963, alpha 1.0, hue 0.375, and saturation 0.86570704.
     * It can be represented as a packed float with the constant {@code -0x1.18e6cp126F}.
     * <pre>
     * <font style='background-color: #3C6E14;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C6E14; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C6E14;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3C6E14'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3C6E14'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3C6E14'>&nbsp;@&nbsp;</font><font style='background-color: #3C6E14; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C6E14;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C6E14; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WOODLANDS = -0x1.18e6cp126F;
    static { NAMED.put("Woodlands", -0x1.18e6cp126F); LIST.add(-0x1.18e6cp126F); }

    /**
     * This color constant "Dark Pine" has RGBA8888 code {@code 283405FF}, L 0.21176471, A 0.4745098, B 0.5294118, alpha 1.0, hue 0.3636593, and saturation 0.7116884.
     * It can be represented as a packed float with the constant {@code -0x1.0ef26cp126F}.
     * <pre>
     * <font style='background-color: #283405;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #283405; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #283405;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #283405'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #283405'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #283405'>&nbsp;@&nbsp;</font><font style='background-color: #283405; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #283405;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #283405; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_PINE = -0x1.0ef26cp126F;
    static { NAMED.put("Dark Pine", -0x1.0ef26cp126F); LIST.add(-0x1.0ef26cp126F); }

    /**
     * This color constant "Moss Green" has RGBA8888 code {@code 204608FF}, L 0.25490198, A 0.45882353, B 0.5372549, alpha 1.0, hue 0.38294512, and saturation 0.836196.
     * It can be represented as a packed float with the constant {@code -0x1.12ea82p126F}.
     * <pre>
     * <font style='background-color: #204608;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #204608; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #204608;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #204608'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #204608'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #204608'>&nbsp;@&nbsp;</font><font style='background-color: #204608; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #204608;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #204608; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MOSS_GREEN = -0x1.12ea82p126F;
    static { NAMED.put("Moss Green", -0x1.12ea82p126F); LIST.add(-0x1.12ea82p126F); }

    /**
     * This color constant "Fern Green" has RGBA8888 code {@code 0C5C0CFF}, L 0.30980393, A 0.4392157, B 0.5411765, alpha 1.0, hue 0.40522522, and saturation 0.895003.
     * It can be represented as a packed float with the constant {@code -0x1.14e09ep126F}.
     * <pre>
     * <font style='background-color: #0C5C0C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0C5C0C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0C5C0C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0C5C0C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0C5C0C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0C5C0C'>&nbsp;@&nbsp;</font><font style='background-color: #0C5C0C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0C5C0C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0C5C0C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FERN_GREEN = -0x1.14e09ep126F;
    static { NAMED.put("Fern Green", -0x1.14e09ep126F); LIST.add(-0x1.14e09ep126F); }

    /**
     * This color constant "Forest Glen" has RGBA8888 code {@code 149605FF}, L 0.47058824, A 0.41568628, B 0.56078434, alpha 1.0, hue 0.40057325, and saturation 0.9503092.
     * It can be represented as a packed float with the constant {@code -0x1.1ed4fp126F}.
     * <pre>
     * <font style='background-color: #149605;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #149605; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #149605;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #149605'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #149605'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #149605'>&nbsp;@&nbsp;</font><font style='background-color: #149605; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #149605;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #149605; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FOREST_GLEN = -0x1.1ed4fp126F;
    static { NAMED.put("Forest Glen", -0x1.1ed4fp126F); LIST.add(-0x1.1ed4fp126F); }

    /**
     * This color constant "Malachite" has RGBA8888 code {@code 0AD70AFF}, L 0.6627451, A 0.39215687, B 0.5764706, alpha 1.0, hue 0.40182015, and saturation 0.9669777.
     * It can be represented as a packed float with the constant {@code -0x1.26c952p126F}.
     * <pre>
     * <font style='background-color: #0AD70A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0AD70A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0AD70A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0AD70A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0AD70A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0AD70A'>&nbsp;@&nbsp;</font><font style='background-color: #0AD70A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0AD70A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0AD70A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MALACHITE = -0x1.26c952p126F;
    static { NAMED.put("Malachite", -0x1.26c952p126F); LIST.add(-0x1.26c952p126F); }

    /**
     * This color constant "Apple Green" has RGBA8888 code {@code 14E60AFF}, L 0.70980394, A 0.3882353, B 0.5803922, alpha 1.0, hue 0.4007443, and saturation 0.965607.
     * It can be represented as a packed float with the constant {@code -0x1.28c76ap126F}.
     * <pre>
     * <font style='background-color: #14E60A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #14E60A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #14E60A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #14E60A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #14E60A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #14E60A'>&nbsp;@&nbsp;</font><font style='background-color: #14E60A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #14E60A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #14E60A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float APPLE_GREEN = -0x1.28c76ap126F;
    static { NAMED.put("Apple Green", -0x1.28c76ap126F); LIST.add(-0x1.28c76ap126F); }

    /**
     * This color constant "Celery" has RGBA8888 code {@code 7DFF73FF}, L 0.8392157, A 0.41960785, B 0.56078434, alpha 1.0, hue 0.39695174, and saturation 0.9052976.
     * It can be represented as a packed float with the constant {@code -0x1.1ed7acp126F}.
     * <pre>
     * <font style='background-color: #7DFF73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7DFF73; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7DFF73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7DFF73'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7DFF73'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7DFF73'>&nbsp;@&nbsp;</font><font style='background-color: #7DFF73; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7DFF73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7DFF73; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CELERY = -0x1.1ed7acp126F;
    static { NAMED.put("Celery", -0x1.1ed7acp126F); LIST.add(-0x1.1ed7acp126F); }

    /**
     * This color constant "Mint Green" has RGBA8888 code {@code 4BF05AFF}, L 0.7607843, A 0.40392157, B 0.5647059, alpha 1.0, hue 0.40565702, and saturation 0.81243694.
     * It can be represented as a packed float with the constant {@code -0x1.20cf84p126F}.
     * <pre>
     * <font style='background-color: #4BF05A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4BF05A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4BF05A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4BF05A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4BF05A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4BF05A'>&nbsp;@&nbsp;</font><font style='background-color: #4BF05A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4BF05A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4BF05A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MINT_GREEN = -0x1.20cf84p126F;
    static { NAMED.put("Mint Green", -0x1.20cf84p126F); LIST.add(-0x1.20cf84p126F); }

    /**
     * This color constant "Emerald" has RGBA8888 code {@code 00C514FF}, L 0.60784316, A 0.4, B 0.57254905, alpha 1.0, hue 0.40009674, and saturation 0.9584095.
     * It can be represented as a packed float with the constant {@code -0x1.24cd36p126F}.
     * <pre>
     * <font style='background-color: #00C514;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00C514; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00C514;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00C514'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00C514'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00C514'>&nbsp;@&nbsp;</font><font style='background-color: #00C514; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00C514;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00C514; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float EMERALD = -0x1.24cd36p126F;
    static { NAMED.put("Emerald", -0x1.24cd36p126F); LIST.add(-0x1.24cd36p126F); }

    /**
     * This color constant "Prase" has RGBA8888 code {@code 05B450FF}, L 0.5647059, A 0.41568628, B 0.54509807, alpha 1.0, hue 0.4218265, and saturation 0.9236977.
     * It can be represented as a packed float with the constant {@code -0x1.16d52p126F}.
     * <pre>
     * <font style='background-color: #05B450;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #05B450; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #05B450;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #05B450'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #05B450'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #05B450'>&nbsp;@&nbsp;</font><font style='background-color: #05B450; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #05B450;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #05B450; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PRASE = -0x1.16d52p126F;
    static { NAMED.put("Prase", -0x1.16d52p126F); LIST.add(-0x1.16d52p126F); }

    /**
     * This color constant "Eucalyptus" has RGBA8888 code {@code 1C8C4EFF}, L 0.45490196, A 0.43529412, B 0.5294118, alpha 1.0, hue 0.43210676, and saturation 0.86645937.
     * It can be represented as a packed float with the constant {@code -0x1.0edee8p126F}.
     * <pre>
     * <font style='background-color: #1C8C4E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1C8C4E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1C8C4E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1C8C4E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1C8C4E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1C8C4E'>&nbsp;@&nbsp;</font><font style='background-color: #1C8C4E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1C8C4E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1C8C4E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float EUCALYPTUS = -0x1.0edee8p126F;
    static { NAMED.put("Eucalyptus", -0x1.0edee8p126F); LIST.add(-0x1.0edee8p126F); }

    /**
     * This color constant "Zucchini" has RGBA8888 code {@code 123832FF}, L 0.21960784, A 0.47058824, B 0.49803922, alpha 1.0, hue 0.5105898, and saturation 0.7186786.
     * It can be represented as a packed float with the constant {@code -0x1.fef07p125F}.
     * <pre>
     * <font style='background-color: #123832;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #123832; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #123832;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #123832'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #123832'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #123832'>&nbsp;@&nbsp;</font><font style='background-color: #123832; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #123832;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #123832; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ZUCCHINI = -0x1.fef07p125F;
    static { NAMED.put("Zucchini", -0x1.fef07p125F); LIST.add(-0x1.fef07p125F); }

    /**
     * This color constant "Soft Teal" has RGBA8888 code {@code 129880FF}, L 0.49803922, A 0.4392157, B 0.5019608, alpha 1.0, hue 0.49488065, and saturation 0.9158164.
     * It can be represented as a packed float with the constant {@code -0x1.00e0fep126F}.
     * <pre>
     * <font style='background-color: #129880;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #129880; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #129880;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #129880'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #129880'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #129880'>&nbsp;@&nbsp;</font><font style='background-color: #129880; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #129880;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #129880; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOFT_TEAL = -0x1.00e0fep126F;
    static { NAMED.put("Soft Teal", -0x1.00e0fep126F); LIST.add(-0x1.00e0fep126F); }

    /**
     * This color constant "Medium Teal" has RGBA8888 code {@code 06C491FF}, L 0.627451, A 0.42352942, B 0.5137255, alpha 1.0, hue 0.47172436, and saturation 0.94710976.
     * It can be represented as a packed float with the constant {@code -0x1.06d94p126F}.
     * <pre>
     * <font style='background-color: #06C491;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #06C491; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #06C491;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #06C491'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #06C491'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #06C491'>&nbsp;@&nbsp;</font><font style='background-color: #06C491; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #06C491;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #06C491; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MEDIUM_TEAL = -0x1.06d94p126F;
    static { NAMED.put("Medium Teal", -0x1.06d94p126F); LIST.add(-0x1.06d94p126F); }

    /**
     * This color constant "Spring Green" has RGBA8888 code {@code 00DE6AFF}, L 0.69411767, A 0.40392157, B 0.54901963, alpha 1.0, hue 0.42491466, and saturation 0.952152.
     * It can be represented as a packed float with the constant {@code -0x1.18cf62p126F}.
     * <pre>
     * <font style='background-color: #00DE6A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00DE6A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00DE6A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00DE6A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00DE6A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00DE6A'>&nbsp;@&nbsp;</font><font style='background-color: #00DE6A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00DE6A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00DE6A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SPRING_GREEN = -0x1.18cf62p126F;
    static { NAMED.put("Spring Green", -0x1.18cf62p126F); LIST.add(-0x1.18cf62p126F); }

    /**
     * This color constant "Turquoise" has RGBA8888 code {@code 2DEBA8FF}, L 0.75686276, A 0.41568628, B 0.52156866, alpha 1.0, hue 0.46014452, and saturation 0.9093621.
     * It can be represented as a packed float with the constant {@code -0x1.0ad582p126F}.
     * <pre>
     * <font style='background-color: #2DEBA8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2DEBA8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2DEBA8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2DEBA8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2DEBA8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2DEBA8'>&nbsp;@&nbsp;</font><font style='background-color: #2DEBA8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2DEBA8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2DEBA8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TURQUOISE = -0x1.0ad582p126F;
    static { NAMED.put("Turquoise", -0x1.0ad582p126F); LIST.add(-0x1.0ad582p126F); }

    /**
     * This color constant "Seafoam" has RGBA8888 code {@code 3CFEA5FF}, L 0.81960785, A 0.4117647, B 0.5294118, alpha 1.0, hue 0.44880432, and saturation 0.91577274.
     * It can be represented as a packed float with the constant {@code -0x1.0ed3a2p126F}.
     * <pre>
     * <font style='background-color: #3CFEA5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3CFEA5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3CFEA5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3CFEA5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3CFEA5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3CFEA5'>&nbsp;@&nbsp;</font><font style='background-color: #3CFEA5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3CFEA5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3CFEA5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SEAFOAM = -0x1.0ed3a2p126F;
    static { NAMED.put("Seafoam", -0x1.0ed3a2p126F); LIST.add(-0x1.0ed3a2p126F); }

    /**
     * This color constant "Variscite" has RGBA8888 code {@code 6AFFCDFF}, L 0.85490197, A 0.43137255, B 0.50980395, alpha 1.0, hue 0.47740343, and saturation 0.9340523.
     * It can be represented as a packed float with the constant {@code -0x1.04ddb4p126F}.
     * <pre>
     * <font style='background-color: #6AFFCD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6AFFCD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6AFFCD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6AFFCD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6AFFCD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6AFFCD'>&nbsp;@&nbsp;</font><font style='background-color: #6AFFCD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6AFFCD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6AFFCD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VARISCITE = -0x1.04ddb4p126F;
    static { NAMED.put("Variscite", -0x1.04ddb4p126F); LIST.add(-0x1.04ddb4p126F); }

    /**
     * This color constant "Refreshing Mist" has RGBA8888 code {@code 91EBFFFF}, L 0.8392157, A 0.4627451, B 0.4745098, alpha 1.0, hue 0.59551346, and saturation 0.92448044.
     * It can be represented as a packed float with the constant {@code -0x1.f2edacp125F}.
     * <pre>
     * <font style='background-color: #91EBFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #91EBFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #91EBFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #91EBFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #91EBFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #91EBFF'>&nbsp;@&nbsp;</font><font style='background-color: #91EBFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #91EBFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #91EBFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float REFRESHING_MIST = -0x1.f2edacp125F;
    static { NAMED.put("Refreshing Mist", -0x1.f2edacp125F); LIST.add(-0x1.f2edacp125F); }

    /**
     * This color constant "Shining Sky" has RGBA8888 code {@code 55E6FFFF}, L 0.7882353, A 0.44313726, B 0.46666667, alpha 1.0, hue 0.5843938, and saturation 0.93742496.
     * It can be represented as a packed float with the constant {@code -0x1.eee392p125F}.
     * <pre>
     * <font style='background-color: #55E6FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #55E6FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #55E6FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #55E6FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #55E6FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #55E6FF'>&nbsp;@&nbsp;</font><font style='background-color: #55E6FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #55E6FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #55E6FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SHINING_SKY = -0x1.eee392p125F;
    static { NAMED.put("Shining Sky", -0x1.eee392p125F); LIST.add(-0x1.eee392p125F); }

    /**
     * This color constant "Steam" has RGBA8888 code {@code 7DD7F0FF}, L 0.7607843, A 0.4627451, B 0.47058824, alpha 1.0, hue 0.6063733, and saturation 0.7364354.
     * It can be represented as a packed float with the constant {@code -0x1.f0ed84p125F}.
     * <pre>
     * <font style='background-color: #7DD7F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7DD7F0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7DD7F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7DD7F0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7DD7F0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7DD7F0'>&nbsp;@&nbsp;</font><font style='background-color: #7DD7F0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7DD7F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7DD7F0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STEAM = -0x1.f0ed84p125F;
    static { NAMED.put("Steam", -0x1.f0ed84p125F); LIST.add(-0x1.f0ed84p125F); }

    /**
     * This color constant "Robin Egg Blue" has RGBA8888 code {@code 08DED5FF}, L 0.7254902, A 0.42745098, B 0.4862745, alpha 1.0, hue 0.5297675, and saturation 0.9451003.
     * It can be represented as a packed float with the constant {@code -0x1.f8db72p125F}.
     * <pre>
     * <font style='background-color: #08DED5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #08DED5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #08DED5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #08DED5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #08DED5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #08DED5'>&nbsp;@&nbsp;</font><font style='background-color: #08DED5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #08DED5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #08DED5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ROBIN_EGG_BLUE = -0x1.f8db72p125F;
    static { NAMED.put("Robin Egg Blue", -0x1.f8db72p125F); LIST.add(-0x1.f8db72p125F); }

    /**
     * This color constant "Denim Blue" has RGBA8888 code {@code 109CDEFF}, L 0.54901963, A 0.45490196, B 0.4392157, alpha 1.0, hue 0.6483954, and saturation 0.9936383.
     * It can be represented as a packed float with the constant {@code -0x1.e0e918p125F}.
     * <pre>
     * <font style='background-color: #109CDE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #109CDE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #109CDE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #109CDE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #109CDE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #109CDE'>&nbsp;@&nbsp;</font><font style='background-color: #109CDE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #109CDE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #109CDE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DENIM_BLUE = -0x1.e0e918p125F;
    static { NAMED.put("Denim Blue", -0x1.e0e918p125F); LIST.add(-0x1.e0e918p125F); }

    /**
     * This color constant "Deep Teal" has RGBA8888 code {@code 055A5CFF}, L 0.32156864, A 0.4627451, B 0.4862745, alpha 1.0, hue 0.55616736, and saturation 0.84699434.
     * It can be represented as a packed float with the constant {@code -0x1.f8eca4p125F}.
     * <pre>
     * <font style='background-color: #055A5C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #055A5C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #055A5C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #055A5C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #055A5C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #055A5C'>&nbsp;@&nbsp;</font><font style='background-color: #055A5C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #055A5C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #055A5C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_TEAL = -0x1.f8eca4p125F;
    static { NAMED.put("Deep Teal", -0x1.f8eca4p125F); LIST.add(-0x1.f8eca4p125F); }

    /**
     * This color constant "Navy Blue" has RGBA8888 code {@code 162C52FF}, L 0.20784314, A 0.49019608, B 0.45882353, alpha 1.0, hue 0.7127991, and saturation 0.5285778.
     * It can be represented as a packed float with the constant {@code -0x1.eafa6ap125F}.
     * <pre>
     * <font style='background-color: #162C52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #162C52; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #162C52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #162C52'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #162C52'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #162C52'>&nbsp;@&nbsp;</font><font style='background-color: #162C52; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #162C52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #162C52; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float NAVY_BLUE = -0x1.eafa6ap125F;
    static { NAMED.put("Navy Blue", -0x1.eafa6ap125F); LIST.add(-0x1.eafa6ap125F); }

    /**
     * This color constant "Blueberry" has RGBA8888 code {@code 0F377DFF}, L 0.25490198, A 0.4862745, B 0.43137255, alpha 1.0, hue 0.71857655, and saturation 0.7789807.
     * It can be represented as a packed float with the constant {@code -0x1.dcf882p125F}.
     * <pre>
     * <font style='background-color: #0F377D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F377D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F377D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0F377D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0F377D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0F377D'>&nbsp;@&nbsp;</font><font style='background-color: #0F377D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F377D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F377D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUEBERRY = -0x1.dcf882p125F;
    static { NAMED.put("Blueberry", -0x1.dcf882p125F); LIST.add(-0x1.dcf882p125F); }

    /**
     * This color constant "Prussian Blue" has RGBA8888 code {@code 004A9CFF}, L 0.3137255, A 0.47843137, B 0.42352942, alpha 1.0, hue 0.70625466, and saturation 0.9685835.
     * It can be represented as a packed float with the constant {@code -0x1.d8f4ap125F}.
     * <pre>
     * <font style='background-color: #004A9C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #004A9C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #004A9C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #004A9C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #004A9C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #004A9C'>&nbsp;@&nbsp;</font><font style='background-color: #004A9C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #004A9C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #004A9C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PRUSSIAN_BLUE = -0x1.d8f4ap125F;
    static { NAMED.put("Prussian Blue", -0x1.d8f4ap125F); LIST.add(-0x1.d8f4ap125F); }

    /**
     * This color constant "Desert Rain" has RGBA8888 code {@code 326496FF}, L 0.38431373, A 0.47843137, B 0.4509804, alpha 1.0, hue 0.6840373, and saturation 0.68550277.
     * It can be represented as a packed float with the constant {@code -0x1.e6f4c4p125F}.
     * <pre>
     * <font style='background-color: #326496;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #326496; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #326496;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #326496'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #326496'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #326496'>&nbsp;@&nbsp;</font><font style='background-color: #326496; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #326496;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #326496; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DESERT_RAIN = -0x1.e6f4c4p125F;
    static { NAMED.put("Desert Rain", -0x1.e6f4c4p125F); LIST.add(-0x1.e6f4c4p125F); }

    /**
     * This color constant "Electric Blue" has RGBA8888 code {@code 0052F6FF}, L 0.39607844, A 0.48235294, B 0.3764706, alpha 1.0, hue 0.72740346, and saturation 0.9259301.
     * It can be represented as a packed float with the constant {@code -0x1.c0f6cap125F}.
     * <pre>
     * <font style='background-color: #0052F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0052F6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0052F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0052F6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0052F6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0052F6'>&nbsp;@&nbsp;</font><font style='background-color: #0052F6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0052F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0052F6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ELECTRIC_BLUE = -0x1.c0f6cap125F;
    static { NAMED.put("Electric Blue", -0x1.c0f6cap125F); LIST.add(-0x1.c0f6cap125F); }

    /**
     * This color constant "Hidden Blue" has RGBA8888 code {@code 186ABDFF}, L 0.4117647, A 0.4745098, B 0.42745098, alpha 1.0, hue 0.696238, and saturation 0.8558943.
     * It can be represented as a packed float with the constant {@code -0x1.daf2d2p125F}.
     * <pre>
     * <font style='background-color: #186ABD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #186ABD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #186ABD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #186ABD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #186ABD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #186ABD'>&nbsp;@&nbsp;</font><font style='background-color: #186ABD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #186ABD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #186ABD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float HIDDEN_BLUE = -0x1.daf2d2p125F;
    static { NAMED.put("Hidden Blue", -0x1.daf2d2p125F); LIST.add(-0x1.daf2d2p125F); }

    /**
     * This color constant "Dull Azure" has RGBA8888 code {@code 2378DCFF}, L 0.4627451, A 0.4745098, B 0.41960785, alpha 1.0, hue 0.70114374, and saturation 0.8466726.
     * It can be represented as a packed float with the constant {@code -0x1.d6f2ecp125F}.
     * <pre>
     * <font style='background-color: #2378DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2378DC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2378DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2378DC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2378DC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2378DC'>&nbsp;@&nbsp;</font><font style='background-color: #2378DC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2378DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2378DC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_AZURE = -0x1.d6f2ecp125F;
    static { NAMED.put("Dull Azure", -0x1.d6f2ecp125F); LIST.add(-0x1.d6f2ecp125F); }

    /**
     * This color constant "Ripped Denim" has RGBA8888 code {@code 699DC3FF}, L 0.57254905, A 0.47843137, B 0.46666667, alpha 1.0, hue 0.65858525, and saturation 0.472741.
     * It can be represented as a packed float with the constant {@code -0x1.eef524p125F}.
     * <pre>
     * <font style='background-color: #699DC3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #699DC3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #699DC3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #699DC3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #699DC3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #699DC3'>&nbsp;@&nbsp;</font><font style='background-color: #699DC3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #699DC3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #699DC3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float RIPPED_DENIM = -0x1.eef524p125F;
    static { NAMED.put("Ripped Denim", -0x1.eef524p125F); LIST.add(-0x1.eef524p125F); }

    /**
     * This color constant "Calm Sky" has RGBA8888 code {@code 4AA4FFFF}, L 0.6039216, A 0.47058824, B 0.42745098, alpha 1.0, hue 0.6887114, and saturation 0.9775976.
     * It can be represented as a packed float with the constant {@code -0x1.daf134p125F}.
     * <pre>
     * <font style='background-color: #4AA4FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4AA4FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4AA4FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4AA4FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4AA4FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4AA4FF'>&nbsp;@&nbsp;</font><font style='background-color: #4AA4FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4AA4FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4AA4FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CALM_SKY = -0x1.daf134p125F;
    static { NAMED.put("Calm Sky", -0x1.daf134p125F); LIST.add(-0x1.daf134p125F); }

    /**
     * This color constant "Vapor" has RGBA8888 code {@code 90B0FFFF}, L 0.6784314, A 0.49411765, B 0.44313726, alpha 1.0, hue 0.7335864, and saturation 0.9756364.
     * It can be represented as a packed float with the constant {@code -0x1.e2fd5ap125F}.
     * <pre>
     * <font style='background-color: #90B0FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #90B0FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #90B0FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #90B0FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #90B0FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #90B0FF'>&nbsp;@&nbsp;</font><font style='background-color: #90B0FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #90B0FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #90B0FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VAPOR = -0x1.e2fd5ap125F;
    static { NAMED.put("Vapor", -0x1.e2fd5ap125F); LIST.add(-0x1.e2fd5ap125F); }

    /**
     * This color constant "Powder Blue" has RGBA8888 code {@code 5AC5FFFF}, L 0.69803923, A 0.4627451, B 0.4509804, alpha 1.0, hue 0.6465574, and saturation 0.9271699.
     * It can be represented as a packed float with the constant {@code -0x1.e6ed64p125F}.
     * <pre>
     * <font style='background-color: #5AC5FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5AC5FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5AC5FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5AC5FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5AC5FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5AC5FF'>&nbsp;@&nbsp;</font><font style='background-color: #5AC5FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5AC5FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5AC5FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float POWDER_BLUE = -0x1.e6ed64p125F;
    static { NAMED.put("Powder Blue", -0x1.e6ed64p125F); LIST.add(-0x1.e6ed64p125F); }

    /**
     * This color constant "Suds" has RGBA8888 code {@code BEB9FAFF}, L 0.7372549, A 0.50980395, B 0.45882353, alpha 1.0, hue 0.7872009, and saturation 0.8668676.
     * It can be represented as a packed float with the constant {@code -0x1.eb0578p125F}.
     * <pre>
     * <font style='background-color: #BEB9FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BEB9FA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BEB9FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BEB9FA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BEB9FA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BEB9FA'>&nbsp;@&nbsp;</font><font style='background-color: #BEB9FA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BEB9FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BEB9FA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SUDS = -0x1.eb0578p125F;
    static { NAMED.put("Suds", -0x1.eb0578p125F); LIST.add(-0x1.eb0578p125F); }

    /**
     * This color constant "Strong Cyan" has RGBA8888 code {@code 00BFFFFF}, L 0.65882355, A 0.44705883, B 0.44313726, alpha 1.0, hue 0.63067734, and saturation 0.97021.
     * It can be represented as a packed float with the constant {@code -0x1.e2e55p125F}.
     * <pre>
     * <font style='background-color: #00BFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00BFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00BFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00BFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00BFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00BFFF'>&nbsp;@&nbsp;</font><font style='background-color: #00BFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00BFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00BFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_CYAN = -0x1.e2e55p125F;
    static { NAMED.put("Strong Cyan", -0x1.e2e55p125F); LIST.add(-0x1.e2e55p125F); }

    /**
     * This color constant "Sharp Azure" has RGBA8888 code {@code 007FFFFF}, L 0.49803922, A 0.47058824, B 0.4, alpha 1.0, hue 0.704483, and saturation 0.98830754.
     * It can be represented as a packed float with the constant {@code -0x1.ccf0fep125F}.
     * <pre>
     * <font style='background-color: #007FFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007FFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007FFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #007FFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #007FFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #007FFF'>&nbsp;@&nbsp;</font><font style='background-color: #007FFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007FFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007FFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SHARP_AZURE = -0x1.ccf0fep125F;
    static { NAMED.put("Sharp Azure", -0x1.ccf0fep125F); LIST.add(-0x1.ccf0fep125F); }

    /**
     * This color constant "Blue Eye" has RGBA8888 code {@code 4B7DC8FF}, L 0.47843137, A 0.48235294, B 0.4392157, alpha 1.0, hue 0.70503896, and saturation 0.60012245.
     * It can be represented as a packed float with the constant {@code -0x1.e0f6f4p125F}.
     * <pre>
     * <font style='background-color: #4B7DC8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B7DC8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B7DC8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4B7DC8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4B7DC8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4B7DC8'>&nbsp;@&nbsp;</font><font style='background-color: #4B7DC8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B7DC8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B7DC8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUE_EYE = -0x1.e0f6f4p125F;
    static { NAMED.put("Blue Eye", -0x1.e0f6f4p125F); LIST.add(-0x1.e0f6f4p125F); }

    /**
     * This color constant "Subtlety" has RGBA8888 code {@code 786EF0FF}, L 0.5019608, A 0.5176471, B 0.4117647, alpha 1.0, hue 0.78142345, and saturation 0.85316926.
     * It can be represented as a packed float with the constant {@code -0x1.d309p125F}.
     * <pre>
     * <font style='background-color: #786EF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #786EF0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #786EF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #786EF0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #786EF0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #786EF0'>&nbsp;@&nbsp;</font><font style='background-color: #786EF0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #786EF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #786EF0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SUBTLETY = -0x1.d309p125F;
    static { NAMED.put("Subtlety", -0x1.d309p125F); LIST.add(-0x1.d309p125F); }

    /**
     * This color constant "Rough Sapphire" has RGBA8888 code {@code 4A5AFFFF}, L 0.44313726, A 0.5019608, B 0.38431373, alpha 1.0, hue 0.75268954, and saturation 0.9711456.
     * It can be represented as a packed float with the constant {@code -0x1.c500e2p125F}.
     * <pre>
     * <font style='background-color: #4A5AFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A5AFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A5AFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4A5AFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4A5AFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4A5AFF'>&nbsp;@&nbsp;</font><font style='background-color: #4A5AFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A5AFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A5AFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ROUGH_SAPPHIRE = -0x1.c500e2p125F;
    static { NAMED.put("Rough Sapphire", -0x1.c500e2p125F); LIST.add(-0x1.c500e2p125F); }

    /**
     * This color constant "Iris" has RGBA8888 code {@code 6241F6FF}, L 0.4117647, A 0.5294118, B 0.38039216, alpha 1.0, hue 0.7883731, and saturation 0.9139643.
     * It can be represented as a packed float with the constant {@code -0x1.c30ed2p125F}.
     * <pre>
     * <font style='background-color: #6241F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6241F6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6241F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6241F6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6241F6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6241F6'>&nbsp;@&nbsp;</font><font style='background-color: #6241F6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6241F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6241F6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float IRIS = -0x1.c30ed2p125F;
    static { NAMED.put("Iris", -0x1.c30ed2p125F); LIST.add(-0x1.c30ed2p125F); }

    /**
     * This color constant "Cornflower Blue" has RGBA8888 code {@code 3C3CF5FF}, L 0.3764706, A 0.5058824, B 0.36862746, alpha 1.0, hue 0.75710976, and saturation 0.9223307.
     * It can be represented as a packed float with the constant {@code -0x1.bd02cp125F}.
     * <pre>
     * <font style='background-color: #3C3CF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C3CF5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C3CF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3C3CF5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3C3CF5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3C3CF5'>&nbsp;@&nbsp;</font><font style='background-color: #3C3CF5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C3CF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C3CF5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CORNFLOWER_BLUE = -0x1.bd02cp125F;
    static { NAMED.put("Cornflower Blue", -0x1.bd02cp125F); LIST.add(-0x1.bd02cp125F); }

    /**
     * This color constant "Polished Sapphire" has RGBA8888 code {@code 101CDAFF}, L 0.29411766, A 0.4862745, B 0.3647059, alpha 1.0, hue 0.73390174, and saturation 0.8703268.
     * It can be represented as a packed float with the constant {@code -0x1.baf896p125F}.
     * <pre>
     * <font style='background-color: #101CDA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #101CDA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #101CDA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #101CDA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #101CDA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #101CDA'>&nbsp;@&nbsp;</font><font style='background-color: #101CDA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #101CDA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #101CDA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float POLISHED_SAPPHIRE = -0x1.baf896p125F;
    static { NAMED.put("Polished Sapphire", -0x1.baf896p125F); LIST.add(-0x1.baf896p125F); }

    /**
     * This color constant "Royal Blue" has RGBA8888 code {@code 0010BDFF}, L 0.25490198, A 0.4862745, B 0.37254903, alpha 1.0, hue 0.7329173, and saturation 0.91155857.
     * It can be represented as a packed float with the constant {@code -0x1.bef882p125F}.
     * <pre>
     * <font style='background-color: #0010BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0010BD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0010BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0010BD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0010BD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0010BD'>&nbsp;@&nbsp;</font><font style='background-color: #0010BD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0010BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0010BD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ROYAL_BLUE = -0x1.bef882p125F;
    static { NAMED.put("Royal Blue", -0x1.bef882p125F); LIST.add(-0x1.bef882p125F); }

    /**
     * This color constant "Indigo" has RGBA8888 code {@code 231094FF}, L 0.22352941, A 0.5058824, B 0.39607844, alpha 1.0, hue 0.75899065, and saturation 0.873656.
     * It can be represented as a packed float with the constant {@code -0x1.cb0272p125F}.
     * <pre>
     * <font style='background-color: #231094;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #231094; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #231094;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #231094'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #231094'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #231094'>&nbsp;@&nbsp;</font><font style='background-color: #231094; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #231094;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #231094; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float INDIGO = -0x1.cb0272p125F;
    static { NAMED.put("Indigo", -0x1.cb0272p125F); LIST.add(-0x1.cb0272p125F); }

    /**
     * This color constant "Space Blue" has RGBA8888 code {@code 0C2148FF}, L 0.16862746, A 0.49019608, B 0.45490196, alpha 1.0, hue 0.7159276, and saturation 0.4074053.
     * It can be represented as a packed float with the constant {@code -0x1.e8fa56p125F}.
     * <pre>
     * <font style='background-color: #0C2148;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0C2148; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0C2148;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0C2148'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0C2148'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0C2148'>&nbsp;@&nbsp;</font><font style='background-color: #0C2148; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0C2148;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0C2148; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SPACE_BLUE = -0x1.e8fa56p125F;
    static { NAMED.put("Space Blue", -0x1.e8fa56p125F); LIST.add(-0x1.e8fa56p125F); }

    /**
     * This color constant "Thick Amethyst" has RGBA8888 code {@code 5010B0FF}, L 0.2901961, A 0.5411765, B 0.39215687, alpha 1.0, hue 0.8080369, and saturation 0.90928644.
     * It can be represented as a packed float with the constant {@code -0x1.c91494p125F}.
     * <pre>
     * <font style='background-color: #5010B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5010B0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5010B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5010B0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5010B0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5010B0'>&nbsp;@&nbsp;</font><font style='background-color: #5010B0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5010B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5010B0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float THICK_AMETHYST = -0x1.c91494p125F;
    static { NAMED.put("Thick Amethyst", -0x1.c91494p125F); LIST.add(-0x1.c91494p125F); }

    /**
     * This color constant "Juicy Grape" has RGBA8888 code {@code 6010D0FF}, L 0.33333334, A 0.54901963, B 0.38039216, alpha 1.0, hue 0.81189364, and saturation 0.932151.
     * It can be represented as a packed float with the constant {@code -0x1.c318aap125F}.
     * <pre>
     * <font style='background-color: #6010D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6010D0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6010D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6010D0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6010D0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6010D0'>&nbsp;@&nbsp;</font><font style='background-color: #6010D0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6010D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6010D0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float JUICY_GRAPE = -0x1.c318aap125F;
    static { NAMED.put("Juicy Grape", -0x1.c318aap125F); LIST.add(-0x1.c318aap125F); }

    /**
     * This color constant "Blacklight Glow" has RGBA8888 code {@code 8732D2FF}, L 0.40392157, A 0.5647059, B 0.40392157, alpha 1.0, hue 0.84434295, and saturation 0.7803671.
     * It can be represented as a packed float with the constant {@code -0x1.cf20cep125F}.
     * <pre>
     * <font style='background-color: #8732D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8732D2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8732D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8732D2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8732D2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8732D2'>&nbsp;@&nbsp;</font><font style='background-color: #8732D2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8732D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8732D2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACKLIGHT_GLOW = -0x1.cf20cep125F;
    static { NAMED.put("Blacklight Glow", -0x1.cf20cep125F); LIST.add(-0x1.cf20cep125F); }

    /**
     * This color constant "Purple Freesia" has RGBA8888 code {@code 9C41FFFF}, L 0.4745098, A 0.5686275, B 0.3882353, alpha 1.0, hue 0.83765215, and saturation 0.98750407.
     * It can be represented as a packed float with the constant {@code -0x1.c722f2p125F}.
     * <pre>
     * <font style='background-color: #9C41FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C41FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C41FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9C41FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9C41FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9C41FF'>&nbsp;@&nbsp;</font><font style='background-color: #9C41FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C41FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C41FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURPLE_FREESIA = -0x1.c722f2p125F;
    static { NAMED.put("Purple Freesia", -0x1.c722f2p125F); LIST.add(-0x1.c722f2p125F); }

    /**
     * This color constant "Thin Amethyst" has RGBA8888 code {@code 7F00FFFF}, L 0.4, A 0.5647059, B 0.3647059, alpha 1.0, hue 0.8209959, and saturation 0.98442614.
     * It can be represented as a packed float with the constant {@code -0x1.bb20ccp125F}.
     * <pre>
     * <font style='background-color: #7F00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F00FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7F00FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7F00FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7F00FF'>&nbsp;@&nbsp;</font><font style='background-color: #7F00FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F00FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float THIN_AMETHYST = -0x1.bb20ccp125F;
    static { NAMED.put("Thin Amethyst", -0x1.bb20ccp125F); LIST.add(-0x1.bb20ccp125F); }

    /**
     * This color constant "Orchid" has RGBA8888 code {@code BD62FFFF}, L 0.56078434, A 0.5686275, B 0.4117647, alpha 1.0, hue 0.8552204, and saturation 1.0040761.
     * It can be represented as a packed float with the constant {@code -0x1.d3231ep125F}.
     * <pre>
     * <font style='background-color: #BD62FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD62FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD62FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BD62FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BD62FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BD62FF'>&nbsp;@&nbsp;</font><font style='background-color: #BD62FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD62FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD62FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ORCHID = -0x1.d3231ep125F;
    static { NAMED.put("Orchid", -0x1.d3231ep125F); LIST.add(-0x1.d3231ep125F); }

    /**
     * This color constant "Lavender" has RGBA8888 code {@code B991FFFF}, L 0.6431373, A 0.53333336, B 0.43529412, alpha 1.0, hue 0.82570946, and saturation 0.9555641.
     * It can be represented as a packed float with the constant {@code -0x1.df1148p125F}.
     * <pre>
     * <font style='background-color: #B991FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B991FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B991FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B991FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B991FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B991FF'>&nbsp;@&nbsp;</font><font style='background-color: #B991FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B991FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B991FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LAVENDER = -0x1.df1148p125F;
    static { NAMED.put("Lavender", -0x1.df1148p125F); LIST.add(-0x1.df1148p125F); }

    /**
     * This color constant "Lilac" has RGBA8888 code {@code D7A5FFFF}, L 0.72156864, A 0.5372549, B 0.4509804, alpha 1.0, hue 0.8534426, and saturation 0.955266.
     * It can be represented as a packed float with the constant {@code -0x1.e7137p125F}.
     * <pre>
     * <font style='background-color: #D7A5FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7A5FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7A5FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D7A5FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D7A5FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D7A5FF'>&nbsp;@&nbsp;</font><font style='background-color: #D7A5FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7A5FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7A5FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LILAC = -0x1.e7137p125F;
    static { NAMED.put("Lilac", -0x1.e7137p125F); LIST.add(-0x1.e7137p125F); }

    /**
     * This color constant "Soap" has RGBA8888 code {@code D7C3FAFF}, L 0.7882353, A 0.5176471, B 0.46666667, alpha 1.0, hue 0.8274942, and saturation 0.87776446.
     * It can be represented as a packed float with the constant {@code -0x1.ef0992p125F}.
     * <pre>
     * <font style='background-color: #D7C3FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7C3FA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7C3FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D7C3FA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D7C3FA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D7C3FA'>&nbsp;@&nbsp;</font><font style='background-color: #D7C3FA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7C3FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7C3FA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOAP = -0x1.ef0992p125F;
    static { NAMED.put("Soap", -0x1.ef0992p125F); LIST.add(-0x1.ef0992p125F); }

    /**
     * This color constant "Pink Tutu" has RGBA8888 code {@code F8C6FCFF}, L 0.8352941, A 0.5294118, B 0.4745098, alpha 1.0, hue 0.88634074, and saturation 0.8664032.
     * It can be represented as a packed float with the constant {@code -0x1.f30faap125F}.
     * <pre>
     * <font style='background-color: #F8C6FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F8C6FC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F8C6FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F8C6FC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F8C6FC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F8C6FC'>&nbsp;@&nbsp;</font><font style='background-color: #F8C6FC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F8C6FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F8C6FC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINK_TUTU = -0x1.f30faap125F;
    static { NAMED.put("Pink Tutu", -0x1.f30faap125F); LIST.add(-0x1.f30faap125F); }

    /**
     * This color constant "Thistle" has RGBA8888 code {@code E673FFFF}, L 0.6392157, A 0.5803922, B 0.43137255, alpha 1.0, hue 0.8875299, and saturation 0.98397696.
     * It can be represented as a packed float with the constant {@code -0x1.dd2946p125F}.
     * <pre>
     * <font style='background-color: #E673FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E673FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E673FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E673FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E673FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E673FF'>&nbsp;@&nbsp;</font><font style='background-color: #E673FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E673FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E673FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float THISTLE = -0x1.dd2946p125F;
    static { NAMED.put("Thistle", -0x1.dd2946p125F); LIST.add(-0x1.dd2946p125F); }

    /**
     * This color constant "Heliotrope" has RGBA8888 code {@code FF52FFFF}, L 0.627451, A 0.6117647, B 0.42745098, alpha 1.0, hue 0.90835387, and saturation 0.98872995.
     * It can be represented as a packed float with the constant {@code -0x1.db394p125F}.
     * <pre>
     * <font style='background-color: #FF52FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF52FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF52FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF52FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF52FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF52FF'>&nbsp;@&nbsp;</font><font style='background-color: #FF52FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF52FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF52FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float HELIOTROPE = -0x1.db394p125F;
    static { NAMED.put("Heliotrope", -0x1.db394p125F); LIST.add(-0x1.db394p125F); }

    /**
     * This color constant "Purple" has RGBA8888 code {@code DA20E0FF}, L 0.50980395, A 0.6156863, B 0.41960785, alpha 1.0, hue 0.90333164, and saturation 0.913023.
     * It can be represented as a packed float with the constant {@code -0x1.d73b04p125F}.
     * <pre>
     * <font style='background-color: #DA20E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA20E0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA20E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DA20E0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DA20E0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DA20E0'>&nbsp;@&nbsp;</font><font style='background-color: #DA20E0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA20E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA20E0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURPLE = -0x1.d73b04p125F;
    static { NAMED.put("Purple", -0x1.d73b04p125F); LIST.add(-0x1.d73b04p125F); }

    /**
     * This color constant "Wisteria" has RGBA8888 code {@code BD29FFFF}, L 0.49411765, A 0.59607846, B 0.39215687, alpha 1.0, hue 0.865835, and saturation 0.9993278.
     * It can be represented as a packed float with the constant {@code -0x1.c930fcp125F}.
     * <pre>
     * <font style='background-color: #BD29FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD29FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD29FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BD29FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BD29FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BD29FF'>&nbsp;@&nbsp;</font><font style='background-color: #BD29FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD29FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD29FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WISTERIA = -0x1.c930fcp125F;
    static { NAMED.put("Wisteria", -0x1.c930fcp125F); LIST.add(-0x1.c930fcp125F); }

    /**
     * This color constant "Medium Plum" has RGBA8888 code {@code BD10C5FF}, L 0.44313726, A 0.60784316, B 0.42352942, alpha 1.0, hue 0.9018202, and saturation 0.9272389.
     * It can be represented as a packed float with the constant {@code -0x1.d936e2p125F}.
     * <pre>
     * <font style='background-color: #BD10C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD10C5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD10C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BD10C5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BD10C5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BD10C5'>&nbsp;@&nbsp;</font><font style='background-color: #BD10C5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD10C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD10C5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MEDIUM_PLUM = -0x1.d936e2p125F;
    static { NAMED.put("Medium Plum", -0x1.d936e2p125F); LIST.add(-0x1.d936e2p125F); }

    /**
     * This color constant "Violet" has RGBA8888 code {@code 8C14BEFF}, L 0.37254903, A 0.58431375, B 0.40784314, alpha 1.0, hue 0.8679365, and saturation 0.9404742.
     * It can be represented as a packed float with the constant {@code -0x1.d12abep125F}.
     * <pre>
     * <font style='background-color: #8C14BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C14BE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C14BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8C14BE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8C14BE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8C14BE'>&nbsp;@&nbsp;</font><font style='background-color: #8C14BE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C14BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C14BE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIOLET = -0x1.d12abep125F;
    static { NAMED.put("Violet", -0x1.d12abep125F); LIST.add(-0x1.d12abep125F); }

    /**
     * This color constant "Grape Lollipop" has RGBA8888 code {@code 5A187BFF}, L 0.26666668, A 0.5568628, B 0.43529412, alpha 1.0, hue 0.8647537, and saturation 0.8018918.
     * It can be represented as a packed float with the constant {@code -0x1.df1c88p125F}.
     * <pre>
     * <font style='background-color: #5A187B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A187B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A187B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5A187B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5A187B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5A187B'>&nbsp;@&nbsp;</font><font style='background-color: #5A187B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A187B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A187B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAPE_LOLLIPOP = -0x1.df1c88p125F;
    static { NAMED.put("Grape Lollipop", -0x1.df1c88p125F); LIST.add(-0x1.df1c88p125F); }

    /**
     * This color constant "Mulberry" has RGBA8888 code {@code 641464FF}, L 0.2627451, A 0.5647059, B 0.45490196, alpha 1.0, hue 0.90311134, and saturation 0.7918062.
     * It can be represented as a packed float with the constant {@code -0x1.e92086p125F}.
     * <pre>
     * <font style='background-color: #641464;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #641464; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #641464;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #641464'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #641464'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #641464'>&nbsp;@&nbsp;</font><font style='background-color: #641464; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #641464;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #641464; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MULBERRY = -0x1.e92086p125F;
    static { NAMED.put("Mulberry", -0x1.e92086p125F); LIST.add(-0x1.e92086p125F); }

    /**
     * This color constant "Grape Soda" has RGBA8888 code {@code 410062FF}, L 0.20392157, A 0.54901963, B 0.43529412, alpha 1.0, hue 0.8531978, and saturation 0.86589247.
     * It can be represented as a packed float with the constant {@code -0x1.df1868p125F}.
     * <pre>
     * <font style='background-color: #410062;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #410062; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #410062;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #410062'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #410062'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #410062'>&nbsp;@&nbsp;</font><font style='background-color: #410062; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #410062;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #410062; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAPE_SODA = -0x1.df1868p125F;
    static { NAMED.put("Grape Soda", -0x1.df1868p125F); LIST.add(-0x1.df1868p125F); }

    /**
     * This color constant "Eggplant" has RGBA8888 code {@code 320A46FF}, L 0.16862746, A 0.5372549, B 0.4509804, alpha 1.0, hue 0.8534426, and saturation 0.7164495.
     * It can be represented as a packed float with the constant {@code -0x1.e71256p125F}.
     * <pre>
     * <font style='background-color: #320A46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #320A46; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #320A46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #320A46'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #320A46'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #320A46'>&nbsp;@&nbsp;</font><font style='background-color: #320A46; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #320A46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #320A46; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float EGGPLANT = -0x1.e71256p125F;
    static { NAMED.put("Eggplant", -0x1.e71256p125F); LIST.add(-0x1.e71256p125F); }

    /**
     * This color constant "Cherry Syrup" has RGBA8888 code {@code 551937FF}, L 0.22352941, A 0.5529412, B 0.49019608, alpha 1.0, hue 0.97084755, and saturation 0.6723596.
     * It can be represented as a packed float with the constant {@code -0x1.fb1a72p125F}.
     * <pre>
     * <font style='background-color: #551937;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #551937; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #551937;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #551937'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #551937'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #551937'>&nbsp;@&nbsp;</font><font style='background-color: #551937; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #551937;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #551937; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CHERRY_SYRUP = -0x1.fb1a72p125F;
    static { NAMED.put("Cherry Syrup", -0x1.fb1a72p125F); LIST.add(-0x1.fb1a72p125F); }

    /**
     * This color constant "Plum Juice" has RGBA8888 code {@code A01982FF}, L 0.36862746, A 0.59607846, B 0.45882353, alpha 1.0, hue 0.9355687, and saturation 0.89199126.
     * It can be represented as a packed float with the constant {@code -0x1.eb30bcp125F}.
     * <pre>
     * <font style='background-color: #A01982;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A01982; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A01982;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A01982'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A01982'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A01982'>&nbsp;@&nbsp;</font><font style='background-color: #A01982; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A01982;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A01982; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PLUM_JUICE = -0x1.eb30bcp125F;
    static { NAMED.put("Plum Juice", -0x1.eb30bcp125F); LIST.add(-0x1.eb30bcp125F); }

    /**
     * This color constant "Fruit Punch" has RGBA8888 code {@code C80078FF}, L 0.41960785, A 0.6117647, B 0.48235294, alpha 1.0, hue 0.9750635, and saturation 0.9343944.
     * It can be represented as a packed float with the constant {@code -0x1.f738d6p125F}.
     * <pre>
     * <font style='background-color: #C80078;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C80078; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C80078;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C80078'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C80078'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C80078'>&nbsp;@&nbsp;</font><font style='background-color: #C80078; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C80078;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C80078; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FRUIT_PUNCH = -0x1.f738d6p125F;
    static { NAMED.put("Fruit Punch", -0x1.f738d6p125F); LIST.add(-0x1.f738d6p125F); }

    /**
     * This color constant "Bubble Gum" has RGBA8888 code {@code FF50BFFF}, L 0.5921569, A 0.60784316, B 0.46666667, alpha 1.0, hue 0.95230013, and saturation 0.947428.
     * It can be represented as a packed float with the constant {@code -0x1.ef372ep125F}.
     * <pre>
     * <font style='background-color: #FF50BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF50BF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF50BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF50BF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF50BF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF50BF'>&nbsp;@&nbsp;</font><font style='background-color: #FF50BF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF50BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF50BF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BUBBLE_GUM = -0x1.ef372ep125F;
    static { NAMED.put("Bubble Gum", -0x1.ef372ep125F); LIST.add(-0x1.ef372ep125F); }

    /**
     * This color constant "Pink Lemonade" has RGBA8888 code {@code FF6AC5FF}, L 0.6313726, A 0.5921569, B 0.47058824, alpha 1.0, hue 0.95084393, and saturation 0.9524819.
     * It can be represented as a packed float with the constant {@code -0x1.f12f42p125F}.
     * <pre>
     * <font style='background-color: #FF6AC5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6AC5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6AC5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF6AC5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF6AC5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF6AC5'>&nbsp;@&nbsp;</font><font style='background-color: #FF6AC5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6AC5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6AC5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINK_LEMONADE = -0x1.f12f42p125F;
    static { NAMED.put("Pink Lemonade", -0x1.f12f42p125F); LIST.add(-0x1.f12f42p125F); }

    /**
     * This color constant "Shrimp" has RGBA8888 code {@code FAA0B9FF}, L 0.72156864, A 0.54901963, B 0.49803922, alpha 1.0, hue 0.99364984, and saturation 0.86614174.
     * It can be represented as a packed float with the constant {@code -0x1.ff197p125F}.
     * <pre>
     * <font style='background-color: #FAA0B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FAA0B9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FAA0B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FAA0B9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FAA0B9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FAA0B9'>&nbsp;@&nbsp;</font><font style='background-color: #FAA0B9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FAA0B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FAA0B9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SHRIMP = -0x1.ff197p125F;
    static { NAMED.put("Shrimp", -0x1.ff197p125F); LIST.add(-0x1.ff197p125F); }

    /**
     * This color constant "Flamingo" has RGBA8888 code {@code FC3A8CFF}, L 0.5411765, A 0.6156863, B 0.49411765, alpha 1.0, hue 0.99192464, and saturation 0.94139516.
     * It can be represented as a packed float with the constant {@code -0x1.fd3b14p125F}.
     * <pre>
     * <font style='background-color: #FC3A8C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FC3A8C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FC3A8C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FC3A8C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FC3A8C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FC3A8C'>&nbsp;@&nbsp;</font><font style='background-color: #FC3A8C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FC3A8C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FC3A8C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FLAMINGO = -0x1.fd3b14p125F;
    static { NAMED.put("Flamingo", -0x1.fd3b14p125F); LIST.add(-0x1.fd3b14p125F); }

    /**
     * This color constant "Rose" has RGBA8888 code {@code E61E78FF}, L 0.47843137, A 0.6156863, B 0.49411765, alpha 1.0, hue 0.99192464, and saturation 0.89860445.
     * It can be represented as a packed float with the constant {@code -0x1.fd3af4p125F}.
     * <pre>
     * <font style='background-color: #E61E78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E61E78; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E61E78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E61E78'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E61E78'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E61E78'>&nbsp;@&nbsp;</font><font style='background-color: #E61E78; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E61E78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E61E78; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ROSE = -0x1.fd3af4p125F;
    static { NAMED.put("Rose", -0x1.fd3af4p125F); LIST.add(-0x1.fd3af4p125F); }

    /**
     * This color constant "Carmine" has RGBA8888 code {@code BD1039FF}, L 0.38431373, A 0.59607846, B 0.5254902, alpha 1.0, hue 0.04126853, and saturation 0.925345.
     * It can be represented as a packed float with the constant {@code -0x1.0d30c4p126F}.
     * <pre>
     * <font style='background-color: #BD1039;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD1039; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD1039;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BD1039'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BD1039'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BD1039'>&nbsp;@&nbsp;</font><font style='background-color: #BD1039; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD1039;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD1039; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CARMINE = -0x1.0d30c4p126F;
    static { NAMED.put("Carmine", -0x1.0d30c4p126F); LIST.add(-0x1.0d30c4p126F); }

    /**
     * This color constant "Bologna" has RGBA8888 code {@code 98344DFF}, L 0.36078432, A 0.5686275, B 0.5058824, alpha 1.0, hue 0.01361084, and saturation 0.6530758.
     * It can be represented as a packed float with the constant {@code -0x1.0322b8p126F}.
     * <pre>
     * <font style='background-color: #98344D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #98344D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #98344D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #98344D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #98344D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #98344D'>&nbsp;@&nbsp;</font><font style='background-color: #98344D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #98344D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #98344D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLOGNA = -0x1.0322b8p126F;
    static { NAMED.put("Bologna", -0x1.0322b8p126F); LIST.add(-0x1.0322b8p126F); }

    /**
     * This color constant "Raspberry" has RGBA8888 code {@code 911437FF}, L 0.3137255, A 0.5803922, B 0.5137255, alpha 1.0, hue 0.02692472, and saturation 0.8699246.
     * It can be represented as a packed float with the constant {@code -0x1.0728ap126F}.
     * <pre>
     * <font style='background-color: #911437;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #911437; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #911437;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #911437'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #911437'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #911437'>&nbsp;@&nbsp;</font><font style='background-color: #911437; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #911437;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #911437; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float RASPBERRY = -0x1.0728ap126F;
    static { NAMED.put("Raspberry", -0x1.0728ap126F); LIST.add(-0x1.0728ap126F); }

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
                final float c1 = NAMED.get(o1, TRANSPARENT), c2 = NAMED.get(o2, TRANSPARENT);
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
                return Float.compare(ColorTools.channelL(NAMED.get(o1, TRANSPARENT)), ColorTools.channelL(NAMED.get(o2, TRANSPARENT)));
            }
        });
    }

    /**
     * Changes the existing RGBA Color instances in {@link Colors} to use Oklab and so be able to be shown normally by
     * {@link ColorfulBatch} or a Batch using {@link com.github.tommyettinger.colorful.Shaders#fragmentShaderOklab}.
     * Any colors used in libGDX text markup look up their values in Colors, so calling this can help display fonts
     * where markup is enabled. This only needs to be called once, and if you call {@link #appendToKnownColors()}, then
     * that should be done after this to avoid mixing RGBA and Oklab colors.
     * <br>
     * This is a duplicate of a method with the same name in SimplePalette; you should still only call this method once,
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
     * are "Title Cased" instead of "ALL UPPER CASE"). If you intend to still use the existing values in Colors, you
     * should call {@link #editKnownColors()} first; otherwise you can just always use "Title Cased" color names.
     * <br>
     * This can be used alongside the method with the same name in SimplePalette, since that uses "lower cased" names.
     */
    public static void appendToKnownColors(){
        for(ObjectFloatMap.Entry<String> ent : NAMED) {
            final float f = ent.value;
            Colors.put(ent.key, new Color(channelL(f), channelA(f), channelB(f), alpha(f)));
        }
    }
}
