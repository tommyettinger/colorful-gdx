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
     * This color constant "transparent" has RGBA8888 code {@code 00000000}, L 0.0, A 0.49803922, B 0.49803922, alpha 0.0, hue 0.0, saturation 0.0, and chroma 0.0055242716.
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
     * This color constant "pure black" has RGBA8888 code {@code 000000FF}, L 0.0, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.0, and chroma 0.0055242716.
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
     * This color constant "almost black" has RGBA8888 code {@code 151515FF}, L 0.06666667, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.fefe22p125F}.
     * <pre>
     * <font style='background-color: #151515;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #151515; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #151515;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #151515'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #151515'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #151515'>&nbsp;@&nbsp;</font><font style='background-color: #151515; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #151515;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #151515; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ALMOST_BLACK = -0x1.fefe22p125F;
    static { NAMED.put("almost black", -0x1.fefe22p125F); LIST.add(-0x1.fefe22p125F); }

    /**
     * This color constant "lead black" has RGBA8888 code {@code 262626FF}, L 0.13725491, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.fefe46p125F}.
     * <pre>
     * <font style='background-color: #262626;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #262626; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #262626;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #262626'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #262626'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #262626'>&nbsp;@&nbsp;</font><font style='background-color: #262626; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #262626;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #262626; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_BLACK = -0x1.fefe46p125F;
    static { NAMED.put("lead black", -0x1.fefe46p125F); LIST.add(-0x1.fefe46p125F); }

    /**
     * This color constant "black lead" has RGBA8888 code {@code 373737FF}, L 0.20784314, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.fefe6ap125F}.
     * <pre>
     * <font style='background-color: #373737;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #373737; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #373737;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #373737'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #373737'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #373737'>&nbsp;@&nbsp;</font><font style='background-color: #373737; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #373737;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #373737; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_LEAD = -0x1.fefe6ap125F;
    static { NAMED.put("black lead", -0x1.fefe6ap125F); LIST.add(-0x1.fefe6ap125F); }

    /**
     * This color constant "pure lead" has RGBA8888 code {@code 494949FF}, L 0.28627452, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.0, and chroma 0.0055242716.
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
     * This color constant "gray lead" has RGBA8888 code {@code 595959FF}, L 0.3529412, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.fefeb4p125F}.
     * <pre>
     * <font style='background-color: #595959;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #595959; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #595959;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #595959'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #595959'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #595959'>&nbsp;@&nbsp;</font><font style='background-color: #595959; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #595959;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #595959; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_LEAD = -0x1.fefeb4p125F;
    static { NAMED.put("gray lead", -0x1.fefeb4p125F); LIST.add(-0x1.fefeb4p125F); }

    /**
     * This color constant "lead gray" has RGBA8888 code {@code 6A6A6AFF}, L 0.42352942, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.fefed8p125F}.
     * <pre>
     * <font style='background-color: #6A6A6A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A6A6A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A6A6A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6A6A6A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6A6A6A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6A6A6A'>&nbsp;@&nbsp;</font><font style='background-color: #6A6A6A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A6A6A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A6A6A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_GRAY = -0x1.fefed8p125F;
    static { NAMED.put("lead gray", -0x1.fefed8p125F); LIST.add(-0x1.fefed8p125F); }

    /**
     * This color constant "pure gray" has RGBA8888 code {@code 7C7C7CFF}, L 0.49803922, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.fefefep125F}.
     * <pre>
     * <font style='background-color: #7C7C7C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7C7C7C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7C7C7C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7C7C7C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7C7C7C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7C7C7C'>&nbsp;@&nbsp;</font><font style='background-color: #7C7C7C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7C7C7C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7C7C7C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_GRAY = -0x1.fefefep125F;
    static { NAMED.put("pure gray", -0x1.fefefep125F); LIST.add(-0x1.fefefep125F); }

    /**
     * This color constant "silver gray" has RGBA8888 code {@code 8F8F8FFF}, L 0.57254905, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.feff24p125F}.
     * <pre>
     * <font style='background-color: #8F8F8F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F8F8F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F8F8F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F8F8F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F8F8F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F8F8F'>&nbsp;@&nbsp;</font><font style='background-color: #8F8F8F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F8F8F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F8F8F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_GRAY = -0x1.feff24p125F;
    static { NAMED.put("silver gray", -0x1.feff24p125F); LIST.add(-0x1.feff24p125F); }

    /**
     * This color constant "gray silver" has RGBA8888 code {@code A1A1A1FF}, L 0.6431373, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.feff48p125F}.
     * <pre>
     * <font style='background-color: #A1A1A1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A1A1A1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A1A1A1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A1A1A1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A1A1A1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A1A1A1'>&nbsp;@&nbsp;</font><font style='background-color: #A1A1A1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A1A1A1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A1A1A1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_SILVER = -0x1.feff48p125F;
    static { NAMED.put("gray silver", -0x1.feff48p125F); LIST.add(-0x1.feff48p125F); }

    /**
     * This color constant "pure silver" has RGBA8888 code {@code B4B4B4FF}, L 0.7137255, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.feff6cp125F}.
     * <pre>
     * <font style='background-color: #B4B4B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4B4B4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4B4B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B4B4B4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B4B4B4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B4B4B4'>&nbsp;@&nbsp;</font><font style='background-color: #B4B4B4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4B4B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4B4B4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_SILVER = -0x1.feff6cp125F;
    static { NAMED.put("pure silver", -0x1.feff6cp125F); LIST.add(-0x1.feff6cp125F); }

    /**
     * This color constant "white silver" has RGBA8888 code {@code C8C8C8FF}, L 0.7882353, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.feff92p125F}.
     * <pre>
     * <font style='background-color: #C8C8C8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8C8C8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8C8C8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C8C8C8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C8C8C8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C8C8C8'>&nbsp;@&nbsp;</font><font style='background-color: #C8C8C8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8C8C8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8C8C8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_SILVER = -0x1.feff92p125F;
    static { NAMED.put("white silver", -0x1.feff92p125F); LIST.add(-0x1.feff92p125F); }

    /**
     * This color constant "silver white" has RGBA8888 code {@code DCDCDCFF}, L 0.85882354, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.feffb6p125F}.
     * <pre>
     * <font style='background-color: #DCDCDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DCDCDC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DCDCDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DCDCDC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DCDCDC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DCDCDC'>&nbsp;@&nbsp;</font><font style='background-color: #DCDCDC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DCDCDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DCDCDC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_WHITE = -0x1.feffb6p125F;
    static { NAMED.put("silver white", -0x1.feffb6p125F); LIST.add(-0x1.feffb6p125F); }

    /**
     * This color constant "almost white" has RGBA8888 code {@code F1F1F1FF}, L 0.93333334, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.feffdcp125F}.
     * <pre>
     * <font style='background-color: #F1F1F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F1F1F1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F1F1F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F1F1F1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F1F1F1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F1F1F1'>&nbsp;@&nbsp;</font><font style='background-color: #F1F1F1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F1F1F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F1F1F1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ALMOST_WHITE = -0x1.feffdcp125F;
    static { NAMED.put("almost white", -0x1.feffdcp125F); LIST.add(-0x1.feffdcp125F); }

    /**
     * This color constant "pure white" has RGBA8888 code {@code FFFFFFFF}, L 1.0, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.0, and chroma 0.0055242716.
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
     * This color constant "black red" has RGBA8888 code {@code 5E3028FF}, L 0.23921569, A 0.5294118, B 0.5176471, alpha 1.0, hue 0.088913955, saturation 0.4492372, and chroma 0.068331465.
     * It can be represented as a packed float with the constant {@code -0x1.090e7ap126F}.
     * <pre>
     * <font style='background-color: #5E3028;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5E3028; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5E3028;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5E3028'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5E3028'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5E3028'>&nbsp;@&nbsp;</font><font style='background-color: #5E3028; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5E3028;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5E3028; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_RED = -0x1.090e7ap126F;
    static { NAMED.put("black red", -0x1.090e7ap126F); LIST.add(-0x1.090e7ap126F); }

    /**
     * This color constant "lead red" has RGBA8888 code {@code 7A463DFF}, L 0.3372549, A 0.5294118, B 0.5176471, alpha 1.0, hue 0.088913955, saturation 0.36996004, and chroma 0.068331465.
     * It can be represented as a packed float with the constant {@code -0x1.090eacp126F}.
     * <pre>
     * <font style='background-color: #7A463D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7A463D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7A463D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7A463D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7A463D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7A463D'>&nbsp;@&nbsp;</font><font style='background-color: #7A463D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7A463D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7A463D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_RED = -0x1.090eacp126F;
    static { NAMED.put("lead red", -0x1.090eacp126F); LIST.add(-0x1.090eacp126F); }

    /**
     * This color constant "gray red" has RGBA8888 code {@code 986055FF}, L 0.4509804, A 0.5294118, B 0.5176471, alpha 1.0, hue 0.088913955, saturation 0.30432197, and chroma 0.068331465.
     * It can be represented as a packed float with the constant {@code -0x1.090ee6p126F}.
     * <pre>
     * <font style='background-color: #986055;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #986055; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #986055;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #986055'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #986055'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #986055'>&nbsp;@&nbsp;</font><font style='background-color: #986055; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #986055;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #986055; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_RED = -0x1.090ee6p126F;
    static { NAMED.put("gray red", -0x1.090ee6p126F); LIST.add(-0x1.090ee6p126F); }

    /**
     * This color constant "silver red" has RGBA8888 code {@code BA7D71FF}, L 0.5686275, A 0.5294118, B 0.5176471, alpha 1.0, hue 0.088913955, saturation 0.3369279, and chroma 0.068331465.
     * It can be represented as a packed float with the constant {@code -0x1.090f22p126F}.
     * <pre>
     * <font style='background-color: #BA7D71;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA7D71; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA7D71;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BA7D71'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BA7D71'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BA7D71'>&nbsp;@&nbsp;</font><font style='background-color: #BA7D71; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA7D71;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA7D71; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_RED = -0x1.090f22p126F;
    static { NAMED.put("silver red", -0x1.090f22p126F); LIST.add(-0x1.090f22p126F); }

    /**
     * This color constant "white red" has RGBA8888 code {@code E2A093FF}, L 0.70980394, A 0.5294118, B 0.5176471, alpha 1.0, hue 0.088913955, saturation 0.5717564, and chroma 0.068331465.
     * It can be represented as a packed float with the constant {@code -0x1.090f6ap126F}.
     * <pre>
     * <font style='background-color: #E2A093;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E2A093; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E2A093;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E2A093'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E2A093'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E2A093'>&nbsp;@&nbsp;</font><font style='background-color: #E2A093; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E2A093;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E2A093; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_RED = -0x1.090f6ap126F;
    static { NAMED.put("white red", -0x1.090f6ap126F); LIST.add(-0x1.090f6ap126F); }

    /**
     * This color constant "black brown" has RGBA8888 code {@code 5C473EFF}, L 0.29803923, A 0.50980395, B 0.50980395, alpha 1.0, hue 0.125, saturation 0.2424366, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.050498p126F}.
     * <pre>
     * <font style='background-color: #5C473E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5C473E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5C473E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5C473E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5C473E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5C473E'>&nbsp;@&nbsp;</font><font style='background-color: #5C473E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5C473E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5C473E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_BROWN = -0x1.050498p126F;
    static { NAMED.put("black brown", -0x1.050498p126F); LIST.add(-0x1.050498p126F); }

    /**
     * This color constant "lead brown" has RGBA8888 code {@code 796258FF}, L 0.41568628, A 0.50980395, B 0.50980395, alpha 1.0, hue 0.125, saturation 0.2020305, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.0504d4p126F}.
     * <pre>
     * <font style='background-color: #796258;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #796258; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #796258;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #796258'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #796258'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #796258'>&nbsp;@&nbsp;</font><font style='background-color: #796258; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #796258;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #796258; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_BROWN = -0x1.0504d4p126F;
    static { NAMED.put("lead brown", -0x1.0504d4p126F); LIST.add(-0x1.0504d4p126F); }

    /**
     * This color constant "gray brown" has RGBA8888 code {@code 937A6FFF}, L 0.5137255, A 0.50980395, B 0.50980395, alpha 1.0, hue 0.125, saturation 0.173169, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.050506p126F}.
     * <pre>
     * <font style='background-color: #937A6F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #937A6F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #937A6F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #937A6F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #937A6F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #937A6F'>&nbsp;@&nbsp;</font><font style='background-color: #937A6F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #937A6F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #937A6F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_BROWN = -0x1.050506p126F;
    static { NAMED.put("gray brown", -0x1.050506p126F); LIST.add(-0x1.050506p126F); }

    /**
     * This color constant "silver brown" has RGBA8888 code {@code AE9488FF}, L 0.6156863, A 0.50980395, B 0.50980395, alpha 1.0, hue 0.125, saturation 0.16637807, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.05053ap126F}.
     * <pre>
     * <font style='background-color: #AE9488;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE9488; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE9488;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AE9488'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AE9488'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AE9488'>&nbsp;@&nbsp;</font><font style='background-color: #AE9488; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE9488;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE9488; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_BROWN = -0x1.05053ap126F;
    static { NAMED.put("silver brown", -0x1.05053ap126F); LIST.add(-0x1.05053ap126F); }

    /**
     * This color constant "white brown" has RGBA8888 code {@code D0B4A8FF}, L 0.7411765, A 0.50980395, B 0.50980395, alpha 1.0, hue 0.125, saturation 0.2828427, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.05057ap126F}.
     * <pre>
     * <font style='background-color: #D0B4A8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0B4A8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0B4A8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D0B4A8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D0B4A8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D0B4A8'>&nbsp;@&nbsp;</font><font style='background-color: #D0B4A8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0B4A8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0B4A8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_BROWN = -0x1.05057ap126F;
    static { NAMED.put("white brown", -0x1.05057ap126F); LIST.add(-0x1.05057ap126F); }

    /**
     * This color constant "black orange" has RGBA8888 code {@code 62432EFF}, L 0.29411766, A 0.5137255, B 0.52156866, alpha 1.0, hue 0.15640444, saturation 0.4973174, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.0b0696p126F}.
     * <pre>
     * <font style='background-color: #62432E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #62432E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #62432E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #62432E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #62432E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #62432E'>&nbsp;@&nbsp;</font><font style='background-color: #62432E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #62432E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #62432E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_ORANGE = -0x1.0b0696p126F;
    static { NAMED.put("black orange", -0x1.0b0696p126F); LIST.add(-0x1.0b0696p126F); }

    /**
     * This color constant "lead orange" has RGBA8888 code {@code 7C5A43FF}, L 0.39215687, A 0.5137255, B 0.52156866, alpha 1.0, hue 0.15640444, saturation 0.412063, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.0b06c8p126F}.
     * <pre>
     * <font style='background-color: #7C5A43;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7C5A43; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7C5A43;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7C5A43'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7C5A43'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7C5A43'>&nbsp;@&nbsp;</font><font style='background-color: #7C5A43; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7C5A43;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7C5A43; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_ORANGE = -0x1.0b06c8p126F;
    static { NAMED.put("lead orange", -0x1.0b06c8p126F); LIST.add(-0x1.0b06c8p126F); }

    /**
     * This color constant "gray orange" has RGBA8888 code {@code 98745BFF}, L 0.49803922, A 0.5137255, B 0.52156866, alpha 1.0, hue 0.15640444, saturation 0.3517611, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.0b06fep126F}.
     * <pre>
     * <font style='background-color: #98745B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #98745B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #98745B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #98745B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #98745B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #98745B'>&nbsp;@&nbsp;</font><font style='background-color: #98745B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #98745B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #98745B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_ORANGE = -0x1.0b06fep126F;
    static { NAMED.put("gray orange", -0x1.0b06fep126F); LIST.add(-0x1.0b06fep126F); }

    /**
     * This color constant "silver orange" has RGBA8888 code {@code B89177FF}, L 0.6156863, A 0.5137255, B 0.52156866, alpha 1.0, hue 0.15640444, saturation 0.3068554, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.0b073ap126F}.
     * <pre>
     * <font style='background-color: #B89177;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B89177; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B89177;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B89177'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B89177'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B89177'>&nbsp;@&nbsp;</font><font style='background-color: #B89177; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B89177;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B89177; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_ORANGE = -0x1.0b073ap126F;
    static { NAMED.put("silver orange", -0x1.0b073ap126F); LIST.add(-0x1.0b073ap126F); }

    /**
     * This color constant "white orange" has RGBA8888 code {@code DEB498FF}, L 0.7490196, A 0.5137255, B 0.52156866, alpha 1.0, hue 0.15640444, saturation 0.4506939, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.0b077ep126F}.
     * <pre>
     * <font style='background-color: #DEB498;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEB498; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEB498;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DEB498'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DEB498'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DEB498'>&nbsp;@&nbsp;</font><font style='background-color: #DEB498; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEB498;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEB498; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_ORANGE = -0x1.0b077ep126F;
    static { NAMED.put("white orange", -0x1.0b077ep126F); LIST.add(-0x1.0b077ep126F); }

    /**
     * This color constant "black saffron" has RGBA8888 code {@code 635339FF}, L 0.3372549, A 0.5019608, B 0.52156866, alpha 1.0, hue 0.22370392, saturation 0.45057502, and chroma 0.04314594.
     * It can be represented as a packed float with the constant {@code -0x1.0b00acp126F}.
     * <pre>
     * <font style='background-color: #635339;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #635339; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #635339;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #635339'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #635339'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #635339'>&nbsp;@&nbsp;</font><font style='background-color: #635339; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #635339;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #635339; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_SAFFRON = -0x1.0b00acp126F;
    static { NAMED.put("black saffron", -0x1.0b00acp126F); LIST.add(-0x1.0b00acp126F); }

    /**
     * This color constant "lead saffron" has RGBA8888 code {@code 837255FF}, L 0.46666667, A 0.5019608, B 0.52156866, alpha 1.0, hue 0.22370392, saturation 0.36865228, and chroma 0.04314594.
     * It can be represented as a packed float with the constant {@code -0x1.0b00eep126F}.
     * <pre>
     * <font style='background-color: #837255;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #837255; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #837255;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #837255'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #837255'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #837255'>&nbsp;@&nbsp;</font><font style='background-color: #837255; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #837255;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #837255; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_SAFFRON = -0x1.0b00eep126F;
    static { NAMED.put("lead saffron", -0x1.0b00eep126F); LIST.add(-0x1.0b00eep126F); }

    /**
     * This color constant "gray saffron" has RGBA8888 code {@code A08D6EFF}, L 0.5764706, A 0.5019608, B 0.52156866, alpha 1.0, hue 0.22370392, saturation 0.3201454, and chroma 0.04314594.
     * It can be represented as a packed float with the constant {@code -0x1.0b0126p126F}.
     * <pre>
     * <font style='background-color: #A08D6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A08D6E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A08D6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A08D6E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A08D6E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A08D6E'>&nbsp;@&nbsp;</font><font style='background-color: #A08D6E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A08D6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A08D6E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_SAFFRON = -0x1.0b0126p126F;
    static { NAMED.put("gray saffron", -0x1.0b0126p126F); LIST.add(-0x1.0b0126p126F); }

    /**
     * This color constant "silver saffron" has RGBA8888 code {@code BCA887FF}, L 0.6784314, A 0.5019608, B 0.52156866, alpha 1.0, hue 0.22370392, saturation 0.28965536, and chroma 0.04314594.
     * It can be represented as a packed float with the constant {@code -0x1.0b015ap126F}.
     * <pre>
     * <font style='background-color: #BCA887;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BCA887; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BCA887;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BCA887'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BCA887'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BCA887'>&nbsp;@&nbsp;</font><font style='background-color: #BCA887; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BCA887;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BCA887; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_SAFFRON = -0x1.0b015ap126F;
    static { NAMED.put("silver saffron", -0x1.0b015ap126F); LIST.add(-0x1.0b015ap126F); }

    /**
     * This color constant "white saffron" has RGBA8888 code {@code DBC6A3FF}, L 0.7921569, A 0.5019608, B 0.52156866, alpha 1.0, hue 0.22370392, saturation 0.3201454, and chroma 0.04314594.
     * It can be represented as a packed float with the constant {@code -0x1.0b0194p126F}.
     * <pre>
     * <font style='background-color: #DBC6A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBC6A3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBC6A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DBC6A3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DBC6A3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DBC6A3'>&nbsp;@&nbsp;</font><font style='background-color: #DBC6A3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBC6A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBC6A3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_SAFFRON = -0x1.0b0194p126F;
    static { NAMED.put("white saffron", -0x1.0b0194p126F); LIST.add(-0x1.0b0194p126F); }

    /**
     * This color constant "black yellow" has RGBA8888 code {@code 5C5E33FF}, L 0.36078432, A 0.4862745, B 0.5294118, alpha 1.0, hue 0.30708748, saturation 0.5696002, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.0ef8b8p126F}.
     * <pre>
     * <font style='background-color: #5C5E33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5C5E33; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5C5E33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5C5E33'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5C5E33'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5C5E33'>&nbsp;@&nbsp;</font><font style='background-color: #5C5E33; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5C5E33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5C5E33; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_YELLOW = -0x1.0ef8b8p126F;
    static { NAMED.put("black yellow", -0x1.0ef8b8p126F); LIST.add(-0x1.0ef8b8p126F); }

    /**
     * This color constant "lead yellow" has RGBA8888 code {@code 797B4CFF}, L 0.47843137, A 0.4862745, B 0.5294118, alpha 1.0, hue 0.30708748, saturation 0.48822877, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.0ef8f4p126F}.
     * <pre>
     * <font style='background-color: #797B4C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #797B4C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #797B4C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #797B4C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #797B4C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #797B4C'>&nbsp;@&nbsp;</font><font style='background-color: #797B4C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #797B4C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #797B4C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_YELLOW = -0x1.0ef8f4p126F;
    static { NAMED.put("lead yellow", -0x1.0ef8f4p126F); LIST.add(-0x1.0ef8f4p126F); }

    /**
     * This color constant "gray yellow" has RGBA8888 code {@code 939564FF}, L 0.5803922, A 0.4862745, B 0.5294118, alpha 1.0, hue 0.30708748, saturation 0.42720017, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.0ef928p126F}.
     * <pre>
     * <font style='background-color: #939564;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #939564; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #939564;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #939564'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #939564'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #939564'>&nbsp;@&nbsp;</font><font style='background-color: #939564; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #939564;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #939564; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_YELLOW = -0x1.0ef928p126F;
    static { NAMED.put("gray yellow", -0x1.0ef928p126F); LIST.add(-0x1.0ef928p126F); }

    /**
     * This color constant "silver yellow" has RGBA8888 code {@code B2B580FF}, L 0.7019608, A 0.4862745, B 0.5294118, alpha 1.0, hue 0.30708748, saturation 0.3714784, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.0ef966p126F}.
     * <pre>
     * <font style='background-color: #B2B580;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2B580; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2B580;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B2B580'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B2B580'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B2B580'>&nbsp;@&nbsp;</font><font style='background-color: #B2B580; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2B580;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2B580; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_YELLOW = -0x1.0ef966p126F;
    static { NAMED.put("silver yellow", -0x1.0ef966p126F); LIST.add(-0x1.0ef966p126F); }

    /**
     * This color constant "white yellow" has RGBA8888 code {@code D9DCA4FF}, L 0.84313726, A 0.4862745, B 0.5294118, alpha 1.0, hue 0.30708748, saturation 0.32861552, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.0ef9aep126F}.
     * <pre>
     * <font style='background-color: #D9DCA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9DCA4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9DCA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D9DCA4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D9DCA4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D9DCA4'>&nbsp;@&nbsp;</font><font style='background-color: #D9DCA4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9DCA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9DCA4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_YELLOW = -0x1.0ef9aep126F;
    static { NAMED.put("white yellow", -0x1.0ef9aep126F); LIST.add(-0x1.0ef9aep126F); }

    /**
     * This color constant "black lime" has RGBA8888 code {@code 465735FF}, L 0.31764707, A 0.47843137, B 0.52156866, alpha 1.0, hue 0.36058098, saturation 0.45942646, and chroma 0.060766988.
     * It can be represented as a packed float with the constant {@code -0x1.0af4a2p126F}.
     * <pre>
     * <font style='background-color: #465735;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #465735; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #465735;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #465735'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #465735'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #465735'>&nbsp;@&nbsp;</font><font style='background-color: #465735; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #465735;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #465735; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_LIME = -0x1.0af4a2p126F;
    static { NAMED.put("black lime", -0x1.0af4a2p126F); LIST.add(-0x1.0af4a2p126F); }

    /**
     * This color constant "lead lime" has RGBA8888 code {@code 5E714CFF}, L 0.42352942, A 0.47843137, B 0.52156866, alpha 1.0, hue 0.36058098, saturation 0.3905125, and chroma 0.060766988.
     * It can be represented as a packed float with the constant {@code -0x1.0af4d8p126F}.
     * <pre>
     * <font style='background-color: #5E714C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5E714C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5E714C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5E714C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5E714C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5E714C'>&nbsp;@&nbsp;</font><font style='background-color: #5E714C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5E714C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5E714C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_LIME = -0x1.0af4d8p126F;
    static { NAMED.put("lead lime", -0x1.0af4d8p126F); LIST.add(-0x1.0af4d8p126F); }

    /**
     * This color constant "gray lime" has RGBA8888 code {@code 7A8E66FF}, L 0.5372549, A 0.47843137, B 0.52156866, alpha 1.0, hue 0.36058098, saturation 0.33235106, and chroma 0.060766988.
     * It can be represented as a packed float with the constant {@code -0x1.0af512p126F}.
     * <pre>
     * <font style='background-color: #7A8E66;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7A8E66; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7A8E66;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7A8E66'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7A8E66'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7A8E66'>&nbsp;@&nbsp;</font><font style='background-color: #7A8E66; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7A8E66;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7A8E66; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_LIME = -0x1.0af512p126F;
    static { NAMED.put("gray lime", -0x1.0af512p126F); LIST.add(-0x1.0af512p126F); }

    /**
     * This color constant "silver lime" has RGBA8888 code {@code 9AB085FF}, L 0.6666667, A 0.47843137, B 0.52156866, alpha 1.0, hue 0.36058098, saturation 0.28926852, and chroma 0.060766988.
     * It can be represented as a packed float with the constant {@code -0x1.0af554p126F}.
     * <pre>
     * <font style='background-color: #9AB085;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9AB085; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9AB085;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9AB085'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9AB085'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9AB085'>&nbsp;@&nbsp;</font><font style='background-color: #9AB085; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9AB085;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9AB085; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_LIME = -0x1.0af554p126F;
    static { NAMED.put("silver lime", -0x1.0af554p126F); LIST.add(-0x1.0af554p126F); }

    /**
     * This color constant "white lime" has RGBA8888 code {@code C2DAACFF}, L 0.81960785, A 0.47843137, B 0.52156866, alpha 1.0, hue 0.36058098, saturation 0.24794444, and chroma 0.060766988.
     * It can be represented as a packed float with the constant {@code -0x1.0af5a2p126F}.
     * <pre>
     * <font style='background-color: #C2DAAC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C2DAAC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C2DAAC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C2DAAC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C2DAAC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C2DAAC'>&nbsp;@&nbsp;</font><font style='background-color: #C2DAAC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C2DAAC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C2DAAC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_LIME = -0x1.0af5a2p126F;
    static { NAMED.put("white lime", -0x1.0af5a2p126F); LIST.add(-0x1.0af5a2p126F); }

    /**
     * This color constant "black green" has RGBA8888 code {@code 38643CFF}, L 0.34901962, A 0.4627451, B 0.52156866, alpha 1.0, hue 0.40640444, saturation 0.52764165, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.0aecb2p126F}.
     * <pre>
     * <font style='background-color: #38643C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #38643C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #38643C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #38643C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #38643C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #38643C'>&nbsp;@&nbsp;</font><font style='background-color: #38643C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #38643C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #38643C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_GREEN = -0x1.0aecb2p126F;
    static { NAMED.put("black green", -0x1.0aecb2p126F); LIST.add(-0x1.0aecb2p126F); }

    /**
     * This color constant "lead green" has RGBA8888 code {@code 538256FF}, L 0.46666667, A 0.4627451, B 0.52156866, alpha 1.0, hue 0.40640444, saturation 0.4414961, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.0aeceep126F}.
     * <pre>
     * <font style='background-color: #538256;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #538256; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #538256;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #538256'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #538256'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #538256'>&nbsp;@&nbsp;</font><font style='background-color: #538256; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #538256;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #538256; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_GREEN = -0x1.0aeceep126F;
    static { NAMED.put("lead green", -0x1.0aeceep126F); LIST.add(-0x1.0aeceep126F); }

    /**
     * This color constant "gray green" has RGBA8888 code {@code 6C9E6FFF}, L 0.57254905, A 0.4627451, B 0.52156866, alpha 1.0, hue 0.40640444, saturation 0.3863091, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.0aed24p126F}.
     * <pre>
     * <font style='background-color: #6C9E6F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C9E6F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C9E6F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6C9E6F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6C9E6F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6C9E6F'>&nbsp;@&nbsp;</font><font style='background-color: #6C9E6F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C9E6F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C9E6F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_GREEN = -0x1.0aed24p126F;
    static { NAMED.put("gray green", -0x1.0aed24p126F); LIST.add(-0x1.0aed24p126F); }

    /**
     * This color constant "silver green" has RGBA8888 code {@code 85BA88FF}, L 0.6784314, A 0.4627451, B 0.52156866, alpha 1.0, hue 0.40640444, saturation 0.34338585, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.0aed5ap126F}.
     * <pre>
     * <font style='background-color: #85BA88;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #85BA88; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #85BA88;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #85BA88'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #85BA88'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #85BA88'>&nbsp;@&nbsp;</font><font style='background-color: #85BA88; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #85BA88;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #85BA88; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_GREEN = -0x1.0aed5ap126F;
    static { NAMED.put("silver green", -0x1.0aed5ap126F); LIST.add(-0x1.0aed5ap126F); }

    /**
     * This color constant "white green" has RGBA8888 code {@code A4DCA7FF}, L 0.79607844, A 0.4627451, B 0.52156866, alpha 1.0, hue 0.40640444, saturation 0.30469447, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.0aed96p126F}.
     * <pre>
     * <font style='background-color: #A4DCA7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4DCA7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4DCA7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A4DCA7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A4DCA7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A4DCA7'>&nbsp;@&nbsp;</font><font style='background-color: #A4DCA7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4DCA7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4DCA7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_GREEN = -0x1.0aed96p126F;
    static { NAMED.put("white green", -0x1.0aed96p126F); LIST.add(-0x1.0aed96p126F); }

    /**
     * This color constant "black cyan" has RGBA8888 code {@code 3C5C5AFF}, L 0.3372549, A 0.47843137, B 0.49411765, alpha 1.0, hue 0.53142345, saturation 0.443393, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.fcf4acp125F}.
     * <pre>
     * <font style='background-color: #3C5C5A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C5C5A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C5C5A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3C5C5A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3C5C5A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3C5C5A'>&nbsp;@&nbsp;</font><font style='background-color: #3C5C5A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C5C5A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C5C5A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_CYAN = -0x1.fcf4acp125F;
    static { NAMED.put("black cyan", -0x1.fcf4acp125F); LIST.add(-0x1.fcf4acp125F); }

    /**
     * This color constant "lead cyan" has RGBA8888 code {@code 537573FF}, L 0.4392157, A 0.47843137, B 0.49411765, alpha 1.0, hue 0.53142345, saturation 0.37770516, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.fcf4ep125F}.
     * <pre>
     * <font style='background-color: #537573;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #537573; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #537573;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #537573'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #537573'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #537573'>&nbsp;@&nbsp;</font><font style='background-color: #537573; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #537573;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #537573; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_CYAN = -0x1.fcf4ep125F;
    static { NAMED.put("lead cyan", -0x1.fcf4ep125F); LIST.add(-0x1.fcf4ep125F); }

    /**
     * This color constant "gray cyan" has RGBA8888 code {@code 6D918FFF}, L 0.54901963, A 0.47843137, B 0.49411765, alpha 1.0, hue 0.53142345, saturation 0.328969, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.fcf518p125F}.
     * <pre>
     * <font style='background-color: #6D918F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D918F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D918F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6D918F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6D918F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6D918F'>&nbsp;@&nbsp;</font><font style='background-color: #6D918F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D918F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D918F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_CYAN = -0x1.fcf518p125F;
    static { NAMED.put("gray cyan", -0x1.fcf518p125F); LIST.add(-0x1.fcf518p125F); }

    /**
     * This color constant "silver cyan" has RGBA8888 code {@code 8AB1AEFF}, L 0.67058825, A 0.47843137, B 0.49411765, alpha 1.0, hue 0.53142345, saturation 0.29137254, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.fcf556p125F}.
     * <pre>
     * <font style='background-color: #8AB1AE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8AB1AE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8AB1AE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8AB1AE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8AB1AE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8AB1AE'>&nbsp;@&nbsp;</font><font style='background-color: #8AB1AE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8AB1AE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8AB1AE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_CYAN = -0x1.fcf556p125F;
    static { NAMED.put("silver cyan", -0x1.fcf556p125F); LIST.add(-0x1.fcf556p125F); }

    /**
     * This color constant "white cyan" has RGBA8888 code {@code AED8D5FF}, L 0.8117647, A 0.47843137, B 0.49411765, alpha 1.0, hue 0.53142345, saturation 0.25495097, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.fcf59ep125F}.
     * <pre>
     * <font style='background-color: #AED8D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AED8D5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AED8D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AED8D5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AED8D5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AED8D5'>&nbsp;@&nbsp;</font><font style='background-color: #AED8D5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AED8D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AED8D5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_CYAN = -0x1.fcf59ep125F;
    static { NAMED.put("white cyan", -0x1.fcf59ep125F); LIST.add(-0x1.fcf59ep125F); }

    /**
     * This color constant "black blue" has RGBA8888 code {@code 314474FF}, L 0.27450982, A 0.49411765, B 0.45490196, alpha 1.0, hue 0.735567, saturation 0.29066738, and chroma 0.09060479.
     * It can be represented as a packed float with the constant {@code -0x1.e8fc8cp125F}.
     * <pre>
     * <font style='background-color: #314474;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #314474; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #314474;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #314474'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #314474'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #314474'>&nbsp;@&nbsp;</font><font style='background-color: #314474; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #314474;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #314474; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_BLUE = -0x1.e8fc8cp125F;
    static { NAMED.put("black blue", -0x1.e8fc8cp125F); LIST.add(-0x1.e8fc8cp125F); }

    /**
     * This color constant "lead blue" has RGBA8888 code {@code 496094FF}, L 0.3882353, A 0.49411765, B 0.45490196, alpha 1.0, hue 0.735567, saturation 0.31558174, and chroma 0.09060479.
     * It can be represented as a packed float with the constant {@code -0x1.e8fcc6p125F}.
     * <pre>
     * <font style='background-color: #496094;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #496094; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #496094;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #496094'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #496094'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #496094'>&nbsp;@&nbsp;</font><font style='background-color: #496094; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #496094;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #496094; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_BLUE = -0x1.e8fcc6p125F;
    static { NAMED.put("lead blue", -0x1.e8fcc6p125F); LIST.add(-0x1.e8fcc6p125F); }

    /**
     * This color constant "gray blue" has RGBA8888 code {@code 6079B1FF}, L 0.49019608, A 0.49411765, B 0.45490196, alpha 1.0, hue 0.735567, saturation 0.40164948, and chroma 0.09060479.
     * It can be represented as a packed float with the constant {@code -0x1.e8fcfap125F}.
     * <pre>
     * <font style='background-color: #6079B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6079B1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6079B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6079B1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6079B1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6079B1'>&nbsp;@&nbsp;</font><font style='background-color: #6079B1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6079B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6079B1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_BLUE = -0x1.e8fcfap125F;
    static { NAMED.put("gray blue", -0x1.e8fcfap125F); LIST.add(-0x1.e8fcfap125F); }

    /**
     * This color constant "silver blue" has RGBA8888 code {@code 7690CBFF}, L 0.5803922, A 0.49411765, B 0.45490196, alpha 1.0, hue 0.735567, saturation 0.50206184, and chroma 0.09060479.
     * It can be represented as a packed float with the constant {@code -0x1.e8fd28p125F}.
     * <pre>
     * <font style='background-color: #7690CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7690CB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7690CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7690CB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7690CB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7690CB'>&nbsp;@&nbsp;</font><font style='background-color: #7690CB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7690CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7690CB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_BLUE = -0x1.e8fd28p125F;
    static { NAMED.put("silver blue", -0x1.e8fd28p125F); LIST.add(-0x1.e8fd28p125F); }

    /**
     * This color constant "white blue" has RGBA8888 code {@code 8DA9E7FF}, L 0.6745098, A 0.49411765, B 0.45490196, alpha 1.0, hue 0.735567, saturation 0.6694158, and chroma 0.09060479.
     * It can be represented as a packed float with the constant {@code -0x1.e8fd58p125F}.
     * <pre>
     * <font style='background-color: #8DA9E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8DA9E7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8DA9E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8DA9E7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8DA9E7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8DA9E7'>&nbsp;@&nbsp;</font><font style='background-color: #8DA9E7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8DA9E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8DA9E7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_BLUE = -0x1.e8fd58p125F;
    static { NAMED.put("white blue", -0x1.e8fd58p125F); LIST.add(-0x1.e8fd58p125F); }

    /**
     * This color constant "black violet" has RGBA8888 code {@code 4F3E68FF}, L 0.2784314, A 0.5176471, B 0.46666667, alpha 1.0, hue 0.8389139, saturation 0.31979597, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.ef088ep125F}.
     * <pre>
     * <font style='background-color: #4F3E68;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4F3E68; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4F3E68;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4F3E68'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4F3E68'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4F3E68'>&nbsp;@&nbsp;</font><font style='background-color: #4F3E68; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4F3E68;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4F3E68; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_VIOLET = -0x1.ef088ep125F;
    static { NAMED.put("black violet", -0x1.ef088ep125F); LIST.add(-0x1.ef088ep125F); }

    /**
     * This color constant "lead violet" has RGBA8888 code {@code 695785FF}, L 0.3882353, A 0.5176471, B 0.46666667, alpha 1.0, hue 0.8389139, saturation 0.25846523, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.ef08c6p125F}.
     * <pre>
     * <font style='background-color: #695785;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #695785; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #695785;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #695785'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #695785'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #695785'>&nbsp;@&nbsp;</font><font style='background-color: #695785; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #695785;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #695785; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_VIOLET = -0x1.ef08c6p125F;
    static { NAMED.put("lead violet", -0x1.ef08c6p125F); LIST.add(-0x1.ef08c6p125F); }

    /**
     * This color constant "gray violet" has RGBA8888 code {@code 806E9FFF}, L 0.48235294, A 0.5176471, B 0.46666667, alpha 1.0, hue 0.8389139, saturation 0.27344874, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.ef08f6p125F}.
     * <pre>
     * <font style='background-color: #806E9F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #806E9F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #806E9F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #806E9F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #806E9F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #806E9F'>&nbsp;@&nbsp;</font><font style='background-color: #806E9F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #806E9F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #806E9F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_VIOLET = -0x1.ef08f6p125F;
    static { NAMED.put("gray violet", -0x1.ef08f6p125F); LIST.add(-0x1.ef08f6p125F); }

    /**
     * This color constant "silver violet" has RGBA8888 code {@code 9A86BAFF}, L 0.5803922, A 0.5176471, B 0.46666667, alpha 1.0, hue 0.8389139, saturation 0.3559993, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.ef0928p125F}.
     * <pre>
     * <font style='background-color: #9A86BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A86BA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A86BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9A86BA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9A86BA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9A86BA'>&nbsp;@&nbsp;</font><font style='background-color: #9A86BA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A86BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A86BA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_VIOLET = -0x1.ef0928p125F;
    static { NAMED.put("silver violet", -0x1.ef0928p125F); LIST.add(-0x1.ef0928p125F); }

    /**
     * This color constant "white violet" has RGBA8888 code {@code BAA5DDFF}, L 0.7019608, A 0.5176471, B 0.46666667, alpha 1.0, hue 0.8389139, saturation 0.5390846, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.ef0966p125F}.
     * <pre>
     * <font style='background-color: #BAA5DD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BAA5DD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BAA5DD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BAA5DD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BAA5DD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BAA5DD'>&nbsp;@&nbsp;</font><font style='background-color: #BAA5DD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BAA5DD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BAA5DD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_VIOLET = -0x1.ef0966p125F;
    static { NAMED.put("white violet", -0x1.ef0966p125F); LIST.add(-0x1.ef0966p125F); }

    /**
     * This color constant "black purple" has RGBA8888 code {@code 553563FF}, L 0.2627451, A 0.5294118, B 0.46666667, alpha 1.0, hue 0.875, saturation 0.4269324, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.ef0e86p125F}.
     * <pre>
     * <font style='background-color: #553563;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #553563; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #553563;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #553563'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #553563'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #553563'>&nbsp;@&nbsp;</font><font style='background-color: #553563; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #553563;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #553563; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_PURPLE = -0x1.ef0e86p125F;
    static { NAMED.put("black purple", -0x1.ef0e86p125F); LIST.add(-0x1.ef0e86p125F); }

    /**
     * This color constant "lead purple" has RGBA8888 code {@code 6D4B7DFF}, L 0.36078432, A 0.5294118, B 0.46666667, alpha 1.0, hue 0.875, saturation 0.3481141, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.ef0eb8p125F}.
     * <pre>
     * <font style='background-color: #6D4B7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D4B7D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D4B7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6D4B7D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6D4B7D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6D4B7D'>&nbsp;@&nbsp;</font><font style='background-color: #6D4B7D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D4B7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D4B7D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_PURPLE = -0x1.ef0eb8p125F;
    static { NAMED.put("lead purple", -0x1.ef0eb8p125F); LIST.add(-0x1.ef0eb8p125F); }

    /**
     * This color constant "gray purple" has RGBA8888 code {@code 89649AFF}, L 0.46666667, A 0.5294118, B 0.46666667, alpha 1.0, hue 0.875, saturation 0.29386255, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.ef0eeep125F}.
     * <pre>
     * <font style='background-color: #89649A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #89649A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #89649A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #89649A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #89649A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #89649A'>&nbsp;@&nbsp;</font><font style='background-color: #89649A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #89649A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #89649A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_PURPLE = -0x1.ef0eeep125F;
    static { NAMED.put("gray purple", -0x1.ef0eeep125F); LIST.add(-0x1.ef0eeep125F); }

    /**
     * This color constant "silver purple" has RGBA8888 code {@code A881BAFF}, L 0.58431375, A 0.5294118, B 0.46666667, alpha 1.0, hue 0.875, saturation 0.34283966, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.ef0f2ap125F}.
     * <pre>
     * <font style='background-color: #A881BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A881BA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A881BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A881BA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A881BA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A881BA'>&nbsp;@&nbsp;</font><font style='background-color: #A881BA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A881BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A881BA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_PURPLE = -0x1.ef0f2ap125F;
    static { NAMED.put("silver purple", -0x1.ef0f2ap125F); LIST.add(-0x1.ef0f2ap125F); }

    /**
     * This color constant "white purple" has RGBA8888 code {@code CDA3E1FF}, L 0.7176471, A 0.5294118, B 0.46666667, alpha 1.0, hue 0.875, saturation 0.5518882, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.ef0f6ep125F}.
     * <pre>
     * <font style='background-color: #CDA3E1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CDA3E1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CDA3E1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CDA3E1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CDA3E1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CDA3E1'>&nbsp;@&nbsp;</font><font style='background-color: #CDA3E1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CDA3E1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CDA3E1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_PURPLE = -0x1.ef0f6ep125F;
    static { NAMED.put("white purple", -0x1.ef0f6ep125F); LIST.add(-0x1.ef0f6ep125F); }

    /**
     * This color constant "black magenta" has RGBA8888 code {@code 684068FF}, L 0.31764707, A 0.53333336, B 0.4745098, alpha 1.0, hue 0.90640444, saturation 0.37953174, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.f310a2p125F}.
     * <pre>
     * <font style='background-color: #684068;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #684068; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #684068;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #684068'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #684068'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #684068'>&nbsp;@&nbsp;</font><font style='background-color: #684068; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #684068;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #684068; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_MAGENTA = -0x1.f310a2p125F;
    static { NAMED.put("black magenta", -0x1.f310a2p125F); LIST.add(-0x1.f310a2p125F); }

    /**
     * This color constant "lead magenta" has RGBA8888 code {@code 875B87FF}, L 0.43529412, A 0.53333336, B 0.4745098, alpha 1.0, hue 0.90640444, saturation 0.3135262, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.f310dep125F}.
     * <pre>
     * <font style='background-color: #875B87;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #875B87; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #875B87;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #875B87'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #875B87'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #875B87'>&nbsp;@&nbsp;</font><font style='background-color: #875B87; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #875B87;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #875B87; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_MAGENTA = -0x1.f310dep125F;
    static { NAMED.put("lead magenta", -0x1.f310dep125F); LIST.add(-0x1.f310dep125F); }

    /**
     * This color constant "gray magenta" has RGBA8888 code {@code A474A4FF}, L 0.5411765, A 0.53333336, B 0.4745098, alpha 1.0, hue 0.90640444, saturation 0.27041635, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.f31114p125F}.
     * <pre>
     * <font style='background-color: #A474A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A474A4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A474A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A474A4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A474A4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A474A4'>&nbsp;@&nbsp;</font><font style='background-color: #A474A4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A474A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A474A4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_MAGENTA = -0x1.f31114p125F;
    static { NAMED.put("gray magenta", -0x1.f31114p125F); LIST.add(-0x1.f31114p125F); }

    /**
     * This color constant "silver magenta" has RGBA8888 code {@code BE8CBEFF}, L 0.63529414, A 0.53333336, B 0.4745098, alpha 1.0, hue 0.90640444, saturation 0.292342, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.f31144p125F}.
     * <pre>
     * <font style='background-color: #BE8CBE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE8CBE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE8CBE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BE8CBE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BE8CBE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BE8CBE'>&nbsp;@&nbsp;</font><font style='background-color: #BE8CBE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE8CBE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE8CBE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_MAGENTA = -0x1.f31144p125F;
    static { NAMED.put("silver magenta", -0x1.f31144p125F); LIST.add(-0x1.f31144p125F); }

    /**
     * This color constant "white magenta" has RGBA8888 code {@code DEA9DEFF}, L 0.7490196, A 0.53333336, B 0.4745098, alpha 1.0, hue 0.90640444, saturation 0.4807402, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.f3117ep125F}.
     * <pre>
     * <font style='background-color: #DEA9DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEA9DE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEA9DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DEA9DE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DEA9DE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DEA9DE'>&nbsp;@&nbsp;</font><font style='background-color: #DEA9DE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEA9DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEA9DE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_MAGENTA = -0x1.f3117ep125F;
    static { NAMED.put("white magenta", -0x1.f3117ep125F); LIST.add(-0x1.f3117ep125F); }

    /**
     * This color constant "drab red" has RGBA8888 code {@code 8E3029FF}, L 0.3254902, A 0.5568628, B 0.5294118, alpha 1.0, hue 0.07798134, saturation 0.68, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.0f1ca6p126F}.
     * <pre>
     * <font style='background-color: #8E3029;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8E3029; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8E3029;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8E3029'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8E3029'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8E3029'>&nbsp;@&nbsp;</font><font style='background-color: #8E3029; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8E3029;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8E3029; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_RED = -0x1.0f1ca6p126F;
    static { NAMED.put("drab red", -0x1.0f1ca6p126F); LIST.add(-0x1.0f1ca6p126F); }

    /**
     * This color constant "faded red" has RGBA8888 code {@code B34C42FF}, L 0.44313726, A 0.5568628, B 0.5294118, alpha 1.0, hue 0.07798134, saturation 0.55737704, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.0f1ce2p126F}.
     * <pre>
     * <font style='background-color: #B34C42;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B34C42; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B34C42;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B34C42'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B34C42'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B34C42'>&nbsp;@&nbsp;</font><font style='background-color: #B34C42; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B34C42;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B34C42; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_RED = -0x1.0f1ce2p126F;
    static { NAMED.put("faded red", -0x1.0f1ce2p126F); LIST.add(-0x1.0f1ce2p126F); }

    /**
     * This color constant "pale red" has RGBA8888 code {@code DE6E62FF}, L 0.58431375, A 0.5568628, B 0.5294118, alpha 1.0, hue 0.07798134, saturation 0.6415094, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.0f1d2ap126F}.
     * <pre>
     * <font style='background-color: #DE6E62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DE6E62; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DE6E62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DE6E62'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DE6E62'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DE6E62'>&nbsp;@&nbsp;</font><font style='background-color: #DE6E62; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DE6E62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DE6E62; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_RED = -0x1.0f1d2ap126F;
    static { NAMED.put("pale red", -0x1.0f1d2ap126F); LIST.add(-0x1.0f1d2ap126F); }

    /**
     * This color constant "drab brown" has RGBA8888 code {@code 815648FF}, L 0.3882353, A 0.52156866, B 0.5176471, alpha 1.0, hue 0.11058099, saturation 0.34712222, and chroma 0.055518243.
     * It can be represented as a packed float with the constant {@code -0x1.090ac6p126F}.
     * <pre>
     * <font style='background-color: #815648;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #815648; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #815648;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #815648'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #815648'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #815648'>&nbsp;@&nbsp;</font><font style='background-color: #815648; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #815648;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #815648; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BROWN = -0x1.090ac6p126F;
    static { NAMED.put("drab brown", -0x1.090ac6p126F); LIST.add(-0x1.090ac6p126F); }

    /**
     * This color constant "faded brown" has RGBA8888 code {@code A17363FF}, L 0.50980395, A 0.52156866, B 0.5176471, alpha 1.0, hue 0.11058099, saturation 0.28926852, and chroma 0.055518243.
     * It can be represented as a packed float with the constant {@code -0x1.090b04p126F}.
     * <pre>
     * <font style='background-color: #A17363;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A17363; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A17363;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A17363'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A17363'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A17363'>&nbsp;@&nbsp;</font><font style='background-color: #A17363; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A17363;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A17363; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_BROWN = -0x1.090b04p126F;
    static { NAMED.put("faded brown", -0x1.090b04p126F); LIST.add(-0x1.090b04p126F); }

    /**
     * This color constant "pale brown" has RGBA8888 code {@code C59281FF}, L 0.63529414, A 0.52156866, B 0.5176471, alpha 1.0, hue 0.11058099, saturation 0.33957607, and chroma 0.055518243.
     * It can be represented as a packed float with the constant {@code -0x1.090b44p126F}.
     * <pre>
     * <font style='background-color: #C59281;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C59281; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C59281;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C59281'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C59281'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C59281'>&nbsp;@&nbsp;</font><font style='background-color: #C59281; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C59281;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C59281; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BROWN = -0x1.090b44p126F;
    static { NAMED.put("pale brown", -0x1.090b44p126F); LIST.add(-0x1.090b44p126F); }

    /**
     * This color constant "drab orange" has RGBA8888 code {@code 90552EFF}, L 0.40392157, A 0.5254902, B 0.5372549, alpha 1.0, hue 0.15278715, saturation 0.67814195, and chroma 0.08992863.
     * It can be represented as a packed float with the constant {@code -0x1.130ccep126F}.
     * <pre>
     * <font style='background-color: #90552E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #90552E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #90552E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #90552E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #90552E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #90552E'>&nbsp;@&nbsp;</font><font style='background-color: #90552E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #90552E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #90552E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_ORANGE = -0x1.130ccep126F;
    static { NAMED.put("drab orange", -0x1.130ccep126F); LIST.add(-0x1.130ccep126F); }

    /**
     * This color constant "faded orange" has RGBA8888 code {@code B17248FF}, L 0.52156866, A 0.5254902, B 0.5372549, alpha 1.0, hue 0.15278715, saturation 0.56774676, and chroma 0.08992863.
     * It can be represented as a packed float with the constant {@code -0x1.130d0ap126F}.
     * <pre>
     * <font style='background-color: #B17248;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B17248; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B17248;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B17248'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B17248'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B17248'>&nbsp;@&nbsp;</font><font style='background-color: #B17248; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B17248;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B17248; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_ORANGE = -0x1.130d0ap126F;
    static { NAMED.put("faded orange", -0x1.130d0ap126F); LIST.add(-0x1.130d0ap126F); }

    /**
     * This color constant "pale orange" has RGBA8888 code {@code DB966AFF}, L 0.6666667, A 0.5254902, B 0.5372549, alpha 1.0, hue 0.15278715, saturation 0.54251355, and chroma 0.08992863.
     * It can be represented as a packed float with the constant {@code -0x1.130d54p126F}.
     * <pre>
     * <font style='background-color: #DB966A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB966A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB966A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DB966A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DB966A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DB966A'>&nbsp;@&nbsp;</font><font style='background-color: #DB966A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB966A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB966A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_ORANGE = -0x1.130d54p126F;
    static { NAMED.put("pale orange", -0x1.130d54p126F); LIST.add(-0x1.130d54p126F); }

    /**
     * This color constant "drab saffron" has RGBA8888 code {@code 946D3EFF}, L 0.47058824, A 0.50980395, B 0.5372549, alpha 1.0, hue 0.20362332, saturation 0.6141357, and chroma 0.07674564.
     * It can be represented as a packed float with the constant {@code -0x1.1304fp126F}.
     * <pre>
     * <font style='background-color: #946D3E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #946D3E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #946D3E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #946D3E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #946D3E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #946D3E'>&nbsp;@&nbsp;</font><font style='background-color: #946D3E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #946D3E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #946D3E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_SAFFRON = -0x1.1304fp126F;
    static { NAMED.put("drab saffron", -0x1.1304fp126F); LIST.add(-0x1.1304fp126F); }

    /**
     * This color constant "faded saffron" has RGBA8888 code {@code BB915EFF}, L 0.6117647, A 0.50980395, B 0.5372549, alpha 1.0, hue 0.20362332, saturation 0.50928324, and chroma 0.07674564.
     * It can be represented as a packed float with the constant {@code -0x1.130538p126F}.
     * <pre>
     * <font style='background-color: #BB915E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB915E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB915E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BB915E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BB915E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BB915E'>&nbsp;@&nbsp;</font><font style='background-color: #BB915E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB915E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB915E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_SAFFRON = -0x1.130538p126F;
    static { NAMED.put("faded saffron", -0x1.130538p126F); LIST.add(-0x1.130538p126F); }

    /**
     * This color constant "pale saffron" has RGBA8888 code {@code DDB17BFF}, L 0.73333335, A 0.50980395, B 0.5372549, alpha 1.0, hue 0.20362332, saturation 0.4745594, and chroma 0.07674564.
     * It can be represented as a packed float with the constant {@code -0x1.130576p126F}.
     * <pre>
     * <font style='background-color: #DDB17B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DDB17B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DDB17B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DDB17B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DDB17B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DDB17B'>&nbsp;@&nbsp;</font><font style='background-color: #DDB17B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DDB17B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DDB17B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_SAFFRON = -0x1.130576p126F;
    static { NAMED.put("pale saffron", -0x1.130576p126F); LIST.add(-0x1.130576p126F); }

    /**
     * This color constant "drab yellow" has RGBA8888 code {@code 8D8E32FF}, L 0.54509807, A 0.47843137, B 0.5529412, alpha 1.0, hue 0.304581, saturation 0.7824247, and chroma 0.11388578.
     * It can be represented as a packed float with the constant {@code -0x1.1af516p126F}.
     * <pre>
     * <font style='background-color: #8D8E32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8D8E32; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8D8E32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8D8E32'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8D8E32'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8D8E32'>&nbsp;@&nbsp;</font><font style='background-color: #8D8E32; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8D8E32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8D8E32; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_YELLOW = -0x1.1af516p126F;
    static { NAMED.put("drab yellow", -0x1.1af516p126F); LIST.add(-0x1.1af516p126F); }

    /**
     * This color constant "faded yellow" has RGBA8888 code {@code AFB051FF}, L 0.6745098, A 0.47843137, B 0.5529412, alpha 1.0, hue 0.304581, saturation 0.6757304, and chroma 0.11388578.
     * It can be represented as a packed float with the constant {@code -0x1.1af558p126F}.
     * <pre>
     * <font style='background-color: #AFB051;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AFB051; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AFB051;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AFB051'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AFB051'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AFB051'>&nbsp;@&nbsp;</font><font style='background-color: #AFB051; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AFB051;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AFB051; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_YELLOW = -0x1.1af558p126F;
    static { NAMED.put("faded yellow", -0x1.1af558p126F); LIST.add(-0x1.1af558p126F); }

    /**
     * This color constant "pale yellow" has RGBA8888 code {@code D8DA76FF}, L 0.8235294, A 0.47843137, B 0.5529412, alpha 1.0, hue 0.304581, saturation 0.5829831, and chroma 0.11388578.
     * It can be represented as a packed float with the constant {@code -0x1.1af5a4p126F}.
     * <pre>
     * <font style='background-color: #D8DA76;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D8DA76; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D8DA76;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D8DA76'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D8DA76'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D8DA76'>&nbsp;@&nbsp;</font><font style='background-color: #D8DA76; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D8DA76;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D8DA76; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOW = -0x1.1af5a4p126F;
    static { NAMED.put("pale yellow", -0x1.1af5a4p126F); LIST.add(-0x1.1af5a4p126F); }

    /**
     * This color constant "drab lime" has RGBA8888 code {@code 688837FF}, L 0.49411765, A 0.4627451, B 0.54509807, alpha 1.0, hue 0.3524291, saturation 0.6976744, and chroma 0.11653464.
     * It can be represented as a packed float with the constant {@code -0x1.16ecfcp126F}.
     * <pre>
     * <font style='background-color: #688837;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #688837; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #688837;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #688837'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #688837'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #688837'>&nbsp;@&nbsp;</font><font style='background-color: #688837; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #688837;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #688837; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_LIME = -0x1.16ecfcp126F;
    static { NAMED.put("drab lime", -0x1.16ecfcp126F); LIST.add(-0x1.16ecfcp126F); }

    /**
     * This color constant "faded lime" has RGBA8888 code {@code 88AB55FF}, L 0.627451, A 0.4627451, B 0.54509807, alpha 1.0, hue 0.3524291, saturation 0.6, and chroma 0.11653464.
     * It can be represented as a packed float with the constant {@code -0x1.16ed4p126F}.
     * <pre>
     * <font style='background-color: #88AB55;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #88AB55; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #88AB55;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #88AB55'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #88AB55'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #88AB55'>&nbsp;@&nbsp;</font><font style='background-color: #88AB55; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #88AB55;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #88AB55; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_LIME = -0x1.16ed4p126F;
    static { NAMED.put("faded lime", -0x1.16ed4p126F); LIST.add(-0x1.16ed4p126F); }

    /**
     * This color constant "pale lime" has RGBA8888 code {@code B1D87CFF}, L 0.7882353, A 0.4627451, B 0.54509807, alpha 1.0, hue 0.3524291, saturation 0.5084746, and chroma 0.11653464.
     * It can be represented as a packed float with the constant {@code -0x1.16ed92p126F}.
     * <pre>
     * <font style='background-color: #B1D87C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1D87C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1D87C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B1D87C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B1D87C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B1D87C'>&nbsp;@&nbsp;</font><font style='background-color: #B1D87C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1D87C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1D87C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_LIME = -0x1.16ed92p126F;
    static { NAMED.put("pale lime", -0x1.16ed92p126F); LIST.add(-0x1.16ed92p126F); }

    /**
     * This color constant "drab green" has RGBA8888 code {@code 369440FF}, L 0.49803922, A 0.43529412, B 0.5411765, alpha 1.0, hue 0.4041304, saturation 0.7191292, and chroma 0.15279381.
     * It can be represented as a packed float with the constant {@code -0x1.14defep126F}.
     * <pre>
     * <font style='background-color: #369440;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #369440; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #369440;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #369440'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #369440'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #369440'>&nbsp;@&nbsp;</font><font style='background-color: #369440; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #369440;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #369440; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_GREEN = -0x1.14defep126F;
    static { NAMED.put("drab green", -0x1.14defep126F); LIST.add(-0x1.14defep126F); }

    /**
     * This color constant "faded green" has RGBA8888 code {@code 56B85EFF}, L 0.6313726, A 0.43529412, B 0.5411765, alpha 1.0, hue 0.4041304, saturation 0.6163965, and chroma 0.15279381.
     * It can be represented as a packed float with the constant {@code -0x1.14df42p126F}.
     * <pre>
     * <font style='background-color: #56B85E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #56B85E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #56B85E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #56B85E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #56B85E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #56B85E'>&nbsp;@&nbsp;</font><font style='background-color: #56B85E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #56B85E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #56B85E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_GREEN = -0x1.14df42p126F;
    static { NAMED.put("faded green", -0x1.14df42p126F); LIST.add(-0x1.14df42p126F); }

    /**
     * This color constant "pale green" has RGBA8888 code {@code 76DB7CFF}, L 0.7529412, A 0.43529412, B 0.5411765, alpha 1.0, hue 0.4041304, saturation 0.54694337, and chroma 0.15279381.
     * It can be represented as a packed float with the constant {@code -0x1.14df8p126F}.
     * <pre>
     * <font style='background-color: #76DB7C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #76DB7C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #76DB7C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #76DB7C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #76DB7C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #76DB7C'>&nbsp;@&nbsp;</font><font style='background-color: #76DB7C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #76DB7C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #76DB7C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_GREEN = -0x1.14df8p126F;
    static { NAMED.put("pale green", -0x1.14df8p126F); LIST.add(-0x1.14df8p126F); }

    /**
     * This color constant "drab cyan" has RGBA8888 code {@code 388C8DFF}, L 0.49803922, A 0.45882353, B 0.4862745, alpha 1.0, hue 0.54637665, saturation 0.7457362, and chroma 0.08646853.
     * It can be represented as a packed float with the constant {@code -0x1.f8eafep125F}.
     * <pre>
     * <font style='background-color: #388C8D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #388C8D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #388C8D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #388C8D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #388C8D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #388C8D'>&nbsp;@&nbsp;</font><font style='background-color: #388C8D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #388C8D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #388C8D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_CYAN = -0x1.f8eafep125F;
    static { NAMED.put("drab cyan", -0x1.f8eafep125F); LIST.add(-0x1.f8eafep125F); }

    /**
     * This color constant "faded cyan" has RGBA8888 code {@code 57ADAFFF}, L 0.62352943, A 0.45882353, B 0.4862745, alpha 1.0, hue 0.54637665, saturation 0.63274586, and chroma 0.08646853.
     * It can be represented as a packed float with the constant {@code -0x1.f8eb3ep125F}.
     * <pre>
     * <font style='background-color: #57ADAF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57ADAF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57ADAF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #57ADAF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #57ADAF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #57ADAF'>&nbsp;@&nbsp;</font><font style='background-color: #57ADAF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57ADAF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57ADAF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_CYAN = -0x1.f8eb3ep125F;
    static { NAMED.put("faded cyan", -0x1.f8eb3ep125F); LIST.add(-0x1.f8eb3ep125F); }

    /**
     * This color constant "pale cyan" has RGBA8888 code {@code 7ED8D9FF}, L 0.7764706, A 0.45882353, B 0.4862745, alpha 1.0, hue 0.54637665, saturation 0.5494898, and chroma 0.08646853.
     * It can be represented as a packed float with the constant {@code -0x1.f8eb8cp125F}.
     * <pre>
     * <font style='background-color: #7ED8D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7ED8D9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7ED8D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7ED8D9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7ED8D9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7ED8D9'>&nbsp;@&nbsp;</font><font style='background-color: #7ED8D9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7ED8D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7ED8D9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_CYAN = -0x1.f8eb8cp125F;
    static { NAMED.put("pale cyan", -0x1.f8eb8cp125F); LIST.add(-0x1.f8eb8cp125F); }

    /**
     * This color constant "drab blue" has RGBA8888 code {@code 1F40A3FF}, L 0.28627452, A 0.49019608, B 0.41568628, alpha 1.0, hue 0.7348826, saturation 0.540898, and chroma 0.16910048.
     * It can be represented as a packed float with the constant {@code -0x1.d4fa92p125F}.
     * <pre>
     * <font style='background-color: #1F40A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1F40A3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1F40A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1F40A3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1F40A3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1F40A3'>&nbsp;@&nbsp;</font><font style='background-color: #1F40A3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1F40A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1F40A3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BLUE = -0x1.d4fa92p125F;
    static { NAMED.put("drab blue", -0x1.d4fa92p125F); LIST.add(-0x1.d4fa92p125F); }

    /**
     * This color constant "faded blue" has RGBA8888 code {@code 375EC9FF}, L 0.40784314, A 0.49019608, B 0.41568628, alpha 1.0, hue 0.7348826, saturation 0.62970215, and chroma 0.16910048.
     * It can be represented as a packed float with the constant {@code -0x1.d4fadp125F}.
     * <pre>
     * <font style='background-color: #375EC9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #375EC9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #375EC9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #375EC9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #375EC9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #375EC9'>&nbsp;@&nbsp;</font><font style='background-color: #375EC9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #375EC9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #375EC9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_BLUE = -0x1.d4fadp125F;
    static { NAMED.put("faded blue", -0x1.d4fadp125F); LIST.add(-0x1.d4fadp125F); }

    /**
     * This color constant "pale blue" has RGBA8888 code {@code 4E7AEBFF}, L 0.5137255, A 0.49019608, B 0.41568628, alpha 1.0, hue 0.7348826, saturation 0.811347, and chroma 0.16910048.
     * It can be represented as a packed float with the constant {@code -0x1.d4fb06p125F}.
     * <pre>
     * <font style='background-color: #4E7AEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E7AEB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E7AEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4E7AEB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4E7AEB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4E7AEB'>&nbsp;@&nbsp;</font><font style='background-color: #4E7AEB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E7AEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E7AEB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BLUE = -0x1.d4fb06p125F;
    static { NAMED.put("pale blue", -0x1.d4fb06p125F); LIST.add(-0x1.d4fb06p125F); }

    /**
     * This color constant "drab violet" has RGBA8888 code {@code 603E93FF}, L 0.32941177, A 0.53333336, B 0.4392157, alpha 1.0, hue 0.83601886, saturation 0.5300865, and chroma 0.1381068.
     * It can be represented as a packed float with the constant {@code -0x1.e110a8p125F}.
     * <pre>
     * <font style='background-color: #603E93;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #603E93; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #603E93;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #603E93'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #603E93'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #603E93'>&nbsp;@&nbsp;</font><font style='background-color: #603E93; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #603E93;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #603E93; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_VIOLET = -0x1.e110a8p125F;
    static { NAMED.put("drab violet", -0x1.e110a8p125F); LIST.add(-0x1.e110a8p125F); }

    /**
     * This color constant "faded violet" has RGBA8888 code {@code 7D59B5FF}, L 0.44313726, A 0.53333336, B 0.4392157, alpha 1.0, hue 0.83601886, saturation 0.4603383, and chroma 0.1381068.
     * It can be represented as a packed float with the constant {@code -0x1.e110e2p125F}.
     * <pre>
     * <font style='background-color: #7D59B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7D59B5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7D59B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7D59B5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7D59B5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7D59B5'>&nbsp;@&nbsp;</font><font style='background-color: #7D59B5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7D59B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7D59B5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_VIOLET = -0x1.e110e2p125F;
    static { NAMED.put("faded violet", -0x1.e110e2p125F); LIST.add(-0x1.e110e2p125F); }

    /**
     * This color constant "pale violet" has RGBA8888 code {@code 9C77D9FF}, L 0.5647059, A 0.53333336, B 0.4392157, alpha 1.0, hue 0.83601886, saturation 0.62474483, and chroma 0.1381068.
     * It can be represented as a packed float with the constant {@code -0x1.e1112p125F}.
     * <pre>
     * <font style='background-color: #9C77D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C77D9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C77D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9C77D9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9C77D9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9C77D9'>&nbsp;@&nbsp;</font><font style='background-color: #9C77D9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C77D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C77D9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_VIOLET = -0x1.e1112p125F;
    static { NAMED.put("pale violet", -0x1.e1112p125F); LIST.add(-0x1.e1112p125F); }

    /**
     * This color constant "drab purple" has RGBA8888 code {@code 733494FF}, L 0.3372549, A 0.5529412, B 0.4392157, alpha 1.0, hue 0.8695183, saturation 0.6513741, and chroma 0.1605844.
     * It can be represented as a packed float with the constant {@code -0x1.e11aacp125F}.
     * <pre>
     * <font style='background-color: #733494;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #733494; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #733494;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #733494'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #733494'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #733494'>&nbsp;@&nbsp;</font><font style='background-color: #733494; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #733494;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #733494; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_PURPLE = -0x1.e11aacp125F;
    static { NAMED.put("drab purple", -0x1.e11aacp125F); LIST.add(-0x1.e11aacp125F); }

    /**
     * This color constant "faded purple" has RGBA8888 code {@code 9350B7FF}, L 0.45490196, A 0.5529412, B 0.4392157, alpha 1.0, hue 0.8695183, saturation 0.53995484, and chroma 0.1605844.
     * It can be represented as a packed float with the constant {@code -0x1.e11ae8p125F}.
     * <pre>
     * <font style='background-color: #9350B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9350B7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9350B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9350B7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9350B7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9350B7'>&nbsp;@&nbsp;</font><font style='background-color: #9350B7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9350B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9350B7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_PURPLE = -0x1.e11ae8p125F;
    static { NAMED.put("faded purple", -0x1.e11ae8p125F); LIST.add(-0x1.e11ae8p125F); }

    /**
     * This color constant "pale purple" has RGBA8888 code {@code BA73E1FF}, L 0.59607846, A 0.5529412, B 0.4392157, alpha 1.0, hue 0.8695183, saturation 0.6839428, and chroma 0.1605844.
     * It can be represented as a packed float with the constant {@code -0x1.e11b3p125F}.
     * <pre>
     * <font style='background-color: #BA73E1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA73E1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA73E1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BA73E1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BA73E1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BA73E1'>&nbsp;@&nbsp;</font><font style='background-color: #BA73E1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA73E1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA73E1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLE = -0x1.e11b3p125F;
    static { NAMED.put("pale purple", -0x1.e11b3p125F); LIST.add(-0x1.e11b3p125F); }

    /**
     * This color constant "drab magenta" has RGBA8888 code {@code 953F90FF}, L 0.40392157, A 0.5647059, B 0.45882353, alpha 1.0, hue 0.915366, saturation 0.6068641, and chroma 0.15279381.
     * It can be represented as a packed float with the constant {@code -0x1.eb20cep125F}.
     * <pre>
     * <font style='background-color: #953F90;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #953F90; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #953F90;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #953F90'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #953F90'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #953F90'>&nbsp;@&nbsp;</font><font style='background-color: #953F90; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #953F90;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #953F90; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_MAGENTA = -0x1.eb20cep125F;
    static { NAMED.put("drab magenta", -0x1.eb20cep125F); LIST.add(-0x1.eb20cep125F); }

    /**
     * This color constant "faded magenta" has RGBA8888 code {@code BA5EB4FF}, L 0.53333336, A 0.5647059, B 0.45882353, alpha 1.0, hue 0.915366, saturation 0.5057201, and chroma 0.15279381.
     * It can be represented as a packed float with the constant {@code -0x1.eb211p125F}.
     * <pre>
     * <font style='background-color: #BA5EB4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA5EB4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA5EB4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BA5EB4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BA5EB4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BA5EB4'>&nbsp;@&nbsp;</font><font style='background-color: #BA5EB4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA5EB4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA5EB4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_MAGENTA = -0x1.eb211p125F;
    static { NAMED.put("faded magenta", -0x1.eb211p125F); LIST.add(-0x1.eb211p125F); }

    /**
     * This color constant "pale magenta" has RGBA8888 code {@code DD7BD6FF}, L 0.6509804, A 0.5647059, B 0.45882353, alpha 1.0, hue 0.915366, saturation 0.5635167, and chroma 0.15279381.
     * It can be represented as a packed float with the constant {@code -0x1.eb214cp125F}.
     * <pre>
     * <font style='background-color: #DD7BD6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD7BD6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD7BD6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DD7BD6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DD7BD6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DD7BD6'>&nbsp;@&nbsp;</font><font style='background-color: #DD7BD6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD7BD6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD7BD6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_MAGENTA = -0x1.eb214cp125F;
    static { NAMED.put("pale magenta", -0x1.eb214cp125F); LIST.add(-0x1.eb214cp125F); }

    /**
     * This color constant "deep pure red" has RGBA8888 code {@code C3241EFF}, L 0.40784314, A 0.58431375, B 0.54509807, alpha 1.0, hue 0.07947698, saturation 0.8641355, and chroma 0.19048727.
     * It can be represented as a packed float with the constant {@code -0x1.172adp126F}.
     * <pre>
     * <font style='background-color: #C3241E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3241E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3241E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C3241E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C3241E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C3241E'>&nbsp;@&nbsp;</font><font style='background-color: #C3241E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3241E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3241E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_RED = -0x1.172adp126F;
    static { NAMED.put("deep pure red", -0x1.172adp126F); LIST.add(-0x1.172adp126F); }

    /**
     * This color constant "true pure red" has RGBA8888 code {@code D6362CFF}, L 0.46666667, A 0.58431375, B 0.54509807, alpha 1.0, hue 0.07947698, saturation 0.7955533, and chroma 0.19048727.
     * It can be represented as a packed float with the constant {@code -0x1.172aeep126F}.
     * <pre>
     * <font style='background-color: #D6362C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D6362C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D6362C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D6362C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D6362C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D6362C'>&nbsp;@&nbsp;</font><font style='background-color: #D6362C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D6362C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D6362C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_RED = -0x1.172aeep126F;
    static { NAMED.put("true pure red", -0x1.172aeep126F); LIST.add(-0x1.172aeep126F); }

    /**
     * This color constant "bright pure red" has RGBA8888 code {@code EE493CFF}, L 0.5372549, A 0.58431375, B 0.54509807, alpha 1.0, hue 0.07947698, saturation 0.8083848, and chroma 0.19048727.
     * It can be represented as a packed float with the constant {@code -0x1.172b12p126F}.
     * <pre>
     * <font style='background-color: #EE493C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EE493C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EE493C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EE493C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EE493C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EE493C'>&nbsp;@&nbsp;</font><font style='background-color: #EE493C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EE493C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EE493C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_RED = -0x1.172b12p126F;
    static { NAMED.put("bright pure red", -0x1.172b12p126F); LIST.add(-0x1.172b12p126F); }

    /**
     * This color constant "deep brown red" has RGBA8888 code {@code B34934FF}, L 0.43529412, A 0.5568628, B 0.5372549, alpha 1.0, hue 0.09359558, saturation 0.62164676, and chroma 0.13542919.
     * It can be represented as a packed float with the constant {@code -0x1.131cdep126F}.
     * <pre>
     * <font style='background-color: #B34934;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B34934; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B34934;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B34934'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B34934'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B34934'>&nbsp;@&nbsp;</font><font style='background-color: #B34934; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B34934;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B34934; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN_RED = -0x1.131cdep126F;
    static { NAMED.put("deep brown red", -0x1.131cdep126F); LIST.add(-0x1.131cdep126F); }

    /**
     * This color constant "true brown red" has RGBA8888 code {@code C65741FF}, L 0.49411765, A 0.5568628, B 0.5372549, alpha 1.0, hue 0.09359558, saturation 0.57230973, and chroma 0.13542919.
     * It can be represented as a packed float with the constant {@code -0x1.131cfcp126F}.
     * <pre>
     * <font style='background-color: #C65741;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C65741; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C65741;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C65741'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C65741'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C65741'>&nbsp;@&nbsp;</font><font style='background-color: #C65741; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C65741;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C65741; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BROWN_RED = -0x1.131cfcp126F;
    static { NAMED.put("true brown red", -0x1.131cfcp126F); LIST.add(-0x1.131cfcp126F); }

    /**
     * This color constant "bright brown red" has RGBA8888 code {@code D8654EFF}, L 0.5529412, A 0.5568628, B 0.5372549, alpha 1.0, hue 0.09359558, saturation 0.6111104, and chroma 0.13542919.
     * It can be represented as a packed float with the constant {@code -0x1.131d1ap126F}.
     * <pre>
     * <font style='background-color: #D8654E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D8654E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D8654E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D8654E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D8654E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D8654E'>&nbsp;@&nbsp;</font><font style='background-color: #D8654E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D8654E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D8654E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN_RED = -0x1.131d1ap126F;
    static { NAMED.put("bright brown red", -0x1.131d1ap126F); LIST.add(-0x1.131d1ap126F); }

    /**
     * This color constant "deep red brown" has RGBA8888 code {@code AD553EFF}, L 0.4509804, A 0.54509807, B 0.53333336, alpha 1.0, hue 0.102429084, saturation 0.5660377, and chroma 0.11172148.
     * It can be represented as a packed float with the constant {@code -0x1.1116e6p126F}.
     * <pre>
     * <font style='background-color: #AD553E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD553E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD553E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AD553E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AD553E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AD553E'>&nbsp;@&nbsp;</font><font style='background-color: #AD553E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD553E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD553E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED_BROWN = -0x1.1116e6p126F;
    static { NAMED.put("deep red brown", -0x1.1116e6p126F); LIST.add(-0x1.1116e6p126F); }

    /**
     * This color constant "true red brown" has RGBA8888 code {@code BE634BFF}, L 0.50980395, A 0.54509807, B 0.53333336, alpha 1.0, hue 0.102429084, saturation 0.51724136, and chroma 0.11172148.
     * It can be represented as a packed float with the constant {@code -0x1.111704p126F}.
     * <pre>
     * <font style='background-color: #BE634B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE634B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE634B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BE634B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BE634B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BE634B'>&nbsp;@&nbsp;</font><font style='background-color: #BE634B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE634B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE634B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_RED_BROWN = -0x1.111704p126F;
    static { NAMED.put("true red brown", -0x1.111704p126F); LIST.add(-0x1.111704p126F); }

    /**
     * This color constant "bright red brown" has RGBA8888 code {@code D4755BFF}, L 0.5803922, A 0.54509807, B 0.53333336, alpha 1.0, hue 0.102429084, saturation 0.54545456, and chroma 0.11172148.
     * It can be represented as a packed float with the constant {@code -0x1.111728p126F}.
     * <pre>
     * <font style='background-color: #D4755B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4755B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4755B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D4755B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D4755B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D4755B'>&nbsp;@&nbsp;</font><font style='background-color: #D4755B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4755B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4755B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED_BROWN = -0x1.111728p126F;
    static { NAMED.put("bright red brown", -0x1.111728p126F); LIST.add(-0x1.111728p126F); }

    /**
     * This color constant "deep pure brown" has RGBA8888 code {@code A6674CFF}, L 0.48235294, A 0.5294118, B 0.5294118, alpha 1.0, hue 0.125, saturation 0.4814344, and chroma 0.082864076.
     * It can be represented as a packed float with the constant {@code -0x1.0f0ef6p126F}.
     * <pre>
     * <font style='background-color: #A6674C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A6674C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A6674C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A6674C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A6674C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A6674C'>&nbsp;@&nbsp;</font><font style='background-color: #A6674C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A6674C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A6674C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_BROWN = -0x1.0f0ef6p126F;
    static { NAMED.put("deep pure brown", -0x1.0f0ef6p126F); LIST.add(-0x1.0f0ef6p126F); }

    /**
     * This color constant "true pure brown" has RGBA8888 code {@code B9775BFF}, L 0.54901963, A 0.5294118, B 0.5294118, alpha 1.0, hue 0.125, saturation 0.44367483, and chroma 0.082864076.
     * It can be represented as a packed float with the constant {@code -0x1.0f0f18p126F}.
     * <pre>
     * <font style='background-color: #B9775B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B9775B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B9775B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B9775B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B9775B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B9775B'>&nbsp;@&nbsp;</font><font style='background-color: #B9775B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B9775B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B9775B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_BROWN = -0x1.0f0f18p126F;
    static { NAMED.put("true pure brown", -0x1.0f0f18p126F); LIST.add(-0x1.0f0f18p126F); }

    /**
     * This color constant "bright pure brown" has RGBA8888 code {@code CB8668FF}, L 0.6117647, A 0.5294118, B 0.5294118, alpha 1.0, hue 0.125, saturation 0.44367483, and chroma 0.082864076.
     * It can be represented as a packed float with the constant {@code -0x1.0f0f38p126F}.
     * <pre>
     * <font style='background-color: #CB8668;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CB8668; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CB8668;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CB8668'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CB8668'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CB8668'>&nbsp;@&nbsp;</font><font style='background-color: #CB8668; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CB8668;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CB8668; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_BROWN = -0x1.0f0f38p126F;
    static { NAMED.put("bright pure brown", -0x1.0f0f38p126F); LIST.add(-0x1.0f0f38p126F); }

    /**
     * This color constant "deep orange brown" has RGBA8888 code {@code AF6748FF}, L 0.49411765, A 0.53333336, B 0.53333336, alpha 1.0, hue 0.125, saturation 0.5416137, and chroma 0.09391262.
     * It can be represented as a packed float with the constant {@code -0x1.1110fcp126F}.
     * <pre>
     * <font style='background-color: #AF6748;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF6748; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF6748;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AF6748'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AF6748'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AF6748'>&nbsp;@&nbsp;</font><font style='background-color: #AF6748; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF6748;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF6748; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_BROWN = -0x1.1110fcp126F;
    static { NAMED.put("deep orange brown", -0x1.1110fcp126F); LIST.add(-0x1.1110fcp126F); }

    /**
     * This color constant "true orange brown" has RGBA8888 code {@code C07555FF}, L 0.5529412, A 0.53333336, B 0.53333336, alpha 1.0, hue 0.125, saturation 0.4991342, and chroma 0.09391262.
     * It can be represented as a packed float with the constant {@code -0x1.11111ap126F}.
     * <pre>
     * <font style='background-color: #C07555;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C07555; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C07555;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C07555'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C07555'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C07555'>&nbsp;@&nbsp;</font><font style='background-color: #C07555; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C07555;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C07555; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_ORANGE_BROWN = -0x1.11111ap126F;
    static { NAMED.put("true orange brown", -0x1.11111ap126F); LIST.add(-0x1.11111ap126F); }

    /**
     * This color constant "bright orange brown" has RGBA8888 code {@code D48665FF}, L 0.61960787, A 0.53333336, B 0.53333336, alpha 1.0, hue 0.125, saturation 0.5091169, and chroma 0.09391262.
     * It can be represented as a packed float with the constant {@code -0x1.11113cp126F}.
     * <pre>
     * <font style='background-color: #D48665;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D48665; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D48665;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D48665'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D48665'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D48665'>&nbsp;@&nbsp;</font><font style='background-color: #D48665; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D48665;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D48665; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE_BROWN = -0x1.11113cp126F;
    static { NAMED.put("bright orange brown", -0x1.11113cp126F); LIST.add(-0x1.11113cp126F); }

    /**
     * This color constant "deep brown orange" has RGBA8888 code {@code AE6A43FF}, L 0.49803922, A 0.5294118, B 0.5372549, alpha 1.0, hue 0.14260027, saturation 0.58210224, and chroma 0.0945603.
     * It can be represented as a packed float with the constant {@code -0x1.130efep126F}.
     * <pre>
     * <font style='background-color: #AE6A43;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE6A43; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE6A43;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AE6A43'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AE6A43'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AE6A43'>&nbsp;@&nbsp;</font><font style='background-color: #AE6A43; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE6A43;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE6A43; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN_ORANGE = -0x1.130efep126F;
    static { NAMED.put("deep brown orange", -0x1.130efep126F); LIST.add(-0x1.130efep126F); }

    /**
     * This color constant "true brown orange" has RGBA8888 code {@code C07951FF}, L 0.56078434, A 0.5294118, B 0.5372549, alpha 1.0, hue 0.14260027, saturation 0.54494673, and chroma 0.0945603.
     * It can be represented as a packed float with the constant {@code -0x1.130f1ep126F}.
     * <pre>
     * <font style='background-color: #C07951;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C07951; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C07951;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C07951'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C07951'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C07951'>&nbsp;@&nbsp;</font><font style='background-color: #C07951; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C07951;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C07951; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BROWN_ORANGE = -0x1.130f1ep126F;
    static { NAMED.put("true brown orange", -0x1.130f1ep126F); LIST.add(-0x1.130f1ep126F); }

    /**
     * This color constant "bright brown orange" has RGBA8888 code {@code D68C62FF}, L 0.63529414, A 0.5294118, B 0.5372549, alpha 1.0, hue 0.14260027, saturation 0.522704, and chroma 0.0945603.
     * It can be represented as a packed float with the constant {@code -0x1.130f44p126F}.
     * <pre>
     * <font style='background-color: #D68C62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D68C62; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D68C62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D68C62'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D68C62'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D68C62'>&nbsp;@&nbsp;</font><font style='background-color: #D68C62; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D68C62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D68C62; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN_ORANGE = -0x1.130f44p126F;
    static { NAMED.put("bright brown orange", -0x1.130f44p126F); LIST.add(-0x1.130f44p126F); }

    /**
     * This color constant "deep pure orange" has RGBA8888 code {@code C3682AFF}, L 0.52156866, A 0.5372549, B 0.5529412, alpha 1.0, hue 0.15127131, saturation 0.8002163, and chroma 0.12896542.
     * It can be represented as a packed float with the constant {@code -0x1.1b130ap126F}.
     * <pre>
     * <font style='background-color: #C3682A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3682A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3682A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C3682A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C3682A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C3682A'>&nbsp;@&nbsp;</font><font style='background-color: #C3682A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3682A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3682A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_ORANGE = -0x1.1b130ap126F;
    static { NAMED.put("deep pure orange", -0x1.1b130ap126F); LIST.add(-0x1.1b130ap126F); }

    /**
     * This color constant "true pure orange" has RGBA8888 code {@code D7793AFF}, L 0.5882353, A 0.5372549, B 0.5529412, alpha 1.0, hue 0.15127131, saturation 0.7321128, and chroma 0.12896542.
     * It can be represented as a packed float with the constant {@code -0x1.1b132cp126F}.
     * <pre>
     * <font style='background-color: #D7793A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7793A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7793A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D7793A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D7793A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D7793A'>&nbsp;@&nbsp;</font><font style='background-color: #D7793A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7793A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7793A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_ORANGE = -0x1.1b132cp126F;
    static { NAMED.put("true pure orange", -0x1.1b132cp126F); LIST.add(-0x1.1b132cp126F); }

    /**
     * This color constant "bright pure orange" has RGBA8888 code {@code EC8A4AFF}, L 0.654902, A 0.5372549, B 0.5529412, alpha 1.0, hue 0.15127131, saturation 0.7321128, and chroma 0.12896542.
     * It can be represented as a packed float with the constant {@code -0x1.1b134ep126F}.
     * <pre>
     * <font style='background-color: #EC8A4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EC8A4A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EC8A4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EC8A4A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EC8A4A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EC8A4A'>&nbsp;@&nbsp;</font><font style='background-color: #EC8A4A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EC8A4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EC8A4A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_ORANGE = -0x1.1b134ep126F;
    static { NAMED.put("bright pure orange", -0x1.1b134ep126F); LIST.add(-0x1.1b134ep126F); }

    /**
     * This color constant "deep saffron orange" has RGBA8888 code {@code BF7530FF}, L 0.54509807, A 0.5254902, B 0.5529412, alpha 1.0, hue 0.17620972, saturation 0.7826238, and chroma 0.11705722.
     * It can be represented as a packed float with the constant {@code -0x1.1b0d16p126F}.
     * <pre>
     * <font style='background-color: #BF7530;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF7530; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF7530;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BF7530'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BF7530'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BF7530'>&nbsp;@&nbsp;</font><font style='background-color: #BF7530; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF7530;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF7530; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_SAFFRON_ORANGE = -0x1.1b0d16p126F;
    static { NAMED.put("deep saffron orange", -0x1.1b0d16p126F); LIST.add(-0x1.1b0d16p126F); }

    /**
     * This color constant "true saffron orange" has RGBA8888 code {@code D0843FFF}, L 0.6039216, A 0.5254902, B 0.5529412, alpha 1.0, hue 0.17620972, saturation 0.72802216, and chroma 0.11705722.
     * It can be represented as a packed float with the constant {@code -0x1.1b0d34p126F}.
     * <pre>
     * <font style='background-color: #D0843F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0843F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0843F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D0843F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D0843F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D0843F'>&nbsp;@&nbsp;</font><font style='background-color: #D0843F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0843F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0843F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_SAFFRON_ORANGE = -0x1.1b0d34p126F;
    static { NAMED.put("true saffron orange", -0x1.1b0d34p126F); LIST.add(-0x1.1b0d34p126F); }

    /**
     * This color constant "bright saffron orange" has RGBA8888 code {@code E6974FFF}, L 0.6784314, A 0.5254902, B 0.5529412, alpha 1.0, hue 0.17620972, saturation 0.68054247, and chroma 0.11705722.
     * It can be represented as a packed float with the constant {@code -0x1.1b0d5ap126F}.
     * <pre>
     * <font style='background-color: #E6974F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E6974F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E6974F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E6974F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E6974F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E6974F'>&nbsp;@&nbsp;</font><font style='background-color: #E6974F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E6974F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E6974F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_SAFFRON_ORANGE = -0x1.1b0d5ap126F;
    static { NAMED.put("bright saffron orange", -0x1.1b0d5ap126F); LIST.add(-0x1.1b0d5ap126F); }

    /**
     * This color constant "deep orange saffron" has RGBA8888 code {@code C87E25FF}, L 0.5764706, A 0.52156866, B 0.56078434, alpha 1.0, hue 0.19291253, saturation 0.85440034, and chroma 0.12849128.
     * It can be represented as a packed float with the constant {@code -0x1.1f0b26p126F}.
     * <pre>
     * <font style='background-color: #C87E25;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C87E25; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C87E25;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C87E25'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C87E25'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C87E25'>&nbsp;@&nbsp;</font><font style='background-color: #C87E25; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C87E25;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C87E25; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_SAFFRON = -0x1.1f0b26p126F;
    static { NAMED.put("deep orange saffron", -0x1.1f0b26p126F); LIST.add(-0x1.1f0b26p126F); }

    /**
     * This color constant "true orange saffron" has RGBA8888 code {@code DD9138FF}, L 0.6509804, A 0.52156866, B 0.56078434, alpha 1.0, hue 0.19291253, saturation 0.79479104, and chroma 0.12849128.
     * It can be represented as a packed float with the constant {@code -0x1.1f0b4cp126F}.
     * <pre>
     * <font style='background-color: #DD9138;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD9138; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD9138;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DD9138'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DD9138'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DD9138'>&nbsp;@&nbsp;</font><font style='background-color: #DD9138; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD9138;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD9138; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_ORANGE_SAFFRON = -0x1.1f0b4cp126F;
    static { NAMED.put("true orange saffron", -0x1.1f0b4cp126F); LIST.add(-0x1.1f0b4cp126F); }

    /**
     * This color constant "bright orange saffron" has RGBA8888 code {@code F0A148FF}, L 0.70980394, A 0.52156866, B 0.56078434, alpha 1.0, hue 0.19291253, saturation 0.759467, and chroma 0.12849128.
     * It can be represented as a packed float with the constant {@code -0x1.1f0b6ap126F}.
     * <pre>
     * <font style='background-color: #F0A148;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0A148; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0A148;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F0A148'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F0A148'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F0A148'>&nbsp;@&nbsp;</font><font style='background-color: #F0A148; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0A148;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0A148; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE_SAFFRON = -0x1.1f0b6ap126F;
    static { NAMED.put("bright orange saffron", -0x1.1f0b6ap126F); LIST.add(-0x1.1f0b6ap126F); }

    /**
     * This color constant "deep pure saffron" has RGBA8888 code {@code C28733FF}, L 0.5921569, A 0.5137255, B 0.5568628, alpha 1.0, hue 0.20852949, saturation 0.7961115, and chroma 0.11653464.
     * It can be represented as a packed float with the constant {@code -0x1.1d072ep126F}.
     * <pre>
     * <font style='background-color: #C28733;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C28733; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C28733;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C28733'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C28733'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C28733'>&nbsp;@&nbsp;</font><font style='background-color: #C28733; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C28733;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C28733; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_SAFFRON = -0x1.1d072ep126F;
    static { NAMED.put("deep pure saffron", -0x1.1d072ep126F); LIST.add(-0x1.1d072ep126F); }

    /**
     * This color constant "true pure saffron" has RGBA8888 code {@code D59843FF}, L 0.654902, A 0.5137255, B 0.5568628, alpha 1.0, hue 0.20852949, saturation 0.7392464, and chroma 0.11653464.
     * It can be represented as a packed float with the constant {@code -0x1.1d074ep126F}.
     * <pre>
     * <font style='background-color: #D59843;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D59843; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D59843;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D59843'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D59843'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D59843'>&nbsp;@&nbsp;</font><font style='background-color: #D59843; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D59843;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D59843; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_SAFFRON = -0x1.1d074ep126F;
    static { NAMED.put("true pure saffron", -0x1.1d074ep126F); LIST.add(-0x1.1d074ep126F); }

    /**
     * This color constant "bright pure saffron" has RGBA8888 code {@code EBAC55FF}, L 0.73333335, A 0.5137255, B 0.5568628, alpha 1.0, hue 0.20852949, saturation 0.68996334, and chroma 0.11653464.
     * It can be represented as a packed float with the constant {@code -0x1.1d0776p126F}.
     * <pre>
     * <font style='background-color: #EBAC55;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBAC55; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBAC55;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EBAC55'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EBAC55'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EBAC55'>&nbsp;@&nbsp;</font><font style='background-color: #EBAC55; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBAC55;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBAC55; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_SAFFRON = -0x1.1d0776p126F;
    static { NAMED.put("bright pure saffron", -0x1.1d0776p126F); LIST.add(-0x1.1d0776p126F); }

    /**
     * This color constant "deep yellow saffron" has RGBA8888 code {@code C49417FF}, L 0.61960787, A 0.5019608, B 0.5686275, alpha 1.0, hue 0.24117598, saturation 0.9245003, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.23013cp126F}.
     * <pre>
     * <font style='background-color: #C49417;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C49417; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C49417;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C49417'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C49417'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C49417'>&nbsp;@&nbsp;</font><font style='background-color: #C49417; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C49417;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C49417; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_SAFFRON = -0x1.23013cp126F;
    static { NAMED.put("deep yellow saffron", -0x1.23013cp126F); LIST.add(-0x1.23013cp126F); }

    /**
     * This color constant "true yellow saffron" has RGBA8888 code {@code D7A52EFF}, L 0.6862745, A 0.5019608, B 0.5686275, alpha 1.0, hue 0.24117598, saturation 0.85846454, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.23015ep126F}.
     * <pre>
     * <font style='background-color: #D7A52E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7A52E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7A52E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D7A52E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D7A52E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D7A52E'>&nbsp;@&nbsp;</font><font style='background-color: #D7A52E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7A52E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7A52E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_YELLOW_SAFFRON = -0x1.23015ep126F;
    static { NAMED.put("true yellow saffron", -0x1.23015ep126F); LIST.add(-0x1.23015ep126F); }

    /**
     * This color constant "bright yellow saffron" has RGBA8888 code {@code F0BC45FF}, L 0.77254903, A 0.5019608, B 0.5686275, alpha 1.0, hue 0.24117598, saturation 0.78381544, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.23018ap126F}.
     * <pre>
     * <font style='background-color: #F0BC45;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0BC45; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0BC45;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F0BC45'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F0BC45'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F0BC45'>&nbsp;@&nbsp;</font><font style='background-color: #F0BC45; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0BC45;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0BC45; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_YELLOW_SAFFRON = -0x1.23018ap126F;
    static { NAMED.put("bright yellow saffron", -0x1.23018ap126F); LIST.add(-0x1.23018ap126F); }

    /**
     * This color constant "deep saffron yellow" has RGBA8888 code {@code C4AF3BFF}, L 0.6901961, A 0.4862745, B 0.5647059, alpha 1.0, hue 0.27781066, saturation 0.80291516, and chroma 0.13177444.
     * It can be represented as a packed float with the constant {@code -0x1.20f96p126F}.
     * <pre>
     * <font style='background-color: #C4AF3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4AF3B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4AF3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C4AF3B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C4AF3B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C4AF3B'>&nbsp;@&nbsp;</font><font style='background-color: #C4AF3B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4AF3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4AF3B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_SAFFRON_YELLOW = -0x1.20f96p126F;
    static { NAMED.put("deep saffron yellow", -0x1.20f96p126F); LIST.add(-0x1.20f96p126F); }

    /**
     * This color constant "true saffron yellow" has RGBA8888 code {@code D7C24CFF}, L 0.7607843, A 0.4862745, B 0.5647059, alpha 1.0, hue 0.27781066, saturation 0.75055116, and chroma 0.13177444.
     * It can be represented as a packed float with the constant {@code -0x1.20f984p126F}.
     * <pre>
     * <font style='background-color: #D7C24C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7C24C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7C24C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D7C24C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D7C24C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D7C24C'>&nbsp;@&nbsp;</font><font style='background-color: #D7C24C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7C24C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7C24C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_SAFFRON_YELLOW = -0x1.20f984p126F;
    static { NAMED.put("true saffron yellow", -0x1.20f984p126F); LIST.add(-0x1.20f984p126F); }

    /**
     * This color constant "bright saffron yellow" has RGBA8888 code {@code EAD55EFF}, L 0.827451, A 0.4862745, B 0.5647059, alpha 1.0, hue 0.27781066, saturation 0.704599, and chroma 0.13177444.
     * It can be represented as a packed float with the constant {@code -0x1.20f9a6p126F}.
     * <pre>
     * <font style='background-color: #EAD55E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EAD55E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EAD55E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EAD55E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EAD55E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EAD55E'>&nbsp;@&nbsp;</font><font style='background-color: #EAD55E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EAD55E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EAD55E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_SAFFRON_YELLOW = -0x1.20f9a6p126F;
    static { NAMED.put("bright saffron yellow", -0x1.20f9a6p126F); LIST.add(-0x1.20f9a6p126F); }

    /**
     * This color constant "deep pure yellow" has RGBA8888 code {@code C1C01FFF}, L 0.7294118, A 0.47058824, B 0.5764706, alpha 1.0, hue 0.3035705, saturation 0.9212879, and chroma 0.1632233.
     * It can be represented as a packed float with the constant {@code -0x1.26f174p126F}.
     * <pre>
     * <font style='background-color: #C1C01F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C1C01F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C1C01F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C1C01F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C1C01F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C1C01F'>&nbsp;@&nbsp;</font><font style='background-color: #C1C01F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C1C01F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C1C01F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_YELLOW = -0x1.26f174p126F;
    static { NAMED.put("deep pure yellow", -0x1.26f174p126F); LIST.add(-0x1.26f174p126F); }

    /**
     * This color constant "true pure yellow" has RGBA8888 code {@code D3D337FF}, L 0.79607844, A 0.47058824, B 0.5764706, alpha 1.0, hue 0.3035705, saturation 0.86488247, and chroma 0.1632233.
     * It can be represented as a packed float with the constant {@code -0x1.26f196p126F}.
     * <pre>
     * <font style='background-color: #D3D337;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3D337; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3D337;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D3D337'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D3D337'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D3D337'>&nbsp;@&nbsp;</font><font style='background-color: #D3D337; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3D337;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3D337; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_YELLOW = -0x1.26f196p126F;
    static { NAMED.put("true pure yellow", -0x1.26f196p126F); LIST.add(-0x1.26f196p126F); }

    /**
     * This color constant "bright pure yellow" has RGBA8888 code {@code EAEA4EFF}, L 0.8745098, A 0.47058824, B 0.5764706, alpha 1.0, hue 0.3035705, saturation 0.79960835, and chroma 0.1632233.
     * It can be represented as a packed float with the constant {@code -0x1.26f1bep126F}.
     * <pre>
     * <font style='background-color: #EAEA4E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EAEA4E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EAEA4E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EAEA4E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EAEA4E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EAEA4E'>&nbsp;@&nbsp;</font><font style='background-color: #EAEA4E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EAEA4E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EAEA4E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_YELLOW = -0x1.26f1bep126F;
    static { NAMED.put("bright pure yellow", -0x1.26f1bep126F); LIST.add(-0x1.26f1bep126F); }

    /**
     * This color constant "deep lime yellow" has RGBA8888 code {@code AEBA32FF}, L 0.69411767, A 0.46666667, B 0.5686275, alpha 1.0, hue 0.31655478, saturation 0.8382007, and chroma 0.15199278.
     * It can be represented as a packed float with the constant {@code -0x1.22ef62p126F}.
     * <pre>
     * <font style='background-color: #AEBA32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AEBA32; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AEBA32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AEBA32'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AEBA32'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AEBA32'>&nbsp;@&nbsp;</font><font style='background-color: #AEBA32; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AEBA32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AEBA32; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_LIME_YELLOW = -0x1.22ef62p126F;
    static { NAMED.put("deep lime yellow", -0x1.22ef62p126F); LIST.add(-0x1.22ef62p126F); }

    /**
     * This color constant "true lime yellow" has RGBA8888 code {@code C1CE46FF}, L 0.76862746, A 0.46666667, B 0.5686275, alpha 1.0, hue 0.31655478, saturation 0.7879086, and chroma 0.15199278.
     * It can be represented as a packed float with the constant {@code -0x1.22ef88p126F}.
     * <pre>
     * <font style='background-color: #C1CE46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C1CE46; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C1CE46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C1CE46'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C1CE46'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C1CE46'>&nbsp;@&nbsp;</font><font style='background-color: #C1CE46; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C1CE46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C1CE46; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_LIME_YELLOW = -0x1.22ef88p126F;
    static { NAMED.put("true lime yellow", -0x1.22ef88p126F); LIST.add(-0x1.22ef88p126F); }

    /**
     * This color constant "bright lime yellow" has RGBA8888 code {@code D9E75DFF}, L 0.85490197, A 0.46666667, B 0.5686275, alpha 1.0, hue 0.31655478, saturation 0.72954506, and chroma 0.15199278.
     * It can be represented as a packed float with the constant {@code -0x1.22efb4p126F}.
     * <pre>
     * <font style='background-color: #D9E75D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9E75D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9E75D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D9E75D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D9E75D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D9E75D'>&nbsp;@&nbsp;</font><font style='background-color: #D9E75D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9E75D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9E75D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_LIME_YELLOW = -0x1.22efb4p126F;
    static { NAMED.put("bright lime yellow", -0x1.22efb4p126F); LIST.add(-0x1.22efb4p126F); }

    /**
     * This color constant "deep yellow lime" has RGBA8888 code {@code 9EC429FF}, L 0.7058824, A 0.4509804, B 0.57254905, alpha 1.0, hue 0.33966506, saturation 0.8812629, and chroma 0.17443058.
     * It can be represented as a packed float with the constant {@code -0x1.24e768p126F}.
     * <pre>
     * <font style='background-color: #9EC429;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9EC429; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9EC429;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9EC429'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9EC429'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9EC429'>&nbsp;@&nbsp;</font><font style='background-color: #9EC429; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9EC429;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9EC429; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_LIME = -0x1.24e768p126F;
    static { NAMED.put("deep yellow lime", -0x1.24e768p126F); LIST.add(-0x1.24e768p126F); }

    /**
     * This color constant "true yellow lime" has RGBA8888 code {@code AFD73EFF}, L 0.77254903, A 0.4509804, B 0.57254905, alpha 1.0, hue 0.33966506, saturation 0.8323039, and chroma 0.17443058.
     * It can be represented as a packed float with the constant {@code -0x1.24e78ap126F}.
     * <pre>
     * <font style='background-color: #AFD73E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AFD73E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AFD73E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AFD73E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AFD73E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AFD73E'>&nbsp;@&nbsp;</font><font style='background-color: #AFD73E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AFD73E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AFD73E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_YELLOW_LIME = -0x1.24e78ap126F;
    static { NAMED.put("true yellow lime", -0x1.24e78ap126F); LIST.add(-0x1.24e78ap126F); }

    /**
     * This color constant "bright yellow lime" has RGBA8888 code {@code C2EC52FF}, L 0.84705883, A 0.4509804, B 0.57254905, alpha 1.0, hue 0.33966506, saturation 0.7749036, and chroma 0.17443058.
     * It can be represented as a packed float with the constant {@code -0x1.24e7bp126F}.
     * <pre>
     * <font style='background-color: #C2EC52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C2EC52; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C2EC52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C2EC52'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C2EC52'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C2EC52'>&nbsp;@&nbsp;</font><font style='background-color: #C2EC52; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C2EC52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C2EC52; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_YELLOW_LIME = -0x1.24e7bp126F;
    static { NAMED.put("bright yellow lime", -0x1.24e7bp126F); LIST.add(-0x1.24e7bp126F); }

    /**
     * This color constant "deep pure lime" has RGBA8888 code {@code 8DC13BFF}, L 0.6862745, A 0.44705883, B 0.5647059, alpha 1.0, hue 0.35391617, saturation 0.80758244, and chroma 0.16655473.
     * It can be represented as a packed float with the constant {@code -0x1.20e55ep126F}.
     * <pre>
     * <font style='background-color: #8DC13B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8DC13B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8DC13B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8DC13B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8DC13B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8DC13B'>&nbsp;@&nbsp;</font><font style='background-color: #8DC13B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8DC13B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8DC13B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_LIME = -0x1.20e55ep126F;
    static { NAMED.put("deep pure lime", -0x1.20e55ep126F); LIST.add(-0x1.20e55ep126F); }

    /**
     * This color constant "true pure lime" has RGBA8888 code {@code 9DD34CFF}, L 0.7490196, A 0.44705883, B 0.5647059, alpha 1.0, hue 0.35391617, saturation 0.75091, and chroma 0.16655473.
     * It can be represented as a packed float with the constant {@code -0x1.20e57ep126F}.
     * <pre>
     * <font style='background-color: #9DD34C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9DD34C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9DD34C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9DD34C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9DD34C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9DD34C'>&nbsp;@&nbsp;</font><font style='background-color: #9DD34C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9DD34C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9DD34C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_LIME = -0x1.20e57ep126F;
    static { NAMED.put("true pure lime", -0x1.20e57ep126F); LIST.add(-0x1.20e57ep126F); }

    /**
     * This color constant "bright pure lime" has RGBA8888 code {@code B2EA60FF}, L 0.827451, A 0.44705883, B 0.5647059, alpha 1.0, hue 0.35391617, saturation 0.70167, and chroma 0.16655473.
     * It can be represented as a packed float with the constant {@code -0x1.20e5a6p126F}.
     * <pre>
     * <font style='background-color: #B2EA60;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2EA60; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2EA60;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B2EA60'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B2EA60'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B2EA60'>&nbsp;@&nbsp;</font><font style='background-color: #B2EA60; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2EA60;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2EA60; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_LIME = -0x1.20e5a6p126F;
    static { NAMED.put("bright pure lime", -0x1.20e5a6p126F); LIST.add(-0x1.20e5a6p126F); }

    /**
     * This color constant "deep green lime" has RGBA8888 code {@code 78C622FF}, L 0.68235296, A 0.43137255, B 0.57254905, alpha 1.0, hue 0.36617374, saturation 0.9105392, and chroma 0.1989505.
     * It can be represented as a packed float with the constant {@code -0x1.24dd5cp126F}.
     * <pre>
     * <font style='background-color: #78C622;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #78C622; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #78C622;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #78C622'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #78C622'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #78C622'>&nbsp;@&nbsp;</font><font style='background-color: #78C622; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #78C622;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #78C622; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREEN_LIME = -0x1.24dd5cp126F;
    static { NAMED.put("deep green lime", -0x1.24dd5cp126F); LIST.add(-0x1.24dd5cp126F); }

    /**
     * This color constant "true green lime" has RGBA8888 code {@code 8ADB39FF}, L 0.75686276, A 0.43137255, B 0.57254905, alpha 1.0, hue 0.36617374, saturation 0.83590484, and chroma 0.1989505.
     * It can be represented as a packed float with the constant {@code -0x1.24dd82p126F}.
     * <pre>
     * <font style='background-color: #8ADB39;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8ADB39; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8ADB39;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8ADB39'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8ADB39'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8ADB39'>&nbsp;@&nbsp;</font><font style='background-color: #8ADB39; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8ADB39;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8ADB39; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_GREEN_LIME = -0x1.24dd82p126F;
    static { NAMED.put("true green lime", -0x1.24dd82p126F); LIST.add(-0x1.24dd82p126F); }

    /**
     * This color constant "bright green lime" has RGBA8888 code {@code 99EC4AFF}, L 0.8117647, A 0.43137255, B 0.57254905, alpha 1.0, hue 0.36617374, saturation 0.7967218, and chroma 0.1989505.
     * It can be represented as a packed float with the constant {@code -0x1.24dd9ep126F}.
     * <pre>
     * <font style='background-color: #99EC4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #99EC4A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #99EC4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #99EC4A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #99EC4A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #99EC4A'>&nbsp;@&nbsp;</font><font style='background-color: #99EC4A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #99EC4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #99EC4A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_GREEN_LIME = -0x1.24dd9ep126F;
    static { NAMED.put("bright green lime", -0x1.24dd9ep126F); LIST.add(-0x1.24dd9ep126F); }

    /**
     * This color constant "deep lime green" has RGBA8888 code {@code 56C33EFF}, L 0.654902, A 0.42352942, B 0.56078434, alpha 1.0, hue 0.38859904, saturation 0.7762339, and chroma 0.1946081.
     * It can be represented as a packed float with the constant {@code -0x1.1ed94ep126F}.
     * <pre>
     * <font style='background-color: #56C33E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #56C33E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #56C33E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #56C33E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #56C33E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #56C33E'>&nbsp;@&nbsp;</font><font style='background-color: #56C33E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #56C33E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #56C33E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_LIME_GREEN = -0x1.1ed94ep126F;
    static { NAMED.put("deep lime green", -0x1.1ed94ep126F); LIST.add(-0x1.1ed94ep126F); }

    /**
     * This color constant "true lime green" has RGBA8888 code {@code 66D54EFF}, L 0.7176471, A 0.42352942, B 0.56078434, alpha 1.0, hue 0.38859904, saturation 0.7305731, and chroma 0.1946081.
     * It can be represented as a packed float with the constant {@code -0x1.1ed96ep126F}.
     * <pre>
     * <font style='background-color: #66D54E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #66D54E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #66D54E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #66D54E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #66D54E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #66D54E'>&nbsp;@&nbsp;</font><font style='background-color: #66D54E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #66D54E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #66D54E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_LIME_GREEN = -0x1.1ed96ep126F;
    static { NAMED.put("true lime green", -0x1.1ed96ep126F); LIST.add(-0x1.1ed96ep126F); }

    /**
     * This color constant "bright lime green" has RGBA8888 code {@code 7AEB61FF}, L 0.79607844, A 0.42352942, B 0.56078434, alpha 1.0, hue 0.38859904, saturation 0.6805338, and chroma 0.1946081.
     * It can be represented as a packed float with the constant {@code -0x1.1ed996p126F}.
     * <pre>
     * <font style='background-color: #7AEB61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7AEB61; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7AEB61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7AEB61'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7AEB61'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7AEB61'>&nbsp;@&nbsp;</font><font style='background-color: #7AEB61; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7AEB61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7AEB61; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_LIME_GREEN = -0x1.1ed996p126F;
    static { NAMED.put("bright lime green", -0x1.1ed996p126F); LIST.add(-0x1.1ed996p126F); }

    /**
     * This color constant "deep pure green" has RGBA8888 code {@code 21C02CFF}, L 0.627451, A 0.40784314, B 0.5647059, alpha 1.0, hue 0.39868373, saturation 0.8937718, and chroma 0.22432896.
     * It can be represented as a packed float with the constant {@code -0x1.20d14p126F}.
     * <pre>
     * <font style='background-color: #21C02C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #21C02C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #21C02C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #21C02C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #21C02C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #21C02C'>&nbsp;@&nbsp;</font><font style='background-color: #21C02C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #21C02C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #21C02C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_GREEN = -0x1.20d14p126F;
    static { NAMED.put("deep pure green", -0x1.20d14p126F); LIST.add(-0x1.20d14p126F); }

    /**
     * This color constant "true pure green" has RGBA8888 code {@code 39D33EFF}, L 0.6901961, A 0.40784314, B 0.5647059, alpha 1.0, hue 0.39868373, saturation 0.841197, and chroma 0.22432896.
     * It can be represented as a packed float with the constant {@code -0x1.20d16p126F}.
     * <pre>
     * <font style='background-color: #39D33E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #39D33E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #39D33E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #39D33E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #39D33E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #39D33E'>&nbsp;@&nbsp;</font><font style='background-color: #39D33E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #39D33E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #39D33E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_GREEN = -0x1.20d16p126F;
    static { NAMED.put("true pure green", -0x1.20d16p126F); LIST.add(-0x1.20d16p126F); }

    /**
     * This color constant "bright pure green" has RGBA8888 code {@code 51EC54FF}, L 0.7764706, A 0.40784314, B 0.5647059, alpha 1.0, hue 0.39868373, saturation 0.77299184, and chroma 0.22432896.
     * It can be represented as a packed float with the constant {@code -0x1.20d18cp126F}.
     * <pre>
     * <font style='background-color: #51EC54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #51EC54; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #51EC54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #51EC54'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #51EC54'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #51EC54'>&nbsp;@&nbsp;</font><font style='background-color: #51EC54; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #51EC54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #51EC54; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_GREEN = -0x1.20d18cp126F;
    static { NAMED.put("bright pure green", -0x1.20d18cp126F); LIST.add(-0x1.20d18cp126F); }

    /**
     * This color constant "deep cyan green" has RGBA8888 code {@code 3EC285FF}, L 0.65882355, A 0.43137255, B 0.52156866, alpha 1.0, hue 0.44601285, saturation 0.78381544, and chroma 0.143312.
     * It can be represented as a packed float with the constant {@code -0x1.0add5p126F}.
     * <pre>
     * <font style='background-color: #3EC285;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3EC285; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3EC285;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3EC285'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3EC285'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3EC285'>&nbsp;@&nbsp;</font><font style='background-color: #3EC285; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3EC285;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3EC285; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_CYAN_GREEN = -0x1.0add5p126F;
    static { NAMED.put("deep cyan green", -0x1.0add5p126F); LIST.add(-0x1.0add5p126F); }

    /**
     * This color constant "true cyan green" has RGBA8888 code {@code 52D696FF}, L 0.7294118, A 0.43137255, B 0.52156866, alpha 1.0, hue 0.44601285, saturation 0.7358268, and chroma 0.143312.
     * It can be represented as a packed float with the constant {@code -0x1.0add74p126F}.
     * <pre>
     * <font style='background-color: #52D696;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #52D696; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #52D696;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #52D696'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #52D696'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #52D696'>&nbsp;@&nbsp;</font><font style='background-color: #52D696; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #52D696;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #52D696; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_CYAN_GREEN = -0x1.0add74p126F;
    static { NAMED.put("true cyan green", -0x1.0add74p126F); LIST.add(-0x1.0add74p126F); }

    /**
     * This color constant "bright cyan green" has RGBA8888 code {@code 64E9A8FF}, L 0.7921569, A 0.43137255, B 0.52156866, alpha 1.0, hue 0.44601285, saturation 0.69337523, and chroma 0.143312.
     * It can be represented as a packed float with the constant {@code -0x1.0add94p126F}.
     * <pre>
     * <font style='background-color: #64E9A8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #64E9A8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #64E9A8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #64E9A8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #64E9A8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #64E9A8'>&nbsp;@&nbsp;</font><font style='background-color: #64E9A8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #64E9A8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #64E9A8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_CYAN_GREEN = -0x1.0add94p126F;
    static { NAMED.put("bright cyan green", -0x1.0add94p126F); LIST.add(-0x1.0add94p126F); }

    /**
     * This color constant "deep green cyan" has RGBA8888 code {@code 1AC0A1FF}, L 0.6509804, A 0.43137255, B 0.5019608, alpha 1.0, hue 0.49065647, saturation 0.8962835, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.00dd4cp126F}.
     * <pre>
     * <font style='background-color: #1AC0A1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1AC0A1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1AC0A1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1AC0A1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1AC0A1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1AC0A1'>&nbsp;@&nbsp;</font><font style='background-color: #1AC0A1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1AC0A1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1AC0A1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREEN_CYAN = -0x1.00dd4cp126F;
    static { NAMED.put("deep green cyan", -0x1.00dd4cp126F); LIST.add(-0x1.00dd4cp126F); }

    /**
     * This color constant "true green cyan" has RGBA8888 code {@code 36D3B3FF}, L 0.7176471, A 0.43137255, B 0.5019608, alpha 1.0, hue 0.49065647, saturation 0.85146934, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.00dd6ep126F}.
     * <pre>
     * <font style='background-color: #36D3B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #36D3B3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #36D3B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #36D3B3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #36D3B3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #36D3B3'>&nbsp;@&nbsp;</font><font style='background-color: #36D3B3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #36D3B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #36D3B3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_GREEN_CYAN = -0x1.00dd6ep126F;
    static { NAMED.put("true green cyan", -0x1.00dd6ep126F); LIST.add(-0x1.00dd6ep126F); }

    /**
     * This color constant "bright green cyan" has RGBA8888 code {@code 4FEAC8FF}, L 0.79607844, A 0.43137255, B 0.5019608, alpha 1.0, hue 0.49065647, saturation 0.7920645, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.00dd96p126F}.
     * <pre>
     * <font style='background-color: #4FEAC8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4FEAC8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4FEAC8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4FEAC8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4FEAC8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4FEAC8'>&nbsp;@&nbsp;</font><font style='background-color: #4FEAC8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4FEAC8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4FEAC8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_GREEN_CYAN = -0x1.00dd96p126F;
    static { NAMED.put("bright green cyan", -0x1.00dd96p126F); LIST.add(-0x1.00dd96p126F); }

    /**
     * This color constant "deep pure cyan" has RGBA8888 code {@code 39C3C4FF}, L 0.68235296, A 0.44313726, B 0.48235294, alpha 1.0, hue 0.54428434, saturation 0.83201253, and chroma 0.11861114.
     * It can be represented as a packed float with the constant {@code -0x1.f6e35cp125F}.
     * <pre>
     * <font style='background-color: #39C3C4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #39C3C4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #39C3C4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #39C3C4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #39C3C4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #39C3C4'>&nbsp;@&nbsp;</font><font style='background-color: #39C3C4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #39C3C4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #39C3C4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_CYAN = -0x1.f6e35cp125F;
    static { NAMED.put("deep pure cyan", -0x1.f6e35cp125F); LIST.add(-0x1.f6e35cp125F); }

    /**
     * This color constant "true pure cyan" has RGBA8888 code {@code 4FD8D9FF}, L 0.7529412, A 0.44313726, B 0.48235294, alpha 1.0, hue 0.54428434, saturation 0.7663274, and chroma 0.11861114.
     * It can be represented as a packed float with the constant {@code -0x1.f6e38p125F}.
     * <pre>
     * <font style='background-color: #4FD8D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4FD8D9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4FD8D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4FD8D9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4FD8D9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4FD8D9'>&nbsp;@&nbsp;</font><font style='background-color: #4FD8D9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4FD8D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4FD8D9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_CYAN = -0x1.f6e38p125F;
    static { NAMED.put("true pure cyan", -0x1.f6e38p125F); LIST.add(-0x1.f6e38p125F); }

    /**
     * This color constant "bright pure cyan" has RGBA8888 code {@code 62ECEDFF}, L 0.8235294, A 0.44313726, B 0.48235294, alpha 1.0, hue 0.54428434, saturation 0.728011, and chroma 0.11861114.
     * It can be represented as a packed float with the constant {@code -0x1.f6e3a4p125F}.
     * <pre>
     * <font style='background-color: #62ECED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #62ECED; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #62ECED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #62ECED'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #62ECED'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #62ECED'>&nbsp;@&nbsp;</font><font style='background-color: #62ECED; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #62ECED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #62ECED; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_CYAN = -0x1.f6e3a4p125F;
    static { NAMED.put("bright pure cyan", -0x1.f6e3a4p125F); LIST.add(-0x1.f6e3a4p125F); }

    /**
     * This color constant "deep blue cyan" has RGBA8888 code {@code 22A8C9FF}, L 0.6, A 0.4509804, B 0.4627451, alpha 1.0, hue 0.6024291, saturation 0.88235295, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.ece732p125F}.
     * <pre>
     * <font style='background-color: #22A8C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #22A8C9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #22A8C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #22A8C9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #22A8C9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #22A8C9'>&nbsp;@&nbsp;</font><font style='background-color: #22A8C9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #22A8C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #22A8C9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE_CYAN = -0x1.ece732p125F;
    static { NAMED.put("deep blue cyan", -0x1.ece732p125F); LIST.add(-0x1.ece732p125F); }

    /**
     * This color constant "true blue cyan" has RGBA8888 code {@code 38BBDCFF}, L 0.6666667, A 0.4509804, B 0.4627451, alpha 1.0, hue 0.6024291, saturation 0.8333333, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.ece754p125F}.
     * <pre>
     * <font style='background-color: #38BBDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #38BBDC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #38BBDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #38BBDC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #38BBDC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #38BBDC'>&nbsp;@&nbsp;</font><font style='background-color: #38BBDC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #38BBDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #38BBDC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BLUE_CYAN = -0x1.ece754p125F;
    static { NAMED.put("true blue cyan", -0x1.ece754p125F); LIST.add(-0x1.ece754p125F); }

    /**
     * This color constant "bright blue cyan" has RGBA8888 code {@code 4CCEF0FF}, L 0.7372549, A 0.4509804, B 0.4627451, alpha 1.0, hue 0.6024291, saturation 0.7692308, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.ece778p125F}.
     * <pre>
     * <font style='background-color: #4CCEF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4CCEF0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4CCEF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4CCEF0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4CCEF0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4CCEF0'>&nbsp;@&nbsp;</font><font style='background-color: #4CCEF0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4CCEF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4CCEF0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE_CYAN = -0x1.ece778p125F;
    static { NAMED.put("bright blue cyan", -0x1.ece778p125F); LIST.add(-0x1.ece778p125F); }

    /**
     * This color constant "deep cyan blue" has RGBA8888 code {@code 3080BAFF}, L 0.48235294, A 0.47058824, B 0.44705883, alpha 1.0, hue 0.67138404, saturation 0.777096, and chroma 0.120651916.
     * It can be represented as a packed float with the constant {@code -0x1.e4f0f6p125F}.
     * <pre>
     * <font style='background-color: #3080BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3080BA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3080BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3080BA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3080BA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3080BA'>&nbsp;@&nbsp;</font><font style='background-color: #3080BA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3080BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3080BA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_CYAN_BLUE = -0x1.e4f0f6p125F;
    static { NAMED.put("deep cyan blue", -0x1.e4f0f6p125F); LIST.add(-0x1.e4f0f6p125F); }

    /**
     * This color constant "true cyan blue" has RGBA8888 code {@code 4292CFFF}, L 0.5529412, A 0.47058824, B 0.44705883, alpha 1.0, hue 0.67138404, saturation 0.7030868, and chroma 0.120651916.
     * It can be represented as a packed float with the constant {@code -0x1.e4f11ap125F}.
     * <pre>
     * <font style='background-color: #4292CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4292CF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4292CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4292CF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4292CF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4292CF'>&nbsp;@&nbsp;</font><font style='background-color: #4292CF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4292CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4292CF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_CYAN_BLUE = -0x1.e4f11ap125F;
    static { NAMED.put("true cyan blue", -0x1.e4f11ap125F); LIST.add(-0x1.e4f11ap125F); }

    /**
     * This color constant "bright cyan blue" has RGBA8888 code {@code 56A8E8FF}, L 0.63529414, A 0.47058824, B 0.44705883, alpha 1.0, hue 0.67138404, saturation 0.7030868, and chroma 0.120651916.
     * It can be represented as a packed float with the constant {@code -0x1.e4f144p125F}.
     * <pre>
     * <font style='background-color: #56A8E8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #56A8E8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #56A8E8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #56A8E8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #56A8E8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #56A8E8'>&nbsp;@&nbsp;</font><font style='background-color: #56A8E8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #56A8E8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #56A8E8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_CYAN_BLUE = -0x1.e4f144p125F;
    static { NAMED.put("bright cyan blue", -0x1.e4f144p125F); LIST.add(-0x1.e4f144p125F); }

    /**
     * This color constant "deep pure blue" has RGBA8888 code {@code 0C2ED1FF}, L 0.2901961, A 0.4862745, B 0.3764706, alpha 1.0, hue 0.7346399, saturation 0.79858524, and chroma 0.24760818.
     * It can be represented as a packed float with the constant {@code -0x1.c0f894p125F}.
     * <pre>
     * <font style='background-color: #0C2ED1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0C2ED1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0C2ED1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0C2ED1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0C2ED1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0C2ED1'>&nbsp;@&nbsp;</font><font style='background-color: #0C2ED1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0C2ED1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0C2ED1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_BLUE = -0x1.c0f894p125F;
    static { NAMED.put("deep pure blue", -0x1.c0f894p125F); LIST.add(-0x1.c0f894p125F); }

    /**
     * This color constant "true pure blue" has RGBA8888 code {@code 1540E6FF}, L 0.34901962, A 0.4862745, B 0.3764706, alpha 1.0, hue 0.7346399, saturation 0.8196006, and chroma 0.24760818.
     * It can be represented as a packed float with the constant {@code -0x1.c0f8b2p125F}.
     * <pre>
     * <font style='background-color: #1540E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1540E6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1540E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1540E6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1540E6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1540E6'>&nbsp;@&nbsp;</font><font style='background-color: #1540E6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1540E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1540E6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_BLUE = -0x1.c0f8b2p125F;
    static { NAMED.put("true pure blue", -0x1.c0f8b2p125F); LIST.add(-0x1.c0f8b2p125F); }

    /**
     * This color constant "bright pure blue" has RGBA8888 code {@code 1F4FF8FF}, L 0.4, A 0.4862745, B 0.3764706, alpha 1.0, hue 0.7346399, saturation 0.9160242, and chroma 0.24760818.
     * It can be represented as a packed float with the constant {@code -0x1.c0f8ccp125F}.
     * <pre>
     * <font style='background-color: #1F4FF8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1F4FF8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1F4FF8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1F4FF8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1F4FF8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1F4FF8'>&nbsp;@&nbsp;</font><font style='background-color: #1F4FF8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1F4FF8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1F4FF8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_BLUE = -0x1.c0f8ccp125F;
    static { NAMED.put("bright pure blue", -0x1.c0f8ccp125F); LIST.add(-0x1.c0f8ccp125F); }

    /**
     * This color constant "deep violet blue" has RGBA8888 code {@code 3932BBFF}, L 0.29803923, A 0.50980395, B 0.39607844, alpha 1.0, hue 0.7682935, saturation 0.7170549, and chroma 0.20795049.
     * It can be represented as a packed float with the constant {@code -0x1.cb0498p125F}.
     * <pre>
     * <font style='background-color: #3932BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3932BB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3932BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3932BB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3932BB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3932BB'>&nbsp;@&nbsp;</font><font style='background-color: #3932BB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3932BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3932BB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET_BLUE = -0x1.cb0498p125F;
    static { NAMED.put("deep violet blue", -0x1.cb0498p125F); LIST.add(-0x1.cb0498p125F); }

    /**
     * This color constant "true violet blue" has RGBA8888 code {@code 4341CEFF}, L 0.3529412, A 0.50980395, B 0.39607844, alpha 1.0, hue 0.7682935, saturation 0.6887501, and chroma 0.20795049.
     * It can be represented as a packed float with the constant {@code -0x1.cb04b4p125F}.
     * <pre>
     * <font style='background-color: #4341CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4341CE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4341CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4341CE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4341CE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4341CE'>&nbsp;@&nbsp;</font><font style='background-color: #4341CE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4341CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4341CE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_VIOLET_BLUE = -0x1.cb04b4p125F;
    static { NAMED.put("true violet blue", -0x1.cb04b4p125F); LIST.add(-0x1.cb04b4p125F); }

    /**
     * This color constant "bright violet blue" has RGBA8888 code {@code 5051E3FF}, L 0.41568628, A 0.50980395, B 0.39607844, alpha 1.0, hue 0.7682935, saturation 0.7812688, and chroma 0.20795049.
     * It can be represented as a packed float with the constant {@code -0x1.cb04d4p125F}.
     * <pre>
     * <font style='background-color: #5051E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5051E3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5051E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5051E3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5051E3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5051E3'>&nbsp;@&nbsp;</font><font style='background-color: #5051E3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5051E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5051E3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_VIOLET_BLUE = -0x1.cb04d4p125F;
    static { NAMED.put("bright violet blue", -0x1.cb04d4p125F); LIST.add(-0x1.cb04d4p125F); }

    /**
     * This color constant "deep blue violet" has RGBA8888 code {@code 5721C9FF}, L 0.31764707, A 0.5372549, B 0.3882353, alpha 1.0, hue 0.804581, saturation 0.8618011, and chroma 0.23470029.
     * It can be represented as a packed float with the constant {@code -0x1.c712a2p125F}.
     * <pre>
     * <font style='background-color: #5721C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5721C9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5721C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5721C9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5721C9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5721C9'>&nbsp;@&nbsp;</font><font style='background-color: #5721C9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5721C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5721C9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE_VIOLET = -0x1.c712a2p125F;
    static { NAMED.put("deep blue violet", -0x1.c712a2p125F); LIST.add(-0x1.c712a2p125F); }

    /**
     * This color constant "true blue violet" has RGBA8888 code {@code 6434DDFF}, L 0.3764706, A 0.5372549, B 0.3882353, alpha 1.0, hue 0.804581, saturation 0.7722633, and chroma 0.23470029.
     * It can be represented as a packed float with the constant {@code -0x1.c712cp125F}.
     * <pre>
     * <font style='background-color: #6434DD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6434DD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6434DD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6434DD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6434DD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6434DD'>&nbsp;@&nbsp;</font><font style='background-color: #6434DD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6434DD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6434DD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BLUE_VIOLET = -0x1.c712cp125F;
    static { NAMED.put("true blue violet", -0x1.c712cp125F); LIST.add(-0x1.c712cp125F); }

    /**
     * This color constant "bright blue violet" has RGBA8888 code {@code 7447F5FF}, L 0.44705883, A 0.5372549, B 0.3882353, alpha 1.0, hue 0.804581, saturation 0.90097386, and chroma 0.23470029.
     * It can be represented as a packed float with the constant {@code -0x1.c712e4p125F}.
     * <pre>
     * <font style='background-color: #7447F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7447F5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7447F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7447F5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7447F5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7447F5'>&nbsp;@&nbsp;</font><font style='background-color: #7447F5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7447F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7447F5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE_VIOLET = -0x1.c712e4p125F;
    static { NAMED.put("bright blue violet", -0x1.c712e4p125F); LIST.add(-0x1.c712e4p125F); }

    /**
     * This color constant "deep pure violet" has RGBA8888 code {@code 7533BDFF}, L 0.36862746, A 0.5529412, B 0.4117647, alpha 1.0, hue 0.84020853, saturation 0.7450517, and chroma 0.2049944.
     * It can be represented as a packed float with the constant {@code -0x1.d31abcp125F}.
     * <pre>
     * <font style='background-color: #7533BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7533BD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7533BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7533BD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7533BD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7533BD'>&nbsp;@&nbsp;</font><font style='background-color: #7533BD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7533BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7533BD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_VIOLET = -0x1.d31abcp125F;
    static { NAMED.put("deep pure violet", -0x1.d31abcp125F); LIST.add(-0x1.d31abcp125F); }

    /**
     * This color constant "true pure violet" has RGBA8888 code {@code 8543D1FF}, L 0.43137255, A 0.5529412, B 0.4117647, alpha 1.0, hue 0.84020853, saturation 0.6773197, and chroma 0.2049944.
     * It can be represented as a packed float with the constant {@code -0x1.d31adcp125F}.
     * <pre>
     * <font style='background-color: #8543D1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8543D1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8543D1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8543D1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8543D1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8543D1'>&nbsp;@&nbsp;</font><font style='background-color: #8543D1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8543D1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8543D1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_VIOLET = -0x1.d31adcp125F;
    static { NAMED.put("true pure violet", -0x1.d31adcp125F); LIST.add(-0x1.d31adcp125F); }

    /**
     * This color constant "bright pure violet" has RGBA8888 code {@code 9452E4FF}, L 0.49019608, A 0.5529412, B 0.4117647, alpha 1.0, hue 0.84020853, saturation 0.75584954, and chroma 0.2049944.
     * It can be represented as a packed float with the constant {@code -0x1.d31afap125F}.
     * <pre>
     * <font style='background-color: #9452E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9452E4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9452E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9452E4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9452E4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9452E4'>&nbsp;@&nbsp;</font><font style='background-color: #9452E4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9452E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9452E4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_VIOLET = -0x1.d31afap125F;
    static { NAMED.put("bright pure violet", -0x1.d31afap125F); LIST.add(-0x1.d31afap125F); }

    /**
     * This color constant "deep purple violet" has RGBA8888 code {@code 7F27C8FF}, L 0.3764706, A 0.5647059, B 0.40392157, alpha 1.0, hue 0.8480996, saturation 0.84031093, and chroma 0.23076649.
     * It can be represented as a packed float with the constant {@code -0x1.cf20cp125F}.
     * <pre>
     * <font style='background-color: #7F27C8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F27C8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F27C8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7F27C8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7F27C8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7F27C8'>&nbsp;@&nbsp;</font><font style='background-color: #7F27C8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F27C8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F27C8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE_VIOLET = -0x1.cf20cp125F;
    static { NAMED.put("deep purple violet", -0x1.cf20cp125F); LIST.add(-0x1.cf20cp125F); }

    /**
     * This color constant "true purple violet" has RGBA8888 code {@code 8E38DCFF}, L 0.43529412, A 0.5647059, B 0.40392157, alpha 1.0, hue 0.8480996, saturation 0.76391906, and chroma 0.23076649.
     * It can be represented as a packed float with the constant {@code -0x1.cf20dep125F}.
     * <pre>
     * <font style='background-color: #8E38DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8E38DC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8E38DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8E38DC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8E38DC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8E38DC'>&nbsp;@&nbsp;</font><font style='background-color: #8E38DC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8E38DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8E38DC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURPLE_VIOLET = -0x1.cf20dep125F;
    static { NAMED.put("true purple violet", -0x1.cf20dep125F); LIST.add(-0x1.cf20dep125F); }

    /**
     * This color constant "bright purple violet" has RGBA8888 code {@code A14BF3FF}, L 0.5058824, A 0.5647059, B 0.40392157, alpha 1.0, hue 0.8480996, saturation 0.85248935, and chroma 0.23076649.
     * It can be represented as a packed float with the constant {@code -0x1.cf2102p125F}.
     * <pre>
     * <font style='background-color: #A14BF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A14BF3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A14BF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A14BF3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A14BF3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A14BF3'>&nbsp;@&nbsp;</font><font style='background-color: #A14BF3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A14BF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A14BF3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURPLE_VIOLET = -0x1.cf2102p125F;
    static { NAMED.put("bright purple violet", -0x1.cf2102p125F); LIST.add(-0x1.cf2102p125F); }

    /**
     * This color constant "deep violet purple" has RGBA8888 code {@code 8A36BEFF}, L 0.40392157, A 0.5647059, B 0.41960785, alpha 1.0, hue 0.86213285, saturation 0.7291336, and chroma 0.20558903.
     * It can be represented as a packed float with the constant {@code -0x1.d720cep125F}.
     * <pre>
     * <font style='background-color: #8A36BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A36BE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A36BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8A36BE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8A36BE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8A36BE'>&nbsp;@&nbsp;</font><font style='background-color: #8A36BE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A36BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A36BE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET_PURPLE = -0x1.d720cep125F;
    static { NAMED.put("deep violet purple", -0x1.d720cep125F); LIST.add(-0x1.d720cep125F); }

    /**
     * This color constant "true violet purple" has RGBA8888 code {@code 9D48D5FF}, L 0.4745098, A 0.5647059, B 0.41960785, alpha 1.0, hue 0.86213285, saturation 0.6730464, and chroma 0.20558903.
     * It can be represented as a packed float with the constant {@code -0x1.d720f2p125F}.
     * <pre>
     * <font style='background-color: #9D48D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9D48D5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9D48D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9D48D5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9D48D5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9D48D5'>&nbsp;@&nbsp;</font><font style='background-color: #9D48D5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9D48D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9D48D5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_VIOLET_PURPLE = -0x1.d720f2p125F;
    static { NAMED.put("true violet purple", -0x1.d720f2p125F); LIST.add(-0x1.d720f2p125F); }

    /**
     * This color constant "bright violet purple" has RGBA8888 code {@code AD57E7FF}, L 0.53333336, A 0.5647059, B 0.41960785, alpha 1.0, hue 0.86213285, saturation 0.7720238, and chroma 0.20558903.
     * It can be represented as a packed float with the constant {@code -0x1.d7211p125F}.
     * <pre>
     * <font style='background-color: #AD57E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD57E7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD57E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AD57E7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AD57E7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AD57E7'>&nbsp;@&nbsp;</font><font style='background-color: #AD57E7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD57E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD57E7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_VIOLET_PURPLE = -0x1.d7211p125F;
    static { NAMED.put("bright violet purple", -0x1.d7211p125F); LIST.add(-0x1.d7211p125F); }

    /**
     * This color constant "deep pure purple" has RGBA8888 code {@code 9828CBFF}, L 0.41568628, A 0.5803922, B 0.4117647, alpha 1.0, hue 0.87130225, saturation 0.8567271, and chroma 0.2378005.
     * It can be represented as a packed float with the constant {@code -0x1.d328d4p125F}.
     * <pre>
     * <font style='background-color: #9828CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9828CB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9828CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9828CB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9828CB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9828CB'>&nbsp;@&nbsp;</font><font style='background-color: #9828CB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9828CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9828CB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_PURPLE = -0x1.d328d4p125F;
    static { NAMED.put("deep pure purple", -0x1.d328d4p125F); LIST.add(-0x1.d328d4p125F); }

    /**
     * This color constant "true pure purple" has RGBA8888 code {@code AA3ADFFF}, L 0.47843137, A 0.5803922, B 0.4117647, alpha 1.0, hue 0.87130225, saturation 0.77984136, and chroma 0.2378005.
     * It can be represented as a packed float with the constant {@code -0x1.d328f4p125F}.
     * <pre>
     * <font style='background-color: #AA3ADF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AA3ADF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AA3ADF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AA3ADF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AA3ADF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AA3ADF'>&nbsp;@&nbsp;</font><font style='background-color: #AA3ADF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AA3ADF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AA3ADF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_PURPLE = -0x1.d328f4p125F;
    static { NAMED.put("true pure purple", -0x1.d328f4p125F); LIST.add(-0x1.d328f4p125F); }

    /**
     * This color constant "bright pure purple" has RGBA8888 code {@code BD4CF4FF}, L 0.54509807, A 0.5803922, B 0.4117647, alpha 1.0, hue 0.87130225, saturation 0.8567271, and chroma 0.2378005.
     * It can be represented as a packed float with the constant {@code -0x1.d32916p125F}.
     * <pre>
     * <font style='background-color: #BD4CF4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD4CF4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD4CF4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BD4CF4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BD4CF4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BD4CF4'>&nbsp;@&nbsp;</font><font style='background-color: #BD4CF4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD4CF4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD4CF4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_PURPLE = -0x1.d32916p125F;
    static { NAMED.put("bright pure purple", -0x1.d32916p125F); LIST.add(-0x1.d32916p125F); }

    /**
     * This color constant "deep magenta purple" has RGBA8888 code {@code A032BBFF}, L 0.42745098, A 0.5803922, B 0.42745098, alpha 1.0, hue 0.8872099, saturation 0.77911645, and chroma 0.21572971.
     * It can be represented as a packed float with the constant {@code -0x1.db28dap125F}.
     * <pre>
     * <font style='background-color: #A032BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A032BB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A032BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A032BB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A032BB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A032BB'>&nbsp;@&nbsp;</font><font style='background-color: #A032BB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A032BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A032BB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_MAGENTA_PURPLE = -0x1.db28dap125F;
    static { NAMED.put("deep magenta purple", -0x1.db28dap125F); LIST.add(-0x1.db28dap125F); }

    /**
     * This color constant "true magenta purple" has RGBA8888 code {@code B242CFFF}, L 0.49019608, A 0.5803922, B 0.42745098, alpha 1.0, hue 0.8872099, saturation 0.7184061, and chroma 0.21572971.
     * It can be represented as a packed float with the constant {@code -0x1.db28fap125F}.
     * <pre>
     * <font style='background-color: #B242CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B242CF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B242CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B242CF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B242CF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B242CF'>&nbsp;@&nbsp;</font><font style='background-color: #B242CF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B242CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B242CF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_MAGENTA_PURPLE = -0x1.db28fap125F;
    static { NAMED.put("true magenta purple", -0x1.db28fap125F); LIST.add(-0x1.db28fap125F); }

    /**
     * This color constant "bright magenta purple" has RGBA8888 code {@code C855E6FF}, L 0.5647059, A 0.5803922, B 0.42745098, alpha 1.0, hue 0.8872099, saturation 0.7278588, and chroma 0.21572971.
     * It can be represented as a packed float with the constant {@code -0x1.db292p125F}.
     * <pre>
     * <font style='background-color: #C855E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C855E6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C855E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C855E6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C855E6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C855E6'>&nbsp;@&nbsp;</font><font style='background-color: #C855E6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C855E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C855E6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA_PURPLE = -0x1.db292p125F;
    static { NAMED.put("bright magenta purple", -0x1.db292p125F); LIST.add(-0x1.db292p125F); }

    /**
     * This color constant "deep purple magenta" has RGBA8888 code {@code B92DCEFF}, L 0.4745098, A 0.59607846, B 0.42352942, alpha 1.0, hue 0.8965574, saturation 0.8373503, and chroma 0.24463232.
     * It can be represented as a packed float with the constant {@code -0x1.d930f2p125F}.
     * <pre>
     * <font style='background-color: #B92DCE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B92DCE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B92DCE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B92DCE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B92DCE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B92DCE'>&nbsp;@&nbsp;</font><font style='background-color: #B92DCE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B92DCE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B92DCE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE_MAGENTA = -0x1.d930f2p125F;
    static { NAMED.put("deep purple magenta", -0x1.d930f2p125F); LIST.add(-0x1.d930f2p125F); }

    /**
     * This color constant "true purple magenta" has RGBA8888 code {@code CE40E2FF}, L 0.5411765, A 0.59607846, B 0.42352942, alpha 1.0, hue 0.8965574, saturation 0.77532434, and chroma 0.24463232.
     * It can be represented as a packed float with the constant {@code -0x1.d93114p125F}.
     * <pre>
     * <font style='background-color: #CE40E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CE40E2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CE40E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CE40E2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CE40E2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CE40E2'>&nbsp;@&nbsp;</font><font style='background-color: #CE40E2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CE40E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CE40E2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURPLE_MAGENTA = -0x1.d93114p125F;
    static { NAMED.put("true purple magenta", -0x1.d93114p125F); LIST.add(-0x1.d93114p125F); }

    /**
     * This color constant "bright purple magenta" has RGBA8888 code {@code E050F5FF}, L 0.6, A 0.59607846, B 0.42352942, alpha 1.0, hue 0.8965574, saturation 0.8602914, and chroma 0.24463232.
     * It can be represented as a packed float with the constant {@code -0x1.d93132p125F}.
     * <pre>
     * <font style='background-color: #E050F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E050F5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E050F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E050F5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E050F5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E050F5'>&nbsp;@&nbsp;</font><font style='background-color: #E050F5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E050F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E050F5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURPLE_MAGENTA = -0x1.d93132p125F;
    static { NAMED.put("bright purple magenta", -0x1.d93132p125F); LIST.add(-0x1.d93132p125F); }

    /**
     * This color constant "deep pure magenta" has RGBA8888 code {@code BD38BDFF}, L 0.48235294, A 0.5921569, B 0.4392157, alpha 1.0, hue 0.911086, saturation 0.7753957, and chroma 0.21993263.
     * It can be represented as a packed float with the constant {@code -0x1.e12ef6p125F}.
     * <pre>
     * <font style='background-color: #BD38BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD38BD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD38BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BD38BD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BD38BD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BD38BD'>&nbsp;@&nbsp;</font><font style='background-color: #BD38BD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD38BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD38BD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_MAGENTA = -0x1.e12ef6p125F;
    static { NAMED.put("deep pure magenta", -0x1.e12ef6p125F); LIST.add(-0x1.e12ef6p125F); }

    /**
     * This color constant "true pure magenta" has RGBA8888 code {@code D049CFFF}, L 0.54509807, A 0.5921569, B 0.4392157, alpha 1.0, hue 0.911086, saturation 0.70754856, and chroma 0.21993263.
     * It can be represented as a packed float with the constant {@code -0x1.e12f16p125F}.
     * <pre>
     * <font style='background-color: #D049CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D049CF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D049CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D049CF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D049CF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D049CF'>&nbsp;@&nbsp;</font><font style='background-color: #D049CF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D049CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D049CF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_MAGENTA = -0x1.e12f16p125F;
    static { NAMED.put("true pure magenta", -0x1.e12f16p125F); LIST.add(-0x1.e12f16p125F); }

    /**
     * This color constant "bright pure magenta" has RGBA8888 code {@code E65BE5FF}, L 0.6156863, A 0.5921569, B 0.4392157, alpha 1.0, hue 0.911086, saturation 0.72569084, and chroma 0.21993263.
     * It can be represented as a packed float with the constant {@code -0x1.e12f3ap125F}.
     * <pre>
     * <font style='background-color: #E65BE5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E65BE5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E65BE5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E65BE5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E65BE5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E65BE5'>&nbsp;@&nbsp;</font><font style='background-color: #E65BE5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E65BE5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E65BE5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_MAGENTA = -0x1.e12f3ap125F;
    static { NAMED.put("bright pure magenta", -0x1.e12f3ap125F); LIST.add(-0x1.e12f3ap125F); }

    /**
     * This color constant "deep red magenta" has RGBA8888 code {@code C4207FFF}, L 0.43529412, A 0.6039216, B 0.47843137, alpha 1.0, hue 0.97084755, saturation 0.88577616, and chroma 0.21144326.
     * It can be represented as a packed float with the constant {@code -0x1.f534dep125F}.
     * <pre>
     * <font style='background-color: #C4207F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4207F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4207F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C4207F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C4207F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C4207F'>&nbsp;@&nbsp;</font><font style='background-color: #C4207F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4207F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4207F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED_MAGENTA = -0x1.f534dep125F;
    static { NAMED.put("deep red magenta", -0x1.f534dep125F); LIST.add(-0x1.f534dep125F); }

    /**
     * This color constant "true red magenta" has RGBA8888 code {@code D83390FF}, L 0.49803922, A 0.6039216, B 0.47843137, alpha 1.0, hue 0.97084755, saturation 0.80761945, and chroma 0.21144326.
     * It can be represented as a packed float with the constant {@code -0x1.f534fep125F}.
     * <pre>
     * <font style='background-color: #D83390;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D83390; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D83390;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D83390'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D83390'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D83390'>&nbsp;@&nbsp;</font><font style='background-color: #D83390; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D83390;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D83390; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_RED_MAGENTA = -0x1.f534fep125F;
    static { NAMED.put("true red magenta", -0x1.f534fep125F); LIST.add(-0x1.f534fep125F); }

    /**
     * This color constant "bright red magenta" has RGBA8888 code {@code F148A3FF}, L 0.57254905, A 0.6039216, B 0.47843137, alpha 1.0, hue 0.97084755, saturation 0.80761945, and chroma 0.21144326.
     * It can be represented as a packed float with the constant {@code -0x1.f53524p125F}.
     * <pre>
     * <font style='background-color: #F148A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F148A3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F148A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F148A3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F148A3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F148A3'>&nbsp;@&nbsp;</font><font style='background-color: #F148A3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F148A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F148A3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED_MAGENTA = -0x1.f53524p125F;
    static { NAMED.put("bright red magenta", -0x1.f53524p125F); LIST.add(-0x1.f53524p125F); }

    /**
     * This color constant "deep magenta red" has RGBA8888 code {@code C3335AFF}, L 0.4392157, A 0.5882353, B 0.50980395, alpha 1.0, hue 0.02065512, saturation 0.7731609, and chroma 0.17686298.
     * It can be represented as a packed float with the constant {@code -0x1.052cep126F}.
     * <pre>
     * <font style='background-color: #C3335A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3335A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3335A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C3335A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C3335A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C3335A'>&nbsp;@&nbsp;</font><font style='background-color: #C3335A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3335A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3335A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_MAGENTA_RED = -0x1.052cep126F;
    static { NAMED.put("deep magenta red", -0x1.052cep126F); LIST.add(-0x1.052cep126F); }

    /**
     * This color constant "true magenta red" has RGBA8888 code {@code D54167FF}, L 0.49411765, A 0.5882353, B 0.50980395, alpha 1.0, hue 0.02065512, saturation 0.71368694, and chroma 0.17686298.
     * It can be represented as a packed float with the constant {@code -0x1.052cfcp126F}.
     * <pre>
     * <font style='background-color: #D54167;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D54167; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D54167;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D54167'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D54167'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D54167'>&nbsp;@&nbsp;</font><font style='background-color: #D54167; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D54167;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D54167; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_MAGENTA_RED = -0x1.052cfcp126F;
    static { NAMED.put("true magenta red", -0x1.052cfcp126F); LIST.add(-0x1.052cfcp126F); }

    /**
     * This color constant "bright magenta red" has RGBA8888 code {@code E95075FF}, L 0.5529412, A 0.5882353, B 0.50980395, alpha 1.0, hue 0.02065512, saturation 0.7482202, and chroma 0.17686298.
     * It can be represented as a packed float with the constant {@code -0x1.052d1ap126F}.
     * <pre>
     * <font style='background-color: #E95075;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E95075; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E95075;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E95075'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E95075'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E95075'>&nbsp;@&nbsp;</font><font style='background-color: #E95075; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E95075;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E95075; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA_RED = -0x1.052d1ap126F;
    static { NAMED.put("bright magenta red", -0x1.052d1ap126F); LIST.add(-0x1.052d1ap126F); }

    /**
     * This color constant "bold pure red" has RGBA8888 code {@code FF0000FF}, L 0.50980395, A 0.6117647, B 0.56078434, alpha 1.0, hue 0.08024464, saturation 0.98868626, and chroma 0.25345513.
     * It can be represented as a packed float with the constant {@code -0x1.1f3904p126F}.
     * <pre>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #FF0000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_RED = -0x1.1f3904p126F;
    static { NAMED.put("bold pure red", -0x1.1f3904p126F); LIST.add(-0x1.1f3904p126F); }

    /**
     * This color constant "bold brown red" has RGBA8888 code {@code E24A30FF}, L 0.5176471, A 0.5764706, B 0.54901963, alpha 1.0, hue 0.09174438, saturation 0.7339607, and chroma 0.1809568.
     * It can be represented as a packed float with the constant {@code -0x1.192708p126F}.
     * <pre>
     * <font style='background-color: #E24A30;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E24A30; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E24A30;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E24A30'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E24A30'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E24A30'>&nbsp;@&nbsp;</font><font style='background-color: #E24A30; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E24A30;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E24A30; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_BROWN_RED = -0x1.192708p126F;
    static { NAMED.put("bold brown red", -0x1.192708p126F); LIST.add(-0x1.192708p126F); }

    /**
     * This color constant "bold red brown" has RGBA8888 code {@code DA6340FF}, L 0.54901963, A 0.5568628, B 0.54509807, alpha 1.0, hue 0.107399724, saturation 0.6511652, and chroma 0.14458403.
     * It can be represented as a packed float with the constant {@code -0x1.171d18p126F}.
     * <pre>
     * <font style='background-color: #DA6340;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA6340; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA6340;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DA6340'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DA6340'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DA6340'>&nbsp;@&nbsp;</font><font style='background-color: #DA6340; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA6340;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA6340; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_RED_BROWN = -0x1.171d18p126F;
    static { NAMED.put("bold red brown", -0x1.171d18p126F); LIST.add(-0x1.171d18p126F); }

    /**
     * This color constant "bold pure brown" has RGBA8888 code {@code CD7351FF}, L 0.5647059, A 0.5411765, B 0.5372549, alpha 1.0, hue 0.11743266, saturation 0.5405843, and chroma 0.11062346.
     * It can be represented as a packed float with the constant {@code -0x1.13152p126F}.
     * <pre>
     * <font style='background-color: #CD7351;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CD7351; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CD7351;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CD7351'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CD7351'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CD7351'>&nbsp;@&nbsp;</font><font style='background-color: #CD7351; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CD7351;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CD7351; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_BROWN = -0x1.13152p126F;
    static { NAMED.put("bold pure brown", -0x1.13152p126F); LIST.add(-0x1.13152p126F); }

    /**
     * This color constant "bold orange brown" has RGBA8888 code {@code DA7A4BFF}, L 0.59607846, A 0.5411765, B 0.54509807, alpha 1.0, hue 0.13191015, saturation 0.6142951, and chroma 0.121659465.
     * It can be represented as a packed float with the constant {@code -0x1.17153p126F}.
     * <pre>
     * <font style='background-color: #DA7A4B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA7A4B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA7A4B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DA7A4B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DA7A4B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DA7A4B'>&nbsp;@&nbsp;</font><font style='background-color: #DA7A4B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA7A4B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA7A4B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_ORANGE_BROWN = -0x1.17153p126F;
    static { NAMED.put("bold orange brown", -0x1.17153p126F); LIST.add(-0x1.17153p126F); }

    /**
     * This color constant "bold brown orange" has RGBA8888 code {@code E37F49FF}, L 0.61960787, A 0.5411765, B 0.54901963, alpha 1.0, hue 0.138223, saturation 0.6678191, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.19153cp126F}.
     * <pre>
     * <font style='background-color: #E37F49;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E37F49; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E37F49;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E37F49'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E37F49'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E37F49'>&nbsp;@&nbsp;</font><font style='background-color: #E37F49; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E37F49;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E37F49; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_BROWN_ORANGE = -0x1.19153cp126F;
    static { NAMED.put("bold brown orange", -0x1.19153cp126F); LIST.add(-0x1.19153cp126F); }

    /**
     * This color constant "bold pure orange" has RGBA8888 code {@code F97A1BFF}, L 0.6392157, A 0.54901963, B 0.5686275, alpha 1.0, hue 0.15043804, saturation 0.90626955, and chroma 0.16801417.
     * It can be represented as a packed float with the constant {@code -0x1.231946p126F}.
     * <pre>
     * <font style='background-color: #F97A1B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F97A1B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F97A1B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F97A1B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F97A1B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F97A1B'>&nbsp;@&nbsp;</font><font style='background-color: #F97A1B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F97A1B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F97A1B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_ORANGE = -0x1.231946p126F;
    static { NAMED.put("bold pure orange", -0x1.231946p126F); LIST.add(-0x1.231946p126F); }

    /**
     * This color constant "bold saffron orange" has RGBA8888 code {@code F98A15FF}, L 0.67058825, A 0.5372549, B 0.57254905, alpha 1.0, hue 0.17289151, saturation 0.9136558, and chroma 0.16247371.
     * It can be represented as a packed float with the constant {@code -0x1.251356p126F}.
     * <pre>
     * <font style='background-color: #F98A15;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F98A15; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F98A15;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F98A15'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F98A15'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F98A15'>&nbsp;@&nbsp;</font><font style='background-color: #F98A15; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F98A15;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F98A15; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_SAFFRON_ORANGE = -0x1.251356p126F;
    static { NAMED.put("bold saffron orange", -0x1.251356p126F); LIST.add(-0x1.251356p126F); }

    /**
     * This color constant "bold orange saffron" has RGBA8888 code {@code FF9600FF}, L 0.7019608, A 0.5294118, B 0.5764706, alpha 1.0, hue 0.189452, saturation 0.9573626, and chroma 0.1632233.
     * It can be represented as a packed float with the constant {@code -0x1.270f66p126F}.
     * <pre>
     * <font style='background-color: #FF9600;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF9600; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF9600;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF9600'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF9600'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF9600'>&nbsp;@&nbsp;</font><font style='background-color: #FF9600; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF9600;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF9600; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_ORANGE_SAFFRON = -0x1.270f66p126F;
    static { NAMED.put("bold orange saffron", -0x1.270f66p126F); LIST.add(-0x1.270f66p126F); }

    /**
     * This color constant "bold pure saffron" has RGBA8888 code {@code F6A319FF}, L 0.7176471, A 0.5176471, B 0.5764706, alpha 1.0, hue 0.21101315, saturation 0.9162457, and chroma 0.15634763.
     * It can be represented as a packed float with the constant {@code -0x1.27096ep126F}.
     * <pre>
     * <font style='background-color: #F6A319;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6A319; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6A319;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F6A319'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F6A319'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F6A319'>&nbsp;@&nbsp;</font><font style='background-color: #F6A319; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6A319;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6A319; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_SAFFRON = -0x1.27096ep126F;
    static { NAMED.put("bold pure saffron", -0x1.27096ep126F); LIST.add(-0x1.27096ep126F); }

    /**
     * This color constant "bold yellow saffron" has RGBA8888 code {@code FCBA00FF}, L 0.7764706, A 0.5058824, B 0.58431375, alpha 1.0, hue 0.235567, saturation 0.96046615, and chroma 0.16837704.
     * It can be represented as a packed float with the constant {@code -0x1.2b038cp126F}.
     * <pre>
     * <font style='background-color: #FCBA00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FCBA00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FCBA00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FCBA00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FCBA00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FCBA00'>&nbsp;@&nbsp;</font><font style='background-color: #FCBA00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FCBA00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FCBA00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_YELLOW_SAFFRON = -0x1.2b038cp126F;
    static { NAMED.put("bold yellow saffron", -0x1.2b038cp126F); LIST.add(-0x1.2b038cp126F); }

    /**
     * This color constant "bold saffron yellow" has RGBA8888 code {@code FEDA2DFF}, L 0.85490197, A 0.4862745, B 0.58431375, alpha 1.0, hue 0.2715826, saturation 0.96537405, and chroma 0.17017984.
     * It can be represented as a packed float with the constant {@code -0x1.2af9b4p126F}.
     * <pre>
     * <font style='background-color: #FEDA2D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FEDA2D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FEDA2D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FEDA2D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FEDA2D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FEDA2D'>&nbsp;@&nbsp;</font><font style='background-color: #FEDA2D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FEDA2D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FEDA2D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_SAFFRON_YELLOW = -0x1.2af9b4p126F;
    static { NAMED.put("bold saffron yellow", -0x1.2af9b4p126F); LIST.add(-0x1.2af9b4p126F); }

    /**
     * This color constant "bold pure yellow" has RGBA8888 code {@code FDFA00FF}, L 0.9254902, A 0.4627451, B 0.59607846, alpha 1.0, hue 0.3049839, saturation 0.94895214, and chroma 0.20529193.
     * It can be represented as a packed float with the constant {@code -0x1.30edd8p126F}.
     * <pre>
     * <font style='background-color: #FDFA00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDFA00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDFA00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FDFA00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FDFA00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FDFA00'>&nbsp;@&nbsp;</font><font style='background-color: #FDFA00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDFA00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDFA00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_YELLOW = -0x1.30edd8p126F;
    static { NAMED.put("bold pure yellow", -0x1.30edd8p126F); LIST.add(-0x1.30edd8p126F); }

    /**
     * This color constant "bold lime yellow" has RGBA8888 code {@code E1F62DFF}, L 0.89411765, A 0.45490196, B 0.5882353, alpha 1.0, hue 0.3209959, saturation 0.89456487, and chroma 0.1974106.
     * It can be represented as a packed float with the constant {@code -0x1.2ce9c8p126F}.
     * <pre>
     * <font style='background-color: #E1F62D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1F62D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1F62D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E1F62D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E1F62D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E1F62D'>&nbsp;@&nbsp;</font><font style='background-color: #E1F62D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1F62D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1F62D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_LIME_YELLOW = -0x1.2ce9c8p126F;
    static { NAMED.put("bold lime yellow", -0x1.2ce9c8p126F); LIST.add(-0x1.2ce9c8p126F); }

    /**
     * This color constant "bold yellow lime" has RGBA8888 code {@code C6F500FF}, L 0.8666667, A 0.4392157, B 0.5921569, alpha 1.0, hue 0.33891395, saturation 0.9593879, and chroma 0.21993263.
     * It can be represented as a packed float with the constant {@code -0x1.2ee1bap126F}.
     * <pre>
     * <font style='background-color: #C6F500;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C6F500; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C6F500;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C6F500'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C6F500'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C6F500'>&nbsp;@&nbsp;</font><font style='background-color: #C6F500; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C6F500;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C6F500; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_YELLOW_LIME = -0x1.2ee1bap126F;
    static { NAMED.put("bold yellow lime", -0x1.2ee1bap126F); LIST.add(-0x1.2ee1bap126F); }

    /**
     * This color constant "bold pure lime" has RGBA8888 code {@code B0FA34FF}, L 0.8627451, A 0.43137255, B 0.58431375, alpha 1.0, hue 0.35471845, saturation 0.882631, and chroma 0.21657681.
     * It can be represented as a packed float with the constant {@code -0x1.2addb8p126F}.
     * <pre>
     * <font style='background-color: #B0FA34;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0FA34; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0FA34;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B0FA34'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B0FA34'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B0FA34'>&nbsp;@&nbsp;</font><font style='background-color: #B0FA34; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0FA34;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0FA34; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_LIME = -0x1.2addb8p126F;
    static { NAMED.put("bold pure lime", -0x1.2addb8p126F); LIST.add(-0x1.2addb8p126F); }

    /**
     * This color constant "bold green lime" has RGBA8888 code {@code 82FC00FF}, L 0.8392157, A 0.40784314, B 0.5882353, alpha 1.0, hue 0.375, saturation 0.97095263, and chroma 0.25417653.
     * It can be represented as a packed float with the constant {@code -0x1.2cd1acp126F}.
     * <pre>
     * <font style='background-color: #82FC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #82FC00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #82FC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #82FC00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #82FC00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #82FC00'>&nbsp;@&nbsp;</font><font style='background-color: #82FC00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #82FC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #82FC00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_GREEN_LIME = -0x1.2cd1acp126F;
    static { NAMED.put("bold green lime", -0x1.2cd1acp126F); LIST.add(-0x1.2cd1acp126F); }

    /**
     * This color constant "bold lime green" has RGBA8888 code {@code 61FA23FF}, L 0.8156863, A 0.4, B 0.58431375, alpha 1.0, hue 0.3851376, saturation 0.912374, and chroma 0.26057938.
     * It can be represented as a packed float with the constant {@code -0x1.2acdap126F}.
     * <pre>
     * <font style='background-color: #61FA23;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #61FA23; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #61FA23;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #61FA23'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #61FA23'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #61FA23'>&nbsp;@&nbsp;</font><font style='background-color: #61FA23; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #61FA23;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #61FA23; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_LIME_GREEN = -0x1.2acdap126F;
    static { NAMED.put("bold lime green", -0x1.2acdap126F); LIST.add(-0x1.2acdap126F); }

    /**
     * This color constant "bold pure green" has RGBA8888 code {@code 00FE1BFF}, L 0.80784315, A 0.38039216, B 0.58431375, alpha 1.0, hue 0.399282, saturation 0.9920574, and chroma 0.29153293.
     * It can be represented as a packed float with the constant {@code -0x1.2ac39cp126F}.
     * <pre>
     * <font style='background-color: #00FE1B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FE1B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FE1B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FE1B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FE1B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FE1B'>&nbsp;@&nbsp;</font><font style='background-color: #00FE1B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FE1B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FE1B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_GREEN = -0x1.2ac39cp126F;
    static { NAMED.put("bold pure green", -0x1.2ac39cp126F); LIST.add(-0x1.2ac39cp126F); }

    /**
     * This color constant "bold cyan green" has RGBA8888 code {@code 2FF59FFF}, L 0.80784315, A 0.4117647, B 0.5294118, alpha 1.0, hue 0.4445043, saturation 0.86701477, and chroma 0.18528971.
     * It can be represented as a packed float with the constant {@code -0x1.0ed39cp126F}.
     * <pre>
     * <font style='background-color: #2FF59F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2FF59F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2FF59F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2FF59F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2FF59F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2FF59F'>&nbsp;@&nbsp;</font><font style='background-color: #2FF59F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2FF59F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2FF59F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_CYAN_GREEN = -0x1.0ed39cp126F;
    static { NAMED.put("bold cyan green", -0x1.0ed39cp126F); LIST.add(-0x1.0ed39cp126F); }

    /**
     * This color constant "bold green cyan" has RGBA8888 code {@code 00F9CFFF}, L 0.827451, A 0.41568628, B 0.5019608, alpha 1.0, hue 0.49243808, saturation 0.9556271, and chroma 0.16801417.
     * It can be represented as a packed float with the constant {@code -0x1.00d5a6p126F}.
     * <pre>
     * <font style='background-color: #00F9CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00F9CF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00F9CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00F9CF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00F9CF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00F9CF'>&nbsp;@&nbsp;</font><font style='background-color: #00F9CF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00F9CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00F9CF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_GREEN_CYAN = -0x1.00d5a6p126F;
    static { NAMED.put("bold green cyan", -0x1.00d5a6p126F); LIST.add(-0x1.00d5a6p126F); }

    /**
     * This color constant "bold pure cyan" has RGBA8888 code {@code 20F6F7FF}, L 0.8352941, A 0.42745098, B 0.47843137, alpha 1.0, hue 0.5431152, saturation 0.9112947, and chroma 0.15078327.
     * It can be represented as a packed float with the constant {@code -0x1.f4dbaap125F}.
     * <pre>
     * <font style='background-color: #20F6F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #20F6F7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #20F6F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #20F6F7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #20F6F7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #20F6F7'>&nbsp;@&nbsp;</font><font style='background-color: #20F6F7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #20F6F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #20F6F7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_CYAN = -0x1.f4dbaap125F;
    static { NAMED.put("bold pure cyan", -0x1.f4dbaap125F); LIST.add(-0x1.f4dbaap125F); }

    /**
     * This color constant "bold blue cyan" has RGBA8888 code {@code 00D0FFFF}, L 0.73333335, A 0.44313726, B 0.4509804, alpha 1.0, hue 0.6127901, saturation 0.9455943, and chroma 0.14956398.
     * It can be represented as a packed float with the constant {@code -0x1.e6e376p125F}.
     * <pre>
     * <font style='background-color: #00D0FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00D0FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00D0FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00D0FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00D0FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00D0FF'>&nbsp;@&nbsp;</font><font style='background-color: #00D0FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00D0FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00D0FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_BLUE_CYAN = -0x1.e6e376p125F;
    static { NAMED.put("bold blue cyan", -0x1.e6e376p125F); LIST.add(-0x1.e6e376p125F); }

    /**
     * This color constant "bold cyan blue" has RGBA8888 code {@code 119FF1FF}, L 0.5921569, A 0.45882353, B 0.43137255, alpha 1.0, hue 0.665366, saturation 0.91735274, and chroma 0.15944009.
     * It can be represented as a packed float with the constant {@code -0x1.dceb2ep125F}.
     * <pre>
     * <font style='background-color: #119FF1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #119FF1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #119FF1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #119FF1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #119FF1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #119FF1'>&nbsp;@&nbsp;</font><font style='background-color: #119FF1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #119FF1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #119FF1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_CYAN_BLUE = -0x1.dceb2ep125F;
    static { NAMED.put("bold cyan blue", -0x1.dceb2ep125F); LIST.add(-0x1.dceb2ep125F); }

    /**
     * This color constant "bold pure blue" has RGBA8888 code {@code 0900FFFF}, L 0.30588236, A 0.48235294, B 0.34117648, alpha 1.0, hue 0.73413044, saturation 0.9925803, and chroma 0.31835338.
     * It can be represented as a packed float with the constant {@code -0x1.aef69cp125F}.
     * <pre>
     * <font style='background-color: #0900FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0900FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0900FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0900FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0900FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0900FF'>&nbsp;@&nbsp;</font><font style='background-color: #0900FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0900FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0900FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_BLUE = -0x1.aef69cp125F;
    static { NAMED.put("bold pure blue", -0x1.aef69cp125F); LIST.add(-0x1.aef69cp125F); }

    /**
     * This color constant "bold violet blue" has RGBA8888 code {@code 401BF0FF}, L 0.32941177, A 0.5137255, B 0.36078432, alpha 1.0, hue 0.7681207, saturation 0.9032777, and chroma 0.27868843.
     * It can be represented as a packed float with the constant {@code -0x1.b906a8p125F}.
     * <pre>
     * <font style='background-color: #401BF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #401BF0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #401BF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #401BF0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #401BF0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #401BF0'>&nbsp;@&nbsp;</font><font style='background-color: #401BF0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #401BF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #401BF0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_VIOLET_BLUE = -0x1.b906a8p125F;
    static { NAMED.put("bold violet blue", -0x1.b906a8p125F); LIST.add(-0x1.b906a8p125F); }

    /**
     * This color constant "bold blue violet" has RGBA8888 code {@code 6A00FFFF}, L 0.38039216, A 0.54901963, B 0.36078432, alpha 1.0, hue 0.8065884, saturation 0.9825344, and chroma 0.2940345.
     * It can be represented as a packed float with the constant {@code -0x1.b918c2p125F}.
     * <pre>
     * <font style='background-color: #6A00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A00FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6A00FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6A00FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6A00FF'>&nbsp;@&nbsp;</font><font style='background-color: #6A00FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A00FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_BLUE_VIOLET = -0x1.b918c2p125F;
    static { NAMED.put("bold blue violet", -0x1.b918c2p125F); LIST.add(-0x1.b918c2p125F); }

    /**
     * This color constant "bold pure violet" has RGBA8888 code {@code 8A22EFFF}, L 0.41960785, A 0.5686275, B 0.38431373, alpha 1.0, hue 0.83841944, saturation 0.8865479, and chroma 0.26796988.
     * It can be represented as a packed float with the constant {@code -0x1.c522d6p125F}.
     * <pre>
     * <font style='background-color: #8A22EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A22EF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A22EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8A22EF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8A22EF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8A22EF'>&nbsp;@&nbsp;</font><font style='background-color: #8A22EF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A22EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A22EF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_VIOLET = -0x1.c522d6p125F;
    static { NAMED.put("bold pure violet", -0x1.c522d6p125F); LIST.add(-0x1.c522d6p125F); }

    /**
     * This color constant "bold purple violet" has RGBA8888 code {@code 9F00FFFF}, L 0.4509804, A 0.5882353, B 0.3764706, alpha 1.0, hue 0.8516046, saturation 0.9897569, and chroma 0.3024255.
     * It can be represented as a packed float with the constant {@code -0x1.c12ce6p125F}.
     * <pre>
     * <font style='background-color: #9F00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9F00FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9F00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9F00FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9F00FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9F00FF'>&nbsp;@&nbsp;</font><font style='background-color: #9F00FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9F00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9F00FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURPLE_VIOLET = -0x1.c12ce6p125F;
    static { NAMED.put("bold purple violet", -0x1.c12ce6p125F); LIST.add(-0x1.c12ce6p125F); }

    /**
     * This color constant "bold violet purple" has RGBA8888 code {@code A324F1FF}, L 0.45882353, A 0.58431375, B 0.39215687, alpha 1.0, hue 0.8588265, saturation 0.89302945, and chroma 0.2727111.
     * It can be represented as a packed float with the constant {@code -0x1.c92aeap125F}.
     * <pre>
     * <font style='background-color: #A324F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A324F1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A324F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A324F1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A324F1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A324F1'>&nbsp;@&nbsp;</font><font style='background-color: #A324F1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A324F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A324F1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_VIOLET_PURPLE = -0x1.c92aeap125F;
    static { NAMED.put("bold violet purple", -0x1.c92aeap125F); LIST.add(-0x1.c92aeap125F); }

    /**
     * This color constant "bold pure purple" has RGBA8888 code {@code C200FFFF}, L 0.5019608, A 0.60784316, B 0.39215687, alpha 1.0, hue 0.8778911, saturation 0.9724325, and chroma 0.30383494.
     * It can be represented as a packed float with the constant {@code -0x1.c937p125F}.
     * <pre>
     * <font style='background-color: #C200FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C200FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C200FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C200FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C200FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C200FF'>&nbsp;@&nbsp;</font><font style='background-color: #C200FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C200FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C200FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_PURPLE = -0x1.c937p125F;
    static { NAMED.put("bold pure purple", -0x1.c937p125F); LIST.add(-0x1.c937p125F); }

    /**
     * This color constant "bold magenta purple" has RGBA8888 code {@code CB2AF8FF}, L 0.5294118, A 0.6039216, B 0.40392157, alpha 1.0, hue 0.88434434, saturation 0.8919699, and chroma 0.2819544.
     * It can be represented as a packed float with the constant {@code -0x1.cf350ep125F}.
     * <pre>
     * <font style='background-color: #CB2AF8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CB2AF8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CB2AF8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CB2AF8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CB2AF8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CB2AF8'>&nbsp;@&nbsp;</font><font style='background-color: #CB2AF8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CB2AF8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CB2AF8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_MAGENTA_PURPLE = -0x1.cf350ep125F;
    static { NAMED.put("bold magenta purple", -0x1.cf350ep125F); LIST.add(-0x1.cf350ep125F); }

    /**
     * This color constant "bold purple magenta" has RGBA8888 code {@code EA14FFFF}, L 0.5686275, A 0.62352943, B 0.40784314, alpha 1.0, hue 0.90080184, saturation 0.96117365, and chroma 0.30703226.
     * It can be represented as a packed float with the constant {@code -0x1.d13f22p125F}.
     * <pre>
     * <font style='background-color: #EA14FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EA14FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EA14FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EA14FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EA14FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EA14FF'>&nbsp;@&nbsp;</font><font style='background-color: #EA14FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EA14FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EA14FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURPLE_MAGENTA = -0x1.d13f22p125F;
    static { NAMED.put("bold purple magenta", -0x1.d13f22p125F); LIST.add(-0x1.d13f22p125F); }

    /**
     * This color constant "bold pure magenta" has RGBA8888 code {@code F52AF6FF}, L 0.59607846, A 0.62352943, B 0.41960785, alpha 1.0, hue 0.911086, saturation 0.9092994, and chroma 0.29361907.
     * It can be represented as a packed float with the constant {@code -0x1.d73f3p125F}.
     * <pre>
     * <font style='background-color: #F52AF6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F52AF6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F52AF6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F52AF6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F52AF6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F52AF6'>&nbsp;@&nbsp;</font><font style='background-color: #F52AF6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F52AF6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F52AF6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_MAGENTA = -0x1.d73f3p125F;
    static { NAMED.put("bold pure magenta", -0x1.d73f3p125F); LIST.add(-0x1.d73f3p125F); }

    /**
     * This color constant "bold red magenta" has RGBA8888 code {@code FF00A4FF}, L 0.54901963, A 0.63529414, B 0.47058824, alpha 1.0, hue 0.96857655, saturation 0.9778942, and chroma 0.2758266.
     * It can be represented as a packed float with the constant {@code -0x1.f14518p125F}.
     * <pre>
     * <font style='background-color: #FF00A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF00A4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF00A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF00A4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF00A4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF00A4'>&nbsp;@&nbsp;</font><font style='background-color: #FF00A4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF00A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF00A4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_RED_MAGENTA = -0x1.f14518p125F;
    static { NAMED.put("bold red magenta", -0x1.f14518p125F); LIST.add(-0x1.f14518p125F); }

    /**
     * This color constant "bold magenta red" has RGBA8888 code {@code F82162FF}, L 0.52156866, A 0.6156863, B 0.5176471, alpha 1.0, hue 0.02629608, saturation 0.907875, and chroma 0.23313475.
     * It can be represented as a packed float with the constant {@code -0x1.093b0ap126F}.
     * <pre>
     * <font style='background-color: #F82162;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F82162; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F82162;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F82162'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F82162'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F82162'>&nbsp;@&nbsp;</font><font style='background-color: #F82162; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F82162;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F82162; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_MAGENTA_RED = -0x1.093b0ap126F;
    static { NAMED.put("bold magenta red", -0x1.093b0ap126F); LIST.add(-0x1.093b0ap126F); }

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
