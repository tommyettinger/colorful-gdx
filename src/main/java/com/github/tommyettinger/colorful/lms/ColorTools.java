package com.github.tommyettinger.colorful.lms;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.NumberUtils;
import com.github.tommyettinger.colorful.FloatColors;

/**
 * Contains code for manipulating colors as {@code int}, packed {@code float}, and {@link Color} values in the LMS
 * color space. Often, LMS is a mid-way result on the way to calculating an IPT color, but it is also usable on its own.
 * This color space does not have any significance for the L, M, and S channels; they correspond to responses in the
 * human eye to different colors, and can't be used as stand-ins for lightness or colorfulness.
 * <br>
 * This class and its package owe a lot to CypherCove's gdx-tween library, which showed how useful LMS was on its own at
 * interpolating between colors.
 */
public class ColorTools {
	/**
	 * Gets a packed float representation of a color given as 4 float components, here, L, M, S (all three of which are
	 * defined together, and correspond to human eye signals in response to light), and A (alpha or opacity).
	 * Alpha is the multiplicative opacity of the color, and acts like RGBA's alpha.
	 * <br>
	 * This method bit-masks the resulting color's byte values, so any values can technically be given to this as
	 * signalL, signalM, and signalS, but they will only be reversible from the returned float color to the original L,
	 * M, and S values if the original values were in the range that {@link #signalL(float)}, {@link #signalM(float)},
	 * and {@link #signalS(float)} return.
	 *
	 * @param signalL     0f to 1f, L component of LMS
	 * @param signalM     0f to 1f, M component of LMS
	 * @param signalS     0f to 1f, S component of LMS
	 * @param alpha      0f to 1f, 0f makes the color transparent and 1f makes it opaque 
	 * @return a float encoding a color with the given properties
	 */
	public static float lms(float signalL, float signalM, float signalS, float alpha) {
		return NumberUtils.intBitsToFloat(((int) (alpha * 255) << 24 & 0xFE000000) | ((int) (signalS * 255) << 16 & 0xFF0000)
				| ((int) (signalM * 255) << 8 & 0xFF00) | ((int) (signalL * 255) & 0xFF));
	}

	private static float forwardTransform(final float component) {
		return (float)Math.pow(component, 0.43);
	}

	private static float reverseTransform(final float component) {
		return (float)Math.pow(component, 2.3256);
	}

	/**
	 * Converts a packed float color in the format produced by {@link ColorTools#lms(float, float, float, float)} to an RGBA8888 int.
	 * This format of int can be used with Pixmap and in some other places in libGDX.
	 * @param packed a packed float color, as produced by {@link ColorTools#lms(float, float, float, float)}
	 * @return an RGBA8888 int color
	 */
	public static int toRGBA8888(final float packed)
	{
		final int decoded = NumberUtils.floatToRawIntBits(packed);
		final float l = reverseTransform((decoded & 0xff) / 255f);
		final float m = reverseTransform((decoded >>> 8 & 0xff) / 255f);
		final float s = reverseTransform((decoded >>> 16 & 0xff) / 255f);
		final int r = MathUtils.clamp((int) ((5.432622f * l + -4.67910f * m + 0.246257f * s) * 256f), 0, 255);
		final int g = MathUtils.clamp((int) ((-1.10517f * l + 2.311198f * m + -0.20588f * s) * 256f), 0, 255);
		final int b = MathUtils.clamp((int) ((0.028104f * l + -0.19466f * m + 1.166325f * s) * 256f), 0, 255);
		return r << 24 | g << 16 | b << 8 | (decoded & 0xfe000000) >>> 24 | decoded >>> 31;
	}

