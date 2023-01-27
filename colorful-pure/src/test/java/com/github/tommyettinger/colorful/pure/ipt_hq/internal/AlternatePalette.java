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

package com.github.tommyettinger.colorful.pure.ipt_hq.internal;

import com.github.tommyettinger.ds.*;

/**
 * A palette of predefined colors as packed IPT ints, and tools for obtaining IPT int colors from a description.
 * You can access colors by their constant name, such as {@code cactus}, by the {@link #NAMED} map using
 * {@code NAMED.get("cactus")}, by getting a color by its name's position in alphabetical order with
 * {@code NAMED.getAt(12)}, or by index in the IntList called {@link #LIST}. When accessing a color with
 * {@link ObjectIntOrderedMap#get(Object)}, if the name is not found, get() will return {@link #TRANSPARENT}. If you
 * want to control the not-found value, you can use {@link ObjectIntOrderedMap#getOrDefault(Object, int)}. You can
 * access the names in a specific order with {@link #NAMES} (which is alphabetical), {@link #NAMES_BY_HUE} (which is
 * sorted by the hue of the matching color, from red to yellow to blue (with gray around here) to purple to red again),
 * or {@link #NAMES_BY_LIGHTNESS} (which is sorted by the intensity of the matching color, from darkest to lightest).
 * Having a name lets you look up the matching color in {@link #NAMED}. You can also modify or re-order NAMED if you
 * want to, such as to add more named colors.
 */
public final class AlternatePalette {
    /**
     * No need to extend this.
     */
    private AlternatePalette() {
    }
    public static final ObjectIntOrderedMap<String> NAMED = new ObjectIntOrderedMap<String>(50);
    public static final IntList LIST = new IntList(50);

