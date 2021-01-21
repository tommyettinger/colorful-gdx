/**
 * Color space code specialized for YCwCm, a custom color space similar to other "YCC" or "YUV" color spaces.
 * YCwCm has Luma (abbreviated Y, and roughly the same as lightness), Chromatic Warmth (abbreviated Cw, this is a
 * chromatic axis like cyan to red), and Chromatic Mildness (a chromatic axis like purple to yellow) channels. YCwCm is
 * a simple color space to compute, and both luma and warmth have aesthetic value to change individually. However, its
 * accuracy at gauging perceived lightness isn't great, and it is definitely lacking when evenly gauging saturation.
 * <br>
 * <ul>
 * <li><a href="https://tommyettinger.github.io/colorful-gdx/ColorTableValueYCwCm.html">Palette by value</a></li>
 * <li><a href="https://tommyettinger.github.io/colorful-gdx/ColorTableHueYCwCm.html">Palette by hue</a></li>
 * <li><a href="https://tommyettinger.github.io/colorful-gdx/ColorTableYCwCm.html">Palette by name</a></li>
 * </ul>
 */
package com.github.tommyettinger.colorful.ycwcm;