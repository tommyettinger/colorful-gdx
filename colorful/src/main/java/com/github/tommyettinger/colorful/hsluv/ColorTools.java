/*
 * Copyright (c) 2016 Alexei Boronine
 * Copyright (c) 2022 Nathan Sweet
 * Copyright (c) 2022 Tommy Ettinger
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.tommyettinger.colorful.hsluv;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.NumberUtils;
import com.github.tommyettinger.colorful.FloatColors;
import com.github.tommyettinger.colorful.TrigTools;

import java.util.Random;

/**
 * Contains code for manipulating colors as {@code int} and packed {@code float} values in the HSLuv color space.
 * See <a href="https://www.hsluv.org/">HSLuv's website</a> for more info.
 * <br>
 * The HSLuv color space has 3 channels, Hue, Saturation, and Lightness. Lightness should be much more even when hue and
 * saturation change, when compared with "vanilla" HSL or HSV. This also has an alpha channel in each color,
 * which acts like it does in every other color space package here (multiplicative alpha, higher is more opaque).
 */
public class ColorTools {
    /**
     * No need to instantiate.
     */
    private ColorTools(){
    }
    /**
     * Gets a packed float representation of a color given as 4 float components, here, hue, saturation, lightness, and
     * alpha (or opacity). As long as you use a batch with {@code Shaders#fragmentShaderHsluv} as its shader, colors
     * passed with {@link com.badlogic.gdx.graphics.g2d.Batch#setPackedColor(float)} will be interpreted as HSLuv. H
     * should be between 0 and 1, inclusive, with 0.0f or so meaning reddish, 0.3f or so meaning greenish, and 0.7 or so
     * meaning bluish. S should be between 0 and 1, inclusive, with 0 meaning grayscale and 1 meaning fully bold/bright.
     * L should be between 0 and 1, inclusive, with 0 used for very dark colors (almost only black), and 1 used for very
     * light colors (almost only white). Alpha is the multiplicative opacity of the color, and acts like RGBA's alpha.
     *
     * @param h     0f to 1f, hue component, with 0.0f meaning red, 0.3 meaning green, and 0.7 meaning blue
     * @param s     0f to 1f, saturation component, with 0.0f meaning grayscale and 1.0f meaning fully bold/bright
     * @param l     0f to 1f, lightness component, with 0.5f meaning "no change" and 1f brightening
     * @param alpha 0f to 1f, 0f makes the color transparent and 1f makes it opaque
     * @return a float encoding a color with the given properties
     */
    public static float hsluv(float h, float s, float l, float alpha) {
        return NumberUtils.intBitsToFloat(((int) (alpha * 255.999f) << 24 & 0xFE000000) | ((int) (l * 255.999f) << 16 & 0xFF0000)
                | ((int) (s * 255.999f) << 8 & 0xFF00) | ((int) (h * 255.999f) & 0xFF));
    }
    /**
     * Gets a packed float representation of a color given as 4 float components, H, S, L, and alpha, with the S, L, and
     * alpha components clamped to the 0f to 1f range before being entered into the packed float color, while H is
     * wrapped into the same range. This is only different from {@link #hsluv(float, float, float, float)} in that it
     * clamps or wraps each component.
     *
     * @see #hsluv(float, float, float, float) This uses the same definitions for H, S, L, and alpha as hsluv().
     * @param h     0f to 1f, hue component, with 0.0f meaning red, 0.3 meaning green, and 0.7 meaning blue
     * @param s     0f to 1f, saturation component, with 0.0f meaning grayscale and 1.0f meaning fully bold/bright
     * @param l     0f to 1f, lightness component, with 0.5f meaning "no change" and 1f brightening
     * @param alpha 0f to 1f, 0f makes the color transparent and 1f makes it opaque
     * @return a float encoding a color with the given properties
     */
    public static float clamp(float h, float s, float l, float alpha) {
        return NumberUtils.intBitsToFloat((Math.min(Math.max((int) (alpha * 127.999f), 0), 127) << 25)
                | (Math.min(Math.max((int) (l * 255.999f), 0), 255) << 16)
                | (Math.min(Math.max((int) (s * 255.999f), 0), 255) << 8)
                | (int) ((h - MathUtils.floor(h)) * 256f)
        );
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
     * This is used when converting from RGB to HSLuv, as an intermediate step.
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
     * Used when given non-linear sRGB inputs to make them linear, using an exact gamma of 2.4 and accounting for the
     * darkest colors with a different formula. This is rather close to squaring {@code component}.
     * @param component any non-linear channel of a color, to be made linear
     * @return a linear version of component
     */
    private static float forwardGamma(final float component) {
        return component < 0.04045f ? component * (1f/12.92f) : (float)Math.pow((component + 0.055f) * (1f/1.055f), 2.4f);
    }

    /**
     * Used to return from a linear, gamma-corrected input to an sRGB, non-linear output, using an exact gamma of 2.4
     * and accounting for the darkest colors with a different formula. This is mostly similar to the square root, but is
     * more precise for very dark colors.
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

    static private final float[][] m = new float[][] {
            new float[] {+3.2404542f, -1.5371385f, -0.4985314f},
            new float[] {-0.9692660f, +1.8760108f, +0.0415560f},
            new float[] {+0.0556434f, -0.2040259f, +1.0572252f},
    };
    static final float[][] minv = new float[][] {
            new float[] {0.4124564f, 0.3575761f, 0.1804375f},
            new float[] {0.2126729f, 0.7151522f, 0.0721750f},
            new float[] {0.0193339f, 0.1191920f, 0.9503041f},
    };
    static private final float refU = 0.19783000664283f, refV = 0.46831999493879f;
    static final float kappa = 9.032962962f, epsilon = 0.0088564516f;

    /**
     * Converts a packed float color in the format produced by {@link #hsluv(float, float, float, float)} to an RGBA8888 int.
     * This format of int can be used with Pixmap and in some other places in libGDX.
     * @param packed a packed float color, as produced by {@link #hsluv(float, float, float, float)}
     * @return an RGBA8888 int color
     */
    public static int toRGBA8888(final float packed)
    {
        final int decoded = NumberUtils.floatToRawIntBits(packed);
        float H = ((decoded & 0xff) / 255f);
        float S = ((decoded >>> 8 & 0xff) / 255f);
        float L = ((decoded >>> 16 & 0xff) / 255f);
//        float L = (1f/1.16f)*((decoded >>> 16 & 0xff) / 255f + 0.16f);

        // HSLuv to Lch
        float C;
        if (L > 0.99999f) {
            L = 1;
            C = 0;
        } else if (L < 0.00001f) {
            L = 0;
            C = 0;
        } else
            C = chromaLimit(H, L) * S;

        // Lch to Luv
        float U = TrigTools.cos_(H) * C;
        float V = TrigTools.sin_(H) * C;

        // Luv to XYZ
        float x, y, z;
        if (L < 0.00001f) {
            return (decoded & 0xfe000000) >>> 24 | decoded >>> 31;
        } else if(L > 0.9999f) {
            return 0xFFFFFF00 | (decoded & 0xfe000000) >>> 24 | decoded >>> 31;
        }else {
            if (L <= epsilon)
                y = L / kappa;
            else {
                y = (L + 0.16f) / 1.16f;
                y *= y * y;
            }
            float iL = 1f / (13f * L);
            float varU = U * iL + refU;
            float varV = V * iL + refV;
            x = 9 * varU * y / (4 * varV);
            z = (3 * y / varV) - x / 3 - 5 * y;
        }
        final int r = (int)(reverseGamma(+3.2404542f * x + -1.5371385f * y + -0.4985314f * z) * 255.999f);
        final int g = (int)(reverseGamma(-0.9692660f * x + +1.8760108f * y + +0.0415560f * z) * 255.999f);
        final int b = (int)(reverseGamma(+0.0556434f * x + -0.2040259f * y + +1.0572252f * z) * 255.999f);
        return r << 24 | g << 16 | b << 8 | (decoded & 0xfe000000) >>> 24 | decoded >>> 31;
    }

    /**
     * Converts a packed float color in the format produced by {@link #hsluv(float, float, float, float)}
     * to a packed float in ABGR8888 format.
     * This format of float can be used with the standard SpriteBatch and in some other places in libGDX.
     * @param packed a packed float color, as produced by {@link #hsluv(float, float, float, float)}
     * @return a packed float color as ABGR8888
     */
    public static float toRGBA(final float packed)
    {
        final int decoded = NumberUtils.floatToRawIntBits(packed);
        float H = ((decoded & 0xff) / 255f);
        float S = ((decoded >>> 8 & 0xff) / 255f);
        float L = ((decoded >>> 16 & 0xff) / 255f);

        // HSLuv to Lch
        float C;
        if (L > 0.99999f) {
            L = 1;
            C = 0;
        } else if (L < 0.00001f) {
            L = 0;
            C = 0;
        } else
            C = chromaLimit(H, L) * S;

        // Lch to Luv
        float U = TrigTools.cos_(H) * C;
        float V = TrigTools.sin_(H) * C;

        // Luv to XYZ
        float x, y, z;
        if (L < 0.00001f) {
            return NumberUtils.intBitsToFloat(decoded & 0xfe000000);
        } else if(L > 0.9999f) {
            return NumberUtils.intBitsToFloat(0xFFFFFF | (decoded & 0xfe000000));
        }else {
            if (L <= epsilon)
                y = L / kappa;
            else {
                y = (L + 0.16f) / 1.16f;
                y *= y * y;
            }
            float iL = 1f / (13f * L);
            float varU = U * iL + refU;
            float varV = V * iL + refV;
            x = 9 * varU * y / (4 * varV);
            z = (3 * y / varV) - x / 3 - 5 * y;
        }
        final int r = (int)(reverseGamma(Math.min(Math.max(+3.2404542f * x + -1.5371385f * y + -0.4985314f * z, 0f), 1f)) * 255.999f);
        final int g = (int)(reverseGamma(Math.min(Math.max(-0.9692660f * x + +1.8760108f * y + +0.0415560f * z, 0f), 1f)) * 255.999f);
        final int b = (int)(reverseGamma(Math.min(Math.max(+0.0556434f * x + -0.2040259f * y + +1.0572252f * z, 0f), 1f)) * 255.999f);
        return NumberUtils.intBitsToFloat(r | g << 8 | b << 16 | (decoded & 0xfe000000));
    }

    /**
     * Writes a HSLuv-format packed float color (the format produced by {@link #hsluv(float, float, float, float)})
     * into an RGBA8888 Color as used by libGDX (called {@code editing}).
     * @param editing a libGDX color that will be filled in-place with an RGBA conversion of {@code packed}
     * @param packed a packed float color, as produced by {@link #hsluv(float, float, float, float)}
     * @return an RGBA8888 int color
     */
    public static Color toColor(Color editing, final float packed)
    {
        final int decoded = NumberUtils.floatToRawIntBits(packed);
        float H = ((decoded & 0xff) / 255f);
        float S = ((decoded >>> 8 & 0xff) / 255f);
        float L = ((decoded >>> 16 & 0xff) / 255f);

        // HSLuv to Lch
        float C;
        if (L > 0.99999f) {
            L = 1;
            C = 0;
        } else if (L < 0.00001f) {
            L = 0;
            C = 0;
        } else
            C = chromaLimit(H, L) * S;

        // Lch to Luv
        float U = TrigTools.cos_(H) * C;
        float V = TrigTools.sin_(H) * C;

        // Luv to XYZ
        float x, y, z;
        if (L < 0.00001f) {
            editing.r = editing.g = editing.b = 0f;
            editing.a = (decoded >>> 25) / 127f;
            return editing;
        } else if(L > 0.9999f) {
            editing.r = editing.g = editing.b = 1f;
            editing.a = (decoded >>> 25) / 127f;
            return editing;
        }else {
            if (L <= epsilon)
                y = L / kappa;
            else {
                y = (L + 0.16f) / 1.16f;
                y *= y * y;
            }
            float iL = 1f / (13f * L);
            float varU = U * iL + refU;
            float varV = V * iL + refV;
            x = 9 * varU * y / (4 * varV);
            z = (3 * y / varV) - x / 3 - 5 * y;
        }
        editing.r = reverseGamma(Math.min(Math.max(+3.2404542f * x + -1.5371385f * y + -0.4985314f * z, 0f), 1f));
        editing.g = reverseGamma(Math.min(Math.max(-0.9692660f * x + +1.8760108f * y + +0.0415560f * z, 0f), 1f));
        editing.b = reverseGamma(Math.min(Math.max(+0.0556434f * x + -0.2040259f * y + +1.0572252f * z, 0f), 1f));
        editing.a = (decoded >>> 25) / 127f;
        return editing;
    }

    /**
     * Writes a HSLuv-format packed float color (the format produced by {@link #hsluv(float, float, float, float)})
     * into a HSLuv-format Color called {@code editing}. This is mostly useful if the rest of your application expects
     * colors in HSLuv format, such as because you use {@code Shaders#fragmentShaderHsluv} or {@code ColorfulBatch}.
     * <br>
     * Internally, this simply calls {@link Color#abgr8888ToColor(Color, float)} and returns the edited Color.
     * @param editing a libGDX Color that will be filled in-place with the color {@code hsluv}, unchanged from its color space
     * @param hsluv a packed float color, as produced by {@link #hsluv(float, float, float, float)}
     * @return an RGBA8888 int color
     */
    public static Color toHsluvColor(Color editing, final float hsluv){
        Color.abgr8888ToColor(editing, hsluv);
        return editing;
    }

    /**
     * Takes a color encoded as an RGBA8888 int and converts to a packed float in the HSLuv format this uses.
     * @param rgba an int with the channels (in order) red, green, blue, alpha; should have 8 bits per channel
     * @return a packed float as HSLuv, which this class can use
     */
    public static float fromRGBA8888(final int rgba) {
        final float r = forwardGamma((rgba >>> 24) * 0x1.010101010101p-8f);
        final float g = forwardGamma((rgba >>> 16 & 0xFF) * 0x1.010101010101p-8f);
        final float b = forwardGamma((rgba >>> 8 & 0xFF) * 0x1.010101010101p-8f);

        final float x = /* forwardXYZ */(0.4124564f * r  + 0.3575761f * g + 0.1804375f * b);
        final float y = /* forwardXYZ */(0.2126729f * r  + 0.7151522f * g + 0.0721750f * b);
        final float z = /* forwardXYZ */(0.0193339f * r  + 0.1191920f * g + 0.9503041f * b);

        // XYZ to Luv
        float L = 1.16f * cbrtPositive(y) - 0.16f, U, V, h, s, l;
        if (L < 0.00001f) {
            L = 0;
            U = 0;
            V = 0;
        } else {
            U = 13 * L * (4 * x / (x + 15 * y + 3 * z) - refU);
            V = 13 * L * (9 * y / (x + 15 * y + 3 * z) - refV);
        }

        // Luv to Lch
        float C = (float)Math.sqrt(U * U + V * V);
        if (C < 0.00001f)
            h = 0;
        else {
            h = TrigTools.atan2_(V, U);
        }

        // Lch to HSLuv
        if (L > 0.99999f) {
            s = 0;
            l = 1;
        } else if (L < 0.00001f) {
            s = 0;
            l = 0;
        } else {
            s = Math.min(C / chromaLimit(h, L), 1);
            l = L;
        }
        return NumberUtils.intBitsToFloat(
                          Math.min(Math.max((int)(h * 255.999f    ), 0), 255)
                        | Math.min(Math.max((int)(s * 255.999f    ), 0), 255) << 8
                        | Math.min(Math.max((int)(l * 255.999f    ), 0), 255) << 16
                        | (rgba & 0xFE) << 24);
    }

    /**
     * Takes a color encoded as an ABGR packed float and converts to a packed float in the HSLuv format this uses.
     * @param packed a packed float in ABGR8888 format, with A in the MSB and R in the LSB
     * @return a packed float as HSLuv, which this class can use
     */
    public static float fromRGBA(final float packed) {
        final int abgr = NumberUtils.floatToRawIntBits(packed);
        final float r = forwardGamma((abgr & 0xFF) * 0x1.010101010101p-8f);
        final float g = forwardGamma((abgr >>> 8 & 0xFF) * 0x1.010101010101p-8f);
        final float b = forwardGamma((abgr >>> 16 & 0xFF) * 0x1.010101010101p-8f);

        final float x = /* forwardXYZ */(0.4124564f * r  + 0.3575761f * g + 0.1804375f * b);
        final float y = /* forwardXYZ */(0.2126729f * r  + 0.7151522f * g + 0.0721750f * b);
        final float z = /* forwardXYZ */(0.0193339f * r  + 0.1191920f * g + 0.9503041f * b);

        // XYZ to Luv
        float L = 1.16f * cbrtPositive(y) - 0.16f, U, V, h, s, l;
//        float L = 1.16f * y - 0.16f, U, V, h, s, l;
        if (L < 0.00001f) {
            L = 0;
            U = 0;
            V = 0;
        } else {
            U = 13 * L * (4 * x / (x + 15 * y + 3 * z) - refU);
            V = 13 * L * (9 * y / (x + 15 * y + 3 * z) - refV);
        }

        // Luv to Lch
        float C = (float)Math.sqrt(U * U + V * V);
        if (C < 0.00001f)
            h = 0;
        else {
            h = TrigTools.atan2_(V, U);
        }

        // Lch to HSLuv
        if (L > 0.99999f) {
            s = 0;
            l = 1;
        } else if (L < 0.00001f) {
            s = 0;
            l = 0;
        } else {
            s = Math.min(C / chromaLimit(h, L), 1);
            l = L;
        }
        return NumberUtils.intBitsToFloat(
                Math.min(Math.max((int)(h * 255.999f    ), 0), 255)
                        | Math.min(Math.max((int)(s * 255.999f    ), 0), 255) << 8
                        | Math.min(Math.max((int)(l * 255.999f    ), 0), 255) << 16
                        | (abgr & 0xFE000000));
    }

    /**
     * Takes a libGDX Color that uses RGBA8888 channels and converts to a packed float in the HSLuv format this uses.
     * @param color a libGDX RGBA8888 Color
     * @return a packed float as HSLuv, which this class can use
     */
    public static float fromColor(final Color color) {
        final float r = forwardGamma(color.r);
        final float g = forwardGamma(color.g);
        final float b = forwardGamma(color.b);

        final float x = /* forwardXYZ */(0.4124564f * r  + 0.3575761f * g + 0.1804375f * b);
        final float y = /* forwardXYZ */(0.2126729f * r  + 0.7151522f * g + 0.0721750f * b);
        final float z = /* forwardXYZ */(0.0193339f * r  + 0.1191920f * g + 0.9503041f * b);

        // XYZ to Luv
        float L = 1.16f * cbrtPositive(y) - 0.16f, U, V, h, s, l;
        if (L < 0.00001f) {
            L = 0;
            U = 0;
            V = 0;
        } else {
            U = 13 * L * (4 * x / (x + 15 * y + 3 * z) - refU);
            V = 13 * L * (9 * y / (x + 15 * y + 3 * z) - refV);
        }

        // Luv to Lch
        float C = (float)Math.sqrt(U * U + V * V);
        if (C < 0.00001f)
            h = 0;
        else {
            h = TrigTools.atan2_(V, U);
        }

        // Lch to HSLuv
        if (L > 0.99999f) {
            s = 0;
            l = 1;
        } else if (L < 0.00001f) {
            s = 0;
            l = 0;
        } else {
            s = Math.min(C / chromaLimit(h, L), 1);
            l = L;
        }
        return NumberUtils.intBitsToFloat(
                Math.min(Math.max((int)(h * 255.999f    ), 0), 255)
                        | Math.min(Math.max((int)(s * 255.999f    ), 0), 255) << 8
                        | Math.min(Math.max((int)(l * 255.999f    ), 0), 255) << 16
                        | ((int)(color.a * 255f) << 24 & 0xFE000000));
    }

    /**
     * Takes RGBA components from 0.0 to 1.0 each and converts to a packed float in the HSLuv format this uses.
     * @param r red, from 0.0 to 1.0 (both inclusive)
     * @param g green, from 0.0 to 1.0 (both inclusive)
     * @param b blue, from 0.0 to 1.0 (both inclusive)
     * @param a alpha, from 0.0 to 1.0 (both inclusive)
     * @return a packed float as HSLuv, which this class can use
     */
    public static float fromRGBA(float r, float g, float b, final float a) {
        r = forwardGamma(r);
        g = forwardGamma(g);
        b = forwardGamma(b);

        final float x = /* forwardXYZ */(0.4124564f * r  + 0.3575761f * g + 0.1804375f * b);
        final float y = /* forwardXYZ */(0.2126729f * r  + 0.7151522f * g + 0.0721750f * b);
        final float z = /* forwardXYZ */(0.0193339f * r  + 0.1191920f * g + 0.9503041f * b);

        // XYZ to Luv
        float L = 1.16f * cbrtPositive(y) - 0.16f, U, V, h, s, l;
        if (L < 0.00001f) {
            L = 0;
            U = 0;
            V = 0;
        } else {
            U = 13 * L * (4 * x / (x + 15 * y + 3 * z) - refU);
            V = 13 * L * (9 * y / (x + 15 * y + 3 * z) - refV);
        }

        // Luv to Lch
        float C = (float)Math.sqrt(U * U + V * V);
        if (C < 0.00001f)
            h = 0;
        else {
            h = TrigTools.atan2_(V, U);
        }

        // Lch to HSLuv
        if (L > 0.99999f) {
            s = 0;
            l = 1;
        } else if (L < 0.00001f) {
            s = 0;
            l = 0;
        } else {
            s = Math.min(C / chromaLimit(h, L), 1);
            l = L;
        }
        return NumberUtils.intBitsToFloat(
                Math.min(Math.max((int)(h * 255.999f    ), 0), 255)
                        | Math.min(Math.max((int)(s * 255.999f    ), 0), 255) << 8
                        | Math.min(Math.max((int)(l * 255.999f    ), 0), 255) << 16
                        | ((int)(a * 255f) << 24 & 0xFE000000));
    }

    	/**
	 * Gets the red channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #hsluv(float, float, float, float)}
	 * @return an int from 0 to 255, inclusive, representing the red channel value of the given encoded color
	 */
	public static int redInt(final float encoded)
	{
        final int decoded = NumberUtils.floatToRawIntBits(encoded);
        float H = ((decoded & 0xff) / 255f);
        float S = ((decoded >>> 8 & 0xff) / 255f);
        float L = ((decoded >>> 16 & 0xff) / 255f);

        // HSLuv to Lch
        float C;
        if (L > 0.99999f) {
            L = 1;
            C = 0;
        } else if (L < 0.00001f) {
            L = 0;
            C = 0;
        } else
            C = chromaLimit(H, L) * S;

        // Lch to Luv
        float U = TrigTools.cos_(H) * C;
        float V = TrigTools.sin_(H) * C;

        // Luv to XYZ
        float x, y, z;
        if (L < 0.00001f) {
            x = 0;
            y = 0;
            z = 0;
        } else {
            if (L <= epsilon)
                y = L / kappa;
            else {
                y = (L + 0.16f) / 1.16f;
                y *= y * y;
            }
            float iL = 1f / (13f * L);
            float varU = U * iL + refU;
            float varV = V * iL + refV;
            x = 9 * varU * y / (4 * varV);
            z = (3 * y / varV) - x / 3 - 5 * y;
        }
        return (int)(reverseGamma(Math.min(Math.max(+3.2404542f * x + -1.5371385f * y + -0.4985314f * z, 0f), 1f)) * 255.999f);
	}

	/**
	 * Gets the green channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #hsluv(float, float, float, float)}
	 * @return an int from 0 to 255, inclusive, representing the green channel value of the given encoded color
	 */
	public static int greenInt(final float encoded)
	{
        final int decoded = NumberUtils.floatToRawIntBits(encoded);
        float H = ((decoded & 0xff) / 255f);
        float S = ((decoded >>> 8 & 0xff) / 255f);
        float L = ((decoded >>> 16 & 0xff) / 255f);

        // HSLuv to Lch
        float C;
        if (L > 0.99999f) {
            L = 1;
            C = 0;
        } else if (L < 0.00001f) {
            L = 0;
            C = 0;
        } else
            C = chromaLimit(H, L) * S;

        // Lch to Luv
        float U = TrigTools.cos_(H) * C;
        float V = TrigTools.sin_(H) * C;

        // Luv to XYZ
        float x, y, z;
        if (L < 0.00001f) {
            x = 0;
            y = 0;
            z = 0;
        } else {
            if (L <= epsilon)
                y = L / kappa;
            else {
                y = (L + 0.16f) / 1.16f;
                y *= y * y;
            }
            float iL = 1f / (13f * L);
            float varU = U * iL + refU;
            float varV = V * iL + refV;
            x = 9 * varU * y / (4 * varV);
            z = (3 * y / varV) - x / 3 - 5 * y;
        }
        return (int)(reverseGamma(Math.min(Math.max(-0.9692660f * x + +1.8760108f * y + +0.0415560f * z, 0f), 1f)) * 255.999f);
	}

	/**
	 * Gets the blue channel value of the given encoded color, as an int ranging from 0 to 255, inclusive.
	 * @param encoded a color as a packed float that can be obtained by {@link #hsluv(float, float, float, float)}
	 * @return an int from 0 to 255, inclusive, representing the blue channel value of the given encoded color
	 */
	public static int blueInt(final float encoded)
	{
        final int decoded = NumberUtils.floatToRawIntBits(encoded);
        float H = ((decoded & 0xff) / 255f);
        float S = ((decoded >>> 8 & 0xff) / 255f);
        float L = ((decoded >>> 16 & 0xff) / 255f);

        // HSLuv to Lch
        float C;
        if (L > 0.99999f) {
            L = 1;
            C = 0;
        } else if (L < 0.00001f) {
            L = 0;
            C = 0;
        } else
            C = chromaLimit(H, L) * S;

        // Lch to Luv
        float U = TrigTools.cos_(H) * C;
        float V = TrigTools.sin_(H) * C;

        // Luv to XYZ
        float x, y, z;
        if (L < 0.00001f) {
            x = 0;
            y = 0;
            z = 0;
        } else {
            if (L <= epsilon)
                y = L / kappa;
            else {
                y = (L + 0.16f) / 1.16f;
                y *= y * y;
            }
            float iL = 1f / (13f * L);
            float varU = U * iL + refU;
            float varV = V * iL + refV;
            x = 9 * varU * y / (4 * varV);
            z = (3 * y / varV) - x / 3 - 5 * y;
        }
        return (int)(reverseGamma(Math.min(Math.max(+0.0556434f * x + -0.2040259f * y + +1.0572252f * z, 0f), 1f)) * 255.999f);
	}

	/**
	 * Gets the alpha channel value of the given encoded color, as an even int ranging from 0 to 254, inclusive. Because
	 * of how alpha is stored in libGDX, no odd-number values are possible for alpha.
	 * @param encoded a color as a packed float that can be obtained by {@link #hsluv(float, float, float, float)}
	 * @return an even int from 0 to 254, inclusive, representing the alpha channel value of the given encoded color
	 */
	public static int alphaInt(final float encoded)
	{
		return (NumberUtils.floatToRawIntBits(encoded) & 0xfe000000) >>> 24;
	}
    /**
     * Gets the red channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
     * @param encoded a color as a packed float that can be obtained by {@link #hsluv(float, float, float, float)}
     * @return a float from 0.0f to 1.0f, inclusive, representing the red channel value of the given encoded color
     */
    public static float red(final float encoded)
    {
        final int decoded = NumberUtils.floatToRawIntBits(encoded);
        float H = ((decoded & 0xff) / 255f);
        float S = ((decoded >>> 8 & 0xff) / 255f);
        float L = ((decoded >>> 16 & 0xff) / 255f);

        // HSLuv to Lch
        float C;
        if (L > 0.99999f) {
            L = 1;
            C = 0;
        } else if (L < 0.00001f) {
            L = 0;
            C = 0;
        } else
            C = chromaLimit(H, L) * S;

        // Lch to Luv
        float U = TrigTools.cos_(H) * C;
        float V = TrigTools.sin_(H) * C;

        // Luv to XYZ
        float x, y, z;
        if (L < 0.00001f) {
            x = 0;
            y = 0;
            z = 0;
        } else {
            if (L <= epsilon)
                y = L / kappa;
            else {
                y = (L + 0.16f) / 1.16f;
                y *= y * y;
            }
            float iL = 1f / (13f * L);
            float varU = U * iL + refU;
            float varV = V * iL + refV;
            x = 9 * varU * y / (4 * varV);
            z = (3 * y / varV) - x / 3 - 5 * y;
        }
        return reverseGamma(Math.min(Math.max(+3.2404542f * x + -1.5371385f * y + -0.4985314f * z, 0f), 1f));
    }

    /**
     * Gets the green channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
     * @param encoded a color as a packed float that can be obtained by {@link #hsluv(float, float, float, float)}
     * @return a float from 0.0f to 1.0f, inclusive, representing the green channel value of the given encoded color
     */
    public static float green(final float encoded)
    {
        final int decoded = NumberUtils.floatToRawIntBits(encoded);
        float H = ((decoded & 0xff) / 255f);
        float S = ((decoded >>> 8 & 0xff) / 255f);
        float L = ((decoded >>> 16 & 0xff) / 255f);

        // HSLuv to Lch
        float C;
        if (L > 0.99999f) {
            L = 1;
            C = 0;
        } else if (L < 0.00001f) {
            L = 0;
            C = 0;
        } else
            C = chromaLimit(H, L) * S;

        // Lch to Luv
        float U = TrigTools.cos_(H) * C;
        float V = TrigTools.sin_(H) * C;

        // Luv to XYZ
        float x, y, z;
        if (L < 0.00001f) {
            x = 0;
            y = 0;
            z = 0;
        } else {
            if (L <= epsilon)
                y = L / kappa;
            else {
                y = (L + 0.16f) / 1.16f;
                y *= y * y;
            }
            float iL = 1f / (13f * L);
            float varU = U * iL + refU;
            float varV = V * iL + refV;
            x = 9 * varU * y / (4 * varV);
            z = (3 * y / varV) - x / 3 - 5 * y;
        }
        return reverseGamma(Math.min(Math.max(-0.9692660f * x + +1.8760108f * y + +0.0415560f * z, 0f), 1f));
    }

    /**
     * Gets the blue channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
     * @param encoded a color as a packed float that can be obtained by {@link #hsluv(float, float, float, float)}
     * @return a float from 0.0f to 1.0f, inclusive, representing the blue channel value of the given encoded color
     */
    public static float blue(final float encoded)
    {
        final int decoded = NumberUtils.floatToRawIntBits(encoded);
        float H = ((decoded & 0xff) / 255f);
        float S = ((decoded >>> 8 & 0xff) / 255f);
        float L = ((decoded >>> 16 & 0xff) / 255f);

        // HSLuv to Lch
        float C;
        if (L > 0.99999f) {
            L = 1;
            C = 0;
        } else if (L < 0.00001f) {
            L = 0;
            C = 0;
        } else
            C = chromaLimit(H, L) * S;

        // Lch to Luv
        float U = TrigTools.cos_(H) * C;
        float V = TrigTools.sin_(H) * C;

        // Luv to XYZ
        float x, y, z;
        if (L < 0.00001f) {
            x = 0;
            y = 0;
            z = 0;
        } else {
            if (L <= epsilon)
                y = L / kappa;
            else {
                y = (L + 0.16f) / 1.16f;
                y *= y * y;
            }
            float iL = 1f / (13f * L);
            float varU = U * iL + refU;
            float varV = V * iL + refV;
            x = 9 * varU * y / (4 * varV);
            z = (3 * y / varV) - x / 3 - 5 * y;
        }
        return reverseGamma(Math.min(Math.max(+0.0556434f * x + -0.2040259f * y + +1.0572252f * z, 0f), 1f));
    }

    /**
     * Gets the alpha channel value of the given encoded color, as a float from 0.0f to 1.0f, inclusive.
     * @param encoded a color as a packed float that can be obtained by {@link #hsluv(float, float, float, float)}
     * @return a float from 0.0f to 1.0f, inclusive, representing the alpha channel value of the given encoded color
     */
    public static float alpha(final float encoded)
    {
        return (NumberUtils.floatToRawIntBits(encoded) >>> 25) / 127f;
    }


    /**
     * Gets the "chroma" or "colorfulness" of a given HSLuv color. Chroma is similar to saturation in that grayscale
     * values have 0 saturation and 0 chroma, while brighter colors have high saturation and chroma. The difference is
     * that colors that are perceptually more-colorful have higher chroma than colors that are perceptually
     * less-colorful, regardless of hue, whereas saturation changes its meaning depending on the hue and lightness. That
     * is, the most saturated color for a given hue and lightness always has a saturation of 1, but if that color
     * isn't perceptually very colorful (as is the case for very dark and very light colors), it will have a chroma that
     * is much lower than the maximum. The result of this method can't be negative, grayscale values have very close to
     * 0 chroma, and the most colorful value (a shade of purple) should have ??? chroma.
     * @param encoded a color as a packed float that can be obtained by {@link #hsluv(float, float, float, float)}
     * @return a float between 0.0f and ??? that represents how colorful the given value is
     */
    public static float chroma(final float encoded) {
        final int decoded = NumberUtils.floatToRawIntBits(encoded);
        float H = ((decoded & 0xff) / 255f);
        float S = ((decoded >>> 8 & 0xff) / 255f);
        float L = ((decoded >>> 16 & 0xff) / 255f);

        // HSLuv to Lch
        if (L > 0.99999f) {
            return 0f;
        } else if (L < 0.00001f) {
            return 0f;
        } else
            return chromaLimit(H, L) * S;
    }
    /**
     * Given a hue and lightness, this gets the (exact) maximum chroma possible for that hue-lightness
     * combination, using HSLuv's versions of lightness and hue (not raw HSL). This is useful to know the bounds of
     * {@link #chroma(float)}. This should be no greater than 1.0f . Note that this version of chromaLimit() is
     * a little slower than Oklab's version, because this has to go to greater lengths to become accurate.
     * @param hue the hue, typically between 0.0f and 1.0f, to look up
     * @param lightness the lightness, clamped between 0.0f and 1.0f, to look up
     * @return the maximum possible chroma for the given hue and lightness, between 0.0f and 1.0f
     */
    public static float chromaLimit(final float hue, final float lightness) {
        final float h = hue - MathUtils.floor(hue);
        float sin = TrigTools.sin_(h);
        float cos = TrigTools.cos_(h);
        float sub1 = (lightness + 0.16f) / 1.16f;
        sub1 *= sub1 * sub1;
        float sub2 = sub1 > epsilon ? sub1 : lightness / kappa;
        float min = Float.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            float m1 = m[i][0] * sub2, m2 = m[i][1] * sub2, m3 = m[i][2] * sub2;
            for (int t = 0; t < 2; t++) {
                m2 -= t;
                float top1 = 2845.17f * m1 - 948.39f * m3;
                float top2 = (8384.22f * m3 + 7698.60f * m2 + 7317.18f * m1) * lightness;
                float bottom = (6322.60f * m3 - 1264.52f * m2);
                float length = intersectLength(sin, cos, top1 / bottom, top2 / bottom);
                if (length >= 0) min = Math.min(min, length);
            }
        }
        return min;
    }

