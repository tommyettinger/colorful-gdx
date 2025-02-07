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
0x00000000, 0x000000FF, 0x333333FF, 0x110909FF, 0x693B4FFF, 0xB60BB5FF, 0x50052DFF, 0x7B0500FF,
0xD10100FF, 0x933020FF, 0x2B1309FF, 0x110E09FF, 0x694E3BFF, 0xB6330BFF, 0x502105FF, 0x7B6100FF,
0xD18500FF, 0x8C9320FF, 0x1E2B09FF, 0x091109FF, 0x4E693BFF, 0x8AB60BFF, 0x285005FF, 0x007B18FF,
0x08D100FF, 0x209364FF, 0x092A2BFF, 0x090A11FF, 0x053D50FF, 0x11007BFF, 0x2B0924FF, 0x6A2093FF,
0x0023D1FF, 0x0BB6A1FF, 0x3B5A69FF, 0x666666FF, 0x999999FF, 0xBD95C3FF, 0xEFEDF5FF, 0xB8AEF9FF,
0xA848F3FF, 0xFF2D53FF, 0xFF83C6FF, 0xF5D3D6FF, 0xDE706BFF, 0xC39E95FF, 0xF5EEEDFF, 0xF9B1AEFF,
0xF35D48FF, 0xFF962DFF, 0xFFB283FF, 0xF5E5D3FF, 0xDEC46BFF, 0xBBC395FF, 0xF5F3EDFF, 0xF9E9AEFF,
0xF1F348FF, 0x65FF2DFF, 0xBEFF83FF, 0xDAF5D3FF, 0x6BDE7FFF, 0x95C3B8FF, 0x48F3A2FF, 0x2D8FFFFF,
0x776BDEFF, 0xD3E1F5FF, 0x83E1FFFF, 0xAEF9BDFF, 0xEDF5EFFF, 0xCCCCCCFF, 0xFFFFFFFF,
}
 */

/**
 * The shape of this linear palette looks <a href="https://i.imgur.com/dkhGFi0.png">like this</a>.
 * It uses 8 tiny 3D Hilbert curves, each 2x2x2, arranged around a central column that extends further up and down.
 */
public class StaccaPaletteGenerator {
    private static final int limit = 71;
    private static final IntArray rgba = new IntArray(limit);

