package robotics.color;

import lejos.robotics.Color;

public class SensorUtils {

    public static int normalize(float sample, float inMin, float inMax, int outMin, int outMax) {
        return (int) ((sample - inMin) * (outMax - outMin) / (inMax - inMin) + outMin);
    }

    public static int findMax(int[] samples) {
        int currentMax = samples[0];

        for (int i = 1; i < samples.length; i++) {
            if (samples[i] > currentMax) {
                currentMax = samples[i];
            }
        }

        return currentMax;
    }

    public static int findMin(int[] samples) {
        int currentMin = samples[0];

        for (int i = 1; i < samples.length; i++) {
            if (samples[i] < currentMin) {
                currentMin = samples[i];
            }
        }

        return currentMin;
    }

    public static boolean isInRange(float sample, float min, float max) {
        return sample >= min && sample < max;
    }

    // NOTE: see lejos.robotics.Color
    public static int determineColorId(HsvColor color) {
        int colorId;

        if (SensorUtils.isInRange(color.getValue(), 10f, 170f)) {

            if (SensorUtils.isInRange(color.getHue(), 0f, 50f)) {
                colorId = 4;
            } else if (SensorUtils.isInRange(color.getHue(), 200f, 360f)) {
                if (SensorUtils.isInRange(color.getSaturation(), 0f, 0.6f)) {
                    colorId = 2;
                } else {
                    colorId = 1;
                }
            } else {
                colorId = 2;
            }
        } else {
            if (SensorUtils.isInRange(color.getHue(), 290f, 300f)) {
                colorId = 2;
            } else if (color.getSaturation() > 0.58f) {
                colorId = 0;
            } else {
                colorId = 0;
            }
        }

        return colorId;
    }
}
