package com.github.tommyettinger.colorful;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.badlogic.gdx.Gdx.input;

public class HSLWheelDemo extends ApplicationAdapter {
    //public static final int backgroundColor = Color.rgba8888(Color.DARK_GRAY);
//    public static final int SCREEN_WIDTH = 1531;
//    public static final int SCREEN_HEIGHT = 862;
    public static final int SCREEN_WIDTH = 512;
    public static final int SCREEN_HEIGHT = 512;
    private SpriteBatch batch;
    private Viewport screenView;
    private BitmapFont font;
    private Texture blank;
    private long lastProcessedTime = 0L, startTime;
    private float layer = 0.5f;
    private ShaderProgram shader, otherShader;

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Color Wheel Demo");
        config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
        config.setIdleFPS(10);
        config.useVsync(true);
//        config.setResizable(false);

        final HSLWheelDemo app = new HSLWheelDemo();
        new Lwjgl3Application(app, config);
    }

    @Override
    public void create() {
        startTime = TimeUtils.millis();
        Pixmap b = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        b.drawPixel(0, 0, 0x7F7F81FF);
        blank = new Texture(b);
        font = new BitmapFont(Gdx.files.internal("font.fnt"));
        font.setColor(1f, 0.5f, 0.5f, 1f);
        shader = new ShaderProgram(Shaders.vertexShader, Shaders.fragmentShaderHSL2);
        if(!shader.isCompiled())
            System.out.println(shader.getLog());
        otherShader = new ShaderProgram(Shaders.vertexShader, Shaders.fragmentShaderHSLPsychedelic);
        if(!otherShader.isCompiled())
            System.out.println(otherShader.getLog());
        batch = new SpriteBatch(1000, shader);
//        basicBatch = new SpriteBatch();
        screenView = new ScreenViewport();
        screenView.getCamera().position.set(SCREEN_WIDTH * 0.5f, SCREEN_HEIGHT * 0.5f, 0);
        screenView.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.enableBlending();
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handleInput();
//        layer = TrigTools.acos_(TrigTools.sin_(TimeUtils.timeSinceMillis(startTime) * 0x1p-13f)) + 0.5f;
//        layer = TrigTools.acos_(TrigTools.sin_(TimeUtils.timeSinceMillis(startTime) * 0x1p-13f)) * 2f;
//        layer = TimeUtils.timeSinceMillis(startTime) * 0x1p-12f;
//        int floor = MathUtils.floorPositive(layer);
//        layer = (floor & 1) + (layer - floor) * (-(floor & 1) | 1);
        batch.setProjectionMatrix(screenView.getCamera().combined);
        batch.setColor(0f, 0f, 0.5f, 1f);
        batch.begin();
        batch.draw(blank, 0, 0, 512, 512);
        final float
                maxDist = 254f * TrigTools.sin_(layer * 0.5f) + 1f,
//                maxDist = 255f,
                iMax = 1f / maxDist;
        //final float circumference = 1605.3539f;//MathUtils.PI * 511f;
        batch.setColor(0, 0, layer, 1f);
        batch.draw(blank, 254.75f, 254.75f, 1.5f, 1.5f);
        float sat, offset = (TimeUtils.millis() & 4095) * 0x1p-12f;
        for (int dist = 1; dist <= maxDist; dist++) {
            final int circ = dist * 6;
            final float ic = 1f / circ;
            for (int t = 0; t < circ; t++) {
                final float angle = t * ic, x = TrigTools.cos_(angle), y = TrigTools.sin_(angle);
                sat = dist * iMax;// * (0.5f - Math.abs(layer - 0.5f)) * 2f;
//                sat = dist * iMax * Shaders.gamutHSL(angle, layer);
//                if((Math.abs(x) + Math.abs(y) + Math.abs(2f * (layer - 0.5f))) * dist > maxDist)
//                    continue;
//                if(!Shaders.inGamutHSL(angle, sat, layer))
//                    continue;
                batch.setColor(angle + offset - (int)(angle + offset), sat, layer, 1f);
                batch.draw(blank, 254.75f + x * dist, 254.75f + y * dist, 1.5f, 1.5f);
            }
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        screenView.update(width, height);
        screenView.getCamera().position.set(width * 0.5f, height * 0.5f, 0f);
        screenView.getCamera().update();
    }

    public void handleInput() {
        if (input.isKeyPressed(Input.Keys.Q) || input.isKeyPressed(Input.Keys.ESCAPE)) //quit
            Gdx.app.exit();
        else if (TimeUtils.timeSinceMillis(lastProcessedTime) > 150) {
            lastProcessedTime = TimeUtils.millis();
            if (input.isKeyPressed(Input.Keys.RIGHT) || input.isKeyPressed(Input.Keys.UP)) {
                layer = MathUtils.clamp(layer + 0x1p-7f, 0f, 1f);
            } else if (input.isKeyPressed(Input.Keys.LEFT) || input.isKeyPressed(Input.Keys.DOWN)) {
                layer = MathUtils.clamp(layer - 0x1p-7f, 0f, 1f);
            } else if (input.isKeyPressed(Input.Keys.R)) // random
            {
                layer = MathUtils.random();
            } else if (input.isKeyPressed(Input.Keys.S)) // shader
            {
                batch.setShader(batch.getShader() == shader ? otherShader : shader); 
            }
        }
    }
}
