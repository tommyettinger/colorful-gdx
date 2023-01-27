/*
 * Copyright (c) 2023 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.tommyettinger.colorful.oklab;

import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.NumberUtils;
import com.github.tommyettinger.colorful.FourWheelRandom;
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
        float params4 = limitToGamut(0.6196f, 0.6078f, 0.3882f, 1f);
        System.out.printf("params4: L %f  A %f  B %f  H %f  S %f\n", ColorTools.channelL(params4), ColorTools.channelA(params4), ColorTools.channelB(params4), ColorTools.oklabHue(params4), ColorTools.oklabSaturation(params4));
        float params1 = limitToGamut(ColorTools.oklab(0.6196f, 0.6078f, 0.3882f, 1f));
        System.out.printf("params1: L %f  A %f  B %f  H %f  S %f\n", ColorTools.channelL(params1), ColorTools.channelA(params1), ColorTools.channelB(params1), ColorTools.oklabHue(params1), ColorTools.oklabSaturation(params1));
        Assert.assertEquals(NumberUtils.floatToRawIntBits(params4),
                NumberUtils.floatToRawIntBits(params1));
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
        FloatArray hues = new FloatArray(512);
        for(String name : FullPalette.NAMES){
            float color = FullPalette.NAMED.get(name, FullPalette.GRAY);
            float L = (ColorTools.channelL(color));
            float A = ColorTools.channelA(color);
            float B = ColorTools.channelB(color);
            if(!inGamut(L, A, B)){
                System.out.printf("%s is having problems! It has L=%a,A=%a,B=%a\n", name, L, A, B);
                hues.add(oklabHue(color));
            }
//            if(inGamut(L, A, B)){
//                System.out.printf("%s is doing fine.    It has L=%f,A=%f,B=%f\n", name, L, A, B);
//            }
            A = (A + 0.5f) % 1f;
            B = (B + 0.5f) % 1f;
            if(inGamut(L, A, B)){
                System.out.printf("%s's inverse should not be in gamut!!!!! It has L=%f,A=%f,B=%f\n", name, L, A, B);
            }
        }
        hues.sort();
        for (int i = 0; i < 10 && i < hues.size; i++) {
            System.out.println(hues.get(i));
        }
        System.out.println("...");
        for (int i = hues.size - 10; i >= 0 && i < hues.size; i++) {
            System.out.println(hues.get(i));
        }
    }
    public static double reverseLight(double L) {
        L = Math.sqrt(L * 0x0.ffp0); // 255.0/256.0
        final double shape = 1.52, turning = 0.963;
        final double d = turning - L;
        double r;
        if(d < 0)
            r = ((1.0 - turning) * (L - 1.0)) / (1.0 - (L + shape * d)) + 1.0;
        else
            r = (turning * L) / (1e-50 + (L + shape * d));
        return r;
    }

    /**
     * Returns true if the given Oklab values are valid to convert losslessly back to RGBA.
     * @param L lightness channel, as a double from 0 to 1
     * @param A green-to-red chromatic channel, as a double from 0 to 1
     * @param B blue-to-yellow chromatic channel, as a double from 0 to 1
     * @return true if the given Oklab channels can be converted back and forth to RGBA
     */
    public static boolean inGamut(double L, double A, double B)
    {
        //reverseLight() for double
        L = reverseLight(L);
        //forwardLight() for double
//        L = (L - 1.00457) / (1.0 - L * 0.4285714) + 1.00457;
        A -= 0x1.fdfdfep-2;
        B -= 0x1.fdfdfep-2;

        double l = (L + +0.3963377774 * A + +0.2158037573 * B);
        l *= l * l;
        double m = (L + -0.1055613458 * A + -0.0638541728 * B);
        m *= m * m;
        double s = (L + -0.0894841775 * A + -1.2914855480 * B);
        s *= s * s;

        final double r = +4.0767245293 * l - 3.3072168827 * m + 0.2307590544 * s;
        if(r < 0.0 || r > 1.0) return false;
        final double g = -1.2681437731 * l + 2.6093323231 * m - 0.3411344290 * s;
        if(g < 0.0 || g > 1.0) return false;
        final double b = -0.0041119885 * l - 0.7034763098 * m + 1.7068625689 * s;
        return (b >= 0.0 && b <= 1.0);

//        final double r = +4.0767245293 * l - 3.3072168827 * m + 0.2307590544 * s;
//        if(r < -0x1p-8 || r > 0x101p-8) return false;
//        final double g = -1.2681437731 * l + 2.6093323231 * m - 0.3411344290 * s;
//        if(g < -0x1p-8 || g > 0x101p-8) return false;
//        final double b = -0.0041119885 * l - 0.7034763098 * m + 1.7068625689 * s;
//        return (b >= -0x1p-8 && b <= 0x101p-8);
    }

    @Test
    public void testBlues() {
        for (float f = 0.70934484f; f < 0.74934484f; f += 0.001f) {
            System.out.printf("hue %1.8f : limit %1.8f\n", f, chromaLimit(f, 0.2627451f));
        }
    }

}
