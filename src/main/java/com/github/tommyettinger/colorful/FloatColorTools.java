package com.github.tommyettinger.colorful;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.NumberUtils;

/**
 * Various utility methods for working with colors encoded as packed YCwCmA floats.
 * <br>
 * Created by Tommy Ettinger on 9/21/2019.
 */
public class FloatColorTools {
    /**
     * Gets the red channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
     * @param encoded a color as a packed float that can be obtained by {@link FloatColorTools#floatColor(float, float, float, float)}
     * @return an int from 0 to 255, inclusive, representing the red channel value of the given encoded color
     */
    public static int redInt(final float encoded)
    {
        final int decoded = NumberUtils.floatToIntBits(encoded);
        return (decoded & 0xff) + (((decoded >>> 7 & 0x1fe) - 255) * 3 >>> 4) - (((decoded >>> 15 & 0x1fe) - 255) >>> 2);
    }

    /**
     * Gets the green channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
     * @param encoded a color as a packed float that can be obtained by {@link FloatColorTools#floatColor(float, float, float, float)}
     * @return an int from 0 to 255, inclusive, representing the green channel value of the given encoded color
     */
    public static int greenInt(final float encoded)
    {
        final int decoded = NumberUtils.floatToIntBits(encoded);
        return (decoded & 0xff) - (((decoded >>> 7 & 0x1fe) - 255) * 3 >> 4) + (((decoded >>> 15 & 0x1fe) - 255) >> 2);
    }

    /**
     * Gets the blue channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
     * @param encoded a color as a packed float that can be obtained by {@link FloatColorTools#floatColor(float, float, float, float)}
     * @return an int from 0 to 255, inclusive, representing the blue channel value of the given encoded color
     */
    public static int blueInt(final float encoded)
    {
        final int decoded = NumberUtils.floatToIntBits(encoded);
        return (decoded & 0xff) - (((decoded >>> 7 & 0x1fe) - 255) * 3 >> 4) - (((decoded >>> 15 & 0x1fe) - 255) >> 2);
    }

    /**
     * Gets the alpha channel value of the given encoded color, as an even int ranging from 0 to 254, inclusive. Because
     * of how alpha is stored in libGDX, no odd-number values are possible for alpha.
     * @param encoded a color as a packed float that can be obtained by {@link FloatColorTools#floatColor(float, float, float, float)}
     * @return an even int from 0 to 254, inclusive, representing the alpha channel value of the given encoded color
     */
    public static int alphaInt(final float encoded)
    {
        return (NumberUtils.floatToIntBits(encoded) & 0xfe000000) >>> 24;
    }
    
    /**
     * Gets the red channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
     * @param encoded a color as a packed float that can be obtained by {@link FloatColorTools#floatColor(float, float, float, float)}
     * @return a float from 0.0f to 1.0f, inclusive, representing the red channel value of the given encoded color
     */
    public static float red(final float encoded)
    {
        final int decoded = NumberUtils.floatToIntBits(encoded);
        return (decoded & 0xff) * 0x1.010102p-8f + ((decoded >>> 8 & 0xff) - 127.5f) * (0x1.414142p-9f) - ((decoded >>> 16 & 0xff) - 127.5f) * 0x1.010102p-9f;
    }

    /**
     * Gets the green channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
     * @param encoded a color as a packed float that can be obtained by {@link FloatColorTools#floatColor(float, float, float, float)}
     * @return a float from 0.0f to 1.0f, inclusive, representing the green channel value of the given encoded color
     */
    public static float green(final float encoded)
    {
        final int decoded = NumberUtils.floatToIntBits(encoded);
        return (decoded & 0xff) * 0x1.010102p-8f - (((decoded >>> 8 & 0xff) - 127.5f) * 0x1.818184p-10f) + ((decoded >>> 16 & 0xff) - 127.5f) * 0x1.010102p-9f;
    }

    /**
     * Gets the blue channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
     * @param encoded a color as a packed float that can be obtained by {@link FloatColorTools#floatColor(float, float, float, float)}
     * @return a float from 0.0f to 1.0f, inclusive, representing the blue channel value of the given encoded color
     */
    public static float blue(final float encoded)
    {
        final int decoded = NumberUtils.floatToIntBits(encoded);
        return (decoded & 0xff) * 0x1.010102p-8f - (((decoded >>> 8 & 0xff) - 127.5f) * 0x1.818184p-10f) - ((decoded >>> 16 & 0xff) - 127.5f) * 0x1.010102p-9f;
    }