    private static float intersectLength (float sin, float cos, float line1, float line2) {
        return line2 / (sin - line1 * cos);
    }

    /**
     * Gets the color with the same L as the HSLuv color stored in the given packed float, but the furthest A
     * B from gray possible for that lightness while keeping the same hue as the given color. This is very
     * similar to calling {@link #enrich(float, float)} with a very large {@code change} value.
     * @param packed a packed float color in HSLuv format; does not need to be in-gamut
     * @return the color that is as far from grayscale as this can get while keeping the L and hue of packed
     * @see #limitToGamut(float) You can use limitToGamut() if you only want max saturation for out-of-gamut colors.
     */
    public static float maximizeSaturation(final float packed) {
        return NumberUtils.intBitsToFloat(NumberUtils.floatToRawIntBits(packed) | 0x0000FF00);
    }
    /**
     * Gets the color with the given H, L, and alpha, but ignores the given S and instead uses the furthest S
     * from gray possible for that lightness while keeping the same hue as the given color. This is very
     * similar to calling {@link #enrich(float, float)} with a very large {@code change} value.
     * @param H hue component; will be clamped between 0 and 1 if it isn't already
     * @param S saturation; ignored, and will always be maximized
     * @param L lightness component; will be clamped between 0 and 1 if it isn't already
     * @param alpha alpha component; will be clamped between 0 and 1 if it isn't already
     * @return the color that is as far from grayscale as this can get while keeping the H and L of packed
     */
    public static float maximizeSaturation(float H, float S, float L, float alpha) {
        return clamp(H, 1f, L, alpha);
    }

