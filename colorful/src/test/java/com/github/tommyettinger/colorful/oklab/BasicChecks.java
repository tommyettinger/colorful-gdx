package com.github.tommyettinger.colorful.oklab;

import com.badlogic.gdx.utils.NumberUtils;
import com.github.tommyettinger.colorful.FourWheelRandom;
import org.junit.Assert;
import org.junit.Test;

public class BasicChecks {
    @Test
    public void testLimitToGamut(){
        Assert.assertEquals(NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(0.5f, 0f, 0f, 1f)),
                NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(ColorTools.oklab(0.5f, 0f, 0f, 1f))));
        Assert.assertEquals(NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(0.5f, 1f, 0f, 1f)),
                NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(ColorTools.oklab(0.5f, 1f, 0f, 1f))));
        Assert.assertEquals(NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(0.5f, 1f, 1f, 1f)),
                NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(ColorTools.oklab(0.5f, 1f, 1f, 1f))));
        Assert.assertEquals(NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(0.5f, 0f, 1f, 1f)),
                NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(ColorTools.oklab(0.5f, 0f, 1f, 1f))));
        Assert.assertEquals(NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(0.5f, 0.126f, 0f, 1f)),
                NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(ColorTools.oklab(0.5f, 0.126f, 0f, 1f))));
        Assert.assertEquals(NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(0.61960787f, 0.60784316f, 0.3882353f, 1f)),
                NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(ColorTools.oklab(0.61960787f, 0.60784316f, 0.3882353f, 1f))));
    }

    @Test
    public void testRandomColors() {
        FourWheelRandom random = new FourWheelRandom(1);
        for (int i = 0; i < 1000000; i++) {
            float l = random.nextInclusiveFloat();
            float a = random.nextFloat(0.4f, 0.6f);
            float b = random.nextFloat(0.4f, 0.6f);
            float f = ColorTools.oklab(l, a, b, 1f);
            if(ColorTools.inGamut(f) != ColorTools.inGamut(l, a, b)){
                System.out.printf("%.4f %.4f %.4f is having problems! ColorTools.inGamut(f)=%s, ColorTools.inGamut(l, a, b)=%s\n", l, a, b,
                        ColorTools.inGamut(f), ColorTools.inGamut(l, a, b));
                System.out.printf("%.4f %.4f %.4f is f.\n\n", ColorTools.channelL(f), ColorTools.channelA(f), ColorTools.channelB(f));
            }
        }
    }
}
