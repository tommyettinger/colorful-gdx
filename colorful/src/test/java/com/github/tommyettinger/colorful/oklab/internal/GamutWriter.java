package com.github.tommyettinger.colorful.oklab.internal;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class GamutWriter extends ApplicationAdapter {

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Gamut Data Generator");
        config.setWindowedMode(320, 320);
        config.setIdleFPS(1);
        config.setResizable(false);
        new Lwjgl3Application(new GamutWriter(), config);
    }

    public void create() {
        byte[] all = new byte[65536];
        int idx = 0, largestDist = -1;
        for (int light = 0; light < 256; light++) {
            double L = light * 0x1p-8;
            PER_HUE:
            for (int angle = 0; angle < 256; angle++) {
                double theta = angle * 0x1p-7 * Math.PI;
                double s = Math.sin(theta), c = Math.cos(theta);
                for (int dist = 255; dist >= 0; dist--) {
                    double d = dist * 0x1p-8, A = c * d, B = s * d;
                    if(inGamut(L, A, B))
                    {
                        all[idx++] = (byte) dist;
                        largestDist = Math.max(largestDist, dist);
                        continue PER_HUE;
                    }
                }
                System.out.println("Problem at light " + light + " angle " + angle);
            }
        }
        System.out.println("Assigned " + idx + " distances");
        System.out.println("largest distance: " + largestDist);
        Gdx.files.local("OklabGamut.dat").writeBytes(all, false);
        Gdx.app.exit();
    }
        /**
         * Returns true if the given Oklab values are valid to convert losslessly back to RGBA.
         * @param L lightness channel, as a double from 0 to 1
         * @param A green-to-red chromatic channel, as a double from 0 to 1
         * @param B blue-to-yellow chromatic channel, as a double from 0 to 1
         * @return true if the given Oklab channels can be converted back and forth to RGBA
         */
    public static boolean inGamut(double L, double A, double B)
    {
        double l = (L + 0.3963377774 * A + 0.2158037573 * B); l *= l * l;
        double m = (L - 0.1055613458 * A - 0.0638541728 * B); m *= m * m;
        double s = (L - 0.0894841775 * A - 1.2914855480 * B); s *= s * s;

        final double r = +4.0767245293 * l - 3.3072168827 * m + 0.2307590544 * s;
        if(r < 0.0 || r > 1.0) return false;
        final double g = -1.2681437731 * l + 2.6093323231 * m - 0.3411344290 * s;
        if(g < 0.0 || g > 1.0) return false;
        final double b = -0.0041119885 * l - 0.7034763098 * m + 1.7068625689 * s;
        return (b >= 0.0 && b <= 1.0);
    }
}
