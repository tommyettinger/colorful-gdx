package com.github.tommyettinger.colorful.ycwcm.internal;

import com.badlogic.gdx.math.Vector3;

import static com.github.tommyettinger.colorful.ycwcm.ColorTools.*;

public class YcwCmWork {
    public static void main(String[] args) {
        float r = fromRGBA(1f, 0f, 0f, 1f);
        float ry = luma(r), rw = chromaWarm(r) - 0.5f, rm = chromaMild(r) - 0.5f;
        float g = fromRGBA(0f, 1f, 0f, 1f);
        float gy = luma(g), gw = chromaWarm(g) - 0.5f, gm = chromaMild(g) - 0.5f;
        float b = fromRGBA(0f, 0f, 1f, 1f);
        float by = luma(b), bw = chromaWarm(b) - 0.5f, bm = chromaMild(b) - 0.5f;
        Vector3 rv = new Vector3(ry, rw, rm);
        Vector3 gv = new Vector3(gy, gw, gm);
        Vector3 bv = new Vector3(by, bw, bm);
        System.out.println(rv);
        System.out.println(gv);
        System.out.println(bv);
        System.out.println();
    }
}