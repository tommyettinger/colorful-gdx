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

package com.github.tommyettinger.colorful.oklab;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.ObjectFloatMap;
import com.github.tommyettinger.colorful.FloatColors;

import java.util.Comparator;

import static com.github.tommyettinger.colorful.FloatColors.unevenMix;
import static com.github.tommyettinger.colorful.oklab.ColorTools.*;

/**
 * A palette of predefined colors as packed Oklab floats, the kind {@link ColorTools} works with, plus a way to describe
 * colors by combinations and adjustments. The description code is probably what you would use this class for; it
 * revolves around {@link #parseDescription(String)}, which takes a color description String and returns a packed float
 * color. The color descriptions look like "darker rich mint yellow", where the order of the words doesn't matter. They
 * can optionally include lightness changes (light/dark), and saturation changes (rich/dull), and must include one or
 * more color names that will be mixed together (repeats are allowed to use a color more heavily). The changes can be
 * suffixed with "er", "est", or "most", such as "duller", "lightest", or "richmost", to progressively increase their
 * effect on lightness or saturation.
 * <br>
 * The rest of this is about the same as in {@link Palette}.
 * <br>
 * You can access colors by their constant name, such as {@code CACTUS}, by the {@link #NAMED} map using
 * {@code NAMED.get("cactus", 0f)}, or by index in the FloatArray called {@link #LIST}. Note that to access a float
 * color from NAMED, you need to give a default value if the name is not found; {@code 0f} is a good default because it
 * will not occur in a valid Oklab color. You can access the names in a specific order with {@link #NAMES} (which is
 * alphabetical), {@link #NAMES_BY_HUE} (which is sorted by the hue of the matching color, from red to yellow to blue
 * (with gray around here) to purple to red again), or {@link #NAMES_BY_LIGHTNESS} (which is sorted by the intensity of
 * the matching color, from darkest to lightest). Having a name lets you look up the matching color in {@link #NAMED}.
 * <br>
 * Created by Tommy Ettinger on 10/13/2020.
 */
public class SimplePalette {
    /**
     * You can look up colors by name here; the names are lower-case, and the colors are packed floats in Oklab format.
     */
    public static final ObjectFloatMap<String> NAMED = new ObjectFloatMap<String>(50);
    /**
     * Stores alternative names for colors in {@link #NAMED}, like "grey" as an alias for {@link #GRAY} or "gold" as an
     * alias for {@link #SAFFRON}. Currently, the list of aliases is as follows:
     * <ul>
     * <li>"grey" maps to {@link #GRAY},</li>
     * <li>"gold" maps to {@link #SAFFRON},</li>
     * <li>"puce" maps to {@link #MAUVE},</li>
     * <li>"sand" maps to {@link #TAN},</li>
     * <li>"skin" maps to {@link #PEACH},</li>
     * <li>"coral" maps to {@link #SALMON},</li>
     * <li>"azure" maps to {@link #SKY}, and</li>
     * <li>"ocean" maps to {@link #TEAL}, and</li>
     * <li>"sapphire" maps to {@link #COBALT}.</li>
     * </ul>
     * Note that these aliases are not duplicated in {@link #NAMES}, {@link #NAMES_BY_HUE}, or
     * {@link #NAMES_BY_LIGHTNESS}; they are primarily there so blind attempts to name a color might still work.
     */
    public static final ObjectFloatMap<String> ALIASES = new ObjectFloatMap<String>(20);
    /**
     * Lists the packed float color values in this, in no particular order. Does not include duplicates from aliases.
     */
    public static final FloatArray LIST = new FloatArray(50);



