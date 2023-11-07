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
ridgeback1; 16 hue divisions, 11 chroma divisions, 16 luma divisions, 0.25938022 random hue offset
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

ridgeback2; 16 hue divisions, 10 chroma divisions, 18 luma divisions, 4478551551607359662 random seed
{
0x00000000, 0x000000FF, 0x111111FF, 0x1F1F1FFF, 0x2C2C2CFF, 0x393939FF, 0x474747FF, 0x545454FF,
0x616161FF, 0x6E6E6EFF, 0x7C7C7CFF, 0x8B8B8BFF, 0x999999FF, 0xA7A7A7FF, 0xB6B6B6FF, 0xC6C6C6FF,
0xD6D6D6FF, 0xE5E5E5FF, 0xF5F5F5FF, 0xFFFFFFFF, 0x0E0034FF, 0x0D1A49FF, 0x1F1444FF, 0x2A0F3DFF,
0x330B30FF, 0x3B0020FF, 0x3E0010FF, 0x351E52FF, 0x431843FF, 0x4C1431FF, 0x501323FF, 0x51150CFF,
0x00370FFF, 0x11295BFF, 0x24245AFF, 0x23430DFF, 0x004725FF, 0x22356CFF, 0x382F67FF, 0x4B285AFF,
0x57234AFF, 0x5E213BFF, 0x612227FF, 0x5F260AFF, 0x713038FF, 0x713325FF, 0x6E3700FF, 0x3C4F13FF,
0x1D5528FF, 0x29457DFF, 0x423F78FF, 0x52396FFF, 0x623262FF, 0x6E2E4DFF, 0x823A4DFF, 0x823E36FF,
0x7F421FFF, 0x754A0BFF, 0x665200FF, 0x4F5B18FF, 0x34612EFF, 0x136443FF, 0x005C81FF, 0x295689FF,
0x444E8DFF, 0x5B4783FF, 0x6D4175FF, 0x783D63FF, 0x465F9DFF, 0x5F5798FF, 0x72518DFF, 0x814C7BFF,
0x8F4763FF, 0x93494CFF, 0x924C37FF, 0x8A5324FF, 0x7B5C11FF, 0x66651DFF, 0x506C2DFF, 0x337145FF,
0x00745EFF, 0x006E88FF, 0x286795FF, 0x31815CFF, 0x008276FF, 0x008089FF, 0x1D7A9CFF, 0x4272A9FF,
0x5D69ADFF, 0x7162A7FF, 0x855C98FF, 0x97577FFF, 0xA25467FF, 0xA45553FF, 0xA05B3DFF, 0x936429FF,
0x826D21FF, 0x6F742AFF, 0x557C40FF, 0x2D89ABFF, 0x4781B9FF, 0x6478BDFF, 0x7E70B7FF, 0x9369A7FF,
0xA56393FF, 0xB06179FF, 0xB4625FFF, 0xB06849FF, 0xA66F36FF, 0x95792FFF, 0x7D8237FF, 0x628A4CFF,
0x428F63FF, 0x23907EFF, 0x138E98FF, 0x9D79BDFF, 0xB074A8FF, 0xBE6F93FF, 0xC37178FF, 0xC3745CFF,
0xB97C4AFF, 0xAD843CFF, 0x958E44FF, 0x789753FF, 0x5B9D6CFF, 0x3DA082FF, 0x339E9DFF, 0x359AB7FF,
0x5093C6FF, 0x668BCFFF, 0x8382C9FF, 0x629FDBFF, 0x7F96DFFF, 0x968FD9FF, 0xB285C7FF, 0xC57FB1FF,
0xD27D96FF, 0xD57E80FF, 0xD28463FF, 0xC78C50FF, 0xB39649FF, 0x9F9E51FF, 0x7DA866FF, 0x5EAE7FFF,
0x44AF9BFF, 0x3EADB1FF, 0x4AA7CCFF, 0xE68E7CFF, 0xE0946AFF, 0xCE9F5DFF, 0xB9A957FF, 0x9BB366FF,
0x82B97AFF, 0x65BD98FF, 0x4ABEB5FF, 0x4CBAD1FF, 0x5EB4E1FF, 0x7DAAEBFF, 0x98A0EFFF, 0xB597E3FF,
0xC891D2FF, 0xD98DB5FF, 0xE68A99FF, 0x95B5FFFF, 0xB3ABFAFF, 0xCBA4EDFF, 0xE09DD6FF, 0xF199B9FF,
0xF69A9CFF, 0xF59E84FF, 0xE9A771FF, 0xD7B163FF, 0xBCBC6BFF, 0xA1C47AFF, 0x83CA94FF, 0x64CEB3FF,
0x5BCCD0FF, 0x60C7E6FF, 0x78BFF6FF, 0xFBB77FFF, 0xE8C172FF, 0xCCCC7AFF, 0xB1D589FF, 0x92DBA3FF,
0x73DFC3FF, 0x6BDDE1FF, 0x70D8F8FF, 0xDBB3FFFF, 0xF1ADE7FF, 0xFFA8C9FF, 0xCEE18FFF, 0xAEE9A5FF,
0x94EDBFFF, 0x7BEFDFFF, 0x7CEBFDFF, 0xEED580FF, 0xF9E78FFF, 0xD8F39EFF, 0xB3FCBAFF, 0x9FFED5FF,
0x86FFF5FF, 0xF6FEA5FF, 0x0A1189FF, 0x33179BFF, 0x4F0088FF, 0x3A2CB0FF, 0x5B1E9CFF, 0x740B81FF,
0x3641C3FF, 0x5D32B7FF, 0x7C259BFF, 0x931677FF, 0xA4004BFF, 0xAA001DFF, 0x3B51D5FF, 0x6A40C9FF,
0x8B32B0FF, 0xA42786FF, 0xB51B5DFF, 0xBC1B29FF, 0xD02B2EFF, 0x4B5EE7FF, 0x7C4CDBFF, 0x9F3EBCFF,
0xB93290FF, 0xC82C65FF, 0xB948BDFF, 0xD23C90FF, 0xE0385DFF, 0xE14022FF, 0x009A29FF, 0x2876FBFF,
0x6C65F8FF, 0x9755E2FF, 0xF4511CFF, 0xB060EFFF, 0xD154C4FF, 0xE84B95FF, 0xF34A60FF, 0x00BC4DFF,
0xBB71FFFF, 0xE062D9FF, 0xFA59A4FF, 0xF370E4FF, 0x85BE00FF, 0x16CC61FF, 0x6BD646FF, 0xABDD25FF,
0x5BEB6EFF, 0xC9E829FF, 0x7CFA6CFF, 0xF4F013FF, 0x3718FBFF, 0x980EFFFF, 0xEA1DE5FF, 0xFF2FECFF,
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
        long seed = 1234567890L;

        FINISH:
        for (int hd = 16; hd < 33; hd++) {
            float hi = 1f / hd;
            for (int cd = 8; cd < 16; cd++) {
                float ci = 0.97f / cd;
                for (int yd = 10; yd < 20; yd++) {
                    float yi = 1f / yd;

                    float yv = 0f;
                    for (int y = 0; y <= yd; y++, yv += yi) {
                        add(0f, 0f, yv);
                    }
                    seed += hd * 0xD1B54A32D192ED03L + cd * 0xABC98388FB8FAC03L + yd * 0x8CB92BA72F3D8DD7L;
                    float cv = 1f / cd;
                    for (int c = 1; c <= cd; c++, cv += ci) {
                        yv = yi;
                        for (int y = 1; y < yd; y++, yv += yi) {
                            float rf = randomize3Float(seed + y + (long) c * yd);
                            float hv = rf;
                            for (int h = 0; h < hd; h++, hv += hi) {
                                add(hv, cv, yv);
                            }
                        }
                    }
                    if (rgba.size == limit) {
                        System.out.println("SUCCESS!");
                        System.out.println(hd + " hue divisions, " + cd + " chroma divisions, " + yd + " luma divisions, " + seed + " random seed");
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

//        System.out.println();
//        for (int i = 0; i < labs.size; i++) {
//            float c = labs.get(i);
//            System.out.printf("L=%f,A=%f,B=%f,RGBA=%08X   ",
//                    ColorTools.channelL(c), ColorTools.channelA(c), ColorTools.channelB(c), ColorTools.toRGBA8888(c));
//            if(3 == (i & 3)) System.out.println();;
//        }
    }
}
