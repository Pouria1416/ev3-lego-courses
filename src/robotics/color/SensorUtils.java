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
        int colorId = Color.NONE;

        if (SensorUtils.isInRange(color.getValue(), 20f, 170f)) {

            if (SensorUtils.isInRange(color.getHue(), 0f, 40f)) {

                if (SensorUtils.isInRange(color.getSaturation(), 0.8f, 0.95f)) {
                    colorId = Color.RED;
                } else {
                    colorId = Color.YELLOW;
                }

            } else if (SensorUtils.isInRange(color.getHue(), 40f, 60f)) {
                if (SensorUtils.isInRange(color.getSaturation(), 0.5f, 0.7f)) {
                    colorId = Color.YELLOW;
                } else {
                    colorId = Color.RED;
                }
            } else if (SensorUtils.isInRange(color.getHue(), 60f, 200f)) {
                if (color.getSaturation() <= 0.6f) {
                    colorId = Color.GREEN;
                } else {
                    colorId = Color.BLUE;
                }
            } else if (SensorUtils.isInRange(color.getHue(), 200f, 320f)) {
                if (color.getSaturation() > 0.65f) {
                    colorId = Color.BLUE;
                } else {
                    colorId = Color.GREEN;
                }
            }

        } else if (color.getValue() >= 170f) {
            colorId = Color.WHITE;
        } else {
            colorId = Color.BLACK;
        }

        return colorId;
    }
}
