/*
 * Copyright (c) 2025 See AUTHORS file.
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

package com.github.tommyettinger.colorful;

import com.badlogic.gdx.utils.NumberUtils;

/**
 * Trigonometric approximations for sin(), cos(), tan(), asin(), acos(), atan(), and atan2(), with variants for most
 * that allow users to trade speed for precision or vice versa. This is primarily derived from libGDX's MathUtils class.
 * Unlike MathUtils, it exposes much more of its internal data, trusting that users can know what they are doing. A key
 * difference here is that all methods have variants that treat angles as radians, as degrees, and as turns. That is,
 * while a full rotation around a circle is {@code 2.0 * PI} radians, it is 360.0 degrees, and it is 1.0 turns.
 * <br>
 * This contains four fairly-sizeable lookup tables for some methods (in total RAM usage, just over 384KB); two store
 * 16385 results of sin() (one as float, one as double) and the other two store 16385 results of cos() (as float and as
 * double). Yes, I know they are the same data, just offset from each other or as a different type; some of the methods
 * here run so briefly that getting the index with offset nearly doubled the time taken by the method. Here,
 * {@link #sin(float)}, {@link #cos(float)}, and
 * {@link #tan(float)} use the LUTs. Other methods here use techniques ranging from basic Taylor series to Padé
 * approximants. The lookup-table-based sin() and cos() can be extraordinarily fast if the 64KB table can stay in a
 * processor cache, while the "smooth" approximations may have decent quality but perform less quickly compared to an
 * in-cache lookup table. Available now are "precise" versions of all methods here, such as
 * {@link #sinPrecise(float)}. The "precise" versions all avoid a lookup table entirely and tend to
 * be the most precise approximations here, while still outperforming {@link Math} versions of their function. In a few
 * cases, the "precise" versions are also the fastest; this occurs most often for tan variants, or situations
 * where a large lookup table is not possible for the processor to cache.
 * <br>
 * Relative to MathUtils in libGDX, the main new functionalities are the variants that take or
 * return measurements in turns, the now-available {@link #SIN_TABLE}, and {@link #COS_TABLE}.
 * Using the sin or cos table directly has other uses mentioned
 * in its docs (in particular, uniform random unit vectors).
 * <br>
 * MathUtils had its sin and cos methods created by Riven on JavaGaming.org . The versions of sin and cos here,
 * including the way the lookup table is calculated, have been updated several times by Tommy Ettinger. The asin(),
 * acos(), and atan() methods all use Taylor series approximations from the 1955 research study "Approximations for
 * Digital Computers," by RAND Corporation; though one might think such code would be obsolete over 60 years later, the
 * approximations from that study seem to have higher accuracy and speed than most attempts in later decades, often
 * those aimed at DSP usage. Even older is the basis for sinSmooth() and cosSmooth(); the versions here are updated to
 * be more precise, but are closely related to a 7th-century sine approximation by Bhaskara I. The update was given in
 * <a href="https://math.stackexchange.com/a/3886664">this Stack Exchange answer by WimC</a>. From the same site,
 * <a href="https://math.stackexchange.com/a/4453027">this Stack Exchange answer by Soonts</a> provided the tan()
 * method used here. The atan2Finite() family of methods is based on the
 * very fast structure given by commenter "imuli" on
 * <a href="https://www.dsprelated.com/showarticle/1052.php">Nic Taylor's DSP article</a>, but uses the last atan()
 * Taylor series approximation from the aforementioned 1955 research study. The "precise" methods are all drawn from
 * <a href="https://github.com/jrouwe/JoltPhysics">Jolt Physics</a> and its Vector4 implementation, which is where that
 * library's trigonometry code ends up calling. Jolt uses SIMD optimization for its trigonometry and other math code,
 * which unfortunately has no cross-platform counterpart in Java at the time of writing (June 2025); the "precise"
 * methods here are mostly ports of the C++ methods with the SIMD-specific code replaced with scalar code. Jolt was
 * written by Jorrit Rouwe and is MIT-licensed; the code based on Jolt here was in turn based partly on code for the
 * Cephes library by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
 */
@SuppressWarnings({"ExpressionComparedToItself", "PointlessArithmeticExpression"})
public final class TrigTools {

    /**
     * Not meant to be instantiated.
     */
    private TrigTools() {
    }
//<editor-fold defaultstate="collapsed" desc="Constants">

    /**
     * The {@code float} value that is closer than any other to
     * <i>pi</i>, the ratio of the circumference of a circle to its
     * diameter.
     */
    public static final float PI = (float) Math.PI;
    /**
     * 1.0f divided by {@link #PI}.
     */
    public static final float PI_INVERSE = (float) (1.0 / Math.PI);
    /**
     * 2f times {@link #PI}; the same as {@link #TAU}.
     */
    public static final float PI2 = PI * 2f;
    /**
     * 2f times {@link #PI}; the same as {@link #PI2}.
     */
    public static final float TAU = PI2;
    /**
     * {@link #PI} divided by 2f; the same as {@link #ETA}.
     */
    public static final float HALF_PI = PI * 0.5f;
    /**
     * {@link #PI} divided by 2f; the same as {@link #HALF_PI}.
     */
    public static final float ETA = HALF_PI;
    /**
     * {@link Math#PI} divided by 2.0; the same as {@link #ETA_D}.
     */
    public static final double HALF_PI_D = Math.PI * 0.5;
    /**
     * {@link Math#PI} divided by 2.0; the same as {@link #HALF_PI_D}.
     */
    public static final double ETA_D = HALF_PI_D;
    /**
     * {@link #PI} divided by 4f.
     */
    public static final float QUARTER_PI = PI * 0.25f;

    /**
     * {@link Math#PI} divided by 4.0.
     */
    public static final double QUARTER_PI_D = Math.PI * 0.25;

    /**
     * The hard-coded size of {@link #SIN_TABLE} and {@link #COS_TABLE} in
     * bits; this is 14 now, and could be adjusted in the future.
     */
    public static final int TABLE_BITS = 14; // 64KB. Adjust for accuracy.
    /**
     * The size of {@link #SIN_TABLE}, available separately from the table's length for convenience.
     */
    public static final int TABLE_SIZE = (1 << TABLE_BITS);

    /**
     * If you add this to an index used in {@link #SIN_TABLE}, you get the result of the cosine instead of the sine.
     */
    public static final int SIN_TO_COS = TABLE_SIZE >>> 2;

    /**
     * The change in angle required to go a quarter of the way around a circle, as measured in table indices for use
     * with {@link #SIN_TABLE} and {@link #COS_TABLE}. Equivalent to {@link #SIN_TO_COS}.
     */
    public static final int QUARTER_CIRCLE_INDEX = SIN_TO_COS;

    /**
     * The bitmask that can be used to confine any int to wrap within {@link #TABLE_SIZE}. Any accesses to
     * {@link #SIN_TABLE} with an index that could be out of bounds should probably be wrapped using this, as with
     * {@code SIN_TABLE[index & TABLE_MASK]}.
     */
    public static final int TABLE_MASK = TABLE_SIZE - 1;

