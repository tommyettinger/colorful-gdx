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

package com.github.tommyettinger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.ScreenUtils;
import com.github.tommyettinger.colorful.oklab.ColorTools;
import com.github.tommyettinger.colorful.oklab.GradientTools;
import com.github.tommyettinger.colorful.oklab.SimplePalette;

import static com.badlogic.gdx.Gdx.input;

public class OklabGradientScreen extends ScreenAdapter {
    private Stage stage;
    private TextField tf, tf2;
    private TextureRegion pixel;
    private final FloatArray colors = new FloatArray(1024);
    private float start, end;
    private final DescriptionDemo mainGame;
    private Interpolation interpolation = Interpolation.linear;

    public OklabGradientScreen(DescriptionDemo main){
        mainGame = main;
    }

    @Override
    public void show() {
        Gdx.graphics.setTitle("Oklab Color Gradients");
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        skin.getFont("default-font").getData().scale(2f);
        pixel = skin.getAtlas().findRegion("white");
        stage = new Stage();
        tf = new TextField("dullest red", skin);
        tf2 = new TextField("light blue", skin);
        start = SimplePalette.parseDescription("dullest red");
        end = SimplePalette.parseDescription("light blue");
        colors.clear();
        GradientTools.appendGradient(colors, start, end, Gdx.graphics.getBackBufferWidth(), interpolation);
        tf.setDisabled(false);
        tf2.setDisabled(false);
        Table tab = new Table(skin);
        tab.align(Align.center);
        tab.setFillParent(true);
        Label title = new Label("Oklab Color Description", skin);
        title.setAlignment(Align.center);
        tab.add(title).growX().minWidth(300).colspan(2).row();
        tab.add(tf).left().minWidth(400);
        tab.add(tf2).right().minWidth(400).row();
        stage.getRoot().addActor(tab);
        stage.addListener(new InputListener(){
            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if(keycode == Input.Keys.LEFT_BRACKET)
                {
                    mainGame.previousScreen();
                    return true;
                }
                else if(keycode == Input.Keys.RIGHT_BRACKET)
                {
                    mainGame.nextScreen();
                    return true;
                }
                else if(keycode == Input.Keys.ESCAPE){
                    Gdx.app.exit();
                    return true;
                }
                return super.keyUp(event, keycode);
            }
        });
        input.setInputProcessor(stage);
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.GRAY);
        float width = stage.getViewport().getWorldWidth(), height = stage.getViewport().getWorldHeight();
        boolean changed = colors.size != width;
        float c = SimplePalette.parseDescription(tf.getText());
        if(ColorTools.alphaInt(c) >= 254){
            changed |= start != (start = c);
        }
        c = SimplePalette.parseDescription(tf2.getText());
        if(ColorTools.alphaInt(c) >= 24){
            changed |= end != (end = c);
        }
        if(changed){
            colors.clear();
            GradientTools.appendGradient(colors, start, end, (int)width, interpolation);
            System.out.println(colors.size + " " + width);
        }
        stage.act();
        Camera camera = stage.getViewport().getCamera();
        camera.update();

        if (!stage.getRoot().isVisible()) return;

        Batch batch = stage.getBatch();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (int i = 0; i < colors.size; i++) {
            batch.setPackedColor(ColorTools.toRGBA(colors.get(i)));
            batch.draw(pixel, i * width / colors.size, 0, width / colors.size, height);
        }
        stage.getRoot().draw(batch, 1);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }
}
