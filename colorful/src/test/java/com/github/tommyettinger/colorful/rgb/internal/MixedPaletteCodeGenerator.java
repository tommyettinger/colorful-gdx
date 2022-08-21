package com.github.tommyettinger.colorful.rgb.internal;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.utils.ObjectIntMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.github.tommyettinger.colorful.internal.StringKit;
import com.github.tommyettinger.colorful.rgb.ColorTools;
import com.github.tommyettinger.colorful.rgb.RGBPalette;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * A tool, not a demo, used to generate the data used in any of various Palette classes and in the Javadocs.
 * Created by Tommy Ettinger on 12/8/2019.
 */
public class MixedPaletteCodeGenerator extends ApplicationAdapter {
    public static float red(int c) {
        return (c >>> 24 & 255) / 255f;
    }
    public static float green(int c) {
        return (c >>> 16 & 255) / 255f;
    }
    public static float blue(int c) {
        return (c >>> 8 & 255) / 255f;
    }
    public static float alpha(int c) {
        return (c & 255) / 255f;
    }
    public static float hue(int c){
        return ColorTools.hue(ColorTools.fromRGBA8888(c));
    }
    public static float saturation(int c){
        return ColorTools.saturation(ColorTools.fromRGBA8888(c));
    }
    public static float lightness(int c){
        return ColorTools.lightness(ColorTools.fromRGBA8888(c));
    }
    public static int toRGBA8888(int c){
        return c;
    }


//    public static final String outputAdd = "Oklab";
//    public static final String inputName = "AuroraColorData.txt";
//    public static final ObjectFloatMap<String> named = Palette.NAMED;

//    public static final String outputAdd = "YamOklab";
//    public static final String inputName = "YamColorData.txt";
//    public static final ObjectFloatMap<String> named = YamPalette.NAMED;

//    public static final String outputAdd = "Yam2Oklab";
//    public static final String inputName = "Yam2ColorData.txt";
//    public static final ObjectFloatMap<String> named = Yam2Palette.NAMED;

//    public static final String outputAdd = "Yam3Oklab";
//    public static final String inputName = "Yam3ColorData.txt";
//    public static final ObjectFloatMap<String> named = Yam3Palette.NAMED;

//    public static final String outputAdd = "FullOklab";
//    public static final String inputName = "ColorData.txt";
//    public static final ObjectFloatMap<String> named = FullPalette.NAMED;

//    public static final String outputAdd = "NamedMunsellOklab";
//    public static final String inputName = "ISCCNBSData.txt";
//    public static final ObjectFloatMap<String> named = NamedMunsellPalette.NAMED;

    public static final String outputAdd = "RGB";
    public static final String inputName = "SimpleColorData.txt";
    public static final ObjectIntMap<String> named = RGBPalette.NAMED;

    public static final boolean INT_PACK = true;

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

