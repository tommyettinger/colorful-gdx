package com.github.tommyettinger.colorful;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3WindowAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.OrderedMap;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.badlogic.gdx.Gdx.input;

public class ColorReplacementDemo extends ApplicationAdapter {
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
    
    private OrderedMap<String, Color> colors;
    private RandomXS128 random;
    private Color search, replace;

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Replacement Demo");
        config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
        config.setIdleFPS(10);
        config.useVsync(true);
//        config.setResizable(false);

        final ColorReplacementDemo app = new ColorReplacementDemo();
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
    
    private void shuffle(){
        Object[] items = colors.orderedKeys().items;
        for (int i = colors.orderedKeys().size - 1; i >= 0; i--) {
            int ii = random.nextInt(i+1);
            Object temp = items[i];
            items[i] = items[ii];
            items[ii] = temp;
        }
    }

    @Override
    public void create() {
        random = new RandomXS128(0x123456789ABCDEFL, 0xB0BAFE77);
        colors = new OrderedMap<>(Colors.getColors().size);
        for(String k : Colors.getColors().keys())
            colors.put(k, Colors.get(k));
        colors.orderedKeys().sort();
        shuffle();
        defaultShader = SpriteBatch.createDefaultShader();
        shader = new ShaderProgram(Shaders.vertexShader, Shaders.fragmentShaderReplacement);
        if(!shader.isCompiled())
            System.out.println(shader.getLog());
        batch = new SpriteBatch(8000, defaultShader);
        screenView = new ScreenViewport();
        screenView.getCamera().position.set(SCREEN_WIDTH * 0.5f, SCREEN_HEIGHT * 0.5f, 0);
        screenView.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.enableBlending();
        
        input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean keyUp(int keycode) {
                switch (keycode){
                    case Input.Keys.Q:
                    case Input.Keys.ESCAPE:
                        Gdx.app.exit();
                        return true;
                    default:
                        shuffle();
                        System.out.println("Searching for " + colors.orderedKeys().get(0) + ", replacing with " + colors.orderedKeys().get(1));
                        return true;
                }
            }
        });
        
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
        batch.setProjectionMatrix(screenView.getCamera().combined);
        if (screenTexture != null) {
            batch.setShader(shader);
            batch.begin();
            shader.setUniformf("u_search", colors.get(colors.orderedKeys().get(0)));
            shader.setUniformf("u_replace", colors.get(colors.orderedKeys().get(1)));
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
}
