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
0x00000000, 0x000000FF, 0x151515FF, 0x2A2A2AFF, 0x3F3F3FFF, 0x555555FF, 0x6A6A6AFF, 0x7F7F7FFF,
0x959595FF, 0xAAAAAAFF, 0xBFBFBFFF, 0xD5D5D5FF, 0xEAEAEAFF, 0xFFFFFFFF, 0x550000FF, 0x550E00FF,
0x553000FF, 0x4A5500FF, 0x0D5500FF, 0x00553BFF, 0x001B55FF, 0x420055FF, 0x7F2A2AFF, 0x7F392AFF,
0x7F5B2AFF, 0x747F2AFF, 0x377F2AFF, 0x2A7F66FF, 0x2A467FFF, 0x6D2A7FFF, 0xAA5555FF, 0xAA6455FF,
0xAA8555FF, 0x9FAA55FF, 0x62AA55FF, 0x55AA90FF, 0x5570AAFF, 0x9855AAFF, 0xD57F7FFF, 0xD58E7FFF,
0xD5B07FFF, 0xCAD57FFF, 0x8DD57FFF, 0x7FD5BBFF, 0x7F9BD5FF, 0xC27FD5FF, 0xFFAAAAFF, 0xFFB9AAFF,
0xFFDBAAFF, 0xF4FFAAFF, 0xB7FFAAFF, 0xAAFFE6FF, 0xAAC6FFFF, 0xEDAAFFFF, 0xAA0000FF, 0xAA0900FF,
0xAA1D00FF, 0xAA3B00FF, 0xAA6100FF, 0xAA8D00FF, 0x94AA00FF, 0x5AAA00FF, 0x1AAA00FF, 0x00AA2BFF,
0x00AA77FF, 0x008DAAFF, 0x0036AAFF, 0x2400AAFF, 0x8500AAFF, 0xAA006AFF, 0xD52A2AFF, 0xD5332AFF,
0xD5482AFF, 0xD5662AFF, 0xD58B2AFF, 0xD5B82AFF, 0xBED52AFF, 0x84D52AFF, 0x44D52AFF, 0x2AD556FF,
0x2AD5A1FF, 0x2AB7D5FF, 0x2A61D5FF, 0x4F2AD5FF, 0xB02AD5FF, 0xD52A95FF, 0xFF5555FF, 0xFF5E55FF,
0xFF7355FF, 0xFF9055FF, 0xFFB655FF, 0xFFE355FF, 0xE9FF55FF, 0xAFFF55FF, 0x6FFF55FF, 0x55FF81FF,
0x55FFCCFF, 0x55E2FFFF, 0x558CFFFF, 0x7A55FFFF, 0xDA55FFFF, 0xFF55BFFF, 0xFF0000FF, 0xFF0600FF,
0xFF1600FF, 0xFF2C00FF, 0xFF4900FF, 0xFF6A00FF, 0xFF9100FF, 0xFFBD00FF, 0xFFED00FF, 0xDEFF00FF,
0xA5FF00FF, 0x68FF00FF, 0x27FF00FF, 0x00FF1DFF, 0x00FF66FF, 0x00FFB2FF, 0x00FDFFFF, 0x00A9FFFF,
0x0052FFFF, 0x0800FFFF, 0x6600FFFF, 0xC800FFFF, 0xFF00D3FF, 0xFF006BFF,
}
 */

/**
 * The shape of this non-linear palette looks <a href="https://i.imgur.com/3WpetQ5.png">like this</a> in HSL space.
 * It has 9 primary square-shaped layers, with sizes 1x1, 3x3, 5x5, 7x7, and 9x9, then repeating 7x7, 5x5... backwards.
 * In between most layers are extra single levels of pure gray.
 */
public class HandaxePaletteGenerator {
    private static final int limit = 126;
    private static final IntArray rgba = new IntArray(limit);

    public static int autoAdjust(int perimeter, int extent, int lightness) {
        return extent == 0
                ? FloatColors.hcl2rgbInt(0, 0, lightness * (1f / 12f), 1f)
                : FloatColors.hcl2rgbInt(
                (float) Math.pow(perimeter / (8f * extent), 1.7f),
                extent / 3f,
                lightness * (1f/12f),
                1f);
    }
    public static void main(String[] args) {
        rgba.add(0);

        // central gray column
        for (int li = 0; li <= 12; li++) {
            rgba.add(autoAdjust(0, 0, li));
        }

        for (int e = 1; e <= 3; e++) {
            int lowerLimit = e + e, upperLimit = 12 - e - e, around = e << 3;
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
