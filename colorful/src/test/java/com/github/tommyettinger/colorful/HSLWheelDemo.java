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
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.tommyettinger.digital.MathTools;

import static com.badlogic.gdx.Gdx.input;

public class HSLWheelDemo extends ApplicationAdapter {
    //public static final int backgroundColor = Color.rgba8888(Color.DARK_GRAY);
//    public static final int SCREEN_WIDTH = 1531;
//    public static final int SCREEN_HEIGHT = 862;
    public static final int SCREEN_WIDTH = 512;
    public static final int SCREEN_HEIGHT = 512;
    private SpriteBatch batch;
    private Viewport screenView;
    private BitmapFont font;
    private Texture blankRed;
    private long lastProcessedTime = 0L, startTime;
    private float layer = 0.5f, shape = 1.25f, turning = 0.4f, exponent = 1.6f;
    private ShaderProgram shader, otherShader;

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Color Wheel Demo");
        config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
        config.setIdleFPS(10);
        config.useVsync(true);
//        config.setResizable(false);

        final HSLWheelDemo app = new HSLWheelDemo();
        new Lwjgl3Application(app, config);
    }

    @Override
    public void create() {
        startTime = TimeUtils.millis();
        Pixmap b = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        b.drawPixel(0, 0, 0xFF0000FF);
        blankRed = new Texture(b);
        b.dispose();
        font = new BitmapFont(Gdx.files.internal("font.fnt"));
        font.setColor(1f, 0.5f, 0.5f, 1f);
        shader = new ShaderProgram(Shaders.vertexShader, Shaders.fragmentShaderHSL);
        if(!shader.isCompiled())
            System.out.println(shader.getLog());
        otherShader = new ShaderProgram(Shaders.vertexShader, Shaders.fragmentShaderHSL4);
        if(!otherShader.isCompiled())
            System.out.println(otherShader.getLog());
        batch = new SpriteBatch(1000, shader);
//        basicBatch = new SpriteBatch();
        screenView = new ScreenViewport();
        screenView.getCamera().position.set(SCREEN_WIDTH * 0.5f, SCREEN_HEIGHT * 0.5f, 0);
        screenView.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.enableBlending();
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handleInput();
//        layer = TrigTools.acos_(TrigTools.sin_(TimeUtils.timeSinceMillis(startTime) * 0x1p-13f)) + 0.5f;
//        layer = TrigTools.acos_(TrigTools.sin_(TimeUtils.timeSinceMillis(startTime) * 0x1p-13f)) * 2f;
        layer = TimeUtils.timeSinceMillis(startTime) * 0x1p-12f;
        int floor = MathUtils.floorPositive(layer);
        layer = (floor & 1) + (layer - floor) * (-(floor & 1) | 1);
        batch.setProjectionMatrix(screenView.getCamera().combined);
        batch.setColor(0f, 0f, 0.5f, 1f);
        batch.begin();
        batch.draw(blankRed, 0, 0, 512, 512);
        final float
                maxDist = 254f * TrigTools.sinTurns(layer * 0.5f) + 1f,
                iMax = 1f / maxDist;
        for (int dist = 0; dist <= maxDist; dist++) {
            final int circ = dist * 16;
            final float ic = 1f / circ;
            for (int t = 0; t < circ; t++) {
                final float angle = t * ic;
                final float x = TrigTools.cosTurns(angle), y = TrigTools.sinTurns(angle);
                final float sat = dist * iMax;// * (0.5f - Math.abs(layer - 0.5f)) * 2f;
                batch.setColor(MathTools.barronSpline((float) Math.pow(angle, exponent), shape, turning), sat, layer, 1f);
                batch.draw(blankRed, 255.5f + x * dist, 255.5f + y * dist, 1f, 1f);
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
        else if (input.isKeyJustPressed(Input.Keys.P)) //print
            System.out.println("shape: " + shape + ", turning: " + turning + ", exponent: " + exponent);
        else {
            // only process once every 80 ms, or just about 12 times a second, at most
            if (TimeUtils.timeSinceMillis(lastProcessedTime) < 80)
                return;
            lastProcessedTime = TimeUtils.millis();
            if (input.isKeyPressed(Input.Keys.RIGHT)) //saturation increase
                turning = MathUtils.clamp(turning + 0.025f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.LEFT)) //saturation decrease
                turning = MathUtils.clamp(turning - 0.025f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.UP)) //lighten
                shape = MathUtils.clamp(shape + 0.025f, 0f, 10f);
            else if (input.isKeyPressed(Input.Keys.DOWN)) //darken
                shape = MathUtils.clamp(shape - 0.025f, 0f, 10f);
            else if (input.isKeyPressed(Input.Keys.RIGHT_BRACKET)) //lighten
                exponent = MathUtils.clamp(exponent + 0.025f, 0.025f, 10f);
            else if (input.isKeyPressed(Input.Keys.LEFT_BRACKET)) //darken
                exponent = MathUtils.clamp(exponent - 0.025f, 0.025f, 10f);
            else if (input.isKeyPressed(Input.Keys.R)) // random
                layer = MathUtils.random();
            else if (input.isKeyPressed(Input.Keys.S)) // shader
                batch.setShader(batch.getShader() == shader ? otherShader : shader);
        }
    }
}
