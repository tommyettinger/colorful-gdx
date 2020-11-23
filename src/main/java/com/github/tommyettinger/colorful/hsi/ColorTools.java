package com.github.tommyettinger.colorful.hsi;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.NumberUtils;
import com.github.tommyettinger.colorful.FloatColors;
import com.github.tommyettinger.colorful.TrigTools;
import com.github.tommyettinger.colorful.ycwcm.Palette;

import java.util.Random;

/**
 * Contains code for manipulating colors as {@code int}, packed {@code float}, and {@link Color} values in the HSI
 * color space.
 */
public class ColorTools {
	/**
	 * Gets a packed float representation of a color given as 4 float components, here, H (hue, which ranges from 0 to 1
	 * and wraps around back to 0), S (saturation, which has a range that depends on the intensity/lightness), I
	 * (intensity or lightness, which ranges from 0 to 1), and A (alpha or opacity, which ranges from 0 to 1). As lon
	 * as you use a batch with Shaders#fragmentShaderHSI as its shader, colors passed with
	 * {@link com.badlogic.gdx.graphics.g2d.Batch#setPackedColor(float)} will be interpreted as HSI. Intensity should be
	 * between 0 and 1, inclusive, with 0 used for very dark colors (only black), and 1 used for very light colors (only
	 * white). Hue wraps around, so 0 hue is reddish, 0.25 hue is yellowish, 0.5 hue is greenish-blue, and 0.75 hue is
	 * bluish, before it wraps back around to reddish. Saturation always starts at 0 for grayscale values, and it can
	 * extend as far as {@code Math.sin(Math.PI * intensity)}; the same saturation means approximately the same thing
	 * regardless of other factors, so 0.1 saturation is always very close to gray and 0.9 saturation, where possible,
	 * is always very bright. Alpha is the multiplicative opacity of the color, and acts like RGBA's alpha.
	 * <br>
	 * This method bit-masks the resulting color's byte values, so any values can technically be given to this as hue,
	 * saturation, and intensity, but they will only be reversible from the returned float color to the original H, S,
	 * and I values if the original values were in the range that {@link #hue(float)}, {@link #saturation(float)}, and
	 * {@link #intensity(float)} return.
	 *
	 * @param hue        0f to 1f, hue or H component of HSI, with 0f more red, 0.25f more yellow, 0.75f more blue
	 * @param saturation 0f to 1f, saturation or S component of HSI, with 0f meaning "no change" and 1f more vivid
	 * @param intensity  0f to 1f, intensity or I component of HSI, with 0.5f meaning "no change" and 1f brightening
	 * @param alpha      0f to 1f, 0f makes the color transparent and 1f makes it opaque
	 * @return a float encoding a color with the given properties
	 */
	public static float hsi(float hue, float saturation, float intensity, float alpha) {
		return NumberUtils.intBitsToFloat(((int) (alpha * 255) << 24 & 0xFE000000) | ((int) (intensity * 255) << 16 & 0xFF0000)
				| ((int) (saturation * 255) << 8 & 0xFF00) | ((int) (hue * 255) & 0xFF));
	}

	/**
	 * Converts a packed float color in the format produced by {@link ColorTools#hsi(float, float, float, float)} to an RGBA8888 int.
	 * This format of int can be used with Pixmap and in some other places in libGDX.
	 * @param packed a packed float color, as produced by {@link ColorTools#hsi(float, float, float, float)}
	 * @return an RGBA8888 int color
	 */
	public static int toRGBA8888(final float packed)
	{
		final int decoded = NumberUtils.floatToRawIntBits(packed);
		final float h = (decoded & 0xff) / 255f;
		final float s = (decoded >>> 8 & 0xff) / 255f;
		final float i = (decoded >>> 16 & 0xff) / 255f;
		final float y = TrigTools.cos_(h) * s, z = TrigTools.sin_(h) * s;
		final float crMid = 0.3481738f * y + 0.104959644f * z;
		final float crScale = (i - 0.5f + (NumberUtils.floatToRawIntBits(crMid) >>> 31)) * 0.16420607f / -crMid;
		final float mgMid = 0.122068435f * y + -0.070396f * z;
		final float mgScale = (i + 0.5f - (NumberUtils.floatToRawIntBits(mgMid) >>> 31)) * -0.16136102f / -mgMid;
		final float ybMid = 0.020876605f * y + -0.26078433f * z;
		final float ybScale = (i - 0.5f + (NumberUtils.floatToRawIntBits(ybMid) >>> 31)) * 0.16155326f / -ybMid;
		final float scale = Math.max(crScale, Math.max(mgScale, ybScale));
		final float d = 4f * Vector2.len(y * scale, z * scale) / MathUtils.sin(3.14159f * i);

		final float p = y * d;
		final float t = z * d;

		final int r = MathUtils.clamp((int) ((0.999779f * i + 1.0709400f * p + 0.324891f * t) * 256.0), 0, 255);
		final int g = MathUtils.clamp((int) ((1.000150f * i - 0.3777440f * p + 0.220439f * t) * 256.0), 0, 255);
		final int b = MathUtils.clamp((int) ((0.999769f * i + 0.0629496f * p - 0.809638f * t) * 256.0), 0, 255);
		return r << 24 | g << 16 | b << 8 | (decoded & 0xfe000000) >>> 24 | decoded >>> 31;
	}

