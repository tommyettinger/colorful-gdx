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

package com.github.tommyettinger.colorful.rgb;

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
import static com.github.tommyettinger.colorful.rgb.RGBHSLBatch.TWEAK_ATTRIBUTE;

public class RGBHSLTintDemo extends ApplicationAdapter {
    //public static final int backgroundColor = Color.rgba8888(Color.DARK_GRAY);
//    public static final int SCREEN_WIDTH = 1531;
//    public static final int SCREEN_HEIGHT = 862;
    public static final int SCREEN_WIDTH = 808;
    public static final int SCREEN_HEIGHT = 600;
    protected SpriteBatch batch;
    protected RGBHSLBatch colorfulBatch;
    protected Viewport screenView;
    protected Texture screenTexture;

    private long lastProcessedTime = 0L;
    private float red = 1f, green = 1f, blue = 1f, hue = 0f, sat = 0.5f, light = 0.5f, potency = 0f;

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Tint Demo");
        config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
        config.setIdleFPS(10);
        config.useVsync(true);
//        config.setResizable(false);
        config.enableGLDebugOutput(true, System.out);
        ShaderProgram.prependVertexCode = "#version 110\n";
        ShaderProgram.prependFragmentCode = "#version 110\n";

        final RGBHSLTintDemo app = new RGBHSLTintDemo();
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
        colorfulBatch = new RGBHSLBatch(1000, new ShaderProgram(
                "attribute vec4 " + ShaderProgram.POSITION_ATTRIBUTE + ";\n"
                        + "attribute vec4 " + ShaderProgram.COLOR_ATTRIBUTE + ";\n"
                        + "attribute vec2 " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n"
                        + "attribute vec4 " + TWEAK_ATTRIBUTE + ";\n"
                        + "uniform mat4 u_projTrans;\n"
                        + "varying vec4 v_color;\n"
                        + "varying vec4 v_tweak;\n"
                        + "varying vec2 v_texCoords;\n"
                        + "\n"
                        + "void main()\n"
                        + "{\n"
                        + "   v_color = " + ShaderProgram.COLOR_ATTRIBUTE + ";\n"
                        + "   v_color.a = v_color.a * (255.0/254.0);\n"
                        + "   v_tweak = " + TWEAK_ATTRIBUTE + ";\n"
                        + "   v_tweak.a = v_tweak.a * (255.0/254.0);\n"
                        + "   v_texCoords = " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n"
                        + "   gl_Position =  u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n"
                        + "}\n",
                "#ifdef GL_ES\n" +
                        "#define LOWP lowp\n" +
                        "precision mediump float;\n" +
                        "#else\n" +
                        "#define LOWP\n" +
                        "#endif\n" +
                        "varying vec2 v_texCoords;\n" +
                        "varying LOWP vec4 v_color;\n" +
                        "varying LOWP vec4 v_tweak;\n" +
                        "uniform sampler2D u_texture;\n" +
                        "const float eps = 1.0e-10;\n" +
                        "\n" +
                        "vec4 rgb2hsl(vec4 c)\n" +
                        "{\n" +
                        "    const vec4 J = vec4(0.0, -1.0 / 3.0, 2.0 / 3.0, -1.0);\n" +
                        "    vec4 p = mix(vec4(c.bg, J.wz), vec4(c.gb, J.xy), step(c.b, c.g));\n" +
                        "    vec4 q = mix(vec4(p.xyw, c.r), vec4(c.r, p.yzx), step(p.x, c.r));\n" +
                        "    float d = q.x - min(q.w, q.y);\n" +
                        "    float l = q.x * (1.0 - 0.5 * d / (q.x + eps));\n" +
                        "    return vec4(abs(q.z + (q.w - q.y) / (6.0 * d + eps)), (q.x - l) / (min(l, 1.0 - l) + eps), l, c.a);\n" +
                        "}\n" +
                        "            \n" +
                        "vec4 hsl2rgb(vec4 c)\n" +
                        "{\n" +
                        "    const vec4 K = vec4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);\n" +
                        "    vec3 p = abs(fract(c.x + K.xyz) * 6.0 - K.www);\n" +
                        "    float v = (c.z + c.y * min(c.z, 1.0 - c.z));\n" +
                        "    return vec4(v * mix(K.xxx, clamp(p - K.xxx, 0.0, 1.0), 2.0 * (1.0 - c.z / (v + eps))), c.w);\n" +
                        "}\n" +
                        "                \n" +
                        "void main()\n" +
                        "{\n" +
                        "  vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                        "  tgt = rgb2hsl(tgt); // convert to HSL\n" +
                        "  \n" +
                        "  tgt.x = fract(tgt.x+v_tweak.x); // tweak Hue\n" +
                        "  tgt.y *= (v_tweak.y*2.0); // tweak Saturation\n" +
                        "  tgt.z += (v_tweak.z-0.5) * 2.0; // tweak Lightness\n" +
                        "  \n" +
                        "  vec4 color = hsl2rgb(clamp(tgt, 0.0, 1.0)); // convert back to RGB \n" +
                        "  vec4 color_tinted = color*v_color; // multiply with batch tint color\n" +
                        "  color = mix(color, color_tinted, v_tweak.w); // mixed with tinted color based on tweak Tint\n" +
                        "  color.rgb = mix(vec3(dot(color.rgb, vec3(0.3333))), color.rgb,  (v_tweak.y*2.0));  // remove colors based on tweak.saturation\n" +
                        "  \n" +
                        "  gl_FragColor = color;\n" +
                        "}"
        ));

