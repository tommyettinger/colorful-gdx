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

public class IPTColorWheelDemo extends ApplicationAdapter {
    //public static final int backgroundColor = Color.rgba8888(Color.DARK_GRAY);
//    public static final int SCREEN_WIDTH = 1531;
//    public static final int SCREEN_HEIGHT = 862;
    public static final int SCREEN_WIDTH = 512;
    public static final int SCREEN_HEIGHT = 512;
    private SpriteBatch batch;
//    private SpriteBatch basicBatch;
    private Viewport screenView;
    private BitmapFont font;
    private Texture blank;
    private long lastProcessedTime = 0L, startTime;
    private float layer = 0.5f;
    private float intens = 0.5f, protan = 0.5f, tritan = 0.5f, contrast = 0.5f;

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Color Wheel Demo");
        config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
        config.setIdleFPS(10);
        config.useVsync(true);
//        config.setResizable(false);

        final IPTColorWheelDemo app = new IPTColorWheelDemo();
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
//        batch = Shaders.makeBatch(1.25f); // experimenting with slightly higher contrast
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
                        "void main()\n" +
                        "{\n" +
                        "    vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                        "    vec3 ipt = v_color.rgb - 0.5 + vec3(0.5, 0.0, 0.0);\n" +
//                        "        (mat3(0.4000, 4.4550, 0.8056, 0.4000, 4.8510, 0.3572, 0.2000, 0.3960, 1.1628) * \n" +
//                        "        (pow(mat3(0.313921, 0.151693, 0.017700, 0.639468, 0.748209, 0.109400, 0.0465970, 0.1000044, 0.8729000) * tgt.rgb, vec3(0.43))));\n" +
                        "    vec3 back = mat3(1.0, 1.0, 1.0, 0.097569, -0.113880, 0.032615, 0.205226, 0.133217, -0.676890) * ipt;\n" +
                        "    back = mat3(5.432622, -1.10517, 0.028104, -4.67910, 2.311198, -0.19466, 0.246257, -0.20588, 1.166325) * \n" +
                        "        (pow(abs(back), vec3(2.3256)) * sign(back));\n" +
//                        "   vec3 ycc = vec3(\n" +
//                        "     (v_tweak.r * pow(dot(tgt.rgb, bright), v_tweak.a) * v_lightFix + v_color.r - 0.5),\n" + // luma
//                        "     (v_color.g - 0.5 + (tgt.r - tgt.b) * v_tweak.g) * 2.0,\n" + // warmth
//                        "     (v_color.b - 0.5 + (tgt.g - tgt.b) * v_tweak.b) * 2.0);\n" + // mildness
                        "    gl_FragColor = clamp(vec4(back, 1.0), 0.0, 1.0);\n" + // back to alpha and clamp
                        "    if(any(notEqual(back, gl_FragColor.rgb))) discard;\n" +
                        "}";
        ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShader);
        if (!shader.isCompiled()) throw new IllegalArgumentException("Error compiling shader: " + shader.getLog());
        batch.setShader(shader);
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
        layer = TrigTools.acos_(TrigTools.sin_(TimeUtils.timeSinceMillis(startTime) * 0x1p-13f)) * 2f;
        batch.setProjectionMatrix(screenView.getCamera().combined);
        batch.setPackedColor(Palette.GRAY);
        batch.begin();
//        batch.setTweak(intens, protan, tritan, contrast);
        batch.draw(blank, 0, 0, 512, 512);
        final float maxDist = 254f * TrigTools.sin_(layer * 0.5f) + 1f, iMax = 1f / maxDist;
        //final float circumference = 1605.3539f;//MathUtils.PI * 511f;
        batch.setColor(layer, 0.5f, 0.5f, 1f);
        batch.draw(blank, 254.75f, 254.75f, 1.5f, 1.5f);
        for (int x = 0; x < 512; x++) {
            for (int y = 0; y < 512; y++) {
                batch.setColor(layer, x * 0x1p-9f, y * 0x1p-9f, 1f);
                batch.draw(blank, x, y, 1f, 1f);
            }
        }
//        for (int dist = 1; dist <= maxDist; dist++) {
//            final int circ = dist * 6;
//            final float ic = 1f / circ;
//            final float id = dist * iMax;
//            for (int t = 0; t < circ; t++) {
//                final float angle = t * ic, x = TrigTools.cos_(angle), y = TrigTools.sin_(angle);
////                if((Math.abs(x) + Math.abs(y) + Math.abs(2f * (layer - 0.5f))) * dist > maxDist)
////                    continue;
//                batch.setPackedColor(FloatColors.floatColor(layer, x * id * 0.5f + 0.5f, y * id * 0.5f + 0.5f, 1f));
//                batch.draw(blank, 254.75f + x * dist, 254.75f + y * dist, 1.5f, 1.5f);
//            }
//        }
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