    /**
     * Gets the hue of the given HSLuv float color, but as HSLuv understands hue rather than how HSL does.
     * This is different from {@link #hue(float)}, which uses HSL. This gives a float between 0 (inclusive)
     * and 1 (exclusive).
     * <br>
     * This is the same as {@link #channelH(float)}.
     *
     * @param packed a packed HSLuv float color
     * @return a float between 0 (inclusive) and 1 (exclusive) that represents hue in the HSLuv color space
     */
    public static float hsluvHue(final float packed) {
        return (NumberUtils.floatToRawIntBits(packed) & 0xff) / 255f;
    }

    /**
     * Gets the saturation of the given HSLuv float color, but as HSLuv understands saturation rather than how HSL
     * does. Saturation here is a fraction of the chroma limit (see {@link #chromaLimit(float, float)}) for a given hue
     * and lightness, and is between 0 and 1. This gives a float between 0 (inclusive) and 1 (inclusive).
     * <br>
     * This is the same as {@link #channelS(float)}.
     *
     * @param packed a packed HSLuv float color
     * @return a float between 0 (inclusive) and 1 (inclusive) that represents saturation in the HSLuv color space
     */
    public static float hsluvSaturation(final float packed) {
        return (NumberUtils.floatToRawIntBits(packed) >>> 8 & 0xff) / 255f;
    }
    /**
     * Gets the lightness of the given HSLuv float color, but as HSLuv understands lightness rather than how HSL does.
     * This is different from {@link #lightness(float)}, which uses HSL. This gives a float between 0 (inclusive)
     * and 1 (inclusive).
     * <br>
     * This is the same as {@link #channelL(float)}.
     *
     * @param packed a packed HSLuv float color
     * @return a float between 0 (inclusive) and 1 (inclusive) that represents lightness in the HSLuv color space
     */
    public static float hsluvLightness(final float packed){
        return (NumberUtils.floatToRawIntBits(packed) >>> 16 & 0xff) / 255f;
    }

