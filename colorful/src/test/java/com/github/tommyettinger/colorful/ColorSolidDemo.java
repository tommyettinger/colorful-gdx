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
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.tommyettinger.colorful.ycwcm.ColorfulBatch;
import com.github.tommyettinger.colorful.ycwcm.Palette;

import static com.badlogic.gdx.Gdx.input;

public class ColorSolidDemo extends ApplicationAdapter {
    //public static final int backgroundColor = Color.rgba8888(Color.DARK_GRAY);
//    public static final int SCREEN_WIDTH = 1531;
//    public static final int SCREEN_HEIGHT = 862;
    public static final int SCREEN_WIDTH = 512;
    public static final int SCREEN_HEIGHT = 512;
    private ColorfulBatch ycwcmBatch;
    private com.github.tommyettinger.colorful.ipt.ColorfulBatch iptBatch;
    private Viewport screenView;
    private BitmapFont font;
    private Texture blank;
    private long lastProcessedTime = 0L, startTime;
    private float layer = 0.5f;

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Color Solid Demo");
        config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
        config.setIdleFPS(10);
        config.useVsync(true);
//        config.setResizable(false);

        final ColorSolidDemo app = new ColorSolidDemo();
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
        ycwcmBatch = new ColorfulBatch();
        {
            String vertexShader = ycwcmBatch.getShader().getVertexShaderSource();
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
                            "varying float v_lightFix;\n" +
                            "uniform sampler2D u_texture;\n" +
                            "const vec3 bright = vec3(0.375, 0.5, 0.125);\n" +
                            "void main()\n" +
                            "{\n" +
                            "   vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                            "   vec3 ycc = vec3(\n" +
                            "     (v_color.r - 0.5 + v_tweak.r * pow(dot(tgt.rgb, bright), v_tweak.a) * v_lightFix),\n" + // luma
                            "     (v_color.g - 0.5 + (tgt.r - tgt.b) * v_tweak.g) * 2.0,\n" + // warmth
                            "     (v_color.b - 0.5 + (tgt.g - tgt.b) * v_tweak.b) * 2.0);\n" + // mildness
                            "   gl_FragColor = vec4(\n" +
                            "     dot(ycc, vec3(1.0, 0.625, -0.5)),\n" + // back to red
                            "     dot(ycc, vec3(1.0, -0.375, 0.5)),\n" + // back to green
                            "     dot(ycc, vec3(1.0, -0.375, -0.5)),\n" + // back to blue
                            "     v_color.a * tgt.a);\n" + // back to alpha and clamp
                            "   if(any(notEqual(clamp(gl_FragColor.rgb, 0.0, 1.0), gl_FragColor.rgb))) discard;\n" +
                            "}";
            ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShader);

            if (!shader.isCompiled()) throw new IllegalArgumentException("Error compiling shader: " + shader.getLog());
            ycwcmBatch.setShader(shader);
        }
        iptBatch = new com.github.tommyettinger.colorful.ipt.ColorfulBatch();
        {
            String vertexShader = iptBatch.getShader().getVertexShaderSource();
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
                            "varying float v_lightFix;\n" +
                            "uniform sampler2D u_texture;\n" +
                            "void main()\n" +
                            "{\n" +
                            "  vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                            "  vec3 ipt = mat3(0.189786, 0.669665 , 0.286498, 0.576951, -0.73741 , 0.655205, 0.233221, 0.0681367, -0.941748)\n" +
                            "             * tgt.rgb;\n" +
                            "  ipt.x = pow(ipt.x, v_tweak.a) * v_lightFix * v_tweak.r + v_color.r - 0.5;\n" +
                            "  ipt.yz = (ipt.yz * v_tweak.gb + v_color.gb - 0.5) * 2.0;\n" +
                            "  vec3 back = mat3(0.999779, 1.00015, 0.999769, 1.07094, -0.377744, 0.0629496, 0.324891, 0.220439, -0.809638) * ipt;\n" +
                            "  gl_FragColor = vec4(back, v_color.a * tgt.a);\n" +
                            "  if(any(notEqual(clamp(gl_FragColor.rgb, 0.0, 1.0), gl_FragColor.rgb))) discard;\n" +
                            "}";
            ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShader);

            if (!shader.isCompiled()) throw new IllegalArgumentException("Error compiling shader: " + shader.getLog());
            iptBatch.setShader(shader);
        }

        screenView = new ScreenViewport();
        screenView.getCamera().position.set(SCREEN_WIDTH * 0.5f, SCREEN_HEIGHT * 0.5f, 0);
        screenView.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handleInput();
        layer = TrigTools.acos_(TrigTools.sin_(TimeUtils.timeSinceMillis(startTime) * 0x1p-13f)) * 2f;
        ycwcmBatch.setProjectionMatrix(screenView.getCamera().combined);
        ycwcmBatch.setPackedColor(Palette.GRAY);
        ycwcmBatch.begin();
        ycwcmBatch.draw(blank, 0, 0, 512, 512);
        for (int x = 0; x < 256; x++) {
            for (int y = 0; y < 256; y++) {
                ycwcmBatch.setColor(layer, x * 0x1p-8f, y * 0x1p-8f, 1f);
                ycwcmBatch.draw(blank, x, y + 256f, 1f, 1f);
            }
        }
        ycwcmBatch.end();
        iptBatch.setProjectionMatrix(screenView.getCamera().combined);
        iptBatch.begin();
        for (int x = 0; x < 256; x++) {
            for (int y = 0; y < 256; y++) {
                iptBatch.setColor(layer, x * 0x1p-8f, y * 0x1p-8f, 1f);
                iptBatch.draw(blank, x + 256f, y + 256f, 1f, 1f);
            }
        }
        iptBatch.end();



//        final float maxDist = 254f * TrigTools.sin_(layer * 0.5f) + 1f, iMax = 1f / maxDist;
//        final float circumference = MathUtils.PI * 511f;
//        batch.setPackedColor(ColorTools.ycwcm(layer, 0.5f, 0.5f, 1f));
//        batch.draw(blank, 254.75f, 254.75f, 1.5f, 1.5f);
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
                System.out.println("Using layer=" + layer + ".");
        }
    }
}
