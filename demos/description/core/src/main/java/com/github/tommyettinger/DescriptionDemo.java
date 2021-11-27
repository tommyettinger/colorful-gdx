package com.github.tommyettinger;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class DescriptionDemo extends Game {
    public static final int SCREEN_WIDTH = 808;
    public static final int SCREEN_HEIGHT = 600;

    private RGBDescriptionScreen rgb;
    private CielabDescriptionScreen cielab;
    private OklabDescriptionScreen oklab;
    private IPTHQDescriptionScreen ipthq;
    private CompareDescriptionScreen compare;
    private Screen[] screens;
    private int screenIndex;
    @Override
    public void create() {
        rgb = new RGBDescriptionScreen(this);
        cielab = new CielabDescriptionScreen(this);
        oklab = new OklabDescriptionScreen(this);
        ipthq = new IPTHQDescriptionScreen(this);
        compare = new CompareDescriptionScreen(this);
        screens = new Screen[]{rgb, cielab, oklab, ipthq, compare};
        screenIndex = 4;
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