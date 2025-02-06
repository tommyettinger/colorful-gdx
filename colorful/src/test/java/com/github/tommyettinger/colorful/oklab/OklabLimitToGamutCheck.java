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

package com.github.tommyettinger.colorful.oklab;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.NumberUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.tommyettinger.colorful.Shaders;
import com.github.tommyettinger.colorful.TrigTools;

import static com.badlogic.gdx.Gdx.input;
import static com.github.tommyettinger.colorful.oklab.ColorTools.*;

public class OklabLimitToGamutCheck extends ApplicationAdapter {
    public static final int SCREEN_WIDTH = 256;
    public static final int SCREEN_HEIGHT = 256;
    private SpriteBatch batch;
    private Viewport screenView;
    private Texture blank;
    private long lastProcessedTime = 0L, startTime;
    private float layer = 0.5f;
    private float L = 0.5f, A = 0.5f, B = 0.5f, contrast = 0.5f;

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Oklab Gamut Demo");
        config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
        config.setIdleFPS(10);
        config.setForegroundFPS(60);
        config.useVsync(true);

        final OklabLimitToGamutCheck app = new OklabLimitToGamutCheck();
        new Lwjgl3Application(app, config);
    }

    @Override
    public void create() {
        startTime = TimeUtils.millis();
        Pixmap blank = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        blank.drawPixel(0, 0, 0x7F7F81FF);
        this.blank = new Texture(blank);
        batch = new SpriteBatch();
        String vertexShader = Shaders.vertexShader;
//        String fragmentShader = Shaders.fragmentShaderOklab;
        String fragmentShader =
                "#ifdef GL_ES\n" +
                        "#define LOWP lowp\n" +
                        "precision mediump float;\n" +
                        "#else\n" +
                        "#define LOWP \n" +
                        "#endif\n" +
                        "varying vec2 v_texCoords;\n" +
                        "varying LOWP vec4 v_color;\n" +
                        "uniform sampler2D u_texture;\n" +
//                        "uniform float u_flash;\n" +
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
                        "  lab = " +
                        "                 mat3(+4.0767245293, -1.2681437731, -0.0041119885, -3.3072168827, +2.6093323231, -0.7034763098, +0.2307590544, -0.3411344290, +1.7068625689) *\n" +
                        "                 (lab * lab * lab);" +
                        "  vec3 back = clamp(lab, 0.0, 1.0);\n" +
//                        "  if(any(notEqual(back, lab))) gl_FragColor = vec4(u_flash + sqrt(back), v_color.a * tgt.a);\n" +
//                        "  else gl_FragColor = vec4(sqrt(back), v_color.a * tgt.a);\n" +
                        "  gl_FragColor = vec4(sqrt(back), v_color.a * tgt.a);\n" +
                        "}";

        ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShader);
        if (!shader.isCompiled()) throw new IllegalArgumentException("Error compiling shader: " + shader.getLog());
        batch.setShader(shader);
        screenView = new ScreenViewport();
        screenView.getCamera().position.set(SCREEN_WIDTH * 0.5f, SCREEN_HEIGHT * 0.5f, 0);
        screenView.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.enableBlending();

        startTime = -1L;
        final int frameCount = 120;
        Array<Pixmap> pixmaps = new Array<>(frameCount);
        for (int i = 0; i < frameCount; i++) {
            layer = i / (frameCount - 1f);
            renderInternal();
//             this gets a screenshot of the current window and adds it to the Array of Pixmap.
            pixmaps.add(Pixmap.createFromFrameBuffer(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        }
//
//
////// AnimatedGif is from anim8; this code uses the predefined Haltonic palette, which has 255 colors
////// plus transparent, and seems to be more accurate than any attempts to analyze an image with almost every color.
//        AnimatedGif gif = new AnimatedGif();
////        gif.setDitherAlgorithm(Dithered.DitherAlgorithm.GRADIENT_NOISE); // this is better than it sounds
////        gif.setDitherAlgorithm(Dithered.DitherAlgorithm.SCATTER); // this is pretty fast to compute, and also good
//        gif.setDitherAlgorithm(Dithered.DitherAlgorithm.PATTERN); // this is very slow, but high-quality
////        gif.setDitherAlgorithm(Dithered.DitherAlgorithm.NONE); // this should be dithered before usage
//        gif.palette = new PaletteReducer();
////        gif.palette = new PaletteReducer(pixmaps);
////        // 24 is how many frames per second the animated GIF should play back at.
//        gif.write(Gdx.files.local("OklabGamut.gif"), pixmaps, 24);
//
        /*
//// AnimatedPNG uses full-color, so it doesn't involve dithering or color reduction at all.
        AnimatedPNG png = new AnimatedPNG();
//// 24 is how many frames per second the animated PNG should play back at.
        png.write(Gdx.files.local("OklabGamutGPU.png"), pixmaps, 24);
*/
        layer = 0.5f;
    }

    /**
     * @param value any float
     * @return a float from -1f (inclusive) to 1f (inclusive)
     */
    public static float zigzag(float value)
    {
        int floor = (value >= 0f ? (int) value : (int) value - 1);
        value -= floor;
        floor = (-(floor & 1) | 1);
        return value * (floor << 1) - floor;
    }

//    public static float limitToGamut(float L, float A, float B, float alpha) {
//        L = Math.min(Math.max(L, 0f), 1f);
//        A = Math.min(Math.max(A, 0f), 1f);
//        B = Math.min(Math.max(B, 0f), 1f);
//        alpha = Math.min(Math.max(alpha, 0f), 1f);
//        final float A2 = (A - 0.5f);
//        final float B2 = (B - 0.5f);
//        final float hue = TrigTools.atan2_(B2, A2);
//        final int idx = (int) (L * 255.999f) << 8 | (int)(256f * hue);
//        final float dist = GAMUT_DATA[idx] * 0x1p-8f;
//        if(dist >= (float) Math.sqrt(A2 * A2 + B2 * B2))
//            return ColorTools.oklab(L, A, B, alpha);
//        return NumberUtils.intBitsToFloat(
//                (int) (alpha * 127.999f) << 25 |
//                        (int) (TrigTools.sin_(hue) * dist * 255.999f + 127.999f) << 16 |
//                        (int) (TrigTools.cos_(hue) * dist * 255.999f + 127.999f) << 8 |
//                        (int) (L * 255.999f));
//    }
    public static float limitToGamut(float L, float A, float B, float alpha) {
        L = Math.min(Math.max(L, 0f), 1f);
        A = Math.min(Math.max(A, 0f), 1f);
        B = Math.min(Math.max(B, 0f), 1f);
        alpha = Math.min(Math.max(alpha, 0f), 1f);
        final float A2 = (A - 0.5f);
        final float B2 = (B - 0.5f);
        final float hue = TrigTools.atan2Turns(B2, A2);
        final int idx = (int) (L * 255.999f) << 8 | (int)(256f * hue);
        final float dist = getRawGamutValue(idx) * 0.5f;
        if(dist * dist * 0x1p-16f >= (A2 * A2 + B2 * B2))
            return //(TimeUtils.millis() >>> 9 & 1L) == 0L ? Palette.LEAD :
                    ColorTools.oklab(L, A, B, alpha);
        return NumberUtils.intBitsToFloat(
                (int) (alpha * 127.999f) << 25 |
                        (int) (TrigTools.sinTurns(hue) * dist + 127.5f) << 16 |
                        (int) (TrigTools.cosTurns(hue) * dist + 127.5f) << 8 |
                        (int) (L * 255f));
    }
//    public static boolean inGamut(final float packed)
//    {
//        final int decoded = NumberUtils.floatToRawIntBits(packed);
//        final float A = ((decoded >>> 8 & 0xff) - 127.5f) / 255f;
//        final float B = ((decoded >>> 16 & 0xff) - 127.5f) / 255f;
//        final float g = ColorTools.getRawGamutValue((decoded & 0xff) << 8 | (int)(256f * TrigTools.atan2_(B, A)));
//        return g * g * 0x1p-18 + 0x1p-14 >= (A * A + B * B);
//    }

    public static boolean inGamut(final float packed) {
        final int decoded = NumberUtils.floatToRawIntBits(packed);
        double L = (decoded & 0xFF) / 255f;
        double A = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
        double B = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
        //reverseLight() for double
        L = (L - 1.0) / (1.0 + L * 0.75) + 1.0;
        //forwardLight() for double
//        L = (L - 1.0) / (1.0 - L * 0.4285714) + 1.0;

        double l = (L + +0.3963377774 * A + +0.2158037573 * B);
        l *= l * l;
        double m = (L + -0.1055613458 * A + -0.0638541728 * B);
        m *= m * m;
        double s = (L + -0.0894841775 * A + -1.2914855480 * B);
        s *= s * s;

        final double r = +4.0767245293 * l - 3.3072168827 * m + 0.2307590544 * s;
        if(r < 0.0 || r > 1.0) return false;
        final double g = -1.2681437731 * l + 2.6093323231 * m - 0.3411344290 * s;
        if(g < 0.0 || g > 1.0) return false;
        final double b = -0.0041119885 * l - 0.7034763098 * m + 1.7068625689 * s;
        return (b >= 0.0 && b <= 1.0);
    }

        @Override
    public void render() {
        handleInput();
        startTime = System.currentTimeMillis();
//        layer = zigzag(TimeUtils.timeSinceMillis(startTime) * 0x1p-13f) * 0.5f + 0.5f;
        renderInternal();
    }
    
    public void renderInternal() {
        Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(screenView.getCamera().combined);
        batch.setColor(0.5f, 0.5f, 0.5f, 1f);
        batch.begin();
        batch.draw(blank, 0, 0, 512, 512);
        batch.setColor(layer, 0.5f, 0.5f, 1f);
//        batch.getShader().setUniformf("u_flash", startTime >>> 9 & 1);
        batch.draw(blank, 254.75f, 254.75f, 1.5f, 1.5f);
        for (int x = 0; x < 256; x++) {
            for (int y = 0; y < 256; y++) {
//                if((startTime >>> 9 & 1) == 0) {
//                    if (Math.sqrt((x * 0x1p-7f - 1f) * (x * 0x1p-7f - 1f) + (y * 0x1p-7f - 1f) * (y * 0x1p-7f - 1f)) < chromaLimit(TrigTools.atan2_(y - 127.5f, x - 127.5f), layer))
//                        batch.setPackedColor(Palette.SILVER);
//                    else
//                        batch.setPackedColor(Palette.LEAD);
//                }
//                else {
//                    float color = oklab(layer, x / 255f, y / 255f, 1f);
//                    if (!ColorTools.inGamut(color)) {
//                        batch.setPackedColor(Palette.LEAD);
//                    } else
//                        batch.setPackedColor(color);
//                }

//                float color = oklab(layer, x / 255f, y / 255f, 1f), sat = oklabSaturation(color);
//                if (Math.sqrt((x * 0x1p-7f - 1f) * (x * 0x1p-7f - 1f) + (y * 0x1p-7f - 1f) * (y * 0x1p-7f - 1f)) < chromaLimit(TrigTools.atan2_(y - 127.5f, x - 127.5f), layer)) {
//                    if (sat > 1) color = Palette.RED;
//                    else if (sat < 0) color = Palette.AIR_FORCE_BLUE;
//                    else color = oklab(sat, 0.5f, 0.5f, 1f);
//                }
//                else
//                    color = Palette.BLACK;
//                batch.setPackedColor(color);

                float limited = ColorTools.limitToGamut(layer, x / 255f, y / 255f, 1f),
                        regular = ColorTools.oklab(layer, x / 255f, y / 255f, 1f);
                if(limited != regular)
                    batch.setPackedColor(Palette.LEAD);
                else
                    batch.setPackedColor(limited);
                batch.draw(blank, x, y, 1f, 1f);
//
//                batch.setPackedColor(oklab(layer, x / 255f, y / 255f, 1f));
//                batch.draw(blank, x, y, 1f, 1f);
            }
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        screenView.update(width, height);
        screenView.getCamera().position.set(width * 0.5f, height * 0.5f, 0f);
        screenView.getCamera().update();
    }

    public void handleInput() {
        if (input.isKeyPressed(Input.Keys.Q) || input.isKeyPressed(Input.Keys.ESCAPE)) //quit
            Gdx.app.exit();
        else if (TimeUtils.timeSinceMillis(lastProcessedTime) > 150) {
            lastProcessedTime = TimeUtils.millis();
            if (input.isKeyPressed(Input.Keys.RIGHT) || input.isKeyPressed(Input.Keys.UP)) {
                layer = MathUtils.clamp(layer + 0x1p-7f, 0f, 1f);
            } else if (input.isKeyPressed(Input.Keys.LEFT) || input.isKeyPressed(Input.Keys.DOWN)) {
                layer = MathUtils.clamp(layer - 0x1p-7f, 0f, 1f);
            } else if (input.isKeyPressed(Input.Keys.R)) // random
            {
                layer = MathUtils.random();
            } else if (input.isKeyPressed(Input.Keys.P)) // print
                System.out.println("Using layer=" + layer + " .");
            else if (input.isKeyPressed(Input.Keys.L)) //light
                L = MathUtils.clamp(L + 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.D)) //dark
                L = MathUtils.clamp(L - 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.BACKSPACE)) //reset
            {
                layer = 0.5f;
                L = 0.5f;
                A = 0.5f;
                B = 0.5f;
                contrast = 0.5f;
            }
        }
    }
}