    public static int autoAdjust(float hue, float sat, float lit) {
        return ColorTools.toRGBA8888(FloatColors.hsl2rgb(
                (float) Math.pow(MathTools.fract(hue + (0.5f - lit) * 0.3f), 1.7f),
//                MathTools.barronSpline((float) Math.pow(MathTools.fract(hue + (0.5f - lit) * 0.3f), 1.6f), 1.25f, 0.4f),
//                (float) ((Math.sqrt(MathTools.fract(hue + (0.5f - lit) * 0.3f) + 0.050625) - 0.225) * 1.25),
//                hue + 0.008f,
                sat,
                Interpolations.smooth.apply(lit), 1f));
    }
    /*
jshell> //hue 0,1: -0.080, 1,1: +0.080, 0,2: -0.125, 1,2: 0.000
jshell> //saturation 0,1: 0.447, 1,1: 0.632, 0,2: 0.894, 1,2: 1.000
jshell> //lightness 0,1: 0.12f, 1,1: 0.10f, 0,2: 0.08f, 1,2: 0.06f
     */
    public static void main(String[] args) {
        rgba.add(0);

        rgba.add(ColorTools.toRGBA8888(FloatColors.hsl2rgb(0f, 0f, 0.0f, 1f)));
        rgba.add(ColorTools.toRGBA8888(FloatColors.hsl2rgb(0f, 0f, 0.2f, 1f)));

        rgba.add(autoAdjust(0.000f - 0.080f, 00.28f, 0.5000f - (0.12f * 2.00f + 0.12f)));
        rgba.add(autoAdjust(0.000f - 0.080f, 00.28f, 0.5000f - (0.12f * 2.00f - 0.12f)));
        rgba.add(autoAdjust(0.000f - 0.125f, 00.88f, 0.5000f - (0.08f * 2.00f - 0.08f)));
        rgba.add(autoAdjust(0.000f - 0.125f, 00.88f, 0.5000f - (0.08f * 2.00f + 0.08f)));
        rgba.add(autoAdjust(0.000f + 0.000f, 01.00f, 0.5000f - (0.06f * 2.00f + 0.06f)));
        rgba.add(autoAdjust(0.000f + 0.000f, 01.00f, 0.5000f - (0.06f * 2.00f - 0.06f)));
        rgba.add(autoAdjust(0.000f + 0.080f, 00.64f, 0.5000f - (0.10f * 2.00f - 0.10f)));
        rgba.add(autoAdjust(0.000f + 0.080f, 00.64f, 0.5000f - (0.10f * 2.00f + 0.10f)));

        rgba.add(autoAdjust(0.250f - 0.080f, 00.28f, 0.5000f - (0.12f * 2.00f + 0.12f)));
        rgba.add(autoAdjust(0.250f - 0.080f, 00.28f, 0.5000f - (0.12f * 2.00f - 0.12f)));
        rgba.add(autoAdjust(0.250f - 0.125f, 00.88f, 0.5000f - (0.08f * 2.00f - 0.08f)));
        rgba.add(autoAdjust(0.250f - 0.125f, 00.88f, 0.5000f - (0.08f * 2.00f + 0.08f)));
        rgba.add(autoAdjust(0.250f + 0.000f, 01.00f, 0.5000f - (0.06f * 2.00f + 0.06f)));
        rgba.add(autoAdjust(0.250f + 0.000f, 01.00f, 0.5000f - (0.06f * 2.00f - 0.06f)));
        rgba.add(autoAdjust(0.250f + 0.080f, 00.64f, 0.5000f - (0.10f * 2.00f - 0.10f)));
        rgba.add(autoAdjust(0.250f + 0.080f, 00.64f, 0.5000f - (0.10f * 2.00f + 0.10f)));

        rgba.add(autoAdjust(0.500f - 0.080f, 00.28f, 0.5000f - (0.12f * 2.00f + 0.12f)));
        rgba.add(autoAdjust(0.500f - 0.080f, 00.28f, 0.5000f - (0.12f * 2.00f - 0.12f)));
        rgba.add(autoAdjust(0.500f - 0.125f, 00.88f, 0.5000f - (0.08f * 2.00f - 0.08f)));
        rgba.add(autoAdjust(0.500f - 0.125f, 00.88f, 0.5000f - (0.08f * 2.00f + 0.08f)));
        rgba.add(autoAdjust(0.500f + 0.000f, 01.00f, 0.5000f - (0.06f * 2.00f + 0.06f)));
        rgba.add(autoAdjust(0.500f + 0.000f, 01.00f, 0.5000f - (0.06f * 2.00f - 0.06f)));
        rgba.add(autoAdjust(0.500f + 0.080f, 00.64f, 0.5000f - (0.10f * 2.00f - 0.10f)));
        rgba.add(autoAdjust(0.500f + 0.080f, 00.64f, 0.5000f - (0.10f * 2.00f + 0.10f)));

        rgba.add(autoAdjust(0.750f - 0.080f, 00.28f, 0.5000f - (0.12f * 2.00f + 0.12f)));
        rgba.add(autoAdjust(0.750f - 0.125f, 00.88f, 0.5000f - (0.08f * 2.00f + 0.08f)));
        rgba.add(autoAdjust(0.750f + 0.000f, 01.00f, 0.5000f - (0.06f * 2.00f + 0.06f)));
        rgba.add(autoAdjust(0.750f + 0.080f, 00.64f, 0.5000f - (0.10f * 2.00f + 0.10f)));
        rgba.add(autoAdjust(0.750f + 0.080f, 00.64f, 0.5000f - (0.10f * 2.00f - 0.10f)));
        rgba.add(autoAdjust(0.750f + 0.000f, 01.00f, 0.5000f - (0.06f * 2.00f - 0.06f)));
        rgba.add(autoAdjust(0.750f - 0.125f, 00.88f, 0.5000f - (0.08f * 2.00f - 0.08f)));
        rgba.add(autoAdjust(0.750f - 0.080f, 00.28f, 0.5000f - (0.12f * 2.00f - 0.12f)));

        rgba.add(ColorTools.toRGBA8888(FloatColors.hsl2rgb(0f, 0f, 0.4f, 1f)));
        rgba.add(ColorTools.toRGBA8888(FloatColors.hsl2rgb(0f, 0f, 0.6f, 1f)));

        rgba.add(autoAdjust(0.000f - 0.080f, 00.28f, 0.5000f + (0.12f * 2.00f - 0.12f)));
        rgba.add(autoAdjust(0.000f - 0.080f, 00.28f, 0.5000f + (0.12f * 2.00f + 0.12f)));
        rgba.add(autoAdjust(0.000f - 0.125f, 00.88f, 0.5000f + (0.08f * 2.00f + 0.08f)));
        rgba.add(autoAdjust(0.000f - 0.125f, 00.88f, 0.5000f + (0.08f * 2.00f - 0.08f)));
        rgba.add(autoAdjust(0.000f + 0.000f, 01.00f, 0.5000f + (0.06f * 2.00f - 0.06f)));
        rgba.add(autoAdjust(0.000f + 0.000f, 01.00f, 0.5000f + (0.06f * 2.00f + 0.06f)));
        rgba.add(autoAdjust(0.000f + 0.080f, 00.64f, 0.5000f + (0.10f * 2.00f + 0.10f)));
        rgba.add(autoAdjust(0.000f + 0.080f, 00.64f, 0.5000f + (0.10f * 2.00f - 0.10f)));
        rgba.add(autoAdjust(0.250f - 0.080f, 00.28f, 0.5000f + (0.12f * 2.00f - 0.12f)));
        rgba.add(autoAdjust(0.250f - 0.080f, 00.28f, 0.5000f + (0.12f * 2.00f + 0.12f)));
        rgba.add(autoAdjust(0.250f - 0.125f, 00.88f, 0.5000f + (0.08f * 2.00f + 0.08f)));
        rgba.add(autoAdjust(0.250f - 0.125f, 00.88f, 0.5000f + (0.08f * 2.00f - 0.08f)));
        rgba.add(autoAdjust(0.250f + 0.000f, 01.00f, 0.5000f + (0.06f * 2.00f - 0.06f)));
        rgba.add(autoAdjust(0.250f + 0.000f, 01.00f, 0.5000f + (0.06f * 2.00f + 0.06f)));
        rgba.add(autoAdjust(0.250f + 0.080f, 00.64f, 0.5000f + (0.10f * 2.00f + 0.10f)));
        rgba.add(autoAdjust(0.250f + 0.080f, 00.64f, 0.5000f + (0.10f * 2.00f - 0.10f)));
        rgba.add(autoAdjust(0.500f - 0.080f, 00.28f, 0.5000f + (0.12f * 2.00f - 0.12f)));
        rgba.add(autoAdjust(0.500f - 0.080f, 00.28f, 0.5000f + (0.12f * 2.00f + 0.12f)));
        rgba.add(autoAdjust(0.500f - 0.125f, 00.88f, 0.5000f + (0.08f * 2.00f + 0.08f)));
        rgba.add(autoAdjust(0.500f - 0.125f, 00.88f, 0.5000f + (0.08f * 2.00f - 0.08f)));
        rgba.add(autoAdjust(0.500f + 0.000f, 01.00f, 0.5000f + (0.06f * 2.00f - 0.06f)));
        rgba.add(autoAdjust(0.500f + 0.000f, 01.00f, 0.5000f + (0.06f * 2.00f + 0.06f)));
        rgba.add(autoAdjust(0.500f + 0.080f, 00.64f, 0.5000f + (0.10f * 2.00f + 0.10f)));
        rgba.add(autoAdjust(0.500f + 0.080f, 00.64f, 0.5000f + (0.10f * 2.00f - 0.10f)));
        rgba.add(autoAdjust(0.750f - 0.080f, 00.28f, 0.5000f + (0.12f * 2.00f - 0.12f)));
        rgba.add(autoAdjust(0.750f - 0.125f, 00.88f, 0.5000f + (0.08f * 2.00f - 0.08f)));
        rgba.add(autoAdjust(0.750f + 0.000f, 01.00f, 0.5000f + (0.06f * 2.00f - 0.06f)));
        rgba.add(autoAdjust(0.750f + 0.080f, 00.64f, 0.5000f + (0.10f * 2.00f - 0.10f)));
        rgba.add(autoAdjust(0.750f + 0.080f, 00.64f, 0.5000f + (0.10f * 2.00f + 0.10f)));
        rgba.add(autoAdjust(0.750f + 0.000f, 01.00f, 0.5000f + (0.06f * 2.00f + 0.06f)));
        rgba.add(autoAdjust(0.750f - 0.125f, 00.88f, 0.5000f + (0.08f * 2.00f + 0.08f)));
        rgba.add(autoAdjust(0.750f - 0.080f, 00.28f, 0.5000f + (0.12f * 2.00f + 0.12f)));

        rgba.add(ColorTools.toRGBA8888(FloatColors.hsl2rgb(0f, 0f, 0.8f, 1f)));
        rgba.add(ColorTools.toRGBA8888(FloatColors.hsl2rgb(0f, 0f, 1.0f, 1f)));

        StringBuilder sb = new StringBuilder(12 * rgba.size + 256).append("{\n");
        for (int i = 0; i < rgba.size; i++) {
            StringKit.appendHex(sb.append("0x"), rgba.get(i)).append(", ");
            if(7 == (i & 7)) sb.append('\n');
        }
        System.out.println(sb.append("\n}"));
    }
}