	/**
	 * Converts a packed float color in the format produced by {@link ColorTools#lms(float, float, float, float)}
	 * to a packed float in RGBA format.
	 * This format of float can be used with the standard SpriteBatch and in some other places in libGDX.
	 * @param packed a packed float color, as produced by {@link ColorTools#lms(float, float, float, float)}
	 * @return a packed float color as RGBA
	 */
	public static float toRGBA(final float packed)
	{
		final int decoded = NumberUtils.floatToRawIntBits(packed);
		final float l = reverseTransform((decoded & 0xff) / 255f);
		final float m = reverseTransform((decoded >>> 8 & 0xff) / 255f);
		final float s = reverseTransform((decoded >>> 16 & 0xff) / 255f);
		final int r = MathUtils.clamp((int) ((5.432622f * l + -4.67910f * m + 0.246257f * s) * 256f), 0, 255);
		final int g = MathUtils.clamp((int) ((-1.10517f * l + 2.311198f * m + -0.20588f * s) * 256f), 0, 255);
		final int b = MathUtils.clamp((int) ((0.028104f * l + -0.19466f * m + 1.166325f * s) * 256f), 0, 255);
		return NumberUtils.intBitsToFloat(r | g << 8 | b << 16 | (decoded & 0xfe000000));
	}
	/**
	 * Writes an LMS-format packed float color (the format produced by {@link ColorTools#lms(float, float, float, float)})
	 * into an RGBA8888 Color as used by libGDX (called {@code editing}).
	 * @param editing a libGDX color that will be filled in-place with an RGBA conversion of {@code packed}
	 * @param packed a packed float color, as produced by {@link ColorTools#lms(float, float, float, float)}
	 * @return an RGBA8888 int color
	 */
	public static Color toColor(Color editing, final float packed)
	{
		final int decoded = NumberUtils.floatToRawIntBits(packed);
		final float l = reverseTransform((decoded & 0xff) / 255f);
		final float m = reverseTransform((decoded >>> 8 & 0xff) / 255f);
		final float s = reverseTransform((decoded >>> 16 & 0xff) / 255f);
		editing.r = (5.432622f * l + -4.67910f * m + 0.246257f * s);
		editing.g = (-1.10517f * l + 2.311198f * m + -0.20588f * s);
		editing.b = (0.028104f * l + -0.19466f * m + 1.166325f * s);
		editing.a = (decoded >>> 25) * 0x1.020408p-7f; // this is 1/127 as a float
		return editing.clamp();
	}

	/**
	 * Takes a color encoded as an RGBA8888 int and converts to a packed float in the LMS format this uses.
	 * @param rgba an int with the channels (in order) red, green, blue, alpha; should have 8 bits per channel
	 * @return a packed float as LMS, which this class can use
	 */
	public static float fromRGBA8888(final int rgba) {
		final float r = (rgba >>> 24) * 0x1.010101010101p-8f;
		final float g = (rgba >>> 16 & 0xFF) * 0x1.010101010101p-8f;
		final float b = (rgba >>> 8 & 0xFF) * 0x1.010101010101p-8f;
		return NumberUtils.intBitsToFloat(
			              MathUtils.clamp((int) (forwardTransform(0.313921f * r + 0.639468f * g + 0.0465970f * b) * 256f), 0, 255)
						| MathUtils.clamp((int) (forwardTransform(0.151693f * r + 0.748209f * g + 0.1000044f * b) * 256f), 0, 255) << 8
						| MathUtils.clamp((int) (forwardTransform(0.017753f * r + 0.109468f * g + 0.8729690f * b) * 256f), 0, 255) << 16
						| (rgba & 0xFE) << 24);
	}

	/**
	 * Takes a color encoded as an RGBA8888 packed float and converts to a packed float in the LMS format this uses.
	 * @param packed a packed float in RGBA8888 format, with A in the MSB and R in the LSB
	 * @return a packed float as LMS, which this class can use
	 */
	public static float fromRGBA(final float packed) {
		final int abgr = NumberUtils.floatToRawIntBits(packed);
		final float r = (abgr & 0xFF) * 0x1.010101010101p-8f;
		final float g = (abgr >>> 8 & 0xFF) * 0x1.010101010101p-8f;
		final float b = (abgr >>> 16 & 0xFF) * 0x1.010101010101p-8f;

		return NumberUtils.intBitsToFloat(
				          MathUtils.clamp((int) (forwardTransform(0.313921f * r + 0.639468f * g + 0.0465970f * b) * 256f), 0, 255)
						| MathUtils.clamp((int) (forwardTransform(0.151693f * r + 0.748209f * g + 0.1000044f * b) * 256f), 0, 255) << 8
						| MathUtils.clamp((int) (forwardTransform(0.017753f * r + 0.109468f * g + 0.8729690f * b) * 256f), 0, 255) << 16
						| (abgr & 0xFE000000));
	}

