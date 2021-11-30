package com.github.tommyettinger.colorful.ipt_hq;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.FloatArray;
import com.github.tommyettinger.colorful.FloatColors;

/**
 * Static methods for handling gradients of smoothly-changing colors, typically inside of {@link FloatArray}s.
 * The intent is for the FloatArray to be used as a sequence of packed float IPT_HQ colors. You can create a new
 * FloatArray gradient with {@link #makeGradient(float, float, int, Interpolation)}, but any FloatArray will work
 * (although it only makes sense if it contains packed float colors or is empty). Once you have a FloatArray, you can
 * pass it to {@link #appendGradient(FloatArray, float, float, int, Interpolation)} to make a gradient between two
 * colors, or {@link #appendGradientChain(FloatArray, int, Interpolation, float...)} to make a gradient between more
 * than two colors. You can also customize each section between colors with
 * {@link #appendPartialGradient(FloatArray, float, float, int, Interpolation)}, which is just like appendGradient() but
 * doesn't add the end color (since it is the start color of the next partial gradient, until you finally end by
 * appending just the end). Using appendPartialGradient(), you can have each transition use a different number of steps.
 * <br>
 * This class does some special handling for IPT_HQ colors.
 */
public class GradientTools {
    /**
     * No need to instantiate.
     */
    private GradientTools(){
    }

