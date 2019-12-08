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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.badlogic.gdx.Gdx.input;

public class TintDemo extends ApplicationAdapter {
    //public static final int backgroundColor = Color.rgba8888(Color.DARK_GRAY);
//    public static final int SCREEN_WIDTH = 1531;
//    public static final int SCREEN_HEIGHT = 862;
    public static final int SCREEN_WIDTH = 808;
    public static final int SCREEN_HEIGHT = 600;
    protected SpriteBatch batch;
    protected Viewport screenView;
    protected Texture screenTexture;
    protected BitmapFont font;

    private long startTime = 0L, lastProcessedTime = 0L;
    private ShaderProgram defaultShader;
    private ShaderProgram shader, shader2, shaderCB;
    private Texture palette;
    private float luma = 0.5f, warm = 0.5f, mild = 0.5f, opacity = 1f;

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Tint Demo");
        config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
        config.setIdleFPS(10);
        config.useVsync(true);
//        config.setResizable(false);

        final TintDemo app = new TintDemo();
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
            return;
        if (screenTexture != null) screenTexture.dispose();
        screenTexture = new Texture(file);
        screenTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        int width, height;
        Gdx.graphics.setWindowedMode(width = Math.min(screenTexture.getWidth() * 2, Gdx.graphics.getDisplayMode().width),
                height = Math.min(screenTexture.getHeight(), Gdx.graphics.getDisplayMode().height));
        screenView.update(width, height);
        screenView.getCamera().position.set(width * 0.5f, height * 0.5f, 0f);
    }

    @Override
    public void create() {
        font = new BitmapFont();
        defaultShader = SpriteBatch.createDefaultShader();
        shader = new ShaderProgram(Basics.vertexShader, Basics.fragmentShader);
        if (!shader.isCompiled()) throw new GdxRuntimeException("Couldn't compile shader: " + shader.getLog());
        batch = new SpriteBatch(1000, defaultShader);
        screenView = new ScreenViewport();
        screenView.getCamera().position.set(SCREEN_WIDTH * 0.5f, SCREEN_HEIGHT * 0.5f, 0);
        screenView.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.enableBlending();

        // if you don't have these files on this absolute path, that's fine, and they will be ignored
//        load("D:/Painting_by_Henri_Biva.jpg");
//        load("D:/Among_the_Sierra_Nevada_by_Albert_Bierstadt.jpg");
        load("samples/Mona_Lisa.jpg");
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handleInput();
        batch.setProjectionMatrix(screenView.getCamera().combined);
        if (screenTexture != null) {
            batch.setShader(shader);
            batch.setColor(luma, warm, mild, opacity);
            batch.begin();
            batch.draw(screenTexture, 0, 0);
            batch.setShader(defaultShader);
            batch.setPackedColor(-0x1.fffffep126f); // packed white
            batch.draw(screenTexture, screenTexture.getWidth(), 0);
        } else {
            batch.begin();
            font.draw(batch, "Drag and drop an image file onto this window", 20, 150);
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
        if (input.isKeyPressed(Input.Keys.P)) // print
            System.out.println("Y=" + luma + ",Cw=" + warm + ",Cm=" + mild);
        else if (input.isKeyPressed(Input.Keys.M))
            load("samples/Mona_Lisa.jpg");
        else if (input.isKeyPressed(Input.Keys.S)) //Sierra Nevada
            load("samples/Among_the_Sierra_Nevada_by_Albert_Bierstadt.jpg");
        else if (input.isKeyPressed(Input.Keys.B)) // Biva
            load("samples/Painting_by_Henri_Biva.jpg");
        else if (input.isKeyPressed(Input.Keys.C)) // Color Guard, pixel art cartoon-wargame style
            load("samples/Color_Guard.png");
        else if (input.isKeyPressed(Input.Keys.G)) // grayscale palette
            load("samples/GrayscaleSpaceships.png");
        else if (input.isKeyPressed(Input.Keys.A)) // higher-color atlas
            load("samples/Spaceships.png");
        else if (input.isKeyPressed(Input.Keys.Q) || input.isKeyPressed(Input.Keys.ESCAPE)) //quit
            Gdx.app.exit();
        else {
            // only process once every 166 ms, or 6 times a second, at most
            if (TimeUtils.timeSinceMillis(lastProcessedTime) < 166)
                return;
            lastProcessedTime = TimeUtils.millis();
            if (input.isKeyPressed(Input.Keys.L)) //light
                luma = MathUtils.clamp(luma + 0x1p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.D)) //dark
                luma = MathUtils.clamp(luma - 0x1p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.RIGHT)) //warm
                warm = MathUtils.clamp(warm + 0x1p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.LEFT)) //cool
                warm = MathUtils.clamp(warm - 0x1p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.UP)) //mild
                mild = MathUtils.clamp(mild + 0x1p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.DOWN)) // bold
                mild = MathUtils.clamp(mild - 0x1p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.R)) // reset
            {
                luma = 0.5f;
                warm = 0.5f;
                mild = 0.5f;
                opacity = 1f;
            }
        }
    }

}