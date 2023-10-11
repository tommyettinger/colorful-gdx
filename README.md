![colorful-gdx logo](images/colorful-gdx-logo-name-500x160.png)

# colorful-gdx
A libGDX mechanism to manipulate colors in powerful ways

Colorful is a small library that mostly offers some predefined shaders and code for handling colors differently from the
normal way. Why would you want this? There are several shortcomings of the default SpriteBatch shader's color handling,
specifically how it handles color tinting with `setColor(float, float, float, float)`:

  - You can use the default setColor() to make an image darker, but never lighter.
  - The default setColor() can't increase any channel's value; that is, if the red channel is 0, then nothing you do can
    make red higher.
  - The ways you can adjust tints don't match any kind of aesthetic way of representing color; you're limited to
    reducing red, green, and blue by some percentage each.

We address this in colorful by representing tint colors differently. The library focuses on six color spaces: RGB, IPT,
YCwCm, CIELAB, Oklab, and HSLuv. The emphasis mostly lies on RGB, Oklab, and HSLuv as the most likely color spaces you
would want to use. Most of this library's users will only employ one of these color spaces at a time, and the APIs for
all the color spaces are extremely similar. You most likely want to use RGB (because it is the most compatible with
existing colors), Oklab (because it is the most expressive and makes quite a lot of common color operations more
intuitive) and/or HSLuv (because it is similar to how users often expect colors to be described). 

### RGB

RGB is familiar to almost everyone who works with color on computers; it has a red, a green, and a blue channel, plus
alpha here. The difference between the way an RGB color tint works with a standard libGDX SpriteBatch, and the way one
works here, is that a SpriteBatch is neutral at the value `(1.0, 1.0, 1.0, 1.0)` and can only reduce channels as they go
lower, while here, a ColorfulBatch is neutral at the value `(0.5, 0.5, 0.5, 1.0)` and can lower channels in the same way
or raise channels if they go above 0.5. Typically, RGB is used with the `com.github.tommyettinger.colorful.rgb` package,
with the `ColorfulBatch` class replacing `SpriteBatch`. The `ColorTools` class in the same package provides various
RGB-specific color manipulation, while `FloatColors` in the parent package provides non-specific manipulation of colors
as packed floats (which are the default here). `Palette` has 256 predefined colors from the DawnBringer Aurora palette,
which is well-distributed for almost any pixel art or digital painting, as packed floats that have substantial
documentation. These colors can be accessed with their names via an ObjectFloatMap, `NAMED`, and those names in usefully
sorted orders as `NAMES_BY_LIGHTNESS` and `NAMES_BY_HUE`. There's also `SimplePalette`, which has fewer named colors
predefined, but allows specifying edited and/or combined colors using simple `String`s; more on that later.

Here's an aside about those colors as packed floats. Packed float colors may seem somewhat odd at first, but libGDX
uses them to represent a color in a way that OpenGL can easily handle. They store almost the same info as an RGBA8888
int color, except that they never use or set one bit in alpha (so they really only have 7 bits of alpha, the most
significant ones), and the RGBA bytes are in reversed order. The conversion from int color to packed float color is very
efficient thanks to how the JVM (and GWT, with a caveat) handle this operation. GWT, used for the HTML backend in
libGDX, actually defaults to a very slow conversion between int bits and float, but provides a way for libGDX (or other
libraries) to convert quite efficiently; libGDX does this with its `NumberUtils` class. The `ColorTools` class in each
color space's package provides many ways to manipulate and query packed floats.

