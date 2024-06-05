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

import static com.github.tommyettinger.colorful.oklab.Gamut.GAMUT_DATA;

// example output
/*
{
0x00000000, 0x000000FF, 0xFFFFFFFF, 0x888888FF, 0x444444FF, 0xCCCCCCFF, 0x222222FF, 0xAAAAAAFF,
0x666666FF, 0xEEEEEEFF, 0x111111FF, 0x999999FF, 0x555555FF, 0xDDDDDDFF, 0x333333FF, 0xBBBBBBFF,
0x777777FF, 0xC72027FF, 0xB37A79FF, 0x845C41FF, 0xF5AFC0FF, 0xD37B1EFF, 0x8F283FFF, 0xE0422DFF,
0xC59384FF, 0x590107FF, 0xDA5E82FF, 0x97754AFF, 0xE79520FF, 0xA7444AFF, 0xF85E32FF, 0xD6AD8EFF,
0x732711FF, 0xEF798EFF, 0xA88F52FF, 0xFAAF20FF, 0xBD5E54FF, 0xC61453FF, 0xE7C797FF, 0x402125FF,
0x8B4118FF, 0xD2785EFF, 0xDF3B5EFF, 0x553A32FF, 0xA15A1FFF, 0xA91D26FF, 0x977075FF, 0xE69166FF,
0xF75968FF, 0x68533EFF, 0xB57324FF, 0x732239FF, 0xC23D2EFF, 0xA88A81FF, 0xF8AB6DFF, 0x7A6C48FF,
0xE7C1C8FF, 0xC98D28FF, 0x8A3D46FF, 0xDA5834FF, 0xB8A48CFF, 0x582010FF, 0xD2718AFF, 0xF6DBD3FF,
0xDBA62AFF, 0xA05651FF, 0xF1733AFF, 0xA8114EFF, 0xFC2C42FF, 0x6F3919FF, 0xE58C96FF, 0xB5705CFF,
0xC2375AFF, 0x3C2F2CFF, 0x855220FF, 0xF8A6A0FF, 0x8C1924FF, 0xC88965FF, 0xDA5366FF, 0x996B27FF,
0xA5382DFF, 0x8B807DFF, 0xDAA36DFF, 0xF06E70FF, 0xFB206FFF, 0xAB842CFF, 0x6F3541FF, 0xBD5235FF,
0xEBBC74FF, 0x3D170DFF, 0xD7D1D0FF, 0x844E4DFF, 0xD36C3CFF, 0xDE2B41FF, 0xFCD67BFF, 0x543017FF,
0xC88392FF, 0x986759FF, 0xE88642FF, 0xA53156FF, 0xF74B49FF, 0x694920FF, 0xDA9D9EFF, 0x6F1421FF,
0xAB8063FF, 0xFBA046FF, 0xBC4D62FF, 0x7C6128FF, 0xEBB7A8FF, 0x89312BFF, 0xBC9A6CFF, 0xD2676DFF,
0xDD1E6BFF, 0xFBD1B1FF, 0xA04B34FF, 0xCCB374FF, 0x240E08FF, 0xFA0A18FF, 0xE78177FF, 0xF64376FF,
0x694548FF, 0xB6653CFF, 0xC0283FFF, 0x3B2614FF, 0xFB9B80FF, 0x7C5E55FF, 0xCA7E43FF, 0xD94648FF,
0x4F3E1EFF, 0xBC949AFF, 0x530D1CFF, 0x8E7760FF, 0xDD9849FF, 0xA0465DFF, 0xF06250FF, 0xCCAEA5FF,
0x6D2A28FF, 0xEFB24EFF, 0xB56069FF, 0xDCC8B0FF, 0x844432FF, 0xFB95AFFF, 0x0C0402FF, 0xDB0E1CFF,
0xC97A74FF, 0xD83F72FF, 0x995D3BFF, 0xA2233BFF, 0x221B0FFF, 0xF53B21FF, 0xDC937EFF, 0xEF5C7EFF,
0x605350FF, 0xAD7643FF, 0xBB4146FF, 0xEFAD87FF, 0x380416FF, 0xBF8F4AFF, 0xD25C4FFF, 0xAEA4A2FF,
0x522223FF, 0x9F3F05FF, 0xD1A950FF, 0x995865FF, 0xE87657FF, 0xF53459FF, 0x683B2FFF, 0xB6590CFF,
0xF9F6F6FF, 0xBD0F1EFF, 0xAC7171FF, 0xFD905EFF, 0x7D5439FF, 0xEFA7B7FF, 0xCB730FFF, 0x861E37FF,
0xD73824FF, 0xBF8B7CFF, 0xD2567AFF, 0x906D42FF, 0xE08C10FF, 0x9E3B42FF, 0xF0552AFF, 0xD0A486FF,
0x6A1E03FF, 0xE87086FF, 0xA2864AFF, 0xF3A60EFF, 0xB5554DFF, 0xE1BE8FFF, 0x38191DFF, 0x83380DFF,
0xFC8B90FF, 0xCA6F56FF, 0xD63156FF, 0x4E322AFF, 0x995214FF, 0x9F0D1EFF, 0x90686DFF, 0xDF895EFF,
0xEF4F60FF, 0x624A36FF, 0xAE6B19FF, 0x691831FF, 0xB93326FF, 0xA18279FF, 0xF2A265FF, 0x746340FF,
0xE1B8BFFF, 0xC2841CFF, 0x82343EFF, 0xD2502CFF, 0xB29B84FF, 0x4F1705FF, 0xCA6982FF, 0xF0D2CAFF,
0xD49E1EFF, 0x984E4AFF, 0xE96A31FF, 0xF31E3AFF, 0x67300FFF, 0xDE838DFF, 0xFFEDD4FF, 0xAD6754FF,
0xFE8436FF, 0xB92D52FF, 0x342724FF, 0x7D4917FF, 0xF19D98FF, 0x82091DFF, 0xC1815DFF, 0xD14A5EFF,
0x474031FF, 0x92621DFF, 0x9C2E25FF, 0x857875FF, 0xD39A65FF, 0xE86568FF, 0xF20667FF, 0xA57B22FF,
0x672C39FF, 0xB44A2DFF, 0xE5B46DFF, 0x340F03FF, 0xFD8071FF, 0xD1C9C8FF, 0x7D4646FF, 0xCB6434FF,
0xD41D39FF, 0xF6CE73FF, 0x4C280FFF, 0xC17B8AFF, 0x915F51FF, 0xE07D39FF, 0x9C284EFF, 0xEE4141FF,
}

 */
