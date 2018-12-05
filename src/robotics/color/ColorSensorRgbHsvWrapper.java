package robotics.color;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class ColorSensorRgbHsvWrapper implements AutoCloseable {
    private static final float DEFAULT_RED_MAX = 0.309f;
    private static final float DEFAULT_GREEN_MAX = 0.364f;
    private static final float DEFAULT_BLUE_MAX = 0.203f;

    private static final int RED_COLOR_INDEX = 0;
    private static final int GREEN_COLOR_INDEX = 1;
    private static final int BLUE_COLOR_INDEX = 2;

    private final EV3ColorSensor colorSensor;
    private final SampleProvider colorSampleProvider;

    private float[] rgbSamples;

    private float redMax = DEFAULT_RED_MAX;
    private float greenMax = DEFAULT_GREEN_MAX;
    private float blueMax = DEFAULT_BLUE_MAX;

    public ColorSensorRgbHsvWrapper(Port sensorPort) {
        colorSensor = new EV3ColorSensor(sensorPort);
        colorSampleProvider = colorSensor.getRGBMode();
        rgbSamples = new float[colorSampleProvider.sampleSize()];

        if (rgbSamples.length != 3) {
            throw new IllegalStateException(String.format("Color sensor is not in RGB mode. Number of samples: %d != 3",
                    rgbSamples.length));
        }
    }

    public ColorSensorRgbHsvWrapper(Port sensorPort, float redMax, float greenMax, float blueMax) {
        this(sensorPort);

        this.redMax = redMax;
        this.greenMax = greenMax;
        this.blueMax = blueMax;
    }

    private void fetchRgbSample() {
        // TODO (Ramil Safin): Add medium value filter for R, G, and B channels.
        colorSampleProvider.fetchSample(rgbSamples, 0);
    }

    public HsvColor fetchHsvSample() {
        fetchRgbSample();

        int[] normRgbSamples = new int[]{
                SensorUtils.normalize(rgbSamples[RED_COLOR_INDEX], 0f, redMax, 0, 255),
                SensorUtils.normalize(rgbSamples[GREEN_COLOR_INDEX], 0f, greenMax, 0, 255),
                SensorUtils.normalize(rgbSamples[BLUE_COLOR_INDEX], 0f, blueMax, 0, 255)
        };

        int rgbMax = SensorUtils.findMax(normRgbSamples);
        int rgbMin = SensorUtils.findMin(normRgbSamples);
        int delta = rgbMax - rgbMin;

        float hue, saturation, value;

        value = rgbMax;

        if (rgbMax == rgbMin) {
            hue = 0;
        } else {
            if (rgbMax == normRgbSamples[RED_COLOR_INDEX]) {  // max == R
                if (normRgbSamples[RED_COLOR_INDEX] > normRgbSamples[BLUE_COLOR_INDEX]) { // R > B
                    // 60 * (G - B) / (max - min)
                    hue = 60f * (normRgbSamples[GREEN_COLOR_INDEX] - normRgbSamples[BLUE_COLOR_INDEX]) / delta;
                } else {
                    // 360 + [60 * (G - B) / (max - min)]
                    hue = 360 + (60f * (normRgbSamples[GREEN_COLOR_INDEX] - normRgbSamples[BLUE_COLOR_INDEX]) / delta);
                }
            } else if (rgbMax == normRgbSamples[GREEN_COLOR_INDEX]) { // max == G
                // 120 + [60 * (B - R) / (max - min)]
                hue = 120 + (60f * (normRgbSamples[BLUE_COLOR_INDEX] - normRgbSamples[RED_COLOR_INDEX]) / delta);
            } else {
                // 240 + [60 * (B - R) / (max - min)]
                hue = 240 + (60f * (normRgbSamples[BLUE_COLOR_INDEX] - normRgbSamples[RED_COLOR_INDEX]) / delta);
            }
        }

        hue = Math.abs(hue);

        if (rgbMax == 0) {
            saturation = 0;
        } else {
            saturation = 1 - (rgbMin / (float) rgbMax);
        }

        return new HsvColor(hue, saturation, value);
    }

    @Override
    public void close() {
        colorSensor.close();
    }
}
