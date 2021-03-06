/**
 * Color space code specialized for IPT, Higher Quality version.
 * IPT has Intensity (roughly the same as lightness), Protan (a chromatic axis like green to red, named after the
 * red-green colorblindness called protanopia), and Tritan (a chromatic axis like blue to yellow, named after another
 * colorblindness type called tritanopia) channels. This package uses the "full" version of IPT, with 2.0 gamma
 * correction applied when converting to or from RGB, and intermediate results raised to the 0.43 power; this improves
 * handling of intensity, and you can see this in the docs by looking for unusually light colors in the darkest range
 * (<a href="https://tommyettinger.github.io/colorful-gdx/ColorTableValueIPT.html">simpler IPT here</a>,
 * <a href="https://tommyettinger.github.io/colorful-gdx/ColorTableValueIPT_HQ.html">IPT_HQ here</a>). You can use the
 * {@link com.github.tommyettinger.colorful.ipt IPT package} if your usage doesn't need the higher quality and can
 * benefit from a fast approximation of IPT.
 * <br>
 * This package is almost unique in that it has the {@link com.github.tommyettinger.colorful.ipt_hq.SimplePalette}
 * class, which offers a method to describe colors by mixing named pigments and applying adjustments, using
 * human-friendly syntax
 * ({@link com.github.tommyettinger.colorful.ipt_hq.SimplePalette#parseDescription(java.lang.String)}). IPT_HQ was
 * chosen for SimplePalette because mixing predefined colors is much more accurate and intuitive with this type of IPT
 * than with, say, RGB or YCwCm. Oklab also has a SimplePalette class
 * ({@link com.github.tommyettinger.colorful.oklab.SimplePalette}), and in some cases it may have even better color
 * mixing than IPT_HQ.
 * <br>
 * <ul>
 * <li><a href="https://tommyettinger.github.io/colorful-gdx/ColorTableValueIPT_HQ.html">Palette by value</a></li>
 * <li><a href="https://tommyettinger.github.io/colorful-gdx/ColorTableHueIPT_HQ.html">Palette by hue</a></li>
 * <li><a href="https://tommyettinger.github.io/colorful-gdx/ColorTableIPT_HQ.html">Palette by name</a></li>
 * </ul>
 */
package com.github.tommyettinger.colorful.ipt_hq;