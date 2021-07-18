package com.github.tommyettinger.colorful.cielab;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.NumberUtils;
import com.github.tommyettinger.colorful.FloatColors;
import com.github.tommyettinger.colorful.Shaders;
import com.github.tommyettinger.colorful.oklab.ColorfulBatch;

/**
 * Contains code for manipulating colors as {@code int} and packed {@code float} values in the CIE L*A*B* color space.
 * This is the old standard (and for some things, gold standard) of color spaces, introduced in 1976 and never fully
 * superseded by a newer color space. A reason there are still newer color spaces being introduced is in part that
 * existing color spaces, such as CIELAB, don't have sufficient performance for a given task, and this is an especially
 * fair criticism of CIELAB -- it's slow. But, if you calculate new colors infrequently and do the heaviest conversions
 * in a shader, this might not be noticeably different from Oklab or IPT_HQ. This package emphasizes quality over speed,
 * so some calculations that are approximated in Oklab and IPT_HQ are performed rigorously here.
 * <br>
 * The CIE L*A*B* color space has 3 channels, L, A, and B, each gamma-corrected (which is why an asterisk is added).
 * L is lightness, A is a chroma axis that is (roughly) cyan-vs.-red, and B is a chroma axis that is (roughly)
 * blue-vs.-yellow. This is the same in concept as Oklab, but Oklab is skewed somewhat so high B there is more orange
 * than yellow; it's somewhat hard to accurately produce yellow in Oklab. This also has an alpha channel in each color,
 * which acts like it does in every other color space package here (multiplicative alpha, higher is more opaque).
 */
public class ColorTools {
    /**
     * Gets a packed float representation of a color given as 4 float components, here, L (luminance or lightness), A
     * (a chromatic component ranging from cyan to red), B (a chromatic component ranging from blue to yellow), and
     * alpha (or opacity). As long as you use a batch with {@link Shaders#fragmentShaderCielab} as its shader, colors
     * passed with {@link com.badlogic.gdx.graphics.g2d.Batch#setPackedColor(float)} will be interpreted as CIELAB. L
     * should be between 0 and 1, inclusive, with 0 used for very dark colors (almost only black), and 1 used for very
     * light colors (almost only white). A and B range from 0.0 to 1.0, with grayscale results when both are about 0.5.
     * There's some aesthetic value in changing just one chroma value. When A is high and B is low, the color is more
     * purple/magenta, when both are low it is more bluish, when B is high and A is low, the color tends to be greenish,
     * and when both are high it tends to be orange. When A and B are both near 0.5f, the color is closer to gray.
     * Alpha is the multiplicative opacity of the color, and acts like RGBA's alpha.
     *
     * @param l     0f to 1f, lightness or L component of CIELAB, with 0.5f meaning "no change" and 1f brightening
     * @param a     0f to 1f, protan or A component of CIELAB, with 1f more orange, red, or magenta
     * @param b     0f to 1f, tritan or B component of CIELAB, with 1f more green, yellow, or red
     * @param alpha 0f to 1f, 0f makes the color transparent and 1f makes it opaque
     * @return a float encoding a color with the given properties
     */
    public static float cielab(float l, float a, float b, float alpha) {
        return NumberUtils.intBitsToFloat(((int) (alpha * 255) << 24 & 0xFE000000) | ((int) (b * 255) << 16 & 0xFF0000)
                | ((int) (a * 255) << 8 & 0xFF00) | ((int) (l * 255) & 0xFF));
    }


    /**
     * An approximation of the cube-root function for float inputs and outputs.
     * This can be about twice as fast as {@link Math#cbrt(double)}. This
     * version does not tolerate negative inputs, because in the narrow use
     * case it has in this class, it never is given negative inputs.
     * <br>
     * Has very low relative error (less than 1E-9) when inputs are uniformly
     * distributed between 0 and 512, and absolute mean error of less than
     * 1E-6 in the same scenario. Uses a bit-twiddling method similar to one
     * presented in Hacker's Delight and also used in early 3D graphics (see
     * https://en.wikipedia.org/wiki/Fast_inverse_square_root for more, but
     * this code approximates cbrt(x) and not 1/sqrt(x)). This specific code
     * was originally by Marc B. Reynolds, posted in his "Stand-alone-junk"
     * repo: https://github.com/Marc-B-Reynolds/Stand-alone-junk/blob/master/src/Posts/ballcube.c#L182-L197 .
     * It's worth noting that while hardware instructions for finding the
     * square root of a float have gotten extremely fast, the same is not
     * true for the cube root (which has to allow negative inputs), so while
     * the bit-twiddling inverse square root is no longer a beneficial
     * optimization on current hardware, this does seem to help.
     * <br>
     * This is used when converting from RGB to CIELAB, as an intermediate step.
     * @param x any non-negative finite float to find the cube root of
     * @return the cube root of x, approximated
     */
    private static float cbrtPositive(float x) {
        int ix = NumberUtils.floatToRawIntBits(x);
        final float x0 = x;
        ix = (ix>>>2) + (ix>>>4);
        ix += (ix>>>4);
        ix += (ix>>>8) + 0x2A5137A0;
        x  = NumberUtils.intBitsToFloat(ix);
        x  = 0.33333334f*(2f * x + x0/(x*x));
        x  = 0.33333334f*(2f * x + x0/(x*x));
        return x;
    }

