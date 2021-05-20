package com.github.tommyettinger.colorful.pure.ycwcm;

import com.github.tommyettinger.colorful.pure.FloatColors;
import com.github.tommyettinger.colorful.pure.MathTools;
import com.github.tommyettinger.ds.support.BitConversion;

import java.util.Random;

/**
 * Contains code for manipulating colors as {@code int} and packed {@code float} values in the YCwCm color space.
 */
public class ColorTools {
	/**
	 * Gets a packed float representation of a color given as 4 float components, here, Y (luma or lightness), Cw
	 * (chromatic warmth), Cm (chromatic mildness), and A (alpha or opacity).
	 * Luma should be between 0 and 1, inclusive, with 0 used for very dark colors (almost only black), and 1 used for
	 * very light colors (almost only white). The two chroma values range from 0.0 to 1.0, and there's some aesthetic
	 * value in changing just one chroma value. When warm is high and mild is low, the color is more reddish, when both
	 * are low it is more bluish, when mild is high and warm is low, the color tends to be greenish, and when both are
	 * high it tends to be brown or yellow. When warm and mild are both near 0.5f, the color is closer to gray.  Alpha
	 * is the multiplicative opacity of the color, and acts like RGBA's alpha.
	 * <br>
	 * This method bit-masks the resulting color's byte values, so any values can technically be given to this as luma,
	 * warm, and mild, but they will only be reversible from the returned float color to the original Y, Cw, and Cm
	 * values if the original values were a valid YCwCm color and not an "imaginary color." {@link #fromRGBA(float)}
	 * should always produce a valid YCwCm color.
	 *
	 * @param luma       0f to 1f, luma or Y component of YCwCm, with 0.5f meaning "no change" and 1f brightening
	 * @param warm       0f to 1f, "chroma warm" or Cw component of YCwCm, with 1f more red or yellow
	 * @param mild       0f to 1f, "chroma mild" or Cm component of YCwCm, with 1f more green or yellow
	 * @param alpha      0f to 1f, 0f makes the color transparent and 1f makes it opaque 
	 * @return a float encoding a color with the given properties
	 */
	public static float ycwcm(float luma, float warm, float mild, float alpha) {
		return BitConversion.intBitsToFloat(((int) (alpha * 255) << 24 & 0xFE000000) | ((int) (mild * 255) << 16 & 0xFF0000)
				| ((int) (warm * 255) << 8 & 0xFF00) | ((int) (luma * 255) & 0xFF));
	}

	/**
	 * Converts a packed float color in the format produced by {@link ColorTools#ycwcm(float, float, float, float)} to an RGBA8888 int.
	 * This format of int can be used with Pixmap and in some other places in libGDX.
	 * @param packed a packed float color, as produced by {@link ColorTools#ycwcm(float, float, float, float)}
	 * @return an RGBA8888 int color
	 */
	public static int toRGBA8888(final float packed)
	{
		final int decoded = BitConversion.floatToRawIntBits(packed), y = (decoded & 0xff),
				cw = ((decoded >>> 7 & 0x1fe) - 0xff),
				cm = (((decoded >>> 15 & 0x1fe) - 0xff) >> 1);
		return Math.min(Math.max(y + (cw * 5 >> 3) - cm, 0), 0xFF) << 24
				| Math.min(Math.max(y - (cw * 3 >> 3) + cm, 0), 0xFF) << 16
				| Math.min(Math.max(y - (cw * 3 >> 3) - cm, 0), 0xFF) << 8
				| (decoded & 0xfe000000) >>> 24 | decoded >>> 31;
	}

	/**
	 * Converts a packed float color in the format produced by {@link ColorTools#ycwcm(float, float, float, float)}
	 * to a packed float in RGBA format.
	 * This format of float can be used with the standard SpriteBatch and in some other places in libGDX.
	 * @param packed a packed float color, as produced by {@link ColorTools#ycwcm(float, float, float, float)}
	 * @return a packed float color as RGBA
	 */
	public static float toRGBA(final float packed)
	{
		final int decoded = BitConversion.floatToRawIntBits(packed), y = (decoded & 0xff),
				cw = ((decoded >>> 7 & 0x1fe) - 0xff),
				cm = (((decoded >>> 15 & 0x1fe) - 0xff) >> 1);
		return BitConversion.intBitsToFloat(Math.min(Math.max(y + (cw * 5 >> 3) - cm, 0), 0xFF)
				| Math.min(Math.max(y - (cw * 3 >> 3) + cm, 0), 0xFF) << 8
				| Math.min(Math.max(y - (cw * 3 >> 3) - cm, 0), 0xFF) << 16
				| (decoded & 0xfe000000));
	}

	/**
	 * Takes a color encoded as an RGBA8888 int and converts to a packed float in the YCwCm this uses.
	 * @param rgba an int with the channels (in order) red, green, blue, alpha; should have 8 bits per channel
	 * @return a packed float as YCwCm, which this class can use
	 */
	public static float fromRGBA8888(final int rgba) {
		return BitConversion.intBitsToFloat(((rgba >>> 24) * 3 + (rgba >>> 16 & 0xFF) * 4 + (rgba >>> 8 & 0xFF) >> 3)
				| (0xFF + (rgba >>> 24) - (rgba >>> 8 & 0xFF) & 0x1FE) << 7
				| (0xFF + (rgba >>> 16 & 0xFF) - (rgba >>> 8 & 0xFF) & 0x1FE) << 15
				| (rgba & 0xFE) << 24);
	}

