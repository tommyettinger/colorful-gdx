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

package com.github.tommyettinger.colorful;

import com.badlogic.gdx.utils.IntArray;
import com.github.tommyettinger.colorful.internal.StringKit;
import com.github.tommyettinger.colorful.rgb.ColorTools;
import com.github.tommyettinger.digital.Interpolations;
import com.github.tommyettinger.digital.MathTools;

// example output
/*
 */

/**
 * The shape of this linear palette looks <a href="https://imgur.com/DsPrUDR">like this</a>, but with the thick central
 * section stretched to double height. It uses 8 tiny 3D Hilbert
 * curves, each 2x2x2, arranged around a central column that extends further up and down.
 */
public class StaccaPaletteGenerator {
    private static final int limit = 71;
    private static final IntArray rgba = new IntArray(limit);

    public static int autoAdjust(float hue, float sat, float lit) {
        return ColorTools.toRGBA8888(FloatColors.hsl2rgb(
                MathTools.barronSpline((float) Math.pow(MathTools.fract(hue + (0.5f - lit) * 0.3f), 1.6f), 1.25f, 0.4f),
//                (float) ((Math.sqrt(MathTools.fract(hue + (0.5f - lit) * 0.3f) + 0.050625) - 0.225) * 1.25),
//                hue + 0.008f,
                sat,
                Interpolations.smooth.apply(lit), 1f));
    }
    /*
jshell> //hue 0,1: -0.080, 1,1: +0.080, 0,2: -0.125, 1,2: 0.000
jshell> //saturation 0,1: 0.447, 1,1: 0.632, 0,2: 0.894, 1,2: 1.000
jshell> //lightness 0,1: 0.12f, 1,1: 0.10f, 0,2: 0.08f, 1,2: 0.06f
     */
    public static void main(String[] args) {
        rgba.add(0);

        rgba.add(ColorTools.toRGBA8888(FloatColors.hsl2rgb(0f, 0f, 0.0f, 1f)));
        rgba.add(ColorTools.toRGBA8888(FloatColors.hsl2rgb(0f, 0f, 0.2f, 1f)));
        rgba.add(ColorTools.toRGBA8888(FloatColors.hsl2rgb(0f, 0f, 0.4f, 1f)));

        rgba.add(autoAdjust(0.000f - 0.080f, 0.353f, 0.5000f + (0.12f * 0.90f - 0.12f * 0.60f)));
        rgba.add(autoAdjust(0.000f - 0.080f, 0.353f, 0.5000f + (0.12f * 0.90f + 0.12f * 0.60f)));
        rgba.add(autoAdjust(0.000f - 0.125f, 0.913f, 0.5000f + (0.08f * 0.90f + 0.08f * 0.60f)));
        rgba.add(autoAdjust(0.000f - 0.125f, 0.913f, 0.5000f + (0.08f * 0.90f - 0.08f * 0.60f)));
        rgba.add(autoAdjust(0.000f + 0.000f, 1.000f, 0.5000f + (0.06f * 0.90f - 0.06f * 0.60f)));
        rgba.add(autoAdjust(0.000f + 0.000f, 1.000f, 0.5000f + (0.06f * 0.90f + 0.06f * 0.60f)));
        rgba.add(autoAdjust(0.000f + 0.080f, 0.523f, 0.5000f + (0.10f * 0.90f + 0.10f * 0.60f)));
        rgba.add(autoAdjust(0.000f + 0.080f, 0.523f, 0.5000f + (0.10f * 0.90f - 0.10f * 0.60f)));

        rgba.add(autoAdjust(0.250f - 0.080f, 0.353f, 0.5000f + (0.12f * 0.90f - 0.12f * 0.60f)));
        rgba.add(autoAdjust(0.250f - 0.080f, 0.353f, 0.5000f + (0.12f * 0.90f + 0.12f * 0.60f)));
        rgba.add(autoAdjust(0.250f - 0.125f, 0.913f, 0.5000f + (0.08f * 0.90f + 0.08f * 0.60f)));
        rgba.add(autoAdjust(0.250f - 0.125f, 0.913f, 0.5000f + (0.08f * 0.90f - 0.08f * 0.60f)));
        rgba.add(autoAdjust(0.250f + 0.000f, 1.000f, 0.5000f + (0.06f * 0.90f - 0.06f * 0.60f)));
        rgba.add(autoAdjust(0.250f + 0.000f, 1.000f, 0.5000f + (0.06f * 0.90f + 0.06f * 0.60f)));
        rgba.add(autoAdjust(0.250f + 0.080f, 0.523f, 0.5000f + (0.10f * 0.90f + 0.10f * 0.60f)));
        rgba.add(autoAdjust(0.250f + 0.080f, 0.523f, 0.5000f + (0.10f * 0.90f - 0.10f * 0.60f)));

        rgba.add(autoAdjust(0.500f - 0.080f, 0.353f, 0.5000f + (0.12f * 0.90f - 0.12f * 0.60f)));
        rgba.add(autoAdjust(0.500f - 0.080f, 0.353f, 0.5000f + (0.12f * 0.90f + 0.12f * 0.60f)));
        rgba.add(autoAdjust(0.500f - 0.125f, 0.913f, 0.5000f + (0.08f * 0.90f + 0.08f * 0.60f)));
        rgba.add(autoAdjust(0.500f - 0.125f, 0.913f, 0.5000f + (0.08f * 0.90f - 0.08f * 0.60f)));
        rgba.add(autoAdjust(0.500f + 0.000f, 1.000f, 0.5000f + (0.06f * 0.90f - 0.06f * 0.60f)));
        rgba.add(autoAdjust(0.500f + 0.000f, 1.000f, 0.5000f + (0.06f * 0.90f + 0.06f * 0.60f)));
        rgba.add(autoAdjust(0.500f + 0.080f, 0.523f, 0.5000f + (0.10f * 0.90f + 0.10f * 0.60f)));
        rgba.add(autoAdjust(0.500f + 0.080f, 0.523f, 0.5000f + (0.10f * 0.90f - 0.10f * 0.60f)));

        rgba.add(autoAdjust(0.750f - 0.080f, 0.353f, 0.5000f + (0.12f * 0.90f - 0.12f * 0.60f)));
        rgba.add(autoAdjust(0.750f - 0.125f, 0.913f, 0.5000f + (0.08f * 0.90f - 0.08f * 0.60f)));
        rgba.add(autoAdjust(0.750f + 0.000f, 1.000f, 0.5000f + (0.06f * 0.90f - 0.06f * 0.60f)));
        rgba.add(autoAdjust(0.750f + 0.080f, 0.523f, 0.5000f + (0.10f * 0.90f - 0.10f * 0.60f)));
        rgba.add(autoAdjust(0.750f + 0.080f, 0.523f, 0.5000f + (0.10f * 0.90f + 0.10f * 0.60f)));
        rgba.add(autoAdjust(0.750f + 0.000f, 1.000f, 0.5000f + (0.06f * 0.90f + 0.06f * 0.60f)));
        rgba.add(autoAdjust(0.750f - 0.125f, 0.913f, 0.5000f + (0.08f * 0.90f + 0.08f * 0.60f)));
        rgba.add(autoAdjust(0.750f - 0.080f, 0.353f, 0.5000f + (0.12f * 0.90f + 0.12f * 0.60f)));


        rgba.add(autoAdjust(0.000f - 0.080f, 0.353f, 0.5000f - (0.12f * 0.90f - 0.12f * 0.60f)));
        rgba.add(autoAdjust(0.000f - 0.080f, 0.353f, 0.5000f - (0.12f * 0.90f + 0.12f * 0.60f)));
        rgba.add(autoAdjust(0.000f - 0.125f, 0.913f, 0.5000f - (0.08f * 0.90f + 0.08f * 0.60f)));
        rgba.add(autoAdjust(0.000f - 0.125f, 0.913f, 0.5000f - (0.08f * 0.90f - 0.08f * 0.60f)));
        rgba.add(autoAdjust(0.000f + 0.000f, 1.000f, 0.5000f - (0.06f * 0.90f - 0.06f * 0.60f)));
        rgba.add(autoAdjust(0.000f + 0.000f, 1.000f, 0.5000f - (0.06f * 0.90f + 0.06f * 0.60f)));
        rgba.add(autoAdjust(0.000f + 0.080f, 0.523f, 0.5000f - (0.10f * 0.90f + 0.10f * 0.60f)));
        rgba.add(autoAdjust(0.000f + 0.080f, 0.523f, 0.5000f - (0.10f * 0.90f - 0.10f * 0.60f)));

        rgba.add(autoAdjust(0.250f - 0.080f, 0.353f, 0.5000f - (0.12f * 0.90f - 0.12f * 0.60f)));
        rgba.add(autoAdjust(0.250f - 0.080f, 0.353f, 0.5000f - (0.12f * 0.90f + 0.12f * 0.60f)));
        rgba.add(autoAdjust(0.250f - 0.125f, 0.913f, 0.5000f - (0.08f * 0.90f + 0.08f * 0.60f)));
        rgba.add(autoAdjust(0.250f - 0.125f, 0.913f, 0.5000f - (0.08f * 0.90f - 0.08f * 0.60f)));
        rgba.add(autoAdjust(0.250f + 0.000f, 1.000f, 0.5000f - (0.06f * 0.90f - 0.06f * 0.60f)));
        rgba.add(autoAdjust(0.250f + 0.000f, 1.000f, 0.5000f - (0.06f * 0.90f + 0.06f * 0.60f)));
        rgba.add(autoAdjust(0.250f + 0.080f, 0.523f, 0.5000f - (0.10f * 0.90f + 0.10f * 0.60f)));
        rgba.add(autoAdjust(0.250f + 0.080f, 0.523f, 0.5000f - (0.10f * 0.90f - 0.10f * 0.60f)));

        rgba.add(autoAdjust(0.500f - 0.080f, 0.353f, 0.5000f - (0.12f * 0.90f - 0.12f * 0.60f)));
        rgba.add(autoAdjust(0.500f - 0.080f, 0.353f, 0.5000f - (0.12f * 0.90f + 0.12f * 0.60f)));
        rgba.add(autoAdjust(0.500f - 0.125f, 0.913f, 0.5000f - (0.08f * 0.90f + 0.08f * 0.60f)));
        rgba.add(autoAdjust(0.500f - 0.125f, 0.913f, 0.5000f - (0.08f * 0.90f - 0.08f * 0.60f)));
        rgba.add(autoAdjust(0.500f + 0.000f, 1.000f, 0.5000f - (0.06f * 0.90f - 0.06f * 0.60f)));
        rgba.add(autoAdjust(0.500f + 0.000f, 1.000f, 0.5000f - (0.06f * 0.90f + 0.06f * 0.60f)));
        rgba.add(autoAdjust(0.500f + 0.080f, 0.523f, 0.5000f - (0.10f * 0.90f + 0.10f * 0.60f)));
        rgba.add(autoAdjust(0.500f + 0.080f, 0.523f, 0.5000f - (0.10f * 0.90f - 0.10f * 0.60f)));

        rgba.add(autoAdjust(0.750f - 0.080f, 0.353f, 0.5000f - (0.12f * 0.90f - 0.12f * 0.60f)));
        rgba.add(autoAdjust(0.750f - 0.125f, 0.913f, 0.5000f - (0.08f * 0.90f - 0.08f * 0.60f)));
        rgba.add(autoAdjust(0.750f + 0.000f, 1.000f, 0.5000f - (0.06f * 0.90f - 0.06f * 0.60f)));
        rgba.add(autoAdjust(0.750f + 0.080f, 0.523f, 0.5000f - (0.10f * 0.90f - 0.10f * 0.60f)));
        rgba.add(autoAdjust(0.750f + 0.080f, 0.523f, 0.5000f - (0.10f * 0.90f + 0.10f * 0.60f)));
        rgba.add(autoAdjust(0.750f + 0.000f, 1.000f, 0.5000f - (0.06f * 0.90f + 0.06f * 0.60f)));
        rgba.add(autoAdjust(0.750f - 0.125f, 0.913f, 0.5000f - (0.08f * 0.90f + 0.08f * 0.60f)));
        rgba.add(autoAdjust(0.750f - 0.080f, 0.353f, 0.5000f - (0.12f * 0.90f + 0.12f * 0.60f)));

        rgba.add(ColorTools.toRGBA8888(FloatColors.hsl2rgb(0f, 0f, 0.6f, 1f)));
        rgba.add(ColorTools.toRGBA8888(FloatColors.hsl2rgb(0f, 0f, 0.8f, 1f)));
        rgba.add(ColorTools.toRGBA8888(FloatColors.hsl2rgb(0f, 0f, 1.0f, 1f)));

        StringBuilder sb = new StringBuilder(12 * rgba.size + 256).append("{\n");
        for (int i = 0; i < rgba.size; i++) {
            StringKit.appendHex(sb.append("0x"), rgba.get(i)).append(", ");
            if(7 == (i & 7)) sb.append('\n');
        }
        System.out.println(sb.append("\n}"));
    }
}
