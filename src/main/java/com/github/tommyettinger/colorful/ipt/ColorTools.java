package com.github.tommyettinger.colorful.ipt;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.NumberUtils;
import com.github.tommyettinger.colorful.FloatColors;
import com.github.tommyettinger.colorful.Shaders;
import com.github.tommyettinger.colorful.ycwcm.Palette;

/**
 * Contains code for manipulating colors as {@code int}, packed {@code float}, and {@link Color} values in the IPT
 * color space.
 */
public class ColorTools {
	/**
	 * Gets a packed float representation of a color given as 4 float components, here, I (intensity or lightness), P
	 * (protan, a chromatic component ranging from greenish to reddish), T (tritan, a chromatic component ranging from
	 * bluish to yellowish), and A (alpha or opacity). As long as you use a batch with {@link Shaders#fragmentShaderIPT}
	 * as its shader, colors passed with {@link com.badlogic.gdx.graphics.g2d.Batch#setPackedColor(float)} will be
	 * interpreted as IPT. Intensity should be between 0 and 1, inclusive, with 0 used for very dark colors (almost only
	 * black), and 1 used for very light colors (almost only white). Protan and tritan range from 0.0 to 1.0, with
	 * grayscale results when both are about 0.5. There's some aesthetic value in changing just one chroma value. When
	 * protan is high and tritan is low, the color is more purple/magenta, when both are low it is more bluish, when
	 * tritan is high and protan is low, the color tends to be greenish, and when both are high it tends to be orange.
	 * When warm and mild are both near 0.5f, the color is closer to gray.  Alpha is the multiplicative opacity of the
	 * color, and acts like RGBA's alpha.
	 * <br>
	 * This method bit-masks the resulting color's byte values, so any values can technically be given to this as luma,
	 * warm, and mild, but they will only be reversible from the returned float color to the original Y, Cw, and Cm
	 * values if the original values were in the range that {@link #protan(float)}, {@link #tritan(float)}, and
	 * {@link #intensity(float)} return.
	 *
	 * @param intens       0f to 1f, intensity or I component of IPT, with 0.5f meaning "no change" and 1f brightening
	 * @param protan       0f to 1f, protan or P component of IPT, with 1f more orange, red, or magenta
	 * @param tritan       0f to 1f, tritan or T component of IPT, with 1f more green, yellow, or red
	 * @param alpha      0f to 1f, 0f makes the color transparent and 1f makes it opaque 
	 * @return a float encoding a color with the given properties
	 */
	public static float ipt(float intens, float protan, float tritan, float alpha) {
		return NumberUtils.intBitsToFloat(((int) (alpha * 255) << 24 & 0xFE000000) | ((int) (tritan * 255) << 16 & 0xFF0000)
				| ((int) (protan * 255) << 8 & 0xFF00) | ((int) (intens * 255) & 0xFF));
	}