    /**
     * Creates a FloatArray gradient from the packed float IPT_HQ color {@code start} to the packed float IPT_HQ color
     * {@code end}, taking the specified number of steps and using linear interpolation.
     * This limits individual steps of color to the correct IPT_HQ gamut, so even interpolations between colors at
     * extreme points in the color space will stay in-gamut.
     * @param start the packed float IPT_HQ color to start with
     * @param end the packed float IPT_HQ color to end on
     * @param steps how many steps the gradient should use; usually greater than 2, and must be non-negative
     * @return a new FloatArray that contains the requested gradient
     */
    public static FloatArray makeGradient(float start, float end, int steps) {
        return makeGradient(start, end, steps, Interpolation.linear);
    }
    /**
     * Creates a FloatArray gradient from the packed float IPT_HQ color {@code start} to the packed float IPT_HQ color
     * {@code end}, taking the specified number of steps and using the specified Interpolation for how it transitions.
     * This limits individual steps of color to the correct IPT_HQ gamut, so even interpolations between colors at
     * extreme points in the color space will stay in-gamut.
     * @param start the packed float IPT_HQ color to start with
     * @param end the packed float IPT_HQ color to end on
     * @param steps how many steps the gradient should use; usually greater than 2, and must be non-negative
     * @param interpolation a libGDX Interpolation that can be used to customize how start transitions to end
     * @return a new FloatArray that contains the requested gradient
     */
    public static FloatArray makeGradient(float start, float end, int steps, Interpolation interpolation) {
        FloatArray appending = new FloatArray(steps);
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
     * Appends a gradient from the packed float IPT_HQ color {@code start} to the packed float IPT_HQ color {@code end},
     * taking the specified number of steps and using linear Interpolation for how it transitions. This limits
     * individual steps of color to the correct IPT_HQ gamut, so even interpolations between colors at extreme points in
     * the color space will stay in-gamut.
     * @param appending a FloatArray that will be appended to
     * @param start the packed float IPT_HQ color to start with
     * @param end the packed float IPT_HQ color to end on
     * @param steps how many steps the gradient should use; usually greater than 2
     * @return {@code appending}, after adding the gradient to the end
     */
    public static FloatArray appendGradient(FloatArray appending, float start, float end, int steps) {
        return appendGradient(appending, start, end, steps, Interpolation.linear);
    }
    /**
     * Appends a gradient from the packed float IPT_HQ color {@code start} to the packed float IPT_HQ color {@code end},
     * taking the specified number of steps and using the specified Interpolation for how it transitions. This limits
     * individual steps of color to the correct IPT_HQ gamut, so even interpolations between colors at extreme points in
     * the color space will stay in-gamut.
     * @param appending a FloatArray that will be appended to
     * @param start the packed float IPT_HQ color to start with
     * @param end the packed float IPT_HQ color to end on
     * @param steps how many steps the gradient should use; usually greater than 2
     * @param interpolation a libGDX Interpolation that can be used to customize how start transitions to end
     * @return {@code appending}, after adding the gradient to the end
     */
    public static FloatArray appendGradient(FloatArray appending, float start, float end, int steps, Interpolation interpolation) {
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
     * Appends a gradient between several packed float IPT_HQ colors provided in {@code chain}. This uses linear
     * Interpolation for the whole gradient. Appends to the end of {@code appending} and produces a total of
     * {@code steps} colors.
     * @param appending a FloatArray that will be appended to
     * @param steps how many steps the gradient should use; usually greater than 2
     * @param chain an array or varargs of packed float IPT_HQ colors that this will interpolate through in order
     * @return {@code appending}, after adding the gradient to the end
     */
    public static FloatArray appendGradientChain(FloatArray appending, int steps, float... chain) {
        return appendGradientChain(appending, steps, Interpolation.linear, chain);
    }

    /**
     * Appends a gradient between several packed float IPT_HQ colors provided in {@code chain}. This uses linear
     * Interpolation for the whole gradient. Appends to the end of {@code appending} and produces a total of
     * {@code steps} colors.
     * @param appending a FloatArray that will be appended to
     * @param steps how many steps the gradient should use; usually greater than 2
     * @param chain a FloatArray of packed float IPT_HQ colors that this will interpolate through in order
     * @return {@code appending}, after adding the gradient to the end
     */
    public static FloatArray appendGradientChain(FloatArray appending, int steps, FloatArray chain) {
        return appendGradientChain(appending, steps, Interpolation.linear, chain);
    }

    /**
     * Appends a gradient between several packed float IPT_HQ colors provided in {@code chain}. This uses the specified
     * Interpolation for the whole gradient, which can make some colors use smaller sections than others. Appends to the
     * end of {@code appending} and produces a total of {@code steps} colors.
     * @param appending a FloatArray that will be appended to
     * @param steps how many steps the gradient should use; usually greater than 2
     * @param interpolation a libGDX Interpolation that can be used to customize how start transitions to end
     * @param chain a FloatArray of packed float IPT_HQ colors that this will interpolate through in order
     * @return {@code appending}, after adding the gradient to the end
     */
    public static FloatArray appendGradientChain(FloatArray appending, int steps, Interpolation interpolation, FloatArray chain) {
        if (appending == null)
            return null;
        if(chain == null)
            return appending;
        if (steps <= 0 || chain.size == 0) {
            return appending;
        }
        if (steps == 1 || chain.size == 1) {
            appending.add(chain.first());
            return appending;
        }
        appending.ensureCapacity(steps);
        int limit = steps - 1, splits = chain.size - 1;
        float step = 1f / steps, change = 0f;
        for (int i = 0; i < limit; i++) {
            float interp = interpolation.apply(change);
            float splint = Math.min(Math.max(interp * splits, 0f), splits - 0.000001f);
            int idx = (int)splint;
            appending.add(ColorTools.limitToGamut(FloatColors.lerpFloatColors(chain.get(idx), chain.get(idx+1), MathUtils.norm(idx, idx +1, splint))));
            change += step;
        }
        appending.add(chain.get(splits));
        return appending;
    }

    /**
     * Appends a gradient between several packed float IPT_HQ colors provided in {@code chain}. This uses the specified
     * Interpolation for the whole gradient, which can make some colors use smaller sections than others. Appends to the
     * end of {@code appending} and produces a total of {@code steps} colors.
     * @param appending a FloatArray that will be appended to
     * @param steps how many steps the gradient should use; usually greater than 2
     * @param interpolation a libGDX Interpolation that can be used to customize how start transitions to end
     * @param chain an array or varargs of packed float IPT_HQ colors that this will interpolate through in order
     * @return {@code appending}, after adding the gradient to the end
     */
    public static FloatArray appendGradientChain(FloatArray appending, int steps, Interpolation interpolation, float... chain) {
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
            appending.add(ColorTools.limitToGamut(FloatColors.lerpFloatColors(chain[idx], chain[idx+1], MathUtils.norm(idx, idx +1, splint))));
            change += step;
        }
        appending.add(chain[splits]);
        return appending;
    }

    /**
     * Exactly like {@link #appendGradient(FloatArray, float, float, int)}, but does not include
     * {@code end} in what it appends to {@code appending}. This is intended for the implementation of chained
     * gradients, where the end of a previous gradient becomes the start of the next one. This still uses the specified
     * number of steps, it just doesn't append {@code end} in the last step.
     * @param appending a FloatArray that will be appended to
     * @param start the packed float IPT_HQ color to start with
     * @param end the packed float IPT_HQ color to end just before
     * @param steps how many steps the gradient should use; usually greater than 2
     * @return {@code appending}, after adding the gradient to its end
     */
    public static FloatArray appendPartialGradient(FloatArray appending, float start, float end, int steps) {
        return appendPartialGradient(appending, start, end, steps, Interpolation.linear);
    }
    /**
     * Exactly like {@link #appendGradient(FloatArray, float, float, int, Interpolation)}, but does not include
     * {@code end} in what it appends to {@code appending}. This is intended for the implementation of chained
     * gradients, where the end of a previous gradient becomes the start of the next one. This still uses the specified
     * number of steps, it just doesn't append {@code end} in the last step.
     * @param appending a FloatArray that will be appended to
     * @param start the packed float IPT_HQ color to start with
     * @param end the packed float IPT_HQ color to end just before
     * @param steps how many steps the gradient should use; usually greater than 2
     * @param interpolation a libGDX Interpolation that can be used to customize how start transitions toward end
     * @return {@code appending}, after adding the gradient to its end
     */
    public static FloatArray appendPartialGradient(FloatArray appending, float start, float end, int steps, Interpolation interpolation){
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
