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

package com.github.tommyettinger;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class DescriptionDemo extends Game {
    public static final int SCREEN_WIDTH = 808;
    public static final int SCREEN_HEIGHT = 600;

    private RGBDescriptionScreen rgb;
    private OklabDescriptionScreen oklab;
    private HsluvDescriptionScreen hsluv;
    private CielabDescriptionScreen cielab;
    private IPTHQDescriptionScreen ipthq;
    private CompareDescriptionScreen compare;
    private OklabGradientScreen gradient;
    private Screen[] screens;
    private int screenIndex;
    @Override
    public void create() {
        rgb = new RGBDescriptionScreen(this);
        oklab = new OklabDescriptionScreen(this);
        hsluv = new HsluvDescriptionScreen(this);
        cielab = new CielabDescriptionScreen(this);
        ipthq = new IPTHQDescriptionScreen(this);
        compare = new CompareDescriptionScreen(this);
        gradient = new OklabGradientScreen(this);
        screens = new Screen[]{rgb, oklab, hsluv, cielab, ipthq, compare, gradient};
        screenIndex = 5;
        setScreen(screens[screenIndex]);
    }
    public void nextScreen(){
        screenIndex = (screenIndex + 1) % screens.length;
        setScreen(screens[screenIndex]);
    }
    public void previousScreen(){
        screenIndex = (screenIndex + screens.length - 1) % screens.length;
        setScreen(screens[screenIndex]);
    }
}