public class CuddlyPaletteGenerator {
    private static final int limit = 256;
    private static final IntArray rgba = new IntArray(limit);
    private static final FloatArray labs = new FloatArray(limit);
    private static int idx = 1;
    private static long LL = 0xD1B54A32D192ED03L, AA = 0xABC98388FB8FAC03L, BB = 0x8CB92BA72F3D8DD7L;

    private static void addL(int rgba8888){
        float oklab = ColorTools.oklab((rgba8888 >>> 8 & 255) / 255f, 0.5f, 0.5f, 1f);
        rgba.add(ColorTools.toRGBA8888(oklab));
        labs.add(oklab);
    }
    private static void add(){
        if(++idx == 42)
            System.out.println("Here we go!");
        LL += 0xD1B54A32D192ED03L;
        AA += 0xABC98388FB8FAC03L;
        BB += 0x8CB92BA72F3D8DD7L;

        double L0 = (LL >>> 11) * 0x1p-53;
        double A0 = (AA >>> 11) * 0x1p-53;
        double B0 = (BB >>> 11) * 0x1p-53;
        double L = reverseLight(L0);
        double A = A0;
        double B = B0;

        double l = (L + +0.3963377774 * A + +0.2158037573 * B);
        l *= l * l;
        double m = (L + -0.1055613458 * A + -0.0638541728 * B);
        m *= m * m;
        double s = (L + -0.0894841775 * A + -1.2914855480 * B);
        s *= s * s;

        double dr = Math.sqrt(+4.0767245293 * l - 3.3072168827 * m + 0.2307590544 * s)*255.0;
        final int r = (int)dr;
        if(Double.isNaN(dr) || r < 0 || r > 255) return;
        double dg = Math.sqrt(-1.2681437731 * l + 2.6093323231 * m - 0.3411344290 * s)*255.0;
        final int g = (int)dg;
        if(Double.isNaN(dg) || g < 0 || g > 255) return;
        double db = Math.sqrt(-0.0041119885 * l - 0.7034763098 * m + 1.7068625689 * s)*255.0;
        final int b = (int)db;
        if(Double.isNaN(db) || b < 0 || b > 255) return;

        float oklab = ColorTools.oklab((float) L0, (float) A0, (float) B0, 1f);

        rgba.add(r << 24 | g << 16 | b << 8 | 0xFF);
        labs.add(oklab);
    }
    public static void main(String[] args) {
//        System.out.printf("%08X, %1.4f, %08X\n", 0xFF0000FF, ColorTools.chroma(ColorTools.fromRGBA8888(0xFF0000FF)), ColorTools.toRGBA8888(ColorTools.maximizeSaturation(ColorTools.fromRGBA8888(0xFF0000FF))));
//        System.out.printf("%08X, %1.4f, %08X\n", 0x00FF00FF, ColorTools.chroma(ColorTools.fromRGBA8888(0x00FF00FF)), ColorTools.toRGBA8888(ColorTools.maximizeSaturation(ColorTools.fromRGBA8888(0x00FF00FF))));
//        System.out.printf("%08X, %1.4f, %08X\n", 0x0000FFFF, ColorTools.chroma(ColorTools.fromRGBA8888(0x0000FFFF)), ColorTools.toRGBA8888(ColorTools.maximizeSaturation(ColorTools.fromRGBA8888(0x0000FFFF))));
//        System.out.printf("%08X, %1.4f, %08X\n", 0xFFFF00FF, ColorTools.chroma(ColorTools.fromRGBA8888(0xFFFF00FF)), ColorTools.toRGBA8888(ColorTools.maximizeSaturation(ColorTools.fromRGBA8888(0xFFFF00FF))));
//        System.out.printf("%08X, %1.4f, %08X\n", 0x00FFFFFF, ColorTools.chroma(ColorTools.fromRGBA8888(0x00FFFFFF)), ColorTools.toRGBA8888(ColorTools.maximizeSaturation(ColorTools.fromRGBA8888(0x00FFFFFF))));
//        System.out.printf("%08X, %1.4f, %08X\n", 0xFF00FFFF, ColorTools.chroma(ColorTools.fromRGBA8888(0xFF00FFFF)), ColorTools.toRGBA8888(ColorTools.maximizeSaturation(ColorTools.fromRGBA8888(0xFF00FFFF))));

        rgba.add(0);
//        add(0x010101FF);
//        add(0xFEFEFEFF);
//        add(0x777777FF);
//        add(0x555555FF);
//        add(0xAAAAAAFF);
//        add(0x333333FF);
//        add(0xE0E0E0FF);
//        add(0xC8C8C8FF);

        addL(0x000000FF);
        addL(0xFFFFFFFF);
        addL(0x888888FF);
        addL(0x444444FF);
        addL(0xCCCCCCFF);
        addL(0x222222FF);
        addL(0xAAAAAAFF);
        addL(0x666666FF);
        addL(0xEEEEEEFF);
        addL(0x111111FF);
        addL(0x999999FF);
        addL(0x555555FF);
        addL(0xDDDDDDFF);
        addL(0x333333FF);
        addL(0xBBBBBBFF);
        addL(0x777777FF);

//        int idx = 1, initial = rgba.size;
        while (rgba.size < limit) {
//            add(ColorTools.randomColor(random));
            add();
        }
        System.out.println(idx + " attempts.");
        StringBuilder sb = new StringBuilder(12 * rgba.size + 35).append("{\n");
        for (int i = 0; i < rgba.size; i++) {
            StringKit.appendHex(sb.append("0x"), rgba.get(i)).append(", ");
            if(7 == (i & 7)) sb.append('\n');
        }
        System.out.println(sb.append('}'));

        System.out.println();
        for (int i = 0; i < labs.size; i++) {
            float c = labs.get(i);
            System.out.printf("L=%f,A=%f,B=%f,RGBA=0x%08X ",
                    ColorTools.channelL(c), ColorTools.channelA(c), ColorTools.channelB(c), ColorTools.toRGBA8888(c));
            if(7 == (i & 7)) System.out.println();;
        }
    }
    public static double reverseLight(double L) {
        return Math.pow(L, 2.0/3.0);
    }