    /**
     * This color constant "transparent" has RGBA8888 code {@code 00000000}, intensity 0.0, protan 0.49803922, tritan 0.49803922, alpha 0.0, hue 0.6666667, and saturation 0.0.
     * It has the encoded IPT value 0x007f7f00 .
     * <pre>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #000000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRANSPARENT = 0x007f7f00;

    static {
        NAMED.put("transparent", 0x007f7f00);
        LIST.add(0x007f7f00);
    }

    /**
     * This color constant "black" has RGBA8888 code {@code 000000FF}, intensity 0.0, protan 0.49803922, tritan 0.49803922, alpha 1.0, hue 0.6666667, and saturation 0.0.
     * It has the encoded IPT value 0xfe7f7f00 .
     * <pre>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #000000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BLACK = 0xfe7f7f00;

    static {
        NAMED.put("black", 0xfe7f7f00);
        LIST.add(0xfe7f7f00);
    }

    /**
     * This color constant "gray" has RGBA8888 code {@code 808080FF}, intensity 0.5529412, protan 0.5019608, tritan 0.49803922, alpha 1.0, hue 0.81858563, and saturation 0.005999118.
     * It has the encoded IPT value 0xfe7f808d .
     * <pre>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #808080; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #808080; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #808080; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int GRAY = 0xfe7f808d;

    static {
        NAMED.put("gray", 0xfe7f808d);
        LIST.add(0xfe7f808d);
    }

    /**
     * This color constant "silver" has RGBA8888 code {@code B6B6B6FF}, intensity 0.7490196, protan 0.5019608, tritan 0.49803922, alpha 1.0, hue 0.81859344, and saturation 0.0062664747.
     * It has the encoded IPT value 0xfe7f80bf .
     * <pre>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B6B6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #B6B6B6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B6B6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int SILVER = 0xfe7f80bf;

    static {
        NAMED.put("silver", 0xfe7f80bf);
        LIST.add(0xfe7f80bf);
    }

    /**
     * This color constant "white" has RGBA8888 code {@code FFFFFFFF}, intensity 1.0, protan 0.5019608, tritan 0.49803922, alpha 1.0, hue 0.8333333, and saturation 0.0.
     * It has the encoded IPT value 0xfe7f80ff .
     * <pre>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #FFFFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int WHITE = 0xfe7f80ff;

    static {
        NAMED.put("white", 0xfe7f80ff);
        LIST.add(0xfe7f80ff);
    }

    /**
     * This color constant "red" has RGBA8888 code {@code FF0000FF}, intensity 0.45490196, protan 0.8117647, tritan 0.72156864, alpha 1.0, hue 0.0, and saturation 0.9997024.
     * It has the encoded IPT value 0xfeb8cf74 .
     * <pre>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #FF0000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int RED = 0xfeb8cf74;

    static {
        NAMED.put("red", 0xfeb8cf74);
        LIST.add(0xfeb8cf74);
    }

    /**
     * This color constant "orange" has RGBA8888 code {@code FF7F00FF}, intensity 0.5921569, protan 0.64705884, tritan 0.7529412, alpha 1.0, hue 0.08270054, and saturation 1.0.
     * It has the encoded IPT value 0xfec0a597 .
     * <pre>
     * <font style='background-color: #FF7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF7F00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF7F00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF7F00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF7F00'>&nbsp;@&nbsp;</font><font style='background-color: #FF7F00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF7F00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int ORANGE = 0xfec0a597;

    static {
        NAMED.put("orange", 0xfec0a597);
        LIST.add(0xfec0a597);
    }

    /**
     * This color constant "yellow" has RGBA8888 code {@code FFFF00FF}, intensity 0.85882354, protan 0.44705883, tritan 0.827451, alpha 1.0, hue 0.16666667, and saturation 1.0.
     * It has the encoded IPT value 0xfed372db .
     * <pre>
     * <font style='background-color: #FFFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFF00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFF00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFF00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFF00'>&nbsp;@&nbsp;</font><font style='background-color: #FFFF00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFF00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int YELLOW = 0xfed372db;

    static {
        NAMED.put("yellow", 0xfed372db);
        LIST.add(0xfed372db);
    }

    /**
     * This color constant "green" has RGBA8888 code {@code 00FF00FF}, intensity 0.7607843, protan 0.27058825, tritan 0.7647059, alpha 1.0, hue 0.33734334, and saturation 1.0.
     * It has the encoded IPT value 0xfec345c2 .
     * <pre>
     * <font style='background-color: #00FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FF00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FF00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FF00'>&nbsp;@&nbsp;</font><font style='background-color: #00FF00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int GREEN = 0xfec345c2;

    static {
        NAMED.put("green", 0xfec345c2);
        LIST.add(0xfec345c2);
    }

    /**
     * This color constant "blue" has RGBA8888 code {@code 0000FFFF}, intensity 0.44313726, protan 0.38039216, tritan 0.1254902, alpha 1.0, hue 0.6666667, and saturation 0.9989025.
     * It has the encoded IPT value 0xfe206171 .
     * <pre>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0000FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #0000FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0000FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BLUE = 0xfe206171;

    static {
        NAMED.put("blue", 0xfe206171);
        LIST.add(0xfe206171);
    }

    /**
     * This color constant "indigo" has RGBA8888 code {@code 520FE0FF}, intensity 0.44313726, protan 0.49803922, tritan 0.2, alpha 1.0, hue 0.72370124, and saturation 0.84834015.
     * It has the encoded IPT value 0xfe337f71 .
     * <pre>
     * <font style='background-color: #520FE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #520FE0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #520FE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #520FE0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #520FE0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #520FE0'>&nbsp;@&nbsp;</font><font style='background-color: #520FE0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #520FE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #520FE0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int INDIGO = 0xfe337f71;

    static {
        NAMED.put("indigo", 0xfe337f71);
        LIST.add(0xfe337f71);
    }

    /**
     * This color constant "violet" has RGBA8888 code {@code 9040EFFF}, intensity 0.5647059, protan 0.5764706, tritan 0.25490198, alpha 1.0, hue 0.7423505, and saturation 0.69213295.
     * It has the encoded IPT value 0xfe419390 .
     * <pre>
     * <font style='background-color: #9040EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9040EF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9040EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9040EF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9040EF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9040EF'>&nbsp;@&nbsp;</font><font style='background-color: #9040EF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9040EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9040EF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int VIOLET = 0xfe419390;

    static {
        NAMED.put("violet", 0xfe419390);
        LIST.add(0xfe419390);
    }

    /**
     * This color constant "purple" has RGBA8888 code {@code C000FFFF}, intensity 0.59607846, protan 0.68235296, tritan 0.24705882, alpha 1.0, hue 0.7911109, and saturation 0.96850824.
     * It has the encoded IPT value 0xfe3fae98 .
     * <pre>
     * <font style='background-color: #C000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C000FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C000FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C000FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C000FF'>&nbsp;@&nbsp;</font><font style='background-color: #C000FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C000FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int PURPLE = 0xfe3fae98;

    static {
        NAMED.put("purple", 0xfe3fae98);
        LIST.add(0xfe3fae98);
    }

    /**
     * This color constant "brown" has RGBA8888 code {@code 8F573BFF}, intensity 0.42352942, protan 0.57254905, tritan 0.5882353, alpha 1.0, hue 0.056629464, and saturation 0.3289587.
     * It has the encoded IPT value 0xfe96926c .
     * <pre>
     * <font style='background-color: #8F573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F573B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F573B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F573B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F573B'>&nbsp;@&nbsp;</font><font style='background-color: #8F573B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F573B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BROWN = 0xfe96926c;

    static {
        NAMED.put("brown", 0xfe96926c);
        LIST.add(0xfe96926c);
    }

    /**
     * This color constant "pink" has RGBA8888 code {@code FFA0E0FF}, intensity 0.8, protan 0.627451, tritan 0.4509804, alpha 1.0, hue 0.8885865, and saturation 0.37119424.
     * It has the encoded IPT value 0xfe73a0cc .
     * <pre>
     * <font style='background-color: #FFA0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA0E0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFA0E0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFA0E0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFA0E0'>&nbsp;@&nbsp;</font><font style='background-color: #FFA0E0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA0E0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int PINK = 0xfe73a0cc;

    static {
        NAMED.put("pink", 0xfe73a0cc);
        LIST.add(0xfe73a0cc);
    }

    /**
     * This color constant "magenta" has RGBA8888 code {@code F500F5FF}, intensity 0.64705884, protan 0.7764706, tritan 0.30980393, alpha 1.0, hue 0.83288956, and saturation 0.96499586.
     * It has the encoded IPT value 0xfe4fc6a5 .
     * <pre>
     * <font style='background-color: #F500F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F500F5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F500F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F500F5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F500F5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F500F5'>&nbsp;@&nbsp;</font><font style='background-color: #F500F5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F500F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F500F5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int MAGENTA = 0xfe4fc6a5;

    static {
        NAMED.put("magenta", 0xfe4fc6a5);
        LIST.add(0xfe4fc6a5);
    }

    /**
     * This color constant "brick" has RGBA8888 code {@code D5524AFF}, intensity 0.5058824, protan 0.6862745, tritan 0.6117647, alpha 1.0, hue 0.010635166, and saturation 0.54750645.
     * It has the encoded IPT value 0xfe9caf81 .
     * <pre>
     * <font style='background-color: #D5524A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5524A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5524A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D5524A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D5524A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D5524A'>&nbsp;@&nbsp;</font><font style='background-color: #D5524A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5524A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5524A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRICK = 0xfe9caf81;

    static {
        NAMED.put("brick", 0xfe9caf81);
        LIST.add(0xfe9caf81);
    }

    /**
     * This color constant "ember" has RGBA8888 code {@code F55A32FF}, intensity 0.5372549, protan 0.70980394, tritan 0.68235296, alpha 1.0, hue 0.034249328, and saturation 0.7679914.
     * It has the encoded IPT value 0xfeaeb589 .
     * <pre>
     * <font style='background-color: #F55A32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F55A32; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F55A32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F55A32'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F55A32'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F55A32'>&nbsp;@&nbsp;</font><font style='background-color: #F55A32; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F55A32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F55A32; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int EMBER = 0xfeaeb589;

    static {
        NAMED.put("ember", 0xfeaeb589);
        LIST.add(0xfeaeb589);
    }

    /**
     * This color constant "salmon" has RGBA8888 code {@code FF6262FF}, intensity 0.59607846, protan 0.72156864, tritan 0.6117647, alpha 1.0, hue 0.99928015, and saturation 0.616992.
     * It has the encoded IPT value 0xfe9cb898 .
     * <pre>
     * <font style='background-color: #FF6262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6262; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF6262'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF6262'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF6262'>&nbsp;@&nbsp;</font><font style='background-color: #FF6262; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6262; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int SALMON = 0xfe9cb898;

    static {
        NAMED.put("salmon", 0xfe9cb898);
        LIST.add(0xfe9cb898);
    }

    /**
     * This color constant "chocolate" has RGBA8888 code {@code 683818FF}, intensity 0.2901961, protan 0.5647059, tritan 0.59607846, alpha 1.0, hue 0.067405425, and saturation 0.31821468.
     * It has the encoded IPT value 0xfe98904a .
     * <pre>
     * <font style='background-color: #683818;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #683818; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #683818;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #683818'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #683818'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #683818'>&nbsp;@&nbsp;</font><font style='background-color: #683818; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #683818;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #683818; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int CHOCOLATE = 0xfe98904a;

    static {
        NAMED.put("chocolate", 0xfe98904a);
        LIST.add(0xfe98904a);
    }

    /**
     * This color constant "tan" has RGBA8888 code {@code D2B48CFF}, intensity 0.73333335, protan 0.5294118, tritan 0.58431375, alpha 1.0, hue 0.09675702, and saturation 0.27724016.
     * It has the encoded IPT value 0xfe9587bb .
     * <pre>
     * <font style='background-color: #D2B48C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2B48C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2B48C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D2B48C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D2B48C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D2B48C'>&nbsp;@&nbsp;</font><font style='background-color: #D2B48C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2B48C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2B48C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TAN = 0xfe9587bb;

    static {
        NAMED.put("tan", 0xfe9587bb);
        LIST.add(0xfe9587bb);
    }

    /**
     * This color constant "bronze" has RGBA8888 code {@code CE8E31FF}, intensity 0.5882353, protan 0.56078434, tritan 0.6901961, alpha 1.0, hue 0.09874619, and saturation 0.61364305.
     * It has the encoded IPT value 0xfeb08f96 .
     * <pre>
     * <font style='background-color: #CE8E31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CE8E31; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CE8E31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CE8E31'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CE8E31'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CE8E31'>&nbsp;@&nbsp;</font><font style='background-color: #CE8E31; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CE8E31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CE8E31; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRONZE = 0xfeb08f96;

    static {
        NAMED.put("bronze", 0xfeb08f96);
        LIST.add(0xfeb08f96);
    }

    /**
     * This color constant "cinnamon" has RGBA8888 code {@code D2691DFF}, intensity 0.50980395, protan 0.627451, tritan 0.69411767, alpha 1.0, hue 0.0698809, and saturation 0.71087015.
     * It has the encoded IPT value 0xfeb1a082 .
     * <pre>
     * <font style='background-color: #D2691D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2691D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2691D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D2691D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D2691D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D2691D'>&nbsp;@&nbsp;</font><font style='background-color: #D2691D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2691D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2691D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int CINNAMON = 0xfeb1a082;

    static {
        NAMED.put("cinnamon", 0xfeb1a082);
        LIST.add(0xfeb1a082);
    }

    /**
     * This color constant "apricot" has RGBA8888 code {@code FFA828FF}, intensity 0.68235296, protan 0.5803922, tritan 0.7490196, alpha 1.0, hue 0.09959209, and saturation 0.8424464.
     * It has the encoded IPT value 0xfebf94ae .
     * <pre>
     * <font style='background-color: #FFA828;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA828; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA828;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFA828'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFA828'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFA828'>&nbsp;@&nbsp;</font><font style='background-color: #FFA828; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA828;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA828; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int APRICOT = 0xfebf94ae;

    static {
        NAMED.put("apricot", 0xfebf94ae);
        LIST.add(0xfebf94ae);
    }

    /**
     * This color constant "peach" has RGBA8888 code {@code FFBF81FF}, intensity 0.78039217, protan 0.5686275, tritan 0.6392157, alpha 1.0, hue 0.082601294, and saturation 0.49658114.
     * It has the encoded IPT value 0xfea391c7 .
     * <pre>
     * <font style='background-color: #FFBF81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFBF81; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFBF81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFBF81'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFBF81'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFBF81'>&nbsp;@&nbsp;</font><font style='background-color: #FFBF81; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFBF81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFBF81; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int PEACH = 0xfea391c7;

    static {
        NAMED.put("peach", 0xfea391c7);
        LIST.add(0xfea391c7);
    }

    /**
     * This color constant "pear" has RGBA8888 code {@code D3E330FF}, intensity 0.7764706, protan 0.4392157, tritan 0.7647059, alpha 1.0, hue 0.18091057, and saturation 0.7110761.
     * It has the encoded IPT value 0xfec370c6 .
     * <pre>
     * <font style='background-color: #D3E330;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3E330; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3E330;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D3E330'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D3E330'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D3E330'>&nbsp;@&nbsp;</font><font style='background-color: #D3E330; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3E330;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3E330; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int PEAR = 0xfec370c6;

    static {
        NAMED.put("pear", 0xfec370c6);
        LIST.add(0xfec370c6);
    }

    /**
     * This color constant "saffron" has RGBA8888 code {@code FFD510FF}, intensity 0.76862746, protan 0.5058824, tritan 0.79607844, alpha 1.0, hue 0.13782778, and saturation 0.9391338.
     * It has the encoded IPT value 0xfecb81c4 .
     * <pre>
     * <font style='background-color: #FFD510;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD510; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD510;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFD510'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFD510'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFD510'>&nbsp;@&nbsp;</font><font style='background-color: #FFD510; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD510;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD510; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int SAFFRON = 0xfecb81c4;

    static {
        NAMED.put("saffron", 0xfecb81c4);
        LIST.add(0xfecb81c4);
    }

    /**
     * This color constant "butter" has RGBA8888 code {@code FFF288FF}, intensity 0.8862745, protan 0.49411765, tritan 0.6745098, alpha 1.0, hue 0.14835475, and saturation 0.46378756.
     * It has the encoded IPT value 0xfeac7ee2 .
     * <pre>
     * <font style='background-color: #FFF288;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFF288; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFF288;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFF288'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFF288'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFF288'>&nbsp;@&nbsp;</font><font style='background-color: #FFF288; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFF288;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFF288; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BUTTER = 0xfeac7ee2;

    static {
        NAMED.put("butter", 0xfeac7ee2);
        LIST.add(0xfeac7ee2);
    }

    /**
     * This color constant "chartreuse" has RGBA8888 code {@code C8FF41FF}, intensity 0.8392157, protan 0.39607844, tritan 0.7647059, alpha 1.0, hue 0.21423629, and saturation 0.7483187.
     * It has the encoded IPT value 0xfec365d6 .
     * <pre>
     * <font style='background-color: #C8FF41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8FF41; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8FF41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C8FF41'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C8FF41'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C8FF41'>&nbsp;@&nbsp;</font><font style='background-color: #C8FF41; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8FF41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8FF41; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int CHARTREUSE = 0xfec365d6;

    static {
        NAMED.put("chartreuse", 0xfec365d6);
        LIST.add(0xfec365d6);
    }

    /**
     * This color constant "cactus" has RGBA8888 code {@code 30A000FF}, intensity 0.5137255, protan 0.36078432, tritan 0.68235296, alpha 1.0, hue 0.28286904, and saturation 0.6252039.
     * It has the encoded IPT value 0xfeae5c83 .
     * <pre>
     * <font style='background-color: #30A000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #30A000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #30A000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #30A000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #30A000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #30A000'>&nbsp;@&nbsp;</font><font style='background-color: #30A000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #30A000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #30A000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int CACTUS = 0xfeae5c83;

    static {
        NAMED.put("cactus", 0xfeae5c83);
        LIST.add(0xfeae5c83);
    }

    /**
     * This color constant "lime" has RGBA8888 code {@code 93D300FF}, intensity 0.6901961, protan 0.38431373, tritan 0.7529412, alpha 1.0, hue 0.21732643, and saturation 0.83122355.
     * It has the encoded IPT value 0xfec062b0 .
     * <pre>
     * <font style='background-color: #93D300;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #93D300; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #93D300;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #93D300'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #93D300'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #93D300'>&nbsp;@&nbsp;</font><font style='background-color: #93D300; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #93D300;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #93D300; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int LIME = 0xfec062b0;

    static {
        NAMED.put("lime", 0xfec062b0);
        LIST.add(0xfec062b0);
    }

    /**
     * This color constant "olive" has RGBA8888 code {@code 818000FF}, intensity 0.4745098, protan 0.47058824, tritan 0.68235296, alpha 1.0, hue 0.16567872, and saturation 0.5062155.
     * It has the encoded IPT value 0xfeae7879 .
     * <pre>
     * <font style='background-color: #818000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #818000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #818000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #818000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #818000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #818000'>&nbsp;@&nbsp;</font><font style='background-color: #818000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #818000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #818000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int OLIVE = 0xfeae7879;

    static {
        NAMED.put("olive", 0xfeae7879);
        LIST.add(0xfeae7879);
    }

    /**
     * This color constant "fern" has RGBA8888 code {@code 4E7942FF}, intensity 0.45490196, protan 0.4392157, tritan 0.5764706, alpha 1.0, hue 0.29822132, and saturation 0.21422556.
     * It has the encoded IPT value 0xfe937074 .
     * <pre>
     * <font style='background-color: #4E7942;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E7942; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E7942;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4E7942'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4E7942'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4E7942'>&nbsp;@&nbsp;</font><font style='background-color: #4E7942; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E7942;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E7942; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int FERN = 0xfe937074;

    static {
        NAMED.put("fern", 0xfe937074);
        LIST.add(0xfe937074);
    }

    /**
     * This color constant "moss" has RGBA8888 code {@code 204608FF}, intensity 0.25882354, protan 0.4392157, tritan 0.5882353, alpha 1.0, hue 0.27203476, and saturation 0.241091.
     * It has the encoded IPT value 0xfe967042 .
     * <pre>
     * <font style='background-color: #204608;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #204608; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #204608;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #204608'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #204608'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #204608'>&nbsp;@&nbsp;</font><font style='background-color: #204608; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #204608;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #204608; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int MOSS = 0xfe967042;

    static {
        NAMED.put("moss", 0xfe967042);
        LIST.add(0xfe967042);
    }

    /**
     * This color constant "celery" has RGBA8888 code {@code 7DFF73FF}, intensity 0.83137256, protan 0.34509805, tritan 0.67058825, alpha 1.0, hue 0.32334346, and saturation 0.54658806.
     * It has the encoded IPT value 0xfeab58d4 .
     * <pre>
     * <font style='background-color: #7DFF73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7DFF73; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7DFF73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7DFF73'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7DFF73'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7DFF73'>&nbsp;@&nbsp;</font><font style='background-color: #7DFF73; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7DFF73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7DFF73; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int CELERY = 0xfeab58d4;

    static {
        NAMED.put("celery", 0xfeab58d4);
        LIST.add(0xfeab58d4);
    }

    /**
     * This color constant "sage" has RGBA8888 code {@code ABE3C5FF}, intensity 0.8509804, protan 0.43529412, tritan 0.5254902, alpha 1.0, hue 0.40719464, and saturation 0.2204172.
     * It has the encoded IPT value 0xfe866fd9 .
     * <pre>
     * <font style='background-color: #ABE3C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABE3C5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABE3C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ABE3C5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ABE3C5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ABE3C5'>&nbsp;@&nbsp;</font><font style='background-color: #ABE3C5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABE3C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABE3C5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int SAGE = 0xfe866fd9;

    static {
        NAMED.put("sage", 0xfe866fd9);
        LIST.add(0xfe866fd9);
    }

    /**
     * This color constant "jade" has RGBA8888 code {@code 3FBF3FFF}, intensity 0.62352943, protan 0.3529412, tritan 0.6627451, alpha 1.0, hue 0.33068874, and saturation 0.50730336.
     * It has the encoded IPT value 0xfea95a9f .
     * <pre>
     * <font style='background-color: #3FBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3FBF3F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3FBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3FBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3FBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3FBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #3FBF3F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3FBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3FBF3F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int JADE = 0xfea95a9f;

    static {
        NAMED.put("jade", 0xfea95a9f);
        LIST.add(0xfea95a9f);
    }

    /**
     * This color constant "cyan" has RGBA8888 code {@code 00FFFFFF}, intensity 0.9137255, protan 0.32941177, tritan 0.43137255, alpha 1.0, hue 0.5, and saturation 1.0.
     * It has the encoded IPT value 0xfe6e54e9 .
     * <pre>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #00FFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int CYAN = 0xfe6e54e9;

    static {
        NAMED.put("cyan", 0xfe6e54e9);
        LIST.add(0xfe6e54e9);
    }

    /**
     * This color constant "mint" has RGBA8888 code {@code 7FFFD4FF}, intensity 0.9019608, protan 0.37254903, tritan 0.5176471, alpha 1.0, hue 0.44493762, and saturation 0.49903446.
     * It has the encoded IPT value 0xfe845fe6 .
     * <pre>
     * <font style='background-color: #7FFFD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7FFFD4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7FFFD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7FFFD4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7FFFD4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7FFFD4'>&nbsp;@&nbsp;</font><font style='background-color: #7FFFD4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7FFFD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7FFFD4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int MINT = 0xfe845fe6;

    static {
        NAMED.put("mint", 0xfe845fe6);
        LIST.add(0xfe845fe6);
    }

    /**
     * This color constant "teal" has RGBA8888 code {@code 007F7FFF}, intensity 0.5019608, protan 0.40784314, tritan 0.4627451, alpha 1.0, hue 0.5000993, and saturation 0.45671126.
     * It has the encoded IPT value 0xfe766880 .
     * <pre>
     * <font style='background-color: #007F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007F7F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #007F7F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #007F7F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #007F7F'>&nbsp;@&nbsp;</font><font style='background-color: #007F7F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007F7F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TEAL = 0xfe766880;

    static {
        NAMED.put("teal", 0xfe766880);
        LIST.add(0xfe766880);
    }

    /**
     * This color constant "turquoise" has RGBA8888 code {@code 2ED6C9FF}, intensity 0.7764706, protan 0.36078432, tritan 0.46666667, alpha 1.0, hue 0.4864811, and saturation 0.6576992.
     * It has the encoded IPT value 0xfe775cc6 .
     * <pre>
     * <font style='background-color: #2ED6C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2ED6C9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2ED6C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2ED6C9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2ED6C9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2ED6C9'>&nbsp;@&nbsp;</font><font style='background-color: #2ED6C9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2ED6C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2ED6C9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TURQUOISE = 0xfe775cc6;

    static {
        NAMED.put("turquoise", 0xfe775cc6);
        LIST.add(0xfe775cc6);
    }

    /**
     * This color constant "sky" has RGBA8888 code {@code 86CFEBFF}, intensity 0.81960785, protan 0.42745098, tritan 0.42352942, alpha 1.0, hue 0.54665047, and saturation 0.39086533.
     * It has the encoded IPT value 0xfe6c6dd1 .
     * <pre>
     * <font style='background-color: #86CFEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #86CFEB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #86CFEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #86CFEB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #86CFEB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #86CFEB'>&nbsp;@&nbsp;</font><font style='background-color: #86CFEB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #86CFEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #86CFEB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int SKY = 0xfe6c6dd1;

    static {
        NAMED.put("sky", 0xfe6c6dd1);
        LIST.add(0xfe6c6dd1);
    }

    /**
     * This color constant "cobalt" has RGBA8888 code {@code 0046ABFF}, intensity 0.4117647, protan 0.43137255, tritan 0.3019608, alpha 1.0, hue 0.5979607, and saturation 0.6710433.
     * It has the encoded IPT value 0xfe4d6e69 .
     * <pre>
     * <font style='background-color: #0046AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0046AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0046AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0046AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0046AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0046AB'>&nbsp;@&nbsp;</font><font style='background-color: #0046AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0046AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0046AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int COBALT = 0xfe4d6e69;

    static {
        NAMED.put("cobalt", 0xfe4d6e69);
        LIST.add(0xfe4d6e69);
    }

    /**
     * This color constant "denim" has RGBA8888 code {@code 2870DDFF}, intensity 0.5647059, protan 0.42352942, tritan 0.28627452, alpha 1.0, hue 0.60051996, and saturation 0.71609116.
     * It has the encoded IPT value 0xfe496c90 .
     * <pre>
     * <font style='background-color: #2870DD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2870DD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2870DD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2870DD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2870DD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2870DD'>&nbsp;@&nbsp;</font><font style='background-color: #2870DD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2870DD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2870DD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DENIM = 0xfe496c90;

    static {
        NAMED.put("denim", 0xfe496c90);
        LIST.add(0xfe496c90);
    }

    /**
     * This color constant "navy" has RGBA8888 code {@code 000080FF}, intensity 0.24313726, protan 0.43529412, tritan 0.29411766, alpha 1.0, hue 0.67328644, and saturation 0.49774668.
     * It has the encoded IPT value 0xfe4b6f3e .
     * <pre>
     * <font style='background-color: #000080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000080; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000080'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000080'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000080'>&nbsp;@&nbsp;</font><font style='background-color: #000080; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000080; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int NAVY = 0xfe4b6f3e;

    static {
        NAMED.put("navy", 0xfe4b6f3e);
        LIST.add(0xfe4b6f3e);
    }

    /**
     * This color constant "lavender" has RGBA8888 code {@code B991FFFF}, intensity 0.74509805, protan 0.54901963, tritan 0.34117648, alpha 1.0, hue 0.72741735, and saturation 0.42804563.
     * It has the encoded IPT value 0xfe578cbe .
     * <pre>
     * <font style='background-color: #B991FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B991FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B991FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B991FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B991FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B991FF'>&nbsp;@&nbsp;</font><font style='background-color: #B991FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B991FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B991FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int LAVENDER = 0xfe578cbe;

    static {
        NAMED.put("lavender", 0xfe578cbe);
        LIST.add(0xfe578cbe);
    }

    /**
     * This color constant "plum" has RGBA8888 code {@code BE0DC6FF}, intensity 0.5294118, protan 0.7137255, tritan 0.3372549, alpha 1.0, hue 0.82619894, and saturation 0.72139674.
     * It has the encoded IPT value 0xfe56b687 .
     * <pre>
     * <font style='background-color: #BE0DC6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE0DC6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE0DC6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BE0DC6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BE0DC6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BE0DC6'>&nbsp;@&nbsp;</font><font style='background-color: #BE0DC6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE0DC6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE0DC6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int PLUM = 0xfe56b687;

    static {
        NAMED.put("plum", 0xfe56b687);
        LIST.add(0xfe56b687);
    }

    /**
     * This color constant "mauve" has RGBA8888 code {@code AB73ABFF}, intensity 0.6, protan 0.5764706, tritan 0.43529412, alpha 1.0, hue 0.8289416, and saturation 0.2213426.
     * It has the encoded IPT value 0xfe6f9399 .
     * <pre>
     * <font style='background-color: #AB73AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB73AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB73AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB73AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB73AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB73AB'>&nbsp;@&nbsp;</font><font style='background-color: #AB73AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB73AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB73AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int MAUVE = 0xfe6f9399;

    static {
        NAMED.put("mauve", 0xfe6f9399);
        LIST.add(0xfe6f9399);
    }

    /**
     * This color constant "rose" has RGBA8888 code {@code E61E78FF}, intensity 0.50980395, protan 0.7882353, tritan 0.5137255, alpha 1.0, hue 0.9242363, and saturation 0.7867001.
     * It has the encoded IPT value 0xfe83c982 .
     * <pre>
     * <font style='background-color: #E61E78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E61E78; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E61E78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E61E78'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E61E78'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E61E78'>&nbsp;@&nbsp;</font><font style='background-color: #E61E78; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E61E78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E61E78; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int ROSE = 0xfe83c982;

    static {
        NAMED.put("rose", 0xfe83c982);
        LIST.add(0xfe83c982);
    }

    /**
     * This color constant "raspberry" has RGBA8888 code {@code 911437FF}, intensity 0.3254902, protan 0.69411767, tritan 0.5529412, alpha 1.0, hue 0.9541538, and saturation 0.49511868.
     * It has the encoded IPT value 0xfe8db153 .
     * <pre>
     * <font style='background-color: #911437;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #911437; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #911437;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #911437'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #911437'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #911437'>&nbsp;@&nbsp;</font><font style='background-color: #911437; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #911437;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #911437; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int RASPBERRY = 0xfe8db153;

    static {
        NAMED.put("raspberry", 0xfe8db153);
        LIST.add(0xfe8db153);
    }

    /**
     * All names for colors in this palette, in alphabetical order. You can fetch the corresponding packed float color
     * by looking up a name in {@link #NAMED}.
     */
    public static final ObjectList<String> NAMES = NAMED.order();

