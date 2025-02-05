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
0x00000000, 0x000000FF, 0x5E585AFF, 0x4E2A39FF, 0xC78DBCFF, 0xE027EFFF, 0x6C0058FF, 0xC0002DFF,
0xFF60A6FF, 0xE3B6A8FF, 0x76613BFF, 0x61754FFF, 0xE5D5A9FF, 0xFEB457FF, 0x857711FF, 0x00A953FF,
0x67EF00FF, 0x8BE7BFFF, 0x47827BFF, 0x45636BFF, 0xA0D4D6FF, 0x28E4CDFF, 0x008790FF, 0x00679CFF,
0x00B3EAFF, 0x6FA0D2FF, 0x18368AFF, 0x2A2445FF, 0x2D0FB1FF, 0x510082FF, 0x461F44FF, 0xAA6FC6FF,
0x8942FFFF, 0x4F86F8FF, 0x8491B7FF, 0xA5A5A5FF, 0xFFFFFFFF,
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
                (float) ((Math.sqrt(MathTools.fract(hue + (0.5f - lit) * 0.25f) + 0.050625) - 0.225) * 1.25),
//                hue + 0.008f,
                sat + (0.5f - lit) * 0.25f,
                Interpolations.smooth.apply(lit + TrigTools.sinTurns(hue) * 0.11f), 1f));
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
