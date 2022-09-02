package com.github.tommyettinger.colorful.pure;

import com.github.tommyettinger.digital.BitConversion;

/**
 * Some alternative approximations to sin(), cos(), asin(), acos(), atan(), and atan2(), including the option to measure
 * most values in turns instead of radians (where 1 turn equals 2pi radians). The convention here is to have methods
 * that take input in turns or produce output in turns use a {@code _} suffix, like {@link #sin_(double)}, for turns to
 * never be returned as a negative value but for negative turn inputs to be accepted, and for the output type to match
 * the input type (float input gives float output, double input gives double output). There are also some methods that
 * measure turns in degrees, either from -180 to 180 for {@link #atan2Degrees(float, float)} or from 0 to 360 for
 * {@link #atan2Degrees360(float, float)}. Also has {@link #lerp(float, float, float)}, why not.
 * <br>
 * New code should use {@link com.github.tommyettinger.digital.TrigTools} for trigonometric methods instead; it has
 * sinTurns() instead of sin_(), and other naming has similar changes, but should be faster and more precise. The other
 * methods have parallels in {@link com.github.tommyettinger.digital.MathTools}.
 * <br>
 * Created by Tommy Ettinger on 8/6/2019.
 */
public class MathTools {

    /**
     * A fairly-close approximation of {@link Math#sin(double)} that can be significantly faster (between 8x and 80x
     * faster sin() calls in benchmarking; if you have access to libGDX you should consider its sometimes-more-precise
     * and sometimes-faster MathUtils.sin() method. Because this method doesn't rely on a
     * lookup table, where libGDX's MathUtils does, applications that have a bottleneck on memory may perform better
     * with this method than with MathUtils. Takes the same arguments Math.sin() does, so one angle in radians,
     * which may technically be any double (but this will lose precision on fairly large doubles, such as those that are
     * larger than {@link Long#MAX_VALUE}, because those doubles themselves will lose precision at that scale). This
     * is closely related to a cubic sway, but the shape of the output when graphed is almost identical to
     * sin(). The difference between the result of this method and {@link Math#sin(double)} should be under 0.0011 at
     * all points between -pi and pi, with an average difference of about 0.0005; not all points have been checked for
     * potentially higher errors, though.
     * <br>
     * The error for this double version is extremely close to the float version, {@link #sin(float)}, so you should
     * choose based on what type you have as input and/or want to return rather than on quality concerns. Coercion
     * between float and double takes about as long as this method normally takes to run (or longer), so if you have
     * floats you should usually use methods that take floats (or return floats, if assigning the result to a float),
     * and likewise for doubles.
     * <br>
     * The technique for sine approximation is mostly from
     * <a href="https://web.archive.org/web/20080228213915/http://devmaster.net/forums/showthread.php?t=5784">this archived DevMaster thread</a>,
     * with credit to "Nick". Changes have been made to accelerate wrapping from any double to the valid input range.
     * @param radians an angle in radians as a double, often from 0 to pi * 2, though not required to be.
     * @return the sine of the given angle, as a double between -1.0 and 1.0 (both inclusive)
     */
    public static double sin(double radians)
    {
        radians *= 0.6366197723675814;
        final long floor = (radians >= 0.0 ? (long) radians : (long) radians - 1L) & -2L;
        radians -= floor;
        radians *= 2.0 - radians;
        return radians * (-0.775 - 0.225 * radians) * ((floor & 2L) - 1L);
    }

    /**
     * A fairly-close approximation of {@link Math#cos(double)} that can be significantly faster (between 8x and 80x
     * faster cos() calls in benchmarking; if you have access to libGDX you should consider its sometimes-more-precise
     * and sometimes-faster MathUtils.cos() method. Because this method doesn't rely on a
     * lookup table, where libGDX's MathUtils does, applications that have a bottleneck on memory may perform better
     * with this method than with MathUtils. Takes the same arguments Math.cos() does, so one angle in radians,
     * which may technically be any double (but this will lose precision on fairly large doubles, such as those that are
     * larger than {@link Long#MAX_VALUE}, because those doubles themselves will lose precision at that scale). This
     * is closely related to a cubic sway, but the shape of the output when graphed is almost identical to
     * cos(). The difference between the result of this method and {@link Math#cos(double)} should be under 0.0011 at
     * all points between -pi and pi, with an average difference of about 0.0005; not all points have been checked for
     * potentially higher errors, though.
     * <br>
     * The error for this double version is extremely close to the float version, {@link #cos(float)}, so you should
     * choose based on what type you have as input and/or want to return rather than on quality concerns. Coercion
     * between float and double takes about as long as this method normally takes to run (or longer), so if you have
     * floats you should usually use methods that take floats (or return floats, if assigning the result to a float),
     * and likewise for doubles.
     * <br>
     * The technique for cosine approximation is mostly from
     * <a href="https://web.archive.org/web/20080228213915/http://devmaster.net/forums/showthread.php?t=5784">this archived DevMaster thread</a>,
     * with credit to "Nick". Changes have been made to accelerate wrapping from any double to the valid input range.
     * @param radians an angle in radians as a double, often from 0 to pi * 2, though not required to be.
     * @return the cosine of the given angle, as a double between -1.0 and 1.0 (both inclusive)
     */
    public static double cos(double radians)
    {
        radians = radians * 0.6366197723675814 + 1.0;
        final long floor = (radians >= 0.0 ? (long) radians : (long) radians - 1L) & -2L;
        radians -= floor;
        radians *= 2.0 - radians;
        return radians * (-0.775 - 0.225 * radians) * ((floor & 2L) - 1L);
    }

    /**
     * A fairly-close approximation of {@link Math#sin(double)} that can be significantly faster (between 8x and 80x
     * faster sin() calls in benchmarking, and both takes and returns floats; if you have access to libGDX you should
     * consider its more-precise and sometimes-faster MathUtils.sin() method. Because this method doesn't rely on a
     * lookup table, where libGDX's MathUtils does, applications that have a bottleneck on memory may perform better
     * with this method than with MathUtils. Takes the same arguments Math.sin() does, so one angle in radians,
     * which may technically be any float (but this will lose precision on fairly large floats, such as those that are
     * larger than {@link Integer#MAX_VALUE}, because those floats themselves will lose precision at that scale). This
     * is closely related to a cubic sway, but the shape of the output when graphed is almost identical to
     * sin(). The difference between the result of this method and {@link Math#sin(double)} should be under 0.0011 at
     * all points between -pi and pi, with an average difference of about 0.0005; not all points have been checked for
     * potentially higher errors, though.
     * <br>
     * The error for this float version is extremely close to the double version, {@link #sin(double)}, so you should
     * choose based on what type you have as input and/or want to return rather than on quality concerns. Coercion
     * between float and double takes about as long as this method normally takes to run (or longer), so if you have
     * floats you should usually use methods that take floats (or return floats, if assigning the result to a float),
     * and likewise for doubles.
     * <br>
     * The technique for sine approximation is mostly from
     * <a href="https://web.archive.org/web/20080228213915/http://devmaster.net/forums/showthread.php?t=5784">this archived DevMaster thread</a>,
     * with credit to "Nick". Changes have been made to accelerate wrapping from any float to the valid input range.
     * @param radians an angle in radians as a float, often from 0 to pi * 2, though not required to be.
     * @return the sine of the given angle, as a float between -1f and 1f (both inclusive)
     */
    public static float sin(float radians)
    {
        radians *= 0.6366197723675814f;
        final int floor = (radians >= 0.0 ? (int) radians : (int) radians - 1) & -2;
        radians -= floor;
        radians *= 2f - radians;
        return radians * (-0.775f - 0.225f * radians) * ((floor & 2) - 1);
    }