	/**
	 * Converts a packed float color in the format produced by {@link ColorTools#hsi(float, float, float, float)}
	 * to a packed float in RGBA format.
	 * This format of float can be used with the standard SpriteBatch and in some other places in libGDX.
	 * @param packed a packed float color, as produced by {@link ColorTools#hsi(float, float, float, float)}
	 * @return a packed float color as RGBA
	 */
	public static float toRGBA(final float packed)
	{
		final int decoded = NumberUtils.floatToRawIntBits(packed);
		final float h = (decoded & 0xff) / 255f;
		final float s = (decoded >>> 8 & 0xff) / 255f;
		final float i = (decoded >>> 16 & 0xff) / 255f;
		final float y = TrigTools.cos_(h) * s, z = TrigTools.sin_(h) * s;
		final float crMid = 0.3481738f * y + 0.104959644f * z;
		final float crScale = (i - 0.5f + (NumberUtils.floatToRawIntBits(crMid) >>> 31)) * 0.16420607f / -crMid;
		final float mgMid = 0.122068435f * y + -0.070396f * z;
		final float mgScale = (i + 0.5f - (NumberUtils.floatToRawIntBits(mgMid) >>> 31)) * -0.16136102f / -mgMid;
		final float ybMid = 0.020876605f * y + -0.26078433f * z;
		final float ybScale = (i - 0.5f + (NumberUtils.floatToRawIntBits(ybMid) >>> 31)) * 0.16155326f / -ybMid;
		final float scale = Math.max(crScale, Math.max(mgScale, ybScale));
		final float d = 4f * Vector2.len(y * scale, z * scale) / MathUtils.sin(3.14159f * i);

		final float p = y * d;
		final float t = z * d;

		final int r = MathUtils.clamp((int) ((0.999779f * i + 1.0709400f * p + 0.324891f * t) * 256.0), 0, 255);
		final int g = MathUtils.clamp((int) ((1.000150f * i - 0.3777440f * p + 0.220439f * t) * 256.0), 0, 255);
		final int b = MathUtils.clamp((int) ((0.999769f * i + 0.0629496f * p - 0.809638f * t) * 256.0), 0, 255);
		return NumberUtils.intBitsToFloat(r | g << 8 | b << 16 | (decoded & 0xfe000000));
	}
	/**
	 * Writes an HSI-format packed float color (the format produced by {@link ColorTools#hsi(float, float, float, float)})
	 * into an RGBA8888 Color as used by libGDX (called {@code editing}).
	 * @param editing a libGDX color that will be filled in-place with an RGBA conversion of {@code packed}
	 * @param packed a packed float color, as produced by {@link ColorTools#hsi(float, float, float, float)}
	 * @return an RGBA8888 int color
	 */
	public static Color toColor(Color editing, final float packed)
	{
		final int decoded = NumberUtils.floatToRawIntBits(packed);
		final float h = (decoded & 0xff) / 255f;
		final float s = (decoded >>> 8 & 0xff) / 255f;
		final float i = (decoded >>> 16 & 0xff) / 255f;
		final float y = TrigTools.cos_(h) * s, z = TrigTools.sin_(h) * s;
		final float crMid = 0.3481738f * y + 0.104959644f * z;
		final float crScale = (i - 0.5f + (NumberUtils.floatToRawIntBits(crMid) >>> 31)) * 0.16420607f / -crMid;
		final float mgMid = 0.122068435f * y + -0.070396f * z;
		final float mgScale = (i + 0.5f - (NumberUtils.floatToRawIntBits(mgMid) >>> 31)) * -0.16136102f / -mgMid;
		final float ybMid = 0.020876605f * y + -0.26078433f * z;
		final float ybScale = (i - 0.5f + (NumberUtils.floatToRawIntBits(ybMid) >>> 31)) * 0.16155326f / -ybMid;
		final float scale = Math.max(crScale, Math.max(mgScale, ybScale));
		final float d = 4f * Vector2.len(y * scale, z * scale) / MathUtils.sin(3.14159f * i);

		final float p = y * d;
		final float t = z * d;

		editing.r = (0.999779f * i + 1.0709400f * p + 0.324891f * t);
		editing.g = (1.000150f * i - 0.3777440f * p + 0.220439f * t);
		editing.b = (0.999769f * i + 0.0629496f * p - 0.809638f * t);
		editing.a = (decoded >>> 25) * 0x1.020408p-7f; // this is 1/127 as a float
		return editing.clamp();
	}

