package com.github.tommyettinger.colorful.cielab;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.NumberUtils;
import com.github.tommyettinger.colorful.Shaders;
import com.github.tommyettinger.colorful.oklab.ColorfulBatch;

/**
 * Contains code for manipulating colors as {@code int} and packed {@code float} values in the CIE L*A*B* color space.
 * This is the old standard (and for some things, gold standard) of color spaces, introduced in 1976 and never fully
 * superseded by a newer color space. A reason there are still newer color spaces being introduced is in part that
 * existing color spaces, such as CIELAB, don't have sufficient performance for a given task, and this is an especially
 * fair criticism of CIELAB -- it's slow. But, if you calculate new colors infrequently and do the heaviest conversions
 * in a shader, this might not be noticeably different from Oklab or IPT_HQ. This package emphasizes quality over speed,
 * so some calculations that are approximated in Oklab and IPT_HQ are performed rigorously here.
 * <br>
 * The CIE L*A*B* color space has 3 channels, L, A, and B, each gamma-corrected (which is why an asterisk is added).
 * L is lightness, A is a chroma axis that is (roughly) cyan-vs.-red, and B is a chroma axis that is (roughly)
 * blue-vs.-yellow. This is the same in concept as Oklab, but Oklab is skewed somewhat so high B there is more orange
 * than yellow; it's somewhat hard to accurately produce yellow in Oklab. This also has an alpha channel in each color,
 * which acts like it does in every other color space package here (multiplicative alpha, higher is more opaque).
 */