    /**
     * Gets the alpha channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
     * @param encoded a color as a packed float that can be obtained by {@link FloatColorTools#floatColor(float, float, float, float)}
     * @return a float from 0.0f to 1.0f, inclusive, representing the alpha channel value of the given encoded color
     */
    public static float alpha(final float encoded)
    {
        return ((NumberUtils.floatToIntBits(encoded) & 0xfe000000) >>> 24) * 0.003937008f;
    }

    /**
     * Gets the approximate saturation of the given encoded color, as a float ranging from 0.0f to 1.0f, inclusive.
     * This actually gets the "colorfulness" of the given color, not its saturation, which is subtly different.
     * @param encoded a color as a packed float that can be obtained by {@link FloatColorTools#floatColor(float, float, float, float)}
     * @return the approximate saturation of the color from 0.0 (a grayscale color; inclusive) to 1.0 (a
     * bright color, exclusive)
     */
    public static float saturation(final float encoded) {
        final int e = NumberUtils.floatToIntBits(encoded);
        final double cw = (e >>> 8 & 255) - 127.5, cm = (e >>> 16 & 255) - 127.5;
        return (float) (Math.sqrt(cw * cw + cm * cm) * 0.00554593553871802);
    }

    /**
     * Gets the hue of the given encoded color, as a float from 0f (inclusive, red and approaching orange if increased)
     * to 1f (exclusive, red and approaching purple if decreased).
     * @param encoded a color as a packed float that can be obtained by {@link FloatColorTools#floatColor(float, float, float, float)}
     * @return The hue of the color from 0.0 (red, inclusive) towards orange, then yellow, and
     * eventually to purple before looping back to almost the same red (1.0, exclusive)
     */
    public static float hue(final float encoded) {
        final int e = NumberUtils.floatToIntBits(encoded);
        final float cw = (e >>> 8 & 255) - 127.5f, cm = (e >>> 16 & 255) - 127.5f;
        if ( cw * cw + cm + cm < 0.125f )
        {
            // it's grayscale
            return 0f;
        }
        else
        {
            // it has color
            float angle = TrigTools.atan2_(cm, cw) + 0.125f;
            return angle - (int)angle;
        }
    }
    
    /**
     * The "luma" of the given packed float, which is like its lightness, in YCwCm format; ranges from 0.0f to
     * 1.0f . You can go back to an RGB color as a packed float with {@link #floatGetYCwCm(float, float, float, float)}.
     * YCwCm is useful for modifications to colors:
     * <ul>
     *     <li>You can get a grayscale version of a color by setting Cw and Cm to 0.5,</li>
     *     <li>You can desaturate by subtracting 0.5, multiplying Cw and Cm by a number between 0 and 1, and adding 0.5
     *     afterwards,</li>
     *     <li>you can oversaturate by subtracting 0.5, multiplying Cw and Cm by a number greater than 1, and adding 0.5
     *     afterwards,</li>
     *     <li>you can lighten or darken by increasing or decreasing luma,</li>
     *     <li>and so on and so forth.</li>
     * </ul>
     * There are aesthetic reasons to adjust just one of Cw or Cm for some effect; multiplying Cw by
     * a number greater than 1 will make warm colors warmer and cool colors cooler, for instance, while adding a
     * positive number to Cw will make most colors approach a warmer hue (some will become more gray).
     * @param encoded a packed float
     * @return the luma as a float from 0.0f to 1.0f
     */
    public static float luma(final float encoded)
    {
        return (NumberUtils.floatToIntBits(encoded) & 0xff) * 0x1.010102p-8f;
//        final int bits = NumberUtils.floatToIntBits(encoded);
//        return (bits & 0xFF) * 0x3p-11f + (bits >>> 8 & 0xFF) * 0x1p-9f + (bits >>> 16 & 0xFF) * 0x1p-11f;
    }
    
    /**
     * The "chroma warm" of the given packed float, which when combined with chroma mild describes the shade and
     * saturation of a color, in YCwCm format; ranges from 0f to 1f . You can go back to an RGB color as
     * a packed float with {@link #floatGetYCwCm(float, float, float, float)}. YCwCm is useful for modifications to
     * colors:
     * <ul>
     *     <li>You can get a grayscale version of a color by setting Cw and Cm to 0.5,</li>
     *     <li>You can desaturate by subtracting 0.5, multiplying Cw and Cm by a number between 0 and 1, and adding 0.5
     *     afterwards,</li>
     *     <li>you can oversaturate by subtracting 0.5, multiplying Cw and Cm by a number greater than 1, and adding 0.5
     *     afterwards,</li>
     *     <li>you can lighten or darken by increasing or decreasing luma,</li>
     *     <li>and so on and so forth.</li>
     * </ul>
     * There are aesthetic reasons to adjust just one of Cw or Cm for some effect; multiplying Cw by
     * a number greater than 1 will make warm colors warmer and cool colors cooler, for instance, while adding a
     * positive number to Cw will make most colors approach a warmer hue (some will become more gray).
     * @param encoded a color encoded as a packed float, as by {@link FloatColorTools#floatColor(float, float, float, float)}
     * @return the chroma warm as a float from 0f to 1f
     */
    public static float chromaWarm(final float encoded)
    {
        return ((NumberUtils.floatToIntBits(encoded) >>> 8 & 0xff)) * 0x1.010102p-8f;
    }

