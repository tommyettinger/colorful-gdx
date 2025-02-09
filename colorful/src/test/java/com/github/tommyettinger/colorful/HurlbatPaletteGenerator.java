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

import com.badlogic.gdx.utils.IntArray;
import com.github.tommyettinger.colorful.internal.StringKit;
import com.github.tommyettinger.digital.TrigTools;

// example output
/*
{
0x00000000, 0x000000FF, 0x1F1F1FFF, 0x3F3F3FFF, 0x5F5F5FFF, 0x7F7F7FFF, 0x9F9F9FFF, 0xBFBFBFFF,
0xDFDFDFFF, 0xFFFFFFFF, 0x890909FF, 0x7F1600FF, 0x6C3D00FF, 0x778808FF, 0x168202FF, 0x006A4AFF,
0x052E85FF, 0x690585FF, 0xB63636FF, 0xC85E48FF, 0xC28B42FF, 0xA4B535FF, 0x59C545FF, 0x45C59FFF,
0x355EB5FF, 0xA743C3FF, 0xFF8585FF, 0xF58B75FF, 0xFFCD8BFF, 0xF0FF8BFF, 0x89F575FF, 0x86FFDBFF,
0x8FB3FFFF, 0xDA76F6FF, 0xFF0707FF, 0xF60D00FF, 0xFF2C00FF, 0xFF5F09FF, 0xF78C00FF, 0xFDD200FF,
0xDFFF0AFF, 0x84FA00FF, 0x26FA00FF, 0x0AFF49FF, 0x00FCB0FF, 0x00CDF8FF, 0x0958FFFF, 0x3700FFFF,
0xC000F6FF, 0xFF08A2FF,
}
 */

/**
 * The shape of this non-linear palette looks <a href="https://i.imgur.com/3WpetQ5.png">like this</a> in HSL space.
 * It has 9 primary square-shaped layers, with sizes 1x1, 3x3, and 5x5, then repeating 3x3, 1x1 backwards.
 * In between all layers are extra single levels of pure gray. This wobbles lightness for non-grayscale colors, though
 * only very slightly.
 */
public class HurlbatPaletteGenerator {
    private static final int limit = 50;
    private static final IntArray rgba = new IntArray(limit);

    public static int autoAdjust(int perimeter, int extent, int lightness) {
        return extent == 0
                ? FloatColors.hcl2rgbInt(0, 0, lightness * (1f / 8f), 1f)
                : FloatColors.hcl2rgbInt(
                (float) Math.pow(perimeter / (8f * extent), 1.7f),
                extent / 2f,
                lightness * (1f/8f) + TrigTools.sinSmoother((perimeter + extent * 8 + lightness)*2) * ((3f - extent)/48f),
                1f);
    }
    public static void main(String[] args) {
        rgba.add(0);

        // central gray column
        for (int li = 0; li <= 8; li++) {
            rgba.add(autoAdjust(0, 0, li));
        }

        for (int e = 1; e <= 2; e++) {
            int lowerLimit = e + e, upperLimit = 8 - e - e, around = e << 3;
            for (int li = lowerLimit; li <= upperLimit; li += 2) {
                for (int pe = 0; pe < around; pe++) {
                    rgba.add(autoAdjust(pe, e, li));
                }
            }
        }

        StringBuilder sb = new StringBuilder(12 * rgba.size + 256).append("{\n");
        for (int i = 0; i < rgba.size; i++) {
            StringKit.appendHex(sb.append("0x"), rgba.get(i)).append(", ");
            if(7 == (i & 7)) sb.append('\n');
        }
        System.out.println(sb.append("\n}"));
        System.out.println("\n" + rgba.size);
    }
}
