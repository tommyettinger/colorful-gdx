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
 * {@link #sin(float)}, {@link #cos(float)}, {@link #sinSmoother(float)}, {@link #cosSmoother(float)}, and
 * {@link #tanSmoother(float)} use the LUTs. Other methods here use techniques ranging from basic Taylor series to Padé
 * approximants. The lookup-table-based sin() and cos() can be extraordinarily fast if the 64KB table can stay in a
 * processor cache, while the "smooth" approximations may have decent quality but perform less quickly compared to an
 * in-cache lookup table. Also available now are "precise" versions of all methods here, from {@link #sinPrecise(float)}
 * to {@link #atan2Deg360Precise(double, double)}. The "precise" versions all avoid a lookup table entirely and tend to
 * be the most precise approximations here, while still outperforming {@link Math} versions of their function. In a few
 * cases, the "precise" versions are also the fastest; this occurs most often for tan variants, or situations
 * where a large lookup table is not possible for the processor to cache.
 * <br>
 * Relative to MathUtils in libGDX, the main new functionalities are the variants that take or
 * return measurements in turns, the now-available {@link #SIN_TABLE}, {@link #COS_TABLE}, {@link #SIN_TABLE_D}, and
 * {@link #COS_TABLE_D}, plus double variants in general. Using the sin or cos table directly has other uses mentioned
 * in its docs (in particular, uniform random unit vectors).
 * Because using a lookup table for {@link #sin(float)} and {@link #cos(float)} has very small "jumps" between
 * what it returns for smoothly increasing inputs, it may be unsuitable for some usage, such as calculating tan(), or
 * some statistical code. TrigTools provides sinSmooth(), cosSmooth(), and degree/turn variants of those for when the
 * precision should be moderately high, but it is most important to have a smoothly-curving graph of returns. A
 * different smooth approximation is used for tan(), though it is not very precise or fast compared to
 * {@link #tanSmoother(float)}, and is much less precise than the also-non-tabular {@link #tanPrecise(float)}. In
 * addition to the "xyzSmooth()" methods, there are also "smoother" variants: {@link #sinSmoother(float)},
 * {@link #cosSmoother(float)}, {@link #tanSmoother(float)}, degree/turn variants on those, and double variants on all
 * of these. {@link #sinSmoother(float)} and {@link #cosSmoother(float)} get the table indices for rounding up from the
 * given angle and for rounding down, and interpolate between the two in {@link #SIN_TABLE} (or {@link #COS_TABLE}).
 * Unlike {@link #sinSmoother(float)} and friends, {@link #tanSmoother(float)} uses both {@link #SIN_TABLE} and
 * {@link #COS_TABLE}, does interpolation like what {@link #sinSmoother(float)} does for both sine and cosine, and
 * divides to get the tangent. The "smoother" methods are all quite precise compared to the others here, and aren't
 * necessarily slower than the "smooth" methods -- see below. Nothing here beats the "precise" methods on accuracy; they
 * often have single-digit ULP differences <em>in their worst cases</em> from what Math returns. These methods tend to
 * be slower than other approximations here but are still always faster than Math's methods (on at least Java 23).
 * <br>
 * For sine and cosine, {@link #sin(float)} and {@link #cos(float)} are extremely fast in benchmarks, but benchmarks
 * typically will have the {@link #COS_TABLE} in cache; if that table is not in cache, then they probably don't perform
 * as well. You can get somewhat improved accuracy at the cost of reduced speed ("reduced" assumes the table is
 * in-cache) by using {@link #sinSmooth(float)} and {@link #cosSmooth(float)}; these should perform the same regardless
 * of whether the table is in cache, so they may even have an edge over sin() and cos() if the table isn't.
 * Accuracy improves even further if you use {@link #sinSmoother(float)} and {@link #cosSmoother(float)}, but these are
 * definitely slower than TrigTools' sin() and cos(), since they do more work. Benchmarks conflicted regarding
 * whether sinSmooth() or sinSmoother() is faster; JMH benchmarks showed sinSmooth() as always being faster, while
 * BumbleBench benchmarks showed sinSmoother() as always being faster. In both cases, the speed difference was small.
 * Like the "smooth" sin and cos, {@link #sinPrecise(float)} and {@link #cosPrecise(float)} are non-tabular, but are
 * more precise instead of less precise than {@link #sinSmoother(float)} and {@link #cosSmoother(float)}. They are
 * likely to perform slightly worse than the other variants but still better than Math's versions. Because the
 * "precise" methods don't use any platform-dependent methods to make their calculations, they are used to calculate the
 * sin and cosine tables, ensuring that the tables are identical on all platforms, except potentially browsers
 * translating Java to JavaScript (because floats may be implemented with doubles there).
 * <br>
 * For calculating tangent, {@link #tan(float)} is somewhat faster on Java 8 (using HotSpot) and some OpenJ9 versions,
 * but {@link #tanSmoother(float)} is faster on Java 11 and up, significantly so on Java 16 and up. However, tan() does
 * not use {@link #SIN_TABLE}, while tanSmoother() does, and this may be relevant if the table is not in-cache. The
 * "precise" version, {@link #tanPrecise(float)}, is non-tabular like {@link #tan(float)}, and in cases where the lookup
 * tables are probably in processor cache, tanPrecise() takes about twice the time as tanSmoother() but has half the
 * mean and worst-case absolute error (which is good) and has better mean relative error as well.
 * <br>
 * There are inverse trigonometric methods here, too. {@link #asin(float)} and {@link #acos(float)} return results in
 * radians, and are accompanied by {@link #asinDeg(float)} for results in degrees, {@link #asinTurns(float)} for results
 * in turns, and acos versions of those as well. Like everything else here, there are "precise" variants on these, but
 * {@link #acosPrecise(float)} isn't quite as accurate as the other "precise" methods. All the "precise" asin and acos
 * methods still have substantially better absolute and relative error than the non-precise versions.
 * <br>
 * We can't forget about {@link #atan(float)}, of course, nor {@link #atan2(float, float)}! There are some more options
 * for these arctangent-related methods. {@link #atanUnchecked(double)} is used primarily by
 * {@link #atan2(float, float)} and can also be used when you are certain your inputs will be defined for the arctangent
 * function (that is, not an odd multiple of {@code 0.5 * Math.PI} for radians, or 90 degrees, or 0.25 turns). It is
 * similar to {@link #atanPrecise(float)} in that both don't return something meaningful when given an input that is out
 * of the domain for arctangent, but {@link #atan(float)} at least tries to give a better output, with dubious results.
 * {@link #atan2Deg360(float, float)} and its "precise" counterpart act like atan2 with results in degrees, but return
 * outputs in the range {@code [0, 360)} rather than the range of  {@link #atan2Deg(float, float)}, which is
 * {@code (-180,180]}. {@link #atan2Turns(float, float)} and its "precise" variant return results in the {@code [0, 1)}
 * range of turns, which can be often useful for angular values like the hue of a color when they must be in the 0-1
 * range, such as for passing to shaders. There are also {@link #atan2Finite(float, float)} and other "Finite"
 * variations on the existing methods; these approximations are faster and more accurate than the
 * {@link #atan2(float, float)} family but less accurate than the {@link #atan2Precise(float, float)} family, and are
 * only well-defined for finite y and x arguments (no infinite arguments and no NaN). If you know you won't be using any
 * infinite point coordinates, atan2Finite() is the best option here unless you need the extreme precision (at some cost
 * to speed) from atan2Precise().
 * <br>
 * In some cases, {@link #atan2Precise(float, float)} can be faster than the "non-precise, non-finite" variant, but this
 * depends quite a bit on your circumstances. All atan2() code here avoids lookup tables, but the "precise" and "finite"
 * versions also avoid casting between float and double. This speed advantage is countered by the branching the
 * "precise" version does; if branch prediction succeeds for a long span of calls and then suddenly starts mispredicting
 * often, then HotSpot may deoptimize parts of atan2Precise(), making it much slower. This is also possible for atan2()
 * and atan2Finite(), but is harder to cause there.
 * <br>
 * In the common case where you have an angle and want to get both the sin() and cos() of that angle, you can use the
 * {@link #radiansToTableIndex(float)}, {@link #degreesToTableIndex(float)}, and/or {@link #turnsToTableIndex(float)}
 * methods to go from an angle (in radians, degrees, or turns, as appropriate) to the index in
 * {@link #SIN_TABLE the sine table} (or {@link #SIN_TABLE_D the sine table for doubles}) that corresponds to the result
 * of sin(). That index can be used both to look up the sine, with {@code SIN_TABLE[radiansToTableIndex(angle)]}, and
 * the cosine, with {@code COS_TABLE[radiansToTableIndex(angle)]}. Unlike in the example snippets, you should usually
 * just call radiansToTableIndex() once and use its result in both places.
 * <br>
 * Because tracking which methods to use when isn't exactly straightforward, here's a table for which versions are the
 * best at which things. Highest Speed has the fastest function, regardless of quality. Highest Quality is always a
 * "Precise" method, which are faster than {@link Math} functions but not 100% perfect quality. Compromise is a guess
 * at which method offers the best mix of both quality and speed, when both are needed.
 * <table>
 *     <tr><th>Trigonometry Function</th><th>Highest Speed</th><th>Highest Quality</th><th>Compromise</th></tr>
 *     <tr><td>sin()</td><td>{@link #sin(float)}</td><td>{@link #sinPrecise(float)}</td><td>{@link #sinSmoother(float)}</td></tr>
 *     <tr><td>cos()</td><td>{@link #cos(float)}</td><td>{@link #cosPrecise(float)}</td><td>{@link #cosSmoother(float)}</td></tr>
 *     <tr><td>tan()</td><td>{@link #tanSmoother(float)}</td><td>{@link #tanPrecise(float)}</td><td>{@link #tanSmoother(float)}</td></tr>
 *     <tr><td>asin()</td><td>{@link #asin(float)}</td><td>{@link #asinPrecise(float)}</td><td>{@link #asin(float)}</td></tr>
 *     <tr><td>acos()</td><td>{@link #acos(float)}</td><td>{@link #acosPrecise(float)}</td><td>{@link #acos(float)}</td></tr>
 *     <tr><td>atan()</td><td>{@link #atan(float)}</td><td>{@link #atanPrecise(float)}</td><td>{@link #atan(float)}</td></tr>
 *     <tr><td>atan2()</td><td>{@link #atan2Finite(float, float)}</td><td>{@link #atan2Precise(float, float)}</td><td>{@link #atan2Finite(float, float)}</td></tr>
 * </table>
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
 * method used here. The technique in the "Smoother" methods is not much different from the typical lookup table used by
 * sin() and cos(); it just linear-interpolates between two adjacent table entries. The main difference between
 * "Smoother" and the standard approximations is that the "Smoother" ones use both the floor and the ceiling of a float
 * to get indices, while the standard approximations essentially round to the nearest index. The current technique of
 * looking up in a cosine table so that we can use the absolute value of an angle (since cos(x) is the same as cos(-x))
 * is something I hadn't seen before, but I'm sure is not novel. The atan2Finite() family of methods is based on the
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
     * The {@code double} value that is closer than any other to
     * <i>pi</i>, the ratio of the circumference of a circle to its
     * diameter.
     */
    public static final double PI_D = Math.PI;
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
     * 1.0 divided by {@link #PI}.
     */
    public static final double PI_INVERSE_D = (1.0 / Math.PI);
    /**
     * 2.0 times {@link Math#PI}; the same as {@link #TAU_D}.
     */
    public static final double PI2_D = Math.PI * 2.0;
    /**
     * 2.0 times {@link Math#PI}; the same as {@link #PI2_D}.
     */
    public static final double TAU_D = PI2_D;
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
     * The hard-coded size of {@link #SIN_TABLE}, {@link #COS_TABLE}, {@link #SIN_TABLE_D}, and {@link #COS_TABLE_D} in
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
     * Multiply by this to convert from a double angle in radians to an index in {@link #SIN_TABLE} (after it is rounded to int and masked with {@link #TABLE_MASK}).
     */
    public static final double radToIndexD = TABLE_SIZE / PI2_D;
    /**
     * Multiply by this to convert from a double angle in degrees to an index in {@link #SIN_TABLE} (after it is rounded to int and masked with {@link #TABLE_MASK}).
     */
    public static final double degToIndexD = TABLE_SIZE / 360.0;
    /**
     * Multiply by this to convert from a double angle in turns to an index in {@link #SIN_TABLE} (after it is rounded to int and masked with {@link #TABLE_MASK}).
     */
    public static final double turnToIndexD = TABLE_SIZE;

    /**
     * Multiply by this to convert from radians to degrees.
     */
    public static final double radiansToDegreesD = 180.0 / Math.PI;
    /**
     * Multiply by this to convert from degrees to radians.
     */
    public static final double degreesToRadiansD = Math.PI / 180.0;

    /**
     * Multiply by this to convert from radians to degrees.
     */
    public static final float radiansToDegrees = (float) radiansToDegreesD;
    /**
     * Multiply by this to convert from degrees to radians.
     */
    public static final float degreesToRadians = (float) degreesToRadiansD;

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
     * A precalculated table of 16385 doubles, corresponding to the y-value of points on the unit circle, ordered by
     * increasing angle. This should not be mutated, but it can be accessed directly for things like getting random
     * unit vectors, or implementing the "sincos" method (which assigns sin() to one item and cos() to another).
     * <br>
     * A quick way to get a random unit vector is to get a random number that can be no larger than the table size, as
     * with {@code int angle = (random.nextInt() & TrigTools.TABLE_MASK);}, and look up that angle in
     * {@code COS_TABLE_D} for the vector's x and {@code SIN_TABLE_D} for the vector's y.
     * Elements 0 and 16384 are identical to allow wrapping.
     */
    public static final double[] SIN_TABLE_D = new double[TABLE_SIZE+1];

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

    /**
     * A precalculated table of 16385 doubles, corresponding to the x-value of points on the unit circle, ordered by
     * increasing angle. This should not be mutated, but it can be accessed directly for things like getting random
     * unit vectors, or implementing the "sincos" method (which assigns sin() to one item and cos() to another).
     * <br>
     * This is calculated without using {@link Math#sin(double)} or {@link Math#cos(double)} in order to avoid any
     * architecture-dependent behavior of special functions. Specifically, it uses the (very precise, but not 100%)
     * approximations {@link #sinPrecise(double)} and {@link #cosPrecise(double)}.
     * <br>
     * A quick way to get a random unit vector is to get a random number that can be no larger than the table size, as
     * with {@code int angle = (random.nextInt() & TrigTools.TABLE_MASK);}, and look up that angle in
     * {@code COS_TABLE_D} for the vector's x and {@code SIN_TABLE_D} for the vector's y.
     * Elements 0 and 16384 are identical to allow wrapping.
     */
    public static final double[] COS_TABLE_D = new double[TABLE_SIZE+1];

    static {
        for (int i = 0; i <= TABLE_SIZE; i++) {
            double theta = ((double)i) / TABLE_SIZE * PI2_D;
            SIN_TABLE[i] = (float) (SIN_TABLE_D[i] = sinPrecise(theta));
            COS_TABLE[i] = (float) (COS_TABLE_D[i] = cosPrecise(theta));
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
        debugD = new double[]{
        SIN_TABLE_D[0]                       ,
        SIN_TABLE_D[QUARTER_CIRCLE_INDEX]    ,
        SIN_TABLE_D[QUARTER_CIRCLE_INDEX * 2],
        SIN_TABLE_D[QUARTER_CIRCLE_INDEX * 3],
        SIN_TABLE_D[QUARTER_CIRCLE_INDEX * 4],
        };
        for(double f : debugD) System.out.printf("%f %016X\n", f, NumberUtils.doubleToLongBits(f));
        debug = new float[]{
        COS_TABLE[0]                       ,
        COS_TABLE[QUARTER_CIRCLE_INDEX]    ,
        COS_TABLE[QUARTER_CIRCLE_INDEX * 2],
        COS_TABLE[QUARTER_CIRCLE_INDEX * 3],
        COS_TABLE[QUARTER_CIRCLE_INDEX * 4],
        };
        for(float f : debug) System.out.printf("%f %08X\n", f, NumberUtils.floatToIntBits(f));
        debugD = new double[]{
        COS_TABLE_D[0]                       ,
        COS_TABLE_D[QUARTER_CIRCLE_INDEX]    ,
        COS_TABLE_D[QUARTER_CIRCLE_INDEX * 2],
        COS_TABLE_D[QUARTER_CIRCLE_INDEX * 3],
        COS_TABLE_D[QUARTER_CIRCLE_INDEX * 4],
        };
        for(double f : debugD) System.out.printf("%f %016X\n", f, NumberUtils.doubleToLongBits(f));
*/
        // The four right angles get extra-precise values, because they are
        // the most likely to need to be correct.

        SIN_TABLE[0]                          = 0f;
        SIN_TABLE[QUARTER_CIRCLE_INDEX]       = 1f;
        SIN_TABLE[QUARTER_CIRCLE_INDEX * 2]   = 0f;
        SIN_TABLE[QUARTER_CIRCLE_INDEX * 3]   = -1.0f;
        SIN_TABLE[QUARTER_CIRCLE_INDEX * 4]   = 0f;
        SIN_TABLE_D[0]                        = 0.0;
        SIN_TABLE_D[QUARTER_CIRCLE_INDEX]     = 1.0;
        SIN_TABLE_D[QUARTER_CIRCLE_INDEX * 2] = 0.0;
        SIN_TABLE_D[QUARTER_CIRCLE_INDEX * 3] = -1.0;
        SIN_TABLE_D[QUARTER_CIRCLE_INDEX * 4] = 0.0;

        COS_TABLE[0]                          = 1f;
        COS_TABLE[QUARTER_CIRCLE_INDEX]       = 0f;
        COS_TABLE[QUARTER_CIRCLE_INDEX * 2]   = -1f;
        COS_TABLE[QUARTER_CIRCLE_INDEX * 3]   = 0f;
        COS_TABLE[QUARTER_CIRCLE_INDEX * 4]   = 1f;
        COS_TABLE_D[0]                        = 1.0;
        COS_TABLE_D[QUARTER_CIRCLE_INDEX]     = 0.0;
        COS_TABLE_D[QUARTER_CIRCLE_INDEX * 2] = -1.0;
        COS_TABLE_D[QUARTER_CIRCLE_INDEX * 3] = 0.0;
        COS_TABLE_D[QUARTER_CIRCLE_INDEX * 4] = 1.0;
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Table Queries">
    /**
     * Converts {@code radians} to an index that can be used in {@link #SIN_TABLE}, {@link #COS_TABLE}, or the _D
     * variants on either to obtain the sine or cosine of the given angle. This method can be useful if you have
     * one angle and want to get both the sine and cosine of that angle (called the "sincos()" function elsewhere).
     * This tries to round the given angle to the nearest table index.
     *
     * @param radians an angle in radians; may be positive or negative
     * @return the index into {@link #SIN_TABLE} or {@link #SIN_TABLE_D} of the sine of radians
     */
    public static int radiansToTableIndex(final float radians) {
        return (int) (radians * radToIndex + 16384.5f) & TABLE_MASK;
    }

    /**
     * Converts {@code degrees} to an index that can be used in {@link #SIN_TABLE}, {@link #COS_TABLE}, or the _D
     * variants on either to obtain the sine or cosine of the given angle. This method can be useful if you have
     * one angle and want to get both the sine and cosine of that angle (called the "sincos()" function elsewhere).
     * This tries to round the given angle to the nearest table index.
     *
     * @param degrees an angle in degrees; may be positive or negative
     * @return the index into {@link #SIN_TABLE} or {@link #SIN_TABLE_D} of the sine of degrees
     */
    public static int degreesToTableIndex(final float degrees) {
        return (int)(degrees * degToIndex + 16384.5f) & TABLE_MASK;
    }

    /**
     * Converts {@code turns} to an index that can be used in {@link #SIN_TABLE}, {@link #COS_TABLE}, or the _D
     * variants on either to obtain the sine or cosine of the given angle. This method can be useful if you have
     * one angle and want to get both the sine and cosine of that angle (called the "sincos()" function elsewhere).
     * This tries to round the given angle to the nearest table index.
     *
     * @param turns an angle in turns; may be positive or negative
     * @return the index into {@link #SIN_TABLE} or {@link #SIN_TABLE_D} of the sine of turns
     */
    public static int turnsToTableIndex(final float turns) {
        return (int)(turns * turnToIndex + 16384.5f) & TABLE_MASK;
    }

    /**
     * Converts {@code radians} to an index that can be used in {@link #SIN_TABLE}, {@link #COS_TABLE}, or the _D
     * variants on either to obtain the sine or cosine of the given angle. This method can be useful if you have
     * one angle and want to get both the sine and cosine of that angle (called the "sincos()" function elsewhere).
     * This tries to round the given angle to the nearest table index.
     *
     * @param radians an angle in radians; may be positive or negative
     * @return the index into {@link #SIN_TABLE} or {@link #SIN_TABLE_D} of the sine of radians
     */
    public static int radiansToTableIndex(final double radians) {
        return (int) (radians * radToIndexD + 16384.5) & TABLE_MASK;
    }

    /**
     * Converts {@code degrees} to an index that can be used in {@link #SIN_TABLE}, {@link #COS_TABLE}, or the _D
     * variants on either to obtain the sine or cosine of the given angle. This method can be useful if you have
     * one angle and want to get both the sine and cosine of that angle (called the "sincos()" function elsewhere).
     * This tries to round the given angle to the nearest table index.
     *
     * @param degrees an angle in degrees; may be positive or negative
     * @return the index into {@link #SIN_TABLE} or {@link #SIN_TABLE_D} of the sine of degrees
     */
    public static int degreesToTableIndex(final double degrees) {
        return (int)(degrees * degToIndexD + 16384.5) & TABLE_MASK;
    }

    /**
     * Converts {@code turns} to an index that can be used in {@link #SIN_TABLE}, {@link #COS_TABLE}, or the _D
     * variants on either to obtain the sine or cosine of the given angle. This method can be useful if you have
     * one angle and want to get both the sine and cosine of that angle (called the "sincos()" function elsewhere).
     * This tries to round the given angle to the nearest table index.
     *
     * @param turns an angle in turns; may be positive or negative
     * @return the index into {@link #SIN_TABLE} or {@link #SIN_TABLE_D} of the sine of turns
     */
    public static int turnsToTableIndex(final double turns) {
        return (int)(turns * turnToIndexD + 16384.5) & TABLE_MASK;
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Sine, Cosine, Tangent for Floats">
    /**
     * Returns the sine in radians from a lookup table. For optimal precision, use radians between -PI2 and PI2 (both
     * inclusive).
     * <br>
     * This approximation may have visible "steps" where it should be smooth, but this is generally only noticeable when
     * you need very fine detail. The steps occur because it converts its argument from radians to an array index in a
     * {@link #TABLE_SIZE}-item array, and truncates some of the least-significant digits to do so if necessary. You can
     * use {@link #sinSmoother(float)} if you need better accuracy; it uses the least-significant digits to smoothly
     * interpolate between two items in the array.
     *
     * @param radians an angle in radians, where 0 to {@link #PI2} is one rotation
     * @return the sine of the given angle, between -1 and 1 inclusive
     */
    public static float sin(final float radians) {
        return SIN_TABLE[(int) (radians * radToIndex + 16384.5f) & TABLE_MASK];
    }

    /**
     * Returns the cosine in radians from a lookup table. For optimal precision, use radians between -PI2 and PI2 (both
     * inclusive).
     * <br>
     * This approximation may have visible "steps" where it should be smooth, but this is generally only noticeable when
     * you need very fine detail. The steps occur because it converts its argument from radians to an array index in a
     * {@link #TABLE_SIZE}-item array, and truncates some of the least-significant digits to do so if necessary. You can
     * use {@link #cosSmoother(float)} if you need better accuracy; it uses the least-significant digits to smoothly
     * interpolate between two items in the array.
     *
     * @param radians an angle in radians, where 0 to {@link #PI2} is one rotation
     * @return the cosine of the given angle, between -1 and 1 inclusive
     */
    public static float cos(final float radians) {
        return COS_TABLE[(int) (radians * radToIndex + 16384.5f) & TABLE_MASK];
    }

    /**
     * Returns the tangent in radians, using a Padé approximant.
     * Padé approximants tend to be most accurate when they aren't producing results of extreme magnitude; in the tan()
     * function, those results occur on and near odd multiples of {@code PI/2}, and this method is least accurate when
     * given inputs near those multiples.
     * <br> For inputs between -1.57 to 1.57 (just inside half-pi), separated by 0x1p-20f,
     * absolute error is 0.00890192, relative error is 0.00000090, and the maximum error is 17.98901367 when given
     * 1.56999838. The maximum error might seem concerning, but it's the difference between the correct 1253.22167969
     * and the 1235.23266602 this returns, so for many purposes the difference won't be noticeable.
     * <br> For inputs between -1.55 to 1.55 (getting less close to half-pi), separated by 0x1p-20f, absolute error is
     * 0.00023368, relative error is -0.00000009, and the maximum error is 0.02355957 when given -1.54996467. The
     * maximum error is the difference between the correct -47.99691010 and the -47.97335052 this returns.
     * <br> While you don't have to use a dedicated method for tan(), and you can use {@code sin(x)/cos(x)},
     * approximating tan() in this way is very susceptible to error building up from any of sin(), cos() or the
     * division. Where this tan() has a maximum error in the -1.55 to 1.55 range of 0.02355957, the simpler division
     * technique on the same range has a maximum error of 1.25724030 (about 50 times worse), as well as larger absolute
     * and relative errors. Casting the double result of {@link Math#tan(double)} to float will get the highest
     * precision, but can be anywhere from 2.5x to nearly 4x slower than this, depending on JVM.
     * <br>
     * Based on <a href="https://math.stackexchange.com/a/4453027">this Stack Exchange answer by Soonts</a>.
     * <br>
     * If you know you target newer JDK versions only, or you need higher precision, you should consider using
     * {@link #tanSmoother(float)} instead. Compared to this method, tanSmoother() is slightly faster on recent
     * JDKs, and is significantly more precise.
     *
     * @param radians a float angle in radians, where 0 to {@link #PI2} is one rotation
     * @return a float approximation of tan()
     */
    public static float tan(float radians) {
        radians *= TrigTools.PI_INVERSE;
        radians += 0.5f;
        radians -= (int)(radians + 16384.0) - 16384;
        radians -= 0.5f;
        radians *= TrigTools.PI;
        final float x2 = radians * radians, x4 = x2 * x2;
        return radians * ((0.0010582010582010583f) * x4 - (0.1111111111111111f) * x2 + 1f)
                / ((0.015873015873015872f) * x4 - (0.4444444444444444f) * x2 + 1f);
        // How we calculated those long constants above (from Stack Exchange, by Soonts):
//        return x * ((1.0/945.0) * x4 - (1.0/9.0) * x2 + 1.0) / ((1.0/63.0) * x4 - (4.0/9.0) * x2 + 1.0);
        // Normally, it would be best to show the division steps, but if GWT isn't computing mathematical constants at
        // compile-time, which I don't know if it does, that would make the shown-division way slower by 4 divisions.
    }

    /**
     * Returns the sine in degrees from a lookup table. For optimal precision, use degrees between -360 and 360 (both
     * inclusive).
     * <br>
     * This approximation may have visible "steps" where it should be smooth, but this is generally only noticeable when
     * you need very fine detail. The steps occur because it converts its argument from degrees to an array index in a
     * {@link #TABLE_SIZE}-item array, and truncates some of the least-significant digits to do so if necessary. You can
     * use {@link #sinSmootherDeg(float)} if you need better accuracy; it uses the least-significant digits to smoothly
     * interpolate between two items in the array.
     *
     * @param degrees an angle in degrees, where 0 to 360 is one rotation
     * @return the sine of the given angle, between -1 and 1 inclusive
     */
    public static float sinDeg(final float degrees) {
        return SIN_TABLE[(int) (degrees * degToIndex + 16384.5f) & TABLE_MASK];
    }

    /**
     * Returns the cosine in degrees from a lookup table. For optimal precision, use degrees between -360 and 360 (both
     * inclusive).
     * <br>
     * This approximation may have visible "steps" where it should be smooth, but this is generally only noticeable when
     * you need very fine detail. The steps occur because it converts its argument from degrees to an array index in a
     * {@link #TABLE_SIZE}-item array, and truncates some of the least-significant digits to do so if necessary. You can
     * use {@link #cosSmootherDeg(float)} if you need better accuracy; it uses the least-significant digits to smoothly
     * interpolate between two items in the array.
     *
     * @param degrees an angle in degrees, where 0 to 360 is one rotation
     * @return the cosine of the given angle, between -1 and 1 inclusive
     */
    public static float cosDeg(final float degrees) {
        return COS_TABLE[(int) (degrees * degToIndex + 16384.5f) & TABLE_MASK];
    }

    /**
     * Returns the tangent in degrees, using a Padé approximant.
     * Based on <a href="https://math.stackexchange.com/a/4453027">this Stack Exchange answer</a>.
     * <br>
     * If you know you target newer JDK versions only, or you need higher precision, you should consider using
     * {@link #tanSmootherDeg(float)} instead. Compared to this method, tanSmootherDeg() is slightly faster on recent
     * JDKs, and is significantly more precise.
     *
     * @param degrees an angle in degrees, where 0 to 360 is one rotation
     * @return a float approximation of tan()
     */
    public static float tanDeg(float degrees) {
        degrees *= 1f/180f;
        degrees += 0.5f;
        degrees -= (int)(degrees + 16384.0) - 16384;
        degrees -= 0.5f;
        degrees *= TrigTools.PI;
        final float x2 = degrees * degrees, x4 = x2 * x2;
        return degrees * ((0.0010582010582010583f) * x4 - (0.1111111111111111f) * x2 + 1f)
                / ((0.015873015873015872f) * x4 - (0.4444444444444444f) * x2 + 1f);
    }

    /**
     * Returns the sine in turns from a lookup table. For optimal precision, use turns between -1 and 1 (both
     * inclusive).
     * <br>
     * This approximation may have visible "steps" where it should be smooth, but this is generally only noticeable when
     * you need very fine detail. The steps occur because it converts its argument from turns to an array index in a
     * {@link #TABLE_SIZE}-item array, and truncates some of the least-significant digits to do so if necessary. You can
     * use {@link #sinSmootherTurns(float)} if you need better accuracy; it uses the least-significant digits to
     * smoothly interpolate between two items in the array.
     *
     * @param turns an angle in turns, where 0 to 1 is one rotation
     * @return the sine of the given angle, between -1 and 1 inclusive
     */
    public static float sinTurns(final float turns) {
        return SIN_TABLE[(int) (turns * turnToIndex + 16384.5f) & TABLE_MASK];
    }

    /**
     * Returns the cosine in turns from a lookup table. For optimal precision, use turns between -1 and 1 (both
     * inclusive).
     * <br>
     * This approximation may have visible "steps" where it should be smooth, but this is generally only noticeable when
     * you need very fine detail. The steps occur because it converts its argument from turns to an array index in a
     * {@link #TABLE_SIZE}-item array, and truncates some of the least-significant digits to do so if necessary. You can
     * use {@link #cosSmootherTurns(float)} if you need better accuracy; it uses the least-significant digits to
     * smoothly interpolate between two items in the array.
     *
     * @param turns an angle in turns, where 0 to 1 is one rotation
     * @return the cosine of the given angle, between -1 and 1 inclusive
     */
    public static float cosTurns(final float turns) {
        return COS_TABLE[(int) (turns * turnToIndex + 16384.5f) & TABLE_MASK];
    }

    /**
     * Returns the tangent in turns, using a Padé approximant.
     * Based on <a href="https://math.stackexchange.com/a/4453027">this Stack Exchange answer</a>.
     * <br>
     * If you know you target newer JDK versions only, or you need higher precision, you should consider using
     * {@link #tanSmootherTurns(float)} instead. Compared to this method, tanSmootherTurns() is slightly faster on
     * recent JDKs, and is significantly more precise.
     *
     * @param turns an angle in turns, where 0 to 1 is one rotation
     * @return a float approximation of tan()
     */
    public static float tanTurns(float turns) {
        turns += turns;
        turns += 0.5f;
        turns -= (int)(turns + 16384.0) - 16384;
        turns -= 0.5f;
        turns *= TrigTools.PI;
        final float x2 = turns * turns, x4 = x2 * x2;
        return turns * ((0.0010582010582010583f) * x4 - (0.1111111111111111f) * x2 + 1f)
                / ((0.015873015873015872f) * x4 - (0.4444444444444444f) * x2 + 1f);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Sine, Cosine, Tangent for Doubles">

    /**
     * Returns the sine in radians from a lookup table. For optimal precision, use radians between -PI2 and PI2 (both
     * inclusive).
     * <br>
     * This approximation may have visible "steps" where it should be smooth, but this is generally only noticeable when
     * you need very fine detail. The steps occur because it converts its argument from radians to an array index in a
     * {@link #TABLE_SIZE}-item array, and truncates some of the least-significant digits to do so if necessary. You can
     * use {@link #sinSmoother(double)} if you need better accuracy; it uses the least-significant digits to smoothly
     * interpolate between two items in the array.
     *
     * @param radians an angle in radians, where 0 to {@link #PI2_D} is one rotation
     * @return the sine of the given angle, between -1 and 1 inclusive
     */
    public static double sin(final double radians) {
        return SIN_TABLE_D[(int) (radians * radToIndexD + 16384.5) & TABLE_MASK];
    }

    /**
     * Returns the cosine in radians from a lookup table. For optimal precision, use radians between -PI2 and PI2 (both
     * inclusive).
     * <br>
     * This approximation may have visible "steps" where it should be smooth, but this is generally only noticeable when
     * you need very fine detail. The steps occur because it converts its argument from radians to an array index in a
     * {@link #TABLE_SIZE}-item array, and truncates some of the least-significant digits to do so if necessary. You can
     * use {@link #cosSmoother(double)} if you need better accuracy; it uses the least-significant digits to smoothly
     * interpolate between two items in the array.
     *
     * @param radians an angle in radians, where 0 to {@link #PI2_D} is one rotation
     * @return the cosine of the given angle, between -1 and 1 inclusive
     */
    public static double cos(final double radians) {
        return COS_TABLE_D[(int) (radians * radToIndexD + 16384.5) & TABLE_MASK];
    }

    /**
     * Returns the tangent in radians, using a Padé approximant.
     * Based on <a href="https://math.stackexchange.com/a/4453027">this Stack Exchange answer</a>.
     * <br>
     * If you know you target newer JDK versions only, or you need higher precision, you should consider using
     * {@link #tanSmoother(double)} instead. Compared to this method, tanSmoother() is slightly faster on recent
     * JDKs, and is significantly more precise.
     *
     * @param radians a double angle in radians, where 0 to {@link #PI2} is one rotation
     * @return a double approximation of tan()
     */
    public static double tan(double radians) {
        radians *= TrigTools.PI_INVERSE_D;
        radians += 0.5;
        radians -= Math.floor(radians);
        radians -= 0.5;
        radians *= TrigTools.PI_D;
        final double x2 = radians * radians, x4 = x2 * x2;
        return radians * ((0.0010582010582010583) * x4 - (0.1111111111111111) * x2 + 1.0)
                / ((0.015873015873015872) * x4 - (0.4444444444444444) * x2 + 1.0);
        // how we calculated those large constants above:
//        return x * ((1.0/945.0) * x4 - (1.0/9.0) * x2 + 1.0) / ((1.0/63.0) * x4 - (4.0/9.0) * x2 + 1.0);
    }

    /**
     * Returns the sine in degrees from a lookup table. For optimal precision, use degrees between -360 and 360 (both
     * inclusive).
     * <br>
     * This approximation may have visible "steps" where it should be smooth, but this is generally only noticeable when
     * you need very fine detail. The steps occur because it converts its argument from degrees to an array index in a
     * {@link #TABLE_SIZE}-item array, and truncates some of the least-significant digits to do so if necessary. You can
     * use {@link #sinSmootherDeg(double)} if you need better accuracy; it uses the least-significant digits to smoothly
     * interpolate between two items in the array.
     *
     * @param degrees an angle in degrees, where 0 to 360 is one rotation
     * @return the sine of the given angle, between -1 and 1 inclusive
     */
    public static double sinDeg(final double degrees) {
        return SIN_TABLE_D[(int) (degrees * degToIndexD + 16384.5) & TABLE_MASK];
    }

    /**
     * Returns the cosine in degrees from a lookup table. For optimal precision, use degrees between -360 and 360 (both
     * inclusive).
     * <br>
     * This approximation may have visible "steps" where it should be smooth, but this is generally only noticeable when
     * you need very fine detail. The steps occur because it converts its argument from degrees to an array index in a
     * {@link #TABLE_SIZE}-item array, and truncates some of the least-significant digits to do so if necessary. You can
     * use {@link #cosSmootherDeg(double)} if you need better accuracy; it uses the least-significant digits to smoothly
     * interpolate between two items in the array.
     *
     * @param degrees an angle in degrees, where 0 to 360 is one rotation
     * @return the cosine of the given angle, between -1 and 1 inclusive
     */
    public static double cosDeg(final double degrees) {
        return COS_TABLE_D[(int) (degrees * degToIndexD + 16384.5) & TABLE_MASK];
    }

    /**
     * Returns the tangent in degrees, using a Padé approximant.
     * Based on <a href="https://math.stackexchange.com/a/4453027">this Stack Exchange answer</a>.
     * <br>
     * If you know you target newer JDK versions only, or you need higher precision, you should consider using
     * {@link #tanSmootherDeg(double)} instead. Compared to this method, tanSmootherDeg() is slightly faster on recent
     * JDKs, and is significantly more precise.
     *
     * @param degrees an angle in degrees, where 0 to 360 is one rotation
     * @return a double approximation of tan()
     */
    public static double tanDeg(double degrees) {
        degrees *= 1.0/180.0;
        degrees += 0.5;
        degrees -= Math.floor(degrees);
        degrees -= 0.5;
        degrees *= TrigTools.PI_D;
        final double x2 = degrees * degrees, x4 = x2 * x2;
        return degrees * ((0.0010582010582010583) * x4 - (0.1111111111111111) * x2 + 1.0)
                / ((0.015873015873015872) * x4 - (0.4444444444444444) * x2 + 1.0);
    }

    /**
     * Returns the sine in turns from a lookup table. For optimal precision, use turns between -1 and 1 (both
     * inclusive).
     * <br>
     * This approximation may have visible "steps" where it should be smooth, but this is generally only noticeable when
     * you need very fine detail. The steps occur because it converts its argument from turns to an array index in a
     * {@link #TABLE_SIZE}-item array, and truncates some of the least-significant digits to do so if necessary. You can
     * use {@link #sinSmootherTurns(double)} if you need better accuracy; it uses the least-significant digits to smoothly
     * interpolate between two items in the array.
     *
     * @param turns an angle in turns, where 0 to 1 is one rotation
     * @return the sine of the given angle, between -1 and 1 inclusive
     */
    public static double sinTurns(final double turns) {
        return SIN_TABLE_D[(int) (turns * turnToIndexD + 16384.5) & TABLE_MASK];
    }

    /**
     * Returns the cosine in turns from a lookup table. For optimal precision, use turns between -1 and 1 (both
     * inclusive).
     * <br>
     * This approximation may have visible "steps" where it should be smooth, but this is generally only noticeable when
     * you need very fine detail. The steps occur because it converts its argument from turns to an array index in a
     * {@link #TABLE_SIZE}-item array, and truncates some of the least-significant digits to do so if necessary. You can
     * use {@link #cosSmootherTurns(double)} if you need better accuracy; it uses the least-significant digits to smoothly
     * interpolate between two items in the array.
     *
     * @param turns an angle in turns, where 0 to 1 is one rotation
     * @return the cosine of the given angle, between -1 and 1 inclusive
     */
    public static double cosTurns(final double turns) {
        return COS_TABLE_D[(int) (turns * turnToIndexD + 16384.5) & TABLE_MASK];
    }

    /**
     * Returns the tangent in turns, using a Padé approximant.
     * Based on <a href="https://math.stackexchange.com/a/4453027">this Stack Exchange answer</a>.
     * <br>
     * If you know you target newer JDK versions only, or you need higher precision, you should consider using
     * {@link #tanSmootherTurns(double)} instead. Compared to this method, tanSmootherTurns() is slightly faster on
     * recent JDKs, and is significantly more precise.
     *
     * @param turns an angle in turns, where 0 to 1 is one rotation
     * @return a double approximation of tan()
     */
    public static double tanTurns(double turns) {
        turns += turns;
        turns += 0.5;
        turns -= Math.floor(turns);
        turns -= 0.5;
        turns *= TrigTools.PI_D;
        final double x2 = turns * turns, x4 = x2 * x2;
        return turns * ((0.0010582010582010583) * x4 - (0.1111111111111111) * x2 + 1.0)
                / ((0.015873015873015872) * x4 - (0.4444444444444444) * x2 + 1.0);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Smooth Sine and Cosine">

    /**
     * A smooth sine approximation (not table-based) built around a Padé approximant calculated by Wolfram Alpha and
     * improved slightly by hand. This takes an input in radians, and takes and returns floats. This has better accuracy
     * for most inputs relative to earlier implementations (before version 0.5.0), but its maximum returned value is
     * slightly less than 1.0 (it is 0.9999010563). You may want to
     * use this if you notice statistical issues with the tabular approximation of sin(); in particular, only 16384
     * outputs are possible from {@link TrigTools#sin(float)}, and about half of those are duplicates, so if you need
     * more possible results in-between the roughly 8192 possible sin() returns, you can use this or
     * {@link #sinSmoother(float)}. Somewhat surprisingly, sinSmoother is both more accurate and faster than this, but
     * in case you don't want to use a lookup table, sinSmooth is here for that purpose.
     *
     * @param radians an angle in radians; most precise between -PI2 and PI2
     * @return the approximate sine of the given angle, from -1 to 1 inclusive
     */
    public static float sinSmooth(float radians) {
        radians = radians * (TrigTools.PI_INVERSE * 2f);
        final int ceil = (int) Math.ceil(radians) & -2;
        radians -= ceil;
        final float x2 = radians * radians;
        return (radians * (137.9199f + x2 * -35.84f)) / (87.802f + x2 * (13.288f + x2)) * (1 - (ceil & 2));
    }

    /**
     * A smooth cosine approximation (not table-based) built around a Padé approximant calculated by Wolfram Alpha and
     * improved slightly by hand. This takes an input in radians, and takes and returns floats. This has better accuracy
     * for most inputs relative to earlier implementations (before version 0.5.0), but its maximum returned value is
     * slightly less than 1.0 (it is 0.9999010563). You may want to
     * use this if you notice statistical issues with the tabular approximation of cos(); in particular, only 16384
     * outputs are possible from {@link TrigTools#cos(float)}, and about half of those are duplicates, so if you need
     * more possible results in-between the roughly 8192 possible cos() returns, you can use this or
     * {@link #cosSmoother(float)}. Somewhat surprisingly, cosSmoother is both more accurate and faster than this, but
     * in case you don't want to use a lookup table, cosSmooth is here for that purpose.
     *
     * @param radians an angle in radians; most precise between -PI2 and PI2
     * @return the approximate cosine of the given angle, from -1 to 1 inclusive
     */
    public static float cosSmooth(float radians) {
        radians = Math.abs(radians * (TrigTools.PI_INVERSE * 2f));
        final int floor = (int)radians | 1;
        radians -= floor;
        final float x2 = radians * radians;
        return (radians * (137.9199f + x2 * -35.84f)) / (87.802f + x2 * (13.288f + x2)) * ((floor & 2) - 1);
    }

    /**
     * A smooth sine approximation (not table-based) built around a Padé approximant calculated by Wolfram Alpha and
     * improved slightly by hand. This takes an input in radians, and takes and returns doubles. This has better
     * accuracy for most inputs relative to earlier implementations (before version 0.5.0), but its maximum returned
     * value is slightly less than 1.0 (it is about 0.9999010563). You may want to
     * use this if you notice statistical issues with the tabular approximation of sin(); in particular, only 16384
     * outputs are possible from {@link TrigTools#sin(float)}, and about half of those are duplicates, so if you need
     * more possible results in-between the roughly 8192 possible sin() returns, you can use this or
     * {@link #sinSmoother(double)}. Somewhat surprisingly, sinSmoother is both more accurate and faster than this, but
     * in case you don't want to use a lookup table, sinSmooth is here for that purpose.
     *
     * @param radians an angle in radians; most precise between -PI2 and PI2
     * @return the approximate sine of the given angle, from -1 to 1 inclusive
     */
    public static double sinSmooth(double radians) {
        radians = radians * (TrigTools.PI_INVERSE_D * 2.0);
        final int ceil = (int) Math.ceil(radians) & -2;
        radians -= ceil;
        final double x2 = radians * radians;
        return (radians * (137.9199 + x2 * -35.84)) / (87.802 + x2 * (13.288 + x2)) * (1 - (ceil & 2));
    }

    /**
     * A smooth cosine approximation (not table-based) built around a Padé approximant calculated by Wolfram Alpha and
     * improved slightly by hand. This takes an input in radians, and takes and returns doubles. This has better
     * accuracy for most inputs relative to earlier implementations (before version 0.5.0), but its maximum returned
     * value is slightly less than 1.0 (it is about 0.9999010563). You may want to
     * use this if you notice statistical issues with the tabular approximation of cos(); in particular, only 16384
     * outputs are possible from {@link TrigTools#cos(float)}, and about half of those are duplicates, so if you need
     * more possible results in-between the roughly 8192 possible cos() returns, you can use this or
     * {@link #cosSmoother(double)}. Somewhat surprisingly, cosSmoother is both more accurate and faster than this, but
     * in case you don't want to use a lookup table, cosSmooth is here for that purpose.
     *
     * @param radians an angle in radians; most precise between -PI2 and PI2
     * @return the approximate cosine of the given angle, from -1 to 1 inclusive
     */
    public static double cosSmooth(double radians) {
        radians = Math.abs(radians * (TrigTools.PI_INVERSE_D * 2.0));
        final int floor = (int)radians | 1;
        radians -= floor;
        final double x2 = radians * radians;
        return (radians * (137.9199 + x2 * -35.84)) / (87.802 + x2 * (13.288 + x2)) * ((floor & 2) - 1);
    }

    /**
     * A smooth sine approximation (not table-based) built around a Padé approximant calculated by Wolfram Alpha and
     * improved slightly by hand. This takes an input in degrees, and takes and returns floats. This has better accuracy
     * for most inputs relative to earlier implementations (before version 0.5.0), but its maximum returned value is
     * slightly less than 1.0 (it is 0.9999010563). You may want to
     * use this if you notice statistical issues with the tabular approximation of sinDeg(); in particular, only 16384
     * outputs are possible from {@link TrigTools#sinDeg(float)}, and about half of those are duplicates, so if you need
     * more possible results in-between the roughly 8192 possible sinDeg() returns, you can use this or
     * {@link #sinSmootherDeg(float)}. Somewhat surprisingly, sinSmootherDeg is both more accurate and faster than this,
     * but in case you don't want to use a lookup table, sinSmoothDeg is here for that purpose.
     *
     * @param degrees an angle in degrees; most precise between -360 and 360
     * @return the approximate sine of the given angle, from -1 to 1 inclusive
     */
    public static float sinSmoothDeg(float degrees) {
        degrees = degrees * (1f / 90f);
        final int ceil = (int) Math.ceil(degrees) & -2;
        degrees -= ceil;
        final float x2 = degrees * degrees;
        return (degrees * (137.9199f + x2 * -35.84f)) / (87.802f + x2 * (13.288f + x2)) * (1 - (ceil & 2));
    }

    /**
     * A smooth cosine approximation (not table-based) built around a Padé approximant calculated by Wolfram Alpha and
     * improved slightly by hand. This takes an input in degrees, and takes and returns floats. This has better accuracy
     * for most inputs relative to earlier implementations (before version 0.5.0), but its maximum returned value is
     * slightly less than 1.0 (it is 0.9999010563). You may want to
     * use this if you notice statistical issues with the tabular approximation of cosDeg(); in particular, only 16384
     * outputs are possible from {@link TrigTools#cosDeg(float)}, and about half of those are duplicates, so if you need
     * more possible results in-between the roughly 8192 possible cosDeg() returns, you can use this or
     * {@link #cosSmootherDeg(float)}. Somewhat surprisingly, cosSmootherDeg is both more accurate and faster than this,
     * but in case you don't want to use a lookup table, cosSmoothDeg is here for that purpose.
     *
     * @param degrees an angle in degrees; most precise between -360 and 360
     * @return the approximate cosine of the given angle, from -1 to 1 inclusive
     */
    public static float cosSmoothDeg(float degrees) {
        degrees = Math.abs(degrees * (1f / 90f));
        final int floor = (int)degrees | 1;
        degrees -= floor;
        final float x2 = degrees * degrees;
        return (degrees * (137.9199f + x2 * -35.84f)) / (87.802f + x2 * (13.288f + x2)) * ((floor & 2) - 1);
    }

    /**
     * A smooth sine approximation (not table-based) built around a Padé approximant calculated by Wolfram Alpha and
     * improved slightly by hand. This takes an input in degrees, and takes and returns doubles. This has better accuracy
     * for most inputs relative to earlier implementations (before version 0.5.0), but its maximum returned value is
     * slightly less than 1.0 (it is about 0.9999010563). You may want to
     * use this if you notice statistical issues with the tabular approximation of sinDeg(); in particular, only 16384
     * outputs are possible from {@link TrigTools#sinDeg(double)}, and about half of those are duplicates, so if you need
     * more possible results in-between the roughly 8192 possible sinDeg() returns, you can use this or
     * {@link #sinSmootherDeg(double)}. Somewhat surprisingly, sinSmootherDeg is both more accurate and faster than this,
     * but in case you don't want to use a lookup table, sinSmoothDeg is here for that purpose.
     *
     * @param degrees an angle in degrees; most precise between -360 and 360
     * @return the approximate sine of the given angle, from -1 to 1 inclusive
     */
    public static double sinSmoothDeg(double degrees) {
        degrees = degrees * (1.0 / 90.0);
        final int ceil = (int) Math.ceil(degrees) & -2;
        degrees -= ceil;
        final double x2 = degrees * degrees;
        return (degrees * (137.9199 + x2 * -35.84)) / (87.802 + x2 * (13.288 + x2)) * (1 - (ceil & 2));
    }

    /**
     * A smooth cosine approximation (not table-based) built around a Padé approximant calculated by Wolfram Alpha and
     * improved slightly by hand. This takes an input in degrees, and takes and returns doubles. This has better accuracy
     * for most inputs relative to earlier implementations (before version 0.5.0), but its maximum returned value is
     * slightly less than 1.0 (it is about 0.9999010563). You may want to
     * use this if you notice statistical issues with the tabular approximation of cosDeg(); in particular, only 16384
     * outputs are possible from {@link TrigTools#cosDeg(double)}, and about half of those are duplicates, so if you need
     * more possible results in-between the roughly 8192 possible cosDeg() returns, you can use this or
     * {@link #cosSmootherDeg(double)}. Somewhat surprisingly, cosSmootherDeg is both more accurate and faster than this,
     * but in case you don't want to use a lookup table, cosSmoothDeg is here for that purpose.
     *
     * @param degrees an angle in degrees; most precise between -360 and 360
     * @return the approximate cosine of the given angle, from -1 to 1 inclusive
     */
    public static double cosSmoothDeg(double degrees) {
        degrees = Math.abs(degrees * (1.0 / 90.0));
        final int floor = (int)degrees | 1;
        degrees -= floor;
        final double x2 = degrees * degrees;
        return (degrees * (137.9199 + x2 * -35.84)) / (87.802 + x2 * (13.288 + x2)) * (1 - (floor & 2));
    }

    /**
     * A smooth sine approximation (not table-based) built around a Padé approximant calculated by Wolfram Alpha and
     * improved slightly by hand. This takes an input in turns, and takes and returns floats. This has better accuracy
     * for most inputs relative to earlier implementations (before version 0.5.0), but its maximum returned value is
     * slightly less than 1.0 (it is 0.9999010563). You may want to
     * use this if you notice statistical issues with the tabular approximation of sinTurns(); in particular, only 16384
     * outputs are possible from {@link TrigTools#sinTurns(float)}, and about half of those are duplicates, so if you need
     * more possible results in-between the roughly 8192 possible sinTurns() returns, you can use this or
     * {@link #sinSmootherTurns(float)}. Somewhat surprisingly, sinSmootherTurns is both more accurate and faster than this,
     * but in case you don't want to use a lookup table, sinSmoothTurns is here for that purpose.
     *
     * @param turns an angle in turns; most precise between -1 and 1
     * @return the approximate sine of the given angle, from -1 to 1 inclusive
     */
    public static float sinSmoothTurns(float turns) {
        turns = turns * 4.0f;
        final int ceil = (int) Math.ceil(turns) & -2;
        turns -= ceil;
        final float x2 = turns * turns;
        return (turns * (137.9199f + x2 * -35.84f)) / (87.802f + x2 * (13.288f + x2)) * (1 - (ceil & 2));
    }

    /**
     * A smooth cosine approximation (not table-based) built around a Padé approximant calculated by Wolfram Alpha and
     * improved slightly by hand. This takes an input in turns, and takes and returns floats. This has better accuracy
     * for most inputs relative to earlier implementations (before version 0.5.0), but its maximum returned value is
     * slightly less than 1.0 (it is 0.9999010563). You may want to
     * use this if you notice statistical issues with the tabular approximation of cosTurns(); in particular, only 16384
     * outputs are possible from {@link TrigTools#cosTurns(float)}, and about half of those are duplicates, so if you need
     * more possible results in-between the roughly 8192 possible cosTurns() returns, you can use this or
     * {@link #cosSmootherTurns(float)}. Somewhat surprisingly, cosSmootherTurns is both more accurate and faster than this,
     * but in case you don't want to use a lookup table, cosSmoothTurns is here for that purpose.
     *
     * @param turns an angle in turns; most precise between -1 and 1
     * @return the approximate cosine of the given angle, from -1 to 1 inclusive
     */
    public static float cosSmoothTurns(float turns) {
        turns = Math.abs(turns * 4.0f);
        final int floor = (int)turns | 1;
        turns -= floor;
        final float x2 = turns * turns;
        return (turns * (137.9199f + x2 * -35.84f)) / (87.802f + x2 * (13.288f + x2)) * ((floor & 2) - 1);
    }

    /**
     * A smooth sine approximation (not table-based) built around a Padé approximant calculated by Wolfram Alpha and
     * improved slightly by hand. This takes an input in turns, and takes and returns doubles. This has better accuracy
     * for most inputs relative to earlier implementations (before version 0.5.0), but its maximum returned value is
     * slightly less than 1.0 (it is about 0.9999010563). You may want to
     * use this if you notice statistical issues with the tabular approximation of sinTurns(); in particular, only 16384
     * outputs are possible from {@link TrigTools#sinTurns(double)}, and about half of those are duplicates, so if you need
     * more possible results in-between the roughly 8192 possible sinTurns() returns, you can use this or
     * {@link #sinSmootherTurns(double)}. Somewhat surprisingly, sinSmootherTurns is both more accurate and faster than this,
     * but in case you don't want to use a lookup table, sinSmoothTurns is here for that purpose.
     *
     * @param turns an angle in turns; most precise between -1 and 1
     * @return the approximate sine of the given angle, from -1 to 1 inclusive
     */
    public static double sinSmoothTurns(double turns) {
        turns = turns * 4.0;
        final int ceil = (int) Math.ceil(turns) & -2;
        turns -= ceil;
        final double x2 = turns * turns;
        return (turns * (137.9199 + x2 * -35.84)) / (87.802 + x2 * (13.288 + x2)) * (1 - (ceil & 2));
    }

    /**
     * A smooth cosine approximation (not table-based) built around a Padé approximant calculated by Wolfram Alpha and
     * improved slightly by hand. This takes an input in turns, and takes and returns doubles. This has better accuracy
     * for most inputs relative to earlier implementations (before version 0.5.0), but its maximum returned value is
     * slightly less than 1.0 (it is about 0.9999010563). You may want to
     * use this if you notice statistical issues with the tabular approximation of cosTurns(); in particular, only 16384
     * outputs are possible from {@link TrigTools#cosTurns(double)}, and about half of those are duplicates, so if you need
     * more possible results in-between the roughly 8192 possible cosTurns() returns, you can use this or
     * {@link #cosSmootherTurns(double)}. Somewhat surprisingly, cosSmootherTurns is both more accurate and faster than this,
     * but in case you don't want to use a lookup table, cosSmoothTurns is here for that purpose.
     *
     * @param turns an angle in turns; most precise between -1 and 1
     * @return the approximate cosine of the given angle, from -1 to 1 inclusive
     */
    public static double cosSmoothTurns(double turns) {
        turns = Math.abs(turns * 4.0);
        final int floor = (int)turns | 1;
        turns -= floor;
        final double x2 = turns * turns;
        return (turns * (137.9199 + x2 * -35.84)) / (87.802 + x2 * (13.288 + x2)) * (1 - (floor & 2));
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Smoother Sine, Cosine, and Tangent">

    /**
     * Gets an approximation of the sine of {@code radians} that is usually much more accurate than
     * {@link #sin(float)} or {@link #sinSmooth(float)}, but that is somewhat slower. This still offers about 2x to
     * 4x the throughput of {@link Math#sin(double)} (cast to float).
     * <br>
     * Internally, this uses the same {@link #SIN_TABLE} that {@link #sin(float)} uses, but interpolates between two
     * adjacent entries in the table, rather than just using one entry unmodified.
     * @param radians an angle in radians; optimally between {@code -PI2} and {@code PI2}
     * @return the approximate sine of the given angle, from -1 to 1 inclusive
     */
    public static float sinSmoother(float radians) {
        radians *= radToIndex;
        final int floor = (int)(radians + 16384f) - 16384;
        final int masked = floor & TABLE_MASK;
        final float from = SIN_TABLE[masked], to = SIN_TABLE[masked+1];
        return from + (to - from) * (radians - floor);
    }

    /**
     * Gets an approximation of the sine of {@code radians} that is usually much more accurate than
     * {@link #sin(double)} or {@link #sinSmooth(double)}, but that is somewhat slower. This still offers better
     * throughput than {@link Math#sin(double)}.
     * <br>
     * Internally, this uses the same {@link #SIN_TABLE_D} that {@link #sin(double)} uses, but interpolates between two
     * adjacent entries in the table, rather than just using one entry unmodified.
     * @param radians an angle in radians; optimally between {@code -PI2_D} and {@code PI2_D}
     * @return the approximate sine of the given angle, from -1 to 1 inclusive
     */
    public static double sinSmoother(double radians) {
        radians = radians * radToIndexD + 16384.0;
        final int floor = (int)(radians);
        final int masked = floor & TABLE_MASK;
        final double from = SIN_TABLE_D[masked], to = SIN_TABLE_D[masked+1];
        return from + (to - from) * (radians - floor);
    }

    /**
     * Gets an approximation of the cosine of {@code radians} that is usually much more accurate than
     * {@link #cos(float)} or {@link #cosSmooth(float)}, but that is somewhat slower. This still offers about 2x to
     * 4x the throughput of {@link Math#cos(double)} (cast to float).
     * <br>
     * Internally, this uses the same {@link #COS_TABLE} that {@link #cos(float)} uses, but interpolates between two
     * adjacent entries in the table, rather than just using one entry unmodified.
     * @param radians an angle in radians; optimally between {@code -PI2} and {@code PI2}
     * @return the approximate cosine of the given angle, from -1 to 1 inclusive
     */
    public static float cosSmoother(float radians) {
        radians = Math.abs(radians) * radToIndex;
        final int floor = (int)radians;
        final int masked = floor & TABLE_MASK;
        final float from = COS_TABLE[masked], to = COS_TABLE[masked+1];
        return from + (to - from) * (radians - floor);
    }

    /**
     * Gets an approximation of the cosine of {@code radians} that is usually much more accurate than
     * {@link #cos(double)} or {@link #cosSmooth(double)}, but that is somewhat slower. This still offers better
     * throughput than {@link Math#cos(double)}.
     * <br>
     * Internally, this uses the same {@link #COS_TABLE_D} that {@link #cos(double)} uses, but interpolates between two
     * adjacent entries in the table, rather than just using one entry unmodified.
     * @param radians an angle in radians; optimally between {@code -PI2_D} and {@code PI2_D}
     * @return the approximate cosine of the given angle, from -1 to 1 inclusive
     */
    public static double cosSmoother(double radians) {
        radians = Math.abs(radians) * radToIndexD;
        final int floor = (int)radians;
        final int masked = floor & TABLE_MASK;
        final double from = COS_TABLE_D[masked], to = COS_TABLE_D[masked+1];
        return from + (to - from) * (radians - floor);
    }

    /**
     * Gets an approximation of the tangent of {@code radians} that is usually much more accurate than
     * {@link #tan(float)}, and can be slightly faster on recent JDKs (or slower on JDK 8). This still offers much
     * higher throughput than {@link Math#tan(double)} (cast to float).
     * <br>
     * Internally, this gets one table index from the given angle (rounding down) and quickly gets another index by
     * adding 1 (effectively rounding up). The down and up indices are looked up in {@link #SIN_TABLE} and
     * {@link #COS_TABLE}, the sines are interpolated, the cosines are interpolated, and the sine is divided by the
     * cosine. This is different from how {@link #tan(float)} works, and tends to be much more precise.
     * @param radians a float angle in radians, where 0 to {@link #PI2} is one rotation
     * @return a float approximation of tan()
     */
    public static float tanSmoother(float radians) {
        radians *= radToIndex;
        final int floor = (int)(radians + 16384.0) - 16384;
        final int masked = floor & TABLE_MASK;
        radians -= floor;
        final float fromS = SIN_TABLE[masked], toS = SIN_TABLE[masked+1];
        final float fromC = COS_TABLE[masked], toC = COS_TABLE[masked+1];
        return (fromS + (toS - fromS) * radians) / (fromC + (toC - fromC) * radians);
    }

    /**
     * Gets an approximation of the tangent of {@code radians} that is usually much more accurate than
     * {@link #tan(double)}, and can be slightly faster on recent JDKs (or slower on JDK 8). This still offers much
     * higher throughput than {@link Math#tan(double)}.
     * <br>
     * Internally, this gets one table index from the given angle (rounding down) and quickly gets another index by
     * adding 1 (effectively rounding up). The down and up indices are looked up in {@link #SIN_TABLE_D} and
     * {@link #COS_TABLE_D}, the sines are interpolated, the cosines are interpolated, and the sine is divided by the
     * cosine. This is different from how {@link #tan(double)} works, and tends to be much more precise.
     * @param radians a double angle in radians, where 0 to {@link #PI2} is one rotation
     * @return an approximation of tan()
     */
    public static double tanSmoother(double radians) {
        radians = radians * radToIndexD + 16384.0;
        final int floor = (int)(radians);
        final int masked = floor & TABLE_MASK;
        radians -= floor;
        final double fromS = SIN_TABLE_D[masked], toS = SIN_TABLE_D[masked+1];
        final double fromC = COS_TABLE_D[masked], toC = COS_TABLE_D[masked+1];
        return (fromS + (toS - fromS) * radians) / (fromC + (toC - fromC) * radians);
    }

    /**
     * Gets an approximation of the sine of {@code degrees} that is usually much more accurate than
     * {@link #sinDeg(float)} or {@link #sinSmoothDeg(float)}, but that is somewhat slower. This still offers about 2x
     * to 4x the throughput of {@link Math#sin(double)} (converted from degrees and cast to float).
     * <br>
     * Internally, this uses the same {@link #SIN_TABLE} that {@link #sinDeg(float)} uses, but interpolates between two
     * adjacent entries in the table, rather than just using one entry unmodified.
     * @param degrees an angle in degrees; optimally between -360 and 360
     * @return the approximate sine of the given angle, from -1 to 1 inclusive
     */
    public static float sinSmootherDeg(float degrees) {
        degrees *= degToIndex;
        final int floor = (int)(degrees + 16384f) - 16384;
        final int masked = floor & TABLE_MASK;
        final float from = SIN_TABLE[masked], to = SIN_TABLE[masked+1];
        return from + (to - from) * (degrees - floor);
    }

    /**
     * Gets an approximation of the sine of {@code degrees} that is usually much more accurate than
     * {@link #sinDeg(double)} or {@link #sinSmoothDeg(double)}, but that is somewhat slower. This still offers better
     * throughput than {@link Math#sin(double)} (converted from degrees).
     * <br>
     * Internally, this uses the same {@link #SIN_TABLE_D} that {@link #sinDeg(double)} uses, but interpolates between
     * two adjacent entries in the table, rather than just using one entry unmodified.
     * @param degrees an angle in degrees; optimally between -360 and 360
     * @return the approximate sine of the given angle, from -1 to 1 inclusive
     */
    public static double sinSmootherDeg(double degrees) {
        degrees = degrees * degToIndexD + 16384.0;
        final int floor = (int)(degrees);
        final int masked = floor & TABLE_MASK;
        final double from = SIN_TABLE_D[masked], to = SIN_TABLE_D[masked+1];
        return from + (to - from) * (degrees - floor);
    }

    /**
     * Gets an approximation of the cosine of {@code degrees} that is usually much more accurate than
     * {@link #cosDeg(float)} or {@link #cosSmoothDeg(float)}, but that is somewhat slower. This still offers about 2x to
     * 4x the throughput of {@link Math#cos(double)} (converted from degrees and cast to float).
     * <br>
     * Internally, this uses the same {@link #COS_TABLE} that {@link #cosDeg(float)} uses, but interpolates between two
     * adjacent entries in the table, rather than just using one entry unmodified.
     * @param degrees an angle in degrees; optimally between -360 and 360
     * @return the approximate cosine of the given angle, from -1 to 1 inclusive
     */
    public static float cosSmootherDeg(float degrees) {
        degrees = Math.abs(degrees) * degToIndex;
        final int floor = (int)degrees;
        final int masked = floor & TABLE_MASK;
        final float from = COS_TABLE[masked], to = COS_TABLE[masked+1];
        return from + (to - from) * (degrees - floor);
    }

    /**
     * Gets an approximation of the cosine of {@code degrees} that is usually much more accurate than
     * {@link #cosDeg(double)} or {@link #cosSmoothDeg(double)}, but that is somewhat slower. This still offers better
     * throughput than {@link Math#cos(double)} (converted from degrees).
     * <br>
     * Internally, this uses the same {@link #COS_TABLE_D} that {@link #cosDeg(double)} uses, but interpolates between
     * two adjacent entries in the table, rather than just using one entry unmodified.
     * @param degrees an angle in degrees; optimally between -360 and 360
     * @return the approximate cosine of the given angle, from -1 to 1 inclusive
     */
    public static double cosSmootherDeg(double degrees) {
        degrees = Math.abs(degrees) * degToIndexD;
        final int floor = (int)degrees;
        final int masked = floor & TABLE_MASK;
        final double from = COS_TABLE_D[masked], to = COS_TABLE_D[masked+1];
        return from + (to - from) * (degrees - floor);
    }

    /**
     * Gets an approximation of the tangent of {@code degrees} that is usually much more accurate than
     * {@link #tanDeg(float)}, and can be slightly faster on recent JDKs (or slower on JDK 8). This still offers much
     * higher throughput than {@link Math#tan(double)} (converted from degrees and cast to float).
     * <br>
     * Internally, this gets one table index from the given angle (rounding down) and quickly gets another index by
     * adding 1 (effectively rounding up). The down and up indices are looked up in {@link #SIN_TABLE} and
     * {@link #COS_TABLE}, the sines are interpolated, the cosines are interpolated, and the sine is divided by the
     * cosine. This is different from how {@link #tan(float)} works, and tends to be much more precise.
     * @param degrees a float angle in degrees, where 0 to 360 is one rotation
     * @return a float approximation of tan()
     */
    public static float tanSmootherDeg(float degrees) {
        degrees *= degToIndex;
        final int floor = (int)(degrees + 16384.0) - 16384;
        final int masked = floor & TABLE_MASK;
        degrees -= floor;
        final float fromS = SIN_TABLE[masked], toS = SIN_TABLE[masked+1];
        final float fromC = COS_TABLE[masked], toC = COS_TABLE[masked+1];
        return (fromS + (toS - fromS) * degrees) / (fromC + (toC - fromC) * degrees);
    }

    /**
     * Gets an approximation of the tangent of {@code degrees} that is usually much more accurate than
     * {@link #tanDeg(double)}, and can be slightly faster on recent JDKs (or slower on JDK 8). This still offers much
     * higher throughput than {@link Math#tan(double)} (converted from degrees).
     * <br>
     * Internally, this gets one table index from the given angle (rounding down) and quickly gets another index by
     * adding 1 (effectively rounding up). The down and up indices are looked up in {@link #SIN_TABLE_D} and
     * {@link #COS_TABLE_D}, the sines are interpolated, the cosines are interpolated, and the sine is divided by the
     * cosine. This is different from how {@link #tan(double)} works, and tends to be much more precise.
     * @param degrees a double angle in degrees, where 0 to 360 is one rotation
     * @return an approximation of tan()
     */
    public static double tanSmootherDeg(double degrees) {
        degrees = degrees * degToIndexD + 16384.0;
        final int floor = (int)(degrees);
        final int masked = floor & TABLE_MASK;
        degrees -= floor;
        final double fromS = SIN_TABLE_D[masked], toS = SIN_TABLE_D[masked+1];
        final double fromC = COS_TABLE_D[masked], toC = COS_TABLE_D[masked+1];
        return (fromS + (toS - fromS) * degrees) / (fromC + (toC - fromC) * degrees);
    }

    /**
     * Gets an approximation of the sine of {@code turns} that is usually much more accurate than
     * {@link #sinTurns(float)} or {@link #sinSmoothTurns(float)}, but that is somewhat slower. This still offers about 2x
     * to 4x the throughput of {@link Math#sin(double)} (converted from turns and cast to float).
     * <br>
     * Internally, this uses the same {@link #SIN_TABLE} that {@link #sinTurns(float)} uses, but interpolates between two
     * adjacent entries in the table, rather than just using one entry unmodified.
     * @param turns an angle in turns; optimally between -1 and 1
     * @return the approximate sine of the given angle, from -1 to 1 inclusive
     */
    public static float sinSmootherTurns(float turns) {
        turns *= turnToIndex;
        final int floor = (int)(turns + 16384f) - 16384;
        final int masked = floor & TABLE_MASK;
        final float from = SIN_TABLE[masked], to = SIN_TABLE[masked+1];
        return from + (to - from) * (turns - floor);
    }

    /**
     * Gets an approximation of the sine of {@code turns} that is usually much more accurate than
     * {@link #sinTurns(double)} or {@link #sinSmoothTurns(double)}, but that is somewhat slower. This still offers better
     * throughput than {@link Math#sin(double)} (converted from turns).
     * <br>
     * Internally, this uses the same {@link #SIN_TABLE_D} that {@link #sinTurns(double)} uses, but interpolates between
     * two adjacent entries in the table, rather than just using one entry unmodified.
     * @param turns an angle in turns; optimally between -1 and 1
     * @return the approximate sine of the given angle, from -1 to 1 inclusive
     */
    public static double sinSmootherTurns(double turns) {
        turns = turns * turnToIndexD + 16384.0;
        final int floor = (int)(turns);
        final int masked = floor & TABLE_MASK;
        final double from = SIN_TABLE_D[masked], to = SIN_TABLE_D[masked+1];
        return from + (to - from) * (turns - floor);
    }

    /**
     * Gets an approximation of the cosine of {@code turns} that is usually much more accurate than
     * {@link #cosTurns(float)} or {@link #cosSmoothTurns(float)}, but that is somewhat slower. This still offers about 2x to
     * 4x the throughput of {@link Math#cos(double)} (converted from turns and cast to float).
     * <br>
     * Internally, this uses the same {@link #COS_TABLE} that {@link #cosTurns(float)} uses, but interpolates between two
     * adjacent entries in the table, rather than just using one entry unmodified.
     * @param turns an angle in turns; optimally between -1 and 1
     * @return the approximate cosine of the given angle, from -1 to 1 inclusive
     */
    public static float cosSmootherTurns(float turns) {
        turns = Math.abs(turns) * turnToIndex;
        final int floor = (int)turns;
        final int masked = floor & TABLE_MASK;
        final float from = COS_TABLE[masked], to = COS_TABLE[masked+1];
        return from + (to - from) * (turns - floor);
    }

    /**
     * Gets an approximation of the cosine of {@code turns} that is usually much more accurate than
     * {@link #cosTurns(double)} or {@link #cosSmoothTurns(double)}, but that is somewhat slower. This still offers better
     * throughput than {@link Math#cos(double)} (converted from turns).
     * <br>
     * Internally, this uses the same {@link #COS_TABLE_D} that {@link #cosTurns(double)} uses, but interpolates between
     * two adjacent entries in the table, rather than just using one entry unmodified.
     * @param turns an angle in turns; optimally between -1 and 1
     * @return the approximate cosine of the given angle, from -1 to 1 inclusive
     */
    public static double cosSmootherTurns(double turns) {
        turns = Math.abs(turns) * turnToIndexD;
        final int floor = (int)turns;
        final int masked = floor & TABLE_MASK;
        final double from = COS_TABLE_D[masked], to = COS_TABLE_D[masked+1];
        return from + (to - from) * (turns - floor);
    }

    /**
     * Gets an approximation of the tangent of {@code turns} that is usually much more accurate than
     * {@link #tanTurns(float)}, and can be slightly faster on recent JDKs (or slower on JDK 8). This still offers much
     * higher throughput than {@link Math#tan(double)} (converted from turns and cast to float).
     * <br>
     * Internally, this gets one table index from the given angle (rounding down) and quickly gets another index by
     * adding 1 (effectively rounding up). The down and up indices are looked up in {@link #SIN_TABLE} and
     * {@link #COS_TABLE}, the sines are interpolated, the cosines are interpolated, and the sine is divided by the
     * cosine. This is different from how {@link #tan(float)} works, and tends to be much more precise.
     * @param turns a float angle in turns, where 0 to 1 is one rotation
     * @return a float approximation of tan()
     */
    public static float tanSmootherTurns(float turns) {
        turns *= turnToIndex;
        final int floor = (int)(turns + 16384.0) - 16384;
        final int masked = floor & TABLE_MASK;
        turns -= floor;
        final float fromS = SIN_TABLE[masked], toS = SIN_TABLE[masked+1];
        final float fromC = COS_TABLE[masked], toC = COS_TABLE[masked+1];
        return (fromS + (toS - fromS) * turns) / (fromC + (toC - fromC) * turns);
    }

    /**
     * Gets an approximation of the tangent of {@code turns} that is usually much more accurate than
     * {@link #tanTurns(double)}, and can be slightly faster on recent JDKs (or slower on JDK 8). This still offers much
     * higher throughput than {@link Math#tan(double)} (converted from turns).
     * <br>
     * Internally, this gets one table index from the given angle (rounding down) and quickly gets another index by
     * adding 1 (effectively rounding up). The down and up indices are looked up in {@link #SIN_TABLE_D} and
     * {@link #COS_TABLE_D}, the sines are interpolated, the cosines are interpolated, and the sine is divided by the
     * cosine. This is different from how {@link #tan(double)} works, and tends to be much more precise.
     * @param turns a double angle in turns, where 0 to 1 is one rotation
     * @return an approximation of tan()
     */
    public static double tanSmootherTurns(double turns) {
        turns = turns * turnToIndexD + 16384.0;
        final int floor = (int)(turns);
        final int masked = floor & TABLE_MASK;
        turns -= floor;
        final double fromS = SIN_TABLE_D[masked], toS = SIN_TABLE_D[masked+1];
        final double fromC = COS_TABLE_D[masked], toC = COS_TABLE_D[masked+1];
        return (fromS + (toS - fromS) * turns) / (fromC + (toC - fromC) * turns);
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Precise Sine, Cosine, and Tangent">

    /**
     * A non-tabular sine approximation in radians that is typically faster than {@link Math#sin(double)} and accurate
     * to within two ULPs for inputs in the 0 to PI2 range. While this is slower than
     * {@link TrigTools#sinSmoother(float)}, it is usually a little more accurate, and its worst-case results are
     * significantly more accurate than sinSmoother().
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
     * {@link TrigTools#cosSmoother(float)}, it is usually a little more accurate, and its worst-case results are
     * significantly more accurate than cosSmoother().
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
     * {@link TrigTools#sinSmootherDeg(float)}, it is usually a little more accurate, and its worst-case results are
     * significantly more accurate than sinSmootherDeg().
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
     * {@link TrigTools#cosSmootherDeg(float)}, it is usually a little more accurate, and its worst-case results are
     * significantly more accurate than cosSmootherDeg().
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
     * {@link TrigTools#sinSmootherTurns(float)}, it is usually a little more accurate, and its worst-case results are
     * significantly more accurate than sinSmootherTurns().
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
     * {@link TrigTools#cosSmootherTurns(float)}, it is usually a little more accurate, and its worst-case results are
     * significantly more accurate than cosSmootherTurns().
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
     * {@link TrigTools#tanSmoother(float)}. This method is only very slightly more precise than tanSmoother();
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
     * {@link TrigTools#tanSmootherDeg(float)}. This method is only very slightly more precise than tanSmootherDeg(),
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
     * {@link TrigTools#tanSmootherTurns(float)}. This method is only very slightly more precise than
     * tanSmootherTurns(), though the error almost certainly balloons significantly near the undefined inputs at odd
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

    /**
     * A non-tabular sine approximation in radians that is typically faster than {@link Math#sin(double)}.
     * While this is slower than
     * {@link TrigTools#sinSmoother(double)}, it is usually a little more accurate, and its worst-case results are
     * significantly more accurate than sinSmoother().
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     * @param radians input in radians, typically between -PI2 and PI2 for greatest precision
     * @return the sine of the given angle as a double, between -1 and 1 inclusive
     */
    public static double sinPrecise(double radians) {
        double x = Math.abs(radians);
        int quadrant = (int)(0.6366197723675814 * x + 0.5);
        // Cody-Waite argument reduction, https://stackoverflow.com/questions/42455143/sine-cosine-modular-extended-precision-arithmetic
        x = ((x - quadrant * 1.5703125) - quadrant * 0.0004837512969970703125) - quadrant * 7.549789948768648e-8;
        double x2 = x * x, s;
        switch ((quadrant ^ (int)(NumberUtils.doubleToLongBits(radians) >>> 62 & 2)) & 3) {
            case 0:
                s = ((-1.9515295891e-4 * x2 + 8.3321608736e-3) * x2 - 1.6666654611e-1) * x2 * x + x;
                break;
            case 1:
                s = ((2.443315711809948e-5 * x2 - (1.388731625493765e-3)) * x2 + (4.166664568298827e-2)) * x2 * x2 - 0.5 * x2 + 1.0;
                break;
            case 2:
                s = (((1.9515295891e-4 * x2 - 8.3321608736e-3) * x2 + 1.6666654611e-1) * x2 * x - x);
                break;
            default:
                s = (((-2.443315711809948e-5 * x2 + 1.388731625493765e-3) * x2 - 4.166664568298827e-2) * x2 * x2 + 0.5 * x2 - 1.0);
        }
        return s;
    }

    /**
     * A non-tabular cosine approximation in radians that is typically faster than {@link Math#cos(double)}. While
     * this is slower than
     * {@link TrigTools#cosSmoother(double)}, it is usually a little more accurate, and its worst-case results are
     * significantly more accurate than cosSmoother().
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     * @param radians input in radians, typically between -PI2 and PI2 for greatest precision
     * @return the cosine of the given angle as a double, between -1 and 1 inclusive
     */
    public static double cosPrecise(double radians) {
        double x = Math.abs(radians);
        int quadrant = (int)(0.6366197723675814 * x + 0.5);
        // Cody-Waite argument reduction, https://stackoverflow.com/questions/42455143/sine-cosine-modular-extended-precision-arithmetic
        x = ((x - quadrant * 1.5703125) - quadrant * 0.0004837512969970703125) - quadrant * 7.549789948768648e-8;
        double x2 = x * x, s;
        switch (quadrant & 3) {
            case 3:
                s = ((-1.9515295891e-4 * x2 + 8.3321608736e-3) * x2 - 1.6666654611e-1) * x2 * x + x;
                break;
            case 0:
                s = ((2.443315711809948e-5 * x2 - (1.388731625493765e-3)) * x2 + (4.166664568298827e-2)) * x2 * x2 - 0.5 * x2 + 1.0;
                break;
            case 1:
                s = (((1.9515295891e-4 * x2 - 8.3321608736e-3) * x2 + 1.6666654611e-1) * x2 * x - x);
                break;
            default:
                s = (((-2.443315711809948e-5 * x2 + 1.388731625493765e-3) * x2 - 4.166664568298827e-2) * x2 * x2 + 0.5 * x2 - 1.0);
        }
        return s;
    }

    /**
     * A non-tabular sine approximation in degrees that is typically faster than {@link Math#sin(double)}. While this
     * is slower than
     * {@link TrigTools#sinSmootherDeg(double)}, it is usually a little more accurate, and its worst-case results are
     * significantly more accurate than sinSmootherDeg().
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     * @param degrees input in degrees, typically between -360 and 360 for greatest precision
     * @return the sine of the given angle as a double, between -1 and 1 inclusive
     */
    public static double sinDegPrecise(double degrees) {
        double x = Math.abs(degrees);
        int quadrant = (int)(0.011111111 * x + 0.5);
        x = (x - quadrant * 90.0) * (HALF_PI_D / 90.0);
        double x2 = x * x, s;
        switch ((quadrant ^ (int)(NumberUtils.doubleToLongBits(degrees) >>> 62 & 2)) & 3) {
            case 0:
                s = ((-1.9515295891e-4 * x2 + 8.3321608736e-3) * x2 - 1.6666654611e-1) * x2 * x + x;
                break;
            case 1:
                s = ((2.443315711809948e-5 * x2 - (1.388731625493765e-3)) * x2 + (4.166664568298827e-2)) * x2 * x2 - 0.5 * x2 + 1;
                break;
            case 2:
                s = (((1.9515295891e-4 * x2 - 8.3321608736e-3) * x2 + 1.6666654611e-1) * x2 * x - x);
                break;
            default:
                s = (((-2.443315711809948e-5 * x2 + 1.388731625493765e-3) * x2 - 4.166664568298827e-2) * x2 * x2 + 0.5 * x2 - 1);
        }
        return s;
    }

    /**
     * A non-tabular cosine approximation in degrees that is typically faster than {@link Math#cos(double)}.
     * While this is slower than
     * {@link TrigTools#cosSmootherDeg(double)}, it is usually a little more accurate, and its worst-case results are
     * significantly more accurate than cosSmootherDeg().
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     * @param degrees input in degrees, typically between -360 and 360 for greatest precision
     * @return the cosine of the given angle as a double, between -1 and 1 inclusive
     */
    public static double cosDegPrecise(double degrees) {
        double x = Math.abs(degrees);
        int quadrant = (int)(0.011111111 * x + 0.5);
        x = (x - quadrant * 90.0) * (HALF_PI_D / 90.0);
        double x2 = x * x, s;
        switch (quadrant & 3) {
            case 3:
                s = ((-1.9515295891e-4 * x2 + 8.3321608736e-3) * x2 - 1.6666654611e-1) * x2 * x + x;
                break;
            case 0:
                s = ((2.443315711809948e-5 * x2 - (1.388731625493765e-3)) * x2 + (4.166664568298827e-2)) * x2 * x2 - 0.5 * x2 + 1.0;
                break;
            case 1:
                s = (((1.9515295891e-4 * x2 - 8.3321608736e-3) * x2 + 1.6666654611e-1) * x2 * x - x);
                break;
            default:
                s = (((-2.443315711809948e-5 * x2 + 1.388731625493765e-3) * x2 - 4.166664568298827e-2) * x2 * x2 + 0.5 * x2 - 1.0);
        }
        return s;
    }

    /**
     * A non-tabular sine approximation in turns that is typically faster than {@link Math#sin(double)}.
     * While this is slower than
     * {@link TrigTools#sinSmootherTurns(double)}, it is usually a little more accurate, and its worst-case results are
     * significantly more accurate than sinSmootherTurns().
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     * @param turns input in turns, typically between -1 and 1 for greatest precision
     * @return the sine of the given angle as a double, between -1 and 1 inclusive
     */
    public static double sinTurnsPrecise(double turns) {
        double x = Math.abs(turns);
        int quadrant = (int)(4.0 * x + 0.5);
        x = (x - quadrant * 0.25) * PI2_D;
        double x2 = x * x, s;
        switch ((quadrant ^ (int)(NumberUtils.doubleToLongBits(turns) >>> 62 & 2)) & 3) {
            case 0:
                s = ((-1.9515295891e-4 * x2 + 8.3321608736e-3) * x2 - 1.6666654611e-1) * x2 * x + x;
                break;
            case 1:
                s = ((2.443315711809948e-5 * x2 - (1.388731625493765e-3)) * x2 + (4.166664568298827e-2)) * x2 * x2 - 0.5 * x2 + 1.0;
                break;
            case 2:
                s = (((1.9515295891e-4 * x2 - 8.3321608736e-3) * x2 + 1.6666654611e-1) * x2 * x - x);
                break;
            default:
                s = (((-2.443315711809948e-5 * x2 + 1.388731625493765e-3) * x2 - 4.166664568298827e-2) * x2 * x2 + 0.5 * x2 - 1.0);
        }
        return s;
    }

    /**
     * A non-tabular cosine approximation in turns that is typically faster than {@link Math#cos(double)}.
     * While this is slower than
     * {@link TrigTools#cosSmootherTurns(double)}, it is usually a little more accurate, and its worst-case results are
     * significantly more accurate than cosSmootherTurns().
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     * @param turns input in turns, typically between -1 and 1 for greatest precision
     * @return the cosine of the given angle as a double, between -1 and 1 inclusive
     */
    public static double cosTurnsPrecise(double turns) {
        double x = Math.abs(turns);
        int quadrant = (int)(4.0 * x + 0.5);
        x = (x - quadrant * 0.25) * PI2_D;
        double x2 = x * x, s;
        switch (quadrant & 3) {
            case 3:
                s = ((-1.9515295891e-4 * x2 + 8.3321608736e-3) * x2 - 1.6666654611e-1) * x2 * x + x;
                break;
            case 0:
                s = ((2.443315711809948e-5 * x2 - (1.388731625493765e-3)) * x2 + (4.166664568298827e-2)) * x2 * x2 - 0.5 * x2 + 1.0;
                break;
            case 1:
                s = (((1.9515295891e-4 * x2 - 8.3321608736e-3) * x2 + 1.6666654611e-1) * x2 * x - x);
                break;
            default:
                s = (((-2.443315711809948e-5 * x2 + 1.388731625493765e-3) * x2 - 4.166664568298827e-2) * x2 * x2 + 0.5 * x2 - 1.0);
        }
        return s;
    }

    /**
     * Returns the tangent in radians; non-tabular and very precise, but about half as fast as
     * {@link TrigTools#tanSmoother(double)}. This method is only very slightly more precise than tanSmoother(), though
     * the error almost certainly balloons significantly for both near the undefined inputs at odd multiples of
     * {@link TrigTools#HALF_PI_D}.
     * The main reason to use this method is that it is non-tabular. If the {@link TrigTools#SIN_TABLE_D} and
     * {@link TrigTools#COS_TABLE_D} arrays are not in processor cache, this non-tabular method may become faster.
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     *
     * @param radians a double angle in radians, where 0 to {@link TrigTools#PI2} is one rotation
     * @return a double approximation of tan()
     */
    public static double tanPrecise(double radians) {
        double x = Math.abs(radians);
        int quadrant = (int)(0.6366197723675814 * x + 0.5);
        // Cody-Waite argument reduction, https://stackoverflow.com/questions/42455143/sine-cosine-modular-extended-precision-arithmetic
        x = ((x - quadrant * 1.5703125) - quadrant * 0.0004837512969970703125) - quadrant * 7.549789948768648e-8;
        double x2 = x * x;
        double p = (((((9.38540185543e-3 * x2 + (3.11992232697e-3)) * x2 + (2.44301354525e-2)) * x2
                + (5.34112807005e-2)) * x2 + (1.33387994085e-1)) * x2 + (3.33331568548e-1)) * x2 * x + x;
        if((quadrant & 1) == 1)
            return -Math.signum(radians) / p;
        return Math.signum(radians) * p;
    }

    /**
     * Returns the tangent in degrees; non-tabular and very precise, but about half as fast as
     * {@link TrigTools#tanSmootherDeg(double)}. This method is only very slightly more precise than tanSmootherDeg(),
     * though the error almost certainly balloons significantly near the undefined inputs at odd multiples of 90.
     * The main reason to use this method is that it is non-tabular. If the {@link TrigTools#SIN_TABLE_D} and
     * {@link TrigTools#COS_TABLE_D} arrays are not in processor cache, this non-tabular method may become faster.
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     *
     * @param degrees a double angle in degrees, where 0 to {@code 360} is one rotation
     * @return a double approximation of tan()
     */
    public static double tanDegPrecise(double degrees) {
        double x = Math.abs(degrees);
        int quadrant = (int)(0.011111111f * x + 0.5f);
        x = (x - quadrant * 90f) * (HALF_PI / 90f);
        double x2 = x * x;
        double p = (((((9.38540185543e-3 * x2 + (3.11992232697e-3)) * x2 + (2.44301354525e-2)) * x2
                + (5.34112807005e-2)) * x2 + (1.33387994085e-1)) * x2 + (3.33331568548e-1)) * x2 * x + x;
        if((quadrant & 1) == 1)
            return -Math.signum(degrees) / p;
        return Math.signum(degrees) * p;
    }

    /**
     * Returns the tangent in turns; non-tabular and very precise, but about half as fast as
     * {@link TrigTools#tanSmootherTurns(double)}. This method is only very slightly more precise than
     * tanSmootherTurns(), though the error almost certainly balloons significantly near the undefined inputs at odd
     * multiples of 0.25. The main reason to use this method is that it is non-tabular. If the
     * {@link TrigTools#SIN_TABLE_D} and {@link TrigTools#COS_TABLE_D} arrays are not in processor cache, this
     * non-tabular method may become faster.
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     *
     * @param turns a double angle in turns, where 0.0 to 1.0 is one rotation
     * @return a double approximation of tan()
     */
    public static double tanTurnsPrecise(double turns) {
        double x = Math.abs(turns);
        int quadrant = (int)(4 * x + 0.5f);
        x = (x - quadrant * 0.25f) * PI2;
        double x2 = x * x;
        double p = (((((9.38540185543e-3 * x2 + (3.11992232697e-3)) * x2 + (2.44301354525e-2)) * x2
                + (5.34112807005e-2)) * x2 + (1.33387994085e-1)) * x2 + (3.33331568548e-1)) * x2 * x + x;
        if((quadrant & 1) == 1)
            return -Math.signum(turns) / p;
        return Math.signum(turns) * p;
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Arctangent and atan2">

    /**
     * A variant on {@link #atan(float)} that does not tolerate infinite inputs for speed reasons. This can be given a double
     * parameter, but is otherwise the same as atan(float), and returns a float like that method. It uses the same approximation,
     * from sheet 11 of "Approximations for Digital Computers." This is mostly meant to be used inside
     * {@link #atan2(float, float)}, but it may be a tiny bit faster than atan(float) in other code.
     *
     * @param i any finite double or float, but more commonly a float
     * @return an output from the inverse tangent function in radians, from {@code -HALF_PI} to {@code HALF_PI} inclusive
     */
    public static double atanUnchecked(double i) {
        // We use double precision internally, because some constants need double precision.
        double n = Math.abs(i);
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
     * A variant on {@link #atanTurns(float)} that does not tolerate infinite inputs for speed reasons. This can be given a double
     * parameter, but is otherwise the same as atanTurns(float), but returns a double in case external code needs higher precision.
     * It uses the same approximation, from sheet 11 of "Approximations for Digital Computers." This is mostly meant to be used inside
     * {@link #atan2Turns(float, float)}, but it may be a tiny bit faster than atanTurns(float) in other code.
     *
     * @param i any finite double or float, but more commonly a float
     * @return an output from the inverse tangent function in turns, from {@code -0.25} to {@code 0.25} inclusive
     */
    public static double atanUncheckedTurns(double i) {
        // We use double precision internally, because some constants need double precision.
        double n = Math.abs(i);
        // c uses the "equally-good" formulation that permits n to be from 0 to almost infinity.
        double c = (n - 1.0) / (n + 1.0);
        // The approximation needs 6 odd powers of c.
        double c2 = c * c;
        double c3 = c * c2;
        double c5 = c3 * c2;
        double c7 = c5 * c2;
        double c9 = c7 * c2;
        double c11 = c9 * c2;
        return (Math.signum(i) * (0.125
                + (0.15915132390848943 * c - 0.052938669438878753 * c3 + 0.030803398362108523 * c5
                - 0.01853086679887605 * c7 + 0.008380036148199356 * c9 - 0.0018654869189687236 * c11)));
    }

    /**
     * A variant on {@link #atanDeg(float)} that does not tolerate infinite inputs for speed reasons. This can be given a double
     * parameter, but is otherwise the same as atanDeg(float), and returns a float like that method. It uses the same approximation,
     * from sheet 11 of "Approximations for Digital Computers." This is mostly meant to be used inside
     * {@link #atan2(float, float)}, but it may be a tiny bit faster than atanDeg(float) in other code.
     *
     * @param i any finite double or float, but more commonly a float
     * @return an output from the inverse tangent function in degrees, from {@code -90} to {@code 90} inclusive
     */
    public static double atanUncheckedDeg(double i) {
        // We use double precision internally, because some constants need double precision.
        double n = Math.abs(i);
        // c uses the "equally-good" formulation that permits n to be from 0 to almost infinity.
        double c = (n - 1.0) / (n + 1.0);
        // The approximation needs 6 odd powers of c.
        double c2 = c * c;
        double c3 = c * c2;
        double c5 = c3 * c2;
        double c7 = c5 * c2;
        double c9 = c7 * c2;
        double c11 = c9 * c2;
        return (Math.signum(i) * (45.0
                + (57.2944766070562 * c - 19.05792099799635 * c3 + 11.089223410359068 * c5 - 6.6711120475953765 * c7 + 3.016813013351768 * c9 - 0.6715752908287405 * c11)));
    }

    /**
     * Arc tangent approximation with very low error, using an algorithm from the 1955 research study "Approximations for Digital
     * Computers," by RAND Corporation (this is sheet 11's algorithm, which is the fourth-fastest and fourth-least precise). This
     * method is usually about 4x faster than {@link Math#atan(double)}, but is somewhat less precise than Math's implementation.
     * For finite inputs only, you may get a tiny speedup by using {@link #atanUnchecked(double)}, but this method will be correct
     * enough for infinite inputs, and atanUnchecked() will not be.
     *
     * @param i an input to the inverse tangent function; any float is accepted
     * @return an output from the inverse tangent function in radians, from {@code -HALF_PI} to {@code HALF_PI} inclusive
     * @see #atanUnchecked(double) If you know the input will be finite, you can use atanUnchecked() instead.
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
     * For finite inputs only, you may get a tiny speedup by using {@link #atanUncheckedDeg(double)}, but this method will be correct
     * enough for infinite inputs, and atanUncheckedDeg() will not be.
     *
     * @param i an input to the inverse tangent function; any float is accepted
     * @return an output from the inverse tangent function in degrees, from {@code -90} to {@code 90} inclusive
     * @see #atanUncheckedDeg(double) If you know the input will be finite, you can use atanUncheckedDeg() instead.
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
     * For finite inputs only, you may get a tiny speedup by using {@link #atanUncheckedTurns(double)}, but this method will be correct
     * enough for infinite inputs, and atanUncheckedTurns() will not be.
     *
     * @param i an input to the inverse tangent function; any float is accepted
     * @return an output from the inverse tangent function in turns, from {@code -0.25} to {@code 0.25} inclusive
     * @see #atanUncheckedTurns(double) If you know the input will be finite, you can use atanUncheckedTurns() instead.
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
     * For finite inputs only, you may get a tiny speedup by using {@link #atanUnchecked(double)}, but this method will be correct
     * enough for infinite inputs, and atanUnchecked() will not be.
     *
     * @param i an input to the inverse tangent function; any double is accepted
     * @return an output from the inverse tangent function in radians, from {@code -HALF_PI} to {@code HALF_PI} inclusive
     * @see #atanUnchecked(double) If you know the input will be finite, you can use atanUnchecked() instead.
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
     * For finite inputs only, you may get a tiny speedup by using {@link #atanUncheckedDeg(double)}, but this method will be correct
     * enough for infinite inputs, and atanUncheckedDeg() will not be.
     *
     * @param i an input to the inverse tangent function; any double is accepted
     * @return an output from the inverse tangent function in degrees, from {@code -90} to {@code 90} inclusive
     * @see #atanUncheckedDeg(double) If you know the input will be finite, you can use atanUncheckedDeg() instead.
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
     * For finite inputs only, you may get a tiny speedup by using {@link #atanUncheckedTurns(double)}, but this method will be correct
     * enough for infinite inputs, and atanUncheckedTurns() will not be.
     *
     * @param i an input to the inverse tangent function; any double is accepted
     * @return an output from the inverse tangent function in turns, from {@code -0.25} to {@code 0.25} inclusive
     * @see #atanUncheckedTurns(double) If you know the input will be finite, you can use atanUncheckedTurns() instead.
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

    /**
     * Close approximation of the frequently-used trigonometric method atan2, using radians. Average error is
     * 1.057E-6 radians; maximum error is 1.922E-6. Takes y and x (in that unusual order) as
     * floats, and returns the angle from the origin to that point in radians. It is about 4 times faster than
     * {@link Math#atan2(double, double)} (roughly 15 ns instead of roughly 60 ns for Math, on Java 8 HotSpot).
     * <br>
     * Credit for this goes to the 1955 research study "Approximations for Digital Computers," by RAND Corporation. This is sheet
     * 11's algorithm, which is the fourth-fastest and fourth-least precise. The algorithms on sheets 8-10 are faster, but only by
     * a very small degree, and are considerably less precise. That study provides an {@link #atan(float)} method, and that cleanly
     * translates to atan2().
     *
     * @param y y-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @param x x-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @return the angle to the given point, in radians as a float; ranges from {@code -PI} to {@code PI}
     */
    public static float atan2(final float y, float x) {
        float n = y / x;
        if (n != n)
            n = (y == x ? 1f : -1.0f); // if both y and x are infinite, n would be NaN
        else if (n - n != n - n) x = 0f; // if n is infinite, y is infinitely larger than x.
        if (x > 0)
            return (float) atanUnchecked(n);
        else if (x < 0) {
            if (y >= 0) return (float) (atanUnchecked(n) + Math.PI);
            return (float) (atanUnchecked(n) - Math.PI);
        } else if (y > 0)
            return x + HALF_PI;
        else if (y < 0) return x - HALF_PI;
        return x + y; // returns 0 for 0,0 or NaN if either y or x is NaN
    }

    /**
     * Close approximation of the frequently-used trigonometric method atan2, using positive or negative degrees.
     * Average absolute error is 0.00006037 degrees; relative error is 0 degrees, maximum error is 0.00010396 degrees.
     * Takes y and x (in that unusual order) as floats, and returns the angle from the origin to that point in degrees.
     * <br>
     * Credit for this goes to the 1955 research study "Approximations for Digital Computers," by RAND Corporation. This is sheet
     * 11's algorithm, which is the fourth-fastest and fourth-least precise. The algorithms on sheets 8-10 are faster, but only by
     * a very small degree, and are considerably less precise. That study provides an {@link #atan(float)} method, and that cleanly
     * translates to atan2Deg().
     *
     * @param y y-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @param x x-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @return the angle to the given point, in degrees as a float; ranges from {@code -180} to {@code 180}
     */
    public static float atan2Deg(final float y, float x) {
        float n = y / x;
        if (n != n)
            n = (y == x ? 1f : -1.0f); // if both y and x are infinite, n would be NaN
        else if (n - n != n - n) x = 0f; // if n is infinite, y is infinitely larger than x.
        if (x > 0)
            return (float) atanUncheckedDeg(n);
        else if (x < 0) {
            if (y >= 0) return (float) (atanUncheckedDeg(n) + 180.0);
            return (float) (atanUncheckedDeg(n) - 180.0);
        } else if (y > 0)
            return x + 90f;
        else if (y < 0) return x - 90f;
        return x + y; // returns 0 for 0,0 or NaN if either y or x is NaN
    }

    /**
     * Close approximation of the frequently-used trigonometric method atan2, using non-negative degrees only.
     * Average absolute error is 0.00006045 degrees; relative error is 0 degrees; maximum error is 0.00011178 degrees.
     * Takes y and x (in that unusual order) as floats, and returns the angle from the origin to that point in degrees.
     * <br>
     * Credit for this goes to the 1955 research study "Approximations for Digital Computers," by RAND Corporation. This is sheet
     * 11's algorithm, which is the fourth-fastest and fourth-least precise. The algorithms on sheets 8-10 are faster, but only by
     * a very small degree, and are considerably less precise. That study provides an {@link #atan(float)} method, and that cleanly
     * translates to atan2Deg360().
     *
     * @param y y-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @param x x-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @return the angle to the given point, in degrees as a float; ranges from {@code 0} to {@code 360}
     */
    public static float atan2Deg360(final float y, float x) {
        float n = y / x;
        if (n != n)
            n = (y == x ? 1f : -1.0f); // if both y and x are infinite, n would be NaN
        else if (n - n != n - n) x = 0f; // if n is infinite, y is infinitely larger than x.
        if (x > 0) {
            if (y >= 0)
                return (float) atanUncheckedDeg(n);
            else
                return (float) (atanUncheckedDeg(n) + 360.0);
        } else if (x < 0) {
            return (float) (atanUncheckedDeg(n) + 180.0);
        } else if (y > 0) return x + 90f;
        else if (y < 0) return x + 270f;
        return x + y; // returns 0 for 0,0 or NaN if either y or x is NaN
    }

    /**
     * Close approximation of the frequently-used trigonometric method atan2, using non-negative turns only.
     * Average absolute error is 0.00000030 turns; relative error is 0 turns; maximum error is 0.00000017 turns.
     * Takes y and x (in that unusual order) as floats, and returns the angle from the origin to that point in turns.
     * Because this always returns a float between 0.0 (inclusive) and 1.0 (exclusive), it can be useful for various
     * kinds of calculations that must store angles as a small fraction, such as packing a hue angle into a byte.
     * <br>
     * Credit for this goes to the 1955 research study "Approximations for Digital Computers," by RAND Corporation. This is sheet
     * 11's algorithm, which is the fourth-fastest and fourth-least precise. The algorithms on sheets 8-10 are faster, but only by
     * a very small degree, and are considerably less precise. That study provides an {@link #atan(float)} method, and that cleanly
     * translates to atan2Turns().
     *
     * @param y y-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @param x x-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @return the angle to the given point, in turns as a float; ranges from {@code 0.0f} to {@code 1.0f}
     */
    public static float atan2Turns(final float y, float x) {
        float n = y / x;
        if (n != n)
            n = (y == x ? 1f : -1.0f); // if both y and x are infinite, n would be NaN
        else if (n - n != n - n) x = 0f; // if n is infinite, y is infinitely larger than x.
        if (x > 0) {
            if (y >= 0)
                return (float) atanUncheckedTurns(n);
            else
                return (float) (atanUncheckedTurns(n) + 1.0);
        } else if (x < 0) {
            return (float) (atanUncheckedTurns(n) + 0.5);
        } else if (y > 0) return x + 0.25f;
        else if (y < 0) return x + 0.75f;
        return x + y; // returns 0 for 0,0 or NaN if either y or x is NaN
    }

    /**
     * Close approximation of the frequently-used trigonometric method atan2, using radians. Average error is
     * 1.057E-6 radians; maximum error is 1.922E-6. Takes y and x (in that unusual order) as
     * doubles, and returns the angle from the origin to that point in radians. It is about 4 times faster than
     * {@link Math#atan2(double, double)} (roughly 15 ns instead of roughly 60 ns for Math, on Java 8 HotSpot).
     * <br>
     * Credit for this goes to the 1955 research study "Approximations for Digital Computers," by RAND Corporation. This is sheet
     * 11's algorithm, which is the fourth-fastest and fourth-least precise. The algorithms on sheets 8-10 are faster, but only by
     * a very small degree, and are considerably less precise. That study provides an {@link #atan(double)} method, and that cleanly
     * translates to atan2().
     *
     * @param y y-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @param x x-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @return the angle to the given point, in radians as a double; ranges from {@code -PI} to {@code PI}
     */
    public static double atan2(final double y, double x) {
        double n = y / x;
        if (n != n)
            n = (y == x ? 1.0 : -1.0); // if both y and x are infinite, n would be NaN
        else if (n - n != n - n) x = 0.0; // if n is infinite, y is infinitely larger than x.
        if (x > 0)
            return atanUnchecked(n);
        else if (x < 0) {
            if (y >= 0) return atanUnchecked(n) + Math.PI;
            return atanUnchecked(n) - Math.PI;
        } else if (y > 0)
            return x + HALF_PI_D;
        else if (y < 0) return x - HALF_PI_D;
        return x + y; // returns 0 for 0,0 or NaN if either y or x is NaN
    }

    /**
     * Close approximation of the frequently-used trigonometric method atan2, using positive or negative degrees.
     * Average absolute error is 0.00006037 degrees; relative error is 0 degrees, maximum error is 0.00010396 degrees.
     * Takes y and x (in that unusual order) as doubles, and returns the angle from the origin to that point in degrees.
     * <br>
     * Credit for this goes to the 1955 research study "Approximations for Digital Computers," by RAND Corporation. This is sheet
     * 11's algorithm, which is the fourth-fastest and fourth-least precise. The algorithms on sheets 8-10 are faster, but only by
     * a very small degree, and are considerably less precise. That study provides an {@link #atan(double)} method, and that cleanly
     * translates to atan2Deg().
     *
     * @param y y-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @param x x-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @return the angle to the given point, in degrees as a double; ranges from {@code -180} to {@code 180}
     */
    public static double atan2Deg(final double y, double x) {
        double n = y / x;
        if (n != n)
            n = (y == x ? 1.0 : -1.0); // if both y and x are infinite, n would be NaN
        else if (n - n != n - n) x = 0.0; // if n is infinite, y is infinitely larger than x.
        if (x > 0)
            return atanUncheckedDeg(n);
        else if (x < 0) {
            if (y >= 0) return atanUncheckedDeg(n) + 180.0;
            return atanUncheckedDeg(n) - 180.0;
        } else if (y > 0)
            return x + 90.0;
        else if (y < 0) return x - 90.0;
        return x + y; // returns 0 for 0,0 or NaN if either y or x is NaN
    }

    /**
     * Close approximation of the frequently-used trigonometric method atan2, using non-negative degrees only.
     * Average absolute error is 0.00006045 degrees; relative error is 0 degrees; maximum error is 0.00011178 degrees.
     * Takes y and x (in that unusual order) as doubles, and returns the angle from the origin to that point in degrees.
     * <br>
     * Credit for this goes to the 1955 research study "Approximations for Digital Computers," by RAND Corporation. This is sheet
     * 11's algorithm, which is the fourth-fastest and fourth-least precise. The algorithms on sheets 8-10 are faster, but only by
     * a very small degree, and are considerably less precise. That study provides an {@link #atan(double)} method, and that cleanly
     * translates to atan2Deg360().
     *
     * @param y y-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @param x x-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @return the angle to the given point, in degrees as a double; ranges from {@code 0} to {@code 360}
     */
    public static double atan2Deg360(final double y, double x) {
        double n = y / x;
        if (n != n)
            n = (y == x ? 1.0 : -1.0); // if both y and x are infinite, n would be NaN
        else if (n - n != n - n) x = 0.0; // if n is infinite, y is infinitely larger than x.
        if (x > 0) {
            if (y >= 0)
                return atanUncheckedDeg(n);
            else
                return atanUncheckedDeg(n) + 360.0;
        } else if (x < 0) {
            return atanUncheckedDeg(n) + 180.0;
        } else if (y > 0) return x + 90.0;
        else if (y < 0) return x + 270.0;
        return x + y; // returns 0 for 0,0 or NaN if either y or x is NaN
    }

    /**
     * Close approximation of the frequently-used trigonometric method atan2, using non-negative turns only.
     * Average absolute error is 0.00000030 turns; relative error is 0 turns; maximum error is 0.00000017 turns.
     * Takes y and x (in that unusual order) as doubles, and returns the angle from the origin to that point in turns.
     * Because this always returns a double between 0.0 (inclusive) and 1.0 (exclusive), it can be useful for various
     * kinds of calculations that must store angles as a small fraction, such as packing a hue angle into a byte.
     * <br>
     * Credit for this goes to the 1955 research study "Approximations for Digital Computers," by RAND Corporation. This is sheet
     * 11's algorithm, which is the fourth-fastest and fourth-least precise. The algorithms on sheets 8-10 are faster, but only by
     * a very small degree, and are considerably less precise. That study provides an {@link #atan(double)} method, and that cleanly
     * translates to atan2Turns().
     *
     * @param y y-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @param x x-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @return the angle to the given point, in turns as a double; ranges from {@code 0.0} to {@code 1.0}
     */
    public static double atan2Turns(final double y, double x) {
        double n = y / x;
        if (n != n)
            n = (y == x ? 1.0 : -1.0); // if both y and x are infinite, n would be NaN
        else if (n - n != n - n) x = 0.0; // if n is infinite, y is infinitely larger than x.
        if (x > 0) {
            if (y >= 0)
                return atanUncheckedTurns(n);
            else
                return atanUncheckedTurns(n) + 1.0;
        } else if (x < 0) {
            return atanUncheckedTurns(n) + 0.5;
        } else if (y > 0) return x + 0.25;
        else if (y < 0) return x + 0.75;
        return x + y; // returns 0 for 0,0 or NaN if either y or x is NaN
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Finite atan2">
    /**
     * A faster approximation of {@link #atan2(float, float)} that is almost as precise as
     * {@link #atan2Precise(float, float)} but is only defined for finite input arguments.
     * The atan2() function takes y first, then x, and returns the angle in radians from
     * the origin to the given point. If y is non-negative, this returns a float from 0.0
     * to {@link #PI}, otherwise it returns a float from -0.0 to -PI.
     * <br>
     * This is both faster and more precise than {@link #atan2(float, float)}, but can't be
     * used as an all-purpose replacement for Math.atan2() because it returns NaN when given
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
    public static float atan2Finite(final float y, final float x) {
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
     * A faster approximation of {@link #atan2Deg(float, float)} that is almost as precise as
     * {@link #atan2DegPrecise(float, float)} but is only defined for finite input arguments.
     * The atan2Deg() function takes y first, then x, and returns the angle in degrees from
     * the origin to the given point. If y is non-negative, this returns a float from 0.0
     * to 180.0 , otherwise it returns a float from -0.0 to -180.0 .
     * <br>
     * This is both faster and more precise than {@link #atan2Deg(float, float)}, but can't be
     * used as an all-purpose replacement for Math.atan2() because it returns NaN when given
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
    public static float atan2DegFinite(final float y, final float x) {
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
     * A faster approximation of {@link #atan2Deg360(float, float)} that is almost as precise as
     * {@link #atan2Deg360Precise(float, float)} but is only defined for finite input arguments.
     * The atan2Deg360() function takes y first, then x, and returns the angle in degrees from
     * the origin to the given point. This returns a float from 0.0 to 360.0, counterclockwise
     * when y points up.
     * <br>
     * This is both faster and more precise than {@link #atan2Deg(float, float)}, but can't be
     * used as an all-purpose replacement for Math.atan2() because it returns NaN when given
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
    public static float atan2Deg360Finite(final float y, final float x) {
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
     * A faster approximation of {@link #atan2Deg360(float, float)} that is almost as precise as
     * {@link #atan2Deg360Precise(float, float)} but is only defined for finite input arguments.
     * The atan2Deg360() function takes y first, then x, and returns the angle in degrees from
     * the origin to the given point. This returns a float from 0.0 to 360.0, counterclockwise
     * when y points up.
     * <br>
     * This is both faster and more precise than {@link #atan2Deg(float, float)}, but can't be
     * used as an all-purpose replacement for Math.atan2() because it returns NaN when given
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
    public static float atan2TurnsFinite(final float y, final float x) {
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

    /**
     * A faster approximation of {@link #atan2(double, double)} that is almost as precise as
     * {@link #atan2Precise(double, double)} but is only defined for finite input arguments.
     * The atan2() function takes y first, then x, and returns the angle in radians from
     * the origin to the given point. If y is non-negative, this returns a double from 0.0
     * to {@link #PI}, otherwise it returns a double from -0.0 to -PI.
     * <br>
     * This is both faster and more precise than {@link #atan2(double, double)}, but can't be
     * used as an all-purpose replacement for Math.atan2() because it returns NaN when given
     * infinite arguments (or NaN). In the undefined case where {@code y == 0.0 && x == 0.0},
     * this returns y (which may be -0.0); this differs from Math.atan2(), which returns
     * {@link Math#PI} when y is 0.0 and x is -0.0.
     * <br>
     * Credit to imuli and Nic Taylor; imuli commented on
     * <a href="https://www.dsprelated.com/showarticle/1052.php">Taylor's article</a> with very useful info.
     * Uses the "Sheet 13" algorithm from "Approximations for Digital Computers," by RAND Corporation (1955)
     * for its atan() approximation over the {@code (0,1]} domain.
     *
     * @param y any finite double; note the unusual argument order (y is first here!)
     * @param x any finite double; note the unusual argument order (x is second here!)
     * @return the angle in radians from the origin to the given point
     */
    public static double atan2Finite(final double y, final double x)
    {
        if (y == 0d && x >= 0d) return y;
        double ay = Math.abs(y), ax = Math.abs(x);
        boolean invert = ay > ax;
        double z = invert ? ax / ay : ay / ax;
        double s = z * z;
        z *= (((((((-0.004054058d * s + 0.0218612288d) * s - 0.0559098861d) * s + 0.0964200441d)
                * s - 0.1390853351d) * s + 0.1994653599d) * s - 0.3332985605d) * s + 0.9999993329d);
        if (invert) z = HALF_PI - z;
        if (x < 0d) z = PI - z;
        return y < 0d ? -z : z;
    }

    /**
     * A faster approximation of {@link #atan2Deg(double, double)} that is almost as precise as
     * {@link #atan2DegPrecise(double, double)} but is only defined for finite input arguments.
     * The atan2Deg() function takes y first, then x, and returns the angle in degrees from
     * the origin to the given point. If y is non-negative, this returns a double from 0.0
     * to 180.0 , otherwise it returns a double from -0.0 to -180.0 .
     * <br>
     * This is both faster and more precise than {@link #atan2Deg(double, double)}, but can't be
     * used as an all-purpose replacement for Math.atan2() because it returns NaN when given
     * infinite arguments (or NaN). In the undefined case where {@code y == 0.0 && x == 0.0},
     * this returns y (which may be -0.0); this differs from Math.atan2(), which returns
     * {@link Math#PI} (in radians) when y is 0.0 and x is -0.0.
     * <br>
     * Credit to imuli and Nic Taylor; imuli commented on
     * <a href="https://www.dsprelated.com/showarticle/1052.php">Taylor's article</a> with very useful info.
     * Uses the "Sheet 13" algorithm from "Approximations for Digital Computers," by RAND Corporation (1955)
     * for its atan() approximation over the {@code (0,1]} domain.
     *
     * @param y any finite double; note the unusual argument order (y is first here!)
     * @param x any finite double; note the unusual argument order (x is second here!)
     * @return the angle in degrees from the origin to the given point
     */
    public static double atan2DegFinite(final double y, final double x)
    {
        if (y == 0d && x >= 0d) return y;
        double ay = Math.abs(y), ax = Math.abs(x);
        boolean invert = ay > ax;
        double z = invert ? ax / ay : ay / ax;
        double s = z * z;
        z *= ((((((-0.2322804062831325d * s + 1.2525561619334924d) * s - 3.2034005556446465d) * s + 5.52446147949459d)
                * s - 7.969002832028255d) * s + 11.428523528717331d) * s - 19.09660103251952d) * s + 57.29574194704188d;
        if (invert) z = 90d - z;
        if (x < 0d) z = 180d - z;
        return y < 0d ? -z : z;
    }

    /**
     * A faster approximation of {@link #atan2Deg360(double, double)} that is almost as precise as
     * {@link #atan2Deg360Precise(double, double)} but is only defined for finite input arguments.
     * The atan2Deg360() function takes y first, then x, and returns the angle in degrees from
     * the origin to the given point. This returns a double from 0.0 to 360.0, counterclockwise
     * when y points up.
     * <br>
     * This is both faster and more precise than {@link #atan2Deg(double, double)}, but can't be
     * used as an all-purpose replacement for Math.atan2() because it returns NaN when given
     * infinite arguments (or NaN). In the undefined case where {@code y == 0.0 && x == 0.0},
     * this returns 0.0; this differs from Math.atan2(), which returns
     * {@link Math#PI} (in radians) when y is 0.0 and x is -0.0.
     * <br>
     * Credit to imuli and Nic Taylor; imuli commented on
     * <a href="https://www.dsprelated.com/showarticle/1052.php">Taylor's article</a> with very useful info.
     * Uses the "Sheet 13" algorithm from "Approximations for Digital Computers," by RAND Corporation (1955)
     * for its atan() approximation over the {@code (0,1]} domain.
     *
     * @param y any finite double; note the unusual argument order (y is first here!)
     * @param x any finite double; note the unusual argument order (x is second here!)
     * @return the angle in degrees from the origin to the given point, from 0 to 360
     */
    public static double atan2Deg360Finite(final double y, final double x)
    {
        if (y == 0d && x >= 0d) return 0d;
        double ay = Math.abs(y), ax = Math.abs(x);
        boolean invert = ay > ax;
        double z = invert ? ax / ay : ay / ax;
        double s = z * z;
        z *= ((((((-0.2322804062831325d * s + 1.2525561619334924d) * s - 3.2034005556446465d) * s + 5.52446147949459d) * s - 7.969002832028255d)
                * s + 11.428523528717331d) * s - 19.09660103251952d) * s + 57.29574194704188d;
        if (invert) z = 90d - z;
        if (x < 0d) z = 180d - z;
        return y < 0d ? 360d - z : z;
    }

    /**
     * A faster approximation of {@link #atan2Deg360(double, double)} that is almost as precise as
     * {@link #atan2Deg360Precise(double, double)} but is only defined for finite input arguments.
     * The atan2Deg360() function takes y first, then x, and returns the angle in degrees from
     * the origin to the given point. This returns a double from 0.0 to 360.0, counterclockwise
     * when y points up.
     * <br>
     * This is both faster and more precise than {@link #atan2Deg(double, double)}, but can't be
     * used as an all-purpose replacement for Math.atan2() because it returns NaN when given
     * infinite arguments (or NaN). In the undefined case where {@code y == 0.0 && x == 0.0},
     * this returns 0.0; this differs from Math.atan2(), which returns
     * {@link Math#PI} (in radians) when y is 0.0 and x is -0.0.
     * <br>
     * Credit to imuli and Nic Taylor; imuli commented on
     * <a href="https://www.dsprelated.com/showarticle/1052.php">Taylor's article</a> with very useful info.
     * Uses the "Sheet 13" algorithm from "Approximations for Digital Computers," by RAND Corporation (1955)
     * for its atan() approximation over the {@code (0,1]} domain.
     *
     * @param y any finite double; note the unusual argument order (y is first here!)
     * @param x any finite double; note the unusual argument order (x is second here!)
     * @return the angle in degrees from the origin to the given point, from 0 to 360
     */
    public static double atan2TurnsFinite(final double y, final double x)
    {
        if (y == 0d && x >= 0d) return 0d;
        double ay = Math.abs(y), ax = Math.abs(x);
        boolean invert = ay > ax;
        double z = invert ? ax / ay : ay / ax;
        double s = z * z;
        z *= (((((((-6.452233507864792E-4d * s + 0.003479322672037479d) * s - 0.008898334876790684d) * s + 0.015345726331929417d) * s - 0.022136118977856264d)
                * s + 0.03174589869088148d) * s - 0.05304611397922089d) * s + 0.15915483874178302d);
        if (invert) z = 0.25d - z;
        if (x < 0d) z = 0.5d - z;
        return y < 0d ? 1d - z : z;
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Precise Arctangent and atan2">
    /**
     * Close approximation of the frequently-used trigonometric method atan2, using radians. Non-tabular.
     * Takes y and x (in that unusual order) as doubles, and returns the angle from the origin to that point in radians.
     * It is about 2x times faster than {@link Math#atan2(double, double)} (roughly 4.01 ns instead of roughly 7.965 ns
     * for Math, on Java 23 HotSpot).
     * <br>
     * This is a simple conditional scaffold around {@link #atanPrecise(double)}, calling it if x is non-zero and non-NaN,
     * or otherwise returning a value that can be computed more quickly.
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     *
     * @param y y-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @param x x-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @return the angle to the given point, in radians as a double; ranges from {@code -PI} to {@code PI}
     */
    public static double atan2Precise(final double y, double x) {
        double n = y / x;
        if (n != n)
            n = (y == x ? 1.0 : -1.0); // if both y and x are infinite, n would be NaN
        else if (n - n != n - n) x = 0.0; // if n is infinite, y is infinitely larger than x.
        if (x > 0)
            return atanPrecise(n);
        else if (x < 0) {
            if (y >= 0) return (atanPrecise(n) + Math.PI);
            return (atanPrecise(n) - Math.PI);
        } else if (y > 0)
            return x + HALF_PI_D;
        else if (y < 0) return x - HALF_PI_D;
        return x + y; // returns 0 for 0,0 or NaN if either y or x is NaN
    }

    /**
     * Close approximation of the frequently-used trigonometric method atan2, using radians. Non-tabular.
     * Takes y and x (in that unusual order) as floats, and returns the angle from the origin to that point in radians.
     * It is about 2x times faster than {@link Math#atan2(double, double)} (roughly 3.987 ns instead of roughly 8.523 ns
     * for Math, on Java 23 HotSpot, with both taking float arguments and casting the result to float).
     * <br>
     * This is a simple conditional scaffold around {@link #atanPrecise(float)}, calling it if x is non-zero and non-NaN,
     * or otherwise returning a value that can be computed more quickly.
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     *
     * @param y y-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @param x x-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @return the angle to the given point, in radians as a float; ranges from {@code -PI} to {@code PI}
     */
    public static float atan2Precise(final float y, float x) {
        float n = y / x;
        if (n != n)
            n = (y == x ? 1f : -1f); // if both y and x are infinite, n would be NaN
        else if (n - n != n - n) x = 0f; // if n is infinite, y is infinitely larger than x.
        if (x > 0)
            return atanPrecise(n);
        else if (x < 0) {
            if (y >= 0) return atanPrecise(n) + TrigTools.PI;
            return atanPrecise(n) - TrigTools.PI;
        } else if (y > 0)
            return x + HALF_PI;
        else if (y < 0) return x - HALF_PI;
        return x + y; // returns 0 for 0,0 or NaN if either y or x is NaN
    }

    /**
     * A non-tabular approximation for arctangent in radians, accurate to at worst 2 ULPs for the -50 to 50 range.
     * Takes and returns a float.
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     *
     * @param n may be any float
     * @return the arctangent of n in radians, from negative {@link TrigTools#HALF_PI} to {@link TrigTools#HALF_PI}
     */
    public static float atanPrecise(float n) {
        float m = Math.abs(n), x, y;
        if (m > 2.414213562373095f) {
            x = -1f / m;
            y = HALF_PI;
        } else if (m > 0.4142135623730950f) {
            x = (m - 1f) / (m + 1f);
            y = QUARTER_PI;
        } else {
            x = m;
            y = 0f;
        }
        float z = x * x;
        return Math.copySign(y + (((8.05374449538e-2f * z - 1.38776856032e-1f) * z + 1.99777106478e-1f)
                * z - 3.33329491539e-1f) * z * x + x, n);
    }

    /**
     * A non-tabular approximation for arctangent in radians.
     * Takes and returns a double.
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     *
     * @param n may be any float
     * @return the arctangent of n in radians, from negative {@link TrigTools#HALF_PI_D} to {@link TrigTools#HALF_PI_D}
     */
    public static double atanPrecise(double n) {
        double m = Math.abs(n), x, y;
        if (m > 2.414213562373095) {
            x = -1. / m;
            y = HALF_PI_D;
        } else if (m > 0.4142135623730950) {
            x = (m - 1.) / (m + 1.);
            y = QUARTER_PI_D;
        } else {
            x = m;
            y = 0.;
        }
        double z = x * x;
        return Math.copySign(y + (((8.05374449538e-2 * z - 1.38776856032e-1) * z + 1.99777106478e-1)
                * z - 3.33329491539e-1) * z * x + x, n);
    }

    /**
     * Close approximation of the frequently-used trigonometric method atan2, using degrees. Non-tabular.
     * Takes y and x (in that unusual order) as doubles, and returns the angle from the origin to that point in degrees.
     * <br>
     * This is a simple conditional scaffold around {@link #atanDegPrecise(double)}, calling it if x is non-zero and
     * non-NaN, or otherwise returning a value that can be computed more quickly.
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     *
     * @param y y-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @param x x-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @return the angle to the given point, in degrees as a double; ranges from {@code -180} to {@code 180}
     */
    public static double atan2DegPrecise(final double y, double x) {
        double n = y / x;
        if (n != n)
            n = (y == x ? 1.0 : -1.0); // if both y and x are infinite, n would be NaN
        else if (n - n != n - n) x = 0.0; // if n is infinite, y is infinitely larger than x.
        if (x > 0)
            return atanDegPrecise(n);
        else if (x < 0) {
            if (y >= 0) return (atanDegPrecise(n) + 180.0);
            return (atanDegPrecise(n) - 180.0);
        } else if (y > 0)
            return x + 90.0;
        else if (y < 0) return x - 90.0;
        return x + y; // returns 0 for 0,0 or NaN if either y or x is NaN
    }

    /**
     * Close approximation of the frequently-used trigonometric method atan2, using degrees. Non-tabular.
     * Takes y and x (in that unusual order) as floats, and returns the angle from the origin to that point in degrees.
     * <br>
     * This is a simple conditional scaffold around {@link #atanDegPrecise(float)}, calling it if x is non-zero and
     * non-NaN, or otherwise returning a value that can be computed more quickly.
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     *
     * @param y y-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @param x x-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @return the angle to the given point, in degrees as a float; ranges from {@code -180} to {@code 180}
     */
    public static float atan2DegPrecise(final float y, float x) {
        float n = y / x;
        if (n != n)
            n = (y == x ? 1f : -1f); // if both y and x are infinite, n would be NaN
        else if (n - n != n - n) x = 0f; // if n is infinite, y is infinitely larger than x.
        if (x > 0)
            return atanDegPrecise(n);
        else if (x < 0) {
            if (y >= 0) return atanDegPrecise(n) + 180f;
            return atanDegPrecise(n) - 180f;
        } else if (y > 0)
            return x + 90f;
        else if (y < 0) return x - 90f;
        return x + y; // returns 0 for 0,0 or NaN if either y or x is NaN
    }

    /**
     * Close approximation of the frequently-used trigonometric method atan2, using degrees, but never returning a
     * negative result. Non-tabular.
     * Takes y and x (in that unusual order) as doubles, and returns the angle from the origin to that point in degrees
     * from 0 to 360 inclusive.
     * <br>
     * This is a simple conditional scaffold around {@link #atanDegPrecise(double)}, calling it if x is non-zero and
     * non-NaN, or otherwise returning a value that can be computed more quickly.
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     *
     * @param y y-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @param x x-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @return the angle to the given point, in degrees as a non-negative double; ranges from {@code 0} to {@code 360}
     */
    public static double atan2Deg360Precise(final double y, double x) {
        double n = y / x;
        if (n != n)
            n = (y == x ? 1.0 : -1.0); // if both y and x are infinite, n would be NaN
        else if (n - n != n - n) x = 0.0; // if n is infinite, y is infinitely larger than x.
        if (x > 0) {
            if (y >= 0) return atanDegPrecise(n);
            else return (atanDegPrecise(n) + 360.0);
        } else if (x < 0) return (atanDegPrecise(n) + 180.0);
        else if (y > 0) return x + 90.0;
        else if (y < 0) return x + 270.0;
        return x + y + 0.0; // returns 0 for 0,0 or NaN if either y or x is NaN
    }

    /**
     * Close approximation of the frequently-used trigonometric method atan2, using degrees, but never returning a
     * negative result. Non-tabular.
     * Takes y and x (in that unusual order) as floats, and returns the angle from the origin to that point in degrees
     * from 0 to 360 inclusive.
     * <br>
     * This is a simple conditional scaffold around {@link #atanDegPrecise(float)}, calling it if x is non-zero and
     * non-NaN, or otherwise returning a value that can be computed more quickly.
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     *
     * @param y y-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @param x x-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @return the angle to the given point, in degrees as a non-negative float; ranges from {@code 0} to {@code 360}
     */
    public static float atan2Deg360Precise(final float y, float x) {
        float n = y / x;
        if (n != n)
            n = (y == x ? 1f : -1f); // if both y and x are infinite, n would be NaN
        else if (n - n != n - n) x = 0f; // if n is infinite, y is infinitely larger than x.
        if (x > 0) {
            if (y >= 0) return atanDegPrecise(n);
            else return atanDegPrecise(n) + 360f;
        } else if (x < 0) return atanDegPrecise(n) + 180f;
        else if (y > 0) return x + 90f;
        else if (y < 0) return x + 270f;
        return x + y + 0f; // returns 0 for 0,0 or NaN if either y or x is NaN
    }

    /**
     * A non-tabular approximation for arctangent in degrees, accurate to at worst 129 ULPs for the -50 to 50 range.
     * Takes and returns a float.
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     *
     * @param n may be any float
     * @return the arctangent of n in degrees, from {@code -90} to {@code 90}
     */
    public static float atanDegPrecise(float n) {
        float m = Math.abs(n), x, y;

        if(m > 2.414213562373095f){
            x = -1f / m;
            y = 90f;
        } else if(m > 0.4142135623730950f){
            x = (m - 1f) / (m + 1f);
            y = 45f;
        } else {
            x = m;
            y = 0f;
        }
        float z = x * x;
        return Math.copySign(y + ((((8.05374449538e-2f * z - 1.38776856032e-1f) * z + 1.99777106478e-1f)
                * z - 3.33329491539e-1f) * z * x + x) * 57.29577951308232f, n);
    }

    /**
     * A non-tabular approximation for arctangent in degrees.
     * Takes and returns a double.
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     *
     * @param n may be any double
     * @return the arctangent of n in degrees, from {@code -90} to {@code 90}
     */
    public static double atanDegPrecise(double n) {
        double m = Math.abs(n), x, y;
        if(m > 2.414213562373095){
            x = -1. / m;
            y = 90.0;
        } else if(m > 0.4142135623730950){
            x = (m - 1.) / (m + 1.);
            y = 45.0;
        } else {
            x = m;
            y = 0.;
        }
        double z = x * x;
        return Math.copySign(y + ((((8.05374449538e-2 * z - 1.38776856032e-1) * z + 1.99777106478e-1)
                * z - 3.33329491539e-1) * z * x + x) * 57.29577951308232, n);
    }

    /**
     * Close approximation of the frequently-used trigonometric method atan2, using turns, and never returning a
     * negative result. Non-tabular.
     * Takes y and x (in that unusual order) as doubles, and returns the angle from the origin to that point in turns
     * from 0.0 to 1.0 inclusive.
     * <br>
     * This is a simple conditional scaffold around {@link #atanTurnsPrecise(double)}, calling it if x is non-zero and
     * non-NaN, or otherwise returning a value that can be computed more quickly.
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     *
     * @param y y-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @param x x-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @return the angle to the given point, in turns as a non-negative double; ranges from {@code 0.0} to {@code 1.0}
     */
    public static double atan2TurnsPrecise(final double y, double x) {
        double n = y / x;
        if (n != n)
            n = (y == x ? 1.0 : -1.0); // if both y and x are infinite, n would be NaN
        else if (n - n != n - n) x = 0.0; // if n is infinite, y is infinitely larger than x.
        if (x > 0) {
            if (y >= 0)
                return atanTurnsPrecise(n);
            else
                return atanTurnsPrecise(n) + 1.0;
        } else if (x < 0) {
            return atanTurnsPrecise(n) + 0.5;
        } else if (y > 0) return x + 0.25;
        else if (y < 0) return x + 0.75;
        return x + y + 0.0; // returns 0 for 0,0 or NaN if either y or x is NaN
    }

    /**
     * Close approximation of the frequently-used trigonometric method atan2, using turns, and never returning a
     * negative result. Non-tabular.
     * Takes y and x (in that unusual order) as floats, and returns the angle from the origin to that point in turns
     * from 0.0 to 1.0 inclusive.
     * <br>
     * This is a simple conditional scaffold around {@link #atanTurnsPrecise(float)}, calling it if x is non-zero and
     * non-NaN, or otherwise returning a value that can be computed more quickly.
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     *
     * @param y y-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @param x x-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @return the angle to the given point, in turns as a non-negative float; ranges from {@code 0f} to {@code 1f}
     */
    public static float atan2TurnsPrecise(final float y, float x) {
        float n = y / x;
        if (n != n)
            n = (y == x ? 1f : -1f); // if both y and x are infinite, n would be NaN
        else if (n - n != n - n) x = 0f; // if n is infinite, y is infinitely larger than x.
        if (x > 0) {
            if (y >= 0)
                return atanTurnsPrecise(n);
            else
                return atanTurnsPrecise(n) + 1.0f;
        } else if (x < 0) {
            return atanTurnsPrecise(n) + 0.5f;
        } else if (y > 0) return x + 0.25f;
        else if (y < 0) return x + 0.75f;
        return x + y + 0f; // returns 0 for 0,0 or NaN if either y or x is NaN
    }

    /**
     * A non-tabular approximation for arctangent in turns, accurate to at worst 2 ULPs for the -50 to 50 range.
     * Takes and returns a float.
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     *
     * @param n may be any float
     * @return the arctangent of n in turns, from {@code -0.25} to {@code 0.25}
     */
    public static float atanTurnsPrecise(float n) {
        float m = Math.abs(n), x, y;

        if(m > 2.414213562373095f){
            x = -1f / m;
            y = 0.25f;
        } else if(m > 0.4142135623730950f){
            x = (m - 1f) / (m + 1f);
            y = 0.125f;
        } else {
            x = m;
            y = 0f;
        }
        float z = x * x;
        return Math.copySign(y + ((((8.05374449538e-2f * z - 1.38776856032e-1f) * z + 1.99777106478e-1f)
                * z - 3.33329491539e-1f) * z * x + x) * 0.15915494309189535f, n);
    }

    /**
     * A non-tabular approximation for arctangent in turns.
     * Takes and returns a double.
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     *
     * @param n may be any double
     * @return the arctangent of n in turns, from {@code -0.25} to {@code 0.25}
     */
    public static double atanTurnsPrecise(double n) {
        double m = Math.abs(n), x, y;
        if(m > 2.414213562373095){
            x = -1. / m;
            y = 0.25;
        } else if(m > 0.4142135623730950){
            x = (m - 1.) / (m + 1.);
            y = 0.125;
        } else {
            x = m;
            y = 0.;
        }
        double z = x * x;
        return Math.copySign(y + ((((8.05374449538e-2 * z - 1.38776856032e-1) * z + 1.99777106478e-1)
                * z - 3.33329491539e-1) * z * x + x) * 0.15915494309189535, n);
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

    /**
     * Returns arcsine in radians; less accurate than Math.asin but may be faster. Average error of 0.000028447 radians (0.0016298931
     * degrees), largest error of 0.000067592 radians (0.0038727364 degrees). This implementation does not return NaN if given an
     * out-of-range input (Math.asin does return NaN), unless the input is NaN.
     *
     * @param a asin is defined only when a is between -1.0 and 1.0, inclusive
     * @return between {@code -HALF_PI} and {@code HALF_PI} when a is in the defined range
     */
    public static double asin(double a) {
        if (a >= 0.0) {
            return HALF_PI_D - Math.sqrt(1.0 - a) * (1.5707288 + a * (-0.2121144 + a * (0.0742610 + a * -0.0187293)));
        }
        return Math.sqrt(1.0 + a) * (1.5707288 + a * (0.2121144 + a * (0.0742610 + a * 0.0187293))) - HALF_PI_D;
    }

    /**
     * Returns arcsine in degrees. This implementation does not return NaN if given an
     * out-of-range input (Math.asin does return NaN), unless the input is NaN.
     *
     * @param a asin is defined only when a is between -1.0 and 1.0, inclusive
     * @return between {@code -90} and {@code 90} when a is in the defined range
     */
    public static double asinDeg(double a) {
        if (a >= 0.0) {
            return 90.0 - Math.sqrt(1.0 - a) * (89.99613099964837 + a * (-12.153259893949748 + a * (4.2548418824210055 + a * -1.0731098432343729)));
        }
        return Math.sqrt(1.0 + a) * (89.99613099964837 + a * (12.153259893949748 + a * (4.2548418824210055 + a * 1.0731098432343729))) - 90.0;
    }

    /**
     * Returns arcsine in turns. This implementation does not return NaN if given an
     * out-of-range input (Math.asin does return NaN), unless the input is NaN.
     * Note that unlike {@link #atan2Turns(double, double)}, this can return negative turn values.
     *
     * @param a asin is defined only when a is between -1.0 and 1.0, inclusive
     * @return between {@code -0.25} and {@code 0.25} when a is in the defined range
     */
    public static double asinTurns(double a) {
        if (a >= 0.0) {
            return 0.25 - Math.sqrt(1.0 - a) * (0.24998925277680104 + a * (-0.033759055260971525 + a * (0.011819005228947238 + a * -0.0029808606756510357)));
        }
        return Math.sqrt(1.0 + a) * (0.24998925277680104 + a * (0.033759055260971525 + a * (0.011819005228947238 + a * 0.0029808606756510357))) - 0.25;
    }

    /**
     * Returns arccosine in radians; less accurate than Math.acos but may be faster. Average error of 0.00002845 radians (0.0016300649
     * degrees), largest error of 0.000067548 radians (0.0038702153 degrees). This implementation does not return NaN if given an
     * out-of-range input (Math.acos does return NaN), unless the input is NaN.
     *
     * @param a acos is defined only when a is between -1.0 and 1.0, inclusive
     * @return between {@code 0} and {@code PI} when a is in the defined range
     */
    public static double acos(double a) {
        if (a >= 0.0) {
            return Math.sqrt(1.0 - a) * (1.5707288 + a * (-0.2121144 + a * (0.0742610 + a * -0.0187293)));
        }
        return Math.PI
                - Math.sqrt(1.0 + a) * (1.5707288 + a * (0.2121144 + a * (0.0742610 + a * 0.0187293)));
    }

    /**
     * Returns arccosine in degrees. This implementation does not return NaN if given an
     * out-of-range input (Math.acos does return NaN), unless the input is NaN.
     *
     * @param a acos is defined only when a is between -1.0 and 1.0, inclusive
     * @return between {@code 0} and {@code 180} when a is in the defined range
     */
    public static double acosDeg(double a) {
        if (a >= 0.0) {
            return Math.sqrt(1.0 - a) * (89.99613099964837 + a * (-12.153259533621753 + a * (4.254842010910525 + a * -1.0731098035209208)));
        }
        return 180.0
                - Math.sqrt(1.0 + a) * (89.99613099964837 + a * (12.153259533621753 + a * (4.254842010910525 + a * 1.0731098035209208)));
    }

    /**
     * Returns arccosine in turns. This implementation does not return NaN if given an
     * out-of-range input (Math.acos does return NaN), unless the input is NaN.
     *
     * @param a acos is defined only when a is between -1.0 and 1.0, inclusive
     * @return between {@code 0} and {@code 0.5} when a is in the defined range
     */
    public static double acosTurns(double a) {
        if (a >= 0.0) {
            return Math.sqrt(1.0 - a) * (0.24998925277680104 + a * (-0.033759055260971525 + a * (0.011819005228947238 + a * -0.0029808606756510357)));
        }
        return 0.5 - Math.sqrt(1.0 + a) * (0.24998925277680104 + a * (0.033759055260971525 + a * (0.011819005228947238 + a * 0.0029808606756510357)));
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Precise Arcsine and Arccosine">
    /**
     * Returns arcsine in radians; non-tabular, almost as accurate as Math.asin() and may be slightly faster.
     * This has a maximum error of 2 ULPs in the defined range of -1 to 1.
     * This implementation does not return NaN if given an out-of-range input (Math.asin does return NaN), unless the
     * input is NaN.
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     *
     * @param n asin is defined only when n is between -1f and 1f, inclusive
     * @return between {@code -HALF_PI} and {@code HALF_PI} when n is in the defined range
     */
    public static float asinPrecise(float n) {
        float a = Math.min(1f, Math.abs(n)), z, x, r;
        if(a <= 0.5f){
            z = a * a;
            x = a;
            r = ((((4.2163199048e-2f * z + 2.4181311049e-2f) * z + 4.5470025998e-2f) * z + 7.4953002686e-2f) * z + 1.6666752422e-1f) * z * x + x;
        } else {
            z = 0.5f - 0.5f * a;
            x = (float) Math.sqrt(z);
            r = TrigTools.HALF_PI - 2f * (((((4.2163199048e-2f * z + 2.4181311049e-2f) * z + 4.5470025998e-2f) * z + 7.4953002686e-2f) * z + 1.6666752422e-1f) * z * x + x);
        }
        return Math.copySign(r, n);
    }

    /**
     * Returns arcsine in radians; non-tabular, almost as accurate as Math.asin() and may be slightly faster.
     * This implementation does not return NaN if given an out-of-range input (Math.asin does return NaN), unless the
     * input is NaN.
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     *
     * @param n asin is defined only when n is between -1.0 and 1.0, inclusive
     * @return between {@code -HALF_PI_D} and {@code HALF_PI_D} when n is in the defined range
     */
    public static double asinPrecise(double n) {
        double a = Math.min(1.0, Math.abs(n)), z, x, r;
        if(a <= 0.5){
            z = a * a;
            x = a;
            r = ((((4.2163199048e-2 * z + 2.4181311049e-2) * z + 4.5470025998e-2) * z + 7.4953002686e-2) * z + 1.6666752422e-1) * z * x + x;
        } else {
            z = 0.5 - 0.5 * a;
            x = Math.sqrt(z);
            r = HALF_PI_D - 2.0 * (((((4.2163199048e-2 * z + 2.4181311049e-2) * z + 4.5470025998e-2) * z + 7.4953002686e-2) * z + 1.6666752422e-1) * z * x + x);
        }
        return Math.copySign(r, n);
    }

    /**
     * Returns arccosine in radians; non-tabular, almost as accurate as Math.acos() and may be slightly faster.
     * This has a maximum error of 247 ULPs in the defined range of -1 to 1.
     * This implementation does not return NaN if given an out-of-range input (Math.acos does return NaN), unless the
     * input is NaN.
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     *
     * @param n acos is defined only when n is between -1f and 1f, inclusive
     * @return between {@code 0} and {@code PI} when n is in the defined range
     */
    public static float acosPrecise(float n) {
        float a = Math.min(1f, Math.abs(n)), z, x, r;
        if(a <= 0.5f){
            z = a * a;
            x = a;
            r = ((((4.2163199048e-2f * z + 2.4181311049e-2f) * z + 4.5470025998e-2f) * z + 7.4953002686e-2f) * z + 1.6666752422e-1f) * z * x + x;
        } else {
            z = 0.5f - 0.5f * a;
            x = (float) Math.sqrt(z);
            r = TrigTools.HALF_PI - 2f * (((((4.2163199048e-2f * z + 2.4181311049e-2f) * z + 4.5470025998e-2f) * z + 7.4953002686e-2f) * z + 1.6666752422e-1f) * z * x + x);
        }
        return TrigTools.HALF_PI - Math.copySign(r, n);
    }

    /**
     * Returns arccosine in radians; non-tabular, almost as accurate as Math.acos() and may be slightly faster.
     * This implementation does not return NaN if given an out-of-range input (Math.acos does return NaN), unless the
     * input is NaN.
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     *
     * @param n acos is defined only when n is between -1.0 and 1.0, inclusive
     * @return between {@code 0.0} and {@code PI_D} when n is in the defined range
     */
    public static double acosPrecise(double n) {
        double a = Math.min(1.0, Math.abs(n)), z, x, r;
        if(a <= 0.5){
            z = a * a;
            x = a;
            r = ((((4.2163199048e-2 * z + 2.4181311049e-2) * z + 4.5470025998e-2) * z + 7.4953002686e-2) * z + 1.6666752422e-1) * z * x + x;
        } else {
            z = 0.5 - 0.5 * a;
            x = Math.sqrt(z);
            r = HALF_PI_D - 2.0 * (((((4.2163199048e-2 * z + 2.4181311049e-2) * z + 4.5470025998e-2) * z + 7.4953002686e-2) * z + 1.6666752422e-1) * z * x + x);
        }
        return HALF_PI_D - Math.copySign(r, n);
    }

    /**
     * Returns arcsine in degrees; non-tabular, almost as accurate as Math.asin() and may be slightly faster.
     * This implementation does not return NaN if given an out-of-range input (Math.asin does return NaN), unless the
     * input is NaN.
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     *
     * @param n asin is defined only when n is between -1f and 1f, inclusive
     * @return between {@code -90f} and {@code 90f} when n is in the defined range
     */
    public static float asinDegPrecise(float n) {
        float a = Math.min(1f, Math.abs(n)), z, x, r;
        if(a <= 0.5f){
            z = a * a;
            x = a;
            r = (90f / HALF_PI) * (((((4.2163199048e-2f * z + 2.4181311049e-2f) * z + 4.5470025998e-2f) * z + 7.4953002686e-2f) * z + 1.6666752422e-1f) * z * x + x);
        } else {
            z = 0.5f - 0.5f * a;
            x = (float) Math.sqrt(z);
            r = 90f - (180f / HALF_PI) * (((((4.2163199048e-2f * z + 2.4181311049e-2f) * z + 4.5470025998e-2f) * z + 7.4953002686e-2f) * z + 1.6666752422e-1f) * z * x + x);
        }
        return Math.copySign(r, n);
    }

    /**
     * Returns arcsine in degrees; non-tabular, almost as accurate as Math.asin() and may be slightly faster.
     * This implementation does not return NaN if given an out-of-range input (Math.asin does return NaN), unless the
     * input is NaN.
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     *
     * @param n asin is defined only when n is between -1.0 and 1.0, inclusive
     * @return between {@code -90.0} and {@code 90.0} when n is in the defined range
     */
    public static double asinDegPrecise(double n) {
        double a = Math.min(1.0, Math.abs(n)), z, x, r;
        if(a <= 0.5){
            z = a * a;
            x = a;
            r = (90.0 / HALF_PI_D) * (((((4.2163199048e-2 * z + 2.4181311049e-2) * z + 4.5470025998e-2) * z + 7.4953002686e-2) * z + 1.6666752422e-1) * z * x + x);
        } else {
            z = 0.5 - 0.5 * a;
            x = Math.sqrt(z);
            r = 90.0 - (180.0 / HALF_PI_D) * (((((4.2163199048e-2 * z + 2.4181311049e-2) * z + 4.5470025998e-2) * z + 7.4953002686e-2) * z + 1.6666752422e-1) * z * x + x);
        }
        return Math.copySign(r, n);
    }

    /**
     * Returns arccosine in degrees; non-tabular, almost as accurate as Math.acos() and may be slightly faster.
     * This implementation does not return NaN if given an out-of-range input (Math.acos does return NaN), unless the
     * input is NaN.
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     *
     * @param n acos is defined only when n is between -1f and 1f, inclusive
     * @return between {@code 0f} and {@code 180f} when n is in the defined range
     */
    public static float acosDegPrecise(float n) {
        float a = Math.min(1f, Math.abs(n)), z, x, r;
        if(a <= 0.5f){
            z = a * a;
            x = a;
            r = (90f / HALF_PI) * (((((4.2163199048e-2f * z + 2.4181311049e-2f) * z + 4.5470025998e-2f) * z + 7.4953002686e-2f) * z + 1.6666752422e-1f) * z * x + x);
        } else {
            z = 0.5f - 0.5f * a;
            x = (float) Math.sqrt(z);
            r = 90f - (180f / HALF_PI) * (((((4.2163199048e-2f * z + 2.4181311049e-2f) * z + 4.5470025998e-2f) * z + 7.4953002686e-2f) * z + 1.6666752422e-1f) * z * x + x);
        }
        return 90f - Math.copySign(r, n);
    }

    /**
     * Returns arccosine in degrees; non-tabular, almost as accurate as Math.acos() and may be slightly faster.
     * This implementation does not return NaN if given an out-of-range input (Math.acos does return NaN), unless the
     * input is NaN.
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     *
     * @param n acos is defined only when n is between -1.0 and 1.0, inclusive
     * @return between {@code 0.0} and {@code 180.0} when n is in the defined range
     */
    public static double acosDegPrecise(double n) {
        double a = Math.min(1.0, Math.abs(n)), z, x, r;
        if(a <= 0.5){
            z = a * a;
            x = a;
            r = (90.0 / HALF_PI_D) * (((((4.2163199048e-2 * z + 2.4181311049e-2) * z + 4.5470025998e-2) * z + 7.4953002686e-2) * z + 1.6666752422e-1) * z * x + x);
        } else {
            z = 0.5 - 0.5 * a;
            x = Math.sqrt(z);
            r = 90.0 - (180.0 / HALF_PI_D) * (((((4.2163199048e-2 * z + 2.4181311049e-2) * z + 4.5470025998e-2) * z + 7.4953002686e-2) * z + 1.6666752422e-1) * z * x + x);
        }
        return 90.0 - Math.copySign(r, n);
    }

    /**
     * Returns arcsine in turns; non-tabular, almost as accurate as Math.asin() and may be slightly faster.
     * This implementation does not return NaN if given an out-of-range input (Math.asin does return NaN), unless the
     * input is NaN.
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     *
     * @param n asin is defined only when n is between -1f and 1f, inclusive
     * @return between {@code -0.25f} and {@code 0.25f} when n is in the defined range
     */
    public static float asinTurnsPrecise(float n) {
        float a = Math.min(1f, Math.abs(n)), z, x, r;
        if(a <= 0.5f){
            z = a * a;
            x = a;
            r = (0.25f / HALF_PI) * (((((4.2163199048e-2f * z + 2.4181311049e-2f) * z + 4.5470025998e-2f) * z + 7.4953002686e-2f) * z + 1.6666752422e-1f) * z * x + x);
        } else {
            z = 0.5f - 0.5f * a;
            x = (float) Math.sqrt(z);
            r = 0.25f - (0.5f / HALF_PI) * (((((4.2163199048e-2f * z + 2.4181311049e-2f) * z + 4.5470025998e-2f) * z + 7.4953002686e-2f) * z + 1.6666752422e-1f) * z * x + x);
        }
        return Math.copySign(r, n);
    }

    /**
     * Returns arcsine in turns; non-tabular, almost as accurate as Math.asin() and may be slightly faster.
     * This implementation does not return NaN if given an out-of-range input (Math.asin does return NaN), unless the
     * input is NaN.
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     *
     * @param n asin is defined only when n is between -1.0 and 1.0, inclusive
     * @return between {@code -0.25} and {@code 0.25} when n is in the defined range
     */
    public static double asinTurnsPrecise(double n) {
        double a = Math.min(1.0, Math.abs(n)), z, x, r;
        if(a <= 0.5){
            z = a * a;
            x = a;
            r = (0.25 / HALF_PI_D) * (((((4.2163199048e-2 * z + 2.4181311049e-2) * z + 4.5470025998e-2) * z + 7.4953002686e-2) * z + 1.6666752422e-1) * z * x + x);
        } else {
            z = 0.5 - 0.5 * a;
            x = Math.sqrt(z);
            r = 0.25 - (0.5 / HALF_PI_D) * (((((4.2163199048e-2 * z + 2.4181311049e-2) * z + 4.5470025998e-2) * z + 7.4953002686e-2) * z + 1.6666752422e-1) * z * x + x);
        }
        return Math.copySign(r, n);
    }

    /**
     * Returns arccosine in turns; non-tabular, almost as accurate as Math.acos() and may be slightly faster.
     * This implementation does not return NaN if given an out-of-range input (Math.acos does return NaN), unless the
     * input is NaN.
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     *
     * @param n acos is defined only when n is between -1f and 1f, inclusive
     * @return between {@code 0f} and {@code 0.5f} when n is in the defined range
     */
    public static float acosTurnsPrecise(float n) {
        float a = Math.min(1f, Math.abs(n)), z, x, r;
        if(a <= 0.5f){
            z = a * a;
            x = a;
            r = (0.25f / HALF_PI) * (((((4.2163199048e-2f * z + 2.4181311049e-2f) * z + 4.5470025998e-2f) * z + 7.4953002686e-2f) * z + 1.6666752422e-1f) * z * x + x);
        } else {
            z = 0.5f - 0.5f * a;
            x = (float) Math.sqrt(z);
            r = 0.25f - (0.5f / HALF_PI) * (((((4.2163199048e-2f * z + 2.4181311049e-2f) * z + 4.5470025998e-2f) * z + 7.4953002686e-2f) * z + 1.6666752422e-1f) * z * x + x);
        }
        return 0.25f - Math.copySign(r, n);
    }

    /**
     * Returns arccosine in turns; non-tabular, almost as accurate as Math.acos() and may be slightly faster.
     * This implementation does not return NaN if given an out-of-range input (Math.acos does return NaN), unless the
     * input is NaN.
     * <br>
     * Based on <a href="https://jrouwe.github.io/JoltPhysics/_vec4_8inl_source.html">Jolt's trigonometry code</a>.
     * Jolt used an original implementation by <a href="https://www.moshier.net/">Stephen L. Moshier</a>.
     * Jolt is MIT-licensed.
     *
     * @param n acos is defined only when n is between -1.0 and 1.0, inclusive
     * @return between {@code 0.0} and {@code 0.5} when n is in the defined range
     */
    public static double acosTurnsPrecise(double n) {
        double a = Math.min(1.0, Math.abs(n)), z, x, r;
        if (a <= 0.5) {
            z = a * a;
            x = a;
            r = (0.25 / HALF_PI_D) * (((((4.2163199048e-2 * z + 2.4181311049e-2) * z + 4.5470025998e-2) * z + 7.4953002686e-2) * z + 1.6666752422e-1) * z * x + x);
        } else {
            z = 0.5 - 0.5 * a;
            x = Math.sqrt(z);
            r = 0.25 - (0.5 / HALF_PI_D) * (((((4.2163199048e-2 * z + 2.4181311049e-2) * z + 4.5470025998e-2) * z + 7.4953002686e-2) * z + 1.6666752422e-1) * z * x + x);
        }
        return 0.25 - Math.copySign(r, n);
    }
//</editor-fold>
}
