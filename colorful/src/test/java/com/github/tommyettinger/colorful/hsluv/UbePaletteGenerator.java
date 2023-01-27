/*
 * Copyright (c) 2023 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.tommyettinger.colorful.hsluv;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.FloatArray;
import com.github.tommyettinger.colorful.TrigTools;
import com.github.tommyettinger.colorful.internal.StringKit;

import java.util.ArrayList;
import java.util.Collections;

import static com.badlogic.gdx.math.MathUtils.floorPositive;
import static com.badlogic.gdx.math.MathUtils.lerp;
import static com.github.tommyettinger.anim8.OtherMath.barronSpline;
import static com.github.tommyettinger.colorful.rgb.ColorTools.*;
import static com.github.tommyettinger.colorful.rgb.SimplePalette.*;

public class UbePaletteGenerator extends ApplicationAdapter {

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Ube Palette Generator");
        config.setWindowedMode(320, 320);
        config.setIdleFPS(1);
        config.setResizable(false);
        new Lwjgl3Application(new UbePaletteGenerator(), config);
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
        d = fromTurns + progress * (d - MathUtils.floor(d) - 0.5f);
        return d - MathUtils.floor(d);
    }

    public void create(){
        float[] coreHues = new float[]{
                hue(RED),
                hue(BROWN),
                hue(ORANGE),
                hue(BRONZE),
                hue(YELLOW),
                hue(LIME),
                hue(JADE),
                hue(CYAN),
                hue(BLUE),
                hue(VIOLET),
                hue(PURPLE),
                hue(MAGENTA),
        }, hueKeys;
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
        }, nameKeys, levelNames;
        FloatArray pal = new FloatArray(256);
        ArrayList<String> names = new ArrayList<>(256);
        pal.add(TRANSPARENT);
        names.add("transparent");
        for (int i = 0; i < 15; i++) {
            pal.add(floatGetHSL(0.1f, 0f, i / 14f, 1f));
        }
        Collections.addAll(names, "pure black", "almost black", "lead black",
                "black lead", "pure lead", "gray lead",
                "lead gray", "pure gray", "silver gray",
                "gray silver", "pure silver", "white silver",
                "silver white", "almost white", "pure white");
        for (int wave = 1; wave <= 4; wave++) {
            int crest;
            switch (wave){
                case 1:
                    crest = 5;
                    hueKeys = new float[12];
                    System.arraycopy(coreHues, 0, hueKeys, 0, 12);
                    nameKeys = new String[12];
                    System.arraycopy(hueNames, 0, nameKeys, 0, 12);
                    levelNames = new String[]{"black ", "lead ", "gray ", "silver ", "white "};
                    break;
                case 2:
                    crest = 3;
                    hueKeys = new float[12];
                    System.arraycopy(coreHues, 0, hueKeys, 0, 12);
                    nameKeys = new String[12];
                    System.arraycopy(hueNames, 0, nameKeys, 0, 12);
                    levelNames = new String[]{"drab ", "faded ", "pale ", };
                    break;
                case 3:
                    crest = 3;
                    hueKeys = new float[36];
                    nameKeys = new String[36];
                    levelNames = new String[]{"deep ", "true ", "bright "};
                    for (int i = 0, c = -1; i < 12; i++) {
                        hueKeys[++c] = lerpAngle_(coreHues[i], coreHues[(i+1)%12], 0f);
                        nameKeys[c] = "pure " + hueNames[i];
                        hueKeys[++c] = lerpAngle_(coreHues[i], coreHues[(i+1)%12], 1f/3f);
                        nameKeys[c] = hueNames[(i+1)%12] + ' ' + hueNames[i];
                        hueKeys[++c] = lerpAngle_(coreHues[i], coreHues[(i+1)%12], 2f/3f);
                        nameKeys[c] = hueNames[i] + ' ' + hueNames[(i+1)%12];
                    }
                    break;
                default:
                    crest = 1;
                    hueKeys = new float[36];
                    nameKeys = new String[36];
                    levelNames = new String[]{"bold "};
                    for (int i = 0, c = -1; i < 12; i++) {
                        hueKeys[++c] = lerpAngle_(coreHues[i], coreHues[(i+1)%12], 0f);
                        nameKeys[c] = "pure " + hueNames[i];
                        hueKeys[++c] = lerpAngle_(coreHues[i], coreHues[(i+1)%12], 1f/3f);
                        nameKeys[c] = hueNames[(i+1)%12] + ' ' + hueNames[i];
                        hueKeys[++c] = lerpAngle_(coreHues[i], coreHues[(i+1)%12], 2f/3f);
                        nameKeys[c] = hueNames[i] + ' ' + hueNames[(i+1)%12];
                    }
                    break;
            }
            for (int i = 0; i < hueKeys.length; i++) {
                float hue = hueKeys[i], quart = barronSpline(wave * 0.25f, 0.5f, 0.95f),
                        lightAdjust = 1f,
                        satAdjust = (hue >= (0.08f) && hue < (0.16f) ? 1f - TrigTools.sinTurns((hue - 0.08f) * 0.5f / (0.16f - 0.08f)) * 0.4f : 1.0f) * (1f - (i & 1) * 0.1f);
                int chroma = 255, outerLight = 128;
                float outerC = 1f, outerL = outerLight / 255f;

                int minLight = 32;
                float minL = minLight / 255f;

                int maxLight = 255 - 32;
                float maxL = lightAdjust;
                for (int j = 0, cr = 1; j < crest; j++, cr += 2) {
                    if(crest == 1)
                        pal.add(floatGetHSL(hue, outerC * satAdjust, outerL, 1f));
                    else
                        pal.add(floatGetHSL(hue, lerp(0.04f, outerC, quart) * satAdjust, lerp(minL, maxL,
                                barronSpline(0.2f + 0.75f * (cr / (crest * 2f)), 0.75f, 0.2f + 0.6f * (i * 0.6180339887498949f - floorPositive(i * 0.6180339887498949f)))), 1f));
                    names.add(levelNames[j] + nameKeys[i]);
                }
            }
        }
        double lowestDistance = Double.POSITIVE_INFINITY;
        float worst0 = 0f, worst1 = 0f;
        int worstIndex0 = 0, worstIndex1 = 0;
        for (int i = 1; i < pal.size; i++) {
            float color0 = pal.get(i);
            double R0 = red(color0), G0 = green(color0), B0 = blue(color0);
            for (int j = i + 1; j < pal.size; j++) {
                float color1 = pal.get(j);
                double R1 = red(color1) - R0, G1 = green(color1) - G0, B1 = blue(color1) - B0;
                if(lowestDistance != (lowestDistance = Math.min(lowestDistance, R1 * R1 + G1 * G1 + B1 * B1))){
                    worst0 = color0;
                    worstIndex0 = i;
                    worst1 = color1;
                    worstIndex1 = j;
                }
            }
        }

        System.out.printf("Worst distance was %1.8f between colors %d and %d: R=%.4f G=%.4f B=%.4f and R=%.4f G=%.4f B=%.4f\n",
                lowestDistance, worstIndex0, worstIndex1,
                red(worst0), green(worst0), blue(worst0),
                red(worst1), green(worst1), blue(worst1));

        StringBuilder sb = new StringBuilder(24 * pal.size + 90).append("{\n");
        for (int i = 0; i < pal.size; i++) {
            StringKit.appendHex(sb.append("0x"), toRGBA8888(pal.get(i))).append(", ");
            if(7 == (i & 7)) sb.append('\n');
        }

        sb.append("}\n\nRGB:\n{\n0x00000000, ");
        for (int i = 1; i < pal.size; i++) {
            StringKit.appendHex(sb, toRGBA8888(pal.get(i)));
            sb.append(", ");
            if(7 == (i & 7)) sb.append('\n');
        }

        sb.append("}\n\nNamed:\n{\n");
        StringBuilder sb2 = new StringBuilder(8192);
        sb2.append("UBE_");
        for (int i = 0; i < pal.size; i++) {
            sb2.append(names.get(i).toUpperCase().replace(' ', '_')).append('\t');
            StringKit.appendHex(sb2, toRGBA8888(pal.get(i)));
            sb2.append('\t').append(names.get(i)).append('\n');
        }
        sb2.setLength(sb2.length() - 1);
        Gdx.files.local("UbeColorData.txt").writeString(sb2.toString(), false, "UTF8");

        System.out.println(sb.append(sb2).append("\n}"));

        Gdx.app.exit();

    }
}
