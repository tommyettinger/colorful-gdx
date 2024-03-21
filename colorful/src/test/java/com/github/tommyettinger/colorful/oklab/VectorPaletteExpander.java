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

 */
public class VectorPaletteExpander {
    static final long SEED = 1L;
    static final int LIMIT = 63;
    static final IntArray RGBA = new IntArray(LIMIT+1);

    static final int[] DB8 = new int[]{
            0x000000FF, 0x55415FFF, 0x646964FF, 0xD77355FF, 0x508CD7FF, 0x64B964FF, 0xE6C86EFF, 0xDCF5FFFF};

    static final int[] PROSPECAL = new int[]{
            0x6DB5BAFF, 0x26544CFF, 0x76AA3AFF, 0xFBFDBEFF, 0xD23C4FFF, 0x2B1328FF, 0x753D38FF, 0xEFAD5FFF};

    public static void main(String[] args) {
        GdxNativesLoader.load();
        FourWheelRandom random = new FourWheelRandom(SEED);
        ArrayList<Vector3> vs = new ArrayList<>(LIMIT);
        int[] base = PROSPECAL;
        for (int i = 0; i < base.length; i++) {
            vs.add(OklabVectors.fromRGBA8888(new Vector3(), base[i]));
        }
        ITERS:
        for (int iter = vs.size(); iter < LIMIT; iter++) {
            int triesLeft = 100;
            float threshold = 1f / iter, lowThreshold = 0.08f, move = (float)Math.sqrt(threshold);
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
        String name = "Web-"+SEED+"-" + System.currentTimeMillis() + ".png";
        PixmapIO.writePNG(files.local(name), palette);
        System.out.println("Wrote to " + name);
        palette.dispose();

    }

}
