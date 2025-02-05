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

package com.github.tommyettinger.colorful.oklab;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.IntArray;
import com.github.tommyettinger.colorful.TrigTools;
import com.github.tommyettinger.colorful.internal.StringKit;
import com.github.tommyettinger.digital.Interpolations;
import com.github.tommyettinger.digital.MathTools;

// example output
/*
{
0x00000000, 0x000000FF, 0x555555FF, 0x533B58FF, 0xA481B4FF, 0x9C44FFFF, 0x5E02A7FF, 0xC70085FF,
0xFF36B9FF, 0xE3A0A7FF, 0xB7515DFF, 0xA07765FF, 0xDAC7BFFF, 0xFF9C86FF, 0xFF240FFF, 0xD08400FF,
0xFFAF2BFF, 0xD2CA7AFF, 0x8C8A4EFF, 0x617756FF, 0xA1C797FF, 0xA7CD00FF, 0x779000FF, 0x008159FF,
0x00B47DFF, 0x599997FF, 0x325250FF, 0x29363CFF, 0x004F5CFF, 0x003A87FF, 0x352C72FF, 0x726DC3FF,
0x0063E8FF, 0x008EA2FF, 0x69838EFF, 0xAAAAAAFF, 0xFFFFFFFF,
}
 */

/**
 * The shape of this linear palette looks <a href="https://imgur.com/DsPrUDR">like this</a>. It uses 4 tiny 3D Hilbert
 * curves, each 2x2x2, arranged around a central column that extends further up and down.
 */
public class DaiisyPaletteGenerator {
    private static final int limit = 37;
    private static final IntArray rgba = new IntArray(limit);

    public static int autoAdjust(float hue, float sat, float lit) {
        return ColorTools.toRGBA8888(ColorTools.oklabByHSL(hue - 0.038f,
                Interpolations.smooth.apply(sat),
                lit + TrigTools.sinTurns(hue) * 0.17f, 1f));
    }
    /*
jshell> //hue 0,1: -0.080, 1,1: +0.080, 0,2: -0.125, 1,2: 0.000
jshell> //saturation 0,1: 0.447, 1,1: 0.632, 0,2: 0.894, 1,2: 1.000
jshell> //lightness 0,1: 0.12f, 1,1: 0.10f, 0,2: 0.08f, 1,2: 0.06f
     */
    public static void main(String[] args) {
        rgba.add(0);
        rgba.add(autoAdjust(0f, 0f, 0f));
        rgba.add(autoAdjust(0f, 0f, 0.333f));

        rgba.add(autoAdjust(0.000f - 0.080f, 0.353f, 0.475f - 0.12f));
        rgba.add(autoAdjust(0.000f - 0.080f, 0.353f, 0.525f + 0.12f));
        rgba.add(autoAdjust(0.000f - 0.125f, 0.894f, 0.525f + 0.08f));
        rgba.add(autoAdjust(0.000f - 0.125f, 0.894f, 0.475f - 0.08f));
        rgba.add(autoAdjust(0.000f + 0.000f, 1.000f, 0.475f - 0.06f));
        rgba.add(autoAdjust(0.000f + 0.000f, 1.000f, 0.525f + 0.06f));
        rgba.add(autoAdjust(0.000f + 0.080f, 0.523f, 0.525f + 0.10f));
        rgba.add(autoAdjust(0.000f + 0.080f, 0.523f, 0.475f - 0.10f));

        rgba.add(autoAdjust(0.250f - 0.080f, 0.353f, 0.475f - 0.12f));
        rgba.add(autoAdjust(0.250f - 0.080f, 0.353f, 0.525f + 0.12f));
        rgba.add(autoAdjust(0.250f - 0.125f, 0.894f, 0.525f + 0.08f));
        rgba.add(autoAdjust(0.250f - 0.125f, 0.894f, 0.475f - 0.08f));
        rgba.add(autoAdjust(0.250f + 0.000f, 1.000f, 0.475f - 0.06f));
        rgba.add(autoAdjust(0.250f + 0.000f, 1.000f, 0.525f + 0.06f));
        rgba.add(autoAdjust(0.250f + 0.080f, 0.523f, 0.525f + 0.10f));
        rgba.add(autoAdjust(0.250f + 0.080f, 0.523f, 0.475f - 0.10f));

        rgba.add(autoAdjust(0.500f - 0.080f, 0.353f, 0.475f - 0.12f));
        rgba.add(autoAdjust(0.500f - 0.080f, 0.353f, 0.525f + 0.12f));
        rgba.add(autoAdjust(0.500f - 0.125f, 0.894f, 0.525f + 0.08f));
        rgba.add(autoAdjust(0.500f - 0.125f, 0.894f, 0.475f - 0.08f));
        rgba.add(autoAdjust(0.500f + 0.000f, 1.000f, 0.475f - 0.06f));
        rgba.add(autoAdjust(0.500f + 0.000f, 1.000f, 0.525f + 0.06f));
        rgba.add(autoAdjust(0.500f + 0.080f, 0.523f, 0.525f + 0.10f));
        rgba.add(autoAdjust(0.500f + 0.080f, 0.523f, 0.475f - 0.10f));

        rgba.add(autoAdjust(0.750f - 0.080f, 0.353f, 0.475f - 0.12f));
        rgba.add(autoAdjust(0.750f - 0.125f, 0.894f, 0.475f - 0.08f));
        rgba.add(autoAdjust(0.750f + 0.000f, 1.000f, 0.475f - 0.06f));
        rgba.add(autoAdjust(0.750f + 0.080f, 0.523f, 0.475f - 0.10f));
        rgba.add(autoAdjust(0.750f + 0.080f, 0.523f, 0.525f + 0.10f));
        rgba.add(autoAdjust(0.750f + 0.000f, 1.000f, 0.525f + 0.06f));
        rgba.add(autoAdjust(0.750f - 0.125f, 0.894f, 0.525f + 0.08f));
        rgba.add(autoAdjust(0.750f - 0.080f, 0.353f, 0.525f + 0.12f));

        rgba.add(autoAdjust(0f, 0f, 0.666f));
        rgba.add(autoAdjust(0f, 0f, 1f));
        StringBuilder sb = new StringBuilder(12 * rgba.size + 256).append("{\n");
        for (int i = 0; i < rgba.size; i++) {
            StringKit.appendHex(sb.append("0x"), rgba.get(i)).append(", ");
            if(7 == (i & 7)) sb.append('\n');
        }
        System.out.println(sb.append("\n}"));
    }
}
