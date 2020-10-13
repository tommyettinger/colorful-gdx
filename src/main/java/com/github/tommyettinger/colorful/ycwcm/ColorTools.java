package com.github.tommyettinger.colorful.ycwcm;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.NumberUtils;
import com.github.tommyettinger.colorful.FloatColors;
import com.github.tommyettinger.colorful.Shaders;

/**
 * Contains code for manipulating colors as {@code int}, packed {@code float}, and {@link Color} values in the YCwCm
 * color space.
 */
public class ColorTools {
	/**
	 * Gets a packed float representation of a color given as 4 float components, here, Y (luma or lightness), Cw
	 * (chromatic warmth), Cm (chromatic mildness), and A (alpha or opacity). As long as you use a shader with
	 * {@link Shaders#fragmentShader} as its shader, colors passed with
	 * {@link com.badlogic.gdx.graphics.g2d.Batch#setPackedColor(float)} will be interpreted as YCwCmA.
	 * Luma should be between 0 and 1, inclusive, with 0 used for very dark colors (almost only black), and 1 used for
	 * very light colors (almost only white). The two chroma values range from 0.0 to 1.0, and there's some aesthetic
	 * value in changing just one chroma value. When warm is high and mild is low, the color is more reddish, when both
	 * are low it is more bluish, when mild is high and warm is low, the color tends to be greenish, and when both are
	 * high it tends to be brown or yellow. When warm and mild are both near 0.5f, the color is closer to gray.  Alpha
	 * is the multiplicative opacity of the color, and acts like RGBA's alpha.
	 * <br>
	 * This method bit-masks the resulting color's byte values, so any values can technically be given to this as luma,
	 * warm, and mild, but they will only be reversible from the returned float color to the original Y, Cw, and Cm
	 * values if the original values were in the range that {@link #chromaWarm(float)}, {@link #chromaMild(float)}, and
	 * {@link #luma(float)} return.
	 *
	 * @param luma       0f to 1f, luma or Y component of YCwCmA, with 0.5f meaning "no change" and 1f brightening
	 * @param warm       0f to 1f, "chroma warm" or Cw component of YCwCmA, with 1f more red or yellow
	 * @param mild       0f to 1f, "chroma mild" or Cm component of YCwCmA, with 1f more green or yellow
	 * @param alpha      0f to 1f, 0f makes the color transparent and 1f makes it opaque 
	 * @return a float encoding a color with the given properties
	 */
	public static float ycwcma(float luma, float warm, float mild, float alpha) {
		return NumberUtils.intBitsToFloat(((int) (alpha * 255) << 24 & 0xFE000000) | ((int) (mild * 255) << 16 & 0xFF0000)
				| ((int) (warm * 255) << 8 & 0xFF00) | ((int) (luma * 255) & 0xFF));
	}

	/**
	 * Converts a packed float color in the format produced by {@link ColorTools#ycwcma(float, float, float, float)} to an RGBA8888 int.
	 * This format of int can be used with Pixmap and in some other places in libGDX.
	 * @param packed a packed float color, as produced by {@link ColorTools#ycwcma(float, float, float, float)}
	 * @return an RGBA8888 int color
	 */
	public static int toRGBA8888(final float packed)
	{
		final int decoded = NumberUtils.floatToIntBits(packed), y = (decoded & 0xff),
				cw = ((decoded >>> 7 & 0x1fe) - 0xfe),
				cm = (((decoded >>> 15 & 0x1fe) - 0xfe) >> 1);
		return MathUtils.clamp(y + (cw * 5 >> 3) - cm, 0, 0xFF) << 24
				| MathUtils.clamp(y - (cw * 3 >> 3) + cm, 0, 0xFF) << 16
				| MathUtils.clamp(y - (cw * 3 >> 3) - cm, 0, 0xFF) << 8
				| (decoded & 0xfe000000) >>> 24 | decoded >>> 31;
	}