	/**
	 * Takes a color encoded as an RGBA8888 int and converts to a packed float in the HSI format this uses.
	 * @param rgba an int with the channels (in order) red, green, blue, alpha; should have 8 bits per channel
	 * @return a packed float as HSI, which this class can use
	 */
	public static float fromRGBA8888(final int rgba) {
		final float r = (rgba >>> 24) * 0x1.010101010101p-8f;
		final float g = (rgba >>> 16 & 0xFF) * 0x1.010101010101p-8f;
		final float b = (rgba >>> 8 & 0xFF) * 0x1.010101010101p-8f;

		final float i = (0.189786f * r + 0.576951f * g + 0.233221f * b) - 0.5f;
		final float p = (0.669665f * r - 0.73741f * g + 0.0681367f * b) * 0.5f;
		final float t = (0.286498f * r + 0.655205f * g - 0.941748f * b) * 0.5f;

		final float crMid = 0.3481738f * p + 0.104959644f * t;
		final float crScale = (i - 0.5f + (NumberUtils.floatToRawIntBits(crMid) >>> 31)) * 0.16420607f / -crMid;
		final float mgMid = 0.122068435f * p + -0.070396f * t;
		final float mgScale = (i + 0.5f - (NumberUtils.floatToRawIntBits(mgMid) >>> 31)) * -0.16136102f / -mgMid;
		final float ybMid = 0.020876605f * p + -0.26078433f * t;
		final float ybScale = (i - 0.5f + (NumberUtils.floatToRawIntBits(ybMid) >>> 31)) * 0.16155326f / -ybMid;
		final float scale = Math.max(crScale, Math.max(mgScale, ybScale));
		final float h = TrigTools.atan2_(t, p);
		final float s = 0.25f * MathUtils.sin(3.14159f * i) / Vector2.len(p * scale, t * scale);
		return NumberUtils.intBitsToFloat(((rgba & 0xFE) << 24) | ((int) ((i+0.5f) * 255) << 16 & 0xFF0000)
				| ((int) (s * 255) << 8 & 0xFF00) | ((int) (h * 255) & 0xFF));
	}

	/**
	 * Takes a color encoded as an RGBA8888 packed float and converts to a packed float in the HSI format this uses.
	 * @param packed a packed float in RGBA8888 format, with A in the MSB and R in the LSB
	 * @return a packed float as HSI, which this class can use
	 */
	public static float fromRGBA(final float packed) {
		final int abgr = NumberUtils.floatToRawIntBits(packed);
		final float r = (abgr & 0xFF) * 0x1.010101010101p-8f;
		final float g = (abgr >>> 8 & 0xFF) * 0x1.010101010101p-8f;
		final float b = (abgr >>> 16 & 0xFF) * 0x1.010101010101p-8f;
		final float i = (0.189786f * r + 0.576951f * g + 0.233221f * b) - 0.5f;
		final float p = (0.669665f * r - 0.73741f * g + 0.0681367f * b) * 0.5f;
		final float t = (0.286498f * r + 0.655205f * g - 0.941748f * b) * 0.5f;

		final float crMid = 0.3481738f * p + 0.104959644f * t;
		final float crScale = (i - 0.5f + (NumberUtils.floatToRawIntBits(crMid) >>> 31)) * 0.16420607f / -crMid;
		final float mgMid = 0.122068435f * p + -0.070396f * t;
		final float mgScale = (i + 0.5f - (NumberUtils.floatToRawIntBits(mgMid) >>> 31)) * -0.16136102f / -mgMid;
		final float ybMid = 0.020876605f * p + -0.26078433f * t;
		final float ybScale = (i - 0.5f + (NumberUtils.floatToRawIntBits(ybMid) >>> 31)) * 0.16155326f / -ybMid;
		final float scale = Math.max(crScale, Math.max(mgScale, ybScale));
		final float h = TrigTools.atan2_(t, p);
		final float s = 0.25f * MathUtils.sin(3.14159f * i) / Vector2.len(p * scale, t * scale);
		return NumberUtils.intBitsToFloat((abgr & 0xFE000000) | ((int) ((i+0.5f) * 255) << 16 & 0xFF0000)
				| ((int) (s * 255) << 8 & 0xFF00) | ((int) (h * 255) & 0xFF));
	}

	/**
	 * Takes a libGDX Color that uses RGBA8888 channels and converts to a packed float in the HSI format this uses.
	 * @param color a libGDX RGBA8888 Color
	 * @return a packed float as HSI, which this class can use
	 */
	public static float fromColor(final Color color) {
		final float r = color.r, g = color.g, b = color.b;
		final float i = (0.189786f * r + 0.576951f * g + 0.233221f * b) - 0.5f;
		final float p = (0.669665f * r - 0.73741f * g + 0.0681367f * b) * 0.5f;
		final float t = (0.286498f * r + 0.655205f * g - 0.941748f * b) * 0.5f;

		final float crMid = 0.3481738f * p + 0.104959644f * t;
		final float crScale = (i - 0.5f + (NumberUtils.floatToRawIntBits(crMid) >>> 31)) * 0.16420607f / -crMid;
		final float mgMid = 0.122068435f * p + -0.070396f * t;
		final float mgScale = (i + 0.5f - (NumberUtils.floatToRawIntBits(mgMid) >>> 31)) * -0.16136102f / -mgMid;
		final float ybMid = 0.020876605f * p + -0.26078433f * t;
		final float ybScale = (i - 0.5f + (NumberUtils.floatToRawIntBits(ybMid) >>> 31)) * 0.16155326f / -ybMid;
		final float scale = Math.max(crScale, Math.max(mgScale, ybScale));
		final float h = TrigTools.atan2_(t, p);
		final float s = 0.25f * MathUtils.sin(3.14159f * i) / Vector2.len(p * scale, t * scale);
		return NumberUtils.intBitsToFloat(((int) (color.a * 255) << 24 & 0xFE000000) | ((int) ((i+0.5f) * 255) << 16 & 0xFF0000)
				| ((int) (s * 255) << 8 & 0xFF00) | ((int) (h * 255) & 0xFF));
	}

