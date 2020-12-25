package com.github.tommyettinger.colorful.ipt_hq;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.OrderedMap;

import static com.badlogic.gdx.Gdx.input;
import static com.github.tommyettinger.colorful.ipt_hq.FullPalette.*;

public class DescriptionMatcher extends ApplicationAdapter {
    public static final OrderedMap<String, Float> viewer = new OrderedMap<>(53);
    static  {

        viewer.put("snow path", ColorTools.toEditedFloat(ALICE_BLUE, 0.0f, -0.2f, -0.15f, 0f));
        viewer.put("dirt path", ColorTools.toEditedFloat(CLOVE_BROWN, -0.005f, -0.275f, 0.17f, 0f));
        viewer.put("sand path", ColorTools.toEditedFloat(CW_PALE_ORANGE, 0.05f, -0.17f, -0.075f, 0f));
        viewer.put("grass path", ColorTools.toEditedFloat(AURORA_DUSTY_GREEN, 0.0f, -0.15f, -0.1f, 0f));
        viewer.put("stone path", ColorTools.toEditedFloat(AURORA_CHIPPED_GRANITE, -0.09f, -0.05f, 0.1f, 0f));
        viewer.put("wooden bridge", ColorTools.toEditedFloat(BRUSHWOOD_DYED, 0.0f, -0.275f, 0.05f, 0f));

        viewer.put("ice ledge", ColorTools.toEditedFloat(PALE_CORNFLOWER_BLUE, 0.0f, -0.1f, 0.1f, 0f));
        viewer.put("dirt ledge", ColorTools.toEditedFloat(CLOVE_BROWN, -0.005f, -0.175f, -0.18f, 0f));
        viewer.put("sand ledge", ColorTools.toEditedFloat(CW_PALE_ORANGE, 0.05f, -0.15f, -0.125f, 0f));
        viewer.put("grass ledge", ColorTools.toEditedFloat(AURORA_DUSTY_GREEN, 0.0f, -0.025f, -0.45f, 0f));
        viewer.put("stone ledge", ColorTools.toEditedFloat(AURORA_CHIPPED_GRANITE, -0.07f, -0.1f, -0.25f, 0f));

        viewer.put("snow", ColorTools.toEditedFloat(ALICE_BLUE, 0f, 0f, 0f, 0f));
        viewer.put("ice", ColorTools.toEditedFloat(PALE_CORNFLOWER_BLUE, 0f, 0f, 0.3f, 0f));
        viewer.put("dirt", ColorTools.toEditedFloat(CLOVE_BROWN, -0.005f, -0.075f, 0.02f, 0f));
        viewer.put("pebbles", ColorTools.toEditedFloat(AURORA_WET_STONE, 0.0f, 0.0f, 0.0f, 0f));
        viewer.put("dry grass", ColorTools.toEditedFloat(CW_FADED_BROWN, 0.06f, 0.05f, 0.05f, 0f));
        viewer.put("fresh water", ColorTools.toEditedFloat(AURORA_BLUE_EYE, 0f, 0f, 0f, 0f));
        viewer.put("salt water", ColorTools.toEditedFloat(AURORA_PRUSSIAN_BLUE, 0f, 0f, 0f, 0f));
        viewer.put("sand", ColorTools.toEditedFloat(CW_PALE_ORANGE, 0.05f, -0.05f, 0.075f, 0f));
        viewer.put("leaves", ColorTools.toEditedFloat(CHINESE_TEA_YELLOW, 0.02f, -0.025f, 0.0f, 0f));
        viewer.put("grass", ColorTools.toEditedFloat(AURORA_DUSTY_GREEN, 0.0f, 0.075f, -0.25f, 0f));
        viewer.put("mud", ColorTools.toEditedFloat(DB_EARTH, 0.03f, -0.15f, -0.03f, 0f));
        viewer.put("moss", ColorTools.toEditedFloat(AURORA_FERN_GREEN, 0f, 0.0f, 0.0f, 0f));
        viewer.put("rubble", ColorTools.toEditedFloat(AURORA_CHIPPED_GRANITE, -0.07f, 0.0f, -0.05f, 0f));
        viewer.put("empty space", ColorTools.toEditedFloat(DB_INK, 0f, 0f, 0f, 0f));
        viewer.put("snow mound", ColorTools.toEditedFloat(ALICE_BLUE, 0f, 0.05f, -0.1f, 0f));
        viewer.put("icy divot", ColorTools.toEditedFloat(ALICE_BLUE, 0.05f, 0.075f, 0.06f, 0f));
        viewer.put("powder snowdrift", ColorTools.toEditedFloat(ALICE_BLUE, 0.0f, 0.0f, -0.07f, 0f));
        viewer.put("hillock", ColorTools.toEditedFloat(CW_DRAB_BROWN, 0.1f, -0.05f, 0.25f, 0f));
        viewer.put("animal burrow", ColorTools.toEditedFloat(AURORA_ARMY_GREEN, 0.05f, 0.0f, -0.05f, 0f));
        viewer.put("small bush 1", ColorTools.toEditedFloat(AURORA_AVOCADO, -0.055f, -0.025f, -0.225f, 0f));
        viewer.put("large bush 1", ColorTools.toEditedFloat(AURORA_FOREST_GLEN, -0.055f, -0.125f, -0.225f, 0f));
        viewer.put("evergreen tree 1", ColorTools.toEditedFloat(PINE_GREEN, -0.13f, -0.03f, -0.05f, 0f));
        viewer.put("evergreen tree 2", ColorTools.toEditedFloat(AURORA_EUCALYPTUS, -0.035f, -0.045f, -0.75f, 0f));
        viewer.put("small cactus 1", ColorTools.toEditedFloat(AURORA_FROG_GREEN, 0.035f, 0.065f, -0.06f, 0f));
        viewer.put("large cactus 1", ColorTools.toEditedFloat(AURORA_MARSH, 0.04f, 0.11f, -0.03f, 0f));
        viewer.put("succulent 1", ColorTools.toEditedFloat(CW_FLUSH_JADE, -0.045f, -0.1f, 0.0f, 0f));
        viewer.put("seashell 1", ColorTools.toEditedFloat(CW_LIGHT_APRICOT, 0.0f, -0.095f, 0.07f, 0f));
        viewer.put("seashell 2", ColorTools.toEditedFloat(CW_PALE_RED, 0.0f, -0.2f, 0.1f, 0f));
        viewer.put("seashell 3", ColorTools.toEditedFloat(CW_PALE_YELLOW, 0.0f, 0.02f, 0.05f, 0f));
        viewer.put("seashell 4", ColorTools.toEditedFloat(CW_PALE_VIOLET, 0.0f, -0.080f, 0.11f, 0f));
        viewer.put("driftwood", ColorTools.toEditedFloat(AURORA_DRIFTWOOD, 0.0f, -0.25f, 0.04f, 0f));
        viewer.put("boulder", ColorTools.toEditedFloat(AURORA_SLOW_CREEK, 0.0f, -0.01f, 0.0f, 0f));
        viewer.put("deciduous tree 1", ColorTools.toEditedFloat(AURORA_AVOCADO, -0.065f, 0.0f, -0.3f, 0f));
        viewer.put("small bush 2", ColorTools.toEditedFloat(AURORA_WOODLANDS, -0.045f, -0.05f, -0.025f, 0f));
        viewer.put("deciduous tree 2", ColorTools.toEditedFloat(AURORA_IVY_GREEN, -0.02f, 0.0f, 0.0f, 0f));
        viewer.put("deciduous tree 3", ColorTools.toEditedFloat(AURORA_ASPARAGUS, -0.015f, 0.055f, 0.02f, 0f));
        viewer.put("large bush 2", ColorTools.toEditedFloat(AURORA_VIRIDIAN, -0.03f, -0.05f, 0.03f, 0f));
        viewer.put("tropical tree 1", ColorTools.toEditedFloat(AURORA_FLORAL_FOAM, -0.05f, 0.025f, 0.075f, 0f));
        viewer.put("tropical tree 2", ColorTools.toEditedFloat(AURORA_MAIDENHAIR_FERN, 0.0f, 0.0f, 0.02f, 0f));
        viewer.put("large bush 3", ColorTools.toEditedFloat(AURORA_KELLY_GREEN, 0.0f, 0.025f, 0.02f, 0f));
        viewer.put("tropical tree 3", ColorTools.toEditedFloat(AURORA_SOFT_TEAL, -0.15f, -0.07f, -0.03f, 0f));
        viewer.put("tropical tree 4", ColorTools.toEditedFloat(AURORA_PRASE, -0.04f, -0.02f, -0.02f, 0f));

//        viewer.put("ice", ColorTools.fromRGBA8888(240 << 24 | 248 << 16 | 255 << 8 | 255));
//        viewer.put("desert", ColorTools.fromRGBA8888(248 << 24 | 229 << 16 | 180 << 8 | 255));
//        viewer.put("savanna", ColorTools.fromRGBA8888(190 << 24 | 195 << 16 | 105 << 8 | 255));
//        viewer.put("tropicalRainforest", ColorTools.fromRGBA8888(66 << 24 | 123 << 16 | 25 << 8 | 255));
//        viewer.put("tundra", ColorTools.fromRGBA8888(151 << 24 | 175 << 16 | 159 << 8 | 255));
//        viewer.put("temperateRainforest", ColorTools.fromRGBA8888(54 << 24 | 113 << 16 | 60 << 8 | 255));
//        viewer.put("grassland", ColorTools.fromRGBA8888(155 << 24 | 205 << 16 | 110 << 8 | 255));
//        viewer.put("seasonalForest", ColorTools.fromRGBA8888(105 << 24 | 150 << 16 | 45 << 8 | 255));
//        viewer.put("borealForest", ColorTools.fromRGBA8888(75 << 24 | 105 << 16 | 45 << 8 | 255));
//        viewer.put("woodland", ColorTools.fromRGBA8888(92 << 24 | 160 << 16 | 70 << 8 | 255));
//        viewer.put("rocky", ColorTools.fromRGBA8888(171 << 24 | 175 << 16 | 145 << 8 | 255));
//        viewer.put("beach", ColorTools.fromRGBA8888(255 << 24 | 235 << 16 | 180 << 8 | 255));
//        viewer.put("empty", ColorTools.fromRGBA8888(34 << 24 | 32 << 16 | 52 << 8 | 255));
//        viewer.put("deep", ColorTools.fromRGBA8888(0 << 24 | 42 << 16 | 88 << 8 | 255));
//        viewer.put("shallow", ColorTools.fromRGBA8888(20 << 24 | 145 << 16 | 197 << 8 | 255));
//        int n = 0;
//        String[] heat = "COLDEST, COLDER, COLD, HOT, HOTTER, HOTTEST".split(", ");
//        String[] moisture = "DRIEST, DRIER, DRY, WET, WETTER, WETTEST, COAST, RIVER, LAKE, OCEAN, STRANGE".split(", ");
//        String[] biomeTable = {
//                //COLDEST //COLDER        //COLD            //HOT                  //HOTTER              //HOTTEST
//                "Ice",    "Ice",          "Grassland",      "Desert",              "Desert",             "Desert",             //DRIEST
//                "Ice",    "Tundra",       "Grassland",      "Grassland",           "Desert",             "Desert",             //DRIER
//                "Ice",    "Tundra",       "Woodland",       "Woodland",            "Savanna",            "Desert",             //DRY
//                "Ice",    "Tundra",       "SeasonalForest", "SeasonalForest",      "Savanna",            "Savanna",            //WET
//                "Ice",    "Tundra",       "BorealForest",   "TemperateRainforest", "TropicalRainforest", "Savanna",            //WETTER
//                "Ice",    "BorealForest", "BorealForest",   "TemperateRainforest", "TropicalRainforest", "TropicalRainforest", //WETTEST
//                "Rocky",  "Rocky",        "Beach",          "Beach",               "Beach",              "Beach",              //COASTS
//                "Ice",    "River",        "River",          "River",               "River",              "River",              //RIVERS
//                "Ice",    "Lake",         "Lake",           "Lake",                "Lake",               "Lake",               //LAKES
//                "Ocean",  "Ocean",        "Ocean",          "Ocean",               "Ocean",              "Ocean",              //OCEAN
//                "Empty",                                                                                                       //SPACE
//        };
//
//        viewer.put(n++, ColorTools.fromRGBA8888(0xF1F8FFFF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0xF0F8FFFF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0xA6D27EFF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0xF8E6B9FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0xF8E7BAFF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0xF8E7BBFF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0xF0F8FFFF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0xA2B8A8FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x9ECE72FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x93C368FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0xF8E6B7FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0xF8E6B8FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0xF0F8FFFF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x9DB3A3FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x599C44FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x60A34BFF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0xC3C875FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0xF8E4B6FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0xEAF2F9FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x97AF9EFF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x638E2AFF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x69962DFF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0xBFC46DFF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0xB9BE66FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0xDDE5EBFF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x8FA697FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x48652BFF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x346E39FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x487F20FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0xAFB461FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0xD0D6DDFF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x415B27FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x445F28FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x316837FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x407818FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x3D7116FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0xB3B89DFF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0xACB194FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0xF9E5B0FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0xFFEBB5FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0xFFEBB9FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0xFFEDBCFF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0xE4EBF2FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x2D9DCBFF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x2599C9FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x1B94C6FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x1590C5FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x128DC0FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0xDDE5EBFF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x2D9DCBFF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x1D95C7FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x1590C5FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x128DC0FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x1388BBFF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x1C416AFF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x113864FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x072F5DFF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x002857FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x002653FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x002550FF));
//        viewer.put(n++, ColorTools.fromRGBA8888(0x222034FF));

        for(OrderedMap.Entry<String, Float> e : viewer){
//        for(OrderedMap.Entry<Integer, Float> e : viewer){
//            if(e.key % 6 == 0) System.out.println("new Biome[]{");
//            System.out.printf("new Biome(%s, %s, \"%s\", \"%s\"),\n", heat[e.key % 6], moisture[e.key / 6], biomeTable[e.key], SimplePalette.bestMatch(e.value, 1));
//            if(e.key % 6 == 5) System.out.println("},");
//            System.out.printf(" | (long)parseDescription(\"%s\") << 32);\n", SimplePalette.bestMatch(e.value, 1));
            System.out.printf("%s: 0x%08X -> %s\n", e.key, ColorTools.toRGBA8888(e.value), SimplePalette.bestMatch(e.value, 1));
        }
    }
    public static final int SCREEN_WIDTH = 808;
    public static final int SCREEN_HEIGHT = 950;
    private Stage stage;
    private Skin skin;
    private Color color = new Color();

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Descriptive Matching Demo");
        config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
        config.setIdleFPS(20);
        config.useVsync(true);
        final DescriptionMatcher app = new DescriptionMatcher();
        new Lwjgl3Application(app, config);
    }

    @Override
    public void create() {
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        skin.getFont("default-font");
        stage = new Stage();
        Table tab = new Table(skin);
        tab.align(Align.center);
        tab.setFillParent(true);
        for(OrderedMap.Entry<String, Float> entry : viewer){
            Label lab = new Label(entry.key, skin);
            tab.add(lab).growX().padLeft(10f);
            Image original = new Image(skin, "white");
            original.getColor().set(ColorTools.toRGBA8888(entry.value));
            tab.add(original).growX().growY();
            Image altered = new Image(skin, "white");
            altered.getColor().set(ColorTools.toRGBA8888(SimplePalette.parseDescription(SimplePalette.bestMatch(entry.value, 1))));
            tab.add(altered).growX().growY().row();
        }
        stage.getRoot().addActor(tab);
        input.setInputProcessor(stage);
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }
}
