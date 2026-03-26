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

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.ObjectFloatMap;

import java.util.Comparator;

/**
 * A palette of predefined colors as packed Oklab floats, the kind {@link ColorTools} works with. This uses a geometric
 * palette, Yam4, that is designed to be as consistent as possible in its support for hues while keeping lots of
 * grayscale, desaturated, and mid-saturation colors, and to have a coherent naming system. This is the successor to
 * Yam (3), and may still be adjusted as needed (this may or may not be in another version like Yam5). The original Yam
 * had too many colors that were indistinguishable from grayscale, despite being named like they had a non-gray color
 * included in their mix. Yam4 is like Yam3, but varies the lightness and saturation of what would otherwise be similar
 * colors significantly. All have 255 opaque colors, plus pure transparent.
 * <br>
 * You can access colors by their constant name, such as {@code PALE_GREEN_CYAN}, by the {@link #NAMED} map using
 * {@code NAMED.get("pale green cyan", 0f)}, or by index in the FloatArray called {@link #LIST}. Note that to access
 * a float color from NAMED, you need to give a default value if the name is not found; {@code 0f} is a good default
 * because it will not occur in a valid Oklab color. You can access the names in a specific order with {@link #NAMES}
 * (which is alphabetical), {@link #NAMES_BY_HUE} (which is sorted by the hue of the matching color, from red to yellow
 * to blue to purple to red again), or {@link #NAMES_BY_LIGHTNESS} (which is sorted by the intensity of
 * the matching color, from darkest to lightest). Having a name lets you look up the matching color in {@link #NAMED}.
 * <br>
 * The names here put the most relevant color last, so "deep green cyan" is more cyan than it is green. There are 12
 * words used for hue; in order, they are: red, brown, orange, saffron, yellow, lime, green, cyan, blue, violet, purple,
 * and magenta. There are also 5 words used for grayscale colors; in order from darkest to lightest, they are: black,
 * lead, gray, silver, and white. There are four very-desaturated (25%) colors per hue, each with a grayscale color name
 * and a hue color name (for each of the twelve hues); these include "silver red" and "black green". Going towards more
 * saturated colors (50%), there are drab, faded, and pale variants of each hue. Getting even more saturated (75%),
 * there are "deep", "true", and "bright" variants of "pure HUE1", "HUE2 HUE1", and "HUE1 HUE2", where the last hue is
 * more important, and hues are only paired with their neighbors in the order or used on their own as a pure hue. At the
 * most saturated edge (100%), the only variant is "bold", and there are again "pure HUE1", "HUE2 HUE1", and "HUE1 HUE2"
 * like with the 75% saturation colors, forming a rainbow-colored band with medium lightness consistently across it. In
 * this last one, "bold violet blue" is primarily blue with some violet tint, while "bold blue violet" has more violet
 * than blue.
 */
public class Yam4Palette {
    public static final ObjectFloatMap<String> NAMED = new ObjectFloatMap<>(256);
    public static final FloatArray LIST = new FloatArray(256);