    /**
     * A fairly-close approximation of {@link Math#cos(double)} that can be significantly faster (between 8x and 80x
     * faster cos() calls in benchmarking, and both takes and returns floats; if you have access to libGDX you should
     * consider its more-precise and sometimes-faster MathUtils.cos() method. Because this method doesn't rely on a
     * lookup table, where libGDX's MathUtils does, applications that have a bottleneck on memory may perform better
     * with this method than with MathUtils. Takes the same arguments Math.cos() does, so one angle in radians,
     * which may technically be any float (but this will lose precision on fairly large floats, such as those that are
     * larger than {@link Integer#MAX_VALUE}, because those floats themselves will lose precision at that scale). This
     * is closely related to a cubic sway, but the shape of the output when graphed is almost identical to
     * cos(). The difference between the result of this method and {@link Math#cos(double)} should be under 0.0011 at
     * all points between -pi and pi, with an average difference of about 0.0005; not all points have been checked for
     * potentially higher errors, though.
     * <br>
     * The error for this float version is extremely close to the double version, {@link #cos(double)}, so you should
     * choose based on what type you have as input and/or want to return rather than on quality concerns. Coercion
     * between float and double takes about as long as this method normally takes to run (or longer), so if you have
     * floats you should usually use methods that take floats (or return floats, if assigning the result to a float),
     * and likewise for doubles.
     * <br>
     * The technique for cosine approximation is mostly from
     * <a href="https://web.archive.org/web/20080228213915/http://devmaster.net/forums/showthread.php?t=5784">this archived DevMaster thread</a>,
     * with credit to "Nick". Changes have been made to accelerate wrapping from any float to the valid input range.
     * @param radians an angle in radians as a float, often from 0 to pi * 2, though not required to be.
     * @return the cosine of the given angle, as a float between -1f and 1f (both inclusive)
     */
    public static float cos(float radians)
    {
        radians = radians * 0.6366197723675814f + 1f;
        final int floor = (radians >= 0.0 ? (int) radians : (int) radians - 1) & -2;
        radians -= floor;
        radians *= 2f - radians;
        return radians * (-0.775f - 0.225f * radians) * ((floor & 2) - 1);
    }

    /**
     * A fairly-close approximation of {@link Math#sin(double)} that can be significantly faster (between 8x and 80x
     * faster sin() calls in benchmarking, and both takes and returns floats; if you have access to libGDX, you should
     * consider its more-precise and sometimes-faster MathUtils.sinDeg() method. Because this method doesn't rely on a
     * lookup table, where libGDX's MathUtils does, applications that have a bottleneck on memory may perform better
     * with this method than with MathUtils. Takes one angle in degrees,
     * which may technically be any float (but this will lose precision on fairly large floats, such as those that are
     * larger than {@link Integer#MAX_VALUE}, because those floats themselves will lose precision at that scale). The
     * difference between the result of this method and {@link Math#sin(double)} should be under 0.0011 at
     * all points between -360 and 360, with an average difference of about 0.0005; not all points have been checked for
     * potentially higher errors, though.
     * <br>
     * The error for this float version is extremely close to the double version, {@link #sin(double)}, so you should
     * choose based on what type you have as input and/or want to return rather than on quality concerns. Coercion
     * between float and double takes about as long as this method normally takes to run (or longer), so if you have
     * floats you should usually use methods that take floats (or return floats, if assigning the result to a float),
     * and likewise for doubles.
     * <br>
     * Unlike in previous versions of this method, the sign of the input doesn't affect performance here, at least not
     * by a measurable amount.
     * <br>
     * The technique for sine approximation is mostly from
     * <a href="https://web.archive.org/web/20080228213915/http://devmaster.net/forums/showthread.php?t=5784">this archived DevMaster thread</a>,
     * with credit to "Nick". Changes have been made to accelerate wrapping from any float to the valid input range.
     * @param degrees an angle in degrees as a float, often from 0 to 360, though not required to be.
     * @return the sine of the given angle, as a float between -1f and 1f (both inclusive)
     */
    public static float sinDegrees(float degrees)
    {
        degrees = degrees * 0.011111111111111112f;
        final int floor = (degrees >= 0.0 ? (int) degrees : (int) degrees - 1) & -2;
        degrees -= floor;
        degrees *= 2f - degrees;
        return degrees * (-0.775f - 0.225f * degrees) * ((floor & 2) - 1);
    }

    /**
     * A fairly-close approximation of {@link Math#cos(double)} that can be significantly faster (between 8x and 80x
     * faster cos() calls in benchmarking, and both takes and returns floats; if you have access to libGDX, you should
     * consider its more-precise and sometimes-faster MathUtils.cosDeg() method. Because this method doesn't rely on a
     * lookup table, where libGDX's MathUtils does, applications that have a bottleneck on memory may perform better
     * with this method than with MathUtils. Takes one angle in degrees,
     * which may technically be any float (but this will lose precision on fairly large floats, such as those that are
     * larger than {@link Integer#MAX_VALUE}, because those floats themselves will lose precision at that scale). The
     * difference between the result of this method and {@link Math#cos(double)} should be under 0.0011 at
     * all points between -360 and 360, with an average difference of about 0.0005; not all points have been checked for
     * potentially higher errors, though.
     * <br>
     * The error for this float version is extremely close to the double version, {@link #cos(double)}, so you should
     * choose based on what type you have as input and/or want to return rather than on quality concerns. Coercion
     * between float and double takes about as long as this method normally takes to run (or longer), so if you have
     * floats you should usually use methods that take floats (or return floats, if assigning the result to a float),
     * and likewise for doubles.
     * <br>
     * Unlike in previous versions of this method, the sign of the input doesn't affect performance here, at least not
     * by a measurable amount.
     * <br>
     * The technique for cosine approximation is mostly from
     * <a href="https://web.archive.org/web/20080228213915/http://devmaster.net/forums/showthread.php?t=5784">this archived DevMaster thread</a>,
     * with credit to "Nick". Changes have been made to accelerate wrapping from any float to the valid input range.
     * @param degrees an angle in degrees as a float, often from 0 to pi * 2, though not required to be.
     * @return the cosine of the given angle, as a float between -1f and 1f (both inclusive)
     */
    public static float cosDegrees(float degrees)
    {
        degrees = degrees * 0.011111111111111112f + 1f;
        final int floor = (degrees >= 0.0 ? (int) degrees : (int) degrees - 1) & -2;
        degrees -= floor;
        degrees *= 2f - degrees;
        return degrees * (-0.775f - 0.225f * degrees) * ((floor & 2) - 1);
    }

