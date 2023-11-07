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
import com.github.tommyettinger.colorful.FloatColors;
import com.github.tommyettinger.colorful.internal.StringKit;
import com.github.tommyettinger.colorful.rgb.ColorTools;

// example output
/* // with Oklab's ColorTools
{
0x00000000, 0xFF1D1DFF, 0xE33110FF, 0xF48341FF, 0xEB8A09FF, 0xFED242FF, 0xF8EB0BFF, 0xE4F761FF,
0xB6EE29FF, 0x73E800FF, 0x72FA36FF, 0x20EF00FF, 0x57F159FF, 0x1FE857FF, 0x5DFBA0FF, 0x26F6A6FF,
0x00D7B3FF, 0x46ECEAFF, 0x0DC8F3FF, 0x4AB6F9FF, 0x1E78EFFF, 0x6388F1FF, 0x1F2FFDFF, 0x2507DCFF,
0x773DEFFF, 0x7C17E1FF, 0xC444F9FF, 0xD819F3FF, 0xF35FEDFF, 0xEA31BAFF, 0xFD66BEFF, 0xF7357DFF,
0xE50E39FF,
}
// with RGB's ColorTools
{
0x00000000, 0xFE2323FF, 0xE33006FF, 0xF4823FFF, 0xE88B13FF, 0xFED046FF, 0xF8EA15FF, 0xE3F660FF,
0xB3EE31FF, 0x74E800FF, 0x74F937FF, 0x28EE0CFF, 0x52F15CFF, 0x25E755FF, 0x5AFBA0FF, 0x2AF4A8FF,
0x10D6B1FF, 0x45EBEBFF, 0x05C7F4FF, 0x4BB6F6FF, 0x1D78EDFF, 0x6587EEFF, 0x222FFAFF, 0x240ADCFF,
0x753EF0FF, 0x7C17E1FF, 0xC245FBFF, 0xD815F4FF, 0xF35EEAFF, 0xEA31BCFF, 0xFC67BBFF, 0xF6367EFF,
0xE60F38FF,
}
{
0x00000000, 0xF24848FF, 0xFD3406FF, 0xFA9152FF, 0xF49820FF, 0xF5D269FF, 0xFDF029FF, 0xEAFB74FF,
0xBEF642FF, 0x81EF12FF, 0x84FE4CFF, 0x36F91AFF, 0x64F86DFF, 0x33F263FF, 0x70FEAEFF, 0x3DFAB3FF,
0x0CF5C9FF, 0x55F4F4FF, 0x14D2FEFF, 0x5FC1FBFF, 0x2E85F6FF, 0x7696F6FF, 0x4651F0FF, 0x2305FAFF,
0x8450F8FF, 0x881FF2FF, 0xC767F3FF, 0xE028FBFF, 0xF972F1FF, 0xF440C7FF, 0xF588C5FF, 0xFC4A8DFF,
0xF71942FF, }

 */
public class SwoopPaletteGenerator {
    private static final int limit = 33;
    private static final IntArray rgba = new IntArray(limit);

    public static void main(String[] args) {
        rgba.add(0);
        float s = 0.4f, l = 0.9f;
        for (int i = 0; i < 32; i++) {
            rgba.add(ColorTools.toRGBA8888(FloatColors.hsl2rgb(0x1p-5f * i,
                    ((s += 0.7548776662466927f) - (int)(s)) * 0.15f + 0.85f,
                    ((l += 0.5698402909980532f) - (int)(l)) * 0.25f + 0.5f,
                    1f)));
        }

        StringBuilder sb = new StringBuilder(12 * rgba.size + 35).append("{\n");
        for (int i = 0; i < rgba.size; i++) {
            StringKit.appendHex(sb.append("0x"), rgba.get(i)).append(", ");
            if(7 == (i & 7)) sb.append('\n');
        }
        System.out.println(sb.append('}'));
    }
}
