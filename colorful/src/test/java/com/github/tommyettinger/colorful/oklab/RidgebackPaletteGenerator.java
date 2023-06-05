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

import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.NumberUtils;
import com.github.tommyettinger.colorful.TrigTools;
import com.github.tommyettinger.colorful.internal.StringKit;

import static com.badlogic.gdx.math.MathUtils.floor;
import static com.github.tommyettinger.colorful.oklab.Gamut.GAMUT_DATA;

// example output
/*
//ridgeback
 */
public class RidgebackPaletteGenerator {
    private static final int limit = 256;
    private static final IntArray rgba = new IntArray(limit);
    private static final FloatArray labs = new FloatArray(limit);
    private static int idx = 1;
    private static float hue = 0.0f, chroma = 0.0f, lightness = 0.0f;
    private static int hueDivisions = 6, chromaDivisions = 6, lightnessDivisions = 8;

    private static void add(float hue, float chroma, float lightness) {
        lightness = Math.min(Math.max(lightness, 0f), 1f);
        chroma = Math.max(chroma, 0f);
        hue -= floor(hue);
        final int idx = (int) (lightness * 255.999f) << 8 | (int) (256f * hue);
        final float dist = chroma * 127.5f;
        if(GAMUT_DATA[idx] * 0.5f < dist) return;
        float oklab = NumberUtils.intBitsToFloat(
                0xFE000000 |
                        (int) (TrigTools.sinTurns(hue) * dist + 127.5f) << 16 |
                        (int) (TrigTools.cosTurns(hue) * dist + 127.5f) << 8 |
                        (int) (lightness * 255.999f));
        rgba.add(ColorTools.toRGBA8888(oklab));
        labs.add(oklab);

    }

    public static float randomize3Float(long state) {
        state ^= 0xABC98388FB8FAC03L;
        state ^= state >>> 32;
        state *= 0xBEA225F9EB34556DL;
        state ^= state >>> 29;
        state *= 0xBEA225F9EB34556DL;
        state ^= state >>> 32;
        state *= 0xBEA225F9EB34556DL;
        return (state >>> 40) * 0x1p-24f;
    }

    public static void main(String[] args) {
//        System.out.printf("%08X, %1.4f, %08X\n", 0xFF0000FF, ColorTools.chroma(ColorTools.fromRGBA8888(0xFF0000FF)), ColorTools.toRGBA8888(ColorTools.maximizeSaturation(ColorTools.fromRGBA8888(0xFF0000FF))));
//        System.out.printf("%08X, %1.4f, %08X\n", 0x00FF00FF, ColorTools.chroma(ColorTools.fromRGBA8888(0x00FF00FF)), ColorTools.toRGBA8888(ColorTools.maximizeSaturation(ColorTools.fromRGBA8888(0x00FF00FF))));
//        System.out.printf("%08X, %1.4f, %08X\n", 0x0000FFFF, ColorTools.chroma(ColorTools.fromRGBA8888(0x0000FFFF)), ColorTools.toRGBA8888(ColorTools.maximizeSaturation(ColorTools.fromRGBA8888(0x0000FFFF))));
//        System.out.printf("%08X, %1.4f, %08X\n", 0xFFFF00FF, ColorTools.chroma(ColorTools.fromRGBA8888(0xFFFF00FF)), ColorTools.toRGBA8888(ColorTools.maximizeSaturation(ColorTools.fromRGBA8888(0xFFFF00FF))));
//        System.out.printf("%08X, %1.4f, %08X\n", 0x00FFFFFF, ColorTools.chroma(ColorTools.fromRGBA8888(0x00FFFFFF)), ColorTools.toRGBA8888(ColorTools.maximizeSaturation(ColorTools.fromRGBA8888(0x00FFFFFF))));
//        System.out.printf("%08X, %1.4f, %08X\n", 0xFF00FFFF, ColorTools.chroma(ColorTools.fromRGBA8888(0xFF00FFFF)), ColorTools.toRGBA8888(ColorTools.maximizeSaturation(ColorTools.fromRGBA8888(0xFF00FFFF))));

        rgba.add(0);
        labs.add(Palette.TRANSPARENT);

        FINISH:
        for (int hd = 5; hd < 17; hd++) {
            float hi = 1f / hd;
            for (int cd = 4; cd < 12; cd++) {
                float ci = 1f / cd;
                for (int yd = 6; yd < 17; yd++) {
                    float yi = 1f / yd;

                    float yv = 0f;
                    for (int y = 0; y <= yd; y++, yv += yi) {
                        add(0f, 0f, yv);
                    }


                    float hv = randomize3Float(hd * 0xD1B54A32D192ED03L + cd * 0xABC98388FB8FAC03L + yd * 0x8CB92BA72F3D8DD7L);
                    for (int h = 0; h < hd; h++, hv += hi) {
                        float cv = ci;
                        for (int c = 1; c <= cd; c++, cv += ci) {
                            yv = yi;
                            for (int y = 1; y < yd; y++, yv += yi) {
                                add(hv, cv, yv);
                            }
                        }
                    }
                    if (rgba.size == limit) break FINISH;
                    rgba.clear();
                    labs.clear();
                    rgba.add(0);
                    labs.add(Palette.TRANSPARENT);
                    System.out.println(idx++ + " attempts.");
                }
            }
        }
        if (rgba.size == limit)
            System.out.println("SUCCESS!");
        StringBuilder sb = new StringBuilder(12 * rgba.size + 35).append("{\n");
        for (int i = 0; i < rgba.size; i++) {
            StringKit.appendHex(sb.append("0x"), rgba.get(i)).append(", ");
            if(7 == (i & 7)) sb.append('\n');
        }
        System.out.println(sb.append('}'));

        System.out.println();
        for (int i = 0; i < labs.size; i++) {
            float c = labs.get(i);
            System.out.printf("L=%f,A=%f,B=%f,RGBA=%08X   ",
                    ColorTools.channelL(c), ColorTools.channelA(c), ColorTools.channelB(c), ColorTools.toRGBA8888(c));
            if(3 == (i & 3)) System.out.println();;
        }
    }
}