    /**
     * Multiply by this to convert from a float angle in radians to an index in {@link #SIN_TABLE} (after it is rounded to int and masked with {@link #TABLE_MASK}).
     */
    public static final float radToIndex = TABLE_SIZE / PI2;
    /**
     * Multiply by this to convert from a float angle in degrees to an index in {@link #SIN_TABLE} (after it is rounded to int and masked with {@link #TABLE_MASK}).
     */
    public static final float degToIndex = TABLE_SIZE / 360f;
    /**
     * Multiply by this to convert from a float angle in turns to an index in {@link #SIN_TABLE} (after it is rounded to int and masked with {@link #TABLE_MASK}).
     */
    public static final float turnToIndex = TABLE_SIZE;

    /**
     * Multiply by this to convert from radians to degrees.
     */
    public static final float radiansToDegrees = (float) (180.0 / Math.PI);
    /**
     * Multiply by this to convert from degrees to radians.
     */
    public static final float degreesToRadians = (float) (Math.PI / 180.0);

    /**
     * A precalculated table of 16385 floats, corresponding to the y-value of points on the unit circle, ordered by
     * increasing angle. This should not be mutated, but it can be accessed directly for things like getting random
     * unit vectors, or implementing the "sincos" method (which assigns sin() to one item and cos() to another).
     * <br>
     * A quick way to get a random unit vector is to get a random number that can be no larger than the table size, as
     * with {@code int angle = (random.nextInt() & TrigTools.TABLE_MASK);}, and look up that angle in {@code COS_TABLE}
     * for the vector's x and {@code SIN_TABLE} for the vector's y.
     * Elements 0 and 16384 are identical to allow wrapping.
     */
    public static final float[] SIN_TABLE = new float[TABLE_SIZE+1];

    /**
     * A precalculated table of 16385 floats, corresponding to the x-value of points on the unit circle, ordered by
     * increasing angle. This should not be mutated, but it can be accessed directly for things like getting random
     * unit vectors, or implementing the "sincos" method (which assigns sin() to one item and cos() to another).
     * <br>
     * A quick way to get a random unit vector is to get a random number that can be no larger than the table size, as
     * with {@code int angle = (random.nextInt() & TrigTools.TABLE_MASK);}, and look up that angle in {@code COS_TABLE}
     * for the vector's x and {@code SIN_TABLE} for the vector's y.
     * Elements 0 and 16384 are identical to allow wrapping.
     */
    public static final float[] COS_TABLE = new float[TABLE_SIZE+1];

