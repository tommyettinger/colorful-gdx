package com.github.tommyettinger.colorful.oklab;

import com.badlogic.gdx.math.RandomXS128;
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
        int[] indices = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        RandomXS128 r = new RandomXS128(12345);
        shuffleInPlace(r, indices);
        int[] distinction = new int[240];
        int hue = 0;
        for (int i = 0; i < 240; i++) {
            String name = triangles[i % 20].replaceFirst("\\ba\\b", hueNames[indices[hue]]).replaceFirst("\\bb\\b", hueNames[(indices[hue] + 1) % 12]);
            System.out.printf("0x%08X, ", distinction[i] = ColorTools.toRGBA8888(Yam2Palette.NAMED.get(name, 0f)));
            if((i & 7) == 7) System.out.println();
            if(++hue == 12){
                hue = 0;
                shuffleInPlace(r, indices);
            }
        }
        System.out.println(IntSet.with(distinction).size);
    }
}