    /**
     * A different way to specify a HSLuv color, using hue, chroma, lightness, and alpha something like a normal HSL(A)
     * color but calculating them directly in the HSLuv color space. This has you specify the desired chroma directly,
     * as obtainable with {@link #chroma(float)}, rather than the saturation, which is a fraction of the maximum chroma.
     * Note that this takes a different value for its {@code hue} that the method {@link #hue(float)} produces, just
     * like {@code lightness} and the method {@link #lightness(float)}. The hue is just distributed differently, and the
     * lightness should be equivalent to {@link #channelL(float)}. If you use this to get two colors with the same
     * chroma and lightness, but different hue, then the resulting colors should have similar colorfulness unless one or
     * both chroma values exceeded the gamut limit (you can get this limit with {@link #chromaLimit(float, float)}). If
     * a chroma value given is greater than the chroma limit, this clamps chroma to that limit. You can use
     * {@link #hsluvHue(float)}, {@link #chroma(float)}, and {@link #hsluvLightness(float)} to get the hue, chroma, and
     * lightness values from an existing color that this will understand ({@link #alpha(float)} too).
     * @param hue between 0 and 1, usually, but this will automatically wrap if too high or too low
     * @param chroma will be clamped between 0 and the maximum chroma possible for the given hue and lightness
     * @param lightness will be clamped between 0 and 1
     * @param alpha will be clamped between 0 and 1
     * @return a packed HSLuv float color that tries to match the requested hue, chroma, and lightness
     */
    public static float hsluvByHCL(float hue, float chroma, float lightness, float alpha) {
        hue -= MathUtils.floor(hue);
        alpha = Math.min(Math.max(alpha, 0f), 1f);
        if(lightness <= 0f) return hsluv(hue, 0f, 0f, alpha);
        if(lightness >= 1f) return hsluv(hue, 0f, 1f, alpha);
        return hsluv(hue, Math.max(chroma, 0f) / (chromaLimit(hue, lightness) + 0.0001f), lightness, alpha);
    }