    /**
     * Used when given non-linear sRGB inputs to make them linear, using an exact gamma of 2.4 and accounting for the
     * darkest colors with a different formula.
     * @param component any non-linear channel of a color, to be made linear
     * @return a linear version of component
     */
    private static float forwardGamma(final float component) {
        return component < 0.04045f ? component * (1f/12.92f) : (float)Math.pow((component + 0.055f) * (1f/1.055f), 2.4f);
    }

    /**
     * Used to return from a linear, gamma-corrected input to an sRGB, non-linear output, using an exact gamma of 2.4
     * and accounting for the darkest colors with a different formula.
     * @param component a linear channel of a color, to be made non-linear
     * @return a non-linear version of component
     */
    private static float reverseGamma(final float component) {
        return component < 0.0031308f ? component * 12.92f : (float)Math.pow(component, 1f/2.4f) * 1.055f - 0.055f;
    }

    private static float forwardXYZ(final float t) {
        return (t < 0.00885645f) ? 7.787037f * t + 0.139731f : cbrtPositive(t);
    }

    private static float reverseXYZ(final float t) {
        return (t < 0.20689655f) ? 0.1284185f * (t - 0.139731f) : t * t * t;
    }

    /*
     */
    /**
     * Converts a packed float color in the format produced by {@link #cielab(float, float, float, float)} to an RGBA8888 int.
     * This format of int can be used with Pixmap and in some other places in libGDX.
     * @param packed a packed float color, as produced by {@link #cielab(float, float, float, float)}
     * @return an RGBA8888 int color
     */
    public static int toRGBA8888(final float packed)
    {
        final int decoded = NumberUtils.floatToRawIntBits(packed);
        final float L = (1f/1.16f)*((decoded & 0xff) / 255f + 0.16f);
        final float A = ((decoded >>> 8 & 0xff) - 127.5f) * (0.2f / 127.5f);
        final float B = ((decoded >>> 16 & 0xff) - 127.5f) * (0.5f / 127.5f);
        final float x = reverseXYZ(L + A);
        final float y = reverseXYZ(L);
        final float z = reverseXYZ(L - B);
        final int r = (int)(reverseGamma(Math.min(Math.max(+3.2406f * x + -0.9689f * y + -0.4986f * z, 0f), 1f)) * 255.999f);
        final int g = (int)(reverseGamma(Math.min(Math.max(-1.5372f * x + +1.8758f * y + +0.0415f * z, 0f), 1f)) * 255.999f);
        final int b = (int)(reverseGamma(Math.min(Math.max(+3.2406f * x + -0.9689f * y + +1.0570f * z, 0f), 1f)) * 255.999f);
        return r << 24 | g << 16 | b << 8 | (decoded & 0xfe000000) >>> 24 | decoded >>> 31;
    }

    /**
     * Converts a packed float color in the format produced by {@link #cielab(float, float, float, float)}
     * to a packed float in ABGR8888 format.
     * This format of float can be used with the standard SpriteBatch and in some other places in libGDX.
     * @param packed a packed float color, as produced by {@link #cielab(float, float, float, float)}
     * @return a packed float color as ABGR8888
     */
    public static float toRGBA(final float packed)
    {
        final int decoded = NumberUtils.floatToRawIntBits(packed);
        final float L = (1f/1.16f)*((decoded & 0xff) / 255f + 0.16f);
        final float A = ((decoded >>> 8 & 0xff) - 127.5f) * (0.2f / 127.5f);
        final float B = ((decoded >>> 16 & 0xff) - 127.5f) * (0.5f / 127.5f);
        final float x = reverseXYZ(L + A);
        final float y = reverseXYZ(L);
        final float z = reverseXYZ(L - B);
        final int r = (int)(reverseGamma(Math.min(Math.max(+3.2406f * x + -0.9689f * y + -0.4986f * z, 0f), 1f)) * 255.999f);
        final int g = (int)(reverseGamma(Math.min(Math.max(-1.5372f * x + +1.8758f * y + +0.0415f * z, 0f), 1f)) * 255.999f);
        final int b = (int)(reverseGamma(Math.min(Math.max(+3.2406f * x + -0.9689f * y + +1.0570f * z, 0f), 1f)) * 255.999f);
        return NumberUtils.intBitsToFloat(r | g << 8 | b << 16 | (decoded & 0xfe000000));
    }