	/**
	 * Converts a packed float color in the format produced by {@link ColorTools#ycwcma(float, float, float, float)}
	 * to a packed float in RGBA format.
	 * This format of float can be used with the standard SpriteBatch and in some other places in libGDX.
	 * @param packed a packed float color, as produced by {@link ColorTools#ycwcma(float, float, float, float)}
	 * @return a packed float color as RGBA
	 */
	public static float toRGBA(final float packed)
	{
		final int decoded = NumberUtils.floatToIntBits(packed), y = (decoded & 0xff),
				cw = ((decoded >>> 7 & 0x1fe) - 0xfe),
				cm = (((decoded >>> 15 & 0x1fe) - 0xfe) >> 1);
		return NumberUtils.intBitsToFloat(MathUtils.clamp(y + (cw * 5 >> 3) - cm, 0, 0xFF)
				| MathUtils.clamp(y - (cw * 3 >> 3) + cm, 0, 0xFF) << 8
				| MathUtils.clamp(y - (cw * 3 >> 3) - cm, 0, 0xFF) << 16
				| (decoded & 0xfe000000));
	}

	/**
	 * Takes a color encoded as an RGBA8888 int and converts to a packed float in the YCwCmA this uses.
	 * @param rgba an int with the channels (in order) red, green, blue, alpha; should have 8 bits per channel
	 * @return a packed float as YCwCmA, which this class can use
	 */
	public static float fromRGBA8888(final int rgba) {
		return NumberUtils.intBitsToFloat(((rgba >>> 24) * 3 + (rgba >>> 16 & 0xFF) * 4 + (rgba >>> 8 & 0xFF) >> 3)
				| (0xFF + (rgba >>> 24) - (rgba >>> 8 & 0xFF) & 0x1FE) << 7
				| (0xFF + (rgba >>> 16 & 0xFF) - (rgba >>> 8 & 0xFF) & 0x1FE) << 15
				| (rgba & 0xFE) << 24);
	}

	/**
	 * Takes a color encoded as an RGBA8888 packed float and converts to a packed float in the YCwCmA this uses.
	 * @param packed a packed float in RGBA8888 format, with A in the MSB and R in the LSB
	 * @return a packed float as YCwCmA, which this class can use
	 */
	public static float fromRGBA(final float packed) {
		final int rgba = NumberUtils.floatToIntBits(packed);
		return NumberUtils.intBitsToFloat(((rgba & 0xFF) * 3 + (rgba >>> 8 & 0xFF) * 4 + (rgba >>> 16 & 0xFF) >> 3)
				| (0xFF + (rgba & 0xFF) - (rgba >>> 16 & 0xFF) & 0x1FE) << 7
				| (0xFF + (rgba >>> 8 & 0xFF) - (rgba >>> 16 & 0xFF) & 0x1FE) << 15
				| (rgba >>> 24 & 0xFE) << 24);
	}

	/**
	 * Takes a libGDX Color that uses RGBA8888 channels and converts to a packed float in the YCwCmA this uses.
	 * @param color a libGDX RGBA8888 Color
	 * @return a packed float as YCwCmA, which this class can use
	 */
	public static float fromColor(final Color color) {
		return NumberUtils.intBitsToFloat((int) (255 * (color.r * 0x3p-3f + color.g * 0x4p-3f + color.b * 0x1p-3f)) & 0xFF
						| (int)((color.r - color.b + 1f) * 127.5f) << 8 & 0xFF00
						| (int)((color.g - color.b + 1f) * 127.5f) << 16 & 0xFF0000
						| ((int)(color.a * 255f) << 24 & 0xFE000000));
	}

	/**
	 * Takes RGBA components from 0.0 to 1.0 each and converts to a packed float in the YCwCmA this uses.
	 * @param r red, from 0.0 to 1.0 (both inclusive)
	 * @param g green, from 0.0 to 1.0 (both inclusive)
	 * @param b blue, from 0.0 to 1.0 (both inclusive)
	 * @param a alpha, from 0.0 to 1.0 (both inclusive)
	 * @return a packed float as YCwCmA, which this class can use
	 */
	public static float fromRGBA(final float r, final float g, final float b, final float a) {
		return NumberUtils.intBitsToFloat((int) (255 * (r * 0x3p-3f + g * 0x4p-3f + b * 0x1p-3f)) & 0xFF
						| (int)((r - b + 1f) * 127.5f) << 8 & 0xFF00
						| (int)((g - b + 1f) * 127.5f) << 16 & 0xFF0000
						| ((int)(a * 255f) << 24 & 0xFE000000));
	}

