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

package com.github.tommyettinger.colorful.ycwcm.internal;

import com.badlogic.gdx.math.Vector3;

import static com.github.tommyettinger.colorful.ycwcm.ColorTools.*;

public class YcwCmWork {
    public static void main(String[] args) {
        float r = fromRGBA(1f, 0f, 0f, 1f);
        float ry = luma(r), rw = chromaWarm(r) - 0.5f, rm = chromaMild(r) - 0.5f;
        float g = fromRGBA(0f, 1f, 0f, 1f);
        float gy = luma(g), gw = chromaWarm(g) - 0.5f, gm = chromaMild(g) - 0.5f;
        float b = fromRGBA(0f, 0f, 1f, 1f);
        float by = luma(b), bw = chromaWarm(b) - 0.5f, bm = chromaMild(b) - 0.5f;
        Vector3 rv = new Vector3(ry, rw, rm);
        Vector3 gv = new Vector3(gy, gw, gm);
        Vector3 bv = new Vector3(by, bw, bm);
        System.out.println(rv);
        System.out.println(gv);
        System.out.println(bv);
        System.out.println();
    }
}