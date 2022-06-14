package com.github.tommyettinger.colorful.rgb;

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
import com.badlogic.gdx.scenes.scene2d.utils.UIUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.badlogic.gdx.Gdx.input;

public class RGBTintDemo extends ApplicationAdapter {
    //public static final int backgroundColor = Color.rgba8888(Color.DARK_GRAY);
//    public static final int SCREEN_WIDTH = 1531;
//    public static final int SCREEN_HEIGHT = 862;
    public static final int SCREEN_WIDTH = 808;
    public static final int SCREEN_HEIGHT = 600;
    protected SpriteBatch batch;
    protected ColorfulBatch colorfulBatch;
    protected Viewport screenView;
    protected Texture screenTexture;

    private long lastProcessedTime = 0L;
    private float red = 0.5f, green = 0.5f, blue = 0.5f, contrast = 0.5f;

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Tint Demo");
        config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
        config.setIdleFPS(10);
        config.useVsync(true);
//        config.setResizable(false);
        config.enableGLDebugOutput(true, System.out);
        ShaderProgram.prependVertexCode = "#version 110\n";
        ShaderProgram.prependFragmentCode = "#version 110\n";

        final RGBTintDemo app = new RGBTintDemo();
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
        batch = new SpriteBatch();
        colorfulBatch = new ColorfulBatch();


        String vertexShader = "attribute vec4 " + ShaderProgram.POSITION_ATTRIBUTE + ";\n"
                + "attribute vec4 " + ShaderProgram.COLOR_ATTRIBUTE + ";\n"
                + "attribute vec2 " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n"
                + "attribute vec4 " + "a_tweak" + ";\n"
                + "uniform mat4 u_projTrans;\n"
                + "varying vec4 v_color;\n"
                + "varying vec4 v_tweak;\n"
                + "varying vec2 v_texCoords;\n"
                + "\n"
                + "void main()\n"
                + "{\n"
                + "   v_color = " + ShaderProgram.COLOR_ATTRIBUTE + ";\n"
                + "   v_color.rgb = v_color.rgb * 0.5 - 0.25;\n"
                + "   v_color.a = v_color.a * (255.0/254.0);\n"
                + "   v_tweak = " + "a_tweak" + ";\n"
                + "   v_tweak.a = v_tweak.a * (255.0/254.0);\n"
                + "   v_texCoords = " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n"
                + "   gl_Position =  u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n"
                + "}\n";
        String fragmentShader =
                "#ifdef GL_ES\n" +
                        "#define LOWP lowp\n" +
                        "precision mediump float;\n" +
                        "#else\n" +
                        "#define LOWP \n" +
                        "#endif\n" +
                        "varying vec2 v_texCoords;\n" +
                        "varying LOWP vec4 v_color;\n" +
                        "varying LOWP vec4 v_tweak;\n" +
                        "uniform sampler2D u_texture;\n" +
                        "vec3 barronSpline(vec3 x, float shape) {\n" +
                        "        const float turning = 0.5;\n" +
                        "        vec3 d = turning - x;\n" +
                        "        return mix(\n" +
                        "          ((1. - turning) * (x - 1.)) / (1. - (x + shape * d)) + 1.,\n" +
                        "          (turning * x) / (1.0e-20 + (x + shape * d)),\n" +
                        "          step(0.0, d));\n" +
                        "}\n" +
                        "void main()\n" +
                        "{\n" +
                        "  vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                        "  tgt.rgb = barronSpline(clamp(tgt.rgb * v_tweak.rgb * 2.0 + v_color.rgb, 0.0, 1.0), v_tweak.a * v_tweak.a * 3.0 + 0.25);\n" +
                        "  tgt.a *= v_color.a;\n" +
                        "  gl_FragColor = tgt;\n" +
                        "}";
        ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShader);
        if (!shader.isCompiled()) throw new IllegalArgumentException("Error compiling shader: " + shader.getLog());
        colorfulBatch.setShader(shader);

        screenView = new ScreenViewport();
        screenView.getCamera().position.set(SCREEN_WIDTH * 0.5f, SCREEN_HEIGHT * 0.5f, 0);
        screenView.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.enableBlending();

        // if you don't have these files on this absolute path, that's fine, and they will be ignored
//        load("samples/Painting_by_Henri_Biva.jpg");
//        load("samples/Among_the_Sierra_Nevada_by_Albert_Bierstadt.jpg");
        load("samples/Mona_Lisa.jpg");
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handleInput();
        batch.setProjectionMatrix(screenView.getCamera().combined);
        colorfulBatch.setProjectionMatrix(screenView.getCamera().combined);
        if (screenTexture != null) {
            colorfulBatch.setColor(red, green, blue, 1f);
            colorfulBatch.setTweak(0.5f, 0.5f, 0.5f, contrast);
            colorfulBatch.begin();
            colorfulBatch.draw(screenTexture, 0, 0);
            colorfulBatch.end();
            batch.begin();
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
        if (input.isKeyPressed(Input.Keys.V)) // view
            System.out.println("R=" + red + ",G=" + green + ",B=" + blue + ",C=" + contrast);
        else if (input.isKeyPressed(Input.Keys.M))
            load("samples/Mona_Lisa.jpg");
        else if (input.isKeyPressed(Input.Keys.S)) //Sierra Nevada
            load("samples/Among_the_Sierra_Nevada_by_Albert_Bierstadt.jpg");
        else if (input.isKeyPressed(Input.Keys.P)) // Pond
            load("samples/Painting_by_Henri_Biva.jpg");
        else if (input.isKeyPressed(Input.Keys.W)) // Wargame, pixel art cartoon style
            load("samples/Color_Guard.png");
        else if (input.isKeyPressed(Input.Keys.A)) // grayscale palette
            load("samples/Grayscale_Spaceships.png");
        else if (input.isKeyPressed(Input.Keys.Q) || input.isKeyPressed(Input.Keys.ESCAPE)) //quit
            Gdx.app.exit();
        else {
            // only process once every 150 ms, or just over 6 times a second, at most
            if (TimeUtils.timeSinceMillis(lastProcessedTime) < 150)
                return;
            lastProcessedTime = TimeUtils.millis();
            if (input.isKeyPressed(Input.Keys.R)) //light
                red = MathUtils.clamp(red + (UIUtils.shift() ? -0x3p-7f : 0x3p-7f), 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.G)) //warm
                green = MathUtils.clamp(green + (UIUtils.shift() ? -0x3p-7f : 0x3p-7f), 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.B)) //mild
                blue = MathUtils.clamp(blue + (UIUtils.shift() ? -0x3p-7f : 0x3p-7f), 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.C)) //contrast
                contrast = MathUtils.clamp(contrast + (UIUtils.shift() ? -0x3p-7f : 0x3p-7f), 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.Z)) // zero changes
            {
                red = 0.5f;
                green = 0.5f;
                blue = 0.5f;
                contrast = 0.5f;
            }
        }
    }

}