	/**
	 * Takes RGBA components from 0.0 to 1.0 each and converts to a packed float in the HSI format this uses.
	 * @param r red, from 0.0 to 1.0 (both inclusive)
	 * @param g green, from 0.0 to 1.0 (both inclusive)
	 * @param b blue, from 0.0 to 1.0 (both inclusive)
	 * @param a alpha, from 0.0 to 1.0 (both inclusive)
	 * @return a packed float as HSI, which this class can use
	 */
	public static float fromRGBA(final float r, final float g, final float b, final float a) {
		final float i = (0.189786f * r + 0.576951f * g + 0.233221f * b) - 0.5f;
		final float p = (0.669665f * r - 0.73741f * g + 0.0681367f * b) * 0.5f;
		final float t = (0.286498f * r + 0.655205f * g - 0.941748f * b) * 0.5f;

		final float crMid = 0.3481738f * p + 0.104959644f * t;
		final float crScale = (i - 0.5f + (NumberUtils.floatToRawIntBits(crMid) >>> 31)) * 0.16420607f / -crMid;
		final float mgMid = 0.122068435f * p + -0.070396f * t;
		final float mgScale = (i + 0.5f - (NumberUtils.floatToRawIntBits(mgMid) >>> 31)) * -0.16136102f / -mgMid;
		final float ybMid = 0.020876605f * p + -0.26078433f * t;
		final float ybScale = (i - 0.5f + (NumberUtils.floatToRawIntBits(ybMid) >>> 31)) * 0.16155326f / -ybMid;
		final float scale = Math.max(crScale, Math.max(mgScale, ybScale));
		final float h = TrigTools.atan2_(t, p);
		final float s = 0.25f * MathUtils.sin(3.14159f * i) / Vector2.len(p * scale, t * scale);
		return NumberUtils.intBitsToFloat(((int) (a * 255) << 24 & 0xFE000000) | ((int) ((i+0.5f) * 255) << 16 & 0xFF0000)
				| ((int) (s * 255) << 8 & 0xFF00) | ((int) (h * 255) & 0xFF));
	}

	/**
	 * Gets the red channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #hsi(float, float, float, float)}
	 * @return an int from 0 to 255, inclusive, representing the red channel value of the given encoded color
	 */
	public static int redInt(final float encoded)
	{
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		final float h = (decoded & 0xff) / 255f;
		final float s = (decoded >>> 8 & 0xff) / 255f;
		final float i = (decoded >>> 16 & 0xff) / 255f;
		final float y = TrigTools.cos_(h) * s, z = TrigTools.sin_(h) * s;
		final float crMid = 0.3481738f * y + 0.104959644f * z;
		final float crScale = (i - 0.5f + (NumberUtils.floatToRawIntBits(crMid) >>> 31)) * 0.16420607f / -crMid;
		final float mgMid = 0.122068435f * y + -0.070396f * z;
		final float mgScale = (i + 0.5f - (NumberUtils.floatToRawIntBits(mgMid) >>> 31)) * -0.16136102f / -mgMid;
		final float ybMid = 0.020876605f * y + -0.26078433f * z;
		final float ybScale = (i - 0.5f + (NumberUtils.floatToRawIntBits(ybMid) >>> 31)) * 0.16155326f / -ybMid;
		final float scale = Math.max(crScale, Math.max(mgScale, ybScale));
		final float d = 4f * Vector2.len(y * scale, z * scale) / MathUtils.sin(3.14159f * i);

		final float p = y * d;
		final float t = z * d;

		return MathUtils.clamp((int) ((0.999779f * i + 1.0709400f * p + 0.324891f * t) * 256.0), 0, 255);
	}

	/**
	 * Gets the green channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #hsi(float, float, float, float)}
	 * @return an int from 0 to 255, inclusive, representing the green channel value of the given encoded color
	 */
	public static int greenInt(final float encoded)
	{
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		final float h = (decoded & 0xff) / 255f;
		final float s = (decoded >>> 8 & 0xff) / 255f;
		final float i = (decoded >>> 16 & 0xff) / 255f;
		final float y = TrigTools.cos_(h) * s, z = TrigTools.sin_(h) * s;
		final float crMid = 0.3481738f * y + 0.104959644f * z;
		final float crScale = (i - 0.5f + (NumberUtils.floatToRawIntBits(crMid) >>> 31)) * 0.16420607f / -crMid;
		final float mgMid = 0.122068435f * y + -0.070396f * z;
		final float mgScale = (i + 0.5f - (NumberUtils.floatToRawIntBits(mgMid) >>> 31)) * -0.16136102f / -mgMid;
		final float ybMid = 0.020876605f * y + -0.26078433f * z;
		final float ybScale = (i - 0.5f + (NumberUtils.floatToRawIntBits(ybMid) >>> 31)) * 0.16155326f / -ybMid;
		final float scale = Math.max(crScale, Math.max(mgScale, ybScale));
		final float d = 4f * Vector2.len(y * scale, z * scale) / MathUtils.sin(3.14159f * i);

		final float p = y * d;
		final float t = z * d;

		return MathUtils.clamp((int) ((1.000150f * i - 0.3777440f * p + 0.220439f * t) * 256.0), 0, 255);
	}

