package com.github.tommyettinger.colorful.oklab;

import com.badlogic.gdx.utils.NumberUtils;
import com.github.tommyettinger.colorful.FourWheelRandom;
import com.github.tommyettinger.colorful.TrigTools;
import org.junit.Assert;
import org.junit.Test;

import static com.github.tommyettinger.colorful.oklab.ColorTools.*;

public class BasicChecks {
//    public static float limitToGamut(float L, float A, float B, float alpha) {
//        L = Math.min(Math.max(L, 0f), 1f);
//        A = Math.min(Math.max(A, 0f), 1f);
//        B = Math.min(Math.max(B, 0f), 1f);
//        alpha = Math.min(Math.max(alpha, 0f), 1f);
//        final float A2 = (A - 0.5f);
//        final float B2 = (B - 0.5f);
//        final float hue = TrigTools.atan2_(B2, A2);
//        final int idx = (int) (L * 255.999f) << 8 | (int)(256f * hue);
//        final float dist = getRawGamutValue(idx);
//        if(dist * dist * 0x1p-16f >= (A2 * A2 + B2 * B2))
//            return //(TimeUtils.millis() >>> 9 & 1L) == 0L ? Palette.LEAD :
//                    ColorTools.oklab(L, A, B, alpha);
//        return NumberUtils.intBitsToFloat(
//                (int) (alpha * 127.999f) << 25 |
//                        (int) (TrigTools.sin_(hue) * dist + 128f) << 16 |
//                        (int) (TrigTools.cos_(hue) * dist + 128f) << 8 |
//                        (int) (L * 255f));
//    }
//    public static float limitToGamut(final float packed) {
//        final int decoded = NumberUtils.floatToRawIntBits(packed);
//        final float A = ((decoded >>> 8 & 0xff) - 127.5f);
//        final float B = ((decoded >>> 16 & 0xff) - 127.5f);
//        final float hue = TrigTools.atan2_(B, A);
//        final int idx = (decoded & 0xff) << 8 | (int) (256f * hue);
//        final float dist = getRawGamutValue(idx);
//        if (dist * dist >= (A * A + B * B))
//            return packed;
//        return NumberUtils.intBitsToFloat(
//                (decoded & 0xFE0000FF) |
//                        (int) (TrigTools.cos_(hue) * dist + 128f) << 8 |
//                        (int) (TrigTools.sin_(hue) * dist + 128f) << 16);
//    }


    @Test
    public void testLimitToGamut(){
        Assert.assertEquals(NumberUtils.floatToRawIntBits(limitToGamut(0.5f, 0f, 0f, 1f)),
                NumberUtils.floatToRawIntBits(limitToGamut(ColorTools.oklab(0.5f, 0f, 0f, 1f))));
        Assert.assertEquals(NumberUtils.floatToRawIntBits(limitToGamut(0.5f, 1f, 0f, 1f)),
                NumberUtils.floatToRawIntBits(limitToGamut(ColorTools.oklab(0.5f, 1f, 0f, 1f))));
        Assert.assertEquals(NumberUtils.floatToRawIntBits(limitToGamut(0.5f, 1f, 1f, 1f)),
                NumberUtils.floatToRawIntBits(limitToGamut(ColorTools.oklab(0.5f, 1f, 1f, 1f))));
        Assert.assertEquals(NumberUtils.floatToRawIntBits(limitToGamut(0.5f, 0f, 1f, 1f)),
                NumberUtils.floatToRawIntBits(limitToGamut(ColorTools.oklab(0.5f, 0f, 1f, 1f))));
        Assert.assertEquals(NumberUtils.floatToRawIntBits(limitToGamut(0.5f, 0.126f, 0f, 1f)),
                NumberUtils.floatToRawIntBits(limitToGamut(ColorTools.oklab(0.5f, 0.126f, 0f, 1f))));
        Assert.assertEquals(NumberUtils.floatToRawIntBits(limitToGamut(0.6f, 0.75f, 0.75f, 1f)),
                NumberUtils.floatToRawIntBits(limitToGamut(ColorTools.oklab(0.6f, 0.75f, 0.75f, 1f))));
        Assert.assertEquals(NumberUtils.floatToRawIntBits(limitToGamut(0.61960787f, 0.60784316f, 0.3882353f, 1f)),
                NumberUtils.floatToRawIntBits(limitToGamut(ColorTools.oklab(0.61960787f, 0.60784316f, 0.3882353f, 1f))));
    }

    @Test
    public void testRandomColors() {
        FourWheelRandom random = new FourWheelRandom(1);
        for (int i = 0; i < 1000000; i++) {
            float l = random.nextInclusiveFloat();
            float a = random.nextFloat(0.2f, 0.8f);
            float b = random.nextFloat(0.2f, 0.8f);
            float f = ColorTools.oklab(l, a, b, 1f);
            if(ColorTools.inGamut(f) != ColorTools.inGamut(l, a, b)){
                System.out.printf("%.4f %.4f %.4f is having problems! ColorTools.inGamut(f)=%s, ColorTools.inGamut(l, a, b)=%s\n", l, a, b,
                        ColorTools.inGamut(f), ColorTools.inGamut(l, a, b));
                System.out.printf("%.4f %.4f %.4f is f.\n\n", ColorTools.channelL(f), ColorTools.channelA(f), ColorTools.channelB(f));
            }
        }
    }

    @Test
    public void testPalette() {
        for(String name : FullPalette.NAMES){
            float color = FullPalette.NAMED.get(name, FullPalette.GRAY);
            float L = ColorTools.channelL(color);
            float A = ColorTools.channelA(color);
            float B = ColorTools.channelB(color);
            if(!ColorTools.inGamut(color)){
                System.out.printf("%s is having problems! It has L=%f,A=%f,B=%f\n", name, L, A, B);
            }
            A = (A + 0.5f) % 1f;
            B = (B + 0.5f) % 1f;
            color = ColorTools.oklab(L, A, B, 1f);
            if(ColorTools.inGamut(color)){
                System.out.printf("%s's inverse should not be in gamut! It has L=%f,A=%f,B=%f\n", name, L, A, B);
            }
        }
    }
}
