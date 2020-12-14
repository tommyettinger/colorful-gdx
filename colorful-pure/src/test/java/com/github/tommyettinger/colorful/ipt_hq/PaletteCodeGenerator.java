package com.github.tommyettinger.colorful.ipt_hq;

import com.github.tommyettinger.colorful.internal.StringKit;
import com.github.tommyettinger.ds.ObjectFloatOrderedMap;
import com.github.tommyettinger.ds.support.BitConversion;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.tommyettinger.colorful.ipt_hq.ColorTools.fromRGBA8888;
import static com.github.tommyettinger.colorful.ipt_hq.ColorTools.toRGBA8888;

/**
 * A tool, not a demo, used to generate the data used in Palette and in the javadocs.
 * Created by Tommy Ettinger on 12/8/2019.
 */
public class PaletteCodeGenerator {
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
        String templateFull = "\n/**\n" +
                "* This color constant \"`Name\" has RGBA8888 code {@code `RRGGBBAA}, intensity `INTENS, protan `PROTAN, tritan `TRITAN, alpha `ALPHA, hue `HUE, and saturation `SAT.\n" +
                "* It can be represented as a packed float with the constant {@code `PACKEDF}.\n" +
                "* <pre>\n" +
                "* <font style='background-color: #FEDCBA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FEDCBA; color: #000000'>&nbsp;@&nbsp;</font>\n" +
                "* <font style='background-color: #FEDCBA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FEDCBA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FEDCBA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FEDCBA'>&nbsp;@&nbsp;</font><font style='background-color: #FEDCBA; color: #888888'>&nbsp;@&nbsp;</font>\n" +
                "* <font style='background-color: #FEDCBA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FEDCBA; color: #ffffff'>&nbsp;@&nbsp;</font>\n" +
                "* </pre>\n" +
                "*/\n" +
                "public static final float `NAME = `PACKEDF;\n" +
                "static { NAMED.put(\"`Name\", `PACKEDF); LIST.add(`PACKEDF); }\n";

        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(PaletteCodeGenerator.class.getResource("/AuroraColorData.txt").toURI())));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        String[] lines = StringKit.split(data, "\n"), rec = new String[3];
        StringBuilder sb = new StringBuilder(100000).append("public static final ObjectFloatMap<String> NAMED = new ObjectFloatMap<String>(").append(lines.length).append(");\n")
                .append("public static final FloatArray LIST = new FloatArray(").append(lines.length).append(");\n");

        for (int i = 0; i < lines.length; i++) {
            tabSplit(rec, lines[i]);
            c = fromRGBA8888(StringKit.intFromHex(rec[1]));
            sb.append(templateFull.replace("`Name", rec[2])
                    .replace("`NAME", rec[0])
                    .replace("`RRGGBBAA", rec[1])
                    .replace("FEDCBA", rec[1].substring(0, 6))
                    .replace("`INTENS", Float.toString(ColorTools.intensity(c)))
                    .replace("`PROTAN", Float.toString(ColorTools.protan(c)))
                    .replace("`TRITAN", Float.toString(ColorTools.tritan(c)))
                    .replace("`ALPHA", Float.toString(ColorTools.alpha(c)))
                    .replace("`HUE", Float.toString(ColorTools.hue(c)))
                    .replace("`SAT", Float.toString(ColorTools.saturation(c)))
                    .replace("`PACKED", Float.toHexString(c))
            );
            System.out.println(rec[2] + " : correct RGBA=" + rec[1] + ", decoded RGBA=" + StringKit.hex(toRGBA8888(c)) + ", raw=" + StringKit.hex(BitConversion.floatToRawIntBits(c))
//                    + ", decoded hue=" + ColorTools.hue(c) + ", decoded saturation=" + ColorTools.saturation(c) + ", decoded lightness=" + ColorTools.lightness(c)
                    + ", decoded intens=" + ColorTools.intensity(c) + ", decoded protan=" + (ColorTools.protan(c)*2f-1f) + ", decoded tritan=" + (ColorTools.tritan(c)*2f-1f)
            );
        }
        try {
            Files.write(Paths.get("ColorOutputIPT_HQ.txt"), sb.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String templateTable = "<tr>\n<td style='background-color: #FEDCBA;'></td>\n<td>Name</td>\n<td>0x`RGBA8888</td>\n<td>`INTENS</td>\n<td>`PROTAN</td>\n<td>`TRITAN</td>\n<td>`ALPH</td>\n<td>`HUE</td>\n<td>`SAT</td>\n<td>`PACKF</td>\n</tr>\n";
        final int size = Palette.NAMED.size();
        ObjectFloatOrderedMap<String> PAL = new ObjectFloatOrderedMap<>(Palette.NAMED);

        sb.setLength(0);
        PAL.sort(null);
        sb.append("<!doctype html>\n<html>\n<body>\n<table>\n<tr>\n<th>Preview Section</th>\n<th>Color Name</th>\n<th>Hex Code</th>\n<th>Intens</th>\n<th>Protan</th>\n<th>Tritan</th>\n<th>Alpha</th>\n<th>Hue</th>\n<th>Sat</th>\n<th>Packed</th>\n</tr>\n");
        for(ObjectFloatOrderedMap.Entry<String> sc : PAL) {
            c = sc.value;
            sb.append(templateTable.replace("Name", sc.key)
                    .replace("`RGBA8888", StringKit.hex(toRGBA8888(c)))
                    .replace("FEDCBA", StringKit.hex(toRGBA8888(c)).substring(0, 6))
                    .replace("`HUE", Float.toString(ColorTools.hue(c)))
                    .replace("`SAT", Float.toString(ColorTools.saturation(c)))
                    .replace("`INTENS", Float.toString(ColorTools.intensity(c)))
                    .replace("`PROTAN", Float.toString(ColorTools.protan(c)))
                    .replace("`TRITAN", Float.toString(ColorTools.tritan(c)))
                    .replace("`ALPH", Float.toString(ColorTools.alpha(c)))
                    .replace("`PACK", Float.toHexString(c))
            );
        }
        sb.append("</table>\n</body>\n</html>");
        try {
            Files.write(Paths.get("ColorTableIPT_HQ.html"), sb.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        sb.setLength(0);
        PAL.sortByValue((float c1, float c2) -> {
                float s1 = ColorTools.saturation(c1), s2 = ColorTools.saturation(c2);
                if(s1 <= 0.05f && s2 > 0.05f)
                    return -1000;
                else if(s1 > 0.05f && s2 <= 0.05f)
                    return 1000;
                else if(s1 <= 0.05f && s2 <= 0.05f)
                    return (int)Math.signum(ColorTools.intensity(c1) - ColorTools.intensity(c2));
                else
                    return 2 * (int)Math.signum(ColorTools.hue(c1) - ColorTools.hue(c2))
                            + (int)Math.signum(ColorTools.intensity(c1) - ColorTools.intensity(c2));
            });
        sb.append("<!doctype html>\n<html>\n<body>\n<table>\n<tr>\n<th>Preview Section</th>\n<th>Color Name</th>\n<th>Hex Code</th>\n<th>Intens</th>\n<th>Protan</th>\n<th>Tritan</th>\n<th>Alpha</th>\n<th>Hue</th>\n<th>Sat</th>\n<th>Packed</th>\n</tr>\n");
        for(ObjectFloatOrderedMap.Entry<String> sc : PAL) {
            c = sc.value;
            sb.append(templateTable.replace("Name", sc.key)
                    .replace("`RGBA8888", StringKit.hex(toRGBA8888(c)))
                    .replace("FEDCBA", StringKit.hex(toRGBA8888(c)).substring(0, 6))
                    .replace("`HUE", Float.toString(ColorTools.hue(c)))
                    .replace("`SAT", Float.toString(ColorTools.saturation(c)))
                    .replace("`INTENS", Float.toString(ColorTools.intensity(c)))
                    .replace("`PROTAN", Float.toString(ColorTools.protan(c)))
                    .replace("`TRITAN", Float.toString(ColorTools.tritan(c)))
                    .replace("`ALPH", Float.toString(ColorTools.alpha(c)))
                    .replace("`PACK", Float.toHexString(c))
            );
        }
        sb.append("</table>\n</body>\n</html>");
        try {
            Files.write(Paths.get("ColorTableHueIPT_HQ.html"), sb.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        sb.setLength(0);
        PAL.sortByValue((float c1, float c2) -> (int)Math.signum(ColorTools.intensity(c1) - ColorTools.intensity(c2)));
        sb.append("<!doctype html>\n<html>\n<body>\n<table>\n<tr>\n<th>Preview Section</th>\n<th>Color Name</th>\n<th>Hex Code</th>\n<th>Intens</th>\n<th>Protan</th>\n<th>Tritan</th>\n<th>Alpha</th>\n<th>Hue</th>\n<th>Sat</th>\n<th>Packed</th>\n</tr>\n");
        for(ObjectFloatOrderedMap.Entry<String> sc : PAL) {
            c = sc.value;
            sb.append(templateTable.replace("Name", sc.key)
                    .replace("`RGBA8888", StringKit.hex(toRGBA8888(c)))
                    .replace("FEDCBA", StringKit.hex(toRGBA8888(c)).substring(0, 6))
                    .replace("`HUE", Float.toString(ColorTools.hue(c)))
                    .replace("`SAT", Float.toString(ColorTools.saturation(c)))
                    .replace("`INTENS", Float.toString(ColorTools.intensity(c)))
                    .replace("`PROTAN", Float.toString(ColorTools.protan(c)))
                    .replace("`TRITAN", Float.toString(ColorTools.tritan(c)))
                    .replace("`ALPH", Float.toString(ColorTools.alpha(c)))
                    .replace("`PACK", Float.toHexString(c))
            );
        }
        sb.append("</table>\n</body>\n</html>");
        try {
            Files.write(Paths.get("ColorTableValueIPT_HQ.html"), sb.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
