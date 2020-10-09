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

public class IPTTintDemo extends ApplicationAdapter {
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
    private ShaderProgram shader;
    private float intens = 0.5f, protan = 0.5f, tritan = 0.5f, opacity = 1f;

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Tint Demo");
        config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
        config.setIdleFPS(10);
        config.useVsync(true);
//        config.setResizable(false);

        final IPTTintDemo app = new IPTTintDemo();
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
        defaultShader = SpriteBatch.createDefaultShader();
        String fragmentShader =
                "#ifdef GL_ES\n" +
                        "#define LOWP lowp\n" +
                        "precision mediump float;\n" +
                        "#else\n" +
                        "#define LOWP \n" +
                        "#endif\n" +
                        "varying vec2 v_texCoords;\n" +
                        "varying LOWP vec4 v_color;\n" +
                        "uniform sampler2D u_texture;\n" +
                        "void main()\n" +
                        "{\n" +
                        "    vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                        "    vec3 ipt = (mat3(+0.4000, +6.6825, +1.0741, +0.4000, -7.2765, +0.4763, +0.2000, +0.5940, -1.5504) * \n" +
                        "        (pow(mat3(0.313921, 0.151693, 0.017700, 0.639468, 0.748209, 0.109400, 0.0465970, 0.1000044, 0.8729000) * (tgt.rgb), vec3(0.43))))\n" +
                        "        + v_color.rgb - 0.5;\n" +
                        "    vec3 back = mat3(1.0, 1.0, 1.0, 0.06503950, -0.07591241, 0.02174116, 0.15391950, 0.09991275, -0.50766750) * ipt;\n" +
                        "    back = mat3(5.432622, -1.10517, 0.028104, -4.67910, 2.311198, -0.19466, 0.246257, -0.20588, 1.166325) * \n" +
                        "        (pow(abs(back), vec3(2.3256)) * sign(back));\n" +
                        "    gl_FragColor = vec4(clamp(back, 0.0, 1.0), v_color.a * tgt.a);\n" +
                        "}";
        shader = new ShaderProgram(Shaders.vertexShader, fragmentShader);
        if (!shader.isCompiled()) throw new IllegalArgumentException("Error compiling shader: " + shader.getLog());
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
        if (screenTexture != null) {
            batch.setShader(shader);
            batch.setColor(intens, protan, tritan, opacity);
            batch.begin();
            batch.draw(screenTexture, 0, 0);
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
            System.out.println("I=" + intens + ",P=" + protan + ",T=" + tritan);
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
            // only process once every 150 ms, or just over 6 times a second, at most
            if (TimeUtils.timeSinceMillis(lastProcessedTime) < 150)
                return;
            lastProcessedTime = TimeUtils.millis();
            if (input.isKeyPressed(Input.Keys.L)) //light
                intens = MathUtils.clamp(intens + 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.D)) //dark
                intens = MathUtils.clamp(intens - 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.RIGHT)) //warm
                protan = MathUtils.clamp(protan + 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.LEFT)) //cool
                protan = MathUtils.clamp(protan - 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.UP)) //mild
                tritan = MathUtils.clamp(tritan + 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.DOWN)) // bold
                tritan = MathUtils.clamp(tritan - 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.R)) // reset
            {
                intens = 0.5f;
                protan = 0.5f;
                tritan = 0.5f;
                opacity = 1f;
            }
        }
    }

}
