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

package com.github.tommyettinger.colorful;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.NumberUtils;
import com.github.tommyettinger.colorful.ycwcm.ColorTools;

/**
 * Various utility methods for working with colors encoded as packed floats in any of the formats this can use.
 * <br>
 * Created by Tommy Ettinger on 12/2/2019.
 */
public class FloatColors {

    /**
     * Converts a packed float in HSLA format (hue, saturation, lightness, alpha) to a packed float in RGBA format.
     * @param hsla an HSLA-format packed float
     * @return an RGBA-format packed float
     */
    public static float hsl2rgb(final float hsla) {
        final int decoded = NumberUtils.floatToRawIntBits(hsla);
        return hsl2rgb((decoded & 0xFF) / 255f, (decoded >>> 8 & 0xFF) / 255f, (decoded >>> 16 & 0xFF) / 255f, (decoded >>> 24 & 0xFE) / 255f);
    }

    /**
     * Converts the four HSLA components, each in the 0.0 to 1.0 range, to a packed float in RGBA format.
     * @param h hue, from 0.0 to 1.0
     * @param s saturation, from 0.0 to 1.0
     * @param l lightness, from 0.0 to 1.0
     * @param a alpha, from 0.0 to 1.0
     * @return an RGBA-format packed float
     */
    public static float hsl2rgb(final float h, final float s, final float l, final float a){
        float x = Math.min(Math.max(Math.abs(h * 6f - 3f) - 1f, 0f), 1f);
        float y = h + (2f / 3f);
        float z = h + (1f / 3f);
        y -= (int)y;
        z -= (int)z;
        y = Math.min(Math.max(Math.abs(y * 6f - 3f) - 1f, 0f), 1f);
        z = Math.min(Math.max(Math.abs(z * 6f - 3f) - 1f, 0f), 1f);
        float v = (l + s * Math.min(l, 1f - l));
        float d = 2f * (1f - l / (v + 1e-10f));
        v *= 255f;
        return NumberUtils.intBitsToFloat(
                (int)(a * 127f) << 25
                        | (int)(v * MathUtils.lerp(1f, z, d)) << 16
                        | (int)(v * MathUtils.lerp(1f, y, d)) << 8
                        | (int)(v * MathUtils.lerp(1f, x, d))
        );
    }

    /**
     * Converts a packed float in HSLA format (hue, saturation, lightness, alpha) to a packed float in RGBA format.
     * @param hsla an HSLA-format packed float
     * @return an RGBA-format packed float
     */
    public static int hsl2rgbInt(final int hsla) {
        return hsl2rgbInt((hsla >>> 24 & 0xFF) * (1f / 255f), (hsla >>> 16 & 0xFF) * (1f / 255f), (hsla >>> 16 & 0xFF) * (1f / 255f), (hsla >>> 1 & 0x7F) * (1f / 127f));
    }

    /**
     * Converts the four HSLA components, each in the 0.0 to 1.0 range, to an int in RGBA8888 format.
     * <a href="https://stackoverflow.com/a/64090995">From this StackOverflow answer by Kamil Kiełczewski</a>
     * @param h hue, from 0.0 to 1.0
     * @param s saturation, from 0.0 to 1.0
     * @param l lightness, from 0.0 to 1.0
     * @param a alpha, from 0.0 to 1.0
     * @return an RGBA8888-format int
     */
    public static int hsl2rgbInt(float h, float s, float l, float a) {
/*
   let a=s*Math.min(l,1-l);
   let f= (n,k=(n+h/30)%12) => l - a*Math.max(Math.min(k-3,9-k,1),-1);
   return [f(0),f(8),f(4)];}
 */
        float c = s * Math.min(l, 1f-l) * 3071.999f; // 12 * 256, minus epsilon
        l *= 255.999f;
        float r = h;                        r = l - c * Math.max(Math.min(Math.min(r - 0.25f, 0.75f - r), 1f/12f), -1f/12f);
        float g = h + (2f/3f); g -= (int)g; g = l - c * Math.max(Math.min(Math.min(g - 0.25f, 0.75f - g), 1f/12f), -1f/12f);
        float b = h + (1f/3f); b -= (int)b; b = l - c * Math.max(Math.min(Math.min(b - 0.25f, 0.75f - b), 1f/12f), -1f/12f);
        return (int)(r) << 24 | (int)(g) << 16 | (int)(b) << 8 | (int)(a * 255.999f);
    }