    /**
     * The "chroma mild" of the given packed float, which when combined with chroma warm describes the shade and
     * saturation of a color, in YCwCm format; ranges from 0f to 1f .
     * YCwCm is useful for modifications to colors:
     * <ul>
     *     <li>You can get a grayscale version of a color by setting Cw and Cm to 0.5,</li>
     *     <li>You can desaturate by subtracting 0.5, multiplying Cw and Cm by a number between 0 and 1, and adding 0.5
     *     afterwards,</li>
     *     <li>you can oversaturate by subtracting 0.5, multiplying Cw and Cm by a number greater than 1, and adding 0.5
     *     afterwards,</li>
     *     <li>you can lighten or darken by increasing or decreasing luma,</li>
     *     <li>and so on and so forth.</li>
     * </ul>
     * There are aesthetic reasons to adjust just one of Cw or Cm for some effect; multiplying Cw by
     * a number greater than 1 will make warm colors warmer and cool colors cooler, for instance, while adding a
     * positive number to Cw will make most colors approach a warmer hue (some will become more gray).
     * @param encoded a color encoded as a packed float, as by {@link FloatColorTools#floatColor(float, float, float, float)}
     * @return the chroma mild as a float from 0f to 1f
     */
    public static float chromaMild(final float encoded)
    {
        return ((NumberUtils.floatToIntBits(encoded) >>> 16 & 0xff)) * 0x1.010102p-8f;
    }

    /**
     * Given a color stored as a packed float and an alpha multiplier to affect that color (between 0f and 1f, inclusive
     * and clamped in that range), this makes a packed float color that has the same red, green, and blue channels but
     * has its own alpha multiplied by {@code alpha}, without constructing any objects along the way. If you want to set
     * the alpha without considering its current value, you can use {@link #setAlpha(float, float)}
     *
     * @param encodedColor a color encoded as a packed float, as by {@link FloatColorTools#floatColor(float, float, float, float)}
     * @param alpha  between 0.0 and 1.0 inclusive, the alpha to multiply the color's own alpha by
     * @return a color encoded as a packed float, using color's RGB channels but with its A channel times {@code alpha}
     */
    public static float multiplyAlpha(float encodedColor, float alpha) {
        final int bits = NumberUtils.floatToIntBits(encodedColor);
        return NumberUtils.intBitsToFloat(bits & 0xFFFFFF
                | (MathUtils.clamp((int) ((bits >>> 24) * alpha), 0, 255) << 24 & 0xFE000000));
    }

    /**
     * Given a color stored as a packed float, and a desired alpha to set for that color (between 0.0 and 1.0, inclusive
     * and clamped in that range), this makes a new float that has the same red, green, and blue channels but has been
     * set to the given alpha, without constructing any objects along the way. This does not consider the current alpha
     * of the encoded color; if you want to do that, you can use {@link #multiplyAlpha(float, float)}.
     *
     * @param encodedColor a color encoded as a packed float, as by {@link FloatColorTools#floatColor(float, float, float, float)}
     * @param alpha        between 0.0 and 1.0 inclusive, the alpha to set into the returned packed color
     * @return another color encoded as a packed float, using encodedColor's RGB channels and the given alpha
     */
    public static float setAlpha(float encodedColor, float alpha) {
        return NumberUtils.intBitsToFloat(NumberUtils.floatToIntBits(encodedColor) & 0xFFFFFF
                | (MathUtils.clamp((int) (255f * alpha), 0, 255) << 24 & 0xFE000000));
    }