    /**
     * Gets a color as a HSLuv packed float given floats representing HSL hue, saturation, lightness, and opacity.
     * You should usually prefer just using {@link #hsluv(float, float, float, float)} to get colors with these values.
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
     * @return a HSLuv float encoding a color with the given properties
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
     *
     * @param encoded a color as a packed float that can be obtained by {@link #hsluv(float, float, float, float)}
     * @return the saturation of the color from 0.0 (a grayscale color; inclusive) to 1.0 (a bright color, inclusive)
     */
    public static float saturation(final float encoded) {
        final int decoded = NumberUtils.floatToRawIntBits(encoded);
        float H = ((decoded & 0xff) / 255f);
        float S = ((decoded >>> 8 & 0xff) / 255f);
        float L = ((decoded >>> 16 & 0xff) / 255f);

        // HSLuv to Lch
        float C;
        if (L > 0.99999f) {
            L = 1;
            C = 0;
        } else if (L < 0.00001f) {
            L = 0;
            C = 0;
        } else
            C = chromaLimit(H, L) * S;

        // Lch to Luv
        float U = TrigTools.cos_(H) * C;
        float V = TrigTools.sin_(H) * C;

        // Luv to XYZ
        float x, y, z;
        if (L < 0.00001f) {
            x = 0;
            y = 0;
            z = 0;
        } else {
            if (L <= epsilon)
                y = L / kappa;
            else {
                y = (L + 0.16f) / 1.16f;
                y *= y * y;
            }
            float iL = 1f / (13f * L);
            float varU = U * iL + refU;
            float varV = V * iL + refV;
            x = 9 * varU * y / (4 * varV);
            z = (3 * y / varV) - x / 3 - 5 * y;
        }
        final float r = reverseGamma(Math.min(Math.max(+3.2404542f * x + -1.5371385f * y + -0.4985314f * z, 0f), 1f));
        final float g = reverseGamma(Math.min(Math.max(-0.9692660f * x + +1.8760108f * y + +0.0415560f * z, 0f), 1f));
        final float b = reverseGamma(Math.min(Math.max(+0.0556434f * x + -0.2040259f * y + +1.0572252f * z, 0f), 1f));
        float X, Y, W;
        if(g < b) {
            X = b;
            Y = g;
        }
        else {
            X = g;
            Y = b;
        }
        if(r < X) {
            W = r;
        }
        else {
            W = X;
            X = r;
        }
        return X - Math.min(W, Y);
    }

