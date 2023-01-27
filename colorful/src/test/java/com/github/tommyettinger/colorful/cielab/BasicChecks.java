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

package com.github.tommyettinger.colorful.cielab;

import com.badlogic.gdx.utils.NumberUtils;
import com.badlogic.gdx.utils.ObjectFloatMap;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class BasicChecks {
    @Ignore
    @Test
    public void testLimitToGamut(){
        Assert.assertEquals(NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(0.5f, 0f, 0f, 1f)),
                NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(ColorTools.cielab(0.5f, 0f, 0f, 1f))));
        Assert.assertEquals(NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(0.5f, 1f, 0f, 1f)),
                NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(ColorTools.cielab(0.5f, 1f, 0f, 1f))));
        Assert.assertEquals(NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(0.5f, 1f, 1f, 1f)),
                NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(ColorTools.cielab(0.5f, 1f, 1f, 1f))));
        Assert.assertEquals(NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(0.5f, 0f, 1f, 1f)),
                NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(ColorTools.cielab(0.5f, 0f, 1f, 1f))));
        Assert.assertEquals(NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(0.5f, 0.126f, 0f, 1f)),
                NumberUtils.floatToRawIntBits(ColorTools.limitToGamut(ColorTools.cielab(0.5f, 0.126f, 0f, 1f))));
    }
    @Ignore
    @Test
    public void testInGamut(){
        for(ObjectFloatMap.Entries<String> colors = Palette.NAMED.entries(); colors.hasNext(); ) {
            ObjectFloatMap.Entry<String> ent = colors.next();
            if(!ColorTools.inGamut(ent.value))
                System.out.println(ent.key);
        }
    }

    @Test
    public void testChroma(){
        float max = 0f, bestCielab = 0f;
        for (int r = 0; r <= 255; r += 5) {
            for (int g = 0; g <= 255; g += 5) {
                for (int b = 0; b <= 255; b += 5) {
                    float cielab = ColorTools.fromRGBA8888(r << 24 | g << 16 | b << 8 | 255);
                    if(max != (max = Math.max(max, ColorTools.chroma(cielab)))) bestCielab = cielab;
                }
            }
        }
        System.out.printf("%1.8f, L=%1.8f, A=%1.8f, B=%1.8f\n", max
                , ColorTools.channelL(bestCielab), ColorTools.channelA(bestCielab), ColorTools.channelB(bestCielab));
    }
}