    /**
     * A variation on {@link Math#sin(double)} that takes its input as a fraction of a turn instead of in radians; one
     * turn is equal to 360 degrees or two*PI radians. This can be useful as a building block for other measurements;
     * to make a sine method that takes its input in grad (with 400 grad equal to 360 degrees), you would just divide
     * the grad value by 400.0 (or multiply it by 0.0025) and pass it to this method. Similarly for binary degrees, also
     * called brad (with 256 brad equal to 360 degrees), you would divide by 256.0 or multiply by 0.00390625 before
     * passing that value here. The brad case is especially useful because you can use a byte for any brad values, and
     * adding up those brad values will wrap correctly (256 brad goes back to 0) while keeping perfect precision for the
     * results (you still divide by 256.0 when you pass the brad value to this method).
     * <br>
     * The error for this double version is extremely close to the float version, {@link #sin_(float)}, so you should
     * choose based on what type you have as input and/or want to return rather than on quality concerns. Coercion
     * between float and double takes about as long as this method normally takes to run (or longer), so if you have
     * floats you should usually use methods that take floats (or return floats, if assigning the result to a float),
     * and likewise for doubles.
     * <br>
     * The technique for sine approximation is mostly from
     * <a href="https://web.archive.org/web/20080228213915/http://devmaster.net/forums/showthread.php?t=5784">this archived DevMaster thread</a>,
     * with credit to "Nick". Changes have been made to accelerate wrapping from any double to the valid input range.
     * @param turns an angle as a fraction of a turn as a double, with 0.5 here equivalent to PI radians in {@link #cos(double)}
     * @return the sine of the given angle, as a double between -1.0 and 1.0 (both inclusive)
     */
    public static double sin_(double turns)
    {
        turns *= 4.0;
        final long floor = (turns >= 0.0 ? (long) turns : (long) turns - 1L) & -2L;
        turns -= floor;
        turns *= 2.0 - turns;
        return turns * (-0.775 - 0.225 * turns) * ((floor & 2L) - 1L);
    }

    /**
     * A variation on {@link Math#cos(double)} that takes its input as a fraction of a turn instead of in radians; one
     * turn is equal to 360 degrees or two*PI radians. This can be useful as a building block for other measurements;
     * to make a cosine method that takes its input in grad (with 400 grad equal to 360 degrees), you would just divide
     * the grad value by 400.0 (or multiply it by 0.0025) and pass it to this method. Similarly for binary degrees, also
     * called brad (with 256 brad equal to 360 degrees), you would divide by 256.0 or multiply by 0.00390625 before
     * passing that value here. The brad case is especially useful because you can use a byte for any brad values, and
     * adding up those brad values will wrap correctly (256 brad goes back to 0) while keeping perfect precision for the
     * results (you still divide by 256.0 when you pass the brad value to this method).
     * <br>
     * The error for this double version is extremely close to the float version, {@link #cos_(float)}, so you should
     * choose based on what type you have as input and/or want to return rather than on quality concerns. Coercion
     * between float and double takes about as long as this method normally takes to run (or longer), so if you have
     * floats you should usually use methods that take floats (or return floats, if assigning the result to a float),
     * and likewise for doubles.
     * <br>
     * The technique for cosine approximation is mostly from
     * <a href="https://web.archive.org/web/20080228213915/http://devmaster.net/forums/showthread.php?t=5784">this archived DevMaster thread</a>,
     * with credit to "Nick". Changes have been made to accelerate wrapping from any double to the valid input range.
     * @param turns an angle as a fraction of a turn as a double, with 0.5 here equivalent to PI radians in {@link #cos(double)}
     * @return the cosine of the given angle, as a double between -1.0 and 1.0 (both inclusive)
     */
    public static double cos_(double turns)
    {
        turns = turns * 4.0 + 1.0;
        final long floor = (turns >= 0.0 ? (long) turns : (long) turns - 1L) & -2L;
        turns -= floor;
        turns *= 2.0 - turns;
        return turns * (-0.775 - 0.225 * turns) * ((floor & 2L) - 1L);
    }

    /**
     * A variation on {@link Math#sin(double)} that takes its input as a fraction of a turn instead of in radians (it
     * also takes and returns a float); one turn is equal to 360 degrees or two*PI radians. This can be useful as a
     * building block for other measurements; to make a sine method that takes its input in grad (with 400 grad equal to
     * 360 degrees), you would just divide the grad value by 400.0 (or multiply it by 0.0025) and pass it to this
     * method. Similarly for binary degrees, also called brad (with 256 brad equal to 360 degrees), you would divide by
     * 256.0 or multiply by 0.00390625 before passing that value here. The brad case is especially useful because you
     * can use a byte for any brad values, and adding up those brad values will wrap correctly (256 brad goes back to 0)
     * while keeping perfect precision for the results (you still divide by 256.0 when you pass the brad value to this
     * method).
     * <br>
     * The error for this float version is extremely close to the double version, {@link #sin_(double)}, so you should
     * choose based on what type you have as input and/or want to return rather than on quality concerns. Coercion
     * between float and double takes about as long as this method normally takes to run (or longer), so if you have
     * floats you should usually use methods that take floats (or return floats, if assigning the result to a float),
     * and likewise for doubles.
     * <br>
     * The technique for sine approximation is mostly from
     * <a href="https://web.archive.org/web/20080228213915/http://devmaster.net/forums/showthread.php?t=5784">this archived DevMaster thread</a>,
     * with credit to "Nick". Changes have been made to accelerate wrapping from any double to the valid input range.
     * @param turns an angle as a fraction of a turn as a float, with 0.5 here equivalent to PI radians in {@link #cos(double)}
     * @return the sine of the given angle, as a float between -1.0 and 1.0 (both inclusive)
     */
    public static float sin_(float turns)
    {
        turns *= 4f;
        final long floor = (turns >= 0.0 ? (long) turns : (long) turns - 1L) & -2L;
        turns -= floor;
        turns *= 2f - turns;
        return turns * (-0.775f - 0.225f * turns) * ((floor & 2L) - 1L);
    }

    /**
     * A variation on {@link Math#cos(double)} that takes its input as a fraction of a turn instead of in radians (it
     * also takes and returns a float); one turn is equal to 360 degrees or two*PI radians. This can be useful as a
     * building block for other measurements; to make a cosine method that takes its input in grad (with 400 grad equal
     * to 360 degrees), you would just divide the grad value by 400.0 (or multiply it by 0.0025) and pass it to this
     * method. Similarly for binary degrees, also called brad (with 256 brad equal to 360 degrees), you would divide by
     * 256.0 or multiply by 0.00390625 before passing that value here. The brad case is especially useful because you
     * can use a byte for any brad values, and adding up those brad values will wrap correctly (256 brad goes back to 0)
     * while keeping perfect precision for the results (you still divide by 256.0 when you pass the brad value to this
     * method).
     * <br>
     * The error for this float version is extremely close to the float version, {@link #cos_(double)}, so you should
     * choose based on what type you have as input and/or want to return rather than on quality concerns. Coercion
     * between float and double takes about as long as this method normally takes to run (or longer), so if you have
     * floats you should usually use methods that take floats (or return floats, if assigning the result to a float),
     * and likewise for doubles.
     * <br>
     * The technique for cosine approximation is mostly from
     * <a href="https://web.archive.org/web/20080228213915/http://devmaster.net/forums/showthread.php?t=5784">this archived DevMaster thread</a>,
     * with credit to "Nick". Changes have been made to accelerate wrapping from any double to the valid input range.
     * @param turns an angle as a fraction of a turn as a float, with 0.5 here equivalent to PI radians in {@link #cos(double)}
     * @return the cosine of the given angle, as a float between -1.0 and 1.0 (both inclusive)
     */
    public static float cos_(float turns)
    {
        turns = turns * 4f + 1f;
        final long floor = (turns >= 0.0 ? (long) turns : (long) turns - 1L) & -2L;
        turns -= floor;
        turns *= 2f - turns;
        return turns * (-0.775f - 0.225f * turns) * ((floor & 2L) - 1L);
    }

