package com.github.tommyettinger.colorful.oklab;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Files;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.GdxNativesLoader;
import com.badlogic.gdx.utils.IntArray;
import com.github.tommyettinger.colorful.FourWheelRandom;
import com.github.tommyettinger.colorful.TrigTools;
import com.github.tommyettinger.colorful.internal.StringKit;
import com.github.tommyettinger.colorful.oklab.internal.OklabVectors;

import java.util.ArrayList;
import java.util.Collections;

/*
{
0x00000000, 0x595C42FF, 0x605C6DFF, 0x7B7584FF, 0x97A38CFF, 0xB3A0A3FF, 0xC7E6D7FF, 0xD6E4FFFF,
0xFCE1F0FF, 0xDB296AFF, 0xA26976FF, 0xD23C4FFF, 0x753D38FF, 0x47231EFF, 0xCC624CFF, 0xFFD1C3FF,
0xA95C2BFF, 0x82684BFF, 0xEFAD5FFF, 0xA39025FF, 0xB8AA23FF, 0xFBFDBEFF, 0xE0EC91FF, 0x849A4FFF,
0x76AA3AFF, 0x618148FF, 0x9BB795FF, 0x79EF8EFF, 0x3CA757FF, 0x007851FF, 0x45937BFF, 0x26544CFF,
0x186A64FF, 0x5BE1E1FF, 0x9BFCFFFF, 0x6DB5BAFF, 0x00C9E6FF, 0x90DAE8FF, 0x4AA5E3FF, 0x2A74CDFF,
0x2A5591FF, 0x153DA1FF, 0x869DD0FF, 0x132892FF, 0x3753FFFF, 0x222341FF, 0x7F7BB0FF, 0x5E33D6FF,
0x410B9DFF, 0xA590D0FF, 0x7727A4FF, 0x5B4766FF, 0x79009AFF, 0xD8AEE5FF, 0x771683FF, 0xA91DABFF,
0x2B1328FF, 0xFF88ECFF, 0xEFC6DFFF, 0xDF7BB7FF, 0x4A0E34FF, 0xA44371FF, 0xAB2B69FF, 0x55293BFF,
}

{
0x00000000, 0x211B20FF, 0x282B2AFF, 0x424D4AFF, 0x61615FFF, 0x8F9390FF, 0x9AAAABFF, 0xC5C2C0FF,
0xD7D6DCFF, 0xB1114DFF, 0xD23C4FFF, 0xFF7A7BFF, 0xFD4A4FFF, 0x753D38FF, 0xA03D34FF, 0xC57264FF,
0x5E2800FF, 0xA95C2BFF, 0xD7986AFF, 0xEFAD5FFF, 0xFFDF1AFF, 0xE9CB30FF, 0x9F8F28FF, 0xAEA951FF,
0xFBFDBEFF, 0x6A7400FF, 0x9DC022FF, 0x425900FF, 0xACDA76FF, 0xB6FA61FF, 0x76AA3AFF, 0x51E640FF,
0x00C33BFF, 0x2C9641FF, 0x94F4B6FF, 0x24A081FF, 0x307876FF, 0x88F4FBFF, 0x00DDE9FF, 0x305F7DFF,
0x22A2FBFF, 0x72B1FFFF, 0x668DC5FF, 0x1F67CEFF, 0x144094FF, 0x0000A1FF, 0x15296EFF, 0x1B2EDEFF,
0x5E35D1FF, 0x825BF5FF, 0xA598DFFF, 0x42375EFF, 0xAD69ECFF, 0x653282FF, 0xB317F5FF, 0xE357F6FF,
0x925F92FF, 0xA91DABFF, 0xFF8EFCFF, 0xDD64BBFF, 0x651E4DFF, 0xF900A7FF, 0xC65985FF, 0xE89EB5FF,
}

// Web31, based on Prospecal8
{
0x00000000, 0x211B20FF, 0x424D4AFF, 0x646867FF, 0x938C91FF, 0x9AAAABFF, 0xD5D7E2FF, 0xE8DFDFFF,
0xF192A8FF, 0xDB727AFF, 0xD23C4FFF, 0x753D38FF, 0xE55738FF, 0xC05015FF, 0xEFAD5FFF, 0x855600FF,
0xA87E34FF, 0xB6A740FF, 0xFBFDBEFF, 0x76AA3AFF, 0xA3F064FF, 0xA4D783FF, 0x2BDC67FF, 0x2A8F75FF,
0x3A84CAFF, 0x4A57A3FF, 0x8491E3FF, 0x3E128DFF, 0x8E6892FF, 0x6D1F6BFF, 0xAD379FFF, 0x9D4168FF,
}

// Web63, based on Prospecal8
{
0x00000000, 0x211B20FF, 0x282B2AFF, 0x424D4AFF, 0x61615FFF, 0x8F9390FF, 0x9AAAABFF, 0xC5C2C0FF,
0xD7D6DCFF, 0xB1114DFF, 0xD23C4FFF, 0xFF7A7BFF, 0xFD4A4FFF, 0x753D38FF, 0xA03D34FF, 0xC57264FF,
0x5E2800FF, 0xA95C2BFF, 0xD7986AFF, 0xEFAD5FFF, 0xFFDF1AFF, 0xE9CB30FF, 0x9F8F28FF, 0xAEA951FF,
0xFBFDBEFF, 0x6A7400FF, 0x9DC022FF, 0x425900FF, 0xACDA76FF, 0xB6FA61FF, 0x76AA3AFF, 0x51E640FF,
0x00C33BFF, 0x2C9641FF, 0x94F4B6FF, 0x24A081FF, 0x307876FF, 0x88F4FBFF, 0x00DDE9FF, 0x305F7DFF,
0x22A2FBFF, 0x72B1FFFF, 0x668DC5FF, 0x1F67CEFF, 0x144094FF, 0x0000A1FF, 0x15296EFF, 0x1B2EDEFF,
0x5E35D1FF, 0x825BF5FF, 0xA598DFFF, 0x42375EFF, 0xAD69ECFF, 0x653282FF, 0xB317F5FF, 0xE357F6FF,
0x925F92FF, 0xA91DABFF, 0xFF8EFCFF, 0xDD64BBFF, 0x651E4DFF, 0xF900A7FF, 0xC65985FF, 0xE89EB5FF,
}

// Deneb31, based on DawnBringer8
{
0x00000000, 0x000000FF, 0x303437FF, 0x4C484FFF, 0x666866FF, 0x7C8885FF, 0xA3ABA7FF, 0xB7C4C9FF,
0xEBF2F4FF, 0xB0154FFF, 0xFF6383FF, 0x6F2A30FF, 0xFE0041FF, 0xD69185FF, 0xD77355FF, 0xEEA250FF,
0xC28100FF, 0xE6C86EFF, 0xE8E154FF, 0x67D422FF, 0x64B964FF, 0x53E8CCFF, 0x37678AFF, 0x508CD7FF,
0x555BD1FF, 0x320090FF, 0xBDB9FFFF, 0x513DA1FF, 0x701392FF, 0xFF72DBFF, 0xF74BC9FF, 0xA64E8AFF,
}

// Deneb63, based on DawnBringer8
{
0x00000000, 0x000000FF, 0x111414FF, 0x3C3636FF, 0x4C484FFF, 0x666866FF, 0x8A8A8CFF, 0x9B978BFF,
0xB1B1AFFF, 0xCEC5C7FF, 0xD5DCD2FF, 0xEBDFE6FF, 0xEBF2F4FF, 0x934953FF, 0x7C2B36FF, 0xFF4759FF,
0xAA2D0BFF, 0xF68D72FF, 0xD77355FF, 0x451802FF, 0xDB4500FF, 0x884807FF, 0xA86600FF, 0xECA854FF,
0xE6C86EFF, 0xF7E94AFF, 0xFDFEBCFF, 0xAEB13AFF, 0xCFD92DFF, 0x2E4A1FFF, 0x9DD383FF, 0x4A6B3AFF,
0x84E75EFF, 0x568D4BFF, 0x3CA12DFF, 0x40DF29FF, 0x64B964FF, 0xA8FFB7FF, 0x00351EFF, 0x00F3BCFF,
0x1CB6B7FF, 0x13D7D8FF, 0x007C89FF, 0x00517EFF, 0x2F639CFF, 0x508CD7FF, 0x0744D2FF, 0xAAC6FEFF,
0x1F317DFF, 0x8EA1DAFF, 0x6259FFFF, 0x7637D2FF, 0x9E53FAFF, 0xF0B9FFFF, 0x8523A3FF, 0x660A76FF,
0x875B8CFF, 0xC07DC2FF, 0xE400E7FF, 0xEB5BE7FF, 0x3F0437FF, 0xC0588FFF, 0xF94EA1FF, 0xEA81ABFF,
}

// Hype31, based on Hyper8
{
0x00000000, 0x000000FF, 0x515B57FF, 0xA8A09BFF, 0xC4C5C0FF, 0xFFFFFFFF, 0x4C0014FF, 0xFF0000FF,
0xAF2100FF, 0xF07E62FF, 0xFFFF00FF, 0x9BD37EFF, 0x00FF00FF, 0x00FFFFFF, 0x119BAEFF, 0x9EF0FFFF,
0x36C5FFFF, 0x0000FFFF, 0x404F9AFF, 0x342984FF, 0x6348CFFF, 0x7847FFFF, 0x9C79DDFF, 0xE0ADFBFF,
0x613172FF, 0xFF00FFFF, 0xC332B3FF, 0x871966FF, 0x9C6080FF, 0xEF179CFF, 0xF367A7FF, 0x670D36FF,
}

// Hype63, based on Hyper8
{
0x00000000, 0x000000FF, 0x201718FF, 0x1E2221FF, 0x393033FF, 0x2F3439FF, 0x3A4540FF, 0x82837EFF,
0x9B8E88FF, 0x9C9E9BFF, 0xB2B1B4FF, 0xBDC7C6FF, 0xE5D5DAFF, 0xFFFFFFFF, 0xD8136AFF, 0x9A5F6EFF,
0xFFACAFFF, 0x9F002AFF, 0xFF0000FF, 0x72271FFF, 0xF07E62FF, 0xAC2F0DFF, 0xF66600FF, 0x764523FF,
0x965919FF, 0xD9A13DFF, 0xE5D793FF, 0xAB9C11FF, 0xFFFF00FF, 0x96BF64FF, 0x459D1CFF, 0x54C344FF,
0x97FF8DFF, 0x00FF00FF, 0xC0FEC4FF, 0x25F29AFF, 0x29B77AFF, 0x2E735DFF, 0x00FFFFFF, 0x46A2E2FF,
0x314B7BFF, 0x0000FFFF, 0x4E6CB5FF, 0x445FFCFF, 0x3C2FC8FF, 0x3C2A8DFF, 0x9E91CFFF, 0x9163FDFF,
0x2B0257FF, 0x866CC2FF, 0x8333E5FF, 0xC418FEFF, 0xCA58F6FF, 0x662C76FF, 0xDDA2E2FF, 0xE276E3FF,
0xFF00FFFF, 0xA2309AFF, 0x774F70FF, 0xDC47BFFF, 0xB64E9BFF, 0xEA3D85FF, 0xD36A8CFF, 0x7A003CFF,
}

// Judo31, based on Japanese Woodblock 12
{
0x00000000, 0x292826FF, 0x363C43FF, 0x55504CFF, 0x57615DFF, 0x696E6FFF, 0x798486FF, 0xA9A6A0FF,
0xBFB4ABFF, 0xD7D2CBFF, 0xFAF5F2FF, 0xB03A48FF, 0xAF582FFF, 0xD4804DFF, 0xE0C872FF, 0x849867FF,
0x71D372FF, 0x51F55CFF, 0x45BE81FF, 0x7ED7FBFF, 0x5AB5F8FF, 0x0062EBFF, 0x384497FF, 0x8377B2FF,
0x6A3D93FF, 0xE5BDF0FF, 0xBB04A4FF, 0xCB63AEFF, 0x8D1D73FF, 0xA16683FF, 0xE090B5FF, 0xFA408DFF,
}

// Judo63, based on Japanese Woodblock 12
{
0x00000000, 0x0C0C10FF, 0x292826FF, 0x363C43FF, 0x3B3E38FF, 0x55504CFF, 0x57615DFF, 0x696E6FFF,
0x766A6DFF, 0x798486FF, 0x968E92FF, 0xA9A6A0FF, 0xBFB4ABFF, 0xBDB7C0FF, 0xB4C1C5FF, 0xD7D2CBFF,
0xCFD7DEFF, 0xF7F2EBFF, 0xAC0852FF, 0x7A283FFF, 0xE71C61FF, 0x53262AFF, 0xB03A48FF, 0xEB6168FF,
0xC2585BFF, 0xCF380CFF, 0xD4804DFF, 0x8A532FFF, 0xE0C872FF, 0xC8B200FF, 0x9F984BFF, 0xD3E764FF,
0xB6E000FF, 0x596D17FF, 0x72834EFF, 0x7BBC69FF, 0x1C5312FF, 0xC0F3B8FF, 0x19B911FF, 0x0F961EFF,
0x64E66FFF, 0x26AC7FFF, 0x68E3D3FF, 0x32B4B9FF, 0x005C71FF, 0x5697ECFF, 0x406BB0FF, 0x2960FFFF,
0x6C7DCFFF, 0x3E46A2FF, 0x30179FFF, 0x5836D5FF, 0x1F103FFF, 0xA05AFAFF, 0xB687E6FF, 0x9043D0FF,
0x9500CDFF, 0x521765FF, 0xC652D4FF, 0x89198FFF, 0x7E407EFF, 0xA668A1FF, 0xE877C3FF, 0xC02E93FF,
}

 */
