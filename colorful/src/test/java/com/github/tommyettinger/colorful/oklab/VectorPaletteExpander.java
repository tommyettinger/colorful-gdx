package com.github.tommyettinger.colorful.oklab;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.ObjectFloatMap;
import com.github.tommyettinger.colorful.FourWheelRandom;
import com.github.tommyettinger.colorful.TrigTools;
import com.github.tommyettinger.colorful.internal.StringKit;
import com.github.tommyettinger.colorful.oklab.internal.OklabVectors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class VectorPaletteExpander {
    static final long SEED = 1L;
    static final int LIMIT = 63;
    static final IntArray RGBA = new IntArray(LIMIT+1);

    static final int[] DB8 = new int[]{
            0x000000FF, 0x55415FFF, 0x646964FF, 0xD77355FF, 0x508CD7FF, 0x64B964FF, 0xE6C86EFF, 0xDCF5FFFF};

    static final int[] PROSPECAL = new int[]{
            0x6DB5BAFF, 0x26544CFF, 0x76AA3AFF, 0xFBFDBEFF, 0xD23C4FFF, 0x2B1328FF, 0x753D38FF, 0xEFAD5FFF};

    public static void main(String[] args) {
//        RGBA.add(0); // transparent
        FourWheelRandom random = new FourWheelRandom(SEED);
        ArrayList<Vector3> vs = new ArrayList<>(LIMIT);
        int[] base = PROSPECAL;
        for (int i = 0; i < base.length; i++) {
            vs.add(OklabVectors.fromRGBA8888(new Vector3(), base[i]));
        }
//        RGBA.addAll(base);
        ITERS:
        for (int iter = vs.size(); iter < LIMIT; iter++) {
            int triesLeft = 100;
            float threshold = 1f / iter, lowThreshold = 0.95f * threshold * threshold;
            TRIALS:
            while (triesLeft-- > 0){
                int choice = triesLeft % iter;
                Vector3 initial = vs.get(choice), next = new Vector3();
                OklabVectors.randomChangeIfValid(next, initial, threshold, random);
                for (int i = 0; i < iter; i++) {
                    if(i == choice) continue;
                    Vector3 other = vs.get(i);
                    if(other.dst(next) < lowThreshold) continue TRIALS;
                }
                vs.add(next);
//                RGBA.add(OklabVectors.toRGBA8888(next));
                continue ITERS;
            }
            // failed to find a valid color in 100 tries
            System.err.println("OH NO, FAILED ON ITERATION " + iter);
            throw new RuntimeException("SAD FACE :(");
        }

        Collections.sort(vs, (c1, c2) -> {
//                if (ColorTools.alphaInt(c1.value) < 128) return -10000;
//                else if (ColorTools.alphaInt(c2.value) < 128) return 10000;
            float s1 = (float)Math.sqrt(c1.y * c1.y + c1.z * c1.z), s2 = (float)Math.sqrt(c2.y * c2.y + c2.z * c2.z);
            if(s1 <= 0.05f && s2 > 0.05f)
                return -1000;
            else if(s1 > 0.05f && s2 <= 0.05f)
                return 1000;
            else if(s1 <= 0.05f && s2 <= 0.05f)
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

    }

}
