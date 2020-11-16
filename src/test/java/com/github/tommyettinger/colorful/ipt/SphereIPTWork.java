package com.github.tommyettinger.colorful.ipt;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.NumberUtils;

import static com.github.tommyettinger.colorful.ipt.ColorTools.*;

public class SphereIPTWork {
    public static final Vector3 yellow  = new Vector3(0.16155326f,0.020876605f,-0.26078433f);
    public static final Vector3 magenta = new Vector3(-0.16136102f,0.122068435f,-0.070396f);
    public static final Vector3 cyan    = new Vector3(0.16420607f,0.3481738f,0.104959644f);

    public static Vector3 plump(Vector3 original, Vector3 buffer) {
        final float crMid = cyan.y * original.y + cyan.z * original.z;
        final float crScale = (original.x - 0.5f + (NumberUtils.floatToRawIntBits(crMid) >>> 31)) * cyan.x / -crMid;
        final float mgMid = magenta.y * original.y + magenta.z * original.z;
        final float mgScale = (original.x + 0.5f - (NumberUtils.floatToRawIntBits(mgMid) >>> 31)) * magenta.x / -mgMid;
        final float ybMid = yellow.y * original.y + yellow.z * original.z;
        final float ybScale = (original.x - 0.5f + (NumberUtils.floatToRawIntBits(ybMid) >>> 31)) * yellow.x / -ybMid;
        final float scale = Math.max(crScale, Math.max(mgScale, ybScale));
        final float d = MathUtils.cos(3.14159f * original.x) * 0.5f / Vector2.len(original.y * scale, original.z * scale);
        return buffer.set(original.x, original.y * d, original.z * d);
    }

    public static Vector3 thin(Vector3 original, Vector3 buffer) {
        final float crMid = cyan.y * original.y + cyan.z * original.z;
        final float crScale = (original.x - 0.5f + (NumberUtils.floatToRawIntBits(crMid) >>> 31)) * cyan.x / -crMid;
        final float mgMid = magenta.y * original.y + magenta.z * original.z;
        final float mgScale = (original.x + 0.5f - (NumberUtils.floatToRawIntBits(mgMid) >>> 31)) * magenta.x / -mgMid;
        final float ybMid = yellow.y * original.y + yellow.z * original.z;
        final float ybScale = (original.x - 0.5f + (NumberUtils.floatToRawIntBits(ybMid) >>> 31)) * yellow.x / -ybMid;
        final float scale = Math.max(crScale, Math.max(mgScale, ybScale));
        final float d = 2f * Vector2.len(original.y * scale, original.z * scale) / MathUtils.cos(3.14159f * original.x);
        return buffer.set(original.x, original.y * d, original.z * d);
    }

    public static void main(String[] args) {
        float r = fromRGBA(1f, 0f, 0f, 1f);
        float ri = intensity(r) - 0.5f, rp = protan(r) - 0.5f, rt = tritan(r) - 0.5f;
        float g = fromRGBA(0f, 1f, 0f, 1f);
        float gi = intensity(g) - 0.5f, gp = protan(g) - 0.5f, gt = tritan(g) - 0.5f;
        float b = fromRGBA(0f, 0f, 1f, 1f);
        float bi = intensity(b) - 0.5f, bp = protan(b) - 0.5f, bt = tritan(b) - 0.5f;
        Vector3 rv = new Vector3(ri, rp, rt);
        Vector3 gv = new Vector3(gi, gp, gt);
        Vector3 bv = new Vector3(bi, bp, bt);
        System.out.println(rv);
        System.out.println(gv);
        System.out.println(bv);
        System.out.println();
        Vector3 kv = new Vector3(-0.5f, 0f, 0f);
        Vector3 wv = new Vector3(0.5f , 0f, 0f);
        Vector3 buffer = new Vector3(), buffer2 = new Vector3();
        Vector3 testPoint = new Vector3();
        for (int i = 0; i < 256; i++) {
            String name = Palette.NAMES.get(i);
            float color = Palette.NAMED.get(name, 0f);
            if(ColorTools.alphaInt(color) < 128) continue;
            testPoint.set(intensity(color) - 0.5f, protan(color) - 0.5f, tritan(color) - 0.5f);
            plump(testPoint, buffer);
            thin(buffer, buffer2);
            if(!testPoint.epsilonEquals(buffer2, 0.01f))
                System.out.printf("%s is bad! before: %s, after: %s\n", name, testPoint, buffer2);
        }

//        System.out.printf("public static final Vector3 yellow  = new Vector3%s;\n", (rv.cpy().sub(kv).crs(gv.cpy().sub(kv))));
//        System.out.printf("public static final Vector3 magenta = new Vector3%s;\n", (rv.cpy().sub(kv).crs(bv.cpy().sub(kv))));
//        System.out.printf("public static final Vector3 cyan    = new Vector3%s;\n", (gv.cpy().sub(kv).crs(bv.cpy().sub(kv))));

        
    }
}