	/**
	 * Takes a color encoded as an RGBA8888 packed float and converts to a packed float in the YCwCm this uses.
	 * @param packed a packed float in RGBA8888 format, with A in the MSB and R in the LSB
	 * @return a packed float as YCwCm, which this class can use
	 */
	public static float fromRGBA(final float packed) {
		final int rgba = BitConversion.floatToRawIntBits(packed);
		return BitConversion.intBitsToFloat(((rgba & 0xFF) * 3 + (rgba >>> 8 & 0xFF) * 4 + (rgba >>> 16 & 0xFF) >> 3)
				| (0xFF + (rgba & 0xFF) - (rgba >>> 16 & 0xFF) & 0x1FE) << 7
				| (0xFF + (rgba >>> 8 & 0xFF) - (rgba >>> 16 & 0xFF) & 0x1FE) << 15
				| (rgba >>> 24 & 0xFE) << 24);
	}

	/**
	 * Takes RGBA components from 0.0 to 1.0 each and converts to a packed float in the YCwCm this uses.
	 * @param r red, from 0.0 to 1.0 (both inclusive)
	 * @param g green, from 0.0 to 1.0 (both inclusive)
	 * @param b blue, from 0.0 to 1.0 (both inclusive)
	 * @param a alpha, from 0.0 to 1.0 (both inclusive)
	 * @return a packed float as YCwCm, which this class can use
	 */
	public static float fromRGBA(final float r, final float g, final float b, final float a) {
		return BitConversion.intBitsToFloat((int) (255 * (r * 0x3p-3f + g * 0x4p-3f + b * 0x1p-3f)) & 0xFF
						| (int)((r - b + 1f) * 127.5f) << 8 & 0xFF00
						| (int)((g - b + 1f) * 127.5f) << 16 & 0xFF0000
						| ((int)(a * 255f) << 24 & 0xFE000000));
	}

	/**
	 * Gets the red channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #ycwcm(float, float, float, float)}
	 * @return an int from 0 to 255, inclusive, representing the red channel value of the given encoded color
	 */
	public static int redInt(final float encoded)
	{
		final int decoded = BitConversion.floatToRawIntBits(encoded);
		return (decoded & 0xff) + (((decoded >>> 7 & 0x1fe) - 0xff) * 5 >>> 4) - (((decoded >>> 15 & 0x1fe) - 0xff) >>> 2);
	}

	/**
	 * Gets the green channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #ycwcm(float, float, float, float)}
	 * @return an int from 0 to 255, inclusive, representing the green channel value of the given encoded color
	 */
	public static int greenInt(final float encoded)
	{
		final int decoded = BitConversion.floatToRawIntBits(encoded);
		return (decoded & 0xff) - (((decoded >>> 7 & 0x1fe) - 0xff) * 3 >> 4) + (((decoded >>> 15 & 0x1fe) - 0xff) >> 2);
	}

	/**
	 * Gets the blue channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #ycwcm(float, float, float, float)}
	 * @return an int from 0 to 255, inclusive, representing the blue channel value of the given encoded color
	 */
	public static int blueInt(final float encoded)
	{
		final int decoded = BitConversion.floatToRawIntBits(encoded);
		return (decoded & 0xff) - (((decoded >>> 7 & 0x1fe) - 0xff) * 3 >> 4) - (((decoded >>> 15 & 0x1fe) - 0xff) >> 2);
	}

	/**
	 * Gets the alpha channel value of the given encoded color, as an even int ranging from 0 to 254, inclusive. Because
	 * of how alpha is stored in libGDX, no odd-number values are possible for alpha.
	 * @param encoded a color as a packed float that can be obtained by {@link #ycwcm(float, float, float, float)}
	 * @return an even int from 0 to 254, inclusive, representing the alpha channel value of the given encoded color
	 */
	public static int alphaInt(final float encoded)
	{
		return (BitConversion.floatToRawIntBits(encoded) & 0xfe000000) >>> 24;
	}

	/**
	 * Gets the red channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #ycwcm(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the red channel value of the given encoded color
	 */
	public static float red(final float encoded)
	{
		final int decoded = BitConversion.floatToRawIntBits(encoded);
		return Math.min(Math.max((decoded & 0xff) * 0x1.010102p-8f + ((decoded >>> 8 & 0xff) - 127.5f) * (0x1.414142p-9f) - ((decoded >>> 16 & 0xff) - 127.5f) * 0x1.010102p-9f, 0f), 1f);
	}

	/**
	 * Gets the green channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #ycwcm(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the green channel value of the given encoded color
	 */
	public static float green(final float encoded)
	{
		final int decoded = BitConversion.floatToRawIntBits(encoded);
		return Math.min(Math.max((decoded & 0xff) * 0x1.010102p-8f - (((decoded >>> 8 & 0xff) - 127.5f) * 0x1.818184p-10f) + ((decoded >>> 16 & 0xff) - 127.5f) * 0x1.010102p-9f, 0f), 1f);
	}

