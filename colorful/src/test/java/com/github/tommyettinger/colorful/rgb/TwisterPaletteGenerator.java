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

package com.github.tommyettinger.colorful.rgb;

import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.IntSet;
import com.github.tommyettinger.colorful.FloatColors;
import com.github.tommyettinger.colorful.FourWheelRandom;
import com.github.tommyettinger.colorful.internal.StringKit;
import com.github.tommyettinger.gand.Path;
import com.github.tommyettinger.gand.TwistedLineI3;
import com.github.tommyettinger.gand.ds.ObjectDeque;
import com.github.tommyettinger.gand.ds.ObjectOrderedSet;
import com.github.tommyettinger.gand.points.PointI3;
import com.github.tommyettinger.gand.points.PointMaker;

import static com.github.tommyettinger.gand.points.PointMaker.pt;

// example output
/*
smallLimit = 16
{
0x00000000, 0x000000FF, 0x110000FF, 0x220011FF, 0x331111FF, 0x442211FF, 0x662211FF, 0x882211FF,
0x992211FF, 0xBB2211FF, 0xCC2200FF, 0xDD1100FF, 0xEE0000FF, 0xFF22FFFF, 0xEE33FFFF, 0xCC33FFFF,
0xCC44FFFF, 0xBB33FFFF, 0xAA22FFFF, 0x9911FFFF, 0x8822FFFF, 0x7711FFFF, 0x6600FFFF, 0x5500FFFF,
0x3300FFFF, 0x2211FFFF, 0x0011FFFF, 0x0000EEFF, 0x2200EEFF, 0x2222EEFF, 0x1122EEFF, 0x1133FFFF,
0x1155FFFF, 0x1166EEFF, 0x1188EEFF, 0x11AAEEFF, 0x00AAFFFF, 0x00BBFFFF, 0x00DDFFFF, 0xFFFFFFFF,
0xFFEEEEFF, 0xFFEECCFF, 0xFFFFBBFF, 0xEEFFBBFF, 0xEEEEAAFF, 0xFFEE99FF, 0xFFFF88FF, 0xFFFF66FF,
0xFFFF44FF, 0xFFFF22FF, 0xEEFF22FF, 0xEEFF00FF, 0x11EE11FF, 0x11DD00FF, 0x11BB00FF, 0x22AA00FF,
0x339900FF, 0x338800FF, 0x228811FF, 0x226611FF, 0x335511FF, 0x334400FF, 0x332200FF, 0x112200FF,
}
smallLimit = 32
{
0x00000000, 0x000000FF, 0x210000FF, 0x390008FF, 0x5A0010FF, 0x6B0808FF, 0x8C0800FF, 0xA50808FF,
0xB51810FF, 0xCE1808FF, 0xE71010FF, 0xF70818FF, 0xFF0010FF, 0xFF00FFFF, 0xEF10F7FF, 0xD610FFFF,
0xAD10FFFF, 0x9C10EFFF, 0x7B10F7FF, 0x5A10F7FF, 0x3908F7FF, 0x2100F7FF, 0x00FFFFFF, 0x00EFFFFF,
0x29EFFFFF, 0x39F7F7FF, 0x52F7F7FF, 0x63F7F7FF, 0x8CF7F7FF, 0x9CFFEFFF, 0xBDF7EFFF, 0xD6F7F7FF,
0xF7FFF7FF, 0xFFF7F7FF, 0xFFF7CEFF, 0xF7FFBDFF, 0xF7FF94FF, 0xE7FF84FF, 0xE7F763FF, 0xEFF75AFF,
0xEFF731FF, 0xF7F729FF, 0xFFF708FF, 0xF7F700FF, 0xEFF710FF, 0xCEF710FF, 0xBDE708FF, 0xA5E700FF,
0x84DE00FF, 0x6BDE08FF, 0x52D600FF, 0x39DE00FF, 0x10DE00FF, 0x00EF00FF, 0x00EF08FF, 0x00DE18FF,
0x08BD18FF, 0x08A510FF, 0x219C08FF, 0x298408FF, 0x295A08FF, 0x294200FF, 0x213110FF, 0x181810FF,
}
smallLimit = 64
{
0x00000000, 0x000000FF, 0x100C00FF, 0x241004FF, 0x340804FF, 0x490C08FF, 0x610C08FF, 0x75080CFF,
0x8E0810FF, 0xA20408FF, 0xB60400FF, 0xCF0004FF, 0xE30004FF, 0xF70000FF, 0xF708FBFF, 0xDF04FFFF,
0xCB04FFFF, 0xB608FBFF, 0xA204FBFF, 0x8E08F7FF, 0x7508EFFF, 0x610CEBFF, 0x410CEBFF, 0x2C0CEBFF,
0x1804EBFF, 0x0404F7FF, 0x0408FFFF, 0x0C18F7FF, 0x0C2CEFFF, 0x1445EFFF, 0x1455E3FF, 0x1069E7FF,
0x1482E3FF, 0x149ADFFF, 0x08AEDFFF, 0x08C3DFFF, 0x08DBE7FF, 0x04EBEFFF, 0x04FFF7FF, 0xFBFBF3FF,
0xFBFFEBFF, 0xF3FFD3FF, 0xF7FBBEFF, 0xF7F7AEFF, 0xF7F392FF, 0xF3F379FF, 0xEFF365FF, 0xEBF34DFF,
0xEFEF34FF, 0xEBEF1CFF, 0xEBF30CFF, 0xF3FB04FF, 0x00FF00FF, 0x0CEB00FF, 0x10D704FF, 0x10BA08FF,
0x08AE08FF, 0x049A04FF, 0x008604FF, 0x006904FF, 0x045100FF, 0x043804FF, 0x04200CFF, 0x081400FF,
}
 */
