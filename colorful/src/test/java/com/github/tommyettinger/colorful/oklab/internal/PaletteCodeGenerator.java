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
import com.badlogic.gdx.utils.NumberUtils;
import com.badlogic.gdx.utils.ObjectFloatMap;
import com.github.tommyettinger.colorful.internal.StringKit;
import com.github.tommyettinger.colorful.oklab.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static com.github.tommyettinger.colorful.oklab.ColorTools.*;

/**
 * A tool, not a demo, used to generate the data used in any of various Palette classes and in the Javadocs.
 * Created by Tommy Ettinger on 12/8/2019.
 */
public class PaletteCodeGenerator extends ApplicationAdapter {
    public static final String outputAdd = "Oklab";
    public static final String inputName = "AuroraColorData.txt";
    public static final String prefix = "Aurora";

//    public static final String outputAdd = "YamOklab";
//    public static final String inputName = "YamColorData.txt";

//    public static final String outputAdd = "Yam2Oklab";
//    public static final String inputName = "Yam2ColorData.txt";

//    public static final String outputAdd = "Yam3Oklab";
//    public static final String inputName = "Yam3ColorData.txt";

//    public static final String outputAdd = "FullOklab";
//    public static final String inputName = "ColorData.txt";
//    public static final String prefix = "Full";

//    public static final String outputAdd = "NamedMunsellOklab";
//    public static final String inputName = "ISCCNBSData.txt";
//    public static final String prefix = "NamedMunsell";

//    public static final String outputAdd = "BrighterMunsellOklab";
//    public static final String inputName = "BrighterMunsell.txt";

//    public static final String outputAdd = "Munsellish2Oklab";
//    public static final String inputName = "Munsellish2.txt";
//    public static final String prefix = "Munsellish2";

//    public static final String outputAdd = "SimpleOklab";
//    public static final String inputName = "SimpleColorData.txt";
//    public static final String prefix = "";

    public static final ObjectFloatMap<String> named = new ObjectFloatMap<>(1024);

    public static final boolean INT_PACK = false;

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
        config.setTitle("Palette codegen tool");
        config.setIdleFPS(1);
        config.useVsync(true);