	/**
	 * Gets the blue channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #ycwcm(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the blue channel value of the given encoded color
	 */
	public static float blue(final float encoded)
	{
		final int decoded = BitConversion.floatToRawIntBits(encoded);
		return Math.min(Math.max((decoded & 0xff) * 0x1.010102p-8f - (((decoded >>> 8 & 0xff) - 127.5f) * 0x1.818184p-10f) - ((decoded >>> 16 & 0xff) - 127.5f) * 0x1.010102p-9f, 0f), 1f);
	}

	/**
	 * Gets the alpha channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #ycwcm(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the alpha channel value of the given encoded color
	 */
	public static float alpha(final float encoded)
	{
		return ((BitConversion.floatToRawIntBits(encoded) & 0xfe000000) >>> 24) * 0.003937008f;
	}


	/**
	 * Gets a color as a YCwCm packed float given floats representing hue, saturation, lightness, and opacity.
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
	 * @param encoded a color as a packed float that can be obtained by {@link #ycwcm(float, float, float, float)}
	 * @return the saturation of the color from 0.0 (a grayscale color; inclusive) to 1.0 (a bright color, inclusive)
	 */
	public static float saturation(final float encoded) {
		final int decoded = BitConversion.floatToRawIntBits(encoded), lu = (decoded & 0xff),
				cw = ((decoded >>> 7 & 0x1fe) - 0xff),
				cm = (((decoded >>> 15 & 0x1fe) - 0xff) >> 1);
		final float r = Math.min(Math.max(lu + (cw * 5 >> 3) - cm, 0), 0xFF) * 0x1.010102p-8f;
		final float g = Math.min(Math.max(lu - (cw * 3 >> 3) + cm, 0), 0xFF) * 0x1.010102p-8f;
		final float b =  Math.min(Math.max(lu - (cw * 3 >> 3) - cm, 0), 0xFF) * 0x1.010102p-8f;
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
		float li = x * (1f - 0.5f * d / (x + 1e-10f));
		return (x - li) / (Math.min(li, 1f - li) + 1e-10f);
	}