    /**
     * Writes a CIELAB-format packed float color (the format produced by {@link #cielab(float, float, float, float)})
     * into an RGBA8888 Color as used by libGDX (called {@code editing}).
     * @param editing a libGDX color that will be filled in-place with an RGBA conversion of {@code packed}
     * @param packed a packed float color, as produced by {@link #cielab(float, float, float, float)}
     * @return an RGBA8888 int color
     */
    public static Color toColor(Color editing, final float packed)
    {
        final int decoded = NumberUtils.floatToRawIntBits(packed);
        final float L = (1f/1.16f)*((decoded & 0xff) / 255f + 0.16f);
        final float A = ((decoded >>> 8 & 0xff) - 127.5f) * (0.2f / 127.5f);
        final float B = ((decoded >>> 16 & 0xff) - 127.5f) * (0.5f / 127.5f);
        final float x = reverseXYZ(L + A);
        final float y = reverseXYZ(L);
        final float z = reverseXYZ(L - B);
        editing.r = reverseGamma(Math.min(Math.max(+3.2406f * x + -0.9689f * y + -0.4986f * z, 0f), 1f));
        editing.g = reverseGamma(Math.min(Math.max(-1.5372f * x + +1.8758f * y + +0.0415f * z, 0f), 1f));
        editing.b = reverseGamma(Math.min(Math.max(+3.2406f * x + -0.9689f * y + +1.0570f * z, 0f), 1f));
        editing.a = (decoded >>> 25) * 0x1.020408p-7f; // this is 1/127 as a float
        return editing.clamp();
    }

    /**
     * Writes a CIELAB-format packed float color (the format produced by {@link #cielab(float, float, float, float)})
     * into a CIELAB-format Color called {@code editing}. This is mostly useful if the rest of your application expects
     * colors in CIELAB format, such as because you use {@link Shaders#fragmentShaderCielab} or {@link ColorfulBatch}.
     * <br>
     * Internally, this simply calls {@link Color#abgr8888ToColor(Color, float)} and returns the edited Color.
     * @param editing a libGDX Color that will be filled in-place with the color {@code cielab}, unchanged from its color space
     * @param cielab a packed float color, as produced by {@link #cielab(float, float, float, float)}
     * @return an RGBA8888 int color
     */
    public static Color toCIELABColor(Color editing, final float cielab){
        Color.abgr8888ToColor(editing, cielab);
        return editing;
    }

    /**
     * Takes a color encoded as an RGBA8888 int and converts to a packed float in the CIELAB format this uses.
     * @param rgba an int with the channels (in order) red, green, blue, alpha; should have 8 bits per channel
     * @return a packed float as CIELAB, which this class can use
     */
    public static float fromRGBA8888(final int rgba) {
        final float r = forwardGamma((rgba >>> 24) * 0x1.010101010101p-8f);
        final float g = forwardGamma((rgba >>> 16 & 0xFF) * 0x1.010101010101p-8f);
        final float b = forwardGamma((rgba >>> 8 & 0xFF) * 0x1.010101010101p-8f);

        final float x = forwardXYZ(0.4124f * r + 0.2126f * g + 0.0193f * b);
        final float y = forwardXYZ(0.3576f * r + 0.7152f * g + 0.1192f * b);
        final float z = forwardXYZ(0.1805f * r + 0.0722f * g + 0.9505f * b);

        return NumberUtils.intBitsToFloat(
                          Math.min(Math.max((int)((1.16f*y - 0.16f) * 255.999f    ), 0), 255)
                        | Math.min(Math.max((int)((5f*(x - y)) * 127.999f + 127.5f), 0), 255) << 8
                        | Math.min(Math.max((int)((2f*(y - z)) * 127.999f + 127.5f), 0), 255) << 16
                        | (rgba & 0xFE) << 24);
    }

    /**
     * Takes a color encoded as an ABGR packed float and converts to a packed float in the CIELAB format this uses.
     * @param packed a packed float in ABGR8888 format, with A in the MSB and R in the LSB
     * @return a packed float as CIELAB, which this class can use
     */
    public static float fromRGBA(final float packed) {
        final int abgr = NumberUtils.floatToRawIntBits(packed);
        final float r = forwardGamma((abgr & 0xFF) * 0x1.010101010101p-8f);
        final float g = forwardGamma((abgr >>> 8 & 0xFF) * 0x1.010101010101p-8f);
        final float b = forwardGamma((abgr >>> 16 & 0xFF) * 0x1.010101010101p-8f);

        final float x = forwardXYZ(0.4124f * r + 0.2126f * g + 0.0193f * b);
        final float y = forwardXYZ(0.3576f * r + 0.7152f * g + 0.1192f * b);
        final float z = forwardXYZ(0.1805f * r + 0.0722f * g + 0.9505f * b);

        return NumberUtils.intBitsToFloat(
                          Math.min(Math.max((int)((1.16f*y - 0.16f) * 255.999f    ), 0), 255)
                        | Math.min(Math.max((int)((5f*(x - y)) * 127.999f + 127.5f), 0), 255) << 8
                        | Math.min(Math.max((int)((2f*(y - z)) * 127.999f + 127.5f), 0), 255) << 16
                        | (abgr & 0xFE000000));
    }

    /**
     * Takes a libGDX Color that uses RGBA8888 channels and converts to a packed float in the CIELAB format this uses.
     * @param color a libGDX RGBA8888 Color
     * @return a packed float as CIELAB, which this class can use
     */
    public static float fromColor(final Color color) {
        final float r = forwardGamma(color.r);
        final float g = forwardGamma(color.g);
        final float b = forwardGamma(color.b);
        final float x = forwardXYZ(0.4124f * r + 0.2126f * g + 0.0193f * b);
        final float y = forwardXYZ(0.3576f * r + 0.7152f * g + 0.1192f * b);
        final float z = forwardXYZ(0.1805f * r + 0.0722f * g + 0.9505f * b);
        return NumberUtils.intBitsToFloat(
                          Math.min(Math.max((int)((1.16f*y - 0.16f) * 255.999f    ), 0), 255)
                        | Math.min(Math.max((int)((5f*(x - y)) * 127.999f + 127.5f), 0), 255) << 8
                        | Math.min(Math.max((int)((2f*(y - z)) * 127.999f + 127.5f), 0), 255) << 16
                        | ((int)(color.a * 255f) << 24 & 0xFE000000));
    }