	/**
	 * Gets the red channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #ycwcma(float, float, float, float)}
	 * @return an int from 0 to 255, inclusive, representing the red channel value of the given encoded color
	 */
	public static int redInt(final float encoded)
	{
		final int decoded = NumberUtils.floatToIntBits(encoded);
		return (decoded & 0xff) + (((decoded >>> 7 & 0x1fe) - 0xfe) * 5 >>> 4) - (((decoded >>> 15 & 0x1fe) - 0xfe) >>> 2);
	}

	/**
	 * Gets the green channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #ycwcma(float, float, float, float)}
	 * @return an int from 0 to 255, inclusive, representing the green channel value of the given encoded color
	 */
	public static int greenInt(final float encoded)
	{
		final int decoded = NumberUtils.floatToIntBits(encoded);
		return (decoded & 0xff) - (((decoded >>> 7 & 0x1fe) - 0xfe) * 3 >> 4) + (((decoded >>> 15 & 0x1fe) - 0xfe) >> 2);
	}

	/**
	 * Gets the blue channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #ycwcma(float, float, float, float)}
	 * @return an int from 0 to 255, inclusive, representing the blue channel value of the given encoded color
	 */
	public static int blueInt(final float encoded)
	{
		final int decoded = NumberUtils.floatToIntBits(encoded);
		return (decoded & 0xff) - (((decoded >>> 7 & 0x1fe) - 0xfe) * 3 >> 4) - (((decoded >>> 15 & 0x1fe) - 0xfe) >> 2);
	}

	/**
	 * Gets the alpha channel value of the given encoded color, as an even int ranging from 0 to 254, inclusive. Because
	 * of how alpha is stored in libGDX, no odd-number values are possible for alpha.
	 * @param encoded a color as a packed float that can be obtained by {@link #ycwcma(float, float, float, float)}
	 * @return an even int from 0 to 254, inclusive, representing the alpha channel value of the given encoded color
	 */
	public static int alphaInt(final float encoded)
	{
		return (NumberUtils.floatToIntBits(encoded) & 0xfe000000) >>> 24;
	}

	/**
	 * Gets the red channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #ycwcma(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the red channel value of the given encoded color
	 */
	public static float red(final float encoded)
	{
		final int decoded = NumberUtils.floatToIntBits(encoded);
		return MathUtils.clamp((decoded & 0xff) * 0x1.010102p-8f + ((decoded >>> 8 & 0xff) - 127.5f) * (0x1.414142p-9f) - ((decoded >>> 16 & 0xff) - 127.5f) * 0x1.010102p-9f, 0f, 1f);
	}

	/**
	 * Gets the green channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #ycwcma(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the green channel value of the given encoded color
	 */
	public static float green(final float encoded)
	{
		final int decoded = NumberUtils.floatToIntBits(encoded);
		return MathUtils.clamp((decoded & 0xff) * 0x1.010102p-8f - (((decoded >>> 8 & 0xff) - 127.5f) * 0x1.818184p-10f) + ((decoded >>> 16 & 0xff) - 127.5f) * 0x1.010102p-9f, 0f, 1f);
	}

	/**
	 * Gets the blue channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #ycwcma(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the blue channel value of the given encoded color
	 */
	public static float blue(final float encoded)
	{
		final int decoded = NumberUtils.floatToIntBits(encoded);
		return MathUtils.clamp((decoded & 0xff) * 0x1.010102p-8f - (((decoded >>> 8 & 0xff) - 127.5f) * 0x1.818184p-10f) - ((decoded >>> 16 & 0xff) - 127.5f) * 0x1.010102p-9f, 0f, 1f);
	}

	/**
	 * Gets the alpha channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #ycwcma(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the alpha channel value of the given encoded color
	 */
	public static float alpha(final float encoded)
	{
		return ((NumberUtils.floatToIntBits(encoded) & 0xfe000000) >>> 24) * 0.003937008f;
	}


