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
No spline:
{
0x00000000, 0x010101FF, 0xFEFEFEFF, 0x777777FF, 0x555555FF, 0xAAAAAAFF, 0x333333FF, 0xE0E0E0FF,
0xC8C8C8FF, 0x92072EFF, 0xBA81E2FF, 0x1B2076FF, 0xCEFE27FF, 0xAE54FCFF, 0x576A5EFF, 0x0A5D9BFF,
0x5CAB26FF, 0x538E5DFF, 0x461425FF, 0x24EEA3FF, 0xAB4633FF, 0x0A1420FF, 0x55358FFF, 0xE7D05EFF,
0xC210D3FF, 0xAB8071FF, 0x18174BFF, 0xE060B7FF, 0xB99CFEFF, 0xFD4A3FFF, 0x7947D2FF, 0xA1D363FF,
0x598BB6FF, 0x581F61FF, 0x7D26B0FF, 0xB96079FF, 0x2A5F34FF, 0xC62765FF, 0x81F981FF, 0xD15D19FF,
0xFA757FFF, 0x19A4EDFF, 0x2E4F6BFF, 0x4966A2FF, 0x3D31CDFF, 0x31D2E8FF, 0x9A3D74FF, 0x5972F5FF,
0x411AA7FF, 0xEBA239FF, 0xF908DBFF, 0xEF9FA0FF, 0xFEA1E0FF, 0x627D15FF, 0xE18823FF, 0xEFBFFDFF,
0xDD303FFF, 0x280C29FF, 0x69BA94FF, 0x923824FF, 0x7E975DFF, 0x4C4220FF, 0x120B0CFF, 0x132A47FF,
0x6EC03BFF, 0x151F06FF, 0xF073F4FF, 0x8965AFFF, 0x053685FF, 0xD5348BFF, 0x3C63ECFF, 0x682825FF,
0x8D2863FF, 0x4CB9F3FF, 0x2842F5FF, 0x7283F3FF, 0xBE8C97FF, 0x7C6311FF, 0x74F7E1FF, 0x080107FF,
0x6DA1A5FF, 0x232D0EFF, 0x089D06FF, 0x1E83A7FF, 0x603657FF, 0xDCF7AEFF, 0x4CEC3AFF, 0x9A10FDFF,
0x9F6A3BFF, 0x361209FF, 0x692BEBFF, 0x5D0E36FF, 0xF64BFAFF, 0x297538FF, 0xA843A9FF, 0x380652FF,
0x0B4624FF, 0xE64980FF, 0x704995FF, 0x9052E5FF, 0xB8A838FF, 0xC8BB8DFF, 0x674A0CFF, 0xAA70BDFF,
0x1B4450FF, 0x0EA984FF, 0xC87B1CFF, 0xB8B2DDFF, 0x27D596FF, 0x879ED8FF, 0xDC89BDFF, 0x12550DFF,
0x0C0476FF, 0x258063FF, 0x65E2DAFF, 0xA45571FF, 0x270B05FF, 0x112AC8FF, 0xAE0D40FF, 0xA31BAAFF,
0x19D931FF, 0x7D0B6AFF, 0x987F1AFF, 0x0B034DFF, 0x480E5FFF, 0xFEDF6EFF, 0xA69A1BFF, 0xD346BAFF,
0x055ECCFF, 0xC3FDFAFF, 0x1949A3FF, 0x06021BFF, 0xC463FDFF, 0xF4B623FF, 0x200CADFF, 0x3A1A83FF,
0x6D03ADFF, 0x815795FF, 0xF3CFA5FF, 0xC0E110FF, 0x0C26FEFF, 0x4D2301FF, 0x7F5258FF, 0xD00CFCFF,
0xAFBE05FF, 0xFE6518FF, 0xFEB591FF, 0xDC7065FF, 0xDE957AFF, 0xFF87E8FF, 0x7E3010FF, 0xD21226FF,
0xA6DEF6FF, 0x1BBB6DFF, 0x60FD1EFF, 0xFEFC65FF, 0x4B49BBFF, 0x12355CFF, 0x650BD9FF, 0xFF32A5FF,
0x5E5D22FF, 0xA4DC9DFF, 0x27685AFF, 0x3D3667FF, 0xFE577EFF, 0xA90274FF, 0x1B2427FF, 0x098EF4FF,
0x4C4C85FF, 0x089998FF, 0x958BB9FF, 0x721546FF, 0x443701FF, 0x65098AFF, 0x753D4CFF, 0x093B0FFF,
0x99C079FF, 0x9AA171FF, 0x09B8C7FF, 0xC4039EFF, 0x99C8FBFF, 0xFF1D42FF, 0x105346FF, 0x0AC217FF,
0xFB67BBFF, 0xBCEAA5FF, 0x060630FF, 0x8F7A98FF, 0x6D9522FF, 0x390D2EFF, 0x16830AFF, 0x87C2B8FF,
0x83645FFF, 0x0A7AD8FF, 0x1B0220FF, 0x524843FF, 0x050A02FF, 0x8F08D8FF, 0x870B8AFF, 0x087583FF,
0xC44A13FF, 0x4E46FDFF, 0xB355A8FF, 0x091C2FFF, 0x690B0AFF, 0x898264FF, 0x595A7BFF, 0x4B3240FF,
0x274185FF, 0x7BD5A5FF, 0x051601FF, 0xFEFDC0FF, 0x914B14FF, 0x372246FF, 0x09CAC5FF, 0x8F69DEFF,
0x099037FF, 0x576FB4FF, 0x597184FF, 0x09A64FFF, 0x4D10FDFF, 0xFDE7FEFF, 0xA6FF9AFF, 0x5107C5FF,
0xD85C5AFF, 0x266B0FFF, 0x8A8DFCFF, 0xD73DFCFF, 0x6C51FDFF, 0x713A84FF, 0xB81E12FF, 0xC86990FF,
0x78918EFF, 0xF90873FF, 0xFF849BFF, 0xCFEC50FF, 0xD91BB9FF, 0x56DC5FFF, 0x73794DFF, 0xFC8A3AFF,
0xFFA169FF, 0x26A1BFFF, 0xB643DAFF, 0x7BBFFAFF, 0x3B1F0BFF, 0xAA0FD0FF, 0x5656C5FF, 0x870802FF,
0x3F2958FF, 0x85E966FF, 0xD4B6FCFF, 0xBF8B58FF, 0xB06E56FF, 0xA35A0BFF, 0x0EFDB6FF, 0x26C87EFF,
}

