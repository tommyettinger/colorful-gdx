package com.github.tommyettinger.colorful.rgb;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.NumberUtils;
import com.github.tommyettinger.colorful.FloatColors;
import com.github.tommyettinger.colorful.Shaders;
import com.github.tommyettinger.colorful.ycwcm.Palette;

import java.util.Random;

/**
 * Contains code for manipulating colors as {@code int}, packed {@code float}, and {@link Color} values in the RGB
 * color space. All of these colors have an alpha channel as well as red, green, and blue; the package name isn't
 * "rgba" because "ycwcm" and "ipt" don't mention alpha either and they also have that channel. RGB is the standard mode
 * for colors in libGDX, and conversions to and from RGB are in the ColorTools classes for each other color space (like
 * {@link com.github.tommyettinger.colorful.ipt.ColorTools#fromRGBA(float, float, float, float)} and
 * {@link com.github.tommyettinger.colorful.ycwcm.ColorTools#toRGBA(float)}).
 */
public class ColorTools {
	/**
	 * Gets a packed float representation of a color given as 4 float components, here, R (red), G (green), B (blue),
	 * and A (alpha or opacity). As long as you use a batch with {@link Shaders#fragmentShaderRGBA} or
	 * {@link Shaders#fragmentShaderGammaRGBA} as its shader, colors passed with
	 * {@link com.badlogic.gdx.graphics.g2d.Batch#setPackedColor(float)} will be interpreted as RGB. All channels range
	 * from 0.0 to 1.0, inclusive. Alpha is the multiplicative opacity of the color.
	 * <br>
	 * This method bit-masks the resulting color's byte values, so any values can technically be given to this as red,
	 * green, and blue, but they will only be reversible from the returned float color to the original R, G, and B
	 * values if the original values were in the range that {@link #red(float)}, {@link #green(float)}, and
	 * {@link #blue(float)} return.
	 *
	 * @param red       0f to 1f, red channel
	 * @param green     0f to 1f, green channel
	 * @param blue      0f to 1f, blue channel
	 * @param alpha     0f to 1f, 0f makes the color transparent and 1f makes it opaque
	 * @return a float encoding a color with the given properties
	 */
	public static float rgb(float red, float green, float blue, float alpha) {
		return NumberUtils.intBitsToFloat(((int) (alpha * 255) << 24 & 0xFE000000) | ((int) (blue * 255) << 16 & 0xFF0000)
				| ((int) (green * 255) << 8 & 0xFF00) | ((int) (red * 255) & 0xFF));
	}

	/**
	 * Writes an RGB-format packed float color (the format produced by {@link ColorTools#rgb(float, float, float, float)})
	 * into an RGBA8888 Color as used by libGDX (called {@code editing}).
	 * @param editing a libGDX color that will be filled in-place with an RGBA conversion of {@code packed}
	 * @param packed a packed float color, as produced by {@link ColorTools#rgb(float, float, float, float)}
	 * @return an RGBA8888 int color
	 */
	public static Color toColor(Color editing, final float packed)
	{
		Color.rgba8888ToColor(editing, Integer.reverseBytes(NumberUtils.floatToRawIntBits(packed)));
		return editing;
	}
	/**
	 * Converts a packed float color in the format produced by {@link #rgb(float, float, float, float)} to an
	 * RGBA8888 int. This format of int can be used with Pixmap and in some other places in libGDX.
	 * @param packed a packed float color, as produced by {@link #rgb(float, float, float, float)}
	 * @return an RGBA8888 int color
	 */
	public static int toRGBA8888(final float packed) {
		return Integer.reverseBytes(NumberUtils.floatToIntColor(packed));
	}

	/**
	 * Takes a color encoded as an RGBA8888 int and converts to a packed float in the RGB format this uses.
	 * @param rgba an int with the channels (in order) red, green, blue, alpha; should have 8 bits per channel
	 * @return a packed float as RGB, which this class can use
	 */
	public static float fromRGBA8888(final int rgba) {
		return NumberUtils.intBitsToFloat(Integer.reverseBytes(rgba) & 0xFEFFFFFF);
	}

	/**
	 * Takes a libGDX Color that uses RGBA8888 channels and converts to a packed float in the RGB format this uses.
	 * @param color a libGDX RGBA8888 Color
	 * @return a packed float as RGB, which this class can use
	 */
	public static float fromColor(final Color color) {
		return color.toFloatBits();
	}

