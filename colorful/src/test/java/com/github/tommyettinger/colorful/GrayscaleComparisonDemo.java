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

import static com.badlogic.gdx.Gdx.input;

public class GrayscaleComparisonDemo extends ApplicationAdapter {
    //public static final int backgroundColor = Color.rgba8888(Color.DARK_GRAY);
//    public static final int SCREEN_WIDTH = 1531;
//    public static final int SCREEN_HEIGHT = 862;
    public static final int SCREEN_WIDTH = 808;
    public static final int SCREEN_HEIGHT = 600;
    protected SpriteBatch batch;
    protected SpriteBatch grayBatch;
    protected Viewport screenView;
    protected Texture screenTexture;

    private long lastProcessedTime = 0L;
    private float r = 1f;
    private float g = 1f;
    private float b = 1f;

    private ShaderProgram[] shaders = new ShaderProgram[4];
    private int shaderIndex = 0;

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Tint Demo");
        config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
        config.setForegroundFPS(10);
        config.useVsync(true);
//        config.setResizable(false);

        final GrayscaleComparisonDemo app = new GrayscaleComparisonDemo();
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
        if (!file.exists())
        {
            file = Gdx.files.classpath(name);
            if(!file.exists())
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
        batch = new SpriteBatch();
        ShaderProgram naive = new ShaderProgram(Shaders.vertexShader,
                "#ifdef GL_ES\n" +
                "#define LOWP lowp\n" +
                "precision mediump float;\n" +
                "#else\n" +
                "#define LOWP \n" +
                "#endif\n" +
                "varying LOWP vec4 v_color;\n" +
                "varying vec2 v_texCoords;\n" +
                "uniform sampler2D u_texture;\n" +
                "void main()\n" +
                "{\n" +
                "  vec4 color = v_color * texture2D(u_texture, v_texCoords);\n" +
                "  float grayValue = (color.r + color.g + color.b) / 3.0;\n" +
                "  color.rgb = vec3(grayValue, grayValue, grayValue);\n" +
                "  gl_FragColor = color;\n" +
                "}");
        if(!naive.isCompiled()){
            System.out.println("Shader failed to compile: " + naive.getLog());
            naive = null;
        }
        ShaderProgram weighted = new ShaderProgram(Shaders.vertexShader,
                "#ifdef GL_ES\n" +
                "#define LOWP lowp\n" +
                "precision mediump float;\n" +
                "#else\n" +
                "#define LOWP \n" +
                "#endif\n" +
                "varying LOWP vec4 v_color;\n" +
                "varying vec2 v_texCoords;\n" +
                "uniform sampler2D u_texture;\n" +
                "void main()\n" +
                "{\n" +
                "  vec4 color = v_color * texture2D(u_texture, v_texCoords);\n" +
                "  float grayValue = dot(color.rgb, vec3(0.35, 0.55, 0.1));\n" +
                "  color.rgb = vec3(grayValue, grayValue, grayValue);\n" +
                "  gl_FragColor = color;\n" +
                "}");
        if(!weighted.isCompiled()){
            System.out.println("Shader failed to compile: " + weighted.getLog());
            weighted = null;
        }
        ShaderProgram bt709 = new ShaderProgram(Shaders.vertexShader,
                "#ifdef GL_ES\n" +
                "#define LOWP lowp\n" +
                "precision mediump float;\n" +
                "#else\n" +
                "#define LOWP \n" +
                "#endif\n" +
                "varying LOWP vec4 v_color;\n" +
                "varying vec2 v_texCoords;\n" +
                "uniform sampler2D u_texture;\n" +
                "void main()\n" +
                "{\n" +
                "  vec4 color = v_color * texture2D(u_texture, v_texCoords);\n" +
                "  float grayValue = dot(color.rgb, vec3(0.2126, 0.7152, 0.0722));\n" +
                "  color.rgb = vec3(grayValue);\n" +
                "  gl_FragColor = color;\n" +
                "}");
        if(!bt709.isCompiled()){
            System.out.println("Shader failed to compile: " + bt709.getLog());
            bt709 = null;
        }
        ShaderProgram bt2020 = new ShaderProgram(Shaders.vertexShader,
                "#ifdef GL_ES\n" +
                "#define LOWP lowp\n" +
                "precision mediump float;\n" +
                "#else\n" +
                "#define LOWP \n" +
                "#endif\n" +
                "varying LOWP vec4 v_color;\n" +
                "varying vec2 v_texCoords;\n" +
                "uniform sampler2D u_texture;\n" +
                "void main()\n" +
                "{\n" +
                "  vec4 color = v_color * texture2D(u_texture, v_texCoords);\n" +
                "//dot() multiplies each pair of components and adds them all up.\n" +
                "  float grayValue = dot(color.rgb, vec3(0.2627, 0.6780, 0.0593));\n" +
                "  color.rgb = vec3(grayValue);\n" +
                "  gl_FragColor = color;\n" +
                "}");
        if(!bt2020.isCompiled()){
            System.out.println("Shader failed to compile: " + bt2020.getLog());
            bt2020 = null;
        }
        shaders[0] = naive;
        shaders[1] = weighted;
        shaders[2] = bt709;
        shaders[3] = bt2020;
        grayBatch = new SpriteBatch(100, shaders[shaderIndex]);
        screenView = new ScreenViewport();
        screenView.getCamera().position.set(SCREEN_WIDTH * 0.5f, SCREEN_HEIGHT * 0.5f, 0);
        screenView.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.enableBlending();

        // if you don't have these files on this absolute path, that's fine, and they will be ignored
//        load("samples/Painting_by_Henri_Biva.jpg");
//        load("samples/Among_the_Sierra_Nevada_by_Albert_Bierstadt.jpg");
        load("samples/Mona_Lisa.jpg");
    }


