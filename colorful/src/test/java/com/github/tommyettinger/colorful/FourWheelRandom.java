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

package com.github.tommyettinger.colorful;

import com.badlogic.gdx.utils.NumberUtils;

import java.util.Random;

/**
 * A faster and much-higher-quality substitute for {@link Random}. This also offers a more substantial API for
 * commonly-used functions.
 * <br>
 * This fills in much of the functionality of MathUtils in libGDX, though with all code as instance methods
 * instead of static methods, and some things renamed (randomTriangular() became {@link #nextTriangular()},
 * for instance, and random() became {@link #nextFloat()}).
 *
 * @author Tommy Ettinger
 */
public class FourWheelRandom extends Random {
	/**
	 * Can be any long value.
	 */
	protected long stateA;

	/**
	 * Can be any long value.
	 */
	protected long stateB;

	/**
	 * Can be any long value.
	 */
	protected long stateC;

	/**
	 * Can be any long value.
	 */
	protected long stateD;

	/**
	 * Creates a new FourWheelRandom. This constructor sets the states of the
	 * random number generator to values very likely to be distinct from
	 * any other invocation of this constructor.
	 */
	public FourWheelRandom() {
		super();
		stateA = super.nextLong();
		stateB = super.nextLong();
		stateC = super.nextLong();
		stateD = super.nextLong();
	}

	/**
	 * Creates a new FourWheelRandom using a single {@code long} seed.
	 *
	 * @param seed the initial seed
	 * @see #setSeed(long)
	 */
	public FourWheelRandom(long seed) {
		super(seed);
		setSeed(seed);
	}

	/**
	 * Creates a new FourWheelRandom using the given seeds to set the corresponding states verbatim.
	 *
	 * @param seedA any long; will be used exactly to set stateA as with {@link #setStateA(long)}
	 * @param seedB any long; will be used exactly to set stateB as with {@link #setStateB(long)}
	 * @param seedC any long; will be used exactly to set stateC as with {@link #setStateC(long)}
	 * @param seedD any long; will be used exactly to set stateD as with {@link #setStateD(long)}
	 */
	public FourWheelRandom(final long seedA, final long seedB, final long seedC, final long seedD) {
		super(seedA + seedB ^ seedC - seedD);
		this.stateA = seedA;
		this.stateB = seedB;
		this.stateC = seedC;
		this.stateD = seedD;
	}

	/**
	 * FourWheelRandom has four possible states, all {@code long}.
	 * @return 4 (four)
	 */
	public int getStateCount() {
		return 4;
	}

	/**
	 * Gets the state determined by {@code selection}, as-is. The value for selection should be
	 * between 0 and 3, inclusive; if it is any other value this gets state D as if 3 was given.
	 * @param selection used to select which state variable to get; generally 0, 1, 2, or 3
	 * @return the value of the selected state
	 */
	public long getSelectedState(int selection) {
		switch (selection) {
			case 0:
				return stateA;
			case 1:
				return stateB;
			case 2:
				return stateC;
			default:
				return stateD;
		}
	}

	/**
	 * Sets one of the states, determined by {@code selection}, to {@code value}, as-is.
	 * Selections 0, 1, 2, and 3 refer to states A, B, C, and D,  and if the selection is anything
	 * else, this treats it as 3 and sets stateD.
	 * @param selection used to select which state variable to set; generally 0, 1, 2, or 3
	 * @param value the exact value to use for the selected state, if valid
	 */
	public void setSelectedState(int selection, long value) {
		switch (selection) {
			case 0:
				stateA = value;
				break;
			case 1:
				stateB = value;
				break;
			case 2:
				stateC = value;
				break;
			default:
				stateD = value;
				break;
		}
	}

	/**
	 * This initializes all 4 states of the generator to random values based on the given seed.
	 * (2 to the 64) possible initial generator states can be produced here, all with a different
	 * first value returned by {@link #nextLong()} (because {@code stateD} is guaranteed to be
	 * different for every different {@code seed}).
	 * @param seed the initial seed; may be any long
	 */
	public void setSeed(long seed) {
		long x = (seed += 0x9E3779B97F4A7C15L);
		x ^= x >>> 27;
		x *= 0x3C79AC492BA7B653L;
		x ^= x >>> 33;
		x *= 0x1C69B3F74AC4AE35L;
		stateA = x ^ x >>> 27;
		x = (seed += 0x9E3779B97F4A7C15L);
		x ^= x >>> 27;
		x *= 0x3C79AC492BA7B653L;
		x ^= x >>> 33;
		x *= 0x1C69B3F74AC4AE35L;
		stateB = x ^ x >>> 27;
		x = (seed += 0x9E3779B97F4A7C15L);
		x ^= x >>> 27;
		x *= 0x3C79AC492BA7B653L;
		x ^= x >>> 33;
		x *= 0x1C69B3F74AC4AE35L;
		stateC = x ^ x >>> 27;
		x = (seed + 0x9E3779B97F4A7C15L);
		x ^= x >>> 27;
		x *= 0x3C79AC492BA7B653L;
		x ^= x >>> 33;
		x *= 0x1C69B3F74AC4AE35L;
		stateD = x ^ x >>> 27;
	}

	public long getStateA() {
		return stateA;
	}

	/**
	 * Sets the first part of the state.
	 * @param stateA can be any long
	 */
	public void setStateA(long stateA) {
		this.stateA = stateA;
	}

	public long getStateB() {
		return stateB;
	}

	/**
	 * Sets the second part of the state.
	 * @param stateB can be any long
	 */
	public void setStateB(long stateB) {
		this.stateB = stateB;
	}

	public long getStateC() {
		return stateC;
	}

	/**
	 * Sets the third part of the state.
	 * @param stateC can be any long
	 */
	public void setStateC(long stateC) {
		this.stateC = stateC;
	}

	public long getStateD() {
		return stateD;
	}

	/**
	 * Sets the fourth part of the state. Note that if you call {@link #nextLong()}
	 * immediately after this, it will return the given {@code stateD} as-is, so you
	 * may want to call some random generation methods (such as nextLong()) and discard
	 * the results after setting the state.
	 * @param stateD can be any long
	 */
	public void setStateD(long stateD) {
		this.stateD = stateD;
	}

