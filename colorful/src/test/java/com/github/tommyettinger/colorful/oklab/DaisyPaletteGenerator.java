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

// example output
/*
{
0x00000000, 0x000000FF, 0x555555FF, 0x2C1728FF, 0xDDA6D5FF, 0xCF6EFAFF, 0x430B58FF, 0xC4005FFF,
0xFF5796FF, 0xF6CAC2FF, 0x86302CFF, 0x694E37FF, 0xF9ECE1FF, 0xFFC5ACFF, 0xA44210FF, 0xAE8500FF,
0xF5BA00FF, 0xD8F478FF, 0x5A6531FF, 0x304D39FF, 0xBCF0C1FF, 0x81F928FF, 0x3C7315FF, 0x007766FF,
0x00B89FFF, 0x64C2CFFF, 0x172E31FF, 0x0C1116FF, 0x09313EFF, 0x2100C5FF, 0x201135FF, 0xAC90E0FF,
0x4661FFFF, 0x18AFE1FF, 0x85B2D3FF, 0xAAAAAAFF, 0xFFFFFFFF,
}
 */

/**
 * The shape of this linear palette looks <a href="https://imgur.com/DsPrUDR">like this</a>. It uses 4 tiny 3D Hilbert
 * curves, each 2x2x2, arranged around a central column that extends further up and down.
 */
public class DaisyPaletteGenerator {
    private static final int limit = 37;
    private static final IntArray rgba = new IntArray(limit);

    public static int autoAdjust(float hue, float sat, float lit) {
        return ColorTools.toRGBA8888(ColorTools.oklabByHSL(hue, sat, lit + TrigTools.sinTurns(hue) * 0.15f, 1f));
    }
    /*
jshell> //hue 0,1: -0.080, 1,1: +0.080, 0,2: -0.125, 1,2: 0.000
jshell> //saturation 0,1: 0.447, 1,1: 0.632, 0,2: 0.894, 1,2: 1.000
jshell> //lightness 0,1: 0.20, 1,1: 0.16, 0,2: 0.12, 1,2: 0.08
     */
    public static void main(String[] args) {
        rgba.add(0);
        rgba.add(autoAdjust(0f, 0f, 0f));
        rgba.add(autoAdjust(0f, 0f, 0.333f));

        rgba.add(autoAdjust(0.000f - 0.080f, 0.447f, 0.400f - 0.200f));
        rgba.add(autoAdjust(0.000f - 0.080f, 0.447f, 0.600f + 0.200f));
        rgba.add(autoAdjust(0.000f - 0.125f, 0.894f, 0.600f + 0.120f));
        rgba.add(autoAdjust(0.000f - 0.125f, 0.894f, 0.400f - 0.120f));
        rgba.add(autoAdjust(0.000f + 0.000f, 1.000f, 0.400f - 0.008f));
        rgba.add(autoAdjust(0.000f + 0.000f, 1.000f, 0.600f + 0.008f));
        rgba.add(autoAdjust(0.000f + 0.080f, 0.632f, 0.600f + 0.160f));
        rgba.add(autoAdjust(0.000f + 0.080f, 0.632f, 0.400f - 0.160f));

        rgba.add(autoAdjust(0.250f - 0.080f, 0.447f, 0.400f - 0.200f));
        rgba.add(autoAdjust(0.250f - 0.080f, 0.447f, 0.600f + 0.200f));
        rgba.add(autoAdjust(0.250f - 0.125f, 0.894f, 0.600f + 0.120f));
        rgba.add(autoAdjust(0.250f - 0.125f, 0.894f, 0.400f - 0.120f));
        rgba.add(autoAdjust(0.250f + 0.000f, 1.000f, 0.400f - 0.008f));
        rgba.add(autoAdjust(0.250f + 0.000f, 1.000f, 0.600f + 0.008f));
        rgba.add(autoAdjust(0.250f + 0.080f, 0.632f, 0.600f + 0.160f));
        rgba.add(autoAdjust(0.250f + 0.080f, 0.632f, 0.400f - 0.160f));

        rgba.add(autoAdjust(0.500f - 0.080f, 0.447f, 0.400f - 0.200f));
        rgba.add(autoAdjust(0.500f - 0.080f, 0.447f, 0.600f + 0.200f));
        rgba.add(autoAdjust(0.500f - 0.125f, 0.894f, 0.600f + 0.120f));
        rgba.add(autoAdjust(0.500f - 0.125f, 0.894f, 0.400f - 0.120f));
        rgba.add(autoAdjust(0.500f + 0.000f, 1.000f, 0.400f - 0.008f));
        rgba.add(autoAdjust(0.500f + 0.000f, 1.000f, 0.600f + 0.008f));
        rgba.add(autoAdjust(0.500f + 0.080f, 0.632f, 0.600f + 0.160f));
        rgba.add(autoAdjust(0.500f + 0.080f, 0.632f, 0.400f - 0.160f));

        rgba.add(autoAdjust(0.750f - 0.080f, 0.447f, 0.400f - 0.200f));
        rgba.add(autoAdjust(0.750f - 0.125f, 0.894f, 0.400f - 0.120f));
        rgba.add(autoAdjust(0.750f + 0.000f, 1.000f, 0.400f - 0.008f));
        rgba.add(autoAdjust(0.750f + 0.080f, 0.632f, 0.400f - 0.160f));
        rgba.add(autoAdjust(0.750f + 0.080f, 0.632f, 0.600f + 0.160f));
        rgba.add(autoAdjust(0.750f + 0.000f, 1.000f, 0.600f + 0.008f));
        rgba.add(autoAdjust(0.750f - 0.125f, 0.894f, 0.600f + 0.120f));
        rgba.add(autoAdjust(0.750f - 0.080f, 0.447f, 0.600f + 0.200f));

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
