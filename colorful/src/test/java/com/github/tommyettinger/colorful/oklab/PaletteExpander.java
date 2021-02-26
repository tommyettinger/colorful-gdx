package com.github.tommyettinger.colorful.oklab;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.NumberUtils;
import com.github.tommyettinger.colorful.TrigTools;
import com.github.tommyettinger.colorful.internal.StringKit;

/*
"surin", based on azurestar
{
0x00000000, 0x000000FF, 0x141414FF, 0xFFFFFFFF, 0x878787FF, 0xCCCCCCFF, 0x4F4F4FFF, 0xEEEEEEFF,
0x282828FF, 0x999999FF, 0x757575FF, 0xDDDDDDFF, 0x3B3B3BFF, 0xBBBBBBFF, 0x626262FF, 0xAAAAAAFF,
0x9B9783FF, 0x514E3DFF, 0xE10788FF, 0xDC8EA1FF, 0xA55F72FF, 0x5E2637FF, 0xF7419BFF, 0x9E0C5BFF,
0x7C706BFF, 0x39302DFF, 0x943907FF, 0xAA755EFF, 0x794B37FF, 0x3A1706FF, 0xAA512AFF, 0x602400FF,
0xE09A8BFF, 0x884F44FF, 0xDC6D00FF, 0xFDAA7CFF, 0xE06719FF, 0x7C3603FF, 0xFF811DFF, 0xA15013FF,
0xECA992FF, 0x945D4AFF, 0xFF6B03FF, 0xFFBE9EFF, 0xEE763DFF, 0x913C07FF, 0xFF9664FF, 0xC15000FF,
0xC09C84FF, 0x6F523EFF, 0xFF3107FF, 0xFF9779FF, 0xCD633FFF, 0x7E2400FF, 0xFF6B4EFF, 0xB52800FF,
0xE9BB63FF, 0x916A13FF, 0xFF6B8EFF, 0xFFC8A5FF, 0xD78C59FF, 0x894A1BFF, 0xFE9C96FF, 0xC74F4EFF,
0xCAC0B2FF, 0x797165FF, 0xFF6F84FF, 0xFFC4C4FF, 0xDA878AFF, 0x8B464AFF, 0xFF9BA3FF, 0xCC475EFF,
0xEEBC98FF, 0x956C4DFF, 0xFF7C64FF, 0xFFCEBDFF, 0xDB9071FF, 0x8D4E33FF, 0xFFA68FFF, 0xC35E47FF,
0xE6CAB3FF, 0x917965FF, 0xF88D7EFF, 0xFEDBCCFF, 0xD79F8CFF, 0x8A5B4BFF, 0xFEB5A5FF, 0xBC6E60FF,
0xE7D7A1FF, 0x928355FF, 0xE79BBAFF, 0xFEE5DAFF, 0xD3AA99FF, 0x876556FF, 0xFCBBC3FF, 0xB27A81FF,
0xBCD68FFF, 0x6C8145FF, 0xF767FDFF, 0xE5DBCCFF, 0xAEA698FF, 0x696156FF, 0xEDA7E5FF, 0xA5689EFF,
0x6A945EFF, 0x294A1FFF, 0x9D00E1FF, 0xAC80D6FF, 0x7B53A0FF, 0x3E1B5BFF, 0xB335FFFF, 0x690B99FF,
0x7AAA6AFF, 0x365E28FF, 0xB725FFFF, 0xD08BFFFF, 0x995ACFFF, 0x561A81FF, 0xC261FFFF, 0x830CBDFF,
0x507A5EFF, 0x133722FF, 0x6410D3FF, 0x6E78BCFF, 0x464C88FF, 0x181747FF, 0x6D45DEFF, 0x3D008CFF,
0x739B83FF, 0x2F513EFF, 0x7B44FFFF, 0x849EE5FF, 0x586EADFF, 0x233266FF, 0x866EFFFF, 0x5203DCFF,
0x7FB98AFF, 0x386A43FF, 0xA162FFFF, 0xA5B9EAFF, 0x7688B5FF, 0x3A486DFF, 0xAB8BFFFF, 0x6D40D3FF,
0x90CCAFFF, 0x457961FF, 0x958AFDFF, 0xB6D2FDFF, 0x7B9AD8FF, 0x3D578BFF, 0xA6ACFFFF, 0x6264DDFF,
0x508B6BFF, 0x0C442CFF, 0x6A10FDFF, 0x6191B8FF, 0x386487FF, 0x002B46FF, 0x635EEBFF, 0x3622A1FF,
0x659FADFF, 0x21535FFF, 0x0080BAFF, 0x4FB3DBFF, 0x1B81A5FF, 0x103E53FF, 0x139AD5FF, 0x065A7DFF,
0x94BAD4FF, 0x4B6B81FF, 0x00AAC5FF, 0x5EDBFFFF, 0x25A5C9FF, 0x055D70FF, 0x26C3E6FF, 0x057D95FF,
0x9DC1EFFF, 0x527198FF, 0x1CB6B9FF, 0x6EE6FFFF, 0x10B3CFFF, 0x026674FF, 0x1FD1E0FF, 0x00898FFF,
0x707DD4FF, 0x32387EFF, 0x197A62FF, 0x6898D8FF, 0x3E6AA3FF, 0x072E5DFF, 0x278DA1FF, 0x114F5AFF,
0x869BEFFF, 0x425196FF, 0x179982FF, 0x5CC1E9FF, 0x2B8FB4FF, 0x114A5FFF, 0x00B3B0FF, 0x116D6BFF,
0xB898E7FF, 0x694D8FFF, 0x00AA3BFF, 0x92C7C5FF, 0x629492FF, 0x275250FF, 0x14C560FF, 0x127A3BFF,
0xBD87D6FF, 0x6C3F80FF, 0x4F961CFF, 0x9CBA97FF, 0x6D8868FF, 0x32482EFF, 0x55B31FFF, 0x386C13FF,
0xD3B0E3FF, 0x7F628CFF, 0x5FBA18FF, 0xA6E790FF, 0x75B060FF, 0x376923FF, 0x6DD819FF, 0x468C0DFF,
0x98738BFF, 0x4D3144FF, 0x65690FFF, 0x9D8E71FF, 0x6E6147FF, 0x342915FF, 0x827C31FF, 0x4A4406FF,
0xD47AA4FF, 0x7D3458FF, 0x8E7C13FF, 0xDC9B71FF, 0xA56B45FF, 0x5F300CFF, 0xB88C14FF, 0x6E530AFF,
0xDBA4AAFF, 0x86595EFF, 0xC98800FF, 0xF8B690FF, 0xC08562FF, 0x764527FF, 0xF79617FF, 0x9D600EFF,
0xEAB0CAFF, 0x926278FF, 0xBAA300FF, 0xFDD07DFF, 0xC7993CFF, 0x7B5800FF, 0xDCBA1CFF, 0x917911FF,
}

A different try, without a palette as a base, "enclave":
0x00000000, 0x000000FF, 0x141414FF, 0xFFFFFFFF, 0x878787FF, 0xCCCCCCFF, 0x4F4F4FFF, 0xEEEEEEFF,
0x282828FF, 0x999999FF, 0x757575FF, 0xDDDDDDFF, 0x3B3B3BFF, 0xBBBBBBFF, 0x626262FF, 0xAAAAAAFF,
0xCAA120FF, 0xE59700FF, 0xFDECB5FF, 0xD29F00FF, 0x5D4A07FF, 0xFFD100FF, 0xDB9C00FF, 0x775E09FF,
0x188BDFFF, 0x0084FFFF, 0x95CDFFFF, 0x0089F4FF, 0x083557FF, 0x00BCFFFF, 0x0088FFFF, 0x0D4976FF,
0xE8764BFF, 0xFF1D00FF, 0xFDD3C7FF, 0xFF6427FF, 0x8D0000FF, 0xFFBBA3FF, 0xFF4C00FF, 0xBB0000FF,
0x00BDC7FF, 0x1FB7BBFF, 0x86FEFEFF, 0x00C8D4FF, 0x0E5253FF, 0x00FFFFFF, 0x1FB7BBFF, 0x006A6EFF,
0xDF6CA0FF, 0xFF00AAFF, 0xFFCADCFF, 0xF755A3FF, 0x84004AFF, 0xFFB0D0FF, 0xFF2EA7FF, 0xB20061FF,
0x4CB868FF, 0x00C733FF, 0x7BFF9AFF, 0x34BC5BFF, 0x005B00FF, 0x51F87BFF, 0x00C04DFF, 0x007400FF,
0x9D6FDCFF, 0xB43EFFFF, 0xD8C0FEFF, 0xA364F5FF, 0x4C0087FF, 0xD0A8FCFF, 0xAD53FFFF, 0x6700B6FF,
0xA69100FF, 0xA49210FF, 0xF5D600FF, 0xAE9000FF, 0x453C0AFF, 0xEEC000FF, 0xB98C00FF, 0x59500DFF,
0x3C89EDFF, 0x007CFFFF, 0xA6CDFFFF, 0x2888FDFF, 0x002C8DFF, 0x89BDFFFF, 0x0084FFFF, 0x003ABEFF,
0xD77031FF, 0xE36800FF, 0xFDC4A1FF, 0xFF4400FF, 0x5D2A05FF, 0xFFAB7DFF, 0xE36800FF, 0x7D3707FF,
0x00BEDBFF, 0x00BBCFFF, 0xB8F7FFFF, 0x00C5E7FF, 0x02555FFF, 0x78EEFFFF, 0x00C8F9FF, 0x126C7AFF,
0xCA577BFF, 0xFF0063FF, 0xFFA6BDFF, 0xFF0072FF, 0x5D0A2DFF, 0xFE8CACFF, 0xFF006BFF, 0x810B3AFF,
0x27BF7EFF, 0x00D45AFF, 0x3BFFB4FF, 0x00C773FF, 0x006322FF, 0x00FF96FF, 0x00CD67FF, 0x136F44FF,
0x9D62C5FF, 0xB245E6FF, 0xEA9CFFFF, 0xA35CC9FF, 0x480065FF, 0xDF81FFFF, 0xAA52D7FF, 0x620087FF,
0x959D18FF, 0x9A9B17FF, 0xE7E500FF, 0x9A9B17FF, 0x41420EFF, 0xCFD200FF, 0x9A9B17FF, 0x565600FF,
0x6B95FFFF, 0x457FFFFF, 0xD0E0FFFF, 0x5790FFFF, 0x1030B2FF, 0xB8CEFFFF, 0x4C88FFFF, 0x1A3AEAFF,
0xD47920FF, 0xD9760EFF, 0xFFCA9EFF, 0xF16700FF, 0x5B310AFF, 0xFEB373FF, 0xFF4700FF, 0x78410DFF,
0x00AEDEFF, 0x16ABCDFF, 0xA0EBFFFF, 0x00B2EFFF, 0x004A59FF, 0x65E0FFFF, 0x00B5FFFF, 0x0D6074FF,
0xD35A6FFF, 0xFF1356FF, 0xFFAFB6FF, 0xDF5069FF, 0x70001FFF, 0xFF6D8FFF, 0xF13B62FF, 0x970029FF,
0x00AA7CFF, 0x13A67BFF, 0x00FFAEFF, 0x13A67BFF, 0x0D4233FF, 0x00E2ADFF, 0x13A67BFF, 0x135942FF,
0xB05FB9FF, 0xD710EDFF, 0xFF92FFFF, 0xBA53C7FF, 0x580064FF, 0xFF70FFFF, 0xC83FD5FF, 0x790085FF,
0x769726FF, 0x7D9518FF, 0xB4E200FF, 0x759A00FF, 0x313B00FF, 0x99D400FF, 0x6C9F00FF, 0x435000FF,
0x8194FFFF, 0x8892FFFF, 0xDCE2FFFF, 0x7D8CFFFF, 0x332DB1FF, 0xC5D0FFFF, 0x7484FFFF, 0x3E37E9FF,
0xE0932BFF, 0xFF6E00FF, 0xFFE7C7FF, 0xF88800FF, 0x694201FF, 0xFFD095FF, 0xFF7D00FF, 0x875404FF,
0x00AEEDFF, 0x16ABE7FF, 0xBBEAFDFF, 0x00B1FFFF, 0x0E4B64FF, 0x8EDDFEFF, 0x16ABE7FF, 0x006380FF,
0xFA7773FF, 0xFF736DFF, 0xFEE0DDFF, 0xFF5D5FFF, 0x9E000DFF, 0xFEC8C6FF, 0xFF1440FF, 0xDB0000FF,
0x00AD95FF, 0x15A88FFF, 0x00FFDCFF, 0x00B997FF, 0x0C453AFF, 0x00FDCBFF, 0x15A88FFF, 0x0B5C4EFF,
0xCD6FBEFF, 0xFF00F6FF, 0xFFC4F2FF, 0xEB4ED6FF, 0x7B0070FF, 0xFFA6EBFF, 0xFF1DE4FF, 0xA70092FF,
0x62A13CFF, 0x40AB00FF, 0x9FEA59FF, 0x61A30AFF, 0x134700FF, 0x7FDD07FF, 0x52A700FF, 0x115E00FF,
0x8883F3FF, 0x8D7CFFFF, 0xD1D3FDFF, 0x8C76FFFF, 0x3F15A3FF, 0xBDBFFFFF, 0x8B61FFFF, 0x5200ECFF,
 */

