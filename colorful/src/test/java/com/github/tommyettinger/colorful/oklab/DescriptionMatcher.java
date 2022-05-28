package com.github.tommyettinger.colorful.oklab;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.NumberUtils;
import com.badlogic.gdx.utils.OrderedMap;

import static com.badlogic.gdx.Gdx.input;
import static com.github.tommyettinger.colorful.oklab.FullPalette.*;

/**
 * Pretty specialized to a task needed for SquidSquad, where older named colors needed to be transitioned to the new
 * descriptive color system using Oklab.
 */
public class DescriptionMatcher extends ApplicationAdapter {
    public static final OrderedMap<String, Float> viewer = new OrderedMap<>(53);
    public static final String[] heat = "COLDEST, COLDER, COLD, HOT, HOTTER, HOTTEST".split(", ");
    public static final String[] moisture = "DRIEST, DRIER, DRY, WET, WETTER, WETTEST, COAST, RIVER, LAKE, OCEAN, STRANGE".split(", ");
    public static final String[] biomeTable = {
            //COLDEST //COLDER        //COLD            //HOT                  //HOTTER              //HOTTEST
            "Ice",    "Ice",          "Grassland",      "Desert",              "Desert",             "Desert",             //DRIEST
            "Ice",    "Tundra",       "Grassland",      "Grassland",           "Desert",             "Desert",             //DRIER
            "Ice",    "Tundra",       "Woodland",       "Woodland",            "Savanna",            "Desert",             //DRY
            "Ice",    "Tundra",       "SeasonalForest", "SeasonalForest",      "Savanna",            "Savanna",            //WET
            "Ice",    "Tundra",       "BorealForest",   "TemperateRainforest", "TropicalRainforest", "Savanna",            //WETTER
            "Ice",    "BorealForest", "BorealForest",   "TemperateRainforest", "TropicalRainforest", "TropicalRainforest", //WETTEST
            "Rocky",  "Rocky",        "Beach",          "Beach",               "Beach",              "Beach",              //COASTS
            "Ice",    "River",        "River",          "River",               "River",              "River",              //RIVERS
            "Ice",    "Lake",         "Lake",           "Lake",                "Lake",               "Lake",               //LAKES
            "Ocean",  "Ocean",        "Ocean",          "Ocean",               "Ocean",              "Ocean",              //OCEAN
            "Space",  "Moon",         "Cavern",         "Cavern",              "Exotic",             "Volcano"             //STRANGE
    };
    public static final float[] BIOME_COLOR_TABLE = new float[66], BIOME_DARK_COLOR_TABLE = new float[66];

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
//        viewer.put("tropicalRainforest", ColorTools.fromRGBA8888(40 << 24 | 100 << 16 | 15 << 8 | 255));
//        viewer.put("tundra", ColorTools.fromRGBA8888(151 << 24 | 175 << 16 | 159 << 8 | 255));
//        viewer.put("temperateRainforest", ColorTools.fromRGBA8888(54 << 24 | 113 << 16 | 60 << 8 | 255));
//        viewer.put("grassland", ColorTools.fromRGBA8888(145 << 24 | 165 << 16 | 100 << 8 | 255));
//        viewer.put("seasonalForest", ColorTools.fromRGBA8888(70 << 24 | 120 << 16 | 35 << 8 | 255));
//        viewer.put("borealForest", ColorTools.fromRGBA8888(75 << 24 | 105 << 16 | 45 << 8 | 255));
//        viewer.put("woodland", ColorTools.fromRGBA8888(92 << 24 | 160 << 16 | 70 << 8 | 255));
//        viewer.put("rocky", ColorTools.fromRGBA8888(171 << 24 | 175 << 16 | 145 << 8 | 255));
//        viewer.put("beach", ColorTools.fromRGBA8888(255 << 24 | 235 << 16 | 180 << 8 | 255));
//        viewer.put("empty", ColorTools.fromRGBA8888(34 << 24 | 32 << 16 | 52 << 8 | 255));
//        viewer.put("deep", ColorTools.fromRGBA8888(0 << 24 | 42 << 16 | 88 << 8 | 255));
//        viewer.put("shallow", ColorTools.fromRGBA8888(20 << 24 | 145 << 16 | 197 << 8 | 255));

        int n = 0;