	/**
	 * Gets the blue channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #hsi(float, float, float, float)}
	 * @return an int from 0 to 255, inclusive, representing the blue channel value of the given encoded color
	 */
	public static int blueInt(final float encoded)
	{
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		final float h = (decoded & 0xff) / 255f;
		final float s = (decoded >>> 8 & 0xff) / 255f;
		final float i = (decoded >>> 16 & 0xff) / 255f;
		final float y = TrigTools.cos_(h) * s, z = TrigTools.sin_(h) * s;
		final float crMid = 0.3481738f * y + 0.104959644f * z;
		final float crScale = (i - 0.5f + (NumberUtils.floatToRawIntBits(crMid) >>> 31)) * 0.16420607f / -crMid;
		final float mgMid = 0.122068435f * y + -0.070396f * z;
		final float mgScale = (i + 0.5f - (NumberUtils.floatToRawIntBits(mgMid) >>> 31)) * -0.16136102f / -mgMid;
		final float ybMid = 0.020876605f * y + -0.26078433f * z;
		final float ybScale = (i - 0.5f + (NumberUtils.floatToRawIntBits(ybMid) >>> 31)) * 0.16155326f / -ybMid;
		final float scale = Math.max(crScale, Math.max(mgScale, ybScale));
		final float d = 4f * Vector2.len(y * scale, z * scale) / MathUtils.sin(3.14159f * i);

		final float p = y * d;
		final float t = z * d;

		return MathUtils.clamp((int) ((0.999769f * i + 0.0629496f * p - 0.809638f * t) * 256.0), 0, 255);
	}

	/**
	 * Gets the alpha channel value of the given encoded color, as an even int ranging from 0 to 254, inclusive. Because
	 * of how alpha is stored in libGDX, no odd-number values are possible for alpha.
	 * @param encoded a color as a packed float that can be obtained by {@link #hsi(float, float, float, float)}
	 * @return an even int from 0 to 254, inclusive, representing the alpha channel value of the given encoded color
	 */
	public static int alphaInt(final float encoded)
	{
		return (NumberUtils.floatToRawIntBits(encoded) & 0xfe000000) >>> 24;
	}

	/**
	 * Gets the red channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #hsi(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the red channel value of the given encoded color
	 */
	public static float red(final float encoded)
	{
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		final float h = (decoded & 0xff) / 255f;
		final float s = (decoded >>> 8 & 0xff) / 255f;
		final float i = (decoded >>> 16 & 0xff) / 255f;
		final float y = TrigTools.cos_(h) * s, z = TrigTools.sin_(h) * s;
		final float crMid = 0.3481738f * y + 0.104959644f * z;
		final float crScale = (i - 0.5f + (NumberUtils.floatToRawIntBits(crMid) >>> 31)) * 0.16420607f / -crMid;
		final float mgMid = 0.122068435f * y + -0.070396f * z;
		final float mgScale = (i + 0.5f - (NumberUtils.floatToRawIntBits(mgMid) >>> 31)) * -0.16136102f / -mgMid;
		final float ybMid = 0.020876605f * y + -0.26078433f * z;
		final float ybScale = (i - 0.5f + (NumberUtils.floatToRawIntBits(ybMid) >>> 31)) * 0.16155326f / -ybMid;
		final float scale = Math.max(crScale, Math.max(mgScale, ybScale));
		final float d = 4f * Vector2.len(y * scale, z * scale) / MathUtils.sin(3.14159f * i);

		final float p = y * d;
		final float t = z * d;

		return MathUtils.clamp((0.999779f * i + 1.0709400f * p + 0.324891f * t), 0f, 1f);
	}

	/**
	 * Gets the green channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #hsi(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the green channel value of the given encoded color
	 */
	public static float green(final float encoded)
	{
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		final float h = (decoded & 0xff) / 255f;
		final float s = (decoded >>> 8 & 0xff) / 255f;
		final float i = (decoded >>> 16 & 0xff) / 255f;
		final float y = TrigTools.cos_(h) * s, z = TrigTools.sin_(h) * s;
		final float crMid = 0.3481738f * y + 0.104959644f * z;
		final float crScale = (i - 0.5f + (NumberUtils.floatToRawIntBits(crMid) >>> 31)) * 0.16420607f / -crMid;
		final float mgMid = 0.122068435f * y + -0.070396f * z;
		final float mgScale = (i + 0.5f - (NumberUtils.floatToRawIntBits(mgMid) >>> 31)) * -0.16136102f / -mgMid;
		final float ybMid = 0.020876605f * y + -0.26078433f * z;
		final float ybScale = (i - 0.5f + (NumberUtils.floatToRawIntBits(ybMid) >>> 31)) * 0.16155326f / -ybMid;
		final float scale = Math.max(crScale, Math.max(mgScale, ybScale));
		final float d = 4f * Vector2.len(y * scale, z * scale) / MathUtils.sin(3.14159f * i);

		final float p = y * d;
		final float t = z * d;

		return MathUtils.clamp((1.000150f * i - 0.3777440f * p + 0.220439f * t), 0f, 1f);
	}

	/**
	 * Gets the blue channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #hsi(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the blue channel value of the given encoded color
	 */
	public static float blue(final float encoded)
	{
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		final float h = (decoded & 0xff) / 255f;
		final float s = (decoded >>> 8 & 0xff) / 255f;
		final float i = (decoded >>> 16 & 0xff) / 255f;
		final float y = TrigTools.cos_(h) * s, z = TrigTools.sin_(h) * s;
		final float crMid = 0.3481738f * y + 0.104959644f * z;
		final float crScale = (i - 0.5f + (NumberUtils.floatToRawIntBits(crMid) >>> 31)) * 0.16420607f / -crMid;
		final float mgMid = 0.122068435f * y + -0.070396f * z;
		final float mgScale = (i + 0.5f - (NumberUtils.floatToRawIntBits(mgMid) >>> 31)) * -0.16136102f / -mgMid;
		final float ybMid = 0.020876605f * y + -0.26078433f * z;
		final float ybScale = (i - 0.5f + (NumberUtils.floatToRawIntBits(ybMid) >>> 31)) * 0.16155326f / -ybMid;
		final float scale = Math.max(crScale, Math.max(mgScale, ybScale));
		final float d = 4f * Vector2.len(y * scale, z * scale) / MathUtils.sin(3.14159f * i);

		final float p = y * d;
		final float t = z * d;

		return MathUtils.clamp((0.999769f * i + 0.0629496f * p - 0.809638f * t), 0f, 1f);
	}

