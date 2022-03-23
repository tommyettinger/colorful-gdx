package com.github.tommyettinger.colorful;

import com.badlogic.gdx.*;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.github.tommyettinger.colorful.rgb.ColorTools;
import com.github.tommyettinger.colorful.rgb.SimplePalette;

import static com.badlogic.gdx.Gdx.input;

public class CompareLightnessDemo extends ApplicationAdapter {
    public static final int SCREEN_WIDTH = 512 + 256;
    public static final int SCREEN_HEIGHT = 600;
    private Stage stage;
    private TextureAtlas.AtlasRegion pixel;
    private final Color color = new Color();
    private float r = SimplePalette.TRANSPARENT;
//    private float shape = 1f, turning = 0.5f;
    private float shape = 1.1726f, turning = 0.1f;

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Descriptive Comparison Demo");
        config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
        config.setIdleFPS(10);
        config.setForegroundFPS(20);
        config.useVsync(true);
        config.setResizable(false);
        config.disableAudio(true);
        final CompareLightnessDemo app = new CompareLightnessDemo();
        new Lwjgl3Application(app, config);
    }

    @Override
    public void create() {
        Gdx.graphics.setTitle("Compare Color Description");
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        skin.getFont("default-font").getData().scale(2f);
        pixel = skin.getAtlas().findRegion("white");
        stage = new Stage();
        ColorTools.toColor(color, SimplePalette.GRAY);
        Table tab = new Table(skin);
        tab.align(Align.center);
        tab.setFillParent(true);
//        Label title = new Label("Compare Lightness", skin);
//        title.setAlignment(Align.center);
//        tab.add(title).colspan(2).growX().minWidth(300).row();
        tab.add("RGB:         ").center().grow().minWidth(200).row();
        tab.add("Hsluv:       ").center().grow().minWidth(200).row();
        tab.add("YCwCm:       ").center().grow().minWidth(200).row();
        tab.add("Oklab:       ").center().grow().minWidth(200).row();
        tab.add("IPT_HQ:      ").center().grow().minWidth(200);
        stage.getRoot().addActor(tab);
        input.setInputProcessor(new InputMultiplexer(new InputAdapter(){
            @Override
            public boolean keyDown(int keycode) {
                switch (keycode){
                    case Input.Keys.LEFT: turning = Math.max(0f, turning - Gdx.graphics.getDeltaTime() * 0.25f);
                    return true;
                    case Input.Keys.RIGHT: turning = Math.min(1f, turning + Gdx.graphics.getDeltaTime() * 0.25f);
                    return true;
                    case Input.Keys.DOWN: shape = Math.max(0f, shape - Gdx.graphics.getDeltaTime() * 0.25f);
                    return true;
                    case Input.Keys.UP: shape = shape + Gdx.graphics.getDeltaTime() * 0.25f;
                    return true;
                    case Input.Keys.S:
                    case Input.Keys.P:
                        System.out.printf("turning=%1.5f, shape=%1.5f\n", turning, shape);
                }
                return false;
            }
        }, stage));
    }

    @Override
    public void render() {
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
        for (int j = 0; j < 256; j++) {
            r = j / 255f;
            batch.setPackedColor(ColorTools.rgb(r, r, r, 1f));
            batch.draw(pixel, 256f + j * 2f, height * 0.8f, 2f, height * 0.2f);
            batch.setPackedColor(com.github.tommyettinger.colorful.hsluv.ColorTools.toRGBA(com.github.tommyettinger.colorful.hsluv.ColorTools.hsluv(0.5f, 0f, (r), 1f)));
//            batch.setPackedColor(com.github.tommyettinger.colorful.hsluv.ColorTools.toRGBA(com.github.tommyettinger.colorful.hsluv.ColorTools.hsluv(0.5f, 0f, barronSpline(r, 1.1726f, 0.1f), 1f)));
            batch.draw(pixel, 256f + j * 2f, height * 0.6f, 2f, height * 0.2f);
            batch.setPackedColor(com.github.tommyettinger.colorful.ycwcm.ColorTools.toRGBA(com.github.tommyettinger.colorful.ycwcm.ColorTools.ycwcm(r, 0.5f, 0.5f, 1f)));
            batch.draw(pixel, 256f + j * 2f, height * 0.4f, 2f, height * 0.2f);
            batch.setPackedColor(com.github.tommyettinger.colorful.oklab.ColorTools.toRGBA(com.github.tommyettinger.colorful.oklab.ColorTools.oklab(r, 0.5f, 0.5f, 1f)));
            batch.draw(pixel, 256f + j * 2f, height * 0.2f, 2f, height * 0.2f);
            batch.setPackedColor(com.github.tommyettinger.colorful.ipt_hq.ColorTools.toRGBA(com.github.tommyettinger.colorful.ipt_hq.ColorTools.ipt(r, 0.5f, 0.5f, 1f)));
            batch.draw(pixel, 256f + j * 2f, 0f, 2f, height * 0.2f);
        }
        batch.setPackedColor(Color.WHITE_FLOAT_BITS);
        stage.getRoot().draw(batch, 1);
        batch.end();
    }

    public static float forwardLight(final float x) {
        final float shape = 1.1726f, turning = 0.1f;
        final float d = turning - x;
        if(d < 0)
            return ((1f - turning) * (x - 1f)) / (1f - (x + shape * d)) + 1f;
        else
            return (turning * x) / (1e-20f + (x + shape * d));
    }

    public static float reverseLight(final float x) {
        final float shape = 0.8528f, turning = 0.1f;
        final float d = turning - x;
        if(d < 0)
            return ((1f - turning) * (x - 1f)) / (1f - (x + shape * d)) + 1f;
        else
            return (turning * x) / (1e-20f + (x + shape * d));
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }
}
