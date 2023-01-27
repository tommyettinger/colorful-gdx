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

package com.github.tommyettinger.colorful.hsluv;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.ObjectFloatMap;

import java.util.Comparator;

import static com.github.tommyettinger.colorful.hsluv.ColorTools.*;

/**
 * A palette of predefined colors as packed HSLuv floats, the kind {@link ColorTools} works with.
 * You can access colors by their constant name, such as {@code OCEAN_BLUE}, by the {@link #NAMED} map using
 * {@code NAMED.get("Ocean Blue", 0f)}, or by index in the FloatArray called {@link #LIST}. Note that to access a float
 * color from NAMED, you need to give a default value if the name is not found; {@code 256f} is a good default because
 * it will not occur in a valid HSLuv color. You can access the names in a specific order with {@link #NAMES} (which is
 * alphabetical), {@link #NAMES_BY_HUE} (which is sorted by the hue of the matching color, from red to yellow to blue
 * (with gray around here) to purple to red again), or {@link #NAMES_BY_LIGHTNESS} (which is sorted by the intensity of
 * the matching color, from darkest to lightest). Having a name lets you look up the matching color in {@link #NAMED}.
 * <br>
 * Created by Tommy Ettinger on 10/13/2020.
 */
public class UbePalette {
    public static final ObjectFloatMap<String> NAMED = new ObjectFloatMap<String>(256);
    public static final FloatArray LIST = new FloatArray(256);