	/**
	 * Gets the alpha channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #hsi(float, float, float, float)}
	 * @return a float from 0.0f to 1.0f, inclusive, representing the alpha channel value of the given encoded color
	 */
	public static float alpha(final float encoded)
	{
		return ((NumberUtils.floatToRawIntBits(encoded) & 0xfe000000) >>> 24) * 0x1.020408p-8f;
	}

	/**
	 * Gets the saturation of the given encoded color, as a float ranging from 0.0f to 1.0f, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #hsi(float, float, float, float)}
	 * @return the saturation of the color from 0.0 (a grayscale color; inclusive) to 1.0 (a bright color, inclusive)
	 */
	public static float saturation(final float encoded) {
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		return  (decoded >>> 8 & 0xff) / 255f;
	}

	/**
	 * Gets the hue of the given encoded color, as a float from 0f (inclusive, red and approaching orange if increased)
	 * to 1f (exclusive, red and approaching purple if decreased).
	 * @param encoded a color as a packed float that can be obtained by {@link #hsi(float, float, float, float)}
	 * @return The hue of the color from 0.0 (red, inclusive) towards orange, then yellow, and
	 * eventually to purple before looping back to almost the same red (1.0, exclusive)
	 */
	public static float hue(final float encoded) {
		final int decoded = NumberUtils.floatToRawIntBits(encoded);
		return  (decoded & 0xff) / 255f;
	}

	/**
	 * The "intensity" of the given packed float in HSI (or IPT) format, which is like its lightness; ranges from 0.0f
	 * to 1.0f . You can edit the intensity of a color with {@link #lighten(float, float)} and
	 * {@link #darken(float, float)}.
	 *
	 * @param encoded a color encoded as a packed float, as by {@link #hsi(float, float, float, float)}
	 * @return the intensity value as a float from 0.0f to 1.0f
	 */
	public static float intensity(final float encoded)
	{
		return (NumberUtils.floatToRawIntBits(encoded) >>> 16 & 0xff) / 255f;
	}

	/**
	 * Gets a variation on the packed float color basis as another packed float that has its hue, saturation, intensity,
	 * and opacity adjusted by the specified amounts. Takes floats representing the amounts of change to apply to hue,
	 * saturation, intensity, and opacity; these can be between -1f and 1f. Returns a float that can be used as a packed
	 * or encoded color with methods like {@link com.badlogic.gdx.graphics.g2d.Batch#setPackedColor(float)}. This won't
	 * allocate any objects.
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
	 * @param intensity  -1f to 1f, the intensity/brightness change that can be applied to the new float color
	 * @param opacity    -1f to 1f, the opacity/alpha change that can be applied to the new float color
	 * @return a float encoding a variation of basis with the given changes
	 */
	public static float toEditedFloat(float basis, float hue, float saturation, float intensity, float opacity) {
		final int e = NumberUtils.floatToRawIntBits(basis);
		intensity = MathUtils.clamp(intensity + (e >>> 16 & 0xff) * 0x1.010102p-8f, 0f, 1f);
		opacity = MathUtils.clamp(opacity + (e >>> 24 & 0xfe) * 0x1.020408p-8f, 0f, 1f); // float is 1.0/254.0
		if (intensity <= 0.001f)
			return NumberUtils.intBitsToFloat((((int) (opacity * 255f) << 24) & 0xFE000000));
		hue += (e & 0xff) * 0x1.010102p-8f + 1f; // float is 1.0/255.0
		hue -= (int)hue;
		saturation = MathUtils.clamp(saturation + (e >>> 8 & 0xff) * 0x1.010102p-8f, 0f, 1f);
		return hsi(hue, saturation, intensity, opacity);
	}

	/**
	 * Interpolates from the packed float color start towards white by change. While change should be between 0f (return
	 * start as-is) and 1f (return white), start should be a packed color, as from
	 * {@link #hsi(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
	 * is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp towards
	 * white. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and both chroma of start as-is.
	 * @see #darken(float, float) the counterpart method that darkens a float color
	 * @param start the starting color as a packed float
	 * @param change how much to go from start toward white, as a float between 0 and 1; higher means closer to white
	 * @return a packed float that represents a color between start and white
	 */
	public static float lighten(final float start, final float change) {
		final int s = NumberUtils.floatToRawIntBits(start), i = s >>> 16 & 0xFF, other = s & 0xFE00FFFF;
		return NumberUtils.intBitsToFloat(((int) (i + (0xFF - i) * change) & 0xFF) << 16 | other);
	}

