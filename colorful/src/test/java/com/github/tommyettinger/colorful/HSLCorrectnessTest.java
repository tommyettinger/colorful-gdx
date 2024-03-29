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
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.NumberUtils;
import com.github.tommyettinger.colorful.rgb.ColorTools;

public class HSLCorrectnessTest {

    /**
     * Converts a packed float in RGBA format to a packed float in "HSLA format" (hue, saturation, lightness, alpha),
     * which isn't one of the regular formats this supports but can be useful for conversions.
     * @param rgba an RGBA-format packed float
     * @return an "HSLA-format" packed float
     */
    public static float rgb2hslRogue(final float rgba) {
        final int decoded = NumberUtils.floatToRawIntBits(rgba);
        return rgb2hslRogue((decoded & 0xFF) / 255f, (decoded >>> 8 & 0xFF) / 255f, (decoded >>> 16 & 0xFF) / 255f, (decoded >>> 25 & 0x7F) / 127f);
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
    public static float rgb2hslRogue(final float r, final float g, final float b, final float a) {

        float hue, sat, lit;
        if (r == g && g == b)
        {
            return Color.toFloatBits(0f, 0f, r, a);
        }

        float min = Math.min(r, Math.min(g, b));
        float max = Math.max(r, Math.max(g, b));

        float delta = max - min;

        if (r == max)
            hue = (g - b) / delta;
        else if (g == max)
            hue = (b - r) / delta + 2f;
        else
            hue = (r - g) / delta + 4f;

        hue /= 6f;
        hue -= MathUtils.floor(hue);

        float div = max + min;
        lit = div * 0.5f;
        if (div > 1f)
            div = 2f - div;

        sat = (max - min) / div;
        return Color.toFloatBits(hue, sat, lit, a);
    }

    /**
     * Cross-reference with <a href="https://en.wikipedia.org/wiki/HSL_and_HSV#Examples">Wikipedia - HSL and HSV</a>.
     * @param args
     */
    public static void main(String[] args) {
        int[] colors = {
                0xFFFFFFFF,
                0x808080FF,
                0x000000FF,
                0xFF0000FF,
                0xBFBF00FF,
                0x008000FF,
                0x80FFFFFF,
                0x8080FFFF,
                0xBF40BFFF,
                0xA0A424FF,
                0x411BEAFF,
                0x1EAC41FF,
                0xF0C80EFF,
                0xB430E5FF,
                0xED7651FF,
                0xFEF888FF,
                0x19CB97FF,
                0x362698FF,
                0x7E7EB8FF,
        };

        IntMap<float[]> map = new IntMap<>(19);
        map.put(0xFFFFFFFF,	new float[]{0.000f,	0.000f, 1.000f});
        map.put(0x808080FF,	new float[]{0.000f,	0.000f, 0.500f});
        map.put(0x000000FF,	new float[]{0.000f,	0.000f, 0.000f});
        map.put(0xFF0000FF,	new float[]{0.000f,	1.000f, 0.500f});
        map.put(0xBFBF00FF,	new float[]{60.00f,	1.000f, 0.375f});
        map.put(0x008000FF,	new float[]{120.0f,	1.000f, 0.250f});
        map.put(0x80FFFFFF,	new float[]{180.0f,	1.000f, 0.750f});
        map.put(0x8080FFFF,	new float[]{240.0f,	1.000f, 0.750f});
        map.put(0xBF40BFFF,	new float[]{300.0f,	0.500f, 0.500f});
        map.put(0xA0A424FF,	new float[]{61.80f,	0.638f, 0.393f});
        map.put(0x411BEAFF,	new float[]{251.1f,	0.832f, 0.511f});
        map.put(0x1EAC41FF,	new float[]{134.9f,	0.707f, 0.396f});
        map.put(0xF0C80EFF,	new float[]{49.50f,	0.893f, 0.497f});
        map.put(0xB430E5FF,	new float[]{283.7f,	0.775f, 0.542f});
        map.put(0xED7651FF,	new float[]{14.30f,	0.817f, 0.624f});
        map.put(0xFEF888FF,	new float[]{56.90f,	0.991f, 0.765f});
        map.put(0x19CB97FF,	new float[]{162.4f,	0.779f, 0.447f});
        map.put(0x362698FF,	new float[]{248.3f,	0.601f, 0.373f});
        map.put(0x7E7EB8FF,	new float[]{240.5f,	0.290f, 0.607f});
        float hAbs = 0f, hRel = 0f, hMax = 0f;
        float sAbs = 0f, sRel = 0f, sMax = 0f;
        float lAbs = 0f, lRel = 0f, lMax = 0f;
        for (int c : colors) {
            float[] target = map.get(c);
            float abgr = ColorTools.fromRGBA8888(c);
            float hsla = rgb2hslRogue(abgr);
            float hue = ColorTools.red(hsla) * 360f;
            float sat = ColorTools.green(hsla);
            float lit = ColorTools.blue(hsla);
            float err;
            err = hue - target[0]; hMax = Math.max(Math.abs(err), hMax); hRel += err; hAbs += Math.abs(err);
            System.out.printf("0x%08X: %7.3f %4.3f %4.3f with hue error %5.4f\n", c, hue, sat, lit, err);
            err = sat - target[1]; sMax = Math.max(Math.abs(err), sMax); sRel += err; sAbs += Math.abs(err);
            err = lit - target[2]; lMax = Math.max(Math.abs(err), lMax); lRel += err; lAbs += Math.abs(err);
        }

        System.out.printf("Hue: Relative error %8.6f, absolute error %8.6f, max error %8.6f\n", hRel, hAbs, hMax);
        System.out.printf("Sat: Relative error %8.6f, absolute error %8.6f, max error %8.6f\n", sRel, sAbs, sMax);
        System.out.printf("Lit: Relative error %8.6f, absolute error %8.6f, max error %8.6f\n", lRel, lAbs, lMax);
    }
}