	/**
	 * Takes a libGDX Color that uses RGBA8888 channels and converts to a packed float in the LMS format this uses.
	 * @param color a libGDX RGBA8888 Color
	 * @return a packed float as LMS, which this class can use
	 */
	public static float fromColor(final Color color) {
		return NumberUtils.intBitsToFloat(
				          MathUtils.clamp((int) (forwardTransform(0.313921f * color.r + 0.639468f * color.g + 0.0465970f * color.b) * 256f), 0, 255)
						| MathUtils.clamp((int) (forwardTransform(0.151693f * color.r + 0.748209f * color.g + 0.1000044f * color.b) * 256f), 0, 255) << 8
						| MathUtils.clamp((int) (forwardTransform(0.017753f * color.r + 0.109468f * color.g + 0.8729690f * color.b) * 256f), 0, 255) << 16
						| ((int)(color.a * 255f) << 24 & 0xFE000000));
	}

	/**
	 * Takes RGBA components from 0.0 to 1.0 each and converts to a packed float in the LMS format this uses.
	 * @param r red, from 0.0 to 1.0 (both inclusive)
	 * @param g green, from 0.0 to 1.0 (both inclusive)
	 * @param b blue, from 0.0 to 1.0 (both inclusive)
	 * @param a alpha, from 0.0 to 1.0 (both inclusive)
	 * @return a packed float as LMS, which this class can use
	 */
	public static float fromRGBA(final float r, final float g, final float b, final float a) {
		return NumberUtils.intBitsToFloat(
    				      MathUtils.clamp((int) (forwardTransform(0.313921f * r + 0.639468f * g + 0.0465970f * b) * 256f), 0, 255)
						| MathUtils.clamp((int) (forwardTransform(0.151693f * r + 0.748209f * g + 0.1000044f * b) * 256f), 0, 255) << 8
						| MathUtils.clamp((int) (forwardTransform(0.017753f * r + 0.109468f * g + 0.8729690f * b) * 256f), 0, 255) << 16
						| ((int)(a * 255f) << 24 & 0xFE000000));
	}

	/**
	 * Gets the red channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #lms(float, float, float, float)}
	 * @return an int from 0 to 255, inclusive, representing the red channel value of the given encoded color
	 */
	public static int redInt(final float encoded)
	{
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		final float l = reverseTransform((decoded & 0xff) / 255f);
		final float m = reverseTransform((decoded >>> 8 & 0xff) / 255f);
		final float s = reverseTransform((decoded >>> 16 & 0xff) / 255f);
		return MathUtils.clamp((int) ((5.432622f * l + -4.67910f * m + 0.246257f * s) * 256f), 0, 255);
	}

	/**
	 * Gets the green channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #lms(float, float, float, float)}
	 * @return an int from 0 to 255, inclusive, representing the green channel value of the given encoded color
	 */
	public static int greenInt(final float encoded)
	{
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		final float l = reverseTransform((decoded & 0xff) / 255f);
		final float m = reverseTransform((decoded >>> 8 & 0xff) / 255f);
		final float s = reverseTransform((decoded >>> 16 & 0xff) / 255f);
		return MathUtils.clamp((int) ((-1.10517f * l + 2.311198f * m + -0.20588f * s) * 256f), 0, 255);
	}

