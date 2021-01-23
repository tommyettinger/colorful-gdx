package com.github.tommyettinger.colorful.oklab;

import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.IntArray;
import com.github.tommyettinger.anim8.OtherMath;
import com.github.tommyettinger.colorful.internal.StringKit;

/* Example output:
{
0x00000000, 0x010101FF, 0xFEFEFEFF, 0x777777FF, 0x555555FF, 0xAAAAAAFF, 0x333333FF, 0xE0E0E0FF,
0xC8C8C8FF, 0x4972F1FF, 0xCFFB6EFF, 0x2B1A8FFF, 0x64A84AFF, 0xB33AC1FF, 0x881765FF, 0xE46254FF,
0x1E0734FF, 0x5420F8FF, 0xE082E5FF, 0x00E747FF, 0x007126FF, 0xAE341FFF, 0xEEB32DFF, 0x25F7EFFF,
0x6F1E12FF, 0x1FACFBFF, 0x061A0AFF, 0xF843A2FF, 0x1552ADFF, 0x4C0E52FF, 0xF929FFFF, 0x3C0909FF,
0xA87719FF, 0x6709AAFF, 0x00490AFF, 0xCA0C67FF, 0x1CC6B5FF, 0x724BA2FF, 0x0A00DFFF, 0x9874C5FF,
0xFA91A1FF, 0x95D789FF, 0x008D71FF, 0x8642FFFF, 0xFDB2FFFF, 0x007DB1FF, 0x9D537DFF, 0x090066FF,
0x003B70FF, 0x805918FF, 0x0F0411FF, 0xCE9469FF, 0xEE1E35FF, 0x617B28FF, 0x53355BFF, 0x6C9C82FF,
0x44561FFF, 0x9892F9FF, 0xFAD19BFF, 0x8BFC26FF, 0x674328FF, 0x6D1640FF, 0x292024FF, 0xE16EA8FF,
0xA77374FF, 0x1E4839FF, 0x4B2C26FF, 0x8FBD33FF, 0x401733FF, 0xC94C2BFF, 0x7989AAFF, 0xF833D7FF,
0x437886FF, 0x4C3F6FFF, 0x9EA5FEFF, 0x8B3693FF, 0x71299AFF, 0xD9927EFF, 0x313026FF, 0xCB6F8EFF,
0x3BD382FF, 0x415D3BFF, 0x00AFCBFF, 0xD2B1B7FF, 0x7F1047FF, 0x388ADBFF, 0xE97EC7FF, 0xF8FEEBFF,
0x816993FF, 0x133585FF, 0xC1BE5BFF, 0x774BA4FF, 0x9294A2FF, 0x4715B5FF, 0xDD31B0FF, 0x3C6A2CFF,
0x5E465DFF, 0xA4D0F7FF, 0xD54672FF, 0xAAF49BFF, 0x56236CFF, 0xBA7E48FF, 0xB1FDE2FF, 0xDB7781FF,
0x260D3CFF, 0x4F70BFFF, 0xD19A87FF, 0x626CF2FF, 0x5CA5DCFF, 0x2A216BFF, 0x8524FEFF, 0xBB54E1FF,
0x1BCDEFFF, 0x885365FF, 0x49596CFF, 0x955299FF, 0x86824AFF, 0x102E36FF, 0x6F872BFF, 0x683018FF,
0xA6B1C4FF, 0xC22147FF, 0x82394FFF, 0x9B8BE0FF, 0xC8E0A7FF, 0x1F0D0BFF, 0x59588EFF, 0x44BA75FF,
0xC11FC9FF, 0x569B8AFF, 0x224BBEFF, 0x290F43FF, 0x724AF3FF, 0xDE63B2FF, 0x69DEB7FF, 0x451781FF,
0x81665AFF, 0x4B4448FF, 0x75BCCFFF, 0x923667FF, 0xBBA13EFF, 0xF9CFF2FF, 0x2869CEFF, 0xD7B989FF,
0x2F43E2FF, 0x7A1C28FF, 0x9E70ADFF, 0x162256FF, 0xB87CF1FF, 0x6C7E6BFF, 0x004162FF, 0x5F9E3AFF,
0x802BA9FF, 0x67A17AFF, 0x4D2B38FF, 0xD44587FF, 0x44CB89FF, 0x480A42FF, 0x485541FF, 0xA0D4CBFF,
0xB6604AFF, 0x7586F1FF, 0xFB84DEFF, 0x182024FF, 0x6E63FFFF, 0x147F91FF, 0x402E96FF, 0xB7A953FF,
0x9822A1FF, 0xA49AB2FF, 0x242A4BFF, 0xBC64C1FF, 0x6D6343FF, 0x22664DFF, 0xCCB7FFFF, 0x73DF24FF,
0x402114FF, 0x7BE88AFF, 0x58123CFF, 0x8268D2FF, 0x7DC6A0FF, 0xB44515FF, 0x1F65E9FF, 0x789EBCFF,
0x3220C8FF, 0x248A5AFF, 0xDC2AC5FF, 0x3A6A6DFF, 0x171195FF, 0x623964FF, 0xA768FAFF, 0xC03DA6FF,
0x541B71FF, 0xB47851FF, 0x122330FF, 0xBB7F93FF, 0x160D47FF, 0x876C2FFF, 0x2F542CFF, 0xD7989BFF,
0x1CB3F5FF, 0xDAE8BEFF, 0x00C6FFFF, 0x695D9DFF, 0x474A9CFF, 0xB9CA47FF, 0x9348D2FF, 0x82888FFF,
0x4C32C0FF, 0x6A7F43FF, 0x453746FF, 0xB4B6DEFF, 0xAB3654FF, 0x843081FF, 0xD57B40FF, 0x423905FF,
0x8BECF0FF, 0xC16361FF, 0x170824FF, 0x50D14DFF, 0x365997FF, 0x43B6B6FF, 0xE6C0A6FF, 0x6747CAFF,
0x4898C6FF, 0x2A29FDFF, 0x310858FF, 0x8031FCFF, 0xE451F3FF, 0x8B696FFF, 0x1F4473FF, 0x87497EFF,
0xB09681FF, 0xEB3F9AFF, 0x630DD2FF, 0x824827FF, 0xA7C7D0FF, 0xC5384AFF, 0x3D40F9FF, 0xB3F26BFF,
0x71333BFF, 0x7570E1FF, 0x5C7CA4FF, 0x2BAC55FF, 0xE5A362FF, 0x82A48DFF, 0x3838A3FF, 0xF3A4ABFF,
0x2F2D5AFF, 0x7625D2FF, 0xC65CA3FF, 0x16C2C5FF, 0x884F44FF, 0x4A4E4FFF, 0xA5597EFF, 0x9B8C19FF,
}
 */