    /**
     * Converts the four RGBA components, each in the 0.0 to 1.0 range, to an int in "HSLA format" (hue, saturation,
     * lightness, alpha), which isn't one of the regular formats this supports but can be useful for conversions.
     * <br>
     * <a href="https://stackoverflow.com/a/64090995">From this StackOverflow answer by Kamil Kiełczewski</a>
     * @param r red, from 0.0 to 1.0
     * @param g green, from 0.0 to 1.0
     * @param b blue, from 0.0 to 1.0
     * @param a alpha, from 0.0 to 1.0
     * @return an "HSLA-format" int
     */
    public static int rgb2hslInt(float r, float g, float b, float a) {
/*
  let max=Math.max(r,g,b), min=Math.min(r,g,b), delta=max-min, f=(1-Math.abs(max+min-1));
  let h= delta && ((max==r) ? (g-b)/delta : ((max==g) ? 2+(b-r)/delta : 4+(r-g)/delta));
  return [60*(h<0?h+6:h), f ? delta/f : 0, (max+min)/2];
 */
        float max = Math.max(Math.max(r, g), b), min = Math.min(Math.min(r, g), b);
        float delta = max - min;
        if(MathUtils.isZero(delta))
            return (int)(max * 255.999f) << 8 | (int)(a * 255.999f);
        float iDelta = 0.16666667f / delta;
        float hue = 6f + ((max == r) ? (g - b) * iDelta : (max == g) ? (1f/3f) + (b - r) * iDelta : (2f/3f) + (r - g) * iDelta);
        return (int)((hue - (int)(hue)) * 255.999f) << 24
                | (int)(delta / (1f - Math.abs(max + min - 1f)) * 255.999f) << 16
                | (int)((max + min) * 127.999f) << 8
                | (int)(a * 255.999f);
    }
    /**
     * Converts the four HSLA components, each in the 0.0 to 1.0 range, to RGBA and assigns them into changing.
     * @param changing a non-null Color that will be modified
     * @param h hue, from 0.0 to 1.0
     * @param s saturation, from 0.0 to 1.0
     * @param l lightness, from 0.0 to 1.0
     * @param a alpha, from 0.0 to 1.0
     * @return changing, after assignment
     */
    public static Color hslColor(Color changing, final float h, final float s, final float l, final float a){
        float x = Math.min(Math.max(Math.abs(h * 6f - 3f) - 1f, 0f), 1f);
        float y = h + (2f / 3f);
        float z = h + (1f / 3f);
        y -= (int)y;
        z -= (int)z;
        y = Math.min(Math.max(Math.abs(y * 6f - 3f) - 1f, 0f), 1f);
        z = Math.min(Math.max(Math.abs(z * 6f - 3f) - 1f, 0f), 1f);
        float v = (l + s * Math.min(l, 1f - l));
        float d = 2f * (1f - l / (v + 1e-10f));
        changing.set(v * MathUtils.lerp(1f, x, d), v * MathUtils.lerp(1f, y, d), v * MathUtils.lerp(1f, z, d), a);
        return changing;
    }

    /**
     * Converts a packed float in RGBA format to a packed float in "HSLA format" (hue, saturation, lightness, alpha),
     * which isn't one of the regular formats this supports but can be useful for conversions.
     * @param rgba an RGBA-format packed float
     * @return an "HSLA-format" packed float
     */
    public static float rgb2hsl(final float rgba) {
        final int decoded = NumberUtils.floatToRawIntBits(rgba);
        return rgb2hsl((decoded & 0xFF) / 255f, (decoded >>> 8 & 0xFF) / 255f, (decoded >>> 16 & 0xFF) / 255f, (decoded >>> 24 & 0xFE) / 255f);
    }

