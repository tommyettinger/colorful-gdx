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

package com.github.tommyettinger.colorful.hsluv;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.github.tommyettinger.colorful.hsluv.ColorTools;
import com.github.tommyettinger.colorful.hsluv.SimplePalette;

import static com.badlogic.gdx.Gdx.input;

public class HsluvDescriptiveDemo extends ApplicationAdapter {
    public static final int SCREEN_WIDTH = 808;
    public static final int SCREEN_HEIGHT = 600;
    private Stage stage;
    private TextField tf;
    private final Color color = new Color();

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Descriptive Color Demo");
        config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
        config.setIdleFPS(20);
        config.useVsync(true);
        final HsluvDescriptiveDemo app = new HsluvDescriptiveDemo();
        new Lwjgl3Application(app, config);
    }

    @Override
    public void create() {
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        skin.getFont("default-font").getData().scale(2f);
        stage = new Stage();
        tf = new TextField("gray", skin);
        tf.setDisabled(false);
        Table tab = new Table(skin);
        tab.align(Align.center);
        tab.setFillParent(true);
        tab.add(tf).center().growX();
        stage.getRoot().addActor(tab);
        input.setInputProcessor(stage);
    }


    @Override
    public void render() {
        float c = SimplePalette.parseDescription(tf.getText());
        if(ColorTools.alphaInt(c) >= 254) {
//            System.out.printf("L=%1.8f, A=%1.8f, B=%1.8f\n", ColorTools.channelL(c), ColorTools.channelA(c), ColorTools.channelB(c));
            ColorTools.toColor(color, c);
        }
        Gdx.gl.glClearColor(color.r, color.g, color.b, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }
}