    static {
        NAMED.setDefaultValue(TRANSPARENT);
        NAMES.sort(null);
    }

    /**
     * All names for colors in this palette, with grayscale first, then sorted by hue from red to yellow to green to
     * blue. You can fetch the corresponding packed float color by looking up a name in {@link #NAMED}.
     */
    public static final ObjectList<String> NAMES_BY_HUE = new ObjectList<>(NAMES);
    /**
     * All names for colors in this palette, sorted by lightness from black to white. You can fetch the
     * corresponding packed float color by looking up a name in {@link #NAMED}.
     */
    public static final ObjectList<String> NAMES_BY_LIGHTNESS = new ObjectList<>(NAMES);

    /**
     *  Linearly interpolates between fromValue to toValue on progress position.
     * @param fromValue starting float value; can be any finite float
     * @param toValue ending float value; can be any finite float
     * @param progress how far the interpolation should go, between 0 (equal to fromValue) and 1 (equal to toValue)
     */
    public static float lerp (final float fromValue, final float toValue, final float progress) {
        return fromValue + (toValue - fromValue) * progress;
    }

    /**
     * Used when converting from RGB to IPT, as an intermediate step.
     *
     * @param component one of the LMS channels to be converted to LMS Prime
     * @return an LMS Prime channel value, which can be converted to IPT
     */
    private static float forwardTransform(final float component) {
        return (float) Math.pow(component, 0.43f);
    }

