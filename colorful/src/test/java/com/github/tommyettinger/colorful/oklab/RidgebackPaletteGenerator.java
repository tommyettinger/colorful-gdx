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
//ridgeback; 16 hue divisions, 11 chroma divisions, 16 luma divisions, 0.25938022 random hue offset
{
0x00000000, 0x000000FF, 0x121212FF, 0x222222FF, 0x313131FF, 0x3F3F3FFF, 0x4E4E4EFF, 0x5D5D5DFF,
0x6C6C6CFF, 0x7C7C7CFF, 0x8C8C8CFF, 0x9C9C9CFF, 0xADADACFF, 0xBEBEBDFF, 0xCFCFCFFF, 0xE1E1E1FF,
0xF3F3F3FF, 0xFFFFFFFF, 0x5E4D00FF, 0x6E5C18FF, 0x7E6C28FF, 0x8F7B37FF, 0x9F8B45FF, 0xB09C54FF,
0xC1AC62FF, 0xD3BD72FF, 0xE5CF81FF, 0xF7E192FF, 0xFECA00FF, 0x3D4409FF, 0x4C541BFF, 0x5A6329FF,
0x697337FF, 0x798345FF, 0x889353FF, 0x98A462FF, 0xA9B571FF, 0xBAC680FF, 0xCBD890FF, 0xDDEAA1FF,
0xEFFDB1FF, 0xA9BA00FF, 0xBACC15FF, 0xCBDE30FF, 0xDCF144FF, 0x193A0CFF, 0x274A1BFF, 0x345A28FF,
0x426A36FF, 0x507A44FF, 0x5F8A52FF, 0x6E9B60FF, 0x7DAB6FFF, 0x8DBD7EFF, 0x9DCE8EFF, 0xAEE09EFF,
0xBFF3AFFF, 0x2E8300FF, 0x3D941DFF, 0x4DA52FFF, 0x5CB73FFF, 0x6BC94EFF, 0x7BDB5EFF, 0x8BEE6EFF,
0x59FA00FF, 0x004D30FF, 0x185D3EFF, 0x296D4CFF, 0x387D5AFF, 0x478E69FF, 0x569E78FF, 0x65AF87FF,
0x75C197FF, 0x84D2A8FF, 0x95E5B9FF, 0xA6F7CAFF, 0x00D281FF, 0x0EE591FF, 0x32F7A1FF, 0x006E64FF,
0x177E73FF, 0x2B8F83FF, 0x3CA093FF, 0x4DB1A4FF, 0x5DC2B5FF, 0x6DD4C6FF, 0x7DE6D8FF, 0x8EF9EAFF,
0x006B76FF, 0x1F7B87FF, 0x318B97FF, 0x419CA8FF, 0x51ADB9FF, 0x61BECBFF, 0x71D0DDFF, 0x81E2EFFF,
0x005777FF, 0x146788FF, 0x277799FF, 0x3787ABFF, 0x4698BDFF, 0x56A8CFFF, 0x65BAE1FF, 0x75CBF4FF,
0x06325AFF, 0x18416CFF, 0x26517EFF, 0x346090FF, 0x4270A2FF, 0x5080B4FF, 0x5F90C6FF, 0x6EA1D9FF,
0x7DB2EBFF, 0x8DC3FEFF, 0x007EEDFF, 0x148FFFFF, 0x0D0933FF, 0x191B49FF, 0x252B5DFF, 0x333A6FFF,
0x404982FF, 0x4E5894FF, 0x5C68A6FF, 0x6B77B8FF, 0x7A87CAFF, 0x8A97DDFF, 0x99A8F0FF, 0xAAB9FFFF,
0x1E1A87FF, 0x282D9DFF, 0x333EB2FF, 0x404EC6FF, 0x4C5EDBFF, 0x5A6FEFFF, 0x687FFFFF, 0x3418E9FF,
0x3D32FFFF, 0x16062CFF, 0x261842FF, 0x352754FF, 0x443567FF, 0x534478FF, 0x62538AFF, 0x72629BFF,
0x8171ADFF, 0x9180BFFF, 0xA290D1FF, 0xB2A1E3FF, 0xC3B1F6FF, 0x3C007DFF, 0x4B1E92FF, 0x5A2FA7FF,
0x693FBBFF, 0x784FCFFF, 0x875FE3FF, 0x976FF7FF, 0x7214E9FF, 0x822EFFFF, 0x31123AFF, 0x42214CFF,
0x532F5EFF, 0x633D6FFF, 0x744C80FF, 0x845A91FF, 0x9569A2FF, 0xA679B4FF, 0xB788C5FF, 0xC999D7FF,
0xDBA9EAFF, 0xEDBAFDFF, 0x631278FF, 0x75248BFF, 0x86349EFF, 0x9844B0FF, 0xAA53C3FF, 0xBC62D6FF,
0xCE72E9FF, 0xE082FCFF, 0xAD03D4FF, 0xC027E9FF, 0xD33BFDFF, 0x390F2DFF, 0x4B1E3EFF, 0x5D2C4EFF,
0x6F3A5EFF, 0x80486DFF, 0x91577EFF, 0xA3668EFF, 0xB4759EFF, 0xC685AFFF, 0xD995C1FF, 0xEBA5D2FF,
0xFEB6E4FF, 0x8A156CFF, 0x9D287DFF, 0xB1388EFF, 0xC4479FFF, 0xD857B1FF, 0xEB66C2FF, 0xFF76D4FF,
0xE300ACFF, 0xF921BDFF, 0x3F0B1DFF, 0x531B2BFF, 0x662939FF, 0x783848FF, 0x8A4656FF, 0x9C5465FF,
0xAE6374FF, 0xC07284FF, 0xD38294FF, 0xE692A4FF, 0xF9A2B5FF, 0x960946FF, 0xAB2255FF, 0xBF3363FF,
0xD44372FF, 0xE85382FF, 0xFD6292FF, 0x3F0F0EFF, 0x521E1BFF, 0x652D28FF, 0x773B36FF, 0x894943FF,
0x9B5851FF, 0xAD6760FF, 0xBF766EFF, 0xD2867EFF, 0xE5968DFF, 0xF8A69DFF, 0x9C0817FF, 0xB12226FF,
0xC63433FF, 0xDB4441FF, 0xEF544FFF, 0x522000FF, 0x652F12FF, 0x773D20FF, 0x894C2EFF, 0x9A5B3BFF,
0xAC6A49FF, 0xBF7957FF, 0xD18966FF, 0xE49975FF, 0xF7AA84FF, 0xE6620CFF, 0xFA7225FF, 0x6D4510FF,
0x7E5321FF, 0x90622FFF, 0xA1723DFF, 0xB2814BFF, 0xC4925AFF, 0xD6A268FF, 0xE9B378FF, 0xFBC488FF,
}
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


                    float r = randomize3Float(hd * 0xD1B54A32D192ED03L + cd * 0xABC98388FB8FAC03L + yd * 0x8CB92BA72F3D8DD7L);
                    float hv = r;
                    for (int h = 0; h < hd; h++, hv += hi) {
                        float cv = ci;
                        for (int c = 1; c <= cd; c++, cv += ci) {
                            yv = yi;
                            for (int y = 1; y < yd; y++, yv += yi) {
                                add(hv, cv, yv);
                            }
                        }
                    }
                    if (rgba.size == limit) {
                        System.out.println("SUCCESS!");
                        System.out.println(hd + " hue divisions, " + cd + " chroma divisions, " + yd + " luma divisions, " + r + " random hue offset");
                        break FINISH;
                    }
                    rgba.clear();
                    labs.clear();
                    rgba.add(0);
                    labs.add(Palette.TRANSPARENT);
                    System.out.println(idx++ + " attempts.");
                }
            }
        }
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
