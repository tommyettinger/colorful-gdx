package com.github.tommyettinger.colorful.internal;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.FloatArray;

import java.util.Random;

public class PoissonDisk {
    private static final float inverseRootTwo = (float)Math.sqrt(0.5f);


    private PoissonDisk() {
    }

    /**
     * Get a list of GridPoint2s, each randomly positioned around the given center out to the given radius (measured with
     * Euclidean distance, so a true circle), but with the given minimum distance from any other GridPoint2 in the list.
     * The parameters maxX and maxY should typically correspond to the width and height of the map; no points will have
     * positions with x equal to or greater than maxX and the same for y and maxY; similarly, no points will have
     * negative x or y.
     * @param center the center of the circle to spray GridPoint2s into
     * @param radius the radius of the circle to spray GridPoint2s into
     * @param minimumDistance the minimum distance between GridPoint2s, in Euclidean distance as a float.
     * @param maxX one more than the highest x that can be assigned; typically an array length
     * @param maxY one more than the highest y that can be assigned; typically an array length
     * @return a 2D int array where non-zero values represent picked points
     */
    public static int[][] sampleCircle(GridPoint2 center, float radius, float minimumDistance,
                                                                             int maxX, int maxY)
    {
        return sampleCircle(center, radius, minimumDistance, maxX, maxY, 10, new RandomXS128());
    }

    /**
     * Get a list of GridPoint2s, each randomly positioned around the given center out to the given radius (measured with
     * Euclidean distance, so a true circle), but with the given minimum distance from any other GridPoint2 in the list.
     * The parameters maxX and maxY should typically correspond to the width and height of the map; no points will have
     * positions with x equal to or greater than maxX and the same for y and maxY; similarly, no points will have
     * negative x or y.
     * @param center the center of the circle to spray GridPoint2s into
     * @param radius the radius of the circle to spray GridPoint2s into
     * @param minimumDistance the minimum distance between GridPoint2s, in Euclidean distance as a float.
     * @param maxX one more than the highest x that can be assigned; typically an array length
     * @param maxY one more than the highest y that can be assigned; typically an array length
     * @param pointsPerIteration with small radii, this can be around 5; with larger ones, 30 is reasonable
     * @param rng a Random to use for all random sampling.
     * @return a 2D int array where non-zero values represent picked points
     */
    public static int[][] sampleCircle(GridPoint2 center, float radius, float minimumDistance,
                                                                        int maxX, int maxY, int pointsPerIteration, Random rng)
    {
        int radius2 = Math.round(radius);
        return sample(new GridPoint2(center.x - radius2, center.y - radius2),
                new GridPoint2(center.x + radius2, center.y + radius2), radius, minimumDistance, maxX, maxY, pointsPerIteration, rng);
    }

    /**
     * Get a list of GridPoint2s, each randomly positioned within the rectangle between the given minPosition and
     * maxPosition, but with the given minimum distance from any other GridPoint2 in the list.
     * The parameters maxX and maxY should typically correspond to the width and height of the map; no points will have
     * positions with x equal to or greater than maxX and the same for y and maxY; similarly, no points will have
     * negative x or y.
     * @param minPosition the GridPoint2 with the lowest x and lowest y to be used as a corner for the bounding box
     * @param maxPosition the GridPoint2 with the highest x and highest y to be used as a corner for the bounding box
     * @param minimumDistance the minimum distance between GridPoint2s, in Euclidean distance as a float.
     * @return a 2D int array where non-zero values represent picked points
     */
    public static int[][] sampleRectangle(GridPoint2 minPosition, GridPoint2 maxPosition, float minimumDistance)
    {
        return sampleRectangle(minPosition, maxPosition, minimumDistance, maxPosition.x + 1, maxPosition.y + 1, 10, new RandomXS128());
    }

    /**
     * Get a list of GridPoint2s, each randomly positioned within the rectangle between the given minPosition and
     * maxPosition, but with the given minimum distance from any other GridPoint2 in the list.
     * The parameters maxX and maxY should typically correspond to the width and height of the map; no points will have
     * positions with x equal to or greater than maxX and the same for y and maxY; similarly, no points will have
     * negative x or y.
     * @param minPosition the GridPoint2 with the lowest x and lowest y to be used as a corner for the bounding box
     * @param maxPosition the GridPoint2 with the highest x and highest y to be used as a corner for the bounding box
     * @param minimumDistance the minimum distance between GridPoint2s, in Euclidean distance as a float.
     * @param pointsPerIteration with small areas, this can be around 5; with larger ones, 30 is reasonable
     * @param rng a Random to use for all random sampling.
     * @return a 2D int array where non-zero values represent picked points
     */
    public static int[][] sampleRectangle(
            GridPoint2 minPosition, GridPoint2 maxPosition, float minimumDistance, int pointsPerIteration, Random rng)
    {
        return sample(minPosition, maxPosition, 0f, minimumDistance, maxPosition.x + 1, maxPosition.y + 1, pointsPerIteration, rng);
    }

