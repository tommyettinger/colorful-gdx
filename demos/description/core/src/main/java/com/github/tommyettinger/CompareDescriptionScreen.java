package com.github.tommyettinger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
        Label title = new Label("Compare Color Descriptions", skin);
        title.setAlignment(Align.center);
        tab.add(title).colspan(2).growX().minWidth(300).row();
        tab.add("Description: ").left().minWidth(200);
        tab.add(nameField).center().growX().minHeight(100).row();
        tab.add("RGB:         ").center().grow().minWidth(200).row();
        tab.add("Oklab:       ").center().grow().minWidth(200).row();
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
        float r = SimplePalette.TRANSPARENT;
        float o = com.github.tommyettinger.colorful.oklab.SimplePalette.TRANSPARENT;
        float i = com.github.tommyettinger.colorful.ipt_hq.SimplePalette.TRANSPARENT;
        if(nameField.hasKeyboardFocus()) {
            r = SimplePalette.parseDescription(nameField.getText());
            if (ColorTools.alphaInt(r) >= 254) {
                o = com.github.tommyettinger.colorful.oklab.SimplePalette.parseDescription(nameField.getText());
                i = com.github.tommyettinger.colorful.ipt_hq.SimplePalette.parseDescription(nameField.getText());
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
        batch.draw(pixel, 0f, height * 0.5f, width, height * 0.25f);
        batch.setPackedColor(com.github.tommyettinger.colorful.oklab.ColorTools.toRGBA(o));
        batch.draw(pixel, 0f, height * 0.25f, width, height * 0.25f);
        batch.setPackedColor(com.github.tommyettinger.colorful.ipt_hq.ColorTools.toRGBA(i));
        batch.draw(pixel, 0f, 0f, width, height * 0.25f);
        batch.setPackedColor(Color.WHITE_FLOAT_BITS);
        stage.getRoot().draw(batch, 1);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }
}
