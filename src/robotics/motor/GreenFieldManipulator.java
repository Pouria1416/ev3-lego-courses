package robotics.motor;

import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.utility.Delay;
import robotics.color.ColorSensorRgbHsvWrapper;
import robotics.color.SensorUtils;

import java.util.HashMap;
import java.util.Map;

public class GreenFieldManipulator {

    private static final int BASE_MOTOR_SPEED = 10;
    private static final int ELBOW_MOTOR_SPEED = 10;

    private final EV3LargeRegulatedMotor baseMotor;
    private final EV3MediumRegulatedMotor elbowMotor;

    private final ColorSensorRgbHsvWrapper colorSensor;

    /*
    static {
        lookupTable = new HashMap<>(9);

        // elbow - base
        lookupTable.put(new FieldPosition(1, 1), new TachoCount(0, 0));
        lookupTable.put(new FieldPosition(1, 2), new TachoCount(0, 40));
        lookupTable.put(new FieldPosition(1, 3), new TachoCount(-70, 90));

        // elbow - base
        lookupTable.put(new FieldPosition(2, 1), new TachoCount(-20, 330));
        lookupTable.put(new FieldPosition(2, 2), new TachoCount(-38, 290));
        lookupTable.put(new FieldPosition(2, 3), new TachoCount(-65, 320));

        // elbow - base - elbow

        // 360 -75 400
        lookupTable.put(new FieldPosition(3, 1), new TachoCount(0, 0));


        lookupTable.put(new FieldPosition(3, 2), new TachoCount(0, 0));
        lookupTable.put(new FieldPosition(3, 3), new TachoCount(0, 0));
    }
    */

    public GreenFieldManipulator(Port baseMotorPort, Port elbowMotorPort, Port colorSensorPort) {
        colorSensor = new ColorSensorRgbHsvWrapper(colorSensorPort);
        baseMotor = new EV3LargeRegulatedMotor(baseMotorPort);
        elbowMotor = new EV3MediumRegulatedMotor(elbowMotorPort);

        baseMotor.setSpeed(BASE_MOTOR_SPEED);
        elbowMotor.setSpeed(ELBOW_MOTOR_SPEED);

        baseMotor.setAcceleration(10);
        elbowMotor.setAcceleration(10);

        baseMotor.resetTachoCount();
        elbowMotor.resetTachoCount();
    }

    public void moveTo(int x, int y) {

        if (x == 1 && y == 1) {
            elbowMotor.rotateTo(400);
            baseMotor.rotateTo(-135);
            elbowMotor.rotate(720 + 320 + 165);

            Delay.msDelay(500);
            LCD.drawString(String.format("%d", SensorUtils.determineColorId(colorSensor.fetchHsvSample())), 1, 1);

            elbowMotor.rotate(720 + 320 + 160);

            baseMotor.rotateTo(150, true);
            while (Math.abs(baseMotor.getTachoCount()) > 5) { }
            baseMotor.stop();

            elbowMotor.rotateTo(600, true);
            while (Math.abs(elbowMotor.getTachoCount()) > 5) { }
            elbowMotor.stop();
        }

        if (x == 1 && y == 2) {
            elbowMotor.rotateTo(40);

            Delay.msDelay(500);
            LCD.drawString(String.format("%d", SensorUtils.determineColorId(colorSensor.fetchHsvSample())), 1, 1);

            elbowMotor.rotateTo(-60, true);
            while (Math.abs(elbowMotor.getTachoCount()) > 5) { }
            elbowMotor.stop();
        }

         if (x == 1 && y == 3) {
             elbowMotor.rotateTo(120);
             baseMotor.rotateTo(-70);

             Delay.msDelay(500);
             LCD.drawString(String.format("%d", SensorUtils.determineColorId(colorSensor.fetchHsvSample())), 1, 1);

             baseMotor.rotateTo(90, true);
             while (Math.abs(baseMotor.getTachoCount()) > 5) { }
             baseMotor.stop();

             elbowMotor.rotateTo(-140, true);
             while (Math.abs(elbowMotor.getTachoCount()) > 5) { }
             elbowMotor.stop();
         }

         if (x == 2 && y == 1) {
             elbowMotor.rotateTo(330);
             baseMotor.rotateTo(-20);

             Delay.msDelay(500);
             LCD.drawString(String.format("%d", SensorUtils.determineColorId(colorSensor.fetchHsvSample())), 1, 1);

             baseMotor.rotateTo(40, true);
             while (Math.abs(baseMotor.getTachoCount()) > 5) { }
             baseMotor.stop();

             elbowMotor.rotateTo(-350, true);
             while (Math.abs(elbowMotor.getTachoCount()) > 5) { }
             elbowMotor.stop();
         }

         if (x == 2 && y == 2) {
             elbowMotor.rotateTo(290);
             baseMotor.rotateTo(-38);

             Delay.msDelay(500);
             LCD.drawString(String.format("%d", SensorUtils.determineColorId(colorSensor.fetchHsvSample())), 1, 1);

             baseMotor.rotateTo(58, true);
             while (Math.abs(baseMotor.getTachoCount()) > 5) { }
             baseMotor.stop();

             elbowMotor.rotateTo(-310, true);
             while (Math.abs(elbowMotor.getTachoCount()) > 5) { }
             elbowMotor.stop();
         }

         if (x == 2 && y == 3) {
             elbowMotor.rotateTo(320);
             baseMotor.rotateTo(-65);

             Delay.msDelay(500);
             LCD.drawString(String.format("%d", SensorUtils.determineColorId(colorSensor.fetchHsvSample())), 1, 1);

             baseMotor.rotateTo(75, true);
             while (Math.abs(baseMotor.getTachoCount()) > 5) { }
             baseMotor.stop();

             elbowMotor.rotateTo(-340, true);
             while (Math.abs(elbowMotor.getTachoCount()) > 5) { }
             elbowMotor.stop();
         }

         if (x == 3 && y == 1) {
             elbowMotor.rotateTo(360);
             baseMotor.rotateTo(-75);
             elbowMotor.rotateTo(400);

             Delay.msDelay(500);
             LCD.drawString(String.format("%d", SensorUtils.determineColorId(colorSensor.fetchHsvSample())), 1, 1);

             elbowMotor.rotate(-800);

             baseMotor.rotateTo(90, true);
             while (Math.abs(baseMotor.getTachoCount()) > 5) { }
             baseMotor.stop();
         }

         if (x == 3 & y == 2) {
             elbowMotor.rotateTo(360);
             baseMotor.rotateTo(-85);
             elbowMotor.rotateTo(330);

             Delay.msDelay(500);
             LCD.drawString(String.format("%d", SensorUtils.determineColorId(colorSensor.fetchHsvSample())), 1, 1);

             elbowMotor.rotate(-800);

             baseMotor.rotateTo(100, true);
             while (Math.abs(baseMotor.getTachoCount()) > 5) { }
             baseMotor.stop();
         }

         if (x == 3 && y == 3) {
             elbowMotor.rotateTo(360);
             baseMotor.rotateTo(-110);
             elbowMotor.rotateTo(485);

             Delay.msDelay(500);
             LCD.drawString(String.format("%d", SensorUtils.determineColorId(colorSensor.fetchHsvSample())), 1, 1);

             baseMotor.rotate(50);

             elbowMotor.rotateTo(-900, true);
             while (Math.abs(elbowMotor.getTachoCount()) > 5) { }
             elbowMotor.stop();

             baseMotor.rotateTo(110, true);
             while (Math.abs(baseMotor.getTachoCount()) > 5) { }
             baseMotor.stop();
         }
    }
}