    /**
     * Converts the four RGBA components, each in the 0.0 to 1.0 range, to a packed float in "HSLA format" (hue,
     * saturation, lightness, alpha), which isn't one of the regular formats this supports but can be useful for
     * conversions.
     * @param r red, from 0.0 to 1.0
     * @param g green, from 0.0 to 1.0
     * @param b blue, from 0.0 to 1.0
     * @param a alpha, from 0.0 to 1.0
     * @return an "HSLA-format" packed float
     */
    public static float rgb2hsl(final float r, final float g, final float b, final float a) {
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
        return Color.toFloatBits(Math.abs(z + (w - y) / (6f * d + 1e-10f)), (x - l) / (Math.min(l, 1f - l) + 1e-10f), l, a);
    }


    /**
     * Given a color stored as a packed float and an alpha multiplier to affect that color (between 0f and 1f, inclusive
     * and clamped in that range), this makes a packed float color that has the same red, green, and blue channels but
     * has its own alpha multiplied by {@code alpha}, without constructing any objects along the way. If you want to set
     * the alpha without considering its current value, you can use {@link #setAlpha(float, float)}
     *
     * @param encodedColor a color encoded as a packed float, as by {@link ColorTools#ycwcm(float, float, float, float)}
     *                     or {@link com.github.tommyettinger.colorful.ipt.ColorTools#ipt(float, float, float, float)}
     * @param alpha  between 0.0 and 1.0 inclusive, the alpha to multiply the color's own alpha by
     * @return a color encoded as a packed float, using color's RGB channels but with its A channel times {@code alpha}
     */
    public static float multiplyAlpha(final float encodedColor, final float alpha) {
        final int bits = NumberUtils.floatToRawIntBits(encodedColor);
        return NumberUtils.intBitsToFloat(bits & 0xFFFFFF
                | (Math.min(Math.max((int) ((bits >>> 24) * alpha), 0), 255) << 24 & 0xFE000000));
    }

    /**
     * Given a color stored as a packed float, and a desired alpha to set for that color (between 0.0 and 1.0, inclusive
     * and clamped in that range), this makes a new float that has the same red, green, and blue channels but has been
     * set to the given alpha, without constructing any objects along the way. This does not consider the current alpha
     * of the encoded color; if you want to do that, you can use {@link #multiplyAlpha(float, float)}.
     *
     * @param encodedColor a color encoded as a packed float, as by {@link ColorTools#ycwcm(float, float, float, float)}
     *                     or {@link com.github.tommyettinger.colorful.ipt.ColorTools#ipt(float, float, float, float)}
     * @param alpha        between 0.0 and 1.0 inclusive, the alpha to set into the returned packed color
     * @return another color encoded as a packed float, using encodedColor's RGB channels and the given alpha
     */
    public static float setAlpha(final float encodedColor, final float alpha) {
        return NumberUtils.intBitsToFloat(NumberUtils.floatToRawIntBits(encodedColor) & 0xFFFFFF
                | (Math.min(Math.max((int) (255f * alpha), 0), 255) << 24 & 0xFE000000));
    }

