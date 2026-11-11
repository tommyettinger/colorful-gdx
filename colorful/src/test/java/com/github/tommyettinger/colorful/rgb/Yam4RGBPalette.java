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

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.ObjectIntMap;

/**
 * 256 distinct colors with names that try to describe them as a combination of their lightness, saturation, and hue.
 * All colors are defined as RGBA8888 ints with extensive JavaDocs.
 */
public class Yam4RGBPalette {
    public static final ObjectIntMap<String> NAMED = new ObjectIntMap<>(256);
    public static final IntArray LIST = new IntArray(256);
    public static final Array<String> NAMES = new Array<>(256);

    /**
     * This color constant "transparent" has RGBA8888 code {@code 00000000}, red 0.0, green 0.0, blue 0.0, alpha 0.0, hue 0.0, saturation 0.0, and lightness 0.0.
     * It has the hex RGBA8888 value {@code 0x00000000}.
     * <pre>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #000000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRANSPARENT = 0x00000000;
    static { NAMED.put("transparent", 0x00000000); LIST.add(0x00000000); NAMES.add("transparent"); }

    /**
     * This color constant "pure_black" has RGBA8888 code {@code 000000FF}, red 0.0, green 0.0, blue 0.0, alpha 1.0, hue 0.0, saturation 0.0, and lightness 0.0.
     * It has the hex RGBA8888 value {@code 0x000000ff}.
     * <pre>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #000000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int PURE_BLACK = 0x000000ff;
    static { NAMED.put("pure_black", 0x000000ff); LIST.add(0x000000ff); NAMES.add("pure_black"); }

    /**
     * This color constant "almost_black" has RGBA8888 code {@code 121212FF}, red 0.07058824, green 0.07058824, blue 0.07058824, alpha 1.0, hue 0.0, saturation 0.0, and lightness 0.07058824.
     * It has the hex RGBA8888 value {@code 0x121212ff}.
     * <pre>
     * <font style='background-color: #121212;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #121212; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #121212;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #121212'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #121212'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #121212'>&nbsp;@&nbsp;</font><font style='background-color: #121212; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #121212;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #121212; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int ALMOST_BLACK = 0x121212ff;
    static { NAMED.put("almost_black", 0x121212ff); LIST.add(0x121212ff); NAMES.add("almost_black"); }

    /**
     * This color constant "lead_black" has RGBA8888 code {@code 242424FF}, red 0.14117648, green 0.14117648, blue 0.14117648, alpha 1.0, hue 0.0, saturation 0.0, and lightness 0.14117648.
     * It has the hex RGBA8888 value {@code 0x242424ff}.
     * <pre>
     * <font style='background-color: #242424;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #242424; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #242424;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #242424'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #242424'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #242424'>&nbsp;@&nbsp;</font><font style='background-color: #242424; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #242424;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #242424; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int LEAD_BLACK = 0x242424ff;
    static { NAMED.put("lead_black", 0x242424ff); LIST.add(0x242424ff); NAMES.add("lead_black"); }

    /**
     * This color constant "black_lead" has RGBA8888 code {@code 363636FF}, red 0.21176471, green 0.21176471, blue 0.21176471, alpha 1.0, hue 0.0, saturation 0.0, and lightness 0.21176471.
     * It has the hex RGBA8888 value {@code 0x363636ff}.
     * <pre>
     * <font style='background-color: #363636;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #363636; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #363636;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #363636'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #363636'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #363636'>&nbsp;@&nbsp;</font><font style='background-color: #363636; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #363636;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #363636; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BLACK_LEAD = 0x363636ff;
    static { NAMED.put("black_lead", 0x363636ff); LIST.add(0x363636ff); NAMES.add("black_lead"); }

    /**
     * This color constant "pure_lead" has RGBA8888 code {@code 494949FF}, red 0.28627452, green 0.28627452, blue 0.28627452, alpha 1.0, hue 0.0, saturation 0.0, and lightness 0.28627452.
     * It has the hex RGBA8888 value {@code 0x494949ff}.
     * <pre>
     * <font style='background-color: #494949;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #494949; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #494949;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #494949'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #494949'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #494949'>&nbsp;@&nbsp;</font><font style='background-color: #494949; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #494949;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #494949; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int PURE_LEAD = 0x494949ff;
    static { NAMED.put("pure_lead", 0x494949ff); LIST.add(0x494949ff); NAMES.add("pure_lead"); }

    /**
     * This color constant "gray_lead" has RGBA8888 code {@code 5B5B5BFF}, red 0.35686275, green 0.35686275, blue 0.35686275, alpha 1.0, hue 0.0, saturation 0.0, and lightness 0.35686275.
     * It has the hex RGBA8888 value {@code 0x5b5b5bff}.
     * <pre>
     * <font style='background-color: #5B5B5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B5B5B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B5B5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5B5B5B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5B5B5B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5B5B5B'>&nbsp;@&nbsp;</font><font style='background-color: #5B5B5B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B5B5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B5B5B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int GRAY_LEAD = 0x5b5b5bff;
    static { NAMED.put("gray_lead", 0x5b5b5bff); LIST.add(0x5b5b5bff); NAMES.add("gray_lead"); }

    /**
     * This color constant "lead_gray" has RGBA8888 code {@code 6D6D6DFF}, red 0.42745098, green 0.42745098, blue 0.42745098, alpha 1.0, hue 0.0, saturation 0.0, and lightness 0.42745098.
     * It has the hex RGBA8888 value {@code 0x6d6d6dff}.
     * <pre>
     * <font style='background-color: #6D6D6D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D6D6D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D6D6D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6D6D6D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6D6D6D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6D6D6D'>&nbsp;@&nbsp;</font><font style='background-color: #6D6D6D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D6D6D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D6D6D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int LEAD_GRAY = 0x6d6d6dff;
    static { NAMED.put("lead_gray", 0x6d6d6dff); LIST.add(0x6d6d6dff); NAMES.add("lead_gray"); }

    /**
     * This color constant "pure_gray" has RGBA8888 code {@code 7F7F7FFF}, red 0.49803922, green 0.49803922, blue 0.49803922, alpha 1.0, hue 0.0, saturation 0.0, and lightness 0.49803922.
     * It has the hex RGBA8888 value {@code 0x7f7f7fff}.
     * <pre>
     * <font style='background-color: #7F7F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F7F7F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F7F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7F7F7F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7F7F7F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7F7F7F'>&nbsp;@&nbsp;</font><font style='background-color: #7F7F7F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F7F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F7F7F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int PURE_GRAY = 0x7f7f7fff;
    static { NAMED.put("pure_gray", 0x7f7f7fff); LIST.add(0x7f7f7fff); NAMES.add("pure_gray"); }

    /**
     * This color constant "silver_gray" has RGBA8888 code {@code 929292FF}, red 0.57254905, green 0.57254905, blue 0.57254905, alpha 1.0, hue 0.0, saturation 0.0, and lightness 0.57254905.
     * It has the hex RGBA8888 value {@code 0x929292ff}.
     * <pre>
     * <font style='background-color: #929292;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #929292; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #929292;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #929292'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #929292'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #929292'>&nbsp;@&nbsp;</font><font style='background-color: #929292; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #929292;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #929292; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int SILVER_GRAY = 0x929292ff;
    static { NAMED.put("silver_gray", 0x929292ff); LIST.add(0x929292ff); NAMES.add("silver_gray"); }

    /**
     * This color constant "gray_silver" has RGBA8888 code {@code A4A4A4FF}, red 0.6431373, green 0.6431373, blue 0.6431373, alpha 1.0, hue 0.0, saturation 0.0, and lightness 0.6431373.
     * It has the hex RGBA8888 value {@code 0xa4a4a4ff}.
     * <pre>
     * <font style='background-color: #A4A4A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4A4A4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4A4A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A4A4A4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A4A4A4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A4A4A4'>&nbsp;@&nbsp;</font><font style='background-color: #A4A4A4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4A4A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4A4A4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int GRAY_SILVER = 0xa4a4a4ff;
    static { NAMED.put("gray_silver", 0xa4a4a4ff); LIST.add(0xa4a4a4ff); NAMES.add("gray_silver"); }

    /**
     * This color constant "pure_silver" has RGBA8888 code {@code B6B6B6FF}, red 0.7137255, green 0.7137255, blue 0.7137255, alpha 1.0, hue 0.0, saturation 0.0, and lightness 0.7137255.
     * It has the hex RGBA8888 value {@code 0xb6b6b6ff}.
     * <pre>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B6B6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #B6B6B6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B6B6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int PURE_SILVER = 0xb6b6b6ff;
    static { NAMED.put("pure_silver", 0xb6b6b6ff); LIST.add(0xb6b6b6ff); NAMES.add("pure_silver"); }

    /**
     * This color constant "white_silver" has RGBA8888 code {@code C9C9C9FF}, red 0.7882353, green 0.7882353, blue 0.7882353, alpha 1.0, hue 0.0, saturation 0.0, and lightness 0.7882353.
     * It has the hex RGBA8888 value {@code 0xc9c9c9ff}.
     * <pre>
     * <font style='background-color: #C9C9C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9C9C9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9C9C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C9C9C9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C9C9C9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C9C9C9'>&nbsp;@&nbsp;</font><font style='background-color: #C9C9C9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9C9C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9C9C9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int WHITE_SILVER = 0xc9c9c9ff;
    static { NAMED.put("white_silver", 0xc9c9c9ff); LIST.add(0xc9c9c9ff); NAMES.add("white_silver"); }

    /**
     * This color constant "silver_white" has RGBA8888 code {@code DBDBDBFF}, red 0.85882354, green 0.85882354, blue 0.85882354, alpha 1.0, hue 0.0, saturation 0.0, and lightness 0.85882354.
     * It has the hex RGBA8888 value {@code 0xdbdbdbff}.
     * <pre>
     * <font style='background-color: #DBDBDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBDBDB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBDBDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DBDBDB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DBDBDB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DBDBDB'>&nbsp;@&nbsp;</font><font style='background-color: #DBDBDB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBDBDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBDBDB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int SILVER_WHITE = 0xdbdbdbff;
    static { NAMED.put("silver_white", 0xdbdbdbff); LIST.add(0xdbdbdbff); NAMES.add("silver_white"); }

    /**
     * This color constant "almost_white" has RGBA8888 code {@code EDEDEDFF}, red 0.92941177, green 0.92941177, blue 0.92941177, alpha 1.0, hue 0.0, saturation 0.0, and lightness 0.92941177.
     * It has the hex RGBA8888 value {@code 0xedededff}.
     * <pre>
     * <font style='background-color: #EDEDED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDEDED; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDEDED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EDEDED'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EDEDED'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EDEDED'>&nbsp;@&nbsp;</font><font style='background-color: #EDEDED; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDEDED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDEDED; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int ALMOST_WHITE = 0xedededff;
    static { NAMED.put("almost_white", 0xedededff); LIST.add(0xedededff); NAMES.add("almost_white"); }

    /**
     * This color constant "pure_white" has RGBA8888 code {@code FFFFFFFF}, red 1.0, green 1.0, blue 1.0, alpha 1.0, hue 0.0, saturation 0.0, and lightness 1.0.
     * It has the hex RGBA8888 value {@code 0xffffffff}.
     * <pre>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #FFFFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int PURE_WHITE = 0xffffffff;
    static { NAMED.put("pure_white", 0xffffffff); LIST.add(0xffffffff); NAMES.add("pure_white"); }

    /**
     * This color constant "black_red" has RGBA8888 code {@code 51302DFF}, red 0.31764707, green 0.1882353, blue 0.1764706, alpha 1.0, hue 0.013888887, saturation 0.14117648, and lightness 0.24705884.
     * It has the hex RGBA8888 value {@code 0x51302dff}.
     * <pre>
     * <font style='background-color: #51302D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #51302D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #51302D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #51302D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #51302D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #51302D'>&nbsp;@&nbsp;</font><font style='background-color: #51302D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #51302D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #51302D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BLACK_RED = 0x51302dff;
    static { NAMED.put("black_red", 0x51302dff); LIST.add(0x51302dff); NAMES.add("black_red"); }

    /**
     * This color constant "lead_red" has RGBA8888 code {@code 7E5752FF}, red 0.49411765, green 0.34117648, blue 0.32156864, alpha 1.0, hue 0.018939395, saturation 0.17254901, and lightness 0.40784314.
     * It has the hex RGBA8888 value {@code 0x7e5752ff}.
     * <pre>
     * <font style='background-color: #7E5752;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E5752; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E5752;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7E5752'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7E5752'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7E5752'>&nbsp;@&nbsp;</font><font style='background-color: #7E5752; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E5752;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E5752; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int LEAD_RED = 0x7e5752ff;
    static { NAMED.put("lead_red", 0x7e5752ff); LIST.add(0x7e5752ff); NAMES.add("lead_red"); }

    /**
     * This color constant "gray_red" has RGBA8888 code {@code A97D78FF}, red 0.6627451, green 0.49019608, blue 0.47058824, alpha 1.0, hue 0.017006801, saturation 0.19215688, and lightness 0.56666666.
     * It has the hex RGBA8888 value {@code 0xa97d78ff}.
     * <pre>
     * <font style='background-color: #A97D78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A97D78; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A97D78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A97D78'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A97D78'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A97D78'>&nbsp;@&nbsp;</font><font style='background-color: #A97D78; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A97D78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A97D78; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int GRAY_RED = 0xa97d78ff;
    static { NAMED.put("gray_red", 0xa97d78ff); LIST.add(0xa97d78ff); NAMES.add("gray_red"); }

    /**
     * This color constant "silver_red" has RGBA8888 code {@code D4A49EFF}, red 0.83137256, green 0.6431373, blue 0.61960787, alpha 1.0, hue 0.018518519, saturation 0.2117647, and lightness 0.7254902.
     * It has the hex RGBA8888 value {@code 0xd4a49eff}.
     * <pre>
     * <font style='background-color: #D4A49E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4A49E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4A49E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D4A49E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D4A49E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D4A49E'>&nbsp;@&nbsp;</font><font style='background-color: #D4A49E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4A49E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4A49E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int SILVER_RED = 0xd4a49eff;
    static { NAMED.put("silver_red", 0xd4a49eff); LIST.add(0xd4a49eff); NAMES.add("silver_red"); }

    /**
     * This color constant "white_red" has RGBA8888 code {@code FECBC4FF}, red 0.99607843, green 0.79607844, blue 0.76862746, alpha 1.0, hue 0.020114943, saturation 0.22745097, and lightness 0.88235295.
     * It has the hex RGBA8888 value {@code 0xfecbc4ff}.
     * <pre>
     * <font style='background-color: #FECBC4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FECBC4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FECBC4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FECBC4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FECBC4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FECBC4'>&nbsp;@&nbsp;</font><font style='background-color: #FECBC4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FECBC4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FECBC4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int WHITE_RED = 0xfecbc4ff;
    static { NAMED.put("white_red", 0xfecbc4ff); LIST.add(0xfecbc4ff); NAMES.add("white_red"); }

    /**
     * This color constant "black_brown" has RGBA8888 code {@code 453933FF}, red 0.27058825, green 0.22352941, blue 0.2, alpha 1.0, hue 0.055555545, saturation 0.070588246, and lightness 0.23529412.
     * It has the hex RGBA8888 value {@code 0x453933ff}.
     * <pre>
     * <font style='background-color: #453933;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #453933; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #453933;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #453933'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #453933'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #453933'>&nbsp;@&nbsp;</font><font style='background-color: #453933; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #453933;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #453933; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BLACK_BROWN = 0x453933ff;
    static { NAMED.put("black_brown", 0x453933ff); LIST.add(0x453933ff); NAMES.add("black_brown"); }

    /**
     * This color constant "lead_brown" has RGBA8888 code {@code 71615BFF}, red 0.44313726, green 0.38039216, blue 0.35686275, alpha 1.0, hue 0.045454547, saturation 0.086274505, and lightness 0.4.
     * It has the hex RGBA8888 value {@code 0x71615bff}.
     * <pre>
     * <font style='background-color: #71615B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #71615B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #71615B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #71615B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #71615B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #71615B'>&nbsp;@&nbsp;</font><font style='background-color: #71615B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #71615B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #71615B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int LEAD_BROWN = 0x71615bff;
    static { NAMED.put("lead_brown", 0x71615bff); LIST.add(0x71615bff); NAMES.add("lead_brown"); }

    /**
     * This color constant "gray_brown" has RGBA8888 code {@code 9B8A83FF}, red 0.60784316, green 0.5411765, blue 0.5137255, alpha 1.0, hue 0.048611112, saturation 0.09411764, and lightness 0.56078434.
     * It has the hex RGBA8888 value {@code 0x9b8a83ff}.
     * <pre>
     * <font style='background-color: #9B8A83;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B8A83; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B8A83;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9B8A83'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9B8A83'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9B8A83'>&nbsp;@&nbsp;</font><font style='background-color: #9B8A83; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B8A83;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B8A83; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int GRAY_BROWN = 0x9b8a83ff;
    static { NAMED.put("gray_brown", 0x9b8a83ff); LIST.add(0x9b8a83ff); NAMES.add("gray_brown"); }

    /**
     * This color constant "silver_brown" has RGBA8888 code {@code C5B2AAFF}, red 0.77254903, green 0.69803923, blue 0.6666667, alpha 1.0, hue 0.049382716, saturation 0.10588235, and lightness 0.71960783.
     * It has the hex RGBA8888 value {@code 0xc5b2aaff}.
     * <pre>
     * <font style='background-color: #C5B2AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C5B2AA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C5B2AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C5B2AA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C5B2AA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C5B2AA'>&nbsp;@&nbsp;</font><font style='background-color: #C5B2AA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C5B2AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C5B2AA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int SILVER_BROWN = 0xc5b2aaff;
    static { NAMED.put("silver_brown", 0xc5b2aaff); LIST.add(0xc5b2aaff); NAMES.add("silver_brown"); }

    /**
     * This color constant "white_brown" has RGBA8888 code {@code EFDBD2FF}, red 0.9372549, green 0.85882354, blue 0.8235294, alpha 1.0, hue 0.05172414, saturation 0.11372548, and lightness 0.8803922.
     * It has the hex RGBA8888 value {@code 0xefdbd2ff}.
     * <pre>
     * <font style='background-color: #EFDBD2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFDBD2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFDBD2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EFDBD2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EFDBD2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EFDBD2'>&nbsp;@&nbsp;</font><font style='background-color: #EFDBD2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFDBD2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFDBD2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int WHITE_BROWN = 0xefdbd2ff;
    static { NAMED.put("white_brown", 0xefdbd2ff); LIST.add(0xefdbd2ff); NAMES.add("white_brown"); }

    /**
     * This color constant "black_orange" has RGBA8888 code {@code 4E392CFF}, red 0.30588236, green 0.22352941, blue 0.17254902, alpha 1.0, hue 0.06372548, saturation 0.13333334, and lightness 0.23921569.
     * It has the hex RGBA8888 value {@code 0x4e392cff}.
     * <pre>
     * <font style='background-color: #4E392C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E392C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E392C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4E392C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4E392C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4E392C'>&nbsp;@&nbsp;</font><font style='background-color: #4E392C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E392C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E392C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BLACK_ORANGE = 0x4e392cff;
    static { NAMED.put("black_orange", 0x4e392cff); LIST.add(0x4e392cff); NAMES.add("black_orange"); }

    /**
     * This color constant "lead_orange" has RGBA8888 code {@code 7B6254FF}, red 0.48235294, green 0.38431373, blue 0.32941177, alpha 1.0, hue 0.05982906, saturation 0.15294117, and lightness 0.40588236.
     * It has the hex RGBA8888 value {@code 0x7b6254ff}.
     * <pre>
     * <font style='background-color: #7B6254;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B6254; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B6254;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7B6254'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7B6254'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7B6254'>&nbsp;@&nbsp;</font><font style='background-color: #7B6254; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B6254;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B6254; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int LEAD_ORANGE = 0x7b6254ff;
    static { NAMED.put("lead_orange", 0x7b6254ff); LIST.add(0x7b6254ff); NAMES.add("lead_orange"); }

    /**
     * This color constant "gray_orange" has RGBA8888 code {@code A78B7AFF}, red 0.654902, green 0.54509807, blue 0.47843137, alpha 1.0, hue 0.06296299, saturation 0.17647061, and lightness 0.56666666.
     * It has the hex RGBA8888 value {@code 0xa78b7aff}.
     * <pre>
     * <font style='background-color: #A78B7A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A78B7A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A78B7A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A78B7A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A78B7A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A78B7A'>&nbsp;@&nbsp;</font><font style='background-color: #A78B7A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A78B7A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A78B7A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int GRAY_ORANGE = 0xa78b7aff;
    static { NAMED.put("gray_orange", 0xa78b7aff); LIST.add(0xa78b7aff); NAMES.add("gray_orange"); }

    /**
     * This color constant "silver_orange" has RGBA8888 code {@code D3B5A3FF}, red 0.827451, green 0.70980394, blue 0.6392157, alpha 1.0, hue 0.0625, saturation 0.18823528, and lightness 0.73333335.
     * It has the hex RGBA8888 value {@code 0xd3b5a3ff}.
     * <pre>
     * <font style='background-color: #D3B5A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3B5A3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3B5A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D3B5A3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D3B5A3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D3B5A3'>&nbsp;@&nbsp;</font><font style='background-color: #D3B5A3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3B5A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3B5A3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int SILVER_ORANGE = 0xd3b5a3ff;
    static { NAMED.put("silver_orange", 0xd3b5a3ff); LIST.add(0xd3b5a3ff); NAMES.add("silver_orange"); }

    /**
     * This color constant "white_orange" has RGBA8888 code {@code FEDDCAFF}, red 0.99607843, green 0.8666667, blue 0.7921569, alpha 1.0, hue 0.060897436, saturation 0.20392156, and lightness 0.89411765.
     * It has the hex RGBA8888 value {@code 0xfeddcaff}.
     * <pre>
     * <font style='background-color: #FEDDCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FEDDCA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FEDDCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FEDDCA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FEDDCA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FEDDCA'>&nbsp;@&nbsp;</font><font style='background-color: #FEDDCA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FEDDCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FEDDCA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int WHITE_ORANGE = 0xfeddcaff;
    static { NAMED.put("white_orange", 0xfeddcaff); LIST.add(0xfeddcaff); NAMES.add("white_orange"); }

    /**
     * This color constant "black_saffron" has RGBA8888 code {@code 4B422DFF}, red 0.29411766, green 0.25882354, blue 0.1764706, alpha 1.0, hue 0.11666667, saturation 0.11764707, and lightness 0.23529413.
     * It has the hex RGBA8888 value {@code 0x4b422dff}.
     * <pre>
     * <font style='background-color: #4B422D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B422D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B422D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4B422D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4B422D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4B422D'>&nbsp;@&nbsp;</font><font style='background-color: #4B422D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B422D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B422D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BLACK_SAFFRON = 0x4b422dff;
    static { NAMED.put("black_saffron", 0x4b422dff); LIST.add(0x4b422dff); NAMES.add("black_saffron"); }

    /**
     * This color constant "lead_saffron" has RGBA8888 code {@code 776D54FF}, red 0.46666667, green 0.42745098, blue 0.32941177, alpha 1.0, hue 0.11904762, saturation 0.1372549, and lightness 0.39803922.
     * It has the hex RGBA8888 value {@code 0x776d54ff}.
     * <pre>
     * <font style='background-color: #776D54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #776D54; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #776D54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #776D54'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #776D54'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #776D54'>&nbsp;@&nbsp;</font><font style='background-color: #776D54; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #776D54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #776D54; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int LEAD_SAFFRON = 0x776d54ff;
    static { NAMED.put("lead_saffron", 0x776d54ff); LIST.add(0x776d54ff); NAMES.add("lead_saffron"); }

    /**
     * This color constant "gray_saffron" has RGBA8888 code {@code A2987CFF}, red 0.63529414, green 0.59607846, blue 0.4862745, alpha 1.0, hue 0.122807026, saturation 0.14901963, and lightness 0.56078434.
     * It has the hex RGBA8888 value {@code 0xa2987cff}.
     * <pre>
     * <font style='background-color: #A2987C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2987C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2987C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A2987C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A2987C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A2987C'>&nbsp;@&nbsp;</font><font style='background-color: #A2987C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2987C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2987C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int GRAY_SAFFRON = 0xa2987cff;
    static { NAMED.put("gray_saffron", 0xa2987cff); LIST.add(0xa2987cff); NAMES.add("gray_saffron"); }

    /**
     * This color constant "silver_saffron" has RGBA8888 code {@code CEC3A5FF}, red 0.80784315, green 0.7647059, blue 0.64705884, alpha 1.0, hue 0.12195122, saturation 0.1607843, and lightness 0.727451.
     * It has the hex RGBA8888 value {@code 0xcec3a5ff}.
     * <pre>
     * <font style='background-color: #CEC3A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CEC3A5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CEC3A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CEC3A5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CEC3A5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CEC3A5'>&nbsp;@&nbsp;</font><font style='background-color: #CEC3A5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CEC3A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CEC3A5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int SILVER_SAFFRON = 0xcec3a5ff;
    static { NAMED.put("silver_saffron", 0xcec3a5ff); LIST.add(0xcec3a5ff); NAMES.add("silver_saffron"); }

    /**
     * This color constant "white_saffron" has RGBA8888 code {@code FAEECEFF}, red 0.98039216, green 0.93333334, blue 0.80784315, alpha 1.0, hue 0.121212125, saturation 0.17254901, and lightness 0.89411765.
     * It has the hex RGBA8888 value {@code 0xfaeeceff}.
     * <pre>
     * <font style='background-color: #FAEECE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FAEECE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FAEECE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FAEECE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FAEECE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FAEECE'>&nbsp;@&nbsp;</font><font style='background-color: #FAEECE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FAEECE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FAEECE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int WHITE_SAFFRON = 0xfaeeceff;
    static { NAMED.put("white_saffron", 0xfaeeceff); LIST.add(0xfaeeceff); NAMES.add("white_saffron"); }

    /**
     * This color constant "black_yellow" has RGBA8888 code {@code 47472AFF}, red 0.2784314, green 0.2784314, blue 0.16470589, alpha 1.0, hue 0.16666666, saturation 0.1137255, and lightness 0.22156863.
     * It has the hex RGBA8888 value {@code 0x47472aff}.
     * <pre>
     * <font style='background-color: #47472A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #47472A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #47472A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #47472A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #47472A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #47472A'>&nbsp;@&nbsp;</font><font style='background-color: #47472A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #47472A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #47472A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BLACK_YELLOW = 0x47472aff;
    static { NAMED.put("black_yellow", 0x47472aff); LIST.add(0x47472aff); NAMES.add("black_yellow"); }

    /**
     * This color constant "lead_yellow" has RGBA8888 code {@code 727351FF}, red 0.44705883, green 0.4509804, blue 0.31764707, alpha 1.0, hue 0.17156863, saturation 0.13333333, and lightness 0.38431373.
     * It has the hex RGBA8888 value {@code 0x727351ff}.
     * <pre>
     * <font style='background-color: #727351;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #727351; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #727351;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #727351'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #727351'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #727351'>&nbsp;@&nbsp;</font><font style='background-color: #727351; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #727351;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #727351; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int LEAD_YELLOW = 0x727351ff;
    static { NAMED.put("lead_yellow", 0x727351ff); LIST.add(0x727351ff); NAMES.add("lead_yellow"); }

    /**
     * This color constant "gray_yellow" has RGBA8888 code {@code 9E9E79FF}, red 0.61960787, green 0.61960787, blue 0.4745098, alpha 1.0, hue 0.16666667, saturation 0.14509806, and lightness 0.5470588.
     * It has the hex RGBA8888 value {@code 0x9e9e79ff}.
     * <pre>
     * <font style='background-color: #9E9E79;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E9E79; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E9E79;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9E9E79'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9E9E79'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9E9E79'>&nbsp;@&nbsp;</font><font style='background-color: #9E9E79; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E9E79;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E9E79; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int GRAY_YELLOW = 0x9e9e79ff;
    static { NAMED.put("gray_yellow", 0x9e9e79ff); LIST.add(0x9e9e79ff); NAMES.add("gray_yellow"); }

    /**
     * This color constant "silver_yellow" has RGBA8888 code {@code C9CAA2FF}, red 0.7882353, green 0.7921569, blue 0.63529414, alpha 1.0, hue 0.17083335, saturation 0.15686274, and lightness 0.7137255.
     * It has the hex RGBA8888 value {@code 0xc9caa2ff}.
     * <pre>
     * <font style='background-color: #C9CAA2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9CAA2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9CAA2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C9CAA2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C9CAA2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C9CAA2'>&nbsp;@&nbsp;</font><font style='background-color: #C9CAA2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9CAA2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9CAA2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int SILVER_YELLOW = 0xc9caa2ff;
    static { NAMED.put("silver_yellow", 0xc9caa2ff); LIST.add(0xc9caa2ff); NAMES.add("silver_yellow"); }

    /**
     * This color constant "white_yellow" has RGBA8888 code {@code F4F5CBFF}, red 0.95686275, green 0.9607843, blue 0.79607844, alpha 1.0, hue 0.17063493, saturation 0.16470587, and lightness 0.8784314.
     * It has the hex RGBA8888 value {@code 0xf4f5cbff}.
     * <pre>
     * <font style='background-color: #F4F5CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F4F5CB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F4F5CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F4F5CB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F4F5CB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F4F5CB'>&nbsp;@&nbsp;</font><font style='background-color: #F4F5CB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F4F5CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F4F5CB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int WHITE_YELLOW = 0xf4f5cbff;
    static { NAMED.put("white_yellow", 0xf4f5cbff); LIST.add(0xf4f5cbff); NAMES.add("white_yellow"); }

    /**
     * This color constant "black_lime" has RGBA8888 code {@code 40482EFF}, red 0.2509804, green 0.28235295, blue 0.18039216, alpha 1.0, hue 0.21794873, saturation 0.10196079, and lightness 0.23137255.
     * It has the hex RGBA8888 value {@code 0x40482eff}.
     * <pre>
     * <font style='background-color: #40482E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #40482E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #40482E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #40482E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #40482E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #40482E'>&nbsp;@&nbsp;</font><font style='background-color: #40482E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #40482E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #40482E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BLACK_LIME = 0x40482eff;
    static { NAMED.put("black_lime", 0x40482eff); LIST.add(0x40482eff); NAMES.add("black_lime"); }

    /**
     * This color constant "lead_lime" has RGBA8888 code {@code 6B7456FF}, red 0.41960785, green 0.45490196, blue 0.3372549, alpha 1.0, hue 0.21666667, saturation 0.11764705, and lightness 0.3960784.
     * It has the hex RGBA8888 value {@code 0x6b7456ff}.
     * <pre>
     * <font style='background-color: #6B7456;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6B7456; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6B7456;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6B7456'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6B7456'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6B7456'>&nbsp;@&nbsp;</font><font style='background-color: #6B7456; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6B7456;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6B7456; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int LEAD_LIME = 0x6b7456ff;
    static { NAMED.put("lead_lime", 0x6b7456ff); LIST.add(0x6b7456ff); NAMES.add("lead_lime"); }

    /**
     * This color constant "gray_lime" has RGBA8888 code {@code 959E7DFF}, red 0.58431375, green 0.61960787, blue 0.49019608, alpha 1.0, hue 0.21212122, saturation 0.12941179, and lightness 0.55490196.
     * It has the hex RGBA8888 value {@code 0x959e7dff}.
     * <pre>
     * <font style='background-color: #959E7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #959E7D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #959E7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #959E7D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #959E7D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #959E7D'>&nbsp;@&nbsp;</font><font style='background-color: #959E7D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #959E7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #959E7D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int GRAY_LIME = 0x959e7dff;
    static { NAMED.put("gray_lime", 0x959e7dff); LIST.add(0x959e7dff); NAMES.add("gray_lime"); }

    /**
     * This color constant "silver_lime" has RGBA8888 code {@code BFCAA6FF}, red 0.7490196, green 0.7921569, blue 0.6509804, alpha 1.0, hue 0.2175926, saturation 0.14117646, and lightness 0.72156864.
     * It has the hex RGBA8888 value {@code 0xbfcaa6ff}.
     * <pre>
     * <font style='background-color: #BFCAA6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFCAA6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFCAA6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BFCAA6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BFCAA6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BFCAA6'>&nbsp;@&nbsp;</font><font style='background-color: #BFCAA6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFCAA6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFCAA6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int SILVER_LIME = 0xbfcaa6ff;
    static { NAMED.put("silver_lime", 0xbfcaa6ff); LIST.add(0xbfcaa6ff); NAMES.add("silver_lime"); }

    /**
     * This color constant "white_lime" has RGBA8888 code {@code EAF6D0FF}, red 0.91764706, green 0.9647059, blue 0.8156863, alpha 1.0, hue 0.21929826, saturation 0.1490196, and lightness 0.8901961.
     * It has the hex RGBA8888 value {@code 0xeaf6d0ff}.
     * <pre>
     * <font style='background-color: #EAF6D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EAF6D0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EAF6D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EAF6D0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EAF6D0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EAF6D0'>&nbsp;@&nbsp;</font><font style='background-color: #EAF6D0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EAF6D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EAF6D0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int WHITE_LIME = 0xeaf6d0ff;
    static { NAMED.put("white_lime", 0xeaf6d0ff); LIST.add(0xeaf6d0ff); NAMES.add("white_lime"); }

    /**
     * This color constant "black_green" has RGBA8888 code {@code 324B2EFF}, red 0.19607843, green 0.29411766, blue 0.18039216, alpha 1.0, hue 0.31034485, saturation 0.1137255, and lightness 0.23725492.
     * It has the hex RGBA8888 value {@code 0x324b2eff}.
     * <pre>
     * <font style='background-color: #324B2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #324B2E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #324B2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #324B2E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #324B2E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #324B2E'>&nbsp;@&nbsp;</font><font style='background-color: #324B2E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #324B2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #324B2E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BLACK_GREEN = 0x324b2eff;
    static { NAMED.put("black_green", 0x324b2eff); LIST.add(0x324b2eff); NAMES.add("black_green"); }

    /**
     * This color constant "lead_green" has RGBA8888 code {@code 5A7654FF}, red 0.3529412, green 0.4627451, blue 0.32941177, alpha 1.0, hue 0.30392158, saturation 0.13333333, and lightness 0.39607844.
     * It has the hex RGBA8888 value {@code 0x5a7654ff}.
     * <pre>
     * <font style='background-color: #5A7654;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A7654; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A7654;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5A7654'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5A7654'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5A7654'>&nbsp;@&nbsp;</font><font style='background-color: #5A7654; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A7654;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A7654; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int LEAD_GREEN = 0x5a7654ff;
    static { NAMED.put("lead_green", 0x5a7654ff); LIST.add(0x5a7654ff); NAMES.add("lead_green"); }

    /**
     * This color constant "gray_green" has RGBA8888 code {@code 83A27CFF}, red 0.5137255, green 0.63529414, blue 0.4862745, alpha 1.0, hue 0.30263156, saturation 0.14901963, and lightness 0.56078434.
     * It has the hex RGBA8888 value {@code 0x83a27cff}.
     * <pre>
     * <font style='background-color: #83A27C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #83A27C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #83A27C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #83A27C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #83A27C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #83A27C'>&nbsp;@&nbsp;</font><font style='background-color: #83A27C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #83A27C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #83A27C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int GRAY_GREEN = 0x83a27cff;
    static { NAMED.put("gray_green", 0x83a27cff); LIST.add(0x83a27cff); NAMES.add("gray_green"); }

    /**
     * This color constant "silver_green" has RGBA8888 code {@code ABCDA4FF}, red 0.67058825, green 0.8039216, blue 0.6431373, alpha 1.0, hue 0.30487806, saturation 0.1607843, and lightness 0.7235294.
     * It has the hex RGBA8888 value {@code 0xabcda4ff}.
     * <pre>
     * <font style='background-color: #ABCDA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABCDA4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABCDA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ABCDA4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ABCDA4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ABCDA4'>&nbsp;@&nbsp;</font><font style='background-color: #ABCDA4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABCDA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABCDA4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int SILVER_GREEN = 0xabcda4ff;
    static { NAMED.put("silver_green", 0xabcda4ff); LIST.add(0xabcda4ff); NAMES.add("silver_green"); }

    /**
     * This color constant "white_green" has RGBA8888 code {@code D4F8CCFF}, red 0.83137256, green 0.972549, blue 0.8, alpha 1.0, hue 0.3030303, saturation 0.17254901, and lightness 0.8862745.
     * It has the hex RGBA8888 value {@code 0xd4f8ccff}.
     * <pre>
     * <font style='background-color: #D4F8CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4F8CC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4F8CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D4F8CC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D4F8CC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D4F8CC'>&nbsp;@&nbsp;</font><font style='background-color: #D4F8CC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4F8CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4F8CC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int WHITE_GREEN = 0xd4f8ccff;
    static { NAMED.put("white_green", 0xd4f8ccff); LIST.add(0xd4f8ccff); NAMES.add("white_green"); }

    /**
     * This color constant "black_cyan" has RGBA8888 code {@code 304848FF}, red 0.1882353, green 0.28235295, blue 0.28235295, alpha 1.0, hue 0.5, saturation 0.09411766, and lightness 0.23529412.
     * It has the hex RGBA8888 value {@code 0x304848ff}.
     * <pre>
     * <font style='background-color: #304848;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #304848; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #304848;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #304848'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #304848'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #304848'>&nbsp;@&nbsp;</font><font style='background-color: #304848; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #304848;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #304848; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BLACK_CYAN = 0x304848ff;
    static { NAMED.put("black_cyan", 0x304848ff); LIST.add(0x304848ff); NAMES.add("black_cyan"); }

    /**
     * This color constant "lead_cyan" has RGBA8888 code {@code 597474FF}, red 0.34901962, green 0.45490196, blue 0.45490196, alpha 1.0, hue 0.5, saturation 0.10588235, and lightness 0.4019608.
     * It has the hex RGBA8888 value {@code 0x597474ff}.
     * <pre>
     * <font style='background-color: #597474;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #597474; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #597474;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #597474'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #597474'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #597474'>&nbsp;@&nbsp;</font><font style='background-color: #597474; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #597474;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #597474; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int LEAD_CYAN = 0x597474ff;
    static { NAMED.put("lead_cyan", 0x597474ff); LIST.add(0x597474ff); NAMES.add("lead_cyan"); }

    /**
     * This color constant "gray_cyan" has RGBA8888 code {@code 819F9EFF}, red 0.5058824, green 0.62352943, blue 0.61960787, alpha 1.0, hue 0.49444446, saturation 0.11764705, and lightness 0.5647059.
     * It has the hex RGBA8888 value {@code 0x819f9eff}.
     * <pre>
     * <font style='background-color: #819F9E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #819F9E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #819F9E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #819F9E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #819F9E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #819F9E'>&nbsp;@&nbsp;</font><font style='background-color: #819F9E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #819F9E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #819F9E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int GRAY_CYAN = 0x819f9eff;
    static { NAMED.put("gray_cyan", 0x819f9eff); LIST.add(0x819f9eff); NAMES.add("gray_cyan"); }

    /**
     * This color constant "silver_cyan" has RGBA8888 code {@code AACBCAFF}, red 0.6666667, green 0.79607844, blue 0.7921569, alpha 1.0, hue 0.49494952, saturation 0.12941176, and lightness 0.7313726.
     * It has the hex RGBA8888 value {@code 0xaacbcaff}.
     * <pre>
     * <font style='background-color: #AACBCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AACBCA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AACBCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AACBCA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AACBCA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AACBCA'>&nbsp;@&nbsp;</font><font style='background-color: #AACBCA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AACBCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AACBCA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int SILVER_CYAN = 0xaacbcaff;
    static { NAMED.put("silver_cyan", 0xaacbcaff); LIST.add(0xaacbcaff); NAMES.add("silver_cyan"); }

    /**
     * This color constant "white_cyan" has RGBA8888 code {@code D4F7F6FF}, red 0.83137256, green 0.96862745, blue 0.9647059, alpha 1.0, hue 0.49523813, saturation 0.1372549, and lightness 0.90000004.
     * It has the hex RGBA8888 value {@code 0xd4f7f6ff}.
     * <pre>
     * <font style='background-color: #D4F7F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4F7F6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4F7F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D4F7F6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D4F7F6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D4F7F6'>&nbsp;@&nbsp;</font><font style='background-color: #D4F7F6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4F7F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4F7F6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int WHITE_CYAN = 0xd4f7f6ff;
    static { NAMED.put("white_cyan", 0xd4f7f6ff); LIST.add(0xd4f7f6ff); NAMES.add("white_cyan"); }

    /**
     * This color constant "black_blue" has RGBA8888 code {@code 263554FF}, red 0.14901961, green 0.20784314, blue 0.32941177, alpha 1.0, hue 0.6123189, saturation 0.18039216, and lightness 0.23921569.
     * It has the hex RGBA8888 value {@code 0x263554ff}.
     * <pre>
     * <font style='background-color: #263554;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #263554; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #263554;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #263554'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #263554'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #263554'>&nbsp;@&nbsp;</font><font style='background-color: #263554; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #263554;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #263554; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BLACK_BLUE = 0x263554ff;
    static { NAMED.put("black_blue", 0x263554ff); LIST.add(0x263554ff); NAMES.add("black_blue"); }

    /**
     * This color constant "lead_blue" has RGBA8888 code {@code 4A5C80FF}, red 0.2901961, green 0.36078432, blue 0.5019608, alpha 1.0, hue 0.61111116, saturation 0.21176472, and lightness 0.39607847.
     * It has the hex RGBA8888 value {@code 0x4a5c80ff}.
     * <pre>
     * <font style='background-color: #4A5C80;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A5C80; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A5C80;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4A5C80'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4A5C80'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4A5C80'>&nbsp;@&nbsp;</font><font style='background-color: #4A5C80; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A5C80;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A5C80; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int LEAD_BLUE = 0x4a5c80ff;
    static { NAMED.put("lead_blue", 0x4a5c80ff); LIST.add(0x4a5c80ff); NAMES.add("lead_blue"); }

    /**
     * This color constant "gray_blue" has RGBA8888 code {@code 6F84ACFF}, red 0.43529412, green 0.5176471, blue 0.6745098, alpha 1.0, hue 0.60928965, saturation 0.2392157, and lightness 0.55490196.
     * It has the hex RGBA8888 value {@code 0x6f84acff}.
     * <pre>
     * <font style='background-color: #6F84AC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6F84AC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6F84AC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6F84AC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6F84AC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6F84AC'>&nbsp;@&nbsp;</font><font style='background-color: #6F84AC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6F84AC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6F84AC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int GRAY_BLUE = 0x6f84acff;
    static { NAMED.put("gray_blue", 0x6f84acff); LIST.add(0x6f84acff); NAMES.add("gray_blue"); }

    /**
     * This color constant "silver_blue" has RGBA8888 code {@code 93AAD6FF}, red 0.5764706, green 0.6666667, blue 0.8392157, alpha 1.0, hue 0.6094528, saturation 0.26274508, and lightness 0.7078431.
     * It has the hex RGBA8888 value {@code 0x93aad6ff}.
     * <pre>
     * <font style='background-color: #93AAD6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #93AAD6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #93AAD6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #93AAD6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #93AAD6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #93AAD6'>&nbsp;@&nbsp;</font><font style='background-color: #93AAD6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #93AAD6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #93AAD6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int SILVER_BLUE = 0x93aad6ff;
    static { NAMED.put("silver_blue", 0x93aad6ff); LIST.add(0x93aad6ff); NAMES.add("silver_blue"); }

    /**
     * This color constant "white_blue" has RGBA8888 code {@code B8D0FFFF}, red 0.72156864, green 0.8156863, blue 1.0, alpha 1.0, hue 0.6103287, saturation 0.27843136, and lightness 0.8607843.
     * It has the hex RGBA8888 value {@code 0xb8d0ffff}.
     * <pre>
     * <font style='background-color: #B8D0FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B8D0FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B8D0FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B8D0FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B8D0FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B8D0FF'>&nbsp;@&nbsp;</font><font style='background-color: #B8D0FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B8D0FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B8D0FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int WHITE_BLUE = 0xb8d0ffff;
    static { NAMED.put("white_blue", 0xb8d0ffff); LIST.add(0xb8d0ffff); NAMES.add("white_blue"); }

    /**
     * This color constant "black_violet" has RGBA8888 code {@code 3B344FFF}, red 0.23137255, green 0.20392157, blue 0.30980393, alpha 1.0, hue 0.70987654, saturation 0.10588236, and lightness 0.25686276.
     * It has the hex RGBA8888 value {@code 0x3b344fff}.
     * <pre>
     * <font style='background-color: #3B344F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B344F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B344F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B344F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B344F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B344F'>&nbsp;@&nbsp;</font><font style='background-color: #3B344F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B344F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B344F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BLACK_VIOLET = 0x3b344fff;
    static { NAMED.put("black_violet", 0x3b344fff); LIST.add(0x3b344fff); NAMES.add("black_violet"); }

    /**
     * This color constant "lead_violet" has RGBA8888 code {@code 635A7BFF}, red 0.3882353, green 0.3529412, blue 0.48235294, alpha 1.0, hue 0.71212125, saturation 0.12941176, and lightness 0.41764706.
     * It has the hex RGBA8888 value {@code 0x635a7bff}.
     * <pre>
     * <font style='background-color: #635A7B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #635A7B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #635A7B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #635A7B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #635A7B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #635A7B'>&nbsp;@&nbsp;</font><font style='background-color: #635A7B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #635A7B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #635A7B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int LEAD_VIOLET = 0x635a7bff;
    static { NAMED.put("lead_violet", 0x635a7bff); LIST.add(0x635a7bff); NAMES.add("lead_violet"); }

    /**
     * This color constant "gray_violet" has RGBA8888 code {@code 8A81A6FF}, red 0.5411765, green 0.5058824, blue 0.6509804, alpha 1.0, hue 0.7072072, saturation 0.14509803, and lightness 0.57843137.
     * It has the hex RGBA8888 value {@code 0x8a81a6ff}.
     * <pre>
     * <font style='background-color: #8A81A6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A81A6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A81A6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8A81A6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8A81A6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8A81A6'>&nbsp;@&nbsp;</font><font style='background-color: #8A81A6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A81A6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A81A6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int GRAY_VIOLET = 0x8a81a6ff;
    static { NAMED.put("gray_violet", 0x8a81a6ff); LIST.add(0x8a81a6ff); NAMES.add("gray_violet"); }

    /**
     * This color constant "silver_violet" has RGBA8888 code {@code B1A8CFFF}, red 0.69411767, green 0.65882355, blue 0.8117647, alpha 1.0, hue 0.70512825, saturation 0.15294117, and lightness 0.73529416.
     * It has the hex RGBA8888 value {@code 0xb1a8cfff}.
     * <pre>
     * <font style='background-color: #B1A8CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1A8CF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1A8CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B1A8CF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B1A8CF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B1A8CF'>&nbsp;@&nbsp;</font><font style='background-color: #B1A8CF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1A8CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1A8CF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int SILVER_VIOLET = 0xb1a8cfff;
    static { NAMED.put("silver_violet", 0xb1a8cfff); LIST.add(0xb1a8cfff); NAMES.add("silver_violet"); }

    /**
     * This color constant "white_violet" has RGBA8888 code {@code D9CEF9FF}, red 0.8509804, green 0.80784315, blue 0.9764706, alpha 1.0, hue 0.70930237, saturation 0.16862744, and lightness 0.8921569.
     * It has the hex RGBA8888 value {@code 0xd9cef9ff}.
     * <pre>
     * <font style='background-color: #D9CEF9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9CEF9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9CEF9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D9CEF9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D9CEF9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D9CEF9'>&nbsp;@&nbsp;</font><font style='background-color: #D9CEF9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9CEF9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9CEF9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int WHITE_VIOLET = 0xd9cef9ff;
    static { NAMED.put("white_violet", 0xd9cef9ff); LIST.add(0xd9cef9ff); NAMES.add("white_violet"); }

    /**
     * This color constant "black_purple" has RGBA8888 code {@code 473151FF}, red 0.2784314, green 0.19215687, blue 0.31764707, alpha 1.0, hue 0.78125, saturation 0.1254902, and lightness 0.25490198.
     * It has the hex RGBA8888 value {@code 0x473151ff}.
     * <pre>
     * <font style='background-color: #473151;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #473151; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #473151;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #473151'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #473151'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #473151'>&nbsp;@&nbsp;</font><font style='background-color: #473151; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #473151;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #473151; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BLACK_PURPLE = 0x473151ff;
    static { NAMED.put("black_purple", 0x473151ff); LIST.add(0x473151ff); NAMES.add("black_purple"); }

    /**
     * This color constant "lead_purple" has RGBA8888 code {@code 72587EFF}, red 0.44705883, green 0.34509805, blue 0.49411765, alpha 1.0, hue 0.78070176, saturation 0.1490196, and lightness 0.41960785.
     * It has the hex RGBA8888 value {@code 0x72587eff}.
     * <pre>
     * <font style='background-color: #72587E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #72587E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #72587E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #72587E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #72587E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #72587E'>&nbsp;@&nbsp;</font><font style='background-color: #72587E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #72587E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #72587E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int LEAD_PURPLE = 0x72587eff;
    static { NAMED.put("lead_purple", 0x72587eff); LIST.add(0x72587eff); NAMES.add("lead_purple"); }

    /**
     * This color constant "gray_purple" has RGBA8888 code {@code 9B7EA8FF}, red 0.60784316, green 0.49411765, blue 0.65882355, alpha 1.0, hue 0.78174603, saturation 0.1647059, and lightness 0.5764706.
     * It has the hex RGBA8888 value {@code 0x9b7ea8ff}.
     * <pre>
     * <font style='background-color: #9B7EA8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B7EA8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B7EA8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9B7EA8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9B7EA8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9B7EA8'>&nbsp;@&nbsp;</font><font style='background-color: #9B7EA8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B7EA8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B7EA8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int GRAY_PURPLE = 0x9b7ea8ff;
    static { NAMED.put("gray_purple", 0x9b7ea8ff); LIST.add(0x9b7ea8ff); NAMES.add("gray_purple"); }

    /**
     * This color constant "silver_purple" has RGBA8888 code {@code C4A5D3FF}, red 0.76862746, green 0.64705884, blue 0.827451, alpha 1.0, hue 0.7789855, saturation 0.18039215, and lightness 0.7372549.
     * It has the hex RGBA8888 value {@code 0xc4a5d3ff}.
     * <pre>
     * <font style='background-color: #C4A5D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4A5D3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4A5D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C4A5D3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C4A5D3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C4A5D3'>&nbsp;@&nbsp;</font><font style='background-color: #C4A5D3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4A5D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4A5D3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int SILVER_PURPLE = 0xc4a5d3ff;
    static { NAMED.put("silver_purple", 0xc4a5d3ff); LIST.add(0xc4a5d3ff); NAMES.add("silver_purple"); }

    /**
     * This color constant "white_purple" has RGBA8888 code {@code EDCCFCFF}, red 0.92941177, green 0.8, blue 0.9882353, alpha 1.0, hue 0.78125, saturation 0.18823528, and lightness 0.89411765.
     * It has the hex RGBA8888 value {@code 0xedccfcff}.
     * <pre>
     * <font style='background-color: #EDCCFC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDCCFC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDCCFC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EDCCFC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EDCCFC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EDCCFC'>&nbsp;@&nbsp;</font><font style='background-color: #EDCCFC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDCCFC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDCCFC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int WHITE_PURPLE = 0xedccfcff;
    static { NAMED.put("white_purple", 0xedccfcff); LIST.add(0xedccfcff); NAMES.add("white_purple"); }

    /**
     * This color constant "black_magenta" has RGBA8888 code {@code 503143FF}, red 0.3137255, green 0.19215687, blue 0.2627451, alpha 1.0, hue 0.9032258, saturation 0.121568635, and lightness 0.2529412.
     * It has the hex RGBA8888 value {@code 0x503143ff}.
     * <pre>
     * <font style='background-color: #503143;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #503143; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #503143;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #503143'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #503143'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #503143'>&nbsp;@&nbsp;</font><font style='background-color: #503143; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #503143;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #503143; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BLACK_MAGENTA = 0x503143ff;
    static { NAMED.put("black_magenta", 0x503143ff); LIST.add(0x503143ff); NAMES.add("black_magenta"); }

    /**
     * This color constant "lead_magenta" has RGBA8888 code {@code 7C576CFF}, red 0.4862745, green 0.34117648, blue 0.42352942, alpha 1.0, hue 0.9054054, saturation 0.14509803, and lightness 0.4137255.
     * It has the hex RGBA8888 value {@code 0x7c576cff}.
     * <pre>
     * <font style='background-color: #7C576C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7C576C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7C576C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7C576C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7C576C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7C576C'>&nbsp;@&nbsp;</font><font style='background-color: #7C576C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7C576C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7C576C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int LEAD_MAGENTA = 0x7c576cff;
    static { NAMED.put("lead_magenta", 0x7c576cff); LIST.add(0x7c576cff); NAMES.add("lead_magenta"); }

    /**
     * This color constant "gray_magenta" has RGBA8888 code {@code A77D94FF}, red 0.654902, green 0.49019608, blue 0.5803922, alpha 1.0, hue 0.90873015, saturation 0.1647059, and lightness 0.57254905.
     * It has the hex RGBA8888 value {@code 0xa77d94ff}.
     * <pre>
     * <font style='background-color: #A77D94;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A77D94; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A77D94;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A77D94'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A77D94'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A77D94'>&nbsp;@&nbsp;</font><font style='background-color: #A77D94; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A77D94;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A77D94; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int GRAY_MAGENTA = 0xa77d94ff;
    static { NAMED.put("gray_magenta", 0xa77d94ff); LIST.add(0xa77d94ff); NAMES.add("gray_magenta"); }

    /**
     * This color constant "silver_magenta" has RGBA8888 code {@code D2A4BDFF}, red 0.8235294, green 0.6431373, blue 0.7411765, alpha 1.0, hue 0.9094203, saturation 0.18039215, and lightness 0.73333335.
     * It has the hex RGBA8888 value {@code 0xd2a4bdff}.
     * <pre>
     * <font style='background-color: #D2A4BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2A4BD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2A4BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D2A4BD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D2A4BD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D2A4BD'>&nbsp;@&nbsp;</font><font style='background-color: #D2A4BD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2A4BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2A4BD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int SILVER_MAGENTA = 0xd2a4bdff;
    static { NAMED.put("silver_magenta", 0xd2a4bdff); LIST.add(0xd2a4bdff); NAMES.add("silver_magenta"); }

    /**
     * This color constant "white_magenta" has RGBA8888 code {@code FBCBE5FF}, red 0.9843137, green 0.79607844, blue 0.8980392, alpha 1.0, hue 0.9097222, saturation 0.18823528, and lightness 0.8901961.
     * It has the hex RGBA8888 value {@code 0xfbcbe5ff}.
     * <pre>
     * <font style='background-color: #FBCBE5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FBCBE5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FBCBE5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FBCBE5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FBCBE5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FBCBE5'>&nbsp;@&nbsp;</font><font style='background-color: #FBCBE5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FBCBE5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FBCBE5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int WHITE_MAGENTA = 0xfbcbe5ff;
    static { NAMED.put("white_magenta", 0xfbcbe5ff); LIST.add(0xfbcbe5ff); NAMES.add("white_magenta"); }

    /**
     * This color constant "drab_red" has RGBA8888 code {@code 7E342AFF}, red 0.49411765, green 0.20392157, blue 0.16470589, alpha 1.0, hue 0.01984127, saturation 0.32941175, and lightness 0.32941177.
     * It has the hex RGBA8888 value {@code 0x7e342aff}.
     * <pre>
     * <font style='background-color: #7E342A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E342A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E342A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7E342A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7E342A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7E342A'>&nbsp;@&nbsp;</font><font style='background-color: #7E342A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E342A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E342A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DRAB_RED = 0x7e342aff;
    static { NAMED.put("drab_red", 0x7e342aff); LIST.add(0x7e342aff); NAMES.add("drab_red"); }

    /**
     * This color constant "faded_red" has RGBA8888 code {@code BA6356FF}, red 0.7294118, green 0.3882353, blue 0.3372549, alpha 1.0, hue 0.021666665, saturation 0.39215687, and lightness 0.53333336.
     * It has the hex RGBA8888 value {@code 0xba6356ff}.
     * <pre>
     * <font style='background-color: #BA6356;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA6356; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA6356;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BA6356'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BA6356'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BA6356'>&nbsp;@&nbsp;</font><font style='background-color: #BA6356; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA6356;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA6356; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int FADED_RED = 0xba6356ff;
    static { NAMED.put("faded_red", 0xba6356ff); LIST.add(0xba6356ff); NAMES.add("faded_red"); }

    /**
     * This color constant "pale_red" has RGBA8888 code {@code F39282FF}, red 0.9529412, green 0.57254905, blue 0.50980395, alpha 1.0, hue 0.023598818, saturation 0.44313723, and lightness 0.7313726.
     * It has the hex RGBA8888 value {@code 0xf39282ff}.
     * <pre>
     * <font style='background-color: #F39282;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F39282; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F39282;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F39282'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F39282'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F39282'>&nbsp;@&nbsp;</font><font style='background-color: #F39282; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F39282;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F39282; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int PALE_RED = 0xf39282ff;
    static { NAMED.put("pale_red", 0xf39282ff); LIST.add(0xf39282ff); NAMES.add("pale_red"); }

    /**
     * This color constant "drab_brown" has RGBA8888 code {@code 6A4E42FF}, red 0.41568628, green 0.30588236, blue 0.25882354, alpha 1.0, hue 0.05, saturation 0.15686274, and lightness 0.3372549.
     * It has the hex RGBA8888 value {@code 0x6a4e42ff}.
     * <pre>
     * <font style='background-color: #6A4E42;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A4E42; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A4E42;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6A4E42'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6A4E42'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6A4E42'>&nbsp;@&nbsp;</font><font style='background-color: #6A4E42; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A4E42;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A4E42; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DRAB_BROWN = 0x6a4e42ff;
    static { NAMED.put("drab_brown", 0x6a4e42ff); LIST.add(0x6a4e42ff); NAMES.add("drab_brown"); }

    /**
     * This color constant "faded_brown" has RGBA8888 code {@code A18072FF}, red 0.6313726, green 0.5019608, blue 0.44705883, alpha 1.0, hue 0.049645413, saturation 0.18431374, and lightness 0.53921574.
     * It has the hex RGBA8888 value {@code 0xa18072ff}.
     * <pre>
     * <font style='background-color: #A18072;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A18072; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A18072;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A18072'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A18072'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A18072'>&nbsp;@&nbsp;</font><font style='background-color: #A18072; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A18072;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A18072; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int FADED_BROWN = 0xa18072ff;
    static { NAMED.put("faded_brown", 0xa18072ff); LIST.add(0xa18072ff); NAMES.add("faded_brown"); }

    /**
     * This color constant "pale_brown" has RGBA8888 code {@code D9B3A3FF}, red 0.8509804, green 0.7019608, blue 0.6392157, alpha 1.0, hue 0.049382716, saturation 0.2117647, and lightness 0.74509805.
     * It has the hex RGBA8888 value {@code 0xd9b3a3ff}.
     * <pre>
     * <font style='background-color: #D9B3A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9B3A3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9B3A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D9B3A3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D9B3A3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D9B3A3'>&nbsp;@&nbsp;</font><font style='background-color: #D9B3A3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9B3A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9B3A3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int PALE_BROWN = 0xd9b3a3ff;
    static { NAMED.put("pale_brown", 0xd9b3a3ff); LIST.add(0xd9b3a3ff); NAMES.add("pale_brown"); }

    /**
     * This color constant "drab_orange" has RGBA8888 code {@code 795031FF}, red 0.4745098, green 0.3137255, blue 0.19215687, alpha 1.0, hue 0.07175927, saturation 0.28235292, and lightness 0.33333334.
     * It has the hex RGBA8888 value {@code 0x795031ff}.
     * <pre>
     * <font style='background-color: #795031;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #795031; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #795031;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #795031'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #795031'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #795031'>&nbsp;@&nbsp;</font><font style='background-color: #795031; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #795031;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #795031; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DRAB_ORANGE = 0x795031ff;
    static { NAMED.put("drab_orange", 0x795031ff); LIST.add(0x795031ff); NAMES.add("drab_orange"); }

    /**
     * This color constant "faded_orange" has RGBA8888 code {@code B68562FF}, red 0.7137255, green 0.52156866, blue 0.38431373, alpha 1.0, hue 0.06944445, saturation 0.32941177, and lightness 0.54901963.
     * It has the hex RGBA8888 value {@code 0xb68562ff}.
     * <pre>
     * <font style='background-color: #B68562;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B68562; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B68562;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B68562'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B68562'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B68562'>&nbsp;@&nbsp;</font><font style='background-color: #B68562; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B68562;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B68562; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int FADED_ORANGE = 0xb68562ff;
    static { NAMED.put("faded_orange", 0xb68562ff); LIST.add(0xb68562ff); NAMES.add("faded_orange"); }

    /**
     * This color constant "pale_orange" has RGBA8888 code {@code F1BA94FF}, red 0.94509804, green 0.7294118, blue 0.5803922, alpha 1.0, hue 0.068100356, saturation 0.36470586, and lightness 0.7627451.
     * It has the hex RGBA8888 value {@code 0xf1ba94ff}.
     * <pre>
     * <font style='background-color: #F1BA94;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F1BA94; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F1BA94;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F1BA94'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F1BA94'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F1BA94'>&nbsp;@&nbsp;</font><font style='background-color: #F1BA94; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F1BA94;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F1BA94; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int PALE_ORANGE = 0xf1ba94ff;
    static { NAMED.put("pale_orange", 0xf1ba94ff); LIST.add(0xf1ba94ff); NAMES.add("pale_orange"); }

    /**
     * This color constant "drab_saffron" has RGBA8888 code {@code 736738FF}, red 0.4509804, green 0.40392157, blue 0.21960784, alpha 1.0, hue 0.13276836, saturation 0.23137255, and lightness 0.33529413.
     * It has the hex RGBA8888 value {@code 0x736738ff}.
     * <pre>
     * <font style='background-color: #736738;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #736738; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #736738;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #736738'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #736738'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #736738'>&nbsp;@&nbsp;</font><font style='background-color: #736738; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #736738;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #736738; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DRAB_SAFFRON = 0x736738ff;
    static { NAMED.put("drab_saffron", 0x736738ff); LIST.add(0x736738ff); NAMES.add("drab_saffron"); }

    /**
     * This color constant "faded_saffron" has RGBA8888 code {@code AFA26CFF}, red 0.6862745, green 0.63529414, blue 0.42352942, alpha 1.0, hue 0.13432837, saturation 0.2627451, and lightness 0.554902.
     * It has the hex RGBA8888 value {@code 0xafa26cff}.
     * <pre>
     * <font style='background-color: #AFA26C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AFA26C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AFA26C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AFA26C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AFA26C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AFA26C'>&nbsp;@&nbsp;</font><font style='background-color: #AFA26C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AFA26C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AFA26C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int FADED_SAFFRON = 0xafa26cff;
    static { NAMED.put("faded_saffron", 0xafa26cff); LIST.add(0xafa26cff); NAMES.add("faded_saffron"); }

    /**
     * This color constant "pale_saffron" has RGBA8888 code {@code EBDCA2FF}, red 0.92156863, green 0.8627451, blue 0.63529414, alpha 1.0, hue 0.1324201, saturation 0.2862745, and lightness 0.77843136.
     * It has the hex RGBA8888 value {@code 0xebdca2ff}.
     * <pre>
     * <font style='background-color: #EBDCA2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBDCA2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBDCA2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EBDCA2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EBDCA2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EBDCA2'>&nbsp;@&nbsp;</font><font style='background-color: #EBDCA2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBDCA2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBDCA2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int PALE_SAFFRON = 0xebdca2ff;
    static { NAMED.put("pale_saffron", 0xebdca2ff); LIST.add(0xebdca2ff); NAMES.add("pale_saffron"); }

    /**
     * This color constant "drab_yellow" has RGBA8888 code {@code 727432FF}, red 0.44705883, green 0.45490196, blue 0.19607843, alpha 1.0, hue 0.17171718, saturation 0.2588235, and lightness 0.3254902.
     * It has the hex RGBA8888 value {@code 0x727432ff}.
     * <pre>
     * <font style='background-color: #727432;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #727432; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #727432;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #727432'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #727432'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #727432'>&nbsp;@&nbsp;</font><font style='background-color: #727432; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #727432;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #727432; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DRAB_YELLOW = 0x727432ff;
    static { NAMED.put("drab_yellow", 0x727432ff); LIST.add(0x727432ff); NAMES.add("drab_yellow"); }

    /**
     * This color constant "faded_yellow" has RGBA8888 code {@code AEB068FF}, red 0.68235296, green 0.6901961, blue 0.40784314, alpha 1.0, hue 0.17129631, saturation 0.28235295, and lightness 0.54901963.
     * It has the hex RGBA8888 value {@code 0xaeb068ff}.
     * <pre>
     * <font style='background-color: #AEB068;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AEB068; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AEB068;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AEB068'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AEB068'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AEB068'>&nbsp;@&nbsp;</font><font style='background-color: #AEB068; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AEB068;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AEB068; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int FADED_YELLOW = 0xaeb068ff;
    static { NAMED.put("faded_yellow", 0xaeb068ff); LIST.add(0xaeb068ff); NAMES.add("faded_yellow"); }

    /**
     * This color constant "pale_yellow" has RGBA8888 code {@code E9EC9DFF}, red 0.9137255, green 0.9254902, blue 0.6156863, alpha 1.0, hue 0.17299579, saturation 0.3098039, and lightness 0.7705882.
     * It has the hex RGBA8888 value {@code 0xe9ec9dff}.
     * <pre>
     * <font style='background-color: #E9EC9D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9EC9D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9EC9D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E9EC9D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E9EC9D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E9EC9D'>&nbsp;@&nbsp;</font><font style='background-color: #E9EC9D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9EC9D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9EC9D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int PALE_YELLOW = 0xe9ec9dff;
    static { NAMED.put("pale_yellow", 0xe9ec9dff); LIST.add(0xe9ec9dff); NAMES.add("pale_yellow"); }

    /**
     * This color constant "drab_lime" has RGBA8888 code {@code 5C753CFF}, red 0.36078432, green 0.45882353, blue 0.23529412, alpha 1.0, hue 0.23976609, saturation 0.22352941, and lightness 0.34705883.
     * It has the hex RGBA8888 value {@code 0x5c753cff}.
     * <pre>
     * <font style='background-color: #5C753C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5C753C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5C753C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5C753C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5C753C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5C753C'>&nbsp;@&nbsp;</font><font style='background-color: #5C753C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5C753C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5C753C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DRAB_LIME = 0x5c753cff;
    static { NAMED.put("drab_lime", 0x5c753cff); LIST.add(0x5c753cff); NAMES.add("drab_lime"); }

    /**
     * This color constant "faded_lime" has RGBA8888 code {@code 94B171FF}, red 0.5803922, green 0.69411767, blue 0.44313726, alpha 1.0, hue 0.2421875, saturation 0.2509804, and lightness 0.5686275.
     * It has the hex RGBA8888 value {@code 0x94b171ff}.
     * <pre>
     * <font style='background-color: #94B171;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #94B171; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #94B171;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #94B171'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #94B171'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #94B171'>&nbsp;@&nbsp;</font><font style='background-color: #94B171; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #94B171;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #94B171; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int FADED_LIME = 0x94b171ff;
    static { NAMED.put("faded_lime", 0x94b171ff); LIST.add(0x94b171ff); NAMES.add("faded_lime"); }

    /**
     * This color constant "pale_lime" has RGBA8888 code {@code CDEDA6FF}, red 0.8039216, green 0.92941177, blue 0.6509804, alpha 1.0, hue 0.24178405, saturation 0.27843136, and lightness 0.79019606.
     * It has the hex RGBA8888 value {@code 0xcdeda6ff}.
     * <pre>
     * <font style='background-color: #CDEDA6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CDEDA6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CDEDA6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CDEDA6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CDEDA6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CDEDA6'>&nbsp;@&nbsp;</font><font style='background-color: #CDEDA6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CDEDA6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CDEDA6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int PALE_LIME = 0xcdeda6ff;
    static { NAMED.put("pale_lime", 0xcdeda6ff); LIST.add(0xcdeda6ff); NAMES.add("pale_lime"); }

    /**
     * This color constant "drab_green" has RGBA8888 code {@code 3F7934FF}, red 0.24705882, green 0.4745098, blue 0.20392157, alpha 1.0, hue 0.3067633, saturation 0.27058822, and lightness 0.3392157.
     * It has the hex RGBA8888 value {@code 0x3f7934ff}.
     * <pre>
     * <font style='background-color: #3F7934;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F7934; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F7934;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3F7934'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3F7934'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3F7934'>&nbsp;@&nbsp;</font><font style='background-color: #3F7934; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F7934;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F7934; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DRAB_GREEN = 0x3f7934ff;
    static { NAMED.put("drab_green", 0x3f7934ff); LIST.add(0x3f7934ff); NAMES.add("drab_green"); }

    /**
     * This color constant "faded_green" has RGBA8888 code {@code 73B466FF}, red 0.4509804, green 0.7058824, blue 0.4, alpha 1.0, hue 0.30555558, saturation 0.30588236, and lightness 0.55294114.
     * It has the hex RGBA8888 value {@code 0x73b466ff}.
     * <pre>
     * <font style='background-color: #73B466;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73B466; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73B466;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #73B466'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #73B466'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #73B466'>&nbsp;@&nbsp;</font><font style='background-color: #73B466; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73B466;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73B466; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int FADED_GREEN = 0x73b466ff;
    static { NAMED.put("faded_green", 0x73b466ff); LIST.add(0x73b466ff); NAMES.add("faded_green"); }

    /**
     * This color constant "pale_green" has RGBA8888 code {@code A8EE99FF}, red 0.65882355, green 0.93333334, blue 0.6, alpha 1.0, hue 0.30392158, saturation 0.3333333, and lightness 0.7666667.
     * It has the hex RGBA8888 value {@code 0xa8ee99ff}.
     * <pre>
     * <font style='background-color: #A8EE99;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A8EE99; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A8EE99;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A8EE99'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A8EE99'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A8EE99'>&nbsp;@&nbsp;</font><font style='background-color: #A8EE99; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A8EE99;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A8EE99; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int PALE_GREEN = 0xa8ee99ff;
    static { NAMED.put("pale_green", 0xa8ee99ff); LIST.add(0xa8ee99ff); NAMES.add("pale_green"); }

    /**
     * This color constant "drab_cyan" has RGBA8888 code {@code 3A7675FF}, red 0.22745098, green 0.4627451, blue 0.45882353, alpha 1.0, hue 0.49722224, saturation 0.23529412, and lightness 0.34509805.
     * It has the hex RGBA8888 value {@code 0x3a7675ff}.
     * <pre>
     * <font style='background-color: #3A7675;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3A7675; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3A7675;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3A7675'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3A7675'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3A7675'>&nbsp;@&nbsp;</font><font style='background-color: #3A7675; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3A7675;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3A7675; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DRAB_CYAN = 0x3a7675ff;
    static { NAMED.put("drab_cyan", 0x3a7675ff); LIST.add(0x3a7675ff); NAMES.add("drab_cyan"); }

    /**
     * This color constant "faded_cyan" has RGBA8888 code {@code 70B2B0FF}, red 0.4392157, green 0.69803923, blue 0.6901961, alpha 1.0, hue 0.49494952, saturation 0.25882354, and lightness 0.5686275.
     * It has the hex RGBA8888 value {@code 0x70b2b0ff}.
     * <pre>
     * <font style='background-color: #70B2B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #70B2B0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #70B2B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #70B2B0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #70B2B0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #70B2B0'>&nbsp;@&nbsp;</font><font style='background-color: #70B2B0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #70B2B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #70B2B0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int FADED_CYAN = 0x70b2b0ff;
    static { NAMED.put("faded_cyan", 0x70b2b0ff); LIST.add(0x70b2b0ff); NAMES.add("faded_cyan"); }

    /**
     * This color constant "pale_cyan" has RGBA8888 code {@code A6EDEBFF}, red 0.6509804, green 0.92941177, blue 0.92156863, alpha 1.0, hue 0.49530518, saturation 0.27843136, and lightness 0.79019606.
     * It has the hex RGBA8888 value {@code 0xa6edebff}.
     * <pre>
     * <font style='background-color: #A6EDEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A6EDEB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A6EDEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A6EDEB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A6EDEB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A6EDEB'>&nbsp;@&nbsp;</font><font style='background-color: #A6EDEB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A6EDEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A6EDEB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int PALE_CYAN = 0xa6edebff;
    static { NAMED.put("pale_cyan", 0xa6edebff); LIST.add(0xa6edebff); NAMES.add("pale_cyan"); }

    /**
     * This color constant "drab_blue" has RGBA8888 code {@code 1C3984FF}, red 0.10980392, green 0.22352941, blue 0.5176471, alpha 1.0, hue 0.62019235, saturation 0.40784317, and lightness 0.31372553.
     * It has the hex RGBA8888 value {@code 0x1c3984ff}.
     * <pre>
     * <font style='background-color: #1C3984;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1C3984; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1C3984;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1C3984'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1C3984'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1C3984'>&nbsp;@&nbsp;</font><font style='background-color: #1C3984; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1C3984;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1C3984; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DRAB_BLUE = 0x1c3984ff;
    static { NAMED.put("drab_blue", 0x1c3984ff); LIST.add(0x1c3984ff); NAMES.add("drab_blue"); }

    /**
     * This color constant "faded_blue" has RGBA8888 code {@code 4369BEFF}, red 0.2627451, green 0.4117647, blue 0.74509805, alpha 1.0, hue 0.6151762, saturation 0.48235294, and lightness 0.50392157.
     * It has the hex RGBA8888 value {@code 0x4369beff}.
     * <pre>
     * <font style='background-color: #4369BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4369BE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4369BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4369BE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4369BE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4369BE'>&nbsp;@&nbsp;</font><font style='background-color: #4369BE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4369BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4369BE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int FADED_BLUE = 0x4369beff;
    static { NAMED.put("faded_blue", 0x4369beff); LIST.add(0x4369beff); NAMES.add("faded_blue"); }

    /**
     * This color constant "pale_blue" has RGBA8888 code {@code 6C97F5FF}, red 0.42352942, green 0.5921569, blue 0.9607843, alpha 1.0, hue 0.61435527, saturation 0.5372549, and lightness 0.69215685.
     * It has the hex RGBA8888 value {@code 0x6c97f5ff}.
     * <pre>
     * <font style='background-color: #6C97F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C97F5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C97F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6C97F5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6C97F5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6C97F5'>&nbsp;@&nbsp;</font><font style='background-color: #6C97F5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C97F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C97F5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int PALE_BLUE = 0x6c97f5ff;
    static { NAMED.put("pale_blue", 0x6c97f5ff); LIST.add(0x6c97f5ff); NAMES.add("pale_blue"); }

    /**
     * This color constant "drab_violet" has RGBA8888 code {@code 4E367AFF}, red 0.30588236, green 0.21176471, blue 0.47843137, alpha 1.0, hue 0.7254902, saturation 0.26666665, and lightness 0.34509802.
     * It has the hex RGBA8888 value {@code 0x4e367aff}.
     * <pre>
     * <font style='background-color: #4E367A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E367A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E367A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4E367A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4E367A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4E367A'>&nbsp;@&nbsp;</font><font style='background-color: #4E367A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E367A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E367A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DRAB_VIOLET = 0x4e367aff;
    static { NAMED.put("drab_violet", 0x4e367aff); LIST.add(0x4e367aff); NAMES.add("drab_violet"); }

    /**
     * This color constant "faded_violet" has RGBA8888 code {@code 8067B6FF}, red 0.5019608, green 0.40392157, blue 0.7137255, alpha 1.0, hue 0.71940935, saturation 0.30980393, and lightness 0.5588235.
     * It has the hex RGBA8888 value {@code 0x8067b6ff}.
     * <pre>
     * <font style='background-color: #8067B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8067B6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8067B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8067B6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8067B6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8067B6'>&nbsp;@&nbsp;</font><font style='background-color: #8067B6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8067B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8067B6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int FADED_VIOLET = 0x8067b6ff;
    static { NAMED.put("faded_violet", 0x8067b6ff); LIST.add(0x8067b6ff); NAMES.add("faded_violet"); }

    /**
     * This color constant "pale_violet" has RGBA8888 code {@code B196EEFF}, red 0.69411767, green 0.5882353, blue 0.93333334, alpha 1.0, hue 0.71780306, saturation 0.34509802, and lightness 0.7607843.
     * It has the hex RGBA8888 value {@code 0xb196eeff}.
     * <pre>
     * <font style='background-color: #B196EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B196EE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B196EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B196EE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B196EE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B196EE'>&nbsp;@&nbsp;</font><font style='background-color: #B196EE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B196EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B196EE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int PALE_VIOLET = 0xb196eeff;
    static { NAMED.put("pale_violet", 0xb196eeff); LIST.add(0xb196eeff); NAMES.add("pale_violet"); }

    /**
     * This color constant "drab_purple" has RGBA8888 code {@code 65377FFF}, red 0.39607844, green 0.21568628, blue 0.49803922, alpha 1.0, hue 0.7731482, saturation 0.28235292, and lightness 0.35686275.
     * It has the hex RGBA8888 value {@code 0x65377fff}.
     * <pre>
     * <font style='background-color: #65377F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #65377F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #65377F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #65377F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #65377F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #65377F'>&nbsp;@&nbsp;</font><font style='background-color: #65377F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #65377F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #65377F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DRAB_PURPLE = 0x65377fff;
    static { NAMED.put("drab_purple", 0x65377fff); LIST.add(0x65377fff); NAMES.add("drab_purple"); }

    /**
     * This color constant "faded_purple" has RGBA8888 code {@code 9C68BAFF}, red 0.6117647, green 0.40784314, blue 0.7294118, alpha 1.0, hue 0.77235776, saturation 0.32156864, and lightness 0.5686274.
     * It has the hex RGBA8888 value {@code 0x9c68baff}.
     * <pre>
     * <font style='background-color: #9C68BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C68BA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C68BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9C68BA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9C68BA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9C68BA'>&nbsp;@&nbsp;</font><font style='background-color: #9C68BA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C68BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C68BA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int FADED_PURPLE = 0x9c68baff;
    static { NAMED.put("faded_purple", 0x9c68baff); LIST.add(0x9c68baff); NAMES.add("faded_purple"); }

    /**
     * This color constant "pale_purple" has RGBA8888 code {@code D299F5FF}, red 0.8235294, green 0.6, blue 0.9607843, alpha 1.0, hue 0.76992756, saturation 0.3607843, and lightness 0.78039217.
     * It has the hex RGBA8888 value {@code 0xd299f5ff}.
     * <pre>
     * <font style='background-color: #D299F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D299F5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D299F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D299F5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D299F5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D299F5'>&nbsp;@&nbsp;</font><font style='background-color: #D299F5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D299F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D299F5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int PALE_PURPLE = 0xd299f5ff;
    static { NAMED.put("pale_purple", 0xd299f5ff); LIST.add(0xd299f5ff); NAMES.add("pale_purple"); }

    /**
     * This color constant "drab_magenta" has RGBA8888 code {@code 7B3660FF}, red 0.48235294, green 0.21176471, blue 0.3764706, alpha 1.0, hue 0.89855075, saturation 0.27058822, and lightness 0.34705883.
     * It has the hex RGBA8888 value {@code 0x7b3660ff}.
     * <pre>
     * <font style='background-color: #7B3660;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B3660; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B3660;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7B3660'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7B3660'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7B3660'>&nbsp;@&nbsp;</font><font style='background-color: #7B3660; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B3660;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B3660; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DRAB_MAGENTA = 0x7b3660ff;
    static { NAMED.put("drab_magenta", 0x7b3660ff); LIST.add(0x7b3660ff); NAMES.add("drab_magenta"); }

    /**
     * This color constant "faded_magenta" has RGBA8888 code {@code B76696FF}, red 0.7176471, green 0.4, blue 0.5882353, alpha 1.0, hue 0.90123457, saturation 0.31764707, and lightness 0.5588236.
     * It has the hex RGBA8888 value {@code 0xb76696ff}.
     * <pre>
     * <font style='background-color: #B76696;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B76696; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B76696;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B76696'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B76696'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B76696'>&nbsp;@&nbsp;</font><font style='background-color: #B76696; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B76696;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B76696; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int FADED_MAGENTA = 0xb76696ff;
    static { NAMED.put("faded_magenta", 0xb76696ff); LIST.add(0xb76696ff); NAMES.add("faded_magenta"); }

    /**
     * This color constant "pale_magenta" has RGBA8888 code {@code F096CAFF}, red 0.9411765, green 0.5882353, blue 0.7921569, alpha 1.0, hue 0.9037037, saturation 0.35294116, and lightness 0.7647059.
     * It has the hex RGBA8888 value {@code 0xf096caff}.
     * <pre>
     * <font style='background-color: #F096CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F096CA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F096CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F096CA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F096CA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F096CA'>&nbsp;@&nbsp;</font><font style='background-color: #F096CA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F096CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F096CA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int PALE_MAGENTA = 0xf096caff;
    static { NAMED.put("pale_magenta", 0xf096caff); LIST.add(0xf096caff); NAMES.add("pale_magenta"); }

    /**
     * This color constant "deep_pure_red" has RGBA8888 code {@code B1271FFF}, red 0.69411767, green 0.15294118, blue 0.12156863, alpha 1.0, hue 0.009132422, saturation 0.57254905, and lightness 0.40784314.
     * It has the hex RGBA8888 value {@code 0xb1271fff}.
     * <pre>
     * <font style='background-color: #B1271F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1271F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1271F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B1271F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B1271F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B1271F'>&nbsp;@&nbsp;</font><font style='background-color: #B1271F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1271F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1271F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_PURE_RED = 0xb1271fff;
    static { NAMED.put("deep_pure_red", 0xb1271fff); LIST.add(0xb1271fff); NAMES.add("deep_pure_red"); }

    /**
     * This color constant "true_pure_red" has RGBA8888 code {@code D64539FF}, red 0.8392157, green 0.27058825, blue 0.22352941, alpha 1.0, hue 0.0127388565, saturation 0.6156863, and lightness 0.5313725.
     * It has the hex RGBA8888 value {@code 0xd64539ff}.
     * <pre>
     * <font style='background-color: #D64539;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D64539; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D64539;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D64539'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D64539'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D64539'>&nbsp;@&nbsp;</font><font style='background-color: #D64539; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D64539;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D64539; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_PURE_RED = 0xd64539ff;
    static { NAMED.put("true_pure_red", 0xd64539ff); LIST.add(0xd64539ff); NAMES.add("true_pure_red"); }

    /**
     * This color constant "bright_pure_red" has RGBA8888 code {@code F96051FF}, red 0.9764706, green 0.3764706, blue 0.31764707, alpha 1.0, hue 0.014880952, saturation 0.6588235, and lightness 0.6470588.
     * It has the hex RGBA8888 value {@code 0xf96051ff}.
     * <pre>
     * <font style='background-color: #F96051;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F96051; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F96051;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F96051'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F96051'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F96051'>&nbsp;@&nbsp;</font><font style='background-color: #F96051; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F96051;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F96051; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_PURE_RED = 0xf96051ff;
    static { NAMED.put("bright_pure_red", 0xf96051ff); LIST.add(0xf96051ff); NAMES.add("bright_pure_red"); }

    /**
     * This color constant "deep_brown_red" has RGBA8888 code {@code A54A36FF}, red 0.64705884, green 0.2901961, blue 0.21176471, alpha 1.0, hue 0.03003003, saturation 0.43529415, and lightness 0.42941177.
     * It has the hex RGBA8888 value {@code 0xa54a36ff}.
     * <pre>
     * <font style='background-color: #A54A36;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A54A36; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A54A36;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A54A36'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A54A36'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A54A36'>&nbsp;@&nbsp;</font><font style='background-color: #A54A36; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A54A36;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A54A36; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_BROWN_RED = 0xa54a36ff;
    static { NAMED.put("deep_brown_red", 0xa54a36ff); LIST.add(0xa54a36ff); NAMES.add("deep_brown_red"); }

    /**
     * This color constant "true_brown_red" has RGBA8888 code {@code CC6952FF}, red 0.8, green 0.4117647, blue 0.32156864, alpha 1.0, hue 0.031420764, saturation 0.47843137, and lightness 0.56078434.
     * It has the hex RGBA8888 value {@code 0xcc6952ff}.
     * <pre>
     * <font style='background-color: #CC6952;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CC6952; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CC6952;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CC6952'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CC6952'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CC6952'>&nbsp;@&nbsp;</font><font style='background-color: #CC6952; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CC6952;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CC6952; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_BROWN_RED = 0xcc6952ff;
    static { NAMED.put("true_brown_red", 0xcc6952ff); LIST.add(0xcc6952ff); NAMES.add("true_brown_red"); }

    /**
     * This color constant "bright_brown_red" has RGBA8888 code {@code F38970FF}, red 0.9529412, green 0.5372549, blue 0.4392157, alpha 1.0, hue 0.03180662, saturation 0.5137255, and lightness 0.6960784.
     * It has the hex RGBA8888 value {@code 0xf38970ff}.
     * <pre>
     * <font style='background-color: #F38970;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F38970; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F38970;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F38970'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F38970'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F38970'>&nbsp;@&nbsp;</font><font style='background-color: #F38970; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F38970;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F38970; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_BROWN_RED = 0xf38970ff;
    static { NAMED.put("bright_brown_red", 0xf38970ff); LIST.add(0xf38970ff); NAMES.add("bright_brown_red"); }

    /**
     * This color constant "deep_red_brown" has RGBA8888 code {@code 8E4F3DFF}, red 0.5568628, green 0.30980393, blue 0.23921569, alpha 1.0, hue 0.037037037, saturation 0.3176471, and lightness 0.3980392.
     * It has the hex RGBA8888 value {@code 0x8e4f3dff}.
     * <pre>
     * <font style='background-color: #8E4F3D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8E4F3D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8E4F3D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8E4F3D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8E4F3D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8E4F3D'>&nbsp;@&nbsp;</font><font style='background-color: #8E4F3D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8E4F3D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8E4F3D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_RED_BROWN = 0x8e4f3dff;
    static { NAMED.put("deep_red_brown", 0x8e4f3dff); LIST.add(0x8e4f3dff); NAMES.add("deep_red_brown"); }

    /**
     * This color constant "true_red_brown" has RGBA8888 code {@code AB6753FF}, red 0.67058825, green 0.40392157, blue 0.3254902, alpha 1.0, hue 0.037878785, saturation 0.34509805, and lightness 0.49803925.
     * It has the hex RGBA8888 value {@code 0xab6753ff}.
     * <pre>
     * <font style='background-color: #AB6753;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB6753; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB6753;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB6753'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB6753'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB6753'>&nbsp;@&nbsp;</font><font style='background-color: #AB6753; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB6753;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB6753; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_RED_BROWN = 0xab6753ff;
    static { NAMED.put("true_red_brown", 0xab6753ff); LIST.add(0xab6753ff); NAMES.add("true_red_brown"); }

    /**
     * This color constant "bright_red_brown" has RGBA8888 code {@code C57D68FF}, red 0.77254903, green 0.49019608, blue 0.40784314, alpha 1.0, hue 0.037634406, saturation 0.3647059, and lightness 0.5901961.
     * It has the hex RGBA8888 value {@code 0xc57d68ff}.
     * <pre>
     * <font style='background-color: #C57D68;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C57D68; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C57D68;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C57D68'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C57D68'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C57D68'>&nbsp;@&nbsp;</font><font style='background-color: #C57D68; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C57D68;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C57D68; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_RED_BROWN = 0xc57d68ff;
    static { NAMED.put("bright_red_brown", 0xc57d68ff); LIST.add(0xc57d68ff); NAMES.add("bright_red_brown"); }

    /**
     * This color constant "deep_pure_brown" has RGBA8888 code {@code 9A5C42FF}, red 0.6039216, green 0.36078432, blue 0.25882354, alpha 1.0, hue 0.04924242, saturation 0.34509805, and lightness 0.43137258.
     * It has the hex RGBA8888 value {@code 0x9a5c42ff}.
     * <pre>
     * <font style='background-color: #9A5C42;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A5C42; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A5C42;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9A5C42'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9A5C42'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9A5C42'>&nbsp;@&nbsp;</font><font style='background-color: #9A5C42; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A5C42;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A5C42; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_PURE_BROWN = 0x9a5c42ff;
    static { NAMED.put("deep_pure_brown", 0x9a5c42ff); LIST.add(0x9a5c42ff); NAMES.add("deep_pure_brown"); }

    /**
     * This color constant "true_pure_brown" has RGBA8888 code {@code BB785CFF}, red 0.73333335, green 0.47058824, blue 0.36078432, alpha 1.0, hue 0.049122803, saturation 0.37254903, and lightness 0.5470588.
     * It has the hex RGBA8888 value {@code 0xbb785cff}.
     * <pre>
     * <font style='background-color: #BB785C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB785C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB785C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BB785C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BB785C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BB785C'>&nbsp;@&nbsp;</font><font style='background-color: #BB785C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB785C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB785C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_PURE_BROWN = 0xbb785cff;
    static { NAMED.put("true_pure_brown", 0xbb785cff); LIST.add(0xbb785cff); NAMES.add("true_pure_brown"); }

    /**
     * This color constant "bright_pure_brown" has RGBA8888 code {@code DC9576FF}, red 0.8627451, green 0.58431375, blue 0.4627451, alpha 1.0, hue 0.050653603, saturation 0.4, and lightness 0.6627451.
     * It has the hex RGBA8888 value {@code 0xdc9576ff}.
     * <pre>
     * <font style='background-color: #DC9576;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DC9576; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DC9576;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DC9576'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DC9576'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DC9576'>&nbsp;@&nbsp;</font><font style='background-color: #DC9576; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DC9576;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DC9576; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_PURE_BROWN = 0xdc9576ff;
    static { NAMED.put("bright_pure_brown", 0xdc9576ff); LIST.add(0xdc9576ff); NAMES.add("bright_pure_brown"); }

    /**
     * This color constant "deep_orange_brown" has RGBA8888 code {@code 9F6649FF}, red 0.62352943, green 0.4, blue 0.28627452, alpha 1.0, hue 0.056201544, saturation 0.3372549, and lightness 0.454902.
     * It has the hex RGBA8888 value {@code 0x9f6649ff}.
     * <pre>
     * <font style='background-color: #9F6649;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9F6649; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9F6649;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9F6649'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9F6649'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9F6649'>&nbsp;@&nbsp;</font><font style='background-color: #9F6649; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9F6649;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9F6649; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_ORANGE_BROWN = 0x9f6649ff;
    static { NAMED.put("deep_orange_brown", 0x9f6649ff); LIST.add(0x9f6649ff); NAMES.add("deep_orange_brown"); }

    /**
     * This color constant "true_orange_brown" has RGBA8888 code {@code C78869FF}, red 0.78039217, green 0.53333336, blue 0.4117647, alpha 1.0, hue 0.054964546, saturation 0.36862746, and lightness 0.59607846.
     * It has the hex RGBA8888 value {@code 0xc78869ff}.
     * <pre>
     * <font style='background-color: #C78869;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C78869; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C78869;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C78869'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C78869'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C78869'>&nbsp;@&nbsp;</font><font style='background-color: #C78869; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C78869;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C78869; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_ORANGE_BROWN = 0xc78869ff;
    static { NAMED.put("true_orange_brown", 0xc78869ff); LIST.add(0xc78869ff); NAMES.add("true_orange_brown"); }

    /**
     * This color constant "bright_orange_brown" has RGBA8888 code {@code EFAC8AFF}, red 0.9372549, green 0.6745098, blue 0.5411765, alpha 1.0, hue 0.056105606, saturation 0.3960784, and lightness 0.73921573.
     * It has the hex RGBA8888 value {@code 0xefac8aff}.
     * <pre>
     * <font style='background-color: #EFAC8A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFAC8A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFAC8A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EFAC8A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EFAC8A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EFAC8A'>&nbsp;@&nbsp;</font><font style='background-color: #EFAC8A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFAC8A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFAC8A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_ORANGE_BROWN = 0xefac8aff;
    static { NAMED.put("bright_orange_brown", 0xefac8aff); LIST.add(0xefac8aff); NAMES.add("bright_orange_brown"); }

    /**
     * This color constant "deep_brown_orange" has RGBA8888 code {@code 9A5E37FF}, red 0.6039216, green 0.36862746, blue 0.21568628, alpha 1.0, hue 0.065656565, saturation 0.38823533, and lightness 0.40980393.
     * It has the hex RGBA8888 value {@code 0x9a5e37ff}.
     * <pre>
     * <font style='background-color: #9A5E37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A5E37; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A5E37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9A5E37'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9A5E37'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9A5E37'>&nbsp;@&nbsp;</font><font style='background-color: #9A5E37; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A5E37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A5E37; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_BROWN_ORANGE = 0x9a5e37ff;
    static { NAMED.put("deep_brown_orange", 0x9a5e37ff); LIST.add(0x9a5e37ff); NAMES.add("deep_brown_orange"); }

    /**
     * This color constant "true_brown_orange" has RGBA8888 code {@code B6764CFF}, red 0.7137255, green 0.4627451, blue 0.29803923, alpha 1.0, hue 0.06603773, saturation 0.41568628, and lightness 0.5058824.
     * It has the hex RGBA8888 value {@code 0xb6764cff}.
     * <pre>
     * <font style='background-color: #B6764C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6764C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6764C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B6764C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B6764C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B6764C'>&nbsp;@&nbsp;</font><font style='background-color: #B6764C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6764C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6764C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_BROWN_ORANGE = 0xb6764cff;
    static { NAMED.put("true_brown_orange", 0xb6764cff); LIST.add(0xb6764cff); NAMES.add("true_brown_orange"); }

    /**
     * This color constant "bright_brown_orange" has RGBA8888 code {@code D28E63FF}, red 0.8235294, green 0.5568628, blue 0.3882353, alpha 1.0, hue 0.06456457, saturation 0.43529412, and lightness 0.6058824.
     * It has the hex RGBA8888 value {@code 0xd28e63ff}.
     * <pre>
     * <font style='background-color: #D28E63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D28E63; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D28E63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D28E63'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D28E63'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D28E63'>&nbsp;@&nbsp;</font><font style='background-color: #D28E63; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D28E63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D28E63; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_BROWN_ORANGE = 0xd28e63ff;
    static { NAMED.put("bright_brown_orange", 0xd28e63ff); LIST.add(0xd28e63ff); NAMES.add("bright_brown_orange"); }

    /**
     * This color constant "deep_pure_orange" has RGBA8888 code {@code AC6228FF}, red 0.6745098, green 0.38431373, blue 0.15686275, alpha 1.0, hue 0.073232315, saturation 0.5176471, and lightness 0.41568628.
     * It has the hex RGBA8888 value {@code 0xac6228ff}.
     * <pre>
     * <font style='background-color: #AC6228;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC6228; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC6228;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AC6228'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AC6228'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AC6228'>&nbsp;@&nbsp;</font><font style='background-color: #AC6228; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC6228;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC6228; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_PURE_ORANGE = 0xac6228ff;
    static { NAMED.put("deep_pure_orange", 0xac6228ff); LIST.add(0xac6228ff); NAMES.add("deep_pure_orange"); }

    /**
     * This color constant "true_pure_orange" has RGBA8888 code {@code D28246FF}, red 0.8235294, green 0.50980395, blue 0.27450982, alpha 1.0, hue 0.07142858, saturation 0.5490196, and lightness 0.54901963.
     * It has the hex RGBA8888 value {@code 0xd28246ff}.
     * <pre>
     * <font style='background-color: #D28246;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D28246; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D28246;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D28246'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D28246'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D28246'>&nbsp;@&nbsp;</font><font style='background-color: #D28246; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D28246;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D28246; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_PURE_ORANGE = 0xd28246ff;
    static { NAMED.put("true_pure_orange", 0xd28246ff); LIST.add(0xd28246ff); NAMES.add("true_pure_orange"); }

    /**
     * This color constant "bright_pure_orange" has RGBA8888 code {@code F6A162FF}, red 0.9647059, green 0.6313726, blue 0.38431373, alpha 1.0, hue 0.070945956, saturation 0.5803921, and lightness 0.6745098.
     * It has the hex RGBA8888 value {@code 0xf6a162ff}.
     * <pre>
     * <font style='background-color: #F6A162;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6A162; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6A162;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F6A162'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F6A162'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F6A162'>&nbsp;@&nbsp;</font><font style='background-color: #F6A162; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6A162;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6A162; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_PURE_ORANGE = 0xf6a162ff;
    static { NAMED.put("bright_pure_orange", 0xf6a162ff); LIST.add(0xf6a162ff); NAMES.add("bright_pure_orange"); }

    /**
     * This color constant "deep_saffron_orange" has RGBA8888 code {@code AF7A37FF}, red 0.6862745, green 0.47843137, blue 0.21568628, alpha 1.0, hue 0.09305554, saturation 0.47058827, and lightness 0.45098042.
     * It has the hex RGBA8888 value {@code 0xaf7a37ff}.
     * <pre>
     * <font style='background-color: #AF7A37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF7A37; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF7A37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AF7A37'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AF7A37'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AF7A37'>&nbsp;@&nbsp;</font><font style='background-color: #AF7A37; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF7A37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF7A37; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_SAFFRON_ORANGE = 0xaf7a37ff;
    static { NAMED.put("deep_saffron_orange", 0xaf7a37ff); LIST.add(0xaf7a37ff); NAMES.add("deep_saffron_orange"); }

    /**
     * This color constant "true_saffron_orange" has RGBA8888 code {@code D9A059FF}, red 0.8509804, green 0.627451, blue 0.34901962, alpha 1.0, hue 0.09244793, saturation 0.50196075, and lightness 0.6000001.
     * It has the hex RGBA8888 value {@code 0xd9a059ff}.
     * <pre>
     * <font style='background-color: #D9A059;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9A059; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9A059;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D9A059'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D9A059'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D9A059'>&nbsp;@&nbsp;</font><font style='background-color: #D9A059; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9A059;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9A059; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_SAFFRON_ORANGE = 0xd9a059ff;
    static { NAMED.put("true_saffron_orange", 0xd9a059ff); LIST.add(0xd9a059ff); NAMES.add("true_saffron_orange"); }

    /**
     * This color constant "bright_saffron_orange" has RGBA8888 code {@code FFC77CFF}, red 1.0, green 0.78039217, blue 0.4862745, alpha 1.0, hue 0.09541985, saturation 0.5137255, and lightness 0.74313724.
     * It has the hex RGBA8888 value {@code 0xffc77cff}.
     * <pre>
     * <font style='background-color: #FFC77C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFC77C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFC77C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFC77C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFC77C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFC77C'>&nbsp;@&nbsp;</font><font style='background-color: #FFC77C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFC77C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFC77C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_SAFFRON_ORANGE = 0xffc77cff;
    static { NAMED.put("bright_saffron_orange", 0xffc77cff); LIST.add(0xffc77cff); NAMES.add("bright_saffron_orange"); }

    /**
     * This color constant "deep_orange_saffron" has RGBA8888 code {@code A57B2CFF}, red 0.64705884, green 0.48235294, blue 0.17254902, alpha 1.0, hue 0.10881542, saturation 0.47450984, and lightness 0.40980393.
     * It has the hex RGBA8888 value {@code 0xa57b2cff}.
     * <pre>
     * <font style='background-color: #A57B2C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A57B2C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A57B2C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A57B2C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A57B2C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A57B2C'>&nbsp;@&nbsp;</font><font style='background-color: #A57B2C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A57B2C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A57B2C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_ORANGE_SAFFRON = 0xa57b2cff;
    static { NAMED.put("deep_orange_saffron", 0xa57b2cff); LIST.add(0xa57b2cff); NAMES.add("deep_orange_saffron"); }

    /**
     * This color constant "true_orange_saffron" has RGBA8888 code {@code C39645FF}, red 0.7647059, green 0.5882353, blue 0.27058825, alpha 1.0, hue 0.10714286, saturation 0.49411765, and lightness 0.5176471.
     * It has the hex RGBA8888 value {@code 0xc39645ff}.
     * <pre>
     * <font style='background-color: #C39645;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C39645; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C39645;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C39645'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C39645'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C39645'>&nbsp;@&nbsp;</font><font style='background-color: #C39645; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C39645;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C39645; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_ORANGE_SAFFRON = 0xc39645ff;
    static { NAMED.put("true_orange_saffron", 0xc39645ff); LIST.add(0xc39645ff); NAMES.add("true_orange_saffron"); }

    /**
     * This color constant "bright_orange_saffron" has RGBA8888 code {@code E1B25FFF}, red 0.88235295, green 0.69803923, blue 0.37254903, alpha 1.0, hue 0.106410265, saturation 0.5098039, and lightness 0.627451.
     * It has the hex RGBA8888 value {@code 0xe1b25fff}.
     * <pre>
     * <font style='background-color: #E1B25F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1B25F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1B25F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E1B25F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E1B25F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E1B25F'>&nbsp;@&nbsp;</font><font style='background-color: #E1B25F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1B25F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1B25F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_ORANGE_SAFFRON = 0xe1b25fff;
    static { NAMED.put("bright_orange_saffron", 0xe1b25fff); LIST.add(0xe1b25fff); NAMES.add("bright_orange_saffron"); }

    /**
     * This color constant "deep_pure_saffron" has RGBA8888 code {@code AD952CFF}, red 0.6784314, green 0.58431375, blue 0.17254902, alpha 1.0, hue 0.13565892, saturation 0.5058824, and lightness 0.42549017.
     * It has the hex RGBA8888 value {@code 0xad952cff}.
     * <pre>
     * <font style='background-color: #AD952C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD952C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD952C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AD952C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AD952C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AD952C'>&nbsp;@&nbsp;</font><font style='background-color: #AD952C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD952C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD952C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_PURE_SAFFRON = 0xad952cff;
    static { NAMED.put("deep_pure_saffron", 0xad952cff); LIST.add(0xad952cff); NAMES.add("deep_pure_saffron"); }

    /**
     * This color constant "true_pure_saffron" has RGBA8888 code {@code CFB74CFF}, red 0.8117647, green 0.7176471, blue 0.29803923, alpha 1.0, hue 0.13613231, saturation 0.5137255, and lightness 0.55490196.
     * It has the hex RGBA8888 value {@code 0xcfb74cff}.
     * <pre>
     * <font style='background-color: #CFB74C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CFB74C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CFB74C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CFB74C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CFB74C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CFB74C'>&nbsp;@&nbsp;</font><font style='background-color: #CFB74C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CFB74C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CFB74C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_PURE_SAFFRON = 0xcfb74cff;
    static { NAMED.put("true_pure_saffron", 0xcfb74cff); LIST.add(0xcfb74cff); NAMES.add("true_pure_saffron"); }

    /**
     * This color constant "bright_pure_saffron" has RGBA8888 code {@code F3D96BFF}, red 0.9529412, green 0.8509804, blue 0.41960785, alpha 1.0, hue 0.13480394, saturation 0.5333333, and lightness 0.6862745.
     * It has the hex RGBA8888 value {@code 0xf3d96bff}.
     * <pre>
     * <font style='background-color: #F3D96B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3D96B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3D96B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F3D96B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F3D96B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F3D96B'>&nbsp;@&nbsp;</font><font style='background-color: #F3D96B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3D96B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3D96B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_PURE_SAFFRON = 0xf3d96bff;
    static { NAMED.put("bright_pure_saffron", 0xf3d96bff); LIST.add(0xf3d96bff); NAMES.add("bright_pure_saffron"); }

    /**
     * This color constant "deep_yellow_saffron" has RGBA8888 code {@code B7A53AFF}, red 0.7176471, green 0.64705884, blue 0.22745098, alpha 1.0, hue 0.14266667, saturation 0.4901961, and lightness 0.47254902.
     * It has the hex RGBA8888 value {@code 0xb7a53aff}.
     * <pre>
     * <font style='background-color: #B7A53A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B7A53A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B7A53A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B7A53A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B7A53A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B7A53A'>&nbsp;@&nbsp;</font><font style='background-color: #B7A53A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B7A53A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B7A53A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_YELLOW_SAFFRON = 0xb7a53aff;
    static { NAMED.put("deep_yellow_saffron", 0xb7a53aff); LIST.add(0xb7a53aff); NAMES.add("deep_yellow_saffron"); }

    /**
     * This color constant "true_yellow_saffron" has RGBA8888 code {@code E1CF61FF}, red 0.88235295, green 0.8117647, blue 0.38039216, alpha 1.0, hue 0.14322917, saturation 0.50196075, and lightness 0.6313726.
     * It has the hex RGBA8888 value {@code 0xe1cf61ff}.
     * <pre>
     * <font style='background-color: #E1CF61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1CF61; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1CF61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E1CF61'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E1CF61'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E1CF61'>&nbsp;@&nbsp;</font><font style='background-color: #E1CF61; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1CF61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1CF61; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_YELLOW_SAFFRON = 0xe1cf61ff;
    static { NAMED.put("true_yellow_saffron", 0xe1cf61ff); LIST.add(0xe1cf61ff); NAMES.add("true_yellow_saffron"); }

    /**
     * This color constant "bright_yellow_saffron" has RGBA8888 code {@code FFF8B2FF}, red 1.0, green 0.972549, blue 0.69803923, alpha 1.0, hue 0.15151516, saturation 0.30196077, and lightness 0.84901965.
     * It has the hex RGBA8888 value {@code 0xfff8b2ff}.
     * <pre>
     * <font style='background-color: #FFF8B2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFF8B2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFF8B2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFF8B2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFF8B2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFF8B2'>&nbsp;@&nbsp;</font><font style='background-color: #FFF8B2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFF8B2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFF8B2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_YELLOW_SAFFRON = 0xfff8b2ff;
    static { NAMED.put("bright_yellow_saffron", 0xfff8b2ff); LIST.add(0xfff8b2ff); NAMES.add("bright_yellow_saffron"); }

    /**
     * This color constant "deep_saffron_yellow" has RGBA8888 code {@code A79C2FFF}, red 0.654902, green 0.6117647, blue 0.18431373, alpha 1.0, hue 0.15138888, saturation 0.47058827, and lightness 0.41960788.
     * It has the hex RGBA8888 value {@code 0xa79c2fff}.
     * <pre>
     * <font style='background-color: #A79C2F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A79C2F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A79C2F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A79C2F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A79C2F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A79C2F'>&nbsp;@&nbsp;</font><font style='background-color: #A79C2F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A79C2F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A79C2F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_SAFFRON_YELLOW = 0xa79c2fff;
    static { NAMED.put("deep_saffron_yellow", 0xa79c2fff); LIST.add(0xa79c2fff); NAMES.add("deep_saffron_yellow"); }

    /**
     * This color constant "true_saffron_yellow" has RGBA8888 code {@code C4B94BFF}, red 0.76862746, green 0.7254902, blue 0.29411766, alpha 1.0, hue 0.15151516, saturation 0.4745098, and lightness 0.53137255.
     * It has the hex RGBA8888 value {@code 0xc4b94bff}.
     * <pre>
     * <font style='background-color: #C4B94B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4B94B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4B94B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C4B94B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C4B94B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C4B94B'>&nbsp;@&nbsp;</font><font style='background-color: #C4B94B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4B94B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4B94B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_SAFFRON_YELLOW = 0xc4b94bff;
    static { NAMED.put("true_saffron_yellow", 0xc4b94bff); LIST.add(0xc4b94bff); NAMES.add("true_saffron_yellow"); }

    /**
     * This color constant "bright_saffron_yellow" has RGBA8888 code {@code DFD464FF}, red 0.8745098, green 0.83137256, blue 0.39215687, alpha 1.0, hue 0.15176152, saturation 0.48235294, and lightness 0.6333334.
     * It has the hex RGBA8888 value {@code 0xdfd464ff}.
     * <pre>
     * <font style='background-color: #DFD464;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DFD464; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DFD464;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DFD464'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DFD464'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DFD464'>&nbsp;@&nbsp;</font><font style='background-color: #DFD464; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DFD464;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DFD464; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_SAFFRON_YELLOW = 0xdfd464ff;
    static { NAMED.put("bright_saffron_yellow", 0xdfd464ff); LIST.add(0xdfd464ff); NAMES.add("bright_saffron_yellow"); }

    /**
     * This color constant "deep_pure_yellow" has RGBA8888 code {@code A9AD26FF}, red 0.6627451, green 0.6784314, blue 0.14901961, alpha 1.0, hue 0.17160495, saturation 0.5294118, and lightness 0.4137255.
     * It has the hex RGBA8888 value {@code 0xa9ad26ff}.
     * <pre>
     * <font style='background-color: #A9AD26;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A9AD26; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A9AD26;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A9AD26'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A9AD26'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A9AD26'>&nbsp;@&nbsp;</font><font style='background-color: #A9AD26; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A9AD26;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A9AD26; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_PURE_YELLOW = 0xa9ad26ff;
    static { NAMED.put("deep_pure_yellow", 0xa9ad26ff); LIST.add(0xa9ad26ff); NAMES.add("deep_pure_yellow"); }

    /**
     * This color constant "true_pure_yellow" has RGBA8888 code {@code CCD14BFF}, red 0.8, green 0.81960785, blue 0.29411766, alpha 1.0, hue 0.17288557, saturation 0.52549016, and lightness 0.5568628.
     * It has the hex RGBA8888 value {@code 0xccd14bff}.
     * <pre>
     * <font style='background-color: #CCD14B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CCD14B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CCD14B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CCD14B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CCD14B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CCD14B'>&nbsp;@&nbsp;</font><font style='background-color: #CCD14B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CCD14B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CCD14B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_PURE_YELLOW = 0xccd14bff;
    static { NAMED.put("true_pure_yellow", 0xccd14bff); LIST.add(0xccd14bff); NAMES.add("true_pure_yellow"); }

    /**
     * This color constant "bright_pure_yellow" has RGBA8888 code {@code EFF66BFF}, red 0.9372549, green 0.9647059, blue 0.41960785, alpha 1.0, hue 0.17505997, saturation 0.54509807, and lightness 0.6921569.
     * It has the hex RGBA8888 value {@code 0xeff66bff}.
     * <pre>
     * <font style='background-color: #EFF66B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFF66B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFF66B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EFF66B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EFF66B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EFF66B'>&nbsp;@&nbsp;</font><font style='background-color: #EFF66B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFF66B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFF66B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_PURE_YELLOW = 0xeff66bff;
    static { NAMED.put("bright_pure_yellow", 0xeff66bff); LIST.add(0xeff66bff); NAMES.add("bright_pure_yellow"); }

    /**
     * This color constant "deep_lime_yellow" has RGBA8888 code {@code AAB442FF}, red 0.6666667, green 0.7058824, blue 0.25882354, alpha 1.0, hue 0.18128656, saturation 0.44705883, and lightness 0.48235294.
     * It has the hex RGBA8888 value {@code 0xaab442ff}.
     * <pre>
     * <font style='background-color: #AAB442;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AAB442; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AAB442;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AAB442'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AAB442'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AAB442'>&nbsp;@&nbsp;</font><font style='background-color: #AAB442; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AAB442;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AAB442; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_LIME_YELLOW = 0xaab442ff;
    static { NAMED.put("deep_lime_yellow", 0xaab442ff); LIST.add(0xaab442ff); NAMES.add("deep_lime_yellow"); }

    /**
     * This color constant "true_lime_yellow" has RGBA8888 code {@code D4E069FF}, red 0.83137256, green 0.8784314, blue 0.4117647, alpha 1.0, hue 0.1834734, saturation 0.46666667, and lightness 0.64509803.
     * It has the hex RGBA8888 value {@code 0xd4e069ff}.
     * <pre>
     * <font style='background-color: #D4E069;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4E069; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4E069;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D4E069'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D4E069'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D4E069'>&nbsp;@&nbsp;</font><font style='background-color: #D4E069; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4E069;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4E069; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_LIME_YELLOW = 0xd4e069ff;
    static { NAMED.put("true_lime_yellow", 0xd4e069ff); LIST.add(0xd4e069ff); NAMES.add("true_lime_yellow"); }

    /**
     * This color constant "bright_lime_yellow" has RGBA8888 code {@code FFFFEBFF}, red 1.0, green 1.0, blue 0.92156863, alpha 1.0, hue 0.16666667, saturation 0.07843137, and lightness 0.9607843.
     * It has the hex RGBA8888 value {@code 0xffffebff}.
     * <pre>
     * <font style='background-color: #FFFFEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFEB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFFEB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFFEB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFFEB'>&nbsp;@&nbsp;</font><font style='background-color: #FFFFEB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFEB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_LIME_YELLOW = 0xffffebff;
    static { NAMED.put("bright_lime_yellow", 0xffffebff); LIST.add(0xffffebff); NAMES.add("bright_lime_yellow"); }

    /**
     * This color constant "deep_yellow_lime" has RGBA8888 code {@code 8DA42FFF}, red 0.5529412, green 0.6431373, blue 0.18431373, alpha 1.0, hue 0.19943021, saturation 0.45882356, and lightness 0.4137255.
     * It has the hex RGBA8888 value {@code 0x8da42fff}.
     * <pre>
     * <font style='background-color: #8DA42F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8DA42F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8DA42F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8DA42F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8DA42F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8DA42F'>&nbsp;@&nbsp;</font><font style='background-color: #8DA42F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8DA42F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8DA42F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_YELLOW_LIME = 0x8da42fff;
    static { NAMED.put("deep_yellow_lime", 0x8da42fff); LIST.add(0x8da42fff); NAMES.add("deep_yellow_lime"); }

    /**
     * This color constant "true_yellow_lime" has RGBA8888 code {@code A8C14BFF}, red 0.65882355, green 0.75686276, blue 0.29411766, alpha 1.0, hue 0.20197742, saturation 0.4627451, and lightness 0.5254902.
     * It has the hex RGBA8888 value {@code 0xa8c14bff}.
     * <pre>
     * <font style='background-color: #A8C14B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A8C14B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A8C14B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A8C14B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A8C14B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A8C14B'>&nbsp;@&nbsp;</font><font style='background-color: #A8C14B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A8C14B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A8C14B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_YELLOW_LIME = 0xa8c14bff;
    static { NAMED.put("true_yellow_lime", 0xa8c14bff); LIST.add(0xa8c14bff); NAMES.add("true_yellow_lime"); }

    /**
     * This color constant "bright_yellow_lime" has RGBA8888 code {@code C3DD64FF}, red 0.7647059, green 0.8666667, blue 0.39215687, alpha 1.0, hue 0.20247935, saturation 0.4745098, and lightness 0.62941176.
     * It has the hex RGBA8888 value {@code 0xc3dd64ff}.
     * <pre>
     * <font style='background-color: #C3DD64;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3DD64; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3DD64;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C3DD64'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C3DD64'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C3DD64'>&nbsp;@&nbsp;</font><font style='background-color: #C3DD64; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3DD64;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3DD64; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_YELLOW_LIME = 0xc3dd64ff;
    static { NAMED.put("bright_yellow_lime", 0xc3dd64ff); LIST.add(0xc3dd64ff); NAMES.add("bright_yellow_lime"); }

    /**
     * This color constant "deep_pure_lime" has RGBA8888 code {@code 83AD29FF}, red 0.5137255, green 0.6784314, blue 0.16078432, alpha 1.0, hue 0.21969697, saturation 0.5176471, and lightness 0.41960788.
     * It has the hex RGBA8888 value {@code 0x83ad29ff}.
     * <pre>
     * <font style='background-color: #83AD29;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #83AD29; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #83AD29;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #83AD29'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #83AD29'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #83AD29'>&nbsp;@&nbsp;</font><font style='background-color: #83AD29; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #83AD29;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #83AD29; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_PURE_LIME = 0x83ad29ff;
    static { NAMED.put("deep_pure_lime", 0x83ad29ff); LIST.add(0x83ad29ff); NAMES.add("deep_pure_lime"); }

    /**
     * This color constant "true_pure_lime" has RGBA8888 code {@code A4D24CFF}, red 0.6431373, green 0.8235294, blue 0.29803923, alpha 1.0, hue 0.22388059, saturation 0.52549016, and lightness 0.56078434.
     * It has the hex RGBA8888 value {@code 0xa4d24cff}.
     * <pre>
     * <font style='background-color: #A4D24C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4D24C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4D24C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A4D24C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A4D24C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A4D24C'>&nbsp;@&nbsp;</font><font style='background-color: #A4D24C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4D24C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4D24C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_PURE_LIME = 0xa4d24cff;
    static { NAMED.put("true_pure_lime", 0xa4d24cff); LIST.add(0xa4d24cff); NAMES.add("true_pure_lime"); }

    /**
     * This color constant "bright_pure_lime" has RGBA8888 code {@code C6F76DFF}, red 0.7764706, green 0.96862745, blue 0.42745098, alpha 1.0, hue 0.22584541, saturation 0.54117644, and lightness 0.69803923.
     * It has the hex RGBA8888 value {@code 0xc6f76dff}.
     * <pre>
     * <font style='background-color: #C6F76D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C6F76D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C6F76D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C6F76D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C6F76D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C6F76D'>&nbsp;@&nbsp;</font><font style='background-color: #C6F76D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C6F76D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C6F76D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_PURE_LIME = 0xc6f76dff;
    static { NAMED.put("bright_pure_lime", 0xc6f76dff); LIST.add(0xc6f76dff); NAMES.add("bright_pure_lime"); }

    /**
     * This color constant "deep_green_lime" has RGBA8888 code {@code 7BB441FF}, red 0.48235294, green 0.7058824, blue 0.25490198, alpha 1.0, hue 0.24927539, saturation 0.4509804, and lightness 0.4803922.
     * It has the hex RGBA8888 value {@code 0x7bb441ff}.
     * <pre>
     * <font style='background-color: #7BB441;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7BB441; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7BB441;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7BB441'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7BB441'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7BB441'>&nbsp;@&nbsp;</font><font style='background-color: #7BB441; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7BB441;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7BB441; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_GREEN_LIME = 0x7bb441ff;
    static { NAMED.put("deep_green_lime", 0x7bb441ff); LIST.add(0x7bb441ff); NAMES.add("deep_green_lime"); }

    /**
     * This color constant "true_green_lime" has RGBA8888 code {@code A1DF67FF}, red 0.6313726, green 0.8745098, blue 0.40392157, alpha 1.0, hue 0.25277779, saturation 0.47058824, and lightness 0.6392157.
     * It has the hex RGBA8888 value {@code 0xa1df67ff}.
     * <pre>
     * <font style='background-color: #A1DF67;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A1DF67; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A1DF67;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A1DF67'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A1DF67'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A1DF67'>&nbsp;@&nbsp;</font><font style='background-color: #A1DF67; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A1DF67;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A1DF67; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_GREEN_LIME = 0xa1df67ff;
    static { NAMED.put("true_green_lime", 0xa1df67ff); LIST.add(0xa1df67ff); NAMES.add("true_green_lime"); }

    /**
     * This color constant "bright_green_lime" has RGBA8888 code {@code DBFFB0FF}, red 0.85882354, green 1.0, blue 0.6901961, alpha 1.0, hue 0.24261604, saturation 0.3098039, and lightness 0.845098.
     * It has the hex RGBA8888 value {@code 0xdbffb0ff}.
     * <pre>
     * <font style='background-color: #DBFFB0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBFFB0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBFFB0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DBFFB0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DBFFB0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DBFFB0'>&nbsp;@&nbsp;</font><font style='background-color: #DBFFB0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBFFB0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBFFB0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_GREEN_LIME = 0xdbffb0ff;
    static { NAMED.put("bright_green_lime", 0xdbffb0ff); LIST.add(0xdbffb0ff); NAMES.add("bright_green_lime"); }

    /**
     * This color constant "deep_lime_green" has RGBA8888 code {@code 5DA430FF}, red 0.3647059, green 0.6431373, blue 0.1882353, alpha 1.0, hue 0.2686782, saturation 0.454902, and lightness 0.41568628.
     * It has the hex RGBA8888 value {@code 0x5da430ff}.
     * <pre>
     * <font style='background-color: #5DA430;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5DA430; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5DA430;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5DA430'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5DA430'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5DA430'>&nbsp;@&nbsp;</font><font style='background-color: #5DA430; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5DA430;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5DA430; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_LIME_GREEN = 0x5da430ff;
    static { NAMED.put("deep_lime_green", 0x5da430ff); LIST.add(0x5da430ff); NAMES.add("deep_lime_green"); }

    /**
     * This color constant "true_lime_green" has RGBA8888 code {@code 76C14AFF}, red 0.4627451, green 0.75686276, blue 0.2901961, alpha 1.0, hue 0.2717087, saturation 0.46666667, and lightness 0.52352947.
     * It has the hex RGBA8888 value {@code 0x76c14aff}.
     * <pre>
     * <font style='background-color: #76C14A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #76C14A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #76C14A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #76C14A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #76C14A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #76C14A'>&nbsp;@&nbsp;</font><font style='background-color: #76C14A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #76C14A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #76C14A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_LIME_GREEN = 0x76c14aff;
    static { NAMED.put("true_lime_green", 0x76c14aff); LIST.add(0x76c14aff); NAMES.add("true_lime_green"); }

    /**
     * This color constant "bright_lime_green" has RGBA8888 code {@code 8FDE63FF}, red 0.56078434, green 0.87058824, blue 0.3882353, alpha 1.0, hue 0.27371275, saturation 0.48235294, and lightness 0.62941176.
     * It has the hex RGBA8888 value {@code 0x8fde63ff}.
     * <pre>
     * <font style='background-color: #8FDE63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8FDE63; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8FDE63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8FDE63'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8FDE63'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8FDE63'>&nbsp;@&nbsp;</font><font style='background-color: #8FDE63; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8FDE63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8FDE63; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_LIME_GREEN = 0x8fde63ff;
    static { NAMED.put("bright_lime_green", 0x8fde63ff); LIST.add(0x8fde63ff); NAMES.add("bright_lime_green"); }

    /**
     * This color constant "deep_pure_green" has RGBA8888 code {@code 3BB12CFF}, red 0.23137255, green 0.69411767, blue 0.17254902, alpha 1.0, hue 0.31453636, saturation 0.52156866, and lightness 0.43333337.
     * It has the hex RGBA8888 value {@code 0x3bb12cff}.
     * <pre>
     * <font style='background-color: #3BB12C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3BB12C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3BB12C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3BB12C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3BB12C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3BB12C'>&nbsp;@&nbsp;</font><font style='background-color: #3BB12C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3BB12C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3BB12C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_PURE_GREEN = 0x3bb12cff;
    static { NAMED.put("deep_pure_green", 0x3bb12cff); LIST.add(0x3bb12cff); NAMES.add("deep_pure_green"); }

    /**
     * This color constant "true_pure_green" has RGBA8888 code {@code 5CD54CFF}, red 0.36078432, green 0.8352941, blue 0.29803923, alpha 1.0, hue 0.3138686, saturation 0.5372549, and lightness 0.56666666.
     * It has the hex RGBA8888 value {@code 0x5cd54cff}.
     * <pre>
     * <font style='background-color: #5CD54C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5CD54C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5CD54C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5CD54C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5CD54C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5CD54C'>&nbsp;@&nbsp;</font><font style='background-color: #5CD54C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5CD54C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5CD54C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_PURE_GREEN = 0x5cd54cff;
    static { NAMED.put("true_pure_green", 0x5cd54cff); LIST.add(0x5cd54cff); NAMES.add("true_pure_green"); }

    /**
     * This color constant "bright_pure_green" has RGBA8888 code {@code 7AF76AFF}, red 0.47843137, green 0.96862745, blue 0.41568628, alpha 1.0, hue 0.31442082, saturation 0.5529412, and lightness 0.69215685.
     * It has the hex RGBA8888 value {@code 0x7af76aff}.
     * <pre>
     * <font style='background-color: #7AF76A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7AF76A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7AF76A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7AF76A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7AF76A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7AF76A'>&nbsp;@&nbsp;</font><font style='background-color: #7AF76A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7AF76A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7AF76A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_PURE_GREEN = 0x7af76aff;
    static { NAMED.put("bright_pure_green", 0x7af76aff); LIST.add(0x7af76aff); NAMES.add("bright_pure_green"); }

    /**
     * This color constant "deep_cyan_green" has RGBA8888 code {@code 3DB379FF}, red 0.23921569, green 0.7019608, blue 0.4745098, alpha 1.0, hue 0.4180791, saturation 0.46274513, and lightness 0.47058827.
     * It has the hex RGBA8888 value {@code 0x3db379ff}.
     * <pre>
     * <font style='background-color: #3DB379;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3DB379; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3DB379;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3DB379'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3DB379'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3DB379'>&nbsp;@&nbsp;</font><font style='background-color: #3DB379; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3DB379;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3DB379; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_CYAN_GREEN = 0x3db379ff;
    static { NAMED.put("deep_cyan_green", 0x3db379ff); LIST.add(0x3db379ff); NAMES.add("deep_cyan_green"); }

    /**
     * This color constant "true_cyan_green" has RGBA8888 code {@code 65DE9FFF}, red 0.39607844, green 0.87058824, blue 0.62352943, alpha 1.0, hue 0.41322315, saturation 0.4745098, and lightness 0.6333333.
     * It has the hex RGBA8888 value {@code 0x65de9fff}.
     * <pre>
     * <font style='background-color: #65DE9F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #65DE9F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #65DE9F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #65DE9F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #65DE9F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #65DE9F'>&nbsp;@&nbsp;</font><font style='background-color: #65DE9F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #65DE9F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #65DE9F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_CYAN_GREEN = 0x65de9fff;
    static { NAMED.put("true_cyan_green", 0x65de9fff); LIST.add(0x65de9fff); NAMES.add("true_cyan_green"); }

    /**
     * This color constant "bright_cyan_green" has RGBA8888 code {@code A1FFCBFF}, red 0.6313726, green 1.0, blue 0.79607844, alpha 1.0, hue 0.40780142, saturation 0.36862743, and lightness 0.8156863.
     * It has the hex RGBA8888 value {@code 0xa1ffcbff}.
     * <pre>
     * <font style='background-color: #A1FFCB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A1FFCB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A1FFCB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A1FFCB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A1FFCB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A1FFCB'>&nbsp;@&nbsp;</font><font style='background-color: #A1FFCB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A1FFCB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A1FFCB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_CYAN_GREEN = 0xa1ffcbff;
    static { NAMED.put("bright_cyan_green", 0xa1ffcbff); LIST.add(0xa1ffcbff); NAMES.add("bright_cyan_green"); }

    /**
     * This color constant "deep_green_cyan" has RGBA8888 code {@code 32A68CFF}, red 0.19607843, green 0.6509804, blue 0.54901963, alpha 1.0, hue 0.46264368, saturation 0.454902, and lightness 0.42352942.
     * It has the hex RGBA8888 value {@code 0x32a68cff}.
     * <pre>
     * <font style='background-color: #32A68C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #32A68C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #32A68C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #32A68C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #32A68C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #32A68C'>&nbsp;@&nbsp;</font><font style='background-color: #32A68C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #32A68C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #32A68C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_GREEN_CYAN = 0x32a68cff;
    static { NAMED.put("deep_green_cyan", 0x32a68cff); LIST.add(0x32a68cff); NAMES.add("deep_green_cyan"); }

    /**
     * This color constant "true_green_cyan" has RGBA8888 code {@code 4EC2A6FF}, red 0.30588236, green 0.7607843, blue 0.6509804, alpha 1.0, hue 0.45977014, saturation 0.45490196, and lightness 0.53333336.
     * It has the hex RGBA8888 value {@code 0x4ec2a6ff}.
     * <pre>
     * <font style='background-color: #4EC2A6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4EC2A6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4EC2A6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4EC2A6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4EC2A6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4EC2A6'>&nbsp;@&nbsp;</font><font style='background-color: #4EC2A6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4EC2A6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4EC2A6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_GREEN_CYAN = 0x4ec2a6ff;
    static { NAMED.put("true_green_cyan", 0x4ec2a6ff); LIST.add(0x4ec2a6ff); NAMES.add("true_green_cyan"); }

    /**
     * This color constant "bright_green_cyan" has RGBA8888 code {@code 68DEC0FF}, red 0.40784314, green 0.87058824, blue 0.7529412, alpha 1.0, hue 0.45762712, saturation 0.4627451, and lightness 0.63921565.
     * It has the hex RGBA8888 value {@code 0x68dec0ff}.
     * <pre>
     * <font style='background-color: #68DEC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #68DEC0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #68DEC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #68DEC0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #68DEC0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #68DEC0'>&nbsp;@&nbsp;</font><font style='background-color: #68DEC0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #68DEC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #68DEC0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_GREEN_CYAN = 0x68dec0ff;
    static { NAMED.put("bright_green_cyan", 0x68dec0ff); LIST.add(0x68dec0ff); NAMES.add("bright_green_cyan"); }

    /**
     * This color constant "deep_pure_cyan" has RGBA8888 code {@code 1DADAFFF}, red 0.11372549, green 0.6784314, blue 0.6862745, alpha 1.0, hue 0.5022831, saturation 0.57254905, and lightness 0.4.
     * It has the hex RGBA8888 value {@code 0x1dadafff}.
     * <pre>
     * <font style='background-color: #1DADAF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1DADAF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1DADAF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1DADAF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1DADAF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1DADAF'>&nbsp;@&nbsp;</font><font style='background-color: #1DADAF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1DADAF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1DADAF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_PURE_CYAN = 0x1dadafff;
    static { NAMED.put("deep_pure_cyan", 0x1dadafff); LIST.add(0x1dadafff); NAMES.add("deep_pure_cyan"); }

    /**
     * This color constant "true_pure_cyan" has RGBA8888 code {@code 48D1D3FF}, red 0.28235295, green 0.81960785, blue 0.827451, alpha 1.0, hue 0.50239813, saturation 0.54509807, and lightness 0.55490196.
     * It has the hex RGBA8888 value {@code 0x48d1d3ff}.
     * <pre>
     * <font style='background-color: #48D1D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #48D1D3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #48D1D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #48D1D3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #48D1D3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #48D1D3'>&nbsp;@&nbsp;</font><font style='background-color: #48D1D3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #48D1D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #48D1D3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_PURE_CYAN = 0x48d1d3ff;
    static { NAMED.put("true_pure_cyan", 0x48d1d3ff); LIST.add(0x48d1d3ff); NAMES.add("true_pure_cyan"); }

    /**
     * This color constant "bright_pure_cyan" has RGBA8888 code {@code 6CF6F7FF}, red 0.42352942, green 0.9647059, blue 0.96862745, alpha 1.0, hue 0.50119907, saturation 0.54509807, and lightness 0.6960784.
     * It has the hex RGBA8888 value {@code 0x6cf6f7ff}.
     * <pre>
     * <font style='background-color: #6CF6F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6CF6F7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6CF6F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6CF6F7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6CF6F7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6CF6F7'>&nbsp;@&nbsp;</font><font style='background-color: #6CF6F7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6CF6F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6CF6F7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_PURE_CYAN = 0x6cf6f7ff;
    static { NAMED.put("bright_pure_cyan", 0x6cf6f7ff); LIST.add(0x6cf6f7ff); NAMES.add("bright_pure_cyan"); }

    /**
     * This color constant "deep_blue_cyan" has RGBA8888 code {@code 3A9CB3FF}, red 0.22745098, green 0.6117647, blue 0.7019608, alpha 1.0, hue 0.53168046, saturation 0.47450984, and lightness 0.46470588.
     * It has the hex RGBA8888 value {@code 0x3a9cb3ff}.
     * <pre>
     * <font style='background-color: #3A9CB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3A9CB3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3A9CB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3A9CB3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3A9CB3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3A9CB3'>&nbsp;@&nbsp;</font><font style='background-color: #3A9CB3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3A9CB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3A9CB3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_BLUE_CYAN = 0x3a9cb3ff;
    static { NAMED.put("deep_blue_cyan", 0x3a9cb3ff); LIST.add(0x3a9cb3ff); NAMES.add("deep_blue_cyan"); }

    /**
     * This color constant "true_blue_cyan" has RGBA8888 code {@code 61C5DEFF}, red 0.38039216, green 0.77254903, blue 0.87058824, alpha 1.0, hue 0.53333336, saturation 0.49019608, and lightness 0.6254902.
     * It has the hex RGBA8888 value {@code 0x61c5deff}.
     * <pre>
     * <font style='background-color: #61C5DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #61C5DE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #61C5DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #61C5DE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #61C5DE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #61C5DE'>&nbsp;@&nbsp;</font><font style='background-color: #61C5DE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #61C5DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #61C5DE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_BLUE_CYAN = 0x61c5deff;
    static { NAMED.put("true_blue_cyan", 0x61c5deff); LIST.add(0x61c5deff); NAMES.add("true_blue_cyan"); }

    /**
     * This color constant "bright_blue_cyan" has RGBA8888 code {@code 8DEAFFFF}, red 0.5529412, green 0.91764706, blue 1.0, alpha 1.0, hue 0.53070176, saturation 0.4470588, and lightness 0.7764706.
     * It has the hex RGBA8888 value {@code 0x8deaffff}.
     * <pre>
     * <font style='background-color: #8DEAFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8DEAFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8DEAFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8DEAFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8DEAFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8DEAFF'>&nbsp;@&nbsp;</font><font style='background-color: #8DEAFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8DEAFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8DEAFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_BLUE_CYAN = 0x8deaffff;
    static { NAMED.put("bright_blue_cyan", 0x8deaffff); LIST.add(0x8deaffff); NAMES.add("bright_blue_cyan"); }

    /**
     * This color constant "deep_cyan_blue" has RGBA8888 code {@code 2571A5FF}, red 0.14509805, green 0.44313726, blue 0.64705884, alpha 1.0, hue 0.5677084, saturation 0.5019608, and lightness 0.39607844.
     * It has the hex RGBA8888 value {@code 0x2571a5ff}.
     * <pre>
     * <font style='background-color: #2571A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2571A5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2571A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2571A5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2571A5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2571A5'>&nbsp;@&nbsp;</font><font style='background-color: #2571A5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2571A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2571A5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_CYAN_BLUE = 0x2571a5ff;
    static { NAMED.put("deep_cyan_blue", 0x2571a5ff); LIST.add(0x2571a5ff); NAMES.add("deep_cyan_blue"); }

    /**
     * This color constant "true_cyan_blue" has RGBA8888 code {@code 3E8CC2FF}, red 0.24313726, green 0.54901963, blue 0.7607843, alpha 1.0, hue 0.5681818, saturation 0.5176471, and lightness 0.50196075.
     * It has the hex RGBA8888 value {@code 0x3e8cc2ff}.
     * <pre>
     * <font style='background-color: #3E8CC2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3E8CC2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3E8CC2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3E8CC2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3E8CC2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3E8CC2'>&nbsp;@&nbsp;</font><font style='background-color: #3E8CC2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3E8CC2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3E8CC2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_CYAN_BLUE = 0x3e8cc2ff;
    static { NAMED.put("true_cyan_blue", 0x3e8cc2ff); LIST.add(0x3e8cc2ff); NAMES.add("true_cyan_blue"); }

    /**
     * This color constant "bright_cyan_blue" has RGBA8888 code {@code 57A7E0FF}, red 0.34117648, green 0.654902, blue 0.8784314, alpha 1.0, hue 0.5693431, saturation 0.5372549, and lightness 0.6098039.
     * It has the hex RGBA8888 value {@code 0x57a7e0ff}.
     * <pre>
     * <font style='background-color: #57A7E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57A7E0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57A7E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #57A7E0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #57A7E0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #57A7E0'>&nbsp;@&nbsp;</font><font style='background-color: #57A7E0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57A7E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57A7E0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_CYAN_BLUE = 0x57a7e0ff;
    static { NAMED.put("bright_cyan_blue", 0x57a7e0ff); LIST.add(0x57a7e0ff); NAMES.add("bright_cyan_blue"); }

    /**
     * This color constant "deep_pure_blue" has RGBA8888 code {@code 062CB7FF}, red 0.023529412, green 0.17254902, blue 0.7176471, alpha 1.0, hue 0.6308851, saturation 0.69411767, and lightness 0.37058824.
     * It has the hex RGBA8888 value {@code 0x062cb7ff}.
     * <pre>
     * <font style='background-color: #062CB7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #062CB7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #062CB7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #062CB7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #062CB7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #062CB7'>&nbsp;@&nbsp;</font><font style='background-color: #062CB7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #062CB7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #062CB7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_PURE_BLUE = 0x062cb7ff;
    static { NAMED.put("deep_pure_blue", 0x062cb7ff); LIST.add(0x062cb7ff); NAMES.add("deep_pure_blue"); }

    /**
     * This color constant "true_pure_blue" has RGBA8888 code {@code 1A49DBFF}, red 0.101960786, green 0.28627452, blue 0.85882354, alpha 1.0, hue 0.62607944, saturation 0.75686276, and lightness 0.48039216.
     * It has the hex RGBA8888 value {@code 0x1a49dbff}.
     * <pre>
     * <font style='background-color: #1A49DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1A49DB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1A49DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1A49DB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1A49DB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1A49DB'>&nbsp;@&nbsp;</font><font style='background-color: #1A49DB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1A49DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1A49DB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_PURE_BLUE = 0x1a49dbff;
    static { NAMED.put("true_pure_blue", 0x1a49dbff); LIST.add(0x1a49dbff); NAMES.add("true_pure_blue"); }

    /**
     * This color constant "bright_pure_blue" has RGBA8888 code {@code 2F65FEFF}, red 0.18431373, green 0.39607844, blue 0.99607843, alpha 1.0, hue 0.62318844, saturation 0.8117647, and lightness 0.590196.
     * It has the hex RGBA8888 value {@code 0x2f65feff}.
     * <pre>
     * <font style='background-color: #2F65FE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2F65FE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2F65FE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2F65FE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2F65FE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2F65FE'>&nbsp;@&nbsp;</font><font style='background-color: #2F65FE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2F65FE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2F65FE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_PURE_BLUE = 0x2f65feff;
    static { NAMED.put("bright_pure_blue", 0x2f65feff); LIST.add(0x2f65feff); NAMES.add("bright_pure_blue"); }

    /**
     * This color constant "deep_violet_blue" has RGBA8888 code {@code 3334AFFF}, red 0.2, green 0.20392157, blue 0.6862745, alpha 1.0, hue 0.6653226, saturation 0.48627454, and lightness 0.44313726.
     * It has the hex RGBA8888 value {@code 0x3334afff}.
     * <pre>
     * <font style='background-color: #3334AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3334AF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3334AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3334AF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3334AF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3334AF'>&nbsp;@&nbsp;</font><font style='background-color: #3334AF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3334AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3334AF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_VIOLET_BLUE = 0x3334afff;
    static { NAMED.put("deep_violet_blue", 0x3334afff); LIST.add(0x3334afff); NAMES.add("deep_violet_blue"); }

    /**
     * This color constant "true_violet_blue" has RGBA8888 code {@code 4A53D5FF}, red 0.2901961, green 0.3254902, blue 0.8352941, alpha 1.0, hue 0.6558753, saturation 0.54509807, and lightness 0.5627451.
     * It has the hex RGBA8888 value {@code 0x4a53d5ff}.
     * <pre>
     * <font style='background-color: #4A53D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A53D5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A53D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4A53D5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4A53D5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4A53D5'>&nbsp;@&nbsp;</font><font style='background-color: #4A53D5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A53D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A53D5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_VIOLET_BLUE = 0x4a53d5ff;
    static { NAMED.put("true_violet_blue", 0x4a53d5ff); LIST.add(0x4a53d5ff); NAMES.add("true_violet_blue"); }

    /**
     * This color constant "bright_violet_blue" has RGBA8888 code {@code 6372FCFF}, red 0.3882353, green 0.44705883, blue 0.9882353, alpha 1.0, hue 0.65032685, saturation 0.6, and lightness 0.6882353.
     * It has the hex RGBA8888 value {@code 0x6372fcff}.
     * <pre>
     * <font style='background-color: #6372FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6372FC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6372FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6372FC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6372FC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6372FC'>&nbsp;@&nbsp;</font><font style='background-color: #6372FC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6372FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6372FC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_VIOLET_BLUE = 0x6372fcff;
    static { NAMED.put("bright_violet_blue", 0x6372fcff); LIST.add(0x6372fcff); NAMES.add("bright_violet_blue"); }

    /**
     * This color constant "deep_blue_violet" has RGBA8888 code {@code 4629A5FF}, red 0.27450982, green 0.16078432, blue 0.64705884, alpha 1.0, hue 0.7056452, saturation 0.48627454, and lightness 0.40392157.
     * It has the hex RGBA8888 value {@code 0x4629a5ff}.
     * <pre>
     * <font style='background-color: #4629A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4629A5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4629A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4629A5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4629A5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4629A5'>&nbsp;@&nbsp;</font><font style='background-color: #4629A5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4629A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4629A5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_BLUE_VIOLET = 0x4629a5ff;
    static { NAMED.put("deep_blue_violet", 0x4629a5ff); LIST.add(0x4629a5ff); NAMES.add("deep_blue_violet"); }

    /**
     * This color constant "true_blue_violet" has RGBA8888 code {@code 5A42C3FF}, red 0.3529412, green 0.25882354, blue 0.7647059, alpha 1.0, hue 0.69767445, saturation 0.5058824, and lightness 0.5117647.
     * It has the hex RGBA8888 value {@code 0x5a42c3ff}.
     * <pre>
     * <font style='background-color: #5A42C3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A42C3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A42C3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5A42C3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5A42C3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5A42C3'>&nbsp;@&nbsp;</font><font style='background-color: #5A42C3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A42C3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A42C3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_BLUE_VIOLET = 0x5a42c3ff;
    static { NAMED.put("true_blue_violet", 0x5a42c3ff); LIST.add(0x5a42c3ff); NAMES.add("true_blue_violet"); }

    /**
     * This color constant "bright_blue_violet" has RGBA8888 code {@code 6E58DFFF}, red 0.43137255, green 0.34509805, blue 0.8745098, alpha 1.0, hue 0.69382715, saturation 0.5294118, and lightness 0.6098039.
     * It has the hex RGBA8888 value {@code 0x6e58dfff}.
     * <pre>
     * <font style='background-color: #6E58DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E58DF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E58DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6E58DF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6E58DF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6E58DF'>&nbsp;@&nbsp;</font><font style='background-color: #6E58DF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E58DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E58DF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_BLUE_VIOLET = 0x6e58dfff;
    static { NAMED.put("bright_blue_violet", 0x6e58dfff); LIST.add(0x6e58dfff); NAMES.add("bright_blue_violet"); }

    /**
     * This color constant "deep_pure_violet" has RGBA8888 code {@code 6129B3FF}, red 0.38039216, green 0.16078432, blue 0.7019608, alpha 1.0, hue 0.73429954, saturation 0.5411765, and lightness 0.43137255.
     * It has the hex RGBA8888 value {@code 0x6129b3ff}.
     * <pre>
     * <font style='background-color: #6129B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6129B3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6129B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6129B3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6129B3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6129B3'>&nbsp;@&nbsp;</font><font style='background-color: #6129B3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6129B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6129B3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_PURE_VIOLET = 0x6129b3ff;
    static { NAMED.put("deep_pure_violet", 0x6129b3ff); LIST.add(0x6129b3ff); NAMES.add("deep_pure_violet"); }

    /**
     * This color constant "true_pure_violet" has RGBA8888 code {@code 7C46D6FF}, red 0.4862745, green 0.27450982, blue 0.8392157, alpha 1.0, hue 0.7291667, saturation 0.56470585, and lightness 0.5568628.
     * It has the hex RGBA8888 value {@code 0x7c46d6ff}.
     * <pre>
     * <font style='background-color: #7C46D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7C46D6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7C46D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7C46D6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7C46D6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7C46D6'>&nbsp;@&nbsp;</font><font style='background-color: #7C46D6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7C46D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7C46D6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_PURE_VIOLET = 0x7c46d6ff;
    static { NAMED.put("true_pure_violet", 0x7c46d6ff); LIST.add(0x7c46d6ff); NAMES.add("true_pure_violet"); }

    /**
     * This color constant "bright_pure_violet" has RGBA8888 code {@code 9862F9FF}, red 0.59607846, green 0.38431373, blue 0.9764706, alpha 1.0, hue 0.72626936, saturation 0.5921569, and lightness 0.68039215.
     * It has the hex RGBA8888 value {@code 0x9862f9ff}.
     * <pre>
     * <font style='background-color: #9862F9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9862F9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9862F9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9862F9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9862F9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9862F9'>&nbsp;@&nbsp;</font><font style='background-color: #9862F9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9862F9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9862F9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_PURE_VIOLET = 0x9862f9ff;
    static { NAMED.put("bright_pure_violet", 0x9862f9ff); LIST.add(0x9862f9ff); NAMES.add("bright_pure_violet"); }

    /**
     * This color constant "deep_purple_violet" has RGBA8888 code {@code 6E34AFFF}, red 0.43137255, green 0.20392157, blue 0.6862745, alpha 1.0, hue 0.7452575, saturation 0.48235297, and lightness 0.445098.
     * It has the hex RGBA8888 value {@code 0x6e34afff}.
     * <pre>
     * <font style='background-color: #6E34AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E34AF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E34AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6E34AF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6E34AF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6E34AF'>&nbsp;@&nbsp;</font><font style='background-color: #6E34AF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E34AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E34AF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_PURPLE_VIOLET = 0x6e34afff;
    static { NAMED.put("deep_purple_violet", 0x6e34afff); LIST.add(0x6e34afff); NAMES.add("deep_purple_violet"); }

    /**
     * This color constant "true_purple_violet" has RGBA8888 code {@code 9055D8FF}, red 0.5647059, green 0.33333334, blue 0.84705883, alpha 1.0, hue 0.74173033, saturation 0.5137255, and lightness 0.5901961.
     * It has the hex RGBA8888 value {@code 0x9055d8ff}.
     * <pre>
     * <font style='background-color: #9055D8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9055D8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9055D8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9055D8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9055D8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9055D8'>&nbsp;@&nbsp;</font><font style='background-color: #9055D8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9055D8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9055D8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_PURPLE_VIOLET = 0x9055d8ff;
    static { NAMED.put("true_purple_violet", 0x9055d8ff); LIST.add(0x9055d8ff); NAMES.add("true_purple_violet"); }

    /**
     * This color constant "bright_purple_violet" has RGBA8888 code {@code B275FFFF}, red 0.69803923, green 0.45882353, blue 1.0, alpha 1.0, hue 0.7403382, saturation 0.54117644, and lightness 0.7294118.
     * It has the hex RGBA8888 value {@code 0xb275ffff}.
     * <pre>
     * <font style='background-color: #B275FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B275FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B275FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B275FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B275FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B275FF'>&nbsp;@&nbsp;</font><font style='background-color: #B275FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B275FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B275FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_PURPLE_VIOLET = 0xb275ffff;
    static { NAMED.put("bright_purple_violet", 0xb275ffff); LIST.add(0xb275ffff); NAMES.add("bright_purple_violet"); }

    /**
     * This color constant "deep_violet_purple" has RGBA8888 code {@code 712BA4FF}, red 0.44313726, green 0.16862746, blue 0.6431373, alpha 1.0, hue 0.7630854, saturation 0.47450984, and lightness 0.40588236.
     * It has the hex RGBA8888 value {@code 0x712ba4ff}.
     * <pre>
     * <font style='background-color: #712BA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #712BA4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #712BA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #712BA4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #712BA4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #712BA4'>&nbsp;@&nbsp;</font><font style='background-color: #712BA4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #712BA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #712BA4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_VIOLET_PURPLE = 0x712ba4ff;
    static { NAMED.put("deep_violet_purple", 0x712ba4ff); LIST.add(0x712ba4ff); NAMES.add("deep_violet_purple"); }

    /**
     * This color constant "true_violet_purple" has RGBA8888 code {@code 8B44C2FF}, red 0.54509807, green 0.26666668, blue 0.7607843, alpha 1.0, hue 0.76058203, saturation 0.49411765, and lightness 0.5137255.
     * It has the hex RGBA8888 value {@code 0x8b44c2ff}.
     * <pre>
     * <font style='background-color: #8B44C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B44C2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B44C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8B44C2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8B44C2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8B44C2'>&nbsp;@&nbsp;</font><font style='background-color: #8B44C2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B44C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B44C2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_VIOLET_PURPLE = 0x8b44c2ff;
    static { NAMED.put("true_violet_purple", 0x8b44c2ff); LIST.add(0x8b44c2ff); NAMES.add("true_violet_purple"); }

    /**
     * This color constant "bright_violet_purple" has RGBA8888 code {@code A45BDFFF}, red 0.6431373, green 0.35686275, blue 0.8745098, alpha 1.0, hue 0.7588384, saturation 0.517647, and lightness 0.6156863.
     * It has the hex RGBA8888 value {@code 0xa45bdfff}.
     * <pre>
     * <font style='background-color: #A45BDF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A45BDF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A45BDF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A45BDF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A45BDF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A45BDF'>&nbsp;@&nbsp;</font><font style='background-color: #A45BDF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A45BDF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A45BDF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_VIOLET_PURPLE = 0xa45bdfff;
    static { NAMED.put("bright_violet_purple", 0xa45bdfff); LIST.add(0xa45bdfff); NAMES.add("bright_violet_purple"); }

    /**
     * This color constant "deep_pure_purple" has RGBA8888 code {@code 8828B5FF}, red 0.53333336, green 0.15686275, blue 0.70980394, alpha 1.0, hue 0.7801419, saturation 0.5529412, and lightness 0.43333334.
     * It has the hex RGBA8888 value {@code 0x8828b5ff}.
     * <pre>
     * <font style='background-color: #8828B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8828B5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8828B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8828B5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8828B5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8828B5'>&nbsp;@&nbsp;</font><font style='background-color: #8828B5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8828B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8828B5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_PURE_PURPLE = 0x8828b5ff;
    static { NAMED.put("deep_pure_purple", 0x8828b5ff); LIST.add(0x8828b5ff); NAMES.add("deep_pure_purple"); }

    /**
     * This color constant "true_pure_purple" has RGBA8888 code {@code A947D9FF}, red 0.6627451, green 0.2784314, blue 0.8509804, alpha 1.0, hue 0.7785388, saturation 0.572549, and lightness 0.5647059.
     * It has the hex RGBA8888 value {@code 0xa947d9ff}.
     * <pre>
     * <font style='background-color: #A947D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A947D9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A947D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A947D9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A947D9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A947D9'>&nbsp;@&nbsp;</font><font style='background-color: #A947D9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A947D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A947D9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_PURE_PURPLE = 0xa947d9ff;
    static { NAMED.put("true_pure_purple", 0xa947d9ff); LIST.add(0xa947d9ff); NAMES.add("true_pure_purple"); }

    /**
     * This color constant "bright_pure_purple" has RGBA8888 code {@code CA64FDFF}, red 0.7921569, green 0.39215687, blue 0.99215686, alpha 1.0, hue 0.7777778, saturation 0.6, and lightness 0.69215685.
     * It has the hex RGBA8888 value {@code 0xca64fdff}.
     * <pre>
     * <font style='background-color: #CA64FD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CA64FD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CA64FD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CA64FD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CA64FD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CA64FD'>&nbsp;@&nbsp;</font><font style='background-color: #CA64FD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CA64FD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CA64FD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_PURE_PURPLE = 0xca64fdff;
    static { NAMED.put("bright_pure_purple", 0xca64fdff); LIST.add(0xca64fdff); NAMES.add("bright_pure_purple"); }

    /**
     * This color constant "deep_magenta_purple" has RGBA8888 code {@code A739B1FF}, red 0.654902, green 0.22352941, blue 0.69411767, alpha 1.0, hue 0.8194445, saturation 0.47058827, and lightness 0.45882353.
     * It has the hex RGBA8888 value {@code 0xa739b1ff}.
     * <pre>
     * <font style='background-color: #A739B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A739B1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A739B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A739B1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A739B1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A739B1'>&nbsp;@&nbsp;</font><font style='background-color: #A739B1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A739B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A739B1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_MAGENTA_PURPLE = 0xa739b1ff;
    static { NAMED.put("deep_magenta_purple", 0xa739b1ff); LIST.add(0xa739b1ff); NAMES.add("deep_magenta_purple"); }

    /**
     * This color constant "true_magenta_purple" has RGBA8888 code {@code D15CDBFF}, red 0.81960785, green 0.36078432, blue 0.85882354, alpha 1.0, hue 0.82021, saturation 0.49803922, and lightness 0.609804.
     * It has the hex RGBA8888 value {@code 0xd15cdbff}.
     * <pre>
     * <font style='background-color: #D15CDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D15CDB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D15CDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D15CDB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D15CDB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D15CDB'>&nbsp;@&nbsp;</font><font style='background-color: #D15CDB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D15CDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D15CDB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_MAGENTA_PURPLE = 0xd15cdbff;
    static { NAMED.put("true_magenta_purple", 0xd15cdbff); LIST.add(0xd15cdbff); NAMES.add("true_magenta_purple"); }

    /**
     * This color constant "bright_magenta_purple" has RGBA8888 code {@code FA7EFFFF}, red 0.98039216, green 0.49411765, blue 1.0, alpha 1.0, hue 0.8268734, saturation 0.5058824, and lightness 0.7470588.
     * It has the hex RGBA8888 value {@code 0xfa7effff}.
     * <pre>
     * <font style='background-color: #FA7EFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FA7EFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FA7EFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FA7EFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FA7EFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FA7EFF'>&nbsp;@&nbsp;</font><font style='background-color: #FA7EFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FA7EFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FA7EFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_MAGENTA_PURPLE = 0xfa7effff;
    static { NAMED.put("bright_magenta_purple", 0xfa7effff); LIST.add(0xfa7effff); NAMES.add("bright_magenta_purple"); }

    /**
     * This color constant "deep_purple_magenta" has RGBA8888 code {@code A82C95FF}, red 0.65882355, green 0.17254902, blue 0.58431375, alpha 1.0, hue 0.858871, saturation 0.48627454, and lightness 0.41568628.
     * It has the hex RGBA8888 value {@code 0xa82c95ff}.
     * <pre>
     * <font style='background-color: #A82C95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A82C95; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A82C95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A82C95'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A82C95'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A82C95'>&nbsp;@&nbsp;</font><font style='background-color: #A82C95; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A82C95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A82C95; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_PURPLE_MAGENTA = 0xa82c95ff;
    static { NAMED.put("deep_purple_magenta", 0xa82c95ff); LIST.add(0xa82c95ff); NAMES.add("deep_purple_magenta"); }

    /**
     * This color constant "true_purple_magenta" has RGBA8888 code {@code C645B0FF}, red 0.7764706, green 0.27058825, blue 0.6901961, alpha 1.0, hue 0.8617571, saturation 0.5058824, and lightness 0.5235294.
     * It has the hex RGBA8888 value {@code 0xc645b0ff}.
     * <pre>
     * <font style='background-color: #C645B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C645B0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C645B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C645B0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C645B0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C645B0'>&nbsp;@&nbsp;</font><font style='background-color: #C645B0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C645B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C645B0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_PURPLE_MAGENTA = 0xc645b0ff;
    static { NAMED.put("true_purple_magenta", 0xc645b0ff); LIST.add(0xc645b0ff); NAMES.add("true_purple_magenta"); }

    /**
     * This color constant "bright_purple_magenta" has RGBA8888 code {@code E25CCAFF}, red 0.8862745, green 0.36078432, blue 0.7921569, alpha 1.0, hue 0.8631841, saturation 0.52549016, and lightness 0.62352943.
     * It has the hex RGBA8888 value {@code 0xe25ccaff}.
     * <pre>
     * <font style='background-color: #E25CCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E25CCA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E25CCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E25CCA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E25CCA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E25CCA'>&nbsp;@&nbsp;</font><font style='background-color: #E25CCA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E25CCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E25CCA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_PURPLE_MAGENTA = 0xe25ccaff;
    static { NAMED.put("bright_purple_magenta", 0xe25ccaff); LIST.add(0xe25ccaff); NAMES.add("bright_purple_magenta"); }

    /**
     * This color constant "deep_pure_magenta" has RGBA8888 code {@code B42583FF}, red 0.7058824, green 0.14509805, blue 0.5137255, alpha 1.0, hue 0.8904429, saturation 0.56078434, and lightness 0.4254902.
     * It has the hex RGBA8888 value {@code 0xb42583ff}.
     * <pre>
     * <font style='background-color: #B42583;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B42583; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B42583;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B42583'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B42583'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B42583'>&nbsp;@&nbsp;</font><font style='background-color: #B42583; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B42583;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B42583; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_PURE_MAGENTA = 0xb42583ff;
    static { NAMED.put("deep_pure_magenta", 0xb42583ff); LIST.add(0xb42583ff); NAMES.add("deep_pure_magenta"); }

    /**
     * This color constant "true_pure_magenta" has RGBA8888 code {@code D844A2FF}, red 0.84705883, green 0.26666668, blue 0.63529414, alpha 1.0, hue 0.8941441, saturation 0.5803921, and lightness 0.5568628.
     * It has the hex RGBA8888 value {@code 0xd844a2ff}.
     * <pre>
     * <font style='background-color: #D844A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D844A2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D844A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D844A2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D844A2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D844A2'>&nbsp;@&nbsp;</font><font style='background-color: #D844A2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D844A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D844A2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_PURE_MAGENTA = 0xd844a2ff;
    static { NAMED.put("true_pure_magenta", 0xd844a2ff); LIST.add(0xd844a2ff); NAMES.add("true_pure_magenta"); }

    /**
     * This color constant "bright_pure_magenta" has RGBA8888 code {@code FC5FC0FF}, red 0.9882353, green 0.37254903, blue 0.7529412, alpha 1.0, hue 0.8970276, saturation 0.6156863, and lightness 0.68039215.
     * It has the hex RGBA8888 value {@code 0xfc5fc0ff}.
     * <pre>
     * <font style='background-color: #FC5FC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FC5FC0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FC5FC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FC5FC0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FC5FC0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FC5FC0'>&nbsp;@&nbsp;</font><font style='background-color: #FC5FC0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FC5FC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FC5FC0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_PURE_MAGENTA = 0xfc5fc0ff;
    static { NAMED.put("bright_pure_magenta", 0xfc5fc0ff); LIST.add(0xfc5fc0ff); NAMES.add("bright_pure_magenta"); }

    /**
     * This color constant "deep_red_magenta" has RGBA8888 code {@code B23664FF}, red 0.69803923, green 0.21176471, blue 0.39215687, alpha 1.0, hue 0.93817204, saturation 0.48627454, and lightness 0.45490193.
     * It has the hex RGBA8888 value {@code 0xb23664ff}.
     * <pre>
     * <font style='background-color: #B23664;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B23664; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B23664;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B23664'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B23664'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B23664'>&nbsp;@&nbsp;</font><font style='background-color: #B23664; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B23664;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B23664; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_RED_MAGENTA = 0xb23664ff;
    static { NAMED.put("deep_red_magenta", 0xb23664ff); LIST.add(0xb23664ff); NAMES.add("deep_red_magenta"); }

    /**
     * This color constant "true_red_magenta" has RGBA8888 code {@code DB5684FF}, red 0.85882354, green 0.3372549, blue 0.5176471, alpha 1.0, hue 0.9423559, saturation 0.52156866, and lightness 0.5980392.
     * It has the hex RGBA8888 value {@code 0xdb5684ff}.
     * <pre>
     * <font style='background-color: #DB5684;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB5684; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB5684;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DB5684'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DB5684'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DB5684'>&nbsp;@&nbsp;</font><font style='background-color: #DB5684; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB5684;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB5684; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_RED_MAGENTA = 0xdb5684ff;
    static { NAMED.put("true_red_magenta", 0xdb5684ff); LIST.add(0xdb5684ff); NAMES.add("true_red_magenta"); }

    /**
     * This color constant "bright_red_magenta" has RGBA8888 code {@code FF75A4FF}, red 1.0, green 0.45882353, blue 0.6431373, alpha 1.0, hue 0.9432367, saturation 0.54117644, and lightness 0.7294118.
     * It has the hex RGBA8888 value {@code 0xff75a4ff}.
     * <pre>
     * <font style='background-color: #FF75A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF75A4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF75A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF75A4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF75A4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF75A4'>&nbsp;@&nbsp;</font><font style='background-color: #FF75A4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF75A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF75A4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_RED_MAGENTA = 0xff75a4ff;
    static { NAMED.put("bright_red_magenta", 0xff75a4ff); LIST.add(0xff75a4ff); NAMES.add("bright_red_magenta"); }

    /**
     * This color constant "deep_magenta_red" has RGBA8888 code {@code A52A40FF}, red 0.64705884, green 0.16470589, blue 0.2509804, alpha 1.0, hue 0.9701897, saturation 0.48235297, and lightness 0.40588236.
     * It has the hex RGBA8888 value {@code 0xa52a40ff}.
     * <pre>
     * <font style='background-color: #A52A40;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A52A40; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A52A40;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A52A40'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A52A40'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A52A40'>&nbsp;@&nbsp;</font><font style='background-color: #A52A40; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A52A40;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A52A40; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int DEEP_MAGENTA_RED = 0xa52a40ff;
    static { NAMED.put("deep_magenta_red", 0xa52a40ff); LIST.add(0xa52a40ff); NAMES.add("deep_magenta_red"); }

    /**
     * This color constant "true_magenta_red" has RGBA8888 code {@code C34255FF}, red 0.7647059, green 0.25882354, blue 0.33333334, alpha 1.0, hue 0.9754522, saturation 0.5058824, and lightness 0.5117647.
     * It has the hex RGBA8888 value {@code 0xc34255ff}.
     * <pre>
     * <font style='background-color: #C34255;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C34255; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C34255;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C34255'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C34255'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C34255'>&nbsp;@&nbsp;</font><font style='background-color: #C34255; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C34255;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C34255; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int TRUE_MAGENTA_RED = 0xc34255ff;
    static { NAMED.put("true_magenta_red", 0xc34255ff); LIST.add(0xc34255ff); NAMES.add("true_magenta_red"); }

    /**
     * This color constant "bright_magenta_red" has RGBA8888 code {@code DF5769FF}, red 0.8745098, green 0.34117648, blue 0.4117647, alpha 1.0, hue 0.97794116, saturation 0.5333333, and lightness 0.60784316.
     * It has the hex RGBA8888 value {@code 0xdf5769ff}.
     * <pre>
     * <font style='background-color: #DF5769;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DF5769; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DF5769;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DF5769'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DF5769'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DF5769'>&nbsp;@&nbsp;</font><font style='background-color: #DF5769; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DF5769;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DF5769; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BRIGHT_MAGENTA_RED = 0xdf5769ff;
    static { NAMED.put("bright_magenta_red", 0xdf5769ff); LIST.add(0xdf5769ff); NAMES.add("bright_magenta_red"); }

    /**
     * This color constant "bold_pure_red" has RGBA8888 code {@code FE0000FF}, red 0.99607843, green 0.0, blue 0.0, alpha 1.0, hue 0.0, saturation 0.99607843, and lightness 0.49803922.
     * It has the hex RGBA8888 value {@code 0xfe0000ff}.
     * <pre>
     * <font style='background-color: #FE0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FE0000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FE0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FE0000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FE0000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FE0000'>&nbsp;@&nbsp;</font><font style='background-color: #FE0000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FE0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FE0000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_PURE_RED = 0xfe0000ff;
    static { NAMED.put("bold_pure_red", 0xfe0000ff); LIST.add(0xfe0000ff); NAMES.add("bold_pure_red"); }

    /**
     * This color constant "bold_brown_red" has RGBA8888 code {@code DF4E31FF}, red 0.8745098, green 0.30588236, blue 0.19215687, alpha 1.0, hue 0.02777778, saturation 0.68235296, and lightness 0.5333333.
     * It has the hex RGBA8888 value {@code 0xdf4e31ff}.
     * <pre>
     * <font style='background-color: #DF4E31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DF4E31; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DF4E31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DF4E31'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DF4E31'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DF4E31'>&nbsp;@&nbsp;</font><font style='background-color: #DF4E31; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DF4E31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DF4E31; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_BROWN_RED = 0xdf4e31ff;
    static { NAMED.put("bold_brown_red", 0xdf4e31ff); LIST.add(0xdf4e31ff); NAMES.add("bold_brown_red"); }

    /**
     * This color constant "bold_red_brown" has RGBA8888 code {@code D56C4AFF}, red 0.8352941, green 0.42352942, blue 0.2901961, alpha 1.0, hue 0.040767383, saturation 0.54509807, and lightness 0.5627451.
     * It has the hex RGBA8888 value {@code 0xd56c4aff}.
     * <pre>
     * <font style='background-color: #D56C4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D56C4A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D56C4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D56C4A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D56C4A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D56C4A'>&nbsp;@&nbsp;</font><font style='background-color: #D56C4A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D56C4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D56C4A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_RED_BROWN = 0xd56c4aff;
    static { NAMED.put("bold_red_brown", 0xd56c4aff); LIST.add(0xd56c4aff); NAMES.add("bold_red_brown"); }

    /**
     * This color constant "bold_pure_brown" has RGBA8888 code {@code D77951FF}, red 0.84313726, green 0.4745098, blue 0.31764707, alpha 1.0, hue 0.049751244, saturation 0.52549016, and lightness 0.5803922.
     * It has the hex RGBA8888 value {@code 0xd77951ff}.
     * <pre>
     * <font style='background-color: #D77951;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D77951; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D77951;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D77951'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D77951'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D77951'>&nbsp;@&nbsp;</font><font style='background-color: #D77951; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D77951;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D77951; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_PURE_BROWN = 0xd77951ff;
    static { NAMED.put("bold_pure_brown", 0xd77951ff); LIST.add(0xd77951ff); NAMES.add("bold_pure_brown"); }

    /**
     * This color constant "bold_orange_brown" has RGBA8888 code {@code D28054FF}, red 0.8235294, green 0.5019608, blue 0.32941177, alpha 1.0, hue 0.058201063, saturation 0.49411765, and lightness 0.5764706.
     * It has the hex RGBA8888 value {@code 0xd28054ff}.
     * <pre>
     * <font style='background-color: #D28054;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D28054; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D28054;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D28054'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D28054'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D28054'>&nbsp;@&nbsp;</font><font style='background-color: #D28054; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D28054;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D28054; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_ORANGE_BROWN = 0xd28054ff;
    static { NAMED.put("bold_orange_brown", 0xd28054ff); LIST.add(0xd28054ff); NAMES.add("bold_orange_brown"); }

    /**
     * This color constant "bold_brown_orange" has RGBA8888 code {@code E68546FF}, red 0.9019608, green 0.52156866, blue 0.27450982, alpha 1.0, hue 0.065625004, saturation 0.62745094, and lightness 0.5882353.
     * It has the hex RGBA8888 value {@code 0xe68546ff}.
     * <pre>
     * <font style='background-color: #E68546;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E68546; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E68546;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E68546'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E68546'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E68546'>&nbsp;@&nbsp;</font><font style='background-color: #E68546; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E68546;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E68546; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_BROWN_ORANGE = 0xe68546ff;
    static { NAMED.put("bold_brown_orange", 0xe68546ff); LIST.add(0xe68546ff); NAMES.add("bold_brown_orange"); }

    /**
     * This color constant "bold_pure_orange" has RGBA8888 code {@code F48000FF}, red 0.95686275, green 0.5019608, blue 0.0, alpha 1.0, hue 0.0874317, saturation 0.95686275, and lightness 0.47843137.
     * It has the hex RGBA8888 value {@code 0xf48000ff}.
     * <pre>
     * <font style='background-color: #F48000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F48000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F48000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F48000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F48000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F48000'>&nbsp;@&nbsp;</font><font style='background-color: #F48000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F48000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F48000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_PURE_ORANGE = 0xf48000ff;
    static { NAMED.put("bold_pure_orange", 0xf48000ff); LIST.add(0xf48000ff); NAMES.add("bold_pure_orange"); }

    /**
     * This color constant "bold_saffron_orange" has RGBA8888 code {@code F09A20FF}, red 0.9411765, green 0.6039216, blue 0.1254902, alpha 1.0, hue 0.097756416, saturation 0.8156863, and lightness 0.53333336.
     * It has the hex RGBA8888 value {@code 0xf09a20ff}.
     * <pre>
     * <font style='background-color: #F09A20;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F09A20; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F09A20;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F09A20'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F09A20'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F09A20'>&nbsp;@&nbsp;</font><font style='background-color: #F09A20; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F09A20;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F09A20; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_SAFFRON_ORANGE = 0xf09a20ff;
    static { NAMED.put("bold_saffron_orange", 0xf09a20ff); LIST.add(0xf09a20ff); NAMES.add("bold_saffron_orange"); }

    /**
     * This color constant "bold_orange_saffron" has RGBA8888 code {@code F9B92EFF}, red 0.9764706, green 0.7254902, blue 0.18039216, alpha 1.0, hue 0.11412151, saturation 0.79607844, and lightness 0.5784313.
     * It has the hex RGBA8888 value {@code 0xf9b92eff}.
     * <pre>
     * <font style='background-color: #F9B92E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F9B92E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F9B92E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F9B92E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F9B92E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F9B92E'>&nbsp;@&nbsp;</font><font style='background-color: #F9B92E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F9B92E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F9B92E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_ORANGE_SAFFRON = 0xf9b92eff;
    static { NAMED.put("bold_orange_saffron", 0xf9b92eff); LIST.add(0xf9b92eff); NAMES.add("bold_orange_saffron"); }

    /**
     * This color constant "bold_pure_saffron" has RGBA8888 code {@code FECE00FF}, red 0.99607843, green 0.80784315, blue 0.0, alpha 1.0, hue 0.13517061, saturation 0.99607843, and lightness 0.49803922.
     * It has the hex RGBA8888 value {@code 0xfece00ff}.
     * <pre>
     * <font style='background-color: #FECE00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FECE00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FECE00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FECE00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FECE00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FECE00'>&nbsp;@&nbsp;</font><font style='background-color: #FECE00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FECE00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FECE00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_PURE_SAFFRON = 0xfece00ff;
    static { NAMED.put("bold_pure_saffron", 0xfece00ff); LIST.add(0xfece00ff); NAMES.add("bold_pure_saffron"); }

    /**
     * This color constant "bold_yellow_saffron" has RGBA8888 code {@code FFE335FF}, red 1.0, green 0.8901961, blue 0.20784314, alpha 1.0, hue 0.14356436, saturation 0.7921569, and lightness 0.60392153.
     * It has the hex RGBA8888 value {@code 0xffe335ff}.
     * <pre>
     * <font style='background-color: #FFE335;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFE335; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFE335;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFE335'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFE335'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFE335'>&nbsp;@&nbsp;</font><font style='background-color: #FFE335; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFE335;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFE335; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_YELLOW_SAFFRON = 0xffe335ff;
    static { NAMED.put("bold_yellow_saffron", 0xffe335ff); LIST.add(0xffe335ff); NAMES.add("bold_yellow_saffron"); }

    /**
     * This color constant "bold_saffron_yellow" has RGBA8888 code {@code FFEF30FF}, red 1.0, green 0.9372549, blue 0.1882353, alpha 1.0, hue 0.15378422, saturation 0.8117647, and lightness 0.59411764.
     * It has the hex RGBA8888 value {@code 0xffef30ff}.
     * <pre>
     * <font style='background-color: #FFEF30;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFEF30; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFEF30;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFEF30'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFEF30'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFEF30'>&nbsp;@&nbsp;</font><font style='background-color: #FFEF30; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFEF30;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFEF30; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_SAFFRON_YELLOW = 0xffef30ff;
    static { NAMED.put("bold_saffron_yellow", 0xffef30ff); LIST.add(0xffef30ff); NAMES.add("bold_saffron_yellow"); }

    /**
     * This color constant "bold_pure_yellow" has RGBA8888 code {@code FFFC00FF}, red 1.0, green 0.9882353, blue 0.0, alpha 1.0, hue 0.16470589, saturation 1.0, and lightness 0.5.
     * It has the hex RGBA8888 value {@code 0xfffc00ff}.
     * <pre>
     * <font style='background-color: #FFFC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFC00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFC00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFC00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFC00'>&nbsp;@&nbsp;</font><font style='background-color: #FFFC00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFC00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_PURE_YELLOW = 0xfffc00ff;
    static { NAMED.put("bold_pure_yellow", 0xfffc00ff); LIST.add(0xfffc00ff); NAMES.add("bold_pure_yellow"); }

    /**
     * This color constant "bold_lime_yellow" has RGBA8888 code {@code E6FD35FF}, red 0.9019608, green 0.99215686, blue 0.20784314, alpha 1.0, hue 0.18583335, saturation 0.78431374, and lightness 0.59999996.
     * It has the hex RGBA8888 value {@code 0xe6fd35ff}.
     * <pre>
     * <font style='background-color: #E6FD35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E6FD35; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E6FD35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E6FD35'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E6FD35'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E6FD35'>&nbsp;@&nbsp;</font><font style='background-color: #E6FD35; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E6FD35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E6FD35; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_LIME_YELLOW = 0xe6fd35ff;
    static { NAMED.put("bold_lime_yellow", 0xe6fd35ff); LIST.add(0xe6fd35ff); NAMES.add("bold_lime_yellow"); }

    /**
     * This color constant "bold_yellow_lime" has RGBA8888 code {@code D5FA2DFF}, red 0.8352941, green 0.98039216, blue 0.1764706, alpha 1.0, hue 0.19674797, saturation 0.8039216, and lightness 0.5784314.
     * It has the hex RGBA8888 value {@code 0xd5fa2dff}.
     * <pre>
     * <font style='background-color: #D5FA2D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5FA2D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5FA2D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D5FA2D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D5FA2D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D5FA2D'>&nbsp;@&nbsp;</font><font style='background-color: #D5FA2D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5FA2D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5FA2D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_YELLOW_LIME = 0xd5fa2dff;
    static { NAMED.put("bold_yellow_lime", 0xd5fa2dff); LIST.add(0xd5fa2dff); NAMES.add("bold_yellow_lime"); }

    /**
     * This color constant "bold_pure_lime" has RGBA8888 code {@code BCFC00FF}, red 0.7372549, green 0.9882353, blue 0.0, alpha 1.0, hue 0.20899472, saturation 0.9882353, and lightness 0.49411765.
     * It has the hex RGBA8888 value {@code 0xbcfc00ff}.
     * <pre>
     * <font style='background-color: #BCFC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BCFC00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BCFC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BCFC00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BCFC00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BCFC00'>&nbsp;@&nbsp;</font><font style='background-color: #BCFC00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BCFC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BCFC00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_PURE_LIME = 0xbcfc00ff;
    static { NAMED.put("bold_pure_lime", 0xbcfc00ff); LIST.add(0xbcfc00ff); NAMES.add("bold_pure_lime"); }

    /**
     * This color constant "bold_green_lime" has RGBA8888 code {@code A1FD33FF}, red 0.6313726, green 0.99215686, blue 0.2, alpha 1.0, hue 0.24257426, saturation 0.7921569, and lightness 0.5960784.
     * It has the hex RGBA8888 value {@code 0xa1fd33ff}.
     * <pre>
     * <font style='background-color: #A1FD33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A1FD33; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A1FD33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A1FD33'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A1FD33'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A1FD33'>&nbsp;@&nbsp;</font><font style='background-color: #A1FD33; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A1FD33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A1FD33; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_GREEN_LIME = 0xa1fd33ff;
    static { NAMED.put("bold_green_lime", 0xa1fd33ff); LIST.add(0xa1fd33ff); NAMES.add("bold_green_lime"); }

    /**
     * This color constant "bold_lime_green" has RGBA8888 code {@code 82FB2AFF}, red 0.50980395, green 0.9843137, blue 0.16470589, alpha 1.0, hue 0.2631579, saturation 0.81960785, and lightness 0.57450986.
     * It has the hex RGBA8888 value {@code 0x82fb2aff}.
     * <pre>
     * <font style='background-color: #82FB2A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #82FB2A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #82FB2A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #82FB2A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #82FB2A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #82FB2A'>&nbsp;@&nbsp;</font><font style='background-color: #82FB2A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #82FB2A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #82FB2A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_LIME_GREEN = 0x82fb2aff;
    static { NAMED.put("bold_lime_green", 0x82fb2aff); LIST.add(0x82fb2aff); NAMES.add("bold_lime_green"); }

    /**
     * This color constant "bold_pure_green" has RGBA8888 code {@code 37FF00FF}, red 0.21568628, green 1.0, blue 0.0, alpha 1.0, hue 0.29738563, saturation 1.0, and lightness 0.5.
     * It has the hex RGBA8888 value {@code 0x37ff00ff}.
     * <pre>
     * <font style='background-color: #37FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #37FF00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #37FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #37FF00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #37FF00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #37FF00'>&nbsp;@&nbsp;</font><font style='background-color: #37FF00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #37FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #37FF00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_PURE_GREEN = 0x37ff00ff;
    static { NAMED.put("bold_pure_green", 0x37ff00ff); LIST.add(0x37ff00ff); NAMES.add("bold_pure_green"); }

    /**
     * This color constant "bold_cyan_green" has RGBA8888 code {@code 27FA9CFF}, red 0.15294118, green 0.98039216, blue 0.6117647, alpha 1.0, hue 0.4257504, saturation 0.827451, and lightness 0.56666666.
     * It has the hex RGBA8888 value {@code 0x27fa9cff}.
     * <pre>
     * <font style='background-color: #27FA9C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #27FA9C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #27FA9C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #27FA9C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #27FA9C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #27FA9C'>&nbsp;@&nbsp;</font><font style='background-color: #27FA9C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #27FA9C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #27FA9C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_CYAN_GREEN = 0x27fa9cff;
    static { NAMED.put("bold_cyan_green", 0x27fa9cff); LIST.add(0x27fa9cff); NAMES.add("bold_cyan_green"); }

    /**
     * This color constant "bold_green_cyan" has RGBA8888 code {@code 29FBD4FF}, red 0.16078432, green 0.9843137, blue 0.83137256, alpha 1.0, hue 0.46904764, saturation 0.8235294, and lightness 0.572549.
     * It has the hex RGBA8888 value {@code 0x29fbd4ff}.
     * <pre>
     * <font style='background-color: #29FBD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #29FBD4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #29FBD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #29FBD4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #29FBD4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #29FBD4'>&nbsp;@&nbsp;</font><font style='background-color: #29FBD4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #29FBD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #29FBD4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_GREEN_CYAN = 0x29fbd4ff;
    static { NAMED.put("bold_green_cyan", 0x29fbd4ff); LIST.add(0x29fbd4ff); NAMES.add("bold_green_cyan"); }

    /**
     * This color constant "bold_pure_cyan" has RGBA8888 code {@code 00FBFAFF}, red 0.0, green 0.9843137, blue 0.98039216, alpha 1.0, hue 0.499336, saturation 0.9843137, and lightness 0.49215686.
     * It has the hex RGBA8888 value {@code 0x00fbfaff}.
     * <pre>
     * <font style='background-color: #00FBFA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FBFA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FBFA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FBFA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FBFA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FBFA'>&nbsp;@&nbsp;</font><font style='background-color: #00FBFA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FBFA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FBFA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_PURE_CYAN = 0x00fbfaff;
    static { NAMED.put("bold_pure_cyan", 0x00fbfaff); LIST.add(0x00fbfaff); NAMES.add("bold_pure_cyan"); }

    /**
     * This color constant "bold_blue_cyan" has RGBA8888 code {@code 1FCFFBFF}, red 0.12156863, green 0.8117647, blue 0.9843137, alpha 1.0, hue 0.53333336, saturation 0.8627451, and lightness 0.55294114.
     * It has the hex RGBA8888 value {@code 0x1fcffbff}.
     * <pre>
     * <font style='background-color: #1FCFFB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1FCFFB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1FCFFB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1FCFFB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1FCFFB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1FCFFB'>&nbsp;@&nbsp;</font><font style='background-color: #1FCFFB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1FCFFB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1FCFFB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_BLUE_CYAN = 0x1fcffbff;
    static { NAMED.put("bold_blue_cyan", 0x1fcffbff); LIST.add(0x1fcffbff); NAMES.add("bold_blue_cyan"); }

    /**
     * This color constant "bold_cyan_blue" has RGBA8888 code {@code 129FF1FF}, red 0.07058824, green 0.62352943, blue 0.94509804, alpha 1.0, hue 0.5612855, saturation 0.8745098, and lightness 0.50784314.
     * It has the hex RGBA8888 value {@code 0x129ff1ff}.
     * <pre>
     * <font style='background-color: #129FF1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #129FF1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #129FF1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #129FF1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #129FF1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #129FF1'>&nbsp;@&nbsp;</font><font style='background-color: #129FF1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #129FF1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #129FF1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_CYAN_BLUE = 0x129ff1ff;
    static { NAMED.put("bold_cyan_blue", 0x129ff1ff); LIST.add(0x129ff1ff); NAMES.add("bold_cyan_blue"); }

    /**
     * This color constant "bold_pure_blue" has RGBA8888 code {@code 0C00FFFF}, red 0.047058824, green 0.0, blue 1.0, alpha 1.0, hue 0.6745098, saturation 1.0, and lightness 0.5.
     * It has the hex RGBA8888 value {@code 0x0c00ffff}.
     * <pre>
     * <font style='background-color: #0C00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0C00FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0C00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0C00FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0C00FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0C00FF'>&nbsp;@&nbsp;</font><font style='background-color: #0C00FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0C00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0C00FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_PURE_BLUE = 0x0c00ffff;
    static { NAMED.put("bold_pure_blue", 0x0c00ffff); LIST.add(0x0c00ffff); NAMES.add("bold_pure_blue"); }

    /**
     * This color constant "bold_violet_blue" has RGBA8888 code {@code 3B1FF0FF}, red 0.23137255, green 0.12156863, blue 0.9411765, alpha 1.0, hue 0.68899524, saturation 0.81960785, and lightness 0.53137255.
     * It has the hex RGBA8888 value {@code 0x3b1ff0ff}.
     * <pre>
     * <font style='background-color: #3B1FF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B1FF0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B1FF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B1FF0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B1FF0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B1FF0'>&nbsp;@&nbsp;</font><font style='background-color: #3B1FF0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B1FF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B1FF0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_VIOLET_BLUE = 0x3b1ff0ff;
    static { NAMED.put("bold_violet_blue", 0x3b1ff0ff); LIST.add(0x3b1ff0ff); NAMES.add("bold_violet_blue"); }

    /**
     * This color constant "bold_blue_violet" has RGBA8888 code {@code 5D1CEFFF}, red 0.3647059, green 0.10980392, blue 0.9372549, alpha 1.0, hue 0.7180095, saturation 0.827451, and lightness 0.5235294.
     * It has the hex RGBA8888 value {@code 0x5d1cefff}.
     * <pre>
     * <font style='background-color: #5D1CEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5D1CEF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5D1CEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5D1CEF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5D1CEF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5D1CEF'>&nbsp;@&nbsp;</font><font style='background-color: #5D1CEF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5D1CEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5D1CEF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_BLUE_VIOLET = 0x5d1cefff;
    static { NAMED.put("bold_blue_violet", 0x5d1cefff); LIST.add(0x5d1cefff); NAMES.add("bold_blue_violet"); }

    /**
     * This color constant "bold_pure_violet" has RGBA8888 code {@code 7E00FFFF}, red 0.49411765, green 0.0, blue 1.0, alpha 1.0, hue 0.7490196, saturation 1.0, and lightness 0.5.
     * It has the hex RGBA8888 value {@code 0x7e00ffff}.
     * <pre>
     * <font style='background-color: #7E00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E00FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7E00FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7E00FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7E00FF'>&nbsp;@&nbsp;</font><font style='background-color: #7E00FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E00FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_PURE_VIOLET = 0x7e00ffff;
    static { NAMED.put("bold_pure_violet", 0x7e00ffff); LIST.add(0x7e00ffff); NAMES.add("bold_pure_violet"); }

    /**
     * This color constant "bold_purple_violet" has RGBA8888 code {@code 8D1EEFFF}, red 0.5529412, green 0.11764706, blue 0.9372549, alpha 1.0, hue 0.75518346, saturation 0.81960785, and lightness 0.52745104.
     * It has the hex RGBA8888 value {@code 0x8d1eefff}.
     * <pre>
     * <font style='background-color: #8D1EEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8D1EEF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8D1EEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8D1EEF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8D1EEF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8D1EEF'>&nbsp;@&nbsp;</font><font style='background-color: #8D1EEF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8D1EEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8D1EEF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_PURPLE_VIOLET = 0x8d1eefff;
    static { NAMED.put("bold_purple_violet", 0x8d1eefff); LIST.add(0x8d1eefff); NAMES.add("bold_purple_violet"); }

    /**
     * This color constant "bold_violet_purple" has RGBA8888 code {@code A120EEFF}, red 0.6313726, green 0.1254902, blue 0.93333334, alpha 1.0, hue 0.7710356, saturation 0.80784315, and lightness 0.5294118.
     * It has the hex RGBA8888 value {@code 0xa120eeff}.
     * <pre>
     * <font style='background-color: #A120EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A120EE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A120EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A120EE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A120EE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A120EE'>&nbsp;@&nbsp;</font><font style='background-color: #A120EE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A120EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A120EE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_VIOLET_PURPLE = 0xa120eeff;
    static { NAMED.put("bold_violet_purple", 0xa120eeff); LIST.add(0xa120eeff); NAMES.add("bold_violet_purple"); }

    /**
     * This color constant "bold_pure_purple" has RGBA8888 code {@code C100FFFF}, red 0.75686276, green 0.0, blue 1.0, alpha 1.0, hue 0.7928105, saturation 1.0, and lightness 0.5.
     * It has the hex RGBA8888 value {@code 0xc100ffff}.
     * <pre>
     * <font style='background-color: #C100FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C100FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C100FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C100FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C100FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C100FF'>&nbsp;@&nbsp;</font><font style='background-color: #C100FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C100FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C100FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_PURE_PURPLE = 0xc100ffff;
    static { NAMED.put("bold_pure_purple", 0xc100ffff); LIST.add(0xc100ffff); NAMES.add("bold_pure_purple"); }

    /**
     * This color constant "bold_magenta_purple" has RGBA8888 code {@code E42BFAFF}, red 0.89411765, green 0.16862746, blue 0.98039216, alpha 1.0, hue 0.81562, saturation 0.8117647, and lightness 0.5745098.
     * It has the hex RGBA8888 value {@code 0xe42bfaff}.
     * <pre>
     * <font style='background-color: #E42BFA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E42BFA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E42BFA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E42BFA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E42BFA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E42BFA'>&nbsp;@&nbsp;</font><font style='background-color: #E42BFA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E42BFA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E42BFA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_MAGENTA_PURPLE = 0xe42bfaff;
    static { NAMED.put("bold_magenta_purple", 0xe42bfaff); LIST.add(0xe42bfaff); NAMES.add("bold_magenta_purple"); }

    /**
     * This color constant "bold_purple_magenta" has RGBA8888 code {@code FC27E0FF}, red 0.9882353, green 0.15294118, blue 0.8784314, alpha 1.0, hue 0.85524255, saturation 0.8352941, and lightness 0.57058823.
     * It has the hex RGBA8888 value {@code 0xfc27e0ff}.
     * <pre>
     * <font style='background-color: #FC27E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FC27E0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FC27E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FC27E0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FC27E0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FC27E0'>&nbsp;@&nbsp;</font><font style='background-color: #FC27E0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FC27E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FC27E0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_PURPLE_MAGENTA = 0xfc27e0ff;
    static { NAMED.put("bold_purple_magenta", 0xfc27e0ff); LIST.add(0xfc27e0ff); NAMES.add("bold_purple_magenta"); }

    /**
     * This color constant "bold_pure_magenta" has RGBA8888 code {@code FF00BDFF}, red 1.0, green 0.0, blue 0.7411765, alpha 1.0, hue 0.87647057, saturation 1.0, and lightness 0.5.
     * It has the hex RGBA8888 value {@code 0xff00bdff}.
     * <pre>
     * <font style='background-color: #FF00BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF00BD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF00BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF00BD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF00BD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF00BD'>&nbsp;@&nbsp;</font><font style='background-color: #FF00BD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF00BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF00BD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_PURE_MAGENTA = 0xff00bdff;
    static { NAMED.put("bold_pure_magenta", 0xff00bdff); LIST.add(0xff00bdff); NAMES.add("bold_pure_magenta"); }

    /**
     * This color constant "bold_red_magenta" has RGBA8888 code {@code F82785FF}, red 0.972549, green 0.15294118, blue 0.52156866, alpha 1.0, hue 0.9250399, saturation 0.81960785, and lightness 0.5627451.
     * It has the hex RGBA8888 value {@code 0xf82785ff}.
     * <pre>
     * <font style='background-color: #F82785;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F82785; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F82785;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F82785'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F82785'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F82785'>&nbsp;@&nbsp;</font><font style='background-color: #F82785; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F82785;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F82785; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_RED_MAGENTA = 0xf82785ff;
    static { NAMED.put("bold_red_magenta", 0xf82785ff); LIST.add(0xf82785ff); NAMES.add("bold_red_magenta"); }

    /**
     * This color constant "bold_magenta_red" has RGBA8888 code {@code F82657FF}, red 0.972549, green 0.14901961, blue 0.34117648, alpha 1.0, hue 0.9611111, saturation 0.8235294, and lightness 0.5607843.
     * It has the hex RGBA8888 value {@code 0xf82657ff}.
     * <pre>
     * <font style='background-color: #F82657;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F82657; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F82657;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F82657'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F82657'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F82657'>&nbsp;@&nbsp;</font><font style='background-color: #F82657; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F82657;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F82657; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final int BOLD_MAGENTA_RED = 0xf82657ff;
    static { NAMED.put("bold_magenta_red", 0xf82657ff); LIST.add(0xf82657ff); NAMES.add("bold_magenta_red"); }

    /**
     * All names for colors in this palette, in alphabetical order. You can fetch the corresponding packed float color
     * by looking up a name in {@link #NAMED}.
     */
    public static final Array<String> ALPHABETICAL_NAMES;
    static {
        ALPHABETICAL_NAMES = NAMED.keys().toArray();
        ALPHABETICAL_NAMES.sort();
    }

    /**
     * Appends standard RGBA Color instances to the map in {@link Colors}, using the names in {@link #NAMES} (which
     * are "lower_case" instead of "ALL_UPPER_CASE"). This doesn't need any changes to be made to Colors in order for
     * it to be compatible; just remember that the colors originally in Colors use "UPPER_CASE" and these use
     * "lower_case".
     */
    public static void appendToKnownColors(){
        for(ObjectIntMap.Entry<String> ent : NAMED) {
            Color editing = new Color();
            Color.rgba8888ToColor(editing, ent.value);
            Colors.put(ent.key, editing);
        }
    }
}
