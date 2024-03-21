package com.github.tommyettinger.colorful.oklab;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.IntArray;
import com.github.tommyettinger.colorful.FourWheelRandom;
import com.github.tommyettinger.colorful.internal.StringKit;
import com.github.tommyettinger.colorful.oklab.internal.OklabVectors;

import java.util.ArrayList;

public class VectorPaletteExpander {
    static final long SEED = 1L;
    static final int LIMIT = 63;
    static final IntArray RGBA = new IntArray(LIMIT+1);

    static final int[] DB8 = new int[]{
            0x000000FF, 0x55415FFF, 0x646964FF, 0xD77355FF, 0x508CD7FF, 0x64B964FF, 0xE6C86EFF, 0xDCF5FFFF};

    static final int[] PROSPECAL = new int[]{
            0x6DB5BAFF, 0x26544CFF, 0x76AA3AFF, 0xFBFDBEFF, 0xD23C4FFF, 0x2B1328FF, 0x753D38FF, 0xEFAD5FFF};

    public static void main(String[] args) {
        RGBA.add(0); // transparent
        FourWheelRandom random = new FourWheelRandom(SEED);
        ArrayList<Vector3> vs = new ArrayList<>(LIMIT);
        int[] base = PROSPECAL;
        for (int i = 0; i < base.length; i++) {
            vs.add(OklabVectors.fromRGBA8888(new Vector3(), base[i]));
        }
        RGBA.addAll(base);
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
                RGBA.add(OklabVectors.toRGBA8888(next));
                continue ITERS;
            }
            // failed to find a valid color in 100 tries
            System.err.println("OH NO, FAILED ON ITERATION " + iter);
            throw new RuntimeException("SAD FACE :(");
        }

        StringBuilder sb = new StringBuilder(12 * RGBA.size + 35).append("{\n");
        for (int i = 0; i < RGBA.size; i++) {
            StringKit.appendHex(sb.append("0x"), RGBA.get(i)).append(", ");
            if(7 == (i & 7)) sb.append('\n');
        }
        System.out.println(sb.append('}'));

    }

}