0.75 spline:
{
0x00000000, 0x010101FF, 0xFEFEFEFF, 0x777777FF, 0x555555FF, 0xAAAAAAFF, 0x333333FF, 0xE0E0E0FF,
0xC8C8C8FF, 0x4972F1FF, 0x822638FF, 0xB189D3FF, 0x192767FF, 0xCFFB6EFF, 0xA464E3FF, 0x556A62FF,
0x64A84AFF, 0x6C1397FF, 0x3F1B29FF, 0x56E8AFFF, 0x4E1050FF, 0xB06728FF, 0x3F4511FF, 0x995245FF,
0xC2D22BFF, 0x0B151EFF, 0x4E3E83FF, 0xB33AC1FF, 0x9F847DFF, 0x161B41FF, 0xA4A934FF, 0xE46254FF,
0x82CF34FF, 0x7253C0FF, 0x004FDBFF, 0x638AACFF, 0x3B1EE2FF, 0xFB47E7FF, 0xA52D96FF, 0x74389FFF,
0xED1740FF, 0xE493D3FF, 0x601708FF, 0x3B1EADFF, 0xF81AB8FF, 0x93F29DFF, 0xE3838BFF, 0x43A3DCFF,
0xF5CF62FF, 0x9E5FA0FF, 0x5D7B1EFF, 0xA82A44FF, 0x5BCEE2FF, 0xDAA95CFF, 0x68EF3FFF, 0x5E2141FF,
0x209462FF, 0x912AF2FF, 0xD18F49FF, 0x426217FF, 0x333870FF, 0x0F2E1EFF, 0x0F4A51FF, 0x151F10FF,
0x06041DFF, 0x4D89FDFF, 0xCB223AFF, 0x196BB7FF, 0x7E4D21FF, 0x7D8930FF, 0x87C486FF, 0x521186FF,
0xA4B0FCFF, 0x4BBBA3FF, 0x070307FF, 0x5DAFF8FF, 0xEF4A8AFF, 0x1F618BFF, 0x6C16FFFF, 0xD037EFFF,
0x2E0731FF, 0x2800A5FF, 0x735177FF, 0x3C57FBFF, 0xD5258CFF, 0x59EFF8FF, 0xBB506CFF, 0x00421BFF,
0xE36CB3FF, 0x5B4531FF, 0x50C01CFF, 0x8B1379FF, 0xF7A3B2FF, 0xD3FDD3FF, 0xF06EF8FF, 0x0082C1FF,
0x137F82FF, 0x009F1DFF, 0xE0AEFFFF, 0x91A288FF, 0x6B2067FF, 0x2DA190FF, 0xFAB46BFF, 0x240607FF,
0x0F0B05FF, 0x005B2EFF, 0xA18E2AFF, 0x007548FF, 0xB6EB30FF, 0x0A08E3FF, 0x924CF5FF, 0x120177FF,
0x090742FF, 0x84FCFCFF, 0xBD6A86FF, 0x0E42B2FF, 0x331359FF, 0xFDC3FEFF, 0xDA522CFF, 0x6312CBFF,
0xBC68FDFF, 0x656EB1FF, 0x095179FF, 0x00DF8FFF, 0x59331BFF, 0x380911FF, 0x7B0E14FF, 0x8727C9FF,
0xFC703FFF, 0x7A3D66FF, 0x8683D3FF, 0x8A6825FF, 0x972A19FF, 0xBF491DFF, 0x8E5488FF, 0xA6D09BFF,
0xC67315FF, 0xCA4BB3FF, 0xF89179FF, 0xF9FE92FF, 0x4E53B3FF, 0x00CE7BFF, 0x372A09FF, 0xDCE7A2FF,
0x3C1079FF, 0xBE0D66FF, 0x5E6A1AFF, 0x00005EFF, 0xB0BCBDFF, 0x523B52FF, 0x16071FFF, 0xF9384CFF,
0xFA8A21FF, 0xBE8FFFFF, 0x00E41DFF, 0x00675CFF, 0xBB15F7FF, 0xF8C7BDFF, 0x00B112FF, 0x4940B1FF,
0xBB8597FF, 0xFAFF23FF, 0xEFE429FF, 0xA911CAFF, 0x930EA3FF, 0x3D2CFEFF, 0x00909CFF, 0xFB88FFFF,
0xB972C4FF, 0x2D1F0DFF, 0x052949FF, 0xB7B72CFF, 0x7AD7FFFF, 0x080695FF, 0x668D76FF, 0x9D2374FF,
0xDB54CCFF, 0xB2DFC4FF, 0xF02178FF, 0x008E25FF, 0x0A395FFF, 0xE5B51BFF, 0xFA6376FF, 0x7A73CDFF,
0xD616CEFF, 0xF977BDFF, 0x7597AAFF, 0xC0D4FFFF, 0xB03707FF, 0x7696F9FF, 0x59669AFF, 0x0098D9FF,
0x9A174CFF, 0x11BD75FF, 0xFCEAF2FF, 0x003197FF, 0x3C2B5DFF, 0x8AA1C3FF, 0x0DAFB3FF, 0x18CFB7FF,
0x2D5650FF, 0x4AFF80FF, 0x4E4E87FF, 0xEF18FFFF, 0x3D043AFF, 0x41350DFF, 0x675BE9FF, 0x99645BFF,
0x008017FF, 0x00FFD2FF, 0xB14085FF, 0x648155FF, 0x5645E3FF, 0x7DDD6CFF, 0x3A3B45FF, 0x609C17FF,
0x7D0C41FF, 0x00D41BFF, 0x495316FF, 0x967D58FF, 0xC1835BFF, 0x1C0C2DFF, 0x0008C7FF, 0xB3A86EFF,
0x9BFFC5FF, 0x00C1DCFF, 0x732F14FF, 0xC215A4FF, 0x001506FF, 0x8D75A0FF, 0x5B1131FF, 0xAAC1F9FF,
0xFAB5DFFF, 0x006EE5FF, 0x4B2CACFF, 0x372545FF, 0xD8175FFF, 0x7D5D31FF, 0x7A9B73FF, 0x526242FF,
0x7968FCFF, 0x5F0C62FF, 0x0087E6FF, 0x9847AFFF, 0x68BD68FF, 0xB5DC8CFF, 0x5D455CFF, 0x0056B0FF,
0x5E30DAFF, 0xDC7914FF, 0x944567FF, 0x502B85FF, 0x53090DFF, 0x9DA0E6FF, 0xD077EAFF, 0xFD49B3FF,
}
 */