public class TwisterPaletteGenerator {
    private static final int limit = 64, smallLimit = 32;
    private static final IntArray rgba = new IntArray(limit);
    private static final IntSet added = new IntSet(limit);

    public static void main(String[] args) {
        rgba.add(0);
        FourWheelRandom random = new FourWheelRandom(1L);
        ObjectOrderedSet<PointI3> valid = new ObjectOrderedSet<>(smallLimit * smallLimit * smallLimit);
        for (int r = 0; r < smallLimit; r++) {
            for (int g = 0; g < smallLimit; g++) {
                for (int b = 0; b < smallLimit; b++) {
                    valid.add(pt(r, g, b));
                }
            }
        }
        TwistedLineI3 twist = new TwistedLineI3(random, valid.toArray(new PointI3[0]), 0.15f);
        ObjectDeque<PointI3> fullPath = new ObjectDeque<>(), partialPath;
        int e = smallLimit-1, h = smallLimit >>> 1;
        PointI3[] waypoints = {
                pt(0,0,0), pt(e,0,0), pt(e,0,e), pt(0,0,e), pt(0,e,e), pt(e,e,e), pt(e,e,0), pt(0,e,0), pt(1,1,1)
        };
        for (int i = 1; i < waypoints.length; i++) {
            while(!twist.graph.contains(waypoints[i-1])){
                PointI3 base = waypoints[i-1];
                base.x += base.x < h ? 1 : -1;
                base.y += base.y < h ? 1 : -1;
                base.z += base.z < h ? 1 : -1;
            }
            while(!twist.graph.contains(waypoints[i])){
                PointI3 base = waypoints[i];
                base.x += base.x < h ? 1 : -1;
                base.y += base.y < h ? 1 : -1;
                base.z += base.z < h ? 1 : -1;
            }
            partialPath = twist.line(waypoints[i-1], waypoints[i]);
            partialPath.pollLast();
            fullPath.addAll(partialPath);
            twist.graph.removeVertices(partialPath);
        }
        float inc = fullPath.size() / (limit-1f);
        float idx = 0;
        int lBits = Integer.numberOfTrailingZeros(smallLimit);
        int mask = ~Integer.reverse(e * 0x00010101) | 255;
        for (int i = 1; i < limit; i++) {
            PointI3 current = fullPath.get((int)idx);
            idx += inc;
            int color = current.x << 32 - lBits | current.y << 24 - lBits | current.z << 16 - lBits | 0xFF;
            color |= color >>> lBits & mask;
            if(added.add(color))
                rgba.add(color);
        }

        StringBuilder sb = new StringBuilder(12 * rgba.size + 35).append("{\n");
        for (int i = 0; i < rgba.size; i++) {
            StringKit.appendHex(sb.append("0x"), rgba.get(i)).append(", ");
            if(7 == (i & 7)) sb.append('\n');
        }
        System.out.println(sb.append('}'));
    }
}