    /**
     * This color constant "transparent" has RGBA8888 code {@code 00000000}, L 0.0, A 0.49803922, B 0.49803922, alpha 0.0, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code 0x0.fefep-126F}.
     * <pre>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #000000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRANSPARENT = 0x0.fefep-126F;
    static { NAMED.put("transparent", 0x0.fefep-126F); LIST.add(0x0.fefep-126F); }

    /**
     * This color constant "black" has RGBA8888 code {@code 000000FF}, L 0.0, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.fefep125F}.
     * <pre>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #000000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK = -0x1.fefep125F;
    static { NAMED.put("black", -0x1.fefep125F); LIST.add(-0x1.fefep125F); }

    /**
     * This color constant "gray" has RGBA8888 code {@code 808080FF}, L 0.5019608, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.feffp125F}.
     * <pre>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #808080; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #808080; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #808080; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY = -0x1.feffp125F;
    static { NAMED.put("gray", -0x1.feffp125F); LIST.add(-0x1.feffp125F); }

    /**
     * This color constant "silver" has RGBA8888 code {@code B6B6B6FF}, L 0.7137255, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.feff6cp125F}.
     * <pre>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B6B6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #B6B6B6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B6B6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER = -0x1.feff6cp125F;
    static { NAMED.put("silver", -0x1.feff6cp125F); LIST.add(-0x1.feff6cp125F); }

    /**
     * This color constant "white" has RGBA8888 code {@code FFFFFFFF}, L 1.0, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.fefffep125F}.
     * <pre>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #FFFFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE = -0x1.fefffep125F;
    static { NAMED.put("white", -0x1.fefffep125F); LIST.add(-0x1.fefffep125F); }

    /**
     * This color constant "red" has RGBA8888 code {@code FF0000FF}, L 0.49803922, A 0.6117647, B 0.56078434, alpha 1.0, hue 0.0802403, saturation 0.98868626, and chroma 0.25345513.
     * It can be represented as a packed float with the constant {@code -0x1.1f38fep126F}.
     * <pre>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #FF0000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float RED = -0x1.1f38fep126F;
    static { NAMED.put("red", -0x1.1f38fep126F); LIST.add(-0x1.1f38fep126F); }

    /**
     * This color constant "orange" has RGBA8888 code {@code FF7F00FF}, L 0.6431373, A 0.54901963, B 0.57254905, alpha 1.0, hue 0.1544989, saturation 0.9208691, and chroma 0.17443058.
     * It can be represented as a packed float with the constant {@code -0x1.251948p126F}.
     * <pre>
     * <font style='background-color: #FF7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF7F00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF7F00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF7F00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF7F00'>&nbsp;@&nbsp;</font><font style='background-color: #FF7F00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF7F00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ORANGE = -0x1.251948p126F;
    static { NAMED.put("orange", -0x1.251948p126F); LIST.add(-0x1.251948p126F); }

    /**
     * This color constant "yellow" has RGBA8888 code {@code FFFF00FF}, L 0.9529412, A 0.4627451, B 0.59607846, alpha 1.0, hue 0.30499697, saturation 0.94895214, and chroma 0.20529193.
     * It can be represented as a packed float with the constant {@code -0x1.30ede6p126F}.
     * <pre>
     * <font style='background-color: #FFFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFF00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFF00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFF00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFF00'>&nbsp;@&nbsp;</font><font style='background-color: #FFFF00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFF00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float YELLOW = -0x1.30ede6p126F;
    static { NAMED.put("yellow", -0x1.30ede6p126F); LIST.add(-0x1.30ede6p126F); }

    /**
     * This color constant "green" has RGBA8888 code {@code 00FF00FF}, L 0.80784315, A 0.38039216, B 0.5882353, alpha 1.0, hue 0.39589924, saturation 0.9947925, and chroma 0.29610303.
     * It can be represented as a packed float with the constant {@code -0x1.2cc39cp126F}.
     * <pre>
     * <font style='background-color: #00FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FF00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FF00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FF00'>&nbsp;@&nbsp;</font><font style='background-color: #00FF00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GREEN = -0x1.2cc39cp126F;
    static { NAMED.put("green", -0x1.2cc39cp126F); LIST.add(-0x1.2cc39cp126F); }

    /**
     * This color constant "blue" has RGBA8888 code {@code 0000FFFF}, L 0.3019608, A 0.48235294, B 0.34117648, alpha 1.0, hue 0.7341372, saturation 0.9925803, and chroma 0.31835338.
     * It can be represented as a packed float with the constant {@code -0x1.aef69ap125F}.
     * <pre>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0000FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #0000FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0000FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUE = -0x1.aef69ap125F;
    static { NAMED.put("blue", -0x1.aef69ap125F); LIST.add(-0x1.aef69ap125F); }

    /**
     * This color constant "indigo" has RGBA8888 code {@code 520FE0FF}, L 0.31764707, A 0.53333336, B 0.36862746, alpha 1.0, hue 0.79237556, saturation 0.95014614, and chroma 0.27001202.
     * It can be represented as a packed float with the constant {@code -0x1.bd10a2p125F}.
     * <pre>
     * <font style='background-color: #520FE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #520FE0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #520FE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #520FE0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #520FE0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #520FE0'>&nbsp;@&nbsp;</font><font style='background-color: #520FE0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #520FE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #520FE0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float INDIGO = -0x1.bd10a2p125F;
    static { NAMED.put("indigo", -0x1.bd10a2p125F); LIST.add(-0x1.bd10a2p125F); }

    /**
     * This color constant "violet" has RGBA8888 code {@code 9040EFFF}, L 0.4509804, A 0.56078434, B 0.39607844, alpha 1.0, hue 0.8377986, saturation 0.74460185, and chroma 0.23984502.
     * It can be represented as a packed float with the constant {@code -0x1.cb1ee6p125F}.
     * <pre>
     * <font style='background-color: #9040EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9040EF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9040EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9040EF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9040EF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9040EF'>&nbsp;@&nbsp;</font><font style='background-color: #9040EF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9040EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9040EF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIOLET = -0x1.cb1ee6p125F;
    static { NAMED.put("violet", -0x1.cb1ee6p125F); LIST.add(-0x1.cb1ee6p125F); }

    /**
     * This color constant "purple" has RGBA8888 code {@code C000FFFF}, L 0.4862745, A 0.6039216, B 0.3882353, alpha 1.0, hue 0.8721067, saturation 0.96042717, and chroma 0.30403575.
     * It can be represented as a packed float with the constant {@code -0x1.c734f8p125F}.
     * <pre>
     * <font style='background-color: #C000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C000FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C000FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C000FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C000FF'>&nbsp;@&nbsp;</font><font style='background-color: #C000FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C000FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURPLE = -0x1.c734f8p125F;
    static { NAMED.put("purple", -0x1.c734f8p125F); LIST.add(-0x1.c734f8p125F); }

    /**
     * This color constant "brown" has RGBA8888 code {@code 8F573BFF}, L 0.4, A 0.5254902, B 0.5294118, alpha 1.0, hue 0.13559444, saturation 0.5315073, and chroma 0.07753685.
     * It can be represented as a packed float with the constant {@code -0x1.0f0cccp126F}.
     * <pre>
     * <font style='background-color: #8F573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F573B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F573B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F573B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F573B'>&nbsp;@&nbsp;</font><font style='background-color: #8F573B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F573B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BROWN = -0x1.0f0cccp126F;
    static { NAMED.put("brown", -0x1.0f0cccp126F); LIST.add(-0x1.0f0cccp126F); }

    /**
     * This color constant "pink" has RGBA8888 code {@code FFA0E0FF}, L 0.7647059, A 0.5568628, B 0.4745098, alpha 1.0, hue 0.9394406, saturation 0.3365728, and chroma 0.124142565.
     * It can be represented as a packed float with the constant {@code -0x1.f31d86p125F}.
     * <pre>
     * <font style='background-color: #FFA0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA0E0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFA0E0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFA0E0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFA0E0'>&nbsp;@&nbsp;</font><font style='background-color: #FFA0E0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA0E0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINK = -0x1.f31d86p125F;
    static { NAMED.put("pink", -0x1.f31d86p125F); LIST.add(-0x1.f31d86p125F); }

    /**
     * This color constant "magenta" has RGBA8888 code {@code F500F5FF}, L 0.5647059, A 0.6313726, B 0.41568628, alpha 1.0, hue 0.9119405, saturation 0.96295136, and chroma 0.31098264.
     * It can be represented as a packed float with the constant {@code -0x1.d5432p125F}.
     * <pre>
     * <font style='background-color: #F500F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F500F5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F500F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F500F5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F500F5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F500F5'>&nbsp;@&nbsp;</font><font style='background-color: #F500F5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F500F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F500F5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MAGENTA = -0x1.d5432p125F;
    static { NAMED.put("magenta", -0x1.d5432p125F); LIST.add(-0x1.d5432p125F); }

    /**
     * This color constant "brick" has RGBA8888 code {@code D5524AFF}, L 0.5019608, A 0.5686275, B 0.53333336, alpha 1.0, hue 0.07379155, saturation 0.60073465, and chroma 0.15199278.
     * It can be represented as a packed float with the constant {@code -0x1.1123p126F}.
     * <pre>
     * <font style='background-color: #D5524A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5524A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5524A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D5524A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D5524A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D5524A'>&nbsp;@&nbsp;</font><font style='background-color: #D5524A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5524A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5524A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRICK = -0x1.1123p126F;
    static { NAMED.put("brick", -0x1.1123p126F); LIST.add(-0x1.1123p126F); }

    /**
     * This color constant "ember" has RGBA8888 code {@code F55A32FF}, L 0.56078434, A 0.57254905, B 0.5568628, alpha 1.0, hue 0.10636183, saturation 0.80691457, and chroma 0.1836353.
     * It can be represented as a packed float with the constant {@code -0x1.1d251ep126F}.
     * <pre>
     * <font style='background-color: #F55A32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F55A32; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F55A32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F55A32'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F55A32'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F55A32'>&nbsp;@&nbsp;</font><font style='background-color: #F55A32; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F55A32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F55A32; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float EMBER = -0x1.1d251ep126F;
    static { NAMED.put("ember", -0x1.1d251ep126F); LIST.add(-0x1.1d251ep126F); }

    /**
     * This color constant "salmon" has RGBA8888 code {@code FF6262FF}, L 0.6, A 0.5803922, B 0.53333336, alpha 1.0, hue 0.06444036, saturation 0.6092618, and chroma 0.17337766.
     * It can be represented as a packed float with the constant {@code -0x1.112932p126F}.
     * <pre>
     * <font style='background-color: #FF6262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6262; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF6262'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF6262'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF6262'>&nbsp;@&nbsp;</font><font style='background-color: #FF6262; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6262; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SALMON = -0x1.112932p126F;
    static { NAMED.put("salmon", -0x1.112932p126F); LIST.add(-0x1.112932p126F); }

    /**
     * This color constant "chocolate" has RGBA8888 code {@code 683818FF}, L 0.27058825, A 0.5254902, B 0.53333336, alpha 1.0, hue 0.14479145, saturation 0.76011693, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.110c8ap126F}.
     * <pre>
     * <font style='background-color: #683818;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #683818; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #683818;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #683818'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #683818'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #683818'>&nbsp;@&nbsp;</font><font style='background-color: #683818; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #683818;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #683818; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CHOCOLATE = -0x1.110c8ap126F;
    static { NAMED.put("chocolate", -0x1.110c8ap126F); LIST.add(-0x1.110c8ap126F); }

    /**
     * This color constant "tan" has RGBA8888 code {@code D2B48CFF}, L 0.7254902, A 0.5058824, B 0.5254902, alpha 1.0, hue 0.20570697, saturation 0.3165265, and chroma 0.052115876.
     * It can be represented as a packed float with the constant {@code -0x1.0d0372p126F}.
     * <pre>
     * <font style='background-color: #D2B48C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2B48C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2B48C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D2B48C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D2B48C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D2B48C'>&nbsp;@&nbsp;</font><font style='background-color: #D2B48C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2B48C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2B48C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TAN = -0x1.0d0372p126F;
    static { NAMED.put("tan", -0x1.0d0372p126F); LIST.add(-0x1.0d0372p126F); }

    /**
     * This color constant "bronze" has RGBA8888 code {@code CE8E31FF}, L 0.60784316, A 0.5137255, B 0.56078434, alpha 1.0, hue 0.21101025, saturation 0.8246211, and chroma 0.124142565.
     * It can be represented as a packed float with the constant {@code -0x1.1f0736p126F}.
     * <pre>
     * <font style='background-color: #CE8E31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CE8E31; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CE8E31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CE8E31'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CE8E31'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CE8E31'>&nbsp;@&nbsp;</font><font style='background-color: #CE8E31; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CE8E31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CE8E31; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRONZE = -0x1.1f0736p126F;
    static { NAMED.put("bronze", -0x1.1f0736p126F); LIST.add(-0x1.1f0736p126F); }

    /**
     * This color constant "cinnamon" has RGBA8888 code {@code D2691DFF}, L 0.53333336, A 0.5411765, B 0.56078434, alpha 1.0, hue 0.15414284, saturation 0.88256764, and chroma 0.14626285.
     * It can be represented as a packed float with the constant {@code -0x1.1f151p126F}.
     * <pre>
     * <font style='background-color: #D2691D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2691D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2691D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D2691D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D2691D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D2691D'>&nbsp;@&nbsp;</font><font style='background-color: #D2691D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2691D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2691D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CINNAMON = -0x1.1f151p126F;
    static { NAMED.put("cinnamon", -0x1.1f151p126F); LIST.add(-0x1.1f151p126F); }

    /**
     * This color constant "apricot" has RGBA8888 code {@code FFA828FF}, L 0.73333335, A 0.52156866, B 0.5764706, alpha 1.0, hue 0.20361295, saturation 0.90785277, and chroma 0.1582875.
     * It can be represented as a packed float with the constant {@code -0x1.270b76p126F}.
     * <pre>
     * <font style='background-color: #FFA828;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA828; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA828;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFA828'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFA828'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFA828'>&nbsp;@&nbsp;</font><font style='background-color: #FFA828; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA828;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA828; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float APRICOT = -0x1.270b76p126F;
    static { NAMED.put("apricot", -0x1.270b76p126F); LIST.add(-0x1.270b76p126F); }

    /**
     * This color constant "peach" has RGBA8888 code {@code FFBF81FF}, L 0.8039216, A 0.5176471, B 0.54509807, alpha 1.0, hue 0.18716717, saturation 0.50980395, and chroma 0.096477255.
     * It can be represented as a packed float with the constant {@code -0x1.17099ap126F}.
     * <pre>
     * <font style='background-color: #FFBF81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFBF81; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFBF81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFBF81'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFBF81'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFBF81'>&nbsp;@&nbsp;</font><font style='background-color: #FFBF81; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFBF81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFBF81; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PEACH = -0x1.17099ap126F;
    static { NAMED.put("peach", -0x1.17099ap126F); LIST.add(-0x1.17099ap126F); }

    /**
     * This color constant "pear" has RGBA8888 code {@code D3E330FF}, L 0.8352941, A 0.45882353, B 0.58431375, alpha 1.0, hue 0.31789964, saturation 0.911928, and chroma 0.18692946.
     * It can be represented as a packed float with the constant {@code -0x1.2aebaap126F}.
     * <pre>
     * <font style='background-color: #D3E330;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3E330; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3E330;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D3E330'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D3E330'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D3E330'>&nbsp;@&nbsp;</font><font style='background-color: #D3E330; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3E330;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3E330; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PEAR = -0x1.2aebaap126F;
    static { NAMED.put("pear", -0x1.2aebaap126F); LIST.add(-0x1.2aebaap126F); }

    /**
     * This color constant "saffron" has RGBA8888 code {@code FFD510FF}, L 0.84313726, A 0.4862745, B 0.5882353, alpha 1.0, hue 0.2706426, saturation 0.9467276, and chroma 0.17789528.
     * It can be represented as a packed float with the constant {@code -0x1.2cf9aep126F}.
     * <pre>
     * <font style='background-color: #FFD510;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD510; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD510;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFD510'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFD510'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFD510'>&nbsp;@&nbsp;</font><font style='background-color: #FFD510; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD510;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD510; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SAFFRON = -0x1.2cf9aep126F;
    static { NAMED.put("saffron", -0x1.2cf9aep126F); LIST.add(-0x1.2cf9aep126F); }

    /**
     * This color constant "butter" has RGBA8888 code {@code FFF288FF}, L 0.93333334, A 0.4862745, B 0.5568628, alpha 1.0, hue 0.2814164, saturation 0.5772475, and chroma 0.11653464.
     * It can be represented as a packed float with the constant {@code -0x1.1cf9dcp126F}.
     * <pre>
     * <font style='background-color: #FFF288;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFF288; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFF288;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFF288'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFF288'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFF288'>&nbsp;@&nbsp;</font><font style='background-color: #FFF288; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFF288;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFF288; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BUTTER = -0x1.1cf9dcp126F;
    static { NAMED.put("butter", -0x1.1cf9dcp126F); LIST.add(-0x1.1cf9dcp126F); }

    /**
     * This color constant "chartreuse" has RGBA8888 code {@code C8FF41FF}, L 0.90588236, A 0.4392157, B 0.58431375, alpha 1.0, hue 0.34524146, saturation 0.8589372, and chroma 0.2070681.
     * It can be represented as a packed float with the constant {@code -0x1.2ae1cep126F}.
     * <pre>
     * <font style='background-color: #C8FF41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8FF41; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8FF41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C8FF41'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C8FF41'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C8FF41'>&nbsp;@&nbsp;</font><font style='background-color: #C8FF41; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8FF41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8FF41; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CHARTREUSE = -0x1.2ae1cep126F;
    static { NAMED.put("chartreuse", -0x1.2ae1cep126F); LIST.add(-0x1.2ae1cep126F); }

    /**
     * This color constant "cactus" has RGBA8888 code {@code 30A000FF}, L 0.5137255, A 0.41960785, B 0.5647059, alpha 1.0, hue 0.38787603, saturation 0.95450217, and chroma 0.20558903.
     * It can be represented as a packed float with the constant {@code -0x1.20d706p126F}.
     * <pre>
     * <font style='background-color: #30A000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #30A000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #30A000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #30A000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #30A000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #30A000'>&nbsp;@&nbsp;</font><font style='background-color: #30A000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #30A000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #30A000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CACTUS = -0x1.20d706p126F;
    static { NAMED.put("cactus", -0x1.20d706p126F); LIST.add(-0x1.20d706p126F); }

    /**
     * This color constant "lime" has RGBA8888 code {@code 93D300FF}, L 0.7294118, A 0.43529412, B 0.5803922, alpha 1.0, hue 0.35362232, saturation 0.94288415, and chroma 0.20558903.
     * It can be represented as a packed float with the constant {@code -0x1.28df74p126F}.
     * <pre>
     * <font style='background-color: #93D300;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #93D300; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #93D300;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #93D300'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #93D300'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #93D300'>&nbsp;@&nbsp;</font><font style='background-color: #93D300; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #93D300;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #93D300; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIME = -0x1.28df74p126F;
    static { NAMED.put("lime", -0x1.28df74p126F); LIST.add(-0x1.28df74p126F); }

    /**
     * This color constant "olive" has RGBA8888 code {@code 818000FF}, L 0.47843137, A 0.4745098, B 0.56078434, alpha 1.0, hue 0.30710015, saturation 0.9493337, and chroma 0.13131043.
     * It can be represented as a packed float with the constant {@code -0x1.1ef2f4p126F}.
     * <pre>
     * <font style='background-color: #818000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #818000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #818000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #818000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #818000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #818000'>&nbsp;@&nbsp;</font><font style='background-color: #818000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #818000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #818000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float OLIVE = -0x1.1ef2f4p126F;
    static { NAMED.put("olive", -0x1.1ef2f4p126F); LIST.add(-0x1.1ef2f4p126F); }

    /**
     * This color constant "fern" has RGBA8888 code {@code 4E7942FF}, L 0.41960785, A 0.4627451, B 0.5294118, alpha 1.0, hue 0.38435107, saturation 0.51240826, and chroma 0.0945603.
     * It can be represented as a packed float with the constant {@code -0x1.0eecd6p126F}.
     * <pre>
     * <font style='background-color: #4E7942;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E7942; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E7942;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4E7942'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4E7942'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4E7942'>&nbsp;@&nbsp;</font><font style='background-color: #4E7942; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E7942;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E7942; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FERN = -0x1.0eecd6p126F;
    static { NAMED.put("fern", -0x1.0eecd6p126F); LIST.add(-0x1.0eecd6p126F); }

    /**
     * This color constant "moss" has RGBA8888 code {@code 204608FF}, L 0.23137255, A 0.45882353, B 0.5372549, alpha 1.0, hue 0.375, saturation 0.94280905, and chroma 0.11062346.
     * It can be represented as a packed float with the constant {@code -0x1.12ea76p126F}.
     * <pre>
     * <font style='background-color: #204608;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #204608; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #204608;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #204608'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #204608'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #204608'>&nbsp;@&nbsp;</font><font style='background-color: #204608; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #204608;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #204608; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MOSS = -0x1.12ea76p126F;
    static { NAMED.put("moss", -0x1.12ea76p126F); LIST.add(-0x1.12ea76p126F); }

    /**
     * This color constant "celery" has RGBA8888 code {@code 7DFF73FF}, L 0.85490197, A 0.41960785, B 0.56078434, alpha 1.0, hue 0.39261138, saturation 0.64841765, and chroma 0.20078278.
     * It can be represented as a packed float with the constant {@code -0x1.1ed7b4p126F}.
     * <pre>
     * <font style='background-color: #7DFF73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7DFF73; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7DFF73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7DFF73'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7DFF73'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7DFF73'>&nbsp;@&nbsp;</font><font style='background-color: #7DFF73; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7DFF73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7DFF73; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CELERY = -0x1.1ed7b4p126F;
    static { NAMED.put("celery", -0x1.1ed7b4p126F); LIST.add(-0x1.1ed7b4p126F); }

    /**
     * This color constant "sage" has RGBA8888 code {@code ABE3C5FF}, L 0.83137256, A 0.46666667, B 0.50980395, alpha 1.0, hue 0.44289985, saturation 0.31069103, and chroma 0.069218926.
     * It can be represented as a packed float with the constant {@code -0x1.04efa8p126F}.
     * <pre>
     * <font style='background-color: #ABE3C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABE3C5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABE3C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ABE3C5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ABE3C5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ABE3C5'>&nbsp;@&nbsp;</font><font style='background-color: #ABE3C5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABE3C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABE3C5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SAGE = -0x1.04efa8p126F;
    static { NAMED.put("sage", -0x1.04efa8p126F); LIST.add(-0x1.04efa8p126F); }

    /**
     * This color constant "jade" has RGBA8888 code {@code 3FBF3FFF}, L 0.62352943, A 0.41568628, B 0.5568628, alpha 1.0, hue 0.40128404, saturation 0.806468, and chroma 0.20259848.
     * It can be represented as a packed float with the constant {@code -0x1.1cd53ep126F}.
     * <pre>
     * <font style='background-color: #3FBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3FBF3F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3FBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3FBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3FBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3FBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #3FBF3F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3FBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3FBF3F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float JADE = -0x1.1cd53ep126F;
    static { NAMED.put("jade", -0x1.1cd53ep126F); LIST.add(-0x1.1cd53ep126F); }

    /**
     * This color constant "cyan" has RGBA8888 code {@code 00FFFFFF}, L 0.8627451, A 0.42352942, B 0.47843137, alpha 1.0, hue 0.5409546, saturation 0.9583845, and chroma 0.1582875.
     * It can be represented as a packed float with the constant {@code -0x1.f4d9b8p125F}.
     * <pre>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #00FFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CYAN = -0x1.f4d9b8p125F;
    static { NAMED.put("cyan", -0x1.f4d9b8p125F); LIST.add(-0x1.f4d9b8p125F); }

    /**
     * This color constant "mint" has RGBA8888 code {@code 7FFFD4FF}, L 0.88235295, A 0.4392157, B 0.50980395, alpha 1.0, hue 0.4685836, saturation 0.6118823, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.04e1c2p126F}.
     * <pre>
     * <font style='background-color: #7FFFD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7FFFD4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7FFFD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7FFFD4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7FFFD4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7FFFD4'>&nbsp;@&nbsp;</font><font style='background-color: #7FFFD4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7FFFD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7FFFD4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MINT = -0x1.04e1c2p126F;
    static { NAMED.put("mint", -0x1.04e1c2p126F); LIST.add(-0x1.04e1c2p126F); }

    /**
     * This color constant "teal" has RGBA8888 code {@code 007F7FFF}, L 0.42745098, A 0.4509804, B 0.4862745, alpha 1.0, hue 0.5389897, saturation 0.95148593, and chroma 0.10141215.
     * It can be represented as a packed float with the constant {@code -0x1.f8e6dap125F}.
     * <pre>
     * <font style='background-color: #007F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007F7F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #007F7F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #007F7F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #007F7F'>&nbsp;@&nbsp;</font><font style='background-color: #007F7F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007F7F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TEAL = -0x1.f8e6dap125F;
    static { NAMED.put("teal", -0x1.f8e6dap125F); LIST.add(-0x1.f8e6dap125F); }

    /**
     * This color constant "turquoise" has RGBA8888 code {@code 2ED6C9FF}, L 0.7254902, A 0.43137255, B 0.49019608, alpha 1.0, hue 0.5186383, saturation 0.9009075, and chroma 0.1381068.
     * It can be represented as a packed float with the constant {@code -0x1.fadd72p125F}.
     * <pre>
     * <font style='background-color: #2ED6C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2ED6C9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2ED6C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2ED6C9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2ED6C9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2ED6C9'>&nbsp;@&nbsp;</font><font style='background-color: #2ED6C9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2ED6C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2ED6C9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TURQUOISE = -0x1.fadd72p125F;
    static { NAMED.put("turquoise", -0x1.fadd72p125F); LIST.add(-0x1.fadd72p125F); }

    /**
     * This color constant "sky" has RGBA8888 code {@code 10C0E0FF}, L 0.6627451, A 0.44313726, B 0.4627451, alpha 1.0, hue 0.59093124, saturation 0.9246287, and chroma 0.13542919.
     * It can be represented as a packed float with the constant {@code -0x1.ece352p125F}.
     * <pre>
     * <font style='background-color: #10C0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #10C0E0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #10C0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #10C0E0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #10C0E0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #10C0E0'>&nbsp;@&nbsp;</font><font style='background-color: #10C0E0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #10C0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #10C0E0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SKY = -0x1.ece352p125F;
    static { NAMED.put("sky", -0x1.ece352p125F); LIST.add(-0x1.ece352p125F); }

    /**
     * This color constant "cobalt" has RGBA8888 code {@code 0046ABFF}, L 0.3019608, A 0.48235294, B 0.4117647, alpha 1.0, hue 0.72137564, saturation 0.9126808, and chroma 0.1792624.
     * It can be represented as a packed float with the constant {@code -0x1.d2f69ap125F}.
     * <pre>
     * <font style='background-color: #0046AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0046AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0046AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0046AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0046AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0046AB'>&nbsp;@&nbsp;</font><font style='background-color: #0046AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0046AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0046AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float COBALT = -0x1.d2f69ap125F;
    static { NAMED.put("cobalt", -0x1.d2f69ap125F); LIST.add(-0x1.d2f69ap125F); }

    /**
     * This color constant "denim" has RGBA8888 code {@code 3088B8FF}, L 0.49411765, A 0.46666667, B 0.45490196, alpha 1.0, hue 0.64992374, saturation 0.7772269, and chroma 0.11172148.
     * It can be represented as a packed float with the constant {@code -0x1.e8eefcp125F}.
     * <pre>
     * <font style='background-color: #3088B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3088B8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3088B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3088B8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3088B8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3088B8'>&nbsp;@&nbsp;</font><font style='background-color: #3088B8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3088B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3088B8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DENIM = -0x1.e8eefcp125F;
    static { NAMED.put("denim", -0x1.e8eefcp125F); LIST.add(-0x1.e8eefcp125F); }

    /**
     * This color constant "navy" has RGBA8888 code {@code 000080FF}, L 0.15294118, A 0.4862745, B 0.4, alpha 1.0, hue 0.73099244, saturation 0.96843684, and chroma 0.20108652.
     * It can be represented as a packed float with the constant {@code -0x1.ccf84ep125F}.
     * <pre>
     * <font style='background-color: #000080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000080; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000080'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000080'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000080'>&nbsp;@&nbsp;</font><font style='background-color: #000080; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000080; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float NAVY = -0x1.ccf84ep125F;
    static { NAMED.put("navy", -0x1.ccf84ep125F); LIST.add(-0x1.ccf84ep125F); }

    /**
     * This color constant "lavender" has RGBA8888 code {@code B991FFFF}, L 0.6627451, A 0.53333336, B 0.43529412, alpha 1.0, hue 0.83154917, saturation 0.34313196, and chroma 0.14500555.
     * It can be represented as a packed float with the constant {@code -0x1.df1152p125F}.
     * <pre>
     * <font style='background-color: #B991FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B991FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B991FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B991FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B991FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B991FF'>&nbsp;@&nbsp;</font><font style='background-color: #B991FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B991FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B991FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LAVENDER = -0x1.df1152p125F;
    static { NAMED.put("lavender", -0x1.df1152p125F); LIST.add(-0x1.df1152p125F); }

    /**
     * This color constant "plum" has RGBA8888 code {@code BE0DC6FF}, L 0.44313726, A 0.60784316, B 0.42352942, alpha 1.0, hue 0.90511185, saturation 0.9399402, and chroma 0.26337513.
     * It can be represented as a packed float with the constant {@code -0x1.d936e2p125F}.
     * <pre>
     * <font style='background-color: #BE0DC6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE0DC6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE0DC6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BE0DC6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BE0DC6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BE0DC6'>&nbsp;@&nbsp;</font><font style='background-color: #BE0DC6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE0DC6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE0DC6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PLUM = -0x1.d936e2p125F;
    static { NAMED.put("plum", -0x1.d936e2p125F); LIST.add(-0x1.d936e2p125F); }

    /**
     * This color constant "mauve" has RGBA8888 code {@code AB73ABFF}, L 0.5372549, A 0.5411765, B 0.47058824, alpha 1.0, hue 0.9098022, saturation 0.32193592, and chroma 0.1008085.
     * It can be represented as a packed float with the constant {@code -0x1.f11512p125F}.
     * <pre>
     * <font style='background-color: #AB73AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB73AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB73AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB73AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB73AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB73AB'>&nbsp;@&nbsp;</font><font style='background-color: #AB73AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB73AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB73AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MAUVE = -0x1.f11512p125F;
    static { NAMED.put("mauve", -0x1.f11512p125F); LIST.add(-0x1.f11512p125F); }

    /**
     * This color constant "rose" has RGBA8888 code {@code E61E78FF}, L 0.48235294, A 0.6156863, B 0.49411765, alpha 1.0, hue 0.99469686, saturation 0.90959585, and chroma 0.23076649.
     * It can be represented as a packed float with the constant {@code -0x1.fd3af6p125F}.
     * <pre>
     * <font style='background-color: #E61E78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E61E78; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E61E78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E61E78'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E61E78'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E61E78'>&nbsp;@&nbsp;</font><font style='background-color: #E61E78; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E61E78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E61E78; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ROSE = -0x1.fd3af6p125F;
    static { NAMED.put("rose", -0x1.fd3af6p125F); LIST.add(-0x1.fd3af6p125F); }

    /**
     * This color constant "raspberry" has RGBA8888 code {@code 911437FF}, L 0.29803923, A 0.5803922, B 0.5137255, alpha 1.0, hue 0.029956235, saturation 0.9096833, and chroma 0.16247371.
     * It can be represented as a packed float with the constant {@code -0x1.072898p126F}.
     * <pre>
     * <font style='background-color: #911437;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #911437; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #911437;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #911437'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #911437'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #911437'>&nbsp;@&nbsp;</font><font style='background-color: #911437; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #911437;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #911437; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float RASPBERRY = -0x1.072898p126F;
    static { NAMED.put("raspberry", -0x1.072898p126F); LIST.add(-0x1.072898p126F); }

    /**
     * All names for colors in this palette, in alphabetical order. You can fetch the corresponding packed float color
     * by looking up a name in {@link #NAMED}.
     */
    public static final Array<String> NAMES = NAMED.keys().toArray();
    static { NAMES.sort(); }
    /**
     * All names for colors in this palette, with grayscale first, then sorted by hue from red to yellow to green to
     * blue. You can fetch the corresponding packed float color by looking up a name in {@link #NAMED}.
     */
    public static final Array<String> NAMES_BY_HUE = new Array<>(NAMES);

