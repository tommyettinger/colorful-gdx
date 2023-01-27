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

package com.github.tommyettinger.colorful.hsi.internal;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.NumberUtils;
import com.github.tommyettinger.colorful.ipt.ColorTools;
import com.github.tommyettinger.colorful.ipt.Palette;

import static com.github.tommyettinger.colorful.ipt.ColorTools.*;

public class SphereIPTWork {
    public static Vector3 plump(Vector3 original, Vector3 buffer) {
        final float crMid = 0.3481738f * original.y + 0.104959644f * original.z;
        final float crScale = (original.x - 0.5f + (NumberUtils.floatToRawIntBits(crMid) >>> 31)) * 0.16420607f / -crMid;
        final float mgMid = 0.122068435f * original.y + -0.070396f * original.z;
        final float mgScale = (original.x + 0.5f - (NumberUtils.floatToRawIntBits(mgMid) >>> 31)) * -0.16136102f / -mgMid;
        final float ybMid = 0.020876605f * original.y + -0.26078433f * original.z;
        final float ybScale = (original.x - 0.5f + (NumberUtils.floatToRawIntBits(ybMid) >>> 31)) * 0.16155326f / -ybMid;
        final float scale = Math.max(crScale, Math.max(mgScale, ybScale));
        final float d = 0.25f * MathUtils.cos(3.14159f * original.x) / Vector2.len(original.y * scale, original.z * scale);
        return buffer.set(original.x, original.y * d, original.z * d);
    }

    public static Vector3 thin(Vector3 original, Vector3 buffer) {
        final float crMid = 0.3481738f * original.y + 0.104959644f * original.z;
        final float crScale = (original.x - 0.5f + (NumberUtils.floatToRawIntBits(crMid) >>> 31)) * 0.16420607f / -crMid;
        final float mgMid = 0.122068435f * original.y + -0.070396f * original.z;
        final float mgScale = (original.x + 0.5f - (NumberUtils.floatToRawIntBits(mgMid) >>> 31)) * -0.16136102f / -mgMid;
        final float ybMid = 0.020876605f * original.y + -0.26078433f * original.z;
        final float ybScale = (original.x - 0.5f + (NumberUtils.floatToRawIntBits(ybMid) >>> 31)) * 0.16155326f / -ybMid;
        final float scale = Math.max(crScale, Math.max(mgScale, ybScale));
        final float d = 4f * Vector2.len(original.y * scale, original.z * scale) / MathUtils.cos(3.14159f * original.x);
        return buffer.set(original.x, original.y * d, original.z * d);
    }

    public static void main(String[] args) {
        float r = fromRGBA(1f, 0f, 0f, 1f);
        float ri = intensity(r) - 0.5f, rp = protan(r) - 0.5f, rt = tritan(r) - 0.5f;
        float g = fromRGBA(0f, 1f, 0f, 1f);
        float gi = intensity(g) - 0.5f, gp = protan(g) - 0.5f, gt = tritan(g) - 0.5f;
        float b = fromRGBA(0f, 0f, 1f, 1f);
        float bi = intensity(b) - 0.5f, bp = protan(b) - 0.5f, bt = tritan(b) - 0.5f;
        Vector3 rv = new Vector3(ri, rp, rt);
        Vector3 gv = new Vector3(gi, gp, gt);
        Vector3 bv = new Vector3(bi, bp, bt);
        System.out.println(rv);
        System.out.println(gv);
        System.out.println(bv);
        System.out.println();
        Vector3 kv = new Vector3(-0.5f, 0f, 0f);
        Vector3 wv = new Vector3(0.5f , 0f, 0f);
        Vector3 buffer = new Vector3(), buffer2 = new Vector3();
        Vector3 testPoint = new Vector3();
        for (int i = 0; i < 256; i++) {
            String name = Palette.NAMES.get(i);
            float color = Palette.NAMED.get(name, 0f);
            if(ColorTools.alphaInt(color) < 128) continue;
            testPoint.set(intensity(color) - 0.5f, protan(color) - 0.5f, tritan(color) - 0.5f);
            plump(testPoint, buffer);
            thin(buffer, buffer2);
            if(!testPoint.epsilonEquals(buffer2, 0.01f))
                System.out.printf("%s is bad! before: %s, after: %s\n", name, testPoint, buffer2);
        }

//        System.out.printf("public static final Vector3 yellow  = new Vector3%s;\n", (rv.cpy().sub(kv).crs(gv.cpy().sub(kv))));
//        System.out.printf("public static final Vector3 magenta = new Vector3%s;\n", (rv.cpy().sub(kv).crs(bv.cpy().sub(kv))));
//        System.out.printf("public static final Vector3 cyan    = new Vector3%s;\n", (gv.cpy().sub(kv).crs(bv.cpy().sub(kv))));

        
    }
}
