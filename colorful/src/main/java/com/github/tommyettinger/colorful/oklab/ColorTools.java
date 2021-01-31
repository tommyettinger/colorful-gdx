package com.github.tommyettinger.colorful.oklab;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.NumberUtils;
import com.github.tommyettinger.colorful.FloatColors;
import com.github.tommyettinger.colorful.Shaders;

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
	 * from bluish to yellowish, called tritan in IPT), and alpha (or opacity). As long as you use a batch with
	 * {@link Shaders#fragmentShaderOklab} as its shader, colors passed with
	 * {@link com.badlogic.gdx.graphics.g2d.Batch#setPackedColor(float)} will be interpreted as Oklab. L should be
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
		return NumberUtils.intBitsToFloat(((int) (alpha * 255) << 24 & 0xFE000000) | ((int) (b * 255) << 16 & 0xFF0000)
				| ((int) (a * 255) << 8 & 0xFF00) | ((int) (l * 255) & 0xFF));
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
		int ix = NumberUtils.floatToRawIntBits(x);
		final int sign = ix & 0x80000000;
		ix &= 0x7FFFFFFF;
		final float x0 = x;
		ix = (ix>>>2) + (ix>>>4);
		ix += (ix>>>4);
		ix = ix + (ix>>>8) + 0x2A5137A0 | sign;
		x  = NumberUtils.intBitsToFloat(ix);
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
	 * Really just a float version of the square root of component.
	 * @param component a linear channel of a color, to be made non-linear
	 * @return a non-linear version of component
	 */
	private static float reverseGamma(final float component) {
		return (float)Math.sqrt(component);
	}


	/**
	 * Converts a packed float color in the format produced by {@link ColorTools#oklab(float, float, float, float)} to an RGBA8888 int.
	 * This format of int can be used with Pixmap and in some other places in libGDX.
	 * @param packed a packed float color, as produced by {@link ColorTools#oklab(float, float, float, float)}
	 * @return an RGBA8888 int color
	 */
	public static int toRGBA8888(final float packed)
	{
		final int decoded = NumberUtils.floatToRawIntBits(packed);
		final float L = (decoded & 0xff) / 255f;
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
		final int decoded = NumberUtils.floatToRawIntBits(packed);
		final float L = (decoded & 0xff) / 255f;
		final float A = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float B = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = cube(L + 0.3963377774f * A + 0.2158037573f * B);
		final float m = cube(L - 0.1055613458f * A - 0.0638541728f * B);
		final float s = cube(L - 0.0894841775f * A - 1.2914855480f * B);
		final int r = (int)(reverseGamma(Math.min(Math.max(+4.0767245293f * l - 3.3072168827f * m + 0.2307590544f * s, 0f), 1f)) * 255.999f);
		final int g = (int)(reverseGamma(Math.min(Math.max(-1.2681437731f * l + 2.6093323231f * m - 0.3411344290f * s, 0f), 1f)) * 255.999f);
		final int b = (int)(reverseGamma(Math.min(Math.max(-0.0041119885f * l - 0.7034763098f * m + 1.7068625689f * s, 0f), 1f)) * 255.999f);
		return NumberUtils.intBitsToFloat(r | g << 8 | b << 16 | (decoded & 0xfe000000));
	}
	/**
	 * Writes an Oklab-format packed float color (the format produced by {@link ColorTools#oklab(float, float, float, float)})
	 * into an RGBA8888 Color as used by libGDX (called {@code editing}).
	 * @param editing a libGDX color that will be filled in-place with an RGBA conversion of {@code packed}
	 * @param packed a packed float color, as produced by {@link ColorTools#oklab(float, float, float, float)}
	 * @return an RGBA8888 int color
	 */
	public static Color toColor(Color editing, final float packed)
	{
		final int decoded = NumberUtils.floatToRawIntBits(packed);
		final float L = (decoded & 0xff) / 255f;
		final float A = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float B = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = cube(L + 0.3963377774f * A + 0.2158037573f * B);
		final float m = cube(L - 0.1055613458f * A - 0.0638541728f * B);
		final float s = cube(L - 0.0894841775f * A - 1.2914855480f * B);
		editing.r = reverseGamma(Math.min(Math.max(+4.0767245293f * l - 3.3072168827f * m + 0.2307590544f * s, 0f), 1f));
		editing.g = reverseGamma(Math.min(Math.max(-1.2681437731f * l + 2.6093323231f * m - 0.3411344290f * s, 0f), 1f));
		editing.b = reverseGamma(Math.min(Math.max(-0.0041119885f * l - 0.7034763098f * m + 1.7068625689f * s, 0f), 1f));
		editing.a = (decoded >>> 25) * 0x1.020408p-7f; // this is 1/127 as a float
		return editing.clamp();
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

		final float l = cbrt(0.4121656120f * r + 0.5362752080f * g + 0.0514575653f * b);
		final float m = cbrt(0.2118591070f * r + 0.6807189584f * g + 0.1074065790f * b);
		final float s = cbrt(0.0883097947f * r + 0.2818474174f * g + 0.6302613616f * b);

		return NumberUtils.intBitsToFloat(
			              Math.min(Math.max((int)((0.2104542553f * l + 0.7936177850f * m - 0.0040720468f * s) * 255.999f         ), 0), 255)
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
		final int abgr = NumberUtils.floatToRawIntBits(packed);
		final float r = forwardGamma((abgr & 0xFF) * 0x1.010101010101p-8f);
		final float g = forwardGamma((abgr >>> 8 & 0xFF) * 0x1.010101010101p-8f);
		final float b = forwardGamma((abgr >>> 16 & 0xFF) * 0x1.010101010101p-8f);
		final float l = cbrt(0.4121656120f * r + 0.5362752080f * g + 0.0514575653f * b);
		final float m = cbrt(0.2118591070f * r + 0.6807189584f * g + 0.1074065790f * b);
		final float s = cbrt(0.0883097947f * r + 0.2818474174f * g + 0.6302613616f * b);
		return NumberUtils.intBitsToFloat(
				          Math.min(Math.max((int)((0.2104542553f * l + 0.7936177850f * m - 0.0040720468f * s) * 255.999f         ), 0), 255)
						| Math.min(Math.max((int)((1.9779984951f * l - 2.4285922050f * m + 0.4505937099f * s) * 127.999f + 127.5f), 0), 255) << 8
						| Math.min(Math.max((int)((0.0259040371f * l + 0.7827717662f * m - 0.8086757660f * s) * 127.999f + 127.5f), 0), 255) << 16
						| (abgr & 0xFE000000));
	}

	// original multipliers for LMS when obtaining the non-shrunken A and B values:
	//+0.2104542553 +0.7936177850 -0.0040720468
	//+1.9779984951 -2.4285922050 +0.4505937099
	//+0.0259040371 +0.7827717662 -0.8086757660

	// shrunken multipliers:
	//+0.2104542553 +0.7936177850 -0.0040720468
	//+0.9889992500 -1.2142961000 +0.2252968500
	//+0.0129520185 +0.3913858800 -0.4043378800

	/**
	 * Takes a libGDX Color that uses RGBA8888 channels and converts to a packed float in the Oklab format this uses.
	 * @param color a libGDX RGBA8888 Color
	 * @return a packed float as Oklab, which this class can use
	 */
	public static float fromColor(final Color color) {
		final float r = forwardGamma(color.r);
		final float g = forwardGamma(color.g);
		final float b = forwardGamma(color.b);
		final float l = cbrt(0.4121656120f * r + 0.5362752080f * g + 0.0514575653f * b);
		final float m = cbrt(0.2118591070f * r + 0.6807189584f * g + 0.1074065790f * b);
		final float s = cbrt(0.0883097947f * r + 0.2818474174f * g + 0.6302613616f * b);
		return NumberUtils.intBitsToFloat(
				          Math.min(Math.max((int)((0.2104542553f * l + 0.7936177850f * m - 0.0040720468f * s) * 255.999f         ), 0), 255)
						| Math.min(Math.max((int)((1.9779984951f * l - 2.4285922050f * m + 0.4505937099f * s) * 127.999f + 127.5f), 0), 255) << 8
						| Math.min(Math.max((int)((0.0259040371f * l + 0.7827717662f * m - 0.8086757660f * s) * 127.999f + 127.5f), 0), 255) << 16
						| ((int)(color.a * 255f) << 24 & 0xFE000000));
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
		final float l = cbrt(0.4121656120f * r + 0.5362752080f * g + 0.0514575653f * b);
		final float m = cbrt(0.2118591070f * r + 0.6807189584f * g + 0.1074065790f * b);
		final float s = cbrt(0.0883097947f * r + 0.2818474174f * g + 0.6302613616f * b);
		return NumberUtils.intBitsToFloat(
				          Math.min(Math.max((int)((0.2104542553f * l + 0.7936177850f * m - 0.0040720468f * s) * 255.999f         ), 0), 255)
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
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		final float L = (decoded & 0xff) / 255f;
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
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		final float L = (decoded & 0xff) / 255f;
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
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		final float L = (decoded & 0xff) / 255f;
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
		return (NumberUtils.floatToRawIntBits(encoded) & 0xfe000000) >>> 24;
	}

	/**
	 * Gets the red channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #oklab(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the red channel value of the given encoded color
	 */
	public static float red(final float encoded)
	{
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		final float L = (decoded & 0xff) / 255f;
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
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		final float L = (decoded & 0xff) / 255f;
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
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		final float L = (decoded & 0xff) / 255f;
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
		return ((NumberUtils.floatToRawIntBits(encoded) & 0xfe000000) >>> 24) * 0x1.020408p-8f;
	}

	/**
	 * Gets the "chroma" or "colorfulness" of a given Oklab color. Chroma is similar to saturation in that grayscale
	 * values have 0 saturation and 0 chroma, while brighter colors have high saturation and chroma. The difference is
	 * that colors that are perceptually more-colorful have higher chroma that colors that are perceptually
	 * less-colorful, regardless of hue, whereas saturation changes its range depending on how colorful a value can be
	 * at its hue. That is, the most saturated color always has a saturation of 1, but if that color isn't perceptually
	 * very colorful, it could have a chroma that is somewhat lower than 1. I don't yet know the range of this function,
	 * other than it can't be negative, grayscale values have 0 chroma, and the most colorful values should be near 1,
	 * maybe as high as the square root of 2.
	 * @param encoded a color as a packed float that can be obtained by {@link #oklab(float, float, float, float)}
	 * @return a non-negative float that represents how colorful the given value is
	 */
	public static float chroma(final float encoded) {
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		final float a = ((decoded >>> 7 & 0x1FE) - 255) / 255f;
		final float b = ((decoded >>> 15 & 0x1FE) - 255) / 255f;
		return (float) Math.sqrt(a * a + b * b);
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
	 *
	 * @param hue        0f to 1f, color wheel position
	 * @param saturation 0f to 1f, 0f is grayscale and 1f is brightly colored
	 * @param lightness  0f to 1f, 0f is black and 1f is white
	 * @param opacity    0f to 1f, 0f is fully transparent and 1f is opaque
	 * @return a float encoding a color with the given properties
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
	 * @param encoded a color as a packed float that can be obtained by {@link #oklab(float, float, float, float)}
	 * @return the saturation of the color from 0.0 (a grayscale color; inclusive) to 1.0 (a bright color, inclusive)
	 */
	public static float saturation(final float encoded) {
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
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
	 * Defined as per HSL; normally you only need {@link #channelL(float)} to get accurate lightness for Oklab.
	 * This ranges from 0.0f (black) to 1.0f (white).
	 * @param encoded a packed float Oklab color
	 * @return the lightness of the given color as HSL would calculate it
	 */
	public static float lightness(final float encoded) {
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
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
	 * to 1f (exclusive, red and approaching purple if decreased).
	 * @param encoded a color as a packed float that can be obtained by {@link #oklab(float, float, float, float)}
	 * @return The hue of the color from 0.0 (red, inclusive) towards orange, then yellow, and
	 * eventually to purple before looping back to almost the same red (1.0, exclusive)
	 */
	public static float hue(final float encoded) {
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
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
		return (NumberUtils.floatToRawIntBits(encoded) & 0xff) / 255f;
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
		return ((NumberUtils.floatToRawIntBits(encoded) >>> 8 & 0xff)) / 255f;
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
		return ((NumberUtils.floatToRawIntBits(encoded) >>> 16 & 0xff)) / 255f;
	}

	/**
	 * Gets a variation on the packed float color basis as another packed float that has its hue, saturation, value, and
	 * opacity adjusted by the specified amounts. Takes floats representing the amounts of change to apply to hue,
	 * saturation, value, and opacity; these can be between -1f and 1f. Returns a float that can be used as a packed or
	 * encoded color with methods like {@link com.badlogic.gdx.graphics.g2d.Batch#setPackedColor(float)}. The float is
	 * likely to be different than the result of {@link #oklab(float, float, float, float)} unless hue, saturation,
	 * value, and opacity are all 0. This won't allocate any objects.
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
		final int decoded = NumberUtils.floatToRawIntBits(basis);
		final float L = Math.min(Math.max(value + (decoded & 0xff) / 255f, 0f), 1f);
		opacity = Math.min(Math.max(opacity + (decoded >>> 24 & 0xfe) * 0x1.020408p-8f, 0f), 1f);
		if (L <= 0.001f)
			return NumberUtils.intBitsToFloat((((int) (opacity * 255f) << 24) & 0xFE000000) | 0x808000);
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
		float hue2 = Math.abs(z + (w - y) / (6f * d + 1e-10f));
		float sat2 = (x - L) / (Math.min(L, 1f - L) + 1e-10f);
		return fromRGBA(FloatColors.hsl2rgb(hue2 + hue + 1 - (int)(hue2 + hue + 1), Math.min(Math.max(saturation + sat2, 0f), 1f), L, opacity));
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
		final int s = NumberUtils.floatToRawIntBits(start), i = s & 0xFF, other = s & 0xFEFFFF00;
		return NumberUtils.intBitsToFloat(((int) (i + (0xFF - i) * change) & 0xFF) | other);
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
		final int s = NumberUtils.floatToRawIntBits(start), i = s & 0xFF, other = s & 0xFEFFFF00;
		return NumberUtils.intBitsToFloat(((int) (i * (1f - change)) & 0xFF) | other);
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
		final int s = NumberUtils.floatToRawIntBits(start), p = s >>> 8 & 0xFF, other = s & 0xFEFF00FF;
		return NumberUtils.intBitsToFloat(((int) (p + (0xFF - p) * change) << 8 & 0xFF00) | other);
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
		final int s = NumberUtils.floatToRawIntBits(start), p = s >>> 8 & 0xFF, other = s & 0xFEFF00FF;
		return NumberUtils.intBitsToFloat(((int) (p * (1f - change)) & 0xFF) << 8 | other);
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
		final int s = NumberUtils.floatToRawIntBits(start), t = s >>> 16 & 0xFF, other = s & 0xFE00FFFF;
		return NumberUtils.intBitsToFloat(((int) (t + (0xFF - t) * change) << 16 & 0xFF0000) | other);
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
		final int s = NumberUtils.floatToRawIntBits(start), t = s >>> 16 & 0xFF, other = s & 0xFE00FFFF;
		return NumberUtils.intBitsToFloat(((int) (t * (1f - change)) & 0xFF) << 16 | other);
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
		final int s = NumberUtils.floatToRawIntBits(start), opacity = s >>> 24 & 0xFE, other = s & 0x00FFFFFF;
		return NumberUtils.intBitsToFloat(((int) (opacity + (0xFE - opacity) * change) & 0xFE) << 24 | other);
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
		final int s = NumberUtils.floatToRawIntBits(start), opacity = s & 0xFE, other = s & 0x00FFFFFF;
		return NumberUtils.intBitsToFloat(((int) (opacity * (1f - change)) & 0xFE) << 24 | other);
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
		final int s = NumberUtils.floatToRawIntBits(start);
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
		final int s = NumberUtils.floatToRawIntBits(start);
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
		return NumberUtils.intBitsToFloat((bits & 0xFEFFFF00) | (int) (cL < 128 ? L * 0.45f + 140 : 127 - L * 0.45f));
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
		final int e = NumberUtils.floatToRawIntBits(color),
				sL = 0x80, sA = 0x80, sB = 0x80, sAlpha = 0xFE,
				eL = (e & 0xFF), eA = (e >>> 8) & 0xFF, eB = (e >>> 16) & 0xFF, eAlpha = e >>> 24 & 0xFE;
		return NumberUtils.intBitsToFloat(((int) (sL + fraction * (eL - sL)) & 0xFF)
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
			if(dist <= limit && inGamut(x += L, y = (A + y) * 0.5f + 0.5f, z = (B + z) * 0.5f + 0.5f))
				return NumberUtils.intBitsToFloat((decoded & 0xFE000000) | ((int)(z * 255.5f) << 16 & 0xFF0000)
					| ((int)(y * 255.5f) << 8 & 0xFF00) | (int)(x * 255.5f));
		}
		return color;
	}

	/**
	 * Returns true if the given packed float color, as Oklab, is valid to convert losslessly back to RGBA.
	 * @param packed a packed float color as Oklab
	 * @return true if the given packed float color can be converted back and forth to RGBA
	 */
	public static boolean inGamut(final float packed)
	{
		final int decoded = NumberUtils.floatToRawIntBits(packed);
		final float L = (decoded & 0xff) / 255f;
		final float A = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float B = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = cube(L + 0.3963377774f * A + 0.2158037573f * B);
		final float m = cube(L - 0.1055613458f * A - 0.0638541728f * B);
		final float s = cube(L - 0.0894841775f * A - 1.2914855480f * B);

		final float r = +4.0767245293f * l - 3.3072168827f * m + 0.2307590544f * s;
		if(r < 0f || r > 1.0f) return false;
		final float g = -1.2681437731f * l + 2.6093323231f * m - 0.3411344290f * s;
		if(g < 0f || g > 1.0f) return false;
		final float b = -0.0041119885f * l - 0.7034763098f * m + 1.7068625689f * s;
		return (b >= 0f && b <= 1.0f);
	}
	/**
	 * Returns true if the given Oklab values are valid to convert losslessly back to RGBA.
	 * @param L lightness channel, as a float from 0 to 1
	 * @param A green-to-red chromatic channel, as a float from 0 to 1
	 * @param B blue-to-yellow chromatic channel, as a float from 0 to 1
	 * @return true if the given packed float color can be converted back and forth to RGBA
	 */
	public static boolean inGamut(float L, float A, float B)
	{
		A = (A - 0.5f) * 2f;
		B = (B - 0.5f) * 2f;
		final float l = cube(L + 0.3963377774f * A + 0.2158037573f * B);
		final float m = cube(L - 0.1055613458f * A - 0.0638541728f * B);
		final float s = cube(L - 0.0894841775f * A - 1.2914855480f * B);

		final float r = +4.0767245293f * l - 3.3072168827f * m + 0.2307590544f * s;
//		if(r < 0f || r > 1.0f) {
//			System.out.println("r out of gamut: " + r);
//			return false;
//		}
		if(r < 0f || r > 1.0f) return false;
		final float g = -1.2681437731f * l + 2.6093323231f * m - 0.3411344290f * s;
//		if(g < 0f || g > 1.0f) {
//			System.out.println("g out of gamut: " + g);
//			return false;
//		}
		if(g < 0f || g > 1.0f) return false;
		final float b = -0.0041119885f * l - 0.7034763098f * m + 1.7068625689f * s;
//		if(b < 0f || b > 1.0f) {
//			System.out.println("b out of gamut: " + b);
//			return false;
//		}
//		return true;
		return (b >= 0f && b <= 1.0f);
	}

	/**
	 * Iteratively checks whether the given Oklab color is in-gamut, and either brings the color closer to 50% gray if it
	 * isn't in-gamut, or returns it as soon as it is in-gamut.
	 * @param packed a packed float color in Oklab format; often this color is not in-gamut
	 * @return the first color this finds that is between the given Oklab color and 50% gray, and is in-gamut
	 * @see #inGamut(float) You can use inGamut() if you just want to check whether a color is in-gamut.
	 */
	public static float limitToGamut(final float packed) {
		final int decoded = NumberUtils.floatToRawIntBits(packed);
		final float L = (decoded & 0xff) / 255f;
		final float A = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float B = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		float L2 = L, A2 = A, B2 = B;
		for (int attempt = 31; attempt >= 0; attempt--) {
			final float l = cube(L2 + 0.3963377774f * A2 + 0.2158037573f * B2);
			final float m = cube(L2 - 0.1055613458f * A2 - 0.0638541728f * B2);
			final float s = cube(L2 - 0.0894841775f * A2 - 1.2914855480f * B2);

			final float r = +4.0767245293f * l - 3.3072168827f * m + 0.2307590544f * s;
			final float g = -1.2681437731f * l + 2.6093323231f * m - 0.3411344290f * s;
			final float b = -0.0041119885f * l - 0.7034763098f * m + 1.7068625689f * s;
			if(r >= 0f && r <= 1f && g >= 0f && g <= 1f && b >= 0f && b <= 1f)
				break;
			final float progress = attempt * 0x1p-5f;
			L2 = MathUtils.lerp(0.63f, L, progress);
			A2 = MathUtils.lerp(0, A, progress);
			B2 = MathUtils.lerp(0, B, progress);
		}
		return oklab(L2, A2 * 0.5f + 0.5f, B2 * 0.5f + 0.5f, (decoded >>> 25) / 127f);
	}

	/**
	 * Iteratively checks whether the given Oklab color is in-gamut, and either brings the color closer to 50% gray if it
	 * isn't in-gamut, or returns it as soon as it is in-gamut. This always produces an opaque color.
	 * @param L lightness component; will be clamped between 0 and 1 if it isn't already
	 * @param A green-to-red chromatic component; will be clamped between 0 and 1 if it isn't already
	 * @param B blue-to-yellow chromatic component; will be clamped between 0 and 1 if it isn't already
	 * @return the first color this finds that is between the given Oklab color and 50% gray, and is in-gamut
	 * @see #inGamut(float, float, float)  You can use inGamut() if you just want to check whether a color is in-gamut.
	 */
	public static float limitToGamut(float L, float A, float B) {
		return limitToGamut(L, A, B, 1f);
	}
	/**
	 * Iteratively checks whether the given Oklab color is in-gamut, and either brings the color closer to 50% gray if it
	 * isn't in-gamut, or returns it as soon as it is in-gamut.
	 * @param L lightness component; will be clamped between 0 and 1 if it isn't already
	 * @param A green-to-red chromatic component; will be clamped between 0 and 1 if it isn't already
	 * @param B blue-to-yellow chromatic component; will be clamped between 0 and 1 if it isn't already
	 * @param alpha alpha component; will be clamped between 0 and 1 if it isn't already
	 * @return the first color this finds that is between the given Oklab color and 50% gray, and is in-gamut
	 * @see #inGamut(float, float, float)  You can use inGamut() if you just want to check whether a color is in-gamut.
	 */
	public static float limitToGamut(float L, float A, float B, float alpha) {
		float L2 = L = Math.min(Math.max(L, 0f), 1f);
		float A2 = A = Math.min(Math.max((A - 0.5f) * 2f, -1f), 1f);
		float B2 = B = Math.min(Math.max((B - 0.5f) * 2f, -1f), 1f);
		alpha = Math.min(Math.max(alpha, 0f), 1f);
		for (int attempt = 31; attempt >= 0; attempt--) {
			final float l = cube(L2 + 0.3963377774f * A2 + 0.2158037573f * B2);
			final float m = cube(L2 - 0.1055613458f * A2 - 0.0638541728f * B2);
			final float s = cube(L2 - 0.0894841775f * A2 - 1.2914855480f * B2);

			final float r = +4.0767245293f * l - 3.3072168827f * m + 0.2307590544f * s;
			final float g = -1.2681437731f * l + 2.6093323231f * m - 0.3411344290f * s;
			final float b = -0.0041119885f * l - 0.7034763098f * m + 1.7068625689f * s;
			if(r >= 0f && r <= 1f && g >= 0f && g <= 1f && b >= 0f && b <= 1f)
				break;
			final float progress = attempt * 0x1p-5f;
			L2 = MathUtils.lerp(0.63f, L, progress);
			A2 = MathUtils.lerp(0, A, progress);
			B2 = MathUtils.lerp(0, B, progress);
		}
		return oklab(L2, A2 * 0.5f + 0.5f, B2 * 0.5f + 0.5f, alpha);
	}

	/**
	 * Produces a random packed float color that is always in-gamut (and opaque) and should be uniformly distributed.
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
		return oklab(L, A, B, 1f);
	}
}
