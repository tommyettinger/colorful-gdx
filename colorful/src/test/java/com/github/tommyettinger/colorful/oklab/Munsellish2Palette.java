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
 * A palette of predefined colors as packed Oklab floats, the kind {@link ColorTools} works with.
 * You can access colors by their constant name, such as {@code DEEP_YELLOWISH_PINK}, by the {@link #NAMED} map using
 * {@code NAMED.get("Deep Yellowish Pink", 0f)}, or by index in the FloatArray called {@link #LIST}. Note that to access
 * a float color from NAMED, you need to give a default value if the name is not found; {@code 0f} is a good default
 * because it will not occur in a valid Oklab color. You can access the names in a specific order with {@link #NAMES}
 * (which is alphabetical), {@link #NAMES_BY_HUE} (which is sorted by the hue of the matching color, from red to yellow
 * to blue to purple to red again), or {@link #NAMES_BY_LIGHTNESS} (which is sorted by the intensity of
 * the matching color, from darkest to lightest). Having a name lets you look up the matching color in {@link #NAMED}.
 * <br>
 * This uses data provided by Paul Centore for the ISCC-NBS color names and their similar sRGB colors. These colors are
 * related closely to the Munsell color system, but I'm not sure exactly how ISCC-NBS ties in. 255 of its colors (plus
 * transparent) are a good size to incorporate here, though.
 * <a href="https://www.munsellcolourscienceforpainters.com/MunsellAndKubelkaMunkToolbox/MunsellAndKubelkaMunkToolbox.html">That data, and other Munsell-related color code, is available here.</a>
 */
public class Munsellish2Palette {
    public static final ObjectFloatMap<String> NAMED = new ObjectFloatMap<String>(256);
    public static final FloatArray LIST = new FloatArray(256);


    /**
     * This color constant "Munsellish Transparent" has RGBA8888 code {@code 00000000}, L 0.0, A 0.49803922, B 0.49803922, alpha 0.0, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code 0x0.fefep-126F}.
     * <pre>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #000000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MUNSELLISH_TRANSPARENT = 0x0.fefep-126F;
    static { NAMED.put("Munsellish Transparent", 0x0.fefep-126F); LIST.add(0x0.fefep-126F); }

    /**
     * This color constant "Black" has RGBA8888 code {@code 000000FF}, L 0.0, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.fefep125F}.
     * <pre>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #000000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK = -0x1.fefep125F;
    static { NAMED.put("Black", -0x1.fefep125F); LIST.add(-0x1.fefep125F); }

    /**
     * This color constant "Dark Gray" has RGBA8888 code {@code 222222FF}, L 0.13333334, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.fefe44p125F}.
     * <pre>
     * <font style='background-color: #222222;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #222222; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #222222;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #222222'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #222222'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #222222'>&nbsp;@&nbsp;</font><font style='background-color: #222222; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #222222;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #222222; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY = -0x1.fefe44p125F;
    static { NAMED.put("Dark Gray", -0x1.fefe44p125F); LIST.add(-0x1.fefe44p125F); }

    /**
     * This color constant "Medium Gray" has RGBA8888 code {@code 545454FF}, L 0.32941177, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.fefea8p125F}.
     * <pre>
     * <font style='background-color: #545454;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #545454; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #545454;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #545454'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #545454'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #545454'>&nbsp;@&nbsp;</font><font style='background-color: #545454; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #545454;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #545454; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MEDIUM_GRAY = -0x1.fefea8p125F;
    static { NAMED.put("Medium Gray", -0x1.fefea8p125F); LIST.add(-0x1.fefea8p125F); }

    /**
     * This color constant "Light Gray" has RGBA8888 code {@code 8C8C8CFF}, L 0.54901963, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.feff18p125F}.
     * <pre>
     * <font style='background-color: #8C8C8C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C8C8C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C8C8C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8C8C8C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8C8C8C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8C8C8C'>&nbsp;@&nbsp;</font><font style='background-color: #8C8C8C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C8C8C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C8C8C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY = -0x1.feff18p125F;
    static { NAMED.put("Light Gray", -0x1.feff18p125F); LIST.add(-0x1.feff18p125F); }

    /**
     * This color constant "Gray White" has RGBA8888 code {@code E0E0E0FF}, L 0.8784314, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.feffcp125F}.
     * <pre>
     * <font style='background-color: #E0E0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E0E0E0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E0E0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E0E0E0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E0E0E0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E0E0E0'>&nbsp;@&nbsp;</font><font style='background-color: #E0E0E0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E0E0E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E0E0E0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_WHITE = -0x1.feffcp125F;
    static { NAMED.put("Gray White", -0x1.feffcp125F); LIST.add(-0x1.feffcp125F); }

    /**
     * This color constant "White" has RGBA8888 code {@code FFFFFFFF}, L 1.0, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.fefffep125F}.
     * <pre>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #FFFFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE = -0x1.fefffep125F;
    static { NAMED.put("White", -0x1.fefffep125F); LIST.add(-0x1.fefffep125F); }

    /**
     * This color constant "Vivid Pink" has RGBA8888 code {@code FC6B8AFF}, L 0.62352943, A 0.5803922, B 0.50980395, alpha 1.0, hue 0.022583367, saturation 0.5509923, and chroma 0.16134278.
     * It can be represented as a packed float with the constant {@code -0x1.05293ep126F}.
     * <pre>
     * <font style='background-color: #FC6B8A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FC6B8A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FC6B8A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FC6B8A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FC6B8A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FC6B8A'>&nbsp;@&nbsp;</font><font style='background-color: #FC6B8A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FC6B8A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FC6B8A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_PINK = -0x1.05293ep126F;
    static { NAMED.put("Vivid Pink", -0x1.05293ep126F); LIST.add(-0x1.05293ep126F); }

    /**
     * This color constant "Strong Pink" has RGBA8888 code {@code FB8E9CFF}, L 0.69411767, A 0.5568628, B 0.50980395, alpha 1.0, hue 0.031416386, saturation 0.37309897, and chroma 0.114952646.
     * It can be represented as a packed float with the constant {@code -0x1.051d62p126F}.
     * <pre>
     * <font style='background-color: #FB8E9C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FB8E9C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FB8E9C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FB8E9C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FB8E9C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FB8E9C'>&nbsp;@&nbsp;</font><font style='background-color: #FB8E9C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FB8E9C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FB8E9C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_PINK = -0x1.051d62p126F;
    static { NAMED.put("Strong Pink", -0x1.051d62p126F); LIST.add(-0x1.051d62p126F); }

    /**
     * This color constant "Deep Pink" has RGBA8888 code {@code F03663FF}, L 0.5137255, A 0.6039216, B 0.5176471, alpha 1.0, hue 0.029142816, saturation 0.8196735, and chroma 0.209995.
     * It can be represented as a packed float with the constant {@code -0x1.093506p126F}.
     * <pre>
     * <font style='background-color: #F03663;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F03663; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F03663;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F03663'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F03663'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F03663'>&nbsp;@&nbsp;</font><font style='background-color: #F03663; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F03663;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F03663; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PINK = -0x1.093506p126F;
    static { NAMED.put("Deep Pink", -0x1.093506p126F); LIST.add(-0x1.093506p126F); }

    /**
     * This color constant "Light Pink" has RGBA8888 code {@code FFE3EBFF}, L 0.9254902, A 0.5137255, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.08, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.ff07d8p125F}.
     * <pre>
     * <font style='background-color: #FFE3EB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFE3EB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFE3EB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFE3EB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFE3EB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFE3EB'>&nbsp;@&nbsp;</font><font style='background-color: #FFE3EB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFE3EB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFE3EB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_PINK = -0x1.ff07d8p125F;
    static { NAMED.put("Light Pink", -0x1.ff07d8p125F); LIST.add(-0x1.ff07d8p125F); }

    /**
     * This color constant "Moderate Pink" has RGBA8888 code {@code EAA9B5FF}, L 0.7411765, A 0.53333336, B 0.5019608, alpha 1.0, hue 0.017611582, saturation 0.21059036, and chroma 0.06652104.
     * It can be represented as a packed float with the constant {@code -0x1.01117ap126F}.
     * <pre>
     * <font style='background-color: #EAA9B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EAA9B5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EAA9B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EAA9B5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EAA9B5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EAA9B5'>&nbsp;@&nbsp;</font><font style='background-color: #EAA9B5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EAA9B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EAA9B5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_PINK = -0x1.01117ap126F;
    static { NAMED.put("Moderate Pink", -0x1.01117ap126F); LIST.add(-0x1.01117ap126F); }

    /**
     * This color constant "Dark Pink" has RGBA8888 code {@code C96674FF}, L 0.5294118, A 0.5568628, B 0.50980395, alpha 1.0, hue 0.031416386, saturation 0.443393, and chroma 0.114952646.
     * It can be represented as a packed float with the constant {@code -0x1.051d0ep126F}.
     * <pre>
     * <font style='background-color: #C96674;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C96674; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C96674;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C96674'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C96674'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C96674'>&nbsp;@&nbsp;</font><font style='background-color: #C96674; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C96674;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C96674; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_PINK = -0x1.051d0ep126F;
    static { NAMED.put("Dark Pink", -0x1.051d0ep126F); LIST.add(-0x1.051d0ep126F); }

    /**
     * This color constant "Pale Pink" has RGBA8888 code {@code FDEEF2FF}, L 0.9529412, A 0.5058824, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.039215688, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.ff03e6p125F}.
     * <pre>
     * <font style='background-color: #FDEEF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDEEF2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDEEF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FDEEF2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FDEEF2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FDEEF2'>&nbsp;@&nbsp;</font><font style='background-color: #FDEEF2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDEEF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDEEF2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PINK = -0x1.ff03e6p125F;
    static { NAMED.put("Pale Pink", -0x1.ff03e6p125F); LIST.add(-0x1.ff03e6p125F); }

    /**
     * This color constant "Grayish Pink" has RGBA8888 code {@code D6B2BBFF}, L 0.7411765, A 0.5176471, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.11494253, and chroma 0.0353726.
     * It can be represented as a packed float with the constant {@code -0x1.ff097ap125F}.
     * <pre>
     * <font style='background-color: #D6B2BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D6B2BB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D6B2BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D6B2BB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D6B2BB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D6B2BB'>&nbsp;@&nbsp;</font><font style='background-color: #D6B2BB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D6B2BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D6B2BB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_PINK = -0x1.ff097ap125F;
    static { NAMED.put("Grayish Pink", -0x1.ff097ap125F); LIST.add(-0x1.ff097ap125F); }

    /**
     * This color constant "Pinkish White" has RGBA8888 code {@code FEF6F8FF}, L 0.9764706, A 0.5019608, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.01923077, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.ff01f2p125F}.
     * <pre>
     * <font style='background-color: #FEF6F8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FEF6F8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FEF6F8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FEF6F8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FEF6F8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FEF6F8'>&nbsp;@&nbsp;</font><font style='background-color: #FEF6F8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FEF6F8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FEF6F8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINKISH_WHITE = -0x1.ff01f2p125F;
    static { NAMED.put("Pinkish White", -0x1.ff01f2p125F); LIST.add(-0x1.ff01f2p125F); }

    /**
     * This color constant "Pinkish Gray" has RGBA8888 code {@code D6C0C5FF}, L 0.78039217, A 0.50980395, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.06666667, and chroma 0.019918045.
     * It can be represented as a packed float with the constant {@code -0x1.ff058ep125F}.
     * <pre>
     * <font style='background-color: #D6C0C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D6C0C5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D6C0C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D6C0C5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D6C0C5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D6C0C5'>&nbsp;@&nbsp;</font><font style='background-color: #D6C0C5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D6C0C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D6C0C5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINKISH_GRAY = -0x1.ff058ep125F;
    static { NAMED.put("Pinkish Gray", -0x1.ff058ep125F); LIST.add(-0x1.ff058ep125F); }

    /**
     * This color constant "Vivid Red" has RGBA8888 code {@code A8062AFF}, L 0.33333334, A 0.5882353, B 0.5294118, alpha 1.0, hue 0.053275164, saturation 0.95496434, and chroma 0.18528971.
     * It can be represented as a packed float with the constant {@code -0x1.0f2caap126F}.
     * <pre>
     * <font style='background-color: #A8062A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A8062A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A8062A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A8062A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A8062A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A8062A'>&nbsp;@&nbsp;</font><font style='background-color: #A8062A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A8062A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A8062A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_RED = -0x1.0f2caap126F;
    static { NAMED.put("Vivid Red", -0x1.0f2caap126F); LIST.add(-0x1.0f2caap126F); }

    /**
     * This color constant "Strong Red" has RGBA8888 code {@code 982038FF}, L 0.32156864, A 0.5764706, B 0.5176471, alpha 1.0, hue 0.03898975, saturation 0.82462114, and chroma 0.15634763.
     * It can be represented as a packed float with the constant {@code -0x1.0926a4p126F}.
     * <pre>
     * <font style='background-color: #982038;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #982038; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #982038;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #982038'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #982038'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #982038'>&nbsp;@&nbsp;</font><font style='background-color: #982038; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #982038;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #982038; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_RED = -0x1.0926a4p126F;
    static { NAMED.put("Strong Red", -0x1.0926a4p126F); LIST.add(-0x1.0926a4p126F); }

    /**
     * This color constant "Deep Red" has RGBA8888 code {@code 650221FF}, L 0.2, A 0.5647059, B 0.5137255, alpha 1.0, hue 0.036779337, saturation 0.9440134, and chroma 0.13177444.
     * It can be represented as a packed float with the constant {@code -0x1.072066p126F}.
     * <pre>
     * <font style='background-color: #650221;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #650221; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #650221;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #650221'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #650221'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #650221'>&nbsp;@&nbsp;</font><font style='background-color: #650221; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #650221;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #650221; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED = -0x1.072066p126F;
    static { NAMED.put("Deep Red", -0x1.072066p126F); LIST.add(-0x1.072066p126F); }

    /**
     * This color constant "Very Deep Red" has RGBA8888 code {@code 43061AFF}, L 0.13333334, A 0.54901963, B 0.5058824, alpha 1.0, hue 0.024294632, saturation 0.93949616, and chroma 0.098356865.
     * It can be represented as a packed float with the constant {@code -0x1.031844p126F}.
     * <pre>
     * <font style='background-color: #43061A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #43061A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #43061A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #43061A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #43061A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #43061A'>&nbsp;@&nbsp;</font><font style='background-color: #43061A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #43061A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #43061A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DEEP_RED = -0x1.031844p126F;
    static { NAMED.put("Very Deep Red", -0x1.031844p126F); LIST.add(-0x1.031844p126F); }

    /**
     * This color constant "Moderate Red" has RGBA8888 code {@code 903240FF}, L 0.33333334, A 0.56078434, B 0.5137255, alpha 1.0, hue 0.03898975, saturation 0.64676166, and chroma 0.124142565.
     * It can be represented as a packed float with the constant {@code -0x1.071eaap126F}.
     * <pre>
     * <font style='background-color: #903240;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #903240; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #903240;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #903240'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #903240'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #903240'>&nbsp;@&nbsp;</font><font style='background-color: #903240; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #903240;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #903240; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_RED = -0x1.071eaap126F;
    static { NAMED.put("Moderate Red", -0x1.071eaap126F); LIST.add(-0x1.071eaap126F); }

    /**
     * This color constant "Dark Red" has RGBA8888 code {@code 581322FF}, L 0.18431373, A 0.5529412, B 0.50980395, alpha 1.0, hue 0.03359654, saturation 0.81816125, and chroma 0.107261956.
     * It can be represented as a packed float with the constant {@code -0x1.051a5ep126F}.
     * <pre>
     * <font style='background-color: #581322;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #581322; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #581322;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #581322'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #581322'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #581322'>&nbsp;@&nbsp;</font><font style='background-color: #581322; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #581322;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #581322; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_RED = -0x1.051a5ep126F;
    static { NAMED.put("Dark Red", -0x1.051a5ep126F); LIST.add(-0x1.051a5ep126F); }

    /**
     * This color constant "Very Dark Red" has RGBA8888 code {@code 34081BFF}, L 0.10980392, A 0.5411765, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.88, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.ff1438p125F}.
     * <pre>
     * <font style='background-color: #34081B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #34081B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #34081B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #34081B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #34081B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #34081B'>&nbsp;@&nbsp;</font><font style='background-color: #34081B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #34081B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #34081B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_RED = -0x1.ff1438p125F;
    static { NAMED.put("Very Dark Red", -0x1.ff1438p125F); LIST.add(-0x1.ff1438p125F); }

    /**
     * This color constant "Light Grayish Red" has RGBA8888 code {@code B1707EFF}, L 0.52156866, A 0.5372549, B 0.5019608, alpha 1.0, hue 0.0158628, saturation 0.29558456, and chroma 0.07432148.
     * It can be represented as a packed float with the constant {@code -0x1.01130ap126F}.
     * <pre>
     * <font style='background-color: #B1707E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1707E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1707E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B1707E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B1707E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B1707E'>&nbsp;@&nbsp;</font><font style='background-color: #B1707E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1707E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1707E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAYISH_RED = -0x1.01130ap126F;
    static { NAMED.put("Light Grayish Red", -0x1.01130ap126F); LIST.add(-0x1.01130ap126F); }

    /**
     * This color constant "Grayish Red" has RGBA8888 code {@code 7E454DFF}, L 0.34117648, A 0.5372549, B 0.5058824, alpha 1.0, hue 0.031416386, saturation 0.39223227, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.0312aep126F}.
     * <pre>
     * <font style='background-color: #7E454D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E454D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E454D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7E454D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7E454D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7E454D'>&nbsp;@&nbsp;</font><font style='background-color: #7E454D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E454D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E454D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_RED = -0x1.0312aep126F;
    static { NAMED.put("Grayish Red", -0x1.0312aep126F); LIST.add(-0x1.0312aep126F); }

    /**
     * This color constant "Dark Grayish Red" has RGBA8888 code {@code 3D272DFF}, L 0.18039216, A 0.5176471, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.2857143, and chroma 0.0353726.
     * It can be represented as a packed float with the constant {@code -0x1.ff085cp125F}.
     * <pre>
     * <font style='background-color: #3D272D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3D272D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3D272D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3D272D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3D272D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3D272D'>&nbsp;@&nbsp;</font><font style='background-color: #3D272D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3D272D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3D272D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_RED = -0x1.ff085cp125F;
    static { NAMED.put("Dark Grayish Red", -0x1.ff085cp125F); LIST.add(-0x1.ff085cp125F); }

    /**
     * This color constant "Blackish Red" has RGBA8888 code {@code 25161BFF}, L 0.105882354, A 0.5137255, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.32, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.ff0636p125F}.
     * <pre>
     * <font style='background-color: #25161B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #25161B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #25161B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #25161B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #25161B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #25161B'>&nbsp;@&nbsp;</font><font style='background-color: #25161B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #25161B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #25161B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACKISH_RED = -0x1.ff0636p125F;
    static { NAMED.put("Blackish Red", -0x1.ff0636p125F); LIST.add(-0x1.ff0636p125F); }

    /**
     * This color constant "Reddish Gray" has RGBA8888 code {@code 81696FFF}, L 0.4392157, A 0.5137255, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.12903225, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.ff06ep125F}.
     * <pre>
     * <font style='background-color: #81696F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #81696F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #81696F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #81696F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #81696F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #81696F'>&nbsp;@&nbsp;</font><font style='background-color: #81696F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #81696F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #81696F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float REDDISH_GRAY = -0x1.ff06ep125F;
    static { NAMED.put("Reddish Gray", -0x1.ff06ep125F); LIST.add(-0x1.ff06ep125F); }

    /**
     * This color constant "Dark Reddish Gray" has RGBA8888 code {@code 48393DFF}, L 0.23921569, A 0.50980395, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.14285715, and chroma 0.019918045.
     * It can be represented as a packed float with the constant {@code -0x1.ff047ap125F}.
     * <pre>
     * <font style='background-color: #48393D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #48393D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #48393D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #48393D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #48393D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #48393D'>&nbsp;@&nbsp;</font><font style='background-color: #48393D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #48393D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #48393D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_REDDISH_GRAY = -0x1.ff047ap125F;
    static { NAMED.put("Dark Reddish Gray", -0x1.ff047ap125F); LIST.add(-0x1.ff047ap125F); }

    /**
     * This color constant "Vivid Yellowish Pink" has RGBA8888 code {@code FF6837FF}, L 0.6039216, A 0.5686275, B 0.5568628, alpha 1.0, hue 0.11057128, saturation 0.7682213, and chroma 0.17755185.
     * It can be represented as a packed float with the constant {@code -0x1.1d2334p126F}.
     * <pre>
     * <font style='background-color: #FF6837;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6837; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6837;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF6837'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF6837'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF6837'>&nbsp;@&nbsp;</font><font style='background-color: #FF6837; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6837;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6837; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_YELLOWISH_PINK = -0x1.1d2334p126F;
    static { NAMED.put("Vivid Yellowish Pink", -0x1.1d2334p126F); LIST.add(-0x1.1d2334p126F); }

    /**
     * This color constant "Strong Yellowish Pink" has RGBA8888 code {@code FE887AFF}, L 0.6784314, A 0.5568628, B 0.5294118, alpha 1.0, hue 0.0779789, saturation 0.41463414, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.0f1d5ap126F}.
     * <pre>
     * <font style='background-color: #FE887A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FE887A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FE887A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FE887A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FE887A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FE887A'>&nbsp;@&nbsp;</font><font style='background-color: #FE887A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FE887A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FE887A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_YELLOWISH_PINK = -0x1.0f1d5ap126F;
    static { NAMED.put("Strong Yellowish Pink", -0x1.0f1d5ap126F); LIST.add(-0x1.0f1d5ap126F); }

    /**
     * This color constant "Deep Yellowish Pink" has RGBA8888 code {@code F92845FF}, L 0.50980395, A 0.60784316, B 0.5372549, alpha 1.0, hue 0.054594062, saturation 0.8875265, and chroma 0.22730213.
     * It can be represented as a packed float with the constant {@code -0x1.133704p126F}.
     * <pre>
     * <font style='background-color: #F92845;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F92845; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F92845;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F92845'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F92845'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F92845'>&nbsp;@&nbsp;</font><font style='background-color: #F92845; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F92845;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F92845; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOWISH_PINK = -0x1.133704p126F;
    static { NAMED.put("Deep Yellowish Pink", -0x1.133704p126F); LIST.add(-0x1.133704p126F); }

    /**
     * This color constant "Light Yellowish Pink" has RGBA8888 code {@code FFE2D5FF}, L 0.9137255, A 0.50980395, B 0.50980395, alpha 1.0, hue 0.125, saturation 0.11785112, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.0505d2p126F}.
     * <pre>
     * <font style='background-color: #FFE2D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFE2D5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFE2D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFE2D5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFE2D5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFE2D5'>&nbsp;@&nbsp;</font><font style='background-color: #FFE2D5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFE2D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFE2D5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_YELLOWISH_PINK = -0x1.0505d2p126F;
    static { NAMED.put("Light Yellowish Pink", -0x1.0505d2p126F); LIST.add(-0x1.0505d2p126F); }

    /**
     * This color constant "Moderate Yellowish Pink" has RGBA8888 code {@code F0AA97FF}, L 0.7411765, A 0.5294118, B 0.52156866, alpha 1.0, hue 0.10241663, saturation 0.26666668, and chroma 0.07266045.
     * It can be represented as a packed float with the constant {@code -0x1.0b0f7ap126F}.
     * <pre>
     * <font style='background-color: #F0AA97;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0AA97; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0AA97;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F0AA97'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F0AA97'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F0AA97'>&nbsp;@&nbsp;</font><font style='background-color: #F0AA97; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0AA97;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0AA97; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_YELLOWISH_PINK = -0x1.0b0f7ap126F;
    static { NAMED.put("Moderate Yellowish Pink", -0x1.0b0f7ap126F); LIST.add(-0x1.0b0f7ap126F); }

    /**
     * This color constant "Dark Yellowish Pink" has RGBA8888 code {@code CB6362FF}, L 0.52156866, A 0.5568628, B 0.52156866, alpha 1.0, hue 0.0605594, saturation 0.4751616, and chroma 0.12115674.
     * It can be represented as a packed float with the constant {@code -0x1.0b1d0ap126F}.
     * <pre>
     * <font style='background-color: #CB6362;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CB6362; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CB6362;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CB6362'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CB6362'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CB6362'>&nbsp;@&nbsp;</font><font style='background-color: #CB6362; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CB6362;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CB6362; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_YELLOWISH_PINK = -0x1.0b1d0ap126F;
    static { NAMED.put("Dark Yellowish Pink", -0x1.0b1d0ap126F); LIST.add(-0x1.0b1d0ap126F); }

    /**
     * This color constant "Pale Yellowish Pink" has RGBA8888 code {@code FFEFF3FF}, L 0.95686275, A 0.5058824, B 0.49803922, alpha 1.0, hue 0.0, saturation 0.03883495, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.ff03e8p125F}.
     * <pre>
     * <font style='background-color: #FFEFF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFEFF3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFEFF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFEFF3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFEFF3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFEFF3'>&nbsp;@&nbsp;</font><font style='background-color: #FFEFF3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFEFF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFEFF3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOWISH_PINK = -0x1.ff03e8p125F;
    static { NAMED.put("Pale Yellowish Pink", -0x1.ff03e8p125F); LIST.add(-0x1.ff03e8p125F); }

    /**
     * This color constant "Grayish Yellowish Pink" has RGBA8888 code {@code D2ADB0FF}, L 0.72156864, A 0.5176471, B 0.5019608, alpha 1.0, hue 0.031416386, saturation 0.12140523, and chroma 0.0353726.
     * It can be represented as a packed float with the constant {@code -0x1.01097p126F}.
     * <pre>
     * <font style='background-color: #D2ADB0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2ADB0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2ADB0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D2ADB0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D2ADB0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D2ADB0'>&nbsp;@&nbsp;</font><font style='background-color: #D2ADB0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2ADB0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2ADB0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_YELLOWISH_PINK = -0x1.01097p126F;
    static { NAMED.put("Grayish Yellowish Pink", -0x1.01097p126F); LIST.add(-0x1.01097p126F); }

    /**
     * This color constant "Brownish Pink" has RGBA8888 code {@code D5B4B0FF}, L 0.7411765, A 0.5137255, B 0.5058824, alpha 1.0, hue 0.07379155, saturation 0.10280772, and chroma 0.029749114.
     * It can be represented as a packed float with the constant {@code -0x1.03077ap126F}.
     * <pre>
     * <font style='background-color: #D5B4B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5B4B0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5B4B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D5B4B0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D5B4B0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D5B4B0'>&nbsp;@&nbsp;</font><font style='background-color: #D5B4B0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5B4B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5B4B0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BROWNISH_PINK = -0x1.03077ap126F;
    static { NAMED.put("Brownish Pink", -0x1.03077ap126F); LIST.add(-0x1.03077ap126F); }

    /**
     * This color constant "Vivid Reddish Orange" has RGBA8888 code {@code C12500FF}, L 0.39607844, A 0.5803922, B 0.5529412, alpha 1.0, hue 0.09358362, saturation 0.9177767, and chroma 0.19176465.
     * It can be represented as a packed float with the constant {@code -0x1.1b28cap126F}.
     * <pre>
     * <font style='background-color: #C12500;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C12500; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C12500;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C12500'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C12500'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C12500'>&nbsp;@&nbsp;</font><font style='background-color: #C12500; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C12500;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C12500; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_REDDISH_ORANGE = -0x1.1b28cap126F;
    static { NAMED.put("Vivid Reddish Orange", -0x1.1b28cap126F); LIST.add(-0x1.1b28cap126F); }

    /**
     * This color constant "Strong Reddish Orange" has RGBA8888 code {@code C34421FF}, L 0.4392157, A 0.5647059, B 0.54901963, alpha 1.0, hue 0.103904024, saturation 0.80758244, and chroma 0.16172063.
     * It can be represented as a packed float with the constant {@code -0x1.1920ep126F}.
     * <pre>
     * <font style='background-color: #C34421;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C34421; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C34421;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C34421'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C34421'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C34421'>&nbsp;@&nbsp;</font><font style='background-color: #C34421; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C34421;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C34421; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_REDDISH_ORANGE = -0x1.1920ep126F;
    static { NAMED.put("Strong Reddish Orange", -0x1.1920ep126F); LIST.add(-0x1.1920ep126F); }

    /**
     * This color constant "Deep Reddish Orange" has RGBA8888 code {@code 8A2007FF}, L 0.28627452, A 0.56078434, B 0.5411765, alpha 1.0, hue 0.09585716, saturation 0.9030925, and chroma 0.14626285.
     * It can be represented as a packed float with the constant {@code -0x1.151e92p126F}.
     * <pre>
     * <font style='background-color: #8A2007;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A2007; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A2007;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8A2007'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8A2007'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8A2007'>&nbsp;@&nbsp;</font><font style='background-color: #8A2007; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A2007;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A2007; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_REDDISH_ORANGE = -0x1.151e92p126F;
    static { NAMED.put("Deep Reddish Orange", -0x1.151e92p126F); LIST.add(-0x1.151e92p126F); }

    /**
     * This color constant "Moderate Reddish Orange" has RGBA8888 code {@code BC4F3AFF}, L 0.4509804, A 0.5568628, B 0.5372549, alpha 1.0, hue 0.09358362, saturation 0.6009252, and chroma 0.13542919.
     * It can be represented as a packed float with the constant {@code -0x1.131ce6p126F}.
     * <pre>
     * <font style='background-color: #BC4F3A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BC4F3A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BC4F3A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BC4F3A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BC4F3A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BC4F3A'>&nbsp;@&nbsp;</font><font style='background-color: #BC4F3A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BC4F3A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BC4F3A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_REDDISH_ORANGE = -0x1.131ce6p126F;
    static { NAMED.put("Moderate Reddish Orange", -0x1.131ce6p126F); LIST.add(-0x1.131ce6p126F); }

    /**
     * This color constant "Dark Reddish Orange" has RGBA8888 code {@code 7F2D1BFF}, L 0.28627452, A 0.54901963, B 0.53333336, alpha 1.0, hue 0.096375585, saturation 0.7354134, and chroma 0.11809544.
     * It can be represented as a packed float with the constant {@code -0x1.111892p126F}.
     * <pre>
     * <font style='background-color: #7F2D1B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F2D1B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F2D1B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7F2D1B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7F2D1B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7F2D1B'>&nbsp;@&nbsp;</font><font style='background-color: #7F2D1B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F2D1B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F2D1B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_REDDISH_ORANGE = -0x1.111892p126F;
    static { NAMED.put("Dark Reddish Orange", -0x1.111892p126F); LIST.add(-0x1.111892p126F); }

    /**
     * This color constant "Grayish Reddish Orange" has RGBA8888 code {@code AD594CFF}, L 0.4509804, A 0.54509807, B 0.5254902, alpha 1.0, hue 0.08404553, saturation 0.44102997, and chroma 0.10320191.
     * It can be represented as a packed float with the constant {@code -0x1.0d16e6p126F}.
     * <pre>
     * <font style='background-color: #AD594C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD594C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD594C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AD594C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AD594C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AD594C'>&nbsp;@&nbsp;</font><font style='background-color: #AD594C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD594C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD594C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_REDDISH_ORANGE = -0x1.0d16e6p126F;
    static { NAMED.put("Grayish Reddish Orange", -0x1.0d16e6p126F); LIST.add(-0x1.0d16e6p126F); }

    /**
     * This color constant "Strong Reddish Brown" has RGBA8888 code {@code 690D00FF}, L 0.20784314, A 0.5568628, B 0.53333336, alpha 1.0, hue 0.08601036, saturation 0.9206766, and chroma 0.13131043.
     * It can be represented as a packed float with the constant {@code -0x1.111c6ap126F}.
     * <pre>
     * <font style='background-color: #690D00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #690D00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #690D00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #690D00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #690D00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #690D00'>&nbsp;@&nbsp;</font><font style='background-color: #690D00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #690D00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #690D00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_REDDISH_BROWN = -0x1.111c6ap126F;
    static { NAMED.put("Strong Reddish Brown", -0x1.111c6ap126F); LIST.add(-0x1.111c6ap126F); }

    /**
     * This color constant "Deep Reddish Brown" has RGBA8888 code {@code 4A000BFF}, L 0.14509805, A 0.54901963, B 0.52156866, alpha 1.0, hue 0.0688196, saturation 0.9545214, and chroma 0.106691405.
     * It can be represented as a packed float with the constant {@code -0x1.0b184ap126F}.
     * <pre>
     * <font style='background-color: #4A000B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A000B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A000B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4A000B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4A000B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4A000B'>&nbsp;@&nbsp;</font><font style='background-color: #4A000B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A000B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A000B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_REDDISH_BROWN = -0x1.0b184ap126F;
    static { NAMED.put("Deep Reddish Brown", -0x1.0b184ap126F); LIST.add(-0x1.0b184ap126F); }

    /**
     * This color constant "Light Reddish Brown" has RGBA8888 code {@code A16052FF}, L 0.4509804, A 0.53333336, B 0.52156866, alpha 1.0, hue 0.09358362, saturation 0.36055514, and chroma 0.079095535.
     * It can be represented as a packed float with the constant {@code -0x1.0b10e6p126F}.
     * <pre>
     * <font style='background-color: #A16052;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A16052; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A16052;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A16052'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A16052'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A16052'>&nbsp;@&nbsp;</font><font style='background-color: #A16052; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A16052;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A16052; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_REDDISH_BROWN = -0x1.0b10e6p126F;
    static { NAMED.put("Light Reddish Brown", -0x1.0b10e6p126F); LIST.add(-0x1.0b10e6p126F); }

    /**
     * This color constant "Moderate Reddish Brown" has RGBA8888 code {@code 642C28FF}, L 0.24313726, A 0.5372549, B 0.5176471, alpha 1.0, hue 0.07379155, saturation 0.53239715, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.09127cp126F}.
     * <pre>
     * <font style='background-color: #642C28;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #642C28; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #642C28;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #642C28'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #642C28'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #642C28'>&nbsp;@&nbsp;</font><font style='background-color: #642C28; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #642C28;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #642C28; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_REDDISH_BROWN = -0x1.09127cp126F;
    static { NAMED.put("Moderate Reddish Brown", -0x1.09127cp126F); LIST.add(-0x1.09127cp126F); }

    /**
     * This color constant "Dark Reddish Brown" has RGBA8888 code {@code 341317FF}, L 0.12156863, A 0.5294118, B 0.5058824, alpha 1.0, hue 0.03898975, saturation 0.6108304, and chroma 0.059754133.
     * It can be represented as a packed float with the constant {@code -0x1.030e3ep126F}.
     * <pre>
     * <font style='background-color: #341317;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #341317; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #341317;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #341317'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #341317'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #341317'>&nbsp;@&nbsp;</font><font style='background-color: #341317; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #341317;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #341317; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_REDDISH_BROWN = -0x1.030e3ep126F;
    static { NAMED.put("Dark Reddish Brown", -0x1.030e3ep126F); LIST.add(-0x1.030e3ep126F); }

    /**
     * This color constant "Light Grayish Reddish Brown" has RGBA8888 code {@code 8C6962FF}, L 0.44705883, A 0.5176471, B 0.50980395, alpha 1.0, hue 0.08601036, saturation 0.18809521, and chroma 0.040217306.
     * It can be represented as a packed float with the constant {@code -0x1.0508e4p126F}.
     * <pre>
     * <font style='background-color: #8C6962;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C6962; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C6962;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8C6962'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8C6962'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8C6962'>&nbsp;@&nbsp;</font><font style='background-color: #8C6962; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C6962;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C6962; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAYISH_REDDISH_BROWN = -0x1.0508e4p126F;
    static { NAMED.put("Light Grayish Reddish Brown", -0x1.0508e4p126F); LIST.add(-0x1.0508e4p126F); }

    /**
     * This color constant "Grayish Reddish Brown" has RGBA8888 code {@code 533736FF}, L 0.24705882, A 0.5176471, B 0.5058824, alpha 1.0, hue 0.0605594, saturation 0.2564364, and chroma 0.03705794.
     * It can be represented as a packed float with the constant {@code -0x1.03087ep126F}.
     * <pre>
     * <font style='background-color: #533736;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #533736; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #533736;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #533736'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #533736'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #533736'>&nbsp;@&nbsp;</font><font style='background-color: #533736; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #533736;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #533736; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_REDDISH_BROWN = -0x1.03087ep126F;
    static { NAMED.put("Grayish Reddish Brown", -0x1.03087ep126F); LIST.add(-0x1.03087ep126F); }

    /**
     * This color constant "Dark Grayish Reddish Brown" has RGBA8888 code {@code 321C1BFF}, L 0.13333334, A 0.5176471, B 0.5058824, alpha 1.0, hue 0.0605594, saturation 0.3846546, and chroma 0.03705794.
     * It can be represented as a packed float with the constant {@code -0x1.030844p126F}.
     * <pre>
     * <font style='background-color: #321C1B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #321C1B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #321C1B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #321C1B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #321C1B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #321C1B'>&nbsp;@&nbsp;</font><font style='background-color: #321C1B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #321C1B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #321C1B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_REDDISH_BROWN = -0x1.030844p126F;
    static { NAMED.put("Dark Grayish Reddish Brown", -0x1.030844p126F); LIST.add(-0x1.030844p126F); }

    /**
     * This color constant "Vivid Orange" has RGBA8888 code {@code DB6D00FF}, L 0.5529412, A 0.5411765, B 0.5647059, alpha 1.0, hue 0.1585965, saturation 0.9203844, and chroma 0.15279381.
     * It can be represented as a packed float with the constant {@code -0x1.21151ap126F}.
     * <pre>
     * <font style='background-color: #DB6D00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB6D00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB6D00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DB6D00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DB6D00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DB6D00'>&nbsp;@&nbsp;</font><font style='background-color: #DB6D00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB6D00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB6D00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_ORANGE = -0x1.21151ap126F;
    static { NAMED.put("Vivid Orange", -0x1.21151ap126F); LIST.add(-0x1.21151ap126F); }

    /**
     * This color constant "Strong Orange" has RGBA8888 code {@code D87314FF}, L 0.56078434, A 0.5372549, B 0.5647059, alpha 1.0, hue 0.16537361, saturation 0.91735274, and chroma 0.14874557.
     * It can be represented as a packed float with the constant {@code -0x1.21131ep126F}.
     * <pre>
     * <font style='background-color: #D87314;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D87314; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D87314;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D87314'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D87314'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D87314'>&nbsp;@&nbsp;</font><font style='background-color: #D87314; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D87314;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D87314; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_ORANGE = -0x1.21131ep126F;
    static { NAMED.put("Strong Orange", -0x1.21131ep126F); LIST.add(-0x1.21131ep126F); }

    /**
     * This color constant "Deep Orange" has RGBA8888 code {@code A74A00FF}, L 0.40392157, A 0.54509807, B 0.5529412, alpha 1.0, hue 0.1372184, saturation 0.92195445, and chroma 0.13854803.
     * It can be represented as a packed float with the constant {@code -0x1.1b16cep126F}.
     * <pre>
     * <font style='background-color: #A74A00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A74A00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A74A00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A74A00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A74A00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A74A00'>&nbsp;@&nbsp;</font><font style='background-color: #A74A00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A74A00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A74A00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE = -0x1.1b16cep126F;
    static { NAMED.put("Deep Orange", -0x1.1b16cep126F); LIST.add(-0x1.1b16cep126F); }

    /**
     * This color constant "Light Orange" has RGBA8888 code {@code FFC09DFF}, L 0.8117647, A 0.52156866, B 0.5294118, alpha 1.0, hue 0.14758338, saturation 0.33333334, and chroma 0.07266045.
     * It can be represented as a packed float with the constant {@code -0x1.0f0b9ep126F}.
     * <pre>
     * <font style='background-color: #FFC09D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFC09D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFC09D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFC09D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFC09D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFC09D'>&nbsp;@&nbsp;</font><font style='background-color: #FFC09D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFC09D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFC09D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_ORANGE = -0x1.0f0b9ep126F;
    static { NAMED.put("Light Orange", -0x1.0f0b9ep126F); LIST.add(-0x1.0f0b9ep126F); }

    /**
     * This color constant "Moderate Orange" has RGBA8888 code {@code E5793FFF}, L 0.59607846, A 0.54509807, B 0.5529412, alpha 1.0, hue 0.1372184, saturation 0.7231015, and chroma 0.13854803.
     * It can be represented as a packed float with the constant {@code -0x1.1b173p126F}.
     * <pre>
     * <font style='background-color: #E5793F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E5793F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E5793F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E5793F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E5793F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E5793F'>&nbsp;@&nbsp;</font><font style='background-color: #E5793F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E5793F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E5793F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_ORANGE = -0x1.1b173p126F;
    static { NAMED.put("Moderate Orange", -0x1.1b173p126F); LIST.add(-0x1.1b173p126F); }

    /**
     * This color constant "Brownish Orange" has RGBA8888 code {@code 925125FF}, L 0.3882353, A 0.5294118, B 0.5411765, alpha 1.0, hue 0.14992374, saturation 0.73521465, and chroma 0.1008085.
     * It can be represented as a packed float with the constant {@code -0x1.150ec6p126F}.
     * <pre>
     * <font style='background-color: #925125;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #925125; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #925125;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #925125'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #925125'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #925125'>&nbsp;@&nbsp;</font><font style='background-color: #925125; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #925125;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #925125; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BROWNISH_ORANGE = -0x1.150ec6p126F;
    static { NAMED.put("Brownish Orange", -0x1.150ec6p126F); LIST.add(-0x1.150ec6p126F); }

    /**
     * This color constant "Strong Brown" has RGBA8888 code {@code 6B3000FF}, L 0.25882354, A 0.5294118, B 0.5411765, alpha 1.0, hue 0.14992374, saturation 0.97153366, and chroma 0.1008085.
     * It can be represented as a packed float with the constant {@code -0x1.150e84p126F}.
     * <pre>
     * <font style='background-color: #6B3000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6B3000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6B3000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6B3000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6B3000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6B3000'>&nbsp;@&nbsp;</font><font style='background-color: #6B3000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6B3000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6B3000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_BROWN = -0x1.150e84p126F;
    static { NAMED.put("Strong Brown", -0x1.150e84p126F); LIST.add(-0x1.150e84p126F); }

    /**
     * This color constant "Deep Brown" has RGBA8888 code {@code 401100FF}, L 0.13333334, A 0.53333336, B 0.5254902, alpha 1.0, hue 0.10520855, saturation 0.91214037, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.0d1044p126F}.
     * <pre>
     * <font style='background-color: #401100;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #401100; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #401100;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #401100'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #401100'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #401100'>&nbsp;@&nbsp;</font><font style='background-color: #401100; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #401100;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #401100; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN = -0x1.0d1044p126F;
    static { NAMED.put("Deep Brown", -0x1.0d1044p126F); LIST.add(-0x1.0d1044p126F); }

    /**
     * This color constant "Light Brown" has RGBA8888 code {@code 9D6447FF}, L 0.4509804, A 0.5254902, B 0.5294118, alpha 1.0, hue 0.13559444, saturation 0.48318845, and chroma 0.07753685.
     * It can be represented as a packed float with the constant {@code -0x1.0f0ce6p126F}.
     * <pre>
     * <font style='background-color: #9D6447;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9D6447; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9D6447;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9D6447'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9D6447'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9D6447'>&nbsp;@&nbsp;</font><font style='background-color: #9D6447; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9D6447;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9D6447; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_BROWN = -0x1.0f0ce6p126F;
    static { NAMED.put("Light Brown", -0x1.0f0ce6p126F); LIST.add(-0x1.0f0ce6p126F); }

    /**
     * This color constant "Moderate Brown" has RGBA8888 code {@code 5A3423FF}, L 0.24313726, A 0.52156866, B 0.52156866, alpha 1.0, hue 0.125, saturation 0.5474375, and chroma 0.060766988.
     * It can be represented as a packed float with the constant {@code -0x1.0b0a7cp126F}.
     * <pre>
     * <font style='background-color: #5A3423;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A3423; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A3423;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5A3423'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5A3423'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5A3423'>&nbsp;@&nbsp;</font><font style='background-color: #5A3423; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A3423;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A3423; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_BROWN = -0x1.0b0a7cp126F;
    static { NAMED.put("Moderate Brown", -0x1.0b0a7cp126F); LIST.add(-0x1.0b0a7cp126F); }

    /**
     * This color constant "Dark Brown" has RGBA8888 code {@code 341505FF}, L 0.12156863, A 0.52156866, B 0.52156866, alpha 1.0, hue 0.125, saturation 0.8485281, and chroma 0.060766988.
     * It can be represented as a packed float with the constant {@code -0x1.0b0a3ep126F}.
     * <pre>
     * <font style='background-color: #341505;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #341505; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #341505;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #341505'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #341505'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #341505'>&nbsp;@&nbsp;</font><font style='background-color: #341505; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #341505;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #341505; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_BROWN = -0x1.0b0a3ep126F;
    static { NAMED.put("Dark Brown", -0x1.0b0a3ep126F); LIST.add(-0x1.0b0a3ep126F); }

    /**
     * This color constant "Light Grayish Brown" has RGBA8888 code {@code 87685AFF}, L 0.43529412, A 0.5137255, B 0.5137255, alpha 1.0, hue 0.125, saturation 0.25141573, and chroma 0.038669903.
     * It can be represented as a packed float with the constant {@code -0x1.0706dep126F}.
     * <pre>
     * <font style='background-color: #87685A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #87685A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #87685A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #87685A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #87685A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #87685A'>&nbsp;@&nbsp;</font><font style='background-color: #87685A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #87685A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #87685A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAYISH_BROWN = -0x1.0706dep126F;
    static { NAMED.put("Light Grayish Brown", -0x1.0706dep126F); LIST.add(-0x1.0706dep126F); }

    /**
     * This color constant "Grayish Brown" has RGBA8888 code {@code 513932FF}, L 0.24705882, A 0.5137255, B 0.50980395, alpha 1.0, hue 0.10241663, saturation 0.27027026, and chroma 0.033602834.
     * It can be represented as a packed float with the constant {@code -0x1.05067ep126F}.
     * <pre>
     * <font style='background-color: #513932;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #513932; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #513932;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #513932'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #513932'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #513932'>&nbsp;@&nbsp;</font><font style='background-color: #513932; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #513932;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #513932; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_BROWN = -0x1.05067ep126F;
    static { NAMED.put("Grayish Brown", -0x1.05067ep126F); LIST.add(-0x1.05067ep126F); }

    /**
     * This color constant "Dark Grayish Brown" has RGBA8888 code {@code 2D1F1BFF}, L 0.13333334, A 0.50980395, B 0.5058824, alpha 1.0, hue 0.09358362, saturation 0.25753936, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.030444p126F}.
     * <pre>
     * <font style='background-color: #2D1F1B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D1F1B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D1F1B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2D1F1B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2D1F1B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2D1F1B'>&nbsp;@&nbsp;</font><font style='background-color: #2D1F1B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D1F1B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D1F1B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_BROWN = -0x1.030444p126F;
    static { NAMED.put("Dark Grayish Brown", -0x1.030444p126F); LIST.add(-0x1.030444p126F); }

    /**
     * This color constant "Light Brownish Gray" has RGBA8888 code {@code 806B66FF}, L 0.4392157, A 0.50980395, B 0.5058824, alpha 1.0, hue 0.09358362, saturation 0.12222207, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.0304ep126F}.
     * <pre>
     * <font style='background-color: #806B66;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #806B66; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #806B66;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #806B66'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #806B66'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #806B66'>&nbsp;@&nbsp;</font><font style='background-color: #806B66; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #806B66;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #806B66; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_BROWNISH_GRAY = -0x1.0304ep126F;
    static { NAMED.put("Light Brownish Gray", -0x1.0304ep126F); LIST.add(-0x1.0304ep126F); }

    /**
     * This color constant "Brownish Gray" has RGBA8888 code {@code 483C3BFF}, L 0.24705882, A 0.5058824, B 0.5019608, alpha 1.0, hue 0.07379155, saturation 0.10400316, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.01027ep126F}.
     * <pre>
     * <font style='background-color: #483C3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #483C3B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #483C3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #483C3B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #483C3B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #483C3B'>&nbsp;@&nbsp;</font><font style='background-color: #483C3B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #483C3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #483C3B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BROWNISH_GRAY = -0x1.01027ep126F;
    static { NAMED.put("Brownish Gray", -0x1.01027ep126F); LIST.add(-0x1.01027ep126F); }

    /**
     * This color constant "Brilliant Orange Yellow" has RGBA8888 code {@code FEDC97FF}, L 0.8784314, A 0.5019608, B 0.5411765, alpha 1.0, hue 0.23557091, saturation 0.44181442, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.1501cp126F}.
     * <pre>
     * <font style='background-color: #FEDC97;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FEDC97; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FEDC97;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FEDC97'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FEDC97'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FEDC97'>&nbsp;@&nbsp;</font><font style='background-color: #FEDC97; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FEDC97;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FEDC97; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_ORANGE_YELLOW = -0x1.1501cp126F;
    static { NAMED.put("Brilliant Orange Yellow", -0x1.1501cp126F); LIST.add(-0x1.1501cp126F); }

    /**
     * This color constant "Strong Orange Yellow" has RGBA8888 code {@code EDA110FF}, L 0.69411767, A 0.5137255, B 0.5764706, alpha 1.0, hue 0.21858361, saturation 0.92709446, and chroma 0.15477823.
     * It can be represented as a packed float with the constant {@code -0x1.270762p126F}.
     * <pre>
     * <font style='background-color: #EDA110;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDA110; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDA110;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EDA110'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EDA110'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EDA110'>&nbsp;@&nbsp;</font><font style='background-color: #EDA110; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDA110;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDA110; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_ORANGE_YELLOW = -0x1.270762p126F;
    static { NAMED.put("Strong Orange Yellow", -0x1.270762p126F); LIST.add(-0x1.270762p126F); }

    /**
     * This color constant "Deep Orange Yellow" has RGBA8888 code {@code C17600FF}, L 0.53333336, A 0.52156866, B 0.5647059, alpha 1.0, hue 0.19599979, saturation 0.94882923, and chroma 0.13587911.
     * It can be represented as a packed float with the constant {@code -0x1.210b1p126F}.
     * <pre>
     * <font style='background-color: #C17600;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C17600; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C17600;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C17600'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C17600'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C17600'>&nbsp;@&nbsp;</font><font style='background-color: #C17600; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C17600;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C17600; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_YELLOW = -0x1.210b1p126F;
    static { NAMED.put("Deep Orange Yellow", -0x1.210b1p126F); LIST.add(-0x1.210b1p126F); }

    /**
     * This color constant "Light Orange Yellow" has RGBA8888 code {@code FFE1B9FF}, L 0.9019608, A 0.5058824, B 0.5254902, alpha 1.0, hue 0.20570697, saturation 0.27472112, and chroma 0.052115876.
     * It can be represented as a packed float with the constant {@code -0x1.0d03ccp126F}.
     * <pre>
     * <font style='background-color: #FFE1B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFE1B9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFE1B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFE1B9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFE1B9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFE1B9'>&nbsp;@&nbsp;</font><font style='background-color: #FFE1B9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFE1B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFE1B9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_ORANGE_YELLOW = -0x1.0d03ccp126F;
    static { NAMED.put("Light Orange Yellow", -0x1.0d03ccp126F); LIST.add(-0x1.0d03ccp126F); }

    /**
     * This color constant "Moderate Orange Yellow" has RGBA8888 code {@code F5A54CFF}, L 0.7176471, A 0.52156866, B 0.56078434, alpha 1.0, hue 0.19289984, saturation 0.72714925, and chroma 0.12849128.
     * It can be represented as a packed float with the constant {@code -0x1.1f0b6ep126F}.
     * <pre>
     * <font style='background-color: #F5A54C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5A54C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5A54C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F5A54C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F5A54C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F5A54C'>&nbsp;@&nbsp;</font><font style='background-color: #F5A54C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5A54C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5A54C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_ORANGE_YELLOW = -0x1.1f0b6ep126F;
    static { NAMED.put("Moderate Orange Yellow", -0x1.1f0b6ep126F); LIST.add(-0x1.1f0b6ep126F); }

    /**
     * This color constant "Dark Orange Yellow" has RGBA8888 code {@code B1742BFF}, L 0.50980395, A 0.5176471, B 0.5529412, alpha 1.0, hue 0.19540595, saturation 0.8035713, and chroma 0.11117382.
     * It can be represented as a packed float with the constant {@code -0x1.1b0904p126F}.
     * <pre>
     * <font style='background-color: #B1742B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1742B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1742B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B1742B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B1742B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B1742B'>&nbsp;@&nbsp;</font><font style='background-color: #B1742B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1742B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1742B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_ORANGE_YELLOW = -0x1.1b0904p126F;
    static { NAMED.put("Dark Orange Yellow", -0x1.1b0904p126F); LIST.add(-0x1.1b0904p126F); }

    /**
     * This color constant "Pale Orange Yellow" has RGBA8888 code {@code FBE2CCFF}, L 0.90588236, A 0.5058824, B 0.5137255, alpha 1.0, hue 0.17620845, saturation 0.15691705, and chroma 0.029749114.
     * It can be represented as a packed float with the constant {@code -0x1.0703cep126F}.
     * <pre>
     * <font style='background-color: #FBE2CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FBE2CC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FBE2CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FBE2CC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FBE2CC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FBE2CC'>&nbsp;@&nbsp;</font><font style='background-color: #FBE2CC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FBE2CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FBE2CC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_ORANGE_YELLOW = -0x1.0703cep126F;
    static { NAMED.put("Pale Orange Yellow", -0x1.0703cep126F); LIST.add(-0x1.0703cep126F); }

    /**
     * This color constant "Strong Yellowish Brown" has RGBA8888 code {@code 805004FF}, L 0.35686275, A 0.5137255, B 0.54901963, alpha 1.0, hue 0.20249216, saturation 0.9380325, and chroma 0.10141215.
     * It can be represented as a packed float with the constant {@code -0x1.1906b6p126F}.
     * <pre>
     * <font style='background-color: #805004;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #805004; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #805004;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #805004'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #805004'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #805004'>&nbsp;@&nbsp;</font><font style='background-color: #805004; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #805004;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #805004; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_YELLOWISH_BROWN = -0x1.1906b6p126F;
    static { NAMED.put("Strong Yellowish Brown", -0x1.1906b6p126F); LIST.add(-0x1.1906b6p126F); }

    /**
     * This color constant "Deep Yellowish Brown" has RGBA8888 code {@code 4A2E02FF}, L 0.20392157, A 0.50980395, B 0.53333336, alpha 1.0, hue 0.19879162, saturation 0.9035079, and chroma 0.069218926.
     * It can be represented as a packed float with the constant {@code -0x1.110468p126F}.
     * <pre>
     * <font style='background-color: #4A2E02;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A2E02; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A2E02;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4A2E02'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4A2E02'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4A2E02'>&nbsp;@&nbsp;</font><font style='background-color: #4A2E02; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A2E02;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A2E02; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOWISH_BROWN = -0x1.110468p126F;
    static { NAMED.put("Deep Yellowish Brown", -0x1.110468p126F); LIST.add(-0x1.110468p126F); }

    /**
     * This color constant "Light Yellowish Brown" has RGBA8888 code {@code C48E5EFF}, L 0.6039216, A 0.5176471, B 0.5372549, alpha 1.0, hue 0.17620845, saturation 0.50819725, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.130934p126F}.
     * <pre>
     * <font style='background-color: #C48E5E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C48E5E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C48E5E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C48E5E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C48E5E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C48E5E'>&nbsp;@&nbsp;</font><font style='background-color: #C48E5E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C48E5E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C48E5E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_YELLOWISH_BROWN = -0x1.130934p126F;
    static { NAMED.put("Light Yellowish Brown", -0x1.130934p126F); LIST.add(-0x1.130934p126F); }

    /**
     * This color constant "Moderate Yellowish Brown" has RGBA8888 code {@code 6B4F32FF}, L 0.33333334, A 0.50980395, B 0.5254902, alpha 1.0, hue 0.18555963, saturation 0.52522576, and chroma 0.054407768.
     * It can be represented as a packed float with the constant {@code -0x1.0d04aap126F}.
     * <pre>
     * <font style='background-color: #6B4F32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6B4F32; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6B4F32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6B4F32'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6B4F32'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6B4F32'>&nbsp;@&nbsp;</font><font style='background-color: #6B4F32; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6B4F32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6B4F32; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_YELLOWISH_BROWN = -0x1.0d04aap126F;
    static { NAMED.put("Moderate Yellowish Brown", -0x1.0d04aap126F); LIST.add(-0x1.0d04aap126F); }

    /**
     * This color constant "Dark Yellowish Brown" has RGBA8888 code {@code 392410FF}, L 0.16078432, A 0.50980395, B 0.52156866, alpha 1.0, hue 0.17620845, saturation 0.7061267, and chroma 0.0471994.
     * It can be represented as a packed float with the constant {@code -0x1.0b0452p126F}.
     * <pre>
     * <font style='background-color: #392410;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #392410; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #392410;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #392410'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #392410'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #392410'>&nbsp;@&nbsp;</font><font style='background-color: #392410; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #392410;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #392410; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_YELLOWISH_BROWN = -0x1.0b0452p126F;
    static { NAMED.put("Dark Yellowish Brown", -0x1.0b0452p126F); LIST.add(-0x1.0b0452p126F); }

    /**
     * This color constant "Light Grayish Yellowish Brown" has RGBA8888 code {@code B48977FF}, L 0.5803922, A 0.5176471, B 0.5176471, alpha 1.0, hue 0.125, saturation 0.2618914, and chroma 0.049718447.
     * It can be represented as a packed float with the constant {@code -0x1.090928p126F}.
     * <pre>
     * <font style='background-color: #B48977;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B48977; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B48977;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B48977'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B48977'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B48977'>&nbsp;@&nbsp;</font><font style='background-color: #B48977; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B48977;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B48977; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAYISH_YELLOWISH_BROWN = -0x1.090928p126F;
    static { NAMED.put("Light Grayish Yellowish Brown", -0x1.090928p126F); LIST.add(-0x1.090928p126F); }

    /**
     * This color constant "Grayish Yellowish Brown" has RGBA8888 code {@code 655046FF}, L 0.33333334, A 0.50980395, B 0.50980395, alpha 1.0, hue 0.125, saturation 0.22329687, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.0504aap126F}.
     * <pre>
     * <font style='background-color: #655046;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #655046; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #655046;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #655046'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #655046'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #655046'>&nbsp;@&nbsp;</font><font style='background-color: #655046; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #655046;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #655046; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_YELLOWISH_BROWN = -0x1.0504aap126F;
    static { NAMED.put("Grayish Yellowish Brown", -0x1.0504aap126F); LIST.add(-0x1.0504aap126F); }

    /**
     * This color constant "Dark Grayish Yellowish Brown" has RGBA8888 code {@code 382B22FF}, L 0.18039216, A 0.5058824, B 0.50980395, alpha 1.0, hue 0.15641639, saturation 0.3277774, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.05025cp126F}.
     * <pre>
     * <font style='background-color: #382B22;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #382B22; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #382B22;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #382B22'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #382B22'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #382B22'>&nbsp;@&nbsp;</font><font style='background-color: #382B22; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #382B22;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #382B22; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_YELLOWISH_BROWN = -0x1.05025cp126F;
    static { NAMED.put("Dark Grayish Yellowish Brown", -0x1.05025cp126F); LIST.add(-0x1.05025cp126F); }

    /**
     * This color constant "Vivid Yellow" has RGBA8888 code {@code FFD550FF}, L 0.84705883, A 0.49411765, B 0.57254905, alpha 1.0, hue 0.25836906, saturation 0.79276246, and chroma 0.14500555.
     * It can be represented as a packed float with the constant {@code -0x1.24fdbp126F}.
     * <pre>
     * <font style='background-color: #FFD550;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD550; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD550;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFD550'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFD550'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFD550'>&nbsp;@&nbsp;</font><font style='background-color: #FFD550; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD550;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD550; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_YELLOW = -0x1.24fdbp126F;
    static { NAMED.put("Vivid Yellow", -0x1.24fdbp126F); LIST.add(-0x1.24fdbp126F); }

    /**
     * This color constant "Brilliant Yellow" has RGBA8888 code {@code FDE9A0FF}, L 0.9137255, A 0.49411765, B 0.5411765, alpha 1.0, hue 0.2644291, saturation 0.4331514, and chroma 0.082864076.
     * It can be represented as a packed float with the constant {@code -0x1.14fdd2p126F}.
     * <pre>
     * <font style='background-color: #FDE9A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDE9A0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDE9A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FDE9A0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FDE9A0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FDE9A0'>&nbsp;@&nbsp;</font><font style='background-color: #FDE9A0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDE9A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDE9A0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_YELLOW = -0x1.14fdd2p126F;
    static { NAMED.put("Brilliant Yellow", -0x1.14fdd2p126F); LIST.add(-0x1.14fdd2p126F); }

    /**
     * This color constant "Strong Yellow" has RGBA8888 code {@code E0AF1AFF}, L 0.70980394, A 0.49803922, B 0.5764706, alpha 1.0, hue 0.25, saturation 0.9302326, and chroma 0.15239382.
     * It can be represented as a packed float with the constant {@code -0x1.26ff6ap126F}.
     * <pre>
     * <font style='background-color: #E0AF1A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E0AF1A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E0AF1A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E0AF1A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E0AF1A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E0AF1A'>&nbsp;@&nbsp;</font><font style='background-color: #E0AF1A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E0AF1A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E0AF1A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_YELLOW = -0x1.26ff6ap126F;
    static { NAMED.put("Strong Yellow", -0x1.26ff6ap126F); LIST.add(-0x1.26ff6ap126F); }

    /**
     * This color constant "Deep Yellow" has RGBA8888 code {@code A87C00FF}, L 0.5137255, A 0.5019608, B 0.5647059, alpha 1.0, hue 0.24064846, saturation 0.9731078, and chroma 0.12896542.
     * It can be represented as a packed float with the constant {@code -0x1.210106p126F}.
     * <pre>
     * <font style='background-color: #A87C00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A87C00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A87C00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A87C00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A87C00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A87C00'>&nbsp;@&nbsp;</font><font style='background-color: #A87C00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A87C00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A87C00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW = -0x1.210106p126F;
    static { NAMED.put("Deep Yellow", -0x1.210106p126F); LIST.add(-0x1.210106p126F); }

    /**
     * This color constant "Light Yellow" has RGBA8888 code {@code FEEFC9FF}, L 0.9411765, A 0.49803922, B 0.52156866, alpha 1.0, hue 0.25, saturation 0.23076923, and chroma 0.04314594.
     * It can be represented as a packed float with the constant {@code -0x1.0affep126F}.
     * <pre>
     * <font style='background-color: #FEEFC9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FEEFC9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FEEFC9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FEEFC9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FEEFC9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FEEFC9'>&nbsp;@&nbsp;</font><font style='background-color: #FEEFC9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FEEFC9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FEEFC9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_YELLOW = -0x1.0affep126F;
    static { NAMED.put("Light Yellow", -0x1.0affep126F); LIST.add(-0x1.0affep126F); }

    /**
     * This color constant "Moderate Yellow" has RGBA8888 code {@code DAAF53FF}, L 0.70980394, A 0.5019608, B 0.5568628, alpha 1.0, hue 0.23940508, saturation 0.6992231, and chroma 0.11334858.
     * It can be represented as a packed float with the constant {@code -0x1.1d016ap126F}.
     * <pre>
     * <font style='background-color: #DAAF53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAAF53; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAAF53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DAAF53'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DAAF53'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DAAF53'>&nbsp;@&nbsp;</font><font style='background-color: #DAAF53; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAAF53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAAF53; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_YELLOW = -0x1.1d016ap126F;
    static { NAMED.put("Moderate Yellow", -0x1.1d016ap126F); LIST.add(-0x1.1d016ap126F); }

    /**
     * This color constant "Dark Yellow" has RGBA8888 code {@code 9F7D34FF}, L 0.50980395, A 0.5019608, B 0.54901963, alpha 1.0, hue 0.23778114, saturation 0.7450517, and chroma 0.09773435.
     * It can be represented as a packed float with the constant {@code -0x1.190104p126F}.
     * <pre>
     * <font style='background-color: #9F7D34;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9F7D34; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9F7D34;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9F7D34'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9F7D34'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9F7D34'>&nbsp;@&nbsp;</font><font style='background-color: #9F7D34; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9F7D34;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9F7D34; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_YELLOW = -0x1.190104p126F;
    static { NAMED.put("Dark Yellow", -0x1.190104p126F); LIST.add(-0x1.190104p126F); }

    /**
     * This color constant "Pale Yellow" has RGBA8888 code {@code F9F2DFFF}, L 0.9529412, A 0.49803922, B 0.50980395, alpha 1.0, hue 0.25, saturation 0.115384616, and chroma 0.019918045.
     * It can be represented as a packed float with the constant {@code -0x1.04ffe6p126F}.
     * <pre>
     * <font style='background-color: #F9F2DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F9F2DF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F9F2DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F9F2DF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F9F2DF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F9F2DF'>&nbsp;@&nbsp;</font><font style='background-color: #F9F2DF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F9F2DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F9F2DF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOW = -0x1.04ffe6p126F;
    static { NAMED.put("Pale Yellow", -0x1.04ffe6p126F); LIST.add(-0x1.04ffe6p126F); }

    /**
     * This color constant "Grayish Yellow" has RGBA8888 code {@code CDB486FF}, L 0.7176471, A 0.5019608, B 0.5294118, alpha 1.0, hue 0.23020846, saturation 0.36646625, and chroma 0.058723815.
     * It can be represented as a packed float with the constant {@code -0x1.0f016ep126F}.
     * <pre>
     * <font style='background-color: #CDB486;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CDB486; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CDB486;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CDB486'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CDB486'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CDB486'>&nbsp;@&nbsp;</font><font style='background-color: #CDB486; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CDB486;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CDB486; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_YELLOW = -0x1.0f016ep126F;
    static { NAMED.put("Grayish Yellow", -0x1.0f016ep126F); LIST.add(-0x1.0f016ep126F); }

    /**
     * This color constant "Dark Grayish Yellow" has RGBA8888 code {@code 977E50FF}, L 0.5058824, A 0.5019608, B 0.53333336, alpha 1.0, hue 0.23238842, saturation 0.51745063, and chroma 0.06652104.
     * It can be represented as a packed float with the constant {@code -0x1.110102p126F}.
     * <pre>
     * <font style='background-color: #977E50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #977E50; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #977E50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #977E50'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #977E50'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #977E50'>&nbsp;@&nbsp;</font><font style='background-color: #977E50; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #977E50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #977E50; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_YELLOW = -0x1.110102p126F;
    static { NAMED.put("Dark Grayish Yellow", -0x1.110102p126F); LIST.add(-0x1.110102p126F); }

    /**
     * This color constant "Yellowish White" has RGBA8888 code {@code FFF6F1FF}, L 0.972549, A 0.5019608, B 0.5019608, alpha 1.0, hue 0.125, saturation 0.03771236, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.0101fp126F}.
     * <pre>
     * <font style='background-color: #FFF6F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFF6F1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFF6F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFF6F1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFF6F1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFF6F1'>&nbsp;@&nbsp;</font><font style='background-color: #FFF6F1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFF6F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFF6F1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float YELLOWISH_WHITE = -0x1.0101fp126F;
    static { NAMED.put("Yellowish White", -0x1.0101fp126F); LIST.add(-0x1.0101fp126F); }

    /**
     * This color constant "Yellowish Gray" has RGBA8888 code {@code D2C4B4FF}, L 0.78039217, A 0.5019608, B 0.50980395, alpha 1.0, hue 0.19879162, saturation 0.12907256, and chroma 0.019918045.
     * It can be represented as a packed float with the constant {@code -0x1.05018ep126F}.
     * <pre>
     * <font style='background-color: #D2C4B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2C4B4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2C4B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D2C4B4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D2C4B4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D2C4B4'>&nbsp;@&nbsp;</font><font style='background-color: #D2C4B4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2C4B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2C4B4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float YELLOWISH_GRAY = -0x1.05018ep126F;
    static { NAMED.put("Yellowish Gray", -0x1.05018ep126F); LIST.add(-0x1.05018ep126F); }

    /**
     * This color constant "Light Olive Brown" has RGBA8888 code {@code 7B6027FF}, L 0.39215687, A 0.5019608, B 0.5411765, alpha 1.0, hue 0.23557091, saturation 0.7363574, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.1500c8p126F}.
     * <pre>
     * <font style='background-color: #7B6027;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B6027; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B6027;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7B6027'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7B6027'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7B6027'>&nbsp;@&nbsp;</font><font style='background-color: #7B6027; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B6027;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B6027; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_OLIVE_BROWN = -0x1.1500c8p126F;
    static { NAMED.put("Light Olive Brown", -0x1.1500c8p126F); LIST.add(-0x1.1500c8p126F); }

    /**
     * This color constant "Moderate Olive Brown" has RGBA8888 code {@code 553E0DFF}, L 0.25882354, A 0.5019608, B 0.5372549, alpha 1.0, hue 0.2341372, saturation 0.8739022, and chroma 0.07432148.
     * It can be represented as a packed float with the constant {@code -0x1.130084p126F}.
     * <pre>
     * <font style='background-color: #553E0D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #553E0D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #553E0D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #553E0D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #553E0D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #553E0D'>&nbsp;@&nbsp;</font><font style='background-color: #553E0D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #553E0D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #553E0D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_OLIVE_BROWN = -0x1.130084p126F;
    static { NAMED.put("Moderate Olive Brown", -0x1.130084p126F); LIST.add(-0x1.130084p126F); }

    /**
     * This color constant "Dark Olive Brown" has RGBA8888 code {@code 2E1E09FF}, L 0.12941177, A 0.5058824, B 0.52156866, alpha 1.0, hue 0.19879162, saturation 0.7905694, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.0b0242p126F}.
     * <pre>
     * <font style='background-color: #2E1E09;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2E1E09; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2E1E09;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2E1E09'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2E1E09'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2E1E09'>&nbsp;@&nbsp;</font><font style='background-color: #2E1E09; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2E1E09;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2E1E09; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_OLIVE_BROWN = -0x1.0b0242p126F;
    static { NAMED.put("Dark Olive Brown", -0x1.0b0242p126F); LIST.add(-0x1.0b0242p126F); }

    /**
     * This color constant "Vivid Greenish Yellow" has RGBA8888 code {@code FFF770FF}, L 0.9411765, A 0.47843137, B 0.5686275, alpha 1.0, hue 0.2931228, saturation 0.69190896, and chroma 0.143312.
     * It can be represented as a packed float with the constant {@code -0x1.22f5ep126F}.
     * <pre>
     * <font style='background-color: #FFF770;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFF770; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFF770;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFF770'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFF770'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFF770'>&nbsp;@&nbsp;</font><font style='background-color: #FFF770; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFF770;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFF770; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_GREENISH_YELLOW = -0x1.22f5ep126F;
    static { NAMED.put("Vivid Greenish Yellow", -0x1.22f5ep126F); LIST.add(-0x1.22f5ep126F); }

    /**
     * This color constant "Brilliant Greenish Yellow" has RGBA8888 code {@code FDF588FF}, L 0.9411765, A 0.48235294, B 0.5568628, alpha 1.0, hue 0.2914764, saturation 0.5749694, and chroma 0.11861114.
     * It can be represented as a packed float with the constant {@code -0x1.1cf7ep126F}.
     * <pre>
     * <font style='background-color: #FDF588;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDF588; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDF588;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FDF588'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FDF588'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FDF588'>&nbsp;@&nbsp;</font><font style='background-color: #FDF588; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDF588;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDF588; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_GREENISH_YELLOW = -0x1.1cf7ep126F;
    static { NAMED.put("Brilliant Greenish Yellow", -0x1.1cf7ep126F); LIST.add(-0x1.1cf7ep126F); }

    /**
     * This color constant "Strong Greenish Yellow" has RGBA8888 code {@code C4BC1CFF}, L 0.7137255, A 0.4745098, B 0.5764706, alpha 1.0, hue 0.29638705, saturation 0.9280273, and chroma 0.1605844.
     * It can be represented as a packed float with the constant {@code -0x1.26f36cp126F}.
     * <pre>
     * <font style='background-color: #C4BC1C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4BC1C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4BC1C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C4BC1C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C4BC1C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C4BC1C'>&nbsp;@&nbsp;</font><font style='background-color: #C4BC1C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4BC1C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4BC1C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_GREENISH_YELLOW = -0x1.26f36cp126F;
    static { NAMED.put("Strong Greenish Yellow", -0x1.26f36cp126F); LIST.add(-0x1.26f36cp126F); }

    /**
     * This color constant "Deep Greenish Yellow" has RGBA8888 code {@code 938600FF}, L 0.5137255, A 0.48235294, B 0.5647059, alpha 1.0, hue 0.28677934, saturation 0.97023606, and chroma 0.1336143.
     * It can be represented as a packed float with the constant {@code -0x1.20f706p126F}.
     * <pre>
     * <font style='background-color: #938600;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #938600; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #938600;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #938600'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #938600'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #938600'>&nbsp;@&nbsp;</font><font style='background-color: #938600; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #938600;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #938600; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREENISH_YELLOW = -0x1.20f706p126F;
    static { NAMED.put("Deep Greenish Yellow", -0x1.20f706p126F); LIST.add(-0x1.20f706p126F); }

    /**
     * This color constant "Light Greenish Yellow" has RGBA8888 code {@code FEF5C5FF}, L 0.95686275, A 0.49411765, B 0.5254902, alpha 1.0, hue 0.27258337, saturation 0.26683274, and chroma 0.052115876.
     * It can be represented as a packed float with the constant {@code -0x1.0cfde8p126F}.
     * <pre>
     * <font style='background-color: #FEF5C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FEF5C5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FEF5C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FEF5C5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FEF5C5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FEF5C5'>&nbsp;@&nbsp;</font><font style='background-color: #FEF5C5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FEF5C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FEF5C5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GREENISH_YELLOW = -0x1.0cfde8p126F;
    static { NAMED.put("Light Greenish Yellow", -0x1.0cfde8p126F); LIST.add(-0x1.0cfde8p126F); }

    /**
     * This color constant "Moderate Greenish Yellow" has RGBA8888 code {@code C0B852FF}, L 0.7019608, A 0.48235294, B 0.5568628, alpha 1.0, hue 0.2914764, saturation 0.7056443, and chroma 0.11861114.
     * It can be represented as a packed float with the constant {@code -0x1.1cf766p126F}.
     * <pre>
     * <font style='background-color: #C0B852;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0B852; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0B852;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C0B852'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C0B852'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C0B852'>&nbsp;@&nbsp;</font><font style='background-color: #C0B852; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0B852;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0B852; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_GREENISH_YELLOW = -0x1.1cf766p126F;
    static { NAMED.put("Moderate Greenish Yellow", -0x1.1cf766p126F); LIST.add(-0x1.1cf766p126F); }

    /**
     * This color constant "Dark Greenish Yellow" has RGBA8888 code {@code 8D8129FF}, L 0.49803922, A 0.4862745, B 0.5529412, alpha 1.0, hue 0.28359655, saturation 0.81816125, and chroma 0.10895567.
     * It can be represented as a packed float with the constant {@code -0x1.1af8fep126F}.
     * <pre>
     * <font style='background-color: #8D8129;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8D8129; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8D8129;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8D8129'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8D8129'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8D8129'>&nbsp;@&nbsp;</font><font style='background-color: #8D8129; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8D8129;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8D8129; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GREENISH_YELLOW = -0x1.1af8fep126F;
    static { NAMED.put("Dark Greenish Yellow", -0x1.1af8fep126F); LIST.add(-0x1.1af8fep126F); }

    /**
     * This color constant "Pale Greenish Yellow" has RGBA8888 code {@code FDF3D9FF}, L 0.95686275, A 0.49803922, B 0.5137255, alpha 1.0, hue 0.25, saturation 0.15384616, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.06ffe8p126F}.
     * <pre>
     * <font style='background-color: #FDF3D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDF3D9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDF3D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FDF3D9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FDF3D9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FDF3D9'>&nbsp;@&nbsp;</font><font style='background-color: #FDF3D9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDF3D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDF3D9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_GREENISH_YELLOW = -0x1.06ffe8p126F;
    static { NAMED.put("Pale Greenish Yellow", -0x1.06ffe8p126F); LIST.add(-0x1.06ffe8p126F); }

    /**
     * This color constant "Grayish Greenish Yellow" has RGBA8888 code {@code C6B880FF}, L 0.7176471, A 0.49411765, B 0.53333336, alpha 1.0, hue 0.2676116, saturation 0.41160843, and chroma 0.06743233.
     * It can be represented as a packed float with the constant {@code -0x1.10fd6ep126F}.
     * <pre>
     * <font style='background-color: #C6B880;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C6B880; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C6B880;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C6B880'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C6B880'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C6B880'>&nbsp;@&nbsp;</font><font style='background-color: #C6B880; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C6B880;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C6B880; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_GREENISH_YELLOW = -0x1.10fd6ep126F;
    static { NAMED.put("Grayish Greenish Yellow", -0x1.10fd6ep126F); LIST.add(-0x1.10fd6ep126F); }

    /**
     * This color constant "Light Olive" has RGBA8888 code {@code 726621FF}, L 0.39607844, A 0.49019608, B 0.54509807, alpha 1.0, hue 0.27628398, saturation 0.81103504, and chroma 0.091942206.
     * It can be represented as a packed float with the constant {@code -0x1.16facap126F}.
     * <pre>
     * <font style='background-color: #726621;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #726621; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #726621;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #726621'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #726621'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #726621'>&nbsp;@&nbsp;</font><font style='background-color: #726621; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #726621;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #726621; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_OLIVE = -0x1.16facap126F;
    static { NAMED.put("Light Olive", -0x1.16facap126F); LIST.add(-0x1.16facap126F); }

    /**
     * This color constant "Moderate Olive" has RGBA8888 code {@code 4B430DFF}, L 0.25882354, A 0.49019608, B 0.5372549, alpha 1.0, hue 0.2814164, saturation 0.886786, and chroma 0.07674564.
     * It can be represented as a packed float with the constant {@code -0x1.12fa84p126F}.
     * <pre>
     * <font style='background-color: #4B430D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B430D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B430D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4B430D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4B430D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4B430D'>&nbsp;@&nbsp;</font><font style='background-color: #4B430D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B430D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B430D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_OLIVE = -0x1.12fa84p126F;
    static { NAMED.put("Moderate Olive", -0x1.12fa84p126F); LIST.add(-0x1.12fa84p126F); }

    /**
     * This color constant "Dark Olive" has RGBA8888 code {@code 262000FF}, L 0.1254902, A 0.49411765, B 0.5254902, alpha 1.0, hue 0.27258337, saturation 0.94280905, and chroma 0.052115876.
     * It can be represented as a packed float with the constant {@code -0x1.0cfc4p126F}.
     * <pre>
     * <font style='background-color: #262000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #262000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #262000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #262000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #262000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #262000'>&nbsp;@&nbsp;</font><font style='background-color: #262000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #262000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #262000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_OLIVE = -0x1.0cfc4p126F;
    static { NAMED.put("Dark Olive", -0x1.0cfc4p126F); LIST.add(-0x1.0cfc4p126F); }

    /**
     * This color constant "Light Grayish Olive" has RGBA8888 code {@code 797057FF}, L 0.4392157, A 0.49803922, B 0.5176471, alpha 1.0, hue 0.25, saturation 0.3125, and chroma 0.0353726.
     * It can be represented as a packed float with the constant {@code -0x1.08feep126F}.
     * <pre>
     * <font style='background-color: #797057;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #797057; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #797057;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #797057'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #797057'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #797057'>&nbsp;@&nbsp;</font><font style='background-color: #797057; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #797057;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #797057; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAYISH_OLIVE = -0x1.08feep126F;
    static { NAMED.put("Light Grayish Olive", -0x1.08feep126F); LIST.add(-0x1.08feep126F); }

    /**
     * This color constant "Grayish Olive" has RGBA8888 code {@code 473F2AFF}, L 0.24705882, A 0.49803922, B 0.5176471, alpha 1.0, hue 0.25, saturation 0.45454547, and chroma 0.0353726.
     * It can be represented as a packed float with the constant {@code -0x1.08fe7ep126F}.
     * <pre>
     * <font style='background-color: #473F2A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #473F2A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #473F2A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #473F2A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #473F2A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #473F2A'>&nbsp;@&nbsp;</font><font style='background-color: #473F2A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #473F2A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #473F2A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_OLIVE = -0x1.08fe7ep126F;
    static { NAMED.put("Grayish Olive", -0x1.08fe7ep126F); LIST.add(-0x1.08fe7ep126F); }

    /**
     * This color constant "Dark Grayish Olive" has RGBA8888 code {@code 242213FF}, L 0.12941177, A 0.49411765, B 0.5137255, alpha 1.0, hue 0.28898975, saturation 0.5497474, and chroma 0.029749114.
     * It can be represented as a packed float with the constant {@code -0x1.06fc42p126F}.
     * <pre>
     * <font style='background-color: #242213;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #242213; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #242213;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #242213'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #242213'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #242213'>&nbsp;@&nbsp;</font><font style='background-color: #242213; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #242213;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #242213; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_OLIVE = -0x1.06fc42p126F;
    static { NAMED.put("Dark Grayish Olive", -0x1.06fc42p126F); LIST.add(-0x1.06fc42p126F); }

    /**
     * This color constant "Light Olive Gray" has RGBA8888 code {@code 7D7164FF}, L 0.4509804, A 0.5019608, B 0.50980395, alpha 1.0, hue 0.19879162, saturation 0.18601634, and chroma 0.019918045.
     * It can be represented as a packed float with the constant {@code -0x1.0500e6p126F}.
     * <pre>
     * <font style='background-color: #7D7164;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7D7164; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7D7164;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7D7164'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7D7164'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7D7164'>&nbsp;@&nbsp;</font><font style='background-color: #7D7164; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7D7164;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7D7164; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_OLIVE_GRAY = -0x1.0500e6p126F;
    static { NAMED.put("Light Olive Gray", -0x1.0500e6p126F); LIST.add(-0x1.0500e6p126F); }

    /**
     * This color constant "Olive Gray" has RGBA8888 code {@code 443C35FF}, L 0.23921569, A 0.5019608, B 0.5058824, alpha 1.0, hue 0.17620845, saturation 0.186339, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.03007ap126F}.
     * <pre>
     * <font style='background-color: #443C35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #443C35; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #443C35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #443C35'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #443C35'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #443C35'>&nbsp;@&nbsp;</font><font style='background-color: #443C35; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #443C35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #443C35; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float OLIVE_GRAY = -0x1.03007ap126F;
    static { NAMED.put("Olive Gray", -0x1.03007ap126F); LIST.add(-0x1.03007ap126F); }

    /**
     * This color constant "Vivid Yellow Green" has RGBA8888 code {@code B9F720FF}, L 0.8666667, A 0.43529412, B 0.5882353, alpha 1.0, hue 0.34673485, saturation 0.9339284, and chroma 0.21798135.
     * It can be represented as a packed float with the constant {@code -0x1.2cdfbap126F}.
     * <pre>
     * <font style='background-color: #B9F720;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B9F720; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B9F720;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B9F720'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B9F720'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B9F720'>&nbsp;@&nbsp;</font><font style='background-color: #B9F720; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B9F720;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B9F720; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_YELLOW_GREEN = -0x1.2cdfbap126F;
    static { NAMED.put("Vivid Yellow Green", -0x1.2cdfbap126F); LIST.add(-0x1.2cdfbap126F); }

    /**
     * This color constant "Brilliant Yellow Green" has RGBA8888 code {@code D8FB60FF}, L 0.9098039, A 0.45490196, B 0.57254905, alpha 1.0, hue 0.3335237, saturation 0.74422026, and chroma 0.17017984.
     * It can be represented as a packed float with the constant {@code -0x1.24e9dp126F}.
     * <pre>
     * <font style='background-color: #D8FB60;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D8FB60; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D8FB60;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D8FB60'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D8FB60'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D8FB60'>&nbsp;@&nbsp;</font><font style='background-color: #D8FB60; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D8FB60;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D8FB60; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_YELLOW_GREEN = -0x1.24e9dp126F;
    static { NAMED.put("Brilliant Yellow Green", -0x1.24e9dp126F); LIST.add(-0x1.24e9dp126F); }

    /**
     * This color constant "Strong Yellow Green" has RGBA8888 code {@code 718D12FF}, L 0.49803922, A 0.45882353, B 0.56078434, alpha 1.0, hue 0.33890384, saturation 0.9203884, and chroma 0.14626285.
     * It can be represented as a packed float with the constant {@code -0x1.1eeafep126F}.
     * <pre>
     * <font style='background-color: #718D12;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #718D12; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #718D12;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #718D12'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #718D12'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #718D12'>&nbsp;@&nbsp;</font><font style='background-color: #718D12; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #718D12;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #718D12; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_YELLOW_GREEN = -0x1.1eeafep126F;
    static { NAMED.put("Strong Yellow Green", -0x1.1eeafep126F); LIST.add(-0x1.1eeafep126F); }

    /**
     * This color constant "Deep Yellow Green" has RGBA8888 code {@code 305309FF}, L 0.2784314, A 0.45882353, B 0.5411765, alpha 1.0, hue 0.36742708, saturation 0.90097386, and chroma 0.116009705.
     * It can be represented as a packed float with the constant {@code -0x1.14ea8ep126F}.
     * <pre>
     * <font style='background-color: #305309;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #305309; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #305309;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #305309'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #305309'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #305309'>&nbsp;@&nbsp;</font><font style='background-color: #305309; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #305309;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #305309; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_GREEN = -0x1.14ea8ep126F;
    static { NAMED.put("Deep Yellow Green", -0x1.14ea8ep126F); LIST.add(-0x1.14ea8ep126F); }

    /**
     * This color constant "Light Yellow Green" has RGBA8888 code {@code E9F4B9FF}, L 0.92941177, A 0.48235294, B 0.5294118, alpha 1.0, hue 0.32379153, saturation 0.30842316, and chroma 0.068331465.
     * It can be represented as a packed float with the constant {@code -0x1.0ef7dap126F}.
     * <pre>
     * <font style='background-color: #E9F4B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9F4B9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9F4B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E9F4B9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E9F4B9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E9F4B9'>&nbsp;@&nbsp;</font><font style='background-color: #E9F4B9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9F4B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9F4B9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_YELLOW_GREEN = -0x1.0ef7dap126F;
    static { NAMED.put("Light Yellow Green", -0x1.0ef7dap126F); LIST.add(-0x1.0ef7dap126F); }

    /**
     * This color constant "Moderate Yellow Green" has RGBA8888 code {@code 75894FFF}, L 0.5019608, A 0.4745098, B 0.53333336, alpha 1.0, hue 0.3435836, saturation 0.5150788, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.10f3p126F}.
     * <pre>
     * <font style='background-color: #75894F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #75894F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #75894F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #75894F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #75894F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #75894F'>&nbsp;@&nbsp;</font><font style='background-color: #75894F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #75894F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #75894F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_YELLOW_GREEN = -0x1.10f3p126F;
    static { NAMED.put("Moderate Yellow Green", -0x1.10f3p126F); LIST.add(-0x1.10f3p126F); }

    /**
     * This color constant "Pale Yellow Green" has RGBA8888 code {@code EFF3CFFF}, L 0.9372549, A 0.49019608, B 0.5176471, alpha 1.0, hue 0.3105594, saturation 0.1923273, and chroma 0.040217306.
     * It can be represented as a packed float with the constant {@code -0x1.08fbdep126F}.
     * <pre>
     * <font style='background-color: #EFF3CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFF3CF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFF3CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EFF3CF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EFF3CF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EFF3CF'>&nbsp;@&nbsp;</font><font style='background-color: #EFF3CF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFF3CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFF3CF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOW_GREEN = -0x1.08fbdep126F;
    static { NAMED.put("Pale Yellow Green", -0x1.08fbdep126F); LIST.add(-0x1.08fbdep126F); }

    /**
     * This color constant "Grayish Yellow Green" has RGBA8888 code {@code 898978FF}, L 0.53333336, A 0.49411765, B 0.50980395, alpha 1.0, hue 0.30120838, saturation 0.16643567, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.04fd1p126F}.
     * <pre>
     * <font style='background-color: #898978;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #898978; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #898978;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #898978'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #898978'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #898978'>&nbsp;@&nbsp;</font><font style='background-color: #898978; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #898978;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #898978; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_YELLOW_GREEN = -0x1.04fd1p126F;
    static { NAMED.put("Grayish Yellow Green", -0x1.04fd1p126F); LIST.add(-0x1.04fd1p126F); }

    /**
     * This color constant "Strong Olive Green" has RGBA8888 code {@code 193F06FF}, L 0.20392157, A 0.45882353, B 0.53333336, alpha 1.0, hue 0.38336867, saturation 0.8969082, and chroma 0.105541065.
     * It can be represented as a packed float with the constant {@code -0x1.10ea68p126F}.
     * <pre>
     * <font style='background-color: #193F06;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #193F06; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #193F06;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #193F06'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #193F06'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #193F06'>&nbsp;@&nbsp;</font><font style='background-color: #193F06; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #193F06;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #193F06; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_OLIVE_GREEN = -0x1.10ea68p126F;
    static { NAMED.put("Strong Olive Green", -0x1.10ea68p126F); LIST.add(-0x1.10ea68p126F); }

    /**
     * This color constant "Moderate Olive Green" has RGBA8888 code {@code 354311FF}, L 0.23529412, A 0.4745098, B 0.53333336, alpha 1.0, hue 0.3435836, saturation 0.8320503, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.10f278p126F}.
     * <pre>
     * <font style='background-color: #354311;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #354311; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #354311;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #354311'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #354311'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #354311'>&nbsp;@&nbsp;</font><font style='background-color: #354311; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #354311;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #354311; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_OLIVE_GREEN = -0x1.10f278p126F;
    static { NAMED.put("Moderate Olive Green", -0x1.10f278p126F); LIST.add(-0x1.10f278p126F); }

    /**
     * This color constant "Dark Olive Green" has RGBA8888 code {@code 142508FF}, L 0.12156863, A 0.4745098, B 0.52156866, alpha 1.0, hue 0.375, saturation 0.8485281, and chroma 0.06652104.
     * It can be represented as a packed float with the constant {@code -0x1.0af23ep126F}.
     * <pre>
     * <font style='background-color: #142508;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #142508; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #142508;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #142508'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #142508'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #142508'>&nbsp;@&nbsp;</font><font style='background-color: #142508; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #142508;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #142508; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_OLIVE_GREEN = -0x1.0af23ep126F;
    static { NAMED.put("Dark Olive Green", -0x1.0af23ep126F); LIST.add(-0x1.0af23ep126F); }

    /**
     * This color constant "Grayish Olive Green" has RGBA8888 code {@code 3D4233FF}, L 0.24705882, A 0.49019608, B 0.50980395, alpha 1.0, hue 0.3435836, saturation 0.2773501, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.04fa7ep126F}.
     * <pre>
     * <font style='background-color: #3D4233;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3D4233; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3D4233;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3D4233'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3D4233'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3D4233'>&nbsp;@&nbsp;</font><font style='background-color: #3D4233; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3D4233;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3D4233; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_OLIVE_GREEN = -0x1.04fa7ep126F;
    static { NAMED.put("Grayish Olive Green", -0x1.04fa7ep126F); LIST.add(-0x1.04fa7ep126F); }

    /**
     * This color constant "Dark Grayish Olive Green" has RGBA8888 code {@code 202518FF}, L 0.13333334, A 0.49019608, B 0.50980395, alpha 1.0, hue 0.3435836, saturation 0.4006168, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.04fa44p126F}.
     * <pre>
     * <font style='background-color: #202518;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #202518; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #202518;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #202518'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #202518'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #202518'>&nbsp;@&nbsp;</font><font style='background-color: #202518; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #202518;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #202518; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_OLIVE_GREEN = -0x1.04fa44p126F;
    static { NAMED.put("Dark Grayish Olive Green", -0x1.04fa44p126F); LIST.add(-0x1.04fa44p126F); }

    /**
     * This color constant "Vivid Yellowish Green" has RGBA8888 code {@code 14DD2DFF}, L 0.7019608, A 0.39607844, B 0.57254905, alpha 1.0, hue 0.39956036, saturation 0.92007095, and chroma 0.25249004.
     * It can be represented as a packed float with the constant {@code -0x1.24cb66p126F}.
     * <pre>
     * <font style='background-color: #14DD2D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #14DD2D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #14DD2D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #14DD2D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #14DD2D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #14DD2D'>&nbsp;@&nbsp;</font><font style='background-color: #14DD2D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #14DD2D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #14DD2D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_YELLOWISH_GREEN = -0x1.24cb66p126F;
    static { NAMED.put("Vivid Yellowish Green", -0x1.24cb66p126F); LIST.add(-0x1.24cb66p126F); }

    /**
     * This color constant "Brilliant Yellowish Green" has RGBA8888 code {@code 96EE96FF}, L 0.83137256, A 0.44313726, B 0.5372549, alpha 1.0, hue 0.40128404, saturation 0.44114488, and chroma 0.13542919.
     * It can be represented as a packed float with the constant {@code -0x1.12e3a8p126F}.
     * <pre>
     * <font style='background-color: #96EE96;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #96EE96; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #96EE96;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #96EE96'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #96EE96'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #96EE96'>&nbsp;@&nbsp;</font><font style='background-color: #96EE96; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #96EE96;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #96EE96; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_YELLOWISH_GREEN = -0x1.12e3a8p126F;
    static { NAMED.put("Brilliant Yellowish Green", -0x1.12e3a8p126F); LIST.add(-0x1.12e3a8p126F); }

    /**
     * This color constant "Strong Yellowish Green" has RGBA8888 code {@code 117E3AFF}, L 0.40392157, A 0.43529412, B 0.53333336, alpha 1.0, hue 0.41845086, saturation 0.8954907, and chroma 0.14500555.
     * It can be represented as a packed float with the constant {@code -0x1.10decep126F}.
     * <pre>
     * <font style='background-color: #117E3A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #117E3A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #117E3A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #117E3A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #117E3A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #117E3A'>&nbsp;@&nbsp;</font><font style='background-color: #117E3A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #117E3A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #117E3A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_YELLOWISH_GREEN = -0x1.10decep126F;
    static { NAMED.put("Strong Yellowish Green", -0x1.10decep126F); LIST.add(-0x1.10decep126F); }

    /**
     * This color constant "Deep Yellowish Green" has RGBA8888 code {@code 0E4F0CFF}, L 0.2509804, A 0.44705883, B 0.5372549, alpha 1.0, hue 0.39564255, saturation 0.9111789, and chroma 0.12896542.
     * It can be represented as a packed float with the constant {@code -0x1.12e48p126F}.
     * <pre>
     * <font style='background-color: #0E4F0C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0E4F0C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0E4F0C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0E4F0C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0E4F0C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0E4F0C'>&nbsp;@&nbsp;</font><font style='background-color: #0E4F0C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0E4F0C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0E4F0C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOWISH_GREEN = -0x1.12e48p126F;
    static { NAMED.put("Deep Yellowish Green", -0x1.12e48p126F); LIST.add(-0x1.12e48p126F); }

    /**
     * This color constant "Very Deep Yellowish Green" has RGBA8888 code {@code 003000FF}, L 0.14901961, A 0.45882353, B 0.5294118, alpha 1.0, hue 0.39261138, saturation 0.98509604, and chroma 0.1008085.
     * It can be represented as a packed float with the constant {@code -0x1.0eea4cp126F}.
     * <pre>
     * <font style='background-color: #003000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #003000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #003000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #003000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #003000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #003000'>&nbsp;@&nbsp;</font><font style='background-color: #003000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #003000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #003000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DEEP_YELLOWISH_GREEN = -0x1.0eea4cp126F;
    static { NAMED.put("Very Deep Yellowish Green", -0x1.0eea4cp126F); LIST.add(-0x1.0eea4cp126F); }

    /**
     * This color constant "Very Light Yellowish Green" has RGBA8888 code {@code EEFAE9FF}, L 0.9647059, A 0.49019608, B 0.5058824, alpha 1.0, hue 0.375, saturation 0.07443229, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.02fbecp126F}.
     * <pre>
     * <font style='background-color: #EEFAE9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EEFAE9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EEFAE9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EEFAE9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EEFAE9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EEFAE9'>&nbsp;@&nbsp;</font><font style='background-color: #EEFAE9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EEFAE9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EEFAE9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_YELLOWISH_GREEN = -0x1.02fbecp126F;
    static { NAMED.put("Very Light Yellowish Green", -0x1.02fbecp126F); LIST.add(-0x1.02fbecp126F); }

    /**
     * This color constant "Light Yellowish Green" has RGBA8888 code {@code 9FD399FF}, L 0.7607843, A 0.4627451, B 0.5254902, alpha 1.0, hue 0.39479145, saturation 0.31237683, and chroma 0.08992863.
     * It can be represented as a packed float with the constant {@code -0x1.0ced84p126F}.
     * <pre>
     * <font style='background-color: #9FD399;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9FD399; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9FD399;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9FD399'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9FD399'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9FD399'>&nbsp;@&nbsp;</font><font style='background-color: #9FD399; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9FD399;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9FD399; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_YELLOWISH_GREEN = -0x1.0ced84p126F;
    static { NAMED.put("Light Yellowish Green", -0x1.0ced84p126F); LIST.add(-0x1.0ced84p126F); }

    /**
     * This color constant "Moderate Yellowish Green" has RGBA8888 code {@code 497A54FF}, L 0.42352942, A 0.4627451, B 0.5176471, alpha 1.0, hue 0.41929296, saturation 0.4902681, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.08ecd8p126F}.
     * <pre>
     * <font style='background-color: #497A54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #497A54; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #497A54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #497A54'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #497A54'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #497A54'>&nbsp;@&nbsp;</font><font style='background-color: #497A54; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #497A54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #497A54; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_YELLOWISH_GREEN = -0x1.08ecd8p126F;
    static { NAMED.put("Moderate Yellowish Green", -0x1.08ecd8p126F); LIST.add(-0x1.08ecd8p126F); }

    /**
     * This color constant "Dark Yellowish Green" has RGBA8888 code {@code 214527FF}, L 0.23137255, A 0.46666667, B 0.5176471, alpha 1.0, hue 0.41109616, saturation 0.60864395, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.08ee76p126F}.
     * <pre>
     * <font style='background-color: #214527;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #214527; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #214527;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #214527'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #214527'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #214527'>&nbsp;@&nbsp;</font><font style='background-color: #214527; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #214527;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #214527; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_YELLOWISH_GREEN = -0x1.08ee76p126F;
    static { NAMED.put("Dark Yellowish Green", -0x1.08ee76p126F); LIST.add(-0x1.08ee76p126F); }

    /**
     * This color constant "Very Dark Yellowish Green" has RGBA8888 code {@code 0C260EFF}, L 0.12156863, A 0.47058824, B 0.5176471, alpha 1.0, hue 0.40128404, saturation 0.7480283, and chroma 0.068331465.
     * It can be represented as a packed float with the constant {@code -0x1.08f03ep126F}.
     * <pre>
     * <font style='background-color: #0C260E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0C260E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0C260E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0C260E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0C260E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0C260E'>&nbsp;@&nbsp;</font><font style='background-color: #0C260E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0C260E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0C260E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_YELLOWISH_GREEN = -0x1.08f03ep126F;
    static { NAMED.put("Very Dark Yellowish Green", -0x1.08f03ep126F); LIST.add(-0x1.08f03ep126F); }

    /**
     * This color constant "Vivid Green" has RGBA8888 code {@code 66FDBEFF}, L 0.85882354, A 0.42745098, B 0.5176471, alpha 1.0, hue 0.4568772, saturation 0.7185208, and chroma 0.14874557.
     * It can be represented as a packed float with the constant {@code -0x1.08dbb6p126F}.
     * <pre>
     * <font style='background-color: #66FDBE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #66FDBE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #66FDBE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #66FDBE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #66FDBE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #66FDBE'>&nbsp;@&nbsp;</font><font style='background-color: #66FDBE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #66FDBE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #66FDBE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_GREEN = -0x1.08dbb6p126F;
    static { NAMED.put("Vivid Green", -0x1.08dbb6p126F); LIST.add(-0x1.08dbb6p126F); }

    /**
     * This color constant "Brilliant Green" has RGBA8888 code {@code 32D5A7FF}, L 0.70980394, A 0.42745098, B 0.50980395, alpha 1.0, hue 0.47371602, saturation 0.86896604, and chroma 0.14584495.
     * It can be represented as a packed float with the constant {@code -0x1.04db6ap126F}.
     * <pre>
     * <font style='background-color: #32D5A7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #32D5A7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #32D5A7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #32D5A7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #32D5A7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #32D5A7'>&nbsp;@&nbsp;</font><font style='background-color: #32D5A7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #32D5A7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #32D5A7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_GREEN = -0x1.04db6ap126F;
    static { NAMED.put("Brilliant Green", -0x1.04db6ap126F); LIST.add(-0x1.04db6ap126F); }

    /**
     * This color constant "Strong Green" has RGBA8888 code {@code 006F50FF}, L 0.3647059, A 0.44705883, B 0.50980395, alpha 1.0, hue 0.46390375, saturation 0.92011476, and chroma 0.107261956.
     * It can be represented as a packed float with the constant {@code -0x1.04e4bap126F}.
     * <pre>
     * <font style='background-color: #006F50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #006F50; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #006F50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #006F50'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #006F50'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #006F50'>&nbsp;@&nbsp;</font><font style='background-color: #006F50; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #006F50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #006F50; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_GREEN = -0x1.04e4bap126F;
    static { NAMED.put("Strong Green", -0x1.04e4bap126F); LIST.add(-0x1.04e4bap126F); }

    /**
     * This color constant "Very Light Green" has RGBA8888 code {@code D6F4E6FF}, L 0.9254902, A 0.48235294, B 0.5019608, alpha 1.0, hue 0.46101025, saturation 0.1555889, and chroma 0.0353726.
     * It can be represented as a packed float with the constant {@code -0x1.00f7d8p126F}.
     * <pre>
     * <font style='background-color: #D6F4E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D6F4E6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D6F4E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D6F4E6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D6F4E6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D6F4E6'>&nbsp;@&nbsp;</font><font style='background-color: #D6F4E6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D6F4E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D6F4E6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_GREEN = -0x1.00f7d8p126F;
    static { NAMED.put("Very Light Green", -0x1.00f7d8p126F); LIST.add(-0x1.00f7d8p126F); }

    /**
     * This color constant "Light Green" has RGBA8888 code {@code 5CA288FF}, L 0.5686275, A 0.45882353, B 0.5058824, alpha 1.0, hue 0.4685836, saturation 0.5367389, and chroma 0.082864076.
     * It can be represented as a packed float with the constant {@code -0x1.02eb22p126F}.
     * <pre>
     * <font style='background-color: #5CA288;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5CA288; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5CA288;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5CA288'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5CA288'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5CA288'>&nbsp;@&nbsp;</font><font style='background-color: #5CA288; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5CA288;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5CA288; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GREEN = -0x1.02eb22p126F;
    static { NAMED.put("Light Green", -0x1.02eb22p126F); LIST.add(-0x1.02eb22p126F); }

    /**
     * This color constant "Moderate Green" has RGBA8888 code {@code 245B47FF}, L 0.30588236, A 0.4627451, B 0.5058824, alpha 1.0, hue 0.4651977, saturation 0.70919573, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.02ec9cp126F}.
     * <pre>
     * <font style='background-color: #245B47;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #245B47; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #245B47;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #245B47'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #245B47'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #245B47'>&nbsp;@&nbsp;</font><font style='background-color: #245B47; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #245B47;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #245B47; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_GREEN = -0x1.02ec9cp126F;
    static { NAMED.put("Moderate Green", -0x1.02ec9cp126F); LIST.add(-0x1.02ec9cp126F); }

    /**
     * This color constant "Dark Green" has RGBA8888 code {@code 0A3929FF}, L 0.1882353, A 0.46666667, B 0.5058824, alpha 1.0, hue 0.46101025, saturation 0.8680222, and chroma 0.06743233.
     * It can be represented as a packed float with the constant {@code -0x1.02ee6p126F}.
     * <pre>
     * <font style='background-color: #0A3929;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0A3929; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0A3929;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0A3929'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0A3929'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0A3929'>&nbsp;@&nbsp;</font><font style='background-color: #0A3929; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0A3929;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0A3929; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GREEN = -0x1.02ee6p126F;
    static { NAMED.put("Dark Green", -0x1.02ee6p126F); LIST.add(-0x1.02ee6p126F); }

    /**
     * This color constant "Very Dark Green" has RGBA8888 code {@code 0A2119FF}, L 0.10980392, A 0.47843137, B 0.5019608, alpha 1.0, hue 0.4685836, saturation 0.72843134, and chroma 0.04314594.
     * It can be represented as a packed float with the constant {@code -0x1.00f438p126F}.
     * <pre>
     * <font style='background-color: #0A2119;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0A2119; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0A2119;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0A2119'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0A2119'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0A2119'>&nbsp;@&nbsp;</font><font style='background-color: #0A2119; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0A2119;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0A2119; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_GREEN = -0x1.00f438p126F;
    static { NAMED.put("Very Dark Green", -0x1.00f438p126F); LIST.add(-0x1.00f438p126F); }

    /**
     * This color constant "Very Pale Green" has RGBA8888 code {@code E9F1EFFF}, L 0.9372549, A 0.49411765, B 0.49803922, alpha 1.0, hue 0.5, saturation 0.04347826, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.fefddep125F}.
     * <pre>
     * <font style='background-color: #E9F1EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9F1EF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9F1EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E9F1EF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E9F1EF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E9F1EF'>&nbsp;@&nbsp;</font><font style='background-color: #E9F1EF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9F1EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9F1EF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_PALE_GREEN = -0x1.fefddep125F;
    static { NAMED.put("Very Pale Green", -0x1.fefddep125F); LIST.add(-0x1.fefddep125F); }

    /**
     * This color constant "Pale Green" has RGBA8888 code {@code 889D98FF}, L 0.59607846, A 0.4862745, B 0.49803922, alpha 1.0, hue 0.5, saturation 0.17142858, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.fef93p125F}.
     * <pre>
     * <font style='background-color: #889D98;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #889D98; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #889D98;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #889D98'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #889D98'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #889D98'>&nbsp;@&nbsp;</font><font style='background-color: #889D98; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #889D98;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #889D98; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_GREEN = -0x1.fef93p125F;
    static { NAMED.put("Pale Green", -0x1.fef93p125F); LIST.add(-0x1.fef93p125F); }

    /**
     * This color constant "Grayish Green" has RGBA8888 code {@code 466856FF}, L 0.37254903, A 0.4745098, B 0.5058824, alpha 1.0, hue 0.44879162, saturation 0.3952847, and chroma 0.052115876.
     * It can be represented as a packed float with the constant {@code -0x1.02f2bep126F}.
     * <pre>
     * <font style='background-color: #466856;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #466856; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #466856;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #466856'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #466856'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #466856'>&nbsp;@&nbsp;</font><font style='background-color: #466856; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #466856;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #466856; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_GREEN = -0x1.02f2bep126F;
    static { NAMED.put("Grayish Green", -0x1.02f2bep126F); LIST.add(-0x1.02f2bep126F); }

    /**
     * This color constant "Dark Grayish Green" has RGBA8888 code {@code 233E2CFF}, L 0.21176471, A 0.4745098, B 0.50980395, alpha 1.0, hue 0.42620847, saturation 0.51601565, and chroma 0.054407768.
     * It can be represented as a packed float with the constant {@code -0x1.04f26cp126F}.
     * <pre>
     * <font style='background-color: #233E2C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #233E2C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #233E2C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #233E2C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #233E2C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #233E2C'>&nbsp;@&nbsp;</font><font style='background-color: #233E2C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #233E2C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #233E2C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_GREEN = -0x1.04f26cp126F;
    static { NAMED.put("Dark Grayish Green", -0x1.04f26cp126F); LIST.add(-0x1.04f26cp126F); }

    /**
     * This color constant "Blackish Green" has RGBA8888 code {@code 10271FFF}, L 0.12941177, A 0.47843137, B 0.5019608, alpha 1.0, hue 0.4685836, saturation 0.6798693, and chroma 0.04314594.
     * It can be represented as a packed float with the constant {@code -0x1.00f442p126F}.
     * <pre>
     * <font style='background-color: #10271F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #10271F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #10271F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #10271F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #10271F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #10271F'>&nbsp;@&nbsp;</font><font style='background-color: #10271F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #10271F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #10271F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACKISH_GREEN = -0x1.00f442p126F;
    static { NAMED.put("Blackish Green", -0x1.00f442p126F); LIST.add(-0x1.00f442p126F); }

    /**
     * This color constant "Greenish White" has RGBA8888 code {@code F1FFF5FF}, L 0.9843137, A 0.49019608, B 0.5019608, alpha 1.0, hue 0.42620847, saturation 0.06576671, and chroma 0.019918045.
     * It can be represented as a packed float with the constant {@code -0x1.00fbf6p126F}.
     * <pre>
     * <font style='background-color: #F1FFF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F1FFF5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F1FFF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F1FFF5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F1FFF5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F1FFF5'>&nbsp;@&nbsp;</font><font style='background-color: #F1FFF5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F1FFF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F1FFF5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GREENISH_WHITE = -0x1.00fbf6p126F;
    static { NAMED.put("Greenish White", -0x1.00fbf6p126F); LIST.add(-0x1.00fbf6p126F); }

    /**
     * This color constant "Light Greenish Gray" has RGBA8888 code {@code D6E3D9FF}, L 0.8745098, A 0.49019608, B 0.5019608, alpha 1.0, hue 0.42620847, saturation 0.070986286, and chroma 0.019918045.
     * It can be represented as a packed float with the constant {@code -0x1.00fbbep126F}.
     * <pre>
     * <font style='background-color: #D6E3D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D6E3D9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D6E3D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D6E3D9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D6E3D9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D6E3D9'>&nbsp;@&nbsp;</font><font style='background-color: #D6E3D9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D6E3D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D6E3D9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GREENISH_GRAY = -0x1.00fbbep126F;
    static { NAMED.put("Light Greenish Gray", -0x1.00fbbep126F); LIST.add(-0x1.00fbbep126F); }

    /**
     * This color constant "Greenish Gray" has RGBA8888 code {@code 6D8473FF}, L 0.49019608, A 0.48235294, B 0.5058824, alpha 1.0, hue 0.42620847, saturation 0.20327891, and chroma 0.03705794.
     * It can be represented as a packed float with the constant {@code -0x1.02f6fap126F}.
     * <pre>
     * <font style='background-color: #6D8473;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D8473; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D8473;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6D8473'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6D8473'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6D8473'>&nbsp;@&nbsp;</font><font style='background-color: #6D8473; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D8473;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D8473; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GREENISH_GRAY = -0x1.02f6fap126F;
    static { NAMED.put("Greenish Gray", -0x1.02f6fap126F); LIST.add(-0x1.02f6fap126F); }

    /**
     * This color constant "Dark Greenish Gray" has RGBA8888 code {@code 375041FF}, L 0.28627452, A 0.47843137, B 0.5058824, alpha 1.0, hue 0.4394406, saturation 0.3846546, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.02f492p126F}.
     * <pre>
     * <font style='background-color: #375041;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #375041; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #375041;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #375041'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #375041'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #375041'>&nbsp;@&nbsp;</font><font style='background-color: #375041; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #375041;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #375041; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GREENISH_GRAY = -0x1.02f492p126F;
    static { NAMED.put("Dark Greenish Gray", -0x1.02f492p126F); LIST.add(-0x1.02f492p126F); }

    /**
     * This color constant "Vivid Bluish Green" has RGBA8888 code {@code BBFEEFFF}, L 0.93333334, A 0.46666667, B 0.49803922, alpha 1.0, hue 0.5, saturation 0.3478261, and chroma 0.06652104.
     * It can be represented as a packed float with the constant {@code -0x1.feefdcp125F}.
     * <pre>
     * <font style='background-color: #BBFEEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BBFEEF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BBFEEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BBFEEF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BBFEEF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BBFEEF'>&nbsp;@&nbsp;</font><font style='background-color: #BBFEEF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BBFEEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BBFEEF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_BLUISH_GREEN = -0x1.feefdcp125F;
    static { NAMED.put("Vivid Bluish Green", -0x1.feefdcp125F); LIST.add(-0x1.feefdcp125F); }

    /**
     * This color constant "Brilliant Bluish Green" has RGBA8888 code {@code 2FE3DAFF}, L 0.76862746, A 0.43137255, B 0.4862745, alpha 1.0, hue 0.5277997, saturation 0.88526547, and chroma 0.13942632.
     * It can be represented as a packed float with the constant {@code -0x1.f8dd88p125F}.
     * <pre>
     * <font style='background-color: #2FE3DA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2FE3DA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2FE3DA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2FE3DA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2FE3DA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2FE3DA'>&nbsp;@&nbsp;</font><font style='background-color: #2FE3DA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2FE3DA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2FE3DA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_BLUISH_GREEN = -0x1.f8dd88p125F;
    static { NAMED.put("Brilliant Bluish Green", -0x1.f8dd88p125F); LIST.add(-0x1.f8dd88p125F); }

    /**
     * This color constant "Strong Bluish Green" has RGBA8888 code {@code 00756BFF}, L 0.39215687, A 0.4509804, B 0.49411765, alpha 1.0, hue 0.5132325, saturation 0.9262765, and chroma 0.098356865.
     * It can be represented as a packed float with the constant {@code -0x1.fce6c8p125F}.
     * <pre>
     * <font style='background-color: #00756B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00756B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00756B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00756B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00756B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00756B'>&nbsp;@&nbsp;</font><font style='background-color: #00756B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00756B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00756B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_BLUISH_GREEN = -0x1.fce6c8p125F;
    static { NAMED.put("Strong Bluish Green", -0x1.fce6c8p125F); LIST.add(-0x1.fce6c8p125F); }

    /**
     * This color constant "Very Light Bluish Green" has RGBA8888 code {@code BFF5F7FF}, L 0.9137255, A 0.4745098, B 0.49019608, alpha 1.0, hue 0.5512084, saturation 0.29416537, and chroma 0.054407768.
     * It can be represented as a packed float with the constant {@code -0x1.faf3d2p125F}.
     * <pre>
     * <font style='background-color: #BFF5F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFF5F7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFF5F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BFF5F7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BFF5F7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BFF5F7'>&nbsp;@&nbsp;</font><font style='background-color: #BFF5F7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFF5F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFF5F7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_BLUISH_GREEN = -0x1.faf3d2p125F;
    static { NAMED.put("Very Light Bluish Green", -0x1.faf3d2p125F); LIST.add(-0x1.faf3d2p125F); }

    /**
     * This color constant "Light Bluish Green" has RGBA8888 code {@code 519C9EFF}, L 0.5529412, A 0.4627451, B 0.4862745, alpha 1.0, hue 0.5512084, saturation 0.6120537, and chroma 0.079095535.
     * It can be represented as a packed float with the constant {@code -0x1.f8ed1ap125F}.
     * <pre>
     * <font style='background-color: #519C9E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #519C9E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #519C9E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #519C9E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #519C9E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #519C9E'>&nbsp;@&nbsp;</font><font style='background-color: #519C9E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #519C9E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #519C9E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_BLUISH_GREEN = -0x1.f8ed1ap125F;
    static { NAMED.put("Light Bluish Green", -0x1.f8ed1ap125F); LIST.add(-0x1.f8ed1ap125F); }

    /**
     * This color constant "Moderate Bluish Green" has RGBA8888 code {@code 155F62FF}, L 0.3254902, A 0.4627451, B 0.4862745, alpha 1.0, hue 0.5512084, saturation 0.86243933, and chroma 0.079095535.
     * It can be represented as a packed float with the constant {@code -0x1.f8eca6p125F}.
     * <pre>
     * <font style='background-color: #155F62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #155F62; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #155F62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #155F62'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #155F62'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #155F62'>&nbsp;@&nbsp;</font><font style='background-color: #155F62; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #155F62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #155F62; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_BLUISH_GREEN = -0x1.f8eca6p125F;
    static { NAMED.put("Moderate Bluish Green", -0x1.f8eca6p125F); LIST.add(-0x1.f8eca6p125F); }

    /**
     * This color constant "Dark Bluish Green" has RGBA8888 code {@code 0E3537FF}, L 0.18039216, A 0.4745098, B 0.49019608, alpha 1.0, hue 0.5512084, saturation 0.84327406, and chroma 0.054407768.
     * It can be represented as a packed float with the constant {@code -0x1.faf25cp125F}.
     * <pre>
     * <font style='background-color: #0E3537;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0E3537; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0E3537;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0E3537'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0E3537'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0E3537'>&nbsp;@&nbsp;</font><font style='background-color: #0E3537; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0E3537;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0E3537; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_BLUISH_GREEN = -0x1.faf25cp125F;
    static { NAMED.put("Dark Bluish Green", -0x1.faf25cp125F); LIST.add(-0x1.faf25cp125F); }

    /**
     * This color constant "Very Dark Bluish Green" has RGBA8888 code {@code 092023FF}, L 0.10980392, A 0.48235294, B 0.49019608, alpha 1.0, hue 0.57379156, saturation 0.745356, and chroma 0.040217306.
     * It can be represented as a packed float with the constant {@code -0x1.faf638p125F}.
     * <pre>
     * <font style='background-color: #092023;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #092023; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #092023;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #092023'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #092023'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #092023'>&nbsp;@&nbsp;</font><font style='background-color: #092023; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #092023;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #092023; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_BLUISH_GREEN = -0x1.faf638p125F;
    static { NAMED.put("Very Dark Bluish Green", -0x1.faf638p125F); LIST.add(-0x1.faf638p125F); }

    /**
     * This color constant "Brilliant Greenish Blue" has RGBA8888 code {@code 00B9DEFF}, L 0.6431373, A 0.44313726, B 0.45882353, alpha 1.0, hue 0.59871596, saturation 0.9831229, and chroma 0.13986339.
     * It can be represented as a packed float with the constant {@code -0x1.eae348p125F}.
     * <pre>
     * <font style='background-color: #00B9DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00B9DE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00B9DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00B9DE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00B9DE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00B9DE'>&nbsp;@&nbsp;</font><font style='background-color: #00B9DE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00B9DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00B9DE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_GREENISH_BLUE = -0x1.eae348p125F;
    static { NAMED.put("Brilliant Greenish Blue", -0x1.eae348p125F); LIST.add(-0x1.eae348p125F); }

    /**
     * This color constant "Strong Greenish Blue" has RGBA8888 code {@code 006D92FF}, L 0.38431373, A 0.4627451, B 0.45882353, alpha 1.0, hue 0.6333687, saturation 0.96097314, and chroma 0.11062346.
     * It can be represented as a packed float with the constant {@code -0x1.eaecc4p125F}.
     * <pre>
     * <font style='background-color: #006D92;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #006D92; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #006D92;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #006D92'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #006D92'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #006D92'>&nbsp;@&nbsp;</font><font style='background-color: #006D92; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #006D92;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #006D92; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_GREENISH_BLUE = -0x1.eaecc4p125F;
    static { NAMED.put("Strong Greenish Blue", -0x1.eaecc4p125F); LIST.add(-0x1.eaecc4p125F); }

    /**
     * This color constant "Very Light Greenish Blue" has RGBA8888 code {@code BAECFCFF}, L 0.8862745, A 0.47843137, B 0.48235294, alpha 1.0, hue 0.6073886, saturation 0.29105112, and chroma 0.055518243.
     * It can be represented as a packed float with the constant {@code -0x1.f6f5c4p125F}.
     * <pre>
     * <font style='background-color: #BAECFC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BAECFC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BAECFC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BAECFC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BAECFC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BAECFC'>&nbsp;@&nbsp;</font><font style='background-color: #BAECFC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BAECFC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BAECFC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_GREENISH_BLUE = -0x1.f6f5c4p125F;
    static { NAMED.put("Very Light Greenish Blue", -0x1.f6f5c4p125F); LIST.add(-0x1.f6f5c4p125F); }

    /**
     * This color constant "Light Greenish Blue" has RGBA8888 code {@code 599CB8FF}, L 0.5686275, A 0.47058824, B 0.47058824, alpha 1.0, hue 0.625, saturation 0.56568545, and chroma 0.082864076.
     * It can be represented as a packed float with the constant {@code -0x1.f0f122p125F}.
     * <pre>
     * <font style='background-color: #599CB8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #599CB8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #599CB8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #599CB8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #599CB8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #599CB8'>&nbsp;@&nbsp;</font><font style='background-color: #599CB8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #599CB8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #599CB8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GREENISH_BLUE = -0x1.f0f122p125F;
    static { NAMED.put("Light Greenish Blue", -0x1.f0f122p125F); LIST.add(-0x1.f0f122p125F); }

    /**
     * This color constant "Moderate Greenish Blue" has RGBA8888 code {@code 065C78FF}, L 0.3254902, A 0.46666667, B 0.46666667, alpha 1.0, hue 0.625, saturation 0.90509665, and chroma 0.09391262.
     * It can be represented as a packed float with the constant {@code -0x1.eeeea6p125F}.
     * <pre>
     * <font style='background-color: #065C78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #065C78; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #065C78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #065C78'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #065C78'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #065C78'>&nbsp;@&nbsp;</font><font style='background-color: #065C78; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #065C78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #065C78; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_GREENISH_BLUE = -0x1.eeeea6p125F;
    static { NAMED.put("Moderate Greenish Blue", -0x1.eeeea6p125F); LIST.add(-0x1.eeeea6p125F); }

    /**
     * This color constant "Dark Greenish Blue" has RGBA8888 code {@code 0B3446FF}, L 0.18431373, A 0.47843137, B 0.4745098, alpha 1.0, hue 0.63942873, saturation 0.86780554, and chroma 0.06652104.
     * It can be represented as a packed float with the constant {@code -0x1.f2f45ep125F}.
     * <pre>
     * <font style='background-color: #0B3446;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0B3446; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0B3446;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0B3446'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0B3446'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0B3446'>&nbsp;@&nbsp;</font><font style='background-color: #0B3446; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0B3446;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0B3446; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GREENISH_BLUE = -0x1.f2f45ep125F;
    static { NAMED.put("Dark Greenish Blue", -0x1.f2f45ep125F); LIST.add(-0x1.f2f45ep125F); }

    /**
     * This color constant "Very Dark Greenish Blue" has RGBA8888 code {@code 001F29FF}, L 0.10980392, A 0.48235294, B 0.48235294, alpha 1.0, hue 0.625, saturation 0.8702853, and chroma 0.049718447.
     * It can be represented as a packed float with the constant {@code -0x1.f6f638p125F}.
     * <pre>
     * <font style='background-color: #001F29;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #001F29; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #001F29;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #001F29'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #001F29'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #001F29'>&nbsp;@&nbsp;</font><font style='background-color: #001F29; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #001F29;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #001F29; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_GREENISH_BLUE = -0x1.f6f638p125F;
    static { NAMED.put("Very Dark Greenish Blue", -0x1.f6f638p125F); LIST.add(-0x1.f6f638p125F); }

    /**
     * This color constant "Vivid Blue" has RGBA8888 code {@code 1C469DFF}, L 0.29411766, A 0.4862745, B 0.42352942, alpha 1.0, hue 0.7250763, saturation 0.73982245, and chroma 0.15477823.
     * It can be represented as a packed float with the constant {@code -0x1.d8f896p125F}.
     * <pre>
     * <font style='background-color: #1C469D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1C469D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1C469D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1C469D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1C469D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1C469D'>&nbsp;@&nbsp;</font><font style='background-color: #1C469D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1C469D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1C469D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_BLUE = -0x1.d8f896p125F;
    static { NAMED.put("Vivid Blue", -0x1.d8f896p125F); LIST.add(-0x1.d8f896p125F); }

    /**
     * This color constant "Brilliant Blue" has RGBA8888 code {@code 338DE0FF}, L 0.5294118, A 0.47058824, B 0.43137255, alpha 1.0, hue 0.6878331, saturation 0.7660324, and chroma 0.14874557.
     * It can be represented as a packed float with the constant {@code -0x1.dcf10ep125F}.
     * <pre>
     * <font style='background-color: #338DE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #338DE0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #338DE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #338DE0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #338DE0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #338DE0'>&nbsp;@&nbsp;</font><font style='background-color: #338DE0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #338DE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #338DE0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_BLUE = -0x1.dcf10ep125F;
    static { NAMED.put("Brilliant Blue", -0x1.dcf10ep125F); LIST.add(-0x1.dcf10ep125F); }

    /**
     * This color constant "Strong Blue" has RGBA8888 code {@code 215294FF}, L 0.32156864, A 0.48235294, B 0.4392157, alpha 1.0, hue 0.70852363, saturation 0.7392464, and chroma 0.12609385.
     * It can be represented as a packed float with the constant {@code -0x1.e0f6a4p125F}.
     * <pre>
     * <font style='background-color: #215294;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #215294; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #215294;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #215294'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #215294'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #215294'>&nbsp;@&nbsp;</font><font style='background-color: #215294; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #215294;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #215294; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_BLUE = -0x1.e0f6a4p125F;
    static { NAMED.put("Strong Blue", -0x1.e0f6a4p125F); LIST.add(-0x1.e0f6a4p125F); }

    /**
     * This color constant "Deep Blue" has RGBA8888 code {@code 092059FF}, L 0.14509805, A 0.49019608, B 0.4392157, alpha 1.0, hue 0.728904, saturation 0.81798625, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.e0fa4ap125F}.
     * <pre>
     * <font style='background-color: #092059;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #092059; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #092059;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #092059'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #092059'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #092059'>&nbsp;@&nbsp;</font><font style='background-color: #092059; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #092059;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #092059; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE = -0x1.e0fa4ap125F;
    static { NAMED.put("Deep Blue", -0x1.e0fa4ap125F); LIST.add(-0x1.e0fa4ap125F); }

    /**
     * This color constant "Very Light Blue" has RGBA8888 code {@code B0D7FCFF}, L 0.8235294, A 0.4862745, B 0.47058824, alpha 1.0, hue 0.68555963, saturation 0.24969748, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.f0f9a4p125F}.
     * <pre>
     * <font style='background-color: #B0D7FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0D7FC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0D7FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B0D7FC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B0D7FC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B0D7FC'>&nbsp;@&nbsp;</font><font style='background-color: #B0D7FC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0D7FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0D7FC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_BLUE = -0x1.f0f9a4p125F;
    static { NAMED.put("Very Light Blue", -0x1.f0f9a4p125F); LIST.add(-0x1.f0f9a4p125F); }

    /**
     * This color constant "Light Blue" has RGBA8888 code {@code 5D9BDDFF}, L 0.5882353, A 0.47843137, B 0.44705883, alpha 1.0, hue 0.6915625, saturation 0.5255996, and chroma 0.11388578.
     * It can be represented as a packed float with the constant {@code -0x1.e4f52cp125F}.
     * <pre>
     * <font style='background-color: #5D9BDD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5D9BDD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5D9BDD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5D9BDD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5D9BDD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5D9BDD'>&nbsp;@&nbsp;</font><font style='background-color: #5D9BDD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5D9BDD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5D9BDD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_BLUE = -0x1.e4f52cp125F;
    static { NAMED.put("Light Blue", -0x1.e4f52cp125F); LIST.add(-0x1.e4f52cp125F); }

    /**
     * This color constant "Moderate Blue" has RGBA8888 code {@code 1A5081FF}, L 0.29803923, A 0.47843137, B 0.4509804, alpha 1.0, hue 0.68716717, saturation 0.8125, and chroma 0.106691405.
     * It can be represented as a packed float with the constant {@code -0x1.e6f498p125F}.
     * <pre>
     * <font style='background-color: #1A5081;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1A5081; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1A5081;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1A5081'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1A5081'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1A5081'>&nbsp;@&nbsp;</font><font style='background-color: #1A5081; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1A5081;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1A5081; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_BLUE = -0x1.e6f498p125F;
    static { NAMED.put("Moderate Blue", -0x1.e6f498p125F); LIST.add(-0x1.e6f498p125F); }

    /**
     * This color constant "Dark Blue" has RGBA8888 code {@code 092543FF}, L 0.14117648, A 0.4862745, B 0.4627451, alpha 1.0, hue 0.6987916, saturation 0.86243933, and chroma 0.079095535.
     * It can be represented as a packed float with the constant {@code -0x1.ecf848p125F}.
     * <pre>
     * <font style='background-color: #092543;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #092543; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #092543;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #092543'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #092543'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #092543'>&nbsp;@&nbsp;</font><font style='background-color: #092543; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #092543;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #092543; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_BLUE = -0x1.ecf848p125F;
    static { NAMED.put("Dark Blue", -0x1.ecf848p125F); LIST.add(-0x1.ecf848p125F); }

    /**
     * This color constant "Very Pale Blue" has RGBA8888 code {@code DEEDFEFF}, L 0.92156863, A 0.49411765, B 0.4862745, alpha 1.0, hue 0.6987916, saturation 0.08663774, and chroma 0.029749114.
     * It can be represented as a packed float with the constant {@code -0x1.f8fdd6p125F}.
     * <pre>
     * <font style='background-color: #DEEDFE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEEDFE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEEDFE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DEEDFE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DEEDFE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DEEDFE'>&nbsp;@&nbsp;</font><font style='background-color: #DEEDFE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEEDFE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEEDFE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_PALE_BLUE = -0x1.f8fdd6p125F;
    static { NAMED.put("Very Pale Blue", -0x1.f8fdd6p125F); LIST.add(-0x1.f8fdd6p125F); }

    /**
     * This color constant "Pale Blue" has RGBA8888 code {@code 7F94BFFF}, L 0.5803922, A 0.49411765, B 0.46666667, alpha 1.0, hue 0.73020846, saturation 0.17719248, and chroma 0.06743233.
     * It can be represented as a packed float with the constant {@code -0x1.eefd28p125F}.
     * <pre>
     * <font style='background-color: #7F94BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F94BF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F94BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7F94BF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7F94BF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7F94BF'>&nbsp;@&nbsp;</font><font style='background-color: #7F94BF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F94BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F94BF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BLUE = -0x1.eefd28p125F;
    static { NAMED.put("Pale Blue", -0x1.eefd28p125F); LIST.add(-0x1.eefd28p125F); }

    /**
     * This color constant "Grayish Blue" has RGBA8888 code {@code 3C5063FF}, L 0.3019608, A 0.49019608, B 0.47843137, alpha 1.0, hue 0.6894406, saturation 0.3263736, and chroma 0.0471994.
     * It can be represented as a packed float with the constant {@code -0x1.f4fa9ap125F}.
     * <pre>
     * <font style='background-color: #3C5063;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C5063; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C5063;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3C5063'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3C5063'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3C5063'>&nbsp;@&nbsp;</font><font style='background-color: #3C5063; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C5063;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C5063; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_BLUE = -0x1.f4fa9ap125F;
    static { NAMED.put("Grayish Blue", -0x1.f4fa9ap125F); LIST.add(-0x1.f4fa9ap125F); }

    /**
     * This color constant "Dark Grayish Blue" has RGBA8888 code {@code 232D3AFF}, L 0.17254902, A 0.49411765, B 0.48235294, alpha 1.0, hue 0.7110103, saturation 0.2748737, and chroma 0.03705794.
     * It can be represented as a packed float with the constant {@code -0x1.f6fc58p125F}.
     * <pre>
     * <font style='background-color: #232D3A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #232D3A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #232D3A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #232D3A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #232D3A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #232D3A'>&nbsp;@&nbsp;</font><font style='background-color: #232D3A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #232D3A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #232D3A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_BLUE = -0x1.f6fc58p125F;
    static { NAMED.put("Dark Grayish Blue", -0x1.f6fc58p125F); LIST.add(-0x1.f6fc58p125F); }

    /**
     * This color constant "Blackish Blue" has RGBA8888 code {@code 121B26FF}, L 0.101960786, A 0.49411765, B 0.48235294, alpha 1.0, hue 0.7110103, saturation 0.37482777, and chroma 0.03705794.
     * It can be represented as a packed float with the constant {@code -0x1.f6fc34p125F}.
     * <pre>
     * <font style='background-color: #121B26;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #121B26; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #121B26;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #121B26'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #121B26'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #121B26'>&nbsp;@&nbsp;</font><font style='background-color: #121B26; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #121B26;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #121B26; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACKISH_BLUE = -0x1.f6fc34p125F;
    static { NAMED.put("Blackish Blue", -0x1.f6fc34p125F); LIST.add(-0x1.f6fc34p125F); }

    /**
     * This color constant "Bluish White" has RGBA8888 code {@code F6F8FEFF}, L 0.9764706, A 0.49803922, B 0.49411765, alpha 1.0, hue 0.75, saturation 0.0, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.fcfff2p125F}.
     * <pre>
     * <font style='background-color: #F6F8FE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6F8FE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6F8FE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F6F8FE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F6F8FE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F6F8FE'>&nbsp;@&nbsp;</font><font style='background-color: #F6F8FE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6F8FE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6F8FE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUISH_WHITE = -0x1.fcfff2p125F;
    static { NAMED.put("Bluish White", -0x1.fcfff2p125F); LIST.add(-0x1.fcfff2p125F); }

    /**
     * This color constant "Light Bluish Gray" has RGBA8888 code {@code C1C0D3FF}, L 0.7607843, A 0.5019608, B 0.4862745, alpha 1.0, hue 0.8012084, saturation 0.050596442, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.f90184p125F}.
     * <pre>
     * <font style='background-color: #C1C0D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C1C0D3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C1C0D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C1C0D3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C1C0D3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C1C0D3'>&nbsp;@&nbsp;</font><font style='background-color: #C1C0D3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C1C0D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C1C0D3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_BLUISH_GRAY = -0x1.f90184p125F;
    static { NAMED.put("Light Bluish Gray", -0x1.f90184p125F); LIST.add(-0x1.f90184p125F); }

    /**
     * This color constant "Bluish Gray" has RGBA8888 code {@code 6D7281FF}, L 0.44705883, A 0.49803922, B 0.4862745, alpha 1.0, hue 0.75, saturation 0.05940594, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.f8fee4p125F}.
     * <pre>
     * <font style='background-color: #6D7281;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D7281; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D7281;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6D7281'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6D7281'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6D7281'>&nbsp;@&nbsp;</font><font style='background-color: #6D7281; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D7281;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D7281; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUISH_GRAY = -0x1.f8fee4p125F;
    static { NAMED.put("Bluish Gray", -0x1.f8fee4p125F); LIST.add(-0x1.f8fee4p125F); }

    /**
     * This color constant "Dark Bluish Gray" has RGBA8888 code {@code 393D4AFF}, L 0.23921569, A 0.49803922, B 0.4862745, alpha 1.0, hue 0.75, saturation 0.08955224, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.f8fe7ap125F}.
     * <pre>
     * <font style='background-color: #393D4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #393D4A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #393D4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #393D4A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #393D4A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #393D4A'>&nbsp;@&nbsp;</font><font style='background-color: #393D4A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #393D4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #393D4A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_BLUISH_GRAY = -0x1.f8fe7ap125F;
    static { NAMED.put("Dark Bluish Gray", -0x1.f8fe7ap125F); LIST.add(-0x1.f8fe7ap125F); }

    /**
     * This color constant "Vivid Purplish Blue" has RGBA8888 code {@code 331BACFF}, L 0.24313726, A 0.5137255, B 0.39215687, alpha 1.0, hue 0.773408, saturation 0.852959, and chroma 0.21657681.
     * It can be represented as a packed float with the constant {@code -0x1.c9067cp125F}.
     * <pre>
     * <font style='background-color: #331BAC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #331BAC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #331BAC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #331BAC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #331BAC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #331BAC'>&nbsp;@&nbsp;</font><font style='background-color: #331BAC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #331BAC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #331BAC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_PURPLISH_BLUE = -0x1.c9067cp125F;
    static { NAMED.put("Vivid Purplish Blue", -0x1.c9067cp125F); LIST.add(-0x1.c9067cp125F); }

    /**
     * This color constant "Brilliant Purplish Blue" has RGBA8888 code {@code 696FEDFF}, L 0.49803922, A 0.50980395, B 0.4117647, alpha 1.0, hue 0.77156967, saturation 0.43536478, and chroma 0.17686298.
     * It can be represented as a packed float with the constant {@code -0x1.d304fep125F}.
     * <pre>
     * <font style='background-color: #696FED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #696FED; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #696FED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #696FED'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #696FED'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #696FED'>&nbsp;@&nbsp;</font><font style='background-color: #696FED; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #696FED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #696FED; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_PURPLISH_BLUE = -0x1.d304fep125F;
    static { NAMED.put("Brilliant Purplish Blue", -0x1.d304fep125F); LIST.add(-0x1.d304fep125F); }

    /**
     * This color constant "Strong Purplish Blue" has RGBA8888 code {@code 383CA5FF}, L 0.29411766, A 0.5058824, B 0.41568628, alpha 1.0, hue 0.7651121, saturation 0.5701357, and chroma 0.16837704.
     * It can be represented as a packed float with the constant {@code -0x1.d50296p125F}.
     * <pre>
     * <font style='background-color: #383CA5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #383CA5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #383CA5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #383CA5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #383CA5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #383CA5'>&nbsp;@&nbsp;</font><font style='background-color: #383CA5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #383CA5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #383CA5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_PURPLISH_BLUE = -0x1.d50296p125F;
    static { NAMED.put("Strong Purplish Blue", -0x1.d50296p125F); LIST.add(-0x1.d50296p125F); }

    /**
     * This color constant "Deep Purplish Blue" has RGBA8888 code {@code 1C1957FF}, L 0.14117648, A 0.5058824, B 0.4392157, alpha 1.0, hue 0.771096, saturation 0.6725665, and chroma 0.121659465.
     * It can be represented as a packed float with the constant {@code -0x1.e10248p125F}.
     * <pre>
     * <font style='background-color: #1C1957;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1C1957; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1C1957;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1C1957'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1C1957'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1C1957'>&nbsp;@&nbsp;</font><font style='background-color: #1C1957; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1C1957;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1C1957; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLISH_BLUE = -0x1.e10248p125F;
    static { NAMED.put("Deep Purplish Blue", -0x1.e10248p125F); LIST.add(-0x1.e10248p125F); }

    /**
     * This color constant "Very Light Purplish Blue" has RGBA8888 code {@code CED5FFFF}, L 0.84705883, A 0.5019608, B 0.47058824, alpha 1.0, hue 0.77258337, saturation 0.0, and chroma 0.058723815.
     * It can be represented as a packed float with the constant {@code -0x1.f101bp125F}.
     * <pre>
     * <font style='background-color: #CED5FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CED5FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CED5FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CED5FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CED5FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CED5FF'>&nbsp;@&nbsp;</font><font style='background-color: #CED5FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CED5FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CED5FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_PURPLISH_BLUE = -0x1.f101bp125F;
    static { NAMED.put("Very Light Purplish Blue", -0x1.f101bp125F); LIST.add(-0x1.f101bp125F); }

    /**
     * This color constant "Light Purplish Blue" has RGBA8888 code {@code 7281CFFF}, L 0.5294118, A 0.5019608, B 0.44313726, alpha 1.0, hue 0.7613492, saturation 0.25519398, and chroma 0.11334858.
     * It can be represented as a packed float with the constant {@code -0x1.e3010ep125F}.
     * <pre>
     * <font style='background-color: #7281CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7281CF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7281CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7281CF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7281CF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7281CF'>&nbsp;@&nbsp;</font><font style='background-color: #7281CF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7281CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7281CF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_PURPLISH_BLUE = -0x1.e3010ep125F;
    static { NAMED.put("Light Purplish Blue", -0x1.e3010ep125F); LIST.add(-0x1.e3010ep125F); }

    /**
     * This color constant "Moderate Purplish Blue" has RGBA8888 code {@code 353772FF}, L 0.24313726, A 0.5058824, B 0.44705883, alpha 1.0, hue 0.7742946, saturation 0.41755384, and chroma 0.10611779.
     * It can be represented as a packed float with the constant {@code -0x1.e5027cp125F}.
     * <pre>
     * <font style='background-color: #353772;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #353772; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #353772;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #353772'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #353772'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #353772'>&nbsp;@&nbsp;</font><font style='background-color: #353772; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #353772;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #353772; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_PURPLISH_BLUE = -0x1.e5027cp125F;
    static { NAMED.put("Moderate Purplish Blue", -0x1.e5027cp125F); LIST.add(-0x1.e5027cp125F); }

    /**
     * This color constant "Dark Purplish Blue" has RGBA8888 code {@code 18163AFF}, L 0.105882354, A 0.5058824, B 0.45882353, alpha 1.0, hue 0.78141636, saturation 0.5665577, and chroma 0.082864076.
     * It can be represented as a packed float with the constant {@code -0x1.eb0236p125F}.
     * <pre>
     * <font style='background-color: #18163A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #18163A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #18163A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #18163A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #18163A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #18163A'>&nbsp;@&nbsp;</font><font style='background-color: #18163A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #18163A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #18163A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_PURPLISH_BLUE = -0x1.eb0236p125F;
    static { NAMED.put("Dark Purplish Blue", -0x1.eb0236p125F); LIST.add(-0x1.eb0236p125F); }

    /**
     * This color constant "Very Pale Purplish Blue" has RGBA8888 code {@code E2E3FEFF}, L 0.9019608, A 0.5019608, B 0.48235294, alpha 1.0, hue 0.7889897, saturation 0.0, and chroma 0.0353726.
     * It can be represented as a packed float with the constant {@code -0x1.f701ccp125F}.
     * <pre>
     * <font style='background-color: #E2E3FE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E2E3FE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E2E3FE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E2E3FE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E2E3FE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E2E3FE'>&nbsp;@&nbsp;</font><font style='background-color: #E2E3FE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E2E3FE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E2E3FE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_PALE_PURPLISH_BLUE = -0x1.f701ccp125F;
    static { NAMED.put("Very Pale Purplish Blue", -0x1.f701ccp125F); LIST.add(-0x1.f701ccp125F); }

    /**
     * This color constant "Pale Purplish Blue" has RGBA8888 code {@code 7B83B5FF}, L 0.5294118, A 0.5019608, B 0.4627451, alpha 1.0, hue 0.76761156, saturation 0.16925955, and chroma 0.07432148.
     * It can be represented as a packed float with the constant {@code -0x1.ed010ep125F}.
     * <pre>
     * <font style='background-color: #7B83B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B83B5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B83B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7B83B5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7B83B5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7B83B5'>&nbsp;@&nbsp;</font><font style='background-color: #7B83B5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B83B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B83B5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLISH_BLUE = -0x1.ed010ep125F;
    static { NAMED.put("Pale Purplish Blue", -0x1.ed010ep125F); LIST.add(-0x1.ed010ep125F); }

    /**
     * This color constant "Grayish Purplish Blue" has RGBA8888 code {@code 32375EFF}, L 0.22745098, A 0.5019608, B 0.4627451, alpha 1.0, hue 0.76761156, saturation 0.29210922, and chroma 0.07432148.
     * It can be represented as a packed float with the constant {@code -0x1.ed0074p125F}.
     * <pre>
     * <font style='background-color: #32375E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #32375E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #32375E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #32375E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #32375E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #32375E'>&nbsp;@&nbsp;</font><font style='background-color: #32375E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #32375E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #32375E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_PURPLISH_BLUE = -0x1.ed0074p125F;
    static { NAMED.put("Grayish Purplish Blue", -0x1.ed0074p125F); LIST.add(-0x1.ed0074p125F); }

    /**
     * This color constant "Vivid Violet" has RGBA8888 code {@code 6017AEFF}, L 0.2901961, A 0.5529412, B 0.40392157, alpha 1.0, hue 0.8340455, saturation 0.89628667, and chroma 0.21854064.
     * It can be represented as a packed float with the constant {@code -0x1.cf1a94p125F}.
     * <pre>
     * <font style='background-color: #6017AE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6017AE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6017AE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6017AE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6017AE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6017AE'>&nbsp;@&nbsp;</font><font style='background-color: #6017AE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6017AE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6017AE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_VIOLET = -0x1.cf1a94p125F;
    static { NAMED.put("Vivid Violet", -0x1.cf1a94p125F); LIST.add(-0x1.cf1a94p125F); }

    /**
     * This color constant "Brilliant Violet" has RGBA8888 code {@code 8A62E6FF}, L 0.49803922, A 0.5372549, B 0.41568628, alpha 1.0, hue 0.8207313, saturation 0.5168757, and chroma 0.1836353.
     * It can be represented as a packed float with the constant {@code -0x1.d512fep125F}.
     * <pre>
     * <font style='background-color: #8A62E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A62E6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A62E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8A62E6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8A62E6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8A62E6'>&nbsp;@&nbsp;</font><font style='background-color: #8A62E6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A62E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A62E6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_VIOLET = -0x1.d512fep125F;
    static { NAMED.put("Brilliant Violet", -0x1.d512fep125F); LIST.add(-0x1.d512fep125F); }

    /**
     * This color constant "Strong Violet" has RGBA8888 code {@code 492C7FFF}, L 0.2509804, A 0.5294118, B 0.43529412, alpha 1.0, hue 0.82379156, saturation 0.6168463, and chroma 0.1415982.
     * It can be represented as a packed float with the constant {@code -0x1.df0e8p125F}.
     * <pre>
     * <font style='background-color: #492C7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #492C7F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #492C7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #492C7F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #492C7F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #492C7F'>&nbsp;@&nbsp;</font><font style='background-color: #492C7F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #492C7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #492C7F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_VIOLET = -0x1.df0e8p125F;
    static { NAMED.put("Strong Violet", -0x1.df0e8p125F); LIST.add(-0x1.df0e8p125F); }

    /**
     * This color constant "Deep Violet" has RGBA8888 code {@code 2D0352FF}, L 0.13333334, A 0.53333336, B 0.4392157, alpha 1.0, hue 0.83601034, saturation 0.94555974, and chroma 0.1381068.
     * It can be represented as a packed float with the constant {@code -0x1.e11044p125F}.
     * <pre>
     * <font style='background-color: #2D0352;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D0352; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D0352;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2D0352'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2D0352'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2D0352'>&nbsp;@&nbsp;</font><font style='background-color: #2D0352; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D0352;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D0352; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET = -0x1.e11044p125F;
    static { NAMED.put("Deep Violet", -0x1.e11044p125F); LIST.add(-0x1.e11044p125F); }

    /**
     * This color constant "Very Light Violet" has RGBA8888 code {@code DBD3FFFF}, L 0.85490197, A 0.50980395, B 0.4745098, alpha 1.0, hue 0.82379156, saturation 0.10564101, and chroma 0.054407768.
     * It can be represented as a packed float with the constant {@code -0x1.f305b4p125F}.
     * <pre>
     * <font style='background-color: #DBD3FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBD3FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBD3FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DBD3FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DBD3FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DBD3FF'>&nbsp;@&nbsp;</font><font style='background-color: #DBD3FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBD3FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBD3FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_VIOLET = -0x1.f305b4p125F;
    static { NAMED.put("Very Light Violet", -0x1.f305b4p125F); LIST.add(-0x1.f305b4p125F); }

    /**
     * This color constant "Light Violet" has RGBA8888 code {@code 9077CEFF}, L 0.53333336, A 0.5254902, B 0.44313726, alpha 1.0, hue 0.82379156, saturation 0.32952583, and chroma 0.124142565.
     * It can be represented as a packed float with the constant {@code -0x1.e30d1p125F}.
     * <pre>
     * <font style='background-color: #9077CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9077CE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9077CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9077CE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9077CE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9077CE'>&nbsp;@&nbsp;</font><font style='background-color: #9077CE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9077CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9077CE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_VIOLET = -0x1.e30d1p125F;
    static { NAMED.put("Light Violet", -0x1.e30d1p125F); LIST.add(-0x1.e30d1p125F); }

    /**
     * This color constant "Moderate Violet" has RGBA8888 code {@code 453269FF}, L 0.24313726, A 0.52156866, B 0.45490196, alpha 1.0, hue 0.82947326, saturation 0.44749874, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.e90a7cp125F}.
     * <pre>
     * <font style='background-color: #453269;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #453269; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #453269;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #453269'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #453269'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #453269'>&nbsp;@&nbsp;</font><font style='background-color: #453269; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #453269;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #453269; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_VIOLET = -0x1.e90a7cp125F;
    static { NAMED.put("Moderate Violet", -0x1.e90a7cp125F); LIST.add(-0x1.e90a7cp125F); }

    /**
     * This color constant "Dark Violet" has RGBA8888 code {@code 261739FF}, L 0.1254902, A 0.5176471, B 0.46666667, alpha 1.0, hue 0.83890384, saturation 0.5241101, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.ef084p125F}.
     * <pre>
     * <font style='background-color: #261739;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #261739; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #261739;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #261739'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #261739'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #261739'>&nbsp;@&nbsp;</font><font style='background-color: #261739; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #261739;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #261739; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_VIOLET = -0x1.ef084p125F;
    static { NAMED.put("Dark Violet", -0x1.ef084p125F); LIST.add(-0x1.ef084p125F); }

    /**
     * This color constant "Very Pale Violet" has RGBA8888 code {@code EAE1F8FF}, L 0.9019608, A 0.5058824, B 0.4862745, alpha 1.0, hue 0.84358364, saturation 0.056780335, and chroma 0.029749114.
     * It can be represented as a packed float with the constant {@code -0x1.f903ccp125F}.
     * <pre>
     * <font style='background-color: #EAE1F8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EAE1F8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EAE1F8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EAE1F8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EAE1F8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EAE1F8'>&nbsp;@&nbsp;</font><font style='background-color: #EAE1F8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EAE1F8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EAE1F8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_PALE_VIOLET = -0x1.f903ccp125F;
    static { NAMED.put("Very Pale Violet", -0x1.f903ccp125F); LIST.add(-0x1.f903ccp125F); }

    /**
     * This color constant "Pale Violet" has RGBA8888 code {@code 8F7DB5FF}, L 0.53333336, A 0.5176471, B 0.4627451, alpha 1.0, hue 0.830707, saturation 0.2214114, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.ed091p125F}.
     * <pre>
     * <font style='background-color: #8F7DB5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F7DB5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F7DB5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F7DB5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F7DB5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F7DB5'>&nbsp;@&nbsp;</font><font style='background-color: #8F7DB5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F7DB5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F7DB5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_VIOLET = -0x1.ed091p125F;
    static { NAMED.put("Pale Violet", -0x1.ed091p125F); LIST.add(-0x1.ed091p125F); }

    /**
     * This color constant "Grayish Violet" has RGBA8888 code {@code 43375AFF}, L 0.24313726, A 0.5137255, B 0.47058824, alpha 1.0, hue 0.8326245, saturation 0.29317302, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.f1067cp125F}.
     * <pre>
     * <font style='background-color: #43375A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #43375A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #43375A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #43375A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #43375A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #43375A'>&nbsp;@&nbsp;</font><font style='background-color: #43375A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #43375A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #43375A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_VIOLET = -0x1.f1067cp125F;
    static { NAMED.put("Grayish Violet", -0x1.f1067cp125F); LIST.add(-0x1.f1067cp125F); }

    /**
     * This color constant "Vivid Purple" has RGBA8888 code {@code 9F00B9FF}, L 0.38431373, A 0.59607846, B 0.41960785, alpha 1.0, hue 0.8926114, saturation 0.9556902, and chroma 0.24957238.
     * It can be represented as a packed float with the constant {@code -0x1.d730c4p125F}.
     * <pre>
     * <font style='background-color: #9F00B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9F00B9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9F00B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9F00B9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9F00B9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9F00B9'>&nbsp;@&nbsp;</font><font style='background-color: #9F00B9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9F00B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9F00B9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_PURPLE = -0x1.d730c4p125F;
    static { NAMED.put("Vivid Purple", -0x1.d730c4p125F); LIST.add(-0x1.d730c4p125F); }

    /**
     * This color constant "Brilliant Purple" has RGBA8888 code {@code CC82E7FF}, L 0.6392157, A 0.5529412, B 0.44705883, alpha 1.0, hue 0.8808918, saturation 0.40220997, and chroma 0.14915533.
     * It can be represented as a packed float with the constant {@code -0x1.e51b46p125F}.
     * <pre>
     * <font style='background-color: #CC82E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CC82E7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CC82E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CC82E7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CC82E7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CC82E7'>&nbsp;@&nbsp;</font><font style='background-color: #CC82E7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CC82E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CC82E7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_PURPLE = -0x1.e51b46p125F;
    static { NAMED.put("Brilliant Purple", -0x1.e51b46p125F); LIST.add(-0x1.e51b46p125F); }

    /**
     * This color constant "Strong Purple" has RGBA8888 code {@code 79398AFF}, L 0.34117648, A 0.5529412, B 0.4509804, alpha 1.0, hue 0.8872184, saturation 0.5948093, and chroma 0.14373726.
     * It can be represented as a packed float with the constant {@code -0x1.e71aaep125F}.
     * <pre>
     * <font style='background-color: #79398A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #79398A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #79398A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #79398A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #79398A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #79398A'>&nbsp;@&nbsp;</font><font style='background-color: #79398A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #79398A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #79398A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_PURPLE = -0x1.e71aaep125F;
    static { NAMED.put("Strong Purple", -0x1.e71aaep125F); LIST.add(-0x1.e71aaep125F); }

    /**
     * This color constant "Deep Purple" has RGBA8888 code {@code 4A1557FF}, L 0.19215687, A 0.54901963, B 0.45490196, alpha 1.0, hue 0.8882321, saturation 0.7920645, and chroma 0.13269757.
     * It can be represented as a packed float with the constant {@code -0x1.e91862p125F}.
     * <pre>
     * <font style='background-color: #4A1557;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A1557; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A1557;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4A1557'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4A1557'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4A1557'>&nbsp;@&nbsp;</font><font style='background-color: #4A1557; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A1557;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A1557; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE = -0x1.e91862p125F;
    static { NAMED.put("Deep Purple", -0x1.e91862p125F); LIST.add(-0x1.e91862p125F); }

    /**
     * This color constant "Very Deep Purple" has RGBA8888 code {@code 330041FF}, L 0.1254902, A 0.5411765, B 0.45490196, alpha 1.0, hue 0.875, saturation 0.94280905, and chroma 0.121659465.
     * It can be represented as a packed float with the constant {@code -0x1.e9144p125F}.
     * <pre>
     * <font style='background-color: #330041;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #330041; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #330041;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #330041'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #330041'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #330041'>&nbsp;@&nbsp;</font><font style='background-color: #330041; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #330041;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #330041; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DEEP_PURPLE = -0x1.e9144p125F;
    static { NAMED.put("Very Deep Purple", -0x1.e9144p125F); LIST.add(-0x1.e9144p125F); }

    /**
     * This color constant "Very Light Purple" has RGBA8888 code {@code F6D8FBFF}, L 0.89411765, A 0.5176471, B 0.48235294, alpha 1.0, hue 0.8926114, saturation 0.110398695, and chroma 0.049718447.
     * It can be represented as a packed float with the constant {@code -0x1.f709c8p125F}.
     * <pre>
     * <font style='background-color: #F6D8FB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6D8FB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6D8FB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F6D8FB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F6D8FB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F6D8FB'>&nbsp;@&nbsp;</font><font style='background-color: #F6D8FB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6D8FB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6D8FB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_PURPLE = -0x1.f709c8p125F;
    static { NAMED.put("Very Light Purple", -0x1.f709c8p125F); LIST.add(-0x1.f709c8p125F); }

    /**
     * This color constant "Light Purple" has RGBA8888 code {@code B684CEFF}, L 0.6039216, A 0.5372549, B 0.45882353, alpha 1.0, hue 0.875, saturation 0.30743772, and chroma 0.11062346.
     * It can be represented as a packed float with the constant {@code -0x1.eb1334p125F}.
     * <pre>
     * <font style='background-color: #B684CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B684CE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B684CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B684CE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B684CE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B684CE'>&nbsp;@&nbsp;</font><font style='background-color: #B684CE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B684CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B684CE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_PURPLE = -0x1.eb1334p125F;
    static { NAMED.put("Light Purple", -0x1.eb1334p125F); LIST.add(-0x1.eb1334p125F); }

    /**
     * This color constant "Moderate Purple" has RGBA8888 code {@code 714479FF}, L 0.34117648, A 0.5372549, B 0.46666667, alpha 1.0, hue 0.8926114, saturation 0.4131048, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.ef12aep125F}.
     * <pre>
     * <font style='background-color: #714479;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #714479; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #714479;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #714479'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #714479'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #714479'>&nbsp;@&nbsp;</font><font style='background-color: #714479; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #714479;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #714479; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_PURPLE = -0x1.ef12aep125F;
    static { NAMED.put("Moderate Purple", -0x1.ef12aep125F); LIST.add(-0x1.ef12aep125F); }

    /**
     * This color constant "Dark Purple" has RGBA8888 code {@code 3E2549FF}, L 0.1882353, A 0.5254902, B 0.47058824, alpha 1.0, hue 0.875, saturation 0.46044162, and chroma 0.07753685.
     * It can be represented as a packed float with the constant {@code -0x1.f10c6p125F}.
     * <pre>
     * <font style='background-color: #3E2549;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3E2549; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3E2549;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3E2549'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3E2549'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3E2549'>&nbsp;@&nbsp;</font><font style='background-color: #3E2549; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3E2549;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3E2549; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_PURPLE = -0x1.f10c6p125F;
    static { NAMED.put("Dark Purple", -0x1.f10c6p125F); LIST.add(-0x1.f10c6p125F); }

    /**
     * This color constant "Very Dark Purple" has RGBA8888 code {@code 280E33FF}, L 0.10980392, A 0.5294118, B 0.46666667, alpha 1.0, hue 0.875, saturation 0.7299167, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.ef0e38p125F}.
     * <pre>
     * <font style='background-color: #280E33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #280E33; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #280E33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #280E33'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #280E33'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #280E33'>&nbsp;@&nbsp;</font><font style='background-color: #280E33; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #280E33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #280E33; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_PURPLE = -0x1.ef0e38p125F;
    static { NAMED.put("Very Dark Purple", -0x1.ef0e38p125F); LIST.add(-0x1.ef0e38p125F); }

    /**
     * This color constant "Very Pale Purple" has RGBA8888 code {@code F4E9FAFF}, L 0.93333334, A 0.5058824, B 0.49019608, alpha 1.0, hue 0.875, saturation 0.045990683, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.fb03dcp125F}.
     * <pre>
     * <font style='background-color: #F4E9FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F4E9FA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F4E9FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F4E9FA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F4E9FA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F4E9FA'>&nbsp;@&nbsp;</font><font style='background-color: #F4E9FA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F4E9FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F4E9FA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_PALE_PURPLE = -0x1.fb03dcp125F;
    static { NAMED.put("Very Pale Purple", -0x1.fb03dcp125F); LIST.add(-0x1.fb03dcp125F); }

    /**
     * This color constant "Pale Purple" has RGBA8888 code {@code A587B3FF}, L 0.5803922, A 0.52156866, B 0.4745098, alpha 1.0, hue 0.875, saturation 0.1885618, and chroma 0.06652104.
     * It can be represented as a packed float with the constant {@code -0x1.f30b28p125F}.
     * <pre>
     * <font style='background-color: #A587B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A587B3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A587B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A587B3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A587B3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A587B3'>&nbsp;@&nbsp;</font><font style='background-color: #A587B3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A587B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A587B3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLE = -0x1.f30b28p125F;
    static { NAMED.put("Pale Purple", -0x1.f30b28p125F); LIST.add(-0x1.f30b28p125F); }

    /**
     * This color constant "Grayish Purple" has RGBA8888 code {@code 634D67FF}, L 0.3372549, A 0.5176471, B 0.48235294, alpha 1.0, hue 0.8926114, saturation 0.2099385, and chroma 0.049718447.
     * It can be represented as a packed float with the constant {@code -0x1.f708acp125F}.
     * <pre>
     * <font style='background-color: #634D67;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #634D67; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #634D67;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #634D67'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #634D67'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #634D67'>&nbsp;@&nbsp;</font><font style='background-color: #634D67; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #634D67;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #634D67; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_PURPLE = -0x1.f708acp125F;
    static { NAMED.put("Grayish Purple", -0x1.f708acp125F); LIST.add(-0x1.f708acp125F); }

    /**
     * This color constant "Dark Grayish Purple" has RGBA8888 code {@code 3B2D3DFF}, L 0.19607843, A 0.5137255, B 0.4862745, alpha 1.0, hue 0.89758337, saturation 0.23255815, and chroma 0.038669903.
     * It can be represented as a packed float with the constant {@code -0x1.f90664p125F}.
     * <pre>
     * <font style='background-color: #3B2D3D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B2D3D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B2D3D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B2D3D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B2D3D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B2D3D'>&nbsp;@&nbsp;</font><font style='background-color: #3B2D3D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B2D3D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B2D3D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_PURPLE = -0x1.f90664p125F;
    static { NAMED.put("Dark Grayish Purple", -0x1.f90664p125F); LIST.add(-0x1.f90664p125F); }

    /**
     * This color constant "Blackish Purple" has RGBA8888 code {@code 221624FF}, L 0.105882354, A 0.5137255, B 0.4862745, alpha 1.0, hue 0.89758337, saturation 0.3448276, and chroma 0.038669903.
     * It can be represented as a packed float with the constant {@code -0x1.f90636p125F}.
     * <pre>
     * <font style='background-color: #221624;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #221624; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #221624;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #221624'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #221624'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #221624'>&nbsp;@&nbsp;</font><font style='background-color: #221624; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #221624;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #221624; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACKISH_PURPLE = -0x1.f90636p125F;
    static { NAMED.put("Blackish Purple", -0x1.f90636p125F); LIST.add(-0x1.f90636p125F); }

    /**
     * This color constant "Purplish White" has RGBA8888 code {@code FBF6FEFF}, L 0.9764706, A 0.5019608, B 0.49411765, alpha 1.0, hue 0.875, saturation 0.02227108, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.fd01f2p125F}.
     * <pre>
     * <font style='background-color: #FBF6FE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FBF6FE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FBF6FE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FBF6FE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FBF6FE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FBF6FE'>&nbsp;@&nbsp;</font><font style='background-color: #FBF6FE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FBF6FE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FBF6FE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURPLISH_WHITE = -0x1.fd01f2p125F;
    static { NAMED.put("Purplish White", -0x1.fd01f2p125F); LIST.add(-0x1.fd01f2p125F); }

    /**
     * This color constant "Light Purplish Gray" has RGBA8888 code {@code D3C3DBFF}, L 0.7921569, A 0.50980395, B 0.4862745, alpha 1.0, hue 0.875, saturation 0.077138916, and chroma 0.033602834.
     * It can be represented as a packed float with the constant {@code -0x1.f90594p125F}.
     * <pre>
     * <font style='background-color: #D3C3DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3C3DB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3C3DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D3C3DB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D3C3DB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D3C3DB'>&nbsp;@&nbsp;</font><font style='background-color: #D3C3DB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3C3DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3C3DB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_PURPLISH_GRAY = -0x1.f90594p125F;
    static { NAMED.put("Light Purplish Gray", -0x1.f90594p125F); LIST.add(-0x1.f90594p125F); }

    /**
     * This color constant "Purplish Gray" has RGBA8888 code {@code 7B6E81FF}, L 0.4509804, A 0.50980395, B 0.4862745, alpha 1.0, hue 0.875, saturation 0.11164843, and chroma 0.033602834.
     * It can be represented as a packed float with the constant {@code -0x1.f904e6p125F}.
     * <pre>
     * <font style='background-color: #7B6E81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B6E81; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B6E81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7B6E81'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7B6E81'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7B6E81'>&nbsp;@&nbsp;</font><font style='background-color: #7B6E81; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B6E81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B6E81; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURPLISH_GRAY = -0x1.f904e6p125F;
    static { NAMED.put("Purplish Gray", -0x1.f904e6p125F); LIST.add(-0x1.f904e6p125F); }

    /**
     * This color constant "Dark Purplish Gray" has RGBA8888 code {@code 443C47FF}, L 0.24705882, A 0.5058824, B 0.49019608, alpha 1.0, hue 0.875, saturation 0.10878566, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.fb027ep125F}.
     * <pre>
     * <font style='background-color: #443C47;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #443C47; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #443C47;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #443C47'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #443C47'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #443C47'>&nbsp;@&nbsp;</font><font style='background-color: #443C47; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #443C47;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #443C47; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_PURPLISH_GRAY = -0x1.fb027ep125F;
    static { NAMED.put("Dark Purplish Gray", -0x1.fb027ep125F); LIST.add(-0x1.fb027ep125F); }

    /**
     * This color constant "Vivid Reddish Purple" has RGBA8888 code {@code B11C99FF}, L 0.40392157, A 0.6, B 0.4509804, alpha 1.0, hue 0.9311804, saturation 0.89486384, and chroma 0.22186674.
     * It can be represented as a packed float with the constant {@code -0x1.e732cep125F}.
     * <pre>
     * <font style='background-color: #B11C99;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B11C99; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B11C99;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B11C99'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B11C99'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B11C99'>&nbsp;@&nbsp;</font><font style='background-color: #B11C99; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B11C99;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B11C99; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_REDDISH_PURPLE = -0x1.e732cep125F;
    static { NAMED.put("Vivid Reddish Purple", -0x1.e732cep125F); LIST.add(-0x1.e732cep125F); }

    /**
     * This color constant "Strong Reddish Purple" has RGBA8888 code {@code 8A3078FF}, L 0.34117648, A 0.5686275, B 0.46666667, alpha 1.0, hue 0.93343776, saturation 0.6792316, and chroma 0.15199278.
     * It can be represented as a packed float with the constant {@code -0x1.ef22aep125F}.
     * <pre>
     * <font style='background-color: #8A3078;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A3078; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A3078;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8A3078'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8A3078'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8A3078'>&nbsp;@&nbsp;</font><font style='background-color: #8A3078; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A3078;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A3078; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_REDDISH_PURPLE = -0x1.ef22aep125F;
    static { NAMED.put("Strong Reddish Purple", -0x1.ef22aep125F); LIST.add(-0x1.ef22aep125F); }

    /**
     * This color constant "Deep Reddish Purple" has RGBA8888 code {@code 570F4AFF}, L 0.19607843, A 0.56078434, B 0.47058824, alpha 1.0, hue 0.934363, saturation 0.87321246, and chroma 0.1345248.
     * It can be represented as a packed float with the constant {@code -0x1.f11e64p125F}.
     * <pre>
     * <font style='background-color: #570F4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #570F4A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #570F4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #570F4A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #570F4A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #570F4A'>&nbsp;@&nbsp;</font><font style='background-color: #570F4A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #570F4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #570F4A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_REDDISH_PURPLE = -0x1.f11e64p125F;
    static { NAMED.put("Deep Reddish Purple", -0x1.f11e64p125F); LIST.add(-0x1.f11e64p125F); }

    /**
     * This color constant "Very Deep Reddish Purple" has RGBA8888 code {@code 390035FF}, L 0.1254902, A 0.54901963, B 0.47058824, alpha 1.0, hue 0.921387, saturation 0.95256925, and chroma 0.11388578.
     * It can be represented as a packed float with the constant {@code -0x1.f1184p125F}.
     * <pre>
     * <font style='background-color: #390035;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #390035; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #390035;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #390035'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #390035'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #390035'>&nbsp;@&nbsp;</font><font style='background-color: #390035; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #390035;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #390035; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DEEP_REDDISH_PURPLE = -0x1.f1184p125F;
    static { NAMED.put("Very Deep Reddish Purple", -0x1.f1184p125F); LIST.add(-0x1.f1184p125F); }

    /**
     * This color constant "Light Reddish Purple" has RGBA8888 code {@code B769A3FF}, L 0.5294118, A 0.5529412, B 0.4745098, alpha 1.0, hue 0.93555963, saturation 0.40083018, and chroma 0.11705722.
     * It can be represented as a packed float with the constant {@code -0x1.f31b0ep125F}.
     * <pre>
     * <font style='background-color: #B769A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B769A3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B769A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B769A3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B769A3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B769A3'>&nbsp;@&nbsp;</font><font style='background-color: #B769A3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B769A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B769A3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_REDDISH_PURPLE = -0x1.f31b0ep125F;
    static { NAMED.put("Light Reddish Purple", -0x1.f31b0ep125F); LIST.add(-0x1.f31b0ep125F); }

    /**
     * This color constant "Moderate Reddish Purple" has RGBA8888 code {@code 7A4070FF}, L 0.34117648, A 0.54509807, B 0.4745098, alpha 1.0, hue 0.92620844, saturation 0.46263474, and chroma 0.10320191.
     * It can be represented as a packed float with the constant {@code -0x1.f316aep125F}.
     * <pre>
     * <font style='background-color: #7A4070;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7A4070; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7A4070;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7A4070'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7A4070'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7A4070'>&nbsp;@&nbsp;</font><font style='background-color: #7A4070; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7A4070;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7A4070; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_REDDISH_PURPLE = -0x1.f316aep125F;
    static { NAMED.put("Moderate Reddish Purple", -0x1.f316aep125F); LIST.add(-0x1.f316aep125F); }

    /**
     * This color constant "Dark Reddish Purple" has RGBA8888 code {@code 4A213FFF}, L 0.19215687, A 0.5372549, B 0.48235294, alpha 1.0, hue 0.9394406, saturation 0.5523246, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.f71262p125F}.
     * <pre>
     * <font style='background-color: #4A213F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A213F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A213F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4A213F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4A213F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4A213F'>&nbsp;@&nbsp;</font><font style='background-color: #4A213F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A213F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A213F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_REDDISH_PURPLE = -0x1.f71262p125F;
    static { NAMED.put("Dark Reddish Purple", -0x1.f71262p125F); LIST.add(-0x1.f71262p125F); }

    /**
     * This color constant "Very Dark Reddish Purple" has RGBA8888 code {@code 2D0E2EFF}, L 0.11372549, A 0.53333336, B 0.4745098, alpha 1.0, hue 0.90641636, saturation 0.7211103, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.f3103ap125F}.
     * <pre>
     * <font style='background-color: #2D0E2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D0E2E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D0E2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2D0E2E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2D0E2E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2D0E2E'>&nbsp;@&nbsp;</font><font style='background-color: #2D0E2E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D0E2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D0E2E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_REDDISH_PURPLE = -0x1.f3103ap125F;
    static { NAMED.put("Very Dark Reddish Purple", -0x1.f3103ap125F); LIST.add(-0x1.f3103ap125F); }

    /**
     * This color constant "Pale Reddish Purple" has RGBA8888 code {@code A17598FF}, L 0.52156866, A 0.5294118, B 0.48235294, alpha 1.0, hue 0.92620844, saturation 0.23537558, and chroma 0.068331465.
     * It can be represented as a packed float with the constant {@code -0x1.f70f0ap125F}.
     * <pre>
     * <font style='background-color: #A17598;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A17598; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A17598;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A17598'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A17598'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A17598'>&nbsp;@&nbsp;</font><font style='background-color: #A17598; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A17598;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A17598; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_REDDISH_PURPLE = -0x1.f70f0ap125F;
    static { NAMED.put("Pale Reddish Purple", -0x1.f70f0ap125F); LIST.add(-0x1.f70f0ap125F); }

    /**
     * This color constant "Grayish Reddish Purple" has RGBA8888 code {@code 704863FF}, L 0.3372549, A 0.5294118, B 0.4862745, alpha 1.0, hue 0.9428998, saturation 0.30514297, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.f90eacp125F}.
     * <pre>
     * <font style='background-color: #704863;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #704863; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #704863;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #704863'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #704863'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #704863'>&nbsp;@&nbsp;</font><font style='background-color: #704863; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #704863;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #704863; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_REDDISH_PURPLE = -0x1.f90eacp125F;
    static { NAMED.put("Grayish Reddish Purple", -0x1.f90eacp125F); LIST.add(-0x1.f90eacp125F); }

    /**
     * This color constant "Brilliant Purplish Pink" has RGBA8888 code {@code FFBFF2FF}, L 0.8392157, A 0.5372549, B 0.47843137, alpha 1.0, hue 0.92620844, saturation 0.21500653, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.f513acp125F}.
     * <pre>
     * <font style='background-color: #FFBFF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFBFF2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFBFF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFBFF2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFBFF2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFBFF2'>&nbsp;@&nbsp;</font><font style='background-color: #FFBFF2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFBFF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFBFF2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_PURPLISH_PINK = -0x1.f513acp125F;
    static { NAMED.put("Brilliant Purplish Pink", -0x1.f513acp125F); LIST.add(-0x1.f513acp125F); }

    /**
     * This color constant "Strong Purplish Pink" has RGBA8888 code {@code F77FCAFF}, L 0.6784314, A 0.57254905, B 0.4745098, alpha 1.0, hue 0.9513176, saturation 0.4580427, and chroma 0.15319274.
     * It can be represented as a packed float with the constant {@code -0x1.f3255ap125F}.
     * <pre>
     * <font style='background-color: #F77FCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F77FCA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F77FCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F77FCA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F77FCA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F77FCA'>&nbsp;@&nbsp;</font><font style='background-color: #F77FCA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F77FCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F77FCA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_PURPLISH_PINK = -0x1.f3255ap125F;
    static { NAMED.put("Strong Purplish Pink", -0x1.f3255ap125F); LIST.add(-0x1.f3255ap125F); }

    /**
     * This color constant "Deep Purplish Pink" has RGBA8888 code {@code D64FA6FF}, L 0.5294118, A 0.5882353, B 0.47058824, alpha 1.0, hue 0.95297885, saturation 0.6497738, and chroma 0.18528971.
     * It can be represented as a packed float with the constant {@code -0x1.f12d0ep125F}.
     * <pre>
     * <font style='background-color: #D64FA6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D64FA6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D64FA6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D64FA6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D64FA6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D64FA6'>&nbsp;@&nbsp;</font><font style='background-color: #D64FA6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D64FA6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D64FA6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLISH_PINK = -0x1.f12d0ep125F;
    static { NAMED.put("Deep Purplish Pink", -0x1.f12d0ep125F); LIST.add(-0x1.f12d0ep125F); }

    /**
     * This color constant "Light Purplish Pink" has RGBA8888 code {@code FFD0EAFF}, L 0.8784314, A 0.5254902, B 0.49019608, alpha 1.0, hue 0.95570695, saturation 0.14136136, and chroma 0.054407768.
     * It can be represented as a packed float with the constant {@code -0x1.fb0dcp125F}.
     * <pre>
     * <font style='background-color: #FFD0EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD0EA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD0EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFD0EA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFD0EA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFD0EA'>&nbsp;@&nbsp;</font><font style='background-color: #FFD0EA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD0EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD0EA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_PURPLISH_PINK = -0x1.fb0dcp125F;
    static { NAMED.put("Light Purplish Pink", -0x1.fb0dcp125F); LIST.add(-0x1.fb0dcp125F); }

    /**
     * This color constant "Moderate Purplish Pink" has RGBA8888 code {@code E296C5FF}, L 0.69411767, A 0.54509807, B 0.48235294, alpha 1.0, hue 0.9487916, saturation 0.2842497, and chroma 0.096477255.
     * It can be represented as a packed float with the constant {@code -0x1.f71762p125F}.
     * <pre>
     * <font style='background-color: #E296C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E296C5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E296C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E296C5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E296C5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E296C5'>&nbsp;@&nbsp;</font><font style='background-color: #E296C5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E296C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E296C5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_PURPLISH_PINK = -0x1.f71762p125F;
    static { NAMED.put("Moderate Purplish Pink", -0x1.f71762p125F); LIST.add(-0x1.f71762p125F); }

    /**
     * This color constant "Dark Purplish Pink" has RGBA8888 code {@code C76193FF}, L 0.5294118, A 0.5647059, B 0.4862745, alpha 1.0, hue 0.9722003, saturation 0.47951877, and chroma 0.13177444.
     * It can be represented as a packed float with the constant {@code -0x1.f9210ep125F}.
     * <pre>
     * <font style='background-color: #C76193;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C76193; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C76193;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C76193'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C76193'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C76193'>&nbsp;@&nbsp;</font><font style='background-color: #C76193; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C76193;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C76193; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_PURPLISH_PINK = -0x1.f9210ep125F;
    static { NAMED.put("Dark Purplish Pink", -0x1.f9210ep125F); LIST.add(-0x1.f9210ep125F); }

    /**
     * This color constant "Pale Purplish Pink" has RGBA8888 code {@code FBE6F2FF}, L 0.92941177, A 0.50980395, B 0.49411765, alpha 1.0, hue 0.9487916, saturation 0.0585607, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.fd05dap125F}.
     * <pre>
     * <font style='background-color: #FBE6F2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FBE6F2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FBE6F2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FBE6F2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FBE6F2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FBE6F2'>&nbsp;@&nbsp;</font><font style='background-color: #FBE6F2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FBE6F2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FBE6F2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLISH_PINK = -0x1.fd05dap125F;
    static { NAMED.put("Pale Purplish Pink", -0x1.fd05dap125F); LIST.add(-0x1.fd05dap125F); }

    /**
     * This color constant "Grayish Purplish Pink" has RGBA8888 code {@code D19EB8FF}, L 0.6862745, A 0.5294118, B 0.49019608, alpha 1.0, hue 0.9610103, saturation 0.19177236, and chroma 0.061763234.
     * It can be represented as a packed float with the constant {@code -0x1.fb0f5ep125F}.
     * <pre>
     * <font style='background-color: #D19EB8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D19EB8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D19EB8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D19EB8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D19EB8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D19EB8'>&nbsp;@&nbsp;</font><font style='background-color: #D19EB8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D19EB8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D19EB8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_PURPLISH_PINK = -0x1.fb0f5ep125F;
    static { NAMED.put("Grayish Purplish Pink", -0x1.fb0f5ep125F); LIST.add(-0x1.fb0f5ep125F); }

    /**
     * This color constant "Vivid Purplish Red" has RGBA8888 code {@code B4166AFF}, L 0.38039216, A 0.6, B 0.4862745, alpha 1.0, hue 0.98171693, saturation 0.9183335, and chroma 0.20108652.
     * It can be represented as a packed float with the constant {@code -0x1.f932c2p125F}.
     * <pre>
     * <font style='background-color: #B4166A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4166A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4166A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B4166A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B4166A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B4166A'>&nbsp;@&nbsp;</font><font style='background-color: #B4166A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4166A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4166A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_PURPLISH_RED = -0x1.f932c2p125F;
    static { NAMED.put("Vivid Purplish Red", -0x1.f932c2p125F); LIST.add(-0x1.f932c2p125F); }

    /**
     * This color constant "Strong Purplish Red" has RGBA8888 code {@code 93275AFF}, L 0.32941177, A 0.5764706, B 0.49019608, alpha 1.0, hue 0.9841372, saturation 0.77306736, and chroma 0.15359065.
     * It can be represented as a packed float with the constant {@code -0x1.fb26a8p125F}.
     * <pre>
     * <font style='background-color: #93275A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #93275A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #93275A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #93275A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #93275A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #93275A'>&nbsp;@&nbsp;</font><font style='background-color: #93275A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #93275A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #93275A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_PURPLISH_RED = -0x1.fb26a8p125F;
    static { NAMED.put("Strong Purplish Red", -0x1.fb26a8p125F); LIST.add(-0x1.fb26a8p125F); }

    /**
     * This color constant "Deep Purplish Red" has RGBA8888 code {@code 67003FFF}, L 0.21568628, A 0.57254905, B 0.4862745, alpha 1.0, hue 0.9750763, saturation 0.9617692, and chroma 0.14709508.
     * It can be represented as a packed float with the constant {@code -0x1.f9246ep125F}.
     * <pre>
     * <font style='background-color: #67003F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #67003F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #67003F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #67003F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #67003F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #67003F'>&nbsp;@&nbsp;</font><font style='background-color: #67003F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #67003F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #67003F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLISH_RED = -0x1.f9246ep125F;
    static { NAMED.put("Deep Purplish Red", -0x1.f9246ep125F); LIST.add(-0x1.f9246ep125F); }

    /**
     * This color constant "Very Deep Purplish Red" has RGBA8888 code {@code 3D002CFF}, L 0.12941177, A 0.54901963, B 0.48235294, alpha 1.0, hue 0.9524922, saturation 0.90676475, and chroma 0.10379164.
     * It can be represented as a packed float with the constant {@code -0x1.f71842p125F}.
     * <pre>
     * <font style='background-color: #3D002C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3D002C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3D002C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3D002C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3D002C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3D002C'>&nbsp;@&nbsp;</font><font style='background-color: #3D002C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3D002C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3D002C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DEEP_PURPLISH_RED = -0x1.f71842p125F;
    static { NAMED.put("Very Deep Purplish Red", -0x1.f71842p125F); LIST.add(-0x1.f71842p125F); }

    /**
     * This color constant "Moderate Purplish Red" has RGBA8888 code {@code 8A365DFF}, L 0.3372549, A 0.56078434, B 0.49019608, alpha 1.0, hue 0.98020846, saturation 0.6084723, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.fb1eacp125F}.
     * <pre>
     * <font style='background-color: #8A365D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A365D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A365D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8A365D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8A365D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8A365D'>&nbsp;@&nbsp;</font><font style='background-color: #8A365D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A365D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A365D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_PURPLISH_RED = -0x1.fb1eacp125F;
    static { NAMED.put("Moderate Purplish Red", -0x1.fb1eacp125F); LIST.add(-0x1.fb1eacp125F); }

    /**
     * This color constant "Dark Purplish Red" has RGBA8888 code {@code 521936FF}, L 0.1882353, A 0.54901963, B 0.49019608, alpha 1.0, hue 0.9757054, saturation 0.7109701, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.fb186p125F}.
     * <pre>
     * <font style='background-color: #521936;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #521936; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #521936;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #521936'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #521936'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #521936'>&nbsp;@&nbsp;</font><font style='background-color: #521936; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #521936;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #521936; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_PURPLISH_RED = -0x1.fb186p125F;
    static { NAMED.put("Dark Purplish Red", -0x1.fb186p125F); LIST.add(-0x1.fb186p125F); }

    /**
     * This color constant "Very Dark Purplish Red" has RGBA8888 code {@code 330821FF}, L 0.10980392, A 0.5411765, B 0.49019608, alpha 1.0, hue 0.97137564, saturation 0.8600261, and chroma 0.084324345.
     * It can be represented as a packed float with the constant {@code -0x1.fb1438p125F}.
     * <pre>
     * <font style='background-color: #330821;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #330821; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #330821;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #330821'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #330821'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #330821'>&nbsp;@&nbsp;</font><font style='background-color: #330821; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #330821;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #330821; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_PURPLISH_RED = -0x1.fb1438p125F;
    static { NAMED.put("Very Dark Purplish Red", -0x1.fb1438p125F); LIST.add(-0x1.fb1438p125F); }

    /**
     * This color constant "Light Grayish Purplish Red" has RGBA8888 code {@code B4708AFF}, L 0.5294118, A 0.5411765, B 0.49411765, alpha 1.0, hue 0.9855709, saturation 0.31113693, and chroma 0.082864076.
     * It can be represented as a packed float with the constant {@code -0x1.fd150ep125F}.
     * <pre>
     * <font style='background-color: #B4708A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4708A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4708A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B4708A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B4708A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B4708A'>&nbsp;@&nbsp;</font><font style='background-color: #B4708A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4708A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4708A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAYISH_PURPLISH_RED = -0x1.fd150ep125F;
    static { NAMED.put("Light Grayish Purplish Red", -0x1.fd150ep125F); LIST.add(-0x1.fd150ep125F); }

    /**
     * This color constant "Grayish Purplish Red" has RGBA8888 code {@code 794459FF}, L 0.3372549, A 0.5372549, B 0.49411765, alpha 1.0, hue 0.9841372, saturation 0.37924057, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.fd12acp125F}.
     * <pre>
     * <font style='background-color: #794459;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #794459; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #794459;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #794459'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #794459'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #794459'>&nbsp;@&nbsp;</font><font style='background-color: #794459; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #794459;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #794459; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_PURPLISH_RED = -0x1.fd12acp125F;
    static { NAMED.put("Grayish Purplish Red", -0x1.fd12acp125F); LIST.add(-0x1.fd12acp125F); }

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
                final float c1 = NAMED.get(o1, MUNSELLISH_TRANSPARENT), c2 = NAMED.get(o2, MUNSELLISH_TRANSPARENT);
                if(ColorTools.alphaInt(c1) < 128) return -10000;
                else if(ColorTools.alphaInt(c2) < 128) return 10000;
                final float s1 = ColorTools.saturation(c1), s2 = ColorTools.saturation(c2);
                if(s1 <= 0.05f && s2 > 0.05f)
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
        NAMES_BY_LIGHTNESS.sort(new Comparator<String>() {
            public int compare(String o1, String o2) {
                return Float.compare(ColorTools.channelL(NAMED.get(o1, MUNSELLISH_TRANSPARENT)), ColorTools.channelL(NAMED.get(o2, MUNSELLISH_TRANSPARENT)));
            }
        });
    }
}