        int
                Desert                 = 0 ,
                Savanna                = 1 ,
                TropicalRainforest     = 2 ,
                Grassland              = 3 ,
                Woodland               = 4 ,
                SeasonalForest         = 5 ,
                TemperateRainforest    = 6 ,
                BorealForest           = 7 ,
                Tundra                 = 8 ,
                Ice                    = 9 ,
                Beach                  = 10,
                Rocky                  = 11,
                Shallow                = 12,
                Ocean                  = 13,
                Space                  = 14,
                Moon                   = 15,
                Cavern                 = 16,
                Exotic                 = 17,
                Volcano                = 18;

        float iceColor = ColorTools.fromRGBA8888(240 << 24 | 248 << 16 | 255 << 8 | 255);
        float desertColor = ColorTools.fromRGBA8888(248 << 24 | 229 << 16 | 180 << 8 | 255);
        float savannaColor = ColorTools.fromRGBA8888(190 << 24 | 195 << 16 | 105 << 8 | 255);
        float tropicalRainforestColor = ColorTools.fromRGBA8888(40 << 24 | 100 << 16 | 15 << 8 | 255);
        float tundraColor = ColorTools.fromRGBA8888(151 << 24 | 175 << 16 | 159 << 8 | 255);
        float temperateRainforestColor = ColorTools.fromRGBA8888(54 << 24 | 113 << 16 | 60 << 8 | 255);
        float grasslandColor = ColorTools.fromRGBA8888(145 << 24 | 165 << 16 | 100 << 8 | 255);
        float seasonalForestColor = ColorTools.fromRGBA8888(70 << 24 | 120 << 16 | 35 << 8 | 255);
        float borealForestColor = ColorTools.fromRGBA8888(75 << 24 | 105 << 16 | 45 << 8 | 255);
        float woodlandColor = ColorTools.fromRGBA8888(92 << 24 | 160 << 16 | 70 << 8 | 255);
        float rockyColor = ColorTools.fromRGBA8888(171 << 24 | 175 << 16 | 145 << 8 | 255);
        float beachColor = ColorTools.fromRGBA8888(255 << 24 | 235 << 16 | 180 << 8 | 255);
        float spaceColor = ColorTools.fromRGBA8888(34 << 24 | 32 << 16 | 52 << 8 | 255);
        float moonColor = SimplePalette.parseDescription("lighter silver");
        float cavernColor = SimplePalette.parseDescription("darker dullest chocolate");
        float exoticColor = SimplePalette.parseDescription("lighter richest raspberry");
        float volcanoColor = SimplePalette.parseDescription("dark ember");
        float deepColor = ColorTools.fromRGBA8888(8 << 24 | 64 << 16 | 76 << 8 | 255);
        float shallowColor = ColorTools.fromRGBA8888(24 << 24 | 120 << 16 | 128 << 8 | 255);

        float[] biomeColors = {
                desertColor,
                savannaColor,
                tropicalRainforestColor,
                grasslandColor,
                woodlandColor,
                seasonalForestColor,
                temperateRainforestColor,
                borealForestColor,
                tundraColor,
                iceColor,
                beachColor,
                rockyColor,
                shallowColor,
                deepColor,
                spaceColor,
                moonColor,
                cavernColor,
                exoticColor,
                volcanoColor,
        };

