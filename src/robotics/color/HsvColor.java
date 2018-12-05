package robotics.color;

public class HsvColor {
    private float hue;
    private float saturation;
    private float value;

    public HsvColor(float hue, float saturation, float value) {
        this.hue = hue;
        this.saturation = saturation;
        this.value = value;
    }

    public float getHue() {
        return hue;
    }

    public float getSaturation() {
        return saturation;
    }

    public float getValue() {
        return value;
    }
}