    /**
     * Defined as per HSL; normally you only need {@link #channelL(float)} to get accurate lightness for HSLuv. This
     * ranges from 0.0f (black) to 1.0f (white).
     *
     * @param encoded a packed float HSLuv color
     * @return the lightness of the given color as HSL would calculate it
     */
    public static float lightness(final float encoded) {
        final int decoded = NumberUtils.floatToRawIntBits(encoded);
        float H = ((decoded & 0xff) / 255f);
        float S = ((decoded >>> 8 & 0xff) / 255f);
        float L = ((decoded >>> 16 & 0xff) / 255f);

        // HSLuv to Lch
        float C;
        if (L > 0.99999f) {
            L = 1;
            C = 0;
        } else if (L < 0.00001f) {
            L = 0;
            C = 0;
        } else
            C = chromaLimit(H, L) * S;

        // Lch to Luv
        float U = TrigTools.cos_(H) * C;
        float V = TrigTools.sin_(H) * C;

        // Luv to XYZ
        float x, y, z;
        if (L < 0.00001f) {
            x = 0;
            y = 0;
            z = 0;
        } else {
            if (L <= epsilon)
                y = L / kappa;
            else {
                y = (L + 0.16f) / 1.16f;
                y *= y * y;
            }
            float iL = 1f / (13f * L);
            float varU = U * iL + refU;
            float varV = V * iL + refV;
            x = 9 * varU * y / (4 * varV);
            z = (3 * y / varV) - x / 3 - 5 * y;
        }
        final float r = reverseGamma(Math.min(Math.max(+3.2404542f * x + -1.5371385f * y + -0.4985314f * z, 0f), 1f));
        final float g = reverseGamma(Math.min(Math.max(-0.9692660f * x + +1.8760108f * y + +0.0415560f * z, 0f), 1f));
        final float b = reverseGamma(Math.min(Math.max(+0.0556434f * x + -0.2040259f * y + +1.0572252f * z, 0f), 1f));
        float X, Y, W;
        if(g < b) {
            X = b;
            Y = g;
        }
        else {
            X = g;
            Y = b;
        }
        if(r < X) {
            W = r;
        }
        else {
            W = X;
            X = r;
        }
        float d = X - Math.min(W, Y);
        return X * (1f - 0.5f * d / (X + 1e-10f));
    }

    /**
     * Gets the hue of the given encoded color, as a float from 0f (inclusive, red and approaching orange if increased)
     * to 1f (exclusive, red and approaching purple if decreased).
     *
     * @param encoded a color as a packed float that can be obtained by {@link #hsluv(float, float, float, float)}
     * @return The hue of the color from 0.0 (red, inclusive) towards orange, then yellow, and
     * eventually to purple before looping back to almost the same red (1.0, exclusive)
     */
    public static float hue(final float encoded) {
        final int decoded = NumberUtils.floatToRawIntBits(encoded);
        float H = ((decoded & 0xff) / 255f);
        float S = ((decoded >>> 8 & 0xff) / 255f);
        float L = ((decoded >>> 16 & 0xff) / 255f);

        // HSLuv to Lch
        float C;
        if (L > 0.99999f) {
            L = 1;
            C = 0;
        } else if (L < 0.00001f) {
            L = 0;
            C = 0;
        } else
            C = chromaLimit(H, L) * S;

        // Lch to Luv
        float U = TrigTools.cos_(H) * C;
        float V = TrigTools.sin_(H) * C;

        // Luv to XYZ
        float x, y, z;
        if (L < 0.00001f) {
            x = 0;
            y = 0;
            z = 0;
        } else {
            if (L <= epsilon)
                y = L / kappa;
            else {
                y = (L + 0.16f) / 1.16f;
                y *= y * y;
            }
            float iL = 1f / (13f * L);
            float varU = U * iL + refU;
            float varV = V * iL + refV;
            x = 9 * varU * y / (4 * varV);
            z = (3 * y / varV) - x / 3 - 5 * y;
        }
        final float r = reverseGamma(Math.min(Math.max(+3.2404542f * x + -1.5371385f * y + -0.4985314f * z, 0f), 1f));
        final float g = reverseGamma(Math.min(Math.max(-0.9692660f * x + +1.8760108f * y + +0.0415560f * z, 0f), 1f));
        final float b = reverseGamma(Math.min(Math.max(+0.0556434f * x + -0.2040259f * y + +1.0572252f * z, 0f), 1f));
        float X, Y, Z, W;
        if(g < b) {
            X = b;
            Y = g;
            Z = -1f;
            W = 2f / 3f;
        }
        else {
            X = g;
            Y = b;
            Z = 0f;
            W = -1f / 3f;
        }
        if(r < X) {
            Z = W;
            W = r;
        }
        else {
            W = X;
            X = r;
        }
        float d = X - Math.min(W, Y);
        return Math.abs(Z + (W - Y) / (6f * d + 1e-10f));
    }
    /**
     * Gets a variation on the packed float color basis as another packed float that has its hue, saturation, lightness,
     * and opacity adjusted by the specified amounts. Note that this edits the color in HSL space, not HSLuv! Takes
     * floats representing the amounts of change to apply to hue, saturation, lightness, and opacity; these can be
     * between -1f and 1f. Returns a float that can be used as a packed or encoded color with methods like
     * {@link com.badlogic.gdx.graphics.g2d.Batch#setPackedColor(float)}. The float is likely to be different than the
     * result of {@code basis} unless hue, saturation, lightness, and opacity are all 0.
     * This won't allocate any objects.
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
        final int decoded = NumberUtils.floatToRawIntBits(basis);
        final float li = Math.min(Math.max(light + (decoded >>> 16 & 0xff) / 255f, 0f), 1f);
        opacity = Math.min(Math.max(opacity + (decoded >>> 25) / 127f, 0f), 1f);
        if (li <= 0.001f)
            return NumberUtils.intBitsToFloat((((int) (opacity * 255f) << 24) & 0xFE000000));
        float H = ((decoded & 0xff) / 255f);
        float S = ((decoded >>> 8 & 0xff) / 255f);
        float L = li;
//        float L = (1f/1.16f)*(li + 0.16f);

        // HSLuv to Lch
        float C;
        if (L > 0.99999f) {
            L = 1;
            C = 0;
        } else
            C = chromaLimit(H, L) * S;

        // Lch to Luv
        float U = TrigTools.cos_(H) * C;
        float V = TrigTools.sin_(H) * C;

        // Luv to XYZ
        float x, y, z;
        if (L <= epsilon)
            y = L / kappa;
        else {
            y = (L + 0.16f) / 1.16f;
            y *= y * y;
        }
        float iL = 1f / (13f * L);
        float varU = U * iL + refU;
        float varV = V * iL + refV;
        x = 9 * varU * y / (4 * varV);
        z = (3 * y / varV) - x / 3 - 5 * y;
        final float r = reverseGamma(Math.min(Math.max(+3.2404542f * x + -1.5371385f * y + -0.4985314f * z, 0f), 1f));
        final float g = reverseGamma(Math.min(Math.max(-0.9692660f * x + +1.8760108f * y + +0.0415560f * z, 0f), 1f));
        final float b = reverseGamma(Math.min(Math.max(+0.0556434f * x + -0.2040259f * y + +1.0572252f * z, 0f), 1f));
        float X, Y, Z, W;
        if(g < b) {
            X = b;
            Y = g;
            Z = -1f;
            W = 2f / 3f;
        }
        else {
            X = g;
            Y = b;
            Z = 0f;
            W = -1f / 3f;
        }
        if(r < X) {
            Z = W;
            W = r;
        }
        else {
            W = X;
            X = r;
        }
        final float d = X - Math.min(W, Y);
        final float lum = X * (1f - 0.5f * d / (X + 1e-10f));
        hue += Math.abs(Z + (W - Y) / (6f * d + 1e-10f)) + 1f;
        saturation += (X - lum) / (Math.min(lum, 1f - lum) + 1e-10f);
        return fromRGBA(FloatColors.hsl2rgb(hue - (int)hue, Math.min(Math.max(saturation, 0f), 1f), lum, opacity));
    }


    /**
     * Given a packed float HSLuv color, this edits its H, S, L, and alpha channels by adding the corresponding "add"
     * parameter and then clamping. This returns a different float value (of course, the given float can't be edited
     * in-place). You can give a value of 0 for any "add" parameter you want to stay unchanged. H is wrapped, while S,
     * L, and alpha are clamped.
     * @param encoded a packed float HSLuv color
     * @param addH how much to add to the H channel; typically in the -1 to 1 range
     * @param addS how much to add to the S channel; typically in the -1 to 1 range
     * @param addL how much to add to the L channel; typically in the -1 to 1 range
     * @param addAlpha how much to add to the alpha channel; typically in the -1 to 1 range
     * @return a packed float HSLuv color with the requested edits applied to {@code encoded}
     */
    public static float editHSLuv(float encoded, float addH, float addS, float addL, float addAlpha) {
        return editHSLuv(encoded, addH, addS, addL, addAlpha, 1f, 1f, 1f, 1f);
    }
    /**
     * Given a packed float HSLuv color, this edits its H, S, L, and alpha channels by first multiplying each channel
     * by the corresponding "mul" parameter and then adding the corresponding "add" parameter, before clamping (this
     * wraps H instead of clamping it). This means the lightness value {@code L} is multiplied by {@code mulL}, then has
     * {@code addL} added, and then is clamped to the normal range for L (0 to 1). This returns a different float value
     * (of course, the given float can't be edited in-place). You can give a value of 0 for any "add" parameter you want
     * to stay unchanged, or a value of 1 for any "mul" parameter that shouldn't change. You can multiply S by 0 to make
     * a grayscale color, or by a large value to make any non-grayscale color more vibrant. Multiplying H generally
     * isn't very useful, but adding to H can be used to do hue cycling (because H wraps).
     * @param encoded a packed float HSLuv color
     * @param addH how much to add to the H channel; typically in the -1 to 1 range
     * @param addS how much to add to the S channel; typically in the -1 to 1 range
     * @param addL how much to add to the L channel; typically in the -1 to 1 range
     * @param addAlpha how much to add to the alpha channel; typically in the -1 to 1 range
     * @param mulH how much to multiply the H channel by; should be non-negative (not always)
     * @param mulS how much to multiply the S channel by; usually non-negative
     * @param mulL how much to multiply the L channel by; usually non-negative (not always)
     * @param mulAlpha how much to multiply the alpha channel by; should be non-negative
     * @return a packed float HSLuv color with the requested edits applied to {@code encoded}
     */
    public static float editHSLuv(float encoded, float addH, float addS, float addL, float addAlpha,
                                  float mulH, float mulS, float mulL, float mulAlpha) {
        final int decoded = NumberUtils.floatToRawIntBits(encoded);
        float H = (decoded & 0xff) / 255f;
        float S = (decoded >>> 8 & 0xff) / 255f;
        float L = (decoded >>> 16 & 0xff) / 255f;
        float alpha = (decoded >>> 25) / 127f;

        H = H * mulH + addH;
        H -= MathUtils.floor(H);
        S = Math.min(Math.max(S * mulS + addS, 0f), 1f);
        L = Math.min(Math.max(L * mulL + addL, 0f), 1f);
        alpha = Math.min(Math.max(alpha * mulAlpha + addAlpha, 0f), 1f);
        return hsluv(H, S, L, alpha);
    }