    /**
     * Arc tangent approximation with very low error, using an algorithm from the 1955 research study
     * "Approximations for Digital Computers," by RAND Corporation (this is sheet 9's algorithm, which is the
     * second-fastest and second-least precise). This method is usually much faster than {@link Math#atan(double)},
     * but is somewhat less precise than Math's implementation.
     * @param i an input to the inverse tangent function; any double is accepted
     * @return an output from the inverse tangent function, from PI/-2.0 to PI/2.0 inclusive
     */
    public static double atan(final double i) {
        final double n = Math.min(Math.abs(i), Double.MAX_VALUE);
        final double c = (n - 1.0) / (n + 1.0);
        final double c2 = c * c;
        final double c3 = c * c2;
        final double c5 = c3 * c2;
        final double c7 = c5 * c2;
        return Math.copySign(0.7853981633974483 +
                (0.999215 * c - 0.3211819 * c3 + 0.1462766 * c5 - 0.0389929 * c7), i);
    }

    /**
     * Arc tangent approximation with very low error, using an algorithm from the 1955 research study
     * "Approximations for Digital Computers," by RAND Corporation (this is sheet 9's algorithm, which is the
     * second-fastest and second-least precise). This method is usually much faster than {@link Math#atan(double)},
     * but is somewhat less precise than Math's implementation.
     * @param i an input to the inverse tangent function; any float is accepted
     * @return an output from the inverse tangent function, from PI/-2.0 to PI/2.0 inclusive
     */
    public static float atan(final float i) {
        final float n = Math.min(Math.abs(i), Float.MAX_VALUE);
        final float c = (n - 1f) / (n + 1f);
        final float c2 = c * c;
        final float c3 = c * c2;
        final float c5 = c3 * c2;
        final float c7 = c5 * c2;
        return Math.copySign(0.7853981633974483f +
                (0.999215f * c - 0.3211819f * c3 + 0.1462766f * c5 - 0.0389929f * c7), i);
    }

    /**
     * A variant on {@link #atan(double)} that does not tolerate infinite inputs, and is slightly faster.
     * @param i any finite double
     * @return an output from the inverse tangent function, from PI/-2.0 to PI/2.0 inclusive
     */
    private static double atn(final double i) {
        final double n = Math.abs(i);
        final double c = (n - 1.0) / (n + 1.0);
        final double c2 = c * c;
        final double c3 = c * c2;
        final double c5 = c3 * c2;
        final double c7 = c5 * c2;
        return Math.copySign(0.7853981633974483 +
                (0.999215 * c - 0.3211819 * c3 + 0.1462766 * c5 - 0.0389929 * c7), i);
    }
    /**
     * A variant on {@link #atan(float)} that does not tolerate infinite inputs, and is slightly faster.
     * @param i any finite float
     * @return an output from the inverse tangent function, from PI/-2.0 to PI/2.0 inclusive
     */
    private static float atn(final float i) {
        final float n = Math.abs(i);
        final float c = (n - 1f) / (n + 1f);
        final float c2 = c * c;
        final float c3 = c * c2;
        final float c5 = c3 * c2;
        final float c7 = c5 * c2;
        return Math.copySign(0.7853981633974483f +
                (0.999215f * c - 0.3211819f * c3 + 0.1462766f * c5 - 0.0389929f * c7), i);
    }

    /**
     * Close approximation of the frequently-used trigonometric method atan2, with higher precision than libGDX's atan2
     * approximation. Maximum error is below 0.00009 radians.
     * Takes y and x (in that unusual order) as doubles, and returns the angle from the origin to that point in radians.
     * It is about 5 times faster than {@link Math#atan2(double, double)} (roughly 12 ns instead of roughly 62 ns for
     * Math, on Java 8 HotSpot). It is slightly faster than libGDX' MathUtils approximation of the same method;
     * MathUtils seems to have worse average error, though.
     * <br>
     * Credit for this goes to the 1955 research study "Approximations for Digital Computers," by RAND Corporation. This
     * is sheet 9's algorithm, which is the second-fastest and second-least precise. The algorithm on sheet 8 is faster,
     * but only by a very small degree, and is considerably less precise. That study provides an {@link #atan(double)}
     * method, and the small code to make that work as atan2() was worked out from Wikipedia.
     * <br>
     * See also {@link #atan2_(double, double)} if you don't want a mess converting to degrees or some other
     * measurement, since that method returns an angle from 0f (equal to 0 degrees) to 1f (equal to 360 degrees). You
     * can also use {@link #atan2Degrees(double, double)} or {@link #atan2Degrees360(double, double)} to produce a
     * result in degrees, either from -180 to 180 or 0 to 360.
     * @param y y-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @param x x-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @return the angle to the given point, in radians as a double; ranges from -PI to PI
     */
    public static double atan2(final double y, double x) {
        double n = y / x;
        if(n != n) n = (y == x ? 1.0 : -1.0); // if both y and x are infinite, n would be NaN
        else if(n - n != n - n) x = 0.0; // if n is infinite, y is infinitely larger than x.
        if(x > 0)
            return atn(n);
        else if(x < 0) {
            if(y >= 0)
                return atn(n) + 3.14159265358979323846;
            else
                return atn(n) - 3.14159265358979323846;
        }
        else if(y > 0) return x + 1.5707963267948966;
        else if(y < 0) return x - 1.5707963267948966;
        else return x + y; // returns 0 for 0,0 or NaN if either y or x is NaN
    }

    /**
     * Close approximation of the frequently-used trigonometric method atan2, with higher precision than libGDX's atan2
     * approximation. Maximum error is below 0.00009 radians.
     * Takes y and x (in that unusual order) as floats, and returns the angle from the origin to that point in radians.
     * It is about 5 times faster than {@link Math#atan2(double, double)} (roughly 12 ns instead of roughly 62 ns for
     * Math, on Java 8 HotSpot). It is slightly faster than libGDX' MathUtils approximation of the same method;
     * MathUtils seems to have worse average error, though.
     * <br>
     * Credit for this goes to the 1955 research study "Approximations for Digital Computers," by RAND Corporation. This
     * is sheet 9's algorithm, which is the second-fastest and second-least precise. The algorithm on sheet 8 is faster,
     * but only by a very small degree, and is considerably less precise. That study provides an {@link #atan(float)}
     * method, and the small code to make that work as atan2() was worked out from Wikipedia.
     * <br>
     * See also {@link #atan2_(float, float)} if you don't want a mess converting to degrees or some other measurement,
     * since that method returns an angle from 0f (equal to 0 degrees) to 1f (equal to 360 degrees). You can also use
     * {@link #atan2Degrees(float, float)} or {@link #atan2Degrees360(float, float)} to produce a result in degrees,
     * either from -180 to 180 or 0 to 360.
     * @param y y-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @param x x-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @return the angle to the given point, in radians as a float; ranges from -PI to PI
     */
    public static float atan2(final float y, float x) {
        float n = y / x;
        if(n != n) n = (y == x ? 1f : -1f); // if both y and x are infinite, n would be NaN
        else if(n - n != n - n) x = 0f; // if n is infinite, y is infinitely larger than x.
        if(x > 0)
            return atn(n);
        else if(x < 0) {
            if(y >= 0)
                return atn(n) + 3.14159265358979323846f;
            else
                return atn(n) - 3.14159265358979323846f;
        }
        else if(y > 0) return x + 1.5707963267948966f;
        else if(y < 0) return x - 1.5707963267948966f;
        else return x + y; // returns 0 for 0,0 or NaN if either y or x is NaN
    }

