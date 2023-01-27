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

/**
 * Color space code specialized for YCwCm, a custom YCC color space.
 * YCwCm has Luma (perceptual lightness, abbreviated Y by custom), Chromatic Warmth (from cyan to magenta, roughly; when
 * Cw is higher, colors tend to be perceived as warmer hues, and lower values are perceived as cooler hues), and
 * Chromatic Mildness (from violet to chartreuse, with higher values of Cm generally blending into backgrounds more
 * easily than lower values with the same saturation) channels. This color space is simple to calculate, and the
 * presence of both luma and chromatic warmth gives some aesthetic freedom in tinting colors, but its luma calculation
 * isn't as good as {@link com.github.tommyettinger.colorful.ipt_hq IPT_HQ}'s intensity.
 * <br>
 * <ul>
 * <li><a href="https://tommyettinger.github.io/colorful-gdx/ColorTableValueYCwCm.html">Palette by value</a></li>
 * <li><a href="https://tommyettinger.github.io/colorful-gdx/ColorTableHueYCwCm.html">Palette by hue</a></li>
 * <li><a href="https://tommyettinger.github.io/colorful-gdx/ColorTableYCwCm.html">Palette by name</a></li>
 * </ul>
 */
package com.github.tommyettinger.colorful.ycwcm;