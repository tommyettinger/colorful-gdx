package com.github.tommyettinger.colorful.oklab;

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
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.tommyettinger.colorful.Shaders;
import com.github.tommyettinger.colorful.TrigTools;

import static com.badlogic.gdx.Gdx.input;

public class OklabByHCLWheelDemo extends ApplicationAdapter {
    public static final int SCREEN_WIDTH = 512;
    public static final int SCREEN_HEIGHT = 512;
    private SpriteBatch batch;
    private Viewport screenView;
    private BitmapFont font;
    private Texture blank;
    private RandomXS128 random;
    private long lastProcessedTime = 0L, startTime;
    private float layer = 0.5f;

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Color Wheel Demo");
        config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
        config.setIdleFPS(10);
        config.useVsync(true);
//        config.setResizable(false);

        final OklabByHCLWheelDemo app = new OklabByHCLWheelDemo();
        new Lwjgl3Application(app, config);
    }

    @Override
    public void create() {
        startTime = TimeUtils.millis();
        random = new RandomXS128(startTime);
        Pixmap b = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        b.drawPixel(0, 0, 0x7F7F81FF);
        blank = new Texture(b);
        font = new BitmapFont(Gdx.files.internal("font.fnt"));
        font.setColor(1f, 0.5f, 0.5f, 1f);
        ShaderProgram shader = new ShaderProgram(Shaders.vertexShader, Shaders.fragmentShaderOklab);
        if(!shader.isCompiled())
            System.out.println(shader.getLog());
        batch = new SpriteBatch(2000, shader);
        screenView = new ScreenViewport();
        screenView.getCamera().position.set(SCREEN_WIDTH * 0.5f, SCREEN_HEIGHT * 0.5f, 0);
        screenView.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        for (int li = 0; li < 256; li++) {
            for (int h = 0; h < 256; h++) {
                System.out.printf("%d %d %1.5f: %01.5f\n", h, li, ColorTools.chromaLimit(h * 0x1p-8f, li * 0x1p-8f), ColorTools.chroma(ColorTools.oklabByHSL(h * 0x1p-8f, 1f, li * 0x1p-8f, 1f)));
            }
        }
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handleInput();
        layer = TimeUtils.timeSinceMillis(startTime) * 0x1p-12f;
//        boolean showOut = (TimeUtils.timeSinceMillis(startTime) & 0x100L) == 0L;
        int floor = MathUtils.floorPositive(layer);
        layer = (floor & 1) + (layer - floor) * (-(floor & 1) | 1);
        batch.setProjectionMatrix(screenView.getCamera().combined);
        batch.setColor(0.5f, 0.5f, 0.5f, 1f);
        batch.begin();
        batch.draw(blank, 0, 0, 512, 512);
        final float
                maxDist = 254f * TrigTools.sin_(layer * 0.5f) + 1f,
                iMax = 1f / maxDist;
        for (int dist = 0; dist <= maxDist; dist++) {
            final int circ = dist * 16;
            final float ic = 1f / circ;
            for (int t = 0; t < circ; t++) {
                final float angle = t * ic, x = TrigTools.cos_(angle), y = TrigTools.sin_(angle);
//                final float g = ColorTools.getRawGamutValue((int)(layer * 255.999f) << 8 | (int)(angle * 256f));
//                if(g < dist) continue;
                final float chr = dist * iMax;// * (0.5f - Math.abs(layer - 0.5f)) * 2f;
                if(random.nextLong() < 0x6800000000000000L && chr > ColorTools.chromaLimit(angle, layer)) continue;
                batch.setPackedColor(ColorTools.oklabByHCL(angle, chr, layer, 1f));
                batch.draw(blank, 255.5f + x * dist, 255.5f + y * dist, 1f, 1f);
            }
        }
        batch.setColor(layer * 0.9f + 0.05f, 0.5f, 0.5f, 1f);
        batch.draw(blank, 255f, 255f, 2f, 2f);
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
                layer = random.nextFloat();
            }
        }
    }
}