    /**
     * Takes RGBA components from 0.0 to 1.0 each and converts to a packed float in the CIELAB format this uses.
     * @param r red, from 0.0 to 1.0 (both inclusive)
     * @param g green, from 0.0 to 1.0 (both inclusive)
     * @param b blue, from 0.0 to 1.0 (both inclusive)
     * @param a alpha, from 0.0 to 1.0 (both inclusive)
     * @return a packed float as CIELAB, which this class can use
     */
    public static float fromRGBA(float r, float g, float b, final float a) {
        r = forwardGamma(r);
        g = forwardGamma(g);
        b = forwardGamma(b);
        final float x = forwardXYZ(0.4124f * r + 0.2126f * g + 0.0193f * b);
        final float y = forwardXYZ(0.3576f * r + 0.7152f * g + 0.1192f * b);
        final float z = forwardXYZ(0.1805f * r + 0.0722f * g + 0.9505f * b);
        return NumberUtils.intBitsToFloat(
                          Math.min(Math.max((int)((1.16f*y - 0.16f) * 255.999f    ), 0), 255)
                        | Math.min(Math.max((int)((5f*(x - y)) * 127.999f + 127.5f), 0), 255) << 8
                        | Math.min(Math.max((int)((2f*(y - z)) * 127.999f + 127.5f), 0), 255) << 16
                        | ((int)(a * 255f) << 24 & 0xFE000000));
    }

    	/**
	 * Gets the red channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #cielab(float, float, float, float)}
	 * @return an int from 0 to 255, inclusive, representing the red channel value of the given encoded color
	 */
	public static int redInt(final float encoded)
	{
        final int decoded = NumberUtils.floatToRawIntBits(encoded);
        final float L = (1f/1.16f)*((decoded & 0xff) / 255f + 0.16f);
        final float A = ((decoded >>> 8 & 0xff) - 127.5f) * (0.2f / 127.5f);
        final float B = ((decoded >>> 16 & 0xff) - 127.5f) * (0.5f / 127.5f);
        final float x = reverseXYZ(L + A);
        final float y = reverseXYZ(L);
        final float z = reverseXYZ(L - B);
        return (int)(reverseGamma(Math.min(Math.max(+3.2406f * x + -0.9689f * y + -0.4986f * z, 0f), 1f)) * 255.999f);
	}

	/**
	 * Gets the green channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #cielab(float, float, float, float)}
	 * @return an int from 0 to 255, inclusive, representing the green channel value of the given encoded color
	 */
	public static int greenInt(final float encoded)
	{
        final int decoded = NumberUtils.floatToRawIntBits(encoded);
        final float L = (1f/1.16f)*((decoded & 0xff) / 255f + 0.16f);
        final float A = ((decoded >>> 8 & 0xff) - 127.5f) * (0.2f / 127.5f);
        final float B = ((decoded >>> 16 & 0xff) - 127.5f) * (0.5f / 127.5f);
        final float x = reverseXYZ(L + A);
        final float y = reverseXYZ(L);
        final float z = reverseXYZ(L - B);
        return (int)(reverseGamma(Math.min(Math.max(-1.5372f * x + +1.8758f * y + +0.0415f * z, 0f), 1f)) * 255.999f);
	}

	/**
	 * Gets the blue channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #cielab(float, float, float, float)}
	 * @return an int from 0 to 255, inclusive, representing the blue channel value of the given encoded color
	 */
	public static int blueInt(final float encoded)
	{
        final int decoded = NumberUtils.floatToRawIntBits(encoded);
        final float L = (1f/1.16f)*((decoded & 0xff) / 255f + 0.16f);
        final float A = ((decoded >>> 8 & 0xff) - 127.5f) * (0.2f / 127.5f);
        final float B = ((decoded >>> 16 & 0xff) - 127.5f) * (0.5f / 127.5f);
        final float x = reverseXYZ(L + A);
        final float y = reverseXYZ(L);
        final float z = reverseXYZ(L - B);
        return (int)(reverseGamma(Math.min(Math.max(+3.2406f * x + -0.9689f * y + +1.0570f * z, 0f), 1f)) * 255.999f);
	}

