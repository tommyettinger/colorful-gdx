/*
 * Copyright (c) 2020-2024 See AUTHORS file.
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

package com.github.tommyettinger.colorful.oklab.squid;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.github.tommyettinger.colorful.oklab.ColorfulSprite;
import com.github.yellowstonegames.grid.Coord;
import com.github.yellowstonegames.smooth.CoordGlider;
import com.github.yellowstonegames.smooth.VectorSequenceGlider;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Extends {@link Sprite}, but uses an {@link Animation} of {@link TextureRegion} for its visuals and a
 * {@link CoordGlider} to store its position. The {@link CoordGlider} is publicly available as {@link #location} or with
 * {@link #getLocation()}, which should be used to determine or change where this started its move, where it is going,
 * and how far it has gone between the two. You Must Call {@link #animate(float)} with an increasing float parameter
 * when you want the animation to be playing; otherwise it will stay on the first frame (or a later frame if you stop
 * calling animate() at some other point). You can use the {@link VectorSequenceGlider} {@link #smallMotion} to move
 * this Sprite at a finer resolution than between Coords for start and end points.
 * <br>
 * You probably want to use Textures with a width and height of 1 world unit in
 * your Animation, and {@link #setSize(float, float)} on this to {@code 1, 1}; this avoids the need to convert between
 * Coord units in the CoordGlider and some other unit in the world.
 */
public class AnimatedGlidingSprite extends ColorfulSprite {
    public Animation<? extends TextureRegion> animation;
    @NonNull
    public CoordGlider location;
    @NonNull
    public VectorSequenceGlider smallMotion;
    /**
     * A VectorSequenceGlider that is empty (has no motions) and belongs to this AnimatedGlidingSprite.
     * This is public so external code can use it, but should never be modified.
     * It is here so {@link #smallMotion} can be easily set to an empty sequence.
     * You can also use {@code setSmallMotion(null)} to stop any small motion.
     */
    @NonNull
    public final VectorSequenceGlider ownEmptyMotion = VectorSequenceGlider.EMPTY.copy();

    private AnimatedGlidingSprite()
    {
        this(null, Coord.get(0, 0));
    }
    public AnimatedGlidingSprite(Animation<? extends TextureRegion> animation) {
        this(animation, Coord.get(0, 0));
    }

    public AnimatedGlidingSprite(Animation<? extends TextureRegion> animation, Coord coord) {
        this(animation, coord, coord);
    }

    public AnimatedGlidingSprite(Animation<? extends TextureRegion> animation, Coord start, Coord end) {
        super();
        this.animation = animation;
        setRegion(animation.getKeyFrame(0f));
        location = new CoordGlider(start, end);
        smallMotion = ownEmptyMotion;
    }

    /**
     * Required to use to have the animation play; give this a steadily increasing stateTime (measured in seconds, as a
     * float) and it will steadily play the animation; if stateTime stops increasing or this stops being called, then
     * the animation is effectively paused.
     * @param stateTime time playing the animation, in seconds; usually not an exact integer
     * @return this for chaining
     */
    public AnimatedGlidingSprite animate(final float stateTime)
    {
        setRegion(animation.getKeyFrame(stateTime));
        return this;
    }
    
    public float getX()
    {
        return location.getX() + smallMotion.getX();
    }

    public float getY()
    {
        return location.getY() + smallMotion.getY();
    }

    @Override
    public float[] getVertices() {
        super.setPosition(getX(), getY());
        return super.getVertices();
    }

    public Animation<? extends TextureRegion> getAnimation() {
        return animation;
    }

    public void setAnimation(Animation<? extends TextureRegion> animation) {
        this.animation = animation;
    }

    @NonNull
    public CoordGlider getLocation() {
        return location;
    }

    public void setLocation(@NonNull CoordGlider location) {
        this.location = location;
    }

    @NonNull
    public VectorSequenceGlider getSmallMotion() {
        return smallMotion;
    }

    public void setSmallMotion(@Nullable VectorSequenceGlider smallMotion) {
        if(smallMotion == null) this.smallMotion = ownEmptyMotion;
        else this.smallMotion = smallMotion;
    }
}
