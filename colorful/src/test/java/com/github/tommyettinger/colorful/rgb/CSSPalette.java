package com.github.tommyettinger.colorful.rgb;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.ObjectFloatMap;

/**
 * A palette of predefined colors as packed RGB(A) floats, which can be used with setPackedColor() in SpriteBatch.
 * You can access colors by their constant name, such as {@code OCEAN_BLUE}, by the {@link #NAMED} map using
 * {@code NAMED.get("Ocean Blue", 0f)}, or by index in the FloatArray called {@link #LIST}. Note that to access a float
 * color from NAMED, you need to give a default value if the name is not found; {@code 0f} is a good default because it
 * is the same as fully-transparent black. You can access the names in a specific order with {@link #NAMES} (which is
 * alphabetical). Having a name lets you look up the matching color in {@link #NAMED}.
 * <br>
 * Created by Tommy Ettinger on 12/2/2020.
 */
public class CSSPalette {
    public static final ObjectFloatMap<String> NAMED = new ObjectFloatMap<String>(147);
    public static final FloatArray LIST = new FloatArray(147);

    /**
     * This color constant "aliceblue" has RGBA8888 code {@code F0F8FFFF}, hue 0.5777778, saturation 0.058823526, lightness 0.9705882, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.fff1ep126F}.
     * <pre>
     * <font style='background-color: #F0F8FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0F8FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0F8FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F0F8FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F0F8FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F0F8FF'>&nbsp;@&nbsp;</font><font style='background-color: #F0F8FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0F8FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0F8FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ALICEBLUE = -0x1.fff1ep126F;
    static { NAMED.put("aliceblue", -0x1.fff1ep126F); LIST.add(-0x1.fff1ep126F); }

    /**
     * This color constant "antiquewhite" has RGBA8888 code {@code FAEBD7FF}, hue 0.0952381, saturation 0.1372549, lightness 0.91176474, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.afd7f4p126F}.
     * <pre>
     * <font style='background-color: #FAEBD7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FAEBD7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FAEBD7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FAEBD7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FAEBD7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FAEBD7'>&nbsp;@&nbsp;</font><font style='background-color: #FAEBD7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FAEBD7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FAEBD7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ANTIQUEWHITE = -0x1.afd7f4p126F;
    static { NAMED.put("antiquewhite", -0x1.afd7f4p126F); LIST.add(-0x1.afd7f4p126F); }

    /**
     * This color constant "aqua" has RGBA8888 code {@code 00FFFFFF}, hue 0.5, saturation 1.0, lightness 0.5, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.fffep126F}.
     * <pre>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #00FFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float AQUA = -0x1.fffep126F;
    static { NAMED.put("aqua", -0x1.fffep126F); LIST.add(-0x1.fffep126F); }

    /**
     * This color constant "aquamarine" has RGBA8888 code {@code 7FFFD4FF}, hue 0.44401044, saturation 0.50196075, lightness 0.7490196, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.a9fefep126F}.
     * <pre>
     * <font style='background-color: #7FFFD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7FFFD4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7FFFD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7FFFD4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7FFFD4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7FFFD4'>&nbsp;@&nbsp;</font><font style='background-color: #7FFFD4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7FFFD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7FFFD4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float AQUAMARINE = -0x1.a9fefep126F;
    static { NAMED.put("aquamarine", -0x1.a9fefep126F); LIST.add(-0x1.a9fefep126F); }

    /**
     * This color constant "azure" has RGBA8888 code {@code F0FFFFFF}, hue 0.5, saturation 0.058823526, lightness 0.9705882, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.ffffep126F}.
     * <pre>
     * <font style='background-color: #F0FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0FFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F0FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F0FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F0FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #F0FFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0FFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float AZURE = -0x1.ffffep126F;
    static { NAMED.put("azure", -0x1.ffffep126F); LIST.add(-0x1.ffffep126F); }

    /**
     * This color constant "beige" has RGBA8888 code {@code F5F5DCFF}, hue 0.16666667, saturation 0.09803921, lightness 0.91176474, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.b9ebeap126F}.
     * <pre>
     * <font style='background-color: #F5F5DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5F5DC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5F5DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F5F5DC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F5F5DC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F5F5DC'>&nbsp;@&nbsp;</font><font style='background-color: #F5F5DC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5F5DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5F5DC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BEIGE = -0x1.b9ebeap126F;
    static { NAMED.put("beige", -0x1.b9ebeap126F); LIST.add(-0x1.b9ebeap126F); }

    /**
     * This color constant "bisque" has RGBA8888 code {@code FFE4C4FF}, hue 0.09039548, saturation 0.23137254, lightness 0.8843137, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.89c9fep126F}.
     * <pre>
     * <font style='background-color: #FFE4C4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFE4C4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFE4C4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFE4C4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFE4C4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFE4C4'>&nbsp;@&nbsp;</font><font style='background-color: #FFE4C4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFE4C4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFE4C4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BISQUE = -0x1.89c9fep126F;
    static { NAMED.put("bisque", -0x1.89c9fep126F); LIST.add(-0x1.89c9fep126F); }

    /**
     * This color constant "black" has RGBA8888 code {@code 000000FF}, hue 0.0, saturation 0.0, lightness 0.0, and alpha 1.0.
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
     * This color constant "blanchedalmond" has RGBA8888 code {@code FFEBCDFF}, hue 0.1, saturation 0.19607842, lightness 0.9019608, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.9bd7fep126F}.
     * <pre>
     * <font style='background-color: #FFEBCD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFEBCD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFEBCD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFEBCD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFEBCD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFEBCD'>&nbsp;@&nbsp;</font><font style='background-color: #FFEBCD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFEBCD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFEBCD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLANCHEDALMOND = -0x1.9bd7fep126F;
    static { NAMED.put("blanchedalmond", -0x1.9bd7fep126F); LIST.add(-0x1.9bd7fep126F); }

    /**
     * This color constant "blue" has RGBA8888 code {@code 0000FFFF}, hue 0.6666667, saturation 1.0, lightness 0.5, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.fep126F}.
     * <pre>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0000FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #0000FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0000FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUE = -0x1.fep126F;
    static { NAMED.put("blue", -0x1.fep126F); LIST.add(-0x1.fep126F); }

    /**
     * This color constant "blueviolet" has RGBA8888 code {@code 8A2BE2FF}, hue 0.75318766, saturation 0.7176471, lightness 0.527451, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.c45714p126F}.
     * <pre>
     * <font style='background-color: #8A2BE2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A2BE2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A2BE2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8A2BE2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8A2BE2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8A2BE2'>&nbsp;@&nbsp;</font><font style='background-color: #8A2BE2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A2BE2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A2BE2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUEVIOLET = -0x1.c45714p126F;
    static { NAMED.put("blueviolet", -0x1.c45714p126F); LIST.add(-0x1.c45714p126F); }

    /**
     * This color constant "brown" has RGBA8888 code {@code A52A2AFF}, hue 0.0, saturation 0.48235297, lightness 0.40588236, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.54554ap125F}.
     * <pre>
     * <font style='background-color: #A52A2A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A52A2A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A52A2A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A52A2A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A52A2A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A52A2A'>&nbsp;@&nbsp;</font><font style='background-color: #A52A2A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A52A2A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A52A2A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BROWN = -0x1.54554ap125F;
    static { NAMED.put("brown", -0x1.54554ap125F); LIST.add(-0x1.54554ap125F); }

    /**
     * This color constant "burlywood" has RGBA8888 code {@code DEB887FF}, hue 0.09386974, saturation 0.34117645, lightness 0.70000005, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.0f71bcp126F}.
     * <pre>
     * <font style='background-color: #DEB887;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEB887; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEB887;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DEB887'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DEB887'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DEB887'>&nbsp;@&nbsp;</font><font style='background-color: #DEB887; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEB887;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEB887; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BURLYWOOD = -0x1.0f71bcp126F;
    static { NAMED.put("burlywood", -0x1.0f71bcp126F); LIST.add(-0x1.0f71bcp126F); }

    /**
     * This color constant "cadetblue" has RGBA8888 code {@code 5F9EA0FF}, hue 0.5051282, saturation 0.25490198, lightness 0.5, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.413cbep126F}.
     * <pre>
     * <font style='background-color: #5F9EA0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5F9EA0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5F9EA0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5F9EA0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5F9EA0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5F9EA0'>&nbsp;@&nbsp;</font><font style='background-color: #5F9EA0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5F9EA0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5F9EA0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CADETBLUE = -0x1.413cbep126F;
    static { NAMED.put("cadetblue", -0x1.413cbep126F); LIST.add(-0x1.413cbep126F); }

    /**
     * This color constant "chartreuse" has RGBA8888 code {@code 7FFF00FF}, hue 0.2503268, saturation 1.0, lightness 0.5, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.01fefep125F}.
     * <pre>
     * <font style='background-color: #7FFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7FFF00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7FFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7FFF00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7FFF00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7FFF00'>&nbsp;@&nbsp;</font><font style='background-color: #7FFF00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7FFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7FFF00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CHARTREUSE = -0x1.01fefep125F;
    static { NAMED.put("chartreuse", -0x1.01fefep125F); LIST.add(-0x1.01fefep125F); }

    /**
     * This color constant "chocolate" has RGBA8888 code {@code D2691EFF}, hue 0.06944444, saturation 0.7058824, lightness 0.4705882, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.3cd3a4p125F}.
     * <pre>
     * <font style='background-color: #D2691E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2691E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2691E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D2691E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D2691E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D2691E'>&nbsp;@&nbsp;</font><font style='background-color: #D2691E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2691E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2691E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CHOCOLATE = -0x1.3cd3a4p125F;
    static { NAMED.put("chocolate", -0x1.3cd3a4p125F); LIST.add(-0x1.3cd3a4p125F); }

    /**
     * This color constant "coral" has RGBA8888 code {@code FF7F50FF}, hue 0.0447619, saturation 0.6862745, lightness 0.65686274, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.a0fffep125F}.
     * <pre>
     * <font style='background-color: #FF7F50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF7F50; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF7F50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF7F50'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF7F50'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF7F50'>&nbsp;@&nbsp;</font><font style='background-color: #FF7F50; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF7F50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF7F50; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CORAL = -0x1.a0fffep125F;
    static { NAMED.put("coral", -0x1.a0fffep125F); LIST.add(-0x1.a0fffep125F); }

    /**
     * This color constant "cornflowerblue" has RGBA8888 code {@code 6495EDFF}, hue 0.60705596, saturation 0.5372549, lightness 0.6607843, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.db2ac8p126F}.
     * <pre>
     * <font style='background-color: #6495ED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6495ED; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6495ED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6495ED'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6495ED'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6495ED'>&nbsp;@&nbsp;</font><font style='background-color: #6495ED; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6495ED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6495ED; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CORNFLOWERBLUE = -0x1.db2ac8p126F;
    static { NAMED.put("cornflowerblue", -0x1.db2ac8p126F); LIST.add(-0x1.db2ac8p126F); }

    /**
     * This color constant "cornsilk" has RGBA8888 code {@code FFF8DCFF}, hue 0.13333334, saturation 0.1372549, lightness 0.9313725, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.b9f1fep126F}.
     * <pre>
     * <font style='background-color: #FFF8DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFF8DC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFF8DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFF8DC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFF8DC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFF8DC'>&nbsp;@&nbsp;</font><font style='background-color: #FFF8DC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFF8DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFF8DC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CORNSILK = -0x1.b9f1fep126F;
    static { NAMED.put("cornsilk", -0x1.b9f1fep126F); LIST.add(-0x1.b9f1fep126F); }

    /**
     * This color constant "crimson" has RGBA8888 code {@code DC143CFF}, hue 0.9666667, saturation 0.78431374, lightness 0.4705882, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.7829b8p125F}.
     * <pre>
     * <font style='background-color: #DC143C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DC143C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DC143C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DC143C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DC143C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DC143C'>&nbsp;@&nbsp;</font><font style='background-color: #DC143C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DC143C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DC143C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CRIMSON = -0x1.7829b8p125F;
    static { NAMED.put("crimson", -0x1.7829b8p125F); LIST.add(-0x1.7829b8p125F); }

    /**
     * This color constant "cyan" has RGBA8888 code {@code 00FFFFFF}, hue 0.5, saturation 1.0, lightness 0.5, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.fffep126F}.
     * <pre>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #00FFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CYAN = -0x1.fffep126F;
    static { NAMED.put("cyan", -0x1.fffep126F); LIST.add(-0x1.fffep126F); }

    /**
     * This color constant "darkblue" has RGBA8888 code {@code 00008BFF}, hue 0.6666667, saturation 0.54509807, lightness 0.27254903, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.16p126F}.
     * <pre>
     * <font style='background-color: #00008B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00008B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00008B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00008B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00008B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00008B'>&nbsp;@&nbsp;</font><font style='background-color: #00008B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00008B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00008B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKBLUE = -0x1.16p126F;
    static { NAMED.put("darkblue", -0x1.16p126F); LIST.add(-0x1.16p126F); }

    /**
     * This color constant "darkcyan" has RGBA8888 code {@code 008B8BFF}, hue 0.5, saturation 0.54509807, lightness 0.27254903, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.1716p126F}.
     * <pre>
     * <font style='background-color: #008B8B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #008B8B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #008B8B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #008B8B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #008B8B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #008B8B'>&nbsp;@&nbsp;</font><font style='background-color: #008B8B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #008B8B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #008B8B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKCYAN = -0x1.1716p126F;
    static { NAMED.put("darkcyan", -0x1.1716p126F); LIST.add(-0x1.1716p126F); }

    /**
     * This color constant "darkgoldenrod" has RGBA8888 code {@code B8860BFF}, hue 0.11849712, saturation 0.6784314, lightness 0.38235295, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.170d7p125F}.
     * <pre>
     * <font style='background-color: #B8860B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B8860B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B8860B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B8860B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B8860B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B8860B'>&nbsp;@&nbsp;</font><font style='background-color: #B8860B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B8860B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B8860B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKGOLDENROD = -0x1.170d7p125F;
    static { NAMED.put("darkgoldenrod", -0x1.170d7p125F); LIST.add(-0x1.170d7p125F); }

    /**
     * This color constant "darkgray" has RGBA8888 code {@code A9A9A9FF}, hue 0.0, saturation 0.0, lightness 0.6627451, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.535352p126F}.
     * <pre>
     * <font style='background-color: #A9A9A9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A9A9A9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A9A9A9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A9A9A9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A9A9A9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A9A9A9'>&nbsp;@&nbsp;</font><font style='background-color: #A9A9A9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A9A9A9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A9A9A9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKGRAY = -0x1.535352p126F;
    static { NAMED.put("darkgray", -0x1.535352p126F); LIST.add(-0x1.535352p126F); }

    /**
     * This color constant "darkgreen" has RGBA8888 code {@code 006400FF}, hue 0.33333334, saturation 0.39215687, lightness 0.19607843, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.00c8p125F}.
     * <pre>
     * <font style='background-color: #006400;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #006400; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #006400;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #006400'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #006400'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #006400'>&nbsp;@&nbsp;</font><font style='background-color: #006400; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #006400;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #006400; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKGREEN = -0x1.00c8p125F;
    static { NAMED.put("darkgreen", -0x1.00c8p125F); LIST.add(-0x1.00c8p125F); }

    /**
     * This color constant "darkgrey" has RGBA8888 code {@code A9A9A9FF}, hue 0.0, saturation 0.0, lightness 0.6627451, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.535352p126F}.
     * <pre>
     * <font style='background-color: #A9A9A9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A9A9A9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A9A9A9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A9A9A9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A9A9A9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A9A9A9'>&nbsp;@&nbsp;</font><font style='background-color: #A9A9A9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A9A9A9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A9A9A9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKGREY = -0x1.535352p126F;
    static { NAMED.put("darkgrey", -0x1.535352p126F); LIST.add(-0x1.535352p126F); }

    /**
     * This color constant "darkkhaki" has RGBA8888 code {@code BDB76BFF}, hue 0.15447155, saturation 0.32156864, lightness 0.5803922, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.d76f7ap125F}.
     * <pre>
     * <font style='background-color: #BDB76B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BDB76B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BDB76B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BDB76B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BDB76B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BDB76B'>&nbsp;@&nbsp;</font><font style='background-color: #BDB76B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BDB76B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BDB76B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKKHAKI = -0x1.d76f7ap125F;
    static { NAMED.put("darkkhaki", -0x1.d76f7ap125F); LIST.add(-0x1.d76f7ap125F); }

    /**
     * This color constant "darkmagenta" has RGBA8888 code {@code 8B008BFF}, hue 0.8333333, saturation 0.54509807, lightness 0.27254903, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.160116p126F}.
     * <pre>
     * <font style='background-color: #8B008B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B008B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B008B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8B008B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8B008B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8B008B'>&nbsp;@&nbsp;</font><font style='background-color: #8B008B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B008B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B008B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKMAGENTA = -0x1.160116p126F;
    static { NAMED.put("darkmagenta", -0x1.160116p126F); LIST.add(-0x1.160116p126F); }

    /**
     * This color constant "darkolivegreen" has RGBA8888 code {@code 556B2FFF}, hue 0.22777778, saturation 0.23529412, lightness 0.3019608, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.5ed6aap125F}.
     * <pre>
     * <font style='background-color: #556B2F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #556B2F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #556B2F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #556B2F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #556B2F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #556B2F'>&nbsp;@&nbsp;</font><font style='background-color: #556B2F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #556B2F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #556B2F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKOLIVEGREEN = -0x1.5ed6aap125F;
    static { NAMED.put("darkolivegreen", -0x1.5ed6aap125F); LIST.add(-0x1.5ed6aap125F); }

    /**
     * This color constant "darkorange" has RGBA8888 code {@code FF8C00FF}, hue 0.09150327, saturation 1.0, lightness 0.5, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.0119fep125F}.
     * <pre>
     * <font style='background-color: #FF8C00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF8C00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF8C00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF8C00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF8C00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF8C00'>&nbsp;@&nbsp;</font><font style='background-color: #FF8C00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF8C00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF8C00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKORANGE = -0x1.0119fep125F;
    static { NAMED.put("darkorange", -0x1.0119fep125F); LIST.add(-0x1.0119fep125F); }

    /**
     * This color constant "darkorchid" has RGBA8888 code {@code 9932CCFF}, hue 0.7781386, saturation 0.6039216, lightness 0.49803922, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.986532p126F}.
     * <pre>
     * <font style='background-color: #9932CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9932CC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9932CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9932CC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9932CC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9932CC'>&nbsp;@&nbsp;</font><font style='background-color: #9932CC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9932CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9932CC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKORCHID = -0x1.986532p126F;
    static { NAMED.put("darkorchid", -0x1.986532p126F); LIST.add(-0x1.986532p126F); }

    /**
     * This color constant "darkred" has RGBA8888 code {@code 8B0000FF}, hue 0.0, saturation 0.54509807, lightness 0.27254903, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.000116p125F}.
     * <pre>
     * <font style='background-color: #8B0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B0000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8B0000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8B0000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8B0000'>&nbsp;@&nbsp;</font><font style='background-color: #8B0000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B0000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKRED = -0x1.000116p125F;
    static { NAMED.put("darkred", -0x1.000116p125F); LIST.add(-0x1.000116p125F); }

    /**
     * This color constant "darksalmon" has RGBA8888 code {@code E9967AFF}, hue 0.04204205, saturation 0.43529412, lightness 0.6960784, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.f52dd2p125F}.
     * <pre>
     * <font style='background-color: #E9967A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9967A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9967A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E9967A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E9967A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E9967A'>&nbsp;@&nbsp;</font><font style='background-color: #E9967A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9967A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9967A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKSALMON = -0x1.f52dd2p125F;
    static { NAMED.put("darksalmon", -0x1.f52dd2p125F); LIST.add(-0x1.f52dd2p125F); }

    /**
     * This color constant "darkseagreen" has RGBA8888 code {@code 8FBC8FFF}, hue 0.33333334, saturation 0.17647058, lightness 0.64901966, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.1f791ep126F}.
     * <pre>
     * <font style='background-color: #8FBC8F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8FBC8F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8FBC8F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8FBC8F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8FBC8F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8FBC8F'>&nbsp;@&nbsp;</font><font style='background-color: #8FBC8F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8FBC8F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8FBC8F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKSEAGREEN = -0x1.1f791ep126F;
    static { NAMED.put("darkseagreen", -0x1.1f791ep126F); LIST.add(-0x1.1f791ep126F); }

    /**
     * This color constant "darkslateblue" has RGBA8888 code {@code 483D8BFF}, hue 0.69017094, saturation 0.3058824, lightness 0.39215687, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.167a9p126F}.
     * <pre>
     * <font style='background-color: #483D8B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #483D8B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #483D8B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #483D8B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #483D8B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #483D8B'>&nbsp;@&nbsp;</font><font style='background-color: #483D8B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #483D8B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #483D8B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKSLATEBLUE = -0x1.167a9p126F;
    static { NAMED.put("darkslateblue", -0x1.167a9p126F); LIST.add(-0x1.167a9p126F); }

    /**
     * This color constant "darkslategray" has RGBA8888 code {@code 2F4F4FFF}, hue 0.5, saturation 0.1254902, lightness 0.24705884, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.9e9e5ep125F}.
     * <pre>
     * <font style='background-color: #2F4F4F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2F4F4F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2F4F4F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2F4F4F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2F4F4F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2F4F4F'>&nbsp;@&nbsp;</font><font style='background-color: #2F4F4F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2F4F4F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2F4F4F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKSLATEGRAY = -0x1.9e9e5ep125F;
    static { NAMED.put("darkslategray", -0x1.9e9e5ep125F); LIST.add(-0x1.9e9e5ep125F); }

    /**
     * This color constant "darkslategrey" has RGBA8888 code {@code 2F4F4FFF}, hue 0.5, saturation 0.1254902, lightness 0.24705884, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.9e9e5ep125F}.
     * <pre>
     * <font style='background-color: #2F4F4F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2F4F4F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2F4F4F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2F4F4F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2F4F4F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2F4F4F'>&nbsp;@&nbsp;</font><font style='background-color: #2F4F4F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2F4F4F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2F4F4F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKSLATEGREY = -0x1.9e9e5ep125F;
    static { NAMED.put("darkslategrey", -0x1.9e9e5ep125F); LIST.add(-0x1.9e9e5ep125F); }

    /**
     * This color constant "darkturquoise" has RGBA8888 code {@code 00CED1FF}, hue 0.50239235, saturation 0.81960785, lightness 0.40980393, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.a39cp126F}.
     * <pre>
     * <font style='background-color: #00CED1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00CED1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00CED1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00CED1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00CED1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00CED1'>&nbsp;@&nbsp;</font><font style='background-color: #00CED1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00CED1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00CED1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKTURQUOISE = -0x1.a39cp126F;
    static { NAMED.put("darkturquoise", -0x1.a39cp126F); LIST.add(-0x1.a39cp126F); }

    /**
     * This color constant "darkviolet" has RGBA8888 code {@code 9400D3FF}, hue 0.78357035, saturation 0.827451, lightness 0.4137255, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.a60128p126F}.
     * <pre>
     * <font style='background-color: #9400D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9400D3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9400D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9400D3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9400D3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9400D3'>&nbsp;@&nbsp;</font><font style='background-color: #9400D3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9400D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9400D3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKVIOLET = -0x1.a60128p126F;
    static { NAMED.put("darkviolet", -0x1.a60128p126F); LIST.add(-0x1.a60128p126F); }

    /**
     * This color constant "deeppink" has RGBA8888 code {@code FF1493FF}, hue 0.9099291, saturation 0.92156863, lightness 0.5392157, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.2629fep126F}.
     * <pre>
     * <font style='background-color: #FF1493;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF1493; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF1493;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF1493'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF1493'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF1493'>&nbsp;@&nbsp;</font><font style='background-color: #FF1493; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF1493;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF1493; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEPPINK = -0x1.2629fep126F;
    static { NAMED.put("deeppink", -0x1.2629fep126F); LIST.add(-0x1.2629fep126F); }

    /**
     * This color constant "deepskyblue" has RGBA8888 code {@code 00BFFFFF}, hue 0.54183006, saturation 1.0, lightness 0.5, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.ff7ep126F}.
     * <pre>
     * <font style='background-color: #00BFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00BFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00BFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00BFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00BFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00BFFF'>&nbsp;@&nbsp;</font><font style='background-color: #00BFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00BFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00BFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEPSKYBLUE = -0x1.ff7ep126F;
    static { NAMED.put("deepskyblue", -0x1.ff7ep126F); LIST.add(-0x1.ff7ep126F); }

    /**
     * This color constant "dimgray" has RGBA8888 code {@code 696969FF}, hue 0.0, saturation 0.0, lightness 0.4117647, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.d2d2d2p125F}.
     * <pre>
     * <font style='background-color: #696969;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #696969; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #696969;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #696969'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #696969'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #696969'>&nbsp;@&nbsp;</font><font style='background-color: #696969; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #696969;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #696969; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DIMGRAY = -0x1.d2d2d2p125F;
    static { NAMED.put("dimgray", -0x1.d2d2d2p125F); LIST.add(-0x1.d2d2d2p125F); }

    /**
     * This color constant "dimgrey" has RGBA8888 code {@code 696969FF}, hue 0.0, saturation 0.0, lightness 0.4117647, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.d2d2d2p125F}.
     * <pre>
     * <font style='background-color: #696969;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #696969; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #696969;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #696969'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #696969'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #696969'>&nbsp;@&nbsp;</font><font style='background-color: #696969; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #696969;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #696969; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DIMGREY = -0x1.d2d2d2p125F;
    static { NAMED.put("dimgrey", -0x1.d2d2d2p125F); LIST.add(-0x1.d2d2d2p125F); }

    /**
     * This color constant "dodgerblue" has RGBA8888 code {@code 1E90FFFF}, hue 0.5822222, saturation 0.88235295, lightness 0.5588235, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.ff203cp126F}.
     * <pre>
     * <font style='background-color: #1E90FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1E90FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1E90FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1E90FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1E90FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1E90FF'>&nbsp;@&nbsp;</font><font style='background-color: #1E90FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1E90FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1E90FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DODGERBLUE = -0x1.ff203cp126F;
    static { NAMED.put("dodgerblue", -0x1.ff203cp126F); LIST.add(-0x1.ff203cp126F); }

    /**
     * This color constant "firebrick" has RGBA8888 code {@code B22222FF}, hue 0.0, saturation 0.5647059, lightness 0.41568628, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.444564p125F}.
     * <pre>
     * <font style='background-color: #B22222;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B22222; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B22222;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B22222'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B22222'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B22222'>&nbsp;@&nbsp;</font><font style='background-color: #B22222; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B22222;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B22222; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FIREBRICK = -0x1.444564p125F;
    static { NAMED.put("firebrick", -0x1.444564p125F); LIST.add(-0x1.444564p125F); }

    /**
     * This color constant "floralwhite" has RGBA8888 code {@code FFFAF0FF}, hue 0.11111111, saturation 0.058823526, lightness 0.9705882, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.e1f5fep126F}.
     * <pre>
     * <font style='background-color: #FFFAF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFAF0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFAF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFAF0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFAF0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFAF0'>&nbsp;@&nbsp;</font><font style='background-color: #FFFAF0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFAF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFAF0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FLORALWHITE = -0x1.e1f5fep126F;
    static { NAMED.put("floralwhite", -0x1.e1f5fep126F); LIST.add(-0x1.e1f5fep126F); }

    /**
     * This color constant "forestgreen" has RGBA8888 code {@code 228B22FF}, hue 0.33333334, saturation 0.41176474, lightness 0.33921573, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.451644p125F}.
     * <pre>
     * <font style='background-color: #228B22;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #228B22; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #228B22;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #228B22'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #228B22'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #228B22'>&nbsp;@&nbsp;</font><font style='background-color: #228B22; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #228B22;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #228B22; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FORESTGREEN = -0x1.451644p125F;
    static { NAMED.put("forestgreen", -0x1.451644p125F); LIST.add(-0x1.451644p125F); }

    /**
     * This color constant "fuchsia" has RGBA8888 code {@code FF00FFFF}, hue 0.8333333, saturation 1.0, lightness 0.5, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.fe01fep126F}.
     * <pre>
     * <font style='background-color: #FF00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF00FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF00FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF00FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF00FF'>&nbsp;@&nbsp;</font><font style='background-color: #FF00FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF00FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FUCHSIA = -0x1.fe01fep126F;
    static { NAMED.put("fuchsia", -0x1.fe01fep126F); LIST.add(-0x1.fe01fep126F); }

    /**
     * This color constant "gainsboro" has RGBA8888 code {@code DCDCDCFF}, hue 0.0, saturation 0.0, lightness 0.8627451, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.b9b9b8p126F}.
     * <pre>
     * <font style='background-color: #DCDCDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DCDCDC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DCDCDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DCDCDC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DCDCDC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DCDCDC'>&nbsp;@&nbsp;</font><font style='background-color: #DCDCDC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DCDCDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DCDCDC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GAINSBORO = -0x1.b9b9b8p126F;
    static { NAMED.put("gainsboro", -0x1.b9b9b8p126F); LIST.add(-0x1.b9b9b8p126F); }

    /**
     * This color constant "ghostwhite" has RGBA8888 code {@code F8F8FFFF}, hue 0.6666667, saturation 0.027450979, lightness 0.9862745, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.fff1fp126F}.
     * <pre>
     * <font style='background-color: #F8F8FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F8F8FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F8F8FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F8F8FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F8F8FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F8F8FF'>&nbsp;@&nbsp;</font><font style='background-color: #F8F8FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F8F8FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F8F8FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GHOSTWHITE = -0x1.fff1fp126F;
    static { NAMED.put("ghostwhite", -0x1.fff1fp126F); LIST.add(-0x1.fff1fp126F); }

    /**
     * This color constant "gold" has RGBA8888 code {@code FFD700FF}, hue 0.14052288, saturation 1.0, lightness 0.5, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.01affep125F}.
     * <pre>
     * <font style='background-color: #FFD700;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD700; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD700;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFD700'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFD700'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFD700'>&nbsp;@&nbsp;</font><font style='background-color: #FFD700; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD700;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD700; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GOLD = -0x1.01affep125F;
    static { NAMED.put("gold", -0x1.01affep125F); LIST.add(-0x1.01affep125F); }

    /**
     * This color constant "goldenrod" has RGBA8888 code {@code DAA520FF}, hue 0.119175635, saturation 0.7294118, lightness 0.49019608, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.414bb4p125F}.
     * <pre>
     * <font style='background-color: #DAA520;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAA520; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAA520;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DAA520'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DAA520'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DAA520'>&nbsp;@&nbsp;</font><font style='background-color: #DAA520; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAA520;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAA520; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GOLDENROD = -0x1.414bb4p125F;
    static { NAMED.put("goldenrod", -0x1.414bb4p125F); LIST.add(-0x1.414bb4p125F); }

    /**
     * This color constant "gray" has RGBA8888 code {@code 808080FF}, hue 0.0, saturation 0.0, lightness 0.5019608, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.0101p126F}.
     * <pre>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #808080; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #808080; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #808080; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY = -0x1.0101p126F;
    static { NAMED.put("gray", -0x1.0101p126F); LIST.add(-0x1.0101p126F); }

    /**
     * This color constant "green" has RGBA8888 code {@code 008000FF}, hue 0.33333334, saturation 0.5019608, lightness 0.2509804, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.01p125F}.
     * <pre>
     * <font style='background-color: #008000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #008000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #008000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #008000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #008000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #008000'>&nbsp;@&nbsp;</font><font style='background-color: #008000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #008000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #008000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GREEN = -0x1.01p125F;
    static { NAMED.put("green", -0x1.01p125F); LIST.add(-0x1.01p125F); }

    /**
     * This color constant "greenyellow" has RGBA8888 code {@code ADFF2FFF}, hue 0.2323718, saturation 0.8156863, lightness 0.5921569, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.5fff5ap125F}.
     * <pre>
     * <font style='background-color: #ADFF2F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ADFF2F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ADFF2F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ADFF2F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ADFF2F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ADFF2F'>&nbsp;@&nbsp;</font><font style='background-color: #ADFF2F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ADFF2F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ADFF2F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GREENYELLOW = -0x1.5fff5ap125F;
    static { NAMED.put("greenyellow", -0x1.5fff5ap125F); LIST.add(-0x1.5fff5ap125F); }

    /**
     * This color constant "grey" has RGBA8888 code {@code 808080FF}, hue 0.0, saturation 0.0, lightness 0.5019608, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.0101p126F}.
     * <pre>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #808080; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #808080; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #808080; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GREY = -0x1.0101p126F;
    static { NAMED.put("grey", -0x1.0101p126F); LIST.add(-0x1.0101p126F); }

    /**
     * This color constant "honeydew" has RGBA8888 code {@code F0FFF0FF}, hue 0.33333334, saturation 0.058823526, lightness 0.9705882, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.e1ffep126F}.
     * <pre>
     * <font style='background-color: #F0FFF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0FFF0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0FFF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F0FFF0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F0FFF0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F0FFF0'>&nbsp;@&nbsp;</font><font style='background-color: #F0FFF0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0FFF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0FFF0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float HONEYDEW = -0x1.e1ffep126F;
    static { NAMED.put("honeydew", -0x1.e1ffep126F); LIST.add(-0x1.e1ffep126F); }

    /**
     * This color constant "hotpink" has RGBA8888 code {@code FF69B4FF}, hue 0.9166666, saturation 0.58823526, lightness 0.7058824, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.68d3fep126F}.
     * <pre>
     * <font style='background-color: #FF69B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF69B4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF69B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF69B4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF69B4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF69B4'>&nbsp;@&nbsp;</font><font style='background-color: #FF69B4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF69B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF69B4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float HOTPINK = -0x1.68d3fep126F;
    static { NAMED.put("hotpink", -0x1.68d3fep126F); LIST.add(-0x1.68d3fep126F); }

    /**
     * This color constant "indianred" has RGBA8888 code {@code CD5C5CFF}, hue 0.0, saturation 0.44313726, lightness 0.582353, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.b8b99ap125F}.
     * <pre>
     * <font style='background-color: #CD5C5C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CD5C5C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CD5C5C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CD5C5C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CD5C5C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CD5C5C'>&nbsp;@&nbsp;</font><font style='background-color: #CD5C5C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CD5C5C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CD5C5C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float INDIANRED = -0x1.b8b99ap125F;
    static { NAMED.put("indianred", -0x1.b8b99ap125F); LIST.add(-0x1.b8b99ap125F); }

    /**
     * This color constant "indigo" has RGBA8888 code {@code 4B0082FF}, hue 0.76282054, saturation 0.50980395, lightness 0.25490198, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.040096p126F}.
     * <pre>
     * <font style='background-color: #4B0082;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B0082; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B0082;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4B0082'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4B0082'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4B0082'>&nbsp;@&nbsp;</font><font style='background-color: #4B0082; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B0082;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B0082; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float INDIGO = -0x1.040096p126F;
    static { NAMED.put("indigo", -0x1.040096p126F); LIST.add(-0x1.040096p126F); }

    /**
     * This color constant "ivory" has RGBA8888 code {@code FFFFF0FF}, hue 0.16666667, saturation 0.058823526, lightness 0.9705882, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.e1fffep126F}.
     * <pre>
     * <font style='background-color: #FFFFF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFF0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFFF0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFFF0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFFF0'>&nbsp;@&nbsp;</font><font style='background-color: #FFFFF0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFF0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float IVORY = -0x1.e1fffep126F;
    static { NAMED.put("ivory", -0x1.e1fffep126F); LIST.add(-0x1.e1fffep126F); }

    /**
     * This color constant "khaki" has RGBA8888 code {@code F0E68CFF}, hue 0.15, saturation 0.39215684, lightness 0.74509805, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.19cdep126F}.
     * <pre>
     * <font style='background-color: #F0E68C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0E68C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0E68C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F0E68C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F0E68C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F0E68C'>&nbsp;@&nbsp;</font><font style='background-color: #F0E68C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0E68C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0E68C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float KHAKI = -0x1.19cdep126F;
    static { NAMED.put("khaki", -0x1.19cdep126F); LIST.add(-0x1.19cdep126F); }

    /**
     * This color constant "lavender" has RGBA8888 code {@code E6E6FAFF}, hue 0.6666667, saturation 0.07843137, lightness 0.9411765, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.f5cdccp126F}.
     * <pre>
     * <font style='background-color: #E6E6FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E6E6FA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E6E6FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E6E6FA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E6E6FA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E6E6FA'>&nbsp;@&nbsp;</font><font style='background-color: #E6E6FA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E6E6FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E6E6FA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LAVENDER = -0x1.f5cdccp126F;
    static { NAMED.put("lavender", -0x1.f5cdccp126F); LIST.add(-0x1.f5cdccp126F); }

    /**
     * This color constant "lavenderblush" has RGBA8888 code {@code FFF0F5FF}, hue 0.9444444, saturation 0.058823526, lightness 0.9705882, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.ebe1fep126F}.
     * <pre>
     * <font style='background-color: #FFF0F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFF0F5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFF0F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFF0F5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFF0F5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFF0F5'>&nbsp;@&nbsp;</font><font style='background-color: #FFF0F5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFF0F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFF0F5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LAVENDERBLUSH = -0x1.ebe1fep126F;
    static { NAMED.put("lavenderblush", -0x1.ebe1fep126F); LIST.add(-0x1.ebe1fep126F); }

    /**
     * This color constant "lawngreen" has RGBA8888 code {@code 7CFC00FF}, hue 0.25132275, saturation 0.9882353, lightness 0.49411765, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.01f8f8p125F}.
     * <pre>
     * <font style='background-color: #7CFC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7CFC00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7CFC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7CFC00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7CFC00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7CFC00'>&nbsp;@&nbsp;</font><font style='background-color: #7CFC00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7CFC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7CFC00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LAWNGREEN = -0x1.01f8f8p125F;
    static { NAMED.put("lawngreen", -0x1.01f8f8p125F); LIST.add(-0x1.01f8f8p125F); }

    /**
     * This color constant "lemonchiffon" has RGBA8888 code {@code FFFACDFF}, hue 0.15, saturation 0.19607842, lightness 0.9019608, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.9bf5fep126F}.
     * <pre>
     * <font style='background-color: #FFFACD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFACD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFACD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFACD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFACD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFACD'>&nbsp;@&nbsp;</font><font style='background-color: #FFFACD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFACD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFACD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEMONCHIFFON = -0x1.9bf5fep126F;
    static { NAMED.put("lemonchiffon", -0x1.9bf5fep126F); LIST.add(-0x1.9bf5fep126F); }

    /**
     * This color constant "lightblue" has RGBA8888 code {@code ADD8E6FF}, hue 0.5409357, saturation 0.2235294, lightness 0.79019606, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.cdb15ap126F}.
     * <pre>
     * <font style='background-color: #ADD8E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ADD8E6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ADD8E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ADD8E6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ADD8E6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ADD8E6'>&nbsp;@&nbsp;</font><font style='background-color: #ADD8E6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ADD8E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ADD8E6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTBLUE = -0x1.cdb15ap126F;
    static { NAMED.put("lightblue", -0x1.cdb15ap126F); LIST.add(-0x1.cdb15ap126F); }

    /**
     * This color constant "lightcoral" has RGBA8888 code {@code F08080FF}, hue 0.0, saturation 0.43921566, lightness 0.72156864, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.0101ep126F}.
     * <pre>
     * <font style='background-color: #F08080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F08080; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F08080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F08080'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F08080'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F08080'>&nbsp;@&nbsp;</font><font style='background-color: #F08080; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F08080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F08080; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTCORAL = -0x1.0101ep126F;
    static { NAMED.put("lightcoral", -0x1.0101ep126F); LIST.add(-0x1.0101ep126F); }

    /**
     * This color constant "lightcyan" has RGBA8888 code {@code E0FFFFFF}, hue 0.5, saturation 0.12156862, lightness 0.93921566, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.ffffcp126F}.
     * <pre>
     * <font style='background-color: #E0FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E0FFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E0FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E0FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E0FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E0FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #E0FFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E0FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E0FFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTCYAN = -0x1.ffffcp126F;
    static { NAMED.put("lightcyan", -0x1.ffffcp126F); LIST.add(-0x1.ffffcp126F); }

    /**
     * This color constant "lightgoldenrodyellow" has RGBA8888 code {@code FAFAD2FF}, hue 0.16666667, saturation 0.15686274, lightness 0.9019608, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.a5f5f4p126F}.
     * <pre>
     * <font style='background-color: #FAFAD2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FAFAD2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FAFAD2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FAFAD2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FAFAD2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FAFAD2'>&nbsp;@&nbsp;</font><font style='background-color: #FAFAD2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FAFAD2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FAFAD2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTGOLDENRODYELLOW = -0x1.a5f5f4p126F;
    static { NAMED.put("lightgoldenrodyellow", -0x1.a5f5f4p126F); LIST.add(-0x1.a5f5f4p126F); }

    /**
     * This color constant "lightgray" has RGBA8888 code {@code D3D3D3FF}, hue 0.0, saturation 0.0, lightness 0.827451, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.a7a7a6p126F}.
     * <pre>
     * <font style='background-color: #D3D3D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3D3D3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3D3D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D3D3D3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D3D3D3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D3D3D3'>&nbsp;@&nbsp;</font><font style='background-color: #D3D3D3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3D3D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3D3D3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTGRAY = -0x1.a7a7a6p126F;
    static { NAMED.put("lightgray", -0x1.a7a7a6p126F); LIST.add(-0x1.a7a7a6p126F); }

    /**
     * This color constant "lightgreen" has RGBA8888 code {@code 90EE90FF}, hue 0.33333334, saturation 0.36862743, lightness 0.7490196, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.21dd2p126F}.
     * <pre>
     * <font style='background-color: #90EE90;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #90EE90; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #90EE90;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #90EE90'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #90EE90'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #90EE90'>&nbsp;@&nbsp;</font><font style='background-color: #90EE90; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #90EE90;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #90EE90; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTGREEN = -0x1.21dd2p126F;
    static { NAMED.put("lightgreen", -0x1.21dd2p126F); LIST.add(-0x1.21dd2p126F); }

    /**
     * This color constant "lightgrey" has RGBA8888 code {@code D3D3D3FF}, hue 0.0, saturation 0.0, lightness 0.827451, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.a7a7a6p126F}.
     * <pre>
     * <font style='background-color: #D3D3D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3D3D3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3D3D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D3D3D3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D3D3D3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D3D3D3'>&nbsp;@&nbsp;</font><font style='background-color: #D3D3D3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3D3D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3D3D3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTGREY = -0x1.a7a7a6p126F;
    static { NAMED.put("lightgrey", -0x1.a7a7a6p126F); LIST.add(-0x1.a7a7a6p126F); }

    /**
     * This color constant "lightpink" has RGBA8888 code {@code FFB6C1FF}, hue 0.9748858, saturation 0.2862745, lightness 0.8568628, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.836dfep126F}.
     * <pre>
     * <font style='background-color: #FFB6C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFB6C1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFB6C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFB6C1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFB6C1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFB6C1'>&nbsp;@&nbsp;</font><font style='background-color: #FFB6C1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFB6C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFB6C1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTPINK = -0x1.836dfep126F;
    static { NAMED.put("lightpink", -0x1.836dfep126F); LIST.add(-0x1.836dfep126F); }

    /**
     * This color constant "lightsalmon" has RGBA8888 code {@code FFA07AFF}, hue 0.047619052, saturation 0.52156866, lightness 0.7392157, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.f541fep125F}.
     * <pre>
     * <font style='background-color: #FFA07A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA07A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA07A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFA07A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFA07A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFA07A'>&nbsp;@&nbsp;</font><font style='background-color: #FFA07A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA07A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA07A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTSALMON = -0x1.f541fep125F;
    static { NAMED.put("lightsalmon", -0x1.f541fep125F); LIST.add(-0x1.f541fep125F); }

    /**
     * This color constant "lightseagreen" has RGBA8888 code {@code 20B2AAFF}, hue 0.4908676, saturation 0.57254905, lightness 0.4117647, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.55644p126F}.
     * <pre>
     * <font style='background-color: #20B2AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #20B2AA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #20B2AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #20B2AA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #20B2AA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #20B2AA'>&nbsp;@&nbsp;</font><font style='background-color: #20B2AA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #20B2AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #20B2AA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTSEAGREEN = -0x1.55644p126F;
    static { NAMED.put("lightseagreen", -0x1.55644p126F); LIST.add(-0x1.55644p126F); }

    /**
     * This color constant "lightskyblue" has RGBA8888 code {@code 87CEFAFF}, hue 0.56376815, saturation 0.45098037, lightness 0.75490195, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.f59d0ep126F}.
     * <pre>
     * <font style='background-color: #87CEFA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #87CEFA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #87CEFA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #87CEFA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #87CEFA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #87CEFA'>&nbsp;@&nbsp;</font><font style='background-color: #87CEFA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #87CEFA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #87CEFA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTSKYBLUE = -0x1.f59d0ep126F;
    static { NAMED.put("lightskyblue", -0x1.f59d0ep126F); LIST.add(-0x1.f59d0ep126F); }

    /**
     * This color constant "lightslategray" has RGBA8888 code {@code 778899FF}, hue 0.5833333, saturation 0.13333336, lightness 0.53333336, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.3310eep126F}.
     * <pre>
     * <font style='background-color: #778899;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #778899; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #778899;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #778899'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #778899'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #778899'>&nbsp;@&nbsp;</font><font style='background-color: #778899; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #778899;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #778899; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTSLATEGRAY = -0x1.3310eep126F;
    static { NAMED.put("lightslategray", -0x1.3310eep126F); LIST.add(-0x1.3310eep126F); }

    /**
     * This color constant "lightslategrey" has RGBA8888 code {@code 778899FF}, hue 0.5833333, saturation 0.13333336, lightness 0.53333336, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.3310eep126F}.
     * <pre>
     * <font style='background-color: #778899;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #778899; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #778899;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #778899'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #778899'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #778899'>&nbsp;@&nbsp;</font><font style='background-color: #778899; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #778899;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #778899; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTSLATEGREY = -0x1.3310eep126F;
    static { NAMED.put("lightslategrey", -0x1.3310eep126F); LIST.add(-0x1.3310eep126F); }

    /**
     * This color constant "lightsteelblue" has RGBA8888 code {@code B0C4DEFF}, hue 0.59420294, saturation 0.18039215, lightness 0.78039217, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.bd896p126F}.
     * <pre>
     * <font style='background-color: #B0C4DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0C4DE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0C4DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B0C4DE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B0C4DE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B0C4DE'>&nbsp;@&nbsp;</font><font style='background-color: #B0C4DE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0C4DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0C4DE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTSTEELBLUE = -0x1.bd896p126F;
    static { NAMED.put("lightsteelblue", -0x1.bd896p126F); LIST.add(-0x1.bd896p126F); }

    /**
     * This color constant "lightyellow" has RGBA8888 code {@code FFFFE0FF}, hue 0.16666667, saturation 0.12156862, lightness 0.93921566, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.c1fffep126F}.
     * <pre>
     * <font style='background-color: #FFFFE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFE0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFFE0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFFE0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFFE0'>&nbsp;@&nbsp;</font><font style='background-color: #FFFFE0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFE0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTYELLOW = -0x1.c1fffep126F;
    static { NAMED.put("lightyellow", -0x1.c1fffep126F); LIST.add(-0x1.c1fffep126F); }

    /**
     * This color constant "lime" has RGBA8888 code {@code 00FF00FF}, hue 0.33333334, saturation 1.0, lightness 0.5, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.01fep125F}.
     * <pre>
     * <font style='background-color: #00FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FF00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FF00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FF00'>&nbsp;@&nbsp;</font><font style='background-color: #00FF00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIME = -0x1.01fep125F;
    static { NAMED.put("lime", -0x1.01fep125F); LIST.add(-0x1.01fep125F); }

    /**
     * This color constant "limegreen" has RGBA8888 code {@code 32CD32FF}, hue 0.33333334, saturation 0.60784316, lightness 0.5, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.659a64p125F}.
     * <pre>
     * <font style='background-color: #32CD32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #32CD32; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #32CD32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #32CD32'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #32CD32'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #32CD32'>&nbsp;@&nbsp;</font><font style='background-color: #32CD32; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #32CD32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #32CD32; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIMEGREEN = -0x1.659a64p125F;
    static { NAMED.put("limegreen", -0x1.659a64p125F); LIST.add(-0x1.659a64p125F); }

    /**
     * This color constant "linen" has RGBA8888 code {@code FAF0E6FF}, hue 0.083333336, saturation 0.07843137, lightness 0.9411765, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.cde1f4p126F}.
     * <pre>
     * <font style='background-color: #FAF0E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FAF0E6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FAF0E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FAF0E6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FAF0E6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FAF0E6'>&nbsp;@&nbsp;</font><font style='background-color: #FAF0E6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FAF0E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FAF0E6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LINEN = -0x1.cde1f4p126F;
    static { NAMED.put("linen", -0x1.cde1f4p126F); LIST.add(-0x1.cde1f4p126F); }

    /**
     * This color constant "magenta" has RGBA8888 code {@code FF00FFFF}, hue 0.8333333, saturation 1.0, lightness 0.5, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.fe01fep126F}.
     * <pre>
     * <font style='background-color: #FF00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF00FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF00FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF00FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF00FF'>&nbsp;@&nbsp;</font><font style='background-color: #FF00FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF00FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MAGENTA = -0x1.fe01fep126F;
    static { NAMED.put("magenta", -0x1.fe01fep126F); LIST.add(-0x1.fe01fep126F); }

    /**
     * This color constant "maroon" has RGBA8888 code {@code 800000FF}, hue 0.0, saturation 0.5019608, lightness 0.2509804, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.0001p125F}.
     * <pre>
     * <font style='background-color: #800000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #800000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #800000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #800000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #800000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #800000'>&nbsp;@&nbsp;</font><font style='background-color: #800000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #800000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #800000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MAROON = -0x1.0001p125F;
    static { NAMED.put("maroon", -0x1.0001p125F); LIST.add(-0x1.0001p125F); }

    /**
     * This color constant "mediumaquamarine" has RGBA8888 code {@code 66CDAAFF}, hue 0.44336572, saturation 0.40392157, lightness 0.6019608, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.559accp126F}.
     * <pre>
     * <font style='background-color: #66CDAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #66CDAA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #66CDAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #66CDAA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #66CDAA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #66CDAA'>&nbsp;@&nbsp;</font><font style='background-color: #66CDAA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #66CDAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #66CDAA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MEDIUMAQUAMARINE = -0x1.559accp126F;
    static { NAMED.put("mediumaquamarine", -0x1.559accp126F); LIST.add(-0x1.559accp126F); }

    /**
     * This color constant "mediumblue" has RGBA8888 code {@code 0000CDFF}, hue 0.6666667, saturation 0.8039216, lightness 0.4019608, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.9ap126F}.
     * <pre>
     * <font style='background-color: #0000CD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0000CD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0000CD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0000CD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0000CD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0000CD'>&nbsp;@&nbsp;</font><font style='background-color: #0000CD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0000CD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0000CD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MEDIUMBLUE = -0x1.9ap126F;
    static { NAMED.put("mediumblue", -0x1.9ap126F); LIST.add(-0x1.9ap126F); }

    /**
     * This color constant "mediumorchid" has RGBA8888 code {@code BA55D3FF}, hue 0.8002646, saturation 0.49411765, lightness 0.5803922, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.a6ab74p126F}.
     * <pre>
     * <font style='background-color: #BA55D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA55D3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA55D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BA55D3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BA55D3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BA55D3'>&nbsp;@&nbsp;</font><font style='background-color: #BA55D3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA55D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA55D3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MEDIUMORCHID = -0x1.a6ab74p126F;
    static { NAMED.put("mediumorchid", -0x1.a6ab74p126F); LIST.add(-0x1.a6ab74p126F); }

    /**
     * This color constant "mediumpurple" has RGBA8888 code {@code 9370DBFF}, hue 0.72118384, saturation 0.41960785, lightness 0.6490196, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.b6e126p126F}.
     * <pre>
     * <font style='background-color: #9370DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9370DB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9370DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9370DB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9370DB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9370DB'>&nbsp;@&nbsp;</font><font style='background-color: #9370DB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9370DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9370DB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MEDIUMPURPLE = -0x1.b6e126p126F;
    static { NAMED.put("mediumpurple", -0x1.b6e126p126F); LIST.add(-0x1.b6e126p126F); }

    /**
     * This color constant "mediumseagreen" has RGBA8888 code {@code 3CB371FF}, hue 0.40756303, saturation 0.4666667, lightness 0.46862745, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.e36678p125F}.
     * <pre>
     * <font style='background-color: #3CB371;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3CB371; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3CB371;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3CB371'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3CB371'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3CB371'>&nbsp;@&nbsp;</font><font style='background-color: #3CB371; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3CB371;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3CB371; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MEDIUMSEAGREEN = -0x1.e36678p125F;
    static { NAMED.put("mediumseagreen", -0x1.e36678p125F); LIST.add(-0x1.e36678p125F); }

    /**
     * This color constant "mediumslateblue" has RGBA8888 code {@code 7B68EEFF}, hue 0.69029856, saturation 0.52549016, lightness 0.67058825, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.dcd0f6p126F}.
     * <pre>
     * <font style='background-color: #7B68EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B68EE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B68EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7B68EE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7B68EE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7B68EE'>&nbsp;@&nbsp;</font><font style='background-color: #7B68EE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B68EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B68EE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MEDIUMSLATEBLUE = -0x1.dcd0f6p126F;
    static { NAMED.put("mediumslateblue", -0x1.dcd0f6p126F); LIST.add(-0x1.dcd0f6p126F); }

    /**
     * This color constant "mediumspringgreen" has RGBA8888 code {@code 00FA9AFF}, hue 0.43600002, saturation 0.98039216, lightness 0.49019608, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.35f4p126F}.
     * <pre>
     * <font style='background-color: #00FA9A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FA9A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FA9A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FA9A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FA9A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FA9A'>&nbsp;@&nbsp;</font><font style='background-color: #00FA9A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FA9A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FA9A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MEDIUMSPRINGGREEN = -0x1.35f4p126F;
    static { NAMED.put("mediumspringgreen", -0x1.35f4p126F); LIST.add(-0x1.35f4p126F); }

    /**
     * This color constant "mediumturquoise" has RGBA8888 code {@code 48D1CCFF}, hue 0.4939173, saturation 0.5372549, lightness 0.5509804, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.99a29p126F}.
     * <pre>
     * <font style='background-color: #48D1CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #48D1CC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #48D1CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #48D1CC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #48D1CC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #48D1CC'>&nbsp;@&nbsp;</font><font style='background-color: #48D1CC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #48D1CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #48D1CC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MEDIUMTURQUOISE = -0x1.99a29p126F;
    static { NAMED.put("mediumturquoise", -0x1.99a29p126F); LIST.add(-0x1.99a29p126F); }

    /**
     * This color constant "mediumvioletred" has RGBA8888 code {@code C71585FF}, hue 0.8951311, saturation 0.69803923, lightness 0.43137255, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.0a2b8ep126F}.
     * <pre>
     * <font style='background-color: #C71585;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C71585; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C71585;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C71585'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C71585'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C71585'>&nbsp;@&nbsp;</font><font style='background-color: #C71585; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C71585;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C71585; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MEDIUMVIOLETRED = -0x1.0a2b8ep126F;
    static { NAMED.put("mediumvioletred", -0x1.0a2b8ep126F); LIST.add(-0x1.0a2b8ep126F); }

    /**
     * This color constant "midnightblue" has RGBA8888 code {@code 191970FF}, hue 0.6666667, saturation 0.34117648, lightness 0.26862743, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.e03232p125F}.
     * <pre>
     * <font style='background-color: #191970;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #191970; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #191970;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #191970'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #191970'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #191970'>&nbsp;@&nbsp;</font><font style='background-color: #191970; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #191970;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #191970; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MIDNIGHTBLUE = -0x1.e03232p125F;
    static { NAMED.put("midnightblue", -0x1.e03232p125F); LIST.add(-0x1.e03232p125F); }

    /**
     * This color constant "mintcream" has RGBA8888 code {@code F5FFFAFF}, hue 0.4166667, saturation 0.039215684, lightness 0.98039216, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.f5ffeap126F}.
     * <pre>
     * <font style='background-color: #F5FFFA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5FFFA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5FFFA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F5FFFA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F5FFFA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F5FFFA'>&nbsp;@&nbsp;</font><font style='background-color: #F5FFFA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5FFFA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5FFFA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MINTCREAM = -0x1.f5ffeap126F;
    static { NAMED.put("mintcream", -0x1.f5ffeap126F); LIST.add(-0x1.f5ffeap126F); }

    /**
     * This color constant "mistyrose" has RGBA8888 code {@code FFE4E1FF}, hue 0.016666668, saturation 0.11764705, lightness 0.9411765, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.c3c9fep126F}.
     * <pre>
     * <font style='background-color: #FFE4E1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFE4E1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFE4E1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFE4E1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFE4E1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFE4E1'>&nbsp;@&nbsp;</font><font style='background-color: #FFE4E1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFE4E1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFE4E1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MISTYROSE = -0x1.c3c9fep126F;
    static { NAMED.put("mistyrose", -0x1.c3c9fep126F); LIST.add(-0x1.c3c9fep126F); }

    /**
     * This color constant "moccasin" has RGBA8888 code {@code FFE4B5FF}, hue 0.10585585, saturation 0.29019606, lightness 0.85490197, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.6bc9fep126F}.
     * <pre>
     * <font style='background-color: #FFE4B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFE4B5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFE4B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFE4B5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFE4B5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFE4B5'>&nbsp;@&nbsp;</font><font style='background-color: #FFE4B5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFE4B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFE4B5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MOCCASIN = -0x1.6bc9fep126F;
    static { NAMED.put("moccasin", -0x1.6bc9fep126F); LIST.add(-0x1.6bc9fep126F); }

    /**
     * This color constant "navajowhite" has RGBA8888 code {@code FFDEADFF}, hue 0.0995935, saturation 0.3215686, lightness 0.8392157, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.5bbdfep126F}.
     * <pre>
     * <font style='background-color: #FFDEAD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFDEAD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFDEAD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFDEAD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFDEAD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFDEAD'>&nbsp;@&nbsp;</font><font style='background-color: #FFDEAD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFDEAD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFDEAD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float NAVAJOWHITE = -0x1.5bbdfep126F;
    static { NAMED.put("navajowhite", -0x1.5bbdfep126F); LIST.add(-0x1.5bbdfep126F); }

    /**
     * This color constant "navy" has RGBA8888 code {@code 000080FF}, hue 0.6666667, saturation 0.5019608, lightness 0.2509804, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.0p126F}.
     * <pre>
     * <font style='background-color: #000080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000080; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000080'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000080'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000080'>&nbsp;@&nbsp;</font><font style='background-color: #000080; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000080; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float NAVY = -0x1.0p126F;
    static { NAMED.put("navy", -0x1.0p126F); LIST.add(-0x1.0p126F); }

    /**
     * This color constant "oldlace" has RGBA8888 code {@code FDF5E6FF}, hue 0.10869565, saturation 0.09019607, lightness 0.9470588, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.cdebfap126F}.
     * <pre>
     * <font style='background-color: #FDF5E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDF5E6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDF5E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FDF5E6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FDF5E6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FDF5E6'>&nbsp;@&nbsp;</font><font style='background-color: #FDF5E6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDF5E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDF5E6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float OLDLACE = -0x1.cdebfap126F;
    static { NAMED.put("oldlace", -0x1.cdebfap126F); LIST.add(-0x1.cdebfap126F); }

    /**
     * This color constant "olive" has RGBA8888 code {@code 808000FF}, hue 0.16666666, saturation 0.5019608, lightness 0.2509804, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.0101p125F}.
     * <pre>
     * <font style='background-color: #808000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #808000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #808000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #808000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #808000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #808000'>&nbsp;@&nbsp;</font><font style='background-color: #808000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #808000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #808000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float OLIVE = -0x1.0101p125F;
    static { NAMED.put("olive", -0x1.0101p125F); LIST.add(-0x1.0101p125F); }

    /**
     * This color constant "olivedrab" has RGBA8888 code {@code 6B8E23FF}, hue 0.22118384, saturation 0.41960788, lightness 0.34705883, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.471cd6p125F}.
     * <pre>
     * <font style='background-color: #6B8E23;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6B8E23; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6B8E23;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6B8E23'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6B8E23'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6B8E23'>&nbsp;@&nbsp;</font><font style='background-color: #6B8E23; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6B8E23;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6B8E23; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float OLIVEDRAB = -0x1.471cd6p125F;
    static { NAMED.put("olivedrab", -0x1.471cd6p125F); LIST.add(-0x1.471cd6p125F); }

    /**
     * This color constant "orange" has RGBA8888 code {@code FFA500FF}, hue 0.10784314, saturation 1.0, lightness 0.5, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.014bfep125F}.
     * <pre>
     * <font style='background-color: #FFA500;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA500; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA500;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFA500'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFA500'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFA500'>&nbsp;@&nbsp;</font><font style='background-color: #FFA500; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA500;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA500; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ORANGE = -0x1.014bfep125F;
    static { NAMED.put("orange", -0x1.014bfep125F); LIST.add(-0x1.014bfep125F); }

    /**
     * This color constant "orangered" has RGBA8888 code {@code FF4500FF}, hue 0.04509804, saturation 1.0, lightness 0.5, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.008bfep125F}.
     * <pre>
     * <font style='background-color: #FF4500;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF4500; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF4500;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF4500'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF4500'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF4500'>&nbsp;@&nbsp;</font><font style='background-color: #FF4500; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF4500;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF4500; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ORANGERED = -0x1.008bfep125F;
    static { NAMED.put("orangered", -0x1.008bfep125F); LIST.add(-0x1.008bfep125F); }

    /**
     * This color constant "orchid" has RGBA8888 code {@code DA70D6FF}, hue 0.8396226, saturation 0.41568628, lightness 0.64705884, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.ace1b4p126F}.
     * <pre>
     * <font style='background-color: #DA70D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA70D6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA70D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DA70D6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DA70D6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DA70D6'>&nbsp;@&nbsp;</font><font style='background-color: #DA70D6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA70D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA70D6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ORCHID = -0x1.ace1b4p126F;
    static { NAMED.put("orchid", -0x1.ace1b4p126F); LIST.add(-0x1.ace1b4p126F); }

    /**
     * This color constant "palegoldenrod" has RGBA8888 code {@code EEE8AAFF}, hue 0.15196079, saturation 0.26666665, lightness 0.8, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.55d1dcp126F}.
     * <pre>
     * <font style='background-color: #EEE8AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EEE8AA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EEE8AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EEE8AA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EEE8AA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EEE8AA'>&nbsp;@&nbsp;</font><font style='background-color: #EEE8AA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EEE8AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EEE8AA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALEGOLDENROD = -0x1.55d1dcp126F;
    static { NAMED.put("palegoldenrod", -0x1.55d1dcp126F); LIST.add(-0x1.55d1dcp126F); }

    /**
     * This color constant "palegreen" has RGBA8888 code {@code 98FB98FF}, hue 0.33333334, saturation 0.38823527, lightness 0.79019606, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.31f73p126F}.
     * <pre>
     * <font style='background-color: #98FB98;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #98FB98; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #98FB98;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #98FB98'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #98FB98'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #98FB98'>&nbsp;@&nbsp;</font><font style='background-color: #98FB98; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #98FB98;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #98FB98; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALEGREEN = -0x1.31f73p126F;
    static { NAMED.put("palegreen", -0x1.31f73p126F); LIST.add(-0x1.31f73p126F); }

    /**
     * This color constant "paleturquoise" has RGBA8888 code {@code AFEEEEFF}, hue 0.5, saturation 0.24705881, lightness 0.8098039, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.dddd5ep126F}.
     * <pre>
     * <font style='background-color: #AFEEEE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AFEEEE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AFEEEE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AFEEEE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AFEEEE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AFEEEE'>&nbsp;@&nbsp;</font><font style='background-color: #AFEEEE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AFEEEE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AFEEEE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALETURQUOISE = -0x1.dddd5ep126F;
    static { NAMED.put("paleturquoise", -0x1.dddd5ep126F); LIST.add(-0x1.dddd5ep126F); }

    /**
     * This color constant "palevioletred" has RGBA8888 code {@code DB7093FF}, hue 0.94548285, saturation 0.41960785, lightness 0.6490196, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.26e1b6p126F}.
     * <pre>
     * <font style='background-color: #DB7093;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB7093; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB7093;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DB7093'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DB7093'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DB7093'>&nbsp;@&nbsp;</font><font style='background-color: #DB7093; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB7093;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB7093; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALEVIOLETRED = -0x1.26e1b6p126F;
    static { NAMED.put("palevioletred", -0x1.26e1b6p126F); LIST.add(-0x1.26e1b6p126F); }

    /**
     * This color constant "papayawhip" has RGBA8888 code {@code FFEFD5FF}, hue 0.103174604, saturation 0.16470587, lightness 0.91764706, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.abdffep126F}.
     * <pre>
     * <font style='background-color: #FFEFD5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFEFD5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFEFD5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFEFD5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFEFD5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFEFD5'>&nbsp;@&nbsp;</font><font style='background-color: #FFEFD5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFEFD5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFEFD5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PAPAYAWHIP = -0x1.abdffep126F;
    static { NAMED.put("papayawhip", -0x1.abdffep126F); LIST.add(-0x1.abdffep126F); }

    /**
     * This color constant "peachpuff" has RGBA8888 code {@code FFDAB9FF}, hue 0.07857143, saturation 0.2745098, lightness 0.8627451, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.73b5fep126F}.
     * <pre>
     * <font style='background-color: #FFDAB9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFDAB9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFDAB9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFDAB9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFDAB9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFDAB9'>&nbsp;@&nbsp;</font><font style='background-color: #FFDAB9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFDAB9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFDAB9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PEACHPUFF = -0x1.73b5fep126F;
    static { NAMED.put("peachpuff", -0x1.73b5fep126F); LIST.add(-0x1.73b5fep126F); }

    /**
     * This color constant "peru" has RGBA8888 code {@code CD853FFF}, hue 0.08215964, saturation 0.5568628, lightness 0.52549016, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.7f0b9ap125F}.
     * <pre>
     * <font style='background-color: #CD853F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CD853F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CD853F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CD853F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CD853F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CD853F'>&nbsp;@&nbsp;</font><font style='background-color: #CD853F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CD853F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CD853F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PERU = -0x1.7f0b9ap125F;
    static { NAMED.put("peru", -0x1.7f0b9ap125F); LIST.add(-0x1.7f0b9ap125F); }

    /**
     * This color constant "pink" has RGBA8888 code {@code FFC0CBFF}, hue 0.97089946, saturation 0.24705881, lightness 0.87647057, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.9781fep126F}.
     * <pre>
     * <font style='background-color: #FFC0CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFC0CB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFC0CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFC0CB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFC0CB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFC0CB'>&nbsp;@&nbsp;</font><font style='background-color: #FFC0CB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFC0CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFC0CB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINK = -0x1.9781fep126F;
    static { NAMED.put("pink", -0x1.9781fep126F); LIST.add(-0x1.9781fep126F); }

    /**
     * This color constant "plum" has RGBA8888 code {@code DDA0DDFF}, hue 0.8333333, saturation 0.23921567, lightness 0.7470588, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.bb41bap126F}.
     * <pre>
     * <font style='background-color: #DDA0DD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DDA0DD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DDA0DD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DDA0DD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DDA0DD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DDA0DD'>&nbsp;@&nbsp;</font><font style='background-color: #DDA0DD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DDA0DD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DDA0DD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PLUM = -0x1.bb41bap126F;
    static { NAMED.put("plum", -0x1.bb41bap126F); LIST.add(-0x1.bb41bap126F); }

    /**
     * This color constant "powderblue" has RGBA8888 code {@code B0E0E6FF}, hue 0.51851857, saturation 0.2117647, lightness 0.79607844, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.cdc16p126F}.
     * <pre>
     * <font style='background-color: #B0E0E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0E0E6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0E0E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B0E0E6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B0E0E6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B0E0E6'>&nbsp;@&nbsp;</font><font style='background-color: #B0E0E6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0E0E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0E0E6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float POWDERBLUE = -0x1.cdc16p126F;
    static { NAMED.put("powderblue", -0x1.cdc16p126F); LIST.add(-0x1.cdc16p126F); }

    /**
     * This color constant "purple" has RGBA8888 code {@code 800080FF}, hue 0.8333334, saturation 0.5019608, lightness 0.2509804, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.0001p126F}.
     * <pre>
     * <font style='background-color: #800080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #800080; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #800080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #800080'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #800080'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #800080'>&nbsp;@&nbsp;</font><font style='background-color: #800080; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #800080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #800080; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURPLE = -0x1.0001p126F;
    static { NAMED.put("purple", -0x1.0001p126F); LIST.add(-0x1.0001p126F); }

    /**
     * This color constant "RED" has RGBA8888 code {@code FF0000FF}, hue 0.0, saturation 1.0, lightness 0.5, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.0001fep125F}.
     * <pre>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #FF0000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float RED = -0x1.0001fep125F;
    static { NAMED.put("RED", -0x1.0001fep125F); LIST.add(-0x1.0001fep125F); }

    /**
     * This color constant "rosybrown" has RGBA8888 code {@code BC8F8FFF}, hue 0.0, saturation 0.17647058, lightness 0.64901966, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.1f1f78p126F}.
     * <pre>
     * <font style='background-color: #BC8F8F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BC8F8F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BC8F8F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BC8F8F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BC8F8F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BC8F8F'>&nbsp;@&nbsp;</font><font style='background-color: #BC8F8F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BC8F8F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BC8F8F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ROSYBROWN = -0x1.1f1f78p126F;
    static { NAMED.put("rosybrown", -0x1.1f1f78p126F); LIST.add(-0x1.1f1f78p126F); }

    /**
     * This color constant "royalblue" has RGBA8888 code {@code 4169E1FF}, hue 0.625, saturation 0.62745094, lightness 0.5686275, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.c2d282p126F}.
     * <pre>
     * <font style='background-color: #4169E1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4169E1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4169E1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4169E1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4169E1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4169E1'>&nbsp;@&nbsp;</font><font style='background-color: #4169E1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4169E1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4169E1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ROYALBLUE = -0x1.c2d282p126F;
    static { NAMED.put("royalblue", -0x1.c2d282p126F); LIST.add(-0x1.c2d282p126F); }

    /**
     * This color constant "saddlebrown" has RGBA8888 code {@code 8B4513FF}, hue 0.06944444, saturation 0.47058827, lightness 0.30980393, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.268b16p125F}.
     * <pre>
     * <font style='background-color: #8B4513;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B4513; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B4513;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8B4513'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8B4513'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8B4513'>&nbsp;@&nbsp;</font><font style='background-color: #8B4513; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B4513;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B4513; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SADDLEBROWN = -0x1.268b16p125F;
    static { NAMED.put("saddlebrown", -0x1.268b16p125F); LIST.add(-0x1.268b16p125F); }

    /**
     * This color constant "salmon" has RGBA8888 code {@code FA8072FF}, hue 0.017156873, saturation 0.5333333, lightness 0.7137255, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.e501f4p125F}.
     * <pre>
     * <font style='background-color: #FA8072;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FA8072; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FA8072;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FA8072'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FA8072'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FA8072'>&nbsp;@&nbsp;</font><font style='background-color: #FA8072; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FA8072;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FA8072; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SALMON = -0x1.e501f4p125F;
    static { NAMED.put("salmon", -0x1.e501f4p125F); LIST.add(-0x1.e501f4p125F); }

    /**
     * This color constant "sandybrown" has RGBA8888 code {@code F4A460FF}, hue 0.07657658, saturation 0.5803921, lightness 0.6666667, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.c149e8p125F}.
     * <pre>
     * <font style='background-color: #F4A460;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F4A460; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F4A460;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F4A460'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F4A460'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F4A460'>&nbsp;@&nbsp;</font><font style='background-color: #F4A460; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F4A460;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F4A460; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SANDYBROWN = -0x1.c149e8p125F;
    static { NAMED.put("sandybrown", -0x1.c149e8p125F); LIST.add(-0x1.c149e8p125F); }

    /**
     * This color constant "seagreen" has RGBA8888 code {@code 2E8B57FF}, hue 0.40681005, saturation 0.36470592, lightness 0.3627451, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.af165cp125F}.
     * <pre>
     * <font style='background-color: #2E8B57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2E8B57; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2E8B57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2E8B57'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2E8B57'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2E8B57'>&nbsp;@&nbsp;</font><font style='background-color: #2E8B57; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2E8B57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2E8B57; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SEAGREEN = -0x1.af165cp125F;
    static { NAMED.put("seagreen", -0x1.af165cp125F); LIST.add(-0x1.af165cp125F); }

    /**
     * This color constant "seashell" has RGBA8888 code {@code FFF5EEFF}, hue 0.068627454, saturation 0.06666666, lightness 0.9666667, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.ddebfep126F}.
     * <pre>
     * <font style='background-color: #FFF5EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFF5EE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFF5EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFF5EE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFF5EE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFF5EE'>&nbsp;@&nbsp;</font><font style='background-color: #FFF5EE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFF5EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFF5EE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SEASHELL = -0x1.ddebfep126F;
    static { NAMED.put("seashell", -0x1.ddebfep126F); LIST.add(-0x1.ddebfep126F); }

    /**
     * This color constant "sienna" has RGBA8888 code {@code A0522DFF}, hue 0.05362319, saturation 0.45098042, lightness 0.4019608, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.5aa54p125F}.
     * <pre>
     * <font style='background-color: #A0522D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A0522D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A0522D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A0522D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A0522D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A0522D'>&nbsp;@&nbsp;</font><font style='background-color: #A0522D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A0522D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A0522D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SIENNA = -0x1.5aa54p125F;
    static { NAMED.put("sienna", -0x1.5aa54p125F); LIST.add(-0x1.5aa54p125F); }

    /**
     * This color constant "silver" has RGBA8888 code {@code C0C0C0FF}, hue 0.0, saturation 0.0, lightness 0.7529412, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.81818p126F}.
     * <pre>
     * <font style='background-color: #C0C0C0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0C0C0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0C0C0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C0C0C0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C0C0C0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C0C0C0'>&nbsp;@&nbsp;</font><font style='background-color: #C0C0C0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0C0C0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0C0C0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER = -0x1.81818p126F;
    static { NAMED.put("silver", -0x1.81818p126F); LIST.add(-0x1.81818p126F); }

    /**
     * This color constant "skyblue" has RGBA8888 code {@code 87CEEBFF}, hue 0.54833335, saturation 0.39215684, lightness 0.7254902, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.d79d0ep126F}.
     * <pre>
     * <font style='background-color: #87CEEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #87CEEB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #87CEEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #87CEEB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #87CEEB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #87CEEB'>&nbsp;@&nbsp;</font><font style='background-color: #87CEEB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #87CEEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #87CEEB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SKYBLUE = -0x1.d79d0ep126F;
    static { NAMED.put("skyblue", -0x1.d79d0ep126F); LIST.add(-0x1.d79d0ep126F); }

    /**
     * This color constant "slateblue" has RGBA8888 code {@code 6A5ACDFF}, hue 0.6898551, saturation 0.4509804, lightness 0.5784314, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.9ab4d4p126F}.
     * <pre>
     * <font style='background-color: #6A5ACD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A5ACD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A5ACD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6A5ACD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6A5ACD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6A5ACD'>&nbsp;@&nbsp;</font><font style='background-color: #6A5ACD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A5ACD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A5ACD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SLATEBLUE = -0x1.9ab4d4p126F;
    static { NAMED.put("slateblue", -0x1.9ab4d4p126F); LIST.add(-0x1.9ab4d4p126F); }

    /**
     * This color constant "slategray" has RGBA8888 code {@code 708090FF}, hue 0.5833333, saturation 0.12549022, lightness 0.5019608, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.2100ep126F}.
     * <pre>
     * <font style='background-color: #708090;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #708090; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #708090;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #708090'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #708090'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #708090'>&nbsp;@&nbsp;</font><font style='background-color: #708090; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #708090;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #708090; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SLATEGRAY = -0x1.2100ep126F;
    static { NAMED.put("slategray", -0x1.2100ep126F); LIST.add(-0x1.2100ep126F); }

    /**
     * This color constant "slategrey" has RGBA8888 code {@code 708090FF}, hue 0.5833333, saturation 0.12549022, lightness 0.5019608, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.2100ep126F}.
     * <pre>
     * <font style='background-color: #708090;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #708090; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #708090;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #708090'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #708090'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #708090'>&nbsp;@&nbsp;</font><font style='background-color: #708090; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #708090;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #708090; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SLATEGREY = -0x1.2100ep126F;
    static { NAMED.put("slategrey", -0x1.2100ep126F); LIST.add(-0x1.2100ep126F); }

    /**
     * This color constant "snow" has RGBA8888 code {@code FFFAFAFF}, hue 0.0, saturation 0.019607842, lightness 0.9901961, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.f5f5fep126F}.
     * <pre>
     * <font style='background-color: #FFFAFA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFAFA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFAFA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFAFA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFAFA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFAFA'>&nbsp;@&nbsp;</font><font style='background-color: #FFFAFA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFAFA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFAFA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SNOW = -0x1.f5f5fep126F;
    static { NAMED.put("snow", -0x1.f5f5fep126F); LIST.add(-0x1.f5f5fep126F); }

    /**
     * This color constant "springgreen" has RGBA8888 code {@code 00FF7FFF}, hue 0.41633987, saturation 1.0, lightness 0.5, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.fffep125F}.
     * <pre>
     * <font style='background-color: #00FF7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF7F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FF7F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FF7F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FF7F'>&nbsp;@&nbsp;</font><font style='background-color: #00FF7F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF7F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SPRINGGREEN = -0x1.fffep125F;
    static { NAMED.put("springgreen", -0x1.fffep125F); LIST.add(-0x1.fffep125F); }

    /**
     * This color constant "steelblue" has RGBA8888 code {@code 4682B4FF}, hue 0.5757576, saturation 0.43137255, lightness 0.49019608, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.69048cp126F}.
     * <pre>
     * <font style='background-color: #4682B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4682B4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4682B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4682B4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4682B4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4682B4'>&nbsp;@&nbsp;</font><font style='background-color: #4682B4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4682B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4682B4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STEELBLUE = -0x1.69048cp126F;
    static { NAMED.put("steelblue", -0x1.69048cp126F); LIST.add(-0x1.69048cp126F); }

    /**
     * This color constant "TAN" has RGBA8888 code {@code D2B48CFF}, hue 0.0952381, saturation 0.2745098, lightness 0.6862745, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.1969a4p126F}.
     * <pre>
     * <font style='background-color: #D2B48C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2B48C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2B48C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D2B48C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D2B48C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D2B48C'>&nbsp;@&nbsp;</font><font style='background-color: #D2B48C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2B48C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2B48C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TAN = -0x1.1969a4p126F;
    static { NAMED.put("TAN", -0x1.1969a4p126F); LIST.add(-0x1.1969a4p126F); }

    /**
     * This color constant "teal" has RGBA8888 code {@code 008080FF}, hue 0.5, saturation 0.5019608, lightness 0.2509804, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.01p126F}.
     * <pre>
     * <font style='background-color: #008080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #008080; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #008080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #008080'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #008080'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #008080'>&nbsp;@&nbsp;</font><font style='background-color: #008080; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #008080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #008080; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TEAL = -0x1.01p126F;
    static { NAMED.put("teal", -0x1.01p126F); LIST.add(-0x1.01p126F); }

    /**
     * This color constant "thistle" has RGBA8888 code {@code D8BFD8FF}, hue 0.8333333, saturation 0.09803921, lightness 0.7980392, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.b17fbp126F}.
     * <pre>
     * <font style='background-color: #D8BFD8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D8BFD8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D8BFD8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D8BFD8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D8BFD8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D8BFD8'>&nbsp;@&nbsp;</font><font style='background-color: #D8BFD8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D8BFD8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D8BFD8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float THISTLE = -0x1.b17fbp126F;
    static { NAMED.put("thistle", -0x1.b17fbp126F); LIST.add(-0x1.b17fbp126F); }

    /**
     * This color constant "tomato" has RGBA8888 code {@code FF6347FF}, hue 0.025362318, saturation 0.7215686, lightness 0.6392157, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.8ec7fep125F}.
     * <pre>
     * <font style='background-color: #FF6347;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6347; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6347;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF6347'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF6347'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF6347'>&nbsp;@&nbsp;</font><font style='background-color: #FF6347; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6347;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6347; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TOMATO = -0x1.8ec7fep125F;
    static { NAMED.put("tomato", -0x1.8ec7fep125F); LIST.add(-0x1.8ec7fep125F); }

    /**
     * This color constant "turquoise" has RGBA8888 code {@code 40E0D0FF}, hue 0.48333335, saturation 0.62745094, lightness 0.5647059, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.a1c08p126F}.
     * <pre>
     * <font style='background-color: #40E0D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #40E0D0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #40E0D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #40E0D0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #40E0D0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #40E0D0'>&nbsp;@&nbsp;</font><font style='background-color: #40E0D0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #40E0D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #40E0D0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TURQUOISE = -0x1.a1c08p126F;
    static { NAMED.put("turquoise", -0x1.a1c08p126F); LIST.add(-0x1.a1c08p126F); }

    /**
     * This color constant "violet" has RGBA8888 code {@code EE82EEFF}, hue 0.8333333, saturation 0.4235294, lightness 0.72156864, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.dd05dcp126F}.
     * <pre>
     * <font style='background-color: #EE82EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EE82EE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EE82EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EE82EE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EE82EE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EE82EE'>&nbsp;@&nbsp;</font><font style='background-color: #EE82EE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EE82EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EE82EE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIOLET = -0x1.dd05dcp126F;
    static { NAMED.put("violet", -0x1.dd05dcp126F); LIST.add(-0x1.dd05dcp126F); }

    /**
     * This color constant "wheat" has RGBA8888 code {@code F5DEB3FF}, hue 0.10858586, saturation 0.2588235, lightness 0.83137256, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.67bdeap126F}.
     * <pre>
     * <font style='background-color: #F5DEB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5DEB3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5DEB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F5DEB3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F5DEB3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F5DEB3'>&nbsp;@&nbsp;</font><font style='background-color: #F5DEB3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5DEB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5DEB3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHEAT = -0x1.67bdeap126F;
    static { NAMED.put("wheat", -0x1.67bdeap126F); LIST.add(-0x1.67bdeap126F); }

    /**
     * This color constant "white" has RGBA8888 code {@code FFFFFFFF}, hue 0.0, saturation 0.0, lightness 1.0, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.fffffep126F}.
     * <pre>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #FFFFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE = -0x1.fffffep126F;
    static { NAMED.put("white", -0x1.fffffep126F); LIST.add(-0x1.fffffep126F); }

    /**
     * This color constant "whitesmoke" has RGBA8888 code {@code F5F5F5FF}, hue 0.0, saturation 0.0, lightness 0.9607843, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.ebebeap126F}.
     * <pre>
     * <font style='background-color: #F5F5F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5F5F5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5F5F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F5F5F5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F5F5F5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F5F5F5'>&nbsp;@&nbsp;</font><font style='background-color: #F5F5F5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5F5F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5F5F5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITESMOKE = -0x1.ebebeap126F;
    static { NAMED.put("whitesmoke", -0x1.ebebeap126F); LIST.add(-0x1.ebebeap126F); }

    /**
     * This color constant "yellow" has RGBA8888 code {@code FFFF00FF}, hue 0.16666667, saturation 1.0, lightness 0.5, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.01fffep125F}.
     * <pre>
     * <font style='background-color: #FFFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFF00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFF00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFF00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFF00'>&nbsp;@&nbsp;</font><font style='background-color: #FFFF00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFF00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float YELLOW = -0x1.01fffep125F;
    static { NAMED.put("yellow", -0x1.01fffep125F); LIST.add(-0x1.01fffep125F); }

    /**
     * This color constant "yellowgreen" has RGBA8888 code {@code 9ACD32FF}, hue 0.22150537, saturation 0.60784316, lightness 0.5, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.659b34p125F}.
     * <pre>
     * <font style='background-color: #9ACD32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9ACD32; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9ACD32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9ACD32'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9ACD32'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9ACD32'>&nbsp;@&nbsp;</font><font style='background-color: #9ACD32; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9ACD32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9ACD32; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float YELLOWGREEN = -0x1.659b34p125F;
    static { NAMED.put("yellowgreen", -0x1.659b34p125F); LIST.add(-0x1.659b34p125F); }

    /**
     * All names for colors in this palette, in alphabetical order. You can fetch the corresponding packed float color
     * by looking up a name in {@link #NAMED}.
     */
    public static final Array<String> NAMES = NAMED.keys().toArray();
    static { NAMES.sort(); }

    /**
     * Appends standard RGBA Color instances to the map in {@link Colors}, using the names in {@link #NAMES} (which
     * are "Title Cased" instead of "ALL_UPPER_CASE"). This doesn't need any changes to be made to Colors in order for
     * it to be compatible; just remember that the colors originally in Colors use "UPPER_CASE" and these use "Title
     * Case".
     */
    public static void appendToKnownColors(){
        for(ObjectFloatMap.Entry<String> ent : NAMED) {
            Colors.put(ent.key, ColorTools.toColor(new Color(), ent.value));
        }
    }

}
