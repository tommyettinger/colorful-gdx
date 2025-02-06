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
import com.github.tommyettinger.digital.Interpolations;
import com.github.tommyettinger.digital.MathTools;

// example output
/*
{
0x00000000, 0x000000FF, 0x333333FF, 0x666666FF, 0xAA6294FF, 0xD5A0CEFF, 0xE740FEFF, 0xBE16B7FF,
0xFF0084FF, 0xFF62ADFF, 0xEBB9B3FF, 0xD48858FF, 0xB2AE7CFF, 0xEBD8BBFF, 0xFEB267FF, 0xCF9820FF,
0x00D200FF, 0x75E600FF, 0x83ECB5FF, 0x68BDA6FF, 0x76A9B0FF, 0x9DE0D8FF, 0x16E6C7FF, 0x22BBB5FF,
0x0094D1FF, 0x00B5EDFF, 0x77B0DCFF, 0x487DC1FF, 0x6469A9FF, 0x3560F9FF, 0x8500FBFF, 0x944BA8FF,
0xB684D4FF, 0x8C55FFFF, 0x5695FBFF, 0x9AA6C8FF, 0x854C6DFF, 0x422431FF, 0x6A0C54FF, 0xA11494FF,
0xEF0064FF, 0xBD001DFF, 0x635635FF, 0xA97B4CFF, 0x909569FF, 0x4C6042FF, 0x736D10FF, 0xB18A17FF,
0x00C13FFF, 0x009C56FF, 0x3E6F6AFF, 0x5EA294FF, 0x628A92FF, 0x3A5059FF, 0x007A84FF, 0x0EA6A7FF,
0x0084C1FF, 0x00649EFF, 0x222F7FFF, 0x3964ABFF, 0x4C4A91FF, 0x323EF8FF, 0x7700D5FF, 0x7A397FFF,
0x421F3EFF, 0x590089FF, 0x310AB1FF, 0x261F3CFF, 0x999999FF, 0xCCCCCCFF, 0xFFFFFFFF,
}
 */

/**
 * The shape of this linear palette looks <a href="https://imgur.com/DsPrUDR">like this</a>, but with the thick central
 * section stretched to double height. It uses 8 tiny 3D Hilbert
 * curves, each 2x2x2, arranged around a central column that extends further up and down.
 */
public class GardeniaPaletteGenerator {
    private static final int limit = 71;
    private static final IntArray rgba = new IntArray(limit);