`ColorfulBatch` doesn't just provide the option for tints to brighten and darken. It effectively has two batch colors;
one is multiplicative and is called the "tweak," while the regular color is now additive. The changes from the tweak can
lower a channel's value if the tweak has a value less than 0.5 for that channel, or can raise its value if the tweak
has a value greater than 0.5. After the tweak multiplies red, green, and blue, the regular color gets added in (minus
0.5, so low values in the regular color will subtract from the resulting color). There's standard multiplicative alpha
in the regular color, and the tweak's alpha channel adjusts lightness contrast (tweak alpha above 0.5 sharpens contrast,
while tweak alpha below 0.5 makes the image puffy or cloudy). Some games may want to use contrast as a way to highlight
specific areas, with reduced contrast in "background" regions and heightened contrast in important ones. You can combine
multiplicative and additive colors creatively to achieve certain effects; while this is easier with the other color
spaces discussed next, it can be done with RGB as well. Multiplicative colors affect the contribution of the original
texture color to the resulting color, so if you had some randomly-generated colorful static and wanted to make it look
like leaves, you could use a tweak of `(0.2f, 0.6f, 0.0f, 0.3f)` and a regular color of `(0.3f, 0.8f, 0.0f, 1.0f)` to at
least get a bit closer to a leafy background. Because libGDX's `Sprite` class depends on internal details of
`SpriteBatch` that aren't as useful with `ColorfulBatch`, we have a `ColorfulSprite` class here that allows setting its
color and its tweak, but otherwise can be treated like a Sprite. You can still use a `Sprite` with a `ColorfulBatch`,
you just can't set its tweak, so it uses the default (multiplying by 1 for all channels).

If you don't want to use `ColorfulBatch`, then `Shaders` provides `ShaderProgram` generators and GLSL code for shaders
that handle various color spaces. There are convenient functions that produce ShaderPrograms, like `makeRGBAShader()`
and `makeGammaRGBAShader()`, from the GLSL sources `fragmentShaderRGBA` and `fragmentShaderGammaRGBA`, respectively.
With these, you still use 50% gray as the neutral value, tinting with white brightens, and tinting with black eliminates
all color. However, you don't have the "tweak" that `ColorfulBatch` has, so there's no contrast adjustment with this.
You can use these `ShaderProgram`s with a standard `SpriteBatch` via `setShader()` or a `SpriteBatch` constructor.

### YCwCm

Instead of red, green, blue, and alpha channels, YCwCm uses luma (also called lightness), chromatic warmth, chromatic
mildness, and alpha. It's the first color space that was implemented here, and it's still not exactly perfect; there's
no `SimplePalette` here, and sometimes two colors with equivalent luma (as YCwCm calculates it) do not look like they
have the same actual lightness. You may want to skip this section and move ahead to Oklab if you intend to use a better
color space from the start, but this also describes some commonly-used classes shared by the color spaces.

The chromatic channels are only meaningful together, and can be used to get the hue and colorfulness (AKA chroma,
related to saturation) of any individual color. All channels go from `0.0f` to `1.0f` as `float`s,
and can also be viewed as `int`s from `0` to `255` (`254` for alpha, because it is never an odd number). For luma,
`0.0f` is black regardless of chromatic channels, and `1.0f` is white, again regardless. Tinting an image with black
won't actually make the image all black, unlike the default setColor(), but it will make it much darker. Similarly,
tinting an image with white will make the image much lighter (unlike the default, where white makes no change). When you
want to tint with a neutral color, use `batch.setColor(0.5f, 0.5f, 0.5f, 1.0f)` or `batch.setPackedColor(Palette.GRAY)`;
this will make almost no changes to the colors in the textures you draw. For chromatic warmth, `0.0f` is used for colors
from green to blue, while `1.0f` is used for colors from yellow to red. For chromatic mildness, `0.0f` is used for
colors from blue to red, while `1.0f` is used for colors from green to yellow. When both warmth and mildness are `0.5f`,
that represents a grayscale color, which means it makes no change to the hue or saturation of the image drawn. For
alpha, it acts exactly like alpha does normally in SpriteBatch.