    /**
     * This color constant "transparent" has RGBA8888 code {@code 00000000}, H 0.0, S 0.0, L 0.0, alpha 0.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code 0x0.0p0F}.
     * <pre>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #000000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float UBE_TRANSPARENT = 0x0.0p0F;
    static { NAMED.put("transparent", 0x0.0p0F); LIST.add(0x0.0p0F); }

    /**
     * This color constant "pure black" has RGBA8888 code {@code 000000FF}, H 0.0, S 0.0, L 0.0, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.0p125F}.
     * <pre>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #000000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_BLACK = -0x1.0p125F;
    static { NAMED.put("pure black", -0x1.0p125F); LIST.add(-0x1.0p125F); }

    /**
     * This color constant "almost black" has RGBA8888 code {@code 121212FF}, H 0.16078432, S 0.0, L 0.05490196, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.1c0052p125F}.
     * <pre>
     * <font style='background-color: #121212;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #121212; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #121212;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #121212'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #121212'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #121212'>&nbsp;@&nbsp;</font><font style='background-color: #121212; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #121212;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #121212; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ALMOST_BLACK = -0x1.1c0052p125F;
    static { NAMED.put("almost black", -0x1.1c0052p125F); LIST.add(-0x1.1c0052p125F); }

    /**
     * This color constant "lead black" has RGBA8888 code {@code 242424FF}, H 0.16470589, S 0.0, L 0.13333334, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.440054p125F}.
     * <pre>
     * <font style='background-color: #242424;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #242424; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #242424;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #242424'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #242424'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #242424'>&nbsp;@&nbsp;</font><font style='background-color: #242424; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #242424;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #242424; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_BLACK = -0x1.440054p125F;
    static { NAMED.put("lead black", -0x1.440054p125F); LIST.add(-0x1.440054p125F); }

    /**
     * This color constant "black lead" has RGBA8888 code {@code 363636FF}, H 0.16078432, S 0.0, L 0.20784314, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.6a0052p125F}.
     * <pre>
     * <font style='background-color: #363636;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #363636; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #363636;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #363636'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #363636'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #363636'>&nbsp;@&nbsp;</font><font style='background-color: #363636; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #363636;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #363636; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_LEAD = -0x1.6a0052p125F;
    static { NAMED.put("black lead", -0x1.6a0052p125F); LIST.add(-0x1.6a0052p125F); }

    /**
     * This color constant "pure lead" has RGBA8888 code {@code 484848FF}, H 0.16470589, S 0.0, L 0.28235295, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.900054p125F}.
     * <pre>
     * <font style='background-color: #484848;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #484848; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #484848;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #484848'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #484848'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #484848'>&nbsp;@&nbsp;</font><font style='background-color: #484848; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #484848;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #484848; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_LEAD = -0x1.900054p125F;
    static { NAMED.put("pure lead", -0x1.900054p125F); LIST.add(-0x1.900054p125F); }

    /**
     * This color constant "gray lead" has RGBA8888 code {@code 5B5B5BFF}, H 0.16078432, S 0.0, L 0.35686275, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.b60052p125F}.
     * <pre>
     * <font style='background-color: #5B5B5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B5B5B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B5B5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5B5B5B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5B5B5B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5B5B5B'>&nbsp;@&nbsp;</font><font style='background-color: #5B5B5B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B5B5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B5B5B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_LEAD = -0x1.b60052p125F;
    static { NAMED.put("gray lead", -0x1.b60052p125F); LIST.add(-0x1.b60052p125F); }

    /**
     * This color constant "lead gray" has RGBA8888 code {@code 6D6D6DFF}, H 0.16470589, S 0.0, L 0.42745098, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.da0054p125F}.
     * <pre>
     * <font style='background-color: #6D6D6D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D6D6D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D6D6D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6D6D6D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6D6D6D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6D6D6D'>&nbsp;@&nbsp;</font><font style='background-color: #6D6D6D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D6D6D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D6D6D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_GRAY = -0x1.da0054p125F;
    static { NAMED.put("lead gray", -0x1.da0054p125F); LIST.add(-0x1.da0054p125F); }

    /**
     * This color constant "pure gray" has RGBA8888 code {@code 7F7F7FFF}, H 0.16078432, S 0.0, L 0.49803922, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.fe0052p125F}.
     * <pre>
     * <font style='background-color: #7F7F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F7F7F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F7F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7F7F7F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7F7F7F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7F7F7F'>&nbsp;@&nbsp;</font><font style='background-color: #7F7F7F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F7F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F7F7F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_GRAY = -0x1.fe0052p125F;
    static { NAMED.put("pure gray", -0x1.fe0052p125F); LIST.add(-0x1.fe0052p125F); }

    /**
     * This color constant "silver gray" has RGBA8888 code {@code 919191FF}, H 0.16078432, S 0.0, L 0.5647059, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.200052p126F}.
     * <pre>
     * <font style='background-color: #919191;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #919191; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #919191;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #919191'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #919191'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #919191'>&nbsp;@&nbsp;</font><font style='background-color: #919191; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #919191;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #919191; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_GRAY = -0x1.200052p126F;
    static { NAMED.put("silver gray", -0x1.200052p126F); LIST.add(-0x1.200052p126F); }

    /**
     * This color constant "gray silver" has RGBA8888 code {@code A3A3A3FF}, H 0.16078432, S 0.0, L 0.63529414, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.440052p126F}.
     * <pre>
     * <font style='background-color: #A3A3A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A3A3A3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A3A3A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A3A3A3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A3A3A3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A3A3A3'>&nbsp;@&nbsp;</font><font style='background-color: #A3A3A3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A3A3A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A3A3A3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_SILVER = -0x1.440052p126F;
    static { NAMED.put("gray silver", -0x1.440052p126F); LIST.add(-0x1.440052p126F); }

    /**
     * This color constant "pure silver" has RGBA8888 code {@code B6B6B6FF}, H 0.16078432, S 0.0, L 0.70980394, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.6a0052p126F}.
     * <pre>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B6B6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #B6B6B6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B6B6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_SILVER = -0x1.6a0052p126F;
    static { NAMED.put("pure silver", -0x1.6a0052p126F); LIST.add(-0x1.6a0052p126F); }

    /**
     * This color constant "white silver" has RGBA8888 code {@code C8C8C8FF}, H 0.16078432, S 0.0, L 0.78039217, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.8e0052p126F}.
     * <pre>
     * <font style='background-color: #C8C8C8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8C8C8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8C8C8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C8C8C8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C8C8C8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C8C8C8'>&nbsp;@&nbsp;</font><font style='background-color: #C8C8C8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8C8C8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8C8C8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_SILVER = -0x1.8e0052p126F;
    static { NAMED.put("white silver", -0x1.8e0052p126F); LIST.add(-0x1.8e0052p126F); }

    /**
     * This color constant "silver white" has RGBA8888 code {@code DADADAFF}, H 0.16470589, S 0.0, L 0.85490197, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.b40054p126F}.
     * <pre>
     * <font style='background-color: #DADADA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DADADA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DADADA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DADADA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DADADA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DADADA'>&nbsp;@&nbsp;</font><font style='background-color: #DADADA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DADADA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DADADA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_WHITE = -0x1.b40054p126F;
    static { NAMED.put("silver white", -0x1.b40054p126F); LIST.add(-0x1.b40054p126F); }

    /**
     * This color constant "almost white" has RGBA8888 code {@code ECECECFF}, H 0.16470589, S 0.0, L 0.9254902, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.d80054p126F}.
     * <pre>
     * <font style='background-color: #ECECEC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ECECEC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ECECEC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ECECEC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ECECEC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ECECEC'>&nbsp;@&nbsp;</font><font style='background-color: #ECECEC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ECECEC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ECECEC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ALMOST_WHITE = -0x1.d80054p126F;
    static { NAMED.put("almost white", -0x1.d80054p126F); LIST.add(-0x1.d80054p126F); }

    /**
     * This color constant "pure white" has RGBA8888 code {@code FFFFFFFF}, H 0.16078432, S 0.0, L 1.0, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.fe0052p126F}.
     * <pre>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #FFFFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_WHITE = -0x1.fe0052p126F;
    static { NAMED.put("pure white", -0x1.fe0052p126F); LIST.add(-0x1.fe0052p126F); }

    /**
     * This color constant "black red" has RGBA8888 code {@code 7F3333FF}, H 0.03137255, S 0.5529412, L 0.29411766, alpha 1.0, and chroma 0.5830242.
     * It can be represented as a packed float with the constant {@code -0x1.971a1p125F}.
     * <pre>
     * <font style='background-color: #7F3333;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F3333; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F3333;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7F3333'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7F3333'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7F3333'>&nbsp;@&nbsp;</font><font style='background-color: #7F3333; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F3333;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F3333; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_RED = -0x1.971a1p125F;
    static { NAMED.put("black red", -0x1.971a1p125F); LIST.add(-0x1.971a1p125F); }

    /**
     * This color constant "lead red" has RGBA8888 code {@code A64343FF}, H 0.03137255, S 0.5686275, L 0.3882353, alpha 1.0, and chroma 0.7888024.
     * It can be represented as a packed float with the constant {@code -0x1.c7221p125F}.
     * <pre>
     * <font style='background-color: #A64343;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A64343; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A64343;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A64343'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A64343'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A64343'>&nbsp;@&nbsp;</font><font style='background-color: #A64343; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A64343;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A64343; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_RED = -0x1.c7221p125F;
    static { NAMED.put("lead red", -0x1.c7221p125F); LIST.add(-0x1.c7221p125F); }

    /**
     * This color constant "gray red" has RGBA8888 code {@code C06666FF}, H 0.03137255, S 0.3882353, L 0.5019608, alpha 1.0, and chroma 0.67152953.
     * It can be represented as a packed float with the constant {@code -0x1.00c61p126F}.
     * <pre>
     * <font style='background-color: #C06666;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C06666; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C06666;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C06666'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C06666'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C06666'>&nbsp;@&nbsp;</font><font style='background-color: #C06666; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C06666;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C06666; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_RED = -0x1.00c61p126F;
    static { NAMED.put("gray red", -0x1.00c61p126F); LIST.add(-0x1.00c61p126F); }

    /**
     * This color constant "silver red" has RGBA8888 code {@code D49696FF}, H 0.03137255, S 0.3764706, L 0.64705884, alpha 1.0, and chroma 0.35317868.
     * It can be represented as a packed float with the constant {@code -0x1.4ac01p126F}.
     * <pre>
     * <font style='background-color: #D49696;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D49696; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D49696;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D49696'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D49696'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D49696'>&nbsp;@&nbsp;</font><font style='background-color: #D49696; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D49696;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D49696; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_RED = -0x1.4ac01p126F;
    static { NAMED.put("silver red", -0x1.4ac01p126F); LIST.add(-0x1.4ac01p126F); }

    /**
     * This color constant "white red" has RGBA8888 code {@code EACCCCFF}, H 0.03137255, S 0.41960785, L 0.8235294, alpha 1.0, and chroma 0.15677978.
     * It can be represented as a packed float with the constant {@code -0x1.a4d61p126F}.
     * <pre>
     * <font style='background-color: #EACCCC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EACCCC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EACCCC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EACCCC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EACCCC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EACCCC'>&nbsp;@&nbsp;</font><font style='background-color: #EACCCC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EACCCC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EACCCC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_RED = -0x1.a4d61p126F;
    static { NAMED.put("white red", -0x1.a4d61p126F); LIST.add(-0x1.a4d61p126F); }

    /**
     * This color constant "black brown" has RGBA8888 code {@code 8D593FFF}, H 0.08627451, S 0.6392157, L 0.39607844, alpha 1.0, and chroma 0.4898464.
     * It can be represented as a packed float with the constant {@code -0x1.cb462cp125F}.
     * <pre>
     * <font style='background-color: #8D593F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8D593F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8D593F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8D593F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8D593F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8D593F'>&nbsp;@&nbsp;</font><font style='background-color: #8D593F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8D593F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8D593F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_BROWN = -0x1.cb462cp125F;
    static { NAMED.put("black brown", -0x1.cb462cp125F); LIST.add(-0x1.cb462cp125F); }

    /**
     * This color constant "lead brown" has RGBA8888 code {@code B37557FF}, H 0.08627451, S 0.5882353, L 0.5137255, alpha 1.0, and chroma 0.57800364.
     * It can be represented as a packed float with the constant {@code -0x1.072c2cp126F}.
     * <pre>
     * <font style='background-color: #B37557;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B37557; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B37557;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B37557'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B37557'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B37557'>&nbsp;@&nbsp;</font><font style='background-color: #B37557; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B37557;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B37557; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_BROWN = -0x1.072c2cp126F;
    static { NAMED.put("lead brown", -0x1.072c2cp126F); LIST.add(-0x1.072c2cp126F); }

    /**
     * This color constant "gray brown" has RGBA8888 code {@code C3947CFF}, H 0.09803922, S 0.39607844, L 0.6156863, alpha 1.0, and chroma 0.4216917.
     * It can be represented as a packed float with the constant {@code -0x1.3aca32p126F}.
     * <pre>
     * <font style='background-color: #C3947C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3947C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3947C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C3947C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C3947C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C3947C'>&nbsp;@&nbsp;</font><font style='background-color: #C3947C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3947C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3947C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_BROWN = -0x1.3aca32p126F;
    static { NAMED.put("gray brown", -0x1.3aca32p126F); LIST.add(-0x1.3aca32p126F); }

    /**
     * This color constant "silver brown" has RGBA8888 code {@code D5B3A2FF}, H 0.101960786, S 0.27058825, L 0.7254902, alpha 1.0, and chroma 0.234664.
     * It can be represented as a packed float with the constant {@code -0x1.728a34p126F}.
     * <pre>
     * <font style='background-color: #D5B3A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5B3A2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5B3A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D5B3A2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D5B3A2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D5B3A2'>&nbsp;@&nbsp;</font><font style='background-color: #D5B3A2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5B3A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5B3A2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_BROWN = -0x1.728a34p126F;
    static { NAMED.put("silver brown", -0x1.728a34p126F); LIST.add(-0x1.728a34p126F); }

    /**
     * This color constant "white brown" has RGBA8888 code {@code E9D8D0FF}, H 0.105882354, S 0.29803923, L 0.85882354, alpha 1.0, and chroma 0.11069048.
     * It can be represented as a packed float with the constant {@code -0x1.b69836p126F}.
     * <pre>
     * <font style='background-color: #E9D8D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9D8D0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9D8D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E9D8D0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E9D8D0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E9D8D0'>&nbsp;@&nbsp;</font><font style='background-color: #E9D8D0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9D8D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9D8D0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_BROWN = -0x1.b69836p126F;
    static { NAMED.put("white brown", -0x1.b69836p126F); LIST.add(-0x1.b69836p126F); }

    /**
     * This color constant "black orange" has RGBA8888 code {@code 876039FF}, H 0.12156863, S 0.7294118, L 0.40784314, alpha 1.0, and chroma 0.45710105.
     * It can be represented as a packed float with the constant {@code -0x1.d1743ep125F}.
     * <pre>
     * <font style='background-color: #876039;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #876039; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #876039;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #876039'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #876039'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #876039'>&nbsp;@&nbsp;</font><font style='background-color: #876039; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #876039;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #876039; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_ORANGE = -0x1.d1743ep125F;
    static { NAMED.put("black orange", -0x1.d1743ep125F); LIST.add(-0x1.d1743ep125F); }

    /**
     * This color constant "lead orange" has RGBA8888 code {@code AB7A49FF}, H 0.12156863, S 0.7294118, L 0.5137255, alpha 1.0, and chroma 0.5697224.
     * It can be represented as a packed float with the constant {@code -0x1.07743ep126F}.
     * <pre>
     * <font style='background-color: #AB7A49;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB7A49; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB7A49;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB7A49'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB7A49'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB7A49'>&nbsp;@&nbsp;</font><font style='background-color: #AB7A49; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB7A49;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB7A49; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_ORANGE = -0x1.07743ep126F;
    static { NAMED.put("lead orange", -0x1.07743ep126F); LIST.add(-0x1.07743ep126F); }

    /**
     * This color constant "gray orange" has RGBA8888 code {@code C0966DFF}, H 0.12941177, S 0.54509807, L 0.6156863, alpha 1.0, and chroma 0.48489353.
     * It can be represented as a packed float with the constant {@code -0x1.3b1642p126F}.
     * <pre>
     * <font style='background-color: #C0966D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0966D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0966D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C0966D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C0966D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C0966D'>&nbsp;@&nbsp;</font><font style='background-color: #C0966D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0966D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0966D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_ORANGE = -0x1.3b1642p126F;
    static { NAMED.put("gray orange", -0x1.3b1642p126F); LIST.add(-0x1.3b1642p126F); }

    /**
     * This color constant "silver orange" has RGBA8888 code {@code D3B79AFF}, H 0.14117648, S 0.33333334, L 0.73333335, alpha 1.0, and chroma 0.33043444.
     * It can be represented as a packed float with the constant {@code -0x1.76aa48p126F}.
     * <pre>
     * <font style='background-color: #D3B79A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3B79A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3B79A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D3B79A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D3B79A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D3B79A'>&nbsp;@&nbsp;</font><font style='background-color: #D3B79A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3B79A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3B79A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_ORANGE = -0x1.76aa48p126F;
    static { NAMED.put("silver orange", -0x1.76aa48p126F); LIST.add(-0x1.76aa48p126F); }

    /**
     * This color constant "white orange" has RGBA8888 code {@code EADBCEFF}, H 0.14117648, S 0.28627452, L 0.8666667, alpha 1.0, and chroma 0.12699346.
     * It can be represented as a packed float with the constant {@code -0x1.ba9248p126F}.
     * <pre>
     * <font style='background-color: #EADBCE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EADBCE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EADBCE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EADBCE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EADBCE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EADBCE'>&nbsp;@&nbsp;</font><font style='background-color: #EADBCE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EADBCE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EADBCE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_ORANGE = -0x1.ba9248p126F;
    static { NAMED.put("white orange", -0x1.ba9248p126F); LIST.add(-0x1.ba9248p126F); }

    /**
     * This color constant "black saffron" has RGBA8888 code {@code 856D4BFF}, H 0.15294118, S 0.5764706, L 0.4392157, alpha 1.0, and chroma 0.34009704.
     * It can be represented as a packed float with the constant {@code -0x1.e1264ep125F}.
     * <pre>
     * <font style='background-color: #856D4B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #856D4B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #856D4B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #856D4B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #856D4B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #856D4B'>&nbsp;@&nbsp;</font><font style='background-color: #856D4B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #856D4B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #856D4B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_SAFFRON = -0x1.e1264ep125F;
    static { NAMED.put("black saffron", -0x1.e1264ep125F); LIST.add(-0x1.e1264ep125F); }

    /**
     * This color constant "lead saffron" has RGBA8888 code {@code A98F68FF}, H 0.16078432, S 0.5137255, L 0.57254905, alpha 1.0, and chroma 0.3797873.
     * It can be represented as a packed float with the constant {@code -0x1.250652p126F}.
     * <pre>
     * <font style='background-color: #A98F68;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A98F68; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A98F68;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A98F68'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A98F68'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A98F68'>&nbsp;@&nbsp;</font><font style='background-color: #A98F68; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A98F68;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A98F68; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_SAFFRON = -0x1.250652p126F;
    static { NAMED.put("lead saffron", -0x1.250652p126F); LIST.add(-0x1.250652p126F); }

    /**
     * This color constant "gray saffron" has RGBA8888 code {@code BFAB8EFF}, H 0.16470589, S 0.3372549, L 0.6784314, alpha 1.0, and chroma 0.2881082.
     * It can be represented as a packed float with the constant {@code -0x1.5aac54p126F}.
     * <pre>
     * <font style='background-color: #BFAB8E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFAB8E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFAB8E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BFAB8E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BFAB8E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BFAB8E'>&nbsp;@&nbsp;</font><font style='background-color: #BFAB8E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFAB8E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFAB8E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_SAFFRON = -0x1.5aac54p126F;
    static { NAMED.put("gray saffron", -0x1.5aac54p126F); LIST.add(-0x1.5aac54p126F); }

    /**
     * This color constant "silver saffron" has RGBA8888 code {@code D2C4AFFF}, H 0.16862746, S 0.21568628, L 0.77254903, alpha 1.0, and chroma 0.20492314.
     * It can be represented as a packed float with the constant {@code -0x1.8a6e56p126F}.
     * <pre>
     * <font style='background-color: #D2C4AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2C4AF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2C4AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D2C4AF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D2C4AF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D2C4AF'>&nbsp;@&nbsp;</font><font style='background-color: #D2C4AF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2C4AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2C4AF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_SAFFRON = -0x1.8a6e56p126F;
    static { NAMED.put("silver saffron", -0x1.8a6e56p126F); LIST.add(-0x1.8a6e56p126F); }

    /**
     * This color constant "white saffron" has RGBA8888 code {@code E7E0D5FF}, H 0.17254902, S 0.14901961, L 0.8784314, alpha 1.0, and chroma 0.08296678.
     * It can be represented as a packed float with the constant {@code -0x1.c04c58p126F}.
     * <pre>
     * <font style='background-color: #E7E0D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E7E0D5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E7E0D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E7E0D5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E7E0D5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E7E0D5'>&nbsp;@&nbsp;</font><font style='background-color: #E7E0D5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E7E0D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E7E0D5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_SAFFRON = -0x1.c04c58p126F;
    static { NAMED.put("white saffron", -0x1.c04c58p126F); LIST.add(-0x1.c04c58p126F); }

    /**
     * This color constant "black yellow" has RGBA8888 code {@code 8F8F3AFF}, H 0.23921569, S 0.8666667, L 0.5411765, alpha 1.0, and chroma 0.5514001.
     * It can be represented as a packed float with the constant {@code -0x1.15ba7ap126F}.
     * <pre>
     * <font style='background-color: #8F8F3A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F8F3A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F8F3A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F8F3A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F8F3A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F8F3A'>&nbsp;@&nbsp;</font><font style='background-color: #8F8F3A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F8F3A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F8F3A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_YELLOW = -0x1.15ba7ap126F;
    static { NAMED.put("black yellow", -0x1.15ba7ap126F); LIST.add(-0x1.15ba7ap126F); }

    /**
     * This color constant "lead yellow" has RGBA8888 code {@code B6B64DFF}, H 0.23921569, S 0.84313726, L 0.6901961, alpha 1.0, and chroma 0.6709891.
     * It can be represented as a packed float with the constant {@code -0x1.61ae7ap126F}.
     * <pre>
     * <font style='background-color: #B6B64D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B64D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B64D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B6B64D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B6B64D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B6B64D'>&nbsp;@&nbsp;</font><font style='background-color: #B6B64D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B64D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B64D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_YELLOW = -0x1.61ae7ap126F;
    static { NAMED.put("lead yellow", -0x1.61ae7ap126F); LIST.add(-0x1.61ae7ap126F); }

    /**
     * This color constant "gray yellow" has RGBA8888 code {@code C5C572FF}, H 0.23921569, S 0.67058825, L 0.7529412, alpha 1.0, and chroma 0.5771059.
     * It can be represented as a packed float with the constant {@code -0x1.81567ap126F}.
     * <pre>
     * <font style='background-color: #C5C572;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C5C572; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C5C572;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C5C572'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C5C572'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C5C572'>&nbsp;@&nbsp;</font><font style='background-color: #C5C572; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C5C572;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C5C572; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_YELLOW = -0x1.81567ap126F;
    static { NAMED.put("gray yellow", -0x1.81567ap126F); LIST.add(-0x1.81567ap126F); }

    /**
     * This color constant "silver yellow" has RGBA8888 code {@code D6D69CFF}, H 0.23921569, S 0.45882353, L 0.8235294, alpha 1.0, and chroma 0.4275448.
     * It can be represented as a packed float with the constant {@code -0x1.a4ea7ap126F}.
     * <pre>
     * <font style='background-color: #D6D69C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D6D69C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D6D69C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D6D69C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D6D69C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D6D69C'>&nbsp;@&nbsp;</font><font style='background-color: #D6D69C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D6D69C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D6D69C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_YELLOW = -0x1.a4ea7ap126F;
    static { NAMED.put("silver yellow", -0x1.a4ea7ap126F); LIST.add(-0x1.a4ea7ap126F); }

    /**
     * This color constant "white yellow" has RGBA8888 code {@code EBEBCEFF}, H 0.23921569, S 0.21568628, L 0.9137255, alpha 1.0, and chroma 0.22009055.
     * It can be represented as a packed float with the constant {@code -0x1.d26e7ap126F}.
     * <pre>
     * <font style='background-color: #EBEBCE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBEBCE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBEBCE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EBEBCE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EBEBCE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EBEBCE'>&nbsp;@&nbsp;</font><font style='background-color: #EBEBCE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBEBCE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBEBCE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_YELLOW = -0x1.d26e7ap126F;
    static { NAMED.put("white yellow", -0x1.d26e7ap126F); LIST.add(-0x1.d26e7ap126F); }

    /**
     * This color constant "black lime" has RGBA8888 code {@code 697F39FF}, H 0.28627452, S 0.8039216, L 0.46666667, alpha 1.0, and chroma 0.47243056.
     * It can be represented as a packed float with the constant {@code -0x1.ef9a92p125F}.
     * <pre>
     * <font style='background-color: #697F39;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #697F39; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #697F39;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #697F39'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #697F39'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #697F39'>&nbsp;@&nbsp;</font><font style='background-color: #697F39; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #697F39;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #697F39; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_LIME = -0x1.ef9a92p125F;
    static { NAMED.put("black lime", -0x1.ef9a92p125F); LIST.add(-0x1.ef9a92p125F); }

    /**
     * This color constant "lead lime" has RGBA8888 code {@code 88A34AFF}, H 0.28627452, S 0.8, L 0.6, alpha 1.0, and chroma 0.5950293.
     * It can be represented as a packed float with the constant {@code -0x1.339892p126F}.
     * <pre>
     * <font style='background-color: #88A34A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #88A34A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #88A34A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #88A34A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #88A34A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #88A34A'>&nbsp;@&nbsp;</font><font style='background-color: #88A34A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #88A34A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #88A34A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_LIME = -0x1.339892p126F;
    static { NAMED.put("lead lime", -0x1.339892p126F); LIST.add(-0x1.339892p126F); }

    /**
     * This color constant "gray lime" has RGBA8888 code {@code A4BC6CFF}, H 0.28235295, S 0.654902, L 0.69803923, alpha 1.0, and chroma 0.5545147.
     * It can be represented as a packed float with the constant {@code -0x1.654e9p126F}.
     * <pre>
     * <font style='background-color: #A4BC6C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4BC6C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4BC6C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A4BC6C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A4BC6C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A4BC6C'>&nbsp;@&nbsp;</font><font style='background-color: #A4BC6C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4BC6C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4BC6C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_LIME = -0x1.654e9p126F;
    static { NAMED.put("gray lime", -0x1.654e9p126F); LIST.add(-0x1.654e9p126F); }

    /**
     * This color constant "silver lime" has RGBA8888 code {@code C1D19AFF}, H 0.2784314, S 0.42745098, L 0.7921569, alpha 1.0, and chroma 0.40212178.
     * It can be represented as a packed float with the constant {@code -0x1.94da8ep126F}.
     * <pre>
     * <font style='background-color: #C1D19A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C1D19A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C1D19A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C1D19A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C1D19A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C1D19A'>&nbsp;@&nbsp;</font><font style='background-color: #C1D19A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C1D19A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C1D19A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_LIME = -0x1.94da8ep126F;
    static { NAMED.put("silver lime", -0x1.94da8ep126F); LIST.add(-0x1.94da8ep126F); }

    /**
     * This color constant "white lime" has RGBA8888 code {@code E1E9CEFF}, H 0.27450982, S 0.19215687, L 0.8980392, alpha 1.0, and chroma 0.2003582.
     * It can be represented as a packed float with the constant {@code -0x1.ca628cp126F}.
     * <pre>
     * <font style='background-color: #E1E9CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1E9CE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1E9CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E1E9CE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E1E9CE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E1E9CE'>&nbsp;@&nbsp;</font><font style='background-color: #E1E9CE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1E9CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1E9CE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_LIME = -0x1.ca628cp126F;
    static { NAMED.put("white lime", -0x1.ca628cp126F); LIST.add(-0x1.ca628cp126F); }

    /**
     * This color constant "black green" has RGBA8888 code {@code 3B923BFF}, H 0.3529412, S 0.8235294, L 0.5019608, alpha 1.0, and chroma 0.6769508.
     * It can be represented as a packed float with the constant {@code -0x1.01a4b4p126F}.
     * <pre>
     * <font style='background-color: #3B923B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B923B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B923B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B923B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B923B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B923B'>&nbsp;@&nbsp;</font><font style='background-color: #3B923B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B923B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B923B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_GREEN = -0x1.01a4b4p126F;
    static { NAMED.put("black green", -0x1.01a4b4p126F); LIST.add(-0x1.01a4b4p126F); }

    /**
     * This color constant "lead green" has RGBA8888 code {@code 54B954FF}, H 0.3529412, S 0.76862746, L 0.6431373, alpha 1.0, and chroma 0.79541147.
     * It can be represented as a packed float with the constant {@code -0x1.4988b4p126F}.
     * <pre>
     * <font style='background-color: #54B954;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #54B954; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #54B954;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #54B954'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #54B954'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #54B954'>&nbsp;@&nbsp;</font><font style='background-color: #54B954; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #54B954;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #54B954; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_GREEN = -0x1.4988b4p126F;
    static { NAMED.put("lead green", -0x1.4988b4p126F); LIST.add(-0x1.4988b4p126F); }

    /**
     * This color constant "gray green" has RGBA8888 code {@code 7CC97CFF}, H 0.3529412, S 0.5529412, L 0.7176471, alpha 1.0, and chroma 0.6320239.
     * It can be represented as a packed float with the constant {@code -0x1.6f1ab4p126F}.
     * <pre>
     * <font style='background-color: #7CC97C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7CC97C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7CC97C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7CC97C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7CC97C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7CC97C'>&nbsp;@&nbsp;</font><font style='background-color: #7CC97C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7CC97C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7CC97C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_GREEN = -0x1.6f1ab4p126F;
    static { NAMED.put("gray green", -0x1.6f1ab4p126F); LIST.add(-0x1.6f1ab4p126F); }

    /**
     * This color constant "silver green" has RGBA8888 code {@code A1D8A1FF}, H 0.3529412, S 0.3647059, L 0.7921569, alpha 1.0, and chroma 0.4553236.
     * It can be represented as a packed float with the constant {@code -0x1.94bab4p126F}.
     * <pre>
     * <font style='background-color: #A1D8A1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A1D8A1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A1D8A1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A1D8A1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A1D8A1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A1D8A1'>&nbsp;@&nbsp;</font><font style='background-color: #A1D8A1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A1D8A1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A1D8A1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_GREEN = -0x1.94bab4p126F;
    static { NAMED.put("silver green", -0x1.94bab4p126F); LIST.add(-0x1.94bab4p126F); }

    /**
     * This color constant "white green" has RGBA8888 code {@code CFEBCFFF}, H 0.3529412, S 0.19215687, L 0.8901961, alpha 1.0, and chroma 0.19014442.
     * It can be represented as a packed float with the constant {@code -0x1.c662b4p126F}.
     * <pre>
     * <font style='background-color: #CFEBCF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CFEBCF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CFEBCF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CFEBCF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CFEBCF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CFEBCF'>&nbsp;@&nbsp;</font><font style='background-color: #CFEBCF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CFEBCF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CFEBCF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_GREEN = -0x1.c662b4p126F;
    static { NAMED.put("white green", -0x1.c662b4p126F); LIST.add(-0x1.c662b4p126F); }

    /**
     * This color constant "black cyan" has RGBA8888 code {@code 3D8787FF}, H 0.53333336, S 0.83137256, L 0.48235294, alpha 1.0, and chroma 0.34043238.
     * It can be represented as a packed float with the constant {@code -0x1.f7a91p125F}.
     * <pre>
     * <font style='background-color: #3D8787;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3D8787; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3D8787;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3D8787'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3D8787'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3D8787'>&nbsp;@&nbsp;</font><font style='background-color: #3D8787; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3D8787;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3D8787; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_CYAN = -0x1.f7a91p125F;
    static { NAMED.put("black cyan", -0x1.f7a91p125F); LIST.add(-0x1.f7a91p125F); }

    /**
     * This color constant "lead cyan" has RGBA8888 code {@code 4DACACFF}, H 0.53333336, S 0.83137256, L 0.6156863, alpha 1.0, and chroma 0.4275965.
     * It can be represented as a packed float with the constant {@code -0x1.3ba91p126F}.
     * <pre>
     * <font style='background-color: #4DACAC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4DACAC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4DACAC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4DACAC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4DACAC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4DACAC'>&nbsp;@&nbsp;</font><font style='background-color: #4DACAC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4DACAC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4DACAC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_CYAN = -0x1.3ba91p126F;
    static { NAMED.put("lead cyan", -0x1.3ba91p126F); LIST.add(-0x1.3ba91p126F); }

    /**
     * This color constant "gray cyan" has RGBA8888 code {@code 71BFBFFF}, H 0.53333336, S 0.6666667, L 0.69411767, alpha 1.0, and chroma 0.3825001.
     * It can be represented as a packed float with the constant {@code -0x1.63551p126F}.
     * <pre>
     * <font style='background-color: #71BFBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #71BFBF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #71BFBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #71BFBF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #71BFBF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #71BFBF'>&nbsp;@&nbsp;</font><font style='background-color: #71BFBF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #71BFBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #71BFBF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_CYAN = -0x1.63551p126F;
    static { NAMED.put("gray cyan", -0x1.63551p126F); LIST.add(-0x1.63551p126F); }

    /**
     * This color constant "silver cyan" has RGBA8888 code {@code 9DD2D2FF}, H 0.53333336, S 0.4392157, L 0.78431374, alpha 1.0, and chroma 0.28115714.
     * It can be represented as a packed float with the constant {@code -0x1.90e11p126F}.
     * <pre>
     * <font style='background-color: #9DD2D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9DD2D2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9DD2D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9DD2D2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9DD2D2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9DD2D2'>&nbsp;@&nbsp;</font><font style='background-color: #9DD2D2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9DD2D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9DD2D2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_CYAN = -0x1.90e11p126F;
    static { NAMED.put("silver cyan", -0x1.90e11p126F); LIST.add(-0x1.90e11p126F); }

    /**
     * This color constant "white cyan" has RGBA8888 code {@code CFE9E9FF}, H 0.53333336, S 0.2, L 0.89411765, alpha 1.0, and chroma 0.14365207.
     * It can be represented as a packed float with the constant {@code -0x1.c8671p126F}.
     * <pre>
     * <font style='background-color: #CFE9E9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CFE9E9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CFE9E9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CFE9E9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CFE9E9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CFE9E9'>&nbsp;@&nbsp;</font><font style='background-color: #CFE9E9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CFE9E9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CFE9E9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_CYAN = -0x1.c8671p126F;
    static { NAMED.put("white cyan", -0x1.c8671p126F); LIST.add(-0x1.c8671p126F); }

    /**
     * This color constant "black blue" has RGBA8888 code {@code 3C3C95FF}, H 0.7411765, S 0.59607846, L 0.2784314, alpha 1.0, and chroma 0.71036446.
     * It can be represented as a packed float with the constant {@code -0x1.8f317ap125F}.
     * <pre>
     * <font style='background-color: #3C3C95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C3C95; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C3C95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3C3C95'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3C3C95'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3C3C95'>&nbsp;@&nbsp;</font><font style='background-color: #3C3C95; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C3C95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C3C95; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_BLUE = -0x1.8f317ap125F;
    static { NAMED.put("black blue", -0x1.8f317ap125F); LIST.add(-0x1.8f317ap125F); }

    /**
     * This color constant "lead blue" has RGBA8888 code {@code 5959BBFF}, H 0.7411765, S 0.5921569, L 0.39215687, alpha 1.0, and chroma 0.78301805.
     * It can be represented as a packed float with the constant {@code -0x1.c92f7ap125F}.
     * <pre>
     * <font style='background-color: #5959BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5959BB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5959BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5959BB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5959BB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5959BB'>&nbsp;@&nbsp;</font><font style='background-color: #5959BB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5959BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5959BB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_BLUE = -0x1.c92f7ap125F;
    static { NAMED.put("lead blue", -0x1.c92f7ap125F); LIST.add(-0x1.c92f7ap125F); }

    /**
     * This color constant "gray blue" has RGBA8888 code {@code 8484CCFF}, H 0.7411765, S 0.52156866, L 0.5411765, alpha 1.0, and chroma 0.5643847.
     * It can be represented as a packed float with the constant {@code -0x1.150b7ap126F}.
     * <pre>
     * <font style='background-color: #8484CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8484CC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8484CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8484CC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8484CC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8484CC'>&nbsp;@&nbsp;</font><font style='background-color: #8484CC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8484CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8484CC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_BLUE = -0x1.150b7ap126F;
    static { NAMED.put("gray blue", -0x1.150b7ap126F); LIST.add(-0x1.150b7ap126F); }

    /**
     * This color constant "silver blue" has RGBA8888 code {@code ABABDCFF}, H 0.7411765, S 0.49019608, L 0.68235296, alpha 1.0, and chroma 0.36265084.
     * It can be represented as a packed float with the constant {@code -0x1.5cfb7ap126F}.
     * <pre>
     * <font style='background-color: #ABABDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABABDC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABABDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ABABDC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ABABDC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ABABDC'>&nbsp;@&nbsp;</font><font style='background-color: #ABABDC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABABDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABABDC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_BLUE = -0x1.5cfb7ap126F;
    static { NAMED.put("silver blue", -0x1.5cfb7ap126F); LIST.add(-0x1.5cfb7ap126F); }

    /**
     * This color constant "white blue" has RGBA8888 code {@code D2D2ECFF}, H 0.7411765, S 0.47843137, L 0.83137256, alpha 1.0, and chroma 0.17860506.
     * It can be represented as a packed float with the constant {@code -0x1.a8f57ap126F}.
     * <pre>
     * <font style='background-color: #D2D2EC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2D2EC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2D2EC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D2D2EC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D2D2EC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D2D2EC'>&nbsp;@&nbsp;</font><font style='background-color: #D2D2EC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2D2EC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2D2EC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_BLUE = -0x1.a8f57ap126F;
    static { NAMED.put("white blue", -0x1.a8f57ap126F); LIST.add(-0x1.a8f57ap126F); }

    /**
     * This color constant "black violet" has RGBA8888 code {@code 623F8CFF}, H 0.78039217, S 0.5921569, L 0.3137255, alpha 1.0, and chroma 0.59763813.
     * It can be represented as a packed float with the constant {@code -0x1.a12f8ep125F}.
     * <pre>
     * <font style='background-color: #623F8C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #623F8C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #623F8C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #623F8C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #623F8C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #623F8C'>&nbsp;@&nbsp;</font><font style='background-color: #623F8C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #623F8C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #623F8C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_VIOLET = -0x1.a12f8ep125F;
    static { NAMED.put("black violet", -0x1.a12f8ep125F); LIST.add(-0x1.a12f8ep125F); }

    /**
     * This color constant "lead violet" has RGBA8888 code {@code 8055B2FF}, H 0.78039217, S 0.5647059, L 0.4117647, alpha 1.0, and chroma 0.7442247.
     * It can be represented as a packed float with the constant {@code -0x1.d3218ep125F}.
     * <pre>
     * <font style='background-color: #8055B2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8055B2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8055B2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8055B2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8055B2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8055B2'>&nbsp;@&nbsp;</font><font style='background-color: #8055B2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8055B2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8055B2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_VIOLET = -0x1.d3218ep125F;
    static { NAMED.put("lead violet", -0x1.d3218ep125F); LIST.add(-0x1.d3218ep125F); }

    /**
     * This color constant "gray violet" has RGBA8888 code {@code 9B79C2FF}, H 0.7882353, S 0.45882353, L 0.5294118, alpha 1.0, and chroma 0.53349984.
     * It can be represented as a packed float with the constant {@code -0x1.0eeb92p126F}.
     * <pre>
     * <font style='background-color: #9B79C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B79C2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B79C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9B79C2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9B79C2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9B79C2'>&nbsp;@&nbsp;</font><font style='background-color: #9B79C2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B79C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B79C2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_VIOLET = -0x1.0eeb92p126F;
    static { NAMED.put("gray violet", -0x1.0eeb92p126F); LIST.add(-0x1.0eeb92p126F); }

    /**
     * This color constant "silver violet" has RGBA8888 code {@code B8A0D4FF}, H 0.7882353, S 0.43529412, L 0.6627451, alpha 1.0, and chroma 0.36203572.
     * It can be represented as a packed float with the constant {@code -0x1.52df92p126F}.
     * <pre>
     * <font style='background-color: #B8A0D4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B8A0D4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B8A0D4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B8A0D4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B8A0D4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B8A0D4'>&nbsp;@&nbsp;</font><font style='background-color: #B8A0D4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B8A0D4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B8A0D4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_VIOLET = -0x1.52df92p126F;
    static { NAMED.put("silver violet", -0x1.52df92p126F); LIST.add(-0x1.52df92p126F); }

    /**
     * This color constant "white violet" has RGBA8888 code {@code DBD0E9FF}, H 0.7921569, S 0.4117647, L 0.83137256, alpha 1.0, and chroma 0.16405578.
     * It can be represented as a packed float with the constant {@code -0x1.a8d394p126F}.
     * <pre>
     * <font style='background-color: #DBD0E9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBD0E9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBD0E9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DBD0E9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DBD0E9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DBD0E9'>&nbsp;@&nbsp;</font><font style='background-color: #DBD0E9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBD0E9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBD0E9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_VIOLET = -0x1.a8d394p126F;
    static { NAMED.put("white violet", -0x1.a8d394p126F); LIST.add(-0x1.a8d394p126F); }

    /**
     * This color constant "black purple" has RGBA8888 code {@code 733786FF}, H 0.81960785, S 0.69803923, L 0.3137255, alpha 1.0, and chroma 0.5915201.
     * It can be represented as a packed float with the constant {@code -0x1.a165a2p125F}.
     * <pre>
     * <font style='background-color: #733786;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #733786; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #733786;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #733786'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #733786'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #733786'>&nbsp;@&nbsp;</font><font style='background-color: #733786; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #733786;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #733786; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_PURPLE = -0x1.a165a2p125F;
    static { NAMED.put("black purple", -0x1.a165a2p125F); LIST.add(-0x1.a165a2p125F); }

    /**
     * This color constant "lead purple" has RGBA8888 code {@code 9246ABFF}, H 0.81960785, S 0.70980394, L 0.4, alpha 1.0, and chroma 0.7637036.
     * It can be represented as a packed float with the constant {@code -0x1.cd6ba2p125F}.
     * <pre>
     * <font style='background-color: #9246AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9246AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9246AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9246AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9246AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9246AB'>&nbsp;@&nbsp;</font><font style='background-color: #9246AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9246AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9246AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_PURPLE = -0x1.cd6ba2p125F;
    static { NAMED.put("lead purple", -0x1.cd6ba2p125F); LIST.add(-0x1.cd6ba2p125F); }

    /**
     * This color constant "gray purple" has RGBA8888 code {@code AC6AC2FF}, H 0.81960785, S 0.5176471, L 0.5137255, alpha 1.0, and chroma 0.6743632.
     * It can be represented as a packed float with the constant {@code -0x1.0709a2p126F}.
     * <pre>
     * <font style='background-color: #AC6AC2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC6AC2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC6AC2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AC6AC2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AC6AC2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AC6AC2'>&nbsp;@&nbsp;</font><font style='background-color: #AC6AC2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC6AC2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC6AC2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_PURPLE = -0x1.0709a2p126F;
    static { NAMED.put("gray purple", -0x1.0709a2p126F); LIST.add(-0x1.0709a2p126F); }

    /**
     * This color constant "silver purple" has RGBA8888 code {@code C698D5FF}, H 0.8235294, S 0.45882353, L 0.65882355, alpha 1.0, and chroma 0.42822737.
     * It can be represented as a packed float with the constant {@code -0x1.50eba4p126F}.
     * <pre>
     * <font style='background-color: #C698D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C698D5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C698D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C698D5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C698D5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C698D5'>&nbsp;@&nbsp;</font><font style='background-color: #C698D5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C698D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C698D5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_PURPLE = -0x1.50eba4p126F;
    static { NAMED.put("silver purple", -0x1.50eba4p126F); LIST.add(-0x1.50eba4p126F); }

    /**
     * This color constant "white purple" has RGBA8888 code {@code E3CDEAFF}, H 0.827451, S 0.43529412, L 0.83137256, alpha 1.0, and chroma 0.19419964.
     * It can be represented as a packed float with the constant {@code -0x1.a8dfa6p126F}.
     * <pre>
     * <font style='background-color: #E3CDEA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3CDEA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3CDEA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E3CDEA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E3CDEA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E3CDEA'>&nbsp;@&nbsp;</font><font style='background-color: #E3CDEA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3CDEA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3CDEA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_PURPLE = -0x1.a8dfa6p126F;
    static { NAMED.put("white purple", -0x1.a8dfa6p126F); LIST.add(-0x1.a8dfa6p126F); }

    /**
     * This color constant "black magenta" has RGBA8888 code {@code 8F408FFF}, H 0.85490197, S 0.69411767, L 0.37254903, alpha 1.0, and chroma 0.6376142.
     * It can be represented as a packed float with the constant {@code -0x1.bf63b4p125F}.
     * <pre>
     * <font style='background-color: #8F408F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F408F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F408F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F408F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F408F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F408F'>&nbsp;@&nbsp;</font><font style='background-color: #8F408F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F408F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F408F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_MAGENTA = -0x1.bf63b4p125F;
    static { NAMED.put("black magenta", -0x1.bf63b4p125F); LIST.add(-0x1.bf63b4p125F); }

    /**
     * This color constant "lead magenta" has RGBA8888 code {@code B55BB5FF}, H 0.85490197, S 0.62352943, L 0.49019608, alpha 1.0, and chroma 0.7458214.
     * It can be represented as a packed float with the constant {@code -0x1.fb3fb4p125F}.
     * <pre>
     * <font style='background-color: #B55BB5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B55BB5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B55BB5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B55BB5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B55BB5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B55BB5'>&nbsp;@&nbsp;</font><font style='background-color: #B55BB5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B55BB5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B55BB5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_MAGENTA = -0x1.fb3fb4p125F;
    static { NAMED.put("lead magenta", -0x1.fb3fb4p125F); LIST.add(-0x1.fb3fb4p125F); }

    /**
     * This color constant "gray magenta" has RGBA8888 code {@code C783C7FF}, H 0.85490197, S 0.40392157, L 0.6039216, alpha 1.0, and chroma 0.5090193.
     * It can be represented as a packed float with the constant {@code -0x1.34cfb4p126F}.
     * <pre>
     * <font style='background-color: #C783C7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C783C7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C783C7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C783C7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C783C7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C783C7'>&nbsp;@&nbsp;</font><font style='background-color: #C783C7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C783C7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C783C7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_MAGENTA = -0x1.34cfb4p126F;
    static { NAMED.put("gray magenta", -0x1.34cfb4p126F); LIST.add(-0x1.34cfb4p126F); }

    /**
     * This color constant "silver magenta" has RGBA8888 code {@code D7A7D7FF}, H 0.85490197, S 0.39215687, L 0.7137255, alpha 1.0, and chroma 0.35237563.
     * It can be represented as a packed float with the constant {@code -0x1.6cc9b4p126F}.
     * <pre>
     * <font style='background-color: #D7A7D7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7A7D7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7A7D7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D7A7D7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D7A7D7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D7A7D7'>&nbsp;@&nbsp;</font><font style='background-color: #D7A7D7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7A7D7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7A7D7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_MAGENTA = -0x1.6cc9b4p126F;
    static { NAMED.put("silver magenta", -0x1.6cc9b4p126F); LIST.add(-0x1.6cc9b4p126F); }

    /**
     * This color constant "white magenta" has RGBA8888 code {@code EAD1EAFF}, H 0.85490197, S 0.3882353, L 0.84705883, alpha 1.0, and chroma 0.17896329.
     * It can be represented as a packed float with the constant {@code -0x1.b0c7b4p126F}.
     * <pre>
     * <font style='background-color: #EAD1EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EAD1EA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EAD1EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EAD1EA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EAD1EA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EAD1EA'>&nbsp;@&nbsp;</font><font style='background-color: #EAD1EA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EAD1EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EAD1EA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_MAGENTA = -0x1.b0c7b4p126F;
    static { NAMED.put("white magenta", -0x1.b0c7b4p126F); LIST.add(-0x1.b0c7b4p126F); }

    /**
     * This color constant "drab red" has RGBA8888 code {@code A42020FF}, H 0.03137255, S 0.8980392, L 0.32941177, alpha 1.0, and chroma 1.0601833.
     * It can be represented as a packed float with the constant {@code -0x1.a9ca1p125F}.
     * <pre>
     * <font style='background-color: #A42020;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A42020; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A42020;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A42020'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A42020'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A42020'>&nbsp;@&nbsp;</font><font style='background-color: #A42020; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A42020;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A42020; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_RED = -0x1.a9ca1p125F;
    static { NAMED.put("drab red", -0x1.a9ca1p125F); LIST.add(-0x1.a9ca1p125F); }

    /**
     * This color constant "faded red" has RGBA8888 code {@code DB4B4BFF}, H 0.03137255, S 0.68235296, L 0.49019608, alpha 1.0, and chroma 1.1839659.
     * It can be represented as a packed float with the constant {@code -0x1.fb5c1p125F}.
     * <pre>
     * <font style='background-color: #DB4B4B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB4B4B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB4B4B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DB4B4B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DB4B4B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DB4B4B'>&nbsp;@&nbsp;</font><font style='background-color: #DB4B4B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB4B4B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB4B4B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_RED = -0x1.fb5c1p125F;
    static { NAMED.put("faded red", -0x1.fb5c1p125F); LIST.add(-0x1.fb5c1p125F); }

    /**
     * This color constant "pale red" has RGBA8888 code {@code EEAEAEFF}, H 0.03137255, S 0.6, L 0.7411765, alpha 1.0, and chroma 0.36215526.
     * It can be represented as a packed float with the constant {@code -0x1.7b321p126F}.
     * <pre>
     * <font style='background-color: #EEAEAE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EEAEAE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EEAEAE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EEAEAE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EEAEAE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EEAEAE'>&nbsp;@&nbsp;</font><font style='background-color: #EEAEAE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EEAEAE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EEAEAE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_RED = -0x1.7b321p126F;
    static { NAMED.put("pale red", -0x1.7b321p126F); LIST.add(-0x1.7b321p126F); }

    /**
     * This color constant "drab brown" has RGBA8888 code {@code B55A2DFF}, H 0.07058824, S 0.89411765, L 0.4509804, alpha 1.0, and chroma 0.8939954.
     * It can be represented as a packed float with the constant {@code -0x1.e7c824p125F}.
     * <pre>
     * <font style='background-color: #B55A2D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B55A2D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B55A2D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B55A2D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B55A2D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B55A2D'>&nbsp;@&nbsp;</font><font style='background-color: #B55A2D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B55A2D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B55A2D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BROWN = -0x1.e7c824p125F;
    static { NAMED.put("drab brown", -0x1.e7c824p125F); LIST.add(-0x1.e7c824p125F); }

    /**
     * This color constant "faded brown" has RGBA8888 code {@code D98C66FF}, H 0.08627451, S 0.6117647, L 0.61960787, alpha 1.0, and chroma 0.71556365.
     * It can be represented as a packed float with the constant {@code -0x1.3d382cp126F}.
     * <pre>
     * <font style='background-color: #D98C66;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D98C66; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D98C66;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D98C66'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D98C66'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D98C66'>&nbsp;@&nbsp;</font><font style='background-color: #D98C66; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D98C66;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D98C66; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_BROWN = -0x1.3d382cp126F;
    static { NAMED.put("faded brown", -0x1.3d382cp126F); LIST.add(-0x1.3d382cp126F); }

    /**
     * This color constant "pale brown" has RGBA8888 code {@code ECC7B5FF}, H 0.101960786, S 0.47843137, L 0.80784315, alpha 1.0, and chroma 0.25402233.
     * It can be represented as a packed float with the constant {@code -0x1.9cf434p126F}.
     * <pre>
     * <font style='background-color: #ECC7B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ECC7B5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ECC7B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ECC7B5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ECC7B5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ECC7B5'>&nbsp;@&nbsp;</font><font style='background-color: #ECC7B5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ECC7B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ECC7B5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BROWN = -0x1.9cf434p126F;
    static { NAMED.put("pale brown", -0x1.9cf434p126F); LIST.add(-0x1.9cf434p126F); }

    /**
     * This color constant "drab orange" has RGBA8888 code {@code AC6926FF}, H 0.105882354, S 0.9529412, L 0.47058824, alpha 1.0, and chroma 0.74914205.
     * It can be represented as a packed float with the constant {@code -0x1.f1e636p125F}.
     * <pre>
     * <font style='background-color: #AC6926;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC6926; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC6926;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AC6926'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AC6926'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AC6926'>&nbsp;@&nbsp;</font><font style='background-color: #AC6926; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC6926;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC6926; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_ORANGE = -0x1.f1e636p125F;
    static { NAMED.put("drab orange", -0x1.f1e636p125F); LIST.add(-0x1.f1e636p125F); }

    /**
     * This color constant "faded orange" has RGBA8888 code {@code D99654FF}, H 0.11764706, S 0.77254903, L 0.6392157, alpha 1.0, and chroma 0.7545668.
     * It can be represented as a packed float with the constant {@code -0x1.478a3cp126F}.
     * <pre>
     * <font style='background-color: #D99654;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D99654; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D99654;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D99654'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D99654'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D99654'>&nbsp;@&nbsp;</font><font style='background-color: #D99654; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D99654;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D99654; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_ORANGE = -0x1.478a3cp126F;
    static { NAMED.put("faded orange", -0x1.478a3cp126F); LIST.add(-0x1.478a3cp126F); }

    /**
     * This color constant "pale orange" has RGBA8888 code {@code EDCFB0FF}, H 0.14509805, S 0.44705883, L 0.827451, alpha 1.0, and chroma 0.28345093.
     * It can be represented as a packed float with the constant {@code -0x1.a6e44ap126F}.
     * <pre>
     * <font style='background-color: #EDCFB0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDCFB0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDCFB0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EDCFB0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EDCFB0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EDCFB0'>&nbsp;@&nbsp;</font><font style='background-color: #EDCFB0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDCFB0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDCFB0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_ORANGE = -0x1.a6e44ap126F;
    static { NAMED.put("pale orange", -0x1.a6e44ap126F); LIST.add(-0x1.a6e44ap126F); }

    /**
     * This color constant "drab saffron" has RGBA8888 code {@code A67D40FF}, H 0.14509805, S 0.8039216, L 0.5176471, alpha 1.0, and chroma 0.5699539.
     * It can be represented as a packed float with the constant {@code -0x1.099a4ap126F}.
     * <pre>
     * <font style='background-color: #A67D40;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A67D40; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A67D40;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A67D40'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A67D40'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A67D40'>&nbsp;@&nbsp;</font><font style='background-color: #A67D40; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A67D40;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A67D40; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_SAFFRON = -0x1.099a4ap126F;
    static { NAMED.put("drab saffron", -0x1.099a4ap126F); LIST.add(-0x1.099a4ap126F); }

    /**
     * This color constant "faded saffron" has RGBA8888 code {@code CDAD7FFF}, H 0.15686275, S 0.5058824, L 0.69411767, alpha 1.0, and chroma 0.45150214.
     * It can be represented as a packed float with the constant {@code -0x1.63025p126F}.
     * <pre>
     * <font style='background-color: #CDAD7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CDAD7F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CDAD7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CDAD7F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CDAD7F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CDAD7F'>&nbsp;@&nbsp;</font><font style='background-color: #CDAD7F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CDAD7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CDAD7F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_SAFFRON = -0x1.63025p126F;
    static { NAMED.put("faded saffron", -0x1.63025p126F); LIST.add(-0x1.63025p126F); }

    /**
     * This color constant "pale saffron" has RGBA8888 code {@code E6D7C0FF}, H 0.16862746, S 0.24705882, L 0.84705883, alpha 1.0, and chroma 0.17422985.
     * It can be represented as a packed float with the constant {@code -0x1.b07e56p126F}.
     * <pre>
     * <font style='background-color: #E6D7C0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E6D7C0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E6D7C0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E6D7C0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E6D7C0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E6D7C0'>&nbsp;@&nbsp;</font><font style='background-color: #E6D7C0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E6D7C0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E6D7C0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_SAFFRON = -0x1.b07e56p126F;
    static { NAMED.put("pale saffron", -0x1.b07e56p126F); LIST.add(-0x1.b07e56p126F); }

    /**
     * This color constant "drab yellow" has RGBA8888 code {@code B9B924FF}, H 0.23921569, S 1.0, L 0.69803923, alpha 1.0, and chroma 0.8039973.
     * It can be represented as a packed float with the constant {@code -0x1.65fe7ap126F}.
     * <pre>
     * <font style='background-color: #B9B924;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B9B924; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B9B924;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B9B924'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B9B924'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B9B924'>&nbsp;@&nbsp;</font><font style='background-color: #B9B924; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B9B924;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B9B924; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_YELLOW = -0x1.65fe7ap126F;
    static { NAMED.put("drab yellow", -0x1.65fe7ap126F); LIST.add(-0x1.65fe7ap126F); }

    /**
     * This color constant "faded yellow" has RGBA8888 code {@code DEDE59FF}, H 0.23921569, S 0.8509804, L 0.84313726, alpha 1.0, and chroma 0.8095511.
     * It can be represented as a packed float with the constant {@code -0x1.afb27ap126F}.
     * <pre>
     * <font style='background-color: #DEDE59;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEDE59; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEDE59;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DEDE59'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DEDE59'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DEDE59'>&nbsp;@&nbsp;</font><font style='background-color: #DEDE59; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEDE59;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEDE59; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_YELLOW = -0x1.afb27ap126F;
    static { NAMED.put("faded yellow", -0x1.afb27ap126F); LIST.add(-0x1.afb27ap126F); }

    /**
     * This color constant "pale yellow" has RGBA8888 code {@code EFEFB0FF}, H 0.23921569, S 0.44313726, L 0.92156863, alpha 1.0, and chroma 0.4555444.
     * It can be represented as a packed float with the constant {@code -0x1.d6e27ap126F}.
     * <pre>
     * <font style='background-color: #EFEFB0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFEFB0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFEFB0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EFEFB0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EFEFB0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EFEFB0'>&nbsp;@&nbsp;</font><font style='background-color: #EFEFB0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFEFB0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFEFB0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOW = -0x1.d6e27ap126F;
    static { NAMED.put("pale yellow", -0x1.d6e27ap126F); LIST.add(-0x1.d6e27ap126F); }

    /**
     * This color constant "drab lime" has RGBA8888 code {@code 7CA128FF}, H 0.29411766, S 0.9764706, L 0.5803922, alpha 1.0, and chroma 0.718003.
     * It can be represented as a packed float with the constant {@code -0x1.29f296p126F}.
     * <pre>
     * <font style='background-color: #7CA128;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7CA128; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7CA128;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7CA128'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7CA128'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7CA128'>&nbsp;@&nbsp;</font><font style='background-color: #7CA128; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7CA128;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7CA128; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_LIME = -0x1.29f296p126F;
    static { NAMED.put("drab lime", -0x1.29f296p126F); LIST.add(-0x1.29f296p126F); }

    /**
     * This color constant "faded lime" has RGBA8888 code {@code ADD454FF}, H 0.28627452, S 0.84705883, L 0.7764706, alpha 1.0, and chroma 0.7958633.
     * It can be represented as a packed float with the constant {@code -0x1.8db092p126F}.
     * <pre>
     * <font style='background-color: #ADD454;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ADD454; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ADD454;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ADD454'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ADD454'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ADD454'>&nbsp;@&nbsp;</font><font style='background-color: #ADD454; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ADD454;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ADD454; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_LIME = -0x1.8db092p126F;
    static { NAMED.put("faded lime", -0x1.8db092p126F); LIST.add(-0x1.8db092p126F); }

    /**
     * This color constant "pale lime" has RGBA8888 code {@code DAEBB1FF}, H 0.2784314, S 0.39607844, L 0.89411765, alpha 1.0, and chroma 0.41440845.
     * It can be represented as a packed float with the constant {@code -0x1.c8ca8ep126F}.
     * <pre>
     * <font style='background-color: #DAEBB1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAEBB1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAEBB1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DAEBB1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DAEBB1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DAEBB1'>&nbsp;@&nbsp;</font><font style='background-color: #DAEBB1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAEBB1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAEBB1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_LIME = -0x1.c8ca8ep126F;
    static { NAMED.put("pale lime", -0x1.c8ca8ep126F); LIST.add(-0x1.c8ca8ep126F); }

    /**
     * This color constant "drab green" has RGBA8888 code {@code 25BE25FF}, H 0.3529412, S 0.99215686, L 0.6431373, alpha 1.0, and chroma 1.0267301.
     * It can be represented as a packed float with the constant {@code -0x1.49fab4p126F}.
     * <pre>
     * <font style='background-color: #25BE25;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #25BE25; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #25BE25;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #25BE25'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #25BE25'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #25BE25'>&nbsp;@&nbsp;</font><font style='background-color: #25BE25; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #25BE25;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #25BE25; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_GREEN = -0x1.49fab4p126F;
    static { NAMED.put("drab green", -0x1.49fab4p126F); LIST.add(-0x1.49fab4p126F); }

    /**
     * This color constant "faded green" has RGBA8888 code {@code 65E065FF}, H 0.3529412, S 0.7647059, L 0.7764706, alpha 1.0, and chroma 0.93790454.
     * It can be represented as a packed float with the constant {@code -0x1.8d86b4p126F}.
     * <pre>
     * <font style='background-color: #65E065;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #65E065; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #65E065;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #65E065'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #65E065'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #65E065'>&nbsp;@&nbsp;</font><font style='background-color: #65E065; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #65E065;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #65E065; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_GREEN = -0x1.8d86b4p126F;
    static { NAMED.put("faded green", -0x1.8d86b4p126F); LIST.add(-0x1.8d86b4p126F); }

    /**
     * This color constant "pale green" has RGBA8888 code {@code B3F0B3FF}, H 0.3529412, S 0.36862746, L 0.88235295, alpha 1.0, and chroma 0.39859918.
     * It can be represented as a packed float with the constant {@code -0x1.c2bcb4p126F}.
     * <pre>
     * <font style='background-color: #B3F0B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B3F0B3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B3F0B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B3F0B3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B3F0B3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B3F0B3'>&nbsp;@&nbsp;</font><font style='background-color: #B3F0B3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B3F0B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B3F0B3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_GREEN = -0x1.c2bcb4p126F;
    static { NAMED.put("pale green", -0x1.c2bcb4p126F); LIST.add(-0x1.c2bcb4p126F); }

    /**
     * This color constant "drab cyan" has RGBA8888 code {@code 2AACACFF}, H 0.53333336, S 0.9882353, L 0.60784316, alpha 1.0, and chroma 0.502314.
     * It can be represented as a packed float with the constant {@code -0x1.37f91p126F}.
     * <pre>
     * <font style='background-color: #2AACAC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2AACAC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2AACAC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2AACAC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2AACAC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2AACAC'>&nbsp;@&nbsp;</font><font style='background-color: #2AACAC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2AACAC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2AACAC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_CYAN = -0x1.37f91p126F;
    static { NAMED.put("drab cyan", -0x1.37f91p126F); LIST.add(-0x1.37f91p126F); }

    /**
     * This color constant "faded cyan" has RGBA8888 code {@code 5BD6D6FF}, H 0.53333336, S 0.84313726, L 0.7647059, alpha 1.0, and chroma 0.52769977.
     * It can be represented as a packed float with the constant {@code -0x1.87af1p126F}.
     * <pre>
     * <font style='background-color: #5BD6D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5BD6D6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5BD6D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5BD6D6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5BD6D6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5BD6D6'>&nbsp;@&nbsp;</font><font style='background-color: #5BD6D6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5BD6D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5BD6D6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_CYAN = -0x1.87af1p126F;
    static { NAMED.put("faded cyan", -0x1.87af1p126F); LIST.add(-0x1.87af1p126F); }

    /**
     * This color constant "pale cyan" has RGBA8888 code {@code B2ECECFF}, H 0.53333336, S 0.42352942, L 0.88235295, alpha 1.0, and chroma 0.30071712.
     * It can be represented as a packed float with the constant {@code -0x1.c2d91p126F}.
     * <pre>
     * <font style='background-color: #B2ECEC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2ECEC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2ECEC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B2ECEC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B2ECEC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B2ECEC'>&nbsp;@&nbsp;</font><font style='background-color: #B2ECEC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2ECEC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2ECEC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_CYAN = -0x1.c2d91p126F;
    static { NAMED.put("pale cyan", -0x1.c2d91p126F); LIST.add(-0x1.c2d91p126F); }

    /**
     * This color constant "drab blue" has RGBA8888 code {@code 2626C2FF}, H 0.7411765, S 0.9254902, L 0.2627451, alpha 1.0, and chroma 1.0400109.
     * It can be represented as a packed float with the constant {@code -0x1.87d97ap125F}.
     * <pre>
     * <font style='background-color: #2626C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2626C2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2626C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2626C2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2626C2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2626C2'>&nbsp;@&nbsp;</font><font style='background-color: #2626C2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2626C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2626C2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BLUE = -0x1.87d97ap125F;
    static { NAMED.put("drab blue", -0x1.87d97ap125F); LIST.add(-0x1.87d97ap125F); }

    /**
     * This color constant "faded blue" has RGBA8888 code {@code 6F6FE2FF}, H 0.7411765, S 0.7529412, L 0.4862745, alpha 1.0, and chroma 0.89969575.
     * It can be represented as a packed float with the constant {@code -0x1.f9817ap125F}.
     * <pre>
     * <font style='background-color: #6F6FE2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6F6FE2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6F6FE2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6F6FE2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6F6FE2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6F6FE2'>&nbsp;@&nbsp;</font><font style='background-color: #6F6FE2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6F6FE2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6F6FE2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_BLUE = -0x1.f9817ap125F;
    static { NAMED.put("faded blue", -0x1.f9817ap125F); LIST.add(-0x1.f9817ap125F); }

    /**
     * This color constant "pale blue" has RGBA8888 code {@code B9B9F1FF}, H 0.7411765, S 0.6901961, L 0.7411765, alpha 1.0, and chroma 0.40894327.
     * It can be represented as a packed float with the constant {@code -0x1.7b617ap126F}.
     * <pre>
     * <font style='background-color: #B9B9F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B9B9F1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B9B9F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B9B9F1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B9B9F1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B9B9F1'>&nbsp;@&nbsp;</font><font style='background-color: #B9B9F1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B9B9F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B9B9F1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BLUE = -0x1.7b617ap126F;
    static { NAMED.put("pale blue", -0x1.7b617ap126F); LIST.add(-0x1.7b617ap126F); }

    /**
     * This color constant "drab violet" has RGBA8888 code {@code 6A2CB4FF}, H 0.77254903, S 0.8901961, L 0.3137255, alpha 1.0, and chroma 0.9414144.
     * It can be represented as a packed float with the constant {@code -0x1.a1c78ap125F}.
     * <pre>
     * <font style='background-color: #6A2CB4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A2CB4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A2CB4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6A2CB4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6A2CB4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6A2CB4'>&nbsp;@&nbsp;</font><font style='background-color: #6A2CB4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A2CB4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A2CB4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_VIOLET = -0x1.a1c78ap125F;
    static { NAMED.put("drab violet", -0x1.a1c78ap125F); LIST.add(-0x1.a1c78ap125F); }

    /**
     * This color constant "faded violet" has RGBA8888 code {@code 9964D8FF}, H 0.78039217, S 0.6745098, L 0.49411765, alpha 1.0, and chroma 0.8225662.
     * It can be represented as a packed float with the constant {@code -0x1.fd598ep125F}.
     * <pre>
     * <font style='background-color: #9964D8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9964D8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9964D8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9964D8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9964D8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9964D8'>&nbsp;@&nbsp;</font><font style='background-color: #9964D8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9964D8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9964D8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_VIOLET = -0x1.fd598ep125F;
    static { NAMED.put("faded violet", -0x1.fd598ep125F); LIST.add(-0x1.fd598ep125F); }

    /**
     * This color constant "pale violet" has RGBA8888 code {@code CEB4ECFF}, H 0.7882353, S 0.6156863, L 0.74509805, alpha 1.0, and chroma 0.37892354.
     * It can be represented as a packed float with the constant {@code -0x1.7d3b92p126F}.
     * <pre>
     * <font style='background-color: #CEB4EC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CEB4EC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CEB4EC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CEB4EC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CEB4EC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CEB4EC'>&nbsp;@&nbsp;</font><font style='background-color: #CEB4EC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CEB4EC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CEB4EC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_VIOLET = -0x1.7d3b92p126F;
    static { NAMED.put("pale violet", -0x1.7d3b92p126F); LIST.add(-0x1.7d3b92p126F); }

    /**
     * This color constant "drab purple" has RGBA8888 code {@code 8A22ACFF}, H 0.8117647, S 0.96862745, L 0.34117648, alpha 1.0, and chroma 0.91729367.
     * It can be represented as a packed float with the constant {@code -0x1.afef9ep125F}.
     * <pre>
     * <font style='background-color: #8A22AC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A22AC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A22AC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8A22AC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8A22AC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8A22AC'>&nbsp;@&nbsp;</font><font style='background-color: #8A22AC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A22AC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A22AC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_PURPLE = -0x1.afef9ep125F;
    static { NAMED.put("drab purple", -0x1.afef9ep125F); LIST.add(-0x1.afef9ep125F); }

    /**
     * This color constant "faded purple" has RGBA8888 code {@code B94FDCFF}, H 0.8156863, S 0.78431374, L 0.49411765, alpha 1.0, and chroma 1.0416006.
     * It can be represented as a packed float with the constant {@code -0x1.fd91ap125F}.
     * <pre>
     * <font style='background-color: #B94FDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B94FDC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B94FDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B94FDC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B94FDC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B94FDC'>&nbsp;@&nbsp;</font><font style='background-color: #B94FDC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B94FDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B94FDC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_PURPLE = -0x1.fd91ap125F;
    static { NAMED.put("faded purple", -0x1.fd91ap125F); LIST.add(-0x1.fd91ap125F); }

    /**
     * This color constant "pale purple" has RGBA8888 code {@code DFAEEFFF}, H 0.8235294, S 0.65882355, L 0.74509805, alpha 1.0, and chroma 0.45028028.
     * It can be represented as a packed float with the constant {@code -0x1.7d51a4p126F}.
     * <pre>
     * <font style='background-color: #DFAEEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DFAEEF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DFAEEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DFAEEF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DFAEEF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DFAEEF'>&nbsp;@&nbsp;</font><font style='background-color: #DFAEEF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DFAEEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DFAEEF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLE = -0x1.7d51a4p126F;
    static { NAMED.put("pale purple", -0x1.7d51a4p126F); LIST.add(-0x1.7d51a4p126F); }

    /**
     * This color constant "drab magenta" has RGBA8888 code {@code B82DB8FF}, H 0.85490197, S 0.94509804, L 0.42745098, alpha 1.0, and chroma 0.9919542.
     * It can be represented as a packed float with the constant {@code -0x1.dbe3b4p125F}.
     * <pre>
     * <font style='background-color: #B82DB8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B82DB8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B82DB8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B82DB8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B82DB8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B82DB8'>&nbsp;@&nbsp;</font><font style='background-color: #B82DB8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B82DB8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B82DB8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_MAGENTA = -0x1.dbe3b4p125F;
    static { NAMED.put("drab magenta", -0x1.dbe3b4p125F); LIST.add(-0x1.dbe3b4p125F); }

    /**
     * This color constant "faded magenta" has RGBA8888 code {@code DB6FDBFF}, H 0.85490197, S 0.61960787, L 0.59607846, alpha 1.0, and chroma 0.7961646.
     * It can be represented as a packed float with the constant {@code -0x1.313db4p126F}.
     * <pre>
     * <font style='background-color: #DB6FDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB6FDB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB6FDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DB6FDB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DB6FDB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DB6FDB'>&nbsp;@&nbsp;</font><font style='background-color: #DB6FDB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB6FDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB6FDB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_MAGENTA = -0x1.313db4p126F;
    static { NAMED.put("faded magenta", -0x1.313db4p126F); LIST.add(-0x1.313db4p126F); }

    /**
     * This color constant "pale magenta" has RGBA8888 code {@code EDB8EDFF}, H 0.85490197, S 0.5803922, L 0.78431374, alpha 1.0, and chroma 0.38539043.
     * It can be represented as a packed float with the constant {@code -0x1.9129b4p126F}.
     * <pre>
     * <font style='background-color: #EDB8ED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDB8ED; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDB8ED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EDB8ED'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EDB8ED'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EDB8ED'>&nbsp;@&nbsp;</font><font style='background-color: #EDB8ED; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDB8ED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDB8ED; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_MAGENTA = -0x1.9129b4p126F;
    static { NAMED.put("pale magenta", -0x1.9129b4p126F); LIST.add(-0x1.9129b4p126F); }

    /**
     * This color constant "deep pure red" has RGBA8888 code {@code B50F0FFF}, H 0.03137255, S 1.0, L 0.3529412, alpha 1.0, and chroma 1.2637471.
     * It can be represented as a packed float with the constant {@code -0x1.b5fe1p125F}.
     * <pre>
     * <font style='background-color: #B50F0F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B50F0F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B50F0F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B50F0F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B50F0F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B50F0F'>&nbsp;@&nbsp;</font><font style='background-color: #B50F0F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B50F0F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B50F0F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_RED = -0x1.b5fe1p125F;
    static { NAMED.put("deep pure red", -0x1.b5fe1p125F); LIST.add(-0x1.b5fe1p125F); }

    /**
     * This color constant "true pure red" has RGBA8888 code {@code EE3838FF}, H 0.03137255, S 0.85490197, L 0.49803922, alpha 1.0, and chroma 1.5035862.
     * It can be represented as a packed float with the constant {@code -0x1.ffb41p125F}.
     * <pre>
     * <font style='background-color: #EE3838;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EE3838; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EE3838;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EE3838'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EE3838'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EE3838'>&nbsp;@&nbsp;</font><font style='background-color: #EE3838; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EE3838;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EE3838; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_RED = -0x1.ffb41p125F;
    static { NAMED.put("true pure red", -0x1.ffb41p125F); LIST.add(-0x1.ffb41p125F); }

    /**
     * This color constant "bright pure red" has RGBA8888 code {@code F7A5A5FF}, H 0.03137255, S 0.7411765, L 0.7294118, alpha 1.0, and chroma 0.4747884.
     * It can be represented as a packed float with the constant {@code -0x1.757a1p126F}.
     * <pre>
     * <font style='background-color: #F7A5A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7A5A5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7A5A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F7A5A5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F7A5A5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F7A5A5'>&nbsp;@&nbsp;</font><font style='background-color: #F7A5A5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7A5A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7A5A5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_RED = -0x1.757a1p126F;
    static { NAMED.put("bright pure red", -0x1.757a1p126F); LIST.add(-0x1.757a1p126F); }

    /**
     * This color constant "deep brown red" has RGBA8888 code {@code C72E1BFF}, H 0.039215688, S 0.9882353, L 0.4117647, alpha 1.0, and chroma 1.3516192.
     * It can be represented as a packed float with the constant {@code -0x1.d3f814p125F}.
     * <pre>
     * <font style='background-color: #C72E1B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C72E1B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C72E1B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C72E1B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C72E1B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C72E1B'>&nbsp;@&nbsp;</font><font style='background-color: #C72E1B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C72E1B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C72E1B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN_RED = -0x1.d3f814p125F;
    static { NAMED.put("deep brown red", -0x1.d3f814p125F); LIST.add(-0x1.d3f814p125F); }

    /**
     * This color constant "true brown red" has RGBA8888 code {@code E86757FF}, H 0.043137256, S 0.6666667, L 0.56078434, alpha 1.0, and chroma 0.9445405.
     * It can be represented as a packed float with the constant {@code -0x1.1f5416p126F}.
     * <pre>
     * <font style='background-color: #E86757;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E86757; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E86757;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E86757'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E86757'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E86757'>&nbsp;@&nbsp;</font><font style='background-color: #E86757; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E86757;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E86757; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BROWN_RED = -0x1.1f5416p126F;
    static { NAMED.put("true brown red", -0x1.1f5416p126F); LIST.add(-0x1.1f5416p126F); }

    /**
     * This color constant "bright brown red" has RGBA8888 code {@code F4B6AEFF}, H 0.050980393, S 0.6666667, L 0.76862746, alpha 1.0, and chroma 0.36460516.
     * It can be represented as a packed float with the constant {@code -0x1.89541ap126F}.
     * <pre>
     * <font style='background-color: #F4B6AE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F4B6AE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F4B6AE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F4B6AE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F4B6AE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F4B6AE'>&nbsp;@&nbsp;</font><font style='background-color: #F4B6AE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F4B6AE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F4B6AE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN_RED = -0x1.89541ap126F;
    static { NAMED.put("bright brown red", -0x1.89541ap126F); LIST.add(-0x1.89541ap126F); }

    /**
     * This color constant "deep red brown" has RGBA8888 code {@code C23710FF}, H 0.047058824, S 1.0, L 0.4117647, alpha 1.0, and chroma 1.213062.
     * It can be represented as a packed float with the constant {@code -0x1.d3fe18p125F}.
     * <pre>
     * <font style='background-color: #C23710;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C23710; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C23710;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C23710'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C23710'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C23710'>&nbsp;@&nbsp;</font><font style='background-color: #C23710; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C23710;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C23710; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED_BROWN = -0x1.d3fe18p125F;
    static { NAMED.put("deep red brown", -0x1.d3fe18p125F); LIST.add(-0x1.d3fe18p125F); }

    /**
     * This color constant "true red brown" has RGBA8888 code {@code EE663FFF}, H 0.05490196, S 0.85490197, L 0.5647059, alpha 1.0, and chroma 1.2573413.
     * It can be represented as a packed float with the constant {@code -0x1.21b41cp126F}.
     * <pre>
     * <font style='background-color: #EE663F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EE663F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EE663F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EE663F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EE663F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EE663F'>&nbsp;@&nbsp;</font><font style='background-color: #EE663F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EE663F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EE663F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_RED_BROWN = -0x1.21b41cp126F;
    static { NAMED.put("true red brown", -0x1.21b41cp126F); LIST.add(-0x1.21b41cp126F); }

    /**
     * This color constant "bright red brown" has RGBA8888 code {@code F7B8A6FF}, H 0.07450981, S 0.70980394, L 0.7764706, alpha 1.0, and chroma 0.40231362.
     * It can be represented as a packed float with the constant {@code -0x1.8d6a26p126F}.
     * <pre>
     * <font style='background-color: #F7B8A6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7B8A6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7B8A6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F7B8A6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F7B8A6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F7B8A6'>&nbsp;@&nbsp;</font><font style='background-color: #F7B8A6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7B8A6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7B8A6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED_BROWN = -0x1.8d6a26p126F;
    static { NAMED.put("bright red brown", -0x1.8d6a26p126F); LIST.add(-0x1.8d6a26p126F); }

    /**
     * This color constant "deep pure brown" has RGBA8888 code {@code CB561BFF}, H 0.0627451, S 1.0, L 0.47843137, alpha 1.0, and chroma 1.1490474.
     * It can be represented as a packed float with the constant {@code -0x1.f5fe2p125F}.
     * <pre>
     * <font style='background-color: #CB561B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CB561B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CB561B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CB561B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CB561B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CB561B'>&nbsp;@&nbsp;</font><font style='background-color: #CB561B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CB561B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CB561B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_BROWN = -0x1.f5fe2p125F;
    static { NAMED.put("deep pure brown", -0x1.f5fe2p125F); LIST.add(-0x1.f5fe2p125F); }

    /**
     * This color constant "true pure brown" has RGBA8888 code {@code E99063FF}, H 0.08235294, S 0.6745098, L 0.64705884, alpha 1.0, and chroma 0.7793732.
     * It can be represented as a packed float with the constant {@code -0x1.4b582ap126F}.
     * <pre>
     * <font style='background-color: #E99063;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E99063; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E99063;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E99063'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E99063'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E99063'>&nbsp;@&nbsp;</font><font style='background-color: #E99063; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E99063;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E99063; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_BROWN = -0x1.4b582ap126F;
    static { NAMED.put("true pure brown", -0x1.4b582ap126F); LIST.add(-0x1.4b582ap126F); }

    /**
     * This color constant "bright pure brown" has RGBA8888 code {@code F4C8B2FF}, H 0.101960786, S 0.61960787, L 0.8156863, alpha 1.0, and chroma 0.31198716.
     * It can be represented as a packed float with the constant {@code -0x1.a13c34p126F}.
     * <pre>
     * <font style='background-color: #F4C8B2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F4C8B2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F4C8B2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F4C8B2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F4C8B2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F4C8B2'>&nbsp;@&nbsp;</font><font style='background-color: #F4C8B2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F4C8B2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F4C8B2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_BROWN = -0x1.a13c34p126F;
    static { NAMED.put("bright pure brown", -0x1.a13c34p126F); LIST.add(-0x1.a13c34p126F); }

    /**
     * This color constant "deep orange brown" has RGBA8888 code {@code CC5911FF}, H 0.06666667, S 1.0, L 0.48235294, alpha 1.0, and chroma 1.109744.
     * It can be represented as a packed float with the constant {@code -0x1.f7fe22p125F}.
     * <pre>
     * <font style='background-color: #CC5911;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CC5911; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CC5911;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CC5911'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CC5911'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CC5911'>&nbsp;@&nbsp;</font><font style='background-color: #CC5911; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CC5911;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CC5911; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_BROWN = -0x1.f7fe22p125F;
    static { NAMED.put("deep orange brown", -0x1.f7fe22p125F); LIST.add(-0x1.f7fe22p125F); }

    /**
     * This color constant "true orange brown" has RGBA8888 code {@code EF8948FF}, H 0.08627451, S 0.84705883, L 0.63529414, alpha 1.0, and chroma 1.013759.
     * It can be represented as a packed float with the constant {@code -0x1.45b02cp126F}.
     * <pre>
     * <font style='background-color: #EF8948;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EF8948; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EF8948;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EF8948'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EF8948'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EF8948'>&nbsp;@&nbsp;</font><font style='background-color: #EF8948; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EF8948;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EF8948; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_ORANGE_BROWN = -0x1.45b02cp126F;
    static { NAMED.put("true orange brown", -0x1.45b02cp126F); LIST.add(-0x1.45b02cp126F); }

    /**
     * This color constant "bright orange brown" has RGBA8888 code {@code F7C7A8FF}, H 0.11372549, S 0.6784314, L 0.8156863, alpha 1.0, and chroma 0.36635128.
     * It can be represented as a packed float with the constant {@code -0x1.a15a3ap126F}.
     * <pre>
     * <font style='background-color: #F7C7A8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7C7A8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7C7A8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F7C7A8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F7C7A8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F7C7A8'>&nbsp;@&nbsp;</font><font style='background-color: #F7C7A8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7C7A8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7C7A8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE_BROWN = -0x1.a15a3ap126F;
    static { NAMED.put("bright orange brown", -0x1.a15a3ap126F); LIST.add(-0x1.a15a3ap126F); }

    /**
     * This color constant "deep brown orange" has RGBA8888 code {@code B15C18FF}, H 0.08627451, S 1.0, L 0.44705883, alpha 1.0, and chroma 0.8611697.
     * It can be represented as a packed float with the constant {@code -0x1.e5fe2cp125F}.
     * <pre>
     * <font style='background-color: #B15C18;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B15C18; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B15C18;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B15C18'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B15C18'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B15C18'>&nbsp;@&nbsp;</font><font style='background-color: #B15C18; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B15C18;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B15C18; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN_ORANGE = -0x1.e5fe2cp125F;
    static { NAMED.put("deep brown orange", -0x1.e5fe2cp125F); LIST.add(-0x1.e5fe2cp125F); }

    /**
     * This color constant "true brown orange" has RGBA8888 code {@code E58B44FF}, H 0.09803922, S 0.8627451, L 0.627451, alpha 1.0, and chroma 0.93464005.
     * It can be represented as a packed float with the constant {@code -0x1.41b832p126F}.
     * <pre>
     * <font style='background-color: #E58B44;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E58B44; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E58B44;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E58B44'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E58B44'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E58B44'>&nbsp;@&nbsp;</font><font style='background-color: #E58B44; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E58B44;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E58B44; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BROWN_ORANGE = -0x1.41b832p126F;
    static { NAMED.put("true brown orange", -0x1.41b832p126F); LIST.add(-0x1.41b832p126F); }

    /**
     * This color constant "bright brown orange" has RGBA8888 code {@code F3CAAAFF}, H 0.1254902, S 0.5882353, L 0.81960785, alpha 1.0, and chroma 0.33507466.
     * It can be represented as a packed float with the constant {@code -0x1.a32c4p126F}.
     * <pre>
     * <font style='background-color: #F3CAAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3CAAA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3CAAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F3CAAA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F3CAAA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F3CAAA'>&nbsp;@&nbsp;</font><font style='background-color: #F3CAAA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3CAAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3CAAA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN_ORANGE = -0x1.a32c4p126F;
    static { NAMED.put("bright brown orange", -0x1.a32c4p126F); LIST.add(-0x1.a32c4p126F); }

    /**
     * This color constant "deep pure orange" has RGBA8888 code {@code CE7116FF}, H 0.09411765, S 1.0, L 0.53333336, alpha 1.0, and chroma 0.95823354.
     * It can be represented as a packed float with the constant {@code -0x1.11fe3p126F}.
     * <pre>
     * <font style='background-color: #CE7116;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CE7116; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CE7116;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CE7116'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CE7116'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CE7116'>&nbsp;@&nbsp;</font><font style='background-color: #CE7116; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CE7116;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CE7116; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_ORANGE = -0x1.11fe3p126F;
    static { NAMED.put("deep pure orange", -0x1.11fe3p126F); LIST.add(-0x1.11fe3p126F); }

    /**
     * This color constant "true pure orange" has RGBA8888 code {@code EDA258FF}, H 0.11764706, S 0.7921569, L 0.69411767, alpha 1.0, and chroma 0.833931.
     * It can be represented as a packed float with the constant {@code -0x1.63943cp126F}.
     * <pre>
     * <font style='background-color: #EDA258;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDA258; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDA258;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EDA258'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EDA258'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EDA258'>&nbsp;@&nbsp;</font><font style='background-color: #EDA258; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDA258;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDA258; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_ORANGE = -0x1.63943cp126F;
    static { NAMED.put("true pure orange", -0x1.63943cp126F); LIST.add(-0x1.63943cp126F); }

    /**
     * This color constant "bright pure orange" has RGBA8888 code {@code F6D1ADFF}, H 0.13725491, S 0.627451, L 0.84313726, alpha 1.0, and chroma 0.32851037.
     * It can be represented as a packed float with the constant {@code -0x1.af4046p126F}.
     * <pre>
     * <font style='background-color: #F6D1AD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6D1AD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6D1AD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F6D1AD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F6D1AD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F6D1AD'>&nbsp;@&nbsp;</font><font style='background-color: #F6D1AD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6D1AD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6D1AD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_ORANGE = -0x1.af4046p126F;
    static { NAMED.put("bright pure orange", -0x1.af4046p126F); LIST.add(-0x1.af4046p126F); }

    /**
     * This color constant "deep saffron orange" has RGBA8888 code {@code B37024FF}, H 0.11372549, S 0.972549, L 0.49803922, alpha 1.0, and chroma 0.76976067.
     * It can be represented as a packed float with the constant {@code -0x1.fff03ap125F}.
     * <pre>
     * <font style='background-color: #B37024;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B37024; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B37024;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B37024'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B37024'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B37024'>&nbsp;@&nbsp;</font><font style='background-color: #B37024; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B37024;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B37024; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_SAFFRON_ORANGE = -0x1.fff03ap125F;
    static { NAMED.put("deep saffron orange", -0x1.fff03ap125F); LIST.add(-0x1.fff03ap125F); }

    /**
     * This color constant "true saffron orange" has RGBA8888 code {@code DC9C54FF}, H 0.1254902, S 0.7882353, L 0.65882355, alpha 1.0, and chroma 0.7600867.
     * It can be represented as a packed float with the constant {@code -0x1.51924p126F}.
     * <pre>
     * <font style='background-color: #DC9C54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DC9C54; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DC9C54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DC9C54'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DC9C54'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DC9C54'>&nbsp;@&nbsp;</font><font style='background-color: #DC9C54; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DC9C54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DC9C54; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_SAFFRON_ORANGE = -0x1.51924p126F;
    static { NAMED.put("true saffron orange", -0x1.51924p126F); LIST.add(-0x1.51924p126F); }

    /**
     * This color constant "bright saffron orange" has RGBA8888 code {@code EFD1B0FF}, H 0.14509805, S 0.4745098, L 0.8352941, alpha 1.0, and chroma 0.28333864.
     * It can be represented as a packed float with the constant {@code -0x1.aaf24ap126F}.
     * <pre>
     * <font style='background-color: #EFD1B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFD1B0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFD1B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EFD1B0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EFD1B0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EFD1B0'>&nbsp;@&nbsp;</font><font style='background-color: #EFD1B0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFD1B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFD1B0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_SAFFRON_ORANGE = -0x1.aaf24ap126F;
    static { NAMED.put("bright saffron orange", -0x1.aaf24ap126F); LIST.add(-0x1.aaf24ap126F); }

    /**
     * This color constant "deep orange saffron" has RGBA8888 code {@code C37E26FF}, H 0.11764706, S 0.98039216, L 0.5529412, alpha 1.0, and chroma 0.83755076.
     * It can be represented as a packed float with the constant {@code -0x1.1bf43cp126F}.
     * <pre>
     * <font style='background-color: #C37E26;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C37E26; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C37E26;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C37E26'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C37E26'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C37E26'>&nbsp;@&nbsp;</font><font style='background-color: #C37E26; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C37E26;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C37E26; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_SAFFRON = -0x1.1bf43cp126F;
    static { NAMED.put("deep orange saffron", -0x1.1bf43cp126F); LIST.add(-0x1.1bf43cp126F); }

    /**
     * This color constant "true orange saffron" has RGBA8888 code {@code E2AF6EFF}, H 0.14117648, S 0.6666667, L 0.7176471, alpha 1.0, and chroma 0.64815414.
     * It can be represented as a packed float with the constant {@code -0x1.6f5448p126F}.
     * <pre>
     * <font style='background-color: #E2AF6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E2AF6E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E2AF6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E2AF6E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E2AF6E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E2AF6E'>&nbsp;@&nbsp;</font><font style='background-color: #E2AF6E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E2AF6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E2AF6E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_ORANGE_SAFFRON = -0x1.6f5448p126F;
    static { NAMED.put("true orange saffron", -0x1.6f5448p126F); LIST.add(-0x1.6f5448p126F); }

    /**
     * This color constant "bright orange saffron" has RGBA8888 code {@code F1D8B9FF}, H 0.15686275, S 0.4745098, L 0.85882354, alpha 1.0, and chroma 0.26274213.
     * It can be represented as a packed float with the constant {@code -0x1.b6f25p126F}.
     * <pre>
     * <font style='background-color: #F1D8B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F1D8B9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F1D8B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F1D8B9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F1D8B9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F1D8B9'>&nbsp;@&nbsp;</font><font style='background-color: #F1D8B9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F1D8B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F1D8B9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE_SAFFRON = -0x1.b6f25p126F;
    static { NAMED.put("bright orange saffron", -0x1.b6f25p126F); LIST.add(-0x1.b6f25p126F); }

    /**
     * This color constant "deep pure saffron" has RGBA8888 code {@code AE7B31FF}, H 0.13333334, S 0.9098039, L 0.5176471, alpha 1.0, and chroma 0.67665493.
     * It can be represented as a packed float with the constant {@code -0x1.09d044p126F}.
     * <pre>
     * <font style='background-color: #AE7B31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE7B31; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE7B31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AE7B31'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AE7B31'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AE7B31'>&nbsp;@&nbsp;</font><font style='background-color: #AE7B31; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE7B31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE7B31; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_SAFFRON = -0x1.09d044p126F;
    static { NAMED.put("deep pure saffron", -0x1.09d044p126F); LIST.add(-0x1.09d044p126F); }

    /**
     * This color constant "true pure saffron" has RGBA8888 code {@code D4A868FF}, H 0.14901961, S 0.6745098, L 0.6862745, alpha 1.0, and chroma 0.6116198.
     * It can be represented as a packed float with the constant {@code -0x1.5f584cp126F}.
     * <pre>
     * <font style='background-color: #D4A868;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4A868; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4A868;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D4A868'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D4A868'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D4A868'>&nbsp;@&nbsp;</font><font style='background-color: #D4A868; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4A868;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4A868; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_SAFFRON = -0x1.5f584cp126F;
    static { NAMED.put("true pure saffron", -0x1.5f584cp126F); LIST.add(-0x1.5f584cp126F); }

    /**
     * This color constant "bright pure saffron" has RGBA8888 code {@code EAD5B7FF}, H 0.16470589, S 0.34509805, L 0.84313726, alpha 1.0, and chroma 0.2392098.
     * It can be represented as a packed float with the constant {@code -0x1.aeb054p126F}.
     * <pre>
     * <font style='background-color: #EAD5B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EAD5B7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EAD5B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EAD5B7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EAD5B7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EAD5B7'>&nbsp;@&nbsp;</font><font style='background-color: #EAD5B7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EAD5B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EAD5B7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_SAFFRON = -0x1.aeb054p126F;
    static { NAMED.put("bright pure saffron", -0x1.aeb054p126F); LIST.add(-0x1.aeb054p126F); }

    /**
     * This color constant "deep yellow saffron" has RGBA8888 code {@code 9C7F33FF}, H 0.17254902, S 0.8901961, L 0.50980395, alpha 1.0, and chroma 0.5722016.
     * It can be represented as a packed float with the constant {@code -0x1.05c658p126F}.
     * <pre>
     * <font style='background-color: #9C7F33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C7F33; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C7F33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9C7F33'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9C7F33'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9C7F33'>&nbsp;@&nbsp;</font><font style='background-color: #9C7F33; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C7F33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C7F33; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_SAFFRON = -0x1.05c658p126F;
    static { NAMED.put("deep yellow saffron", -0x1.05c658p126F); LIST.add(-0x1.05c658p126F); }

    /**
     * This color constant "true yellow saffron" has RGBA8888 code {@code CBAE60FF}, H 0.18039216, S 0.73333335, L 0.6901961, alpha 1.0, and chroma 0.6128618.
     * It can be represented as a packed float with the constant {@code -0x1.61765cp126F}.
     * <pre>
     * <font style='background-color: #CBAE60;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBAE60; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBAE60;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CBAE60'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CBAE60'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CBAE60'>&nbsp;@&nbsp;</font><font style='background-color: #CBAE60; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBAE60;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBAE60; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_YELLOW_SAFFRON = -0x1.61765cp126F;
    static { NAMED.put("true yellow saffron", -0x1.61765cp126F); LIST.add(-0x1.61765cp126F); }

    /**
     * This color constant "bright yellow saffron" has RGBA8888 code {@code E7DAB6FF}, H 0.19607843, S 0.30588236, L 0.85490197, alpha 1.0, and chroma 0.30114865.
     * It can be represented as a packed float with the constant {@code -0x1.b49c64p126F}.
     * <pre>
     * <font style='background-color: #E7DAB6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E7DAB6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E7DAB6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E7DAB6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E7DAB6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E7DAB6'>&nbsp;@&nbsp;</font><font style='background-color: #E7DAB6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E7DAB6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E7DAB6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_YELLOW_SAFFRON = -0x1.b49c64p126F;
    static { NAMED.put("bright yellow saffron", -0x1.b49c64p126F); LIST.add(-0x1.b49c64p126F); }

    /**
     * This color constant "deep saffron yellow" has RGBA8888 code {@code B6A430FF}, H 0.20784314, S 0.95686275, L 0.63529414, alpha 1.0, and chroma 0.7123911.
     * It can be represented as a packed float with the constant {@code -0x1.45e86ap126F}.
     * <pre>
     * <font style='background-color: #B6A430;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6A430; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6A430;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B6A430'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B6A430'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B6A430'>&nbsp;@&nbsp;</font><font style='background-color: #B6A430; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6A430;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6A430; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_SAFFRON_YELLOW = -0x1.45e86ap126F;
    static { NAMED.put("deep saffron yellow", -0x1.45e86ap126F); LIST.add(-0x1.45e86ap126F); }

    /**
     * This color constant "true saffron yellow" has RGBA8888 code {@code D9CB71FF}, H 0.21176471, S 0.7058824, L 0.7882353, alpha 1.0, and chroma 0.6363091.
     * It can be represented as a packed float with the constant {@code -0x1.93686cp126F}.
     * <pre>
     * <font style='background-color: #D9CB71;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9CB71; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9CB71;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D9CB71'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D9CB71'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D9CB71'>&nbsp;@&nbsp;</font><font style='background-color: #D9CB71; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9CB71;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9CB71; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_SAFFRON_YELLOW = -0x1.93686cp126F;
    static { NAMED.put("true saffron yellow", -0x1.93686cp126F); LIST.add(-0x1.93686cp126F); }

    /**
     * This color constant "bright saffron yellow" has RGBA8888 code {@code ECE5B9FF}, H 0.21568628, S 0.34117648, L 0.8901961, alpha 1.0, and chroma 0.3413584.
     * It can be represented as a packed float with the constant {@code -0x1.c6ae6ep126F}.
     * <pre>
     * <font style='background-color: #ECE5B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ECE5B9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ECE5B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ECE5B9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ECE5B9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ECE5B9'>&nbsp;@&nbsp;</font><font style='background-color: #ECE5B9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ECE5B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ECE5B9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_SAFFRON_YELLOW = -0x1.c6ae6ep126F;
    static { NAMED.put("bright saffron yellow", -0x1.c6ae6ep126F); LIST.add(-0x1.c6ae6ep126F); }

    /**
     * This color constant "deep pure yellow" has RGBA8888 code {@code CACA11FF}, H 0.23921569, S 1.0, L 0.7647059, alpha 1.0, and chroma 0.8725873.
     * It can be represented as a packed float with the constant {@code -0x1.87fe7ap126F}.
     * <pre>
     * <font style='background-color: #CACA11;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CACA11; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CACA11;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CACA11'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CACA11'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CACA11'>&nbsp;@&nbsp;</font><font style='background-color: #CACA11; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CACA11;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CACA11; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_YELLOW = -0x1.87fe7ap126F;
    static { NAMED.put("deep pure yellow", -0x1.87fe7ap126F); LIST.add(-0x1.87fe7ap126F); }

    /**
     * This color constant "true pure yellow" has RGBA8888 code {@code EFEF45FF}, H 0.23921569, S 0.92941177, L 0.9098039, alpha 1.0, and chroma 0.94486165.
     * It can be represented as a packed float with the constant {@code -0x1.d1da7ap126F}.
     * <pre>
     * <font style='background-color: #EFEF45;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFEF45; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFEF45;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EFEF45'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EFEF45'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EFEF45'>&nbsp;@&nbsp;</font><font style='background-color: #EFEF45; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFEF45;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFEF45; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_YELLOW = -0x1.d1da7ap126F;
    static { NAMED.put("true pure yellow", -0x1.d1da7ap126F); LIST.add(-0x1.d1da7ap126F); }

    /**
     * This color constant "bright pure yellow" has RGBA8888 code {@code F7F7A8FF}, H 0.23921569, S 0.52156866, L 0.9490196, alpha 1.0, and chroma 0.5499256.
     * It can be represented as a packed float with the constant {@code -0x1.e50a7ap126F}.
     * <pre>
     * <font style='background-color: #F7F7A8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7F7A8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7F7A8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F7F7A8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F7F7A8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F7F7A8'>&nbsp;@&nbsp;</font><font style='background-color: #F7F7A8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7F7A8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7F7A8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_YELLOW = -0x1.e50a7ap126F;
    static { NAMED.put("bright pure yellow", -0x1.e50a7ap126F); LIST.add(-0x1.e50a7ap126F); }

    /**
     * This color constant "deep lime yellow" has RGBA8888 code {@code 9FAE17FF}, H 0.25882354, S 1.0, L 0.6431373, alpha 1.0, and chroma 0.75690347.
     * It can be represented as a packed float with the constant {@code -0x1.49fe84p126F}.
     * <pre>
     * <font style='background-color: #9FAE17;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9FAE17; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9FAE17;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9FAE17'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9FAE17'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9FAE17'>&nbsp;@&nbsp;</font><font style='background-color: #9FAE17; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9FAE17;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9FAE17; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_LIME_YELLOW = -0x1.49fe84p126F;
    static { NAMED.put("deep lime yellow", -0x1.49fe84p126F); LIST.add(-0x1.49fe84p126F); }

    /**
     * This color constant "true lime yellow" has RGBA8888 code {@code D4E542FF}, H 0.25882354, S 0.92941177, L 0.85490197, alpha 1.0, and chroma 0.9076667.
     * It can be represented as a packed float with the constant {@code -0x1.b5da84p126F}.
     * <pre>
     * <font style='background-color: #D4E542;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4E542; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4E542;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D4E542'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D4E542'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D4E542'>&nbsp;@&nbsp;</font><font style='background-color: #D4E542; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4E542;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4E542; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_LIME_YELLOW = -0x1.b5da84p126F;
    static { NAMED.put("true lime yellow", -0x1.b5da84p126F); LIST.add(-0x1.b5da84p126F); }

    /**
     * This color constant "bright lime yellow" has RGBA8888 code {@code EBF3A9FF}, H 0.2509804, S 0.49411765, L 0.92941177, alpha 1.0, and chroma 0.5150827.
     * It can be represented as a packed float with the constant {@code -0x1.dafc8p126F}.
     * <pre>
     * <font style='background-color: #EBF3A9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBF3A9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBF3A9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EBF3A9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EBF3A9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EBF3A9'>&nbsp;@&nbsp;</font><font style='background-color: #EBF3A9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBF3A9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBF3A9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_LIME_YELLOW = -0x1.dafc8p126F;
    static { NAMED.put("bright lime yellow", -0x1.dafc8p126F); LIST.add(-0x1.dafc8p126F); }

    /**
     * This color constant "deep yellow lime" has RGBA8888 code {@code AAD111FF}, H 0.28235295, S 1.0, L 0.7607843, alpha 1.0, and chroma 0.91474456.
     * It can be represented as a packed float with the constant {@code -0x1.85fe9p126F}.
     * <pre>
     * <font style='background-color: #AAD111;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AAD111; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AAD111;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AAD111'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AAD111'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AAD111'>&nbsp;@&nbsp;</font><font style='background-color: #AAD111; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AAD111;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AAD111; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_LIME = -0x1.85fe9p126F;
    static { NAMED.put("deep yellow lime", -0x1.85fe9p126F); LIST.add(-0x1.85fe9p126F); }

    /**
     * This color constant "true yellow lime" has RGBA8888 code {@code D0F051FF}, H 0.27450982, S 0.8901961, L 0.8862745, alpha 1.0, and chroma 0.917604.
     * It can be represented as a packed float with the constant {@code -0x1.c5c68cp126F}.
     * <pre>
     * <font style='background-color: #D0F051;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0F051; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0F051;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D0F051'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D0F051'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D0F051'>&nbsp;@&nbsp;</font><font style='background-color: #D0F051; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0F051;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0F051; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_YELLOW_LIME = -0x1.c5c68cp126F;
    static { NAMED.put("true yellow lime", -0x1.c5c68cp126F); LIST.add(-0x1.c5c68cp126F); }

    /**
     * This color constant "bright yellow lime" has RGBA8888 code {@code E8F7ABFF}, H 0.26666668, S 0.49411765, L 0.9372549, alpha 1.0, and chroma 0.52796555.
     * It can be represented as a packed float with the constant {@code -0x1.defc88p126F}.
     * <pre>
     * <font style='background-color: #E8F7AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E8F7AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E8F7AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E8F7AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E8F7AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E8F7AB'>&nbsp;@&nbsp;</font><font style='background-color: #E8F7AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E8F7AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E8F7AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_YELLOW_LIME = -0x1.defc88p126F;
    static { NAMED.put("bright yellow lime", -0x1.defc88p126F); LIST.add(-0x1.defc88p126F); }

    /**
     * This color constant "deep pure lime" has RGBA8888 code {@code 8ABB19FF}, H 0.29803923, S 1.0, L 0.67058825, alpha 1.0, and chroma 0.84855735.
     * It can be represented as a packed float with the constant {@code -0x1.57fe98p126F}.
     * <pre>
     * <font style='background-color: #8ABB19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8ABB19; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8ABB19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8ABB19'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8ABB19'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8ABB19'>&nbsp;@&nbsp;</font><font style='background-color: #8ABB19; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8ABB19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8ABB19; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_LIME = -0x1.57fe98p126F;
    static { NAMED.put("deep pure lime", -0x1.57fe98p126F); LIST.add(-0x1.57fe98p126F); }

    /**
     * This color constant "true pure lime" has RGBA8888 code {@code B6E648FF}, H 0.2901961, S 0.9098039, L 0.8352941, alpha 1.0, and chroma 0.92033684.
     * It can be represented as a packed float with the constant {@code -0x1.abd094p126F}.
     * <pre>
     * <font style='background-color: #B6E648;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6E648; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6E648;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B6E648'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B6E648'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B6E648'>&nbsp;@&nbsp;</font><font style='background-color: #B6E648; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6E648;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6E648; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_LIME = -0x1.abd094p126F;
    static { NAMED.put("true pure lime", -0x1.abd094p126F); LIST.add(-0x1.abd094p126F); }

    /**
     * This color constant "bright pure lime" has RGBA8888 code {@code DDF3ABFF}, H 0.2784314, S 0.47058824, L 0.91764706, alpha 1.0, and chroma 0.50358886.
     * It can be represented as a packed float with the constant {@code -0x1.d4f08ep126F}.
     * <pre>
     * <font style='background-color: #DDF3AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DDF3AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DDF3AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DDF3AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DDF3AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DDF3AB'>&nbsp;@&nbsp;</font><font style='background-color: #DDF3AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DDF3AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DDF3AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_LIME = -0x1.d4f08ep126F;
    static { NAMED.put("bright pure lime", -0x1.d4f08ep126F); LIST.add(-0x1.d4f08ep126F); }

    /**
     * This color constant "deep green lime" has RGBA8888 code {@code 6DD612FF}, H 0.32941177, S 1.0, L 0.7411765, alpha 1.0, and chroma 1.0402648.
     * It can be represented as a packed float with the constant {@code -0x1.7bfea8p126F}.
     * <pre>
     * <font style='background-color: #6DD612;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6DD612; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6DD612;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6DD612'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6DD612'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6DD612'>&nbsp;@&nbsp;</font><font style='background-color: #6DD612; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6DD612;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6DD612; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREEN_LIME = -0x1.7bfea8p126F;
    static { NAMED.put("deep green lime", -0x1.7bfea8p126F); LIST.add(-0x1.7bfea8p126F); }

    /**
     * This color constant "true green lime" has RGBA8888 code {@code A2F15DFF}, H 0.31764707, S 0.84313726, L 0.85882354, alpha 1.0, and chroma 0.9518587.
     * It can be represented as a packed float with the constant {@code -0x1.b7aea2p126F}.
     * <pre>
     * <font style='background-color: #A2F15D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2F15D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2F15D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A2F15D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A2F15D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A2F15D'>&nbsp;@&nbsp;</font><font style='background-color: #A2F15D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2F15D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2F15D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_GREEN_LIME = -0x1.b7aea2p126F;
    static { NAMED.put("true green lime", -0x1.b7aea2p126F); LIST.add(-0x1.b7aea2p126F); }

    /**
     * This color constant "bright green lime" has RGBA8888 code {@code D1F8B0FF}, H 0.30980393, S 0.49019608, L 0.9254902, alpha 1.0, and chroma 0.41412964.
     * It can be represented as a packed float with the constant {@code -0x1.d8fa9ep126F}.
     * <pre>
     * <font style='background-color: #D1F8B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D1F8B0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D1F8B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D1F8B0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D1F8B0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D1F8B0'>&nbsp;@&nbsp;</font><font style='background-color: #D1F8B0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D1F8B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D1F8B0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_GREEN_LIME = -0x1.d8fa9ep126F;
    static { NAMED.put("bright green lime", -0x1.d8fa9ep126F); LIST.add(-0x1.d8fa9ep126F); }

    /**
     * This color constant "deep lime green" has RGBA8888 code {@code 42C41AFF}, H 0.34509805, S 1.0, L 0.6666667, alpha 1.0, and chroma 1.0220064.
     * It can be represented as a packed float with the constant {@code -0x1.55febp126F}.
     * <pre>
     * <font style='background-color: #42C41A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #42C41A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #42C41A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #42C41A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #42C41A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #42C41A'>&nbsp;@&nbsp;</font><font style='background-color: #42C41A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #42C41A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #42C41A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_LIME_GREEN = -0x1.55febp126F;
    static { NAMED.put("deep lime green", -0x1.55febp126F); LIST.add(-0x1.55febp126F); }

    /**
     * This color constant "true lime green" has RGBA8888 code {@code 74E751FF}, H 0.34117648, S 0.87058824, L 0.8039216, alpha 1.0, and chroma 1.0306169.
     * It can be represented as a packed float with the constant {@code -0x1.9bbcaep126F}.
     * <pre>
     * <font style='background-color: #74E751;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #74E751; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #74E751;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #74E751'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #74E751'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #74E751'>&nbsp;@&nbsp;</font><font style='background-color: #74E751; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #74E751;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #74E751; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_LIME_GREEN = -0x1.9bbcaep126F;
    static { NAMED.put("true lime green", -0x1.9bbcaep126F); LIST.add(-0x1.9bbcaep126F); }

    /**
     * This color constant "bright lime green" has RGBA8888 code {@code BDF3ADFF}, H 0.33333334, S 0.42745098, L 0.89411765, alpha 1.0, and chroma 0.46046954.
     * It can be represented as a packed float with the constant {@code -0x1.c8daaap126F}.
     * <pre>
     * <font style='background-color: #BDF3AD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BDF3AD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BDF3AD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BDF3AD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BDF3AD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BDF3AD'>&nbsp;@&nbsp;</font><font style='background-color: #BDF3AD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BDF3AD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BDF3AD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_LIME_GREEN = -0x1.c8daaap126F;
    static { NAMED.put("bright lime green", -0x1.c8daaap126F); LIST.add(-0x1.c8daaap126F); }

    /**
     * This color constant "deep pure green" has RGBA8888 code {@code 0FBB0FFF}, H 0.3529412, S 1.0, L 0.627451, alpha 1.0, and chroma 1.011715.
     * It can be represented as a packed float with the constant {@code -0x1.41feb4p126F}.
     * <pre>
     * <font style='background-color: #0FBB0F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0FBB0F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0FBB0F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0FBB0F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0FBB0F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0FBB0F'>&nbsp;@&nbsp;</font><font style='background-color: #0FBB0F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0FBB0F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0FBB0F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_GREEN = -0x1.41feb4p126F;
    static { NAMED.put("deep pure green", -0x1.41feb4p126F); LIST.add(-0x1.41feb4p126F); }

    /**
     * This color constant "true pure green" has RGBA8888 code {@code 3BEE3BFF}, H 0.3529412, S 0.94509804, L 0.80784315, alpha 1.0, and chroma 1.2005854.
     * It can be represented as a packed float with the constant {@code -0x1.9de2b4p126F}.
     * <pre>
     * <font style='background-color: #3BEE3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3BEE3B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3BEE3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3BEE3B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3BEE3B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3BEE3B'>&nbsp;@&nbsp;</font><font style='background-color: #3BEE3B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3BEE3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3BEE3B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_GREEN = -0x1.9de2b4p126F;
    static { NAMED.put("true pure green", -0x1.9de2b4p126F); LIST.add(-0x1.9de2b4p126F); }

    /**
     * This color constant "bright pure green" has RGBA8888 code {@code A6F7A6FF}, H 0.3529412, S 0.5529412, L 0.8901961, alpha 1.0, and chroma 0.54715025.
     * It can be represented as a packed float with the constant {@code -0x1.c71ab4p126F}.
     * <pre>
     * <font style='background-color: #A6F7A6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A6F7A6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A6F7A6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A6F7A6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A6F7A6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A6F7A6'>&nbsp;@&nbsp;</font><font style='background-color: #A6F7A6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A6F7A6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A6F7A6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_GREEN = -0x1.c71ab4p126F;
    static { NAMED.put("bright pure green", -0x1.c71ab4p126F); LIST.add(-0x1.c71ab4p126F); }

    /**
     * This color constant "deep cyan green" has RGBA8888 code {@code 1BC955FF}, H 0.36862746, S 1.0, L 0.68235296, alpha 1.0, and chroma 0.96603405.
     * It can be represented as a packed float with the constant {@code -0x1.5dfebcp126F}.
     * <pre>
     * <font style='background-color: #1BC955;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1BC955; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1BC955;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1BC955'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1BC955'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1BC955'>&nbsp;@&nbsp;</font><font style='background-color: #1BC955; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1BC955;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1BC955; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_CYAN_GREEN = -0x1.5dfebcp126F;
    static { NAMED.put("deep cyan green", -0x1.5dfebcp126F); LIST.add(-0x1.5dfebcp126F); }

    /**
     * This color constant "true cyan green" has RGBA8888 code {@code 5EE98CFF}, H 0.38039216, S 0.827451, L 0.8117647, alpha 1.0, and chroma 0.84849197.
     * It can be represented as a packed float with the constant {@code -0x1.9fa6c2p126F}.
     * <pre>
     * <font style='background-color: #5EE98C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5EE98C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5EE98C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5EE98C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5EE98C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5EE98C'>&nbsp;@&nbsp;</font><font style='background-color: #5EE98C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5EE98C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5EE98C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_CYAN_GREEN = -0x1.9fa6c2p126F;
    static { NAMED.put("true cyan green", -0x1.9fa6c2p126F); LIST.add(-0x1.9fa6c2p126F); }

    /**
     * This color constant "bright cyan green" has RGBA8888 code {@code B0F4C6FF}, H 0.3882353, S 0.45882353, L 0.89411765, alpha 1.0, and chroma 0.36716747.
     * It can be represented as a packed float with the constant {@code -0x1.c8eac6p126F}.
     * <pre>
     * <font style='background-color: #B0F4C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0F4C6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0F4C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B0F4C6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B0F4C6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B0F4C6'>&nbsp;@&nbsp;</font><font style='background-color: #B0F4C6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0F4C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0F4C6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_CYAN_GREEN = -0x1.c8eac6p126F;
    static { NAMED.put("bright cyan green", -0x1.c8eac6p126F); LIST.add(-0x1.c8eac6p126F); }

    /**
     * This color constant "deep green cyan" has RGBA8888 code {@code 10C88BFF}, H 0.41568628, S 1.0, L 0.6862745, alpha 1.0, and chroma 0.712227.
     * It can be represented as a packed float with the constant {@code -0x1.5ffed4p126F}.
     * <pre>
     * <font style='background-color: #10C88B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #10C88B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #10C88B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #10C88B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #10C88B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #10C88B'>&nbsp;@&nbsp;</font><font style='background-color: #10C88B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #10C88B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #10C88B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREEN_CYAN = -0x1.5ffed4p126F;
    static { NAMED.put("deep green cyan", -0x1.5ffed4p126F); LIST.add(-0x1.5ffed4p126F); }

    /**
     * This color constant "true green cyan" has RGBA8888 code {@code 43EFB5FF}, H 0.42745098, S 0.93333334, L 0.83137256, alpha 1.0, and chroma 0.7487075.
     * It can be represented as a packed float with the constant {@code -0x1.a9dcdap126F}.
     * <pre>
     * <font style='background-color: #43EFB5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #43EFB5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #43EFB5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #43EFB5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #43EFB5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #43EFB5'>&nbsp;@&nbsp;</font><font style='background-color: #43EFB5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #43EFB5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #43EFB5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_GREEN_CYAN = -0x1.a9dcdap126F;
    static { NAMED.put("true green cyan", -0x1.a9dcdap126F); LIST.add(-0x1.a9dcdap126F); }

    /**
     * This color constant "bright green cyan" has RGBA8888 code {@code A7F7DDFF}, H 0.4509804, S 0.54901963, L 0.90588236, alpha 1.0, and chroma 0.33533213.
     * It can be represented as a packed float with the constant {@code -0x1.cf18e6p126F}.
     * <pre>
     * <font style='background-color: #A7F7DD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A7F7DD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A7F7DD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A7F7DD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A7F7DD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A7F7DD'>&nbsp;@&nbsp;</font><font style='background-color: #A7F7DD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A7F7DD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A7F7DD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_GREEN_CYAN = -0x1.cf18e6p126F;
    static { NAMED.put("bright green cyan", -0x1.cf18e6p126F); LIST.add(-0x1.cf18e6p126F); }

    /**
     * This color constant "deep pure cyan" has RGBA8888 code {@code 1CCDCDFF}, H 0.53333336, S 1.0, L 0.72156864, alpha 1.0, and chroma 0.5941716.
     * It can be represented as a packed float with the constant {@code -0x1.71ff1p126F}.
     * <pre>
     * <font style='background-color: #1CCDCD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1CCDCD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1CCDCD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1CCDCD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1CCDCD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1CCDCD'>&nbsp;@&nbsp;</font><font style='background-color: #1CCDCD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1CCDCD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1CCDCD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_CYAN = -0x1.71ff1p126F;
    static { NAMED.put("deep pure cyan", -0x1.71ff1p126F); LIST.add(-0x1.71ff1p126F); }

    /**
     * This color constant "true pure cyan" has RGBA8888 code {@code 68EAEAFF}, H 0.53333336, S 0.81960785, L 0.8392157, alpha 1.0, and chroma 0.5569707.
     * It can be represented as a packed float with the constant {@code -0x1.ada31p126F}.
     * <pre>
     * <font style='background-color: #68EAEA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #68EAEA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #68EAEA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #68EAEA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #68EAEA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #68EAEA'>&nbsp;@&nbsp;</font><font style='background-color: #68EAEA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #68EAEA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #68EAEA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_CYAN = -0x1.ada31p126F;
    static { NAMED.put("true pure cyan", -0x1.ada31p126F); LIST.add(-0x1.ada31p126F); }

    /**
     * This color constant "bright pure cyan" has RGBA8888 code {@code B7F5F5FF}, H 0.53333336, S 0.4509804, L 0.9137255, alpha 1.0, and chroma 0.27077296.
     * It can be represented as a packed float with the constant {@code -0x1.d2e71p126F}.
     * <pre>
     * <font style='background-color: #B7F5F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B7F5F5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B7F5F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B7F5F5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B7F5F5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B7F5F5'>&nbsp;@&nbsp;</font><font style='background-color: #B7F5F5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B7F5F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B7F5F5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_CYAN = -0x1.d2e71p126F;
    static { NAMED.put("bright pure cyan", -0x1.d2e71p126F); LIST.add(-0x1.d2e71p126F); }

    /**
     * This color constant "deep blue cyan" has RGBA8888 code {@code 1190D0FF}, H 0.67058825, S 1.0, L 0.5294118, alpha 1.0, and chroma 0.7634575.
     * It can be represented as a packed float with the constant {@code -0x1.0fff56p126F}.
     * <pre>
     * <font style='background-color: #1190D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1190D0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1190D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1190D0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1190D0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1190D0'>&nbsp;@&nbsp;</font><font style='background-color: #1190D0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1190D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1190D0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE_CYAN = -0x1.0fff56p126F;
    static { NAMED.put("deep blue cyan", -0x1.0fff56p126F); LIST.add(-0x1.0fff56p126F); }

    /**
     * This color constant "true blue cyan" has RGBA8888 code {@code 4EBAF0FF}, H 0.6509804, S 0.8862745, L 0.6862745, alpha 1.0, and chroma 0.73823124.
     * It can be represented as a packed float with the constant {@code -0x1.5fc54cp126F}.
     * <pre>
     * <font style='background-color: #4EBAF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4EBAF0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4EBAF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4EBAF0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4EBAF0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4EBAF0'>&nbsp;@&nbsp;</font><font style='background-color: #4EBAF0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4EBAF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4EBAF0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BLUE_CYAN = -0x1.5fc54cp126F;
    static { NAMED.put("true blue cyan", -0x1.5fc54cp126F); LIST.add(-0x1.5fc54cp126F); }

    /**
     * This color constant "bright blue cyan" has RGBA8888 code {@code AADDF7FF}, H 0.627451, S 0.69803923, L 0.8352941, alpha 1.0, and chroma 0.32480642.
     * It can be represented as a packed float with the constant {@code -0x1.ab654p126F}.
     * <pre>
     * <font style='background-color: #AADDF7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AADDF7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AADDF7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AADDF7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AADDF7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AADDF7'>&nbsp;@&nbsp;</font><font style='background-color: #AADDF7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AADDF7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AADDF7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE_CYAN = -0x1.ab654p126F;
    static { NAMED.put("bright blue cyan", -0x1.ab654p126F); LIST.add(-0x1.ab654p126F); }

    /**
     * This color constant "deep cyan blue" has RGBA8888 code {@code 194EB8FF}, H 0.72156864, S 1.0, L 0.33333334, alpha 1.0, and chroma 0.9602463.
     * It can be represented as a packed float with the constant {@code -0x1.abff7p125F}.
     * <pre>
     * <font style='background-color: #194EB8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #194EB8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #194EB8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #194EB8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #194EB8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #194EB8'>&nbsp;@&nbsp;</font><font style='background-color: #194EB8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #194EB8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #194EB8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_CYAN_BLUE = -0x1.abff7p125F;
    static { NAMED.put("deep cyan blue", -0x1.abff7p125F); LIST.add(-0x1.abff7p125F); }

    /**
     * This color constant "true cyan blue" has RGBA8888 code {@code 477CE5FF}, H 0.7137255, S 0.84313726, L 0.49803922, alpha 1.0, and chroma 1.001549.
     * It can be represented as a packed float with the constant {@code -0x1.ffaf6cp125F}.
     * <pre>
     * <font style='background-color: #477CE5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #477CE5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #477CE5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #477CE5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #477CE5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #477CE5'>&nbsp;@&nbsp;</font><font style='background-color: #477CE5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #477CE5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #477CE5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_CYAN_BLUE = -0x1.ffaf6cp125F;
    static { NAMED.put("true cyan blue", -0x1.ffaf6cp125F); LIST.add(-0x1.ffaf6cp125F); }

    /**
     * This color constant "bright cyan blue" has RGBA8888 code {@code AAC2F3FF}, H 0.69803923, S 0.7058824, L 0.75686276, alpha 1.0, and chroma 0.4015625.
     * It can be represented as a packed float with the constant {@code -0x1.836964p126F}.
     * <pre>
     * <font style='background-color: #AAC2F3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AAC2F3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AAC2F3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AAC2F3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AAC2F3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AAC2F3'>&nbsp;@&nbsp;</font><font style='background-color: #AAC2F3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AAC2F3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AAC2F3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_CYAN_BLUE = -0x1.836964p126F;
    static { NAMED.put("bright cyan blue", -0x1.836964p126F); LIST.add(-0x1.836964p126F); }

    /**
     * This color constant "deep pure blue" has RGBA8888 code {@code 1111D5FF}, H 0.7411765, S 1.0, L 0.25490198, alpha 1.0, and chroma 1.089597.
     * It can be represented as a packed float with the constant {@code -0x1.83ff7ap125F}.
     * <pre>
     * <font style='background-color: #1111D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1111D5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1111D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1111D5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1111D5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1111D5'>&nbsp;@&nbsp;</font><font style='background-color: #1111D5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1111D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1111D5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_BLUE = -0x1.83ff7ap125F;
    static { NAMED.put("deep pure blue", -0x1.83ff7ap125F); LIST.add(-0x1.83ff7ap125F); }

    /**
     * This color constant "true pure blue" has RGBA8888 code {@code 5B5BF1FF}, H 0.7411765, S 0.8901961, L 0.43529412, alpha 1.0, and chroma 1.1369853.
     * It can be represented as a packed float with the constant {@code -0x1.dfc77ap125F}.
     * <pre>
     * <font style='background-color: #5B5BF1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B5BF1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B5BF1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5B5BF1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5B5BF1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5B5BF1'>&nbsp;@&nbsp;</font><font style='background-color: #5B5BF1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B5BF1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B5BF1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_BLUE = -0x1.dfc77ap125F;
    static { NAMED.put("true pure blue", -0x1.dfc77ap125F); LIST.add(-0x1.dfc77ap125F); }

    /**
     * This color constant "bright pure blue" has RGBA8888 code {@code AEAEF8FF}, H 0.7411765, S 0.80784315, L 0.7058824, alpha 1.0, and chroma 0.5498893.
     * It can be represented as a packed float with the constant {@code -0x1.699d7ap126F}.
     * <pre>
     * <font style='background-color: #AEAEF8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AEAEF8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AEAEF8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AEAEF8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AEAEF8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AEAEF8'>&nbsp;@&nbsp;</font><font style='background-color: #AEAEF8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AEAEF8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AEAEF8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_BLUE = -0x1.699d7ap126F;
    static { NAMED.put("bright pure blue", -0x1.699d7ap126F); LIST.add(-0x1.699d7ap126F); }

    /**
     * This color constant "deep violet blue" has RGBA8888 code {@code 341AC2FF}, H 0.74509805, S 1.0, L 0.25490198, alpha 1.0, and chroma 1.0516629.
     * It can be represented as a packed float with the constant {@code -0x1.83ff7cp125F}.
     * <pre>
     * <font style='background-color: #341AC2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #341AC2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #341AC2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #341AC2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #341AC2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #341AC2'>&nbsp;@&nbsp;</font><font style='background-color: #341AC2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #341AC2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #341AC2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET_BLUE = -0x1.83ff7cp125F;
    static { NAMED.put("deep violet blue", -0x1.83ff7cp125F); LIST.add(-0x1.83ff7cp125F); }

    /**
     * This color constant "true violet blue" has RGBA8888 code {@code 664FE7FF}, H 0.7490196, S 0.84705883, L 0.4117647, alpha 1.0, and chroma 1.1066986.
     * It can be represented as a packed float with the constant {@code -0x1.d3b17ep125F}.
     * <pre>
     * <font style='background-color: #664FE7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #664FE7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #664FE7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #664FE7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #664FE7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #664FE7'>&nbsp;@&nbsp;</font><font style='background-color: #664FE7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #664FE7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #664FE7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_VIOLET_BLUE = -0x1.d3b17ep125F;
    static { NAMED.put("true violet blue", -0x1.d3b17ep125F); LIST.add(-0x1.d3b17ep125F); }

    /**
     * This color constant "bright violet blue" has RGBA8888 code {@code B7ACF3FF}, H 0.7529412, S 0.74509805, L 0.7058824, alpha 1.0, and chroma 0.50972825.
     * It can be represented as a packed float with the constant {@code -0x1.697d8p126F}.
     * <pre>
     * <font style='background-color: #B7ACF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B7ACF3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B7ACF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B7ACF3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B7ACF3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B7ACF3'>&nbsp;@&nbsp;</font><font style='background-color: #B7ACF3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B7ACF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B7ACF3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_VIOLET_BLUE = -0x1.697d8p126F;
    static { NAMED.put("bright violet blue", -0x1.697d8p126F); LIST.add(-0x1.697d8p126F); }

    /**
     * This color constant "deep blue violet" has RGBA8888 code {@code 430FB8FF}, H 0.7529412, S 1.0, L 0.24705882, alpha 1.0, and chroma 0.9540998.
     * It can be represented as a packed float with the constant {@code -0x1.7fff8p125F}.
     * <pre>
     * <font style='background-color: #430FB8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #430FB8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #430FB8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #430FB8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #430FB8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #430FB8'>&nbsp;@&nbsp;</font><font style='background-color: #430FB8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #430FB8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #430FB8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE_VIOLET = -0x1.7fff8p125F;
    static { NAMED.put("deep blue violet", -0x1.7fff8p125F); LIST.add(-0x1.7fff8p125F); }

    /**
     * This color constant "true blue violet" has RGBA8888 code {@code 713AEEFF}, H 0.75686276, S 0.90588236, L 0.39215687, alpha 1.0, and chroma 1.2030116.
     * It can be represented as a packed float with the constant {@code -0x1.c9cf82p125F}.
     * <pre>
     * <font style='background-color: #713AEE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #713AEE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #713AEE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #713AEE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #713AEE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #713AEE'>&nbsp;@&nbsp;</font><font style='background-color: #713AEE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #713AEE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #713AEE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BLUE_VIOLET = -0x1.c9cf82p125F;
    static { NAMED.put("true blue violet", -0x1.c9cf82p125F); LIST.add(-0x1.c9cf82p125F); }

    /**
     * This color constant "bright blue violet" has RGBA8888 code {@code BEA5F7FF}, H 0.76862746, S 0.8, L 0.69411767, alpha 1.0, and chroma 0.5797391.
     * It can be represented as a packed float with the constant {@code -0x1.639988p126F}.
     * <pre>
     * <font style='background-color: #BEA5F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BEA5F7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BEA5F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BEA5F7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BEA5F7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BEA5F7'>&nbsp;@&nbsp;</font><font style='background-color: #BEA5F7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BEA5F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BEA5F7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE_VIOLET = -0x1.639988p126F;
    static { NAMED.put("bright blue violet", -0x1.639988p126F); LIST.add(-0x1.639988p126F); }

    /**
     * This color constant "deep pure violet" has RGBA8888 code {@code 6A1BC8FF}, H 0.76862746, S 1.0, L 0.3137255, alpha 1.0, and chroma 1.0844078.
     * It can be represented as a packed float with the constant {@code -0x1.a1ff88p125F}.
     * <pre>
     * <font style='background-color: #6A1BC8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A1BC8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A1BC8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6A1BC8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6A1BC8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6A1BC8'>&nbsp;@&nbsp;</font><font style='background-color: #6A1BC8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A1BC8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A1BC8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_VIOLET = -0x1.a1ff88p125F;
    static { NAMED.put("deep pure violet", -0x1.a1ff88p125F); LIST.add(-0x1.a1ff88p125F); }

    /**
     * This color constant "true pure violet" has RGBA8888 code {@code 9C5BE8FF}, H 0.7764706, S 0.79607844, L 0.4862745, alpha 1.0, and chroma 0.97681844.
     * It can be represented as a packed float with the constant {@code -0x1.f9978cp125F}.
     * <pre>
     * <font style='background-color: #9C5BE8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C5BE8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C5BE8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9C5BE8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9C5BE8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9C5BE8'>&nbsp;@&nbsp;</font><font style='background-color: #9C5BE8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C5BE8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C5BE8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_VIOLET = -0x1.f9978cp125F;
    static { NAMED.put("true pure violet", -0x1.f9978cp125F); LIST.add(-0x1.f9978cp125F); }

    /**
     * This color constant "bright pure violet" has RGBA8888 code {@code CEAFF4FF}, H 0.7882353, S 0.7372549, L 0.73333335, alpha 1.0, and chroma 0.47640046.
     * It can be represented as a packed float with the constant {@code -0x1.777992p126F}.
     * <pre>
     * <font style='background-color: #CEAFF4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CEAFF4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CEAFF4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CEAFF4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CEAFF4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CEAFF4'>&nbsp;@&nbsp;</font><font style='background-color: #CEAFF4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CEAFF4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CEAFF4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_VIOLET = -0x1.777992p126F;
    static { NAMED.put("bright pure violet", -0x1.777992p126F); LIST.add(-0x1.777992p126F); }

    /**
     * This color constant "deep purple violet" has RGBA8888 code {@code 7510C5FF}, H 0.7764706, S 1.0, L 0.3137255, alpha 1.0, and chroma 1.0325358.
     * It can be represented as a packed float with the constant {@code -0x1.a1ff8cp125F}.
     * <pre>
     * <font style='background-color: #7510C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7510C5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7510C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7510C5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7510C5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7510C5'>&nbsp;@&nbsp;</font><font style='background-color: #7510C5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7510C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7510C5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE_VIOLET = -0x1.a1ff8cp125F;
    static { NAMED.put("deep purple violet", -0x1.a1ff8cp125F); LIST.add(-0x1.a1ff8cp125F); }

    /**
     * This color constant "true purple violet" has RGBA8888 code {@code A141EFFF}, H 0.78431374, S 0.8784314, L 0.45490196, alpha 1.0, and chroma 1.1395589.
     * It can be represented as a packed float with the constant {@code -0x1.e9c19p125F}.
     * <pre>
     * <font style='background-color: #A141EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A141EF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A141EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A141EF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A141EF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A141EF'>&nbsp;@&nbsp;</font><font style='background-color: #A141EF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A141EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A141EF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURPLE_VIOLET = -0x1.e9c19p125F;
    static { NAMED.put("true purple violet", -0x1.e9c19p125F); LIST.add(-0x1.e9c19p125F); }

    /**
     * This color constant "bright purple violet" has RGBA8888 code {@code D3A7F7FF}, H 0.8, S 0.7882353, L 0.72156864, alpha 1.0, and chroma 0.5488968.
     * It can be represented as a packed float with the constant {@code -0x1.719398p126F}.
     * <pre>
     * <font style='background-color: #D3A7F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3A7F7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3A7F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D3A7F7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D3A7F7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D3A7F7'>&nbsp;@&nbsp;</font><font style='background-color: #D3A7F7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3A7F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3A7F7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURPLE_VIOLET = -0x1.719398p126F;
    static { NAMED.put("bright purple violet", -0x1.719398p126F); LIST.add(-0x1.719398p126F); }

    /**
     * This color constant "deep violet purple" has RGBA8888 code {@code 8F1BCDFF}, H 0.7921569, S 1.0, L 0.3647059, alpha 1.0, and chroma 1.1005801.
     * It can be represented as a packed float with the constant {@code -0x1.bbff94p125F}.
     * <pre>
     * <font style='background-color: #8F1BCD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F1BCD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F1BCD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F1BCD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F1BCD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F1BCD'>&nbsp;@&nbsp;</font><font style='background-color: #8F1BCD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F1BCD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F1BCD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET_PURPLE = -0x1.bbff94p125F;
    static { NAMED.put("deep violet purple", -0x1.bbff94p125F); LIST.add(-0x1.bbff94p125F); }

    /**
     * This color constant "true violet purple" has RGBA8888 code {@code BC66EAFF}, H 0.8039216, S 0.76862746, L 0.54509807, alpha 1.0, and chroma 0.89912623.
     * It can be represented as a packed float with the constant {@code -0x1.17899ap126F}.
     * <pre>
     * <font style='background-color: #BC66EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BC66EA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BC66EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BC66EA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BC66EA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BC66EA'>&nbsp;@&nbsp;</font><font style='background-color: #BC66EA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BC66EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BC66EA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_VIOLET_PURPLE = -0x1.17899ap126F;
    static { NAMED.put("true violet purple", -0x1.17899ap126F); LIST.add(-0x1.17899ap126F); }

    /**
     * This color constant "bright violet purple" has RGBA8888 code {@code DEB4F4FF}, H 0.8117647, S 0.7176471, L 0.7647059, alpha 1.0, and chroma 0.43160442.
     * It can be represented as a packed float with the constant {@code -0x1.876f9ep126F}.
     * <pre>
     * <font style='background-color: #DEB4F4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEB4F4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEB4F4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DEB4F4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DEB4F4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DEB4F4'>&nbsp;@&nbsp;</font><font style='background-color: #DEB4F4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEB4F4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEB4F4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_VIOLET_PURPLE = -0x1.876f9ep126F;
    static { NAMED.put("bright violet purple", -0x1.876f9ep126F); LIST.add(-0x1.876f9ep126F); }

    /**
     * This color constant "deep pure purple" has RGBA8888 code {@code 9F11CEFF}, H 0.80784315, S 1.0, L 0.38431373, alpha 1.0, and chroma 1.0805489.
     * It can be represented as a packed float with the constant {@code -0x1.c5ff9cp125F}.
     * <pre>
     * <font style='background-color: #9F11CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9F11CE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9F11CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9F11CE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9F11CE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9F11CE'>&nbsp;@&nbsp;</font><font style='background-color: #9F11CE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9F11CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9F11CE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_PURPLE = -0x1.c5ff9cp125F;
    static { NAMED.put("deep pure purple", -0x1.c5ff9cp125F); LIST.add(-0x1.c5ff9cp125F); }

    /**
     * This color constant "true pure purple" has RGBA8888 code {@code C74BEFFF}, H 0.8156863, S 0.84705883, L 0.5176471, alpha 1.0, and chroma 1.0815578.
     * It can be represented as a packed float with the constant {@code -0x1.09b1ap126F}.
     * <pre>
     * <font style='background-color: #C74BEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C74BEF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C74BEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C74BEF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C74BEF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C74BEF'>&nbsp;@&nbsp;</font><font style='background-color: #C74BEF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C74BEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C74BEF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_PURPLE = -0x1.09b1ap126F;
    static { NAMED.put("true pure purple", -0x1.09b1ap126F); LIST.add(-0x1.09b1ap126F); }

    /**
     * This color constant "bright pure purple" has RGBA8888 code {@code E4A9F7FF}, H 0.8235294, S 0.7764706, L 0.7411765, alpha 1.0, and chroma 0.5394702.
     * It can be represented as a packed float with the constant {@code -0x1.7b8da4p126F}.
     * <pre>
     * <font style='background-color: #E4A9F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E4A9F7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E4A9F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E4A9F7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E4A9F7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E4A9F7'>&nbsp;@&nbsp;</font><font style='background-color: #E4A9F7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E4A9F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E4A9F7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_PURPLE = -0x1.7b8da4p126F;
    static { NAMED.put("bright pure purple", -0x1.7b8da4p126F); LIST.add(-0x1.7b8da4p126F); }

    /**
     * This color constant "deep magenta purple" has RGBA8888 code {@code 9B18B5FF}, H 0.8235294, S 1.0, L 0.3647059, alpha 1.0, and chroma 0.97081625.
     * It can be represented as a packed float with the constant {@code -0x1.bbffa4p125F}.
     * <pre>
     * <font style='background-color: #9B18B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B18B5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B18B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9B18B5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9B18B5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9B18B5'>&nbsp;@&nbsp;</font><font style='background-color: #9B18B5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B18B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B18B5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_MAGENTA_PURPLE = -0x1.bbffa4p125F;
    static { NAMED.put("deep magenta purple", -0x1.bbffa4p125F); LIST.add(-0x1.bbffa4p125F); }

    /**
     * This color constant "true magenta purple" has RGBA8888 code {@code CB45E5FF}, H 0.827451, S 0.87058824, L 0.50980395, alpha 1.0, and chroma 1.1521349.
     * It can be represented as a packed float with the constant {@code -0x1.05bda6p126F}.
     * <pre>
     * <font style='background-color: #CB45E5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CB45E5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CB45E5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CB45E5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CB45E5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CB45E5'>&nbsp;@&nbsp;</font><font style='background-color: #CB45E5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CB45E5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CB45E5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_MAGENTA_PURPLE = -0x1.05bda6p126F;
    static { NAMED.put("true magenta purple", -0x1.05bda6p126F); LIST.add(-0x1.05bda6p126F); }

    /**
     * This color constant "bright magenta purple" has RGBA8888 code {@code E7AAF3FF}, H 0.8352941, S 0.7176471, L 0.74509805, alpha 1.0, and chroma 0.51525503.
     * It can be represented as a packed float with the constant {@code -0x1.7d6faap126F}.
     * <pre>
     * <font style='background-color: #E7AAF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E7AAF3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E7AAF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E7AAF3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E7AAF3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E7AAF3'>&nbsp;@&nbsp;</font><font style='background-color: #E7AAF3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E7AAF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E7AAF3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA_PURPLE = -0x1.7d6faap126F;
    static { NAMED.put("bright magenta purple", -0x1.7d6faap126F); LIST.add(-0x1.7d6faap126F); }

    /**
     * This color constant "deep purple magenta" has RGBA8888 code {@code C411D4FF}, H 0.8392157, S 1.0, L 0.44705883, alpha 1.0, and chroma 1.1318272.
     * It can be represented as a packed float with the constant {@code -0x1.e5ffacp125F}.
     * <pre>
     * <font style='background-color: #C411D4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C411D4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C411D4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C411D4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C411D4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C411D4'>&nbsp;@&nbsp;</font><font style='background-color: #C411D4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C411D4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C411D4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE_MAGENTA = -0x1.e5ffacp125F;
    static { NAMED.put("deep purple magenta", -0x1.e5ffacp125F); LIST.add(-0x1.e5ffacp125F); }

    /**
     * This color constant "true purple magenta" has RGBA8888 code {@code E458F0FF}, H 0.84313726, S 0.8, L 0.5803922, alpha 1.0, and chroma 1.0037035.
     * It can be represented as a packed float with the constant {@code -0x1.2999aep126F}.
     * <pre>
     * <font style='background-color: #E458F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E458F0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E458F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E458F0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E458F0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E458F0'>&nbsp;@&nbsp;</font><font style='background-color: #E458F0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E458F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E458F0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURPLE_MAGENTA = -0x1.2999aep126F;
    static { NAMED.put("true purple magenta", -0x1.2999aep126F); LIST.add(-0x1.2999aep126F); }

    /**
     * This color constant "bright purple magenta" has RGBA8888 code {@code F1ADF8FF}, H 0.84313726, S 0.7764706, L 0.76862746, alpha 1.0, and chroma 0.52168816.
     * It can be represented as a packed float with the constant {@code -0x1.898daep126F}.
     * <pre>
     * <font style='background-color: #F1ADF8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F1ADF8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F1ADF8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F1ADF8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F1ADF8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F1ADF8'>&nbsp;@&nbsp;</font><font style='background-color: #F1ADF8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F1ADF8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F1ADF8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURPLE_MAGENTA = -0x1.898daep126F;
    static { NAMED.put("bright purple magenta", -0x1.898daep126F); LIST.add(-0x1.898daep126F); }

    /**
     * This color constant "deep pure magenta" has RGBA8888 code {@code C01AC0FF}, H 0.85490197, S 1.0, L 0.43137255, alpha 1.0, and chroma 1.0588346.
     * It can be represented as a packed float with the constant {@code -0x1.ddffb4p125F}.
     * <pre>
     * <font style='background-color: #C01AC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C01AC0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C01AC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C01AC0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C01AC0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C01AC0'>&nbsp;@&nbsp;</font><font style='background-color: #C01AC0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C01AC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C01AC0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_MAGENTA = -0x1.ddffb4p125F;
    static { NAMED.put("deep pure magenta", -0x1.ddffb4p125F); LIST.add(-0x1.ddffb4p125F); }

    /**
     * This color constant "true pure magenta" has RGBA8888 code {@code E64DE6FF}, H 0.85490197, S 0.84705883, L 0.56078434, alpha 1.0, and chroma 1.1495643.
     * It can be represented as a packed float with the constant {@code -0x1.1fb1b4p126F}.
     * <pre>
     * <font style='background-color: #E64DE6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E64DE6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E64DE6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E64DE6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E64DE6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E64DE6'>&nbsp;@&nbsp;</font><font style='background-color: #E64DE6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E64DE6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E64DE6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_MAGENTA = -0x1.1fb1b4p126F;
    static { NAMED.put("true pure magenta", -0x1.1fb1b4p126F); LIST.add(-0x1.1fb1b4p126F); }

    /**
     * This color constant "bright pure magenta" has RGBA8888 code {@code F3ACF3FF}, H 0.85490197, S 0.7019608, L 0.7647059, alpha 1.0, and chroma 0.5115234.
     * It can be represented as a packed float with the constant {@code -0x1.8767b4p126F}.
     * <pre>
     * <font style='background-color: #F3ACF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3ACF3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3ACF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F3ACF3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F3ACF3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F3ACF3'>&nbsp;@&nbsp;</font><font style='background-color: #F3ACF3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3ACF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3ACF3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_MAGENTA = -0x1.8767b4p126F;
    static { NAMED.put("bright pure magenta", -0x1.8767b4p126F); LIST.add(-0x1.8767b4p126F); }

    /**
     * This color constant "deep red magenta" has RGBA8888 code {@code B60F7EFF}, H 0.9411765, S 1.0, L 0.3764706, alpha 1.0, and chroma 0.93176126.
     * It can be represented as a packed float with the constant {@code -0x1.c1ffep125F}.
     * <pre>
     * <font style='background-color: #B60F7E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B60F7E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B60F7E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B60F7E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B60F7E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B60F7E'>&nbsp;@&nbsp;</font><font style='background-color: #B60F7E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B60F7E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B60F7E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED_MAGENTA = -0x1.c1ffep125F;
    static { NAMED.put("deep red magenta", -0x1.c1ffep125F); LIST.add(-0x1.c1ffep125F); }

    /**
     * This color constant "true red magenta" has RGBA8888 code {@code EE39B1FF}, H 0.92941177, S 0.9137255, L 0.5294118, alpha 1.0, and chroma 1.1583593.
     * It can be represented as a packed float with the constant {@code -0x1.0fd3dap126F}.
     * <pre>
     * <font style='background-color: #EE39B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EE39B1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EE39B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EE39B1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EE39B1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EE39B1'>&nbsp;@&nbsp;</font><font style='background-color: #EE39B1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EE39B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EE39B1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_RED_MAGENTA = -0x1.0fd3dap126F;
    static { NAMED.put("true red magenta", -0x1.0fd3dap126F); LIST.add(-0x1.0fd3dap126F); }

    /**
     * This color constant "bright red magenta" has RGBA8888 code {@code F7A5DCFF}, H 0.90588236, S 0.7607843, L 0.74509805, alpha 1.0, and chroma 0.47752857.
     * It can be represented as a packed float with the constant {@code -0x1.7d85cep126F}.
     * <pre>
     * <font style='background-color: #F7A5DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7A5DC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7A5DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F7A5DC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F7A5DC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F7A5DC'>&nbsp;@&nbsp;</font><font style='background-color: #F7A5DC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7A5DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7A5DC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED_MAGENTA = -0x1.7d85cep126F;
    static { NAMED.put("bright red magenta", -0x1.7d85cep126F); LIST.add(-0x1.7d85cep126F); }

    /**
     * This color constant "deep magenta red" has RGBA8888 code {@code C71B54FF}, H 0.003921569, S 0.99607843, L 0.40392157, alpha 1.0, and chroma 1.2201552.
     * It can be represented as a packed float with the constant {@code -0x1.cffc02p125F}.
     * <pre>
     * <font style='background-color: #C71B54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C71B54; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C71B54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C71B54'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C71B54'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C71B54'>&nbsp;@&nbsp;</font><font style='background-color: #C71B54; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C71B54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C71B54; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_MAGENTA_RED = -0x1.cffc02p125F;
    static { NAMED.put("deep magenta red", -0x1.cffc02p125F); LIST.add(-0x1.cffc02p125F); }

    /**
     * This color constant "true magenta red" has RGBA8888 code {@code E85888FF}, H 0.9882353, S 0.6745098, L 0.54509807, alpha 1.0, and chroma 0.8619814.
     * It can be represented as a packed float with the constant {@code -0x1.1759f8p126F}.
     * <pre>
     * <font style='background-color: #E85888;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E85888; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E85888;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E85888'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E85888'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E85888'>&nbsp;@&nbsp;</font><font style='background-color: #E85888; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E85888;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E85888; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_MAGENTA_RED = -0x1.1759f8p126F;
    static { NAMED.put("true magenta red", -0x1.1759f8p126F); LIST.add(-0x1.1759f8p126F); }

    /**
     * This color constant "bright magenta red" has RGBA8888 code {@code F4AEC5FF}, H 0.972549, S 0.69411767, L 0.75686276, alpha 1.0, and chroma 0.36777127.
     * It can be represented as a packed float with the constant {@code -0x1.8363fp126F}.
     * <pre>
     * <font style='background-color: #F4AEC5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F4AEC5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F4AEC5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F4AEC5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F4AEC5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F4AEC5'>&nbsp;@&nbsp;</font><font style='background-color: #F4AEC5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F4AEC5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F4AEC5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA_RED = -0x1.8363fp126F;
    static { NAMED.put("bright magenta red", -0x1.8363fp126F); LIST.add(-0x1.8363fp126F); }

    /**
     * This color constant "bold pure red" has RGBA8888 code {@code FF0101FF}, H 0.03137255, S 1.0, L 0.49803922, alpha 1.0, and chroma 1.7587819.
     * It can be represented as a packed float with the constant {@code -0x1.fffe1p125F}.
     * <pre>
     * <font style='background-color: #FF0101;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0101; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0101;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF0101'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF0101'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF0101'>&nbsp;@&nbsp;</font><font style='background-color: #FF0101; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0101;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0101; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_RED = -0x1.fffe1p125F;
    static { NAMED.put("bold pure red", -0x1.fffe1p125F); LIST.add(-0x1.fffe1p125F); }

    /**
     * This color constant "bold brown red" has RGBA8888 code {@code F2270DFF}, H 0.03529412, S 1.0, L 0.4862745, alpha 1.0, and chroma 1.714403.
     * It can be represented as a packed float with the constant {@code -0x1.f9fe12p125F}.
     * <pre>
     * <font style='background-color: #F2270D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2270D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2270D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F2270D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F2270D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F2270D'>&nbsp;@&nbsp;</font><font style='background-color: #F2270D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2270D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2270D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_BROWN_RED = -0x1.f9fe12p125F;
    static { NAMED.put("bold brown red", -0x1.f9fe12p125F); LIST.add(-0x1.f9fe12p125F); }

    /**
     * This color constant "bold red brown" has RGBA8888 code {@code FF3901FF}, H 0.039215688, S 1.0, L 0.5254902, alpha 1.0, and chroma 1.6220237.
     * It can be represented as a packed float with the constant {@code -0x1.0dfe14p126F}.
     * <pre>
     * <font style='background-color: #FF3901;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF3901; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF3901;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF3901'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF3901'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF3901'>&nbsp;@&nbsp;</font><font style='background-color: #FF3901; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF3901;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF3901; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_RED_BROWN = -0x1.0dfe14p126F;
    static { NAMED.put("bold red brown", -0x1.0dfe14p126F); LIST.add(-0x1.0dfe14p126F); }

    /**
     * This color constant "bold pure brown" has RGBA8888 code {@code F2590DFF}, H 0.05490196, S 1.0, L 0.54509807, alpha 1.0, and chroma 1.4257207.
     * It can be represented as a packed float with the constant {@code -0x1.17fe1cp126F}.
     * <pre>
     * <font style='background-color: #F2590D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2590D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2590D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F2590D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F2590D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F2590D'>&nbsp;@&nbsp;</font><font style='background-color: #F2590D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2590D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2590D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_BROWN = -0x1.17fe1cp126F;
    static { NAMED.put("bold pure brown", -0x1.17fe1cp126F); LIST.add(-0x1.17fe1cp126F); }

    /**
     * This color constant "bold orange brown" has RGBA8888 code {@code FF6301FF}, H 0.05882353, S 1.0, L 0.58431375, alpha 1.0, and chroma 1.3720239.
     * It can be represented as a packed float with the constant {@code -0x1.2bfe1ep126F}.
     * <pre>
     * <font style='background-color: #FF6301;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6301; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6301;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF6301'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF6301'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF6301'>&nbsp;@&nbsp;</font><font style='background-color: #FF6301; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6301;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6301; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_ORANGE_BROWN = -0x1.2bfe1ep126F;
    static { NAMED.put("bold orange brown", -0x1.2bfe1ep126F); LIST.add(-0x1.2bfe1ep126F); }

    /**
     * This color constant "bold brown orange" has RGBA8888 code {@code F2730DFF}, H 0.07450981, S 1.0, L 0.5921569, alpha 1.0, and chroma 1.2434042.
     * It can be represented as a packed float with the constant {@code -0x1.2ffe26p126F}.
     * <pre>
     * <font style='background-color: #F2730D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2730D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2730D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F2730D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F2730D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F2730D'>&nbsp;@&nbsp;</font><font style='background-color: #F2730D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2730D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2730D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_BROWN_ORANGE = -0x1.2ffe26p126F;
    static { NAMED.put("bold brown orange", -0x1.2ffe26p126F); LIST.add(-0x1.2ffe26p126F); }

    /**
     * This color constant "bold pure orange" has RGBA8888 code {@code F97F06FF}, H 0.08235294, S 1.0, L 0.627451, alpha 1.0, and chroma 1.222372.
     * It can be represented as a packed float with the constant {@code -0x1.41fe2ap126F}.
     * <pre>
     * <font style='background-color: #F97F06;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F97F06; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F97F06;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F97F06'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F97F06'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F97F06'>&nbsp;@&nbsp;</font><font style='background-color: #F97F06; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F97F06;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F97F06; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_ORANGE = -0x1.41fe2ap126F;
    static { NAMED.put("bold pure orange", -0x1.41fe2ap126F); LIST.add(-0x1.41fe2ap126F); }

    /**
     * This color constant "bold saffron orange" has RGBA8888 code {@code E3851CFF}, H 0.101960786, S 1.0, L 0.60784316, alpha 1.0, and chroma 1.0245811.
     * It can be represented as a packed float with the constant {@code -0x1.37fe34p126F}.
     * <pre>
     * <font style='background-color: #E3851C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3851C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3851C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E3851C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E3851C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E3851C'>&nbsp;@&nbsp;</font><font style='background-color: #E3851C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3851C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3851C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_SAFFRON_ORANGE = -0x1.37fe34p126F;
    static { NAMED.put("bold saffron orange", -0x1.37fe34p126F); LIST.add(-0x1.37fe34p126F); }

    /**
     * This color constant "bold orange saffron" has RGBA8888 code {@code E58C1AFF}, H 0.10980392, S 1.0, L 0.627451, alpha 1.0, and chroma 1.0041153.
     * It can be represented as a packed float with the constant {@code -0x1.41fe38p126F}.
     * <pre>
     * <font style='background-color: #E58C1A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E58C1A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E58C1A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E58C1A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E58C1A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E58C1A'>&nbsp;@&nbsp;</font><font style='background-color: #E58C1A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E58C1A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E58C1A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_ORANGE_SAFFRON = -0x1.41fe38p126F;
    static { NAMED.put("bold orange saffron", -0x1.41fe38p126F); LIST.add(-0x1.41fe38p126F); }

    /**
     * This color constant "bold pure saffron" has RGBA8888 code {@code D38F2CFF}, H 0.12941177, S 0.96862745, L 0.6117647, alpha 1.0, and chroma 0.85659623.
     * It can be represented as a packed float with the constant {@code -0x1.39ee42p126F}.
     * <pre>
     * <font style='background-color: #D38F2C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D38F2C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D38F2C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D38F2C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D38F2C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D38F2C'>&nbsp;@&nbsp;</font><font style='background-color: #D38F2C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D38F2C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D38F2C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_SAFFRON = -0x1.39ee42p126F;
    static { NAMED.put("bold pure saffron", -0x1.39ee42p126F); LIST.add(-0x1.39ee42p126F); }

    /**
     * This color constant "bold yellow saffron" has RGBA8888 code {@code CCA233FF}, H 0.16862746, S 0.9490196, L 0.654902, alpha 1.0, and chroma 0.77695906.
     * It can be represented as a packed float with the constant {@code -0x1.4fe456p126F}.
     * <pre>
     * <font style='background-color: #CCA233;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CCA233; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CCA233;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CCA233'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CCA233'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CCA233'>&nbsp;@&nbsp;</font><font style='background-color: #CCA233; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CCA233;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CCA233; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_YELLOW_SAFFRON = -0x1.4fe456p126F;
    static { NAMED.put("bold yellow saffron", -0x1.4fe456p126F); LIST.add(-0x1.4fe456p126F); }

    /**
     * This color constant "bold saffron yellow" has RGBA8888 code {@code D7BF28FF}, H 0.20392157, S 0.99215686, L 0.74509805, alpha 1.0, and chroma 0.8566367.
     * It can be represented as a packed float with the constant {@code -0x1.7dfa68p126F}.
     * <pre>
     * <font style='background-color: #D7BF28;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7BF28; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7BF28;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D7BF28'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D7BF28'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D7BF28'>&nbsp;@&nbsp;</font><font style='background-color: #D7BF28; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7BF28;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7BF28; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_SAFFRON_YELLOW = -0x1.7dfa68p126F;
    static { NAMED.put("bold saffron yellow", -0x1.7dfa68p126F); LIST.add(-0x1.7dfa68p126F); }

    /**
     * This color constant "bold pure yellow" has RGBA8888 code {@code FFFF01FF}, H 0.23921569, S 1.0, L 0.96862745, alpha 1.0, and chroma 0.96873754.
     * It can be represented as a packed float with the constant {@code -0x1.effe7ap126F}.
     * <pre>
     * <font style='background-color: #FFFF01;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFF01; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFF01;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFF01'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFF01'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFF01'>&nbsp;@&nbsp;</font><font style='background-color: #FFFF01; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFF01;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFF01; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_YELLOW = -0x1.effe7ap126F;
    static { NAMED.put("bold pure yellow", -0x1.effe7ap126F); LIST.add(-0x1.effe7ap126F); }

    /**
     * This color constant "bold lime yellow" has RGBA8888 code {@code DBF20DFF}, H 0.2627451, S 1.0, L 0.8980392, alpha 1.0, and chroma 1.0242358.
     * It can be represented as a packed float with the constant {@code -0x1.cbfe86p126F}.
     * <pre>
     * <font style='background-color: #DBF20D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBF20D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBF20D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DBF20D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DBF20D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DBF20D'>&nbsp;@&nbsp;</font><font style='background-color: #DBF20D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBF20D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBF20D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_LIME_YELLOW = -0x1.cbfe86p126F;
    static { NAMED.put("bold lime yellow", -0x1.cbfe86p126F); LIST.add(-0x1.cbfe86p126F); }

    /**
     * This color constant "bold yellow lime" has RGBA8888 code {@code CBFF01FF}, H 0.28627452, S 1.0, L 0.9254902, alpha 1.0, and chroma 1.0959718.
     * It can be represented as a packed float with the constant {@code -0x1.d9fe92p126F}.
     * <pre>
     * <font style='background-color: #CBFF01;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBFF01; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBFF01;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CBFF01'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CBFF01'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CBFF01'>&nbsp;@&nbsp;</font><font style='background-color: #CBFF01; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBFF01;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBFF01; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_YELLOW_LIME = -0x1.d9fe92p126F;
    static { NAMED.put("bold yellow lime", -0x1.d9fe92p126F); LIST.add(-0x1.d9fe92p126F); }

    /**
     * This color constant "bold pure lime" has RGBA8888 code {@code ACF20DFF}, H 0.3019608, S 1.0, L 0.8666667, alpha 1.0, and chroma 1.0788691.
     * It can be represented as a packed float with the constant {@code -0x1.bbfe9ap126F}.
     * <pre>
     * <font style='background-color: #ACF20D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ACF20D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ACF20D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ACF20D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ACF20D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ACF20D'>&nbsp;@&nbsp;</font><font style='background-color: #ACF20D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ACF20D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ACF20D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_LIME = -0x1.bbfe9ap126F;
    static { NAMED.put("bold pure lime", -0x1.bbfe9ap126F); LIST.add(-0x1.bbfe9ap126F); }

    /**
     * This color constant "bold green lime" has RGBA8888 code {@code 76FF01FF}, H 0.33333334, S 1.0, L 0.88235295, alpha 1.0, and chroma 1.2356075.
     * It can be represented as a packed float with the constant {@code -0x1.c3feaap126F}.
     * <pre>
     * <font style='background-color: #76FF01;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #76FF01; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #76FF01;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #76FF01'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #76FF01'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #76FF01'>&nbsp;@&nbsp;</font><font style='background-color: #76FF01; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #76FF01;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #76FF01; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_GREEN_LIME = -0x1.c3feaap126F;
    static { NAMED.put("bold green lime", -0x1.c3feaap126F); LIST.add(-0x1.c3feaap126F); }

    /**
     * This color constant "bold lime green" has RGBA8888 code {@code 42F20DFF}, H 0.34901962, S 1.0, L 0.8235294, alpha 1.0, and chroma 1.262463.
     * It can be represented as a packed float with the constant {@code -0x1.a5feb2p126F}.
     * <pre>
     * <font style='background-color: #42F20D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #42F20D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #42F20D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #42F20D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #42F20D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #42F20D'>&nbsp;@&nbsp;</font><font style='background-color: #42F20D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #42F20D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #42F20D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_LIME_GREEN = -0x1.a5feb2p126F;
    static { NAMED.put("bold lime green", -0x1.a5feb2p126F); LIST.add(-0x1.a5feb2p126F); }

    /**
     * This color constant "bold pure green" has RGBA8888 code {@code 01FF01FF}, H 0.3529412, S 1.0, L 0.8627451, alpha 1.0, and chroma 1.3288879.
     * It can be represented as a packed float with the constant {@code -0x1.b9feb4p126F}.
     * <pre>
     * <font style='background-color: #01FF01;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #01FF01; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #01FF01;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #01FF01'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #01FF01'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #01FF01'>&nbsp;@&nbsp;</font><font style='background-color: #01FF01; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #01FF01;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #01FF01; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_GREEN = -0x1.b9feb4p126F;
    static { NAMED.put("bold pure green", -0x1.b9feb4p126F); LIST.add(-0x1.b9feb4p126F); }

    /**
     * This color constant "bold cyan green" has RGBA8888 code {@code 0DF259FF}, H 0.3647059, S 1.0, L 0.81960785, alpha 1.0, and chroma 1.1790828.
     * It can be represented as a packed float with the constant {@code -0x1.a3febap126F}.
     * <pre>
     * <font style='background-color: #0DF259;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0DF259; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0DF259;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0DF259'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0DF259'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0DF259'>&nbsp;@&nbsp;</font><font style='background-color: #0DF259; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0DF259;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0DF259; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_CYAN_GREEN = -0x1.a3febap126F;
    static { NAMED.put("bold cyan green", -0x1.a3febap126F); LIST.add(-0x1.a3febap126F); }

    /**
     * This color constant "bold green cyan" has RGBA8888 code {@code 01FFAAFF}, H 0.40784314, S 1.0, L 0.8784314, alpha 1.0, and chroma 0.8878825.
     * It can be represented as a packed float with the constant {@code -0x1.c1fedp126F}.
     * <pre>
     * <font style='background-color: #01FFAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #01FFAA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #01FFAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #01FFAA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #01FFAA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #01FFAA'>&nbsp;@&nbsp;</font><font style='background-color: #01FFAA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #01FFAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #01FFAA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_GREEN_CYAN = -0x1.c1fedp126F;
    static { NAMED.put("bold green cyan", -0x1.c1fedp126F); LIST.add(-0x1.c1fedp126F); }

    /**
     * This color constant "bold pure cyan" has RGBA8888 code {@code 0DF2F2FF}, H 0.53333336, S 1.0, L 0.8509804, alpha 1.0, and chroma 0.6879112.
     * It can be represented as a packed float with the constant {@code -0x1.b3ff1p126F}.
     * <pre>
     * <font style='background-color: #0DF2F2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0DF2F2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0DF2F2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0DF2F2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0DF2F2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0DF2F2'>&nbsp;@&nbsp;</font><font style='background-color: #0DF2F2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0DF2F2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0DF2F2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_CYAN = -0x1.b3ff1p126F;
    static { NAMED.put("bold pure cyan", -0x1.b3ff1p126F); LIST.add(-0x1.b3ff1p126F); }

    /**
     * This color constant "bold blue cyan" has RGBA8888 code {@code 01AAFFFF}, H 0.6745098, S 1.0, L 0.6313726, alpha 1.0, and chroma 0.9312566.
     * It can be represented as a packed float with the constant {@code -0x1.43ff58p126F}.
     * <pre>
     * <font style='background-color: #01AAFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #01AAFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #01AAFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #01AAFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #01AAFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #01AAFF'>&nbsp;@&nbsp;</font><font style='background-color: #01AAFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #01AAFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #01AAFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_BLUE_CYAN = -0x1.43ff58p126F;
    static { NAMED.put("bold blue cyan", -0x1.43ff58p126F); LIST.add(-0x1.43ff58p126F); }

    /**
     * This color constant "bold cyan blue" has RGBA8888 code {@code 0D59F2FF}, H 0.7254902, S 1.0, L 0.40392157, alpha 1.0, and chroma 1.2588465.
     * It can be represented as a packed float with the constant {@code -0x1.cfff72p125F}.
     * <pre>
     * <font style='background-color: #0D59F2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0D59F2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0D59F2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0D59F2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0D59F2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0D59F2'>&nbsp;@&nbsp;</font><font style='background-color: #0D59F2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0D59F2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0D59F2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_CYAN_BLUE = -0x1.cfff72p125F;
    static { NAMED.put("bold cyan blue", -0x1.cfff72p125F); LIST.add(-0x1.cfff72p125F); }

    /**
     * This color constant "bold pure blue" has RGBA8888 code {@code 0101FFFF}, H 0.7411765, S 1.0, L 0.29803923, alpha 1.0, and chroma 1.2761624.
     * It can be represented as a packed float with the constant {@code -0x1.99ff7ap125F}.
     * <pre>
     * <font style='background-color: #0101FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0101FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0101FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0101FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0101FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0101FF'>&nbsp;@&nbsp;</font><font style='background-color: #0101FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0101FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0101FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_BLUE = -0x1.99ff7ap125F;
    static { NAMED.put("bold pure blue", -0x1.99ff7ap125F); LIST.add(-0x1.99ff7ap125F); }

    /**
     * This color constant "bold violet blue" has RGBA8888 code {@code 300DF2FF}, H 0.7411765, S 1.0, L 0.29803923, alpha 1.0, and chroma 1.2761624.
     * It can be represented as a packed float with the constant {@code -0x1.99ff7ap125F}.
     * <pre>
     * <font style='background-color: #300DF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #300DF2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #300DF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #300DF2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #300DF2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #300DF2'>&nbsp;@&nbsp;</font><font style='background-color: #300DF2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #300DF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #300DF2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_VIOLET_BLUE = -0x1.99ff7ap125F;
    static { NAMED.put("bold violet blue", -0x1.99ff7ap125F); LIST.add(-0x1.99ff7ap125F); }

    /**
     * This color constant "bold blue violet" has RGBA8888 code {@code 4E01FFFF}, H 0.7490196, S 1.0, L 0.32941177, alpha 1.0, and chroma 1.3159771.
     * It can be represented as a packed float with the constant {@code -0x1.a9ff7ep125F}.
     * <pre>
     * <font style='background-color: #4E01FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E01FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E01FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4E01FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4E01FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4E01FF'>&nbsp;@&nbsp;</font><font style='background-color: #4E01FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E01FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E01FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_BLUE_VIOLET = -0x1.a9ff7ep125F;
    static { NAMED.put("bold blue violet", -0x1.a9ff7ep125F); LIST.add(-0x1.a9ff7ep125F); }

    /**
     * This color constant "bold pure violet" has RGBA8888 code {@code 760DF2FF}, H 0.7647059, S 1.0, L 0.35686275, alpha 1.0, and chroma 1.2646295.
     * It can be represented as a packed float with the constant {@code -0x1.b7ff86p125F}.
     * <pre>
     * <font style='background-color: #760DF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #760DF2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #760DF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #760DF2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #760DF2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #760DF2'>&nbsp;@&nbsp;</font><font style='background-color: #760DF2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #760DF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #760DF2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_VIOLET = -0x1.b7ff86p125F;
    static { NAMED.put("bold pure violet", -0x1.b7ff86p125F); LIST.add(-0x1.b7ff86p125F); }

    /**
     * This color constant "bold purple violet" has RGBA8888 code {@code 8E01FFFF}, H 0.77254903, S 1.0, L 0.39607844, alpha 1.0, and chroma 1.3299705.
     * It can be represented as a packed float with the constant {@code -0x1.cbff8ap125F}.
     * <pre>
     * <font style='background-color: #8E01FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8E01FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8E01FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8E01FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8E01FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8E01FF'>&nbsp;@&nbsp;</font><font style='background-color: #8E01FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8E01FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8E01FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURPLE_VIOLET = -0x1.cbff8ap125F;
    static { NAMED.put("bold purple violet", -0x1.cbff8ap125F); LIST.add(-0x1.cbff8ap125F); }

    /**
     * This color constant "bold violet purple" has RGBA8888 code {@code A30DF2FF}, H 0.7882353, S 1.0, L 0.41568628, alpha 1.0, and chroma 1.2747668.
     * It can be represented as a packed float with the constant {@code -0x1.d5ff92p125F}.
     * <pre>
     * <font style='background-color: #A30DF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A30DF2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A30DF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A30DF2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A30DF2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A30DF2'>&nbsp;@&nbsp;</font><font style='background-color: #A30DF2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A30DF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A30DF2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_VIOLET_PURPLE = -0x1.d5ff92p125F;
    static { NAMED.put("bold violet purple", -0x1.d5ff92p125F); LIST.add(-0x1.d5ff92p125F); }

    /**
     * This color constant "bold pure purple" has RGBA8888 code {@code C001FFFF}, H 0.8039216, S 1.0, L 0.46666667, alpha 1.0, and chroma 1.3237739.
     * It can be represented as a packed float with the constant {@code -0x1.efff9ap125F}.
     * <pre>
     * <font style='background-color: #C001FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C001FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C001FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C001FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C001FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C001FF'>&nbsp;@&nbsp;</font><font style='background-color: #C001FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C001FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C001FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_PURPLE = -0x1.efff9ap125F;
    static { NAMED.put("bold pure purple", -0x1.efff9ap125F); LIST.add(-0x1.efff9ap125F); }

    /**
     * This color constant "bold magenta purple" has RGBA8888 code {@code CC0DF2FF}, H 0.81960785, S 1.0, L 0.47843137, alpha 1.0, and chroma 1.2775759.
     * It can be represented as a packed float with the constant {@code -0x1.f5ffa2p125F}.
     * <pre>
     * <font style='background-color: #CC0DF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CC0DF2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CC0DF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CC0DF2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CC0DF2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CC0DF2'>&nbsp;@&nbsp;</font><font style='background-color: #CC0DF2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CC0DF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CC0DF2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_MAGENTA_PURPLE = -0x1.f5ffa2p125F;
    static { NAMED.put("bold magenta purple", -0x1.f5ffa2p125F); LIST.add(-0x1.f5ffa2p125F); }

    /**
     * This color constant "bold purple magenta" has RGBA8888 code {@code EA01FFFF}, H 0.8392157, S 1.0, L 0.53333336, alpha 1.0, and chroma 1.3376548.
     * It can be represented as a packed float with the constant {@code -0x1.11ffacp126F}.
     * <pre>
     * <font style='background-color: #EA01FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EA01FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EA01FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EA01FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EA01FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EA01FF'>&nbsp;@&nbsp;</font><font style='background-color: #EA01FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EA01FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EA01FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURPLE_MAGENTA = -0x1.11ffacp126F;
    static { NAMED.put("bold purple magenta", -0x1.11ffacp126F); LIST.add(-0x1.11ffacp126F); }

    /**
     * This color constant "bold pure magenta" has RGBA8888 code {@code F20DF2FF}, H 0.85490197, S 1.0, L 0.5411765, alpha 1.0, and chroma 1.3128066.
     * It can be represented as a packed float with the constant {@code -0x1.15ffb4p126F}.
     * <pre>
     * <font style='background-color: #F20DF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F20DF2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F20DF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F20DF2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F20DF2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F20DF2'>&nbsp;@&nbsp;</font><font style='background-color: #F20DF2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F20DF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F20DF2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_MAGENTA = -0x1.15ffb4p126F;
    static { NAMED.put("bold pure magenta", -0x1.15ffb4p126F); LIST.add(-0x1.15ffb4p126F); }

    /**
     * This color constant "bold red magenta" has RGBA8888 code {@code FF01AAFF}, H 0.9490196, S 1.0, L 0.5254902, alpha 1.0, and chroma 1.3022852.
     * It can be represented as a packed float with the constant {@code -0x1.0dffe4p126F}.
     * <pre>
     * <font style='background-color: #FF01AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF01AA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF01AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF01AA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF01AA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF01AA'>&nbsp;@&nbsp;</font><font style='background-color: #FF01AA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF01AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF01AA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_RED_MAGENTA = -0x1.0dffe4p126F;
    static { NAMED.put("bold red magenta", -0x1.0dffe4p126F); LIST.add(-0x1.0dffe4p126F); }

    /**
     * This color constant "bold magenta red" has RGBA8888 code {@code F20D59FF}, H 0.007843138, S 1.0, L 0.48235294, alpha 1.0, and chroma 1.4811642.
     * It can be represented as a packed float with the constant {@code -0x1.f7fe04p125F}.
     * <pre>
     * <font style='background-color: #F20D59;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F20D59; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F20D59;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F20D59'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F20D59'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F20D59'>&nbsp;@&nbsp;</font><font style='background-color: #F20D59; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F20D59;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F20D59; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_MAGENTA_RED = -0x1.f7fe04p125F;
    static { NAMED.put("bold magenta red", -0x1.f7fe04p125F); LIST.add(-0x1.f7fe04p125F); }

    /**
     * All names for colors in this palette, in alphabetical order. You can fetch the corresponding packed float color
     * by looking up a name in {@link #NAMED}.
     */
    public static final Array<String> NAMES = NAMED.keys().toArray();
    static { NAMES.sort(); }
    /**
     * All names for colors in this palette, sorted by hue from red to yellow to green to blue, with grayscale colors
     * before any other colors. You can fetch the corresponding packed float color by looking up a name in
     * {@link #NAMED}.
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
                final float c1 = NAMED.get(o1, UBE_TRANSPARENT), c2 = NAMED.get(o2, UBE_TRANSPARENT);
                if(ColorTools.alphaInt(c1) < 128) return -10000;
                else if(ColorTools.alphaInt(c2) < 128) return 10000;
                final float s1 = ColorTools.channelS(c1), s2 = ColorTools.channelS(c2);
                if(s1 <= 0.05f && s2 > 0.05f)
                    return -1000;
                else if(s1 > 0.05f && s2 <= 0.05f)
                    return 1000;
                else if(s1 <= 0.05f && s2 <= 0.05f)
                    return (int)Math.signum(ColorTools.channelL(c1) - ColorTools.channelL(c2));
                else
                    return 2 * (int)Math.signum(ColorTools.channelH(c1) - ColorTools.channelH(c2))
                            + (int)Math.signum(ColorTools.channelL(c1) - ColorTools.channelL(c2));
            }
        });
        NAMES_BY_LIGHTNESS.sort(new Comparator<String>() {
            public int compare(String o1, String o2) {
                return Float.compare(ColorTools.channelL(NAMED.get(o1, UBE_TRANSPARENT)), ColorTools.channelL(NAMED.get(o2, UBE_TRANSPARENT)));
            }
        });
    }

    /**
     * Changes the existing RGBA Color instances in {@link Colors} to use HSLuv and so be able to be shown normally by
     * {@link ColorfulBatch} or a Batch using {@link com.github.tommyettinger.colorful.Shaders#fragmentShaderCielab}.
     * Any colors used in libGDX text markup look up their values in Colors, so calling this can help display fonts
     * where markup is enabled. This only needs to be called once, and if you call {@link #appendToKnownColors()}, then
     * that should be done after this to avoid mixing RGBA and HSLuv colors.
     * <br>
     * This is a duplicate of a method with the same name in SimplePalette; you should still only call this method once,
     * regardless of where it was from.
     */
    public static void editKnownColors(){
        for(Color c : Colors.getColors().values()) {
            final float f = ColorTools.fromColor(c);
            c.set(channelH(f), channelS(f), channelL(f), c.a);
        }
    }

    /**
     * Appends HSLuv-compatible Color instances to the map in {@link Colors}, using the names in {@link #NAMES} (which
     * are "Title Cased" instead of "ALL UPPER CASE"). If you intend to still use the existing values in Colors, you
     * should call {@link #editKnownColors()} first; otherwise you can just always use "Title Cased" color names.
     * <br>
     * This can be used alongside the method with the same name in SimplePalette, since that uses "lower cased" names.
     */
    public static void appendToKnownColors(){
        for(ObjectFloatMap.Entry<String> ent : NAMED) {
            final float f = ent.value;
            Colors.put(ent.key, new Color(channelH(f), channelS(f), channelL(f), alpha(f)));
        }
    }
}
