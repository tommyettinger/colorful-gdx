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
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.github.tommyettinger.colorful.rgb.ColorTools;
import com.github.tommyettinger.colorful.rgb.SimplePalette;

import static com.badlogic.gdx.Gdx.input;

public class CompareDescriptionScreen extends ScreenAdapter {
    private Stage stage;
    private TextField nameField;
    private TextureAtlas.AtlasRegion pixel;
    private final Color color = new Color();
    private final DescriptionDemo mainGame;
    private float r = SimplePalette.TRANSPARENT;
    private float o = com.github.tommyettinger.colorful.oklab.SimplePalette.TRANSPARENT;
    private float i = com.github.tommyettinger.colorful.ipt_hq.SimplePalette.TRANSPARENT;
    private float c = com.github.tommyettinger.colorful.cielab.SimplePalette.TRANSPARENT;
    private float h = com.github.tommyettinger.colorful.hsluv.SimplePalette.TRANSPARENT;

    public CompareDescriptionScreen(DescriptionDemo main){
        mainGame = main;
    }

    @Override
    public void show() {
        Gdx.graphics.setTitle("Compare Color Description");
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        skin.getFont("default-font").getData().scale(2f);
        pixel = skin.getAtlas().findRegion("white");
        stage = new Stage();
        nameField = new TextField("gray", skin);
        nameField.setDisabled(false);
        ColorTools.toColor(color, SimplePalette.GRAY);
        Table tab = new Table(skin);
        tab.align(Align.center);
        tab.setFillParent(true);
//        Label title = new Label("Compare Color Descriptions", skin);
//        title.setAlignment(Align.center);
//        tab.add(title).colspan(2).growX().minWidth(300).row();
        tab.add("Description: ").left().minWidth(200);
        tab.add(nameField).center().growX().minHeight(100).row();
        tab.add("RGB:         ").center().grow().minWidth(200).row();
        tab.add("Oklab:       ").center().grow().minWidth(200).row();
        tab.add("HSLuv:       ").center().grow().minWidth(200).row();
        tab.add("CIELAB:      ").center().grow().minWidth(200).row();
        tab.add("IPT_HQ:      ").center().grow().minWidth(200);
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
        if(nameField.hasKeyboardFocus()) {
            float t = SimplePalette.parseDescription(nameField.getText());
            if (ColorTools.alphaInt(t) >= 254) {
                r = t;
                o = com.github.tommyettinger.colorful.oklab.SimplePalette.parseDescription(nameField.getText());
                i = com.github.tommyettinger.colorful.ipt_hq.SimplePalette.parseDescription(nameField.getText());
                c = com.github.tommyettinger.colorful.cielab.SimplePalette.parseDescription(nameField.getText());
                h = com.github.tommyettinger.colorful.hsluv.SimplePalette.parseDescription(nameField.getText());
            }
        }
        ScreenUtils.clear(0.5f, 0.5f, 0.5f, 1f);
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        stage.act();

        Camera camera = stage.getViewport().getCamera();
        camera.update();

        if (!stage.getRoot().isVisible()) return;

        Batch batch = stage.getBatch();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.setPackedColor(r);
        batch.draw(pixel, 0f, height*2/3f, width, height / 6f);
        batch.setPackedColor(com.github.tommyettinger.colorful.oklab.ColorTools.toRGBA(o));
        batch.draw(pixel, 0f, height / 2f, width, height / 6f);
        batch.setPackedColor(com.github.tommyettinger.colorful.hsluv.ColorTools.toRGBA(h));
        batch.draw(pixel, 0f, height / 3f, width, height / 6f);
        batch.setPackedColor(com.github.tommyettinger.colorful.cielab.ColorTools.toRGBA(c));
        batch.draw(pixel, 0f, height / 6f, width, height / 6f);
        batch.setPackedColor(com.github.tommyettinger.colorful.ipt_hq.ColorTools.toRGBA(i));
        batch.draw(pixel, 0f, 0f, width, height / 6f);
        batch.setPackedColor(Color.WHITE_FLOAT_BITS);
        stage.getRoot().draw(batch, 1);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }
}