	/**
	 * Sets the state completely to the given four state variables.
	 * This is the same as calling {@link #setStateA(long)}, {@link #setStateB(long)},
	 * {@link #setStateC(long)}, and {@link #setStateD(long)} as a group. You may want
	 * to call {@link #nextLong()} a few times after setting the states like this, unless
	 * the value for stateD (in particular) is already adequately random; the first call
	 * to {@link #nextLong()}, if it is made immediately after calling this, will return {@code stateD} as-is.
	 * @param stateA the first state; can be any long
	 * @param stateB the second state; can be any long
	 * @param stateC the third state; can be any long
	 * @param stateD the fourth state; this will be returned as-is if the next call is to {@link #nextLong()}
	 */
	public void setState(long stateA, long stateB, long stateC, long stateD) {
		this.stateA = stateA;
		this.stateB = stateB;
		this.stateC = stateC;
		this.stateD = stateD;
	}

	/**
	 * Generates the next pseudorandom number with a specific maximum size in bits (not a max number).
	 * If you want to get a random number in a range, you should usually use {@link #nextInt(int)} instead.
	 * For some specific cases, this method is more efficient and less biased than {@link #nextInt(int)}.
	 * For {@code bits} values between 1 and 30, this should be similar in effect to
	 * {@code nextInt(1 << bits)}; though it won't typically produce the same values, they will have
	 * the correct range. If {@code bits} is 31, this can return any non-negative {@code int}; note that
	 * {@code nextInt(1 << 31)} won't behave this way because {@code 1 << 31} is negative. If
	 * {@code bits} is 32 (or 0), this can return any {@code int}.
	 *
	 * <p>The general contract of {@code next} is that it returns an
	 * {@code int} value and if the argument {@code bits} is between
	 * {@code 1} and {@code 32} (inclusive), then that many low-order
	 * bits of the returned value will be (approximately) independently
	 * chosen bit values, each of which is (approximately) equally
	 * likely to be {@code 0} or {@code 1}.
	 * <p>
	 *
	 * @param bits the amount of random bits to request, from 1 to 32
	 * @return the next pseudorandom value from this random number
	 * generator's sequence
	 */
	public int next (int bits) {
		final long fa = this.stateA;
		final long fb = this.stateB;
		final long fc = this.stateC;
		final long fd = this.stateD;
		this.stateA = 0xD1342543DE82EF95L * fd;
		this.stateB = fa + 0xC6BC279692B5C323L;
		this.stateC = Long.rotateLeft(fb, 47) - fd;
		this.stateD = fb ^ fc;
		return (int)(fd) >>> 32 - bits;
	}

	/**
	 * Generates random bytes and places them into a user-supplied
	 * byte array.  The number of random bytes produced is equal to
	 * the length of the byte array.
	 *
	 * @param bytes the byte array to fill with random bytes
	 * @throws NullPointerException if the byte array is null
	 */
	public void nextBytes (byte[] bytes) {
		for (int i = 0; i < bytes.length; ) { for (long r = nextLong(), n = Math.min(bytes.length - i, 8); n-- > 0; r >>>= 8) { bytes[i++] = (byte)r; } }
	}

	/**
	 * Returns the next pseudorandom, uniformly distributed {@code int}
	 * value from this random number generator's sequence. The general
	 * contract of {@code nextInt} is that one {@code int} value is
	 * pseudorandomly generated and returned. All 2<sup>32</sup> possible
	 * {@code int} values are produced with (approximately) equal probability.
	 *
	 * @return the next pseudorandom, uniformly distributed {@code int}
	 * value from this random number generator's sequence
	 */
	public int nextInt () {
		final long fa = this.stateA;
		final long fb = this.stateB;
		final long fc = this.stateC;
		final long fd = this.stateD;
		this.stateA = 0xD1342543DE82EF95L * fd;
		this.stateB = fa + 0xC6BC279692B5C323L;
		this.stateC = Long.rotateLeft(fb, 47) - fd;
		this.stateD = fb ^ fc;
		return (int) fd;
	}

	/**
	 * Returns a pseudorandom, uniformly distributed {@code int} value
	 * between 0 (inclusive) and the specified value (exclusive), drawn from
	 * this random number generator's sequence.  The general contract of
	 * {@code nextInt} is that one {@code int} value in the specified range
	 * is pseudorandomly generated and returned.  All {@code bound} possible
	 * {@code int} values are produced with (approximately) equal
	 * probability.
	 * <br>
	 * It should be mentioned that the technique this uses has some bias, depending
	 * on {@code bound}, but it typically isn't measurable without specifically looking
	 * for it. Using the method this does allows this method to always advance the state
	 * by one step, instead of a varying and unpredictable amount with the more typical
	 * ways of rejection-sampling random numbers and only using numbers that can produce
	 * an int within the bound without bias.
	 * See <a href="https://www.pcg-random.org/posts/bounded-rands.html">M.E. O'Neill's
	 * blog about random numbers</a> for discussion of alternative, unbiased methods.
	 *
	 * @param bound the upper bound (exclusive). If negative or 0, this always returns 0.
	 * @return the next pseudorandom, uniformly distributed {@code int}
	 * value between zero (inclusive) and {@code bound} (exclusive)
	 * from this random number generator's sequence
	 */
	public int nextInt (int bound) {
		final long fa = this.stateA;
		final long fb = this.stateB;
		final long fc = this.stateC;
		final long fd = this.stateD;
		this.stateA = 0xD1342543DE82EF95L * fd;
		this.stateB = fa + 0xC6BC279692B5C323L;
		this.stateC = Long.rotateLeft(fb, 47) - fd;
		this.stateD = fb ^ fc;
		return (int)(bound * (fd & 0xFFFFFFFFL) >> 32) & ~(bound >> 31);
	}

