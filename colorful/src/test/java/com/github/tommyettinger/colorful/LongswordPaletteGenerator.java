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
0x00000000, 0x000000FF, 0x1F1F1FFF, 0x2F2F2FFF, 0x3F3F3FFF, 0x4F4F4FFF, 0x5F5F5FFF, 0x6F6F6FFF,
0x7F7F7FFF, 0x8F8F8FFF, 0x9F9F9FFF, 0xAFAFAFFF, 0xBFBFBFFF, 0xCFCFCFFF, 0xDFDFDFFF, 0xFFFFFFFF,
0x3F0000FF, 0x3F2F00FF, 0x1F3F00FF, 0x003F0FFF, 0x003F3FFF, 0x000F3FFF, 0x1F003FFF, 0x3F002FFF,
0x5F1F1FFF, 0x5F4F1FFF, 0x3F5F1FFF, 0x1F5F2FFF, 0x1F5F5FFF, 0x1F2F5FFF, 0x3F1F5FFF, 0x5F1F4FFF,
0x7F3F3FFF, 0x7F6F3FFF, 0x5F7F3FFF, 0x3F7F4FFF, 0x3F7F7FFF, 0x3F4F7FFF, 0x5F3F7FFF, 0x7F3F6FFF,
0x9F5F5FFF, 0x9F8F5FFF, 0x7F9F5FFF, 0x5F9F6FFF, 0x5F9F9FFF, 0x5F6F9FFF, 0x7F5F9FFF, 0x9F5F8FFF,
0xBF7F7FFF, 0xBFAF7FFF, 0x9FBF7FFF, 0x7FBF8FFF, 0x7FBFBFFF, 0x7F8FBFFF, 0x9F7FBFFF, 0xBF7FAFFF,
0xDF9F9FFF, 0xDFCF9FFF, 0xBFDF9FFF, 0x9FDFAFFF, 0x9FDFDFFF, 0x9FAFDFFF, 0xBF9FDFFF, 0xDF9FCFFF,
0xFFBFBFFF, 0xFFEFBFFF, 0xDFFFBFFF, 0xBFFFCFFF, 0xBFFFFFFF, 0xBFCFFFFF, 0xDFBFFFFF, 0xFFBFEFFF,
0x7F0000FF, 0x7F2F00FF, 0x7F5F00FF, 0x6F7F00FF, 0x3F7F00FF, 0x0F7F00FF, 0x007F1FFF, 0x007F4FFF,
0x007F7FFF, 0x004F7FFF, 0x001F7FFF, 0x0F007FFF, 0x3F007FFF, 0x6F007FFF, 0x7F005FFF, 0x7F002FFF,
0x9F1F1FFF, 0x9F4F1FFF, 0x9F7F1FFF, 0x8F9F1FFF, 0x5F9F1FFF, 0x2F9F1FFF, 0x1F9F3FFF, 0x1F9F6FFF,
0x1F9F9FFF, 0x1F6F9FFF, 0x1F3F9FFF, 0x2F1F9FFF, 0x5F1F9FFF, 0x8F1F9FFF, 0x9F1F7FFF, 0x9F1F4FFF,
0xBF3F3FFF, 0xBF6F3FFF, 0xBF9F3FFF, 0xAFBF3FFF, 0x7FBF3FFF, 0x4FBF3FFF, 0x3FBF5FFF, 0x3FBF8FFF,
0x3FBFBFFF, 0x3F8FBFFF, 0x3F5FBFFF, 0x4F3FBFFF, 0x7F3FBFFF, 0xAF3FBFFF, 0xBF3F9FFF, 0xBF3F6FFF,
0xDF5F5FFF, 0xDF8F5FFF, 0xDFBF5FFF, 0xCFDF5FFF, 0x9FDF5FFF, 0x6FDF5FFF, 0x5FDF7FFF, 0x5FDFAFFF,
0x5FDFDFFF, 0x5FAFDFFF, 0x5F7FDFFF, 0x6F5FDFFF, 0x9F5FDFFF, 0xCF5FDFFF, 0xDF5FBFFF, 0xDF5F8FFF,
0xFF7F7FFF, 0xFFAF7FFF, 0xFFDF7FFF, 0xEFFF7FFF, 0xBFFF7FFF, 0x8FFF7FFF, 0x7FFF9FFF, 0x7FFFCFFF,
0x7FFFFFFF, 0x7FCFFFFF, 0x7F9FFFFF, 0x8F7FFFFF, 0xBF7FFFFF, 0xEF7FFFFF, 0xFF7FDFFF, 0xFF7FAFFF,
0xBF0000FF, 0xBF2F00FF, 0xBF5F00FF, 0xBF8F00FF, 0xBFBF00FF, 0x8FBF00FF, 0x5FBF00FF, 0x2FBF00FF,
0x00BF00FF, 0x00BF2FFF, 0x00BF5FFF, 0x00BF8FFF, 0x00BFBFFF, 0x008FBFFF, 0x005FBFFF, 0x002FBFFF,
0x0000BFFF, 0x2F00BFFF, 0x5F00BFFF, 0x8F00BFFF, 0xBF00BFFF, 0xBF008FFF, 0xBF005FFF, 0xBF002FFF,
0xDF1F1FFF, 0xDF4F1FFF, 0xDF7F1FFF, 0xDFAF1FFF, 0xDFDF1FFF, 0xAFDF1FFF, 0x7FDF1FFF, 0x4FDF1FFF,
0x1FDF1FFF, 0x1FDF4FFF, 0x1FDF7FFF, 0x1FDFAFFF, 0x1FDFDFFF, 0x1FAFDFFF, 0x1F7FDFFF, 0x1F4FDFFF,
0x1F1FDFFF, 0x4F1FDFFF, 0x7F1FDFFF, 0xAF1FDFFF, 0xDF1FDFFF, 0xDF1FAFFF, 0xDF1F7FFF, 0xDF1F4FFF,
0xFF3F3FFF, 0xFF6F3FFF, 0xFF9F3FFF, 0xFFCF3FFF, 0xFFFF3FFF, 0xCFFF3FFF, 0x9FFF3FFF, 0x6FFF3FFF,
0x3FFF3FFF, 0x3FFF6FFF, 0x3FFF9FFF, 0x3FFFCFFF, 0x3FFFFFFF, 0x3FCFFFFF, 0x3F9FFFFF, 0x3F6FFFFF,
0x3F3FFFFF, 0x6F3FFFFF, 0x9F3FFFFF, 0xCF3FFFFF, 0xFF3FFFFF, 0xFF3FCFFF, 0xFF3F9FFF, 0xFF3F6FFF,
0xFF0000FF, 0xFF2F00FF, 0xFF5F00FF, 0xFF8F00FF, 0xFFBF00FF, 0xFFEF00FF, 0xDFFF00FF, 0xAFFF00FF,
0x7FFF00FF, 0x4FFF00FF, 0x1FFF00FF, 0x00FF0FFF, 0x00FF3FFF, 0x00FF6FFF, 0x00FF9FFF, 0x00FFCFFF,
0x00FFFFFF, 0x00CFFFFF, 0x009FFFFF, 0x006FFFFF, 0x003FFFFF, 0x000FFFFF, 0x1F00FFFF, 0x4F00FFFF,
0x7F00FFFF, 0xAF00FFFF, 0xDF00FFFF, 0xFF00EFFF, 0xFF00BFFF, 0xFF008FFF, 0xFF005FFF, 0xFF002FFF,
}
 */