	public static float lightness(final float encoded) {
		final int decoded = BitConversion.floatToRawIntBits(encoded), lu = (decoded & 0xff),
				cw = ((decoded >>> 7 & 0x1fe) - 0xff),
				cm = (((decoded >>> 15 & 0x1fe) - 0xff) >> 1);
		final float r = Math.min(Math.max(lu + (cw * 5 >> 3) - cm, 0), 0xFF) * 0x1.010102p-8f;
		final float g = Math.min(Math.max(lu - (cw * 3 >> 3) + cm, 0), 0xFF) * 0x1.010102p-8f;
		final float b =  Math.min(Math.max(lu - (cw * 3 >> 3) - cm, 0), 0xFF) * 0x1.010102p-8f;
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
	 * @param encoded a color as a packed float that can be obtained by {@link #ycwcm(float, float, float, float)}
	 * @return The hue of the color from 0.0 (red, inclusive) towards orange, then yellow, and
	 * eventually to purple before looping back to almost the same red (1.0, exclusive)
	 */
	public static float hue(final float encoded) {
		final int decoded = BitConversion.floatToRawIntBits(encoded), lu = (decoded & 0xff),
				cw = ((decoded >>> 7 & 0x1fe) - 0xff),
				cm = (((decoded >>> 15 & 0x1fe) - 0xff) >> 1);
		final float r = Math.min(Math.max(lu + (cw * 5 >> 3) - cm, 0), 0xFF) * 0x1.010102p-8f;
		final float g = Math.min(Math.max(lu - (cw * 3 >> 3) + cm, 0), 0xFF) * 0x1.010102p-8f;
		final float b =  Math.min(Math.max(lu - (cw * 3 >> 3) - cm, 0), 0xFF) * 0x1.010102p-8f;
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
	 * The "luma" of the given packed float in YCwCm format, which is like its lightness; ranges from 0.0f to
	 * 1.0f . YCwCm is useful for modifications to colors:
	 * <ul>
	 *     <li>You can get a grayscale version of a color by setting Cw and Cm to 0.5,</li>
	 *     <li>You can desaturate by subtracting 0.5, multiplying Cw and Cm by a number between 0 and 1, and adding 0.5
	 *     afterwards,</li>
	 *     <li>you can oversaturate by subtracting 0.5, multiplying Cw and Cm by a number greater than 1, and adding 0.5
	 *     afterwards,</li>
	 *     <li>you can lighten or darken by increasing or decreasing luma,</li>
	 *     <li>and so on and so forth.</li>
	 * </ul>
	 * @param encoded a packed float
	 * @return the luma as a float from 0.0f to 1.0f
	 */
	public static float luma(final float encoded)
	{
		return (BitConversion.floatToRawIntBits(encoded) & 0xff) * 0x1.010102p-8f;
//        final int bits = BitConversion.floatToRawIntBits(encoded);
//        return (bits & 0xFF) * 0x3p-11f + (bits >>> 8 & 0xFF) * 0x1p-9f + (bits >>> 16 & 0xFF) * 0x1p-11f;
	}

	/**
	 * The "chroma warm" of the given packed float in YCwCm format, which when combined with chroma mild describes the
	 * shade and saturation of a color; ranges from 0f to 1f . YCwCm is useful for modifications to colors:
	 * <ul>
	 *     <li>You can get a grayscale version of a color by setting Cw and Cm to 0.5,</li>
	 *     <li>You can desaturate by subtracting 0.5, multiplying Cw and Cm by a number between 0 and 1, and adding 0.5
	 *     afterwards,</li>
	 *     <li>you can oversaturate by subtracting 0.5, multiplying Cw and Cm by a number greater than 1, and adding 0.5
	 *     afterwards,</li>
	 *     <li>you can lighten or darken by increasing or decreasing luma,</li>
	 *     <li>and so on and so forth.</li>
	 * </ul>
	 * @param encoded a color encoded as a packed float, as by {@link #ycwcm(float, float, float, float)}
	 * @return the chroma warm as a float from 0f to 1f
	 */
	public static float chromaWarm(final float encoded)
	{
		return ((BitConversion.floatToRawIntBits(encoded) >>> 8 & 0xff)) * 0x1.010102p-8f;
	}

	/**
	 * The "chroma mild" of the given packed float in YCwCm format, which when combined with chroma warm describes the
	 * shade and saturation of a color; ranges from 0f to 1f .
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
	 * @param encoded a color encoded as a packed float, as by {@link #ycwcm(float, float, float, float)}
	 * @return the chroma mild as a float from 0f to 1f
	 */
	public static float chromaMild(final float encoded)
	{
		return ((BitConversion.floatToRawIntBits(encoded) >>> 16 & 0xff)) * 0x1.010102p-8f;
	}

	/**
	 * Gets a variation on the packed float color basis as another packed float that has its hue, saturation, lightness,
	 * and opacity adjusted by the specified amounts. Note that this edits the color in HSL space, not YCwCm! Takes
	 * floats representing the amounts of change to apply to hue, saturation, lightness, and opacity; these can be
	 * between -1f and 1f. Returns a float that can be used as a packed or encoded color. The float is
	 * likely to be different than the result of {@link #ycwcm(float, float, float, float)} unless hue, saturation,
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
		opacity = Math.min(Math.max(opacity + (e >>> 24 & 0xfe) * 0x1.020408p-8f, 0f), 1f);
		if (light + (e & 0xff) * 0x1.010102p-8f <= 0.001f)
			return BitConversion.intBitsToFloat((((int) (opacity * 255f) << 24) & 0xFE000000) | 0x7F7F00);
		final int lu = (e & 0xff);
		final int cw = ((e >>> 7 & 0x1fe) - 0xff);
		final int cm = (((e >>> 15 & 0x1fe) - 0xff) >> 1);
		final float r = Math.min(Math.max(lu + light + (cw * 0.625f) - cm, 0), 255f) * 0x1.010102p-8f;
		final float g = Math.min(Math.max(lu + light - (cw * 0.375f) + cm, 0), 255f) * 0x1.010102p-8f;
		final float b = Math.min(Math.max(lu + light - (cw * 0.375f) - cm, 0), 255f) * 0x1.010102p-8f;
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
	 * {@link #ycwcm(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
	 * is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp towards
	 * white. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and both chroma of start as-is.
	 * @see #darken(float, float) the counterpart method that darkens a float color
	 * @param start the starting color as a packed float
	 * @param change how much to go from start toward white, as a float between 0 and 1; higher means closer to white
	 * @return a packed float that represents a color between start and white
	 */
	public static float lighten(final float start, final float change) {
		final int s = BitConversion.floatToRawIntBits(start), luma = s & 0xFF, other = s & 0xFEFFFF00;
		return BitConversion.intBitsToFloat(((int) (luma + (0xFF - luma) * change) & 0xFF) | other);
	}

	/**
	 * Interpolates from the packed float color start towards black by change. While change should be between 0f (return
	 * start as-is) and 1f (return black), start should be a packed color, as from
	 * {@link #ycwcm(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
	 * is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp towards
	 * black. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and both chroma of start as-is.
	 * @see #lighten(float, float) the counterpart method that lightens a float color
	 * @param start the starting color as a packed float
	 * @param change how much to go from start toward black, as a float between 0 and 1; higher means closer to black
	 * @return a packed float that represents a color between start and black
	 */
	public static float darken(final float start, final float change) {
		final int s = BitConversion.floatToRawIntBits(start), luma = s & 0xFF, other = s & 0xFEFFFF00;
		return BitConversion.intBitsToFloat(((int) (luma * (1f - change)) & 0xFF) | other);
	}

	/**
	 * Interpolates from the packed float color start towards a warmer color (yellow to red) by change. While change
	 * should be between 0f (returnstart as-is) and 1f (return fully warmed), start should be a packed color, as from
	 * {@link #ycwcm(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors,
	 * and is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp towards
	 * a warmer color. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and luma of start
	 * as-is.
	 * @see #cool(float, float) the counterpart method that cools a float color
	 * @param start the starting color as a packed float
	 * @param change how much to warm start, as a float between 0 and 1; higher means a warmer result
	 * @return a packed float that represents a color between start and a warmer color
	 */
	public static float warm(final float start, final float change) {
		final int s = BitConversion.floatToRawIntBits(start), warmth = s >>> 8 & 0xFF, other = s & 0xFEFF00FF;
		return BitConversion.intBitsToFloat(((int) (warmth + (0xFF - warmth) * change) << 8 & 0xFF) | other);
	}

	/**
	 * Interpolates from the packed float color start towards a cooler color (green to blue) by change. While change
	 * should be between 0f (return start as-is) and 1f (return fully cooled), start should be a packed color, as from
	 * {@link #ycwcm(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
	 * is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp towards
	 * a cooler color. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and luma of start
	 * as-is.
	 * @see #warm(float, float) the counterpart method that warms a float color
	 * @param start the starting color as a packed float
	 * @param change how much to cool start, as a float between 0 and 1; higher means a cooler result
	 * @return a packed float that represents a color between start and a cooler color
	 */
	public static float cool(final float start, final float change) {
		final int s = BitConversion.floatToRawIntBits(start), warmth = s >>> 8 & 0xFF, other = s & 0xFEFF00FF;
		return BitConversion.intBitsToFloat(((int) (warmth * (1f - change)) & 0xFF) << 8 | other);
	}

	/**
	 * Interpolates from the packed float color start towards a milder color (between green and yellow) by change. While
	 * change should be between 0f (return start as-is) and 1f (return fully mild), start should be a packed color, as
	 * from {@link #ycwcm(float, float, float, float)}. This is a good way to reduce allocations of temporary
	 * Colors, and is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp
	 * towards a milder color. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and luma of
	 * start as-is.
	 * @see #strengthen(float, float) the counterpart method that makes a float color more bold
	 * @param start the starting color as a packed float
	 * @param change how much to change start to a milder color, as a float between 0 and 1; higher means a milder result
	 * @return a packed float that represents a color between start and a milder color
	 */
	public static float weaken(final float start, final float change) {
		final int s = BitConversion.floatToRawIntBits(start), warmth = s >>> 8 & 0xFF, other = s & 0xFEFF00FF;
		return BitConversion.intBitsToFloat(((int) (warmth + (0xFF - warmth) * change) << 8 & 0xFF) | other);
	}

	/**
	 * Interpolates from the packed float color start towards a bolder color (between blue and red) by change. While
	 * change should be between 0f (return start as-is) and 1f (return fully cooled), start should be a packed color, as
	 * from {@link #ycwcm(float, float, float, float)}. This is a good way to reduce allocations of temporary
	 * Colors, and is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp
	 * towards a bolder color. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and luma of
	 * start as-is.
	 * @see #weaken(float, float) the counterpart method that makes a float color more mild
	 * @param start the starting color as a packed float
	 * @param change how much to change start to a bolder color, as a float between 0 and 1; higher means a bolder result
	 * @return a packed float that represents a color between start and a bolder color
	 */
	public static float strengthen(final float start, final float change) {
		final int s = BitConversion.floatToRawIntBits(start), warmth = s >>> 8 & 0xFF, other = s & 0xFEFF00FF;
		return BitConversion.intBitsToFloat(((int) (warmth * (1f - change)) & 0xFF) << 8 | other);
	}

	/**
	 * Interpolates from the packed float color start towards that color made opaque by change. While change should be
	 * between 0f (return start as-is) and 1f (return start with full alpha), start should be a packed color, as from
	 * {@link #ycwcm(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
	 * is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp towards
	 * transparent. This won't change the luma, chroma warm, or chroma mild of the color.
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
	 * {@link #ycwcm(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors,
	 * and is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp towards
	 * transparent. This won't change the luma, chroma warm, or chroma mild of the color.
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
	 * from {@link #ycwcm(float, float, float, float)}. This only changes Cw and Cm; it leaves Y and alpha alone, unlike
	 * {@link #lessenChange(float, float)}, which usually changes Y.
	 * @see #enrich(float, float) the counterpart method that makes a float color more saturated
	 * @param start the starting color as a packed float
	 * @param change how much to change start to a desaturated color, as a float between 0 and 1; higher means a less saturated result
	 * @return a packed float that represents a color between start and a desaturated color
	 */
	public static float dullen(final float start, final float change) {
		final int s = BitConversion.floatToRawIntBits(start);
		return ycwcm((s & 0xFF) / 255f,
				((s >>> 8 & 0xFF) / 255f - 0.5f) * (1f - change) + 0.5f,
				((s >>> 16 & 0xFF) / 255f - 0.5f) * (1f - change) + 0.5f,
				(s >>> 25) / 127f);
	}

	/**
	 * Pushes the chromatic components of {@code start} away from grayscale by change (saturating them). While change
	 * should be between 0f (return start as-is) and 1f (return maximally saturated), start should be a packed color, as
	 * from {@link #ycwcm(float, float, float, float)}. This usually changes only Cw and Cm, but higher values for
	 * {@code change} can force the color out of the gamut, which this corrects using
	 * {@link #limitToGamut(float, float, float, float)} (and that can change Y somewhat). If the color stays in-gamut,
	 * then Y won't change; alpha never changes.
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
	 * Given a packed float YCwCm color {@code mainColor} and another YCwCm color that it should be made to contrast
	 * with, gets a packed float YCwCm color with roughly inverted luma but the same chromatic channels and opacity (Cw
	 * and Cm are likely to be clamped if the result gets close to white or black). This won't ever produce black or
	 * other very dark colors, and also has a gap in the range it produces for luma values between 0.5 and 0.55. That
	 * allows most of the colors this method produces to contrast well as a foreground when displayed on a background of
	 * {@code contrastingColor}, or vice versa. This will leave the luma unchanged if the chromatic channels of the
	 * contrastingColor and those of the mainColor are already very different. This has nothing to do with the contrast
	 * channel of the tweak in ColorfulBatch; where that part of the tweak can make too-similar lightness values further
	 * apart by just a little, this makes a modification on {@code mainColor} to maximize its lightness difference from
	 * {@code contrastingColor} without losing its other qualities.
	 * @param mainColor a packed float color, as produced by {@link #ycwcm(float, float, float, float)}; this is the color that will be adjusted
	 * @param contrastingColor a packed float color, as produced by {@link #ycwcm(float, float, float, float)}; the adjusted mainColor will contrast with this
	 * @return a different packed float color, based on mainColor but with potentially very different lightness
	 */
	public static float inverseLightness(final float mainColor, final float contrastingColor)
	{
		final int bits = BitConversion.floatToRawIntBits(mainColor),
				contrastBits = BitConversion.floatToRawIntBits(contrastingColor),
				luma = (bits & 0xff),
				warm = (bits >>> 8 & 0xff),
				mild = (bits >>> 16 & 0xff),
				cLuma = (contrastBits & 0xff),
				cWarm = (contrastBits >>> 8 & 0xff),
				cMild = (contrastBits >>> 16 & 0xff);
		if((warm - cWarm) * (warm - cWarm) + (mild - cMild) * (mild - cMild) >= 0x10000)
			return mainColor;
		return ycwcm(cLuma < 128 ? luma * (0.45f / 255f) + 0.55f : 0.5f - luma * (0.45f / 255f), warm / 255f, mild / 255f, 0x1.010102p-8f * (bits >>> 24));
	}

	/**
	 * Given a packed float YCwCm color {@code mainColor} and another YCwCm color that it should be made to contrast
	 * with, gets a packed float YCwCm color with Y that should be quite different from {@code contrastingColor}'s Y,
	 * but the same chromatic channels and opacity (Cw and Cm are likely to be clamped if the result gets close to white
	 * or black). This allows most of the colors this method produces to contrast well as a foreground when displayed on
	 * a background of {@code contrastingColor}, or vice versa.
	 * <br>
	 * This is similar to {@link #inverseLightness(float, float)}, but is considerably simpler, and this method will
	 * change the lightness of mainColor when the two given colors have close lightness but distant chroma. Because it
	 * averages the original Y of mainColor with the modified one, this tends to not produce harsh color changes.
	 * @param mainColor a packed YCwCm float color; this is the color that will be adjusted
	 * @param contrastingColor a packed YCwCm float color; the adjusted mainColor will contrast with the Y of this
	 * @return a different packed YCwCm float color, based on mainColor but typically with different lightness
	 */
	public static float differentiateLightness(final float mainColor, final float contrastingColor)
	{
		final int main = BitConversion.floatToRawIntBits(mainColor), contrast = BitConversion.floatToRawIntBits(contrastingColor);
		return limitToGamut(BitConversion.intBitsToFloat((main & 0xFEFFFF00) | (contrast + 128 & 0xFF) + (main & 0xFF) >>> 1));
	}

	/**
	 * Pretty simple; adds 0.5 to the given color's Y and wraps it around if it would go above 1.0, then averages that
	 * with the original Y. This means light colors become darker, and dark colors become lighter, with almost all
	 * results in the middle-range of possible lightness.
	 * @param mainColor a packed YCwCm float color
	 * @return a different packed YCwCm float color, with its Y channel changed and limited to the correct gamut
	 */
	public static float offsetLightness(final float mainColor) {
		final int decoded = BitConversion.floatToRawIntBits(mainColor);
		return limitToGamut(BitConversion.intBitsToFloat((decoded & 0xFEFFFF00) | (decoded + 128 & 0xFF) + (decoded & 0xFF) >>> 1));
	}

	/**
	 * Makes the additive YCwCm color stored in {@code color} cause less of a change when used as a tint, as if it were
	 * mixed with neutral gray. When {@code fraction} is 1.0, this returns color unchanged; when fraction is 0.0, it
	 * returns {@link Palette#GRAY}, and when it is in-between 0.0 and 1.0 it returns something between the two. This is
	 * meant for things like area of effect abilities that make smaller color changes toward their periphery.
	 * @param color a color that should have its tinting effect potentially weakened
	 * @param fraction how much of {@code color} should be kept, from 0.0 to 1.0
	 * @return a YCwCm float color between gray and {@code color}
	 */
	public static float lessenChange(final float color, float fraction) {
		final int e = BitConversion.floatToRawIntBits(color),
				ys = 0x7F, cws = 0x7F, cms = 0x7F, as = 0xFE,
				ye = (e & 0xFF), cwe = (e >>> 8) & 0xFF, cme = (e >>> 16) & 0xFF, ae = e >>> 24 & 0xFE;
		return BitConversion.intBitsToFloat(((int) (ys + fraction * (ye - ys)) & 0xFF)
				| (((int) (cws + fraction * (cwe - cws)) & 0xFF) << 8)
				| (((int) (cms + fraction * (cme - cms)) & 0xFF) << 16)
				| (((int) (as + fraction * (ae - as)) & 0xFE) << 24));
	}
	/**
	 * Makes a quasi-randomly-edited variant on the given {@code color}, allowing typically a small amount of
	 * {@code variance} (such as 0.05 to 0.25) between the given color and what this can return. The {@code seed} should
	 * be different each time this is called, and can be obtained from a random number generator to make the colors more
	 * random, or can be incremented on each call. If the seed is only incremented or decremented, then this shouldn't
	 * produce two similar colors in a row unless variance is very small. The variance affects the Y, Cw, and Cm of the
	 * generated color, and each of those channels can go up or down by the given variance as long as the total distance
	 * isn't greater than the variance (this considers Cw and Cm extra-wide, going from -1 to 1, while I goes from 0 to
	 * 1, but only internally for measuring distance).
	 * @param color a packed float color, as produced by {@link #ycwcm(float, float, float, float)}
	 * @param seed a long seed that should be different on each call; should not be 0
	 * @param variance max amount of difference between the given color and the generated color; always less than 1
	 * @return a generated packed float color that should be at least somewhat different from {@code color}
	 */
	public static float randomEdit(final float color, long seed, final float variance) {
		final int decoded = BitConversion.floatToRawIntBits(color);
		final float y = (decoded & 0xff) / 255f;
		final float cw = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float cm = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float limit = variance * variance;
		float dist = limit + 1f, a = 0, b = 0, c = 0;
		while (dist > limit){
			a = (((seed * 0xD1B54A32D192ED03L >>> 41) - 0x7FFFFFp-1f) * 0x1p-22f) * variance;
			b = (((seed * 0xABC98388FB8FAC03L >>> 41) - 0x7FFFFFp-1f) * 0x1p-22f) * variance;
			c = (((seed * 0x8CB92BA72F3D8DD7L >>> 41) - 0x7FFFFFp-1f) * 0x1p-22f) * variance;
			seed += 0x9E3779B97F4A7C15L;
			dist = a * a + b * b + c * c;
		}
		return BitConversion.intBitsToFloat((decoded & 0xFE000000) | ((int)(Math.min(Math.max(cm + c, -1), 1) * 127.5f + 128f) << 16 & 0xFF0000)
				| ((int)(Math.min(Math.max(cw + b, -1), 1) * 127.5f + 128f) << 8 & 0xFF00) | (int)(Math.min(Math.max(y + a, 0), 1) * 255f));
	}

	/**
	 * Returns true if the given packed float color, as YCwCm, is valid to convert losslessly back to RGBA. 
	 * @param packed a packed float color as YCwCm
	 * @return true if the given packed float color can be converted back and forth to RGBA
	 */
	public static boolean inGamut(final float packed)
	{
		final int decoded = BitConversion.floatToRawIntBits(packed), y = (decoded & 0xff),
				cw = ((decoded >>> 7 & 0x1fe) - 0xff),
				cm = (((decoded >>> 15 & 0x1fe) - 0xff) / 2);
		final int r = y + (cw * 5 / 8) - cm;
		if(r < 0 || r > 255) return false;
		final int g = y - (cw * 3 / 8) + cm;
		if(g < 0 || g > 255) return false;
		final int b = y - (cw * 3 / 8) - cm;
		return (b >= 0) && (b <= 255);
	}
	/**
	 * Returns true if the given YCwCm values are valid to convert losslessly back to RGBA. 
	 * @param y luma channel, as a float from 0 to 1
	 * @param cw chromatic warmth channel, as a float from 0 to 1
	 * @param cm chromatic mildness channel, as a float from 0 to 1
	 * @return true if the given packed float color can be converted back and forth to RGBA
	 */
	public static boolean inGamut(float y, float cw, float cm)
	{
		final int yi = (int)(y * 255.999f), cwi = (int) ((cw - 0.5f) * 511.999f), cmi = (int) ((cm - 0.5f) * 255.999f);
		final int r = yi + (cwi * 5 / 8) - cmi;
		if(r < 0 || r > 255) return false;
		final int g = yi - (cwi * 3 / 8) + cmi;
		if(g < 0 || g > 255) return false;
		final int b = yi - (cwi * 3 / 8) - cmi;
		return (b >= 0) && (b <= 255);
	}

	/**
	 * Iteratively checks whether the given YCwCm color is in-gamut, and either brings the color closer to 50% gray if
	 * it isn't in-gamut, or returns it as soon as it is in-gamut.
	 * @param packed a packed float color in YCwCm format; often this color is not in-gamut
	 * @return the first color this finds that is between the given YCwCm color and 50% gray, and is in-gamut
	 * @see #inGamut(float) You can use inGamut() if you just want to check whether a color is in-gamut.
	 */
	public static float limitToGamut(final float packed) {
		final int decoded = BitConversion.floatToRawIntBits(packed);
		final float y = (decoded & 0xff) / 255f;
		final float cw = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float cm = ((decoded >>> 16 & 0xff) - 127.5f) / 255f;
		float y2 = y, cw2 = cw, cm2 = cm;
		for (int attempt = 31; attempt >= 0; attempt--) {
			final float r = y2 + 0.625f * cw2 - cm2;
			final float g = y2 - 0.375f * cw2 + cm2;
			final float b = y2 - 0.375f * cw2 - cm2;
			if(r >= 0f && r <= 1f && g >= 0f && g <= 1f && b >= 0f && b <= 1f)
				break;
			final float progress = attempt * 0x1p-5f;
			y2 = MathTools.lerp(0.5f, y, progress);
			cw2 = MathTools.lerp(0, cw, progress);
			cm2 = MathTools.lerp(0, cm, progress);
		}
		return ycwcm(y2, cw2 * 0.5f + 0.5f, cm2 * 0.5f + 0.5f, (decoded >>> 25) / 127f);
	}

	/**
	 * Iteratively checks whether the given YCwCm color is in-gamut, and either brings the color closer to 50% gray if
	 * it isn't in-gamut, or returns it as soon as it is in-gamut. This always produces an opaque color.
	 * @param y luma component; will be clamped between 0 and 1 if it isn't already
	 * @param cw chromatic warmth component; will be clamped between 0 and 1 if it isn't already
	 * @param cm chromatic mildness component; will be clamped between 0 and 1 if it isn't already
	 * @return the first color this finds that is between the given YCwCm color and 50% gray, and is in-gamut
	 * @see #inGamut(float, float, float)  You can use inGamut() if you just want to check whether a color is in-gamut.
	 */
	public static float limitToGamut(float y, float cw, float cm) {
		return limitToGamut(y, cw, cm, 1f);
	}

	/**
	 * Iteratively checks whether the given YCwCm color is in-gamut, and either brings the color closer to 50% gray if
	 * it isn't in-gamut, or returns it as soon as it is in-gamut.
	 * @param y luma component; will be clamped between 0 and 1 if it isn't already
	 * @param cw chromatic warmth component; will be clamped between 0 and 1 if it isn't already
	 * @param cm chromatic mildness component; will be clamped between 0 and 1 if it isn't already
	 * @param a alpha component; will be clamped between 0 and 1 if it isn't already
	 * @return the first color this finds that is between the given YCwCm color and 50% gray, and is in-gamut
	 * @see #inGamut(float, float, float)  You can use inGamut() if you just want to check whether a color is in-gamut.
	 */
	public static float limitToGamut(float y, float cw, float cm, float a)
	{
		float y2 = y = Math.min(Math.max(y, 0f), 1f);
		float cw2 = cw = Math.min(Math.max((cw - 0.5f) * 2f, -1f), 1f);
		float cm2 = cm = Math.min(Math.max((cm - 0.5f) * 2f, -1f), 1f);
		for (int attempt = 31; attempt >= 0; attempt--) {
			final float r = y2 + 0.625f * cw2 - cm2;
			final float g = y2 - 0.375f * cw2 + cm2;
			final float b = y2 - 0.375f * cw2 - cm2;
			if(r >= 0f && r <= 1f && g >= 0f && g <= 1f && b >= 0f && b <= 1f)
				break;
			final float progress = attempt * 0x1p-5f;
			y2 = MathTools.lerp(0.5f, y, progress);
			cw2 = MathTools.lerp(0, cw, progress);
			cm2 = MathTools.lerp(0, cm, progress);
		}
		return ycwcm(y2, cw2 * 0.5f + 0.5f, cm2 * 0.5f + 0.5f, Math.min(Math.max(a, 0f), 1f));
	}



	/**
	 * Produces a random packed float color that is always in-gamut and should be uniformly distributed.
	 * @param random a Random object (or preferably a subclass of Random, like {@link com.github.tommyettinger.ds.support.LaserRandom})
	 * @return a random opaque packed float color that is always in-gamut
	 */
	public static float randomColor(Random random) {
		final float yr = +0.375f, wr = +0.5f, mr = +0.0f;
		final float yg = +0.500f, wg = +0.0f, mg = +0.5f;
		final float yb = +0.125f, wb = -0.5f, mb = -0.5f;
		final float r = random.nextFloat(), g = random.nextFloat(), b = random.nextFloat();
		return BitConversion.intBitsToFloat(0xFE000000
				| ((int) ((mr * r + mg * g + mb * b) * 128f + 128f) << 16 & 0xFF0000)
				| ((int) ((wr * r + wg * g + wb * b) * 128f + 128f) << 8 & 0xFF00)
				| ((int) ((yr * r + yg * g + yb * b) * 256f) & 0xFF));
	}

}
