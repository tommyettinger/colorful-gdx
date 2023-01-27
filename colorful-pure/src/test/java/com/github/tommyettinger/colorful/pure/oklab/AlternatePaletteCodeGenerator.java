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

package com.github.tommyettinger.colorful.pure.oklab;

import com.github.tommyettinger.colorful.pure.internal.StringKit;
import com.github.tommyettinger.ds.ObjectIntOrderedMap;
import com.github.tommyettinger.digital.BitConversion;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.tommyettinger.colorful.pure.oklab.ColorTools.*;

/**
 * A tool, not a demo, used to generate the data used in AlternatePalette and in the javadocs.
 * Created by Tommy Ettinger on 12/8/2019.
 */
public class AlternatePaletteCodeGenerator {
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
        float c;
        int ci;
        String templateSimple = "\n/**\n" +
                "* This color constant \"`Name\" has RGBA8888 code {@code `RRGGBBAA}, L `LCHAN, A `ACHAN, B `BCHAN, alpha `ALPHA, hue `HUE, saturation `SAT, and chroma `CHR.\n" +
                "* It has the encoded Oklab value `PACKED .\n" +
                "* <pre>\n" +
                "* <font style='background-color: #FEDCBA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FEDCBA; color: #000000'>&nbsp;@&nbsp;</font>\n" +
                "* <font style='background-color: #FEDCBA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FEDCBA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FEDCBA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FEDCBA'>&nbsp;@&nbsp;</font><font style='background-color: #FEDCBA; color: #888888'>&nbsp;@&nbsp;</font>\n" +
                "* <font style='background-color: #FEDCBA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FEDCBA; color: #ffffff'>&nbsp;@&nbsp;</font>\n" +
                "* </pre>\n" +
                "*/\n" +
                "public static final int `NAME = `PACKED;\n" +
                "static { NAMED.put(\"`Name\", `PACKED); LIST.add(`PACKED); }\n";

        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(AlternatePaletteCodeGenerator.class.getResource("/SimpleColorData.txt").toURI())));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        String[] lines = StringKit.split(data, "\n"), rec = new String[3];
        StringBuilder sb = new StringBuilder(100000)
                ;