    /**
     * Gets a packed float representation of a color given as 4 float components, here, Y (luma or lightness), Cw
     * (chromatic warmth), Cm (chromatic mildness), and A (alpha or opacity). As long as you use a shader with
     * {@link Basics#fragmentShader} as its shader, colors passed with
     * {@link com.badlogic.gdx.graphics.g2d.Batch#setPackedColor(float)} will be interpreted as YCwCmA.
     Luma should be between 0 and
     * 1, inclusive, with 0 used for very dark colors (almost only black), and 1 used for very light colors (almost only
     * white). The two chroma values range from 0.0 to 1.0, and there's some aesthetic value in
     * changing just one chroma value. When warm is high and mild is low, the color is more reddish; when both are low
     * it is more bluish, and when mild is high and warm is low, the color tends to be greenish, and when both are high
     * it tends to be brown or yellow. When warm and mild are both near 0.5f, the color is closer to gray.  Alpha is
     * the multiplicative opacity of the color, and acts like RGBA's alpha.
     * <br>
     * This method clamps the resulting color's byte values, so any values can technically be given to this as luma,
     * warm, and mild, but they will only be reversible from the returned float color to the original Y, Cw, and Cm
     * values if the original values were in the range that {@link #chromaWarm(float)}, {@link #chromaMild(float)}, and
     * {@link #luma(float)} return.
     *
     * @param luma       0f to 1f, luma or Y component of YCwCmA, with 0.5f meaning "no change" and 1f brightening
     * @param warm       0f to 1f, "chroma warm" or Cw component of YCwCmA, with 1f more red or yellow
     * @param mild       0f to 1f, "chroma mild" or Cm component of YCwCmA, with 1f more green or yellow
     * @param alpha      0f to 1f, 0f makes the color transparent and 1f makes it opaque 
     * @return a float encoding a color with the given properties
     */
    public static float floatColor(float luma, float warm, float mild, float alpha) {
        return NumberUtils.intBitsToFloat(((int) (alpha * 255) << 24 & 0xFE000000) | ((int) (mild * 255) << 16 & 0xFF0000)
                | ((int) (warm * 255) << 8 & 0xFF00) | ((int) (luma * 255) & 0xFF));
    }

    ///TODO: we're done converting only up to here, maybe some more is done later in the file
    
    /**
     * Gets a color as a packed float given floats representing hue, saturation, value, and opacity.
     * All parameters should normally be between 0 and 1 inclusive, though hue is tolerated if it is negative down to
     * -6f at the lowest or positive up to any finite value, though precision loss may affect the color if the hue is
     * too large. A hue of 0 is red, progressively higher hue values go to orange, yellow, green, blue, and purple
     * before wrapping around to red as it approaches 1. A saturation of 0 is grayscale, a saturation of 1 is brightly
     * colored, and values close to 1 will usually appear more distinct than values close to 0, especially if the
     * hue is different (saturation below 0.0039f is treated specially here, and does less work to simply get a color
     * between black and white with the given opacity). The value is similar to lightness; a value of 0.0039 or less is
     * always black (also using a shortcut if this is the case, respecting opacity), while a value of 1 is as bright as
     * the color gets with the given saturation and value. To get a value of white, you would need both a value of 1 and
     * a saturation of 0.
     *
     * @param hue        0f to 1f, color wheel position
     * @param saturation 0f to 1f, 0f is grayscale and 1f is brightly colored
     * @param value      0f to 1f, 0f is black and 1f is bright or light
     * @param opacity    0f to 1f, 0f is fully transparent and 1f is opaque
     * @return a float encoding a color with the given properties
     */
    public static float floatGetHSV(float hue, float saturation, float value, float opacity) {
        if (saturation <= 0.0039f) {
            return floatColor(value, value, value, opacity);
        } else if (value <= 0.0039f) {
            return NumberUtils.intBitsToFloat((int) (opacity * 255f) << 24 & 0xFE000000);
        } else {
            final float h = ((hue + 6f) % 1f) * 6f;
            final int i = (int) h;
            value = MathUtils.clamp(value, 0f, 1f);
            saturation = MathUtils.clamp(saturation, 0f, 1f);
            final float a = value * (1 - saturation);
            final float b = value * (1 - saturation * (h - i));
            final float c = value * (1 - saturation * (1 - (h - i)));

            switch (i) {
                case 0:
                    return floatColor(value, c, a, opacity);
                case 1:
                    return floatColor(b, value, a, opacity);
                case 2:
                    return floatColor(a, value, c, opacity);
                case 3:
                    return floatColor(a, b, value, opacity);
                case 4:
                    return floatColor(c, a, value, opacity);
                default:
                    return floatColor(value, a, b, opacity);
            }
        }
    }
    
