package com.github.tommyettinger;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class DescriptionDemo extends Game {
    public static final int SCREEN_WIDTH = 808;
    public static final int SCREEN_HEIGHT = 600;

    private RGBDescriptionScreen rgb;
    @Override
    public void create() {
        rgb = new RGBDescriptionScreen(this);
        setScreen(rgb);
    }

}