    /**
     * Used when converting from IPT to RGB, as an intermediate step.
     *
     * @param component one of the LMS Prime channels to be converted to LMS
     * @return an LMS channel value, which can be converted to RGB
     */
    private static float reverseTransform(final float component) {
        return Math.copySign((float) Math.pow(Math.abs(component), 2.3256f), component);
    }

    /**
     * Used when given non-linear sRGB inputs to make them linear, approximating with gamma 2.0.
     *
     * @param component any non-linear channel of a color, to be made linear
     * @return a linear version of component
     */
    private static float forwardGamma(final float component) {
        return component * component;
    }

    /**
     * Used to return from a linear, gamma-corrected input to an sRGB, non-linear output, using gamma 2.0.
     *
     * @param component a linear channel of a color, to be made non-linear
     * @return a non-linear version of component
     */
    private static float reverseGamma(final float component) {
        return (float) Math.sqrt(component);
    }

    /**
     * Converts a packed IPT int color in the format used by constants in this class to an RGBA8888 int.
     * This format of int can be used with Pixmap and in some other places in libGDX.
     * @param decoded a packed float color, as from a constant in this class
     * @return an RGBA8888 int color
     */
    public static int toRGBA8888(final int decoded)
    {
        final float i = (decoded & 0xff) / 255f;
        final float p = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
        final float t = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
        final float l = reverseTransform(i + 0.097569f * p + 0.205226f * t);
        final float m = reverseTransform(i + -0.11388f * p + 0.133217f * t);
        final float s = reverseTransform(i + 0.032615f * p + -0.67689f * t);
        final int r = (int)(reverseGamma(Math.min(Math.max(5.432622f * l + -4.67910f * m + 0.246257f * s, 0f), 1f)) * 255.999f);
        final int g = (int)(reverseGamma(Math.min(Math.max(-1.10517f * l + 2.311198f * m + -0.20588f * s, 0f), 1f)) * 255.999f);
        final int b = (int)(reverseGamma(Math.min(Math.max(0.028104f * l + -0.19466f * m + 1.166325f * s, 0f), 1f)) * 255.999f);
        return r << 24 | g << 16 | b << 8 | (decoded & 0xfe000000) >>> 24 | decoded >>> 31;
    }

