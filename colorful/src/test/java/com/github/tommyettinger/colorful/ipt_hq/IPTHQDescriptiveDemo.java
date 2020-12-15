package com.github.tommyettinger.colorful.ipt_hq;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3WindowAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.UIUtils;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.badlogic.gdx.Gdx.input;

public class IPTHQDescriptiveDemo extends ApplicationAdapter {
    public static final int SCREEN_WIDTH = 808;
    public static final int SCREEN_HEIGHT = 600;
    private Stage stage;
    private Skin skin;
    private TextField tf;
    private Image bg;
    private Color color = new Color();

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Descriptive Color Demo");
        config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
        config.setIdleFPS(20);
        config.useVsync(true);
        final IPTHQDescriptiveDemo app = new IPTHQDescriptiveDemo();
        new Lwjgl3Application(app, config);
    }

    @Override
    public void create() {
        skin = new Skin(Gdx.files.internal("uiskin.json"));
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
        if(ColorTools.alphaInt(c) >= 254)
            ColorTools.toColor(color, c);
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
