package com.github.tommyettinger.colorful.ycwcm;

import com.badlogic.gdx.utils.IntArray;
import com.github.tommyettinger.anim8.PNG8;
import com.github.tommyettinger.anim8.PaletteReducer;
import com.github.tommyettinger.colorful.internal.StringKit;
/* Example output (256 colors):
{0x00000000, 0x0B080FFF, 0x353336FF, 0x555555FF, 0x797577FF, 0xAAAAAAFF, 0xC8C8C8FF, 0xE0E0E0FF,
0xFAF7F0FF, 0x5D8ABCFF, 0x620105FF, 0xBD8CAAFF, 0x872B9FFF, 0x5D8545FF, 0x114C66FF, 0x204503FF,
0xAC6FBFFF, 0x642D53FF, 0xBE9F85FF, 0x19806EFF, 0x789997FF, 0xD23589FF, 0xB5D7EDFF, 0xB8978BFF,
0x4A695FFF, 0x5B1038FF, 0xAD54CAFF, 0x389888FF, 0xC7A456FF, 0x157E80FF, 0x5FC274FF, 0x485395FF,
0xA2C1D1FF, 0x90673DFF, 0xE9FEEEFF, 0xE6EDB5FF, 0x734A32FF, 0x9784ACFF, 0x3D4060FF, 0xE9E189FF,
0xB1CF89FF, 0xC82488FF, 0xB39BBBFF, 0xE8E5C7FF, 0x6898D0FF, 0x008428FF, 0xB5B44CFF, 0x060B23FF,
0xA7464CFF, 0xA6F2AEFF, 0xAF6E86FF, 0x26305EFF, 0xD3F9CBFF, 0x5D3B25FF, 0x9EB9ADFF, 0xA62D8DFF,
0xC7D6F6FF, 0x77273FFF, 0xFC2A8CFF, 0x215024FF, 0xE3988EFF, 0xA94AA6FF, 0xDF82E4FF, 0x3872A0FF,
0xA09795FF, 0xB0C1BBFF, 0x9F5A54FF, 0x789850FF, 0x57205EFF, 0xA780B6FF, 0x639B63FF, 0x7DE492FF,
0x38854BFF, 0x68D367FF, 0x465533FF, 0x32AFA9FF, 0x575ECAFF, 0x6875CDFF, 0x7F9E64FF, 0x69360CFF,
0x239076FF, 0x6BCC6EFF, 0x8E1A56FF, 0x8C73BBFF, 0x236656FF, 0xB9D6A8FF, 0x4A9989FF, 0xD89AC0FF,
0x9F90A2FF, 0x198A14FF, 0x75EDB5FF, 0x5975C9FF, 0x332E18FF, 0x3EAFA9FF, 0x610236FF, 0x3F2C76FF,
0x7BD478FF, 0x666B2BFF, 0xCED3F3FF, 0x8B6AA0FF, 0xF6BBD3FF, 0xBF9A64FF, 0x11A636FF, 0xAFA6BAFF,
0xAC215FFF, 0x69BC6CFF, 0xC2F189FF, 0xF672C6FF, 0x72606AFF, 0xE04D8DFF, 0x8464A4FF, 0xF277B7FF,
0x54664CFF, 0x88454FFF, 0xB4878BFF, 0x155F6DFF, 0x835AC0FF, 0x84855FFF, 0x0B601AFF, 0x4C0414FF,
0x149B41FF, 0xA8936BFF, 0xE8F1F7FF, 0xBAC3C9FF, 0x6D844CFF, 0x90D37BFF, 0xBD7290FF, 0x4F465AFF,
0xD39A9EFF, 0x93E9CBFF, 0x6768ACFF, 0xBA56B2FF, 0x25B282FF, 0x46CB73FF, 0xE9EAF8FF, 0xA31242FF,
0x90DB77FF, 0x26A45EFF, 0x181517FF, 0xD7EEA4FF, 0x187B45FF, 0xCC598BFF, 0x75192DFF, 0xBFC0ECFF,
0xDDAB6DFF, 0x537280FF, 0xC58A94FF, 0xECA5E1FF, 0x627ED2FF, 0x46BB81FF, 0x52D3B1FF, 0xDB9CC6FF,
0x54726CFF, 0xA69B99FF, 0x2C2F01FF, 0x9EA5A5FF, 0x415866FF, 0xB06D6DFF, 0x439183FF, 0xBCD7ABFF,
0xA085BBFF, 0x783153FF, 0x776367FF, 0x82B7A5FF, 0x635222FF, 0x76CB61FF, 0x346BA3FF, 0x72F0DAFF,
0x2E3533FF, 0x331D43FF, 0x49A890FF, 0x1F380CFF, 0x80B39FFF, 0x962B4DFF, 0xF79EB6FF, 0x37083AFF,
0xA0FDBDFF, 0x6B8E56FF, 0x610E78FF, 0xDC3C84FF, 0xB690C6FF, 0x208197FF, 0x1EB28EFF, 0xF2B9D9FF,
0x95EED0FF, 0x163E4EFF, 0xBB74CAFF, 0x4F6614FF, 0x6BD4BAFF, 0x287F5FFF, 0xBD6D95FF, 0x9067A5FF,
0x4CEFA3FF, 0x7D4098FF, 0x4A1B59FF, 0x5DB264FF, 0x5D8E58FF, 0xAA98F2FF, 0x7F3A6AFF, 0x2D6042FF,
0x2CE593FF, 0x67244AFF, 0x626139FF, 0xDEA991FF, 0x449187FF, 0xCDF8ECFF, 0x062D29FF, 0xE5F5B5FF,
0x4F5686FF, 0x8499ABFF, 0x7B5AB2FF, 0xBD98CCFF, 0x949864FF, 0x465135FF, 0xA4A5D7FF, 0xE2B987FF,
0x2FA064FF, 0xFFE0C6FF, 0x8B266AFF, 0x878C92FF, 0x4F2E0EFF, 0xF6E3D5FF, 0x1E6B49FF, 0xAF3D47FF,
0x1C0533FF, 0x83214BFF, 0x20DA70FF, 0x17524AFF, 0xA5BFB5FF, 0x687327FF, 0x5DD87CFF, 0xA3D7C3FF,
0x906B6BFF, 0x847FA3FF, 0x5B2A3EFF, 0x7BC252FF, 0xCBAE98FF, 0xB72662FF, 0x51AEB0FF, 0x12095DFF,
0x86B981FF, 0x7682A6FF, 0xC32A76FF, 0xC6BEDEFF, 0xB26155FF, 0x899F69FF, 0x413D99FF, 0x24301CFF,
0x418D41FF, 0xCD828AFF, 0x375876FF, 0x989B55FF, 0x6D4642FF, 0x5F81BFFF, 0xF3F8C6FF, 0xE26E72FF,
}
 */
