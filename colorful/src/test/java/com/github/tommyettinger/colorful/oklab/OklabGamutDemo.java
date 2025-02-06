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
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.tommyettinger.anim8.*;
import com.github.tommyettinger.colorful.TrigTools;

import static com.badlogic.gdx.Gdx.input;

public class OklabGamutDemo extends ApplicationAdapter {
    private static final boolean WRITING = true;
    public static final int SCREEN_WIDTH = 128;
    public static final int SCREEN_HEIGHT = 128;
    private SpriteBatch batch;
    private Viewport screenView;
    private Texture blank;
    private boolean paused = false;
    private long lastProcessedTime = 0L, startTime;
    private float layer = 0.5f;
    private float L = 0.5f, A = 0.5f, B = 0.5f, contrast = 0.5f;

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Oklab Gamut Demo");
        config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
        config.setIdleFPS(10);
        config.setTransparentFramebuffer(true);
        config.setForegroundFPS(60);
        config.useVsync(true);

        final OklabGamutDemo app = new OklabGamutDemo();
        new Lwjgl3Application(app, config);
    }

    @Override
    public void create() {
        startTime = TimeUtils.millis();
        Pixmap blank = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        blank.drawPixel(0, 0, 0x808080FF);
        this.blank = new Texture(blank);
        batch = new SpriteBatch();
        String vertexShader = batch.getShader().getVertexShaderSource();
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
                        "  if(any(notEqual(back, lab))) discard;\n" +
                        "  gl_FragColor = vec4(sqrt(back), v_color.a * tgt.a);\n" +
                        "}";
        ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShader);
        if (!shader.isCompiled()) throw new IllegalArgumentException("Error compiling shader: " + shader.getLog());
        batch.setShader(shader);
