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

// example output
/*
{
0x00000000, 0x000000FF, 0x1F1F1FFF, 0x3F3F3FFF, 0x5F5F5FFF, 0x7F7F7FFF, 0x9F9F9FFF, 0xBFBFBFFF,
0xDFDFDFFF, 0xFFFFFFFF, 0x7F0000FF, 0x7F1600FF, 0x7F4800FF, 0x6F7F00FF, 0x137F00FF, 0x007F59FF,
0x00297FFF, 0x64007FFF, 0xBF3F3FFF, 0xBF563FFF, 0xBF883FFF, 0xAFBF3FFF, 0x53BF3FFF, 0x3FBF99FF,
0x3F69BFFF, 0xA43FBFFF, 0xFF7F7FFF, 0xFF967FFF, 0xFFC87FFF, 0xEFFF7FFF, 0x93FF7FFF, 0x7FFFD9FF,
0x7FA9FFFF, 0xE47FFFFF, 0xFF0000FF, 0xFF0D00FF, 0xFF2C00FF, 0xFF5900FF, 0xFF9100FF, 0xFFD400FF,
0xDEFF00FF, 0x87FF00FF, 0x27FF00FF, 0x00FF41FF, 0x00FFB2FF, 0x00D3FFFF, 0x0052FFFF, 0x3700FFFF,
0xC800FFFF, 0xFF009FFF,
}
 */

/**
 * The shape of this non-linear palette looks <a href="https://i.imgur.com/3WpetQ5.png">like this</a> in HSL space.
 * It has 9 primary square-shaped layers, with sizes 1x1, 3x3, and 5x5, then repeating 3x3, 1x1 backwards.
 * In between all layers are extra single levels of pure gray.
 */
public class HatchetPaletteGenerator {
    private static final int limit = 50;
    private static final IntArray rgba = new IntArray(limit);

    public static int autoAdjust(int perimeter, int extent, int lightness) {
        return extent == 0
                ? FloatColors.hcl2rgbInt(0, 0, lightness * (1f / 8f), 1f)
                : FloatColors.hcl2rgbInt(
                (float) Math.pow(perimeter / (8f * extent), 1.7f),
                extent / 2f,
                lightness * (1f/8f),
                1f);
    }
    public static void main(String[] args) {
        rgba.add(0);

        // central gray column
        for (int li = 0; li <= 8; li++) {
            rgba.add(autoAdjust(0, 0, li));
        }

        for (int e = 1; e <= 3; e++) {
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
