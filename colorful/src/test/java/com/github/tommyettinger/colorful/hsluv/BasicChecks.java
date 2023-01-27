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

package com.github.tommyettinger.colorful.hsluv;

public class BasicChecks {
    public static void main(String[] args) {
        float mx = 0f, highest = 0f;
        for (float h = 0x1p-8f; h < 1f; h += 0x1p-8f) {
            for (float li = 0x1p-8f; li < 1f; li += 0x1p-8f) {
                float lim = ColorTools.chromaLimit(h, li);
                if(mx != (mx = Math.max(mx, lim)))
                    highest = h;
            }
        }
        System.out.println("Max chroma limit is at " + highest + ", with: " + mx); // furthest hue is 0.03515625, with 1.7629014
    }
}
