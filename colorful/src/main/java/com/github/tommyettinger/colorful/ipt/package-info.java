/**
 * Color space code specialized for IPT.
 * IPT has Intensity (roughly the same as lightness), Protan (a chromatic axis like green to red, named after the
 * red-green colorblindness called protanopia), and Tritan (a chromatic axis like blue to yellow, named after another
 * colorblindness type called tritanopia) channels. This package uses a simplified version of IPT, without gamma
 * correction or any exponentiation, and it loses some quality regarding intensity as a result. You can use the
 * {@link com.github.tommyettinger.colorful.ipt_hq IPT_HQ package} if your usage can stand to be slower to gain
 * higher quality.
 */
package com.github.tommyettinger.colorful.ipt;