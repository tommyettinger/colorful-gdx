package com.github.tommyettinger.colorful.ipt;

import com.badlogic.gdx.math.Vector3;

import static com.github.tommyettinger.colorful.ipt.ColorTools.*;

public class SphereIPTWork {
    public static void main(String[] args) {
        float r = fromRGBA(1f, 0f, 0f, 1f);
        float ri = intensity(r), rp = protan(r) - 0.5f, rt = tritan(r) - 0.5f;
        float g = fromRGBA(0f, 1f, 0f, 1f);
        float gi = intensity(g), gp = protan(g) - 0.5f, gt = tritan(g) - 0.5f;
        float b = fromRGBA(0f, 0f, 1f, 1f);
        float bi = intensity(b), bp = protan(b) - 0.5f, bt = tritan(b) - 0.5f;
        Vector3 rv = new Vector3(ri, rp, rt);
        Vector3 gv = new Vector3(gi, gp, gt);
        Vector3 bv = new Vector3(bi, bp, bt);
        System.out.println(rv);
        System.out.println(gv);
        System.out.println(bv);
        Vector3 kv = new Vector3(0f, 0f, 0f);
        Vector3 wv = new Vector3(1f, 0f, 0f);
        Vector3 yellow, magenta, cyan;
        yellow  = new Vector3(0.16155326f,0.020876601f,-0.26078433f);
        magenta = new Vector3(-0.16136102f,0.122068435f,-0.070396f);
        cyan    = new Vector3(0.16420607f,0.3481738f,0.10495965f);

        System.out.println("Black to Red to Green to Yellow : " + yellow ); //(rv.cpy().crs(gv))
        System.out.println("Black to Red to Blue to Magenta : " + magenta); //(rv.cpy().crs(bv))
        System.out.println("Black to Blue to Green to Cyan  : " + cyan   ); //(gv.cpy().crs(bv))
    }
}