    /**
     * Gets the saturation of the given encoded color, as a float ranging from 0.0f to 1.0f, inclusive.
     *
     * @param iptColor a color as an IPT int that can be obtained from any of the constants in this class.
     * @return the saturation of the color from 0.0 (a grayscale color; inclusive) to 1.0 (a bright color, inclusive)
     */
    public static float saturation(final int iptColor) {
        final float i = (iptColor & 0xff) / 255f;
        if (Math.abs(i - 0.5) > 0.495f) return 0f;
        final float p = ((iptColor >>> 8 & 0xff) - 127.5f) / 127.5f;
        final float t = ((iptColor >>> 16 & 0xff) - 127.5f) / 127.5f;
        final float l = reverseTransform(i + 0.097569f * p + 0.205226f * t);
        final float m = reverseTransform(i + -0.11388f * p + 0.133217f * t);
        final float s = reverseTransform(i + 0.032615f * p + -0.67689f * t);
        final float r = reverseGamma(Math.min(Math.max(5.432622f * l + -4.67910f * m + 0.246257f * s, 0f), 1f));
        final float g = reverseGamma(Math.min(Math.max(-1.10517f * l + 2.311198f * m + -0.20588f * s, 0f), 1f));
        final float b = reverseGamma(Math.min(Math.max(0.028104f * l + -0.19466f * m + 1.166325f * s, 0f), 1f));
        float x, y, w;
        if (g < b) {
            x = b;
            y = g;
        } else {
            x = g;
            y = b;
        }
        if (r < x) {
            w = r;
        } else {
            w = x;
            x = r;
        }
        return x - Math.min(w, y);
    }

