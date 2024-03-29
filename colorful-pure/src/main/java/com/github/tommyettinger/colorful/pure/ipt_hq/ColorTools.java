/*
 * Copyright (c) 2023 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.tommyettinger.colorful.pure.ipt_hq;

import com.github.tommyettinger.colorful.pure.FloatColors;
import com.github.tommyettinger.digital.BitConversion;
import com.github.tommyettinger.digital.MathTools;

import java.util.Random;

/**
 * Contains code for manipulating colors as {@code int} and packed {@code float} values in the IPT color space.
 * IPT has more perceptually-uniform handling of hue than some other color spaces, like YCwCm, and this version goes
 * further than {@link com.github.tommyettinger.colorful.pure.ipt the IPT package} by performing gamma correction and all the
 * complex exponential adjustments to various components that the original IPT paper used. In most regards, this is a
 * more thoroughly-constructed color space than YCwCm, but YCwCm may still be useful because of how it maps to aesthetic
 * components of color. See {@link #ipt(float, float, float, float)} for docs on the I, P, and T channels.
 */
public class ColorTools {
	/**
	 * Gets a packed float representation of a color given as 4 float components, here, I (intensity or lightness), P
	 * (protan, a chromatic component ranging from greenish to reddish), T (tritan, a chromatic component ranging from
	 * bluish to yellowish), and A (alpha or opacity). Intensity should be between 0 and 1, inclusive, with 0 used for
	 * very dark colors (almost only black), and 1 used for very light colors (almost only white). Protan and tritan
	 * range from 0.0 to 1.0, with grayscale results when both are about 0.5. There's some aesthetic value in changing
	 * just one chroma value. When protan is high and tritan is low, the color is more purple/magenta, when both are low
	 * it is more bluish, when tritan is high and protan is low, the color tends to be greenish, and when both are high
	 * it tends to be orange. When protan and tritan are both near 0.5f, the color is closer to gray.  Alpha is the
	 * multiplicative opacity of the color, and acts like RGBA's alpha.
	 * <br>
	 * This method bit-masks the resulting color's byte values, so any values can technically be given to this as
	 * intensity, protan, and tritan, but they will only be reversible from the returned float color to the original I,
	 * P, and T values if the original values were in the range that {@link #intensity(float)}, {@link #protan(float)},
	 * and {@link #tritan(float)} return. You can use {@link #inGamut(float, float, float)} to check if a given set of
	 * I, P, and T values is in-gamut, that is, it can be converted to and from an RGB color without going out of the
	 * valid range. If you just want to enforce that a color is in-gamut, you can use
	 * {@link #limitToGamut(float, float, float, float)}, which takes the same parameters this method does, or
	 * {@link #limitToGamut(float)} if you already have a packed float color that could be out-of-gamut.
	 *
	 * @param intens     0f to 1f, intensity or I component of IPT, with 0.5f meaning "no change" and 1f brightening
	 * @param protan     0f to 1f, protan or P component of IPT, with 1f more orange, red, or magenta
	 * @param tritan     0f to 1f, tritan or T component of IPT, with 1f more green, yellow, or red
	 * @param alpha      0f to 1f, 0f makes the color transparent and 1f makes it opaque 
	 * @return a float encoding a color with the given properties
	 */
	public static float ipt(float intens, float protan, float tritan, float alpha) {
		return BitConversion.intBitsToFloat(((int) (alpha * 255) << 24 & 0xFE000000) | ((int) (tritan * 255) << 16 & 0xFF0000)
				| ((int) (protan * 255) << 8 & 0xFF00) | ((int) (intens * 255) & 0xFF));
	}


	/**
	 * Used when converting from RGB to IPT, as an intermediate step.
	 * @param component one of the LMS channels to be converted to LMS Prime
	 * @return an LMS Prime channel value, which can be converted to IPT
	 */
	private static float forwardTransform(final float component) {
		return (float)Math.pow(component, 0.43f);
	}