	/**
	 * Takes RGBA components from 0.0 to 1.0 each and converts to a packed float in the RGBA format this uses.
	 * @param r red, from 0.0 to 1.0 (both inclusive)
	 * @param g green, from 0.0 to 1.0 (both inclusive)
	 * @param b blue, from 0.0 to 1.0 (both inclusive)
	 * @param a alpha, from 0.0 to 1.0 (both inclusive)
	 * @return a packed float as RGBA, which this class can use
	 */
	public static float fromRGBA(final float r, final float g, final float b, final float a) {
		return NumberUtils.intBitsToFloat(
				          Math.min(Math.max((int)(r * 255.999f), 0), 255)
						| Math.min(Math.max((int)(g * 255.999f), 0), 255) << 8
						| Math.min(Math.max((int)(b * 255.999f), 0), 255) << 16
						| ((int)(a * 255f) << 24 & 0xFE000000));
	}

	/**
	 * Gets the red channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #rgb(float, float, float, float)}
	 * @return an int from 0 to 255, inclusive, representing the red channel value of the given encoded color
	 */
	public static int redInt(final float encoded) {
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		return (decoded & 0xff);
	}

	/**
	 * Gets the green channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #rgb(float, float, float, float)}
	 * @return an int from 0 to 255, inclusive, representing the green channel value of the given encoded color
	 */
	public static int greenInt(final float encoded) {
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		return (decoded >>> 8 & 0xff);
	}

	/**
	 * Gets the blue channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #rgb(float, float, float, float)}
	 * @return an int from 0 to 255, inclusive, representing the blue channel value of the given encoded color
	 */
	public static int blueInt(final float encoded) {
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		return  (decoded >>> 16 & 0xff);
	}

	/**
	 * Gets the alpha channel value of the given encoded color, as an even int ranging from 0 to 254, inclusive. Because
	 * of how alpha is stored in libGDX, no odd-number values are possible for alpha.
	 * @param encoded a color as a packed float that can be obtained by {@link #rgb(float, float, float, float)}
	 * @return an even int from 0 to 254, inclusive, representing the alpha channel value of the given encoded color
	 */
	public static int alphaInt(final float encoded) {
		return (NumberUtils.floatToRawIntBits(encoded) & 0xfe000000) >>> 24;
	}

	/**
	 * Gets the red channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #rgb(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the red channel value of the given encoded color
	 */
	public static float red(final float encoded) {
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		return  (decoded & 0xff) / 255f;
	}

	/**
	 * Gets the green channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #rgb(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the green channel value of the given encoded color
	 */
	public static float green(final float encoded) {
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		return  (decoded >>> 8 & 0xff) / 255f;
	}

	/**
	 * Gets the blue channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #rgb(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the blue channel value of the given encoded color
	 */
	public static float blue(final float encoded) {
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		return  (decoded >>> 16 & 0xff) / 255f;
	}

	/**
	 * Gets the alpha channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #rgb(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the alpha channel value of the given encoded color
	 */
	public static float alpha(final float encoded) {
		return ((NumberUtils.floatToRawIntBits(encoded) & 0xfe000000) >>> 24) * 0x1.020408p-8f;
	}

