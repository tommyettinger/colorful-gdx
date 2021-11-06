package com.github.tommyettinger.colorful.pure.oklab;

import com.github.tommyettinger.colorful.pure.FloatColors;
import com.github.tommyettinger.colorful.pure.MathTools;
import com.github.tommyettinger.ds.support.BitConversion;
import com.github.tommyettinger.ds.support.EnhancedRandom;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * Contains code for manipulating colors as {@code int} and packed {@code float} values in the Oklab color space.
 * Oklab is a very new color space that builds on the same foundation as IPT, but seems to be better-calibrated for
 * uniform lightness and colorfulness, instead of just the emphasis on uniform hue that IPT has. Relative to IPT, Oklab
 * has a noticeably smaller range in chromatic channels (IPT's protan and tritan can range past 0.8 or as low as 0.35,
 * but the similar A and B channels in Oklab don't stray past about 0.65 at the upper end, if that), but it does this so
 * the difference between two Oklab colors is just the Euclidean distance between their components. A slight difference
 * between Oklab and IPT here is that IPT shrinks the chromatic channels to store their -1 to 1 range in a color float's
 * 0 to 1 range, then offsets the shrunken range from -0.5 to 0.5, to 0 to 1; Oklab does not need to shrink the range,
 * and only offsets it in the same way (both just add 0.5).
 * <br>
 * Here's <a href="https://bottosson.github.io/posts/oklab/">Björn Ottosson's original post introducing Oklab</a>.
 * So far, <a href="https://raphlinus.github.io/color/2021/01/18/oklab-critique.html">it stood up to analysis by Raph
 * Levien</a>, and seems to be gaining fans quickly.
 */
public class ColorTools {
	/**
	 * Gets a packed float representation of a color given as 4 float components, here, L (luminance or lightness), A
	 * (a chromatic component ranging from greenish to reddish, called protan in IPT), B (a chromatic component ranging
	 * from bluish to yellowish, called tritan in IPT), and alpha (or opacity). L should be
	 * between 0 and 1, inclusive, with 0 used for very dark colors (almost only black), and 1 used for very light
	 * colors (almost only white). A and B range from 0.0 to 1.0, with grayscale results when both are about 0.5.
	 * There's some aesthetic value in changing just one chroma value. When A is high and B is low, the color is more
	 * purple/magenta, when both are low it is more bluish, when B is high and A is low, the color tends to be greenish,
	 * and when both are high it tends to be orange. When A and B are both near 0.5f, the color is closer to gray.
	 * Alpha is the multiplicative opacity of the color, and acts like RGBA's alpha.
	 * <br>
	 * This method bit-masks the resulting color's byte values, so any values can technically be given to this as
	 * L, A, and B, but they will only be reversible from the returned float color to the original L,
	 * A, and B values if the original values were in the range that {@link #channelL(float)}, {@link #channelA(float)},
	 * and {@link #channelB(float)} return. You can use {@link #inGamut(float, float, float)} to check if a given set of
	 * L, A, and B values is in-gamut, that is, it can be converted to and from an RGB color without going out of the
	 * valid range. If you just want to enforce that a color is in-gamut, you can use
	 * {@link #limitToGamut(float, float, float, float)}, which takes the same parameters this method does, or
	 * {@link #limitToGamut(float)} if you already have a packed float color that could be out-of-gamut.
	 *
	 * @param l     0f to 1f, lightness or L component of Oklab, with 0.5f meaning "no change" and 1f brightening
	 * @param a     0f to 1f, protan or A component of Oklab, with 1f more orange, red, or magenta
	 * @param b     0f to 1f, tritan or B component of Oklab, with 1f more green, yellow, or red
	 * @param alpha 0f to 1f, 0f makes the color transparent and 1f makes it opaque
	 * @return a float encoding a color with the given properties
	 */
	public static float oklab(float l, float a, float b, float alpha) {
		return BitConversion.intBitsToFloat(((int) (alpha * 255) << 24 & 0xFE000000) | ((int) (b * 255) << 16 & 0xFF0000)
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
	 * This is used when converting from RGB to Oklab, as an intermediate step.
	 * @param x any non-negative finite float to find the cube root of
	 * @return the cube root of x, approximated
	 */
	private static float cbrtPositive(float x) {
		int ix = BitConversion.floatToRawIntBits(x);
		final float x0 = x;
		ix = (ix>>>2) + (ix>>>4);
		ix += (ix>>>4);
		ix += (ix>>>8) + 0x2A5137A0;
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
		return (L - 1f) / (1f - L * 0.4285714f) + 1f;
	}

	/**
	 * Changes the curve of the internally-used lightness when it is output to another format. This makes the very-dark
	 * area smaller, matching (kind-of) the curve that the standard sRGB lightness uses. This is typically used on "from
	 * Oklab" conversions.
	 * @param L lightness, from 0 to 1 inclusive
	 * @return an adjusted L value that can be fed into a conversion to RGBA or something similar
	 */
	public static float reverseLight(final float L) {
		return (L - 1f) / (1f + L * 0.75f) + 1f;
	}

	/**
	 * Converts a packed float color in the format produced by {@link ColorTools#oklab(float, float, float, float)} to an RGBA8888 int.
	 * This format of int can be used with Pixmap and in some other places in libGDX.
	 * @param packed a packed float color, as produced by {@link ColorTools#oklab(float, float, float, float)}
	 * @return an RGBA8888 int color
	 */
	public static int toRGBA8888(final float packed)
	{
		final int decoded = BitConversion.floatToRawIntBits(packed);
		final float L = reverseLight((decoded & 0xff) / 255f);
		final float A = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float B = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = cube(L + 0.3963377774f * A + 0.2158037573f * B);
		final float m = cube(L - 0.1055613458f * A - 0.0638541728f * B);
		final float s = cube(L - 0.0894841775f * A - 1.2914855480f * B);
		final int r = (int)(reverseGamma(Math.min(Math.max(+4.0767245293f * l - 3.3072168827f * m + 0.2307590544f * s, 0f), 1f)) * 255.999f);
		final int g = (int)(reverseGamma(Math.min(Math.max(-1.2681437731f * l + 2.6093323231f * m - 0.3411344290f * s, 0f), 1f)) * 255.999f);
		final int b = (int)(reverseGamma(Math.min(Math.max(-0.0041119885f * l - 0.7034763098f * m + 1.7068625689f * s, 0f), 1f)) * 255.999f);
		return r << 24 | g << 16 | b << 8 | (decoded & 0xfe000000) >>> 24 | decoded >>> 31;
	}

	/**
	 * Converts a packed float color in the format produced by {@link ColorTools#oklab(float, float, float, float)}
	 * to a packed float in RGBA format.
	 * This format of float can be used with the standard SpriteBatch and in some other places in libGDX.
	 * @param packed a packed float color, as produced by {@link ColorTools#oklab(float, float, float, float)}
	 * @return a packed float color as RGBA
	 */
	public static float toRGBA(final float packed)
	{
		final int decoded = BitConversion.floatToRawIntBits(packed);
		final float L = reverseLight((decoded & 0xff) / 255f);
		final float A = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float B = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = cube(L + 0.3963377774f * A + 0.2158037573f * B);
		final float m = cube(L - 0.1055613458f * A - 0.0638541728f * B);
		final float s = cube(L - 0.0894841775f * A - 1.2914855480f * B);
		final int r = (int)(reverseGamma(Math.min(Math.max(+4.0767245293f * l - 3.3072168827f * m + 0.2307590544f * s, 0f), 1f)) * 255.999f);
		final int g = (int)(reverseGamma(Math.min(Math.max(-1.2681437731f * l + 2.6093323231f * m - 0.3411344290f * s, 0f), 1f)) * 255.999f);
		final int b = (int)(reverseGamma(Math.min(Math.max(-0.0041119885f * l - 0.7034763098f * m + 1.7068625689f * s, 0f), 1f)) * 255.999f);
		return BitConversion.intBitsToFloat(r | g << 8 | b << 16 | (decoded & 0xfe000000));
	}

	/**
	 * Takes a color encoded as an RGBA8888 int and converts to a packed float in the Oklab format this uses.
	 * @param rgba an int with the channels (in order) red, green, blue, alpha; should have 8 bits per channel
	 * @return a packed float as Oklab, which this class can use
	 */
	public static float fromRGBA8888(final int rgba) {
		final float r = forwardGamma((rgba >>> 24) * 0x1.010101010101p-8f);
		final float g = forwardGamma((rgba >>> 16 & 0xFF) * 0x1.010101010101p-8f);
		final float b = forwardGamma((rgba >>> 8 & 0xFF) * 0x1.010101010101p-8f);

		final float l = cbrtPositive(0.4121656120f * r + 0.5362752080f * g + 0.0514575653f * b);
		final float m = cbrtPositive(0.2118591070f * r + 0.6807189584f * g + 0.1074065790f * b);
		final float s = cbrtPositive(0.0883097947f * r + 0.2818474174f * g + 0.6302613616f * b);

		return BitConversion.intBitsToFloat(
			              Math.min(Math.max((int)(forwardLight(0.2104542553f * l + 0.7936177850f * m - 0.0040720468f * s) * 255.999f         ), 0), 255)
						| Math.min(Math.max((int)((1.9779984951f * l - 2.4285922050f * m + 0.4505937099f * s) * 127.999f + 127.5f), 0), 255) << 8
						| Math.min(Math.max((int)((0.0259040371f * l + 0.7827717662f * m - 0.8086757660f * s) * 127.999f + 127.5f), 0), 255) << 16
						| (rgba & 0xFE) << 24);
	}

	/**
	 * Takes a color encoded as an RGBA8888 packed float and converts to a packed float in the Oklab format this uses.
	 * @param packed a packed float in RGBA8888 format, with A in the MSB and R in the LSB
	 * @return a packed float as Oklab, which this class can use
	 */
	public static float fromRGBA(final float packed) {
		final int abgr = BitConversion.floatToRawIntBits(packed);
		final float r = forwardGamma((abgr & 0xFF) * 0x1.010101010101p-8f);
		final float g = forwardGamma((abgr >>> 8 & 0xFF) * 0x1.010101010101p-8f);
		final float b = forwardGamma((abgr >>> 16 & 0xFF) * 0x1.010101010101p-8f);
		final float l = cbrtPositive(0.4121656120f * r + 0.5362752080f * g + 0.0514575653f * b);
		final float m = cbrtPositive(0.2118591070f * r + 0.6807189584f * g + 0.1074065790f * b);
		final float s = cbrtPositive(0.0883097947f * r + 0.2818474174f * g + 0.6302613616f * b);
		return BitConversion.intBitsToFloat(
			              Math.min(Math.max((int)(forwardLight(0.2104542553f * l + 0.7936177850f * m - 0.0040720468f * s) * 255.999f         ), 0), 255)
						| Math.min(Math.max((int)((1.9779984951f * l - 2.4285922050f * m + 0.4505937099f * s) * 127.999f + 127.5f), 0), 255) << 8
						| Math.min(Math.max((int)((0.0259040371f * l + 0.7827717662f * m - 0.8086757660f * s) * 127.999f + 127.5f), 0), 255) << 16
						| (abgr & 0xFE000000));
	}

	/**
	 * Takes RGBA components from 0.0 to 1.0 each and converts to a packed float in the Oklab format this uses.
	 * @param r red, from 0.0 to 1.0 (both inclusive)
	 * @param g green, from 0.0 to 1.0 (both inclusive)
	 * @param b blue, from 0.0 to 1.0 (both inclusive)
	 * @param a alpha, from 0.0 to 1.0 (both inclusive)
	 * @return a packed float as Oklab, which this class can use
	 */
	public static float fromRGBA(float r, float g, float b, final float a) {
		r = forwardGamma(r);
		g = forwardGamma(g);
		b = forwardGamma(b);
		final float l = cbrtPositive(0.4121656120f * r + 0.5362752080f * g + 0.0514575653f * b);
		final float m = cbrtPositive(0.2118591070f * r + 0.6807189584f * g + 0.1074065790f * b);
		final float s = cbrtPositive(0.0883097947f * r + 0.2818474174f * g + 0.6302613616f * b);
		return BitConversion.intBitsToFloat(
				          Math.min(Math.max((int)(forwardLight(0.2104542553f * l + 0.7936177850f * m - 0.0040720468f * s) * 255.999f         ), 0), 255)
						| Math.min(Math.max((int)((1.9779984951f * l - 2.4285922050f * m + 0.4505937099f * s) * 127.999f + 127.5f), 0), 255) << 8
						| Math.min(Math.max((int)((0.0259040371f * l + 0.7827717662f * m - 0.8086757660f * s) * 127.999f + 127.5f), 0), 255) << 16
						| ((int)(a * 255f) << 24 & 0xFE000000));
	}

	/**
	 * Gets the red channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #oklab(float, float, float, float)}
	 * @return an int from 0 to 255, inclusive, representing the red channel value of the given encoded color
	 */
	public static int redInt(final float encoded)
	{
		final int decoded = BitConversion.floatToRawIntBits(encoded);
		final float L = reverseLight((decoded & 0xff) / 255f);
		final float A = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float B = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = cube(L + 0.3963377774f * A + 0.2158037573f * B);
		final float m = cube(L - 0.1055613458f * A - 0.0638541728f * B);
		final float s = cube(L - 0.0894841775f * A - 1.2914855480f * B);
		return (int)(reverseGamma(Math.min(Math.max(+4.0767245293f * l - 3.3072168827f * m + 0.2307590544f * s, 0f), 1f)) * 255.999f);
	}

	/**
	 * Gets the green channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #oklab(float, float, float, float)}
	 * @return an int from 0 to 255, inclusive, representing the green channel value of the given encoded color
	 */
	public static int greenInt(final float encoded)
	{
		final int decoded = BitConversion.floatToRawIntBits(encoded);
		final float L = reverseLight((decoded & 0xff) / 255f);
		final float A = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float B = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = cube(L + 0.3963377774f * A + 0.2158037573f * B);
		final float m = cube(L - 0.1055613458f * A - 0.0638541728f * B);
		final float s = cube(L - 0.0894841775f * A - 1.2914855480f * B);
		return (int)(reverseGamma(Math.min(Math.max(-1.2681437731f * l + 2.6093323231f * m - 0.3411344290f * s, 0f), 1f)) * 255.999f);
	}

	/**
	 * Gets the blue channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #oklab(float, float, float, float)}
	 * @return an int from 0 to 255, inclusive, representing the blue channel value of the given encoded color
	 */
	public static int blueInt(final float encoded)
	{
		final int decoded = BitConversion.floatToRawIntBits(encoded);
		final float L = reverseLight((decoded & 0xff) / 255f);
		final float A = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float B = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = cube(L + 0.3963377774f * A + 0.2158037573f * B);
		final float m = cube(L - 0.1055613458f * A - 0.0638541728f * B);
		final float s = cube(L - 0.0894841775f * A - 1.2914855480f * B);
		return (int)(reverseGamma(Math.min(Math.max(-0.0041119885f * l - 0.7034763098f * m + 1.7068625689f * s, 0f), 1f)) * 255.999f);
	}

	/**
	 * Gets the alpha channel value of the given encoded color, as an even int ranging from 0 to 254, inclusive. Because
	 * of how alpha is stored in libGDX, no odd-number values are possible for alpha.
	 * @param encoded a color as a packed float that can be obtained by {@link #oklab(float, float, float, float)}
	 * @return an even int from 0 to 254, inclusive, representing the alpha channel value of the given encoded color
	 */
	public static int alphaInt(final float encoded)
	{
		return (BitConversion.floatToRawIntBits(encoded) & 0xfe000000) >>> 24;
	}

	/**
	 * Gets the red channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #oklab(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the red channel value of the given encoded color
	 */
	public static float red(final float encoded)
	{
		final int decoded = BitConversion.floatToRawIntBits(encoded);
		final float L = reverseLight((decoded & 0xff) / 255f);
		final float A = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float B = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = cube(L + 0.3963377774f * A + 0.2158037573f * B);
		final float m = cube(L - 0.1055613458f * A - 0.0638541728f * B);
		final float s = cube(L - 0.0894841775f * A - 1.2914855480f * B);
		return reverseGamma(Math.min(Math.max(+4.0767245293f * l - 3.3072168827f * m + 0.2307590544f * s, 0f), 1f));
	}

	/**
	 * Gets the green channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #oklab(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the green channel value of the given encoded color
	 */
	public static float green(final float encoded)
	{
		final int decoded = BitConversion.floatToRawIntBits(encoded);
		final float L = reverseLight((decoded & 0xff) / 255f);
		final float A = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float B = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = cube(L + 0.3963377774f * A + 0.2158037573f * B);
		final float m = cube(L - 0.1055613458f * A - 0.0638541728f * B);
		final float s = cube(L - 0.0894841775f * A - 1.2914855480f * B);
		return reverseGamma(Math.min(Math.max(-1.2681437731f * l + 2.6093323231f * m - 0.3411344290f * s, 0f), 1f));
	}

	/**
	 * Gets the blue channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #oklab(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the blue channel value of the given encoded color
	 */
	public static float blue(final float encoded)
	{
		final int decoded = BitConversion.floatToRawIntBits(encoded);
		final float L = reverseLight((decoded & 0xff) / 255f);
		final float A = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float B = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = cube(L + 0.3963377774f * A + 0.2158037573f * B);
		final float m = cube(L - 0.1055613458f * A - 0.0638541728f * B);
		final float s = cube(L - 0.0894841775f * A - 1.2914855480f * B);
		return reverseGamma(Math.min(Math.max(-0.0041119885f * l - 0.7034763098f * m + 1.7068625689f * s, 0f), 1f));
	}

	/**
	 * Gets the alpha channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #oklab(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the alpha channel value of the given encoded color
	 */
	public static float alpha(final float encoded)
	{
		return ((BitConversion.floatToRawIntBits(encoded) & 0xfe000000) >>> 24) * 0x1.020408p-8f;
	}

	/**
	 * Gets the "chroma" or "colorfulness" of a given Oklab color. Chroma is similar to saturation in that grayscale
	 * values have 0 saturation and 0 chroma, while brighter colors have high saturation and chroma. The difference is
	 * that colors that are perceptually more-colorful have higher chroma than colors that are perceptually
	 * less-colorful, regardless of hue, whereas saturation changes its meaning depending on the hue and lightness. That
	 * is, the most saturated color for a given hue and lightness always has a saturation of 1, but if that color
	 * isn't perceptually very colorful (as is the case for very dark and very light colors), it will have a chroma that
	 * is much lower than the maximum. The result of this method can't be negative, grayscale values have very close to
	 * 0 chroma, and the most colorful values (all very close to magenta) should have 0.31613f chroma.
	 * @param encoded a color as a packed float that can be obtained by {@link #oklab(float, float, float, float)}
	 * @return a float between 0.0f and 0.31613f that represents how colorful the given value is
	 */
	public static float chroma(final float encoded) {
		final int decoded = BitConversion.floatToRawIntBits(encoded);
		final float a = ((decoded >>> 7 & 0x1FE) - 255)  * 0x1p-8f;
		final float b = ((decoded >>> 15 & 0x1FE) - 255) * 0x1p-8f;
		return (float) Math.sqrt(a * a + b * b);
	}

	/**
	 * Given a hue and lightness, this gets the (approximate) maximum chroma possible for that hue-lightness
	 * combination. This is useful to know the bounds of {@link #chroma(float)}. This should be no greater
	 * than 0.31613f .
	 * @param hue the hue, typically between 0.0f and 1.0f, to look up
	 * @param lightness the lightness, clamped between 0.0f and 1.0f, to look up
	 * @return the maximum possible chroma for the given hue and lightness, between 0.0f and 0.31613f
	 */
	public static float chromaLimit(final float hue, final float lightness){
		final int idx = (int) (Math.min(Math.max(lightness, 0f), 1f) * 255f) << 8
				| (int) (256f * (hue - ((int)(hue + 0x1p14) - 0x4000)));
		return (GAMUT_DATA[idx] + 2f) * 0x1p-8f;
	}

	/**
	 * Gets a color as an Oklab packed float given floats representing hue, saturation, lightness, and opacity.
	 * All parameters should normally be between 0 and 1 inclusive, though any hue is tolerated (precision loss may
	 * affect the color if the hue is too large). A hue of 0 is red, progressively higher hue values go to orange,
	 * yellow, green, blue, and purple before wrapping around to red as it approaches 1. A saturation of 0 is grayscale,
	 * a saturation of 1 is brightly colored, and values close to 1 will usually appear more distinct than values close
	 * to 0, especially if the hue is different. A lightness of 0.001f or less is always black (also using a shortcut if
	 * this is the case, respecting opacity), while a lightness of 1f is white. Very bright colors are mostly in a band
	 * of high-saturation where lightness is 0.5f.
	 * <br>
	 * This method considers its hue, saturation, and lightness parameters as the HSL color space does. You may instead
	 * want the method {@link #oklabByHSL(float, float, float, float)}, which treats lightness exactly as Oklab does,
	 * and handles hue and saturation with the Oklab color solid (which is shaped oddly) instead of the HSL color solid
	 * (which is a bicone, with a wide cone pointing up attached at the base to another wide cone pointing down).
	 * Using oklabByHSL() should be faster in many cases, and the lightness handling alone may be a reason to use it.
	 * There is also {@link #oklabByHCL(float, float, float, float)}, which may be preferable if you want a specific
	 * amount of chroma (colorful-ness) and no greater.
	 *
	 * @param hue        0f to 1f, color wheel position
	 * @param saturation 0f to 1f, 0f is grayscale and 1f is brightly colored
	 * @param lightness  0f to 1f, 0f is black and 1f is white
	 * @param opacity    0f to 1f, 0f is fully transparent and 1f is opaque
	 * @return a float encoding a color with the given properties
	 */
	public static float floatGetHSL(float hue, float saturation, float lightness, float opacity) {
		if (lightness <= 0.001f) {
			return BitConversion.intBitsToFloat((((int) (opacity * 255f) << 24) & 0xFE000000) | 0x7F7F00);
		} else {
			return fromRGBA(FloatColors.hsl2rgb(hue, saturation, lightness, opacity));
		}
	}

	/**
	 * Gets the saturation of the given encoded color as HSL would calculate it, as a float ranging from 0.0f to 1.0f,
	 * inclusive. This is different from {@link #chroma(float)}; see that method's documentation for details. It is also
	 * different from {@link #oklabSaturation(float)}, which gets the saturation as Oklab understands it rather than how
	 * HSL understands it.
	 *
	 * @param encoded a color as a packed float that can be obtained by {@link #oklab(float, float, float, float)}
	 * @return the saturation of the color from 0.0 (a grayscale color; inclusive) to 1.0 (a bright color, inclusive)
	 */
	public static float saturation(final float encoded) {
		final int decoded = BitConversion.floatToRawIntBits(encoded);
		final float L = (decoded & 0xff) / 255f;
		if(Math.abs(L - 0.5) > 0.495f) return 0f;
		final float A = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float B = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = cube(L + 0.3963377774f * A + 0.2158037573f * B);
		final float m = cube(L - 0.1055613458f * A - 0.0638541728f * B);
		final float s = cube(L - 0.0894841775f * A - 1.2914855480f * B);
		final float r = reverseGamma(Math.min(Math.max(+4.0767245293f * l - 3.3072168827f * m + 0.2307590544f * s, 0f), 1f));
		final float g = reverseGamma(Math.min(Math.max(-1.2681437731f * l + 2.6093323231f * m - 0.3411344290f * s, 0f), 1f));
		final float b = reverseGamma(Math.min(Math.max(-0.0041119885f * l - 0.7034763098f * m + 1.7068625689f * s, 0f), 1f));
		float x, y, w;
		if(g < b) {
			x = b;
			y = g;
		}
		else {
			x = g;
			y = b;
		}
		if(r < x) {
			w = r;
		}
		else {
			w = x;
			x = r;
		}
		return x - Math.min(w, y);
	}

	/**
	 * Defined as per HSL; normally you only need {@link #channelL(float)} to get accurate lightness for Oklab. You can
	 * also use {@link #oklabLightness(float)}, which is an alias for channelL(). This ranges from 0.0f (black) to 1.0f
	 * (white).
	 *
	 * @param encoded a packed float Oklab color
	 * @return the lightness of the given color as HSL would calculate it
	 */
	public static float lightness(final float encoded) {
		final int decoded = BitConversion.floatToRawIntBits(encoded);
		final float L = (decoded & 0xff) / 255f;
		final float A = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float B = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = cube(L + 0.3963377774f * A + 0.2158037573f * B);
		final float m = cube(L - 0.1055613458f * A - 0.0638541728f * B);
		final float s = cube(L - 0.0894841775f * A - 1.2914855480f * B);
		final float r = reverseGamma(Math.min(Math.max(+4.0767245293f * l - 3.3072168827f * m + 0.2307590544f * s, 0f), 1f));
		final float g = reverseGamma(Math.min(Math.max(-1.2681437731f * l + 2.6093323231f * m - 0.3411344290f * s, 0f), 1f));
		final float b = reverseGamma(Math.min(Math.max(-0.0041119885f * l - 0.7034763098f * m + 1.7068625689f * s, 0f), 1f));
		float x, y, w;
		if(g < b) {
			x = b;
			y = g;
		}
		else {
			x = g;
			y = b;
		}
		if(r < x) {
			w = r;
		}
		else {
			w = x;
			x = r;
		}
		float d = x - Math.min(w, y);
		return x * (1f - 0.5f * d / (x + 1e-10f));
	}

	/**
	 * Gets the hue of the given encoded color, as a float from 0f (inclusive, red and approaching orange if increased)
	 * to 1f (exclusive, red and approaching purple if decreased). You can also use {@link #oklabHue(float)}, which
	 * positions the different hues at different values, somewhat, from this, but is more accurate to how Oklab handles
	 * hues.
	 *
	 * @param encoded a color as a packed float that can be obtained by {@link #oklab(float, float, float, float)}
	 * @return The hue of the color from 0.0 (red, inclusive) towards orange, then yellow, and
	 * eventually to purple before looping back to almost the same red (1.0, exclusive)
	 */
	public static float hue(final float encoded) {
		final int decoded = BitConversion.floatToRawIntBits(encoded);
		final float L = (decoded & 0xff) / 255f;
		final float A = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float B = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = cube(L + 0.3963377774f * A + 0.2158037573f * B);
		final float m = cube(L - 0.1055613458f * A - 0.0638541728f * B);
		final float s = cube(L - 0.0894841775f * A - 1.2914855480f * B);
		final float r = reverseGamma(Math.min(Math.max(+4.0767245293f * l - 3.3072168827f * m + 0.2307590544f * s, 0f), 1f));
		final float g = reverseGamma(Math.min(Math.max(-1.2681437731f * l + 2.6093323231f * m - 0.3411344290f * s, 0f), 1f));
		final float b = reverseGamma(Math.min(Math.max(-0.0041119885f * l - 0.7034763098f * m + 1.7068625689f * s, 0f), 1f));
		float x, y, z, w;
		if(g < b) {
			x = b;
			y = g;
			z = -1f;
			w = 2f / 3f;
		}
		else {
			x = g;
			y = b;
			z = 0f;
			w = -1f / 3f;
		}
		if(r < x) {
			z = w;
			w = r;
		}
		else {
			w = x;
			x = r;
		}
		float d = x - Math.min(w, y);
		return Math.abs(z + (w - y) / (6f * d + 1e-10f));
	}

	/**
	 * The "L" channel of the given packed float in Oklab format, which is its lightness; ranges from 0.0f to
	 * 1.0f . You can edit the L of a color with {@link #lighten(float, float)} and {@link #darken(float, float)}.
	 *
	 * @param encoded a color encoded as a packed float, as by {@link #oklab(float, float, float, float)}
	 * @return the L value as a float from 0.0f to 1.0f
	 */
	public static float channelL(final float encoded)
	{
		return (BitConversion.floatToRawIntBits(encoded) & 0xff) / 255f;
	}

	/**
	 * The "A" channel of the given packed float in Oklab format, which when combined with the B channel describes the
	 * hue and saturation of a color; ranges from 0f to 1f . If A is 0f, the color will be cooler, more green or
	 * blue; if A is 1f, the color will be warmer, from magenta to orange. You can edit the A of a color with
	 * {@link #raiseA(float, float)} and {@link #lowerA(float, float)}.
	 * @param encoded a color encoded as a packed float, as by {@link #oklab(float, float, float, float)}
	 * @return the A value as a float from 0.0f to 1.0f
	 */
	public static float channelA(final float encoded)
	{
		return ((BitConversion.floatToRawIntBits(encoded) >>> 8 & 0xff)) / 255f;
	}

	/**
	 * The "B" channel of the given packed float in Oklab format, which when combined with the A channel describes the
	 * hue and saturation of a color; ranges from 0f to 1f . If B is 0f, the color will be more "artificial", more
	 * blue or purple; if B is 1f, the color will be more "natural", from green to yellow to orange. You can edit
	 * the B of a color with {@link #raiseB(float, float)} and {@link #lowerB(float, float)}.
	 * @param encoded a color encoded as a packed float, as by {@link #oklab(float, float, float, float)}
	 * @return the B value as a float from 0.0f to 1.0f
	 */
	public static float channelB(final float encoded)
	{
		return ((BitConversion.floatToRawIntBits(encoded) >>> 16 & 0xff)) / 255f;
	}

	/**
	 * Gets a variation on the packed float color basis as another packed float that has its hue, saturation, lightness,
	 * and opacity adjusted by the specified amounts. Note that this edits the color in HSL space, not Oklab! Takes
	 * floats representing the amounts of change to apply to hue, saturation, lightness, and opacity; these can be
	 * between -1f and 1f. Returns a float that can be used as a packed or encoded color. The float is
	 * likely to be different than the result of {@link #oklab(float, float, float, float)} unless hue, saturation,
	 * lightness, and opacity are all 0. This won't allocate any objects.
	 * <br>
	 * The parameters this takes all specify additive changes for a color component, clamping the final values so they
	 * can't go above 1 or below 0, with an exception for hue, which can rotate around if lower or higher hues would be
	 * used. As an example, if you give this 0.4f for saturation, and the current color has saturation 0.7f, then the
	 * resulting color will have 1f for saturation. If you gave this -0.1f for saturation and the current color again
	 * has saturation 0.7f, then resulting color will have 0.6f for saturation.
	 * <br>
	 * You may want {@link #editOklab(float, float, float, float, float, float, float, float, float)} instead, which
	 * adjusts the L, A, B, and alpha channels directly instead of converting to HSL and back.
	 *
	 * @param basis      a packed float color that will be used as the starting point to make the next color
	 * @param hue        -1f to 1f, the hue change that can be applied to the new float color (not clamped, wraps)
	 * @param saturation -1f to 1f, the saturation change that can be applied to the new float color
	 * @param light      -1f to 1f, the light/brightness change that can be applied to the new float color
	 * @param opacity    -1f to 1f, the opacity/alpha change that can be applied to the new float color
	 * @return a float encoding a variation of basis with the given changes
	 */
	public static float toEditedFloat(float basis, float hue, float saturation, float light, float opacity) {
		final int decoded = BitConversion.floatToRawIntBits(basis);
		final float L = Math.min(Math.max(light + reverseLight((decoded & 0xff) / 255f), 0f), 1f);
		opacity = Math.min(Math.max(opacity + (decoded >>> 24 & 0xfe) * 0x1.020408p-8f, 0f), 1f);
		if (L <= 0.001f)
			return BitConversion.intBitsToFloat((((int) (opacity * 255f) << 24) & 0xFE000000) | 0x808000);
		final float A = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float B = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = cube(L + 0.3963377774f * A + 0.2158037573f * B);
		final float m = cube(L - 0.1055613458f * A - 0.0638541728f * B);
		final float s = cube(L - 0.0894841775f * A - 1.2914855480f * B);
		final float r = reverseGamma(Math.min(Math.max(+4.0767245293f * l - 3.3072168827f * m + 0.2307590544f * s, 0f), 1f));
		final float g = reverseGamma(Math.min(Math.max(-1.2681437731f * l + 2.6093323231f * m - 0.3411344290f * s, 0f), 1f));
		final float b = reverseGamma(Math.min(Math.max(-0.0041119885f * l - 0.7034763098f * m + 1.7068625689f * s, 0f), 1f));
		float x, y, z, w;
		if(g < b) {
			x = b;
			y = g;
			z = -1f;
			w = 2f / 3f;
		}
		else {
			x = g;
			y = b;
			z = 0f;
			w = -1f / 3f;
		}
		if(r < x) {
			z = w;
			w = r;
		}
		else {
			w = x;
			x = r;
		}
		final float d = x - Math.min(w, y);
		final float lum = x * (1f - 0.5f * d / (x + 1e-10f));
		hue += Math.abs(z + (w - y) / (6f * d + 1e-10f)) + 1f;
		saturation += (x - lum) / (Math.min(lum, 1f - lum) + 1e-10f);
		return fromRGBA(FloatColors.hsl2rgb(hue - (int)hue, Math.min(Math.max(saturation, 0f), 1f), lum, opacity));
	}

	/**
	 * Interpolates from the packed float color start towards white by change. While change should be between 0f (return
	 * start as-is) and 1f (return white), start should be a packed color, as from
	 * {@link #oklab(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
	 * is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp towards
	 * white. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and both chroma of start as-is.
	 * @see #darken(float, float) the counterpart method that darkens a float color
	 * @param start the starting color as a packed float
	 * @param change how much to go from start toward white, as a float between 0 and 1; higher means closer to white
	 * @return a packed float that represents a color between start and white
	 */
	public static float lighten(final float start, final float change) {
		final int s = BitConversion.floatToRawIntBits(start), i = s & 0xFF, other = s & 0xFEFFFF00;
		return BitConversion.intBitsToFloat(((int) (i + (0xFF - i) * change) & 0xFF) | other);
	}

	/**
	 * Interpolates from the packed float color start towards black by change. While change should be between 0f (return
	 * start as-is) and 1f (return black), start should be a packed color, as from
	 * {@link #oklab(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
	 * is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp towards
	 * black. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and both chroma of start as-is.
	 * @see #lighten(float, float) the counterpart method that lightens a float color
	 * @param start the starting color as a packed float
	 * @param change how much to go from start toward black, as a float between 0 and 1; higher means closer to black
	 * @return a packed float that represents a color between start and black
	 */
	public static float darken(final float start, final float change) {
		final int s = BitConversion.floatToRawIntBits(start), i = s & 0xFF, other = s & 0xFEFFFF00;
		return BitConversion.intBitsToFloat(((int) (i * (1f - change)) & 0xFF) | other);
	}

	/**
	 * Interpolates from the packed float color start towards a warmer color (orange to magenta) by change. While change
	 * should be between 0f (return start as-is) and 1f (return fully warmed), start should be a packed color, as from
	 * {@link #oklab(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors,
	 * and is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to
	 * lerp towards a warmer color. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the
	 * alpha and L of start as-is.
	 * @see #lowerA(float, float) the counterpart method that cools a float color
	 * @param start the starting color as a packed float
	 * @param change how much to warm start, as a float between 0 and 1; higher means a warmer result
	 * @return a packed float that represents a color between start and a warmer color
	 */
	public static float raiseA(final float start, final float change) {
		final int s = BitConversion.floatToRawIntBits(start), p = s >>> 8 & 0xFF, other = s & 0xFEFF00FF;
		return BitConversion.intBitsToFloat(((int) (p + (0xFF - p) * change) << 8 & 0xFF00) | other);
	}

	/**
	 * Interpolates from the packed float color start towards a cooler color (green to blue) by change. While change
	 * should be between 0f (return start as-is) and 1f (return fully cooled), start should be a packed color, as from
	 * {@link #oklab(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
	 * is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp
	 * towards a cooler color. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and
	 * L of start as-is.
	 * @see #raiseA(float, float) the counterpart method that warms a float color
	 * @param start the starting color as a packed float
	 * @param change how much to cool start, as a float between 0 and 1; higher means a cooler result
	 * @return a packed float that represents a color between start and a cooler color
	 */
	public static float lowerA(final float start, final float change) {
		final int s = BitConversion.floatToRawIntBits(start), p = s >>> 8 & 0xFF, other = s & 0xFEFF00FF;
		return BitConversion.intBitsToFloat(((int) (p * (1f - change)) & 0xFF) << 8 | other);
	}

	/**
	 * Interpolates from the packed float color start towards a "natural" color (between green and orange) by change.
	 * While change should be between 0f (return start as-is) and 1f (return fully natural), start should be a packed color, as
	 * from {@link #oklab(float, float, float, float)}. This is a good way to reduce allocations of temporary
	 * Colors, and is a little more efficient and clear than using
	 * {@link FloatColors#lerpFloatColors(float, float, float)} to lerp towards a more natural color. Unlike
	 * {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and L of start as-is.
	 * @see #lowerB(float, float) the counterpart method that makes a float color less natural
	 * @param start the starting color as a packed float
	 * @param change how much to change start to a natural color, as a float between 0 and 1; higher means a more natural result
	 * @return a packed float that represents a color between start and a more natural color
	 */
	public static float raiseB(final float start, final float change) {
		final int s = BitConversion.floatToRawIntBits(start), t = s >>> 16 & 0xFF, other = s & 0xFE00FFFF;
		return BitConversion.intBitsToFloat(((int) (t + (0xFF - t) * change) << 16 & 0xFF0000) | other);
	}

	/**
	 * Interpolates from the packed float color start towards an "artificial" color (between blue and purple) by change.
	 * While change should be between 0f (return start as-is) and 1f (return fully artificial), start should be a packed color, as
	 * from {@link #oklab(float, float, float, float)}. This is a good way to reduce allocations of temporary
	 * Colors, and is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp
	 * towards a more artificial color. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the
	 * alpha and L of start as-is.
	 * @see #raiseB(float, float) the counterpart method that makes a float color less artificial
	 * @param start the starting color as a packed float
	 * @param change how much to change start to a bolder color, as a float between 0 and 1; higher means a more artificial result
	 * @return a packed float that represents a color between start and a more artificial color
	 */
	public static float lowerB(final float start, final float change) {
		final int s = BitConversion.floatToRawIntBits(start), t = s >>> 16 & 0xFF, other = s & 0xFE00FFFF;
		return BitConversion.intBitsToFloat(((int) (t * (1f - change)) & 0xFF) << 16 | other);
	}

	/**
	 * Interpolates from the packed float color start towards that color made opaque by change. While change should be
	 * between 0f (return start as-is) and 1f (return start with full alpha), start should be a packed color, as from
	 * {@link #oklab(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
	 * is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp towards
	 * transparent. This won't change the L, A, or B of the color.
	 * @see #fade(float, float) the counterpart method that makes a float color more translucent
	 * @param start the starting color as a packed float
	 * @param change how much to go from start toward opaque, as a float between 0 and 1; higher means closer to opaque
	 * @return a packed float that represents a color between start and its opaque version
	 */
	public static float blot(final float start, final float change) {
		final int s = BitConversion.floatToRawIntBits(start), opacity = s >>> 24 & 0xFE, other = s & 0x00FFFFFF;
		return BitConversion.intBitsToFloat(((int) (opacity + (0xFE - opacity) * change) & 0xFE) << 24 | other);
	}

	/**
	 * Interpolates from the packed float color start towards transparent by change. While change should be between 0
	 * (return start as-is) and 1f (return the color with 0 alpha), start should be a packed color, as from
	 * {@link #oklab(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors,
	 * and is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp towards
	 * transparent. This won't change the L, A, or B of the color.
	 * @see #blot(float, float) the counterpart method that makes a float color more opaque
	 * @param start the starting color as a packed float
	 * @param change how much to go from start toward transparent, as a float between 0 and 1; higher means closer to transparent
	 * @return a packed float that represents a color between start and transparent
	 */
	public static float fade(final float start, final float change) {
		final int s = BitConversion.floatToRawIntBits(start), opacity = s & 0xFE, other = s & 0x00FFFFFF;
		return BitConversion.intBitsToFloat(((int) (opacity * (1f - change)) & 0xFE) << 24 | other);
	}

	/**
	 * Brings the chromatic components of {@code start} closer to grayscale by {@code change} (desaturating them). While
	 * change should be between 0f (return start as-is) and 1f (return fully gray), start should be a packed color, as
	 * from {@link #oklab(float, float, float, float)}. This only changes A and B; it leaves L and alpha alone, unlike
	 * {@link #lessenChange(float, float)}, which usually changes L.
	 * @see #enrich(float, float) the counterpart method that makes a float color more saturated
	 * @param start the starting color as a packed float
	 * @param change how much to change start to a desaturated color, as a float between 0 and 1; higher means a less saturated result
	 * @return a packed float that represents a color between start and a desaturated color
	 */
	public static float dullen(final float start, final float change) {
		final int s = BitConversion.floatToRawIntBits(start);
		return oklab((s & 0xFF) / 255f,
				((s >>> 8 & 0xFF) / 255f - 0.5f) * (1f - change) + 0.5f,
				((s >>> 16 & 0xFF) / 255f - 0.5f) * (1f - change) + 0.5f,
				(s >>> 25) / 127f);
	}

	/**
	 * Pushes the chromatic components of {@code start} away from grayscale by change (saturating them). While change
	 * should be between 0f (return start as-is) and 1f (return maximally saturated), start should be a packed color, as
	 * from {@link #oklab(float, float, float, float)}. This usually changes only A and B, but higher values for
	 * {@code change} can force the color out of the gamut, which this corrects using
	 * {@link #limitToGamut(float, float, float, float)} (and that can change L somewhat). If the color stays in-gamut,
	 * then L won't change; alpha never changes.
	 * @see #dullen(float, float) the counterpart method that makes a float color less saturated
	 * @param start the starting color as a packed float
	 * @param change how much to change start to a saturated color, as a float between 0 and 1; higher means a more saturated result
	 * @return a packed float that represents a color between start and a saturated color
	 */
	public static float enrich(final float start, final float change) {
		final int s = BitConversion.floatToRawIntBits(start);
		return limitToGamut((s & 0xFF) / 255f,
				((s >>> 8 & 0xFF) / 255f - 0.5f) * (1f + change) + 0.5f,
				((s >>> 16 & 0xFF) / 255f - 0.5f) * (1f + change) + 0.5f,
				(s >>> 25) / 127f);
	}


	/**
	 * Given a packed float Oklab color {@code mainColor} and another Oklab color that it should be made to contrast with,
	 * gets a packed float Oklab color with roughly inverted L but the same chromatic channels and opacity (A and B
	 * are likely to be clamped if the result gets close to white or black). This won't ever produce black or other very
	 * dark colors, and also has a gap in the range it produces for L values between 0.5 and 0.55. That allows
	 * most of the colors this method produces to contrast well as a foreground when displayed on a background of
	 * {@code contrastingColor}, or vice versa. This will leave the L unchanged if the chromatic channels of the
	 * contrastingColor and those of the mainColor are already very different. This has nothing to do with the contrast
	 * channel of the tweak in ColorfulBatch; where that part of the tweak can make too-similar lightness values further
	 * apart by just a little, this makes a modification on {@code mainColor} to maximize its lightness difference from
	 * {@code contrastingColor} without losing its other qualities.
	 * @param mainColor a packed float color, as produced by {@link #oklab(float, float, float, float)};
	 *                     this is the color that will be adjusted
	 * @param contrastingColor a packed float color, as produced by {@link #oklab(float, float, float, float)};
	 *                            the adjusted mainColor will contrast with this
	 * @return a different Oklab packed float color, based on mainColor but with potentially very different lightness
	 */
	public static float inverseLightness(final float mainColor, final float contrastingColor)
	{
		final int bits = BitConversion.floatToRawIntBits(mainColor),
				contrastBits = BitConversion.floatToRawIntBits(contrastingColor),
				L = (bits & 0xff),
				A = (bits >>> 8 & 0xff),
				B = (bits >>> 16 & 0xff),
				cL = (contrastBits & 0xff),
				cA = (contrastBits >>> 8 & 0xff),
				cB = (contrastBits >>> 16 & 0xff);
		if((A - cA) * (A - cA) + (B - cB) * (B - cB) >= 0x10000)
			return mainColor;
		return BitConversion.intBitsToFloat((bits & 0xFEFFFF00) | (int) (cL < 128 ? L * 0.45f + 140 : 127 - L * 0.45f));
	}
	
	/**
	 * Given a packed float Oklab color {@code mainColor} and another Oklab color that it should be made to contrast
	 * with, gets a packed float Oklab color with L that should be quite different from {@code contrastingColor}'s L,
	 * but the same chromatic channels and opacity (A and B are likely to be clamped if the result gets close to white
	 * or black). This allows most of the colors this method produces to contrast well as a foreground when displayed on
	 * a background of {@code contrastingColor}, or vice versa.
	 * <br>
	 * This is similar to {@link #inverseLightness(float, float)}, but is considerably simpler, and this method will
	 * change the lightness of mainColor when the two given colors have close lightness but distant chroma. Because it
	 * averages the original L of mainColor with the modified one, this tends to not produce harsh color changes.
	 * @param mainColor a packed Oklab float color; this is the color that will be adjusted
	 * @param contrastingColor a packed Oklab float color; the adjusted mainColor will contrast with the L of this
	 * @return a different packed Oklab float color, based on mainColor but typically with different lightness
	 */
	public static float differentiateLightness(final float mainColor, final float contrastingColor)
	{
		final int main = BitConversion.floatToRawIntBits(mainColor), contrast = BitConversion.floatToRawIntBits(contrastingColor);
		return limitToGamut(BitConversion.intBitsToFloat((main & 0xFEFFFF00) | (contrast + 128 & 0xFF) + (main & 0xFF) >>> 1));
	}

	/**
	 * Pretty simple; adds 0.5 to the given color's L and wraps it around if it would go above 1.0, then averages that
	 * with the original L. This means light colors become darker, and dark colors become lighter, with almost all
	 * results in the middle-range of possible lightness.
	 * @param mainColor a packed Oklab float color
	 * @return a different packed Oklab float color, with its L channel changed and limited to the correct gamut
	 */
	public static float offsetLightness(final float mainColor) {
		final int oklab = BitConversion.floatToRawIntBits(mainColor);
		return limitToGamut(BitConversion.intBitsToFloat((oklab & 0xFEFFFF00) | (oklab + 128 & 0xFF) + (oklab & 0xFF) >>> 1));
	}

	/**
	 * Makes the additive Oklab color stored in {@code color} cause less of a change when used as a tint, as if it were
	 * mixed with neutral gray. When {@code fraction} is 1.0, this returns color unchanged; when fraction is 0.0, it
	 * returns {@link Palette#GRAY}, and when it is in-between 0.0 and 1.0 it returns something between the two. This is
	 * meant for things like area of effect abilities that make smaller color changes toward their periphery.
	 * @param color a color that should have its tinting effect potentially weakened
	 * @param fraction how much of {@code color} should be kept, from 0.0 to 1.0
	 * @return an Oklab float color between gray and {@code color}
	 */
	public static float lessenChange(final float color, float fraction) {
		final int e = BitConversion.floatToRawIntBits(color),
				sL = 0x80, sA = 0x80, sB = 0x80, sAlpha = 0xFE,
				eL = (e & 0xFF), eA = (e >>> 8) & 0xFF, eB = (e >>> 16) & 0xFF, eAlpha = e >>> 24 & 0xFE;
		return BitConversion.intBitsToFloat(((int) (sL + fraction * (eL - sL)) & 0xFF)
				| (((int) (sA + fraction * (eA - sA)) & 0xFF) << 8)
				| (((int) (sB + fraction * (eB - sB)) & 0xFF) << 16)
				| (((int) (sAlpha + fraction * (eAlpha - sAlpha)) & 0xFE) << 24));
	}

	/**
	 * Makes a quasi-randomly-edited variant on the given {@code color}, allowing typically a small amount of
	 * {@code variance} (such as 0.05 to 0.25) between the given color and what this can return. The {@code seed} should
	 * be different each time this is called, and can be obtained from a random number generator to make the colors more
	 * random, or can be incremented on each call. If the seed is only incremented or decremented, then this shouldn't
	 * produce two similar colors in a row unless variance is very small. The variance affects the L, A, and B of the
	 * generated color, and each of those channels can go up or down by the given variance as long as the total distance
	 * isn't greater than the variance (this considers A and B extra-wide, going from -1 to 1, while L goes from 0 to 1,
	 * but only internally for measuring distance).
	 * @param color a packed float color, as produced by {@link #oklab(float, float, float, float)}
	 * @param seed a long seed that should be different on each call; should not be 0
	 * @param variance max amount of difference between the given color and the generated color; always less than 1
	 * @return a generated packed float color that should be at least somewhat different from {@code color}
	 */
	public static float randomEdit(final float color, long seed, final float variance) {
		final int decoded = BitConversion.floatToRawIntBits(color);
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
			if(dist <= limit && inGamut(x += L, y = (A + y) * 0.5f + 0.5f, z = (B + z) * 0.5f + 0.5f))
				return BitConversion.intBitsToFloat((decoded & 0xFE000000) | ((int)(z * 255.5f) << 16 & 0xFF0000)
					| ((int)(y * 255.5f) << 8 & 0xFF00) | (int)(x * 255.5f));
		}
		return color;
	}


	private static byte[] GAMUT_DATA;

	static {
		try {
			GAMUT_DATA = new StringBuilder().append("kjihggggggaZUPKGD@>;975310.-,+*)(''&%%$##\"\"\"!!!    \037\037\037\037\037\037\037\036\036\036\036\036\036\036\037\037\037\037\037\037\037\037    !!!\"\"##$$%&&'()*+,-./12468:=?BFINSX^_^\\ZXWUSRPONLKJIHGGFEEDDDCCCCCCCDDEEFGHIJLMPRUQNLIGEDBA?>=<;:9988776655554444444444455566677889::;<=>?@ABDEGHJLNPRUWZ]`cfjmpsvy{}~~~}|{ywusqpnlgfedccbbbcc^XSNJGC@>;975421/.-,+*)(''&&%$$$##\"\"\"!!!!!                  !!!!\"\"\"###$$%%&''()**+,./013568:=?BEIMQV\\ceca_]\\ZXVUSRQONMLKKJIIHHHGGGGGHHHIIJKLNOQSUWSPMJHFDBA?>=<;:98776655444333333333333333444455667789::;<=>?ABCEFHJLNPRUWZ]`cfilortvxyyyxwvusqomljicba`_^^^^^^_\\VRMJFC@>;9764310/.-,+*)((''&&%%$$####\"\"\"\"\"!!!!!!!!!!!!!\"\"\"\"\"\"###$$$%%&&'(()**+,-./124579;=?BEHLPUZ`gjhfdb`^\\[YWVTSRQPONNMLLLKKKKKLLLMNNOQRTVX[VROLIGECA@>=<;:987665544333222211111111122222333445567789:;<=>?@ACDFHJKNPRTWZ\\_behkmoqrsttsrqpnmkihfd_^]\\[ZZYYYYZZZUPMIFC@><:865321/.-,,+*))((''&&%%%$$$#######\"\"\"\"\"\"\"#######$$$$%%%&&''(()*++,-./0134679;=?BEHKOSX]cknljhfdb`^][YXWVTSRRQPPPOOOOOOPPQQRSUVXZ]ZUQNKHFDB@?=<;:9876554433221111000000000000011112233345567789:;<=>@ABDFGIKMORTVY\\^acfhjlmnnnnmlkihfecb`[ZYXWVUUUTTUUUVTOLHEC@><:8754310/.-,,+**))((''&&&%%%%$$$$$$$$$$$$$$$$$%%%%&&&'''(())*++,-./01235689;=?BDGJNRV[agosqoljhfdb`^][ZYXWVUTTSSSRRSSSTTUVWYZ\\^`ZUQNKHFCB@>=;:98765543322111000///////////////000111223344567789:;<>?@BCEGIJLOQSUXZ]_aceghiiiihgfecb`_]\\VUTSSRQQPPPPPPQQROKHEC@><:97643210/.--,++*)))(('''&&&&&%%%%%%%%%%%%%&&&&&'''((())**+,,-./00234578:;=?BDGJMQUY^dkrvtrpnkigeca`^][ZYXXWVVVVVVVVWWXYZ\\^`ba[VQNKHECA@><;:98765433221100////...............////001112334456789:;<=>@ACDFHJLNPRTVY[]^`abcddccba`^]\\ZYXRQPONNMMLLKKKKLLMMNKHEC@><;986543210/.--,++***))((((''''''&&&&&&&&''''''((()))**++,,-.//01234678:<=?BDFILPSX\\agnvywuspnljhfdba_^]\\[ZYYYXXXYYYZ[\\]_acec\\WRNKHFDA@><;:8765543221100//....---------------....///00112234556789:;<>?@BDEGIKLNPRTVXZ[\\]^^^^]]\\[YXWVTSNMLKJJIHHHGGGGGGHHIIJGEC@>=;9875432100/..-,,++***))))(((((((((((((((((()))***++,,--.//012345679:<>?ADFILORVZ_ekrz|zwuspnligfdba`_^]\\\\[[[[[[\\\\]^`acehf^XTPLIGDB@>=;:976544321100//...----,,,,,,,,,,,,,,----...//00112234556789;<=>@ACDFGIKMOPRTUVWXYYYYXWWVUSRQPOJIHGGFEEDDCCCCCCCCDDEFGECA?=;:9765432100/..--,,+++****)))))))))))))))****+++,,,--.//0122345789;<>?ACFHKNQUY]bgnu}~{ywtromkigedba`_^]]]]]]]]^_`acegjja[VQNKHECA?=<:98754432110//...---,,,,+++++++++++++,,,,---...//011223456789:;<=?@BCEFHIKLNOPQRSSTTSSRRQPONMLKFEDCCBAA@@@?????????@@ABCCA?=<:98765432100//..--,,,+++++************++++,,,---..//01123456789;<>?ACEHJMPSW[`ejqx~|zwurpmkigedca`__^^^^^^__`acdfhknf_YTPLIGDB@><;98765432110//..---,,,++++++********++++++,,,---..//011233456789:;<>?@BCDFGIJKLMNNOOOONNMLKJJIHGBAA@??>===<<<;;;;;;;<<<=>>?A?=<;987654332110///..---,,,,,,++++++++,,,,,----..//001123455679:;<>@ACEGJLORVY^bgmt{~|yvtqoljhfecba`__^^^^^__`bcegikolc]WSOKHFCA?=<:9865432210//..--,,,++++*****************++++,,,--..//011233456789:;<>?@ACDEFGHIIJJJJJIIHHGFEEDC?>==<;;::9998888888888899::;<=><;:9876543321100///...--------,---------...///0012233456789:;=>@ACEGILNQTX\\`ejpv~~|ywtromjhfdca`_^^]]]]]^_`abdfhknrib[VRNKHECA?=;:9765432110//.--,,,+++*****))))))))))))*****+++,,,--..//011234456789:;<=>?@ABCDEEEFFFFEEDDCBBA@?;;:9988776665555555555556667889:<;:987655433221100////................///0001122334566789:<=>@ACEGIKMPSVZ^bglry{ywurpmkhfdb`_^]\\[[[[[[\\]^_`bdgjmqrha[VRNKHECA?=;:8765432100/..--,,+++****))))))))))))))))))****+++,,--..//011234456789:;<==>?@@AABBBBAAA@@??>==<88766555443333222222222223334456789::987655433221110000//////////////000011122334556789:;<=>@ACDFHJMORUX\\`dinttsrpmkheca_][ZYXWVVVVVVWXY[\\^adgjnspg`ZVQNKHECA?=;:876543210//..-,,,++***))))))((((((((((()))))****+++,,--..//0112344567789:;;<===>>>>>>===<<;;:995544332221110000000//00000011122345678987765544332221111000000000000111122233445567789:;<=>@ACDFHJLNQTWZ^bfjjihfdb`][XVTRQPONMMMMMMNOPQSUXZ^aeintpg`[VRNKHECA?=;:876543210//.--,,+++***))))(((((((((((((((()))))***+++,,--..//011233455677899:::;;;;;:::9998776632211100////.....-------.....//001123456887765544333222211111111111122223334455667899:;<=>@ABDFGIKNPSUX\\^]\\[ZXVSQNLJHFDCBA@???>>??@ABCEHJNRV[agmtpha[VROKHFCA?=<:9765432100/.--,,+++**))))(((((((((((((((((((())))***+++,,--..//00122334556667778888877766655443000//...----,,,,,,,++++,,,,,,---..//01124568776555444333322222222223333344455667889:;;<=>@ABDEGIKMOQTWUTRPMKHFDA?=;:876543322211112233568:>DLV_irqhb\\WSOLIFDB@><;9875432100/..-,,+++**))))((((((''''''''''(((((())))***+++,,--..//001122333444555555554443332211...---,,,++++***************++++,,--../0013457776655544443333333333444445556677899:;<=>?@ABDEGHJLNPSPNLIFCA><9764210/.-,++**))))((())))**+,-/27Ymric]XTPMJGEC@?=;:876543210/..--,,++***)))(((((''''''''''''''(((((()))***+++,,,--..//0001112222333332222111000//-,,,+++****)))))))(((((())))))****++,,--./01235777666555544444444445555566677889::;<=>?@ABCEFHIKMOOLIFC@=:75310.-+*)(('&&%%$$#############$$%&&()skd^YUQNKHFCA?=<:9765432100/.--,,++***)))(((((''''''''''''''''(((((()))****++,,,---..////00000111100000///...--++***))))((((((''''''''''''((((()))**++,,-./01235777766665555555555566667778899:;;<=>?@ABCDFGIKLNLIEB>;8631/-,*)('&%$##\"!!!  \037\037\037\037\037\037\036\036\036\036\036\037\037\037\037\037  !!\"lf`[VSOLIGDB@>=;9876532210/..--,,++**))))((((''''''''''''''''''(((((())))***+++,,,----....////////....----,,,+))))((((''''''&&&&&&&&&&&&&'''''((()))**+,,-./0124688777766666666666777788899::;<<=>?@ABCDEGHJLMKGC@<8530.,*)'&%$#\"!! \037\037\036\036\036\035\035\035\034\034\034\034\034\033\033\033\033\033\033\033\033\034\034\034\034\034\035\035\036ga\\XTPMJHECA?=<:9765432100/..-,,++***)))((((('''''''''''''''''''(((((())))****+++,,,,,-------------,,,,+++***((('''''&&&&&&%%%%%%%%%%%%%&&&&&'''((())**+,--./123578887777777777777888899::;;<<=>?@@ACDEFHIKMKGC>:741.,*('%$#\"! \037\037\036\036\035\034\034\034\033\033\033\032\032\032\032\031\031\031\031\031\031\031\031\031\031\031\031\031\031\032\032\032\032\033c^YURNLIFDB@>=;:876543210//.--,,++***)))(((('''''''''''''''''''''(((((())))*****+++++,,,,,,,,,,+++++****)))('''&&&&&%%%%%%%$$$$$$$$$$%%%%%%&&&&''((())*++,-./023579988888888888888999:::;;<==>??@ABCEFGHJLMHC>:630-+)'&$#\"! \037\036\035\035\034\034\033\033\032\032\031\031\031\031\030\030\030\030\030\027\027\027\027\027\027\027\027\027\027\027\027\030\030\030\030\030\031\031\031WSPMJHECA?=<:9875432110/..--,,++***)))((((''''''''''''''''''''''(((((()))))*******++++++++******))))(((('&&&&%%%%%$$$$$$$$$$$$$$$$$$$$%%%%%&&&''(())*++,-./023579999988888899999:::;;<<==>??@ABCDEFHIKLJD?:63/-*(&%#\"! \037\036\035\034\034\033\033\032\032\031\031\030\030\030\027\027\027\027\027\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\027\027\027\027\030\030\030NKIFDB@>=;:9765432100/..--,,++**))))(((('''''''''''''''''''''''((((((()))))))**********))))))((((''''&%%%%%$$$$$$###############$$$$$$%%%&&&''(())*++,-./023579:9999999999::::;;;<<==>??@ABCDEFGHJKMGA<730-*(&$#! \037\036\035\034\034\033\032\032\031\031\030\030\030\027\027\027\026\026\026\026\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\026\026\026\026\027\027\027\030\030\031CA@><;98765432100/..-,,+++***)))((((('''''''''''''''''''''''((((((((()))))))))))))(((((('''''&&&&%$$$$$$######################$$$$$%%%&&''(())*+,--.012468:::::::::::::;;;<<<==>>?@AABCDEFHIJLJD>951-+(&$#! \037\036\035\034\033\032\032\031\031\030\030\027\027\027\026\026\026\025\025\025\025\025\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\025\025\025\025\025\026\026\026\027\027\030\030\031?=<:9876443210//..-,,,++***)))(((((''''''''''''''''''''''''(((((((((((((((((((((('''''&&&&&%%%$$$$#######\"\"\"\"\"\"\"\"\"\"\"\"\"\"######$$$$%%%&&''(()**+,-./023579;;:::::::;;;;;<<<==>>??@ABCCDEGHIJLHB<72/,)'%#! \037\036\035\034\033\032\032\031\030\030\027\027\026\026\026\025\025\025\025\024\024\024\024\024\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\024\024\024\024\024\024\025\025\025\026\026\026\027\027\030\031\031\032:9765432210//..-,,,++***))))((((('''''''''''''''''''''''''(((((((((((((('''''''&&&&&%%%%%$$#######\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"#####$$$$%%&&&'(())*+,-./012468:;;;;;;;;;;;;<<<==>>??@@ABCDEFGHIJLF?940-*'%#\" \037\036\035\034\033\032\031\031\030\030\027\027\026\026\025\025\025\024\024\024\024\024\023\023\023\023\023\023\023\022\022\022\022\022\022\022\022\022\022\022\023\023\023\023\023\023\023\023\024\024\024\024\025\025\025\026\026\027\027\030\031\031\0328765432210//..--,,++***))))(((((('''''''''''''''''''''''''''''''''''''''''&&&&&%%%%%$$$$#####\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"#####$$$%%%&&''())*++,-./124579;;;;;;;;;;<<<<===>>??@@ABCDDEFHIJKD=73/+)&$\"!\037\036\035\034\033\032\031\031\030\027\027\026\026\026\025\025\025\024\024\024\023\023\023\023\023\023\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\023\023\023\023\023\024\024\024\025\025\025\026\026\027\027\030\031\032\033765432210//..--,,+++***))))((((((''''''''''''''''''''''''''''''''''''&&&&&&%%%%%$$$$$####\"\"\"\"\"\"\"\"\"\"!!!!!!!!\"\"\"\"\"\"\"\"####$$$%%%&&''(()**+,-./023579;;;;;;;;;;<<<<===>>??@@ABBCDEFGHIJB;61-*(%#! \037\035\034\033\032\032\031\030\030\027\026\026\026\025\025\024\024\024\023\023\023\023\023\022\022\022\022\022\022\022\022\021\021\021\021\021\021\021\021\021\021\022\022\022\022\022\022\022\022\022\023\023\023\023\024\024\024\025\025\026\026\027\027\030\031\032\033\03465432110//..--,,+++***)))))(((((((''''''''''''''''''''''''''''''&&&&&&&%%%%%$$$$$#####\"\"\"\"\"\"\"\"!!!!!!!!!!!!\"\"\"\"\"\"\"#####$$$%%&&''(()**+,-./023568;<;;;;;;;;<<<<<===>>??@@ABBCDEFGHIA:50,)'$\"!\037\036\035\034\033\032\031\030\030\027\027\026\026\025\025\024\024\024\023\023\023\023\022\022\022\022\022\022\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\022\022\022\022\022\022\023\023\023\023\024\024\024\025\025\026\026\027\030\031\031\032\034\0355432110//..--,,,++****)))))(((((((('''''''''''''''''''''''''&&&&&&&%%%%%%$$$$$#####\"\"\"\"\"\"\"!!!!!!!!!!!!!!!\"\"\"\"\"\"\"####$$$%%&&''(()**+,-./023568:;;;;;;;;;;;;<<<<===>>??@@AABCCDDE?93/+(&$\" \037\035\034\033\032\031\031\030\027\027\026\026\025\025\024\024\024\023\023\023\023\022\022\022\022\022\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\022\022\022\022\022\023\023\023\023\024\024\024\025\025\026\026\027\030\031\031\032\034\03554321100/..--,,,+++****)))))(((((((((''''''''''''''''''''&&&&&&&&%%%%%%$$$$$#####\"\"\"\"\"\"\"\"!!!!!!!!!!!!!!!\"\"\"\"\"\"####$$$%%%&&''(()*++,-./023568:;;;::::::::::;;;;;<<<<=======<91/..-.+(%#! \036\035\034\033\032\031\030\030\027\026\026\025\025\025\024\024\023\023\023\023\022\022\022\022\022\021\021\021\021\021\021\021\021\021\021\021\021\020\020\021\021\021\021\021\021\021\021\021\021\021\021\022\022\022\022\022\023\023\023\024\024\024\025\025\026\026\027\030\031\032\033\034\03654322100//..--,,,+++****)))))(((((((((((''''''''''''''''&&&&&&&%%%%%%$$$$$######\"\"\"\"\"\"\"!!!!!!!!!!!!!!!!\"\"\"\"\"\"####$$$%%%&&''())*++,-./123568::::999999888888887765'&%%%%%%%&&&''(()'%#!\037\036\035\034\033\032\031\030\027\027\026\026\025\025\024\024\024\023\023\023\022\022\022\022\022\021\021\021\021\021\021\021\021\020\020\020\020\020\020\020\020\020\020\020\020\021\021\021\021\021\021\021\021\022\022\022\022\022\023\023\023\024\024\024\025\025\026\027\027\030\031\032\033\035\03654322100//..--,,,+++*****))))))((((((((((('''''''''''''&&&&&&&%%%%%$$$$$$#####\"\"\"\"\"\"\"\"!!!!!!!!!!!!!!!\"\"\"\"\"\"\"####$$$%%&&&'(())*+,--.012356888877665431               !!!!\"\"##$$%%&'$\"!\037\036\034\033\032\031\031\030\027\027\026\026\025\025\024\024\023\023\023\023\022\022\022\022\021\021\021\021\021\021\021\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\021\021\021\021\021\021\021\022\022\022\022\022\023\023\023\024\024\025\025\026\026\027\030\030\031\032\034\035\03754322100//..---,,,+++*****)))))))(((((((((((''''''''''&&&&&&&%%%%%$$$$$######\"\"\"\"\"\"\"\"\"!!!!!!!!!!!!!\"\"\"\"\"\"\"####$$$%%%&&''(()**+,-./012453\036\036\036\036\035\035\035\035\035\035\034\034\034\034\034\034\034\034\035\035\035\035\035\035\035\036\036\036\037\037\037  !!\"\"#$$%$\" \037\035\034\033\032\031\030\030\027\026\026\025\025\024\024\024\023\023\023\022\022\022\022\021\021\021\021\021\021\021\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\021\021\021\021\021\021\021\022\022\022\022\023\023\023\024\024\024\025\025\026\027\027\030\031\032\033\034\036 54322110//...--,,,++++*****))))))))(((((((((('''''''''&&&&&&%%%%%$$$$$######\"\"\"\"\"\"\"\"\"\"!!!!!!!!!!!\"\"\"\"\"\"\"#####$$$%%%&&%$#\"!! \037\037\036\036\035\035\035\034\034\034\034\033\033\033\033\033\033\033\033\033\032\032\032\032\032\033\033\033\033\033\033\033\033\034\034\034\034\034\035\035\035\036\036\037\037  !!\"#$$\" \037\035\034\033\032\031\030\030\027\026\026\025\025\024\024\024\023\023\023\022\022\022\022\021\021\021\021\021\021\021\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\021\021\021\021\021\021\021\022\022\022\022\023\023\023\024\024\025\025\026\026\027\030\031\031\033\034\035\037\"543221100//..---,,,++++******)))))))(((((((((('''''''&&&&&&%%%%%$$$$$$######\"\"\"\"\"\"\"\"\"\"\"!!!!!!\"\"\"\"\"\"\"\"\"#####$$$%%$#\"!! \037\037\036\036\035\035\034\034\034\033\033\033\033\032\032\032\032\032\032\032\031\031\031\031\031\031\031\031\031\031\031\031\031\032\032\032\032\032\032\033\033\033\033\034\034\034\035\035\036\036\037\037  !\"#$\" \037\035\034\033\032\031\030\030\027\026\026\025\025\024\024\024\023\023\023\022\022\022\022\021\021\021\021\021\021\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\021\021\021\021\021\021\022\022\022\022\023\023\023\024\024\025\025\026\026\027\027\030\031\032\033\035\037!5543321100//...---,,,+++++*****))))))))(((((((('''''''&&&&&&%%%%%$$$$$#######\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"#####$$$$#\"\"! \037\037\036\036\035\035\034\034\033\033\033\032\032\032\032\032\031\031\031\031\031\031\031\030\030\030\030\030\030\030\030\030\030\030\031\031\031\031\031\031\031\032\032\032\032\033\033\033\034\034\034\035\035\036\036\037  !\"#\" \036\035\034\033\032\031\030\030\027\026\026\025\025\024\024\024\023\023\023\022\022\022\022\021\021\021\021\021\021\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\021\021\021\021\021\021\022\022\022\022\023\023\023\024\024\024\025\025\026\027\027\030\031\032\033\034\036 #5543322100///..----,,,+++++******))))))))(((((((''''''&&&&&%%%%%$$$$$$#######\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"#####$$$$#\"! \037\037\036\035\035\034\034\034\033\033\032\032\032\032\031\031\031\031\031\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\031\031\031\031\031\032\032\032\033\033\033\034\034\035\035\036\036\037 !!\"\" \036\035\034\033\032\031\030\030\027\026\026\025\025\024\024\023\023\023\023\022\022\022\022\021\021\021\021\021\021\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\021\021\021\021\021\021\021\022\022\022\023\023\023\024\024\024\025\025\026\026\027\030\031\032\033\034\036\037\"%65433221100//...---,,,,+++++*******)))))))((((((''''''&&&&&%%%%%$$$$$$########\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"######$$$#\"!  \037\036\036\035\035\034\034\033\033\032\032\032\031\031\031\031\030\030\030\030\030\030\030\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\030\030\030\030\030\030\031\031\031\031\032\032\032\033\033\033\034\034\035\035\036\037\037 !\"\" \036\035\034\033\032\031\030\030\027\026\026\025\025\024\024\024\023\023\023\022\022\022\022\021\021\021\021\021\021\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\021\021\021\021\021\021\021\022\022\022\022\023\023\023\024\024\025\025\026\026\027\030\031\032\033\034\035\037!$665443221100///...---,,,,,+++++******)))))))(((((('''''&&&&&%%%%%$$$$$$#########\"\"\"\"\"\"\"\"\"\"\"\"\"\"#######$$$#\"!  \037\036\036\035\034\034\033\033\033\032\032\032\031\031\031\030\030\030\030\030\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\030\030\030\030\030\031\031\031\032\032\032\033\033\033\034\034\035\036\036\037  !\" \037\035\034\033\032\031\030\030\027\026\026\025\025\024\024\024\023\023\023\022\022\022\022\021\021\021\021\021\021\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\021\021\021\021\021\021\021\022\022\022\022\023\023\023\024\024\025\025\026\026\027\030\031\031\032\034\035\037!#76654433211100///...---,,,,,+++++*******))))))((((('''''&&&&&%%%%%$$$$$$############\"\"\"\"\"\"#########$$$$#\"!! \037\036\036\035\034\034\033\033\033\032\032\031\031\031\031\030\030\030\030\027\027\027\027\027\027\027\027\027\026\026\026\026\026\026\026\026\027\027\027\027\027\027\027\027\030\030\030\030\030\031\031\031\032\032\032\033\033\034\034\035\035\036\037\037 !\" \037\035\034\033\032\031\030\030\027\026\026\025\025\024\024\024\023\023\023\022\022\022\022\021\021\021\021\021\021\021\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\021\021\021\021\021\021\022\022\022\022\023\023\023\023\024\024\025\025\026\026\027\030\030\031\032\034\035\036 #&766544332211000//....----,,,,++++++******)))))((((((''''&&&&&%%%%%$$$$$$#$#######################$$$$$#\"! \037\037\036\035\035\034\034\033\033\032\032\031\031\031\030\030\030\030\030\027\027\027\027\027\027\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\027\027\027\027\027\027\030\030\030\030\030\031\031\031\032\032\033\033\034\034\035\035\036\036\037 !\" \037\035\034\033\032\031\030\030\027\026\026\025\025\024\024\024\023\023\023\022\022\022\022\021\021\021\021\021\021\021\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\021\021\021\021\021\021\022\022\022\022\023\023\023\024\024\024\025\025\026\026\027\030\030\031\032\033\035\036 \"&8776554332211100///....----,,,,,+++++******)))))((((('''''&&&&%%%%%%$$$$$$$$##################$$$$$$%#\"!  \037\036\035\035\034\034\033\033\032\032\032\031\031\031\030\030\030\030\027\027\027\027\027\027\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\027\027\027\027\027\027\030\030\030\030\031\031\031\032\032\032\033\033\034\034\035\036\036\037 !\" \037\035\034\033\032\031\031\030\027\027\026\025\025\025\024\024\023\023\023\023\022\022\022\022\021\021\021\021\021\021\021\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\021\021\021\021\021\021\021\022\022\022\022\023\023\023\024\024\024\025\025\026\026\027\030\030\031\032\033\035\036 \"%987765544332211000///....----,,,,,+++++******)))))(((('''''&&&&&%%%%%$$$$$$$$$$#############$$$$$$$%$#\"! \037\037\036\035\035\034\034\033\033\032\032\031\031\031\030\030\030\030\027\027\027\027\027\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\027\027\027\027\027\027\030\030\030\031\031\031\032\032\032\033\033\034\034\035\036\036\037 !\" \037\036\034\033\032\031\031\030\027\027\026\026\025\025\024\024\024\023\023\023\022\022\022\022\021\021\021\021\021\021\021\021\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\021\021\021\021\021\021\021\021\022\022\022\022\023\023\023\024\024\024\025\025\026\026\027\030\031\031\032\033\035\036 \"%)9877655443322111000///....----,,,,,+++++*****)))))(((((''''&&&&&%%%%%%$$$$$$$$$$$$$$$$$$$$$$$$$$%%%$#\"! \037\036\036\035\034\034\033\033\032\032\032\031\031\031\030\030\030\027\027\027\027\027\027\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\027\027\027\027\027\030\030\030\030\031\031\031\032\032\033\033\034\034\035\035\036\037 !!!\037\036\035\033\033\032\031\030\027\027\026\026\025\025\024\024\024\023\023\023\022\022\022\022\022\021\021\021\021\021\021\021\021\020\020\020\020\020\020\020\020\020\020\020\020\020\020\021\021\021\021\021\021\021\021\022\022\022\022\022\023\023\023\024\024\024\025\025\026\026\027\030\031\031\032\033\035\036 \"$(:98776654433222111000///....-----,,,,+++++*****)))))(((('''''&&&&%%%%%%$%%$$$$$$$$$$$$$$$$$$$$%%%%%$#\"! \037\036\036\035\034\034\033\033\032\032\031\031\031\030\030\030\030\027\027\027\027\027\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\027\027\027\027\027\030\030\030\030\031\031\031\032\032\033\033\034\034\035\035\036\037 !!!\037\036\035\034\033\032\031\030\030\027\026\026\025\025\024\024\024\023\023\023\023\022\022\022\022\021\021\021\021\021\021\021\021\021\020\020\020\020\020\020\020\020\020\020\020\020\021\021\021\021\021\021\021\021\021\022\022\022\022\023\023\023\023\024\024\025\025\025\026\027\027\030\031\031\032\033\035\036 \"$(::98876655443322111000////....-----,,,,,++++*****))))(((('''''&&&&&%%%%%%%%%%$$$$$$$$$$$$$$%%%%%%%%$\"!! \037\036\035\035\034\034\033\033\032\032\031\031\031\030\030\030\030\027\027\027\027\027\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\027\027\027\027\027\030\030\030\030\031\031\031\032\032\033\033\034\034\035\035\036\037 !\"!\037\036\035\034\033\032\031\030\030\027\027\026\025\025\025\024\024\024\023\023\023\022\022\022\022\022\021\021\021\021\021\021\021\021\021\021\020\020\020\020\020\020\020\020\021\021\021\021\021\021\021\021\021\021\022\022\022\022\022\023\023\023\024\024\024\025\025\026\026\027\027\030\031\032\032\034\035\036 \"$';;:988766554433322111000////....-----,,,,+++++****)))))((((''''&&&&&&%%%%%%%%%%%%%$$$%%%%%%%%%%%&&%#\"!  \037\036\035\035\034\034\033\033\032\032\031\031\031\030\030\030\030\027\027\027\027\027\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\027\027\027\027\027\030\030\030\030\031\031\031\032\032\033\033\034\034\035\036\036\037 !\"! \036\035\034\033\032\031\031\030\027\027\026\026\025\025\024\024\024\023\023\023\023\022\022\022\022\022\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\022\022\022\022\022\023\023\023\023\024\024\024\025\025\026\026\027\027\030\031\032\033\034\035\036 \"$',;;:9887765544433222111000////....-----,,,,+++++****))))(((('''''&&&&&&%&%%%%%%%%%%%%%%%%%%%%%&&&&%#\"!  \037\036\035\035\034\034\033\033\032\032\031\031\031\030\030\030\030\027\027\027\027\027\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\027\027\027\027\027\030\030\030\030\031\031\031\032\032\033\033\034\034\035\036\036\037 !\"! \036\035\034\033\032\031\031\030\027\027\026\026\025\025\024\024\024\023\023\023\023\022\022\022\022\022\022\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\022\022\022\022\022\023\023\023\023\024\024\024\025\025\026\026\027\030\030\031\032\033\034\035\036 \"$',<;;:99877665544333222111000////.....----,,,,++++****)))))(((('''''&&&&&&&&&%%%%%%%%%%%%%%%&&&&&&&%#\"!  \037\036\035\035\034\034\033\033\032\032\031\031\031\030\030\030\030\027\027\027\027\027\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\027\027\027\027\027\027\030\030\030\031\031\031\032\032\032\033\033\034\034\035\036\036\037 !\"\" \037\035\034\033\032\032\031\030\030\027\026\026\025\025\025\024\024\024\023\023\023\023\022\022\022\022\022\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\022\022\022\022\022\023\023\023\023\024\024\024\025\025\025\026\026\027\030\030\031\032\033\034\035\036 \"$'+=<;;:998776655444332221111000////.....----,,,,++++****))))((((('''''&&&&&&&&&&&&&&&&&&&&&&&&&&''&%#\"!  \037\036\035\035\034\034\033\033\032\032\031\031\031\030\030\030\030\027\027\027\027\027\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\027\027\027\027\027\030\030\030\030\031\031\031\032\032\032\033\033\034\035\035\036\036\037 !\"\" \037\036\035\034\033\032\031\030\030\027\027\026\026\025\025\024\024\024\023\023\023\023\022\022\022\022\022\022\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\022\022\022\022\022\022\023\023\023\023\024\024\024\025\025\026\026\027\027\030\030\031\032\033\034\035\037 \"$'+>=<;;:9988776655443332221111000////....----,,,,++++****)))))((((''''''&'&&&&&&&&&&&&&&&&&&&&''''&%#\"!! \037\036\035\035\034\034\033\033\032\032\031\031\031\030\030\030\030\027\027\027\027\027\027\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\027\027\027\027\027\027\030\030\030\030\031\031\031\032\032\033\033\034\034\035\035\036\037\037 !\"\"!\037\036\035\034\033\032\031\031\030\027\027\026\026\025\025\025\024\024\024\023\023\023\023\022\022\022\022\022\022\022\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\022\022\022\022\022\022\023\023\023\023\024\024\024\025\025\025\026\026\027\027\030\031\031\032\033\034\035\037 \"$'+?>=<;;:99887766554443332221111000////....----,,,,++++****))))(((((''''''''''&&&&&&&&&&&&&'''''''&%$\"!! \037\036\036\035\034\034\033\033\032\032\032\031\031\031\030\030\030\030\027\027\027\027\027\027\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\027\027\027\027\027\027\030\030\030\030\031\031\031\032\032\033\033\034\034\035\035\036\037  !\"\"!\037\036\035\034\033\032\031\031\030\030\027\026\026\026\025\025\024\024\024\023\023\023\023\023\022\022\022\022\022\022\022\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\022\022\022\022\022\022\022\022\023\023\023\023\024\024\024\025\025\025\026\026\027\027\030\031\032\032\033\034\035\037 \"$'+1?>=<<;::98877666554443332221111000////....----,,,,+++****)))))((((('''''''''''''''''''''''''(('&%$#\"! \037\036\036\035\034\034\033\033\032\032\032\031\031\031\030\030\030\030\027\027\027\027\027\027\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\027\027\027\027\027\027\030\030\030\030\031\031\031\032\032\032\033\033\034\034\035\036\036\037  !\"#! \036\035\034\033\032\032\031\030\030\027\027\026\026\025\025\025\024\024\024\023\023\023\023\023\022\022\022\022\022\022\022\022\021\021\021\021\021\021\021\021\021\021\021\021\021\022\022\022\022\022\022\022\022\023\023\023\023\024\024\024\024\025\025\026\026\027\027\030\030\031\032\033\033\035\036\037!\"$'+1??>=<<;::998877665554433322221110000////....---,,,,++++****)))))((((('((''''''''''''''''''(((((&%$#\"! \037\036\036\035\035\034\033\033\033\032\032\031\031\031\030\030\030\030\030\027\027\027\027\027\027\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\027\027\027\027\027\027\027\030\030\030\030\031\031\031\032\032\032\033\033\034\034\035\036\036\037 !\"##! \037\036\034\034\033\032\031\030\030\027\027\026\026\025\025\025\024\024\024\024\023\023\023\023\022\022\022\022\022\022\022\022\022\022\021\021\021\021\021\021\021\021\021\022\022\022\022\022\022\022\022\022\023\023\023\023\023\024\024\024\025\025\025\026\026\027\027\030\030\031\032\033\034\035\036\037!\"%'+0@??>=<<;::9988776665544433322221110000////...----,,,,++++****)))))((((((((((''''''''''((((((()(&%$#\"! \037\037\036\035\035\034\034\033\033\032\032\032\031\031\031\030\030\030\030\027\027\027\027\027\027\027\026\026\026\026\026\026\026\026\026\026\026\026\026\027\027\027\027\027\027\027\030\030\030\030\031\031\031\031\032\032\033\033\034\034\035\035\036\037\037 !\"##\" \037\036\035\034\033\032\031\031\030\030\027\027\026\026\025\025\025\024\024\024\023\023\023\023\023\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\023\023\023\023\023\024\024\024\024\025\025\026\026\026\027\027\030\031\031\032\033\034\035\036\037!#%'+0A@??>=<<;;:99887776655544433322221110000///....----,,,++++*****)))))((((((((((((((((((((((()))('%$#\"! \037\037\036\035\035\034\034\033\033\032\032\032\031\031\031\030\030\030\030\030\027\027\027\027\027\027\027\027\026\026\026\026\026\026\026\026\026\027\027\027\027\027\027\027\027\030\030\030\030\030\031\031\031\032\032\032\033\033\034\034\035\035\036\037\037 !\"##\" \037\036\035\034\033\032\032\031\030\030\027\027\026\026\025\025\025\024\024\024\024\023\023\023\023\023\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\023\023\023\023\023\024\024\024\024\025\025\025\026\026\027\027\030\030\031\032\032\033\034\035\036 !#%(+0BA@??>=<<;;::9988776665554443332221111000////...----,,,,++++****)))))))))((((((((((((((())))))('%$#\"!  \037\036\036\035\034\034\033\033\033\032\032\031\031\031\031\030\030\030\030\027\027\027\027\027\027\027\027\027\027\027\026\026\026\027\027\027\027\027\027\027\027\027\027\027\030\030\030\030\031\031\031\031\032\032\033\033\033\034\034\035\036\036\037  !\"#$\"!\037\036\035\034\033\033\032\031\030\030\027\027\026\026\026\025\025\025\024\024\024\024\023\023\023\023\023\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\023\023\023\023\023\023\024\024\024\024\025\025\025\026\026\027\027\030\030\031\032\032\033\034\035\036 !#%(+0CBA@??>==<;;::99887776655544433332221111000////...----,,,+++++****))))))))))))))(())))))))))**('&$#\"!! \037\036\036\035\035\034\034\033\033\032\032\032\031\031\031\030\030\030\030\030\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\030\030\030\030\030\031\031\031\032\032\032\033\033\034\034\035\035\036\036\037 !!\"#$\"! \036\035\034\034\033\032\031\031\030\030\027\027\026\026\025\025\025\024\024\024\024\023\023\023\023\023\023\023\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\023\023\023\023\023\023\024\024\024\024\025\025\025\026\026\026\027\027\030\031\031\032\033\034\034\035\037 \"#%(+0CCBA@??>==<;;::998887766655544433322221110000///....---,,,,++++******)*)))))))))))))))))))****)'&%$#\"! \037\037\036\035\035\034\034\033\033\032\032\032\031\031\031\031\030\030\030\030\030\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\030\030\030\030\030\031\031\031\031\032\032\032\033\033\034\034\035\035\036\037\037 !\"#$$#! \037\036\035\034\033\032\032\031\030\030\027\027\026\026\026\025\025\025\024\024\024\024\023\023\023\023\023\023\023\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\023\023\023\023\023\023\023\024\024\024\024\025\025\025\025\026\026\027\027\030\030\031\031\032\033\034\035\036\037 \"#&(+0DCBBA@??>==<<;;::99887776665554443332221111000////...----,,,,++++**********)))))))))))********)'&%$#\"! \037\037\036\035\035\034\034\033\033\033\032\032\032\031\031\031\030\030\030\030\030\030\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\030\030\030\030\030\031\031\031\031\032\032\032\033\033\033\034\034\035\036\036\037\037 !\"#$%#\" \037\036\035\034\033\032\032\031\031\030\027\027\027\026\026\025\025\025\025\024\024\024\024\023\023\023\023\023\023\023\023\022\022\022\022\022\022\022\022\022\022\022\022\022\023\023\023\023\023\023\023\024\024\024\024\024\025\025\025\026\026\026\027\027\030\030\031\032\032\033\034\035\036\037 \"$&(,0EDCBBA@??>==<<;;::998887766655544443332221110000///....---,,,,+++++************************+++)(&%$#\"!  \037\036\036\035\035\034\034\033\033\032\032\032\031\031\031\031\030\030\030\030\030\030\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\030\030\030\030\030\030\031\031\031\031\032\032\032\033\033\034\034\035\035\036\036\037  !\"#$%#\" \037\036\035\034\033\033\032\031\031\030\030\027\027\026\026\026\025\025\025\024\024\024\024\024\023\023\023\023\023\023\023\023\023\023\022\022\022\022\022\022\022\023\023\023\023\023\023\023\023\023\023\024\024\024\024\025\025\025\025\026\026\027\027\027\030\031\031\032\033\033\034\035\036\037!\"$&),0FEDCBBA@??>>=<<;;::999887776665554443332222111000////...----,,,,+++++++++***************++++++)(&%$#\"!! \037\036\036\035\035\034\034\033\033\033\032\032\032\031\031\031\031\030\030\030\030\030\030\030\027\027\027\027\027\027\027\027\027\027\027\027\027\030\030\030\030\030\030\030\031\031\031\031\032\032\032\033\033\033\034\034\035\035\036\037\037 !\"\"#$%$\"! \036\035\035\034\033\032\032\031\030\030\027\027\027\026\026\025\025\025\025\024\024\024\024\024\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\024\024\024\024\024\025\025\025\026\026\026\027\027\030\030\031\031\032\033\034\034\035\036 !\"$&),1GFEDCBBA@??>>=<<;;;::998887776665554443332221110000///....----,,,,++++++++++++*****+++++++++,+)('&$#\"\"! \037\037\036\035\035\034\034\034\033\033\032\032\032\031\031\031\031\031\030\030\030\030\030\030\030\030\027\027\027\027\027\027\027\027\027\030\030\030\030\030\030\030\030\031\031\031\031\032\032\032\032\033\033\034\034\035\035\036\036\037\037 !\"#$%&$\"! \037\036\035\034\033\032\032\031\031\030\030\027\027\026\026\026\025\025\025\025\024\024\024\024\024\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\024\024\024\024\024\024\025\025\025\025\026\026\027\027\027\030\030\031\032\032\033\034\035\036\037 !#$'),19FFEDCBBA@??>>==<<;;::999887776665554443333222111000////...----,,,,,,+,+++++++++++++++++++,,,+*('&%$#\"! \037\037\036\036\035\035\034\034\033\033\033\032\032\032\031\031\031\031\031\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\031\031\031\031\031\032\032\032\033\033\033\034\034\035\035\036\036\037  !\"#$%&$#! \037\036\035\034\033\033\032\031\031\030\030\027\027\027\026\026\026\025\025\025\025\024\024\024\024\024\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\024\024\024\024\024\024\025\025\025\025\026\026\026\027\027\030\030\031\031\032\032\033\034\035\036\037 !#%')-19GFEEDCBBA@@?>>==<<;;:::998887776665554443332221110000///....----,,,,,,,,,,+++++++++++,,,,,,,,*)'&%$#\"!  \037\036\036\035\035\034\034\033\033\033\032\032\032\032\031\031\031\031\031\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\031\031\031\031\031\032\032\032\032\033\033\034\034\034\035\035\036\037\037 !!\"#$%&%#\" \037\036\035\034\034\033\032\032\031\031\030\030\027\027\026\026\026\025\025\025\025\024\024\024\024\024\024\024\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\024\024\024\024\024\024\025\025\025\025\026\026\026\027\027\027\030\030\031\031\032\033\033\034\035\036\037 \"#%'*-18HGFEEDCBBA@@?>>==<<;;;::999888776665554443332222111000////....----,,,,,,,,,,,,,,,,,,,,,,,,--,*)'&%$#\"!! \037\037\036\035\035\035\034\034\033\033\033\032\032\032\031\031\031\031\031\031\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\031\031\031\031\031\032\032\032\032\033\033\033\034\034\035\035\036\036\037\037 !\"\"#$%'%#\"! \036\036\035\034\033\032\032\031\031\030\030\027\027\027\026\026\026\025\025\025\025\024\024\024\024\024\024\024\024\023\023\023\023\023\023\023\023\023\023\023\023\023\024\024\024\024\024\024\024\025\025\025\025\025\026\026\026\027\027\030\030\030\031\032\032\033\034\034\035\036\037!\"#%'*-18IHGFEDDCBBA@@??>>==<<;;:::998887776665554443332221110000///.....--------,,,,,,,,,,,,,,,-----,+)('%$#\"\"! \037\037\036\036\035\035\034\034\033\033\033\032\032\032\032\031\031\031\031\031\031\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\031\031\031\031\031\032\032\032\032\033\033\033\034\034\034\035\035\036\036\037  !\"#$%&'%$\"! \037\036\035\034\033\033\032\031\031\030\030\030\027\027\026\026\026\026\025\025\025\025\024\024\024\024\024\024\024\024\024\023\023\023\023\023\023\023\023\023\024\024\024\024\024\024\024\024\024\025\025\025\025\026\026\026\027\027\027\030\030\031\031\032\032\033\034\035\036\036 !\"$&(*-29JIHGFEDDCBBA@@??>>==<<;;;::999888776665554443332221111000////.....-----------,,,,,---------.,+)('&%$#\"!  \037\036\036\035\035\034\034\034\033\033\033\032\032\032\032\031\031\031\031\031\031\031\030\030\030\030\030\030\030\030\030\030\030\030\030\031\031\031\031\031\031\031\032\032\032\032\033\033\033\034\034\035\035\036\036\037\037 !!\"#$%&'%$\"! \037\036\035\034\034\033\032\032\031\031\030\030\027\027\027\026\026\026\025\025\025\025\025\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\025\025\025\025\026\026\026\026\027\027\027\030\030\031\031\032\033\033\034\035\036\037 !\"$&(*.29JIHHGFEDDCBBA@@??>>==<<<;;:::998887776665554443332221110000////.....-.------------------....-+*('&%$#\"!! \037\037\036\036\035\035\034\034\033\033\033\033\032\032\032\032\031\031\031\031\031\031\031\031\030\030\030\030\030\030\030\030\030\031\031\031\031\031\031\031\031\032\032\032\032\033\033\033\034\034\034\035\035\036\036\037\037 !\"\"#$%&'&$#! \037\036\035\035\034\033\032\032\031\031\030\030\030\027\027\026\026\026\026\025\025\025\025\025\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\025\025\025\025\025\025\026\026\026\027\027\027\030\030\031\031\032\032\033\034\034\035\036\037 !#$&(+.29KJIHGGFEDDCBBAA@??>>===<<;;:::999888776665554443332221111000/////.........-----------....../-+*)'&%$#\"\"! \037\037\036\036\035\035\034\034\034\033\033\033\032\032\032\032\032\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\032\032\032\032\033\033\033\033\034\034\035\035\035\036\037\037  !\"#$$%'(&%#\"! \037\036\035\034\033\033\032\032\031\031\030\030\027\027\027\026\026\026\026\025\025\025\025\025\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\025\025\025\025\025\025\026\026\026\026\027\027\027\030\030\031\031\032\032\033\034\034\035\036\037 \"#%&)+.29LKJIHGGFEDDCBBAA@@??>>==<<;;;:::998887776665554443332221110000/////......................///-,*)(&%$##\"!  \037\037\036\036\035\035\034\034\034\033\033\033\032\032\032\032\032\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\032\032\032\032\032\033\033\033\034\034\034\035\035\036\036\037\037 !!\"#$%&'(&%#\"! \037\036\035\034\034\033\032\032\031\031\030\030\030\027\027\027\026\026\026\026\025\025\025\025\025\025\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\025\025\025\025\025\025\026\026\026\026\027\027\027\030\030\030\031\031\032\033\033\034\035\036\036\037!\"#%')+/39MLKJIHGFFEDDCBBAA@@??>>==<<<;;:::9998877766655544433322211110000////////..............//////.,+)('&%$#\"!! \037\037\036\036\035\035\034\034\034\033\033\033\033\032\032\032\032\032\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\032\032\032\032\032\032\033\033\033\034\034\034\035\035\035\036\036\037  !\"\"#$%&'('%$\"! \037\036\035\035\034\033\033\032\031\031\031\030\030\027\027\027\026\026\026\026\025\025\025\025\025\025\025\025\024\024\024\024\024\024\024\024\024\024\024\024\024\025\025\025\025\025\025\025\026\026\026\026\027\027\027\027\030\030\031\031\032\032\033\033\034\035\036\037 !\"$%'),/39NMLKJIHGFFEDDCBBAA@@??>>===<<;;;::99988877766555444333222211100000////////////.../////////00.,+)('&%$#\"!!  \037\036\036\035\035\035\034\034\034\033\033\033\033\032\032\032\032\032\032\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\032\032\032\032\032\032\033\033\033\033\034\034\034\035\035\036\036\037\037  !\"##$%&')'%$#! \037\036\036\035\034\033\033\032\032\031\031\030\030\030\027\027\027\026\026\026\026\025\025\025\025\025\025\025\025\025\024\024\024\024\024\024\024\024\024\025\025\025\025\025\025\025\025\026\026\026\026\026\027\027\027\030\030\030\031\031\032\032\033\034\034\035\036\037 !\"$%'),/3:NMLKJJIHGFFEDDCCBAA@@??>>>==<<<;;:::99888777666555444333222111100000/00/////////////////0000.-+*('&%$#\"\"!  \037\037\036\036\035\035\034\034\034\033\033\033\033\033\032\032\032\032\032\032\031\031\031\031\031\031\031\031\031\031\031\031\032\032\032\032\032\032\032\033\033\033\033\034\034\034\035\035\035\036\036\037\037 !!\"#$%&'()'&$#\"! \037\036\035\034\034\033\032\032\031\031\031\030\030\027\027\027\027\026\026\026\026\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\026\026\026\026\026\027\027\027\030\030\030\031\031\032\032\033\033\034\035\035\036\037 !#$&(*,/4:ONMLKJIIHGFFEDDCCBAA@@???>>==<<<;;;::9998887766655544433322221111000000000//////////00000001/-+*)(&%$$#\"!! \037\037\036\036\035\035\035\034\034\034\033\033\033\033\032\032\032\032\032\032\032\032\032\031\031\031\031\031\031\031\032\032\032\032\032\032\032\032\033\033\033\033\034\034\034\034\035\035\036\036\037\037  !\"\"#$%&'()(&%#\"! \037\036\035\035\034\033\033\032\032\031\031\030\030\030\027\027\027\026\026\026\026\026\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\026\026\026\026\026\027\027\027\027\030\030\030\031\031\032\032\033\033\034\035\036\036\037 \"#$&(*-04:PONMLKJIHHGFFEDDCCBBAA@@??>>===<<;;;::999888777665554443333222111110000000000000000000000111/-,*)('&%$#\"!!  \037\037\036\036\035\035\035\034\034\034\033\033\033\033\033\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\033\033\033\033\033\034\034\034\035\035\035\036\036\037\037  !\"##$%&'(*(&%#\"! \037\036\035\035\034\033\033\032\032\031\031\031\030\030\027\027\027\027\026\026\026\026\026\026\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\026\026\026\026\026\026\027\027\027\030\030\030\031\031\031\032\032\033\034\034\035\036\037 !\"#%&(*-04:QPONMLKJIHHGFFEDDCCBBAA@@??>>>==<<<;;:::9988877766655544433322221111111100000000000000111111/.,+)('&%$#\"\"!  \037\037\036\036\035\035\035\034\034\034\034\033\033\033\033\033\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\033\033\033\033\033\034\034\034\035\035\035\036\036\037\037  !!\"#$$%&')*('%$#! \037\037\036\035\034\034\033\033\032\032\031\031\030\030\030\027\027\027\027\026\026\026\026\026\026\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\026\026\026\026\026\026\027\027\027\027\030\030\030\031\031\032\032\033\033\034\035\035\036\037 !\"#%'(+-05;QPONMLLKJIHHGFFEDDCCBBAA@@???>>==<<<;;:::9998877766655544433332222111111111111111111111111220.,+*('&%$##\"!!  \037\037\036\036\035\035\035\034\034\034\033\033\033\033\033\033\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\033\033\033\033\033\033\034\034\034\034\035\035\035\036\036\037\037  !\"\"#$%&'()*)'%$#\"! \037\036\035\035\034\033\033\032\032\031\031\031\030\030\030\027\027\027\027\026\026\026\026\026\026\026\025\025\025\025\025\025\025\025\025\025\025\025\025\026\026\026\026\026\026\026\027\027\027\027\030\030\030\031\031\031\032\032\033\033\034\035\036\036\037 !\"$%')+.15;RQPONMLKKJIHHGFFEDDCCBBAA@@@??>>===<<;;;::999888776665554444333322221221111111111111111222220.-+*)'&%%$#\"\"!  \037\037\036\036\035\035\035\034\034\034\034\033\033\033\033\033\033\033\032\032\032\032\032\032\032\032\032\032\032\032\032\033\033\033\033\033\033\034\034\034\034\035\035\035\036\036\037\037\037 !!\"##$%&'()+)'&$#\"! \037\036\035\035\034\033\033\032\032\032\031\031\030\030\030\027\027\027\027\027\026\026\026\026\026\026\026\026\025\025\025\025\025\025\025\025\025\026\026\026\026\026\026\026\026\027\027\027\027\030\030\030\030\031\031\032\032\033\033\034\034\035\036\037\037 !#$%')+.15;RRQPONMLKJJIHHGFFEEDCCBBAAA@@??>>>==<<<;;:::9988877766655544433332222222222111111111222222320/-,*)('&%$#\"\"!! \037\037\037\036\036\035\035\035\034\034\034\034\033\033\033\033\033\033\033\033\032\032\032\032\032\032\032\032\032\033\033\033\033\033\033\033\034\034\034\034\035\035\035\036\036\036\037\037  !!\"#$$%&'(*+)(&%#\"! \037\036\036\035\034\034\033\033\032\032\031\031\031\030\030\030\027\027\027\027\027\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\027\027\027\027\027\030\030\030\031\031\031\032\032\033\033\034\035\035\036\037 !\"#$&'),.16<QQQPOONMLKJJIHHGFFEEDDCCBBAA@@??>>>==<<<;;:::998887776665554444333322322222222222222222233331/-,*)('&%$##\"!!  \037\037\036\036\036\035\035\035\034\034\034\034\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\034\034\034\034\034\035\035\035\036\036\037\037\037  !\"\"#$%&'()*+*(&%$\"! \037\037\036\035\035\034\033\033\032\032\032\031\031\030\030\030\030\027\027\027\027\027\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\027\027\027\027\027\030\030\030\030\031\031\032\032\032\033\034\034\035\035\036\037 !\"#%&(*,/26<PPPPPONMMLKJJIHHGFFEEDDCCBBAA@@???>>===<<;;;::99988777666555544443333333322222222222233333331/.,+)('&%$$#\"\"!  \037\037\037\036\036\035\035\035\035\034\034\034\034\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\034\034\034\034\034\035\035\035\036\036\036\037\037  !!\"##$%&'()*+*('%$#\"! \037\036\035\035\034\034\033\033\032\032\031\031\031\030\030\030\030\027\027\027\027\027\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\027\027\027\027\027\030\030\030\030\031\031\031\032\032\033\033\034\034\035\036\036\037 !\"#%&(*,/26<KOOOOOONMLLKJIIHHGFFEEDDCCBBAA@@@??>>===<<;;;::99988877766655544443333333333333333333333344310.,+*)('&%$#\"\"!!  \037\037\036\036\036\035\035\035\034\034\034\034\034\034\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\034\034\034\034\034\035\035\035\035\036\036\037\037\037  !\"\"#$$%&'()*,*)'%$#\"! \037\036\036\035\034\034\033\033\032\032\032\031\031\030\030\030\030\027\027\027\027\027\027\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\027\027\027\027\027\027\030\030\030\031\031\031\032\032\032\033\033\034\035\035\036\037\037 !\"$%'(*,/27=JONNNOOONMLLKJIIHHGFFEEDDCCBBAA@@@??>>>==<<;;;::9998887776665555444444433333333333333334444420.-+*)('&%$##\"!!  \037\037\037\036\036\036\035\035\035\034\034\034\034\034\034\033\033\033\033\033\033\033\033\033\033\033\033\033\033\034\034\034\034\034\034\035\035\035\035\036\036\036\037\037  !!\"\"#$%%&'(*+,+)'&$#\"! \037\037\036\035\035\034\033\033\033\032\032\031\031\031\030\030\030\030\027\027\027\027\027\027\027\026\026\026\026\026\026\026\026\026\026\026\026\026\027\027\027\027\027\027\027\030\030\030\030\031\031\031\032\032\033\033\034\034\035\035\036\037 !\"#$%')+-/37=JNNNNNNNNNMLKKJIIHHGFFEEDDCCBBAAA@@??>>>==<<<;;:::99888777666655554444444443333333344444445420/-,*)('&%$$#\"\"!!  \037\037\036\036\036\035\035\035\035\034\034\034\034\034\034\034\033\033\033\033\033\033\033\033\033\033\034\034\034\034\034\034\034\035\035\035\035\036\036\036\037\037\037  !!\"##$%&'()*+,+)(&%$\"!  \037\036\035\035\034\034\033\033\032\032\032\031\031\031\030\030\030\030\027\027\027\027\027\027\027\027\026\026\026\026\026\026\026\026\026\027\027\027\027\027\027\027\027\030\030\030\030\031\031\031\032\032\032\033\033\034\034\035\036\036\037 !\"#$&')+-037=JMMMMMMMNNNMLKKJIIHHGFFEEDDCCBBBAA@@??>>>==<<<;;:::9998877776665555545444444444444444444555421/-,+)('&%%$##\"!!  \037\037\037\036\036\036\035\035\035\035\034\034\034\034\034\034\034\034\034\033\033\033\033\034\034\034\034\034\034\034\034\034\035\035\035\035\035\036\036\036\037\037  !!\"\"#$$%&'()*+-+)(&%$#\"! \037\036\036\035\034\034\033\033\032\032\032\031\031\031\030\030\030\030\030\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\030\030\030\030\031\031\031\031\032\032\033\033\034\034\035\035\036\037\037 !\"#$&')+-038>JLLLLLLMMMMMMLKKJIIHHGFFEEDDCCCBBAA@@???>>==<<<;;:::999888777666655555555444444444444555555531/.,+*)('&%$##\"\"!!  \037\037\036\036\036\036\035\035\035\035\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\035\035\035\035\035\036\036\036\037\037\037  !!\"\"#$%%&'()*,-,*('%$#\"! \037\037\036\035\035\034\034\033\033\032\032\032\031\031\031\030\030\030\030\030\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\030\030\030\030\030\031\031\031\032\032\032\033\033\034\034\035\035\036\037  !\"$%&()+.048>JLKKLLLLLLMMMMLKKJIIHHGFFEEDDDCCBBAA@@???>>===<<;;;::99988877776666555555555555555555555566531/.-+*)('&%$$#\"\"!!  \037\037\037\036\036\036\035\035\035\035\035\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\035\035\035\035\035\036\036\036\037\037\037  !!\"\"##$%&''(*+,-,*('&$#\"!  \037\036\035\035\034\034\033\033\032\032\032\031\031\031\031\030\030\030\030\030\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\030\030\030\030\030\031\031\031\031\032\032\033\033\033\034\034\035\036\036\037 !\"#$%&(*,.148>JKKKKKKKKLLLMMMLKKJIIHHGGFFEEDDCCBBAA@@@??>>===<<;;;::99988877776666666555555555555555566665320.-,*)('&%%$##\"!!   \037\037\037\036\036\036\035\035\035\035\035\035\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\035\035\035\035\035\036\036\036\036\037\037\037  !!\"\"#$$%&'()*+,.,*)'&%#\"!! \037\036\036\035\035\034\034\033\033\032\032\032\031\031\031\031\030\030\030\030\030\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\030\030\030\030\030\030\031\031\031\031\032\032\032\033\033\034\034\035\035\036\037\037 !\"#$%'(*,.159?JJJJJJJJKKKLLLMLLKKJIIHHGGFFEEDDCCBBAA@@@??>>===<<;;;:::999888777766666666655555555666666676420/-,+)(''&%$##\"\"!!  \037\037\037\036\036\036\036\035\035\035\035\035\035\035\034\034\034\034\034\034\034\034\034\034\034\035\035\035\035\035\035\036\036\036\036\037\037\037  !!!\"##$%%&'()*+-.-+)'&%$#\"! \037\037\036\035\035\034\034\033\033\032\032\032\031\031\031\031\030\030\030\030\030\030\030\027\027\027\027\027\027\027\027\027\027\027\027\027\030\030\030\030\030\030\031\031\031\031\032\032\032\033\033\033\034\034\035\035\036\037\037 !\"#$&')*,/159?IIIIIIJJJJJKKLLMLLKJJIIHHGGFFEEDDCCBBAAA@@??>>===<<;;;:::99988877777676666666666666666667776420/-,+*)('&%$$#\"\"!!   \037\037\037\036\036\036\036\035\035\035\035\035\035\035\035\034\034\034\034\034\034\035\035\035\035\035\035\035\035\036\036\036\036\037\037\037\037  !!\"\"##$%&&'()*,-.-+)(&%$#\"! \037\037\036\035\035\034\034\033\033\033\032\032\032\031\031\031\031\030\030\030\030\030\030\030\030\027\027\027\027\027\027\027\027\027\030\030\030\030\030\030\030\030\031\031\031\031\032\032\032\033\033\034\034\035\035\036\036\037  !\"#%&')+-/25:?IIIIIIIIIIJJJKKLMLLKJJIIHHGGFFEEDDCCBBAAA@@??>>>==<<<;;:::9998888777777776666666666667777786431/.,+*)('&%%$##\"\"!!  \037\037\037\037\036\036\036\036\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\036\036\036\036\036\037\037\037  !!!\"\"#$$%&'()*+,-/-+*('%$#\"!  \037\036\036\035\035\034\034\033\033\032\032\032\032\031\031\031\031\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\031\031\031\031\032\032\032\033\033\033\034\034\035\035\036\036\037 !\"#$%&')+-/26:@HHHHHHHHHIIIJJKKLMLLKJJIIHHGGFFEEDDCCBBAAA@@??>>>==<<<;;:::999988887777777777777777777777887531/.-+*)('&&%$$#\"\"!!   \037\037\037\036\036\036\036\036\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\036\036\036\036\036\037\037\037   !!\"\"##$%%&'()*+,./.,*('&$#\"!! \037\037\036\035\035\034\034\033\033\033\032\032\032\031\031\031\031\031\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\031\031\031\031\031\032\032\032\033\033\033\034\034\035\035\036\037\037 !\"#$%&()+-026:@GGGGGGGHHHHIIIJKKLLLLKJJIIHHGGFFEEDDCCBBBAA@@??>>>==<<<;;;:::99988888887777777777777777888875310.-,+)(('&%$$##\"\"!!   \037\037\037\036\036\036\036\036\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\036\036\036\036\036\037\037\037\037  !!!\"\"#$$%&&'()*+-./.,*)'&%$#\"! \037\037\036\036\035\035\034\034\033\033\033\032\032\032\031\031\031\031\031\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\031\031\031\031\031\032\032\032\033\033\033\034\034\035\035\036\036\037  !\"#$%'(*+.036;AGFFFFGGGGGHHHIIJJKLLLLKJJIIHHGGFFEEDDCCBBBAA@@??>>>==<<<;;;:::9999888888888777777778888888985320/-,+*)('&%%$##\"\"!!   \037\037\037\037\036\036\036\036\036\036\035\035\035\035\035\035\035\035\035\035\035\035\036\036\036\036\036\036\037\037\037\037   !!\"\"##$$%&''()*,-.0.,*)'&%$#\"!  \037\036\036\035\035\034\034\033\033\033\032\032\032\032\031\031\031\031\031\031\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\031\031\031\031\031\031\032\032\032\032\033\033\033\034\034\035\035\036\036\037 !!\"#$&'(*,.037;AFFFFFFFFFGGGHHHIJJKLMLLKJJIIHHGGFFEEDDCCBBBAA@@??>>>==<<<;;;::::99998988888888888888888899986420/.,+*)('&&%$$#\"\"\"!!   \037\037\037\037\036\036\036\036\036\036\036\035\035\035\035\035\035\035\035\036\036\036\036\036\036\036\037\037\037\037   !!!\"\"##$%%&'()*+,-.0.-+)(&%$#\"!! \037\037\036\035\035\034\034\034\033\033\033\032\032\032\032\031\031\031\031\031\031\030\030\030\030\030\030\030\030\030\030\030\030\030\031\031\031\031\031\031\032\032\032\032\033\033\033\034\034\035\035\036\036\037\037 !\"#$%&')*,.137;AEEEEEEEEFFFFGGHHIJJKLMLLKJJIIHHGGFFEEDDCCBBBAA@@???>>===<<;;;;:::9999999988888888888899999:86421/.-+*)(''&%$$##\"\"!!!   \037\037\037\037\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\037\037\037\037\037   !!\"\"##$$%&&'()*+,-/0/-+)('%$#\"\"! \037\037\036\036\035\035\034\034\033\033\033\032\032\032\032\031\031\031\031\031\031\031\031\030\030\030\030\030\030\030\030\030\031\031\031\031\031\031\031\032\032\032\032\032\033\033\033\034\034\035\035\036\036\037  !\"#$%&')*,.147<BDDDDDDEEEEEFFGGHHIIJKLMLKKJJIIHHGGFFEEDDCCBBBAA@@???>>===<<<;;;::::9999999999988999999999::964310.-,+*)('&%%$##\"\"\"!!   \037\037\037\037\037\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\037\037\037\037\037   !!!\"\"##$$%&''()*+,./1/-+*('&%$#\"!  \037\036\036\035\035\034\034\034\033\033\033\032\032\032\032\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\032\032\032\032\033\033\033\034\034\034\035\035\036\036\037  !\"#$%&()+-/148<BDDDDDDDDDDEEEFFGGHIIJKLMLKKJJIIHHGGFFEEDDCCBBBAA@@???>>===<<<;;;:::::::9999999999999999::::975310.-,+*)('&&%$$##\"\"!!!   \037\037\037\037\037\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\037\037\037\037\037    !!\"\"\"#$$%%&'())*,-./1/-,*)'&%$#\"!  \037\037\036\035\035\035\034\034\033\033\033\033\032\032\032\032\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\032\032\032\032\033\033\033\033\034\034\035\035\036\036\037\037 !!\"#$%'()+-/248<CCCCCCCCCDDDDEEFFGGHIIJKLMLKKJJIIHHGGFFEEDDCCBBBAA@@???>>===<<<;;;;::::::::9999999999::::::;975320/-,+*)(''&%%$##\"\"\"!!    \037\037\037\037\037\037\036\036\036\036\036\036\036\036\036\036\036\036\036\037\037\037\037\037    !!!\"\"##$$%&&'()*+,-.010.,*)'&%$#\"!! \037\037\036\036\035\035\034\034\034\033\033\033\032\032\032\032\032\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\032\032\032\032\032\033\033\033\034\034\034\035\035\036\036\037\037 !\"##$&'(*+-/258=CBBBBBBCCCCCDDDEEFGGHIJJKLMLKKJJIIHHGGFFEEDDCCBBBAA@@???>>===<<<<;;;;:;::::::::::::::::::;;;:75420/.,+*)(('&%%$$##\"\"!!!    \037\037\037\037\037\037\037\036\036\036\036\036\036\036\036\036\037\037\037\037\037\037\037   !!!\"\"\"##$%%&''()*+,-/010.,+)(&%$#\"\"!  \037\036\036\035\035\035\034\034\033\033\033\033\032\032\032\032\032\032\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\032\032\032\032\032\032\033\033\033\033\034\034\035\035\035\036\036\037  !\"#$%&'(*,-0259=BBBBBBBBBBBCCCDDEEFGGHIJKLMLLKKJJIIHHGGFFEEDDCCBBBAA@@???>>>===<<<;;;;;;;:::::::::::::;;;;;<:86421/.-,+*)('&&%$$##\"\"\"!!!    \037\037\037\037\037\037\037\037\037\036\036\037\037\037\037\037\037\037\037\037\037    !!!\"\"##$$%%&'(()*+,./020.,+)('&%$#\"!  \037\037\036\036\035\035\034\034\034\033\033\033\033\032\032\032\032\032\032\031\031\031\031\031\031\031\031\031\031\031\031\031\032\032\032\032\032\032\033\033\033\033\034\034\034\035\035\036\036\037\037 !!\"#$%&')*,.0259>AAAAAAAAABBBBCCDDEEFFGHIJKLMLLKKJJIIHHGGFFEEDDCCBBBAA@@???>>>===<<<<;;;;;;;;;::::::;;;;;;;<<:864310.-,+*)(''&%%$$##\"\"!!!     \037\037\037\037\037\037\037\037\037\037\037\037\037\037\037\037\037\037\037    !!!\"\"\"##$$%&&'()*+,-./121/-+*('&%$#\"!! \037\037\036\036\035\035\035\034\034\034\033\033\033\033\032\032\032\032\032\032\032\031\031\031\031\031\031\031\031\031\032\032\032\032\032\032\032\033\033\033\033\034\034\034\035\035\035\036\036\037\037 !\"\"#$%&()*,.0369>A@@@@@@AAAAABBBCCDDEFGGHIJKLMLLKKJJIIHHGGFFEEDDCCBBBAA@@???>>>===<<<<<<<;;;;;;;;;;;;;;;;<<<<;865310/-,+*)(('&%%$$##\"\"\"!!!     \037\037\037\037\037\037\037\037\037\037\037\037\037\037\037\037     !!!!\"\"##$$%%&''()*+,-./121/-+*('&%$#\"\"!  \037\036\036\036\035\035\034\034\034\033\033\033\033\032\032\032\032\032\032\032\032\032\032\031\031\031\032\032\032\032\032\032\032\032\032\032\033\033\033\033\034\034\034\035\035\036\036\037\037  !\"##$%'()+,.136:>@@@@@@@@@@@AAABBCCDDEFGGHIJLMMLLKKJJIIHHGGFFEEDDCCBBBAA@@???>>>====<<<<<<<;;;;;;;;;;;<<<<<<=;975320/.,+*))('&&%$$###\"\"!!!!     \037\037\037\037\037\037\037\037\037\037\037\037\037\037     !!!!\"\"\"##$$%%&'(()*+,-.0131/-,*)'&%$##\"!  \037\037\036\036\035\035\035\034\034\034\033\033\033\033\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\033\033\033\033\034\034\034\035\035\035\036\036\037\037  !\"#$%&'()+-/136:??????????@@@@AAABBCDDEFGHIJKLMMLLKKJJIIHHGGFFEEDDCCBBBAA@@@??>>>>====<<<<<<<<<<<<<<<<<<<<===;975420/.-,+*)(''&%%$$##\"\"\"!!!!      \037\037\037\037\037\037\037\037\037\037       !!!\"\"\"##$$%%&&'())*+,-/01320.,*)('%%$#\"!! \037\037\036\036\035\035\035\034\034\034\033\033\033\033\033\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\033\033\033\033\033\034\034\034\035\035\035\036\036\037\037 !!\"#$%&'(*+-/147:???>>>>>?????@@@AABBCDDEFGHIJKMMMLLKKJJIIHHGGFFEEDDCCBBBAA@@@???>>>=======<<<<<<<<<<<<<<====><:76421/.-,+*)(''&%%$$###\"\"\"!!!!       \037\037\037\037\037\037        !!!!\"\"\"##$$%%&''()*+,-./02320.,+)('&%$#\"!!  \037\037\036\036\035\035\035\034\034\034\033\033\033\033\033\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\033\033\033\033\033\034\034\034\035\035\035\036\036\037\037  !\"\"#$%&')*+-/147;>>>>>>>>>>>>???@@AABBCDDEFGHIJLMMMLLKKJJIIHHGGFFEEDDCCBBBAA@@@???>>>>========<<<<<<<<======>><:864310.-,+*)(('&&%%$$##\"\"\"!!!!!                   !!!!\"\"\"###$$%&&''()*+,-./12420.,+*('&%$#\"\"!  \037\037\036\036\035\035\035\034\034\034\034\033\033\033\033\033\033\032\032\032\032\032\032\032\032\032\032\032\032\032\033\033\033\033\033\033\034\034\034\034\035\035\035\036\036\037\037  !\"##$%&')*,./247;==========>>>>???@@ABBCDEEFGHJKLNMMLLKKJJIIHHGGFFEEDDCCBBBAA@@@???>>>>=>=================>>>><:864310/-,+**)(''&%%$$###\"\"\"!!!!!                 !!!!\"\"\"\"##$$%%&&'(()*+,-.012430.-+*('&%$##\"!! \037\037\036\036\036\035\035\035\034\034\034\034\033\033\033\033\033\033\032\032\032\032\032\032\032\032\032\032\032\033\033\033\033\033\033\034\034\034\034\035\035\035\036\036\036\037\037 !!\"#$$%'()*,.0258;====<<<======>>>??@@ABBCDEFGHIJKMNMMLLKKJJIHHGGFFFEEDDCCBBBAA@@@????>>>>>>>============>>>>>?=;865320/.-,+*)(''&&%%$$##\"\"\"\"!!!!!               !!!!!\"\"\"###$$%%&''()**+,-/013431/-+*)(&%$$#\"!!  \037\037\036\036\035\035\035\034\034\034\034\033\033\033\033\033\033\033\033\032\032\032\032\032\032\032\033\033\033\033\033\033\033\033\034\034\034\034\035\035\035\036\036\037\037  !!\"#$%&'()+,.0258<<<<<<<<<<<<====>>??@@ABBCDEFGHIKLNNMMLLKJJIIHHGGFFEEEDDCCBBBAA@@@????>>>>>>>>>>====>>>>>>>???=;975320/.-,+*)(('&&%%$$###\"\"\"\"!!!!!            !!!!!!\"\"\"###$$%%&&''()*+,-./013531/-,*)('&%$#\"\"!  \037\037\036\036\036\035\035\035\034\034\034\034\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\034\034\034\034\035\035\035\036\036\036\037\037  !\"\"#$%&'()+-.0358<<;;;;;;;;<<<<<===>>?@@ABBCDEFGIJKMNNMMLKKJJIIHHGGFFEEDDDCCBBBAAA@@@??????>>>>>>>>>>>>>>>?????>;975421/.-,+*))(''&%%$$$###\"\"\"!!!!!!!        !!!!!!!\"\"\"\"###$$%%&&'(()*+,-./023531/.,+)('&%$#\"\"!! \037\037\037\036\036\035\035\035\034\034\034\034\034\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\034\034\034\034\034\035\035\035\036\036\037\037  !!\"##$%&'(*+-/1369;;;;;;;;;;;;;;<<<==>>??@ABCCDEGHIJLNNNMLLKKJJIIHHGGFFEEDDDCCBBBAAA@@@????????>>>>>>>>>>?????@@><9764210/-,+**)(''&&%%$$###\"\"\"\"!!!!!!!!!!!!!!!!!!!!\"\"\"\"###$$%%%&''())*+,-./1245420.,+)('&%$##\"!!  \037\037\036\036\036\035\035\035\034\034\034\034\034\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\034\034\034\034\034\035\035\035\036\036\036\037\037  !!\"#$$%&')*+-/1369;:::::::::::;;;;<<==>>??@ABCDEFGHJKMNNMMLLKKJJIIHHGGFFEEDDDCCBBBAAA@@@@?@??????????????????@@@><:864310/.-,+*)(('&&%%$$$###\"\"\"\"!!!!!!!!!!!!!!!!!!\"\"\"\"####$$%%&&''()**+,-.01245420.,+*('&%$$#\"\"!  \037\037\036\036\036\035\035\035\035\034\034\034\034\034\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\034\034\034\034\034\035\035\035\035\036\036\037\037\037  !\"\"#$%&'()*,-/1369:::999999:::::;;;<<<=>>?@@ABCDEFHIJLNONMMLLKKJJIIHHGGFFEEDDDCCBBBAAA@@@@@@@??????????????@@@@A?<:865320/.-,+*))(''&&%%$$####\"\"\"\"!!!!!!!!!!!!!!!!\"\"\"\"\"###$$$%%&&'(()*+,-./01346420.-+*)('&%$#\"\"!!  \037\037\036\036\036\035\035\035\034\034\034\034\034\034\034\033\033\033\033\033\033\033\033\033\033\033\034\034\034\034\034\034\035\035\035\035\036\036\036\037\037  !!\"\"#$%&'()*,./146:9999999999999:::;;;<<==>?@@ABCDFGHJKMONNMMLLKKJJIIHHGGFFEEDDDCCBBBAAAA@@@@@@@@????????@@@@@@AA?=:875321/.-,+**)(''&&%%$$$###\"\"\"\"\"\"!!!!!!!!!!!!!\"\"\"\"\"###$$$%%&&''())*+,-./01346520/-,*)('&%$##\"!!  \037\037\036\036\036\035\035\035\035\034\034\034\034\034\034\034\034\033\033\033\033\033\033\033\033\034\034\034\034\034\034\034\035\035\035\035\036\036\036\037\037  !!\"##$%&'()+,.02479998888888889999:::;;<<==>?@ABCDEFGIJLNONNMMLLKKJJIHHHGGFFEEDDDCCBBBAAAA@A@@@@@@@@@@@@@@@@@AAAA?=;9754210.-,++*)(('&&%%%$$####\"\"\"\"\"\"!!!!!!!!!!\"\"\"\"\"\"####$$$%%&&''()**+,-./02356531/-,*)('&%$##\"!!  \037\037\037\036\036\036\035\035\035\035\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\035\035\035\035\036\036\036\037\037\037  !\"\"#$$%&'(*+,.0247888888888888888999::;;<<=>>?@ABCDEGHIKMOONNMMLLKJJIIHHGGFFFEEDDDCCBBBBAAAAAA@@@@@@@@@@@@@AAAAAB@=;9754210/.-,+*))(''&&%%$$$####\"\"\"\"\"\"\"\"!!!!!\"\"\"\"\"\"\"####$$$%%&&''(()*++,-.012357531/.,+)('&%$$#\"\"!!  \037\037\036\036\036\035\035\035\035\035\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\035\035\035\035\035\036\036\036\037\037  !!\"\"#$%%&')*+-.025787777777777778888999::;<<=>>?@ABCDFGIJLNOONNMLLKKJJIIHHGGFFFEEDDDCCCBBBAAAAAAAAA@@@@@@AAAAAABBB@>;9764310/.-,+*))(''&&%%%$$$####\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"#####$$$%%&&''())*+,-./012457631/.,+*('&&%$#\"\"!!  \037\037\037\036\036\036\035\035\035\035\035\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\035\035\035\035\035\036\036\036\037\037\037  !!\"##$%&'()*+-/0357777766666667777788899::;<<=>??@ACDEFHIKMOOONMMLLKKJJIIHHGGFFFEEDDDCCCBBBBBBAAAAAAAAAAAAAAAABBBB@><:864320/.-,+**)((''&&%%$$$#####\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"#####$$$%%%&&'(())*+,-./0134676420.-+*)('&%$##\"!!   \037\037\036\036\036\036\035\035\035\035\035\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\035\035\035\035\035\036\036\036\036\037\037   !\"\"##$%&'()*,-/13577666666666666677778899::;<<=>?@ABCDFGIJLNPONNMMLLKKJJIIHHGGFFEEEDDDCCCBBBBBBBAAAAAAAAAAABBBBBCCA><:865321/.-,++*))(''&&%%%$$$#####\"\"\"\"\"\"\"\"\"\"\"\"\"\"#####$$$%%%&&''(()**+,-./0234686420.-+*)('&%$$#\"\"!!  \037\037\037\036\036\036\035\035\035\035\035\035\034\034\034\034\034\034\034\034\034\034\034\034\034\035\035\035\035\035\035\036\036\036\037\037\037  !!\"\"#$$%&'()+,-/136666655555555566667778899::;<<=>?@ABDEFHJKMOOONNMMLLKKJJIIHHGGFFEEEDDDCCCCBBBBBBBBBBAABBBBBBBBCCCA><:8753210/.-,+*))(('&&&%%$$$$#####\"\"\"\"\"\"\"\"\"\"\"\"#####$$$$%%%&&''())*++,-./1235686420/-,*)('&%$$#\"\"!!  \037\037\037\036\036\036\036\035\035\035\035\035\035\035\034\034\034\034\034\034\034\034\034\035\035\035\035\035\035\035\036\036\036\036\037\037\037  !!\"\"#$%%&'()+,./1366555555555555555666777889::;<<=>?@BCDFGIJLNPOONNMMLLKJJIIIHHGGFFEEEDDDCCCCCCBBBBBBBBBBBBBBBCCCCCA?=:9754210/.-,+**)((''&&%%%$$$$######\"\"\"\"\"\"\"\"#######$$$%%%&&''(())*+,-./01235687420/-,+)('&%%$##\"!!   \037\037\037\036\036\036\036\035\035\035\035\035\035\035\035\034\034\034\034\034\035\035\035\035\035\035\035\035\036\036\036\036\037\037\037  !!\"\"##$%&'')*+,.014555544444444444555566677889::;<=>?@ABCEFHJLNPPOONNMLLKKJJIIHHHGGFFEEEDDDDCCCCCCCBBBBBBBBBBBBBBBBBB?=;9764310/.-,++*))(''&&&%%%$$$####################$$$$%%%&&''(()**+,-./01245787531/-,+*(''&%$##\"\"!!  \037\037\037\036\036\036\036\036\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\036\036\036\036\036\037\037\037  !!\"\"#$$%&'()*+-.0245444444333344444445556677889::;<=>?@ACDEGIKMOPPOONMMLLKKJJIIHHGGGFFEEEDDDDCDCCCCCCCCCCBBBBBAAAAAAB?=;9764320/.-,,+*))((''&&%%%$$$$##################$$$$%%%&&&''())*++,-./01345797531/.,+*)('&%$$#\"\"!!   \037\037\037\036\036\036\036\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\036\036\036\036\037\037\037   !!\"\"#$$%&'()*+-.02444433333333333334444556677889::;<=>?ABCEFHJLNPPPONNMMLLKKJJIIHHGGGFFFEEEDDDDDDCCCCCBBBAAAAAA@@@@@A@=;98653210..-,+**)((''&&&%%%$$$$################$$$$%%%%&&''(())*+,,-./023467985310.-+*)('&%%$##\"!!!  \037\037\037\037\036\036\036\036\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\036\036\036\036\037\037\037\037  !!\"\"##$%%&'()*,-/024433333222222233333444555667889:;<=>?@ABDEGIKMOQPOONNMMLLKKJJIIHHGGGFFFEEEDDDDDDCCBBAAA@@@@@@@??@@@@><:8653210/.-,+**))('''&&%%%$$$$$$#############$$$$$%%%&&&''(()**+,-..0123468986320.-+*)('&%%$##\"\"!!   \037\037\037\036\036\036\036\036\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\036\036\036\036\036\037\037\037   !!\"\"##$%&&'()+,-/1333322222222222222333344455667889:;<=>?@BCEFHJLNQPPOONNMMLKKJJJIIHHGGGFFFEEEEEDDCBBAAA@@@????????????><:8754210/.-,+").append("+*))((''&&&%%%$$$$$$##########$$$$$$%%%%&&''(())*++,-./0123568:86420.-,*)('&&%$$#\"\"!!   \037\037\037\037\036\036\036\036\036\036\035\035\035\035\035\035\035\035\035\035\035\036\036\036\036\036\036\037\037\037\037   !!\"\"#$$%&'()*+,./13322222111111111222223334455667899:;<=>@ABDFGIKNPQPPOONMMLLKKJJIIIHHGGGFFFEEEDCCBAA@@@???>>>>>>>>>>>>><:9754310/.-,,+**)(('''&&%%%%$$$$$$$######$$$$$$$%%%%&&&''(())*++,-./0124568:86420/-,+*)('&%$$##\"\"!!   \037\037\037\036\036\036\036\036\036\036\035\035\035\035\035\035\035\035\035\036\036\036\036\036\036\036\037\037\037   !!\"\"##$$%&'()*+,./12222111111111111111222233445566789::;=>?@BCEGHJMOQQPPONNMMLLKKJJIIIHHGGGFFFEECBBAA@@??>>>>=============;9764320/..-,+**))((''&&&%%%%$$$$$$$$$$$$$$$$$$%%%%&&&'''(()**+,,-./0134578:96421/-,+*)('&%%$##\"\"!!   \037\037\037\037\036\036\036\036\036\036\036\036\036\035\035\035\036\036\036\036\036\036\036\036\036\037\037\037\037   !!\"\"##$%%&'()*+-.0122111100000000000111122233445567789:;<=>?ABDFHJLNQQPPOONNMMLLKKJJIIHHHGGGFEEDBAA@@??>>====<<<<<<<<<<<==;97643210/.-,++*))(('''&&&%%%%$$$$$$$$$$$$$$$$%%%%%&&&''(())**+,-../0234679:97431/.,+*)('&%%$##\"\"!!!   \037\037\037\037\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\037\037\037\037   !!!\"\"#$$%&&'()*+-.02111000000000000000001112223345567789:;<=?@BCEGIKMPQQPPOONNMMLLKKJJIIHHHGFEDDCA@@??>>===<<<<;;;;;;;;;<<<;98653210/.-,,+**)(((''&&&%%%%%$$$$$$$$$$$$$$%%%%%&&&'''(())*++,-./01234679;97531/.-+*)('&&%$$##\"\"!!   \037\037\037\037\037\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\037\037\037\037\037   !!\"\"##$$%&''()*,-.0110000////////////00000111223345567899:<=>?ABDFHJLORQQPPOONMMLLKKKJJIIHGFEDCCB@@?>>==<<<;;;;;:::::::;;;;;:8654210/.--,+**))(('''&&&%%%%%$$$$$$$$$$$$%%%%%&&&&''(())**+,,-./01235689;:75310.-,*)(''&%$$##\"\"!!!   \037\037\037\037\037\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\037\037\037\037\037   !!!\"\"##$$%&'()*+,-/00000///////...///////00011122334556789:;<=?@BCEGIKNQRQQPOONNMMLLKKJJJIGFEDCBBA??>==<<<;;;::::::999::::::::8754310/..-,++*))(('''&&&&%%%%%$$$$$$$$$$%%%%%%&&&'''(())**+,--./0124568:;:75310.-,+*)('&%%$##\"\"!!!   \037\037\037\037\037\037\036\036\036\036\036\036\036\036\036\036\036\036\036\037\037\037\037\037\037   !!!\"\"##$%%&'()*+,-/000////.............////0001122334566789:;=>?ACDFHKMPRRQPPOONNMMLLKKJIHFEDCBBA@>>==<<;;:::999999999999999::87543210/.-,++**))(('''&&&%%%%%%%%$$$$$%%%%%%%&&&&''((())*++,-../0134578:<:86420/-,+*)('&%%$$##\"\"!!!   \037\037\037\037\037\037\036\036\036\036\036\036\036\036\036\036\036\037\037\037\037\037\037   !!!\"\"##$$%&&'()*+,./0///......------......////00112233456778:;<=?@BCEGJLORRQQPPOONNMMLLKIHGEDCBAA@?==<<;;::9999888888888888899997643210/.-,,+**))(('''&&&&%%%%%%%%%%%%%%%%%%&&&&'''(())**++,-./01234578:<:86420/-,+*)('&&%$$##\"\"!!!    \037\037\037\037\037\037\037\036\036\036\036\036\036\036\037\037\037\037\037\037\037    !!!\"\"##$$%&&'()*+-.////....-------------....///00112234456789:;<>?ACEGIKNQRRQQPPONNMMLKIHGFDCBA@@?>=<;;::999888877777777777888897653210/.--,++*))((('''&&&&%%%%%%%%%%%%%%%%&&&&'''((())**+,,-./01234679:<;86421/.,+*)(''&%%$##\"\"\"!!!   \037\037\037\037\037\037\037\037\037\037\036\037\037\037\037\037\037\037\037\037\037   !!!\"\"\"##$%%&''()*+-.//...------,,,,,,,------...//00012234456789:<=?@BDFHJMPSRRQPPOONNMKJHGFECBA@??>=<;::99988877777666666667777788654210/..-,++**))(('''&&&&&%%%%%%%%%%%%%%&&&&&'''(()))*++,--./01235679;<;96421/.-+*)(('&%%$$##\"\"!!!    \037\037\037\037\037\037\037\037\037\037\037\037\037\037\037\037\037\037\037    !!!\"\"##$$%%&'()*+,-....----,,,,,,,,,,,,,,,----..///001223456679:;<>?ACEGILORSRQQPPOONLJIGFEDBA@??>=<;::998877766666665556666666777654310//.-,,+**))((('''&&&&&%%%%%%%%%%%%&&&&&'''((())**++,-../01245689;=;97531/.-,+*)('&%%$$##\"\"!!!!    \037\037\037\037\037\037\037\037\037\037\037\037\037\037\037\037\037    !!!!\"\"##$$%&&'()*+,-...---,,,,,+++++++++,,,,,---...//001223456789:<=?@BDFHKNQSRRQQPPNMKIHFEDCA@?>>=<;:998877766655555555555555566677543210/.-,,++*)))((''''&&&&&&%%%%%%%%%&&&&&''''((())**+,,-./001345689;=<975310.-,+*)('&&%$$##\"\"\"!!!     \037\037\037\037\037\037\037\037\037\037\037\037\037\037\037     !!!\"\"\"##$$%&&'()*+,-.---,,,,+++++++++++++++,,,,---..//00123345678:;<>@ACEHJMPSSRRQQOMKJHGEDCB@?>>=<;;9887766655554444444444444555566643210/.--,++**))(((''''&&&&&&&%%%%&&&&&&&&'''((())**++,--./01234578:;=<975310/-,+*)(''&%%$$##\"\"!!!!     \037\037\037\037\037\037\037\037\037\037\037\037\037     !!!!\"\"##$$%%&''()*+,.--,,,,+++++*********+++++,,,,--..//01123456789:<=?ABDGILORSSRQPNLJIGFDCBA?>>=<;::8877665554444433333333344444555643210/..-,,+**)))((''''&&&&&&&&&&&&&&&&&&''''((())**++,--./01234578:<><:75320/-,+*)(''&%%$$##\"\"\"!!!      \037\037\037\037\037\037\037\037\037\037\037      !!!\"\"\"##$$%%&'(()*+---,,,++++***************++++,,,--..//0112345678:;<>@BDFHKNQTSRQOMKIHFECBA@?>=<;:997766555444333333222223333334445554210//.-,,++**))(((''''&&&&&&&&&&&&&&&&''''((()))**+,,-../01234679:<><:86420/.,+*)(('&&%$$##\"\"\"!!!!       \037\037\037\037\037\037\037       !!!!\"\"\"##$$%&&'())*,--,,++++*****)))))))))*****++++,,--..//0122345689:<=?ACEGJMPSSRPNLJHFEDBA@?>=<;:99876555443333222222222222222333344543210/.--,++**))((((''''&&&&&&&&&&&&&&'''''((())**++,,-.//01235679:<>=:86421/.-,**)('&&%%$$##\"\"\"!!!!         \037         !!!!\"\"\"##$$%%&&'()*+,-,,+++****)))))))))))))))****+++,,--..//0123456789;<>@BDFILORSQNLJIGEDBA@?>=<;:988765544333222211111111111112222333443210/.--,,+**)))((('''''&&&&&&&&&&&&'''''((()))**++,--./012345689;<>=:86421/.-,+*)(''&%%$$##\"\"\"!!!!                   !!!!\"\"\"##$$%%&''()*+,,,++***))))))((((((((())))))***++,,,-../0012345679:<=?ACFHKNQROMKIHFDCA@?>=<;:9887654433322211111000000000111112223343210/..-,,++**))((((''''''&&&&&&&&''''''(((())***+,,--./012345689;=?=;864310.-,+*)(''&%%$$###\"\"\"!!!!                 !!!!\"\"\"###$$%%&''()*+,+++***))))((((((((((((((())))***+++,,-../0112345789;=>@BEGJMPQNLJHGECB@?>=<;:98776544332211110000000000000000011122233210//.-,,++**)))(((('''''''&&&&''''''''((()))**++,,-../01234578:;=?=;965310.-,+*)(('&&%$$$##\"\"\"!!!!!               !!!!!\"\"\"##$$$%&&'(()*+++***)))(((((('''''''''(((((()))***++,,-../012345679:<>@BDFILOOMKIGEDBA@>=<;:987765533221110000//////////////00001112232100/.--,++***)))(((''''''''''''''''''(((())***++,--.//01234678:<=?>;975310/-,+*))('&&%%$$###\"\"\"!!!!!             !!!!!\"\"\"\"##$$%%&&'())*++***))((((('''''''''''''''((((())***++,,-.//01234578:;=?ACEHKNNLJHFDCA@?=<;:9877654432211000/////...........////00011122210/..-,,++**)))((((''''''''''''''''(((()))**++,,--./011235679:<>?>;975320/.,+**)(''&%%$$###\"\"\"!!!!!!           !!!!!!\"\"\"###$$%%&''()*++**)))(((''''''&&&&&&&&&''''''((()))**++,--./001235679:<>@BEGJMMKIGECB@?><;:987765443211000///.......----.......////00112210/..-,,++***)))((((''''''''''''''((((()))**++,,-../012345679:<>@><975320/.-,+*)(''&%%$$$##\"\"\"\"!!!!!!         !!!!!!\"\"\"\"##$$$%&&''()*+**))((('''''&&&&&&&&&&&&&&&'''''((())**++,--./01234578:;=?ADFILLJHFDBA?>=;:98766544321100///....---------------....//0001110//.--,,++**)))(((((''''''''''''((((()))***++,--../012345689;<>@?<:75421/.-,+*)(('&&%%$$###\"\"\"\"!!!!!!!!   !!!!!!!!\"\"\"\"###$$%%&&'(()***))((('''&&&&&&&%%%%%%%&&&&&&&'''((())**++,-../01235679;<>@CEHKKIGECA@>=<;9877654332200///...----,,,,,,,,,,,,-----...//001100/.--,,++***)))(((((('''''''''((((())))**++,,--.//012345789;=>@?<:86421/.-,+*)(('&&%%$$###\"\"\"\"!!!!!!!!!!!!!!!!!!!\"\"\"\"###$$%%&&'())**))(('''&&&&&%%%%%%%%%%%%%%%&&&&&'''(())**+,,-./00234578:<=@BDGJJGEDB@?=<;:987654332110//..----,,,,,,,+++++,,,,,,----..///0110/..-,,++***))))((((((('''''((((((()))***++,,--./001234678:;=?A?<:864310.-,+*))(''&%%$$$###\"\"\"\"!!!!!!!!!!!!!!!!!\"\"\"\"###$$$%%&''())*))(('''&&&%%%%%%%$$$$$$$%%%%%%%&&&'''(())**+,,-./01234679;=?ADFIHFDCA?><;:987654332110/..---,,,,++++++++++++++++,,,,---..//000/..--,,++***)))(((((((((((((((((())))**+++,,-../012235678:;=?A?=:864310/-,+**)(''&&%%$$###\"\"\"\"\"!!!!!!!!!!!!!!!\"\"\"\"\"###$$%%&&''()*))((''&&&&%%%%$$$$$$$$$$$$$$$%%%%%&&&''(())**+,--./0134578:<>@CEHGECB@>=;:987654322100/..--,,,+++++************+++++,,,--..//00//.--,,++***))))(((((((((((((((())))***++,,--.//012345679:<=?A@=;865310/.-,+*)(('&&%%$$$###\"\"\"\"\"!!!!!!!!!!!!!\"\"\"\"\"###$$$%%&&'(()))((''&&&%%%%$$$$$$$#####$$$$$$$%%%%&&&''(())**+,-./01234689;=?BDGFDBA?=<;987654322100//--,,,++++*******))))*******++++,,,--../00/..-,,+++***))))(((((((((((((())))***+++,,--.//012345679:<>?A@=;975320/.-,+*)(('&&%%$$$###\"\"\"\"\"\"!!!!!!!!!!!\"\"\"\"\"\"###$$$%%&&'(())((''&&%%%%$$$$###############$$$$%%%%&&''(())*++,-./0124578:<>ACFECA@><;:87654322100/..-,,+++****))))))))))))))))****+++,,--../0/..--,,++***)))))(((((((((((()))))***++,,,-../0012345689:<>@B@=;975320/.-,+*))(''&&%%$$####\"\"\"\"\"\"!!!!!!!!!\"\"\"\"\"\"####$$%%&&''())((''&&%%%$$$$###################$$$$%%%&&''(())*+,,-./123568:;>@CEDB@?=;:9865432210//..-,+++***))))))(((((((((()))))****++,,,-..///.--,,+++***)))))(((((((((()))))****++,,--../0112345789;<>@B@>;975421/.-,+*))(''&&%%$$$####\"\"\"\"\"\"\"!!!!!\"\"\"\"\"\"\"\"###$$$%%&&''()((''&&%%$$$$#####\"\"\"\"\"\"\"\"\"\"\"\"\"#####$$$$%%&&''(())*+,-./0124579;=?BDCA?><;9876433210//.--,++***))))(((((((((((((((((())))***++,,--.//.--,,,++****))))))(((((()))))))***+++,,--.//0123356789;=>@BA>;9754210.-,+**)(('&&%%$$$####\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"####$$$%%&&'((((''&&%%$$$####\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"####$$$%%&&''(()**+,-./023568:<>ADB@>=;:876543210//.--,,+**)))(((((''''''''''''''(((())))**++,,--./..--,,+++***))))))))))))))))))****++,,--..//012345678:;=?@BA><:764210/-,++*)((''&%%%$$$####\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"####$$$%%%&''(((''&&%%$$$###\"\"\"\"\"\"!!!!!!!!!!!!\"\"\"\"\"####$$%%%&''(()*+,,./0124579;=@CA?=<:976543210//.--,,+*)))((((''''''&&&&&&&&''''''(((())***+,,--...--,,+++****))))))))))))))))****+++,,--../0012345679:;=?ACA><:864310/.-,+*)((''&&%%$$$#####\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"#####$$$%%&&''((''&&%%$$###\"\"\"\"\"!!!!!!!!!!!!!!!!!!\"\"\"\"###$$%%%&''())*+,-./023568:=?B@><;986543210//.-,,++*))((('''''&&&&&&&&&&&&&&&&''''((()))**++,--..--,,,+++****))))))))))))))*****++,,,--.//0112345679:<=?ACA?<:864310/.-,+*))(''&&%%%$$$####\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"####$$$%%%&&''(''&&%%$$###\"\"\"!!!!!!           !!!!!!\"\"\"###$$$%%&''()**+,-.012468:<>A?=;:87643210//.-,,++**)((''''&&&&&%%%%%%%%%%%%&&&&&'''((())**++,--..--,,+++*****))))))))))))*****+++,,--..//0122345689:<=?ACB?<:865320/.-,+**)(('&&%%%$$$#####\"\"\"\"\"\"\"\"\"\"\"\"\"#####$$$%%%&&'(('&&%%$$##\"\"\"!!!!!                 !!!!!\"\"\"##$$$%&&''()*+,-./023579;=@><:97654210//.-,,+**))('''&&&&%%%%%%%%%%%%%%%%%%%&&&&'''(())**++,--.--,,,+++*****))))))))))*****++++,,--../00123446789;<>?ACB?=:875321/.-,+**)((''&&%%$$$$#####\"\"\"\"\"\"\"\"\"\"\"#####$$$$%%&&''('&&%%$$##\"\"\"!!!!       \037\037\037\037\037\037\037       !!!!\"\"\"###$$%&&'(()*+,-.013468:<?=;986543210/.-,,+**))(''&&&&%%%%%$$$$$$$$$$$$$$%%%%%&&&'''(())*++,-.---,,+++******))))))))******+++,,,--../00123456789;<>@BDB?=;975321/.-,++*)((''&&%%%$$$#######\"\"\"\"\"\"\"#######$$$%%%&&'''&&%%$$##\"\"!!!!     \037\037\037\037\037\037\037\037\037\037\037\037\037\037\037     !!!!\"\"###$$%&&'())*+-./023579;><:97643210/.-,,+**))(('&&&%%%$$$$$$$#######$$$$$$$%%%%&&&''(())*++,-.--,,,+++********))********++++,,--..//0112345678:;=>@BDB@=;9754210/--,+*))(''&&%%%$$$$########\"\"\"########$$$$%%%&&'''&%%$$##\"\"!!!    \037\037\037\037\037\037\037\037\037\036\036\036\037\037\037\037\037\037\037\037\037    !!!\"\"\"##$%%&&'()*+,-.013468;=;98653210/.-,,+**)((''&&%%%$$$$#################$$$$%%%%&&''(())*++,---,,,++++****************++++,,,--..//0122345679:;=>@BDC@=;9754210/.-,+*))((''&&%%%$$$$#################$$$$%%%&&'''&%%$$##\"\"!!!   \037\037\037\037\037\037\036\036\036\036\036\036\036\036\036\036\036\036\036\036\037\037\037\037\037    !!\"\"\"##$%%&''()*+,./02468:<:8754310/.-,,+**)((''&%%%$$$$######\"\"\"\"\"\"\"\"\"\"######$$$%%%&&&''())*++,---,,,++++***************++++,,---../00122345679:;=?@BDC@>;9764310/.-,+**)((''&&%%%$$$$#################$$$$%%%&&''&&%$$##\"\"!!   \037\037\037\037\037\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\037\037\037\037\037   !!!\"\"##$%%&'()*+,-.013579;9764321/.--,+*))((''&&%$$$#####\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"####$$$%%%&&''())*+,---,,,++++**************++++,,,--..//00123445789:<=?ACEC@><9864310/.-,+**)((''&&&%%%$$$$###############$$$$%%%&&&''&%$$##\"\"!!   \037\037\037\037\036\036\036\036\036\036\035\035\035\035\035\035\035\035\035\035\035\035\036\036\036\036\036\036\037\037\037   !!!\"\"#$$%&&'()*+,./02468:8653210/.-,+*))(('&&&%$$####\"\"\"\"\"!!!!!!!!!!!!!\"\"\"\"\"####$$$%%&&''())*+,---,,,++++************+++++,,,--..//01123456789:<=?ACECA><:864320/.-,++*))(''&&&%%%$$$$$#############$$$$$%%%&&&'&%$$##\"\"!!  \037\037\037\037\036\036\036\036\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\036\036\036\036\036\037\037\037  !!!\"\"#$$%&''()*,-.013579754310/.-,+*))(''&&%%$####\"\"\"\"!!!!!!!!!!!!!!!!!!!\"\"\"\"\"###$$%%&&''())*+,--,,,++++++*********+++++,,,---..//01123456789;<>?ACEDA><:865321/.-,,+*))((''&&%%%%$$$$$###########$$$$$%%%%&&'&%%$##\"\"!!  \037\037\037\036\036\036\036\035\035\035\035\035\035\035\034\034\034\034\034\034\034\034\034\035\035\035\035\035\035\035\036\036\036\036\037\037\037   !!\"\"#$$%&'()*+,./1246865320/.-,+**)(''&&%%$$##\"\"\"!!!!!               !!!!!\"\"\"###$$%%&&''()**+,-,,,,++++++******++++++,,,,--..//00122345678:;<>@ACEDA?<:8653210..-,+**)((''&&&%%%$$$$$$#########$$$$$$%%%&&&'&%$##\"\"!!  \037\037\037\036\036\036\035\035\035\035\035\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\035\035\035\035\035\036\036\036\036\037\037   !!\"##$%%&'()*,-.0246754210.-,+**)(''&&%%$$#\"\"\"!!!!          \037          !!!!\"\"\"###$$%%&''()*+,--,,,,+++++++++++++++++,,,---..//00123345679:;=>@BDFDA?<:8754210/.-,+**)((''&&&%%%%$$$$$$#######$$$$$$%%%%&&&&%$$#\"\"!!  \037\037\036\036\036\035\035\035\035\035\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\035\035\035\035\036\036\036\037\037\037  !!\"##$%&'()*+,./13564310/.-,+*)(''&%%$$###\"!!!!     \037\037\037\037\037\037\037\037\037\037\037\037\037\037\037     !!!!\"\"\"##$$%%&''()*+,-,,,,++++++++++++++++,,,,---..//01123445689:;=>@BDFDB?=:9754210/.-,++*))((''&&&%%%$$$$$$$$$#$$$$$$$$$%%%%&&&%%$#\"\"!!  \037\037\036\036\036\035\035\035\035\034\034\034\034\034\034\033\033\033\033\033\033\033\033\033\033\033\033\033\033\034\034\034\034\034\034\035\035\035\036\036\036\037\037\037  !\"\"#$$%&'()*,-.0245321/.-,+*)(''&%%$$##\"\"!!     \037\037\037\037\037\037\037\037\036\036\036\036\036\037\037\037\037\037\037\037\037     !!!\"\"##$$%%&'(()*+,-,,,,++++++++++++++,,,,---..//001123456789:<=?@BDFEB?=;9754310/.-,++*))((''&&&%%%%$$$$$$$$$$$$$$$$$%%%%&&&&%$#\"\"!!  \037\037\036\036\036\035\035\035\034\034\034\034\034\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\034\034\034\034\035\035\035\035\036\036\037\037\037  !\"\"#$%%&'(*+,./134310.-,+*)(''&%%$$##\"\"!!   \037\037\037\037\037\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\037\037\037\037\037   !!!\"\"##$$%%&'())*,-,,,,,++++++++++++,,,,,---..//001223456789:<=?@BDFEB?=;9764310/.-,,+*))((''&&&%%%%$$$$$$$$$$$$$$$$$%%%%&&&%$##\"!!  \037\037\036\036\035\035\035\034\034\034\034\033\033\033\033\033\033\033\032\032\032\032\032\032\032\032\032\032\032\033\033\033\033\033\033\033\034\034\034\034\035\035\035\036\036\036\037\037  !\"\"#$%&'()*,-/02320/.,+*)(('&%%$$##\"\"!!  \037\037\037\037\036\036\036\036\036\036\036\036\035\035\035\035\035\035\035\036\036\036\036\036\036\036\036\037\037\037\037   !!\"\"\"#$$%&&'()*+,-,,,,,++++++++++,,,,,---...//001233456789;<=?ACEGEB@=;9764320/.--,+**)(('''&&&%%%%$$$$$$$$$$$$$$$%%%%&&&%%$#\"!!  \037\037\036\036\035\035\035\034\034\034\033\033\033\033\033\033\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\033\033\033\033\033\034\034\034\035\035\035\036\036\036\037\037 !!\"##$%&'(*+,.0221/.-,+*)('&%%$$#\"\"!!! \037\037\037\037\036\036\036\036\036\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\035\036\036\036\036\036\037\037\037\037  !!!\"\"#$$%&&'()*+,-,,,,,++++++++,,,,,,---..//001123345678:;<>?ACEGEB@=;9864321/..-,+**))((''&&&%%%%%$$$$$$$$$$$$$%%%%%&&&%$#\"\"!  \037\037\036\036\035\035\035\034\034\034\033\033\033\033\032\032\032\032\032\032\032\032\032\031\031\031\031\031\032\032\032\032\032\032\032\032\032\033\033\033\033\033\034\034\034\035\035\036\036\036\037\037 !!\"#$%&'()*,-/110/-,+*)('&%%$##\"\"!!   \037\037\036\036\036\036\035\035\035\035\035\035\035\034\034\034\034\034\034\034\034\035\035\035\035\035\035\035\035\036\036\036\036\037\037\037   !!\"\"#$$%&''()*,-,,,,,,,++++,,,,,,,---...//001123455679:;<>?ACEGFC@><:8653210/.-,++*))(('''&&&%%%%%$$$$$$$$$$$%%%%%&&&%$##\"!  \037\037\036\036\035\035\034\034\034\033\033\033\033\032\032\032\032\032\032\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\032\032\032\032\032\032\033\033\033\033\034\034\034\035\035\035\036\036\037  !\"\"#$%&'(*+,.01/.,+*)('&&%$##\"\"!!   \037\036\036\036\035\035\035\035\035\035\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\034\035\035\035\035\035\036\036\036\036\037\037\037  !!\"\"#$$%&'()*+,-,,,,,,,,,,,,,,,,----...//001223456789:;=>@ACEGFC@><:8653210/.-,++*))(('''&&&%%%%%%$$$$$$$$$%%%%%%&&&%$#\"!! \037\037\036\036\035\035\034\034\034\033\033\033\032\032\032\032\032\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\032\032\032\032\032\033\033\033\033\034\034\035\035\035\036\036\037  !\"##$%&()*,-/0.-,*)('&&%$##\"\"!!  \037\037\037\036\035\035\035\035\035\034\034\034\034\034\034\034\033\033\033\033\033\033\033\033\034\034\034\034\034\034\034\034\035\035\035\035\036\036\036\037\037\037  !!\"\"#$$%&'()*+--,,,,,,,,,,,,,,,----..//0011223456789:;=>@BCEHFCA><:8654210/.-,,+**)(((''&&&&%%%%%%$$$$$$$%%%%%%&&&%$#\"!! \037\037\036\036\035\035\034\034\034\033\033\033\032\032\032\032\031\031\031\031\031\031\031\030\030\030\030\030\030\030\030\030\030\030\031\031\031\031\031\031\031\031\032\032\032\032\033\033\033\034\034\035\035\035\036\037\037  !\"#$%&'(*+-./-,+*)('&%$##\"\"!!  \037\037\036\036\035\035\035\034\034\034\034\034\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\033\034\034\034\034\034\034\035\035\035\036\036\036\037\037  !!\"\"#$%%&'()+,-,,,,,,,,,,,,,,----...//0011233456789:<=>@BDFHFCA><:8754210/.-,,+**))(('''&&&%%%%%%%%%$$%%%%%%%%&&&$##\"! \037\037\036\036\035\035\034\034\033\033\033\032\032\032\032\031\031\031\031\031\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\031\031\031\031\031\031\032\032\032\033\033\033\034\034\035\035\035\036\037\037 !!\"#$%&')*,..-+*)('&%$$#\"\"!  \037\037\037\036\036\036\035\034\034\034\034\033\033\033\033\033\033\033\033\032\032\032\032\032\032\032\032\033\033\033\033\033\033\033\033\034\034\034\034\034\035\035\035\036\036\037\037  !!\"\"#$%&'()*+--,,,,,,,,,,,,-----..///0011234456789:<=?@BDFHGDA?<:9754310/.--,+**))(('''&&&&%%%%%%%%%%%%%%%%%&&&%$#\"!  \037\036\036\035\035\034\034\033\033\033\032\032\032\031\031\031\031\031\030\030\030\030\030\030\030\030\030\030\027\027\027\027\030\030\030\030\030\030\030\030\030\030\031\031\031\031\032\032\032\032\033\033\034\034\035\035\036\036\037\037 !\"##%&'(*+--,*)('&%$$#\"\"!  \037\037\036\036\036\035\035\034\034\034\033\033\033\033\033\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\032\033\033\033\033\033\033\034\034\034\035\035\035\036\036\036\037\037 !!\"##$%&'()*,--,,,,,,,,,,-----...//00012234556789;<=?ABDFHGDA?=;9764320/..-,++*))(((''&&&&&%%%%%%%%%%%%%%%%&&%$#\"!! \037\036\036\035\035\034\034\033\033\033\032\032\032\031\031\031\031\030\030\030\030\030\030\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\030\030\030\030\030\030\030\031\031\031\031\032\032\032\033\033\034\034\035\035\036\036\037  !\"#$%&')*,,+*)'&%%$#\"\"!  \037\037\036\036\035\035\035\034\034\033\033\033\033\032\032\032\032\032\032\032\032\032\031\031\031\031\031\031\031\032\032\032\032\032\032\032\032\033\033\033\033\033\034\034\034\035\035\035\036\036\037\037 !!\"##$%&'(*+,---,,,,,,,-----....//0011223456679:;<>?ABDFIGDA?=;97643210/.-,++**))(('''&&&&%%%%%%%%%%%%%%%&&&%$#\"! \037\037\036\035\035\034\034\033\033\032\032\032\031\031\031\031\030\030\030\030\030\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\030\030\030\030\030\031\031\031\032\032\032\033\033\034\034\035\035\036\036\037 !!\"#$&'(*+,*)('&%$#\"\"!  \037\037\036\036\035\035\035\034\034\033\033\033\032\032\032\032\032\032\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\031\032\032\032\032\032\032\033\033\033\034\034\034\035\035\035\036\036\037\037 !!\"#$%&'()*,-----,,--------...///0011233456789:;<>?ACEGIGDB?=;97643210/.-,,+**))(('''&&&&&%%%%%%%%%%%%%&&&%$#\"!  \037\036\035\035\034\034\033\033\032\032\032\031\031\031\030\030\030\030\030\027\027\027\027\027\027\027\026\026\026\026\026\026\026\026\026\026\026\027\027\027\027\027\027\027\027\030\030\030\030\031\031\031\031\032\032\033\033\034\034\035\035\036\036\037 !\"#$%&')++)('&%$#\"\"!  \037\037\036\036\035\035\034\034\034\033\033\032\032\032\032\031\031\031\031\031\031\031\031\031\031\030\030\030\030\031\031\031\031\031\031\031\031\031\031\032\032\032\032\032\033\033\033\034\034\034\035\035\036\036\037\037 !!\"#$%&'()+,-------------....//00012233456789:;=>@ACEGIHEB?=;98653210/.-,,+**))((('''&&&&&%%%%%%%%%%%&&&&%$#\"! \037\036\036\035\034\034\033\033\032\032\032\031\031\031\030\030\030\030\027\027\027\027\027\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\027\027\027\027\027\027\030\030\030\030\031\031\031\032\032\033\033\034\034\035\035\036\037\037 !\"#$%'(**)'&%$#\"\"!  \037\036\036\035\035\035\034\034\033\033\033\032\032\032\031\031\031\031\031\031\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\031\031\031\031\031\031\032\032\032\033\033\033\034\034\034\035\035\036\036\037\037 !\"\"#$%&')*,------------....///00112234456789:;=>@ACEGIHEB@=;98653210/.--,++*))((('''&&&&&&%%%%%%%%%&&&&%$#\"! \037\036\036\035\034\034\033\033\032\032\031\031\031\030\030\030\030\027\027\027\027\027\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\027\027\027\027\027\030\030\030\031\031\031\032\032\033\033\034\034\035\035\036\037 !!\"$%&())('&$$#\"!  \037\036\036\035\035\034\034\034\033\033\033\032\032\031\031\031\031\031\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\030\031\031\031\031\031\032\032\032\033\033\033\034\034\035\035\036\036\037\037 !\"##$&'()+,-----------....///00112234556789:<=>@BCEGJHEB@><:8654210/.--,++**))((''''&&&&&&%%%%%%%&&&&&%#\"! \037\037\036\035\035\034\033\033\032\032\031\031\031\030\030\030\027\027\027\027\026\026\026\026\026\026\026\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\026\026\026\026\026\026\026\027\027\027\027\030\030\030\031\031\031\032\032\033\033\034\034\035\036\036\037 !\"#$%'(('&%$#\"!  \037\036\036\035\035\034\034\034\033\033\032\032\032\031\031\031\030\030\030\030\030\030\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\030\030\030\030\030\030\031\031\031\031\032\032\032\032\033\033\034\034\035\035\036\036\037  !\"#$%&')*,----------....///000112334566789;<=?@BDEHJHEB@><:8754310/..-,++**))((('''&&&&&&&&%%%&&&&&&%$#\"! \037\036\035\035\034\033\033\032\032\031\031\031\030\030\030\027\027\027\027\026\026\026\026\026\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\026\026\026\026\026\026\027\027\027\027\030\030\030\031\031\032\032\033\033\034\034\035\036\037\037 !\"#%&('&%$#\"!! \037\036\036\035\035\034\034\033\033\033\032\032\032\031\031\030\030\030\030\030\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\027\030\030\030\030\030\031\031\031\032\032\032\033\033\034\034\035\035\036\036\037  !\"#$%&()+,--------.....///00112233456789:;<=?@BDFHJHEC@><:87543210/.-,,+**))(((''''&&&&&&&&&&&&&&&&%#\"! \037\036\036\035\034\034\033\032\032\031\031\031\030\030\027\027\027\027\026\026\026\026\026\025\025\025\025\025\025\025\025\025\024\024\024\024\024\024\024\024\025\025\025\025\025\025\025\025\025\026\026\026\026\026\027\027\027\030\030\030\031\031\032\032\033\033\034\035\035\036\037 !\"#$%''%$#\"!! \037\036\036\035\035\034\034\033\033\032\032\032\031\031\031\030\030\030\027\027\027\027\027\027\027\026\026\026\026\026\026\026\026\026\026\026\026\026\026\027\027\027\027\027\027\027\030\030\030\030\031\031\031\031\032\032\033\033\033\034\035\035\036\036\037 !!\"#%&'(*,-------.....////00112234456789:;<>?ABDFHJIFC@><:87543210/.-,,++*)))((('''&&&&&&&&&&&&&&&%$#\"! \037\036\035\034\034\033\032\032\031\031\031\030\030\027\027\027\026\026\026\026\025\025\025\025\025\025\025\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\025\025\025\025\025\025\025\026\026\026\026\027\027\027\030\030\030\031\031\032\032\033\033\034\035\035\036\037 !\"#%&&%$#\"! \037\037\036\035\035\034\034\033\033\032\032\032\031\031\031\030\030\027\027\027\027\027\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\026\027\027\027\027\027\030\030\030\030\031\031\031\032\032\032\033\033\034\035\035\036\036\037 !\"#$%&()+-.---.......///000112234456789:;<>?ABDFHKIFCA><:97643210/.--,++**))(((''''&&&&&&&&&&&&&&%#\"! \037\036\035\034\034\033\033\032\031\031\030\030\030\027\027\027\026\026\026\026\025\025\025\025\025\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\025\025\025\025\025\026\026\026\026\027\027\027\030\030\031\031\032\032\033\033\034\035\036\037\037 \"#$%%$#\"! \037\037\036\035\035\034\034\033\033\032\032\031\031\031\030\030\030\027\027\027\026\026\026\026\026\026\026\026\025\025\025\025\025\025\025\025\025\025\025\025\026\026\026\026\026\026\026\026\027\027\027\027\030\030\030\030\031\031\032\032\032\033\033\034\035\035\036\037\037 !\"#$&'(*,..........////001122334556789:;=>?ACDFHKIFCA?<;97643210/.--,++**))((('''''&&&&&&&&&&&&%$#! \037\036\036\035\034\033\033\032\031\031\030\030\030\027\027\027\026\026\026\025\025\025\025\025\024\024\024\024\024\024\024\024\023\023\023\023\023\023\023\023\023\023\023\023\024\024\024\024\024\024\024\024\025\025\025\025\025\026\026\026\027\027\027\030\030\031\031\032\032\033\034\034\035\036\037 !\"#%$#\"! \037\037\036\035\035\034\033\033\032\032\032\031\031\031\030\030\030\027\027\026\026\026\026\026\026\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\025\026\026\026\026\026\026\027\027\027\027\030\030\030\031\031\031\032\032\033\033\034\035\035\036\037  !\"$%&()+-........////0001122334566789:<=>@ACEGIKIFDA?=;97653210/..-,,+**)))(((''''&&&&&&&&&&&&%#\"! \037\036\035\034\033\033\032\032\031\030\030\030\027\027\026\026\026\025\025\025\025\025\024\024\024\024\024\024\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\024\024\024\024\024\024\025\025\025\025\026\026\026\027\027\027\030\030\031\031\032\032\033\034\035\035\036\037 !#$$#!! \037\036\035\035\034\033\033\032\032\031\031\031\030\030\030\027\027\027\026\026\026\026\025\025\025\025\025\025\025\025\025\025\024\024\024\024\024\024\024\025\025\025\025\025\025\025\025\025\026\026\026\026\026\027\027\027\027\030\030\031\031\031\032\032\033\033\034\035\035\036\037 !\"#$%'(*,........////0001122344567789;<=>@ACEGIKJGDA?=;98654210//.-,,++**))((('''''&&&&&&&&&&&$#! \037\036\035\034\034\033\032\032\031\031\030\030\027\027\026\026\026\025\025\025\025\024\024\024\024\024\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\024\024\024\024\024\025\025\025\025\026\026\027\027\027\030\030\031\031\032\033\033\034\035\036\037 !\"##\"! \037\036\035\035\034\033\033\032\032\031\031\031\030\030\027\027\027\027\026\026\025\025\025\025\025\025\025\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\025\025\025\025\025\025\025\026\026\026\026\027\027\027\030\030\030\031\031\032\032\033\033\034\035\035\036\037 !\"#%&()+-......////0001112334456789:;<=?@BCEGIKJGDA?=;986543100/.-,,++**))(((('''''&&&&&&&&&%#\"! \037\036\035\034\033\032\032\031\031\030\030\027\027\026\026\026\025\025\025\024\024\024\024\024\023\023\023\023\023\023\023\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\023\023\023\023\023\023\023\023\024\024\024\024\025\025\025\025\026\026\026\027\027\030\030\031\031\032\033\033\034\035\036\037 !#\"! \037\036\036\035\034\033\033\032\032\031\031\030\030\030\027\027\027\026\026\026\025\025\025\025\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\024\025\025\025\025\025\026\026\026\026\027\027\030\030\030\031\031\032\032\033\033\034\035\036\037\037 !#$%'(*,...../////0001122334556789:;<=?@BCEGILJGDB?=;:86543210/.--,++**)))((('''''''&&&&&'&$#! \037\036\035\034\033\033\032\031\031\030\030\027\027\026\026\025\025\025\025\024\024\024\024\023\023\023\023\023\023\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\023\023\023\023\023\023\024\024\024\024\025\025\025\026\026\026\027\027\030\030\031\031\032\033\034\034\035\036\037!\"! \037\036\036\035\034\033\033\032\032\031\031\030\030\030\027\027\026\026\026\026\025\025\025\024\024\024\024\024\024\024\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\024\024\024\024\024\024\024\025\025\025\025\025\026\026\026\027\027\027\030\030\031\031\032\032\033\034\034\035\036\037 !\"#$&()+-.../////00011122334556789:;<>?@BDEGJLJGDB@=;:87543210/.--,++**)))((((''''''''''''%#\"!\037\036\035\034\034\033\032\031\031\030\030\027\027\026\026\025\025\025\024\024\024\024\023\023\023\023\023\022\022\022\022\022\022\022\022\022\022\022\022\022\021\021\022\022\022\022\022\022\022\022\022\022\022\022\023\023\023\023\023\023\024\024\024\025\025\025\026\026\026\027\027\030\030\031\032\032\033\034\035\036\037 !! \037\036\035\034\034\033\032\032\031\031\030\030\027\027\027\026\026\026\025\025\025\024\024\024\024\024\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\024\024\024\024\024\025\025\025\025\026\026\026\027\027\027\030\030\031\031\032\032\033\034\034\035\036\037 !\"$%'(*-.///////00011122344566789:;<>?ABDFHJLJGEB@><:87543210/..-,,++**))(((('''''''''''&$#! \037\036\035\034\033\032\031\031\030\030\027\027\026\026\025\025\025\024\024\024\023\023\023\023\023\022\022\022\022\022\022\022\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\022\022\022\022\022\022\022\022\023\023\023\023\024\024\024\024\025\025\025\026\026\027\027\030\030\031\032\033\033\034\035\036\037! \037\036\035\034\034\033\032\032\031\031\030\030\027\027\026\026\026\026\025\025\025\025\024\024\023\023\023\023\023\023\023\023\023\022\022\022\022\022\022\022\022\022\022\022\022\023\023\023\023\023\023\023\023\023\024\024\024\024\025\025\025\025\026\026\026\027\027\030\030\031\031\032\032\033\034\035\035\036\037 \"#$&(*,.//////000011223344567789:;=>?ABDFHJJHFEB@><:87643210/..-,,++**)))((((''''''''''%$\"!\037\036\035\034\033\032\032\031\030\030\027\027\026\026\025\025\025\024\024\024\023\023\023\023\022\022\022\022\022\022\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\022\022\022\022\022\022\023\023\023\023\024\024\024\025\025\025\026\026\027\027\030\031\031\032\033\034\035\036\037 \037\036\035\035\034\033\032\032\031\031\030\030\027\027\026\026\026\025\025\025\025\024\024\023\023\023\023\023\023\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\023\023\023\023\023\023\024\024\024\024\025\025\025\026\026\026\027\027\030\030\031\031\032\032\033\034\035\036\037 !\"$%')+-//////000111223345567889:<=>?ACDFHJHFECA@><:97643210//.-,,++**)))(((('''''''''&$#! \037\035\034\034\033\032\031\030\030\027\027\026\026\025\025\024\024\024\023\023\023\023\022\022\022\022\022\021\021\021\021\021\021\021\021\021\021\021\020\020\020\020\020\020\020\021\021\021\021\021\021\021\021\021\021\021\022\022\022\022\022\023\023\023\023\024\024\024\025\025\025\026\026\027\027\030\031\031\032\033\034\035\036\037\037\036\035\034\033\032\032\031\031\030\030\027\027\026\026\025\025\025\025\024\024\024\024\023\023\023\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\023\023\023\023\023\024\024\024\024\025\025\025\026\026\027\027\030\030\031\031\032\033\033\034\035\036\037 !#$&(*,/////000011122334556789:;<=>@ACDFHIFDCA@>=<:97653210//.--,++***)))((((''''''''&$\"!\037\036\035\034\033\032\031\031\030\027\027\026\026\025\025\024\024\024\023\023\023\022\022\022\022\022\021\021\021\021\021\021\021\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\021\021\021\021\021\021\021\021\022\022\022\022\023\023\023\023\024\024\025\025\025\026\026\027\030\030\031\032\032\033\034\035\036\036\035\034\033\032\032\031\031\030\027\027\027\026\026\025\025\025\024\024\024\024\023\023\023\022\022\022\022\022\022\022\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\022\022\022\022\022\022\022\023\023\023\023\023\024\024\024\025\025\025\026\026\027\027\030\030\031\031\032\033\034\034\035\036\037!\"$%')+.///0000111223344566789:;<=>@ACEFIFDCA?>=<;:976542110/.--,,++**)))((((('''''''%#! \037\035\034\033\032\032\031\030\027\027\026\026\025\025\024\024\024\023\023\023\022\022\022\022\021\021\021\021\021\021\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\021\021\021\021\021\021\021\022\022\022\022\023\023\023\024\024\025\025\025\026\026\027\030\030\031\032\033\034\035\036\035\034\033\033\032\031\031\030\027\027\026\026\026\025\025\025\024\024\024\023\023\023\023\022\022\022\022\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\022\022\022\022\022\023\023\023\023\023\024\024\024\025\025\026\026\026\027\030\030\031\031\032\033\034\035\036\037 !#$&(*-//00000111223344566789:;<=?@ACEGGECA?><;:98766543210/.--,,++**)))(((((('''''&$\"!\037\036\035\034\033\032\031\030\030\027\026\026\025\025\024\024\024\023\023\023\022\022\022\021\021\021\021\021\021\020\020\020\020\020\020\020\020\020\020\017\017\017\017\017\017\017\017\017\017\020\020\020\020\020\020\020\020\020\020\021\021\021\021\021\022\022\022\022\023\023\023\024\024\024\025\025\026\027\027\030\030\031\032\033\034\035\034\034\033\032\031\031\030\027\027\026\026\025\025\025\024\024\024\023\023\023\023\022\022\022\021\021\021\021\021\021\021\021\021\020\020\020\020\020\020\020\020\020\020\020\020\020\021\021\021\021\021\021\021\021\021\022\022\022\022\022\023\023\023\023\024\024\024\025\025\025\026\026\027\030\030\031\032\032\033\034\035\036\037!\"#%'),.000001112223344567789:;<=?@BCEGECA?=<;:877654433210/..-,,++***)))((((((''''%#! \036\035\034\033\032\031\030\030\027\026\026\025\025\024\024\023\023\023\022\022\022\022\021\021\021\021\020\020\020\020\020\020\020\020\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\020\020\020\020\020\020\020\021\021\021\021\021\022\022\022\023\023\023\024\024\024\025\025\026\027\027\030\031\032\032\033\034\034\033\032\031\031\030\027\027\026\026\025\025\025\024\024\024\023\023\023\022\022\022\022\021\021\021\021\021\021\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\021\021\021\021\021\021\021\022\022\022\022\023\023\023\023\024\024\025\025\025\026\026\027\030\030\031\032\032\033\034\035\037 !#$&(+-000001112223345567889:;<>?@BCEECA?=<:98765443221110/..-,,++***)))(((((((''&$\"!\037\036\035\033\032\031\031\030\027\026\026\025\025\024\024\023\023\023\022\022\022\021\021\021\021\020\020\020\020\020\020\020\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\020\020\020\020\020\020\021\021\021\021\022\022\022\022\023\023\024\024\024\025\025\026\027\027\030\031\032\033\034\033\032\031\031\030\027\027\026\026\025\025\024\024\024\023\023\023\023\022\022\022\022\021\021\021\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\021\021\021\021\021\021\022\022\022\022\023\023\023\024\024\024\025\025\026\026\027\030\030\031\032\033\034\035\036\037 \"#%'*,/00011112233445567899:;=>?ABDECA?=;:9765443221000///..--,,++**))))((((((((%#! \036\035\034\033\032\031\030\027\027\026\025\025\024\024\023\023\023\022\022\022\021\021\021\021\020\020\020\020\020\017\017\017\017\017\017\017\017\017\017\016\016\016\016\016\016\016\016\016\016\016\017\017\017\017\017\017\017\017\017\017\017\020\020\020\020\020\021\021\021\021\022\022\022\023\023\024\024\024\025\026\026\027\030\030\031\032\033\032\032\031\030\027\027\026\026\025\025\024\024\024\023\023\023\022\022\022\022\021\021\021\020\020\020\020\020\020\020\020\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\020\020\020\020\020\020\020\020\021\021\021\021\021\022\022\022\023\023\023\024\024\024\025\025\026\026\027\030\030\031\032\033\034\035\036 !#$&)+.0001112223344566789:;<=>?ABDCA?=;:87654321100//...-----,,++***)))((((((('%#!\037\036\034\033\032\031\030\027\027\026\025\025\024\024\023\023\022\022\022\021\021\021\021\020\020\020\020\020\017\017\017\017\017\017\017\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\017\017\017\017\017\017\017\017\020\020\020\020\020\021\021\021\022\022\022\023\023\023\024\024\025\026\026\027\030\031\031\032\032\031\030\027\027\026\026\025\025\024\024\023\023\023\022\022\022\022\021\021\021\021\021\020\020\020\020\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\020\020\020\020\020\020\021\021\021\021\021\022\022\022\023\023\023\024\024\025\025\026\026\027\030\031\031\032\033\034\036\037 \"$&(*-0011112223344566789:;<=>@ABDA?=;9875432210//..---,,,,,+++++***))))((((((&$\" \036\035\034\033\031\031\030\027\026\026\025\024\024\023\023\022\022\022\021\021\021\020\020\020\020\020\017\017\017\017\017\017\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\017\017\017\017\017\017\020\020\020\020\021\021\021\021\022\022\023\023\023\024\025\025\026\026\027\030\031\032\031\030\030\027\026\026\025\025\024\024\023\023\023\022\022\022\021\021\021\021\020\020\020\020\017\017\017\017\017\017\017\017\017\017\016\016\016\016\016\016\016\016\016\016\016\017\017\017\017\017\017\017\017\017\017\017\020\020\020\020\020\021\021\021\021\022\022\022\023\023\023\024\024\025\025\026\027\027\030\031\032\033\034\035\036\037!#%'),/111122233445567789:;<=>@ACA?=;986543210//..--,,++++**********)))))(((((%#!\037\036\034\033\032\031\030\027\026\026\025\024\024\023\023\022\022\022\021\021\021\020\020\020\020\017\017\017\017\017\016\016\016\016\016\016\016\016\016\016\016\r\r\r\r\r\r\r\r\r\r\r\r\016\016\016\016\016\016\016\016\016\016\017\017\017\017\017\017\020\020\020\020\021\021\021\022\022\023\023\023\024\025\025\026\027\027\030\031\030\030\027\026\026\025\025\024\024\023\023\022\022\022\021\021\021\021\020\020\020\020\020\017\017\017\017\017\017\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\017\017\017\017\017\017\017\020\020\020\020\020\021\021\021\021\022\022\022\023\023\024\024\025\025\026\027\027\030\031\032\033\034\035\037 \"$&(+.111122233445567789:;<=?@AB?=;986532100/.--,,+++***))))))))))))))))(((('$\" \036\035\034\032\031\030\027\027\026\025\024\024\023\023\022\022\022\021\021\020\020\020\020\017\017\017\017\017\016\016\016\016\016\016\016\016\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\016\016\016\016\016\016\016\016\017\017\017\017\017\020\020\020\021\021\021\022\022\023\023\024\024\025\025\026\027\030\031\030\027\026\026\025\025\024\024\023\023\022\022\022\021\021\021\020\020\020\020\020\017\017\017\017\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\017\017\017\017\017\017\017\020\020\020\020\021\021\021\022\022\022\023\023\024\024\025\025\026\027\030\030\031\032\033\035\036\037!#%'*-011122233445667889:;<=?@A@=;97643210/.--,++***)))((((((((((((((()))(((&#!\037\036\034\033\032\031\030\027\026\025\025\024\023\023\022\022\021\021\021\020\020\020\020\017\017\017\017\016\016\016\016\016\016\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\016\016\016\016\016\016\016\017\017\017\017\020\020\020\021\021\021\022\022\023\023\024\024\025\025\026\027\030\027\026\026\025\025\024\023\023\023\022\022\021\021\021\021\020\020\020\020\017\017\017\017\016\016\016\016\016\016\016\016\016\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\016\016\016\016\016\016\016\016\016\017\017\017\017\017\017\020\020\020\021\021\021\022\022\022\023\023\024\024\025\025\026\027\030\031\032\033\034\035\036 \"$&),/11222333445667899:;<>?@@>;9764310/..-,++**))(((''''''''&&'''''''(((('%\" \036\035\033\032\031\030\027\026\025\025\024\023\023\022\022\021\021\021\020\020\020\017\017\017\017\016\016\016\016\016\016\r\r\r\r\r\r\r\r\r\r\r\f\f\f\f\f\f\f\f\f\f\f\f\r\r\r\r\r\r\r\r\r\r\r\016\016\016\016\016\016\017\017\017\017\020\020\020\021\021\022\022\023\023\024\024\025\026\026\027\026\026\025\025\024\023\023\023\022\022\021\021\021\020\020\020\020\017\017\017\017\017\016\016\016\016\016\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\016\016\016\016\016\016\016\016\017\017\017\017\020\020\020\020\021\021\021\022\022\023\023\024\024\025\026\026\027\030\031\032\033\034\036\037!#%(+.1122233445566789:;<=>?@>;9754210/.-,++*))((('''&&&&&%%%%%%%&&&&&&'''&$!\037\036\034\033\031\030\027\026\026\025\024\023\023\022\022\021\021\021\020\020\020\017\017\017\016\016\016\016\016\r\r\r\r\r\r\r\r\r\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\r\r\r\r\r\r\r\r\016\016\016\016\016\017\017\017\017\020\020\020\021\021\022\022\023\023\024\024\025\026\027\026\025\025\024\023\023\022\022\022\021\021\021\020\020\020\017\017\017\017\017\016\016\016\016\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\016\016\016\016\016\016\017\017\017\017\020\020\020\021\021\021\022\022\023\023\024\024\025\026\026\027\030\031\032\033\035\036 \"$'*-1222333445567789:;<=>??<9754210.-,,+*))((''&&&%%%%%$$$$$$$$$$%%%%&&&%# \036\035\033\032\031\030\027\026\025\024\024\023\022\022\021\021\021\020\020\017\017\017\017\016\016\016\016\r\r\r\r\r\r\r\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\r\r\r\r\r\r\016\016\016\016\016\017\017\017\020\020\020\021\021\022\022\023\023\024\024\025\026\025\025\024\023\023\022\022\021\021\021\020\020\020\017\017\017\017\017\016\016\016\016\016\r\r\r\r\r\r\r\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\r\r\r\r\r\r\r\r\r\016\016\016\016\016\017\017\017\017\020\020\020\021\021\022\022\023\023\024\024\025\026\027\027\030\032\033\034\036\037!#&(,/222333445567789:;<=>?<:75420/.-,+*))(''&&%%%$$$$$############$$$$%%$\"\037\036\034\032\031\030\027\026\025\024\024\023\022\022\021\021\020\020\020\017\017\017\016\016\016\016\r\r\r\r\r\r\f\f\f\f\f\f\f\f\f\f\f\f\013\013\013\013\013\013\013\013\013\013\013\f\f\f\f\f\f\f\f\f\f\f\f\r\r\r\r\r\r\016\016\016\016\017\017\017\017\020\020\021\021\022\022\023\023\024\025\025\025\024\023\023\022\022\021\021\021\020\020\020\017\017\017\017\016\016\016\016\016\r\r\r\r\r\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\r\r\r\r\r\r\r\016\016\016\016\016\017\017\017\020\020\020\021\021\022\022\023\023\024\024\025\026\027\030\031\032\033\035\036 \"%'*.222334455667889:;<=>=:85420/.,+**)(''&&%%$$$####\"\"\"\"\"\"\"\"\"\"\"\"\"\"####$#!\037\035\033\032\030\027\026\025\025\024\023\022\022\021\021\020\020\020\017\017\017\016\016\016\016\r\r\r\r\r\f\f\f\f\f\f\f\f\f\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\f\f\f\f\f\f\f\f\f\r\r\r\r\r\016\016\016\016\017\017\017\020\020\021\021\022\022\023\023\024\025\024\023\023\022\022\021\021\020\020\020\017\017\017\017\016\016\016\016\r\r\r\r\r\f\f\f\f\f\f\f\f\f\f\f\f\013\013\013\013\013\013\013\013\013\f\f\f\f\f\f\f\f\f\f\f\f\f\r\r\r\r\r\r\016\016\016\016\017\017\017\017\020\020\021\021\021\022\023\023\024\024\025\026\027\030\031\032\034\035\037!$&)-123334455667899:;<=>;86320/-,+*)(''&%%$$###\"\"\"\"!!!!!!!!!!!!!!!\"\"\"\"#\" \036\034\032\031\030\027\026\025\024\023\023\022\021\021\020\020\020\017\017\016\016\016\016\r\r\r\r\r\f\f\f\f\f\f\f\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\f\f\f\f\f\f\f\r\r\r\r\r\016\016\016\017\017\017\020\020\021\021\022\022\023\023\024\023\023\022\022\021\021\020\020\020\017\017\017\016\016\016\016\r\r\r\r\r\r\f\f\f\f\f\f\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\f\f\f\f\f\f\f\f\f\r\r\r\r\r\016\016\016\016\017\017\017\020\020\020\021\021\022\023\023\024\025\025\026\027\030\032\033\035\036 \"%(,02333445567789::;<=;86420.-,*)(('&%%$$##\"\"\"!!!!                !!!!!\037\035\033\032\030\027\026\025\024\023\023\022\021\021\020\020\017\017\017\016\016\016\r\r\r\r\r\f\f\f\f\f\f\013\013\013\013\013\013\013\013\013\013\013\013\013\n\n\n\n\n\n\n\n\n\013\013\013\013\013\013\013\013\013\013\013\013\013\013\f\f\f\f\f\f\r\r\r\r\016\016\016\016\017\017\020\020\020\021\022\022\023\023\023\022\022\021\021\020\020\017\017\017\016\016\016\016\r\r\r\r\r\r\f\f\f\f\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\013\f\f\f\f\f\f\f\r\r\r\r\r\016\016\016\017\017\017\020\020\020\021\021\022\023\023\024\025\026\027\030\031\032\034\035\037!$'*.3334445567789:;<=<96420.-+*)('&%%$##\"\"\"!!    \037\037\037\037\037\037\037\037\037\037\037\037\037\037\037\037\037\037    \036\034\032\031\027\026\025\024\024\023\022\021\021\020\020\017\017\017\016\016\016\r\r\r\r\f\f\f\f\f\013\013\013\013\013\013\013\013\013\013\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\013\013\013\013\013\013\013\013\013\f\f\f\f\f\f\r\r\r\r\016\016\016\017\017\020\020\020\021\022\022\023\022\022\021\021\020\020\017\017\017\016\016\016\r\r\r\r\r\f\f\f\f\f\f\013\013\013\013\013\013\013\013\013\013\013\n\n\n\n\n\n\n\n\n\n\n\n\013\013\013\013\013\013\013\013\013\013\013\013\f\f\f\f\f\f\r\r\r\r\r\016\016\016\017\017\017\020\020\021\021\022\023\023\024\025\026\027\030\031\033\034\036 #&)-2334455667789:;<=96420.,+*)'&&%$##\"\"!!   \037\037\037\037\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\036\037\037\037\037\035\033\031\030\027\026\025\024\023\022\021\021\020\020\017\017\017\016\016\r\r\r\r\f\f\f\f\f\013\013\013\013\013\013\013\013\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\013\013\013\013\013\013\013\013\f\f\f\f\f\r\r\r\016\016\016\017\017\017\020\020\021\022\022\022\021\020\020\020\017\017\016\016\016\016\r\r\r\r\f\f\f\f\f\f\013\013\013\013\013\013\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\013\013\013\013\013\013\013\013\013\f\f\f\f\f\r\r\r\r\016\016\016\017\017\017\020\020\021\021\022\023\023\024\025\026\027\031\032\033\035\037\"%(,0334455667889:;<:7420.,+)('&%$##\"\"!   \037\037\036\036\036\036\035\035\035\035\035\035\035\035\035\035\034\035\035\035\035\035\035\035\035\035\036\036\036\034\032\031\027\026\025\024\023\022\022\021\020\020\017\017\016\016\016\r\r\r\f\f\f\f\f\013\013\013\013\013\013\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\t\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\013\013\013\013\013\013\013\f\f\f\f\r\r\r\r\016\016\017\017\017\020\020\021\022\021\020\020\017\017\017\016\016\016\r\r\r\r\f\f\f\f\f\013\013\013\013\013\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\013\013\013\013\013\013\013\f\f\f\f\f\r\r\r\r\016\016\016\017\017\020\020\021\021\022\023\024\024\025\026\030\031\033\034\036!#'*/344455667889:;;8520.,*)('%%$#\"!!  \037\037\036\036\036\035\035\035\035\034\034\034\034\034\034\034\034\033\033\033\033\034\034\034\034\034\034\034\034\035\035\035\033\031\030\026\025\024\023\022\022\021\020\020\017\017\016\016\016\r\r\r\f\f\f\f\013\013\013\013\013\013\n\n\n\n\n\n\n\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n\n\n\n\n\n\n\n\n\n\013\013\013\013\013\013\f\f\f\f\r\r\r\016\016\016\017\017\020\021\021\020\020\017\017\017\016\016\r\r\r\r\f\f\f\f\f\013\013\013\013\013\013\013\n\n\n\n\n\n\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n\n\n\n\n\n\n\n\n\n\n\n\013\013\013\013\013\013\013\f\f\f\f\r\r\r\016\016\016\017\017\020\020\021\021\022\023\024\025\026\027\030\032\033\035 \"%)-344555677899:;8520.,*)'&%$#\"\"!  \037\037\036\036\035\035\035\034\034\034\034\033\033\033\033\033\033\033\032\032\032\032\032\032\033\033\033\033\033\033\033\033\034\034\032\030\027\026\025\024\023\022\021\020\020\017\017\016\016\r\r\r\f\f\f\f\013\013\013\013\013\n\n\n\n\n\n\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n\n\n\n\n\n\n\n\013\013\013\013\013\013\f\f\f\r\r\r\016\016\016\017\017\020\020\020\017\017\016\016\016\r\r\r\f\f\f\f\f\013\013\013\013\013\013\n\n\n\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n\n\n\n\n\n\n\n\n\013\013\013\013\013\013\f\f\f\f\r\r\r\016\016\017\017\020\020\021\021\022\023\024\025\026\027\031\032\034\036!$(,14455667789:::630.,*('&%##\"!  \037\036\036\035\035\034\034\034\033\033\033\033\032\032\032\032\032\032\032\032\031\031\031\031\031\031\031\032\032\032\032\032\032\032\033\033\031\030\026\025\024\023\022\021\020\020\017\017\016\016\r\r\r\f\f\f\013\013\013\013\013\n\n\n\n\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n\n\n\n\n\n\n\013\013\013\013\f\f\f\f\r\r\r\016\016\017\017\020\017\017\016\016\r\r\r\r\f\f\f\f\013\013\013\013\013\n\n\n\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n\n\n\n\n\n\n\n\013\013\013\013\013\f\f\f\r\r\r\016\016\017\017\020\020\021\022\022\023\024\025\026\030\031\033\035 #&*04455667889:;730.,*('%$#\"!  \037\036\036\035\035\034\034\033\033\033\032\032\032\032\031\031\031\031\031\031\031\031\030\030\030\030\030\030\030\031\031\031\031\031\031\031\032\032\030\027\025\024\023\022\021\021\020\017\017\016\016\r\r\r\f\f\f\013\013\013\013\n\n\n\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\t\t\t\t\t\t\t\t\t\t\t\n\n\n\n\n\n\n\013\013\013\013\f\f\f\r\r\r\016\016\017\017\017\016\016\r\r\r\f\f\f\f\013\013\013\013\013\n\n\n\n\n\n\n\t\t\t\t\t\t\t\t\t\t\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\t\t\t\t\t\t\t\t\t\t\t\t\t\n\n\n\n\n\n\n\013\013\013\013\f\f\f\f\r\r\016\016\016\017\020\020\021\022\022\023\024\026\027\030\032\034\037!%).4555667889:841.,*(&%$\"!! \037\036\036\035\034\034\033\033\033\032\032\032\031\031\031\031\030\030\030\030\030\030\030\027\027\027\027\027\027\027\027\030\030\030\030\030\030\030\030\031\027\026\024\023\022\021\021\020\017\017\016\016\r\r\f\f\f\013\013\013\013\n\n\n\n\n\n\t\t\t\t\t\t\t\t\t\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\t\t\t\t\t\t\t\t\t\n\n\n\n\n\n\013\013\013\013\f\f\f\r\r\016\016\017\016\016\r\r\f\f\f\f\013\013\013\013\n\n\n\n\n\n\n\t\t\t\t\t\t\t\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\t\t\t\t\t\t\t\t\t\n\n\n\n\n\n\013\013\013\013\f\f\f\r\r\r\016\016\017\020\020\021\022\023\024\025\026\030\031\033\036 $(,3556677889951.,)(&$#\"! \037\036\036\035\034\034\033\033\032\032\031\031\031\030\030\030\030\030\027\027\027\027\027\027\027\027\026\026\026\026\026\026\026\027\027\027\027\027\027\027\027\030\026\025\024\023\022\021\020\017\017\016\016\r\r\f\f\f\013\013\013\n\n\n\n\n\t\t\t\t\t\t\t\t\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\t\t\t\t\t\t\t\n\n\n\n\n\013\013\013\013\f\f\f\r\r\016\016\r\r\r\f\f\f\013\013\013\013\n\n\n\n\n\n\t\t\t\t\t\t\t\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\t\t\t\t\t\t\t\t\n\n\n\n\n\013\013\013\013\f\f\f\r\r\016\016\017\020\020\021\022\023\024\025\027\030\032\034\037\"&+155667789962.,)'&$#\" \037\037\036\035\034\034\033\032\032\032\031\031\030\030\030\027\027\027\027\026\026\026\026\026\026\026\026\026\025\025\025\025\025\025\025\026\026\026\026\026\026\026\026\027\025\024\023\022\021\020\017\017\016\016\r\r\f\f\013\013\013\013\n\n\n\n\t\t\t\t\t\t\t\b\b\b\b\b\b\b\b\b\b\b\b\b\b\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\b\b\b\b\b\b\b\b\b\b\b\b\b\b\t\t\t\t\t\t\t\n\n\n\n\013\013\013\f\f\f\r\r\r\r\r\f\f\013\013\013\013\n\n\n\n\n\t\t\t\t\t\t\t\t\b\b\b\b\b\b\b\b\b\b\b\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\b\b\b\b\b\b\b\b\b\b\b\b\b\b\t\t\t\t\t\t\t\n\n\n\n\n\013\013\013\f\f\f\r\r\016\016\017\020\020\021\022\023\024\026\027\031\033\036!%)/5566778972/,)'%$\"! \037\036\035\034\034\033\032\032\031\031\030\030\030\027\027\027\026\026\026\026\025\025\025\025\025\025\025\025\025\025\024\024\024\024\024\025\025\025\025\025\025\025\025\025\025\025\023\022\021\020\017\017\016\r\r\f\f\f\013\013\013\n\n\n\n\t\t\t\t\t\t\b\b\b\b\b\b\b\b\b\b\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\b\b\b\b\b\b\b\b\b\b\b\t\t\t\t\t\t\n\n\n\n\013\013\013\f\f\r\r\f\f\f\013\013\013\n\n\n\n\n\t\t\t\t\t\t\t\b\b\b\b\b\b\b\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\b\b\b\b\b\b\b\b\b\b\t\t\t\t\t\t\n\n\n\n\n\013\013\013\f\f\r\r\016\016\017\020\020\021\022\023\025\026\030\032\034\037#(-556678894/,)'%#\"!\037\036\035\034\034\033\032\032\031\031\030\030\027\027\026\026\026\026\025\025\025\025\024\024\024\024\024\024\024\024\024\024\024\023\023\023\024\024\024\024\024\024\024\024\024\024\024\024\022\021\020\017\017\016\r\r\f\f\f\013\013\n\n\n\n\t\t\t\t\t\t\b\b\b\b\b\b\b\b\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\b\b\b\b\b\b\b\b\t\t\t\t\t\t\n\n\n\013\013\013\f\f\f\f\013\013\013\n\n\n\n\n\t\t\t\t\t\t\b\b\b\b\b\b\b\b\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\b\b\b\b\b\b\b\b\t\t\t\t\t\n\n\n\n\013\013\013\f\f\r\r\016\016\017\020\020\021\023\024\025\027\031\033\036\"&+366778850,)'%#! \037\036\035\034\033\032\032\031\030\030\027\027\026\026\026\025\025\025\024\024\024\024\024\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\023\022\020\020\017\016\r\r\f\f\013\013\013\n\n\n\t\t\t\t\t\b\b\b\b\b\b\b\b\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\006\006\006\006\006\006\006\006\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\b\b\b\b\b\b\b\b\t\t\t\t\n\n\n\n\013\013\013\f\013\013\013\n\n\n\n\t\t\t\t\t\b\b\b\b\b\b\b\b\007\007\007\007\007\007\007\007\007\007\007\007\007\006\006\006\006\006\006\006\006\006\006\006\006\006\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\b\b\b\b\b\b\b\b\t\t\t\t\n\n\n\n\013\013\013\f\f\r\016\016\017\020\021\022\023\024\026\030\032\035 $*16677871-*'%#! \036\035\034\033\032\032\031\030\030\027\027\026\026\025\025\025\024\024\024\023\023\023\023\023\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\022\021\020\017\016\r\r\f\f\013\013\013\n\n\n\t\t\t\t\b\b\b\b\b\b\007\007\007\007\007\007\007\007\007\007\007\007\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\007\007\007\007\007\007\007\007\007\007\007\007\b\b\b\b\b\b\b\t\t\t\t\n\n\n\013\013\013\013\n\n\n\n\t\t\t\t\b\b\b\b\b\b\b\b\007\007\007\007\007\007\007\007\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\007\007\007\007\007\007\007\007\007\007\007\007\b\b\b\b\b\b\t\t\t\t\n\n\n\013\013\013\f\f\r\r\016\017\020\021\022\023\025\027\031\033\037#(.667783.*'%\"!\037\036\035\033\033\032\031\030\027\027\026\026\025\025\025\024\024\023\023\023\023\022\022\022\022\022\022\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\021\020\017\016\r\r\f\f\013\013\n\n\n\t\t\t\t\b\b\b\b\b\b\007\007\007\007\007\007\007\007\007\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\007\007\007\007\007\007\007\007\007\b\b\b\b\b\b\t\t\t\t\n\n\n\013\n\n\n\t\t\t\t\b\b\b\b\b\b\007\007\007\007\007\007\007\007\007\007\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\007\007\007\007\007\007\007\007\007\007\b\b\b\b\b\t\t\t\t\n\n\n\013\013\f\f\r\r\016\017\020\021\022\024\025\027\032\035!&,56785/*'$\" \037\035\034\033\032\031\030\027\027\026\026\025\025\024\024\023\023\023\022\022\022\022\021\021\021\021\021\021\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\020\021\020\017\016\r\r\f\f\013\013\n\n\t\t\t\t\b\b\b\b\b\007\007\007\007\007\007\007\007\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\007\007\007\007\007\007\007\007\b\b\b\b\b\t\t\t\n\n\n\n\t\t\t\t\b\b\b\b\b\007\007\007\007\007\007\007\007\007\007\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\005\005\005\005\005\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\007\007\007\007\007\007\007\007\b\b\b\b\b\t\t\t\t\n\n\013\013\013\f\r\r\016\017\020\021\023\024\026\031\034\037$*37780+'$\" \036\035\033\032\031\030\027\027\026\025\025\024\024\023\023\023\022\022\022\021\021\021\021\020\020\020\020\020\020\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\017\020\017\016\r\r\f\013\013\n\n\n\t\t\t\b\b\b\b\b\007\007\007\007\007\007\007\006\006\006\006\006\006\006\006\006\006\006\006\006\006\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\007\007\007\007\007\007\007\b\b\b\b\b\t\t\t\t\t\t\t\b\b\b\b\b\007\007\007\007\007\007\007\006\006\006\006\006\006\006\006\006\006\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\006\006\006\006\006\006\006\006\006\006\006\006\006\007\007\007\007\007\007\007\b\b\b\b\t\t\t\n\n\n\013\013\f\r\r\016\017\020\022\023\025\027\032\036\"(0772,'$\"\037\036\034\033\032\030\030\027\026\025\025\024\024\023\023\022\022\021\021\021\021\020\020\020\020\017\017\017\017\017\017\017\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\016\017\016\r\r\f\013\013\n\n\t\t\t\b\b\b\b\007\007\007\007\007\007\007\006\006\006\006\006\006\006\006\006\006\006\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\006\006\006\006\006\006\006\006\006\006\006\007\007\007\007\007\007\b\b\b\b\b\t\t\t\b\b\b\b\007\007\007\007\007\007\007\006\006\006\006\006\006\006\006\006\006\006\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\006\006\006\006\006\006\006\006\006\006\007\007\007\007\007\007\b\b\b\b\t\t\t\n\n\013\013\f\r\r\016\017\020\022\024\026\030\034 &.75-($!\037\035\033\032\031\030\027\026\025\024\024\023\023\022\022\021\021\021\020\020\020\017\017\017\017\017\016\016\016\016\016\016\016\016\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\016\016\r\r\f\013\013\n\n\t\t\t\b\b\b\007\007\007\007\007\007\006\006\006\006\006\006\006\006\006\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\006\006\006\006\006\006\006\006\006\007\007\007\007\007\007\b\b\b\b\b\b\b\007\007\007\007\007\007\006\006\006\006\006\006\006\006\006\006\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\006\006\006\006\006\006\006\006\007\007\007\007\007\b\b\b\b\t\t\n\n\n\013\f\f\r\016\017\021\022\024\027\032\036$+6.($!\037\035\033\031\030\027\026\025\024\024\023\022\022\021\021\021\020\020\017\017\017\017\016\016\016\016\016\r\r\r\r\r\r\r\r\r\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\f\r\r\r\r\f\013\n\n\t\t\t\b\b\b\007\007\007\007\007\006\006\006\006\006\006\006\006\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\004\004\004\004\004\004\004\004\004\004\004\004\004\004\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\006\006\006\006\006\006\006\006\007\007\007\007\007\b\b\b\007\007\007\007\007\006\006\006\006\006\006\006\006\005\005\005\005\005\005\005\005\005\005\005\005\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\006\006\006\006\006\006\006\007\007\007\007\b\b\b\b\t\t\n\n\013\f\f\r\016\020\021\023\025\030\034\")1)$!\036\034\032\031\027\026\025\024\024\023\022\022\021\021\020\020\017\017\017\016\016\016\016\r\r\r\r\r\f\f\f\f\f\f\f\f\f\f\f\013\013\013\013\013\013\013\013\013\013\013\013\013\013\f\f\f\f\f\f\013\n\n\t\t\b\b\b\007\007\007\007\007\006\006\006\006\006\006\005\005\005\005\005\005\005\005\005\005\005\005\005\005\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\005\005\005\005\005\005\005\005\005\005\005\005\005\006\006\006\006\006\006\006\007\007\007\007\007\007\007\007\006\006\006\006\006\006\006\005\005\005\005\005\005\005\005\005\005\005\005\005\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\005\005\005\005\005\005\005\005\005\005\005\005\006\006\006\006\006\006\007\007\007\007\b\b\b\t\t\n\n\013\013\f\r\017\020\022\024\027\032\037&*$!\036\033\031\030\027\025\024\023\023\022\021\021\020\020\017\017\016\016\016\016\r\r\r\r\f\f\f\f\f\f\013\013\013\013\013\013\013\013\013\013\013\013\013\n\n\n\n\n\n\n\n\013\013\013\013\013\013\013\013\013\n\n\t\t\b\b\007\007\007\007\006\006\006\006\006\006\005\005\005\005\005\005\005\005\005\005\005\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\005\005\005\005\005\005\005\005\005\005\005\006\006\006\006\006\006\007\007\007\006\006\006\006\006\006\005\005\005\005\005\005\005\005\005\005\005\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\005\005\005\005\005\005\005\005\005\006\006\006\006\006\006\007\007\007\b\b\b\t\t\n\013\013\f\r\017\020\022\025\030\035$% \035\033\031\027\026\024\023\023\022\021\020\020\017\017\016\016\016\r\r\r\f\f\f\f\013\013\013\013\013\013\013\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\t\t\b\b\b\007\007\007\006\006\006\006\006\005\005\005\005\005\005\005\005\005\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\005\005\005\005\005\005\005\005\005\006\006\006\006\006\006\006\006\005\005\005\005\005\005\005\005\005\005\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\005\005\005\005\005\005\005\006\006\006\006\006\007\007\007\b\b\t\t\n\n\013\f\r\017\021\023\026\033! \034\032\030\026\025\023\022\022\021\020\017\017\016\016\r\r\r\f\f\f\f\013\013\013\013\n\n\n\n\n\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\b\b\007\007\007\006\006\006\006\005\005\005\005\005\005\005\005\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\005\005\005\005\005\005\005\005\006\006\005\005\005\005\005\005\005\005\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\004\004\004\004\004\004\004\004\004\004\004\004\004\004\005\005\005\005\005\005\006\006\006\006\007\007\007\b\b\t\t\n\013\f\016\017\021\024\030\036\034\031\027\025\024\022\021\020\020\017\016\016\r\r\f\f\f\013\013\013\013\n\n\n\n\n\t\t\t\t\t\t\t\t\t\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\007\007\007\006\006\006\006\005\005\005\005\005\005\004\004\004\004\004\004\004\004\004\004\004\004\004\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\004\004\004\004\004\004\004\004\004\004\004\004\004\005\005\005\005\005\005\005\005\005\005\004\004\004\004\004\004\004\004\004\004\004\004\004\004\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\004\004\004\004\004\004").append("\004\004\004\004\005\005\005\005\005\005\006\006\006\007\007\007\b\b\t\n\013\f\016\020\022\026\033\030\026\024\022\021\020\017\017\016\r\r\f\f\013\013\013\n\n\n\n\t\t\t\t\t\t\b\b\b\b\b\b\b\b\b\b\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\007\006\006\006\005\005\005\005\005\004\004\004\004\004\004\004\004\004\004\004\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\004\004\004\004\004\004\004\004\004\004\005\005\005\004\004\004\004\004\004\004\004\004\004\004\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\004\004\004\004\004\004\004\004\004\005\005\005\005\006\006\006\007\007\007\b\t\n\013\f\016\020\024\027\024\023\021\020\017\016\r\r\f\f\013\013\n\n\n\t\t\t\t\t\b\b\b\b\b\b\007\007\007\007\007\007\007\007\007\007\007\007\007\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\007\006\006\006\005\005\005\005\004\004\004\004\004\004\004\004\004\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\002\002\002\002\002\002\002\002\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\004\004\004\004\004\004\004\004\004\004\004\004\004\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\004\004\004\004\004\004\004\005\005\005\005\006\006\006\007\b\b\t\013\f\016\021\023\021\020\017\016\r\f\013\013\n\n\n\t\t\t\b\b\b\b\b\007\007\007\007\007\007\007\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\006\005\005\005\005\005\005\005\005\005\005\005\005\006\006\006\006\006\006\006\005\005\005\005\004\004\004\004\004\004\004\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\004\004\004\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\003\003\003\003\003\003\003\003\003\003\003\003\003\004\004\004\004\004\005\005\005\006\006\007\007\b\t\n\f\017\017\016\r\f\013\013\n\n\t\t\b\b\b\b\007\007\007\007\007\006\006\006\006\006\006\006\006\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\005\004\004\004\004\004\003\003\003\003\003\003\003\003\003\003\003\003\003\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\003\003\003\003\003\003\003\003\003\004\004\004\004\005\005\005\006\007\007\b\n\f\f\013\n\n\t\t\b\b\007\007\007\007\006\006\006\006\006\006\005\005\005\005\005\005\005\005\005\005\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\004\003\003\003\003\003\003\003\003\003\003\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\003\003\003\003\003\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\003\003\003\003\003\003\003\004\004\004\005\005\006\007\b\n\t\t\b\007\007\007\006\006\006\006\005\005\005\005\005\005\004\004\004\004\004\004\004\004\004\004\004\004\004\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\003\003\003\003\004\004\005\006\007\006\006\006\005\005\005\004\004\004\004\004\004\004\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\003\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\002\002\002\002\002\002\002\002\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\002\002\002\002\002\002\002\003\003\003\004\004\003\003\003\003\003\003\003\003\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\002\001\001\001\001\001\001\001\001\001\001\001\001\001\002\002\002\002\002\002\002\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001\001").toString().getBytes("ISO_8859_1");


		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			GAMUT_DATA = new byte[65536];
		}
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
	 * Returns true if the given packed float color, as Oklab, is valid to convert losslessly back to RGBA.
	 * @param packed a packed float color as Oklab
	 * @return true if the given packed float color can be converted back and forth to RGBA
	 */
	public static boolean inGamut(final float packed)
	{
		final int decoded = BitConversion.floatToRawIntBits(packed);
		final float A = ((decoded >>> 8 & 0xff) - 127.5f) / 255f;
		final float B = ((decoded >>> 16 & 0xff) - 127.5f) / 255f;
		final byte g = GAMUT_DATA[(decoded & 0xff) << 8 | (int)(256f * MathTools.atan2_(B, A))];
		return g * g * 0x1p-16 >= (A * A + B * B);
	}

	/**
	 * Returns true if the given Oklab values are valid to convert losslessly back to RGBA.
	 * @param L lightness channel, as a float from 0 to 1
	 * @param A green-to-red chromatic channel, as a float from 0 to 1
	 * @param B blue-to-yellow chromatic channel, as a float from 0 to 1
	 * @return true if the given Oklab channels can be converted back and forth to RGBA
	 */
	public static boolean inGamut(float L, float A, float B)
	{
		A = (A - 0.5f);
		B = (B - 0.5f);
		final byte g = GAMUT_DATA[((int)(L * 255.999f) << 8) | (int)(256f * MathTools.atan2_(B, A))];
		return g * g * 0x1p-16 >= (A * A + B * B);
	}

	/**
	 * Gets the color with the same L as the Oklab color stored in the given packed float, but the furthest A
	 * B from gray possible for that lightness while keeping the same hue as the given color. This is very
	 * similar to calling {@link #enrich(float, float)} with a very large {@code change} value.
	 * @param packed a packed float color in Oklab format; does not need to be in-gamut
	 * @return the color that is as far from grayscale as this can get while keeping the L and hue of packed
	 * @see #limitToGamut(float) You can use limitToGamut() if you only want max saturation for out-of-gamut colors.
	 */
	public static float maximizeSaturation(final float packed) {
		final int decoded = BitConversion.floatToRawIntBits(packed);
		final float A = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float B = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float hue = MathTools.atan2_(B, A);
		final int idx = (decoded & 0xff) << 8 | (int) (256f * hue);
		final float dist = GAMUT_DATA[idx];
		return BitConversion.intBitsToFloat(
				(decoded & 0xFE0000FF) |
						(int) (MathTools.cos_(hue) * dist + 128f) << 8 |
						(int) (MathTools.sin_(hue) * dist + 128f) << 16);
	}

	/**
	 * Gets the color with the same L as the Oklab color stored in the given packed float, but the furthest A
	 * B from gray possible for that lightness while keeping the same hue as the given color. This is very
	 * similar to calling {@link #enrich(float, float)} with a very large {@code change} value.
	 * @param L lightness component; will be clamped between 0 and 1 if it isn't already
	 * @param A green-to-red chromatic component; will be clamped between 0 and 1 if it isn't already
	 * @param B blue-to-yellow chromatic component; will be clamped between 0 and 1 if it isn't already
	 * @param alpha alpha component; will be clamped between 0 and 1 if it isn't already
	 * @return the color that is as far from grayscale as this can get while keeping the L and hue of packed
	 * @see #limitToGamut(float, float, float, float) You can use limitToGamut() if you only want max saturation for out-of-gamut colors.
	 */
	public static float maximizeSaturation(float L, float A, float B, float alpha) {
		L = Math.min(Math.max(L, 0f), 1f);
		A = Math.min(Math.max(A, 0f), 1f);
		B = Math.min(Math.max(B, 0f), 1f);
		alpha = Math.min(Math.max(alpha, 0f), 1f);
		final float A2 = (A - 0.5f);
		final float B2 = (B - 0.5f);
		final float hue = MathTools.atan2_(B2, A2);
		final int idx = (int) (L * 255.999f) << 8 | (int) (256f * hue);
		final float dist = GAMUT_DATA[idx];
		return BitConversion.intBitsToFloat(
				(int) (alpha * 127.999f) << 25 |
						(int) (MathTools.sin_(hue) * dist + 128f) << 16 |
						(int) (MathTools.cos_(hue) * dist + 128f) << 8 |
						(int) (L * 255.999f));
	}
	
	/**
	 * Gets the hue of the given Oklab float color, but as Oklab understands hue rather than how HSL does.
	 * This is different from {@link #hue(float)}, which uses HSL. This gives a float between 0 (inclusive)
	 * and 1 (exclusive).
	 *
	 * @param packed a packed Oklab float color
	 * @return a float between 0 (inclusive) and 1 (exclusive) that represents hue in the Oklab color space
	 */
	public static float oklabHue(final float packed) {
		final int decoded = BitConversion.floatToRawIntBits(packed);
		final float A = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float B = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		return MathTools.atan2_(B, A);
	}

	/**
	 * Gets the saturation of the given Oklab float color, but as Oklab understands saturation rather than how HSL does.
	 * Saturation here is a fraction of the chroma limit (see {@link #chromaLimit(float, float)}) for a given hue and
	 * lightness, and is between 0 and 1. This gives a float between 0 (inclusive) and 1 (inclusive).
	 *
	 * @param packed a packed Oklab float color
	 * @return a float between 0 (inclusive) and 1 (inclusive) that represents saturation in the Oklab color space
	 */
	public static float oklabSaturation(final float packed) {
		final int decoded = BitConversion.floatToRawIntBits(packed);
		final float A = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float B = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float hue = MathTools.atan2_(B, A);
		final int idx = (decoded & 0xff) << 8 | (int) (256f * hue);
		final float dist = GAMUT_DATA[idx];
		return (float) Math.sqrt(A * A + B * B) * 256f / dist;
	}
	/**
	 * Gets the lightness of the given Oklab float color, but as Oklab understands lightness rather than how HSL does.
	 * This is different from {@link #lightness(float)}, which uses HSL. This gives a float between 0 (inclusive)
	 * and 1 (inclusive).
	 * <br>
	 * This is the same as {@link #channelL(float)}.
	 *
	 * @param packed a packed Oklab float color
	 * @return a float between 0 (inclusive) and 1 (inclusive) that represents lightness in the Oklab color space
	 */
	public static float oklabLightness(final float packed){
		return (BitConversion.floatToRawIntBits(packed) & 0xff) / 255f;
	}

	/**
	 * A different way to specify an Oklab color, using hue, saturation, lightness, and alpha like a normal HSL(A) color
	 * but calculating them directly in the Oklab color space. This is more efficient than
	 * {@link #floatGetHSL(float, float, float, float)}, but for colors with less than 0.5f lightness, it can be very
	 * unpredictable in how it handles saturation. Most colors between 0.5 and 0.75 hue that also have less than 0.5
	 * lightness are extremely desaturated and close to gray, regardless of what you give for saturation, and these
	 * colors suddenly jump to very saturated around 0.75 hue and higher. To avoid this issue, you may prefer using
	 * {@link #oklabByHCL(float, float, float, float)}, which takes an absolute chroma as opposed to the saturation here
	 * (which is a fraction of the maximum chroma).
	 * <br>
	 * Note that this takes a different value for its {@code hue} that the method {@link #hue(float)} produces, just
	 * like its {@code saturation} and the method {@link #saturation(float)}, and {@code lightness} and the method
	 * {@link #lightness(float)}. The hue is just distributed differently, and the lightness should be equivalent to
	 * {@link #channelL(float)}, but the saturation here refers to what fraction the chroma should be of the maximum
	 * chroma for the given hue and lightness. You can use {@link #oklabHue(float)}, {@link #oklabSaturation(float)},
	 * and {@link #oklabLightness(float)} to get the hue, saturation, and lightness values from an existing color that
	 * this will understand ({@link #alpha(float)} too).
	 * @param hue between 0 and 1, usually, but this will automatically wrap if too high or too low
	 * @param saturation will be clamped between 0 and 1
	 * @param lightness will be clamped between 0 and 1
	 * @param alpha will be clamped between 0 and 1
	 * @return a packed Oklab float color that tries to match the requested hue, saturation, and lightness
	 */
	public static float oklabByHSL(float hue, float saturation, float lightness, float alpha) {
		lightness = Math.min(Math.max(lightness, 0f), 1f);
		saturation = Math.min(Math.max(saturation, 0f), 1f);
		hue -= (int)(hue + 0x1p14) - 0x4000;
		alpha = Math.min(Math.max(alpha, 0f), 1f);
		final int idx = (int) (lightness * 255.999f) << 8 | (int) (256f * hue);
		final float dist = GAMUT_DATA[idx] * saturation;
		return BitConversion.intBitsToFloat(
				(int) (alpha * 127.999f) << 25 |
						(int) (MathTools.sin_(hue) * dist + 128f) << 16 |
						(int) (MathTools.cos_(hue) * dist + 128f) << 8 |
						(int) (lightness * 255.999f));
	}

	/**
	 * A different way to specify an Oklab color, using hue, chroma, lightness, and alpha something like a normal HSL(A)
	 * color but calculating them directly in the Oklab color space. This has you specify the desired chroma directly,
	 * as obtainable with {@link #chroma(float)}, rather than the saturation, which is a fraction of the maximum chroma
	 * (saturation is what {@link #oklabByHSL(float, float, float, float)} uses). Note that this takes a different value
	 * for its {@code hue} that the method {@link #hue(float)} produces, just like {@code lightness} and the method
	 * {@link #lightness(float)}. The hue is just distributed differently, and the lightness should be equivalent to
	 * {@link #channelL(float)}. If you use this to get two colors with the same chroma and lightness, but different
	 * hue, then the resulting colors should have similar colorfulness unless one or both chroma values exceeded the
	 * gamut limit (you can get this limit with {@link #chromaLimit(float, float)}). If a chroma value given is greater
	 * than the chroma limit, this clamps chroma to that limit. You can use {@link #oklabHue(float)},
	 * {@link #chroma(float)}, and {@link #oklabLightness(float)} to get the hue, chroma, and lightness values from an
	 * existing color that this will understand ({@link #alpha(float)} too).
	 * @param hue between 0 and 1, usually, but this will automatically wrap if too high or too low
	 * @param chroma will be clamped between 0 and the maximum chroma possible for the given hue and lightness
	 * @param lightness will be clamped between 0 and 1
	 * @param alpha will be clamped between 0 and 1
	 * @return a packed Oklab float color that tries to match the requested hue, chroma, and lightness
	 */
	public static float oklabByHCL(float hue, float chroma, float lightness, float alpha) {
		lightness = Math.min(Math.max(lightness, 0f), 1f);
		chroma = Math.max(chroma, 0f);
		hue -= (int)(hue + 0x1p14) - 0x4000;
		alpha = Math.min(Math.max(alpha, 0f), 1f);
		final int idx = (int) (lightness * 255.999f) << 8 | (int) (256f * hue);
		final float dist = Math.min(chroma * 128f, GAMUT_DATA[idx]);
		return BitConversion.intBitsToFloat(
				(int) (alpha * 127.999f) << 25 |
						(int) (MathTools.sin_(hue) * dist + 128f) << 16 |
						(int) (MathTools.cos_(hue) * dist + 128f) << 8 |
						(int) (lightness * 255.999f));
	}

	/**
	 * Checks whether the given Oklab color is in-gamut; if it isn't in-gamut, brings the color just inside
	 * the gamut at the same lightness, or if it is already in-gamut, returns the color as-is.
	 * @param packed a packed float color in Oklab format; often this color is not in-gamut
	 * @return the first color this finds that is in-gamut, as if it was moving toward a grayscale color with the same L
	 * @see #inGamut(float) You can use inGamut() if you just want to check whether a color is in-gamut.
	 */
	public static float limitToGamut(final float packed) {
		final int decoded = BitConversion.floatToRawIntBits(packed);
		final float A = ((decoded >>> 8 & 0xff) - 127.5f);
		final float B = ((decoded >>> 16 & 0xff) - 127.5f);
		final float hue = MathTools.atan2_(B, A);
		final int idx = (decoded & 0xff) << 8 | (int) (256f * hue);
		final float dist = GAMUT_DATA[idx];
		if (dist * dist >= (A * A + B * B))
			return packed;
		return BitConversion.intBitsToFloat(
				(decoded & 0xFE0000FF) |
						(int) (MathTools.cos_(hue) * dist + 128f) << 8 |
						(int) (MathTools.sin_(hue) * dist + 128f) << 16);
	}

	/**
	 * Checks whether the given Oklab color is in-gamut; if it isn't in-gamut, brings the color just inside
	 * the gamut at the same lightness, or if it is already in-gamut, returns the color as-is. This always produces
	 * an opaque color.
	 * @param L lightness component; will be clamped between 0 and 1 if it isn't already
	 * @param A green-to-red chromatic component; will be clamped between 0 and 1 if it isn't already
	 * @param B blue-to-yellow chromatic component; will be clamped between 0 and 1 if it isn't already
	 * @return the first color this finds that is in-gamut, as if it was moving toward a grayscale color with the same L
	 * @see #inGamut(float, float, float)  You can use inGamut() if you just want to check whether a color is in-gamut.
	 */
	public static float limitToGamut(float L, float A, float B) {
		return limitToGamut(L, A, B, 1f);
	}

	/**
	 * Checks whether the given Oklab color is in-gamut; if it isn't in-gamut, brings the color just inside
	 * the gamut at the same lightness, or if it is already in-gamut, returns the color as-is.
	 * @param L lightness component; will be clamped between 0 and 1 if it isn't already
	 * @param A green-to-red chromatic component; will be clamped between 0 and 1 if it isn't already
	 * @param B blue-to-yellow chromatic component; will be clamped between 0 and 1 if it isn't already
	 * @param alpha alpha component; will be clamped between 0 and 1 if it isn't already
	 * @return the first color this finds that is in-gamut, as if it was moving toward a grayscale color with the same L
	 * @see #inGamut(float, float, float)  You can use inGamut() if you just want to check whether a color is in-gamut.
	 */
	public static float limitToGamut(float L, float A, float B, float alpha) {
		L = Math.min(Math.max(L, 0f), 1f);
		A = Math.min(Math.max(A, 0f), 1f);
		B = Math.min(Math.max(B, 0f), 1f);
		alpha = Math.min(Math.max(alpha, 0f), 1f);
		final float A2 = (A - 0.5f);
		final float B2 = (B - 0.5f);
		final float hue = MathTools.atan2_(B2, A2);
		final int idx = (int) (L * 255.999f) << 8 | (int)(256f * hue);
		final float dist = GAMUT_DATA[idx];// * 0.5f;
		if(dist * dist * 0x1p-16f >= (A2 * A2 + B2 * B2))
			return oklab(L, A, B, alpha);
		return BitConversion.intBitsToFloat(
				(int) (alpha * 127.999f) << 25 |
						(int) (MathTools.sin_(hue) * dist + 128f) << 16 |
						(int) (MathTools.cos_(hue) * dist + 128f) << 8 |
						(int) (L * 255.999f));
	}
	/**
	 * Given a packed float Oklab color, this edits its L, A, B, and alpha channels by adding the corresponding "add"
	 * parameter and then clamping. This returns a different float value (of course, the given float can't be edited
	 * in-place). You can give a value of 0 for any "add" parameter you want to stay unchanged. This clamps the
	 * resulting color to remain in-gamut, so it should be safe to convert it back to RGBA.
	 * @param encoded a packed float Oklab color
	 * @param addL how much to add to the L channel; typically in the -1 to 1 range
	 * @param addA how much to add to the A channel; typically in the -1 to 1 range
	 * @param addB how much to add to the B channel; typically in the -1 to 1 range
	 * @param addAlpha how much to add to the alpha channel; typically in the -1 to 1 range
	 * @return a packed float Oklab color with the requested edits applied to {@code encoded}
	 */
	public static float editOklab(float encoded, float addL, float addA, float addB, float addAlpha) {
		return editOklab(encoded, addL, addA, addB, addAlpha, 1f, 1f, 1f, 1f);
	}
	/**
	 * Given a packed float Oklab color, this edits its L, A, B, and alpha channels by first multiplying each channel by
	 * the corresponding "mul" parameter and then adding the corresponding "add" parameter, before clamping. This means
	 * the lightness value {@code L} is multiplied by {@code mulL}, then has {@code addL} added, and then is clamped to
	 * the normal range for L (0 to 1). This returns a different float value (of course, the given float can't be edited
	 * in-place). You can give a value of 0 for any "add" parameter you want to stay unchanged, or a value of 1 for any
	 * "mul" parameter that shouldn't change. Note that this manipulates A and B in the -0.5 to 0.5 range, so if you
	 * multiply by a small number like {@code 0.25f}, then this will produce a less-saturated color, and if you multiply
	 * by a larger number like {@code 4f}, then you will get a much more-saturated color. This clamps the resulting
	 * color to remain in-gamut, so it should be safe to convert it back to RGBA.
	 * @param encoded a packed float Oklab color
	 * @param addL how much to add to the L channel; typically in the -1 to 1 range
	 * @param addA how much to add to the A channel; typically in the -1 to 1 range
	 * @param addB how much to add to the B channel; typically in the -1 to 1 range
	 * @param addAlpha how much to add to the alpha channel; typically in the -1 to 1 range
	 * @param mulL how much to multiply the L channel by; should be non-negative
	 * @param mulA how much to multiply the A channel by; usually non-negative (not always)
	 * @param mulB how much to multiply the B channel by; usually non-negative (not always)
	 * @param mulAlpha how much to multiply the alpha channel by; should be non-negative
	 * @return a packed float Oklab color with the requested edits applied to {@code encoded}
	 */
	public static float editOklab(float encoded, float addL, float addA, float addB, float addAlpha,
								  float mulL, float mulA, float mulB, float mulAlpha) {
		final int decoded = BitConversion.floatToRawIntBits(encoded);
		float L = (decoded & 0xff) / 255f;
		float A = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		float B = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		float alpha = (decoded >>> 25) / 127f;

		L = Math.min(Math.max(L * mulL + addL, 0f), 1f);
		A = Math.min(Math.max(A * mulA + addA * 2f, -1f), 1f) * 0.5f;
		B = Math.min(Math.max(B * mulB + addB * 2f, -1f), 1f) * 0.5f;
		alpha = Math.min(Math.max(alpha * mulAlpha + addAlpha, 0f), 1f);
		final float hue = MathTools.atan2_(B, A);
		final int idx = (int) (L * 255.999f) << 8 | (int)(256f * hue);
		final float dist = GAMUT_DATA[idx];
		if(dist * dist * 0x1p-16f >= (A * A + B * B))
			return oklab(L, A + 0.5f, B + 0.5f, alpha);
		return BitConversion.intBitsToFloat(
				(int) (alpha * 127.999f) << 25 |
						(int) (MathTools.sin_(hue) * dist + 128f) << 16 |
						(int) (MathTools.cos_(hue) * dist + 128f) << 8 |
						(int) (L * 255.999f));
	}

	/**
	 * Produces a random packed float color that is always in-gamut (and opaque) and should be uniformly distributed.
	 * @param random a Random object (preferably a subclass of Random, like
	 * {@link com.github.tommyettinger.ds.support.LaserRandom})
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
		return oklab(L, A, B, 1f);
	}

	/**
	 * Produces a random packed float color that is always in-gamut (and opaque) and should be uniformly distributed.
	 * This is named differently from {@link #randomColor(Random)} to avoid confusion when a class both extends Random
	 * and implements EnhancedRandom.
	 * @param random any implementation of jdkgdxds' EnhancedRandom, such as a
	 * {@link com.github.tommyettinger.ds.support.DistinctRandom} or
	 * {@link com.github.tommyettinger.ds.support.FourWheelRandom}
	 * @return a packed float color that is always in-gamut
	 */
	public static float randomizedColor(EnhancedRandom random) {
		float L = random.nextFloat();
		float A = random.nextFloat();
		float B = random.nextFloat();
		while (!inGamut(L, A, B)) {
			L = random.nextFloat();
			A = random.nextFloat();
			B = random.nextFloat();
		}
		return oklab(L, A, B, 1f);
	}
}
