package com.github.tommyettinger.colorful.ipt;

import com.badlogic.gdx.math.RandomXS128;

public class IPTRandomColorCheck {
    public static void main(String[] args) {
        RandomXS128 random = new RandomXS128(0L, 1L);
        for (int i = 0; i < 100000; i++) {
            float color = ColorTools.randomColor(random);
            if (!ColorTools.inGamut(color)) {
                System.out.printf("Color with IPT values: I=%f, P=%f, T=%f\n",
                        ColorTools.intensity(color), ColorTools.protan(color), ColorTools.tritan(color));
                System.out.println("It's out of gamut!");
            }
        }
    }
}
