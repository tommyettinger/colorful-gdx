package com.github.tommyettinger.colorful.oklab;

import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.IntArray;
import com.github.tommyettinger.anim8.OtherMath;
import com.github.tommyettinger.colorful.internal.StringKit;

// example output
/*
{
0x00000000, 0x000000FF, 0xFFFFFFFF, 0x888888FF, 0x444444FF, 0xCCCCCCFF, 0x222222FF, 0xAAAAAAFF,
0x666666FF, 0xEEEEEEFF, 0x111111FF, 0x999999FF, 0x555555FF, 0xDDDDDDFF, 0x333333FF, 0xBBBBBBFF,
0x777777FF, 0x4416A6FF, 0x795AF6FF, 0x5833C5FF, 0xDF6F08FF, 0xC651A3FF, 0x992E7BFF, 0xE268BCFF,
0x55842DFF, 0x8FC666FF, 0x335D00FF, 0x6A9C42FF, 0xA7E17DFF, 0x577DBBFF, 0x001D49FF, 0x91BDFFFF,
0x365890FF, 0x6D95D6FF, 0x173465FF, 0xFEAFA1FF, 0x8B4D43FF, 0xD0877BFF, 0x612B24FF, 0xA56258FF,
0x9438C1FF, 0x6A0092FF, 0xAE4FDEFF, 0x70D7BFFF, 0x006C5BFF, 0x48AC96FF, 0x87F1D8FF, 0x208471FF,
0xF82E8AFF, 0x001686FF, 0x235AD7FF, 0x0032A4FF, 0x3773F5FF, 0xC59D19FF, 0xDFB536FF, 0xC697CCFF,
0x5E3A63FF, 0x9D72A2FF, 0x36183AFF, 0xE0B0E7FF, 0x764F7BFF, 0x00C04BFF, 0x16DA61FF, 0x932FFFFF,
0xEF5445FF, 0xBE2E24FF, 0x00B9E6FF, 0x13D4FFFF, 0xF346D6FF, 0xC115AAFF, 0x0022EEFF, 0xF3F7C8FF,
0x878A63FF, 0x2B2C0FFF, 0xC7CB9FFF, 0x636542FF, 0x9FA279FF, 0x404122FF, 0x5541A4FF, 0x8E7CEEFF,
0x341A74FF, 0x6956BFFF, 0x6D0D39FF, 0xB74A72FF, 0x89264EFF, 0xD46089FF, 0xAEE140FF, 0x86B500FF,
0xC6FC59FF, 0xC112F3FF, 0x17394AFF, 0xACDCF6FF, 0x4C7489FF, 0x85B2CAFF, 0x2A4E61FF, 0x618BA1FF,
0x3300B7FF, 0x422BD8FF, 0xF1A165FF, 0xC47A42FF, 0x99561FFF, 0xAE59BAFF, 0xF696FFFF, 0x83358EFF,
0xC86FD4FF, 0x5A0D64FF, 0x66C884FF, 0x005F2BFF, 0x419E60FF, 0x7EE29CFF, 0x19773FFF, 0xE72057FF,
0x347CD2FF, 0x0A56A4FF, 0x4994EEFF, 0x77525AFF, 0xB88C95FF, 0x4F3037FF, 0xFFCDD7FF, 0x8F676FFF,
0x270F15FF, 0xD3A4AEFF, 0x4FF737FF, 0x1ACA00FF, 0x7E37D5FF, 0x5900A4FF, 0x9550F3FF, 0x46F5F5FF,
0x00C9CAFF, 0xE040A1FF, 0xB01279FF, 0xE7E786FF, 0x7D7C27FF, 0xBCBC60FF, 0xFFFF9CFF, 0x95943DFF,
0xA79BE5FF, 0x473B74FF, 0x8074B8FF, 0x251948FF, 0xBFB3FFFF, 0x5C518EFF, 0xD66263FF, 0xA73E40FF,
0xF27877FF, 0x791922FF, 0xDD4AECFF, 0xAF1EBEFF, 0xFA63FFFF, 0x658F7DFF, 0x0C2E22FF, 0xA2D1BCFF,
0x426859FF, 0x7BA694FF, 0x224437FF, 0xBBECD6FF, 0x707CFFFF, 0x231386FF, 0x5056D6FF, 0x332FA5FF,
0xE49200FF, 0xFFAA2EFF, 0x9E5187FF, 0xE58CC8FF, 0x742F61FF, 0xB9679FFF, 0x490B3AFF, 0x277800FF,
0x61B946FF, 0x9FFF83FF, 0x3C8F1FFF, 0x78D45DFF, 0xAE1DFFFF, 0x25739EFF, 0x61B3E3FF, 0x004E74FF,
0x3C8BB8FF, 0x2C1CECFF, 0xD3AA85FF, 0x68482BFF, 0xA88260FF, 0x41270BFF, 0xEDC29BFF, 0x805E3FFF,
0x6F39A3FF, 0xAE74EDFF, 0x491074FF, 0x874FBFFF, 0x13CCA1FF, 0x3BE6B9FF, 0xD0376FFF, 0x0093FFFF,
0xDDD737FF, 0xF8F353FF, 0x9A91AEFF, 0x3B3449FF, 0xDCD3F3FF, 0x746C87FF, 0x171121FF, 0xB2AAC8FF,
0x504961FF, 0x6E35E6FF, 0x834EFFFF, 0xC75426FF, 0xE46B3CFF, 0xCD48B8FF, 0x9E208DFF, 0xEA5FD3FF,
0xC0EEA8FF, 0x5C8148FF, 0x97C181FF, 0x3B5B28FF, 0x72995DFF, 0x1B3700FF, 0x2F3B87FF, 0x6277CFFF,
0x131456FF, 0x4151A2FF, 0x788EECFF, 0x4B1223FF, 0x914958FF, 0xD88393FF, 0x652736FF, 0xAC5E6EFF,
0x79D500FF, 0x8FF02BFF, 0xC752FFFF, 0x9B27CFFF, 0x73D3D6FF, 0x006A6EFF, 0x4EA9ADFF, 0x8CEEF1FF,
0x278285FF, 0xFF1999FF, 0x3052EBFF, 0x1827B8FF, 0xC69A45FF, 0x9C7420FF, 0xE0B35CFF, 0x87579DFF,
0xC993E3FF, 0x5F3473FF, 0x9F6CB6FF, 0x391149FF, 0xE4ABFEFF, 0x05BD68FF, 0x36D87EFF, 0xA30B2BFF,
0xF2535FFF, 0xC02B3EFF, 0x19B5FDFF, 0xF935E5FF, 0x1A00FDFF, 0x534C42FF, 0xFAF1E1FF, 0x8E867AFF,
}

 */