    /**
     * This color constant "transparent" has RGBA8888 code {@code 00000000}, L 0.0, A 0.49803922, B 0.49803922,
     * alpha 0.0, red 0.0, green 0.0, blue 0.0, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code 0x0.fefep-126F}.
     * <pre>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #000000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float YAM_TRANSPARENT = 0x0.fefep-126F;
    static { NAMED.put("transparent", 0x0.fefep-126F); LIST.add(0x0.fefep-126F); }

    /**
     * This color constant "pure black" has RGBA8888 code {@code 000000FF}, L 0.0, A 0.49803922, B 0.49803922,
     * alpha 1.0, red 0.0, green 0.0, blue 0.0, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.fefep125F}.
     * <pre>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #000000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_BLACK = -0x1.fefep125F;
    static { NAMED.put("pure black", -0x1.fefep125F); LIST.add(-0x1.fefep125F); }

    /**
     * This color constant "almost black" has RGBA8888 code {@code 121212FF}, L 0.07058824, A 0.49803922, B 0.49803922,
     * alpha 1.0, red 0.070597656, green 0.07059015, blue 0.07056262, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.fefe24p125F}.
     * <pre>
     * <font style='background-color: #121212;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #121212; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #121212;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #121212'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #121212'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #121212'>&nbsp;@&nbsp;</font><font style='background-color: #121212; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #121212;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #121212; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ALMOST_BLACK = -0x1.fefe24p125F;
    static { NAMED.put("almost black", -0x1.fefe24p125F); LIST.add(-0x1.fefe24p125F); }

    /**
     * This color constant "lead black" has RGBA8888 code {@code 242424FF}, L 0.14117648, A 0.49803922, B 0.49803922,
     * alpha 1.0, red 0.14119564, green 0.14118065, blue 0.14112559, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.fefe48p125F}.
     * <pre>
     * <font style='background-color: #242424;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #242424; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #242424;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #242424'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #242424'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #242424'>&nbsp;@&nbsp;</font><font style='background-color: #242424; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #242424;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #242424; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_BLACK = -0x1.fefe48p125F;
    static { NAMED.put("lead black", -0x1.fefe48p125F); LIST.add(-0x1.fefe48p125F); }

    /**
     * This color constant "black lead" has RGBA8888 code {@code 363636FF}, L 0.21176471, A 0.49803922, B 0.49803922,
     * alpha 1.0, red 0.21179298, green 0.21177045, blue 0.21168786, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.fefe6cp125F}.
     * <pre>
     * <font style='background-color: #363636;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #363636; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #363636;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #363636'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #363636'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #363636'>&nbsp;@&nbsp;</font><font style='background-color: #363636; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #363636;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #363636; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_LEAD = -0x1.fefe6cp125F;
    static { NAMED.put("black lead", -0x1.fefe6cp125F); LIST.add(-0x1.fefe6cp125F); }

    /**
     * This color constant "pure lead" has RGBA8888 code {@code 494949FF}, L 0.28627452, A 0.49803922, B 0.49803922,
     * alpha 1.0, red 0.28631294, green 0.28628254, blue 0.2861709, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.fefe92p125F}.
     * <pre>
     * <font style='background-color: #494949;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #494949; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #494949;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #494949'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #494949'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #494949'>&nbsp;@&nbsp;</font><font style='background-color: #494949; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #494949;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #494949; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_LEAD = -0x1.fefe92p125F;
    static { NAMED.put("pure lead", -0x1.fefe92p125F); LIST.add(-0x1.fefe92p125F); }

    /**
     * This color constant "gray lead" has RGBA8888 code {@code 5B5B5BFF}, L 0.35686275, A 0.49803922, B 0.49803922,
     * alpha 1.0, red 0.35691032, green 0.35687238, blue 0.35673323, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.fefeb6p125F}.
     * <pre>
     * <font style='background-color: #5B5B5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B5B5B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B5B5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5B5B5B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5B5B5B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5B5B5B'>&nbsp;@&nbsp;</font><font style='background-color: #5B5B5B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B5B5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B5B5B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_LEAD = -0x1.fefeb6p125F;
    static { NAMED.put("gray lead", -0x1.fefeb6p125F); LIST.add(-0x1.fefeb6p125F); }

    /**
     * This color constant "lead gray" has RGBA8888 code {@code 6D6D6DFF}, L 0.42745098, A 0.49803922, B 0.49803922,
     * alpha 1.0, red 0.42750823, green 0.42746276, blue 0.42729607, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.fefedap125F}.
     * <pre>
     * <font style='background-color: #6D6D6D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D6D6D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D6D6D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6D6D6D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6D6D6D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6D6D6D'>&nbsp;@&nbsp;</font><font style='background-color: #6D6D6D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D6D6D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D6D6D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_GRAY = -0x1.fefedap125F;
    static { NAMED.put("lead gray", -0x1.fefedap125F); LIST.add(-0x1.fefedap125F); }

    /**
     * This color constant "pure gray" has RGBA8888 code {@code 7F7F7FFF}, L 0.49803922, A 0.49803922, B 0.49803922,
     * alpha 1.0, red 0.4981066, green 0.49805364, blue 0.49785942, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.fefefep125F}.
     * <pre>
     * <font style='background-color: #7F7F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F7F7F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F7F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7F7F7F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7F7F7F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7F7F7F'>&nbsp;@&nbsp;</font><font style='background-color: #7F7F7F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F7F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F7F7F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_GRAY = -0x1.fefefep125F;
    static { NAMED.put("pure gray", -0x1.fefefep125F); LIST.add(-0x1.fefefep125F); }

    /**
     * This color constant "silver gray" has RGBA8888 code {@code 929292FF}, L 0.57254905, A 0.49803922, B 0.49803922,
     * alpha 1.0, red 0.57262534, green 0.5725645, blue 0.57234114, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.feff24p125F}.
     * <pre>
     * <font style='background-color: #929292;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #929292; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #929292;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #929292'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #929292'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #929292'>&nbsp;@&nbsp;</font><font style='background-color: #929292; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #929292;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #929292; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_GRAY = -0x1.feff24p125F;
    static { NAMED.put("silver gray", -0x1.feff24p125F); LIST.add(-0x1.feff24p125F); }

    /**
     * This color constant "gray silver" has RGBA8888 code {@code A4A4A4FF}, L 0.6431373, A 0.49803922, B 0.49803922,
     * alpha 1.0, red 0.643223, green 0.64315456, blue 0.6429038, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.feff48p125F}.
     * <pre>
     * <font style='background-color: #A4A4A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4A4A4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4A4A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A4A4A4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A4A4A4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A4A4A4'>&nbsp;@&nbsp;</font><font style='background-color: #A4A4A4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4A4A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4A4A4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_SILVER = -0x1.feff48p125F;
    static { NAMED.put("gray silver", -0x1.feff48p125F); LIST.add(-0x1.feff48p125F); }

    /**
     * This color constant "pure silver" has RGBA8888 code {@code B6B6B6FF}, L 0.7137255, A 0.49803922, B 0.49803922,
     * alpha 1.0, red 0.71382064, green 0.71374476, blue 0.71346647, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.feff6cp125F}.
     * <pre>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B6B6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #B6B6B6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B6B6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_SILVER = -0x1.feff6cp125F;
    static { NAMED.put("pure silver", -0x1.feff6cp125F); LIST.add(-0x1.feff6cp125F); }

    /**
     * This color constant "white silver" has RGBA8888 code {@code C9C9C9FF}, L 0.7882353, A 0.49803922, B 0.49803922,
     * alpha 1.0, red 0.7883404, green 0.7882565, blue 0.7879492, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.feff92p125F}.
     * <pre>
     * <font style='background-color: #C9C9C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9C9C9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9C9C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C9C9C9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C9C9C9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C9C9C9'>&nbsp;@&nbsp;</font><font style='background-color: #C9C9C9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9C9C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9C9C9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_SILVER = -0x1.feff92p125F;
    static { NAMED.put("white silver", -0x1.feff92p125F); LIST.add(-0x1.feff92p125F); }

    /**
     * This color constant "silver white" has RGBA8888 code {@code DBDBDBFF}, L 0.85882354, A 0.49803922, B 0.49803922,
     * alpha 1.0, red 0.8589381, green 0.8588467, blue 0.8585118, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.feffb6p125F}.
     * <pre>
     * <font style='background-color: #DBDBDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBDBDB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBDBDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DBDBDB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DBDBDB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DBDBDB'>&nbsp;@&nbsp;</font><font style='background-color: #DBDBDB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBDBDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBDBDB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_WHITE = -0x1.feffb6p125F;
    static { NAMED.put("silver white", -0x1.feffb6p125F); LIST.add(-0x1.feffb6p125F); }

    /**
     * This color constant "almost white" has RGBA8888 code {@code EDEDEDFF}, L 0.92941177, A 0.49803922, B 0.49803922,
     * alpha 1.0, red 0.92953587, green 0.92943704, blue 0.9290746, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.feffdap125F}.
     * <pre>
     * <font style='background-color: #EDEDED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDEDED; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDEDED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EDEDED'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EDEDED'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EDEDED'>&nbsp;@&nbsp;</font><font style='background-color: #EDEDED; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDEDED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDEDED; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ALMOST_WHITE = -0x1.feffdap125F;
    static { NAMED.put("almost white", -0x1.feffdap125F); LIST.add(-0x1.feffdap125F); }

    /**
     * This color constant "pure white" has RGBA8888 code {@code FFFFFFFF}, L 1.0, A 0.49803922, B 0.49803922,
     * alpha 1.0, red 1.0, green 1.0, blue 0.99963707, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.fefffep125F}.
     * <pre>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #FFFFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_WHITE = -0x1.fefffep125F;
    static { NAMED.put("pure white", -0x1.fefffep125F); LIST.add(-0x1.fefffep125F); }

    /**
     * This color constant "black red" has RGBA8888 code {@code 51302DFF}, L 0.22745098, A 0.52156866, B 0.50980395,
     * alpha 1.0, red 0.31716886, green 0.18943171, blue 0.17623541, hue 0.0737918, saturation 0.33541018, and chroma 0.0471994.
     * It can be represented as a packed float with the constant {@code -0x1.050a74p126F}.
     * <pre>
     * <font style='background-color: #51302D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #51302D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #51302D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #51302D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #51302D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #51302D'>&nbsp;@&nbsp;</font><font style='background-color: #51302D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #51302D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #51302D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_RED = -0x1.050a74p126F;
    static { NAMED.put("black red", -0x1.050a74p126F); LIST.add(-0x1.050a74p126F); }

    /**
     * This color constant "lead red" has RGBA8888 code {@code 7E5752FF}, L 0.38431373, A 0.52156866, B 0.50980395,
     * alpha 1.0, red 0.4943358, green 0.34129766, blue 0.32370347, hue 0.0737918, saturation 0.23537557, and chroma 0.0471994.
     * It can be represented as a packed float with the constant {@code -0x1.050ac4p126F}.
     * <pre>
     * <font style='background-color: #7E5752;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E5752; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E5752;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7E5752'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7E5752'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7E5752'>&nbsp;@&nbsp;</font><font style='background-color: #7E5752; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E5752;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E5752; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_RED = -0x1.050ac4p126F;
    static { NAMED.put("lead red", -0x1.050ac4p126F); LIST.add(-0x1.050ac4p126F); }

    /**
     * This color constant "gray red" has RGBA8888 code {@code A97D78FF}, L 0.5372549, A 0.52156866, B 0.50980395,
     * alpha 1.0, red 0.6622112, green 0.49027038, blue 0.46963495, hue 0.0737918, saturation 0.2236068, and chroma 0.0471994.
     * It can be represented as a packed float with the constant {@code -0x1.050b12p126F}.
     * <pre>
     * <font style='background-color: #A97D78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A97D78; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A97D78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A97D78'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A97D78'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A97D78'>&nbsp;@&nbsp;</font><font style='background-color: #A97D78; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A97D78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A97D78; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_RED = -0x1.050b12p126F;
    static { NAMED.put("gray red", -0x1.050b12p126F); LIST.add(-0x1.050b12p126F); }

    /**
     * This color constant "silver red" has RGBA8888 code {@code D4A49EFF}, L 0.69411767, A 0.52156866, B 0.50980395,
     * alpha 1.0, red 0.8316423, green 0.64367557, blue 0.62054724, hue 0.0737918, saturation 0.3946002, and chroma 0.0471994.
     * It can be represented as a packed float with the constant {@code -0x1.050b62p126F}.
     * <pre>
     * <font style='background-color: #D4A49E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4A49E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4A49E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D4A49E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D4A49E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D4A49E'>&nbsp;@&nbsp;</font><font style='background-color: #D4A49E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4A49E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4A49E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_RED = -0x1.050b62p126F;
    static { NAMED.put("silver red", -0x1.050b62p126F); LIST.add(-0x1.050b62p126F); }

    /**
     * This color constant "white red" has RGBA8888 code {@code FECBC4FF}, L 0.8509804, A 0.52156866, B 0.50980395,
     * alpha 1.0, red 0.9992715, green 0.79751444, blue 0.77228075, hue 0.0737918, saturation 0.8944272, and chroma 0.0471994.
     * It can be represented as a packed float with the constant {@code -0x1.050bb2p126F}.
     * <pre>
     * <font style='background-color: #FECBC4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FECBC4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FECBC4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FECBC4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FECBC4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FECBC4'>&nbsp;@&nbsp;</font><font style='background-color: #FECBC4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FECBC4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FECBC4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_RED = -0x1.050bb2p126F;
    static { NAMED.put("white red", -0x1.050bb2p126F); LIST.add(-0x1.050bb2p126F); }

    /**
     * This color constant "black brown" has RGBA8888 code {@code 453933FF}, L 0.23137255, A 0.5058824, B 0.5058824,
     * alpha 1.0, red 0.26915058, green 0.21961176, blue 0.19836481, hue 0.124999985, saturation 0.18247917, and chroma 0.016572814.
     * It can be represented as a packed float with the constant {@code -0x1.030276p126F}.
     * <pre>
     * <font style='background-color: #453933;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #453933; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #453933;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #453933'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #453933'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #453933'>&nbsp;@&nbsp;</font><font style='background-color: #453933; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #453933;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #453933; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_BROWN = -0x1.030276p126F;
    static { NAMED.put("black brown", -0x1.030276p126F); LIST.add(-0x1.030276p126F); }

    /**
     * This color constant "lead brown" has RGBA8888 code {@code 71615BFF}, L 0.39607844, A 0.5058824, B 0.5058824,
     * alpha 1.0, red 0.44191334, green 0.38235918, blue 0.35672885, hue 0.124999985, saturation 0.13155475, and chroma 0.016572814.
     * It can be represented as a packed float with the constant {@code -0x1.0302cap126F}.
     * <pre>
     * <font style='background-color: #71615B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #71615B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #71615B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #71615B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #71615B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #71615B'>&nbsp;@&nbsp;</font><font style='background-color: #71615B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #71615B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #71615B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_BROWN = -0x1.0302cap126F;
    static { NAMED.put("lead brown", -0x1.0302cap126F); LIST.add(-0x1.0302cap126F); }

    /**
     * This color constant "gray brown" has RGBA8888 code {@code 9B8A83FF}, L 0.5568628, A 0.5058824, B 0.5058824,
     * alpha 1.0, red 0.6085783, green 0.5416755, blue 0.5128241, hue 0.124999985, saturation 0.10475656, and chroma 0.016572814.
     * It can be represented as a packed float with the constant {@code -0x1.03031cp126F}.
     * <pre>
     * <font style='background-color: #9B8A83;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B8A83; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B8A83;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9B8A83'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9B8A83'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9B8A83'>&nbsp;@&nbsp;</font><font style='background-color: #9B8A83; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B8A83;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B8A83; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_BROWN = -0x1.03031cp126F;
    static { NAMED.put("gray brown", -0x1.03031cp126F); LIST.add(-0x1.03031cp126F); }

    /**
     * This color constant "silver brown" has RGBA8888 code {@code C5B2AAFF}, L 0.7176471, A 0.5058824, B 0.5058824,
     * alpha 1.0, red 0.7741827, green 0.7012402, blue 0.6697352, hue 0.124999985, saturation 0.17141983, and chroma 0.016572814.
     * It can be represented as a packed float with the constant {@code -0x1.03036ep126F}.
     * <pre>
     * <font style='background-color: #C5B2AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C5B2AA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C5B2AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C5B2AA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C5B2AA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C5B2AA'>&nbsp;@&nbsp;</font><font style='background-color: #C5B2AA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C5B2AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C5B2AA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_BROWN = -0x1.03036ep126F;
    static { NAMED.put("silver brown", -0x1.03036ep126F); LIST.add(-0x1.03036ep126F); }

    /**
     * This color constant "white brown" has RGBA8888 code {@code EFDBD2FF}, L 0.8784314, A 0.5058824, B 0.5058824,
     * alpha 1.0, red 0.9391045, green 0.8609696, blue 0.827177, hue 0.124999985, saturation 0.40406102, and chroma 0.016572814.
     * It can be represented as a packed float with the constant {@code -0x1.0303cp126F}.
     * <pre>
     * <font style='background-color: #EFDBD2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFDBD2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFDBD2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EFDBD2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EFDBD2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EFDBD2'>&nbsp;@&nbsp;</font><font style='background-color: #EFDBD2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFDBD2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFDBD2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_BROWN = -0x1.0303cp126F;
    static { NAMED.put("white brown", -0x1.0303cp126F); LIST.add(-0x1.0303cp126F); }

    /**
     * This color constant "black orange" has RGBA8888 code {@code 4E392CFF}, L 0.24313726, A 0.50980395, B 0.5137255,
     * alpha 1.0, red 0.30507147, green 0.22371896, blue 0.17531496, hue 0.14758363, saturation 0.35714287, and chroma 0.033602834.
     * It can be represented as a packed float with the constant {@code -0x1.07047cp126F}.
     * <pre>
     * <font style='background-color: #4E392C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E392C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E392C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4E392C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4E392C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4E392C'>&nbsp;@&nbsp;</font><font style='background-color: #4E392C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E392C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E392C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_ORANGE = -0x1.07047cp126F;
    static { NAMED.put("black orange", -0x1.07047cp126F); LIST.add(-0x1.07047cp126F); }

    /**
     * This color constant "lead orange" has RGBA8888 code {@code 7B6254FF}, L 0.40784314, A 0.50980395, B 0.5137255,
     * alpha 1.0, red 0.48275754, green 0.38582703, blue 0.32826298, hue 0.14758363, saturation 0.2631579, and chroma 0.033602834.
     * It can be represented as a packed float with the constant {@code -0x1.0704dp126F}.
     * <pre>
     * <font style='background-color: #7B6254;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B6254; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B6254;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7B6254'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7B6254'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7B6254'>&nbsp;@&nbsp;</font><font style='background-color: #7B6254; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B6254;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B6254; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_ORANGE = -0x1.0704dp126F;
    static { NAMED.put("lead orange", -0x1.0704dp126F); LIST.add(-0x1.0704dp126F); }

    /**
     * This color constant "gray orange" has RGBA8888 code {@code A78B7AFF}, L 0.5686275, A 0.50980395, B 0.5137255,
     * alpha 1.0, red 0.65310675, green 0.5445859, blue 0.48014116, hue 0.14758363, saturation 0.20833333, and chroma 0.033602834.
     * It can be represented as a packed float with the constant {@code -0x1.070522p126F}.
     * <pre>
     * <font style='background-color: #A78B7A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A78B7A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A78B7A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A78B7A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A78B7A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A78B7A'>&nbsp;@&nbsp;</font><font style='background-color: #A78B7A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A78B7A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A78B7A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_ORANGE = -0x1.070522p126F;
    static { NAMED.put("gray orange", -0x1.070522p126F); LIST.add(-0x1.070522p126F); }

    /**
     * This color constant "silver orange" has RGBA8888 code {@code D3B5A3FF}, L 0.7372549, A 0.50980395, B 0.5137255,
     * alpha 1.0, red 0.8299578, green 0.71142143, blue 0.64101124, hue 0.14758363, saturation 0.3125, and chroma 0.033602834.
     * It can be represented as a packed float with the constant {@code -0x1.070578p126F}.
     * <pre>
     * <font style='background-color: #D3B5A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3B5A3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3B5A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D3B5A3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D3B5A3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D3B5A3'>&nbsp;@&nbsp;</font><font style='background-color: #D3B5A3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3B5A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3B5A3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_ORANGE = -0x1.070578p126F;
    static { NAMED.put("silver orange", -0x1.070578p126F); LIST.add(-0x1.070578p126F); }

    /**
     * This color constant "white orange" has RGBA8888 code {@code FEDDCAFF}, L 0.8980392, A 0.50980395, B 0.5137255,
     * alpha 1.0, red 0.9974681, green 0.8707142, blue 0.79539996, hue 0.14758363, saturation 0.8333333, and chroma 0.033602834.
     * It can be represented as a packed float with the constant {@code -0x1.0705cap126F}.
     * <pre>
     * <font style='background-color: #FEDDCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FEDDCA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FEDDCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FEDDCA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FEDDCA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FEDDCA'>&nbsp;@&nbsp;</font><font style='background-color: #FEDDCA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FEDDCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FEDDCA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_ORANGE = -0x1.0705cap126F;
    static { NAMED.put("white orange", -0x1.0705cap126F); LIST.add(-0x1.0705cap126F); }

    /**
     * This color constant "black saffron" has RGBA8888 code {@code 4B422DFF}, L 0.2627451, A 0.49803922, B 0.5176471,
     * alpha 1.0, red 0.29301375, green 0.2605972, blue 0.1767649, hue 0.25, saturation 0.4347826, and chroma 0.0353726.
     * It can be represented as a packed float with the constant {@code -0x1.08fe86p126F}.
     * <pre>
     * <font style='background-color: #4B422D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B422D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B422D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4B422D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4B422D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4B422D'>&nbsp;@&nbsp;</font><font style='background-color: #4B422D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B422D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B422D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_SAFFRON = -0x1.08fe86p126F;
    static { NAMED.put("black saffron", -0x1.08fe86p126F); LIST.add(-0x1.08fe86p126F); }

    /**
     * This color constant "lead saffron" has RGBA8888 code {@code 776D54FF}, L 0.43137255, A 0.49803922, B 0.5176471,
     * alpha 1.0, red 0.46703282, green 0.4295443, blue 0.3318223, hue 0.25, saturation 0.32258064, and chroma 0.0353726.
     * It can be represented as a packed float with the constant {@code -0x1.08fedcp126F}.
     * <pre>
     * <font style='background-color: #776D54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #776D54; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #776D54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #776D54'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #776D54'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #776D54'>&nbsp;@&nbsp;</font><font style='background-color: #776D54; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #776D54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #776D54; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_SAFFRON = -0x1.08fedcp126F;
    static { NAMED.put("lead saffron", -0x1.08fedcp126F); LIST.add(-0x1.08fedcp126F); }

    /**
     * This color constant "gray saffron" has RGBA8888 code {@code A2987CFF}, L 0.59607846, A 0.49803922, B 0.5176471,
     * alpha 1.0, red 0.63577235, green 0.5944375, blue 0.4859952, hue 0.25, saturation 0.25641027, and chroma 0.0353726.
     * It can be represented as a packed float with the constant {@code -0x1.08ff3p126F}.
     * <pre>
     * <font style='background-color: #A2987C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2987C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2987C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A2987C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A2987C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A2987C'>&nbsp;@&nbsp;</font><font style='background-color: #A2987C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2987C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2987C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_SAFFRON = -0x1.08ff3p126F;
    static { NAMED.put("gray saffron", -0x1.08ff3p126F); LIST.add(-0x1.08ff3p126F); }

    /**
     * This color constant "silver saffron" has RGBA8888 code {@code CEC3A5FF}, L 0.76862746, A 0.49803922, B 0.5176471,
     * alpha 1.0, red 0.8118174, green 0.7671237, blue 0.6492909, hue 0.25, saturation 0.22222222, and chroma 0.0353726.
     * It can be represented as a packed float with the constant {@code -0x1.08ff88p126F}.
     * <pre>
     * <font style='background-color: #CEC3A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CEC3A5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CEC3A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CEC3A5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CEC3A5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CEC3A5'>&nbsp;@&nbsp;</font><font style='background-color: #CEC3A5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CEC3A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CEC3A5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_SAFFRON = -0x1.08ff88p126F;
    static { NAMED.put("silver saffron", -0x1.08ff88p126F); LIST.add(-0x1.08ff88p126F); }

    /**
     * This color constant "white saffron" has RGBA8888 code {@code FAEECEFF}, L 0.9372549, A 0.49803922, B 0.5176471,
     * alpha 1.0, red 0.9833895, green 0.93585235, blue 0.8100682, hue 0.25, saturation 0.5882353, and chroma 0.0353726.
     * It can be represented as a packed float with the constant {@code -0x1.08ffdep126F}.
     * <pre>
     * <font style='background-color: #FAEECE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FAEECE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FAEECE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FAEECE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FAEECE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FAEECE'>&nbsp;@&nbsp;</font><font style='background-color: #FAEECE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FAEECE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FAEECE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_SAFFRON = -0x1.08ffdep126F;
    static { NAMED.put("white saffron", -0x1.08ffdep126F); LIST.add(-0x1.08ffdep126F); }

    /**
     * This color constant "black yellow" has RGBA8888 code {@code 47472AFF}, L 0.27058825, A 0.49019608, B 0.52156866,
     * alpha 1.0, red 0.27907658, green 0.27918145, blue 0.16579042, hue 0.3012082, saturation 0.50596446, and chroma 0.0471994.
     * It can be represented as a packed float with the constant {@code -0x1.0afa8ap126F}.
     * <pre>
     * <font style='background-color: #47472A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #47472A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #47472A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #47472A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #47472A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #47472A'>&nbsp;@&nbsp;</font><font style='background-color: #47472A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #47472A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #47472A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_YELLOW = -0x1.0afa8ap126F;
    static { NAMED.put("black yellow", -0x1.0afa8ap126F); LIST.add(-0x1.0afa8ap126F); }

    /**
     * This color constant "lead yellow" has RGBA8888 code {@code 727351FF}, L 0.4392157, A 0.49019608, B 0.52156866,
     * alpha 1.0, red 0.44865212, green 0.45018947, blue 0.3193045, hue 0.3012082, saturation 0.37203267, and chroma 0.0471994.
     * It can be represented as a packed float with the constant {@code -0x1.0afaep126F}.
     * <pre>
     * <font style='background-color: #727351;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #727351; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #727351;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #727351'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #727351'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #727351'>&nbsp;@&nbsp;</font><font style='background-color: #727351; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #727351;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #727351; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_YELLOW = -0x1.0afaep126F;
    static { NAMED.put("lead yellow", -0x1.0afaep126F); LIST.add(-0x1.0afaep126F); }

    /**
     * This color constant "gray yellow" has RGBA8888 code {@code 9E9E79FF}, L 0.60784316, A 0.49019608, B 0.52156866,
     * alpha 1.0, red 0.61804783, green 0.6205877, blue 0.47558993, hue 0.3012082, saturation 0.3011693, and chroma 0.0471994.
     * It can be represented as a packed float with the constant {@code -0x1.0afb36p126F}.
     * <pre>
     * <font style='background-color: #9E9E79;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E9E79; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E9E79;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9E9E79'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9E9E79'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9E9E79'>&nbsp;@&nbsp;</font><font style='background-color: #9E9E79; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E9E79;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E9E79; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_YELLOW = -0x1.0afb36p126F;
    static { NAMED.put("gray yellow", -0x1.0afb36p126F); LIST.add(-0x1.0afb36p126F); }

    /**
     * This color constant "silver yellow" has RGBA8888 code {@code C9CAA2FF}, L 0.78039217, A 0.49019608, B 0.52156866,
     * alpha 1.0, red 0.79126966, green 0.794607, blue 0.6374565, hue 0.3012082, saturation 0.25814512, and chroma 0.0471994.
     * It can be represented as a packed float with the constant {@code -0x1.0afb8ep126F}.
     * <pre>
     * <font style='background-color: #C9CAA2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9CAA2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9CAA2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C9CAA2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C9CAA2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C9CAA2'>&nbsp;@&nbsp;</font><font style='background-color: #C9CAA2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9CAA2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9CAA2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_YELLOW = -0x1.0afb8ep126F;
    static { NAMED.put("silver yellow", -0x1.0afb8ep126F); LIST.add(-0x1.0afb8ep126F); }

    /**
     * This color constant "white yellow" has RGBA8888 code {@code F4F5CBFF}, L 0.9490196, A 0.49019608, B 0.52156866,
     * alpha 1.0, red 0.9604784, green 0.9644577, blue 0.7969692, hue 0.3012082, saturation 0.22998384, and chroma 0.0471994.
     * It can be represented as a packed float with the constant {@code -0x1.0afbe4p126F}.
     * <pre>
     * <font style='background-color: #F4F5CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F4F5CB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F4F5CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F4F5CB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F4F5CB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F4F5CB'>&nbsp;@&nbsp;</font><font style='background-color: #F4F5CB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F4F5CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F4F5CB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_YELLOW = -0x1.0afbe4p126F;
    static { NAMED.put("white yellow", -0x1.0afbe4p126F); LIST.add(-0x1.0afbe4p126F); }

    /**
     * This color constant "black lime" has RGBA8888 code {@code 40482EFF}, L 0.26666668, A 0.4862745, B 0.5176471,
     * alpha 1.0, red 0.253406, green 0.2815049, blue 0.18187416, hue 0.33601046, saturation 0.43192235, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.08f888p126F}.
     * <pre>
     * <font style='background-color: #40482E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #40482E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #40482E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #40482E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #40482E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #40482E'>&nbsp;@&nbsp;</font><font style='background-color: #40482E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #40482E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #40482E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_LIME = -0x1.08f888p126F;
    static { NAMED.put("black lime", -0x1.08f888p126F); LIST.add(-0x1.08f888p126F); }

    /**
     * This color constant "lead lime" has RGBA8888 code {@code 6B7456FF}, L 0.43529412, A 0.4862745, B 0.5176471,
     * alpha 1.0, red 0.41940156, green 0.45345387, blue 0.33738285, hue 0.33601046, saturation 0.3151866, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.08f8dep126F}.
     * <pre>
     * <font style='background-color: #6B7456;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6B7456; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6B7456;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6B7456'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6B7456'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6B7456'>&nbsp;@&nbsp;</font><font style='background-color: #6B7456; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6B7456;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6B7456; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_LIME = -0x1.08f8dep126F;
    static { NAMED.put("lead lime", -0x1.08f8dep126F); LIST.add(-0x1.08f8dep126F); }

    /**
     * This color constant "gray lime" has RGBA8888 code {@code 959E7DFF}, L 0.6, A 0.4862745, B 0.5176471,
     * alpha 1.0, red 0.5821639, green 0.62060696, blue 0.4918724, hue 0.33601046, saturation 0.25351962, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.08f932p126F}.
     * <pre>
     * <font style='background-color: #959E7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #959E7D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #959E7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #959E7D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #959E7D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #959E7D'>&nbsp;@&nbsp;</font><font style='background-color: #959E7D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #959E7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #959E7D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_LIME = -0x1.08f932p126F;
    static { NAMED.put("gray lime", -0x1.08f932p126F); LIST.add(-0x1.08f932p126F); }

    /**
     * This color constant "silver lime" has RGBA8888 code {@code BFCAA6FF}, L 0.76862746, A 0.4862745, B 0.5176471,
     * alpha 1.0, red 0.7491585, green 0.7912899, blue 0.6516979, hue 0.33601046, saturation 0.21596117, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.08f988p126F}.
     * <pre>
     * <font style='background-color: #BFCAA6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFCAA6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFCAA6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BFCAA6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BFCAA6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BFCAA6'>&nbsp;@&nbsp;</font><font style='background-color: #BFCAA6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFCAA6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFCAA6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_LIME = -0x1.08f988p126F;
    static { NAMED.put("silver lime", -0x1.08f988p126F); LIST.add(-0x1.08f988p126F); }

    /**
     * This color constant "white lime" has RGBA8888 code {@code EAF6D0FF}, L 0.9411765, A 0.4862745, B 0.5176471,
     * alpha 1.0, red 0.9202778, green 0.96563745, blue 0.81641996, hue 0.33601046, saturation 0.3151866, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.08f9ep126F}.
     * <pre>
     * <font style='background-color: #EAF6D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EAF6D0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EAF6D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EAF6D0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EAF6D0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EAF6D0'>&nbsp;@&nbsp;</font><font style='background-color: #EAF6D0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EAF6D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EAF6D0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_LIME = -0x1.08f9ep126F;
    static { NAMED.put("white lime", -0x1.08f9ep126F); LIST.add(-0x1.08f9ep126F); }

    /**
     * This color constant "black green" has RGBA8888 code {@code 324B2EFF}, L 0.2627451, A 0.4745098, B 0.5176471,
     * alpha 1.0, red 0.19882368, green 0.29300904, blue 0.17983998, hue 0.38942897, saturation 0.43390277, and chroma 0.061763234.
     * It can be represented as a packed float with the constant {@code -0x1.08f286p126F}.
     * <pre>
     * <font style='background-color: #324B2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #324B2E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #324B2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #324B2E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #324B2E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #324B2E'>&nbsp;@&nbsp;</font><font style='background-color: #324B2E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #324B2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #324B2E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_GREEN = -0x1.08f286p126F;
    static { NAMED.put("black green", -0x1.08f286p126F); LIST.add(-0x1.08f286p126F); }

    /**
     * This color constant "lead green" has RGBA8888 code {@code 5A7654FF}, L 0.42745098, A 0.4745098, B 0.5176471,
     * alpha 1.0, red 0.35455337, green 0.4641395, blue 0.33198094, hue 0.38942897, saturation 0.3187857, and chroma 0.061763234.
     * It can be represented as a packed float with the constant {@code -0x1.08f2dap126F}.
     * <pre>
     * <font style='background-color: #5A7654;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A7654; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A7654;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5A7654'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5A7654'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5A7654'>&nbsp;@&nbsp;</font><font style='background-color: #5A7654; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A7654;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A7654; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_GREEN = -0x1.08f2dap126F;
    static { NAMED.put("lead green", -0x1.08f2dap126F); LIST.add(-0x1.08f2dap126F); }

    /**
     * This color constant "gray green" has RGBA8888 code {@code 83A27CFF}, L 0.5921569, A 0.4745098, B 0.5176471,
     * alpha 1.0, red 0.51203674, green 0.6337113, blue 0.48664644, hue 0.38942897, saturation 0.26034167, and chroma 0.061763234.
     * It can be represented as a packed float with the constant {@code -0x1.08f32ep126F}.
     * <pre>
     * <font style='background-color: #83A27C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #83A27C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #83A27C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #83A27C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #83A27C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #83A27C'>&nbsp;@&nbsp;</font><font style='background-color: #83A27C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #83A27C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #83A27C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_GREEN = -0x1.08f32ep126F;
    static { NAMED.put("gray green", -0x1.08f32ep126F); LIST.add(-0x1.08f32ep126F); }

    /**
     * This color constant "silver green" has RGBA8888 code {@code ABCDA4FF}, L 0.7607843, A 0.4745098, B 0.5176471,
     * alpha 1.0, red 0.67442745, green 0.8064251, blue 0.64663345, hue 0.38942897, saturation 0.22000703, and chroma 0.061763234.
     * It can be represented as a packed float with the constant {@code -0x1.08f384p126F}.
     * <pre>
     * <font style='background-color: #ABCDA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABCDA4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABCDA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ABCDA4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ABCDA4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ABCDA4'>&nbsp;@&nbsp;</font><font style='background-color: #ABCDA4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABCDA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABCDA4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_GREEN = -0x1.08f384p126F;
    static { NAMED.put("silver green", -0x1.08f384p126F); LIST.add(-0x1.08f384p126F); }

    /**
     * This color constant "white green" has RGBA8888 code {@code D4F8CCFF}, L 0.9254902, A 0.4745098, B 0.5176471,
     * alpha 1.0, red 0.8338207, green 0.97455454, blue 0.8039913, hue 0.38942897, saturation 0.53863794, and chroma 0.061763234.
     * It can be represented as a packed float with the constant {@code -0x1.08f3d8p126F}.
     * <pre>
     * <font style='background-color: #D4F8CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4F8CC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4F8CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D4F8CC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D4F8CC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D4F8CC'>&nbsp;@&nbsp;</font><font style='background-color: #D4F8CC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4F8CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4F8CC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_GREEN = -0x1.08f3d8p126F;
    static { NAMED.put("white green", -0x1.08f3d8p126F); LIST.add(-0x1.08f3d8p126F); }

    /**
     * This color constant "black cyan" has RGBA8888 code {@code 304848FF}, L 0.25882354, A 0.48235294, B 0.49411765,
     * alpha 1.0, red 0.1859917, green 0.27984235, blue 0.27739543, hue 0.53898954, saturation 0.4340111, and chroma 0.03705794.
     * It can be represented as a packed float with the constant {@code -0x1.fcf684p125F}.
     * <pre>
     * <font style='background-color: #304848;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #304848; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #304848;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #304848'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #304848'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #304848'>&nbsp;@&nbsp;</font><font style='background-color: #304848; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #304848;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #304848; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_CYAN = -0x1.fcf684p125F;
    static { NAMED.put("black cyan", -0x1.fcf684p125F); LIST.add(-0x1.fcf684p125F); }

    /**
     * This color constant "lead cyan" has RGBA8888 code {@code 597474FF}, L 0.43137255, A 0.48235294, B 0.49411765,
     * alpha 1.0, red 0.3486069, green 0.45677713, blue 0.4533493, hue 0.53898954, saturation 0.3054152, and chroma 0.03705794.
     * It can be represented as a packed float with the constant {@code -0x1.fcf6dcp125F}.
     * <pre>
     * <font style='background-color: #597474;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #597474; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #597474;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #597474'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #597474'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #597474'>&nbsp;@&nbsp;</font><font style='background-color: #597474; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #597474;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #597474; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_CYAN = -0x1.fcf6dcp125F;
    static { NAMED.put("lead cyan", -0x1.fcf6dcp125F); LIST.add(-0x1.fcf6dcp125F); }

    /**
     * This color constant "gray cyan" has RGBA8888 code {@code 819F9EFF}, L 0.59607846, A 0.48235294, B 0.49411765,
     * alpha 1.0, red 0.5055311, green 0.62464255, blue 0.6205166, hue 0.53898954, saturation 0.24988519, and chroma 0.03705794.
     * It can be represented as a packed float with the constant {@code -0x1.fcf73p125F}.
     * <pre>
     * <font style='background-color: #819F9E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #819F9E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #819F9E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #819F9E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #819F9E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #819F9E'>&nbsp;@&nbsp;</font><font style='background-color: #819F9E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #819F9E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #819F9E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_CYAN = -0x1.fcf73p125F;
    static { NAMED.put("gray cyan", -0x1.fcf73p125F); LIST.add(-0x1.fcf73p125F); }

    /**
     * This color constant "silver cyan" has RGBA8888 code {@code AACBCAFF}, L 0.7647059, A 0.48235294, B 0.49411765,
     * alpha 1.0, red 0.6673758, green 0.795933, blue 0.7912193, hue 0.53898954, saturation 0.21700555, and chroma 0.03705794.
     * It can be represented as a packed float with the constant {@code -0x1.fcf786p125F}.
     * <pre>
     * <font style='background-color: #AACBCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AACBCA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AACBCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AACBCA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AACBCA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AACBCA'>&nbsp;@&nbsp;</font><font style='background-color: #AACBCA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AACBCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AACBCA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_CYAN = -0x1.fcf786p125F;
    static { NAMED.put("silver cyan", -0x1.fcf786p125F); LIST.add(-0x1.fcf786p125F); }

    /**
     * This color constant "white cyan" has RGBA8888 code {@code D4F7F6FF}, L 0.9372549, A 0.48235294, B 0.49411765,
     * alpha 1.0, red 0.83385736, green 0.970819, blue 0.96558756, hue 0.53898954, saturation 0.4340111, and chroma 0.03705794.
     * It can be represented as a packed float with the constant {@code -0x1.fcf7dep125F}.
     * <pre>
     * <font style='background-color: #D4F7F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4F7F6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4F7F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D4F7F6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D4F7F6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D4F7F6'>&nbsp;@&nbsp;</font><font style='background-color: #D4F7F6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4F7F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4F7F6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_CYAN = -0x1.fcf7dep125F;
    static { NAMED.put("white cyan", -0x1.fcf7dep125F); LIST.add(-0x1.fcf7dep125F); }

    /**
     * This color constant "black blue" has RGBA8888 code {@code 263554FF}, L 0.20784314, A 0.49411765, B 0.46666667,
     * alpha 1.0, red 0.14821701, green 0.20605834, blue 0.32662782, hue 0.7302083, saturation 0.3430748, and chroma 0.06743233.
     * It can be represented as a packed float with the constant {@code -0x1.eefc6ap125F}.
     * <pre>
     * <font style='background-color: #263554;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #263554; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #263554;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #263554'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #263554'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #263554'>&nbsp;@&nbsp;</font><font style='background-color: #263554; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #263554;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #263554; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_BLUE = -0x1.eefc6ap125F;
    static { NAMED.put("black blue", -0x1.eefc6ap125F); LIST.add(-0x1.eefc6ap125F); }

    /**
     * This color constant "lead blue" has RGBA8888 code {@code 4A5C80FF}, L 0.36078432, A 0.49411765, B 0.46666667,
     * alpha 1.0, red 0.28983045, green 0.36135045, blue 0.5037895, hue 0.7302083, saturation 0.24066441, and chroma 0.06743233.
     * It can be represented as a packed float with the constant {@code -0x1.eefcb8p125F}.
     * <pre>
     * <font style='background-color: #4A5C80;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A5C80; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A5C80;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4A5C80'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4A5C80'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4A5C80'>&nbsp;@&nbsp;</font><font style='background-color: #4A5C80; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A5C80;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A5C80; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_BLUE = -0x1.eefcb8p125F;
    static { NAMED.put("lead blue", -0x1.eefcb8p125F); LIST.add(-0x1.eefcb8p125F); }

    /**
     * This color constant "gray blue" has RGBA8888 code {@code 6F84ACFF}, L 0.5137255, A 0.49411765, B 0.46666667,
     * alpha 1.0, red 0.4342537, green 0.5157578, blue 0.67490804, hue 0.7302083, saturation 0.31616697, and chroma 0.06743233.
     * It can be represented as a packed float with the constant {@code -0x1.eefd06p125F}.
     * <pre>
     * <font style='background-color: #6F84AC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6F84AC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6F84AC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6F84AC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6F84AC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6F84AC'>&nbsp;@&nbsp;</font><font style='background-color: #6F84AC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6F84AC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6F84AC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_BLUE = -0x1.eefd06p125F;
    static { NAMED.put("gray blue", -0x1.eefd06p125F); LIST.add(-0x1.eefd06p125F); }

    /**
     * This color constant "silver blue" has RGBA8888 code {@code 93AAD6FF}, L 0.6627451, A 0.49411765, B 0.46666667,
     * alpha 1.0, red 0.57645285, green 0.665837, blue 0.83846164, hue 0.7302083, saturation 0.48862168, and chroma 0.06743233.
     * It can be represented as a packed float with the constant {@code -0x1.eefd52p125F}.
     * <pre>
     * <font style='background-color: #93AAD6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #93AAD6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #93AAD6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #93AAD6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #93AAD6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #93AAD6'>&nbsp;@&nbsp;</font><font style='background-color: #93AAD6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #93AAD6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #93AAD6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_BLUE = -0x1.eefd52p125F;
    static { NAMED.put("silver blue", -0x1.eefd52p125F); LIST.add(-0x1.eefd52p125F); }

    /**
     * This color constant "white blue" has RGBA8888 code {@code B8D0FFFF}, L 0.8117647, A 0.49411765, B 0.46666667,
     * alpha 1.0, red 0.7196001, green 0.81571156, blue 0.9999853, hue 0.7302083, saturation 0.89580643, and chroma 0.06743233.
     * It can be represented as a packed float with the constant {@code -0x1.eefd9ep125F}.
     * <pre>
     * <font style='background-color: #B8D0FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B8D0FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B8D0FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B8D0FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B8D0FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B8D0FF'>&nbsp;@&nbsp;</font><font style='background-color: #B8D0FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B8D0FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B8D0FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_BLUE = -0x1.eefd9ep125F;
    static { NAMED.put("white blue", -0x1.eefd9ep125F); LIST.add(-0x1.eefd9ep125F); }

    /**
     * This color constant "black violet" has RGBA8888 code {@code 3B344FFF}, L 0.22352941, A 0.50980395, B 0.4745098,
     * alpha 1.0, red 0.23314747, green 0.20346649, blue 0.31239134, hue 0.8237918, saturation 0.248452, and chroma 0.054407768.
     * It can be represented as a packed float with the constant {@code -0x1.f30472p125F}.
     * <pre>
     * <font style='background-color: #3B344F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B344F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B344F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B344F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B344F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B344F'>&nbsp;@&nbsp;</font><font style='background-color: #3B344F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B344F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B344F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_VIOLET = -0x1.f30472p125F;
    static { NAMED.put("black violet", -0x1.f30472p125F); LIST.add(-0x1.f30472p125F); }

    /**
     * This color constant "lead violet" has RGBA8888 code {@code 635A7BFF}, L 0.3764706, A 0.50980395, B 0.4745098,
     * alpha 1.0, red 0.3872646, green 0.35412616, blue 0.48262748, hue 0.8237918, saturation 0.17888543, and chroma 0.054407768.
     * It can be represented as a packed float with the constant {@code -0x1.f304cp125F}.
     * <pre>
     * <font style='background-color: #635A7B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #635A7B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #635A7B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #635A7B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #635A7B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #635A7B'>&nbsp;@&nbsp;</font><font style='background-color: #635A7B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #635A7B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #635A7B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_VIOLET = -0x1.f304cp125F;
    static { NAMED.put("lead violet", -0x1.f304cp125F); LIST.add(-0x1.f304cp125F); }

    /**
     * This color constant "gray violet" has RGBA8888 code {@code 8A81A6FF}, L 0.5294118, A 0.50980395, B 0.4745098,
     * alpha 1.0, red 0.54115736, green 0.5051671, blue 0.64865416, hue 0.8237918, saturation 0.23957871, and chroma 0.054407768.
     * It can be represented as a packed float with the constant {@code -0x1.f3050ep125F}.
     * <pre>
     * <font style='background-color: #8A81A6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A81A6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A81A6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8A81A6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8A81A6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8A81A6'>&nbsp;@&nbsp;</font><font style='background-color: #8A81A6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A81A6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A81A6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_VIOLET = -0x1.f3050ep125F;
    static { NAMED.put("gray violet", -0x1.f3050ep125F); LIST.add(-0x1.f3050ep125F); }

    /**
     * This color constant "silver violet" has RGBA8888 code {@code B1A8CFFF}, L 0.68235296, A 0.50980395, B 0.4745098,
     * alpha 1.0, red 0.69490945, green 0.6564764, blue 0.8123507, hue 0.8237918, saturation 0.38332593, and chroma 0.054407768.
     * It can be represented as a packed float with the constant {@code -0x1.f3055cp125F}.
     * <pre>
     * <font style='background-color: #B1A8CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1A8CF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1A8CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B1A8CF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B1A8CF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B1A8CF'>&nbsp;@&nbsp;</font><font style='background-color: #B1A8CF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1A8CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1A8CF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_VIOLET = -0x1.f3055cp125F;
    static { NAMED.put("silver violet", -0x1.f3055cp125F); LIST.add(-0x1.f3055cp125F); }

    /**
     * This color constant "white violet" has RGBA8888 code {@code D9CEF9FF}, L 0.8392157, A 0.50980395, B 0.4745098,
     * alpha 1.0, red 0.85250396, green 0.81186604, blue 0.97867763, hue 0.8237918, saturation 0.74535596, and chroma 0.054407768.
     * It can be represented as a packed float with the constant {@code -0x1.f305acp125F}.
     * <pre>
     * <font style='background-color: #D9CEF9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9CEF9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9CEF9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D9CEF9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D9CEF9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D9CEF9'>&nbsp;@&nbsp;</font><font style='background-color: #D9CEF9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9CEF9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9CEF9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_VIOLET = -0x1.f305acp125F;
    static { NAMED.put("white violet", -0x1.f305acp125F); LIST.add(-0x1.f305acp125F); }

    /**
     * This color constant "black purple" has RGBA8888 code {@code 473151FF}, L 0.23137255, A 0.52156866, B 0.4745098,
     * alpha 1.0, red 0.2791635, green 0.19256504, blue 0.31922778, hue 0.875, saturation 0.346338, and chroma 0.06652104.
     * It can be represented as a packed float with the constant {@code -0x1.f30a76p125F}.
     * <pre>
     * <font style='background-color: #473151;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #473151; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #473151;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #473151'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #473151'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #473151'>&nbsp;@&nbsp;</font><font style='background-color: #473151; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #473151;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #473151; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_PURPLE = -0x1.f30a76p125F;
    static { NAMED.put("black purple", -0x1.f30a76p125F); LIST.add(-0x1.f30a76p125F); }

    /**
     * This color constant "lead purple" has RGBA8888 code {@code 72587EFF}, L 0.3882353, A 0.52156866, B 0.4745098,
     * alpha 1.0, red 0.44572163, green 0.3446238, blue 0.49317116, hue 0.875, saturation 0.24595018, and chroma 0.06652104.
     * It can be represented as a packed float with the constant {@code -0x1.f30ac6p125F}.
     * <pre>
     * <font style='background-color: #72587E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #72587E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #72587E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #72587E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #72587E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #72587E'>&nbsp;@&nbsp;</font><font style='background-color: #72587E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #72587E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #72587E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_PURPLE = -0x1.f30ac6p125F;
    static { NAMED.put("lead purple", -0x1.f30ac6p125F); LIST.add(-0x1.f30ac6p125F); }

    /**
     * This color constant "gray purple" has RGBA8888 code {@code 9B7EA8FF}, L 0.5411765, A 0.52156866, B 0.4745098,
     * alpha 1.0, red 0.60581, green 0.4936867, blue 0.65875643, hue 0.875, saturation 0.23570225, and chroma 0.06652104.
     * It can be represented as a packed float with the constant {@code -0x1.f30b14p125F}.
     * <pre>
     * <font style='background-color: #9B7EA8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B7EA8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B7EA8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9B7EA8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9B7EA8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9B7EA8'>&nbsp;@&nbsp;</font><font style='background-color: #9B7EA8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B7EA8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B7EA8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_PURPLE = -0x1.f30b14p125F;
    static { NAMED.put("gray purple", -0x1.f30b14p125F); LIST.add(-0x1.f30b14p125F); }

    /**
     * This color constant "silver purple" has RGBA8888 code {@code C4A5D3FF}, L 0.69803923, A 0.52156866, B 0.4745098,
     * alpha 1.0, red 0.7687005, green 0.64714825, blue 0.82630026, hue 0.875, saturation 0.3856946, and chroma 0.06652104.
     * It can be represented as a packed float with the constant {@code -0x1.f30b64p125F}.
     * <pre>
     * <font style='background-color: #C4A5D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4A5D3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4A5D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C4A5D3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C4A5D3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C4A5D3'>&nbsp;@&nbsp;</font><font style='background-color: #C4A5D3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4A5D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4A5D3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_PURPLE = -0x1.f30b64p125F;
    static { NAMED.put("silver purple", -0x1.f30b64p125F); LIST.add(-0x1.f30b64p125F); }

    /**
     * This color constant "white purple" has RGBA8888 code {@code EDCCFCFF}, L 0.85490197, A 0.52156866, B 0.4745098,
     * alpha 1.0, red 0.930737, green 0.80102545, blue 0.9923358, hue 0.875, saturation 0.808122, and chroma 0.06652104.
     * It can be represented as a packed float with the constant {@code -0x1.f30bb4p125F}.
     * <pre>
     * <font style='background-color: #EDCCFC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDCCFC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDCCFC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EDCCFC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EDCCFC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EDCCFC'>&nbsp;@&nbsp;</font><font style='background-color: #EDCCFC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDCCFC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDCCFC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_PURPLE = -0x1.f30bb4p125F;
    static { NAMED.put("white purple", -0x1.f30bb4p125F); LIST.add(-0x1.f30bb4p125F); }

    /**
     * This color constant "black magenta" has RGBA8888 code {@code 503143FF}, L 0.23529412, A 0.5254902, B 0.49019608,
     * alpha 1.0, red 0.31340685, green 0.19204873, blue 0.26205772, hue 0.95570725, saturation 0.33091408, and chroma 0.054407768.
     * It can be represented as a packed float with the constant {@code -0x1.fb0c78p125F}.
     * <pre>
     * <font style='background-color: #503143;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #503143; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #503143;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #503143'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #503143'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #503143'>&nbsp;@&nbsp;</font><font style='background-color: #503143; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #503143;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #503143; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_MAGENTA = -0x1.fb0c78p125F;
    static { NAMED.put("black magenta", -0x1.fb0c78p125F); LIST.add(-0x1.fb0c78p125F); }

    /**
     * This color constant "lead magenta" has RGBA8888 code {@code 7C576CFF}, L 0.39215687, A 0.5254902, B 0.49019608,
     * alpha 1.0, red 0.4874185, green 0.34289178, blue 0.42402712, hue 0.95570725, saturation 0.23869213, and chroma 0.054407768.
     * It can be represented as a packed float with the constant {@code -0x1.fb0cc8p125F}.
     * <pre>
     * <font style='background-color: #7C576C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7C576C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7C576C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7C576C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7C576C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7C576C'>&nbsp;@&nbsp;</font><font style='background-color: #7C576C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7C576C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7C576C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_MAGENTA = -0x1.fb0cc8p125F;
    static { NAMED.put("lead magenta", -0x1.fb0cc8p125F); LIST.add(-0x1.fb0cc8p125F); }

    /**
     * This color constant "gray magenta" has RGBA8888 code {@code A77D94FF}, L 0.54509807, A 0.5254902, B 0.49019608,
     * alpha 1.0, red 0.65302527, green 0.4911085, blue 0.5807297, hue 0.95570725, saturation 0.19413626, and chroma 0.054407768.
     * It can be represented as a packed float with the constant {@code -0x1.fb0d16p125F}.
     * <pre>
     * <font style='background-color: #A77D94;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A77D94; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A77D94;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A77D94'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A77D94'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A77D94'>&nbsp;@&nbsp;</font><font style='background-color: #A77D94; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A77D94;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A77D94; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_MAGENTA = -0x1.fb0d16p125F;
    static { NAMED.put("gray magenta", -0x1.fb0d16p125F); LIST.add(-0x1.fb0d16p125F); }

    /**
     * This color constant "silver magenta" has RGBA8888 code {@code D2A4BDFF}, L 0.7019608, A 0.5254902, B 0.49019608,
     * alpha 1.0, red 0.8205687, green 0.64387375, blue 0.74075997, hue 0.95570725, saturation 0.33860976, and chroma 0.054407768.
     * It can be represented as a packed float with the constant {@code -0x1.fb0d66p125F}.
     * <pre>
     * <font style='background-color: #D2A4BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2A4BD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2A4BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D2A4BD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D2A4BD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D2A4BD'>&nbsp;@&nbsp;</font><font style='background-color: #D2A4BD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2A4BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2A4BD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_MAGENTA = -0x1.fb0d66p125F;
    static { NAMED.put("silver magenta", -0x1.fb0d66p125F); LIST.add(-0x1.fb0d66p125F); }

    /**
     * This color constant "white magenta" has RGBA8888 code {@code FBCBE5FF}, L 0.85882354, A 0.5254902, B 0.49019608,
     * alpha 1.0, red 0.98659205, green 0.7971611, blue 0.90033585, hue 0.95570725, saturation 0.7663274, and chroma 0.054407768.
     * It can be represented as a packed float with the constant {@code -0x1.fb0db6p125F}.
     * <pre>
     * <font style='background-color: #FBCBE5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FBCBE5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FBCBE5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FBCBE5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FBCBE5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FBCBE5'>&nbsp;@&nbsp;</font><font style='background-color: #FBCBE5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FBCBE5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FBCBE5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_MAGENTA = -0x1.fb0db6p125F;
    static { NAMED.put("white magenta", -0x1.fb0db6p125F); LIST.add(-0x1.fb0db6p125F); }

    /**
     * This color constant "drab red" has RGBA8888 code {@code 7E342AFF}, L 0.3019608, A 0.54509807, B 0.5254902,
     * alpha 1.0, red 0.49432826, green 0.20452935, blue 0.16648458, hue 0.08404566, saturation 0.56703854, and chroma 0.10320191.
     * It can be represented as a packed float with the constant {@code -0x1.0d169ap126F}.
     * <pre>
     * <font style='background-color: #7E342A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E342A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E342A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7E342A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7E342A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7E342A'>&nbsp;@&nbsp;</font><font style='background-color: #7E342A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E342A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E342A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_RED = -0x1.0d169ap126F;
    static { NAMED.put("drab red", -0x1.0d169ap126F); LIST.add(-0x1.0d169ap126F); }

    /**
     * This color constant "faded red" has RGBA8888 code {@code BA6356FF}, L 0.49411765, A 0.54509807, B 0.5254902,
     * alpha 1.0, red 0.72892606, green 0.39046988, blue 0.338731, hue 0.08404566, saturation 0.41469982, and chroma 0.10320191.
     * It can be represented as a packed float with the constant {@code -0x1.0d16fcp126F}.
     * <pre>
     * <font style='background-color: #BA6356;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA6356; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA6356;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BA6356'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BA6356'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BA6356'>&nbsp;@&nbsp;</font><font style='background-color: #BA6356; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA6356;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA6356; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_RED = -0x1.0d16fcp126F;
    static { NAMED.put("faded red", -0x1.0d16fcp126F); LIST.add(-0x1.0d16fcp126F); }

    /**
     * This color constant "pale red" has RGBA8888 code {@code F39282FF}, L 0.6862745, A 0.54509807, B 0.5254902,
     * alpha 1.0, red 0.95375055, green 0.57586604, blue 0.51451844, hue 0.08404566, saturation 0.79385394, and chroma 0.10320191.
     * It can be represented as a packed float with the constant {@code -0x1.0d175ep126F}.
     * <pre>
     * <font style='background-color: #F39282;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F39282; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F39282;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F39282'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F39282'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F39282'>&nbsp;@&nbsp;</font><font style='background-color: #F39282; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F39282;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F39282; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_RED = -0x1.0d175ep126F;
    static { NAMED.put("pale red", -0x1.0d175ep126F); LIST.add(-0x1.0d175ep126F); }

    /**
     * This color constant "drab brown" has RGBA8888 code {@code 6A4E42FF}, L 0.33333334, A 0.5137255, B 0.5137255,
     * alpha 1.0, red 0.41665968, green 0.3054729, blue 0.25804764, hue 0.124999985, saturation 0.29772916, and chroma 0.038669903.
     * It can be represented as a packed float with the constant {@code -0x1.0706aap126F}.
     * <pre>
     * <font style='background-color: #6A4E42;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A4E42; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A4E42;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6A4E42'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6A4E42'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6A4E42'>&nbsp;@&nbsp;</font><font style='background-color: #6A4E42; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A4E42;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A4E42; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BROWN = -0x1.0706aap126F;
    static { NAMED.put("drab brown", -0x1.0706aap126F); LIST.add(-0x1.0706aap126F); }

    /**
     * This color constant "faded brown" has RGBA8888 code {@code A18072FF}, L 0.53333336, A 0.5137255, B 0.5137255,
     * alpha 1.0, red 0.6325004, green 0.50188047, blue 0.44594237, hue 0.124999985, saturation 0.21757132, and chroma 0.038669903.
     * It can be represented as a packed float with the constant {@code -0x1.07071p126F}.
     * <pre>
     * <font style='background-color: #A18072;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A18072; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A18072;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A18072'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A18072'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A18072'>&nbsp;@&nbsp;</font><font style='background-color: #A18072; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A18072;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A18072; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_BROWN = -0x1.07071p126F;
    static { NAMED.put("faded brown", -0x1.07071p126F); LIST.add(-0x1.07071p126F); }

    /**
     * This color constant "pale brown" has RGBA8888 code {@code D9B3A3FF}, L 0.7372549, A 0.5137255, B 0.5137255,
     * alpha 1.0, red 0.84882355, green 0.70286214, blue 0.6402088, hue 0.124999985, saturation 0.37712362, and chroma 0.038669903.
     * It can be represented as a packed float with the constant {@code -0x1.070778p126F}.
     * <pre>
     * <font style='background-color: #D9B3A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9B3A3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9B3A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D9B3A3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D9B3A3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D9B3A3'>&nbsp;@&nbsp;</font><font style='background-color: #D9B3A3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9B3A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9B3A3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BROWN = -0x1.070778p126F;
    static { NAMED.put("pale brown", -0x1.070778p126F); LIST.add(-0x1.070778p126F); }

    /**
     * This color constant "drab orange" has RGBA8888 code {@code 795031FF}, L 0.3529412, A 0.5176471, B 0.5294118,
     * alpha 1.0, red 0.47600394, green 0.31257161, blue 0.1938882, hue 0.16109616, saturation 0.5717564, and chroma 0.068331465.
     * It can be represented as a packed float with the constant {@code -0x1.0f08b4p126F}.
     * <pre>
     * <font style='background-color: #795031;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #795031; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #795031;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #795031'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #795031'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #795031'>&nbsp;@&nbsp;</font><font style='background-color: #795031; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #795031;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #795031; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_ORANGE = -0x1.0f08b4p126F;
    static { NAMED.put("drab orange", -0x1.0f08b4p126F); LIST.add(-0x1.0f08b4p126F); }

    /**
     * This color constant "faded orange" has RGBA8888 code {@code B68562FF}, L 0.5647059, A 0.5176471, B 0.5294118,
     * alpha 1.0, red 0.7114374, green 0.52058107, blue 0.38437167, hue 0.16109616, saturation 0.4288173, and chroma 0.068331465.
     * It can be represented as a packed float with the constant {@code -0x1.0f092p126F}.
     * <pre>
     * <font style='background-color: #B68562;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B68562; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B68562;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B68562'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B68562'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B68562'>&nbsp;@&nbsp;</font><font style='background-color: #B68562; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B68562;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B68562; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_ORANGE = -0x1.0f092p126F;
    static { NAMED.put("faded orange", -0x1.0f092p126F); LIST.add(-0x1.0f092p126F); }

    /**
     * This color constant "pale orange" has RGBA8888 code {@code F1BA94FF}, L 0.78039217, A 0.5176471, B 0.5294118,
     * alpha 1.0, red 0.94566774, green 0.7329862, blue 0.58184415, hue 0.16109616, saturation 0.6738558, and chroma 0.068331465.
     * It can be represented as a packed float with the constant {@code -0x1.0f098ep126F}.
     * <pre>
     * <font style='background-color: #F1BA94;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F1BA94; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F1BA94;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F1BA94'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F1BA94'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F1BA94'>&nbsp;@&nbsp;</font><font style='background-color: #F1BA94; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F1BA94;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F1BA94; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_ORANGE = -0x1.0f098ep126F;
    static { NAMED.put("pale orange", -0x1.0f098ep126F); LIST.add(-0x1.0f098ep126F); }

    /**
     * This color constant "drab saffron" has RGBA8888 code {@code 736738FF}, L 0.40392157, A 0.49411765, B 0.53333336,
     * alpha 1.0, red 0.45131803, green 0.40477532, blue 0.21965194, hue 0.26761162, saturation 0.60369235, and chroma 0.06743233.
     * It can be represented as a packed float with the constant {@code -0x1.10fccep126F}.
     * <pre>
     * <font style='background-color: #736738;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #736738; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #736738;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #736738'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #736738'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #736738'>&nbsp;@&nbsp;</font><font style='background-color: #736738; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #736738;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #736738; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_SAFFRON = -0x1.10fccep126F;
    static { NAMED.put("drab saffron", -0x1.10fccep126F); LIST.add(-0x1.10fccep126F); }

    /**
     * This color constant "faded saffron" has RGBA8888 code {@code AFA26CFF}, L 0.6313726, A 0.49411765, B 0.53333336,
     * alpha 1.0, red 0.68588674, green 0.6339987, blue 0.42470905, hue 0.26761162, saturation 0.45276928, and chroma 0.06743233.
     * It can be represented as a packed float with the constant {@code -0x1.10fd42p126F}.
     * <pre>
     * <font style='background-color: #AFA26C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AFA26C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AFA26C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AFA26C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AFA26C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AFA26C'>&nbsp;@&nbsp;</font><font style='background-color: #AFA26C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AFA26C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AFA26C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_SAFFRON = -0x1.10fd42p126F;
    static { NAMED.put("faded saffron", -0x1.10fd42p126F); LIST.add(-0x1.10fd42p126F); }

    /**
     * This color constant "pale saffron" has RGBA8888 code {@code EBDCA2FF}, L 0.8627451, A 0.49411765, B 0.53333336,
     * alpha 1.0, red 0.92292506, green 0.86666673, blue 0.6364681, hue 0.26761162, saturation 0.4024616, and chroma 0.06743233.
     * It can be represented as a packed float with the constant {@code -0x1.10fdb8p126F}.
     * <pre>
     * <font style='background-color: #EBDCA2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBDCA2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBDCA2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EBDCA2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EBDCA2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EBDCA2'>&nbsp;@&nbsp;</font><font style='background-color: #EBDCA2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBDCA2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBDCA2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_SAFFRON = -0x1.10fdb8p126F;
    static { NAMED.put("pale saffron", -0x1.10fdb8p126F); LIST.add(-0x1.10fdb8p126F); }

    /**
     * This color constant "drab yellow" has RGBA8888 code {@code 727432FF}, L 0.43529412, A 0.48235294, B 0.5411765,
     * alpha 1.0, red 0.4489669, green 0.45410094, blue 0.19850416, hue 0.3055086, saturation 0.6885117, and chroma 0.08924734.
     * It can be represented as a packed float with the constant {@code -0x1.14f6dep126F}.
     * <pre>
     * <font style='background-color: #727432;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #727432; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #727432;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #727432'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #727432'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #727432'>&nbsp;@&nbsp;</font><font style='background-color: #727432; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #727432;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #727432; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_YELLOW = -0x1.14f6dep126F;
    static { NAMED.put("drab yellow", -0x1.14f6dep126F); LIST.add(-0x1.14f6dep126F); }

    /**
     * This color constant "faded yellow" has RGBA8888 code {@code AEB068FF}, L 0.6666667, A 0.48235294, B 0.5411765,
     * alpha 1.0, red 0.68108344, green 0.6904489, blue 0.40724537, hue 0.3055086, saturation 0.5320318, and chroma 0.08924734.
     * It can be represented as a packed float with the constant {@code -0x1.14f754p126F}.
     * <pre>
     * <font style='background-color: #AEB068;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AEB068; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AEB068;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AEB068'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AEB068'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AEB068'>&nbsp;@&nbsp;</font><font style='background-color: #AEB068; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AEB068;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AEB068; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_YELLOW = -0x1.14f754p126F;
    static { NAMED.put("faded yellow", -0x1.14f754p126F); LIST.add(-0x1.14f754p126F); }

    /**
     * This color constant "pale yellow" has RGBA8888 code {@code E9EC9DFF}, L 0.8980392, A 0.48235294, B 0.5411765,
     * alpha 1.0, red 0.91312355, green 0.9256315, blue 0.6168726, hue 0.3055086, saturation 0.43350738, and chroma 0.08924734.
     * It can be represented as a packed float with the constant {@code -0x1.14f7cap126F}.
     * <pre>
     * <font style='background-color: #E9EC9D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9EC9D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9EC9D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E9EC9D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E9EC9D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E9EC9D'>&nbsp;@&nbsp;</font><font style='background-color: #E9EC9D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9EC9D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9EC9D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOW = -0x1.14f7cap126F;
    static { NAMED.put("pale yellow", -0x1.14f7cap126F); LIST.add(-0x1.14f7cap126F); }

    /**
     * This color constant "drab lime" has RGBA8888 code {@code 5C753CFF}, L 0.41960785, A 0.47058824, B 0.53333336,
     * alpha 1.0, red 0.36211264, green 0.45909292, blue 0.23713145, hue 0.35520828, saturation 0.58470535, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.10f0d6p126F}.
     * <pre>
     * <font style='background-color: #5C753C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5C753C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5C753C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5C753C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5C753C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5C753C'>&nbsp;@&nbsp;</font><font style='background-color: #5C753C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5C753C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5C753C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_LIME = -0x1.10f0d6p126F;
    static { NAMED.put("drab lime", -0x1.10f0d6p126F); LIST.add(-0x1.10f0d6p126F); }

    /**
     * This color constant "faded lime" has RGBA8888 code {@code 94B171FF}, L 0.64705884, A 0.47058824, B 0.53333336,
     * alpha 1.0, red 0.5808023, green 0.69447, blue 0.44312543, hue 0.35520828, saturation 0.438529, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.10f14ap126F}.
     * <pre>
     * <font style='background-color: #94B171;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #94B171; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #94B171;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #94B171'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #94B171'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #94B171'>&nbsp;@&nbsp;</font><font style='background-color: #94B171; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #94B171;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #94B171; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_LIME = -0x1.10f14ap126F;
    static { NAMED.put("faded lime", -0x1.10f14ap126F); LIST.add(-0x1.10f14ap126F); }

    /**
     * This color constant "pale lime" has RGBA8888 code {@code CDEDA6FF}, L 0.8784314, A 0.47058824, B 0.53333336,
     * alpha 1.0, red 0.8051597, green 0.93207806, blue 0.65569514, hue 0.35520828, saturation 0.36196047, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.10f1cp126F}.
     * <pre>
     * <font style='background-color: #CDEDA6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CDEDA6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CDEDA6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CDEDA6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CDEDA6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CDEDA6'>&nbsp;@&nbsp;</font><font style='background-color: #CDEDA6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CDEDA6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CDEDA6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_LIME = -0x1.10f1cp126F;
    static { NAMED.put("pale lime", -0x1.10f1cp126F); LIST.add(-0x1.10f1cp126F); }

    /**
     * This color constant "drab green" has RGBA8888 code {@code 3F7934FF}, L 0.40784314, A 0.4509804, B 0.5372549,
     * alpha 1.0, red 0.24922472, green 0.4744254, blue 0.20445386, hue 0.38942897, saturation 0.6647021, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.12e6dp126F}.
     * <pre>
     * <font style='background-color: #3F7934;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F7934; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F7934;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3F7934'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3F7934'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3F7934'>&nbsp;@&nbsp;</font><font style='background-color: #3F7934; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F7934;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F7934; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_GREEN = -0x1.12e6dp126F;
    static { NAMED.put("drab green", -0x1.12e6dp126F); LIST.add(-0x1.12e6dp126F); }

    /**
     * This color constant "faded green" has RGBA8888 code {@code 73B466FF}, L 0.627451, A 0.4509804, B 0.5372549,
     * alpha 1.0, red 0.4540206, green 0.7073307, blue 0.40333712, hue 0.38942897, saturation 0.4958889, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.12e74p126F}.
     * <pre>
     * <font style='background-color: #73B466;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73B466; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73B466;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #73B466'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #73B466'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #73B466'>&nbsp;@&nbsp;</font><font style='background-color: #73B466; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73B466;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73B466; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_GREEN = -0x1.12e74p126F;
    static { NAMED.put("faded green", -0x1.12e74p126F); LIST.add(-0x1.12e74p126F); }

    /**
     * This color constant "pale green" has RGBA8888 code {@code A8EE99FF}, L 0.84313726, A 0.4509804, B 0.5372549,
     * alpha 1.0, red 0.65634286, green 0.9331946, blue 0.6002198, hue 0.38942897, saturation 0.47334847, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.12e7aep126F}.
     * <pre>
     * <font style='background-color: #A8EE99;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A8EE99; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A8EE99;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A8EE99'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A8EE99'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A8EE99'>&nbsp;@&nbsp;</font><font style='background-color: #A8EE99; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A8EE99;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A8EE99; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_GREEN = -0x1.12e7aep126F;
    static { NAMED.put("pale green", -0x1.12e7aep126F); LIST.add(-0x1.12e7aep126F); }

    /**
     * This color constant "drab cyan" has RGBA8888 code {@code 3A7675FF}, L 0.41568628, A 0.46666667, B 0.49019608,
     * alpha 1.0, red 0.22926496, green 0.46348253, blue 0.45924887, hue 0.53898954, saturation 0.63432395, and chroma 0.069218926.
     * It can be represented as a packed float with the constant {@code -0x1.faeed4p125F}.
     * <pre>
     * <font style='background-color: #3A7675;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3A7675; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3A7675;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3A7675'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3A7675'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3A7675'>&nbsp;@&nbsp;</font><font style='background-color: #3A7675; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3A7675;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3A7675; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_CYAN = -0x1.faeed4p125F;
    static { NAMED.put("drab cyan", -0x1.faeed4p125F); LIST.add(-0x1.faeed4p125F); }

    /**
     * This color constant "faded cyan" has RGBA8888 code {@code 70B2B0FF}, L 0.6431373, A 0.46666667, B 0.49019608,
     * alpha 1.0, red 0.44286394, green 0.69973856, blue 0.693465, hue 0.53898954, saturation 0.48507124, and chroma 0.069218926.
     * It can be represented as a packed float with the constant {@code -0x1.faef48p125F}.
     * <pre>
     * <font style='background-color: #70B2B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #70B2B0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #70B2B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #70B2B0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #70B2B0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #70B2B0'>&nbsp;@&nbsp;</font><font style='background-color: #70B2B0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #70B2B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #70B2B0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_CYAN = -0x1.faef48p125F;
    static { NAMED.put("faded cyan", -0x1.faef48p125F); LIST.add(-0x1.faef48p125F); }

    /**
     * This color constant "pale cyan" has RGBA8888 code {@code A6EDEBFF}, L 0.8666667, A 0.46666667, B 0.49019608,
     * alpha 1.0, red 0.6523154, green 0.93, blue 0.92220336, hue 0.53898954, saturation 0.41231054, and chroma 0.069218926.
     * It can be represented as a packed float with the constant {@code -0x1.faefbap125F}.
     * <pre>
     * <font style='background-color: #A6EDEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A6EDEB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A6EDEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A6EDEB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A6EDEB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A6EDEB'>&nbsp;@&nbsp;</font><font style='background-color: #A6EDEB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A6EDEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A6EDEB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_CYAN = -0x1.faefbap125F;
    static { NAMED.put("pale cyan", -0x1.faefbap125F); LIST.add(-0x1.faefbap125F); }

    /**
     * This color constant "drab blue" has RGBA8888 code {@code 1C3984FF}, L 0.24313726, A 0.49019608, B 0.43137255,
     * alpha 1.0, red 0.10761488, green 0.22156754, blue 0.51071954, hue 0.73136157, saturation 0.48906407, and chroma 0.1381068.
     * It can be represented as a packed float with the constant {@code -0x1.dcfa7cp125F}.
     * <pre>
     * <font style='background-color: #1C3984;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1C3984; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1C3984;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1C3984'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1C3984'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1C3984'>&nbsp;@&nbsp;</font><font style='background-color: #1C3984; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1C3984;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1C3984; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BLUE = -0x1.dcfa7cp125F;
    static { NAMED.put("drab blue", -0x1.dcfa7cp125F); LIST.add(-0x1.dcfa7cp125F); }

    /**
     * This color constant "faded blue" has RGBA8888 code {@code 4369BEFF}, L 0.42352942, A 0.49019608, B 0.43137255,
     * alpha 1.0, red 0.26412424, green 0.41123745, blue 0.7436437, hue 0.73136157, saturation 0.5434045, and chroma 0.1381068.
     * It can be represented as a packed float with the constant {@code -0x1.dcfad8p125F}.
     * <pre>
     * <font style='background-color: #4369BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4369BE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4369BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4369BE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4369BE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4369BE'>&nbsp;@&nbsp;</font><font style='background-color: #4369BE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4369BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4369BE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_BLUE = -0x1.dcfad8p125F;
    static { NAMED.put("faded blue", -0x1.dcfad8p125F); LIST.add(-0x1.dcfad8p125F); }

    /**
     * This color constant "pale blue" has RGBA8888 code {@code 6C97F5FF}, L 0.6, A 0.49019608, B 0.43137255,
     * alpha 1.0, red 0.42246157, green 0.5926319, blue 0.9592698, hue 0.73136157, saturation 0.85586214, and chroma 0.1381068.
     * It can be represented as a packed float with the constant {@code -0x1.dcfb32p125F}.
     * <pre>
     * <font style='background-color: #6C97F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C97F5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C97F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6C97F5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6C97F5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6C97F5'>&nbsp;@&nbsp;</font><font style='background-color: #6C97F5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C97F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C97F5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BLUE = -0x1.dcfb32p125F;
    static { NAMED.put("pale blue", -0x1.dcfb32p125F); LIST.add(-0x1.dcfb32p125F); }

    /**
     * This color constant "drab violet" has RGBA8888 code {@code 4E367AFF}, L 0.27058825, A 0.5254902, B 0.44705883,
     * alpha 1.0, red 0.3034001, green 0.20961247, blue 0.47452152, hue 0.8286132, saturation 0.49216077, and chroma 0.11705722.
     * It can be represented as a packed float with the constant {@code -0x1.e50c8ap125F}.
     * <pre>
     * <font style='background-color: #4E367A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E367A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E367A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4E367A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4E367A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4E367A'>&nbsp;@&nbsp;</font><font style='background-color: #4E367A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E367A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E367A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_VIOLET = -0x1.e50c8ap125F;
    static { NAMED.put("drab violet", -0x1.e50c8ap125F); LIST.add(-0x1.e50c8ap125F); }

    /**
     * This color constant "faded violet" has RGBA8888 code {@code 8067B6FF}, L 0.46666667, A 0.5254902, B 0.44705883,
     * alpha 1.0, red 0.50327444, green 0.4029169, blue 0.71160454, hue 0.8286132, saturation 0.44074097, and chroma 0.11705722.
     * It can be represented as a packed float with the constant {@code -0x1.e50ceep125F}.
     * <pre>
     * <font style='background-color: #8067B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8067B6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8067B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8067B6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8067B6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8067B6'>&nbsp;@&nbsp;</font><font style='background-color: #8067B6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8067B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8067B6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_VIOLET = -0x1.e50ceep125F;
    static { NAMED.put("faded violet", -0x1.e50ceep125F); LIST.add(-0x1.e50ceep125F); }

    /**
     * This color constant "pale violet" has RGBA8888 code {@code B196EEFF}, L 0.654902, A 0.5254902, B 0.44705883,
     * alpha 1.0, red 0.69447327, green 0.58768255, blue 0.9297404, hue 0.8286132, saturation 0.73824114, and chroma 0.11705722.
     * It can be represented as a packed float with the constant {@code -0x1.e50d4ep125F}.
     * <pre>
     * <font style='background-color: #B196EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B196EE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B196EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B196EE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B196EE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B196EE'>&nbsp;@&nbsp;</font><font style='background-color: #B196EE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B196EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B196EE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_VIOLET = -0x1.e50d4ep125F;
    static { NAMED.put("pale violet", -0x1.e50d4ep125F); LIST.add(-0x1.e50d4ep125F); }

    /**
     * This color constant "drab purple" has RGBA8888 code {@code 65377FFF}, L 0.30588236, A 0.5411765, B 0.4509804,
     * alpha 1.0, red 0.39607933, green 0.21595995, blue 0.49831074, hue 0.86808455, saturation 0.54262733, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.e7149cp125F}.
     * <pre>
     * <font style='background-color: #65377F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #65377F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #65377F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #65377F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #65377F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #65377F'>&nbsp;@&nbsp;</font><font style='background-color: #65377F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #65377F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #65377F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_PURPLE = -0x1.e7149cp125F;
    static { NAMED.put("drab purple", -0x1.e7149cp125F); LIST.add(-0x1.e7149cp125F); }

    /**
     * This color constant "faded purple" has RGBA8888 code {@code 9C68BAFF}, L 0.5019608, A 0.5411765, B 0.4509804,
     * alpha 1.0, red 0.60943234, green 0.40630776, blue 0.7297583, hue 0.86808455, saturation 0.42839, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.e715p125F}.
     * <pre>
     * <font style='background-color: #9C68BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C68BA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C68BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9C68BA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9C68BA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9C68BA'>&nbsp;@&nbsp;</font><font style='background-color: #9C68BA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C68BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C68BA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_PURPLE = -0x1.e715p125F;
    static { NAMED.put("faded purple", -0x1.e715p125F); LIST.add(-0x1.e715p125F); }

    /**
     * This color constant "pale purple" has RGBA8888 code {@code D299F5FF}, L 0.7019608, A 0.5411765, B 0.4509804,
     * alpha 1.0, red 0.8228742, green 0.599962, blue 0.95749384, hue 0.86808455, saturation 0.7940888, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.e71566p125F}.
     * <pre>
     * <font style='background-color: #D299F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D299F5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D299F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D299F5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D299F5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D299F5'>&nbsp;@&nbsp;</font><font style='background-color: #D299F5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D299F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D299F5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLE = -0x1.e71566p125F;
    static { NAMED.put("pale purple", -0x1.e71566p125F); LIST.add(-0x1.e71566p125F); }

    /**
     * This color constant "drab magenta" has RGBA8888 code {@code 7B3660FF}, L 0.31764707, A 0.5529412, B 0.48235294,
     * alpha 1.0, red 0.48154405, green 0.2114663, blue 0.3762193, hue 0.95570725, saturation 0.54944223, and chroma 0.11117382.
     * It can be represented as a packed float with the constant {@code -0x1.f71aa2p125F}.
     * <pre>
     * <font style='background-color: #7B3660;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B3660; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B3660;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7B3660'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7B3660'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7B3660'>&nbsp;@&nbsp;</font><font style='background-color: #7B3660; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B3660;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B3660; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_MAGENTA = -0x1.f71aa2p125F;
    static { NAMED.put("drab magenta", -0x1.f71aa2p125F); LIST.add(-0x1.f71aa2p125F); }

    /**
     * This color constant "faded magenta" has RGBA8888 code {@code B76696FF}, L 0.5176471, A 0.5529412, B 0.48235294,
     * alpha 1.0, red 0.7175722, green 0.40204203, blue 0.58706385, hue 0.95570725, saturation 0.39891014, and chroma 0.11117382.
     * It can be represented as a packed float with the constant {@code -0x1.f71b08p125F}.
     * <pre>
     * <font style='background-color: #B76696;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B76696; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B76696;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B76696'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B76696'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B76696'>&nbsp;@&nbsp;</font><font style='background-color: #B76696; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B76696;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B76696; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_MAGENTA = -0x1.f71b08p125F;
    static { NAMED.put("faded magenta", -0x1.f71b08p125F); LIST.add(-0x1.f71b08p125F); }

    /**
     * This color constant "pale magenta" has RGBA8888 code {@code F096CAFF}, L 0.7137255, A 0.5529412, B 0.48235294,
     * alpha 1.0, red 0.9408306, green 0.5893417, blue 0.79126495, hue 0.95570725, saturation 0.7102546, and chroma 0.11117382.
     * It can be represented as a packed float with the constant {@code -0x1.f71b6cp125F}.
     * <pre>
     * <font style='background-color: #F096CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F096CA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F096CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F096CA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F096CA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F096CA'>&nbsp;@&nbsp;</font><font style='background-color: #F096CA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F096CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F096CA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_MAGENTA = -0x1.f71b6cp125F;
    static { NAMED.put("pale magenta", -0x1.f71b6cp125F); LIST.add(-0x1.f71b6cp125F); }

    /**
     * This color constant "deep pure red" has RGBA8888 code {@code B1271FFF}, L 0.36862746, A 0.5764706, B 0.5411765,
     * alpha 1.0, red 0.6923979, green 0.15266256, blue 0.123273365, hue 0.08002999, saturation 0.8300154, and chroma 0.17302527.
     * It can be represented as a packed float with the constant {@code -0x1.1526bcp126F}.
     * <pre>
     * <font style='background-color: #B1271F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1271F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1271F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B1271F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B1271F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B1271F'>&nbsp;@&nbsp;</font><font style='background-color: #B1271F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1271F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1271F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_RED = -0x1.1526bcp126F;
    static { NAMED.put("deep pure red", -0x1.1526bcp126F); LIST.add(-0x1.1526bcp126F); }

    /**
     * This color constant "true pure red" has RGBA8888 code {@code D64539FF}, L 0.47843137, A 0.5764706, B 0.5411765,
     * alpha 1.0, red 0.83932465, green 0.2717945, blue 0.22387412, hue 0.08002999, saturation 0.69167954, and chroma 0.17302527.
     * It can be represented as a packed float with the constant {@code -0x1.1526f4p126F}.
     * <pre>
     * <font style='background-color: #D64539;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D64539; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D64539;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D64539'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D64539'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D64539'>&nbsp;@&nbsp;</font><font style='background-color: #D64539; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D64539;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D64539; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_RED = -0x1.1526f4p126F;
    static { NAMED.put("true pure red", -0x1.1526f4p126F); LIST.add(-0x1.1526f4p126F); }

    /**
     * This color constant "bright pure red" has RGBA8888 code {@code F96051FF}, L 0.58431375, A 0.5764706, B 0.5411765,
     * alpha 1.0, red 0.97627634, green 0.37786224, blue 0.317898, hue 0.08002999, saturation 0.89511466, and chroma 0.17302527.
     * It can be represented as a packed float with the constant {@code -0x1.15272ap126F}.
     * <pre>
     * <font style='background-color: #F96051;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F96051; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F96051;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F96051'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F96051'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F96051'>&nbsp;@&nbsp;</font><font style='background-color: #F96051; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F96051;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F96051; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_RED = -0x1.15272ap126F;
    static { NAMED.put("bright pure red", -0x1.15272ap126F); LIST.add(-0x1.15272ap126F); }

    /**
     * This color constant "deep brown red" has RGBA8888 code {@code A54A36FF}, L 0.40392157, A 0.54901963, B 0.53333336,
     * alpha 1.0, red 0.6447476, green 0.2891451, blue 0.21186183, hue 0.09637542, saturation 0.585607, and chroma 0.11809544.
     * It can be represented as a packed float with the constant {@code -0x1.1118cep126F}.
     * <pre>
     * <font style='background-color: #A54A36;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A54A36; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A54A36;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A54A36'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A54A36'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A54A36'>&nbsp;@&nbsp;</font><font style='background-color: #A54A36; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A54A36;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A54A36; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN_RED = -0x1.1118cep126F;
    static { NAMED.put("deep brown red", -0x1.1118cep126F); LIST.add(-0x1.1118cep126F); }

    /**
     * This color constant "true brown red" has RGBA8888 code {@code CC6952FF}, L 0.5294118, A 0.54901963, B 0.53333336,
     * alpha 1.0, red 0.79798585, green 0.41081247, blue 0.3235969, hue 0.09637542, saturation 0.51004475, and chroma 0.11809544.
     * It can be represented as a packed float with the constant {@code -0x1.11190ep126F}.
     * <pre>
     * <font style='background-color: #CC6952;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CC6952; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CC6952;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CC6952'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CC6952'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CC6952'>&nbsp;@&nbsp;</font><font style='background-color: #CC6952; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CC6952;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CC6952; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BROWN_RED = -0x1.11190ep126F;
    static { NAMED.put("true brown red", -0x1.11190ep126F); LIST.add(-0x1.11190ep126F); }

    /**
     * This color constant "bright brown red" has RGBA8888 code {@code F38970FF}, L 0.65882355, A 0.54901963, B 0.53333336,
     * alpha 1.0, red 0.9518427, green 0.535887, blue 0.4398769, hue 0.09637542, saturation 0.7905694, and chroma 0.11809544.
     * It can be represented as a packed float with the constant {@code -0x1.11195p126F}.
     * <pre>
     * <font style='background-color: #F38970;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F38970; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F38970;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F38970'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F38970'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F38970'>&nbsp;@&nbsp;</font><font style='background-color: #F38970; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F38970;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F38970; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN_RED = -0x1.11195p126F;
    static { NAMED.put("bright brown red", -0x1.11195p126F); LIST.add(-0x1.11195p126F); }

    /**
     * This color constant "deep red brown" has RGBA8888 code {@code 8E4F3DFF}, L 0.38039216, A 0.53333336, B 0.5254902,
     * alpha 1.0, red 0.55387187, green 0.30832806, blue 0.23803294, hue 0.105208285, saturation 0.4750731, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.0d10c2p126F}.
     * <pre>
     * <font style='background-color: #8E4F3D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8E4F3D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8E4F3D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8E4F3D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8E4F3D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8E4F3D'>&nbsp;@&nbsp;</font><font style='background-color: #8E4F3D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8E4F3D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8E4F3D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED_BROWN = -0x1.0d10c2p126F;
    static { NAMED.put("deep red brown", -0x1.0d10c2p126F); LIST.add(-0x1.0d10c2p126F); }

    /**
     * This color constant "true red brown" has RGBA8888 code {@code AB6753FF}, L 0.47843137, A 0.53333336, B 0.5254902,
     * alpha 1.0, red 0.66829306, green 0.40327412, blue 0.3264219, hue 0.105208285, saturation 0.40720552, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.0d10f4p126F}.
     * <pre>
     * <font style='background-color: #AB6753;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB6753; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB6753;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB6753'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB6753'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB6753'>&nbsp;@&nbsp;</font><font style='background-color: #AB6753; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB6753;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB6753; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_RED_BROWN = -0x1.0d10f4p126F;
    static { NAMED.put("true red brown", -0x1.0d10f4p126F); LIST.add(-0x1.0d10f4p126F); }

    /**
     * This color constant "bright red brown" has RGBA8888 code {@code C57D68FF}, L 0.5686275, A 0.53333336, B 0.5254902,
     * alpha 1.0, red 0.77169836, green 0.49075237, blue 0.40857667, hue 0.105208285, saturation 0.41460925, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.0d1122p126F}.
     * <pre>
     * <font style='background-color: #C57D68;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C57D68; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C57D68;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C57D68'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C57D68'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C57D68'>&nbsp;@&nbsp;</font><font style='background-color: #C57D68; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C57D68;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C57D68; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED_BROWN = -0x1.0d1122p126F;
    static { NAMED.put("bright red brown", -0x1.0d1122p126F); LIST.add(-0x1.0d1122p126F); }

    /**
     * This color constant "deep pure brown" has RGBA8888 code {@code 9A5C42FF}, L 0.42745098, A 0.5294118, B 0.5294118,
     * alpha 1.0, red 0.601709, green 0.3609777, blue 0.25878426, hue 0.124999985, saturation 0.50283146, and chroma 0.082864076.
     * It can be represented as a packed float with the constant {@code -0x1.0f0edap126F}.
     * <pre>
     * <font style='background-color: #9A5C42;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A5C42; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A5C42;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9A5C42'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9A5C42'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9A5C42'>&nbsp;@&nbsp;</font><font style='background-color: #9A5C42; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A5C42;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A5C42; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_BROWN = -0x1.0f0edap126F;
    static { NAMED.put("deep pure brown", -0x1.0f0edap126F); LIST.add(-0x1.0f0edap126F); }

    /**
     * This color constant "true pure brown" has RGBA8888 code {@code BB785CFF}, L 0.5411765, A 0.5294118, B 0.5294118,
     * alpha 1.0, red 0.7320919, green 0.47164887, blue 0.3610745, hue 0.124999985, saturation 0.4269324, and chroma 0.082864076.
     * It can be represented as a packed float with the constant {@code -0x1.0f0f14p126F}.
     * <pre>
     * <font style='background-color: #BB785C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB785C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB785C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BB785C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BB785C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BB785C'>&nbsp;@&nbsp;</font><font style='background-color: #BB785C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB785C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB785C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_BROWN = -0x1.0f0f14p126F;
    static { NAMED.put("true pure brown", -0x1.0f0f14p126F); LIST.add(-0x1.0f0f14p126F); }

    /**
     * This color constant "bright pure brown" has RGBA8888 code {@code DC9576FF}, L 0.65882355, A 0.5294118, B 0.5294118,
     * alpha 1.0, red 0.864672, green 0.58634156, blue 0.46801078, hue 0.124999985, saturation 0.538748, and chroma 0.082864076.
     * It can be represented as a packed float with the constant {@code -0x1.0f0f5p126F}.
     * <pre>
     * <font style='background-color: #DC9576;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DC9576; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DC9576;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DC9576'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DC9576'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DC9576'>&nbsp;@&nbsp;</font><font style='background-color: #DC9576; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DC9576;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DC9576; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_BROWN = -0x1.0f0f5p126F;
    static { NAMED.put("bright pure brown", -0x1.0f0f5p126F); LIST.add(-0x1.0f0f5p126F); }

    /**
     * This color constant "deep orange brown" has RGBA8888 code {@code 9F6649FF}, L 0.45882353, A 0.5254902, B 0.5294118,
     * alpha 1.0, red 0.6237312, green 0.40005356, blue 0.28749916, hue 0.13559465, saturation 0.48318845, and chroma 0.07753685.
     * It can be represented as a packed float with the constant {@code -0x1.0f0ceap126F}.
     * <pre>
     * <font style='background-color: #9F6649;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9F6649; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9F6649;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9F6649'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9F6649'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9F6649'>&nbsp;@&nbsp;</font><font style='background-color: #9F6649; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9F6649;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9F6649; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_BROWN = -0x1.0f0ceap126F;
    static { NAMED.put("deep orange brown", -0x1.0f0ceap126F); LIST.add(-0x1.0f0ceap126F); }

    /**
     * This color constant "true orange brown" has RGBA8888 code {@code C78869FF}, L 0.59607846, A 0.5254902, B 0.5294118,
     * alpha 1.0, red 0.7782899, green 0.5341006, blue 0.41152582, hue 0.13559465, saturation 0.40885177, and chroma 0.07753685.
     * It can be represented as a packed float with the constant {@code -0x1.0f0d3p126F}.
     * <pre>
     * <font style='background-color: #C78869;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C78869; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C78869;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C78869'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C78869'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C78869'>&nbsp;@&nbsp;</font><font style='background-color: #C78869; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C78869;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C78869; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_ORANGE_BROWN = -0x1.0f0d3p126F;
    static { NAMED.put("true orange brown", -0x1.0f0d3p126F); LIST.add(-0x1.0f0d3p126F); }

    /**
     * This color constant "bright orange brown" has RGBA8888 code {@code EFAC8AFF}, L 0.7411765, A 0.5254902, B 0.5294118,
     * alpha 1.0, red 0.9389814, green 0.6761105, blue 0.5442126, hue 0.13559465, saturation 0.6858159, and chroma 0.07753685.
     * It can be represented as a packed float with the constant {@code -0x1.0f0d7ap126F}.
     * <pre>
     * <font style='background-color: #EFAC8A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFAC8A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFAC8A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EFAC8A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EFAC8A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EFAC8A'>&nbsp;@&nbsp;</font><font style='background-color: #EFAC8A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFAC8A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFAC8A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE_BROWN = -0x1.0f0d7ap126F;
    static { NAMED.put("bright orange brown", -0x1.0f0d7ap126F); LIST.add(-0x1.0f0d7ap126F); }

    /**
     * This color constant "deep brown orange" has RGBA8888 code {@code 9A5E37FF}, L 0.43137255, A 0.5254902, B 0.5372549,
     * alpha 1.0, red 0.60521895, green 0.3698224, blue 0.21519971, hue 0.15279995, saturation 0.6424503, and chroma 0.08992863.
     * It can be represented as a packed float with the constant {@code -0x1.130cdcp126F}.
     * <pre>
     * <font style='background-color: #9A5E37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A5E37; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A5E37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9A5E37'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9A5E37'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9A5E37'>&nbsp;@&nbsp;</font><font style='background-color: #9A5E37; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A5E37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A5E37; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN_ORANGE = -0x1.130cdcp126F;
    static { NAMED.put("deep brown orange", -0x1.130cdcp126F); LIST.add(-0x1.130cdcp126F); }

    /**
     * This color constant "true brown orange" has RGBA8888 code {@code B6764CFF}, L 0.5254902, A 0.5254902, B 0.5372549,
     * alpha 1.0, red 0.71295905, green 0.46194115, blue 0.2993105, hue 0.15279995, saturation 0.5548434, and chroma 0.08992863.
     * It can be represented as a packed float with the constant {@code -0x1.130d0cp126F}.
     * <pre>
     * <font style='background-color: #B6764C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6764C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6764C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B6764C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B6764C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B6764C'>&nbsp;@&nbsp;</font><font style='background-color: #B6764C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6764C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6764C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BROWN_ORANGE = -0x1.130d0cp126F;
    static { NAMED.put("true brown orange", -0x1.130d0cp126F); LIST.add(-0x1.130d0cp126F); }

    /**
     * This color constant "bright brown orange" has RGBA8888 code {@code D28E63FF}, L 0.62352943, A 0.5254902, B 0.5372549,
     * alpha 1.0, red 0.8235547, green 0.5579684, blue 0.38708177, hue 0.15279995, saturation 0.49822676, and chroma 0.08992863.
     * It can be represented as a packed float with the constant {@code -0x1.130d3ep126F}.
     * <pre>
     * <font style='background-color: #D28E63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D28E63; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D28E63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D28E63'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D28E63'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D28E63'>&nbsp;@&nbsp;</font><font style='background-color: #D28E63; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D28E63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D28E63; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN_ORANGE = -0x1.130d3ep126F;
    static { NAMED.put("bright brown orange", -0x1.130d3ep126F); LIST.add(-0x1.130d3ep126F); }

    /**
     * This color constant "deep pure orange" has RGBA8888 code {@code AC6228FF}, L 0.4627451, A 0.5294118, B 0.54901963,
     * alpha 1.0, red 0.67506754, green 0.3853714, blue 0.15686154, hue 0.16220137, saturation 0.78278655, and chroma 0.11388578.
     * It can be represented as a packed float with the constant {@code -0x1.190eecp126F}.
     * <pre>
     * <font style='background-color: #AC6228;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC6228; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC6228;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AC6228'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AC6228'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AC6228'>&nbsp;@&nbsp;</font><font style='background-color: #AC6228; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC6228;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC6228; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_ORANGE = -0x1.190eecp126F;
    static { NAMED.put("deep pure orange", -0x1.190eecp126F); LIST.add(-0x1.190eecp126F); }

    /**
     * This color constant "true pure orange" has RGBA8888 code {@code D28246FF}, L 0.5921569, A 0.5294118, B 0.54901963,
     * alpha 1.0, red 0.82553434, green 0.51237667, blue 0.27718845, hue 0.16220137, saturation 0.66366684, and chroma 0.11388578.
     * It can be represented as a packed float with the constant {@code -0x1.190f2ep126F}.
     * <pre>
     * <font style='background-color: #D28246;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D28246; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D28246;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D28246'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D28246'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D28246'>&nbsp;@&nbsp;</font><font style='background-color: #D28246; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D28246;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D28246; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_ORANGE = -0x1.190f2ep126F;
    static { NAMED.put("true pure orange", -0x1.190f2ep126F); LIST.add(-0x1.190f2ep126F); }

    /**
     * This color constant "bright pure orange" has RGBA8888 code {@code F6A162FF}, L 0.7137255, A 0.5294118, B 0.54901963,
     * alpha 1.0, red 0.9642525, green 0.6316397, blue 0.38618803, hue 0.16220137, saturation 0.8033862, and chroma 0.11388578.
     * It can be represented as a packed float with the constant {@code -0x1.190f6cp126F}.
     * <pre>
     * <font style='background-color: #F6A162;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6A162; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6A162;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F6A162'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F6A162'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F6A162'>&nbsp;@&nbsp;</font><font style='background-color: #F6A162; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6A162;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6A162; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_ORANGE = -0x1.190f6cp126F;
    static { NAMED.put("bright pure orange", -0x1.190f6cp126F); LIST.add(-0x1.190f6cp126F); }

    /**
     * This color constant "deep saffron orange" has RGBA8888 code {@code AF7A37FF}, L 0.52156866, A 0.5137255, B 0.54901963,
     * alpha 1.0, red 0.6841721, green 0.47819886, blue 0.21510981, hue 0.20249242, saturation 0.73521465, and chroma 0.10141215.
     * It can be represented as a packed float with the constant {@code -0x1.19070ap126F}.
     * <pre>
     * <font style='background-color: #AF7A37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF7A37; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF7A37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AF7A37'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AF7A37'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AF7A37'>&nbsp;@&nbsp;</font><font style='background-color: #AF7A37; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF7A37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF7A37; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_SAFFRON_ORANGE = -0x1.19070ap126F;
    static { NAMED.put("deep saffron orange", -0x1.19070ap126F); LIST.add(-0x1.19070ap126F); }

    /**
     * This color constant "true saffron orange" has RGBA8888 code {@code D9A059FF}, L 0.6745098, A 0.5137255, B 0.54901963,
     * alpha 1.0, red 0.8529973, green 0.62990564, blue 0.3537558, hue 0.20249242, saturation 0.6182487, and chroma 0.10141215.
     * It can be represented as a packed float with the constant {@code -0x1.190758p126F}.
     * <pre>
     * <font style='background-color: #D9A059;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9A059; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9A059;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D9A059'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D9A059'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D9A059'>&nbsp;@&nbsp;</font><font style='background-color: #D9A059; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9A059;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9A059; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_SAFFRON_ORANGE = -0x1.190758p126F;
    static { NAMED.put("true saffron orange", -0x1.190758p126F); LIST.add(-0x1.190758p126F); }

    /**
     * This color constant "bright saffron orange" has RGBA8888 code {@code FFC77CFF}, L 0.8235294, A 0.50980395, B 0.54901963,
     * alpha 1.0, red 0.9965734, green 0.7869126, blue 0.48793948, hue 0.21390384, saturation 0.8894443, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.1905a4p126F}.
     * <pre>
     * <font style='background-color: #FFC77C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFC77C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFC77C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFC77C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFC77C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFC77C'>&nbsp;@&nbsp;</font><font style='background-color: #FFC77C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFC77C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFC77C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_SAFFRON_ORANGE = -0x1.1905a4p126F;
    static { NAMED.put("bright saffron orange", -0x1.1905a4p126F); LIST.add(-0x1.1905a4p126F); }

    /**
     * This color constant "deep orange saffron" has RGBA8888 code {@code A57B2CFF}, L 0.50980395, A 0.5058824, B 0.5529412,
     * alpha 1.0, red 0.64752823, green 0.4808694, blue 0.17197359, hue 0.22741638, saturation 0.80812204, and chroma 0.10611779.
     * It can be represented as a packed float with the constant {@code -0x1.1b0304p126F}.
     * <pre>
     * <font style='background-color: #A57B2C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A57B2C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A57B2C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A57B2C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A57B2C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A57B2C'>&nbsp;@&nbsp;</font><font style='background-color: #A57B2C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A57B2C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A57B2C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_SAFFRON = -0x1.1b0304p126F;
    static { NAMED.put("deep orange saffron", -0x1.1b0304p126F); LIST.add(-0x1.1b0304p126F); }

    /**
     * This color constant "true orange saffron" has RGBA8888 code {@code C39645FF}, L 0.6156863, A 0.5058824, B 0.5529412,
     * alpha 1.0, red 0.76279324, green 0.58673143, blue 0.27205297, hue 0.22741638, saturation 0.70710677, and chroma 0.10611779.
     * It can be represented as a packed float with the constant {@code -0x1.1b033ap126F}.
     * <pre>
     * <font style='background-color: #C39645;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C39645; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C39645;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C39645'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C39645'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C39645'>&nbsp;@&nbsp;</font><font style='background-color: #C39645; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C39645;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C39645; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_ORANGE_SAFFRON = -0x1.1b033ap126F;
    static { NAMED.put("true orange saffron", -0x1.1b033ap126F); LIST.add(-0x1.1b033ap126F); }

    /**
     * This color constant "bright orange saffron" has RGBA8888 code {@code E1B25FFF}, L 0.7294118, A 0.5058824, B 0.5529412,
     * alpha 1.0, red 0.8854511, green 0.70034134, blue 0.37500623, hue 0.22741638, saturation 0.64282435, and chroma 0.10611779.
     * It can be represented as a packed float with the constant {@code -0x1.1b0374p126F}.
     * <pre>
     * <font style='background-color: #E1B25F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1B25F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1B25F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E1B25F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E1B25F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E1B25F'>&nbsp;@&nbsp;</font><font style='background-color: #E1B25F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1B25F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1B25F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE_SAFFRON = -0x1.1b0374p126F;
    static { NAMED.put("bright orange saffron", -0x1.1b0374p126F); LIST.add(-0x1.1b0374p126F); }

    /**
     * This color constant "deep pure saffron" has RGBA8888 code {@code AD952CFF}, L 0.58431375, A 0.49019608, B 0.56078434,
     * alpha 1.0, red 0.67688066, green 0.5836191, blue 0.17333692, hue 0.26979172, saturation 0.8268982, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.1efb2ap126F}.
     * <pre>
     * <font style='background-color: #AD952C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD952C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD952C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AD952C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AD952C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AD952C'>&nbsp;@&nbsp;</font><font style='background-color: #AD952C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD952C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD952C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_SAFFRON = -0x1.1efb2ap126F;
    static { NAMED.put("deep pure saffron", -0x1.1efb2ap126F); LIST.add(-0x1.1efb2ap126F); }

    /**
     * This color constant "true pure saffron" has RGBA8888 code {@code CFB74CFF}, L 0.7176471, A 0.49019608, B 0.56078434,
     * alpha 1.0, red 0.81619626, green 0.7188877, blue 0.30332595, hue 0.26979172, saturation 0.7329325, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.1efb6ep126F}.
     * <pre>
     * <font style='background-color: #CFB74C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CFB74C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CFB74C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CFB74C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CFB74C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CFB74C'>&nbsp;@&nbsp;</font><font style='background-color: #CFB74C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CFB74C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CFB74C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_SAFFRON = -0x1.1efb6ep126F;
    static { NAMED.put("true pure saffron", -0x1.1efb6ep126F); LIST.add(-0x1.1efb6ep126F); }

    /**
     * This color constant "bright pure saffron" has RGBA8888 code {@code F3D96BFF}, L 0.8509804, A 0.49019608, B 0.56078434,
     * alpha 1.0, red 0.95481056, green 0.8538558, blue 0.42501098, hue 0.26979172, saturation 0.6581435, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.1efbb2p126F}.
     * <pre>
     * <font style='background-color: #F3D96B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3D96B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3D96B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F3D96B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F3D96B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F3D96B'>&nbsp;@&nbsp;</font><font style='background-color: #F3D96B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3D96B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3D96B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_SAFFRON = -0x1.1efbb2p126F;
    static { NAMED.put("bright pure saffron", -0x1.1efbb2p126F); LIST.add(-0x1.1efbb2p126F); }

    /**
     * This color constant "deep yellow saffron" has RGBA8888 code {@code B7A53AFF}, L 0.6392157, A 0.4862745, B 0.56078434,
     * alpha 1.0, red 0.7161234, green 0.6472629, blue 0.2295673, hue 0.27949905, saturation 0.7940888, and chroma 0.124142565.
     * It can be represented as a packed float with the constant {@code -0x1.1ef946p126F}.
     * <pre>
     * <font style='background-color: #B7A53A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B7A53A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B7A53A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B7A53A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B7A53A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B7A53A'>&nbsp;@&nbsp;</font><font style='background-color: #B7A53A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B7A53A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B7A53A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_SAFFRON = -0x1.1ef946p126F;
    static { NAMED.put("deep yellow saffron", -0x1.1ef946p126F); LIST.add(-0x1.1ef946p126F); }

    /**
     * This color constant "true yellow saffron" has RGBA8888 code {@code E1CF61FF}, L 0.8039216, A 0.4862745, B 0.56078434,
     * alpha 1.0, red 0.8860544, green 0.8147104, blue 0.38309345, hue 0.27949905, saturation 0.67828417, and chroma 0.124142565.
     * It can be represented as a packed float with the constant {@code -0x1.1ef99ap126F}.
     * <pre>
     * <font style='background-color: #E1CF61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1CF61; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1CF61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E1CF61'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E1CF61'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E1CF61'>&nbsp;@&nbsp;</font><font style='background-color: #E1CF61; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1CF61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1CF61; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_YELLOW_SAFFRON = -0x1.1ef99ap126F;
    static { NAMED.put("true yellow saffron", -0x1.1ef99ap126F); LIST.add(-0x1.1ef99ap126F); }

    /**
     * This color constant "bright yellow saffron" has RGBA8888 code {@code FFF8B2FF}, L 0.9607843, A 0.49019608, B 0.5372549,
     * alpha 1.0, red 1.0, green 0.9729757, blue 0.70043296, hue 0.28141648, saturation 0.92709446, and chroma 0.07674564.
     * It can be represented as a packed float with the constant {@code -0x1.12fbeap126F}.
     * <pre>
     * <font style='background-color: #FFF8B2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFF8B2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFF8B2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFF8B2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFF8B2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFF8B2'>&nbsp;@&nbsp;</font><font style='background-color: #FFF8B2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFF8B2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFF8B2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_YELLOW_SAFFRON = -0x1.12fbeap126F;
    static { NAMED.put("bright yellow saffron", -0x1.12fbeap126F); LIST.add(-0x1.12fbeap126F); }

    /**
     * This color constant "deep saffron yellow" has RGBA8888 code {@code A79C2FFF}, L 0.59607846, A 0.48235294, B 0.56078434,
     * alpha 1.0, red 0.6533831, green 0.6109142, blue 0.18693908, hue 0.28898957, saturation 0.8246211, and chroma 0.12609385.
     * It can be represented as a packed float with the constant {@code -0x1.1ef73p126F}.
     * <pre>
     * <font style='background-color: #A79C2F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A79C2F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A79C2F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A79C2F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A79C2F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A79C2F'>&nbsp;@&nbsp;</font><font style='background-color: #A79C2F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A79C2F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A79C2F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_SAFFRON_YELLOW = -0x1.1ef73p126F;
    static { NAMED.put("deep saffron yellow", -0x1.1ef73p126F); LIST.add(-0x1.1ef73p126F); }

    /**
     * This color constant "true saffron yellow" has RGBA8888 code {@code C4B94BFF}, L 0.70980394, A 0.48235294, B 0.56078434,
     * alpha 1.0, red 0.7697234, green 0.72711426, blue 0.29733306, hue 0.28898957, saturation 0.7329965, and chroma 0.12609385.
     * It can be represented as a packed float with the constant {@code -0x1.1ef76ap126F}.
     * <pre>
     * <font style='background-color: #C4B94B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4B94B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4B94B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C4B94B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C4B94B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C4B94B'>&nbsp;@&nbsp;</font><font style='background-color: #C4B94B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4B94B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4B94B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_SAFFRON_YELLOW = -0x1.1ef76ap126F;
    static { NAMED.put("true saffron yellow", -0x1.1ef76ap126F); LIST.add(-0x1.1ef76ap126F); }

    /**
     * This color constant "bright saffron yellow" has RGBA8888 code {@code DFD464FF}, L 0.8117647, A 0.48235294, B 0.56078434,
     * alpha 1.0, red 0.8738116, green 0.8310384, blue 0.3909008, hue 0.28898957, saturation 0.6731601, and chroma 0.12609385.
     * It can be represented as a packed float with the constant {@code -0x1.1ef79ep126F}.
     * <pre>
     * <font style='background-color: #DFD464;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DFD464; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DFD464;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DFD464'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DFD464'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DFD464'>&nbsp;@&nbsp;</font><font style='background-color: #DFD464; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DFD464;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DFD464; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_SAFFRON_YELLOW = -0x1.1ef79ep126F;
    static { NAMED.put("bright saffron yellow", -0x1.1ef79ep126F); LIST.add(-0x1.1ef79ep126F); }

    /**
     * This color constant "deep pure yellow" has RGBA8888 code {@code A9AD26FF}, L 0.6431373, A 0.47058824, B 0.5686275,
     * alpha 1.0, red 0.66190124, green 0.67817074, blue 0.14869907, hue 0.30902916, saturation 0.87787306, and chroma 0.14874557.
     * It can be represented as a packed float with the constant {@code -0x1.22f148p126F}.
     * <pre>
     * <font style='background-color: #A9AD26;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A9AD26; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A9AD26;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A9AD26'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A9AD26'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A9AD26'>&nbsp;@&nbsp;</font><font style='background-color: #A9AD26; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A9AD26;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A9AD26; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_YELLOW = -0x1.22f148p126F;
    static { NAMED.put("deep pure yellow", -0x1.22f148p126F); LIST.add(-0x1.22f148p126F); }

    /**
     * This color constant "true pure yellow" has RGBA8888 code {@code CCD14BFF}, L 0.78039217, A 0.47058824, B 0.5686275,
     * alpha 1.0, red 0.79902977, green 0.8198606, blue 0.29348892, hue 0.30902916, saturation 0.7725283, and chroma 0.14874557.
     * It can be represented as a packed float with the constant {@code -0x1.22f18ep126F}.
     * <pre>
     * <font style='background-color: #CCD14B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CCD14B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CCD14B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CCD14B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CCD14B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CCD14B'>&nbsp;@&nbsp;</font><font style='background-color: #CCD14B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CCD14B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CCD14B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_YELLOW = -0x1.22f18ep126F;
    static { NAMED.put("true pure yellow", -0x1.22f18ep126F); LIST.add(-0x1.22f18ep126F); }

    /**
     * This color constant "bright pure yellow" has RGBA8888 code {@code EFF66BFF}, L 0.92156863, A 0.47058824, B 0.5686275,
     * alpha 1.0, red 0.9401319, green 0.9650283, blue 0.42535707, hue 0.30902916, saturation 0.70229846, and chroma 0.14874557.
     * It can be represented as a packed float with the constant {@code -0x1.22f1d6p126F}.
     * <pre>
     * <font style='background-color: #EFF66B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFF66B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFF66B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EFF66B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EFF66B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EFF66B'>&nbsp;@&nbsp;</font><font style='background-color: #EFF66B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFF66B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFF66B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_YELLOW = -0x1.22f1d6p126F;
    static { NAMED.put("bright pure yellow", -0x1.22f1d6p126F); LIST.add(-0x1.22f1d6p126F); }

    /**
     * This color constant "deep lime yellow" has RGBA8888 code {@code AAB442FF}, L 0.6666667, A 0.47058824, B 0.56078434,
     * alpha 1.0, red 0.6665909, green 0.7059486, blue 0.25859782, hue 0.31563717, saturation 0.77618885, and chroma 0.1345248.
     * It can be represented as a packed float with the constant {@code -0x1.1ef154p126F}.
     * <pre>
     * <font style='background-color: #AAB442;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AAB442; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AAB442;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AAB442'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AAB442'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AAB442'>&nbsp;@&nbsp;</font><font style='background-color: #AAB442; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AAB442;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AAB442; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_LIME_YELLOW = -0x1.1ef154p126F;
    static { NAMED.put("deep lime yellow", -0x1.1ef154p126F); LIST.add(-0x1.1ef154p126F); }

    /**
     * This color constant "true lime yellow" has RGBA8888 code {@code D4E069FF}, L 0.8352941, A 0.47058824, B 0.56078434,
     * alpha 1.0, red 0.8339071, green 0.87963533, blue 0.41431308, hue 0.31563717, saturation 0.67170185, and chroma 0.1345248.
     * It can be represented as a packed float with the constant {@code -0x1.1ef1aap126F}.
     * <pre>
     * <font style='background-color: #D4E069;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4E069; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4E069;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D4E069'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D4E069'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D4E069'>&nbsp;@&nbsp;</font><font style='background-color: #D4E069; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4E069;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4E069; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_LIME_YELLOW = -0x1.1ef1aap126F;
    static { NAMED.put("true lime yellow", -0x1.1ef1aap126F); LIST.add(-0x1.1ef1aap126F); }

    /**
     * This color constant "bright lime yellow" has RGBA8888 code {@code FFFFEBFF}, L 0.99607843, A 0.49411765, B 0.50980395,
     * alpha 1.0, red 1.0, green 1.0, blue 0.91952664, hue 0.3012082, saturation 0.7905694, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.04fdfcp126F}.
     * <pre>
     * <font style='background-color: #FFFFEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFEB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFFEB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFFEB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFFEB'>&nbsp;@&nbsp;</font><font style='background-color: #FFFFEB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFEB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_LIME_YELLOW = -0x1.04fdfcp126F;
    static { NAMED.put("bright lime yellow", -0x1.04fdfcp126F); LIST.add(-0x1.04fdfcp126F); }

    /**
     * This color constant "deep yellow lime" has RGBA8888 code {@code 8DA42FFF}, L 0.5921569, A 0.4627451, B 0.56078434,
     * alpha 1.0, red 0.5525292, green 0.64296746, blue 0.18602678, hue 0.33154932, saturation 0.8344345, and chroma 0.14202859.
     * It can be represented as a packed float with the constant {@code -0x1.1eed2ep126F}.
     * <pre>
     * <font style='background-color: #8DA42F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8DA42F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8DA42F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8DA42F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8DA42F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8DA42F'>&nbsp;@&nbsp;</font><font style='background-color: #8DA42F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8DA42F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8DA42F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_LIME = -0x1.1eed2ep126F;
    static { NAMED.put("deep yellow lime", -0x1.1eed2ep126F); LIST.add(-0x1.1eed2ep126F); }

    /**
     * This color constant "true yellow lime" has RGBA8888 code {@code A8C14BFF}, L 0.7019608, A 0.4627451, B 0.56078434,
     * alpha 1.0, red 0.65914494, green 0.7573165, blue 0.29324424, hue 0.33154932, saturation 0.74928814, and chroma 0.14202859.
     * It can be represented as a packed float with the constant {@code -0x1.1eed66p126F}.
     * <pre>
     * <font style='background-color: #A8C14B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A8C14B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A8C14B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A8C14B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A8C14B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A8C14B'>&nbsp;@&nbsp;</font><font style='background-color: #A8C14B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A8C14B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A8C14B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_YELLOW_LIME = -0x1.1eed66p126F;
    static { NAMED.put("true yellow lime", -0x1.1eed66p126F); LIST.add(-0x1.1eed66p126F); }

    /**
     * This color constant "bright yellow lime" has RGBA8888 code {@code C3DD64FF}, L 0.80784315, A 0.4627451, B 0.56078434,
     * alpha 1.0, red 0.7622879, green 0.8671199, blue 0.39076287, hue 0.33154932, saturation 0.6799096, and chroma 0.14202859.
     * It can be represented as a packed float with the constant {@code -0x1.1eed9cp126F}.
     * <pre>
     * <font style='background-color: #C3DD64;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3DD64; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3DD64;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C3DD64'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C3DD64'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C3DD64'>&nbsp;@&nbsp;</font><font style='background-color: #C3DD64; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3DD64;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3DD64; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_YELLOW_LIME = -0x1.1eed9cp126F;
    static { NAMED.put("bright yellow lime", -0x1.1eed9cp126F); LIST.add(-0x1.1eed9cp126F); }

    /**
     * This color constant "deep pure lime" has RGBA8888 code {@code 83AD29FF}, L 0.60784316, A 0.4509804, B 0.5647059,
     * alpha 1.0, red 0.5126263, green 0.6783316, blue 0.1610653, hue 0.34782666, saturation 0.84933275, and chroma 0.16172063.
     * It can be represented as a packed float with the constant {@code -0x1.20e736p126F}.
     * <pre>
     * <font style='background-color: #83AD29;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #83AD29; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #83AD29;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #83AD29'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #83AD29'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #83AD29'>&nbsp;@&nbsp;</font><font style='background-color: #83AD29; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #83AD29;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #83AD29; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_LIME = -0x1.20e736p126F;
    static { NAMED.put("deep pure lime", -0x1.20e736p126F); LIST.add(-0x1.20e736p126F); }

    /**
     * This color constant "true pure lime" has RGBA8888 code {@code A4D24CFF}, L 0.74509805, A 0.4509804, B 0.5647059,
     * alpha 1.0, red 0.64281255, green 0.82278496, blue 0.30045515, hue 0.34782666, saturation 0.7566783, and chroma 0.16172063.
     * It can be represented as a packed float with the constant {@code -0x1.20e77cp126F}.
     * <pre>
     * <font style='background-color: #A4D24C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4D24C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4D24C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A4D24C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A4D24C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A4D24C'>&nbsp;@&nbsp;</font><font style='background-color: #A4D24C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4D24C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4D24C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_LIME = -0x1.20e77cp126F;
    static { NAMED.put("true pure lime", -0x1.20e77cp126F); LIST.add(-0x1.20e77cp126F); }

    /**
     * This color constant "bright pure lime" has RGBA8888 code {@code C6F76DFF}, L 0.8862745, A 0.4509804, B 0.5647059,
     * alpha 1.0, red 0.7775863, green 0.97044444, blue 0.43107224, hue 0.34782666, saturation 0.6712469, and chroma 0.16172063.
     * It can be represented as a packed float with the constant {@code -0x1.20e7c4p126F}.
     * <pre>
     * <font style='background-color: #C6F76D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C6F76D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C6F76D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C6F76D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C6F76D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C6F76D'>&nbsp;@&nbsp;</font><font style='background-color: #C6F76D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C6F76D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C6F76D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_LIME = -0x1.20e7c4p126F;
    static { NAMED.put("bright pure lime", -0x1.20e7c4p126F); LIST.add(-0x1.20e7c4p126F); }

    /**
     * This color constant "deep green lime" has RGBA8888 code {@code 7BB441FF}, L 0.62352943, A 0.44705883, B 0.5568628,
     * alpha 1.0, red 0.48183173, green 0.70443654, blue 0.2561735, hue 0.36365107, saturation 0.7351642, and chroma 0.15477823.
     * It can be represented as a packed float with the constant {@code -0x1.1ce53ep126F}.
     * <pre>
     * <font style='background-color: #7BB441;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7BB441; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7BB441;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7BB441'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7BB441'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7BB441'>&nbsp;@&nbsp;</font><font style='background-color: #7BB441; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7BB441;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7BB441; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREEN_LIME = -0x1.1ce53ep126F;
    static { NAMED.put("deep green lime", -0x1.1ce53ep126F); LIST.add(-0x1.1ce53ep126F); }

    /**
     * This color constant "true green lime" has RGBA8888 code {@code A1DF67FF}, L 0.78431374, A 0.44705883, B 0.5568628,
     * alpha 1.0, red 0.6327491, green 0.87392515, blue 0.40409604, hue 0.36365107, saturation 0.6403043, and chroma 0.15477823.
     * It can be represented as a packed float with the constant {@code -0x1.1ce59p126F}.
     * <pre>
     * <font style='background-color: #A1DF67;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A1DF67; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A1DF67;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A1DF67'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A1DF67'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A1DF67'>&nbsp;@&nbsp;</font><font style='background-color: #A1DF67; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A1DF67;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A1DF67; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_GREEN_LIME = -0x1.1ce59p126F;
    static { NAMED.put("true green lime", -0x1.1ce59p126F); LIST.add(-0x1.1ce59p126F); }

    /**
     * This color constant "bright green lime" has RGBA8888 code {@code DBFFB0FF}, L 0.9411765, A 0.46666667, B 0.5372549,
     * alpha 1.0, red 0.8523956, green 1.0, blue 0.6872377, hue 0.35738835, saturation 0.88318956, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.12efep126F}.
     * <pre>
     * <font style='background-color: #DBFFB0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBFFB0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBFFB0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DBFFB0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DBFFB0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DBFFB0'>&nbsp;@&nbsp;</font><font style='background-color: #DBFFB0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBFFB0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBFFB0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_GREEN_LIME = -0x1.12efep126F;
    static { NAMED.put("bright green lime", -0x1.12efep126F); LIST.add(-0x1.12efep126F); }

    /**
     * This color constant "deep lime green" has RGBA8888 code {@code 5DA430FF}, L 0.5529412, A 0.4392157, B 0.5568628,
     * alpha 1.0, red 0.3646489, green 0.64192885, blue 0.1886843, hue 0.375, saturation 0.80049825, and chroma 0.1658202.
     * It can be represented as a packed float with the constant {@code -0x1.1ce11ap126F}.
     * <pre>
     * <font style='background-color: #5DA430;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5DA430; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5DA430;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5DA430'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5DA430'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5DA430'>&nbsp;@&nbsp;</font><font style='background-color: #5DA430; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5DA430;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5DA430; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_LIME_GREEN = -0x1.1ce11ap126F;
    static { NAMED.put("deep lime green", -0x1.1ce11ap126F); LIST.add(-0x1.1ce11ap126F); }

    /**
     * This color constant "true lime green" has RGBA8888 code {@code 76C14AFF}, L 0.65882355, A 0.4392157, B 0.5568628,
     * alpha 1.0, red 0.46281436, green 0.7551019, blue 0.29050586, hue 0.375, saturation 0.70710677, and chroma 0.1658202.
     * It can be represented as a packed float with the constant {@code -0x1.1ce15p126F}.
     * <pre>
     * <font style='background-color: #76C14A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #76C14A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #76C14A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #76C14A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #76C14A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #76C14A'>&nbsp;@&nbsp;</font><font style='background-color: #76C14A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #76C14A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #76C14A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_LIME_GREEN = -0x1.1ce15p126F;
    static { NAMED.put("true lime green", -0x1.1ce15p126F); LIST.add(-0x1.1ce15p126F); }

    /**
     * This color constant "bright lime green" has RGBA8888 code {@code 8FDE63FF}, L 0.76862746, A 0.4392157, B 0.5568628,
     * alpha 1.0, red 0.5649742, green 0.87164885, blue 0.3912206, hue 0.375, saturation 0.64282435, and chroma 0.1658202.
     * It can be represented as a packed float with the constant {@code -0x1.1ce188p126F}.
     * <pre>
     * <font style='background-color: #8FDE63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8FDE63; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8FDE63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8FDE63'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8FDE63'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8FDE63'>&nbsp;@&nbsp;</font><font style='background-color: #8FDE63; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8FDE63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8FDE63; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_LIME_GREEN = -0x1.1ce188p126F;
    static { NAMED.put("bright lime green", -0x1.1ce188p126F); LIST.add(-0x1.1ce188p126F); }

    /**
     * This color constant "deep pure green" has RGBA8888 code {@code 3BB12CFF}, L 0.5764706, A 0.41960785, B 0.56078434,
     * alpha 1.0, red 0.23486769, green 0.6960433, blue 0.17637652, hue 0.39261165, saturation 0.839754, and chroma 0.20078278.
     * It can be represented as a packed float with the constant {@code -0x1.1ed726p126F}.
     * <pre>
     * <font style='background-color: #3BB12C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3BB12C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3BB12C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3BB12C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3BB12C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3BB12C'>&nbsp;@&nbsp;</font><font style='background-color: #3BB12C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3BB12C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3BB12C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_GREEN = -0x1.1ed726p126F;
    static { NAMED.put("deep pure green", -0x1.1ed726p126F); LIST.add(-0x1.1ed726p126F); }

    /**
     * This color constant "true pure green" has RGBA8888 code {@code 5CD54CFF}, L 0.7058824, A 0.41960785, B 0.56078434,
     * alpha 1.0, red 0.36344713, green 0.8368404, blue 0.30402812, hue 0.39261165, saturation 0.73178566, and chroma 0.20078278.
     * It can be represented as a packed float with the constant {@code -0x1.1ed768p126F}.
     * <pre>
     * <font style='background-color: #5CD54C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5CD54C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5CD54C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5CD54C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5CD54C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5CD54C'>&nbsp;@&nbsp;</font><font style='background-color: #5CD54C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5CD54C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5CD54C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_GREEN = -0x1.1ed768p126F;
    static { NAMED.put("true pure green", -0x1.1ed768p126F); LIST.add(-0x1.1ed768p126F); }

    /**
     * This color constant "bright pure green" has RGBA8888 code {@code 7AF76AFF}, L 0.827451, A 0.41960785, B 0.56078434,
     * alpha 1.0, red 0.47895512, green 0.9678556, blue 0.41619346, hue 0.39261165, saturation 0.70171225, and chroma 0.20078278.
     * It can be represented as a packed float with the constant {@code -0x1.1ed7a6p126F}.
     * <pre>
     * <font style='background-color: #7AF76A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7AF76A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7AF76A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7AF76A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7AF76A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7AF76A'>&nbsp;@&nbsp;</font><font style='background-color: #7AF76A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7AF76A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7AF76A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_GREEN = -0x1.1ed7a6p126F;
    static { NAMED.put("bright pure green", -0x1.1ed7a6p126F); LIST.add(-0x1.1ed7a6p126F); }

    /**
     * This color constant "deep cyan green" has RGBA8888 code {@code 3DB379FF}, L 0.59607846, A 0.43529412, B 0.52156866,
     * alpha 1.0, red 0.23877664, green 0.7012448, blue 0.47458234, hue 0.44289988, saturation 0.7767276, and chroma 0.13587911.
     * It can be represented as a packed float with the constant {@code -0x1.0adf3p126F}.
     * <pre>
     * <font style='background-color: #3DB379;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3DB379; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3DB379;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3DB379'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3DB379'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3DB379'>&nbsp;@&nbsp;</font><font style='background-color: #3DB379; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3DB379;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3DB379; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_CYAN_GREEN = -0x1.0adf3p126F;
    static { NAMED.put("deep cyan green", -0x1.0adf3p126F); LIST.add(-0x1.0adf3p126F); }

    /**
     * This color constant "true cyan green" has RGBA8888 code {@code 65DE9FFF}, L 0.75686276, A 0.43529412, B 0.52156866,
     * alpha 1.0, red 0.39996132, green 0.8726367, blue 0.6262777, hue 0.44289988, saturation 0.65723103, and chroma 0.13587911.
     * It can be represented as a packed float with the constant {@code -0x1.0adf82p126F}.
     * <pre>
     * <font style='background-color: #65DE9F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #65DE9F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #65DE9F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #65DE9F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #65DE9F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #65DE9F'>&nbsp;@&nbsp;</font><font style='background-color: #65DE9F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #65DE9F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #65DE9F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_CYAN_GREEN = -0x1.0adf82p126F;
    static { NAMED.put("true cyan green", -0x1.0adf82p126F); LIST.add(-0x1.0adf82p126F); }

    /**
     * This color constant "bright cyan green" has RGBA8888 code {@code A1FFCBFF}, L 0.9019608, A 0.44705883, B 0.5176471,
     * alpha 1.0, red 0.6222054, green 1.0, blue 0.7874345, hue 0.44156247, saturation 0.92855924, and chroma 0.11117382.
     * It can be represented as a packed float with the constant {@code -0x1.08e5ccp126F}.
     * <pre>
     * <font style='background-color: #A1FFCB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A1FFCB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A1FFCB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A1FFCB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A1FFCB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A1FFCB'>&nbsp;@&nbsp;</font><font style='background-color: #A1FFCB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A1FFCB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A1FFCB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_CYAN_GREEN = -0x1.08e5ccp126F;
    static { NAMED.put("bright cyan green", -0x1.08e5ccp126F); LIST.add(-0x1.08e5ccp126F); }

    /**
     * This color constant "deep green cyan" has RGBA8888 code {@code 32A68CFF}, L 0.56078434, A 0.44313726, B 0.5019608,
     * alpha 1.0, red 0.19851287, green 0.6519343, blue 0.5501903, hue 0.48865107, saturation 0.8020382, and chroma 0.11334858.
     * It can be represented as a packed float with the constant {@code -0x1.00e31ep126F}.
     * <pre>
     * <font style='background-color: #32A68C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #32A68C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #32A68C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #32A68C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #32A68C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #32A68C'>&nbsp;@&nbsp;</font><font style='background-color: #32A68C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #32A68C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #32A68C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREEN_CYAN = -0x1.00e31ep126F;
    static { NAMED.put("deep green cyan", -0x1.00e31ep126F); LIST.add(-0x1.00e31ep126F); }

    /**
     * This color constant "true green cyan" has RGBA8888 code {@code 4EC2A6FF}, L 0.6627451, A 0.44313726, B 0.5019608,
     * alpha 1.0, red 0.30626467, green 0.7601588, blue 0.6515496, hue 0.48865107, saturation 0.7387194, and chroma 0.11334858.
     * It can be represented as a packed float with the constant {@code -0x1.00e352p126F}.
     * <pre>
     * <font style='background-color: #4EC2A6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4EC2A6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4EC2A6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4EC2A6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4EC2A6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4EC2A6'>&nbsp;@&nbsp;</font><font style='background-color: #4EC2A6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4EC2A6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4EC2A6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_GREEN_CYAN = -0x1.00e352p126F;
    static { NAMED.put("true green cyan", -0x1.00e352p126F); LIST.add(-0x1.00e352p126F); }

    /**
     * This color constant "bright green cyan" has RGBA8888 code {@code 68DEC0FF}, L 0.76862746, A 0.44313726, B 0.5019608,
     * alpha 1.0, red 0.41010672, green 0.8718754, blue 0.75686425, hue 0.48865107, saturation 0.6683652, and chroma 0.11334858.
     * It can be represented as a packed float with the constant {@code -0x1.00e388p126F}.
     * <pre>
     * <font style='background-color: #68DEC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #68DEC0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #68DEC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #68DEC0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #68DEC0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #68DEC0'>&nbsp;@&nbsp;</font><font style='background-color: #68DEC0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #68DEC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #68DEC0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_GREEN_CYAN = -0x1.00e388p126F;
    static { NAMED.put("bright green cyan", -0x1.00e388p126F); LIST.add(-0x1.00e388p126F); }

    /**
     * This color constant "deep pure cyan" has RGBA8888 code {@code 1DADAFFF}, L 0.5882353, A 0.44313726, B 0.48235294,
     * alpha 1.0, red 0.1166596, green 0.67851174, blue 0.68449986, hue 0.5442928, saturation 0.91001374, and chroma 0.11861114.
     * It can be represented as a packed float with the constant {@code -0x1.f6e32cp125F}.
     * <pre>
     * <font style='background-color: #1DADAF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1DADAF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1DADAF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1DADAF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1DADAF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1DADAF'>&nbsp;@&nbsp;</font><font style='background-color: #1DADAF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1DADAF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1DADAF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_CYAN = -0x1.f6e32cp125F;
    static { NAMED.put("deep pure cyan", -0x1.f6e32cp125F); LIST.add(-0x1.f6e32cp125F); }

    /**
     * This color constant "true pure cyan" has RGBA8888 code {@code 48D1D3FF}, L 0.72156864, A 0.44313726, B 0.48235294,
     * alpha 1.0, red 0.28406465, green 0.8197948, blue 0.824585, hue 0.5442928, saturation 0.7870389, and chroma 0.11861114.
     * It can be represented as a packed float with the constant {@code -0x1.f6e37p125F}.
     * <pre>
     * <font style='background-color: #48D1D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #48D1D3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #48D1D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #48D1D3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #48D1D3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #48D1D3'>&nbsp;@&nbsp;</font><font style='background-color: #48D1D3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #48D1D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #48D1D3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_CYAN = -0x1.f6e37p125F;
    static { NAMED.put("true pure cyan", -0x1.f6e37p125F); LIST.add(-0x1.f6e37p125F); }

    /**
     * This color constant "bright pure cyan" has RGBA8888 code {@code 6CF6F7FF}, L 0.85882354, A 0.44313726, B 0.48235294,
     * alpha 1.0, red 0.42346075, green 0.9642238, blue 0.96796745, hue 0.5442928, saturation 0.7102546, and chroma 0.11861114.
     * It can be represented as a packed float with the constant {@code -0x1.f6e3b6p125F}.
     * <pre>
     * <font style='background-color: #6CF6F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6CF6F7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6CF6F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6CF6F7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6CF6F7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6CF6F7'>&nbsp;@&nbsp;</font><font style='background-color: #6CF6F7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6CF6F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6CF6F7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_CYAN = -0x1.f6e3b6p125F;
    static { NAMED.put("bright pure cyan", -0x1.f6e3b6p125F); LIST.add(-0x1.f6e3b6p125F); }

    /**
     * This color constant "deep blue cyan" has RGBA8888 code {@code 3A9CB3FF}, L 0.54901963, A 0.45882353, B 0.47058824,
     * alpha 1.0, red 0.23025283, green 0.6103611, blue 0.70136845, hue 0.59720004, saturation 0.7629097, and chroma 0.1008085.
     * It can be represented as a packed float with the constant {@code -0x1.f0eb18p125F}.
     * <pre>
     * <font style='background-color: #3A9CB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3A9CB3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3A9CB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3A9CB3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3A9CB3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3A9CB3'>&nbsp;@&nbsp;</font><font style='background-color: #3A9CB3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3A9CB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3A9CB3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE_CYAN = -0x1.f0eb18p125F;
    static { NAMED.put("deep blue cyan", -0x1.f0eb18p125F); LIST.add(-0x1.f0eb18p125F); }

    /**
     * This color constant "true blue cyan" has RGBA8888 code {@code 61C5DEFF}, L 0.7058824, A 0.45882353, B 0.47058824,
     * alpha 1.0, red 0.38391963, green 0.7742696, blue 0.8716233, hue 0.59720004, saturation 0.6598138, and chroma 0.1008085.
     * It can be represented as a packed float with the constant {@code -0x1.f0eb68p125F}.
     * <pre>
     * <font style='background-color: #61C5DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #61C5DE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #61C5DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #61C5DE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #61C5DE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #61C5DE'>&nbsp;@&nbsp;</font><font style='background-color: #61C5DE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #61C5DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #61C5DE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BLUE_CYAN = -0x1.f0eb68p125F;
    static { NAMED.put("true blue cyan", -0x1.f0eb68p125F); LIST.add(-0x1.f0eb68p125F); }

    /**
     * This color constant "bright blue cyan" has RGBA8888 code {@code 8DEAFFFF}, L 0.8509804, A 0.4627451, B 0.4745098,
     * alpha 1.0, red 0.5640759, green 0.91864115, blue 1.0, hue 0.5935835, saturation 0.86533237, and chroma 0.08992863.
     * It can be represented as a packed float with the constant {@code -0x1.f2edb2p125F}.
     * <pre>
     * <font style='background-color: #8DEAFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8DEAFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8DEAFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8DEAFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8DEAFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8DEAFF'>&nbsp;@&nbsp;</font><font style='background-color: #8DEAFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8DEAFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8DEAFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE_CYAN = -0x1.f2edb2p125F;
    static { NAMED.put("bright blue cyan", -0x1.f2edb2p125F); LIST.add(-0x1.f2edb2p125F); }

    /**
     * This color constant "deep cyan blue" has RGBA8888 code {@code 2571A5FF}, L 0.41568628, A 0.47058824, B 0.4509804,
     * alpha 1.0, red 0.1453479, green 0.44516587, blue 0.6450195, hue 0.66595435, saturation 0.81720257, and chroma 0.11388578.
     * It can be represented as a packed float with the constant {@code -0x1.e6f0d4p125F}.
     * <pre>
     * <font style='background-color: #2571A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2571A5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2571A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2571A5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2571A5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2571A5'>&nbsp;@&nbsp;</font><font style='background-color: #2571A5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2571A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2571A5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_CYAN_BLUE = -0x1.e6f0d4p125F;
    static { NAMED.put("deep cyan blue", -0x1.e6f0d4p125F); LIST.add(-0x1.e6f0d4p125F); }

    /**
     * This color constant "true cyan blue" has RGBA8888 code {@code 3E8CC2FF}, L 0.5137255, A 0.47058824, B 0.4509804,
     * alpha 1.0, red 0.24423559, green 0.54765457, blue 0.75984657, hue 0.66595435, saturation 0.712433, and chroma 0.11388578.
     * It can be represented as a packed float with the constant {@code -0x1.e6f106p125F}.
     * <pre>
     * <font style='background-color: #3E8CC2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3E8CC2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3E8CC2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3E8CC2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3E8CC2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3E8CC2'>&nbsp;@&nbsp;</font><font style='background-color: #3E8CC2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3E8CC2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3E8CC2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_CYAN_BLUE = -0x1.e6f106p125F;
    static { NAMED.put("true cyan blue", -0x1.e6f106p125F); LIST.add(-0x1.e6f106p125F); }

    /**
     * This color constant "bright cyan blue" has RGBA8888 code {@code 57A7E0FF}, L 0.6156863, A 0.47058824, B 0.4509804,
     * alpha 1.0, red 0.340694, green 0.6535577, blue 0.8772023, hue 0.66595435, saturation 0.6461602, and chroma 0.11388578.
     * It can be represented as a packed float with the constant {@code -0x1.e6f13ap125F}.
     * <pre>
     * <font style='background-color: #57A7E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57A7E0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57A7E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #57A7E0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #57A7E0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #57A7E0'>&nbsp;@&nbsp;</font><font style='background-color: #57A7E0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57A7E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57A7E0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_CYAN_BLUE = -0x1.e6f13ap125F;
    static { NAMED.put("bright cyan blue", -0x1.e6f13ap125F); LIST.add(-0x1.e6f13ap125F); }

    /**
     * This color constant "deep pure blue" has RGBA8888 code {@code 062CB7FF}, L 0.25882354, A 0.4862745, B 0.3882353,
     * alpha 1.0, red 0.024219962, green 0.17192423, blue 0.71744037, hue 0.7330125, saturation 0.7715138, and chroma 0.22432896.
     * It can be represented as a packed float with the constant {@code -0x1.c6f884p125F}.
     * <pre>
     * <font style='background-color: #062CB7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #062CB7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #062CB7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #062CB7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #062CB7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #062CB7'>&nbsp;@&nbsp;</font><font style='background-color: #062CB7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #062CB7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #062CB7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_BLUE = -0x1.c6f884p125F;
    static { NAMED.put("deep pure blue", -0x1.c6f884p125F); LIST.add(-0x1.c6f884p125F); }

    /**
     * This color constant "true pure blue" has RGBA8888 code {@code 1A49DBFF}, L 0.3529412, A 0.4862745, B 0.3882353,
     * alpha 1.0, red 0.10485282, green 0.28773624, blue 0.85597557, hue 0.7330125, saturation 0.761088, and chroma 0.22432896.
     * It can be represented as a packed float with the constant {@code -0x1.c6f8b4p125F}.
     * <pre>
     * <font style='background-color: #1A49DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1A49DB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1A49DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1A49DB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1A49DB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1A49DB'>&nbsp;@&nbsp;</font><font style='background-color: #1A49DB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1A49DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1A49DB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_BLUE = -0x1.c6f8b4p125F;
    static { NAMED.put("true pure blue", -0x1.c6f8b4p125F); LIST.add(-0x1.c6f8b4p125F); }

    /**
     * This color constant "bright pure blue" has RGBA8888 code {@code 2F65FEFF}, L 0.4509804, A 0.4862745, B 0.3882353,
     * alpha 1.0, red 0.18521373, green 0.39791065, blue 0.99351615, hue 0.7330125, saturation 0.95458496, and chroma 0.22432896.
     * It can be represented as a packed float with the constant {@code -0x1.c6f8e6p125F}.
     * <pre>
     * <font style='background-color: #2F65FE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2F65FE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2F65FE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2F65FE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2F65FE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2F65FE'>&nbsp;@&nbsp;</font><font style='background-color: #2F65FE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2F65FE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2F65FE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_BLUE = -0x1.c6f8e6p125F;
    static { NAMED.put("bright pure blue", -0x1.c6f8e6p125F); LIST.add(-0x1.c6f8e6p125F); }

    /**
     * This color constant "deep violet blue" has RGBA8888 code {@code 3334AFFF}, L 0.28235295, A 0.5058824, B 0.40392157,
     * alpha 1.0, red 0.1999377, green 0.20202589, blue 0.6781426, hue 0.76323235, saturation 0.6689775, and chroma 0.19176465.
     * It can be represented as a packed float with the constant {@code -0x1.cf029p125F}.
     * <pre>
     * <font style='background-color: #3334AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3334AF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3334AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3334AF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3334AF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3334AF'>&nbsp;@&nbsp;</font><font style='background-color: #3334AF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3334AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3334AF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET_BLUE = -0x1.cf029p125F;
    static { NAMED.put("deep violet blue", -0x1.cf029p125F); LIST.add(-0x1.cf029p125F); }

    /**
     * This color constant "true violet blue" has RGBA8888 code {@code 4A53D5FF}, L 0.39215687, A 0.5058824, B 0.40392157,
     * alpha 1.0, red 0.2887275, green 0.32331362, blue 0.8306245, hue 0.76323235, saturation 0.69806343, and chroma 0.19176465.
     * It can be represented as a packed float with the constant {@code -0x1.cf02c8p125F}.
     * <pre>
     * <font style='background-color: #4A53D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A53D5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A53D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4A53D5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4A53D5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4A53D5'>&nbsp;@&nbsp;</font><font style='background-color: #4A53D5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A53D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A53D5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_VIOLET_BLUE = -0x1.cf02c8p125F;
    static { NAMED.put("true violet blue", -0x1.cf02c8p125F); LIST.add(-0x1.cf02c8p125F); }

    /**
     * This color constant "bright violet blue" has RGBA8888 code {@code 6372FCFF}, L 0.50980395, A 0.5058824, B 0.40392157,
     * alpha 1.0, red 0.38995442, green 0.4470318, blue 0.98671544, hue 0.76323235, saturation 0.9262765, and chroma 0.19176465.
     * It can be represented as a packed float with the constant {@code -0x1.cf0304p125F}.
     * <pre>
     * <font style='background-color: #6372FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6372FC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6372FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6372FC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6372FC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6372FC'>&nbsp;@&nbsp;</font><font style='background-color: #6372FC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6372FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6372FC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_VIOLET_BLUE = -0x1.cf0304p125F;
    static { NAMED.put("bright violet blue", -0x1.cf0304p125F); LIST.add(-0x1.cf0304p125F); }

    /**
     * This color constant "deep blue violet" has RGBA8888 code {@code 4629A5FF}, L 0.27450982, A 0.5254902, B 0.40784314,
     * alpha 1.0, red 0.27449235, green 0.1617524, blue 0.6451178, hue 0.79702085, saturation 0.73974246, and chroma 0.19048727.
     * It can be represented as a packed float with the constant {@code -0x1.d10c8cp125F}.
     * <pre>
     * <font style='background-color: #4629A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4629A5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4629A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4629A5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4629A5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4629A5'>&nbsp;@&nbsp;</font><font style='background-color: #4629A5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4629A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4629A5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE_VIOLET = -0x1.d10c8cp125F;
    static { NAMED.put("deep blue violet", -0x1.d10c8cp125F); LIST.add(-0x1.d10c8cp125F); }

    /**
     * This color constant "true blue violet" has RGBA8888 code {@code 5A42C3FF}, L 0.36078432, A 0.5254902, B 0.40784314,
     * alpha 1.0, red 0.35346615, green 0.2581996, blue 0.7646053, hue 0.79702085, saturation 0.6244579, and chroma 0.19048727.
     * It can be represented as a packed float with the constant {@code -0x1.d10cb8p125F}.
     * <pre>
     * <font style='background-color: #5A42C3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A42C3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A42C3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5A42C3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5A42C3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5A42C3'>&nbsp;@&nbsp;</font><font style='background-color: #5A42C3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A42C3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A42C3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BLUE_VIOLET = -0x1.d10cb8p125F;
    static { NAMED.put("true blue violet", -0x1.d10cb8p125F); LIST.add(-0x1.d10cb8p125F); }

    /**
     * This color constant "bright blue violet" has RGBA8888 code {@code 6E58DFFF}, L 0.44313726, A 0.5254902, B 0.40784314,
     * alpha 1.0, red 0.43041164, green 0.34466168, blue 0.87450755, hue 0.79702085, saturation 0.73974246, and chroma 0.19048727.
     * It can be represented as a packed float with the constant {@code -0x1.d10ce2p125F}.
     * <pre>
     * <font style='background-color: #6E58DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E58DF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E58DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6E58DF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6E58DF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6E58DF'>&nbsp;@&nbsp;</font><font style='background-color: #6E58DF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E58DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E58DF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE_VIOLET = -0x1.d10ce2p125F;
    static { NAMED.put("bright blue violet", -0x1.d10ce2p125F); LIST.add(-0x1.d10ce2p125F); }

    /**
     * This color constant "deep pure violet" has RGBA8888 code {@code 6129B3FF}, L 0.3137255, A 0.54509807, B 0.40784314,
     * alpha 1.0, red 0.3781391, green 0.15908314, blue 0.695289, hue 0.8265356, saturation 0.7861286, and chroma 0.20439805.
     * It can be represented as a packed float with the constant {@code -0x1.d116ap125F}.
     * <pre>
     * <font style='background-color: #6129B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6129B3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6129B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6129B3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6129B3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6129B3'>&nbsp;@&nbsp;</font><font style='background-color: #6129B3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6129B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6129B3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_VIOLET = -0x1.d116ap125F;
    static { NAMED.put("deep pure violet", -0x1.d116ap125F); LIST.add(-0x1.d116ap125F); }

    /**
     * This color constant "true pure violet" has RGBA8888 code {@code 7C46D6FF}, L 0.41960785, A 0.54509807, B 0.40784314,
     * alpha 1.0, red 0.48608696, green 0.27642685, blue 0.8384142, hue 0.8265356, saturation 0.7011417, and chroma 0.20439805.
     * It can be represented as a packed float with the constant {@code -0x1.d116d6p125F}.
     * <pre>
     * <font style='background-color: #7C46D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7C46D6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7C46D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7C46D6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7C46D6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7C46D6'>&nbsp;@&nbsp;</font><font style='background-color: #7C46D6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7C46D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7C46D6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_VIOLET = -0x1.d116d6p125F;
    static { NAMED.put("true pure violet", -0x1.d116d6p125F); LIST.add(-0x1.d116d6p125F); }

    /**
     * This color constant "bright pure violet" has RGBA8888 code {@code 9862F9FF}, L 0.5254902, A 0.54509807, B 0.40784314,
     * alpha 1.0, red 0.5939636, green 0.38530862, blue 0.97624403, hue 0.8265356, saturation 0.9102542, and chroma 0.20439805.
     * It can be represented as a packed float with the constant {@code -0x1.d1170cp125F}.
     * <pre>
     * <font style='background-color: #9862F9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9862F9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9862F9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9862F9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9862F9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9862F9'>&nbsp;@&nbsp;</font><font style='background-color: #9862F9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9862F9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9862F9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_VIOLET = -0x1.d1170cp125F;
    static { NAMED.put("bright pure violet", -0x1.d1170cp125F); LIST.add(-0x1.d1170cp125F); }

    /**
     * This color constant "deep purple violet" has RGBA8888 code {@code 6E34AFFF}, L 0.34509805, A 0.54901963, B 0.41960785,
     * alpha 1.0, red 0.43331847, green 0.20476213, blue 0.6838482, hue 0.841733, saturation 0.70158005, and chroma 0.18758136.
     * It can be represented as a packed float with the constant {@code -0x1.d718bp125F}.
     * <pre>
     * <font style='background-color: #6E34AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E34AF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E34AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6E34AF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6E34AF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6E34AF'>&nbsp;@&nbsp;</font><font style='background-color: #6E34AF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E34AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E34AF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE_VIOLET = -0x1.d718bp125F;
    static { NAMED.put("deep purple violet", -0x1.d718bp125F); LIST.add(-0x1.d718bp125F); }

    /**
     * This color constant "true purple violet" has RGBA8888 code {@code 9055D8FF}, L 0.47058824, A 0.54901963, B 0.41960785,
     * alpha 1.0, red 0.566339, green 0.33382228, blue 0.84572345, hue 0.841733, saturation 0.6815349, and chroma 0.18758136.
     * It can be represented as a packed float with the constant {@code -0x1.d718fp125F}.
     * <pre>
     * <font style='background-color: #9055D8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9055D8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9055D8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9055D8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9055D8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9055D8'>&nbsp;@&nbsp;</font><font style='background-color: #9055D8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9055D8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9055D8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURPLE_VIOLET = -0x1.d718fp125F;
    static { NAMED.put("true purple violet", -0x1.d718fp125F); LIST.add(-0x1.d718fp125F); }

    /**
     * This color constant "bright purple violet" has RGBA8888 code {@code B275FFFF}, L 0.59607846, A 0.54901963, B 0.41960785,
     * alpha 1.0, red 0.6982466, green 0.4584172, blue 1.0, hue 0.841733, saturation 0.9541489, and chroma 0.18758136.
     * It can be represented as a packed float with the constant {@code -0x1.d7193p125F}.
     * <pre>
     * <font style='background-color: #B275FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B275FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B275FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B275FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B275FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B275FF'>&nbsp;@&nbsp;</font><font style='background-color: #B275FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B275FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B275FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURPLE_VIOLET = -0x1.d7193p125F;
    static { NAMED.put("bright purple violet", -0x1.d7193p125F); LIST.add(-0x1.d7193p125F); }

    /**
     * This color constant "deep violet purple" has RGBA8888 code {@code 712BA4FF}, L 0.3254902, A 0.5568628, B 0.42352942,
     * alpha 1.0, red 0.440524, green 0.16742551, blue 0.6386107, hue 0.85636157, saturation 0.7564824, and chroma 0.18984535.
     * It can be represented as a packed float with the constant {@code -0x1.d91ca6p125F}.
     * <pre>
     * <font style='background-color: #712BA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #712BA4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #712BA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #712BA4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #712BA4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #712BA4'>&nbsp;@&nbsp;</font><font style='background-color: #712BA4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #712BA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #712BA4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET_PURPLE = -0x1.d91ca6p125F;
    static { NAMED.put("deep violet purple", -0x1.d91ca6p125F); LIST.add(-0x1.d91ca6p125F); }

    /**
     * This color constant "true violet purple" has RGBA8888 code {@code 8B44C2FF}, L 0.41960785, A 0.5568628, B 0.42352942,
     * alpha 1.0, red 0.5445123, green 0.26683828, blue 0.76009864, hue 0.85636157, saturation 0.64553165, and chroma 0.18984535.
     * It can be represented as a packed float with the constant {@code -0x1.d91cd6p125F}.
     * <pre>
     * <font style='background-color: #8B44C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B44C2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B44C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8B44C2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8B44C2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8B44C2'>&nbsp;@&nbsp;</font><font style='background-color: #8B44C2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B44C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B44C2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_VIOLET_PURPLE = -0x1.d91cd6p125F;
    static { NAMED.put("true violet purple", -0x1.d91cd6p125F); LIST.add(-0x1.d91cd6p125F); }

    /**
     * This color constant "bright violet purple" has RGBA8888 code {@code A45BDFFF}, L 0.50980395, A 0.5568628, B 0.42352942,
     * alpha 1.0, red 0.6429181, green 0.35728338, blue 0.8731743, hue 0.85636157, saturation 0.70166487, and chroma 0.18984535.
     * It can be represented as a packed float with the constant {@code -0x1.d91d04p125F}.
     * <pre>
     * <font style='background-color: #A45BDF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A45BDF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A45BDF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A45BDF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A45BDF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A45BDF'>&nbsp;@&nbsp;</font><font style='background-color: #A45BDF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A45BDF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A45BDF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_VIOLET_PURPLE = -0x1.d91d04p125F;
    static { NAMED.put("bright violet purple", -0x1.d91d04p125F); LIST.add(-0x1.d91d04p125F); }

    /**
     * This color constant "deep pure purple" has RGBA8888 code {@code 8828B5FF}, L 0.36862746, A 0.57254905, B 0.41960785,
     * alpha 1.0, red 0.534701, green 0.1571798, blue 0.7090494, hue 0.87092, saturation 0.8113597, and chroma 0.21572971.
     * It can be represented as a packed float with the constant {@code -0x1.d724bcp125F}.
     * <pre>
     * <font style='background-color: #8828B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8828B5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8828B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8828B5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8828B5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8828B5'>&nbsp;@&nbsp;</font><font style='background-color: #8828B5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8828B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8828B5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_PURPLE = -0x1.d724bcp125F;
    static { NAMED.put("deep pure purple", -0x1.d724bcp125F); LIST.add(-0x1.d724bcp125F); }

    /**
     * This color constant "true pure purple" has RGBA8888 code {@code A947D9FF}, L 0.47843137, A 0.57254905, B 0.41960785,
     * alpha 1.0, red 0.66093236, green 0.27753043, blue 0.8496611, hue 0.87092, saturation 0.69838554, and chroma 0.21572971.
     * It can be represented as a packed float with the constant {@code -0x1.d724f4p125F}.
     * <pre>
     * <font style='background-color: #A947D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A947D9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A947D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A947D9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A947D9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A947D9'>&nbsp;@&nbsp;</font><font style='background-color: #A947D9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A947D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A947D9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_PURPLE = -0x1.d724f4p125F;
    static { NAMED.put("true pure purple", -0x1.d724f4p125F); LIST.add(-0x1.d724f4p125F); }

    /**
     * This color constant "bright pure purple" has RGBA8888 code {@code CA64FDFF}, L 0.5921569, A 0.57254905, B 0.41960785,
     * alpha 1.0, red 0.7892657, green 0.39219862, blue 0.9909785, hue 0.87092, saturation 0.9351264, and chroma 0.21572971.
     * It can be represented as a packed float with the constant {@code -0x1.d7252ep125F}.
     * <pre>
     * <font style='background-color: #CA64FD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CA64FD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CA64FD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CA64FD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CA64FD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CA64FD'>&nbsp;@&nbsp;</font><font style='background-color: #CA64FD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CA64FD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CA64FD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_PURPLE = -0x1.d7252ep125F;
    static { NAMED.put("bright pure purple", -0x1.d7252ep125F); LIST.add(-0x1.d7252ep125F); }

    /**
     * This color constant "deep magenta purple" has RGBA8888 code {@code A739B1FF}, L 0.43137255, A 0.5803922, B 0.4392157,
     * alpha 1.0, red 0.6541556, green 0.22380579, blue 0.69447196, hue 0.9012842, saturation 0.7269571, and chroma 0.20078278.
     * It can be represented as a packed float with the constant {@code -0x1.e128dcp125F}.
     * <pre>
     * <font style='background-color: #A739B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A739B1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A739B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A739B1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A739B1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A739B1'>&nbsp;@&nbsp;</font><font style='background-color: #A739B1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A739B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A739B1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_MAGENTA_PURPLE = -0x1.e128dcp125F;
    static { NAMED.put("deep magenta purple", -0x1.e128dcp125F); LIST.add(-0x1.e128dcp125F); }

    /**
     * This color constant "true magenta purple" has RGBA8888 code {@code D15CDBFF}, L 0.5686275, A 0.5803922, B 0.4392157,
     * alpha 1.0, red 0.81736743, green 0.36146143, blue 0.8578916, hue 0.9012842, saturation 0.6372093, and chroma 0.20078278.
     * It can be represented as a packed float with the constant {@code -0x1.e12922p125F}.
     * <pre>
     * <font style='background-color: #D15CDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D15CDB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D15CDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D15CDB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D15CDB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D15CDB'>&nbsp;@&nbsp;</font><font style='background-color: #D15CDB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D15CDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D15CDB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_MAGENTA_PURPLE = -0x1.e12922p125F;
    static { NAMED.put("true magenta purple", -0x1.e12922p125F); LIST.add(-0x1.e12922p125F); }

    /**
     * This color constant "bright magenta purple" has RGBA8888 code {@code FA7EFFFF}, L 0.7058824, A 0.5803922, B 0.44313726,
     * alpha 1.0, red 0.9826056, green 0.49657112, blue 0.99568105, hue 0.9064165, saturation 0.9177767, and chroma 0.19616999.
     * It can be represented as a packed float with the constant {@code -0x1.e32968p125F}.
     * <pre>
     * <font style='background-color: #FA7EFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FA7EFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FA7EFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FA7EFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FA7EFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FA7EFF'>&nbsp;@&nbsp;</font><font style='background-color: #FA7EFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FA7EFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FA7EFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA_PURPLE = -0x1.e32968p125F;
    static { NAMED.put("bright magenta purple", -0x1.e32968p125F); LIST.add(-0x1.e32968p125F); }

    /**
     * This color constant "deep purple magenta" has RGBA8888 code {@code A82C95FF}, L 0.4, A 0.5882353, B 0.45490196,
     * alpha 1.0, red 0.6577336, green 0.17226191, blue 0.5824091, hue 0.9290001, saturation 0.7967218, and chroma 0.1974106.
     * It can be represented as a packed float with the constant {@code -0x1.e92cccp125F}.
     * <pre>
     * <font style='background-color: #A82C95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A82C95; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A82C95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A82C95'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A82C95'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A82C95'>&nbsp;@&nbsp;</font><font style='background-color: #A82C95; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A82C95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A82C95; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE_MAGENTA = -0x1.e92cccp125F;
    static { NAMED.put("deep purple magenta", -0x1.e92cccp125F); LIST.add(-0x1.e92cccp125F); }

    /**
     * This color constant "true purple magenta" has RGBA8888 code {@code C645B0FF}, L 0.49411765, A 0.5882353, B 0.45490196,
     * alpha 1.0, red 0.7755928, green 0.27081117, blue 0.6904878, hue 0.9290001, saturation 0.6890567, and chroma 0.1974106.
     * It can be represented as a packed float with the constant {@code -0x1.e92cfcp125F}.
     * <pre>
     * <font style='background-color: #C645B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C645B0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C645B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C645B0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C645B0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C645B0'>&nbsp;@&nbsp;</font><font style='background-color: #C645B0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C645B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C645B0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURPLE_MAGENTA = -0x1.e92cfcp125F;
    static { NAMED.put("true purple magenta", -0x1.e92cfcp125F); LIST.add(-0x1.e92cfcp125F); }

    /**
     * This color constant "bright purple magenta" has RGBA8888 code {@code E25CCAFF}, L 0.58431375, A 0.5882353, B 0.45490196,
     * alpha 1.0, red 0.88596064, green 0.35983917, blue 0.7925137, hue 0.9290001, saturation 0.63737744, and chroma 0.1974106.
     * It can be represented as a packed float with the constant {@code -0x1.e92d2ap125F}.
     * <pre>
     * <font style='background-color: #E25CCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E25CCA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E25CCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E25CCA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E25CCA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E25CCA'>&nbsp;@&nbsp;</font><font style='background-color: #E25CCA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E25CCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E25CCA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURPLE_MAGENTA = -0x1.e92d2ap125F;
    static { NAMED.put("bright purple magenta", -0x1.e92d2ap125F); LIST.add(-0x1.e92d2ap125F); }

    /**
     * This color constant "deep pure magenta" has RGBA8888 code {@code B42583FF}, L 0.40392157, A 0.59607846, B 0.47058824,
     * alpha 1.0, red 0.7048339, green 0.14657597, blue 0.51331365, hue 0.9565493, saturation 0.837468, and chroma 0.20017387.
     * It can be represented as a packed float with the constant {@code -0x1.f130cep125F}.
     * <pre>
     * <font style='background-color: #B42583;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B42583; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B42583;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B42583'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B42583'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B42583'>&nbsp;@&nbsp;</font><font style='background-color: #B42583; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B42583;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B42583; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_MAGENTA = -0x1.f130cep125F;
    static { NAMED.put("deep pure magenta", -0x1.f130cep125F); LIST.add(-0x1.f130cep125F); }

    /**
     * This color constant "true pure magenta" has RGBA8888 code {@code D844A2FF}, L 0.5137255, A 0.59607846, B 0.47058824,
     * alpha 1.0, red 0.846967, green 0.26605552, blue 0.63284147, hue 0.9565493, saturation 0.721153, and chroma 0.20017387.
     * It can be represented as a packed float with the constant {@code -0x1.f13106p125F}.
     * <pre>
     * <font style='background-color: #D844A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D844A2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D844A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D844A2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D844A2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D844A2'>&nbsp;@&nbsp;</font><font style='background-color: #D844A2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D844A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D844A2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_MAGENTA = -0x1.f13106p125F;
    static { NAMED.put("true pure magenta", -0x1.f13106p125F); LIST.add(-0x1.f13106p125F); }

    /**
     * This color constant "bright pure magenta" has RGBA8888 code {@code FC5FC0FF}, L 0.62352943, A 0.59607846, B 0.47058824,
     * alpha 1.0, red 0.9849975, green 0.3749805, blue 0.75105804, hue 0.9565493, saturation 0.8800512, and chroma 0.20017387.
     * It can be represented as a packed float with the constant {@code -0x1.f1313ep125F}.
     * <pre>
     * <font style='background-color: #FC5FC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FC5FC0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FC5FC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FC5FC0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FC5FC0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FC5FC0'>&nbsp;@&nbsp;</font><font style='background-color: #FC5FC0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FC5FC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FC5FC0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_MAGENTA = -0x1.f1313ep125F;
    static { NAMED.put("bright pure magenta", -0x1.f1313ep125F); LIST.add(-0x1.f1313ep125F); }

    /**
     * This color constant "deep red magenta" has RGBA8888 code {@code B23664FF}, L 0.40784314, A 0.5803922, B 0.49803922,
     * alpha 1.0, red 0.6975365, green 0.21452528, blue 0.3929724, hue 0.0, saturation 0.7118644, and chroma 0.16020387.
     * It can be represented as a packed float with the constant {@code -0x1.ff28dp125F}.
     * <pre>
     * <font style='background-color: #B23664;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B23664; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B23664;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B23664'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B23664'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B23664'>&nbsp;@&nbsp;</font><font style='background-color: #B23664; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B23664;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B23664; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED_MAGENTA = -0x1.ff28dp125F;
    static { NAMED.put("deep red magenta", -0x1.ff28dp125F); LIST.add(-0x1.ff28dp125F); }

    /**
     * This color constant "true red magenta" has RGBA8888 code {@code DB5684FF}, L 0.53333336, A 0.5803922, B 0.49803922,
     * alpha 1.0, red 0.8576026, green 0.33820117, blue 0.5170374, hue 0.0, saturation 0.6268657, and chroma 0.16020387.
     * It can be represented as a packed float with the constant {@code -0x1.ff291p125F}.
     * <pre>
     * <font style='background-color: #DB5684;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB5684; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB5684;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DB5684'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DB5684'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DB5684'>&nbsp;@&nbsp;</font><font style='background-color: #DB5684; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB5684;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB5684; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_RED_MAGENTA = -0x1.ff291p125F;
    static { NAMED.put("true red magenta", -0x1.ff291p125F); LIST.add(-0x1.ff291p125F); }

    /**
     * This color constant "bright red magenta" has RGBA8888 code {@code FF75A4FF}, L 0.654902, A 0.5803922, B 0.49803922,
     * alpha 1.0, red 1.0, green 0.4549299, blue 0.637419, hue 0.0, saturation 0.95454544, and chroma 0.16020387.
     * It can be represented as a packed float with the constant {@code -0x1.ff294ep125F}.
     * <pre>
     * <font style='background-color: #FF75A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF75A4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF75A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF75A4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF75A4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF75A4'>&nbsp;@&nbsp;</font><font style='background-color: #FF75A4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF75A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF75A4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED_MAGENTA = -0x1.ff294ep125F;
    static { NAMED.put("bright red magenta", -0x1.ff294ep125F); LIST.add(-0x1.ff294ep125F); }

    /**
     * This color constant "deep magenta red" has RGBA8888 code {@code A52A40FF}, L 0.35686275, A 0.5764706, B 0.5176471,
     * alpha 1.0, red 0.6446623, green 0.16504818, blue 0.25113994, hue 0.03898957, saturation 0.77794445, and chroma 0.15634763.
     * It can be represented as a packed float with the constant {@code -0x1.0926b6p126F}.
     * <pre>
     * <font style='background-color: #A52A40;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A52A40; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A52A40;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A52A40'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A52A40'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A52A40'>&nbsp;@&nbsp;</font><font style='background-color: #A52A40; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A52A40;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A52A40; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_MAGENTA_RED = -0x1.0926b6p126F;
    static { NAMED.put("deep magenta red", -0x1.0926b6p126F); LIST.add(-0x1.0926b6p126F); }

    /**
     * This color constant "true magenta red" has RGBA8888 code {@code C34255FF}, L 0.44705883, A 0.5764706, B 0.5176471,
     * alpha 1.0, red 0.76345956, green 0.25836027, blue 0.33351025, hue 0.03898957, saturation 0.67591894, and chroma 0.15634763.
     * It can be represented as a packed float with the constant {@code -0x1.0926e4p126F}.
     * <pre>
     * <font style='background-color: #C34255;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C34255; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C34255;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C34255'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C34255'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C34255'>&nbsp;@&nbsp;</font><font style='background-color: #C34255; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C34255;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C34255; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_MAGENTA_RED = -0x1.0926e4p126F;
    static { NAMED.put("true magenta red", -0x1.0926e4p126F); LIST.add(-0x1.0926e4p126F); }

    /**
     * This color constant "bright magenta red" has RGBA8888 code {@code DF5769FF}, L 0.53333336, A 0.5764706, B 0.5176471,
     * alpha 1.0, red 0.8738949, green 0.3432425, blue 0.41315553, hue 0.03898957, saturation 0.665017, and chroma 0.15634763.
     * It can be represented as a packed float with the constant {@code -0x1.09271p126F}.
     * <pre>
     * <font style='background-color: #DF5769;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DF5769; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DF5769;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DF5769'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DF5769'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DF5769'>&nbsp;@&nbsp;</font><font style='background-color: #DF5769; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DF5769;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DF5769; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA_RED = -0x1.09271p126F;
    static { NAMED.put("bright magenta red", -0x1.09271p126F); LIST.add(-0x1.09271p126F); }

    /**
     * This color constant "bold pure red" has RGBA8888 code {@code FE0000FF}, L 0.49411765, A 0.6117647, B 0.56078434,
     * alpha 1.0, red 1.0, green 0.0, blue 0.0, hue 0.0802405, saturation 0.98868626, and chroma 0.25345513.
     * It can be represented as a packed float with the constant {@code -0x1.1f38fcp126F}.
     * <pre>
     * <font style='background-color: #FE0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FE0000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FE0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FE0000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FE0000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FE0000'>&nbsp;@&nbsp;</font><font style='background-color: #FE0000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FE0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FE0000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_RED = -0x1.1f38fcp126F;
    static { NAMED.put("bold pure red", -0x1.1f38fcp126F); LIST.add(-0x1.1f38fcp126F); }

    /**
     * This color constant "bold brown red" has RGBA8888 code {@code DF4E31FF}, L 0.5058824, A 0.57254905, B 0.54901963,
     * alpha 1.0, red 0.87464416, green 0.3062282, blue 0.19170263, hue 0.09550095, saturation 0.73084855, and chroma 0.17443058.
     * It can be represented as a packed float with the constant {@code -0x1.192502p126F}.
     * <pre>
     * <font style='background-color: #DF4E31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DF4E31; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DF4E31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DF4E31'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DF4E31'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DF4E31'>&nbsp;@&nbsp;</font><font style='background-color: #DF4E31; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DF4E31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DF4E31; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_BROWN_RED = -0x1.192502p126F;
    static { NAMED.put("bold brown red", -0x1.192502p126F); LIST.add(-0x1.192502p126F); }

    /**
     * This color constant "bold red brown" has RGBA8888 code {@code D56C4AFF}, L 0.54901963, A 0.54901963, B 0.5411765,
     * alpha 1.0, red 0.83475095, green 0.42543536, blue 0.29092935, hue 0.11176766, saturation 0.58722025, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.151918p126F}.
     * <pre>
     * <font style='background-color: #D56C4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D56C4A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D56C4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D56C4A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D56C4A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D56C4A'>&nbsp;@&nbsp;</font><font style='background-color: #D56C4A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D56C4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D56C4A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_RED_BROWN = -0x1.151918p126F;
    static { NAMED.put("bold red brown", -0x1.151918p126F); LIST.add(-0x1.151918p126F); }

    /**
     * This color constant "bold pure brown" has RGBA8888 code {@code D77951FF}, L 0.5803922, A 0.5411765, B 0.5411765,
     * alpha 1.0, red 0.8431029, green 0.476237, blue 0.320125, hue 0.124999985, saturation 0.5761611, and chroma 0.116009705.
     * It can be represented as a packed float with the constant {@code -0x1.151528p126F}.
     * <pre>
     * <font style='background-color: #D77951;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D77951; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D77951;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D77951'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D77951'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D77951'>&nbsp;@&nbsp;</font><font style='background-color: #D77951; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D77951;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D77951; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_BROWN = -0x1.151528p126F;
    static { NAMED.put("bold pure brown", -0x1.151528p126F); LIST.add(-0x1.151528p126F); }

    /**
     * This color constant "bold orange brown" has RGBA8888 code {@code D28054FF}, L 0.5882353, A 0.53333336, B 0.5411765,
     * alpha 1.0, red 0.82208866, green 0.5032115, blue 0.32839048, hue 0.14086276, saturation 0.56850684, and chroma 0.105541065.
     * It can be represented as a packed float with the constant {@code -0x1.15112cp126F}.
     * <pre>
     * <font style='background-color: #D28054;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D28054; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D28054;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D28054'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D28054'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D28054'>&nbsp;@&nbsp;</font><font style='background-color: #D28054; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D28054;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D28054; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_ORANGE_BROWN = -0x1.15112cp126F;
    static { NAMED.put("bold orange brown", -0x1.15112cp126F); LIST.add(-0x1.15112cp126F); }

    /**
     * This color constant "bold brown orange" has RGBA8888 code {@code E68546FF}, L 0.62352943, A 0.5372549, B 0.5529412,
     * alpha 1.0, red 0.89984435, green 0.5215227, blue 0.2742229, hue 0.15128423, saturation 0.68818605, and chroma 0.12896542.
     * It can be represented as a packed float with the constant {@code -0x1.1b133ep126F}.
     * <pre>
     * <font style='background-color: #E68546;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E68546; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E68546;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E68546'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E68546'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E68546'>&nbsp;@&nbsp;</font><font style='background-color: #E68546; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E68546;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E68546; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_BROWN_ORANGE = -0x1.1b133ep126F;
    static { NAMED.put("bold brown orange", -0x1.1b133ep126F); LIST.add(-0x1.1b133ep126F); }

    /**
     * This color constant "bold pure orange" has RGBA8888 code {@code F48000FF}, L 0.627451, A 0.5411765, B 0.57254905,
     * alpha 1.0, red 0.9557283, green 0.50116026, blue 0.0, hue 0.16647616, saturation 0.93423396, and chroma 0.16618787.
     * It can be represented as a packed float with the constant {@code -0x1.25154p126F}.
     * <pre>
     * <font style='background-color: #F48000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F48000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F48000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F48000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F48000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F48000'>&nbsp;@&nbsp;</font><font style='background-color: #F48000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F48000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F48000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_ORANGE = -0x1.25154p126F;
    static { NAMED.put("bold pure orange", -0x1.25154p126F); LIST.add(-0x1.25154p126F); }

    /**
     * This color constant "bold saffron orange" has RGBA8888 code {@code F09A20FF}, L 0.68235296, A 0.52156866, B 0.57254905,
     * alpha 1.0, red 0.94222456, green 0.6059686, blue 0.13109295, hue 0.20131786, saturation 0.90567535, and chroma 0.15078327.
     * It can be represented as a packed float with the constant {@code -0x1.250b5cp126F}.
     * <pre>
     * <font style='background-color: #F09A20;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F09A20; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F09A20;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F09A20'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F09A20'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F09A20'>&nbsp;@&nbsp;</font><font style='background-color: #F09A20; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F09A20;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F09A20; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_SAFFRON_ORANGE = -0x1.250b5cp126F;
    static { NAMED.put("bold saffron orange", -0x1.250b5cp126F); LIST.add(-0x1.250b5cp126F); }

    /**
     * This color constant "bold orange saffron" has RGBA8888 code {@code F9B92EFF}, L 0.76862746, A 0.5058824, B 0.5764706,
     * alpha 1.0, red 0.97851336, green 0.72705805, blue 0.1853812, hue 0.23413725, saturation 0.8739022, and chroma 0.15279381.
     * It can be represented as a packed float with the constant {@code -0x1.270388p126F}.
     * <pre>
     * <font style='background-color: #F9B92E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F9B92E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F9B92E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F9B92E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F9B92E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F9B92E'>&nbsp;@&nbsp;</font><font style='background-color: #F9B92E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F9B92E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F9B92E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_ORANGE_SAFFRON = -0x1.270388p126F;
    static { NAMED.put("bold orange saffron", -0x1.270388p126F); LIST.add(-0x1.270388p126F); }

    /**
     * This color constant "bold pure saffron" has RGBA8888 code {@code FECE00FF}, L 0.8235294, A 0.49019608, B 0.5882353,
     * alpha 1.0, red 0.99039423, green 0.8113747, blue 0.0, hue 0.26380482, saturation 0.96194965, and chroma 0.17686298.
     * It can be represented as a packed float with the constant {@code -0x1.2cfba4p126F}.
     * <pre>
     * <font style='background-color: #FECE00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FECE00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FECE00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FECE00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FECE00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FECE00'>&nbsp;@&nbsp;</font><font style='background-color: #FECE00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FECE00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FECE00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_SAFFRON = -0x1.2cfba4p126F;
    static { NAMED.put("bold pure saffron", -0x1.2cfba4p126F); LIST.add(-0x1.2cfba4p126F); }

    /**
     * This color constant "bold yellow saffron" has RGBA8888 code {@code FFE335FF}, L 0.88235295, A 0.48235294, B 0.58431375,
     * alpha 1.0, red 1.0, green 0.8913277, blue 0.21353011, hue 0.2786246, saturation 0.8944272, and chroma 0.17160846.
     * It can be represented as a packed float with the constant {@code -0x1.2af7c2p126F}.
     * <pre>
     * <font style='background-color: #FFE335;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFE335; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFE335;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFE335'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFE335'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFE335'>&nbsp;@&nbsp;</font><font style='background-color: #FFE335; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFE335;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFE335; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_YELLOW_SAFFRON = -0x1.2af7c2p126F;
    static { NAMED.put("bold yellow saffron", -0x1.2af7c2p126F); LIST.add(-0x1.2af7c2p126F); }

    /**
     * This color constant "bold saffron yellow" has RGBA8888 code {@code FFEF30FF}, L 0.9137255, A 0.4745098, B 0.5882353,
     * alpha 1.0, red 1.0, green 0.93869233, blue 0.19289875, hue 0.29061353, saturation 0.89697087, and chroma 0.18296935.
     * It can be represented as a packed float with the constant {@code -0x1.2cf3d2p126F}.
     * <pre>
     * <font style='background-color: #FFEF30;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFEF30; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFEF30;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFEF30'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFEF30'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFEF30'>&nbsp;@&nbsp;</font><font style='background-color: #FFEF30; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFEF30;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFEF30; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_SAFFRON_YELLOW = -0x1.2cf3d2p126F;
    static { NAMED.put("bold saffron yellow", -0x1.2cf3d2p126F); LIST.add(-0x1.2cf3d2p126F); }

    /**
     * This color constant "bold pure yellow" has RGBA8888 code {@code FFFC00FF}, L 0.94509804, A 0.4627451, B 0.59607846,
     * alpha 1.0, red 0.991993, green 0.99240994, blue 0.04535171, hue 0.30499688, saturation 0.94895214, and chroma 0.20529193.
     * It can be represented as a packed float with the constant {@code -0x1.30ede2p126F}.
     * <pre>
     * <font style='background-color: #FFFC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFC00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFC00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFC00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFC00'>&nbsp;@&nbsp;</font><font style='background-color: #FFFC00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFC00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_YELLOW = -0x1.30ede2p126F;
    static { NAMED.put("bold pure yellow", -0x1.30ede2p126F); LIST.add(-0x1.30ede2p126F); }

    /**
     * This color constant "bold lime yellow" has RGBA8888 code {@code E6FD35FF}, L 0.9254902, A 0.45490196, B 0.5882353,
     * alpha 1.0, red 0.90599704, green 0.9930301, blue 0.21217744, hue 0.3209999, saturation 0.89456487, and chroma 0.1974106.
     * It can be represented as a packed float with the constant {@code -0x1.2ce9d8p126F}.
     * <pre>
     * <font style='background-color: #E6FD35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E6FD35; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E6FD35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E6FD35'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E6FD35'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E6FD35'>&nbsp;@&nbsp;</font><font style='background-color: #E6FD35; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E6FD35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E6FD35; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_LIME_YELLOW = -0x1.2ce9d8p126F;
    static { NAMED.put("bold lime yellow", -0x1.2ce9d8p126F); LIST.add(-0x1.2ce9d8p126F); }

    /**
     * This color constant "bold yellow lime" has RGBA8888 code {@code D5FA2DFF}, L 0.9019608, A 0.44705883, B 0.5882353,
     * alpha 1.0, red 0.83601224, green 0.9845711, blue 0.18318097, hue 0.33187747, saturation 0.91102374, and chroma 0.2049944.
     * It can be represented as a packed float with the constant {@code -0x1.2ce5ccp126F}.
     * <pre>
     * <font style='background-color: #D5FA2D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5FA2D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5FA2D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D5FA2D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D5FA2D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D5FA2D'>&nbsp;@&nbsp;</font><font style='background-color: #D5FA2D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5FA2D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5FA2D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_YELLOW_LIME = -0x1.2ce5ccp126F;
    static { NAMED.put("bold yellow lime", -0x1.2ce5ccp126F); LIST.add(-0x1.2ce5ccp126F); }

    /**
     * This color constant "bold pure lime" has RGBA8888 code {@code BCFC00FF}, L 0.88235295, A 0.43137255, B 0.5921569,
     * alpha 1.0, red 0.72904927, green 0.9928923, blue 0.032661963, hue 0.34808668, saturation 0.94873816, and chroma 0.22890759.
     * It can be represented as a packed float with the constant {@code -0x1.2eddc2p126F}.
     * <pre>
     * <font style='background-color: #BCFC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BCFC00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BCFC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BCFC00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BCFC00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BCFC00'>&nbsp;@&nbsp;</font><font style='background-color: #BCFC00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BCFC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BCFC00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_LIME = -0x1.2eddc2p126F;
    static { NAMED.put("bold pure lime", -0x1.2eddc2p126F); LIST.add(-0x1.2eddc2p126F); }

    /**
     * This color constant "bold green lime" has RGBA8888 code {@code A1FD33FF}, L 0.8666667, A 0.42352942, B 0.58431375,
     * alpha 1.0, red 0.6336886, green 0.99450105, blue 0.20635083, hue 0.36337522, saturation 0.8944272, and chroma 0.22676446.
     * It can be represented as a packed float with the constant {@code -0x1.2ad9bap126F}.
     * <pre>
     * <font style='background-color: #A1FD33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A1FD33; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A1FD33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A1FD33'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A1FD33'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A1FD33'>&nbsp;@&nbsp;</font><font style='background-color: #A1FD33; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A1FD33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A1FD33; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_GREEN_LIME = -0x1.2ad9bap126F;
    static { NAMED.put("bold green lime", -0x1.2ad9bap126F); LIST.add(-0x1.2ad9bap126F); }

    /**
     * This color constant "bold lime green" has RGBA8888 code {@code 82FB2AFF}, L 0.8392157, A 0.4117647, B 0.58431375,
     * alpha 1.0, red 0.5152992, green 0.98622996, blue 0.17324422, hue 0.375, saturation 0.8889342, and chroma 0.24313073.
     * It can be represented as a packed float with the constant {@code -0x1.2ad3acp126F}.
     * <pre>
     * <font style='background-color: #82FB2A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #82FB2A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #82FB2A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #82FB2A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #82FB2A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #82FB2A'>&nbsp;@&nbsp;</font><font style='background-color: #82FB2A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #82FB2A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #82FB2A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_LIME_GREEN = -0x1.2ad3acp126F;
    static { NAMED.put("bold lime green", -0x1.2ad3acp126F); LIST.add(-0x1.2ad3acp126F); }

    /**
     * This color constant "bold pure green" has RGBA8888 code {@code 37FF00FF}, L 0.8156863, A 0.38431373, B 0.5882353,
     * alpha 1.0, red 0.17432547, green 1.0, blue 0.0, hue 0.3932832, saturation 0.987027, and chroma 0.28985322.
     * It can be represented as a packed float with the constant {@code -0x1.2cc5ap126F}.
     * <pre>
     * <font style='background-color: #37FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #37FF00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #37FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #37FF00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #37FF00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #37FF00'>&nbsp;@&nbsp;</font><font style='background-color: #37FF00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #37FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #37FF00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_GREEN = -0x1.2cc5ap126F;
    static { NAMED.put("bold pure green", -0x1.2cc5ap126F); LIST.add(-0x1.2cc5ap126F); }

    /**
     * This color constant "bold cyan green" has RGBA8888 code {@code 27FA9CFF}, L 0.8156863, A 0.40784314, B 0.53333336,
     * alpha 1.0, red 0.15412499, green 0.9803728, blue 0.61007637, hue 0.44063717, saturation 0.88207775, and chroma 0.19523436.
     * It can be represented as a packed float with the constant {@code -0x1.10d1ap126F}.
     * <pre>
     * <font style='background-color: #27FA9C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #27FA9C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #27FA9C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #27FA9C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #27FA9C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #27FA9C'>&nbsp;@&nbsp;</font><font style='background-color: #27FA9C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #27FA9C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #27FA9C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_CYAN_GREEN = -0x1.10d1ap126F;
    static { NAMED.put("bold cyan green", -0x1.10d1ap126F); LIST.add(-0x1.10d1ap126F); }

    /**
     * This color constant "bold green cyan" has RGBA8888 code {@code 29FBD4FF}, L 0.8392157, A 0.41960785, B 0.5019608,
     * alpha 1.0, red 0.17059644, green 0.9866611, blue 0.8323903, hue 0.4920489, saturation 0.88999933, and chroma 0.16020387.
     * It can be represented as a packed float with the constant {@code -0x1.00d7acp126F}.
     * <pre>
     * <font style='background-color: #29FBD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #29FBD4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #29FBD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #29FBD4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #29FBD4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #29FBD4'>&nbsp;@&nbsp;</font><font style='background-color: #29FBD4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #29FBD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #29FBD4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_GREEN_CYAN = -0x1.00d7acp126F;
    static { NAMED.put("bold green cyan", -0x1.00d7acp126F); LIST.add(-0x1.00d7acp126F); }

    /**
     * This color constant "bold pure cyan" has RGBA8888 code {@code 00FBFAFF}, L 0.84705883, A 0.42352942, B 0.47843137,
     * alpha 1.0, red 0.0, green 0.9847105, blue 0.9843701, hue 0.54095435, saturation 0.9583845, and chroma 0.1582875.
     * It can be represented as a packed float with the constant {@code -0x1.f4d9bp125F}.
     * <pre>
     * <font style='background-color: #00FBFA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FBFA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FBFA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FBFA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FBFA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FBFA'>&nbsp;@&nbsp;</font><font style='background-color: #00FBFA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FBFA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FBFA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_CYAN = -0x1.f4d9bp125F;
    static { NAMED.put("bold pure cyan", -0x1.f4d9bp125F); LIST.add(-0x1.f4d9bp125F); }

    /**
     * This color constant "bold blue cyan" has RGBA8888 code {@code 1FCFFBFF}, L 0.72156864, A 0.44313726, B 0.45490196,
     * alpha 1.0, red 0.122156955, green 0.811228, blue 0.98199284, hue 0.6059923, saturation 0.91305095, and chroma 0.14458403.
     * It can be represented as a packed float with the constant {@code -0x1.e8e37p125F}.
     * <pre>
     * <font style='background-color: #1FCFFB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1FCFFB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1FCFFB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1FCFFB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1FCFFB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1FCFFB'>&nbsp;@&nbsp;</font><font style='background-color: #1FCFFB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1FCFFB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1FCFFB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_BLUE_CYAN = -0x1.e8e37p125F;
    static { NAMED.put("bold blue cyan", -0x1.e8e37p125F); LIST.add(-0x1.e8e37p125F); }

    /**
     * This color constant "bold cyan blue" has RGBA8888 code {@code 129FF1FF}, L 0.5803922, A 0.45882353, B 0.43137255,
     * alpha 1.0, red 0.07245364, green 0.6241355, blue 0.9437182, hue 0.66537344, saturation 0.91735274, and chroma 0.15944009.
     * It can be represented as a packed float with the constant {@code -0x1.dceb28p125F}.
     * <pre>
     * <font style='background-color: #129FF1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #129FF1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #129FF1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #129FF1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #129FF1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #129FF1'>&nbsp;@&nbsp;</font><font style='background-color: #129FF1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #129FF1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #129FF1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_CYAN_BLUE = -0x1.dceb28p125F;
    static { NAMED.put("bold cyan blue", -0x1.dceb28p125F); LIST.add(-0x1.dceb28p125F); }

    /**
     * This color constant "bold pure blue" has RGBA8888 code {@code 0C00FFFF}, L 0.30588236, A 0.48235294, B 0.34117648,
     * alpha 1.0, red 0.034525402, green 0.0, blue 1.0, hue 0.73413724, saturation 0.98047566, and chroma 0.31835338.
     * It can be represented as a packed float with the constant {@code -0x1.aef69cp125F}.
     * <pre>
     * <font style='background-color: #0C00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0C00FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0C00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0C00FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0C00FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0C00FF'>&nbsp;@&nbsp;</font><font style='background-color: #0C00FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0C00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0C00FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_BLUE = -0x1.aef69cp125F;
    static { NAMED.put("bold pure blue", -0x1.aef69cp125F); LIST.add(-0x1.aef69cp125F); }

    /**
     * This color constant "bold violet blue" has RGBA8888 code {@code 3B1FF0FF}, L 0.3254902, A 0.50980395, B 0.36078432,
     * alpha 1.0, red 0.23281193, green 0.121663995, blue 0.9397669, hue 0.7636086, saturation 0.889325, and chroma 0.27803063.
     * It can be represented as a packed float with the constant {@code -0x1.b904a6p125F}.
     * <pre>
     * <font style='background-color: #3B1FF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B1FF0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B1FF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B1FF0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B1FF0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B1FF0'>&nbsp;@&nbsp;</font><font style='background-color: #3B1FF0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B1FF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B1FF0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_VIOLET_BLUE = -0x1.b904a6p125F;
    static { NAMED.put("bold violet blue", -0x1.b904a6p125F); LIST.add(-0x1.b904a6p125F); }

    /**
     * This color constant "bold blue violet" has RGBA8888 code {@code 5D1CEFFF}, L 0.3529412, A 0.5372549, B 0.36862746,
     * alpha 1.0, red 0.36586162, green 0.1110181, blue 0.93609864, hue 0.79682887, saturation 0.8956333, and chroma 0.27203885.
     * It can be represented as a packed float with the constant {@code -0x1.bd12b4p125F}.
     * <pre>
     * <font style='background-color: #5D1CEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5D1CEF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5D1CEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5D1CEF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5D1CEF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5D1CEF'>&nbsp;@&nbsp;</font><font style='background-color: #5D1CEF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5D1CEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5D1CEF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_BLUE_VIOLET = -0x1.bd12b4p125F;
    static { NAMED.put("bold blue violet", -0x1.bd12b4p125F); LIST.add(-0x1.bd12b4p125F); }

    /**
     * This color constant "bold pure violet" has RGBA8888 code {@code 7E00FFFF}, L 0.39607844, A 0.5647059, B 0.3647059,
     * alpha 1.0, red 0.49905404, green 0.0, blue 1.0, hue 0.8237918, saturation 0.9873547, and chroma 0.29877067.
     * It can be represented as a packed float with the constant {@code -0x1.bb20cap125F}.
     * <pre>
     * <font style='background-color: #7E00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E00FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7E00FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7E00FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7E00FF'>&nbsp;@&nbsp;</font><font style='background-color: #7E00FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E00FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_VIOLET = -0x1.bb20cap125F;
    static { NAMED.put("bold pure violet", -0x1.bb20cap125F); LIST.add(-0x1.bb20cap125F); }

    /**
     * This color constant "bold purple violet" has RGBA8888 code {@code 8D1EEFFF}, L 0.4117647, A 0.57254905, B 0.38431373,
     * alpha 1.0, red 0.5538034, green 0.1183785, blue 0.93512666, hue 0.8423103, saturation 0.90051615, and chroma 0.27203885.
     * It can be represented as a packed float with the constant {@code -0x1.c524d2p125F}.
     * <pre>
     * <font style='background-color: #8D1EEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8D1EEF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8D1EEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8D1EEF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8D1EEF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8D1EEF'>&nbsp;@&nbsp;</font><font style='background-color: #8D1EEF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8D1EEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8D1EEF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURPLE_VIOLET = -0x1.c524d2p125F;
    static { NAMED.put("bold purple violet", -0x1.c524d2p125F); LIST.add(-0x1.c524d2p125F); }

    /**
     * This color constant "bold violet purple" has RGBA8888 code {@code A120EEFF}, L 0.4392157, A 0.58431375, B 0.39215687,
     * alpha 1.0, red 0.62920237, green 0.12839854, blue 0.9311905, hue 0.8588157, saturation 0.89302945, and chroma 0.2727111.
     * It can be represented as a packed float with the constant {@code -0x1.c92aep125F}.
     * <pre>
     * <font style='background-color: #A120EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A120EE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A120EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A120EE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A120EE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A120EE'>&nbsp;@&nbsp;</font><font style='background-color: #A120EE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A120EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A120EE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_VIOLET_PURPLE = -0x1.c92aep125F;
    static { NAMED.put("bold violet purple", -0x1.c92aep125F); LIST.add(-0x1.c92aep125F); }

    /**
     * This color constant "bold pure purple" has RGBA8888 code {@code C100FFFF}, L 0.49019608, A 0.60784316, B 0.39215687,
     * alpha 1.0, red 0.7658322, green 0.0, blue 0.99350595, hue 0.8778934, saturation 0.9724325, and chroma 0.30383494.
     * It can be represented as a packed float with the constant {@code -0x1.c936fap125F}.
     * <pre>
     * <font style='background-color: #C100FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C100FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C100FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C100FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C100FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C100FF'>&nbsp;@&nbsp;</font><font style='background-color: #C100FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C100FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C100FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_PURPLE = -0x1.c936fap125F;
    static { NAMED.put("bold pure purple", -0x1.c936fap125F); LIST.add(-0x1.c936fap125F); }

    /**
     * This color constant "bold magenta purple" has RGBA8888 code {@code E42BFAFF}, L 0.5568628, A 0.6156863, B 0.4117647,
     * alpha 1.0, red 0.894085, green 0.16948627, blue 0.9769091, hue 0.8992949, saturation 0.8964374, and chroma 0.28985322.
     * It can be represented as a packed float with the constant {@code -0x1.d33b1cp125F}.
     * <pre>
     * <font style='background-color: #E42BFA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E42BFA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E42BFA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E42BFA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E42BFA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E42BFA'>&nbsp;@&nbsp;</font><font style='background-color: #E42BFA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E42BFA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E42BFA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_MAGENTA_PURPLE = -0x1.d33b1cp125F;
    static { NAMED.put("bold magenta purple", -0x1.d33b1cp125F); LIST.add(-0x1.d33b1cp125F); }

    /**
     * This color constant "bold purple magenta" has RGBA8888 code {@code FC27E0FF}, L 0.5764706, A 0.627451, B 0.43529412,
     * alpha 1.0, red 0.98670936, green 0.15596794, blue 0.8757112, hue 0.928149, saturation 0.89449364, and chroma 0.28475463.
     * It can be represented as a packed float with the constant {@code -0x1.df4126p125F}.
     * <pre>
     * <font style='background-color: #FC27E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FC27E0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FC27E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FC27E0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FC27E0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FC27E0'>&nbsp;@&nbsp;</font><font style='background-color: #FC27E0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FC27E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FC27E0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURPLE_MAGENTA = -0x1.df4126p125F;
    static { NAMED.put("bold purple magenta", -0x1.df4126p125F); LIST.add(-0x1.df4126p125F); }

    /**
     * This color constant "bold pure magenta" has RGBA8888 code {@code FF00BDFF}, L 0.54901963, A 0.63529414, B 0.45490196,
     * alpha 1.0, red 1.0, green 0.0, blue 0.7417412, hue 0.9515356, saturation 0.9654704, and chroma 0.28411087.
     * It can be represented as a packed float with the constant {@code -0x1.e94518p125F}.
     * <pre>
     * <font style='background-color: #FF00BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF00BD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF00BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF00BD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF00BD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF00BD'>&nbsp;@&nbsp;</font><font style='background-color: #FF00BD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF00BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF00BD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_MAGENTA = -0x1.e94518p125F;
    static { NAMED.put("bold pure magenta", -0x1.e94518p125F); LIST.add(-0x1.e94518p125F); }

    /**
     * This color constant "bold red magenta" has RGBA8888 code {@code F82785FF}, L 0.5254902, A 0.61960787, B 0.49411765,
     * alpha 1.0, red 0.9713087, green 0.15387148, blue 0.52174187, hue 0.99486774, saturation 0.886175, and chroma 0.23856924.
     * It can be represented as a packed float with the constant {@code -0x1.fd3d0cp125F}.
     * <pre>
     * <font style='background-color: #F82785;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F82785; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F82785;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F82785'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F82785'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F82785'>&nbsp;@&nbsp;</font><font style='background-color: #F82785; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F82785;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F82785; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_RED_MAGENTA = -0x1.fd3d0cp125F;
    static { NAMED.put("bold red magenta", -0x1.fd3d0cp125F); LIST.add(-0x1.fd3d0cp125F); }

    /**
     * This color constant "bold magenta red" has RGBA8888 code {@code F82657FF}, L 0.50980395, A 0.6117647, B 0.5254902,
     * alpha 1.0, red 0.9699314, green 0.15198661, blue 0.3419019, hue 0.037695654, saturation 0.8905334, and chroma 0.22837369.
     * It can be represented as a packed float with the constant {@code -0x1.0d3904p126F}.
     * <pre>
     * <font style='background-color: #F82657;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F82657; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F82657;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F82657'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F82657'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F82657'>&nbsp;@&nbsp;</font><font style='background-color: #F82657; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F82657;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F82657; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_MAGENTA_RED = -0x1.0d3904p126F;
    static { NAMED.put("bold magenta red", -0x1.0d3904p126F); LIST.add(-0x1.0d3904p126F); }

    /**
     * All names for colors in this palette, in alphabetical order. You can fetch the corresponding packed float color
     * by looking up a name in {@link #NAMED}.
     */
    public static final Array<String> NAMES = NAMED.keys().toArray();
    static { NAMES.sort(); }
    /**
     * All names for colors in this palette, sorted by hue from red to yellow to green to blue. You can fetch the
     * corresponding packed float color by looking up a name in {@link #NAMED}.
     */
    public static final Array<String> NAMES_BY_HUE = new Array<>(NAMES);
    /**
     * All names for colors in this palette, sorted by lightness from black to white. You can fetch the
     * corresponding packed float color by looking up a name in {@link #NAMED}.
     */
    public static final Array<String> NAMES_BY_LIGHTNESS = new Array<>(NAMES);
    static {
        NAMES_BY_HUE.sort(new Comparator<String>() {
            public int compare(String o1, String o2) {
                final float c1 = NAMED.get(o1, YAM_TRANSPARENT), c2 = NAMED.get(o2, YAM_TRANSPARENT);
                if(ColorTools.alphaInt(c1) < 128) return -10000;
                else if(ColorTools.alphaInt(c2) < 128) return 10000;
                final float s1 = ColorTools.oklabSaturation(c1), s2 = ColorTools.oklabSaturation(c2);
                if(s1 <= 0.05f && s2 > 0.05f)
                    return -1000;
                else if(s1 > 0.05f && s2 <= 0.05f)
                    return 1000;
                else if(s1 <= 0.05f && s2 <= 0.05f)
                    return (int)Math.signum(ColorTools.channelL(c1) - ColorTools.channelL(c2));
                else
                    return 2 * (int)Math.signum(ColorTools.oklabHue(c1) - ColorTools.oklabHue(c2))
                            + (int)Math.signum(ColorTools.channelL(c1) - ColorTools.channelL(c2));
            }
        });
        NAMES_BY_LIGHTNESS.sort(new Comparator<String>() {
            public int compare(String o1, String o2) {
                return Float.compare(ColorTools.channelL(NAMED.get(o1, YAM_TRANSPARENT)), ColorTools.channelL(NAMED.get(o2, YAM_TRANSPARENT)));
            }
        });
    }
}