public class PaletteExpander {
    private static final int[] BASE_PALETTE = new int[]{
            //0x15111BFF, 0xFFEDD4FF,
            0x6E6550FF, 0x372B26FF, 0xC37C6BFF, 0xDD997EFF, 0x9A765EFF, 0xE1AD56FF,
            0xC6B5A5FF, 0xE9B58CFF, 0xEFCBB3FF, 0xF7DFAAFF, 0xBBD18AFF, 0x355525FF, 0x557A41FF, 0x112D19FF,
            0x45644FFF, 0x62966AFF, 0x86BB9AFF, 0x15452DFF, 0x396A76FF, 0x86A2B7FF, 0x92B3DBFF, 0x3D4186FF,
            0x6672BFFF, 0x9A76BFFF, 0x925EA2FF, 0xC7A2CFFF, 0x553549FF, 0xA24D72FF, 0xC38E92FF, 0xE3A6BBFF,
    };
    private static final int limit = 256;
    private static float minDistance = Float.MAX_VALUE;
    private static final IntArray rgba = new IntArray(limit);
    private static final FloatArray labs = new FloatArray(limit);
    public static void main(String[] args) {
        rgba.add(0);

        rgba.add(0x000000FF);
        rgba.add(0x141414FF);
        rgba.add(0xFFFFFFFF);
        rgba.add(0x878787FF);
        rgba.add(0xCCCCCCFF);
        rgba.add(0x4F4F4FFF);
        rgba.add(0xEEEEEEFF);
        rgba.add(0x282828FF);
        rgba.add(0x999999FF);
        rgba.add(0x757575FF);
        rgba.add(0xDDDDDDFF);
        rgba.add(0x3B3B3BFF);
        rgba.add(0xBBBBBBFF);
        rgba.add(0x626262FF);
        rgba.add(0xAAAAAAFF);

//        rgba.add(0x000000FF);
//        rgba.add(0x141414FF);
//        rgba.add(0x282828FF);
//        rgba.add(0x3B3B3BFF);
//        rgba.add(0x4F4F4FFF);
//        rgba.add(0x626262FF);
//        rgba.add(0x757575FF);
//        rgba.add(0x878787FF);
//        rgba.add(0x999999FF);
//        rgba.add(0xAAAAAAFF);
//        rgba.add(0xBBBBBBFF);
//        rgba.add(0xCCCCCCFF);
//        rgba.add(0xDDDDDDFF);
//        rgba.add(0xEEEEEEFF);
//        rgba.add(0xFFFFFFFF);

        for (int i = 0; i < 30; i++) {
            int light = ((i ^ 0xACED) * 0x9B & 0x1F) + 0xA0;
            float L = light / 255f;
            float hue = (i * 2.718281828459045f + MathUtils.HALF_PI) % MathUtils.PI2;
            float A = TrigTools.cos(hue) * 0.075f + 0.5f;
            float B = TrigTools.sin(hue) * 0.075f + 0.5f;
            rgba.add(ColorTools.toRGBA8888(ColorTools.limitToGamut(L, A, B)));
            int hi = (int)(hue * 256f);
            int gamut = ColorTools.getRawGamutValue(light << 8 | hi);
            float gA = TrigTools.cos(hue) * gamut * 0x1p-8f + 0.5f;
            float gB = TrigTools.sin(hue) * gamut * 0x1p-8f + 0.5f;
            rgba.add(ColorTools.toRGBA8888(ColorTools.limitToGamut(L, gA, gB)));
            float edit = TrigTools.sin(hi) * 0.08f;
            float gA1 = MathUtils.lerp(A, gA, 0.3125f + edit);
            float gB1 = MathUtils.lerp(B, gB, 0.3125f + edit);
            rgba.add(ColorTools.toRGBA8888(ColorTools.limitToGamut(L + 0.2f, gA1, gB1)));
            rgba.add(ColorTools.toRGBA8888(ColorTools.limitToGamut(L, gA1, gB1)));
            rgba.add(ColorTools.toRGBA8888(ColorTools.limitToGamut(L - 0.3f, gA1, gB1)));
            float gA2 = MathUtils.lerp(A, gA, 0.625f + edit);
            float gB2 = MathUtils.lerp(B, gB, 0.625f + edit);
            rgba.add(ColorTools.toRGBA8888(ColorTools.limitToGamut(L + 0.15f, gA2, gB2)));
            rgba.add(ColorTools.toRGBA8888(ColorTools.limitToGamut(L, gA2, gB2)));
            rgba.add(ColorTools.toRGBA8888(ColorTools.limitToGamut(L - 0.225f, gA2, gB2)));
        }
//        for (int i = 0; i < BASE_PALETTE.length; i++) {
//            float lab = ColorTools.fromRGBA8888(BASE_PALETTE[i]);
//            int dec = NumberUtils.floatToRawIntBits(lab);
//            int light = dec & 0xFF;
//            float L = MathUtils.lerp(light / 255f, 0.666f, 0.5f);
//            float A = ColorTools.channelA(lab);
//            float B = ColorTools.channelB(lab);
//            rgba.add(ColorTools.toRGBA8888(ColorTools.limitToGamut(L + 0.1f, A, B)));
//            rgba.add(ColorTools.toRGBA8888(ColorTools.limitToGamut(L - 0.15f, A, B)));
//            float hue = TrigTools.atan2_((dec >>> 8 & 0xFF) - 127.5f, (dec >>> 16 & 0xFF) - 127.5f);
//            int hi = (int)(hue * 256f);
//            int gamut = ColorTools.getRawGamutValue(light << 8 | hi);
//            float gA = TrigTools.cos_(hue) * gamut * 0x1p-8f + 0.5f;
//            float gB = TrigTools.sin_(hue) * gamut * 0x1p-8f + 0.5f;
//            rgba.add(ColorTools.toRGBA8888(ColorTools.limitToGamut(L, gA, gB)));
//            float edit = TrigTools.sin(hi) * 0.1f;
//            float gA1 = MathUtils.lerp(A, gA, 0.3125f + edit);
//            float gB1 = MathUtils.lerp(B, gB, 0.3125f + edit);
//            rgba.add(ColorTools.toRGBA8888(ColorTools.limitToGamut(L + 0.15f, gA1, gB1)));
//            rgba.add(ColorTools.toRGBA8888(ColorTools.limitToGamut(L, gA1, gB1)));
//            rgba.add(ColorTools.toRGBA8888(ColorTools.limitToGamut(L - 0.225f, gA1, gB1)));
//            float gA2 = MathUtils.lerp(A, gA, 0.625f + edit);
//            float gB2 = MathUtils.lerp(B, gB, 0.625f + edit);
//            rgba.add(ColorTools.toRGBA8888(ColorTools.limitToGamut(L + 0.075f, gA2, gB2)));
//            rgba.add(ColorTools.toRGBA8888(ColorTools.limitToGamut(L - 0.125f, gA2, gB2)));
//        }

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
}