	/**
	 * Interpolates from the packed float color start towards black by change. While change should be between 0f (return
	 * start as-is) and 1f (return black), start should be a packed color, as from
	 * {@link #hsi(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
	 * is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp towards
	 * black. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha and both chroma of start as-is.
	 * @see #lighten(float, float) the counterpart method that lightens a float color
	 * @param start the starting color as a packed float
	 * @param change how much to go from start toward black, as a float between 0 and 1; higher means closer to black
	 * @return a packed float that represents a color between start and black
	 */
	public static float darken(final float start, final float change) {
		final int s = NumberUtils.floatToRawIntBits(start), i = s >>> 16 & 0xFF, other = s & 0xFE00FFFF;
		return NumberUtils.intBitsToFloat(((int) (i * (1f - change)) & 0xFF) << 16 | other);
	}

	/**
	 * Changes the hue of the packed float color start, with change representing a rotation that can be negative or
	 * positive and has no strict limit, though only the fractional part will matter. If change is 0.5, or the
	 * fractional part of change is 0.5, then this makes the hue its polar opposite on the color wheel. If change is
	 * 0.0, 1.0, or any other integer value, it does not change the hue (it will have wrapped around). The rotation
	 * direction should probably be viewed as red-to-yellow-to-green-to-blue-to-red, instead of as clockwise or
	 * counterclockwise, because the colors can be laid out in either direction on as color wheel.
	 * @param start the starting color as a packed float
	 * @param change how much to rotate the hue of start, as a float
	 * @return a packed float that represents a color between start and a warmer color
	 */
	public static float hueRotate(final float start, final float change) {
		final int s = NumberUtils.floatToRawIntBits(start), p = s & 0xFF, other = s & 0xFEFFFF00;
		return NumberUtils.intBitsToFloat(((int) (p + 256 * change) << 8 & 0xFF00) | other);
	}

	/**
	 * Interpolates from the packed float color start towards a "vivid" color (away from gray) by change.
	 * While change should be between 0f (return start as-is) and 1f (return fully natural), start should be a packed
	 * color, as from {@link #hsi(float, float, float, float)}. This is a good way to reduce allocations of temporary
	 * Colors, and is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp
	 * towards a more natural color. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the
	 * alpha, hue, and intensity of start as-is.
	 * @see #saturationDown(float, float) the counterpart method that makes a float color less saturated
	 * @param start the starting color as a packed float
	 * @param change how much to change start to a saturated color, as a float between 0 and 1; higher means a more vivid result
	 * @return a packed float that represents a color between start and a more vivid color
	 */
	public static float saturationUp(final float start, final float change) {
		final int s = NumberUtils.floatToRawIntBits(start), t = s >>> 8 & 0xFF, other = s & 0xFEFF00FF;
		float limit = MathUtils.sin((s >>> 16 & 0xFF) * (3.14159f / 255f)) * 255;
		return NumberUtils.intBitsToFloat(((int) Math.min(t + (0xFF - t) * change, limit) << 8 & 0xFF00) | other);
	}

	/**
	 * Interpolates from the packed float color start towards a less saturated color (closer to gray) by change. While change
	 * should be between 0f (return start as-is) and 1f (return fully cooled), start should be a packed color, as from
	 * {@link #hsi(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
	 * is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp towards
	 * a cooler color. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the
	 * alpha, hue, and intensity of start as-is.
	 * @see #saturationUp(float, float) the counterpart method that warms a float color
	 * @param start the starting color as a packed float
	 * @param change how much to desaturate start, as a float between 0 and 1; higher means a more-gray result
	 * @return a packed float that represents a color between start and a cooler color
	 */
	public static float saturationDown(final float start, final float change) {
		final int s = NumberUtils.floatToRawIntBits(start), p = s >>> 8 & 0xFF, other = s & 0xFEFF00FF;
		return NumberUtils.intBitsToFloat(((int) (p * (1f - change)) & 0xFF) << 8 | other);
	}

	/**
	 * Interpolates from the packed float color start towards that color made opaque by change. While change should be
	 * between 0f (return start as-is) and 1f (return start with full alpha), start should be a packed color, as from
	 * {@link #hsi(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
	 * is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp towards
	 * transparent. This won't change the intensity, hue, or saturation of the color.
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
	 * {@link #hsi(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors,
	 * and is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp towards
	 * transparent. This won't change the intensity, hue, or saturation of the color.
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
	 * Given a packed float HSI color {@code mainColor} and another HSI color that it should be made to contrast with,
	 * gets a packed float HSI color with roughly inverted intensity but the same hue, saturation, and opacity (although
	 * saturation will be reduced if this gets closer to black or white). This won't ever produce black or other very
	 * dark colors, and also has a gap in the range it produces for intensity values between 0.5 and 0.55. That allows
	 * most of the colors this method produces to contrast well as a foreground when displayed on a background of
	 * {@code contrastingColor}, or vice versa. This will leave the intensity unchanged if the hue and saturation of the
	 * contrastingColor and those of the mainColor are already very different. This has nothing to do with the contrast
	 * channel of the tweak in ColorfulBatch; where that part of the tweak can make too-similar lightness values further
	 * apart by just a little, this makes a modification on {@code mainColor} to maximize its lightness difference from
	 * {@code contrastingColor} without losing its other qualities.
	 * @param mainColor a packed float color, as produced by {@link #hsi(float, float, float, float)}; this is the color that will be adjusted
	 * @param contrastingColor a packed float color, as produced by {@link #hsi(float, float, float, float)}; the adjusted mainColor will contrast with this
	 * @return a different HSI packed float color, based on mainColor but with potentially very different lightness
	 */
	public static float inverseIntensity(final float mainColor, final float contrastingColor)
	{
		final int bits = NumberUtils.floatToRawIntBits(mainColor),
				contrastBits = NumberUtils.floatToRawIntBits(contrastingColor),
				h = (bits & 0xff),
				s = (bits >>> 8 & 0xff),
				i = (bits >>> 16 & 0xff),
				ch = (contrastBits & 0xff),
				cs = (contrastBits >>> 8 & 0xff),
				ci = (contrastBits >>> 16 & 0xff);
		if(Math.abs(s - cs) * Math.abs(h - ch) >= 0x900)
			return mainColor;
		final float i2 = ci < 128 ? i * (0.45f / 255f) + 0.55f : 0.5f - i * (0.45f / 255f);
		float limit = MathUtils.sin(i2 * (3.14159f / 255f)) * 255;
		return NumberUtils.intBitsToFloat((bits & 0xFE000000) | h << 16 | (int)Math.min(s, limit) << 8 | h);
	}

