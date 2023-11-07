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
import com.github.tommyettinger.colorful.internal.StringKit;

// example output
/*
{
0x00000000, 0xE80071FF, 0xFF256EFF, 0xF52B3DFF, 0xB6361EFF, 0xEF5400FF, 0xAD5400FF, 0xCF7E27FF,
0x9A6D28FF, 0xC29500FF, 0x91800FFF, 0x676916FF, 0x77962AFF, 0x4A7E2AFF, 0x0DB335FF, 0x188E53FF,
0x2FB58CFF, 0x339281FF, 0x00746FFF, 0x269BA5FF, 0x27798BFF, 0x439FC3FF, 0x007FBCFF, 0x1BA1FFFF,
0x2576FFFF, 0x343DFFFF, 0x7D14FFFF, 0x7B24FFFF, 0xBF3BFFFF, 0xA838D6FF, 0xFF02FFFF, 0xDA21B2FF,
0xAD2475FF,
}
 */
public class TalonPaletteGenerator {
    private static final int limit = 33;
    private static final IntArray rgba = new IntArray(limit);

    public static void main(String[] args) {
        rgba.add(0);
        float s = 0.2f, l = 0.8f;
        for (int i = 0; i < 32; i++) {
            rgba.add(ColorTools.toRGBA8888(ColorTools.oklabByHSL(0x1p-5f * i,
                    ((s += 0.7548776662466927f) - (int)(s)) * 0.25f + 0.75f,
                    ((l += 0.5698402909980532f) - (int)(l)) * 0.25f + 0.375f,
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
