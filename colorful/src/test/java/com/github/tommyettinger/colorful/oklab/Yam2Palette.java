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
 * palette, Yam2, that is designed to be as consistent as possible in its support for hues while keeping lots of
 * grayscale, desaturated, and mid-saturation colors, and to have a coherent naming system. This is the successor to
 * Yam (1), and may still be adjusted as needed (this may or may not be in another version like Yam3). The original Yam
 * had too many colors that were indistinguishable from grayscale, despite being named like they had a non-gray color
 * included in their mix. Yam and Yam2 share the same shape and distribution of colors, though, and both have 255 opaque
 * colors, plus pure transparent.
 * <br>
 * You can access colors by their constant name, such as {@code PALE_GREEN_CYAN}, by the {@link #NAMED} map using
 * {@code NAMED.get("pale green cyan", 0f)}, or by index in the FloatArray called {@link #LIST}. Note that to access
 * a float color from NAMED, you need to give a default value if the name is not found; {@code 0f} is a good default
 * because it will not occur in a valid Oklab color. You can access the names in a specific order with {@link #NAMES}
 * (which is alphabetical), {@link #NAMES_BY_HUE} (which is sorted by the hue of the matching color, from red to yellow
 * to blue to purple to red again), or {@link #NAMES_BY_LIGHTNESS} (which is sorted by the intensity of
 * the matching color, from darkest to lightest). Having a name lets you look up the matching color in {@link #NAMED}.
 * <br>
 * The names here put the most relevant color last, so "pale green cyan" is more cyan than it is green. There are 12
 * words used for hue; in order, they are: red, brown, orange, saffron, yellow, lime, green, cyan, blue, violet, purple,
 * and magenta. There are four very-desaturated colors per hue, called "darker gray HUE", "dark gray HUE", "light gray
 * HUE", and "lighter gray HUE" (for each of the twelve hues). Going towards more saturated colors, there are drab,
 * dull, and pale variants of "HUE2 HUE1" and "HUE1 HUE2", where the last hue is more important, and hues are only
 * paired with their neighbors in the order. Getting even more saturated, there are "bright" and "deep" variants of
 * "HUE1", "HUE2 HUE1", and "HUE1 HUE2". At the most saturated edge, there are "some HUE2 HUE1", "more HUE2 HUE1", "more
 * HUE1 HUE2", and "some HUE1 HUE2", forming a rainbow-colored band with medium lightness consistently across it. In
 * this last one, "some violet blue" is primarily blue and only has a little violet tinge, while "more violet blue" has
 * more violet, and "more blue violet" becomes primarily violet.
 * @deprecated This palette doesn't work as well as it should; also, the docs are all slightly wrong regarding L.
 */
@Deprecated
public class Yam2Palette {
    public static final ObjectFloatMap<String> NAMED = new ObjectFloatMap<>(256);
    public static final FloatArray LIST = new FloatArray(256);

    /**
     * This color constant "transparent" has RGBA8888 code {@code 00000000}, L 0.0, A 0.49803922, B 0.49803922, alpha 0.0, hue 0.625, saturation 0.0, and chroma 0.0055242716.
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
     * This color constant "pure black" has RGBA8888 code {@code 000000FF}, L 0.0, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, saturation 0.0, and chroma 0.0055242716.
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
     * This color constant "almost black" has RGBA8888 code {@code 0B0909FF}, L 0.06666667, A 0.5019608, B 0.49803922, alpha 1.0, hue 0.875, saturation 0.007346189, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.ff0022p125F}.
     * <pre>
     * <font style='background-color: #0B0909;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0B0909; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0B0909;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0B0909'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0B0909'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0B0909'>&nbsp;@&nbsp;</font><font style='background-color: #0B0909; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0B0909;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0B0909; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ALMOST_BLACK = -0x1.ff0022p125F;
    static { NAMED.put("almost black", -0x1.ff0022p125F); LIST.add(-0x1.ff0022p125F); }

    /**
     * This color constant "lead black" has RGBA8888 code {@code 1C1A19FF}, L 0.13725491, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, saturation 0.010973937, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.fefe46p125F}.
     * <pre>
     * <font style='background-color: #1C1A19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1C1A19; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1C1A19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1C1A19'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1C1A19'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1C1A19'>&nbsp;@&nbsp;</font><font style='background-color: #1C1A19; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1C1A19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1C1A19; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_BLACK = -0x1.fefe46p125F;
    static { NAMED.put("lead black", -0x1.fefe46p125F); LIST.add(-0x1.fefe46p125F); }

    /**
     * This color constant "black lead" has RGBA8888 code {@code 302D2CFF}, L 0.20784314, A 0.5019608, B 0.49803922, alpha 1.0, hue 0.875, saturation 0.0011072664, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.ff006ap125F}.
     * <pre>
     * <font style='background-color: #302D2C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #302D2C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #302D2C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #302D2C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #302D2C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #302D2C'>&nbsp;@&nbsp;</font><font style='background-color: #302D2C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #302D2C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #302D2C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_LEAD = -0x1.ff006ap125F;
    static { NAMED.put("black lead", -0x1.ff006ap125F); LIST.add(-0x1.ff006ap125F); }

    /**
     * This color constant "pure lead" has RGBA8888 code {@code 464241FF}, L 0.28235295, A 0.5019608, B 0.49803922, alpha 1.0, hue 0.875, saturation 6.9875096E-4, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.ff009p125F}.
     * <pre>
     * <font style='background-color: #464241;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #464241; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #464241;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #464241'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #464241'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #464241'>&nbsp;@&nbsp;</font><font style='background-color: #464241; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #464241;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #464241; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_LEAD = -0x1.ff009p125F;
    static { NAMED.put("pure lead", -0x1.ff009p125F); LIST.add(-0x1.ff009p125F); }

    /**
     * This color constant "gray lead" has RGBA8888 code {@code 5A5755FF}, L 0.3529412, A 0.49803922, B 0.5019608, alpha 1.0, hue 0.375, saturation 0.0011072664, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.00feb4p126F}.
     * <pre>
     * <font style='background-color: #5A5755;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A5755; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A5755;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5A5755'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5A5755'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5A5755'>&nbsp;@&nbsp;</font><font style='background-color: #5A5755; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A5755;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A5755; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_LEAD = -0x1.00feb4p126F;
    static { NAMED.put("gray lead", -0x1.00feb4p126F); LIST.add(-0x1.00feb4p126F); }

    /**
     * This color constant "lead gray" has RGBA8888 code {@code 6F6B6AFF}, L 0.42352942, A 0.5019608, B 0.49803922, alpha 1.0, hue 0.875, saturation 3.7021612E-4, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.ff00d8p125F}.
     * <pre>
     * <font style='background-color: #6F6B6A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6F6B6A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6F6B6A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6F6B6A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6F6B6A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6F6B6A'>&nbsp;@&nbsp;</font><font style='background-color: #6F6B6A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6F6B6A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6F6B6A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_GRAY = -0x1.ff00d8p125F;
    static { NAMED.put("lead gray", -0x1.ff00d8p125F); LIST.add(-0x1.ff00d8p125F); }

    /**
     * This color constant "pure gray" has RGBA8888 code {@code 847F7EFF}, L 0.49411765, A 0.5019608, B 0.49803922, alpha 1.0, hue 0.875, saturation 3.011028E-4, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.ff00fcp125F}.
     * <pre>
     * <font style='background-color: #847F7E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #847F7E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #847F7E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #847F7E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #847F7E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #847F7E'>&nbsp;@&nbsp;</font><font style='background-color: #847F7E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #847F7E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #847F7E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_GRAY = -0x1.ff00fcp125F;
    static { NAMED.put("pure gray", -0x1.ff00fcp125F); LIST.add(-0x1.ff00fcp125F); }

    /**
     * This color constant "silver gray" has RGBA8888 code {@code 999492FF}, L 0.5686275, A 0.5019608, B 0.5019608, alpha 1.0, hue 0.125, saturation 6.9875096E-4, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.010122p126F}.
     * <pre>
     * <font style='background-color: #999492;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #999492; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #999492;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #999492'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #999492'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #999492'>&nbsp;@&nbsp;</font><font style='background-color: #999492; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #999492;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #999492; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_GRAY = -0x1.010122p126F;
    static { NAMED.put("silver gray", -0x1.010122p126F); LIST.add(-0x1.010122p126F); }

    /**
     * This color constant "gray silver" has RGBA8888 code {@code ACA7A5FF}, L 0.6392157, A 0.5019608, B 0.5019608, alpha 1.0, hue 0.125, saturation 0.0010099735, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.010146p126F}.
     * <pre>
     * <font style='background-color: #ACA7A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ACA7A5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ACA7A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ACA7A5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ACA7A5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ACA7A5'>&nbsp;@&nbsp;</font><font style='background-color: #ACA7A5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ACA7A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ACA7A5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_SILVER = -0x1.010146p126F;
    static { NAMED.put("gray silver", -0x1.010146p126F); LIST.add(-0x1.010146p126F); }

    /**
     * This color constant "pure silver" has RGBA8888 code {@code BEB9B7FF}, L 0.70980394, A 0.5019608, B 0.5019608, alpha 1.0, hue 0.125, saturation 0.0017821341, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.01016ap126F}.
     * <pre>
     * <font style='background-color: #BEB9B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BEB9B7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BEB9B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BEB9B7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BEB9B7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BEB9B7'>&nbsp;@&nbsp;</font><font style='background-color: #BEB9B7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BEB9B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BEB9B7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_SILVER = -0x1.01016ap126F;
    static { NAMED.put("pure silver", -0x1.01016ap126F); LIST.add(-0x1.01016ap126F); }

    /**
     * This color constant "white silver" has RGBA8888 code {@code D1CCCAFF}, L 0.78431374, A 0.5019608, B 0.49803922, alpha 1.0, hue 0.875, saturation 0.0021499598, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.ff019p125F}.
     * <pre>
     * <font style='background-color: #D1CCCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D1CCCA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D1CCCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D1CCCA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D1CCCA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D1CCCA'>&nbsp;@&nbsp;</font><font style='background-color: #D1CCCA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D1CCCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D1CCCA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_SILVER = -0x1.ff019p125F;
    static { NAMED.put("white silver", -0x1.ff019p125F); LIST.add(-0x1.ff019p125F); }

    /**
     * This color constant "silver white" has RGBA8888 code {@code E2DDDBFF}, L 0.85490197, A 0.5019608, B 0.49803922, alpha 1.0, hue 0.875, saturation 0.004759072, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.ff01b4p125F}.
     * <pre>
     * <font style='background-color: #E2DDDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E2DDDB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E2DDDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E2DDDB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E2DDDB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E2DDDB'>&nbsp;@&nbsp;</font><font style='background-color: #E2DDDB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E2DDDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E2DDDB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_WHITE = -0x1.ff01b4p125F;
    static { NAMED.put("silver white", -0x1.ff01b4p125F); LIST.add(-0x1.ff01b4p125F); }

    /**
     * This color constant "almost white" has RGBA8888 code {@code F3EEEBFF}, L 0.9254902, A 0.49803922, B 0.5019608, alpha 1.0, hue 0.375, saturation 0.0022981903, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.00ffd8p126F}.
     * <pre>
     * <font style='background-color: #F3EEEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3EEEB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3EEEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F3EEEB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F3EEEB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F3EEEB'>&nbsp;@&nbsp;</font><font style='background-color: #F3EEEB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3EEEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3EEEB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ALMOST_WHITE = -0x1.00ffd8p126F;
    static { NAMED.put("almost white", -0x1.00ffd8p126F); LIST.add(-0x1.00ffd8p126F); }

    /**
     * This color constant "pure white" has RGBA8888 code {@code FFFDFBFF}, L 0.99215686, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, saturation 0.09876543, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.fefffap125F}.
     * <pre>
     * <font style='background-color: #FFFDFB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFDFB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFDFB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFDFB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFDFB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFDFB'>&nbsp;@&nbsp;</font><font style='background-color: #FFFDFB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFDFB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFDFB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_WHITE = -0x1.fefffap125F;
    static { NAMED.put("pure white", -0x1.fefffap125F); LIST.add(-0x1.fefffap125F); }

    /**
     * This color constant "darker gray red" has RGBA8888 code {@code 3F1613FF}, L 0.1764706, A 0.53333336, B 0.5176471, alpha 1.0, hue 0.07749419, saturation 0.3728899, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.09105ap126F}.
     * <pre>
     * <font style='background-color: #3F1613;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F1613; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F1613;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3F1613'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3F1613'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3F1613'>&nbsp;@&nbsp;</font><font style='background-color: #3F1613; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F1613;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F1613; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_RED = -0x1.09105ap126F;
    static { NAMED.put("darker gray red", -0x1.09105ap126F); LIST.add(-0x1.09105ap126F); }

    /**
     * This color constant "dark gray red" has RGBA8888 code {@code 804A43FF}, L 0.36078432, A 0.53333336, B 0.5176471, alpha 1.0, hue 0.07749419, saturation 0.12456864, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.0910b8p126F}.
     * <pre>
     * <font style='background-color: #804A43;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #804A43; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #804A43;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #804A43'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #804A43'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #804A43'>&nbsp;@&nbsp;</font><font style='background-color: #804A43; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #804A43;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #804A43; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_RED = -0x1.0910b8p126F;
    static { NAMED.put("dark gray red", -0x1.0910b8p126F); LIST.add(-0x1.0910b8p126F); }

    /**
     * This color constant "light gray red" has RGBA8888 code {@code BD7E75FF}, L 0.54901963, A 0.53333336, B 0.5176471, alpha 1.0, hue 0.07749419, saturation 0.11190926, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.091118p126F}.
     * <pre>
     * <font style='background-color: #BD7E75;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD7E75; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD7E75;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BD7E75'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BD7E75'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BD7E75'>&nbsp;@&nbsp;</font><font style='background-color: #BD7E75; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD7E75;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD7E75; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_RED = -0x1.091118p126F;
    static { NAMED.put("light gray red", -0x1.091118p126F); LIST.add(-0x1.091118p126F); }

    /**
     * This color constant "lighter gray red" has RGBA8888 code {@code F3ADA4FF}, L 0.73333335, A 0.53333336, B 0.5137255, alpha 1.0, hue 0.062156405, saturation 0.41612804, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.071176p126F}.
     * <pre>
     * <font style='background-color: #F3ADA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3ADA4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3ADA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F3ADA4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F3ADA4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F3ADA4'>&nbsp;@&nbsp;</font><font style='background-color: #F3ADA4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3ADA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3ADA4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_RED = -0x1.071176p126F;
    static { NAMED.put("lighter gray red", -0x1.071176p126F); LIST.add(-0x1.071176p126F); }

    /**
     * This color constant "darker gray brown" has RGBA8888 code {@code 38241CFF}, L 0.19215687, A 0.5137255, B 0.5137255, alpha 1.0, hue 0.125, saturation 0.17745586, and chroma 0.038669903.
     * It can be represented as a packed float with the constant {@code -0x1.070662p126F}.
     * <pre>
     * <font style='background-color: #38241C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #38241C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #38241C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #38241C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #38241C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #38241C'>&nbsp;@&nbsp;</font><font style='background-color: #38241C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #38241C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #38241C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_BROWN = -0x1.070662p126F;
    static { NAMED.put("darker gray brown", -0x1.070662p126F); LIST.add(-0x1.070662p126F); }

    /**
     * This color constant "dark gray brown" has RGBA8888 code {@code 745A4FFF}, L 0.38431373, A 0.5137255, B 0.5137255, alpha 1.0, hue 0.125, saturation 0.059746988, and chroma 0.038669903.
     * It can be represented as a packed float with the constant {@code -0x1.0706c4p126F}.
     * <pre>
     * <font style='background-color: #745A4F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #745A4F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #745A4F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #745A4F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #745A4F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #745A4F'>&nbsp;@&nbsp;</font><font style='background-color: #745A4F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #745A4F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #745A4F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_BROWN = -0x1.0706c4p126F;
    static { NAMED.put("dark gray brown", -0x1.0706c4p126F); LIST.add(-0x1.0706c4p126F); }

    /**
     * This color constant "light gray brown" has RGBA8888 code {@code AE9083FF}, L 0.5764706, A 0.50980395, B 0.5137255, alpha 1.0, hue 0.15127131, saturation 0.032797784, and chroma 0.033602834.
     * It can be represented as a packed float with the constant {@code -0x1.070526p126F}.
     * <pre>
     * <font style='background-color: #AE9083;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE9083; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE9083;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AE9083'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AE9083'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AE9083'>&nbsp;@&nbsp;</font><font style='background-color: #AE9083; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE9083;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE9083; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_BROWN = -0x1.070526p126F;
    static { NAMED.put("light gray brown", -0x1.070526p126F); LIST.add(-0x1.070526p126F); }

    /**
     * This color constant "lighter gray brown" has RGBA8888 code {@code E3C2B3FF}, L 0.76862746, A 0.50980395, B 0.5137255, alpha 1.0, hue 0.15127131, saturation 0.09785124, and chroma 0.033602834.
     * It can be represented as a packed float with the constant {@code -0x1.070588p126F}.
     * <pre>
     * <font style='background-color: #E3C2B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3C2B3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3C2B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E3C2B3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E3C2B3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E3C2B3'>&nbsp;@&nbsp;</font><font style='background-color: #E3C2B3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3C2B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3C2B3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_BROWN = -0x1.070588p126F;
    static { NAMED.put("lighter gray brown", -0x1.070588p126F); LIST.add(-0x1.070588p126F); }

    /**
     * This color constant "darker gray orange" has RGBA8888 code {@code 3E2314FF}, L 0.19607843, A 0.5176471, B 0.52156866, alpha 1.0, hue 0.14085212, saturation 0.39901236, and chroma 0.055518243.
     * It can be represented as a packed float with the constant {@code -0x1.0b0864p126F}.
     * <pre>
     * <font style='background-color: #3E2314;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3E2314; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3E2314;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3E2314'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3E2314'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3E2314'>&nbsp;@&nbsp;</font><font style='background-color: #3E2314; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3E2314;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3E2314; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_ORANGE = -0x1.0b0864p126F;
    static { NAMED.put("darker gray orange", -0x1.0b0864p126F); LIST.add(-0x1.0b0864p126F); }

    /**
     * This color constant "dark gray orange" has RGBA8888 code {@code 7F5B48FF}, L 0.39607844, A 0.5176471, B 0.52156866, alpha 1.0, hue 0.14085212, saturation 0.1362793, and chroma 0.055518243.
     * It can be represented as a packed float with the constant {@code -0x1.0b08cap126F}.
     * <pre>
     * <font style='background-color: #7F5B48;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F5B48; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F5B48;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7F5B48'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7F5B48'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7F5B48'>&nbsp;@&nbsp;</font><font style='background-color: #7F5B48; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F5B48;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F5B48; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_ORANGE = -0x1.0b08cap126F;
    static { NAMED.put("dark gray orange", -0x1.0b08cap126F); LIST.add(-0x1.0b08cap126F); }

    /**
     * This color constant "light gray orange" has RGBA8888 code {@code BC937CFF}, L 0.59607846, A 0.5137255, B 0.52156866, alpha 1.0, hue 0.15979148, saturation 0.07534626, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.0b073p126F}.
     * <pre>
     * <font style='background-color: #BC937C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BC937C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BC937C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BC937C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BC937C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BC937C'>&nbsp;@&nbsp;</font><font style='background-color: #BC937C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BC937C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BC937C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_ORANGE = -0x1.0b073p126F;
    static { NAMED.put("light gray orange", -0x1.0b073p126F); LIST.add(-0x1.0b073p126F); }

    /**
     * This color constant "lighter gray orange" has RGBA8888 code {@code F3C6ADFF}, L 0.79607844, A 0.5137255, B 0.52156866, alpha 1.0, hue 0.15979148, saturation 0.26143792, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.0b0796p126F}.
     * <pre>
     * <font style='background-color: #F3C6AD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3C6AD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3C6AD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F3C6AD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F3C6AD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F3C6AD'>&nbsp;@&nbsp;</font><font style='background-color: #F3C6AD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3C6AD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3C6AD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_ORANGE = -0x1.0b0796p126F;
    static { NAMED.put("lighter gray orange", -0x1.0b0796p126F); LIST.add(-0x1.0b0796p126F); }

    /**
     * This color constant "darker gray saffron" has RGBA8888 code {@code 3C2D19FF}, L 0.21568628, A 0.5058824, B 0.52156866, alpha 1.0, hue 0.20763123, saturation 0.30933967, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.0b026ep126F}.
     * <pre>
     * <font style='background-color: #3C2D19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C2D19; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C2D19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3C2D19'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3C2D19'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3C2D19'>&nbsp;@&nbsp;</font><font style='background-color: #3C2D19; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C2D19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C2D19; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_SAFFRON = -0x1.0b026ep126F;
    static { NAMED.put("darker gray saffron", -0x1.0b026ep126F); LIST.add(-0x1.0b026ep126F); }

    /**
     * This color constant "dark gray saffron" has RGBA8888 code {@code 7B684FFF}, L 0.41960785, A 0.5019608, B 0.52156866, alpha 1.0, hue 0.235567, saturation 0.11550296, and chroma 0.04314594.
     * It can be represented as a packed float with the constant {@code -0x1.0b00d6p126F}.
     * <pre>
     * <font style='background-color: #7B684F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B684F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B684F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7B684F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7B684F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7B684F'>&nbsp;@&nbsp;</font><font style='background-color: #7B684F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B684F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B684F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_SAFFRON = -0x1.0b00d6p126F;
    static { NAMED.put("dark gray saffron", -0x1.0b00d6p126F); LIST.add(-0x1.0b00d6p126F); }

    /**
     * This color constant "light gray saffron" has RGBA8888 code {@code B59F83FF}, L 0.61960787, A 0.5058824, B 0.5176471, alpha 1.0, hue 0.19880433, saturation 0.047562424, and chroma 0.03705794.
     * It can be represented as a packed float with the constant {@code -0x1.09033cp126F}.
     * <pre>
     * <font style='background-color: #B59F83;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B59F83; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B59F83;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B59F83'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B59F83'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B59F83'>&nbsp;@&nbsp;</font><font style='background-color: #B59F83; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B59F83;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B59F83; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_SAFFRON = -0x1.09033cp126F;
    static { NAMED.put("light gray saffron", -0x1.09033cp126F); LIST.add(-0x1.09033cp126F); }

    /**
     * This color constant "lighter gray saffron" has RGBA8888 code {@code EBD3B4FF}, L 0.8235294, A 0.5058824, B 0.52156866, alpha 1.0, hue 0.20763123, saturation 0.16004924, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.0b03a4p126F}.
     * <pre>
     * <font style='background-color: #EBD3B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBD3B4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBD3B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EBD3B4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EBD3B4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EBD3B4'>&nbsp;@&nbsp;</font><font style='background-color: #EBD3B4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBD3B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBD3B4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_SAFFRON = -0x1.0b03a4p126F;
    static { NAMED.put("lighter gray saffron", -0x1.0b03a4p126F); LIST.add(-0x1.0b03a4p126F); }

    /**
     * This color constant "darker gray yellow" has RGBA8888 code {@code 383814FF}, L 0.23529412, A 0.4862745, B 0.5294118, alpha 1.0, hue 0.31948605, saturation 0.4961521, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.0ef878p126F}.
     * <pre>
     * <font style='background-color: #383814;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #383814; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #383814;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #383814'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #383814'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #383814'>&nbsp;@&nbsp;</font><font style='background-color: #383814; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #383814;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #383814; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_YELLOW = -0x1.0ef878p126F;
    static { NAMED.put("darker gray yellow", -0x1.0ef878p126F); LIST.add(-0x1.0ef878p126F); }

    /**
     * This color constant "dark gray yellow" has RGBA8888 code {@code 77774DFF}, L 0.4509804, A 0.49019608, B 0.5254902, alpha 1.0, hue 0.30842525, saturation 0.14561832, and chroma 0.054407768.
     * It can be represented as a packed float with the constant {@code -0x1.0cfae6p126F}.
     * <pre>
     * <font style='background-color: #77774D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #77774D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #77774D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #77774D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #77774D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #77774D'>&nbsp;@&nbsp;</font><font style='background-color: #77774D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #77774D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #77774D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_YELLOW = -0x1.0cfae6p126F;
    static { NAMED.put("dark gray yellow", -0x1.0cfae6p126F); LIST.add(-0x1.0cfae6p126F); }

    /**
     * This color constant "light gray yellow" has RGBA8888 code {@code B2B282FF}, L 0.6627451, A 0.49019608, B 0.5294118, alpha 1.0, hue 0.30119568, saturation 0.1156203, and chroma 0.061763234.
     * It can be represented as a packed float with the constant {@code -0x1.0efb52p126F}.
     * <pre>
     * <font style='background-color: #B2B282;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2B282; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2B282;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B2B282'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B2B282'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B2B282'>&nbsp;@&nbsp;</font><font style='background-color: #B2B282; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2B282;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2B282; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_YELLOW = -0x1.0efb52p126F;
    static { NAMED.put("light gray yellow", -0x1.0efb52p126F); LIST.add(-0x1.0efb52p126F); }

    /**
     * This color constant "lighter gray yellow" has RGBA8888 code {@code E7E8B4FF}, L 0.8784314, A 0.4862745, B 0.5254902, alpha 1.0, hue 0.32861596, saturation 0.061577573, and chroma 0.05767509.
     * It can be represented as a packed float with the constant {@code -0x1.0cf9cp126F}.
     * <pre>
     * <font style='background-color: #E7E8B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E7E8B4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E7E8B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E7E8B4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E7E8B4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E7E8B4'>&nbsp;@&nbsp;</font><font style='background-color: #E7E8B4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E7E8B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E7E8B4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_YELLOW = -0x1.0cf9cp126F;
    static { NAMED.put("lighter gray yellow", -0x1.0cf9cp126F); LIST.add(-0x1.0cf9cp126F); }

    /**
     * This color constant "darker gray lime" has RGBA8888 code {@code 293918FF}, L 0.22745098, A 0.4745098, B 0.5254902, alpha 1.0, hue 0.375, saturation 0.36334318, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.0cf274p126F}.
     * <pre>
     * <font style='background-color: #293918;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #293918; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #293918;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #293918'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #293918'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #293918'>&nbsp;@&nbsp;</font><font style='background-color: #293918; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #293918;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #293918; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_LIME = -0x1.0cf274p126F;
    static { NAMED.put("darker gray lime", -0x1.0cf274p126F); LIST.add(-0x1.0cf274p126F); }

    /**
     * This color constant "dark gray lime" has RGBA8888 code {@code 647950FF}, L 0.4392157, A 0.47843137, B 0.5254902, alpha 1.0, hue 0.361777, saturation 0.14644615, and chroma 0.06652104.
     * It can be represented as a packed float with the constant {@code -0x1.0cf4ep126F}.
     * <pre>
     * <font style='background-color: #647950;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #647950; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #647950;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #647950'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #647950'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #647950'>&nbsp;@&nbsp;</font><font style='background-color: #647950; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #647950;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #647950; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_LIME = -0x1.0cf4ep126F;
    static { NAMED.put("dark gray lime", -0x1.0cf4ep126F); LIST.add(-0x1.0cf4ep126F); }

    /**
     * This color constant "light gray lime" has RGBA8888 code {@code 9CB586FF}, L 0.6509804, A 0.4745098, B 0.5254902, alpha 1.0, hue 0.375, saturation 0.083824165, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.0cf34cp126F}.
     * <pre>
     * <font style='background-color: #9CB586;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9CB586; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9CB586;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9CB586'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9CB586'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9CB586'>&nbsp;@&nbsp;</font><font style='background-color: #9CB586; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9CB586;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9CB586; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_LIME = -0x1.0cf34cp126F;
    static { NAMED.put("light gray lime", -0x1.0cf34cp126F); LIST.add(-0x1.0cf34cp126F); }

    /**
     * This color constant "lighter gray lime" has RGBA8888 code {@code CFEAB7FF}, L 0.85882354, A 0.4745098, B 0.5254902, alpha 1.0, hue 0.375, saturation 0.10223062, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.0cf3b6p126F}.
     * <pre>
     * <font style='background-color: #CFEAB7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CFEAB7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CFEAB7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CFEAB7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CFEAB7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CFEAB7'>&nbsp;@&nbsp;</font><font style='background-color: #CFEAB7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CFEAB7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CFEAB7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_LIME = -0x1.0cf3b6p126F;
    static { NAMED.put("lighter gray lime", -0x1.0cf3b6p126F); LIST.add(-0x1.0cf3b6p126F); }

    /**
     * This color constant "darker gray green" has RGBA8888 code {@code 14391AFF}, L 0.21568628, A 0.4627451, B 0.52156866, alpha 1.0, hue 0.41646945, saturation 0.7412534, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.0aec6ep126F}.
     * <pre>
     * <font style='background-color: #14391A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #14391A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #14391A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #14391A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #14391A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #14391A'>&nbsp;@&nbsp;</font><font style='background-color: #14391A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #14391A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #14391A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_GREEN = -0x1.0aec6ep126F;
    static { NAMED.put("darker gray green", -0x1.0aec6ep126F); LIST.add(-0x1.0aec6ep126F); }

    /**
     * This color constant "dark gray green" has RGBA8888 code {@code 457049FF}, L 0.39215687, A 0.4627451, B 0.52156866, alpha 1.0, hue 0.41646945, saturation 0.29385763, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.0aecc8p126F}.
     * <pre>
     * <font style='background-color: #457049;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #457049; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #457049;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #457049'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #457049'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #457049'>&nbsp;@&nbsp;</font><font style='background-color: #457049; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #457049;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #457049; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_GREEN = -0x1.0aecc8p126F;
    static { NAMED.put("dark gray green", -0x1.0aecc8p126F); LIST.add(-0x1.0aecc8p126F); }

    /**
     * This color constant "light gray green" has RGBA8888 code {@code 73A478FF}, L 0.5686275, A 0.4627451, B 0.52156866, alpha 1.0, hue 0.41646945, saturation 0.17487529, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.0aed22p126F}.
     * <pre>
     * <font style='background-color: #73A478;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73A478; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73A478;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #73A478'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #73A478'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #73A478'>&nbsp;@&nbsp;</font><font style='background-color: #73A478; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73A478;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73A478; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_GREEN = -0x1.0aed22p126F;
    static { NAMED.put("light gray green", -0x1.0aed22p126F); LIST.add(-0x1.0aed22p126F); }

    /**
     * This color constant "lighter gray green" has RGBA8888 code {@code 9FD4A4FF}, L 0.74509805, A 0.4627451, B 0.52156866, alpha 1.0, hue 0.41646945, saturation 0.123392, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.0aed7cp126F}.
     * <pre>
     * <font style='background-color: #9FD4A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9FD4A4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9FD4A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9FD4A4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9FD4A4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9FD4A4'>&nbsp;@&nbsp;</font><font style='background-color: #9FD4A4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9FD4A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9FD4A4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_GREEN = -0x1.0aed7cp126F;
    static { NAMED.put("lighter gray green", -0x1.0aed7cp126F); LIST.add(-0x1.0aed7cp126F); }

    /**
     * This color constant "darker gray cyan" has RGBA8888 code {@code 193B3BFF}, L 0.23137255, A 0.4745098, B 0.49019608, alpha 1.0, hue 0.55842525, saturation 0.6334694, and chroma 0.054407768.
     * It can be represented as a packed float with the constant {@code -0x1.faf276p125F}.
     * <pre>
     * <font style='background-color: #193B3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #193B3B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #193B3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #193B3B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #193B3B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #193B3B'>&nbsp;@&nbsp;</font><font style='background-color: #193B3B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #193B3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #193B3B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_CYAN = -0x1.faf276p125F;
    static { NAMED.put("darker gray cyan", -0x1.faf276p125F); LIST.add(-0x1.faf276p125F); }

    /**
     * This color constant "dark gray cyan" has RGBA8888 code {@code 527979FF}, L 0.4392157, A 0.47843137, B 0.49019608, alpha 1.0, hue 0.56789327, saturation 0.17974761, and chroma 0.0471994.
     * It can be represented as a packed float with the constant {@code -0x1.faf4ep125F}.
     * <pre>
     * <font style='background-color: #527979;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #527979; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #527979;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #527979'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #527979'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #527979'>&nbsp;@&nbsp;</font><font style='background-color: #527979; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #527979;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #527979; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_CYAN = -0x1.faf4ep125F;
    static { NAMED.put("dark gray cyan", -0x1.faf4ep125F); LIST.add(-0x1.faf4ep125F); }

    /**
     * This color constant "light gray cyan" has RGBA8888 code {@code 89B6B5FF}, L 0.6509804, A 0.4745098, B 0.49411765, alpha 1.0, hue 0.5360971, saturation 0.13360855, and chroma 0.052115876.
     * It can be represented as a packed float with the constant {@code -0x1.fcf34cp125F}.
     * <pre>
     * <font style='background-color: #89B6B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #89B6B5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #89B6B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #89B6B5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #89B6B5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #89B6B5'>&nbsp;@&nbsp;</font><font style='background-color: #89B6B5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #89B6B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #89B6B5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_CYAN = -0x1.fcf34cp125F;
    static { NAMED.put("light gray cyan", -0x1.fcf34cp125F); LIST.add(-0x1.fcf34cp125F); }

    /**
     * This color constant "lighter gray cyan" has RGBA8888 code {@code BBEBEAFF}, L 0.8627451, A 0.4745098, B 0.49411765, alpha 1.0, hue 0.5360971, saturation 0.11408428, and chroma 0.052115876.
     * It can be represented as a packed float with the constant {@code -0x1.fcf3b8p125F}.
     * <pre>
     * <font style='background-color: #BBEBEA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BBEBEA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BBEBEA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BBEBEA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BBEBEA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BBEBEA'>&nbsp;@&nbsp;</font><font style='background-color: #BBEBEA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BBEBEA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BBEBEA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_CYAN = -0x1.fcf3b8p125F;
    static { NAMED.put("lighter gray cyan", -0x1.fcf3b8p125F); LIST.add(-0x1.fcf3b8p125F); }

    /**
     * This color constant "darker gray blue" has RGBA8888 code {@code 0A1A42FF}, L 0.14901961, A 0.49019608, B 0.4509804, alpha 1.0, hue 0.71857655, saturation 0.6987369, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.e6fa4cp125F}.
     * <pre>
     * <font style='background-color: #0A1A42;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0A1A42; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0A1A42;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0A1A42'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0A1A42'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0A1A42'>&nbsp;@&nbsp;</font><font style='background-color: #0A1A42; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0A1A42;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0A1A42; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_BLUE = -0x1.e6fa4cp125F;
    static { NAMED.put("darker gray blue", -0x1.e6fa4cp125F); LIST.add(-0x1.e6fa4cp125F); }

    /**
     * This color constant "dark gray blue" has RGBA8888 code {@code 364F82FF}, L 0.3254902, A 0.49019608, B 0.4509804, alpha 1.0, hue 0.71857655, saturation 0.2188368, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.e6faa6p125F}.
     * <pre>
     * <font style='background-color: #364F82;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #364F82; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #364F82;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #364F82'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #364F82'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #364F82'>&nbsp;@&nbsp;</font><font style='background-color: #364F82; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #364F82;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #364F82; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_BLUE = -0x1.e6faa6p125F;
    static { NAMED.put("dark gray blue", -0x1.e6faa6p125F); LIST.add(-0x1.e6faa6p125F); }

    /**
     * This color constant "light gray blue" has RGBA8888 code {@code 6583BDFF}, L 0.5019608, A 0.49019608, B 0.45490196, alpha 1.0, hue 0.7159276, saturation 0.19355403, and chroma 0.091942206.
     * It can be represented as a packed float with the constant {@code -0x1.e8fbp125F}.
     * <pre>
     * <font style='background-color: #6583BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6583BD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6583BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6583BD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6583BD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6583BD'>&nbsp;@&nbsp;</font><font style='background-color: #6583BD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6583BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6583BD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_BLUE = -0x1.e8fbp125F;
    static { NAMED.put("light gray blue", -0x1.e8fbp125F); LIST.add(-0x1.e8fbp125F); }

    /**
     * This color constant "lighter gray blue" has RGBA8888 code {@code 92B3F4FF}, L 0.6784314, A 0.49411765, B 0.4509804, alpha 1.0, hue 0.73098123, saturation 0.6389519, and chroma 0.098356865.
     * It can be represented as a packed float with the constant {@code -0x1.e6fd5ap125F}.
     * <pre>
     * <font style='background-color: #92B3F4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #92B3F4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #92B3F4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #92B3F4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #92B3F4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #92B3F4'>&nbsp;@&nbsp;</font><font style='background-color: #92B3F4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #92B3F4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #92B3F4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_BLUE = -0x1.e6fd5ap125F;
    static { NAMED.put("lighter gray blue", -0x1.e6fd5ap125F); LIST.add(-0x1.e6fd5ap125F); }

    /**
     * This color constant "darker gray violet" has RGBA8888 code {@code 271B3EFF}, L 0.16862746, A 0.5137255, B 0.4627451, alpha 1.0, hue 0.80616736, saturation 0.2499619, and chroma 0.079095535.
     * It can be represented as a packed float with the constant {@code -0x1.ed0656p125F}.
     * <pre>
     * <font style='background-color: #271B3E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #271B3E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #271B3E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #271B3E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #271B3E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #271B3E'>&nbsp;@&nbsp;</font><font style='background-color: #271B3E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #271B3E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #271B3E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_VIOLET = -0x1.ed0656p125F;
    static { NAMED.put("darker gray violet", -0x1.ed0656p125F); LIST.add(-0x1.ed0656p125F); }

    /**
     * This color constant "dark gray violet" has RGBA8888 code {@code 5D4F7DFF}, L 0.3529412, A 0.5176471, B 0.4627451, alpha 1.0, hue 0.8204015, saturation 0.08892913, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.ed08b4p125F}.
     * <pre>
     * <font style='background-color: #5D4F7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5D4F7D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5D4F7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5D4F7D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5D4F7D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5D4F7D'>&nbsp;@&nbsp;</font><font style='background-color: #5D4F7D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5D4F7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5D4F7D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_VIOLET = -0x1.ed08b4p125F;
    static { NAMED.put("dark gray violet", -0x1.ed08b4p125F); LIST.add(-0x1.ed08b4p125F); }

    /**
     * This color constant "light gray violet" has RGBA8888 code {@code 9182B7FF}, L 0.53333336, A 0.5137255, B 0.4627451, alpha 1.0, hue 0.80616736, saturation 0.14324395, and chroma 0.079095535.
     * It can be represented as a packed float with the constant {@code -0x1.ed071p125F}.
     * <pre>
     * <font style='background-color: #9182B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9182B7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9182B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9182B7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9182B7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9182B7'>&nbsp;@&nbsp;</font><font style='background-color: #9182B7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9182B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9182B7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_VIOLET = -0x1.ed071p125F;
    static { NAMED.put("light gray violet", -0x1.ed071p125F); LIST.add(-0x1.ed071p125F); }

    /**
     * This color constant "lighter gray violet" has RGBA8888 code {@code C3B2EDFF}, L 0.7176471, A 0.5176471, B 0.4627451, alpha 1.0, hue 0.8204015, saturation 0.47514108, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.ed096ep125F}.
     * <pre>
     * <font style='background-color: #C3B2ED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3B2ED; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3B2ED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C3B2ED'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C3B2ED'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C3B2ED'>&nbsp;@&nbsp;</font><font style='background-color: #C3B2ED; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3B2ED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3B2ED; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_VIOLET = -0x1.ed096ep125F;
    static { NAMED.put("lighter gray violet", -0x1.ed096ep125F); LIST.add(-0x1.ed096ep125F); }

    /**
     * This color constant "darker gray purple" has RGBA8888 code {@code 321941FF}, L 0.18039216, A 0.5294118, B 0.46666667, alpha 1.0, hue 0.8650731, saturation 0.3467701, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.ef0e5cp125F}.
     * <pre>
     * <font style='background-color: #321941;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #321941; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #321941;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #321941'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #321941'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #321941'>&nbsp;@&nbsp;</font><font style='background-color: #321941; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #321941;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #321941; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_PURPLE = -0x1.ef0e5cp125F;
    static { NAMED.put("darker gray purple", -0x1.ef0e5cp125F); LIST.add(-0x1.ef0e5cp125F); }

    /**
     * This color constant "dark gray purple" has RGBA8888 code {@code 6D4E81FF}, L 0.36862746, A 0.5294118, B 0.46666667, alpha 1.0, hue 0.8650731, saturation 0.11281207, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.ef0ebcp125F}.
     * <pre>
     * <font style='background-color: #6D4E81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D4E81; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D4E81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6D4E81'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6D4E81'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6D4E81'>&nbsp;@&nbsp;</font><font style='background-color: #6D4E81; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D4E81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D4E81; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_PURPLE = -0x1.ef0ebcp125F;
    static { NAMED.put("dark gray purple", -0x1.ef0ebcp125F); LIST.add(-0x1.ef0ebcp125F); }

    /**
     * This color constant "light gray purple" has RGBA8888 code {@code A683BEFF}, L 0.56078434, A 0.5254902, B 0.4627451, alpha 1.0, hue 0.84551346, saturation 0.16030246, and chroma 0.08992863.
     * It can be represented as a packed float with the constant {@code -0x1.ed0d1ep125F}.
     * <pre>
     * <font style='background-color: #A683BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A683BE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A683BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A683BE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A683BE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A683BE'>&nbsp;@&nbsp;</font><font style='background-color: #A683BE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A683BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A683BE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_PURPLE = -0x1.ed0d1ep125F;
    static { NAMED.put("light gray purple", -0x1.ed0d1ep125F); LIST.add(-0x1.ed0d1ep125F); }

    /**
     * This color constant "lighter gray purple" has RGBA8888 code {@code DAB3F4FF}, L 0.7490196, A 0.5294118, B 0.4627451, alpha 1.0, hue 0.8563733, saturation 0.59057695, and chroma 0.0945603.
     * It can be represented as a packed float with the constant {@code -0x1.ed0f7ep125F}.
     * <pre>
     * <font style='background-color: #DAB3F4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAB3F4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAB3F4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DAB3F4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DAB3F4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DAB3F4'>&nbsp;@&nbsp;</font><font style='background-color: #DAB3F4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAB3F4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAB3F4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_PURPLE = -0x1.ed0f7ep125F;
    static { NAMED.put("lighter gray purple", -0x1.ed0f7ep125F); LIST.add(-0x1.ed0f7ep125F); }

    /**
     * This color constant "darker gray magenta" has RGBA8888 code {@code 3B1C3BFF}, L 0.19215687, A 0.53333336, B 0.47843137, alpha 1.0, hue 0.90858525, saturation 0.29155555, and chroma 0.079095535.
     * It can be represented as a packed float with the constant {@code -0x1.f51062p125F}.
     * <pre>
     * <font style='background-color: #3B1C3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B1C3B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B1C3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B1C3B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B1C3B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B1C3B'>&nbsp;@&nbsp;</font><font style='background-color: #3B1C3B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B1C3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B1C3B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_MAGENTA = -0x1.f51062p125F;
    static { NAMED.put("darker gray magenta", -0x1.f51062p125F); LIST.add(-0x1.f51062p125F); }

    /**
     * This color constant "dark gray magenta" has RGBA8888 code {@code 795179FF}, L 0.38431373, A 0.53333336, B 0.4745098, alpha 1.0, hue 0.89608383, saturation 0.106753685, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.f310c4p125F}.
     * <pre>
     * <font style='background-color: #795179;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #795179; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #795179;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #795179'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #795179'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #795179'>&nbsp;@&nbsp;</font><font style='background-color: #795179; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #795179;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #795179; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_MAGENTA = -0x1.f310c4p125F;
    static { NAMED.put("dark gray magenta", -0x1.f310c4p125F); LIST.add(-0x1.f310c4p125F); }

    /**
     * This color constant "light gray magenta" has RGBA8888 code {@code B587B4FF}, L 0.5803922, A 0.5294118, B 0.47843137, alpha 1.0, hue 0.899282, saturation 0.053393003, and chroma 0.07266045.
     * It can be represented as a packed float with the constant {@code -0x1.f50f28p125F}.
     * <pre>
     * <font style='background-color: #B587B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B587B4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B587B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B587B4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B587B4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B587B4'>&nbsp;@&nbsp;</font><font style='background-color: #B587B4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B587B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B587B4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_MAGENTA = -0x1.f50f28p125F;
    static { NAMED.put("light gray magenta", -0x1.f50f28p125F); LIST.add(-0x1.f50f28p125F); }

    /**
     * This color constant "lighter gray magenta" has RGBA8888 code {@code EAB7E9FF}, L 0.77254903, A 0.53333336, B 0.4745098, alpha 1.0, hue 0.89608383, saturation 0.3256889, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.f3118ap125F}.
     * <pre>
     * <font style='background-color: #EAB7E9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EAB7E9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EAB7E9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EAB7E9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EAB7E9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EAB7E9'>&nbsp;@&nbsp;</font><font style='background-color: #EAB7E9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EAB7E9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EAB7E9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_MAGENTA = -0x1.f3118ap125F;
    static { NAMED.put("lighter gray magenta", -0x1.f3118ap125F); LIST.add(-0x1.f3118ap125F); }

    /**
     * This color constant "drab brown red" has RGBA8888 code {@code 7B231AFF}, L 0.28627452, A 0.5568628, B 0.5294118, alpha 1.0, hue 0.075972304, saturation 0.5149137, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.0f1c92p126F}.
     * <pre>
     * <font style='background-color: #7B231A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B231A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B231A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7B231A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7B231A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7B231A'>&nbsp;@&nbsp;</font><font style='background-color: #7B231A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B231A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B231A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BROWN_RED = -0x1.0f1c92p126F;
    static { NAMED.put("drab brown red", -0x1.0f1c92p126F); LIST.add(-0x1.0f1c92p126F); }

    /**
     * This color constant "dull brown red" has RGBA8888 code {@code B95344FF}, L 0.4509804, A 0.5568628, B 0.53333336, alpha 1.0, hue 0.084393784, saturation 0.28928, and chroma 0.13131043.
     * It can be represented as a packed float with the constant {@code -0x1.111ce6p126F}.
     * <pre>
     * <font style='background-color: #B95344;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B95344; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B95344;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B95344'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B95344'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B95344'>&nbsp;@&nbsp;</font><font style='background-color: #B95344; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B95344;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B95344; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_BROWN_RED = -0x1.111ce6p126F;
    static { NAMED.put("dull brown red", -0x1.111ce6p126F); LIST.add(-0x1.111ce6p126F); }

    /**
     * This color constant "pale brown red" has RGBA8888 code {@code EF7E6DFF}, L 0.6117647, A 0.5568628, B 0.5294118, alpha 1.0, hue 0.075972304, saturation 0.5149137, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.0f1d38p126F}.
     * <pre>
     * <font style='background-color: #EF7E6D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EF7E6D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EF7E6D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EF7E6D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EF7E6D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EF7E6D'>&nbsp;@&nbsp;</font><font style='background-color: #EF7E6D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EF7E6D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EF7E6D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BROWN_RED = -0x1.0f1d38p126F;
    static { NAMED.put("pale brown red", -0x1.0f1d38p126F); LIST.add(-0x1.0f1d38p126F); }

    /**
     * This color constant "drab red brown" has RGBA8888 code {@code 77331EFF}, L 0.3019608, A 0.5411765, B 0.53333336, alpha 1.0, hue 0.10831932, saturation 0.5191111, and chroma 0.105541065.
     * It can be represented as a packed float with the constant {@code -0x1.11149ap126F}.
     * <pre>
     * <font style='background-color: #77331E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #77331E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #77331E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #77331E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #77331E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #77331E'>&nbsp;@&nbsp;</font><font style='background-color: #77331E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #77331E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #77331E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_RED_BROWN = -0x1.11149ap126F;
    static { NAMED.put("drab red brown", -0x1.11149ap126F); LIST.add(-0x1.11149ap126F); }

    /**
     * This color constant "dull red brown" has RGBA8888 code {@code B2624AFF}, L 0.47058824, A 0.5411765, B 0.53333336, alpha 1.0, hue 0.10831932, saturation 0.2648526, and chroma 0.105541065.
     * It can be represented as a packed float with the constant {@code -0x1.1114fp126F}.
     * <pre>
     * <font style='background-color: #B2624A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2624A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2624A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B2624A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B2624A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B2624A'>&nbsp;@&nbsp;</font><font style='background-color: #B2624A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2624A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2624A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_RED_BROWN = -0x1.1114fp126F;
    static { NAMED.put("dull red brown", -0x1.1114fp126F); LIST.add(-0x1.1114fp126F); }

    /**
     * This color constant "pale red brown" has RGBA8888 code {@code E88F74FF}, L 0.6392157, A 0.5411765, B 0.53333336, alpha 1.0, hue 0.10831932, saturation 0.40415224, and chroma 0.105541065.
     * It can be represented as a packed float with the constant {@code -0x1.111546p126F}.
     * <pre>
     * <font style='background-color: #E88F74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E88F74; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E88F74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E88F74'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E88F74'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E88F74'>&nbsp;@&nbsp;</font><font style='background-color: #E88F74; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E88F74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E88F74; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_RED_BROWN = -0x1.111546p126F;
    static { NAMED.put("pale red brown", -0x1.111546p126F); LIST.add(-0x1.111546p126F); }

    /**
     * This color constant "drab orange brown" has RGBA8888 code {@code 6F422FFF}, L 0.32156864, A 0.5254902, B 0.5254902, alpha 1.0, hue 0.125, saturation 0.26820076, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.0d0ca4p126F}.
     * <pre>
     * <font style='background-color: #6F422F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6F422F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6F422F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6F422F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6F422F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6F422F'>&nbsp;@&nbsp;</font><font style='background-color: #6F422F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6F422F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6F422F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_ORANGE_BROWN = -0x1.0d0ca4p126F;
    static { NAMED.put("drab orange brown", -0x1.0d0ca4p126F); LIST.add(-0x1.0d0ca4p126F); }

    /**
     * This color constant "dull orange brown" has RGBA8888 code {@code A6725BFF}, L 0.49019608, A 0.52156866, B 0.5254902, alpha 1.0, hue 0.138223, saturation 0.1400797, and chroma 0.06652104.
     * It can be represented as a packed float with the constant {@code -0x1.0d0afap126F}.
     * <pre>
     * <font style='background-color: #A6725B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A6725B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A6725B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A6725B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A6725B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A6725B'>&nbsp;@&nbsp;</font><font style='background-color: #A6725B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A6725B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A6725B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_ORANGE_BROWN = -0x1.0d0afap126F;
    static { NAMED.put("dull orange brown", -0x1.0d0afap126F); LIST.add(-0x1.0d0afap126F); }

    /**
     * This color constant "pale orange brown" has RGBA8888 code {@code DAA087FF}, L 0.6627451, A 0.52156866, B 0.5254902, alpha 1.0, hue 0.138223, saturation 0.16055363, and chroma 0.06652104.
     * It can be represented as a packed float with the constant {@code -0x1.0d0b52p126F}.
     * <pre>
     * <font style='background-color: #DAA087;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAA087; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAA087;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DAA087'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DAA087'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DAA087'>&nbsp;@&nbsp;</font><font style='background-color: #DAA087; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAA087;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAA087; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_ORANGE_BROWN = -0x1.0d0b52p126F;
    static { NAMED.put("pale orange brown", -0x1.0d0b52p126F); LIST.add(-0x1.0d0b52p126F); }

    /**
     * This color constant "drab brown orange" has RGBA8888 code {@code 604C40FF}, L 0.32941177, A 0.50980395, B 0.5137255, alpha 1.0, hue 0.15127131, saturation 0.07005917, and chroma 0.033602834.
     * It can be represented as a packed float with the constant {@code -0x1.0704a8p126F}.
     * <pre>
     * <font style='background-color: #604C40;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #604C40; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #604C40;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #604C40'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #604C40'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #604C40'>&nbsp;@&nbsp;</font><font style='background-color: #604C40; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #604C40;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #604C40; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BROWN_ORANGE = -0x1.0704a8p126F;
    static { NAMED.put("drab brown orange", -0x1.0704a8p126F); LIST.add(-0x1.0704a8p126F); }

    /**
     * This color constant "dull brown orange" has RGBA8888 code {@code 978072FF}, L 0.50980395, A 0.50980395, B 0.5137255, alpha 1.0, hue 0.15127131, saturation 0.03736902, and chroma 0.033602834.
     * It can be represented as a packed float with the constant {@code -0x1.070504p126F}.
     * <pre>
     * <font style='background-color: #978072;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #978072; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #978072;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #978072'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #978072'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #978072'>&nbsp;@&nbsp;</font><font style='background-color: #978072; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #978072;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #978072; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_BROWN_ORANGE = -0x1.070504p126F;
    static { NAMED.put("dull brown orange", -0x1.070504p126F); LIST.add(-0x1.070504p126F); }

    /**
     * This color constant "pale brown orange" has RGBA8888 code {@code C8AF9FFF}, L 0.6862745, A 0.5058824, B 0.5137255, alpha 1.0, hue 0.18556869, saturation 0.02682391, and chroma 0.029749114.
     * It can be represented as a packed float with the constant {@code -0x1.07035ep126F}.
     * <pre>
     * <font style='background-color: #C8AF9F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8AF9F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8AF9F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C8AF9F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C8AF9F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C8AF9F'>&nbsp;@&nbsp;</font><font style='background-color: #C8AF9F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8AF9F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8AF9F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BROWN_ORANGE = -0x1.07035ep126F;
    static { NAMED.put("pale brown orange", -0x1.07035ep126F); LIST.add(-0x1.07035ep126F); }

    /**
     * This color constant "drab saffron orange" has RGBA8888 code {@code 7D471DFF}, L 0.34509805, A 0.5254902, B 0.5411765, alpha 1.0, hue 0.16176948, saturation 0.61476445, and chroma 0.096477255.
     * It can be represented as a packed float with the constant {@code -0x1.150cbp126F}.
     * <pre>
     * <font style='background-color: #7D471D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7D471D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7D471D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7D471D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7D471D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7D471D'>&nbsp;@&nbsp;</font><font style='background-color: #7D471D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7D471D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7D471D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_SAFFRON_ORANGE = -0x1.150cbp126F;
    static { NAMED.put("drab saffron orange", -0x1.150cbp126F); LIST.add(-0x1.150cbp126F); }

    /**
     * This color constant "dull saffron orange" has RGBA8888 code {@code BA7B4DFF}, L 0.5294118, A 0.5254902, B 0.5411765, alpha 1.0, hue 0.16176948, saturation 0.32236755, and chroma 0.096477255.
     * It can be represented as a packed float with the constant {@code -0x1.150d0ep126F}.
     * <pre>
     * <font style='background-color: #BA7B4D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA7B4D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA7B4D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BA7B4D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BA7B4D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BA7B4D'>&nbsp;@&nbsp;</font><font style='background-color: #BA7B4D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA7B4D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA7B4D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_SAFFRON_ORANGE = -0x1.150d0ep126F;
    static { NAMED.put("dull saffron orange", -0x1.150d0ep126F); LIST.add(-0x1.150d0ep126F); }

    /**
     * This color constant "pale saffron orange" has RGBA8888 code {@code EFAA78FF}, L 0.70980394, A 0.5254902, B 0.5411765, alpha 1.0, hue 0.16176948, saturation 0.43377778, and chroma 0.096477255.
     * It can be represented as a packed float with the constant {@code -0x1.150d6ap126F}.
     * <pre>
     * <font style='background-color: #EFAA78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFAA78; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFAA78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EFAA78'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EFAA78'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EFAA78'>&nbsp;@&nbsp;</font><font style='background-color: #EFAA78; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFAA78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFAA78; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_SAFFRON_ORANGE = -0x1.150d6ap126F;
    static { NAMED.put("pale saffron orange", -0x1.150d6ap126F); LIST.add(-0x1.150d6ap126F); }

    /**
     * This color constant "drab orange saffron" has RGBA8888 code {@code 775021FF}, L 0.35686275, A 0.5137255, B 0.5372549, alpha 1.0, hue 0.19383265, saturation 0.44074175, and chroma 0.079095535.
     * It can be represented as a packed float with the constant {@code -0x1.1306b6p126F}.
     * <pre>
     * <font style='background-color: #775021;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #775021; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #775021;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #775021'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #775021'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #775021'>&nbsp;@&nbsp;</font><font style='background-color: #775021; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #775021;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #775021; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_ORANGE_SAFFRON = -0x1.1306b6p126F;
    static { NAMED.put("drab orange saffron", -0x1.1306b6p126F); LIST.add(-0x1.1306b6p126F); }

    /**
     * This color constant "dull orange saffron" has RGBA8888 code {@code B58853FF}, L 0.5529412, A 0.5137255, B 0.5411765, alpha 1.0, hue 0.19880433, saturation 0.29873496, and chroma 0.08646853.
     * It can be represented as a packed float with the constant {@code -0x1.15071ap126F}.
     * <pre>
     * <font style='background-color: #B58853;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B58853; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B58853;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B58853'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B58853'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B58853'>&nbsp;@&nbsp;</font><font style='background-color: #B58853; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B58853;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B58853; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_ORANGE_SAFFRON = -0x1.15071ap126F;
    static { NAMED.put("dull orange saffron", -0x1.15071ap126F); LIST.add(-0x1.15071ap126F); }

    /**
     * This color constant "pale orange saffron" has RGBA8888 code {@code EBBA81FF}, L 0.74509805, A 0.50980395, B 0.5411765, alpha 1.0, hue 0.21279909, saturation 0.24626768, and chroma 0.084324345.
     * It can be represented as a packed float with the constant {@code -0x1.15057cp126F}.
     * <pre>
     * <font style='background-color: #EBBA81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBBA81; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBBA81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EBBA81'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EBBA81'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EBBA81'>&nbsp;@&nbsp;</font><font style='background-color: #EBBA81; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBBA81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBBA81; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_ORANGE_SAFFRON = -0x1.15057cp126F;
    static { NAMED.put("pale orange saffron", -0x1.15057cp126F); LIST.add(-0x1.15057cp126F); }

    /**
     * This color constant "drab yellow saffron" has RGBA8888 code {@code 775C16FF}, L 0.38039216, A 0.49803922, B 0.54901963, alpha 1.0, hue 0.25635016, saturation 0.6729374, and chroma 0.09773435.
     * It can be represented as a packed float with the constant {@code -0x1.18fec2p126F}.
     * <pre>
     * <font style='background-color: #775C16;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #775C16; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #775C16;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #775C16'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #775C16'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #775C16'>&nbsp;@&nbsp;</font><font style='background-color: #775C16; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #775C16;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #775C16; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_YELLOW_SAFFRON = -0x1.18fec2p126F;
    static { NAMED.put("drab yellow saffron", -0x1.18fec2p126F); LIST.add(-0x1.18fec2p126F); }

    /**
     * This color constant "dull yellow saffron" has RGBA8888 code {@code B4954DFF}, L 0.5803922, A 0.49803922, B 0.54901963, alpha 1.0, hue 0.25635016, saturation 0.40121776, and chroma 0.09773435.
     * It can be represented as a packed float with the constant {@code -0x1.18ff28p126F}.
     * <pre>
     * <font style='background-color: #B4954D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4954D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4954D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B4954D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B4954D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B4954D'>&nbsp;@&nbsp;</font><font style='background-color: #B4954D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4954D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4954D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_YELLOW_SAFFRON = -0x1.18ff28p126F;
    static { NAMED.put("dull yellow saffron", -0x1.18ff28p126F); LIST.add(-0x1.18ff28p126F); }

    /**
     * This color constant "pale yellow saffron" has RGBA8888 code {@code ECCA7CFF}, L 0.78431374, A 0.49803922, B 0.54901963, alpha 1.0, hue 0.25635016, saturation 0.26612818, and chroma 0.09773435.
     * It can be represented as a packed float with the constant {@code -0x1.18ff9p126F}.
     * <pre>
     * <font style='background-color: #ECCA7C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ECCA7C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ECCA7C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ECCA7C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ECCA7C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ECCA7C'>&nbsp;@&nbsp;</font><font style='background-color: #ECCA7C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ECCA7C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ECCA7C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOW_SAFFRON = -0x1.18ff9p126F;
    static { NAMED.put("pale yellow saffron", -0x1.18ff9p126F); LIST.add(-0x1.18ff9p126F); }

    /**
     * This color constant "drab saffron yellow" has RGBA8888 code {@code 776B22FF}, L 0.41568628, A 0.49019608, B 0.54901963, alpha 1.0, hue 0.28142345, saturation 0.61538464, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.18fad4p126F}.
     * <pre>
     * <font style='background-color: #776B22;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #776B22; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #776B22;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #776B22'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #776B22'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #776B22'>&nbsp;@&nbsp;</font><font style='background-color: #776B22; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #776B22;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #776B22; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_SAFFRON_YELLOW = -0x1.18fad4p126F;
    static { NAMED.put("drab saffron yellow", -0x1.18fad4p126F); LIST.add(-0x1.18fad4p126F); }

    /**
     * This color constant "dull saffron yellow" has RGBA8888 code {@code B5AA5BFF}, L 0.63529414, A 0.4862745, B 0.54901963, alpha 1.0, hue 0.29344302, saturation 0.34036106, and chroma 0.10141215.
     * It can be represented as a packed float with the constant {@code -0x1.18f944p126F}.
     * <pre>
     * <font style='background-color: #B5AA5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B5AA5B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B5AA5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B5AA5B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B5AA5B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B5AA5B'>&nbsp;@&nbsp;</font><font style='background-color: #B5AA5B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B5AA5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B5AA5B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_SAFFRON_YELLOW = -0x1.18f944p126F;
    static { NAMED.put("dull saffron yellow", -0x1.18f944p126F); LIST.add(-0x1.18f944p126F); }

    /**
     * This color constant "pale saffron yellow" has RGBA8888 code {@code EDE18BFF}, L 0.8509804, A 0.4862745, B 0.54901963, alpha 1.0, hue 0.29344302, saturation 0.24453515, and chroma 0.10141215.
     * It can be represented as a packed float with the constant {@code -0x1.18f9b2p126F}.
     * <pre>
     * <font style='background-color: #EDE18B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDE18B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDE18B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EDE18B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EDE18B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EDE18B'>&nbsp;@&nbsp;</font><font style='background-color: #EDE18B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDE18B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDE18B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_SAFFRON_YELLOW = -0x1.18f9b2p126F;
    static { NAMED.put("pale saffron yellow", -0x1.18f9b2p126F); LIST.add(-0x1.18f9b2p126F); }

    /**
     * This color constant "drab lime yellow" has RGBA8888 code {@code 6A741BFF}, L 0.42352942, A 0.4745098, B 0.5529412, alpha 1.0, hue 0.3214129, saturation 0.67404765, and chroma 0.11705722.
     * It can be represented as a packed float with the constant {@code -0x1.1af2d8p126F}.
     * <pre>
     * <font style='background-color: #6A741B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A741B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A741B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6A741B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6A741B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6A741B'>&nbsp;@&nbsp;</font><font style='background-color: #6A741B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A741B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A741B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_LIME_YELLOW = -0x1.1af2d8p126F;
    static { NAMED.put("drab lime yellow", -0x1.1af2d8p126F); LIST.add(-0x1.1af2d8p126F); }

    /**
     * This color constant "dull lime yellow" has RGBA8888 code {@code A5B255FF}, L 0.6392157, A 0.4745098, B 0.5529412, alpha 1.0, hue 0.3214129, saturation 0.39800555, and chroma 0.11705722.
     * It can be represented as a packed float with the constant {@code -0x1.1af346p126F}.
     * <pre>
     * <font style='background-color: #A5B255;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A5B255; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A5B255;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A5B255'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A5B255'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A5B255'>&nbsp;@&nbsp;</font><font style='background-color: #A5B255; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A5B255;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A5B255; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_LIME_YELLOW = -0x1.1af346p126F;
    static { NAMED.put("dull lime yellow", -0x1.1af346p126F); LIST.add(-0x1.1af346p126F); }

    /**
     * This color constant "pale lime yellow" has RGBA8888 code {@code D9E985FF}, L 0.8509804, A 0.47058824, B 0.5529412, alpha 1.0, hue 0.33071172, saturation 0.27876398, and chroma 0.120651916.
     * It can be represented as a packed float with the constant {@code -0x1.1af1b2p126F}.
     * <pre>
     * <font style='background-color: #D9E985;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9E985; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9E985;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D9E985'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D9E985'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D9E985'>&nbsp;@&nbsp;</font><font style='background-color: #D9E985; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9E985;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9E985; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_LIME_YELLOW = -0x1.1af1b2p126F;
    static { NAMED.put("pale lime yellow", -0x1.1af1b2p126F); LIST.add(-0x1.1af1b2p126F); }

    /**
     * This color constant "drab yellow lime" has RGBA8888 code {@code 5B7422FF}, L 0.4117647, A 0.46666667, B 0.54901963, alpha 1.0, hue 0.34505606, saturation 0.58580357, and chroma 0.11809544.
     * It can be represented as a packed float with the constant {@code -0x1.18eed2p126F}.
     * <pre>
     * <font style='background-color: #5B7422;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B7422; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B7422;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5B7422'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5B7422'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5B7422'>&nbsp;@&nbsp;</font><font style='background-color: #5B7422; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B7422;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B7422; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_YELLOW_LIME = -0x1.18eed2p126F;
    static { NAMED.put("drab yellow lime", -0x1.18eed2p126F); LIST.add(-0x1.18eed2p126F); }

    /**
     * This color constant "dull yellow lime" has RGBA8888 code {@code 92B158FF}, L 0.61960787, A 0.4627451, B 0.54901963, alpha 1.0, hue 0.3534426, saturation 0.34448424, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.18ed3cp126F}.
     * <pre>
     * <font style='background-color: #92B158;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #92B158; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #92B158;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #92B158'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #92B158'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #92B158'>&nbsp;@&nbsp;</font><font style='background-color: #92B158; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #92B158;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #92B158; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_YELLOW_LIME = -0x1.18ed3cp126F;
    static { NAMED.put("dull yellow lime", -0x1.18ed3cp126F); LIST.add(-0x1.18ed3cp126F); }

    /**
     * This color constant "pale yellow lime" has RGBA8888 code {@code C6E888FF}, L 0.83137256, A 0.4627451, B 0.54901963, alpha 1.0, hue 0.3534426, saturation 0.24452849, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.18eda8p126F}.
     * <pre>
     * <font style='background-color: #C6E888;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C6E888; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C6E888;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C6E888'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C6E888'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C6E888'>&nbsp;@&nbsp;</font><font style='background-color: #C6E888; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C6E888;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C6E888; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOW_LIME = -0x1.18eda8p126F;
    static { NAMED.put("pale yellow lime", -0x1.18eda8p126F); LIST.add(-0x1.18eda8p126F); }

    /**
     * This color constant "drab green lime" has RGBA8888 code {@code 3F741CFF}, L 0.39215687, A 0.44705883, B 0.54901963, alpha 1.0, hue 0.38111365, saturation 0.62619954, and chroma 0.14373726.
     * It can be represented as a packed float with the constant {@code -0x1.18e4c8p126F}.
     * <pre>
     * <font style='background-color: #3F741C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F741C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F741C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3F741C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3F741C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3F741C'>&nbsp;@&nbsp;</font><font style='background-color: #3F741C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F741C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F741C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_GREEN_LIME = -0x1.18e4c8p126F;
    static { NAMED.put("drab green lime", -0x1.18e4c8p126F); LIST.add(-0x1.18e4c8p126F); }

    /**
     * This color constant "dull green lime" has RGBA8888 code {@code 76B254FF}, L 0.6, A 0.44705883, B 0.54901963, alpha 1.0, hue 0.38111365, saturation 0.35798797, and chroma 0.14373726.
     * It can be represented as a packed float with the constant {@code -0x1.18e532p126F}.
     * <pre>
     * <font style='background-color: #76B254;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #76B254; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #76B254;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #76B254'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #76B254'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #76B254'>&nbsp;@&nbsp;</font><font style='background-color: #76B254; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #76B254;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #76B254; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_GREEN_LIME = -0x1.18e532p126F;
    static { NAMED.put("dull green lime", -0x1.18e532p126F); LIST.add(-0x1.18e532p126F); }

    /**
     * This color constant "pale green lime" has RGBA8888 code {@code A8EA84FF}, L 0.80784315, A 0.44705883, B 0.54901963, alpha 1.0, hue 0.38111365, saturation 0.2506363, and chroma 0.14373726.
     * It can be represented as a packed float with the constant {@code -0x1.18e59cp126F}.
     * <pre>
     * <font style='background-color: #A8EA84;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A8EA84; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A8EA84;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A8EA84'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A8EA84'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A8EA84'>&nbsp;@&nbsp;</font><font style='background-color: #A8EA84; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A8EA84;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A8EA84; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_GREEN_LIME = -0x1.18e59cp126F;
    static { NAMED.put("pale green lime", -0x1.18e59cp126F); LIST.add(-0x1.18e59cp126F); }

    /**
     * This color constant "drab lime green" has RGBA8888 code {@code 347424FF}, L 0.3882353, A 0.44313726, B 0.54509807, alpha 1.0, hue 0.39327157, saturation 0.60720223, and chroma 0.14458403.
     * It can be represented as a packed float with the constant {@code -0x1.16e2c6p126F}.
     * <pre>
     * <font style='background-color: #347424;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #347424; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #347424;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #347424'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #347424'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #347424'>&nbsp;@&nbsp;</font><font style='background-color: #347424; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #347424;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #347424; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_LIME_GREEN = -0x1.16e2c6p126F;
    static { NAMED.put("drab lime green", -0x1.16e2c6p126F); LIST.add(-0x1.16e2c6p126F); }

    /**
     * This color constant "dull lime green" has RGBA8888 code {@code 69B158FF}, L 0.5882353, A 0.44313726, B 0.54509807, alpha 1.0, hue 0.39327157, saturation 0.32930714, and chroma 0.14458403.
     * It can be represented as a packed float with the constant {@code -0x1.16e32cp126F}.
     * <pre>
     * <font style='background-color: #69B158;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #69B158; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #69B158;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #69B158'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #69B158'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #69B158'>&nbsp;@&nbsp;</font><font style='background-color: #69B158; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #69B158;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #69B158; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_LIME_GREEN = -0x1.16e32cp126F;
    static { NAMED.put("dull lime green", -0x1.16e32cp126F); LIST.add(-0x1.16e32cp126F); }

    /**
     * This color constant "pale lime green" has RGBA8888 code {@code 99E787FF}, L 0.7882353, A 0.44313726, B 0.54509807, alpha 1.0, hue 0.39327157, saturation 0.22809574, and chroma 0.14458403.
     * It can be represented as a packed float with the constant {@code -0x1.16e392p126F}.
     * <pre>
     * <font style='background-color: #99E787;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #99E787; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #99E787;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #99E787'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #99E787'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #99E787'>&nbsp;@&nbsp;</font><font style='background-color: #99E787; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #99E787;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #99E787; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_LIME_GREEN = -0x1.16e392p126F;
    static { NAMED.put("pale lime green", -0x1.16e392p126F); LIST.add(-0x1.16e392p126F); }

    /**
     * This color constant "drab cyan green" has RGBA8888 code {@code 1A7549FF}, L 0.3882353, A 0.44313726, B 0.52156866, alpha 1.0, hue 0.4423118, saturation 0.8572065, and chroma 0.12115674.
     * It can be represented as a packed float with the constant {@code -0x1.0ae2c6p126F}.
     * <pre>
     * <font style='background-color: #1A7549;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1A7549; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1A7549;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1A7549'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1A7549'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1A7549'>&nbsp;@&nbsp;</font><font style='background-color: #1A7549; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1A7549;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1A7549; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_CYAN_GREEN = -0x1.0ae2c6p126F;
    static { NAMED.put("drab cyan green", -0x1.0ae2c6p126F); LIST.add(-0x1.0ae2c6p126F); }

    /**
     * This color constant "dull cyan green" has RGBA8888 code {@code 4BA674FF}, L 0.54901963, A 0.44313726, B 0.5176471, alpha 1.0, hue 0.45211816, saturation 0.5621094, and chroma 0.11861114.
     * It can be represented as a packed float with the constant {@code -0x1.08e318p126F}.
     * <pre>
     * <font style='background-color: #4BA674;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4BA674; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4BA674;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4BA674'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4BA674'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4BA674'>&nbsp;@&nbsp;</font><font style='background-color: #4BA674; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4BA674;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4BA674; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_CYAN_GREEN = -0x1.08e318p126F;
    static { NAMED.put("dull cyan green", -0x1.08e318p126F); LIST.add(-0x1.08e318p126F); }

    /**
     * This color constant "pale cyan green" has RGBA8888 code {@code 74D39CFF}, L 0.70980394, A 0.44313726, B 0.52156866, alpha 1.0, hue 0.4423118, saturation 0.37721792, and chroma 0.12115674.
     * It can be represented as a packed float with the constant {@code -0x1.0ae36ap126F}.
     * <pre>
     * <font style='background-color: #74D39C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #74D39C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #74D39C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #74D39C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #74D39C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #74D39C'>&nbsp;@&nbsp;</font><font style='background-color: #74D39C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #74D39C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #74D39C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_CYAN_GREEN = -0x1.0ae36ap126F;
    static { NAMED.put("pale cyan green", -0x1.0ae36ap126F); LIST.add(-0x1.0ae36ap126F); }

    /**
     * This color constant "drab green cyan" has RGBA8888 code {@code 1B6B60FF}, L 0.36862746, A 0.45490196, B 0.49803922, alpha 1.0, hue 0.50690335, saturation 0.8150711, and chroma 0.08992863.
     * It can be represented as a packed float with the constant {@code -0x1.fee8bcp125F}.
     * <pre>
     * <font style='background-color: #1B6B60;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1B6B60; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1B6B60;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1B6B60'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1B6B60'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1B6B60'>&nbsp;@&nbsp;</font><font style='background-color: #1B6B60; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1B6B60;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1B6B60; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_GREEN_CYAN = -0x1.fee8bcp125F;
    static { NAMED.put("drab green cyan", -0x1.fee8bcp125F); LIST.add(-0x1.fee8bcp125F); }

    /**
     * This color constant "dull green cyan" has RGBA8888 code {@code 489A8DFF}, L 0.52156866, A 0.45490196, B 0.49803922, alpha 1.0, hue 0.50690335, saturation 0.50177515, and chroma 0.08992863.
     * It can be represented as a packed float with the constant {@code -0x1.fee90ap125F}.
     * <pre>
     * <font style='background-color: #489A8D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #489A8D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #489A8D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #489A8D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #489A8D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #489A8D'>&nbsp;@&nbsp;</font><font style='background-color: #489A8D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #489A8D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #489A8D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_GREEN_CYAN = -0x1.fee90ap125F;
    static { NAMED.put("dull green cyan", -0x1.fee90ap125F); LIST.add(-0x1.fee90ap125F); }

    /**
     * This color constant "pale green cyan" has RGBA8888 code {@code 6FC4B5FF}, L 0.67058825, A 0.45490196, B 0.49803922, alpha 1.0, hue 0.50690335, saturation 0.3575645, and chroma 0.08992863.
     * It can be represented as a packed float with the constant {@code -0x1.fee956p125F}.
     * <pre>
     * <font style='background-color: #6FC4B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6FC4B5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6FC4B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6FC4B5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6FC4B5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6FC4B5'>&nbsp;@&nbsp;</font><font style='background-color: #6FC4B5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6FC4B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6FC4B5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_GREEN_CYAN = -0x1.fee956p125F;
    static { NAMED.put("pale green cyan", -0x1.fee956p125F); LIST.add(-0x1.fee956p125F); }

    /**
     * This color constant "drab blue cyan" has RGBA8888 code {@code 1D6877FF}, L 0.36862746, A 0.4627451, B 0.47843137, alpha 1.0, hue 0.58353055, saturation 0.7412534, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.f4ecbcp125F}.
     * <pre>
     * <font style='background-color: #1D6877;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1D6877; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1D6877;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1D6877'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1D6877'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1D6877'>&nbsp;@&nbsp;</font><font style='background-color: #1D6877; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1D6877;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1D6877; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BLUE_CYAN = -0x1.f4ecbcp125F;
    static { NAMED.put("drab blue cyan", -0x1.f4ecbcp125F); LIST.add(-0x1.f4ecbcp125F); }

    /**
     * This color constant "dull blue cyan" has RGBA8888 code {@code 56A3B4FF}, L 0.5686275, A 0.4627451, B 0.47843137, alpha 1.0, hue 0.58353055, saturation 0.40495694, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.f4ed22p125F}.
     * <pre>
     * <font style='background-color: #56A3B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #56A3B4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #56A3B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #56A3B4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #56A3B4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #56A3B4'>&nbsp;@&nbsp;</font><font style='background-color: #56A3B4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #56A3B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #56A3B4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_BLUE_CYAN = -0x1.f4ed22p125F;
    static { NAMED.put("dull blue cyan", -0x1.f4ed22p125F); LIST.add(-0x1.f4ed22p125F); }

    /**
     * This color constant "pale blue cyan" has RGBA8888 code {@code 87D9ECFF}, L 0.76862746, A 0.46666667, B 0.4745098, alpha 1.0, hue 0.60391617, saturation 0.4081087, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.f2ef88p125F}.
     * <pre>
     * <font style='background-color: #87D9EC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #87D9EC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #87D9EC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #87D9EC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #87D9EC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #87D9EC'>&nbsp;@&nbsp;</font><font style='background-color: #87D9EC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #87D9EC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #87D9EC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BLUE_CYAN = -0x1.f2ef88p125F;
    static { NAMED.put("pale blue cyan", -0x1.f2ef88p125F); LIST.add(-0x1.f2ef88p125F); }

    /**
     * This color constant "drab cyan blue" has RGBA8888 code {@code 1C4A73FF}, L 0.29411766, A 0.47843137, B 0.45490196, alpha 1.0, hue 0.6790041, saturation 0.65507686, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.e8f496p125F}.
     * <pre>
     * <font style='background-color: #1C4A73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1C4A73; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1C4A73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1C4A73'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1C4A73'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1C4A73'>&nbsp;@&nbsp;</font><font style='background-color: #1C4A73; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1C4A73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1C4A73; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_CYAN_BLUE = -0x1.e8f496p125F;
    static { NAMED.put("drab cyan blue", -0x1.e8f496p125F); LIST.add(-0x1.e8f496p125F); }

    /**
     * This color constant "dull cyan blue" has RGBA8888 code {@code 4C7FAFFF}, L 0.47058824, A 0.47843137, B 0.45490196, alpha 1.0, hue 0.6790041, saturation 0.3435064, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.e8f4fp125F}.
     * <pre>
     * <font style='background-color: #4C7FAF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4C7FAF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4C7FAF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4C7FAF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4C7FAF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4C7FAF'>&nbsp;@&nbsp;</font><font style='background-color: #4C7FAF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4C7FAF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4C7FAF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_CYAN_BLUE = -0x1.e8f4fp125F;
    static { NAMED.put("dull cyan blue", -0x1.e8f4fp125F); LIST.add(-0x1.e8f4fp125F); }

    /**
     * This color constant "pale cyan blue" has RGBA8888 code {@code 7AB2E7FF}, L 0.6509804, A 0.47843137, B 0.45490196, alpha 1.0, hue 0.6790041, saturation 0.4878964, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.e8f54cp125F}.
     * <pre>
     * <font style='background-color: #7AB2E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7AB2E7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7AB2E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7AB2E7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7AB2E7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7AB2E7'>&nbsp;@&nbsp;</font><font style='background-color: #7AB2E7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7AB2E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7AB2E7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_CYAN_BLUE = -0x1.e8f54cp125F;
    static { NAMED.put("pale cyan blue", -0x1.e8f54cp125F); LIST.add(-0x1.e8f54cp125F); }

    /**
     * This color constant "drab violet blue" has RGBA8888 code {@code 1D217DFF}, L 0.21960784, A 0.5019608, B 0.41568628, alpha 1.0, hue 0.75368965, saturation 0.5595463, and chroma 0.16801417.
     * It can be represented as a packed float with the constant {@code -0x1.d5007p125F}.
     * <pre>
     * <font style='background-color: #1D217D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1D217D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1D217D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1D217D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1D217D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1D217D'>&nbsp;@&nbsp;</font><font style='background-color: #1D217D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1D217D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1D217D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_VIOLET_BLUE = -0x1.d5007p125F;
    static { NAMED.put("drab violet blue", -0x1.d5007p125F); LIST.add(-0x1.d5007p125F); }

    /**
     * This color constant "dull violet blue" has RGBA8888 code {@code 4052BBFF}, L 0.37254903, A 0.5019608, B 0.41568628, alpha 1.0, hue 0.75368965, saturation 0.33331832, and chroma 0.16801417.
     * It can be represented as a packed float with the constant {@code -0x1.d500bep125F}.
     * <pre>
     * <font style='background-color: #4052BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4052BB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4052BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4052BB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4052BB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4052BB'>&nbsp;@&nbsp;</font><font style='background-color: #4052BB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4052BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4052BB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_VIOLET_BLUE = -0x1.d500bep125F;
    static { NAMED.put("dull violet blue", -0x1.d500bep125F); LIST.add(-0x1.d500bep125F); }

    /**
     * This color constant "pale violet blue" has RGBA8888 code {@code 6680F3FF}, L 0.5254902, A 0.49803922, B 0.41568628, alpha 1.0, hue 0.74631035, saturation 0.75502497, and chroma 0.16801417.
     * It can be represented as a packed float with the constant {@code -0x1.d4ff0cp125F}.
     * <pre>
     * <font style='background-color: #6680F3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6680F3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6680F3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6680F3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6680F3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6680F3'>&nbsp;@&nbsp;</font><font style='background-color: #6680F3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6680F3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6680F3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_VIOLET_BLUE = -0x1.d4ff0cp125F;
    static { NAMED.put("pale violet blue", -0x1.d4ff0cp125F); LIST.add(-0x1.d4ff0cp125F); }

    /**
     * This color constant "drab blue violet" has RGBA8888 code {@code 3A2474FF}, L 0.23921569, A 0.52156866, B 0.43137255, alpha 1.0, hue 0.798453, saturation 0.4531605, and chroma 0.143312.
     * It can be represented as a packed float with the constant {@code -0x1.dd0a7ap125F}.
     * <pre>
     * <font style='background-color: #3A2474;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3A2474; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3A2474;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3A2474'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3A2474'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3A2474'>&nbsp;@&nbsp;</font><font style='background-color: #3A2474; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3A2474;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3A2474; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BLUE_VIOLET = -0x1.dd0a7ap125F;
    static { NAMED.put("drab blue violet", -0x1.dd0a7ap125F); LIST.add(-0x1.dd0a7ap125F); }

    /**
     * This color constant "dull blue violet" has RGBA8888 code {@code 6753B0FF}, L 0.39607844, A 0.5254902, B 0.43137255, alpha 1.0, hue 0.8065884, saturation 0.23209158, and chroma 0.14584495.
     * It can be represented as a packed float with the constant {@code -0x1.dd0ccap125F}.
     * <pre>
     * <font style='background-color: #6753B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6753B0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6753B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6753B0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6753B0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6753B0'>&nbsp;@&nbsp;</font><font style='background-color: #6753B0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6753B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6753B0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_BLUE_VIOLET = -0x1.dd0ccap125F;
    static { NAMED.put("dull blue violet", -0x1.dd0ccap125F); LIST.add(-0x1.dd0ccap125F); }

    /**
     * This color constant "pale blue violet" has RGBA8888 code {@code 9480E7FF}, L 0.5568628, A 0.5254902, B 0.43137255, alpha 1.0, hue 0.8065884, saturation 0.5466131, and chroma 0.14584495.
     * It can be represented as a packed float with the constant {@code -0x1.dd0d1cp125F}.
     * <pre>
     * <font style='background-color: #9480E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9480E7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9480E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9480E7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9480E7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9480E7'>&nbsp;@&nbsp;</font><font style='background-color: #9480E7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9480E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9480E7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BLUE_VIOLET = -0x1.dd0d1cp125F;
    static { NAMED.put("pale blue violet", -0x1.dd0d1cp125F); LIST.add(-0x1.dd0d1cp125F); }

    /**
     * This color constant "drab purple violet" has RGBA8888 code {@code 4F1F7BFF}, L 0.25882354, A 0.54509807, B 0.43137255, alpha 1.0, hue 0.8425412, saturation 0.6128046, and chroma 0.16359681.
     * It can be represented as a packed float with the constant {@code -0x1.dd1684p125F}.
     * <pre>
     * <font style='background-color: #4F1F7B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4F1F7B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4F1F7B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4F1F7B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4F1F7B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4F1F7B'>&nbsp;@&nbsp;</font><font style='background-color: #4F1F7B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4F1F7B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4F1F7B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_PURPLE_VIOLET = -0x1.dd1684p125F;
    static { NAMED.put("drab purple violet", -0x1.dd1684p125F); LIST.add(-0x1.dd1684p125F); }

    /**
     * This color constant "dull purple violet" has RGBA8888 code {@code 814FB7FF}, L 0.41960785, A 0.54509807, B 0.43137255, alpha 1.0, hue 0.8425412, saturation 0.29202914, and chroma 0.16359681.
     * It can be represented as a packed float with the constant {@code -0x1.dd16d6p125F}.
     * <pre>
     * <font style='background-color: #814FB7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #814FB7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #814FB7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #814FB7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #814FB7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #814FB7'>&nbsp;@&nbsp;</font><font style='background-color: #814FB7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #814FB7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #814FB7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_PURPLE_VIOLET = -0x1.dd16d6p125F;
    static { NAMED.put("dull purple violet", -0x1.dd16d6p125F); LIST.add(-0x1.dd16d6p125F); }

    /**
     * This color constant "pale purple violet" has RGBA8888 code {@code B27DEFFF}, L 0.58431375, A 0.54509807, B 0.43137255, alpha 1.0, hue 0.8425412, saturation 0.6363719, and chroma 0.16359681.
     * It can be represented as a packed float with the constant {@code -0x1.dd172ap125F}.
     * <pre>
     * <font style='background-color: #B27DEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B27DEF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B27DEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B27DEF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B27DEF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B27DEF'>&nbsp;@&nbsp;</font><font style='background-color: #B27DEF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B27DEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B27DEF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLE_VIOLET = -0x1.dd172ap125F;
    static { NAMED.put("pale purple violet", -0x1.dd172ap125F); LIST.add(-0x1.dd172ap125F); }

    /**
     * This color constant "drab violet purple" has RGBA8888 code {@code 552779FF}, L 0.27450982, A 0.5411765, B 0.4392157, alpha 1.0, hue 0.8447748, saturation 0.45515785, and chroma 0.14626285.
     * It can be represented as a packed float with the constant {@code -0x1.e1148cp125F}.
     * <pre>
     * <font style='background-color: #552779;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #552779; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #552779;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #552779'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #552779'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #552779'>&nbsp;@&nbsp;</font><font style='background-color: #552779; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #552779;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #552779; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_VIOLET_PURPLE = -0x1.e1148cp125F;
    static { NAMED.put("drab violet purple", -0x1.e1148cp125F); LIST.add(-0x1.e1148cp125F); }

    /**
     * This color constant "dull violet purple" has RGBA8888 code {@code 8855B3FF}, L 0.43529412, A 0.54509807, B 0.4392157, alpha 1.0, hue 0.8516046, saturation 0.24807492, and chroma 0.15078327.
     * It can be represented as a packed float with the constant {@code -0x1.e116dep125F}.
     * <pre>
     * <font style='background-color: #8855B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8855B3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8855B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8855B3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8855B3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8855B3'>&nbsp;@&nbsp;</font><font style='background-color: #8855B3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8855B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8855B3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_VIOLET_PURPLE = -0x1.e116dep125F;
    static { NAMED.put("dull violet purple", -0x1.e116dep125F); LIST.add(-0x1.e116dep125F); }

    /**
     * This color constant "pale violet purple" has RGBA8888 code {@code B982E9FF}, L 0.6, A 0.54509807, B 0.4392157, alpha 1.0, hue 0.8516046, saturation 0.5205695, and chroma 0.15078327.
     * It can be represented as a packed float with the constant {@code -0x1.e11732p125F}.
     * <pre>
     * <font style='background-color: #B982E9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B982E9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B982E9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B982E9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B982E9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B982E9'>&nbsp;@&nbsp;</font><font style='background-color: #B982E9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B982E9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B982E9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_VIOLET_PURPLE = -0x1.e11732p125F;
    static { NAMED.put("pale violet purple", -0x1.e11732p125F); LIST.add(-0x1.e11732p125F); }

    /**
     * This color constant "drab magenta purple" has RGBA8888 code {@code 66207EFF}, L 0.2901961, A 0.56078434, B 0.4392157, alpha 1.0, hue 0.875, saturation 0.6470836, and chroma 0.17125243.
     * It can be represented as a packed float with the constant {@code -0x1.e11e94p125F}.
     * <pre>
     * <font style='background-color: #66207E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #66207E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #66207E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #66207E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #66207E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #66207E'>&nbsp;@&nbsp;</font><font style='background-color: #66207E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #66207E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #66207E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_MAGENTA_PURPLE = -0x1.e11e94p125F;
    static { NAMED.put("drab magenta purple", -0x1.e11e94p125F); LIST.add(-0x1.e11e94p125F); }

    /**
     * This color constant "dull magenta purple" has RGBA8888 code {@code 9E52BAFF}, L 0.45882353, A 0.56078434, B 0.4392157, alpha 1.0, hue 0.875, saturation 0.32, and chroma 0.17125243.
     * It can be represented as a packed float with the constant {@code -0x1.e11eeap125F}.
     * <pre>
     * <font style='background-color: #9E52BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E52BA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E52BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9E52BA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9E52BA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9E52BA'>&nbsp;@&nbsp;</font><font style='background-color: #9E52BA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E52BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E52BA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_MAGENTA_PURPLE = -0x1.e11eeap125F;
    static { NAMED.put("dull magenta purple", -0x1.e11eeap125F); LIST.add(-0x1.e11eeap125F); }

    /**
     * This color constant "pale magenta purple" has RGBA8888 code {@code D27FF1FF}, L 0.627451, A 0.56078434, B 0.4392157, alpha 1.0, hue 0.875, saturation 0.60208315, and chroma 0.17125243.
     * It can be represented as a packed float with the constant {@code -0x1.e11f4p125F}.
     * <pre>
     * <font style='background-color: #D27FF1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D27FF1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D27FF1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D27FF1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D27FF1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D27FF1'>&nbsp;@&nbsp;</font><font style='background-color: #D27FF1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D27FF1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D27FF1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_MAGENTA_PURPLE = -0x1.e11f4p125F;
    static { NAMED.put("pale magenta purple", -0x1.e11f4p125F); LIST.add(-0x1.e11f4p125F); }

    /**
     * This color constant "drab purple magenta" has RGBA8888 code {@code 702978FF}, L 0.30980393, A 0.56078434, B 0.4509804, alpha 1.0, hue 0.89197636, saturation 0.49682826, and chroma 0.1555649.
     * It can be represented as a packed float with the constant {@code -0x1.e71e9ep125F}.
     * <pre>
     * <font style='background-color: #702978;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #702978; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #702978;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #702978'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #702978'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #702978'>&nbsp;@&nbsp;</font><font style='background-color: #702978; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #702978;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #702978; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_PURPLE_MAGENTA = -0x1.e71e9ep125F;
    static { NAMED.put("drab purple magenta", -0x1.e71e9ep125F); LIST.add(-0x1.e71e9ep125F); }

    /**
     * This color constant "dull purple magenta" has RGBA8888 code {@code AA5AB3FF}, L 0.48235294, A 0.56078434, B 0.4509804, alpha 1.0, hue 0.89197636, saturation 0.2573735, and chroma 0.1555649.
     * It can be represented as a packed float with the constant {@code -0x1.e71ef6p125F}.
     * <pre>
     * <font style='background-color: #AA5AB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AA5AB3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AA5AB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AA5AB3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AA5AB3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AA5AB3'>&nbsp;@&nbsp;</font><font style='background-color: #AA5AB3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AA5AB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AA5AB3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_PURPLE_MAGENTA = -0x1.e71ef6p125F;
    static { NAMED.put("dull purple magenta", -0x1.e71ef6p125F); LIST.add(-0x1.e71ef6p125F); }

    /**
     * This color constant "pale purple magenta" has RGBA8888 code {@code E089EAFF}, L 0.65882355, A 0.56078434, B 0.4509804, alpha 1.0, hue 0.89197636, saturation 0.47969756, and chroma 0.1555649.
     * It can be represented as a packed float with the constant {@code -0x1.e71f5p125F}.
     * <pre>
     * <font style='background-color: #E089EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E089EA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E089EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E089EA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E089EA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E089EA'>&nbsp;@&nbsp;</font><font style='background-color: #E089EA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E089EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E089EA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLE_MAGENTA = -0x1.e71f5p125F;
    static { NAMED.put("pale purple magenta", -0x1.e71f5p125F); LIST.add(-0x1.e71f5p125F); }

    /**
     * This color constant "drab red magenta" has RGBA8888 code {@code 7C205CFF}, L 0.3019608, A 0.57254905, B 0.47843137, alpha 1.0, hue 0.95401794, saturation 0.6081012, and chroma 0.15078327.
     * It can be represented as a packed float with the constant {@code -0x1.f5249ap125F}.
     * <pre>
     * <font style='background-color: #7C205C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7C205C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7C205C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7C205C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7C205C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7C205C'>&nbsp;@&nbsp;</font><font style='background-color: #7C205C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7C205C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7C205C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_RED_MAGENTA = -0x1.f5249ap125F;
    static { NAMED.put("drab red magenta", -0x1.f5249ap125F); LIST.add(-0x1.f5249ap125F); }

    /**
     * This color constant "dull red magenta" has RGBA8888 code {@code B75090FF}, L 0.46666667, A 0.57254905, B 0.47843137, alpha 1.0, hue 0.95401794, saturation 0.31754488, and chroma 0.15078327.
     * It can be represented as a packed float with the constant {@code -0x1.f524eep125F}.
     * <pre>
     * <font style='background-color: #B75090;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B75090; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B75090;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B75090'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B75090'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B75090'>&nbsp;@&nbsp;</font><font style='background-color: #B75090; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B75090;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B75090; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_RED_MAGENTA = -0x1.f524eep125F;
    static { NAMED.put("dull red magenta", -0x1.f524eep125F); LIST.add(-0x1.f524eep125F); }

    /**
     * This color constant "pale red magenta" has RGBA8888 code {@code ED7BC0FF}, L 0.6313726, A 0.57254905, B 0.47843137, alpha 1.0, hue 0.95401794, saturation 0.5016413, and chroma 0.15078327.
     * It can be represented as a packed float with the constant {@code -0x1.f52542p125F}.
     * <pre>
     * <font style='background-color: #ED7BC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ED7BC0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ED7BC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ED7BC0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ED7BC0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ED7BC0'>&nbsp;@&nbsp;</font><font style='background-color: #ED7BC0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ED7BC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ED7BC0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_RED_MAGENTA = -0x1.f52542p125F;
    static { NAMED.put("pale red magenta", -0x1.f52542p125F); LIST.add(-0x1.f52542p125F); }

    /**
     * This color constant "drab magenta red" has RGBA8888 code {@code 752131FF}, L 0.2784314, A 0.56078434, B 0.5137255, alpha 1.0, hue 0.035347383, saturation 0.5100366, and chroma 0.124142565.
     * It can be represented as a packed float with the constant {@code -0x1.071e8ep126F}.
     * <pre>
     * <font style='background-color: #752131;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #752131; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #752131;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #752131'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #752131'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #752131'>&nbsp;@&nbsp;</font><font style='background-color: #752131; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #752131;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #752131; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_MAGENTA_RED = -0x1.071e8ep126F;
    static { NAMED.put("drab magenta red", -0x1.071e8ep126F); LIST.add(-0x1.071e8ep126F); }

    /**
     * This color constant "dull magenta red" has RGBA8888 code {@code B2505DFF}, L 0.44313726, A 0.56078434, B 0.5137255, alpha 1.0, hue 0.035347383, saturation 0.25856, and chroma 0.124142565.
     * It can be represented as a packed float with the constant {@code -0x1.071ee2p126F}.
     * <pre>
     * <font style='background-color: #B2505D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2505D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2505D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B2505D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B2505D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B2505D'>&nbsp;@&nbsp;</font><font style='background-color: #B2505D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2505D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2505D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_MAGENTA_RED = -0x1.071ee2p126F;
    static { NAMED.put("dull magenta red", -0x1.071ee2p126F); LIST.add(-0x1.071ee2p126F); }

    /**
     * This color constant "pale magenta red" has RGBA8888 code {@code E87B88FF}, L 0.6039216, A 0.56078434, B 0.5137255, alpha 1.0, hue 0.035347383, saturation 0.44764543, and chroma 0.124142565.
     * It can be represented as a packed float with the constant {@code -0x1.071f34p126F}.
     * <pre>
     * <font style='background-color: #E87B88;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E87B88; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E87B88;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E87B88'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E87B88'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E87B88'>&nbsp;@&nbsp;</font><font style='background-color: #E87B88; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E87B88;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E87B88; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_MAGENTA_RED = -0x1.071f34p126F;
    static { NAMED.put("pale magenta red", -0x1.071f34p126F); LIST.add(-0x1.071f34p126F); }

    /**
     * This color constant "deep red" has RGBA8888 code {@code C11D17FF}, L 0.39215687, A 0.5882353, B 0.54901963, alpha 1.0, hue 0.080711745, saturation 0.7743444, and chroma 0.20108652.
     * It can be represented as a packed float with the constant {@code -0x1.192cc8p126F}.
     * <pre>
     * <font style='background-color: #C11D17;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C11D17; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C11D17;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C11D17'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C11D17'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C11D17'>&nbsp;@&nbsp;</font><font style='background-color: #C11D17; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C11D17;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C11D17; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED = -0x1.192cc8p126F;
    static { NAMED.put("deep red", -0x1.192cc8p126F); LIST.add(-0x1.192cc8p126F); }

    /**
     * This color constant "bright red" has RGBA8888 code {@code F04739FF}, L 0.5137255, A 0.5882353, B 0.54901963, alpha 1.0, hue 0.080711745, saturation 0.63698095, and chroma 0.20108652.
     * It can be represented as a packed float with the constant {@code -0x1.192d06p126F}.
     * <pre>
     * <font style='background-color: #F04739;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F04739; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F04739;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F04739'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F04739'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F04739'>&nbsp;@&nbsp;</font><font style='background-color: #F04739; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F04739;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F04739; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED = -0x1.192d06p126F;
    static { NAMED.put("bright red", -0x1.192d06p126F); LIST.add(-0x1.192d06p126F); }

    /**
     * This color constant "deep brown red" has RGBA8888 code {@code BD3A27FF}, L 0.41568628, A 0.57254905, B 0.54509807, alpha 1.0, hue 0.08852651, saturation 0.594565, and chroma 0.17017984.
     * It can be represented as a packed float with the constant {@code -0x1.1724d4p126F}.
     * <pre>
     * <font style='background-color: #BD3A27;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD3A27; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD3A27;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BD3A27'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BD3A27'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BD3A27'>&nbsp;@&nbsp;</font><font style='background-color: #BD3A27; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD3A27;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD3A27; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN_RED = -0x1.1724d4p126F;
    static { NAMED.put("deep brown red", -0x1.1724d4p126F); LIST.add(-0x1.1724d4p126F); }

    /**
     * This color constant "bright brown red" has RGBA8888 code {@code E85B45FF}, L 0.53333336, A 0.57254905, B 0.54509807, alpha 1.0, hue 0.08852651, saturation 0.51854384, and chroma 0.17017984.
     * It can be represented as a packed float with the constant {@code -0x1.17251p126F}.
     * <pre>
     * <font style='background-color: #E85B45;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E85B45; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E85B45;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E85B45'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E85B45'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E85B45'>&nbsp;@&nbsp;</font><font style='background-color: #E85B45; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E85B45;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E85B45; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN_RED = -0x1.17251p126F;
    static { NAMED.put("bright brown red", -0x1.17251p126F); LIST.add(-0x1.17251p126F); }

    /**
     * This color constant "deep red brown" has RGBA8888 code {@code C3401AFF}, L 0.43137255, A 0.5686275, B 0.5529412, alpha 1.0, hue 0.10458898, saturation 0.73673296, and chroma 0.17267215.
     * It can be represented as a packed float with the constant {@code -0x1.1b22dcp126F}.
     * <pre>
     * <font style='background-color: #C3401A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3401A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3401A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C3401A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C3401A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C3401A'>&nbsp;@&nbsp;</font><font style='background-color: #C3401A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3401A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3401A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED_BROWN = -0x1.1b22dcp126F;
    static { NAMED.put("deep red brown", -0x1.1b22dcp126F); LIST.add(-0x1.1b22dcp126F); }

    /**
     * This color constant "bright red brown" has RGBA8888 code {@code F2653EFF}, L 0.56078434, A 0.5686275, B 0.5529412, alpha 1.0, hue 0.10458898, saturation 0.61210746, and chroma 0.17267215.
     * It can be represented as a packed float with the constant {@code -0x1.1b231ep126F}.
     * <pre>
     * <font style='background-color: #F2653E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2653E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2653E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F2653E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F2653E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F2653E'>&nbsp;@&nbsp;</font><font style='background-color: #F2653E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2653E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2653E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED_BROWN = -0x1.1b231ep126F;
    static { NAMED.put("bright red brown", -0x1.1b231ep126F); LIST.add(-0x1.1b231ep126F); }

    /**
     * This color constant "deep brown" has RGBA8888 code {@code A36044FF}, L 0.44705883, A 0.53333336, B 0.53333336, alpha 1.0, hue 0.125, saturation 0.27919334, and chroma 0.09391262.
     * It can be represented as a packed float with the constant {@code -0x1.1110e4p126F}.
     * <pre>
     * <font style='background-color: #A36044;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A36044; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A36044;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A36044'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A36044'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A36044'>&nbsp;@&nbsp;</font><font style='background-color: #A36044; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A36044;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A36044; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN = -0x1.1110e4p126F;
    static { NAMED.put("deep brown", -0x1.1110e4p126F); LIST.add(-0x1.1110e4p126F); }

    /**
     * This color constant "bright brown" has RGBA8888 code {@code CD8465FF}, L 0.5764706, A 0.53333336, B 0.53333336, alpha 1.0, hue 0.125, saturation 0.20193903, and chroma 0.09391262.
     * It can be represented as a packed float with the constant {@code -0x1.111126p126F}.
     * <pre>
     * <font style='background-color: #CD8465;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CD8465; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CD8465;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CD8465'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CD8465'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CD8465'>&nbsp;@&nbsp;</font><font style='background-color: #CD8465; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CD8465;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CD8465; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN = -0x1.111126p126F;
    static { NAMED.put("bright brown", -0x1.111126p126F); LIST.add(-0x1.111126p126F); }

    /**
     * This color constant "deep orange brown" has RGBA8888 code {@code A76240FF}, L 0.45490196, A 0.53333336, B 0.5372549, alpha 1.0, hue 0.13382626, saturation 0.3435064, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.1310e8p126F}.
     * <pre>
     * <font style='background-color: #A76240;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A76240; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A76240;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A76240'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A76240'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A76240'>&nbsp;@&nbsp;</font><font style='background-color: #A76240; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A76240;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A76240; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_BROWN = -0x1.1310e8p126F;
    static { NAMED.put("deep orange brown", -0x1.1310e8p126F); LIST.add(-0x1.1310e8p126F); }

    /**
     * This color constant "bright orange brown" has RGBA8888 code {@code D48963FF}, L 0.59607846, A 0.5294118, B 0.5372549, alpha 1.0, hue 0.14362669, saturation 0.22978139, and chroma 0.0945603.
     * It can be represented as a packed float with the constant {@code -0x1.130f3p126F}.
     * <pre>
     * <font style='background-color: #D48963;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D48963; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D48963;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D48963'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D48963'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D48963'>&nbsp;@&nbsp;</font><font style='background-color: #D48963; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D48963;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D48963; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE_BROWN = -0x1.130f3p126F;
    static { NAMED.put("bright orange brown", -0x1.130f3p126F); LIST.add(-0x1.130f3p126F); }

    /**
     * This color constant "deep brown orange" has RGBA8888 code {@code BB6128FF}, L 0.4745098, A 0.5372549, B 0.5529412, alpha 1.0, hue 0.15239218, saturation 0.632893, and chroma 0.12896542.
     * It can be represented as a packed float with the constant {@code -0x1.1b12f2p126F}.
     * <pre>
     * <font style='background-color: #BB6128;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB6128; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB6128;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BB6128'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BB6128'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BB6128'>&nbsp;@&nbsp;</font><font style='background-color: #BB6128; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB6128;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB6128; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN_ORANGE = -0x1.1b12f2p126F;
    static { NAMED.put("deep brown orange", -0x1.1b12f2p126F); LIST.add(-0x1.1b12f2p126F); }

    /**
     * This color constant "bright brown orange" has RGBA8888 code {@code E9864BFF}, L 0.6117647, A 0.5411765, B 0.5529412, alpha 1.0, hue 0.14477962, saturation 0.45877856, and chroma 0.1336143.
     * It can be represented as a packed float with the constant {@code -0x1.1b1538p126F}.
     * <pre>
     * <font style='background-color: #E9864B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9864B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9864B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E9864B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E9864B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E9864B'>&nbsp;@&nbsp;</font><font style='background-color: #E9864B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9864B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9864B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN_ORANGE = -0x1.1b1538p126F;
    static { NAMED.put("bright brown orange", -0x1.1b1538p126F); LIST.add(-0x1.1b1538p126F); }

    /**
     * This color constant "deep orange" has RGBA8888 code {@code C06011FF}, L 0.47843137, A 0.5411765, B 0.56078434, alpha 1.0, hue 0.15522522, saturation 0.8140514, and chroma 0.14626285.
     * It can be represented as a packed float with the constant {@code -0x1.1f14f4p126F}.
     * <pre>
     * <font style='background-color: #C06011;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C06011; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C06011;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C06011'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C06011'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C06011'>&nbsp;@&nbsp;</font><font style='background-color: #C06011; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C06011;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C06011; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE = -0x1.1f14f4p126F;
    static { NAMED.put("deep orange", -0x1.1f14f4p126F); LIST.add(-0x1.1f14f4p126F); }

    /**
     * This color constant "bright orange" has RGBA8888 code {@code F1893FFF}, L 0.627451, A 0.5372549, B 0.56078434, alpha 1.0, hue 0.1624788, saturation 0.5620151, and chroma 0.14202859.
     * It can be represented as a packed float with the constant {@code -0x1.1f134p126F}.
     * <pre>
     * <font style='background-color: #F1893F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F1893F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F1893F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F1893F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F1893F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F1893F'>&nbsp;@&nbsp;</font><font style='background-color: #F1893F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F1893F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F1893F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE = -0x1.1f134p126F;
    static { NAMED.put("bright orange", -0x1.1f134p126F); LIST.add(-0x1.1f134p126F); }

    /**
     * This color constant "deep saffron orange" has RGBA8888 code {@code BC6F27FF}, L 0.5019608, A 0.5294118, B 0.5568628, alpha 1.0, hue 0.17402768, saturation 0.6499009, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.1d0fp126F}.
     * <pre>
     * <font style='background-color: #BC6F27;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BC6F27; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BC6F27;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BC6F27'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BC6F27'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BC6F27'>&nbsp;@&nbsp;</font><font style='background-color: #BC6F27; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BC6F27;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BC6F27; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_SAFFRON_ORANGE = -0x1.1d0fp126F;
    static { NAMED.put("deep saffron orange", -0x1.1d0fp126F); LIST.add(-0x1.1d0fp126F); }

    /**
     * This color constant "bright saffron orange" has RGBA8888 code {@code EC984FFF}, L 0.654902, A 0.5294118, B 0.5568628, alpha 1.0, hue 0.17402768, saturation 0.47246537, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.1d0f4ep126F}.
     * <pre>
     * <font style='background-color: #EC984F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EC984F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EC984F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EC984F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EC984F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EC984F'>&nbsp;@&nbsp;</font><font style='background-color: #EC984F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EC984F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EC984F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_SAFFRON_ORANGE = -0x1.1d0f4ep126F;
    static { NAMED.put("bright saffron orange", -0x1.1d0f4ep126F); LIST.add(-0x1.1d0f4ep126F); }

    /**
     * This color constant "deep orange saffron" has RGBA8888 code {@code BD7713FF}, L 0.5176471, A 0.52156866, B 0.5647059, alpha 1.0, hue 0.19880433, saturation 0.81632656, and chroma 0.13587911.
     * It can be represented as a packed float with the constant {@code -0x1.210b08p126F}.
     * <pre>
     * <font style='background-color: #BD7713;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD7713; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD7713;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BD7713'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BD7713'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BD7713'>&nbsp;@&nbsp;</font><font style='background-color: #BD7713; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD7713;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD7713; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_SAFFRON = -0x1.210b08p126F;
    static { NAMED.put("deep orange saffron", -0x1.210b08p126F); LIST.add(-0x1.210b08p126F); }

    /**
     * This color constant "bright orange saffron" has RGBA8888 code {@code EEA243FF}, L 0.6784314, A 0.5176471, B 0.5647059, alpha 1.0, hue 0.20763123, saturation 0.5651491, and chroma 0.1336143.
     * It can be represented as a packed float with the constant {@code -0x1.21095ap126F}.
     * <pre>
     * <font style='background-color: #EEA243;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EEA243; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EEA243;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EEA243'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EEA243'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EEA243'>&nbsp;@&nbsp;</font><font style='background-color: #EEA243; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EEA243;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EEA243; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE_SAFFRON = -0x1.21095ap126F;
    static { NAMED.put("bright orange saffron", -0x1.21095ap126F); LIST.add(-0x1.21095ap126F); }

    /**
     * This color constant "deep saffron" has RGBA8888 code {@code BB8229FF}, L 0.5411765, A 0.5137255, B 0.56078434, alpha 1.0, hue 0.21465261, saturation 0.68139654, and chroma 0.124142565.
     * It can be represented as a packed float with the constant {@code -0x1.1f0714p126F}.
     * <pre>
     * <font style='background-color: #BB8229;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB8229; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB8229;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BB8229'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BB8229'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BB8229'>&nbsp;@&nbsp;</font><font style='background-color: #BB8229; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB8229;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB8229; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_SAFFRON = -0x1.1f0714p126F;
    static { NAMED.put("deep saffron", -0x1.1f0714p126F); LIST.add(-0x1.1f0714p126F); }

    /**
     * This color constant "bright saffron" has RGBA8888 code {@code EAAD52FF}, L 0.7019608, A 0.5137255, B 0.56078434, alpha 1.0, hue 0.21465261, saturation 0.467106, and chroma 0.124142565.
     * It can be represented as a packed float with the constant {@code -0x1.1f0766p126F}.
     * <pre>
     * <font style='background-color: #EAAD52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EAAD52; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EAAD52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EAAD52'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EAAD52'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EAAD52'>&nbsp;@&nbsp;</font><font style='background-color: #EAAD52; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EAAD52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EAAD52; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_SAFFRON = -0x1.1f0766p126F;
    static { NAMED.put("bright saffron", -0x1.1f0766p126F); LIST.add(-0x1.1f0766p126F); }

    /**
     * This color constant "deep yellow saffron" has RGBA8888 code {@code C39723FF}, L 0.59607846, A 0.49803922, B 0.5686275, alpha 1.0, hue 0.2545336, saturation 0.747447, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.22ff3p126F}.
     * <pre>
     * <font style='background-color: #C39723;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C39723; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C39723;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C39723'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C39723'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C39723'>&nbsp;@&nbsp;</font><font style='background-color: #C39723; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C39723;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C39723; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_SAFFRON = -0x1.22ff3p126F;
    static { NAMED.put("deep yellow saffron", -0x1.22ff3p126F); LIST.add(-0x1.22ff3p126F); }

    /**
     * This color constant "bright yellow saffron" has RGBA8888 code {@code EFC04DFF}, L 0.7529412, A 0.5019608, B 0.5686275, alpha 1.0, hue 0.24546641, saturation 0.56700194, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.23018p126F}.
     * <pre>
     * <font style='background-color: #EFC04D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFC04D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFC04D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EFC04D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EFC04D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EFC04D'>&nbsp;@&nbsp;</font><font style='background-color: #EFC04D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFC04D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFC04D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_YELLOW_SAFFRON = -0x1.23018p126F;
    static { NAMED.put("bright yellow saffron", -0x1.23018p126F); LIST.add(-0x1.23018p126F); }

    /**
     * This color constant "deep saffron yellow" has RGBA8888 code {@code C4AB33FF}, L 0.64705884, A 0.4862745, B 0.5686275, alpha 1.0, hue 0.28142345, saturation 0.6433531, and chroma 0.13942632.
     * It can be represented as a packed float with the constant {@code -0x1.22f94ap126F}.
     * <pre>
     * <font style='background-color: #C4AB33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4AB33; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4AB33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C4AB33'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C4AB33'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C4AB33'>&nbsp;@&nbsp;</font><font style='background-color: #C4AB33; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4AB33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4AB33; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_SAFFRON_YELLOW = -0x1.22f94ap126F;
    static { NAMED.put("deep saffron yellow", -0x1.22f94ap126F); LIST.add(-0x1.22f94ap126F); }

    /**
     * This color constant "bright saffron yellow" has RGBA8888 code {@code F1D75DFF}, L 0.81960785, A 0.4862745, B 0.5686275, alpha 1.0, hue 0.28142345, saturation 0.49955887, and chroma 0.13942632.
     * It can be represented as a packed float with the constant {@code -0x1.22f9a2p126F}.
     * <pre>
     * <font style='background-color: #F1D75D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F1D75D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F1D75D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F1D75D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F1D75D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F1D75D'>&nbsp;@&nbsp;</font><font style='background-color: #F1D75D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F1D75D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F1D75D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_SAFFRON_YELLOW = -0x1.22f9a2p126F;
    static { NAMED.put("bright saffron yellow", -0x1.22f9a2p126F); LIST.add(-0x1.22f9a2p126F); }

    /**
     * This color constant "deep yellow" has RGBA8888 code {@code B4B81DFF}, L 0.6627451, A 0.46666667, B 0.5764706, alpha 1.0, hue 0.31541443, saturation 0.80221605, and chroma 0.16618787.
     * It can be represented as a packed float with the constant {@code -0x1.26ef52p126F}.
     * <pre>
     * <font style='background-color: #B4B81D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4B81D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4B81D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B4B81D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B4B81D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B4B81D'>&nbsp;@&nbsp;</font><font style='background-color: #B4B81D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4B81D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4B81D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW = -0x1.26ef52p126F;
    static { NAMED.put("deep yellow", -0x1.26ef52p126F); LIST.add(-0x1.26ef52p126F); }

    /**
     * This color constant "bright yellow" has RGBA8888 code {@code E5EA53FF}, L 0.85882354, A 0.46666667, B 0.5764706, alpha 1.0, hue 0.31541443, saturation 0.58761466, and chroma 0.16618787.
     * It can be represented as a packed float with the constant {@code -0x1.26efb6p126F}.
     * <pre>
     * <font style='background-color: #E5EA53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E5EA53; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E5EA53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E5EA53'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E5EA53'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E5EA53'>&nbsp;@&nbsp;</font><font style='background-color: #E5EA53; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E5EA53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E5EA53; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_YELLOW = -0x1.26efb6p126F;
    static { NAMED.put("bright yellow", -0x1.26efb6p126F); LIST.add(-0x1.26efb6p126F); }

    /**
     * This color constant "deep lime yellow" has RGBA8888 code {@code A4BB37FF}, L 0.65882355, A 0.4627451, B 0.5686275, alpha 1.0, hue 0.32915777, saturation 0.62189984, and chroma 0.1555649.
     * It can be represented as a packed float with the constant {@code -0x1.22ed5p126F}.
     * <pre>
     * <font style='background-color: #A4BB37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4BB37; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4BB37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A4BB37'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A4BB37'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A4BB37'>&nbsp;@&nbsp;</font><font style='background-color: #A4BB37; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4BB37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4BB37; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_LIME_YELLOW = -0x1.22ed5p126F;
    static { NAMED.put("deep lime yellow", -0x1.22ed5p126F); LIST.add(-0x1.22ed5p126F); }

    /**
     * This color constant "bright lime yellow" has RGBA8888 code {@code D1EB63FF}, L 0.84313726, A 0.4627451, B 0.5686275, alpha 1.0, hue 0.32915777, saturation 0.4634378, and chroma 0.1555649.
     * It can be represented as a packed float with the constant {@code -0x1.22edaep126F}.
     * <pre>
     * <font style='background-color: #D1EB63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D1EB63; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D1EB63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D1EB63'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D1EB63'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D1EB63'>&nbsp;@&nbsp;</font><font style='background-color: #D1EB63; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D1EB63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D1EB63; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_LIME_YELLOW = -0x1.22edaep126F;
    static { NAMED.put("bright lime yellow", -0x1.22edaep126F); LIST.add(-0x1.22edaep126F); }

    /**
     * This color constant "deep yellow lime" has RGBA8888 code {@code 93BB13FF}, L 0.6392157, A 0.44705883, B 0.5764706, alpha 1.0, hue 0.34638813, saturation 0.81632656, and chroma 0.18528971.
     * It can be represented as a packed float with the constant {@code -0x1.26e546p126F}.
     * <pre>
     * <font style='background-color: #93BB13;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #93BB13; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #93BB13;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #93BB13'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #93BB13'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #93BB13'>&nbsp;@&nbsp;</font><font style='background-color: #93BB13; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #93BB13;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #93BB13; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_LIME = -0x1.26e546p126F;
    static { NAMED.put("deep yellow lime", -0x1.26e546p126F); LIST.add(-0x1.26e546p126F); }

    /**
     * This color constant "bright yellow lime" has RGBA8888 code {@code C0EC4CFF}, L 0.8235294, A 0.44705883, B 0.5764706, alpha 1.0, hue 0.34638813, saturation 0.6147121, and chroma 0.18528971.
     * It can be represented as a packed float with the constant {@code -0x1.26e5a4p126F}.
     * <pre>
     * <font style='background-color: #C0EC4C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0EC4C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0EC4C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C0EC4C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C0EC4C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C0EC4C'>&nbsp;@&nbsp;</font><font style='background-color: #C0EC4C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0EC4C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0EC4C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_YELLOW_LIME = -0x1.26e5a4p126F;
    static { NAMED.put("bright yellow lime", -0x1.26e5a4p126F); LIST.add(-0x1.26e5a4p126F); }

    /**
     * This color constant "deep lime" has RGBA8888 code {@code 82B72BFF}, L 0.6156863, A 0.44313726, B 0.5686275, alpha 1.0, hue 0.3601329, saturation 0.67072475, and chroma 0.17755185.
     * It can be represented as a packed float with the constant {@code -0x1.22e33ap126F}.
     * <pre>
     * <font style='background-color: #82B72B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #82B72B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #82B72B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #82B72B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #82B72B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #82B72B'>&nbsp;@&nbsp;</font><font style='background-color: #82B72B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #82B72B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #82B72B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_LIME = -0x1.22e33ap126F;
    static { NAMED.put("deep lime", -0x1.22e33ap126F); LIST.add(-0x1.22e33ap126F); }

    /**
     * This color constant "bright lime" has RGBA8888 code {@code AEE85AFF}, L 0.8, A 0.44313726, B 0.5686275, alpha 1.0, hue 0.3601329, saturation 0.49660477, and chroma 0.17755185.
     * It can be represented as a packed float with the constant {@code -0x1.22e398p126F}.
     * <pre>
     * <font style='background-color: #AEE85A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AEE85A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AEE85A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AEE85A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AEE85A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AEE85A'>&nbsp;@&nbsp;</font><font style='background-color: #AEE85A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AEE85A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AEE85A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_LIME = -0x1.22e398p126F;
    static { NAMED.put("bright lime", -0x1.22e398p126F); LIST.add(-0x1.22e398p126F); }

    /**
     * This color constant "deep green lime" has RGBA8888 code {@code 5FBA17FF}, L 0.6, A 0.42352942, B 0.57254905, alpha 1.0, hue 0.37918407, saturation 0.7640954, and chroma 0.209995.
     * It can be represented as a packed float with the constant {@code -0x1.24d932p126F}.
     * <pre>
     * <font style='background-color: #5FBA17;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5FBA17; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5FBA17;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5FBA17'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5FBA17'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5FBA17'>&nbsp;@&nbsp;</font><font style='background-color: #5FBA17; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5FBA17;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5FBA17; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREEN_LIME = -0x1.24d932p126F;
    static { NAMED.put("deep green lime", -0x1.24d932p126F); LIST.add(-0x1.24d932p126F); }

    /**
     * This color constant "bright green lime" has RGBA8888 code {@code 8AEC4DFF}, L 0.78039217, A 0.42352942, B 0.57254905, alpha 1.0, hue 0.37918407, saturation 0.5498216, and chroma 0.209995.
     * It can be represented as a packed float with the constant {@code -0x1.24d98ep126F}.
     * <pre>
     * <font style='background-color: #8AEC4D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8AEC4D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8AEC4D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8AEC4D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8AEC4D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8AEC4D'>&nbsp;@&nbsp;</font><font style='background-color: #8AEC4D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8AEC4D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8AEC4D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_GREEN_LIME = -0x1.24d98ep126F;
    static { NAMED.put("bright green lime", -0x1.24d98ep126F); LIST.add(-0x1.24d98ep126F); }

    /**
     * This color constant "deep lime green" has RGBA8888 code {@code 4BB930FF}, L 0.5882353, A 0.41960785, B 0.5647059, alpha 1.0, hue 0.39212817, saturation 0.66582537, and chroma 0.20558903.
     * It can be represented as a packed float with the constant {@code -0x1.20d72cp126F}.
     * <pre>
     * <font style='background-color: #4BB930;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4BB930; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4BB930;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4BB930'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4BB930'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4BB930'>&nbsp;@&nbsp;</font><font style='background-color: #4BB930; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4BB930;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4BB930; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_LIME_GREEN = -0x1.20d72cp126F;
    static { NAMED.put("deep lime green", -0x1.20d72cp126F); LIST.add(-0x1.20d72cp126F); }

    /**
     * This color constant "bright lime green" has RGBA8888 code {@code 75E85AFF}, L 0.75686276, A 0.41960785, B 0.5647059, alpha 1.0, hue 0.39212817, saturation 0.4859436, and chroma 0.20558903.
     * It can be represented as a packed float with the constant {@code -0x1.20d782p126F}.
     * <pre>
     * <font style='background-color: #75E85A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #75E85A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #75E85A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #75E85A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #75E85A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #75E85A'>&nbsp;@&nbsp;</font><font style='background-color: #75E85A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #75E85A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #75E85A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_LIME_GREEN = -0x1.20d782p126F;
    static { NAMED.put("bright lime green", -0x1.20d782p126F); LIST.add(-0x1.20d782p126F); }

    /**
     * This color constant "deep green" has RGBA8888 code {@code 15BA37FF}, L 0.5764706, A 0.40784314, B 0.56078434, alpha 1.0, hue 0.40718868, saturation 0.99303, and chroma 0.21993263.
     * It can be represented as a packed float with the constant {@code -0x1.1ed126p126F}.
     * <pre>
     * <font style='background-color: #15BA37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #15BA37; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #15BA37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #15BA37'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #15BA37'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #15BA37'>&nbsp;@&nbsp;</font><font style='background-color: #15BA37; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #15BA37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #15BA37; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREEN = -0x1.1ed126p126F;
    static { NAMED.put("deep green", -0x1.1ed126p126F); LIST.add(-0x1.1ed126p126F); }

    /**
     * This color constant "bright green" has RGBA8888 code {@code 3ED952FF}, L 0.68235296, A 0.40784314, B 0.56078434, alpha 1.0, hue 0.40718868, saturation 0.7861616, and chroma 0.21993263.
     * It can be represented as a packed float with the constant {@code -0x1.1ed15cp126F}.
     * <pre>
     * <font style='background-color: #3ED952;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3ED952; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3ED952;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3ED952'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3ED952'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3ED952'>&nbsp;@&nbsp;</font><font style='background-color: #3ED952; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3ED952;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3ED952; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_GREEN = -0x1.1ed15cp126F;
    static { NAMED.put("bright green", -0x1.1ed15cp126F); LIST.add(-0x1.1ed15cp126F); }

    /**
     * This color constant "deep cyan green" has RGBA8888 code {@code 31B079FF}, L 0.5686275, A 0.43137255, B 0.52156866, alpha 1.0, hue 0.451547, saturation 0.7815358, and chroma 0.143312.
     * It can be represented as a packed float with the constant {@code -0x1.0add22p126F}.
     * <pre>
     * <font style='background-color: #31B079;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #31B079; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #31B079;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #31B079'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #31B079'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #31B079'>&nbsp;@&nbsp;</font><font style='background-color: #31B079; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #31B079;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #31B079; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_CYAN_GREEN = -0x1.0add22p126F;
    static { NAMED.put("deep cyan green", -0x1.0add22p126F); LIST.add(-0x1.0add22p126F); }

    /**
     * This color constant "bright cyan green" has RGBA8888 code {@code 48C78DFF}, L 0.64705884, A 0.43137255, B 0.52156866, alpha 1.0, hue 0.451547, saturation 0.650163, and chroma 0.143312.
     * It can be represented as a packed float with the constant {@code -0x1.0add4ap126F}.
     * <pre>
     * <font style='background-color: #48C78D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #48C78D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #48C78D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #48C78D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #48C78D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #48C78D'>&nbsp;@&nbsp;</font><font style='background-color: #48C78D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #48C78D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #48C78D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_CYAN_GREEN = -0x1.0add4ap126F;
    static { NAMED.put("bright cyan green", -0x1.0add4ap126F); LIST.add(-0x1.0add4ap126F); }

    /**
     * This color constant "deep green cyan" has RGBA8888 code {@code 12B69BFF}, L 0.5882353, A 0.43137255, B 0.49803922, alpha 1.0, hue 0.5045336, saturation 0.97282284, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.fedd2cp125F}.
     * <pre>
     * <font style='background-color: #12B69B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #12B69B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #12B69B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #12B69B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #12B69B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #12B69B'>&nbsp;@&nbsp;</font><font style='background-color: #12B69B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #12B69B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #12B69B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREEN_CYAN = -0x1.fedd2cp125F;
    static { NAMED.put("deep green cyan", -0x1.fedd2cp125F); LIST.add(-0x1.fedd2cp125F); }

    /**
     * This color constant "bright green cyan" has RGBA8888 code {@code 3DD4B7FF}, L 0.69411767, A 0.43137255, B 0.49803922, alpha 1.0, hue 0.5045336, saturation 0.7857715, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.fedd62p125F}.
     * <pre>
     * <font style='background-color: #3DD4B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3DD4B7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3DD4B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3DD4B7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3DD4B7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3DD4B7'>&nbsp;@&nbsp;</font><font style='background-color: #3DD4B7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3DD4B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3DD4B7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_GREEN_CYAN = -0x1.fedd62p125F;
    static { NAMED.put("bright green cyan", -0x1.fedd62p125F); LIST.add(-0x1.fedd62p125F); }

    /**
     * This color constant "deep cyan" has RGBA8888 code {@code 3ABAB9FF}, L 0.61960787, A 0.44313726, B 0.4862745, alpha 1.0, hue 0.5376946, saturation 0.70620906, and chroma 0.11653464.
     * It can be represented as a packed float with the constant {@code -0x1.f8e33cp125F}.
     * <pre>
     * <font style='background-color: #3ABAB9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3ABAB9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3ABAB9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3ABAB9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3ABAB9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3ABAB9'>&nbsp;@&nbsp;</font><font style='background-color: #3ABAB9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3ABAB9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3ABAB9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_CYAN = -0x1.f8e33cp125F;
    static { NAMED.put("deep cyan", -0x1.f8e33cp125F); LIST.add(-0x1.f8e33cp125F); }

    /**
     * This color constant "bright cyan" has RGBA8888 code {@code 68EAE8FF}, L 0.79607844, A 0.44313726, B 0.4862745, alpha 1.0, hue 0.5376946, saturation 0.51676583, and chroma 0.11653464.
     * It can be represented as a packed float with the constant {@code -0x1.f8e396p125F}.
     * <pre>
     * <font style='background-color: #68EAE8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #68EAE8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #68EAE8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #68EAE8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #68EAE8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #68EAE8'>&nbsp;@&nbsp;</font><font style='background-color: #68EAE8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #68EAE8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #68EAE8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_CYAN = -0x1.f8e396p125F;
    static { NAMED.put("bright cyan", -0x1.f8e396p125F); LIST.add(-0x1.f8e396p125F); }

    /**
     * This color constant "deep blue cyan" has RGBA8888 code {@code 199CBFFF}, L 0.53333336, A 0.4509804, B 0.45882353, alpha 1.0, hue 0.6112048, saturation 0.89561015, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.eae71p125F}.
     * <pre>
     * <font style='background-color: #199CBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #199CBF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #199CBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #199CBF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #199CBF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #199CBF'>&nbsp;@&nbsp;</font><font style='background-color: #199CBF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #199CBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #199CBF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE_CYAN = -0x1.eae71p125F;
    static { NAMED.put("deep blue cyan", -0x1.eae71p125F); LIST.add(-0x1.eae71p125F); }

    /**
     * This color constant "bright blue cyan" has RGBA8888 code {@code 4DCAF0FF}, L 0.69411767, A 0.4509804, B 0.45882353, alpha 1.0, hue 0.6112048, saturation 0.6499009, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.eae762p125F}.
     * <pre>
     * <font style='background-color: #4DCAF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4DCAF0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4DCAF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4DCAF0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4DCAF0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4DCAF0'>&nbsp;@&nbsp;</font><font style='background-color: #4DCAF0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4DCAF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4DCAF0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE_CYAN = -0x1.eae762p125F;
    static { NAMED.put("bright blue cyan", -0x1.eae762p125F); LIST.add(-0x1.eae762p125F); }

    /**
     * This color constant "deep cyan blue" has RGBA8888 code {@code 237AB5FF}, L 0.44313726, A 0.46666667, B 0.44313726, alpha 1.0, hue 0.6656062, saturation 0.76235455, and chroma 0.13131043.
     * It can be represented as a packed float with the constant {@code -0x1.e2eee2p125F}.
     * <pre>
     * <font style='background-color: #237AB5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #237AB5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #237AB5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #237AB5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #237AB5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #237AB5'>&nbsp;@&nbsp;</font><font style='background-color: #237AB5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #237AB5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #237AB5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_CYAN_BLUE = -0x1.e2eee2p125F;
    static { NAMED.put("deep cyan blue", -0x1.e2eee2p125F); LIST.add(-0x1.e2eee2p125F); }

    /**
     * This color constant "bright cyan blue" has RGBA8888 code {@code 4DA5E6FF}, L 0.5921569, A 0.46666667, B 0.44313726, alpha 1.0, hue 0.6656062, saturation 0.5458278, and chroma 0.13131043.
     * It can be represented as a packed float with the constant {@code -0x1.e2ef2ep125F}.
     * <pre>
     * <font style='background-color: #4DA5E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4DA5E6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4DA5E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4DA5E6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4DA5E6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4DA5E6'>&nbsp;@&nbsp;</font><font style='background-color: #4DA5E6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4DA5E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4DA5E6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_CYAN_BLUE = -0x1.e2ef2ep125F;
    static { NAMED.put("bright cyan blue", -0x1.e2ef2ep125F); LIST.add(-0x1.e2ef2ep125F); }

    /**
     * This color constant "deep blue" has RGBA8888 code {@code 0024C0FF}, L 0.2784314, A 0.4862745, B 0.3764706, alpha 1.0, hue 0.7323789, saturation 0.74376416, and chroma 0.24760818.
     * It can be represented as a packed float with the constant {@code -0x1.c0f88ep125F}.
     * <pre>
     * <font style='background-color: #0024C0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0024C0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0024C0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0024C0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0024C0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0024C0'>&nbsp;@&nbsp;</font><font style='background-color: #0024C0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0024C0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0024C0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE = -0x1.c0f88ep125F;
    static { NAMED.put("deep blue", -0x1.c0f88ep125F); LIST.add(-0x1.c0f88ep125F); }

    /**
     * This color constant "bright blue" has RGBA8888 code {@code 164CEFFF}, L 0.38039216, A 0.48235294, B 0.3764706, alpha 1.0, hue 0.72740346, saturation 0.77051127, and chroma 0.24859223.
     * It can be represented as a packed float with the constant {@code -0x1.c0f6c2p125F}.
     * <pre>
     * <font style='background-color: #164CEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #164CEF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #164CEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #164CEF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #164CEF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #164CEF'>&nbsp;@&nbsp;</font><font style='background-color: #164CEF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #164CEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #164CEF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE = -0x1.c0f6c2p125F;
    static { NAMED.put("bright blue", -0x1.c0f6c2p125F); LIST.add(-0x1.c0f6c2p125F); }

    /**
     * This color constant "deep violet blue" has RGBA8888 code {@code 332DB7FF}, L 0.29803923, A 0.5058824, B 0.39215687, alpha 1.0, hue 0.7586634, saturation 0.5934765, and chroma 0.21516311.
     * It can be represented as a packed float with the constant {@code -0x1.c90298p125F}.
     * <pre>
     * <font style='background-color: #332DB7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #332DB7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #332DB7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #332DB7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #332DB7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #332DB7'>&nbsp;@&nbsp;</font><font style='background-color: #332DB7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #332DB7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #332DB7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET_BLUE = -0x1.c90298p125F;
    static { NAMED.put("deep violet blue", -0x1.c90298p125F); LIST.add(-0x1.c90298p125F); }

    /**
     * This color constant "bright violet blue" has RGBA8888 code {@code 4C51E3FF}, L 0.40392157, A 0.50980395, B 0.39215687, alpha 1.0, hue 0.764433, saturation 0.6314373, and chroma 0.21572971.
     * It can be represented as a packed float with the constant {@code -0x1.c904cep125F}.
     * <pre>
     * <font style='background-color: #4C51E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4C51E3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4C51E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4C51E3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4C51E3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4C51E3'>&nbsp;@&nbsp;</font><font style='background-color: #4C51E3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4C51E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4C51E3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_VIOLET_BLUE = -0x1.c904cep125F;
    static { NAMED.put("bright violet blue", -0x1.c904cep125F); LIST.add(-0x1.c904cep125F); }

    /**
     * This color constant "deep blue violet" has RGBA8888 code {@code 4E22C1FF}, L 0.3137255, A 0.5294118, B 0.3882353, alpha 1.0, hue 0.7909493, saturation 0.74036974, and chroma 0.2302369.
     * It can be represented as a packed float with the constant {@code -0x1.c70eap125F}.
     * <pre>
     * <font style='background-color: #4E22C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E22C1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E22C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4E22C1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4E22C1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4E22C1'>&nbsp;@&nbsp;</font><font style='background-color: #4E22C1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E22C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E22C1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE_VIOLET = -0x1.c70eap125F;
    static { NAMED.put("deep blue violet", -0x1.c70eap125F); LIST.add(-0x1.c70eap125F); }

    /**
     * This color constant "bright blue violet" has RGBA8888 code {@code 6E4CF3FF}, L 0.43529412, A 0.53333336, B 0.3882353, alpha 1.0, hue 0.7961206, saturation 0.7765158, and chroma 0.23234801.
     * It can be represented as a packed float with the constant {@code -0x1.c710dep125F}.
     * <pre>
     * <font style='background-color: #6E4CF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E4CF3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E4CF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6E4CF3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6E4CF3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6E4CF3'>&nbsp;@&nbsp;</font><font style='background-color: #6E4CF3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E4CF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E4CF3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE_VIOLET = -0x1.c710dep125F;
    static { NAMED.put("bright blue violet", -0x1.c710dep125F); LIST.add(-0x1.c710dep125F); }

    /**
     * This color constant "deep violet" has RGBA8888 code {@code 6B2CB6FF}, L 0.34509805, A 0.5529412, B 0.40784314, alpha 1.0, hue 0.8329952, saturation 0.64482856, and chroma 0.21173172.
     * It can be represented as a packed float with the constant {@code -0x1.d11abp125F}.
     * <pre>
     * <font style='background-color: #6B2CB6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6B2CB6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6B2CB6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6B2CB6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6B2CB6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6B2CB6'>&nbsp;@&nbsp;</font><font style='background-color: #6B2CB6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6B2CB6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6B2CB6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET = -0x1.d11abp125F;
    static { NAMED.put("deep violet", -0x1.d11abp125F); LIST.add(-0x1.d11abp125F); }

    /**
     * This color constant "bright violet" has RGBA8888 code {@code 9052E5FF}, L 0.46666667, A 0.5529412, B 0.40784314, alpha 1.0, hue 0.8329952, saturation 0.57469803, and chroma 0.21173172.
     * It can be represented as a packed float with the constant {@code -0x1.d11aeep125F}.
     * <pre>
     * <font style='background-color: #9052E5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9052E5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9052E5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9052E5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9052E5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9052E5'>&nbsp;@&nbsp;</font><font style='background-color: #9052E5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9052E5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9052E5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_VIOLET = -0x1.d11aeep125F;
    static { NAMED.put("bright violet", -0x1.d11aeep125F); LIST.add(-0x1.d11aeep125F); }

    /**
     * This color constant "deep purple violet" has RGBA8888 code {@code 7823C5FF}, L 0.36078432, A 0.5647059, B 0.4, alpha 1.0, hue 0.84141475, saturation 0.7639356, and chroma 0.2372866.
     * It can be represented as a packed float with the constant {@code -0x1.cd20b8p125F}.
     * <pre>
     * <font style='background-color: #7823C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7823C5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7823C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7823C5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7823C5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7823C5'>&nbsp;@&nbsp;</font><font style='background-color: #7823C5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7823C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7823C5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE_VIOLET = -0x1.cd20b8p125F;
    static { NAMED.put("deep purple violet", -0x1.cd20b8p125F); LIST.add(-0x1.cd20b8p125F); }

    /**
     * This color constant "bright purple violet" has RGBA8888 code {@code 9E4BF4FF}, L 0.48235294, A 0.5647059, B 0.4, alpha 1.0, hue 0.84141475, saturation 0.7424174, and chroma 0.2372866.
     * It can be represented as a packed float with the constant {@code -0x1.cd20f6p125F}.
     * <pre>
     * <font style='background-color: #9E4BF4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E4BF4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E4BF4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9E4BF4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9E4BF4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9E4BF4'>&nbsp;@&nbsp;</font><font style='background-color: #9E4BF4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E4BF4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E4BF4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURPLE_VIOLET = -0x1.cd20f6p125F;
    static { NAMED.put("bright purple violet", -0x1.cd20f6p125F); LIST.add(-0x1.cd20f6p125F); }

    /**
     * This color constant "deep violet purple" has RGBA8888 code {@code 852FBAFF}, L 0.38039216, A 0.5686275, B 0.41568628, alpha 1.0, hue 0.85874414, saturation 0.61847997, and chroma 0.21657681.
     * It can be represented as a packed float with the constant {@code -0x1.d522c2p125F}.
     * <pre>
     * <font style='background-color: #852FBA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #852FBA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #852FBA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #852FBA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #852FBA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #852FBA'>&nbsp;@&nbsp;</font><font style='background-color: #852FBA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #852FBA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #852FBA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET_PURPLE = -0x1.d522c2p125F;
    static { NAMED.put("deep violet purple", -0x1.d522c2p125F); LIST.add(-0x1.d522c2p125F); }

    /**
     * This color constant "bright violet purple" has RGBA8888 code {@code AC53E6FF}, L 0.5019608, A 0.5686275, B 0.41568628, alpha 1.0, hue 0.85874414, saturation 0.5690222, and chroma 0.21657681.
     * It can be represented as a packed float with the constant {@code -0x1.d523p125F}.
     * <pre>
     * <font style='background-color: #AC53E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC53E6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC53E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AC53E6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AC53E6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AC53E6'>&nbsp;@&nbsp;</font><font style='background-color: #AC53E6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC53E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC53E6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_VIOLET_PURPLE = -0x1.d523p125F;
    static { NAMED.put("bright violet purple", -0x1.d523p125F); LIST.add(-0x1.d523p125F); }

    /**
     * This color constant "deep purple" has RGBA8888 code {@code 9521C2FF}, L 0.39215687, A 0.58431375, B 0.4117647, alpha 1.0, hue 0.8713863, saturation 0.8020289, and chroma 0.24313073.
     * It can be represented as a packed float with the constant {@code -0x1.d32ac8p125F}.
     * <pre>
     * <font style='background-color: #9521C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9521C2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9521C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9521C2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9521C2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9521C2'>&nbsp;@&nbsp;</font><font style='background-color: #9521C2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9521C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9521C2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE = -0x1.d32ac8p125F;
    static { NAMED.put("deep purple", -0x1.d32ac8p125F); LIST.add(-0x1.d32ac8p125F); }

    /**
     * This color constant "bright purple" has RGBA8888 code {@code C04CF1FF}, L 0.52156866, A 0.58431375, B 0.4117647, alpha 1.0, hue 0.8713863, saturation 0.66196764, and chroma 0.24313073.
     * It can be represented as a packed float with the constant {@code -0x1.d32b0ap125F}.
     * <pre>
     * <font style='background-color: #C04CF1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C04CF1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C04CF1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C04CF1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C04CF1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C04CF1'>&nbsp;@&nbsp;</font><font style='background-color: #C04CF1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C04CF1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C04CF1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURPLE = -0x1.d32b0ap125F;
    static { NAMED.put("bright purple", -0x1.d32b0ap125F); LIST.add(-0x1.d32b0ap125F); }

    /**
     * This color constant "deep magenta purple" has RGBA8888 code {@code 9B31BBFF}, L 0.4117647, A 0.5803922, B 0.42352942, alpha 1.0, hue 0.878975, saturation 0.6263387, and chroma 0.2210399.
     * It can be represented as a packed float with the constant {@code -0x1.d928d2p125F}.
     * <pre>
     * <font style='background-color: #9B31BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B31BB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B31BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9B31BB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9B31BB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9B31BB'>&nbsp;@&nbsp;</font><font style='background-color: #9B31BB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B31BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B31BB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_MAGENTA_PURPLE = -0x1.d928d2p125F;
    static { NAMED.put("deep magenta purple", -0x1.d928d2p125F); LIST.add(-0x1.d928d2p125F); }

    /**
     * This color constant "bright magenta purple" has RGBA8888 code {@code C759EAFF}, L 0.54509807, A 0.5803922, B 0.42352942, alpha 1.0, hue 0.878975, saturation 0.5617297, and chroma 0.2210399.
     * It can be represented as a packed float with the constant {@code -0x1.d92916p125F}.
     * <pre>
     * <font style='background-color: #C759EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C759EA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C759EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C759EA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C759EA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C759EA'>&nbsp;@&nbsp;</font><font style='background-color: #C759EA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C759EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C759EA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA_PURPLE = -0x1.d92916p125F;
    static { NAMED.put("bright magenta purple", -0x1.d92916p125F); LIST.add(-0x1.d92916p125F); }

    /**
     * This color constant "deep purple magenta" has RGBA8888 code {@code B323C3FF}, L 0.43529412, A 0.6, B 0.42352942, alpha 1.0, hue 0.89608383, saturation 0.78420925, and chroma 0.2507922.
     * It can be represented as a packed float with the constant {@code -0x1.d932dep125F}.
     * <pre>
     * <font style='background-color: #B323C3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B323C3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B323C3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B323C3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B323C3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B323C3'>&nbsp;@&nbsp;</font><font style='background-color: #B323C3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B323C3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B323C3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE_MAGENTA = -0x1.d932dep125F;
    static { NAMED.put("deep purple magenta", -0x1.d932dep125F); LIST.add(-0x1.d932dep125F); }

    /**
     * This color constant "bright purple magenta" has RGBA8888 code {@code E14EF1FF}, L 0.5686275, A 0.6, B 0.42352942, alpha 1.0, hue 0.89608383, saturation 0.6360866, and chroma 0.2507922.
     * It can be represented as a packed float with the constant {@code -0x1.d93322p125F}.
     * <pre>
     * <font style='background-color: #E14EF1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E14EF1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E14EF1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E14EF1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E14EF1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E14EF1'>&nbsp;@&nbsp;</font><font style='background-color: #E14EF1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E14EF1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E14EF1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURPLE_MAGENTA = -0x1.d93322p125F;
    static { NAMED.put("bright purple magenta", -0x1.d93322p125F); LIST.add(-0x1.d93322p125F); }

    /**
     * This color constant "deep magenta" has RGBA8888 code {@code B830B9FF}, L 0.44705883, A 0.59607846, B 0.43529412, alpha 1.0, hue 0.90565705, saturation 0.6639715, and chroma 0.23076649.
     * It can be represented as a packed float with the constant {@code -0x1.df30e4p125F}.
     * <pre>
     * <font style='background-color: #B830B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B830B9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B830B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B830B9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B830B9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B830B9'>&nbsp;@&nbsp;</font><font style='background-color: #B830B9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B830B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B830B9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_MAGENTA = -0x1.df30e4p125F;
    static { NAMED.put("deep magenta", -0x1.df30e4p125F); LIST.add(-0x1.df30e4p125F); }

    /**
     * This color constant "bright magenta" has RGBA8888 code {@code E759E6FF}, L 0.58431375, A 0.59607846, B 0.4392157, alpha 1.0, hue 0.91021276, saturation 0.48219728, and chroma 0.22649515.
     * It can be represented as a packed float with the constant {@code -0x1.e1312ap125F}.
     * <pre>
     * <font style='background-color: #E759E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E759E6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E759E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E759E6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E759E6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E759E6'>&nbsp;@&nbsp;</font><font style='background-color: #E759E6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E759E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E759E6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA = -0x1.e1312ap125F;
    static { NAMED.put("bright magenta", -0x1.e1312ap125F); LIST.add(-0x1.e1312ap125F); }

    /**
     * This color constant "deep red magenta" has RGBA8888 code {@code C21C80FF}, L 0.41960785, A 0.60784316, B 0.4745098, alpha 1.0, hue 0.96305966, saturation 0.7921136, and chroma 0.22076361.
     * It can be represented as a packed float with the constant {@code -0x1.f336d6p125F}.
     * <pre>
     * <font style='background-color: #C21C80;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C21C80; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C21C80;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C21C80'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C21C80'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C21C80'>&nbsp;@&nbsp;</font><font style='background-color: #C21C80; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C21C80;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C21C80; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED_MAGENTA = -0x1.f336d6p125F;
    static { NAMED.put("deep red magenta", -0x1.f336d6p125F); LIST.add(-0x1.f336d6p125F); }

    /**
     * This color constant "bright red magenta" has RGBA8888 code {@code F045A4FF}, L 0.5411765, A 0.60784316, B 0.47843137, alpha 1.0, hue 0.96857655, saturation 0.61538464, and chroma 0.2190985.
     * It can be represented as a packed float with the constant {@code -0x1.f53714p125F}.
     * <pre>
     * <font style='background-color: #F045A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F045A4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F045A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F045A4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F045A4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F045A4'>&nbsp;@&nbsp;</font><font style='background-color: #F045A4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F045A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F045A4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED_MAGENTA = -0x1.f53714p125F;
    static { NAMED.put("bright red magenta", -0x1.f53714p125F); LIST.add(-0x1.f53714p125F); }

    /**
     * This color constant "deep magenta red" has RGBA8888 code {@code B92D52FF}, L 0.40392157, A 0.5882353, B 0.50980395, alpha 1.0, hue 0.017621128, saturation 0.59902114, and chroma 0.17686298.
     * It can be represented as a packed float with the constant {@code -0x1.052ccep126F}.
     * <pre>
     * <font style='background-color: #B92D52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B92D52; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B92D52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B92D52'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B92D52'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B92D52'>&nbsp;@&nbsp;</font><font style='background-color: #B92D52; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B92D52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B92D52; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_MAGENTA_RED = -0x1.052ccep126F;
    static { NAMED.put("deep magenta red", -0x1.052ccep126F); LIST.add(-0x1.052ccep126F); }

    /**
     * This color constant "bright magenta red" has RGBA8888 code {@code E55071FF}, L 0.52156866, A 0.5882353, B 0.5137255, alpha 1.0, hue 0.024573287, saturation 0.4834217, and chroma 0.17789528.
     * It can be represented as a packed float with the constant {@code -0x1.072d0ap126F}.
     * <pre>
     * <font style='background-color: #E55071;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E55071; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E55071;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E55071'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E55071'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E55071'>&nbsp;@&nbsp;</font><font style='background-color: #E55071; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E55071;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E55071; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA_RED = -0x1.072d0ap126F;
    static { NAMED.put("bright magenta red", -0x1.072d0ap126F); LIST.add(-0x1.072d0ap126F); }

    /**
     * This color constant "some brown red" has RGBA8888 code {@code FF0C00FF}, L 0.49019608, A 0.6117647, B 0.56078434, alpha 1.0, hue 0.07928106, saturation 0.8972241, and chroma 0.25345513.
     * It can be represented as a packed float with the constant {@code -0x1.1f38fap126F}.
     * <pre>
     * <font style='background-color: #FF0C00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0C00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0C00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF0C00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF0C00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF0C00'>&nbsp;@&nbsp;</font><font style='background-color: #FF0C00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0C00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0C00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_BROWN_RED = -0x1.1f38fap126F;
    static { NAMED.put("some brown red", -0x1.1f38fap126F); LIST.add(-0x1.1f38fap126F); }

    /**
     * This color constant "more brown red" has RGBA8888 code {@code F5431DFF}, L 0.5137255, A 0.5882353, B 0.56078434, alpha 1.0, hue 0.09601964, saturation 0.78947717, and chroma 0.21345432.
     * It can be represented as a packed float with the constant {@code -0x1.1f2d06p126F}.
     * <pre>
     * <font style='background-color: #F5431D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5431D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5431D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F5431D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F5431D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F5431D'>&nbsp;@&nbsp;</font><font style='background-color: #F5431D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5431D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5431D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_BROWN_RED = -0x1.1f2d06p126F;
    static { NAMED.put("more brown red", -0x1.1f2d06p126F); LIST.add(-0x1.1f2d06p126F); }

    /**
     * This color constant "more red brown" has RGBA8888 code {@code FC4400FF}, L 0.5254902, A 0.5882353, B 0.5647059, alpha 1.0, hue 0.10071799, saturation 0.85076153, and chroma 0.21798135.
     * It can be represented as a packed float with the constant {@code -0x1.212d0cp126F}.
     * <pre>
     * <font style='background-color: #FC4400;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FC4400; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FC4400;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FC4400'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FC4400'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FC4400'>&nbsp;@&nbsp;</font><font style='background-color: #FC4400; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FC4400;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FC4400; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_RED_BROWN = -0x1.212d0cp126F;
    static { NAMED.put("more red brown", -0x1.212d0cp126F); LIST.add(-0x1.212d0cp126F); }

    /**
     * This color constant "some red brown" has RGBA8888 code {@code D17557FF}, L 0.54901963, A 0.5411765, B 0.5372549, alpha 1.0, hue 0.11705489, saturation 0.26036847, and chroma 0.11062346.
     * It can be represented as a packed float with the constant {@code -0x1.131518p126F}.
     * <pre>
     * <font style='background-color: #D17557;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D17557; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D17557;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D17557'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D17557'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D17557'>&nbsp;@&nbsp;</font><font style='background-color: #D17557; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D17557;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D17557; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_RED_BROWN = -0x1.131518p126F;
    static { NAMED.put("some red brown", -0x1.131518p126F); LIST.add(-0x1.131518p126F); }

    /**
     * This color constant "some orange brown" has RGBA8888 code {@code DB734CFF}, L 0.5568628, A 0.54901963, B 0.54509807, alpha 1.0, hue 0.11837745, saturation 0.3746449, and chroma 0.13269757.
     * It can be represented as a packed float with the constant {@code -0x1.17191cp126F}.
     * <pre>
     * <font style='background-color: #DB734C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB734C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB734C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DB734C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DB734C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DB734C'>&nbsp;@&nbsp;</font><font style='background-color: #DB734C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB734C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB734C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_ORANGE_BROWN = -0x1.17191cp126F;
    static { NAMED.put("some orange brown", -0x1.17191cp126F); LIST.add(-0x1.17191cp126F); }

    /**
     * This color constant "more orange brown" has RGBA8888 code {@code F17132FF}, L 0.5803922, A 0.5568628, B 0.56078434, alpha 1.0, hue 0.1302991, saturation 0.65378684, and chroma 0.1658202.
     * It can be represented as a packed float with the constant {@code -0x1.1f1d28p126F}.
     * <pre>
     * <font style='background-color: #F17132;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F17132; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F17132;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F17132'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F17132'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F17132'>&nbsp;@&nbsp;</font><font style='background-color: #F17132; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F17132;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F17132; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_ORANGE_BROWN = -0x1.1f1d28p126F;
    static { NAMED.put("more orange brown", -0x1.1f1d28p126F); LIST.add(-0x1.1f1d28p126F); }

    /**
     * This color constant "more brown orange" has RGBA8888 code {@code CC8F6BFF}, L 0.6, A 0.5254902, B 0.53333336, alpha 1.0, hue 0.14608382, saturation 0.17959024, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.110d32p126F}.
     * <pre>
     * <font style='background-color: #CC8F6B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CC8F6B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CC8F6B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CC8F6B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CC8F6B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CC8F6B'>&nbsp;@&nbsp;</font><font style='background-color: #CC8F6B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CC8F6B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CC8F6B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_BROWN_ORANGE = -0x1.110d32p126F;
    static { NAMED.put("more brown orange", -0x1.110d32p126F); LIST.add(-0x1.110d32p126F); }

    /**
     * This color constant "some brown orange" has RGBA8888 code {@code F87E26FF}, L 0.6117647, A 0.54901963, B 0.5686275, alpha 1.0, hue 0.15127131, saturation 0.75502497, and chroma 0.16801417.
     * It can be represented as a packed float with the constant {@code -0x1.231938p126F}.
     * <pre>
     * <font style='background-color: #F87E26;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F87E26; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F87E26;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F87E26'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F87E26'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F87E26'>&nbsp;@&nbsp;</font><font style='background-color: #F87E26; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F87E26;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F87E26; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_BROWN_ORANGE = -0x1.231938p126F;
    static { NAMED.put("some brown orange", -0x1.231938p126F); LIST.add(-0x1.231938p126F); }

    /**
     * This color constant "some saffron orange" has RGBA8888 code {@code FD7D00FF}, L 0.6156863, A 0.54901963, B 0.57254905, alpha 1.0, hue 0.15541562, saturation 0.8137945, and chroma 0.17443058.
     * It can be represented as a packed float with the constant {@code -0x1.25193ap126F}.
     * <pre>
     * <font style='background-color: #FD7D00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD7D00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD7D00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FD7D00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FD7D00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FD7D00'>&nbsp;@&nbsp;</font><font style='background-color: #FD7D00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD7D00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD7D00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_SAFFRON_ORANGE = -0x1.25193ap126F;
    static { NAMED.put("some saffron orange", -0x1.25193ap126F); LIST.add(-0x1.25193ap126F); }

    /**
     * This color constant "more saffron orange" has RGBA8888 code {@code F88D23FF}, L 0.6431373, A 0.5372549, B 0.57254905, alpha 1.0, hue 0.1744969, saturation 0.766759, and chroma 0.16247371.
     * It can be represented as a packed float with the constant {@code -0x1.251348p126F}.
     * <pre>
     * <font style='background-color: #F88D23;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F88D23; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F88D23;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F88D23'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F88D23'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F88D23'>&nbsp;@&nbsp;</font><font style='background-color: #F88D23; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F88D23;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F88D23; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_SAFFRON_ORANGE = -0x1.251348p126F;
    static { NAMED.put("more saffron orange", -0x1.251348p126F); LIST.add(-0x1.251348p126F); }

    /**
     * This color constant "more orange saffron" has RGBA8888 code {@code FA9600FF}, L 0.6627451, A 0.5294118, B 0.5764706, alpha 1.0, hue 0.19157475, saturation 0.8433764, and chroma 0.1632233.
     * It can be represented as a packed float with the constant {@code -0x1.270f52p126F}.
     * <pre>
     * <font style='background-color: #FA9600;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FA9600; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FA9600;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FA9600'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FA9600'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FA9600'>&nbsp;@&nbsp;</font><font style='background-color: #FA9600; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FA9600;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FA9600; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_ORANGE_SAFFRON = -0x1.270f52p126F;
    static { NAMED.put("more orange saffron", -0x1.270f52p126F); LIST.add(-0x1.270f52p126F); }

    /**
     * This color constant "some orange saffron" has RGBA8888 code {@code F9A426FF}, L 0.69411767, A 0.52156866, B 0.5764706, alpha 1.0, hue 0.20625468, saturation 0.75939417, and chroma 0.1582875.
     * It can be represented as a packed float with the constant {@code -0x1.270b62p126F}.
     * <pre>
     * <font style='background-color: #F9A426;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F9A426; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F9A426;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F9A426'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F9A426'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F9A426'>&nbsp;@&nbsp;</font><font style='background-color: #F9A426; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F9A426;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F9A426; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_ORANGE_SAFFRON = -0x1.270b62p126F;
    static { NAMED.put("some orange saffron", -0x1.270b62p126F); LIST.add(-0x1.270b62p126F); }

    /**
     * This color constant "some yellow saffron" has RGBA8888 code {@code FCAC00FF}, L 0.7176471, A 0.5137255, B 0.5803922, alpha 1.0, hue 0.22307527, saturation 0.8000925, and chroma 0.16247371.
     * It can be represented as a packed float with the constant {@code -0x1.29076ep126F}.
     * <pre>
     * <font style='background-color: #FCAC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FCAC00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FCAC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FCAC00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FCAC00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FCAC00'>&nbsp;@&nbsp;</font><font style='background-color: #FCAC00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FCAC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FCAC00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_YELLOW_SAFFRON = -0x1.29076ep126F;
    static { NAMED.put("some yellow saffron", -0x1.29076ep126F); LIST.add(-0x1.29076ep126F); }

    /**
     * This color constant "more yellow saffron" has RGBA8888 code {@code FFC524FF}, L 0.78431374, A 0.49803922, B 0.58431375, alpha 1.0, hue 0.25368965, saturation 0.786481, and chroma 0.16801417.
     * It can be represented as a packed float with the constant {@code -0x1.2aff9p126F}.
     * <pre>
     * <font style='background-color: #FFC524;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFC524; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFC524;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFC524'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFC524'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFC524'>&nbsp;@&nbsp;</font><font style='background-color: #FFC524; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFC524;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFC524; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_YELLOW_SAFFRON = -0x1.2aff9p126F;
    static { NAMED.put("more yellow saffron", -0x1.2aff9p126F); LIST.add(-0x1.2aff9p126F); }

    /**
     * This color constant "more saffron yellow" has RGBA8888 code {@code FFDB00FF}, L 0.8392157, A 0.48235294, B 0.5921569, alpha 1.0, hue 0.2801204, saturation 0.86341786, and chroma 0.18692946.
     * It can be represented as a packed float with the constant {@code -0x1.2ef7acp126F}.
     * <pre>
     * <font style='background-color: #FFDB00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFDB00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFDB00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFDB00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFDB00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFDB00'>&nbsp;@&nbsp;</font><font style='background-color: #FFDB00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFDB00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFDB00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_SAFFRON_YELLOW = -0x1.2ef7acp126F;
    static { NAMED.put("more saffron yellow", -0x1.2ef7acp126F); LIST.add(-0x1.2ef7acp126F); }

    /**
     * This color constant "some saffron yellow" has RGBA8888 code {@code FFF932FF}, L 0.92941177, A 0.46666667, B 0.5921569, alpha 1.0, hue 0.30522364, saturation 0.75553876, and chroma 0.19523436.
     * It can be represented as a packed float with the constant {@code -0x1.2eefdap126F}.
     * <pre>
     * <font style='background-color: #FFF932;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFF932; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFF932;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFF932'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFF932'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFF932'>&nbsp;@&nbsp;</font><font style='background-color: #FFF932; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFF932;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFF932; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_SAFFRON_YELLOW = -0x1.2eefdap126F;
    static { NAMED.put("some saffron yellow", -0x1.2eefdap126F); LIST.add(-0x1.2eefdap126F); }

    /**
     * This color constant "some lime yellow" has RGBA8888 code {@code EFFE00FF}, L 0.92156863, A 0.45490196, B 0.59607846, alpha 1.0, hue 0.31984162, saturation 0.8561619, and chroma 0.21144326.
     * It can be represented as a packed float with the constant {@code -0x1.30e9d6p126F}.
     * <pre>
     * <font style='background-color: #EFFE00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFFE00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFFE00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EFFE00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EFFE00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EFFE00'>&nbsp;@&nbsp;</font><font style='background-color: #EFFE00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFFE00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFFE00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_LIME_YELLOW = -0x1.30e9d6p126F;
    static { NAMED.put("some lime yellow", -0x1.30e9d6p126F); LIST.add(-0x1.30e9d6p126F); }

    /**
     * This color constant "more lime yellow" has RGBA8888 code {@code DAFA27FF}, L 0.8901961, A 0.44705883, B 0.5921569, alpha 1.0, hue 0.33299518, saturation 0.8026774, and chroma 0.21173172.
     * It can be represented as a packed float with the constant {@code -0x1.2ee5c6p126F}.
     * <pre>
     * <font style='background-color: #DAFA27;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAFA27; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAFA27;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DAFA27'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DAFA27'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DAFA27'>&nbsp;@&nbsp;</font><font style='background-color: #DAFA27; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAFA27;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAFA27; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_LIME_YELLOW = -0x1.2ee5c6p126F;
    static { NAMED.put("more lime yellow", -0x1.2ee5c6p126F); LIST.add(-0x1.2ee5c6p126F); }

    /**
     * This color constant "more yellow lime" has RGBA8888 code {@code CAFB00FF}, L 0.8745098, A 0.4392157, B 0.5921569, alpha 1.0, hue 0.34281132, saturation 0.83812547, and chroma 0.21993263.
     * It can be represented as a packed float with the constant {@code -0x1.2ee1bep126F}.
     * <pre>
     * <font style='background-color: #CAFB00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CAFB00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CAFB00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CAFB00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CAFB00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CAFB00'>&nbsp;@&nbsp;</font><font style='background-color: #CAFB00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CAFB00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CAFB00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_YELLOW_LIME = -0x1.2ee1bep126F;
    static { NAMED.put("more yellow lime", -0x1.2ee1bep126F); LIST.add(-0x1.2ee1bep126F); }

    /**
     * This color constant "some yellow lime" has RGBA8888 code {@code B2F629FF}, L 0.8392157, A 0.43137255, B 0.5882353, alpha 1.0, hue 0.35522038, saturation 0.8060016, and chroma 0.22269051.
     * It can be represented as a packed float with the constant {@code -0x1.2cddacp126F}.
     * <pre>
     * <font style='background-color: #B2F629;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2F629; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2F629;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B2F629'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B2F629'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B2F629'>&nbsp;@&nbsp;</font><font style='background-color: #B2F629; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2F629;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2F629; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_YELLOW_LIME = -0x1.2cddacp126F;
    static { NAMED.put("some yellow lime", -0x1.2cddacp126F); LIST.add(-0x1.2cddacp126F); }

    /**
     * This color constant "some green lime" has RGBA8888 code {@code 98FD00FF}, L 0.8392157, A 0.41568628, B 0.5921569, alpha 1.0, hue 0.3679365, saturation 0.8648303, and chroma 0.24883763.
     * It can be represented as a packed float with the constant {@code -0x1.2ed5acp126F}.
     * <pre>
     * <font style='background-color: #98FD00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #98FD00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #98FD00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #98FD00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #98FD00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #98FD00'>&nbsp;@&nbsp;</font><font style='background-color: #98FD00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #98FD00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #98FD00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_GREEN_LIME = -0x1.2ed5acp126F;
    static { NAMED.put("some green lime", -0x1.2ed5acp126F); LIST.add(-0x1.2ed5acp126F); }

    /**
     * This color constant "more green lime" has RGBA8888 code {@code 7FFA20FF}, L 0.8117647, A 0.40784314, B 0.5882353, alpha 1.0, hue 0.37845665, saturation 0.8282068, and chroma 0.25417653.
     * It can be represented as a packed float with the constant {@code -0x1.2cd19ep126F}.
     * <pre>
     * <font style='background-color: #7FFA20;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7FFA20; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7FFA20;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7FFA20'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7FFA20'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7FFA20'>&nbsp;@&nbsp;</font><font style='background-color: #7FFA20; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7FFA20;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7FFA20; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_GREEN_LIME = -0x1.2cd19ep126F;
    static { NAMED.put("more green lime", -0x1.2cd19ep126F); LIST.add(-0x1.2cd19ep126F); }

    /**
     * This color constant "more lime green" has RGBA8888 code {@code 40FC00FF}, L 0.7882353, A 0.3882353, B 0.5882353, alpha 1.0, hue 0.3936267, saturation 0.8780853, and chroma 0.2836809.
     * It can be represented as a packed float with the constant {@code -0x1.2cc792p126F}.
     * <pre>
     * <font style='background-color: #40FC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #40FC00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #40FC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #40FC00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #40FC00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #40FC00'>&nbsp;@&nbsp;</font><font style='background-color: #40FC00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #40FC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #40FC00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_LIME_GREEN = -0x1.2cc792p126F;
    static { NAMED.put("more lime green", -0x1.2cc792p126F); LIST.add(-0x1.2cc792p126F); }

    /**
     * This color constant "some lime green" has RGBA8888 code {@code 26FC45FF}, L 0.78431374, A 0.3882353, B 0.5764706, alpha 1.0, hue 0.4044865, saturation 0.9074911, and chroma 0.26978588.
     * It can be represented as a packed float with the constant {@code -0x1.26c79p126F}.
     * <pre>
     * <font style='background-color: #26FC45;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #26FC45; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #26FC45;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #26FC45'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #26FC45'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #26FC45'>&nbsp;@&nbsp;</font><font style='background-color: #26FC45; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #26FC45;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #26FC45; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_LIME_GREEN = -0x1.26c79p126F;
    static { NAMED.put("some lime green", -0x1.26c79p126F); LIST.add(-0x1.26c79p126F); }

    /**
     * This color constant "some cyan green" has RGBA8888 code {@code 00FF76FF}, L 0.8, A 0.39607844, B 0.5568628, alpha 1.0, hue 0.42031258, saturation 0.9052018, and chroma 0.23599699.
     * It can be represented as a packed float with the constant {@code -0x1.1ccb98p126F}.
     * <pre>
     * <font style='background-color: #00FF76;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF76; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF76;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FF76'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FF76'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FF76'>&nbsp;@&nbsp;</font><font style='background-color: #00FF76; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF76;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF76; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_CYAN_GREEN = -0x1.1ccb98p126F;
    static { NAMED.put("some cyan green", -0x1.1ccb98p126F); LIST.add(-0x1.1ccb98p126F); }

    /**
     * This color constant "more cyan green" has RGBA8888 code {@code 28F7B1FF}, L 0.7921569, A 0.4117647, B 0.52156866, alpha 1.0, hue 0.4618454, saturation 0.875829, and chroma 0.1809568.
     * It can be represented as a packed float with the constant {@code -0x1.0ad394p126F}.
     * <pre>
     * <font style='background-color: #28F7B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #28F7B1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #28F7B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #28F7B1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #28F7B1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #28F7B1'>&nbsp;@&nbsp;</font><font style='background-color: #28F7B1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #28F7B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #28F7B1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_CYAN_GREEN = -0x1.0ad394p126F;
    static { NAMED.put("more cyan green", -0x1.0ad394p126F); LIST.add(-0x1.0ad394p126F); }

    /**
     * This color constant "more green cyan" has RGBA8888 code {@code 00FACEFF}, L 0.80784315, A 0.41568628, B 0.5058824, alpha 1.0, hue 0.48891783, saturation 0.89747614, and chroma 0.16837704.
     * It can be represented as a packed float with the constant {@code -0x1.02d59cp126F}.
     * <pre>
     * <font style='background-color: #00FACE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FACE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FACE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FACE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FACE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FACE'>&nbsp;@&nbsp;</font><font style='background-color: #00FACE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FACE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FACE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_GREEN_CYAN = -0x1.02d59cp126F;
    static { NAMED.put("more green cyan", -0x1.02d59cp126F); LIST.add(-0x1.02d59cp126F); }

    /**
     * This color constant "some green cyan" has RGBA8888 code {@code 2FFDF4FF}, L 0.8392157, A 0.42352942, B 0.48235294, alpha 1.0, hue 0.5360971, saturation 0.8869204, and chroma 0.15634763.
     * It can be represented as a packed float with the constant {@code -0x1.f6d9acp125F}.
     * <pre>
     * <font style='background-color: #2FFDF4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2FFDF4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2FFDF4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2FFDF4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2FFDF4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2FFDF4'>&nbsp;@&nbsp;</font><font style='background-color: #2FFDF4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2FFDF4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2FFDF4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_GREEN_CYAN = -0x1.f6d9acp125F;
    static { NAMED.put("some green cyan", -0x1.f6d9acp125F); LIST.add(-0x1.f6d9acp125F); }

    /**
     * This color constant "some blue cyan" has RGBA8888 code {@code 00E6F8FF}, L 0.7647059, A 0.43137255, B 0.47058824, alpha 1.0, hue 0.5644313, saturation 0.88401157, and chroma 0.14874557.
     * It can be represented as a packed float with the constant {@code -0x1.f0dd86p125F}.
     * <pre>
     * <font style='background-color: #00E6F8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00E6F8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00E6F8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00E6F8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00E6F8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00E6F8'>&nbsp;@&nbsp;</font><font style='background-color: #00E6F8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00E6F8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00E6F8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_BLUE_CYAN = -0x1.f0dd86p125F;
    static { NAMED.put("some blue cyan", -0x1.f0dd86p125F); LIST.add(-0x1.f0dd86p125F); }

    /**
     * This color constant "more blue cyan" has RGBA8888 code {@code 16C4F2FF}, L 0.6627451, A 0.44313726, B 0.45490196, alpha 1.0, hue 0.60672843, saturation 0.8780644, and chroma 0.14458403.
     * It can be represented as a packed float with the constant {@code -0x1.e8e352p125F}.
     * <pre>
     * <font style='background-color: #16C4F2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #16C4F2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #16C4F2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #16C4F2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #16C4F2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #16C4F2'>&nbsp;@&nbsp;</font><font style='background-color: #16C4F2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #16C4F2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #16C4F2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_BLUE_CYAN = -0x1.e8e352p125F;
    static { NAMED.put("more blue cyan", -0x1.e8e352p125F); LIST.add(-0x1.e8e352p125F); }

    /**
     * This color constant "more cyan blue" has RGBA8888 code {@code 00A5FEFF}, L 0.5882353, A 0.45882353, B 0.42745098, alpha 1.0, hue 0.6678337, saturation 0.8742905, and chroma 0.16618787.
     * It can be represented as a packed float with the constant {@code -0x1.daeb2cp125F}.
     * <pre>
     * <font style='background-color: #00A5FE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00A5FE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00A5FE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00A5FE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00A5FE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00A5FE'>&nbsp;@&nbsp;</font><font style='background-color: #00A5FE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00A5FE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00A5FE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_CYAN_BLUE = -0x1.daeb2cp125F;
    static { NAMED.put("more cyan blue", -0x1.daeb2cp125F); LIST.add(-0x1.daeb2cp125F); }

    /**
     * This color constant "some cyan blue" has RGBA8888 code {@code 006EF2FF}, L 0.44705883, A 0.4745098, B 0.39607844, alpha 1.0, hue 0.71171963, saturation 0.78736204, and chroma 0.21316819.
     * It can be represented as a packed float with the constant {@code -0x1.caf2e4p125F}.
     * <pre>
     * <font style='background-color: #006EF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #006EF2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #006EF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #006EF2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #006EF2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #006EF2'>&nbsp;@&nbsp;</font><font style='background-color: #006EF2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #006EF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #006EF2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_CYAN_BLUE = -0x1.caf2e4p125F;
    static { NAMED.put("some cyan blue", -0x1.caf2e4p125F); LIST.add(-0x1.caf2e4p125F); }

    /**
     * This color constant "some violet blue" has RGBA8888 code {@code 2200FFFF}, L 0.3254902, A 0.49019608, B 0.34509805, alpha 1.0, hue 0.7399465, saturation 0.943355, and chroma 0.30921122.
     * It can be represented as a packed float with the constant {@code -0x1.b0faa6p125F}.
     * <pre>
     * <font style='background-color: #2200FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2200FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2200FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2200FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2200FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2200FF'>&nbsp;@&nbsp;</font><font style='background-color: #2200FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2200FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2200FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_VIOLET_BLUE = -0x1.b0faa6p125F;
    static { NAMED.put("some violet blue", -0x1.b0faa6p125F); LIST.add(-0x1.b0faa6p125F); }

    /**
     * This color constant "more violet blue" has RGBA8888 code {@code 4322F0FF}, L 0.34509805, A 0.5137255, B 0.36078432, alpha 1.0, hue 0.7656472, saturation 0.825997, and chroma 0.27868843.
     * It can be represented as a packed float with the constant {@code -0x1.b906bp125F}.
     * <pre>
     * <font style='background-color: #4322F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4322F0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4322F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4322F0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4322F0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4322F0'>&nbsp;@&nbsp;</font><font style='background-color: #4322F0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4322F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4322F0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_VIOLET_BLUE = -0x1.b906bp125F;
    static { NAMED.put("more violet blue", -0x1.b906bp125F); LIST.add(-0x1.b906bp125F); }

    /**
     * This color constant "more blue violet" has RGBA8888 code {@code 6000FFFF}, L 0.36862746, A 0.5411765, B 0.35686275, alpha 1.0, hue 0.7945719, saturation 0.960666, and chroma 0.29672077.
     * It can be represented as a packed float with the constant {@code -0x1.b714bcp125F}.
     * <pre>
     * <font style='background-color: #6000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6000FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6000FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6000FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6000FF'>&nbsp;@&nbsp;</font><font style='background-color: #6000FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6000FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_BLUE_VIOLET = -0x1.b714bcp125F;
    static { NAMED.put("more blue violet", -0x1.b714bcp125F); LIST.add(-0x1.b714bcp125F); }

    /**
     * This color constant "some blue violet" has RGBA8888 code {@code 7824F7FF}, L 0.4, A 0.5529412, B 0.37254903, alpha 1.0, hue 0.81264865, saturation 0.82480747, and chroma 0.27494007.
     * It can be represented as a packed float with the constant {@code -0x1.bf1accp125F}.
     * <pre>
     * <font style='background-color: #7824F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7824F7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7824F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7824F7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7824F7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7824F7'>&nbsp;@&nbsp;</font><font style='background-color: #7824F7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7824F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7824F7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_BLUE_VIOLET = -0x1.bf1accp125F;
    static { NAMED.put("some blue violet", -0x1.bf1accp125F); LIST.add(-0x1.bf1accp125F); }

    /**
     * This color constant "some purple violet" has RGBA8888 code {@code 9606FFFF}, L 0.42745098, A 0.5803922, B 0.37254903, alpha 1.0, hue 0.83957285, saturation 0.95841616, and chroma 0.30019727.
     * It can be represented as a packed float with the constant {@code -0x1.bf28dap125F}.
     * <pre>
     * <font style='background-color: #9606FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9606FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9606FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9606FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9606FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9606FF'>&nbsp;@&nbsp;</font><font style='background-color: #9606FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9606FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9606FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_PURPLE_VIOLET = -0x1.bf28dap125F;
    static { NAMED.put("some purple violet", -0x1.bf28dap125F); LIST.add(-0x1.bf28dap125F); }

    /**
     * This color constant "more purple violet" has RGBA8888 code {@code 9A23F5FF}, L 0.43529412, A 0.5803922, B 0.38431373, alpha 1.0, hue 0.84666836, saturation 0.81673986, and chroma 0.28065258.
     * It can be represented as a packed float with the constant {@code -0x1.c528dep125F}.
     * <pre>
     * <font style='background-color: #9A23F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A23F5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A23F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9A23F5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9A23F5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9A23F5'>&nbsp;@&nbsp;</font><font style='background-color: #9A23F5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A23F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A23F5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_PURPLE_VIOLET = -0x1.c528dep125F;
    static { NAMED.put("more purple violet", -0x1.c528dep125F); LIST.add(-0x1.c528dep125F); }

    /**
     * This color constant "more violet purple" has RGBA8888 code {@code AE04FFFF}, L 0.45490196, A 0.59607846, B 0.38431373, alpha 1.0, hue 0.86031544, saturation 0.9306594, and chroma 0.2995867.
     * It can be represented as a packed float with the constant {@code -0x1.c530e8p125F}.
     * <pre>
     * <font style='background-color: #AE04FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE04FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE04FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AE04FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AE04FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AE04FF'>&nbsp;@&nbsp;</font><font style='background-color: #AE04FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE04FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE04FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_VIOLET_PURPLE = -0x1.c530e8p125F;
    static { NAMED.put("more violet purple", -0x1.c530e8p125F); LIST.add(-0x1.c530e8p125F); }

    /**
     * This color constant "some violet purple" has RGBA8888 code {@code B727FAFF}, L 0.47843137, A 0.59607846, B 0.39215687, alpha 1.0, hue 0.865835, saturation 0.83731335, and chroma 0.28773978.
     * It can be represented as a packed float with the constant {@code -0x1.c930f4p125F}.
     * <pre>
     * <font style='background-color: #B727FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B727FA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B727FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B727FA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B727FA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B727FA'>&nbsp;@&nbsp;</font><font style='background-color: #B727FA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B727FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B727FA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_VIOLET_PURPLE = -0x1.c930f4p125F;
    static { NAMED.put("some violet purple", -0x1.c930f4p125F); LIST.add(-0x1.c930f4p125F); }

    /**
     * This color constant "some magenta purple" has RGBA8888 code {@code CA00FFFF}, L 0.49411765, A 0.6117647, B 0.39215687, alpha 1.0, hue 0.8778395, saturation 0.94455945, and chroma 0.30940855.
     * It can be represented as a packed float with the constant {@code -0x1.c938fcp125F}.
     * <pre>
     * <font style='background-color: #CA00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CA00FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CA00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CA00FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CA00FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CA00FF'>&nbsp;@&nbsp;</font><font style='background-color: #CA00FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CA00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CA00FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_MAGENTA_PURPLE = -0x1.c938fcp125F;
    static { NAMED.put("some magenta purple", -0x1.c938fcp125F); LIST.add(-0x1.c938fcp125F); }

    /**
     * This color constant "more magenta purple" has RGBA8888 code {@code CC27F3FF}, L 0.5019608, A 0.60784316, B 0.40392157, alpha 1.0, hue 0.884165, saturation 0.81689185, and chroma 0.28773978.
     * It can be represented as a packed float with the constant {@code -0x1.cf37p125F}.
     * <pre>
     * <font style='background-color: #CC27F3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CC27F3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CC27F3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CC27F3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CC27F3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CC27F3'>&nbsp;@&nbsp;</font><font style='background-color: #CC27F3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CC27F3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CC27F3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_MAGENTA_PURPLE = -0x1.cf37p125F;
    static { NAMED.put("more magenta purple", -0x1.cf37p125F); LIST.add(-0x1.cf37p125F); }

    /**
     * This color constant "more purple magenta" has RGBA8888 code {@code EB03FFFF}, L 0.5411765, A 0.627451, B 0.40392157, alpha 1.0, hue 0.89717996, saturation 0.95033884, and chroma 0.3179697.
     * It can be represented as a packed float with the constant {@code -0x1.cf4114p125F}.
     * <pre>
     * <font style='background-color: #EB03FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EB03FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EB03FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EB03FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EB03FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EB03FF'>&nbsp;@&nbsp;</font><font style='background-color: #EB03FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EB03FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EB03FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_PURPLE_MAGENTA = -0x1.cf4114p125F;
    static { NAMED.put("more purple magenta", -0x1.cf4114p125F); LIST.add(-0x1.cf4114p125F); }

    /**
     * This color constant "some purple magenta" has RGBA8888 code {@code F32DFFFF}, L 0.5686275, A 0.62352943, B 0.4117647, alpha 1.0, hue 0.90127134, saturation 0.8394664, and chroma 0.3024255.
     * It can be represented as a packed float with the constant {@code -0x1.d33f22p125F}.
     * <pre>
     * <font style='background-color: #F32DFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F32DFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F32DFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F32DFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F32DFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F32DFF'>&nbsp;@&nbsp;</font><font style='background-color: #F32DFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F32DFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F32DFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_PURPLE_MAGENTA = -0x1.d33f22p125F;
    static { NAMED.put("some purple magenta", -0x1.d33f22p125F); LIST.add(-0x1.d33f22p125F); }

    /**
     * This color constant "some red magenta" has RGBA8888 code {@code FF00DDFF}, L 0.5529412, A 0.63529414, B 0.43529412, alpha 1.0, hue 0.92900413, saturation 0.9027429, and chroma 0.29877067.
     * It can be represented as a packed float with the constant {@code -0x1.df451ap125F}.
     * <pre>
     * <font style='background-color: #FF00DD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF00DD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF00DD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF00DD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF00DD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF00DD'>&nbsp;@&nbsp;</font><font style='background-color: #FF00DD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF00DD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF00DD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_RED_MAGENTA = -0x1.df451ap125F;
    static { NAMED.put("some red magenta", -0x1.df451ap125F); LIST.add(-0x1.df451ap125F); }

    /**
     * This color constant "more red magenta" has RGBA8888 code {@code F3269CFF}, L 0.5137255, A 0.62352943, B 0.4745098, alpha 1.0, hue 0.9676073, saturation 0.78725326, and chroma 0.25127846.
     * It can be represented as a packed float with the constant {@code -0x1.f33f06p125F}.
     * <pre>
     * <font style='background-color: #F3269C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3269C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3269C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F3269C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F3269C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F3269C'>&nbsp;@&nbsp;</font><font style='background-color: #F3269C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3269C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3269C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_RED_MAGENTA = -0x1.f33f06p125F;
    static { NAMED.put("more red magenta", -0x1.f33f06p125F); LIST.add(-0x1.f33f06p125F); }

    /**
     * This color constant "more magenta red" has RGBA8888 code {@code FF006BFF}, L 0.5058824, A 0.627451, B 0.50980395, alpha 1.0, hue 0.01221767, saturation 0.90574884, and chroma 0.25465634.
     * It can be represented as a packed float with the constant {@code -0x1.054102p126F}.
     * <pre>
     * <font style='background-color: #FF006B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF006B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF006B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF006B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF006B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF006B'>&nbsp;@&nbsp;</font><font style='background-color: #FF006B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF006B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF006B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_MAGENTA_RED = -0x1.054102p126F;
    static { NAMED.put("more magenta red", -0x1.054102p126F); LIST.add(-0x1.054102p126F); }

    /**
     * This color constant "some magenta red" has RGBA8888 code {@code F92241FF}, L 0.49411765, A 0.6117647, B 0.5411765, alpha 1.0, hue 0.056167345, saturation 0.80987656, and chroma 0.2372866.
     * It can be represented as a packed float with the constant {@code -0x1.1538fcp126F}.
     * <pre>
     * <font style='background-color: #F92241;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F92241; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F92241;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F92241'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F92241'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F92241'>&nbsp;@&nbsp;</font><font style='background-color: #F92241; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F92241;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F92241; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_MAGENTA_RED = -0x1.1538fcp126F;
    static { NAMED.put("some magenta red", -0x1.1538fcp126F); LIST.add(-0x1.1538fcp126F); }

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
