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
import com.github.tommyettinger.colorful.rgb.ColorTools;
import com.github.tommyettinger.digital.Interpolations;
import com.github.tommyettinger.digital.MathTools;

// example output
/*
{
0x00000000, 0x000000FF, 0x1F1F1FFF, 0x2F2F2FFF, 0x3F3F3FFF, 0x4F4F4FFF, 0x5F5F5FFF, 0x6F6F6FFF,
0x7F7F7FFF, 0x8F8F8FFF, 0x9F9F9FFF, 0xAFAFAFFF, 0xBFBFBFFF, 0xCFCFCFFF, 0xDFDFDFFF, 0xFFFFFFFF,
0x3F0000FF, 0x3F0B00FF, 0x3F2400FF, 0x373F00FF, 0x093F00FF, 0x003F2CFF, 0x00143FFF, 0x32003FFF,
0x5F1F1FFF, 0x5F2B1FFF, 0x5F441FFF, 0x575F1FFF, 0x295F1FFF, 0x1F5F4CFF, 0x1F345FFF, 0x521F5FFF,
0x7F3F3FFF, 0x7F4B3FFF, 0x7F643FFF, 0x777F3FFF, 0x497F3FFF, 0x3F7F6CFF, 0x3F547FFF, 0x723F7FFF,
0x9F5F5FFF, 0x9F6B5FFF, 0x9F845FFF, 0x979F5FFF, 0x699F5FFF, 0x5F9F8CFF, 0x5F749FFF, 0x925F9FFF,
0xBF7F7FFF, 0xBF8B7FFF, 0xBFA47FFF, 0xB7BF7FFF, 0x89BF7FFF, 0x7FBFACFF, 0x7F94BFFF, 0xB27FBFFF,
0xDF9F9FFF, 0xDFAB9FFF, 0xDFC49FFF, 0xD7DF9FFF, 0xA9DF9FFF, 0x9FDFCCFF, 0x9FB4DFFF, 0xD29FDFFF,
0xFFBFBFFF, 0xFFCBBFFF, 0xFFE4BFFF, 0xF7FFBFFF, 0xC9FFBFFF, 0xBFFFECFF, 0xBFD4FFFF, 0xF2BFFFFF,
0x7F0000FF, 0x7F0600FF, 0x7F1600FF, 0x7F2C00FF, 0x7F4800FF, 0x7F6A00FF, 0x6F7F00FF, 0x437F00FF,
0x137F00FF, 0x007F20FF, 0x007F59FF, 0x00697FFF, 0x00297FFF, 0x1B007FFF, 0x64007FFF, 0x7F004FFF,
0x9F1F1FFF, 0x9F261FFF, 0x9F361FFF, 0x9F4C1FFF, 0x9F681FFF, 0x9F8A1FFF, 0x8F9F1FFF, 0x639F1FFF,
0x339F1FFF, 0x1F9F40FF, 0x1F9F79FF, 0x1F899FFF, 0x1F499FFF, 0x3B1F9FFF, 0x841F9FFF, 0x9F1F6FFF,
0xBF3F3FFF, 0xBF463FFF, 0xBF563FFF, 0xBF6C3FFF, 0xBF883FFF, 0xBFAA3FFF, 0xAFBF3FFF, 0x83BF3FFF,
0x53BF3FFF, 0x3FBF60FF, 0x3FBF99FF, 0x3FA9BFFF, 0x3F69BFFF, 0x5B3FBFFF, 0xA43FBFFF, 0xBF3F8FFF,
0xDF5F5FFF, 0xDF665FFF, 0xDF765FFF, 0xDF8C5FFF, 0xDFA85FFF, 0xDFCA5FFF, 0xCFDF5FFF, 0xA3DF5FFF,
0x73DF5FFF, 0x5FDF80FF, 0x5FDFB9FF, 0x5FC9DFFF, 0x5F89DFFF, 0x7B5FDFFF, 0xC45FDFFF, 0xDF5FAFFF,
0xFF7F7FFF, 0xFF867FFF, 0xFF967FFF, 0xFFAC7FFF, 0xFFC87FFF, 0xFFEA7FFF, 0xEFFF7FFF, 0xC3FF7FFF,
0x93FF7FFF, 0x7FFFA0FF, 0x7FFFD9FF, 0x7FE9FFFF, 0x7FA9FFFF, 0x9B7FFFFF, 0xE47FFFFF, 0xFF7FCFFF,
0xBF0000FF, 0xBF0500FF, 0xBF1000FF, 0xBF2100FF, 0xBF3600FF, 0xBF5000FF, 0xBF6D00FF, 0xBF8D00FF,
0xBFB100FF, 0xA6BF00FF, 0x7BBF00FF, 0x4EBF00FF, 0x1DBF00FF, 0x00BF16FF, 0x00BF4CFF, 0x00BF86FF,
0x00BDBFFF, 0x007FBFFF, 0x003DBFFF, 0x0600BFFF, 0x4C00BFFF, 0x9600BFFF, 0xBF009EFF, 0xBF0050FF,
0xDF1F1FFF, 0xDF251FFF, 0xDF301FFF, 0xDF411FFF, 0xDF561FFF, 0xDF701FFF, 0xDF8D1FFF, 0xDFAD1FFF,
0xDFD11FFF, 0xC6DF1FFF, 0x9BDF1FFF, 0x6EDF1FFF, 0x3DDF1FFF, 0x1FDF36FF, 0x1FDF6CFF, 0x1FDFA6FF,
0x1FDDDFFF, 0x1F9FDFFF, 0x1F5DDFFF, 0x261FDFFF, 0x6C1FDFFF, 0xB61FDFFF, 0xDF1FBEFF, 0xDF1F70FF,
0xFF3F3FFF, 0xFF453FFF, 0xFF503FFF, 0xFF613FFF, 0xFF763FFF, 0xFF903FFF, 0xFFAD3FFF, 0xFFCD3FFF,
0xFFF13FFF, 0xE6FF3FFF, 0xBBFF3FFF, 0x8EFF3FFF, 0x5DFF3FFF, 0x3FFF56FF, 0x3FFF8CFF, 0x3FFFC6FF,
0x3FFDFFFF, 0x3FBFFFFF, 0x3F7DFFFF, 0x463FFFFF, 0x8C3FFFFF, 0xD63FFFFF, 0xFF3FDEFF, 0xFF3F90FF,
0xFF0000FF, 0xFF0400FF, 0xFF0D00FF, 0xFF1B00FF, 0xFF2C00FF, 0xFF4100FF, 0xFF5900FF, 0xFF7300FF,
0xFF9100FF, 0xFFB100FF, 0xFFD400FF, 0xFFFA00FF, 0xDEFF00FF, 0xB3FF00FF, 0x87FF00FF, 0x58FF00FF,
0x27FF00FF, 0x00FF0CFF, 0x00FF41FF, 0x00FF79FF, 0x00FFB2FF, 0x00FFEEFF, 0x00D3FFFF, 0x0093FFFF,
0x0052FFFF, 0x000EFFFF, 0x3700FFFF, 0x7E00FFFF, 0xC800FFFF, 0xFF00ECFF, 0xFF009FFF, 0xFF0050FF,
}
 */

/**
 * The shape of this non-linear palette looks <a href="https://i.imgur.com/3WpetQ5.png">like this</a> in HSL space.
 * It has 9 primary square-shaped layers, with sizes 1x1, 3x3, 5x5, 7x7, and 9x9, then repeating 7x7, 5x5... backwards.
 * In between most layers are extra single levels of pure gray.
 */
public class BattleaxePaletteGenerator {
    private static final int limit = 256;
    private static final IntArray rgba = new IntArray(limit);

    public static int autoAdjust(int perimeter, int extent, int lightness) {
        return extent == 0
                ? FloatColors.hcl2rgbInt(0, 0, lightness * (1f / 16f), 1f)
                : FloatColors.hcl2rgbInt(
                (float) Math.pow(perimeter / (8f * extent), 1.7f),
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
