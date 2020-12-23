package com.github.tommyettinger.colorful.ipt_hq;

import com.badlogic.gdx.utils.ObjectFloatMap;

import static com.github.tommyettinger.colorful.ipt_hq.FullPalette.*;

public class DescriptionMatcher {
    public static void main(String[] args) {
        
        ObjectFloatMap<String> viewer = new ObjectFloatMap<>(53);
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

        for(ObjectFloatMap.Entry<String> e : viewer){
            System.out.printf("%s: 0x%08X -> %s\n", e.key, ColorTools.toRGBA8888(e.value), SimplePalette.bestMatch(e.value, 2));
        }
    }
}
