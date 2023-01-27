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
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.github.tommyettinger.colorful.rgb.ColorTools;
import com.github.tommyettinger.colorful.rgb.SimplePalette;
import com.github.tommyettinger.digital.BitConversion;

import static com.badlogic.gdx.Gdx.input;

public class RGBDescriptionScreen extends ScreenAdapter {
    private Stage stage;
    private TextField nameField, hexField;
    private final Color color = new Color();
    private final DescriptionDemo mainGame;

    public RGBDescriptionScreen(DescriptionDemo main){
        mainGame = main;
    }

    @Override
    public void show() {
        Gdx.graphics.setTitle("RGB Color Description");
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        skin.getFont("default-font").getData().scale(2f);
        stage = new Stage();
        nameField = new TextField("gray", skin);
        nameField.setDisabled(false);
        hexField = new TextField("808080", skin);
        hexField.setDisabled(false);
        hexField.setTextFieldFilter(new TextField.TextFieldFilter() {
            @Override
            public boolean acceptChar(TextField textField, char c) {
                return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f');
            }
        });
        ColorTools.toColor(color, SimplePalette.GRAY);
        Table tab = new Table(skin);
        tab.align(Align.center);
        tab.setFillParent(true);
        Label title = new Label("RGB Color Description", skin);
        title.setAlignment(Align.center);
        tab.add(title).colspan(2).growX().minWidth(300).row();
        tab.add("Description: ").left().minWidth(200);
        tab.add(nameField).center().growX().row();
        tab.add("RRGGBB Hex:  ").left().minWidth(200);
        tab.add(hexField).center().growX();
        stage.getRoot().addActor(tab);
        TextButton chaos = new TextButton("Random color", skin);
        chaos.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                int z = (color.toIntBits() + 0x9e3779b9);
                z ^= z >>> 16;
                z = BitConversion.imul(z, 0x21f0aaad);
                z ^= z >>> 15;
                z = BitConversion.imul(z, 0x735a2d97);
                z ^= z >>> 15;
                Color.abgr8888ToColor(color, z | 0xFF000000);
                hexField.setText(hexColor());
                String t = nameField.getText();
                try {
                    String h = hexField.getText();
                    if(h.length() > 6) h = h.substring(0, 6);
                    t = SimplePalette.bestMatch(ColorTools.fromRGBA8888(Integer.parseInt(h, 16) << 8 | 0xFF), 1);
                }catch (NumberFormatException ignored){}
                nameField.setText(t);
            }
        });
        stage.addActor(chaos);
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

    private final StringBuilder sb = new StringBuilder("808080");

    private String hexColor(){
        int r = (int) (color.r * 255.999f);
        int g = (int) (color.g * 255.999f);
        int b = (int) (color.b * 255.999f);
        sb.setCharAt(0, Character.forDigit(r >>> 4, 16));
        sb.setCharAt(1, Character.forDigit(r & 15, 16));
        sb.setCharAt(2, Character.forDigit(g >>> 4, 16));
        sb.setCharAt(3, Character.forDigit(g & 15, 16));
        sb.setCharAt(4, Character.forDigit(b >>> 4, 16));
        sb.setCharAt(5, Character.forDigit(b & 15, 16));
        return sb.toString();
    }

    @Override
    public void render(float delta) {
        if(nameField.hasKeyboardFocus()) {
            float c = SimplePalette.parseDescription(nameField.getText());
            if (ColorTools.alphaInt(c) >= 254) {
                ColorTools.toColor(color, c);
                hexField.setText(hexColor());
            }
        }if(hexField.hasKeyboardFocus()) {
            String t = nameField.getText();
            try {
                String h = hexField.getText();
                if(h.length() > 6) h = h.substring(0, 6);
                t = SimplePalette.bestMatch(ColorTools.fromRGBA8888(Integer.parseInt(h, 16) << 8 | 0xFF), 1);
            }catch (NumberFormatException ignored){}
            nameField.setText(t);
            float c = SimplePalette.parseDescription(nameField.getText());
            ColorTools.toColor(color, c);
        }
        ScreenUtils.clear(color);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }
}
