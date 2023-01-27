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

import com.badlogic.gdx.math.RandomXS128;

public class IPTRandomColorCheck {
    public static void main(String[] args) {
        RandomXS128 random = new RandomXS128(0L, 1L);
        for (int i = 0; i < 100000; i++) {
            float color = ColorTools.randomColor(random);
            if (!ColorTools.inGamut(color)) {
                System.out.printf("Color with IPT values: I=%f, P=%f, T=%f\n",
                        ColorTools.intensity(color), ColorTools.protan(color), ColorTools.tritan(color));
                System.out.println("It's out of gamut!");
            }
        }
    }
}