    /**
     * This one's weird; unlike {@link #atan2_(double, double)}, it can return negative results.
     * @param v any finite double
     * @return between -0.25 and 0.25
     */
    private static double atn_(final double v) {
        final double n = Math.abs(v);
        final double c = (n - 1.0) / (n + 1.0);
        final double c2 = c * c;
        final double c3 = c * c2;
        final double c5 = c3 * c2;
        final double c7 = c5 * c2;
        return Math.copySign(0.125 + 0.1590300064615682 * c - 0.051117687016646825 * c3 + 0.02328064394867594 * c5
                - 0.006205912780487965 * c7, v);
    }

    /**
     * This one's weird; unlike {@link #atan2_(float, float)}, it can return negative results.
     * @param v any finite float
     * @return between -0.25 and 0.25
     */
    private static float atn_(final float v) {
        final float n = Math.abs(v);
        final float c = (n - 1f) / (n + 1f);
        final float c2 = c * c;
        final float c3 = c * c2;
        final float c5 = c3 * c2;
        final float c7 = c5 * c2;
        return Math.copySign(0.125f + 0.1590300064615682f * c - 0.051117687016646825f * c3 + 0.02328064394867594f * c5
                - 0.006205912780487965f * c7, v);
    }
    /**
     * Altered-range approximation of the frequently-used trigonometric method atan2, taking y and x positions as
     * doubles and returning an angle measured in turns from 0.0 to 1.0 (inclusive), with one cycle over the range
     * equivalent to 360 degrees or 2PI radians. You can multiply the angle by {@code 6.2831855f} to change to radians,
     * or by {@code 360f} to change to degrees. Takes y and x (in that unusual order) as doubles. Will never return a
     * negative number, which may help avoid costly floating-point modulus when you actually want a positive number.
     * <br>
     * Credit for this goes to the 1955 research study "Approximations for Digital Computers," by RAND Corporation. This
     * is sheet 9's algorithm, which is the second-fastest and second-least precise. The algorithm on sheet 8 is faster,
     * but only by a very small degree, and is considerably less precise. That study provides an {@link #atan(float)}
     * method, and the small code to make that work as atan2_() was worked out from Wikipedia.
     * <br>
     * Note that {@link #atan2(double, double)} returns an angle in radians and can return negative results, which may
     * be fine for many tasks; these two methods are extremely close in implementation and speed. There are also
     * {@link #atan2Degrees(double, double)} and {@link #atan2Degrees360(double, double)}, which provide an angle in
     * degrees, either from -180 to 180 or 0 to 360.
     * @param y y-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @param x x-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @return the angle to the given point, as a double from 0.0 to 1.0, inclusive
     */
    public static double atan2_(final double y, double x) {
        double n = y / x;
        if(n != n) n = (y == x ? 1f : -1f); // if both y and x are infinite, n would be NaN
        else if(n - n != n - n) x = 0.0; // if n is infinite, y is infinitely larger than x.
        if(x > 0) {
            if(y >= 0)
                return atn_(n);
            else
                return atn_(n) + 1.0;
        }
        else if(x < 0) {
            return atn_(n) + 0.5;
        }
        else if(y > 0) return x + 0.25;
        else if(y < 0) return x + 0.75;
        else return x + y; // returns 0 for 0,0 or NaN if either y or x is NaN
    }

    /**
     * Altered-range approximation of the frequently-used trigonometric method atan2, taking y and x positions as floats
     * and returning an angle measured in turns from 0.0f to 1.0f, with one cycle over the range equivalent to 360
     * degrees or 2PI radians. You can multiply the angle by {@code 6.2831855f} to change to radians, or by {@code 360f}
     * to change to degrees. Takes y and x (in that unusual order) as floats. Will never return a negative number, which
     * may help avoid costly floating-point modulus when you actually want a positive number.
     * <br>
     * Credit for this goes to the 1955 research study "Approximations for Digital Computers," by RAND Corporation. This
     * is sheet 9's algorithm, which is the second-fastest and second-least precise. The algorithm on sheet 8 is faster,
     * but only by a very small degree, and is considerably less precise. That study provides an {@link #atan(float)}
     * method, and the small code to make that work as atan2_() was worked out from Wikipedia.
     * <br>
     * Note that {@link #atan2(float, float)} returns an angle in radians and can return negative results, which may
     * be fine for many tasks; these two methods are extremely close in implementation and speed. There are also
     * {@link #atan2Degrees(float, float)} and {@link #atan2Degrees360(float, float)}, which provide an angle in
     * degrees, either from -180 to 180 or 0 to 360.
     * @param y y-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @param x x-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @return the angle to the given point, as a float from 0.0f to 1.0f, inclusive
     */
    public static float atan2_(final float y, float x) {
        float n = y / x;
        if(n != n) n = (y == x ? 1f : -1f); // if both y and x are infinite, n would be NaN
        else if(n - n != n - n) x = 0f; // if n is infinite, y is infinitely larger than x.
        if(x > 0) {
            if(y >= 0)
                return atn_(n);
            else
                return atn_(n) + 1f;
        }
        else if(x < 0) {
            return atn_(n) + 0.5f;
        }
        else if(y > 0) return x + 0.25f;
        else if(y < 0) return x + 0.75f;
        else return x + y; // returns 0 for 0,0 or NaN if either y or x is NaN
    }

    /**
     * Arc tangent approximation measured in degrees, using an algorithm from the 1955 research study
     * "Approximations for Digital Computers," by RAND Corporation (this is sheet 9's algorithm, which is the
     * second-fastest and second-least precise). This method is usually much faster than {@link Math#atan(double)},
     * but is somewhat less precise than Math's implementation. This implementation can return negative or positive
     * results in degrees.
     * @param i an input to the inverse tangent function; any double is accepted
     * @return an output from the inverse tangent function in degrees, from -90 to 90 inclusive
     */
    public static double atanDegrees(final double i) {
        final double n = Math.min(Math.abs(i), Double.MAX_VALUE);
        final double c = (n - 1.0) / (n + 1.0);
        final double c2 = c * c;
        final double c3 = c * c2;
        final double c5 = c3 * c2;
        final double c7 = c5 * c2;
        return Math.copySign(45.0 +
                (57.25080271739779 * c - 18.402366944901082 * c3 + 8.381031432388337 * c5 - 2.2341286239715488 * c7), i);
    }

