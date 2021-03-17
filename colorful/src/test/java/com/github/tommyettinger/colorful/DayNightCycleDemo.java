package com.github.tommyettinger.colorful;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3WindowAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.badlogic.gdx.Gdx.input;

public class DayNightCycleDemo  extends ApplicationAdapter {
    //public static final int backgroundColor = Color.rgba8888(Color.DARK_GRAY);
//    public static final int SCREEN_WIDTH = 1531;
//    public static final int SCREEN_HEIGHT = 862;
    public static final int SCREEN_WIDTH = 808;
    public static final int SCREEN_HEIGHT = 600;
    protected SpriteBatch batch;
    protected Viewport screenView;
    protected Texture screenTexture;

    private long lastProcessedTime = 0L;
    private ShaderProgram defaultShader;
    private ShaderProgram shaderDN;
    private boolean flipping = true;
    private float r = 0.5f, g = 0.5f, b = 0.5f, // all neutral values
    //// contrast can be used by some shaders as alpha/opacity; it's currently lightness contrast
    contrast = 0.5f;

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Day/Night Cycle Demo");
        config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
        config.setIdleFPS(10);
        config.useVsync(true);
        final DayNightCycleDemo app = new DayNightCycleDemo();
        config.setWindowListener(new Lwjgl3WindowAdapter() {
            @Override
            public void filesDropped(String[] files) {
                if (files != null && files.length > 0) {
                    if (files[0].endsWith(".png") || files[0].endsWith(".jpg") || files[0].endsWith(".jpeg"))
                        app.load(files[0]);
                }
            }
        });

        new Lwjgl3Application(app, config);
    }

    public void load(String name) {
        //// loads a file by its full path, which we get via drag+drop
        FileHandle file = Gdx.files.absolute(name);
        if (!file.exists())
        {
            file = Gdx.files.classpath(name);
            if(!file.exists())
                return;
        }
        if (screenTexture != null) screenTexture.dispose();
        screenTexture = new Texture(file);
        screenTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        int width, height;
        Gdx.graphics.setWindowedMode(width = Math.min(screenTexture.getWidth(), Gdx.graphics.getDisplayMode().width),
                height = Math.min(screenTexture.getHeight(), Gdx.graphics.getDisplayMode().height));
        screenView.update(width, height);
        screenView.getCamera().position.set(width * 0.5f, height * 0.5f, 0f);
    }

    @Override
    public void create() {
        defaultShader = SpriteBatch.createDefaultShader();
        shaderDN = new ShaderProgram(Shaders.vertexShaderDayNight, Shaders.fragmentShaderDayNight);
        if(!shaderDN.isCompiled())
            System.out.println(shaderDN.getLog());
        batch = new SpriteBatch(1000, defaultShader);
        screenView = new ScreenViewport();
        screenView.getCamera().position.set(SCREEN_WIDTH * 0.5f, SCREEN_HEIGHT * 0.5f, 0);
        screenView.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.enableBlending();

        // if you don't have these files on this absolute path, that's fine, and they will be ignored
//        load("samples/Painting_by_Henri_Biva.jpg");
//        load("samples/Among_the_Sierra_Nevada_by_Albert_Bierstadt.jpg");
        load("samples/Dawnlike.png");
//        load("C:/d/Art/translucent-bubble.png");
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handleInput();
        batch.setProjectionMatrix(screenView.getCamera().combined);
        if (screenTexture != null) {
            batch.setShader(shaderDN);
            // It is more likely that you would set u_timeOfDay based on an in-game clock, not the actual time.
            // This next line is roughly the same as: (System.currentTimeMillis() % 1048576L) / 256.0f
            // It's just a little faster and more precise. It makes time change in 1/256 increments every millisecond.
            shaderDN.setUniformf("u_timeOfDay", (System.currentTimeMillis() & 0xFFFFFL) * 0x1p-8f);
            batch.begin();
            batch.draw(screenTexture, 0, 0);
            //// double window width in load() and uncomment the following to show an un-shifted image at right
//            batch.setShader(defaultShader);
//            batch.setPackedColor(-0x1.fffffep126f); // packed white
//            batch.draw(screenTexture, screenTexture.getWidth(), 0);
            batch.end();
        }

    }

    @Override
    public void resize(int width, int height) {
        screenView.update(width, height);
        screenView.getCamera().position.set(width * 0.5f, height * 0.5f, 0f);
        screenView.getCamera().update();
    }

    public void handleInput() {
        if (input.isKeyPressed(Input.Keys.P)) // print
            System.out.println("R=" + r + ",G=" + g + ",B=" + b);
        else if (input.isKeyPressed(Input.Keys.D))
            load("samples/Dawnlike.png");
        else if (input.isKeyPressed(Input.Keys.M))
            load("samples/Mona_Lisa.jpg");
        else if (input.isKeyPressed(Input.Keys.S)) //Sierra Nevada
            load("samples/Among_the_Sierra_Nevada_by_Albert_Bierstadt.jpg");
        else if (input.isKeyPressed(Input.Keys.B)) // Biva
            load("samples/Painting_by_Henri_Biva.jpg");
        else if (input.isKeyPressed(Input.Keys.C)) // Color Guard, pixel art cartoon-wargame style
            load("samples/Color_Guard.png");
        else if (input.isKeyPressed(Input.Keys.G)) // grayscale palette
            load("samples/Grayscale_Spaceships.png");
        else if (input.isKeyPressed(Input.Keys.A)) // higher-color atlas
            load("samples/Spaceships.png");
        else if (input.isKeyPressed(Input.Keys.Q) || input.isKeyPressed(Input.Keys.ESCAPE)) //quit
            Gdx.app.exit();
        else {
            // only process once every 80 ms, or just about 12 times a second, at most
            if (TimeUtils.timeSinceMillis(lastProcessedTime) < 80)
                return;
            lastProcessedTime = TimeUtils.millis();
            if (input.isKeyPressed(Input.Keys.H)) //hue
                r = (r += 0x7p-8f) - MathUtils.floor(r);
            else if (input.isKeyPressed(Input.Keys.RIGHT)) //saturation increase
                g = MathUtils.clamp(g + 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.LEFT)) //saturation decrease
                g = MathUtils.clamp(g - 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.UP)) //lighten
                b = MathUtils.clamp(b + 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.DOWN)) //darken
                b = MathUtils.clamp(b - 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.RIGHT_BRACKET)) //contrast increase
                contrast = MathUtils.clamp(contrast + 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.LEFT_BRACKET)) //contrast decrease
                contrast = MathUtils.clamp(contrast - 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.R)) //reset
            {
                r = 0.5f;
                g = 0.5f;
                b = 0.5f;
                contrast = 0.5f;
            }
            else if (input.isKeyPressed(Input.Keys.F))
                flipping = !flipping;
        }
    }

}