public class RandomPaletteGenerator {
    private static final int limit = 256;
    private static final IntArray rgba = new IntArray(limit);
    private static final FloatArray labs = new FloatArray(limit);
    private static void add(int value){
        float oklab = ColorTools.fromRGBA8888(value);
//        float L = ColorTools.channelL(oklab),
//                A = OtherMath.barronSpline(ColorTools.channelA(oklab), 0.625f, 0.5f),
//                B = OtherMath.barronSpline(ColorTools.channelB(oklab), 0.625f, 0.5f);
//        final double limit = 0.011 - (labs.size * 0.00002);
//        for (int idx = 0; idx < labs.size; idx++) {
//            float o = labs.get(idx);
//            if(Vector3.dst2(L * 2, A, B, ColorTools.channelL(o) * 2, ColorTools.channelA(o), ColorTools.channelB(o)) < limit)
//                return;
//        }
        rgba.add(value);
        labs.add(oklab);
    }
    private static void add(float oklab){
        float L = ColorTools.channelL(oklab),
                A = ColorTools.channelA(oklab),
                B = ColorTools.channelB(oklab);
//                A = OtherMath.barronSpline(ColorTools.channelA(oklab), 0.75f, 0.5f),
//                B = OtherMath.barronSpline(ColorTools.channelB(oklab), 0.75f, 0.5f);
        if(!ColorTools.inGamut(L, A, B))
            return;
        oklab = ColorTools.oklab(L, A, B, 1f);
        final double limit = 0.0075 - (labs.size * 0.0001);
        for (int idx = 0; idx < labs.size; idx++) {
            float o = labs.get(idx);
            if(Vector3.dst2(L * 0.5f, A, B, ColorTools.channelL(o) * 0.5f, ColorTools.channelA(o), ColorTools.channelB(o)) < limit)
                return;
        }
        rgba.add(ColorTools.toRGBA8888(oklab));
        labs.add(oklab);
    }
    public static void main(String[] args) {
        rgba.add(0);
        add(0x010101FF);
        add(0xFEFEFEFF);
        add(0x777777FF);
        add(0x555555FF);
        add(0xAAAAAAFF);
        add(0x333333FF);
        add(0xE0E0E0FF);
        add(0xC8C8C8FF);
//        int idx = 1, initial = rgba.size;
        int idx = 1;
        RandomXS128 random = new RandomXS128(1L);
//        RandomXS128 random = new RandomXS128(0xB0BAFE77BA77L, 0xCAFEF00D15BADL);
        while (rgba.size < limit) {
//            add(ColorTools.randomColor(random));
            add(haltonColor(idx));
//            if(++idx % 10000 == 0)
//                System.out.println(idx + " tries, " + rgba.size + " placed");
            ++idx;
//            for (int i = initial; i < 32 && rgba.size < limit; i++) {
//                float color = gaussianColor(idx++, (1.0 - i * i * i * 0x1p-16));
//                if (ColorTools.inGamut(color)) {
//                    rgba.add(ColorTools.toRGBA8888(color));
//                }
//            }
        }
        System.out.println(idx);
        StringBuilder sb = new StringBuilder(12 * rgba.size + 35).append("{\n");
        for (int i = 0; i < rgba.size; i++) {
            StringKit.appendHex(sb.append("0x"), rgba.get(i)).append(", ");
            if(7 == (i & 7)) sb.append('\n');
        }
        System.out.println(sb.append('}'));
//
//        System.out.println();
//        for (int i = 0; i < labs.size; i++) {
//            float c = labs.get(i);
//            System.out.printf("I=%f, P=%f, T=%f, RGBA=%08X\n",
//                    ColorTools.channelL(c), ColorTools.channelA(c), ColorTools.channelB(c), ColorTools.toRGBA8888(c));
//        }
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
        return ColorTools.oklab((float) resX, (float) resY, (float) resZ, 1f);
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
        return ColorTools.oklab((float) resX,
                (float) (OtherMath.probit(resY) % 0.5 + 0.5),
                (float) (OtherMath.probit(resZ) % 0.5 + 0.5), 1f);
    }
    public static float gaussianColor(int index, double sat)
    {
//        sat *= 0.5;
        sat *= 2.5;
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
//        resY -= 0.5;
//        resZ -= 0.5;
        return ColorTools.oklab(
//                (float)resX,
//                (float)((resY - 0.5) % sat) + 0.5f,
//                (float)((resZ - 0.5) % sat) + 0.5f,
                (float) Math.pow(OtherMath.probit(resX) * 2.0 % 0.5 + 0.5, 0.9375),
//                (float)(resY * resY * resY * sat) + 0.5f,
//                (float)(resZ * resZ * resZ * sat) + 0.5f,
//                PaletteReducer.barronSpline((float)resY, 0.5f, 0.5f),
//                PaletteReducer.barronSpline((float)resZ, 0.5f, 0.5f),
//                (PaletteReducer.barronSpline((float)resY, 0.5f, 0.5f) - 0.5f) * (float) sat + 0.5f,
//                (PaletteReducer.barronSpline((float)resZ, 0.5f, 0.5f) - 0.5f) * (float) sat + 0.5f,
//                (float)resY,
//                (float)resZ,
//                PaletteReducer.barronSpline((float) resX, 1.25f, 0.45f),
//                (float) (PNG8.probit(resX) * 2.0 % 0.5 + 0.5),
                (float) (OtherMath.probit(resY) * sat % 0.5 + 0.5),
                (float) (OtherMath.probit(resZ) * sat % 0.5 + 0.5),
//                (float) (PNG8.probit(PaletteReducer.barronSpline((float)resY, 1.5f, 0.5f)) * sat + 0.5),
//                (float) (PNG8.probit(PaletteReducer.barronSpline((float)resZ, 1.5f, 0.5f)) * sat + 0.5),
                1f);
    }

}
