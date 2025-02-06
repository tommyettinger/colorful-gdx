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

package com.github.tommyettinger.colorful.hsi;

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
import com.github.tommyettinger.anim8.AnimatedGif;
import com.github.tommyettinger.anim8.AnimatedPNG;
import com.github.tommyettinger.anim8.Dithered;
import com.github.tommyettinger.anim8.PaletteReducer;
import com.github.tommyettinger.colorful.TrigTools;

import static com.badlogic.gdx.Gdx.input;

public class HSIGamutDemo extends ApplicationAdapter {
    public static final int SCREEN_WIDTH = 512;
    public static final int SCREEN_HEIGHT = 512;
    private SpriteBatch batch;
    private Viewport screenView;
    private Texture blank;
    private long lastProcessedTime = 0L, startTime;
    private float layer = 0.5f;
    private float intens = 0.5f, protan = 0.5f, tritan = 0.5f, contrast = 0.5f;

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("HSI Wheel Demo");
        config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
        config.setIdleFPS(10);
        config.useVsync(true);

        final HSIGamutDemo app = new HSIGamutDemo();
        new Lwjgl3Application(app, config);
    }

    @Override
    public void create() {
        startTime = TimeUtils.millis();
        Pixmap b = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        b.drawPixel(0, 0, 0x808080FF);
        blank = new Texture(b);
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
                        "const vec3 yellow  = vec3( 0.16155326f,0.020876605f,-0.26078433f );\n" +
                        "const vec3 magenta = vec3(-0.16136102f,0.122068435f,-0.070396f   );\n" +
                        "const vec3 cyan    = vec3( 0.16420607f,0.3481738f,   0.104959644f);\n" +
                        "void main()\n" +
                        "{\n" +
                        "    vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                        "    vec3 ipt = vec3(v_color.r * 0.9998 - 0.4999, v_color.gb - 0.5);\n" +
                        "    float crMid = dot(cyan.yz, ipt.yz);\n" +
                        "    float crScale = (ipt.x - 0.5 * sign(crMid)) * cyan.x / -crMid;\n" +
                        "    float mgMid = dot(magenta.yz, ipt.yz);\n" +
                        "    float mgScale = (ipt.x + 0.5 * sign(mgMid)) * magenta.x / -mgMid;\n" +
                        "    float ybMid = dot(yellow.yz, ipt.yz);\n" +
                        "    float ybScale = (ipt.x - 0.5 * sign(ybMid)) * yellow.x / -ybMid;\n" +
                        "    float scale = 4.0 * min(crScale, min(mgScale, ybScale));\n" +
                        "    ipt.yz *= scale * length(ipt.yz) / cos(3.14159 * ipt.x);\n" +
                        "    ipt.x += 0.5;\n" +
                        "    vec3 back = mat3(0.999779, 1.00015, 0.999769, 1.07094, -0.377744, 0.0629496, 0.324891, 0.220439, -0.809638) * ipt;\n" +
                        "    gl_FragColor = vec4(clamp(back, 0.0, 1.0), v_color.a * tgt.a);\n" +
                        "    if(any(notEqual(back, gl_FragColor.rgb))) discard;\n" +
                        "}";
        /*
        final float crMid = cyan.y * original.y + cyan.z * original.z;
        final float crScale = (original.x - 0.5f + (NumberUtils.floatToRawIntBits(crMid) >>> 31)) * cyan.x / -crMid;
        final float mgMid = magenta.y * original.y + magenta.z * original.z;
        final float mgScale = (original.x - 0.5f + (NumberUtils.floatToRawIntBits(mgMid) >>> 31)) * magenta.x / -mgMid;
        final float ybMid = yellow.y * original.y + yellow.z * original.z;
        final float ybScale = (original.x - 0.5f + (NumberUtils.floatToRawIntBits(ybMid) >>> 31)) * yellow.x / -ybMid;
        final float scale = Math.max(crScale, Math.max(mgScale, ybScale));
        final float d = 2f * Vector2.len(original.y * scale, original.z * scale) / MathUtils.cos(MathUtils.PI * original.x);
        return buffer.set(original.x, original.y * d, original.z * d);

         */
        ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShader);
        if (!shader.isCompiled()) throw new IllegalArgumentException("Error compiling shader: " + shader.getLog());
        batch.setShader(shader);
