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

/**
 * See <a href="https://marioslab.io/posts/jitterbugs/">Mario Zechner's article.</a>
 */
public class FuzzyPixelsDemo extends ApplicationAdapter {
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
    contrast = 0.5f;

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Smooth/Fuzzy Pixels Demo");
        config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
        config.setIdleFPS(10);
        config.useVsync(true);
        final FuzzyPixelsDemo app = new FuzzyPixelsDemo();
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
    public static final String fragmentShaderFuzzy =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    "uniform vec2 u_texSize;\n" +
                    "const float scale = 4.0;\n" +
                    "void main()\n" +
                    "{\n" +
                    "    vec2 px = v_texCoords.xy * u_texSize.xy;\n" + // important; we do still need v_texCoords
                    "    vec2 uv = (floor(px) + 0.5);\n" +
                    "    uv += 1.0 - clamp((1.0 - fract(px)) * scale, 0.0, 1.0);\n" +
                    "    gl_FragColor = texture2D(u_texture, uv.xy / u_texSize.xy);\n" +
                    "}";

    @Override
    public void create() {
        defaultShader = SpriteBatch.createDefaultShader();
        shaderDN = new ShaderProgram(Shaders.vertexShaderDayNight, fragmentShaderFuzzy);
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
        
//        stage = new Stage();
    }

//    Stage stage;
    @Override
    public void render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handleInput();
        batch.setProjectionMatrix(screenView.getCamera().combined);
        if (screenTexture != null) {
            batch.setShader(shaderDN);
            batch.begin();
            // You must set this uniform for every change in the size of a Texture (not TextureRegion) you render.
            shaderDN.setUniformf("u_texSize", screenTexture.getWidth(), screenTexture.getHeight());
            // you can ignore the weird math here, it just moves the texture around in a circle.
            batch.draw(screenTexture, screenTexture.getWidth() * (-0.5f + 0.25f * MathUtils.sinDeg(TimeUtils.millis() >>> 4 & 0xFFFFF)),
                    screenTexture.getHeight() * (-0.5f + 0.25f * MathUtils.cosDeg(TimeUtils.millis() >>> 4 & 0xFFFFF))
                    // IMPORTANT: The shader operates on a larger texture than normal, scaled by the same value used in
                    // the shader called 'scale' (here, it's 4.0f).
                    , screenTexture.getWidth() * 4f, screenTexture.getHeight() * 4f
            );
            batch.end();
        }

    }

    @Override
    public void resize(int width, int height) {
        screenView.update(width, height);
        screenView.getCamera().position.set(width * 0.5f, height * 0.5f, 0f);
        screenView.getCamera().update();
    }

    // this stuff probably won't matter here or elsewhere.
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