        screenView = new ScreenViewport();
        screenView.getCamera().position.set(SCREEN_WIDTH * 0.5f, SCREEN_HEIGHT * 0.5f, 0);
        screenView.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.enableBlending();

        // if you don't have these files on this absolute path, that's fine, and they will be ignored
//        load("samples/Painting_by_Henri_Biva.jpg");
//        load("samples/Among_the_Sierra_Nevada_by_Albert_Bierstadt.jpg");
        load("samples/Mona_Lisa.jpg");
//        load("samples/gradients.png");
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handleInput();
        batch.setProjectionMatrix(screenView.getCamera().combined);
        colorfulBatch.setProjectionMatrix(screenView.getCamera().combined);
        if (screenTexture != null) {
            colorfulBatch.setColor(red, green, blue, 1f);
            colorfulBatch.setTweak(hue, sat, light, potency);
            colorfulBatch.begin();
            colorfulBatch.draw(screenTexture, 0, 0);
            colorfulBatch.end();
            batch.begin();
            batch.setPackedColor(-0x1.fffffep126f); // packed white
            batch.draw(screenTexture, screenTexture.getWidth(), 0);
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
        if (input.isKeyPressed(Input.Keys.V)) // view
            System.out.println("R=" + red + ",G=" + green + ",B=" + blue + ",C=" + potency);
        else if (input.isKeyPressed(Input.Keys.M))
            load("samples/Mona_Lisa.jpg");
        else if (input.isKeyPressed(Input.Keys.N)) //Sierra Nevada
            load("samples/Among_the_Sierra_Nevada_by_Albert_Bierstadt.jpg");
        else if (input.isKeyPressed(Input.Keys.P)) // Pond
            load("samples/Painting_by_Henri_Biva.jpg");
        else if (input.isKeyPressed(Input.Keys.W)) // Wargame, pixel art cartoon style
            load("samples/Color_Guard.png");
        else if (input.isKeyPressed(Input.Keys.A)) // grayscale palette
            load("samples/Grayscale_Spaceships.png");
        else if (input.isKeyPressed(Input.Keys.Q) || input.isKeyPressed(Input.Keys.ESCAPE)) //quit
            Gdx.app.exit();
        else {
            // only process once every 150 ms, or just over 6 times a second, at most
            if (TimeUtils.timeSinceMillis(lastProcessedTime) < 150)
                return;
            lastProcessedTime = TimeUtils.millis();
            if (input.isKeyPressed(Input.Keys.R)) //red
                red = MathUtils.clamp(red + (UIUtils.shift() ? -0x3p-7f : 0x3p-7f), 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.G)) //green
                green = MathUtils.clamp(green + (UIUtils.shift() ? -0x3p-7f : 0x3p-7f), 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.B)) //blue
                blue = MathUtils.clamp(blue + (UIUtils.shift() ? -0x3p-7f : 0x3p-7f), 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.H)) //hue
                hue = (hue += (UIUtils.shift() ? -0x3p-7f : 0x3p-7f) - MathUtils.floor(hue));
            else if (input.isKeyPressed(Input.Keys.S)) //green
                sat = MathUtils.clamp(sat + (UIUtils.shift() ? -0x3p-7f : 0x3p-7f), 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.L)) //blue
                light = MathUtils.clamp(light + (UIUtils.shift() ? -0x3p-7f : 0x3p-7f), 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.O)) //rOughness
                potency = MathUtils.clamp(potency + (UIUtils.shift() ? -0x3p-7f : 0x3p-7f), 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.Z)) // zero changes
            {
                red = 1f;
                green = 1f;
                blue = 1f;
                hue = 0f;
                sat = 0.5f;
                light = 0.5f;
                potency = 0f;
            }
        }
    }

}
