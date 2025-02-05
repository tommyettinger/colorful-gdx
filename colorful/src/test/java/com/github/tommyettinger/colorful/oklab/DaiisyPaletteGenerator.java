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
0x00000000, 0x000000FF, 0x5A5A5AFF, 0x372431FF, 0xBA7FA7FF, 0xC000D9FF, 0x470052FF, 0xBB004CFF,
0xFF668BFF, 0xEEC2BAFF, 0xB05841FF, 0x9B846CFF, 0xF3E6DBFF, 0xFFC6A0FF, 0xE36300FF, 0xC4A700FF,
0xFFDE20FF, 0xC9F68EFF, 0x819A56FF, 0x577362FF, 0xB2E3C3FF, 0x4DFF46FF, 0x0AAD00FF, 0x00706AFF,
0x00BDAFFF, 0x5998A8FF, 0x1C363EFF, 0x11171CFF, 0x002F44FF, 0x1C0066FF, 0x221430FF, 0x8852BFFF,
0x4708FFFF, 0x0080B6FF, 0x627A95FF, 0xA5A5A5FF, 0xFFFFFFFF,
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
        return ColorTools.toRGBA8888(ColorTools.oklabByHSL(hue + 0.02f,
                Interpolations.smooth.apply(sat),
                Interpolations.smooth.apply(lit + TrigTools.sinTurns(hue) * 0.19f), 1f));
    }
    /*
jshell> //hue 0,1: -0.080, 1,1: +0.080, 0,2: -0.125, 1,2: 0.000
jshell> //saturation 0,1: 0.447, 1,1: 0.632, 0,2: 0.894, 1,2: 1.000
jshell> //lightness 0,1: 0.12f, 1,1: 0.10f, 0,2: 0.08f, 1,2: 0.06f
     */
    public static void main(String[] args) {
        rgba.add(0);
        rgba.add(autoAdjust(0f, 0f, 0f));
        rgba.add(autoAdjust(0f, 0f, 0.4f));

        rgba.add(autoAdjust(0.000f - 0.080f, 0.353f, 0.475f - 0.12f));
        rgba.add(autoAdjust(0.000f - 0.080f, 0.353f, 0.525f + 0.12f));
        rgba.add(autoAdjust(0.000f - 0.125f, 0.913f, 0.525f + 0.08f));
        rgba.add(autoAdjust(0.000f - 0.125f, 0.913f, 0.475f - 0.08f));
        rgba.add(autoAdjust(0.000f + 0.000f, 1.000f, 0.475f - 0.06f));
        rgba.add(autoAdjust(0.000f + 0.000f, 1.000f, 0.525f + 0.06f));
        rgba.add(autoAdjust(0.000f + 0.080f, 0.523f, 0.525f + 0.10f));
        rgba.add(autoAdjust(0.000f + 0.080f, 0.523f, 0.475f - 0.10f));

        rgba.add(autoAdjust(0.250f - 0.080f, 0.353f, 0.475f - 0.12f));
        rgba.add(autoAdjust(0.250f - 0.080f, 0.353f, 0.525f + 0.12f));
        rgba.add(autoAdjust(0.250f - 0.125f, 0.913f, 0.525f + 0.08f));
        rgba.add(autoAdjust(0.250f - 0.125f, 0.913f, 0.475f - 0.08f));
        rgba.add(autoAdjust(0.250f + 0.000f, 1.000f, 0.475f - 0.06f));
        rgba.add(autoAdjust(0.250f + 0.000f, 1.000f, 0.525f + 0.06f));
        rgba.add(autoAdjust(0.250f + 0.080f, 0.523f, 0.525f + 0.10f));
        rgba.add(autoAdjust(0.250f + 0.080f, 0.523f, 0.475f - 0.10f));

        rgba.add(autoAdjust(0.500f - 0.080f, 0.353f, 0.475f - 0.12f));
        rgba.add(autoAdjust(0.500f - 0.080f, 0.353f, 0.525f + 0.12f));
        rgba.add(autoAdjust(0.500f - 0.125f, 0.913f, 0.525f + 0.08f));
        rgba.add(autoAdjust(0.500f - 0.125f, 0.913f, 0.475f - 0.08f));
        rgba.add(autoAdjust(0.500f + 0.000f, 1.000f, 0.475f - 0.06f));
        rgba.add(autoAdjust(0.500f + 0.000f, 1.000f, 0.525f + 0.06f));
        rgba.add(autoAdjust(0.500f + 0.080f, 0.523f, 0.525f + 0.10f));
        rgba.add(autoAdjust(0.500f + 0.080f, 0.523f, 0.475f - 0.10f));

        rgba.add(autoAdjust(0.750f - 0.080f, 0.353f, 0.475f - 0.12f));
        rgba.add(autoAdjust(0.750f - 0.125f, 0.913f, 0.475f - 0.08f));
        rgba.add(autoAdjust(0.750f + 0.000f, 1.000f, 0.475f - 0.06f));
        rgba.add(autoAdjust(0.750f + 0.080f, 0.523f, 0.475f - 0.10f));
        rgba.add(autoAdjust(0.750f + 0.080f, 0.523f, 0.525f + 0.10f));
        rgba.add(autoAdjust(0.750f + 0.000f, 1.000f, 0.525f + 0.06f));
        rgba.add(autoAdjust(0.750f - 0.125f, 0.913f, 0.525f + 0.08f));
        rgba.add(autoAdjust(0.750f - 0.080f, 0.353f, 0.525f + 0.12f));

        rgba.add(autoAdjust(0f, 0f, 0.6f));
        rgba.add(autoAdjust(0f, 0f, 1f));
        StringBuilder sb = new StringBuilder(12 * rgba.size + 256).append("{\n");
        for (int i = 0; i < rgba.size; i++) {
            StringKit.appendHex(sb.append("0x"), rgba.get(i)).append(", ");
            if(7 == (i & 7)) sb.append('\n');
        }
        System.out.println(sb.append("\n}"));
    }
}
