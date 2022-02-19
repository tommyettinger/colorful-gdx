package com.github.tommyettinger.colorful.oklab;

import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.IntSet;

import java.util.Arrays;

/**
 * Reorders the Yam2 palette to try to maximize visual contrast between successive or nearby colors in the palette.
 */
public class Yam2Scrambler {
    /**
     * Mutates the array arr by switching the contents at pos1 and pos2.
     * @param arr an array of int; must not be null
     * @param pos1 an index into arr; must be at least 0 and no greater than arr.length
     * @param pos2 an index into arr; must be at least 0 and no greater than arr.length
     */
    protected static void swap(int[] arr, int pos1, int pos2) {
        final int tmp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = tmp;
    }

    /**
     * Shuffles an array in-place using the Fisher-Yates algorithm.
     * <br>
     * <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">Wikipedia has more on this algorithm</a>.
     *
     * @param r random number generator
     * @param elements an array of int; <b>will</b> be modified
     * @return elements after shuffling it in-place
     */
    public static int[] shuffleInPlace(RandomXS128 r, int[] elements) {
        final int size = elements.length;
        for (int i = size; i > 1; i--) {
            swap(elements, i - 1, r.nextInt(i));
        }
        return elements;
    }

    public static void main(String[] args){
        String[] triangles = new String[]{"darker gray a", "bright a b", "lighter gray a", "drab b a", "more b a", "pale a b", "deep b a", "light gray a", "some a b", "dull b a", "deep a b", "some b a", "dull a b", "dark gray a", "bright b a", "drab a b", "more a b", "deep a", "pale b a", "bright a"};
        String[] hueNames = new String[]{
                "red",
                "brown",
                "orange",
                "saffron",
                "yellow",
                "lime",
                "green",
                "cyan",
                "blue",
                "violet",
                "purple",
                "magenta",
        };
        int[] distinction = new int[240];
        int hue = 0, inc = 9;
        for (int i = 0; i < 240; i++) {
            String name = triangles[i % 20].replaceFirst("\\ba\\b", hueNames[hue]).replaceFirst("\\bb\\b", hueNames[(hue + 1) % 12]);
            System.out.printf("0x%08X, ", distinction[i] = ColorTools.toRGBA8888(Yam2Palette.NAMED.get(name, 0f)));
            if((i & 7) == 7) System.out.println();
            if((i % 20) == 19) ++hue;
            if((hue = hue + inc) >= 12){
                hue %= 12;
//                inc += 2;
            }
        }
        System.out.println(IntSet.with(distinction).size);
        IntArray ints = IntArray.with(distinction),
                grays = IntArray.with(
                        0x111111FF,
                        0x222222FF,
                        0x333333FF,
                        0x444444FF,
                        0x555555FF,
                        0x666666FF,
                        0x777777FF,
                        0x888888FF,
                        0x999999FF,
                        0xAAAAAAFF,
                        0xBBBBBBFF,
                        0xCCCCCCFF,
                        0xDDDDDDFF,
                        0xEEEEEEFF
                        );
        FloatArray labs = new FloatArray(14);
        for (int i = 0; i < grays.size; i++) {
            labs.add(ColorTools.fromRGBA8888(grays.get(i)));
        }
        ints.add(0x000000FF);
        for (int i = 224; i >= 16; i -= 16) {
            float before = ColorTools.fromRGBA8888(ints.get(i));
            float after = ColorTools.fromRGBA8888(ints.get(i+1));
            double distance = -1.0;
            int best = 0;
            for (int j = 0; j < labs.size; j++) {
                float g = labs.get(j);
                float l = ColorTools.channelL(before) - ColorTools.channelL(g);
                float a = ColorTools.channelA(before) - ColorTools.channelA(g);
                float b = ColorTools.channelB(before) - ColorTools.channelB(g);
                double bf = Math.sqrt(l * l + a * a + b * b);
                l = ColorTools.channelL(after) - ColorTools.channelL(g);
                a = ColorTools.channelA(after) - ColorTools.channelA(g);
                b = ColorTools.channelB(after) - ColorTools.channelB(g);
                double af = Math.sqrt(l * l + a * a + b * b);
                if(distance != (distance = Math.max(distance, Math.min(bf, af))))
                    best = j;
            }
            ints.insert(i, grays.get(best));
            labs.removeIndex(best);
            grays.removeIndex(best);
        }
        ints.insert(0, 0xFFFFFFFF);

        for (int i = 0; i < ints.size; i++) {
            System.out.printf("0x%08X, ", ints.get(i));
            if((i & 7) == 7) System.out.println();
        }
    }
}
