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

 */
public class GlimmerPaletteGenerator {
    private static final int limit = 256;
    private static final IntArray rgba = new IntArray(limit);
    private static final FloatArray labs = new FloatArray(limit);
    private static int idx = 1;
    private static float chroma = 0.5f;
    private static void addL(int rgba8888){
        float oklab = ColorTools.oklab((rgba8888 >>> 8 & 255) / 255f, 0.5f, 0.5f, 1f);
        rgba.add(ColorTools.toRGBA8888(oklab));
        labs.add(oklab);
    }
    private static void add(){
        ++idx;
        int li = (idx * 0xD1 & 0xff), hue = (0xAB * idx & 0xff);
        final int i = li << 8 | hue;
        chroma += 0.5497004779019703f;
        chroma -= (int)chroma;
        float ch = OtherMath.cbrt(chroma);
        final double dist = GAMUT_DATA[i] * ch * ch;
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