    public static final FloatArray COLORS_BY_HUE = new FloatArray(NAMES_BY_HUE.size);
    /**
     * All names for colors in this palette, sorted by lightness from black to white. You can fetch the
     * corresponding packed float color by looking up a name in {@link #NAMED}.
     */
    public static final Array<String> NAMES_BY_LIGHTNESS = new Array<>(NAMES);
    static {
        NAMES_BY_HUE.sort(new Comparator<String>() {
            public int compare(String o1, String o2) {
                final float c1 = NAMED.get(o1, TRANSPARENT), c2 = NAMED.get(o2, TRANSPARENT);
                final float s1 = ColorTools.saturation(c1), s2 = ColorTools.saturation(c2);
                if(alphaInt(c1) < 128) return -10000;
                else if(alphaInt(c2) < 128) return 10000;
                else if(s1 <= 0.05f && s2 > 0.05f)
                    return -1000;
                else if(s1 > 0.05f && s2 <= 0.05f)
                    return 1000;
                else if(s1 <= 0.05f && s2 <= 0.05f)
                    return (int)Math.signum(ColorTools.channelL(c1) - ColorTools.channelL(c2));
                else
                    return 2 * (int)Math.signum(ColorTools.hue(c1) - ColorTools.hue(c2))
                            + (int)Math.signum(ColorTools.channelL(c1) - ColorTools.channelL(c2));
            }
        });
        for(String name : NAMES_BY_HUE) {
            COLORS_BY_HUE.add(NAMED.get(name, TRANSPARENT));
        }
        NAMES_BY_LIGHTNESS.sort(new Comparator<String>() {
            public int compare(String o1, String o2) {
                return Float.compare(channelL(NAMED.get(o1, TRANSPARENT)), channelL(NAMED.get(o2, TRANSPARENT)));
            }
        });
    }

