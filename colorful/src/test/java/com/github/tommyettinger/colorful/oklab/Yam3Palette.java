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
 * palette, Yam3, that is designed to be as consistent as possible in its support for hues while keeping lots of
 * grayscale, desaturated, and mid-saturation colors, and to have a coherent naming system. This is the successor to
 * Yam (2), and may still be adjusted as needed (this may or may not be in another version like Yam4). The original Yam
 * had too many colors that were indistinguishable from grayscale, despite being named like they had a non-gray color
 * included in their mix. Where Yam and Yam2 share the same shape and distribution of colors, Yam3 has more possible
 * lightness levels for the 25% and 75% saturation colors, and fewer total hues for the 50% and 100% saturation colors.
 * All have 255 opaque colors, plus pure transparent.
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
public class Yam3Palette {
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
     * This color constant "black red" has RGBA8888 code {@code 61322AFF}, L 0.2509804, A 0.5294118, B 0.5176471,
     * alpha 1.0, red 0.37697697, green 0.19471823, blue 0.16208121, hue 0.088903844, saturation 0.4387898, and chroma 0.068331465.
     * It can be represented as a packed float with the constant {@code -0x1.090e8p126F}.
     * <pre>
     * <font style='background-color: #61322A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #61322A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #61322A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #61322A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #61322A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #61322A'>&nbsp;@&nbsp;</font><font style='background-color: #61322A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #61322A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #61322A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_RED = -0x1.090e8p126F;
    static { NAMED.put("black red", -0x1.090e8p126F); LIST.add(-0x1.090e8p126F); }