	/**
	 * Returns a pseudorandom, uniformly distributed {@code int} value between an
	 * inner bound of 0 (inclusive) and the specified {@code outerBound} (exclusive).
	 * This is meant for cases where the outer bound may be negative, especially if
	 * the bound is unknown or may be user-specified. A negative outer bound is used
	 * as the lower bound; a positive outer bound is used as the upper bound. An outer
	 * bound of -1, 0, or 1 will always return 0, keeping the bound exclusive (except
	 * for outer bound 0). This method is slightly slower than {@link #nextInt(int)}.
	 *
	 * @see #nextInt(int) Here's a note about the bias present in the bounded generation.
	 * @param outerBound the outer exclusive bound; may be any int value, allowing negative
	 * @return a pseudorandom int between 0 (inclusive) and outerBound (exclusive)
	 */
	public int nextSignedInt (int outerBound) {
		final long fa = this.stateA;
		final long fb = this.stateB;
		final long fc = this.stateC;
		final long fd = this.stateD;
		this.stateA = 0xD1342543DE82EF95L * fd;
		this.stateB = fa + 0xC6BC279692B5C323L;
		this.stateC = Long.rotateLeft(fb, 47) - fd;
		this.stateD = fb ^ fc;
		outerBound = (int)(outerBound * (fd & 0xFFFFFFFFL) >> 32);
		return outerBound + (outerBound >>> 31);
	}

	/**
	 * Returns a pseudorandom, uniformly distributed {@code int} value between the
	 * specified {@code innerBound} (inclusive) and the specified {@code outerBound}
	 * (exclusive). If {@code outerBound} is less than or equal to {@code innerBound},
	 * this always returns {@code innerBound}.
	 *
	 * <br> For any case where outerBound might be valid but less than innerBound, you
	 * can use {@link #nextSignedInt(int, int)}.
	 *
	 * @see #nextInt(int) Here's a note about the bias present in the bounded generation.
	 * @param innerBound the inclusive inner bound; may be any int, allowing negative
	 * @param outerBound the exclusive outer bound; must be greater than innerBound (otherwise this returns innerBound)
	 * @return a pseudorandom int between innerBound (inclusive) and outerBound (exclusive)
	 */
	public int nextInt (int innerBound, int outerBound) {
		return innerBound + nextInt(outerBound - innerBound);
	}

	/**
	 * Returns a pseudorandom, uniformly distributed {@code int} value between the
	 * specified {@code innerBound} (inclusive) and the specified {@code outerBound}
	 * (exclusive). This is meant for cases where either bound may be negative,
	 * especially if the bounds are unknown or may be user-specified. It is slightly
	 * slower than {@link #nextInt(int, int)}.
	 *
	 * @see #nextInt(int) Here's a note about the bias present in the bounded generation.
	 * @param innerBound the inclusive inner bound; may be any int, allowing negative
	 * @param outerBound the exclusive outer bound; may be any int, allowing negative
	 * @return a pseudorandom int between innerBound (inclusive) and outerBound (exclusive)
	 */
	public int nextSignedInt (int innerBound, int outerBound) {
		return innerBound + nextSignedInt(outerBound - innerBound);
	}

	/**
	 * Returns the next pseudorandom, uniformly distributed {@code long}
	 * value from this random number generator's sequence. The general
	 * contract of {@code nextLong} is that one {@code long} value is
	 * pseudorandomly generated and returned.
	 *
	 * @return the next pseudorandom, uniformly distributed {@code long}
	 * value from this random number generator's sequence
	 */
	public long nextLong() {
		final long fa = this.stateA;
		final long fb = this.stateB;
		final long fc = this.stateC;
		final long fd = this.stateD;
		this.stateA = 0xD1342543DE82EF95L * fd;
		this.stateB = fa + 0xC6BC279692B5C323L;
		this.stateC = Long.rotateLeft(fb, 47) - fd;
		this.stateD = fb ^ fc;
		return fd;
	}

	/**
	 * Returns a pseudorandom, uniformly distributed {@code long} value
	 * between 0 (inclusive) and the specified value (exclusive), drawn from
	 * this random number generator's sequence.  The general contract of
	 * {@code nextLong} is that one {@code long} value in the specified range
	 * is pseudorandomly generated and returned.  All {@code bound} possible
	 * {@code long} values are produced with (approximately) equal
	 * probability, though there is a small amount of bias depending on the bound.
	 *
	 * <br> Note that this advances the state by the same amount as a single call to
	 * {@link #nextLong()}, but introduces some bias when {@code bound} is very large. This will
	 * also advance the state if {@code bound} is 0 or negative, so usage with a variable
	 * bound will advance the state reliably.
	 *
	 * <br> This method has some bias, particularly on larger bounds. Actually measuring
	 * bias with bounds in the trillions or greater is challenging but not impossible, so
	 * don't use this for a real-money gambling purpose. The bias isn't especially
	 * significant, though.
	 *
	 * @see #nextInt(int) Here's a note about the bias present in the bounded generation.
	 * @param bound the upper bound (exclusive). If negative or 0, this always returns 0.
	 * @return the next pseudorandom, uniformly distributed {@code long}
	 * value between zero (inclusive) and {@code bound} (exclusive)
	 * from this random number generator's sequence
	 */
	public long nextLong (long bound) {
		return nextLong(0L, bound);
	}

	/**
	 * Returns a pseudorandom, uniformly distributed {@code long} value between an
	 * inner bound of 0 (inclusive) and the specified {@code outerBound} (exclusive).
	 * This is meant for cases where the outer bound may be negative, especially if
	 * the bound is unknown or may be user-specified. A negative outer bound is used
	 * as the lower bound; a positive outer bound is used as the upper bound. An outer
	 * bound of -1, 0, or 1 will always return 0, keeping the bound exclusive (except
	 * for outer bound 0).
	 *
	 * <p>Note that this advances the state by the same amount as a single call to
	 * {@link #nextLong()}, but introduces some bias when {@code bound} is very large. This
	 * method should be about as fast as {@link #nextLong(long)} , unlike the speed
	 * difference between {@link #nextInt(int)} and {@link #nextSignedInt(int)}.
	 *
	 * @see #nextInt(int) Here's a note about the bias present in the bounded generation.
	 * @param outerBound the outer exclusive bound; may be any long value, allowing negative
	 * @return a pseudorandom long between 0 (inclusive) and outerBound (exclusive)
	 */
	public long nextSignedLong (long outerBound) {
		return nextSignedLong(0L, outerBound);
	}