	/**
	 * Gets a color as an RGBA packed float given floats representing hue, saturation, lightness, and opacity.
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
			return FloatColors.hsl2rgb(hue, saturation, lightness, opacity);
		}
	}

	/**
	 * Gets the saturation of the given encoded color, as a float ranging from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #rgb(float, float, float, float)}
	 * @return the saturation of the color from 0.0 (a grayscale color; inclusive) to 1.0 (a bright color, inclusive)
	 */
	public static float saturation(final float encoded) {
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		final float r = (decoded & 0xff) / 255f;
		final float g = (decoded >>> 8 & 0xff) / 255f;
		final float b = (decoded >>> 16 & 0xff) / 255f;
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
		final float r = (decoded & 0xff) / 255f;
		final float g = (decoded >>> 8 & 0xff) / 255f;
		final float b = (decoded >>> 16 & 0xff) / 255f;
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
	 * @param encoded a color as a packed float that can be obtained by {@link #rgb(float, float, float, float)}
	 * @return The hue of the color from 0.0 (red, inclusive) towards orange, then yellow, and
	 * eventually to purple before looping back to almost the same red (1.0, exclusive)
	 */
	public static float hue(final float encoded) {
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		final float r = (decoded & 0xff) / 255f;
		final float g = (decoded >>> 8 & 0xff) / 255f;
		final float b = (decoded >>> 16 & 0xff) / 255f;
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
	 * Gets a variation on the packed float color basis as another packed float that has its hue, saturation, value, and
	 * opacity adjusted by the specified amounts. Takes floats representing the amounts of change to apply to hue,
	 * saturation, value, and opacity; these can be between -1f and 1f. Returns a float that can be used as a packed or
	 * encoded color with methods like {@link com.badlogic.gdx.graphics.g2d.Batch#setPackedColor(float)}. The float is
	 * likely to be different than the result of {@link #rgb(float, float, float, float)} unless hue saturation,
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
		opacity = Math.min(Math.max(opacity + (decoded >>> 24 & 0xfe) * 0x1.020408p-8f, 0f), 1f);
		final float r = (decoded & 0xff) / 255f;
		final float g = (decoded >>> 8 & 0xff) / 255f;
		final float b = (decoded >>> 16 & 0xff) / 255f;
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
		final float light = x * (1f - 0.5f * d / (x + 1e-10f));
		hue += Math.abs(z + (w - y) / (6f * d + 1e-10f)) + 1f;
		saturation += (x - light) / (Math.min(light, 1f - light) + 1e-10f);
		return FloatColors.hsl2rgb(hue - (int)hue, Math.min(Math.max(saturation, 0f), 1f), light, opacity);
	}

	/**
	 * Interpolates from the packed float color start towards white by change. While change should be between 0f (return
	 * start as-is) and 1f (return white), start should be a packed color, as from
	 * {@link #rgb(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
	 * is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp towards
	 * white. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and both chroma of start as-is.
	 * @see #darken(float, float) the counterpart method that darkens a float color
	 * @param start the starting color as a packed float
	 * @param change how much to go from start toward white, as a float between 0 and 1; higher means closer to white
	 * @return a packed float that represents a color between start and white
	 */
	public static float lighten(final float start, final float change) {
		final int s = NumberUtils.floatToRawIntBits(start), r = s & 0xFF, g = s >>> 8 & 0xFF, b = s >>> 16 & 0xFF,
				a = s & 0xFE000000;
		return NumberUtils.intBitsToFloat(
				((int) (r + (0xFF - r) * change) & 0xFF) |
				((int) (g + (0xFF - g) * change) & 0xFF) << 8 |
				((int) (b + (0xFF - b) * change) & 0xFF) << 16 |
				a);
	}

	/**
	 * Interpolates from the packed float color start towards black by change. While change should be between 0f (return
	 * start as-is) and 1f (return black), start should be a packed color, as from
	 * {@link #rgb(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
	 * is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp towards
	 * black. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and both chroma of start as-is.
	 * @see #lighten(float, float) the counterpart method that lightens a float color
	 * @param start the starting color as a packed float
	 * @param change how much to go from start toward black, as a float between 0 and 1; higher means closer to black
	 * @return a packed float that represents a color between start and black
	 */
	public static float darken(final float start, final float change) {
		final int s = NumberUtils.floatToRawIntBits(start), r = s & 0xFF, g = s >>> 8 & 0xFF, b = s >>> 16 & 0xFF,
				a = s & 0xFE000000;
		return NumberUtils.intBitsToFloat(
				((int) (r * (1f - change)) & 0xFF) |
				((int) (g * (1f - change)) & 0xFF) << 8 |
				((int) (b * (1f - change)) & 0xFF) << 16 |
				a);
	}

	/**
	 * Interpolates from the packed float color start towards that color made opaque by change. While change should be
	 * between 0f (return start as-is) and 1f (return start with full alpha), start should be a packed color, as from
	 * {@link #rgb(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
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
	 * {@link #rgb(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors,
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
	 * Given a packed float RGBA color {@code mainColor} and another RGBA color that it should be made to contrast with,
	 * gets a packed float RGBA color with roughly inverted lightness (how the Oklab color space interprets it), but the
	 * same general hue and saturation unless the lightness gets too close to white or black. This won't ever produce
	 * black or other very dark colors, and also has a gap in the range it produces for lightness values between 0.5 and
	 * 0.55. That allows most of the colors this method produces to contrast well as a foreground when displayed on a
	 * background of {@code contrastingColor}, or vice versa. This will leave the lightness unchanged if the
	 * hue/saturation of the contrastingColor and those of the mainColor are already very different. This has nothing to
	 * do with the contrast channel of the tweak in ColorfulBatch; where that part of the tweak can make too-similar
	 * lightness values further apart by just a little, this makes a modification on {@code mainColor} to maximize its
	 * lightness difference from {@code contrastingColor} without losing its other qualities.
	 * @param mainColor a packed float color, as produced by {@link #rgb(float, float, float, float)}; this is the color that will be adjusted
	 * @param contrastingColor a packed float color, as produced by {@link #rgb(float, float, float, float)}; the adjusted mainColor will contrast with this
	 * @return a different RGBA packed float color, based on mainColor but with potentially very different lightness
	 */
	public static float inverseLightness(final float mainColor, final float contrastingColor)
	{
		return com.github.tommyettinger.colorful.oklab.ColorTools.toRGBA(
				com.github.tommyettinger.colorful.oklab.ColorTools.inverseLightness(
						com.github.tommyettinger.colorful.oklab.ColorTools.fromRGBA(mainColor),
						com.github.tommyettinger.colorful.oklab.ColorTools.fromRGBA(contrastingColor)));
	}

	/**
	 * Given a packed float RGBA color {@code mainColor} and another RGBA color that it should be made to contrast
	 * with, gets a packed float RGBA color with lightness that should be quite different from
	 * {@code contrastingColor}'s lightness, but the same chromatic channels and opacity. This allows most of the colors
	 * this method produces to contrast well as a foreground when displayed on a background of {@code contrastingColor},
	 * or vice versa. This goes through Oklab as an intermediate step, so lightness is in terms of Oklab's L channel.
	 * <br>
	 * This is similar to {@link #inverseLightness(float, float)}, but is considerably simpler, and this method will
	 * change the lightness of mainColor when the two given colors have close lightness but distant chroma. Because it
	 * averages the original lightness of mainColor with the modified one, this tends to produce softer color changes.
	 * @param mainColor a packed RGBA float color; this is the color that will be adjusted
	 * @param contrastingColor a packed RGBA float color; the adjusted mainColor will contrast with the lightness of this
	 * @return a different packed RGBA float color, based on mainColor but typically with different lightness
	 */
	public static float differentiateLightness(final float mainColor, final float contrastingColor)
	{
		final int main = NumberUtils.floatToRawIntBits(com.github.tommyettinger.colorful.oklab.ColorTools.fromRGBA(mainColor)),
				contrast = NumberUtils.floatToRawIntBits(com.github.tommyettinger.colorful.oklab.ColorTools.fromRGBA(contrastingColor));
		return com.github.tommyettinger.colorful.oklab.ColorTools.toRGBA(NumberUtils.intBitsToFloat((main & 0xFEFFFF00) | (contrast + 128 & 0xFF) + (main & 0xFF) >>> 1));
	}

	/**
	 * Pretty simple; adds 0.5 to the given color's lightness (calculated by converting it to Oklab internally) and
	 * wraps it around if it would go above 1.0, then averages that with the original lightness. This means light colors
	 * become darker, and dark colors become lighter, with almost all results in the middle-range of possible lightness.
	 * @param mainColor a packed RGBA float color
	 * @return a different packed RGBA float color, with its lightness channel changed and limited to the correct gamut
	 */
	public static float offsetLightness(final float mainColor) {
		final int oklab = NumberUtils.floatToRawIntBits(mainColor);
		return com.github.tommyettinger.colorful.oklab.ColorTools.toRGBA(NumberUtils.intBitsToFloat((oklab & 0xFEFFFF00) | (oklab + 128 & 0xFF) + (oklab & 0xFF) >>> 1));
	}

	/**
	 * Makes the additive RGBA color stored in {@code color} cause less of a change when used as a tint, as if it were
	 * mixed with neutral gray. When {@code fraction} is 1.0, this returns color unchanged; when fraction is 0.0, it
	 * returns {@link Palette#GRAY}, and when it is in-between 0.0 and 1.0 it returns something between the two. This is
	 * meant for things like area of effect abilities that make smaller color changes toward their periphery.
	 * @param color a color that should have its tinting effect potentially weakened
	 * @param fraction how much of {@code color} should be kept, from 0.0 to 1.0
	 * @return an RGBA float color between gray and {@code color}
	 */
	public static float lessenChange(final float color, float fraction) {
		final int e = NumberUtils.floatToRawIntBits(color),
				rs = 0x80, gs = 0x80, bs = 0x80, as = 0xFE,
				re = (e & 0xFF), ge = (e >>> 8) & 0xFF, be = (e >>> 16) & 0xFF, ae = e >>> 24 & 0xFE;
		return NumberUtils.intBitsToFloat(
				  ((int) (rs + fraction * (re - rs)) & 0xFF)
				| ((int) (gs + fraction * (ge - gs)) & 0xFF) << 8
				| ((int) (bs + fraction * (be - bs)) & 0xFF) << 16
				| ((int) (as + fraction * (ae - as)) & 0xFE) << 24);
	}

	/**
	 * Makes a quasi-randomly-edited variant on the given {@code color}, allowing typically a small amount of
	 * {@code variance} (such as 0.05 to 0.25) between the given color and what this can return. The {@code seed} should
	 * be different each time this is called, and can be obtained from a random number generator to make the colors more
	 * random, or can be incremented on each call. If the seed is only incremented or decremented, then this shouldn't
	 * produce two similar colors in a row unless variance is very small. The variance affects the R, G, and B of the
	 * generated color equally, and each of those channels can go up or down by the given variance as long as the total
	 * distance isn't greater than the variance.
	 * @param color a packed float color, as produced by {@link #rgb(float, float, float, float)}
	 * @param seed a long seed that should be different on each call; should not be 0
	 * @param variance max amount of difference between the given color and the generated color; always less than 1
	 * @return a generated packed float color that should be at least somewhat different from {@code color}
	 */
	public static float randomEdit(final float color, long seed, final float variance) {
		final int decoded = NumberUtils.floatToRawIntBits(color);
		final float r = (decoded & 0xff) / 255f;
		final float g = (decoded >>> 8 & 0xff) / 255f;
		final float b = (decoded >>> 16 & 0xff) / 255f;
		final float limit = variance * variance;
		float dist, x, y, z;
		for (int j = 0; j < 50; j++) {
			x = (((seed * 0xD1B54A32D192ED03L >>> 41) - 0x7FFFFFp-1f) * 0x1p-22f) * variance;
			y = (((seed * 0xABC98388FB8FAC03L >>> 41) - 0x7FFFFFp-1f) * 0x1p-22f) * variance;
			z = (((seed * 0x8CB92BA72F3D8DD7L >>> 41) - 0x7FFFFFp-1f) * 0x1p-22f) * variance;
			seed += 0x9E3779B97F4A7C15L;
			dist = x * x + y * y + z * z;
			x += r;
			y += g;
			z += b;
			if(dist <= limit)
				return NumberUtils.intBitsToFloat((decoded & 0xFE000000) | ((int)(z * 255.999f) << 16 & 0xFF0000)
					| ((int)(y * 255.999f) << 8 & 0xFF00) | (int)(x * 255.999f));
		}
		return color;
	}

	/**
	 * Produces a random packed float color that is always opaque and should be uniformly distributed.
	 * @param random a Random object (or preferably a subclass of Random, like {@link com.badlogic.gdx.math.RandomXS128})
	 * @return a packed float color that is always opaque
	 */
	public static float randomColor(Random random) {
		final float r = random.nextFloat(), g = random.nextFloat(), b = random.nextFloat();
		return NumberUtils.intBitsToFloat(0xFE000000
				| ((int) (b * 256f) << 16 & 0xFF0000)
				| ((int) (g * 256f) << 8 & 0xFF00)
				| ((int) (r * 256f) & 0xFF));
	}

	/**
	 * Limited-use; like {@link #randomColor(Random)} but for cases where you already have three floats (r, g, and b)
	 * distributed how you want. This can be somewhat useful if you are using a "subrandom" or "quasi-random" sequence,
	 * like the Halton, Sobol, or R3 sequences, to get 3D points and map them to colors. It can also be useful if you
	 * want to randomly generate the RGB channels yourself and track the values produced, as you would if you wanted to
	 * avoid generating too many colors with high blue, for instance.
	 * @param r red value to use; will be clamped between 0 and 1
	 * @param g green value to use; will be clamped between 0 and 1
	 * @param b blue value to use; will be clamped between 0 and 1
	 * @return a packed float color that is always opaque
	 */
	public static float subrandomColor(float r, float g, float b) {
		return NumberUtils.intBitsToFloat(0xFE000000
				| ((int) Math.min(Math.max(b * 256f, 0), 255.999f) << 16 & 0xFF0000)
				| ((int) Math.min(Math.max(g * 256f, 0), 255.999f) << 8 & 0xFF00)
				| ((int) Math.min(Math.max(r * 256f, 0), 255.999f) & 0xFF));
	}
}
