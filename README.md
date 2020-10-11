# colorful-gdx
A libGDX mechanism to manipulate colors in powerful ways

Colorful is a small library that mostly offers some predefined shaders and code for handling colors in a different way
than normal. Why would you want this? There's several shortcomings of the default SpriteBatch shader's color handling,
specifically how it handles color tinting with `setColor(float, float, float, float)`:

  - You can use the default setColor() to make an image darker, but never lighter.
  - The default setColor() can't increase any channel's value; that is, if the red channel is 0, then nothing you do can
    make red higher.
  - The ways you can adjust tints don't match any kind of aesthetic way of representing color; you're limited to
    reducing red, green, and blue by some percentage each.

We address this in colorful by representing tint colors differently. While there's some support here for HSLC tints
(hue, saturation, lightness, contrast) via `Shaders.makeBatchHSLC()`, much more of the library is focused on the YCwCmA
color space.

### YCwCmA

Instead of red, green, blue, and alpha channels, YCwCmA uses luma (also called lightness), chromatic
warmth, chromatic mildness, and alpha. The chromatic channels are only meaningful together, and can be used to get the
hue and colorfulness (related to saturation) of any individual color. All channels go from `0.0f` to `1.0f` as `float`s,
and can also be viewed as `int`s from `0` to `255` (`254` for alpha, because it is never an odd number). For luma,
`0.0f` is black regardless of chromatic channels, and `1.0f` is white, again regardless. Tinting an image with black
won't actually make the image all black, unlike the default setColor(), but it will make it much darker. Similarly,
tinting an image with white will make the image much lighter (unlike the default, where white makes no change). When you
want to tint with a neutral color, use `batch.setColor(0.5f, 0.5f, 0.5f, 1.0f)` or `batch.setPackedColor(Palette.GRAY)`;
this will make almost no changes to the colors in the textures you draw. For chromatic warmth, `0.0f` is used for colors
from green to blue, while `1.0f` is used for colors from yellow to red. For chromatic mildness, `0.0f` is used for
colors from blue to red, while `1.0f` is used for colors from green to yellow. When both warmth and mildness are `0.5f`,
that represents a grayscale color, which means it makes no change to the hue or saturation of the image drawn. For
alpha, it acts exactly like alpha does normally in SpriteBatch. YCwCmA uses a similar naming convention to
[YcbCr](https://en.wikipedia.org/wiki/YCbCr) or [YCoCg](https://en.wikipedia.org/wiki/YCoCg), both close relatives. The
reason this library uses YCwCmA instead of YCoCg is that it is comparable in computational cost to transform to and from
RGB, but the luma is somewhat more accurate with YCwCmA, and the warmth axis is very useful for aesthetic reasons. As an
example of the aesthetic usage, you could move an image into warm or hot hues when a fire is nearby, or into cooler hues
when the weather is freezing. When warmth is very high, it is also nice to be able to move mildness up and down, which
makes the color mimic that of fire (going from red embers to yellow sparks, spending more time near orange flame).

Starting in version 0.2.0, the additive changes that could be applied with vertex colors in the previous version have
been supplemented with multiplicative changes that allow richer and more involved changes to colors. `ColorfulBatch` has
a "tweak" that can be applied to colors somewhat-independently of the existing color that can be set with `setColor()`.
Where `setColor()` changes the additive (or subtractive) luma, chromatic warmth, and chromatic mildness, plus
multiplicative alpha, `setTweak()` changes:
  - the multiplicative luma (a value from 0.0 to 1.0 that maps to a multiplier from 0.0 to 2.0),
  - multiplicative chromatic warmth and chromatic mildness (two separate values from 0.0 to 1.0 that map to multipliers
    from 0.0 to 2.0, but apply to the current warmth or mildness of a pixel as if they are centered on 0.0, not 0.5),
  - and a contrast adjustment (a value from 0.0 to 1.0 that can make mid-range colors more similar when it is low, or
    separate bright colors into brighter colors and dark colors into darker colors when it is high).

Some useful things to use the tweak for include:
  - Setting the chromatic tweaks to 0.0 will make the rendered color grayscale.
    - If the color of a ColorfulBatch isn't gray, then the rendered color will be "green-scale" or some other variation
      of brightness for an existing color.
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
objects using those shaders), predefined packed-float colors in `Palette.java`, and quite a lot of methods for
manipulating those colors as floats in `FloatColors.java`. The newer method involving a tweak requires using a
ColorfulBatch instead of a SpriteBatch, but the API is almost the same, and is in `ColorfulBatch.java`. If you use a
ColorfulBatch, you should also use ColorfulSprite instead of Sprite if you want to set a tweak per-sprite, but even a
standard Sprite will render correctly.

