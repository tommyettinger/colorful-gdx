# colorful-gdx
A libGDX mechanism to manipulate colors in powerful ways

Colorful is a small library that mostly offers some predefined shaders and code for handling colors in a different way than normal. Why would you want this? There's several shortcomings of the default SpriteBatch shader's color handling, specifically how it handles color tinting with `setColor(float, float, float, float)`:

  - You can use the default setColor() to make an image darker, but never lighter.
  - The default setColor() can't increase any channel's value; that is, if the red channel is 0, then nothing you do can make red higher.
  - The ways you can adjust tints don't match any kind of aesthetic way of representing color; you're limited to reducing red, green, and blue by some percentage each.

We address this in colorful by representing colors differently. Instead of red, green, blue, and alpha channels, colorful uses luma (also called lightness), chromatic warmth, chromatic mildness, and alpha. The chromatic channels are only meaningful together, and can be used to get the hue and colorfulness (related to saturation) of any individual color. All channels go from `0.0f` to `1.0f` as `float`s, and can also be viewed as `int`s from `0` to `255` (`254` for alpha, because it is never an odd number). For luma, `0.0f` is black regardless of chromatic channels, and `1.0f` is white, again regardless. Tinting an image with black won't actually make the image all black, unlike the default setColor(), but it will make it much darker. Similarly, tinting an image with white will make the image much lighter (unlike the default, where white makes no change). When you want to tint with a neutral color, use `batch.setColor(0.5f, 0.5f, 0.5f, 1.0f)` or `batch.setPackedColor(Palette.GRAY)`; this will make almost no changes to the colors in the textures you draw. For chromatic warmth, `0.0f` is used for colors from green to blue, while `1.0f` is used for colors from yellow to red. For chromatic mildness, `0.0f` is used for colors from blue to red, while `1.0f` is used for colors from green to yellow. When both warmth and mildness are `0.5f`, the color is grayscale. For alpha, it acts exactly like alpha does normally in SpriteBatch. The term for this color representation is YCwCmA, using a similar naming convention to [YcbCr](https://en.wikipedia.org/wiki/YCbCr) or [YCoCg](https://en.wikipedia.org/wiki/YCoCg), both close relatives. The reason this library uses YCwCmA instead of YCoCg is that it is comparable in computational cost to transform to and from RGB, but the luma is somewhat more accurate with YCwCmA, and the warmth axis is very useful for aesthetic reasons. As an example of the aesthetic usage, you could move an image into warm or hot hues when a fire is nearby, or into cooler hues when the weather is freezing. When warmth is very high, it is also nice to be able to move mildness up and down, which makes the color mimic that of fire (going from red embers to yellow sparks, spending more time near orange flame).

In the library, there's shader code in `Shaders.java` (and convenience methods that make using that code directly unnecessary), predefined packed-float colors in `Palette.java`, and quite a lot of methods for manipulating those colors as floats in `FloatColors.java`. The palette used is a slight adjustment on DawnBringer's Aurora palette, a 256-color palette that gets less attention than his smaller pixel art palettes, but that has excellent coverage of most colors.

## Samples

Tinting with gray causes no change to the original image.
![Tinting with gray](https://i.imgur.com/BxOIh4t.png)

Tinting with black makes it much darker, but keeps very colorful areas where they are.
![Tinting with black](https://i.imgur.com/bPLN1bY.png)

Tinting with white makes it much lighter, which isn't possible with the default SpriteBatch shader and color representation.
![Tinting with white](https://i.imgur.com/bKAPAP5.png)

It's a common request to be able to make a Sprite or other texture flash red when a character is hurt; you can tint with the predefined color `Palette.FRESH_BLOOD` to tint any image to vivid red (including images that have `0.0f` in their red channel).
![Tinting with red](https://i.imgur.com/1AuosKF.png)

Using the Maven Central dependency is recommended, and Gradle and Maven can both depend on this library using that repository.

Gradle dependency:
```groovy
implementation 'com.github.tommyettinger:colorful:0.1.0'
```

Gradle dependency if also using GWT to make an HTML application:
```groovy
implementation 'com.github.tommyettinger:colorful:0.1.0:sources'
```
And also for GWT, in your application's `.gwt.xml` file (usually `GdxDefinition.gwt.xml`)
```xml
<inherits name="com.github.tommyettinger.colorful.colorful" />
```

If you don't use Gradle, here's the Maven dependency:
```maven
<dependency>
  <groupId>com.github.tommyettinger</groupId>
  <artifactId>colorful</artifactId>
  <version>0.1.0</version>
</dependency>
```

If you don't use Gradle or Maven, [there are jars here](https://github.com/tommyettinger/colorful-gdx/releases/tag/v0.1.0).