public class VectorPaletteExpander {
    static final long SEED = 1L;
    static final int LIMIT = 63;
    static final IntArray RGBA = new IntArray(LIMIT+1);

    static final int[] DB8 = new int[]{
            0x000000FF, 0x55415FFF, 0x646964FF, 0xD77355FF, 0x508CD7FF, 0x64B964FF, 0xE6C86EFF, 0xDCF5FFFF};

    static final int[] PROSPECAL = new int[]{
            0x6DB5BAFF, 0x26544CFF, 0x76AA3AFF, 0xFBFDBEFF, 0xD23C4FFF, 0x2B1328FF, 0x753D38FF, 0xEFAD5FFF};

    static final int[] HYPER8 = new int[]{
            0x000000FF, 0xFFFFFFFF, 0xFF0000FF, 0x00FF00FF, 0x0000FFFF, 0x00FFFFFF, 0xFF00FFFF, 0xFFFF00FF};

    static final int[] JAPANESE_WOODBLOCK = new int[]{
0x2B2821FF,
0x624C3CFF,
0xD9AC8BFF,
0xE3CFB4FF,
0x243D5CFF,
0x5D7275FF,
0x5C8B93FF,
0xB1A58DFF,
0xB03A48FF,
0xD4804DFF,
0xE0C872FF,
0x3E6958FF,
    };

