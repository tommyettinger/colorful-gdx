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

package com.github.tommyettinger.colorful.oklab.internal;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.github.tommyettinger.colorful.internal.StringKit;

import static com.github.tommyettinger.colorful.oklab.ColorTools.*;

/**
 * This uses data provided by Paul Centore for the ISCC-NBS color names and their similar sRGB colors. These colors are
 * related closely to the Munsell color system, but I'm not sure exactly how ISCC-NBS ties in. The 260 colors (plus
 * transparent) are a good size to incorporate here, though.
 * <a href="https://www.munsellcolourscienceforpainters.com/MunsellAndKubelkaMunkToolbox/MunsellAndKubelkaMunkToolbox.html">That data, and other Munsell-related color code, is available here.</a>
 */
public class MunsellBrightener extends ApplicationAdapter {

    public static void tabSplit(String[] receiving, String source) {
        int dl = 1, idx = -1, idx2;
        for (int i = 0; i < 2; i++) {
            receiving[i] = StringKit.safeSubstring(source, idx+dl, idx = source.indexOf('\t', idx+dl));
        }
        if((idx2 = source.indexOf('\t', idx+dl)) < 0)
        {
            receiving[2] = StringKit.safeSubstring(source, idx+dl, source.length());
        }
        else
        {
            receiving[2] = StringKit.safeSubstring(source, idx+dl, idx2);
        }
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Munsell Brightener");
        config.setIdleFPS(1);
        config.useVsync(true);

//        float c = SimplePalette.BLUE;
//        System.out.printf("%08X Original, H %f, S %f, L %f, A %f, B %f\n", ColorTools.toRGBA8888(c), oklabHue(c), oklabSaturation(c), oklabLightness(c), channelA(c), channelB(c));
//        c = ColorTools.fromRGBA8888(ColorTools.toRGBA8888(c));
//        System.out.printf("%08X Round Trip, H %f, S %f, L %f, A %f, B %f\n", ColorTools.toRGBA8888(c), oklabHue(c), oklabSaturation(c), oklabLightness(c), channelA(c), channelB(c));
//        c = oklabByHSL(oklabHue(c), oklabSaturation(c), oklabLightness(c), alpha(c));
//        System.out.printf("%08X Reconstructed, H %f, S %f, L %f, A %f, B %f\n", ColorTools.toRGBA8888(c), oklabHue(c), oklabSaturation(c), oklabLightness(c), channelA(c), channelB(c));
//        c = oklabByHSL(oklabHue(c), (float) Math.pow(oklabSaturation(c), 0.75) * 1.047f, oklabLightness(c), alpha(c));
//        System.out.printf("%08X Saturated, H %f, S %f, L %f, A %f, B %f\n", ColorTools.toRGBA8888(c), oklabHue(c), oklabSaturation(c), oklabLightness(c), channelA(c), channelB(c));


        new Lwjgl3Application(new MunsellBrightener(), config);
    }
    public void create() {
        float c;
        String data = Gdx.files.classpath("ISCCNBSData.txt").readString();
        String[] lines = StringKit.split(data, "\n"), rec = new String[3];
        StringBuilder sb = new StringBuilder(100000);

        for (int i = 0; i < lines.length; i++) {
            tabSplit(rec, lines[i]);
            c = fromRGBA8888(StringKit.intFromHex(rec[1]));
            c = oklabByHSL(oklabHue(c), (float) Math.pow(oklabSaturation(c), 0.75) * 1.047f, (float) Math.pow(oklabLightness(c), 0.8f), alpha(c));
            sb.append(rec[0]).append('\t').append(StringKit.hex(toRGBA8888(c)).toUpperCase()).append('\t').append(rec[2]).append('\n');
        }
        sb.setLength(sb.length() - 1);
        Gdx.files.local("BrighterMunsell.txt").writeString(sb.toString(), false);

        Gdx.app.exit();
    }
}
