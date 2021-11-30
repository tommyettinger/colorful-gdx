package com.github.tommyettinger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
        tab.add(title).growX().minWidth(300).row();
        tab.add(tf).left().growX();
        tab.add(tf2).right().row();
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
        float c = SimplePalette.parseDescription(tf.getText());
        boolean changed = false;//colors.size != Gdx.graphics.getBackBufferWidth()
        if(ColorTools.alphaInt(c) >= 254){
            changed |= start != (start = c);
        }
        c = SimplePalette.parseDescription(tf2.getText());
        if(ColorTools.alphaInt(c) >= 24){
            changed |= end != (end = c);
        }
        if(changed){
            colors.clear();
            GradientTools.appendGradient(colors, start, end, Gdx.graphics.getBackBufferWidth(), interpolation);
//            System.out.println(colors.size + " " + Gdx.graphics.getBackBufferWidth());
        }
        stage.act();
        Camera camera = stage.getViewport().getCamera();
        camera.update();

        if (!stage.getRoot().isVisible()) return;

        Batch batch = stage.getBatch();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        float width = Gdx.graphics.getBackBufferWidth(), height = Gdx.graphics.getBackBufferHeight();
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