    /**
     * Interpolates from the packed float color start towards end by change. Both start and end should be packed colors,
     * as from {@link com.github.tommyettinger.colorful.oklab.ColorTools#oklab(float, float, float, float)} or
     * {@link com.github.tommyettinger.colorful.ipt.ColorTools#ipt(float, float, float, float)}, and change can be
     * between 0f (keep start) and 1f (only use end). Both start and end should use the same color space; that is, both
     * could be produced using Oklab, both could be produced using IPT, or both could be libGDX-native RGB, but they
     * can't mix. This is a good way to reduce allocations of temporary Colors.
     * @param start the starting color as a packed float
     * @param end the target color as a packed float
     * @param change how much to go from start toward end, as a float between 0 and 1; higher means closer to end
     * @return a packed float that represents a color between start and end
     */
    public static float lerpFloatColors(final float start, final float end, float change) {
        final int s = NumberUtils.floatToRawIntBits(start), e = NumberUtils.floatToRawIntBits(end),
                ys = (s & 0xFF), cws = (s >>> 8) & 0xFF, cms = (s >>> 16) & 0xFF, as = s >>> 24 & 0xFE,
                ye = (e & 0xFF), cwe = (e >>> 8) & 0xFF, cme = (e >>> 16) & 0xFF, ae = e >>> 24 & 0xFE;
        return NumberUtils.intBitsToFloat(((int) (ys + change * (ye - ys)) & 0xFF)
                | (((int) (cws + change * (cwe - cws)) & 0xFF) << 8)
                | (((int) (cms + change * (cme - cms)) & 0xFF) << 16)
                | (((int) (as + change * (ae - as)) & 0xFE) << 24));
    }

    /**
     * Interpolates from the packed float color start towards end by change, but keeps the alpha of start and uses the
     * alpha of end as an extra factor that can affect how much to change. Both start and end should be packed colors,
     * as from {@link com.github.tommyettinger.colorful.ipt_hq.ColorTools#ipt(float, float, float, float)} or
     * {@link ColorTools#ycwcm(float, float, float, float)}, and change can be between 0f (keep start) and 1f (only use
     * end). Both start and end should use the same color space; that is, both could be produced using IPT_HQ, or both
     * could be produced using YCwCm, but not a mix of the two. This is a good way to reduce allocations of temporary
     * Colors.
     * @param start the starting color as a packed float; alpha will be preserved
     * @param end the target color as a packed float; alpha will not be used directly, and will instead be multiplied with change
     * @param change how much to go from start toward end, as a float between 0 and 1; higher means closer to end
     * @return a packed float that represents a color between start and end
     */
    public static float lerpFloatColorsBlended(final float start, final float end, float change) {
        final int s = NumberUtils.floatToRawIntBits(start), e = NumberUtils.floatToRawIntBits(end),
                ys = (s & 0xFF), cws = (s >>> 8) & 0xFF, cms = (s >>> 16) & 0xFF, as = s & 0xFE000000,
                ye = (e & 0xFF), cwe = (e >>> 8) & 0xFF, cme = (e >>> 16) & 0xFF;
        change *= (e >>> 25) * 0.007874016f;
        return NumberUtils.intBitsToFloat(((int) (ys + change * (ye - ys)) & 0xFF)
                | (((int) (cws + change * (cwe - cws)) & 0xFF) << 8)
                | (((int) (cms + change * (cme - cms)) & 0xFF) << 16)
                | as);
    }

    /**
     * Returns a 1:1 mix of color0 and color1. All colors should use the same color space.
     * This is the same as calling {@link #lerpFloatColors(float, float, float)} with a change of 0.5.
     * @param color0 the first color to mix, as a packed float color
     * @param color1 the second color to mix, as a packed float color
     * @return an even mix of all colors given, as a packed float color
     */
    public static float mix(float color0, float color1) {
        return lerpFloatColors(color0, color1, 0.5f);
    }

    /**
     * Returns a 1:1:1 mix of color0, color1, and color2. All colors should use the same color space.
     * @param color0 the first color to mix, as a packed float color
     * @param color1 the second color to mix, as a packed float color
     * @param color2 the third color to mix, as a packed float color
     * @return an even mix of all colors given, as a packed float color
     */
    public static float mix(float color0, float color1, float color2) {
        return lerpFloatColors(lerpFloatColors(color0, color1, 0.5f), color2, 0.33333f);
    }

