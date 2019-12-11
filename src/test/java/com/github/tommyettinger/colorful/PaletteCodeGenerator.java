package com.github.tommyettinger.colorful;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.utils.ObjectFloatMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Tommy Ettinger on 12/8/2019.
 */
public class PaletteCodeGenerator extends ApplicationAdapter {
    public static float lightnessAltLAB(float color)
    {
        return (float) Math.sqrt((Math.pow(FloatColorTools.red(color), 2.19921875) * 0x3p-3
                + Math.pow(FloatColorTools.green(color), 2.19921875) * 0x4p-3
                + Math.pow(FloatColorTools.blue(color), 2.19921875) * 0x1p-3));
    }

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
//        System.out.println(StringKit.join(";", receiving));
    }


    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Palette codegen tool");
        config.setIdleFPS(1);
        config.useVsync(true);
//        config.setResizable(false);

        new Lwjgl3Application(new PaletteCodeGenerator(), config);
    }
    public void create() {
        //// This block, when uncommented, will read in color names and values from ColorData.txt and produce a formatted
        //// block of partial Java source as ColorOutput.txt , to be put in SColor.java .
        String templateFull = "\n/**\n" +
                "* This color constant \"`Name\" has RGBA8888 code {@code `RRGGBBAA}, luma `LUMA, warmth `WARM, mildness `MILD, alpha `ALPHA, hue `HUE, and saturation `SAT.\n" +
                "* It can be represented as a packed float with the constant {@code `PACKEDF}.\n" +
                "* <pre>\n" +
                "* <font style='background-color: #FEDCBA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FEDCBA; color: #000000'>&nbsp;@&nbsp;</font>\n" +
                "* <font style='background-color: #FEDCBA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FEDCBA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FEDCBA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FEDCBA'>&nbsp;@&nbsp;</font><font style='background-color: #FEDCBA; color: #888888'>&nbsp;@&nbsp;</font>\n" +
                "* <font style='background-color: #FEDCBA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FEDCBA; color: #ffffff'>&nbsp;@&nbsp;</font>\n" +
                "* </pre>\n" +
                "*/\n" +
                "public static final float `NAME = `PACKEDF;\n" +
                "static { NAMED.put(\"`Name\", `PACKEDF); LIST.add(`PACKEDF); }\n";
        String data = Gdx.files.classpath("AuroraColorData.txt").readString();
        String[] lines = StringKit.split(data, "\n"), rec = new String[3];
        float c;
        StringBuilder sb = new StringBuilder(100000).append("public static final ObjectFloatMap<String> NAMED = new ObjectFloatMap<String>(").append(lines.length).append(");\n")
                .append("public static final FloatArray LIST = new FloatArray(").append(lines.length).append(");\n");

        for (int i = 0; i < lines.length; i++) {
            tabSplit(rec, lines[i]);
            c = FloatColorTools.fromRGBA8888(StringKit.intFromHex(rec[1]));
            sb.append(templateFull.replace("`Name", rec[2])
                    .replace("`NAME", rec[0])
                    .replace("`RRGGBBAA", rec[1])
                    .replace("FEDCBA", rec[1].substring(0, 6))
                    .replace("`LUMA", Float.toString(FloatColorTools.luma(c)))
                    .replace("`WARM", Float.toString(FloatColorTools.chromaWarm(c)))
                    .replace("`MILD", Float.toString(FloatColorTools.chromaMild(c)))
                    .replace("`ALPHA", Float.toString(FloatColorTools.alpha(c)))
                    .replace("`HUE", Float.toString(FloatColorTools.hue(c)))
                    .replace("`SAT", Float.toString(FloatColorTools.saturation(c)))
                    .replace("`PACKED", Float.toHexString(c))
            );
            System.out.println(rec[2] + " : correct RGBA=" + rec[1] + ", decoded RGBA=" + StringKit.hex(FloatColorTools.toRGBA8888(c))
                    + ", decoded luma=" + FloatColorTools.luma(c) + ", decoded warmth=" + FloatColorTools.chromaWarm(c) + ", decoded mild=" + FloatColorTools.chromaMild(c));
        }
        Gdx.files.local("ColorOutput.txt").writeString(sb.toString(), false);

        String templateTable = "<tr>\n<td style='background-color: #FEDCBA;'></td>\n<td>Name</td>\n<td>0x`RGBA8888</td>\n<td>`LUMA</td>\n<td>`WARM</td>\n<td>`MILD</td>\n<td>`ALPH</td>\n<td>`HUE</td>\n<td>`SAT</td>\n<td>`PACK</td>\n</tr>\n";
        final int size = Palette.NAMED.size;
        ArrayList<ObjectFloatMap.Entry<String>> PAL = new ArrayList<>(size);
        for(ObjectFloatMap.Entry<String> e : Palette.NAMED.entries())
        {
            ObjectFloatMap.Entry<String> ee = new ObjectFloatMap.Entry<String>();
            ee.key = e.key;
            ee.value = e.value;
            PAL.add(ee);
        }
        //final OrderedSet<Float> PAL = new OrderedSet<>(Palette);
//        StringBuilder sb = new StringBuilder(100000);
        sb.setLength(0);
        Collections.sort(PAL, new Comparator<ObjectFloatMap.Entry<String>>() {
            @Override
            public int compare(ObjectFloatMap.Entry<String> c1, ObjectFloatMap.Entry<String> c2) {
                return c1.key.compareTo(c2.key);
            }
        });
        sb.append("<!doctype html>\n<html>\n<body>\n<table>\n<tr>\n<th>Preview Section</th>\n<th>Color Name</th>\n<th>Hex Code</th>\n<th>Luma</th>\n<th>Warm</th>\n<th>Mild</th>\n<th>Alph</th>\n<th>Hue</th>\n<th>Sat</th>\n<th>Packed</th>\n</tr>\n");
        for(ObjectFloatMap.Entry<String> sc : PAL) {
            c = sc.value;
            sb.append(templateTable.replace("Name", sc.key)
                    .replace("`RGBA8888", StringKit.hex(FloatColorTools.toRGBA8888(c)))
                    .replace("FEDCBA", StringKit.hex(FloatColorTools.toRGBA8888(c)).substring(0, 6))
                    .replace("`HUE", Float.toString(FloatColorTools.hue(c)))
                    .replace("`SAT", Float.toString(FloatColorTools.saturation(c)))
                    .replace("`LUMA", Float.toString(FloatColorTools.luma(c)))
                    .replace("`WARM", Float.toString(FloatColorTools.chromaWarm(c)))
                    .replace("`MILD", Float.toString(FloatColorTools.chromaMild(c)))
                    .replace("`ALPH", Float.toString(FloatColorTools.alpha(c)))
//                    .replace("`TWEA", Float.toString(lightnessAltLAB(c)))
                    .replace("`PACK", Float.toHexString(c))
            );
            //System.out.println("Processed " + i);
        }
        sb.append("</table>\n</body>\n</html>");
        Gdx.files.local("ColorTable.html").writeString(sb.toString(), false);

        sb.setLength(0);
        Collections.sort(PAL, new Comparator<ObjectFloatMap.Entry<String>>() {
            @Override
            public int compare(ObjectFloatMap.Entry<String> c1, ObjectFloatMap.Entry<String> c2) {
                float s1 = FloatColorTools.saturation(c1.value), s2 = FloatColorTools.saturation(c2.value);
                if(s1 < 0x1p-7f && s2 >= 0x1p-7f)
                    return -1000;
                else if(s1 >= 0x1p-7f && s2 < 0x1p-7f)
                    return 1000;
                else if(s1 < 0x1p-7f && s2 < 0x1p-7f)
                    return (int)Math.signum(lightnessAltLAB(c1.value) - lightnessAltLAB(c2.value));
                else
                    return 3 * (int)Math.signum(FloatColorTools.hue(c1.value) - FloatColorTools.hue(c2.value))
                            + (int)Math.signum(lightnessAltLAB(c1.value) - lightnessAltLAB(c2.value));
            }
        });
        sb.append("<!doctype html>\n<html>\n<body>\n<table>\n<tr>\n<th>Preview Section</th>\n<th>Color Name</th>\n<th>Hex Code</th>\n<th>Luma</th>\n<th>Warm</th>\n<th>Mild</th>\n<th>Alph</th>\n<th>Hue</th>\n<th>Sat</th>\n<th>Packed</th>\n</tr>\n");
        for(ObjectFloatMap.Entry<String> sc : PAL) {
            c = sc.value;
            sb.append(templateTable.replace("Name", sc.key)
                    .replace("`RGBA8888", StringKit.hex(FloatColorTools.toRGBA8888(c)))
                    .replace("FEDCBA", StringKit.hex(FloatColorTools.toRGBA8888(c)).substring(0, 6))
                    .replace("`HUE", Float.toString(FloatColorTools.hue(c)))
                    .replace("`SAT", Float.toString(FloatColorTools.saturation(c)))
                    .replace("`LUMA", Float.toString(FloatColorTools.luma(c)))
                    .replace("`WARM", Float.toString(FloatColorTools.chromaWarm(c)))
                    .replace("`MILD", Float.toString(FloatColorTools.chromaMild(c)))
                    .replace("`ALPH", Float.toString(FloatColorTools.alpha(c)))
//                    .replace("`TWEA", Float.toString(lightnessAltLAB(c)))
                    .replace("`PACK", Float.toHexString(c))
            );
            //System.out.println("Processed " + i);
        }
        sb.append("</table>\n</body>\n</html>");
        Gdx.files.local("ColorTableHue.html").writeString(sb.toString(), false);

        sb.setLength(0);
        Collections.sort(PAL, new Comparator<ObjectFloatMap.Entry<String>>() {
            @Override
            public int compare(ObjectFloatMap.Entry<String> c1, ObjectFloatMap.Entry<String> c2) {
                return (int)Math.signum(lightnessAltLAB(c1.value) - lightnessAltLAB(c2.value));
            }
        });
        sb.append("<!doctype html>\n<html>\n<body>\n<table>\n<tr>\n<th>Preview Section</th>\n<th>Color Name</th>\n<th>Hex Code</th>\n<th>Luma</th>\n<th>Warm</th>\n<th>Mild</th>\n<th>Alph</th>\n<th>Hue</th>\n<th>Sat</th>\n<th>Packed</th>\n</tr>\n");
        for(ObjectFloatMap.Entry<String> sc : PAL) {
            c = sc.value;
            sb.append(templateTable.replace("Name", sc.key)
                    .replace("`RGBA8888", StringKit.hex(FloatColorTools.toRGBA8888(c)))
                    .replace("FEDCBA", StringKit.hex(FloatColorTools.toRGBA8888(c)).substring(0, 6))
                    .replace("`HUE", Float.toString(FloatColorTools.hue(c)))
                    .replace("`SAT", Float.toString(FloatColorTools.saturation(c)))
                    .replace("`LUMA", Float.toString(FloatColorTools.luma(c)))
                    .replace("`WARM", Float.toString(FloatColorTools.chromaWarm(c)))
                    .replace("`MILD", Float.toString(FloatColorTools.chromaMild(c)))
                    .replace("`ALPH", Float.toString(FloatColorTools.alpha(c)))
//                    .replace("`TWEA", Float.toString(lightnessAltLAB(c)))
                    .replace("`PACK", Float.toHexString(c))
            );
            //System.out.println("Processed " + i);
        }
        sb.append("</table>\n</body>\n</html>");
        Gdx.files.local("ColorTableValue.html").writeString(sb.toString(), false);
        
        c = FloatColorTools.floatColor(0.5f, 0.5f, 0.5f, 1f);
        rec[0] = "NEUTRAL";
        rec[1] = StringKit.hex(FloatColorTools.toRGBA8888(c));
        rec[2] = "Neutral";
        System.out.println(
                templateFull.replace("`Name", rec[2])
                .replace("`NAME", rec[0])
                .replace("`RRGGBBAA", rec[1])
                .replace("FEDCBA", rec[1].substring(0, 6))
                .replace("`LUMA", Float.toString(FloatColorTools.luma(c)))
                .replace("`WARM", Float.toString(FloatColorTools.chromaWarm(c)))
                .replace("`MILD", Float.toString(FloatColorTools.chromaMild(c)))
                .replace("`ALPHA", Float.toString(FloatColorTools.alpha(c)))
                .replace("`HUE", Float.toString(FloatColorTools.hue(c)))
                .replace("`SAT", Float.toString(FloatColorTools.saturation(c)))
                .replace("`PACKED", Float.toHexString(c))
        );
        
        Gdx.app.exit();
    }
}