    /**
     * The "H" channel of the given packed float in HSLuv format, which is its hue; ranges from 0.0f to 1.0f .
     * You can edit the H of a color with {@link #rotateH(float, float)}.
     *
     * @param encoded a color encoded as a packed float, as by {@link #hsluv(float, float, float, float)}
     * @return the H value as a float from 0.0f to 1.0f
     */
    public static float channelH(final float encoded)
    {
        return (NumberUtils.floatToRawIntBits(encoded) & 0xff) / 255f;
    }

    /**
     * The "S" channel of the given packed float in HSLuv format, which is its saturation; ranges from 0.0f to
     * 1.0f . You can edit the S of a color with {@link #enrich(float, float)} and {@link #dullen(float, float)}.
     * @param encoded a color encoded as a packed float, as by {@link #hsluv(float, float, float, float)}
     * @return the S value as a float from 0.0f to 1.0f
     */
    public static float channelS(final float encoded)
    {
        return ((NumberUtils.floatToRawIntBits(encoded) >>> 8 & 0xff)) / 255f;
    }

    /**
     * The "L" channel of the given packed float in HSLuv format, which is its lightness; ranges from 0.0f to
     * 1.0f . You can edit the L of a color with {@link #lighten(float, float)} and {@link #darken(float, float)}.
     *
     * @param encoded a color encoded as a packed float, as by {@link #hsluv(float, float, float, float)}
     * @return the L value as a float from 0.0f to 1.0f
     */
    public static float channelL(final float encoded)
    {
        return ((NumberUtils.floatToRawIntBits(encoded) >>> 16 & 0xff)) / 255f;
    }

    /**
     * Interpolates from the packed float color start towards white by change. While change should be between 0f (return
     * start as-is) and 1f (return white), start should be a packed color, as from
     * {@link #hsluv(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
     * is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp
     * towards white. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha, hue, and
     * saturation of start as-is.
     * @see #darken(float, float) the counterpart method that darkens a float color
     * @param start the starting color as a packed float
     * @param change how much to go from start toward white, as a float between 0 and 1; higher means closer to white
     * @return a packed float that represents a color between start and white
     */
    public static float lighten(final float start, final float change) {
        final int s = NumberUtils.floatToRawIntBits(start), t = s >>> 16 & 0xFF, other = s & 0xFE00FFFF;
        return NumberUtils.intBitsToFloat(((int) (t + (0xFF - t) * change) << 16 & 0xFF0000) | other);
    }

    /**
     * Interpolates from the packed float color start towards black by change. While change should be between 0f (return
     * start as-is) and 1f (return black), start should be a packed color, as from
     * {@link #hsluv(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
     * is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp
     * towards black. Unlike {@link FloatColors#lerpFloatColors(float, float, float)}, this keeps the alpha, hue, and
     * saturation of start as-is.
     * @see #lighten(float, float) the counterpart method that lightens a float color
     * @param start the starting color as a packed float
     * @param change how much to go from start toward black, as a float between 0 and 1; higher means closer to black
     * @return a packed float that represents a color between start and black
     */
    public static float darken(final float start, final float change) {
        final int s = NumberUtils.floatToRawIntBits(start), t = s >>> 16 & 0xFF, other = s & 0xFE00FFFF;
        return NumberUtils.intBitsToFloat(((int) (t * (1f - change)) & 0xFF) << 16 | other);
    }

    /**
     * Moves the color of {@code start} away from grayscale by change (saturating the color). While change
     * should be between 0f (return start as-is) and 1f (return maximally saturated), start should be a packed color, as
     * from {@link #hsluv(float, float, float, float)}. This changes only S, and won't change hue, lightness, or alpha.
     * @see #dullen(float, float) the counterpart method that makes a float color less saturated
     * @param start the starting color as a packed float
     * @param change how much to change start to a saturated color, as a float between 0 and 1; higher means a more saturated result
     * @return a packed float that represents a color between start and a saturated color
     */
    public static float enrich(final float start, final float change) {
        final int s = NumberUtils.floatToRawIntBits(start), p = s >>> 8 & 0xFF, other = s & 0xFEFF00FF;
        return NumberUtils.intBitsToFloat(((int) (p + (0xFF - p) * change) << 8 & 0xFF00) | other);
    }

    /**
     * Brings the color of {@code start} closer to grayscale by {@code change} (desaturating the color). While
     * change should be between 0f (return start as-is) and 1f (return fully gray), start should be a packed color, as
     * from {@link #hsluv(float, float, float, float)}. This changes only S, and won't change hue, lightness, or alpha.
     * @see #enrich(float, float) the counterpart method that makes a float color more saturated
     * @param start the starting color as a packed float
     * @param change how much to change start to a desaturated color, as a float between 0 and 1; higher means a less saturated result
     * @return a packed float that represents a color between start and a desaturated color
     */
    public static float dullen(final float start, final float change) {
        final int s = NumberUtils.floatToRawIntBits(start), p = s >>> 8 & 0xFF, other = s & 0xFEFF00FF;
        return NumberUtils.intBitsToFloat(((int) (p * (1f - change)) & 0xFF) << 8 | other);
    }

    /**
     * Cycles the hue of the packed float color by change. If change is 0f, this returns start as-is. If change is
     * positive, this rotates from red to orange to yellow to green, and so on. If change is negative, this instead
     * rotates from green to yellow to orange to red.  A change value is typically between -1f and 1f; if change is
     * exactly an integer, then this will wrap around in a perfect circle and produce no change. The start value should
     * be a packed color, as from {@link #hsluv(float, float, float, float)}. This is a good way to reduce allocations
     * of temporary Colors. This only changes H, and won't change saturation, lightness, or alpha.
     * @param start the starting color as a packed float
     * @param change how much to rotate hue by, as a float typically between -1 and 1; further from 0 rotates more
     * @return a packed float that represents a color like start but with a rotated hue
     */
    public static float rotateH(final float start, final float change) {
        final int s = NumberUtils.floatToRawIntBits(start), i = s & 0xFF, other = s & 0xFEFFFF00;
        return NumberUtils.intBitsToFloat(((int) (i + (256f * change)) & 0xFF) | other);
    }

    /**
     * Interpolates from the packed float color start towards that color made opaque by change. While change should be
     * between 0f (return start as-is) and 1f (return start with full alpha), start should be a packed color, as from
     * {@link #hsluv(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors, and
     * is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to lerp
     * towards transparent. This won't change the H, S, or L of the color.
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
     * {@link #hsluv(float, float, float, float)}. This is a good way to reduce allocations of temporary Colors,
     * and is a little more efficient and clear than using {@link FloatColors#lerpFloatColors(float, float, float)} to
     * lerp towards transparent. This won't change the H, S, or L of the color.
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
     * Given a packed float HSLuv color {@code mainColor} and another HSLuv color that it should be made to contrast
     * with, gets a packed float HSLuv color with roughly inverted lightness but the same hue, saturation, and alpha.
     * This won't ever produce black or other very dark colors, and also has a gap in the range it produces for
     * lightness values between 0.5 and 0.55. That allows most of the colors this method produces to contrast well as a
     * foreground when displayed on a background of {@code contrastingColor}, or vice versa. This will leave the
     * lightness unchanged if the hues of the contrastingColor and those of the mainColor are already very different.
     * This has nothing to do with the contrast channel of the tweak in ColorfulBatch; where that part of the tweak can
     * make too-similar lightness values further apart by just a little, this makes a modification on {@code mainColor}
     * to maximize its lightness difference from {@code contrastingColor} without losing its other qualities.
     * @param mainColor a packed float color, as produced by {@link #hsluv(float, float, float, float)}; this is the color that will be adjusted
     * @param contrastingColor a packed float color, as produced by {@link #hsluv(float, float, float, float)}; the adjusted mainColor will contrast with this
     * @return a different HSLuv packed float color, based on mainColor but with potentially very different lightness
     */
    public static float inverseLightness(final float mainColor, final float contrastingColor)
    {
        final int bits = NumberUtils.floatToRawIntBits(mainColor),
                contrastBits = NumberUtils.floatToRawIntBits(contrastingColor),
                H = (bits & 0xff),
//                S = (bits >>> 8 & 0xff),
                L = (bits >>> 16 & 0xff),
                cH = (contrastBits & 0xff),
//                cS = (contrastBits >>> 8 & 0xff),
                cL = (contrastBits >>> 16 & 0xff);
        if(Math.abs(H - cH) >= 90)
            return mainColor;
        return NumberUtils.intBitsToFloat((bits & 0xFE00FFFF) | (int) (cL < 128 ? L * 0.45f + 128 : 128 - L * 0.45f) << 16);
    }