    /**
     * Arc tangent approximation measured in degrees, using an algorithm from the 1955 research study
     * "Approximations for Digital Computers," by RAND Corporation (this is sheet 9's algorithm, which is the
     * second-fastest and second-least precise). This method is usually much faster than {@link Math#atan(double)},
     * but is somewhat less precise than Math's implementation. This implementation can return negative or positive
     * results in degrees.
     * @param i an input to the inverse tangent function; any float is accepted
     * @return an output from the inverse tangent function, from -90 to 90 inclusive
     */
    public static float atanDegrees(final float i) {
        final float n = Math.min(Math.abs(i), Float.MAX_VALUE);
        final float c = (n - 1f) / (n + 1f);
        final float c2 = c * c;
        final float c3 = c * c2;
        final float c5 = c3 * c2;
        final float c7 = c5 * c2;
        return Math.copySign(45f +
                (57.25080271739779f * c - 18.402366944901082f * c3 + 8.381031432388337f * c5 - 2.2341286239715488f * c7), i);
    }
    /**
     * A variant on {@link #atanDegrees(double)} that does not tolerate infinite inputs, and is slightly faster.
     * @param i any finite double
     * @return an output from the inverse tangent function, from PI/-2.0 to PI/2.0 inclusive
     */
    private static double atnDegrees(final double i) {
        final double n = Math.abs(i);
        final double c = (n - 1.0) / (n + 1.0);
        final double c2 = c * c;
        final double c3 = c * c2;
        final double c5 = c3 * c2;
        final double c7 = c5 * c2;
        return Math.copySign(45.0 +
                (57.25080271739779 * c - 18.402366944901082 * c3 + 8.381031432388337 * c5 - 2.2341286239715488 * c7), i);
    }
    /**
     * A variant on {@link #atan(float)} that does not tolerate infinite inputs, and is slightly faster.
     * @param i any finite float
     * @return an output from the inverse tangent function, from PI/-2.0 to PI/2.0 inclusive
     */
    private static float atnDegrees(final float i) {
        final float n = Math.abs(i);
        final float c = (n - 1f) / (n + 1f);
        final float c2 = c * c;
        final float c3 = c * c2;
        final float c5 = c3 * c2;
        final float c7 = c5 * c2;
        return Math.copySign(45f +
                (57.25080271739779f * c - 18.402366944901082f * c3 + 8.381031432388337f * c5 - 2.2341286239715488f * c7), i);
    }

    /**
     * Close approximation of the frequently-used trigonometric method atan2 measured in degrees, with higher precision
     * than libGDX's atan2 approximation. The range for this is -180 to 180.
     * Takes y and x (in that unusual order) as doubles, and returns the angle from the origin to that point in degrees.
     * It is about 5 times faster than {@link Math#atan2(double, double)} (roughly 12 ns instead of roughly 62 ns for
     * Math, on Java 8 HotSpot). It is slightly faster than libGDX' MathUtils approximation of the same method;
     * MathUtils seems to have worse average error, as well.
     * <br>
     * Credit for this goes to the 1955 research study "Approximations for Digital Computers," by RAND Corporation. This
     * is sheet 9's algorithm, which is the second-fastest and second-least precise. The algorithm on sheet 8 is faster,
     * but only by a very small degree, and is considerably less precise. That study provides an {@link #atan(double)}
     * method, and the small code to make that work as atan2Degrees() was worked out from Wikipedia.
     * <br>
     * See also {@link #atan2_(double, double)} for a version that returns a measurement as a fraction of a turn, or
     * {@link #atan2(double, double)} for the typical radians. You
     * can also use {@link #atan2Degrees360(double, double)} to produce a result in degrees from 0 to 360.
     * @param y y-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @param x x-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @return the angle to the given point, in radians as a double; ranges from -180 to 180
     */
    public static double atan2Degrees(final double y, double x) {
        double n = y / x;
        if(n != n) n = (y == x ? 1.0 : -1.0); // if both y and x are infinite, n would be NaN
        else if(n - n != n - n) x = 0.0; // if n is infinite, y is infinitely larger than x.
        if(x > 0)
            return atnDegrees(n);
        else if(x < 0) {
            if(y >= 0)
                return atnDegrees(n) + 180.0;
            else
                return atnDegrees(n) - 180.0;
        }
        else if(y > 0) return x + 90.0;
        else if(y < 0) return x - 90.0;
        else return x + y; // returns 0 for 0,0 or NaN if either y or x is NaN
    }

    /**
     * Close approximation of the frequently-used trigonometric method atan2 measured in degrees, with higher precision
     * than libGDX's atan2 approximation. The range for this is -180 to 180.
     * Takes y and x (in that unusual order) as floats, and returns the angle from the origin to that point in degrees.
     * It is about 5 times faster than {@link Math#atan2(double, double)} (roughly 12 ns instead of roughly 62 ns for
     * Math, on Java 8 HotSpot). It is slightly faster than libGDX' MathUtils approximation of the same method;
     * MathUtils seems to have worse average error, as well.
     * <br>
     * Credit for this goes to the 1955 research study "Approximations for Digital Computers," by RAND Corporation. This
     * is sheet 9's algorithm, which is the second-fastest and second-least precise. The algorithm on sheet 8 is faster,
     * but only by a very small degree, and is considerably less precise. That study provides an {@link #atan(float)}
     * method, and the small code to make that work as atan2Degrees() was worked out from Wikipedia.
     * <br>
     * See also {@link #atan2_(float, float)} for a version that returns a measurement as a fraction of a turn, or
     * {@link #atan2(float, float)} for the typical radians. You
     * can also use {@link #atan2Degrees360(float, float)} to produce a result in degrees from 0 to 360.
     * @param y y-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @param x x-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @return the angle to the given point, in radians as a float; ranges from -180 to 180
     */
    public static float atan2Degrees(final float y, float x) {
        float n = y / x;
        if(n != n) n = (y == x ? 1f : -1f); // if both y and x are infinite, n would be NaN
        else if(n - n != n - n) x = 0f; // if n is infinite, y is infinitely larger than x.
        if(x > 0)
            return atnDegrees(n);
        else if(x < 0) {
            if(y >= 0)
                return atnDegrees(n) + 180f;
            else
                return atnDegrees(n) - 180f;
        }
        else if(y > 0) return x + 90f;
        else if(y < 0) return x - 90f;
        else return x + y; // returns 0 for 0,0 or NaN if either y or x is NaN
    }

    /**
     * This one's weird; unlike {@link #atan2Degrees360(double, double)}, it can return negative results.
     * @param v any finite double
     * @return between -90 and 90
     */
    private static double atnDegrees360(final double v) {
        final double n = Math.abs(v);
        final double c = (n - 1.0) / (n + 1.0);
        final double c2 = c * c;
        final double c3 = c * c2;
        final double c5 = c3 * c2;
        final double c7 = c5 * c2;
        return Math.copySign(45.0 + 57.25080232616455 * c - 18.402367325992856 * c3
                + 8.381031821523338 * c5 - 2.2341286009756676 * c7, v);
    }