	/**
	 * Converts a packed float color in the format produced by {@link ColorTools#ipt(float, float, float, float)} to an RGBA8888 int.
	 * This format of int can be used with Pixmap and in some other places in libGDX.
	 * @param packed a packed float color, as produced by {@link ColorTools#ipt(float, float, float, float)}
	 * @return an RGBA8888 int color
	 */
	public static int toRGBA8888(final float packed)
	{
		final int decoded = NumberUtils.floatToIntBits(packed), i = (decoded & 0xff),
				p = ((decoded >>> 7 & 0x1fe) - 0xfe),
				t = ((decoded >>> 15 & 0x1fe) - 0xfe);
		final float lPrime = i + 0.06503950f * p + 0.15391950f * t;
		final float mPrime = i - 0.07591241f * p + 0.09991275f * t;
		final float sPrime = i + 0.02174116f * p - 0.50766750f * t;
		final float l = Math.copySign((float) Math.pow(Math.abs(lPrime), 2.3256), lPrime);
		final float m = Math.copySign((float) Math.pow(Math.abs(mPrime), 2.3256), mPrime);
		final float s = Math.copySign((float) Math.pow(Math.abs(sPrime), 2.3256), sPrime);
		final int r = MathUtils.clamp((int) ((5.432622 * l - 4.679100 * m + 0.246257 * s) * 255.99999), 0, 255);
		final int g = MathUtils.clamp((int) ((-1.10517 * l + 2.311198 * m - 0.205880 * s) * 255.99999), 0, 255);
		final int b = MathUtils.clamp((int) ((0.028104 * l - 0.194660 * m + 1.166325 * s) * 255.99999), 0, 255);
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
		final int decoded = NumberUtils.floatToIntBits(packed), i = (decoded & 0xff),
				p = ((decoded >>> 7 & 0x1fe) - 0xfe),
				t = ((decoded >>> 15 & 0x1fe) - 0xfe);
		final float lPrime = i + 0.06503950f * p + 0.15391950f * t;
		final float mPrime = i - 0.07591241f * p + 0.09991275f * t;
		final float sPrime = i + 0.02174116f * p - 0.50766750f * t;
		final float l = Math.copySign((float) Math.pow(Math.abs(lPrime), 2.3256), lPrime);
		final float m = Math.copySign((float) Math.pow(Math.abs(mPrime), 2.3256), mPrime);
		final float s = Math.copySign((float) Math.pow(Math.abs(sPrime), 2.3256), sPrime);
		final int r = MathUtils.clamp((int) ((5.432622 * l - 4.679100 * m + 0.246257 * s) * 255.99999), 0, 255);
		final int g = MathUtils.clamp((int) ((-1.10517 * l + 2.311198 * m - 0.205880 * s) * 255.99999), 0, 255);
		final int b = MathUtils.clamp((int) ((0.028104 * l - 0.194660 * m + 1.166325 * s) * 255.99999), 0, 255);
		return NumberUtils.intBitsToFloat(r | g << 8 | b << 16 | (decoded & 0xfe000000));
	}
	/**
	 * Writes an IPT-format packed float color (the format produced by {@link ColorTools#ipt(float, float, float, float)})
	 * into an RGBA8888 Color as used by libGDX (called {@code editing}).
	 * @param editing a libGDX color that will be filled in-place with an RGBA conversion of {@code packed}
	 * @param packed a packed float color, as produced by {@link ColorTools#ipt(float, float, float, float)}
	 * @return an RGBA8888 int color
	 */
	public static Color toColor(Color editing, final float packed)
	{
		final int decoded = NumberUtils.floatToIntBits(packed), i = (decoded & 0xff),
				p = ((decoded >>> 7 & 0x1fe) - 0xfe),
				t = ((decoded >>> 15 & 0x1fe) - 0xfe);
		final float lPrime = i + 0.06503950f * p + 0.15391950f * t;
		final float mPrime = i - 0.07591241f * p + 0.09991275f * t;
		final float sPrime = i + 0.02174116f * p - 0.50766750f * t;
		final float l = Math.copySign((float) Math.pow(Math.abs(lPrime), 2.3256), lPrime);
		final float m = Math.copySign((float) Math.pow(Math.abs(mPrime), 2.3256), mPrime);
		final float s = Math.copySign((float) Math.pow(Math.abs(sPrime), 2.3256), sPrime);
		editing.r = (5.432622f * l - 4.679100f * m + 0.246257f * s);
		editing.g = (-1.10517f * l + 2.311198f * m - 0.205880f * s);
		editing.b = (0.028104f * l - 0.194660f * m + 1.166325f * s);
		editing.a = (decoded >>> 25) * 0x1.020408p-7f; // this is 1/127 as a float
		return editing;
	}

	/**
	 * Takes a color encoded as an RGBA8888 int and converts to a packed float in the IPT format this uses.
	 * @param rgba an int with the channels (in order) red, green, blue, alpha; should have 8 bits per channel
	 * @return a packed float as IPT, which this class can use
	 */
	public static float fromRGBA8888(final int rgba) {
		final float r = (rgba >>> 24) * 0x1.010101010101p-8f;
		final float g = (rgba >>> 16 & 0xFF) * 0x1.010101010101p-8f;
		final float b = (rgba >>> 8 & 0xFF) * 0x1.010101010101p-8f;
		final float l = (float) Math.pow(0.313921f * r + 0.639468f * g + 0.0465970f * b, 0.43f);
		final float m = (float) Math.pow(0.151693f * r + 0.748209f * g + 0.1000044f * b, 0.43f);
		final float s = (float) Math.pow(0.017700f * r + 0.109400f * g + 0.8729000f * b, 0.43f);

		return NumberUtils.intBitsToFloat(
				  (int)(102.0f * l + 102.0f * m + 51.0f * s + 0.5f)
				| (int)((852.01874f) * l - (927.7538f) * m + (75.7350f) * s + 128f)
				| (int)((136.94775f) * l + (60.72825f) * m - (197.676f) * s + 128f)
				| (rgba & 0xFE) << 24);
	}