    private static final FloatArray mixing = new FloatArray(4);

    /**
     * Parses a color description and returns the approximate color it describes, as a packed Oklab float color.
     * Color descriptions consist of one or more alphabetical words, separated by non-alphanumeric characters (typically
     * spaces and/or hyphens, though the underscore is treated as a letter). Any word that is the name of a color in
     * this palette will be looked up in {@link #NAMED} and tracked; if there is more than one of these color name
     * words, the colors will be mixed using {@link FloatColors#unevenMix(float[], int, int)}, or if there is just one
     * color name word, then the corresponding color will be used. A number can be present after a color name (separated
     * by any non-alphanumeric character(s) other than the underscore); if so, it acts as a positive weight for that
     * color name when mixed with other named colors. The recommended separator between a color name and its weight is
     * the char {@code '^'}, but other punctuation like {@code ':'} is equally valid. You can also repeat a color name
     * to increase its weight. You may use a decimal point in weights to make them floats.
     * <br>
     * The special adjectives "light" and "dark" change the lightness of the described color; likewise, "rich" and
     * "dull" change the saturation (how different the color is from grayscale). All of these adjectives can have "-er"
     * or "-est" appended to make their effect twice or three times as strong. Technically, the chars appended to an
     * adjective don't matter, only their count, so "lightaa" is the same as "lighter" and "richcat" is the same as
     * "richest". There's an unofficial fourth level as well, used when any 4 characters are appended to an adjective
     * (as in "darkmost"); it has four times the effect of the original adjective. There are also the adjectives
     * "bright" (equivalent to "light rich"), "pale" ("light dull"), "deep" ("dark rich"), and "weak" ("dark dull").
     * These can be amplified like the other four, except that "pale" goes to "paler", "palest", and then to
     * "palemax" or (its equivalent) "palemost", where only the word length is checked. The case of adjectives doesn't
     * matter here; they can be all-caps, all lower-case, or mixed-case without issues. The names of colors, however,
     * are case-sensitive, because you can combine other named color palettes with the one here, and at least in one
     * common situation (merging libGDX Colors with the palette here), the other palette uses all-caps names only.
     * <br>
     * If part of a color name or adjective is invalid, it is not considered; if the description is empty or fully
     * invalid, this returns the float color {@code 0f}, or fully transparent black.
     * <br>
     * Examples of valid descriptions include "blue", "dark green", "duller red", "peach pink", "indigo purple mauve",
     * "lightest richer apricot-olive", "bright magenta", "palest cyan blue", "deep fern black", "weakmost celery",
     * "red^3 orange", and "dark deep blue^7 cyan^3".
     * <br>
     * This overload always reads the whole String provided.
     *
     * @param description a color description, as a String matching the above format
     * @return a packed Oklab float color as described
     */
    public static float parseDescription(final String description) {
        return parseDescription(description, 0, description.length());
    }
    /**
     * Parses a color description and returns the approximate color it describes, as a packed Oklab float color.
     * Color descriptions consist of one or more alphabetical words, separated by non-alphanumeric characters (typically
     * spaces and/or hyphens, though the underscore is treated as a letter). Any word that is the name of a color in
     * this palette will be looked up in {@link #NAMED} and tracked; if there is more than one of these color name
     * words, the colors will be mixed using {@link FloatColors#unevenMix(float[], int, int)}, or if there is just one
     * color name word, then the corresponding color will be used. A number can be present after a color name (separated
     * by any non-alphanumeric character(s) other than the underscore); if so, it acts as a positive weight for that
     * color name when mixed with other named colors. The recommended separator between a color name and its weight is
     * the char {@code '^'}, but other punctuation like {@code ':'} is equally valid. You can also repeat a color name
     * to increase its weight. You may use a decimal point in weights to make them floats.
     * <br>
     * The special adjectives "light" and "dark" change the lightness of the described color; likewise, "rich" and
     * "dull" change the saturation (how different the color is from grayscale). All of these adjectives can have "-er"
     * or "-est" appended to make their effect twice or three times as strong. Technically, the chars appended to an
     * adjective don't matter, only their count, so "lightaa" is the same as "lighter" and "richcat" is the same as
     * "richest". There's an unofficial fourth level as well, used when any 4 characters are appended to an adjective
     * (as in "darkmost"); it has four times the effect of the original adjective. There are also the adjectives
     * "bright" (equivalent to "light rich"), "pale" ("light dull"), "deep" ("dark rich"), and "weak" ("dark dull").
     * These can be amplified like the other four, except that "pale" goes to "paler", "palest", and then to
     * "palemax" or (its equivalent) "palemost", where only the word length is checked. The case of adjectives doesn't
     * matter here; they can be all-caps, all lower-case, or mixed-case without issues. The names of colors, however,
     * are case-sensitive, because you can combine other named color palettes with the one here, and at least in one
     * common situation (merging libGDX Colors with the palette here), the other palette uses all-caps names only.
     * <br>
     * If part of a color name or adjective is invalid, it is not considered; if the description is empty or fully
     * invalid, this returns the float color {@code 0f}, or fully transparent black.
     * <br>
     * Examples of valid descriptions include "blue", "dark green", "duller red", "peach pink", "indigo purple mauve",
     * "lightest richer apricot-olive", "bright magenta", "palest cyan blue", "deep fern black", "weakmost celery",
     * "red^3 orange", and "dark deep blue^7 cyan^3".
     * <br>
     * This overload lets you specify a
     * starting index in {@code description} to read from and a maximum {@code length} to read before stopping. If
     * {@code length} is negative, this reads the rest of {@code description} after {@code start}.
     *
     * @param description a color description, as a String matching the above format
     * @param start the first character index of the description to read from
     * @param length how much of description to attempt to parse; if negative, this parses until the end
     * @return a packed Oklab float color as described
     */
    public static float parseDescription(final String description, int start, int length) {
        float lightness = 0f, saturation = 0f;
        final String[] terms = description.substring(start,
                        length < 0 ? description.length() - start : Math.min(description.length(), start + length))
                .split("[^a-zA-Z0-9_.]+");
        mixing.clear();
        for (int i = 0; i < terms.length; i++) {
            String term = terms[i];
            if (term == null || term.isEmpty()) continue;
            final int len = term.length();
            switch (term.charAt(0)) {
                case 'L':
                case 'l':
                    if (len > 2 && (term.charAt(2) == 'g' || term.charAt(2) == 'G')) { // light
                        switch (len) {
                            case 9:
                                lightness += 0.150f;
                            case 8:
                                lightness += 0.150f;
                            case 7:
                                lightness += 0.150f;
                            case 5:
                                lightness += 0.150f;
                                continue;
                        }
                    }
                    mixing.add(NAMED.get(term, 0f), 1);
                    break;
                case 'B':
                case 'b':
                    if (len > 3 && (term.charAt(3) == 'g' || term.charAt(3) == 'G')) { // bright
                        switch (len) {
                            case 10:
                                lightness += 0.150f;
                                saturation += 00.20000f;
                            case 9:
                                lightness += 0.150f;
                                saturation += 00.2000f;
                            case 8:
                                lightness += 0.150f;
                                saturation += 00.200f;
                            case 6:
                                lightness += 0.150f;
                                saturation += 00.20f;
                                continue;
                        }
                    }
                    mixing.add(NAMED.get(term, 0f), 1);
                    break;
                case 'P':
                case 'p':
                    if (len > 2 && (term.charAt(2) == 'l' || term.charAt(2) == 'L')) { // pale
                        switch (len) {
                            case 8: // palemost
                            case 7: // palerer
                                lightness += 0.150f;
                                saturation -= 00.20000f;
                            case 6: // palest
                                lightness += 0.150f;
                                saturation -= 00.2000f;
                            case 5: // paler
                                lightness += 0.150f;
                                saturation -= 00.200f;
                            case 4: // pale
                                lightness += 0.150f;
                                saturation -= 00.20f;
                                continue;
                        }
                    }
                    mixing.add(NAMED.get(term, 0f), 1);
                    break;
                case 'W':
                case 'w':
                    if (len > 3 && (term.charAt(3) == 'k' || term.charAt(3) == 'K')) { // weak
                        switch (len) {
                            case 8:
                                lightness -= 0.150f;
                                saturation -= 00.20000f;
                            case 7:
                                lightness -= 0.150f;
                                saturation -= 00.2000f;
                            case 6:
                                lightness -= 0.150f;
                                saturation -= 00.200f;
                            case 4:
                                lightness -= 0.150f;
                                saturation -= 00.20f;
                                continue;
                        }
                    }
                    mixing.add(NAMED.get(term, 0f), 1);
                    break;
                case 'R':
                case 'r':
                    if (len > 1 && (term.charAt(1) == 'i' || term.charAt(1) == 'I')) { // rich
                        switch (len) {
                            case 8:
                                saturation += 00.20000f;
                            case 7:
                                saturation += 00.2000f;
                            case 6:
                                saturation += 00.200f;
                            case 4:
                                saturation += 00.20f;
                                continue;
                        }
                    }
                    mixing.add(NAMED.get(term, 0f), 1);
                    break;
                case 'D':
                case 'd':
                    if (len > 1 && (term.charAt(1) == 'a' || term.charAt(1) == 'A')) { // dark
                        switch (len) {
                            case 8:
                                lightness -= 0.150f;
                            case 7:
                                lightness -= 0.150f;
                            case 6:
                                lightness -= 0.150f;
                            case 4:
                                lightness -= 0.150f;
                                continue;
                        }
                    } else if (len > 1 && (term.charAt(1) == 'u' || term.charAt(1) == 'U')) { // dull
                        switch (len) {
                            case 8:
                                saturation -= 00.20000f;
                            case 7:
                                saturation -= 00.2000f;
                            case 6:
                                saturation -= 00.200f;
                            case 4:
                                saturation -= 00.20f;
                                continue;
                        }
                    } else if (len > 3 && (term.charAt(3) == 'p' || term.charAt(3) == 'P')) { // deep
                        switch (len) {
                            case 8:
                                lightness -= 0.150f;
                                saturation += 00.20000f;
                            case 7:
                                lightness -= 0.150f;
                                saturation += 00.2000f;
                            case 6:
                                lightness -= 0.150f;
                                saturation += 00.200f;
                            case 4:
                                lightness -= 0.150f;
                                saturation += 00.20f;
                                continue;
                        }
                    }
                    mixing.add(NAMED.get(term, 0f), 1);
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    if(mixing.size >= 2) {
                        float num = 1;
                        try {
                            num = Float.parseFloat(term);
                        } catch (NullPointerException | NumberFormatException ignored) {
                        }
                        mixing.set((mixing.size & -2) - 1, num);
                    }
                    break;
                default:
                    mixing.add(NAMED.get(term, 0f), 1);
                    break;
            }
        }
        if(mixing.size < 2) return 0f;

        float result = unevenMix(mixing.items, 0, mixing.size);
        if(result == 0f) return result;

        if(lightness > 0) result = FloatColors.lerpFloatColorsBlended(result, WHITE, lightness);
        else if(lightness < 0) result = FloatColors.lerpFloatColorsBlended(result, BLACK, -lightness);

        if(saturation > 0) result = ColorTools.enrich(result, saturation);
        else if(saturation < 0) result = ColorTools.dullen(result, -saturation);

        return result;
    }
    