	/**
	 * Returns a pseudorandom, uniformly distributed {@code long} value between the
	 * specified {@code innerBound} (inclusive) and the specified {@code outerBound}
	 * (exclusive). If {@code outerBound} is less than or equal to {@code innerBound},
	 * this always returns {@code innerBound}.
	 *
	 * <br> For any case where outerBound might be valid but less than innerBound, you
	 * can use {@link #nextSignedLong(long, long)}.
	 *
	 * @see #nextInt(int) Here's a note about the bias present in the bounded generation.
	 * @param inner the inclusive inner bound; may be any long, allowing negative
	 * @param outer the exclusive outer bound; must be greater than innerBound (otherwise this returns innerBound)
	 * @return a pseudorandom long between innerBound (inclusive) and outerBound (exclusive)
	 */
	public long nextLong (long inner, long outer) {
		final long rand = nextLong();
		if(inner >= outer) return inner;
		final long bound = outer - inner;
		final long randLow = rand & 0xFFFFFFFFL;
		final long boundLow = bound & 0xFFFFFFFFL;
		final long randHigh = (rand >>> 32);
		final long boundHigh = (bound >>> 32);
		return inner + (randHigh * boundLow >>> 32) + (randLow * boundHigh >>> 32) + randHigh * boundHigh;
	}

	/**
	 * Returns a pseudorandom, uniformly distributed {@code long} value between the
	 * specified {@code innerBound} (inclusive) and the specified {@code outerBound}
	 * (exclusive). This is meant for cases where either bound may be negative,
	 * especially if the bounds are unknown or may be user-specified.
	 *
	 * @see #nextInt(int) Here's a note about the bias present in the bounded generation.
	 * @param inner the inclusive inner bound; may be any long, allowing negative
	 * @param outer the exclusive outer bound; may be any long, allowing negative
	 * @return a pseudorandom long between innerBound (inclusive) and outerBound (exclusive)
	 */
	public long nextSignedLong (long inner, long outer) {
		final long rand = nextLong();
		if(outer < inner) {
			long t = outer;
			outer = inner + 1L;
			inner = t + 1L;
		}
		final long bound = outer - inner;
		final long randLow = rand & 0xFFFFFFFFL;
		final long boundLow = bound & 0xFFFFFFFFL;
		final long randHigh = (rand >>> 32);
		final long boundHigh = (bound >>> 32);
		return inner + (randHigh * boundLow >>> 32) + (randLow * boundHigh >>> 32) + randHigh * boundHigh;
	}

	/**
	 * Returns the next pseudorandom, uniformly distributed
	 * {@code boolean} value from this random number generator's
	 * sequence. The general contract of {@code nextBoolean} is that one
	 * {@code boolean} value is pseudorandomly generated and returned.  The
	 * values {@code true} and {@code false} are produced with
	 * (approximately) equal probability.
	 *
	 * @return the next pseudorandom, uniformly distributed
	 * {@code boolean} value from this random number generator's
	 * sequence
	 */
	public boolean nextBoolean () {
		final long fa = this.stateA;
		final long fb = this.stateB;
		final long fc = this.stateC;
		final long fd = this.stateD;
		this.stateA = 0xD1342543DE82EF95L * fd;
		this.stateB = fa + 0xC6BC279692B5C323L;
		this.stateC = Long.rotateLeft(fb, 47) - fd;
		this.stateD = fb ^ fc;
		return fd < 0L;
	}

	/**
	 * Returns the next pseudorandom, uniformly distributed {@code float}
	 * value between {@code 0.0} (inclusive) and {@code 1.0} (exclusive)
	 * from this random number generator's sequence.
	 *
	 * <p>The general contract of {@code nextFloat} is that one
	 * {@code float} value, chosen (approximately) uniformly from the
	 * range {@code 0.0f} (inclusive) to {@code 1.0f} (exclusive), is
	 * pseudorandomly generated and returned. All 2<sup>24</sup> possible
	 * {@code float} values of the form <i>m&nbsp;x&nbsp;</i>2<sup>-24</sup>,
	 * where <i>m</i> is a positive integer less than 2<sup>24</sup>, are
	 * produced with (approximately) equal probability.
	 *
	 * <p>The hedge "approximately" is used in the foregoing description only
	 * because the next method is only approximately an unbiased source of
	 * independently chosen bits. If it were a perfect source of randomly
	 * chosen bits, then the algorithm shown would choose {@code float}
	 * values from the stated range with perfect uniformity.<p>
	 *
	 * @return the next pseudorandom, uniformly distributed {@code float}
	 * value between {@code 0.0} and {@code 1.0} from this
	 * random number generator's sequence
	 */
	public float nextFloat () {
		final long fa = this.stateA;
		final long fb = this.stateB;
		final long fc = this.stateC;
		final long fd = this.stateD;
		this.stateA = 0xD1342543DE82EF95L * fd;
		this.stateB = fa + 0xC6BC279692B5C323L;
		this.stateC = Long.rotateLeft(fb, 47) - fd;
		this.stateD = fb ^ fc;
		return (fd >>> 40) * 0x1p-24f;
	}

	/**
	 * Gets a pseudo-random float between 0 (inclusive) and {@code outerBound} (exclusive).
	 * The outerBound may be positive or negative.
	 * Exactly the same as {@code nextFloat() * outerBound}.
	 * @param outerBound the exclusive outer bound
	 * @return a float between 0 (inclusive) and {@code outerBound} (exclusive)
	 */
	public float nextFloat (float outerBound) {
		return nextFloat() * outerBound;
	}

	/**
	 * Gets a pseudo-random float between {@code innerBound} (inclusive) and {@code outerBound} (exclusive).
	 * Either, neither, or both of innerBound and outerBound may be negative; this does not change which is
	 * inclusive and which is exclusive.
	 * @param innerBound the inclusive inner bound; may be negative
	 * @param outerBound the exclusive outer bound; may be negative
	 * @return a float between {@code innerBound} (inclusive) and {@code outerBound} (exclusive)
	 */
	public float nextFloat (float innerBound, float outerBound) {
		return innerBound + nextFloat() * (outerBound - innerBound);
	}

