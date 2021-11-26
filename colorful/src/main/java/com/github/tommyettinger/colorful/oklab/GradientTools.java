package com.github.tommyettinger.colorful.oklab;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.FloatArray;
import com.github.tommyettinger.colorful.FloatColors;

public class GradientTools {
    /**
     * No need to instantiate.
     */
    private GradientTools(){
    }

    /**
     * Appends a gradient from the packed float Oklab color {@code start} to the packed float Oklab color {@code end},
     * taking the specified number of steps and using the specified Interpolation for how it transitions. This limits
     * individual steps of color to the correct Oklab gamut, so even interpolations between colors at extreme points in
     * the color space will stay in-gamut.
     * @param appending a FloatArray that will be appended to
     * @param start the packed float Oklab color to start with
     * @param end the packed float Oklab color to end on
     * @param steps how many steps the gradient should use; usually greater than 2
     * @param interpolation a libGDX Interpolation that can be used to customize how start transitions to end.
     * @return {@code appending}, after adding the gradient to the end
     */
    public static FloatArray makeGradient(FloatArray appending, float start, float end, int steps, Interpolation interpolation){
        if(appending == null)
            return null;
        if(steps <= 0) {
            return appending;
        }
        if(steps == 1) {
            appending.add(start);
            return appending;
        }
        appending.ensureCapacity(steps);
        int limit = steps - 1;
        float step = 1f / limit, change = 0f;
        for (int i = 0; i <= limit; i++) {
            appending.add(ColorTools.limitToGamut(FloatColors.lerpFloatColors(start, end, interpolation.apply(change))));
            change += step;
        }
        return appending;
    }
}
