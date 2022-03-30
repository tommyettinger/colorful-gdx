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
import com.badlogic.gdx.utils.NumberUtils;
import com.github.tommyettinger.anim8.OtherMath;
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
    private float shape = 0.64516133f, turning = 0.95f;
    private final float iShape = 1.55f;
//    private float shape = 1.625f, turning = 0f;
//    private float shape = 1.1726f, turning = 0.1f;

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

        if(input.isKeyPressed(Input.Keys.LEFT))
            turning = Math.max(0f, turning - Gdx.graphics.getDeltaTime() * 0.25f);
        if(input.isKeyPressed(Input.Keys.RIGHT))
            turning = Math.min(1f, turning + Gdx.graphics.getDeltaTime() * 0.25f);
        if(input.isKeyPressed(Input.Keys.DOWN))
            shape = Math.max(0f, shape - Gdx.graphics.getDeltaTime() * 0.25f);
        if(input.isKeyPressed(Input.Keys.UP))
            shape = shape + Gdx.graphics.getDeltaTime() * 0.25f;

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
            batch.draw(pixel, 256f + j * 2f, 0, 2f, height * 0.2f);
        }
        batch.setPackedColor(Color.WHITE_FLOAT_BITS);
        stage.getRoot().draw(batch, 1);
        batch.end();
    }
    public static float oklabToRGBA(final float packed, final float shape, final float turning)
    {
        final int decoded = NumberUtils.floatToRawIntBits(packed);
        final float L = reverseLightConf((decoded & 0xff) / 255f, shape, turning);
        final float A = ((decoded >>> 8 & 0xff) - 127.5f) / 127.5f;
        final float B = ((decoded >>> 16 & 0xff) - 127.5f) / 127.5f;
        final float l = cube(L + 0.3963377774f * A + 0.2158037573f * B);
        final float m = cube(L - 0.1055613458f * A - 0.0638541728f * B);
        final float s = cube(L - 0.0894841775f * A - 1.2914855480f * B);
        final int r = (int)((float)Math.sqrt(Math.min(Math.max(+4.0767245293f * l - 3.3072168827f * m + 0.2307590544f * s, 0f), 1f)) * 255.999f);
        final int g = (int)((float)Math.sqrt(Math.min(Math.max(-1.2681437731f * l + 2.6093323231f * m - 0.3411344290f * s, 0f), 1f)) * 255.999f);
        final int b = (int)((float)Math.sqrt(Math.min(Math.max(-0.0041119885f * l - 0.7034763098f * m + 1.7068625689f * s, 0f), 1f)) * 255.999f);
        return NumberUtils.intBitsToFloat(r | g << 8 | b << 16 | (decoded & 0xfe000000));
    }
    private static float cube(final float x) {
        return x * x * x;
    }

    public static float reverseLightConf(float x, float s, float t){
        return OtherMath.barronSpline((float) Math.sqrt(x), 1f/s, t);
    }

//    public static float forwardLight(final float x) {
//        final float shape = 1.1726f, turning = 0.1f;
//        final float d = turning - x;
//        if(d < 0)
//            return ((1f - turning) * (x - 1f)) / (1f - (x + shape * d)) + 1f;
//        else
//            return (turning * x) / (1e-20f + (x + shape * d));
//    }
//
//    public static float reverseLight(final float x) {
//        final float shape = 0.8528f, turning = 0.1f;
//        final float d = turning - x;
//        if(d < 0)
//            return ((1f - turning) * (x - 1f)) / (1f - (x + shape * d)) + 1f;
//        else
//            return (turning * x) / (1e-20f + (x + shape * d));
//    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }
}