public class ColorTools {
    /**
     * Gets a packed float representation of a color given as 4 float components, here, L (luminance or lightness), A
     * (a chromatic component ranging from cyan to red), B (a chromatic component ranging from blue to yellow), and
     * alpha (or opacity). As long as you use a batch with {@link Shaders#fragmentShaderCielab} as its shader, colors
     * passed with {@link com.badlogic.gdx.graphics.g2d.Batch#setPackedColor(float)} will be interpreted as CIELAB. L
     * should be between 0 and 1, inclusive, with 0 used for very dark colors (almost only black), and 1 used for very
     * light colors (almost only white). A and B range from 0.0 to 1.0, with grayscale results when both are about 0.5.
     * There's some aesthetic value in changing just one chroma value. When A is high and B is low, the color is more
     * purple/magenta, when both are low it is more bluish, when B is high and A is low, the color tends to be greenish,
     * and when both are high it tends to be orange. When A and B are both near 0.5f, the color is closer to gray.
     * Alpha is the multiplicative opacity of the color, and acts like RGBA's alpha.
     *
     * @param l     0f to 1f, lightness or L component of CIELAB, with 0.5f meaning "no change" and 1f brightening
     * @param a     0f to 1f, protan or A component of CIELAB, with 1f more orange, red, or magenta
     * @param b     0f to 1f, tritan or B component of CIELAB, with 1f more green, yellow, or red
     * @param alpha 0f to 1f, 0f makes the color transparent and 1f makes it opaque
     * @return a float encoding a color with the given properties
     */
    public static float cielab(float l, float a, float b, float alpha) {
        return NumberUtils.intBitsToFloat(((int) (alpha * 255) << 24 & 0xFE000000) | ((int) (b * 255) << 16 & 0xFF0000)
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
     * This is used when converting from RGB to CIELAB, as an intermediate step.
     * @param x any non-negative finite float to find the cube root of
     * @return the cube root of x, approximated
     */
    private static float cbrtPositive(float x) {
        int ix = NumberUtils.floatToRawIntBits(x);
        final float x0 = x;
        ix = (ix>>>2) + (ix>>>4);
        ix += (ix>>>4);
        ix += (ix>>>8) + 0x2A5137A0;
        x  = NumberUtils.intBitsToFloat(ix);
        x  = 0.33333334f*(2f * x + x0/(x*x));
        x  = 0.33333334f*(2f * x + x0/(x*x));
        return x;
    }

    /**
     * Used when converting from CIELAB to RGB, as an intermediate step.
     * Really just {@code x * x * x}.
     * @param x one of the LMS Prime channels to be converted to LMS
     * @return an LMS channel value, which can be converted to RGB
     */
    private static float cube(final float x) {
        return x * x * x;
    }

    /**
     * Used when given non-linear sRGB inputs to make them linear, using an exact gamma of 2.4 and accounting for the
     * darkest colors with a different formula.
     * @param component any non-linear channel of a color, to be made linear
     * @return a linear version of component
     */
    private static float forwardGamma(final float component) {
        return component < 0.04045f ? component * (1f/12.92f) : (float)Math.pow((component + 0.055f) * (1f/1.055f), 2.4f);
    }

    /**
     * Used to return from a linear, gamma-corrected input to an sRGB, non-linear output, using an exact gamma of 2.4
     * and accounting for the darkest colors with a different formula.
     * @param component a linear channel of a color, to be made non-linear
     * @return a non-linear version of component
     */
    private static float reverseGamma(final float component) {
        return component < 0.0031308f ? component * 12.92f : (float)Math.pow(component, 1f/2.4f) * 1.055f - 0.055f;
    }

    private static float forwardXYZ(final float t) {
        return (t < 0.00885645f) ? 7.787037f * t + 0.139731f : cbrtPositive(t);
    }

    private static float reverseXYZ(final float t) {
        return (t < 0.20689655f) ? 0.1284185f * (t - 0.139731f) : t * t * t;
    }

    /*
     */
    /**
     * Converts a packed float color in the format produced by {@link #cielab(float, float, float, float)} to an RGBA8888 int.
     * This format of int can be used with Pixmap and in some other places in libGDX.
     * @param packed a packed float color, as produced by {@link #cielab(float, float, float, float)}
     * @return an RGBA8888 int color
     */
    public static int toRGBA8888(final float packed)
    {
        final int decoded = NumberUtils.floatToRawIntBits(packed);
        final float L = (1f/1.16f)*((decoded & 0xff) / 255f + 0.16f);
        final float A = ((decoded >>> 8 & 0xff) - 127.5f) * (0.2f / 127.5f);
        final float B = ((decoded >>> 16 & 0xff) - 127.5f) * (0.5f / 127.5f);
        final float x = reverseXYZ(L + A);
        final float y = reverseXYZ(L);
        final float z = reverseXYZ(L - B);
        final int r = (int)(reverseGamma(Math.min(Math.max(+3.2406f * x + -0.9689f * y + -0.4986f * z, 0f), 1f)) * 255.999f);
        final int g = (int)(reverseGamma(Math.min(Math.max(-1.5372f * x + +1.8758f * y + +0.0415f * z, 0f), 1f)) * 255.999f);
        final int b = (int)(reverseGamma(Math.min(Math.max(+3.2406f * x + -0.9689f * y + +1.0570f * z, 0f), 1f)) * 255.999f);
        return r << 24 | g << 16 | b << 8 | (decoded & 0xfe000000) >>> 24 | decoded >>> 31;
    }

    /**
     * Converts a packed float color in the format produced by {@link #cielab(float, float, float, float)}
     * to a packed float in ABGR8888 format.
     * This format of float can be used with the standard SpriteBatch and in some other places in libGDX.
     * @param packed a packed float color, as produced by {@link #cielab(float, float, float, float)}
     * @return a packed float color as ABGR8888
     */
    public static float toRGBA(final float packed)
    {
        final int decoded = NumberUtils.floatToRawIntBits(packed);
        final float L = (1f/1.16f)*((decoded & 0xff) / 255f + 0.16f);
        final float A = ((decoded >>> 8 & 0xff) - 127.5f) * (0.2f / 127.5f);
        final float B = ((decoded >>> 16 & 0xff) - 127.5f) * (0.5f / 127.5f);
        final float x = reverseXYZ(L + A);
        final float y = reverseXYZ(L);
        final float z = reverseXYZ(L - B);
        final int r = (int)(reverseGamma(Math.min(Math.max(+3.2406f * x + -0.9689f * y + -0.4986f * z, 0f), 1f)) * 255.999f);
        final int g = (int)(reverseGamma(Math.min(Math.max(-1.5372f * x + +1.8758f * y + +0.0415f * z, 0f), 1f)) * 255.999f);
        final int b = (int)(reverseGamma(Math.min(Math.max(+3.2406f * x + -0.9689f * y + +1.0570f * z, 0f), 1f)) * 255.999f);
        return NumberUtils.intBitsToFloat(r | g << 8 | b << 16 | (decoded & 0xfe000000));
    }

    /**
     * Writes a CIELAB-format packed float color (the format produced by {@link #cielab(float, float, float, float)})
     * into an RGBA8888 Color as used by libGDX (called {@code editing}).
     * @param editing a libGDX color that will be filled in-place with an RGBA conversion of {@code packed}
     * @param packed a packed float color, as produced by {@link #cielab(float, float, float, float)}
     * @return an RGBA8888 int color
     */
    public static Color toColor(Color editing, final float packed)
    {
        final int decoded = NumberUtils.floatToRawIntBits(packed);
        final float L = (1f/1.16f)*((decoded & 0xff) / 255f + 0.16f);
        final float A = ((decoded >>> 8 & 0xff) - 127.5f) * (0.2f / 127.5f);
        final float B = ((decoded >>> 16 & 0xff) - 127.5f) * (0.5f / 127.5f);
        final float x = reverseXYZ(L + A);
        final float y = reverseXYZ(L);
        final float z = reverseXYZ(L - B);
        editing.r = (reverseGamma(Math.min(Math.max(+3.2406f * x + -0.9689f * y + -0.4986f * z, 0f), 1f));
        editing.g = (reverseGamma(Math.min(Math.max(-1.5372f * x + +1.8758f * y + +0.0415f * z, 0f), 1f));
        editing.b = (reverseGamma(Math.min(Math.max(+3.2406f * x + -0.9689f * y + +1.0570f * z, 0f), 1f));
        editing.a = (decoded >>> 25) * 0x1.020408p-7f; // this is 1/127 as a float
        return editing.clamp();
    }

    /**
     * Writes a CIELAB-format packed float color (the format produced by {@link #cielab(float, float, float, float)})
     * into a CIELAB-format Color called {@code editing}. This is mostly useful if the rest of your application expects
     * colors in Oklab format, such as because you use {@link Shaders#fragmentShaderCielab} or {@link ColorfulBatch}.
     * <br>
     * Internally, this simply calls {@link Color#abgr8888ToColor(Color, float)} and returns the edited Color.
     * @param editing a libGDX Color that will be filled in-place with the color {@code cielab}, unchanged from its color space
     * @param cielab a packed float color, as produced by {@link #cielab(float, float, float, float)}
     * @return an RGBA8888 int color
     */
    public static Color toCIELABColor(Color editing, final float cielab){
        Color.abgr8888ToColor(editing, cielab);
        return editing;
    }

}