	/**
	 * Gets the alpha channel value of the given encoded color, as an even int ranging from 0 to 254, inclusive. Because
	 * of how alpha is stored in libGDX, no odd-number values are possible for alpha.
	 * @param encoded a color as a packed float that can be obtained by {@link #cielab(float, float, float, float)}
	 * @return an even int from 0 to 254, inclusive, representing the alpha channel value of the given encoded color
	 */
	public static int alphaInt(final float encoded)
	{
		return (NumberUtils.floatToRawIntBits(encoded) & 0xfe000000) >>> 24;
	}
    /**
     * Gets the red channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
     * @param encoded a color as a packed float that can be obtained by {@link #cielab(float, float, float, float)}
     * @return a float from 0.0f to 1.0f, inclusive, representing the red channel value of the given encoded color
     */
    public static float red(final float encoded)
    {
        final int decoded = NumberUtils.floatToRawIntBits(encoded);
        final float L = (1f/1.16f)*((decoded & 0xff) / 255f + 0.16f);
        final float A = ((decoded >>> 8 & 0xff) - 127.5f) * (0.2f / 127.5f);
        final float B = ((decoded >>> 16 & 0xff) - 127.5f) * (0.5f / 127.5f);
        final float x = reverseXYZ(L + A);
        final float y = reverseXYZ(L);
        final float z = reverseXYZ(L - B);
        return reverseGamma(Math.min(Math.max(+3.2406f * x + -0.9689f * y + -0.4986f * z, 0f), 1f));
    }

    /**
     * Gets the green channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
     * @param encoded a color as a packed float that can be obtained by {@link #cielab(float, float, float, float)}
     * @return a float from 0.0f to 1.0f, inclusive, representing the green channel value of the given encoded color
     */
    public static float green(final float encoded)
    {
        final int decoded = NumberUtils.floatToRawIntBits(encoded);
        final float L = (1f/1.16f)*((decoded & 0xff) / 255f + 0.16f);
        final float A = ((decoded >>> 8 & 0xff) - 127.5f) * (0.2f / 127.5f);
        final float B = ((decoded >>> 16 & 0xff) - 127.5f) * (0.5f / 127.5f);
        final float x = reverseXYZ(L + A);
        final float y = reverseXYZ(L);
        final float z = reverseXYZ(L - B);
        return reverseGamma(Math.min(Math.max(-1.5372f * x + +1.8758f * y + +0.0415f * z, 0f), 1f));
    }

    /**
     * Gets the blue channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
     * @param encoded a color as a packed float that can be obtained by {@link #cielab(float, float, float, float)}
     * @return a float from 0.0f to 1.0f, inclusive, representing the blue channel value of the given encoded color
     */
    public static float blue(final float encoded)
    {
        final int decoded = NumberUtils.floatToRawIntBits(encoded);
        final float L = (1f/1.16f)*((decoded & 0xff) / 255f + 0.16f);
        final float A = ((decoded >>> 8 & 0xff) - 127.5f) * (0.2f / 127.5f);
        final float B = ((decoded >>> 16 & 0xff) - 127.5f) * (0.5f / 127.5f);
        final float x = reverseXYZ(L + A);
        final float y = reverseXYZ(L);
        final float z = reverseXYZ(L - B);
        return reverseGamma(Math.min(Math.max(+3.2406f * x + -0.9689f * y + +1.0570f * z, 0f), 1f));
    }

    /**
     * Gets the alpha channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
     * @param encoded a color as a packed float that can be obtained by {@link #cielab(float, float, float, float)}
     * @return a float from 0.0f to 1.0f, inclusive, representing the alpha channel value of the given encoded color
     */
    public static float alpha(final float encoded)
    {
        return ((NumberUtils.floatToRawIntBits(encoded) & 0xfe000000) >>> 24) * 0x1.020408p-8f;
    }


    //TODO: figure out what the highest chroma is for in-gamut colors
    /**
     * Gets the "chroma" or "colorfulness" of a given CIELAB color. Chroma is similar to saturation in that grayscale
     * values have 0 saturation and 0 chroma, while brighter colors have high saturation and chroma. The difference is
     * that colors that are perceptually more-colorful have higher chroma than colors that are perceptually
     * less-colorful, regardless of hue, whereas saturation changes its meaning depending on the hue and lightness. That
     * is, the most saturated color for a given hue and lightness always has a saturation of 1, but if that color
     * isn't perceptually very colorful (as is the case for very dark and very light colors), it will have a chroma that
     * is much lower than the maximum. The result of this method can't be negative, grayscale values have very close to
     * 0 chroma, and the most colorful values should have ??? chroma.
     * @param encoded a color as a packed float that can be obtained by {@link #cielab(float, float, float, float)}
     * @return a float between 0.0f and ??? that represents how colorful the given value is
     */
    public static float chroma(final float encoded) {
        final int decoded = NumberUtils.floatToRawIntBits(encoded);
        final float a = ((decoded >>> 7 & 0x1FE) - 255) / 255f;
        final float b = ((decoded >>> 15 & 0x1FE) - 255) / 255f;
        return (float) Math.sqrt(a * a + b * b);
    }