    /**
     * Returns true if the given Oklab values are valid to convert losslessly back to RGBA.
     * @param L lightness channel, as a double from 0 to 1
     * @param A green-to-red chromatic channel, as a double from 0 to 1
     * @param B blue-to-yellow chromatic channel, as a double from 0 to 1
     * @return true if the given Oklab channels can be converted back and forth to RGBA
     */
    public static boolean inGamut(double L, double A, double B)
    {
        L = reverseLight(L);

        double l = (L + +0.3963377774 * A + +0.2158037573 * B);
        l *= l * l;
        double m = (L + -0.1055613458 * A + -0.0638541728 * B);
        m *= m * m;
        double s = (L + -0.0894841775 * A + -1.2914855480 * B);
        s *= s * s;

        double dr = Math.sqrt(+4.0767245293 * l - 3.3072168827 * m + 0.2307590544 * s)*255.0;
        final int r = (int)dr;
        if(Double.isNaN(dr) || r < 0 || r > 255) return false;
        double dg = Math.sqrt(-1.2681437731 * l + 2.6093323231 * m - 0.3411344290 * s)*255.0;
        final int g = (int)dg;
        if(Double.isNaN(dg) || g < 0 || g > 255) return false;
        double db = Math.sqrt(-0.0041119885 * l - 0.7034763098 * m + 1.7068625689 * s)*255.0;
        final int b = (int)db;
        return (!Double.isNaN(db) && b >= 0 && b <= 255);
    }


}