	/**
	 * Returns the next pseudorandom, uniformly distributed
	 * {@code double} value between {@code 0.0} (inclusive) and {@code 1.0}
	 * (exclusive) from this random number generator's sequence.
	 *
	 * <p>The general contract of {@code nextDouble} is that one
	 * {@code double} value, chosen (approximately) uniformly from the
	 * range {@code 0.0d} (inclusive) to {@code 1.0d} (exclusive), is
	 * pseudorandomly generated and returned.
	 *
	 * <p>The hedge "approximately" is used in the foregoing description only
	 * because the {@code next} method is only approximately an unbiased
	 * source of independently chosen bits. If it were a perfect source of
	 * randomly chosen bits, then the algorithm shown would choose
	 * {@code double} values from the stated range with perfect uniformity.
	 *
	 * @return the next pseudorandom, uniformly distributed {@code double}
	 * value between {@code 0.0} and {@code 1.0} from this
	 * random number generator's sequence
	 */
	public double nextDouble () {
		final long fa = this.stateA;
		final long fb = this.stateB;
		final long fc = this.stateC;
		final long fd = this.stateD;
		this.stateA = 0xD1342543DE82EF95L * fd;
		this.stateB = fa + 0xC6BC279692B5C323L;
		this.stateC = Long.rotateLeft(fb, 47) - fd;
		this.stateD = fb ^ fc;
		return (fd >>> 11) * 0x1.0p-53;
	}

	/**
	 * Gets a pseudo-random double between 0 (inclusive) and {@code outerBound} (exclusive).
	 * The outerBound may be positive or negative.
	 * Exactly the same as {@code nextDouble() * outerBound}.
	 * @param outerBound the exclusive outer bound
	 * @return a double between 0 (inclusive) and {@code outerBound} (exclusive)
	 */
	public double nextDouble (double outerBound) {
		return nextDouble() * outerBound;
	}

	/**
	 * Gets a pseudo-random double between {@code innerBound} (inclusive) and {@code outerBound} (exclusive).
	 * Either, neither, or both of innerBound and outerBound may be negative; this does not change which is
	 * inclusive and which is exclusive.
	 * @param innerBound the inclusive inner bound; may be negative
	 * @param outerBound the exclusive outer bound; may be negative
	 * @return a double between {@code innerBound} (inclusive) and {@code outerBound} (exclusive)
	 */
	public double nextDouble (double innerBound, double outerBound) {
		return innerBound + nextDouble() * (outerBound - innerBound);
	}

	/**
	 * This is just like {@link #nextDouble()}, returning a double between 0 and 1, except that it is inclusive on both 0.0 and 1.0.
	 * It returns 1.0 extremely rarely, 0.000000000000011102230246251565% of the time if there is no bias in the generator, but it
	 * can happen. This uses {@link #nextLong(long)} internally, so it may have some bias towards or against specific
	 * subtly-different results.
	 * @return a double between 0.0, inclusive, and 1.0, inclusive
	 */
	public double nextInclusiveDouble() {
		return nextLong(0x20000000000001L) * 0x1p-53;
	}

	/**
	 * Just like {@link #nextDouble(double)}, but this is inclusive on both 0.0 and {@code outerBound}.
	 * It may be important to note that it returns outerBound on only 0.000000000000011102230246251565% of calls.
	 * @param outerBound the outer inclusive bound; may be positive or negative
	 * @return a double between 0.0, inclusive, and {@code outerBound}, inclusive
	 */
	public double nextInclusiveDouble(double outerBound) {
		return nextInclusiveDouble() * outerBound;
	}

	/**
	 * Just like {@link #nextDouble(double, double)}, but this is inclusive on both {@code innerBound} and {@code outerBound}.
	 * It may be important to note that it returns outerBound on only 0.000000000000011102230246251565% of calls, if it can
	 * return it at all because of floating-point imprecision when innerBound is a larger number.
	 * @param innerBound the inner inclusive bound; may be positive or negative
	 * @param outerBound the outer inclusive bound; may be positive or negative
	 * @return a double between {@code innerBound}, inclusive, and {@code outerBound}, inclusive
	 */
	public double nextInclusiveDouble(double innerBound, double outerBound) {
		return innerBound + nextInclusiveDouble() * (outerBound - innerBound);
	}

	/**
	 * This is just like {@link #nextFloat()}, returning a float between 0 and 1, except that it is inclusive on both 0.0 and 1.0.
	 * It returns 1.0 rarely, 0.00000596046412226771% of the time if there is no bias in the generator, but it can happen. This method
	 * has been tested by generating 268435456 (or 0x10000000) random ints with {@link #nextInt(int)}, and just before the end of that
	 * it had generated every one of the 16777217 roughly-equidistant floats this is able to produce. Not all seeds are
	 * likely to accomplish that in the same time, or at all, depending on the generator.
	 * @return a float between 0.0, inclusive, and 1.0, inclusive
	 */
	public float nextInclusiveFloat() {
		return nextInt(0x1000001) * 0x1p-24f;
	}

	/**
	 * Just like {@link #nextFloat(float)}, but this is inclusive on both 0.0 and {@code outerBound}.
	 * It may be important to note that it returns outerBound on only 0.00000596046412226771% of calls.
	 * @param outerBound the outer inclusive bound; may be positive or negative
	 * @return a float between 0.0, inclusive, and {@code outerBound}, inclusive
	 */
	public float nextInclusiveFloat(float outerBound) {
		return nextInclusiveFloat() * outerBound;
	}

	/**
	 * Just like {@link #nextFloat(float, float)}, but this is inclusive on both {@code innerBound} and {@code outerBound}.
	 * It may be important to note that it returns outerBound on only 0.00000596046412226771% of calls, if it can return
	 * it at all because of floating-point imprecision when innerBound is a larger number.
	 * @param innerBound the inner inclusive bound; may be positive or negative
	 * @param outerBound the outer inclusive bound; may be positive or negative
	 * @return a float between {@code innerBound}, inclusive, and {@code outerBound}, inclusive
	 */
	public float nextInclusiveFloat(float innerBound, float outerBound) {
		return innerBound + nextInclusiveFloat() * (outerBound - innerBound);
	}