/**
 * The shape of this non-linear palette looks <a href="https://i.imgur.com/3WpetQ5.png">like this</a> in HSL space.
 * It has 9 primary square-shaped layers, with sizes 1x1, 3x3, 5x5, 7x7, and 9x9, then repeating 7x7, 5x5... backwards.
 * In between most layers are extra single levels of pure gray. This is identical to BattleaxePaletteGenerator except
 * that it doesn't alter hue in any way; it "plays it straight."
 */
public class LongswordPaletteGenerator {
    private static final int limit = 256;
    private static final IntArray rgba = new IntArray(limit);

    public static int autoAdjust(int perimeter, int extent, int lightness) {
        return extent == 0
                ? FloatColors.hcl2rgbInt(0, 0, lightness * (1f / 16f), 1f)
                : FloatColors.hcl2rgbInt(
                perimeter / (8f * extent),
                extent * 0.25f,
                lightness * (1f/16f),
                1f);
    }
    public static void main(String[] args) {
        rgba.add(0);

        // central gray column
        rgba.add(autoAdjust(0, 0, 0));
        for (int li = 2; li <= 14; li++) {
            rgba.add(autoAdjust(0, 0, li));
        }
        rgba.add(autoAdjust(0, 0, 16));

        for (int e = 1; e <= 4; e++) {
            int lowerLimit = e + e, upperLimit = 16 - e - e, around = e << 3;
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
        System.out.println(sb.append("}"));
    }
}
