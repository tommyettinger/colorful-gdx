package com.github.tommyettinger.colorful;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3WindowAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.tommyettinger.colorful.ycwcm.ColorTools;
import com.github.tommyettinger.colorful.ycwcm.ColorfulBatch;
import com.github.tommyettinger.colorful.ycwcm.Palette;

import static com.badlogic.gdx.Gdx.input;

public class NamedDemo extends ApplicationAdapter {
    //public static final int backgroundColor = Color.rgba8888(Color.DARK_GRAY);
//    public static final int SCREEN_WIDTH = 1531;
//    public static final int SCREEN_HEIGHT = 862;
    public static final int SCREEN_WIDTH = 808;
    public static final int SCREEN_HEIGHT = 600;
    private ColorfulBatch batch;
//    private SpriteBatch basicBatch;
    private Viewport screenView;
    private Texture screenTexture;
    private BitmapFont font;
    private Texture blank;
    private long lastProcessedTime = 0L;
    private int selectedIndex;
    private String selectedName;
    private float selected;

    private float luma = 0.5f, warm = 0.5f, mild = 0.5f, contrast = 0.5f;

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Named Color Demo");
        config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
        config.setIdleFPS(10);
        config.useVsync(true);
//        config.setResizable(false);

        final NamedDemo app = new NamedDemo();
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
        if (!file.exists()) {
            file = Gdx.files.classpath(name);
            if (!file.exists())
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
        Pixmap b = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        b.drawPixel(0, 0, 0x7F7F81FF);
        blank = new Texture(b);
        font = new BitmapFont(Gdx.files.internal("font.fnt"));
        font.setColor(1f, 0.5f, 0.5f, 1f);
//        batch = Shaders.makeBatch(1.25f); // experimenting with slightly higher contrast
        batch = new ColorfulBatch();
//        basicBatch = new SpriteBatch();
        screenView = new ScreenViewport();
        screenView.getCamera().position.set(SCREEN_WIDTH * 0.5f, SCREEN_HEIGHT * 0.5f, 0);
        screenView.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.enableBlending();

        for (int i = 0; i < Palette.NAMES_BY_HUE.size; i++) {
            String name = Palette.NAMES_BY_HUE.get(i);
            float color = Palette.NAMED.get(name, Palette.WHITE);
            if (ColorTools.alphaInt(color) == 0)
                Palette.NAMES_BY_HUE.removeIndex(i--);
        }
        selectedName = "Gray";
        selectedIndex = Palette.NAMES_BY_HUE.indexOf("Gray", false);
        selected = Palette.GRAY;
//        selectedName = Palette.NAMES_BY_HUE.first();
//        selectedIndex = 0;
//        selected = Palette.NAMED.get(selectedName, Palette.GRAY);

        // if you don't have these files on this absolute path, that's fine, and they will be ignored
//        load("samples/Painting_by_Henri_Biva.jpg");
//        load("samples/Among_the_Sierra_Nevada_by_Albert_Bierstadt.jpg");
        load("samples/Color_Guard.png");
//        load("samples/Mona_Lisa.jpg");
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handleInput();
        batch.setProjectionMatrix(screenView.getCamera().combined);
//        basicBatch.setProjectionMatrix(screenView.getCamera().combined);
        if (screenTexture != null) {
            batch.setPackedColor(selected);
            batch.begin();
            batch.setTweak(luma, warm, mild, contrast);
            batch.draw(screenTexture, 0, 0);
            int i = -1;
            final float width = screenTexture.getWidth() / 5f, height = screenTexture.getHeight() / 51f;
            for (int y = 0; y < 51; y++) {
                for (int x = 0; x < 5; x++) {
                    String name = Palette.NAMES_BY_HUE.get(++i);
                    float color = Palette.NAMED.get(name, Palette.WHITE);
                    batch.setPackedColor(color);
                    batch.draw(blank, screenTexture.getWidth() + width * x, height * (50 - y), width, height);
                }
            }
//            batch.end();
//            basicBatch.begin();
            i = -1;
            for (int y = 0; y < 51; y++) {
                for (int x = 0; x < 5; x++) {
                    if (++i == selectedIndex) {
                        font.setColor(0.5f, 0.5f, 0.5f, 1f);
                        font.draw(batch, Palette.NAMES_BY_HUE.get(i), screenTexture.getWidth() + width * x + 1f, height * (51 - y) - 1f);
                    }
                }
            }
//            basicBatch.end();
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
        if (input.isKeyPressed(Input.Keys.Q) || input.isKeyPressed(Input.Keys.ESCAPE)) //quit
            Gdx.app.exit();
        else if (input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            if (input.isKeyPressed(Input.Keys.M))
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
        } else if (TimeUtils.timeSinceMillis(lastProcessedTime) > 150) {
            lastProcessedTime = TimeUtils.millis();
            if (input.isKeyPressed(Input.Keys.RIGHT) || input.isKeyPressed(Input.Keys.DOWN)) {
                selectedIndex = (selectedIndex + 1) % Palette.NAMES_BY_HUE.size;
                selectedName = Palette.NAMES_BY_HUE.get(selectedIndex);
                selected = Palette.NAMED.get(selectedName, Palette.GRAY);
            } else if (input.isKeyPressed(Input.Keys.LEFT) || input.isKeyPressed(Input.Keys.UP)) {
                selectedIndex = (selectedIndex + Palette.NAMES_BY_HUE.size - 1) % Palette.NAMES_BY_HUE.size;
                selectedName = Palette.NAMES_BY_HUE.get(selectedIndex);
                selected = Palette.NAMED.get(selectedName, Palette.GRAY);
            } else if (input.isKeyPressed(Input.Keys.R)) // random
            {
                selectedIndex = MathUtils.random(Palette.NAMES_BY_HUE.size);
                selectedName = Palette.NAMES_BY_HUE.get(selectedIndex);
                selected = Palette.NAMED.get(selectedName, Palette.GRAY);
            } else if (input.isKeyPressed(Input.Keys.P)) // print
                System.out.println("Using color " + selectedName
                        + " with luma="+ ColorTools.luma(selected) + ",warm="+ ColorTools.chromaWarm(selected)
                        + ",mild="+ ColorTools.chromaMild(selected)+",alpha=1.0 .\nUsing tweak with luma="+luma
                        + ",warm="+warm + ",mild="+mild+",contrast="+contrast + " .");
            else if (input.isKeyPressed(Input.Keys.L)) //light
                luma = MathUtils.clamp(luma + 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.D)) //dark
                luma = MathUtils.clamp(luma - 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.W)) //warm
                warm = MathUtils.clamp(warm + 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.C)) //cool
                warm = MathUtils.clamp(warm - 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.M)) //mild
                mild = MathUtils.clamp(mild + 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.B)) //bold
                mild = MathUtils.clamp(mild - 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.S)) //sharp contrast
                contrast = MathUtils.clamp(contrast + 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.F)) //fuzzy contrast
                contrast = MathUtils.clamp(contrast - 0x3p-7f, 0f, 1f);
            else if (input.isKeyPressed(Input.Keys.BACKSPACE)) //reset
            {
                luma = 0.5f;
                warm = 0.5f;
                mild = 0.5f;
                contrast = 0.5f;
            }
        }
    }
}