    /**
     * Get a list of GridPoint2s, each randomly positioned within the rectangle between the given minPosition and
     * maxPosition, but with the given minimum distance from any other GridPoint2 in the list.
     * The parameters maxX and maxY should typically correspond to the width and height of the map; no points will have
     * positions with x equal to or greater than maxX and the same for y and maxY; similarly, no points will have
     * negative x or y.
     * @param minPosition the GridPoint2 with the lowest x and lowest y to be used as a corner for the bounding box
     * @param maxPosition the GridPoint2 with the highest x and highest y to be used as a corner for the bounding box
     * @param minimumDistance the minimum distance between GridPoint2s, in Euclidean distance as a float.
     * @param maxX one more than the highest x that can be assigned; typically an array length
     * @param maxY one more than the highest y that can be assigned; typically an array length
     * @param pointsPerIteration with small areas, this can be around 5; with larger ones, 30 is reasonable
     * @param rng a Random to use for all random sampling.
     * @return a 2D int array where non-zero values represent picked points
     */
    public static int[][] sampleRectangle(GridPoint2 minPosition, GridPoint2 maxPosition, float minimumDistance,
                                                                           int maxX, int maxY, int pointsPerIteration, Random rng)
    {
        return sample(minPosition, maxPosition, 0f, minimumDistance, maxX, maxY, pointsPerIteration, rng);
    }

    protected static int[][] sample(GridPoint2 minPos, GridPoint2 maxPos,
                                                                     float maxCircleRadius, float radius,
                                                                     int xBound, int yBound,
                                                                     int pointsPerTry, Random random) {
        radius = Math.max(1.0001f, radius);
        maxCircleRadius *= maxCircleRadius;
        final float radius2 = radius * radius;
        final float iCellSize = 1f / (radius * inverseRootTwo);
        final float ik = 1f / pointsPerTry;
        final int width = maxPos.x - minPos.x + 1, height = maxPos.y - minPos.y + 1;
        final Vector2 gridCenter = new Vector2(minPos.x + maxPos.x, minPos.y + maxPos.y).scl(0.5f);
        final int gridWidth = Math.min((int) Math.ceil(width * iCellSize), xBound);
        final int gridHeight = Math.min((int) Math.ceil(height * iCellSize), yBound);
        final float[][] gridX = new float[gridWidth][gridHeight];
        final float[][] gridY = new float[gridWidth][gridHeight];
        final FloatArray qx = new FloatArray(false, gridWidth + gridHeight);
        final FloatArray qy = new FloatArray(false, gridWidth + gridHeight);
        final int[][] array = new int[width][height];
        // Pick the first sample.
        sample(width * 0.5f, height * 0.5f, iCellSize, qx, qy, gridX, gridY, minPos, array);

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
                if (x >= minPos.x && x < maxPos.x + 0.99999994f && y >= minPos.y && y < maxPos.y + 0.99999994f && far(x, y, iCellSize, radius2,
                        gridCenter, maxCircleRadius, gridX, gridY, minPos)) {
                    sample(x, y, iCellSize, qx, qy, gridX, gridY, minPos, array);
                    continue PICKING;
                }
            }

            // If none of k candidates were accepted, remove it from the queue.
            qx.removeIndex(i);
            qy.removeIndex(i);
        }
        return array;
    }
    private static boolean far(float x, float y, float iCellSize, float radius2, Vector2 gridCenter, float maxSampleRadius, float[][] gridX, float[][] gridY, GridPoint2 minPos){
        if(maxSampleRadius != 0f && gridCenter.dst2(x, y) > maxSampleRadius) return false;
        final int i = (int)((x - minPos.x) * iCellSize);
        final int j = (int)((y - minPos.y) * iCellSize);
        final int gridWidth = gridX.length;
        final int i0 = Math.max(i - 2, 0);
        final int j0 = Math.max(j - 2, 0);
        final int i1 = Math.min(i + 3, gridWidth);
        final int j1 = Math.min(j + 3, gridX[0].length);
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
    private static void sample(float x, float y, float invCellSize, FloatArray qx, FloatArray qy, float[][] gridX, float[][] gridY, GridPoint2 minPos, int[][] array){
        final int gx = (int)((x - minPos.x) * invCellSize), gy = (int)((y - minPos.y) * invCellSize);
        gridX[gx][gy] = x;
        gridY[gx][gy] = y;
        qx.add(x);
        qy.add(y);
        array[(int) x][(int) y] = qx.size;
    }
}

