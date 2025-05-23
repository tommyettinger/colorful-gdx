/*
 * Copyright (c) 2023 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.tommyettinger.colorful;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.github.tommyettinger.colorful.ycwcm.ColorfulBatch;
import com.github.tommyettinger.colorful.ycwcm.ColorfulSprite;

/**
 * Shader code to construct a {@link ShaderProgram} that can render the specialized colors produced by the rest of this
 * library. The shader code is meant for use in a {@link com.badlogic.gdx.graphics.g2d.SpriteBatch}; a convenience
 * method, {@link #makeYCwCmBatch()}, is provided to generate and return a SpriteBatch that uses a correct ShaderProgram.
 * Many of the shaders here are experimental and meant as a basis for user code, rather than a complete solution.
 * If you aren't familiar with shaders in libGDX, <a href="https://github.com/libgdx/libgdx/wiki/Shaders">see this
 * libGDX wiki article</a> for more information.
 * <br>
 * None of the shaders here specify a {@code #version}, and some combinations of OS, driver, and GPU hardware may
 * require the version to be set to a specific value. You may need to put:
 * {@code ShaderProgram.prependVertexCode = "#version 110\n";
 * ShaderProgram.prependFragmentCode = "#version 110\n";} in your launchers, with whatever version you can support, in
 * order to avoid some warnings or errors on some systems.
 * <br>
 * Many of these shaders would need at least some changing to work with kinds of
 * {@link com.badlogic.gdx.graphics.g2d.Batch} that aren't SpriteBatch (or at least very similar). You should compare
 * the names of {@code uniform} variables in the vertex and fragment shader if you use a different kind of Batch; if all
 * of them are the same and there are the same amount, then you should be able to use these shaders without issue.
 * <br>
 * Note that you don't need to specify a shader from here at all if you use a ColorfulBatch from this library!
 * There is a ColorfulBatch in each of the color space subpackages, such as {@code rgb}, {@code oklab}, and {@code ipt}.
 * All of those allow a multiplicative "tweak" to each color channel before an additive change is applied to that
 * channel; to contrast, all the shaders here can only permit one or the other for a given channel. ColorfulBatch may
 * act oddly if it has to interact with SpriteBatch-specific classes like {@link com.badlogic.gdx.graphics.g2d.Sprite},
 * so there are ColorfulSprite classes that also allow setting the "tweak" and additive change per-sprite. Still, it can
 * be a good idea to use the shaders here if you have an existing SpriteBatch, or want to use the various experimental
 * shaders as a test-bed.
 */
public class Shaders {
    /**
     * This is the default vertex shader from libGDX. It is used without change by most of the fragment shaders here.
     */
    public static final String vertexShader = "attribute vec4 " + ShaderProgram.POSITION_ATTRIBUTE + ";\n"
            + "attribute vec4 " + ShaderProgram.COLOR_ATTRIBUTE + ";\n"
            + "attribute vec2 " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n"
            + "uniform mat4 u_projTrans;\n"
            + "varying vec4 v_color;\n"
            + "varying vec2 v_texCoords;\n"
            + "\n"
            + "void main()\n"
            + "{\n"
            + "   v_color = " + ShaderProgram.COLOR_ATTRIBUTE + ";\n"
            + "   v_color.a = v_color.a * (255.0/254.0);\n"
            + "   v_texCoords = " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n"
            + "   gl_Position =  u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n"
            + "}\n";
    /*
attribute vec4 a_position;
attribute vec4 a_color;
attribute vec2 a_texCoord0;
uniform mat4 u_projTrans;
varying vec4 v_color;
varying vec2 v_texCoords;

void main()
{
   v_color = a_color;
   v_color.a = v_color.a * (255.0/254.0);
   v_texCoords = a_texCoord0;
   gl_Position =  u_projTrans * a_position;
}
     */

    /**
     * A simple shader that uses additive blending with "normal" RGBA colors (alpha is still multiplicative).
     * With the default SpriteBatch ShaderProgram, white is the neutral color, 50% gray darkens a color by about 50%,
     * and black darkens a color to black, but nothing can brighten a color. With this, 50% gray is the neutral color,
     * white adds 0.5 to the RGB channels (brightening it and also desaturating it), and black subtracts 0.5 from the
     * RGB channels (darkening and desaturating, but not to black unless the color is already somewhat dark). When
     * tinting with white, this looks like <a href="https://i.imgur.com/yFtgdwz.png">The Mona Lisa on the left</a>, when
     * tinting with 50% gray, it makes no change, and when tinting with black, it almost reaches all black, but some
     * part of the image is still visible.
     * <br>
     * This is probably the fastest shader here, since it does so little math to compute a brighter or darker color. The
     * code here changed from a more complex RGBA shader that used pow(), but didn't look different enough from
     * {@link #fragmentShaderMultiplyRGBA} to warrant having both. Note that the Multiply version can actually reduce an
     * image's colors to all black, but this version cannot.
     * <br>
     * You can generate RGB colors using any of various methods in the {@code rgb} package, such as
     * {@link com.github.tommyettinger.colorful.rgb.ColorTools#rgb(float, float, float, float)}.
     * <br>
     * Meant for use with {@link #vertexShader}.
     */
    public static final String fragmentShaderRGBA =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    "void main()\n" +
                    "{\n" +
                    "   vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "   gl_FragColor = clamp(vec4(tgt.rgb + (v_color.rgb - 0.5), v_color.a * tgt.a), 0.0, 1.0);\n" +
                    "}";

    /**
     * Adjusts RGBA colors so the RGB values are exaggerated towards or away from 0.0 or 1.0, depending on a uniform.
     * This uses a uniform float, 0.0 or greater, called "contrast"; when contrast is 1.0, the image is rendered without
     * changes, but when it is 0.0, everything will be gray, and if it is greater than 1.0, contrast will be stronger.
     * <br>
     * This code was partly written by <a href="https://github.com/SheerSt">SheerSt</a> and partly by tommyettinger,
     * but this is the type of shader code you see in some tutorials, so hopefully it is easy to read.
     * <br>
     * You can generate RGB colors using any of various methods in the {@code rgb} package, such as
     * {@link com.github.tommyettinger.colorful.rgb.ColorTools#rgb(float, float, float, float)}.
     * <br>
     * Meant for use with {@link #vertexShader}. Make sure to set the {@code contrast} uniform before using!
     */
    public static final String fragmentShaderContrastUniform =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    "\n" +
                    "uniform float contrast;\n" +
                    "\n" +
                    "void main()\n" +
                    "{\n" +
                    "    vec4 color = texture2D( u_texture, v_texCoords );\n" +
                    "    color.rgb = clamp((color.rgb - 0.5) * contrast + 0.5, 0.0, 1.0);\n" +
                    "    gl_FragColor = color;\n" +
                    "}";
    /**
     * A simple shader that uses multiplicative blending with "normal" RGBA colors, and is simpler than
     * {@link #fragmentShaderRGBA} but can make changes in color smoother. With the default SpriteBatch ShaderProgram,
     * white is the neutral color, 50% gray darkens a color by about 50%, and black darkens a color to black, but
     * nothing can brighten a color. With this, 50% gray is the neutral color, white multiplies the RGB channels by
     * 2.0 (brightening it and slightly desaturating it), and black multiplies the RGB channels by 0 (reducing the color
     * always to black). When tinting with white, this looks like <a href="https://i.imgur.com/I30jeXv.png">The Mona
     * Lisa on the left</a>; when tinting with 50% gray, it makes no change, and when tinting with black, it produces an
     * all-black image.
     * <br>
     * You can generate RGB colors using any of various methods in the {@code rgb} package, such as
     * {@link com.github.tommyettinger.colorful.rgb.ColorTools#rgb(float, float, float, float)}.
     * <br>
     * Meant for use with {@link #vertexShader}.
     * <br>
     * An earlier version of this attempted to use some useful code by CypherCove in gdx-tween, but the current version
     * doesn't share any code, and doesn't really do any gamma correction either. It does less... gamma... un-correction
     * than {@link #fragmentShaderRGBA}, though, so if the source images are gamma-corrected this should be fine.
     * <br>
     * This was called {@code fragmentShaderGammaRGBA}, but it doesn't do any gamma-correction now. It still seems quite
     * smooth. Where {@link #fragmentShaderRGBA} adds the batch color to the pixel colors, this multiplies them.
     */
    public static final String fragmentShaderMultiplyRGBA =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    "void main()\n" +
                    "{\n" +
                    "   vec4 tgt = texture2D(u_texture, v_texCoords);\n" +
                    "   gl_FragColor = clamp(vec4(tgt.rgb * v_color.rgb * 2.0, v_color.a * tgt.a), 0.0, 1.0);\n" +
                    "}";


    /**
     * A simple shader that uses additive blending with "normal" RGBA colors (alpha is still multiplicative), and
     * increases contrast somewhat, making the lightness change more sharply or harshly.
     * With the default SpriteBatch ShaderProgram, white is the neutral color, 50% gray darkens a color by about 50%,
     * and black darkens a color to black, but nothing can brighten a color. With this, 50% gray is the neutral color,
     * white adds 0.5 to the RGB channels (brightening it and also desaturating it), and black subtracts 0.5 from the
     * RGB channels (darkening and desaturating, but not to black unless the color is already somewhat dark).
     * <br>
     * You can generate RGB colors using any of various methods in the {@code rgb} package, such as
     * {@link com.github.tommyettinger.colorful.rgb.ColorTools#rgb(float, float, float, float)}.
     * <br>
     * Meant for use with {@link #vertexShader}.
     */
    public static final String fragmentShaderHigherContrastRGBA =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    "const float contrast =    1.5   ;\n" +
                    "vec3 barronSpline(vec3 x, float shape) {\n" +
                    "        const float turning = 0.5;\n" +
                    "        vec3 d = turning - x;\n" +
                    "        return mix(\n" +
                    "          ((1. - turning) * (x - 1.)) / (1. - (x + shape * d)) + 1.,\n" +
                    "          (turning * x) / (1.0e-3 + (x + shape * d)),\n" +
                    "          step(0.0, d));\n" +
                    "}\n" +
                    "void main()\n" +
                    "{\n" +
                    "  vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "  tgt.rgb = barronSpline(clamp(tgt.rgb + v_color.rgb - 0.5, 0.0, 1.0), contrast);\n" +
                    "  tgt.a *= v_color.a;\n" +
                    "  gl_FragColor = tgt;\n" +
                    "}";

    /**
     * A simple shader that uses additive blending with "normal" RGBA colors (alpha is still multiplicative), and
     * reduces contrast somewhat, making the lightness more murky and uniform.
     * With the default SpriteBatch ShaderProgram, white is the neutral color, 50% gray darkens a color by about 50%,
     * and black darkens a color to black, but nothing can brighten a color. With this, 50% gray is the neutral color,
     * white adds 0.5 to the RGB channels (brightening it and also desaturating it), and black subtracts 0.5 from the
     * RGB channels (darkening and desaturating, but not to black unless the color is already somewhat dark).
     * <br>
     * You can generate RGB colors using any of various methods in the {@code rgb} package, such as
     * {@link com.github.tommyettinger.colorful.rgb.ColorTools#rgb(float, float, float, float)}.
     * <br>
     * Meant for use with {@link #vertexShader}.
     */
    public static final String fragmentShaderLowerContrastRGBA = fragmentShaderHigherContrastRGBA.replace("   1.5   ", "0.5");

