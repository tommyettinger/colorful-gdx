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

package com.github.tommyettinger.colorful.pure.oklab;

import com.github.tommyettinger.colorful.pure.FloatColors;
import com.github.tommyettinger.digital.Interpolations;
import com.github.tommyettinger.digital.Interpolations.InterpolationFunction;
import com.github.tommyettinger.digital.MathTools;
import com.github.tommyettinger.ds.FloatList;

/**
 * Static methods for handling gradients of smoothly-changing colors, typically inside of {@link FloatList}s.
 * The intent is for the FloatList to be used as a sequence of packed float Oklab colors. You can create a new
 * FloatList gradient with {@link #makeGradient(float, float, int, InterpolationFunction)}, but any FloatList will work
 * (although it only makes sense if it contains packed float colors or is empty). Once you have a FloatList, you can
 * pass it to {@link #appendGradient(FloatList, float, float, int, InterpolationFunction)} to make a gradient between two
 * colors, or {@link #appendGradientChain(FloatList, int, InterpolationFunction, float...)} to make a gradient between more
 * than two colors. You can also customize each section between colors with
 * {@link #appendPartialGradient(FloatList, float, float, int, InterpolationFunction)}, which is just like appendGradient() but
 * doesn't add the end color (since it is the start color of the next partial gradient, until you finally end by
 * appending just the end). Using appendPartialGradient(), you can have each transition use a different number of steps.
 * <br>
 * This class does some special handling for Oklab colors.
 */
public class GradientTools {
    /**
     * No need to instantiate.
     */
    private GradientTools(){
    }

    /**
     * Creates a FloatList gradient from the packed float Oklab color {@code start} to the packed float Oklab color
     * {@code end}, taking the specified number of steps and using linear interpolation.
     * This limits individual steps of color to the correct Oklab gamut, so even interpolations between colors at
     * extreme points in the color space will stay in-gamut.
     * @param start the packed float Oklab color to start with
     * @param end the packed float Oklab color to end on
     * @param steps how many steps the gradient should use; usually greater than 2, and must be non-negative
     * @return a new FloatList that contains the requested gradient
     */
    public static FloatList makeGradient(float start, float end, int steps) {
        return makeGradient(start, end, steps, Interpolations.linear);
    }
    /**
     * Creates a FloatList gradient from the packed float Oklab color {@code start} to the packed float Oklab color
     * {@code end}, taking the specified number of steps and using the specified InterpolationFunction for how it transitions.
     * This limits individual steps of color to the correct Oklab gamut, so even interpolations between colors at
     * extreme points in the color space will stay in-gamut.
     * @param start the packed float Oklab color to start with
     * @param end the packed float Oklab color to end on
     * @param steps how many steps the gradient should use; usually greater than 2, and must be non-negative
     * @param interpolation a libGDX InterpolationFunction that can be used to customize how start transitions to end
     * @return a new FloatList that contains the requested gradient
     */
    public static FloatList makeGradient(float start, float end, int steps, InterpolationFunction interpolation) {
        FloatList appending = new FloatList(steps);
        if(steps <= 0) {
            return appending;
        }
        if(steps == 1) {
            appending.add(start);
            return appending;
        }
        appendPartialGradient(appending, start, end, steps - 1, interpolation).add(end);
        return appending;
    }

    /**
     * Appends a gradient from the packed float Oklab color {@code start} to the packed float Oklab color {@code end},
     * taking the specified number of steps and using linear InterpolationFunction for how it transitions. This limits
     * individual steps of color to the correct Oklab gamut, so even interpolations between colors at extreme points in
     * the color space will stay in-gamut.
     * @param appending a FloatList that will be appended to
     * @param start the packed float Oklab color to start with
     * @param end the packed float Oklab color to end on
     * @param steps how many steps the gradient should use; usually greater than 2
     * @return {@code appending}, after adding the gradient to the end
     */
    public static FloatList appendGradient(FloatList appending, float start, float end, int steps) {
        return appendGradient(appending, start, end, steps, Interpolations.linear);
    }
    /**
     * Appends a gradient from the packed float Oklab color {@code start} to the packed float Oklab color {@code end},
     * taking the specified number of steps and using the specified InterpolationFunction for how it transitions. This limits
     * individual steps of color to the correct Oklab gamut, so even interpolations between colors at extreme points in
     * the color space will stay in-gamut.
     * @param appending a FloatList that will be appended to
     * @param start the packed float Oklab color to start with
     * @param end the packed float Oklab color to end on
     * @param steps how many steps the gradient should use; usually greater than 2
     * @param interpolation a libGDX InterpolationFunction that can be used to customize how start transitions to end
     * @return {@code appending}, after adding the gradient to the end
     */
    public static FloatList appendGradient(FloatList appending, float start, float end, int steps, InterpolationFunction interpolation) {
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
        appendPartialGradient(appending, start, end, steps - 1, interpolation).add(end);
        return appending;
    }

    /**
     * Appends a gradient between several packed float Oklab colors provided in {@code chain}. This uses linear
     * InterpolationFunction for the whole gradient. Appends to the end of {@code appending} and produces a total of
     * {@code steps} colors.
     * @param appending a FloatList that will be appended to
     * @param steps how many steps the gradient should use; usually greater than 2
     * @param chain an array or varargs of packed float Oklab colors that this will interpolate through in order
     * @return {@code appending}, after adding the gradient to the end
     */
    public static FloatList appendGradientChain(FloatList appending, int steps, float... chain) {
        return appendGradientChain(appending, steps, Interpolations.linear, chain);
    }

