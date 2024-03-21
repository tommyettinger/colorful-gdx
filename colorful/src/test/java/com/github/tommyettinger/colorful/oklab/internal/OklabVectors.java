package com.github.tommyettinger.colorful.oklab.internal;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.NumberUtils;
import com.github.tommyettinger.colorful.oklab.ColorTools;

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
}
