package com.github.tommyettinger.colorful.cielab;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.NumberUtils;
import com.github.tommyettinger.colorful.FloatColors;
import com.github.tommyettinger.colorful.Shaders;
import com.github.tommyettinger.colorful.TrigTools;

import java.util.Random;

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
     * @param a     0f to 1f, A component of CIELAB, with 1f more orange, red, or magenta
     * @param b     0f to 1f, B component of CIELAB, with 1f more green, yellow, or red
     * @param alpha 0f to 1f, 0f makes the color transparent and 1f makes it opaque
     * @return a float encoding a color with the given properties
     */
    public static float cielab(float l, float a, float b, float alpha) {
        return NumberUtils.intBitsToFloat(((int) (alpha * 255.999f) << 24 & 0xFE000000) | ((int) (b * 255.999f) << 16 & 0xFF0000)
                | ((int) (a * 255.999f) << 8 & 0xFF00) | ((int) (l * 255.999f) & 0xFF));
    }
    /**
     * Gets a packed float representation of a color given as 4 float components, L, A, B, and alpha, with each
     * component clamped to the 0f to 1f range before being entered into the packed float color. This is only different
     * from {@link #cielab(float, float, float, float)} in that it clamps each component. It still permits out-of-gamut
     * colors to be stored, but as long as the rendering code (such as a shader or ColorfulBatch) does its own
     * validation or correction of colors, then having out-of-gamut colors shouldn't be a problem.
     *
     * @see #cielab(float, float, float, float) This uses the same definitions for L, A, B, and alpha as cielab().
     * @param l     0f to 1f, lightness or L component of CIELAB, with 0.5f meaning "no change" and 1f brightening
     * @param a     0f to 1f, A component of CIELAB, with 1f more orange, red, or magenta
     * @param b     0f to 1f, B component of CIELAB, with 1f more green, yellow, or red
     * @param alpha 0f to 1f, 0f makes the color transparent and 1f makes it opaque
     * @return a float encoding a color with the given properties
     */
    public static float clamp(float l, float a, float b, float alpha) {
        return NumberUtils.intBitsToFloat((Math.min(Math.max((int) (alpha * 127.999f), 0), 127) << 25)
                | (Math.min(Math.max((int) (b * 255.999f), 0), 255) << 16)
                | (Math.min(Math.max((int) (a * 255.999f), 0), 255) << 8)
                | (Math.min(Math.max((int) (l * 255.999f), 0), 255))
        );
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
        final int r = (int)(reverseGamma(Math.min(Math.max(+3.2404542f * x + -1.5371385f * y + -0.4985314f * z, 0f), 1f)) * 255.999f);
        final int g = (int)(reverseGamma(Math.min(Math.max(-0.9692660f * x + +1.8760108f * y + +0.0415560f * z, 0f), 1f)) * 255.999f);
        final int b = (int)(reverseGamma(Math.min(Math.max(+0.0556434f * x + -0.2040259f * y + +1.0572252f * z, 0f), 1f)) * 255.999f);
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
        final int r = (int)(reverseGamma(Math.min(Math.max(+3.2404542f * x + -1.5371385f * y + -0.4985314f * z, 0f), 1f)) * 255.999f);
        final int g = (int)(reverseGamma(Math.min(Math.max(-0.9692660f * x + +1.8760108f * y + +0.0415560f * z, 0f), 1f)) * 255.999f);
        final int b = (int)(reverseGamma(Math.min(Math.max(+0.0556434f * x + -0.2040259f * y + +1.0572252f * z, 0f), 1f)) * 255.999f);
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
//        final float A = ((decoded >>> 8 & 0xff) - 127.5f) * (1f / 127.5f);
//        final float B = ((decoded >>> 16 & 0xff) - 127.5f) * (1f / 127.5f);
        final float A = ((decoded >>> 8 & 0xff) - 127.5f) * (0.2f / 127.5f);
        final float B = ((decoded >>> 16 & 0xff) - 127.5f) * (0.5f / 127.5f);
        final float x = reverseXYZ(L + A);
        final float y = reverseXYZ(L);
        final float z = reverseXYZ(L - B);
        editing.r = reverseGamma(Math.min(Math.max(+3.2404542f * x + -1.5371385f * y + -0.4985314f * z, 0f), 1f));
        editing.g = reverseGamma(Math.min(Math.max(-0.9692660f * x + +1.8760108f * y + +0.0415560f * z, 0f), 1f));
        editing.b = reverseGamma(Math.min(Math.max(+0.0556434f * x + -0.2040259f * y + +1.0572252f * z, 0f), 1f));
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

        final float x = forwardXYZ(0.4124564f * r  + 0.3575761f * g + 0.1804375f * b);
        final float y = forwardXYZ(0.2126729f * r  + 0.7151522f * g + 0.0721750f * b);
        final float z = forwardXYZ(0.0193339f * r  + 0.1191920f * g + 0.9503041f * b);

        return NumberUtils.intBitsToFloat(
                          Math.min(Math.max((int)((1.16f*y - 0.16f) * 255.999f    ), 0), 255)
                        | Math.min(Math.max((int)((x - y) * (127.999f * 5f) + 127.5f), 0), 255) << 8
                        | Math.min(Math.max((int)((y - z) * (127.999f * 2f) + 127.5f), 0), 255) << 16
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

        final float x = forwardXYZ(0.4124564f * r + 0.3575761f * g + 0.1804375f * b);
        final float y = forwardXYZ(0.2126729f * r + 0.7151522f * g + 0.0721750f * b);
        final float z = forwardXYZ(0.0193339f * r + 0.1191920f * g + 0.9503041f * b);

        return NumberUtils.intBitsToFloat(
                          Math.min(Math.max((int)((1.16f*y - 0.16f) * 255.999f    ), 0), 255)
                        | Math.min(Math.max((int)(((x - y)) * (127.999f * 5f) + 127.5f), 0), 255) << 8
                        | Math.min(Math.max((int)(((y - z)) * (127.999f * 2f) + 127.5f), 0), 255) << 16
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
        final float x = forwardXYZ(0.4124564f * r + 0.3575761f * g + 0.1804375f * b);
        final float y = forwardXYZ(0.2126729f * r + 0.7151522f * g + 0.0721750f * b);
        final float z = forwardXYZ(0.0193339f * r + 0.1191920f * g + 0.9503041f * b);
        return NumberUtils.intBitsToFloat(
                          Math.min(Math.max((int)((1.16f*y - 0.16f) * 255.999f    ), 0), 255)
                        | Math.min(Math.max((int)(((x - y)) * (127.999f * 5f) + 127.5f), 0), 255) << 8
                        | Math.min(Math.max((int)(((y - z)) * (127.999f * 2f) + 127.5f), 0), 255) << 16
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
        final float x = forwardXYZ(0.4124564f * r + 0.3575761f * g + 0.1804375f * b);
        final float y = forwardXYZ(0.2126729f * r + 0.7151522f * g + 0.0721750f * b);
        final float z = forwardXYZ(0.0193339f * r + 0.1191920f * g + 0.9503041f * b);
        return NumberUtils.intBitsToFloat(
                          Math.min(Math.max((int)((1.16f*y - 0.16f) * 255.999f    ), 0), 255)
                        | Math.min(Math.max((int)(((x - y)) * (127.999f * 5f) + 127.5f), 0), 255) << 8
                        | Math.min(Math.max((int)(((y - z)) * (127.999f * 2f) + 127.5f), 0), 255) << 16
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
        final float A = ((decoded >>> 8 & 0xff) - 127.5f) *  (0.2f / 127.5f);
        final float B = ((decoded >>> 16 & 0xff) - 127.5f) * (0.5f / 127.5f);
        final float x = reverseXYZ(L + A);
        final float y = reverseXYZ(L);
        final float z = reverseXYZ(L - B);
        return (int)(reverseGamma(Math.min(Math.max(+3.2404542f * x + -1.5371385f * y + -0.4985314f * z, 0f), 1f)) * 255.999f);
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
        final float A = ((decoded >>> 8 & 0xff) - 127.5f) *  (0.2f / 127.5f);
        final float B = ((decoded >>> 16 & 0xff) - 127.5f) * (0.5f / 127.5f);
        final float x = reverseXYZ(L + A);
        final float y = reverseXYZ(L);
        final float z = reverseXYZ(L - B);
        return (int)(reverseGamma(Math.min(Math.max(-0.9692660f * x + +1.8760108f * y + +0.0415560f * z, 0f), 1f)) * 255.999f);
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
        final float A = ((decoded >>> 8 & 0xff) - 127.5f) *  (0.2f / 127.5f);
        final float B = ((decoded >>> 16 & 0xff) - 127.5f) * (0.5f / 127.5f);
        final float x = reverseXYZ(L + A);
        final float y = reverseXYZ(L);
        final float z = reverseXYZ(L - B);
        return (int)(reverseGamma(Math.min(Math.max(+0.0556434f * x + -0.2040259f * y + +1.0572252f * z, 0f), 1f)) * 255.999f);
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
        final float A = ((decoded >>> 8 & 0xff) - 127.5f) *  (0.2f / 127.5f);
        final float B = ((decoded >>> 16 & 0xff) - 127.5f) * (0.5f / 127.5f);
        final float x = reverseXYZ(L + A);
        final float y = reverseXYZ(L);
        final float z = reverseXYZ(L - B);
        return reverseGamma(Math.min(Math.max(+3.2404542f * x + -1.5371385f * y + -0.4985314f * z, 0f), 1f));
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
        final float A = ((decoded >>> 8 & 0xff) - 127.5f) *  (0.2f / 127.5f);
        final float B = ((decoded >>> 16 & 0xff) - 127.5f) * (0.5f / 127.5f);
        final float x = reverseXYZ(L + A);
        final float y = reverseXYZ(L);
        final float z = reverseXYZ(L - B);
        return reverseGamma(Math.min(Math.max(-0.9692660f * x + +1.8760108f * y + +0.0415560f * z, 0f), 1f));
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
        final float A = ((decoded >>> 8 & 0xff) - 127.5f) *  (0.2f / 127.5f);
        final float B = ((decoded >>> 16 & 0xff) - 127.5f) * (0.5f / 127.5f);
        final float x = reverseXYZ(L + A);
        final float y = reverseXYZ(L);
        final float z = reverseXYZ(L - B);
        return reverseGamma(Math.min(Math.max(+0.0556434f * x + -0.2040259f * y + +1.0572252f * z, 0f), 1f));
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


    /**
     * Gets the "chroma" or "colorfulness" of a given CIELAB color. Chroma is similar to saturation in that grayscale
     * values have 0 saturation and 0 chroma, while brighter colors have high saturation and chroma. The difference is
     * that colors that are perceptually more-colorful have higher chroma than colors that are perceptually
     * less-colorful, regardless of hue, whereas saturation changes its meaning depending on the hue and lightness. That
     * is, the most saturated color for a given hue and lightness always has a saturation of 1, but if that color
     * isn't perceptually very colorful (as is the case for very dark and very light colors), it will have a chroma that
     * is much lower than the maximum. The result of this method can't be negative, grayscale values have very close to
     * 0 chroma, and the most colorful value (a shade of purple) should have 1.26365817 chroma.
     * @param encoded a color as a packed float that can be obtained by {@link #cielab(float, float, float, float)}
     * @return a float between 0.0f and 1.26365817 that represents how colorful the given value is
     */
    public static float chroma(final float encoded) {
        final int decoded = NumberUtils.floatToRawIntBits(encoded);
        final float a = ((decoded >>> 7 & 0x1FE) - 255) / 255f;
        final float b = ((decoded >>> 15 & 0x1FE) - 255) / 255f;
        return (float) Math.sqrt(a * a + b * b);
    }
    /**
     * Given a hue and lightness, this gets the (approximate) maximum chroma possible for that hue-lightness
     * combination, using CIELAB's versions of lightness and hue (not HSL). This is useful to know the bounds of
     * {@link #chroma(float)}. This should be no greater than 1.26365817f .
     * @param hue the hue, typically between 0.0f and 1.0f, to look up
     * @param lightness the lightness, clamped between 0.0f and 1.0f, to look up
     * @return the maximum possible chroma for the given hue and lightness, between 0.0f and 1.26365817f
     */
    public static float chromaLimit(final float hue, final float lightness) {
        final float h = hue - MathUtils.floor(hue);
        final float L = (1f/1.16f)*(lightness + 0.16f);
        final float A = TrigTools.cos_(h) * 1.26365817f;
        final float B = TrigTools.sin_(h) * 1.26365817f;
        final float y = reverseXYZ(L);
        float A2 = A, B2 = B;
        for (int attempt = 39; attempt >= 0; attempt--) {
            final float x = reverseXYZ(L + A2);
            final float z = reverseXYZ(L - B2);
            final float r = reverseGamma(Math.min(Math.max(+3.2404542f * x + -1.5371385f * y + -0.4985314f * z, 0f), 1f));
            final float g = reverseGamma(Math.min(Math.max(-0.9692660f * x + +1.8760108f * y + +0.0415560f * z, 0f), 1f));
            final float b = reverseGamma(Math.min(Math.max(+0.0556434f * x + -0.2040259f * y + +1.0572252f * z, 0f), 1f));
            if(r >= 0f && r <= 1f && g >= 0f && g <= 1f && b >= 0f && b <= 1f)
                break;
            final float progress = attempt * 0.025f;
            A2 = (A * progress);
            B2 = (B * progress);
        }
        return (float) Math.sqrt(A2 * A2 + B2 * B2);
    }
    /**
     * Gets the color with the same L as the CIELAB color stored in the given packed float, but the furthest A
     * B from gray possible for that lightness while keeping the same hue as the given color. This is very
     * similar to calling {@link #enrich(float, float)} with a very large {@code change} value.
     * @param packed a packed float color in CIELAB format; does not need to be in-gamut
     * @return the color that is as far from grayscale as this can get while keeping the L and hue of packed
     * @see #limitToGamut(float) You can use limitToGamut() if you only want max saturation for out-of-gamut colors.
     */
    public static float maximizeSaturation(final float packed) {
        final int decoded = NumberUtils.floatToRawIntBits(packed);
        final float lightness = (decoded & 255) / 255f;
        final float h = TrigTools.atan2_(((decoded >>> 16 & 0xff) - 127.5f), ((decoded >>> 8 & 0xff) - 127.5f));
        final float L = (1f/1.16f)*(lightness + 0.16f);
        final float A = TrigTools.cos_(h) * 1.26365817f;
        final float B = TrigTools.sin_(h) * 1.26365817f;
        final float y = reverseXYZ(L);
        float A2 = A, B2 = B;
        for (int attempt = 39; attempt >= 0; attempt--) {
            final float x = reverseXYZ(L + A2);
            final float z = reverseXYZ(L - B2);
            final float r = reverseGamma(Math.min(Math.max(+3.2404542f * x + -1.5371385f * y + -0.4985314f * z, 0f), 1f));
            final float g = reverseGamma(Math.min(Math.max(-0.9692660f * x + +1.8760108f * y + +0.0415560f * z, 0f), 1f));
            final float b = reverseGamma(Math.min(Math.max(+0.0556434f * x + -0.2040259f * y + +1.0572252f * z, 0f), 1f));
            if(r >= 0f && r <= 1f && g >= 0f && g <= 1f && b >= 0f && b <= 1f)
                break;
            final float progress = attempt * 0.025f;
            A2 = (A * progress);
            B2 = (B * progress);
        }
        return cielab(lightness, A2 * 0.5f + 0.5f, B2 * 0.5f + 0.5f, (decoded >>> 25) / 127f);
    }
    /**
     * Gets the color with the same L as the CIELAB color stored in the given packed float, but the furthest A
     * B from gray possible for that lightness while keeping the same hue as the given color. This is very
     * similar to calling {@link #enrich(float, float)} with a very large {@code change} value.
     * @param L lightness component; will be clamped between 0 and 1 if it isn't already
     * @param A green-to-red chromatic component; will be clamped between 0 and 1 if it isn't already
     * @param B blue-to-yellow chromatic component; will be clamped between 0 and 1 if it isn't already
     * @param alpha alpha component; will be clamped between 0 and 1 if it isn't already
     * @return the color that is as far from grayscale as this can get while keeping the L and hue of packed
     * @see #limitToGamut(float, float, float, float) You can use limitToGamut() if you only want max saturation
     * for out-of-gamut colors.
     */
    public static float maximizeSaturation(float L, float A, float B, float alpha) {
        L = Math.min(Math.max(L, 0f), 1f);
        A = Math.min(Math.max(A, 0f), 1f);
        B = Math.min(Math.max(B, 0f), 1f);
        alpha = Math.min(Math.max(alpha, 0f), 1f);
        final float h = TrigTools.atan2_(B - 0.5f, A - 0.5f);
        final float L0 = (1f/1.16f)*(L + 0.16f);
        final float A0 = TrigTools.cos_(h) * 1.26365817f;
        final float B0 = TrigTools.sin_(h) * 1.26365817f;
        final float y = reverseXYZ(L0);
        float A2 = A0, B2 = B0;
        for (int attempt = 39; attempt >= 0; attempt--) {
            final float x = reverseXYZ(L0 + A2);
            final float z = reverseXYZ(L0 - B2);
            final float r = reverseGamma(Math.min(Math.max(+3.2404542f * x + -1.5371385f * y + -0.4985314f * z, 0f), 1f));
            final float g = reverseGamma(Math.min(Math.max(-0.9692660f * x + +1.8760108f * y + +0.0415560f * z, 0f), 1f));
            final float b = reverseGamma(Math.min(Math.max(+0.0556434f * x + -0.2040259f * y + +1.0572252f * z, 0f), 1f));
            if(r >= 0f && r <= 1f && g >= 0f && g <= 1f && b >= 0f && b <= 1f)
                break;
            final float progress = attempt * 0.025f;
            A2 = (A0 * progress);
            B2 = (B0 * progress);
        }
        return cielab(L, A2, B2, alpha);
    }

    /**
     * Gets the hue of the given CIELAB float color, but as CIELAB understands hue rather than how HSL does.
     * This is different from {@link #hue(float)}, which uses HSL. This gives a float between 0 (inclusive)
     * and 1 (exclusive).
     *
     * @param packed a packed CIELAB float color
     * @return a float between 0 (inclusive) and 1 (exclusive) that represents hue in the CIELAB color space
     */
    public static float cielabHue(final float packed) {
        final int decoded = NumberUtils.floatToRawIntBits(packed);
        final float A = ((decoded >>> 8 & 0xff) - 127.5f);
        final float B = ((decoded >>> 16 & 0xff) - 127.5f);
        return TrigTools.atan2_(B, A);
    }

    /**
     * Gets the saturation of the given CIELAB float color, but as CIELAB understands saturation rather than how HSL
     * does. Saturation here is a fraction of the chroma limit (see {@link #chromaLimit(float, float)}) for a given hue
     * and lightness, and is between 0 and 1. This gives a float between 0 (inclusive) and 1 (inclusive).
     *
     * @param packed a packed CIELAB float color
     * @return a float between 0 (inclusive) and 1 (inclusive) that represents saturation in the CIELAB color space
     */
    public static float cielabSaturation(final float packed) {
        final int decoded = NumberUtils.floatToRawIntBits(packed);
        final float L = (1f/1.16f)*(((decoded & 0xff) / 255f) + 0.16f);
        final float A = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
        final float B = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
        final float h = TrigTools.atan2_(B - 0.5f, A - 0.5f);
        final float L0 = (1f/1.16f)*(L + 0.16f);
        final float A0 = TrigTools.cos_(h) * 1.26365817f;
        final float B0 = TrigTools.sin_(h) * 1.26365817f;
        final float y = reverseXYZ(L0);
        float A2 = A0, B2 = B0;
        for (int attempt = 39; attempt >= 0; attempt--) {
            final float x = reverseXYZ(L + A2);
            final float z = reverseXYZ(L - B2);
            final float r = reverseGamma(Math.min(Math.max(+3.2404542f * x + -1.5371385f * y + -0.4985314f * z, 0f), 1f));
            final float g = reverseGamma(Math.min(Math.max(-0.9692660f * x + +1.8760108f * y + +0.0415560f * z, 0f), 1f));
            final float b = reverseGamma(Math.min(Math.max(+0.0556434f * x + -0.2040259f * y + +1.0572252f * z, 0f), 1f));
            if(r >= 0f && r <= 1f && g >= 0f && g <= 1f && b >= 0f && b <= 1f)
                break;
            final float progress = attempt * 0.025f;
            A2 = (A0 * progress);
            B2 = (B0 * progress);
        }
        final float dist = (float) Math.sqrt(A2 * A2 + B2 * B2);
        return (float) Math.sqrt(A * A + B * B) / dist;
    }
    /**
     * Gets the lightness of the given CIELAB float color, but as CIELAB understands lightness rather than how HSL does.
     * This is different from {@link #lightness(float)}, which uses HSL. This gives a float between 0 (inclusive)
     * and 1 (inclusive).
     * <br>
     * This is the same as {@link #channelL(float)}.
     *
     * @param packed a packed CIELAB float color
     * @return a float between 0 (inclusive) and 1 (inclusive) that represents lightness in the CIELAB color space
     */
    public static float cielabLightness(final float packed){
        return (NumberUtils.floatToRawIntBits(packed) & 0xff) / 255f;
    }

    /**
     * A different way to specify a CIELAB color, using hue, saturation, lightness, and alpha like a normal HSL(A) color
     * but calculating them directly in the CIELAB color space. This is more efficient than
     * {@link #floatGetHSL(float, float, float, float)}. You may prefer using
     * {@link #cielabByHCL(float, float, float, float)}, which takes an absolute chroma as opposed to the saturation
     * here (which is a fraction of the maximum chroma).
     * <br>
     * Note that this takes a different value for its {@code hue} that the method {@link #hue(float)} produces, just
     * like its {@code saturation} and the method {@link #saturation(float)}, and {@code lightness} and the method
     * {@link #lightness(float)}. The hue is just distributed differently, and the lightness should be equivalent to
     * {@link #channelL(float)}, but the saturation here refers to what fraction the chroma should be of the maximum
     * chroma for the given hue and lightness. You can use {@link #cielabHue(float)}, {@link #cielabSaturation(float)},
     * and {@link #cielabLightness(float)} to get the hue, saturation, and lightness values from an existing color that
     * this will understand ({@link #alpha(float)} too).
     * @param hue between 0 and 1, usually, but this will automatically wrap if too high or too low
     * @param saturation will be clamped between 0 and 1
     * @param lightness will be clamped between 0 and 1
     * @param alpha will be clamped between 0 and 1
     * @return a packed CIELAB float color that tries to match the requested hue, saturation, and lightness
     */
    public static float cielabByHSL(float hue, float saturation, float lightness, float alpha) {
        lightness = Math.min(Math.max(lightness, 0f), 1f);
        saturation = Math.min(Math.max(saturation, 0f), 1f);
        hue -= MathUtils.floor(hue);
        alpha = Math.min(Math.max(alpha, 0f), 1f);
        final float L = (1f/1.16f)*(lightness + 0.16f);
        final float L0 = (1f/1.16f)*(L + 0.16f);
        final float cos = TrigTools.cos_(hue);
        final float sin = TrigTools.sin_(hue);
        final float A0 = cos * 1.26365817f;
        final float B0 = sin * 1.26365817f;
        final float y = reverseXYZ(L0);
        float A2 = A0, B2 = B0;
        for (int attempt = 39; attempt >= 0; attempt--) {
            final float x = reverseXYZ(L + A2);
            final float z = reverseXYZ(L - B2);
            final float r = reverseGamma(Math.min(Math.max(+3.2404542f * x + -1.5371385f * y + -0.4985314f * z, 0f), 1f));
            final float g = reverseGamma(Math.min(Math.max(-0.9692660f * x + +1.8760108f * y + +0.0415560f * z, 0f), 1f));
            final float b = reverseGamma(Math.min(Math.max(+0.0556434f * x + -0.2040259f * y + +1.0572252f * z, 0f), 1f));
            if(r >= 0f && r <= 1f && g >= 0f && g <= 1f && b >= 0f && b <= 1f)
                break;
            final float progress = attempt * 0.025f;
            A2 = (A0 * progress);
            B2 = (B0 * progress);
        }
        final float dist = (float) Math.sqrt(A2 * A2 + B2 * B2) * saturation;
        return NumberUtils.intBitsToFloat(
                (int) (alpha * 127.999f) << 25 |
                        (int) (sin * dist + 128f) << 16 |
                        (int) (cos * dist + 128f) << 8 |
                        (int) (lightness * 255.999f));
    }

    /**
     * A different way to specify a CIELAB color, using hue, chroma, lightness, and alpha something like a normal HSL(A)
     * color but calculating them directly in the CIELAB color space. This has you specify the desired chroma directly,
     * as obtainable with {@link #chroma(float)}, rather than the saturation, which is a fraction of the maximum chroma
     * (saturation is what {@link #cielabByHSL(float, float, float, float)} uses). Note that this takes a different
     * value for its {@code hue} that the method {@link #hue(float)} produces, just like {@code lightness} and the
     * method {@link #lightness(float)}. The hue is just distributed differently, and the lightness should be equivalent
     * to {@link #channelL(float)}. If you use this to get two colors with the same chroma and lightness, but different
     * hue, then the resulting colors should have similar colorfulness unless one or both chroma values exceeded the
     * gamut limit (you can get this limit with {@link #chromaLimit(float, float)}). If a chroma value given is greater
     * than the chroma limit, this clamps chroma to that limit. You can use {@link #cielabHue(float)},
     * {@link #chroma(float)}, and {@link #cielabLightness(float)} to get the hue, chroma, and lightness values from an
     * existing color that this will understand ({@link #alpha(float)} too).
     * @param hue between 0 and 1, usually, but this will automatically wrap if too high or too low
     * @param chroma will be clamped between 0 and the maximum chroma possible for the given hue and lightness
     * @param lightness will be clamped between 0 and 1
     * @param alpha will be clamped between 0 and 1
     * @return a packed CIELAB float color that tries to match the requested hue, chroma, and lightness
     */
    public static float cielabByHCL(float hue, float chroma, float lightness, float alpha) {
        lightness = Math.min(Math.max(lightness, 0f), 1f);
        chroma = Math.max(chroma, 0f) * 127.5f;
        hue -= MathUtils.floor(hue);
        alpha = Math.min(Math.max(alpha, 0f), 1f);
        return NumberUtils.intBitsToFloat(
                (int) (alpha * 127.999f) << 25 |
                        Math.min(Math.max((int) (TrigTools.sin_(hue) * chroma + 127.5f), 0), 255) << 16 |
                        Math.min(Math.max((int) (TrigTools.cos_(hue) * chroma + 127.5f), 0), 255) << 8 |
                        (int) (lightness * 255.999f));
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
        final float A = ((decoded >>> 8 & 0xff) - 127.5f) *  (0.2f / 127.5f);
        final float B = ((decoded >>> 16 & 0xff) - 127.5f) * (0.5f / 127.5f);
        final float x = reverseXYZ(L + A);
        final float y = reverseXYZ(L);
        final float z = reverseXYZ(L - B);
        final float r = reverseGamma(Math.min(Math.max(+3.2404542f * x + -1.5371385f * y + -0.4985314f * z, 0f), 1f));
        final float g = reverseGamma(Math.min(Math.max(-0.9692660f * x + +1.8760108f * y + +0.0415560f * z, 0f), 1f));
        final float b = reverseGamma(Math.min(Math.max(+0.0556434f * x + -0.2040259f * y + +1.0572252f * z, 0f), 1f));
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
        final float A = ((decoded >>> 8 & 0xff) - 127.5f) *  (0.2f / 127.5f);
        final float B = ((decoded >>> 16 & 0xff) - 127.5f) * (0.5f / 127.5f);
        final float x = reverseXYZ(L + A);
        final float y = reverseXYZ(L);
        final float z = reverseXYZ(L - B);
        final float r = reverseGamma(Math.min(Math.max(+3.2404542f * x + -1.5371385f * y + -0.4985314f * z, 0f), 1f));
        final float g = reverseGamma(Math.min(Math.max(-0.9692660f * x + +1.8760108f * y + +0.0415560f * z, 0f), 1f));
        final float b = reverseGamma(Math.min(Math.max(+0.0556434f * x + -0.2040259f * y + +1.0572252f * z, 0f), 1f));
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
        final float A = ((decoded >>> 8 & 0xff) - 127.5f) *  (0.2f / 127.5f);
        final float B = ((decoded >>> 16 & 0xff) - 127.5f) * (0.5f / 127.5f);
        final float x = reverseXYZ(L + A);
        final float y = reverseXYZ(L);
        final float z = reverseXYZ(L - B);
        final float r = reverseGamma(Math.min(Math.max(+3.2404542f * x + -1.5371385f * y + -0.4985314f * z, 0f), 1f));
        final float g = reverseGamma(Math.min(Math.max(-0.9692660f * x + +1.8760108f * y + +0.0415560f * z, 0f), 1f));
        final float b = reverseGamma(Math.min(Math.max(+0.0556434f * x + -0.2040259f * y + +1.0572252f * z, 0f), 1f));
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
     * Gets a variation on the packed float color basis as another packed float that has its hue, saturation, lightness,
     * and opacity adjusted by the specified amounts. Note that this edits the color in HSL space, not CIELAB! Takes
     * floats representing the amounts of change to apply to hue, saturation, lightness, and opacity; these can be
     * between -1f and 1f. Returns a float that can be used as a packed or encoded color with methods like
     * {@link com.badlogic.gdx.graphics.g2d.Batch#setPackedColor(float)}. The float is likely to be different than the
     * result of {@code basis} unless hue, saturation, lightness, and opacity are all 0.
     * This won't allocate any objects.
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
     * @param light      -1f to 1f, the light/brightness change that can be applied to the new float color
     * @param opacity    -1f to 1f, the opacity/alpha change that can be applied to the new float color
     * @return a float encoding a variation of basis with the given changes
     */
    public static float toEditedFloat(float basis, float hue, float saturation, float light, float opacity) {
        final int decoded = NumberUtils.floatToRawIntBits(basis);
        final float li = Math.min(Math.max(light + (decoded & 0xff) / 255f, 0f), 1f);
        opacity = Math.min(Math.max(opacity + (decoded >>> 24 & 0xfe) * 0x1.020408p-8f, 0f), 1f);
        if (li <= 0.001f)
            return NumberUtils.intBitsToFloat((((int) (opacity * 255f) << 24) & 0xFE000000) | 0x808000);
        final float L = (1f/1.16f)*(li + 0.16f);
        final float A = ((decoded >>> 8 & 0xff) - 127.5f) *  (0.2f / 127.5f);
        final float B = ((decoded >>> 16 & 0xff) - 127.5f) * (0.5f / 127.5f);
        final float x = reverseXYZ(L + A);
        final float y = reverseXYZ(L);
        final float z = reverseXYZ(L - B);
        final float r = reverseGamma(Math.min(Math.max(+3.2404542f * x + -1.5371385f * y + -0.4985314f * z, 0f), 1f));
        final float g = reverseGamma(Math.min(Math.max(-0.9692660f * x + +1.8760108f * y + +0.0415560f * z, 0f), 1f));
        final float b = reverseGamma(Math.min(Math.max(+0.0556434f * x + -0.2040259f * y + +1.0572252f * z, 0f), 1f));
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
        final float d = X - Math.min(W, Y);
        final float lum = X * (1f - 0.5f * d / (X + 1e-10f));
        hue += Math.abs(Z + (W - Y) / (6f * d + 1e-10f)) + 1f;
        saturation += (X - lum) / (Math.min(lum, 1f - lum) + 1e-10f);
        return fromRGBA(FloatColors.hsl2rgb(hue - (int)hue, Math.min(Math.max(saturation, 0f), 1f), lum, opacity));
    }


    /**
     * Given a packed float CIELAB color, this edits its L, A, B, and alpha channels by adding the corresponding "add"
     * parameter and then clamping. This returns a different float value (of course, the given float can't be edited
     * in-place). You can give a value of 0 for any "add" parameter you want to stay unchanged. This clamps the
     * resulting color so it contains in-range L, A, B, and alpha values, but it doesn't guarantee it stays in-gamut.
     * @param encoded a packed float CIELAB color
     * @param addL how much to add to the L channel; typically in the -1 to 1 range
     * @param addA how much to add to the A channel; typically in the -1 to 1 range
     * @param addB how much to add to the B channel; typically in the -1 to 1 range
     * @param addAlpha how much to add to the alpha channel; typically in the -1 to 1 range
     * @return a packed float CIELAB color with the requested edits applied to {@code encoded}
     */
    public static float editCIELAB(float encoded, float addL, float addA, float addB, float addAlpha) {
        return editCIELAB(encoded, addL, addA, addB, addAlpha, 1f, 1f, 1f, 1f);
    }
    /**
     * Given a packed float CIELAB color, this edits its L, A, B, and alpha channels by first multiplying each channel
     * by the corresponding "mul" parameter and then adding the corresponding "add" parameter, before clamping. This
     * means the lightness value {@code L} is multiplied by {@code mulL}, then has {@code addL} added, and then is
     * clamped to the normal range for L (0 to 1). This returns a different float value (of course, the given float
     * can't be edited in-place). You can give a value of 0 for any "add" parameter you want to stay unchanged, or a
     * value of 1 for any "mul" parameter that shouldn't change. Note that this manipulates A and B in the -0.5 to 0.5
     * range, so if you multiply by a small number like {@code 0.25f}, then this will produce a less-saturated color,
     * and if you multiply by a larger number like {@code 4f}, then you will get a much more-saturated color. This
     * clamps the resulting color so it contains in-range L, A, B, and alpha values, but it doesn't guarantee it stays
     * in-gamut.
     * @param encoded a packed float CIELAB color
     * @param addL how much to add to the L channel; typically in the -1 to 1 range
     * @param addA how much to add to the A channel; typically in the -1 to 1 range
     * @param addB how much to add to the B channel; typically in the -1 to 1 range
     * @param addAlpha how much to add to the alpha channel; typically in the -1 to 1 range
     * @param mulL how much to multiply the L channel by; should be non-negative
     * @param mulA how much to multiply the A channel by; usually non-negative (not always)
     * @param mulB how much to multiply the B channel by; usually non-negative (not always)
     * @param mulAlpha how much to multiply the alpha channel by; should be non-negative
     * @return a packed float CIELAB color with the requested edits applied to {@code encoded}
     */
    public static float editCIELAB(float encoded, float addL, float addA, float addB, float addAlpha,
                                  float mulL, float mulA, float mulB, float mulAlpha) {
        final int decoded = NumberUtils.floatToRawIntBits(encoded);
        float L = (decoded & 0xff) / 255f;
        float A = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
        float B = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
        float alpha = (decoded >>> 25) / 127f;

        L = Math.min(Math.max(L * mulL + addL, 0f), 1f);
        A = Math.min(Math.max(A * mulA + addA * 2f, -1f), 1f) * 0.5f;
        B = Math.min(Math.max(B * mulB + addB * 2f, -1f), 1f) * 0.5f;
        alpha = Math.min(Math.max(alpha * mulAlpha + addAlpha, 0f), 1f);
        return clamp(L, A, B, alpha);
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

    /**
     * Brings the chromatic components of {@code start} closer to grayscale by {@code change} (desaturating them). While
     * change should be between 0f (return start as-is) and 1f (return fully gray), start should be a packed color, as
     * from {@link #cielab(float, float, float, float)}. This only changes A and B; it leaves L and alpha alone.
     * @see #enrich(float, float) the counterpart method that makes a float color more saturated
     * @param start the starting color as a packed float
     * @param change how much to change start to a desaturated color, as a float between 0 and 1; higher means a less saturated result
     * @return a packed float that represents a color between start and a desaturated color
     */
    public static float dullen(final float start, final float change) {
        final int s = NumberUtils.floatToRawIntBits(start);
        return cielab((s & 0xFF) / 255f,
                ((s >>> 8 & 0xFF) / 255f - 0.5f) * (1f - change) + 0.5f,
                ((s >>> 16 & 0xFF) / 255f - 0.5f) * (1f - change) + 0.5f,
                (s >>> 25) / 127f);
    }

    /**
     * Pushes the chromatic components of {@code start} away from grayscale by change (saturating them). While change
     * should be between 0f (return start as-is) and 1f (return maximally saturated), start should be a packed color, as
     * from {@link #cielab(float, float, float, float)}. This changes only A and B. This prevents high values for change
     * from pushing A or B out of the valid range by using {@link #clamp(float, float, float, float)}; this doesn't
     * actually keep the color in-gamut, but usually rendering code can handle out-of-gamut colors in some way.
     * Alpha. The alpha never changes. It never changes L either.
     * @see #dullen(float, float) the counterpart method that makes a float color less saturated
     * @param start the starting color as a packed float
     * @param change how much to change start to a saturated color, as a float between 0 and 1; higher means a more saturated result
     * @return a packed float that represents a color between start and a saturated color
     */
    public static float enrich(final float start, final float change) {
        final int s = NumberUtils.floatToRawIntBits(start);
        return clamp((s & 0xFF) / 255f,
                ((s >>> 8 & 0xFF) / 255f - 0.5f) * (1f + change) + 0.5f,
                ((s >>> 16 & 0xFF) / 255f - 0.5f) * (1f + change) + 0.5f,
                (s >>> 25) / 127f);
    }

    /**
     * Given a packed float CIELAB color {@code mainColor} and another CIELAB color that it should be made to contrast with,
     * gets a packed float CIELAB color with roughly inverted intnsity but the same chromatic channels and opacity (P and T
     * are likely to be clamped if the result gets close to white or black). This won't ever produce black or other very
     * dark colors, and also has a gap in the range it produces for intensity values between 0.5 and 0.55. That allows
     * most of the colors this method produces to contrast well as a foreground when displayed on a background of
     * {@code contrastingColor}, or vice versa. This will leave the intensity unchanged if the chromatic channels of the
     * contrastingColor and those of the mainColor are already very different. This has nothing to do with the contrast
     * channel of the tweak in ColorfulBatch; where that part of the tweak can make too-similar lightness values further
     * apart by just a little, this makes a modification on {@code mainColor} to maximize its lightness difference from
     * {@code contrastingColor} without losing its other qualities.
     * @param mainColor a packed float color, as produced by {@link #cielab(float, float, float, float)}; this is the color that will be adjusted
     * @param contrastingColor a packed float color, as produced by {@link #cielab(float, float, float, float)}; the adjusted mainColor will contrast with this
     * @return a different CIELAB packed float color, based on mainColor but with potentially very different lightness
     */
    public static float inverseLightness(final float mainColor, final float contrastingColor)
    {
        final int bits = NumberUtils.floatToRawIntBits(mainColor),
                contrastBits = NumberUtils.floatToRawIntBits(contrastingColor),
                L = (bits & 0xff),
                A = (bits >>> 8 & 0xff),
                B = (bits >>> 16 & 0xff),
                cL = (contrastBits & 0xff),
                cA = (contrastBits >>> 8 & 0xff),
                cB = (contrastBits >>> 16 & 0xff);
        if((A - cA) * (A - cA) + (B - cB) * (B - cB) >= 0x10000)
            return mainColor;
        return cielab(cL < 128 ? L * (0.45f / 255f) + 0.5f : 0.5f - L * (0.45f / 255f), A / 255f, B / 255f, 0x1.0p-8f * (bits >>> 24));
    }

    /**
     * Given a packed float CIELAB color {@code mainColor} and another CIELAB color that it should be made to contrast
     * with, gets a packed float CIELAB color with L that should be quite different from {@code contrastingColor}'s L,
     * but the same chromatic channels and opacity (A and B are likely to be clamped if the result gets close to white
     * or black). This allows most of the colors this method produces to contrast well as a foreground when displayed on
     * a background of {@code contrastingColor}, or vice versa.
     * <br>
     * This is similar to {@link #inverseLightness(float, float)}, but is considerably simpler, and this method will
     * change the lightness of mainColor when the two given colors have close lightness but distant chroma. Because it
     * averages the original L of mainColor with the modified one, this tends to not produce harsh color changes.
     * @param mainColor a packed CIELAB float color; this is the color that will be adjusted
     * @param contrastingColor a packed CIELAB float color; the adjusted mainColor will contrast with the I of this
     * @return a different packed CIELAB float color, based on mainColor but typically with different lightness
     */
    public static float differentiateLightness(final float mainColor, final float contrastingColor)
    {
        final int main = NumberUtils.floatToRawIntBits(mainColor), contrast = NumberUtils.floatToRawIntBits(contrastingColor);
        return NumberUtils.intBitsToFloat((main & 0xFEFFFF00) | (contrast + 128 & 0xFF) + (main & 0xFF) >>> 1);
    }

    /**
     * Pretty simple; adds 0.5 to the given color's L and wraps it around if it would go above 1.0, then averages that
     * with the original L. This means light colors become darker, and dark colors become lighter, with almost all
     * results in the middle-range of possible lightness.
     * @param mainColor a packed CIELAB float color
     * @return a different packed CIELAB float color, with its L channel changed and limited to the correct gamut
     */
    public static float offsetLightness(final float mainColor) {
        final int decoded = NumberUtils.floatToRawIntBits(mainColor);
        return NumberUtils.intBitsToFloat((decoded & 0xFEFFFF00) | (decoded + 128 & 0xFF) + (decoded & 0xFF) >>> 1);
    }

    /**
     * Makes the additive CIELAB color stored in {@code color} cause less of a change when used as a tint, as if it were
     * mixed with neutral gray. When {@code fraction} is 1.0, this returns color unchanged; when fraction is 0.0, it
     * returns {@link Palette#GRAY}, and when it is in-between 0.0 and 1.0 it returns something between the two. This is
     * meant for things like area of effect abilities that make smaller color changes toward their periphery.
     * @param color a color that should have its tinting effect potentially weakened
     * @param fraction how much of {@code color} should be kept, from 0.0 to 1.0
     * @return a CIELAB float color between gray and {@code color}
     */
    public static float lessenChange(final float color, float fraction) {
        final int e = NumberUtils.floatToRawIntBits(color),
                sL = 0x80, sA = 0x80, sB = 0x80, sAlpha = 0xFE,
                eL = (e & 0xFF), eA = (e >>> 8) & 0xFF, eB = (e >>> 16) & 0xFF, eAlpha = e >>> 24 & 0xFE;
        return NumberUtils.intBitsToFloat(((int) (sL + fraction * (eL - sL)) & 0xFF)
                | (((int) (sA + fraction * (eA - sA)) & 0xFF) << 8)
                | (((int) (sB + fraction * (eB - sB)) & 0xFF) << 16)
                | (((int) (sAlpha + fraction * (eAlpha - sAlpha)) & 0xFE) << 24));
    }

    /**
     * Returns true if the given packed float color, as CIELAB, is valid to convert losslessly back to RGBA.
     * @param packed a packed float color as CIELAB
     * @return true if the given packed float color can be converted back and forth to RGBA
     */
    public static boolean inGamut(final float packed)
    {
        final int decoded = NumberUtils.floatToRawIntBits(packed);
        final float L = (1f/1.16f)*((decoded & 0xff) / 255f + 0.16f);
        final float A = ((decoded >>> 8 & 0xff) - 127.5f) *  (0.2f / 127.5f);
        final float B = ((decoded >>> 16 & 0xff) - 127.5f) * (0.5f / 127.5f);
        final float x = reverseXYZ(L + A);
        final float y = reverseXYZ(L);
        final float z = reverseXYZ(L - B);
        final float r = +3.2404542f * x + -1.5371385f * y + -0.4985314f * z;
        if(r <= -0x1p-8f || r >= 0x1.01p0f) return false;
        final float g = -0.9692660f * x + +1.8760108f * y + +0.0415560f * z;
        if(g <= -0x1p-8f || g >= 0x1.01p0f) return false;
        final float b = +0.0556434f * x + -0.2040259f * y + +1.0572252f * z;
        return (b > -0x1p-8f && b < 0x1.01p0f);

//        final int decoded = NumberUtils.floatToRawIntBits(packed);
//        final float L = (1f/1.16f)*((decoded & 0xff) / 255f + 0.16f);
//        final float A = ((decoded >>> 8 & 0xff) - 127.5f) *  (0.2f / 127.5f);
//        final float B = ((decoded >>> 16 & 0xff) - 127.5f) * (0.5f / 127.5f);
//        final float x = reverseXYZ(L + A);
//        final float y = reverseXYZ(L);
//        final float z = reverseXYZ(L - B);
//        boolean ret = true;
//        final float r = +3.2406f * x + -1.5372f * y + -0.4986f * z;
////        final float r = +3.2404542f * x + -1.5371385f * y + -0.4985314f * z;
//        if(r <= -0x1p-8f || r >= 0x1.01p0f) ret = false;
////        final float g = -0.9689f * x + +1.8758f * y + +0.0415f * z;
//        final float g = -0.9692660f * x + +1.8760108f * y + +0.0415560f * z;
//        if(g <= -0x1p-8f || g >= 0x1.01p0f) ret = false;
////        final float b = +0.0557f * x + -0.2040f * y + +1.0570f * z;
//        final float b = +0.0556434f * x + -0.2040259f * y + +1.0572252f * z;
//        ret &= (b > -0x1p-8f && b < 0x1.01p0f);
//        if(!ret) System.out.printf("L %f, A %f, B %f, x %f, y %f, z %f, r %f, g %f, b %f\n", L, A, B,  x, y, z,  r, g, b);
//        return ret;

    }

    /**
     * Returns true if the given CIELAB values are valid to convert losslessly back to RGBA.
     * @param L lightness, as a float from 0 to 1
     * @param A cyan-to-red chroma, as a float from 0 to 1
     * @param B blue-to-yellow chroma, as a float from 0 to 1
     * @return true if the given packed float color can be converted back and forth to RGBA
     */
    public static boolean inGamut(float L, float A, float B)
    {
        L = (1f/1.16f)*(L + 0.16f);
        A = (A - 0.5f) * (0.4f);
        B = (B - 0.5f);
        final float x = reverseXYZ(L + A);
        final float y = reverseXYZ(L);
        final float z = reverseXYZ(L - B);
        final float r = +3.2404542f * x + -1.5371385f * y + -0.4985314f * z;
        if(r < 0f || r > 1.0f) return false;
        final float g = -0.9692660f * x + +1.8760108f * y + +0.0415560f * z;
        if(g < 0f || g > 1.0f) return false;
        final float b = +0.0556434f * x + -0.2040259f * y + +1.0572252f * z;
        return (b >= 0f && b <= 1.0f);
    }

    /**
     * Iteratively checks whether the given CIELAB color is in-gamut, and either brings the color closer to grayscale if
     * it isn't in-gamut, or returns it as soon as it is in-gamut. Maintains the L of the color, only bringing A and B
     * closer to grayscale.
     * @param packed a packed float color in CIELAB format; often this color is not in-gamut
     * @return the first color this finds that is between the given CIELAB color and grayscale, and is in-gamut
     * @see #inGamut(float) You can use inGamut() if you just want to check whether a color is in-gamut.
     */
    public static float limitToGamut(final float packed) {
        final int decoded = NumberUtils.floatToRawIntBits(packed);
        final float L = (1f/1.16f)*((decoded & 0xff) / 255f + 0.16f);
        final float A = ((decoded >>> 8 & 0xff) - 127.5f) *  (1f / 127.5f);
        final float B = ((decoded >>> 16 & 0xff) - 127.5f) * (1f / 127.5f);
        final float y = reverseXYZ(L);
        float A2 = A, B2 = B;
        for (int attempt = 31; attempt >= 0; attempt--) {
            final float x = reverseXYZ(L + A2);
            final float z = reverseXYZ(L - B2);
            final float r = reverseGamma(Math.min(Math.max(+3.2404542f * x + -1.5371385f * y + -0.4985314f * z, 0f), 1f));
            final float g = reverseGamma(Math.min(Math.max(-0.9692660f * x + +1.8760108f * y + +0.0415560f * z, 0f), 1f));
            final float b = reverseGamma(Math.min(Math.max(+0.0556434f * x + -0.2040259f * y + +1.0572252f * z, 0f), 1f));
            if(r >= 0f && r <= 1f && g >= 0f && g <= 1f && b >= 0f && b <= 1f)
                break;
            final float progress = attempt * 0x1p-5f;
            A2 = (A * progress);
            B2 = (B * progress);
        }
        return cielab(L, A2 * 0.5f + 0.5f, B2 * 0.5f + 0.5f, (decoded >>> 25) / 127f);
    }

    /**
     * Iteratively checks whether the given CIELAB color is in-gamut, and either brings the color closer to grayscale if it
     * isn't in-gamut, or returns it as soon as it is in-gamut. Maintains the L of the color, only bringing A and B
     * closer to grayscale. This always produces an opaque color.
     * @param L lightness; will be clamped between 0 and 1 if it isn't already
     * @param A cyan-to-red chroma; will be clamped between 0 and 1 if it isn't already
     * @param B blue-to-yellow chroma; will be clamped between 0 and 1 if it isn't already
     * @return the first color this finds that is between the given CIELAB color and grayscale, and is in-gamut
     * @see #inGamut(float, float, float)  You can use inGamut() if you just want to check whether a color is in-gamut.
     */
    public static float limitToGamut(float L, float A, float B) {
        return limitToGamut(L, A, B, 1f);
    }
    /**
     * Iteratively checks whether the given CIELAB color is in-gamut, and either brings the color closer to grayscale if it
     * isn't in-gamut, or returns it as soon as it is in-gamut.
     * @param L lightness; will be clamped between 0 and 1 if it isn't already
     * @param A cyan-to-red chroma; will be clamped between 0 and 1 if it isn't already
     * @param B blue-to-yellow chroma; will be clamped between 0 and 1 if it isn't already
     * @param alpha opacity; will be clamped between 0 and 1 if it isn't already
     * @return the first color this finds that is between the given CIELAB color and grayscale, and is in-gamut
     * @see #inGamut(float, float, float)  You can use inGamut() if you just want to check whether a color is in-gamut.
     */
    public static float limitToGamut(float L, float A, float B, float alpha) {

        L = (1f/1.16f)*(Math.min(Math.max(L, 0f), 1f) + 0.16f);
        A = (Math.min(Math.max(A, 0f), 1f) - 0.5f) * 2f;// * (0.4f);
        B = (Math.min(Math.max(B, 0f), 1f) - 0.5f) * 2f;
        alpha = Math.min(Math.max(alpha, 0f), 1f);

        final float y = reverseXYZ(L);
        float A2 = A, B2 = B;
        for (int attempt = 31; attempt >= 0; attempt--) {
            final float x = reverseXYZ(L + A2);
            final float z = reverseXYZ(L - B2);
            final float r = reverseGamma(Math.min(Math.max(+3.2404542f * x + -1.5371385f * y + -0.4985314f * z, 0f), 1f));
            final float g = reverseGamma(Math.min(Math.max(-0.9692660f * x + +1.8760108f * y + +0.0415560f * z, 0f), 1f));
            final float b = reverseGamma(Math.min(Math.max(+0.0556434f * x + -0.2040259f * y + +1.0572252f * z, 0f), 1f));
            if(r >= 0f && r <= 1f && g >= 0f && g <= 1f && b >= 0f && b <= 1f)
                break;
            final float progress = attempt * 0x1p-5f;
            A2 = (A * progress);
            B2 = (B * progress);
        }
        return cielab(L, A2 * 0.5f + 0.5f, B2 * 0.5f + 0.5f, alpha);
    }

    /**
     * Makes a quasi-randomly-edited variant on the given {@code color}, allowing typically a small amount of
     * {@code variance} (such as 0.05 to 0.25) between the given color and what this can return. The {@code seed} should
     * be different each time this is called, and can be obtained from a random number generator to make the colors more
     * random, or can be incremented on each call. If the seed is only incremented or decremented, then this shouldn't
     * produce two similar colors in a row unless variance is very small. The variance affects the L, A, and B of the
     * generated color, and each of those channels can go up or down by the given variance as long as the total distance
     * isn't greater than the variance (this considers P and T extra-wide, going from -1 to 1, while I goes from 0 to 1,
     * but only internally for measuring distance).
     * @param color a packed float color, as produced by {@link #cielab(float, float, float, float)}
     * @param seed a long seed that should be different on each call; should not be 0
     * @param variance max amount of difference between the given color and the generated color; always less than 1
     * @return a generated packed float color that should be at least somewhat different from {@code color}
     */
    public static float randomEdit(final float color, long seed, final float variance) {
        final int decoded = NumberUtils.floatToRawIntBits(color);
        final float L = (decoded & 0xff) / 255f;
        final float A = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
        final float B = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
        final float limit = variance * variance;
        float dist, x, y, z;
        for (int j = 0; j < 50; j++) {
            x = (((seed * 0xD1B54A32D192ED03L >>> 41) - 0x7FFFFFp-1f) * 0x1p-22f) * variance;
            y = (((seed * 0xABC98388FB8FAC03L >>> 41) - 0x7FFFFFp-1f) * 0x1p-22f) * variance;
            z = (((seed * 0x8CB92BA72F3D8DD7L >>> 41) - 0x7FFFFFp-1f) * 0x1p-22f) * variance;
            seed += 0x9E3779B97F4A7C15L;
            dist = x * x + y * y + z * z;
            if(dist <= limit)
                return clamp(x + L, (A + y) * 0.5f + 0.5f, (B + z) * 0.5f + 0.5f, (decoded >>> 25) / 127f);
        }
        return color;
    }

    /**
     * Produces a random packed float color that is always in-gamut and should be uniformly distributed.
     * @param random a Random object (preferably a subclass of Random, like {@link com.badlogic.gdx.math.RandomXS128})
     * @return a packed float color that is always in-gamut
     */
    public static float randomColor(Random random) {
        float L = random.nextFloat();
        float A = random.nextFloat();
        float B = random.nextFloat();
        while (!inGamut(L, A, B)) {
            L = random.nextFloat();
            A = random.nextFloat();
            B = random.nextFloat();
        }
        return cielab(L, A, B, 1f);
    }

}