    /**
     * Gets a color as a packed float given floats representing luma (Y, akin to lightness), chroma warm (Cw, one of two
     * kinds of chroma used here), chroma mild (Cm, the other kind of chroma), and opacity. Luma should be between 0 and
     * 1, inclusive, with 0 used for very dark colors including but not limited to black, and 1 used for very light
     * colors including but not limited to white. The two chroma values range from -1.0 to 1.0, unlike YCbCr and YCoCg,
     * and also unlike those color spaces, there's some aesthetic value in changing just one chroma value. When warm is
     * high and mild is low, the color is more reddish; when both are low it is more bluish, and when mild is high and
     * warm is low, the color tends to be greenish, and when both are high it tends to be brown or yellow. When warm and
     * mild are both near 0.0f, the color is closer to gray. Because chroma values are centered on 0.0f, you can multiply
     * them by a value like 0.5f to halve the colorfulness of the color.
     * <br>
     * This method clamps the resulting color's RGB values, so any values can technically be given to this as luma,
     * warm, and mild, but they will only be reversible from the returned float color to the original Y, Cw, and Cm
     * values if the original values were in the range that {@link #chromaWarm(float)}, {@link #chromaMild(float)}, and
     * {@link #luma(float)} return.
     *
     * @param luma       0f to 1f, luma or Y component of YCwCm
     * @param warm       -1f to 1f, "chroma warm" or Cw component of YCwCm, with 1f more red or yellow
     * @param mild       -1f to 1f, "chroma mild" or Cm component of YCwCm, with 1f more green or yellow
     * @param opacity    0f to 1f, 0f is fully transparent and 1f is opaque
     * @return a float encoding a color with the given properties
     */
    public static float floatGetYCwCm(float luma, float warm, float mild, float opacity) {
        // the color solid should be:

        //                   > warm >
        // blue    violet     red
        // cyan     gray      orange
        // green    neon      yellow
        //  \/ mild \/

        // so, warm is effectively defined as the presence of red.
        // and mild is, effectively, presence of green.
        // negative warm or negative mild will each contribute to blue.
        // luma is defined as (r * 3 + g * 4 + b) / 8
        // or r * 0.375f + g * 0.5f + b * 0.125f
        // warm is the warm-cool axis, with positive warm between red and yellow and negative warm between blue and green
        // warm is defined as (r - b), with range from -1 to 1
        // mild is the green-purple axis, with positive mild between green and yellow, negative mild between blue and red
        // mild is defined as (g - b), with range from -1 to 1

        //r = (warm * 5 - mild * 4 + luma * 8) / 8; r5 - b5 - g4 + b4 + r3 + g4 + b1
        //g = (mild * 4 - warm * 3 + luma * 8) / 8; g4 - b4 - r3 + b3 + r3 + g4 + b1
        //b = (luma * 8 - warm * 3 - mild * 4) / 8; r3 + g4 + b1 - r3 + b3 - g4 + b4
        return floatColor(MathUtils.clamp(luma + warm * 0.625f - mild * 0.5f, 0f, 1f),
                MathUtils.clamp(luma + mild * 0.5f - warm * 0.375f, 0f, 1f),
                MathUtils.clamp(luma - warm * 0.375f - mild * 0.5f, 0f, 1f), opacity);
    }


    /**
     * Converts a packed float color in the format produced by {@link FloatColorTools#floatColor(float, float, float, float)} to an RGBA8888 int. This format of
     * int can be used with Pixmap and in some other places in libGDX.
     * @param packed a packed float color, as produced by {@link FloatColorTools#floatColor(float, float, float, float)}
     * @return an RGBA8888 int color
     */
    public static int floatToInt(final float packed)
    {
        return Integer.reverseBytes(NumberUtils.floatToIntBits(packed));
    }

    /**
     * Gets a variation on the Color basis as a packed float that has its hue, saturation, and value adjusted by the
     * specified amounts. Takes floats representing the amounts of change to apply to hue, saturation, and value; these
     * can be between -1f and 1f. Returns a float that can be used as a packed or encoded color with methods like
     * {@link com.badlogic.gdx.graphics.g2d.Batch#setPackedColor(float)}, or in various SquidLib classes like SparseLayers or
     * SquidLayers. The float is likely to be different than the result of {@link FloatColorTools#floatColor(float, float, float, float)} unless hue,
     * saturation, and value are all 0. This won't modify the given NamedColor, nor will it allocate any objects.
     * <br>
     * The parameters this takes all specify additive changes for a color component, clamping the final values so they
     * can't go above 1 or below 0, with an exception for hue, which can rotate around if lower or higher hues would be
     * used. As an example, if you give this 0.4f for saturation, and the current color has saturation 0.7f, then the
     * resulting color will have 1f for saturation. If you gave this -0.1f for saturation and the current color still
     * has saturation 0.7f, then resulting color will have 0.6f for saturation.
     *
     * @param basis      a Color or NamedColor to use as the starting point; will not be modified itself
     * @param hue        -1f to 1f, the hue change that can be applied to the new float color
     * @param saturation -1f to 1f, the saturation change that can be applied to the new float color
     * @param value      -1f to 1f, the value/brightness change that can be applied to the new float color
     * @return a float encoding a variation of basis with the given changes
     */
    public static float toEditedFloat(Color basis, float hue, float saturation, float value) {
        return toEditedFloat(basis, hue, saturation, value, 0f);
    }

