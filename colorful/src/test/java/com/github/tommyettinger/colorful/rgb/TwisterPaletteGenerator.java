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
 */
public class TwisterPaletteGenerator {
    private static final int limit = 64;
    private static final IntArray rgba = new IntArray(limit);
    private static final IntSet added = new IntSet(limit);

    public static void main(String[] args) {
        rgba.add(0);
        FourWheelRandom random = new FourWheelRandom(1L);
        ObjectOrderedSet<PointI3> valid = new ObjectOrderedSet<>(4096);
        for (int r = 0; r < 16; r++) {
            for (int g = 0; g < 16; g++) {
                for (int b = 0; b < 16; b++) {
                    valid.add(pt(r, g, b));
                }
            }
        }
        TwistedLineI3 twist = new TwistedLineI3(random, valid.toArray(new PointI3[0]), 0.15f);
        ObjectDeque<PointI3> fullPath = new ObjectDeque<>(), partialPath;
        PointI3[] waypoints = {
                pt(0,0,0), pt(15,0,0), pt(15,1,15), pt(0,0,15), pt(0,14,14), pt(15,15,15), pt(15,15,1), pt(1,14,1), pt(1,1,1)
        };
        for (int i = 1; i < waypoints.length; i++) {
            while(!twist.graph.contains(waypoints[i-1])){
                PointI3 base = waypoints[i-1];
                base.x += base.x < 8 ? 1 : -1;
                base.y += base.y < 8 ? 1 : -1;
                base.z += base.z < 8 ? 1 : -1;
            }
            while(!twist.graph.contains(waypoints[i])){
                PointI3 base = waypoints[i];
                base.x += base.x < 8 ? 1 : -1;
                base.y += base.y < 8 ? 1 : -1;
                base.z += base.z < 8 ? 1 : -1;
            }
            partialPath = twist.line(waypoints[i-1], waypoints[i]);
            partialPath.pollLast();
            fullPath.addAll(partialPath);
            twist.graph.removeVertices(partialPath);
        }
        float inc = fullPath.size() / (limit-1f);
        float idx = 0;
        for (int i = 1; i < limit; i++) {
            PointI3 current = fullPath.get((int)idx);
            idx += inc;
            int color = current.x << 24 | current.y << 16 | current.z << 8 | 15;
            color |= color << 4;
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