        new Lwjgl3Application(new MixedPaletteCodeGenerator(), config);
    }
    public void create() {
        int c;
        String templateFull = "\n/**\n" +
                "* This color constant \"`Name\" has RGBA8888 code {@code `RRGGBBAA}, R `RCHAN, G `GCHAN, B `BCHAN, A `ALPHA, hue `HUE, saturation `SAT, and lightness `LIG.\n" +
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
        sb.append("public static final ObjectIntMap<String> NAMED = new ObjectIntMap<String>(").append(lines.length + Colors.getColors().size).append(");\n")
                .append("public static final IntArray LIST = new IntArray(").append(lines.length + Colors.getColors().size).append(");\n");

        for (int i = 0; i < lines.length; i++) {
            tabSplit(rec, lines[i]);
            c = StringKit.intFromHex(rec[1]);
            sb.append(templateFull.replace("`Name", rec[2].toLowerCase())
                    .replace("`NAME", rec[0].toLowerCase())
                    .replace("`RRGGBBAA", rec[1])
                    .replace("FEDCBA", rec[1].substring(0, 6))
                    .replace("`RCHAN", Float.toString(red(c)))
                    .replace("`GCHAN", Float.toString(green(c)))
                    .replace("`BCHAN", Float.toString(blue(c)))
                    .replace("`ALPHA", Float.toString(alpha(c)))
                    .replace("`HUE", Float.toString(hue(c)))
                    .replace("`SAT", Float.toString(saturation(c)))
                    .replace("`LIG", Float.toString(lightness(c)))
                    .replace("`PACKED", INT_PACK
                            ? "0x"+rec[1]
                            : Float.toHexString(c) + 'F')
                    .replace("`TYPE", INT_PACK
                            ? "int"
                            : "float")
            );
        }
        for(ObjectMap.Entry<String, Color> e : Colors.getColors()){
            c = Color.rgba8888(e.value);
            rec[1] = StringKit.hex(c).toUpperCase();
            sb.append(templateFull.replace("`Name", e.key)
                    .replace("`NAME", e.key)
                    .replace("`RRGGBBAA", rec[1])
                    .replace("FEDCBA", rec[1].substring(0, 6))
                    .replace("`RCHAN", Float.toString(red(c)))
                    .replace("`GCHAN", Float.toString(green(c)))
                    .replace("`BCHAN", Float.toString(blue(c)))
                    .replace("`ALPHA", Float.toString(alpha(c)))
                    .replace("`HUE", Float.toString(hue(c)))
                    .replace("`SAT", Float.toString(saturation(c)))
                    .replace("`LIG", Float.toString(lightness(c)))
                    .replace("`PACKED", INT_PACK
                            ? "0x"+rec[1]
                            : Float.toHexString(c) + 'F')
                    .replace("`TYPE", INT_PACK
                            ? "int"
                            : "float")
            );

        }
        Gdx.files.local("ColorOutput"+outputAdd+".txt").writeString(sb.toString(), false);

        String templateTable = "<tr>\n<td style='background-color: #FEDCBA;'></td>\n<td>Name</td>\n<td>0x`RGBA8888</td>\n<td>`RCHAN</td>\n<td>`GCHAN</td>\n<td>`BCHAN</td>\n<td>`ALPHA</td>\n<td>`HUE</td>\n<td>`SAT</td>\n<td>`LIG</td>\n<td>`PACK</td>\n</tr>\n";
        final int size = named.size;
        ArrayList<ObjectIntMap.Entry<String>> PAL = new ArrayList<>(size);
        for(ObjectIntMap.Entry<String> e : named.entries())
        {
            ObjectIntMap.Entry<String> ee = new ObjectIntMap.Entry<>();
            ee.key = e.key;
            ee.value = e.value;
            PAL.add(ee);
        }

        sb.setLength(0);
        Collections.sort(PAL, new Comparator<ObjectIntMap.Entry<String>>() {
            @Override
            public int compare(ObjectIntMap.Entry<String> c1, ObjectIntMap.Entry<String> c2) {
                return c1.key.compareTo(c2.key);
            }
        });
        sb.append("<!doctype html>\n<html>\n<body>\n<table>\n<tr>\n<th>Preview Section</th>\n<th>Color Name</th>\n<th>Hex Code</th>\n<th>L</th>\n<th>A</th>\n<th>B</th>\n<th>Alpha</th>\n<th>Hue</th>\n<th>Sat</th>\n<th>Chroma</th>\n<th>Packed</th>\n</tr>\n");
        for(ObjectIntMap.Entry<String> sc : PAL) {
            c = sc.value;
            sb.append(templateTable.replace("Name", sc.key)
                    .replace("`RGBA8888", StringKit.hex(toRGBA8888(c)).toUpperCase())
                    .replace("FEDCBA", StringKit.hex(toRGBA8888(c)).substring(0, 6).toUpperCase())
                    .replace("`RCHAN", Float.toString(red(c)))
                    .replace("`GCHAN", Float.toString(green(c)))
                    .replace("`BCHAN", Float.toString(blue(c)))
                    .replace("`ALPHA", Float.toString(alpha(c)))
                    .replace("`HUE", Float.toString(hue(c)))
                    .replace("`SAT", Float.toString(saturation(c)))
                    .replace("`LIG", Float.toString(lightness(c)))
                    .replace("`PACK", INT_PACK
                            ? "0x"+StringKit.hex(c).toUpperCase()
                            : Float.toHexString(c) + 'F')
            );
        }
        sb.append("</table>\n</body>\n</html>");
        Gdx.files.local(INT_PACK ? "ColorTableAlphabetical.html" : "ColorTable"+outputAdd+".html").writeString(sb.toString(), false);

        sb.setLength(0);
        Collections.sort(PAL, new Comparator<ObjectIntMap.Entry<String>>() {
            @Override
            public int compare(ObjectIntMap.Entry<String> c1, ObjectIntMap.Entry<String> c2) {
                float a1 = alpha(c1.value), a2 = alpha(c2.value);
                if(a1 >= 0.5f || a2 >= 0.5f) {
                    if (a1 < 0.5f) return -10000;
                    else if (a2 < 0.5f) return 10000;
                }
                float s1 = saturation(c1.value), s2 = saturation(c2.value);
                if(s1 <= 0.05f && s2 > 0.05f)
                    return -1000;
                else if(s1 > 0.05f && s2 <= 0.05f)
                    return 1000;
                else if(s1 <= 0.05f && s2 <= 0.05f)
                    return (int)Math.signum(lightness(c1.value) - lightness(c2.value));
                else
                    return 2 * (int)Math.signum(hue(c1.value) - hue(c2.value))
                            + (int)Math.signum(lightness(c1.value) - lightness(c2.value));
            }
        });
        sb.append("<!doctype html>\n<html>\n<body>\n<table>\n<tr>\n<th>Preview Section</th>\n<th>Color Name</th>\n<th>Hex Code</th>\n<th>L</th>\n<th>A</th>\n<th>B</th>\n<th>Alpha</th>\n<th>Hue</th>\n<th>Sat</th>\n<th>Chroma</th>\n<th>Packed</th>\n</tr>\n");
        for(ObjectIntMap.Entry<String> sc : PAL) {
            c = sc.value;
            sb.append(templateTable.replace("Name", sc.key)
                    .replace("`RGBA8888", StringKit.hex(toRGBA8888(c)).toUpperCase())
                    .replace("FEDCBA", StringKit.hex(toRGBA8888(c)).substring(0, 6).toUpperCase())
                    .replace("`RCHAN", Float.toString(red(c)))
                    .replace("`GCHAN", Float.toString(green(c)))
                    .replace("`BCHAN", Float.toString(blue(c)))
                    .replace("`ALPHA", Float.toString(alpha(c)))
                    .replace("`HUE", Float.toString(hue(c)))
                    .replace("`SAT", Float.toString(saturation(c)))
                    .replace("`LIG", Float.toString(lightness(c)))
                    .replace("`PACK", INT_PACK
                            ? "0x"+StringKit.hex(c).toUpperCase()
                            : Float.toHexString(c) + 'F')
            );
        }
        sb.append("</table>\n</body>\n</html>");
        Gdx.files.local(INT_PACK ? "ColorTableHue.html" : "ColorTableHue"+outputAdd+".html").writeString(sb.toString(), false);

        sb.setLength(0);
        Collections.sort(PAL, new Comparator<ObjectIntMap.Entry<String>>() {
            @Override
            public int compare(ObjectIntMap.Entry<String> c1, ObjectIntMap.Entry<String> c2) {
                return (int)Math.signum(lightness(c1.value) - lightness(c2.value));
            }
        });
        sb.append("<!doctype html>\n<html>\n<body>\n<table>\n<tr>\n<th>Preview Section</th>\n<th>Color Name</th>\n<th>Hex Code</th>\n<th>L</th>\n<th>A</th>\n<th>B</th>\n<th>Alpha</th>\n<th>Hue</th>\n<th>Sat</th>\n<th>Chroma</th>\n<th>Packed</th>\n</tr>\n");
        for(ObjectIntMap.Entry<String> sc : PAL) {
            c = sc.value;
            sb.append(templateTable.replace("Name", sc.key)
                    .replace("`RGBA8888", StringKit.hex(toRGBA8888(c)).toUpperCase())
                    .replace("FEDCBA", StringKit.hex(toRGBA8888(c)).substring(0, 6).toUpperCase())
                    .replace("`RCHAN", Float.toString(red(c)))
                    .replace("`GCHAN", Float.toString(green(c)))
                    .replace("`BCHAN", Float.toString(blue(c)))
                    .replace("`ALPHA", Float.toString(alpha(c)))
                    .replace("`HUE", Float.toString(hue(c)))
                    .replace("`SAT", Float.toString(saturation(c)))
                    .replace("`LIG", Float.toString(lightness(c)))
                    .replace("`PACK", INT_PACK
                            ? "0x"+StringKit.hex(c).toUpperCase()
                            : Float.toHexString(c) + 'F')
            );
        }
        sb.append("</table>\n</body>\n</html>");
        Gdx.files.local(INT_PACK ? "ColorTableLightness.html" : "ColorTableValue"+outputAdd+".html").writeString(sb.toString(), false);
        
        Gdx.app.exit();
    }
}