    //// save the result as shader, and set it on your batch with
    // ShaderProgram shader = makeRGBAShader();
    // batch.setShader(shader)
    /**
     * A simple helper method that builds the simplest shader here; this shader allows tinting with light colors to
     * lighten an image. You can assign the result to a SpriteBatch with its
     * {@link SpriteBatch#setShader(ShaderProgram)} method.
     * <br>
     * You can generate RGB colors using any of various methods in the {@code rgb} package, such as
     * {@link com.github.tommyettinger.colorful.rgb.ColorTools#rgb(float, float, float, float)}.
     * @return a ShaderProgram that uses the RGBA shader {@link #fragmentShaderRGBA}
     */
    public static ShaderProgram makeRGBAShader()
    {
        ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShaderRGBA);
        if(!shader.isCompiled())
            throw new GdxRuntimeException("Couldn't compile shader: " + shader.getLog());
        return shader;
    }
    /**
     * A simple helper method that builds the simplest shader here; this shader allows tinting with light colors to
     * lighten an image, and also smooths changes well. You can assign the result to a SpriteBatch with its
     * {@link SpriteBatch#setShader(ShaderProgram)} method.
     * <br>
     * You can generate RGB colors using any of various methods in the {@code rgb} package, such as
     * {@link com.github.tommyettinger.colorful.rgb.ColorTools#rgb(float, float, float, float)}.
     * <br>
     * This was called {@code makeGammaRGBAShader}, but because it doesn't currently do any gamma correction, naming it
     * {@code makeMultiplyRGBAShader} seems more appropriate. {@link #makeRGBAShader()} is the additive counterpart.
     * @return a ShaderProgram that uses the RGBA shader {@link #fragmentShaderMultiplyRGBA}
     */
    public static ShaderProgram makeMultiplyRGBAShader()
    {
        ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShaderMultiplyRGBA);
        if(!shader.isCompiled())
            throw new GdxRuntimeException("Couldn't compile shader: " + shader.getLog());
        return shader;
    }
    /**
     * Prepares and returns a new SpriteBatch that uses {@link #vertexShader} and {@link #fragmentShaderHigherContrastRGBA}
     * from this class, making it able to render RGBA colors from the libGDX or the rgb package. This also takes a
     * {@code contrast} parameter, which modifies the contrast in the higher-contrast fragment shader; if greater than
     * 1.0 it will make light colors lighter and dark colors darker, while if it is less than 1.0 it will make all but
     * the darkest colors closer to the upper-middle-range of lightness. If you want to adjust contrast per-sprite, use
     * a {@link com.github.tommyettinger.colorful.rgb.ColorfulBatch} (those can adjust colors in more ways); you can
     * simply use {@code new ColorfulBatch()} to make one of those. Note that a SpriteBatch like this produces
     * won't be able to render a {@link com.github.tommyettinger.colorful.rgb.ColorfulSprite}, but ColorfulBatch can.
     * ColorfulBatch also will calculate contrast differently from the shader this uses. It also takes a contrast in its
     * tweak value that is limited to a 0.0 to 1.0 range, rather than 0.0 on up here. One potential use for this method
     * is to "deep-fry" a scene's image by using very high contrast (5.0 or higher).
     * <br>
     * You can generate RGB colors using any of various methods in the {@code rgb} package, such as
     * {@link com.github.tommyettinger.colorful.rgb.ColorTools#rgb(float, float, float, float)}.
     * @param contrast how much contrast should be emphasized; higher than 1.0 is more contrasting, and this should usually be between 0.1 and 5.0
     * @return a freshly allocated SpriteBatch that will also have a new ShaderProgram for rendering RGB with contrast
     */
    public static SpriteBatch makeRGBABatch(final float contrast)
    {
        ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShaderHigherContrastRGBA.replace("   1.5   ", Float.toString(Math.max(contrast, 0.0f))));
        if(!shader.isCompiled())
            throw new GdxRuntimeException("Couldn't compile shader: " + shader.getLog());
        return new SpriteBatch(1000, shader);
    }

    /**
     * Where the magic happens; this converts a batch color from the YCwCm format (used by colorful's ycwcm package) to
     * RGBA. The vertex color will be split up into 4 channels just as a normal shader does, but the channels here are
     * luma, chromatic warmth, chromatic mildness, and alpha; alpha acts just like a typical RGBA shader, but the others
     * are additive instead of multiplicative, with 0.5 as a neutral value. This does not support the "tweak" features
     * that {@link ColorfulBatch} does, which include multiplicative counterparts to the additive operations this
     * supports on luma, chromatic warmth, and chromatic mildness, plus a contrast adjustment. If you want to adjust
     * contrast globally, you can use {@link #makeYCwCmBatch(float)} to set the contrast for a new Batch with a new shader.
     * <br>
     * You can generate YCwCm colors using any of various methods in the {@code ycwcm} package, such as
     * {@link com.github.tommyettinger.colorful.ycwcm.ColorTools#ycwcm(float, float, float, float)}.
     * <br>
     * Meant for use with {@link #vertexShader}.
     * <br>
     * This was called {@code fragmentShader}, a name which dates back to when this was the only one in this file. It is
     * not the only one anymore.
     */
    public static final String fragmentShaderYCwCm =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    "const vec3 bright = vec3(0.375, 0.5, 0.125);\n" +
                    "void main()\n" +
                    "{\n" +
                    "   vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "////use the following line to match the color exactly\n" +
                    "   vec3 ycc = vec3(v_color.r - 0.5 + dot(tgt.rgb, bright), ((v_color.g - 0.5) * 2.0 + tgt.r - tgt.b), ((v_color.b - 0.5) * 2.0 + tgt.g - tgt.b));\n" +
                    "////use the following line to increase contrast\n" +
                    "//   vec3 ycc = vec3(v_color.r * dot(sin(tgt.rgb * 1.5707963267948966) * sqrt(tgt.rgb), bright), ((v_color.g - 0.5) * 2.0 + tgt.r - tgt.b), ((v_color.b - 0.5) * 2.0 + tgt.g - tgt.b));\n" +
                    "////use the following line to increase contrast more\n" +
                    "//   vec3 ycc = vec3(v_color.r * pow(dot(tgt.rgb, bright), 1.25), ((v_color.g - 0.5) * 2.0 + tgt.r - tgt.b), ((v_color.b - 0.5) * 2.0 + tgt.g - tgt.b));\n" +
                    //uses a specific matrix (related to bright, above) multiplied with ycc to get back to rgb.
                    "   gl_FragColor = vec4( (clamp(mat3(1.0, 1.0, 1.0, 0.625, -0.375, -0.375, -0.5, 0.5, -0.5) * ycc, 0.0, 1.0)), v_color.a * tgt.a);\n" +
                    "}";
    /**
     * A variant on {@link #fragmentShaderYCwCm} that adjusts luma to make mid-range colors darker, while keeping light
     * colors light. This is not the same as {@link ColorfulBatch} even when the contrast for ColorfulBatch's tweak is
     * the same as what this uses (1.375 here, which is roughly 0.875f in a tweak value). ColorfulBatch does some work
     * in the vertex shader so it may be a little faster than this approach, and it seems to have less severe effects on
     * the overall brightness of an image that has adjusted contrast.
     * <br>
     * You can generate YCwCm colors using any of various methods in the {@code ycwcm} package, such as
     * {@link com.github.tommyettinger.colorful.ycwcm.ColorTools#ycwcm(float, float, float, float)}.
     * <br>
     * Meant for use with {@link #vertexShader}.
     */
    public static final String fragmentShaderHigherContrast =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    "const float contrast =    1.375   ; // You can make contrast a uniform if you want.\n" +
                    "const vec3 bright = vec3(0.375, 0.5, 0.125) * (4.0 / 3.0);\n" +
                    "void main()\n" +
                    "{\n" +
                    "   vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "   vec3 ycc = vec3(v_color.r - 0.5 + pow(dot(tgt.rgb, bright), contrast) * 0.75, ((v_color.g - 0.5) * 2.0 + tgt.r - tgt.b), ((v_color.b - 0.5) * 2.0 + tgt.g - tgt.b));\n" +
                    "   gl_FragColor = vec4( (clamp(mat3(1.0, 1.0, 1.0, 0.625, -0.375, -0.375, -0.5, 0.5, -0.5) * ycc, 0.0, 1.0)), v_color.a * tgt.a);\n" +
                    "}";

    /**
     * An alternative shader that effectively reduces luma contrast, bringing all but the darkest colors to the
     * upper-mid luma range. This is not the same as {@link ColorfulBatch} even when the contrast for ColorfulBatch's
     * tweak is the same as what this uses (0.625 here, which is roughly 0.125f in a tweak value). ColorfulBatch does
     * some work in the vertex shader so it may be a little faster than this approach, and it seems to have less severe
     * effects on the overall brightness of an image that has adjusted contrast.
     * <br>
     * You can generate YCwCm colors using any of various methods in the {@code ycwcm} package, such as
     * {@link com.github.tommyettinger.colorful.ycwcm.ColorTools#ycwcm(float, float, float, float)}.
     * <br>
     * Meant for use with {@link #vertexShader}.
     */
    public static final String fragmentShaderLowerContrast = fragmentShaderHigherContrast.replace("   1.375   ", "0.625");

    /**
     * Prepares and returns a new SpriteBatch that uses the default {@link #vertexShader} and {@link #fragmentShaderYCwCm}
     * from this class, making it able to render YCwCm colors from the ycwcm package. It won't be a
     * {@link ColorfulBatch} (those can adjust colors in more ways); you can simply use {@code new ColorfulBatch()} to
     * make one of those. Note that a SpriteBatch like this produces won't be able to render a {@link ColorfulSprite},
     * but ColorfulBatch can.
     * <br>
     * You can generate YCwCm colors using any of various methods in the {@code ycwcm} package, such as
     * {@link com.github.tommyettinger.colorful.ycwcm.ColorTools#ycwcm(float, float, float, float)}.
     * @return a freshly allocated SpriteBatch that will also have a new ShaderProgram for rendering YCwCm
     */
    public static SpriteBatch makeYCwCmBatch()
    {
        ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShaderYCwCm);
        if(!shader.isCompiled())
            throw new GdxRuntimeException("Couldn't compile shader: " + shader.getLog());
        return new SpriteBatch(1000, shader);
    }

    /**
     * Prepares and returns a new SpriteBatch that uses the default {@link #vertexShader} and {@link #fragmentShaderYCwCm}
     * from this class, making it able to render YCwCm colors from the ycwcm package. This also takes a
     * {@code contrast} parameter; if greater than 1.0 it will make light colors lighter and dark colors darker, while
     * if it is less than 1.0 it will make all but the darkest colors closer to the upper-middle-range of lightness.
     * If you want to adjust contrast per-sprite, use a {@link ColorfulBatch} (those can adjust colors in more ways);
     * you can simply use {@code new ColorfulBatch()} to make one of those. Note that a SpriteBatch like this produces
     * won't be able to render a {@link ColorfulSprite}, but ColorfulBatch can. ColorfulBatch also will calculate
     * contrast differently from the shader this uses, including doing some work in the vertex shader (which may be
     * faster). It also takes a contrast in its tweak value that is limited to a 0.0 to 1.0 range, rather than 0.1 to
     * 2.0 here (this can technically tolerate 0.01 to 10.0, but those extremes aren't recommended).
     * <br>
     * You can generate YCwCm colors using any of various methods in the {@code ycwcm} package, such as
     * {@link com.github.tommyettinger.colorful.ycwcm.ColorTools#ycwcm(float, float, float, float)}.
     * @param contrast how much contrast should be emphasized; higher than 1.0 is more contrasting, and this should usually be between 0.1 and 2.0
     * @return a freshly allocated SpriteBatch that will also have a new ShaderProgram for rendering YCwCm
     */
    public static SpriteBatch makeYCwCmBatch(final float contrast)
    {
        ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShaderHigherContrast.replace("   1.375   ", Float.toString(Math.min(Math.max(contrast, 0.01f), 10.0f))));
        if(!shader.isCompiled())
            throw new GdxRuntimeException("Couldn't compile shader: " + shader.getLog());
        return new SpriteBatch(1000, shader);
    }

    /**
     * Prepares and returns a new SpriteBatch that uses {@link #vertexShaderHSLC} and {@link #fragmentShaderHSLC}
     * from this class, making it interpret the SpriteBatch's color, as set by 
     * {@link SpriteBatch#setColor(float, float, float, float)}, to be hue rotation, saturation change, lightness
     * change, and contrast change. All of these are neutral when their value is 0.5f. Hue rotates clockwise or
     * counterclockwise when it goes toward 0.0, and the other way when it goes toward 1.0; 0.0 and 1.0 both refer to
     * the 180 degree rotation, but I don't know which goes which way (it also depends on how you visualize hue).
     * Saturation becomes more grayscale as it goes towards 0.0, and more vivid as it goes towards 1.0. Lightness gets
     * darker towards 0.0, lighter towards 1.0 (any lightness above 0.5 will brighten the image, unlike the default
     * shader and batch color). Contrast affects changes in lightness; low contrast makes all lightness closer to the
     * mid-range, while high contrast makes even small changes in the mid-range of an image's color have stark lightness
     * changes in the result. Note, this does not support changing an image's alpha with the batch color.
     * <br>
     * You can generate HSLC colors using {@link FloatColors#rgb2hsl(float, float, float, float)}, using the last
     * parameter to store contrast.
     * @return a freshly allocated SpriteBatch that will also have a new ShaderProgram for rendering HSLC
     */
    public static SpriteBatch makeBatchHSLC()
    {
        ShaderProgram shader = new ShaderProgram(vertexShaderHSLC, fragmentShaderHSLC);
        if(!shader.isCompiled())
            throw new GdxRuntimeException("Couldn't compile shader: " + shader.getLog());
        return new SpriteBatch(1000, shader);
    }

    /**
     * Similar to {@link #fragmentShaderYCwCm}, but this uses the very perceptually-accurate IPT color space as described by
     * Ebner and Fairchild, instead of the custom YCwCm color space. IPT doesn't really need that much more computation
     * to be done by the shader, but tends to make gradual changes in color much smoother. If comparing to YCwCm, then
     * Y (luma) is like I (intensity) here, so when a Batch color has 0 for I (stored in the r channel), it makes the
     * image very dark, while if the Batch color has 1 for I, it makes the image very light. Cw and Cm can be graphed
     * like a color wheel, and in their case, the 4 corners of a square graph are red, yellow, green, and blue. IPT is
     * less geometrical, and the corners are roughly purple, orange, green, and cyan, while the centers of the edges of
     * the square are close to red, yellow, green, and blue. <a href="https://i.imgur.com/A3n4qmM.png">See this capture
     * of the IPT space</a> if you want a better picture. The P (short for protan, a term from ophthalmology) channel
     * (stored in g) is the left-to-right axis there, with P==0 making colors green or blue (cooler), and P==1 making
     * colors orange, red, or purple (somewhat warmer). The T (short for tritan, also from ophthalmology) channel
     * (stored in b) is the down-to-up axis there, with T==0 making colors more blue or purple, and T==1 making colors
     * more green, yellow, brown, or orange. Where protan can be viewed as going from cool to warm as its value
     * increases, tritan can be viewed as going from artificial to natural, or perhaps fluid to solid. Alpha is treated
     * exactly as it is in the standard libGDX fragment shader, with the alpha in the batch color multiplied by the
     * alpha in the image pixel to get the result alpha.
     * <br>
     * You can generate IPT colors using any of various methods in the {@code ipt} package, such as
     * {@link com.github.tommyettinger.colorful.ipt.ColorTools#ipt(float, float, float, float)}. Note that IPT is
     * intended to share an API with the {@code ipt_hq} package, but IPT_HQ is usually preferred.
     * <br>
     * Meant for use with {@link #vertexShader}.
     */
    public static String fragmentShaderIPT =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    "void main()\n" +
                    "{\n" +
                    "    vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "    vec4 adj = v_color;\n" +
                    "    adj.yz = adj.yz * 2.0 - 0.5;\n" +
                    "    vec3 ipt = (mat3(0.189786, 0.669665 , 0.286498, 0.576951, -0.73741 , 0.655205, 0.233221, 0.0681367, -0.941748)\n" +
                    "         * (tgt.rgb)) + adj.xyz - 0.5;\n" +
                    "    ipt.x = clamp(ipt.x, 0.0, 1.0);\n" +
                    "    ipt.yz = clamp(ipt.yz, -1.0, 1.0);\n" +
                    "    vec3 back = mat3(0.999779, 1.00015, 0.999769, 1.07094, -0.377744, 0.0629496, 0.324891, 0.220439, -0.809638) * ipt;\n" +
                    "    gl_FragColor = vec4(clamp(back, 0.0, 1.0), adj.a * tgt.a);\n" +
                    "}";
    /**
     * Just like {@link #fragmentShaderIPT}, but gamma-corrects the input and output RGB values (which improves
     * lightness uniformity) and uses an exponential step internally to change how colors are distributed within the
     * gamut. These steps are more computationally expensive than the bare-bones ones in {@link #fragmentShaderIPT}, but
     * they seem to improve some aspects of color transitions quite a bit.
     * <br>
     * You can generate IPT_HQ colors using any of various methods in the {@code ipt_hq} package, such as
     * {@link com.github.tommyettinger.colorful.ipt_hq.ColorTools#ipt(float, float, float, float)}. Note that IPT_HQ is
     * intended to share an API with the {@code ipt} package, but IPT_HQ is usually preferred.
     * <br>
     * Meant for use with {@link #vertexShader}.
     */
    public static String fragmentShaderIPT_HQ =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    "const vec3 forward = vec3(0.43);\n" +
                    "const vec3 reverse = vec3(1.0 / 0.43);\n" +
                    "void main()\n" +
                    "{\n" +
                    "  vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "  vec3 ipt = mat3(0.40000, 4.45500, 0.80560, 0.40000, -4.8510, 0.35720, 0.20000, 0.39600, -1.1628) *" +
                    "             pow(mat3(0.313921, 0.151693, 0.017753, 0.639468, 0.748209, 0.109468, 0.0465970, 0.1000044, 0.8729690) \n" +
                    "             * (tgt.rgb * tgt.rgb), forward);\n" +
                    "  ipt.x = clamp(ipt.x + v_color.x - 0.55, 0.0, 1.0);\n" +
                    "  ipt.yz = clamp(ipt.yz + v_color.yz * 2.0 - 1.0, -1.0, 1.0);\n" +
                    "  ipt = mat3(1.0, 1.0, 1.0, 0.097569, -0.11388, 0.032615, 0.205226, 0.133217, -0.67689) * ipt;\n" +
                    "  gl_FragColor = vec4(sqrt(clamp(" +
                    "                 mat3(5.432622, -1.10517, 0.028104, -4.67910, 2.311198, -0.19466, 0.246257, -0.20588, 1.166325) *\n" +
                    "                 (sign(ipt) * pow(abs(ipt), reverse))," +
                    "                 0.0, 1.0)), v_color.a * tgt.a);\n" +
                    "}";

    /**
     * Just like {@link #fragmentShaderIPT_HQ}, but uses the Oklab color space instead of the very similar IPT_HQ one.
     * This also gamma-corrects the inputs and outputs, though it uses subtly different math internally. Oklab colors
     * tend to have more variation on their L channel, which represents lightness, than their A or B channels, which
     * represent green-to-red and blue-to-yellow chromatic axes; indeed, A and B tend to be no more than about 1/6 away
     * from their middle point at 1/2, which is used for grayscale. This is normal for Oklab, and allows colors to be
     * compared for approximate difference using Euclidean distance. Importantly, Oklab preserves the meaning of its L
     * channel (lightness) very well when comparing two arbitrary colors, while also doing well when comparing chroma
     * (see {@link com.github.tommyettinger.colorful.oklab.ColorTools#chroma(float)}).
     * <br>
     * You can generate Oklab colors using any of various methods in the {@code oklab} package, such as
     * {@link com.github.tommyettinger.colorful.oklab.ColorTools#oklab(float, float, float, float)}.
     * <br>
     * Meant for use with {@link #vertexShader}.
     */
    public static String fragmentShaderOklab =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    "const vec3 forward = vec3(1.0 / 3.0);\n" +
                    "float toOklab(float L) {\n" +
                    "  return pow(L, 1.5);\n" +
                    "}\n" +
                    "float fromOklab(float L) {\n" +
                    "  return pow(L, 0.666666);\n" +
                    "}\n" +
                    "void main()\n" +
                    "{\n" +
                    "  vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "  vec3 lab = mat3(+0.2104542553, +1.9779984951, +0.0259040371, +0.7936177850, -2.4285922050, +0.7827717662, -0.0040720468, +0.4505937099, -0.8086757660) *" +
                    "             pow(mat3(0.4121656120, 0.2118591070, 0.0883097947, 0.5362752080, 0.6807189584, 0.2818474174, 0.0514575653, 0.1074065790, 0.6302613616) \n" +
                    "             * (tgt.rgb * tgt.rgb), forward);\n" +
                    "  lab.x = fromOklab(clamp(toOklab(lab.x) + v_color.r - 0.5, 0.0, 1.0));\n" +
                    "  lab.yz = clamp(lab.yz + v_color.gb * 2.0 - 1.0, -1.0, 1.0);\n" +
                    "  lab = mat3(1.0, 1.0, 1.0, +0.3963377774, -0.1055613458, -0.0894841775, +0.2158037573, -0.0638541728, -1.2914855480) * lab;\n" +
                    "  gl_FragColor = vec4(sqrt(clamp(" +
                    "                 mat3(+4.0767245293, -1.2681437731, -0.0041119885, -3.3072168827, +2.6093323231, -0.7034763098, +0.2307590544, -0.3411344290, +1.7068625689) *\n" +
                    "                 (lab * lab * lab)," +
                    "                 0.0, 1.0)), v_color.a * tgt.a);\n" +
                    "}";

    /**
     * A vertex shader that does the bulk of processing HSI-format batch colors and converting them to a format
     * {@link #fragmentShaderHSI} can use. Since HSI is only a cylindrical/spherical adaptation of IPT, with identical
     * I components and the combination of H and S in polar coordinates mapping to P and T in Cartesian coordinates,
     * the fragment shader this is used with can be any that expects an IPT color (currently only
     * {@link #fragmentShaderIPT} does this, and it's the same as {@link #fragmentShaderHSI}). This vertex shader does
     * a lot more than most vertex shaders here, but since it is executed relatively rarely (unless you're drawing many
     * 1-pixel textures), there shouldn't be a heavy performance penalty.
     * <br>
     * There is no special code to generate HSI colors; you can use
     * {@link com.github.tommyettinger.colorful.ipt.ColorTools#ipt(float, float, float, float)}, or any ColorTools
     * method that generates a packed float color directly from the channel values, to specify hue, saturation,
     * intensity, and alpha in that order. You can also just specify the batch color directly with
     * {@link SpriteBatch#setColor(float, float, float, float)}, taking the channels in the same order as above.
     * <br>
     * EXPERIMENTAL. Meant more for reading and editing than serious usage.
     */
    public static final String vertexShaderHSI = "attribute vec4 " + ShaderProgram.POSITION_ATTRIBUTE + ";\n"
        + "attribute vec4 " + ShaderProgram.COLOR_ATTRIBUTE + ";\n"
        + "attribute vec2 " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n"
        + "uniform mat4 u_projTrans;\n"
        + "varying vec4 v_color;\n"
        + "varying vec2 v_texCoords;\n"
        + "const vec3 yellow  = vec3( 0.16155326,0.020876605,-0.26078433 );\n"
        + "const vec3 magenta = vec3(-0.16136102,0.122068435,-0.070396   );\n"
        + "const vec3 cyan    = vec3( 0.16420607,0.3481738,   0.104959644);\n"
        + "void main()\n"
        + "{\n"
        + "    v_color = " + ShaderProgram.COLOR_ATTRIBUTE + ";\n"
        + "    v_color.a = " + ShaderProgram.COLOR_ATTRIBUTE + ".a * (255.0/254.0);\n"
        + "    vec3 hsi = v_color.rgb;\n"
        + "    v_color.x = (hsi.z - 0.5) * 0.9999;\n"
        + "    hsi.x *= 6.28318;\n"
        + "    hsi.y *= 0.5;\n"
        + "    v_color.y = cos(hsi.x) * hsi.y;\n"
        + "    v_color.z = sin(hsi.x) * hsi.y;\n"
        + "    float crMid = dot(cyan.yz, v_color.yz);\n"
        + "    float mgMid = dot(magenta.yz, v_color.yz);\n"
        + "    float ybMid = dot(yellow.yz, v_color.yz);\n"
        + "    float crScale = (v_color.x - 0.5 + step(crMid, 0.0)) * cyan.x / (0.00001 - crMid);\n"
        + "    float mgScale = (v_color.x + 0.5 - step(mgMid, 0.0)) * magenta.x / (0.00001 - mgMid);\n"
        + "    float ybScale = (v_color.x - 0.5 + step(ybMid, 0.0)) * yellow.x / (0.00001 - ybMid);\n"
        + "    float scale = 4.0 * min(crScale, min(mgScale, ybScale));\n"
        + "    v_color.yz *= scale * length(v_color.yz) / cos(3.14159 * v_color.x);\n"
        + "    v_color.xyz += 0.5;\n"
        + "    v_texCoords = " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n"
        + "    gl_Position =  u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n"
        + "}\n";
    /**
     * This is an alias for {@link #fragmentShaderIPT}. If used with {@link #vertexShaderHSI}, you can specify a batch
     * color using an HSL-like system.
     * <br>
     * There is no special code to generate HSI colors; you can use
     * {@link com.github.tommyettinger.colorful.ipt.ColorTools#ipt(float, float, float, float)}, or any ColorTools
     * method that generates a packed float color directly from the channel values, to specify hue, saturation,
     * intensity, and alpha in that order. You can also just specify the batch color directly with
     * {@link SpriteBatch#setColor(float, float, float, float)}, taking the channels in the same order as above.
     * <br>
     * Meant for use with {@link #vertexShaderHSI}.
     */
    public static String fragmentShaderHSI = fragmentShaderIPT;

    /**
     * Not a full shader, this is a snippet used by most of the other HSL-based shaders to implement the complex
     * rgb2hsl() and hsl2rgb() methods. There are also comments in the code snippet that you can use if you want to
     * change the distribution of colors across the color wheel.
     * <br>
     * <a href="https://gamedev.stackexchange.com/a/59808">Credit to Sam Hocevar</a>.
     * <br>
     * EXPERIMENTAL. Meant more for reading and editing than serious usage.
     */
    public static final String partialCodeHSL =
                    "const float eps = 1.0e-3;\n" +
                    "vec4 rgb2hsl(vec4 c)\n" +
                    "{\n" +
                    "    const vec4 J = vec4(0.0, -1.0 / 3.0, 2.0 / 3.0, -1.0);\n" +
                    "    vec4 p = mix(vec4(c.bg, J.wz), vec4(c.gb, J.xy), step(c.b, c.g));\n" +
                    "    vec4 q = mix(vec4(p.xyw, c.r), vec4(c.r, p.yzx), step(p.x, c.r));\n" +
                    "    float d = q.x - min(q.w, q.y);\n" +
                    "    float l = q.x * (1.0 - 0.5 * d / (q.x + eps));\n" +
                    "    return vec4(abs(q.z + (q.w - q.y) / (6.0 * d + eps)), (q.x - l) / (min(l, 1.0 - l) + eps), l, c.a);\n" +
                    "}\n" +
                    "\n" +
                    "vec4 hsl2rgb(vec4 c)\n" +
                    "{\n" +
                    "    const vec4 K = vec4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);\n" +
                    "    vec3 p = abs(fract(c.x + K.xyz) * 6.0 - K.www);\n" +
                    "    float v = (c.z + c.y * min(c.z, 1.0 - c.z));\n" +
                    "    return vec4(v * mix(K.xxx, clamp(p - K.xxx, 0.0, 1.0), 2.0 * (1.0 - c.z / (v + eps))), c.w);\n" +
                    "}" +
                            "////Call this to go from the official HSL hue distribution (where blue is opposite yellow) to a\n" +
                            "////different distribution that matches primary colors in painting (where purple is opposite yellow).\n" +
                            "//float official2primaries(float hue) {\n" +
                            "//    return  hue * (  2.137\n" +
                            "//          + hue * (  0.542\n" +
                            "//          + hue * (-15.141\n" +
                            "//          + hue * ( 30.120\n" +
                            "//          + hue * (-22.541\n" +
                            "//          + hue *   5.883)))));\n" +
                            "//}\n" +
                            "////Call this to go to the official HSL hue distribution (where blue is opposite yellow) from a\n" +
                            "////different distribution that matches primary colors in painting (where purple is opposite yellow).\n" +
                            "//float primaries2official(float hue) {\n" +
                            "//    return  hue * (  0.677\n" +
                            "//          + hue * ( -0.123\n" +
                            "//          + hue * (-11.302\n" +
                            "//          + hue * ( 46.767\n" +
                            "//          + hue * (-58.493\n" +
                            "//          + hue *   23.474)))));\n" +
                            "//}\n"
            ;

    /**
     * Adjusted-hue version of {@link #partialCodeHSL}, supplying HSL to-and-from RGB conversions with a smaller range
     * of hue used for cyan and a larger range for orange. Not currently used. This is pretty much only meant so people
     * reading the source code and trying different variations on HSL can see some of the attempts I made.
     * <br>
     * Credit to Sam Hocevar, https://gamedev.stackexchange.com/a/59808 .
     * <br>
     * EXPERIMENTAL. Meant more for reading and editing than serious usage.
     */
    public static final String partialCodeHSLStretched =
            "const float eps = 1.0e-3;\n" +
                    //// maybe not blue enough?
