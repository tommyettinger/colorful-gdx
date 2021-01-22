package com.github.tommyettinger.colorful.rgb.internal;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectFloatMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.OrderedMap;
import com.github.tommyettinger.colorful.internal.StringKit;
import com.github.tommyettinger.colorful.rgb.ColorTools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * A tool, not a demo, used to generate the data used in Palette and in the javadocs.
 * Created by Tommy Ettinger on 12/8/2019.
 */
public class GDXPaletteCodeGenerator extends ApplicationAdapter {
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

        new Lwjgl3Application(new GDXPaletteCodeGenerator(), config);
    }
    public void create() {

        float c;
        StringBuilder sb = new StringBuilder(1024);
        String templateTable = "<tr>\n<td style='background-color: #FEDCBA;'></td>\n<td>Name</td>\n<td>0x`RGBA8888</td>\n<td>`RED</td>\n<td>`GREEN</td>\n<td>`BLUE</td>\n<td>`HUE</td>\n<td>`SAT</td>\n<td>`LUMA</td>\n<td>`ALPH</td>\n<td>`PACK</td>\n</tr>\n";
        ObjectMap<String, Color> colors = Colors.getColors();
        final int size = colors.size;
        ObjectFloatMap<String> palette = new ObjectFloatMap<>(size);
        for(String name : colors.keys())
            palette.put(name, colors.get(name).toFloatBits());
        Array<String> named = palette.keys().toArray();
        named.sort();
        ArrayList<ObjectFloatMap.Entry<String>> PAL = new ArrayList<>(size);
        for(String name : named)
        {
            ObjectFloatMap.Entry<String> ee = new ObjectFloatMap.Entry<>();
            ee.key = name;
            ee.value = palette.get(name, 0f);
            PAL.add(ee);
        }

        sb.setLength(0);
        Collections.sort(PAL, new Comparator<ObjectFloatMap.Entry<String>>() {
            @Override
            public int compare(ObjectFloatMap.Entry<String> c1, ObjectFloatMap.Entry<String> c2) {
                return c1.key.compareTo(c2.key);
            }
        });
        sb.append("<!doctype html>\n<html>\n<body>\n<table>\n<tr>\n<th>Preview Section</th>\n<th>Color Name</th>\n<th>Hex Code</th>\n<th>R</th>\n<th>G</th>\n<th>B</th>\n<th>Hue</th>\n<th>Sat</th>\n<th>Light</th>\n<th>Alpha</th>\n<th>Packed</th>\n</tr>\n");
        for(ObjectFloatMap.Entry<String> sc : PAL) {
            c = sc.value;
            sb.append(templateTable.replace("Name", sc.key)
                    .replace("`RGBA8888", StringKit.hex(ColorTools.toRGBA8888(c)))
                    .replace("FEDCBA", StringKit.hex(ColorTools.toRGBA8888(c)).substring(0, 6))
                    .replace("`RED", Float.toString(ColorTools.red(c)))
                    .replace("`GREEN", Float.toString(ColorTools.green(c)))
                    .replace("`BLUE", Float.toString(ColorTools.blue(c)))
                    .replace("`HUE", Float.toString(ColorTools.hue(c)))
                    .replace("`SAT", Float.toString(ColorTools.saturation(c)))
                    .replace("`LUMA", Float.toString(ColorTools.lightness(c)))
                    .replace("`ALPH", Float.toString(ColorTools.alpha(c)))
                    .replace("`PACK", Float.toHexString(c))
            );
        }
        sb.append("</table>\n</body>\n</html>");
        Gdx.files.local("ColorTableGDX.html").writeString(sb.toString(), false);

        sb.setLength(0);
        Collections.sort(PAL, new Comparator<ObjectFloatMap.Entry<String>>() {
            @Override
            public int compare(ObjectFloatMap.Entry<String> c1, ObjectFloatMap.Entry<String> c2) {
                float s1 = ColorTools.saturation(c1.value), s2 = ColorTools.saturation(c2.value);
                if(ColorTools.alphaInt(c1.value) < 128) return -10000;
                else if(ColorTools.alphaInt(c2.value) < 128) return 10000;
                if(s1 <= 0x1p-6f && s2 > 0x1p-6f)
                    return -1000;
                else if(s1 > 0x1p-6f && s2 <= 0x1p-6f)
                    return 1000;
                else if(s1 <= 0x1p-6f && s2 <= 0x1p-6f)
                    return (int)Math.signum(ColorTools.lightness(c1.value) - ColorTools.lightness(c2.value));
                else
                    return 2 * (int)Math.signum(ColorTools.hue(c1.value) - ColorTools.hue(c2.value))
                            + (int)Math.signum(ColorTools.lightness(c1.value) - ColorTools.lightness(c2.value));
            }
        });
        sb.append("<!doctype html>\n<html>\n<body>\n<table>\n<tr>\n<th>Preview Section</th>\n<th>Color Name</th>\n<th>Hex Code</th>\n<th>Red</th>\n<th>Green</th>\n<th>Blue</th>\n<th>Hue</th>\n<th>Sat</th>\n<th>Light</th>\n<th>Alpha</th>\n<th>Packed</th>\n</tr>\n");
        for(ObjectFloatMap.Entry<String> sc : PAL) {
            c = sc.value;
            sb.append(templateTable.replace("Name", sc.key)
                    .replace("`RGBA8888", StringKit.hex(ColorTools.toRGBA8888(c)))
                    .replace("FEDCBA", StringKit.hex(ColorTools.toRGBA8888(c)).substring(0, 6))
                    .replace("`RED", Float.toString(ColorTools.red(c)))
                    .replace("`GREEN", Float.toString(ColorTools.green(c)))
                    .replace("`BLUE", Float.toString(ColorTools.blue(c)))
                    .replace("`HUE", Float.toString(ColorTools.hue(c)))
                    .replace("`SAT", Float.toString(ColorTools.saturation(c)))
                    .replace("`LUMA", Float.toString(ColorTools.lightness(c)))
                    .replace("`ALPH", Float.toString(ColorTools.alpha(c)))
                    .replace("`PACK", Float.toHexString(c))
            );
        }
        sb.append("</table>\n</body>\n</html>");
        Gdx.files.local("ColorTableHueGDX.html").writeString(sb.toString(), false);

        sb.setLength(0);
        Collections.sort(PAL, new Comparator<ObjectFloatMap.Entry<String>>() {
            @Override
            public int compare(ObjectFloatMap.Entry<String> c1, ObjectFloatMap.Entry<String> c2) {
                return (int)Math.signum(ColorTools.lightness(c1.value) - ColorTools.lightness(c2.value));
            }
        });
        sb.append("<!doctype html>\n<html>\n<body>\n<table>\n<tr>\n<th>Preview Section</th>\n<th>Color Name</th>\n<th>Hex Code</th>\n<th>Red</th>\n<th>Green</th>\n<th>Blue</th>\n<th>Hue</th>\n<th>Sat</th>\n<th>Light</th>\n<th>Alpha</th>\n<th>Packed</th>\n</tr>\n");
        for(ObjectFloatMap.Entry<String> sc : PAL) {
            c = sc.value;
            sb.append(templateTable.replace("Name", sc.key)
                    .replace("`RGBA8888", StringKit.hex(ColorTools.toRGBA8888(c)))
                    .replace("FEDCBA", StringKit.hex(ColorTools.toRGBA8888(c)).substring(0, 6))
                    .replace("`HUE", Float.toString(ColorTools.hue(c)))
                    .replace("`SAT", Float.toString(ColorTools.saturation(c)))
                    .replace("`LUMA", Float.toString(ColorTools.lightness(c)))
                    .replace("`ALPH", Float.toString(ColorTools.alpha(c)))
                    .replace("`PACK", Float.toHexString(c))
            );
        }
        sb.append("</table>\n</body>\n</html>");
        Gdx.files.local("ColorTableValueGDX.html").writeString(sb.toString(), false);

        Gdx.app.exit();
    }
}
