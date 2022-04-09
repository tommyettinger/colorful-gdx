/**
 * HSLuv, a type of Hue, Saturation, Lightness color space with more even lightness.
 * This color space has H (hue), S (saturation, as a fraction of the maximum possible colorfulness), and L (lightness)
 * channels. It is a new color space; HSL is much older, but HSLuv uses specific formulas to allow the most-colorful
 * value to be mathematically calculated instead of looked up in a gamut file (as Oklab does).
 * <br>
 * <a href="https://www.hsluv.org/">HSLuv's website</a> does a great job at describing and showing how the color space
 * works. HSL makes a lot of sense for users to enter colors with, because there aren't any major invalid selections for
 * channels (other than 0 or 1 L with a large S).
 * <br>
 * This package offers a {@link com.github.tommyettinger.colorful.pure.hsluv.SimplePalette} class like the one in the
 * ipt_hq or Oklab package, and compatible with the same descriptions (though the resulting colors will be a little
 * different). While HSLuv and Oklab are very good at mixing colors evenly, Oklab is probably a little better at it.
 * <br>
 * <ul>
 * <li><a href="https://tommyettinger.github.io/colorful-gdx/ColorTableValueHsluv.html">Palette by value</a></li>
 * <li><a href="https://tommyettinger.github.io/colorful-gdx/ColorTableHueHsluv.html">Palette by hue</a></li>
 * <li><a href="https://tommyettinger.github.io/colorful-gdx/ColorTableHsluv.html">Palette by name</a></li>
 * </ul>
 */
package com.github.tommyettinger.colorful.pure.hsluv;