//                    //Call this to go from the official HSL hue distribution (where blue is opposite yellow) to a
//                    //different distribution that matches primary colors in painting (where purple is opposite yellow).
//                    "float official2primaries(float hue) {\n" +
//                    "    return  hue * (  1.630\n" +
//                    "          + hue * (  5.937\n" +
//                    "          + hue * (-38.485\n" +
//                    "          + hue * ( 76.101\n" +
//                    "          + hue * (-63.977\n" +
//                    "          + hue *   19.794)))));\n" +
//                    "}\n" +
//                    //Call this to go to the official HSL hue distribution (where blue is opposite yellow) from a
//                    //different distribution that matches primary colors in painting (where purple is opposite yellow).
//                    "float primaries2official(float hue) {\n" +
//                    "    return  hue * (  1.463\n" +
//                    "          + hue * ( -9.834\n" +
//                    "          + hue * ( 31.153\n" +
//                    "          + hue * (-35.066\n" +
//                    "          + hue *   13.284))));\n" +
//                    "}\n" +
                    //// still weirdly high orange
//                    "float official2primaries(float hue) {\n" +
//                    "    return  hue * (  2.634\n" +
//                    "          + hue * ( -4.703\n" +
//                    "          + hue * (  3.659\n" +
//                    "          + hue * (  0.829\n" +
//                    "          + hue *   -1.419))));\n" +
//                    "}\n" +
//                    "float primaries2official(float hue) {\n" +
//                    "    return  hue * (  1.163\n" +
//                    "          + hue * ( -7.102\n" +
//                    "          + hue * ( 22.709\n" +
//                    "          + hue * (-25.502\n" +
//                    "          + hue *    9.732))));\n" +
//                    "}\n" +
//                    "const float ROOT   = 16.0;\n" +
//                    "const float SQUARE = ROOT * ROOT;\n" +
                    "float official2primaries(float hue) {\n" +