	/**
	 * Gets a random double between 0.0 and 1.0, exclusive at both ends; this method is also more uniform than
	 * {@link #nextDouble()} if you use the bit-patterns of the returned doubles. This is a simplified version of
	 * <a href="https://allendowney.com/research/rand/">this algorithm by Allen Downey</a>. This can return double
	 * values between 2.710505431213761E-20 and 0.9999999999999999, or 0x1.0p-65 and 0x1.fffffffffffffp-1 in hex
	 * notation. It cannot return 0 or 1. Most cases can instead use {@link #nextExclusiveDoubleEquidistant()}, which is
	 * implemented more traditionally but may have different performance. This method can also return doubles that
	 * are extremely close to 0, but can't return doubles that are as close to 1, due to limits of doubles.
	 * However, nextExclusiveDoubleEquidistant() can return only a minimum value that is as distant from 0 as its maximum
	 * value is distant from 1.
	 * <br>
	 * To compare, nextDouble() and nextExclusiveDoubleEquidistant() are less likely to produce a "1" bit for their
	 * lowest 5 bits of mantissa/significand (the least significant bits numerically, but potentially important
	 * for some uses), with the least significant bit produced half as often as the most significant bit in the
	 * mantissa. As for this method, it has approximately the same likelihood of producing a "1" bit for any
	 * position in the mantissa.
	 * <br>
	 * The default implementation may have different performance characteristics than {@link #nextDouble()},
	 * because this doesn't perform any floating-point multiplication or division, and instead assembles bits
	 * obtained by one call to {@link #nextLong()}. This uses {@link NumberUtils#longBitsToDouble(long)} and
	 * {@link Long#numberOfTrailingZeros(long)}, both of which typically have optimized intrinsics on HotSpot,
	 * and this is branchless and loopless, unlike the original algorithm by Allen Downey. When compared with
	 * {@link #nextExclusiveDoubleEquidistant()}, this method performs better on at least HotSpot JVMs.
	 * @return a random uniform double between 0 and 1 (both exclusive)
	 */
	 public double nextExclusiveDouble (){
		final long bits = nextLong();
		return NumberUtils.longBitsToDouble(1022L - Long.numberOfTrailingZeros(bits) << 52
				| bits >>> 12);
	}

	/**
	 * Gets a random double between 0.0 and 1.0, exclusive at both ends. This can return double
	 * values between 1.1102230246251565E-16 and 0.9999999999999999, or 0x1.0p-53 and 0x1.fffffffffffffp-1 in hex
	 * notation. It cannot return 0 or 1, and its minimum and maximum results are equally distant from 0 and from
	 * 1, respectively. Some usages may prefer {@link #nextExclusiveDouble()}, which is
	 * better-distributed if you consider the bit representation of the returned doubles, tends to perform
	 * better, and can return doubles that much closer to 0 than this can.
	 * <br>
	 * The default implementation simply uses {@link #nextLong(long)} to get a uniformly-chosen long between 1 and
	 * (2 to the 53) - 1, both inclusive, and multiplies it by (2 to the -53). Using larger values than (2 to the
	 * 53) would cause issues with the double math.
	 * @return a random uniform double between 0 and 1 (both exclusive)
	 */
	public double nextExclusiveDoubleEquidistant (){
		return (nextLong(0x1FFFFFFFFFFFFFL) + 1L) * 0x1p-53;
	}

	/**
	 * Just like {@link #nextDouble(double)}, but this is exclusive on both 0.0 and {@code outerBound}.
	 * Like {@link #nextExclusiveDouble()}, which this uses, this may have better bit-distribution of
	 * double values, and it may also be better able to produce very small doubles when {@code outerBound} is large.
	 * @param outerBound the outer exclusive bound; may be positive or negative
	 * @return a double between 0.0, exclusive, and {@code outerBound}, exclusive
	 */
	public double nextExclusiveDouble(double outerBound) {
		return nextExclusiveDouble() * outerBound;
	}

	/**
	 * Just like {@link #nextDouble(double, double)}, but this is exclusive on both {@code innerBound} and {@code outerBound}.
	 * Like {@link #nextExclusiveDouble()}, which this uses,, this may have better bit-distribution of double values,
	 * and it may also be better able to produce doubles close to innerBound when {@code outerBound - innerBound} is large.
	 * @param innerBound the inner exclusive bound; may be positive or negative
	 * @param outerBound the outer exclusive bound; may be positive or negative
	 * @return a double between {@code innerBound}, exclusive, and {@code outerBound}, exclusive
	 */
	public double nextExclusiveDouble(double innerBound, double outerBound) {
		return innerBound + nextExclusiveDouble() * (outerBound - innerBound);
	}

	/**
	 * Gets a random float between 0.0 and 1.0, exclusive at both ends. This method is also more uniform than
	 * {@link #nextFloat()} if you use the bit-patterns of the returned floats. This is a simplified version of
	 * <a href="https://allendowney.com/research/rand/">this algorithm by Allen Downey</a>. This version can
	 * return float values between 2.7105054E-20 to 0.99999994, or 0x1.0p-65 to 0x1.fffffep-1 in hex notation.
	 * It cannot return 0 or 1. To compare, nextFloat() is less likely to produce a "1" bit for its
	 * lowest 5 bits of mantissa/significand (the least significant bits numerically, but potentially important
	 * for some uses), with the least significant bit produced half as often as the most significant bit in the
	 * mantissa. As for this method, it has approximately the same likelihood of producing a "1" bit for any
	 * position in the mantissa.
	 * <br>
	 * The default implementation may have different performance characteristics than {@link #nextFloat()},
	 * because this doesn't perform any floating-point multiplication or division, and instead assembles bits
	 * obtained by one call to {@link #nextLong()}. This uses {@link NumberUtils#intBitsToFloat(int)} and
	 * {@link Long#numberOfTrailingZeros(long)}, both of which typically have optimized intrinsics on HotSpot,
	 * and this is branchless and loopless, unlike the original algorithm by Allen Downey. When compared with
	 * {@link #nextExclusiveFloatEquidistant()}, this method performs better on at least HotSpot JVMs.
	 * @return a random uniform float between 0 and 1 (both exclusive)
	 */
	public float nextExclusiveFloat(){
		final long bits = nextLong();
		return NumberUtils.intBitsToFloat(126 - Long.numberOfTrailingZeros(bits) << 23
				| (int)(bits >>> 41));
	}

