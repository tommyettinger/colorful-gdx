/**
 * Oklab, the Okay-enough LAB color space.
 * This color space has L (lightness), A (one of the chromatic channels; somewhere between cyan-to-red and
 * green-to-magenta), and B (the other chromatic channel; somewhere between violet-to-yellow and blue-to-orange)
 * channels. It is a new color space that builds on the same foundation as IPT, but seems to be better-calibrated for
 * uniform lightness and colorfulness, instead of just the emphasis on uniform hue that IPT has. Relative to IPT, Oklab
 * has a noticeably smaller range in chromatic channels (IPT's protan and tritan can range past 0.8 or as low as 0.35,
 * but the similar A and B channels in Oklab don't stray past about 0.65 at the upper end, if that), but it does this so
 * the difference between two Oklab colors is just the Euclidean distance between their components. A slight difference
 * between Oklab and IPT here is that IPT shrinks the chromatic channels to store their -1 to 1 range in a color float's
 * 0 to 1 range, then offsets the shrunken range from -0.5 to 0.5, to 0 to 1; Oklab does not need to shrink the range,
 * and only offsets it in the same way (both just add 0.5).
 * <br>
 * Here's <a href="https://bottosson.github.io/posts/oklab/">Björn Ottosson's original post introducing Oklab</a>.
 * So far, <a href="https://raphlinus.github.io/color/2021/01/18/oklab-critique.html">it stood up to analysis by Raph
 * Levien</a>, and seems to be gaining fans quickly. Oklab does especially well on smooth gradients.
 * <br>
 * This package offers a {@link com.github.tommyettinger.colorful.oklab.SimplePalette} class like the one in the ipt_hq
 * package, and compatible with the same descriptions (though the resulting colors will be a little different). Both
 * IPT_HQ and Oklab are very good at mixing colors evenly, with Oklab probably a little better at it.
 * <br>
 * <ul>
 * <li><a href="https://tommyettinger.github.io/colorful-gdx/ColorTableValueOklab.html">Palette by value</a></li>
 * <li><a href="https://tommyettinger.github.io/colorful-gdx/ColorTableHueOklab.html">Palette by hue</a></li>
 * <li><a href="https://tommyettinger.github.io/colorful-gdx/ColorTableOklab.html">Palette by name</a></li>
 * </ul>
 */
package com.github.tommyettinger.colorful.oklab;