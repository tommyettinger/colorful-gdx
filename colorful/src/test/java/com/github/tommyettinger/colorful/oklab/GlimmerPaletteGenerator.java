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
import com.github.tommyettinger.anim8.OtherMath;
import com.github.tommyettinger.colorful.TrigTools;
import com.github.tommyettinger.colorful.internal.StringKit;

import static com.github.tommyettinger.colorful.oklab.Gamut.GAMUT_DATA;

// example output
/*
//glimmer
{
0x00000000, 0x000000FF, 0xFFFFFFFF, 0x858585FF, 0x444444FF, 0xCCCCCCFF, 0x252524FF, 0xA7A7A7FF,
0x646464FF, 0xF2F2F2FF, 0x141414FF, 0x969696FF, 0x545454FF, 0xDFDFDFFF, 0x343434FF, 0xB9B9B9FF,
0x747474FF, 0x9AA483FF, 0xFF0063FF, 0x254862FF, 0x141C00FF, 0xFFE0E8FF, 0x00C1FFFF, 0x709500FF,
0xE8003DFF, 0x002E58FF, 0xF7FFDCFF, 0xFFABC2FF, 0x73A0C6FF, 0x4A7F00FF, 0x6C2435FF, 0x001132FF,
0xBDFC66FF, 0xFF4E8CFF, 0x0082FCFF, 0x1D6800FF, 0x57001AFF, 0xF9F9F9FF, 0x65F100FF, 0xD67681FF,
0x005BFFFF, 0x284416FF, 0x250004FF, 0xC4E1FFFF, 0x00DB00FF, 0xFF0044FF, 0x0026FFFF, 0x0A2B00FF,
0xFBF3F5FF, 0x82C3FFFF, 0x67A443FF, 0xF80009FF, 0x0F3370FF, 0x000C00FF, 0xFFC5C3FF, 0x1C9CFFFF,
0x009500FF, 0x5E4140FF, 0x000C60FF, 0xDFF8DDFF, 0xFF8787FF, 0x5689E9FF, 0x007E00FF, 0x640011FF,
0x03000EFF, 0x00FF28FF, 0xFF0025FF, 0x134EFFFF, 0x324D34FF, 0x400000FF, 0xE3EAFDFF, 0x00F800FF,
0xF4403CFF, 0x4B00FFFF, 0x003910FF, 0xFFF9F6FF, 0xB5CBFFFF, 0x87A48BFF, 0xFF0000FF, 0x283789FF,
0x001B00FF, 0xF9DED7FF, 0x85A3FFFF, 0x009E4BFF, 0xE20000FF, 0x1C008EFF, 0xDDFFE8FF, 0xFFAA93FF,
0x8B93C1FF, 0x008927FF, 0x602A1BFF, 0x110048FF, 0x93FABBFF, 0xFF5718FF, 0x6462FFFF, 0x006D16FF,
0x490000FF, 0xF6F6F6FF, 0x00FB74FF, 0xD0775BFF, 0x6300FFFF, 0x17412CFF, 0x180000FF, 0xD1D6FDFF,
0x00E25CFF, 0xDF3800FF, 0x7200FFFF, 0x002716FF, 0xF9EFEAFF, 0xB4ACFFFF, 0x539E7DFF, 0xCA0000FF,
0x321781FF, 0x000502FF, 0xFFC5A6FF, 0xA06EFFFF, 0x008E61FF, 0x50433DFF, 0x21005EFF, 0xD0F6E6FF,
0xFF882AFF, 0x8475E2FF, 0x00764AFF, 0x4B2006FF, 0xFFFCFFFF, 0x00FBBAFF, 0xFF0000FF, 0x8400FFFF,
0x354542FF, 0x290900FF, 0xE4E3F8FF, 0x00E6A7FF, 0xC56820FF, 0x8700FFFF, 0x003227FF, 0xFFF6EDFF,
0xD0B9FFFF, 0x8D9B97FF, 0xB94000FF, 0x443067FF, 0x001511FF, 0xF9D9C6FF, 0xC77CFFFF, 0x12937FFF,
0x9B2100FF, 0x330060FF, 0xBDFFF9FF, 0xFFA94DFF, 0x9889B6FF, 0x007E6CFF, 0x4A331EFF, 0x130026FF,
0x61F9E4FF, 0xFF5D00FF, 0xA428FFFF, 0x006357FF, 0x341800FF, 0xF2F2F2FF, 0x00EAD5FF, 0xAA865FFF,
0xA400FFFF, 0x163A38FF, 0x0A0000FF, 0xE6C7FDFF, 0x00D0C5FF, 0xAA6100FF, 0x8B00F0FF, 0x00211FFF,
0xEFECE6FF, 0xEA87FFFF, 0x569594FF, 0x964100FF, 0x451458FF, 0xF3FFFFFF, 0xFFC372FF, 0xFF00FFFF,
0x008485FF, 0x424242FF, 0x280037FF, 0xC4F1F4FF, 0xFF9100FF, 0xB94FDAFF, 0x006B74FF, 0x36270BFF,
0xFFF7FFFF, 0x00EDF8FF, 0xF97200FF, 0xBC00E7FF, 0x344042FF, 0x1A0E00FF, 0xF0D8F2FF, 0x00D4F2FF,
0x9F7730FF, 0xA900CCFF, 0x002B30FF, 0xFFF5D0FF, 0xFF94FFFF, 0x00BAE4FF, 0x8C5B00FF, 0x4C2C4BFF,
0x000F11FF, 0xF2DA9AFF, 0xFF00FFFF, 0x1C8999FF, 0x734100FF, 0x3A003CFF, 0xBDFFFFFF, 0xFCB300FF,
0xAD7BA6FF, 0x00738DFF, 0x3B3420FF, 0x0E000DFF, 0x8FE7F6FF, 0xE29600FF, 0xCB08B7FF, 0x005879FF,
0x231B00FF, 0xFFDBFFFF, 0x00DAFFFF, 0x928A62FF, 0xC200A4FF, 0x1A343BFF, 0xFFFFF2FF, 0xFBB9E6FF,
0x00BEFFFF, 0x876E00FF, 0xA3007CFF, 0x001B23FF, 0xEBEACEFF, 0xFF64E6FF, 0x568DA4FF, 0x6C5500FF,
0x491337FF, 0xEBFFFFFF, 0xE6CE00FF, 0xFF00E6FF, 0x0079A2FF, 0x4E3F00FF, 0x27001BFF, 0xD2E6EFFF,
0xC9B400FF, 0xCC4A94FF, 0x005E97FF, 0x2A2807FF, 0xFFF2FEFF, 0x65D6FFFF, 0xAB9900FF, 0xD2007FFF,
0x353B3EFF, 0x0F0D00FF, 0xF0D4E2FF, 0x00BFFFFF, 0x7D802DFF, 0xBA005FFF, 0x002536FF, 0xFDFF00FF,
}
//glimmer2
// uses 0.675, 0.8 Kumaraswamy distribution for lightness, sqrt for chroma. LAB use R3.
{
0x00000000, 0x000000FF, 0xFFFFFFFF, 0x858585FF, 0x444444FF, 0xCCCCCCFF, 0x252524FF, 0xA7A7A7FF,
0x646464FF, 0xF2F2F2FF, 0x141414FF, 0x969696FF, 0x545454FF, 0xDFDFDFFF, 0x343434FF, 0xB9B9B9FF,
0x747474FF, 0x616B43FF, 0x8D0032FF, 0xECF8FFFF, 0x9EE700FF, 0xFF6696FF, 0x0079FFFF, 0x475E00FF,
0x590017FF, 0xB3E6FFFF, 0x75DA00FF, 0xFF0071FF, 0x376D9DFF, 0x274A00FF, 0xFFF3F7FF, 0x76D4FFFF,
0x79BB00FF, 0xFF0042FF, 0x0055B4FF, 0x102D00FF, 0xFFD0DAFF, 0x9FBADAFF, 0x46B100FF, 0xBE3353FF,
0x0037AEFF, 0xF0FFE8FF, 0xFFA3B0FF, 0x51A9FFFF, 0x00A300FF, 0xC7002BFF, 0x000F88FF, 0x95FF4AFF,
0xE4A8ACFF, 0x0089FFFF, 0x3B8116FF, 0xAE0000FF, 0xFAFDFFFF, 0x00FF00FF, 0xFF6A74FF, 0x004DFFFF,
0x007500FF, 0x3F2324FF, 0xD4E9FFFF, 0x5FE04CFF, 0xFF002FFF, 0x1D60FFFF, 0x006000FF, 0x080000FF,
0xA5D3FFFF, 0x00DD00FF, 0xFF0000FF, 0x2000FFFF, 0x1B371BFF, 0xFFDCD5FF, 0xA3BFF9FF, 0x00D100FF,
0xEC0000FF, 0x5800FFFF, 0x001300FF, 0xFFB2A5FF, 0x79A4FFFF, 0x67936CFF, 0xEC0000FF, 0x1B2382FF,
0xA7FFBCFF, 0xFFA79AFF, 0x637DFFFF, 0x009332FF, 0xCE0000FF, 0x110052FF, 0x00FF31FF, 0xFF7452FF,
0x7382D0FF, 0x008220FF, 0x611B0FFF, 0xEAEDFFFF, 0x00F07BFF, 0xFF0000FF, 0x584BFFFF, 0x006D15FF,
0x370000FF, 0xD6DAE6FF, 0x00E965FF, 0xE2613AFF, 0x6900FFFF, 0x034429FF, 0xFFECDFFF, 0xB8C0FFFF,
0x00D754FF, 0xEF1C00FF, 0x7A00FFFF, 0x002715FF, 0xF3D6C9FF, 0xA79DFFFF, 0x24A77AFF, 0xDE0000FF,
0x3E00AFFF, 0xC2FFEBFF, 0xFFAF80FF, 0x9F6CFFFF, 0x009D64FF, 0x644D40FF, 0x2C0085FF, 0x9EF6D6FF,
0xFF7900FF, 0x9075FFFF, 0x008B59FF, 0x6D2700FF, 0xF7F6FFFF, 0x00FEB8FF, 0xFF0000FF, 0x940EFFFF,
0x3B5E52FF, 0x4A0C00FF, 0xE4DDFAFF, 0x00F1AEFF, 0xEC6800FF, 0xA600FFFF, 0x00513FFF, 0xFFF6EDFF,
0xD5BFFFFF, 0x90ADA6FF, 0xE84600FF, 0x6738B4FF, 0x00352BFF, 0xFFDEC5FF, 0xD194FFFF, 0x00B398FF,
0xD52B00FF, 0x6400C7FF, 0xEBFFFEFF, 0xFFBA72FF, 0xAF9CD8FF, 0x00A58CFF, 0x824D1DFF, 0x4F009DFF,
0x95FFF1FF, 0xFF8900FF, 0xBD5EFFFF, 0x009283FF, 0x753300FF, 0x070508FF, 0x00FFF2FF, 0xE39A51FF,
0xD000FFFF, 0x046D68FF, 0x551E00FF, 0xF3E4FFFF, 0x00F7EAFF, 0xEE7800FF, 0xE200FFFF, 0x005C59FF,
0x130E0AFF, 0xF7BBFFFF, 0x4CC4C0FF, 0xE15F00FF, 0x9500CDFF, 0x004241FF, 0xFFE8C6FF, 0xFF83FFFF,
0x00BBBFFF, 0x76746FFF, 0x8C00C1FF, 0x0A1818FF, 0xFFC844FF, 0xE087F9FF, 0x00ACB8FF, 0x895700FF,
0x700099FF, 0xB6FFFFFF, 0xFF9900FF, 0xFF00FFFF, 0x538081FF, 0x774100FF, 0x270D2EFF, 0x00FFFFFF,
0xEBA300FF, 0xFF00FFFF, 0x007583FF, 0x5C2C00FF, 0xFFEBFFFF, 0x00FAFFFF, 0xE38D00FF, 0xB04CB5FF,
0x006377FF, 0x261E06FF, 0xFFB8FFFF, 0x00D2ECFF, 0xD47800FF, 0xC200C1FF, 0x004A62FF, 0xFFF7CFFF,
0xDFBBDFFF, 0x00C1EEFF, 0x94782EFF, 0xB800ABFF, 0x02272DFF, 0xFFD700FF, 0xFF78F5FF, 0x00AFF0FF,
0x896300FF, 0x960086FF, 0xE2FFFFFF, 0xE6CB70FF, 0xFF00FFFF, 0x278CA9FF, 0x735100FF, 0x47003CFF,
0x82F8FFFF, 0xE1B600FF, 0xFF00FFFF, 0x007EA8FF, 0x444230FF, 0xFFF7FFFF, 0x94DBF4FF, 0xD0A400FF,
0xE900B0FF, 0x00689FFF, 0x322A00FF, 0xFFCCF5FF, 0x00D4FFFF, 0xBD9200FF, 0xF0009DFF, 0x2B4955FF,
0xFFFFDDFF, 0xFFB9DAFF, 0x00C3FFFF, 0x8D8700FF, 0xE20082FF, 0x00334BFF, 0xFFF100FF, 0xFF87D0FF,
0x769DB2FF, 0x797700FF, 0x782350FF, 0x000A11FF, 0xDAE000FF, 0xFF26C3FF, 0x0094DAFF, 0x646400FF,
}

 */
