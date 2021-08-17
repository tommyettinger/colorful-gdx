/**
 * CIELAB, the 1976 CIE L*A*B* color space.
 * This color space has L (lightness), A (one of the chromatic channels; something like cyan-to-red), and B (the other
 * chromatic channel; somewhere between blue-to-yellow) channels. It is an older, widely-used color space that is mostly
 * very good at perceptual uniformity (except between bluish colors). Like IPT_HQ, this compresses a -1 to 1 range for
 * its chromatic channels down to the 0 to 1 range by shrinking them and re-centering on 0.5 instead of 0.0. This is
 * different from Oklab, which just offsets its -0.5 to 0.5 range.
 * <br>
 * This package offers a {@link com.github.tommyettinger.colorful.pure.cielab.SimplePalette} class like the one in the
 * ipt_hq and oklab package, and compatible with the same descriptions (though the resulting colors will be a little
 * different). IPT_HQ, Oklab, and CIELAB are all very good at mixing colors evenly, with CIELAB or Oklab probably best.
 * <br>
 * <ul>
 * <li><a href="https://tommyettinger.github.io/colorful-gdx/ColorTableValueCIELAB.html">Palette by value</a></li>
 * <li><a href="https://tommyettinger.github.io/colorful-gdx/ColorTableHueCIELAB.html">Palette by hue</a></li>
 * <li><a href="https://tommyettinger.github.io/colorful-gdx/ColorTableCIELAB.html">Palette by name</a></li>
 * <li><a href="https://tommyettinger.github.io/colorful-gdx/ColorTableSimpleValueCIELAB.html">Palette by value</a></li>
 * <li><a href="https://tommyettinger.github.io/colorful-gdx/ColorTableSimpleHueCIELAB.html">Palette by hue</a></li>
 * <li><a href="https://tommyettinger.github.io/colorful-gdx/ColorTableSimpleCIELAB.html">Palette by name</a></li>
 * </ul>
 */
package com.github.tommyettinger.colorful.pure.cielab;