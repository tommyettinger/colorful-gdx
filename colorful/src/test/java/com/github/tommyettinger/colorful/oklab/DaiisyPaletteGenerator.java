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
0x00000000, 0x000000FF, 0x5A5A5AFF, 0x522B44FF, 0xD18DB9FF, 0xF117E2FF, 0x6D0067FF, 0xBA005AFF,
0xFF639BFF, 0xF5D2B9FF, 0xAE7449FF, 0x808253FF, 0xE5E89AFF, 0xFFD36CFF, 0xB78A1AFF, 0x00A014FF,
0x00EE2EFF, 0x69C8AFFF, 0x315E55FF, 0x23353AFF, 0x74A7AEFF, 0x17B4AFFF, 0x005956FF, 0x003A54FF,
0x007EB4FF, 0x4279C8FF, 0x0F223CFF, 0x191734FF, 0x0F0B77FF, 0x46007CFF, 0x451F4CFF, 0xC25CD5FF,
0x9512FFFF, 0x3A5BFBFF, 0x7379BBFF, 0xA5A5A5FF, 0xFFFFFFFF,
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
        hue = (float) ((Math.sqrt(MathTools.fract(hue) + 0.050625) - 0.225) * 1.25);
        return ColorTools.toRGBA8888(ColorTools.oklabByHSL(
                hue,
//                hue + 0.008f,
                (float) Math.pow(sat, 0.8f),
                Interpolations.smooth.apply(lit + TrigTools.sinTurns(hue) * 0.15f), 1f));
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
