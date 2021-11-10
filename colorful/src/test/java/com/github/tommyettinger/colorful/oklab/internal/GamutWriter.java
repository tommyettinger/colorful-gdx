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
        double minA = 1000.0, maxA = -1000.0, minB = 1000.0, maxB = -1000.0, maxDist = -1000.0, furthest = 300.0;
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
                        all[idx++] = (byte) (dist);
                        largestDist = Math.max(largestDist, dist);
                        minA = Math.min(minA, A);
                        maxA = Math.max(maxA, A);
                        minB = Math.min(minB, B);
                        maxB = Math.max(maxB, B);
                        if(maxDist != (maxDist = Math.max(maxDist, Math.sqrt(A * A + B * B))))
                            furthest = L;
                        continue PER_HUE;
                    }
                }
                System.out.println("Problem at light " + light + " angle " + angle);
            }
        }
        System.out.println("Assigned " + idx + " distances");
        System.out.println("largest distance: " + largestDist);
        System.out.println("min A: " + minA);
        System.out.println("max A: " + maxA);
        System.out.println("min B: " + minB);
        System.out.println("max B: " + maxB);
        System.out.println("dist : " + maxDist);
        System.out.println("at   : " + furthest);
        Gdx.files.local("OklabGamut.dat").writeBytes(all, false);
        generateByteString(all, "OklabGamut.txt");
        Gdx.app.exit();
    }
    /**
     * Given a byte array, this appends to a file called {@code filename} containing a code snippet that can be pasted
     * into Java code as a huge byte array.
     * @param data the bytes to use as data
     * @param filename the name of the text file to append to
     */
    public static void generateByteString(final byte[] data, String filename){
        StringBuilder sb = new StringBuilder(data.length + 400);
        sb.append("new StringBuilder().append(\"");
        for (int i = 0; i < data.length;) {
            byte b = data[i++];
            switch (b) {
                case '\t':
                    sb.append("\\t");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                default:
                    if (Character.isISOControl(b))
                        sb.append(String.format("\\%03o", b));
                    else
                        sb.append((char) (b & 0xFF));
                    break;
            }
            if((sb.length() & 0xFFFF) == 0xFFFF)
                sb.append("\").append(\"");
        }
        sb.append("\").toString().getBytes(\"ISO_8859_1\");\n");
        Gdx.files.local(filename).writeString(sb.toString(), false, "ISO-8859-1");
        System.out.println("Wrote code snippet to " + filename);
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
        //reverseLight() for double
        L = (L - 1.0) / (1.0 + L * 0.75) + 1.0;
        //forwardLight() for double
//        L = (L - 1.0) / (1.0 - L * 0.4285714) + 1.0;
//        A += 0.0039215684;
//        B += 0.0039215684;

        double l = (L + +0.3963377774 * A + +0.2158037573 * B); l *= l * l;
        double m = (L + -0.1055613458 * A + -0.0638541728 * B); m *= m * m;
        double s = (L + -0.0894841775 * A + -1.2914855480 * B); s *= s * s;

        final int r = (int)((255.0 * +4.0767245293) * l + (255.0 * -3.3072168827) * m + (255.0 * +0.2307590544) * s + 0.5);
        final int g = (int)((255.0 * -1.2681437731) * l + (255.0 * +2.6093323231) * m + (255.0 * -0.3411344290) * s + 0.5);
        final int b = (int)((255.0 * -0.0041119885) * l + (255.0 * -0.7034763098) * m + (255.0 * +1.7068625689) * s + 0.5);
        return r >= 0 && r <= 255 && g >= 0 && g <= 255 && b >= 0 && b <= 255;

//        double l = (L + 0.3963377774 * A + 0.2158037573 * B); l *= l * l;
//        double m = (L - 0.1055613458 * A - 0.0638541728 * B); m *= m * m;
//        double s = (L - 0.0894841775 * A - 1.2914855480 * B); s *= s * s;
//
//        final double r = +4.0767245293 * l - 3.3072168827 * m + 0.2307590544 * s;
//        if(r < 0.0 || r > 1.0) return false;
//        final double g = -1.2681437731 * l + 2.6093323231 * m - 0.3411344290 * s;
//        if(g < 0.0 || g > 1.0) return false;
//        final double b = -0.0041119885 * l - 0.7034763098 * m + 1.7068625689 * s;
//        return (b >= 0.0 && b <= 1.0);
//        return (r >= 0.0 && r <= 1.0) || (g >= 0.0 && g <= 1.0) || (b >= 0.0 && b <= 1.0);
    }
}
