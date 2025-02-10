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

package com.github.tommyettinger.colorful.oklab;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3WindowAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.github.tommyettinger.anim8.FastPNG;
import com.github.tommyettinger.digital.MathTools;

import static com.badlogic.gdx.Gdx.input;

public class OklabBulkImageProcessor extends ApplicationAdapter {
//    public static final int SCREEN_WIDTH = 1531;
//    public static final int SCREEN_HEIGHT = 862;
    public static final int SCREEN_WIDTH = 808;
    public static final int SCREEN_HEIGHT = 600;
    protected Pixmap loadedPixmap;

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Tint Demo");
        config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
        config.setIdleFPS(2);
        config.setForegroundFPS(30);
        config.useVsync(true);

        final OklabBulkImageProcessor app = new OklabBulkImageProcessor();
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
        if (loadedPixmap != null) loadedPixmap.dispose();
        loadedPixmap = new Pixmap(file);
        loadedPixmap.setFilter(Pixmap.Filter.NearestNeighbour);
        int width, height;
        Gdx.graphics.setWindowedMode(width = Math.min(loadedPixmap.getWidth(), Gdx.graphics.getDisplayMode().width),
                height = Math.min(loadedPixmap.getHeight(), Gdx.graphics.getDisplayMode().height));
        FastPNG png = new FastPNG(width * height * 2);
        png.setFlipY(false);
        for (float shape = 0.25f, s = 0; shape < 3f; shape += 0.25f, s++) {
            for (float turning = 0f, t = 0; turning <= 1.01f; turning += 0.1f, t++) {
                int w = loadedPixmap.getWidth(), h = loadedPixmap.getHeight();
                Pixmap next = new Pixmap(w, h, Pixmap.Format.RGBA8888);
                for (int x = 0; x < w; x++) {
                    for (int y = 0; y < h; y++) {
                        int rgba = loadedPixmap.getPixel(x, y);
                        float oklab = ColorTools.fromRGBA8888(rgba);
                        float l = ColorTools.channelL(oklab), a = ColorTools.channelA(oklab), b = ColorTools.channelB(oklab);
                        next.drawPixel(x, y, ColorTools.toRGBA8888(ColorTools.oklab(
                                MathTools.barronSpline(l, shape, turning),
                                a,
                                b,
                                ColorTools.alpha(oklab))));
                    }
                }
                String name2 = file.nameWithoutExtension() + "_" + s + "_" + t + ".png";
                png.write(Gdx.files.local(name2), next);
                System.out.println("Wrote " + name2);
                next.dispose();
            }
        }
        Gdx.app.exit();
    }

//    @Override
//    public void create() {
//    }


    @Override
    public void render() {
        ScreenUtils.clear(0.4f, 0.4f, 0.4f, 1f);
        if (input.isKeyJustPressed(Input.Keys.Q) || input.isKeyJustPressed(Input.Keys.ESCAPE)) //quit
            Gdx.app.exit();
    }
}