        new Lwjgl3Application(new PaletteCodeGenerator(), config);
    }
    public void create() {
        float c;
        String templateFull = "\n/**\n" +
                "* This color constant \"`Name\" has RGBA8888 code {@code `RRGGBBAA}, L `LCHAN, A `ACHAN, B `BCHAN," +
                "* alpha `ALPHA, red `RRR, green `GGG, blue `BBB, hue `HUE, saturation `SAT, and chroma `CHR.\n" +
                "* `PACKTYPE {@code `PACKED}.\n" +
                "* <pre>\n" +
                "* <font style='background-color: #FEDCBA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FEDCBA; color: #000000'>&nbsp;@&nbsp;</font>\n" +
                "* <font style='background-color: #FEDCBA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FEDCBA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FEDCBA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FEDCBA'>&nbsp;@&nbsp;</font><font style='background-color: #FEDCBA; color: #888888'>&nbsp;@&nbsp;</font>\n" +
                "* <font style='background-color: #FEDCBA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FEDCBA; color: #ffffff'>&nbsp;@&nbsp;</font>\n" +
                "* </pre>\n" +
                "*/\n" +
                "public static final `TYPE `NAME = `PACKED;\n" +
                "static { NAMED.put(\"`Name\", `PACKED); LIST.add(`PACKED); }\n";
        String data = Gdx.files.classpath(inputName).readString();
        String[] lines = StringKit.split(data, "\n"), rec = new String[3];
        StringBuilder sb = new StringBuilder(100000);
//        .append("public static final ObjectFloatMap<String> NAMED = new ObjectFloatMap<String>(").append(lines.length).append(");\n")
//                .append("public static final FloatArray LIST = new FloatArray(").append(lines.length).append(");\n");

        for (int i = 0; i < lines.length; i++) {
            tabSplit(rec, lines[i]);
            c = fromRGBA8888(StringKit.intFromHex(rec[1]));
            sb.append(templateFull.replace("`Name", rec[2])
                    .replace("`NAME", rec[0])
                    .replace("`RRGGBBAA", rec[1])
                    .replace("FEDCBA", rec[1].substring(0, 6))
                    .replace("`LCHAN", Float.toString(ColorTools.channelL(c)))
                    .replace("`ACHAN", Float.toString(ColorTools.channelA(c)))
                    .replace("`BCHAN", Float.toString(ColorTools.channelB(c)))
                    .replace("`RRR", Float.toString(ColorTools.red(c)))
                    .replace("`GGG", Float.toString(ColorTools.green(c)))
                    .replace("`BBB", Float.toString(ColorTools.blue(c)))
                    .replace("`ALPHA", Float.toString(ColorTools.alpha(c)))
                    .replace("`HUE", Float.toString(ColorTools.oklabHue(c)))
                    .replace("`SAT", Float.toString(ColorTools.oklabSaturation(c)))
                    .replace("`CHR", Float.toString(ColorTools.chroma(c)))
//                    .replace("`CHLIM", Float.toString(ColorTools.chromaLimit(ColorTools.oklabHue(c), channelL(c))))
//                    .replace("`RAW", Integer.toString(ColorTools.getRawGamutValue((int)(channelL(c) * 255.999f) << 8 | (int)(oklabHue(c) * 256f))))
                    .replace("`PACKED", INT_PACK
                            ? "0x"+StringKit.hex((NumberUtils.floatToIntColor(c)))
                            : Float.toHexString(c) + 'F')
                    .replace("`PACKTYPE", INT_PACK
                            ? "It has the encoded Oklab value"
                            : "It can be represented as a packed float with the constant")
                    .replace("`TYPE", INT_PACK
                            ? "int"
                            : "float")
            );
            named.put(rec[2], c);
            System.out.println(rec[2] + " : correct RGBA=" + rec[1] + ", decoded RGBA=" + StringKit.hex(toRGBA8888(c)) + ", raw=" + StringKit.hex(NumberUtils.floatToRawIntBits(c))
//                    + ", decoded hue=" + ColorTools.hue(c) + ", decoded saturation=" + ColorTools.saturation(c) + ", decoded lightness=" + ColorTools.lightness(c)
                    + ", decoded L=" + ColorTools.channelL(c) + ", decoded A=" + (ColorTools.channelA(c)*2f-1f) + ", decoded B=" + (ColorTools.channelB(c)*2f-1f)
            );
        }
        Gdx.files.local("ColorOutput"+outputAdd+".txt").writeString(sb.toString(), false);

        String templateTable = "<tr>\n<td style='background-color: #FEDCBA;'></td>\n<td>Name</td>\n<td>0x`RGBA8888</td>\n<td>`LCHAN</td>\n<td>`ACHAN</td>\n<td>`BCHAN</td>\n<td>`ALPH</td>\n<td>`HUE</td>\n<td>`SAT</td>\n<td>`CHR</td>\n<td>`RRR</td>\n<td>`GGG</td>\n<td>`BBB</td>\n<td>`PACK</td>\n</tr>\n";
        final int size = named.size;
        ArrayList<ObjectFloatMap.Entry<String>> PAL = new ArrayList<>(size);
        for(ObjectFloatMap.Entry<String> e : named.entries())
        {
            ObjectFloatMap.Entry<String> ee = new ObjectFloatMap.Entry<>();
            ee.key = e.key;
            ee.value = e.value;
            PAL.add(ee);
        }

        sb.setLength(0);
        Collections.sort(PAL, new Comparator<ObjectFloatMap.Entry<String>>() {
            @Override
            public int compare(ObjectFloatMap.Entry<String> c1, ObjectFloatMap.Entry<String> c2) {
                return c1.key.compareTo(c2.key);
            }
        });
        sb.append("<!doctype html>\n<html>\n<body>\n<table>\n<tr>\n<th>Preview Section</th>\n<th>Color Name</th>\n<th>Hex Code</th>\n<th>L</th>\n<th>A</th>\n<th>B</th>\n<th>Alpha</th>\n<th>Hue</th>\n<th>Sat</th>\n<th>Chroma</th>\n<th>Red</th>\n<th>Green</th>\n<th>Blue</th>\n<th>Packed</th>\n</tr>\n");
        for(ObjectFloatMap.Entry<String> sc : PAL) {
            c = sc.value;
            sb.append(templateTable.replace("Name", sc.key)
                    .replace("`RGBA8888", StringKit.hex(toRGBA8888(c)))
                    .replace("FEDCBA", StringKit.hex(toRGBA8888(c)).substring(0, 6))
                    .replace("`HUE", Float.toString(ColorTools.oklabHue(c)))
                    .replace("`SAT", Float.toString(ColorTools.oklabSaturation(c)))
                    .replace("`CHR", Float.toString(ColorTools.chroma(c)))
                    .replace("`LCHAN", Float.toString(ColorTools.channelL(c)))
                    .replace("`ACHAN", Float.toString(ColorTools.channelA(c)))
                    .replace("`BCHAN", Float.toString(ColorTools.channelB(c)))
                    .replace("`ALPH", Float.toString(ColorTools.alpha(c)))
                    .replace("`RRR", Float.toString(ColorTools.red(c)))
                    .replace("`GGG", Float.toString(ColorTools.green(c)))
                    .replace("`BBB", Float.toString(ColorTools.blue(c)))
                    .replace("`PACK", INT_PACK
                            ? "0x"+StringKit.hex((NumberUtils.floatToIntColor(c)))
                            : Float.toHexString(c) + 'F')
            );
        }
        sb.append("</table>\n</body>\n</html>");
        Gdx.files.local(INT_PACK ? prefix+"ColorTableAlphabetical.html" : "ColorTable"+outputAdd+".html").writeString(sb.toString(), false);

        sb.setLength(0);
        Collections.sort(PAL, new Comparator<ObjectFloatMap.Entry<String>>() {
            @Override
            public int compare(ObjectFloatMap.Entry<String> c1, ObjectFloatMap.Entry<String> c2) {
                if (ColorTools.alphaInt(c1.value) < 128) return -10000;
                else if (ColorTools.alphaInt(c2.value) < 128) return 10000;
                float s1 = ColorTools.saturation(c1.value), s2 = ColorTools.saturation(c2.value);
                if(s1 <= 0.05f && s2 > 0.05f)
                    return -1000;
                else if(s1 > 0.05f && s2 <= 0.05f)
                    return 1000;
                else if(s1 <= 0.05f && s2 <= 0.05f)
                    return (int)Math.signum(ColorTools.channelL(c1.value) - ColorTools.channelL(c2.value));
                else
                    return 2 * (int)Math.signum(ColorTools.oklabHue(c1.value) - ColorTools.oklabHue(c2.value))
                            + (int)Math.signum(ColorTools.channelL(c1.value) - ColorTools.channelL(c2.value));
            }
        });
        System.out.println("\nnew int[]{");
        int column = 0;
        StringBuilder hx = new StringBuilder(2048);
        sb.append("<!doctype html>\n<html>\n<body>\n<table>\n<tr>\n<th>Preview Section</th>\n<th>Color Name</th>\n<th>Hex Code</th>\n<th>L</th>\n<th>A</th>\n<th>B</th>\n<th>Alpha</th>\n<th>Hue</th>\n<th>Sat</th>\n<th>Chroma</th>\n<th>Red</th>\n<th>Green</th>\n<th>Blue</th>\n<th>Packed</th>\n</tr>\n");
        for(ObjectFloatMap.Entry<String> sc : PAL) {
            c = sc.value;
            sb.append(templateTable.replace("Name", sc.key)
                    .replace("`RGBA8888", StringKit.hex(toRGBA8888(c)))
                    .replace("FEDCBA", StringKit.hex(toRGBA8888(c)).substring(0, 6))
                    .replace("`HUE", Float.toString(ColorTools.oklabHue(c)))
                    .replace("`SAT", Float.toString(ColorTools.oklabSaturation(c)))
                    .replace("`CHR", Float.toString(ColorTools.chroma(c)))
                    .replace("`LCHAN", Float.toString(ColorTools.channelL(c)))
                    .replace("`ACHAN", Float.toString(ColorTools.channelA(c)))
                    .replace("`BCHAN", Float.toString(ColorTools.channelB(c)))
                    .replace("`ALPH", Float.toString(ColorTools.alpha(c)))
                    .replace("`RRR", Float.toString(ColorTools.red(c)))
                    .replace("`GGG", Float.toString(ColorTools.green(c)))
                    .replace("`BBB", Float.toString(ColorTools.blue(c)))
                    .replace("`PACK", INT_PACK
                            ? "0x"+StringKit.hex((NumberUtils.floatToIntColor(c)))
                            : Float.toHexString(c) + 'F')
            );
            System.out.print("0x" + StringKit.hex(toRGBA8888(c)) + ", ");
            if((column = column + 1 & 7) == 0)
                System.out.println();
            hx.append(StringKit.hex(toRGBA8888(c)).substring(0, 6).toUpperCase()).append('\n');
        }
        System.out.println("}");

        hx.deleteCharAt(hx.length() - 1);
        Gdx.files.local(inputName.replace(".txt", ".hex")).writeString(hx.toString(), false);

        sb.append("</table>\n</body>\n</html>");
        Gdx.files.local(INT_PACK ? prefix+"ColorTableHue.html" : "ColorTableHue"+outputAdd+".html").writeString(sb.toString(), false);

        sb.setLength(0);
        Collections.sort(PAL, new Comparator<ObjectFloatMap.Entry<String>>() {
            @Override
            public int compare(ObjectFloatMap.Entry<String> c1, ObjectFloatMap.Entry<String> c2) {
                return (int)Math.signum(ColorTools.channelL(c1.value) - ColorTools.channelL(c2.value));
            }
        });
        sb.append("<!doctype html>\n<html>\n<body>\n<table>\n<tr>\n<th>Preview Section</th>\n<th>Color Name</th>\n<th>Hex Code</th>\n<th>L</th>\n<th>A</th>\n<th>B</th>\n<th>Alpha</th>\n<th>Hue</th>\n<th>Sat</th>\n<th>Chroma</th>\n<th>Red</th>\n<th>Green</th>\n<th>Blue</th>\n<th>Packed</th>\n</tr>\n");
        for(ObjectFloatMap.Entry<String> sc : PAL) {
            c = sc.value;
            sb.append(templateTable.replace("Name", sc.key)
                    .replace("`RGBA8888", StringKit.hex(toRGBA8888(c)))
                    .replace("FEDCBA", StringKit.hex(toRGBA8888(c)).substring(0, 6))
                    .replace("`HUE", Float.toString(ColorTools.oklabHue(c)))
                    .replace("`SAT", Float.toString(ColorTools.oklabSaturation(c)))
                    .replace("`CHR", Float.toString(ColorTools.chroma(c)))
                    .replace("`LCHAN", Float.toString(ColorTools.channelL(c)))
                    .replace("`ACHAN", Float.toString(ColorTools.channelA(c)))
                    .replace("`BCHAN", Float.toString(ColorTools.channelB(c)))
                    .replace("`ALPH", Float.toString(ColorTools.alpha(c)))
                    .replace("`RRR", Float.toString(ColorTools.red(c)))
                    .replace("`GGG", Float.toString(ColorTools.green(c)))
                    .replace("`BBB", Float.toString(ColorTools.blue(c)))
                    .replace("`PACK", INT_PACK
                            ? "0x"+StringKit.hex((NumberUtils.floatToIntColor(c)))
                            : Float.toHexString(c) + 'F')
            );
        }
        sb.append("</table>\n</body>\n</html>");
        Gdx.files.local(INT_PACK ? prefix+"ColorTableLightness.html" : "ColorTableValue"+outputAdd+".html").writeString(sb.toString(), false);
        
        Gdx.app.exit();
    }
}