    private static final Array<String> namesByHue = new Array<>(NAMES_BY_HUE);
    private static final FloatArray colorsByHue = new FloatArray(COLORS_BY_HUE);

    private static final String[] lightAdjectives = {"darkmost ", "darkest ", "darker ", "dark ", "", "light ", "lighter ", "lightest ", "lightmost "};
    private static final String[] satAdjectives = {"dullmost ", "dullest ", "duller ", "dull ", "", "rich ", "richer ", "richest ", "richmost "};
    private static final String[] combinedAdjectives = new String[81];

    static {
        int trn = namesByHue.indexOf("transparent", false);
        namesByHue.removeIndex(trn);
        colorsByHue.removeIndex(trn);
        ALIASES.put("grey", GRAY);
        ALIASES.put("gold", SAFFRON);
        ALIASES.put("puce", MAUVE);
        ALIASES.put("sand", TAN);
        ALIASES.put("skin", PEACH); // Yes, I am aware that there is more than one skin color, but this can only map to one.
        ALIASES.put("coral", SALMON);
        ALIASES.put("azure", SKY);
        ALIASES.put("ocean", TEAL);
        ALIASES.put("sapphire", COBALT);
        NAMED.putAll(ALIASES);

        for (int sat = 0, idx = 0; sat < 9; sat++) {
            for (int lit = 0; lit < 9; lit++) {
                int s = sat - 4, l = lit - 4;
                if(s != l && s != -l)
                    combinedAdjectives[idx] = lightAdjectives[lit] + satAdjectives[sat];
                ++idx;
            }
        }

        // Special cases for multiple-effect adjectives:
        combinedAdjectives[0 * 9 + 0] = "weakmost ";
        combinedAdjectives[1 * 9 + 1] = "weakest ";
        combinedAdjectives[2 * 9 + 2] = "weaker ";
        combinedAdjectives[3 * 9 + 3] = "weak ";

        combinedAdjectives[0 * 9 + 8] = "palemost ";
        combinedAdjectives[1 * 9 + 7] = "palest ";
        combinedAdjectives[2 * 9 + 6] = "paler ";
        combinedAdjectives[3 * 9 + 5] = "pale ";

        combinedAdjectives[8 * 9 + 0] = "deepmost ";
        combinedAdjectives[7 * 9 + 1] = "deepest ";
        combinedAdjectives[6 * 9 + 2] = "deeper ";
        combinedAdjectives[5 * 9 + 3] = "deep ";

        combinedAdjectives[8 * 9 + 8] = "brightmost ";
        combinedAdjectives[7 * 9 + 7] = "brightest ";
        combinedAdjectives[6 * 9 + 6] = "brighter ";
        combinedAdjectives[5 * 9 + 5] = "bright ";

        combinedAdjectives[4 * 9 + 4] = "";
    }