YCwCm uses a similar naming convention to
[YcbCr](https://en.wikipedia.org/wiki/YCbCr) or [YCoCg](https://en.wikipedia.org/wiki/YCoCg), both close relatives. The
reason this library uses YCwCm instead of YCoCg is that it is comparable in computational cost to transform to and from
RGB, but the luma is somewhat more accurate with YCwCm, and the warmth axis is very useful for aesthetic reasons. As an
example of the aesthetic usage, you could move an image into warm or hot hues when a fire is nearby, or into cooler hues
when the weather is freezing. When warmth is very high, it is also nice to be able to move mildness up and down, which
makes the color mimic that of fire (going from red embers to yellow sparks, spending more time near orange flame).

The YCwCm `ColorfulBatch` has a tweak color, like the RGB `ColorfulBatch`, that can be applied to colors
somewhat-independently of the regular color that can be set with `setColor()`. Where `setColor()` changes the additive
(or subtractive) luma, chromatic warmth, and chromatic mildness, plus multiplicative alpha, `setTweak()` changes:
  - the multiplicative luma (a value from 0.0 to 1.0 that maps to a multiplier from 0.0 to 2.0),
  - multiplicative chromatic warmth and chromatic mildness (two separate values from 0.0 to 1.0 that map to multipliers
    from 0.0 to 2.0, but apply to the current warmth or mildness of a pixel as if they are centered on 0.0, not 0.5),
  - and a contrast adjustment (a value from 0.0 to 1.0 that can make mid-range colors more similar when it is low, or
    separate bright colors into brighter colors and dark colors into darker colors when it is high).

Some useful things to use the tweak for include:
  - Setting the chromatic tweaks to 0.0 will make the rendered color grayscale.
    - If the regular color of a ColorfulBatch isn't gray, then the rendered color will be "green-scale" or some other
      variation of brightness for an existing color.
  - Setting the chromatic tweaks both to values higher than 0.5 will increase saturation/vividness.
  - You can set the chromatic tweaks separately, with 0.0 warmth but 0.5 mildness making all colors somewhere between
    lime and magenta, and 0.0 mildness but 0.5 warmth making all colors between red-orange and cyan.
    - Adjusting one value randomly in small movements over a small range can liven up an effect that seems too slow.
  - Reducing the contrast and increasing the color's lightness will make a misty or foggy effect.
    - You can also change the color to more blue or cyan to make a blue mist, or to pink or purple for "magic fog."
  - Sharply increasing the contrast and increasing luma tweak, optionally changing the color to something thematic, can
    help with an electric shock animation when brief frames, randomly spaced, have the adjustments and other frames have
    darkening or other adjustments.

In the library, there's the basic shader code in `Shaders.java` (and convenience methods that construct SpriteBatch
objects using those shaders), predefined packed-float colors in `ycwcm/Palette.java`, and quite a lot of methods for
manipulating those colors as floats in `FloatColors.java` and `ycwcm/ColorTools.java`. The newer method involving a
tweak requires using a ColorfulBatch instead of a SpriteBatch, but the API is almost the same, and is in
`ycwcm/ColorfulBatch.java`. If you use a ColorfulBatch, you should also use ColorfulSprite instead of Sprite if you want
to set a tweak per-sprite, but even a standard Sprite will render correctly.

The new `GradientTools` class provides some simple methods that write to libGDX `FloatArray` objects, producing a
sequence of packed float colors (allocating no objects) that go from one color to one or more later colors. Gradients
produced by YCwCm should be a little smoother than ones made by the RGB `GradientTools`, and gradients made by IPT_HQ or
Oklab should be even smoother. CIELAB... don't count on it.

