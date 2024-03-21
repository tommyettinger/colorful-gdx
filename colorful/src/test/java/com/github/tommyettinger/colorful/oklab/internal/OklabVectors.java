package com.github.tommyettinger.colorful.oklab.internal;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.NumberUtils;
import com.github.tommyettinger.colorful.oklab.ColorTools;

import java.util.Random;

import static com.github.tommyettinger.colorful.oklab.ColorTools.*;

public final class OklabVectors {
    private OklabVectors() {}

    public static Vector3 fromOklab(Vector3 filling, float oklab) {
        return filling.set(ColorTools.channelL(oklab), (ColorTools.channelA(oklab) - 0.5f) * 2f, (ColorTools.channelB(oklab) - 0.5f) * 2f);
    }

    public static Vector3 fromRGBA(Vector3 filling, float rgba) {
        final int abgr = NumberUtils.floatToRawIntBits(rgba);
        final float r = forwardGamma((abgr & 0xFF) * 0x1.010101010101p-8f);
        final float g = forwardGamma((abgr >>> 8 & 0xFF) * 0x1.010101010101p-8f);
        final float b = forwardGamma((abgr >>> 16 & 0xFF) * 0x1.010101010101p-8f);
        final float l = cbrtPositive(0.4121656120f * r + 0.5362752080f * g + 0.0514575653f * b);
        final float m = cbrtPositive(0.2118591070f * r + 0.6807189584f * g + 0.1074065790f * b);
        final float s = cbrtPositive(0.0883097947f * r + 0.2818474174f * g + 0.6302613616f * b);
        filling.set(
                Math.min(Math.max((forwardLight(0.2104542553f * l + 0.7936177850f * m - 0.0040720468f * s)), 0f), 1f),
                Math.min(Math.max(((1.9779984951f * l - 2.4285922050f * m + 0.4505937099f * s)), -1f), 1f),
                Math.min(Math.max(((0.0259040371f * l + 0.7827717662f * m - 0.8086757660f * s)), -1f), 1f)
        );
        return filling;
    }

    public static Vector3 fromRGBA8888(Vector3 filling, int rgba) {
        final float r = forwardGamma((rgba >>> 24) * 0x1.010101010101p-8f);
        final float g = forwardGamma((rgba >>> 16 & 0xFF) * 0x1.010101010101p-8f);
        final float b = forwardGamma((rgba >>> 8 & 0xFF) * 0x1.010101010101p-8f);
        final float l = cbrtPositive(0.4121656120f * r + 0.5362752080f * g + 0.0514575653f * b);
        final float m = cbrtPositive(0.2118591070f * r + 0.6807189584f * g + 0.1074065790f * b);
        final float s = cbrtPositive(0.0883097947f * r + 0.2818474174f * g + 0.6302613616f * b);
        filling.set(
                Math.min(Math.max((forwardLight(0.2104542553f * l + 0.7936177850f * m - 0.0040720468f * s)), 0f), 1f),
                Math.min(Math.max(((1.9779984951f * l - 2.4285922050f * m + 0.4505937099f * s)), -1f), 1f),
                Math.min(Math.max(((0.0259040371f * l + 0.7827717662f * m - 0.8086757660f * s)), -1f), 1f)
        );
        return filling;
    }

    public static Vector3 randomChangeIfValid(Vector3 filling, Vector3 original, float change, Random random){
        if(random == null)
            random = MathUtils.random;
        // set to random direction, using random
        float u = random.nextFloat();
        float v = random.nextFloat();
        float theta = MathUtils.PI2 * u;
        float phi = MathUtils.acos(v + v - 1f);
        filling.setFromSpherical(theta, phi);
        // scale the translation to match change, add it with original
        filling.scl(change).add(original);
        // if the modified color is in-gamut, return it
        if(inGamut(filling.x, (filling.y * 0.5f) + 0.5f, (filling.z * 0.5f) + 0.5f))
            return filling;
        return null;
    }

    private static float cube(final float x) {
        return x * x * x;
    }

    public static int toRGBA8888(final Vector3 oklab)
    {
        final float L = reverseLight(oklab.x);
        final float A = oklab.y;
        final float B = oklab.z;
        final float l = cube(L + 0.3963377774f * A + 0.2158037573f * B);
        final float m = cube(L - 0.1055613458f * A - 0.0638541728f * B);
        final float s = cube(L - 0.0894841775f * A - 1.2914855480f * B);
        final int r = (int)(reverseGamma(Math.min(Math.max(+4.0767245293f * l - 3.3072168827f * m + 0.2307590544f * s, 0f), 1f)) * 255.999f);
        final int g = (int)(reverseGamma(Math.min(Math.max(-1.2681437731f * l + 2.6093323231f * m - 0.3411344290f * s, 0f), 1f)) * 255.999f);
        final int b = (int)(reverseGamma(Math.min(Math.max(-0.0041119885f * l - 0.7034763098f * m + 1.7068625689f * s, 0f), 1f)) * 255.999f);
        return r << 24 | g << 16 | b << 8 | 0xFF;
    }

}
