package com.github.tommyettinger.colorful;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.NumberUtils;

public class ColorBitConversionTest {
    public static int floatToIntColor(float value) {
        int intBits = Float.floatToRawIntBits(value);
        intBits |= (intBits >>> 31) << 24;
        return intBits;
    }

    public static int floatToIntColor2(float value) {
        int intBits = Float.floatToRawIntBits(value);
        intBits |= MathUtils.round((intBits >>> 24) * 255f / 254f) << 24;
        return intBits;
    }

    public static void main(String[] args) {
        Color c = new Color();
        for (int i = 0; i < 256; i++) {
            c.set(i);
            float packed = c.toFloatBits();
            int unpacked = floatToIntColor(packed);
            float repacked = NumberUtils.intToFloatColor(unpacked);
            int unpacked2 = floatToIntColor2(packed);
            float repacked2 = NumberUtils.intToFloatColor(unpacked2);
            int unpackedG = NumberUtils.floatToIntColor(packed);
            float repackedG = NumberUtils.intToFloatColor(unpackedG);
            int alpha = (unpacked >> 24) & 0xFF;
            int alpha2 = (unpacked2 >> 24) & 0xFF;
            int alphaG = (unpackedG >> 24) & 0xFF;
            System.out.printf("index=% 3d => %A, GDX=%03d => %A, int-only=%03d => %A, round=%03d => %A\n", i, packed, alphaG, repackedG, alpha, repacked, alpha2, repacked2);
        }
    }
}