    /**
     * Gets the hue of the given encoded color, as a float from 0f (inclusive, red and approaching orange if increased)
     * to 1f (exclusive, red and approaching purple if decreased).
     *
     * @param iptColor a color as an IPT int that can be obtained from any of the constants in this class.
     * @return The hue of the color from 0.0 (red, inclusive) towards orange, then yellow, and
     * eventually to purple before looping back to almost the same red (1.0, exclusive)
     */
    public static float hue(final int iptColor) {
        final float i = (iptColor & 0xff) / 255f;
        final float p = ((iptColor >>> 8 & 0xff) - 127.5f) / 127.5f;
        final float t = ((iptColor >>> 16 & 0xff) - 127.5f) / 127.5f;
        final float l = reverseTransform(i + 0.097569f * p + 0.205226f * t);
        final float m = reverseTransform(i + -0.11388f * p + 0.133217f * t);
        final float s = reverseTransform(i + 0.032615f * p + -0.67689f * t);
        final float r = reverseGamma(Math.min(Math.max(5.432622f * l + -4.67910f * m + 0.246257f * s, 0f), 1f));
        final float g = reverseGamma(Math.min(Math.max(-1.10517f * l + 2.311198f * m + -0.20588f * s, 0f), 1f));
        final float b = reverseGamma(Math.min(Math.max(0.028104f * l + -0.19466f * m + 1.166325f * s, 0f), 1f));
        float x, y, z, w;
        if (g < b) {
            x = b;
            y = g;
            z = -1f;
            w = 2f / 3f;
        } else {
            x = g;
            y = b;
            z = 0f;
            w = -1f / 3f;
        }
        if (r < x) {
            z = w;
            w = r;
        } else {
            w = x;
            x = r;
        }
        float d = x - Math.min(w, y);
        return Math.abs(z + (w - y) / (6f * d + 1e-10f));
    }