    /**
     * Given a color as a packed Oklab float, this finds the closest description it can to match the given color while
     * using at most {@code mixCount} colors to mix in. You should only use small numbers for mixCount, like 1 to 3;
     * this can take quite a while to run otherwise. This returns a String description that can be passed to
     * {@link #parseDescription(String)}. It is likely that this will use very contrasting colors if mixCount is 2 or
     * greater and the color to match is desaturated or brownish.
     * @param oklab a packed Oklab float color to attempt to match
     * @param mixCount how many color names this will use in the returned description
     * @return a description that can be fed to {@link #parseDescription(String)} to get a similar color
     */
    public static String bestMatch(final float oklab, int mixCount) {
        mixCount = Math.max(1, mixCount);
        float bestDistance = Float.POSITIVE_INFINITY;
        final int paletteSize = namesByHue.size, colorTries = (int)Math.pow(paletteSize, mixCount), totalTries = colorTries * 81;
        final float targetL = ColorTools.channelL(oklab), targetA = ColorTools.channelA(oklab), targetB = ColorTools.channelB(oklab);
        mixing.clear();
        final float first = colorsByHue.get(0);
        for (int i = 0; i < mixCount; i++) {
            mixing.add(first);
        }
        int bestCode = 0;
        for (int c = 0; c < totalTries; c++) {
            for (int i = 0, e = 1; i < mixCount; i++, e *= paletteSize) {
                mixing.set(i, colorsByHue.get((c / e) % paletteSize));
            }
            int idxL = ((c / colorTries) % 9 - 4), idxS = (c / (colorTries * 9) - 4);

            float result = FloatColors.mix(mixing.items, 0, mixCount);
            if(idxL > 0) result = ColorTools.lighten(result, 0.15f * idxL);
            else if(idxL < 0) result = ColorTools.darken(result, -0.15f * idxL);

            if(idxS > 0) result = ColorTools.limitToGamut(ColorTools.enrich(result, idxS * (idxS + 3) * 0.025f));
            else if(idxS < 0) result = ColorTools.dullen(result, idxS * (-idxS + 3) * -0.025f);
            else result = ColorTools.limitToGamut(result);

            float dL = ColorTools.channelL(result) - targetL, dA = ColorTools.channelA(result) - targetA, dB = ColorTools.channelB(result) - targetB;
            if(bestDistance > (bestDistance = Math.min(dL * dL + dA * dA + dB * dB, bestDistance)))
                bestCode = c;
        }

        StringBuilder description = new StringBuilder(combinedAdjectives[(bestCode / colorTries)]);
        for (int i = 0, e = 1; i < mixCount; e *= paletteSize) {
            description.append(namesByHue.get((bestCode / e) % paletteSize));
            if(++i < mixCount)
                description.append(' ');
        }
        return description.toString();
    }