    /**
     * Gets a variation on the Color basis as a packed float that has its hue, saturation, value, and opacity adjusted
     * by the specified amounts. Takes floats representing the amounts of change to apply to hue, saturation, value, and
     * opacity; these can be between -1f and 1f. Returns a float that can be used as a packed or encoded color with
     * methods like {@link com.badlogic.gdx.graphics.g2d.Batch#setPackedColor(float)}, or in various SquidLib classes like
     * SparseLayers or SquidLayers. The float is likely to be different than the result of {@link FloatColorTools#floatColor(float, float, float, float)} unless
     * hue saturation, value, and opacity are all 0. This won't modify the given NamedColor, nor will it allocate any
     * objects.
     * <br>
     * The parameters this takes all specify additive changes for a color component, clamping the final values so they
     * can't go above 1 or below 0, with an exception for hue, which can rotate around if lower or higher hues would be
     * used. As an example, if you give this 0.4f for saturation, and the current color has saturation 0.7f, then the
     * resulting color will have 1f for saturation. If you gave this -0.1f for saturation and the current color still
     * has saturation 0.7f, then resulting color will have 0.6f for saturation.
     *
     * @param hue        -1f to 1f, the hue change that can be applied to the new float color
     * @param saturation -1f to 1f, the saturation change that can be applied to the new float color
     * @param value      -1f to 1f, the value/brightness change that can be applied to the new float color
     * @param opacity    -1f to 1f, the opacity/alpha change that can be applied to the new float color
     * @return a float encoding a variation of basis with the given changes
     */
    public static float toEditedFloat(Color basis, float hue, float saturation, float value, float opacity) {
        final float h, s, r = basis.r, g = basis.g, b = basis.b;
        final float min = Math.min(Math.min(r, g), b);   //Min. value of RGB
        final float max = Math.max(Math.max(r, g), b);   //Max value of RGB, equivalent to value
        final float delta = max - min;                   //Delta RGB value
        if ( delta < 0.0039f )                           //This is a gray, no chroma...
        {
            s = 0f;
            h = 0f;
            hue = 1f;
        }
        else                                             //Chromatic data...
        {
            s = delta / max;
            final float rDelta = (((max - r) / 6f) + (delta / 2f)) / delta;
            final float gDelta = (((max - g) / 6f) + (delta / 2f)) / delta;
            final float bDelta = (((max - b) / 6f) + (delta / 2f)) / delta;

            if      (r == max) h = (bDelta - gDelta + 1f) % 1f;
            else if (g == max) h = ((1f / 3f) + rDelta - bDelta + 1f) % 1f;
            else               h = ((2f / 3f) + gDelta - rDelta + 1f) % 1f;
        }
        saturation = MathUtils.clamp(s + saturation, 0f, 1f);
        value = MathUtils.clamp(max + value, 0f, 1f);
        opacity = MathUtils.clamp(basis.a + opacity, 0f, 1f);

        if (saturation <= 0.0039f) {
            return floatColor(value, value, value, opacity);
        } else if (value <= 0.0039f) {
            return NumberUtils.intBitsToFloat((int) (opacity * 254f) << 24 & 0xFE000000);
        } else {
            final float hu = ((h + hue + 6f) % 1f) * 6f;
            final int i = (int) hu;
            final float x = value * (1 - saturation);
            final float y = value * (1 - saturation * (hu - i));
            final float z = value * (1 - saturation * (1 - (hu - i)));

            switch (i) {
                case 0:
                    return floatColor(value, z, x, opacity);
                case 1:
                    return floatColor(y, value, x, opacity);
                case 2:
                    return floatColor(x, value, z, opacity);
                case 3:
                    return floatColor(x, y, value, opacity);
                case 4:
                    return floatColor(z, x, value, opacity);
                default:
                    return floatColor(value, x, y, opacity);
            }
        }
    }

