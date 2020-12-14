package com.github.tommyettinger.colorful.hsi;

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
import com.github.tommyettinger.colorful.Shaders;
import com.github.tommyettinger.colorful.TrigTools;

import static com.badlogic.gdx.Gdx.input;

public class HSIVersusHSLDemo extends ApplicationAdapter {
    public static final int SCREEN_WIDTH = 512;
    public static final int SCREEN_HEIGHT = 256;
    private SpriteBatch batch;
    private Viewport screenView;
    private Texture blank;
    private long startTime;
    private float layer = 0.5f;
    private ShaderProgram hsiShader, hslShader;

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Color Wheel Demo");
        config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
        config.setIdleFPS(10);
        config.useVsync(true);

        final HSIVersusHSLDemo app = new HSIVersusHSLDemo();
        new Lwjgl3Application(app, config);
    }

    @Override
    public void create() {
        startTime = TimeUtils.millis();
        Pixmap b = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        b.drawPixel(0, 0, 0x808080FF);
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
                        "const vec3 yellow  = vec3( 0.16155326f,0.020876605f,-0.26078433f );\n" +
                        "const vec3 magenta = vec3(-0.16136102f,0.122068435f,-0.070396f   );\n" +
                        "const vec3 cyan    = vec3( 0.16420607f,0.3481738f,   0.104959644f);\n" +
                        "void main()\n" +
                        "{\n" +
                        "    vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                        "    vec3 ipt = v_color.rgb - 0.5;\n" +
                        "    ipt.x *= 0.99999;\n" +
                        "    float crMid = dot(cyan.yz, ipt.yz);\n" +
                        "    float mgMid = dot(magenta.yz, ipt.yz);\n" +
                        "    float ybMid = dot(yellow.yz, ipt.yz);\n" +
                        "    float crScale = (ipt.x - 0.5 + step(crMid, 0.0)) * cyan.x / -crMid;\n" +
                        "    float mgScale = (ipt.x + 0.5 - step(mgMid, 0.0)) * magenta.x / -mgMid;\n" +
                        "    float ybScale = (ipt.x - 0.5 + step(ybMid, 0.0)) * yellow.x / -ybMid;\n" +
                        "    float scale = 4.0 * min(crScale, min(mgScale, ybScale));\n" +
                        "    ipt.yz *= scale * length(ipt.yz) / cos(3.14159 * ipt.x);\n" +
                        "    ipt.x += 0.5;\n" +
                        "    vec3 back = mat3(0.999779, 1.00015, 0.999769, 1.07094, -0.377744, 0.0629496, 0.324891, 0.220439, -0.809638) * ipt;\n" +
                        "    gl_FragColor = vec4(clamp(back, 0.0, 1.0), v_color.a * tgt.a);\n" +
                        "    if(any(notEqual(back, gl_FragColor.rgb))) discard;\n" +
                        "}";
        hsiShader = new ShaderProgram(vertexShader, fragmentShader);
        if (!hsiShader.isCompiled())
            throw new IllegalArgumentException("Error compiling shader: " + hsiShader.getLog());
        hslShader = new ShaderProgram(Shaders.vertexShader, Shaders.fragmentShaderHSL);
        if (!hslShader.isCompiled())
            System.out.println(hslShader.getLog());
        screenView = new ScreenViewport();
        screenView.getCamera().position.set(SCREEN_WIDTH * 0.5f, SCREEN_HEIGHT * 0.5f, 0);
        screenView.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.enableBlending();
        final int frameCount = 128;
        Array<Pixmap> pixmaps = new Array<>(frameCount);
        for (int i = 0; i < frameCount; i++) {
            layer = TrigTools.acos_(TrigTools.sin_(i / (frameCount - 1f))) * 2f;
            renderInternal();

            // this gets a screenshot of the current window and adds it to the Array of Pixmap.
            pixmaps.add(ScreenUtils.getFrameBufferPixmap(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        }
//// AnimatedGif is from anim8; this code uses the predefined DawnBringer Aurora palette, which has 255 colors
//// plus transparent, and seems to be more accurate than any attempts to analyze an image with almost every color.
        AnimatedGif gif = new AnimatedGif();
////        gif.setDitherAlgorithm(Dithered.DitherAlgorithm.GRADIENT_NOISE); // this is better than it sounds
////        gif.setDitherAlgorithm(Dithered.DitherAlgorithm.SCATTER); // this is pretty fast to compute, and also good
        gif.setDitherAlgorithm(Dithered.DitherAlgorithm.PATTERN); // this is very slow, but high-quality
        gif.palette = new PaletteReducer();
////        gif.palette.setDitherStrength(0.5f);
////        gif.palette.analyze(pixmaps, 500);
////        // 24 is how many frames per second the animated GIF should play back at.
        gif.write(Gdx.files.local("HSIVersusHSV.gif"), pixmaps, 24);
//// AnimatedPNG uses full-color, so it doesn't involve dithering or color reduction at all.
        AnimatedPNG png = new AnimatedPNG();
//// 24 is how many frames per second the animated PNG should play back at.
        png.write(Gdx.files.local("HSIVersusHSV.png"), pixmaps, 24);
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
        batch.setShader(hsiShader);
        batch.setColor(0.5f, 0.5f, 0.5f, 1f);
        batch.begin();
        batch.draw(blank, 0, 0, 512,256);
//        batch.setColor(layer, 0.5f, 0.5f, 1f);
//        batch.draw(blank, 254.75f, 254.75f, 1.5f, 1.5f);
        for (int x = 0; x < 256; x++) {
            for (int y = 0; y < 256; y++) {
                batch.setColor(layer, x * 0x1p-8f, y * 0x1p-8f, 1f);
                batch.draw(blank, x, y, 1f, 1f);
            }
        }
        batch.setShader(hslShader);
        final float
                maxDist = 127f * TrigTools.sin_(layer * 0.5f) + 1f,
                iMax = 1f / maxDist;
        for (int dist = 0; dist <= maxDist; dist++) {
            final int circ = dist * 16;
            final float ic = 1f / circ;
            for (int t = 0; t < circ; t++) {
                final float angle = t * ic, x = TrigTools.cos_(angle), y = TrigTools.sin_(angle);
                final float sat = dist * iMax;// * (0.5f - Math.abs(layer - 0.5f)) * 2f;
                batch.setColor(angle, sat, layer, 1f);
                batch.draw(blank, 127.25f + 256f + x * dist, 127.25f + y * dist, 1f, 1f);
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
    }
}
