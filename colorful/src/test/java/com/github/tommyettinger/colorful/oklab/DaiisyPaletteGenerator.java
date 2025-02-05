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
0x00000000, 0x000000FF, 0x5A5A5AFF, 0x50303EFF, 0xCC91C0FF, 0xE925FBFF, 0x730D5DFF, 0xC0001FFF,
0xFF5DABFF, 0xE4B1A5FF, 0x725D37FF, 0x5D6C4EFF, 0xE0D0A4FF, 0xFFAC49FF, 0x7C7304FF, 0x009F52FF,
0x6FE400FF, 0x80E2B3FF, 0x477974FF, 0x465D67FF, 0x98D2CCFF, 0x29DFC2FF, 0x14808BFF, 0x0066A1FF,
0x00B3EAFF, 0x73A5D7FF, 0x22388EFF, 0x2F294BFF, 0x3411BDFF, 0x5E0090FF, 0x4D254BFF, 0xAE78CEFF,
0x8F52FFFF, 0x4C8DFFFF, 0x8B98BFFF, 0xA5A5A5FF, 0xFFFFFFFF,
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
        return ColorTools.toRGBA8888(ColorTools.oklabByHSL(
                (float) ((Math.sqrt(MathTools.fract(hue + (0.5f - lit) * 0.3f) + 0.050625) - 0.225) * 1.25),
//                hue + 0.008f,
                sat,
                Interpolations.smooth.apply(lit + TrigTools.sinTurns(hue) * 0.09f), 1f));
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
