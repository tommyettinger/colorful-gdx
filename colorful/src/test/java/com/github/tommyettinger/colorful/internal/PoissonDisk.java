package com.github.tommyettinger.colorful.internal;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.FloatArray;

import java.util.Arrays;
import java.util.Random;

public class PoissonDisk {
    private static final float inverseRootTwo = (float)Math.sqrt(0.5f);

    public int[][] array;
    public FloatArray qx;
    public FloatArray qy;
    public float[][] gridX;
    public float[][] gridY;
    public int gridWidth;
    public int gridHeight;
    public float invCellSize;
    public float radius;
    public Random random;

    public PoissonDisk() {
        this(512, 512, 5, new RandomXS128());
    }

    public PoissonDisk(int width, int height, float radius, Random random) {
        array = new int[width][height];

        this.radius = Math.max(1.0001f, radius);
        invCellSize = 1f / (this.radius * inverseRootTwo);
        gridWidth = Math.min((int) Math.ceil(width * invCellSize), width);
        gridHeight = Math.min((int) Math.ceil(height * invCellSize), height);
        gridX = new float[gridWidth][gridHeight];
        gridY = new float[gridWidth][gridHeight];
        qx = new FloatArray(gridWidth + gridHeight);
        qy = new FloatArray(gridWidth + gridHeight);
        this.random = random;

    }

    /**
     * Get a list of GridPoint2s, each randomly positioned around the given center out to the given radius (measured with
     * Euclidean distance, so a true circle), but with the given minimum distance from any other GridPoint2 in the list.
     * The parameters maxX and maxY should typically correspond to the width and height of the map; no points will have
     * positions with x equal to or greater than maxX and the same for y and maxY; similarly, no points will have
     * negative x or y.
     * @param center the center of the circle to spray GridPoint2s into
     * @param radius the radius of the circle to spray GridPoint2s into
     * @return a 2D int array where non-zero values represent picked points
     */
    public int[][] sampleCircle(GridPoint2 center, float radius) {
        return sampleCircle(center, radius, 10);
    }

    /**
     * Get a list of GridPoint2s, each randomly positioned around the given center out to the given radius (measured with
     * Euclidean distance, so a true circle), but with the given minimum distance from any other GridPoint2 in the list.
     * The parameters maxX and maxY should typically correspond to the width and height of the map; no points will have
     * positions with x equal to or greater than maxX and the same for y and maxY; similarly, no points will have
     * negative x or y.
     * @param center the center of the circle to spray GridPoint2s into
     * @param radius the radius of the circle to spray GridPoint2s into
     * @param pointsPerIteration with small radii, this can be around 5; with larger ones, 30 is reasonable
     * @return a 2D int array where non-zero values represent picked points
     */
    public int[][] sampleCircle(GridPoint2 center, float radius, int pointsPerIteration) {
        int rr = Math.round(radius);
        return sample(new GridPoint2(center.x - rr, center.y - rr),
                new GridPoint2(center.x + rr, center.y + rr), radius, pointsPerIteration);
    }

    /**
     * Get a list of GridPoint2s, each randomly positioned within the rectangle between the given minPosition and
     * maxPosition, but with the given minimum distance from any other GridPoint2 in the list.
     * The parameters maxX and maxY should typically correspond to the width and height of the map; no points will have
     * positions with x equal to or greater than maxX and the same for y and maxY; similarly, no points will have
     * negative x or y.
     * @param minPosition the GridPoint2 with the lowest x and lowest y to be used as a corner for the bounding box
     * @param maxPosition the GridPoint2 with the highest x and highest y to be used as a corner for the bounding box
     * @return a 2D int array where non-zero values represent picked points
     */
    public int[][] sampleRectangle(GridPoint2 minPosition, GridPoint2 maxPosition) {
        return sampleRectangle(minPosition, maxPosition, 10);
    }

