package com.github.tommyettinger.colorful.hsluv.internal;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.utils.NumberUtils;
import com.badlogic.gdx.utils.ObjectFloatMap;
import com.github.tommyettinger.colorful.hsluv.Palette;
import com.github.tommyettinger.colorful.hsluv.SimplePalette;
import com.github.tommyettinger.colorful.internal.StringKit;
import com.github.tommyettinger.colorful.hsluv.ColorTools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static com.github.tommyettinger.colorful.hsluv.ColorTools.fromRGBA8888;
import static com.github.tommyettinger.colorful.hsluv.ColorTools.toRGBA8888;

/**
 * A tool, not a demo, used to generate the data used in any of various Palette classes and in the Javadocs.
 * Created by Tommy Ettinger on 12/8/2019.
 */
public class PaletteCodeGenerator extends ApplicationAdapter {
    public static final String outputAdd = "Hsluv";
    public static final String inputName = "AuroraColorData.txt";
    public static final ObjectFloatMap<String> named = Palette.NAMED;

//    public static final String outputAdd = "YamHsluv";
//    public static final String inputName = "YamColorData.txt";
//    public static final ObjectFloatMap<String> named = YamPalette.NAMED;

//    public static final String outputAdd = "Yam2Hsluv";
//    public static final String inputName = "Yam2ColorData.txt";
//    public static final ObjectFloatMap<String> named = Yam2Palette.NAMED;

//    public static final String outputAdd = "Yam3Hsluv";
//    public static final String inputName = "Yam3ColorData.txt";
//    public static final ObjectFloatMap<String> named = Yam3Palette.NAMED;

//    public static final String outputAdd = "FullHsluv";
//    public static final String inputName = "ColorData.txt";
//    public static final ObjectFloatMap<String> named = FullPalette.NAMED;

//    public static final String outputAdd = "NamedMunsellHsluv";
//    public static final String inputName = "ISCCNBSData.txt";
//    public static final ObjectFloatMap<String> named = NamedMunsellPalette.NAMED;

//    public static final String outputAdd = "SimpleHsluv";
//    public static final String inputName = "SimpleColorData.txt";
//    public static final ObjectFloatMap<String> named = SimplePalette.NAMED;

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
                "* This color constant \"`Name\" has RGBA8888 code {@code `RRGGBBAA}, H `HCHAN, S `SCHAN, L `LCHAN, alpha `ALPHA, and chroma `CHR.\n" +
                "* It can be represented as a packed float with the constant {@code `PACKEDF}.\n" +
                "* <pre>\n" +
                "* <font style='background-color: #FEDCBA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FEDCBA; color: #000000'>&nbsp;@&nbsp;</font>\n" +
                "* <font style='background-color: #FEDCBA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FEDCBA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FEDCBA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FEDCBA'>&nbsp;@&nbsp;</font><font style='background-color: #FEDCBA; color: #888888'>&nbsp;@&nbsp;</font>\n" +
                "* <font style='background-color: #FEDCBA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FEDCBA; color: #ffffff'>&nbsp;@&nbsp;</font>\n" +
                "* </pre>\n" +
                "*/\n" +
                "public static final float `NAME = `PACKEDF;\n" +
                "static { NAMED.put(\"`Name\", `PACKEDF); LIST.add(`PACKEDF); }\n";
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
                    .replace("`HCHAN", Float.toString(ColorTools.channelH(c)))
                    .replace("`SCHAN", Float.toString(ColorTools.channelS(c)))
                    .replace("`LCHAN", Float.toString(ColorTools.channelL(c)))
                    .replace("`ALPHA", Float.toString(ColorTools.alpha(c)))
                    .replace("`CHR", Float.toString(ColorTools.chroma(c)))
                    .replace("`PACKED", Float.toHexString(c))
            );
            System.out.println(rec[2] + " : correct RGBA=" + rec[1] + ", decoded RGBA=" + StringKit.hex(toRGBA8888(c)) + ", raw=" + StringKit.hex(NumberUtils.floatToRawIntBits(c))
//                    + ", decoded hue=" + ColorTools.hue(c) + ", decoded saturation=" + ColorTools.saturation(c) + ", decoded lightness=" + ColorTools.lightness(c)
                            + ", decoded H=" + ColorTools.channelH(c) + ", decoded S=" + (ColorTools.channelS(c)) + ", decoded L=" + (ColorTools.channelL(c))
            );
        }
        Gdx.files.local("ColorOutput"+outputAdd+".txt").writeString(sb.toString(), false);

        String templateTable = "<tr>\n<td style='background-color: #FEDCBA;'></td>\n<td>Name</td>\n<td>0x`RGBA8888</td>\n<td>`HCHAN</td>\n<td>`SCHAN</td>\n<td>`LCHAN</td>\n<td>`ALPH</td>\n<td>`CHR</td>\n<td>`PACKF</td>\n</tr>\n";
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
        sb.append("<!doctype html>\n<html>\n<body>\n<table>\n<tr>\n<th>Preview Section</th>\n<th>Color Name</th>\n<th>Hex Code</th>\n<th>H</th>\n<th>S</th>\n<th>L</th>\n<th>Alpha</th>\n<th>Chroma</th>\n<th>Packed</th>\n</tr>\n");
        for(ObjectFloatMap.Entry<String> sc : PAL) {
            c = sc.value;
            sb.append(templateTable.replace("Name", sc.key)
                    .replace("`RGBA8888", StringKit.hex(toRGBA8888(c)))
                    .replace("FEDCBA", StringKit.hex(toRGBA8888(c)).substring(0, 6))
                    .replace("`HUE", Float.toString(ColorTools.hsluvHue(c)))
                    .replace("`SAT", Float.toString(ColorTools.hsluvSaturation(c)))
                    .replace("`CHR", Float.toString(ColorTools.chroma(c)))
                    .replace("`HCHAN", Float.toString(ColorTools.channelH(c)))
                    .replace("`SCHAN", Float.toString(ColorTools.channelS(c)))
                    .replace("`LCHAN", Float.toString(ColorTools.channelL(c)))
                    .replace("`ALPH", Float.toString(ColorTools.alpha(c)))
                    .replace("`PACK", Float.toHexString(c))
            );
        }
        sb.append("</table>\n</body>\n</html>");
        Gdx.files.local("ColorTable"+outputAdd+".html").writeString(sb.toString(), false);

        sb.setLength(0);
        Collections.sort(PAL, new Comparator<ObjectFloatMap.Entry<String>>() {
            @Override
            public int compare(ObjectFloatMap.Entry<String> c1, ObjectFloatMap.Entry<String> c2) {
                if (ColorTools.alphaInt(c1.value) < 128) return -10000;
                else if (ColorTools.alphaInt(c2.value) < 128) return 10000;
                float s1 = ColorTools.channelS(c1.value), s2 = ColorTools.channelS(c2.value);
                if(s1 <= 0.05f && s2 > 0.05f)
                    return -1000;
                else if(s1 > 0.05f && s2 <= 0.05f)
                    return 1000;
                else if(s1 <= 0.05f && s2 <= 0.05f)
                    return (int)Math.signum(ColorTools.channelL(c1.value) - ColorTools.channelL(c2.value));
                else
                    return 2 * (int)Math.signum(ColorTools.channelH(c1.value) - ColorTools.channelH(c2.value))
                            + (int)Math.signum(ColorTools.channelL(c1.value) - ColorTools.channelL(c2.value));
            }
        });
        sb.append("<!doctype html>\n<html>\n<body>\n<table>\n<tr>\n<th>Preview Section</th>\n<th>Color Name</th>\n<th>Hex Code</th>\n<th>H</th>\n<th>S</th>\n<th>L</th>\n<th>Alpha</th>\n<th>Chroma</th>\n<th>Packed</th>\n</tr>\n");
        for(ObjectFloatMap.Entry<String> sc : PAL) {
            c = sc.value;
            sb.append(templateTable.replace("Name", sc.key)
                    .replace("`RGBA8888", StringKit.hex(toRGBA8888(c)))
                    .replace("FEDCBA", StringKit.hex(toRGBA8888(c)).substring(0, 6))
                    .replace("`HUE", Float.toString(ColorTools.hsluvHue(c)))
                    .replace("`SAT", Float.toString(ColorTools.hsluvSaturation(c)))
                    .replace("`CHR", Float.toString(ColorTools.chroma(c)))
                    .replace("`HCHAN", Float.toString(ColorTools.channelH(c)))
                    .replace("`SCHAN", Float.toString(ColorTools.channelS(c)))
                    .replace("`LCHAN", Float.toString(ColorTools.channelL(c)))
                    .replace("`ALPH", Float.toString(ColorTools.alpha(c)))
                    .replace("`PACK", Float.toHexString(c))
            );
        }
        sb.append("</table>\n</body>\n</html>");
        Gdx.files.local("ColorTableHue"+outputAdd+".html").writeString(sb.toString(), false);

        sb.setLength(0);
        Collections.sort(PAL, new Comparator<ObjectFloatMap.Entry<String>>() {
            @Override
            public int compare(ObjectFloatMap.Entry<String> c1, ObjectFloatMap.Entry<String> c2) {
                return (int)Math.signum(ColorTools.channelL(c1.value) - ColorTools.channelL(c2.value));
            }
        });
        sb.append("<!doctype html>\n<html>\n<body>\n<table>\n<tr>\n<th>Preview Section</th>\n<th>Color Name</th>\n<th>Hex Code</th>\n<th>H</th>\n<th>S</th>\n<th>L</th>\n<th>Alpha</th>\n<th>Chroma</th>\n<th>Packed</th>\n</tr>\n");
        for(ObjectFloatMap.Entry<String> sc : PAL) {
            c = sc.value;
            sb.append(templateTable.replace("Name", sc.key)
                    .replace("`RGBA8888", StringKit.hex(toRGBA8888(c)))
                    .replace("FEDCBA", StringKit.hex(toRGBA8888(c)).substring(0, 6))
                    .replace("`HUE", Float.toString(ColorTools.hsluvHue(c)))
                    .replace("`SAT", Float.toString(ColorTools.hsluvSaturation(c)))
                    .replace("`CHR", Float.toString(ColorTools.chroma(c)))
                    .replace("`HCHAN", Float.toString(ColorTools.channelH(c)))
                    .replace("`SCHAN", Float.toString(ColorTools.channelS(c)))
                    .replace("`LCHAN", Float.toString(ColorTools.channelL(c)))
                    .replace("`ALPH", Float.toString(ColorTools.alpha(c)))
                    .replace("`PACK", Float.toHexString(c))
            );
        }
        sb.append("</table>\n</body>\n</html>");
        Gdx.files.local("ColorTableValue"+outputAdd+".html").writeString(sb.toString(), false);

        Gdx.app.exit();
    }
}