    /**
     * This one's weird; unlike {@link #atan2Degrees360(float, float)}, it can return negative results.
     * @param v any finite float
     * @return between -90 and 90
     */
    private static float atnDegrees360(final float v) {
        final float n = Math.abs(v);
        final float c = (n - 1f) / (n + 1f);
        final float c2 = c * c;
        final float c3 = c * c2;
        final float c5 = c3 * c2;
        final float c7 = c5 * c2;
        return Math.copySign(45f + 57.25080232616455f * c - 18.402367325992856f * c3
                + 8.381031821523338f * c5 - 2.2341286009756676f * c7, v);
    }
    /**
     * Altered-range approximation of the frequently-used trigonometric method atan2, taking y and x positions as
     * doubles and returning an angle measured in turns from 0.0 to 360.0 (inclusive), with one cycle over the range
     * equivalent to 2PI radians or 1 turn. Takes y and x (in that unusual order) as doubles. Will never return a
     * negative number, which may help avoid costly floating-point modulus when you actually want a positive number.
     * <br>
     * Credit for this goes to the 1955 research study "Approximations for Digital Computers," by RAND Corporation. This
     * is sheet 9's algorithm, which is the second-fastest and second-least precise. The algorithm on sheet 8 is faster,
     * but only by a very small degree, and is considerably less precise. That study provides an {@link #atan(double)}
     * method, and the small code to make that work as atan2Degrees360() was worked out from Wikipedia.
     * <br>
     * See also {@link #atan2_(double, double)} for a version that returns a measurement as a fraction of a turn, or
     * {@link #atan2(double, double)} for the typical radians. You
     * can also use {@link #atan2Degrees(double, double)} to produce a result in degrees from -180 to 180.
     * @param y y-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @param x x-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @return the angle to the given point, as a double from 0.0 to 360.0, inclusive
     */
    public static double atan2Degrees360(final double y, double x) {
        double n = y / x;
        if(n != n) n = (y == x ? 1.0 : -1.0); // if both y and x are infinite, n would be NaN
        else if(n - n != n - n) x = 0.0; // if n is infinite, y is infinitely larger than x.
        if(x > 0) {
            if(y >= 0)
                return atnDegrees360(n);
            else
                return atnDegrees360(n) + 360.0;
        }
        else if(x < 0) {
            return atnDegrees360(n) + 180.0;
        }
        else if(y > 0) return x + 90.0;
        else if(y < 0) return x + 270.0;
        else return x + y; // returns 0 for 0,0 or NaN if either y or x is NaN
    }

    /**
     * Altered-range approximation of the frequently-used trigonometric method atan2, taking y and x positions as
     * floats and returning an angle measured in turns from 0.0 to 360.0 (inclusive), with one cycle over the range
     * equivalent to 2PI radians or 1 turn. Takes y and x (in that unusual order) as floats. Will never return a
     * negative number, which may help avoid costly floating-point modulus when you actually want a positive number.
     * <br>
     * Credit for this goes to the 1955 research study "Approximations for Digital Computers," by RAND Corporation. This
     * is sheet 9's algorithm, which is the second-fastest and second-least precise. The algorithm on sheet 8 is faster,
     * but only by a very small degree, and is considerably less precise. That study provides an {@link #atan(float)}
     * method, and the small code to make that work as atan2Degrees360() was worked out from Wikipedia.
     * <br>
     * See also {@link #atan2_(float, float)} for a version that returns a measurement as a fraction of a turn, or
     * {@link #atan2(float, float)} for the typical radians. You
     * can also use {@link #atan2Degrees(float, float)} to produce a result in degrees from -180 to 180.
     * @param y y-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @param x x-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @return the angle to the given point, as a float from 0.0 to 360.0, inclusive
     */
    public static float atan2Degrees360(final float y, float x) {
        float n = y / x;
        if(n != n) n = (y == x ? 1f : -1f); // if both y and x are infinite, n would be NaN
        else if(n - n != n - n) x = 0f; // if n is infinite, y is infinitely larger than x.
        if(x > 0) {
            if(y >= 0)
                return atnDegrees360(n);
            else
                return atnDegrees360(n) + 360f;
        }
        else if(x < 0) {
            return atnDegrees360(n) + 180f;
        }
        else if(y > 0) return x + 90f;
        else if(y < 0) return x + 270f;
        else return x + y; // returns 0 for 0,0 or NaN if either y or x is NaN
    }


    /**
     * Arc sine approximation with very low error, using an algorithm from the 1955 research study
     * "Approximations for Digital Computers," by RAND Corporation (this is sheet 35's algorithm, which is the fastest
     * and least precise). This method is usually much faster than {@link Math#asin(double)}, but is somewhat less
     * precise than Math's implementation. It is currently the same as libGDX's approximation in their MathUtils.
     * @param x an input to the inverse sine function, from -1 to 1 inclusive
     * @return an output from the inverse sine function, from PI/-2.0 to PI/2.0 inclusive.
     */
    public static float asin(final float x) {
        final float x2 = x * x;
        final float x3 = x * x2;
        if (x >= 0f) {
            return 1.5707963267948966f - (float) Math.sqrt(1f - x) *
                    (1.5707288f - 0.2121144f * x + 0.0742610f * x2 - 0.0187293f * x3);
        }
        else {
            return -1.5707964f + (float) Math.sqrt(1f + x) *
                    (1.5707288f + 0.2121144f * x + 0.0742610f * x2 + 0.0187293f * x3);
        }
    }
    /**
     * Arc cosine approximation with very low error, using an algorithm from the 1955 research study
     * "Approximations for Digital Computers," by RAND Corporation (this is sheet 35's algorithm, which is the fastest
     * and least precise). This method is usually much faster than {@link Math#acos(double)}, but is somewhat less
     * precise than Math's implementation. It is currently the same as libGDX's approximation in their MathUtils.
     * <br>
     * Accuracy: absolute error 0.000028450, relative error -0.000000011, max error 0.000067548 .
     * @param x an input to the inverse cosine function, from -1 to 1 inclusive
     * @return an output from the inverse cosine function, from 0 to PI inclusive.
     */
    public static float acos(final float x) {
        final float x2 = x * x;
        final float x3 = x * x2;
        if (x >= 0f) {
            return (float) Math.sqrt(1f - x) * (1.5707288f - 0.2121144f * x + 0.0742610f * x2 - 0.0187293f * x3);
        }
        else {
            return 3.14159265358979323846f - (float) Math.sqrt(1f + x) * (1.5707288f + 0.2121144f * x + 0.0742610f * x2 + 0.0187293f * x3);
        }
    }

