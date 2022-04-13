package com.github.tommyettinger.colorful;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.github.tommyettinger.colorful.rgb.ColorTools;
import com.github.tommyettinger.colorful.rgb.SimplePalette;

import static com.badlogic.gdx.Gdx.input;

public class CompareDescriptionDemo extends ApplicationAdapter {
    public static final int SCREEN_WIDTH = 808;
    public static final int SCREEN_HEIGHT = 600;
    private Stage stage;
    private TextField nameField;
    private TextureAtlas.AtlasRegion pixel;
    private final Color color = new Color();
    private float r = SimplePalette.TRANSPARENT;
    private float o = com.github.tommyettinger.colorful.oklab.SimplePalette.TRANSPARENT;
    private float i = com.github.tommyettinger.colorful.ipt_hq.SimplePalette.TRANSPARENT;
    private float c = com.github.tommyettinger.colorful.cielab.SimplePalette.TRANSPARENT;
    private float h = com.github.tommyettinger.colorful.hsluv.SimplePalette.TRANSPARENT;

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Descriptive Comparison Demo");
        config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
        config.setIdleFPS(10);
        config.setForegroundFPS(20);
        config.useVsync(true);
        config.disableAudio(true);
        final CompareDescriptionDemo app = new CompareDescriptionDemo();
        new Lwjgl3Application(app, config);
    }

    @Override
    public void create() {
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
        input.setInputProcessor(stage);
    }

    @Override
    public void render() {
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
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