    /**
     * Changes the existing RGBA Color instances in {@link Colors} to use Oklab and so be able to be shown normally by
     * {@link ColorfulBatch} or a Batch using {@link com.github.tommyettinger.colorful.Shaders#fragmentShaderOklab}.
     * Any colors used in libGDX text markup look up their values in Colors, so calling this can help display fonts
     * where markup is enabled. This only needs to be called once, and if you call {@link #appendToKnownColors()}, then
     * that should be done after this to avoid mixing RGBA and Oklab colors.
     * <br>
     * This is a duplicate of a method with the same name in Palette; you should still only call this method once,
     * regardless of where it was from.
     */
    public static void editKnownColors(){
        for(Color c : Colors.getColors().values()) {
            final float f = ColorTools.fromColor(c);
            c.set(channelL(f), channelA(f), channelB(f), c.a);
        }
    }

    /**
     * Appends Oklab-compatible Color instances to the map in {@link Colors}, using the names in {@link #NAMES} (which
     * are "lower cased" instead of "ALL UPPER CASE"). If you intend to still use the existing values in Colors, you
     * should call {@link #editKnownColors()} first; otherwise you can just always use "lower cased" color names.
     * This does append aliases as well, so some color values will be duplicates.
     * <br>
     * This can be used alongside the method with the same name in Palette, since that uses "Title Cased" names.
     */
    public static void appendToKnownColors(){
        for(ObjectFloatMap.Entry<String> ent : NAMED) {
            final float f = ent.value;
            Colors.put(ent.key, new Color(channelL(f), channelA(f), channelB(f), alpha(f)));
        }
    }
}