//        basicBatch = new SpriteBatch();
        screenView = new ScreenViewport();
        screenView.getCamera().position.set(SCREEN_WIDTH * 0.5f, SCREEN_HEIGHT * 0.5f, 0);
        screenView.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.enableBlending();

        if (WRITING) {
            final int frameCount = 120;
            Array<Pixmap> pixmaps = new Array<>(frameCount);
            for (int i = 0; i < frameCount; i++) {
                layer = i / (frameCount - 1f);
                renderInternal();
                // this gets a screenshot of the current window and adds it to the Array of Pixmap.
                pixmaps.add(Pixmap.createFromFrameBuffer(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
            }

//
//// AnimatedGif is from anim8; this code uses the predefined Haltonic palette, which has 255 colors
//// plus transparent, and seems to be more accurate than any attempts to analyze an image with almost every color.
            AnimatedGif gif = new AnimatedGif();
            gif.setDitherAlgorithm(Dithered.DitherAlgorithm.WREN); // the best we have
//        gif.setDitherAlgorithm(Dithered.DitherAlgorithm.GRADIENT_NOISE); // this is better than it sounds
//        gif.setDitherAlgorithm(Dithered.DitherAlgorithm.SCATTER); // this is pretty fast to compute, and also good
//        gif.setDitherAlgorithm(Dithered.DitherAlgorithm.NEUE); // this is fast and great with smooth gradients, but the temporal dithering looks weird here.
//        gif.setDitherAlgorithm(Dithered.DitherAlgorithm.PATTERN); // this is very slow, but high-quality
//        gif.setDitherAlgorithm(Dithered.DitherAlgorithm.NONE); // this should be dithered before usage
//        gif.setDitherAlgorithm(Dithered.DitherAlgorithm.NEUE); // this is the current default; fairly high quality
            gif.setDitherStrength(0.5f);
            gif.fastAnalysis = false;
            gif.palette = new QualityPalette();
//        // 24 is how many frames per second the animated GIF should play back at.
            gif.write(Gdx.files.local("OklabGamut.gif"), pixmaps, 24);

//// AnimatedPNG uses full-color, so it doesn't involve dithering or color reduction at all.
            AnimatedPNG png = new AnimatedPNG();
//// 24 is how many frames per second the animated PNG should play back at.
            png.write(Gdx.files.local("OklabGamut.png"), pixmaps, 24);
        }
//        PixmapIO.PNG still = new PixmapIO.PNG();
//        int idx = 0;
//        for (Pixmap pm : pixmaps){
//            try {
//                still.write(Gdx.files.local("oklab_" + (idx++) + ".png"), pm);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }


//        float minA = 1000f, minB = 1000f, maxA = -1000f, maxB = -1000f, ok, A, B, maxChroma = 0f, chroma;
//        int c = 0xFF;
//        for (int r = 0; r < 256; r++, c += 0x01000000) {
//            int c2 = c;
//            for (int g = 0; g < 256; g++, c2 += 0x00010000) {
//                ok = ColorTools.fromRGBA8888(c2);
////                if(!ColorTools.inGamut(ok)) {
////                    System.out.printf("0x%08X is out of gamut!\n", c2);
////                    System.out.println(ColorTools.getRawGamutValue((int)(ColorTools.channelL(ok) * 255.999f) << 8
////                            | (int)(TrigTools.atan2_(ColorTools.channelB(ok) - 0.5f, ColorTools.channelA(ok) - 0.5f) * 256f)));
////                }
//                ok = ColorTools.limitToGamut(ok);
//
//                A = ColorTools.channelA(ok);
//                B = ColorTools.channelB(ok);
//                minA = Math.min(A, minA);
//                minB = Math.min(B, minB);
//                maxA = Math.max(A, maxA);
//                maxB = Math.max(B, maxB);
//                maxChroma = Math.max(maxChroma, chroma = ColorTools.chroma(ok));
//                if(chroma > 0.6322) System.out.printf("RGBA 0x%08X has chroma %1.5f\n", c2, chroma);
//            }
//        }
//        c = 0xFFFF;
//        for (int r = 0; r < 256; r++, c += 0x01000000) {
//            int c2 = c;
//            for (int g = 0; g < 256; g++, c2 += 0x00010000) {
//                ok = ColorTools.fromRGBA8888(c2);
//                ok = ColorTools.limitToGamut(ok);
//                A = ColorTools.channelA(ok);
//                B = ColorTools.channelB(ok);
//                minA = Math.min(A, minA);
//                minB = Math.min(B, minB);
//                maxA = Math.max(A, maxA);
//                maxB = Math.max(B, maxB);
//                maxChroma = Math.max(maxChroma, chroma = ColorTools.chroma(ok));
//                if(chroma > 0.6322) System.out.printf("RGBA 0x%08X has chroma %1.5f\n", c2, chroma);
//            }
//        }
//        c = 0xFF;
//        for (int r = 0; r < 256; r++, c += 0x01000000) {
//            int c2 = c;
//            for (int b = 0; b < 256; b++, c2 += 0x00000100) {
//                ok = ColorTools.fromRGBA8888(c2);
//                ok = ColorTools.limitToGamut(ok);
//                A = ColorTools.channelA(ok);
//                B = ColorTools.channelB(ok);
//                minA = Math.min(A, minA);
//                minB = Math.min(B, minB);
//                maxA = Math.max(A, maxA);
//                maxB = Math.max(B, maxB);
//                maxChroma = Math.max(maxChroma, chroma = ColorTools.chroma(ok));
//                if(chroma > 0.6322) System.out.printf("RGBA 0x%08X has chroma %1.5f\n", c2, chroma);
//            }
//        }
//        c = 0xFF00FF;
//        for (int r = 0; r < 256; r++, c += 0x01000000) {
//            int c2 = c;
//            for (int b = 0; b < 256; b++, c2 += 0x00000100) {
//                ok = ColorTools.fromRGBA8888(c2);
//                ok = ColorTools.limitToGamut(ok);
//                A = ColorTools.channelA(ok);
//                B = ColorTools.channelB(ok);
//                minA = Math.min(A, minA);
//                minB = Math.min(B, minB);
//                maxA = Math.max(A, maxA);
//                maxB = Math.max(B, maxB);
//                maxChroma = Math.max(maxChroma, chroma = ColorTools.chroma(ok));
//                if(chroma > 0.6322) System.out.printf("RGBA 0x%08X has chroma %1.5f\n", c2, chroma);
//            }
//        }
//        c = 0xFF;
//        for (int g = 0; g < 256; g++, c += 0x00010000) {
//            int c2 = c;
//            for (int b = 0; b < 256; b++, c2 += 0x00000100) {
//                ok = ColorTools.fromRGBA8888(c2);
//                ok = ColorTools.limitToGamut(ok);
//                A = ColorTools.channelA(ok);
//                B = ColorTools.channelB(ok);
//                minA = Math.min(A, minA);
//                minB = Math.min(B, minB);
//                maxA = Math.max(A, maxA);
//                maxB = Math.max(B, maxB);
//                maxChroma = Math.max(maxChroma, chroma = ColorTools.chroma(ok));
//                if(chroma > 0.6322) System.out.printf("RGBA 0x%08X has chroma %1.5f\n", c2, chroma);
//            }
//        }
//        c = 0xFF0000FF;
//        for (int g = 0; g < 256; g++, c += 0x00010000) {
//            int c2 = c;
//            for (int b = 0; b < 256; b++, c2 += 0x00000100) {
//                ok = ColorTools.fromRGBA8888(c2);
//                ok = ColorTools.limitToGamut(ok);
//                A = ColorTools.channelA(ok);
//                B = ColorTools.channelB(ok);
//                minA = Math.min(A, minA);
//                minB = Math.min(B, minB);
//                maxA = Math.max(A, maxA);
//                maxB = Math.max(B, maxB);
//                maxChroma = Math.max(maxChroma, chroma = ColorTools.chroma(ok));
//                if(chroma > 0.6322) System.out.printf("RGBA 0x%08X has chroma %1.5f\n", c2, chroma);
//            }
//        }
//        System.out.printf("minimum A: %1.5f\nmaximum A: %1.5f\nminimum B: %1.5f\nmaximum B: %1.5f\nmaximum chroma: %1.5f\n",
//                minA - 0.5f, maxA - 0.5f, minB - 0.5f, maxB - 0.5f, maxChroma);

        ////Prints:
        //minimum A: -0.11961
        //maximum A: 0.13529
        //minimum B: -0.15882
        //maximum B: 0.09608

        //// with limitToGamut():
        //minimum A: -0.11961
        //maximum A: 0.13529
        //minimum B: -0.15882
        //maximum B: 0.09608

//        float color = SimplePalette.APRICOT; //FFA828FF, L 0.8156863, A 0.52156866, B 0.5764706,
//        System.out.printf("%02X, %02X, %02X\n", ColorTools.redInt(color), ColorTools.greenInt(color), ColorTools.blueInt(color));
//        System.out.printf("%1.4f, %1.4f, %1.4f\n", ColorTools.channelL(color), ColorTools.channelA(color), ColorTools.channelB(color));
//        System.out.println("To RGBA...");
//        float rgba = ColorTools.toRGBA(color);
//        int abgr = NumberUtils.floatToRawIntBits(rgba);
//        System.out.printf("%02X, %02X, %02X\n", abgr & 0xFF, abgr >>> 8 & 0xFF, abgr >>> 16 & 0xFF);
//        System.out.println("And back...");
//        color = ColorTools.fromRGBA(rgba);
//        System.out.printf("%02X, %02X, %02X\n", ColorTools.redInt(color), ColorTools.greenInt(color), ColorTools.blueInt(color));
//        System.out.printf("%1.4f, %1.4f, %1.4f\n", ColorTools.channelL(color), ColorTools.channelA(color), ColorTools.channelB(color));
    }


    @Override
    public void render() {
        handleInput();
        if(!paused)
            layer = TrigTools.acosTurns(TrigTools.sinTurns(TimeUtils.timeSinceMillis(startTime) * 0x1p-13f)) * 2f;
        renderInternal();
    }
    
    public void renderInternal() {
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 0f);
//        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(screenView.getCamera().combined);
        batch.setColor(0.5f, 0.5f, 0.5f, 1f);
        batch.begin();
//        batch.draw(blank, 0, 0, 512, 512);
//        batch.setColor(layer, 0.5f, 0.5f, 1f);
//        batch.draw(blank, 254.75f, 254.75f, 1.5f, 1.5f);
        for (int x = 0; x < 256; x++) {
            for (int y = 0; y < 256; y++) {
                batch.setColor(layer, x * 0x1p-8f, y * 0x1p-8f, 1f);
                batch.draw(blank, x - 64f, y - 64f, 1f, 1f);
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
        else if (input.isKeyPressed(Input.Keys.ANY_KEY) && TimeUtils.timeSinceMillis(lastProcessedTime) > 150) {
            lastProcessedTime = TimeUtils.millis();
            if(input.isKeyPressed(Input.Keys.SPACE)){
                paused = !paused;
            } else if (input.isKeyPressed(Input.Keys.RIGHT) || input.isKeyPressed(Input.Keys.UP)) {
                layer = MathUtils.clamp(layer + 0x1p-7f, 0f, 1f);
            } else if (input.isKeyPressed(Input.Keys.LEFT) || input.isKeyPressed(Input.Keys.DOWN)) {
                layer = MathUtils.clamp(layer - 0x1p-7f, 0f, 1f);
            } else if (input.isKeyPressed(Input.Keys.R)) // random
            {
                layer = MathUtils.random();
            } else if (input.isKeyPressed(Input.Keys.P)) // print
                System.out.println("Using layer=" + layer
                        +", and using tweak with L="+ L
                        + ",A="+ A + ",B="+ B +",contrast="+contrast + " .");
            else if (input.isKeyPressed(Input.Keys.L)) //light
                L = MathUtils.clamp(L + 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.D)) //dark
                L = MathUtils.clamp(L - 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.W)) //warm
                A = MathUtils.clamp(A + 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.C)) //cool
                A = MathUtils.clamp(A - 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.M)) //mild
                B = MathUtils.clamp(B + 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.B)) //bold
                B = MathUtils.clamp(B - 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.S)) //sharp contrast
                contrast = MathUtils.clamp(contrast + 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.F)) //fuzzy contrast
                contrast = MathUtils.clamp(contrast - 0x3p-7f, 0f, 1f);
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
