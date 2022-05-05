package com.github.tommyettinger.colorful.hsluv;

public class BasicChecks {
    public static void main(String[] args) {
        float mx = 0f, highest = 0f;
        for (float h = 0x1p-8f; h < 1f; h += 0x1p-8f) {
            for (float li = 0x1p-8f; li < 1f; li += 0x1p-8f) {
                float lim = ColorTools.chromaLimit(h, li);
                if(mx != (mx = Math.max(mx, lim)))
                    highest = h;
            }
        }
        System.out.println("Max chroma limit is at " + highest + ", with: " + mx); // furthest hue is 0.03515625, with 1.7629014
    }
}