    @Override
    public void render() {
//        grayBatch.setShader(shaders[shaderIndex = (shaderIndex + 1) % shaders.length]);
        Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handleInput();
        batch.setProjectionMatrix(screenView.getCamera().combined);
        grayBatch.setProjectionMatrix(screenView.getCamera().combined);
        if (screenTexture != null) {
            grayBatch.setColor(r, g, b, 1f);
            grayBatch.begin();
            grayBatch.draw(screenTexture, 0, 0);
            grayBatch.end();
            batch.begin();
            batch.setPackedColor(-0x1.fffffep126f); // packed white
            batch.draw(screenTexture, screenTexture.getWidth(), 0);
            batch.end();
        }
        Gdx.graphics.setTitle("Showing shader #" + shaderIndex);
    }

    @Override
    public void resize(int width, int height) {
        screenView.update(width, height);
        screenView.getCamera().position.set(width * 0.5f, height * 0.5f, 0f);
        screenView.getCamera().update();
    }

    public void handleInput() {
        if (input.isKeyPressed(Input.Keys.V)) // view
            System.out.println("r=" + r + ",g=" + g + ",b=" + b);
        else if(input.isKeyJustPressed(Input.Keys.RIGHT))
            grayBatch.setShader(shaders[shaderIndex = (shaderIndex + 1) % shaders.length]);
        else if(input.isKeyJustPressed(Input.Keys.LEFT))
            grayBatch.setShader(shaders[shaderIndex = (shaderIndex - 1 + shaders.length) % shaders.length]);
        else if (input.isKeyPressed(Input.Keys.D))
            load("samples/Dawnlike.png");
        else if (input.isKeyPressed(Input.Keys.M))
            load("samples/Mona_Lisa.jpg");
        else if (input.isKeyPressed(Input.Keys.S)) //Sierra Nevada
            load("samples/Among_the_Sierra_Nevada_by_Albert_Bierstadt.jpg");
        else if (input.isKeyPressed(Input.Keys.P)) // Pond
            load("samples/Painting_by_Henri_Biva.jpg");
        else if (input.isKeyPressed(Input.Keys.W)) // Wargame, pixel art cartoon style
            load("samples/Color_Guard.png");
        else if (input.isKeyPressed(Input.Keys.O)) // old-style grayscale palette
            load("samples/Grayscale_Spaceships.png");
        else if (input.isKeyPressed(Input.Keys.Q) || input.isKeyPressed(Input.Keys.ESCAPE)) //quit
            Gdx.app.exit();
        else {
            // only process once every 150 ms, or just over 6 times a second, at most
            if (TimeUtils.timeSinceMillis(lastProcessedTime) < 150)
                return;
            lastProcessedTime = TimeUtils.millis();
            if (input.isKeyPressed(Input.Keys.R)) //red
                r = MathUtils.clamp(r + (UIUtils.shift() ? -0x3p-7f : 0x3p-7f), 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.G)) //green
                g = MathUtils.clamp(g + (UIUtils.shift() ? -0x3p-8f : 0x3p-8f), 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.B)) //blue
                b = MathUtils.clamp(b + (UIUtils.shift() ? -0x3p-8f : 0x3p-8f), 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.Z)) // zero changes
            {
                r = 1f;
                g = 1f;
                b = 1f;
            }
        }
    }

}
