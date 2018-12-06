package robotics;

import lejos.hardware.Sound;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;
import robotics.motor.GreenFieldManipulator;

public class Demo {

    private static final int ID_X = 1;
    private static final int ID_Y = 1;

    public static void main(String[] args) {
        GreenFieldManipulator manipulator = new GreenFieldManipulator(MotorPort.A, MotorPort.B, SensorPort.S4);

        Sound.beep(); // START

        manipulator.moveTo(ID_X, ID_Y); // scanning inside move

        Sound.beep(); // STOP

        Delay.msDelay(20 * 1000);
    }
}
