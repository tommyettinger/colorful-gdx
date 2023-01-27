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

package com.github.tommyettinger.colorful.ipt_hq;

import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.IntArray;
import com.github.tommyettinger.anim8.OtherMath;
import com.github.tommyettinger.colorful.internal.StringKit;

/* Example output:
Halton:
0x00000000, 0x0B080FFF, 0xFAF7F0FF, 0x797577FF, 0x555555FF, 0xAAAAAAFF, 0x353336FF, 0xE0E0E0FF,
0xC8C8C8FF, 0xB8B761FF, 0x48A99AFF, 0xB43619FF, 0x1D6292FF, 0x585C11FF, 0xBEBBAFFF, 0x7F86BAFF,
0xE08766FF, 0xC95C90FF, 0x83565EFF, 0x1F4FFEFF, 0x7BE76EFF, 0x284890FF, 0x6C361DFF, 0x4B8FFEFF,
0xA81E73FF, 0x3B3C5CFF, 0x45CE47FF, 0x9D6F13FF, 0xAD50B9FF, 0xDCFEC9FF, 0xC12BEEFF, 0xE6E098FF,
0x7C36B8FF, 0x251813FF, 0xCC614FFF, 0x8B5918FF, 0x0F84CAFF, 0x8C1749FF, 0xE4B51CFF, 0xB178C9FF,
0x3EA23FFF, 0xE064F6FF, 0x4D1271FF, 0x65813BFF, 0x764FCDFF, 0xBFF252FF, 0x4C2BCBFF, 0x1DB1E9FF,
0xAF4865FF, 0x99905DFF, 0x8F57F4FF, 0x461B04FF, 0xE49CC0FF, 0xF2C262FF, 0x781B84FF, 0xA6E3C8FF,
0xFA6642FF, 0x4C7AA3FF, 0xAF4A35FF, 0x7EAF25FF, 0x241954FF, 0x49FAEBFF, 0xE953A0FF, 0xA48734FF,
0x619487FF, 0x80DC18FF, 0xB1A2EAFF, 0xFE74B0FF, 0xC1A92DFF, 0x7FC59AFF, 0x69C5EDFF, 0xAE6FABFF,
0x482C13FF, 0xEBF879FF, 0xF9355CFF, 0x457119FF, 0xE1BFF9FF, 0xBDCD49FF, 0xCD9793FF, 0x9299DCFF,
0xA6D99EFF, 0xE15A1BFF, 0x4C0E46FF, 0x21F935FF, 0xF29E72FF, 0xBE9519FF, 0x46B466FF, 0xE6359AFF,
0xA514D1FF, 0xDDDB0CFF, 0xB0164EFF, 0x6C1A10FF, 0xAB8B97FF, 0x77468DFF, 0xCD2571FF, 0x682349FF,
0x8D0CA2FF, 0x587160FF, 0xB263F9FF, 0xC5408FFF, 0x80415EFF, 0xA3C329FF, 0x66433EFF, 0xA82C96FF,
0x335AD2FF, 0x370E30FF, 0x5FDEFAFF, 0xD842F6FF, 0xF82333FF, 0x58E0C4FF, 0x414436FF, 0xD12B26FF,
0x5B08A4FF, 0x2B12F5FF, 0x0E13A0FF, 0xD47D45FF, 0xF5BAB0FF, 0xE786FEFF, 0xE54CCCFF, 0x398D5EFF,
0x6910F7FF, 0xB2FBA1FF, 0x525FA7FF, 0xA56085FF, 0x0A1431FF, 0xFD0ACDFF, 0x956951FF, 0x4BFEAAFF,
0x349723FF, 0x8B170DFF, 0xFF1FF3FF, 0x7D89DDFF, 0x86749FFF, 0x241B7CFF, 0x636541FF, 0xECFC34FF,
0x00391CFF, 0xD9DD77FF, 0xFB7C84FF, 0xF3942DFF, 0x9B3312FF, 0x33C08AFF, 0xF16375FF, 0xD57AA3FF,
0x92393BFF, 0x910CF4FF, 0xFAA6DCFF, 0x1FBFC0FF, 0x262E88FF, 0x4974F5FF, 0xDD7DCEFF, 0x5D2EA9FF,
0x2E1FC0FF, 0xC1B1FAFF, 0x8B5097FF, 0x5CD36BFF, 0xC3324DFF, 0x1F613FFF, 0x8AA074FF, 0x59F48BFF,
0x074B0EFF, 0x593D6CFF, 0x119FCAFF, 0xD430C2FF, 0x0E2530FF, 0x2DD0C3FF, 0x6740F6FF, 0xF8E34CFF,
0xFADDFFFF, 0xB67574FF, 0x152C0BFF, 0xB387FFFF, 0x0C6767FF, 0x12E499FF, 0xD57D10FF, 0x8CFE25FF,
0xE0B190FF, 0xB8FDFDFF, 0xD74447FF, 0x8768C8FF, 0xFDA141FF, 0xFFD2C7FF, 0x347982FF, 0xC70ABCFF,
0xAFCD7EFF, 0x70096EFF, 0x550D25FF, 0x1A7B3EFF, 0xF8C11FFF, 0x1C345AFF, 0xBC57D2FF, 0xFC8F97FF,
0x92136EFF, 0x86B8C8FF, 0x293BF0FF, 0xB2DAF1FF, 0x1A506CFF, 0x005A25FF, 0x9F0B2AFF, 0xA094B9FF,
0xF74279FF, 0xFC4A32FF, 0x9AE538FF, 0x1DCA1CFF, 0xBA7328FF, 0x0C0957FF, 0x91FBCBFF, 0xFB82D6FF,
0x849C1AFF, 0x8D6239FF, 0xC77888FF, 0x59A160FF, 0xFCEAA5FF, 0x9AB255FF, 0x730CDBFF, 0x0D64FFFF,
0x838156FF, 0x9BAF89FF, 0x268F83FF, 0x751134FF, 0x21070FFF, 0x561A86FF, 0x1E53BFFF, 0x5762E7FF,
0xD39F5EFF, 0x9542E5FF, 0xEA127FFF, 0xC45503FF, 0x99F986FF, 0x504F82FF, 0xB8974DFF, 0xEF62D1FF,
0xE3124DFF, 0xB07FE8FF, 0x4C450BFF, 0x598013FF, 0x92D0E0FF, 0x0D086FFF, 0xBD0BDDFF, 0x3096ADFF,
0x00BDFEFF, 0xFE6213FF, 0xA141B3FF, 0x61A9BDFF, 0x926FD9FF, 0x43F1D0FF, 0xF28022FF, 0x7C4C1DFF,
0xD3EE0FFF, 0x858810FF, 0xACF1EEFF, 0xC7D856FF, 0x708C6EFF, 0xC790DEFF, 0x5A44CFFF, 0x5B292AFF,

Random:
0x00000000, 0x0B080FFF, 0xFAF7F0FF, 0x797577FF, 0x555555FF, 0xAAAAAAFF, 0x353336FF, 0xE0E0E0FF,
0xC8C8C8FF, 0xB5C289FF, 0xB64823FF, 0x876DBDFF, 0xE1CA3CFF, 0x8B4252FF, 0x43B684FF, 0x3C43D6FF,
0x5E2F92FF, 0x5B5E74FF, 0xA7604DFF, 0xE65F84FF, 0x9E43B3FF, 0x7C2A45FF, 0xEAA768FF, 0x8B267AFF,
0x917CEEFF, 0xEA99B2FF, 0x8E898CFF, 0x2F249EFF, 0x1E5736FF, 0x385FA5FF, 0xAEA451FF, 0x2A3EB1FF,
0xE77CCFFF, 0xC148B6FF, 0xD960BAFF, 0x75ECDFFF, 0x5FC2C7FF, 0xAE4D7FFF, 0x2B221CFF, 0x5B92C8FF,
0x1A415BFF, 0x874028FF, 0x99BC58FF, 0xF1F074FF, 0xDD4067FF, 0x3E6FD2FF, 0x6A9849FF, 0xE8F4B0FF,
0xAD8250FF, 0xB240EEFF, 0x211317FF, 0xAFE38DFF, 0x9AA08FFF, 0xF14FDFFF, 0xB090E5FF, 0x7240CAFF,
0xC3A4E8FF, 0x86FAFCFF, 0x281C4FFF, 0x71371DFF, 0xEA3123FF, 0xB03859FF, 0xBEB9B7FF, 0x764D9DFF,
0x826C22FF, 0xEDB396FF, 0xE6D55FFF, 0xED7BA2FF, 0x316D41FF, 0xBF253AFF, 0xCF8287FF, 0xDA6F4AFF,
0x50316FFF, 0xE9832BFF, 0x86DE67FF, 0x5EF4AEFF, 0x7DD2C5FF, 0x642030FF, 0xAA78AFFF, 0x4A172EFF,
0xBBE8B6FF, 0x0B1D8BFF, 0x4ED540FF, 0x992E9DFF, 0x4DADB8FF, 0x66B441FF, 0x704BE4FF, 0x9D221EFF,
0x42912FFF, 0xC05E89FF, 0x695F23FF, 0xE3F63EFF, 0x4DD277FF, 0x184A25FF, 0x7E60EEFF, 0x3B7A58FF,
0x1A29E5FF, 0xBD64E1FF, 0xD65B24FF, 0xEE3087FF, 0xD739D9FF, 0xD9BAF5FF, 0xE69C3BFF, 0x96832AFF,
0x47F543FF, 0x5DA6EDFF, 0x378A64FF, 0x5C8296FF, 0x3FA867FF, 0x48225DFF, 0xA89A2FFF, 0x7463A4FF,
0x75BEEDFF, 0x583CB9FF, 0xED40ABFF, 0x9EF55AFF, 0x23577EFF, 0x4A2E15FF, 0x533A41FF, 0x141236FF,
0x417DF1FF, 0xDA2C51FF, 0xA094BEFF, 0x4EA094FF, 0xEED48EFF, 0x4FEB90FF, 0x914E71FF, 0x96CEF0FF,
0xDD86F2FF, 0xE4B539FF, 0x9E3BDAFF, 0xCF9A85FF, 0xF0A4CDFF, 0x2658E8FF, 0x821C16FF, 0x010001FF,
0xA1214FFF, 0x1D2868FF, 0x1B437BFF, 0x171FBFFF, 0xF1CEC7FF, 0xBF2879FF, 0x7A29A2FF, 0x6CBC96FF,
0xEB7D5CFF, 0x447F2DFF, 0x47D89AFF, 0xA5CD45FF, 0x6C3C5BFF, 0x4394F2FF, 0x725F48FF, 0x11351CFF,
0xA9696AFF, 0x080929FF, 0x6C4576FF, 0x3F7583FF, 0x7C744EFF, 0x5E4F24FF, 0x8E9864FF, 0xBB6E43FF,
0x4484C6FF, 0xC93B94FF, 0x936D96FF, 0xC07777FF, 0xC8FADEFF, 0x4361C3FF, 0xB8E13AFF, 0xAC351FFF,
0xD568F1FF, 0x001451FF, 0xC87CC2FF, 0x3C1F7EFF, 0xEC5143FF, 0x4A1210FF, 0xF4E2A0FF, 0x642BF0FF,
0xEB5269FF, 0xC08C5CFF, 0x31AE2DFF, 0xB36023FF, 0xE7BE6DFF, 0x0A156BFF, 0xB3B37CFF, 0x90512AFF,
0x611613FF, 0xF5C3F6FF, 0x95AAD9FF, 0x78896FFF, 0xF7E3F9FF, 0xBBD4F8FF, 0xEC9166FF, 0xC8CC97FF,
0xAAF78DFF, 0x8B79D1FF, 0x1F6B21FF, 0x661E5DFF, 0xB35DCAFF, 0xC04A43FF, 0x38C559FF, 0x71217CFF,
0x27515BFF, 0x4E3B20FF, 0xCC93AFFF, 0x6CE337FF, 0x875D87FF, 0xEFA1F4FF, 0xF0BDB0FF, 0xB8B433FF,
0x564B94FF, 0x8491A8FF, 0x9B5246FF, 0xC78327FF, 0xF33ECBFF, 0x9FF8CCFF, 0x2E123DFF, 0x9F307DFF,
0xC4689EFF, 0x7AAA6DFF, 0xD3AA95FF, 0xF7FCC8FF, 0x3A9783FF, 0xD3EA6BFF, 0x4E1748FF, 0xF0669AFF,
0x97DDC9FF, 0x210807FF, 0xCB5E60FF, 0xF46AF2FF, 0xC6AA5DFF, 0xE3E635FF, 0x3077A7FF, 0x4724D5FF,
0xE76724FF, 0x7E96DEFF, 0xD62A1EFF, 0x812DBAFF, 0xC59DCDFF, 0xAEBAECFF, 0x4A4574FF, 0x0D240CFF,
0x4823ABFF, 0x868054FF, 0x852462FF, 0x618BA6FF, 0x7CC234FF, 0xB12DA5FF, 0xED9396FF, 0xB28BA4FF,
0x787E27FF, 0xD743F1FF, 0xDB2FAAFF, 0xCC9A2EFF, 0xF0B5D6FF, 0x89C5A8FF, 0x8258C5FF, 0x696B70FF,

 */