//                    "    return (sqrt(hue * 0.9375 + 0.0625) - 0.25) * 1.333;\n" +
//                    "    return asin(sqrt(hue * SQUARE) * (1.0 / ROOT)) * (2.0 * 3.14159274);\n" +

//                    "    return asin((sqrt(hue * 0.9375 + 0.0625) - 0.25) * 2.666 - 1.0) * 0.318309886 + 0.5;\n" +

//                    "    return sqrt(sin((hue - 0.5) * 3.14159274) * 0.5 + 0.5);\n" +
//                    "    return pow(hue, 0.5625);\n" +
//                    "    return sqrt(hue);\n" +
                    "    return (sqrt(hue + 0.050625) - 0.225) * 1.25;\n" +
                    "}\n" +
                    "float primaries2official(float hue) {\n" +
//                    "    hue = sin((hue) * (0.5 * 3.14159274));\n" +
//                    "    hue = (hue) * 0.75 + 0.25;\n" +
                    
//                    "    hue = sin((hue - 0.5) * 3.14159274);\n" +
//                    "    hue = (hue + 1.0) * 0.375 + 0.25;\n" +
//                    "    return (hue * hue - 0.0625) * (1.0 / 0.9375);\n" +
                    
//                    "    return asin(hue * hue * 2.0 - 1.0) * 0.318309886 + 0.5;\n" +
//                    "    return pow(hue, 1.77777);\n" +
                    "    return pow(hue * 0.8 + 0.225, 2.0) - 0.050625;\n" +
//                    "    return hue * hue;\n" +

//                    "    hue = sin((hue) * (0.5 * 3.14159274));\n" +
//                    "    hue = hue * ROOT;\n" +
//                    "    return (hue * hue) * (1.0 / SQUARE);\n" +

                    "}\n" +
                    //// way too much orange?
