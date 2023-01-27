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
 * Color space code specialized for RGB.
 * This is the same RGB color space you're almost certainly familiar with, though this has a twist where a channel value
 * of 0.5 is neutral, instead of 1.0. This allows multiplicative blending to lighten as well as darken, while keeping a
 * neutral value at 50% gray.
 * <br>
 * <ul>
 * <li><a href="https://tommyettinger.github.io/colorful-gdx/ColorTableValueRGB.html">Palette by value</a></li>
 * <li><a href="https://tommyettinger.github.io/colorful-gdx/ColorTableHueRGB.html">Palette by hue</a></li>
 * <li><a href="https://tommyettinger.github.io/colorful-gdx/ColorTableRGB.html">Palette by name</a></li>
 * </ul>
 */
package com.github.tommyettinger.colorful.pure.rgb;