The palette used is a slight adjustment on DawnBringer's Aurora palette, a 256-color palette that gets less attention
than his smaller pixel art palettes, but that has excellent coverage of most colors. The names used for colors in it are
very similar to the ones chosen for the palette as used in [SquidLib](https://github.com/SquidPony/SquidLib), though
not identical; some colors have simpler names, like `Green` instead of `Shamrock Green` or `Yellow` instead of `Lemon`.
Naming 256 colors, some of them very similar, was not easy, and some choices are probably odd.

### IPT

The IPT color space is quite similar to YCwCm in some ways, but should have smoother transitions between hues -- after
all, [that's what it was created for](https://www.researchgate.net/publication/221677980_Development_and_Testing_of_a_Color_Space_IPT_with_Improved_Hue_Uniformity)
by Ebner and Fairchild in 1998. It has I (intensity, effectively lightness), P (protan, named after protanopia, or
red-green colorblindness, and corresponding to a cyan-to-red axis), and T (tritan, named after tritanopia, another type
of colorblindness, and corresponding to a blue-to-yellow axis) channels, plus alpha here. In standard IPT, intensity is
very similar for most mid-brightness colors, but falls off suddenly from about 0.3 to 0 in a range of just 1/14 gray to
black. Here, we avoid any `Math.pow()` calculations, which evens out the intensity so 1/10 gray has 0.1 intensity. The
hue and chroma components should be fairly similar, but aren't quite identical. You might want to prefer IPT over YCwCm
if you want color transitions to look as smooth as possible, and don't mind a tiny bit of extra calculation this needs
to do internally. Even with the different calculation for intensity/lightness, most colors that are perceptually similar
in lightness should have somewhat similar intensity here.

The `com.github.tommyettinger.colorful.ipt` package has parallels to all the classes in the `ycmcw` package, and the
`ColorfulBatch`, `ColorfulSprite`, and `Palette` classes work almost identically. For ColorfulBatch, this means there's
an additive color, and a multiplicative "tweak," plus multiplicative alpha in the color and contrast in the tweak.

### IPT_HQ

This library provides two variants on IPT; the above version in the `com.github.tommyettinger.colorful.ipt` package is
simpler to compute, while a newer version in the `com.github.tommyettinger.colorful.ipt_hq` package is more faithful to
Ebner's and Fairchild's paper, and gauges the lightness of colors much more accurately. To get a sense of whether you
should use IPT or IPT_HQ, you can compare these palette lists, which are sorted by lightness:
[the above simpler IPT list](https://tommyettinger.github.io/colorful-gdx/ColorTableValueIPT.html), and
[the more-involved IPT_HQ list](https://tommyettinger.github.io/colorful-gdx/ColorTableValueIPT_HQ.html).
IPT has some odd jumps in lightness for red and blue, while IPT_HQ has all perceptually-dark colors in the start of its
list. Using IPT_HQ involves some extra operations that aren't always especially fast, especially when on the GPU, and
they need to be calculated on many fragments. The IPT_HQ shader is still branch-less and isn't much longer than the IPT
shader, so the performance dip is likely to be small.

### Oklab

So, IPT is great when comparing the hues of colors, but isn't optimal when comparing their lightness, or their chroma
(how close or far from being gray they are). YCwCm isn't great at either. RGB doesn't compare lightness easily at all.
How about something new, then? The similar, much newer color space Oklab, by Björn Ottosson, was
introduced in [this recent blog post](https://bottosson.github.io/posts/oklab/), and seeks to remedy the mismatch
between hue and the other aspects of color comparison. It has the components L (lightness), A (one chromatic channel,
roughly describing how cool or warm a color is, with high values closer to red and low values closer to cyan), and
B (the other chromatic channel, also in a sense describing something like cool to warm, but with high values closer to
yellow and low values closer to blue). It's like a slightly-rotated version of IPT or YCwCm. The main benefits of Oklab
are for comparing colors, where you can use a standard Euclidean distance, and for making smooth gradients. It may also
be a slight bit faster than IPT_HQ, even though its calculations are extremely similar, because Oklab uses a fast
approximation of cube root when it's processed by Java, where IPT_HQ uses a slightly slower call to `Math.pow()` with
0.43 as the exponent. Going in reverse, Oklab can just do `n * n * n` where IPT_HQ needs to use `Math.pow()` again but
also preserve the sign of its argument. This difference probably won't be noticeable in practice, since most color
processing will be done on the GPU for the most intensive applications.

Also, the more I use Oklab, the more I want to keep using Oklab, so future work is probably going to continue on
improving features in Oklab or making variants on Oklab for mysterious purposes.

The exact variety of Oklab isn't 100% faithful to the linked blog post by Ottosson; as he detailed in
[a later blog post](https://bottosson.github.io/posts/colorpicker/#intermission---a-new-lightness-estimate-for-oklab),
Oklab as it was originally detailed has far too many colors that are nearly-black, and too few that are close to white.
Ottosson devised a high-quality conversion from the dark-color-heavy scale to a more-uniform scale, but computing it
involves quite a bit of code per-pixel, so I used a modified ["Barron spline"](https://arxiv.org/abs/2010.09714) that
also gets 50% gray to 0.5 L. This is where it should be; L would be at 0.63 before applying the spline. Various other
changes have applied across versions to Oklab to get its gamut, or the range of valid colors that can be converted to
and from RGBA, correct, and it should be now.

The `com.github.tommyettinger.colorful.oklab` package has parallels to all the classes in the `ipt_hq` package, which
includes those in `ycwcm` and `ipt` as well. Its `SimplePalette` is particularly adept at smoothly changing colors.

### CIELAB

CIELAB, or more accurately, the `1976 CIE L*A*B* Color Space`, is mostly here for completeness; in practice, many common
operations are done better by the implementation of Oklab here. Both Oklab and CIELAB use L for lightness, A for red vs.
cyan, and B for yellow vs. blue, but they calculate lots of little things differently, and CIELAB is sometimes smoother.
However, we only store the full gamut information for Oklab (it's stored in a giant String that gets read as bytes),
while CIELAB needs to calculate the approximate gamut for each color as requested. The full gamut is large, and I didn't
want to store it multiple times for two similar color spaces, so CIELAB is just generally slower at gamut-related code,
and less precise. CIELAB is slower at most operations by at least a little bit, relative to Oklab. It does handle some
gradients more accurately, but most less accurately, so every use case may encounter trade-offs. A notable flaw of the
implementation here is that grayscale colors "tilt" out of the central line along A=0.5, B=0.5, and around black or very
dark colors, there isn't any color in-gamut with A=0.5 and B=0.5.

The `com.github.tommyettinger.colorful.cielab` package has parallels to all the classes in the `ipt_hq` package, which
includes those in `ycwcm`, `ipt`, and `oklab` as well. Its `SimplePalette` is just-okay, though it tends to change the
hue of colors when it lightens or darkens them. As you can see from the description demo (see next), CIELAB has all
kinds of trouble with blue and similar colors.

### HSLuv

HSLuv is a form of Hue, Saturation, Lightness color space, similar to the standard HSL, but with drastically more-even
perceptual lightness when L is the same but H and/or S change. As you can see from
[the HSLuv website](https://www.hsluv.org/), it is very useful for cases such as user-selected colors. It has the nice
quality that nearly all combinations of H, S, and L are valid colors; only min or max L with high S are technically not
supposed to appear, and this can tolerate those colors being entered anyway. Adding to hue rotates from red to orange to
yellow, and so on; adding to saturation brings the colorfulness closer to the maximum (at 1.0), and adding to lightness,
well, lightens. With a `ColorfulBatch`, you can also multiply hue (which is basically useless, and may be changed in a
future release), multiply saturation (which is much more useful, and makes the image more bright/bold or
dull/desaturated), multiply lightness (which occurs before lightness is added), and adjust contrast (like in the other
color spaces).

Like Oklab, HSLuv uses a modified lightness value so it should match the expectations of RGB very closely. It also uses
a Barron spline, though a different one from Oklab. Unlike Oklab, HSLuv does not need a large precalculated Gamut file,
because its gamut can be calculated with several relatively-simple formulas. I have to give thanks to the community
around HSLuv, such as [Nathan Sweet's HSLuv code](https://github.com/EsotericSoftware/hsl/blob/main/src/com/esotericsoftware/hsluv/Hsl.java)
and [Alex Boronine's earlier code](https://github.com/hsluv/hsluv-java); I wouldn't have taken a second look at HSLuv if
not for Nathan Sweet's more-efficient conversion code.

The `com.github.tommyettinger.colorful.hsluv` package has parallels to all the classes in the `ipt_hq` package, which
includes those in `ycwcm`, `ipt`, and `oklab` as well. Its `SimplePalette` is not at all shabby.

### Describing Colors

The `rgb`, `ipt_hq`, `oklab`, `hsluv`, and `cielab` packages have the same classes present for other color spaces, like
those in `ipt`, plus an extra palette, `SimplePalette`, with a key extra feature. You can use the
`SimplePalette.parseDescription(String)`
method to describe a color with a combination of one or more (clearly-named) color names and optionally with adjectives
like "light", "dull", "darker", or "richest". The predefined colors in SimplePalette for IPT_HQ can be previewed in
[this list alphabetically](https://tommyettinger.github.io/colorful-gdx/ColorTableSimpleIPT_HQ.html),
[this list by hue](https://tommyettinger.github.io/colorful-gdx/ColorTableHueSimpleIPT_HQ.html), or
[this list by lightness](https://tommyettinger.github.io/colorful-gdx/ColorTableValueSimpleIPT_HQ.html). The predefined
colors in SimplePalette for Oklab can be previewed in
[this list alphabetically](https://tommyettinger.github.io/colorful-gdx/ColorTableSimpleOklab.html),
[this list by hue](https://tommyettinger.github.io/colorful-gdx/ColorTableHueSimpleOklab.html), or
[this list by lightness](https://tommyettinger.github.io/colorful-gdx/ColorTableValueSimpleOklab.html).

You can use [this small libGDX web app](https://tommyettinger.github.io/colorful-gdx/description/) to experiment with
different descriptions and what they produce. Use the `[` and `]` keys to change modes; there are RGB, Oklab, IPT_HQ,
CIELAB, comparison, and gradient modes. The comparison mode may be the most useful; it has 4 bars that change color
using different color spaces and their SimplePalette transformations. The gradient mode is new, and lets you preview
Oklab gradients between two described colors.

### HSLC

If for some reason you don't want to use HSLuv, there's a version that has contrast instead of alpha in tints.

HSLC doesn't allow changing alpha, so it may be unsuitable for some tasks, but it does allow smooth hue rotations across
the HSL hue range, can saturate or desaturate colors like the two Chroma values can in YCwCm, and has similar luma
adjustment to YCwCm as well. Like with YCwCm, when you tint with all values equal to 0.5f, then the result color
shouldn't change. Raising or lowering hue (stored in the red channel) will rotate the hue away from the input color.
Raising saturation (stored in the green channel) will make the colors more vivid, while decreasing it wll make them
closer to grayscale. Raising lightness (stored in the blue channel) will make colors lighter (it can make them brighter
than the original color if lightness is greater than 0.5), while lowering it will make colors darker. Contrast affects
how rapidly the lightness in input colors changes, so when contrast is high, even slightly different mid-range colors
will have stark lightness differences, while when contrast is low, most lightness will be in the mid-range.

### colorful vs. colorful-pure

Starting with version 0.4.0, there are two similar, but not identical, sub-projects in this repo: colorful, which will
probably be used more frequently, and colorful-pure, which is more specialized. You want colorful if you already depend
on libGDX (currently on version 1.9.13 or higher); it has the useful `ColorfulBatch` and `ColorfulSprite` classes, and
can convert to and from libGDX `Color` objects. If you have a server project, or some other kind of project that doesn't
have a dependency on libGDX, then you might want colorful-pure instead. Instead of libGDX, colorful-pure depends on
[jdkgdxds](https://github.com/tommyettinger/jdkgdxds) for its primitive-backed data structures, and needs Java 8 or
higher (colorful needs Java 7 or higher). Both colorful and colorful-pure produce compatible packed float colors when
they use the same color space, and even though their `Palette` classes use different data structures, the colors in
those palettes are the same. The descriptive color system in `SimplePalette` for the `rgb`, `ipt_hq`, `oklab`, and
`cielab` packages may be especially useful in colorful-pure.

## Samples

These all show Oklab changes.

Tinting with gray as the color causes no change to the original image.
![Tinting with gray](https://i.imgur.com/5a4LUDr.png)

Tinting with black as the color makes it much darker, but keeps the most colorful areas where they are.
![Tinting with black](https://i.imgur.com/O5oeoWA.png)

Tinting with white as the color makes it much lighter, which isn't possible with the default SpriteBatch shader and color representation.
![Tinting with white](https://i.imgur.com/Rg4CSrY.png)

It's a common request to be able to make a Sprite or other texture flash red when a character is hurt; you can tint with the predefined color `Palette.RED` to tint any image to vivid red (including images that have `0.0f` in their red channel).
![Tinting with red](https://i.imgur.com/BKQ0NwN.png)

Tinting with gray, but using a tweak with 0.0 for the chromatic channels A and B makes the image grayscale.
![Tint with gray, tweak with 0.0 chroma](https://i.imgur.com/RbyjQ3f.png)

Tinting with the palette color `WOODLANDS`, but using a tweak with 0.0 for the chromatic channels A and B makes the image "green-scale."
![Tint with "Woodlands", tweak with 0.0 A and B](https://i.imgur.com/iQNumNc.png)

Tinting with gray, but using a tweak with 1.0 for the chromatic channels A and B makes the image more saturated.
![Tint with gray, tweak with 1.0 chroma](https://i.imgur.com/RhyLtFT.png)

Tinting with gray, but using a tweak with 0.5 for the chromatic channel A and 0.0 for the chromatic channel B distorts the colors used.
![Tint with gray, tweak with 0.0 chroma](https://i.imgur.com/Gar7qBU.png)

Tinting with the Palette color `THISTLE`, but using a tweak with about 0.6 L and about 0.25 contrast achieves the aforementioned "magic fog" effect.
![Magic Fog](https://i.imgur.com/71c4MNG.png)

Tinting with the Palette color `OCHRE` and using a tweak with L=0.43, A=0.14, B=0.258, contrast=0.8125 changes the cartoon-y graphics to a more gritty palette.
![Gritty Tint](https://i.imgur.com/ZQqRJTA.png)

## Compatibility Notes

### ProGuard

ProGuard (on desktop and iOS) doesn't do well with some code here, at least
out-of-the-box. If you are using colorful or colorful-pure, you need to add
this line to your `proguard-rules.pro` file to use ProGuard:

```
-optimizations !code/simplification/string
```

This allows some large data stored in a very long String to be loaded correctly.

Note that this line will be added automatically to the Android R8 configuration,
which is subtly different from ProGuard configuration on any other platform.
If you only use ProGuard on Android (where it's really using R8), you don't need
to change any `.pro` files manually to use colorful or colorful-pure.

### GPU/Shader Incompatibility

Older versions of colorful-gdx had issues on some particular GPUs, and would show nothing but black textures. This
has been fixed since 0.6.0 , and shouldn't affect code using the current version. If it does, please post an issue.

## How to Obtain

Using the Maven Central dependency is recommended, and Gradle and Maven can both depend on this library using that repository.

To depend on colorful, which is the main way to use the library and uses libGDX, use:

Gradle dependency (`implementation` should be changed to `api` if any other dependencies use `api`):
```groovy
implementation 'com.github.tommyettinger:colorful:0.8.5'
```

Gradle dependency in the HTML project, if present:
```groovy
implementation 'com.github.tommyettinger:colorful:0.8.5:sources'
```

And also for GWT, in your application's `.gwt.xml` file (usually `GdxDefinition.gwt.xml`)
```xml
<inherits name="com.github.tommyettinger.colorful.colorful" />
```

If you don't use Gradle, here's the Maven dependency:
```xml
<dependency>
  <groupId>com.github.tommyettinger</groupId>
  <artifactId>colorful</artifactId>
  <version>0.8.5</version>
</dependency>
```

Using colorful-pure is similar; note that **Most Client-Side Applications Do Not Need These Following Steps**.

Gradle dependency (`implementation` should be changed to `api` if any other dependencies use `api`):
```groovy
implementation 'com.github.tommyettinger:colorful-pure:0.8.5'
```

Gradle dependency in the HTML project, if present:
```groovy
implementation 'com.github.tommyettinger:funderby:0.1.1:sources'
implementation 'com.github.tommyettinger:digital:0.4.0:sources'
implementation 'com.github.tommyettinger:juniper:0.3.9:sources'
implementation 'com.github.tommyettinger:jdkgdxds:1.4.1:sources'
implementation 'com.github.tommyettinger:colorful-pure:0.8.5:sources'
```

And also for GWT, in your application's `.gwt.xml` file (usually `GdxDefinition.gwt.xml`)
```
<inherits name="funderby" />
<inherits name="digital" />
<inherits name="juniper" />
<inherits name="jdkgdxds" />
<inherits name="com.github.tommyettinger.colorful.pure.colorful_pure" />
```

If you don't use Gradle, here's the Maven dependency (GWT dependencies should be similar to Gradle):
```xml
<dependency>
  <groupId>com.github.tommyettinger</groupId>
  <artifactId>colorful-pure</artifactId>
  <version>0.8.5</version>
</dependency>
```

If you don't use Gradle or Maven, [there are jars here](https://github.com/tommyettinger/colorful-gdx/releases/).

