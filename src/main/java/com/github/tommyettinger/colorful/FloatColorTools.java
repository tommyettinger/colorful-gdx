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
     * @param encoded a color as a packed float that can be obtained by {@link Basics#getYCwCmA(float, float, float, float)}
     * @return an int from 0 to 255, inclusive, representing the red channel value of the given encoded color
     */
    public static int redOfFloat(final float encoded)
    {
        final int decoded = NumberUtils.floatToIntBits(encoded);
        return (decoded & 0xff) + (((decoded >>> 8 & 0xff) - 128) * 5 >>> 3) - (((decoded >>> 16 & 0xff) - 128) >>> 1);
    }

    /**
     * Gets the green channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
     * @param encoded a color as a packed float that can be obtained by {@link Basics#getYCwCmA(float, float, float, float)}
     * @return an int from 0 to 255, inclusive, representing the green channel value of the given encoded color
     */
    public static int greenOfFloat(final float encoded)
    {
        final int decoded = NumberUtils.floatToIntBits(encoded);
        return (decoded & 0xff) - (((decoded >>> 8 & 0xff) - 128) * 3 >> 3) + (((decoded >>> 16 & 0xff) - 128) >> 1);
    }

    /**
     * Gets the blue channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
     * @param encoded a color as a packed float that can be obtained by {@link Basics#getYCwCmA(float, float, float, float)}
     * @return an int from 0 to 255, inclusive, representing the blue channel value of the given encoded color
     */
    public static int blueOfFloat(final float encoded)
    {
        final int decoded = NumberUtils.floatToIntBits(encoded);
        return (decoded & 0xff) + (((decoded >>> 8 & 0xff) - 128) * 3 >> 3) - (((decoded >>> 16 & 0xff) - 128) >> 1);
    }

    /**
     * Gets the alpha channel value of the given encoded color, as an even int ranging from 0 to 254, inclusive. Because
     * of how alpha is stored in libGDX, no odd-number values are possible for alpha.
     * @param encoded a color as a packed float that can be obtained by {@link Basics#getYCwCmA(float, float, float, float)}
     * @return an even int from 0 to 254, inclusive, representing the alpha channel value of the given encoded color
     */
    public static int alphaOfFloat(final float encoded)
    {
        return (NumberUtils.floatToIntBits(encoded) & 0xfe000000) >>> 24;
    }
    
    
    ///TODO: we're done converting only up to here

    /**
     * Gets the red channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
     * @param encoded a color as a packed float that can be obtained by {@link Basics#getYCwCmA(float, float, float, float)}
     * @return a float from 0.0f to 1.0f, inclusive, representing the red channel value of the given encoded color
     */
    public static float redOfFloatF(final float encoded)
    {
        return (NumberUtils.floatToIntBits(encoded) & 0x000000ff) * 0.003921569f;
    }

    /**
     * Gets the green channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
     * @param encoded a color as a packed float that can be obtained by {@link Basics#getYCwCmA(float, float, float, float)}
     * @return a float from 0.0f to 1.0f, inclusive, representing the green channel value of the given encoded color
     */
    public static float greenOfFloatF(final float encoded)
    {
        return ((NumberUtils.floatToIntBits(encoded) & 0x0000ff00) >>> 8) * 0.003921569f;
    }

    /**
     * Gets the blue channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
     * @param encoded a color as a packed float that can be obtained by {@link Basics#getYCwCmA(float, float, float, float)}
     * @return a float from 0.0f to 1.0f, inclusive, representing the blue channel value of the given encoded color
     */
    public static float blueOfFloatF(final float encoded)
    {
        return ((NumberUtils.floatToIntBits(encoded) & 0x00ff0000) >>> 16) * 0.003921569f;
    }

    /**
     * Gets the alpha channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
     * @param encoded a color as a packed float that can be obtained by {@link Basics#getYCwCmA(float, float, float, float)}
     * @return a float from 0.0f to 1.0f, inclusive, representing the alpha channel value of the given encoded color
     */
    public static float alphaOfFloatF(final float encoded)
    {
        return ((NumberUtils.floatToIntBits(encoded) & 0xfe000000) >>> 24) * 0.003937008f;
    }

    /**
     * Gets the saturation of the given encoded color, as a float ranging from 0.0f to 1.0f, inclusive.
     * @param encoded a color as a packed float that can be obtained by {@link Basics#getYCwCmA(float, float, float, float)}
     * @return the saturation of the color from 0.0 (a grayscale color; inclusive) to 1.0 (a
     * bright color, exclusive)
     */
    public static float saturationOfFloat(final float encoded) {
        final int e = NumberUtils.floatToIntBits(encoded),
                r = (e & 255), g = (e >>> 8 & 255),
                b = (e >>> 16 & 255);//, a = (e >>> 24 & 254) / 254f;
        final float min = Math.min(Math.min(r, g), b) * 0.003921569f;   //Min. value of RGB
        final float max = Math.max(Math.max(r, g), b) * 0.003921569f;    //Max. value of RGB
        final float delta = max - min;                     //Delta RGB value

        if ( delta < 0.0001f )                     //This is a gray, no chroma...
        {
            return 0f;
        }
        else                                    //Chromatic data...
        {
            return delta / max;
        }
    }

    /**
     * Gets the value (like lightness or brightness) of the given encoded color as a float ranging from 0f to 1f, inclusive.
     * @param encoded a color as a packed float that can be obtained by {@link Basics#getYCwCmA(float, float, float, float)}
     * @return the value (essentially lightness) of the color from 0.0f (black, inclusive) to
     * 1.0f (very bright, inclusive).
     */
    public static float valueOfFloat(final float encoded)
    {
        final int e = NumberUtils.floatToIntBits(encoded);
        final int r = (e & 255), g = (e >>> 8 & 255),
                b = (e >>> 16 & 255);//, a = (e >>> 24 & 254) / 254f;
        return Math.max(Math.max(r, g), b) * 0.003921569f;
    }

    /**
     * Gets the hue of the given encoded color, as a float from 0f (inclusive, red and approaching orange if increased)
     * to 1f (exclusive, red and approaching purple if decreased).
     * @param encoded a color as a packed float that can be obtained by {@link Basics#getYCwCmA(float, float, float, float)}
     * @return The hue of the color from 0.0 (red, inclusive) towards orange, then yellow, and
     * eventually to purple before looping back to almost the same red (1.0, exclusive)
     */
    public static float hueOfFloat(final float encoded) {
        final int e = NumberUtils.floatToIntBits(encoded);
        final float r = (e & 255) * 0.003921569f, g = (e >>> 8 & 255) * 0.003921569f,
                b = (e >>> 16 & 255) * 0.003921569f;//, a = (e >>> 24 & 254) / 254f;
        final float min = Math.min(Math.min(r, g), b);   //Min. value of RGB
        final float max = Math.max(Math.max(r, g), b);   //Max value of RGB
        final float delta = max - min;                           //Delta RGB value

        if ( delta < 0.0001f )                     //This is a gray, no chroma...
        {
            return 0f;
        }
        else                                    //Chromatic data...
        {
            final float rDelta = (((max - r) / 6f) + (delta * 0.5f)) / delta;
            final float gDelta = (((max - g) / 6f) + (delta * 0.5f)) / delta;
            final float bDelta = (((max - b) / 6f) + (delta * 0.5f)) / delta;

            if      (r == max) return (1f + bDelta - gDelta) % 1f;
            else if (g == max) return (1f + (1f / 3f) + rDelta - bDelta) % 1f;
            else               return (1f + (2f / 3f) + gDelta - rDelta) % 1f;
        }
    }

    /**
     * The "luma" of the given packed float, which is like its lightness, in YCbCr format; ranges from 0.0f to 1.0f .
     * You can go back to an RGB color as a packed float with {@link #floatGetYCbCr(float, float, float, float)}.
     * YCbCr is useful for modifications to colors because you can get a grayscale version of a color by setting Cb and
     * Cr to 0, you can desaturate by multiplying Cb and Cr by a number between 0 and 1, you can oversaturate by
     * multiplying Cb and Cr by a number greater than 1, you can lighten or darken by increasing or decreasing luma, and
     * so on and so forth.
     * @param encoded a packed float
     * @return the luma as a float from 0.0f to 1.0f
     */
    public static float lumaOfFloat(final float encoded)
    {
        final int bits = NumberUtils.floatToIntBits(encoded);
        return  (bits & 0x000000ff) * (0x1.010102p-8f  * 0.299f) +
                (bits & 0x0000ff00) * (0x1.010102p-16f * 0.587f) +
                (bits & 0x00ff0000) * (0x1.010102p-24f * 0.114f);
    }

    /**
     * The "Chroma B," or Cb, of the given packed float in YCbCr format; ranges from -0.5f to 0.5f .
     * This is related to Chroma R; when Chroma B is high and Chroma R is low, the color is more blue; when Chroma R is
     * high and Chroma B is low, the color is more red, when both are low it is more green, and when both are high it is
     * more purple. When Chroma R and Chroma B are both near 0.0f, the color is closer to gray. Because Chroma values
     * are centered on 0.0f, you can multiply them by a value like 0.5f to halve the colorfulness of the color.
     * You can go back to an RGB color as a packed float with {@link #floatGetYCbCr(float, float, float, float)}.
     * YCbCr is useful for modifications to colors because you can get a grayscale version of a color by setting Cb and
     * Cr to 0, you can desaturate by multiplying Cb and Cr by a number between 0 and 1, you can oversaturate by
     * multiplying Cb and Cr by a number greater than 1, you can lighten or darken by increasing or decreasing luma, and
     * so on and so forth.
     * @param encoded a packed float
     * @return the Chroma B as a float from -0.5f to 0.5f
     */
    public static float chromaBOfFloat(final float encoded)
    {
        final int bits = NumberUtils.floatToIntBits(encoded);
        return  (bits & 0x000000ff) * (0x1.010102p-8f  * -0.168736f) +
                (bits & 0x0000ff00) * (0x1.010102p-16f * -0.331264f) +
                (bits & 0x00ff0000) * (0x1.010102p-24f * 0.5f);
    }

    /**
     * The "Chroma R," or Cr, of the given packed float in YCbCr format; ranges from -0.5f to 0.5f .
     * This is related to Chroma B; when Chroma B is high and Chroma R is low, the color is more blue; when Chroma R is
     * high and Chroma B is low, the color is more red, when both are low it is more green, and when both are high it is
     * more purple. When Chroma R and Chroma B are both near 0.0f, the color is closer to gray. Because Chroma values
     * are centered on 0.0f, you can multiply them by a value like 0.5f to halve the colorfulness of the color.
     * You can go back to an RGB color as a packed float with {@link #floatGetYCbCr(float, float, float, float)}.
     * YCbCr is useful for modifications to colors because you can get a grayscale version of a color by setting Cb and
     * Cr to 0, you can desaturate by multiplying Cb and Cr by a number between 0 and 1, you can oversaturate by
     * multiplying Cb and Cr by a number greater than 1, you can lighten or darken by increasing or decreasing luma, and
     * so on and so forth.
     * @param encoded a packed float
     * @return the Chroma R as a float from -0.5f to 0.5f
     */
    public static float chromaROfFloat(final float encoded)
    {
        final int bits = NumberUtils.floatToIntBits(encoded);
        return  (bits & 0x000000ff) * (0x1.010102p-8f  * 0.5f) +
                (bits & 0x0000ff00) * (0x1.010102p-16f * -0.418688f) +
                (bits & 0x00ff0000) * (0x1.010102p-24f * -0.081312f);
    }

    /**
     * The "luminance" of the given packed float, which is like its lightness, in YCoCg format; ranges from 0.0f to
     * 1.0f . You can go back to an RGB color as a packed float with {@link #floatGetYCoCg(float, float, float, float)}.
     * YCoCg is useful for modifications to colors because you can get a grayscale version of a color by setting Co and
     * Cg to 0, you can desaturate by multiplying Co and Cg by a number between 0 and 1, you can oversaturate by
     * multiplying Co and Cg by a number greater than 1, you can lighten or darken by increasing or decreasing
     * luminance, and so on and so forth. YCoCg is also a little more efficient to process than YCbCr, which other
     * methods like {@link #floatGetYCbCr(float, float, float, float)} use. This might not be as perceptually accurate
     * at determining lightness as {@link #lumaOfFloat(float)}.
     * @param encoded a packed float
     * @return the luminance as a float from 0.0f to 1.0f
     */
    public static float luminanceYCoCg(final float encoded)
    {
        final int bits = NumberUtils.floatToIntBits(encoded);
        return ((bits & 0x000000ff) + ((bits & 0x0000ff00) >>> 7) + ((bits & 0x00ff0000) >>> 16)) * 0x1.010102p-10f;
    }

    /**
     * The "luma" of the given packed float, which is like its lightness, in YCwCm format; ranges from 0.0f to
     * 1.0f . You can go back to an RGB color as a packed float with {@link #floatGetYCwCm(float, float, float, float)}.
     * YCwCm is useful for modifications to colors because you can get a grayscale version of a color by setting Cw and
     * Cm to 0, you can desaturate by multiplying Cw and Cm by a number between 0 and 1, you can oversaturate by
     * multiplying Cw and Cm by a number greater than 1, you can lighten or darken by increasing or decreasing luma, and
     * so on and so forth. Unlike YCoCg and YCbCr, there are aesthetic reasons to adjust just one of Cw or Cm for some
     * effect; multiplying Cw by a number greater than 1 will make warm colors warmer and cool colors cooler, for
     * instance. This might not be as perceptually accurate at determining lightness as {@link #lumaOfFloat(float)}.
     * @param encoded a packed float
     * @return the luma as a float from 0.0f to 1.0f
     */
    public static float lumaYCwCm(final float encoded)
    {
        final int bits = NumberUtils.floatToIntBits(encoded);
        return (bits & 0xFF) * 0x3p-11f + (bits >>> 8 & 0xFF) * 0x1p-9f + (bits >>> 16 & 0xFF) * 0x1p-11f;
    }

    /**
     * The "chrominance orange" of the given packed float, which when combined with chrominance green describes the
     * shade and saturation of a color, in YCoCg format; ranges from -0.5f to 0.5f . You can go back to an RGB color as
     * a packed float with {@link #floatGetYCoCg(float, float, float, float)}. YCoCg is useful for modifications to
     * colors because you can get a grayscale version of a color by setting Co and Cg to 0, you can desaturate by
     * multiplying Co and Cg by a number between 0 and 1, you can oversaturate by multiplying Co and Cg by a number
     * greater than 1, you can lighten or darken by increasing or decreasing luminance, and so on and so forth. YCoCg is
     * also a little more efficient to process than YCbCr, which other methods like
     * {@link #floatGetYCbCr(float, float, float, float)} use.
     * @param encoded a packed float
     * @return the chrominance orange as a float from -0.5f to 0.5f
     */
    public static float chrominanceOrange(final float encoded)
    {
        final int bits = NumberUtils.floatToIntBits(encoded);
        return ((bits & 0x000000ff) - ((bits & 0x00ff0000) >>> 16)) * 0x1.010102p-9f;
    }

    /**
     * The "chrominance green" of the given packed float, which when combined with chrominance orange describes the
     * shade and saturation of a color, in YCoCg format; ranges from -0.5f to 0.5f . You can go back to an RGB color as
     * a packed float with {@link #floatGetYCoCg(float, float, float, float)}. YCoCg is useful for modifications to
     * colors because you can get a grayscale version of a color by setting Co and Cg to 0, you can desaturate by
     * multiplying Co and Cg by a number between 0 and 1, you can oversaturate by multiplying Co and Cg by a number
     * greater than 1, you can lighten or darken by increasing or decreasing luminance, and so on and so forth. YCoCg is
     * also a little more efficient to process than YCbCr, which other methods like
     * {@link #floatGetYCbCr(float, float, float, float)} use.
     * @param encoded a packed float
     * @return the chrominance green as a float from -0.5f to 0.5f
     */
    public static float chrominanceGreen(final float encoded)
    {
        final int bits = NumberUtils.floatToIntBits(encoded);
        return (((bits & 0x0000ff00) >>> 7) - (bits & 0x000000ff) - ((bits & 0x00ff0000) >>> 16)) * 0x1.010102p-10f;
    }

    /**
     * The "chroma warm" of the given packed float, which when combined with chroma mild describes the shade and
     * saturation of a color, in YCwCm format; ranges from -1f to 1f . You can go back to an RGB color as
     * a packed float with {@link #floatGetYCwCm(float, float, float, float)}. YCwCm is useful for modifications to
     * colors because you can get a grayscale version of a color by setting Cw and Cm to 0, you can desaturate by
     * multiplying Cw and Cm by a number between 0 and 1, you can oversaturate by multiplying Cw and Cm by a number
     * greater than 1, you can lighten or darken by increasing or decreasing luma, and so on and so forth. Unlike
     * YCoCg and YCbCr, there are aesthetic reasons to adjust just one of Cw or Cm for some effect; multiplying Cw by
     * a number greater than 1 will make warm colors warmer and cool colors cooler, for instance.
     * @param encoded a packed float
     * @return the chroma warm as a float from -1f to 1f
     */
    public static float chromaWarm(final float encoded)
    {
        final int bits = NumberUtils.floatToIntBits(encoded);
        return ((bits & 0xff) - (bits >>> 16 & 0xff)) * 0x1.010102p-8f;
    }

    /**
     * The "chroma mild" of the given packed float, which when combined with chroma warm describes the shade and
     * saturation of a color, in YCwCm format; ranges from -1f to 1f . You can go back to an RGB color as
     * a packed float with {@link #floatGetYCwCm(float, float, float, float)}. YCwCm is useful for modifications to
     * colors because you can get a grayscale version of a color by setting Cw and Cm to 0, you can desaturate by
     * multiplying Cw and Cm by a number between 0 and 1, you can oversaturate by multiplying Cw and Cm by a number
     * greater than 1, you can lighten or darken by increasing or decreasing luma, and so on and so forth. Unlike
     * YCoCg and YCbCr, there are aesthetic reasons to adjust just one of Cw or Cm for some effect; multiplying Cw by
     * a number greater than 1 will make warm colors warmer and cool colors cooler, for instance.
     * @param encoded a packed float
     * @return the chroma mild as a float from -1f to 1f
     */
    public static float chromaMild(final float encoded)
    {
        final int bits = NumberUtils.floatToIntBits(encoded);
        return ((bits >>> 8 & 0xff) - (bits >>> 16 & 0xff)) * 0x1.010102p-8f;
    }

    /**
     * Given a color stored as a packed float and an alpha multiplier to affect that color (between 0f and 1f, inclusive
     * and clamped in that range), this makes a packed float color that has the same red, green, and blue channels but
     * has its own alpha multiplied by {@code alpha}, without constructing any objects along the way.
     *
     * @param encodedColor a color encoded as a packed float, as by {@link Basics#getYCwCmA(float, float, float, float)}
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
     * set to the given alpha, without constructing any objects along the way.
     *
     * @param encodedColor a color encoded as a packed float, as by {@link Basics#getYCwCmA(float, float, float, float)}
     * @param alpha        between 0.0 and 1.0 inclusive, the alpha to set into the returned packed color
     * @return another color encoded as a packed float, using encodedColor's RGB channels and the given alpha
     */
    public static float translucentColor(float encodedColor, float alpha) {
        return NumberUtils.intBitsToFloat(NumberUtils.floatToIntBits(encodedColor) & 0xFFFFFF
                | (MathUtils.clamp((int) (255f * alpha), 0, 255) << 24 & 0xFE000000));
    }

    /**
     * Gets a packed float representation of a color given as 4 RGBA float components. LibGDX expects ABGR format
     * in some places, but not all, and it can be confusing to track when it wants RGBA, ABGR, or ARGB. Generally,
     * packed floats like what this returns are ABGR format, the kind that can be passed directly to
     * {@link com.badlogic.gdx.graphics.g2d.Batch#setPackedColor(float)} without constructing intermediate objects.
     * SquidPanel also uses floats internally instead of LibGDX Color objects in its internal 2D array that
     * associates colors to cells; this has changed from earlier releases and should be much more efficient.
     *
     * @param r a float from 0.0 to 1.0 for red
     * @param g a float from 0.0 to 1.0 for green
     * @param b a float from 0.0 to 1.0 for blue
     * @param a a float from 0.0 to 1.0 for alpha/opacity
     * @return a packed float that can be given to the setColor method in LibGDX's Batch classes
     */
    public static float floatGet(float r, float g, float b, float a) {
        return NumberUtils.intBitsToFloat(((int) (a * 255) << 24 & 0xFE000000) | ((int) (b * 255) << 16)
                | ((int) (g * 255) << 8) | (int) (r * 255));
    }

    /**
     * Gets a packed float representation of a color given an RGBA8888-format long. LibGDX expects ABGR format
     * in some places, but not all, and it can be confusing to track when it wants RGBA, ABGR, or ARGB. Generally,
     * packed floats like what this returns are ABGR format, the kind that can be passed directly to
     * {@link com.badlogic.gdx.graphics.g2d.Batch#setPackedColor(float)} without constructing intermediate objects.
     * SquidPanel also uses floats internally instead of LibGDX Color objects in its internal 2D array that
     * associates colors to cells; this has changed from earlier releases and should be much more efficient.
     * <br>
     * This method is probably not what you want unless you specifically have RGBA8888-format longs that you
     * want converted to packed floats. You probably should look at {@link #floatGet(float, float, float, float)} if
     * you have alpha and/or float components, or {@link #floatGetI(int, int, int)} for the common case of the 3 RGB
     * components as ints and alpha simply opaque.
     *
     * @param c a long with format {@code 32 unused bits, 8 red bits, 8 green bits, 8 blue bits, 7 alpha bits, 1 unused bit}
     * @return a packed float that can be given to the setColor method in LibGDX's Batch classes
     */
    public static float floatGet(long c) {
        return NumberUtils.intBitsToFloat((int) ((c >>> 24 & 0xff) | (c >>> 8 & 0xff00) | (c << 8 & 0xff0000)
                | (c << 24 & 0xfe000000)));
    }

    /**
     * Gets a packed float representation of a color given an RGBA8888-format int. LibGDX expects ABGR format
     * in some places, but not all, and it can be confusing to track when it wants RGBA, ABGR, or ARGB. Generally,
     * packed floats like what this returns are ABGR format, the kind that can be passed directly to
     * {@link com.badlogic.gdx.graphics.g2d.Batch#setPackedColor(float)} without constructing intermediate objects.
     * SquidPanel also uses floats internally instead of LibGDX Color objects in its internal 2D array that
     * associates colors to cells; this has changed from earlier releases and should be much more efficient.
     * <br>
     * This method is probably not what you want unless you specifically have RGBA8888-format ints that you
     * want converted to packed floats. You probably should look at {@link #floatGet(float, float, float, float)} if
     * you have alpha and/or float components, or {@link #floatGetI(int, int, int)} for the common case of the 3 RGB
     * components as ints and alpha simply opaque.
     *
     * @param c an int with format {@code 8 red bits, 8 green bits, 8 blue bits, 7 alpha bits, 1 unused bit}
     * @return a packed float that can be given to the setColor method in LibGDX's Batch classes
     */
    public static float floatGet(int c) {
        return NumberUtils.intBitsToFloat(Integer.reverseBytes(c) & 0xFEFFFFFF);
    }

    /**
     * Gets a packed float representation of a color given as 3 RGB int components, setting alpha to opaque. LibGDX
     * expects ABGR format in some places, but not all, and it can be confusing to track when it wants RGBA, ABGR,
     * or ARGB. Generally, packed floats like what this returns are ABGR format, the kind that can be passed
     * directly to {@link com.badlogic.gdx.graphics.g2d.Batch#setPackedColor(float)} without constructing intermediate
     * objects. SquidPanel also uses floats internally instead of LibGDX Color objects in its internal 2D array that
     * associates colors to cells; this has changed from earlier releases and should be much more efficient.
     *
     * @param r an int from 0 to 255 (both inclusive) for red
     * @param g an int from 0 to 255 (both inclusive) for green
     * @param b an int from 0 to 255 (both inclusive) for blue
     * @return a packed float that can be given to the setColor method in LibGDX's Batch classes
     */
    public static float floatGetI(int r, int g, int b) {
        return NumberUtils.intBitsToFloat((r & 0xff) | (g << 8 & 0xff00) | (b << 16 & 0xff0000)
                | 0xfe000000); //rgbToFloatColor((b & 0xff) | (g << 8 & 0xff00) | (r << 16));
    }

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
            return floatGet(value, value, value, opacity);
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
                    return floatGet(value, c, a, opacity);
                case 1:
                    return floatGet(b, value, a, opacity);
                case 2:
                    return floatGet(a, value, c, opacity);
                case 3:
                    return floatGet(a, b, value, opacity);
                case 4:
                    return floatGet(c, a, value, opacity);
                default:
                    return floatGet(value, a, b, opacity);
            }
        }
    }

    /**
     * Gets a color as a packed float given floats representing luma (Y, akin to lightness), blue chroma (Cb, one of two
     * kinds of chroma used here), red chroma (Cr, the other kind of chroma), and opacity. Luma should be between 0 and
     * 1, inclusive, with 0 used for very dark colors including but not limited to black, and 1 used for very light
     * colors including but not limited to white. The two chroma values range from -0.5 to 0.5, and only make sense when
     * used together. When Chroma B is high and Chroma R is low, the color is more blue; when Chroma R is high and
     * Chroma B is low, the color is more red, when both are low it is more green, and when both are high it is more
     * purple. When Chroma R and Chroma B are both near 0.0f, the color is closer to gray. Because Chroma values are
     * centered on 0.0f, you can multiply them by a value like 0.5f to halve the colorfulness of the color.
     *
     * @param luma       0f to 1f, lightness (see {@link #lumaOfFloat(float)})
     * @param chromaB    -0.5f to 0.5f, "blueness" of chroma component, with 0.5 more blue (see {@link #chromaBOfFloat(float)})
     * @param chromaR    -0.5f to 0.5f, "redness" of chroma component, with 0.5 more red (see {@link #chromaROfFloat(float)})
     * @param opacity    0f to 1f, 0f is fully transparent and 1f is opaque
     * @return a float encoding a color with the given properties
     */
    public static float floatGetYCbCr(float luma, float chromaB, float chromaR, float opacity) {
        if (chromaR >= -0.0039f && chromaR <= 0.0039f && chromaB >= -0.0039f && chromaB <= 0.0039f) {
            return floatGet(luma, luma, luma, opacity);
        }
        return floatGet(MathUtils.clamp(luma + chromaR * 1.402f, 0f, 1f),
                MathUtils.clamp(luma - chromaB * 0.344136f - chromaR * 0.714136f, 0f, 1f),
                MathUtils.clamp(luma + chromaB * 1.772f, 0f, 1f),
                opacity);
    }

    /**
     * Gets a color as a packed float given floats representing luminance (Y, akin to lightness), orange chrominance
     * (Co, one of two kinds of chroma used here), green chrominance (Cg, the other kind of chroma), and opacity.
     * Luminance should be between 0 and 1, inclusive, with 0 used for very dark colors including but not limited to
     * black, and 1 used for very light colors including but not limited to white. The two chrominance values range from
     * -0.5 to 0.5, and only make sense when used together. When Co is high and Cg is low, the color is more reddish;
     * when both are low it is more bluish, and when Cg is high the color tends to be greenish (it can be more cyan or
     * more yellow depending on Co, but mostly when Y isn't low). When Co and Cg are both near 0.0f, the color is closer
     * to gray. Because chrominance values are centered on 0.0f, you can multiply them by a value like 0.5f to halve the
     * colorfulness of the color.
     * <br>
     * This method clamps the resulting color's RGB values, so any values can technically be given to this as y, co, and
     * cg, but they will only be reversible from the returned float color to the original Y, Cb, and Cr values if the
     * original values were in the range that {@link #chrominanceOrange(float)}, {@link #chrominanceGreen(float)}, and
     * {@link #luminanceYCoCg(float)} return.
     *
     * @param y          0f to 1f, luminance or Y component of YCoCg
     * @param co         -0.5f to 0.5f, "chrominance orange" or Co component of YCoCg, with 0.5f more orange
     * @param cg         -0.5f to 0.5f, "chrominance green" or Cg component of YCoCg, with 0.5f more green
     * @param opacity    0f to 1f, 0f is fully transparent and 1f is opaque
     * @return a float encoding a color with the given properties
     */
    public static float floatGetYCoCg(float y, float co, float cg, float opacity) {
//        if (luma <= 0.0039f) {
//            return floatGet(0f, 0f, 0f, opacity);
//        } else if (luma >= 0.9961f) {
//            return floatGet(1f, 1f, 1f, opacity);
//        }
//        if (co >= -0.0039f && co <= 0.0039f && cg >= -0.0039f && cg <= 0.0039f) {
//            return floatGet(y, y, y, opacity);
//        }
        /*
t = Y - Cg;
R = t + Co;
G = Y + Cg;
B = t - Co;
         */
        final float t = y - cg;
        return floatGet(MathUtils.clamp(t + co, 0f, 1f),
                MathUtils.clamp(y + cg, 0f, 1f),
                MathUtils.clamp(t - co, 0f, 1f),
                opacity);
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
     * {@link #lumaYCwCm(float)} return.
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
        return floatGet(MathUtils.clamp(luma + warm * 0.625f - mild * 0.5f, 0f, 1f),
                MathUtils.clamp(luma + mild * 0.5f - warm * 0.375f, 0f, 1f),
                MathUtils.clamp(luma - warm * 0.375f - mild * 0.5f, 0f, 1f), opacity);
    }

    public static float ycwcmLerp(float start, float end, float alpha) {
        final int s = NumberUtils.floatToIntBits(start), e = NumberUtils.floatToIntBits(end),
                sr = s & 0xFF, er = e & 0xFF,
                sg = s >>> 8 & 0xFF, eg = e >>> 8 & 0xFF,
                sb = s >>> 16 & 0xFF, eb = e >>> 16 & 0xFF,
                sa = s >>> 25, ea = e >>> 25; // 7 bit opacity 
        final float
                luma = MathUtils.lerp(sr * 0x2.FDp-11f + sg * 0x0.FFp-9f + sb * 0x0.FFp-11f, er * 0x2.FDp-11f + eg * 0x0.FFp-9f + eb * 0x0.FFp-11f, alpha),
                warm = MathUtils.lerp(sr - sb, er - eb, alpha) * 0x0.FFp-8f,
                mild = MathUtils.lerp(sg - sb, eg - eb, alpha) * 0x0.FFp-8f,
                opacity = MathUtils.lerp(sa, ea, alpha) * 0x0.FEp-7f;
        return floatGet(luma + warm * 0.625f - mild * 0.5f,
                luma + mild * 0.5f - warm * 0.375f,
                luma - warm * 0.375f - mild * 0.5f, opacity);
    }


    /**
     * Converts a packed float color in the format produced by {@link Basics#getYCwCmA(float, float, float, float)} to an RGBA8888 int. This format of
     * int can be used with Pixmap and in some other places in libGDX.
     * @param packed a packed float color, as produced by {@link Basics#getYCwCmA(float, float, float, float)}
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
     * SquidLayers. The float is likely to be different than the result of {@link Basics#getYCwCmA(float, float, float, float)} unless hue,
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
     * SparseLayers or SquidLayers. The float is likely to be different than the result of {@link Basics#getYCwCmA(float, float, float, float)} unless
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
            return floatGet(value, value, value, opacity);
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
                    return floatGet(value, z, x, opacity);
                case 1:
                    return floatGet(y, value, x, opacity);
                case 2:
                    return floatGet(x, value, z, opacity);
                case 3:
                    return floatGet(x, y, value, opacity);
                case 4:
                    return floatGet(z, x, value, opacity);
                default:
                    return floatGet(value, x, y, opacity);
            }
        }
    }

    /**
     * Gets a variation on the packed float color basis as another packed float that has its hue, saturation, value, and
     * opacity adjusted by the specified amounts. Takes floats representing the amounts of change to apply to hue,
     * saturation, value, and opacity; these can be between -1f and 1f. Returns a float that can be used as a packed or
     * encoded color with methods like {@link com.badlogic.gdx.graphics.g2d.Batch#setPackedColor(float)}, or in various
     * SquidLib classes like SparseLayers or SquidLayers. The float is likely to be different than the result of
     * {@link Basics#getYCwCmA(float, float, float, float)} unless hue saturation, value, and opacity are all 0. This won't allocate any objects.
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
            return floatGet(value, value, value, opacity);
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
                    return floatGet(value, z, x, opacity);
                case 1:
                    return floatGet(y, value, x, opacity);
                case 2:
                    return floatGet(x, value, z, opacity);
                case 3:
                    return floatGet(x, y, value, opacity);
                case 4:
                    return floatGet(z, x, value, opacity);
                default:
                    return floatGet(value, x, y, opacity);
            }
        }
    }

    /**
     * Interpolates from the packed float color start towards end by change. Both start and end should be packed colors,
     * as from {@link Basics#getYCwCmA(float, float, float, float)} or {@link #floatGet(float, float, float, float)}, and change can be between 0f
     * (keep start) and 1f (only use end). This is a good way to reduce allocations of temporary Colors.
     * @param start the starting color as a packed float
     * @param end the target color as a packed float
     * @param change how much to go from start toward end, as a float between 0 and 1; higher means closer to end
     * @return a packed float that represents a color between start and end
     */
    public static float lerpFloatColors(final float start, final float end, final float change) {
        final int s = NumberUtils.floatToIntBits(start), e = NumberUtils.floatToIntBits(end),
                rs = (s & 0xFF), gs = (s >>> 8) & 0xFF, bs = (s >>> 16) & 0xFF, as = (s >>> 24) & 254,
                re = (e & 0xFF), ge = (e >>> 8) & 0xFF, be = (e >>> 16) & 0xFF, ae = (e >>> 24) & 254;
        return NumberUtils.intBitsToFloat(((int) (rs + change * (re - rs)) & 0xFF)
                | (((int) (gs + change * (ge - gs)) & 0xFF) << 8)
                | (((int) (bs + change * (be - bs)) & 0xFF) << 16)
                | (((int) (as + change * (ae - as)) & 0xFE) << 24));
    }

    /**
     * Interpolates from the packed float color start towards end by change, but keeps the alpha of start and uses the
     * alpha of end as an extra factor that can affect how much to change. Both start and end should be packed colors,
     * as from {@link Basics#getYCwCmA(float, float, float, float)} or {@link #floatGet(float, float, float, float)}, and change can be between 0f
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
     * start as-is) and 1f (return white), start should be a packed color, as from {@link Basics#getYCwCmA(float, float, float, float)} or
     * {@link #floatGet(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
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
     * start as-is) and 1f (return black), start should be a packed color, as from {@link Basics#getYCwCmA(float, float, float, float)} or
     * {@link #floatGet(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
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
