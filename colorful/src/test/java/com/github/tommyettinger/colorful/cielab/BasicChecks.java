package com.github.tommyettinger.colorful.cielab;

import com.badlogic.gdx.utils.NumberUtils;
import org.junit.Assert;
import org.junit.Test;

public class BasicChecks {
    @Test
    public void testLimitToGamut(){
        Assert.assertEquals(NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(0.5f, 0f, 0f, 1f)),
                NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(ColorTools.cielab(0.5f, 0f, 0f, 1f))));
        Assert.assertEquals(NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(0.5f, 1f, 0f, 1f)),
                NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(ColorTools.cielab(0.5f, 1f, 0f, 1f))));
        Assert.assertEquals(NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(0.5f, 1f, 1f, 1f)),
                NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(ColorTools.cielab(0.5f, 1f, 1f, 1f))));
        Assert.assertEquals(NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(0.5f, 0f, 1f, 1f)),
                NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(ColorTools.cielab(0.5f, 0f, 1f, 1f))));
        Assert.assertEquals(NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(0.5f, 0.126f, 0f, 1f)),
                NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(ColorTools.cielab(0.5f, 0.126f, 0f, 1f))));
    }
}
