package com.github.tommyettinger.colorful.hsi;

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
import com.github.tommyettinger.colorful.Shaders;

import static com.badlogic.gdx.Gdx.input;

public class HSITintDemo extends ApplicationAdapter {
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
    private ShaderProgram shaderHSI;
    private float hue = 0.5f, sat = 0f, intensity = 0.5f, alpha = 1f;

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("HSI Demo");
        config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
        config.setIdleFPS(10);
        config.useVsync(true);
//        config.setResizable(false);

        final HSITintDemo app = new HSITintDemo();
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
        Gdx.graphics.setWindowedMode(width = Math.min(screenTexture.getWidth() * 2, Gdx.graphics.getDisplayMode().width),
                height = Math.min(screenTexture.getHeight(), Gdx.graphics.getDisplayMode().height));
        screenView.update(width, height);
        screenView.getCamera().position.set(width * 0.5f, height * 0.5f, 0f);
    }

    @Override
    public void create() {
        defaultShader = SpriteBatch.createDefaultShader();
//        shaderBroken = new ShaderProgram(Shaders.vertexShader, Shaders.fragmentShaderHSL2);
//        if(!shaderBroken.isCompiled())
//            System.out.println(shaderBroken.getLog());
        shaderHSI = new ShaderProgram(Shaders.vertexShaderHSI, Shaders.fragmentShaderHSI);
        if(!shaderHSI.isCompiled())
            System.out.println(shaderHSI.getLog());
        batch = new SpriteBatch(8000, defaultShader);
        screenView = new ScreenViewport();
        screenView.getCamera().position.set(SCREEN_WIDTH * 0.5f, SCREEN_HEIGHT * 0.5f, 0);
        screenView.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.enableBlending();

        // if you don't have these files on this absolute path, that's fine, and they will be ignored
//        load("samples/Painting_by_Henri_Biva.jpg");
//        load("samples/Among_the_Sierra_Nevada_by_Albert_Bierstadt.jpg");
        load("samples/Mona_Lisa.jpg");
//        load("D:/bubble-translucent.png");
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handleInput();
        batch.setProjectionMatrix(screenView.getCamera().combined);
        if (screenTexture != null) { 
            batch.setShader(shaderHSI);
            // the bitwise AND with 0xFFFFFF is needed to make the millisecond time a usable size for a float
            // it causes the animation to jump, but very rarely, once every 18 minutes or so
            hue = (TimeUtils.millis() & 0xFFFFFF) * 0.0007f;
            // we need to make sure hue and lightness are in the 0.0 to 1.0 range. If they are positive, this does that.
            hue -= (int)hue;
//            lightness = (TimeUtils.millis() & 0xFFFFFF) * 0.0016f;
//            lightness -= (int)lightness;
            batch.setColor(hue, sat, intensity, alpha);
            batch.begin();
            batch.draw(screenTexture, 0, 0);
            //// double window width in load() and uncomment the following to show an un-shifted image at right
            batch.setShader(defaultShader);
            batch.setPackedColor(-0x1.fffffep126f); // packed white
            batch.draw(screenTexture, screenTexture.getWidth(), 0);
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
            System.out.println("H=" + hue + ",S=" + sat + ",I=" + intensity);
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
                hue = (hue += 0x7p-8f) - MathUtils.floor(hue);
            else if (input.isKeyPressed(Input.Keys.RIGHT)) //saturation increase
                sat = MathUtils.clamp(sat + 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.LEFT)) //saturation decrease
                sat = MathUtils.clamp(sat - 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.UP)) //lighten
                intensity = MathUtils.clamp(intensity + 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.DOWN)) //darken
                intensity = MathUtils.clamp(intensity - 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.R)) //reset
            {
                hue = 0.5f;
                sat = 0f;
                intensity = 0.5f;
                alpha = 1f;
            }
        }
    }

}
