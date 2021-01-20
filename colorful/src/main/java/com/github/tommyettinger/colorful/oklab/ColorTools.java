package com.github.tommyettinger.colorful.oklab;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.NumberUtils;
import com.github.tommyettinger.colorful.FloatColors;
import com.github.tommyettinger.colorful.Shaders;
import com.github.tommyettinger.colorful.ycwcm.Palette;

import java.util.Random;

/**
 * Contains code for manipulating colors as {@code int} and packed {@code float} values in the Oklab color space.
 * Oklab is a very new color space that builds on the same foundation as IPT, but seems to be better-calibrated for
 * uniform lightness and colorfulness, instead of just the emphasis on uniform hue that IPT has.
 * Here's <a href="https://bottosson.github.io/posts/oklab/">Ottosson's original post introducing Oklab</>.
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
	 * A, and B values if the original values were in the range that {@link #intensity(float)}, {@link #protan(float)},
	 * and {@link #tritan(float)} return. You can use {@link #inGamut(float, float, float)} to check if a given set of
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
	 * @param component any non-linear channel of a color, to be made linear
	 * @return a linear version of component
	 */
	private static float forwardGamma(final float component) {
		return component * component;
	}

	/**
	 * Used to return from a linear, gamma-corrected input to an sRGB, non-linear output, using gamma 2.0.
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
		final int r = (int)(reverseGamma(MathUtils.clamp(+4.0767245293f * l - 3.3072168827f * m + 0.2307590544f * s, 0f, 1f)) * 255.999f);
		final int g = (int)(reverseGamma(MathUtils.clamp(-1.2681437731f * l + 2.6093323231f * m - 0.3411344290f * s, 0f, 1f)) * 255.999f);
		final int b = (int)(reverseGamma(MathUtils.clamp(-0.0041119885f * l - 0.7034763098f * m + 1.7068625689f * s, 0f, 1f)) * 255.999f);
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
		final int r = (int)(reverseGamma(MathUtils.clamp(+4.0767245293f * l - 3.3072168827f * m + 0.2307590544f * s, 0f, 1f)) * 255.999f);
		final int g = (int)(reverseGamma(MathUtils.clamp(-1.2681437731f * l + 2.6093323231f * m - 0.3411344290f * s, 0f, 1f)) * 255.999f);
		final int b = (int)(reverseGamma(MathUtils.clamp(-0.0041119885f * l - 0.7034763098f * m + 1.7068625689f * s, 0f, 1f)) * 255.999f);
		return NumberUtils.intBitsToFloat(r | g << 8 | b << 16 | (decoded & 0xfe000000));
	}
	/**
	 * Writes an IPT-format packed float color (the format produced by {@link ColorTools#oklab(float, float, float, float)})
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
		editing.r = reverseGamma(MathUtils.clamp(+4.0767245293f * l - 3.3072168827f * m + 0.2307590544f * s, 0f, 1f));
		editing.g = reverseGamma(MathUtils.clamp(-1.2681437731f * l + 2.6093323231f * m - 0.3411344290f * s, 0f, 1f));
		editing.b = reverseGamma(MathUtils.clamp(-0.0041119885f * l - 0.7034763098f * m + 1.7068625689f * s, 0f, 1f));
		editing.a = (decoded >>> 25) * 0x1.020408p-7f; // this is 1/127 as a float
		return editing.clamp();
	}

	/**
	 * Takes a color encoded as an RGBA8888 int and converts to a packed float in the IPT format this uses.
	 * @param rgba an int with the channels (in order) red, green, blue, alpha; should have 8 bits per channel
	 * @return a packed float as IPT, which this class can use
	 */
	public static float fromRGBA8888(final int rgba) {
		final float r = forwardGamma((rgba >>> 24) * 0x1.010101010101p-8f);
		final float g = forwardGamma((rgba >>> 16 & 0xFF) * 0x1.010101010101p-8f);
		final float b = forwardGamma((rgba >>> 8 & 0xFF) * 0x1.010101010101p-8f);

		final float l = cbrt(0.4121656120f * r + 0.5362752080f * g + 0.0514575653f * b);
		final float m = cbrt(0.2118591070f * r + 0.6807189584f * g + 0.1074065790f * b);
		final float s = cbrt(0.0883097947f * r + 0.2818474174f * g + 0.6302613616f * b);

		return NumberUtils.intBitsToFloat(
			              MathUtils.clamp((int)((0.2104542553f * l + 0.7936177850f * m - 0.0040720468f * s       ) * 255.999f), 0, 255)
						| MathUtils.clamp((int)((1.9779984951f * l - 2.4285922050f * m + 0.4505937099f * s + 0.5f) * 255.999f), 0, 255) << 8
						| MathUtils.clamp((int)((0.0259040371f * l + 0.7827717662f * m - 0.8086757660f * s + 0.5f) * 255.999f), 0, 255) << 16
						| (rgba & 0xFE) << 24);
	}

	/**
	 * Takes a color encoded as an RGBA8888 packed float and converts to a packed float in the IPT format this uses.
	 * @param packed a packed float in RGBA8888 format, with A in the MSB and R in the LSB
	 * @return a packed float as IPT, which this class can use
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
				          MathUtils.clamp((int)((0.2104542553f * l + 0.7936177850f * m - 0.0040720468f * s       ) * 255.999f), 0, 255)
						| MathUtils.clamp((int)((1.9779984951f * l - 2.4285922050f * m + 0.4505937099f * s + 0.5f) * 255.999f), 0, 255) << 8
						| MathUtils.clamp((int)((0.0259040371f * l + 0.7827717662f * m - 0.8086757660f * s + 0.5f) * 255.999f), 0, 255) << 16
						| (abgr & 0xFE000000));
	}

	/**
	 * Takes a libGDX Color that uses RGBA8888 channels and converts to a packed float in the IPT format this uses.
	 * @param color a libGDX RGBA8888 Color
	 * @return a packed float as IPT, which this class can use
	 */
	public static float fromColor(final Color color) {
		final float r = forwardGamma(color.r);
		final float g = forwardGamma(color.g);
		final float b = forwardGamma(color.b);
		final float l = cbrt(0.4121656120f * r + 0.5362752080f * g + 0.0514575653f * b);
		final float m = cbrt(0.2118591070f * r + 0.6807189584f * g + 0.1074065790f * b);
		final float s = cbrt(0.0883097947f * r + 0.2818474174f * g + 0.6302613616f * b);
		return NumberUtils.intBitsToFloat(
				          MathUtils.clamp((int)((0.2104542553f * l + 0.7936177850f * m - 0.0040720468f * s       ) * 255.999f), 0, 255)
						| MathUtils.clamp((int)((1.9779984951f * l - 2.4285922050f * m + 0.4505937099f * s + 0.5f) * 255.999f), 0, 255) << 8
						| MathUtils.clamp((int)((0.0259040371f * l + 0.7827717662f * m - 0.8086757660f * s + 0.5f) * 255.999f), 0, 255) << 16
						| ((int)(color.a * 255f) << 24 & 0xFE000000));
	}

	/**
	 * Takes RGBA components from 0.0 to 1.0 each and converts to a packed float in the IPT format this uses.
	 * @param r red, from 0.0 to 1.0 (both inclusive)
	 * @param g green, from 0.0 to 1.0 (both inclusive)
	 * @param b blue, from 0.0 to 1.0 (both inclusive)
	 * @param a alpha, from 0.0 to 1.0 (both inclusive)
	 * @return a packed float as IPT, which this class can use
	 */
	public static float fromRGBA(float r, float g, float b, final float a) {
		r = forwardGamma(r);
		g = forwardGamma(g);
		b = forwardGamma(b);
		final float l = cbrt(0.4121656120f * r + 0.5362752080f * g + 0.0514575653f * b);
		final float m = cbrt(0.2118591070f * r + 0.6807189584f * g + 0.1074065790f * b);
		final float s = cbrt(0.0883097947f * r + 0.2818474174f * g + 0.6302613616f * b);
		return NumberUtils.intBitsToFloat(
				          MathUtils.clamp((int)((0.2104542553f * l + 0.7936177850f * m - 0.0040720468f * s       ) * 255.999f), 0, 255)
						| MathUtils.clamp((int)((1.9779984951f * l - 2.4285922050f * m + 0.4505937099f * s + 0.5f) * 255.999f), 0, 255) << 8
						| MathUtils.clamp((int)((0.0259040371f * l + 0.7827717662f * m - 0.8086757660f * s + 0.5f) * 255.999f), 0, 255) << 16
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
		final float i = (decoded & 0xff) / 255f;
		final float p = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float t = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = cube(i + 0.097569f * p + 0.205226f * t);
		final float m = cube(i + -0.11388f * p + 0.133217f * t);
		final float s = cube(i + 0.032615f * p + -0.67689f * t);
		return (int)(reverseGamma(MathUtils.clamp(5.432622f * l + -4.67910f * m + 0.246257f * s, 0f, 1f)) * 255.999f);
	}

	/**
	 * Gets the green channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #oklab(float, float, float, float)}
	 * @return an int from 0 to 255, inclusive, representing the green channel value of the given encoded color
	 */
	public static int greenInt(final float encoded)
	{
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		final float i = (decoded & 0xff) / 255f;
		final float p = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float t = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = cube(i + 0.097569f * p + 0.205226f * t);
		final float m = cube(i + -0.11388f * p + 0.133217f * t);
		final float s = cube(i + 0.032615f * p + -0.67689f * t);
		return (int)(reverseGamma(MathUtils.clamp(-1.10517f * l + 2.311198f * m + -0.20588f * s, 0f, 1f)) * 255.999f);
	}

	/**
	 * Gets the blue channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #oklab(float, float, float, float)}
	 * @return an int from 0 to 255, inclusive, representing the blue channel value of the given encoded color
	 */
	public static int blueInt(final float encoded)
	{
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		final float i = (decoded & 0xff) / 255f;
		final float p = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float t = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = cube(i + 0.097569f * p + 0.205226f * t);
		final float m = cube(i + -0.11388f * p + 0.133217f * t);
		final float s = cube(i + 0.032615f * p + -0.67689f * t);
		return (int)(reverseGamma(MathUtils.clamp(0.028104f * l + -0.19466f * m + 1.166325f * s, 0f, 1f)) * 255.999f);
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
		final float i = (decoded & 0xff) / 255f;
		final float p = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float t = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = cube(i + 0.097569f * p + 0.205226f * t);
		final float m = cube(i + -0.11388f * p + 0.133217f * t);
		final float s = cube(i + 0.032615f * p + -0.67689f * t);
		return reverseGamma(MathUtils.clamp(5.432622f * l + -4.67910f * m + 0.246257f * s, 0f, 1f));
	}

	/**
	 * Gets the green channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #oklab(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the green channel value of the given encoded color
	 */
	public static float green(final float encoded)
	{
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		final float i = (decoded & 0xff) / 255f;
		final float p = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float t = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = cube(i + 0.097569f * p + 0.205226f * t);
		final float m = cube(i + -0.11388f * p + 0.133217f * t);
		final float s = cube(i + 0.032615f * p + -0.67689f * t);
		return reverseGamma(MathUtils.clamp(-1.10517f * l + 2.311198f * m + -0.20588f * s, 0f, 1f));
	}

	/**
	 * Gets the blue channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #oklab(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the blue channel value of the given encoded color
	 */
	public static float blue(final float encoded)
	{
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		final float i = (decoded & 0xff) / 255f;
		final float p = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float t = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = cube(i + 0.097569f * p + 0.205226f * t);
		final float m = cube(i + -0.11388f * p + 0.133217f * t);
		final float s = cube(i + 0.032615f * p + -0.67689f * t);
		return reverseGamma(MathUtils.clamp(0.028104f * l + -0.19466f * m + 1.166325f * s, 0f, 1f));
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
	 * Gets a color as an IPT packed float given floats representing hue, saturation, lightness, and opacity.
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
	 * Gets the saturation of the given encoded color, as a float ranging from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #oklab(float, float, float, float)}
	 * @return the saturation of the color from 0.0 (a grayscale color; inclusive) to 1.0 (a bright color, inclusive)
	 */
	public static float saturation(final float encoded) {
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		final float i = (decoded & 0xff) / 255f;
		if(Math.abs(i - 0.5) > 0.495f) return 0f;
		final float p = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float t = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = cube(i + 0.097569f * p + 0.205226f * t);
		final float m = cube(i + -0.11388f * p + 0.133217f * t);
		final float s = cube(i + 0.032615f * p + -0.67689f * t);
		final float r = reverseGamma(MathUtils.clamp(5.432622f * l + -4.67910f * m + 0.246257f * s, 0f, 1f));
		final float g = reverseGamma(MathUtils.clamp(-1.10517f * l + 2.311198f * m + -0.20588f * s, 0f, 1f));
		final float b = reverseGamma(MathUtils.clamp(0.028104f * l + -0.19466f * m + 1.166325f * s, 0f, 1f));
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
//		float d = x - Math.min(w, y);
//		float li = x * (1f - 0.5f * d / (x + 1e-10f));
//		return (x - li); // (Math.min(li, 1f - li) + 1e-10f);
	}

	public static float lightness(final float encoded) {
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		final float i = (decoded & 0xff) / 255f;
		final float p = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float t = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = cube(i + 0.097569f * p + 0.205226f * t);
		final float m = cube(i + -0.11388f * p + 0.133217f * t);
		final float s = cube(i + 0.032615f * p + -0.67689f * t);
		final float r = reverseGamma(MathUtils.clamp(5.432622f * l + -4.67910f * m + 0.246257f * s, 0f, 1f));
		final float g = reverseGamma(MathUtils.clamp(-1.10517f * l + 2.311198f * m + -0.20588f * s, 0f, 1f));
		final float b = reverseGamma(MathUtils.clamp(0.028104f * l + -0.19466f * m + 1.166325f * s, 0f, 1f));

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
		final float i = (decoded & 0xff) / 255f;
		final float p = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float t = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = cube(i + 0.097569f * p + 0.205226f * t);
		final float m = cube(i + -0.11388f * p + 0.133217f * t);
		final float s = cube(i + 0.032615f * p + -0.67689f * t);
		final float r = reverseGamma(MathUtils.clamp(5.432622f * l + -4.67910f * m + 0.246257f * s, 0f, 1f));
		final float g = reverseGamma(MathUtils.clamp(-1.10517f * l + 2.311198f * m + -0.20588f * s, 0f, 1f));
		final float b = reverseGamma(MathUtils.clamp(0.028104f * l + -0.19466f * m + 1.166325f * s, 0f, 1f));
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
	 * The "intensity" of the given packed float in IPT format, which is like its lightness; ranges from 0.0f to
	 * 1.0f . You can edit the intensity of a color with {@link #lighten(float, float)} and
	 * {@link #darken(float, float)}.
	 *
	 * @param encoded a color encoded as a packed float, as by {@link #oklab(float, float, float, float)}
	 * @return the intensity value as a float from 0.0f to 1.0f
	 */
	public static float intensity(final float encoded)
	{
		return (NumberUtils.floatToRawIntBits(encoded) & 0xff) / 255f;
	}

	/**
	 * The "protan" of the given packed float in IPT format, which when combined with tritan describes the
	 * hue and saturation of a color; ranges from 0f to 1f . If protan is 0f, the color will be cooler, more green or
	 * blue; if protan is 1f, the color will be warmer, from magenta to orange. You can edit the protan of a color with
	 * {@link #protanUp(float, float)} and {@link #protanDown(float, float)}.
	 * @param encoded a color encoded as a packed float, as by {@link #oklab(float, float, float, float)}
	 * @return the protan value as a float from 0.0f to 1.0f
	 */
	public static float protan(final float encoded)
	{
		return ((NumberUtils.floatToRawIntBits(encoded) >>> 8 & 0xff)) / 255f;
	}

	/**
	 * The "tritan" of the given packed float in IPT format, which when combined with protan describes the
	 * hue and saturation of a color; ranges from 0f to 1f . If tritan is 0f, the color will be more "artificial", more
	 * blue or purple; if tritan is 1f, the color will be more "natural", from green to yellow to orange. You can edit
	 * the tritan of a color with {@link #tritanUp(float, float)} and {@link #tritanDown(float, float)}.
	 * @param encoded a color encoded as a packed float, as by {@link #oklab(float, float, float, float)}
	 * @return the tritan value as a float from 0.0f to 1.0f
	 */
	public static float tritan(final float encoded)
	{
		return ((NumberUtils.floatToRawIntBits(encoded) >>> 16 & 0xff)) / 255f;
	}

	/**
	 * Gets a variation on the packed float color basis as another packed float that has its hue, saturation, value, and
	 * opacity adjusted by the specified amounts. Takes floats representing the amounts of change to apply to hue,
	 * saturation, value, and opacity; these can be between -1f and 1f. Returns a float that can be used as a packed or
	 * encoded color with methods like {@link com.badlogic.gdx.graphics.g2d.Batch#setPackedColor(float)}. The float is
	 * likely to be different than the result of {@link #oklab(float, float, float, float)} unless hue saturation,
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
		final int e = NumberUtils.floatToRawIntBits(basis);
		final float i = MathUtils.clamp(value + (e & 0xff) / 255f, 0f, 1f);
		opacity = MathUtils.clamp(opacity + (e >>> 24 & 0xfe) * 0x1.020408p-8f, 0f, 1f);
		if (i <= 0.001f)
			return NumberUtils.intBitsToFloat((((int) (opacity * 255f) << 24) & 0xFE000000) | 0x808000);
		final float p = ((e >>> 7 & 0x1fe) - 0xff) / 255f;
		final float t = ((e >>> 15 & 0x1fe) - 0xff) / 255f;
		final float l = cube(i + 0.097569f * p + 0.205226f * t);
		final float m = cube(i + -0.11388f * p + 0.133217f * t);
		final float s = cube(i + 0.032615f * p + -0.67689f * t);
		final float r = reverseGamma(MathUtils.clamp(5.432622f * l + -4.67910f * m + 0.246257f * s, 0f, 1f));
		final float g = reverseGamma(MathUtils.clamp(-1.10517f * l + 2.311198f * m + -0.20588f * s, 0f, 1f));
		final float b = reverseGamma(MathUtils.clamp(0.028104f * l + -0.19466f * m + 1.166325f * s, 0f, 1f));
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
		float sat2 = (x - i) / (Math.min(i, 1f - i) + 1e-10f);
		return fromRGBA(FloatColors.hsl2rgb(hue2 + hue + 1 - (int)(hue2 + hue + 1), MathUtils.clamp(saturation + sat2, 0f, 1f), i, opacity));
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
	 * alpha and intensity of start as-is.
	 * @see #protanDown(float, float) the counterpart method that cools a float color
	 * @param start the starting color as a packed float
	 * @param change how much to warm start, as a float between 0 and 1; higher means a warmer result
	 * @return a packed float that represents a color between start and a warmer color
	 */
	public static float protanUp(final float start, final float change) {
		final int s = NumberUtils.floatToRawIntBits(start), p = s >>> 8 & 0xFF, other = s & 0xFEFF00FF;
		return NumberUtils.intBitsToFloat(((int) (p + (0xFF - p) * change) << 8 & 0xFF00) | other);
	}

	/**
	 * Interpolates from the packed float color start towards a cooler color (green to blue) by change. While change
	 * should be between 0f (return start as-is) and 1f (return fully cooled), start should be a packed color, as from
	 * {@link #oklab(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
	 * is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp
	 * towards a cooler color. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and
	 * intensity of start as-is.
	 * @see #protanUp(float, float) the counterpart method that warms a float color
	 * @param start the starting color as a packed float
	 * @param change how much to cool start, as a float between 0 and 1; higher means a cooler result
	 * @return a packed float that represents a color between start and a cooler color
	 */
	public static float protanDown(final float start, final float change) {
		final int s = NumberUtils.floatToRawIntBits(start), p = s >>> 8 & 0xFF, other = s & 0xFEFF00FF;
		return NumberUtils.intBitsToFloat(((int) (p * (1f - change)) & 0xFF) << 8 | other);
	}

	/**
	 * Interpolates from the packed float color start towards a "natural" color (between green and orange) by change.
	 * While change should be between 0f (return start as-is) and 1f (return fully natural), start should be a packed color, as
	 * from {@link #oklab(float, float, float, float)}. This is a good way to reduce allocations of temporary
	 * Colors, and is a little more efficient and clear than using
	 * {@link FloatColors#lerpFloatColors(float, float, float)} to lerp towards a more natural color. Unlike
	 * {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and intensity of start as-is.
	 * @see #tritanDown(float, float) the counterpart method that makes a float color less natural
	 * @param start the starting color as a packed float
	 * @param change how much to change start to a natural color, as a float between 0 and 1; higher means a more natural result
	 * @return a packed float that represents a color between start and a more natural color
	 */
	public static float tritanUp(final float start, final float change) {
		final int s = NumberUtils.floatToRawIntBits(start), t = s >>> 16 & 0xFF, other = s & 0xFE00FFFF;
		return NumberUtils.intBitsToFloat(((int) (t + (0xFF - t) * change) << 16 & 0xFF0000) | other);
	}

	/**
	 * Interpolates from the packed float color start towards an "artificial" color (between blue and purple) by change.
	 * While change should be between 0f (return start as-is) and 1f (return fully artificial), start should be a packed color, as
	 * from {@link #oklab(float, float, float, float)}. This is a good way to reduce allocations of temporary
	 * Colors, and is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp
	 * towards a more artificial color. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the
	 * alpha and intensity of start as-is.
	 * @see #tritanUp(float, float) the counterpart method that makes a float color less artificial
	 * @param start the starting color as a packed float
	 * @param change how much to change start to a bolder color, as a float between 0 and 1; higher means a more artificial result
	 * @return a packed float that represents a color between start and a more artificial color
	 */
	public static float tritanDown(final float start, final float change) {
		final int s = NumberUtils.floatToRawIntBits(start), t = s >>> 16 & 0xFF, other = s & 0xFE00FFFF;
		return NumberUtils.intBitsToFloat(((int) (t * (1f - change)) & 0xFF) << 16 | other);
	}

	/**
	 * Interpolates from the packed float color start towards that color made opaque by change. While change should be
	 * between 0f (return start as-is) and 1f (return start with full alpha), start should be a packed color, as from
	 * {@link #oklab(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
	 * is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp towards
	 * transparent. This won't change the intensity, protan, or tritan of the color.
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
	 * transparent. This won't change the intensity, protan, or tritan of the color.
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
	 * from {@link #oklab(float, float, float, float)}. This only changes Cw and Cm; it leaves Y and alpha alone, unlike
	 * {@link #lessenChange(float, float)}, which usually changes Y.
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
	 * from {@link #oklab(float, float, float, float)}. This usually changes only Cw and Cm, but higher values for
	 * {@code change} can force the color out of the gamut, which this corrects using
	 * {@link #limitToGamut(float, float, float, float)} (and that can change Y somewhat). If the color stays in-gamut,
	 * then Y won't change; alpha never changes.
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
	 * Given a packed float IPT color {@code mainColor} and another IPT color that it should be made to contrast with,
	 * gets a packed float IPT color with roughly inverted intnsity but the same chromatic channels and opacity (P and T
	 * are likely to be clamped if the result gets close to white or black). This won't ever produce black or other very
	 * dark colors, and also has a gap in the range it produces for intensity values between 0.5 and 0.55. That allows
	 * most of the colors this method produces to contrast well as a foreground when displayed on a background of
	 * {@code contrastingColor}, or vice versa. This will leave the intensity unchanged if the chromatic channels of the
	 * contrastingColor and those of the mainColor are already very different. This has nothing to do with the contrast
	 * channel of the tweak in ColorfulBatch; where that part of the tweak can make too-similar lightness values further
	 * apart by just a little, this makes a modification on {@code mainColor} to maximize its lightness difference from
	 * {@code contrastingColor} without losing its other qualities.
	 * @param mainColor a packed float color, as produced by {@link #oklab(float, float, float, float)}; this is the color that will be adjusted
	 * @param contrastingColor a packed float color, as produced by {@link #oklab(float, float, float, float)}; the adjusted mainColor will contrast with this
	 * @return a different IPT packed float color, based on mainColor but with potentially very different lightness
	 */
	public static float inverseIntensity(final float mainColor, final float contrastingColor)
	{
		final int bits = NumberUtils.floatToRawIntBits(mainColor),
				contrastBits = NumberUtils.floatToRawIntBits(contrastingColor),
				i = (bits & 0xff),
				p = (bits >>> 8 & 0xff),
				t = (bits >>> 16 & 0xff),
				ci = (contrastBits & 0xff),
				cp = (contrastBits >>> 8 & 0xff),
				ct = (contrastBits >>> 16 & 0xff);
		if((p - cp) * (p - cp) + (t - ct) * (t - ct) >= 0x10000)
			return mainColor;
		return oklab(ci < 128 ? i * (0.45f / 255f) + 0.55f : 0.5f - i * (0.45f / 255f), p / 255f, t / 255f, 0x1.0p-8f * (bits >>> 24));
	}

	/**
	 * Makes the additive IPT color stored in {@code color} cause less of a change when used as a tint, as if it were
	 * mixed with neutral gray. When {@code fraction} is 1.0, this returns color unchanged; when fraction is 0.0, it
	 * returns {@link Palette#GRAY}, and when it is in-between 0.0 and 1.0 it returns something between the two. This is
	 * meant for things like area of effect abilities that make smaller color changes toward their periphery.
	 * @param color a color that should have its tinting effect potentially weakened
	 * @param fraction how much of {@code color} should be kept, from 0.0 to 1.0
	 * @return an IPT float color between gray and {@code color}
	 */
	public static float lessenChange(final float color, float fraction) {
		final int e = NumberUtils.floatToRawIntBits(color),
				is = 0x80, ps = 0x80, ts = 0x80, as = 0xFE,
				ie = (e & 0xFF), pe = (e >>> 8) & 0xFF, te = (e >>> 16) & 0xFF, ae = e >>> 24 & 0xFE;
		return NumberUtils.intBitsToFloat(((int) (is + fraction * (ie - is)) & 0xFF)
				| (((int) (ps + fraction * (pe - ps)) & 0xFF) << 8)
				| (((int) (ts + fraction * (te - ts)) & 0xFF) << 16)
				| (((int) (as + fraction * (ae - as)) & 0xFE) << 24));
	}

	/**
	 * Makes a quasi-randomly-edited variant on the given {@code color}, allowing typically a small amount of
	 * {@code variance} (such as 0.05 to 0.25) between the given color and what this can return. The {@code seed} should
	 * be different each time this is called, and can be obtained from a random number generator to make the colors more
	 * random, or can be incremented on each call. If the seed is only incremented or decremented, then this shouldn't
	 * produce two similar colors in a row unless variance is very small. The variance affects the I, P, and T of the
	 * generated color, and each of those channels can go up or down by the given variance as long as the total distance
	 * isn't greater than the variance (this considers P and T extra-wide, going from -1 to 1, while I goes from 0 to 1,
	 * but only internally for measuring distance).
	 * @param color a packed float color, as produced by {@link #oklab(float, float, float, float)}
	 * @param seed a long seed that should be different on each call; should not be 0
	 * @param variance max amount of difference between the given color and the generated color; always less than 1
	 * @return a generated packed float color that should be at least somewhat different from {@code color}
	 */
	public static float randomEdit(final float color, long seed, final float variance) {
		final int decoded = NumberUtils.floatToRawIntBits(color);
		final float i = (decoded & 0xff) / 255f;
		final float p = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float t = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float limit = variance * variance;
		float dist, x, y, z;
		for (int j = 0; j < 50; j++) {
			x = (((seed * 0xD1B54A32D192ED03L >>> 41) - 0x7FFFFFp-1f) * 0x1p-22f) * variance;
			y = (((seed * 0xABC98388FB8FAC03L >>> 41) - 0x7FFFFFp-1f) * 0x1p-22f) * variance;
			z = (((seed * 0x8CB92BA72F3D8DD7L >>> 41) - 0x7FFFFFp-1f) * 0x1p-22f) * variance;
			seed += 0x9E3779B97F4A7C15L;
			dist = x * x + y * y + z * z;
			if(dist <= limit && inGamut(x += i, y = (p + y) * 0.5f + 0.5f, z = (t + z) * 0.5f + 0.5f))
				return NumberUtils.intBitsToFloat((decoded & 0xFE000000) | ((int)(z * 255.5f) << 16 & 0xFF0000)
					| ((int)(y * 255.5f) << 8 & 0xFF00) | (int)(x * 255.5f));
		}
		return color;
	}

	/**
	 * Returns true if the given packed float color, as IPT, is valid to convert losslessly back to RGBA. 
	 * @param packed a packed float color as IPT
	 * @return true if the given packed float color can be converted back and forth to RGBA
	 */
	public static boolean inGamut(final float packed)
	{
		final int decoded = NumberUtils.floatToRawIntBits(packed);
		final float i = (decoded & 0xff) / 255f;
		final float p = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float t = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = cube(i + 0.097569f * p + 0.205226f * t);
		final float m = cube(i + -0.11388f * p + 0.133217f * t);
		final float s = cube(i + 0.032615f * p + -0.67689f * t);

		final float r = 5.432622f * l + -4.67910f * m + 0.246257f * s;
		if(r < 0f || r > 1.0f) return false;
		final float g = -1.10517f * l + 2.311198f * m + -0.20588f * s;
		if(g < 0f || g > 1.0f) return false;
		final float b = 0.028104f * l + -0.19466f * m + 1.166325f * s;
		return (b >= 0f && b <= 1.0f);
	}
	/**
	 * Returns true if the given IPT values are valid to convert losslessly back to RGBA. 
	 * @param i intensity channel, as a float from 0 to 1
	 * @param p protan channel, as a float from 0 to 1
	 * @param t tritan channel, as a float from 0 to 1
	 * @return true if the given packed float color can be converted back and forth to RGBA
	 */
	public static boolean inGamut(float i, float p, float t)
	{
		p = (p - 0.5f) * 2f;
		t = (t - 0.5f) * 2f;
		final float l = cube(i + 0.097569f * p + 0.205226f * t);
		final float m = cube(i + -0.11388f * p + 0.133217f * t);
		final float s = cube(i + 0.032615f * p + -0.67689f * t);

		final float r = 5.432622f * l + -4.67910f * m + 0.246257f * s;
		if(r < 0f || r > 1.0f) return false;
		final float g = -1.10517f * l + 2.311198f * m + -0.20588f * s;
		if(g < 0f || g > 1.0f) return false;
		final float b = 0.028104f * l + -0.19466f * m + 1.166325f * s;
		return (b >= 0f && b <= 1.0f);
	}

	/**
	 * Iteratively checks whether the given IPT color is in-gamut, and either brings the color closer to 50% gray if it
	 * isn't in-gamut, or returns it as soon as it is in-gamut.
	 * @param packed a packed float color in IPT format; often this color is not in-gamut
	 * @return the first color this finds that is between the given IPT color and 50% gray, and is in-gamut
	 * @see #inGamut(float) You can use inGamut() if you just want to check whether a color is in-gamut.
	 */
	public static float limitToGamut(final float packed) {
		final int decoded = NumberUtils.floatToRawIntBits(packed);
		final float i = (decoded & 0xff) / 255f;
		final float p = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float t = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		float i2 = i, p2 = p, t2 = t;
		for (int attempt = 31; attempt >= 0; attempt--) {
			final float l = cube(i2 + 0.097569f * p2 + 0.205226f * t2);
			final float m = cube(i2 + -0.11388f * p2 + 0.133217f * t2);
			final float s = cube(i2 + 0.032615f * p2 + -0.67689f * t2);

			final float r = 5.432622f * l + -4.67910f * m + 0.246257f * s;
			final float g = -1.10517f * l + 2.311198f * m + -0.20588f * s;
			final float b = 0.028104f * l + -0.19466f * m + 1.166325f * s;
			if(r >= 0f && r <= 1f && g >= 0f && g <= 1f && b >= 0f && b <= 1f)
				break;
			final float progress = attempt * 0x1p-5f;
			i2 = MathUtils.lerp(0.55f, i, progress);
			p2 = MathUtils.lerp(0, p, progress);
			t2 = MathUtils.lerp(0, t, progress);
		}
		return oklab(i2, p2 * 0.5f + 0.5f, t2 * 0.5f + 0.5f, (decoded >>> 25) / 127f);
	}

	/**
	 * Iteratively checks whether the given IPT color is in-gamut, and either brings the color closer to 50% gray if it
	 * isn't in-gamut, or returns it as soon as it is in-gamut. This always produces an opaque color.
	 * @param i intensity component; will be clamped between 0 and 1 if it isn't already
	 * @param p protan component; will be clamped between 0 and 1 if it isn't already
	 * @param t tritan component; will be clamped between 0 and 1 if it isn't already
	 * @return the first color this finds that is between the given IPT color and 50% gray, and is in-gamut
	 * @see #inGamut(float, float, float)  You can use inGamut() if you just want to check whether a color is in-gamut.
	 */
	public static float limitToGamut(float i, float p, float t) {
		return limitToGamut(i, p, t, 1f);
	}
	/**
	 * Iteratively checks whether the given IPT color is in-gamut, and either brings the color closer to 50% gray if it
	 * isn't in-gamut, or returns it as soon as it is in-gamut.
	 * @param i intensity component; will be clamped between 0 and 1 if it isn't already
	 * @param p protan component; will be clamped between 0 and 1 if it isn't already
	 * @param t tritan component; will be clamped between 0 and 1 if it isn't already
	 * @param a alpha component; will be clamped between 0 and 1 if it isn't already
	 * @return the first color this finds that is between the given IPT color and 50% gray, and is in-gamut
	 * @see #inGamut(float, float, float)  You can use inGamut() if you just want to check whether a color is in-gamut.
	 */
	public static float limitToGamut(float i, float p, float t, float a) {
		float i2 = i = MathUtils.clamp(i, 0f, 1f);
		float p2 = p = MathUtils.clamp((p - 0.5f) * 2f, -1f, 1f);
		float t2 = t = MathUtils.clamp((t - 0.5f) * 2f, -1f, 1f);
		a = MathUtils.clamp(a, 0f, 1f);
		for (int attempt = 31; attempt >= 0; attempt--) {
			final float l = cube(i2 + 0.097569f * p2 + 0.205226f * t2);
			final float m = cube(i2 + -0.11388f * p2 + 0.133217f * t2);
			final float s = cube(i2 + 0.032615f * p2 + -0.67689f * t2);

			final float r = 5.432622f * l + -4.67910f * m + 0.246257f * s;
			final float g = -1.10517f * l + 2.311198f * m + -0.20588f * s;
			final float b = 0.028104f * l + -0.19466f * m + 1.166325f * s;
			if(r >= 0f && r <= 1f && g >= 0f && g <= 1f && b >= 0f && b <= 1f)
				break;
			final float progress = attempt * 0x1p-5f;
			i2 = MathUtils.lerp(0.55f, i, progress);
			p2 = MathUtils.lerp(0, p, progress);
			t2 = MathUtils.lerp(0, t, progress);
		}
		return oklab(i2, p2 * 0.5f + 0.5f, t2 * 0.5f + 0.5f, 1f);
	}

	/**
	 * Produces a random packed float color that is always in-gamut and should be uniformly distributed.
	 * @param random a Random object (preferably a subclass of Random, like {@link com.badlogic.gdx.math.RandomXS128})
	 * @return a packed float color that is always in-gamut
	 */
	public static float randomColor(Random random) {
		float i = random.nextFloat();
		float p = random.nextFloat();
		float t = random.nextFloat();
		while (!inGamut(i, p, t)) {
			i = random.nextFloat();
			p = random.nextFloat();
			t = random.nextFloat();
		}
		return oklab(i, p, t, 1f);
	}
}