    /**
     * Returns a 1:1:1:1 mix of color0, color1, color2, and color3. All colors should use the same color space.
     * @param color0 the first color to mix, as a packed float color
     * @param color1 the second color to mix, as a packed float color
     * @param color2 the third color to mix, as a packed float color
     * @param color3 the fourth color to mix, as a packed float color
     * @return an even mix of all colors given, as a packed float color
     */
    public static float mix(float color0, float color1, float color2, float color3) {
        return lerpFloatColors(lerpFloatColors(lerpFloatColors(color0, color1, 0.5f), color2, 0.33333f), color3, 0.25f);
    }

    /**
     * Given several colors, this gets an even mix of all colors in equal measure.
     * If {@code colors} is null or has no items, this returns 0f (usually transparent in most color spaces).
     * @param colors an array or varargs of packed float colors; all should use the same color space
     * @return an even mix of all colors given, as a packed float color
     */
    public static float mix(float... colors) {
        if(colors == null || colors.length == 0)
            return 0f; // transparent, usually
        float result = colors[0];
        for (int i = 1; i < colors.length; i++) {
            result = lerpFloatColors(result, colors[i], 1f / (i + 1f));
        }
        return result;
    }

    /**
     * Given several colors, this gets an even mix of all colors in equal measure.
     * If {@code colors} is null or has no items, this returns 0f (usually transparent in most color spaces).
     * This is mostly useful in conjunction with {@link com.badlogic.gdx.utils.FloatArray}, using its {@code items}
     * for colors, typically 0 for offset, and its {@code size} for size.
     * @param colors an array of packed float colors; all should use the same color space
     * @param offset the index of the first item in {@code colors} to use
     * @param size how many items from {@code colors} to use
     * @return an even mix of all colors given, as a packed float color
     */
    public static float mix(float[] colors, int offset, int size) {
        final int end = offset + size;
        if(colors == null || colors.length < end || offset < 0 || size <= 0)
            return 0f; // transparent, usually
        float result = colors[offset];
        for (int i = offset + 1, denom = 2; i < end; i++, denom++) {
            result = lerpFloatColors(result, colors[i], 1f / denom);
        }
        return result;
    }

    /**
     * Mixes any number of colors with arbitrary weights per-color. Takes an array of varargs of alternating floats
     * representing colors and weights, as with {@code color, weight, color, weight...}.
     * If {@code colors} is null or has no items, this returns 0f (usually transparent in most color spaces).
     * @param colors an array or varargs that should contain alternating {@code color, weight, color, weight...} floats
     * @return a mix of all colors given respecting their weights, as a packed float color
     */
    public static float unevenMix(float... colors) {
        if(colors == null || colors.length == 0) return 0f;
        if(colors.length <= 2) return colors[0];
        return unevenMix(colors, 0, colors.length);
    }

    /**
     * Mixes any number of colors with arbitrary weights per-color. Takes an array of alternating floats representing
     * colors and weights, as with {@code color, weight, color, weight...}, starting at {@code offset} in the array and
     * continuing for {@code size} indices in the array. The {@code size} should be an even number 2 or greater,
     * otherwise it will be reduced by 1. The weights can be any non-negative finite float values; this method handles
     * normalizing them internally.
     * @param colors starting at {@code offset}, this should contain alternating {@code color, weight, color, weight...} floats
     * @param offset where to start reading from in {@code colors}
     * @param size how many indices to read from {@code colors}; must be an even number
     * @return the mixed color, as a packed float in the same color space as the given float colors
     */
    public static float unevenMix(float[] colors, int offset, int size) {
        size &= -2;
        final int end = offset + size;
        if(colors == null || colors.length < end || offset < 0 || size <= 0)
            return 0f; // transparent, usually
        float result = colors[offset];
        float current = colors[offset + 1], total = current;
        for (int i = offset+3; i < end; i += 2) {
            total += colors[i];
        }
        total = 1f / total;
        current *= total;
        for (int i = offset+3; i < end; i += 2) {
            float mixColor = colors[i-1], weight = colors[i] * total;
            result = lerpFloatColors(result, mixColor, weight / (current += weight));
        }
        return result;
    }
}