    /**
     * Interpolates from the packed IPT int color s towards white by change. While change should be between 0f (return
     * s as-is) and 1f (return white), s should be a packed color, as from a constant here. This method does not
     * necessarily keep the resulting color in-gamut; after performing some changes with this or other component-editing
     * methods, you may want to call {@link #limitToGamut(int)} to make sure the color can be rendered correctly.
     *
     * @param s      the starting color as an IPT int
     * @param change how much to go from start toward white, as a float between 0 and 1; higher means closer to white
     * @return a packed IPT int that represents a color between start and white
     * @see #darken(int, float) the counterpart method that darkens a float color
     */
    public static int lighten(final int s, final float change) {
        final int i = s & 255, other = s & 0xFEFFFF00;
        return (((int) (i + (255 - i) * change) & 255) | other);
    }

    /**
     * Interpolates from the packed IPT int color s towards black by change. While change should be between 0f (return
     * s as-is) and 1f (return black), s should be a packed color, as from a constant here. This method does not
     * necessarily keep the resulting color in-gamut; after performing some changes with this or other component-editing
     * methods, you may want to call {@link #limitToGamut(int)} to make sure the color can be rendered correctly.
     *
     * @param s      the starting color as a packed float
     * @param change how much to go from start toward black, as a float between 0 and 1; higher means closer to black
     * @return a packed float that represents a color between start and black
     * @see #lighten(int, float) the counterpart method that lightens a float color
     */
    public static int darken(final int s, final float change) {
        final int i = s & 255, other = s & 0xFEFFFF00;
        return (((int) (i * (1f - change)) & 255) | other);
    }

    /**
     * Brings the chromatic components of {@code s} closer to grayscale by {@code change} (desaturating them). While
     * change should be between 0f (return s as-is) and 1f (return fully gray), s should be a packed IPT int color, as
     * from a constant in this class.
     *
     * @param s      the starting color as a packed IPT int
     * @param change how much to change start to a desaturated color, as a float between 0 and 1; higher means a less saturated result
     * @return a packed float that represents a color between start and a desaturated color
     * @see #enrich(int, float) the counterpart method that makes an IPT int color more saturated
     */
    public static int dullen(final int s, final float change) {
        return (s & 255) |
                (int) (((s >>> 8 & 255) - 127.5f) * (1f - change) + 127.5f) << 8 |
                (int) (((s >>> 16 & 255) - 127.5f) * (1f - change) + 127.5f) << 16 |
                (s & 0xFE000000);
    }

    /**
     * Pushes the chromatic components of {@code start} away from grayscale by change (saturating them). While change
     * should be between 0f (return start as-is) and 1f (return maximally saturated), start should be a packed color, as
     * from a constant in this class. This usually changes only Cw and Cm, but higher values for {@code change} can
     * force the color out of the gamut, which this corrects using {@link #limitToGamut(int)} (and that can change Y
     * somewhat). If the color stays in-gamut, then Y won't change; alpha never changes.
     *
     * @param s      the starting color as a packed float
     * @param change how much to change start to a saturated color, as a float between 0 and 1; higher means a more saturated result
     * @return a packed float that represents a color between start and a saturated color
     * @see #dullen(int, float) the counterpart method that makes a float color less saturated
     */
    public static int enrich(final int s, final float change) {
        return limitToGamut((s & 255) |
                (int) (((s >>> 8 & 255) - 127.5f) * (1f - change) + 127.5f) << 8 |
                (int) (((s >>> 16 & 255) - 127.5f) * (1f - change) + 127.5f) << 16 |
                (s & 0xFE000000));
    }

    /**
     * Iteratively checks whether the given IPT color is in-gamut, and either brings the color closer to 50% gray if it
     * isn't in-gamut, or returns it as soon as it is in-gamut.
     *
     * @param packed a packed IPT int color; often this color is not in-gamut
     * @return the first color this finds that is between the given IPT color and 50% gray, and is in-gamut
     */
    public static int limitToGamut(final int packed) {
        final float i = (packed & 0xff) / 255f;
        final float p = ((packed >>> 8 & 0xff) - 127.5f) / 127.5f;
        final float t = ((packed >>> 16 & 0xff) - 127.5f) / 127.5f;
        float i2 = i, p2 = p, t2 = t;
        for (int attempt = 31; attempt >= 0; attempt--) {
            final float l = reverseTransform(i2 + 0.097569f * p2 + 0.205226f * t2);
            final float m = reverseTransform(i2 + -0.11388f * p2 + 0.133217f * t2);
            final float s = reverseTransform(i2 + 0.032615f * p2 + -0.67689f * t2);

            final float r = 5.432622f * l + -4.67910f * m + 0.246257f * s;
            final float g = -1.10517f * l + 2.311198f * m + -0.20588f * s;
            final float b = 0.028104f * l + -0.19466f * m + 1.166325f * s;
            if (r >= 0f && r <= 1f && g >= 0f && g <= 1f && b >= 0f && b <= 1f)
                break;
            final float progress = attempt * 0x1p-5f;
            i2 = lerp(0.55f, i, progress);
            p2 = lerp(0, p, progress);
            t2 = lerp(0, t, progress);
        }
        return ((packed & 0xFE000000) | ((int) (t2 * 127.999f + 128f) << 16)
                | ((int) (p2 * 127.999f + 128f) << 8) | ((int) (i2 * 255.999f)));
    }

