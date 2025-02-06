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
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.badlogic.gdx.Gdx.input;

public class ImageScramblerDemo extends ApplicationAdapter {
    public static final int SCREEN_WIDTH = 256;
    public static final int SCREEN_HEIGHT = 224;
    protected SpriteBatch batch;
    protected Viewport screenView;
    protected Texture screenTexture;

    private long lastProcessedTime = 0L;
    private ShaderProgram defaultShader;
    private ShaderProgram scrambleShader;
    private float r = 0.5f, g = 0.5f, b = 0.5f, // all neutral values
    //// contrast can be used by some shaders as alpha/opacity; it's currently lightness contrast
    contrast = 0.5f;

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Image Scrambler Demo");
        config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
        config.setIdleFPS(10);
        config.useVsync(true);
        config.setOpenGLEmulation(Lwjgl3ApplicationConfiguration.GLEmulation.GL30, 4, 2);
        config.setTransparentFramebuffer(true);
        final ImageScramblerDemo app = new ImageScramblerDemo();
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
        Gdx.graphics.setWindowedMode(width = Math.min(screenTexture.getWidth(), Gdx.graphics.getDisplayMode().width),
                height = Math.min(screenTexture.getHeight(), Gdx.graphics.getDisplayMode().height));
        screenView.update(width, height);
        screenView.getCamera().position.set(width * 0.5f, height * 0.5f, 0f);
    }

    @Override
    public void create() {
        defaultShader = SpriteBatch.createDefaultShader();
        scrambleShader = new ShaderProgram(
                "attribute vec4 a_position;\n" +
                "attribute vec4 a_color;\n" +
                "attribute vec2 a_texCoord0;\n" +
                "uniform mat4 u_projTrans;\n" +
                "varying vec4 v_color;\n" +
                "varying vec2 v_texCoords;\n" +
                "\n" +
                "void main()\n" +
                "{\n" +
                "   v_color = a_color;\n" +
                "   v_color.a = v_color.a * (255.0/254.0);\n" +
                "   v_texCoords = a_texCoord0;\n" +
                "   gl_Position =  u_projTrans * a_position;\n" +
                "}\n",
                "#ifdef GL_ES\n" +
                        "#define LOWP lowp\n" +
                        "precision mediump float;\n" +
                        "#else\n" +
                        "#define LOWP \n" +
                        "#endif\n" +
                        "varying vec2 v_texCoords;\n" +
                        "varying LOWP vec4 v_color;\n" +
                        "uniform sampler2D u_texture;\n" +
                        "uint scramblePosition(){\n" +
                        "  uint x = (floatBitsToUint(gl_FragCoord.x));\n" +
                        "  uint y = (floatBitsToUint(gl_FragCoord.y));\n" +
                        "  uint w = (x ^ x >> 16u) * 0xC13FA9A9u + (y ^ y >> 16u) * 0x91E10DA5u;\n" +
                        "  w ^= w >> 15u;\n" +
                        "  w *= 0X2C1B3C6Du;\n" +
                        "  w ^= w >> 12u;\n" +
                        "  w *= 0X297A2D39u;\n" +
                        "  w ^= w >> 15u;\n" +
                        "  return w;\n" +
                        "}\n" +
                        "vec4 getColor(){\n" +
                        "  uint w = scramblePosition();\n" +
                        "  uint a = packUnorm4x8(texelFetch(u_texture, ivec2(gl_FragCoord.xy), 0));\n" +
//                        "  a = (a << 8u | a >> 24u);\n" +
                        "  a ^= w;\n" +
//                        "  a *= 3u;\n" +
//                        "  a *= 0xAAAAAAABu;\n" +
//                        "  uint i = 2u ^ (a * 3u);\n" +
//                        "  i = i * (2u - (a * i));\n" +
//                        "  i = i * (2u - (a * i));\n" +
//                        "  i = i * (2u - (a * i)) & ~1u;\n" +
                        "  return unpackUnorm4x8(a);\n" +
//                        "  return unpackUnorm4x8((i << 24u | i >> 8u));\n" +
//                        "  return unpackUnorm4x8((a << 24u | a >> 8u));\n" +
                        "}\n" +
                        "vec4 descramble(vec4 color){\n" +
                        "  uint w = scramblePosition();\n" +
                        "  uint a = packUnorm4x8(color);\n" +
                        "  a ^= w;\n" +
                        "  return unpackUnorm4x8(a);\n" +
                        "}\n" +
                        "void main()\n" +
                        "{\n" +
                        "   gl_FragColor = v_color * 0. + getColor();\n" +
                        "}");
        if(!scrambleShader.isCompiled())
            System.out.println(scrambleShader.getLog());
        batch = new SpriteBatch(1000, defaultShader);
        screenView = new ScreenViewport();
        screenView.getCamera().position.set(SCREEN_WIDTH * 0.5f, SCREEN_HEIGHT * 0.5f, 0);
        screenView.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.enableBlending();

        // if you don't have these files on this absolute path, that's fine, and they will be ignored
//        load("samples/Painting_by_Henri_Biva.jpg");
//        load("samples/Among_the_Sierra_Nevada_by_Albert_Bierstadt.jpg");
        load("samples/gradients.png");
//        load("C:/d/Art/translucent-bubble.png");

        FrameBuffer fb = new FrameBuffer(Pixmap.Format.RGBA8888,
                Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), false, false);
        fb.begin();
        render();
        Pixmap pm = Pixmap.createFromFrameBuffer(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight());
        PixmapIO.writePNG(Gdx.files.local("Scrambled.png"), pm, 7, true);
        fb.end(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight());
        pm.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handleInput();
        batch.setProjectionMatrix(screenView.getCamera().combined);
        if (screenTexture != null) {
            batch.setShader(scrambleShader);
            batch.begin();
            batch.draw(screenTexture, 0, 0);
//            batch.setShader(defaultShader);
//            batch.setPackedColor(-0x1.fffffep126f); // packed white
//            batch.draw(screenTexture, screenTexture.getWidth(), 0);
            batch.end();

//            stage.act();
//            stage.draw();
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
            System.out.println("R=" + r + ",G=" + g + ",B=" + b);
        else if (input.isKeyPressed(Input.Keys.X))
            load("samples/Scrambled.png");
        else if (input.isKeyPressed(Input.Keys.E))
            load("samples/Descrambled.png");
        else if (input.isKeyPressed(Input.Keys.D))
            load("samples/Dawnlike.png");
        else if (input.isKeyPressed(Input.Keys.M))
            load("samples/Mona_Lisa.jpg");
        else if (input.isKeyPressed(Input.Keys.S)) //Sierra Nevada
            load("samples/Among_the_Sierra_Nevada_by_Albert_Bierstadt.jpg");
        else if (input.isKeyPressed(Input.Keys.B)) // Biva
            load("samples/Painting_by_Henri_Biva.jpg");
        else if (input.isKeyPressed(Input.Keys.C)) // Color Guard, pixel art cartoon-wargame style
            load("samples/Color_Guard.png");
        else if (input.isKeyPressed(Input.Keys.G)) // grayscale palette
            load("samples/Grayscale_Spaceships.png");
        else if (input.isKeyPressed(Input.Keys.A)) // higher-color atlas
            load("samples/Spaceships.png");
        else if (input.isKeyPressed(Input.Keys.Q) || input.isKeyPressed(Input.Keys.ESCAPE)) //quit
            Gdx.app.exit();
//        else {
//            // only process once every 80 ms, or just about 12 times a second, at most
//            if (TimeUtils.timeSinceMillis(lastProcessedTime) < 80)
//                return;
//            lastProcessedTime = TimeUtils.millis();
//            if (input.isKeyPressed(Input.Keys.H))
//                r = (r += 0x7p-8f) - MathUtils.floor(r);
//            else if (input.isKeyPressed(Input.Keys.RIGHT))
//                g = MathUtils.clamp(g + 0x3p-7f, 0f, 1f);
//            else if (input.isKeyPressed(Input.Keys.LEFT))
//                g = MathUtils.clamp(g - 0x3p-7f, 0f, 1f);
//        }
    }

}