public class SubRandomPaletteGenerator {
    public static void main(String[] args) {
        final int limit = 256;
        IntArray rgba = new IntArray(limit);
        rgba.add(0);
        rgba.add(0x0B080FFF);
        rgba.add(0x353336FF);
        rgba.add(0x555555FF);
        rgba.add(0x797577FF);
        rgba.add(0xAAAAAAFF);
        rgba.add(0xC8C8C8FF);
        rgba.add(0xE0E0E0FF);
        rgba.add(0xFAF7F0FF);
        int idx = 1, initial = rgba.size;
        while (rgba.size < limit) {
            for (int i = initial; i < 32 && rgba.size < limit; i++) {
                float color = gaussianColor(idx++, 0.5 * (1.0 - i * i * i * 0x1p-16));
                if (ColorTools.inGamut(color)) {
                    rgba.add(ColorTools.toRGBA8888(color));
                }
            }
        }
        StringBuilder sb = new StringBuilder(12 * rgba.size + 34).append('{');
        for (int i = 0; i < rgba.size; i++) {
            StringKit.appendHex(sb.append("0x"), rgba.get(i)).append(", ");
            if(7 == (i & 7)) sb.append('\n');
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
        return ColorTools.ycwcm((float) resX, (float) resY, (float) resZ, 1f);
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
        return ColorTools.ycwcm((float) resX,
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
        return ColorTools.ycwcm(
                PaletteReducer.barronSpline((float) resX, 1.25f, 0.5f),//(PNG8.probit(resX) * 5.0 % 0.5 + 0.5),
//                (float) (PNG8.probit(resX) * 2.0 % 0.5 + 0.5),
                (float) (PNG8.probit(resY) % sat + 0.5),
                (float) (PNG8.probit(resZ) % sat + 0.5), 1f);
    }

}
