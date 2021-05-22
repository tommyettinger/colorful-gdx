package com.github.tommyettinger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.github.tommyettinger.colorful.rgb.ColorTools;
import com.github.tommyettinger.colorful.rgb.SimplePalette;

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