public class CornPaletteGenerator {
    private static final int limit = 256;
    private static final IntArray rgba = new IntArray(limit);
    private static final FloatArray labs = new FloatArray(limit);
    private static int idx = 1;
    private static void add(int value){
        float oklab = ColorTools.fromRGBA8888(value);
        rgba.add(value);
        labs.add(oklab);
    }
    private static void add(float oklab){
        if(oklab == 0f) return;
        float L = ColorTools.channelL(oklab), A = ColorTools.channelA(oklab), B = ColorTools.channelB(oklab);
        int ctr = 0;
        for (int hi = 0; hi < 8; hi++) {
            if(rgba.size >= limit) break;
            oklab = ColorTools.oklab((L + 0.6180339887498949f * hi) % 1f, A, B, 1f);
            if (!ColorTools.inGamut(oklab))
                continue;
            int reg = ColorTools.toRGBA8888(oklab);
            rgba.add(reg);
            labs.add(oklab);
            ctr++;
        }
        if(ctr != 0)
            System.out.print(ctr + " ");
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

        add(0x000000FF);
        add(0xFFFFFFFF);
        add(0x888888FF);
        add(0x444444FF);
        add(0xCCCCCCFF);
        add(0x222222FF);
        add(0xAAAAAAFF);
        add(0x666666FF);
        add(0xEEEEEEFF);
        add(0x111111FF);
        add(0x999999FF);
        add(0x555555FF);
        add(0xDDDDDDFF);
        add(0x333333FF);
        add(0xBBBBBBFF);
        add(0x777777FF);

//        int idx = 1, initial = rgba.size;
        while (rgba.size < limit) {
//            add(ColorTools.randomColor(random));
            add(oklabR2Color(idx));
            if(++idx % 10000 == 0)
                System.out.println(idx + " tries, " + rgba.size + " placed");
//            ++idx;
//            for (int i = initial; i < 32 && rgba.size < limit; i++) {
//                float color = gaussianColor(idx++, (1.0 - i * i * i * 0x1p-16));
//                if (ColorTools.inGamut(color)) {
//                    rgba.add(ColorTools.toRGBA8888(color));
//                }
//            }
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
    public static float oklabR2Color(int index)
    {
        float L = ((0x9E3779B97F4A7C15L * index >>> 41) * 0x1p-23f + 0.6f) % 1f;
        float A = ((0xC13FA9A902A6328FL * index >>> 41) * 0x1p-23f + 0.5f) % 1f * 0.6f + 0.2f;
        float B = ((0x91E10DA5C79E7B1DL * index >>> 41) * 0x1p-23f + 0.5f) % 1f * 0.6f + 0.2f;

        return ColorTools.oklab(L, A, B, 1f);
    }
}
