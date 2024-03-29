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

package com.github.tommyettinger.colorful.ipt;

import com.badlogic.gdx.utils.IntArray;
import com.github.tommyettinger.anim8.OtherMath;
import com.github.tommyettinger.colorful.internal.StringKit;
/* Example output:

{0x00000000, 0x0B080FFF, 0x353336FF, 0x555555FF, 0x797577FF, 0xAAAAAAFF, 0xC8C8C8FF, 0xE0E0E0FF,
0xFAF7F0FF, 0xA01948FF, 0x18CA91FF, 0x076C12FF, 0xD1B8E1FF, 0xC768D7FF, 0x8F6730FF, 0x44BF74FF,
0x76450FFF, 0x4569B5FF, 0x1A0635FF, 0xC5706FFF, 0x007A43FF, 0x98C5B6FF, 0x9B39B2FF, 0x9CF1EFFF,
0x48262FFF, 0xFAB8C0FF, 0x0A2815FF, 0x275ECCFF, 0x6A1E7DFF, 0x778E35FF, 0xBD3819FF, 0x9BE1DEFF,
0x8C0F0AFF, 0xACB1A0FF, 0xA394C4FF, 0x68070CFF, 0x507394FF, 0x1B5264FF, 0xCB8C55FF, 0x8534B8FF,
0xB6C6E4FF, 0x261286FF, 0x37D5E2FF, 0x0A3744FF, 0xE758CBFF, 0xBF3C6BFF, 0xA1A75EFF, 0x945B87FF,
0xB9AA7FFF, 0x8F3D3FFF, 0xC0D4C2FF, 0x1D0372FF, 0xF277C4FF, 0x41C18CFF, 0xA7EFFCFF, 0xC52EC5FF,
0x525124FF, 0xE46884FF, 0x0F1475FF, 0x6072D6FF, 0xCDBDC1FF, 0x25226CFF, 0xCBDBD1FF, 0x7A0C28FF,
}

EXPERIMENTAL:
{0x00000000, 0x0B080FFF, 0x353336FF, 0x555555FF, 0x797577FF, 0xAAAAAAFF, 0xC8C8C8FF, 0xE0E0E0FF,
0xFAF7F0FF, 0xE2B6EAFF, 0x8D9325FF, 0x3E86AFFF, 0xD405C7FF, 0x2BC98DFF, 0xB36713FF, 0x144367FF,
0xBFA9E6FF, 0xE970CEFF, 0x518026FF, 0xD1E37FFF, 0xEAE7DAFF, 0x4A98C2FF, 0x304CBCFF, 0xED9277FF,
0x48912FFF, 0x3E564AFF, 0x210CABFF, 0xEC4A5BFF, 0xE722C7FF, 0x0F547BFF, 0xE70610FF, 0x2582C9FF,
0x00D44EFF, 0x6F232CFF, 0x15FF1FFF, 0xE114ADFF, 0xC0A800FF, 0xCEEB28FF, 0xBB9FC0FF, 0xD02BB2FF,
0xF8FCB8FF, 0x2CE6DBFF, 0xBD704BFF, 0x1C5DC0FF, 0xA8B714FF, 0x66328DFF, 0x8A8FB5FF, 0xFDAE1DFF,
0xDF696EFF, 0x709258FF, 0xAAEBB2FF, 0xA2F3E6FF, 0x30A4C8FF, 0x6A3280FF, 0xA19C3AFF, 0x7126C7FF,
0x745D54FF, 0x89CE42FF, 0xD91AFCFF, 0x2F9CADFF, 0x7C2925FF, 0x91B556FF, 0x07FB0CFF, 0x4C28D0FF,
}
 */
public class SubRandomPaletteGenerator {
    public static void main(String[] args) {
        final int limit = 64;
        IntArray rgba = new IntArray(limit);
        rgba.add(0);
        rgba.add(0x0B080FFF);
        rgba.add(0xFAF7F0FF);
        rgba.add(0x797577FF);
        rgba.add(0x555555FF);
        rgba.add(0xAAAAAAFF);
        rgba.add(0x353336FF);
        rgba.add(0xE0E0E0FF);
        rgba.add(0xC8C8C8FF);
        int idx = 1, initial = rgba.size;
        while (rgba.size < limit) {
            for (int i = initial; i < 32 && rgba.size < limit; i++) {
                float color = gaussianColor(idx++, 0.5 * (1.0 - i * i * i * 0x1p-16));
                if (ColorTools.inGamut(color)) {
                    rgba.add(ColorTools.toRGBA8888(color));
                }
            }
        }
        StringBuilder sb = new StringBuilder(12 * rgba.size + 34).append('{');
        for (int i = 0; i < rgba.size; i++) {
            StringKit.appendHex(sb.append("0x"), rgba.get(i)).append(", ");
            if(7 == (i & 7)) sb.append('\n');
        }
        System.out.println(sb.append('}'));
    }
    public static float haltonColor(int index)
    {
        double denominator = 3.0, resY = 0.0, resZ = 0.0,
                resX = (Integer.reverse(index) >>> 1) * 0x1p-31;
        int n = (index & 0x7fffffff);
        while (n > 0)
        {
            resY += (n % 3) / denominator;
            n /= 3;
            denominator *= 3.0;
        }

        denominator = 5;
        n = (index & 0x7fffffff);
        while (n > 0)
        {
            resZ += (n % 5) / denominator;
            n /= 5;
            denominator *= 5.0;
        }
        return ColorTools.ipt((float) resX, (float) resY, (float) resZ, 1f);
    }
    public static float gaussianColor(int index)
    {
        double denominator = 3.0, resY = 0.0, resZ = 0.0,
                resX = (Integer.reverse(index) >>> 1) * 0x1p-31;
        int n = (index & 0x7fffffff);
        while (n > 0)
        {
            resY += (n % 3) / denominator;
            n /= 3;
            denominator *= 3.0;
        }

        denominator = 5;
        n = (index & 0x7fffffff);
        while (n > 0)
        {
            resZ += (n % 5) / denominator;
            n /= 5;
            denominator *= 5.0;
        }
        return ColorTools.ipt((float) resX,
                (float) (OtherMath.probit(resY) % 0.5 + 0.5),
                (float) (OtherMath.probit(resZ) % 0.5 + 0.5), 1f);
    }
    public static float gaussianColor(int index, double sat)
    {
        sat *= 0.5;
        double denominator = 3.0, resY = 0.0, resZ = 0.0,
                resX = (Integer.reverse(index) >>> 1) * 0x1p-31;
        int n = (index & 0x7fffffff);
        while (n > 0)
        {
            resY += (n % 3) / denominator;
            n /= 3;
            denominator *= 3.0;
        }

        denominator = 5;
        n = (index & 0x7fffffff);
        while (n > 0)
        {
            resZ += (n % 5) / denominator;
            n /= 5;
            denominator *= 5.0;
        }
        return ColorTools.ipt(
                OtherMath.barronSpline((float) resX, 1.25f, 0.5f),//(float) (PNG8.probit(resX) * 2.0 % 0.5 + 0.5),
                (float) (OtherMath.probit(resY) * sat + 0.5),
                (float) (OtherMath.probit(resZ) * sat + 0.5), 1f);
    }

}
