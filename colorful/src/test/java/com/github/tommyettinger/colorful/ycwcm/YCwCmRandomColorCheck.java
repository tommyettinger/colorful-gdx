package com.github.tommyettinger.colorful.ycwcm;

import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.NumberUtils;
import com.github.tommyettinger.colorful.FourWheelRandom;

public class YCwCmRandomColorCheck {

    /**
     * Doesn't work, unclear why.
     * @param packed
     * @return
     */
    public static boolean inGamut(final float packed)
    {
        final int decoded = NumberUtils.floatToRawIntBits(packed), yi = (decoded & 0xff),
                cwi = ((decoded >>> 7 & 0x1fe) - 0xff),
                cmi = (((decoded >>> 15 & 0x1fe) - 0xff) / 2);
        final int r = (yi - cmi << 3) + (cwi * 5);
        if(r < 0 || r > 2047) return false;
        final int g = (yi + cmi << 3) - (cwi * 3);
        if(g < 0 || g > 2047) return false;
        final int b = (yi - cmi << 3) - (cwi * 3);
        return (b >= 0) && (b <= 2047);
    }

    public static float howOutOfGamut(final float packed)
    {
        final int decoded = NumberUtils.floatToRawIntBits(packed);
        final float yi = (decoded & 0xff),
                cwi = ((decoded >>> 7 & 0x1fe) - 0xff),
                cmi = (((decoded >>> 15 & 0x1fe) - 0xff) * 0.5f);
        final float r = yi + (cwi * 0.625f) - cmi;
        final float g = yi - (cwi * 0.375f) + cmi;
        final float b = yi - (cwi * 0.375f) - cmi;
        final float high = Math.max(r, Math.max(g, b));
        final float low  = Math.min(r, Math.min(g, b));
        return high >= 256 || low <= -1 ? (high - 256 >= -low ? high : low) : 128;
    }

//    public static int howOutOfGamut(final float packed)
//    {
//        final int decoded = NumberUtils.floatToRawIntBits(packed), yi = (decoded & 0xff),
//                cwi = ((decoded >>> 7 & 0x1fe) - 0xff),
//                cmi = (((decoded >>> 15 & 0x1fe) - 0xff) / 2);
//        final int r = yi + (cwi * 5 / 8) - cmi;
//        final int g = yi - (cwi * 3 / 8) + cmi;
//        final int b = yi - (cwi * 3 / 8) - cmi;
//        final int high = Math.max(r, Math.max(g, b));
//        final int low  = Math.min(r, Math.min(g, b));
//        return high > 255 || low < 0 ? (high - 255 > -low ? high : low) : 128;
//    }
//
    public static void main(String[] args) {
        FourWheelRandom random = new FourWheelRandom(1L);
//        RandomXS128 random = new RandomXS128(1L);
        for (int i = 0; i < 100000000; i++) {
            float color = ColorTools.randomColor(random);
            float out = howOutOfGamut(color);
            if (out != 128) {
                System.out.printf("Color with YCwCm values: Y=%f, Cw=%f, Cm=%f\n",
                        ColorTools.luma(color), ColorTools.chromaWarm(color), ColorTools.chromaMild(color));
                System.out.println("It's out of gamut! By " + out);
            }
        }
    }
}