	/**
	 * Gets the blue channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #lms(float, float, float, float)}
	 * @return an int from 0 to 255, inclusive, representing the blue channel value of the given encoded color
	 */
	public static int blueInt(final float encoded)
	{
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		final float l = reverseTransform((decoded & 0xff) / 255f);
		final float m = reverseTransform((decoded >>> 8 & 0xff) / 255f);
		final float s = reverseTransform((decoded >>> 16 & 0xff) / 255f);
		return MathUtils.clamp((int) ((0.028104f * l + -0.19466f * m + 1.166325f * s) * 256f), 0, 255);
	}

	/**
	 * Gets the alpha channel value of the given encoded color, as an even int ranging from 0 to 254, inclusive. Because
	 * of how alpha is stored in libGDX, no odd-number values are possible for alpha.
	 * @param encoded a color as a packed float that can be obtained by {@link #lms(float, float, float, float)}
	 * @return an even int from 0 to 254, inclusive, representing the alpha channel value of the given encoded color
	 */
	public static int alphaInt(final float encoded)
	{
		return (NumberUtils.floatToRawIntBits(encoded) & 0xfe000000) >>> 24;
	}

	/**
	 * Gets the red channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #lms(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the red channel value of the given encoded color
	 */
	public static float red(final float encoded)
	{
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		final float l = reverseTransform((decoded & 0xff) / 255f);
		final float m = reverseTransform((decoded >>> 8 & 0xff) / 255f);
		final float s = reverseTransform((decoded >>> 16 & 0xff) / 255f);

		return MathUtils.clamp((5.432622f * l + -4.67910f * m + 0.246257f * s), 0f, 1f);
	}

	/**
	 * Gets the green channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #lms(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the green channel value of the given encoded color
	 */
	public static float green(final float encoded)
	{
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		final float l = reverseTransform((decoded & 0xff) / 255f);
		final float m = reverseTransform((decoded >>> 8 & 0xff) / 255f);
		final float s = reverseTransform((decoded >>> 16 & 0xff) / 255f);

		return MathUtils.clamp((-1.10517f * l + 2.311198f * m + -0.20588f * s), 0f, 1f);
	}

	/**
	 * Gets the blue channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #lms(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the blue channel value of the given encoded color
	 */
	public static float blue(final float encoded)
	{
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		final float l = reverseTransform((decoded & 0xff) / 255f);
		final float m = reverseTransform((decoded >>> 8 & 0xff) / 255f);
		final float s = reverseTransform((decoded >>> 16 & 0xff) / 255f);
		return MathUtils.clamp((0.028104f * l + -0.19466f * m + 1.166325f * s), 0f, 1f);
	}

	/**
	 * Gets the alpha channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #lms(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the alpha channel value of the given encoded color
	 */
	public static float alpha(final float encoded)
	{
		return ((NumberUtils.floatToRawIntBits(encoded) & 0xfe000000) >>> 24) * 0x1.020408p-8f;
	}

