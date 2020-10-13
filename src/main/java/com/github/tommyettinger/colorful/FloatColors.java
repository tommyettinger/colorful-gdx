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
        final int decoded = NumberUtils.floatToIntBits(hsla);
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
        float x = MathUtils.clamp(Math.abs(h * 6f - 3f) - 1f, 0f, 1f), y = h + 2f / 3f, z = h + 1f / 3f;
        y -= (int)y;
        z -= (int)z;
        y = MathUtils.clamp(Math.abs(y * 6f - 3f) - 1f, 0f, 1f);
        z = MathUtils.clamp(Math.abs(z * 6f - 3f) - 1f, 0f, 1f);
        float v = (l + s * Math.min(l, 1f - l));
        float d = 2f * (1f - l / (v + 1e-10f));
        return Color.toFloatBits(v * MathUtils.lerp(1f, x, d), v * MathUtils.lerp(1f, y, d), v * MathUtils.lerp(1f, z, d), a);
    }

    /**
     * Converts a packed float in RGBA format to a packed float in "HSLA format" (hue, saturation, lightness, alpha),
     * which isn't one of the regular formats this supports but can be useful for conversions.
     * @param rgba an RGBA-format packed float
     * @return an "HSLA-format" packed float
     */
    public static float rgb2hsl(final float rgba) {
        final int decoded = NumberUtils.floatToIntBits(rgba);
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
     * @param encodedColor a color encoded as a packed float, as by {@link ColorTools#ycwcma(float, float, float, float)}
     *                     or {@link com.github.tommyettinger.colorful.ipt.ColorTools#ipt(float, float, float, float)}
     * @param alpha  between 0.0 and 1.0 inclusive, the alpha to multiply the color's own alpha by
     * @return a color encoded as a packed float, using color's RGB channels but with its A channel times {@code alpha}
     */
    public static float multiplyAlpha(final float encodedColor, final float alpha) {
        final int bits = NumberUtils.floatToIntBits(encodedColor);
        return NumberUtils.intBitsToFloat(bits & 0xFFFFFF
                | (MathUtils.clamp((int) ((bits >>> 24) * alpha), 0, 255) << 24 & 0xFE000000));
    }

    /**
     * Given a color stored as a packed float, and a desired alpha to set for that color (between 0.0 and 1.0, inclusive
     * and clamped in that range), this makes a new float that has the same red, green, and blue channels but has been
     * set to the given alpha, without constructing any objects along the way. This does not consider the current alpha
     * of the encoded color; if you want to do that, you can use {@link #multiplyAlpha(float, float)}.
     *
     * @param encodedColor a color encoded as a packed float, as by {@link ColorTools#ycwcma(float, float, float, float)}
     *                     or {@link com.github.tommyettinger.colorful.ipt.ColorTools#ipt(float, float, float, float)}
     * @param alpha        between 0.0 and 1.0 inclusive, the alpha to set into the returned packed color
     * @return another color encoded as a packed float, using encodedColor's RGB channels and the given alpha
     */
    public static float setAlpha(final float encodedColor, final float alpha) {
        return NumberUtils.intBitsToFloat(NumberUtils.floatToIntBits(encodedColor) & 0xFFFFFF
                | (MathUtils.clamp((int) (255f * alpha), 0, 255) << 24 & 0xFE000000));
    }

    /**
     * Interpolates from the packed float color start towards end by change. Both start and end should be packed colors,
     * as from {@link ColorTools#ycwcma(float, float, float, float)} or
     * {@link com.github.tommyettinger.colorful.ipt.ColorTools#ipt(float, float, float, float)}, and change can be between 0f
     * (keep start) and 1f (only use end). Both start and end should use the same color space; that is, both could be
     * produced using YCwCmA, or both could be produced using IPT, but not a mix of the two. This is a good way to
     * reduce allocations of temporary Colors.
     * @param start the starting color as a packed float
     * @param end the target color as a packed float
     * @param change how much to go from start toward end, as a float between 0 and 1; higher means closer to end
     * @return a packed float that represents a color between start and end
     */
    public static float lerpFloatColors(final float start, final float end, float change) {
        final int s = NumberUtils.floatToIntBits(start), e = NumberUtils.floatToIntBits(end),
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
     * as from {@link ColorTools#ycwcma(float, float, float, float)} or
     * {@link com.github.tommyettinger.colorful.ipt.ColorTools#ipt(float, float, float, float)}, and change can be between 0f
     * (keep start) and 1f (only use end). Both start and end should use the same color space; that is, both could be
     * produced using YCwCmA, or both could be produced using IPT, but not a mix of the two. This is a good way to
     * reduce allocations of temporary Colors.
     * @param start the starting color as a packed float; alpha will be preserved
     * @param end the target color as a packed float; alpha will not be used directly, and will instead be multiplied with change
     * @param change how much to go from start toward end, as a float between 0 and 1; higher means closer to end
     * @return a packed float that represents a color between start and end
     */
    public static float lerpFloatColorsBlended(final float start, final float end, float change) {
        final int s = NumberUtils.floatToIntBits(start), e = NumberUtils.floatToIntBits(end),
                ys = (s & 0xFF), cws = (s >>> 8) & 0xFF, cms = (s >>> 16) & 0xFF, as = s >>> 24 & 0xFE,
                ye = (e & 0xFF), cwe = (e >>> 8) & 0xFF, cme = (e >>> 16) & 0xFF;
        change *= (e >>> 25) * 0.007874016f;
        return NumberUtils.intBitsToFloat(((int) (ys + change * (ye - ys)) & 0xFF)
                | (((int) (cws + change * (cwe - cws)) & 0xFF) << 8)
                | (((int) (cms + change * (cme - cms)) & 0xFF) << 16)
                | as);
    }

}
