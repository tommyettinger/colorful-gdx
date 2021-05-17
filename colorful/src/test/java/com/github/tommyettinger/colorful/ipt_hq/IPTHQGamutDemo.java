package com.github.tommyettinger.colorful.ipt_hq;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.tommyettinger.anim8.AnimatedGif;
import com.github.tommyettinger.anim8.AnimatedPNG;
import com.github.tommyettinger.anim8.Dithered;
import com.github.tommyettinger.anim8.PaletteReducer;
import com.github.tommyettinger.colorful.TrigTools;

import static com.badlogic.gdx.Gdx.input;

public class IPTHQGamutDemo extends ApplicationAdapter {
    //public static final int backgroundColor = Color.rgba8888(Color.DARK_GRAY);
//    public static final int SCREEN_WIDTH = 1531;
//    public static final int SCREEN_HEIGHT = 862;
    public static final int SCREEN_WIDTH = 512;
    public static final int SCREEN_HEIGHT = 512;
    private SpriteBatch batch;
    private Viewport screenView;
    private Texture blank;
    private long lastProcessedTime = 0L, startTime;
    private float layer = 0.5f;
    private float intens = 0.5f, protan = 0.5f, tritan = 0.5f, contrast = 0.5f;

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("IPT_HQ Gamut Demo");
        config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
        config.setIdleFPS(10);
        config.useVsync(true);

        final IPTHQGamutDemo app = new IPTHQGamutDemo();
        new Lwjgl3Application(app, config);
    }

    @Override
    public void create() {
        startTime = TimeUtils.millis();
        Pixmap b = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        b.drawPixel(0, 0, 0x7F7F81FF);
        blank = new Texture(b);
        batch = new SpriteBatch();
        String vertexShader = batch.getShader().getVertexShaderSource();
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
                        "const vec3 forward = vec3(0.43);\n" +
                        "const vec3 reverse = vec3(1.0 / 0.43);\n" +
                        "void main()\n" +
                        "{\n" +
                        "  vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                        "  vec3 ipt = mat3(0.40000, 4.45500, 0.80560, 0.40000, -4.8510, 0.35720, 0.20000, 0.39600, -1.1628) *" +
                        "             pow(mat3(0.313921f, 0.151693f, 0.017753f, 0.639468f, 0.748209f, 0.109468f, 0.0465970f, 0.1000044f, 0.8729690f) \n" +
                        "             * (tgt.rgb * tgt.rgb), forward);\n" +
                        "  ipt.yz += 2.0 * (v_color.gb - 0.5);\n" +
                        "  ipt.x = clamp(ipt.x + v_color.r - 0.55, 0.0, 1.0);\n" +
                        "  ipt = mat3(1.0, 1.0, 1.0, 0.097569, -0.11388, 0.032615, 0.205226, 0.133217, -0.67689) * ipt;\n" +
                        "  ipt = \n" +
                        "                 mat3(5.432622, -1.10517, 0.028104, -4.67910, 2.311198, -0.19466, 0.246257, -0.20588, 1.166325) *\n" +
                        "                 (sign(ipt) * pow(abs(ipt), reverse));\n" +
                        "  vec3 back = clamp(ipt, 0.0, 1.0);\n" +
                        "  if(any(notEqual(back, ipt))) discard;\n" +
                        "  gl_FragColor = vec4(sqrt(back), v_color.a * tgt.a);\n" +
                        "}";
        ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShader);
        if (!shader.isCompiled()) throw new IllegalArgumentException("Error compiling shader: " + shader.getLog());
        batch.setShader(shader);
        screenView = new ScreenViewport();
        screenView.getCamera().position.set(SCREEN_WIDTH * 0.5f, SCREEN_HEIGHT * 0.5f, 0);
        screenView.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.enableBlending();
        final int frameCount = 120;
        Array<Pixmap> pixmaps = new Array<>(frameCount);
        for (int i = 0; i < frameCount; i++) {
            layer = i / (frameCount - 1f);
            renderInternal();
            // this gets a screenshot of the current window and adds it to the Array of Pixmap.
            pixmaps.add(ScreenUtils.getFrameBufferPixmap(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        }

//// AnimatedGif is from anim8; this code uses the predefined Haltonic palette, which has 255 colors
//// plus transparent, and seems to be more accurate than any attempts to analyze an image with almost every color.
        AnimatedGif gif = new AnimatedGif();
//        gif.setDitherAlgorithm(Dithered.DitherAlgorithm.GRADIENT_NOISE); // this is better than it sounds
//        gif.setDitherAlgorithm(Dithered.DitherAlgorithm.SCATTER); // this is pretty fast to compute, and also good
        gif.setDitherAlgorithm(Dithered.DitherAlgorithm.PATTERN); // this is very slow, but high-quality
//        gif.setDitherAlgorithm(Dithered.DitherAlgorithm.NONE); // this should be dithered before usage
//        gif.palette.setDitherStrength(0.5f);
        gif.palette = new PaletteReducer();
//        gif.palette = new PaletteReducer(pixmaps);
//        // 24 is how many frames per second the animated GIF should play back at.
        gif.write(Gdx.files.local("IPTHQGamut.gif"), pixmaps, 24);

//// AnimatedPNG uses full-color, so it doesn't involve dithering or color reduction at all.
        AnimatedPNG png = new AnimatedPNG();
//// 24 is how many frames per second the animated PNG should play back at.
        png.write(Gdx.files.local("IPTHQGamut.png"), pixmaps, 24);
    }


    @Override
    public void render() {
        handleInput();
        layer = TrigTools.acos_(TrigTools.sin_(TimeUtils.timeSinceMillis(startTime) * 0x1p-13f)) * 2f;
        renderInternal();
    }
    
    public void renderInternal() {
        Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(screenView.getCamera().combined);
        batch.setColor(0.5f, 0.5f, 0.5f, 1f);
        batch.begin();
        batch.draw(blank, 0, 0, 512, 512);
        batch.setColor(layer, 0.5f, 0.5f, 1f);
        batch.draw(blank, 254.75f, 254.75f, 1.5f, 1.5f);
        for (int x = 0; x < 512; x++) {
            for (int y = 0; y < 512; y++) {
                batch.setColor(layer, x * 0x1p-9f, y * 0x1p-9f, 1f);
                batch.draw(blank, x, y, 1f, 1f);
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
            } else if (input.isKeyPressed(Input.Keys.P)) // print
                System.out.println("Using layer=" + layer
                        +", and using tweak with luma="+ intens
                        + ",warm="+ protan + ",mild="+ tritan +",contrast="+contrast + " .");
            else if (input.isKeyPressed(Input.Keys.L)) //light
                intens = MathUtils.clamp(intens + 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.D)) //dark
                intens = MathUtils.clamp(intens - 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.W)) //warm
                protan = MathUtils.clamp(protan + 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.C)) //cool
                protan = MathUtils.clamp(protan - 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.M)) //mild
                tritan = MathUtils.clamp(tritan + 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.B)) //bold
                tritan = MathUtils.clamp(tritan - 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.S)) //sharp contrast
                contrast = MathUtils.clamp(contrast + 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.F)) //fuzzy contrast
                contrast = MathUtils.clamp(contrast - 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.BACKSPACE)) //reset
            {
                intens = 0.5f;
                protan = 0.5f;
                tritan = 0.5f;
                contrast = 0.5f;
            }
        }
    }
}