//        basicBatch = new SpriteBatch();
        screenView = new ScreenViewport();
        screenView.getCamera().position.set(SCREEN_WIDTH * 0.5f, SCREEN_HEIGHT * 0.5f, 0);
        screenView.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.enableBlending();
        final int frameCount = 120;
        Array<Pixmap> pixmaps = new Array<>(frameCount);
        for (int i = 0; i < frameCount; i++) {
            layer = i / (frameCount - 1f);
            renderInternal();
            // this gets a screenshot of the current window and adds it to the Array of Pixmap.
            pixmaps.add(Pixmap.createFromFrameBuffer(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        }
//// AnimatedGif is from anim8; this code uses the predefined Haltonic palette, which has 255 colors
//// plus transparent, and seems to be more accurate than any attempts to analyze an image with almost every color.
        AnimatedGif gif = new AnimatedGif();
//        gif.setDitherAlgorithm(Dithered.DitherAlgorithm.GRADIENT_NOISE); // this is better than it sounds
//        gif.setDitherAlgorithm(Dithered.DitherAlgorithm.SCATTER); // this is pretty fast to compute, and also good
        gif.setDitherAlgorithm(Dithered.DitherAlgorithm.PATTERN); // this is very slow, but high-quality
//        gif.setDitherAlgorithm(Dithered.DitherAlgorithm.NONE); // should be dithered before
        gif.palette = new PaletteReducer();
//        gif.palette.analyze(pixmaps, 500); // this can be used to attempt to analyze the image to get a palette...
//        // 24 is how many frames per second the animated GIF should play back at.
        gif.write(Gdx.files.local("SphereIPTGamut.gif"), pixmaps, 24);

        //// AnimatedPNG uses full-color, so it doesn't involve dithering or color reduction at all.
        AnimatedPNG png = new AnimatedPNG();
// 24 is how many frames per second the animated PNG should play back at.
        png.write(Gdx.files.local("SphereIPTGamut.png"), pixmaps, 24);
    }


    @Override
    public void render() {
        handleInput();
        layer = TrigTools.acosTurns(TrigTools.sinTurns(TimeUtils.timeSinceMillis(startTime) * 0x1p-13f)) * 2f;
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
        batch.draw(blank, 254.75f, 254.75f, 1.5f, 1.5f);
        for (int x = 0; x < 512; x++) {
            for (int y = 0; y < 512; y++) {
                batch.setColor(layer, x * 0x1p-9f, y * 0x1p-9f, 1f);
                batch.draw(blank, x, y, 1f, 1f);
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
                System.out.println("Using layer=" + layer
                        +", and using tweak with luma="+ intens
                        + ",warm="+ protan + ",mild="+ tritan +",contrast="+contrast + " .");
            else if (input.isKeyPressed(Input.Keys.L)) //light
                intens = MathUtils.clamp(intens + 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.D)) //dark
                intens = MathUtils.clamp(intens - 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.W)) //warm
                protan = MathUtils.clamp(protan + 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.C)) //cool
                protan = MathUtils.clamp(protan - 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.M)) //mild
                tritan = MathUtils.clamp(tritan + 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.B)) //bold
                tritan = MathUtils.clamp(tritan - 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.S)) //sharp contrast
                contrast = MathUtils.clamp(contrast + 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.F)) //fuzzy contrast
                contrast = MathUtils.clamp(contrast - 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.BACKSPACE)) //reset
            {
                intens = 0.5f;
                protan = 0.5f;
                tritan = 0.5f;
                contrast = 0.5f;
            }
        }
    }
}
