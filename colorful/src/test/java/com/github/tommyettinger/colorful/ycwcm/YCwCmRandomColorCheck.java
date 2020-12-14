package com.github.tommyettinger.colorful.ycwcm;

import com.badlogic.gdx.math.RandomXS128;

public class YCwCmRandomColorCheck {
    public static void main(String[] args) {
        RandomXS128 random = new RandomXS128(0L, 1L);
        for (int i = 0; i < 100000; i++) {
            float color = ColorTools.randomColor(random);
            if (!ColorTools.inGamut(color)) {
                System.out.printf("Color with YCwCm values: Y=%f, Cw=%f, Cm=%f\n",
                        ColorTools.luma(color), ColorTools.chromaWarm(color), ColorTools.chromaMild(color));
                System.out.println("It's out of gamut!");
            }
        }
    }
}