	/**
	 * Gets a color as a YCwCmA packed float given floats representing hue, saturation, lightness, and opacity.
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
	 * @param encoded a color as a packed float that can be obtained by {@link #ycwcma(float, float, float, float)}
	 * @return the saturation of the color from 0.0 (a grayscale color; inclusive) to 1.0 (a bright color, inclusive)
	 */
	public static float saturation(final float encoded) {
		final int decoded = NumberUtils.floatToIntBits(encoded), lu = (decoded & 0xff),
				cw = ((decoded >>> 7 & 0x1fe) - 0xfe),
				cm = (((decoded >>> 15 & 0x1fe) - 0xfe) >> 1);
		final float r = MathUtils.clamp(lu + (cw * 5 >> 3) - cm, 0, 0xFF) * 0x1.010102p-8f;
		final float g = MathUtils.clamp(lu - (cw * 3 >> 3) + cm, 0, 0xFF) * 0x1.010102p-8f;
		final float b =  MathUtils.clamp(lu - (cw * 3 >> 3) - cm, 0, 0xFF) * 0x1.010102p-8f;
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
		float l = x * (1f - 0.5f * d / (x + 1e-10f));
		return (x - l) / (Math.min(l, 1f - l) + 1e-10f);
	}

	public static float lightness(final float encoded) {
		final int decoded = NumberUtils.floatToIntBits(encoded), lu = (decoded & 0xff),
				cw = ((decoded >>> 7 & 0x1fe) - 0xfe),
				cm = (((decoded >>> 15 & 0x1fe) - 0xfe) >> 1);
		final float r = MathUtils.clamp(lu + (cw * 5 >> 3) - cm, 0, 0xFF) * 0x1.010102p-8f;
		final float g = MathUtils.clamp(lu - (cw * 3 >> 3) + cm, 0, 0xFF) * 0x1.010102p-8f;
		final float b =  MathUtils.clamp(lu - (cw * 3 >> 3) - cm, 0, 0xFF) * 0x1.010102p-8f;
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
	 * @param encoded a color as a packed float that can be obtained by {@link #ycwcma(float, float, float, float)}
	 * @return The hue of the color from 0.0 (red, inclusive) towards orange, then yellow, and
	 * eventually to purple before looping back to almost the same red (1.0, exclusive)
	 */
	public static float hue(final float encoded) {
		final int decoded = NumberUtils.floatToIntBits(encoded), lu = (decoded & 0xff),
				cw = ((decoded >>> 7 & 0x1fe) - 0xfe),
				cm = (((decoded >>> 15 & 0x1fe) - 0xfe) >> 1);
		final float r = MathUtils.clamp(lu + (cw * 5 >> 3) - cm, 0, 0xFF) * 0x1.010102p-8f;
		final float g = MathUtils.clamp(lu - (cw * 3 >> 3) + cm, 0, 0xFF) * 0x1.010102p-8f;
		final float b =  MathUtils.clamp(lu - (cw * 3 >> 3) - cm, 0, 0xFF) * 0x1.010102p-8f;
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
		return (NumberUtils.floatToIntBits(encoded) & 0xff) * 0x1.010102p-8f;
//        final int bits = NumberUtils.floatToIntBits(encoded);
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
	 * @param encoded a color encoded as a packed float, as by {@link #ycwcma(float, float, float, float)}
	 * @return the chroma warm as a float from 0f to 1f
	 */
	public static float chromaWarm(final float encoded)
	{
		return ((NumberUtils.floatToIntBits(encoded) >>> 8 & 0xff)) * 0x1.010102p-8f;
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
	 * @param encoded a color encoded as a packed float, as by {@link #ycwcma(float, float, float, float)}
	 * @return the chroma mild as a float from 0f to 1f
	 */
	public static float chromaMild(final float encoded)
	{
		return ((NumberUtils.floatToIntBits(encoded) >>> 16 & 0xff)) * 0x1.010102p-8f;
	}

	/**
	 * Gets a variation on the packed float color basis as another packed float that has its hue, saturation, value, and
	 * opacity adjusted by the specified amounts. Takes floats representing the amounts of change to apply to hue,
	 * saturation, value, and opacity; these can be between -1f and 1f. Returns a float that can be used as a packed or
	 * encoded color with methods like {@link com.badlogic.gdx.graphics.g2d.Batch#setPackedColor(float)}. The float is
	 * likely to be different than the result of {@link #ycwcma(float, float, float, float)} unless hue saturation,
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
		final int e = NumberUtils.floatToIntBits(basis);
		value = MathUtils.clamp(value + (e & 0xff) * 0x1.010102p-8f, 0f, 1f);
		opacity = MathUtils.clamp(opacity + (e >>> 24 & 0xfe) * 0x1.020408p-8f, 0f, 1f);
		if (value <= 0.001f)
			return NumberUtils.intBitsToFloat((((int) (opacity * 255f) << 24) & 0xFE000000) | 0x7F7F00);
		final int lu = (e & 0xff);
		final int cw = ((e >>> 7 & 0x1fe) - 0xfe);
		final int cm = (((e >>> 15 & 0x1fe) - 0xfe) >> 1);
		final float r = MathUtils.clamp(lu + (cw * 5 >> 3) - cm, 0, 0xFF) * 0x1.010102p-8f;
		final float g = MathUtils.clamp(lu - (cw * 3 >> 3) + cm, 0, 0xFF) * 0x1.010102p-8f;
		final float b = MathUtils.clamp(lu - (cw * 3 >> 3) - cm, 0, 0xFF) * 0x1.010102p-8f;
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
		float l = x * (1f - 0.5f * d / (x + 1e-10f));
		float hue2 = Math.abs(z + (w - y) / (6f * d + 1e-10f));
		float sat2 = (x - l) / (Math.min(l, 1f - l) + 1e-10f);
		return fromRGBA(FloatColors.hsl2rgb(hue2 + hue + 1 - (int)(hue2 + hue + 1), MathUtils.clamp(saturation + sat2, 0f, 1f), value, opacity));
	}

	/**
	 * Interpolates from the packed float color start towards white by change. While change should be between 0f (return
	 * start as-is) and 1f (return white), start should be a packed color, as from
	 * {@link #ycwcma(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
	 * is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp towards
	 * white. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and both chroma of start as-is.
	 * @see #darken(float, float) the counterpart method that darkens a float color
	 * @param start the starting color as a packed float
	 * @param change how much to go from start toward white, as a float between 0 and 1; higher means closer to white
	 * @return a packed float that represents a color between start and white
	 */
	public static float lighten(final float start, final float change) {
		final int s = NumberUtils.floatToIntBits(start), luma = s & 0xFF, other = s & 0xFEFFFF00;
		return NumberUtils.intBitsToFloat(((int) (luma + (0xFF - luma) * change) & 0xFF) | other);
	}

	/**
	 * Interpolates from the packed float color start towards black by change. While change should be between 0f (return
	 * start as-is) and 1f (return black), start should be a packed color, as from
	 * {@link #ycwcma(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
	 * is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp towards
	 * black. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and both chroma of start as-is.
	 * @see #lighten(float, float) the counterpart method that lightens a float color
	 * @param start the starting color as a packed float
	 * @param change how much to go from start toward black, as a float between 0 and 1; higher means closer to black
	 * @return a packed float that represents a color between start and black
	 */
	public static float darken(final float start, final float change) {
		final int s = NumberUtils.floatToIntBits(start), luma = s & 0xFF, other = s & 0xFEFFFF00;
		return NumberUtils.intBitsToFloat(((int) (luma * (1f - change)) & 0xFF) | other);
	}

	/**
	 * Interpolates from the packed float color start towards a warmer color (yellow to red) by change. While change
	 * should be between 0f (returnstart as-is) and 1f (return fully warmed), start should be a packed color, as from
	 * {@link #ycwcma(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors,
	 * and is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp towards
	 * a warmer color. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and luma of start
	 * as-is.
	 * @see #cool(float, float) the counterpart method that cools a float color
	 * @param start the starting color as a packed float
	 * @param change how much to warm start, as a float between 0 and 1; higher means a warmer result
	 * @return a packed float that represents a color between start and a warmer color
	 */
	public static float warm(final float start, final float change) {
		final int s = NumberUtils.floatToIntBits(start), warmth = s >>> 8 & 0xFF, other = s & 0xFEFF00FF;
		return NumberUtils.intBitsToFloat(((int) (warmth + (0xFF - warmth) * change) << 8 & 0xFF) | other);
	}

	/**
	 * Interpolates from the packed float color start towards a cooler color (green to blue) by change. While change
	 * should be between 0f (return start as-is) and 1f (return fully cooled), start should be a packed color, as from
	 * {@link #ycwcma(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
	 * is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp towards
	 * a cooler color. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and luma of start
	 * as-is.
	 * @see #warm(float, float) the counterpart method that warms a float color
	 * @param start the starting color as a packed float
	 * @param change how much to cool start, as a float between 0 and 1; higher means a cooler result
	 * @return a packed float that represents a color between start and a cooler color
	 */
	public static float cool(final float start, final float change) {
		final int s = NumberUtils.floatToIntBits(start), warmth = s >>> 8 & 0xFF, other = s & 0xFEFF00FF;
		return NumberUtils.intBitsToFloat(((int) (warmth * (1f - change)) & 0xFF) << 8 | other);
	}

	/**
	 * Interpolates from the packed float color start towards a milder color (between green and yellow) by change. While
	 * change should be between 0f (return start as-is) and 1f (return fully mild), start should be a packed color, as
	 * from {@link #ycwcma(float, float, float, float)}. This is a good way to reduce allocations of temporary
	 * Colors, and is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp
	 * towards a milder color. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and luma of
	 * start as-is.
	 * @see #strengthen(float, float) the counterpart method that makes a float color more bold
	 * @param start the starting color as a packed float
	 * @param change how much to change start to a milder color, as a float between 0 and 1; higher means a milder result
	 * @return a packed float that represents a color between start and a milder color
	 */
	public static float weaken(final float start, final float change) {
		final int s = NumberUtils.floatToIntBits(start), warmth = s >>> 8 & 0xFF, other = s & 0xFEFF00FF;
		return NumberUtils.intBitsToFloat(((int) (warmth + (0xFF - warmth) * change) << 8 & 0xFF) | other);
	}

	/**
	 * Interpolates from the packed float color start towards a bolder color (between blue and red) by change. While
	 * change should be between 0f (return start as-is) and 1f (return fully cooled), start should be a packed color, as
	 * from {@link #ycwcma(float, float, float, float)}. This is a good way to reduce allocations of temporary
	 * Colors, and is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp
	 * towards a bolder color. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and luma of
	 * start as-is.
	 * @see #weaken(float, float) the counterpart method that makes a float color more mild
	 * @param start the starting color as a packed float
	 * @param change how much to change start to a bolder color, as a float between 0 and 1; higher means a bolder result
	 * @return a packed float that represents a color between start and a bolder color
	 */
	public static float strengthen(final float start, final float change) {
		final int s = NumberUtils.floatToIntBits(start), warmth = s >>> 8 & 0xFF, other = s & 0xFEFF00FF;
		return NumberUtils.intBitsToFloat(((int) (warmth * (1f - change)) & 0xFF) << 8 | other);
	}

	/**
	 * Interpolates from the packed float color start towards that color made opaque by change. While change should be
	 * between 0f (return start as-is) and 1f (return start with full alpha), start should be a packed color, as from
	 * {@link #ycwcma(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
	 * is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp towards
	 * transparent. This won't change the luma, chroma warm, or chroma mild of the color.
	 * @see #fade(float, float) the counterpart method that makes a float color more translucent
	 * @param start the starting color as a packed float
	 * @param change how much to go from start toward opaque, as a float between 0 and 1; higher means closer to opaque
	 * @return a packed float that represents a color between start and its opaque version
	 */
	public static float blot(final float start, final float change) {
		final int s = NumberUtils.floatToIntBits(start), opacity = s >>> 24 & 0xFE, other = s & 0x00FFFFFF;
		return NumberUtils.intBitsToFloat(((int) (opacity + (0xFE - opacity) * change) & 0xFE) << 24 | other);
	}

	/**
	 * Interpolates from the packed float color start towards transparent by change. While change should be between 0
	 * (return start as-is) and 1f (return the color with 0 alpha), start should be a packed color, as from
	 * {@link #ycwcma(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors,
	 * and is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp towards
	 * transparent. This won't change the luma, chroma warm, or chroma mild of the color.
	 * @see #blot(float, float) the counterpart method that makes a float color more opaque
	 * @param start the starting color as a packed float
	 * @param change how much to go from start toward transparent, as a float between 0 and 1; higher means closer to transparent
	 * @return a packed float that represents a color between start and transparent
	 */
	public static float fade(final float start, final float change) {
		final int s = NumberUtils.floatToIntBits(start), opacity = s & 0xFE, other = s & 0x00FFFFFF;
		return NumberUtils.intBitsToFloat(((int) (opacity * (1f - change)) & 0xFE) << 24 | other);
	}

	/**
	 * Given a packed float YCwCmA color {@code mainColor} and another YCwCmA color that it should be made to contrast
	 * with, gets a packed float YCwCmA color with roughly inverted luma but the same chromatic channels and opacity (Cw
	 * and Cm are likely to be clamped if the result gets close to white or black). This won't ever produce black or
	 * other very dark colors, and also has a gap in the range it produces for luma values between 0.5 and 0.55. That
	 * allows most of the colors this method produces to contrast well as a foreground when displayed on a background of
	 * {@code contrastingColor}, or vice versa. This will leave the luma unchanged if the chromatic channels of the
	 * contrastingColor and those of the mainColor are already very different. This has nothing to do with the contrast
	 * channel of the tweak in ColorfulBatch; where that part of the tweak can make too-similar lightness values further
	 * apart by just a little, this makes a modification on {@code mainColor} to maximize its lightness difference from
	 * {@code contrastingColor} without losing its other qualities.
	 * @param mainColor a packed float color, as produced by {@link #ycwcma(float, float, float, float)}; this is the color that will be adjusted
	 * @param contrastingColor a packed float color, as produced by {@link #ycwcma(float, float, float, float)}; the adjusted mainColor will contrast with this
	 * @return a different packed float color, based on mainColor but with potentially very different lightness
	 */
	public static float inverseLuma(final float mainColor, final float contrastingColor)
	{
		final int bits = NumberUtils.floatToIntBits(mainColor),
				contrastBits = NumberUtils.floatToIntBits(contrastingColor),
				luma = (bits & 0xff),
				warm = (bits >>> 8 & 0xff),
				mild = (bits >>> 16 & 0xff),
				cLuma = (contrastBits & 0xff),
				cWarm = (contrastBits >>> 8 & 0xff),
				cMild = (contrastBits >>> 16 & 0xff);
		if((warm - cWarm) * (warm - cWarm) + (mild - cMild) * (mild - cMild) >= 0x10000)
			return mainColor;
		return ycwcma(cLuma < 128 ? luma * (0.45f / 255f) + 0.55f : 0.5f - luma * (0.45f / 255f), warm, mild, 0x1.010102p-8f * (bits >>> 24));
	}

	/**
	 * Makes the additive YCwCmA color stored in {@code color} cause less of a change when used as a tint, as if it were
	 * mixed with neutral gray. When {@code fraction} is 1.0, this returns color unchanged; when fraction is 0.0, it
	 * returns {@link Palette#GRAY}, and when it is in-between 0.0 and 1.0 it returns something between the two. This is
	 * meant for things like area of effect abilities that make smaller color changes toward their periphery.
	 * @param color a color that should have its tinting effect potentially weakened
	 * @param fraction how much of {@code color} should be kept, from 0.0 to 1.0
	 * @return a YCwCmA float color between gray and {@code color}
	 */
	public static float lessenChange(final float color, float fraction) {
		final int e = NumberUtils.floatToIntBits(color),
				ys = 0x7F, cws = 0x7F, cms = 0x7F, as = 0xFE,
				ye = (e & 0xFF), cwe = (e >>> 8) & 0xFF, cme = (e >>> 16) & 0xFF, ae = e >>> 24 & 0xFE;
		return NumberUtils.intBitsToFloat(((int) (ys + fraction * (ye - ys)) & 0xFF)
				| (((int) (cws + fraction * (cwe - cws)) & 0xFF) << 8)
				| (((int) (cms + fraction * (cme - cms)) & 0xFF) << 16)
				| (((int) (as + fraction * (ae - as)) & 0xFE) << 24));
	}
}