    /**
     * Gets a variation on the packed float color basis as another packed float that has its hue, saturation, value, and
     * opacity adjusted by the specified amounts. Takes floats representing the amounts of change to apply to hue,
     * saturation, value, and opacity; these can be between -1f and 1f. Returns a float that can be used as a packed or
     * encoded color with methods like {@link com.badlogic.gdx.graphics.g2d.Batch#setPackedColor(float)}, or in various
     * SquidLib classes like SparseLayers or SquidLayers. The float is likely to be different than the result of
     * {@link FloatColorTools#floatColor(float, float, float, float)} unless hue saturation, value, and opacity are all 0. This won't allocate any objects.
     * <br>
     * The parameters this takes all specify additive changes for a color component, clamping the final values so they
     * can't go above 1 or below 0, with an exception for hue, which can rotate around if lower or higher hues would be
     * used. As an example, if you give this 0.4f for saturation, and the current color has saturation 0.7f, then the
     * resulting color will have 1f for saturation. If you gave this -0.1f for saturation and the current color again
     * has saturation 0.7f, then resulting color will have 0.6f for saturation.
     *
     * @param basis      a packed float color that will be used as the starting point to make the next color
     * @param hue        -1f to 1f, the hue change that can be applied to the new float color (not clamped, wraps)
     * @param saturation -1f to 1f, the saturation change that can be applied to the new float color
     * @param value      -1f to 1f, the value/brightness change that can be applied to the new float color
     * @param opacity    -1f to 1f, the opacity/alpha change that can be applied to the new float color
     * @return a float encoding a variation of basis with the given changes
     */
    public static float toEditedFloat(float basis, float hue, float saturation, float value, float opacity) {
        final int bits = NumberUtils.floatToIntBits(basis);
        final float h, s,
                r = (bits & 0x000000ff) * 0x1.010102p-8f,
                g = (bits & 0x0000ff00) * 0x1.010102p-16f,
                b = (bits & 0x00ff0000) * 0x1.010102p-24f;
        final float min = Math.min(Math.min(r, g), b);   //Min. value of RGB
        final float max = Math.max(Math.max(r, g), b);   //Max value of RGB, equivalent to value
        final float delta = max - min;                   //Delta RGB value
        if ( delta < 0.0039f )                           //This is a gray, no chroma...
        {
            s = 0f;
            h = 0f;
            hue = 1f;
        }
        else                                             //Chromatic data...
        {
            s = delta / max;
            final float rDelta = (((max - r) / 6f) + (delta / 2f)) / delta;
            final float gDelta = (((max - g) / 6f) + (delta / 2f)) / delta;
            final float bDelta = (((max - b) / 6f) + (delta / 2f)) / delta;

            if      (r == max) h = (bDelta - gDelta + 1f) % 1f;
            else if (g == max) h = ((1f / 3f) + rDelta - bDelta + 1f) % 1f;
            else               h = ((2f / 3f) + gDelta - rDelta + 1f) % 1f;
        }
        saturation = MathUtils.clamp(s + saturation, 0f, 1f);
        value = MathUtils.clamp(max + value, 0f, 1f);
        opacity = MathUtils.clamp(((bits & 0xfe000000) >>> 24) * 0x1.020408p-8f + opacity, 0f, 1f);

        if (saturation <= 0.0039f) {
            return floatColor(value, value, value, opacity);
        } else if (value <= 0.0039f) {
            return NumberUtils.intBitsToFloat((int) (opacity * 254f) << 24 & 0xFE000000);
        } else {
            final float hu = ((h + hue + 6f) % 1f) * 6f;
            final int i = (int) hu;
            final float x = value * (1 - saturation);
            final float y = value * (1 - saturation * (hu - i));
            final float z = value * (1 - saturation * (1 - (hu - i)));

            switch (i) {
                case 0:
                    return floatColor(value, z, x, opacity);
                case 1:
                    return floatColor(y, value, x, opacity);
                case 2:
                    return floatColor(x, value, z, opacity);
                case 3:
                    return floatColor(x, y, value, opacity);
                case 4:
                    return floatColor(z, x, value, opacity);
                default:
                    return floatColor(value, x, y, opacity);
            }
        }
    }