	/**
	 * Gets a color as an LMS packed float given floats representing hue, saturation, lightness, and opacity.
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
	 * @param encoded a color as a packed float that can be obtained by {@link #lms(float, float, float, float)}
	 * @return the saturation of the color from 0.0 (a grayscale color; inclusive) to 1.0 (a bright color, inclusive)
	 */
	public static float saturation(final float encoded) {
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		final float l = reverseTransform((decoded & 0xff) / 255f);
		final float m = reverseTransform((decoded >>> 8 & 0xff) / 255f);
		final float s = reverseTransform((decoded >>> 16 & 0xff) / 255f);
		final float r = MathUtils.clamp(5.432622f * l + -4.67910f * m + 0.246257f * s, 0f, 1f);
		final float g = MathUtils.clamp(-1.10517f * l + 2.311198f * m + -0.20588f * s, 0f, 1f);
		final float b = MathUtils.clamp(0.028104f * l + -0.19466f * m + 1.166325f * s, 0f, 1f);
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
		final float l = reverseTransform((decoded & 0xff) / 255f);
		final float m = reverseTransform((decoded >>> 8 & 0xff) / 255f);
		final float s = reverseTransform((decoded >>> 16 & 0xff) / 255f);
		final float r = MathUtils.clamp(5.432622f * l + -4.67910f * m + 0.246257f * s, 0f, 1f);
		final float g = MathUtils.clamp(-1.10517f * l + 2.311198f * m + -0.20588f * s, 0f, 1f);
		final float b = MathUtils.clamp(0.028104f * l + -0.19466f * m + 1.166325f * s, 0f, 1f);
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
	 * @param encoded a color as a packed float that can be obtained by {@link #lms(float, float, float, float)}
	 * @return The hue of the color from 0.0 (red, inclusive) towards orange, then yellow, and
	 * eventually to purple before looping back to almost the same red (1.0, exclusive)
	 */
	public static float hue(final float encoded) {
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		final float l = reverseTransform((decoded & 0xff) / 255f);
		final float m = reverseTransform((decoded >>> 8 & 0xff) / 255f);
		final float s = reverseTransform((decoded >>> 16 & 0xff) / 255f);
		final float r = MathUtils.clamp(5.432622f * l + -4.67910f * m + 0.246257f * s, 0f, 1f);
		final float g = MathUtils.clamp(-1.10517f * l + 2.311198f * m + -0.20588f * s, 0f, 1f);
		final float b = MathUtils.clamp(0.028104f * l + -0.19466f * m + 1.166325f * s, 0f, 1f);
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
	 * The "L" signal of the given packed float in LMS format, which is like its lightness; ranges from 0.0f to
	 * 1.0f . This is approximately meaningless on its own, and only is useful in conjunction with other signals.
	 *
	 * @param encoded a color encoded as a packed float, as by {@link #lms(float, float, float, float)}
	 * @return the intensity value as a float from 0.0f to 1.0f
	 */
	public static float signalL(final float encoded)
	{
		return (NumberUtils.floatToRawIntBits(encoded) & 0xff) / 255f;
	}

	/**
	 * The "M" signal of the given packed float in LMS format, which is like its lightness; ranges from 0.0f to
	 * 1.0f . This is approximately meaningless on its own, and only is useful in conjunction with other signals.
	 *
	 *  @param encoded a color encoded as a packed float, as by {@link #lms(float, float, float, float)}
	 * @return the protan value as a float from 0.0f to 1.0f
	 */
	public static float signalM(final float encoded)
	{
		return ((NumberUtils.floatToRawIntBits(encoded) >>> 8 & 0xff)) / 255f;
	}

	/**
	 * The "S" signal of the given packed float in LMS format, which is like its lightness; ranges from 0.0f to
	 * 1.0f . This is approximately meaningless on its own, and only is useful in conjunction with other signals.
	 *
	 * @param encoded a color encoded as a packed float, as by {@link #lms(float, float, float, float)}
	 * @return the tritan value as a float from 0.0f to 1.0f
	 */
	public static float signalS(final float encoded)
	{
		return ((NumberUtils.floatToRawIntBits(encoded) >>> 16 & 0xff)) / 255f;
	}

	/**
	 * Gets a variation on the packed float color basis as another packed float that has its hue, saturation, value, and
	 * opacity adjusted by the specified amounts. Takes floats representing the amounts of change to apply to hue,
	 * saturation, value, and opacity; these can be between -1f and 1f. Returns a float that can be used as a packed or
	 * encoded color with methods like {@link com.badlogic.gdx.graphics.g2d.Batch#setPackedColor(float)}. The float is
	 * likely to be different than the result of {@link #lms(float, float, float, float)} unless hue saturation,
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
		opacity = MathUtils.clamp(opacity + (decoded >>> 24 & 0xfe) * 0x1.020408p-8f, 0f, 1f);
		final float l = reverseTransform((decoded & 0xff) / 255f);
		final float m = reverseTransform((decoded >>> 8 & 0xff) / 255f);
		final float s = reverseTransform((decoded >>> 16 & 0xff) / 255f);
		final float r = MathUtils.clamp(5.432622f * l + -4.67910f * m + 0.246257f * s, 0f, 1f);
		final float g = MathUtils.clamp(-1.10517f * l + 2.311198f * m + -0.20588f * s, 0f, 1f);
		final float b = MathUtils.clamp(0.028104f * l + -0.19466f * m + 1.166325f * s, 0f, 1f);
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
		float li = x * (1f - 0.5f * d / (x + 1e-10f));
		float hue2 = Math.abs(z + (w - y) / (6f * d + 1e-10f));
		float sat2 = (x - li) / (Math.min(li, 1f - li) + 1e-10f);
		return FloatColors.hsl2rgb(hue2 + hue + 1 - (int)(hue2 + hue + 1), MathUtils.clamp(saturation + sat2, 0f, 1f), MathUtils.clamp(li + value, 0f, 1f), opacity);
	}

	/**
	 * Interpolates from the packed float color start towards that color made opaque by change. While change should be
	 * between 0f (return start as-is) and 1f (return start with full alpha), start should be a packed color, as from
	 * {@link #lms(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
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
	 * {@link #lms(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors,
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
	 * Makes a quasi-randomly-edited variant on the given {@code color}, allowing typically a small amount of
	 * {@code variance} (such as 0.05 to 0.25) between the given color and what this can return. The {@code seed} should
	 * be different each time this is called, and can be obtained from a random number generator to make the colors more
	 * random, or can be incremented on each call. If the seed is only incremented or decremented, then this shouldn't
	 * produce two similar colors in a row unless variance is very small. The variance affects the I, P, and T of the
	 * generated color, and each of those channels can go up or down by the given variance as long as the total distance
	 * isn't greater than the variance (this considers P and T extra-wide, going from -1 to 1, while I goes from 0 to 1,
	 * but only internally for measuring distance).
	 * @param color a packed float color, as produced by {@link #lms(float, float, float, float)}
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
	 * Returns true if the given packed float color, as LMS, is valid to convert losslessly back to RGBA.
	 * @param packed a packed float color as LMS
	 * @return true if the given packed float color can be converted back and forth to RGBA
	 */
	public static boolean inGamut(final float packed)
	{
		final int decoded = NumberUtils.floatToRawIntBits(packed);
		final float l = reverseTransform((decoded & 0xff) / 255f);
		final float m = reverseTransform((decoded >>> 8 & 0xff) / 255f);
		final float s = reverseTransform((decoded >>> 16 & 0xff) / 255f);
		final float r = 5.432622f * l + -4.67910f * m + 0.246257f * s;
		if(r < -0.006f || r > 1.003f) return false;
		final float g = -1.10517f * l + 2.311198f * m + -0.20588f * s;
		if(g < -0.006f || g > 1.003f)
			return false;
		final float b = 0.028104f * l + -0.19466f * m + 1.166325f * s;
		return (b >= -0.006f && b <= 1.003f);
	}
	/**
	 * Returns true if the given LMS values are valid to convert losslessly back to RGBA.
	 * @param l L channel, as a float from 0 to 1
	 * @param m M channel, as a float from 0 to 1
	 * @param s S channel, as a float from 0 to 1
	 * @return true if the given packed float color can be converted back and forth to RGBA
	 */
	public static boolean inGamut(float l, float m, float s)
	{
		l = reverseTransform(l);
		m = reverseTransform(m);
		s = reverseTransform(s);
		final float r = 5.432622f * l + -4.67910f * m + 0.246257f * s;
		if(r < -0.006f || r > 1.003f) return false;
		final float g = -1.10517f * l + 2.311198f * m + -0.20588f * s;
		if(g < -0.006f || g > 1.003f)
			return false;
		final float b = 0.028104f * l + -0.19466f * m + 1.166325f * s;
		return (b >= -0.006f && b <= 1.003f);
	}
}