//                    //Call this to go from the official HSL hue distribution (where blue is opposite yellow) to a
//                    //different distribution that matches primary colors in painting (where purple is opposite yellow).
//                    "float official2primaries(float hue) {\n" +
//                    "    return  hue * (  2.137\n" +
//                    "          + hue * (  0.542\n" +
//                    "          + hue * (-15.141\n" +
//                    "          + hue * ( 30.120\n" +
//                    "          + hue * (-22.541\n" +
//                    "          + hue *    5.883)))));\n" +
//                    "}\n" +
//                    //Call this to go to the official HSL hue distribution (where blue is opposite yellow) from a
//                    //different distribution that matches primary colors in painting (where purple is opposite yellow).
//                    "float primaries2official(float hue) {\n" +
//                    "    return  hue * (  0.677\n" +
//                    "          + hue * ( -0.123\n" +
//                    "          + hue * (-11.302\n" +
//                    "          + hue * ( 46.767\n" +
//                    "          + hue * (-58.493\n" +
//                    "          + hue *   23.474)))));\n" +
//                    "}\n" +
                    "vec4 rgb2hsl(vec4 c)\n" +
                    "{\n" +
                    "    const vec4 J = vec4(0.0, -1.0 / 3.0, 2.0 / 3.0, -1.0);\n" +
                    "    vec4 p = mix(vec4(c.bg, J.wz), vec4(c.gb, J.xy), step(c.b, c.g));\n" +
                    "    vec4 q = mix(vec4(p.xyw, c.r), vec4(c.r, p.yzx), step(p.x, c.r));\n" +
                    "\n" +
                    "    float d = q.x - min(q.w, q.y);\n" +
                    "    float l = q.x * (1.0 - 0.5 * d / (q.x + eps));\n" +
                    "    return vec4(official2primaries(abs(q.z + (q.w - q.y) / (6.0 * d + eps))), (q.x - l) / (min(l, 1.0 - l) + eps), l, c.a);\n" +
                    "}\n" +
                    "\n" +
                    "vec4 hsl2rgb(vec4 c)\n" +
                    "{\n" +
                    "    const vec4 K = vec4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);\n" +
                    "    vec3 p = abs(fract(primaries2official(c.x) + K.xyz) * 6.0 - K.www);\n" +
                    "    float v = (c.z + c.y * min(c.z, 1.0 - c.z));\n" +
                    "    return vec4(v * mix(K.xxx, clamp(p - K.xxx, 0.0, 1.0), 2.0 * (1.0 - c.z / (v + eps))), c.w);\n" +
                    "}";
    /**
     * This GLSL snippet takes an RGB vec3 and a float that represents a hue rotation in radians, and returns the
     * rotated RGB vec3. It is not a full shader, and is used by inserting it into shader code to provide the applyHue()
     * method to that code.
     * <br>
     * Credit for this challenging method goes to Andrey-Postelzhuk,
     * <a href="https://forum.unity.com/threads/hue-saturation-brightness-contrast-shader.260649/">Unity Forums</a>.
     */
    public static final String partialHueRodrigues =
            "vec3 applyHue(vec3 rgb, float hue)\n" +
                    "{\n" +
                    "    vec3 k = vec3(0.57735);\n" +
                    "    float c = cos(hue);\n" +
                    "    //Rodrigues' rotation formula\n" +
                    "    return rgb * c + cross(k, rgb) * sin(hue) + k * dot(k, rgb) * (1.0 - c);\n" +
                    "}\n";

    /**
     * Treats the color as hue, saturation, lightness, and alpha.
     * You probably want {@link #fragmentShaderHSLC} or {@link #fragmentShaderHSLA}.
     * <br>
     * EXPERIMENTAL. Meant more for reading and editing than serious usage.
     */
    public static final String fragmentShaderHSL =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    partialCodeHSL +
                    "void main()\n" +
                    "{\n" +
                    "   vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "   vec4 hsl = rgb2hsl(tgt);\n" +
                    "   hsl.x = fract(v_color.x + hsl.x);\n" +
                    "   hsl.y = hsl.y * v_color.y;\n" +
                    "   hsl.z = hsl.z * v_color.z * 2.0;\n" +
                    "   gl_FragColor = hsl2rgb(hsl);\n" +
                    "}";

    /**
     * I can't even remember what this does, to be honest. It's probably not what you want. Instead, you probably want
     * {@link #fragmentShaderHSLC} or {@link #fragmentShaderHSLA}.
     * <br>
     * EXPERIMENTAL. Meant more for reading and editing than serious usage.
     */
    public static final String fragmentShaderRotateHSL =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    partialCodeHSL +
                    "void main()\n" +
                    "{\n" +
                    "   vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "   vec4 hsl = rgb2hsl(tgt);\n" +
                    "   hsl.x = fract(v_color.x + hsl.x + 0.5);\n" +
                    "   hsl.yz = clamp(hsl.yz * v_color.yz * 2.0, 0.0, 1.0);\n" +
                    "   gl_FragColor = hsl2rgb(hsl);\n" +
                    "}";

    /**
     * This is similar to the default vertex shader from libGDX, but also sets a varying value for contrast. It is
     * needed if you use {@link #fragmentShaderHSLC}.
     */
    public static final String vertexShaderHSLC = "attribute vec4 a_position;\n"
            + "attribute vec4 a_color;\n"
            + "attribute vec2 a_texCoord0;\n"
            + "uniform mat4 u_projTrans;\n"
            + "varying vec4 v_color;\n"
            + "varying vec2 v_texCoords;\n" 
            + "varying float v_lightFix;\n"
            + "\n"
            + "void main()\n"
            + "{\n"
            + "   v_color = a_color;\n"
            + "   v_texCoords = a_texCoord0;\n"
            + "   v_color.a = pow(v_color.a * (255.0/254.0) + 0.5, 1.709);\n"    
            + "   v_lightFix = 1.0 + pow(v_color.a, 1.41421356);\n"
            + "   gl_Position =  u_projTrans * a_position;\n"
            + "}\n";

    /**
     * Allows changing Hue/Saturation/Lightness/Contrast, with hue as a rotation. If hue continuously goes from 0 to 1,
     * then 0 to 1, 0 to 1, and so on, then this will smoothly rotate the hue of the image.
     * <br>
     * You can generate HSLC colors using methods like {@link FloatColors#rgb2hsl(float, float, float, float)}.
     * <br>
     * Credit for HLSL version goes to Andrey-Postelzhuk,
     * <a href="https://forum.unity.com/threads/hue-saturation-brightness-contrast-shader.260649/">Unity Forums</a>.
     * The YCC adaptation, and different approach to contrast (this has close to neutral contrast when a is 0.5,
     * while the original had a fair bit higher contrast than expected), is from this codebase.
     * <br>
     * Meant only for use with {@link #vertexShaderHSLC}, which sets {@code varying float v_lightFix} so contrast can
     * use it.
     */
    public static final String fragmentShaderHSLC = 
                    "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying float v_lightFix;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                            partialHueRodrigues +
                    "void main()\n" +
                    "{\n" +
                    "    float hue = 6.2831853 * (v_color.x - 0.5);\n" +
                    "    float saturation = v_color.y * 2.0;\n" +
                    "    float brightness = v_color.z - 0.5;\n" +
                    "    vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "    tgt.rgb = applyHue(tgt.rgb, hue);\n" +
                    "    tgt.rgb = vec3(\n" +
                    "     (0.5 * pow(dot(tgt.rgb, vec3(0.375, 0.5, 0.125)), v_color.w) * v_lightFix + brightness),\n" + // lightness
                    "     ((tgt.r - tgt.b) * saturation),\n" + // warmth
                    "     ((tgt.g - tgt.b) * saturation));\n" + // mildness
                    "    gl_FragColor = clamp(vec4(\n" +
                    "     dot(tgt.rgb, vec3(1.0, 0.625, -0.5)),\n" + // back to red
                    "     dot(tgt.rgb, vec3(1.0, -0.375, 0.5)),\n" + // back to green
                    "     dot(tgt.rgb, vec3(1.0, -0.375, -0.5)),\n" + // back to blue
                    "     tgt.a), 0.0, 1.0);\n" + // keep alpha, then clamp
                    "}";
    /**
     * Allows changing Hue/Saturation/Lightness/Alpha, with hue as a rotation.
     * <br>
     * You can generate HSLA colors using methods like {@link FloatColors#rgb2hsl(float, float, float, float)}.
     * <br>
     * Credit for HLSL version goes to Andrey-Postelzhuk,
     * <a href="https://forum.unity.com/threads/hue-saturation-brightness-contrast-shader.260649/">Unity Forums</a>.
     * The YCC adaptation, and change to use alpha, is from this codebase.
     * <br>
     * Meant to be used with {@link #vertexShader}, unlike what {@link #fragmentShaderHSLC} expects.
     */
    public static final String fragmentShaderHSLA = 
                    "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                            partialHueRodrigues +
                    "void main()\n" +
                    "{\n" +
                    "    float hue = 6.2831853 * (v_color.x - 0.5);\n" +
                    "    float saturation = v_color.y * 2.0;\n" +
                    "    float brightness = v_color.z - 0.5;\n" +
                    "    vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "    tgt.rgb = applyHue(tgt.rgb, hue);\n" +
                    "    tgt.rgb = vec3(\n" +
                    "     (dot(tgt.rgb, vec3(0.375, 0.5, 0.125)) + brightness),\n" + // lightness
                    "     ((tgt.r - tgt.b) * saturation),\n" + // warmth
                    "     ((tgt.g - tgt.b) * saturation));\n" + // mildness
                    "    gl_FragColor = clamp(vec4(\n" +
                    "     dot(tgt.rgb, vec3(1.0, 0.625, -0.5)),\n" + // back to red
                    "     dot(tgt.rgb, vec3(1.0, -0.375, 0.5)),\n" + // back to green
                    "     dot(tgt.rgb, vec3(1.0, -0.375, -0.5)),\n" + // back to blue
                    "     tgt.a * v_color.w), 0.0, 1.0);\n" + // keep alpha, then clamp
                    "}";
    /**
     * Allows changing Hue/Saturation/Lightness/Alpha, with hue as a rotation.
     * <br>
     * You can generate HSLA colors using methods like {@link FloatColors#rgb2hsl(float, float, float, float)}.
     * <br>
     * Meant to be used with {@link #vertexShader}, unlike what {@link #fragmentShaderHSLC} expects.
     */
    public static final String fragmentShaderHSLA2 =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    partialCodeHSL +
                    "\n" +
                    "void main()\n" +
                    "{\n" +
                    "   vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "   vec4 hsl = rgb2hsl(tgt);\n" +
                    "   hsl.x = fract(v_color.x + hsl.x);\n" +
                    "   hsl.yz = hsl.yz * v_color.yz;\n" +
                    "   gl_FragColor = hsl2rgb(hsl);\n" +
                    "}";
    public static final String fragmentShaderHSLARoundTrip =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    partialCodeHSL +
                    "\n" +
                    "void main()\n" +
                    "{\n" +
                    "   vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "   tgt = hsl2rgb(rgb2hsl(tgt));\n" +
                    "   tgt = hsl2rgb(rgb2hsl(tgt));\n" +
                    "   tgt = hsl2rgb(rgb2hsl(tgt));\n" +
                    "   tgt = hsl2rgb(rgb2hsl(tgt));\n" +
                    "   tgt = hsl2rgb(rgb2hsl(tgt));\n" +
                    "   tgt = hsl2rgb(rgb2hsl(tgt));\n" +
                    "   tgt = hsl2rgb(rgb2hsl(tgt));\n" +
                    "   tgt = hsl2rgb(rgb2hsl(tgt));\n" +
                    "   gl_FragColor = tgt * v_color;\n" +
                    "}";
    /**
     * Generally a lower-quality hue rotation than {@link #fragmentShaderHSLC}; this is here as a work in progress.
     * <br>
     * You can generate HSLC colors using methods like {@link FloatColors#rgb2hsl(float, float, float, float)}.
     * <br>
     * Meant to be used with {@link #vertexShaderHSLC}, which sets {@code varying float v_lightFix} so
     * contrast can use it.
     * <br>
     * EXPERIMENTAL. Meant more for reading and editing than serious usage.
     */
    public static final String fragmentShaderHSLC2 = 
                    "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying float v_lightFix;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                            partialCodeHSL +
                    "void main()\n" +
                    "{\n" +
                    "    float hue = (v_color.x - 0.5);\n" +
                    "    float saturation = v_color.y * 2.0;\n" +
                    "    float brightness = v_color.z - 0.5;\n" +
                    "    vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "    tgt = rgb2hsl(tgt);\n" +
                    "    tgt.r = fract(tgt.r + hue);\n" +
                    "    tgt = hsl2rgb(tgt);\n" +
                    "    tgt.rgb = vec3(\n" +
                    "     (0.5 * pow(dot(tgt.rgb, vec3(0.375, 0.5, 0.125)), v_color.w) * v_lightFix + brightness),\n" + // lightness
                    "     ((tgt.r - tgt.b) * saturation),\n" + // warmth
                    "     ((tgt.g - tgt.b) * saturation));\n" + // mildness
                    "    gl_FragColor = clamp(vec4(\n" +
                    "     dot(tgt.rgb, vec3(1.0, 0.625, -0.5)),\n" + // back to red
                    "     dot(tgt.rgb, vec3(1.0, -0.375, 0.5)),\n" + // back to green
                    "     dot(tgt.rgb, vec3(1.0, -0.375, -0.5)),\n" + // back to blue
                    "     tgt.a), 0.0, 1.0);\n" + // keep alpha, then clamp
                    "}";
    /**
     * Cycles lightness in a psychedelic way as hue and lightness change; not a general-purpose usage.
     * <br>
     * You can generate HSLC colors using methods like {@link FloatColors#rgb2hsl(float, float, float, float)}.
     * <br>
     * Meant to be used with {@link #vertexShaderHSLC}, which sets {@code varying float v_lightFix} so
     * contrast can use it.
     * <br>
     * EXPERIMENTAL. Meant more for reading and editing than serious usage.
     */
    public static final String fragmentShaderHSLC3 =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying float v_lightFix;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    partialHueRodrigues +
                    "void main()\n" +
                    "{\n" +
                    "    float hue = 6.2831853 * (v_color.x - 0.5);\n" +
                    "    float saturation = v_color.y * 2.0;\n" +
                    "    float brightness = v_color.z - 0.5;\n" +
                    "    vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "    tgt.rgb = applyHue(tgt.rgb, hue);\n" +
                    "    tgt.rgb = vec3(\n" +
                    "     (0.5 * pow(dot(tgt.rgb, vec3(0.375, 0.5, 0.125)), v_color.w) * v_lightFix),\n" + // lightness
                    "     ((tgt.r - tgt.b) * saturation),\n" + // warmth
                    "     ((tgt.g - tgt.b) * saturation));\n" + // mildness
                    "    tgt.r = sin((tgt.r + brightness) * 6.2831853) * 0.5 + 0.5;\n" +
                    "    gl_FragColor = clamp(vec4(\n" +
                    "     dot(tgt.rgb, vec3(1.0, 0.625, -0.5)),\n" + // back to red
                    "     dot(tgt.rgb, vec3(1.0, -0.375, 0.5)),\n" + // back to green
                    "     dot(tgt.rgb, vec3(1.0, -0.375, -0.5)),\n" + // back to blue
                    "     tgt.a), 0.0, 1.0);\n" + // keep alpha, then clamp
                    "}";
    /**
     * Cycles hue, but not lightness; otherwise this is like {@link #fragmentShaderHSLC3} without contrast, and keeping
     * alpha intact. Internally, this uses Sam Hocevar's RGB/HSL conversion instead of Andrey-Postelzhuk's HSLC code.
     * <br>
     * You can generate HSLA colors using methods like {@link FloatColors#rgb2hsl(float, float, float, float)}.
     * <br>
     * Expects the vertex shader to be {@link #vertexShader}, not the HSLC variant.
     * <br>
     * EXPERIMENTAL. Meant more for reading and editing than serious usage.
     */
    public static final String fragmentShaderHSL4 =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    partialCodeHSL +
                    "void main()\n" +
                    "{\n" +
                    "    float hue = v_color.x - 0.5;\n" +
                    "    float saturation = v_color.y * 2.0;\n" +
                    "    float brightness = v_color.z - 0.5;\n" +
                    "    vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "    tgt = rgb2hsl(tgt);\n" +
                    "    tgt.x = fract(tgt.x + hue);\n" +
                    "    tgt.y = clamp(tgt.y * saturation, 0.0, 1.0);\n" +
                    "    tgt.z = clamp(brightness + tgt.z, 0.0, 1.0);\n" +
                    "    gl_FragColor = hsl2rgb(tgt);\n" +
                    "}";

    /**
     * One of the more useful HSL shaders here, this takes a batch color as hue, saturation, lightness, and power,
     * with hue as a target hue and power used to determine how much of the target color should be used. There is no
     * neutral value for hue, saturation, or lightness, but if power is 0, then the source color will be used
     * exactly. On the other hand, if power is 1.0, then all pixels will be the target color. Hue is specified from
     * 0.0 to 1.0, with 0.0 as red, about 0.3 as green, about 0.6 as blue, etc. Saturation is specified from 0.0 to 1.0,
     * with 0.0 as grayscale and 1.0 as a fully-saturated target color. Lightness is specified from 0.0 to 1.0, with 0.0
     * as black, the 0.3 to 0.7 range as most colors, and 1.0 white; saturation is clamped to a smaller value as
     * lightness moves away from 0.5 (toward black or white).
     * <br>
     * Expects the vertex shader to be {@link #vertexShader}, not the HSLC variant.
     * <br>
     * You can generate HSL(P) colors using methods like {@link FloatColors#rgb2hsl(float, float, float, float)}.
     */
    public static final String fragmentShaderHSLP =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    partialCodeHSL +
                    "void main()\n" +
                    "{\n" +
                    "   vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "   vec4 hsl = rgb2hsl(tgt);\n" +
                    "   hsl.x *= 6.2831853;\n" +
                    "   hsl.xy = vec2(cos(hsl.x), sin(hsl.x)) * hsl.y;\n" +
                    "   vec3 tint = vec3(cos(v_color.x * 6.2831853) * v_color.y, sin(v_color.x * 6.2831853) * v_color.y * 2.0, v_color.z);\n" +
                    "   hsl.xyz = mix(hsl.xyz, tint, v_color.w);\n" +
                    "   hsl.xy = vec2(fract(atan(hsl.y, hsl.x) / 6.2831853), length(hsl.xy));\n" +
                    "   gl_FragColor = hsl2rgb(hsl);\n" +
                    "}";

    /**
     * This is supposed to look for RGBA colors that are similar to {@code search}, and if it finds
     * one, to replace it with {@code replace} (also an RGBA color). It isn't great at the searching part.
     * <br>
     * You can generate RGB colors using any of various methods in the {@code rgb} package, such as
     * {@link com.github.tommyettinger.colorful.rgb.ColorTools#rgb(float, float, float, float)}.
     * <br>
     * EXPERIMENTAL. Meant more for reading and editing than serious usage.
     */
    public static final String fragmentShaderReplacement = 
                    "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    "uniform vec4 u_search;\n" +
                    "uniform vec4 u_replace;\n" +
                    "void main()\n" +
                    "{\n" +
                    "   vec4 tgt = texture2D(u_texture, v_texCoords);\n" +
                    "   float curve = smoothstep(0.0, 1.0, 1.25 - distance(tgt.rgb, u_search.rgb) * 2.0);\n" +
                    "   gl_FragColor = vec4(mix(tgt.rgb, u_replace.rgb, curve), tgt.a) * v_color;\n" +
                    "}";
    /**
     * A drop-in replacement for the default fragment shader that eliminates lightness differences in the output colors.
     * Specifically, it does the normal SpriteBatch shader's step with the multiplicative batch color, converts to IPT,
     * sets intensity to 0.5, shrinks the P and T components so the color is less saturated, and then converts back to
     * an RGBA color. Even though this uses IPT internally, it expects the batch color to be normal RGBA.
     * <br>
     * Editing this shader is strongly encouraged to fit your needs!
     * <br>
     * You can generate RGB colors using any of various methods in the {@code rgb} package, such as
     * {@link com.github.tommyettinger.colorful.rgb.ColorTools#rgb(float, float, float, float)}.
     * <br>
     * Meant for use with {@link #vertexShader}.
     * @see #fragmentShaderConfigurableContrast a per-sprite-configurable version of this
     */
    public static String fragmentShaderFlatLightness =
  "#ifdef GL_ES\n" +
  "#define LOWP lowp\n" +
  "precision mediump float;\n" +
  "#else\n" +
  "#define LOWP \n" +
  "#endif\n" +
  "#define TARGET_LIGHTNESS 0.5 \n" +
  "#define SATURATION_CHANGE 1.0 \n" +
  "varying vec2 v_texCoords;\n" +
  "varying LOWP vec4 v_color;\n" +
  "uniform sampler2D u_texture;\n" +
  "void main()\n" +
  "{\n" +
  "    vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
  "    vec3 ipt = (mat3(0.189786, 0.669665 , 0.286498, 0.576951, -0.73741 , 0.655205, 0.233221, 0.0681367, -0.941748)\n" +
  "         * (tgt.rgb * v_color.rgb));\n" +
  "    ipt.x = TARGET_LIGHTNESS;\n" + // change to desired lightness or pass in a lightness as a uniform
  "//    ipt.x = (ipt.x - 0.5) * 0.25 + TARGET_LIGHTNESS;\n" + // an alternative way that preserves a tiny bit of original lightness
  "    ipt.yz *= SATURATION_CHANGE;\n" + // multiplies saturation by SATURATION_CHANGE, which may be more or less than 1.0
  "    vec3 back = clamp(mat3(0.999779, 1.00015, 0.999769, 1.07094, -0.377744, 0.0629496, 0.324891, 0.220439, -0.809638) * ipt, 0.0, 1.0);\n" +
  "    gl_FragColor = vec4(back, v_color.a * tgt.a);\n" +
  "}";
    /**
     * A specialized shader that can reduce lightness differences in the output colors, saturate/desaturate them, and
     * can be configured to use some of the existing lightness in the image to add to a main flat lightness.
     * Specifically, it takes the fragment color (typically a pixel in a texture), converts to IPT, does a calculation
     * involving the intensity of the fragment color where the batch color's blue channel affects how much that
     * intensity is used, adds the red channel of the batch color, multiplies the P and T components by the green
     * channel times 2.0 to saturate or desaturate, and then converts back to an RGBA color. The neutral value for this
     * is the RGBA color 0.5, 0.5, 1.0, 1.0 . A typical usage to achieve a fog effect would be to raise lightness (r),
     * slightly reduce saturation (g), sharply flatten the original texture's lightness (b), and leave alpha alone (a):
     * 0.7, 0.4, 0.2, 1.0 .
     * <br>
     * This doesn't use a standard type of color; you should use something like
     * {@link com.github.tommyettinger.colorful.rgb.ColorTools#rgb(float, float, float, float)} to set the channels in
     * the specific way this uses them.
     * <br>
     * Meant for use with {@link #vertexShader}.
     * @see #fragmentShaderFlatLightness if you only need one contrast setting and still want to set color tints
     */
    public static String fragmentShaderConfigurableContrast =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    "void main()\n" +
                    "{\n" +
                    "    vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "    vec3 ipt = (mat3(0.189786, 0.669665 , 0.286498, 0.576951, -0.73741 , 0.655205, 0.233221, 0.0681367, -0.941748)\n" +
                    "         * tgt.rgb);\n" +
                    "    ipt.x = (ipt.x - 0.5) * v_color.b + v_color.r;\n" + // preserves some lightness based on b, sets main lightness with r
                    "    ipt.yz *= v_color.g * 2.0;\n" + // the green channel affects saturation; if it's 0.5 it won't change saturation.
                    "    vec3 back = clamp(mat3(0.999779, 1.00015, 0.999769, 1.07094, -0.377744, 0.0629496, 0.324891, 0.220439, -0.809638) * ipt, 0.0, 1.0);\n" +
                    "    gl_FragColor = vec4(back, v_color.a * tgt.a);\n" +
                    "}";

    /**
     * A day/night cycle shader that can be used without any other parts of this library. This only needs setting the
     * uniform {@code u_timeOfDay} to any float; the rate at which you change this float affects how fast the day/night
     * cycle occurs. This is meant to be used with {@link #fragmentShaderDayNight}. Together, they make the color
     * adjustment go from bluish and dark at night, to purplish at dawn, to orange/yellow and bright at mid-day, toward
     * red at dusk, and then back to bluish at night. This uses an RGBA batch color.
     * <br>
     * Editing this shader is strongly encouraged to fit your needs! The time-based variables st, ct, and dd can all be
     * adjusted to increase or decrease the strength of the effect, and their effects can also be adjusted upon v_color
     * and v_tweak.
     * <br>
     * You can generate RGB colors using any of various methods in the {@code rgb} package, such as
     * {@link com.github.tommyettinger.colorful.rgb.ColorTools#rgb(float, float, float, float)}.
     */
    public static String vertexShaderDayNight = "attribute vec4 " + ShaderProgram.POSITION_ATTRIBUTE + ";\n"
            + "attribute vec4 " + ShaderProgram.COLOR_ATTRIBUTE + ";\n"
            + "attribute vec2 " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n"
            + "uniform mat4 u_projTrans;\n"
            + "uniform float u_timeOfDay;\n"
            + "varying vec4 v_color;\n"
            + "varying vec4 v_tweak;\n"
            + "varying vec2 v_texCoords;\n"
            + "varying float v_lightFix;\n"
            + "const vec3 forward = vec3(1.0 / 3.0);\n"
            + "\n"
            + "void main()\n"
            + "{\n"
            + "   float st = sin(1.5707963 * sin(0.2617994 * u_timeOfDay)); // Whenever st is very high or low... \n"
            + "   float ct = sin(1.5707963 * cos(0.2617994 * u_timeOfDay)); // ...ct is close to 0, and vice versa. \n"
            + "   float dd = ct * ct; // Positive, small; used for dawn and dusk. \n"
            + "   v_color = " + ShaderProgram.COLOR_ATTRIBUTE + ";\n"
            + "   v_color.w = v_color.w * (255.0/254.0);\n"
            + "   vec3 oklab = mat3(+0.2104542553, +1.9779984951, +0.0259040371, +0.7936177850, -2.4285922050, +0.7827717662, -0.0040720468, +0.4505937099, -0.8086757660) *"
            + "     pow(mat3(0.4121656120, 0.2118591070, 0.0883097947, 0.5362752080, 0.6807189584, 0.2818474174, 0.0514575653, 0.1074065790, 0.6302613616) \n"
            + "     * (v_color.rgb * v_color.rgb), forward);\n"
            + "   // The next four lines make use of the time-based variables st, ct, and dd. Edit to fit. \n"
            + "   v_color.x = clamp(oklab.x + (0.0625 * st), 0.0, 1.0);\n"
            + "   v_color.yz = clamp(oklab.yz + vec2(0.0625 * dd + 0.03125 * st, 0.1 * st), -1.0, 1.0) * ((dd + 0.25) * 0.5);\n"
            + "   v_tweak = vec4(0.2 * st + 0.5);\n"
            + "   v_tweak.w = pow((1.0 - 0.125 * st), 1.709);\n"
            + "   v_lightFix = 1.0 + pow(v_tweak.w, 1.41421356);\n"
            + "   v_texCoords = " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n"
            + "   gl_Position =  u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n"
            + "}\n";
    /**
     * The fragment shader counterpart to {@link #vertexShaderDayNight}; must be used with that vertex shader. See its
     * docs for more info, particularly about the one uniform this needs set. This uses an RGBA batch color.
     * <br>
     * You can generate RGB colors using any of various methods in the {@code rgb} package, such as
     * {@link com.github.tommyettinger.colorful.rgb.ColorTools#rgb(float, float, float, float)}.
     * <br>
     * Meant only for use with {@link #vertexShaderDayNight}.
     */
    public static String fragmentShaderDayNight =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "varying LOWP vec4 v_tweak;\n" +
                    "varying float v_lightFix;\n" +
                    "uniform sampler2D u_texture;\n" +
                    "const vec3 forward = vec3(1.0 / 3.0);\n" +
                    "void main()\n" +
                    "{\n" +
                    "  vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "  vec3 lab = mat3(+0.2104542553, +1.9779984951, +0.0259040371, +0.7936177850, -2.4285922050, +0.7827717662, -0.0040720468, +0.4505937099, -0.8086757660) *" +
                    "             pow(mat3(0.4121656120, 0.2118591070, 0.0883097947, 0.5362752080, 0.6807189584, 0.2818474174, 0.0514575653, 0.1074065790, 0.6302613616) \n" +
                    "             * (tgt.rgb * tgt.rgb), forward);\n" +
                    "  lab.x = clamp(pow(lab.x, v_tweak.w) * v_lightFix * v_tweak.x + v_color.x - 1.0, 0.0, 1.0);\n" +
                    "  lab.yz = clamp((lab.yz * v_tweak.yz + v_color.yz) * 1.5, -1.0, 1.0);\n" +
                    "  lab = mat3(1.0, 1.0, 1.0, +0.3963377774, -0.1055613458, -0.0894841775, +0.2158037573, -0.0638541728, -1.2914855480) * lab;\n" +
                    "  gl_FragColor = vec4(sqrt(clamp(" +
                    "                 mat3(+4.0767245293, -1.2681437731, -0.0041119885, -3.3072168827, +2.6093323231, -0.7034763098, +0.2307590544, -0.3411344290, +1.7068625689) *\n" +
                    "                 (lab * lab * lab)," +
                    "                 0.0, 1.0)), v_color.a * tgt.a);\n" +
                    "}";

    /**
     * Takes a batch color in CIE LAB format (but ranging from 0 to 1 instead of its normal larger range).
     * Adapted from <a href="https://www.shadertoy.com/view/lsdGzN">This ShaderToy by nmz</a>.
     * <br>
     * You can generate CIELAB colors using any of various methods in the {@code cielab} package, such as
     * {@link com.github.tommyettinger.colorful.cielab.ColorTools#cielab(float, float, float, float)}.
     * <br>
     * Meant for use with {@link #vertexShader}.
     */
    public static String fragmentShaderCielab =
            "#ifdef GL_ES\n" +
            "#define LOWP lowp\n" +
            "precision mediump float;\n" +
            "#else\n" +
            "#define LOWP \n" +
            "#endif\n" +
            "varying vec2 v_texCoords;\n" +
            "varying LOWP vec4 v_color;\n" +
            "uniform sampler2D u_texture;\n" +
            "const vec3 forward = vec3(1.0 / 3.0);\n" +
            "const vec3 sRGBFrom = vec3(2.4);\n" +
            "const vec3 sRGBThresholdFrom = vec3(0.04045);\n" +
            "const vec3 sRGBTo = vec3(1.0 / 2.4);\n" +
            "const vec3 sRGBThresholdTo = vec3(0.0031308);\n" +
            "const vec3 epsilon = vec3(0.00885645);\n" +
            "vec3 linear(vec3 t){ return mix(pow((t + 0.055) * (1.0 / 1.055), sRGBFrom), t * (1.0/12.92), step(t, sRGBThresholdFrom)); }\n" +
            "vec3 sRGB(vec3 t){ return mix(1.055 * pow(t, sRGBTo) - 0.055, 12.92*t, step(t, sRGBThresholdTo)); }\n" +
            "float xyzF(float t){ return mix(pow(t,1./3.), 7.787037 * t + 0.139731, step(t, 0.00885645)); }\n" +
            "vec3 xyzF(vec3 t){ return mix(pow(t, forward), 7.787037 * t + 0.139731, step(t, epsilon)); }\n" +
            "float xyzR(float t){ return mix(t*t*t , 0.1284185 * (t - 0.139731), step(t, 0.20689655)); }\n" +
            "vec3 rgb2lab(vec3 c)\n" +
            "{\n" +
            "    c *= mat3(0.4124, 0.3576, 0.1805,\n" +
            "              0.2126, 0.7152, 0.0722,\n" +
            "              0.0193, 0.1192, 0.9505);\n" +
            "    c = xyzF(c);\n" +
            "    vec3 lab = vec3(max(0.,1.16*c.y - 0.16), (c.x - c.y) * 5.0, (c.y - c.z) * 2.0); \n" +
            "    return lab;\n" +
//            "    return vec3(lab.x, length(vec2(lab.y,lab.z)), atan(lab.z, lab.y));\n" +
            "}\n" +
            "vec3 lab2rgb(vec3 c)\n" +
            "{\n" +
//            "    c = vec3(c.x, cos(c.z) * c.y, sin(c.z) * c.y);\n" +
            "    float lg = 1./1.16*(c.x + 0.16);\n" +
            "    vec3 xyz = vec3(xyzR(lg + c.y * 0.2),\n" +
            "                    xyzR(lg),\n" +
            "                    xyzR(lg - c.z * 0.5));\n" +
            "    vec3 rgb = xyz*mat3( 3.2406, -1.5372,-0.4986,\n" +
            "                        -0.9689,  1.8758, 0.0415,\n" +
            "                         0.0557, -0.2040, 1.0570);\n" +
            "    return rgb;\n" +
            "}\n" +
            "void main()\n" +
            "{\n" +
            "  vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
            "  vec3 lab = rgb2lab(linear(tgt.rgb));\n" +
            "  lab.x = lab.x + v_color.r - 0.5372549;\n" +
            "  lab.yz = lab.yz + (v_color.gb - 0.5) * 2.0;\n" +
            "  gl_FragColor = vec4(sRGB(clamp(lab2rgb(lab), 0.0, 1.0)), v_color.a * tgt.a);\n" +
            "}";

    /**
     * Makes the colors in the given textures almost-grayscale, then moves their chromatic channels much closer to the
     * batch color, without changing the lightness. The result is almost all the same hue as the batch color, and can be
     * gray if the batch color is any grayscale color. This uses an RGB batch color for simpler usage in most code that
     * doesn't already use colorful-gdx. There can be some contribution from the original texture, so even if the batch
     * color is gray, then the result will probably have some very muted colors.
     * <br>
     * You can generate RGB colors using any of various methods in the {@code rgb} package, such as
     * {@link com.github.tommyettinger.colorful.rgb.ColorTools#rgb(float, float, float, float)}.
     * <br>
     * Meant for use with {@link #vertexShader}.
     */
    public static String fragmentShaderColorize =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    "const vec3 forward = vec3(1.0 / 3.0);\n" +
                    "void main()\n" +
                    "{\n" +
                    "  vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "//// Ugly repeated matrix math to convert from RGB to Oklab. Oklab keeps lightness separate from hue and saturation.\n" +
                    "  vec3 base = mat3(+0.2104542553, +1.9779984951, +0.0259040371, +0.7936177850, -2.4285922050, +0.7827717662, -0.0040720468, +0.4505937099, -0.8086757660) *" +
                    "              pow(mat3(0.4121656120, 0.2118591070, 0.0883097947, 0.5362752080, 0.6807189584, 0.2818474174, 0.0514575653, 0.1074065790, 0.6302613616) \n" +
                    "              * (tgt.rgb * tgt.rgb), forward);\n" +
                    "  vec3 tint = mat3(+0.2104542553, +1.9779984951, +0.0259040371, +0.7936177850, -2.4285922050, +0.7827717662, -0.0040720468, +0.4505937099, -0.8086757660) *" +
                    "              pow(mat3(0.4121656120, 0.2118591070, 0.0883097947, 0.5362752080, 0.6807189584, 0.2818474174, 0.0514575653, 0.1074065790, 0.6302613616) \n" +
                    "              * (v_color.rgb * v_color.rgb), forward);\n" +
                    "//// Sharply increases lightness contrast, to counteract the gray-ing caused by averaging base and tint lightness.\n" +
                    "  tint.x = (tint.x + base.x) - 1.0;\n" +
                    "  tint.x = sign(tint.x) * pow(abs(tint.x), 0.7) * 0.5 + 0.5;\n" +
                    "//// Uncomment these next 3 lines if you want the original image to contribute some color, if it has any.\n" +
                    "  float blen = length(base.yz);\n" +
                    "  blen *= blen;\n" +
                    "  tint.yz = clamp(tint.yz * (0.7 + blen) + base.yz * (0.3 - blen), -1.0, 1.0);\n" +
                    "//// Reverse the Oklab conversion to get back to RGB. Uses the batch color's alpha normally.\n" +
                    "  tint = mat3(1.0, 1.0, 1.0, +0.3963377774, -0.1055613458, -0.0894841775, +0.2158037573, -0.0638541728, -1.2914855480) * tint;\n" +
                    "  gl_FragColor = vec4(sqrt(clamp(" +
                    "                 mat3(+4.0767245293, -1.2681437731, -0.0041119885, -3.3072168827, +2.6093323231, -0.7034763098, +0.2307590544, -0.3411344290, +1.7068625689) *\n" +
                    "                 (tint * tint * tint)," +
                    "                 0.0, 1.0)), v_color.a * tgt.a);\n" +
                    "}";

    /**
     * Makes the colors in the given textures almost-grayscale, then moves their chromatic channels much closer to the
     * batch color's chromatic channels, without changing the lightness. The result is almost all the same hue as the
     * batch color, and can be gray if the batch color is any grayscale color. The lightness of the batch color is used
     * to determine how strong the effect should be; you may want to use an out-of-gamut color for very strong tint
     * effects, because those need to be light and the lightest gamut is just one point. This uses an Oklab batch color.
     * There is some contribution from the original texture, varied based on the batch lightness, so even if the batch
     * color is gray, then the result will probably have some very muted colors.
     * <br>
     * You can generate Oklab colors using any of various methods in the {@code oklab} package, such as
     * {@link com.github.tommyettinger.colorful.oklab.ColorTools#oklab(float, float, float, float)}.
     * <br>
     * Meant for use with {@link #vertexShader}.
     */
    public static String fragmentShaderColorizeOklab =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    "const vec3 forward = vec3(1.0 / 3.0);\n" +
                    "void main()\n" +
                    "{\n" +
                    "  vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "  vec3 base = mat3(+0.2104542553, +1.9779984951, +0.0259040371, +0.7936177850, -2.4285922050, +0.7827717662, -0.0040720468, +0.4505937099, -0.8086757660) *" +
                    "              pow(mat3(0.4121656120, 0.2118591070, 0.0883097947, 0.5362752080, 0.6807189584, 0.2818474174, 0.0514575653, 0.1074065790, 0.6302613616) \n" +
                    "              * (tgt.rgb * tgt.rgb), forward);\n" +
                    "  vec2 tint = v_color.gb - 0.5;\n" +
                    "  base.x = clamp(base.x, 0.0, 1.0);\n" +
                    "  float blen = length(base.yz);\n" +
                    "  blen *= blen;\n" +
                    "  base.gb = clamp(tint * (v_color.r + blen) + base.yz * (1.0 - v_color.r - blen), -1.0, 1.0);\n" +
                    "  base = mat3(1.0, 1.0, 1.0, +0.3963377774, -0.1055613458, -0.0894841775, +0.2158037573, -0.0638541728, -1.2914855480) * base;\n" +
                    "  gl_FragColor = vec4(sqrt(clamp(" +
                    "                 mat3(+4.0767245293, -1.2681437731, -0.0041119885, -3.3072168827, +2.6093323231, -0.7034763098, +0.2307590544, -0.3411344290, +1.7068625689) *\n" +
                    "                 (base * base * base)," +
                    "                 0.0, 1.0)), v_color.a * tgt.a);\n" +
                    "}";
    /**
     * The simplest possible color-inverting shader for a SpriteBatch, this just takes each RGB channel and subtracts it
     * from 1.0 to get the value this uses. This uses an RGBA batch color.
     * <br>
     * You can generate RGB colors using any of various methods in the {@code rgb} package, such as
     * {@link com.github.tommyettinger.colorful.rgb.ColorTools#rgb(float, float, float, float)}.
     * <br>
     * Meant only for use with {@link #vertexShader}.
     */
    public static final String fragmentShaderInvertedRGB =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    "void main()\n" +
                    "{\n" +
                    "   vec4 tgt = texture2D(u_texture, v_texCoords);\n" +
                    "   gl_FragColor = vec4(1.0 - tgt.rgb * v_color.rgb, v_color.a * tgt.a);\n" +
                    "}";

    /**
     * Just like {@link #fragmentShaderInvertedRGB}, but internally converts to Oklab, so it can invert just lightness
     * without changing color hue or saturation. This uses an RGBA batch color.
     * <br>
     * You can generate RGB colors using any of various methods in the {@code rgb} package, such as
     * {@link com.github.tommyettinger.colorful.rgb.ColorTools#rgb(float, float, float, float)}.
     * <br>
     * Meant for use with {@link #vertexShader}.
     */
    public static String fragmentShaderInvertedLightness =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    "const vec3 forward = vec3(1.0 / 3.0);\n" +
                    "float toOklab(float L) {\n" +
                    "        const float shape = 0.64516133, turning = 0.95;\n" +
                    "        float d = turning - L;\n" +
                    "        float r = mix(\n" +
                    "          ((1. - turning) * (L - 1.)) / (1. - (L + shape * d)) + 1.,\n" +
                    "          (turning * L) / (1.0e-3 + (L + shape * d)),\n" +
                    "          step(0.0, d));\n" +
                    "        return r * r;\n" +
                    "}\n" +
                    "float fromOklab(float L) {\n" +
                    "        const float shape = 1.55, turning = 0.95;\n" +
                    "        L = sqrt(L);\n" +
                    "        float d = turning - L;\n" +
                    "        return mix(\n" +
                    "          ((1. - turning) * (L - 1.)) / (1. - (L + shape * d)) + 1.,\n" +
                    "          (turning * L) / (1.0e-3 + (L + shape * d)),\n" +
                    "          step(0.0, d));\n" +
                    "}\n" +
//                    "float toOklab(float L) {\n" +
//                    "  return (L - 1.0) / (1.0 - L * 0.4285714) + 1.0;\n" +
//                    "}\n" +
//                    "float fromOklab(float L) {\n" +
//                    "  return (L - 1.0) / (1.0 + L * 0.75) + 1.0;\n" +
//                    "}\n" +
                    "void main()\n" +
                    "{\n" +
                    "  vec4 tgt = texture2D( u_texture, v_texCoords ) * v_color;\n" +
                    "  vec3 lab = mat3(+0.2104542553, +1.9779984951, +0.0259040371, +0.7936177850, -2.4285922050, +0.7827717662, -0.0040720468, +0.4505937099, -0.8086757660) *" +
                    "             pow(mat3(0.4121656120, 0.2118591070, 0.0883097947, 0.5362752080, 0.6807189584, 0.2818474174, 0.0514575653, 0.1074065790, 0.6302613616) \n" +
                    "             * (tgt.rgb * tgt.rgb), forward);\n" +
                    "  lab.x = fromOklab(1.0 - toOklab(lab.x));\n" +
                    "  lab = mat3(1.0, 1.0, 1.0, +0.3963377774, -0.1055613458, -0.0894841775, +0.2158037573, -0.0638541728, -1.2914855480) * lab;\n" +
                    "  gl_FragColor = vec4(sqrt(clamp(" +
                    "                 mat3(+4.0767245293, -1.2681437731, -0.0041119885, -3.3072168827, +2.6093323231, -0.7034763098, +0.2307590544, -0.3411344290, +1.7068625689) *\n" +
                    "                 (lab * lab * lab)," +
                    "                 0.0, 1.0)), tgt.a);\n" +
                    "}";

    /**
     * Just like {@link #fragmentShaderInvertedLightness}, but instead of inverting lightness, this tries to change only
     * hue, without changing lightness or (generally) saturation. This works internally by negating the A and B
     * components of the color (as Oklab). This uses an RGBA batch color.
     * <br>
     * You can generate RGB colors using any of various methods in the {@code rgb} package, such as
     * {@link com.github.tommyettinger.colorful.rgb.ColorTools#rgb(float, float, float, float)}.
     * <br>
     * Meant for use with {@link #vertexShader}.
     */
    public static String fragmentShaderInvertedChroma =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    "const vec3 forward = vec3(1.0 / 3.0);\n" +
                    "void main()\n" +
                    "{\n" +
                    "  vec4 tgt = texture2D( u_texture, v_texCoords ) * v_color;\n" +
                    "  vec3 lab = mat3(+0.2104542553, +1.9779984951, +0.0259040371, +0.7936177850, -2.4285922050, +0.7827717662, -0.0040720468, +0.4505937099, -0.8086757660) *" +
                    "             pow(mat3(0.4121656120, 0.2118591070, 0.0883097947, 0.5362752080, 0.6807189584, 0.2818474174, 0.0514575653, 0.1074065790, 0.6302613616) \n" +
                    "             * (tgt.rgb * tgt.rgb), forward);\n" +
                    "  lab.yz = -lab.yz;\n" +
                    "  lab = mat3(1.0, 1.0, 1.0, +0.3963377774, -0.1055613458, -0.0894841775, +0.2158037573, -0.0638541728, -1.2914855480) * lab;\n" +
                    "  gl_FragColor = vec4(sqrt(clamp(" +
                    "                 mat3(+4.0767245293, -1.2681437731, -0.0041119885, -3.3072168827, +2.6093323231, -0.7034763098, +0.2307590544, -0.3411344290, +1.7068625689) *\n" +
                    "                 (lab * lab * lab)," +
                    "                 0.0, 1.0)), tgt.a);\n" +
                    "}";

    /**
     * Just like {@link #fragmentShaderInvertedChroma}, but instead of inverting chroma, this tries to significantly
     * increase the saturation of a color (by pushing chroma away from gray). This works internally by multiplying the A
     * and B components of the color (as Oklab) by 2.0 . This uses an RGBA batch color.
     * <br>
     * You can generate RGB colors using any of various methods in the {@code rgb} package, such as
     * {@link com.github.tommyettinger.colorful.rgb.ColorTools#rgb(float, float, float, float)}.
     * <br>
     * Meant for use with {@link #vertexShader}.
     */
    public static String fragmentShaderDoubleSaturation =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    "const vec3 forward = vec3(1.0 / 3.0);\n" +
                    "void main()\n" +
                    "{\n" +
                    "  vec4 tgt = texture2D( u_texture, v_texCoords ) * v_color;\n" +
                    "  vec3 lab = mat3(+0.2104542553, +1.9779984951, +0.0259040371, +0.7936177850, -2.4285922050, +0.7827717662, -0.0040720468, +0.4505937099, -0.8086757660) *" +
                    "             pow(mat3(0.4121656120, 0.2118591070, 0.0883097947, 0.5362752080, 0.6807189584, 0.2818474174, 0.0514575653, 0.1074065790, 0.6302613616) \n" +
                    "             * (tgt.rgb * tgt.rgb), forward);\n" +
                    "  lab.yz *=   2.000  ;\n" +
                    "  lab = mat3(1.0, 1.0, 1.0, +0.3963377774, -0.1055613458, -0.0894841775, +0.2158037573, -0.0638541728, -1.2914855480) * lab;\n" +
                    "  gl_FragColor = vec4(sqrt(clamp(" +
                    "                 mat3(+4.0767245293, -1.2681437731, -0.0041119885, -3.3072168827, +2.6093323231, -0.7034763098, +0.2307590544, -0.3411344290, +1.7068625689) *\n" +
                    "                 (lab * lab * lab)," +
                    "                 0.0, 1.0)), tgt.a);\n" +
                    "}";

    /**
     * Prepares and returns a new SpriteBatch that uses {@link #vertexShader} and {@link #fragmentShaderDoubleSaturation}
     * from this class, making it able to render RGBA colors from the libGDX or the rgb package. This also takes a
     * {@code saturationMultiplier} parameter, which modifies the saturation in the fragment shader so it doesn't have
     * to always double the saturation, and can change it by more or less. If greater than 1.0, saturationMultiplier
     * will make colors more saturated/rich/vivid, while if it is less than 1.0 it will make all colors closer to gray.
     * Using 0.0 will make all colors grayscale.
     * <br>
     * You can generate RGB colors using any of various methods in the {@code rgb} package, such as
     * {@link com.github.tommyettinger.colorful.rgb.ColorTools#rgb(float, float, float, float)}.
     *
     * @param saturationMultiplier what to multiply the saturation by; 1.0 will have no change, 0.5 desaturates, 2.0 over-saturates
     * @return a freshly allocated SpriteBatch that will also have a new ShaderProgram for rendering RGB with contrast
     */
    public static SpriteBatch makeSaturatingBatch(final float saturationMultiplier)
    {
        ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShaderDoubleSaturation.replace("  2.000  ", Float.toString(saturationMultiplier)));
        if(!shader.isCompiled())
            throw new GdxRuntimeException("Couldn't compile shader: " + shader.getLog());
        return new SpriteBatch(1000, shader);
    }

    /**
     * This is similar to the default vertex shader from libGDX, but also does some expensive setup work for HSLuv batch
     * colors per-vertex instead of per-fragment. It is needed if you use {@link #fragmentShaderHsluv}.
     */
    public static final String vertexShaderHsluv =
            "attribute vec4 " + ShaderProgram.POSITION_ATTRIBUTE + ";\n"
            + "attribute vec4 " + ShaderProgram.COLOR_ATTRIBUTE + ";\n"
            + "attribute vec2 " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n"
            + "uniform mat4 u_projTrans;\n"
            + "varying vec4 v_color;\n"
            + "varying vec2 v_texCoords;\n"
            +
            "const vec3 epsilon = vec3(0.0088564516790356308);\n" +
            "const float kappa = 9.032962962;\n" +
            "const mat3 m =" +
            "         mat3(+3.240969941904521, -1.537383177570093, -0.498610760293000,\n" +
            "              -0.969243636280870, +1.875967501507720, +0.041555057407175,\n" +
            "              +0.055630079696993, -0.203976958888970, +1.056971514242878);\n" +
            "float intersectLength (float sn, float cs, float line1, float line2) {\n" +
            "    return line2 / (sn - line1 * cs);\n" +
            "}\n" +
            "float chromaLimit(float hue, float lightness) {\n" +
            "        float sn = sin(hue);\n" +
            "        float cs = cos(hue);\n" +
            "        float sub1 = (lightness + 0.16) / 1.16;\n" +
            "        sub1 *= sub1 * sub1;\n" +
            "        float sub2 = sub1 > epsilon.x ? sub1 : lightness / kappa;\n" +
            "        float mn = 1.0e20;\n" +
            "        vec3 ms = m[0] * sub2;\n" +
            "        float msy, top1, top2, bottom, length;\n" +
            "        msy = ms.y;\n" +
            "        top1 = 2845.17 * ms.x - 948.39 * ms.z;\n" +
            "        top2 = (8384.22 * ms.z + 7698.60 * msy + 7317.18 * ms.x) * lightness;\n" +
            "        bottom = (6322.60 * ms.z - 1264.52 * msy);\n" +
            "        length = intersectLength(sn, cs, top1 / bottom, top2 / bottom);\n" +
            "        if (length >= 0.) mn = min(mn, length);\n" +
            "        msy -= 1.0;\n" +
            "        top1 = 2845.17 * ms.x - 948.39 * ms.z;\n" +
            "        top2 = (8384.22 * ms.z + 7698.60 * msy + 7317.18 * ms.x) * lightness;\n" +
            "        bottom = (6322.60 * ms.z - 1264.52 * msy);\n" +
            "        length = intersectLength(sn, cs, top1 / bottom, top2 / bottom);\n" +
            "        if (length >= 0.) mn = min(mn, length);\n" +
            "        ms = m[1] * sub2;\n" +
            "        msy = ms.y;\n" +
            "        top1 = 2845.17 * ms.x - 948.39 * ms.z;\n" +
            "        top2 = (8384.22 * ms.z + 7698.60 * msy + 7317.18 * ms.x) * lightness;\n" +
            "        bottom = (6322.60 * ms.z - 1264.52 * msy);\n" +
            "        length = intersectLength(sn, cs, top1 / bottom, top2 / bottom);\n" +
            "        if (length >= 0.) mn = min(mn, length);\n" +
            "        msy -= 1.0;\n" +
            "        top1 = 2845.17 * ms.x - 948.39 * ms.z;\n" +
            "        top2 = (8384.22 * ms.z + 7698.60 * msy + 7317.18 * ms.x) * lightness;\n" +
            "        bottom = (6322.60 * ms.z - 1264.52 * msy);\n" +
            "        length = intersectLength(sn, cs, top1 / bottom, top2 / bottom);\n" +
            "        if (length >= 0.) mn = min(mn, length);\n" +
            "        ms = m[2] * sub2;\n" +
            "        msy = ms.y;\n" +
            "        top1 = 2845.17 * ms.x - 948.39 * ms.z;\n" +
            "        top2 = (8384.22 * ms.z + 7698.60 * msy + 7317.18 * ms.x) * lightness;\n" +
            "        bottom = (6322.60 * ms.z - 1264.52 * msy);\n" +
            "        length = intersectLength(sn, cs, top1 / bottom, top2 / bottom);\n" +
            "        if (length >= 0.) mn = min(mn, length);\n" +
            "        msy -= 1.0;\n" +
            "        top1 = 2845.17 * ms.x - 948.39 * ms.z;\n" +
            "        top2 = (8384.22 * ms.z + 7698.60 * msy + 7317.18 * ms.x) * lightness;\n" +
            "        bottom = (6322.60 * ms.z - 1264.52 * msy);\n" +
            "        length = intersectLength(sn, cs, top1 / bottom, top2 / bottom);\n" +
            "        if (length >= 0.) mn = min(mn, length);\n" +
            "        return mn;\n" +
            "}\n" +
            "vec3 hsl2luv(vec3 c)\n" +
            "{\n" +
            "    float L = c.z;\n" +
            "    float C = chromaLimit(c.x, L) * c.y;\n" +
            "    float U = cos(c.x) * C;\n" +
            "    float V = sin(c.x) * C;\n" +
            "    return vec3(L, U, V);\n" +
            "}\n"
            + "void main()\n"
            + "{\n"
            + "   v_color = " + ShaderProgram.COLOR_ATTRIBUTE + ";\n"
            + "   v_color.w = v_color.w * (255.0/254.0);\n"
            + "   v_color.x *= 6.2831;\n"
            + "   v_color.rgb = hsl2luv(v_color.rgb);\n"
            + "   v_texCoords = " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n"
            + "   gl_Position =  u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n"
            + "}\n";

    /**
     * Just like {@link #fragmentShaderOklab}, but uses the HSLuv color space instead of the Oklab one.
     * This also gamma-corrects the inputs and outputs, though it uses subtly different math internally.
     * <br>
     * You can generate HSLuv colors using any of various methods in the {@code hsluv} package, such as
     * {@link com.github.tommyettinger.colorful.hsluv.ColorTools#hsluv(float, float, float, float)}.
     * <br>
     * Meant for use with {@link #vertexShaderHsluv}.
     */
    public static String fragmentShaderHsluv =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    "const vec3 forward = vec3(1.0 / 3.0);\n" +
                    "const vec3 sRGBFrom = vec3(2.4);\n" +
                    "const vec3 sRGBThresholdFrom = vec3(0.04045);\n" +
                    "const vec3 sRGBTo = vec3(1.0 / 2.4);\n" +
                    "const vec3 sRGBThresholdTo = vec3(0.0031308);\n" +
                    "const vec3 epsilon = vec3(0.0088564516790356308);\n" +
                    "const float kappa = 9.032962962;\n" +
                    "const vec2 refUV = vec2(0.19783000664283681, 0.468319994938791);\n" +
                    "const mat3 m =" +
                    "         mat3(+3.240969941904521, -1.537383177570093, -0.498610760293000,\n" +
                    "              -0.969243636280870, +1.875967501507720, +0.041555057407175,\n" +
                    "              +0.055630079696993, -0.203976958888970, +1.056971514242878);\n" +
                    "const mat3 mInv =\n" +
                    "         mat3(0.41239079926595948 , 0.35758433938387796, 0.180480788401834290,\n" +
                    "              0.21263900587151036 , 0.71516867876775593, 0.072192315360733715,\n" +
                    "              0.019330818715591851, 0.11919477979462599, 0.950532152249660580);\n" +
                    "vec3 linear(vec3 t){ return mix(pow((t + 0.055) * (1.0 / 1.055), sRGBFrom), t * (1.0/12.92), step(t, sRGBThresholdFrom)); }\n" +
                    "vec3 sRGB(vec3 t){ return mix(1.055 * pow(t, sRGBTo) - 0.055, 12.92*t, step(t, sRGBThresholdTo)); }\n" +
                    "float xyzF(float t){ return mix(pow(t,1./3.), 7.787037 * t + 0.139731, step(t, epsilon.x)); }\n" +
                    "vec3 xyzF(vec3 t){ return mix(pow(t, forward), 7.787037 * t + 0.139731, step(t, epsilon)); }\n" +
                    "float xyzR(float t){ return mix(t*t*t , 0.1284185 * (t - 0.139731), step(t, 0.20689655)); }\n" +
                    "float intersectLength (float sn, float cs, float line1, float line2) {\n" +
                    "    return line2 / (sn - line1 * cs);\n" +
                    "}\n" +
                    "float chromaLimit(float hue, float lightness) {\n" +
                    "        float sn = sin(hue);\n" +
                    "        float cs = cos(hue);\n" +
                    "        float sub1 = (lightness + 0.16) / 1.16;\n" +
                    "        sub1 *= sub1 * sub1;\n" +
                    "        float sub2 = sub1 > epsilon.x ? sub1 : lightness / kappa;\n" +
                    "        float mn = 1.0e20;\n" +
                    "        vec3 ms = m[0] * sub2;\n" +
                    "        float msy, top1, top2, bottom, length;\n" +
                    "        msy = ms.y;\n" +
                    "        top1 = 2845.17 * ms.x - 948.39 * ms.z;\n" +
                    "        top2 = (8384.22 * ms.z + 7698.60 * msy + 7317.18 * ms.x) * lightness;\n" +
                    "        bottom = (6322.60 * ms.z - 1264.52 * msy);\n" +
                    "        length = intersectLength(sn, cs, top1 / bottom, top2 / bottom);\n" +
                    "        if (length >= 0.) mn = min(mn, length);\n" +
                    "        msy -= 1.0;\n" +
                    "        top1 = 2845.17 * ms.x - 948.39 * ms.z;\n" +
                    "        top2 = (8384.22 * ms.z + 7698.60 * msy + 7317.18 * ms.x) * lightness;\n" +
                    "        bottom = (6322.60 * ms.z - 1264.52 * msy);\n" +
                    "        length = intersectLength(sn, cs, top1 / bottom, top2 / bottom);\n" +
                    "        if (length >= 0.) mn = min(mn, length);\n" +
                    "        ms = m[1] * sub2;\n" +
                    "        msy = ms.y;\n" +
                    "        top1 = 2845.17 * ms.x - 948.39 * ms.z;\n" +
                    "        top2 = (8384.22 * ms.z + 7698.60 * msy + 7317.18 * ms.x) * lightness;\n" +
                    "        bottom = (6322.60 * ms.z - 1264.52 * msy);\n" +
                    "        length = intersectLength(sn, cs, top1 / bottom, top2 / bottom);\n" +
                    "        if (length >= 0.) mn = min(mn, length);\n" +
                    "        msy -= 1.0;\n" +
                    "        top1 = 2845.17 * ms.x - 948.39 * ms.z;\n" +
                    "        top2 = (8384.22 * ms.z + 7698.60 * msy + 7317.18 * ms.x) * lightness;\n" +
                    "        bottom = (6322.60 * ms.z - 1264.52 * msy);\n" +
                    "        length = intersectLength(sn, cs, top1 / bottom, top2 / bottom);\n" +
                    "        if (length >= 0.) mn = min(mn, length);\n" +
                    "        ms = m[2] * sub2;\n" +
                    "        msy = ms.y;\n" +
                    "        top1 = 2845.17 * ms.x - 948.39 * ms.z;\n" +
                    "        top2 = (8384.22 * ms.z + 7698.60 * msy + 7317.18 * ms.x) * lightness;\n" +
                    "        bottom = (6322.60 * ms.z - 1264.52 * msy);\n" +
                    "        length = intersectLength(sn, cs, top1 / bottom, top2 / bottom);\n" +
                    "        if (length >= 0.) mn = min(mn, length);\n" +
                    "        msy -= 1.0;\n" +
                    "        top1 = 2845.17 * ms.x - 948.39 * ms.z;\n" +
                    "        top2 = (8384.22 * ms.z + 7698.60 * msy + 7317.18 * ms.x) * lightness;\n" +
                    "        bottom = (6322.60 * ms.z - 1264.52 * msy);\n" +
                    "        length = intersectLength(sn, cs, top1 / bottom, top2 / bottom);\n" +
                    "        if (length >= 0.) mn = min(mn, length);\n" +
                    "        return mn;\n" +
                    "}\n" +
                    "vec3 rgb2luv(vec3 c)\n" +
                    "{\n" +
                    "    c *= mInv;" +
                    "    float L = max(0.,1.16*pow(c.y, 1.0 / 3.0) - 0.16);\n" +
                    "    vec2 uv;\n" +
                    "    if(L < 0.0001) uv = vec2(0.0);\n" +
                    "    else uv = 13. * L * (vec2(4., 9.) * c.xy / (c.x + 15. * c.y + 3. * c.z) - refUV);\n" +
                    "    return vec3(L, uv);\n" +
                    "}\n" +
                    "float forwardLight(float L) {\n" +
                    "        const float shape = 0.8528, turning = 0.1;\n" +
                    "        float d = turning - L;\n" +
                    "        return mix(\n" +
                    "          ((1. - turning) * (L - 1.)) / (1. - (L + shape * d)) + 1.,\n" +
                    "          (turning * L) / (1.0e-3 + (L + shape * d)),\n" +
                    "          step(0.0, d));\n" +
                    "}\n" +
                    "float reverseLight(float L) {\n" +
                    "        const float shape = 1.1726, turning = 0.1;\n" +
                    "        float d = turning - L;\n" +
                    "        return mix(\n" +
                    "          ((1. - turning) * (L - 1.)) / (1. - (L + shape * d)) + 1.,\n" +
                    "          (turning * L) / (1.0e-3 + (L + shape * d)),\n" +
                    "          step(0.0, d));\n" +
                    "}\n" +
                    "vec3 luv2rgb(vec3 c)\n" +
                    "{\n" +
                    "    float L = reverseLight(c.x);\n" +
                    "    float U = c.y;\n" +
                    "    float V = c.z;\n" +
                    "    float lim = chromaLimit(atan(V, U), L);\n" +
                    "    float len = length(vec2(U,V));\n" +
                    "    if(len > lim) {\n" +
                    "      lim /= len;\n" +
                    "      U *= lim;\n" +
                    "      V *= lim;\n" +
                    "    }\n" +
                    "    if (L <= 0.0001) {\n" +
                    "        return vec3(0.0);\n" +
                    "    } else if(L >= 0.9999) {\n" +
                    "        return vec3(1.0);\n" +
                    "    } else {\n" +
                    "      if (L <= 0.08) {\n" +
                    "        c.y = L / kappa;\n" +
                    "      } else {\n" +
                    "        c.y = (L + 0.16) / 1.16;\n" +
                    "        c.y *= c.y * c.y;\n" +
                    "      }\n" +
                    "    }\n" +
                    "    float iL = 1. / (13.0 * L);\n" +
                    "    float varU = U * iL + refUV.x;\n" +
                    "    float varV = V * iL + refUV.y;\n" +
                    "    c.x = 2.25 * varU * c.y / varV;\n" +
                    "    c.z = (3. / varV - 5.) * c.y - (c.x / 3.);\n" +
                    "    vec3 rgb = c * m;\n" +
                    "    return rgb;\n" +
                    "}\n" +
                    "void main()\n" +
                    "{\n" +
                    "  vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "  vec3 luv = rgb2luv(linear(tgt.rgb));\n" +
                    "  luv.x = forwardLight(clamp(luv.x + v_color.x - 0.5372549, 0.0, 1.0));\n" +
                    "  luv.yz = (luv.yz) + (v_color.yz);\n" +
                    "  gl_FragColor = vec4(sRGB(clamp(luv2rgb(luv), 0.0, 1.0)), v_color.a * tgt.a);\n" +
                    "}";

    /**
     * A simple shader that renders just like SpriteBatch's default shader, except that when it would draw white, it
     * instead draws red (which can be replaced in the shader code with a custom color, as with
     * {@link #makeSwappingBatch(float, float, float)}).
     * <br>
     * You can generate RGB colors using any of various methods in the {@code rgb} package, such as
     * {@link com.github.tommyettinger.colorful.rgb.ColorTools#rgb(float, float, float, float)}, or just by using normal
     * SpriteBatch and Color methods.
     * <br>
     * Meant for use with {@link #vertexShader}.
     */
    public static final String fragmentShaderSwapWhite =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    "void main()\n" +
                    "{\n" +
                    "   vec4 tgt = texture2D(u_texture, v_texCoords);\n" +
                    "   if(tgt.r + tgt.g + tgt.b == 3.0)\n" + // color of the pixel in the Texture must be white
                    "     gl_FragColor = vec4(   1.00, 0.00, 0.00   , tgt.a) * v_color;\n" +
                    "   else\n" +
                    "     gl_FragColor = tgt * v_color;\n" +
                    "}";

    /**
     * Prepares and returns a new SpriteBatch that uses {@link #vertexShader} and {@link #fragmentShaderSwapWhite}
     * from this class, making it able to render RGBA colors from libGDX or the rgb package. This also takes red, green,
     * and blue parameters, each between 0.0 and 1.0, which define the color that white will be replaced with.
     * <br>
     * You can generate RGB colors using any of various methods in the {@code rgb} package, such as
     * {@link com.github.tommyettinger.colorful.rgb.ColorTools#rgb(float, float, float, float)}, or just by using normal
     * SpriteBatch and Color methods.
     *
     * @param red   between 0.0 and 1.0, the red value of the color to replace white with.
     * @param green between 0.0 and 1.0, the green value of the color to replace white with.
     * @param blue  between 0.0 and 1.0, the blue value of the color to replace white with.
     * @return a freshly allocated SpriteBatch that will also have a new ShaderProgram for rendering RGB with contrast
     */
    public static SpriteBatch makeSwappingBatch(final float red, final float green, final float blue)
    {
        ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShaderSwapWhite.replace("   1.00, 0.00, 0.00   ", red + ", " + green + ", " + blue));
        if(!shader.isCompiled())
            throw new GdxRuntimeException("Couldn't compile shader: " + shader.getLog());
        return new SpriteBatch(1000, shader);
    }

}