    /**
     * Gets a color as a CIELAB packed float given floats representing hue, saturation, lightness, and opacity.
     * All parameters should normally be between 0 and 1 inclusive, though any hue is tolerated (precision loss may
     * affect the color if the hue is too large). A hue of 0 is red, progressively higher hue values go to orange,
     * yellow, green, blue, and purple before wrapping around to red as it approaches 1. A saturation of 0 is grayscale,
     * a saturation of 1 is brightly colored, and values close to 1 will usually appear more distinct than values close
     * to 0, especially if the hue is different. A lightness of 0.001f or less is always black (also using a shortcut if
     * this is the case, respecting opacity), while a lightness of 1f is white. Very bright colors are mostly in a band
     * of high-saturation where lightness is 0.5f.
     *
     * @param hue        0f to 1f, color wheel position
     * @param saturation 0f to 1f, 0f is grayscale and 1f is brightly colored
     * @param lightness  0f to 1f, 0f is black and 1f is white
     * @param opacity    0f to 1f, 0f is fully transparent and 1f is opaque
     * @return a CIELAB float encoding a color with the given properties
     */
    public static float floatGetHSL(float hue, float saturation, float lightness, float opacity) {
        if (lightness <= 0.001f) {
            return NumberUtils.intBitsToFloat((((int) (opacity * 255f) << 24) & 0xFE000000) | 0x7F7F00);
        } else {
            return fromRGBA(FloatColors.hsl2rgb(hue, saturation, lightness, opacity));
        }
    }

    /**
     * Gets the saturation of the given encoded color as HSL would calculate it, as a float ranging from 0.0f to 1.0f,
     * inclusive. This is different from {@link #chroma(float)}; see that method's documentation for details.
     *
     * @param encoded a color as a packed float that can be obtained by {@link #cielab(float, float, float, float)}
     * @return the saturation of the color from 0.0 (a grayscale color; inclusive) to 1.0 (a bright color, inclusive)
     */
    public static float saturation(final float encoded) {
        final int decoded = NumberUtils.floatToRawIntBits(encoded);
        final float L = (1f/1.16f)*((decoded & 0xff) / 255f + 0.16f);
        if(Math.abs(L - 0.5) > 0.495f) return 0f;
        final float A = ((decoded >>> 8 & 0xff) - 127.5f) * (0.2f / 127.5f);
        final float B = ((decoded >>> 16 & 0xff) - 127.5f) * (0.5f / 127.5f);
        final float x = reverseXYZ(L + A);
        final float y = reverseXYZ(L);
        final float z = reverseXYZ(L - B);
        final float r = reverseGamma(Math.min(Math.max(+3.2406f * x + -0.9689f * y + -0.4986f * z, 0f), 1f));
        final float g = reverseGamma(Math.min(Math.max(-1.5372f * x + +1.8758f * y + +0.0415f * z, 0f), 1f));
        final float b = reverseGamma(Math.min(Math.max(+3.2406f * x + -0.9689f * y + +1.0570f * z, 0f), 1f));
        float X, Y, W;
        if(g < b) {
            X = b;
            Y = g;
        }
        else {
            X = g;
            Y = b;
        }
        if(r < X) {
            W = r;
        }
        else {
            W = X;
            X = r;
        }
        return X - Math.min(W, Y);
    }

    /**
     * Defined as per HSL; normally you only need {@link #channelL(float)} to get accurate lightness for CIELAB. This
     * ranges from 0.0f (black) to 1.0f (white).
     *
     * @param encoded a packed float CIELAB color
     * @return the lightness of the given color as HSL would calculate it
     */
    public static float lightness(final float encoded) {
        final int decoded = NumberUtils.floatToRawIntBits(encoded);
        final float L = (1f/1.16f)*((decoded & 0xff) / 255f + 0.16f);
        final float A = ((decoded >>> 8 & 0xff) - 127.5f) * (0.2f / 127.5f);
        final float B = ((decoded >>> 16 & 0xff) - 127.5f) * (0.5f / 127.5f);
        final float x = reverseXYZ(L + A);
        final float y = reverseXYZ(L);
        final float z = reverseXYZ(L - B);
        final float r = reverseGamma(Math.min(Math.max(+3.2406f * x + -0.9689f * y + -0.4986f * z, 0f), 1f));
        final float g = reverseGamma(Math.min(Math.max(-1.5372f * x + +1.8758f * y + +0.0415f * z, 0f), 1f));
        final float b = reverseGamma(Math.min(Math.max(+3.2406f * x + -0.9689f * y + +1.0570f * z, 0f), 1f));
        float X, Y, W;
        if(g < b) {
            X = b;
            Y = g;
        }
        else {
            X = g;
            Y = b;
        }
        if(r < X) {
            W = r;
        }
        else {
            W = X;
            X = r;
        }
        float d = X - Math.min(W, Y);
        return X * (1f - 0.5f * d / (X + 1e-10f));
    }

