package com.github.tommyettinger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.github.tommyettinger.colorful.hsluv.ColorTools;
import com.github.tommyettinger.colorful.hsluv.SimplePalette;

import static com.badlogic.gdx.Gdx.input;

public class HsluvDescriptionScreen extends ScreenAdapter {
    private Stage stage;
    private TextField tf;
    private final Color color = new Color();
    private final DescriptionDemo mainGame;

    public HsluvDescriptionScreen(DescriptionDemo main){
        mainGame = main;
    }

    @Override
    public void show() {
        Gdx.graphics.setTitle("HSLuv Color Description");
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        skin.getFont("default-font").getData().scale(2f);
        stage = new Stage();
        tf = new TextField("gray", skin);
        tf.setDisabled(false);
        Table tab = new Table(skin);
        tab.align(Align.center);
        tab.setFillParent(true);
        Label title = new Label("HSLuv Color Description", skin);
        title.setAlignment(Align.center);
        tab.add(title).growX().minWidth(300).row();
        tab.add(tf).center().growX();
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
        float c = SimplePalette.parseDescription(tf.getText());
        if(ColorTools.alphaInt(c) >= 254)
            ColorTools.toColor(color, c);
        ScreenUtils.clear(color);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }
}