	/**
	 * Used when converting from IPT to RGB, as an intermediate step.
	 * @param component one of the LMS Prime channels to be converted to LMS
	 * @return an LMS channel value, which can be converted to RGB
	 */
	private static float reverseTransform(final float component) {
		return Math.copySign((float)Math.pow(Math.abs(component), 2.3256f), component);
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
	 * Converts a packed float color in the format produced by {@link ColorTools#ipt(float, float, float, float)} to an RGBA8888 int.
	 * This format of int can be used with Pixmap and in some other places in libGDX.
	 * @param packed a packed float color, as produced by {@link ColorTools#ipt(float, float, float, float)}
	 * @return an RGBA8888 int color
	 */
	public static int toRGBA8888(final float packed)
	{
		final int decoded = BitConversion.floatToRawIntBits(packed);
		final float i = (decoded & 0xff) / 255f;
		final float p = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float t = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = reverseTransform(i + 0.097569f * p + 0.205226f * t);
		final float m = reverseTransform(i + -0.11388f * p + 0.133217f * t);
		final float s = reverseTransform(i + 0.032615f * p + -0.67689f * t);
		final int r = (int)(reverseGamma(Math.min(Math.max(5.432622f * l + -4.67910f * m + 0.246257f * s, 0f), 1f)) * 255.999f);
		final int g = (int)(reverseGamma(Math.min(Math.max(-1.10517f * l + 2.311198f * m + -0.20588f * s, 0f), 1f)) * 255.999f);
		final int b = (int)(reverseGamma(Math.min(Math.max(0.028104f * l + -0.19466f * m + 1.166325f * s, 0f), 1f)) * 255.999f);
		return r << 24 | g << 16 | b << 8 | (decoded & 0xfe000000) >>> 24 | decoded >>> 31;
	}

	/**
	 * Converts a packed float color in the format produced by {@link ColorTools#ipt(float, float, float, float)}
	 * to a packed float in RGBA format.
	 * This format of float can be used with the standard SpriteBatch and in some other places in libGDX.
	 * @param packed a packed float color, as produced by {@link ColorTools#ipt(float, float, float, float)}
	 * @return a packed float color as RGBA
	 */
	public static float toRGBA(final float packed)
	{
		final int decoded = BitConversion.floatToRawIntBits(packed);
		final float i = (decoded & 0xff) / 255f;
		final float p = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float t = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = reverseTransform(i + 0.097569f * p + 0.205226f * t);
		final float m = reverseTransform(i + -0.11388f * p + 0.133217f * t);
		final float s = reverseTransform(i + 0.032615f * p + -0.67689f * t);
		final int r = (int)(reverseGamma(Math.min(Math.max(5.432622f * l + -4.67910f * m + 0.246257f * s, 0f), 1f)) * 255.999f);
		final int g = (int)(reverseGamma(Math.min(Math.max(-1.10517f * l + 2.311198f * m + -0.20588f * s, 0f), 1f)) * 255.999f);
		final int b = (int)(reverseGamma(Math.min(Math.max(0.028104f * l + -0.19466f * m + 1.166325f * s, 0f), 1f)) * 255.999f);
		return BitConversion.intBitsToFloat(r | g << 8 | b << 16 | (decoded & 0xfe000000));
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
		final float l = forwardTransform(0.313921f * r + 0.639468f * g + 0.0465970f * b);
		final float m = forwardTransform(0.151693f * r + 0.748209f * g + 0.1000044f * b);
		final float s = forwardTransform(0.017753f * r + 0.109468f * g + 0.8729690f * b);
		return BitConversion.intBitsToFloat(
			              Math.min(Math.max((int)((0.4000f * l + 0.4000f * m + 0.2000f * s       ) * 255.999f), 0), 255)
						| Math.min(Math.max((int)((2.2275f * l - 2.4255f * m + 0.1980f * s + 0.5f) * 255.999f), 0), 255) << 8
						| Math.min(Math.max((int)((0.4028f * l + 0.1786f * m - 0.5814f * s + 0.5f) * 255.999f), 0), 255) << 16
						| (rgba & 0xFE) << 24);
	}

	/**
	 * Takes a color encoded as an RGBA8888 packed float and converts to a packed float in the IPT format this uses.
	 * @param packed a packed float in RGBA8888 format, with A in the MSB and R in the LSB
	 * @return a packed float as IPT, which this class can use
	 */
	public static float fromRGBA(final float packed) {
		final int abgr = BitConversion.floatToRawIntBits(packed);
		final float r = forwardGamma((abgr & 0xFF) * 0x1.010101010101p-8f);
		final float g = forwardGamma((abgr >>> 8 & 0xFF) * 0x1.010101010101p-8f);
		final float b = forwardGamma((abgr >>> 16 & 0xFF) * 0x1.010101010101p-8f);
		final float l = forwardTransform(0.313921f * r + 0.639468f * g + 0.0465970f * b);
		final float m = forwardTransform(0.151693f * r + 0.748209f * g + 0.1000044f * b);
		final float s = forwardTransform(0.017753f * r + 0.109468f * g + 0.8729690f * b);
		return BitConversion.intBitsToFloat(
			              Math.min(Math.max((int)((0.4000f * l + 0.4000f * m + 0.2000f * s       ) * 255.999f), 0), 255)
						| Math.min(Math.max((int)((2.2275f * l - 2.4255f * m + 0.1980f * s + 0.5f) * 255.999f), 0), 255) << 8
						| Math.min(Math.max((int)((0.4028f * l + 0.1786f * m - 0.5814f * s + 0.5f) * 255.999f), 0), 255) << 16
						| (abgr & 0xFE000000));
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
		final float l = forwardTransform(0.313921f * r + 0.639468f * g + 0.0465970f * b);
		final float m = forwardTransform(0.151693f * r + 0.748209f * g + 0.1000044f * b);
		final float s = forwardTransform(0.017753f * r + 0.109468f * g + 0.8729690f * b);
		return BitConversion.intBitsToFloat(
				          Math.min(Math.max((int)((0.4000f * l + 0.4000f * m + 0.2000f * s       ) * 255.999f), 0), 255)
						| Math.min(Math.max((int)((2.2275f * l - 2.4255f * m + 0.1980f * s + 0.5f) * 255.999f), 0), 255) << 8
						| Math.min(Math.max((int)((0.4028f * l + 0.1786f * m - 0.5814f * s + 0.5f) * 255.999f), 0), 255) << 16
						| ((int)(a * 255f) << 24 & 0xFE000000));
	}

	/**
	 * Gets the red channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #ipt(float, float, float, float)}
	 * @return an int from 0 to 255, inclusive, representing the red channel value of the given encoded color
	 */
	public static int redInt(final float encoded)
	{
		final int decoded = BitConversion.floatToRawIntBits(encoded);
		final float i = (decoded & 0xff) / 255f;
		final float p = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float t = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = reverseTransform(i + 0.097569f * p + 0.205226f * t);
		final float m = reverseTransform(i + -0.11388f * p + 0.133217f * t);
		final float s = reverseTransform(i + 0.032615f * p + -0.67689f * t);
		return (int)(reverseGamma(Math.min(Math.max(5.432622f * l + -4.67910f * m + 0.246257f * s, 0f), 1f)) * 255.999f);
	}

	/**
	 * Gets the green channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #ipt(float, float, float, float)}
	 * @return an int from 0 to 255, inclusive, representing the green channel value of the given encoded color
	 */
	public static int greenInt(final float encoded)
	{
		final int decoded = BitConversion.floatToRawIntBits(encoded);
		final float i = (decoded & 0xff) / 255f;
		final float p = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float t = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = reverseTransform(i + 0.097569f * p + 0.205226f * t);
		final float m = reverseTransform(i + -0.11388f * p + 0.133217f * t);
		final float s = reverseTransform(i + 0.032615f * p + -0.67689f * t);
		return (int)(reverseGamma(Math.min(Math.max(-1.10517f * l + 2.311198f * m + -0.20588f * s, 0f), 1f)) * 255.999f);
	}

	/**
	 * Gets the blue channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #ipt(float, float, float, float)}
	 * @return an int from 0 to 255, inclusive, representing the blue channel value of the given encoded color
	 */
	public static int blueInt(final float encoded)
	{
		final int decoded = BitConversion.floatToRawIntBits(encoded);
		final float i = (decoded & 0xff) / 255f;
		final float p = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float t = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = reverseTransform(i + 0.097569f * p + 0.205226f * t);
		final float m = reverseTransform(i + -0.11388f * p + 0.133217f * t);
		final float s = reverseTransform(i + 0.032615f * p + -0.67689f * t);
		return (int)(reverseGamma(Math.min(Math.max(0.028104f * l + -0.19466f * m + 1.166325f * s, 0f), 1f)) * 255.999f);
	}

	/**
	 * Gets the alpha channel value of the given encoded color, as an even int ranging from 0 to 254, inclusive. Because
	 * of how alpha is stored in libGDX, no odd-number values are possible for alpha.
	 * @param encoded a color as a packed float that can be obtained by {@link #ipt(float, float, float, float)}
	 * @return an even int from 0 to 254, inclusive, representing the alpha channel value of the given encoded color
	 */
	public static int alphaInt(final float encoded)
	{
		return (BitConversion.floatToRawIntBits(encoded) & 0xfe000000) >>> 24;
	}

	/**
	 * Gets the red channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #ipt(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the red channel value of the given encoded color
	 */
	public static float red(final float encoded)
	{
		final int decoded = BitConversion.floatToRawIntBits(encoded);
		final float i = (decoded & 0xff) / 255f;
		final float p = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float t = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = reverseTransform(i + 0.097569f * p + 0.205226f * t);
		final float m = reverseTransform(i + -0.11388f * p + 0.133217f * t);
		final float s = reverseTransform(i + 0.032615f * p + -0.67689f * t);
		return reverseGamma(Math.min(Math.max(5.432622f * l + -4.67910f * m + 0.246257f * s, 0f), 1f));
	}

	/**
	 * Gets the green channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #ipt(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the green channel value of the given encoded color
	 */
	public static float green(final float encoded)
	{
		final int decoded = BitConversion.floatToRawIntBits(encoded);
		final float i = (decoded & 0xff) / 255f;
		final float p = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float t = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = reverseTransform(i + 0.097569f * p + 0.205226f * t);
		final float m = reverseTransform(i + -0.11388f * p + 0.133217f * t);
		final float s = reverseTransform(i + 0.032615f * p + -0.67689f * t);
		return reverseGamma(Math.min(Math.max(-1.10517f * l + 2.311198f * m + -0.20588f * s, 0f), 1f));
	}

	/**
	 * Gets the blue channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #ipt(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the blue channel value of the given encoded color
	 */
	public static float blue(final float encoded)
	{
		final int decoded = BitConversion.floatToRawIntBits(encoded);
		final float i = (decoded & 0xff) / 255f;
		final float p = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float t = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = reverseTransform(i + 0.097569f * p + 0.205226f * t);
		final float m = reverseTransform(i + -0.11388f * p + 0.133217f * t);
		final float s = reverseTransform(i + 0.032615f * p + -0.67689f * t);
		return reverseGamma(Math.min(Math.max(0.028104f * l + -0.19466f * m + 1.166325f * s, 0f), 1f));
	}

	/**
	 * Gets the alpha channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #ipt(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the alpha channel value of the given encoded color
	 */
	public static float alpha(final float encoded)
	{
		return ((BitConversion.floatToRawIntBits(encoded) & 0xfe000000) >>> 24) * 0x1.020408p-8f;
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
			return BitConversion.intBitsToFloat((((int) (opacity * 255f) << 24) & 0xFE000000) | 0x7F7F00);
		} else {
			return fromRGBA(FloatColors.hsl2rgb(hue, saturation, lightness, opacity));
		}
	}

	/**
	 * Gets the saturation of the given encoded color, as a float ranging from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #ipt(float, float, float, float)}
	 * @return the saturation of the color from 0.0 (a grayscale color; inclusive) to 1.0 (a bright color, inclusive)
	 */
	public static float saturation(final float encoded) {
		final int decoded = BitConversion.floatToRawIntBits(encoded);
		final float i = (decoded & 0xff) / 255f;
		if(Math.abs(i - 0.5) > 0.495f) return 0f;
		final float p = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float t = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = reverseTransform(i + 0.097569f * p + 0.205226f * t);
		final float m = reverseTransform(i + -0.11388f * p + 0.133217f * t);
		final float s = reverseTransform(i + 0.032615f * p + -0.67689f * t);
		final float r = reverseGamma(Math.min(Math.max(5.432622f * l + -4.67910f * m + 0.246257f * s, 0f), 1f));
		final float g = reverseGamma(Math.min(Math.max(-1.10517f * l + 2.311198f * m + -0.20588f * s, 0f), 1f));
		final float b = reverseGamma(Math.min(Math.max(0.028104f * l + -0.19466f * m + 1.166325f * s, 0f), 1f));
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

	public static float lightness(final float encoded) {
		final int decoded = BitConversion.floatToRawIntBits(encoded);
		final float i = (decoded & 0xff) / 255f;
		final float p = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float t = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = reverseTransform(i + 0.097569f * p + 0.205226f * t);
		final float m = reverseTransform(i + -0.11388f * p + 0.133217f * t);
		final float s = reverseTransform(i + 0.032615f * p + -0.67689f * t);
		final float r = reverseGamma(Math.min(Math.max(5.432622f * l + -4.67910f * m + 0.246257f * s, 0f), 1f));
		final float g = reverseGamma(Math.min(Math.max(-1.10517f * l + 2.311198f * m + -0.20588f * s, 0f), 1f));
		final float b = reverseGamma(Math.min(Math.max(0.028104f * l + -0.19466f * m + 1.166325f * s, 0f), 1f));

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
	 * @param encoded a color as a packed float that can be obtained by {@link #ipt(float, float, float, float)}
	 * @return The hue of the color from 0.0 (red, inclusive) towards orange, then yellow, and
	 * eventually to purple before looping back to almost the same red (1.0, exclusive)
	 */
	public static float hue(final float encoded) {
		final int decoded = BitConversion.floatToRawIntBits(encoded);
		final float i = (decoded & 0xff) / 255f;
		final float p = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float t = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = reverseTransform(i + 0.097569f * p + 0.205226f * t);
		final float m = reverseTransform(i + -0.11388f * p + 0.133217f * t);
		final float s = reverseTransform(i + 0.032615f * p + -0.67689f * t);
		final float r = reverseGamma(Math.min(Math.max(5.432622f * l + -4.67910f * m + 0.246257f * s, 0f), 1f));
		final float g = reverseGamma(Math.min(Math.max(-1.10517f * l + 2.311198f * m + -0.20588f * s, 0f), 1f));
		final float b = reverseGamma(Math.min(Math.max(0.028104f * l + -0.19466f * m + 1.166325f * s, 0f), 1f));
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
	 * @param encoded a color encoded as a packed float, as by {@link #ipt(float, float, float, float)}
	 * @return the intensity value as a float from 0.0f to 1.0f
	 */
	public static float intensity(final float encoded)
	{
		return (BitConversion.floatToRawIntBits(encoded) & 0xff) / 255f;
	}

	/**
	 * The "protan" of the given packed float in IPT format, which when combined with tritan describes the
	 * hue and saturation of a color; ranges from 0f to 1f . If protan is 0f, the color will be cooler, more green or
	 * blue; if protan is 1f, the color will be warmer, from magenta to orange. You can edit the protan of a color with
	 * {@link #protanUp(float, float)} and {@link #protanDown(float, float)}.
	 * @param encoded a color encoded as a packed float, as by {@link #ipt(float, float, float, float)}
	 * @return the protan value as a float from 0.0f to 1.0f
	 */
	public static float protan(final float encoded)
	{
		return ((BitConversion.floatToRawIntBits(encoded) >>> 8 & 0xff)) / 255f;
	}

	/**
	 * The "tritan" of the given packed float in IPT format, which when combined with protan describes the
	 * hue and saturation of a color; ranges from 0f to 1f . If tritan is 0f, the color will be more "artificial", more
	 * blue or purple; if tritan is 1f, the color will be more "natural", from green to yellow to orange. You can edit
	 * the tritan of a color with {@link #tritanUp(float, float)} and {@link #tritanDown(float, float)}.
	 * @param encoded a color encoded as a packed float, as by {@link #ipt(float, float, float, float)}
	 * @return the tritan value as a float from 0.0f to 1.0f
	 */
	public static float tritan(final float encoded)
	{
		return ((BitConversion.floatToRawIntBits(encoded) >>> 16 & 0xff)) / 255f;
	}

	/**
	 * Gets a variation on the packed float color basis as another packed float that has its hue, saturation, lightness,
	 * and opacity adjusted by the specified amounts. Note that this edits the color in HSL space, not IPT_HQ! Takes
	 * floats representing the amounts of change to apply to hue, saturation, lightness, and opacity; these can be
	 * between -1f and 1f. Returns a float that can be used as a packed or encoded color. The float is
	 * likely to be different than the result of {@link #ipt(float, float, float, float)} unless hue, saturation,
	 * lightness, and opacity are all 0. This won't allocate any objects.
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
		final int e = BitConversion.floatToRawIntBits(basis);
		final float i = Math.min(Math.max(light + (e & 0xff) / 255f, 0f), 1f);
		opacity = Math.min(Math.max(opacity + (e >>> 24 & 0xfe) * 0x1.020408p-8f, 0f), 1f);
		if (i <= 0.001f)
			return BitConversion.intBitsToFloat((((int) (opacity * 255f) << 24) & 0xFE000000) | 0x808000);
		final float p = ((e >>> 7 & 0x1fe) - 0xff) / 255f;
		final float t = ((e >>> 15 & 0x1fe) - 0xff) / 255f;
		final float l = reverseTransform(i + 0.097569f * p + 0.205226f * t);
		final float m = reverseTransform(i + -0.11388f * p + 0.133217f * t);
		final float s = reverseTransform(i + 0.032615f * p + -0.67689f * t);
		final float r = reverseGamma(Math.min(Math.max(5.432622f * l + -4.67910f * m + 0.246257f * s, 0f), 1f));
		final float g = reverseGamma(Math.min(Math.max(-1.10517f * l + 2.311198f * m + -0.20588f * s, 0f), 1f));
		final float b = reverseGamma(Math.min(Math.max(0.028104f * l + -0.19466f * m + 1.166325f * s, 0f), 1f));
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
	 * {@link #ipt(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
	 * is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp
	 * towards white. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and both
	 * chroma of start as-is. This method does not necessarily keep the resulting color in-gamut; after performing some
	 * changes with this or other component-editing methods (like {@link #protanUp(float, float)} or
	 * {@link #tritanDown(float, float)}), you may want to call {@link #limitToGamut(float)} to make sure the color can
	 * be rendered correctly.
	 *
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
	 * {@link #ipt(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
	 * is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp
	 * towards white. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and both
	 * chroma of start as-is. This method does not necessarily keep the resulting color in-gamut; after performing some
	 * changes with this or other component-editing methods (like {@link #protanUp(float, float)} or
	 * {@link #tritanDown(float, float)}), you may want to call {@link #limitToGamut(float)} to make sure the color can
	 * be rendered correctly.
	 *
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
	 * {@link #ipt(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors,
	 * and is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to
	 * lerp towards a warmer color. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the
	 * alpha and intensity of start as-is.
	 * This method does not necessarily keep the resulting color in-gamut; after performing some changes with this or
	 * other component-editing methods (like {@link #lighten(float, float)} or {@link #tritanDown(float, float)}), you
	 * may want to call {@link #limitToGamut(float)} to make sure the color can be rendered correctly.
	 *
	 * @see #protanDown(float, float) the counterpart method that cools a float color
	 * @param start the starting color as a packed float
	 * @param change how much to warm start, as a float between 0 and 1; higher means a warmer result
	 * @return a packed float that represents a color between start and a warmer color
	 */
	public static float protanUp(final float start, final float change) {
		final int s = BitConversion.floatToRawIntBits(start), p = s >>> 8 & 0xFF, other = s & 0xFEFF00FF;
		return BitConversion.intBitsToFloat(((int) (p + (0xFF - p) * change) << 8 & 0xFF00) | other);
	}

	/**
	 * Interpolates from the packed float color start towards a cooler color (green to blue) by change. While change
	 * should be between 0f (return start as-is) and 1f (return fully cooled), start should be a packed color, as from
	 * {@link #ipt(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
	 * is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp
	 * towards a cooler color. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and
	 * intensity of start as-is.
	 * This method does not necessarily keep the resulting color in-gamut; after performing some changes with this or
	 * other component-editing methods (like {@link #lighten(float, float)} or {@link #tritanDown(float, float)}), you
	 * may want to call {@link #limitToGamut(float)} to make sure the color can be rendered correctly.
	 *
	 * @see #protanUp(float, float) the counterpart method that warms a float color
	 * @param start the starting color as a packed float
	 * @param change how much to cool start, as a float between 0 and 1; higher means a cooler result
	 * @return a packed float that represents a color between start and a cooler color
	 */
	public static float protanDown(final float start, final float change) {
		final int s = BitConversion.floatToRawIntBits(start), p = s >>> 8 & 0xFF, other = s & 0xFEFF00FF;
		return BitConversion.intBitsToFloat(((int) (p * (1f - change)) & 0xFF) << 8 | other);
	}

	/**
	 * Interpolates from the packed float color start towards a "natural" color (between green and orange) by change.
	 * While change should be between 0f (return start as-is) and 1f (return fully natural), start should be a packed color, as
	 * from {@link #ipt(float, float, float, float)}. This is a good way to reduce allocations of temporary
	 * Colors, and is a little more efficient and clear than using
	 * {@link FloatColors#lerpFloatColors(float, float, float)} to lerp towards a more natural color. Unlike
	 * {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and intensity of start as-is.
	 * This method does not necessarily keep the resulting color in-gamut; after performing some changes with this or
	 * other component-editing methods (like {@link #protanUp(float, float)} or {@link #darken(float, float)}), you
	 * may want to call {@link #limitToGamut(float)} to make sure the color can be rendered correctly.
	 *
	 * @see #tritanDown(float, float) the counterpart method that makes a float color less natural
	 * @param start the starting color as a packed float
	 * @param change how much to change start to a natural color, as a float between 0 and 1; higher means a more natural result
	 * @return a packed float that represents a color between start and a more natural color
	 */
	public static float tritanUp(final float start, final float change) {
		final int s = BitConversion.floatToRawIntBits(start), t = s >>> 16 & 0xFF, other = s & 0xFE00FFFF;
		return BitConversion.intBitsToFloat(((int) (t + (0xFF - t) * change) << 16 & 0xFF0000) | other);
	}

	/**
	 * Interpolates from the packed float color start towards an "artificial" color (between blue and purple) by change.
	 * While change should be between 0f (return start as-is) and 1f (return fully artificial), start should be a packed color, as
	 * from {@link #ipt(float, float, float, float)}. This is a good way to reduce allocations of temporary
	 * Colors, and is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp
	 * towards a more artificial color. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the
	 * alpha and intensity of start as-is.
	 * This method does not necessarily keep the resulting color in-gamut; after performing some changes with this or
	 * other component-editing methods (like {@link #protanUp(float, float)} or {@link #darken(float, float)}), you
	 * may want to call {@link #limitToGamut(float)} to make sure the color can be rendered correctly.
	 *
	 * @see #tritanUp(float, float) the counterpart method that makes a float color less artificial
	 * @param start the starting color as a packed float
	 * @param change how much to change start to a bolder color, as a float between 0 and 1; higher means a more artificial result
	 * @return a packed float that represents a color between start and a more artificial color
	 */
	public static float tritanDown(final float start, final float change) {
		final int s = BitConversion.floatToRawIntBits(start), t = s >>> 16 & 0xFF, other = s & 0xFE00FFFF;
		return BitConversion.intBitsToFloat(((int) (t * (1f - change)) & 0xFF) << 16 | other);
	}

	/**
	 * Interpolates from the packed float color start towards that color made opaque by change. While change should be
	 * between 0f (return start as-is) and 1f (return start with full alpha), start should be a packed color, as from
	 * {@link #ipt(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
	 * is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp
	 * towards transparent. This won't change the intensity, protan, or tritan of the color.
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
	 * {@link #ipt(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors,
	 * and is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp towards
	 * transparent. This won't change the intensity, protan, or tritan of the color.
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
	 * from {@link #ipt(float, float, float, float)}. This only changes protan and tritan; it leaves intensity and alpha
	 * alone, unlike {@link #lessenChange(float, float)}, which usually changes intensity.
	 * @see #enrich(float, float) the counterpart method that makes a float color more saturated
	 * @param start the starting color as a packed float
	 * @param change how much to change start to a desaturated color, as a float between 0 and 1; higher means a less saturated result
	 * @return a packed float that represents a color between start and a desaturated color
	 */
	public static float dullen(final float start, final float change) {
		final int s = BitConversion.floatToRawIntBits(start);
		return ipt((s & 0xFF) / 255f,
				((s >>> 8 & 0xFF) / 255f - 0.5f) * (1f - change) + 0.5f,
				((s >>> 16 & 0xFF) / 255f - 0.5f) * (1f - change) + 0.5f,
				(s >>> 25) / 127f);
	}

	/**
	 * Pushes the chromatic components of {@code start} away from grayscale by change (saturating them). While change
	 * should be between 0f (return start as-is) and 1f (return maximally saturated), start should be a packed color, as
	 * from {@link #ipt(float, float, float, float)}. This usually changes only protan and tritan, but higher values for
	 * {@code change} can force the color out of the gamut, which this corrects using
	 * {@link #limitToGamut(float, float, float, float)} (and that can change intensity somewhat). If the color stays
	 * in-gamut, then intensity won't change; alpha never changes.
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
	 * @param mainColor a packed float color, as produced by {@link #ipt(float, float, float, float)}; this is the color that will be adjusted
	 * @param contrastingColor a packed float color, as produced by {@link #ipt(float, float, float, float)}; the adjusted mainColor will contrast with this
	 * @return a different IPT packed float color, based on mainColor but with potentially very different lightness
	 */
	public static float inverseLightness(final float mainColor, final float contrastingColor)
	{
		final int bits = BitConversion.floatToRawIntBits(mainColor),
				contrastBits = BitConversion.floatToRawIntBits(contrastingColor),
				i = (bits & 0xff),
				p = (bits >>> 8 & 0xff),
				t = (bits >>> 16 & 0xff),
				ci = (contrastBits & 0xff),
				cp = (contrastBits >>> 8 & 0xff),
				ct = (contrastBits >>> 16 & 0xff);
		if((p - cp) * (p - cp) + (t - ct) * (t - ct) >= 0x10000)
			return mainColor;
		return ipt(ci < 128 ? i * (0.45f / 255f) + 0.55f : 0.5f - i * (0.45f / 255f), p / 255f, t / 255f, 0x1.0p-8f * (bits >>> 24));
	}

	/**
	 * Given a packed float IPT_HQ color {@code mainColor} and another IPT_HQ color that it should be made to contrast
	 * with, gets a packed float IPT_HQ color with I that should be quite different from {@code contrastingColor}'s I,
	 * but the same chromatic channels and opacity (A and B are likely to be clamped if the result gets close to white
	 * or black). This allows most of the colors this method produces to contrast well as a foreground when displayed on
	 * a background of {@code contrastingColor}, or vice versa.
	 * <br>
	 * This is similar to {@link #inverseLightness(float, float)}, but is considerably simpler, and this method will
	 * change the lightness of mainColor when the two given colors have close lightness but distant chroma. Because it
	 * averages the original I of mainColor with the modified one, this tends to not produce harsh color changes.
	 * @param mainColor a packed IPT_HQ float color; this is the color that will be adjusted
	 * @param contrastingColor a packed IPT_HQ float color; the adjusted mainColor will contrast with the I of this
	 * @return a different packed IPT_HQ float color, based on mainColor but typically with different lightness
	 */
	public static float differentiateLightness(final float mainColor, final float contrastingColor)
	{
		final int main = BitConversion.floatToRawIntBits(mainColor), contrast = BitConversion.floatToRawIntBits(contrastingColor);
		return limitToGamut(BitConversion.intBitsToFloat((main & 0xFEFFFF00) | (contrast + 128 & 0xFF) + (main & 0xFF) >>> 1));
	}

	/**
	 * Pretty simple; adds 0.5 to the given color's I and wraps it around if it would go above 1.0, then averages that
	 * with the original I. This means light colors become darker, and dark colors become lighter, with almost all
	 * results in the middle-range of possible lightness.
	 * @param mainColor a packed IPT_HQ float color
	 * @return a different packed IPT_HQ float color, with its I channel changed and limited to the correct gamut
	 */
	public static float offsetLightness(final float mainColor) {
		final int decoded = BitConversion.floatToRawIntBits(mainColor);
		return limitToGamut(BitConversion.intBitsToFloat((decoded & 0xFEFFFF00) | (decoded + 128 & 0xFF) + (decoded & 0xFF) >>> 1));
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
		final int e = BitConversion.floatToRawIntBits(color),
				is = 0x80, ps = 0x80, ts = 0x80,
				ie = (e & 0xFF), pe = (e >>> 8) & 0xFF, te = (e >>> 16) & 0xFF, ae = e >>> 24 & 0xFE;
		return BitConversion.intBitsToFloat(((int) (is + fraction * (ie - is)) & 0xFF)
				| (((int) (ps + fraction * (pe - ps)) & 0xFF) << 8)
				| (((int) (ts + fraction * (te - ts)) & 0xFF) << 16)
				| (ae << 24));
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
	 * @param color a packed float color, as produced by {@link #ipt(float, float, float, float)}
	 * @param seed a long seed that should be different on each call; should not be 0
	 * @param variance max amount of difference between the given color and the generated color; always less than 1
	 * @return a generated packed float color that should be at least somewhat different from {@code color}
	 */
	public static float randomEdit(final float color, long seed, final float variance) {
		final int decoded = BitConversion.floatToRawIntBits(color);
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
				return BitConversion.intBitsToFloat((decoded & 0xFE000000) | ((int)(z * 255.5f) << 16 & 0xFF0000)
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
		final int decoded = BitConversion.floatToRawIntBits(packed);
		final float i = (decoded & 0xff) / 255f;
		final float p = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float t = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float l = reverseTransform(i + 0.097569f * p + 0.205226f * t);
		final float m = reverseTransform(i + -0.11388f * p + 0.133217f * t);
		final float s = reverseTransform(i + 0.032615f * p + -0.67689f * t);

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
		final float l = reverseTransform(i + 0.097569f * p + 0.205226f * t);
		final float m = reverseTransform(i + -0.11388f * p + 0.133217f * t);
		final float s = reverseTransform(i + 0.032615f * p + -0.67689f * t);

		final float r = 5.432622f * l + -4.67910f * m + 0.246257f * s;
		if(r < 0f || r > 1.0f) return false;
		final float g = -1.10517f * l + 2.311198f * m + -0.20588f * s;
		if(g < 0f || g > 1.0f) return false;
		final float b = 0.028104f * l + -0.19466f * m + 1.166325f * s;
		return (b >= 0f && b <= 1.0f);
	}

	/**
	 * Iteratively checks whether the given IPT color is in-gamut, and either brings the color closer to grayscale if it
	 * isn't in-gamut, or returns it as soon as it is in-gamut. Maintains the intensity of the color, only bringing
	 * protan and tritan closer to grayscale.
	 * @param packed a packed float color in IPT format; often this color is not in-gamut
	 * @return the first color this finds that is between the given IPT color and grayscale, and is in-gamut
	 * @see #inGamut(float) You can use inGamut() if you just want to check whether a color is in-gamut.
	 */
	public static float limitToGamut(final float packed) {
		final int decoded = BitConversion.floatToRawIntBits(packed);
		final float i = (decoded & 0xff) / 255f;
		final float p = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float t = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		float p2 = p, t2 = t;
		for (int attempt = 31; attempt >= 0; attempt--) {
			final float l = reverseTransform(i + 0.097569f * p2 + 0.205226f * t2);
			final float m = reverseTransform(i + -0.11388f * p2 + 0.133217f * t2);
			final float s = reverseTransform(i + 0.032615f * p2 + -0.67689f * t2);

			final float r = 5.432622f * l + -4.67910f * m + 0.246257f * s;
			final float g = -1.10517f * l + 2.311198f * m + -0.20588f * s;
			final float b = 0.028104f * l + -0.19466f * m + 1.166325f * s;
			if(r >= 0f && r <= 1f && g >= 0f && g <= 1f && b >= 0f && b <= 1f)
				break;
			final float progress = attempt * 0x1p-5f;
			p2 = MathTools.lerp(0, p, progress);
			t2 = MathTools.lerp(0, t, progress);
		}
		return ipt(i, p2 * 0.5f + 0.5f, t2 * 0.5f + 0.5f, (decoded >>> 25) / 127f);
	}

	/**
	 * Iteratively checks whether the given IPT color is in-gamut, and either brings the color closer to grayscale if it
	 * isn't in-gamut, or returns it as soon as it is in-gamut. Maintains the intensity of the color, only bringing
	 * protan and tritan closer to grayscale. This always produces an opaque color.
	 * @param i intensity component; will be clamped between 0 and 1 if it isn't already
	 * @param p protan component; will be clamped between 0 and 1 if it isn't already
	 * @param t tritan component; will be clamped between 0 and 1 if it isn't already
	 * @return the first color this finds that is between the given IPT color and grayscale, and is in-gamut
	 * @see #inGamut(float, float, float)  You can use inGamut() if you just want to check whether a color is in-gamut.
	 */
	public static float limitToGamut(float i, float p, float t) {
		return limitToGamut(i, p, t, 1f);
	}
	/**
	 * Iteratively checks whether the given IPT color is in-gamut, and either brings the color closer to grayscale if it
	 * isn't in-gamut, or returns it as soon as it is in-gamut. Maintains the intensity of the color, only bringing
	 * protan and tritan closer to grayscale.
	 * @param i intensity component; will be clamped between 0 and 1 if it isn't already
	 * @param p protan component; will be clamped between 0 and 1 if it isn't already
	 * @param t tritan component; will be clamped between 0 and 1 if it isn't already
	 * @param a alpha component; will be clamped between 0 and 1 if it isn't already
	 * @return the first color this finds that is between the given IPT color and grayscale, and is in-gamut
	 * @see #inGamut(float, float, float)  You can use inGamut() if you just want to check whether a color is in-gamut.
	 */
	public static float limitToGamut(float i, float p, float t, float a) {
		float i2 = Math.min(Math.max(i, 0f), 1f);
		float p2 = p = Math.min(Math.max((p - 0.5f) * 2f, -1f), 1f);
		float t2 = t = Math.min(Math.max((t - 0.5f) * 2f, -1f), 1f);
		a = Math.min(Math.max(a, 0f), 1f);
		for (int attempt = 31; attempt >= 0; attempt--) {
			final float l = reverseTransform(i2 + 0.097569f * p2 + 0.205226f * t2);
			final float m = reverseTransform(i2 + -0.11388f * p2 + 0.133217f * t2);
			final float s = reverseTransform(i2 + 0.032615f * p2 + -0.67689f * t2);

			final float r = 5.432622f * l + -4.67910f * m + 0.246257f * s;
			final float g = -1.10517f * l + 2.311198f * m + -0.20588f * s;
			final float b = 0.028104f * l + -0.19466f * m + 1.166325f * s;
			if(r >= 0f && r <= 1f && g >= 0f && g <= 1f && b >= 0f && b <= 1f)
				break;
			final float progress = attempt * 0x1p-5f;
			p2 = MathTools.lerp(0, p, progress);
			t2 = MathTools.lerp(0, t, progress);
		}
		return ipt(i2, p2 * 0.5f + 0.5f, t2 * 0.5f + 0.5f, a);
	}
	/**
	 * Given a packed float IPT_HQ color, this edits its intensity, protan, tritan, and alpha channels by adding the
	 * corresponding "add" parameter and then clamping. This returns a different float value (of course, the given float
	 * can't be edited in-place). You can give a value of 0 for any "add" parameter you want to stay unchanged. This
	 * clamps the resulting color to remain in-gamut, so it should be safe to convert it back to RGBA.
	 * @param encoded a packed float IPT_HQ color
	 * @param addI how much to add to the intensity channel; typically in the -1 to 1 range
	 * @param addP how much to add to the protan channel; typically in the -2 to 2 range
	 * @param addT how much to add to the tritan channel; typically in the -2 to 2 range
	 * @param addAlpha how much to add to the alpha channel; typically in the -1 to 1 range
	 * @return a packed float IPT_HQ color with the requested edits applied to {@code encoded}
	 */
	public static float editIPT(float encoded, float addI, float addP, float addT, float addAlpha) {
		return editIPT(encoded, addI, addP, addT, addAlpha, 1f, 1f, 1f, 1f);
	}
	/**
	 * Given a packed float IPT_HQ color, this edits its intensity, protan, tritan, and alpha channels by first
	 * multiplying each channel by the corresponding "mul" parameter and then adding the corresponding "add" parameter,
	 * before clamping. This means the intensity value is multiplied by {@code mulI}, then has {@code addI} added, and
	 * then is clamped to the normal range for intensity (0 to 1). This returns a different float value (of course, the
	 * given float can't be edited in-place). You can give a value of 0 for any "add" parameter you want to stay
	 * unchanged, or a value of 1 for any "mul" parameter that shouldn't change. Note that this manipulates protan and
	 * tritan in the -1 to 1 range, so if you multiply by a small number like {@code 0.25f}, then this will produce a
	 * less-saturated color, and if you multiply by a larger number like {@code 4f}, then you will get a much
	 * more-saturated color. This clamps the resulting color to remain in-gamut, so it should be safe to convert it back
	 * to RGBA.
	 * @param encoded a packed float IPT_HQ color
	 * @param addI how much to add to the intensity channel; typically in the -1 to 1 range
	 * @param addP how much to add to the protan channel; typically in the -2 to 2 range
	 * @param addT how much to add to the tritan channel; typically in the -2 to 2 range
	 * @param addAlpha how much to add to the alpha channel; typically in the -1 to 1 range
	 * @param mulI how much to multiply the intensity channel by; should be non-negative
	 * @param mulP how much to multiply the protan channel by; usually non-negative (not always)
	 * @param mulT how much to multiply the tritan channel by; usually non-negative (not always)
	 * @param mulAlpha how much to multiply the alpha channel by; should be non-negative
	 * @return a packed float IPT_HQ color with the requested edits applied to {@code encoded}
	 */
	public static float editIPT(float encoded, float addI, float addP, float addT, float addAlpha,
								float mulI, float mulP, float mulT, float mulAlpha) {
		final int decoded = BitConversion.floatToRawIntBits(encoded);
		float i = (decoded & 0xff) / 255f;
		float p = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		float t = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		float alpha = (decoded >>> 25) / 127f;

		float i2 = Math.min(Math.max(i * mulI + addI, 0f), 1f);
		float p2 = p = Math.min(Math.max(p * mulP + addP, -1f), 1f);
		float t2 = t = Math.min(Math.max(t * mulT + addT, -1f), 1f);
		alpha = Math.min(Math.max(alpha * mulAlpha + addAlpha, 0f), 1f);
		for (int attempt = 31; attempt >= 0; attempt--) {
			final float l = reverseTransform(i2 + 0.097569f * p2 + 0.205226f * t2);
			final float m = reverseTransform(i2 + -0.11388f * p2 + 0.133217f * t2);
			final float s = reverseTransform(i2 + 0.032615f * p2 + -0.67689f * t2);

			final float r = 5.432622f * l + -4.67910f * m + 0.246257f * s;
			final float g = -1.10517f * l + 2.311198f * m + -0.20588f * s;
			final float b = 0.028104f * l + -0.19466f * m + 1.166325f * s;
			if(r >= 0f && r <= 1f && g >= 0f && g <= 1f && b >= 0f && b <= 1f)
				break;
			final float progress = attempt * 0x1p-5f;
			p2 = MathTools.lerp(0, p, progress);
			t2 = MathTools.lerp(0, t, progress);
		}
		return ipt(i2, p2 * 0.5f + 0.5f, t2 * 0.5f + 0.5f, alpha);
	}

	/**
	 * Produces a random packed float color that is always in-gamut (and opaque) and should be uniformly distributed.
	 * @param random a Random object (preferably a subclass of Random, like {@link com.github.tommyettinger.digital.AlternateRandom})
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
		return ipt(i, p, t, 1f);
	}

	/**
	 * Produces a random packed float color that is always in-gamut (and opaque) and should be uniformly distributed.
	 * This is only present for legacy compatibility; new code should use {@link #randomColor(Random)}.
	 * @param random a Random object (or preferably a subclass of Random, like {@link com.github.tommyettinger.digital.AlternateRandom})
	 * @return a packed float color that is always in-gamut
	 * @deprecated Use {@link #randomColor(Random)} instead.
	 */
	@Deprecated
	public static float randomizedColor(Random random) {
		float i = random.nextFloat();
		float p = random.nextFloat();
		float t = random.nextFloat();
		while (!inGamut(i, p, t)) {
			i = random.nextFloat();
			p = random.nextFloat();
			t = random.nextFloat();
		}
		return ipt(i, p, t, 1f);
	}

}
