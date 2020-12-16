/**
 * Color space code specialized for RGB.
 * This is the same RGB color space you're almost certainly familiar with, though this has a twist where a channel value
 * of 0.5 is neutral, instead of 1.0. This allows multiplicative blending to lighten as well as darken, while keeping a
 * neutral value at 50% gray.
 */
package com.github.tommyettinger.colorful.pure.rgb;