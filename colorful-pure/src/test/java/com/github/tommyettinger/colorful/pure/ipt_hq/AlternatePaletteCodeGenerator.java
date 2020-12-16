package com.github.tommyettinger.colorful.pure.ipt_hq;

import com.github.tommyettinger.colorful.pure.internal.StringKit;
import com.github.tommyettinger.colorful.pure.ipt_hq.internal.AlternatePalette;
import com.github.tommyettinger.ds.ObjectIntOrderedMap;
import com.github.tommyettinger.ds.ObjectIntOrderedMap;
import com.github.tommyettinger.ds.support.BitConversion;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.tommyettinger.colorful.pure.ipt_hq.ColorTools.fromRGBA8888;
import static com.github.tommyettinger.colorful.pure.ipt_hq.ColorTools.toRGBA8888;

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
                "* This color constant \"`Name\" has RGBA8888 code {@code `RRGGBBAA}, intensity `INTENS, protan `PROTAN, tritan `TRITAN, alpha `ALPHA, hue `HUE, and saturation `SAT.\n" +
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
        StringBuilder sb = new StringBuilder(100000).append("public static final ObjectIntOrderedMap<String> NAMED = new ObjectIntOrderedMap<String>(").append(lines.length).append(");\n")
                .append("public static final IntList LIST = new IntList(").append(lines.length).append(");\n");

        for (int i = 0; i < lines.length; i++) {
            tabSplit(rec, lines[i]);
            c = fromRGBA8888(ci = StringKit.intFromHex(rec[1]));
            sb.append(templateSimple.replace("`Name", rec[2])
                    .replace("`NAME", rec[0])
                    .replace("`RRGGBBAA", rec[1])
                    .replace("FEDCBA", rec[1].substring(0, 6))
                    .replace("`INTENS", Float.toString(ColorTools.intensity(c)))
                    .replace("`PROTAN", Float.toString(ColorTools.protan(c)))
                    .replace("`TRITAN", Float.toString(ColorTools.tritan(c)))
                    .replace("`ALPHA", Float.toString(ColorTools.alpha(c)))
                    .replace("`HUE", Float.toString(ColorTools.hue(c)))
                    .replace("`SAT", Float.toString(ColorTools.saturation(c)))
                    .replace("`PACKED", "0x" + rec[1])
            );
            System.out.println(rec[2] + " : correct RGBA=" + rec[1] + ", decoded RGBA=" + StringKit.hex(toRGBA8888(c)) + ", raw=" + StringKit.hex(BitConversion.floatToRawIntBits(c))
//                    + ", decoded hue=" + ColorTools.hue(c) + ", decoded saturation=" + ColorTools.saturation(c) + ", decoded lightness=" + ColorTools.lightness(c)
                    + ", decoded intens=" + ColorTools.intensity(c) + ", decoded protan=" + (ColorTools.protan(c)*2f-1f) + ", decoded tritan=" + (ColorTools.tritan(c)*2f-1f)
            );
        }
        try {
            Files.write(Paths.get("ColorOutputAlternateIPT_HQ.txt"), sb.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String templateTable = "<tr>\n<td style='background-color: #FEDCBA;'></td>\n<td>Name</td>\n<td>0x`RGBA8888</td>\n<td>`INTENS</td>\n<td>`PROTAN</td>\n<td>`TRITAN</td>\n<td>`ALPH</td>\n<td>`HUE</td>\n<td>`SAT</td>\n</tr>\n";
        final int size = AlternatePalette.NAMED.size();
        ObjectIntOrderedMap<String> PAL = new ObjectIntOrderedMap<>(AlternatePalette.NAMED);

        sb.setLength(0);
        PAL.sort(null);
        sb.append("<!doctype html>\n<html>\n<body>\n<table>\n<tr>\n<th>Preview Section</th>\n<th>Color Name</th>\n<th>Hex Code</th>\n<th>Intens</th>\n<th>Protan</th>\n<th>Tritan</th>\n<th>Alpha</th>\n<th>Hue</th>\n<th>Sat</th>\n</tr>\n");
        for(ObjectIntOrderedMap.Entry<String> sc : PAL) {
            ci = sc.value;
            c = ColorTools.fromRGBA8888(ci);
            sb.append(templateTable.replace("Name", sc.key)
                    .replace("`RGBA8888", StringKit.hex(ci))
                    .replace("FEDCBA", StringKit.hex(ci).substring(0, 6))
                    .replace("`HUE", Float.toString(ColorTools.hue(c)))
                    .replace("`SAT", Float.toString(ColorTools.saturation(c)))
                    .replace("`INTENS", Float.toString(ColorTools.intensity(c)))
                    .replace("`PROTAN", Float.toString(ColorTools.protan(c)))
                    .replace("`TRITAN", Float.toString(ColorTools.tritan(c)))
                    .replace("`ALPH", Float.toString(ColorTools.alpha(c)))
                    .replace("`PACK", StringKit.hex(ci))
            );
        }
        sb.append("</table>\n</body>\n</html>");
        try {
            Files.write(Paths.get("ColorTableAlternateIPT_HQ.html"), sb.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        sb.setLength(0);
        PAL.sortByValue((int ci1, int ci2) -> {
            float c1 = ColorTools.fromRGBA8888(ci1), c2 = ColorTools.fromRGBA8888(ci2);
            float s1 = ColorTools.saturation(c1), s2 = ColorTools.saturation(c2);
            if (s1 <= 0.05f && s2 > 0.05f)
                return -1000;
            else if (s1 > 0.05f && s2 <= 0.05f)
                return 1000;
            else if (s1 <= 0.05f && s2 <= 0.05f)
                return (int) Math.signum(ColorTools.intensity(c1) - ColorTools.intensity(c2));
            else
                return 2 * (int) Math.signum(ColorTools.hue(c1) - ColorTools.hue(c2))
                        + (int) Math.signum(ColorTools.intensity(c1) - ColorTools.intensity(c2));
        });
        sb.append("<!doctype html>\n<html>\n<body>\n<table>\n<tr>\n<th>Preview Section</th>\n<th>Color Name</th>\n<th>Hex Code</th>\n<th>Intens</th>\n<th>Protan</th>\n<th>Tritan</th>\n<th>Alpha</th>\n<th>Hue</th>\n<th>Sat</th>\n<th>Packed</th>\n</tr>\n");
        for(ObjectIntOrderedMap.Entry<String> sc : PAL) {
            ci = sc.value;
            c = ColorTools.fromRGBA8888(ci);
            sb.append(templateTable.replace("Name", sc.key)
                    .replace("`RGBA8888", StringKit.hex(ci))
                    .replace("FEDCBA", StringKit.hex(ci).substring(0, 6))
                    .replace("`HUE", Float.toString(ColorTools.hue(c)))
                    .replace("`SAT", Float.toString(ColorTools.saturation(c)))
                    .replace("`INTENS", Float.toString(ColorTools.intensity(c)))
                    .replace("`PROTAN", Float.toString(ColorTools.protan(c)))
                    .replace("`TRITAN", Float.toString(ColorTools.tritan(c)))
                    .replace("`ALPH", Float.toString(ColorTools.alpha(c)))
                    .replace("`PACK", StringKit.hex(ci))
            );
        }
        sb.append("</table>\n</body>\n</html>");
        try {
            Files.write(Paths.get("ColorTableAlternateHueIPT_HQ.html"), sb.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        sb.setLength(0);
        PAL.sortByValue((int c1, int c2) -> (int)Math.signum(ColorTools.intensity(ColorTools.fromRGBA8888(c1)) - ColorTools.intensity(ColorTools.fromRGBA8888(c2))));
        sb.append("<!doctype html>\n<html>\n<body>\n<table>\n<tr>\n<th>Preview Section</th>\n<th>Color Name</th>\n<th>Hex Code</th>\n<th>Intens</th>\n<th>Protan</th>\n<th>Tritan</th>\n<th>Alpha</th>\n<th>Hue</th>\n<th>Sat</th>\n<th>Packed</th>\n</tr>\n");
        for(ObjectIntOrderedMap.Entry<String> sc : PAL) {
            ci = sc.value;
            c = ColorTools.fromRGBA8888(ci);
            sb.append(templateTable.replace("Name", sc.key)
                    .replace("`RGBA8888", StringKit.hex(ci))
                    .replace("FEDCBA", StringKit.hex(ci).substring(0, 6))
                    .replace("`HUE", Float.toString(ColorTools.hue(c)))
                    .replace("`SAT", Float.toString(ColorTools.saturation(c)))
                    .replace("`INTENS", Float.toString(ColorTools.intensity(c)))
                    .replace("`PROTAN", Float.toString(ColorTools.protan(c)))
                    .replace("`TRITAN", Float.toString(ColorTools.tritan(c)))
                    .replace("`ALPH", Float.toString(ColorTools.alpha(c)))
                    .replace("`PACK", StringKit.hex(ci))
            );
        }
        sb.append("</table>\n</body>\n</html>");
        try {
            Files.write(Paths.get("ColorTableAlternateValueIPT_HQ.html"), sb.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
