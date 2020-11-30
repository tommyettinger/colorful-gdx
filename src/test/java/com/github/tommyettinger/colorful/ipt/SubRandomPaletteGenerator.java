package com.github.tommyettinger.colorful.ipt;

import com.badlogic.gdx.utils.IntArray;
import com.github.tommyettinger.anim8.PNG8;
import com.github.tommyettinger.colorful.internal.StringKit;

public class SubRandomPaletteGenerator {
    public static void main(String[] args) {
        IntArray rgba = new IntArray(64);
        rgba.add(0);
        rgba.add(0x0B080FFF);
        rgba.add(0x353336FF);
        rgba.add(0x555555FF);
        rgba.add(0x797577FF);
        rgba.add(0xAAAAAAFF);
        rgba.add(0xC8C8C8FF);
        rgba.add(0xE0E0E0FF);
        rgba.add(0xFAF7F0FF);
        int idx = 1;
        while (rgba.size < 64) {
            float color = gaussianColor(idx++, 0.5 * (1.0 - rgba.size * rgba.size * rgba.size * 0x1p-19));
            if(ColorTools.inGamut(color))
            {
                rgba.add(ColorTools.toRGBA8888(color));
            }
        }
        StringBuilder sb = new StringBuilder(12 * 64 + 2).append('{');
        for (int i = 0; i < 64; i++) {
            StringKit.appendHex(sb.append("0x"), rgba.get(i)).append(", ");
        }
        System.out.println(sb.append('}'));
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
                (float) (PNG8.probit(resY) % 0.5 + 0.5),
                (float) (PNG8.probit(resZ) % 0.5 + 0.5), 1f);
    }
    public static float gaussianColor(int index, double sat)
    {
        sat *= 0.5;
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
        return ColorTools.ipt((float) (PNG8.probit(resX) * 2.0 % 0.5 + 0.5),
                (float) (PNG8.probit(resY) % sat + 0.5),
                (float) (PNG8.probit(resZ) % sat + 0.5), 1f);
    }

}