public class GlimmerPaletteGenerator {
    private static final int limit = 256;
    private static final IntArray rgba = new IntArray(limit);
    private static final FloatArray labs = new FloatArray(limit);
    private static int idx = 1;
    private static double chroma = 0.5, lightness = 0.5;
    private static void addL(int rgba8888){
        float oklab = ColorTools.oklab((rgba8888 >>> 8 & 255) / 255f, 0.5f, 0.5f, 1f);
        rgba.add(ColorTools.toRGBA8888(oklab));
        labs.add(oklab);
    }
    private static void add(){
        ++idx;
        lightness += 0.8191725133961645;
        lightness -= (int)lightness;
        int li = (int)(Math.pow(1.0-Math.pow(1.0- lightness, 0.8), 0.675) * 255.999), hue = (0xAB * idx & 0xff);
//        int li = (int)(Math.pow(1.0-Math.pow(1.0- lightness, 1.3), 1.7) * 255.999), hue = (0xAB * idx & 0xff);
        final int i = li << 8 | hue;
        chroma += 0.5497004779019703;
        chroma -= (int)chroma;
        double ch = Math.sqrt(chroma);
        final double dist = GAMUT_DATA[i] * ch;
        if(li == 0) System.out.println("HSL " + hue * 0x1p-8f + " " + ch * ch + " " + li * 0x1p-8f + " (" + lightness + ") ");
        float oklab = NumberUtils.intBitsToFloat(
                (0xFE000000) | li |
                        (int) (TrigTools.sinTurns(hue * 0x1p-8f) * dist + 127.5f) << 16 |
                        (int) (TrigTools.cosTurns(hue * 0x1p-8f) * dist + 127.5f) << 8);

        int reg = ColorTools.toRGBA8888(oklab);
            rgba.add(reg);
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
            System.out.printf("L=%f,A=%f,B=%f,RGBA=%08X ",
                    ColorTools.channelL(c), ColorTools.channelA(c), ColorTools.channelB(c), ColorTools.toRGBA8888(c));
            if(7 == (i & 7)) System.out.println();;
        }
    }
}