    /**
     * This color constant "lead red" has RGBA8888 code {@code 7F4B41FF}, L 0.3529412, A 0.5294118, B 0.5176471,
     * alpha 1.0, red 0.49723348, green 0.29313776, blue 0.25458688, hue 0.088903844, saturation 0.3559993, and chroma 0.068331465.
     * It can be represented as a packed float with the constant {@code -0x1.090eb4p126F}.
     * <pre>
     * <font style='background-color: #7F4B41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F4B41; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F4B41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7F4B41'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7F4B41'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7F4B41'>&nbsp;@&nbsp;</font><font style='background-color: #7F4B41; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F4B41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F4B41; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_RED = -0x1.090eb4p126F;
    static { NAMED.put("lead red", -0x1.090eb4p126F); LIST.add(-0x1.090eb4p126F); }

    /**
     * This color constant "gray red" has RGBA8888 code {@code 9F665BFF}, L 0.4627451, A 0.5294118, B 0.5176471,
     * alpha 1.0, red 0.6231744, green 0.39937195, blue 0.35578638, hue 0.088903844, saturation 0.2948119, and chroma 0.068331465.
     * It can be represented as a packed float with the constant {@code -0x1.090eecp126F}.
     * <pre>
     * <font style='background-color: #9F665B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9F665B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9F665B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9F665B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9F665B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9F665B'>&nbsp;@&nbsp;</font><font style='background-color: #9F665B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9F665B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9F665B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_RED = -0x1.090eecp126F;
    static { NAMED.put("gray red", -0x1.090eecp126F); LIST.add(-0x1.090eecp126F); }

    /**
     * This color constant "silver red" has RGBA8888 code {@code C18377FF}, L 0.5803922, A 0.5294118, B 0.5176471,
     * alpha 1.0, red 0.7554868, green 0.51354843, blue 0.4654865, hue 0.088903844, saturation 0.36284542, and chroma 0.068331465.
     * It can be represented as a packed float with the constant {@code -0x1.090f28p126F}.
     * <pre>
     * <font style='background-color: #C18377;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C18377; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C18377;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C18377'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C18377'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C18377'>&nbsp;@&nbsp;</font><font style='background-color: #C18377; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C18377;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C18377; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_RED = -0x1.090f28p126F;
    static { NAMED.put("silver red", -0x1.090f28p126F); LIST.add(-0x1.090f28p126F); }

    /**
     * This color constant "white red" has RGBA8888 code {@code E8A598FF}, L 0.7176471, A 0.5294118, B 0.5176471,
     * alpha 1.0, red 0.90751785, green 0.647154, blue 0.5946492, hue 0.088903844, saturation 0.60864395, and chroma 0.068331465.
     * It can be represented as a packed float with the constant {@code -0x1.090f6ep126F}.
     * <pre>
     * <font style='background-color: #E8A598;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E8A598; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E8A598;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E8A598'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E8A598'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E8A598'>&nbsp;@&nbsp;</font><font style='background-color: #E8A598; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E8A598;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E8A598; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_RED = -0x1.090f6ep126F;
    static { NAMED.put("white red", -0x1.090f6ep126F); LIST.add(-0x1.090f6ep126F); }

    /**
     * This color constant "black brown" has RGBA8888 code {@code 614C43FF}, L 0.31764707, A 0.50980395, B 0.50980395,
     * alpha 1.0, red 0.38004044, green 0.29768613, blue 0.26244172, hue 0.124999985, saturation 0.22933193, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.0504a2p126F}.
     * <pre>
     * <font style='background-color: #614C43;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #614C43; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #614C43;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #614C43'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #614C43'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #614C43'>&nbsp;@&nbsp;</font><font style='background-color: #614C43; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #614C43;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #614C43; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_BROWN = -0x1.0504a2p126F;
    static { NAMED.put("black brown", -0x1.0504a2p126F); LIST.add(-0x1.0504a2p126F); }

    /**
     * This color constant "lead brown" has RGBA8888 code {@code 80685EFF}, L 0.43137255, A 0.50980395, B 0.50980395,
     * alpha 1.0, red 0.501148, green 0.409678, blue 0.3704429, hue 0.124999985, saturation 0.1885618, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.0504dcp126F}.
     * <pre>
     * <font style='background-color: #80685E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #80685E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #80685E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #80685E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #80685E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #80685E'>&nbsp;@&nbsp;</font><font style='background-color: #80685E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #80685E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #80685E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_BROWN = -0x1.0504dcp126F;
    static { NAMED.put("lead brown", -0x1.0504dcp126F); LIST.add(-0x1.0504dcp126F); }

    /**
     * This color constant "gray brown" has RGBA8888 code {@code 9A8176FF}, L 0.5294118, A 0.50980395, B 0.50980395,
     * alpha 1.0, red 0.60455084, green 0.5064285, blue 0.46428224, hue 0.124999985, saturation 0.16317847, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.05050ep126F}.
     * <pre>
     * <font style='background-color: #9A8176;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A8176; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A8176;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9A8176'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9A8176'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9A8176'>&nbsp;@&nbsp;</font><font style='background-color: #9A8176; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A8176;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A8176; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_BROWN = -0x1.05050ep126F;
    static { NAMED.put("gray brown", -0x1.05050ep126F); LIST.add(-0x1.05050ep126F); }

    /**
     * This color constant "silver brown" has RGBA8888 code {@code B69B8FFF}, L 0.6313726, A 0.50980395, B 0.50980395,
     * alpha 1.0, red 0.71141607, green 0.60719305, blue 0.56237686, hue 0.124999985, saturation 0.18446264, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.050542p126F}.
     * <pre>
     * <font style='background-color: #B69B8F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B69B8F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B69B8F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B69B8F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B69B8F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B69B8F'>&nbsp;@&nbsp;</font><font style='background-color: #B69B8F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B69B8F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B69B8F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_BROWN = -0x1.050542p126F;
    static { NAMED.put("silver brown", -0x1.050542p126F); LIST.add(-0x1.050542p126F); }

    /**
     * This color constant "white brown" has RGBA8888 code {@code D6BAAEFF}, L 0.75686276, A 0.50980395, B 0.50980395,
     * alpha 1.0, red 0.8422546, green 0.7313644, blue 0.68362874, hue 0.124999985, saturation 0.30304575, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.050582p126F}.
     * <pre>
     * <font style='background-color: #D6BAAE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D6BAAE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D6BAAE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D6BAAE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D6BAAE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D6BAAE'>&nbsp;@&nbsp;</font><font style='background-color: #D6BAAE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D6BAAE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D6BAAE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_BROWN = -0x1.050582p126F;
    static { NAMED.put("white brown", -0x1.050582p126F); LIST.add(-0x1.050582p126F); }

    /**
     * This color constant "black orange" has RGBA8888 code {@code 674732FF}, L 0.30980393, A 0.5137255, B 0.52156866,
     * alpha 1.0, red 0.4025546, green 0.2803529, blue 0.1979979, hue 0.15641648, saturation 0.46523243, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.0b069ep126F}.
     * <pre>
     * <font style='background-color: #674732;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #674732; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #674732;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #674732'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #674732'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #674732'>&nbsp;@&nbsp;</font><font style='background-color: #674732; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #674732;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #674732; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_ORANGE = -0x1.0b069ep126F;
    static { NAMED.put("black orange", -0x1.0b069ep126F); LIST.add(-0x1.0b069ep126F); }

    /**
     * This color constant "lead orange" has RGBA8888 code {@code 826049FF}, L 0.40784314, A 0.5137255, B 0.52156866,
     * alpha 1.0, red 0.51060075, green 0.376658, blue 0.28687656, hue 0.15641648, saturation 0.4006168, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.0b06dp126F}.
     * <pre>
     * <font style='background-color: #826049;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #826049; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #826049;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #826049'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #826049'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #826049'>&nbsp;@&nbsp;</font><font style='background-color: #826049; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #826049;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #826049; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_ORANGE = -0x1.0b06dp126F;
    static { NAMED.put("lead orange", -0x1.0b06dp126F); LIST.add(-0x1.0b06dp126F); }

    /**
     * This color constant "gray orange" has RGBA8888 code {@code A07B62FF}, L 0.5137255, A 0.5137255, B 0.52156866,
     * alpha 1.0, red 0.6256078, green 0.48086327, blue 0.38406965, hue 0.15641648, saturation 0.34338585, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.0b0706p126F}.
     * <pre>
     * <font style='background-color: #A07B62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A07B62; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A07B62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A07B62'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A07B62'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A07B62'>&nbsp;@&nbsp;</font><font style='background-color: #A07B62; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A07B62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A07B62; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_ORANGE = -0x1.0b0706p126F;
    static { NAMED.put("gray orange", -0x1.0b0706p126F); LIST.add(-0x1.0b0706p126F); }

    /**
     * This color constant "silver orange" has RGBA8888 code {@code C0987EFF}, L 0.6313726, A 0.5137255, B 0.52156866,
     * alpha 1.0, red 0.7520057, green 0.59683555, blue 0.49319327, hue 0.15641648, saturation 0.3004626, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.0b0742p126F}.
     * <pre>
     * <font style='background-color: #C0987E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0987E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0987E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C0987E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C0987E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C0987E'>&nbsp;@&nbsp;</font><font style='background-color: #C0987E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0987E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0987E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_ORANGE = -0x1.0b0742p126F;
    static { NAMED.put("silver orange", -0x1.0b0742p126F); LIST.add(-0x1.0b0742p126F); }

    /**
     * This color constant "white orange" has RGBA8888 code {@code E5BB9FFF}, L 0.76862746, A 0.5137255, B 0.52156866,
     * alpha 1.0, red 0.898173, green 0.7323348, blue 0.6216382, hue 0.15641648, saturation 0.4973174, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.0b0788p126F}.
     * <pre>
     * <font style='background-color: #E5BB9F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E5BB9F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E5BB9F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E5BB9F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E5BB9F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E5BB9F'>&nbsp;@&nbsp;</font><font style='background-color: #E5BB9F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E5BB9F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E5BB9F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_ORANGE = -0x1.0b0788p126F;
    static { NAMED.put("white orange", -0x1.0b0788p126F); LIST.add(-0x1.0b0788p126F); }

    /**
     * This color constant "black saffron" has RGBA8888 code {@code 69583EFF}, L 0.3529412, A 0.5019608, B 0.52156866,
     * alpha 1.0, red 0.40788367, green 0.3435563, blue 0.23865767, hue 0.22371578, saturation 0.43448305, and chroma 0.04314594.
     * It can be represented as a packed float with the constant {@code -0x1.0b00b4p126F}.
     * <pre>
     * <font style='background-color: #69583E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #69583E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #69583E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #69583E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #69583E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #69583E'>&nbsp;@&nbsp;</font><font style='background-color: #69583E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #69583E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #69583E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_SAFFRON = -0x1.0b00b4p126F;
    static { NAMED.put("black saffron", -0x1.0b00b4p126F); LIST.add(-0x1.0b00b4p126F); }

    /**
     * This color constant "lead saffron" has RGBA8888 code {@code 8C795CFF}, L 0.4862745, A 0.5019608, B 0.52156866,
     * alpha 1.0, red 0.5476309, green 0.47650838, blue 0.36072534, hue 0.22371578, saturation 0.35780957, and chroma 0.04314594.
     * It can be represented as a packed float with the constant {@code -0x1.0b00f8p126F}.
     * <pre>
     * <font style='background-color: #8C795C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C795C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C795C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8C795C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8C795C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8C795C'>&nbsp;@&nbsp;</font><font style='background-color: #8C795C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C795C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C795C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_SAFFRON = -0x1.0b00f8p126F;
    static { NAMED.put("lead saffron", -0x1.0b00f8p126F); LIST.add(-0x1.0b00f8p126F); }

    /**
     * This color constant "gray saffron" has RGBA8888 code {@code A99676FF}, L 0.6, A 0.5019608, B 0.52156866,
     * alpha 1.0, red 0.66594285, green 0.58992374, blue 0.46613362, hue 0.22371578, saturation 0.31193656, and chroma 0.04314594.
     * It can be represented as a packed float with the constant {@code -0x1.0b0132p126F}.
     * <pre>
     * <font style='background-color: #A99676;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A99676; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A99676;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A99676'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A99676'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A99676'>&nbsp;@&nbsp;</font><font style='background-color: #A99676; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A99676;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A99676; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_SAFFRON = -0x1.0b0132p126F;
    static { NAMED.put("gray saffron", -0x1.0b0132p126F); LIST.add(-0x1.0b0132p126F); }

    /**
     * This color constant "silver saffron" has RGBA8888 code {@code C4B08EFF}, L 0.7019608, A 0.5019608, B 0.52156866,
     * alpha 1.0, red 0.77154297, green 0.6916222, blue 0.56140035, hue 0.22371578, saturation 0.27648923, and chroma 0.04314594.
     * It can be represented as a packed float with the constant {@code -0x1.0b0166p126F}.
     * <pre>
     * <font style='background-color: #C4B08E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4B08E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4B08E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C4B08E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C4B08E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C4B08E'>&nbsp;@&nbsp;</font><font style='background-color: #C4B08E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4B08E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4B08E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_SAFFRON = -0x1.0b0166p126F;
    static { NAMED.put("silver saffron", -0x1.0b0166p126F); LIST.add(-0x1.0b0166p126F); }

    /**
     * This color constant "white saffron" has RGBA8888 code {@code E2CDAAFF}, L 0.8156863, A 0.5019608, B 0.52156866,
     * alpha 1.0, red 0.8889306, green 0.8050707, blue 0.66832477, hue 0.22371578, saturation 0.35780957, and chroma 0.04314594.
     * It can be represented as a packed float with the constant {@code -0x1.0b01ap126F}.
     * <pre>
     * <font style='background-color: #E2CDAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E2CDAA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E2CDAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E2CDAA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E2CDAA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E2CDAA'>&nbsp;@&nbsp;</font><font style='background-color: #E2CDAA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E2CDAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E2CDAA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_SAFFRON = -0x1.0b01ap126F;
    static { NAMED.put("white saffron", -0x1.0b01ap126F); LIST.add(-0x1.0b01ap126F); }

    /**
     * This color constant "black yellow" has RGBA8888 code {@code 626438FF}, L 0.3764706, A 0.4862745, B 0.5294118,
     * alpha 1.0, red 0.38379917, green 0.39108333, blue 0.21932358, hue 0.30710012, saturation 0.551226, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.0ef8cp126F}.
     * <pre>
     * <font style='background-color: #626438;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #626438; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #626438;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #626438'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #626438'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #626438'>&nbsp;@&nbsp;</font><font style='background-color: #626438; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #626438;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #626438; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_YELLOW = -0x1.0ef8cp126F;
    static { NAMED.put("black yellow", -0x1.0ef8cp126F); LIST.add(-0x1.0ef8cp126F); }

    /**
     * This color constant "lead yellow" has RGBA8888 code {@code 7F8152FF}, L 0.49019608, A 0.4862745, B 0.5294118,
     * alpha 1.0, red 0.49772492, green 0.506898, blue 0.3219534, hue 0.30710012, saturation 0.47466686, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.0ef8fap126F}.
     * <pre>
     * <font style='background-color: #7F8152;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F8152; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F8152;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7F8152'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7F8152'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7F8152'>&nbsp;@&nbsp;</font><font style='background-color: #7F8152; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F8152;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F8152; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_YELLOW = -0x1.0ef8fap126F;
    static { NAMED.put("lead yellow", -0x1.0ef8fap126F); LIST.add(-0x1.0ef8fap126F); }

    /**
     * This color constant "gray yellow" has RGBA8888 code {@code 9A9D6BFF}, L 0.59607846, A 0.4862745, B 0.5294118,
     * alpha 1.0, red 0.60378784, green 0.61442125, blue 0.41834834, hue 0.30710012, saturation 0.41678065, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.0ef93p126F}.
     * <pre>
     * <font style='background-color: #9A9D6B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A9D6B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A9D6B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9A9D6B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9A9D6B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9A9D6B'>&nbsp;@&nbsp;</font><font style='background-color: #9A9D6B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A9D6B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A9D6B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_YELLOW = -0x1.0ef93p126F;
    static { NAMED.put("gray yellow", -0x1.0ef93p126F); LIST.add(-0x1.0ef93p126F); }

    /**
     * This color constant "silver yellow" has RGBA8888 code {@code B8BB86FF}, L 0.7137255, A 0.4862745, B 0.5294118,
     * alpha 1.0, red 0.72162944, green 0.73365706, blue 0.5263837, hue 0.30710012, saturation 0.3714784, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.0ef96cp126F}.
     * <pre>
     * <font style='background-color: #B8BB86;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B8BB86; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B8BB86;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B8BB86'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B8BB86'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B8BB86'>&nbsp;@&nbsp;</font><font style='background-color: #B8BB86; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B8BB86;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B8BB86; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_YELLOW = -0x1.0ef96cp126F;
    static { NAMED.put("silver yellow", -0x1.0ef96cp126F); LIST.add(-0x1.0ef96cp126F); }

    /**
     * This color constant "white yellow" has RGBA8888 code {@code DBDFA7FF}, L 0.85490197, A 0.4862745, B 0.5294118,
     * alpha 1.0, red 0.863029, green 0.876504, blue 0.6571083, hue 0.30710012, saturation 0.32861552, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.0ef9b4p126F}.
     * <pre>
     * <font style='background-color: #DBDFA7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBDFA7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBDFA7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DBDFA7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DBDFA7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DBDFA7'>&nbsp;@&nbsp;</font><font style='background-color: #DBDFA7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBDFA7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBDFA7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_YELLOW = -0x1.0ef9b4p126F;
    static { NAMED.put("white yellow", -0x1.0ef9b4p126F); LIST.add(-0x1.0ef9b4p126F); }

    /**
     * This color constant "black lime" has RGBA8888 code {@code 4A5C39FF}, L 0.33333334, A 0.47843137, B 0.52156866,
     * alpha 1.0, red 0.29199916, green 0.3604476, blue 0.2241895, hue 0.36057103, saturation 0.4463, and chroma 0.060766988.
     * It can be represented as a packed float with the constant {@code -0x1.0af4aap126F}.
     * <pre>
     * <font style='background-color: #4A5C39;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A5C39; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A5C39;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4A5C39'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4A5C39'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4A5C39'>&nbsp;@&nbsp;</font><font style='background-color: #4A5C39; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A5C39;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A5C39; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_LIME = -0x1.0af4aap126F;
    static { NAMED.put("black lime", -0x1.0af4aap126F); LIST.add(-0x1.0af4aap126F); }

    /**
     * This color constant "lead lime" has RGBA8888 code {@code 647852FF}, L 0.4392157, A 0.47843137, B 0.52156866,
     * alpha 1.0, red 0.394073, green 0.46953565, blue 0.32117912, hue 0.36057103, saturation 0.37191665, and chroma 0.060766988.
     * It can be represented as a packed float with the constant {@code -0x1.0af4ep126F}.
     * <pre>
     * <font style='background-color: #647852;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #647852; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #647852;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #647852'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #647852'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #647852'>&nbsp;@&nbsp;</font><font style='background-color: #647852; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #647852;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #647852; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_LIME = -0x1.0af4ep126F;
    static { NAMED.put("lead lime", -0x1.0af4ep126F); LIST.add(-0x1.0af4ep126F); }

    /**
     * This color constant "gray lime" has RGBA8888 code {@code 81966DFF}, L 0.5568628, A 0.47843137, B 0.52156866,
     * alpha 1.0, red 0.5081341, green 0.5901593, blue 0.43018222, hue 0.36057103, saturation 0.3187857, and chroma 0.060766988.
     * It can be represented as a packed float with the constant {@code -0x1.0af51cp126F}.
     * <pre>
     * <font style='background-color: #81966D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #81966D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #81966D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #81966D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #81966D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #81966D'>&nbsp;@&nbsp;</font><font style='background-color: #81966D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #81966D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #81966D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_LIME = -0x1.0af51cp126F;
    static { NAMED.put("gray lime", -0x1.0af51cp126F); LIST.add(-0x1.0af51cp126F); }

    /**
     * This color constant "silver lime" has RGBA8888 code {@code A2B88DFF}, L 0.6862745, A 0.47843137, B 0.52156866,
     * alpha 1.0, red 0.6341349, green 0.7223732, blue 0.5512264, hue 0.36057103, saturation 0.2789375, and chroma 0.060766988.
     * It can be represented as a packed float with the constant {@code -0x1.0af55ep126F}.
     * <pre>
     * <font style='background-color: #A2B88D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2B88D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2B88D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A2B88D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A2B88D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A2B88D'>&nbsp;@&nbsp;</font><font style='background-color: #A2B88D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2B88D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2B88D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_LIME = -0x1.0af55ep126F;
    static { NAMED.put("silver lime", -0x1.0af55ep126F); LIST.add(-0x1.0af55ep126F); }

    /**
     * This color constant "white lime" has RGBA8888 code {@code C6DEB0FF}, L 0.83137256, A 0.47843137, B 0.52156866,
     * alpha 1.0, red 0.7758802, green 0.8702037, blue 0.68800485, hue 0.36057103, saturation 0.24794444, and chroma 0.060766988.
     * It can be represented as a packed float with the constant {@code -0x1.0af5a8p126F}.
     * <pre>
     * <font style='background-color: #C6DEB0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C6DEB0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C6DEB0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C6DEB0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C6DEB0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C6DEB0'>&nbsp;@&nbsp;</font><font style='background-color: #C6DEB0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C6DEB0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C6DEB0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_LIME = -0x1.0af5a8p126F;
    static { NAMED.put("white lime", -0x1.0af5a8p126F); LIST.add(-0x1.0af5a8p126F); }

    /**
     * This color constant "black green" has RGBA8888 code {@code 3D6A41FF}, L 0.3647059, A 0.4627451, B 0.52156866,
     * alpha 1.0, red 0.23983009, green 0.41520917, blue 0.25510404, hue 0.40641648, saturation 0.5031002, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.0aecbap126F}.
     * <pre>
     * <font style='background-color: #3D6A41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3D6A41; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3D6A41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3D6A41'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3D6A41'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3D6A41'>&nbsp;@&nbsp;</font><font style='background-color: #3D6A41; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3D6A41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3D6A41; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_GREEN = -0x1.0aecbap126F;
    static { NAMED.put("black green", -0x1.0aecbap126F); LIST.add(-0x1.0aecbap126F); }

    /**
     * This color constant "lead green" has RGBA8888 code {@code 5A8A5EFF}, L 0.4862745, A 0.4627451, B 0.52156866,
     * alpha 1.0, red 0.35349688, green 0.5429347, blue 0.3672552, hue 0.40641648, saturation 0.41602516, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.0aecf8p126F}.
     * <pre>
     * <font style='background-color: #5A8A5E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A8A5E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A8A5E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5A8A5E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5A8A5E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5A8A5E'>&nbsp;@&nbsp;</font><font style='background-color: #5A8A5E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A8A5E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A8A5E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_GREEN = -0x1.0aecf8p126F;
    static { NAMED.put("lead green", -0x1.0aecf8p126F); LIST.add(-0x1.0aecf8p126F); }

    /**
     * This color constant "gray green" has RGBA8888 code {@code 74A878FF}, L 0.59607846, A 0.4627451, B 0.52156866,
     * alpha 1.0, red 0.45657992, green 0.65743315, blue 0.46959773, hue 0.40641648, saturation 0.36666626, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.0aed3p126F}.
     * <pre>
     * <font style='background-color: #74A878;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #74A878; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #74A878;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #74A878'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #74A878'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #74A878'>&nbsp;@&nbsp;</font><font style='background-color: #74A878; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #74A878;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #74A878; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_GREEN = -0x1.0aed3p126F;
    static { NAMED.put("gray green", -0x1.0aed3p126F); LIST.add(-0x1.0aed3p126F); }

    /**
     * This color constant "silver green" has RGBA8888 code {@code 8DC390FF}, L 0.69803923, A 0.4627451, B 0.52156866,
     * alpha 1.0, red 0.552743, green 0.763242, blue 0.5653159, hue 0.40641648, saturation 0.33282012, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.0aed64p126F}.
     * <pre>
     * <font style='background-color: #8DC390;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8DC390; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8DC390;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8DC390'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8DC390'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8DC390'>&nbsp;@&nbsp;</font><font style='background-color: #8DC390; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8DC390;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8DC390; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_GREEN = -0x1.0aed64p126F;
    static { NAMED.put("silver green", -0x1.0aed64p126F); LIST.add(-0x1.0aed64p126F); }

    /**
     * This color constant "white green" has RGBA8888 code {@code AAE3AEFF}, L 0.8235294, A 0.4627451, B 0.52156866,
     * alpha 1.0, red 0.6716325, green 0.8929658, blue 0.6838332, hue 0.40641648, saturation 0.3228852, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.0aeda4p126F}.
     * <pre>
     * <font style='background-color: #AAE3AE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AAE3AE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AAE3AE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AAE3AE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AAE3AE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AAE3AE'>&nbsp;@&nbsp;</font><font style='background-color: #AAE3AE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AAE3AE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AAE3AE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_GREEN = -0x1.0aeda4p126F;
    static { NAMED.put("white green", -0x1.0aeda4p126F); LIST.add(-0x1.0aeda4p126F); }

    /**
     * This color constant "black cyan" has RGBA8888 code {@code 40615FFF}, L 0.34901962, A 0.47843137, B 0.49411765,
     * alpha 1.0, red 0.25013524, green 0.37808233, blue 0.37019902, hue 0.5314165, saturation 0.443393, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.fcf4b2p125F}.
     * <pre>
     * <font style='background-color: #40615F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #40615F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #40615F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #40615F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #40615F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #40615F'>&nbsp;@&nbsp;</font><font style='background-color: #40615F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #40615F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #40615F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_CYAN = -0x1.fcf4b2p125F;
    static { NAMED.put("black cyan", -0x1.fcf4b2p125F); LIST.add(-0x1.fcf4b2p125F); }

    /**
     * This color constant "lead cyan" has RGBA8888 code {@code 5A7D7BFF}, L 0.45882353, A 0.47843137, B 0.49411765,
     * alpha 1.0, red 0.35325634, green 0.49100748, blue 0.481996, hue 0.5314165, saturation 0.36421567, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.fcf4eap125F}.
     * <pre>
     * <font style='background-color: #5A7D7B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A7D7B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A7D7B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5A7D7B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5A7D7B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5A7D7B'>&nbsp;@&nbsp;</font><font style='background-color: #5A7D7B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A7D7B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A7D7B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_CYAN = -0x1.fcf4eap125F;
    static { NAMED.put("lead cyan", -0x1.fcf4eap125F); LIST.add(-0x1.fcf4eap125F); }

    /**
     * This color constant "gray cyan" has RGBA8888 code {@code 749996FF}, L 0.5647059, A 0.47843137, B 0.49411765,
     * alpha 1.0, red 0.45329183, green 0.5994419, blue 0.5895115, hue 0.5314165, saturation 0.31868872, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.fcf52p125F}.
     * <pre>
     * <font style='background-color: #749996;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #749996; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #749996;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #749996'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #749996'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #749996'>&nbsp;@&nbsp;</font><font style='background-color: #749996; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #749996;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #749996; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_CYAN = -0x1.fcf52p125F;
    static { NAMED.put("gray cyan", -0x1.fcf52p125F); LIST.add(-0x1.fcf52p125F); }

    /**
     * This color constant "silver cyan" has RGBA8888 code {@code 92BAB7FF}, L 0.69411767, A 0.47843137, B 0.49411765,
     * alpha 1.0, red 0.5762566, green 0.7315589, blue 0.72065634, hue 0.5314165, saturation 0.28327885, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.fcf562p125F}.
     * <pre>
     * <font style='background-color: #92BAB7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #92BAB7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #92BAB7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #92BAB7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #92BAB7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #92BAB7'>&nbsp;@&nbsp;</font><font style='background-color: #92BAB7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #92BAB7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #92BAB7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_CYAN = -0x1.fcf562p125F;
    static { NAMED.put("silver cyan", -0x1.fcf562p125F); LIST.add(-0x1.fcf562p125F); }

    /**
     * This color constant "white cyan" has RGBA8888 code {@code B5DFDCFF}, L 0.8352941, A 0.47843137, B 0.49411765,
     * alpha 1.0, red 0.7111093, green 0.87531716, blue 0.86348695, hue 0.5314165, saturation 0.24873266, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.fcf5aap125F}.
     * <pre>
     * <font style='background-color: #B5DFDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B5DFDC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B5DFDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B5DFDC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B5DFDC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B5DFDC'>&nbsp;@&nbsp;</font><font style='background-color: #B5DFDC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B5DFDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B5DFDC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_CYAN = -0x1.fcf5aap125F;
    static { NAMED.put("white cyan", -0x1.fcf5aap125F); LIST.add(-0x1.fcf5aap125F); }

    /**
     * This color constant "black blue" has RGBA8888 code {@code 314575FF}, L 0.27450982, A 0.49411765, B 0.45490196,
     * alpha 1.0, red 0.19158068, green 0.26817235, blue 0.45341927, hue 0.735571, saturation 0.29066738, and chroma 0.09060479.
     * It can be represented as a packed float with the constant {@code -0x1.e8fc8cp125F}.
     * <pre>
     * <font style='background-color: #314575;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #314575; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #314575;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #314575'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #314575'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #314575'>&nbsp;@&nbsp;</font><font style='background-color: #314575; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #314575;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #314575; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_BLUE = -0x1.e8fc8cp125F;
    static { NAMED.put("black blue", -0x1.e8fc8cp125F); LIST.add(-0x1.e8fc8cp125F); }

    /**
     * This color constant "lead blue" has RGBA8888 code {@code 4C6397FF}, L 0.39215687, A 0.49411765, B 0.45490196,
     * alpha 1.0, red 0.29898483, green 0.3881012, blue 0.5937156, hue 0.735571, saturation 0.32486355, and chroma 0.09060479.
     * It can be represented as a packed float with the constant {@code -0x1.e8fcc8p125F}.
     * <pre>
     * <font style='background-color: #4C6397;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4C6397; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4C6397;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4C6397'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4C6397'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4C6397'>&nbsp;@&nbsp;</font><font style='background-color: #4C6397; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4C6397;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4C6397; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_BLUE = -0x1.e8fcc8p125F;
    static { NAMED.put("lead blue", -0x1.e8fcc8p125F); LIST.add(-0x1.e8fcc8p125F); }

    /**
     * This color constant "gray blue" has RGBA8888 code {@code 647DB6FF}, L 0.49411765, A 0.49411765, B 0.45490196,
     * alpha 1.0, red 0.3936319, green 0.49145153, blue 0.7119908, hue 0.735571, saturation 0.41680607, and chroma 0.09060479.
     * It can be represented as a packed float with the constant {@code -0x1.e8fcfcp125F}.
     * <pre>
     * <font style='background-color: #647DB6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #647DB6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #647DB6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #647DB6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #647DB6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #647DB6'>&nbsp;@&nbsp;</font><font style='background-color: #647DB6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #647DB6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #647DB6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_BLUE = -0x1.e8fcfcp125F;
    static { NAMED.put("gray blue", -0x1.e8fcfcp125F); LIST.add(-0x1.e8fcfcp125F); }

    /**
     * This color constant "silver blue" has RGBA8888 code {@code 7A95D0FF}, L 0.58431375, A 0.49411765, B 0.45490196,
     * alpha 1.0, red 0.4781564, green 0.5826244, blue 0.8148845, hue 0.735571, saturation 0.52596956, and chroma 0.09060479.
     * It can be represented as a packed float with the constant {@code -0x1.e8fd2ap125F}.
     * <pre>
     * <font style='background-color: #7A95D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7A95D0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7A95D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7A95D0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7A95D0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7A95D0'>&nbsp;@&nbsp;</font><font style='background-color: #7A95D0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7A95D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7A95D0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_BLUE = -0x1.e8fd2ap125F;
    static { NAMED.put("silver blue", -0x1.e8fd2ap125F); LIST.add(-0x1.e8fd2ap125F); }

    /**
     * This color constant "white blue" has RGBA8888 code {@code 91ADEBFF}, L 0.6784314, A 0.49411765, B 0.45490196,
     * alpha 1.0, red 0.56694835, green 0.6775978, blue 0.9209572, hue 0.735571, saturation 0.7126039, and chroma 0.09060479.
     * It can be represented as a packed float with the constant {@code -0x1.e8fd5ap125F}.
     * <pre>
     * <font style='background-color: #91ADEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #91ADEB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #91ADEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #91ADEB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #91ADEB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #91ADEB'>&nbsp;@&nbsp;</font><font style='background-color: #91ADEB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #91ADEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #91ADEB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_BLUE = -0x1.e8fd5ap125F;
    static { NAMED.put("white blue", -0x1.e8fd5ap125F); LIST.add(-0x1.e8fd5ap125F); }

    /**
     * This color constant "black violet" has RGBA8888 code {@code 52416CFF}, L 0.2901961, A 0.5176471, B 0.46666667,
     * alpha 1.0, red 0.31864443, green 0.25369304, blue 0.41853663, hue 0.83890384, saturation 0.30432197, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.ef0894p125F}.
     * <pre>
     * <font style='background-color: #52416C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #52416C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #52416C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #52416C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #52416C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #52416C'>&nbsp;@&nbsp;</font><font style='background-color: #52416C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #52416C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #52416C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_VIOLET = -0x1.ef0894p125F;
    static { NAMED.put("black violet", -0x1.ef0894p125F); LIST.add(-0x1.ef0894p125F); }

    /**
     * This color constant "lead violet" has RGBA8888 code {@code 6E5C8BFF}, L 0.4, A 0.5176471, B 0.46666667,
     * alpha 1.0, red 0.43124253, green 0.36116052, blue 0.5431959, hue 0.83890384, saturation 0.24826266, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.ef08ccp125F}.
     * <pre>
     * <font style='background-color: #6E5C8B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E5C8B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E5C8B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6E5C8B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6E5C8B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6E5C8B'>&nbsp;@&nbsp;</font><font style='background-color: #6E5C8B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E5C8B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E5C8B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_VIOLET = -0x1.ef08ccp125F;
    static { NAMED.put("lead violet", -0x1.ef08ccp125F); LIST.add(-0x1.ef08ccp125F); }

    /**
     * This color constant "gray violet" has RGBA8888 code {@code 8573A4FF}, L 0.49019608, A 0.5176471, B 0.46666667,
     * alpha 1.0, red 0.5233951, green 0.44959477, blue 0.6436968, hue 0.83890384, saturation 0.2858782, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.ef08fap125F}.
     * <pre>
     * <font style='background-color: #8573A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8573A4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8573A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8573A4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8573A4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8573A4'>&nbsp;@&nbsp;</font><font style='background-color: #8573A4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8573A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8573A4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_VIOLET = -0x1.ef08fap125F;
    static { NAMED.put("gray violet", -0x1.ef08fap125F); LIST.add(-0x1.ef08fap125F); }

    /**
     * This color constant "silver violet" has RGBA8888 code {@code 9F8BC0FF}, L 0.5882353, A 0.5176471, B 0.46666667,
     * alpha 1.0, red 0.6233184, green 0.5458692, blue 0.75160223, hue 0.83890384, saturation 0.36996004, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.ef092cp125F}.
     * <pre>
     * <font style='background-color: #9F8BC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9F8BC0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9F8BC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9F8BC0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9F8BC0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9F8BC0'>&nbsp;@&nbsp;</font><font style='background-color: #9F8BC0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9F8BC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9F8BC0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_VIOLET = -0x1.ef092cp125F;
    static { NAMED.put("silver violet", -0x1.ef092cp125F); LIST.add(-0x1.ef092cp125F); }

    /**
     * This color constant "white violet" has RGBA8888 code {@code BFAAE2FF}, L 0.7137255, A 0.5176471, B 0.46666667,
     * alpha 1.0, red 0.7509518, green 0.66929686, blue 0.8882438, hue 0.83890384, saturation 0.5717564, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.ef096cp125F}.
     * <pre>
     * <font style='background-color: #BFAAE2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFAAE2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFAAE2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BFAAE2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BFAAE2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BFAAE2'>&nbsp;@&nbsp;</font><font style='background-color: #BFAAE2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFAAE2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFAAE2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_VIOLET = -0x1.ef096cp125F;
    static { NAMED.put("white violet", -0x1.ef096cp125F); LIST.add(-0x1.ef096cp125F); }

    /**
     * This color constant "black purple" has RGBA8888 code {@code 593968FF}, L 0.28235295, A 0.5294118, B 0.46666667,
     * alpha 1.0, red 0.3500163, green 0.22513701, blue 0.40725103, hue 0.875, saturation 0.40406102, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.ef0e9p125F}.
     * <pre>
     * <font style='background-color: #593968;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #593968; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #593968;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #593968'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #593968'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #593968'>&nbsp;@&nbsp;</font><font style='background-color: #593968; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #593968;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #593968; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_PURPLE = -0x1.ef0e9p125F;
    static { NAMED.put("black purple", -0x1.ef0e9p125F); LIST.add(-0x1.ef0e9p125F); }

    /**
     * This color constant "lead purple" has RGBA8888 code {@code 735083FF}, L 0.37254903, A 0.5294118, B 0.46666667,
     * alpha 1.0, red 0.44732255, green 0.31225398, blue 0.509908, hue 0.875, saturation 0.33772263, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.ef0ebep125F}.
     * <pre>
     * <font style='background-color: #735083;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #735083; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #735083;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #735083'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #735083'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #735083'>&nbsp;@&nbsp;</font><font style='background-color: #735083; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #735083;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #735083; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_PURPLE = -0x1.ef0ebep125F;
    static { NAMED.put("lead purple", -0x1.ef0ebep125F); LIST.add(-0x1.ef0ebep125F); }

    /**
     * This color constant "gray purple" has RGBA8888 code {@code 906BA1FF}, L 0.48235294, A 0.5294118, B 0.46666667,
     * alpha 1.0, red 0.5643653, green 0.4185573, blue 0.6324507, hue 0.875, saturation 0.286423, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.ef0ef6p125F}.
     * <pre>
     * <font style='background-color: #906BA1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #906BA1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #906BA1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #906BA1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #906BA1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #906BA1'>&nbsp;@&nbsp;</font><font style='background-color: #906BA1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #906BA1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #906BA1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_PURPLE = -0x1.ef0ef6p125F;
    static { NAMED.put("gray purple", -0x1.ef0ef6p125F); LIST.add(-0x1.ef0ef6p125F); }

    /**
     * This color constant "silver purple" has RGBA8888 code {@code B088C3FF}, L 0.6, A 0.5294118, B 0.46666667,
     * alpha 1.0, red 0.68862504, green 0.5327925, blue 0.76176935, hue 0.875, saturation 0.37094125, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.ef0f32p125F}.
     * <pre>
     * <font style='background-color: #B088C3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B088C3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B088C3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B088C3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B088C3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B088C3'>&nbsp;@&nbsp;</font><font style='background-color: #B088C3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B088C3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B088C3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_PURPLE = -0x1.ef0f32p125F;
    static { NAMED.put("silver purple", -0x1.ef0f32p125F); LIST.add(-0x1.ef0f32p125F); }

    /**
     * This color constant "white purple" has RGBA8888 code {@code D4A9E8FF}, L 0.7372549, A 0.5294118, B 0.46666667,
     * alpha 1.0, red 0.83257174, green 0.6664504, blue 0.9108578, hue 0.875, saturation 0.5954583, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.ef0f78p125F}.
     * <pre>
     * <font style='background-color: #D4A9E8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4A9E8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4A9E8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D4A9E8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D4A9E8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D4A9E8'>&nbsp;@&nbsp;</font><font style='background-color: #D4A9E8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4A9E8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4A9E8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_PURPLE = -0x1.ef0f78p125F;
    static { NAMED.put("white purple", -0x1.ef0f78p125F); LIST.add(-0x1.ef0f78p125F); }

    /**
     * This color constant "black magenta" has RGBA8888 code {@code 6C436DFF}, L 0.3254902, A 0.53333336, B 0.4745098,
     * alpha 1.0, red 0.4197081, green 0.26142806, blue 0.4220305, hue 0.9064165, saturation 0.37298808, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.f310a6p125F}.
     * <pre>
     * <font style='background-color: #6C436D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C436D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C436D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6C436D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6C436D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6C436D'>&nbsp;@&nbsp;</font><font style='background-color: #6C436D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C436D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C436D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_MAGENTA = -0x1.f310a6p125F;
    static { NAMED.put("black magenta", -0x1.f310a6p125F); LIST.add(-0x1.f310a6p125F); }

    /**
     * This color constant "lead magenta" has RGBA8888 code {@code 8D608EFF}, L 0.44705883, A 0.53333336, B 0.4745098,
     * alpha 1.0, red 0.5533876, green 0.37823248, blue 0.5547652, hue 0.9064165, saturation 0.3004626, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.f310e4p125F}.
     * <pre>
     * <font style='background-color: #8D608E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8D608E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8D608E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8D608E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8D608E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8D608E'>&nbsp;@&nbsp;</font><font style='background-color: #8D608E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8D608E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8D608E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_MAGENTA = -0x1.f310e4p125F;
    static { NAMED.put("lead magenta", -0x1.f310e4p125F); LIST.add(-0x1.f310e4p125F); }

    /**
     * This color constant "gray magenta" has RGBA8888 code {@code AB7AABFF}, L 0.5529412, A 0.53333336, B 0.4745098,
     * alpha 1.0, red 0.6681107, green 0.48039174, blue 0.6688167, hue 0.9064165, saturation 0.26382083, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.f3111ap125F}.
     * <pre>
     * <font style='background-color: #AB7AAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB7AAB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB7AAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB7AAB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB7AAB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB7AAB'>&nbsp;@&nbsp;</font><font style='background-color: #AB7AAB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB7AAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB7AAB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_MAGENTA = -0x1.f3111ap125F;
    static { NAMED.put("gray magenta", -0x1.f3111ap125F); LIST.add(-0x1.f3111ap125F); }

    /**
     * This color constant "silver magenta" has RGBA8888 code {@code C593C6FF}, L 0.6509804, A 0.53333336, B 0.4745098,
     * alpha 1.0, red 0.77336407, green 0.5752858, blue 0.7735314, hue 0.9064165, saturation 0.3181369, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.f3114cp125F}.
     * <pre>
     * <font style='background-color: #C593C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C593C6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C593C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C593C6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C593C6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C593C6'>&nbsp;@&nbsp;</font><font style='background-color: #C593C6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C593C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C593C6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_MAGENTA = -0x1.f3114cp125F;
    static { NAMED.put("silver magenta", -0x1.f3114cp125F); LIST.add(-0x1.f3114cp125F); }

    /**
     * This color constant "white magenta" has RGBA8888 code {@code E5AFE4FF}, L 0.76862746, A 0.53333336, B 0.4745098,
     * alpha 1.0, red 0.8987545, green 0.68947476, blue 0.8983519, hue 0.9064165, saturation 0.52764165, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.f31188p125F}.
     * <pre>
     * <font style='background-color: #E5AFE4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E5AFE4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E5AFE4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E5AFE4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E5AFE4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E5AFE4'>&nbsp;@&nbsp;</font><font style='background-color: #E5AFE4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E5AFE4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E5AFE4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_MAGENTA = -0x1.f31188p125F;
    static { NAMED.put("white magenta", -0x1.f31188p125F); LIST.add(-0x1.f31188p125F); }

    /**
     * This color constant "drab red" has RGBA8888 code {@code 91322AFF}, L 0.32941177, A 0.5568628, B 0.5294118,
     * alpha 1.0, red 0.5682251, green 0.1962504, blue 0.1676766, hue 0.07797913, saturation 0.6666667, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.0f1ca8p126F}.
     * <pre>
     * <font style='background-color: #91322A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #91322A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #91322A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #91322A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #91322A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #91322A'>&nbsp;@&nbsp;</font><font style='background-color: #91322A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #91322A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #91322A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_RED = -0x1.0f1ca8p126F;
    static { NAMED.put("drab red", -0x1.0f1ca8p126F); LIST.add(-0x1.0f1ca8p126F); }

    /**
     * This color constant "faded red" has RGBA8888 code {@code B64E44FF}, L 0.44313726, A 0.5568628, B 0.5294118,
     * alpha 1.0, red 0.7129469, green 0.30826837, blue 0.26865804, hue 0.07797913, saturation 0.5483871, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.0f1ce2p126F}.
     * <pre>
     * <font style='background-color: #B64E44;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B64E44; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B64E44;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B64E44'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B64E44'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B64E44'>&nbsp;@&nbsp;</font><font style='background-color: #B64E44; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B64E44;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B64E44; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_RED = -0x1.0f1ce2p126F;
    static { NAMED.put("faded red", -0x1.0f1ce2p126F); LIST.add(-0x1.0f1ce2p126F); }

    /**
     * This color constant "pale red" has RGBA8888 code {@code E27165FF}, L 0.58431375, A 0.5568628, B 0.5294118,
     * alpha 1.0, red 0.8862076, green 0.4447391, blue 0.39542648, hue 0.07797913, saturation 0.6666667, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.0f1d2ap126F}.
     * <pre>
     * <font style='background-color: #E27165;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E27165; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E27165;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E27165'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E27165'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E27165'>&nbsp;@&nbsp;</font><font style='background-color: #E27165; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E27165;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E27165; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_RED = -0x1.0f1d2ap126F;
    static { NAMED.put("pale red", -0x1.0f1d2ap126F); LIST.add(-0x1.0f1d2ap126F); }

    /**
     * This color constant "drab brown" has RGBA8888 code {@code 85594BFF}, L 0.39607844, A 0.52156866, B 0.5176471,
     * alpha 1.0, red 0.5196916, green 0.35092977, blue 0.29540542, hue 0.110571034, saturation 0.33235106, and chroma 0.055518243.
     * It can be represented as a packed float with the constant {@code -0x1.090acap126F}.
     * <pre>
     * <font style='background-color: #85594B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #85594B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #85594B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #85594B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #85594B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #85594B'>&nbsp;@&nbsp;</font><font style='background-color: #85594B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #85594B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #85594B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BROWN = -0x1.090acap126F;
    static { NAMED.put("drab brown", -0x1.090acap126F); LIST.add(-0x1.090acap126F); }

    /**
     * This color constant "faded brown" has RGBA8888 code {@code A67767FF}, L 0.5137255, A 0.52156866, B 0.5176471,
     * alpha 1.0, red 0.6501856, green 0.46568674, blue 0.4045533, hue 0.110571034, saturation 0.28400907, and chroma 0.055518243.
     * It can be represented as a packed float with the constant {@code -0x1.090b06p126F}.
     * <pre>
     * <font style='background-color: #A67767;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A67767; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A67767;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A67767'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A67767'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A67767'>&nbsp;@&nbsp;</font><font style='background-color: #A67767; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A67767;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A67767; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_BROWN = -0x1.090b06p126F;
    static { NAMED.put("faded brown", -0x1.090b06p126F); LIST.add(-0x1.090b06p126F); }

    /**
     * This color constant "pale brown" has RGBA8888 code {@code C99685FF}, L 0.6392157, A 0.52156866, B 0.5176471,
     * alpha 1.0, red 0.7873501, green 0.5884386, blue 0.52220076, hue 0.110571034, saturation 0.35501134, and chroma 0.055518243.
     * It can be represented as a packed float with the constant {@code -0x1.090b46p126F}.
     * <pre>
     * <font style='background-color: #C99685;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C99685; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C99685;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C99685'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C99685'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C99685'>&nbsp;@&nbsp;</font><font style='background-color: #C99685; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C99685;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C99685; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BROWN = -0x1.090b46p126F;
    static { NAMED.put("pale brown", -0x1.090b46p126F); LIST.add(-0x1.090b46p126F); }

    /**
     * This color constant "drab orange" has RGBA8888 code {@code 935831FF}, L 0.40784314, A 0.5254902, B 0.5372549,
     * alpha 1.0, red 0.5779806, green 0.34679613, blue 0.19406566, hue 0.15279995, saturation 0.6598138, and chroma 0.08992863.
     * It can be represented as a packed float with the constant {@code -0x1.130cdp126F}.
     * <pre>
     * <font style='background-color: #935831;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #935831; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #935831;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #935831'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #935831'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #935831'>&nbsp;@&nbsp;</font><font style='background-color: #935831; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #935831;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #935831; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_ORANGE = -0x1.130cdp126F;
    static { NAMED.put("drab orange", -0x1.130cdp126F); LIST.add(-0x1.130cdp126F); }

    /**
     * This color constant "faded orange" has RGBA8888 code {@code B7774DFF}, L 0.5294118, A 0.5254902, B 0.5372549,
     * alpha 1.0, red 0.7174115, green 0.46578062, blue 0.30281392, hue 0.15279995, saturation 0.5548434, and chroma 0.08992863.
     * It can be represented as a packed float with the constant {@code -0x1.130d0ep126F}.
     * <pre>
     * <font style='background-color: #B7774D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B7774D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B7774D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B7774D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B7774D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B7774D'>&nbsp;@&nbsp;</font><font style='background-color: #B7774D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B7774D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B7774D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_ORANGE = -0x1.130d0ep126F;
    static { NAMED.put("faded orange", -0x1.130d0ep126F); LIST.add(-0x1.130d0ep126F); }

    /**
     * This color constant "pale orange" has RGBA8888 code {@code E19B6EFF}, L 0.6745098, A 0.5254902, B 0.5372549,
     * alpha 1.0, red 0.88054717, green 0.60793966, blue 0.43292323, hue 0.15279995, saturation 0.56774676, and chroma 0.08992863.
     * It can be represented as a packed float with the constant {@code -0x1.130d58p126F}.
     * <pre>
     * <font style='background-color: #E19B6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E19B6E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E19B6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E19B6E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E19B6E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E19B6E'>&nbsp;@&nbsp;</font><font style='background-color: #E19B6E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E19B6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E19B6E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_ORANGE = -0x1.130d58p126F;
    static { NAMED.put("pale orange", -0x1.130d58p126F); LIST.add(-0x1.130d58p126F); }

    /**
     * This color constant "drab saffron" has RGBA8888 code {@code 987141FF}, L 0.4745098, A 0.50980395, B 0.5372549,
     * alpha 1.0, red 0.5955832, green 0.44486073, blue 0.2561317, hue 0.2036132, saturation 0.59658897, and chroma 0.07674564.
     * It can be represented as a packed float with the constant {@code -0x1.1304f2p126F}.
     * <pre>
     * <font style='background-color: #987141;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #987141; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #987141;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #987141'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #987141'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #987141'>&nbsp;@&nbsp;</font><font style='background-color: #987141; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #987141;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #987141; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_SAFFRON = -0x1.1304f2p126F;
    static { NAMED.put("drab saffron", -0x1.1304f2p126F); LIST.add(-0x1.1304f2p126F); }

    /**
     * This color constant "faded saffron" has RGBA8888 code {@code C09662FF}, L 0.61960787, A 0.50980395, B 0.5372549,
     * alpha 1.0, red 0.7528226, green 0.5888386, blue 0.38622653, hue 0.2036132, saturation 0.50928324, and chroma 0.07674564.
     * It can be represented as a packed float with the constant {@code -0x1.13053cp126F}.
     * <pre>
     * <font style='background-color: #C09662;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C09662; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C09662;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C09662'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C09662'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C09662'>&nbsp;@&nbsp;</font><font style='background-color: #C09662; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C09662;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C09662; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_SAFFRON = -0x1.13053cp126F;
    static { NAMED.put("faded saffron", -0x1.13053cp126F); LIST.add(-0x1.13053cp126F); }

    /**
     * This color constant "pale saffron" has RGBA8888 code {@code E3B67FFF}, L 0.74509805, A 0.50980395, B 0.5372549,
     * alpha 1.0, red 0.8873443, green 0.71340626, blue 0.49955285, hue 0.2036132, saturation 0.49715745, and chroma 0.07674564.
     * It can be represented as a packed float with the constant {@code -0x1.13057cp126F}.
     * <pre>
     * <font style='background-color: #E3B67F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3B67F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3B67F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E3B67F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E3B67F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E3B67F'>&nbsp;@&nbsp;</font><font style='background-color: #E3B67F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3B67F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3B67F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_SAFFRON = -0x1.13057cp126F;
    static { NAMED.put("pale saffron", -0x1.13057cp126F); LIST.add(-0x1.13057cp126F); }

    /**
     * This color constant "drab yellow" has RGBA8888 code {@code 919236FF}, L 0.54901963, A 0.47843137, B 0.5529412,
     * alpha 1.0, red 0.5700739, green 0.57334954, blue 0.2141942, hue 0.30459395, saturation 0.7623625, and chroma 0.11388578.
     * It can be represented as a packed float with the constant {@code -0x1.1af518p126F}.
     * <pre>
     * <font style='background-color: #919236;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #919236; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #919236;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #919236'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #919236'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #919236'>&nbsp;@&nbsp;</font><font style='background-color: #919236; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #919236;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #919236; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_YELLOW = -0x1.1af518p126F;
    static { NAMED.put("drab yellow", -0x1.1af518p126F); LIST.add(-0x1.1af518p126F); }

    /**
     * This color constant "faded yellow" has RGBA8888 code {@code B4B556FF}, L 0.68235296, A 0.47843137, B 0.5529412,
     * alpha 1.0, red 0.7039969, green 0.71007025, blue 0.3371785, hue 0.30459395, saturation 0.66071415, and chroma 0.11388578.
     * It can be represented as a packed float with the constant {@code -0x1.1af55cp126F}.
     * <pre>
     * <font style='background-color: #B4B556;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4B556; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4B556;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B4B556'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B4B556'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B4B556'>&nbsp;@&nbsp;</font><font style='background-color: #B4B556; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4B556;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4B556; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_YELLOW = -0x1.1af55cp126F;
    static { NAMED.put("faded yellow", -0x1.1af55cp126F); LIST.add(-0x1.1af55cp126F); }

    /**
     * This color constant "pale yellow" has RGBA8888 code {@code DBDD79FF}, L 0.8352941, A 0.47843137, B 0.5529412,
     * alpha 1.0, red 0.85756624, green 0.8663303, blue 0.47496915, hue 0.30459395, saturation 0.5829831, and chroma 0.11388578.
     * It can be represented as a packed float with the constant {@code -0x1.1af5aap126F}.
     * <pre>
     * <font style='background-color: #DBDD79;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBDD79; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBDD79;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DBDD79'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DBDD79'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DBDD79'>&nbsp;@&nbsp;</font><font style='background-color: #DBDD79; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBDD79;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBDD79; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOW = -0x1.1af5aap126F;
    static { NAMED.put("pale yellow", -0x1.1af5aap126F); LIST.add(-0x1.1af5aap126F); }

    /**
     * This color constant "drab lime" has RGBA8888 code {@code 6C8C3BFF}, L 0.49803922, A 0.4627451, B 0.54509807,
     * alpha 1.0, red 0.42375824, green 0.55010927, blue 0.230751, hue 0.35241637, saturation 0.6818182, and chroma 0.11653464.
     * It can be represented as a packed float with the constant {@code -0x1.16ecfep126F}.
     * <pre>
     * <font style='background-color: #6C8C3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C8C3B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C8C3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6C8C3B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6C8C3B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6C8C3B'>&nbsp;@&nbsp;</font><font style='background-color: #6C8C3B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C8C3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C8C3B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_LIME = -0x1.16ecfep126F;
    static { NAMED.put("drab lime", -0x1.16ecfep126F); LIST.add(-0x1.16ecfep126F); }

    /**
     * This color constant "faded lime" has RGBA8888 code {@code 8EB15AFF}, L 0.63529414, A 0.4627451, B 0.54509807,
     * alpha 1.0, red 0.55473304, green 0.69333285, blue 0.35519364, hue 0.35241637, saturation 0.5882353, and chroma 0.11653464.
     * It can be represented as a packed float with the constant {@code -0x1.16ed44p126F}.
     * <pre>
     * <font style='background-color: #8EB15A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8EB15A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8EB15A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8EB15A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8EB15A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8EB15A'>&nbsp;@&nbsp;</font><font style='background-color: #8EB15A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8EB15A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8EB15A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_LIME = -0x1.16ed44p126F;
    static { NAMED.put("faded lime", -0x1.16ed44p126F); LIST.add(-0x1.16ed44p126F); }

    /**
     * This color constant "pale lime" has RGBA8888 code {@code B6DD80FF}, L 0.8039216, A 0.4627451, B 0.54509807,
     * alpha 1.0, red 0.7167723, green 0.8681523, blue 0.50733864, hue 0.35241637, saturation 0.5084746, and chroma 0.11653464.
     * It can be represented as a packed float with the constant {@code -0x1.16ed9ap126F}.
     * <pre>
     * <font style='background-color: #B6DD80;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6DD80; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6DD80;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B6DD80'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B6DD80'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B6DD80'>&nbsp;@&nbsp;</font><font style='background-color: #B6DD80; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6DD80;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6DD80; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_LIME = -0x1.16ed9ap126F;
    static { NAMED.put("pale lime", -0x1.16ed9ap126F); LIST.add(-0x1.16ed9ap126F); }

    /**
     * This color constant "drab green" has RGBA8888 code {@code 3A9844FF}, L 0.5019608, A 0.43529412, B 0.5411765,
     * alpha 1.0, red 0.22871135, green 0.59709173, blue 0.26611978, hue 0.404143, saturation 0.70605415, and chroma 0.15279381.
     * It can be represented as a packed float with the constant {@code -0x1.14dfp126F}.
     * <pre>
     * <font style='background-color: #3A9844;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3A9844; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3A9844;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3A9844'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3A9844'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3A9844'>&nbsp;@&nbsp;</font><font style='background-color: #3A9844; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3A9844;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3A9844; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_GREEN = -0x1.14dfp126F;
    static { NAMED.put("drab green", -0x1.14dfp126F); LIST.add(-0x1.14dfp126F); }

    /**
     * This color constant "faded green" has RGBA8888 code {@code 5BBD63FF}, L 0.63529414, A 0.43529412, B 0.5411765,
     * alpha 1.0, red 0.357926, green 0.7404736, blue 0.38692906, hue 0.404143, saturation 0.6067653, and chroma 0.15279381.
     * It can be represented as a packed float with the constant {@code -0x1.14df44p126F}.
     * <pre>
     * <font style='background-color: #5BBD63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5BBD63; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5BBD63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5BBD63'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5BBD63'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5BBD63'>&nbsp;@&nbsp;</font><font style='background-color: #5BBD63; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5BBD63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5BBD63; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_GREEN = -0x1.14df44p126F;
    static { NAMED.put("faded green", -0x1.14df44p126F); LIST.add(-0x1.14df44p126F); }

    /**
     * This color constant "pale green" has RGBA8888 code {@code 7BE182FF}, L 0.76862746, A 0.43529412, B 0.5411765,
     * alpha 1.0, red 0.48339152, green 0.8824829, blue 0.507995, hue 0.404143, saturation 0.53934693, and chroma 0.15279381.
     * It can be represented as a packed float with the constant {@code -0x1.14df88p126F}.
     * <pre>
     * <font style='background-color: #7BE182;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7BE182; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7BE182;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7BE182'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7BE182'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7BE182'>&nbsp;@&nbsp;</font><font style='background-color: #7BE182; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7BE182;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7BE182; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_GREEN = -0x1.14df88p126F;
    static { NAMED.put("pale green", -0x1.14df88p126F); LIST.add(-0x1.14df88p126F); }

    /**
     * This color constant "drab cyan" has RGBA8888 code {@code 3D9192FF}, L 0.5058824, A 0.45882353, B 0.4862745,
     * alpha 1.0, red 0.2413963, green 0.5687937, blue 0.5741133, hue 0.5463868, saturation 0.7200211, and chroma 0.08646853.
     * It can be represented as a packed float with the constant {@code -0x1.f8eb02p125F}.
     * <pre>
     * <font style='background-color: #3D9192;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3D9192; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3D9192;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3D9192'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3D9192'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3D9192'>&nbsp;@&nbsp;</font><font style='background-color: #3D9192; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3D9192;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3D9192; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_CYAN = -0x1.f8eb02p125F;
    static { NAMED.put("drab cyan", -0x1.f8eb02p125F); LIST.add(-0x1.f8eb02p125F); }

    /**
     * This color constant "faded cyan" has RGBA8888 code {@code 5DB4B5FF}, L 0.63529414, A 0.45882353, B 0.4862745,
     * alpha 1.0, red 0.3659762, green 0.70418596, blue 0.7088832, hue 0.5463868, saturation 0.6141357, and chroma 0.08646853.
     * It can be represented as a packed float with the constant {@code -0x1.f8eb44p125F}.
     * <pre>
     * <font style='background-color: #5DB4B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5DB4B5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5DB4B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5DB4B5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5DB4B5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5DB4B5'>&nbsp;@&nbsp;</font><font style='background-color: #5DB4B5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5DB4B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5DB4B5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_CYAN = -0x1.f8eb44p125F;
    static { NAMED.put("faded cyan", -0x1.f8eb44p125F); LIST.add(-0x1.f8eb44p125F); }

    /**
     * This color constant "pale cyan" has RGBA8888 code {@code 83DEDFFF}, L 0.79607844, A 0.45882353, B 0.4862745,
     * alpha 1.0, red 0.51699156, green 0.87131536, blue 0.8753863, hue 0.5463868, saturation 0.53540033, and chroma 0.08646853.
     * It can be represented as a packed float with the constant {@code -0x1.f8eb96p125F}.
     * <pre>
     * <font style='background-color: #83DEDF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #83DEDF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #83DEDF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #83DEDF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #83DEDF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #83DEDF'>&nbsp;@&nbsp;</font><font style='background-color: #83DEDF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #83DEDF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #83DEDF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_CYAN = -0x1.f8eb96p125F;
    static { NAMED.put("pale cyan", -0x1.f8eb96p125F); LIST.add(-0x1.f8eb96p125F); }

    /**
     * This color constant "drab blue" has RGBA8888 code {@code 2041A4FF}, L 0.2901961, A 0.49019608, B 0.41568628,
     * alpha 1.0, red 0.12568842, green 0.2540459, blue 0.64132464, hue 0.73488796, saturation 0.5340512, and chroma 0.16910048.
     * It can be represented as a packed float with the constant {@code -0x1.d4fa94p125F}.
     * <pre>
     * <font style='background-color: #2041A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2041A4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2041A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2041A4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2041A4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2041A4'>&nbsp;@&nbsp;</font><font style='background-color: #2041A4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2041A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2041A4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BLUE = -0x1.d4fa94p125F;
    static { NAMED.put("drab blue", -0x1.d4fa94p125F); LIST.add(-0x1.d4fa94p125F); }

    /**
     * This color constant "faded blue" has RGBA8888 code {@code 3961CCFF}, L 0.40784314, A 0.49019608, B 0.41568628,
     * alpha 1.0, red 0.22409287, green 0.38033095, blue 0.7989912, hue 0.73488796, saturation 0.6392431, and chroma 0.16910048.
     * It can be represented as a packed float with the constant {@code -0x1.d4fadp125F}.
     * <pre>
     * <font style='background-color: #3961CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3961CC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3961CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3961CC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3961CC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3961CC'>&nbsp;@&nbsp;</font><font style='background-color: #3961CC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3961CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3961CC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_BLUE = -0x1.d4fadp125F;
    static { NAMED.put("faded blue", -0x1.d4fadp125F); LIST.add(-0x1.d4fadp125F); }

    /**
     * This color constant "pale blue" has RGBA8888 code {@code 4F7BECFF}, L 0.5058824, A 0.49019608, B 0.41568628,
     * alpha 1.0, red 0.30888873, green 0.48300403, blue 0.92531145, hue 0.73488796, saturation 0.811347, and chroma 0.16910048.
     * It can be represented as a packed float with the constant {@code -0x1.d4fb02p125F}.
     * <pre>
     * <font style='background-color: #4F7BEC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4F7BEC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4F7BEC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4F7BEC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4F7BEC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4F7BEC'>&nbsp;@&nbsp;</font><font style='background-color: #4F7BEC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4F7BEC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4F7BEC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BLUE = -0x1.d4fb02p125F;
    static { NAMED.put("pale blue", -0x1.d4fb02p125F); LIST.add(-0x1.d4fb02p125F); }

    /**
     * This color constant "drab violet" has RGBA8888 code {@code 634095FF}, L 0.33333334, A 0.53333336, B 0.4392157,
     * alpha 1.0, red 0.38679507, green 0.2505382, blue 0.5847745, hue 0.83601046, saturation 0.5221748, and chroma 0.1381068.
     * It can be represented as a packed float with the constant {@code -0x1.e110aap125F}.
     * <pre>
     * <font style='background-color: #634095;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #634095; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #634095;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #634095'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #634095'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #634095'>&nbsp;@&nbsp;</font><font style='background-color: #634095; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #634095;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #634095; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_VIOLET = -0x1.e110aap125F;
    static { NAMED.put("drab violet", -0x1.e110aap125F); LIST.add(-0x1.e110aap125F); }

    /**
     * This color constant "faded violet" has RGBA8888 code {@code 805BB8FF}, L 0.44313726, A 0.53333336, B 0.4392157,
     * alpha 1.0, red 0.50051624, green 0.3589722, blue 0.71974707, hue 0.83601046, saturation 0.47277987, and chroma 0.1381068.
     * It can be represented as a packed float with the constant {@code -0x1.e110e2p125F}.
     * <pre>
     * <font style='background-color: #805BB8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #805BB8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #805BB8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #805BB8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #805BB8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #805BB8'>&nbsp;@&nbsp;</font><font style='background-color: #805BB8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #805BB8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #805BB8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_VIOLET = -0x1.e110e2p125F;
    static { NAMED.put("faded violet", -0x1.e110e2p125F); LIST.add(-0x1.e110e2p125F); }

    /**
     * This color constant "pale violet" has RGBA8888 code {@code A07ADDFF}, L 0.5647059, A 0.53333336, B 0.4392157,
     * alpha 1.0, red 0.6258228, green 0.47817966, blue 0.86493677, hue 0.83601046, saturation 0.64788353, and chroma 0.1381068.
     * It can be represented as a packed float with the constant {@code -0x1.e1112p125F}.
     * <pre>
     * <font style='background-color: #A07ADD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A07ADD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A07ADD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A07ADD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A07ADD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A07ADD'>&nbsp;@&nbsp;</font><font style='background-color: #A07ADD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A07ADD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A07ADD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_VIOLET = -0x1.e1112p125F;
    static { NAMED.put("pale violet", -0x1.e1112p125F); LIST.add(-0x1.e1112p125F); }

    /**
     * This color constant "drab purple" has RGBA8888 code {@code 753697FF}, L 0.34117648, A 0.5529412, B 0.4392157,
     * alpha 1.0, red 0.4606613, green 0.21383731, blue 0.5901884, hue 0.86951405, saturation 0.64119637, and chroma 0.1605844.
     * It can be represented as a packed float with the constant {@code -0x1.e11aaep125F}.
     * <pre>
     * <font style='background-color: #753697;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #753697; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #753697;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #753697'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #753697'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #753697'>&nbsp;@&nbsp;</font><font style='background-color: #753697; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #753697;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #753697; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_PURPLE = -0x1.e11aaep125F;
    static { NAMED.put("drab purple", -0x1.e11aaep125F); LIST.add(-0x1.e11aaep125F); }

    /**
     * This color constant "faded purple" has RGBA8888 code {@code 9754BBFF}, L 0.45882353, A 0.5529412, B 0.4392157,
     * alpha 1.0, red 0.5916718, green 0.33007064, blue 0.73404557, hue 0.86951405, saturation 0.5261098, and chroma 0.1605844.
     * It can be represented as a packed float with the constant {@code -0x1.e11aeap125F}.
     * <pre>
     * <font style='background-color: #9754BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9754BB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9754BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9754BB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9754BB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9754BB'>&nbsp;@&nbsp;</font><font style='background-color: #9754BB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9754BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9754BB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_PURPLE = -0x1.e11aeap125F;
    static { NAMED.put("faded purple", -0x1.e11aeap125F); LIST.add(-0x1.e11aeap125F); }

    /**
     * This color constant "pale purple" has RGBA8888 code {@code BF77E6FF}, L 0.6, A 0.5529412, B 0.4392157,
     * alpha 1.0, red 0.7461768, green 0.46716338, blue 0.9015479, hue 0.86951405, saturation 0.70752704, and chroma 0.1605844.
     * It can be represented as a packed float with the constant {@code -0x1.e11b32p125F}.
     * <pre>
     * <font style='background-color: #BF77E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF77E6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF77E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BF77E6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BF77E6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BF77E6'>&nbsp;@&nbsp;</font><font style='background-color: #BF77E6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF77E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF77E6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLE = -0x1.e11b32p125F;
    static { NAMED.put("pale purple", -0x1.e11b32p125F); LIST.add(-0x1.e11b32p125F); }

    /**
     * This color constant "drab magenta" has RGBA8888 code {@code 994294FF}, L 0.40784314, A 0.5647059, B 0.45882353,
     * alpha 1.0, red 0.59798694, green 0.26139998, blue 0.57846946, hue 0.9153735, saturation 0.5976692, and chroma 0.15279381.
     * It can be represented as a packed float with the constant {@code -0x1.eb20dp125F}.
     * <pre>
     * <font style='background-color: #994294;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #994294; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #994294;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #994294'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #994294'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #994294'>&nbsp;@&nbsp;</font><font style='background-color: #994294; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #994294;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #994294; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_MAGENTA = -0x1.eb20dp125F;
    static { NAMED.put("drab magenta", -0x1.eb20dp125F); LIST.add(-0x1.eb20dp125F); }

    /**
     * This color constant "faded magenta" has RGBA8888 code {@code BF62B9FF}, L 0.5372549, A 0.5647059, B 0.45882353,
     * alpha 1.0, red 0.7496142, green 0.3859107, blue 0.7249919, hue 0.9153735, saturation 0.4930771, and chroma 0.15279381.
     * It can be represented as a packed float with the constant {@code -0x1.eb2112p125F}.
     * <pre>
     * <font style='background-color: #BF62B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF62B9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF62B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BF62B9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BF62B9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BF62B9'>&nbsp;@&nbsp;</font><font style='background-color: #BF62B9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF62B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF62B9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_MAGENTA = -0x1.eb2112p125F;
    static { NAMED.put("faded magenta", -0x1.eb2112p125F); LIST.add(-0x1.eb2112p125F); }

    /**
     * This color constant "pale magenta" has RGBA8888 code {@code E27FDBFF}, L 0.654902, A 0.5647059, B 0.45882353,
     * alpha 1.0, red 0.884564, green 0.49839285, blue 0.85597366, hue 0.9153735, saturation 0.58874875, and chroma 0.15279381.
     * It can be represented as a packed float with the constant {@code -0x1.eb214ep125F}.
     * <pre>
     * <font style='background-color: #E27FDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E27FDB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E27FDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E27FDB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E27FDB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E27FDB'>&nbsp;@&nbsp;</font><font style='background-color: #E27FDB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E27FDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E27FDB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_MAGENTA = -0x1.eb214ep125F;
    static { NAMED.put("pale magenta", -0x1.eb214ep125F); LIST.add(-0x1.eb214ep125F); }

    /**
     * This color constant "deep pure red" has RGBA8888 code {@code C3251EFF}, L 0.4, A 0.58431375, B 0.54509807,
     * alpha 1.0, red 0.763523, green 0.14532664, blue 0.12061677, hue 0.0794735, saturation 0.8641355, and chroma 0.19048727.
     * It can be represented as a packed float with the constant {@code -0x1.172accp126F}.
     * <pre>
     * <font style='background-color: #C3251E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3251E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3251E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C3251E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C3251E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C3251E'>&nbsp;@&nbsp;</font><font style='background-color: #C3251E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3251E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3251E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_RED = -0x1.172accp126F;
    static { NAMED.put("deep pure red", -0x1.172accp126F); LIST.add(-0x1.172accp126F); }

    /**
     * This color constant "true pure red" has RGBA8888 code {@code D8372DFF}, L 0.45882353, A 0.58431375, B 0.54509807,
     * alpha 1.0, red 0.8437963, green 0.21524148, blue 0.17693122, hue 0.0794735, saturation 0.7831228, and chroma 0.19048727.
     * It can be represented as a packed float with the constant {@code -0x1.172aeap126F}.
     * <pre>
     * <font style='background-color: #D8372D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D8372D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D8372D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D8372D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D8372D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D8372D'>&nbsp;@&nbsp;</font><font style='background-color: #D8372D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D8372D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D8372D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_RED = -0x1.172aeap126F;
    static { NAMED.put("true pure red", -0x1.172aeap126F); LIST.add(-0x1.172aeap126F); }

    /**
     * This color constant "bright pure red" has RGBA8888 code {@code F04A3DFF}, L 0.5294118, A 0.58431375, B 0.54509807,
     * alpha 1.0, red 0.93800914, green 0.29072824, blue 0.24092549, hue 0.0794735, saturation 0.82163703, and chroma 0.19048727.
     * It can be represented as a packed float with the constant {@code -0x1.172b0ep126F}.
     * <pre>
     * <font style='background-color: #F04A3D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F04A3D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F04A3D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F04A3D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F04A3D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F04A3D'>&nbsp;@&nbsp;</font><font style='background-color: #F04A3D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F04A3D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F04A3D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_RED = -0x1.172b0ep126F;
    static { NAMED.put("bright pure red", -0x1.172b0ep126F); LIST.add(-0x1.172b0ep126F); }

    /**
     * This color constant "deep brown red" has RGBA8888 code {@code B34834FF}, L 0.42352942, A 0.5568628, B 0.5372549,
     * alpha 1.0, red 0.7001068, green 0.28397954, blue 0.2037034, hue 0.09358352, saturation 0.62164676, and chroma 0.13542919.
     * It can be represented as a packed float with the constant {@code -0x1.131cd8p126F}.
     * <pre>
     * <font style='background-color: #B34834;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B34834; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B34834;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B34834'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B34834'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B34834'>&nbsp;@&nbsp;</font><font style='background-color: #B34834; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B34834;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B34834; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN_RED = -0x1.131cd8p126F;
    static { NAMED.put("deep brown red", -0x1.131cd8p126F); LIST.add(-0x1.131cd8p126F); }

    /**
     * This color constant "true brown red" has RGBA8888 code {@code C65741FF}, L 0.48235294, A 0.5568628, B 0.5372549,
     * alpha 1.0, red 0.7739858, green 0.34155482, blue 0.25605017, hue 0.09358352, saturation 0.57230973, and chroma 0.13542919.
     * It can be represented as a packed float with the constant {@code -0x1.131cf6p126F}.
     * <pre>
     * <font style='background-color: #C65741;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C65741; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C65741;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C65741'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C65741'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C65741'>&nbsp;@&nbsp;</font><font style='background-color: #C65741; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C65741;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C65741; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BROWN_RED = -0x1.131cf6p126F;
    static { NAMED.put("true brown red", -0x1.131cf6p126F); LIST.add(-0x1.131cf6p126F); }

    /**
     * This color constant "bright brown red" has RGBA8888 code {@code D9674FFF}, L 0.54509807, A 0.5568628, B 0.5372549,
     * alpha 1.0, red 0.85154957, green 0.40253192, blue 0.31181282, hue 0.09358352, saturation 0.6111104, and chroma 0.13542919.
     * It can be represented as a packed float with the constant {@code -0x1.131d16p126F}.
     * <pre>
     * <font style='background-color: #D9674F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9674F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9674F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D9674F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D9674F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D9674F'>&nbsp;@&nbsp;</font><font style='background-color: #D9674F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9674F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9674F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN_RED = -0x1.131d16p126F;
    static { NAMED.put("bright brown red", -0x1.131d16p126F); LIST.add(-0x1.131d16p126F); }

    /**
     * This color constant "deep red brown" has RGBA8888 code {@code AE563FFF}, L 0.44313726, A 0.54509807, B 0.53333336,
     * alpha 1.0, red 0.6800962, green 0.33737075, blue 0.24729776, hue 0.102416374, saturation 0.5660377, and chroma 0.11172148.
     * It can be represented as a packed float with the constant {@code -0x1.1116e2p126F}.
     * <pre>
     * <font style='background-color: #AE563F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE563F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE563F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AE563F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AE563F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AE563F'>&nbsp;@&nbsp;</font><font style='background-color: #AE563F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE563F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE563F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED_BROWN = -0x1.1116e2p126F;
    static { NAMED.put("deep red brown", -0x1.1116e2p126F); LIST.add(-0x1.1116e2p126F); }

    /**
     * This color constant "true red brown" has RGBA8888 code {@code C0644CFF}, L 0.5019608, A 0.54509807, B 0.53333336,
     * alpha 1.0, red 0.7510379, green 0.39434993, blue 0.29970473, hue 0.102416374, saturation 0.51724136, and chroma 0.11172148.
     * It can be represented as a packed float with the constant {@code -0x1.1117p126F}.
     * <pre>
     * <font style='background-color: #C0644C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0644C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0644C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C0644C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C0644C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C0644C'>&nbsp;@&nbsp;</font><font style='background-color: #C0644C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0644C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0644C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_RED_BROWN = -0x1.1117p126F;
    static { NAMED.put("true red brown", -0x1.1117p126F); LIST.add(-0x1.1117p126F); }

    /**
     * This color constant "bright red brown" has RGBA8888 code {@code D5765CFF}, L 0.57254905, A 0.54509807, B 0.53333336,
     * alpha 1.0, red 0.8350033, green 0.46265268, blue 0.36287507, hue 0.102416374, saturation 0.5555556, and chroma 0.11172148.
     * It can be represented as a packed float with the constant {@code -0x1.111724p126F}.
     * <pre>
     * <font style='background-color: #D5765C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5765C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5765C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D5765C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D5765C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D5765C'>&nbsp;@&nbsp;</font><font style='background-color: #D5765C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5765C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5765C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED_BROWN = -0x1.111724p126F;
    static { NAMED.put("bright red brown", -0x1.111724p126F); LIST.add(-0x1.111724p126F); }

    /**
     * This color constant "deep pure brown" has RGBA8888 code {@code A6674CFF}, L 0.47058824, A 0.5294118, B 0.5294118,
     * alpha 1.0, red 0.6514757, green 0.40293592, blue 0.29745582, hue 0.124999985, saturation 0.47140452, and chroma 0.082864076.
     * It can be represented as a packed float with the constant {@code -0x1.0f0efp126F}.
     * <pre>
     * <font style='background-color: #A6674C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A6674C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A6674C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A6674C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A6674C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A6674C'>&nbsp;@&nbsp;</font><font style='background-color: #A6674C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A6674C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A6674C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_BROWN = -0x1.0f0efp126F;
    static { NAMED.put("deep pure brown", -0x1.0f0efp126F); LIST.add(-0x1.0f0efp126F); }

    /**
     * This color constant "true pure brown" has RGBA8888 code {@code BA775BFF}, L 0.5372549, A 0.5294118, B 0.5294118,
     * alpha 1.0, red 0.7276369, green 0.4678294, blue 0.35752904, hue 0.124999985, saturation 0.43514264, and chroma 0.082864076.
     * It can be represented as a packed float with the constant {@code -0x1.0f0f12p126F}.
     * <pre>
     * <font style='background-color: #BA775B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA775B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA775B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BA775B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BA775B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BA775B'>&nbsp;@&nbsp;</font><font style='background-color: #BA775B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA775B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA775B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_BROWN = -0x1.0f0f12p126F;
    static { NAMED.put("true pure brown", -0x1.0f0f12p126F); LIST.add(-0x1.0f0f12p126F); }

    /**
     * This color constant "bright pure brown" has RGBA8888 code {@code CC876AFF}, L 0.6, A 0.5294118, B 0.5294118,
     * alpha 1.0, red 0.79862636, green 0.528968, blue 0.414408, hue 0.124999985, saturation 0.44367483, and chroma 0.082864076.
     * It can be represented as a packed float with the constant {@code -0x1.0f0f32p126F}.
     * <pre>
     * <font style='background-color: #CC876A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CC876A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CC876A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CC876A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CC876A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CC876A'>&nbsp;@&nbsp;</font><font style='background-color: #CC876A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CC876A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CC876A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_BROWN = -0x1.0f0f32p126F;
    static { NAMED.put("bright pure brown", -0x1.0f0f32p126F); LIST.add(-0x1.0f0f32p126F); }

    /**
     * This color constant "deep orange brown" has RGBA8888 code {@code AF6748FF}, L 0.48235294, A 0.53333336, B 0.53333336,
     * alpha 1.0, red 0.685809, green 0.403801, blue 0.2840319, hue 0.124999985, saturation 0.51950705, and chroma 0.09391262.
     * It can be represented as a packed float with the constant {@code -0x1.1110f6p126F}.
     * <pre>
     * <font style='background-color: #AF6748;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF6748; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF6748;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AF6748'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AF6748'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AF6748'>&nbsp;@&nbsp;</font><font style='background-color: #AF6748; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF6748;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF6748; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_BROWN = -0x1.1110f6p126F;
    static { NAMED.put("deep orange brown", -0x1.1110f6p126F); LIST.add(-0x1.1110f6p126F); }

    /**
     * This color constant "true orange brown" has RGBA8888 code {@code C17656FF}, L 0.54509807, A 0.53333336, B 0.53333336,
     * alpha 1.0, red 0.7584695, green 0.46481818, blue 0.34018955, hue 0.124999985, saturation 0.48029897, and chroma 0.09391262.
     * It can be represented as a packed float with the constant {@code -0x1.111116p126F}.
     * <pre>
     * <font style='background-color: #C17656;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C17656; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C17656;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C17656'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C17656'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C17656'>&nbsp;@&nbsp;</font><font style='background-color: #C17656; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C17656;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C17656; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_ORANGE_BROWN = -0x1.111116p126F;
    static { NAMED.put("true orange brown", -0x1.111116p126F); LIST.add(-0x1.111116p126F); }

    /**
     * This color constant "bright orange brown" has RGBA8888 code {@code D58766FF}, L 0.6117647, A 0.53333336, B 0.53333336,
     * alpha 1.0, red 0.8348767, green 0.52969307, blue 0.40015408, hue 0.124999985, saturation 0.51950705, and chroma 0.09391262.
     * It can be represented as a packed float with the constant {@code -0x1.111138p126F}.
     * <pre>
     * <font style='background-color: #D58766;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D58766; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D58766;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D58766'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D58766'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D58766'>&nbsp;@&nbsp;</font><font style='background-color: #D58766; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D58766;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D58766; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE_BROWN = -0x1.111138p126F;
    static { NAMED.put("bright orange brown", -0x1.111138p126F); LIST.add(-0x1.111138p126F); }

    /**
     * This color constant "deep brown orange" has RGBA8888 code {@code AF6B44FF}, L 0.49019608, A 0.5294118, B 0.5372549,
     * alpha 1.0, red 0.6872305, green 0.4185993, blue 0.26719537, hue 0.14261165, saturation 0.58210224, and chroma 0.0945603.
     * It can be represented as a packed float with the constant {@code -0x1.130efap126F}.
     * <pre>
     * <font style='background-color: #AF6B44;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF6B44; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF6B44;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AF6B44'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AF6B44'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AF6B44'>&nbsp;@&nbsp;</font><font style='background-color: #AF6B44; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF6B44;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF6B44; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN_ORANGE = -0x1.130efap126F;
    static { NAMED.put("deep brown orange", -0x1.130efap126F); LIST.add(-0x1.130efap126F); }

    /**
     * This color constant "true brown orange" has RGBA8888 code {@code C27A52FF}, L 0.5529412, A 0.5294118, B 0.5372549,
     * alpha 1.0, red 0.7593211, green 0.47986168, blue 0.3232126, hue 0.14261165, saturation 0.5335937, and chroma 0.0945603.
     * It can be represented as a packed float with the constant {@code -0x1.130f1ap126F}.
     * <pre>
     * <font style='background-color: #C27A52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C27A52; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C27A52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C27A52'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C27A52'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C27A52'>&nbsp;@&nbsp;</font><font style='background-color: #C27A52; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C27A52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C27A52; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BROWN_ORANGE = -0x1.130f1ap126F;
    static { NAMED.put("true brown orange", -0x1.130f1ap126F); LIST.add(-0x1.130f1ap126F); }

    /**
     * This color constant "bright brown orange" has RGBA8888 code {@code D88D63FF}, L 0.627451, A 0.5294118, B 0.5372549,
     * alpha 1.0, red 0.84405553, green 0.55265355, blue 0.3899325, hue 0.14261165, saturation 0.522704, and chroma 0.0945603.
     * It can be represented as a packed float with the constant {@code -0x1.130f4p126F}.
     * <pre>
     * <font style='background-color: #D88D63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D88D63; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D88D63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D88D63'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D88D63'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D88D63'>&nbsp;@&nbsp;</font><font style='background-color: #D88D63; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D88D63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D88D63; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN_ORANGE = -0x1.130f4p126F;
    static { NAMED.put("bright brown orange", -0x1.130f4p126F); LIST.add(-0x1.130f4p126F); }

    /**
     * This color constant "deep pure orange" has RGBA8888 code {@code C66A2CFF}, L 0.5176471, A 0.5372549, B 0.5529412,
     * alpha 1.0, red 0.77468365, green 0.41782433, blue 0.17496398, hue 0.15128423, saturation 0.78202957, and chroma 0.12896542.
     * It can be represented as a packed float with the constant {@code -0x1.1b1308p126F}.
     * <pre>
     * <font style='background-color: #C66A2C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C66A2C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C66A2C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C66A2C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C66A2C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C66A2C'>&nbsp;@&nbsp;</font><font style='background-color: #C66A2C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C66A2C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C66A2C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_ORANGE = -0x1.1b1308p126F;
    static { NAMED.put("deep pure orange", -0x1.1b1308p126F); LIST.add(-0x1.1b1308p126F); }

    /**
     * This color constant "true pure orange" has RGBA8888 code {@code DA7B3CFF}, L 0.58431375, A 0.5372549, B 0.5529412,
     * alpha 1.0, red 0.85375893, green 0.48313814, blue 0.23823985, hue 0.15128423, saturation 0.7321128, and chroma 0.12896542.
     * It can be represented as a packed float with the constant {@code -0x1.1b132ap126F}.
     * <pre>
     * <font style='background-color: #DA7B3C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA7B3C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA7B3C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DA7B3C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DA7B3C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DA7B3C'>&nbsp;@&nbsp;</font><font style='background-color: #DA7B3C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA7B3C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA7B3C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_ORANGE = -0x1.1b132ap126F;
    static { NAMED.put("true pure orange", -0x1.1b132ap126F); LIST.add(-0x1.1b132ap126F); }

    /**
     * This color constant "bright pure orange" has RGBA8888 code {@code EE8C4CFF}, L 0.6509804, A 0.5372549, B 0.5529412,
     * alpha 1.0, red 0.93193644, green 0.5483832, blue 0.29913303, hue 0.15128423, saturation 0.7480283, and chroma 0.12896542.
     * It can be represented as a packed float with the constant {@code -0x1.1b134cp126F}.
     * <pre>
     * <font style='background-color: #EE8C4C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EE8C4C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EE8C4C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EE8C4C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EE8C4C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EE8C4C'>&nbsp;@&nbsp;</font><font style='background-color: #EE8C4C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EE8C4C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EE8C4C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_ORANGE = -0x1.1b134cp126F;
    static { NAMED.put("bright pure orange", -0x1.1b134cp126F); LIST.add(-0x1.1b134cp126F); }

    /**
     * This color constant "deep saffron orange" has RGBA8888 code {@code C17732FF}, L 0.5372549, A 0.5254902, B 0.5529412,
     * alpha 1.0, red 0.7543174, green 0.4654258, blue 0.19578457, hue 0.1762082, saturation 0.7826238, and chroma 0.11705722.
     * It can be represented as a packed float with the constant {@code -0x1.1b0d12p126F}.
     * <pre>
     * <font style='background-color: #C17732;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C17732; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C17732;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C17732'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C17732'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C17732'>&nbsp;@&nbsp;</font><font style='background-color: #C17732; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C17732;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C17732; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_SAFFRON_ORANGE = -0x1.1b0d12p126F;
    static { NAMED.put("deep saffron orange", -0x1.1b0d12p126F); LIST.add(-0x1.1b0d12p126F); }

    /**
     * This color constant "true saffron orange" has RGBA8888 code {@code D28540FF}, L 0.59607846, A 0.5254902, B 0.5529412,
     * alpha 1.0, red 0.821828, green 0.52336293, blue 0.2509448, hue 0.1762082, saturation 0.72802216, and chroma 0.11705722.
     * It can be represented as a packed float with the constant {@code -0x1.1b0d3p126F}.
     * <pre>
     * <font style='background-color: #D28540;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D28540; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D28540;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D28540'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D28540'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D28540'>&nbsp;@&nbsp;</font><font style='background-color: #D28540; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D28540;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D28540; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_SAFFRON_ORANGE = -0x1.1b0d3p126F;
    static { NAMED.put("true saffron orange", -0x1.1b0d3p126F); LIST.add(-0x1.1b0d3p126F); }

    /**
     * This color constant "bright saffron orange" has RGBA8888 code {@code E99952FF}, L 0.6745098, A 0.5254902, B 0.5529412,
     * alpha 1.0, red 0.91099584, green 0.60059273, blue 0.3223078, hue 0.1762082, saturation 0.68054247, and chroma 0.11705722.
     * It can be represented as a packed float with the constant {@code -0x1.1b0d58p126F}.
     * <pre>
     * <font style='background-color: #E99952;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E99952; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E99952;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E99952'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E99952'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E99952'>&nbsp;@&nbsp;</font><font style='background-color: #E99952; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E99952;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E99952; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_SAFFRON_ORANGE = -0x1.1b0d58p126F;
    static { NAMED.put("bright saffron orange", -0x1.1b0d58p126F); LIST.add(-0x1.1b0d58p126F); }

    /**
     * This color constant "deep orange saffron" has RGBA8888 code {@code CB8127FF}, L 0.57254905, A 0.52156866, B 0.56078434,
     * alpha 1.0, red 0.7941241, green 0.50466925, blue 0.15547071, hue 0.19289988, saturation 0.85440034, and chroma 0.12849128.
     * It can be represented as a packed float with the constant {@code -0x1.1f0b24p126F}.
     * <pre>
     * <font style='background-color: #CB8127;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CB8127; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CB8127;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CB8127'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CB8127'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CB8127'>&nbsp;@&nbsp;</font><font style='background-color: #CB8127; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CB8127;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CB8127; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_SAFFRON = -0x1.1f0b24p126F;
    static { NAMED.put("deep orange saffron", -0x1.1f0b24p126F); LIST.add(-0x1.1f0b24p126F); }

    /**
     * This color constant "true orange saffron" has RGBA8888 code {@code E1943BFF}, L 0.6509804, A 0.52156866, B 0.56078434,
     * alpha 1.0, red 0.88337696, green 0.58231884, blue 0.23526627, hue 0.19289988, saturation 0.7767276, and chroma 0.12849128.
     * It can be represented as a packed float with the constant {@code -0x1.1f0b4cp126F}.
     * <pre>
     * <font style='background-color: #E1943B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1943B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1943B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E1943B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E1943B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E1943B'>&nbsp;@&nbsp;</font><font style='background-color: #E1943B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1943B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1943B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_ORANGE_SAFFRON = -0x1.1f0b4cp126F;
    static { NAMED.put("true orange saffron", -0x1.1f0b4cp126F); LIST.add(-0x1.1f0b4cp126F); }

    /**
     * This color constant "bright orange saffron" has RGBA8888 code {@code F3A34AFF}, L 0.70980394, A 0.52156866, B 0.56078434,
     * alpha 1.0, red 0.94975626, green 0.64052707, blue 0.2908256, hue 0.19289988, saturation 0.759467, and chroma 0.12849128.
     * It can be represented as a packed float with the constant {@code -0x1.1f0b6ap126F}.
     * <pre>
     * <font style='background-color: #F3A34A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3A34A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3A34A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F3A34A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F3A34A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F3A34A'>&nbsp;@&nbsp;</font><font style='background-color: #F3A34A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3A34A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3A34A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE_SAFFRON = -0x1.1f0b6ap126F;
    static { NAMED.put("bright orange saffron", -0x1.1f0b6ap126F); LIST.add(-0x1.1f0b6ap126F); }

    /**
     * This color constant "deep pure saffron" has RGBA8888 code {@code C38834FF}, L 0.5803922, A 0.5137255, B 0.5568628,
     * alpha 1.0, red 0.76442504, green 0.5325157, blue 0.20435438, hue 0.20852384, saturation 0.7961115, and chroma 0.11653464.
     * It can be represented as a packed float with the constant {@code -0x1.1d0728p126F}.
     * <pre>
     * <font style='background-color: #C38834;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C38834; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C38834;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C38834'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C38834'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C38834'>&nbsp;@&nbsp;</font><font style='background-color: #C38834; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C38834;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C38834; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_SAFFRON = -0x1.1d0728p126F;
    static { NAMED.put("deep pure saffron", -0x1.1d0728p126F); LIST.add(-0x1.1d0728p126F); }

    /**
     * This color constant "true pure saffron" has RGBA8888 code {@code D79A45FF}, L 0.6509804, A 0.5137255, B 0.5568628,
     * alpha 1.0, red 0.8428075, green 0.6026875, blue 0.27120563, hue 0.20852384, saturation 0.7392464, and chroma 0.11653464.
     * It can be represented as a packed float with the constant {@code -0x1.1d074cp126F}.
     * <pre>
     * <font style='background-color: #D79A45;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D79A45; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D79A45;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D79A45'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D79A45'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D79A45'>&nbsp;@&nbsp;</font><font style='background-color: #D79A45; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D79A45;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D79A45; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_SAFFRON = -0x1.1d074cp126F;
    static { NAMED.put("true pure saffron", -0x1.1d074cp126F); LIST.add(-0x1.1d074cp126F); }

    /**
     * This color constant "bright pure saffron" has RGBA8888 code {@code EDAE57FF}, L 0.7294118, A 0.5137255, B 0.5568628,
     * alpha 1.0, red 0.92925805, green 0.6806281, blue 0.34293398, hue 0.20852384, saturation 0.68996334, and chroma 0.11653464.
     * It can be represented as a packed float with the constant {@code -0x1.1d0774p126F}.
     * <pre>
     * <font style='background-color: #EDAE57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDAE57; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDAE57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EDAE57'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EDAE57'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EDAE57'>&nbsp;@&nbsp;</font><font style='background-color: #EDAE57; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDAE57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDAE57; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_SAFFRON = -0x1.1d0774p126F;
    static { NAMED.put("bright pure saffron", -0x1.1d0774p126F); LIST.add(-0x1.1d0774p126F); }

    /**
     * This color constant "deep yellow saffron" has RGBA8888 code {@code C59519FF}, L 0.6117647, A 0.5019608, B 0.5686275,
     * alpha 1.0, red 0.7734264, green 0.5828963, blue 0.09899864, hue 0.24116714, saturation 0.9245003, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.230138p126F}.
     * <pre>
     * <font style='background-color: #C59519;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C59519; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C59519;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C59519'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C59519'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C59519'>&nbsp;@&nbsp;</font><font style='background-color: #C59519; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C59519;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C59519; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_SAFFRON = -0x1.230138p126F;
    static { NAMED.put("deep yellow saffron", -0x1.230138p126F); LIST.add(-0x1.230138p126F); }

    /**
     * This color constant "true yellow saffron" has RGBA8888 code {@code DAA831FF}, L 0.6862745, A 0.5019608, B 0.5686275,
     * alpha 1.0, red 0.85437727, green 0.6578713, blue 0.1928531, hue 0.24116714, saturation 0.85846454, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.23015ep126F}.
     * <pre>
     * <font style='background-color: #DAA831;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAA831; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAA831;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DAA831'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DAA831'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DAA831'>&nbsp;@&nbsp;</font><font style='background-color: #DAA831; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAA831;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAA831; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_YELLOW_SAFFRON = -0x1.23015ep126F;
    static { NAMED.put("true yellow saffron", -0x1.23015ep126F); LIST.add(-0x1.23015ep126F); }

    /**
     * This color constant "bright yellow saffron" has RGBA8888 code {@code F2BE47FF}, L 0.77254903, A 0.5019608, B 0.5686275,
     * alpha 1.0, red 0.9475419, green 0.7445867, blue 0.28052586, hue 0.24116714, saturation 0.78381544, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.23018ap126F}.
     * <pre>
     * <font style='background-color: #F2BE47;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2BE47; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2BE47;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F2BE47'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F2BE47'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F2BE47'>&nbsp;@&nbsp;</font><font style='background-color: #F2BE47; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2BE47;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2BE47; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_YELLOW_SAFFRON = -0x1.23018ap126F;
    static { NAMED.put("bright yellow saffron", -0x1.23018ap126F); LIST.add(-0x1.23018ap126F); }

    /**
     * This color constant "deep saffron yellow" has RGBA8888 code {@code C7B23DFF}, L 0.6901961, A 0.4862745, B 0.5647059,
     * alpha 1.0, red 0.7776154, green 0.6973172, blue 0.24161388, hue 0.27779996, saturation 0.80291516, and chroma 0.13177444.
     * It can be represented as a packed float with the constant {@code -0x1.20f96p126F}.
     * <pre>
     * <font style='background-color: #C7B23D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7B23D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7B23D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C7B23D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C7B23D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C7B23D'>&nbsp;@&nbsp;</font><font style='background-color: #C7B23D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7B23D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7B23D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_SAFFRON_YELLOW = -0x1.20f96p126F;
    static { NAMED.put("deep saffron yellow", -0x1.20f96p126F); LIST.add(-0x1.20f96p126F); }

    /**
     * This color constant "true saffron yellow" has RGBA8888 code {@code DAC550FF}, L 0.7647059, A 0.4862745, B 0.5647059,
     * alpha 1.0, red 0.8547572, green 0.773105, blue 0.31296638, hue 0.27779996, saturation 0.75055116, and chroma 0.13177444.
     * It can be represented as a packed float with the constant {@code -0x1.20f986p126F}.
     * <pre>
     * <font style='background-color: #DAC550;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAC550; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAC550;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DAC550'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DAC550'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DAC550'>&nbsp;@&nbsp;</font><font style='background-color: #DAC550; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAC550;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAC550; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_SAFFRON_YELLOW = -0x1.20f986p126F;
    static { NAMED.put("true saffron yellow", -0x1.20f986p126F); LIST.add(-0x1.20f986p126F); }

    /**
     * This color constant "bright saffron yellow" has RGBA8888 code {@code EDD860FF}, L 0.8392157, A 0.4862745, B 0.5647059,
     * alpha 1.0, red 0.9317355, green 0.848792, blue 0.38184413, hue 0.27779996, saturation 0.704599, and chroma 0.13177444.
     * It can be represented as a packed float with the constant {@code -0x1.20f9acp126F}.
     * <pre>
     * <font style='background-color: #EDD860;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDD860; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDD860;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EDD860'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EDD860'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EDD860'>&nbsp;@&nbsp;</font><font style='background-color: #EDD860; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDD860;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDD860; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_SAFFRON_YELLOW = -0x1.20f9acp126F;
    static { NAMED.put("bright saffron yellow", -0x1.20f9acp126F); LIST.add(-0x1.20f9acp126F); }

    /**
     * This color constant "deep pure yellow" has RGBA8888 code {@code C3C223FF}, L 0.7294118, A 0.47058824, B 0.5764706,
     * alpha 1.0, red 0.7672535, green 0.7635328, blue 0.14227743, hue 0.30358344, saturation 0.901686, and chroma 0.1632233.
     * It can be represented as a packed float with the constant {@code -0x1.26f174p126F}.
     * <pre>
     * <font style='background-color: #C3C223;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3C223; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3C223;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C3C223'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C3C223'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C3C223'>&nbsp;@&nbsp;</font><font style='background-color: #C3C223; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3C223;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3C223; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_YELLOW = -0x1.26f174p126F;
    static { NAMED.put("deep pure yellow", -0x1.26f174p126F); LIST.add(-0x1.26f174p126F); }

    /**
     * This color constant "true pure yellow" has RGBA8888 code {@code D6D63AFF}, L 0.8039216, A 0.47058824, B 0.5764706,
     * alpha 1.0, red 0.84223205, green 0.8404028, blue 0.23186837, hue 0.30358344, saturation 0.84758484, and chroma 0.1632233.
     * It can be represented as a packed float with the constant {@code -0x1.26f19ap126F}.
     * <pre>
     * <font style='background-color: #D6D63A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D6D63A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D6D63A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D6D63A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D6D63A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D6D63A'>&nbsp;@&nbsp;</font><font style='background-color: #D6D63A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D6D63A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D6D63A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_YELLOW = -0x1.26f19ap126F;
    static { NAMED.put("true pure yellow", -0x1.26f19ap126F); LIST.add(-0x1.26f19ap126F); }

    /**
     * This color constant "bright pure yellow" has RGBA8888 code {@code ECEC50FF}, L 0.8862745, A 0.47058824, B 0.5764706,
     * alpha 1.0, red 0.9250825, green 0.92517936, blue 0.31621668, hue 0.30358344, saturation 0.79960835, and chroma 0.1632233.
     * It can be represented as a packed float with the constant {@code -0x1.26f1c4p126F}.
     * <pre>
     * <font style='background-color: #ECEC50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ECEC50; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ECEC50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ECEC50'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ECEC50'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ECEC50'>&nbsp;@&nbsp;</font><font style='background-color: #ECEC50; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ECEC50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ECEC50; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_YELLOW = -0x1.26f1c4p126F;
    static { NAMED.put("bright pure yellow", -0x1.26f1c4p126F); LIST.add(-0x1.26f1c4p126F); }

    /**
     * This color constant "deep lime yellow" has RGBA8888 code {@code B1BD35FF}, L 0.69803923, A 0.46666667, B 0.5686275,
     * alpha 1.0, red 0.6962519, green 0.7425177, blue 0.21157598, hue 0.31656247, saturation 0.8382007, and chroma 0.15199278.
     * It can be represented as a packed float with the constant {@code -0x1.22ef64p126F}.
     * <pre>
     * <font style='background-color: #B1BD35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1BD35; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1BD35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B1BD35'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B1BD35'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B1BD35'>&nbsp;@&nbsp;</font><font style='background-color: #B1BD35; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1BD35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1BD35; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_LIME_YELLOW = -0x1.22ef64p126F;
    static { NAMED.put("deep lime yellow", -0x1.22ef64p126F); LIST.add(-0x1.22ef64p126F); }

    /**
     * This color constant "true lime yellow" has RGBA8888 code {@code C6D24AFF}, L 0.7764706, A 0.46666667, B 0.5686275,
     * alpha 1.0, red 0.7738652, green 0.82369494, blue 0.29037455, hue 0.31656247, saturation 0.77245945, and chroma 0.15199278.
     * It can be represented as a packed float with the constant {@code -0x1.22ef8cp126F}.
     * <pre>
     * <font style='background-color: #C6D24A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C6D24A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C6D24A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C6D24A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C6D24A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C6D24A'>&nbsp;@&nbsp;</font><font style='background-color: #C6D24A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C6D24A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C6D24A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_LIME_YELLOW = -0x1.22ef8cp126F;
    static { NAMED.put("true lime yellow", -0x1.22ef8cp126F); LIST.add(-0x1.22ef8cp126F); }

    /**
     * This color constant "bright lime yellow" has RGBA8888 code {@code DDEB61FF}, L 0.87058824, A 0.46666667, B 0.5686275,
     * alpha 1.0, red 0.8670918, green 0.92085075, blue 0.3791537, hue 0.31656247, saturation 0.7162806, and chroma 0.15199278.
     * It can be represented as a packed float with the constant {@code -0x1.22efbcp126F}.
     * <pre>
     * <font style='background-color: #DDEB61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DDEB61; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DDEB61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DDEB61'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DDEB61'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DDEB61'>&nbsp;@&nbsp;</font><font style='background-color: #DDEB61; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DDEB61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DDEB61; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_LIME_YELLOW = -0x1.22efbcp126F;
    static { NAMED.put("bright lime yellow", -0x1.22efbcp126F); LIST.add(-0x1.22efbcp126F); }

    /**
     * This color constant "deep yellow lime" has RGBA8888 code {@code A0C72CFF}, L 0.70980394, A 0.4509804, B 0.57254905,
     * alpha 1.0, red 0.63082886, green 0.7825409, blue 0.17953525, hue 0.33965456, saturation 0.8812629, and chroma 0.17443058.
     * It can be represented as a packed float with the constant {@code -0x1.24e76ap126F}.
     * <pre>
     * <font style='background-color: #A0C72C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A0C72C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A0C72C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A0C72C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A0C72C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A0C72C'>&nbsp;@&nbsp;</font><font style='background-color: #A0C72C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A0C72C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A0C72C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_LIME = -0x1.24e76ap126F;
    static { NAMED.put("deep yellow lime", -0x1.24e76ap126F); LIST.add(-0x1.24e76ap126F); }

    /**
     * This color constant "true yellow lime" has RGBA8888 code {@code B2DB41FF}, L 0.78431374, A 0.4509804, B 0.57254905,
     * alpha 1.0, red 0.7021501, green 0.86075276, blue 0.2601916, hue 0.33965456, saturation 0.8323039, and chroma 0.17443058.
     * It can be represented as a packed float with the constant {@code -0x1.24e79p126F}.
     * <pre>
     * <font style='background-color: #B2DB41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2DB41; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2DB41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B2DB41'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B2DB41'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B2DB41'>&nbsp;@&nbsp;</font><font style='background-color: #B2DB41; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2DB41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2DB41; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_YELLOW_LIME = -0x1.24e79p126F;
    static { NAMED.put("true yellow lime", -0x1.24e79p126F); LIST.add(-0x1.24e79p126F); }

    /**
     * This color constant "bright yellow lime" has RGBA8888 code {@code C6F055FF}, L 0.8627451, A 0.4509804, B 0.57254905,
     * alpha 1.0, red 0.7774517, green 0.9428206, blue 0.3372395, hue 0.33965456, saturation 0.7749036, and chroma 0.17443058.
     * It can be represented as a packed float with the constant {@code -0x1.24e7b8p126F}.
     * <pre>
     * <font style='background-color: #C6F055;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C6F055; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C6F055;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C6F055'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C6F055'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C6F055'>&nbsp;@&nbsp;</font><font style='background-color: #C6F055; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C6F055;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C6F055; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_YELLOW_LIME = -0x1.24e7b8p126F;
    static { NAMED.put("bright yellow lime", -0x1.24e7b8p126F); LIST.add(-0x1.24e7b8p126F); }

    /**
     * This color constant "deep pure lime" has RGBA8888 code {@code 90C43EFF}, L 0.6862745, A 0.44705883, B 0.5647059,
     * alpha 1.0, red 0.56312823, green 0.7679913, blue 0.24423112, hue 0.35390377, saturation 0.7926272, and chroma 0.16655473.
     * It can be represented as a packed float with the constant {@code -0x1.20e55ep126F}.
     * <pre>
     * <font style='background-color: #90C43E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #90C43E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #90C43E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #90C43E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #90C43E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #90C43E'>&nbsp;@&nbsp;</font><font style='background-color: #90C43E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #90C43E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #90C43E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_LIME = -0x1.20e55ep126F;
    static { NAMED.put("deep pure lime", -0x1.20e55ep126F); LIST.add(-0x1.20e55ep126F); }

    /**
     * This color constant "true pure lime" has RGBA8888 code {@code A0D64EFF}, L 0.7529412, A 0.44705883, B 0.5647059,
     * alpha 1.0, red 0.6260152, green 0.8382553, blue 0.30853683, hue 0.35390377, saturation 0.75091, and chroma 0.16655473.
     * It can be represented as a packed float with the constant {@code -0x1.20e58p126F}.
     * <pre>
     * <font style='background-color: #A0D64E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A0D64E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A0D64E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A0D64E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A0D64E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A0D64E'>&nbsp;@&nbsp;</font><font style='background-color: #A0D64E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A0D64E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A0D64E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_LIME = -0x1.20e58p126F;
    static { NAMED.put("true pure lime", -0x1.20e58p126F); LIST.add(-0x1.20e58p126F); }

    /**
     * This color constant "bright pure lime" has RGBA8888 code {@code B5ED63FF}, L 0.84313726, A 0.44705883, B 0.5647059,
     * alpha 1.0, red 0.7114246, green 0.93297756, blue 0.39233953, hue 0.35390377, saturation 0.70167, and chroma 0.16655473.
     * It can be represented as a packed float with the constant {@code -0x1.20e5aep126F}.
     * <pre>
     * <font style='background-color: #B5ED63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B5ED63; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B5ED63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B5ED63'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B5ED63'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B5ED63'>&nbsp;@&nbsp;</font><font style='background-color: #B5ED63; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B5ED63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B5ED63; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_LIME = -0x1.20e5aep126F;
    static { NAMED.put("bright pure lime", -0x1.20e5aep126F); LIST.add(-0x1.20e5aep126F); }

    /**
     * This color constant "deep green lime" has RGBA8888 code {@code 7AC926FF}, L 0.68235296, A 0.43137255, B 0.57254905,
     * alpha 1.0, red 0.4802496, green 0.7879616, blue 0.14927338, hue 0.36616713, saturation 0.89456487, and chroma 0.1989505.
     * It can be represented as a packed float with the constant {@code -0x1.24dd5cp126F}.
     * <pre>
     * <font style='background-color: #7AC926;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7AC926; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7AC926;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7AC926'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7AC926'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7AC926'>&nbsp;@&nbsp;</font><font style='background-color: #7AC926; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7AC926;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7AC926; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREEN_LIME = -0x1.24dd5cp126F;
    static { NAMED.put("deep green lime", -0x1.24dd5cp126F); LIST.add(-0x1.24dd5cp126F); }

    /**
     * This color constant "true green lime" has RGBA8888 code {@code 8DDF3DFF}, L 0.7647059, A 0.43137255, B 0.57254905,
     * alpha 1.0, red 0.5564755, green 0.8760781, blue 0.24342008, hue 0.36616713, saturation 0.83590484, and chroma 0.1989505.
     * It can be represented as a packed float with the constant {@code -0x1.24dd86p126F}.
     * <pre>
     * <font style='background-color: #8DDF3D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8DDF3D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8DDF3D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8DDF3D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8DDF3D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8DDF3D'>&nbsp;@&nbsp;</font><font style='background-color: #8DDF3D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8DDF3D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8DDF3D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_GREEN_LIME = -0x1.24dd86p126F;
    static { NAMED.put("true green lime", -0x1.24dd86p126F); LIST.add(-0x1.24dd86p126F); }

    /**
     * This color constant "bright green lime" has RGBA8888 code {@code 9DF14EFF}, L 0.83137256, A 0.43137255, B 0.57254905,
     * alpha 1.0, red 0.6183871, green 0.94710183, blue 0.31040698, hue 0.36616713, saturation 0.7967218, and chroma 0.1989505.
     * It can be represented as a packed float with the constant {@code -0x1.24dda8p126F}.
     * <pre>
     * <font style='background-color: #9DF14E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9DF14E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9DF14E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9DF14E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9DF14E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9DF14E'>&nbsp;@&nbsp;</font><font style='background-color: #9DF14E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9DF14E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9DF14E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_GREEN_LIME = -0x1.24dda8p126F;
    static { NAMED.put("bright green lime", -0x1.24dda8p126F); LIST.add(-0x1.24dda8p126F); }

    /**
     * This color constant "deep lime green" has RGBA8888 code {@code 58C540FF}, L 0.6509804, A 0.42352942, B 0.56078434,
     * alpha 1.0, red 0.3469408, green 0.7710995, blue 0.25110608, hue 0.38860857, saturation 0.7762339, and chroma 0.1946081.
     * It can be represented as a packed float with the constant {@code -0x1.1ed94cp126F}.
     * <pre>
     * <font style='background-color: #58C540;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #58C540; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #58C540;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #58C540'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #58C540'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #58C540'>&nbsp;@&nbsp;</font><font style='background-color: #58C540; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #58C540;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #58C540; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_LIME_GREEN = -0x1.1ed94cp126F;
    static { NAMED.put("deep lime green", -0x1.1ed94cp126F); LIST.add(-0x1.1ed94cp126F); }

    /**
     * This color constant "true lime green" has RGBA8888 code {@code 68D750FF}, L 0.7176471, A 0.42352942, B 0.56078434,
     * alpha 1.0, red 0.4100553, green 0.8430912, blue 0.3143971, hue 0.38860857, saturation 0.7305731, and chroma 0.1946081.
     * It can be represented as a packed float with the constant {@code -0x1.1ed96ep126F}.
     * <pre>
     * <font style='background-color: #68D750;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #68D750; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #68D750;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #68D750'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #68D750'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #68D750'>&nbsp;@&nbsp;</font><font style='background-color: #68D750; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #68D750;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #68D750; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_LIME_GREEN = -0x1.1ed96ep126F;
    static { NAMED.put("true lime green", -0x1.1ed96ep126F); LIST.add(-0x1.1ed96ep126F); }

    /**
     * This color constant "bright lime green" has RGBA8888 code {@code 7BED63FF}, L 0.8, A 0.42352942, B 0.56078434,
     * alpha 1.0, red 0.48727936, green 0.93156826, blue 0.3904304, hue 0.38860857, saturation 0.6805338, and chroma 0.1946081.
     * It can be represented as a packed float with the constant {@code -0x1.1ed998p126F}.
     * <pre>
     * <font style='background-color: #7BED63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7BED63; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7BED63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7BED63'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7BED63'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7BED63'>&nbsp;@&nbsp;</font><font style='background-color: #7BED63; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7BED63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7BED63; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_LIME_GREEN = -0x1.1ed998p126F;
    static { NAMED.put("bright lime green", -0x1.1ed998p126F); LIST.add(-0x1.1ed998p126F); }

    /**
     * This color constant "deep pure green" has RGBA8888 code {@code 25C22EFF}, L 0.61960787, A 0.40784314, B 0.5647059,
     * alpha 1.0, red 0.14465171, green 0.75976074, blue 0.18154798, hue 0.39869657, saturation 0.8937718, and chroma 0.22432896.
     * It can be represented as a packed float with the constant {@code -0x1.20d13cp126F}.
     * <pre>
     * <font style='background-color: #25C22E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #25C22E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #25C22E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #25C22E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #25C22E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #25C22E'>&nbsp;@&nbsp;</font><font style='background-color: #25C22E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #25C22E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #25C22E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_GREEN = -0x1.20d13cp126F;
    static { NAMED.put("deep pure green", -0x1.20d13cp126F); LIST.add(-0x1.20d13cp126F); }

    /**
     * This color constant "true pure green" has RGBA8888 code {@code 3CD641FF}, L 0.69411767, A 0.40784314, B 0.5647059,
     * alpha 1.0, red 0.2394016, green 0.84167737, blue 0.2584365, hue 0.39869657, saturation 0.8290058, and chroma 0.22432896.
     * It can be represented as a packed float with the constant {@code -0x1.20d162p126F}.
     * <pre>
     * <font style='background-color: #3CD641;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3CD641; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3CD641;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3CD641'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3CD641'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3CD641'>&nbsp;@&nbsp;</font><font style='background-color: #3CD641; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3CD641;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3CD641; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_GREEN = -0x1.20d162p126F;
    static { NAMED.put("true pure green", -0x1.20d162p126F); LIST.add(-0x1.20d162p126F); }

    /**
     * This color constant "bright pure green" has RGBA8888 code {@code 55EF57FF}, L 0.78431374, A 0.40784314, B 0.5647059,
     * alpha 1.0, red 0.3361909, green 0.9401424, blue 0.34466583, hue 0.39869657, saturation 0.7626853, and chroma 0.22432896.
     * It can be represented as a packed float with the constant {@code -0x1.20d19p126F}.
     * <pre>
     * <font style='background-color: #55EF57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #55EF57; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #55EF57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #55EF57'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #55EF57'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #55EF57'>&nbsp;@&nbsp;</font><font style='background-color: #55EF57; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #55EF57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #55EF57; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_GREEN = -0x1.20d19p126F;
    static { NAMED.put("bright pure green", -0x1.20d19p126F); LIST.add(-0x1.20d19p126F); }

    /**
     * This color constant "deep cyan green" has RGBA8888 code {@code 40C486FF}, L 0.654902, A 0.43137255, B 0.52156866,
     * alpha 1.0, red 0.25654107, green 0.7703344, blue 0.5306712, hue 0.44599992, saturation 0.78381544, and chroma 0.143312.
     * It can be represented as a packed float with the constant {@code -0x1.0add4ep126F}.
     * <pre>
     * <font style='background-color: #40C486;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #40C486; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #40C486;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #40C486'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #40C486'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #40C486'>&nbsp;@&nbsp;</font><font style='background-color: #40C486; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #40C486;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #40C486; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_CYAN_GREEN = -0x1.0add4ep126F;
    static { NAMED.put("deep cyan green", -0x1.0add4ep126F); LIST.add(-0x1.0add4ep126F); }

    /**
     * This color constant "true cyan green" has RGBA8888 code {@code 54D898FF}, L 0.7254902, A 0.43137255, B 0.52156866,
     * alpha 1.0, red 0.33001846, green 0.84579986, blue 0.59735376, hue 0.44599992, saturation 0.7358268, and chroma 0.143312.
     * It can be represented as a packed float with the constant {@code -0x1.0add72p126F}.
     * <pre>
     * <font style='background-color: #54D898;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #54D898; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #54D898;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #54D898'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #54D898'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #54D898'>&nbsp;@&nbsp;</font><font style='background-color: #54D898; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #54D898;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #54D898; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_CYAN_GREEN = -0x1.0add72p126F;
    static { NAMED.put("true cyan green", -0x1.0add72p126F); LIST.add(-0x1.0add72p126F); }

    /**
     * This color constant "bright cyan green" has RGBA8888 code {@code 67ECABFF}, L 0.8, A 0.43137255, B 0.52156866,
     * alpha 1.0, red 0.4039669, green 0.92511386, blue 0.66797894, hue 0.44599992, saturation 0.69337523, and chroma 0.143312.
     * It can be represented as a packed float with the constant {@code -0x1.0add98p126F}.
     * <pre>
     * <font style='background-color: #67ECAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #67ECAB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #67ECAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #67ECAB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #67ECAB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #67ECAB'>&nbsp;@&nbsp;</font><font style='background-color: #67ECAB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #67ECAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #67ECAB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_CYAN_GREEN = -0x1.0add98p126F;
    static { NAMED.put("bright cyan green", -0x1.0add98p126F); LIST.add(-0x1.0add98p126F); }

    /**
     * This color constant "deep green cyan" has RGBA8888 code {@code 1EC3A3FF}, L 0.6509804, A 0.43137255, B 0.5019608,
     * alpha 1.0, red 0.12747245, green 0.7660921, blue 0.64227575, hue 0.49064872, saturation 0.8962835, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.00dd4cp126F}.
     * <pre>
     * <font style='background-color: #1EC3A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1EC3A3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1EC3A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1EC3A3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1EC3A3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1EC3A3'>&nbsp;@&nbsp;</font><font style='background-color: #1EC3A3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1EC3A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1EC3A3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREEN_CYAN = -0x1.00dd4cp126F;
    static { NAMED.put("deep green cyan", -0x1.00dd4cp126F); LIST.add(-0x1.00dd4cp126F); }

    /**
     * This color constant "true green cyan" has RGBA8888 code {@code 38D6B5FF}, L 0.72156864, A 0.43137255, B 0.5019608,
     * alpha 1.0, red 0.22699988, green 0.8415756, blue 0.71256316, hue 0.49064872, saturation 0.83070177, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.00dd7p126F}.
     * <pre>
     * <font style='background-color: #38D6B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #38D6B5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #38D6B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #38D6B5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #38D6B5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #38D6B5'>&nbsp;@&nbsp;</font><font style='background-color: #38D6B5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #38D6B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #38D6B5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_GREEN_CYAN = -0x1.00dd7p126F;
    static { NAMED.put("true green cyan", -0x1.00dd7p126F); LIST.add(-0x1.00dd7p126F); }

    /**
     * This color constant "bright green cyan" has RGBA8888 code {@code 53EECCFF}, L 0.80784315, A 0.43137255, B 0.5019608,
     * alpha 1.0, red 0.32446462, green 0.9334027, blue 0.79849243, hue 0.49064872, saturation 0.77406305, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.00dd9cp126F}.
     * <pre>
     * <font style='background-color: #53EECC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #53EECC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #53EECC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #53EECC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #53EECC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #53EECC'>&nbsp;@&nbsp;</font><font style='background-color: #53EECC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #53EECC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #53EECC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_GREEN_CYAN = -0x1.00dd9cp126F;
    static { NAMED.put("bright green cyan", -0x1.00dd9cp126F); LIST.add(-0x1.00dd9cp126F); }

    /**
     * This color constant "deep pure cyan" has RGBA8888 code {@code 3BC5C6FF}, L 0.6784314, A 0.44313726, B 0.48235294,
     * alpha 1.0, red 0.23651236, green 0.774207, blue 0.77936196, hue 0.5442928, saturation 0.83201253, and chroma 0.11861114.
     * It can be represented as a packed float with the constant {@code -0x1.f6e35ap125F}.
     * <pre>
     * <font style='background-color: #3BC5C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3BC5C6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3BC5C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3BC5C6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3BC5C6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3BC5C6'>&nbsp;@&nbsp;</font><font style='background-color: #3BC5C6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3BC5C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3BC5C6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_CYAN = -0x1.f6e35ap125F;
    static { NAMED.put("deep pure cyan", -0x1.f6e35ap125F); LIST.add(-0x1.f6e35ap125F); }

    /**
     * This color constant "true pure cyan" has RGBA8888 code {@code 52DBDCFF}, L 0.7607843, A 0.44313726, B 0.48235294,
     * alpha 1.0, red 0.32522064, green 0.8611511, blue 0.86562574, hue 0.5442928, saturation 0.7663274, and chroma 0.11861114.
     * It can be represented as a packed float with the constant {@code -0x1.f6e384p125F}.
     * <pre>
     * <font style='background-color: #52DBDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #52DBDC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #52DBDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #52DBDC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #52DBDC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #52DBDC'>&nbsp;@&nbsp;</font><font style='background-color: #52DBDC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #52DBDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #52DBDC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_CYAN = -0x1.f6e384p125F;
    static { NAMED.put("true pure cyan", -0x1.f6e384p125F); LIST.add(-0x1.f6e384p125F); }

    /**
     * This color constant "bright pure cyan" has RGBA8888 code {@code 65EFF0FF}, L 0.8352941, A 0.44313726, B 0.48235294,
     * alpha 1.0, red 0.4003009, green 0.93952465, blue 0.943437, hue 0.5442928, saturation 0.728011, and chroma 0.11861114.
     * It can be represented as a packed float with the constant {@code -0x1.f6e3aap125F}.
     * <pre>
     * <font style='background-color: #65EFF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #65EFF0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #65EFF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #65EFF0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #65EFF0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #65EFF0'>&nbsp;@&nbsp;</font><font style='background-color: #65EFF0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #65EFF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #65EFF0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_CYAN = -0x1.f6e3aap125F;
    static { NAMED.put("bright pure cyan", -0x1.f6e3aap125F); LIST.add(-0x1.f6e3aap125F); }

    /**
     * This color constant "deep blue cyan" has RGBA8888 code {@code 25ABCBFF}, L 0.59607846, A 0.4509804, B 0.4627451,
     * alpha 1.0, red 0.14665867, green 0.6692355, blue 0.7965325, hue 0.6024164, saturation 0.88235295, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.ece73p125F}.
     * <pre>
     * <font style='background-color: #25ABCB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #25ABCB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #25ABCB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #25ABCB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #25ABCB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #25ABCB'>&nbsp;@&nbsp;</font><font style='background-color: #25ABCB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #25ABCB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #25ABCB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE_CYAN = -0x1.ece73p125F;
    static { NAMED.put("deep blue cyan", -0x1.ece73p125F); LIST.add(-0x1.ece73p125F); }

    /**
     * This color constant "true blue cyan" has RGBA8888 code {@code 3BBEDFFF}, L 0.6666667, A 0.4509804, B 0.4627451,
     * alpha 1.0, red 0.23387314, green 0.74368453, blue 0.874774, hue 0.6024164, saturation 0.8108108, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.ece754p125F}.
     * <pre>
     * <font style='background-color: #3BBEDF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3BBEDF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3BBEDF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3BBEDF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3BBEDF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3BBEDF'>&nbsp;@&nbsp;</font><font style='background-color: #3BBEDF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3BBEDF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3BBEDF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BLUE_CYAN = -0x1.ece754p125F;
    static { NAMED.put("true blue cyan", -0x1.ece754p125F); LIST.add(-0x1.ece754p125F); }

    /**
     * This color constant "bright blue cyan" has RGBA8888 code {@code 4FD1F3FF}, L 0.7411765, A 0.4509804, B 0.4627451,
     * alpha 1.0, red 0.31336385, green 0.82196045, blue 0.95680463, hue 0.6024164, saturation 0.7692308, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.ece77ap125F}.
     * <pre>
     * <font style='background-color: #4FD1F3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4FD1F3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4FD1F3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4FD1F3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4FD1F3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4FD1F3'>&nbsp;@&nbsp;</font><font style='background-color: #4FD1F3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4FD1F3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4FD1F3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE_CYAN = -0x1.ece77ap125F;
    static { NAMED.put("bright blue cyan", -0x1.ece77ap125F); LIST.add(-0x1.ece77ap125F); }

    /**
     * This color constant "deep cyan blue" has RGBA8888 code {@code 3282BCFF}, L 0.47843137, A 0.47058824, B 0.44705883,
     * alpha 1.0, red 0.19816065, green 0.50875217, blue 0.7382578, hue 0.6713868, saturation 0.7571704, and chroma 0.120651916.
     * It can be represented as a packed float with the constant {@code -0x1.e4f0f4p125F}.
     * <pre>
     * <font style='background-color: #3282BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3282BC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3282BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3282BC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3282BC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3282BC'>&nbsp;@&nbsp;</font><font style='background-color: #3282BC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3282BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3282BC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_CYAN_BLUE = -0x1.e4f0f4p125F;
    static { NAMED.put("deep cyan blue", -0x1.e4f0f4p125F); LIST.add(-0x1.e4f0f4p125F); }

    /**
     * This color constant "true cyan blue" has RGBA8888 code {@code 4394D1FF}, L 0.54901963, A 0.47058824, B 0.44705883,
     * alpha 1.0, red 0.26701382, green 0.58238715, blue 0.8210567, hue 0.6713868, saturation 0.7030868, and chroma 0.120651916.
     * It can be represented as a packed float with the constant {@code -0x1.e4f118p125F}.
     * <pre>
     * <font style='background-color: #4394D1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4394D1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4394D1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4394D1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4394D1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4394D1'>&nbsp;@&nbsp;</font><font style='background-color: #4394D1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4394D1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4394D1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_CYAN_BLUE = -0x1.e4f118p125F;
    static { NAMED.put("true cyan blue", -0x1.e4f118p125F); LIST.add(-0x1.e4f118p125F); }

    /**
     * This color constant "bright cyan blue" has RGBA8888 code {@code 58AAEAFF}, L 0.6313726, A 0.47058824, B 0.44705883,
     * alpha 1.0, red 0.3446757, green 0.6679076, blue 0.9164423, hue 0.6713868, saturation 0.7202353, and chroma 0.120651916.
     * It can be represented as a packed float with the constant {@code -0x1.e4f142p125F}.
     * <pre>
     * <font style='background-color: #58AAEA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #58AAEA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #58AAEA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #58AAEA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #58AAEA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #58AAEA'>&nbsp;@&nbsp;</font><font style='background-color: #58AAEA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #58AAEA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #58AAEA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_CYAN_BLUE = -0x1.e4f142p125F;
    static { NAMED.put("bright cyan blue", -0x1.e4f142p125F); LIST.add(-0x1.e4f142p125F); }

    /**
     * This color constant "deep pure blue" has RGBA8888 code {@code 0B2DCFFF}, L 0.28627452, A 0.4862745, B 0.3764706,
     * alpha 1.0, red 0.046178896, green 0.17693973, blue 0.8122143, hue 0.7346457, saturation 0.79858524, and chroma 0.24760818.
     * It can be represented as a packed float with the constant {@code -0x1.c0f892p125F}.
     * <pre>
     * <font style='background-color: #0B2DCF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0B2DCF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0B2DCF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0B2DCF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0B2DCF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0B2DCF'>&nbsp;@&nbsp;</font><font style='background-color: #0B2DCF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0B2DCF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0B2DCF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_BLUE = -0x1.c0f892p125F;
    static { NAMED.put("deep pure blue", -0x1.c0f892p125F); LIST.add(-0x1.c0f892p125F); }

    /**
     * This color constant "true pure blue" has RGBA8888 code {@code 153FE5FF}, L 0.34117648, A 0.4862745, B 0.3764706,
     * alpha 1.0, red 0.08357716, green 0.24934943, blue 0.8948854, hue 0.7346457, saturation 0.8196006, and chroma 0.24760818.
     * It can be represented as a packed float with the constant {@code -0x1.c0f8aep125F}.
     * <pre>
     * <font style='background-color: #153FE5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #153FE5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #153FE5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #153FE5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #153FE5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #153FE5'>&nbsp;@&nbsp;</font><font style='background-color: #153FE5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #153FE5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #153FE5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_BLUE = -0x1.c0f8aep125F;
    static { NAMED.put("true pure blue", -0x1.c0f8aep125F); LIST.add(-0x1.c0f8aep125F); }

    /**
     * This color constant "bright pure blue" has RGBA8888 code {@code 1E4EF6FF}, L 0.3882353, A 0.4862745, B 0.3764706,
     * alpha 1.0, red 0.118767954, green 0.30628276, blue 0.9637966, hue 0.7346457, saturation 0.90274847, and chroma 0.24760818.
     * It can be represented as a packed float with the constant {@code -0x1.c0f8c6p125F}.
     * <pre>
     * <font style='background-color: #1E4EF6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1E4EF6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1E4EF6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1E4EF6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1E4EF6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1E4EF6'>&nbsp;@&nbsp;</font><font style='background-color: #1E4EF6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1E4EF6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1E4EF6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_BLUE = -0x1.c0f8c6p125F;
    static { NAMED.put("bright pure blue", -0x1.c0f8c6p125F); LIST.add(-0x1.c0f8c6p125F); }

    /**
     * This color constant "deep violet blue" has RGBA8888 code {@code 3932BCFF}, L 0.29803923, A 0.50980395, B 0.39607844,
     * alpha 1.0, red 0.22513378, green 0.19766846, blue 0.73460215, hue 0.7682832, saturation 0.707365, and chroma 0.20795049.
     * It can be represented as a packed float with the constant {@code -0x1.cb0498p125F}.
     * <pre>
     * <font style='background-color: #3932BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3932BC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3932BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3932BC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3932BC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3932BC'>&nbsp;@&nbsp;</font><font style='background-color: #3932BC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3932BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3932BC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET_BLUE = -0x1.cb0498p125F;
    static { NAMED.put("deep violet blue", -0x1.cb0498p125F); LIST.add(-0x1.cb0498p125F); }

    /**
     * This color constant "true violet blue" has RGBA8888 code {@code 4341CEFF}, L 0.34901962, A 0.50980395, B 0.39607844,
     * alpha 1.0, red 0.26538175, green 0.25672978, blue 0.8072639, hue 0.7682832, saturation 0.6887501, and chroma 0.20795049.
     * It can be represented as a packed float with the constant {@code -0x1.cb04b2p125F}.
     * <pre>
     * <font style='background-color: #4341CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4341CE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4341CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4341CE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4341CE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4341CE'>&nbsp;@&nbsp;</font><font style='background-color: #4341CE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4341CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4341CE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_VIOLET_BLUE = -0x1.cb04b2p125F;
    static { NAMED.put("true violet blue", -0x1.cb04b2p125F); LIST.add(-0x1.cb04b2p125F); }

    /**
     * This color constant "bright violet blue" has RGBA8888 code {@code 5052E3FF}, L 0.40784314, A 0.50980395, B 0.39607844,
     * alpha 1.0, red 0.31381696, green 0.32155576, blue 0.8889268, hue 0.7682832, saturation 0.7931062, and chroma 0.20795049.
     * It can be represented as a packed float with the constant {@code -0x1.cb04dp125F}.
     * <pre>
     * <font style='background-color: #5052E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5052E3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5052E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5052E3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5052E3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5052E3'>&nbsp;@&nbsp;</font><font style='background-color: #5052E3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5052E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5052E3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_VIOLET_BLUE = -0x1.cb04dp125F;
    static { NAMED.put("bright violet blue", -0x1.cb04dp125F); LIST.add(-0x1.cb04dp125F); }

    /**
     * This color constant "deep blue violet" has RGBA8888 code {@code 5621C9FF}, L 0.3137255, A 0.5372549, B 0.3882353,
     * alpha 1.0, red 0.3398296, green 0.12907572, blue 0.7859689, hue 0.8045939, saturation 0.8494896, and chroma 0.23470029.
     * It can be represented as a packed float with the constant {@code -0x1.c712ap125F}.
     * <pre>
     * <font style='background-color: #5621C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5621C9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5621C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5621C9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5621C9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5621C9'>&nbsp;@&nbsp;</font><font style='background-color: #5621C9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5621C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5621C9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE_VIOLET = -0x1.c712ap125F;
    static { NAMED.put("deep blue violet", -0x1.c712ap125F); LIST.add(-0x1.c712ap125F); }

    /**
     * This color constant "true blue violet" has RGBA8888 code {@code 6333DDFF}, L 0.36862746, A 0.5372549, B 0.3882353,
     * alpha 1.0, red 0.39060828, green 0.20226178, blue 0.86485577, hue 0.8045939, saturation 0.7722633, and chroma 0.23470029.
     * It can be represented as a packed float with the constant {@code -0x1.c712bcp125F}.
     * <pre>
     * <font style='background-color: #6333DD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6333DD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6333DD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6333DD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6333DD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6333DD'>&nbsp;@&nbsp;</font><font style='background-color: #6333DD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6333DD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6333DD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BLUE_VIOLET = -0x1.c712bcp125F;
    static { NAMED.put("true blue violet", -0x1.c712bcp125F); LIST.add(-0x1.c712bcp125F); }

    /**
     * This color constant "bright blue violet" has RGBA8888 code {@code 7448F6FF}, L 0.4392157, A 0.5372549, B 0.3882353,
     * alpha 1.0, red 0.4568022, green 0.2838437, blue 0.9634171, hue 0.8045939, saturation 0.90097386, and chroma 0.23470029.
     * It can be represented as a packed float with the constant {@code -0x1.c712ep125F}.
     * <pre>
     * <font style='background-color: #7448F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7448F6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7448F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7448F6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7448F6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7448F6'>&nbsp;@&nbsp;</font><font style='background-color: #7448F6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7448F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7448F6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE_VIOLET = -0x1.c712ep125F;
    static { NAMED.put("bright blue violet", -0x1.c712ep125F); LIST.add(-0x1.c712ep125F); }

    /**
     * This color constant "deep pure violet" has RGBA8888 code {@code 7533BEFF}, L 0.36078432, A 0.5529412, B 0.4117647,
     * alpha 1.0, red 0.4561281, green 0.19876304, blue 0.7395964, hue 0.84019774, saturation 0.7450517, and chroma 0.2049944.
     * It can be represented as a packed float with the constant {@code -0x1.d31ab8p125F}.
     * <pre>
     * <font style='background-color: #7533BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7533BE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7533BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7533BE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7533BE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7533BE'>&nbsp;@&nbsp;</font><font style='background-color: #7533BE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7533BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7533BE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_VIOLET = -0x1.d31ab8p125F;
    static { NAMED.put("deep pure violet", -0x1.d31ab8p125F); LIST.add(-0x1.d31ab8p125F); }

    /**
     * This color constant "true pure violet" has RGBA8888 code {@code 8544D2FF}, L 0.42352942, A 0.5529412, B 0.4117647,
     * alpha 1.0, red 0.5227348, green 0.266257, blue 0.82260597, hue 0.84019774, saturation 0.6773197, and chroma 0.2049944.
     * It can be represented as a packed float with the constant {@code -0x1.d31ad8p125F}.
     * <pre>
     * <font style='background-color: #8544D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8544D2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8544D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8544D2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8544D2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8544D2'>&nbsp;@&nbsp;</font><font style='background-color: #8544D2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8544D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8544D2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_VIOLET = -0x1.d31ad8p125F;
    static { NAMED.put("true pure violet", -0x1.d31ad8p125F); LIST.add(-0x1.d31ad8p125F); }

    /**
     * This color constant "bright pure violet" has RGBA8888 code {@code 9654E7FF}, L 0.4862745, A 0.5529412, B 0.4117647,
     * alpha 1.0, red 0.58902967, green 0.33092964, blue 0.90387356, hue 0.84019774, saturation 0.766965, and chroma 0.2049944.
     * It can be represented as a packed float with the constant {@code -0x1.d31af8p125F}.
     * <pre>
     * <font style='background-color: #9654E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9654E7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9654E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9654E7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9654E7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9654E7'>&nbsp;@&nbsp;</font><font style='background-color: #9654E7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9654E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9654E7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_VIOLET = -0x1.d31af8p125F;
    static { NAMED.put("bright pure violet", -0x1.d31af8p125F); LIST.add(-0x1.d31af8p125F); }

    /**
     * This color constant "deep purple violet" has RGBA8888 code {@code 8028C9FF}, L 0.37254903, A 0.5647059, B 0.40392157,
     * alpha 1.0, red 0.5007472, green 0.15874572, blue 0.78899914, hue 0.8480867, saturation 0.8284756, and chroma 0.23076649.
     * It can be represented as a packed float with the constant {@code -0x1.cf20bep125F}.
     * <pre>
     * <font style='background-color: #8028C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8028C9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8028C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8028C9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8028C9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8028C9'>&nbsp;@&nbsp;</font><font style='background-color: #8028C9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8028C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8028C9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE_VIOLET = -0x1.cf20bep125F;
    static { NAMED.put("deep purple violet", -0x1.cf20bep125F); LIST.add(-0x1.cf20bep125F); }

    /**
     * This color constant "true purple violet" has RGBA8888 code {@code 8F39DCFF}, L 0.42745098, A 0.5647059, B 0.40392157,
     * alpha 1.0, red 0.5607895, green 0.2239672, blue 0.86280227, hue 0.8480867, saturation 0.76391906, and chroma 0.23076649.
     * It can be represented as a packed float with the constant {@code -0x1.cf20dap125F}.
     * <pre>
     * <font style='background-color: #8F39DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F39DC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F39DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F39DC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F39DC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F39DC'>&nbsp;@&nbsp;</font><font style='background-color: #8F39DC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F39DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F39DC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURPLE_VIOLET = -0x1.cf20dap125F;
    static { NAMED.put("true purple violet", -0x1.cf20dap125F); LIST.add(-0x1.cf20dap125F); }

    /**
     * This color constant "bright purple violet" has RGBA8888 code {@code A24BF3FF}, L 0.49411765, A 0.5647059, B 0.40392157,
     * alpha 1.0, red 0.63318735, green 0.29627976, blue 0.95055884, hue 0.8480867, saturation 0.85248935, and chroma 0.23076649.
     * It can be represented as a packed float with the constant {@code -0x1.cf20fcp125F}.
     * <pre>
     * <font style='background-color: #A24BF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A24BF3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A24BF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A24BF3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A24BF3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A24BF3'>&nbsp;@&nbsp;</font><font style='background-color: #A24BF3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A24BF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A24BF3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURPLE_VIOLET = -0x1.cf20fcp125F;
    static { NAMED.put("bright purple violet", -0x1.cf20fcp125F); LIST.add(-0x1.cf20fcp125F); }

    /**
     * This color constant "deep violet purple" has RGBA8888 code {@code 8A36C5FF}, L 0.4, A 0.5647059, B 0.41568628,
     * alpha 1.0, red 0.541606, green 0.21342474, blue 0.7701723, hue 0.8583083, saturation 0.74023324, and chroma 0.21173172.
     * It can be represented as a packed float with the constant {@code -0x1.d520ccp125F}.
     * <pre>
     * <font style='background-color: #8A36C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A36C5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A36C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8A36C5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8A36C5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8A36C5'>&nbsp;@&nbsp;</font><font style='background-color: #8A36C5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A36C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A36C5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET_PURPLE = -0x1.d520ccp125F;
    static { NAMED.put("deep violet purple", -0x1.d520ccp125F); LIST.add(-0x1.d520ccp125F); }

    /**
     * This color constant "true violet purple" has RGBA8888 code {@code 9C47D9FF}, L 0.4627451, A 0.5647059, B 0.41568628,
     * alpha 1.0, red 0.61133015, green 0.2799007, blue 0.85116273, hue 0.8583083, saturation 0.6927824, and chroma 0.21173172.
     * It can be represented as a packed float with the constant {@code -0x1.d520ecp125F}.
     * <pre>
     * <font style='background-color: #9C47D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C47D9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C47D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9C47D9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9C47D9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9C47D9'>&nbsp;@&nbsp;</font><font style='background-color: #9C47D9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C47D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C47D9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_VIOLET_PURPLE = -0x1.d520ecp125F;
    static { NAMED.put("true violet purple", -0x1.d520ecp125F); LIST.add(-0x1.d520ecp125F); }

    /**
     * This color constant "bright violet purple" has RGBA8888 code {@code AB56EBFF}, L 0.5176471, A 0.5647059, B 0.41568628,
     * alpha 1.0, red 0.6718588, green 0.3359475, blue 0.92082334, hue 0.8583083, saturation 0.7946621, and chroma 0.21173172.
     * It can be represented as a packed float with the constant {@code -0x1.d52108p125F}.
     * <pre>
     * <font style='background-color: #AB56EB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB56EB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB56EB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB56EB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB56EB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB56EB'>&nbsp;@&nbsp;</font><font style='background-color: #AB56EB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB56EB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB56EB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_VIOLET_PURPLE = -0x1.d52108p125F;
    static { NAMED.put("bright violet purple", -0x1.d52108p125F); LIST.add(-0x1.d52108p125F); }

    /**
     * This color constant "deep pure purple" has RGBA8888 code {@code 9928CBFF}, L 0.40784314, A 0.5803922, B 0.4117647,
     * alpha 1.0, red 0.597807, green 0.15797035, blue 0.79510826, hue 0.8712994, saturation 0.8448281, and chroma 0.2378005.
     * It can be represented as a packed float with the constant {@code -0x1.d328dp125F}.
     * <pre>
     * <font style='background-color: #9928CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9928CB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9928CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9928CB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9928CB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9928CB'>&nbsp;@&nbsp;</font><font style='background-color: #9928CB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9928CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9928CB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_PURPLE = -0x1.d328dp125F;
    static { NAMED.put("deep pure purple", -0x1.d328dp125F); LIST.add(-0x1.d328dp125F); }

    /**
     * This color constant "true pure purple" has RGBA8888 code {@code AB3BE0FF}, L 0.47058824, A 0.5803922, B 0.4117647,
     * alpha 1.0, red 0.67067266, green 0.23212227, blue 0.87660176, hue 0.8712994, saturation 0.76996994, and chroma 0.2378005.
     * It can be represented as a packed float with the constant {@code -0x1.d328fp125F}.
     * <pre>
     * <font style='background-color: #AB3BE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB3BE0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB3BE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB3BE0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB3BE0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB3BE0'>&nbsp;@&nbsp;</font><font style='background-color: #AB3BE0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB3BE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB3BE0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_PURPLE = -0x1.d328fp125F;
    static { NAMED.put("true pure purple", -0x1.d328fp125F); LIST.add(-0x1.d328fp125F); }

    /**
     * This color constant "bright pure purple" has RGBA8888 code {@code BF4DF6FF}, L 0.5372549, A 0.5803922, B 0.4117647,
     * alpha 1.0, red 0.74718314, green 0.3036299, blue 0.9615842, hue 0.8712994, saturation 0.8567271, and chroma 0.2378005.
     * It can be represented as a packed float with the constant {@code -0x1.d32912p125F}.
     * <pre>
     * <font style='background-color: #BF4DF6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF4DF6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF4DF6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BF4DF6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BF4DF6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BF4DF6'>&nbsp;@&nbsp;</font><font style='background-color: #BF4DF6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF4DF6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF4DF6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_PURPLE = -0x1.d32912p125F;
    static { NAMED.put("bright pure purple", -0x1.d32912p125F); LIST.add(-0x1.d32912p125F); }

    /**
     * This color constant "deep magenta purple" has RGBA8888 code {@code A032BCFF}, L 0.41960785, A 0.5803922, B 0.42745098,
     * alpha 1.0, red 0.62723154, green 0.19834173, blue 0.73573273, hue 0.8872186, saturation 0.77911645, and chroma 0.21572971.
     * It can be represented as a packed float with the constant {@code -0x1.db28d6p125F}.
     * <pre>
     * <font style='background-color: #A032BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A032BC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A032BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A032BC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A032BC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A032BC'>&nbsp;@&nbsp;</font><font style='background-color: #A032BC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A032BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A032BC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_MAGENTA_PURPLE = -0x1.db28d6p125F;
    static { NAMED.put("deep magenta purple", -0x1.db28d6p125F); LIST.add(-0x1.db28d6p125F); }

    /**
     * This color constant "true magenta purple" has RGBA8888 code {@code B343D0FF}, L 0.48235294, A 0.5803922, B 0.42745098,
     * alpha 1.0, red 0.70150816, green 0.2652136, blue 0.8137135, hue 0.8872186, saturation 0.70919573, and chroma 0.21572971.
     * It can be represented as a packed float with the constant {@code -0x1.db28f6p125F}.
     * <pre>
     * <font style='background-color: #B343D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B343D0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B343D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B343D0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B343D0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B343D0'>&nbsp;@&nbsp;</font><font style='background-color: #B343D0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B343D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B343D0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_MAGENTA_PURPLE = -0x1.db28f6p125F;
    static { NAMED.put("true magenta purple", -0x1.db28f6p125F); LIST.add(-0x1.db28f6p125F); }

    /**
     * This color constant "bright magenta purple" has RGBA8888 code {@code C957E7FF}, L 0.5568628, A 0.5803922, B 0.42745098,
     * alpha 1.0, red 0.78851074, green 0.34074777, blue 0.90478283, hue 0.8872186, saturation 0.73756355, and chroma 0.21572971.
     * It can be represented as a packed float with the constant {@code -0x1.db291cp125F}.
     * <pre>
     * <font style='background-color: #C957E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C957E7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C957E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C957E7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C957E7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C957E7'>&nbsp;@&nbsp;</font><font style='background-color: #C957E7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C957E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C957E7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA_PURPLE = -0x1.db291cp125F;
    static { NAMED.put("bright magenta purple", -0x1.db291cp125F); LIST.add(-0x1.db291cp125F); }

    /**
     * This color constant "deep purple magenta" has RGBA8888 code {@code BB2ECFFF}, L 0.46666667, A 0.59607846, B 0.42352942,
     * alpha 1.0, red 0.7305793, green 0.18109989, blue 0.8096487, hue 0.8965699, saturation 0.8373503, and chroma 0.24463232.
     * It can be represented as a packed float with the constant {@code -0x1.d930eep125F}.
     * <pre>
     * <font style='background-color: #BB2ECF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB2ECF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB2ECF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BB2ECF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BB2ECF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BB2ECF'>&nbsp;@&nbsp;</font><font style='background-color: #BB2ECF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB2ECF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB2ECF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE_MAGENTA = -0x1.d930eep125F;
    static { NAMED.put("deep purple magenta", -0x1.d930eep125F); LIST.add(-0x1.d930eep125F); }

    /**
     * This color constant "true purple magenta" has RGBA8888 code {@code CF41E4FF}, L 0.53333336, A 0.59607846, B 0.42352942,
     * alpha 1.0, red 0.8117118, green 0.2572162, blue 0.89220834, hue 0.8965699, saturation 0.77532434, and chroma 0.24463232.
     * It can be represented as a packed float with the constant {@code -0x1.d9311p125F}.
     * <pre>
     * <font style='background-color: #CF41E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CF41E4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CF41E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CF41E4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CF41E4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CF41E4'>&nbsp;@&nbsp;</font><font style='background-color: #CF41E4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CF41E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CF41E4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURPLE_MAGENTA = -0x1.d9311p125F;
    static { NAMED.put("true purple magenta", -0x1.d9311p125F); LIST.add(-0x1.d9311p125F); }

    /**
     * This color constant "bright purple magenta" has RGBA8888 code {@code E151F6FF}, L 0.5921569, A 0.59607846, B 0.42352942,
     * alpha 1.0, red 0.88236386, green 0.31929213, blue 0.9640516, hue 0.8965699, saturation 0.8602914, and chroma 0.24463232.
     * It can be represented as a packed float with the constant {@code -0x1.d9312ep125F}.
     * <pre>
     * <font style='background-color: #E151F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E151F6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E151F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E151F6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E151F6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E151F6'>&nbsp;@&nbsp;</font><font style='background-color: #E151F6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E151F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E151F6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURPLE_MAGENTA = -0x1.d9312ep125F;
    static { NAMED.put("bright purple magenta", -0x1.d9312ep125F); LIST.add(-0x1.d9312ep125F); }

    /**
     * This color constant "deep pure magenta" has RGBA8888 code {@code BE39BEFF}, L 0.4745098, A 0.5921569, B 0.4392157,
     * alpha 1.0, red 0.74514276, green 0.22562253, blue 0.74353516, hue 0.91109616, saturation 0.7649174, and chroma 0.21993263.
     * It can be represented as a packed float with the constant {@code -0x1.e12ef2p125F}.
     * <pre>
     * <font style='background-color: #BE39BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE39BE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE39BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BE39BE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BE39BE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BE39BE'>&nbsp;@&nbsp;</font><font style='background-color: #BE39BE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE39BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE39BE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_MAGENTA = -0x1.e12ef2p125F;
    static { NAMED.put("deep pure magenta", -0x1.e12ef2p125F); LIST.add(-0x1.e12ef2p125F); }

    /**
     * This color constant "true pure magenta" has RGBA8888 code {@code D24AD1FF}, L 0.5372549, A 0.5921569, B 0.4392157,
     * alpha 1.0, red 0.8219687, green 0.29107708, blue 0.8180476, hue 0.91109616, saturation 0.70754856, and chroma 0.21993263.
     * It can be represented as a packed float with the constant {@code -0x1.e12f12p125F}.
     * <pre>
     * <font style='background-color: #D24AD1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D24AD1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D24AD1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D24AD1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D24AD1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D24AD1'>&nbsp;@&nbsp;</font><font style='background-color: #D24AD1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D24AD1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D24AD1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_MAGENTA = -0x1.e12f12p125F;
    static { NAMED.put("true pure magenta", -0x1.e12f12p125F); LIST.add(-0x1.e12f12p125F); }

    /**
     * This color constant "bright pure magenta" has RGBA8888 code {@code E95DE7FF}, L 0.6117647, A 0.5921569, B 0.4392157,
     * alpha 1.0, red 0.911888, green 0.36569348, blue 0.9054395, hue 0.91109616, saturation 0.7351154, and chroma 0.21993263.
     * It can be represented as a packed float with the constant {@code -0x1.e12f38p125F}.
     * <pre>
     * <font style='background-color: #E95DE7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E95DE7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E95DE7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E95DE7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E95DE7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E95DE7'>&nbsp;@&nbsp;</font><font style='background-color: #E95DE7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E95DE7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E95DE7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_MAGENTA = -0x1.e12f38p125F;
    static { NAMED.put("bright pure magenta", -0x1.e12f38p125F); LIST.add(-0x1.e12f38p125F); }

    /**
     * This color constant "deep red magenta" has RGBA8888 code {@code C62487FF}, L 0.43529412, A 0.6039216, B 0.4745098,
     * alpha 1.0, red 0.7748013, green 0.1425003, blue 0.5275602, hue 0.96519774, saturation 0.8643323, and chroma 0.21316819.
     * It can be represented as a packed float with the constant {@code -0x1.f334dep125F}.
     * <pre>
     * <font style='background-color: #C62487;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C62487; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C62487;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C62487'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C62487'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C62487'>&nbsp;@&nbsp;</font><font style='background-color: #C62487; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C62487;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C62487; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED_MAGENTA = -0x1.f334dep125F;
    static { NAMED.put("deep red magenta", -0x1.f334dep125F); LIST.add(-0x1.f334dep125F); }

    /**
     * This color constant "true red magenta" has RGBA8888 code {@code DA3697FF}, L 0.49411765, A 0.6039216, B 0.4745098,
     * alpha 1.0, red 0.85250705, green 0.21217917, blue 0.5906444, hue 0.96519774, saturation 0.8016995, and chroma 0.21316819.
     * It can be represented as a packed float with the constant {@code -0x1.f334fcp125F}.
     * <pre>
     * <font style='background-color: #DA3697;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA3697; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA3697;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DA3697'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DA3697'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DA3697'>&nbsp;@&nbsp;</font><font style='background-color: #DA3697; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA3697;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA3697; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_RED_MAGENTA = -0x1.f334fcp125F;
    static { NAMED.put("true red magenta", -0x1.f334fcp125F); LIST.add(-0x1.f334fcp125F); }

    /**
     * This color constant "bright red magenta" has RGBA8888 code {@code F149AAFF}, L 0.5647059, A 0.6039216, B 0.4745098,
     * alpha 1.0, red 0.94399476, green 0.28691265, blue 0.6659135, hue 0.96519774, saturation 0.8016995, and chroma 0.21316819.
     * It can be represented as a packed float with the constant {@code -0x1.f3352p125F}.
     * <pre>
     * <font style='background-color: #F149AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F149AA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F149AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F149AA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F149AA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F149AA'>&nbsp;@&nbsp;</font><font style='background-color: #F149AA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F149AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F149AA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED_MAGENTA = -0x1.f3352p125F;
    static { NAMED.put("bright red magenta", -0x1.f3352p125F); LIST.add(-0x1.f3352p125F); }

    /**
     * This color constant "deep magenta red" has RGBA8888 code {@code C4335BFF}, L 0.43137255, A 0.5882353, B 0.50980395,
     * alpha 1.0, red 0.7683199, green 0.20235641, blue 0.35709518, hue 0.020642795, saturation 0.7731609, and chroma 0.17686298.
     * It can be represented as a packed float with the constant {@code -0x1.052cdcp126F}.
     * <pre>
     * <font style='background-color: #C4335B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4335B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4335B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C4335B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C4335B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C4335B'>&nbsp;@&nbsp;</font><font style='background-color: #C4335B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4335B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4335B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_MAGENTA_RED = -0x1.052cdcp126F;
    static { NAMED.put("deep magenta red", -0x1.052cdcp126F); LIST.add(-0x1.052cdcp126F); }

    /**
     * This color constant "true magenta red" has RGBA8888 code {@code D74268FF}, L 0.4862745, A 0.5882353, B 0.50980395,
     * alpha 1.0, red 0.84072375, green 0.25959584, blue 0.40895727, hue 0.020642795, saturation 0.71368694, and chroma 0.17686298.
     * It can be represented as a packed float with the constant {@code -0x1.052cf8p126F}.
     * <pre>
     * <font style='background-color: #D74268;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D74268; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D74268;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D74268'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D74268'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D74268'>&nbsp;@&nbsp;</font><font style='background-color: #D74268; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D74268;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D74268; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_MAGENTA_RED = -0x1.052cf8p126F;
    static { NAMED.put("true magenta red", -0x1.052cf8p126F); LIST.add(-0x1.052cf8p126F); }

    /**
     * This color constant "bright magenta red" has RGBA8888 code {@code EA5176FF}, L 0.54509807, A 0.5882353, B 0.50980395,
     * alpha 1.0, red 0.91698766, green 0.3185529, blue 0.46476316, hue 0.020642795, saturation 0.7482202, and chroma 0.17686298.
     * It can be represented as a packed float with the constant {@code -0x1.052d16p126F}.
     * <pre>
     * <font style='background-color: #EA5176;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EA5176; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EA5176;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EA5176'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EA5176'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EA5176'>&nbsp;@&nbsp;</font><font style='background-color: #EA5176; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EA5176;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EA5176; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA_RED = -0x1.052d16p126F;
    static { NAMED.put("bright magenta red", -0x1.052d16p126F); LIST.add(-0x1.052d16p126F); }

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
     * This color constant "bold brown red" has RGBA8888 code {@code DE4D30FF}, L 0.5019608, A 0.57254905, B 0.54901963,
     * alpha 1.0, red 0.8695441, green 0.30225837, blue 0.18803619, hue 0.09550095, saturation 0.7426364, and chroma 0.17443058.
     * It can be represented as a packed float with the constant {@code -0x1.1925p126F}.
     * <pre>
     * <font style='background-color: #DE4D30;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DE4D30; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DE4D30;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DE4D30'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DE4D30'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DE4D30'>&nbsp;@&nbsp;</font><font style='background-color: #DE4D30; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DE4D30;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DE4D30; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_BROWN_RED = -0x1.1925p126F;
    static { NAMED.put("bold brown red", -0x1.1925p126F); LIST.add(-0x1.1925p126F); }

    /**
     * This color constant "bold red brown" has RGBA8888 code {@code DA6340FF}, L 0.5372549, A 0.5568628, B 0.54509807,
     * alpha 1.0, red 0.8548645, green 0.38977724, blue 0.25215667, hue 0.107388355, saturation 0.6511652, and chroma 0.14458403.
     * It can be represented as a packed float with the constant {@code -0x1.171d12p126F}.
     * <pre>
     * <font style='background-color: #DA6340;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA6340; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA6340;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DA6340'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DA6340'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DA6340'>&nbsp;@&nbsp;</font><font style='background-color: #DA6340; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA6340;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA6340; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_RED_BROWN = -0x1.171d12p126F;
    static { NAMED.put("bold red brown", -0x1.171d12p126F); LIST.add(-0x1.171d12p126F); }

    /**
     * This color constant "bold pure brown" has RGBA8888 code {@code D17654FF}, L 0.5647059, A 0.5411765, B 0.5372549,
     * alpha 1.0, red 0.8177897, green 0.46303216, blue 0.33181566, hue 0.11742691, saturation 0.53093106, and chroma 0.11062346.
     * It can be represented as a packed float with the constant {@code -0x1.13152p126F}.
     * <pre>
     * <font style='background-color: #D17654;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D17654; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D17654;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D17654'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D17654'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D17654'>&nbsp;@&nbsp;</font><font style='background-color: #D17654; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D17654;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D17654; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_BROWN = -0x1.13152p126F;
    static { NAMED.put("bold pure brown", -0x1.13152p126F); LIST.add(-0x1.13152p126F); }

    /**
     * This color constant "bold orange brown" has RGBA8888 code {@code DA7A4BFF}, L 0.58431375, A 0.5411765, B 0.54509807,
     * alpha 1.0, red 0.8546452, green 0.477885, blue 0.29672205, hue 0.13191544, saturation 0.6142951, and chroma 0.121659465.
     * It can be represented as a packed float with the constant {@code -0x1.17152ap126F}.
     * <pre>
     * <font style='background-color: #DA7A4B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA7A4B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA7A4B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DA7A4B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DA7A4B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DA7A4B'>&nbsp;@&nbsp;</font><font style='background-color: #DA7A4B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA7A4B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA7A4B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_ORANGE_BROWN = -0x1.17152ap126F;
    static { NAMED.put("bold orange brown", -0x1.17152ap126F); LIST.add(-0x1.17152ap126F); }

    /**
     * This color constant "bold brown orange" has RGBA8888 code {@code E37F4AFF}, L 0.60784316, A 0.5411765, B 0.54901963,
     * alpha 1.0, red 0.88943654, green 0.4985273, blue 0.28945404, hue 0.13823235, saturation 0.6678191, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.191536p126F}.
     * <pre>
     * <font style='background-color: #E37F4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E37F4A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E37F4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E37F4A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E37F4A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E37F4A'>&nbsp;@&nbsp;</font><font style='background-color: #E37F4A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E37F4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E37F4A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_BROWN_ORANGE = -0x1.191536p126F;
    static { NAMED.put("bold brown orange", -0x1.191536p126F); LIST.add(-0x1.191536p126F); }

    /**
     * This color constant "bold pure orange" has RGBA8888 code {@code F97A1BFF}, L 0.62352943, A 0.54901963, B 0.5686275,
     * alpha 1.0, red 0.97334194, green 0.47868094, blue 0.10668708, hue 0.15045097, saturation 0.88814414, and chroma 0.16801417.
     * It can be represented as a packed float with the constant {@code -0x1.23193ep126F}.
     * <pre>
     * <font style='background-color: #F97A1B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F97A1B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F97A1B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F97A1B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F97A1B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F97A1B'>&nbsp;@&nbsp;</font><font style='background-color: #F97A1B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F97A1B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F97A1B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_ORANGE = -0x1.23193ep126F;
    static { NAMED.put("bold pure orange", -0x1.23193ep126F); LIST.add(-0x1.23193ep126F); }

    /**
     * This color constant "bold saffron orange" has RGBA8888 code {@code F98915FF}, L 0.654902, A 0.5372549, B 0.57254905,
     * alpha 1.0, red 0.9734198, green 0.53882426, blue 0.08366903, hue 0.17289294, saturation 0.9136558, and chroma 0.16247371.
     * It can be represented as a packed float with the constant {@code -0x1.25134ep126F}.
     * <pre>
     * <font style='background-color: #F98915;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F98915; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F98915;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F98915'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F98915'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F98915'>&nbsp;@&nbsp;</font><font style='background-color: #F98915; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F98915;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F98915; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_SAFFRON_ORANGE = -0x1.25134ep126F;
    static { NAMED.put("bold saffron orange", -0x1.25134ep126F); LIST.add(-0x1.25134ep126F); }

    /**
     * This color constant "bold orange saffron" has RGBA8888 code {@code FF9600FF}, L 0.69411767, A 0.5294118, B 0.5764706,
     * alpha 1.0, red 0.9957731, green 0.5951701, blue 0.06468667, hue 0.18944053, saturation 0.9365504, and chroma 0.1632233.
     * It can be represented as a packed float with the constant {@code -0x1.270f62p126F}.
     * <pre>
     * <font style='background-color: #FF9600;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF9600; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF9600;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF9600'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF9600'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF9600'>&nbsp;@&nbsp;</font><font style='background-color: #FF9600; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF9600;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF9600; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_ORANGE_SAFFRON = -0x1.270f62p126F;
    static { NAMED.put("bold orange saffron", -0x1.270f62p126F); LIST.add(-0x1.270f62p126F); }

    /**
     * This color constant "bold pure saffron" has RGBA8888 code {@code F6A319FF}, L 0.70980394, A 0.5176471, B 0.5764706,
     * alpha 1.0, red 0.96484447, green 0.64018905, blue 0.10090439, hue 0.21101043, saturation 0.9162457, and chroma 0.15634763.
     * It can be represented as a packed float with the constant {@code -0x1.27096ap126F}.
     * <pre>
     * <font style='background-color: #F6A319;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6A319; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6A319;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F6A319'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F6A319'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F6A319'>&nbsp;@&nbsp;</font><font style='background-color: #F6A319; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6A319;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6A319; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_SAFFRON = -0x1.27096ap126F;
    static { NAMED.put("bold pure saffron", -0x1.27096ap126F); LIST.add(-0x1.27096ap126F); }

    /**
     * This color constant "bold yellow saffron" has RGBA8888 code {@code FDBB00FF}, L 0.7764706, A 0.5019608, B 0.58431375,
     * alpha 1.0, red 0.9863163, green 0.7391388, blue 0.0, hue 0.24277067, saturation 0.95750934, and chroma 0.16801417.
     * It can be represented as a packed float with the constant {@code -0x1.2b018cp126F}.
     * <pre>
     * <font style='background-color: #FDBB00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDBB00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDBB00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FDBB00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FDBB00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FDBB00'>&nbsp;@&nbsp;</font><font style='background-color: #FDBB00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDBB00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDBB00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_YELLOW_SAFFRON = -0x1.2b018cp126F;
    static { NAMED.put("bold yellow saffron", -0x1.2b018cp126F); LIST.add(-0x1.2b018cp126F); }

    /**
     * This color constant "bold saffron yellow" has RGBA8888 code {@code FFDB2EFF}, L 0.85882354, A 0.4862745, B 0.58431375,
     * alpha 1.0, red 0.99900407, green 0.8585315, blue 0.18344419, hue 0.2715699, saturation 0.90626955, and chroma 0.17017984.
     * It can be represented as a packed float with the constant {@code -0x1.2af9b6p126F}.
     * <pre>
     * <font style='background-color: #FFDB2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFDB2E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFDB2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFDB2E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFDB2E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFDB2E'>&nbsp;@&nbsp;</font><font style='background-color: #FFDB2E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFDB2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFDB2E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_SAFFRON_YELLOW = -0x1.2af9b6p126F;
    static { NAMED.put("bold saffron yellow", -0x1.2af9b6p126F); LIST.add(-0x1.2af9b6p126F); }

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
     * This color constant "bold lime yellow" has RGBA8888 code {@code E3F82FFF}, L 0.9098039, A 0.45490196, B 0.5882353,
     * alpha 1.0, red 0.890597, green 0.9766937, blue 0.19200431, hue 0.3209999, saturation 0.89456487, and chroma 0.1974106.
     * It can be represented as a packed float with the constant {@code -0x1.2ce9dp126F}.
     * <pre>
     * <font style='background-color: #E3F82F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3F82F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3F82F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E3F82F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E3F82F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E3F82F'>&nbsp;@&nbsp;</font><font style='background-color: #E3F82F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3F82F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3F82F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_LIME_YELLOW = -0x1.2ce9dp126F;
    static { NAMED.put("bold lime yellow", -0x1.2ce9dp126F); LIST.add(-0x1.2ce9dp126F); }

    /**
     * This color constant "bold yellow lime" has RGBA8888 code {@code C8FF00FF}, L 0.9019608, A 0.43529412, B 0.5921569,
     * alpha 1.0, red 0.77341974, green 1.0, blue 0.09739314, hue 0.34358352, saturation 0.9614803, and chroma 0.22432896.
     * It can be represented as a packed float with the constant {@code -0x1.2edfccp126F}.
     * <pre>
     * <font style='background-color: #C8FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8FF00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C8FF00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C8FF00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C8FF00'>&nbsp;@&nbsp;</font><font style='background-color: #C8FF00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8FF00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_YELLOW_LIME = -0x1.2edfccp126F;
    static { NAMED.put("bold yellow lime", -0x1.2edfccp126F); LIST.add(-0x1.2edfccp126F); }

    /**
     * This color constant "bold pure lime" has RGBA8888 code {@code B2FC36FF}, L 0.8745098, A 0.43137255, B 0.58431375,
     * alpha 1.0, red 0.6967724, green 0.9881656, blue 0.21434157, hue 0.35470623, saturation 0.882631, and chroma 0.21657681.
     * It can be represented as a packed float with the constant {@code -0x1.2addbep126F}.
     * <pre>
     * <font style='background-color: #B2FC36;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2FC36; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2FC36;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B2FC36'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B2FC36'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B2FC36'>&nbsp;@&nbsp;</font><font style='background-color: #B2FC36; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2FC36;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2FC36; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_LIME = -0x1.2addbep126F;
    static { NAMED.put("bold pure lime", -0x1.2addbep126F); LIST.add(-0x1.2addbep126F); }

    /**
     * This color constant "bold green lime" has RGBA8888 code {@code 84FE00FF}, L 0.84705883, A 0.40784314, B 0.5921569,
     * alpha 1.0, red 0.5203765, green 0.99838173, blue 0.0, hue 0.37161422, saturation 0.9776923, and chroma 0.25964078.
     * It can be represented as a packed float with the constant {@code -0x1.2ed1bp126F}.
     * <pre>
     * <font style='background-color: #84FE00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #84FE00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #84FE00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #84FE00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #84FE00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #84FE00'>&nbsp;@&nbsp;</font><font style='background-color: #84FE00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #84FE00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #84FE00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_GREEN_LIME = -0x1.2ed1bp126F;
    static { NAMED.put("bold green lime", -0x1.2ed1bp126F); LIST.add(-0x1.2ed1bp126F); }

    /**
     * This color constant "bold lime green" has RGBA8888 code {@code 63FD27FF}, L 0.827451, A 0.4, B 0.58431375,
     * alpha 1.0, red 0.39197084, green 0.99379694, blue 0.15926223, hue 0.38514507, saturation 0.912374, and chroma 0.26057938.
     * It can be represented as a packed float with the constant {@code -0x1.2acda6p126F}.
     * <pre>
     * <font style='background-color: #63FD27;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #63FD27; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #63FD27;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #63FD27'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #63FD27'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #63FD27'>&nbsp;@&nbsp;</font><font style='background-color: #63FD27; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #63FD27;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #63FD27; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_LIME_GREEN = -0x1.2acda6p126F;
    static { NAMED.put("bold lime green", -0x1.2acda6p126F); LIST.add(-0x1.2acda6p126F); }

    /**
     * This color constant "bold pure green" has RGBA8888 code {@code 00FF1FFF}, L 0.80784315, A 0.38039216, B 0.58431375,
     * alpha 1.0, red 0.0, green 1.0, blue 0.13362467, hue 0.3992949, saturation 0.979004, and chroma 0.29153293.
     * It can be represented as a packed float with the constant {@code -0x1.2ac39cp126F}.
     * <pre>
     * <font style='background-color: #00FF1F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF1F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF1F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FF1F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FF1F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FF1F'>&nbsp;@&nbsp;</font><font style='background-color: #00FF1F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF1F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF1F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_GREEN = -0x1.2ac39cp126F;
    static { NAMED.put("bold pure green", -0x1.2ac39cp126F); LIST.add(-0x1.2ac39cp126F); }

    /**
     * This color constant "bold cyan green" has RGBA8888 code {@code 31F7A0FF}, L 0.8117647, A 0.4117647, B 0.5294118,
     * alpha 1.0, red 0.1996496, green 0.97006166, blue 0.6318893, hue 0.4444914, saturation 0.86701477, and chroma 0.18528971.
     * It can be represented as a packed float with the constant {@code -0x1.0ed39ep126F}.
     * <pre>
     * <font style='background-color: #31F7A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #31F7A0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #31F7A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #31F7A0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #31F7A0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #31F7A0'>&nbsp;@&nbsp;</font><font style='background-color: #31F7A0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #31F7A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #31F7A0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_CYAN_GREEN = -0x1.0ed39ep126F;
    static { NAMED.put("bold cyan green", -0x1.0ed39ep126F); LIST.add(-0x1.0ed39ep126F); }

    /**
     * This color constant "bold green cyan" has RGBA8888 code {@code 00FAD0FF}, L 0.83137256, A 0.41568628, B 0.5019608,
     * alpha 1.0, red 0.0, green 0.98474044, blue 0.8254478, hue 0.49242693, saturation 0.9556271, and chroma 0.16801417.
     * It can be represented as a packed float with the constant {@code -0x1.00d5a8p126F}.
     * <pre>
     * <font style='background-color: #00FAD0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FAD0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FAD0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FAD0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FAD0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FAD0'>&nbsp;@&nbsp;</font><font style='background-color: #00FAD0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FAD0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FAD0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_GREEN_CYAN = -0x1.00d5a8p126F;
    static { NAMED.put("bold green cyan", -0x1.00d5a8p126F); LIST.add(-0x1.00d5a8p126F); }

    /**
     * This color constant "bold pure cyan" has RGBA8888 code {@code 24F8F9FF}, L 0.84313726, A 0.42745098, B 0.47843137,
     * alpha 1.0, red 0.14961061, green 0.973952, blue 0.9792822, hue 0.54312253, saturation 0.9112947, and chroma 0.15078327.
     * It can be represented as a packed float with the constant {@code -0x1.f4dbaep125F}.
     * <pre>
     * <font style='background-color: #24F8F9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #24F8F9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #24F8F9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #24F8F9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #24F8F9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #24F8F9'>&nbsp;@&nbsp;</font><font style='background-color: #24F8F9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #24F8F9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #24F8F9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_CYAN = -0x1.f4dbaep125F;
    static { NAMED.put("bold pure cyan", -0x1.f4dbaep125F); LIST.add(-0x1.f4dbaep125F); }

    /**
     * This color constant "bold blue cyan" has RGBA8888 code {@code 00D0FFFF}, L 0.7254902, A 0.44313726, B 0.4509804,
     * alpha 1.0, red 0.089522496, green 0.81364644, blue 1.0, hue 0.6127814, saturation 0.9455943, and chroma 0.14956398.
     * It can be represented as a packed float with the constant {@code -0x1.e6e372p125F}.
     * <pre>
     * <font style='background-color: #00D0FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00D0FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00D0FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00D0FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00D0FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00D0FF'>&nbsp;@&nbsp;</font><font style='background-color: #00D0FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00D0FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00D0FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_BLUE_CYAN = -0x1.e6e372p125F;
    static { NAMED.put("bold blue cyan", -0x1.e6e372p125F); LIST.add(-0x1.e6e372p125F); }

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
     * This color constant "bold violet blue" has RGBA8888 code {@code 3F19EEFF}, L 0.32156864, A 0.5137255, B 0.36078432,
     * alpha 1.0, red 0.24788053, green 0.09916656, blue 0.93260795, hue 0.7681106, saturation 0.9032777, and chroma 0.27868843.
     * It can be represented as a packed float with the constant {@code -0x1.b906a4p125F}.
     * <pre>
     * <font style='background-color: #3F19EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F19EE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F19EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3F19EE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3F19EE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3F19EE'>&nbsp;@&nbsp;</font><font style='background-color: #3F19EE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F19EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F19EE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_VIOLET_BLUE = -0x1.b906a4p125F;
    static { NAMED.put("bold violet blue", -0x1.b906a4p125F); LIST.add(-0x1.b906a4p125F); }

    /**
     * This color constant "bold blue violet" has RGBA8888 code {@code 6900FFFF}, L 0.36862746, A 0.54509807, B 0.36078432,
     * alpha 1.0, red 0.40456513, green 0.035924982, blue 0.99561656, hue 0.80256844, saturation 0.96103895, and chroma 0.29153293.
     * It can be represented as a packed float with the constant {@code -0x1.b916bcp125F}.
     * <pre>
     * <font style='background-color: #6900FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6900FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6900FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6900FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6900FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6900FF'>&nbsp;@&nbsp;</font><font style='background-color: #6900FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6900FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6900FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_BLUE_VIOLET = -0x1.b916bcp125F;
    static { NAMED.put("bold blue violet", -0x1.b916bcp125F); LIST.add(-0x1.b916bcp125F); }

    /**
     * This color constant "bold pure violet" has RGBA8888 code {@code 8921EEFF}, L 0.40784314, A 0.5686275, B 0.38431373,
     * alpha 1.0, red 0.53661466, green 0.13249634, blue 0.93069494, hue 0.83840954, saturation 0.8865479, and chroma 0.26796988.
     * It can be represented as a packed float with the constant {@code -0x1.c522dp125F}.
     * <pre>
     * <font style='background-color: #8921EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8921EE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8921EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8921EE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8921EE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8921EE'>&nbsp;@&nbsp;</font><font style='background-color: #8921EE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8921EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8921EE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_VIOLET = -0x1.c522dp125F;
    static { NAMED.put("bold pure violet", -0x1.c522dp125F); LIST.add(-0x1.c522dp125F); }

    /**
     * This color constant "bold purple violet" has RGBA8888 code {@code 9F00FFFF}, L 0.4392157, A 0.5882353, B 0.3764706,
     * alpha 1.0, red 0.62947994, green 0.0, blue 1.0, hue 0.85159177, saturation 0.9897569, and chroma 0.3024255.
     * It can be represented as a packed float with the constant {@code -0x1.c12cep125F}.
     * <pre>
     * <font style='background-color: #9F00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9F00FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9F00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9F00FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9F00FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9F00FF'>&nbsp;@&nbsp;</font><font style='background-color: #9F00FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9F00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9F00FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURPLE_VIOLET = -0x1.c12cep125F;
    static { NAMED.put("bold purple violet", -0x1.c12cep125F); LIST.add(-0x1.c12cep125F); }

    /**
     * This color constant "bold violet purple" has RGBA8888 code {@code AB24F6FF}, L 0.4627451, A 0.5882353, B 0.39215687,
     * alpha 1.0, red 0.66908854, green 0.14337344, blue 0.9620044, hue 0.8622947, saturation 0.8979315, and chroma 0.27759123.
     * It can be represented as a packed float with the constant {@code -0x1.c92cecp125F}.
     * <pre>
     * <font style='background-color: #AB24F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB24F6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB24F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB24F6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB24F6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB24F6'>&nbsp;@&nbsp;</font><font style='background-color: #AB24F6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB24F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB24F6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_VIOLET_PURPLE = -0x1.c92cecp125F;
    static { NAMED.put("bold violet purple", -0x1.c92cecp125F); LIST.add(-0x1.c92cecp125F); }

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
     * This color constant "bold magenta purple" has RGBA8888 code {@code CB29F7FF}, L 0.5137255, A 0.6039216, B 0.40392157,
     * alpha 1.0, red 0.79298437, green 0.16244225, blue 0.96552974, hue 0.88435125, saturation 0.8919699, and chroma 0.2819544.
     * It can be represented as a packed float with the constant {@code -0x1.cf3506p125F}.
     * <pre>
     * <font style='background-color: #CB29F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CB29F7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CB29F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CB29F7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CB29F7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CB29F7'>&nbsp;@&nbsp;</font><font style='background-color: #CB29F7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CB29F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CB29F7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_MAGENTA_PURPLE = -0x1.cf3506p125F;
    static { NAMED.put("bold magenta purple", -0x1.cf3506p125F); LIST.add(-0x1.cf3506p125F); }

    /**
     * This color constant "bold purple magenta" has RGBA8888 code {@code E912FFFF}, L 0.5568628, A 0.62352943, B 0.40784314,
     * alpha 1.0, red 0.91608685, green 0.09162115, blue 0.99520576, hue 0.9008148, saturation 0.94959325, and chroma 0.30703226.
     * It can be represented as a packed float with the constant {@code -0x1.d13f1cp125F}.
     * <pre>
     * <font style='background-color: #E912FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E912FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E912FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E912FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E912FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E912FF'>&nbsp;@&nbsp;</font><font style='background-color: #E912FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E912FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E912FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURPLE_MAGENTA = -0x1.d13f1cp125F;
    static { NAMED.put("bold purple magenta", -0x1.d13f1cp125F); LIST.add(-0x1.d13f1cp125F); }

    /**
     * This color constant "bold pure magenta" has RGBA8888 code {@code F52AF6FF}, L 0.5803922, A 0.62352943, B 0.41960785,
     * alpha 1.0, red 0.9593891, green 0.166036, blue 0.9631133, hue 0.91109616, saturation 0.8984744, and chroma 0.29361907.
     * It can be represented as a packed float with the constant {@code -0x1.d73f28p125F}.
     * <pre>
     * <font style='background-color: #F52AF6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F52AF6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F52AF6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F52AF6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F52AF6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F52AF6'>&nbsp;@&nbsp;</font><font style='background-color: #F52AF6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F52AF6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F52AF6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_MAGENTA = -0x1.d73f28p125F;
    static { NAMED.put("bold pure magenta", -0x1.d73f28p125F); LIST.add(-0x1.d73f28p125F); }

    /**
     * This color constant "bold red magenta" has RGBA8888 code {@code FF00A7FF}, L 0.5372549, A 0.63529414, B 0.47058824,
     * alpha 1.0, red 1.0, green 0.0, blue 0.64964795, hue 0.9685835, saturation 0.9778942, and chroma 0.2758266.
     * It can be represented as a packed float with the constant {@code -0x1.f14512p125F}.
     * <pre>
     * <font style='background-color: #FF00A7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF00A7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF00A7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF00A7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF00A7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF00A7'>&nbsp;@&nbsp;</font><font style='background-color: #FF00A7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF00A7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF00A7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_RED_MAGENTA = -0x1.f14512p125F;
    static { NAMED.put("bold red magenta", -0x1.f14512p125F); LIST.add(-0x1.f14512p125F); }

    /**
     * This color constant "bold magenta red" has RGBA8888 code {@code F72061FF}, L 0.5058824, A 0.6156863, B 0.5176471,
     * alpha 1.0, red 0.96537066, green 0.12775865, blue 0.38095307, hue 0.026284225, saturation 0.907875, and chroma 0.23313475.
     * It can be represented as a packed float with the constant {@code -0x1.093b02p126F}.
     * <pre>
     * <font style='background-color: #F72061;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F72061; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F72061;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F72061'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F72061'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F72061'>&nbsp;@&nbsp;</font><font style='background-color: #F72061; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F72061;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F72061; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_MAGENTA_RED = -0x1.093b02p126F;
    static { NAMED.put("bold magenta red", -0x1.093b02p126F); LIST.add(-0x1.093b02p126F); }

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
