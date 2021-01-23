package com.github.tommyettinger.colorful.ipt.internal;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.utils.NumberUtils;
import com.badlogic.gdx.utils.ObjectFloatMap;
import com.github.tommyettinger.colorful.internal.StringKit;
import com.github.tommyettinger.colorful.ipt.ColorTools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static com.github.tommyettinger.colorful.ipt.ColorTools.fromRGBA8888;
import static com.github.tommyettinger.colorful.ipt.ColorTools.toRGBA8888;

/**
 * A tool, not a demo, used to generate the data used in Palette and in the javadocs.
 * Created by Tommy Ettinger on 12/8/2019.
 */
public class PaletteCodeGenerator extends ApplicationAdapter {
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
    //0.313921 0.639468 0.0465970
    //0.151693 0.748209 0.1000044
    //0.017700 0.109400 0.8729000
    
//    public static float fromRGBA8888(final int rgba) {
//        final float r = (rgba >>> 24) * 0x1.010101010101p-8f;
//        final float g = (rgba >>> 16 & 0xFF) * 0x1.010101010101p-8f;
//        final float b = (rgba >>> 8 & 0xFF) * 0x1.010101010101p-8f;
//        //final float l = 0.313921f * r + 0.639468f * g + 0.0465970f * b;  //0.439020f 0.512400f 0.048586f
//        //final float m = 0.151693f * r + 0.748209f * g + 0.1000044f * b;  //0.212140f 0.683470f 0.104310f
//        //final float s = 0.017700f * r + 0.109400f * g + 0.8729000f * b;  //0.024828f 0.064919f 0.910230f
//        final float l = 0.439020f * r + 0.512400f * g + 0.048586f * b;
//        final float m = 0.212140f * r + 0.683470f * g + 0.104310f * b;
//        final float s = 0.024828f * r + 0.064919f * g + 0.910230f * b;
////        final float l = (float) Math.pow(0.439020f * r + 0.512400f * g + 0.048586f * b, 0.43f);
////        final float m = (float) Math.pow(0.212140f * r + 0.683470f * g + 0.104310f * b, 0.43f);
////        final float s = (float) Math.pow(0.024828f * r + 0.064919f * g + 0.910230f * b, 0.43f);
//
////        final float l = (float) Math.pow(0.313921f * r + 0.639468f * g + 0.0465970f * b, 0.43f);
////        final float m = (float) Math.pow(0.151693f * r + 0.748209f * g + 0.1000044f * b, 0.43f);
////        final float s = (float) Math.pow(0.017700f * r + 0.109400f * g + 0.8729000f * b, 0.43f);
////        float                         i = 0.4000f * l + 0.4000f * m + 0.2000f * s;
////        float                         p = (568.0125f) * l - (618.5025f) * m + ( 50.4900f) * s;
////        float                         t = (102.7140f) * l + ( 45.5430f) * m - (148.2570f) * s;
//        //(852.01874f) * l - (927.7538f) * m + (75.7350f) * s
//        //(136.94775f) * l + (60.72825f) * m - (197.676f) * s
//        return NumberUtils.intBitsToFloat(
//                (int)(102.0f * l + 102.0f * m + 51.0f * s + 0.5f)
//                        | (int)((568.0125f) * l - (618.5025f) * m + ( 50.4900f) * s + 128f) << 8
//                        | (int)((102.7140f) * l + ( 45.5430f) * m - (148.2570f) * s + 128f) << 16
//                        | (rgba & 0xFE) << 24);
//    }
//    public static int toRGBA8888(final float packed)
//    {
//        final int decoded = NumberUtils.floatToRawIntBits(packed);
//        final float i = (decoded & 0xff) / 255f;
//        final float p = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
//        final float t = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
//        //final float l = i + 0.06503950f * p + 0.15391950f * t;
//        //final float m = i - 0.07591241f * p + 0.09991275f * t;
//        //final float s = i + 0.02174116f * p - 0.50766750f * t;
//
//        final float l = i + 0.097569f * p + 0.205226f * t;
//        final float m = i - 0.113880f * p + 0.133217f * t;
//        final float s = i + 0.032615f * p - 0.676890f * t;
////        final float l = Math.copySign((float) Math.pow(Math.abs(lPrime), 2.3256f), lPrime);
////        final float m = Math.copySign((float) Math.pow(Math.abs(mPrime), 2.3256f), mPrime);
////        final float s = Math.copySign((float) Math.pow(Math.abs(sPrime), 2.3256f), sPrime);
//        final int r = MathUtils.clamp((int) ((+3.569800f * l - 2.687500f * m + 0.11744f * s) * 256.0), 0, 255); // 3.569800f 2.687500f 0.11744f
//        final int g = MathUtils.clamp((int) ((-1.105200f * l + 2.311300f * m - 0.20588f * s) * 256.0), 0, 255); // 1.105200f 2.311300f 0.20588f
//        final int b = MathUtils.clamp((int) ((-0.018548f * l - 0.091536f * m + 1.11010f * s) * 256.0), 0, 255); // 0.018548f 0.091536f 1.11010f
////        final int r = MathUtils.clamp((int) ((5.432622 * l - 4.679100 * m + 0.246257 * s) * 256.0), 0, 255); // 3.569800 2.687500 0.11744
////        final int g = MathUtils.clamp((int) ((-1.10517 * l + 2.311198 * m - 0.205880 * s) * 256.0), 0, 255); // 1.105200 2.311300 0.20588
////        final int b = MathUtils.clamp((int) ((0.028104 * l - 0.194660 * m + 1.166325 * s) * 256.0), 0, 255); // 0.018548 0.091536 1.11010
//        return r << 24 | g << 16 | b << 8 | (decoded & 0xfe000000) >>> 24 | decoded >>> 31;
//    }

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
        String data = Gdx.files.classpath("AuroraColorData.txt").readString();
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
            System.out.println(rec[2] + " : correct RGBA=" + rec[1] + ", decoded RGBA=" + StringKit.hex(toRGBA8888(c)) + ", raw=" + StringKit.hex(NumberUtils.floatToRawIntBits(c))
//                    + ", decoded hue=" + ColorTools.hue(c) + ", decoded saturation=" + ColorTools.saturation(c) + ", decoded lightness=" + ColorTools.lightness(c)
                    + ", decoded intens=" + ColorTools.intensity(c) + ", decoded protan=" + (ColorTools.protan(c)*2f-1f) + ", decoded tritan=" + (ColorTools.tritan(c)*2f-1f)
            );
        }
        Gdx.files.local("ColorOutputIPT.txt").writeString(sb.toString(), false);

        String templateTable = "<tr>\n<td style='background-color: #FEDCBA;'></td>\n<td>Name</td>\n<td>0x`RGBA8888</td>\n<td>`INTENS</td>\n<td>`PROTAN</td>\n<td>`TRITAN</td>\n<td>`ALPH</td>\n<td>`HUE</td>\n<td>`SAT</td>\n<td>`PACKF</td>\n</tr>\n";
        final int size = Palette.NAMED.size;
        ArrayList<ObjectFloatMap.Entry<String>> PAL = new ArrayList<>(size);
        for(ObjectFloatMap.Entry<String> e : Palette.NAMED.entries())
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
        sb.append("<!doctype html>\n<html>\n<body>\n<table>\n<tr>\n<th>Preview Section</th>\n<th>Color Name</th>\n<th>Hex Code</th>\n<th>Intens</th>\n<th>Protan</th>\n<th>Tritan</th>\n<th>Alpha</th>\n<th>Hue</th>\n<th>Sat</th>\n<th>Packed</th>\n</tr>\n");
        for(ObjectFloatMap.Entry<String> sc : PAL) {
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
        Gdx.files.local("ColorTableIPT.html").writeString(sb.toString(), false);

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
                    return (int)Math.signum(ColorTools.intensity(c1.value) - ColorTools.intensity(c2.value));
                else
                    return 2 * (int)Math.signum(ColorTools.hue(c1.value) - ColorTools.hue(c2.value))
                            + (int)Math.signum(ColorTools.intensity(c1.value) - ColorTools.intensity(c2.value));
            }
        });
        sb.append("<!doctype html>\n<html>\n<body>\n<table>\n<tr>\n<th>Preview Section</th>\n<th>Color Name</th>\n<th>Hex Code</th>\n<th>Intens</th>\n<th>Protan</th>\n<th>Tritan</th>\n<th>Alpha</th>\n<th>Hue</th>\n<th>Sat</th>\n<th>Packed</th>\n</tr>\n");
        for(ObjectFloatMap.Entry<String> sc : PAL) {
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
        Gdx.files.local("ColorTableHueIPT.html").writeString(sb.toString(), false);

        sb.setLength(0);
        Collections.sort(PAL, new Comparator<ObjectFloatMap.Entry<String>>() {
            @Override
            public int compare(ObjectFloatMap.Entry<String> c1, ObjectFloatMap.Entry<String> c2) {
                return (int)Math.signum(ColorTools.intensity(c1.value) - ColorTools.intensity(c2.value));
            }
        });
        sb.append("<!doctype html>\n<html>\n<body>\n<table>\n<tr>\n<th>Preview Section</th>\n<th>Color Name</th>\n<th>Hex Code</th>\n<th>Intens</th>\n<th>Protan</th>\n<th>Tritan</th>\n<th>Alpha</th>\n<th>Hue</th>\n<th>Sat</th>\n<th>Packed</th>\n</tr>\n");
        for(ObjectFloatMap.Entry<String> sc : PAL) {
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
        Gdx.files.local("ColorTableValueIPT.html").writeString(sb.toString(), false);
        
        Gdx.app.exit();
    }
}
