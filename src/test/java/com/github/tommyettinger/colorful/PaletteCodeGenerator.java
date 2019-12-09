package com.github.tommyettinger.colorful;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;

/**
 * Created by Tommy Ettinger on 12/8/2019.
 */
public class PaletteCodeGenerator extends ApplicationAdapter {
    public static double lightnessAltLAB(Color color)
    {
        return Math.sqrt((Math.pow(color.r, 2.19921875) * 0x3p-3
                + Math.pow(color.g, 2.19921875) * 0x4p-3
                + Math.pow(color.b, 2.19921875) * 0x1p-3));
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
        config.setTitle("Palete codegen tool");
        config.setIdleFPS(1);
        config.useVsync(true);
//        config.setResizable(false);

        new Lwjgl3Application(new PaletteCodeGenerator(), config);
    }
    public void create() {
        //// This block, when uncommented, will read in color names and values from ColorData.txt and produce a formatted
        //// block of partial Java source as ColorOutput.txt , to be put in SColor.java .
        String templateFull = "/**\n" +
                "* This color constant \"`Name\" has RGBA8888 code {@code `RRGGBBAA}, luma `LUMA, warmth `WARM, mildness `MILD, alpha `ALPHA, hue `HUE, and saturation `SAT.\n" +
                "* It can be represented as a packed float with the constant {@code `PACKEDF}.\n" +
                "* <pre>\n" +
                "* <font style='background-color: #FEDCBA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FEDCBA; color: #000000'>&nbsp;@&nbsp;</font>\n" +
                "* <font style='background-color: #FEDCBA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FEDCBA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FEDCBA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FEDCBA'>&nbsp;@&nbsp;</font><font style='background-color: #FEDCBA; color: #888888'>&nbsp;@&nbsp;</font>\n" +
                "* <font style='background-color: #FEDCBA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FEDCBA; color: #ffffff'>&nbsp;@&nbsp;</font>\n" +
                "* </pre>\n" +
//            "* <br>\n" +
//            "* <font style='background-color: #ff0000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffff00; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00ff00; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0000ff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #964b00; color: #000000'>&nbsp;&nbsp;&nbsp;</font>\n" +
//            "* <font style='background-color: #ff0000; color: #FEDCBA'>&nbsp;@&nbsp;</font><font style='background-color: #ffff00; color: #FEDCBA'>&nbsp;@&nbsp;</font><font style='background-color: #00ff00; color: #FEDCBA'>&nbsp;@&nbsp;</font><font style='background-color: #0000ff; color: #FEDCBA'>&nbsp;@&nbsp;</font><font style='background-color: #964b00; color: #FEDCBA'>&nbsp;@&nbsp;</font>\n" +
//            "* <font style='background-color: #ff0000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffff00; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00ff00; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0000ff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #964b00; color: #000000'>&nbsp;&nbsp;&nbsp;</font></pre>\n" +
                "*/\n" +
                "public static final float `NAME = `PACKEDF;\n" +
                "static { NAMED.put(\"`Name\", `PACKEDF); }\n\n";
        String data = Gdx.files.classpath("AuroraColorData.txt").readString();
        String[] lines = StringKit.split(data, "\n"), rec = new String[3];
        float c;
        StringBuilder sb = new StringBuilder(100000).append("public static final ObjectFloatMap<String> NAMED = new ObjectFloatMap<String>(").append(lines.length).append(");");
        for (int i = 0; i < lines.length; i++) {
            tabSplit(rec, lines[i]);
            c = FloatColorTools.fromRGBA8888(StringKit.intFromHex(rec[1]));
            sb.append(templateFull.replace("`Name", rec[2])
                    .replace("`NAME", rec[0])
                    .replace("`RRGGBBAA", rec[1].toUpperCase())
                    .replace("FEDCBA", rec[1].toUpperCase().substring(0, 6))
                    .replace("`LUMA", Float.toString(FloatColorTools.luma(c)))
                    .replace("`WARM", Float.toString(FloatColorTools.chromaWarm(c)))
                    .replace("`MILD", Float.toString(FloatColorTools.chromaMild(c)))
                    .replace("`ALPHA", Float.toString(FloatColorTools.alpha(c)))
                    .replace("`HUE", Float.toString(FloatColorTools.hue(c)))
                    .replace("`SAT", Float.toString(FloatColorTools.saturation(c)))
                    .replace("`PACKED", Float.toHexString(c))
            );
            //System.out.println("Processed " + i);
        }
        Gdx.files.local("ColorOutput.txt").writeString(sb.toString(), false);

//        String templateTable = "<tr>\n<td style='background-color: #FEDCBA;'></td>\n<td>Name</td>\n<td>0xFEDCBAFF</td>\n<td>`HUE</td>\n<td>`SAT</td>\n<td>`VAL</td>\n<td>`LUMA</td>\n<td>`WARM</td>\n<td>`MILD</td>\n<td>`TWEA</td>\n</tr>\n";
//        final OrderedSet<Float> PAL = new OrderedSet<>(Palette);
////        StringBuilder sb = new StringBuilder(100000);
//        sb.setLength(0);
//        sb.append("<!doctype html>\n<html>\n<body>\n<table>\n<tr>\n<th>Preview Section</th>\n<th>Color Name</th>\n<th>Hex Code</th>\n<th>Hue</th>\n<th>Sat</th>\n<th>Val</th>\n<th>Luma</th>\n<th>Warm</th>\n<th>Mild</th>\n<th>Better Lightness</th>\n</tr>\n");
//        for(float sc : PAL) {
//            sb.append(templateTable.replace("Name", sc.name)
//                            .replace("FEDCBA", StringKit.hex(Color.rgba8888(sc)).substring(0, 6))
////                    .replace("`RED", Float.toString(c.r))
////                    .replace("`GREEN", Float.toString(c.g))
////                    .replace("`BLUE", Float.toString(c.b))
//                            .replace("`HUE", Float.toString(SColor.hue(sc)))
//                            .replace("`SAT", Float.toString(SColor.saturation(sc)))
//                            .replace("`VAL", Float.toString(SColor.value(sc)))
//                            .replace("`LUMA", Float.toString(SColor.lumaYCwCm(sc)))
//                            .replace("`WARM", Float.toString(SColor.chromaWarm(sc)))
//                            .replace("`MILD", Float.toString(SColor.chromaMild(sc)))
//                            .replace("`TWEA", Float.toString((float) lightnessAltLAB(sc)))
//            );
//            //System.out.println("Processed " + i);
//        }
//        sb.append("</table>\n</body>\n</html>");
//        Gdx.files.local("ColorTable.html").writeString(sb.toString(), false);
//
//        sb.setLength(0);
//        PAL.sort(new Comparator<SColor>() {
//            @Override
//            public int compare(SColor c1, SColor c2) {
//                float s1 = c1.saturation(), s2 = c2.saturation();
//                if(s1 < 0x1p-7f && s2 >= 0x1p-7f)
//                    return -1000;
//                else if(s1 >= 0x1p-7f && s2 < 0x1p-7f)
//                    return 1000;
//                else if(s1 < 0x1p-7f && s2 < 0x1p-7f)
//                    return (int)Math.signum(lightnessAltLAB(c1) - lightnessAltLAB(c2));
//                else
//                    return 3 * (int)Math.signum(c1.hue() - c2.hue()) + (int)Math.signum(lightnessAltLAB(c1) - lightnessAltLAB(c2));
//            }
//        });
//        sb.append("<!doctype html>\n<html>\n<body>\n<table>\n<tr>\n<th>Preview Section</th>\n<th>Color Name</th>\n<th>Hex Code</th>\n<th>Hue</th>\n<th>Sat</th>\n<th>Val</th>\n<th>Luma</th>\n<th>Warm</th>\n<th>Mild</th>\n<th>Better Lightness</th>\n</tr>\n");
////        sb.append("<!doctype html>\n<html>\n<body>\n<table>\n<tr>\n<th>Color Name</th>\n<th>Hex Code</th>\n<th>Hue</th>\n<th>Sat</th>\n<th>Val</th>\n<th>Preview Section</th>\n</tr>\n");
//        for (int i = 0; i < PAL.size(); i++) {
//            sc = PAL.getAt(i);
//            sb.append(templateTable.replace("Name", sc.name)
//                    .replace("FEDCBA", StringKit.hex(Color.rgba8888(sc)).substring(0, 6))
//                    .replace("`HUE", Float.toString(SColor.hue(sc)))
//                    .replace("`SAT", Float.toString(SColor.saturation(sc)))
//                    .replace("`VAL", Float.toString(SColor.value(sc)))
//                    .replace("`LUMA", Float.toString(SColor.lumaYCwCm(sc)))
//                    .replace("`WARM", Float.toString(SColor.chromaWarm(sc)))
//                    .replace("`MILD", Float.toString(SColor.chromaMild(sc)))
//                    .replace("`TWEA", Float.toString((float) lightnessAltLAB(sc)))
//            );
//        }
//        sb.append("</table>\n</body>\n</html>");
//        Gdx.files.local("ColorTableHue.html").writeString(sb.toString(), false);
//
//        sb.setLength(0);
//        PAL.sort(new Comparator<SColor>() {
//            @Override
//            public int compare(SColor c1, SColor c2) {
//                return (int)Math.signum(lightnessAltLAB(c1) - lightnessAltLAB(c2));
//            }
//        });
//        sb.append("<!doctype html>\n<html>\n<body>\n<table>\n<tr>\n<th>Preview Section</th>\n<th>Color Name</th>\n<th>Hex Code</th>\n<th>Hue</th>\n<th>Sat</th>\n<th>Val</th>\n<th>Luma</th>\n<th>Warm</th>\n<th>Mild</th>\n<th>Better Lightness</th>\n</tr>\n");
//        for (int i = 0; i < PAL.size(); i++) {
//            sc = PAL.getAt(i);
//            sb.append(templateTable.replace("Name", sc.name)
//                    .replace("FEDCBA", StringKit.hex(Color.rgba8888(sc)).substring(0, 6))
//                    .replace("`HUE", Float.toString(SColor.hue(sc)))
//                    .replace("`SAT", Float.toString(SColor.saturation(sc)))
//                    .replace("`VAL", Float.toString(SColor.value(sc)))
//                    .replace("`LUMA", Float.toString(SColor.lumaYCwCm(sc)))
//                    .replace("`WARM", Float.toString(SColor.chromaWarm(sc)))
//                    .replace("`MILD", Float.toString(SColor.chromaMild(sc)))
//                    .replace("`TWEA", Float.toString((float) lightnessAltLAB(sc)))
//            );
//            //System.out.println("Processed " + i);
//        }
//        sb.append("</table>\n</body>\n</html>");
//        Gdx.files.local("ColorTableValue.html").writeString(sb.toString(), false);
        
        Gdx.app.exit();
    }
}