	/**
	 * Gets a random float between 0.0 and 1.0, exclusive at both ends. This can return float
	 * values between 5.9604645E-8 and 0.99999994, or 0x1.0p-24 and 0x1.fffffep-1 in hex notation.
	 * It cannot return 0 or 1, and its minimum and maximum results are equally distant from 0 and from
	 * 1, respectively. Some usages may prefer {@link #nextExclusiveFloat()}, which is
	 * better-distributed if you consider the bit representation of the returned floats, tends to perform
	 * better, and can return floats that much closer to 0 than this can.
	 * <br>
	 * The default implementation simply uses {@link #nextInt(int)} to get a uniformly-chosen int between 1 and
	 * (2 to the 24) - 1, both inclusive, and multiplies it by (2 to the -24). Using larger values than (2 to the
	 * 24) would cause issues with the float math.
	 * @return a random uniform float between 0 and 1 (both exclusive)
	 */
	public float nextExclusiveFloatEquidistant (){
		return (nextInt(0xFFFFFF) + 1) * 0x1p-24f;
	}

	/**
	 * Just like {@link #nextFloat(float)}, but this is exclusive on both 0.0 and {@code outerBound}.
	 * Like {@link #nextExclusiveFloat()}, this may have better bit-distribution of float values, and
	 * it may also be better able to produce very small floats when {@code outerBound} is large.
	 * @param outerBound the outer exclusive bound; may be positive or negative
	 * @return a float between 0.0, exclusive, and {@code outerBound}, exclusive
	 */
	public float nextExclusiveFloat(float outerBound) {
		return nextExclusiveFloat() * outerBound;
	}

	/**
	 * Just like {@link #nextFloat(float, float)}, but this is exclusive on both {@code innerBound} and {@code outerBound}.
	 * Like {@link #nextExclusiveFloat()}, this may have better bit-distribution of float values, and
	 * it may also be better able to produce floats close to innerBound when {@code outerBound - innerBound} is large.
	 * @param innerBound the inner exclusive bound; may be positive or negative
	 * @param outerBound the outer exclusive bound; may be positive or negative
	 * @return a float between {@code innerBound}, exclusive, and {@code outerBound}, exclusive
	 */
	public float nextExclusiveFloat(float innerBound, float outerBound) {
		return innerBound + nextExclusiveFloat() * (outerBound - innerBound);
	}

	/**
	 * Returns the next pseudorandom, Gaussian ("normally") distributed
	 * {@code double} value with mean {@code 0.0} and standard
	 * deviation {@code 1.0} from this random number generator's sequence.
	 * <p>
	 * The general contract of {@code nextGaussian} is that one
	 * {@code double} value, chosen from (approximately) the usual
	 * normal distribution with mean {@code 0.0} and standard deviation
	 * {@code 1.0}, is pseudorandomly generated and returned.
	 * <p>
	 * This uses an imperfect approximation, but one that is much faster than
	 * the Box-Muller transform, Marsaglia Polar method, or a transform using the
	 * probit function. Like earlier versions that used probit(), it requests
	 * exactly one long from the generator's sequence (using {@link #nextLong()}).
	 * This makes it different from code like java.util.Random's nextGaussian()
	 * method, which can (rarely) fetch a higher number of random doubles.
	 * <p>
	 * This can't produce as extreme results in extremely-rare cases as methods
	 * like Box-Muller and Marsaglia Polar can. All possible results are between
	 * {@code -7.929080009460449} and {@code 7.929080009460449}, inclusive.
	 * <p>
	 * <a href="https://marc-b-reynolds.github.io/distribution/2021/03/18/CheapGaussianApprox.html">Credit
	 * to Marc B. Reynolds</a> for coming up with this clever fusion of the
	 * already-bell-curved bit count and a triangular distribution to smooth
	 * it out. Using one random long instead of two is the contribution here.
	 *
	 * @return the next pseudorandom, Gaussian ("normally") distributed
	 * {@code double} value with mean {@code 0.0} and standard deviation
	 * {@code 1.0} from this random number generator's sequence
	 */
	public double nextGaussian () {
		//// here, we want to only request one long from this FourWheelRandom.
		//// because the bitCount() doesn't really care about the numerical value of its argument, only its Hamming weight,
		//// we use the random long un-scrambled, and get the bit count of that.
		//// for the later steps, we multiply the random long by a specific constant and get the difference of its halves.
		//// 0xC6AC29E4C6AC29E5L is... OK, it's complicated. It needs to have almost-identical upper and lower halves, but
		//// for reasons I don't currently understand, if the upper and lower halves are equal, then the min and max results
		//// of the Gaussian aren't equally distant from 0. By using an upper half that is exactly 1 less than the lower
		//// half, we get bounds of -7.929080009460449 to 7.929080009460449, returned when the RNG gives 0 and -1 resp.
		//// because it only needs one floating-point operation, it is quite fast on a CPU.
		//// this winds up being a very smooth Gaussian, as Marc B. Reynolds had it with two random longs.
		long u = nextLong();
		final long c = Long.bitCount(u) - 32L << 32;
		u *= 0xC6AC29E4C6AC29E5L;
		return 0x1.fb760cp-35 * (c + (u & 0xFFFFFFFFL) - (u >>> 32));
	}

	/**
	 * Creates a new {@code FourWheelRandom} with identical states to this one, so if the same FourWheelRandom methods are
	 * called on this object and its copy (in the same order), the same outputs will be produced. This is not
	 * guaranteed to copy the inherited state of the {@link Random} parent class, so if you call methods that are
	 * only implemented by Random and not FourWheelRandom, the results may differ.
	 * @return a deep copy of this FourWheelRandom.
	 */
	public FourWheelRandom copy () {
		return new FourWheelRandom(stateA, stateB, stateC, stateD);
	}

	/**
	 * Returns true if a random value between 0 and 1 is less than the specified value.
	 *
	 * @param chance a float between 0.0 and 1.0; higher values are more likely to result in true
	 * @return a boolean selected with the given {@code chance} of being true
	 */
	public boolean nextBoolean (float chance) {
		return nextFloat() < chance;
	}