    public static void main(String[] args) {
        GdxNativesLoader.load();
        FourWheelRandom random = new FourWheelRandom(SEED);
        ArrayList<Vector3> vs = new ArrayList<>(LIMIT);
        int[] base = JAPANESE_WOODBLOCK;
        for (int i = 0; i < base.length; i++) {
            vs.add(OklabVectors.fromRGBA8888(new Vector3(), base[i]));
        }
        final float threshold = 1f / LIMIT, lowThreshold = 0.08f, move = (float)Math.sqrt(threshold);

        ITERS:
        for (int iter = vs.size(); iter < LIMIT; iter++) {
            int triesLeft = 100;
            TRIALS:
            while (triesLeft-- > 0){
                int choice = random.nextInt(iter);
                Vector3 initial = vs.get(choice), next = new Vector3();
                if(OklabVectors.randomChangeIfValid(next, initial, move, random) == null) continue;
                for (int i = 0; i < iter; i++) {
                    if(i == choice) continue;
                    Vector3 other = vs.get(i);
                    if(other.dst(next) < lowThreshold) continue TRIALS;
                }
                vs.add(next);
                continue ITERS;
            }
            // failed to find a valid color in 100 tries
            System.err.println("OH NO, FAILED ON ITERATION " + iter);
            throw new RuntimeException("SAD FACE :(");
        }
        final float GRAY_LIMIT = 0.005f;
        for (int i = 0; i < vs.size(); i++) {
            Vector3 v = vs.get(i);
            if(v.y * v.y + v.z * v.z <= GRAY_LIMIT){
                v.y *= 0.25f;
                v.z *= 0.25f;
            }
        }
        Collections.sort(vs, (c1, c2) -> {
//                if (ColorTools.alphaInt(c1.value) < 128) return -10000;
//                else if (ColorTools.alphaInt(c2.value) < 128) return 10000;
            float s1 = (c1.y * c1.y + c1.z * c1.z), s2 = (c2.y * c2.y + c2.z * c2.z);

            if(s1 <= GRAY_LIMIT && s2 > GRAY_LIMIT)
                return -1000;
            else if(s1 > GRAY_LIMIT && s2 <= GRAY_LIMIT)
                return 1000;
            else if(s1 <= GRAY_LIMIT && s2 <= GRAY_LIMIT)
                return (int)Math.signum(c1.x - c2.x);
            else
                return 2 * (int)Math.signum(TrigTools.atan2Turns(c1.z, c1.y) - TrigTools.atan2Turns(c2.z, c2.y))
                        + (int)Math.signum(c1.x - c2.x);
        });

        RGBA.add(0);
        for(Vector3 v : vs) {
            RGBA.add(OklabVectors.toRGBA8888(v));
        }
        StringBuilder sb = new StringBuilder(12 * RGBA.size + 35).append("{\n");
        for (int i = 0; i < RGBA.size; i++) {
            StringKit.appendHex(sb.append("0x"), RGBA.get(i)).append(", ");
            if(7 == (i & 7)) sb.append('\n');
        }
        System.out.println(sb.append('}'));

        Pixmap palette = new Pixmap(RGBA.size, 1, Pixmap.Format.RGBA8888);
        for (int i = 0; i < RGBA.size; i++) {
            palette.drawPixel(i, 0, RGBA.get(i));
        }
        Lwjgl3Files files = new Lwjgl3Files();
        String name = "Judo"+LIMIT+"-"+SEED+"-" + System.currentTimeMillis() + ".png";
        PixmapIO.writePNG(files.local(name), palette);
        System.out.println("Wrote to " + name);
        palette.dispose();

    }

}
