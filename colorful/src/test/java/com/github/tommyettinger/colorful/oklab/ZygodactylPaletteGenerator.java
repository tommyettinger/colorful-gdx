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

package com.github.tommyettinger.colorful.oklab;

import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.IntArray;
import com.github.tommyettinger.colorful.internal.StringKit;

// example output
/*
{
0x00000000, 0xC37F75FF, 0x8A3F39FF, 0xFF6451FF, 0xD8000BFF, 0xDFC09AFF, 0x9A7D56FF, 0xFFC262FF,
0xDC8C00FF, 0xF7FAC0FF, 0xACB16EFF, 0xF5FF64FF, 0xD2DC00FF, 0x7BA17AFF, 0x3B683FFF, 0x60C55AFF,
0x098A00FF, 0x4466A9FF, 0x132E76FF, 0x276EFFFF, 0x062FADFF, 0x8F7DB6FF, 0x564383FF, 0xB377FFFF,
0x7F00F7FF, 0xFF8548FF, 0xAA5327FF, 0xFDDD5FFF, 0xAC9637FF, 0xA5DB53FF, 0x69922DFF, 0x008DB8FF,
0x00516FFF, 0x6459FFFF, 0x372AA5FF, 0xCF64A4FF, 0x813663FF,
}
 */
public class ZygodactylPaletteGenerator {
    private static final int limit = 33;
    private static final IntArray rgba = new IntArray(limit);

    public static void main(String[] args) {
//        System.out.printf("%08X, %1.4f, %08X\n", 0xFF0000FF, ColorTools.chroma(ColorTools.fromRGBA8888(0xFF0000FF)), ColorTools.toRGBA8888(ColorTools.maximizeSaturation(ColorTools.fromRGBA8888(0xFF0000FF))));
//        System.out.printf("%08X, %1.4f, %08X\n", 0x00FF00FF, ColorTools.chroma(ColorTools.fromRGBA8888(0x00FF00FF)), ColorTools.toRGBA8888(ColorTools.maximizeSaturation(ColorTools.fromRGBA8888(0x00FF00FF))));
//        System.out.printf("%08X, %1.4f, %08X\n", 0x0000FFFF, ColorTools.chroma(ColorTools.fromRGBA8888(0x0000FFFF)), ColorTools.toRGBA8888(ColorTools.maximizeSaturation(ColorTools.fromRGBA8888(0x0000FFFF))));
//        System.out.printf("%08X, %1.4f, %08X\n", 0xFFFF00FF, ColorTools.chroma(ColorTools.fromRGBA8888(0xFFFF00FF)), ColorTools.toRGBA8888(ColorTools.maximizeSaturation(ColorTools.fromRGBA8888(0xFFFF00FF))));
//        System.out.printf("%08X, %1.4f, %08X\n", 0x00FFFFFF, ColorTools.chroma(ColorTools.fromRGBA8888(0x00FFFFFF)), ColorTools.toRGBA8888(ColorTools.maximizeSaturation(ColorTools.fromRGBA8888(0x00FFFFFF))));
//        System.out.printf("%08X, %1.4f, %08X\n", 0xFF00FFFF, ColorTools.chroma(ColorTools.fromRGBA8888(0xFF00FFFF)), ColorTools.toRGBA8888(ColorTools.maximizeSaturation(ColorTools.fromRGBA8888(0xFF00FFFF))));

        rgba.add(0);
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("light dullest red")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("darker duller red")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("lighter rich red")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("dark richer red")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("light dullest apricot")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("darker duller apricot")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("lighter rich apricot")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("dark richer apricot")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("light dullest yellow")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("darker duller yellow")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("lighter rich yellow")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("dark richer yellow")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("light dullest cactus")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("darker duller cactus")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("lighter rich cactus")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("dark richer cactus")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("light dullest blue")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("darker duller blue")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("lighter rich blue")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("dark richer blue")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("light dullest violet")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("darker duller violet")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("lighter rich violet")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("dark richer violet")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("light red apricot")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("darker red apricot")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("light yellow apricot")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("darker yellow apricot")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("light yellow cactus")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("darker yellow cactus")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("light blue cactus")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("darker blue cactus")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("light blue violet")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("darker blue violet")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("light red violet")));
        rgba.add(ColorTools.toRGBA8888(SimplePalette.parseDescription("darker red violet")));
        

        StringBuilder sb = new StringBuilder(12 * rgba.size + 35).append("{\n");
        for (int i = 0; i < rgba.size; i++) {
            StringKit.appendHex(sb.append("0x"), rgba.get(i)).append(", ");
            if(7 == (i & 7)) sb.append('\n');
        }
        System.out.println(sb.append('}'));
    }
}