    /**
     * Get a list of GridPoint2s, each randomly positioned within the rectangle between the given minPosition and
     * maxPosition, but with the given minimum distance from any other GridPoint2 in the list.
     * The parameters maxX and maxY should typically correspond to the width and height of the map; no points will have
     * positions with x equal to or greater than maxX and the same for y and maxY; similarly, no points will have
     * negative x or y.
     * @param minPosition the GridPoint2 with the lowest x and lowest y to be used as a corner for the bounding box
     * @param maxPosition the GridPoint2 with the highest x and highest y to be used as a corner for the bounding box
     * @param pointsPerIteration with small areas, this can be around 5; with larger ones, 30 is reasonable
     * @return a 2D int array where non-zero values represent picked points
     */
    public int[][] sampleRectangle(GridPoint2 minPosition, GridPoint2 maxPosition, int pointsPerIteration) {
        return sample(minPosition, maxPosition, 0f, pointsPerIteration);
    }

    protected int[][] sample(GridPoint2 minPos, GridPoint2 maxPos, float maxCircleRadius, int pointsPerTry) {
        for (int x = 0; x < array.length; x++) {
            Arrays.fill(array[x], 0);
        }
        for (int x = 0; x < gridX.length; x++) {
            Arrays.fill(gridX[x], 0f);
            Arrays.fill(gridY[x], 0f);
        }
        qx.clear();
        qy.clear();

        maxCircleRadius *= maxCircleRadius;
        final float ik = 1f / pointsPerTry;
        final int width = maxPos.x - minPos.x + 1, height = maxPos.y - minPos.y + 1;
        final Vector2 gridCenter = new Vector2(minPos.x + maxPos.x, minPos.y + maxPos.y).scl(0.5f);
        // Pick the first sample.
        sample(width * 0.5f, height * 0.5f, minPos);

        // Pick a random existing sample from the queue.
        PICKING:
        while (qx.notEmpty()) {
            final int i = random.nextInt(qx.size);
            final float px = qx.get(i);
            final float py = qy.get(i);
            float seed = random.nextFloat();
            // Make a new candidate.
            for (int j = 0; j < pointsPerTry; j++) {
                final float x = px + radius * MathUtils.cosDeg(360f * seed);
                final float y = py + radius * MathUtils.sinDeg(360f * seed);
                seed += ik;

                // Accept candidates that are inside the allowed extent
                // and farther than 2 * radius to all existing samples.
                if (x >= minPos.x && x < maxPos.x + 0.99999994f && y >= minPos.y && y < maxPos.y + 0.99999994f &&
                        far(x, y, gridCenter, maxCircleRadius, minPos)) {
                    sample(x, y, minPos);
                    continue PICKING;
                }
            }

            // If none of k candidates were accepted, remove it from the queue.
            qx.removeIndex(i);
            qy.removeIndex(i);
        }
        return array;
    }
    private boolean far(float x, float y, Vector2 gridCenter, float maxSampleRadius, GridPoint2 minPos){
        if(maxSampleRadius != 0f && gridCenter.dst2(x, y) > maxSampleRadius) return false;
        final int i = (int)((x - minPos.x) * invCellSize);
        final int j = (int)((y - minPos.y) * invCellSize);
        final int gridWidth = gridX.length;
        final int i0 = Math.max(i - 2, 0);
        final int j0 = Math.max(j - 2, 0);
        final int i1 = Math.min(i + 3, gridWidth);
        final int j1 = Math.min(j + 3, gridX[0].length);
        float radius2 = radius * radius;
        for (int xx = i0; xx < i1; xx++) {
            for (int yy = j0; yy < j1; yy++) {
                float dx = gridX[xx][yy];
                if(dx >= 0){
                    dx -= x;
                    final float dy = gridY[xx][yy] - y;
                    dx = dx * dx + dy * dy;
                    if(dx < radius2) return false;
                }
            }
        }
        return true;
    }
    private void sample(float x, float y, GridPoint2 minPos){
        final int gx = (int)((x - minPos.x) * invCellSize), gy = (int)((y - minPos.y) * invCellSize);
        gridX[gx][gy] = x;
        gridY[gx][gy] = y;
        qx.add(x);
        qy.add(y);
        array[(int) x][(int) y] = qx.size;
    }
}