	/**
	 * Takes a color encoded as an RGBA8888 packed float and converts to a packed float in the IPT format this uses.
	 * @param packed a packed float in RGBA8888 format, with A in the MSB and R in the LSB
	 * @return a packed float as IPT, which this class can use
	 */
	public static float fromRGBA(final float packed) {
		final int abgr = NumberUtils.floatToIntBits(packed);
		final float r = (abgr & 0xFF) * 0x1.010101010101p-8f;
		final float g = (abgr >>> 8 & 0xFF) * 0x1.010101010101p-8f;
		final float b = (abgr >>> 16 & 0xFF) * 0x1.010101010101p-8f;
		final float l = (float) Math.pow(0.313921f * r + 0.639468f * g + 0.0465970f * b, 0.43f);
		final float m = (float) Math.pow(0.151693f * r + 0.748209f * g + 0.1000044f * b, 0.43f);
		final float s = (float) Math.pow(0.017700f * r + 0.109400f * g + 0.8729000f * b, 0.43f);

		return NumberUtils.intBitsToFloat(
				(int)(102.0f * l + 102.0f * m + 51.0f * s + 0.5f)
						| (int)((852.01874f) * l - (927.7538f) * m + (75.7350f) * s + 128f)
						| (int)((136.94775f) * l + (60.72825f) * m - (197.676f) * s + 128f)
				| (abgr & 0xFE000000));
	}

