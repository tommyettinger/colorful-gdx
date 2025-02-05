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

import com.badlogic.gdx.utils.IntArray;
import com.github.tommyettinger.colorful.TrigTools;
import com.github.tommyettinger.colorful.internal.StringKit;
import com.github.tommyettinger.digital.MathTools;

// example output
/*
{
0x00000000, 0x000000FF, 0x555555FF, 0x5D4362FF, 0xAE8AB7FF, 0x9D6CDDFF, 0x68349FFF, 0x9D4B75FF,
0xD074A2FF, 0xD29D9FFF, 0x9C5656FF, 0x906751FF, 0xDAB29CFF, 0xE89581FF, 0xC54F37FF, 0xB27700FF,
0xEE9F00FF, 0xBCBC6CFF, 0x7A7A46FF, 0x576F54FF, 0x98BE8FFF, 0x97BD55FF, 0x67833AFF, 0x427666FF,
0x67A58CFF, 0x6E9E9FFF, 0x3F5958FF, 0x324851FF, 0x2D5B66FF, 0x0049B6FF, 0x493693FF, 0x837ED1FF,
0x2374FFFF, 0x4396A9FF, 0x6896AAFF, 0xAAAAAAFF, 0xFFFFFFFF,
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
        return ColorTools.toRGBA8888(ColorTools.oklabByHSL(hue - 0.03f,
                sat * (1f-0.5f*MathTools.square(TrigTools.cosTurns(hue))),
                lit + TrigTools.sinTurns(hue) * 0.1f, 1f));
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

        rgba.add(autoAdjust(0.000f - 0.080f, 0.447f, 0.475f - 0.12f));
        rgba.add(autoAdjust(0.000f - 0.080f, 0.447f, 0.525f + 0.12f));
        rgba.add(autoAdjust(0.000f - 0.125f, 0.894f, 0.525f + 0.08f));
        rgba.add(autoAdjust(0.000f - 0.125f, 0.894f, 0.475f - 0.08f));
        rgba.add(autoAdjust(0.000f + 0.000f, 1.000f, 0.475f - 0.06f));
        rgba.add(autoAdjust(0.000f + 0.000f, 1.000f, 0.525f + 0.06f));
        rgba.add(autoAdjust(0.000f + 0.080f, 0.632f, 0.525f + 0.10f));
        rgba.add(autoAdjust(0.000f + 0.080f, 0.632f, 0.475f - 0.10f));

        rgba.add(autoAdjust(0.250f - 0.080f, 0.447f, 0.475f - 0.12f));
        rgba.add(autoAdjust(0.250f - 0.080f, 0.447f, 0.525f + 0.12f));
        rgba.add(autoAdjust(0.250f - 0.125f, 0.894f, 0.525f + 0.08f));
        rgba.add(autoAdjust(0.250f - 0.125f, 0.894f, 0.475f - 0.08f));
        rgba.add(autoAdjust(0.250f + 0.000f, 1.000f, 0.475f - 0.06f));
        rgba.add(autoAdjust(0.250f + 0.000f, 1.000f, 0.525f + 0.06f));
        rgba.add(autoAdjust(0.250f + 0.080f, 0.632f, 0.525f + 0.10f));
        rgba.add(autoAdjust(0.250f + 0.080f, 0.632f, 0.475f - 0.10f));

        rgba.add(autoAdjust(0.500f - 0.080f, 0.447f, 0.475f - 0.12f));
        rgba.add(autoAdjust(0.500f - 0.080f, 0.447f, 0.525f + 0.12f));
        rgba.add(autoAdjust(0.500f - 0.125f, 0.894f, 0.525f + 0.08f));
        rgba.add(autoAdjust(0.500f - 0.125f, 0.894f, 0.475f - 0.08f));
        rgba.add(autoAdjust(0.500f + 0.000f, 1.000f, 0.475f - 0.06f));
        rgba.add(autoAdjust(0.500f + 0.000f, 1.000f, 0.525f + 0.06f));
        rgba.add(autoAdjust(0.500f + 0.080f, 0.632f, 0.525f + 0.10f));
        rgba.add(autoAdjust(0.500f + 0.080f, 0.632f, 0.475f - 0.10f));

        rgba.add(autoAdjust(0.750f - 0.080f, 0.447f, 0.475f - 0.12f));
        rgba.add(autoAdjust(0.750f - 0.125f, 0.894f, 0.475f - 0.08f));
        rgba.add(autoAdjust(0.750f + 0.000f, 1.000f, 0.475f - 0.06f));
        rgba.add(autoAdjust(0.750f + 0.080f, 0.632f, 0.475f - 0.10f));
        rgba.add(autoAdjust(0.750f + 0.080f, 0.632f, 0.525f + 0.10f));
        rgba.add(autoAdjust(0.750f + 0.000f, 1.000f, 0.525f + 0.06f));
        rgba.add(autoAdjust(0.750f - 0.125f, 0.894f, 0.525f + 0.08f));
        rgba.add(autoAdjust(0.750f - 0.080f, 0.447f, 0.525f + 0.12f));

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