    static {
        for (int i = 0; i <= TABLE_SIZE; i++) {
            float theta = ((float)i) / TABLE_SIZE * PI2;
            SIN_TABLE[i] = sinPrecise(theta);
            COS_TABLE[i] = cosPrecise(theta);
        }

/*
 // This section can be uncommented to check how precise the 90-degree-angle increments are.
 // Surprisingly, the angles corresponding to 180 degrees (for sine) or 270 degrees (for cosine) are not exactly 0.
        float[] debug;
        double[] debugD;
        debug = new float[]{
        SIN_TABLE[0]                       ,
        SIN_TABLE[QUARTER_CIRCLE_INDEX]    ,
        SIN_TABLE[QUARTER_CIRCLE_INDEX * 2],
        SIN_TABLE[QUARTER_CIRCLE_INDEX * 3],
        SIN_TABLE[QUARTER_CIRCLE_INDEX * 4],
        };
        for(float f : debug) System.out.printf("%f %08X\n", f, NumberUtils.floatToIntBits(f));
        debug = new float[]{
        COS_TABLE[0]                       ,
        COS_TABLE[QUARTER_CIRCLE_INDEX]    ,
        COS_TABLE[QUARTER_CIRCLE_INDEX * 2],
        COS_TABLE[QUARTER_CIRCLE_INDEX * 3],
        COS_TABLE[QUARTER_CIRCLE_INDEX * 4],
        };
        for(float f : debug) System.out.printf("%f %08X\n", f, NumberUtils.floatToIntBits(f));
*/
        // The four right angles get extra-precise values, because they are
        // the most likely to need to be correct.

        SIN_TABLE[0]                          = 0f;
        SIN_TABLE[QUARTER_CIRCLE_INDEX]       = 1f;
        SIN_TABLE[QUARTER_CIRCLE_INDEX * 2]   = 0f;
        SIN_TABLE[QUARTER_CIRCLE_INDEX * 3]   = -1.0f;
        SIN_TABLE[QUARTER_CIRCLE_INDEX * 4]   = 0f;

        COS_TABLE[0]                          = 1f;
        COS_TABLE[QUARTER_CIRCLE_INDEX]       = 0f;
        COS_TABLE[QUARTER_CIRCLE_INDEX * 2]   = -1f;
        COS_TABLE[QUARTER_CIRCLE_INDEX * 3]   = 0f;
        COS_TABLE[QUARTER_CIRCLE_INDEX * 4]   = 1f;
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Table Queries">
    /**
     * Converts {@code radians} to an index that can be used in {@link #SIN_TABLE} or {@link #COS_TABLE}
     * to obtain the sine or cosine of the given angle. This method can be useful if you have
     * one angle and want to get both the sine and cosine of that angle (called the "sincos()" function elsewhere).
     * This tries to round the given angle to the nearest table index.
     *
     * @param radians an angle in radians; may be positive or negative
     * @return the index into {@link #SIN_TABLE} of the sine of radians
     */
    public static int radiansToTableIndex(final float radians) {
        return (int) (radians * radToIndex + 16384.5f) & TABLE_MASK;
    }

    /**
     * Converts {@code degrees} to an index that can be used in {@link #SIN_TABLE} or {@link #COS_TABLE}
     * to obtain the sine or cosine of the given angle. This method can be useful if you have
     * one angle and want to get both the sine and cosine of that angle (called the "sincos()" function elsewhere).
     * This tries to round the given angle to the nearest table index.
     *
     * @param degrees an angle in degrees; may be positive or negative
     * @return the index into {@link #SIN_TABLE} of the sine of degrees
     */
    public static int degreesToTableIndex(final float degrees) {
        return (int)(degrees * degToIndex + 16384.5f) & TABLE_MASK;
    }

    /**
     * Converts {@code turns} to an index that can be used in {@link #SIN_TABLE} or {@link #COS_TABLE}
     * to obtain the sine or cosine of the given angle. This method can be useful if you have
     * one angle and want to get both the sine and cosine of that angle (called the "sincos()" function elsewhere).
     * This tries to round the given angle to the nearest table index.
     *
     * @param turns an angle in turns; may be positive or negative
     * @return the index into {@link #SIN_TABLE} of the sine of turns
     */
    public static int turnsToTableIndex(final float turns) {
        return (int)(turns * turnToIndex + 16384.5f) & TABLE_MASK;
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Sine, Cosine, and Tangent">

    /**
     * Gets an approximation of the sine of {@code radians}. This offers about 2x to
     * 4x the throughput of {@link Math#sin(double)} (cast to float).
     * <br>
     * Internally, this uses {@link #SIN_TABLE}, but interpolates between two
     * adjacent entries in the table, rather than just using one entry unmodified.
     * @param radians an angle in radians; optimally between {@code -PI2} and {@code PI2}
     * @return the approximate sine of the given angle, from -1 to 1 inclusive
     */
    public static float sin(float radians) {
        radians *= radToIndex;
        final int floor = (int)(radians + 16384f) - 16384;
        final int masked = floor & TABLE_MASK;
        final float from = SIN_TABLE[masked], to = SIN_TABLE[masked+1];
        return from + (to - from) * (radians - floor);
    }

    /**
     * Gets an approximation of the cosine of {@code radians}. This offers about 2x to
     * 4x the throughput of {@link Math#cos(double)} (cast to float).
     * <br>
     * Internally, this uses {@link #COS_TABLE}, but interpolates between two
     * adjacent entries in the table, rather than just using one entry unmodified.
     * @param radians an angle in radians; optimally between {@code -PI2} and {@code PI2}
     * @return the approximate cosine of the given angle, from -1 to 1 inclusive
     */
    public static float cos(float radians) {
        radians = Math.abs(radians) * radToIndex;
        final int floor = (int)radians;
        final int masked = floor & TABLE_MASK;
        final float from = COS_TABLE[masked], to = COS_TABLE[masked+1];
        return from + (to - from) * (radians - floor);
    }

    /**
     * Gets an approximation of the tangent of {@code radians}. This offers much
     * higher throughput than {@link Math#tan(double)} (cast to float).
     * <br>
     * Internally, this gets one table index from the given angle (rounding down) and quickly gets another index by
     * adding 1 (effectively rounding up). The down and up indices are looked up in {@link #SIN_TABLE} and
     * {@link #COS_TABLE}, the sines are interpolated, the cosines are interpolated, and the sine is divided by the
     * cosine.
     * @param radians a float angle in radians, where 0 to {@link #PI2} is one rotation
     * @return a float approximation of tan()
     */
    public static float tan(float radians) {
        radians *= radToIndex;
        final int floor = (int)(radians + 16384.0) - 16384;
        final int masked = floor & TABLE_MASK;
        radians -= floor;
        final float fromS = SIN_TABLE[masked], toS = SIN_TABLE[masked+1];
        final float fromC = COS_TABLE[masked], toC = COS_TABLE[masked+1];
        return (fromS + (toS - fromS) * radians) / (fromC + (toC - fromC) * radians);
    }


    /**
     * Gets an approximation of the sine of {@code degrees}. This offers about 2x
     * to 4x the throughput of {@link Math#sin(double)} (converted from degrees and cast to float).
     * <br>
     * Internally, this uses {@link #SIN_TABLE}, but interpolates between two
     * adjacent entries in the table, rather than just using one entry unmodified.
     * @param degrees an angle in degrees; optimally between -360 and 360
     * @return the approximate sine of the given angle, from -1 to 1 inclusive
     */
    public static float sinDeg(float degrees) {
        degrees *= degToIndex;
        final int floor = (int)(degrees + 16384f) - 16384;
        final int masked = floor & TABLE_MASK;
        final float from = SIN_TABLE[masked], to = SIN_TABLE[masked+1];
        return from + (to - from) * (degrees - floor);
    }

    /**
     * Gets an approximation of the cosine of {@code degrees}. This offers about 2x to
     * 4x the throughput of {@link Math#cos(double)} (converted from degrees and cast to float).
     * <br>
     * Internally, this uses {@link #COS_TABLE}, but interpolates between two
     * adjacent entries in the table, rather than just using one entry unmodified.
     * @param degrees an angle in degrees; optimally between -360 and 360
     * @return the approximate cosine of the given angle, from -1 to 1 inclusive
     */
    public static float cosDeg(float degrees) {
        degrees = Math.abs(degrees) * degToIndex;
        final int floor = (int)degrees;
        final int masked = floor & TABLE_MASK;
        final float from = COS_TABLE[masked], to = COS_TABLE[masked+1];
        return from + (to - from) * (degrees - floor);
    }

    /**
     * Gets an approximation of the tangent of {@code degrees}. This offers much
     * higher throughput than {@link Math#tan(double)} (converted from degrees and cast to float).
     * <br>
     * Internally, this gets one table index from the given angle (rounding down) and quickly gets another index by
     * adding 1 (effectively rounding up). The down and up indices are looked up in {@link #SIN_TABLE} and
     * {@link #COS_TABLE}, the sines are interpolated, the cosines are interpolated, and the sine is divided by the
     * cosine.
     * @param degrees a float angle in degrees, where 0 to 360 is one rotation
     * @return a float approximation of tan()
     */
    public static float tanDeg(float degrees) {
        degrees *= degToIndex;
        final int floor = (int)(degrees + 16384.0) - 16384;
        final int masked = floor & TABLE_MASK;
        degrees -= floor;
        final float fromS = SIN_TABLE[masked], toS = SIN_TABLE[masked+1];
        final float fromC = COS_TABLE[masked], toC = COS_TABLE[masked+1];
        return (fromS + (toS - fromS) * degrees) / (fromC + (toC - fromC) * degrees);
    }

    /**
     * Gets an approximation of the sine of {@code turns}. This offers about 2x
     * to 4x the throughput of {@link Math#sin(double)} (converted from turns and cast to float).
     * <br>
     * Internally, this uses {@link #SIN_TABLE}, but interpolates between two
     * adjacent entries in the table, rather than just using one entry unmodified.
     * @param turns an angle in turns; optimally between -1 and 1
     * @return the approximate sine of the given angle, from -1 to 1 inclusive
     */
    public static float sinTurns(float turns) {
        turns *= turnToIndex;
        final int floor = (int)(turns + 16384f) - 16384;
        final int masked = floor & TABLE_MASK;
        final float from = SIN_TABLE[masked], to = SIN_TABLE[masked+1];
        return from + (to - from) * (turns - floor);
    }

    /**
     * Gets an approximation of the cosine of {@code turns}. This offers about 2x to
     * 4x the throughput of {@link Math#cos(double)} (converted from turns and cast to float).
     * <br>
     * Internally, this uses {@link #COS_TABLE}, but interpolates between two
     * adjacent entries in the table, rather than just using one entry unmodified.
     * @param turns an angle in turns; optimally between -1 and 1
     * @return the approximate cosine of the given angle, from -1 to 1 inclusive
     */
    public static float cosTurns(float turns) {
        turns = Math.abs(turns) * turnToIndex;
        final int floor = (int)turns;
        final int masked = floor & TABLE_MASK;
        final float from = COS_TABLE[masked], to = COS_TABLE[masked+1];
        return from + (to - from) * (turns - floor);
    }

    /**
     * Gets an approximation of the tangent of {@code turns}. This offers much
     * higher throughput than {@link Math#tan(double)} (converted from turns and cast to float).
     * <br>
     * Internally, this gets one table index from the given angle (rounding down) and quickly gets another index by
     * adding 1 (effectively rounding up). The down and up indices are looked up in {@link #SIN_TABLE} and
     * {@link #COS_TABLE}, the sines are interpolated, the cosines are interpolated, and the sine is divided by the
     * cosine.
     * @param turns a float angle in turns, where 0 to 1 is one rotation
     * @return a float approximation of tan()
     */
    public static float tanTurns(float turns) {
        turns *= turnToIndex;
        final int floor = (int)(turns + 16384.0) - 16384;
        final int masked = floor & TABLE_MASK;
        turns -= floor;
        final float fromS = SIN_TABLE[masked], toS = SIN_TABLE[masked+1];
        final float fromC = COS_TABLE[masked], toC = COS_TABLE[masked+1];
        return (fromS + (toS - fromS) * turns) / (fromC + (toC - fromC) * turns);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Precise Sine, Cosine, and Tangent">

    /**
     * A non-tabular sine approximation in radians that is typically faster than {@link Math#sin(double)} and accurate
     * to within two ULPs for inputs in the 0 to PI2 range. While this is slower than
     * {@link TrigTools#sin(float)}, it is usually a little more accurate, and its worst-case results are
     * significantly more accurate than sin().
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     * @param radians input in radians, typically between -PI2 and PI2 for greatest precision
     * @return the sine of the given angle as a float, between -1 and 1 inclusive
     */
    public static float sinPrecise(float radians) {
        float x = Math.abs(radians);
        int quadrant = (int)(0.6366197723675814f * x + 0.5f);
        // Cody-Waite argument reduction, https://stackoverflow.com/questions/42455143/sine-cosine-modular-extended-precision-arithmetic
        x = ((x - quadrant * 1.5703125f) - quadrant * 0.0004837512969970703125f) - quadrant * 7.549789948768648e-8f;
        float x2 = x * x, s;
        switch ((quadrant ^ (NumberUtils.floatToIntBits(radians) >>> 30 & 2)) & 3) {
            case 0:
                s = ((-1.9515295891e-4f * x2 + 8.3321608736e-3f) * x2 - 1.6666654611e-1f) * x2 * x + x;
                break;
            case 1:
                s = ((2.443315711809948e-5f * x2 - (1.388731625493765e-3f)) * x2 + (4.166664568298827e-2f)) * x2 * x2 - 0.5f * x2 + 1f;
                break;
            case 2:
                s = (((1.9515295891e-4f * x2 - 8.3321608736e-3f) * x2 + 1.6666654611e-1f) * x2 * x - x);
                break;
            default:
                s = (((-2.443315711809948e-5f * x2 + 1.388731625493765e-3f) * x2 - 4.166664568298827e-2f) * x2 * x2 + 0.5f * x2 - 1f);
        }
        return s;
    }

    /**
     * A non-tabular cosine approximation in radians that is typically faster than {@link Math#cos(double)} and
     * accurate to within two ULPs for inputs in the 0 to PI2 range. While this is slower than
     * {@link TrigTools#cos(float)}, it is usually a little more accurate, and its worst-case results are
     * significantly more accurate than cos().
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     * @param radians input in radians, typically between -PI2 and PI2 for greatest precision
     * @return the cosine of the given angle as a float, between -1 and 1 inclusive
     */
    public static float cosPrecise(float radians) {
        float x = Math.abs(radians);
        int quadrant = (int)(0.6366197723675814f * x + 0.5f);
        // Cody-Waite argument reduction, https://stackoverflow.com/questions/42455143/sine-cosine-modular-extended-precision-arithmetic
        x = ((x - quadrant * 1.5703125f) - quadrant * 0.0004837512969970703125f) - quadrant * 7.549789948768648e-8f;
        float x2 = x * x, s;
        switch (quadrant & 3) {
            case 3:
                s = ((-1.9515295891e-4f * x2 + 8.3321608736e-3f) * x2 - 1.6666654611e-1f) * x2 * x + x;
                break;
            case 0:
                s = ((2.443315711809948e-5f * x2 - (1.388731625493765e-3f)) * x2 + (4.166664568298827e-2f)) * x2 * x2 - 0.5f * x2 + 1f;
                break;
            case 1:
                s = (((1.9515295891e-4f * x2 - 8.3321608736e-3f) * x2 + 1.6666654611e-1f) * x2 * x - x);
                break;
            default:
                s = (((-2.443315711809948e-5f * x2 + 1.388731625493765e-3f) * x2 - 4.166664568298827e-2f) * x2 * x2 + 0.5f * x2 - 1f);
        }
        return s;
    }

    /**
     * A non-tabular sine approximation in degrees that is typically faster than {@link Math#sin(double)} and accurate
     * to within two ULPs for inputs in the 0 to 360 range. While this is slower than
     * {@link TrigTools#sinDeg(float)}, it is usually a little more accurate, and its worst-case results are
     * significantly more accurate than sinDeg().
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     * @param degrees input in degrees, typically between -360 and 360 for greatest precision
     * @return the sine of the given angle as a float, between -1 and 1 inclusive
     */
    public static float sinDegPrecise(float degrees) {
        float x = Math.abs(degrees);
        int quadrant = (int)(0.011111111f * x + 0.5f);
        x = (x - quadrant * 90f) * (HALF_PI / 90f);
        float x2 = x * x, s;
        switch ((quadrant ^ (NumberUtils.floatToIntBits(degrees) >>> 30 & 2)) & 3) {
            case 0:
                s = ((-1.9515295891e-4f * x2 + 8.3321608736e-3f) * x2 - 1.6666654611e-1f) * x2 * x + x;
                break;
            case 1:
                s = ((2.443315711809948e-5f * x2 - (1.388731625493765e-3f)) * x2 + (4.166664568298827e-2f)) * x2 * x2 - 0.5f * x2 + 1f;
                break;
            case 2:
                s = (((1.9515295891e-4f * x2 - 8.3321608736e-3f) * x2 + 1.6666654611e-1f) * x2 * x - x);
                break;
            default:
                s = (((-2.443315711809948e-5f * x2 + 1.388731625493765e-3f) * x2 - 4.166664568298827e-2f) * x2 * x2 + 0.5f * x2 - 1f);
        }
        return s;
    }

    /**
     * A non-tabular cosine approximation in degrees that is typically faster than {@link Math#cos(double)} and
     * accurate to within two ULPs for inputs in the 0 to 360 range. While this is slower than
     * {@link TrigTools#cosDeg(float)}, it is usually a little more accurate, and its worst-case results are
     * significantly more accurate than cosDeg().
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     * @param degrees input in degrees, typically between -360 and 360 for greatest precision
     * @return the cosine of the given angle as a float, between -1 and 1 inclusive
     */
    public static float cosDegPrecise(float degrees) {
        float x = Math.abs(degrees);
        int quadrant = (int)(0.011111111f * x + 0.5f);
        x = (x - quadrant * 90f) * (HALF_PI / 90f);
        float x2 = x * x, s;
        switch (quadrant & 3) {
            case 3:
                s = ((-1.9515295891e-4f * x2 + 8.3321608736e-3f) * x2 - 1.6666654611e-1f) * x2 * x + x;
                break;
            case 0:
                s = ((2.443315711809948e-5f * x2 - (1.388731625493765e-3f)) * x2 + (4.166664568298827e-2f)) * x2 * x2 - 0.5f * x2 + 1f;
                break;
            case 1:
                s = (((1.9515295891e-4f * x2 - 8.3321608736e-3f) * x2 + 1.6666654611e-1f) * x2 * x - x);
                break;
            default:
                s = (((-2.443315711809948e-5f * x2 + 1.388731625493765e-3f) * x2 - 4.166664568298827e-2f) * x2 * x2 + 0.5f * x2 - 1f);
        }
        return s;
    }

    /**
     * A non-tabular sine approximation in turns that is typically faster than {@link Math#sin(double)} and accurate
     * to within two ULPs for inputs in the 0 to 1 range. While this is slower than
     * {@link TrigTools#sinTurns(float)}, it is usually a little more accurate, and its worst-case results are
     * significantly more accurate than sinTurns().
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     * @param turns input in turns, typically between -1 and 1 for greatest precision
     * @return the sine of the given angle as a float, between -1 and 1 inclusive
     */
    public static float sinTurnsPrecise(float turns) {
        float x = Math.abs(turns);
        int quadrant = (int)(4f * x + 0.5f);
        x = (x - quadrant * 0.25f) * PI2;
        float x2 = x * x, s;
        switch ((quadrant ^ (NumberUtils.floatToIntBits(turns) >>> 30 & 2)) & 3) {
            case 0:
                s = ((-1.9515295891e-4f * x2 + 8.3321608736e-3f) * x2 - 1.6666654611e-1f) * x2 * x + x;
                break;
            case 1:
                s = ((2.443315711809948e-5f * x2 - (1.388731625493765e-3f)) * x2 + (4.166664568298827e-2f)) * x2 * x2 - 0.5f * x2 + 1f;
                break;
            case 2:
                s = (((1.9515295891e-4f * x2 - 8.3321608736e-3f) * x2 + 1.6666654611e-1f) * x2 * x - x);
                break;
            default:
                s = (((-2.443315711809948e-5f * x2 + 1.388731625493765e-3f) * x2 - 4.166664568298827e-2f) * x2 * x2 + 0.5f * x2 - 1f);
        }
        return s;
    }

    /**
     * A non-tabular cosine approximation in turns that is typically faster than {@link Math#cos(double)} and
     * accurate to within two ULPs for inputs in the 0 to 1 range. While this is slower than
     * {@link TrigTools#cosTurns(float)}, it is usually a little more accurate, and its worst-case results are
     * significantly more accurate than cosTurns().
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     * @param turns input in turns, typically between -1 and 1 for greatest precision
     * @return the cosine of the given angle as a float, between -1 and 1 inclusive
     */
    public static float cosTurnsPrecise(float turns) {
        float x = Math.abs(turns);
        int quadrant = (int)(4f * x + 0.5f);
        x = (x - quadrant * 0.25f) * PI2;
        float x2 = x * x, s;
        switch (quadrant & 3) {
            case 3:
                s = ((-1.9515295891e-4f * x2 + 8.3321608736e-3f) * x2 - 1.6666654611e-1f) * x2 * x + x;
                break;
            case 0:
                s = ((2.443315711809948e-5f * x2 - (1.388731625493765e-3f)) * x2 + (4.166664568298827e-2f)) * x2 * x2 - 0.5f * x2 + 1f;
                break;
            case 1:
                s = (((1.9515295891e-4f * x2 - 8.3321608736e-3f) * x2 + 1.6666654611e-1f) * x2 * x - x);
                break;
            default:
                s = (((-2.443315711809948e-5f * x2 + 1.388731625493765e-3f) * x2 - 4.166664568298827e-2f) * x2 * x2 + 0.5f * x2 - 1f);
        }
        return s;
    }

    /**
     * Returns the tangent in radians; non-tabular and very precise, but about half as fast as
     * {@link TrigTools#tan(float)}. This method is only very slightly more precise than tan();
     * the difference is only about 2 ULPs for the worst-case absolute error in the -1 to 1 range, though the error
     * almost certainly balloons significantly near the undefined inputs at odd multiples of {@link TrigTools#HALF_PI}.
     * The main reason to use this method is that it is non-tabular. If the {@link TrigTools#SIN_TABLE} and
     * {@link TrigTools#COS_TABLE} arrays are not in processor cache, this non-tabular method may become faster.
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     *
     * @param radians a float angle in radians, where 0 to {@link TrigTools#PI2} is one rotation
     * @return a float approximation of tan()
     */
    public static float tanPrecise(float radians) {
        float x = Math.abs(radians);
        int quadrant = (int)(0.6366197723675814f * x + 0.5f);
        // Cody-Waite argument reduction, https://stackoverflow.com/questions/42455143/sine-cosine-modular-extended-precision-arithmetic
        x = ((x - quadrant * 1.5703125f) - quadrant * 0.0004837512969970703125f) - quadrant * 7.549789948768648e-8f;
        float x2 = x * x;
        float p = (((((9.38540185543e-3f * x2 + (3.11992232697e-3f)) * x2 + (2.44301354525e-2f)) * x2
                + (5.34112807005e-2f)) * x2 + (1.33387994085e-1f)) * x2 + (3.33331568548e-1f)) * x2 * x + x;
        if((quadrant & 1) == 1)
            return -Math.signum(radians) / p;
        return Math.signum(radians) * p;
    }

    /**
     * Returns the tangent in degrees; non-tabular and very precise, but about half as fast as
     * {@link TrigTools#tanDeg(float)}. This method is only very slightly more precise than tanDeg(),
     * though the error almost certainly balloons significantly near the undefined inputs at odd multiples of 90.
     * The main reason to use this method is that it is non-tabular. If the {@link TrigTools#SIN_TABLE} and
     * {@link TrigTools#COS_TABLE} arrays are not in processor cache, this non-tabular method may become faster.
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     *
     * @param degrees a float angle in degrees, where 0 to {@code 360} is one rotation
     * @return a float approximation of tan()
     */
    public static float tanDegPrecise(float degrees) {
        float x = Math.abs(degrees);
        int quadrant = (int)(0.011111111f * x + 0.5f);
        x = (x - quadrant * 90f) * (HALF_PI / 90f);
        float x2 = x * x;
        float p = (((((9.38540185543e-3f * x2 + (3.11992232697e-3f)) * x2 + (2.44301354525e-2f)) * x2
                + (5.34112807005e-2f)) * x2 + (1.33387994085e-1f)) * x2 + (3.33331568548e-1f)) * x2 * x + x;
        if((quadrant & 1) == 1)
            return -Math.signum(degrees) / p;
        return Math.signum(degrees) * p;
    }

    /**
     * Returns the tangent in turns; non-tabular and very precise, but about half as fast as
     * {@link TrigTools#tanTurns(float)}. This method is only very slightly more precise than
     * tanTurns(), though the error almost certainly balloons significantly near the undefined inputs at odd
     * multiples of 0.25. The main reason to use this method is that it is non-tabular. If the
     * {@link TrigTools#SIN_TABLE} and {@link TrigTools#COS_TABLE} arrays are not in processor cache, this
     * non-tabular method may become faster.
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     *
     * @param turns a float angle in turns, where 0.0 to 1.0 is one rotation
     * @return a float approximation of tan()
     */
    public static float tanTurnsPrecise(float turns) {
        float x = Math.abs(turns);
        int quadrant = (int)(4 * x + 0.5f);
        x = (x - quadrant * 0.25f) * PI2;
        float x2 = x * x;
        float p = (((((9.38540185543e-3f * x2 + (3.11992232697e-3f)) * x2 + (2.44301354525e-2f)) * x2
                + (5.34112807005e-2f)) * x2 + (1.33387994085e-1f)) * x2 + (3.33331568548e-1f)) * x2 * x + x;
        if((quadrant & 1) == 1)
            return -Math.signum(turns) / p;
        return Math.signum(turns) * p;
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Arctangent">

    /**
     * Arc tangent approximation with very low error, using an algorithm from the 1955 research study "Approximations for Digital
     * Computers," by RAND Corporation (this is sheet 11's algorithm, which is the fourth-fastest and fourth-least precise). This
     * method is usually about 4x faster than {@link Math#atan(double)}, but is somewhat less precise than Math's implementation.
     *
     * @param i an input to the inverse tangent function; any float is accepted
     * @return an output from the inverse tangent function in radians, from {@code -HALF_PI} to {@code HALF_PI} inclusive
     */
    public static float atan(float i) {
        // We use double precision internally, because some constants need double precision.
        // This clips infinite inputs at Double.MAX_VALUE, which still probably becomes infinite
        // again when converted back to float.
        double n = Math.min(Math.abs(i), Double.MAX_VALUE);
        // c uses the "equally-good" formulation that permits n to be from 0 to almost infinity.
        double c = (n - 1.0) / (n + 1.0);
        // The approximation needs 6 odd powers of c.
        double c2 = c * c;
        double c3 = c * c2;
        double c5 = c3 * c2;
        double c7 = c5 * c2;
        double c9 = c7 * c2;
        double c11 = c9 * c2;
        return (float) (Math.signum(i) * (QUARTER_PI_D
                + (0.99997726 * c - 0.33262347 * c3 + 0.19354346 * c5 - 0.11643287 * c7 + 0.05265332 * c9 - 0.0117212 * c11)));
    }

    /**
     * Arc tangent approximation returning a value measured in positive or negative degrees, using an algorithm from the
     * 1955 research study "Approximations for Digital Computers," by RAND Corporation (this is sheet 11's algorithm,
     * which is the fourth-fastest and fourth-least precise).
     *
     * @param i an input to the inverse tangent function; any float is accepted
     * @return an output from the inverse tangent function in degrees, from {@code -90} to {@code 90} inclusive
     */
    public static float atanDeg(float i) {
        // We use double precision internally, because some constants need double precision.
        // This clips infinite inputs at Double.MAX_VALUE, which still probably becomes infinite
        // again when converted back to float.
        double n = Math.min(Math.abs(i), Double.MAX_VALUE);
        // c uses the "equally-good" formulation that permits n to be from 0 to almost infinity.
        double c = (n - 1.0) / (n + 1.0);
        // The approximation needs 6 odd powers of c.
        double c2 = c * c;
        double c3 = c * c2;
        double c5 = c3 * c2;
        double c7 = c5 * c2;
        double c9 = c7 * c2;
        double c11 = c9 * c2;
        return (float) (Math.signum(i) * (45.0
                + (57.2944766070562 * c - 19.05792099799635 * c3 + 11.089223410359068 * c5 - 6.6711120475953765 * c7 + 3.016813013351768 * c9 - 0.6715752908287405 * c11)));
    }

    /**
     * Arc tangent approximation with very low error, using an algorithm from the 1955 research study "Approximations for Digital
     * Computers," by RAND Corporation (this is sheet 11's algorithm, which is the fourth-fastest and fourth-least precise).
     *
     * @param i an input to the inverse tangent function; any float is accepted
     * @return an output from the inverse tangent function in turns, from {@code -0.25} to {@code 0.25} inclusive
     */
    public static float atanTurns(float i) {
        // We use double precision internally, because some constants need double precision.
        // This clips infinite inputs at Double.MAX_VALUE, which still probably becomes infinite
        // again when converted back to float.
        double n = Math.min(Math.abs(i), Double.MAX_VALUE);
        // c uses the "equally-good" formulation that permits n to be from 0 to almost infinity.
        double c = (n - 1.0) / (n + 1.0);
        // The approximation needs 6 odd powers of c.
        double c2 = c * c;
        double c3 = c * c2;
        double c5 = c3 * c2;
        double c7 = c5 * c2;
        double c9 = c7 * c2;
        double c11 = c9 * c2;
        return (float) (Math.signum(i) * (0.125
                + (0.15915132390848943 * c - 0.052938669438878753 * c3 + 0.030803398362108523 * c5
                - 0.01853086679887605 * c7 + 0.008380036148199356 * c9 - 0.0018654869189687236 * c11)));
    }


    /**
     * Arc tangent approximation with very low error, using an algorithm from the 1955 research study "Approximations for Digital
     * Computers," by RAND Corporation (this is sheet 11's algorithm, which is the fourth-fastest and fourth-least precise). This
     * method is usually about 4x faster than {@link Math#atan(double)}, but is somewhat less precise than Math's implementation.
     *
     * @param i an input to the inverse tangent function; any double is accepted
     * @return an output from the inverse tangent function in radians, from {@code -HALF_PI} to {@code HALF_PI} inclusive
     */
    public static double atan(double i) {
        // We use double precision internally, because some constants need double precision.
        // This clips infinite inputs at Double.MAX_VALUE, which still probably becomes infinite
        // again when converted back to double.
        double n = Math.min(Math.abs(i), Double.MAX_VALUE);
        // c uses the "equally-good" formulation that permits n to be from 0 to almost infinity.
        double c = (n - 1.0) / (n + 1.0);
        // The approximation needs 6 odd powers of c.
        double c2 = c * c;
        double c3 = c * c2;
        double c5 = c3 * c2;
        double c7 = c5 * c2;
        double c9 = c7 * c2;
        double c11 = c9 * c2;
        return Math.signum(i) * (QUARTER_PI_D
                + (0.99997726 * c - 0.33262347 * c3 + 0.19354346 * c5 - 0.11643287 * c7 + 0.05265332 * c9 - 0.0117212 * c11));
    }

    /**
     * Arc tangent approximation returning a value measured in positive or negative degrees, using an algorithm from the
     * 1955 research study "Approximations for Digital Computers," by RAND Corporation (this is sheet 11's algorithm,
     * which is the fourth-fastest and fourth-least precise).
     *
     * @param i an input to the inverse tangent function; any double is accepted
     * @return an output from the inverse tangent function in degrees, from {@code -90} to {@code 90} inclusive
     */
    public static double atanDeg(double i) {
        // We use double precision internally, because some constants need double precision.
        // This clips infinite inputs at Double.MAX_VALUE, which still probably becomes infinite
        // again when converted back to double.
        double n = Math.min(Math.abs(i), Double.MAX_VALUE);
        // c uses the "equally-good" formulation that permits n to be from 0 to almost infinity.
        double c = (n - 1.0) / (n + 1.0);
        // The approximation needs 6 odd powers of c.
        double c2 = c * c;
        double c3 = c * c2;
        double c5 = c3 * c2;
        double c7 = c5 * c2;
        double c9 = c7 * c2;
        double c11 = c9 * c2;
        return Math.signum(i) * (45.0
                + (57.2944766070562 * c - 19.05792099799635 * c3 + 11.089223410359068 * c5 - 6.6711120475953765 * c7 + 3.016813013351768 * c9 - 0.6715752908287405 * c11));
    }

    /**
     * Arc tangent approximation with very low error, using an algorithm from the 1955 research study "Approximations for Digital
     * Computers," by RAND Corporation (this is sheet 11's algorithm, which is the fourth-fastest and fourth-least precise).
     *
     * @param i an input to the inverse tangent function; any double is accepted
     * @return an output from the inverse tangent function in turns, from {@code -0.25} to {@code 0.25} inclusive
     */
    public static double atanTurns(double i) {
        // We use double precision internally, because some constants need double precision.
        // This clips infinite inputs at Double.MAX_VALUE, which still probably becomes infinite
        // again when converted back to double.
        double n = Math.min(Math.abs(i), Double.MAX_VALUE);
        // c uses the "equally-good" formulation that permits n to be from 0 to almost infinity.
        double c = (n - 1.0) / (n + 1.0);
        // The approximation needs 6 odd powers of c.
        double c2 = c * c;
        double c3 = c * c2;
        double c5 = c3 * c2;
        double c7 = c5 * c2;
        double c9 = c7 * c2;
        double c11 = c9 * c2;
        return Math.signum(i) * (0.125
                + (0.15915132390848943 * c - 0.052938669438878753 * c3 + 0.030803398362108523 * c5
                - 0.01853086679887605 * c7 + 0.008380036148199356 * c9 - 0.0018654869189687236 * c11));
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Atan2">
    /**
     * A fast approximation of {@link Math#atan2(double, double)} that is only defined for finite input arguments.
     * The atan2() function takes y first, then x, and returns the angle in radians from
     * the origin to the given point. If y is non-negative, this returns a float from 0.0
     * to {@link #PI}, otherwise it returns a float from -0.0 to -PI.
     * <br>
     * This returns NaN when given
     * infinite arguments (or NaN). In the undefined case where {@code y == 0f && x == 0f},
     * this returns y (which may be -0.0); this differs from Math.atan2(), which returns
     * {@link Math#PI} when y is 0.0 and x is -0.0.
     * <br>
     * Credit to imuli and Nic Taylor; imuli commented on
     * <a href="https://www.dsprelated.com/showarticle/1052.php">Taylor's article</a> with very useful info.
     * Uses the "Sheet 13" algorithm from "Approximations for Digital Computers," by RAND Corporation (1955)
     * for its atan() approximation over the {@code (0,1]} domain.
     *
     * @param y any finite float; note the unusual argument order (y is first here!)
     * @param x any finite float; note the unusual argument order (x is second here!)
     * @return the angle in radians from the origin to the given point
     */
    public static float atan2(final float y, final float x) {
        if (y == 0f && x >= 0f) return y;
        float ay = Math.abs(y), ax = Math.abs(x);
        boolean invert = ay > ax;
        float z = invert ? ax / ay : ay / ax;
        float s = z * z;
        z *= (((((((-0.004054058f * s + 0.0218612288f) * s - 0.0559098861f) * s + 0.0964200441f)
                * s - 0.1390853351f) * s + 0.1994653599f) * s - 0.3332985605f) * s + 0.9999993329f);
        if (invert) z = HALF_PI - z;
        if (x < 0f) z = PI - z;
        return y < 0f ? -z : z;
    }

    /**
     * A fast approximation of atan2() in degrees that is only defined for finite input arguments.
     * The atan2Deg() function takes y first, then x, and returns the angle in degrees from
     * the origin to the given point. If y is non-negative, this returns a float from 0.0
     * to 180.0 , otherwise it returns a float from -0.0 to -180.0 .
     * <br>
     * This returns NaN when given
     * infinite arguments (or NaN). In the undefined case where {@code y == 0f && x == 0f},
     * this returns y (which may be -0.0); this differs from Math.atan2(), which returns
     * {@link Math#PI} (in radians) when y is 0.0 and x is -0.0.
     * <br>
     * Credit to imuli and Nic Taylor; imuli commented on
     * <a href="https://www.dsprelated.com/showarticle/1052.php">Taylor's article</a> with very useful info.
     * Uses the "Sheet 13" algorithm from "Approximations for Digital Computers," by RAND Corporation (1955)
     * for its atan() approximation over the {@code (0,1]} domain.
     *
     * @param y any finite float; note the unusual argument order (y is first here!)
     * @param x any finite float; note the unusual argument order (x is second here!)
     * @return the angle in degrees from the origin to the given point
     */
    public static float atan2Deg(final float y, final float x) {
        if (y == 0f && x >= 0f) return y;
        float ay = Math.abs(y), ax = Math.abs(x);
        boolean invert = ay > ax;
        float z = invert ? ax / ay : ay / ax;
        float s = z * z;
        z *= ((((((-0.2322804062831325f * s + 1.2525561619334924f) * s - 3.2034005556446465f) * s + 5.52446147949459f)
                * s - 7.969002832028255f) * s + 11.428523528717331f) * s - 19.09660103251952f) * s + 57.29574194704188f;
        if (invert) z = 90f - z;
        if (x < 0f) z = 180f - z;
        return y < 0f ? -z : z;
    }

    /**
     * A fast approximation of atan2() in non-negative degrees that is only defined for finite input arguments.
     * The atan2Deg360() function takes y first, then x, and returns the angle in degrees from
     * the origin to the given point. This returns a float from 0.0 to 360.0, counterclockwise
     * when y points up.
     * <br>
     * This returns NaN when given
     * infinite arguments (or NaN). In the undefined case where {@code y == 0f && x == 0f},
     * this returns 0.0; this differs from Math.atan2(), which returns
     * {@link Math#PI} (in radians) when y is 0.0 and x is -0.0.
     * <br>
     * Credit to imuli and Nic Taylor; imuli commented on
     * <a href="https://www.dsprelated.com/showarticle/1052.php">Taylor's article</a> with very useful info.
     * Uses the "Sheet 13" algorithm from "Approximations for Digital Computers," by RAND Corporation (1955)
     * for its atan() approximation over the {@code (0,1]} domain.
     *
     * @param y any finite float; note the unusual argument order (y is first here!)
     * @param x any finite float; note the unusual argument order (x is second here!)
     * @return the angle in degrees from the origin to the given point, from 0 to 360
     */
    public static float atan2Deg360(final float y, final float x) {
        if (y == 0f && x >= 0f) return 0f;
        float ay = Math.abs(y), ax = Math.abs(x);
        boolean invert = ay > ax;
        float z = invert ? ax / ay : ay / ax;
        float s = z * z;
        z *= ((((((-0.2322804062831325f * s + 1.2525561619334924f) * s - 3.2034005556446465f) * s + 5.52446147949459f) * s - 7.969002832028255f)
                * s + 11.428523528717331f) * s - 19.09660103251952f) * s + 57.29574194704188f;
        if (invert) z = 90f - z;
        if (x < 0f) z = 180f - z;
        return y < 0f ? 360f - z : z;
    }

    /**
     * A fast approximation of atan2() in turns that is only defined for finite input arguments.
     * The atan2Turns() function takes y first, then x, and returns the angle in turns from
     * the origin to the given point. This returns a float from 0.0 to 1.0, counterclockwise
     * when y points up.
     * <br>
     * This returns NaN when given
     * infinite arguments (or NaN). In the undefined case where {@code y == 0f && x == 0f},
     * this returns 0.0; this differs from Math.atan2(), which returns
     * {@link Math#PI} (in radians) when y is 0.0 and x is -0.0.
     * <br>
     * Credit to imuli and Nic Taylor; imuli commented on
     * <a href="https://www.dsprelated.com/showarticle/1052.php">Taylor's article</a> with very useful info.
     * Uses the "Sheet 13" algorithm from "Approximations for Digital Computers," by RAND Corporation (1955)
     * for its atan() approximation over the {@code (0,1]} domain.
     *
     * @param y any finite float; note the unusual argument order (y is first here!)
     * @param x any finite float; note the unusual argument order (x is second here!)
     * @return the angle in turns from the origin to the given point, from 0.0 to 1.0
     */
    public static float atan2Turns(final float y, final float x) {
        if (y == 0f && x >= 0f) return 0f;
        float ay = Math.abs(y), ax = Math.abs(x);
        boolean invert = ay > ax;
        float z = invert ? ax / ay : ay / ax;
        float s = z * z;
        z *= (((((((-6.452233507864792E-4f * s + 0.003479322672037479f) * s - 0.008898334876790684f) * s + 0.015345726331929417f) * s - 0.022136118977856264f)
                * s + 0.03174589869088148f) * s - 0.05304611397922089f) * s + 0.15915483874178302f);
        if (invert) z = 0.25f - z;
        if (x < 0f) z = 0.5f - z;
        return y < 0f ? 1f - z : z;
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Arcsine and Arccosine">

    /**
     * Returns arcsine in radians; less accurate than Math.asin but may be faster. Average error of 0.000028447 radians (0.0016298931
     * degrees), largest error of 0.000067592 radians (0.0038727364 degrees). This implementation does not return NaN if given an
     * out-of-range input (Math.asin does return NaN), unless the input is NaN.
     *
     * @param a asin is defined only when a is between -1f and 1f, inclusive
     * @return between {@code -HALF_PI} and {@code HALF_PI} when a is in the defined range
     */
    public static float asin(final float a) {
        if (a >= 0f) {
            return (float) (HALF_PI_D - Math.sqrt(1.0 - a) * (1.5707288 + a * (-0.2121144 + a * (0.0742610 + a * -0.0187293))));
        }
        return (float) (Math.sqrt(1.0 + a) * (1.5707288 + a * (0.2121144 + a * (0.0742610 + a * 0.0187293))) - HALF_PI_D);
    }

    /**
     * Returns arcsine in degrees. This implementation does not return NaN if given an
     * out-of-range input (Math.asin does return NaN), unless the input is NaN.
     *
     * @param a asin is defined only when a is between -1f and 1f, inclusive
     * @return between {@code -90} and {@code 90} when a is in the defined range
     */
    public static float asinDeg(float a) {
        if (a >= 0f) {
            return (float) (90.0 - Math.sqrt(1.0 - a) * (89.99613099964837 + a * (-12.153259893949748 + a * (4.2548418824210055 + a * -1.0731098432343729))));
        }
        return (float) (Math.sqrt(1.0 + a) * (89.99613099964837 + a * (12.153259893949748 + a * (4.2548418824210055 + a * 1.0731098432343729))) - 90.0);
    }

    /**
     * Returns arcsine in turns. This implementation does not return NaN if given an
     * out-of-range input (Math.asin does return NaN), unless the input is NaN.
     * Note that unlike {@link #atan2Turns(float, float)}, this can return negative turn values.
     *
     * @param a asin is defined only when a is between -1f and 1f, inclusive
     * @return between {@code -0.25} and {@code 0.25} when a is in the defined range
     */
    public static float asinTurns(float a) {
        if (a >= 0f) {
            return (float) (0.25 - Math.sqrt(1.0 - a) * (0.24998925277680104 + a * (-0.033759055260971525 + a * (0.011819005228947238 + a * -0.0029808606756510357))));
        }
        return (float) (Math.sqrt(1.0 + a) * (0.24998925277680104 + a * (0.033759055260971525 + a * (0.011819005228947238 + a * 0.0029808606756510357))) - 0.25);
    }

    /**
     * Returns arccosine in radians; less accurate than Math.acos but may be faster. Average error of 0.00002845 radians (0.0016300649
     * degrees), largest error of 0.000067548 radians (0.0038702153 degrees). This implementation does not return NaN if given an
     * out-of-range input (Math.acos does return NaN), unless the input is NaN.
     *
     * @param a acos is defined only when a is between -1f and 1f, inclusive
     * @return between {@code 0} and {@code PI} when a is in the defined range
     */
    public static float acos(float a) {
        if (a >= 0f) {
            return (float) (Math.sqrt(1.0 - a) * (1.5707288 + a * (-0.2121144 + a * (0.0742610 + a * -0.0187293))));
        }
        return (float) (Math.PI
                - Math.sqrt(1.0 + a) * (1.5707288 + a * (0.2121144 + a * (0.0742610 + a * 0.0187293))));
    }

    /**
     * Returns arccosine in degrees. This implementation does not return NaN if given an
     * out-of-range input (Math.acos does return NaN), unless the input is NaN.
     *
     * @param a acos is defined only when a is between -1f and 1f, inclusive
     * @return between {@code 0} and {@code 180} when a is in the defined range
     */
    public static float acosDeg(float a) {
        if (a >= 0f) {
            return (float) (Math.sqrt(1.0 - a) * (89.99613099964837 + a * (-12.153259533621753 + a * (4.254842010910525 + a * -1.0731098035209208))));
        }
        return (float) (180.0
                - Math.sqrt(1.0 + a) * (89.99613099964837 + a * (12.153259533621753 + a * (4.254842010910525 + a * 1.0731098035209208))));
    }

    /**
     * Returns arccosine in turns. This implementation does not return NaN if given an
     * out-of-range input (Math.acos does return NaN), unless the input is NaN.
     *
     * @param a acos is defined only when a is between -1f and 1f, inclusive
     * @return between {@code 0} and {@code 0.5} when a is in the defined range
     */
    public static float acosTurns(float a) {
        if (a >= 0f) {
            return (float)(Math.sqrt(1.0 - a) * (0.24998925277680104 + a * (-0.033759055260971525 + a * (0.011819005228947238 + a * -0.0029808606756510357))));
        }
        return (float)(0.5 - Math.sqrt(1.0 + a) * (0.24998925277680104 + a * (0.033759055260971525 + a * (0.011819005228947238 + a * 0.0029808606756510357))));
    }
//</editor-fold>
}