public class RandomPaletteGenerator {
    private static final int limit = 256;
    private static float minDistance = Float.MAX_VALUE;
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
//            if(Vector3.dst2(L, A, B, ColorTools.channelL(o), ColorTools.channelA(o), ColorTools.channelB(o)) < limit)
//                return;
//        }
        rgba.add(value);
        labs.add(oklab);
    }
    private static void add(float oklab){
        float L = ColorTools.channelL(oklab),
//                A = ColorTools.channelA(oklab),
//                B = ColorTools.channelB(oklab);
                A = OtherMath.barronSpline(ColorTools.channelA(oklab), 0.75f, 0.5f),
                B = OtherMath.barronSpline(ColorTools.channelB(oklab), 0.75f, 0.5f);
        if(!ColorTools.inGamut(L, A, B))
            return;
        oklab = ColorTools.oklab(L, A, B, 1f);
        final double limit = 0.00275 - (labs.size * 0.000006);
        for (int idx = 0; idx < labs.size; idx++) {
            float o = labs.get(idx),
                    d = Vector3.dst2(L, A, B, ColorTools.channelL(o), ColorTools.channelA(o), ColorTools.channelB(o));
            if(d < limit)
                return;
            minDistance = Math.min(minDistance, d);
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
            if(++idx % 10000 == 0)
                System.out.println(idx + " tries, " + rgba.size + " placed, " + minDistance + " min distance");
//            ++idx;
//            for (int i = initial; i < 32 && rgba.size < limit; i++) {
//                float color = gaussianColor(idx++, (1.0 - i * i * i * 0x1p-16));
//                if (ColorTools.inGamut(color)) {
//                    rgba.add(ColorTools.toRGBA8888(color));
//                }
//            }
        }
        System.out.println(idx + " attempts.");
        System.out.println(minDistance + " min distance");
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