public class RandomPaletteGenerator {
    private static final int limit = 256;
    private static final IntArray rgba = new IntArray(limit);
    private static final FloatArray ipts = new FloatArray(limit);
    private static void add(int value){
        float ipt = ColorTools.fromRGBA8888(value);
        float i = ColorTools.intensity(ipt),
                p = OtherMath.barronSpline(ColorTools.protan(ipt), 0.75f, 0.5f),
                t = OtherMath.barronSpline(ColorTools.tritan(ipt), 0.75f, 0.5f);
        final double limit = 0.011 - (ipts.size * 0.00002);
        for (int idx = 0; idx < ipts.size; idx++) {
            float o = ipts.get(idx);
            if(Vector3.dst2(i * 2, p, t, ColorTools.intensity(o) * 2, ColorTools.protan(o), ColorTools.tritan(o)) < limit)
                return;
        }
        rgba.add(value);
        ipts.add(ipt);
    }
    private static void add(float ipt){
        float i = ColorTools.intensity(ipt),
                p = ColorTools.protan(ipt),
                t = ColorTools.tritan(ipt);
//                p = PaletteReducer.barronSpline(ColorTools.protan(ipt), 0.75f, 0.5f),
//                t = PaletteReducer.barronSpline(ColorTools.tritan(ipt), 0.75f, 0.5f);
        if(!ColorTools.inGamut(i, p, t))
            return;
        ipt = ColorTools.ipt(i, p, t, 1f);
        final double limit = 0.011 - (ipts.size * 0.00002);
        for (int idx = 0; idx < ipts.size; idx++) {
            float o = ipts.get(idx);
            if(Vector3.dst2(i * 2, p, t, ColorTools.intensity(o) * 2, ColorTools.protan(o), ColorTools.tritan(o)) < limit)
                return;
        }
        rgba.add(ColorTools.toRGBA8888(ipt));
        ipts.add(ipt);
    }
    public static void main(String[] args) {
        rgba.add(0);
        add(0x0B080FFF);
        add(0xFAF7F0FF);
        add(0x797577FF);
        add(0x555555FF);
        add(0xAAAAAAFF);
        add(0x353336FF);
        add(0xE0E0E0FF);
        add(0xC8C8C8FF);
//        int idx = 1, initial = rgba.size;
        int idx = 1;
        RandomXS128 random = new RandomXS128(1L);
//        RandomXS128 random = new RandomXS128(0xB0BAFE77BA77L, 0xCAFEF00D15BADL);
        while (rgba.size < limit) {
//            add(ColorTools.randomColor(random));
            add(haltonColor(idx));
            idx++;
//            for (int i = initial; i < 32 && rgba.size < limit; i++) {
//                float color = gaussianColor(idx++, (1.0 - i * i * i * 0x1p-16));
//                if (ColorTools.inGamut(color)) {
//                    rgba.add(ColorTools.toRGBA8888(color));
//                }
//            }
        }
        System.out.println(idx);
        StringBuilder sb = new StringBuilder(12 * rgba.size + 35).append("{\n");
        for (int i = 0; i < rgba.size; i++) {
            StringKit.appendHex(sb.append("0x"), rgba.get(i)).append(", ");
            if(7 == (i & 7)) sb.append('\n');
        }
        System.out.println(sb.append('}'));
//
//        System.out.println();
//        for (int i = 0; i < ipts.size; i++) {
//            float c = ipts.get(i);
//            System.out.printf("I=%f, P=%f, T=%f, RGBA=%08X\n",
//                    ColorTools.intensity(c), ColorTools.protan(c), ColorTools.tritan(c), ColorTools.toRGBA8888(c));
//        }
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
//        sat *= 0.5;
        sat *= 2.5;
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
//        resY -= 0.5;
//        resZ -= 0.5;
        return ColorTools.ipt(
//                (float)resX,
//                (float)((resY - 0.5) % sat) + 0.5f,
//                (float)((resZ - 0.5) % sat) + 0.5f,
                (float) Math.pow(OtherMath.probit(resX) * 2.0 % 0.5 + 0.5, 0.9375),
//                (float)(resY * resY * resY * sat) + 0.5f,
//                (float)(resZ * resZ * resZ * sat) + 0.5f,
//                PaletteReducer.barronSpline((float)resY, 0.5f, 0.5f),
//                PaletteReducer.barronSpline((float)resZ, 0.5f, 0.5f),
//                (PaletteReducer.barronSpline((float)resY, 0.5f, 0.5f) - 0.5f) * (float) sat + 0.5f,
//                (PaletteReducer.barronSpline((float)resZ, 0.5f, 0.5f) - 0.5f) * (float) sat + 0.5f,
//                (float)resY,
//                (float)resZ,
//                PaletteReducer.barronSpline((float) resX, 1.25f, 0.45f),
//                (float) (PNG8.probit(resX) * 2.0 % 0.5 + 0.5),
                (float) (OtherMath.probit(resY) * sat % 0.5 + 0.5),
                (float) (OtherMath.probit(resZ) * sat % 0.5 + 0.5),
//                (float) (PNG8.probit(PaletteReducer.barronSpline((float)resY, 1.5f, 0.5f)) * sat + 0.5),
//                (float) (PNG8.probit(PaletteReducer.barronSpline((float)resZ, 1.5f, 0.5f)) * sat + 0.5),
                1f);
    }

}
