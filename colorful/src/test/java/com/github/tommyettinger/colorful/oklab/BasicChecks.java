package com.github.tommyettinger.colorful.oklab;

import com.badlogic.gdx.utils.NumberUtils;
import org.junit.Assert;
import org.junit.Test;

public class BasicChecks {
    @Test
    public void testLimitToGamut(){
        System.out.printf("%08X\n", NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(0.5f, 0f, 0f, 1f)));
        System.out.printf("%08X\n", NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(ColorTools.oklab(0.5f, 0f, 0f, 1f))));
        System.out.println();
        System.out.printf("%08X\n", NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(0.5f, 1f, 0f, 1f)));
        System.out.printf("%08X\n", NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(ColorTools.oklab(0.5f, 1f, 0f, 1f))));
        System.out.println();
        System.out.printf("%08X\n", NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(0.5f, 1f, 1f, 1f)));
        System.out.printf("%08X\n", NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(ColorTools.oklab(0.5f, 1f, 1f, 1f))));
        System.out.println();
        System.out.printf("%08X\n", NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(0.5f, 0f, 1f, 1f)));
        System.out.printf("%08X\n", NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(ColorTools.oklab(0.5f, 0f, 1f, 1f))));
    }
}