    /**
     * Given a packed float HSLuv color {@code mainColor} and another HSLuv color that it should be made to contrast
     * with, gets a packed float HSLuv color with L that should be quite different from {@code contrastingColor}'s L,
     * but the same hue, saturation, and opacity. This allows most of the colors this method produces to contrast well
     * as a foreground when displayed on a background of {@code contrastingColor}, or vice versa.
     * <br>
     * This is similar to {@link #inverseLightness(float, float)}, but is considerably simpler, and this method will
     * change the lightness of mainColor when the two given colors have close lightness but distant hues. Because it
     * averages the original L of mainColor with the modified one, this tends to not produce harsh color changes.
     * @param mainColor a packed HSLuv float color; this is the color that will be adjusted
     * @param contrastingColor a packed HSLuv float color; the adjusted mainColor will contrast with the L of this
     * @return a different packed HSLuv float color, based on mainColor but typically with different lightness
     */
    public static float differentiateLightness(final float mainColor, final float contrastingColor)
    {
        final int main = NumberUtils.floatToRawIntBits(mainColor), contrast = NumberUtils.floatToRawIntBits(contrastingColor);
        return NumberUtils.intBitsToFloat((main & 0xFE00FFFF) | ((contrast >>> 16) + 128 & 0xFF) + (main >>> 16 & 0xFF) >>> 1);
    }

    /**
     * Pretty simple; adds 0.5 to the given color's L and wraps it around if it would go above 1.0, then averages that
     * with the original L. This means light colors become darker, and dark colors become lighter, with almost all
     * results in the middle-range of possible lightness.
     * <br>
     * Calling {@code offsetLightness(mainColor)} is the same as calling
     * {@code ColorTools.differentiateLightness(mainColor, mainColor)}.
     * @param mainColor a packed HSLuv float color
     * @return a different packed HSLuv float color, with its L channel changed.
     */
    public static float offsetLightness(final float mainColor) {
        final int decoded = NumberUtils.floatToRawIntBits(mainColor);
        return NumberUtils.intBitsToFloat((decoded & 0xFE00FFFF) | ((decoded >>> 16) + 128 & 0xFF) + (decoded >>> 16 & 0xFF) >>> 1);
    }

    /**
     * Makes the additive HSLuv color stored in {@code color} cause less of a change when used as a tint, as if it were
     * mixed with neutral gray. When {@code fraction} is 1.0, this returns color unchanged; when fraction is 0.0, it
     * returns {@code Palette#GRAY}, and when it is in-between 0.0 and 1.0 it returns something between the two. This is
     * meant for things like area of effect abilities that make smaller color changes toward their periphery. This only
     * affects the saturation and lightness of the color; its hue and alpha are unchanged.
     * @param color a color that should have its tinting effect potentially weakened
     * @param fraction how much of {@code color} should be kept, from 0.0 to 1.0
     * @return a HSLuv float color between gray and {@code color}
     */
    public static float lessenChange(final float color, float fraction) {
        final int e = NumberUtils.floatToRawIntBits(color),
                sS = 0x80, sL = 0x80,
                eH = (e & 0xFF), eS = (e >>> 8) & 0xFF, eL = (e >>> 16) & 0xFF, eAlpha = e >>> 24 & 0xFE;
        return NumberUtils.intBitsToFloat(eH
                | (((int) (sS + fraction * (eS - sS)) & 0xFF) << 8)
                | (((int) (sL + fraction * (eL - sL)) & 0xFF) << 16)
                | (eAlpha << 24));
    }

    /**
     * Returns true always; HSLuv colors are always in-gamut.
     * @param packed a packed float color as HSLuv
     * @return true
     */
    public static boolean inGamut(final float packed)
    {
        return true;
    }

    /**
     * Returns true if S and L are each between 0 and 1; if valid, HSLuv colors are always in-gamut.
     * @param H hue, as an unbounded float
     * @param S saturation, as a float from 0 to 1
     * @param L lightness, as a float from 0 to 1
     * @return true if S and L are both between 0 and 1
     */
    public static boolean inGamut(float H, float S, float L)
    {
        return (S >= 0f && S <= 1.0f && L >= 0f && L <= 1.0f);
    }

    /**
     * Returns its argument unchanged; HSLuv colors are always in-gamut.
     * @param packed a packed float color in HSLuv format
     * @return {@code packed}, unchanged
     */
    public static float limitToGamut(final float packed) {
        return packed;
    }

    /**
     * Identical to calling {@link #clamp(float, float, float, float)} with 1f as its last parameter.
     * @param H hue; will be wrapped between 0 and 1
     * @param S saturation; will be clamped between 0 and 1 if it isn't already
     * @param L lightness; will be clamped between 0 and 1 if it isn't already
     * @return an HSLuv color with the specified channel values, wrapped or clamped as appropriate
     */
    public static float limitToGamut(float H, float S, float L) {
        return clamp(H - MathUtils.floor(H), S, L, 1f);
    }
    /**
     * Identical to calling {@link #clamp(float, float, float, float)} with 1f as its last parameter.
     * @param H hue; will be wrapped between 0 and 1
     * @param S saturation; will be clamped between 0 and 1 if it isn't already
     * @param L lightness; will be clamped between 0 and 1 if it isn't already
     * @param alpha opacity; will be clamped between 0 and 1 if it isn't already
     * @return an HSLuv color with the specified channel values, wrapped or clamped as appropriate
     */
    public static float limitToGamut(float H, float S, float L, float alpha) {
        return clamp(H, S, L, alpha);
    }

    /**
     * Makes a quasi-randomly-edited variant on the given {@code color}, allowing typically a small amount of
     * {@code variance} (such as 0.05 to 0.25) between the given color and what this can return. The {@code seed} should
     * be different each time this is called, and can be obtained from a random number generator to make the colors more
     * random, or can be incremented on each call. If the seed is only incremented or decremented, then this shouldn't
     * produce two similar colors in a row unless variance is very small. The variance affects the H, S, and L of the
     * generated color, and each of those channels can go up or down by the given variance as long as the total distance
     * isn't greater than the variance.
     * @param color a packed float color, as produced by {@link #hsluv(float, float, float, float)}
     * @param seed a long seed that should be different on each call; should not be 0
     * @param variance max amount of difference between the given color and the generated color; always less than 1
     * @return a generated packed float color that should be at least somewhat different from {@code color}
     */
    public static float randomEdit(final float color, long seed, final float variance) {
        final int decoded = NumberUtils.floatToRawIntBits(color);
        float H = (decoded & 0xff) / 255f;
        float S = (decoded >>> 8 & 0xff) / 255f;
        float L = (decoded >>> 16 & 0xff) / 255f;
        final float limit = variance * variance;
        float dist, x, y, z;
        for (int j = 0; j < 50; j++) {
            x = (((seed * 0xD1B54A32D192ED03L >>> 41) - 0x7FFFFFp-1f) * 0x1p-22f) * variance;
            y = (((seed * 0xABC98388FB8FAC03L >>> 41) - 0x7FFFFFp-1f) * 0x1p-22f) * variance;
            z = (((seed * 0x8CB92BA72F3D8DD7L >>> 41) - 0x7FFFFFp-1f) * 0x1p-22f) * variance;
            seed += 0x9E3779B97F4A7C15L;
            dist = x * x + y * y + z * z;
            if(dist <= limit)
                return clamp(H + x, S + y, L + z, (decoded >>> 25) / 127f);
        }
        return color;
    }

    /**
     * Produces a random packed float color that is always in-gamut and should be uniformly distributed.
     * @param random a Random object (preferably a subclass of Random, like {@link com.badlogic.gdx.math.RandomXS128})
     * @return a packed float color that is always in-gamut
     */
    public static float randomColor(Random random) {
        return hsluv(random.nextFloat(), random.nextFloat(), random.nextFloat(), 1f);
    }

}