    /**
     * Gets the hue of the given encoded color, as a float from 0f (inclusive, red and approaching orange if increased)
     * to 1f (exclusive, red and approaching purple if decreased).
     *
     * @param encoded a color as a packed float that can be obtained by {@link #cielab(float, float, float, float)}
     * @return The hue of the color from 0.0 (red, inclusive) towards orange, then yellow, and
     * eventually to purple before looping back to almost the same red (1.0, exclusive)
     */
    public static float hue(final float encoded) {
        final int decoded = NumberUtils.floatToRawIntBits(encoded);
        final float L = (1f/1.16f)*((decoded & 0xff) / 255f + 0.16f);
        final float A = ((decoded >>> 8 & 0xff) - 127.5f) * (0.2f / 127.5f);
        final float B = ((decoded >>> 16 & 0xff) - 127.5f) * (0.5f / 127.5f);
        final float x = reverseXYZ(L + A);
        final float y = reverseXYZ(L);
        final float z = reverseXYZ(L - B);
        final float r = reverseGamma(Math.min(Math.max(+3.2406f * x + -0.9689f * y + -0.4986f * z, 0f), 1f));
        final float g = reverseGamma(Math.min(Math.max(-1.5372f * x + +1.8758f * y + +0.0415f * z, 0f), 1f));
        final float b = reverseGamma(Math.min(Math.max(+3.2406f * x + -0.9689f * y + +1.0570f * z, 0f), 1f));
        float X, Y, Z, W;
        if(g < b) {
            X = b;
            Y = g;
            Z = -1f;
            W = 2f / 3f;
        }
        else {
            X = g;
            Y = b;
            Z = 0f;
            W = -1f / 3f;
        }
        if(r < X) {
            Z = W;
            W = r;
        }
        else {
            W = X;
            X = r;
        }
        float d = X - Math.min(W, Y);
        return Math.abs(Z + (W - Y) / (6f * d + 1e-10f));
    }

    /**
     * The "L" channel of the given packed float in CIELAB format, which is its lightness; ranges from 0.0f to
     * 1.0f . You can edit the L of a color with {@link #lighten(float, float)} and {@link #darken(float, float)}.
     *
     * @param encoded a color encoded as a packed float, as by {@link #cielab(float, float, float, float)}
     * @return the L value as a float from 0.0f to 1.0f
     */
    public static float channelL(final float encoded)
    {
        return (NumberUtils.floatToRawIntBits(encoded) & 0xff) / 255f;
    }

    /**
     * The "A" channel of the given packed float in CIELAB format, which when combined with the B channel describes the
     * hue and saturation of a color; ranges from 0f to 1f . If A is 0f, the color will be cooler, more green or
     * blue; if A is 1f, the color will be warmer, from magenta to orange. You can edit the A of a color with
     * {@link #raiseA(float, float)} and {@link #lowerA(float, float)}.
     * @param encoded a color encoded as a packed float, as by {@link #cielab(float, float, float, float)}
     * @return the A value as a float from 0.0f to 1.0f
     */
    public static float channelA(final float encoded)
    {
        return ((NumberUtils.floatToRawIntBits(encoded) >>> 8 & 0xff)) / 255f;
    }

    /**
     * The "B" channel of the given packed float in CIELAB format, which when combined with the A channel describes the
     * hue and saturation of a color; ranges from 0f to 1f . If B is 0f, the color will be more "artificial", more
     * blue or purple; if B is 1f, the color will be more "natural", from green to yellow to orange. You can edit
     * the B of a color with {@link #raiseB(float, float)} and {@link #lowerB(float, float)}.
     * @param encoded a color encoded as a packed float, as by {@link #cielab(float, float, float, float)}
     * @return the B value as a float from 0.0f to 1.0f
     */
    public static float channelB(final float encoded)
    {
        return ((NumberUtils.floatToRawIntBits(encoded) >>> 16 & 0xff)) / 255f;
    }

    /**
     * Interpolates from the packed float color start towards white by change. While change should be between 0f (return
     * start as-is) and 1f (return white), start should be a packed color, as from
     * {@link #cielab(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
     * is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp
     * towards white. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and both
     * chroma of start as-is.
     * @see #darken(float, float) the counterpart method that darkens a float color
     * @param start the starting color as a packed float
     * @param change how much to go from start toward white, as a float between 0 and 1; higher means closer to white
     * @return a packed float that represents a color between start and white
     */
    public static float lighten(final float start, final float change) {
        final int s = NumberUtils.floatToRawIntBits(start), i = s & 0xFF, other = s & 0xFEFFFF00;
        return NumberUtils.intBitsToFloat(((int) (i + (0xFF - i) * change) & 0xFF) | other);
    }

    /**
     * Interpolates from the packed float color start towards black by change. While change should be between 0f (return
     * start as-is) and 1f (return black), start should be a packed color, as from
     * {@link #cielab(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
     * is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp
     * towards black. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and both
     * chroma of start as-is.
     * @see #lighten(float, float) the counterpart method that lightens a float color
     * @param start the starting color as a packed float
     * @param change how much to go from start toward black, as a float between 0 and 1; higher means closer to black
     * @return a packed float that represents a color between start and black
     */
    public static float darken(final float start, final float change) {
        final int s = NumberUtils.floatToRawIntBits(start), i = s & 0xFF, other = s & 0xFEFFFF00;
        return NumberUtils.intBitsToFloat(((int) (i * (1f - change)) & 0xFF) | other);
    }

    /**
     * Interpolates from the packed float color start towards a warmer color (orange to magenta) by change. While change
     * should be between 0f (return start as-is) and 1f (return fully warmed), start should be a packed color, as from
     * {@link #cielab(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors,
     * and is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to
     * lerp towards a warmer color. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the
     * alpha and L of start as-is.
     * @see #lowerA(float, float) the counterpart method that cools a float color
     * @param start the starting color as a packed float
     * @param change how much to warm start, as a float between 0 and 1; higher means a warmer result
     * @return a packed float that represents a color between start and a warmer color
     */
    public static float raiseA(final float start, final float change) {
        final int s = NumberUtils.floatToRawIntBits(start), p = s >>> 8 & 0xFF, other = s & 0xFEFF00FF;
        return NumberUtils.intBitsToFloat(((int) (p + (0xFF - p) * change) << 8 & 0xFF00) | other);
    }

