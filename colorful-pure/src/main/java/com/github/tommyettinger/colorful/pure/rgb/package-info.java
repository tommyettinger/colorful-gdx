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