	/**
	 * Takes a libGDX Color that uses RGBA8888 channels and converts to a packed float in the IPT format this uses.
	 * @param color a libGDX RGBA8888 Color
	 * @return a packed float as IPT, which this class can use
	 */
	public static float fromColor(final Color color) {
		final float l = (float) Math.pow(0.313921f * color.r + 0.639468f * color.g + 0.0465970f * color.b, 0.43f);
		final float m = (float) Math.pow(0.151693f * color.r + 0.748209f * color.g + 0.1000044f * color.b, 0.43f);
		final float s = (float) Math.pow(0.017700f * color.r + 0.109400f * color.g + 0.8729000f * color.b, 0.43f);

		return NumberUtils.intBitsToFloat(
				(int)(102.0f * l + 102.0f * m + 51.0f * s + 0.5f)
						| (int)((852.01874f) * l - (927.7538f) * m + (75.7350f) * s + 128f)
						| (int)((136.94775f) * l + (60.72825f) * m - (197.676f) * s + 128f)
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
	public static float fromRGBA(final float r, final float g, final float b, final float a) {
		final float l = (float) Math.pow(0.313921f * r + 0.639468f * g + 0.0465970f * b, 0.43f);
		final float m = (float) Math.pow(0.151693f * r + 0.748209f * g + 0.1000044f * b, 0.43f);
		final float s = (float) Math.pow(0.017700f * r + 0.109400f * g + 0.8729000f * b, 0.43f);

		return NumberUtils.intBitsToFloat(
				(int)(102.0f * l + 102.0f * m + 51.0f * s + 0.5f)
						| (int)((852.01874f) * l - (927.7538f) * m + (75.7350f) * s + 128f)
						| (int)((136.94775f) * l + (60.72825f) * m - (197.676f) * s + 128f)
						| ((int)(a * 255f) << 24 & 0xFE000000));
	}

	/**
	 * Gets the red channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #ipt(float, float, float, float)}
	 * @return an int from 0 to 255, inclusive, representing the red channel value of the given encoded color
	 */
	public static int redInt(final float encoded)
	{
		final int decoded = NumberUtils.floatToIntBits(encoded), i = (decoded & 0xff),
				p = ((decoded >>> 7 & 0x1fe) - 0xfe),
				t = ((decoded >>> 15 & 0x1fe) - 0xfe);
		final float lPrime = i + 0.06503950f * p + 0.15391950f * t;
		final float mPrime = i - 0.07591241f * p + 0.09991275f * t;
		final float sPrime = i + 0.02174116f * p - 0.50766750f * t;
		final float l = Math.copySign((float) Math.pow(Math.abs(lPrime), 2.3256), lPrime);
		final float m = Math.copySign((float) Math.pow(Math.abs(mPrime), 2.3256), mPrime);
		final float s = Math.copySign((float) Math.pow(Math.abs(sPrime), 2.3256), sPrime);
		return MathUtils.clamp((int) ((5.432622 * l - 4.679100 * m + 0.246257 * s) * 255.99999), 0, 255);
	}

	/**
	 * Gets the green channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #ipt(float, float, float, float)}
	 * @return an int from 0 to 255, inclusive, representing the green channel value of the given encoded color
	 */
	public static int greenInt(final float encoded)
	{
		final int decoded = NumberUtils.floatToIntBits(encoded), i = (decoded & 0xff),
				p = ((decoded >>> 7 & 0x1fe) - 0xfe),
				t = ((decoded >>> 15 & 0x1fe) - 0xfe);
		final float lPrime = i + 0.06503950f * p + 0.15391950f * t;
		final float mPrime = i - 0.07591241f * p + 0.09991275f * t;
		final float sPrime = i + 0.02174116f * p - 0.50766750f * t;
		final float l = Math.copySign((float) Math.pow(Math.abs(lPrime), 2.3256), lPrime);
		final float m = Math.copySign((float) Math.pow(Math.abs(mPrime), 2.3256), mPrime);
		final float s = Math.copySign((float) Math.pow(Math.abs(sPrime), 2.3256), sPrime);
		return MathUtils.clamp((int) ((-1.10517 * l + 2.311198 * m - 0.205880 * s) * 255.99999), 0, 255); 
	}

	/**
	 * Gets the blue channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #ipt(float, float, float, float)}
	 * @return an int from 0 to 255, inclusive, representing the blue channel value of the given encoded color
	 */
	public static int blueInt(final float encoded)
	{
		final int decoded = NumberUtils.floatToIntBits(encoded), i = (decoded & 0xff),
				p = ((decoded >>> 7 & 0x1fe) - 0xfe),
				t = ((decoded >>> 15 & 0x1fe) - 0xfe);
		final float lPrime = i + 0.06503950f * p + 0.15391950f * t;
		final float mPrime = i - 0.07591241f * p + 0.09991275f * t;
		final float sPrime = i + 0.02174116f * p - 0.50766750f * t;
		final float l = Math.copySign((float) Math.pow(Math.abs(lPrime), 2.3256), lPrime);
		final float m = Math.copySign((float) Math.pow(Math.abs(mPrime), 2.3256), mPrime);
		final float s = Math.copySign((float) Math.pow(Math.abs(sPrime), 2.3256), sPrime);
		return MathUtils.clamp((int) ((0.028104 * l - 0.194660 * m + 1.166325 * s) * 255.99999), 0, 255);
	}

	/**
	 * Gets the alpha channel value of the given encoded color, as an even int ranging from 0 to 254, inclusive. Because
	 * of how alpha is stored in libGDX, no odd-number values are possible for alpha.
	 * @param encoded a color as a packed float that can be obtained by {@link #ipt(float, float, float, float)}
	 * @return an even int from 0 to 254, inclusive, representing the alpha channel value of the given encoded color
	 */
	public static int alphaInt(final float encoded)
	{
		return (NumberUtils.floatToIntBits(encoded) & 0xfe000000) >>> 24;
	}

	/**
	 * Gets the red channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #ipt(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the red channel value of the given encoded color
	 */
	public static float red(final float encoded)
	{
		final int decoded = NumberUtils.floatToIntBits(encoded), i = (decoded & 0xff),
				p = ((decoded >>> 7 & 0x1fe) - 0xfe),
				t = ((decoded >>> 15 & 0x1fe) - 0xfe);
		final float lPrime = i + 0.06503950f * p + 0.15391950f * t;
		final float mPrime = i - 0.07591241f * p + 0.09991275f * t;
		final float sPrime = i + 0.02174116f * p - 0.50766750f * t;
		final float l = Math.copySign((float) Math.pow(Math.abs(lPrime), 2.3256), lPrime);
		final float m = Math.copySign((float) Math.pow(Math.abs(mPrime), 2.3256), mPrime);
		final float s = Math.copySign((float) Math.pow(Math.abs(sPrime), 2.3256), sPrime);
		return (5.432622f * l - 4.679100f * m + 0.246257f * s);
	}

	/**
	 * Gets the green channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #ipt(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the green channel value of the given encoded color
	 */
	public static float green(final float encoded)
	{
		final int decoded = NumberUtils.floatToIntBits(encoded), i = (decoded & 0xff),
				p = ((decoded >>> 7 & 0x1fe) - 0xfe),
				t = ((decoded >>> 15 & 0x1fe) - 0xfe);
		final float lPrime = i + 0.06503950f * p + 0.15391950f * t;
		final float mPrime = i - 0.07591241f * p + 0.09991275f * t;
		final float sPrime = i + 0.02174116f * p - 0.50766750f * t;
		final float l = Math.copySign((float) Math.pow(Math.abs(lPrime), 2.3256), lPrime);
		final float m = Math.copySign((float) Math.pow(Math.abs(mPrime), 2.3256), mPrime);
		final float s = Math.copySign((float) Math.pow(Math.abs(sPrime), 2.3256), sPrime);
		return (-1.10517f * l + 2.311198f * m - 0.205880f * s);
	}

	/**
	 * Gets the blue channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #ipt(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the blue channel value of the given encoded color
	 */
	public static float blue(final float encoded)
	{
		final int decoded = NumberUtils.floatToIntBits(encoded), i = (decoded & 0xff),
				p = ((decoded >>> 7 & 0x1fe) - 0xfe),
				t = ((decoded >>> 15 & 0x1fe) - 0xfe);
		final float lPrime = i + 0.06503950f * p + 0.15391950f * t;
		final float mPrime = i - 0.07591241f * p + 0.09991275f * t;
		final float sPrime = i + 0.02174116f * p - 0.50766750f * t;
		final float l = Math.copySign((float) Math.pow(Math.abs(lPrime), 2.3256), lPrime);
		final float m = Math.copySign((float) Math.pow(Math.abs(mPrime), 2.3256), mPrime);
		final float s = Math.copySign((float) Math.pow(Math.abs(sPrime), 2.3256), sPrime);
		return (0.028104f * l - 0.194660f * m + 1.166325f * s);
	}

	/**
	 * Gets the alpha channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #ipt(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the alpha channel value of the given encoded color
	 */
	public static float alpha(final float encoded)
	{
		return ((NumberUtils.floatToIntBits(encoded) & 0xfe000000) >>> 24) * 0x1.020408p-8f;
	}

	/**
	 * Gets the saturation of the given encoded color, as a float ranging from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #ipt(float, float, float, float)}
	 * @return the saturation of the color from 0.0 (a grayscale color; inclusive) to 1.0 (a bright color, inclusive)
	 */
	public static float saturation(final float encoded) {
		final int decoded = NumberUtils.floatToIntBits(encoded), i = (decoded & 0xff),
				p = ((decoded >>> 7 & 0x1fe) - 0xfe),
				t = ((decoded >>> 15 & 0x1fe) - 0xfe);
		final float lPrime = i + 0.06503950f * p + 0.15391950f * t;
		final float mPrime = i - 0.07591241f * p + 0.09991275f * t;
		final float sPrime = i + 0.02174116f * p - 0.50766750f * t;
		final float l = Math.copySign((float) Math.pow(Math.abs(lPrime), 2.3256), lPrime);
		final float m = Math.copySign((float) Math.pow(Math.abs(mPrime), 2.3256), mPrime);
		final float s = Math.copySign((float) Math.pow(Math.abs(sPrime), 2.3256), sPrime);
		final float r = (5.432622f * l - 4.679100f * m + 0.246257f * s);
		final float g = (-1.10517f * l + 2.311198f * m - 0.205880f * s);
		final float b = (0.028104f * l - 0.194660f * m + 1.166325f * s);
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
		final int decoded = NumberUtils.floatToIntBits(encoded), i = (decoded & 0xff),
				p = ((decoded >>> 7 & 0x1fe) - 0xfe),
				t = ((decoded >>> 15 & 0x1fe) - 0xfe);
		final float lPrime = i + 0.06503950f * p + 0.15391950f * t;
		final float mPrime = i - 0.07591241f * p + 0.09991275f * t;
		final float sPrime = i + 0.02174116f * p - 0.50766750f * t;
		final float l = Math.copySign((float) Math.pow(Math.abs(lPrime), 2.3256), lPrime);
		final float m = Math.copySign((float) Math.pow(Math.abs(mPrime), 2.3256), mPrime);
		final float s = Math.copySign((float) Math.pow(Math.abs(sPrime), 2.3256), sPrime);
		final float r = (5.432622f * l - 4.679100f * m + 0.246257f * s);
		final float g = (-1.10517f * l + 2.311198f * m - 0.205880f * s);
		final float b = (0.028104f * l - 0.194660f * m + 1.166325f * s);

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
		final int decoded = NumberUtils.floatToIntBits(encoded), i = (decoded & 0xff),
				p = ((decoded >>> 7 & 0x1fe) - 0xfe),
				t = ((decoded >>> 15 & 0x1fe) - 0xfe);
		final float lPrime = i + 0.06503950f * p + 0.15391950f * t;
		final float mPrime = i - 0.07591241f * p + 0.09991275f * t;
		final float sPrime = i + 0.02174116f * p - 0.50766750f * t;
		final float l = Math.copySign((float) Math.pow(Math.abs(lPrime), 2.3256), lPrime);
		final float m = Math.copySign((float) Math.pow(Math.abs(mPrime), 2.3256), mPrime);
		final float s = Math.copySign((float) Math.pow(Math.abs(sPrime), 2.3256), sPrime);
		final float r = (5.432622f * l - 4.679100f * m + 0.246257f * s);
		final float g = (-1.10517f * l + 2.311198f * m - 0.205880f * s);
		final float b = (0.028104f * l - 0.194660f * m + 1.166325f * s);
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
		return (NumberUtils.floatToIntBits(encoded) & 0xff) * 0x1.010102p-8f;
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
		return ((NumberUtils.floatToIntBits(encoded) >>> 8 & 0xff)) * 0x1.010102p-8f;
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
		return ((NumberUtils.floatToIntBits(encoded) >>> 16 & 0xff)) * 0x1.010102p-8f;
	}

	/**
	 * Gets a variation on the packed float color basis as another packed float that has its hue, saturation, value, and
	 * opacity adjusted by the specified amounts. Takes floats representing the amounts of change to apply to hue,
	 * saturation, value, and opacity; these can be between -1f and 1f. Returns a float that can be used as a packed or
	 * encoded color with methods like {@link com.badlogic.gdx.graphics.g2d.Batch#setPackedColor(float)}. The float is
	 * likely to be different than the result of {@link #ipt(float, float, float, float)} unless hue saturation,
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
	 * {@link #ipt(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
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
	 * {@link #ipt(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
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
	 * Interpolates from the packed float color start towards a warmer color (orange to magenta) by change. While change
	 * should be between 0f (return start as-is) and 1f (return fully warmed), start should be a packed color, as from
	 * {@link #ipt(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors,
	 * and is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp towards
	 * a warmer color. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and luma of start
	 * as-is.
	 * @see #protanDown(float, float) the counterpart method that cools a float color
	 * @param start the starting color as a packed float
	 * @param change how much to warm start, as a float between 0 and 1; higher means a warmer result
	 * @return a packed float that represents a color between start and a warmer color
	 */
	public static float protanUp(final float start, final float change) {
		final int s = NumberUtils.floatToIntBits(start), warmth = s >>> 8 & 0xFF, other = s & 0xFEFF00FF;
		return NumberUtils.intBitsToFloat(((int) (warmth + (0xFF - warmth) * change) << 8 & 0xFF) | other);
	}

	/**
	 * Interpolates from the packed float color start towards a cooler color (green to blue) by change. While change
	 * should be between 0f (return start as-is) and 1f (return fully cooled), start should be a packed color, as from
	 * {@link #ipt(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
	 * is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp towards
	 * a cooler color. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and luma of start
	 * as-is.
	 * @see #protanUp(float, float) the counterpart method that warms a float color
	 * @param start the starting color as a packed float
	 * @param change how much to cool start, as a float between 0 and 1; higher means a cooler result
	 * @return a packed float that represents a color between start and a cooler color
	 */
	public static float protanDown(final float start, final float change) {
		final int s = NumberUtils.floatToIntBits(start), warmth = s >>> 8 & 0xFF, other = s & 0xFEFF00FF;
		return NumberUtils.intBitsToFloat(((int) (warmth * (1f - change)) & 0xFF) << 8 | other);
	}

	/**
	 * Interpolates from the packed float color start towards a "natural" color (between green and orange) by change.
	 * While change should be between 0f (return start as-is) and 1f (return fully natural), start should be a packed color, as
	 * from {@link #ipt(float, float, float, float)}. This is a good way to reduce allocations of temporary
	 * Colors, and is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp
	 * towards a more natural color. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and luma of
	 * start as-is.
	 * @see #tritanDown(float, float) the counterpart method that makes a float color less natural
	 * @param start the starting color as a packed float
	 * @param change how much to change start to a natural color, as a float between 0 and 1; higher means a more natural result
	 * @return a packed float that represents a color between start and a more natural color
	 */
	public static float tritanUp(final float start, final float change) {
		final int s = NumberUtils.floatToIntBits(start), warmth = s >>> 8 & 0xFF, other = s & 0xFEFF00FF;
		return NumberUtils.intBitsToFloat(((int) (warmth + (0xFF - warmth) * change) << 8 & 0xFF) | other);
	}

	/**
	 * Interpolates from the packed float color start towards an "artificial" color (between blue and purple) by change.
	 * While change should be between 0f (return start as-is) and 1f (return fully artificial), start should be a packed color, as
	 * from {@link #ipt(float, float, float, float)}. This is a good way to reduce allocations of temporary
	 * Colors, and is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp
	 * towards a more artificial color. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the
	 * alpha and luma of start as-is.
	 * @see #tritanUp(float, float) the counterpart method that makes a float color less artificial
	 * @param start the starting color as a packed float
	 * @param change how much to change start to a bolder color, as a float between 0 and 1; higher means a more artificial result
	 * @return a packed float that represents a color between start and a more artificial color
	 */
	public static float tritanDown(final float start, final float change) {
		final int s = NumberUtils.floatToIntBits(start), warmth = s >>> 8 & 0xFF, other = s & 0xFEFF00FF;
		return NumberUtils.intBitsToFloat(((int) (warmth * (1f - change)) & 0xFF) << 8 | other);
	}

	/**
	 * Interpolates from the packed float color start towards that color made opaque by change. While change should be
	 * between 0f (return start as-is) and 1f (return start with full alpha), start should be a packed color, as from
	 * {@link #ipt(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
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
	 * {@link #ipt(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors,
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
	 * @param mainColor a packed float color, as produced by {@link #ipt(float, float, float, float)}; this is the color that will be adjusted
	 * @param contrastingColor a packed float color, as produced by {@link #ipt(float, float, float, float)}; the adjusted mainColor will contrast with this
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
		return ipt(cLuma < 128 ? luma * (0.45f / 255f) + 0.55f : 0.5f - luma * (0.45f / 255f), warm, mild, 0x1.010102p-8f * (bits >>> 24));
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
		final int e = NumberUtils.floatToIntBits(color),
				is = 0x80, ps = 0x80, ts = 0x80, as = 0xFE,
				ie = (e & 0xFF), pe = (e >>> 8) & 0xFF, te = (e >>> 16) & 0xFF, ae = e >>> 24 & 0xFE;
		return NumberUtils.intBitsToFloat(((int) (is + fraction * (ie - is)) & 0xFF)
				| (((int) (ps + fraction * (pe - ps)) & 0xFF) << 8)
				| (((int) (ts + fraction * (te - ts)) & 0xFF) << 16)
				| (((int) (as + fraction * (ae - as)) & 0xFE) << 24));
	}
}
