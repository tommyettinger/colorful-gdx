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
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3WindowAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.utils.UIUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.tommyettinger.colorful.Shaders;

import static com.badlogic.gdx.Gdx.input;

public class OklabColorfulBatchDemo extends ApplicationAdapter {
    //public static final int backgroundColor = Color.rgba8888(Color.DARK_GRAY);
//    public static final int SCREEN_WIDTH = 1531;
//    public static final int SCREEN_HEIGHT = 862;
    public static final int SCREEN_WIDTH = 808;
    public static final int SCREEN_HEIGHT = 600;
    protected ColorfulBatch batch;
    protected Viewport screenView;
    protected Texture screenTexture;

    private long lastProcessedTime = 0L;
    private float L = 0.5f, A = 0.5f, B = 0.5f, opacity = 1f, LM = 0.5f, AM = 0.5f, BM = 0.5f, CM = 0.5f;

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Tint Demo");
        config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
        config.setIdleFPS(10);
        config.useVsync(true);
//        config.setResizable(false);

        final OklabColorfulBatchDemo app = new OklabColorfulBatchDemo();
        config.setWindowListener(new Lwjgl3WindowAdapter() {
            @Override
            public void filesDropped(String[] files) {
                if (files != null && files.length > 0) {
                    if (files[0].endsWith(".png") || files[0].endsWith(".jpg") || files[0].endsWith(".jpeg"))
                        app.load(files[0]);
                }
            }
        });

        new Lwjgl3Application(app, config);
    }

    public void load(String name) {
        //// loads a file by its full path, which we get via drag+drop
        FileHandle file = Gdx.files.absolute(name);
        if (!file.exists()) {
            file = Gdx.files.classpath(name);
            if (!file.exists())
                return;
        }
        if (screenTexture != null) screenTexture.dispose();
        screenTexture = new Texture(file);
        screenTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        int width, height;
        Gdx.graphics.setWindowedMode(width = Math.min(screenTexture.getWidth() * 2, Gdx.graphics.getDisplayMode().width),
                height = Math.min(screenTexture.getHeight(), Gdx.graphics.getDisplayMode().height));
        screenView.update(width, height);
        screenView.getCamera().position.set(width * 0.5f, height * 0.5f, 0f);
    }

    @Override
    public void create() {
        batch = new ColorfulBatch();
        screenView = new ScreenViewport();
        screenView.getCamera().position.set(SCREEN_WIDTH * 0.5f, SCREEN_HEIGHT * 0.5f, 0);
        screenView.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.enableBlending();

        // if you don't have these files on this absolute path, that's fine, and they will be ignored
//        load("samples/Painting_by_Henri_Biva.jpg");
//        load("samples/Among_the_Sierra_Nevada_by_Albert_Bierstadt.jpg");
//        load("samples/Mona_Lisa.jpg");
        load("samples/Color_Guard.png");
//        load("samples/Grashers_Logo.png");
//        load("C:/d/Art/translucent-bubble.png");
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handleInput();
        batch.setProjectionMatrix(screenView.getCamera().combined);
        if (screenTexture != null) {
            batch.setTweakedColor(L, A, B, opacity, LM, AM, BM, CM);
            batch.begin();
            batch.draw(screenTexture, 0, 0);
            batch.end();
        }

    }

    @Override
    public void resize(int width, int height) {
        screenView.update(width, height);
        screenView.getCamera().position.set(width * 0.5f, height * 0.5f, 0f);
        screenView.getCamera().update();
    }

    public void handleInput() {
        if (input.isKeyPressed(Input.Keys.P)) // print
            System.out.println("L=" + L + ",A=" + A + ",B=" + B +  ",opacity=" + opacity + ",LM=" + LM + ",AM=" + AM + ",BM=" + BM + ",contrast=" + CM);
        else if (input.isKeyPressed(Input.Keys.M))
            load("samples/Mona_Lisa.jpg");
        else if (input.isKeyPressed(Input.Keys.S)) //Sierra Nevada
            load("samples/Among_the_Sierra_Nevada_by_Albert_Bierstadt.jpg");
        else if (input.isKeyPressed(Input.Keys.T)) // Trees
            load("samples/Painting_by_Henri_Biva.jpg");
        else if (input.isKeyPressed(Input.Keys.C)) // Color Guard, pixel art cartoon-wargame style
            load("samples/Color_Guard.png");
        else if (input.isKeyPressed(Input.Keys.G)) // grayscale palette
            load(Gdx.files.internal("samples/Grashers_Logo.png").exists() ? "samples/Grashers_Logo.png" : "samples/Spaceships.png");
        else if (input.isKeyPressed(Input.Keys.Q) || input.isKeyPressed(Input.Keys.ESCAPE)) //quit
            Gdx.app.exit();
        else {
            // only process once every 150 ms, or just over 6 times a second, at most
            if (TimeUtils.timeSinceMillis(lastProcessedTime) < 150)
                return;
            lastProcessedTime = TimeUtils.millis();
            if (input.isKeyPressed(Input.Keys.L)) //light
            {
                if(UIUtils.ctrl())
                    LM = Math.min(Math.max(LM + (UIUtils.shift() ? 0x3p-7f : -0x3p-7f), 0f), 1f);
                else
                    L = Math.min(Math.max(L + (UIUtils.shift() ? 0x3p-7f : -0x3p-7f), 0f), 1f);
            } else if (input.isKeyPressed(Input.Keys.A)) // A
            {
                if(UIUtils.ctrl())
                    AM = Math.min(Math.max(AM + (UIUtils.shift() ? 0x3p-7f : -0x3p-7f), 0f), 1f);
                else
                    A = Math.min(Math.max(A + (UIUtils.shift() ? 0x3p-7f : -0x3p-7f), 0f), 1f);
            } else if (input.isKeyPressed(Input.Keys.B)) // B
            {
                if(UIUtils.ctrl())
                    BM = Math.min(Math.max(BM + (UIUtils.shift() ? 0x3p-7f : -0x3p-7f), 0f), 1f);
                else
                    B = Math.min(Math.max(B + (UIUtils.shift() ? 0x3p-7f : -0x3p-7f), 0f), 1f);
            } else if (input.isKeyPressed(Input.Keys.O)) // Opacity/Contrast
            {
                if(UIUtils.ctrl())
                    CM = Math.min(Math.max(CM + (UIUtils.shift() ? 0x3p-7f : -0x3p-7f), 0f), 1f);
                else
                    opacity = Math.min(Math.max(opacity + (UIUtils.shift() ? 0x3p-7f : -0x3p-7f), 0f), 1f);
            } else if (input.isKeyPressed(Input.Keys.R)) // reset
            {
                LM = L = 0.5f;
                AM = A = 0.5f;
                BM = B = 0.5f;
                opacity = 1f;
                CM = 0.5f;
            }
        }
    }

}