	/**
	 * Makes the additive HSI color stored in {@code color} cause less of a change when used as a tint, as if it were
	 * mixed with neutral gray. When {@code fraction} is 1.0, this returns color unchanged; when fraction is 0.0, it
	 * returns {@link Palette#GRAY}, and when it is in-between 0.0 and 1.0 it returns something between the two. This is
	 * meant for things like area of effect abilities that make smaller color changes toward their periphery.
	 * @param color a color that should have its tinting effect potentially weakened
	 * @param fraction how much of {@code color} should be kept, from 0.0 to 1.0
	 * @return an HSI float color between gray and {@code color}
	 */
	public static float lessenChange(final float color, float fraction) {
		final int e = NumberUtils.floatToRawIntBits(color),
				he = (e & 0xFF), se = (e >>> 8) & 0xFF, ie = (e >>> 16) & 0xFF, ae = e >>> 24 & 0xFE,
				is = 0x80, as = 0xFE;
		return NumberUtils.intBitsToFloat(he
				| (((int) (fraction * se) & 0xFF) << 8)
				| (((int) (is + fraction * (ie - is)) & 0xFF) << 16)
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
	 * @param color a packed float color, as produced by {@link #hsi(float, float, float, float)}
	 * @param seed a long seed that should be different on each call; should not be 0
	 * @param variance max amount of difference between the given color and the generated color; always less than 1
	 * @return a generated packed float color that should be at least somewhat different from {@code color}
	 */
	public static float randomEdit(final float color, long seed, final float variance) {
		final int decoded = NumberUtils.floatToRawIntBits(color);
		final float h = (decoded & 0xff) / 255f;
		final float s = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
		final float i = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
		final float limit = variance * variance;
		float dist, x, y, z;
		for (int j = 0; j < 50; j++) {
			x = (((seed * 0xD1B54A32D192ED03L >>> 41) - 0x7FFFFFp-1f) * 0x1p-22f) * variance;
			y = (((seed * 0xABC98388FB8FAC03L >>> 41) - 0x7FFFFFp-1f) * 0x1p-22f) * variance;
			z = (((seed * 0x8CB92BA72F3D8DD7L >>> 41) - 0x7FFFFFp-1f) * 0x1p-22f) * variance;
			++seed;
			dist = x * x + y * y + z * z;
			if(dist <= limit && inGamut(x += h, y += s, z += i))
				return NumberUtils.intBitsToFloat((decoded & 0xFE000000) | ((int)(z * 255.5f) << 16 & 0xFF0000)
					| ((int)(y * 255.5f) << 8 & 0xFF00) | (int)(x * 255.5f));
		}
		return color;
	}

	/**
	 * Returns true if the given packed float color, as HSI, is valid to convert losslessly back to RGBA.
	 * @param packed a packed float color as HSI
	 * @return true if the given packed float color can be converted back and forth to RGBA
	 */
	public static boolean inGamut(final float packed)
	{
		final int decoded = NumberUtils.floatToRawIntBits(packed);
		return (decoded >>> 8 & 0xff) / 255f <= MathUtils.sin((decoded >>> 16 & 0xff) * (3.14159f / 255f));

	}
	/**
	 * Returns true if the given HSI values are valid to convert losslessly back to RGBA.
	 * @param h hue channel; not used
	 * @param s saturation channel, as a float from 0 to 1
	 * @param i intensity channel, as a float from 0 to 1
	 * @return true if the given packed float color can be converted back and forth to RGBA
	 */
	public static boolean inGamut(float h, float s, float i)
	{
		return i >= 0 && i <= 1 && s >= 0 && s <= MathUtils.sin(i * 3.14159f);
	}

	/**
	 * Given a Random to generate random values, this produces an approximately-evenly-distributed
	 * random HSI color as a packed float. It has an equal likelihood of producing any intensity (which is the least
	 * evenly-distributed part of this), but will correctly produce results in the wide outer bands of high saturation
	 * more often than the thin inner column of grayscale. It always produces an opaque color (alpha always equals 1).
	 * @param random a Random number generator, like {@link com.badlogic.gdx.math.RandomXS128} from libGDX
	 * @return an approximately-evenly-distributed random HSI color
	 */
	public static float randomColor(Random random) {
		final float i = MathUtils.sin(random.nextFloat() * MathUtils.PI);
		final float limit = MathUtils.sin(i * 3.14159f);
		final float s = (float) Math.sqrt(random.nextFloat()) * limit;
		final float h = random.nextFloat();
		return hsi(h, s, i, 1f);
	}
}