	/**
	 * Returns -1 or 1, randomly.
	 *
	 * @return -1 or 1, selected with approximately equal likelihood
	 */
	public int nextSign () {
		return 1 | nextInt() >> 31;
	}

	/**
	 * Returns a triangularly distributed random number between -1.0 (exclusive) and 1.0 (exclusive), where values around zero are
	 * more likely. Advances the state twice.
	 * <p>
	 * This is an optimized version of {@link #nextTriangular(float, float, float) randomTriangular(-1, 1, 0)}
	 */
	public float nextTriangular () {
		return nextFloat() - nextFloat();
	}

	/**
	 * Returns a triangularly distributed random number between {@code -max} (exclusive) and {@code max} (exclusive), where values
	 * around zero are more likely. Advances the state twice.
	 * <p>
	 * This is an optimized version of {@link #nextTriangular(float, float, float) randomTriangular(-max, max, 0)}
	 *
	 * @param max the upper limit
	 */
	public float nextTriangular (float max) {
		return (nextFloat() - nextFloat()) * max;
	}

	/**
	 * Returns a triangularly distributed random number between {@code min} (inclusive) and {@code max} (exclusive), where the
	 * {@code mode} argument defaults to the midpoint between the bounds, giving a symmetric distribution. Advances the state once.
	 * <p>
	 * This method is equivalent of {@link #nextTriangular(float, float, float) randomTriangular(min, max, (min + max) * 0.5f)}
	 *
	 * @param min the lower limit
	 * @param max the upper limit
	 */
	public float nextTriangular (float min, float max) {
		return nextTriangular(min, max, (min + max) * 0.5f);
	}

	/**
	 * Returns a triangularly distributed random number between {@code min} (inclusive) and {@code max} (exclusive), where values
	 * around {@code mode} are more likely. Advances the state once.
	 *
	 * @param min  the lower limit
	 * @param max  the upper limit
	 * @param mode the point around which the values are more likely
	 */
	public float nextTriangular (float min, float max, float mode) {
		float u = nextFloat();
		float d = max - min;
		if (u <= (mode - min) / d) { return min + (float)Math.sqrt(u * d * (mode - min)); }
		return max - (float)Math.sqrt((1 - u) * d * (max - mode));
	}

	/**
	 * Gets a randomly-selected item from the given array, which must be non-null and non-empty
	 * @param array a non-null, non-empty array of {@code T} items
	 * @param <T> any reference type
	 * @return a random item from {@code array}
	 * @throws NullPointerException if array is null
	 * @throws IndexOutOfBoundsException if array is empty
	 */
	public <T> T randomElement (T[] array) {
		return array[nextInt(array.length)];
	}

	/**
	 * Shuffles the given array in-place pseudo-randomly, using this to determine how to shuffle.
	 *
	 * @param items an int array; must be non-null
	 */
	public void shuffle (int[] items) {
		for (int i = items.length - 1; i >= 0; i--) {
			int ii = nextInt(i + 1);
			int temp = items[i];
			items[i] = items[ii];
			items[ii] = temp;
		}
	}

	/**
	 * Shuffles the given array in-place pseudo-randomly, using this to determine how to shuffle.
	 *
	 * @param items a long array; must be non-null
	 */
	public void shuffle (long[] items) {
		for (int i = items.length - 1; i >= 0; i--) {
			int ii = nextInt(i + 1);
			long temp = items[i];
			items[i] = items[ii];
			items[ii] = temp;
		}
	}

	/**
	 * Shuffles the given array in-place pseudo-randomly, using this to determine how to shuffle.
	 *
	 * @param items a float array; must be non-null
	 */
	public void shuffle (float[] items) {
		for (int i = items.length - 1; i >= 0; i--) {
			int ii = nextInt(i + 1);
			float temp = items[i];
			items[i] = items[ii];
			items[ii] = temp;
		}
	}

	/**
	 * Shuffles the given array in-place pseudo-randomly, using this to determine how to shuffle.
	 *
	 * @param items a char array; must be non-null
	 */
	public void shuffle (char[] items) {
		for (int i = items.length - 1; i >= 0; i--) {
			int ii = nextInt(i + 1);
			char temp = items[i];
			items[i] = items[ii];
			items[ii] = temp;
		}
	}

	/**
	 * Shuffles the given array in-place pseudo-randomly, using this to determine how to shuffle.
	 *
	 * @param items a double array; must be non-null
	 */
	public void shuffle (double[] items) {
		for (int i = items.length - 1; i >= 0; i--) {
			int ii = nextInt(i + 1);
			double temp = items[i];
			items[i] = items[ii];
			items[ii] = temp;
		}
	}

	/**
	 * Shuffles the given array in-place pseudo-randomly, using this to determine how to shuffle.
	 *
	 * @param items a short array; must be non-null
	 */
	public void shuffle (short[] items) {
		for (int i = items.length - 1; i >= 0; i--) {
			int ii = nextInt(i + 1);
			short temp = items[i];
			items[i] = items[ii];
			items[ii] = temp;
		}
	}

	/**
	 * Shuffles the given array in-place pseudo-randomly, using this to determine how to shuffle.
	 *
	 * @param items a boolean array; must be non-null
	 */
	public void shuffle (boolean[] items) {
		for (int i = items.length - 1; i >= 0; i--) {
			int ii = nextInt(i + 1);
			boolean temp = items[i];
			items[i] = items[ii];
			items[ii] = temp;
		}
	}

	/**
	 * Shuffles the given array in-place pseudo-randomly, using this to determine how to shuffle.
	 *
	 * @param items an array of some reference type; must be non-null but may contain null items
	 */
	public <T> void shuffle (T[] items) {
		for (int i = items.length - 1; i >= 0; i--) {
			int ii = nextInt(i + 1);
			T temp = items[i];
			items[i] = items[ii];
			items[ii] = temp;
		}
	}

	public boolean equals (Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		FourWheelRandom that = (FourWheelRandom)o;

		if (stateA != that.stateA) return false;
		if (stateB != that.stateB) return false;
		if (stateC != that.stateC) return false;
		return stateD == that.stateD;
	}

	public String toString () {
		return "FourWheelRandom{" + "stateA=" + stateA + "L, stateB=" + stateB + "L, stateC=" + stateC + "L, stateD=" + stateD + "L}";
	}
}