    /**
     * Appends a gradient between several packed float Oklab colors provided in {@code chain}. This uses linear
     * InterpolationFunction for the whole gradient. Appends to the end of {@code appending} and produces a total of
     * {@code steps} colors.
     * @param appending a FloatList that will be appended to
     * @param steps how many steps the gradient should use; usually greater than 2
     * @param chain a FloatList of packed float Oklab colors that this will interpolate through in order
     * @return {@code appending}, after adding the gradient to the end
     */
    public static FloatList appendGradientChain(FloatList appending, int steps, FloatList chain) {
        return appendGradientChain(appending, steps, Interpolations.linear, chain);
    }

    /**
     * Appends a gradient between several packed float Oklab colors provided in {@code chain}. This uses the specified
     * InterpolationFunction for the whole gradient, which can make some colors use smaller sections than others. Appends to the
     * end of {@code appending} and produces a total of {@code steps} colors.
     * @param appending a FloatList that will be appended to
     * @param steps how many steps the gradient should use; usually greater than 2
     * @param interpolation a libGDX InterpolationFunction that can be used to customize how start transitions to end
     * @param chain a FloatList of packed float Oklab colors that this will interpolate through in order
     * @return {@code appending}, after adding the gradient to the end
     */
    public static FloatList appendGradientChain(FloatList appending, int steps, InterpolationFunction interpolation, FloatList chain) {
        if (appending == null)
            return null;
        if(chain == null)
            return appending;
        if (steps <= 0 || chain.size() == 0) {
            return appending;
        }
        if (steps == 1 || chain.size() == 1) {
            appending.add(chain.first());
            return appending;
        }
        appending.ensureCapacity(steps);
        int limit = steps - 1, splits = chain.size() - 1;
        float step = 1f / steps, change = 0f;
        for (int i = 0; i < limit; i++) {
            float interp = interpolation.apply(change);
            float splint = Math.min(Math.max(interp * splits, 0f), splits - 0.000001f);
            int idx = (int)splint;
            appending.add(ColorTools.limitToGamut(FloatColors.lerpFloatColors(chain.get(idx), chain.get(idx+1), MathTools.norm(idx, idx +1, splint))));
            change += step;
        }
        appending.add(chain.get(splits));
        return appending;
    }

    /**
     * Appends a gradient between several packed float Oklab colors provided in {@code chain}. This uses the specified
     * InterpolationFunction for the whole gradient, which can make some colors use smaller sections than others. Appends to the
     * end of {@code appending} and produces a total of {@code steps} colors.
     * @param appending a FloatList that will be appended to
     * @param steps how many steps the gradient should use; usually greater than 2
     * @param interpolation a libGDX InterpolationFunction that can be used to customize how start transitions to end
     * @param chain an array or varargs of packed float Oklab colors that this will interpolate through in order
     * @return {@code appending}, after adding the gradient to the end
     */
    public static FloatList appendGradientChain(FloatList appending, int steps, InterpolationFunction interpolation, float... chain) {
        if (appending == null)
            return null;
        if(chain == null)
            return appending;
        if (steps <= 0 || chain.length == 0) {
            return appending;
        }
        if (steps == 1 || chain.length == 1) {
            appending.add(chain[0]);
            return appending;
        }
        appending.ensureCapacity(steps);
        int limit = steps - 1, splits = chain.length - 1;
        float step = 1f / steps, change = 0f;
        for (int i = 0; i < limit; i++) {
            float interp = interpolation.apply(change);
            float splint = Math.min(Math.max(interp * splits, 0f), splits - 0.000001f);
            int idx = (int)splint;
            appending.add(ColorTools.limitToGamut(FloatColors.lerpFloatColors(chain[idx], chain[idx+1], MathTools.norm(idx, idx +1, splint))));
            change += step;
        }
        appending.add(chain[splits]);
        return appending;
    }

    /**
     * Exactly like {@link #appendGradient(FloatList, float, float, int)}, but does not include
     * {@code end} in what it appends to {@code appending}. This is intended for the implementation of chained
     * gradients, where the end of a previous gradient becomes the start of the next one. This still uses the specified
     * number of steps, it just doesn't append {@code end} in the last step.
     * @param appending a FloatList that will be appended to
     * @param start the packed float Oklab color to start with
     * @param end the packed float Oklab color to end just before
     * @param steps how many steps the gradient should use; usually greater than 2
     * @return {@code appending}, after adding the gradient to its end
     */
    public static FloatList appendPartialGradient(FloatList appending, float start, float end, int steps) {
        return appendPartialGradient(appending, start, end, steps, Interpolations.linear);
    }
    /**
     * Exactly like {@link #appendGradient(FloatList, float, float, int, InterpolationFunction)}, but does not include
     * {@code end} in what it appends to {@code appending}. This is intended for the implementation of chained
     * gradients, where the end of a previous gradient becomes the start of the next one. This still uses the specified
     * number of steps, it just doesn't append {@code end} in the last step.
     * @param appending a FloatList that will be appended to
     * @param start the packed float Oklab color to start with
     * @param end the packed float Oklab color to end just before
     * @param steps how many steps the gradient should use; usually greater than 2
     * @param interpolation a libGDX InterpolationFunction that can be used to customize how start transitions toward end
     * @return {@code appending}, after adding the gradient to its end
     */
    public static FloatList appendPartialGradient(FloatList appending, float start, float end, int steps, InterpolationFunction interpolation){
        if(appending == null)
            return null;
        if(steps <= 0) {
            return appending;
        }
        if(steps == 1) {
            appending.add(start);
            return appending;
        }
        int limit = steps;
        float step = 1f / steps, change = 0f;
        for (int i = 0; i < limit; i++) {
            appending.add(ColorTools.limitToGamut(FloatColors.lerpFloatColors(start, end, interpolation.apply(change))));
            change += step;
        }
        return appending;
    }
}