//                .append("public static final ObjectIntOrderedMap<String> NAMED = new ObjectIntOrderedMap<String>(").append(lines.length).append(");\n")
//                .append("public static final IntList LIST = new IntList(").append(lines.length).append(");\n");

        for (int i = 0; i < lines.length; i++) {
            tabSplit(rec, lines[i]);
            c = fromRGBA8888(StringKit.intFromHex(rec[1]));
            ci = BitConversion.floatToRawIntBits(c);
            ci |= (ci >>> 7 & 0x01000000);
            sb.append(templateSimple.replace("`Name", rec[2])
                    .replace("`NAME", rec[0])
                    .replace("`RRGGBBAA", rec[1])
                    .replace("FEDCBA", rec[1].substring(0, 6))
                    .replace("`LCHAN", Float.toString(ColorTools.channelL(c)))
                    .replace("`ACHAN", Float.toString(ColorTools.channelA(c)))
                    .replace("`BCHAN", Float.toString(ColorTools.channelB(c)))
                    .replace("`ALPHA", Float.toString(ColorTools.alpha(c)))
                    .replace("`HUE", Float.toString(ColorTools.oklabHue(c)))
                    .replace("`SAT", Float.toString(ColorTools.oklabSaturation(c)))
                    .replace("`CHR", Float.toString(ColorTools.chroma(c)))
                    .replace("`PACKED", "0x" + StringKit.hex(ci))
            );
//            System.out.println(rec[2] + " : correct RGBA=" + rec[1] + ", decoded RGBA=" + StringKit.hex(toRGBA8888(c)) + ", raw=" + StringKit.hex(ci)
//                    + ", decoded L=" + ColorTools.channelL(c) + ", decoded A=" + (ColorTools.channelA(c)*2f-1f) + ", decoded B=" + ((ColorTools.channelB(c)*2f-1f)
//                    + ", chroma=" + ColorTools.chroma(c) + ", max chroma=" + ColorTools.chroma(ColorTools.maximizeSaturation(c))
//                    + ", chroma limit=" + ColorTools.chromaLimit(ColorTools.oklabHue(c), (ColorTools.channelL(c))) + ", in gamut=" + ColorTools.inGamut(c))
//            );
        }
        try {
            Files.write(Paths.get("ColorOutputAlternateOklab.txt"), sb.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String templateTable = "<tr>\n<td style='background-color: #FEDCBA;'></td>\n<td>Name</td>\n<td>0x`RGBA8888</td>\n<td>`LCHAN</td>\n<td>`ACHAN</td>\n<td>`BCHAN</td>\n<td>`ALPH</td>\n<td>`HUE</td>\n<td>`SAT</td>\n<td>`CHR</td>\n<td>0x`PACK</td>\n</tr>\n";
        final int size = AlternatePalette.NAMED.size();
        ObjectIntOrderedMap<String> PAL = new ObjectIntOrderedMap<>(AlternatePalette.NAMED);

        sb.setLength(0);
        PAL.sort(null);
        sb.append("<!doctype html>\n<html>\n<body>\n<table>\n<tr>\n<th>Preview Section</th>\n<th>Color Name</th>\n<th>Hex Code</th>\n<th>L</th>\n<th>A</th>\n<th>B</th>\n<th>Alpha</th>\n<th>Hue</th>\n<th>Sat</th>\n<th>Chroma</th>\n<th>Packed</th>\n</tr>\n");
        for(ObjectIntOrderedMap.Entry<String> sc : PAL) {
            assert sc.key != null;
            ci = sc.value;
            c = BitConversion.intBitsToFloat(ci);
            int rgba = ColorTools.toRGBA8888(c);
            sb.append(templateTable.replace("Name", sc.key)
                    .replace("`RGBA8888", StringKit.hex(rgba))
                    .replace("FEDCBA", StringKit.hex(rgba).substring(0, 6))
                    .replace("`HUE", Float.toString(ColorTools.oklabHue(c)))
                    .replace("`SAT", Float.toString(ColorTools.oklabSaturation(c)))
                    .replace("`CHR", Float.toString(ColorTools.chroma(c)))
                    .replace("`LCHAN", Float.toString(ColorTools.channelL(c)))
                    .replace("`ACHAN", Float.toString(ColorTools.channelA(c)))
                    .replace("`BCHAN", Float.toString(ColorTools.channelB(c)))
                    .replace("`ALPH", Float.toString(ColorTools.alpha(c)))
                    .replace("`PACK", StringKit.hex(ci))
            );
        }
        sb.append("</table>\n</body>\n</html>");
        try {
            Files.write(Paths.get("ColorTableAlphabetical.html"), sb.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        sb.setLength(0);
        PAL.sortByValue((int ci1, int ci2) -> {
            if (ColorTools.alphaInt(ci1) < 128) return -10000;
            else if (ColorTools.alphaInt(ci2) < 128) return 10000;
            float c1 = BitConversion.intBitsToFloat(ci1), c2 = BitConversion.intBitsToFloat(ci2);
            float s1 = ColorTools.saturation(c1), s2 = ColorTools.saturation(c2);
            if (s1 <= 0.05f && s2 > 0.05f)
                return -1000;
            else if (s1 > 0.05f && s2 <= 0.05f)
                return 1000;
            else if (s1 <= 0.05f && s2 <= 0.05f)
                return (int) Math.signum(ColorTools.channelL(c1) - ColorTools.channelL(c2));
            else
                return 2 * (int) Math.signum(ColorTools.hue(c1) - ColorTools.hue(c2))
                        + (int) Math.signum(ColorTools.channelL(c1) - ColorTools.channelL(c2));
        });
        sb.append("<!doctype html>\n<html>\n<body>\n<table>\n<tr>\n<th>Preview Section</th>\n<th>Color Name</th>\n<th>Hex Code</th>\n<th>L</th>\n<th>A</th>\n<th>B</th>\n<th>Alpha</th>\n<th>Hue</th>\n<th>Sat</th>\n<th>Chroma</th>\n<th>Packed</th>\n</tr>\n");
        for(ObjectIntOrderedMap.Entry<String> sc : PAL) {
            assert sc.key != null;
            ci = sc.value;
            c = BitConversion.intBitsToFloat(ci);
            int rgba = ColorTools.toRGBA8888(c);
            sb.append(templateTable.replace("Name", sc.key)
                    .replace("`RGBA8888", StringKit.hex(rgba))
                    .replace("FEDCBA", StringKit.hex(rgba).substring(0, 6))
                    .replace("`HUE", Float.toString(ColorTools.oklabHue(c)))
                    .replace("`SAT", Float.toString(ColorTools.oklabSaturation(c)))
                    .replace("`CHR", Float.toString(ColorTools.chroma(c)))
                    .replace("`LCHAN", Float.toString(ColorTools.channelL(c)))
                    .replace("`ACHAN", Float.toString(ColorTools.channelA(c)))
                    .replace("`BCHAN", Float.toString(ColorTools.channelB(c)))
                    .replace("`ALPH", Float.toString(ColorTools.alpha(c)))
                    .replace("`PACK", StringKit.hex(ci))
            );
        }
        sb.append("</table>\n</body>\n</html>");
        try {
            Files.write(Paths.get("ColorTableHue.html"), sb.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        sb.setLength(0);
        PAL.sortByValue((int c1, int c2) -> (int)Math.signum(ColorTools.channelL(BitConversion.intBitsToFloat(c1)) - ColorTools.channelL(BitConversion.intBitsToFloat(c2))));
        sb.append("<!doctype html>\n<html>\n<body>\n<table>\n<tr>\n<th>Preview Section</th>\n<th>Color Name</th>\n<th>Hex Code</th>\n<th>L</th>\n<th>A</th>\n<th>B</th>\n<th>Alpha</th>\n<th>Hue</th>\n<th>Sat</th>\n<th>Chroma</th>\n<th>Packed</th>\n</tr>\n");
        for(ObjectIntOrderedMap.Entry<String> sc : PAL) {
            assert sc.key != null;
            ci = sc.value;
            c = BitConversion.intBitsToFloat(ci);
            int rgba = ColorTools.toRGBA8888(c);
            sb.append(templateTable.replace("Name", sc.key)
                    .replace("`RGBA8888", StringKit.hex(rgba))
                    .replace("FEDCBA", StringKit.hex(rgba).substring(0, 6))
                    .replace("`HUE", Float.toString(ColorTools.oklabHue(c)))
                    .replace("`SAT", Float.toString(ColorTools.oklabSaturation(c)))
                    .replace("`CHR", Float.toString(ColorTools.chroma(c)))
                    .replace("`LCHAN", Float.toString(ColorTools.channelL(c)))
                    .replace("`ACHAN", Float.toString(ColorTools.channelA(c)))
                    .replace("`BCHAN", Float.toString(ColorTools.channelB(c)))
                    .replace("`ALPH", Float.toString(ColorTools.alpha(c)))
                    .replace("`PACK", StringKit.hex(ci))
            );
        }
        sb.append("</table>\n</body>\n</html>");
        try {
            Files.write(Paths.get("ColorTableLightness.html"), sb.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        for (int i = 0; i < 256; i++) {
//            float L = (i / 255f);
//            System.out.printf("At L=%.5f (i=%d), limit is %.5f\n", L, i, chromaLimit(0.7323789f, (L)));
////            System.out.printf("At L=%.5f, limit is %.5f\n", L, chromaLimit(0.9091779f, (L)));
//        }

    }
}