The palette used is a slight adjustment on DawnBringer's Aurora palette, a 256-color palette that gets less attention
than his smaller pixel art palettes, but that has excellent coverage of most colors. The names used for colors in it are
very similar to the ones chosen for the palette as used in [SquidLib](https://github.com/SquidPony/SquidLib), though
not identical; some colors have simpler names, like `Green` instead of `Shamrock Green` or `Yellow` instead of `Lemon`.
Naming 256 colors, some of them very similar, was not easy, and some choices are probably odd.

### HSLC

HSLC doesn't allow changing alpha, so it may be unsuitable for some tasks, but it does allow smooth hue rotations across
the HSL hue range, can saturate or desaturate colors like the two Chroma values can in YCwCmA, and has similar luma
adjustment to YCwCmA as well. Like with YCwCmA, when you tint with all values equal to 0.5f, then the result color
shouldn't change. Raising or lowering hue (stored in the red channel) will rotate the hue away from the input color.
Raising saturation (stored in the green channel) will make the colors more vivid, while decreasing it wll make them
closer to grayscale. Raising lightness (stored in the blue channel) will make colors lighter (it can make them brighter
than the original color if lightness is greater than 0.5), while lowering it will make colors darker. Contrast affects
how rapidly the lightness in input colors changes, so when contrast is high, even slightly different mid-range colors
will have stark lightness differences, while when contrast is low, most lightness will be in the mid-range.

## Samples

These all show YCwCmA changes.

Tinting with gray as the color causes no change to the original image.
![Tinting with gray](https://i.imgur.com/1Nq43hx.png)

Tinting with black as the color makes it much darker, but keeps very colorful areas where they are.
![Tinting with black](https://i.imgur.com/O5oeoWA.png)

Tinting with white as the color makes it much lighter, which isn't possible with the default SpriteBatch shader and color representation.
![Tinting with white](https://i.imgur.com/AiycJSn.png)

It's a common request to be able to make a Sprite or other texture flash red when a character is hurt; you can tint with the predefined color `Palette.FRESH_BLOOD` to tint any image to vivid red (including images that have `0.0f` in their red channel).
![Tinting with red](https://i.imgur.com/gRaLsAL.png)

Tinting with gray, but using a tweak with 0.0 for chromatic warmth and mildness makes the image grayscale.
![Tint with gray, tweak with 0.0 chroma](https://i.imgur.com/hOih4Dr.png)

Tinting with the palette color `WOODLANDS`, but using a tweak with 0.0 for chromatic warmth and mildness makes the image "green-scale."
![Tint with "Woodlands", tweak with 0.0 chroma](https://i.imgur.com/3Mzi8ai.png)

Tinting with gray, but using a tweak with 1.0 for chromatic warmth and mildness makes the image more saturated.
![Tint with gray, tweak with 1.0 chroma](https://i.imgur.com/jlaJ75c.png)

Tinting with gray, but using a tweak with 0.5 for chromatic warmth and 0.0 for chromatic mildness distorts the colors used.
![Tint with gray, tweak with 0.0 chroma](https://i.imgur.com/gxR4k71.png)

Tinting with the Palette color `THISTLE`, but using a tweak with about 0.75 luma and about 0.25 contrast achieves the aforementioned "magic fog" effect.
![Magic Fog](https://i.imgur.com/mUghcVg.png)

## How to Obtain

Using the Maven Central dependency is recommended, and Gradle and Maven can both depend on this library using that repository.

Gradle dependency:
```groovy
implementation 'com.github.tommyettinger:colorful:0.2.2'
```

Gradle dependency if also using GWT to make an HTML application:
```groovy
implementation 'com.github.tommyettinger:colorful:0.2.2:sources'
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
  <version>0.2.2</version>
</dependency>
```

If you don't use Gradle or Maven, [there are jars here](https://github.com/tommyettinger/colorful-gdx/releases/tag/v0.2.2).
