/*
 * Copyright (c) 2023 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.tommyettinger.colorful;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
import com.github.tommyettinger.colorful.hsluv.ColorfulBatch;

import static com.badlogic.gdx.Gdx.input;

public class ColorSolidDemo extends ApplicationAdapter {
    //public static final int backgroundColor = Color.rgba8888(Color.DARK_GRAY);
//    public static final int SCREEN_WIDTH = 1531;
//    public static final int SCREEN_HEIGHT = 862;
    public static final int SCREEN_WIDTH = 512;
    public static final int SCREEN_HEIGHT = 512;
    private ColorfulBatch hsluvBatch;
    private com.github.tommyettinger.colorful.cielab.ColorfulBatch cielabBatch;
    private com.github.tommyettinger.colorful.ipt_hq.ColorfulBatch ipthqBatch;
    private com.github.tommyettinger.colorful.oklab.ColorfulBatch oklabBatch;
    private Viewport screenView;
    private BitmapFont font;
    private Texture blank;
    private long lastProcessedTime = 0L, startTime;
    private float layer = 0.5f;
    private boolean recording;
    private Array<Pixmap> pixmaps;
    private AnimatedGif gif;
    private AnimatedPNG png;
    private PixmapIO.PNG pp;

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Color Solid Demo");
        config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
        config.setIdleFPS(10);
        config.useVsync(true);
        config.setTransparentFramebuffer(true);
//        config.setResizable(false);

        final ColorSolidDemo app = new ColorSolidDemo();
        new Lwjgl3Application(app, config);
    }

    @Override
    public void create() {
        startTime = TimeUtils.millis();
        Pixmap b = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        b.drawPixel(0, 0, 0x808080FF);
        blank = new Texture(b);
        font = new BitmapFont(Gdx.files.internal("font.fnt"));
        font.setColor(1f, 0.5f, 0.5f, 1f);

        pixmaps = new Array<>(true, 256, Pixmap.class);
        gif = new AnimatedGif();
        gif.palette = new PaletteReducer();
        gif.setDitherAlgorithm(Dithered.DitherAlgorithm.GRADIENT_NOISE);
        gif.fastAnalysis = false;
        gif.setDitherStrength(1f);

        png = new AnimatedPNG();

        pp = new PixmapIO.PNG();

        hsluvBatch = new ColorfulBatch();
        {
            String vertexShader = Shaders.vertexShaderHsluv;
            String fragmentShader = Shaders.fragmentShaderHsluv.replace("gl_FragColor = vec4(sRGB(clamp(luv2rgb(luv), 0.0, 1.0)), v_color.a * tgt.a);",
                    "vec3 back = luv2rgb(luv); gl_FragColor = vec4(clamp(back, 0.0, 1.0), v_color.a * tgt.a); if(any(notEqual(back, gl_FragColor.rgb))) discard; gl_FragColor.rgb = sRGB(gl_FragColor.rgb);");

//                    "#ifdef GL_ES\n" +
//                            "#define LOWP lowp\n" +
//                            "precision mediump float;\n" +
//                            "#else\n" +
//                            "#define LOWP \n" +
//                            "#endif\n" +
//                            "varying vec2 v_texCoords;\n" +
//                            "varying LOWP vec4 v_color;\n" +
//                            "varying LOWP vec4 v_tweak;\n" +
//                            "varying float v_lightFix;\n" +
//                            "uniform sampler2D u_texture;\n" +
//                            "const vec3 bright = vec3(0.375, 0.5, 0.125);\n" +
//                            "void main()\n" +
//                            "{\n" +
//                            "   vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
//                            "   vec3 ycc = vec3(\n" +
//                            "     (v_color.r - 0.5) + dot(tgt.rgb, bright),\n" + // luma
//                            "     (v_color.g - 0.5) * 2.0 + (tgt.r - tgt.b),\n" + // warmth
//                            "     (v_color.b - 0.5) * 2.0 + (tgt.g - tgt.b));\n" + // mildness
//                            "   gl_FragColor = vec4( (mat3(1.0, 1.0, 1.0, 0.625, -0.375, -0.375, -0.5, 0.5, -0.5) * ycc), v_color.a * tgt.a);\n" +
//                            "   if(any(notEqual(clamp(gl_FragColor.rgb, 0.0, 1.0), gl_FragColor.rgb))) discard;\n" +
//                            "}";
            ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShader);

            if (!shader.isCompiled()) throw new IllegalArgumentException("Error compiling shader: " + shader.getLog());
            hsluvBatch.setShader(shader);
        }
        cielabBatch = new com.github.tommyettinger.colorful.cielab.ColorfulBatch();
        {
            String vertexShader = cielabBatch.getShader().getVertexShaderSource();
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
                            "const vec3 forward = vec3(1.0 / 3.0);\n" +
                            "const vec3 sRGBFrom = vec3(2.4);\n" +
                            "const vec3 sRGBThresholdFrom = vec3(0.04045);\n" +
                            "const vec3 sRGBTo = vec3(1.0 / 2.4);\n" +
                            "const vec3 sRGBThresholdTo = vec3(0.0031308);\n" +
                            "const vec3 epsilon = vec3(0.00885645);\n" +
                            "vec3 linear(vec3 t){ return mix(pow((t + 0.055) * (1.0 / 1.055), sRGBFrom), t * (1.0/12.92), step(t, sRGBThresholdFrom)); }\n" +
                            "vec3 sRGB(vec3 t){ return mix(1.055 * pow(t, sRGBTo) - 0.055, 12.92*t, step(t, sRGBThresholdTo)); }\n" +
                            "float xyzF(float t){ return mix(pow(t,1./3.), 7.787037 * t + 0.139731, step(t, 0.00885645)); }\n" +
                            "vec3 xyzF(vec3 t){ return mix(pow(t, forward), 7.787037 * t + 0.139731, step(t, epsilon)); }\n" +
                            "float xyzR(float t){ return mix(t*t*t , 0.1284185 * (t - 0.139731), step(t, 0.20689655)); }\n" +
                            "vec3 rgb2lab(vec3 c)\n" +
                            "{\n" +
                            "    c *= mat3(0.4124, 0.3576, 0.1805,\n" +
                            "              0.2126, 0.7152, 0.0722,\n" +
                            "              0.0193, 0.1192, 0.9505);\n" +
                            "    c = xyzF(c);\n" +
                            "    vec3 lab = vec3(max(0.,1.16*c.y - 0.16), (c.x - c.y) * 5.0, (c.y - c.z) * 2.0); \n" +
                            "    return lab;\n" +
                            "}\n" +
                            "vec3 lab2rgb(vec3 c)\n" +
                            "{\n" +
                            "    float lg = 1./1.16*(c.x + 0.16);\n" +
                            "    vec3 xyz = vec3(xyzR(lg + c.y * 0.2),\n" +
                            "                    xyzR(lg),\n" +
                            "                    xyzR(lg - c.z * 0.5));\n" +
                            "    vec3 rgb = xyz*mat3( 3.2406, -1.5372,-0.4986,\n" +
                            "                        -0.9689,  1.8758, 0.0415,\n" +
                            "                         0.0557, -0.2040, 1.0570);\n" +
                            "    return rgb;\n" +
                            "}\n" +
                            "void main()\n" +
                            "{\n" +
                            "  vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                            "  vec3 lab = rgb2lab(linear(tgt.rgb));\n" +
                            "  lab.x = lab.x + v_color.r - 0.5372549;\n" +
                            "  lab.yz = (lab.yz + v_color.gb - 0.5) * 2.0;\n" +
                            "  lab = lab2rgb(lab);\n" +
                            "  vec3 back = clamp(lab, 0.0, 1.0);\n" +
                            "  if(any(notEqual(back, lab))) discard;\n" +
                            "  gl_FragColor = vec4(sRGB(back), v_color.a * tgt.a);\n" +
                            "}";
            ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShader);

            if (!shader.isCompiled()) throw new IllegalArgumentException("Error compiling shader: " + shader.getLog());
            cielabBatch.setShader(shader);
        }

//        iptBatch = new com.github.tommyettinger.colorful.ipt.ColorfulBatch();
//        {
//            String vertexShader = iptBatch.getShader().getVertexShaderSource();
//            String fragmentShader =
//                    "#ifdef GL_ES\n" +
//                            "#define LOWP lowp\n" +
//                            "precision mediump float;\n" +
//                            "#else\n" +
//                            "#define LOWP \n" +
//                            "#endif\n" +
//                            "varying vec2 v_texCoords;\n" +
//                            "varying LOWP vec4 v_color;\n" +
//                            "varying LOWP vec4 v_tweak;\n" +
//                            "varying float v_lightFix;\n" +
//                            "uniform sampler2D u_texture;\n" +
//                            "void main()\n" +
//                            "{\n" +
//                            "  vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
//                            "  vec3 ipt = mat3(0.189786, 0.669665 , 0.286498, 0.576951, -0.73741 , 0.655205, 0.233221, 0.0681367, -0.941748)\n" +
//                            "             * tgt.rgb;\n" +
//                            "  ipt.x = pow(ipt.x, v_tweak.a) * v_lightFix * v_tweak.r + v_color.r - 0.5;\n" +
//                            "  ipt.yz = (ipt.yz * v_tweak.gb + v_color.gb - 0.5) * 2.0;\n" +
//                            "  vec3 back = mat3(0.999779, 1.00015, 0.999769, 1.07094, -0.377744, 0.0629496, 0.324891, 0.220439, -0.809638) * ipt;\n" +
//                            "  gl_FragColor = vec4(back, v_color.a * tgt.a);\n" +
//                            "  if(any(notEqual(clamp(gl_FragColor.rgb, 0.0, 1.0), gl_FragColor.rgb))) discard;\n" +
//                            "}";
//            ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShader);
//
//            if (!shader.isCompiled()) throw new IllegalArgumentException("Error compiling shader: " + shader.getLog());
//            iptBatch.setShader(shader);
//        }
//
        ipthqBatch = new com.github.tommyettinger.colorful.ipt_hq.ColorfulBatch();
        {
            String vertexShader = ipthqBatch.getShader().getVertexShaderSource();
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
                            "const vec3 forward = vec3(0.43);\n" +
                            "const vec3 reverse = vec3(1.0 / 0.43);\n" +
                            "void main()\n" +
                            "{\n" +
                            "  vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                            "  vec3 ipt = mat3(0.40000, 4.45500, 0.80560, 0.40000, -4.8510, 0.35720, 0.20000, 0.39600, -1.1628) *" +
                            "             pow(mat3(0.313921f, 0.151693f, 0.017753f, 0.639468f, 0.748209f, 0.109468f, 0.0465970f, 0.1000044f, 0.8729690f) \n" +
                            "             * (tgt.rgb * tgt.rgb), forward);\n" +
                            "  ipt.x = clamp(pow(ipt.x, v_tweak.a) * v_lightFix * v_tweak.r + v_color.r - 0.55, 0.0, 1.0);\n" +
                            "  ipt.yz = clamp((ipt.yz * v_tweak.gb + v_color.gb - 0.5) * 2.0, -1.0, 1.0);\n" +
                            "  ipt = mat3(1.0, 1.0, 1.0, 0.097569, -0.11388, 0.032615, 0.205226, 0.133217, -0.67689) * ipt;\n" +
                            "  gl_FragColor = vec4(sqrt(" +
                            "                 mat3(5.432622, -1.10517, 0.028104, -4.67910, 2.311198, -0.19466, 0.246257, -0.20588, 1.166325) *\n" +
                            "                 (sign(ipt) * pow(abs(ipt), reverse))" +
                            "                 ), v_color.a * tgt.a);\n" +
                            "  if(any(notEqual(clamp(gl_FragColor.rgb, 0.0, 1.0), gl_FragColor.rgb))) discard;\n" +
                            "}";
            ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShader);

            if (!shader.isCompiled()) throw new IllegalArgumentException("Error compiling shader: " + shader.getLog());
            ipthqBatch.setShader(shader);
        }

        oklabBatch = new com.github.tommyettinger.colorful.oklab.ColorfulBatch();
        {
            String vertexShader = oklabBatch.getShader().getVertexShaderSource();
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
                            "const vec3 forward = vec3(1.0 / 3.0);\n" +
                            "float toOklab(float L) {\n" +
                            "        const float shape = 0.64516133, turning = 0.95;\n" +
                            "        float d = turning - L;\n" +
                            "        float r = mix(\n" +
                            "          ((1. - turning) * (L - 1.)) / (1. - (L + shape * d)) + 1.,\n" +
                            "          (turning * L) / (1.0e-20 + (L + shape * d)),\n" +
                            "          step(0.0, d));\n" +
                            "        return r * r;\n" +
                            "}\n" +
                            "float fromOklab(float L) {\n" +
                            "        const float shape = 1.55, turning = 0.95;\n" +
                            "        L = sqrt(L);\n" +
                            "        float d = turning - L;\n" +
                            "        return mix(\n" +
                            "          ((1. - turning) * (L - 1.)) / (1. - (L + shape * d)) + 1.,\n" +
                            "          (turning * L) / (1.0e-20 + (L + shape * d)),\n" +
                            "          step(0.0, d));\n" +
                            "}\n" +
                            "void main()\n" +
                            "{\n" +
                            "  vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                            "  vec3 lab = mat3(+0.2104542553, +1.9779984951, +0.0259040371, +0.7936177850, -2.4285922050, +0.7827717662, -0.0040720468, +0.4505937099, -0.8086757660) *" +
                            "             pow(mat3(0.4121656120, 0.2118591070, 0.0883097947, 0.5362752080, 0.6807189584, 0.2818474174, 0.0514575653, 0.1074065790, 0.6302613616) \n" +
                            "             * (tgt.rgb * tgt.rgb), forward);\n" +
                            "  lab.x = fromOklab(clamp(toOklab(lab.x) + v_color.r - 0.5, 0.0, 1.0));\n" +
                            "  lab.yz = clamp(lab.yz + v_color.gb * 2.0 - 1.0, -1.0, 1.0);\n" +
                            "  lab = mat3(1.0, 1.0, 1.0, +0.3963377774, -0.1055613458, -0.0894841775, +0.2158037573, -0.0638541728, -1.2914855480) * lab;\n" +
                            "  lab = " +
                            "                 mat3(+4.0767245293, -1.2681437731, -0.0041119885, -3.3072168827, +2.6093323231, -0.7034763098, +0.2307590544, -0.3411344290, +1.7068625689) *\n" +
                            "                 (lab * lab * lab);" +
                            "  vec3 back = clamp(lab, 0.0, 1.0);\n" +
                            "  if(any(notEqual(back, lab))) discard;\n" +
                            "  gl_FragColor = vec4(sqrt(back), v_color.a * tgt.a);\n" +
                            "}";
            ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShader);

            if (!shader.isCompiled()) throw new IllegalArgumentException("Error compiling shader: " + shader.getLog());
            oklabBatch.setShader(shader);
        }

        screenView = new ScreenViewport();
        screenView.getCamera().position.set(SCREEN_WIDTH * 0.5f, SCREEN_HEIGHT * 0.5f, 0);
        screenView.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        recording = true;
        for (int i = 0; i < 256; i++) {
            render();
            pixmaps.add(ScreenUtils.getFrameBufferPixmap(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        }
//        gif.palette = new PaletteReducer(pixmaps);
        gif.write(Gdx.files.local("ColorSolids.gif"), pixmaps, 30);
        png.write(Gdx.files.local("ColorSolids.png"), pixmaps, 30);
//        int idx = 0;
//        Gdx.files.local("tmp").mkdirs();
//        for (Pixmap pm : pixmaps) {
//            try {
//                pp.write(Gdx.files.local("tmp/solids_" + idx++ + ".png"), pm);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        recording = false;

    }

    private float layer(){
        return recording ? pixmaps.size / 255f : TimeUtils.timeSinceMillis(startTime) * 0x1p-13f;
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(!recording)
            handleInput();
        layer = TrigTools.acosTurns(TrigTools.sinTurns(layer())) * 2f;
        hsluvBatch.setProjectionMatrix(screenView.getCamera().combined);
//        ycwcmBatch.setPackedColor(Palette.GRAY);
        hsluvBatch.begin();
//        ycwcmBatch.draw(blank, 0, 0, 512, 512);
//        for (int x = 0; x < 256; x++) {
//            for (int y = 0; y < 256; y++) {
//                hsluvBatch.setColor(TrigTools.atan2Turns(y - 127.5f, x - 127.5f), Vector2.len(x / 255f - 0.5f, y / 255f - 0.5f), layer, 1f);
//                hsluvBatch.draw(blank, x, y + 256f, 1f, 1f);
//            }
//        }

        final float
                maxDist = 127f * TrigTools.sinTurns(layer * 0.5f) + 1f,
                iMax = 1f / maxDist;
        for (int dist = 0; dist <= maxDist; dist++) {
            final int circ = dist * 16;
            final float ic = 1f / circ;
            for (int t = 0; t < circ; t++) {
                final float angle = t * ic, x = TrigTools.cosTurns(angle), y = TrigTools.sinTurns(angle);
                final float sat = dist * iMax;// * (0.5f - Math.abs(layer - 0.5f)) * 2f;
                hsluvBatch.setColor(angle, sat, layer, 1f);
                hsluvBatch.draw(blank, 127.25f + x * dist, 127.25f + y * dist + 256f, 1f, 1f);
            }
        }


        hsluvBatch.end();
        cielabBatch.setProjectionMatrix(screenView.getCamera().combined);
        cielabBatch.begin();
        for (int x = 0; x < 256; x++) {
            for (int y = 0; y < 256; y++) {
                cielabBatch.setColor(layer, x * 0x1p-8f, y * 0x1p-8f, 1f);
                cielabBatch.draw(blank, x + 256f, y + 256f, 1f, 1f);
            }
        }
        cielabBatch.end();
        ipthqBatch.setProjectionMatrix(screenView.getCamera().combined);
        ipthqBatch.begin();
        for (int x = 0; x < 256; x++) {
            for (int y = 0; y < 256; y++) {
                ipthqBatch.setColor(layer, x * 0x1p-8f, y * 0x1p-8f, 1f);
                ipthqBatch.draw(blank, x + 256f, y, 1f, 1f);
            }
        }
        ipthqBatch.end();
        oklabBatch.setProjectionMatrix(screenView.getCamera().combined);
        oklabBatch.begin();
        for (int x = 0; x < 256; x++) {
            for (int y = 0; y < 256; y++) {
                oklabBatch.setColor(layer, x * 0x1p-8f, y * 0x1p-8f, 1f);
                oklabBatch.draw(blank, x, y, 1f, 1f);
            }
        }
        oklabBatch.setColor(0.9f - layer * 0.8f, 0.5f, 0.5f, 1f);

        oklabBatch.draw(blank, 128, 128 + 256, 2f, 2f);
        oklabBatch.draw(blank, 128 + 256, 128 + 256, 2f, 2f);
        oklabBatch.draw(blank, 128 + 256, 128, 2f, 2f);
        oklabBatch.draw(blank, 128, 128, 2f, 2f);

        oklabBatch.draw(blank, 255, layer * 511, 4f, 4f);
        oklabBatch.end();
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
