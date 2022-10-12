package com.github.tommyettinger.colorful;

import com.github.tommyettinger.colorful.rgb.ColorTools;

public class HSLCorrectnessTest {
    /**
     * Cross-reference with <a href="https://en.wikipedia.org/wiki/HSL_and_HSV#Examples">Wikipedia - HSL and HSV</a>.
     * @param args
     */
    public static void main(String[] args) {
        int[] colors = {
                0xFFFFFFFF,
                0x808080FF,
                0x000000FF,
                0xFF0000FF,
                0xBFBF00FF,
                0x008000FF,
                0x80FFFFFF,
                0x8080FFFF,
                0xBF40BFFF,
                0xA0A424FF,
                0x411BEAFF,
                0x1EAC41FF,
                0xF0C80EFF,
                0xB430E5FF,
                0xED7651FF,
                0xFEF888FF,
                0x19CB97FF,
                0x362698FF,
                0x7E7EB8FF,
        };
        for (int c : colors) {
            float abgr = ColorTools.fromRGBA8888(c);
            float hsla = FloatColors.rgb2hsl(abgr);
            System.out.printf("0x%08X: %7.3f %4.3f %4.3f\n", c, ColorTools.red(hsla) * 360f, ColorTools.green(hsla), ColorTools.blue(hsla));
        }
    }
}
