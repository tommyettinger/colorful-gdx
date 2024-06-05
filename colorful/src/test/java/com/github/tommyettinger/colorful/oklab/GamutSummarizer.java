package com.github.tommyettinger.colorful.oklab;

import com.badlogic.gdx.utils.IntSet;
import com.badlogic.gdx.utils.NumberUtils;

public class GamutSummarizer {
    public static final byte[] CHROMA_LIMITS_BY_L = new byte[]{
            2, 3, 3, 4, 4, 5, 5, 5, 6, 6, 7, 7, 7, 7, 8, 8,
            8, 9, 9, 9, 9, 10, 10, 10, 11, 11, 11, 11, 11, 12, 12, 12,
            12, 13, 13, 13, 13, 14, 14, 14, 14, 14, 15, 15, 15, 15, 15, 16,
            16, 16, 16, 16, 17, 17, 17, 17, 17, 18, 18, 18, 18, 18, 18, 19,
            19, 19, 19, 19, 20, 20, 20, 20, 20, 20, 21, 21, 21, 21, 21, 21,
            22, 22, 22, 22, 22, 22, 23, 23, 23, 23, 23, 23, 24, 24, 24, 24,
            24, 24, 25, 25, 25, 25, 25, 25, 25, 26, 26, 26, 26, 26, 26, 27,
            27, 27, 27, 27, 27, 27, 28, 28, 28, 28, 28, 28, 28, 29, 29, 29,
            29, 29, 29, 29, 30, 30, 30, 30, 30, 30, 30, 31, 31, 31, 31, 31,
            31, 31, 32, 32, 32, 32, 32, 32, 32, 32, 33, 33, 33, 33, 33, 33,
            33, 34, 34, 34, 34, 34, 34, 34, 34, 33, 33, 32, 32, 31, 31, 31,
            30, 30, 29, 29, 29, 28, 28, 27, 27, 26, 26, 26, 25, 25, 25, 24,
            24, 23, 23, 23, 22, 22, 21, 21, 21, 20, 20, 20, 19, 19, 18, 18,
            18, 17, 17, 17, 16, 16, 16, 15, 15, 14, 14, 14, 13, 13, 13, 12,
            12, 12, 11, 11, 11, 10, 10, 10, 9, 9, 9, 8, 8, 8, 7, 7,
            7, 6, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3, 3, 2, 2, 2,
    };

    public static void main(String[] args) {
        final byte[] data = Gamut.GAMUT_DATA;
        System.out.println("public static final byte[] CHROMA_LIMITS_BY_L = new byte[]{");
        for (int li = 0; li < 256; li++) {
            int limit = 1000;
            for (int hu = 0; hu < 256; hu++) {
                limit = Math.min(data[li << 8 | hu] & 255, limit);
            }
            System.out.print((byte)limit + ", ");
            if((li & 15) == 15) System.out.println();
        }
        System.out.println("};");

        System.out.println("Total number of RGB555 colors that are in the Oklab Gamut: ");
        int count = 0;
        for (int r = 0; r < 32; r++) {
            int rr = r << 3 | r >>> 2;
            for (int g = 0; g < 32; g++) {
                int gg = g << 3 | g >>> 2;
                for (int b = 0; b < 32; b++) {
                    int bb = b << 3 | b >>> 2;
                    int rgba = rr << 24 | gg << 16 | bb << 8 | 255;
                    if(ColorTools.inGamut(ColorTools.fromRGBA8888(rgba))) count++;
                }
            }
        }
        System.out.println(count + "/" + (1 << 15));

        //Total number of RGB555 colors that are in the Oklab Gamut:
        //32729/32768

        System.out.println("Total number of 24-bit Oklab colors that are in the Oklab Gamut: ");
        count = 0;
        for (int c = 0; c < 0x1000000; c++) {
            if (ColorTools.inGamut(NumberUtils.intBitsToFloat(0xFE000000 | c)))count++;
        }
        System.out.println(count + "/" + (1 << 24));

        //Total number of 24-bit Oklab colors that are in the Oklab Gamut:
        //289041/16777216

        System.out.println("Total number of 18-bit Oklab colors that are in the Oklab Gamut: ");
        IntSet counted = new IntSet(0x1000000);
        for (int c = 0; c < 0x1000000; c++) {
            int color = (c & 0xFCFCFC) | (c >>> 6 & 0x030303) | 0xFE000000;
            if (ColorTools.inGamut(NumberUtils.intBitsToFloat(color))) counted.add(color);
        }
        System.out.println(counted.size + "/" + (1 << 18));

        //Total number of 18-bit Oklab colors that are in the Oklab Gamut:
        //4189/262144
    }
}
