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