        float[] BIOME_TABLE = {
                //COLDEST   //COLDER      //COLD               //HOT                     //HOTTER                 //HOTTEST
                Ice+0.85f,  Ice+0.65f,    Grassland+0.9f,      Desert+0.75f,             Desert+0.8f,             Desert+0.85f,            //DRYEST
                Ice+0.7f,   Tundra+0.9f,  Grassland+0.6f,      Grassland+0.3f,           Desert+0.65f,            Desert+0.7f,             //DRYER
                Ice+0.55f,  Tundra+0.7f,  Woodland+0.4f,       Woodland+0.6f,            Savanna+0.8f,            Desert+0.6f,             //DRY
                Ice+0.4f,   Tundra+0.5f,  SeasonalForest+0.3f, SeasonalForest+0.5f,      Savanna+0.6f,            Savanna+0.4f,            //WET
                Ice+0.2f,   Tundra+0.3f,  BorealForest+0.35f,  TemperateRainforest+0.4f, TropicalRainforest+0.6f, Savanna+0.2f,            //WETTER
                Ice+0.0f,   BorealForest, BorealForest+0.15f,  TemperateRainforest+0.2f, TropicalRainforest+0.4f, TropicalRainforest+0.2f, //WETTEST
                Rocky+0.9f, Rocky+0.6f,   Beach+0.4f,          Beach+0.55f,              Beach+0.75f,             Beach+0.9f,              //COASTS
                Ice+0.3f,   Shallow+0.9f, Shallow+0.75f,       Shallow+0.6f,             Shallow+0.5f,            Shallow+0.4f,            //RIVERS
                Ice+0.2f,   Shallow+0.9f, Shallow+0.65f,       Shallow+0.5f,             Shallow+0.4f,            Shallow+0.3f,            //LAKES
                Ocean+0.9f, Ocean+0.75f,  Ocean+0.6f,          Ocean+0.45f,              Ocean+0.3f,              Ocean+0.15f,             //OCEANS
                Space+0.5f, Moon+0.6f,    Cavern+0.2f,         Cavern+0.45f,             Exotic+0.4f,             Volcano+0.55f,           //STRANGE
        };
         float b, diff;
            for (int i = 0; i < 66; i++) {
                b = BIOME_TABLE[i];
                diff = ((b % 1.0f) - 0.48f) * 0.27f;
                BIOME_COLOR_TABLE[i] = b = (diff >= 0)
                        ? ColorTools.lighten(biomeColors[(int)b], diff)
                        : ColorTools.darken(biomeColors[(int)b], -diff);
                BIOME_DARK_COLOR_TABLE[i] = ColorTools.darken(b, 0.08f);
            }

        for (int i = 0; i < BIOME_COLOR_TABLE.length; i++) {
            System.out.printf("new Biome(%s, %s, \"%s\", 0x%08X),\n", heat[i % 6], moisture[i / 6], biomeTable[i], NumberUtils.floatToIntColor(BIOME_COLOR_TABLE[i]));
        }

        System.out.println();

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

//        for(OrderedMap.Entry<Integer, Float> e : viewer){
//            System.out.printf(" | (long)parseDescription(\"%s\") << 32);\n", SimplePalette.bestMatch(e.value, 1));
//        }

        //// uses String-to-Float viewer at top
        for(OrderedMap.Entry<String, Float> e : viewer){
            System.out.printf(" | (long)describe(\"%s\") << 32);\n", SimplePalette.bestMatch(e.value, 1));
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
        for(OrderedMap.Entry<String, Float> entry : viewer) {
            String key = entry.key;
            float value = entry.value;
            Label lab = new Label(key, skin);
            tab.add(lab).growX().padLeft(10f);
            float described = SimplePalette.parseDescription(SimplePalette.bestMatch(value, 1));
            Label diff = new Label(String.format("%2.4f", difference(value, described)), skin);
            tab.add(diff).padLeft(2).padRight(2).left();
            Image original = new Image(skin, "white");
            original.getColor().set(ColorTools.toRGBA8888(value));
            tab.add(original).growX().growY();
            Image altered = new Image(skin, "white");
            altered.getColor().set(ColorTools.toRGBA8888(described));
            tab.add(altered).growX().growY().row();
        }
//        for (int i = 0; i < BIOME_COLOR_TABLE.length; i++) {
//            String key = biomeTable[i];
//            float value = BIOME_COLOR_TABLE[i];
//            Label lab = new Label(key, skin);
//            tab.add(lab).growX().padLeft(10f);
//            float described = SimplePalette.parseDescription(SimplePalette.bestMatch(value, 1));
//            Label diff = new Label(String.format("%2.4f", difference(value, described)), skin);
//            tab.add(diff).padLeft(2).padRight(2).left();
//            Image original = new Image(skin, "white");
//            original.getColor().set(ColorTools.toRGBA8888(value));
//            tab.add(original).growX().growY();
//            Image altered = new Image(skin, "white");
//            altered.getColor().set(ColorTools.toRGBA8888(described));
//            tab.add(altered).growX().growY().row();
//        }
        stage.getRoot().addActor(tab);
        input.setInputProcessor(stage);
    }

    private float difference(float left, float right) {
        final float i = ColorTools.channelL(left) - ColorTools.channelL(right);
        final float p = (ColorTools.channelA(left) - ColorTools.channelA(right));
        final float t = (ColorTools.channelB(left) - ColorTools.channelB(right));
        return (float) Math.sqrt(i * i + p * p + t * t);
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
