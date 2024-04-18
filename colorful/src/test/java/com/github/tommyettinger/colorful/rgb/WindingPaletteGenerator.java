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

package com.github.tommyettinger.colorful.rgb;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Files;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.GdxNativesLoader;
import com.badlogic.gdx.utils.IntArray;
import com.github.tommyettinger.colorful.internal.StringKit;

// example output
/*
{
0x00000000, 0x000000FF, 0x00424EFF, 0x440030FF, 0x2F3600FF, 0x960000FF, 0xC60049FF, 0x624537FF,
0x9A0089FF, 0xBC3E79FF, 0xCC007EFF, 0xFF3941FF, 0xEE0000FF, 0x9F4A00FF, 0x9E9000FF, 0xA8614BFF,
0xE07800FF, 0xFFEE4DFF, 0xD5F300FF, 0xB2C34EFF, 0xECD800FF, 0x65FF00FF, 0x7D9731FF, 0x9AE597FF,
0x30ED6AFF, 0x00FF8EFF, 0x4AC039FF, 0x00FF00FF, 0x44E700FF, 0x009D3BFF, 0x006C00FF, 0x479C36FF,
0x8A8593FF, 0x5F2F99FF, 0x9500FFFF, 0xC70091FF, 0x9336FFFF, 0xFF00BFFF, 0xFC4BEDFF, 0xCB8CC3FF,
0xEC60E7FF, 0xEE7E67FF, 0xD5EEDCFF, 0xEEAE6FFF, 0xE1C888FF, 0xA8FFB8FF, 0xFFFFFDFF, 0xA093FFFF,
0x7677E7FF, 0x9DEED8FF, 0x65F4D4FF, 0x3DC3E4FF, 0x00FFC6FF, 0x00E2ECFF, 0x4198BFFF, 0x0071FFFF,
0x2F4EC0FF, 0x003FEBFF, 0x485F96FF, 0x008996FF, 0x474C6BFF, 0x00008DFF, 0x2F00B0FF, 0x0000FFFF,
}
 */
public class WindingPaletteGenerator {
    private static final int limit = 64;
    private static final IntArray rgba = new IntArray(limit);
    private static final int[] pukaX = new int[]
            {
                    0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 3, 3, 2, 2, 2, 3, 3, 4, 4, 4, 4, 4, 4, 3, 3, 2, 2,
                    3, 3, 4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 1, 1, 0, 0, 0, 1, 1, 0, 0,
                    1, 1, 0, 0, 0, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 3, 3, 4,
                    4, 4, 3, 3, 3, 4, 4, 3, 3, 4, 4, 3, 3, 2, 2, 2, 2, 2, 1, 1, 0, 0, 0, 0, 1, 1, 1,
                    0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0
            }, pukaY = new int[]
    {
        0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 2,
                2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 3, 3, 3, 4, 4, 3, 3, 4, 4, 3, 3, 4, 4, 4, 4, 4, 3,
                3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 2, 2, 2, 2, 2,
                3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 3, 3, 2, 2, 3, 3, 4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 2,
                2, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 0, 0, 0, 0, 0, 0
    }, pukaZ = new int[]
    {
        0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 1, 1, 0, 0, 0, 0, 0, 0,
                0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 2, 2, 2, 2, 2, 2, 1, 1, 0, 0, 0,
                0, 1, 1, 1, 0, 0, 1, 1, 2, 3, 3, 4, 4, 3, 3, 3, 4, 4, 4, 3, 3, 4, 4, 3, 3, 2, 2,
                2, 3, 3, 2, 2, 2, 3, 3, 4, 4, 4, 4, 4, 4, 4, 3, 3, 4, 4, 3, 3, 4, 4, 3, 3, 4, 4,
                4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 3, 3, 4, 4
    };
    private static int prepare(int n, double pow) {
        // RGB
//        return (int)(Math.pow((1.0 / (1.0 + Math.pow(3, 1.5 - n)) - 0.16139047779640892) * 1.2848076211353316, pow) * 255);
        // evenly distributed
//        return n * 64 + (-n >> 31);
        // weird
        pow = Math.sin(pow);
        return Math.min(255, (int) ((n * 64 + (-n >> 31)) * (pow * pow * 0.5 + 0.75)));
    }
    /**
     * Converts the four HSLA components, each in the 0.0 to 1.0 range, to an int in RGBA8888 format.
     * I brought this over from colorful-gdx's FloatColors class. I can't recall where I got the original HSL(A) code
     * from, but there's a strong chance it was written by cypherdare/cyphercove for their color space comparison.
     * The {@code h} parameter for hue can be lower than 0.0 or higher than 1.0 because the hue "wraps around;" only the
     * fractional part of h is used. The other parameters must be between 0.0 and 1.0 (inclusive) to make sense.
     *
     * @param h hue, usually from 0.0 to 1.0, but only the fractional part is used
     * @param s saturation, from 0.0 to 1.0
     * @param l lightness, from 0.0 to 1.0
     * @param a alpha, from 0.0 to 1.0
     * @return an RGBA8888-format int
     */
    public static int hsl2rgb(final float h, final float s, final float l, final float a) {
        float hue = h - MathUtils.floor(h);
        float x = Math.min(Math.max(Math.abs(hue * 6f - 3f) - 1f, 0f), 1f);
        float y = hue + 2f / 3f;
        float z = hue + 1f / 3f;
        y -= (int) y;
        z -= (int) z;
        y = Math.min(Math.max(Math.abs(y * 6f - 3f) - 1f, 0f), 1f);
        z = Math.min(Math.max(Math.abs(z * 6f - 3f) - 1f, 0f), 1f);
        float v = l + s * Math.min(l, 1f - l);
        float d = 2f * (1f - l / (v + 1e-10f));
        v *= 255.999f;
        return (int)(v*(1f+(x-1f)*d)) << 24 | (int)(v*(1f+(y-1f)*d)) << 16 | (int)(v*(1f+(z-1f)*d)) << 8 | (int)(a * 255.999f);
    }

    public static void main(String[] args) {
        GdxNativesLoader.load();
        rgba.add(0);
        for (int i = 0; i < 125; i+=2) {
            int color = prepare(pukaX[i], i * 2.6) << 24 | prepare(pukaY[i], i * 2.7) << 16 | prepare(pukaZ[i], i * 2.4) << 8 | 0xFF;
//            int color = hsl2rgb((pukaX[i] * 0.25f), (pukaY[i] * 0.25f), (pukaZ[i] * 0.25f), 1f);
            rgba.add(color);
        }

        StringBuilder sb = new StringBuilder(12 * rgba.size + 35).append("{\n");
        for (int i = 0; i < rgba.size; i++) {
            StringKit.appendHex(sb.append("0x"), rgba.get(i)).append(", ");
            if(7 == (i & 7)) sb.append('\n');
        }
        System.out.println(sb.append('}'));

        Pixmap palette = new Pixmap(rgba.size, 1, Pixmap.Format.RGBA8888);
        for (int i = 0; i < rgba.size; i++) {
            palette.drawPixel(i, 0, rgba.get(i));
        }
        Lwjgl3Files files = new Lwjgl3Files();
        String name = "Winding-" + System.currentTimeMillis() + ".png";
        PixmapIO.writePNG(files.local(name), palette);
        System.out.println("Wrote to " + name);
        palette.dispose();
    }
}
