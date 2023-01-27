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
 * Color space code specialized for IPT.
 * IPT has Intensity (roughly the same as lightness), Protan (a chromatic axis like green to red, named after the
 * red-green colorblindness called protanopia), and Tritan (a chromatic axis like blue to yellow, named after another
 * colorblindness type called tritanopia) channels. This package uses a simplified version of IPT, without gamma
 * correction or any exponentiation, and it loses some quality regarding intensity as a result. You can use the
 * {@link com.github.tommyettinger.colorful.ipt_hq IPT_HQ package} if your usage can stand to be slower to gain
 * higher quality.
 * <br>
 * <ul>
 * <li><a href="https://tommyettinger.github.io/colorful-gdx/ColorTableValueIPT.html">Palette by value</a></li>
 * <li><a href="https://tommyettinger.github.io/colorful-gdx/ColorTableHueIPT.html">Palette by hue</a></li>
 * <li><a href="https://tommyettinger.github.io/colorful-gdx/ColorTableIPT.html">Palette by name</a></li>
 * </ul>
 */
package com.github.tommyettinger.colorful.ipt;