    /**
     * Interpolates from the packed float color start towards a cooler color (green to blue) by change. While change
     * should be between 0f (return start as-is) and 1f (return fully cooled), start should be a packed color, as from
     * {@link #cielab(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
     * is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp
     * towards a cooler color. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and
     * L of start as-is.
     * @see #raiseA(float, float) the counterpart method that warms a float color
     * @param start the starting color as a packed float
     * @param change how much to cool start, as a float between 0 and 1; higher means a cooler result
     * @return a packed float that represents a color between start and a cooler color
     */
    public static float lowerA(final float start, final float change) {
        final int s = NumberUtils.floatToRawIntBits(start), p = s >>> 8 & 0xFF, other = s & 0xFEFF00FF;
        return NumberUtils.intBitsToFloat(((int) (p * (1f - change)) & 0xFF) << 8 | other);
    }

    /**
     * Interpolates from the packed float color start towards a "natural" color (between green and orange) by change.
     * While change should be between 0f (return start as-is) and 1f (return fully natural), start should be a packed
     * color, as from {@link #cielab(float, float, float, float)}. This is a good way to reduce allocations of temporary
     * Colors, and is a little more efficient and clear than using
     * {@link FloatColors#lerpFloatColors(float, float, float)} to lerp towards a more natural color. Unlike
     * {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and L of start as-is.
     * @see #lowerB(float, float) the counterpart method that makes a float color less natural
     * @param start the starting color as a packed float
     * @param change how much to change start to a natural color, as a float between 0 and 1; higher means a more natural result
     * @return a packed float that represents a color between start and a more natural color
     */
    public static float raiseB(final float start, final float change) {
        final int s = NumberUtils.floatToRawIntBits(start), t = s >>> 16 & 0xFF, other = s & 0xFE00FFFF;
        return NumberUtils.intBitsToFloat(((int) (t + (0xFF - t) * change) << 16 & 0xFF0000) | other);
    }

    /**
     * Interpolates from the packed float color start towards an "artificial" color (between blue and purple) by change.
     * While change should be between 0f (return start as-is) and 1f (return fully artificial), start should be a packed
     * color, as from {@link #cielab(float, float, float, float)}. This is a good way to reduce allocations of temporary
     * Colors, and is a little more efficient and clear than using
     * {@link FloatColors#lerpFloatColors(float, float, float)} to lerp towards a more artificial color. Unlike
     * {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and L of start as-is.
     * @see #raiseB(float, float) the counterpart method that makes a float color less artificial
     * @param start the starting color as a packed float
     * @param change how much to change start to a bolder color, as a float between 0 and 1; higher means a more artificial result
     * @return a packed float that represents a color between start and a more artificial color
     */
    public static float lowerB(final float start, final float change) {
        final int s = NumberUtils.floatToRawIntBits(start), t = s >>> 16 & 0xFF, other = s & 0xFE00FFFF;
        return NumberUtils.intBitsToFloat(((int) (t * (1f - change)) & 0xFF) << 16 | other);
    }

    /**
     * Interpolates from the packed float color start towards that color made opaque by change. While change should be
     * between 0f (return start as-is) and 1f (return start with full alpha), start should be a packed color, as from
     * {@link #cielab(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
     * is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp
     * towards transparent. This won't change the L, A, or B of the color.
     * @see #fade(float, float) the counterpart method that makes a float color more translucent
     * @param start the starting color as a packed float
     * @param change how much to go from start toward opaque, as a float between 0 and 1; higher means closer to opaque
     * @return a packed float that represents a color between start and its opaque version
     */
    public static float blot(final float start, final float change) {
        final int s = NumberUtils.floatToRawIntBits(start), opacity = s >>> 24 & 0xFE, other = s & 0x00FFFFFF;
        return NumberUtils.intBitsToFloat(((int) (opacity + (0xFE - opacity) * change) & 0xFE) << 24 | other);
    }

    /**
     * Interpolates from the packed float color start towards transparent by change. While change should be between 0
     * (return start as-is) and 1f (return the color with 0 alpha), start should be a packed color, as from
     * {@link #cielab(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors,
     * and is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to
     * lerp towards transparent. This won't change the L, A, or B of the color.
     * @see #blot(float, float) the counterpart method that makes a float color more opaque
     * @param start the starting color as a packed float
     * @param change how much to go from start toward transparent, as a float between 0 and 1; higher means closer to transparent
     * @return a packed float that represents a color between start and transparent
     */
    public static float fade(final float start, final float change) {
        final int s = NumberUtils.floatToRawIntBits(start), opacity = s & 0xFE, other = s & 0x00FFFFFF;
        return NumberUtils.intBitsToFloat(((int) (opacity * (1f - change)) & 0xFE) << 24 | other);
    }
}