    public static int autoAdjust(float hue, float sat, float lit) {
        return ColorTools.toRGBA8888(ColorTools.oklabByHSL(
                (float) ((Math.sqrt(MathTools.fract(hue + (0.5f - lit) * 0.3f) + 0.050625) - 0.225) * 1.25),
//                hue + 0.008f,
                (float) Math.pow(sat, 0.9f + (0.5f - lit) * (0.5f - lit)),
                Interpolations.smooth.apply(lit + TrigTools.sinTurns(hue) * 0.09f), 1f));
    }
    /*
jshell> //hue 0,1: -0.080, 1,1: +0.080, 0,2: -0.125, 1,2: 0.000
jshell> //saturation 0,1: 0.447, 1,1: 0.632, 0,2: 0.894, 1,2: 1.000
jshell> //lightness 0,1: 0.12f, 1,1: 0.10f, 0,2: 0.08f, 1,2: 0.06f
     */
    public static void main(String[] args) {
        rgba.add(0);

        rgba.add(ColorTools.toRGBA8888(ColorTools.oklab(0.0f, 0.5f, 0.5f, 1f)));
        rgba.add(ColorTools.toRGBA8888(ColorTools.oklab(0.2f, 0.5f, 0.5f, 1f)));
        rgba.add(ColorTools.toRGBA8888(ColorTools.oklab(0.4f, 0.5f, 0.5f, 1f)));

        rgba.add(autoAdjust(0.000f - 0.080f, 0.353f, 0.5000f + (0.12f * 0.90f - 0.12f * 0.60f)));
        rgba.add(autoAdjust(0.000f - 0.080f, 0.353f, 0.5000f + (0.12f * 0.90f + 0.12f * 0.60f)));
        rgba.add(autoAdjust(0.000f - 0.125f, 0.913f, 0.5000f + (0.08f * 0.90f + 0.08f * 0.60f)));
        rgba.add(autoAdjust(0.000f - 0.125f, 0.913f, 0.5000f + (0.08f * 0.90f - 0.08f * 0.60f)));
        rgba.add(autoAdjust(0.000f + 0.000f, 1.000f, 0.5000f + (0.06f * 0.90f - 0.06f * 0.60f)));
        rgba.add(autoAdjust(0.000f + 0.000f, 1.000f, 0.5000f + (0.06f * 0.90f + 0.06f * 0.60f)));
        rgba.add(autoAdjust(0.000f + 0.080f, 0.523f, 0.5000f + (0.10f * 0.90f + 0.10f * 0.60f)));
        rgba.add(autoAdjust(0.000f + 0.080f, 0.523f, 0.5000f + (0.10f * 0.90f - 0.10f * 0.60f)));

        rgba.add(autoAdjust(0.250f - 0.080f, 0.353f, 0.5000f + (0.12f * 0.90f - 0.12f * 0.60f)));
        rgba.add(autoAdjust(0.250f - 0.080f, 0.353f, 0.5000f + (0.12f * 0.90f + 0.12f * 0.60f)));
        rgba.add(autoAdjust(0.250f - 0.125f, 0.913f, 0.5000f + (0.08f * 0.90f + 0.08f * 0.60f)));
        rgba.add(autoAdjust(0.250f - 0.125f, 0.913f, 0.5000f + (0.08f * 0.90f - 0.08f * 0.60f)));
        rgba.add(autoAdjust(0.250f + 0.000f, 1.000f, 0.5000f + (0.06f * 0.90f - 0.06f * 0.60f)));
        rgba.add(autoAdjust(0.250f + 0.000f, 1.000f, 0.5000f + (0.06f * 0.90f + 0.06f * 0.60f)));
        rgba.add(autoAdjust(0.250f + 0.080f, 0.523f, 0.5000f + (0.10f * 0.90f + 0.10f * 0.60f)));
        rgba.add(autoAdjust(0.250f + 0.080f, 0.523f, 0.5000f + (0.10f * 0.90f - 0.10f * 0.60f)));

        rgba.add(autoAdjust(0.500f - 0.080f, 0.353f, 0.5000f + (0.12f * 0.90f - 0.12f * 0.60f)));
        rgba.add(autoAdjust(0.500f - 0.080f, 0.353f, 0.5000f + (0.12f * 0.90f + 0.12f * 0.60f)));
        rgba.add(autoAdjust(0.500f - 0.125f, 0.913f, 0.5000f + (0.08f * 0.90f + 0.08f * 0.60f)));
        rgba.add(autoAdjust(0.500f - 0.125f, 0.913f, 0.5000f + (0.08f * 0.90f - 0.08f * 0.60f)));
        rgba.add(autoAdjust(0.500f + 0.000f, 1.000f, 0.5000f + (0.06f * 0.90f - 0.06f * 0.60f)));
        rgba.add(autoAdjust(0.500f + 0.000f, 1.000f, 0.5000f + (0.06f * 0.90f + 0.06f * 0.60f)));
        rgba.add(autoAdjust(0.500f + 0.080f, 0.523f, 0.5000f + (0.10f * 0.90f + 0.10f * 0.60f)));
        rgba.add(autoAdjust(0.500f + 0.080f, 0.523f, 0.5000f + (0.10f * 0.90f - 0.10f * 0.60f)));

        rgba.add(autoAdjust(0.750f - 0.080f, 0.353f, 0.5000f + (0.12f * 0.90f - 0.12f * 0.60f)));
        rgba.add(autoAdjust(0.750f - 0.125f, 0.913f, 0.5000f + (0.08f * 0.90f - 0.08f * 0.60f)));
        rgba.add(autoAdjust(0.750f + 0.000f, 1.000f, 0.5000f + (0.06f * 0.90f - 0.06f * 0.60f)));
        rgba.add(autoAdjust(0.750f + 0.080f, 0.523f, 0.5000f + (0.10f * 0.90f - 0.10f * 0.60f)));
        rgba.add(autoAdjust(0.750f + 0.080f, 0.523f, 0.5000f + (0.10f * 0.90f + 0.10f * 0.60f)));
        rgba.add(autoAdjust(0.750f + 0.000f, 1.000f, 0.5000f + (0.06f * 0.90f + 0.06f * 0.60f)));
        rgba.add(autoAdjust(0.750f - 0.125f, 0.913f, 0.5000f + (0.08f * 0.90f + 0.08f * 0.60f)));
        rgba.add(autoAdjust(0.750f - 0.080f, 0.353f, 0.5000f + (0.12f * 0.90f + 0.12f * 0.60f)));


        rgba.add(autoAdjust(0.000f - 0.080f, 0.353f, 0.5000f - (0.12f * 0.90f - 0.12f * 0.60f)));
        rgba.add(autoAdjust(0.000f - 0.080f, 0.353f, 0.5000f - (0.12f * 0.90f + 0.12f * 0.60f)));
        rgba.add(autoAdjust(0.000f - 0.125f, 0.913f, 0.5000f - (0.08f * 0.90f + 0.08f * 0.60f)));
        rgba.add(autoAdjust(0.000f - 0.125f, 0.913f, 0.5000f - (0.08f * 0.90f - 0.08f * 0.60f)));
        rgba.add(autoAdjust(0.000f + 0.000f, 1.000f, 0.5000f - (0.06f * 0.90f - 0.06f * 0.60f)));
        rgba.add(autoAdjust(0.000f + 0.000f, 1.000f, 0.5000f - (0.06f * 0.90f + 0.06f * 0.60f)));
        rgba.add(autoAdjust(0.000f + 0.080f, 0.523f, 0.5000f - (0.10f * 0.90f + 0.10f * 0.60f)));
        rgba.add(autoAdjust(0.000f + 0.080f, 0.523f, 0.5000f - (0.10f * 0.90f - 0.10f * 0.60f)));

        rgba.add(autoAdjust(0.250f - 0.080f, 0.353f, 0.5000f - (0.12f * 0.90f - 0.12f * 0.60f)));
        rgba.add(autoAdjust(0.250f - 0.080f, 0.353f, 0.5000f - (0.12f * 0.90f + 0.12f * 0.60f)));
        rgba.add(autoAdjust(0.250f - 0.125f, 0.913f, 0.5000f - (0.08f * 0.90f + 0.08f * 0.60f)));
        rgba.add(autoAdjust(0.250f - 0.125f, 0.913f, 0.5000f - (0.08f * 0.90f - 0.08f * 0.60f)));
        rgba.add(autoAdjust(0.250f + 0.000f, 1.000f, 0.5000f - (0.06f * 0.90f - 0.06f * 0.60f)));
        rgba.add(autoAdjust(0.250f + 0.000f, 1.000f, 0.5000f - (0.06f * 0.90f + 0.06f * 0.60f)));
        rgba.add(autoAdjust(0.250f + 0.080f, 0.523f, 0.5000f - (0.10f * 0.90f + 0.10f * 0.60f)));
        rgba.add(autoAdjust(0.250f + 0.080f, 0.523f, 0.5000f - (0.10f * 0.90f - 0.10f * 0.60f)));

        rgba.add(autoAdjust(0.500f - 0.080f, 0.353f, 0.5000f - (0.12f * 0.90f - 0.12f * 0.60f)));
        rgba.add(autoAdjust(0.500f - 0.080f, 0.353f, 0.5000f - (0.12f * 0.90f + 0.12f * 0.60f)));
        rgba.add(autoAdjust(0.500f - 0.125f, 0.913f, 0.5000f - (0.08f * 0.90f + 0.08f * 0.60f)));
        rgba.add(autoAdjust(0.500f - 0.125f, 0.913f, 0.5000f - (0.08f * 0.90f - 0.08f * 0.60f)));
        rgba.add(autoAdjust(0.500f + 0.000f, 1.000f, 0.5000f - (0.06f * 0.90f - 0.06f * 0.60f)));
        rgba.add(autoAdjust(0.500f + 0.000f, 1.000f, 0.5000f - (0.06f * 0.90f + 0.06f * 0.60f)));
        rgba.add(autoAdjust(0.500f + 0.080f, 0.523f, 0.5000f - (0.10f * 0.90f + 0.10f * 0.60f)));
        rgba.add(autoAdjust(0.500f + 0.080f, 0.523f, 0.5000f - (0.10f * 0.90f - 0.10f * 0.60f)));

        rgba.add(autoAdjust(0.750f - 0.080f, 0.353f, 0.5000f - (0.12f * 0.90f - 0.12f * 0.60f)));
        rgba.add(autoAdjust(0.750f - 0.125f, 0.913f, 0.5000f - (0.08f * 0.90f - 0.08f * 0.60f)));
        rgba.add(autoAdjust(0.750f + 0.000f, 1.000f, 0.5000f - (0.06f * 0.90f - 0.06f * 0.60f)));
        rgba.add(autoAdjust(0.750f + 0.080f, 0.523f, 0.5000f - (0.10f * 0.90f - 0.10f * 0.60f)));
        rgba.add(autoAdjust(0.750f + 0.080f, 0.523f, 0.5000f - (0.10f * 0.90f + 0.10f * 0.60f)));
        rgba.add(autoAdjust(0.750f + 0.000f, 1.000f, 0.5000f - (0.06f * 0.90f + 0.06f * 0.60f)));
        rgba.add(autoAdjust(0.750f - 0.125f, 0.913f, 0.5000f - (0.08f * 0.90f + 0.08f * 0.60f)));
        rgba.add(autoAdjust(0.750f - 0.080f, 0.353f, 0.5000f - (0.12f * 0.90f + 0.12f * 0.60f)));

        rgba.add(ColorTools.toRGBA8888(ColorTools.oklab(0.6f, 0.5f, 0.5f, 1f)));
        rgba.add(ColorTools.toRGBA8888(ColorTools.oklab(0.8f, 0.5f, 0.5f, 1f)));
        rgba.add(ColorTools.toRGBA8888(ColorTools.oklab(1f, 0.5f, 0.5f, 1f)));

        StringBuilder sb = new StringBuilder(12 * rgba.size + 256).append("{\n");
        for (int i = 0; i < rgba.size; i++) {
            StringKit.appendHex(sb.append("0x"), rgba.get(i)).append(", ");
            if(7 == (i & 7)) sb.append('\n');
        }
        System.out.println(sb.append("\n}"));
    }
}
