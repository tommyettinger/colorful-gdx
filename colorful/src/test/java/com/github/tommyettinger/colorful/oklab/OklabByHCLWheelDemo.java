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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.utils.UIUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.tommyettinger.anim8.*;
import com.github.tommyettinger.colorful.Shaders;
import com.github.tommyettinger.colorful.TrigTools;

import static com.badlogic.gdx.Gdx.input;

public class OklabByHCLWheelDemo extends ApplicationAdapter {
    public static final int SCREEN_WIDTH = 512;
    public static final int SCREEN_HEIGHT = 512;
    public static final boolean WRITING = true;
    private SpriteBatch batch;
    private Viewport screenView;
    private BitmapFont font;
    private Texture blank;
    private long lastProcessedTime = 0L, startTime;
    private float layer = 0.5f;
    private float shape = 1.75f, turning = 0.65f;
    private GridPoint2 center = new GridPoint2(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);

//    private PoissonDisk poisson = new PoissonDisk(SCREEN_WIDTH, SCREEN_HEIGHT, 5, new FourWheelRandom());

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Color Wheel Demo");
        config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
        config.setIdleFPS(10);
        config.useVsync(true);
        config.disableAudio(true);
//        config.setResizable(false);

        final OklabByHCLWheelDemo app = new OklabByHCLWheelDemo();
        new Lwjgl3Application(app, config);
    }

    @Override
    public void create() {
        startTime = TimeUtils.millis();
        Pixmap b = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        b.drawPixel(0, 0, 0x7F7F81FF);
        blank = new Texture(b);
        font = new BitmapFont(Gdx.files.internal("font.fnt"));
        font.setColor(1f, 0.5f, 0.5f, 1f);
        ShaderProgram shader = new ShaderProgram(Shaders.vertexShader, Shaders.fragmentShaderOklab);
        if (!shader.isCompiled())
            System.out.println(shader.getLog());
        batch = new SpriteBatch(2000, shader);
        screenView = new ScreenViewport();
        screenView.getCamera().position.set(SCREEN_WIDTH * 0.5f, SCREEN_HEIGHT * 0.5f, 0);
        screenView.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        if (WRITING) {
            final int frameCount = 256;
            Array<Pixmap> pixmaps = new Array<>(frameCount);
            for (int i = 0; i < frameCount; i++) {
                layer = i * 2f / (frameCount - 1f);
                layer = Math.abs(layer - ((int) (layer + 16384.5) - 16384)) * 2f; // 0-1 triangle wave

                renderInternal();
                // this gets a screenshot of the current window and adds it to the Array of Pixmap.
                pixmaps.add(Pixmap.createFromFrameBuffer(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
            }


//// AnimatedGif is from anim8; this code uses the predefined Haltonic palette, which has 255 colors
//// plus transparent, and seems to be more accurate than any attempts to analyze an image with almost every color.
            AnimatedGif gif = new AnimatedGif();
            gif.setDitherAlgorithm(Dithered.DitherAlgorithm.WREN); // the best we have
//            gif.setDitherAlgorithm(Dithered.DitherAlgorithm.DODGY); // might be better with complicated color stuff
//            gif.setDitherAlgorithm(Dithered.DitherAlgorithm.GRADIENT_NOISE); // this is better than it sounds
//        gif.setDitherAlgorithm(Dithered.DitherAlgorithm.SCATTER); // this is pretty fast to compute, and also good
//        gif.setDitherAlgorithm(Dithered.DitherAlgorithm.NEUE); // this is fast and great with smooth gradients, but the temporal dithering looks weird here.
//        gif.setDitherAlgorithm(Dithered.DitherAlgorithm.PATTERN); // this is very slow, but high-quality
//        gif.setDitherAlgorithm(Dithered.DitherAlgorithm.NONE); // this should be dithered before usage
//        gif.setDitherAlgorithm(Dithered.DitherAlgorithm.NEUE); // this is the current default; fairly high quality
            gif.setDitherStrength(0.5f);
            gif.fastAnalysis = false;
            gif.palette = new QualityPalette();
//        // 24 is how many frames per second the animated GIF should play back at.
            gif.write(Gdx.files.local("OklabByHSL.gif"), pixmaps, 24);

//// AnimatedPNG uses full-color, so it doesn't involve dithering or color reduction at all.
            AnimatedPNG png = new AnimatedPNG();
            png.setCompression(9);
//// 24 is how many frames per second the animated PNG should play back at.
            png.write(Gdx.files.local("OklabByHSL.png"), pixmaps, 24);
        }
    }

    @Override
    public void render() {
        handleInput();
        layer = TimeUtils.timeSinceMillis(startTime) * 0x1p-13f;
//        int floor = MathUtils.floorPositive(layer);
//        layer = (floor & 1) + (layer - floor) * (-(floor & 1) | 1);
        layer = Math.abs(layer - ((int) (layer + 16384.5) - 16384)) * 2f; // 0-1 triangle wave
        renderInternal();
    }

    public void renderInternal() {
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handleInput();
//        poisson.sampleCircle(center, 256, 30);
        batch.setProjectionMatrix(screenView.getCamera().combined);
        batch.setColor(0.5f, 0.5f, 0.5f, 1f);
        batch.begin();
        batch.draw(blank, 0, 0, 512, 512);
        final float
                maxDist = 255f,
//                maxDist = 254f * TrigTools.sin_(layer * layer * 0.5f) + 1f,
//                maxDist = 254f * TrigTools.sin_(layer * 0.5f) + 1f,
                iMax = 1f / maxDist;
        for (int dist = 0; dist <= maxDist; dist++) {
            final int circ = dist * 16;
            final float ic = 1f / circ;
            for (int t = 0; t < circ; t++) {
                final float angle = t * ic, x = TrigTools.cosTurns(angle), y = TrigTools.sinTurns(angle);
//                final float g = ColorTools.getRawGamutValue((int)(layer * 255.999f) << 8 | (int)(angle * 256f));
//                if(g < dist) continue;
//                if(poisson.array[(int)(256 + x * dist)][(int) (256 + y * dist)] == 0) continue;
                final float chr = dist * iMax, hue = angle;// * (0.5f - Math.abs(layer - 0.5f)) * 2f;
//                final float chr = dist * iMax, hue = OtherMath.barronSpline(angle, shape, turning);// * (0.5f - Math.abs(layer - 0.5f)) * 2f;
//                if(chr > ColorTools.chromaLimit(hue, layer)) continue;
//                batch.setPackedColor(ColorTools.oklabByHCL(hue, chr, layer, 1f));
                batch.setPackedColor(ColorTools.oklabByHSL(hue, chr, layer, 1f));
                batch.draw(blank, 256f + x * dist, 256f + y * dist, 2f, 2f);
            }
        }
        batch.setColor(layer, 0.5f, 0.5f, 1f);
        batch.draw(blank, 255f, 255f, 2f, 2f);
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
        else if(input.isKeyPressed(Input.Keys.S))
            shape += Gdx.graphics.getDeltaTime() * (UIUtils.shift() ? -0.5f : 0.5f);
        else if(input.isKeyPressed(Input.Keys.T))
            turning = Math.min(Math.max(turning + Gdx.graphics.getDeltaTime() * (UIUtils.shift() ? -0.25f : 0.25f), 0f), 1f);
        else if(input.isKeyJustPressed(Input.Keys.P))
            System.out.println("shape = " + shape + "; turning = " + turning + ";");
    }
}
