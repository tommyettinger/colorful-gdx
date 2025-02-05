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
0x00000000, 0x000000FF, 0x555555FF, 0x5F4166FF, 0xB585BCFF, 0xA75EF6FF, 0x701FB5FF, 0xC80081FF,
0xFF3CAEFF, 0xDC9899FF, 0xAB4B50FF, 0x95644BFF, 0xDAB29CFF, 0xF88D74FF, 0xDA3B1DFF, 0xB27700FF,
0xEE9F00FF, 0xC1BB5DFF, 0x7C7A3FFF, 0x55704FFF, 0x8FC289FF, 0x90C130FF, 0x648522FF, 0x00805EFF,
0x00B482FF, 0x5EA1A5FF, 0x325B5DFF, 0x304755FF, 0x145D6FFF, 0x0049B6FF, 0x483598FF, 0x867BD6FF,
0x2374FFFF, 0x2599B4FF, 0x6696AFFF, 0xAAAAAAFF, 0xFFFFFFFF,
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
                        (float)Math.pow(sat, 0.9f+0.6f*MathTools.square(TrigTools.cosTurns(hue))),
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