    /**
     * Arc sine approximation with very low error, using an algorithm from the 1955 research study
     * "Approximations for Digital Computers," by RAND Corporation (this is sheet 35's algorithm, which is the fastest
     * and least precise). This method is usually much faster than {@link Math#asin(double)}, but is somewhat less
     * precise than Math's implementation. It is currently the same as libGDX's approximation in their MathUtils, except
     * that this takes a double and returns a double.
     * <br>
     * Accuracy: absolute error 0.000028447, relative error -0.000000033, max error 0.000067592 .
     * @param x an input to the inverse sine function, from -1 to 1 inclusive
     * @return an output from the inverse sine function, from PI/-2.0 to PI/2.0 inclusive.
     */
    public static double asin(final double x) {
        final double x2 = x * x;
        final double x3 = x * x2;
        if (x >= 0.0) {
            return 1.5707963267948966 - Math.sqrt(1.0 - x) *
                    (1.5707288 - 0.2121144 * x + 0.0742610 * x2 - 0.0187293 * x3);
        }
        else {
            return -1.5707963267948966 + Math.sqrt(1.0 + x) *
                    (1.5707288 + 0.2121144 * x + 0.0742610 * x2 + 0.0187293 * x3);
        }
    }
    /**
     * Arc cosine approximation with very low error, using an algorithm from the 1955 research study
     * "Approximations for Digital Computers," by RAND Corporation (this is sheet 35's algorithm, which is the fastest
     * and least precise). This method is usually much faster than {@link Math#acos(double)}, but is somewhat less
     * precise than Math's implementation. It is currently the same as libGDX's approximation in their MathUtils, except
     * that this takes a double and returns a double.
     * <br>
     * Accuracy: absolute error 0.000028450, relative error -0.000000011, max error 0.000067548 .
     * @param x an input to the inverse cosine function, from -1 to 1 inclusive
     * @return an output from the inverse cosine function, from 0 to PI inclusive.
     */
    public static double acos(final double x) {
        final double x2 = x * x;
        final double x3 = x * x2;
        if (x >= 0.0) {
            return Math.sqrt(1.0 - x) * (1.5707288 - 0.2121144 * x + 0.0742610 * x2 - 0.0187293 * x3);
        }
        else {
            return 3.14159265358979323846 - Math.sqrt(1.0 + x) * (1.5707288 + 0.2121144 * x + 0.0742610 * x2 + 0.0187293 * x3);
        }
    }
    /**
     * Inverse sine function (arcsine) but with output measured in turns instead of radians. Possible results for this
     * range from 0.75 (inclusive) to 1.0 (exclusive), and continuing past that to 0.0 (inclusive) to 0.25 (inclusive).
     * <br>
     * This method is extremely similar to the non-turn approximation, but it never returns a negative result.
     * @param x a double from -1.0 to 1.0 (both inclusive), usually the output of sin_() or cos_()
     * @return one of the values that would produce {@code n} if it were passed to {@link #sin_(double)}
     */
    public static double asin_(final double x)
    {
        final double x2 = x * x;
        final double x3 = x * x2;
        if (x >= 0.0) {
            return 0.25 - Math.sqrt(1.0 - x) *
                    (0.24998925277680106 - 0.033759055260971525 * x + 0.01181900522894724 * x2 - 0.0029808606756510357 * x3);
        }
        else {
            return 0.75 + Math.sqrt(1.0 + x) *
                    (0.24998925277680106 + 0.033759055260971525 * x + 0.01181900522894724 * x2 + 0.0029808606756510357 * x3);
        }
    }
    /**
     * Inverse cosine function (arccos) but with output measured in turns instead of radians. Possible results for this
     * range from 0.0 (inclusive) to 0.5 (inclusive).
     * <br>
     * This method is extremely similar to the non-turn approximation.
     * @param x a double from -1.0 to 1.0 (both inclusive), usually the output of sin_() or cos_()
     * @return one of the values that would produce {@code n} if it were passed to {@link #cos_(double)}
     */
    public static double acos_(final double x)
    {
        final double x2 = x * x;
        final double x3 = x * x2;
        if (x >= 0.0) {
            return Math.sqrt(1.0 - x) * (0.24998925277680106 - 0.033759055260971525 * x + 0.01181900522894724 * x2 - 0.0029808606756510357 * x3);
        }
        else {
            return 0.5 - Math.sqrt(1.0 + x) * (0.24998925277680106 + 0.033759055260971525 * x + 0.01181900522894724 * x2 + 0.0029808606756510357 * x3);
        }
    }


    /**
     * Inverse sine function (arcsine) but with output measured in turns instead of radians. Possible results for this
     * range from 0.75f (inclusive) to 1.0f (exclusive), and continuing past that to 0.0f (inclusive) to 0.25f
     * (inclusive).
     * <br>
     * This method is extremely similar to the non-turn approximation, but it never returns a negative result.
     * @param x a float from -1.0f to 1.0f (both inclusive), usually the output of sin_() or cos_()
     * @return one of the values that would produce {@code n} if it were passed to {@link #sin_(float)}
     */
    public static float asin_(final float x)
    {
        final float x2 = x * x;
        final float x3 = x * x2;
        if (x >= 0f) {
            return 0.25f - (float) Math.sqrt(1f - x) *
                    (0.24998925277680106f - 0.033759055260971525f * x + 0.01181900522894724f * x2 - 0.0029808606756510357f * x3);
        }
        else {
            return 0.75f + (float) Math.sqrt(1f + x) *
                    (0.24998925277680106f + 0.033759055260971525f * x + 0.01181900522894724f * x2 + 0.0029808606756510357f * x3);
        }
    }
    /**
     * Inverse cosine function (arccos) but with output measured in turns instead of radians. Possible results for this
     * range from 0.0f (inclusive) to 0.5f (inclusive).
     * <br>
     * This method is extremely similar to the non-turn approximation.
     * @param x a float from -1.0f to 1.0f (both inclusive), usually the output of sin_() or cos_()
     * @return one of the values that would produce {@code n} if it were passed to {@link #cos_(float)}
     */
    public static float acos_(final float x)
    {
        final float x2 = x * x;
        final float x3 = x * x2;
        if (x >= 0f) {
            return (float) Math.sqrt(1f - x) * (0.24998925277680106f - 0.033759055260971525f * x + 0.01181900522894724f * x2 - 0.0029808606756510357f * x3);
        }
        else {
            return 0.5f - (float) Math.sqrt(1f + x) * (0.24998925277680106f + 0.033759055260971525f * x + 0.01181900522894724f * x2 + 0.0029808606756510357f * x3);
        }
    }

    /**
     * Linearly interpolates between fromValue to toValue on progress position.
     * @param fromValue starting float value; can be any finite float
     * @param toValue ending float value; can be any finite float
     * @param progress how far the interpolation should go, between 0 (equal to fromValue) and 1 (equal to toValue)
     */
    public static float lerp (final float fromValue, final float toValue, final float progress) {
        return fromValue + (toValue - fromValue) * progress;
    }
    /**
     * Linearly normalizes value from a range. Range must not be empty. This is the inverse of
     * {@link #lerp(float, float, float)}.
     * @param rangeStart Range start normalized to 0
     * @param rangeEnd Range end normalized to 1
     * @param value Value to normalize
     * @return Normalized value. Values outside the range are not clamped to 0 and 1 */
    public static float norm (float rangeStart, float rangeEnd, float value) {
        return (value - rangeStart) / (rangeEnd - rangeStart);
    }

    /**
     * Returns the largest integer less than or equal to the specified float. This method will only properly floor
     * floats from {@code -16384} to {@code Float.MAX_VALUE - 16384}.
     * @param value the float to find the floor of
     * @return the int floor of {@code value}, that is, the largest int that is less than or equal to {@code value}
     */
    public static int floor (float value) {
        return (int)(value + 16384.0) - 16384;
    }

    /**
     * A generalization on bias and gain functions that can represent both; this version is branch-less.
     * This is based on <a href="https://arxiv.org/abs/2010.09714">this micro-paper</a> by Jon Barron, which
     * generalizes the earlier bias and gain rational functions by Schlick. The second and final page of the
     * paper has useful graphs of what the s (shape) and t (turning point) parameters do; shape should be 0
     * or greater, while turning must be between 0 and 1, inclusive. This effectively combines two different
     * curving functions so they continue into each other when x equals turning. The shape parameter will
     * cause this to imitate "smoothstep-like" splines when greater than 1 (where the values ease into their
     * starting and ending levels), or to be the inverse when less than 1 (where values start like square
     * root does, taking off very quickly, but also end like square does, landing abruptly at the ending
     * level). You should only give x values between 0 and 1, inclusive.
     * @param x progress through the spline, from 0 to 1, inclusive
     * @param shape must be greater than or equal to 0; values greater than 1 are "normal interpolations"
     * @param turning a value between 0.0 and 1.0, inclusive, where the shape changes
     * @return a float between 0 and 1, inclusive
     */
    public static float barronSpline(final float x, final float shape, final float turning) {
        final float d = turning - x;
        final int f = BitConversion.floatToRawIntBits(d) >> 31, n = f | 1;
        return ((turning * n - f) * (x + f)) / (Float.MIN_NORMAL - f + (x + shape * d) * n) - f;
    }

}