    /**
     * Interpolates from the packed float color start towards end by change. Both start and end should be packed colors,
     * as from {@link FloatColorTools#floatColor(float, float, float, float)} or {@link #floatColor(float, float, float, float)}, and change can be between 0f
     * (keep start) and 1f (only use end). This is a good way to reduce allocations of temporary Colors.
     * @param start the starting color as a packed float
     * @param end the target color as a packed float
     * @param change how much to go from start toward end, as a float between 0 and 1; higher means closer to end
     * @return a packed float that represents a color between start and end
     */
    public static float lerpFloatColors(final float start, final float end, float change) {
        final int s = NumberUtils.floatToIntBits(start), e = NumberUtils.floatToIntBits(end),
                ys = (s & 0xFF), cws = (s >>> 8) & 0xFF, cms = (s >>> 16) & 0xFF, as = s >>> 24 & 0xFE,
                ye = (e & 0xFF), cwe = (e >>> 8) & 0xFF, cme = (e >>> 16) & 0xFF, ae = e >>> 24 & 0xFE;
        return NumberUtils.intBitsToFloat(((int) (ys + change * (ye - ys)) & 0xFF)
                | (((int) (cws + change * (cwe - cws)) & 0xFF) << 8)
                | (((int) (cms + change * (cme - cms)) & 0xFF) << 16)
                | (((int) (as + change * (ae - as)) & 0xFE) << 24));
    }

    /**
     * Interpolates from the packed float color start towards end by change, but keeps the alpha of start and uses the
     * alpha of end as an extra factor that can affect how much to change. Both start and end should be packed colors,
     * as from {@link FloatColorTools#floatColor(float, float, float, float)} or {@link #floatColor(float, float, float, float)}, and change can be between 0f
     * (keep start) and 1f (only use end). This is a good way to reduce allocations of temporary Colors.
     * @param start the starting color as a packed float; alpha will be preserved
     * @param end the target color as a packed float; alpha will not be used directly, and will instead be multiplied with change
     * @param change how much to go from start toward end, as a float between 0 and 1; higher means closer to end
     * @return a packed float that represents a color between start and end
     */
    public static float lerpFloatColorsBlended(final float start, final float end, float change) {
        final int s = NumberUtils.floatToIntBits(start), e = NumberUtils.floatToIntBits(end),
                rs = (s & 0xFF), gs = (s >>> 8) & 0xFF, bs = (s >>> 16) & 0xFF, as = s & 0xFE000000,
                re = (e & 0xFF), ge = (e >>> 8) & 0xFF, be = (e >>> 16) & 0xFF, ae = (e >>> 25);
        change *= ae * 0.007874016f;
        return NumberUtils.intBitsToFloat(((int) (rs + change * (re - rs)) & 0xFF)
                | (((int) (gs + change * (ge - gs)) & 0xFF) << 8)
                | (((int) (bs + change * (be - bs)) & 0xFF) << 16)
                | as);
    }

    /**
     * Interpolates from the packed float color start towards white by change. While change should be between 0f (return
     * start as-is) and 1f (return white), start should be a packed color, as from {@link FloatColorTools#floatColor(float, float, float, float)} or
     * {@link #floatColor(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
     * is a little more efficient and clear than using {@link #lerpFloatColors(float, float, float)} to lerp towards
     * white. Unlike {@link #lerpFloatColors(float, float, float)}, this keeps the alpha of start as-is.
     * @param start the starting color as a packed float
     * @param change how much to go from start toward white, as a float between 0 and 1; higher means closer to end
     * @return a packed float that represents a color between start and white
     */
    public static float lightenFloat(final float start, final float change) {
        final int s = NumberUtils.floatToIntBits(start),
                rs = (s & 0xFF), gs = (s >>> 8) & 0xFF, bs = (s >>> 16) & 0xFF, as = s & 0xFE000000;
        return NumberUtils.intBitsToFloat(((int) (rs + change * (0xFF - rs)) & 0xFF)
                | (((int) (gs + change * (0xFF - gs)) & 0xFF) << 8)
                | (((int) (bs + change * (0xFF - bs)) & 0xFF) << 16)
                | as);
    }

    /**
     * Interpolates from the packed float color start towards black by change. While change should be between 0f (return
     * start as-is) and 1f (return black), start should be a packed color, as from {@link FloatColorTools#floatColor(float, float, float, float)} or
     * {@link #floatColor(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
     * is a little more efficient and clear than using {@link #lerpFloatColors(float, float, float)} to lerp towards
     * black. Unlike {@link #lerpFloatColors(float, float, float)}, this keeps the alpha of start as-is.
     * @param start the starting color as a packed float
     * @param change how much to go from start toward black, as a float between 0 and 1; higher means closer to end
     * @return a packed float that represents a color between start and black
     */
    public static float darkenFloat(final float start, float change) {
        final int s = NumberUtils.floatToIntBits(start),
                rs = (s & 0xFF), gs = (s >>> 8) & 0xFF, bs = (s >>> 16) & 0xFF, as = s & 0xFE000000;
        change = 1f - change;
        return NumberUtils.intBitsToFloat(((int) (rs * change) & 0xFF)
                | (((int) (gs * change) & 0xFF) << 8)
                | (((int) (bs * change) & 0xFF) << 16)
                | as);
    }
}
