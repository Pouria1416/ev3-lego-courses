package robotics;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class Demo {
    public static void main(String[] args) {

        Sound.beepSequence();
        LCD.drawString("Press any key...", 1, 1);
        Button.waitForAnyPress();

        EV3LargeRegulatedMotor base = new EV3LargeRegulatedMotor(MotorPort.A);
        EV3MediumRegulatedMotor elbow = new EV3MediumRegulatedMotor(MotorPort.B);

        base.setAcceleration(10);
        base.setSpeed(20);

        elbow.setAcceleration(10);
        elbow.setSpeed(20);

        // WARNING: Check this values
        base.setStallThreshold(1, 100);
        elbow.setStallThreshold(1, 100);

        LCD.clearDisplay();

        LCD.drawString(String.format("Base: %d", base.getTachoCount()), 1, 1);
        LCD.drawString(String.format("Elbow: %d", elbow.getTachoCount()), 1, 2);

        Delay.msDelay(1_000);

        Sound.twoBeeps();

        Button.waitForAnyPress();

        /*
        try (ColorSensorRgbHsvWrapper colorProvider = new ColorSensorRgbHsvWrapper(SensorPort.S4)) {

            while (true) {
                LCD.clearDisplay();

                HsvColor color = colorProvider.fetchHsvSample();

                LCD.drawString(String.format("H: %.6f", color.getHue()), 1, 1);
                LCD.drawString(String.format("S: %.6f", color.getSaturation()), 1, 2);
                LCD.drawString(String.format("V: %.6f", color.getValue()), 1, 3);

                int colorId = SensorUtils.determineColorId(color);

                LCD.drawString("Color: " + colorId, 1, 5);

                Delay.msDelay(33);
            }
        }
        */
    }
}
