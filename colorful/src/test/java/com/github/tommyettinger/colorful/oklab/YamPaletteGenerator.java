package com.github.tommyettinger.colorful.oklab;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.FloatArray;
import com.github.tommyettinger.colorful.internal.StringKit;

import static com.badlogic.gdx.math.MathUtils.lerp;
import static com.github.tommyettinger.colorful.oklab.ColorTools.*;
import static com.github.tommyettinger.colorful.oklab.SimplePalette.*;

public class YamPaletteGenerator {
    /**
     * Like {@link Math#floor(double)}, but takes a float and returns an int.
     * Doesn't consider "weird floats" like INFINITY and NaN.
     * @param t the float to find the floor for
     * @return the floor of t, as an int
     */
    public static int fastFloor(float t) {
        return t >= 0f ? (int) t : (int) t - 1;
    }

    /** Linearly interpolates between two angles in turns. Takes into account that angles wrap at 1.0 and always takes
     * the direction with the smallest delta angle.
     *
     * @param fromTurns start angle in turns
     * @param toTurns target angle in turns
     * @param progress interpolation value in the range [0, 1]
     * @return the interpolated angle in the range [0, 1) */
    public static float lerpAngle_ (float fromTurns, float toTurns, float progress) {
        float d = toTurns - fromTurns + 0.5f;
        d = fromTurns + progress * (d - fastFloor(d) - 0.5f);
        return d - fastFloor(d);
    }

    public static void main(String[] args){
        float[] coreHues = new float[]{
                oklabHue(RED),
                oklabHue(BROWN),
                oklabHue(ORANGE),
                oklabHue(BRONZE),
                oklabHue(YELLOW),
                oklabHue(CHARTREUSE),
                oklabHue(GREEN),
                oklabHue(CYAN),
                oklabHue(BLUE),
                oklabHue(VIOLET),
                oklabHue(PURPLE),
                oklabHue(MAGENTA),
        }, hueKeys;
        FloatArray pal = new FloatArray(256);
        pal.add(TRANSPARENT);
        for (int i = 0; i < 15; i++) {
            pal.add(oklabByHCL(0.1f, 0.01f, i / 14f, 1f));
        }
        for (int wave = 1; wave <= 4; wave++) {
            int crest = 5 - wave;
            switch (wave){
                case 1:
                    hueKeys = new float[12];
                    System.arraycopy(coreHues, 0, hueKeys, 0, 12);
                    break;
                case 2:
                    hueKeys = new float[24];
                    for (int i = 0, c = 0; i < 12; i++) {
                        hueKeys[c++] = lerpAngle_(coreHues[i], coreHues[(i+1)%12], 1f/4f);
                        hueKeys[c++] = lerpAngle_(coreHues[i], coreHues[(i+1)%12], 3f/4f);
                    }
                    break;
                case 3:
                    hueKeys = new float[36];
                    for (int i = 0, c = 0; i < 12; i++) {
                        hueKeys[c++] = lerpAngle_(coreHues[i], coreHues[(i+1)%12], 0f);
                        hueKeys[c++] = lerpAngle_(coreHues[i], coreHues[(i+1)%12], 1f/3f);
                        hueKeys[c++] = lerpAngle_(coreHues[i], coreHues[(i+1)%12], 2f/3f);
                    }
                    break;
                default:
                    hueKeys = new float[48];
                    for (int i = 0, c = 0; i < 12; i++) {
                        hueKeys[c++] = lerpAngle_(coreHues[i], coreHues[(i+1)%12], 1f/8f);
                        hueKeys[c++] = lerpAngle_(coreHues[i], coreHues[(i+1)%12], 3f/8f);
                        hueKeys[c++] = lerpAngle_(coreHues[i], coreHues[(i+1)%12], 5f/8f);
                        hueKeys[c++] = lerpAngle_(coreHues[i], coreHues[(i+1)%12], 7f/8f);
                    }
                    break;
            }
            for (int i = 0; i < hueKeys.length; i++) {
                float hue = hueKeys[i], quart = wave * 0.25f;
                int chroma = 0, outerLight = 0;
                for (int l = 0, gamut = (int) (hue * 256f); l < 256; l++, gamut += 256) {
                    if (chroma != (chroma = Math.max(chroma, ColorTools.getRawGamutValue(gamut))))
                        outerLight = l;
                }
                float outerC = chroma * 0x1p-8f, outerL = outerLight / 255f;

                int minLight = 0;
                for (int l = 0, gamut = (int) (hue * 256f); l < 256; l++, gamut += 256) {
                    if (ColorTools.getRawGamutValue(gamut) * 0x1p-8f >= outerC * quart) {
                        minLight = l;
                        break;
                    }
                }
                float minL = minLight / 255f;

                int maxLight = 255;
                for (int l = 255, gamut = 0xFF00 | (int) (hue * 256f); l >= 0; l--, gamut -= 256) {
                    if (ColorTools.getRawGamutValue(gamut) * 0x1p-8f >= outerC * quart) {
                        maxLight = l;
                        break;
                    }
                }
                float maxL = maxLight / 255f;
                for (int j = 0, cr = 1; j < crest; j++, cr += 2) {
                    pal.add(oklabByHCL(hue, outerC * quart, lerp(minL, maxL, cr / (crest * 2f)), 1f));
                }
            }
        }

        StringBuilder sb = new StringBuilder(12 * pal.size + 35).append("{\n");
        for (int i = 0; i < pal.size; i++) {
            StringKit.appendHex(sb.append("0x"), toRGBA8888(pal.get(i))).append(", ");
            if(7 == (i & 7)) sb.append('\n');
        }
        System.out.println(sb.append('}'));

    }
}
