package com.github.tommyettinger.colorful;

/**
 * Some alternative approximations to sin(), cos(), asin(), acos(), and atan2(), including the option to measure
 * most values in turns instead of radians (where 1 turn equals 2pi radians). The convention here is to have methods
 * that take input in turns or produce output in turns use a {@code _} suffix, like {@link #sin_(double)}, for turns to
 * never be returned as a negative value but for negative turn inputs to be accepted, and for the output type to match
 * the input type (float input gives float output, double input gives double output).
 * Created by Tommy Ettinger on 8/6/2019.
 */
public class TrigTools {

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
     * Close approximation of the frequently-used trigonometric method atan2, with higher precision than libGDX's atan2
     * approximation. Maximum error is below 0.001 radians.
     * Takes y and x (in that unusual order) as doubles, and returns the angle from the origin to that point in radians.
     * It is about 5 times faster than {@link Math#atan2(double, double)} (roughly 17 ns instead of roughly 88 ns for
     * Math, though the computer was under some load during testing). It is almost identical in speed to libGDX'
     * MathUtils approximation of the same method; MathUtils seems to have worse average error, though.
     * Credit to Nic Taylor and imuli, with Taylor publishing
     * <a href="https://www.dsprelated.com/showarticle/1052.php">this nice post</a> and imuli commenting with very
     * handy information that makes this approach usable. This method changed from an earlier
     * technique that was twice as fast but had very poor quality, enough to be visually noticeable. See also
     * {@link #atan2_(double, double)} if you don't want a mess converting to degrees or some other measurement, since
     * that method returns an angle from 0.0 (equal to 0 degrees) to 1.0 (equal to 360 degrees).
     * @param y y-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @param x x-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @return the angle to the given point, in radians as a double; ranges from -PI to PI
     */
    public static double atan2(double y, double x)
    {
        if(y == 0.0 && x >= 0.0) return 0.0;
        double ay = Math.abs(y), ax = Math.abs(x);
        boolean invert = ay > ax;
        double z = invert ? ax / ay : ay / ax;
        z = ((((0.141499  * z) - 0.343315 ) * z - 0.016224 ) * z + 1.003839 ) * z - 0.000158 ;
        if (invert) z = 1.5707963267948966 - z;
        if (x < 0) z = 3.141592653589793 - z;
        return Math.copySign(z, y);
    }

    /**
     * Close approximation of the frequently-used trigonometric method atan2, with higher precision than libGDX's atan2
     * approximation. Maximum error is below 0.001 radians.
     * Takes y and x (in that unusual order) as floats, and returns the angle from the origin to that point in radians.
     * It is about 5 times faster than {@link Math#atan2(double, double)} (roughly 17 ns instead of roughly 88 ns for
     * Math, though the computer was under some load during testing). It is almost identical in speed to libGDX'
     * MathUtils approximation of the same method; MathUtils seems to have worse average error, though.
     * Credit to Nic Taylor and imuli, with Taylor publishing
     * <a href="https://www.dsprelated.com/showarticle/1052.php">this nice post</a> and imuli commenting with very
     * handy information that makes this approach usable. This method changed from an earlier
     * technique that was twice as fast but had very poor quality, enough to be visually noticeable. See also
     * {@link #atan2_(float, float)} if you don't want a mess converting to degrees or some other measurement, since
     * that method returns an angle from 0f (equal to 0 degrees) to 1f (equal to 360 degrees).
     * @param y y-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @param x x-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @return the angle to the given point, in radians as a float; ranges from -PI to PI
     */
    public static float atan2(float y, float x) {
        if (y == 0f && x >= 0f) return 0f;
        float ay = Math.abs(y), ax = Math.abs(x);
        boolean invert = ay > ax;
        float z = invert ? ax / ay : ay / ax;
        z = ((((0.141499f * z) - 0.343315f) * z - 0.016224f) * z + 1.003839f) * z - 0.000158f;
        if (invert) z = 1.5707963267948966f - z;
        if (x < 0) z = 3.141592653589793f - z;
        return Math.copySign(z, y);
    }

    /**
     * Altered-range approximation of the frequently-used trigonometric method atan2, taking y and x positions as 
     * doubles and returning an angle measured in turns from 0.0 to 1.0 (inclusive), with one cycle over the range
     * equivalent to 360 degrees or 2PI radians. You can multiply the angle by {@code 6.2831855f} to change to radians,
     * or by {@code 360f} to change to degrees. Takes y and x (in that unusual order) as doubles. Will never return a
     * negative number, which may help avoid costly floating-point modulus when you actually want a positive number.
     * Credit to Nic Taylor and imuli, with Taylor publishing
     * <a href="https://www.dsprelated.com/showarticle/1052.php">this nice post</a> and imuli commenting with very
     * handy information that makes this approach usable. Note that
     * {@link #atan2(double, double)} returns an angle in radians and can return negative results, which may be fine for
     * many tasks; these two methods are extremely close in implementation and speed.
     * @param y y-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @param x x-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @return the angle to the given point, as a double from 0.0 to 1.0, inclusive
     */
    public static double atan2_(double y, double x)
    {
        if(y == 0.0 && x >= 0.0) return 0.0;
        double ay = Math.abs(y), ax = Math.abs(x);
        boolean invert = ay > ax;
        double z = invert ? ax/ay : ay/ax;
        z = (((((0.022520265292560102) * z) - (0.054640279287594046)) * z - (0.0025821297967229097)) * z + (0.1597659389184251)) * z - (0.000025146481008519463);
        if(invert) z = 0.25 - z;
        if(x < 0) z = 0.5 - z;
        return y < 0 ? (int)(1+z) - z : z;

    }
    /**
     * Altered-range approximation of the frequently-used trigonometric method atan2, taking y and x positions as floats
     * and returning an angle measured in turns from 0.0f to 1.0f, with one cycle over the range equivalent to 360
     * degrees or 2PI radians. You can multiply the angle by {@code 6.2831855f} to change to radians, or by {@code 360f}
     * to change to degrees. Takes y and x (in that unusual order) as floats. Will never return a negative number, which
     * may help avoid costly floating-point modulus when you actually want a positive number.
     * Credit to Nic Taylor and imuli, with Taylor publishing
     * <a href="https://www.dsprelated.com/showarticle/1052.php">this nice post</a> and imuli commenting with very
     * handy information that makes this approach usable. Note that
     * {@link #atan2(float, float)} returns an angle in radians and can return negative results, which may be fine for
     * many tasks; these two methods are extremely close in implementation and speed.
     * @param y y-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @param x x-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @return the angle to the given point, as a float from 0.0f to 1.0f, inclusive
     */
    public static float atan2_(float y, float x)
    {
        if(y == 0.0 && x >= 0.0) return 0f;
        float ay = Math.abs(y), ax = Math.abs(x);
        boolean invert = ay > ax;
        float z = invert ? ax/ay : ay/ax;
        z = (((((0.022520265292560102f) * z) - (0.054640279287594046f)) * z - (0.0025821297967229097f)) * z + (0.1597659389184251f)) * z - (0.000025146481008519463f);
        if(invert) z = 0.25f - z;
        if(x < 0) z = 0.5f - z;
        return y < 0 ? (int)(1+z) - z : z;
    }

    /**
     * Close approximation of the frequently-used trigonometric method atan2, with higher precision than libGDX's atan2
     * approximation, and giving a result in degrees from -180 to 180. Maximum error is below 0.1 degrees.
     * Takes y and x (in that unusual order) as doubles, and returns the angle from the origin to that point in degrees.
     * It is about 5 times faster than {@link Math#atan2(double, double)} (roughly 17 ns instead of roughly 88 ns for
     * Math, though the computer was under some load during testing). It is almost identical in speed to libGDX'
     * MathUtils approximation after converting to degrees; MathUtils seems to have worse average error, though.
     * Credit to Nic Taylor and imuli, with Taylor publishing
     * <a href="https://www.dsprelated.com/showarticle/1052.php">this nice post</a> and imuli commenting with very
     * handy information that makes this approach usable.
     * <br>
     * See also {@link #atan2Degrees360(double, double)}, which is just like this but returns an angle from 0 to 360,
     * instead of -180 to 180, in case negative angles are undesirable.
     * @param y y-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @param x x-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @return the angle to the given point, in degrees as a double
     */
    public static double atan2Degrees(double y, double x)
    {
        if(y == 0.0 && x >= 0.0) return 0.0;
        double ay = Math.abs(y), ax = Math.abs(x);
        boolean invert = ay > ax;
        double z = invert ? ax / ay : ay / ax;
        z = (((((8.107295505321636)  * z) - (19.670500543533855) ) * z - (0.9295667268202475) ) * z + (57.51573801063304) ) * z - (0.009052733163067006) ;
        if (invert) z = 90 - z;
        if (x < 0) z = 180 - z;
        return Math.copySign(z, y);
    }

    /**
     * Close approximation of the frequently-used trigonometric method atan2, with higher precision than libGDX's atan2
     * approximation, and giving a result in degrees from -180 to 180. Maximum error is below 0.1 degrees.
     * Takes y and x (in that unusual order) as floats, and returns the angle from the origin to that point in degrees.
     * It is about 5 times faster than {@link Math#atan2(double, double)} (roughly 17 ns instead of roughly 88 ns for
     * Math, though the computer was under some load during testing). It is almost identical in speed to libGDX'
     * MathUtils approximation after converting to degrees; MathUtils seems to have worse average error, though.
     * Credit to Nic Taylor and imuli, with Taylor publishing
     * <a href="https://www.dsprelated.com/showarticle/1052.php">this nice post</a> and imuli commenting with very
     * handy information that makes this approach usable.
     * <br>
     * See also {@link #atan2Degrees360(float, float)}, which is just like this but returns an angle from 0 to 360,
     * instead of -180 to 180, in case negative angles are undesirable.
     * @param y y-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @param x x-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @return the angle to the given point, in degrees as a float
     */
    public static float atan2Degrees(float y, float x)
    {
        if(y == 0f && x >= 0f) return 0f;
        float ax = Math.abs(x), ay = Math.abs(y);
        boolean invert = ay > ax;
        float z = invert ? ax / ay : ay / ax;
        z = (((((8.107295505321636f)  * z) - (19.670500543533855f) ) * z - (0.9295667268202475f) ) * z + (57.51573801063304f) ) * z - (0.009052733163067006f) ;
        if (invert) z = 90 - z;
        if (x < 0) z = 180 - z;
        return Math.copySign(z, y);
    }

    /**
     * Close approximation of the frequently-used trigonometric method atan2, with higher precision than libGDX's atan2
     * approximation, and giving a result in degrees from 0 to 360 (both inclusive). Maximum error is below 0.1 degrees.
     * Takes y and x (in that unusual order) as doubles, and returns the angle from the origin to that point in degrees.
     * It is about 5 times faster than {@link Math#atan2(double, double)} (roughly 17 ns instead of roughly 88 ns for
     * Math, though the computer was under some load during testing). It is almost identical in speed to libGDX'
     * MathUtils approximation after converting to degrees; MathUtils seems to have worse average error, though.
     * Credit to Nic Taylor and imuli, with Taylor publishing
     * <a href="https://www.dsprelated.com/showarticle/1052.php">this nice post</a> and imuli commenting with very
     * handy information that makes this approach usable.
     * <br>
     * See also {@link #atan2Degrees(double, double)}, which is just like this but returns an angle from -180 to 180,
     * matching {@link Math#atan2(double, double)}'s convention.
     * @param y y-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @param x x-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @return the angle to the given point, in degrees as a double
     */
    public static double atan2Degrees360(double y, double x)
    {
        if(y == 0.0 && x >= 0.0) return 0.0;
        double ay = Math.abs(y), ax = Math.abs(x);
        boolean invert = ay > ax;
        double z = invert ? ax / ay : ay / ax;
        z = (((((8.107295505321636)  * z) - (19.670500543533855) ) * z - (0.9295667268202475) ) * z + (57.51573801063304) ) * z - (0.009052733163067006) ;
        if (invert) z = 90 - z;
        if (x < 0) z = 180 - z;
        return y < 0 ? 360 - z : z;
    }
    /**
     * Close approximation of the frequently-used trigonometric method atan2, with higher precision than libGDX's atan2
     * approximation, and giving a result in degrees from 0 to 360 (both inclusive). Maximum error is below 0.1 degrees.
     * Takes y and x (in that unusual order) as floats, and returns the angle from the origin to that point in degrees.
     * It is about 5 times faster than {@link Math#atan2(double, double)} (roughly 17 ns instead of roughly 88 ns for
     * Math, though the computer was under some load during testing). It is almost identical in speed to libGDX'
     * MathUtils approximation after converting to degrees; MathUtils seems to have worse average error, though.
     * Credit to Nic Taylor and imuli, with Taylor publishing
     * <a href="https://www.dsprelated.com/showarticle/1052.php">this nice post</a> and imuli commenting with very
     * handy information that makes this approach usable.
     * <br>
     * See also {@link #atan2Degrees(float, float)}, which is just like this but returns an angle from -180 to 180,
     * matching {@link Math#atan2(double, double)}'s convention.
     * @param y y-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @param x x-component of the point to find the angle towards; note the parameter order is unusual by convention
     * @return the angle to the given point, in degrees as a float
     */
    public static float atan2Degrees360(float y, float x)
    {
        if(y == 0f && x >= 0f) return 0f;
        float ax = Math.abs(x), ay = Math.abs(y);
        boolean invert = ay > ax;
        float z = invert ? ax / ay : ay / ax;
        z = (((((8.107295505321636f)  * z) - (19.670500543533855f) ) * z - (0.9295667268202475f) ) * z + (57.51573801063304f) ) * z - (0.009052733163067006f) ;
        if (invert) z = 90 - z;
        if (x < 0) z = 180 - z;
        return y < 0 ? 360 - z : z;
    }

    /**
     * Arc sine approximation with very low error, using an algorithm from the 1955 research study
     * "Approximations for Digital Computers," by RAND Corporation (this is sheet 35's algorithm, which is the fastest
     * and least precise). This method is usually much faster than {@link Math#asin(double)}, but is somewhat less
     * precise than Math's implementation. It is currently much more precise than libGDX's approximation in their
     * MathUtils, and is a little faster.
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
     * precise than Math's implementation. It is currently much more precise than libGDX's approximation in their
     * MathUtils, and is a little faster.
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
     * This method is extremely similar to the non-turn approximation.
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
     * This method is extremely similar to the non-turn approximation.
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
}
