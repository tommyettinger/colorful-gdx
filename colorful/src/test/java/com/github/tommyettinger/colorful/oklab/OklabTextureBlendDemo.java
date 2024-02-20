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
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.tommyettinger.colorful.Shaders;

public class OklabTextureBlendDemo extends ApplicationAdapter {
    //public static final int backgroundColor = Color.rgba8888(Color.DARK_GRAY);
//    public static final int SCREEN_WIDTH = 1531;
//    public static final int SCREEN_HEIGHT = 862;
    public static final int SCREEN_WIDTH = 512;
    public static final int SCREEN_HEIGHT = 512;
    protected SpriteBatch batch;
    protected Viewport screenView;
    protected Texture dirt, grass;

    private ShaderProgram oklabShader;
    private ShaderProgram rgbShader;

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Texture Blending Demo");
        config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
        config.setIdleFPS(10);
        config.useVsync(true);
        final OklabTextureBlendDemo app = new OklabTextureBlendDemo();
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
        if (dirt != null) dirt.dispose();
        dirt = new Texture(file);
        dirt.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    @Override
    public void create() {
        oklabShader = new ShaderProgram(Shaders.vertexShader,
                "#ifdef GL_ES\n" +
                        "#define LOWP lowp\n" +
                        "precision mediump float;\n" +
                        "#else\n" +
                        "#define LOWP \n" +
                        "#endif\n" +
                        "varying vec2 v_texCoords;\n" +
                        "varying LOWP vec4 v_color;\n" +
                        "uniform sampler2D u_texture;\n" +
                        "uniform sampler2D u_texture2;\n" +
                        "const vec3 forward = vec3(1.0 / 3.0);\n" +
                        "float toOklab(float L) {\n" +
                        "  return pow(L, 1.5);\n" +
                        "}\n" +
                        "float fromOklab(float L) {\n" +
                        "  return pow(L, 0.666666);\n" +
                        "}\n" +
                        "void main()\n" +
                        "{\n" +
                        "  vec4 dirt = texture2D( u_texture, v_texCoords );\n" +
                        "  vec3 dab = mat3(+0.2104542553, +1.9779984951, +0.0259040371, +0.7936177850, -2.4285922050, +0.7827717662, -0.0040720468, +0.4505937099, -0.8086757660) *" +
                        "             pow(mat3(0.4121656120, 0.2118591070, 0.0883097947, 0.5362752080, 0.6807189584, 0.2818474174, 0.0514575653, 0.1074065790, 0.6302613616) \n" +
                        "             * (dirt.rgb * dirt.rgb), forward);\n" +
                        "  vec4 grass = texture2D( u_texture2, v_texCoords );\n" +
                        "  vec3 gab = mat3(+0.2104542553, +1.9779984951, +0.0259040371, +0.7936177850, -2.4285922050, +0.7827717662, -0.0040720468, +0.4505937099, -0.8086757660) *" +
                        "             pow(mat3(0.4121656120, 0.2118591070, 0.0883097947, 0.5362752080, 0.6807189584, 0.2818474174, 0.0514575653, 0.1074065790, 0.6302613616) \n" +
                        "             * (grass.rgb * grass.rgb), forward);\n" +
                        "  vec3 lab = mix(dab, gab, v_texCoords.xxx);\n" +
                        "  lab.x = fromOklab(clamp(toOklab(lab.x) + v_color.r - 0.5, 0.0, 1.0));\n" +
                        "  lab.yz = clamp(lab.yz + v_color.gb * 2.0 - 1.0, -1.0, 1.0);\n" +
                        "  lab = mat3(1.0, 1.0, 1.0, +0.3963377774, -0.1055613458, -0.0894841775, +0.2158037573, -0.0638541728, -1.2914855480) * lab;\n" +
                        "  gl_FragColor = vec4(sqrt(clamp(" +
                        "                 mat3(+4.0767245293, -1.2681437731, -0.0041119885, -3.3072168827, +2.6093323231, -0.7034763098, +0.2307590544, -0.3411344290, +1.7068625689) *\n" +
                        "                 (lab * lab * lab)," +
                        "                 0.0, 1.0)), v_color.a * mix(dirt.a, grass.a, v_texCoords.x));\n" +
                        "}");
        if(!oklabShader.isCompiled())
            System.out.println(oklabShader.getLog());
        rgbShader = new ShaderProgram(Shaders.vertexShader,
                "#ifdef GL_ES\n" +
                        "#define LOWP lowp\n" +
                        "precision mediump float;\n" +
                        "#else\n" +
                        "#define LOWP \n" +
                        "#endif\n" +
                        "varying vec2 v_texCoords;\n" +
                        "varying LOWP vec4 v_color;\n" +
                        "uniform sampler2D u_texture;\n" +
                        "uniform sampler2D u_texture2;\n" +
                        "void main()\n" +
                        "{\n" +
                        "  vec4 dirt = texture2D( u_texture, v_texCoords );\n" +
                        "  vec4 grass = texture2D( u_texture2, v_texCoords );\n" +
                        "  vec3 rgb = mix(dirt.rgb, grass.rgb, v_texCoords.xxx) * v_color.rgb;\n" +
                        "  gl_FragColor = vec4(rgb, v_color.a * mix(dirt.a, grass.a, v_texCoords.x));\n" +
                        "}");
        if(!rgbShader.isCompiled())
            System.out.println(rgbShader.getLog());
        batch = new SpriteBatch(1000, oklabShader);
        screenView = new ScreenViewport();
        screenView.getCamera().position.set(SCREEN_WIDTH * 0.5f, SCREEN_HEIGHT * 0.5f, 0);
        screenView.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.enableBlending();

        dirt = new Texture("samples/dirt.png");
        dirt.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        grass = new Texture("samples/grass.png");
        grass.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(Gdx.input.isKeyJustPressed(Input.Keys.R)){
            batch.setShader(rgbShader);
        } else if(Gdx.input.isKeyJustPressed(Input.Keys.O)){
            batch.setShader(oklabShader);
        }
        if(batch.getShader() == oklabShader)
            batch.setColor(0.5f, 0.5f, 0.5f, 1f);
        else batch.setPackedColor(Color.WHITE_FLOAT_BITS);
        batch.setProjectionMatrix(screenView.getCamera().combined);
        dirt.bind(1);
        batch.begin();

        batch.getShader().setUniformi("u_texture2", 1);
        Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
        batch.draw(grass, 0, 0);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        screenView.update(width, height);
        screenView.getCamera().position.set(width * 0.5f, height * 0.5f, 0f);
        screenView.getCamera().update();
    }

}