    /**
     * Interpolates from the packed float color start towards end by change. Both start and end should be packed IPT
     * ints, and change can be between 0f (keep start) and 1f (only use end). This is a good way to reduce allocations
     * of temporary Colors.
     *
     * @param s      the starting color as a packed float
     * @param e      the end/target color as a packed float
     * @param change how much to go from start toward end, as a float between 0 and 1; higher means closer to end
     * @return a packed IPT int that represents a color between start and end
     */
    public static int lerpIntColors(final int s, final int e, final float change) {
        final int
                ys = (s & 0xFF), cws = (s >>> 8) & 0xFF, cms = (s >>> 16) & 0xFF, as = s >>> 24 & 0xFE,
                ye = (e & 0xFF), cwe = (e >>> 8) & 0xFF, cme = (e >>> 16) & 0xFF, ae = e >>> 24 & 0xFE;
        return (((int) (ys + change * (ye - ys)) & 0xFF)
                | (((int) (cws + change * (cwe - cws)) & 0xFF) << 8)
                | (((int) (cms + change * (cme - cms)) & 0xFF) << 16)
                | (((int) (as + change * (ae - as)) & 0xFE) << 24));
    }

    /**
     * Given several colors, this gets an even mix of all colors in equal measure.
     * If {@code colors} is null or has no items, this returns 0f (usually transparent in most color spaces).
     *
     * @param colors an array or varargs of packed float colors; all should use the same color space
     * @param offset the index of the first item in {@code colors} to use
     * @param size   how many items from {@code colors} to use
     * @return an even mix of all colors given, as a packed float color
     */
    public static int mix(int[] colors, int offset, int size) {
        if (colors == null || colors.length < offset + size || offset < 0 || size <= 0)
            return TRANSPARENT;
        int result = colors[offset];
        for (int i = offset + 1, o = offset + size, denom = 2; i < o; i++, denom++) {
            result = lerpIntColors(result, colors[i], 1f / denom);
        }
        return result;
    }


    static {
        NAMES_BY_HUE.sort((o1, o2) -> {
            final int c1 = NAMED.get(o1), c2 = NAMED.get(o2);
            final float s1 = saturation(c1), s2 = saturation(c2);
            if (s1 <= 0x1p-6f && s2 > 0x1p-6f)
                return -1000;
            else if (s1 > 0x1p-6f && s2 <= 0x1p-6f)
                return 1000;
            else if (s1 <= 0x1p-6f && s2 <= 0x1p-6f)
                return (c1 & 255) - (c2 & 255);
            else
                return 256 * (int) Math.signum(hue(c1) - hue(c2))
                        + (c1 & 255) - (c2 & 255);
        });
        NAMES_BY_LIGHTNESS.sort((o1, o2) -> (NAMED.get(o1) & 255) - (NAMED.get(o2) & 255));
    }


    private static final IntList mixing = new IntList(4);

    /**
     * Parses a color description and returns the approximate color it describes, as a packed IPT int color.
     * Color descriptions consist of one or more lower-case words, separated by non-alphabetical characters (typically
     * spaces and/or hyphens). Any word that is the name of a color in this palette will be looked up in
     * {@link #NAMED} and tracked; if there is more than one of these color name words, the colors will be mixed using
     * {@link #mix(int[], int, int)}, or if there is just one color name word, then the corresponding color
     * will be used. The special adjectives "light" and "dark" change the intensity of the described color; likewise,
     * "rich" and "dull" change the saturation (the difference of the chromatic channels from grayscale). All of these
     * adjectives can have "-er" or "-est" appended to make their effect twice or three times as strong. If a color name
     * or adjective is invalid, it is considered the same as adding the color {@link #TRANSPARENT}.
     * <br>
     * Examples of valid descriptions include "blue", "dark green", "duller red", "peach pink", "indigo purple mauve",
     * and "lightest richer apricot-olive".
     *
     * @param description a color description, as a lower-case String matching the above format
     * @return a packed IPT int color as described
     */
    public static int parseDescription(final String description) {
        float intensity = 0f, saturation = 0f;
        final String[] terms = description.split("[^a-zA-Z]+");
        mixing.clear();
        for (String term : terms) {
            if (term == null || term.isEmpty()) continue;
            final int len = term.length();
            switch (term.charAt(0)) {
                case 'l':
                    if (len > 2 && term.charAt(2) == 'g') {
                        switch (len) {
                            case 8:
                                intensity += 0.125f;
                            case 7:
                                intensity += 0.125f;
                            case 5:
                                intensity += 0.125f;
                                break;
                            default:
                                mixing.add(TRANSPARENT);
                                break;
                        }
                    } else {
                        mixing.add(NAMED.get(term));
                    }
                    break;
                case 'r':
                    if (len > 1 && term.charAt(1) == 'i') {
                        switch (len) {
                            case 7:
                                saturation += 0.2f;
                            case 6:
                                saturation += 0.2f;
                            case 4:
                                saturation += 0.2f;
                                break;
                            default:
                                mixing.add(TRANSPARENT);
                                break;
                        }
                    } else {
                        mixing.add(NAMED.get(term));
                    }
                    break;
                case 'd':
                    if (len > 1 && term.charAt(1) == 'a') {
                        switch (len) {
                            case 7:
                                intensity -= 0.125f;
                            case 6:
                                intensity -= 0.125f;
                            case 4:
                                intensity -= 0.125f;
                                break;
                            default:
                                mixing.add(TRANSPARENT);
                                break;
                        }
                    } else if (len > 1 && term.charAt(1) == 'u') {
                        switch (len) {
                            case 7:
                                saturation -= 0.2f;
                            case 6:
                                saturation -= 0.2f;
                            case 4:
                                saturation -= 0.2f;
                                break;
                            default:
                                mixing.add(TRANSPARENT);
                                break;
                        }
                    } else {
                        mixing.add(NAMED.get(term));
                    }
                    break;
                default:
                    mixing.add(NAMED.get(term));
                    break;
            }
        }
        int result = mix(mixing.items, 0, mixing.size());

        if (intensity > 0) result = lighten(result, intensity);
        else if (intensity < 0) result = darken(result, -intensity);

        if (saturation > 0) result = enrich(result, saturation);
        else if (saturation < 0) result = limitToGamut(dullen(result, -saturation));
        else result = limitToGamut(result);

        return result;
    }
}