package robotics.motor;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.Port;

import java.util.HashMap;
import java.util.Map;

public class GreenFieldManipulator {

    private static final int BASE_MOTOR_SPEED = 20;
    private static final int ELBOW_MOTOR_SPEED = 20;

    private final EV3LargeRegulatedMotor baseMotor;
    private final EV3MediumRegulatedMotor elbowMotor;

    private static final Map<FieldPosition, TachoCount> lookupTable;

    static {
        lookupTable = new HashMap<>(9);

        lookupTable.put(new FieldPosition(1, 1), new TachoCount(0, 0));
        lookupTable.put(new FieldPosition(1, 2), new TachoCount(0, 0));
        lookupTable.put(new FieldPosition(1, 3), new TachoCount(0, 0));

        lookupTable.put(new FieldPosition(2, 1), new TachoCount(0, 0));
        lookupTable.put(new FieldPosition(2, 2), new TachoCount(0, 0));
        lookupTable.put(new FieldPosition(2, 3), new TachoCount(0, 0));

        lookupTable.put(new FieldPosition(3, 1), new TachoCount(0, 0));
        lookupTable.put(new FieldPosition(3, 2), new TachoCount(0, 0));
        lookupTable.put(new FieldPosition(3, 3), new TachoCount(0, 0));
    }

    public GreenFieldManipulator(Port baseMotorPort, Port elbowMotorPort) {
        baseMotor = new EV3LargeRegulatedMotor(baseMotorPort);
        elbowMotor = new EV3MediumRegulatedMotor(elbowMotorPort);

        baseMotor.setSpeed(BASE_MOTOR_SPEED);
        elbowMotor.setSpeed(ELBOW_MOTOR_SPEED);

        baseMotor.setAcceleration(10);
        elbowMotor.setAcceleration(10);

        baseMotor.resetTachoCount();
        elbowMotor.resetTachoCount();
    }

    public void moveTo(FieldPosition position) {
        // lookup the tachometer values and move while encoders have not reached them
    }

    public void moveToBase(TachoCount tachoCount) {
        // move backwards until tacho count is not zero
    }

    /*
       31(7)   32(8)   33(9)
       21(4)   22(5)   23(6)
       11(1)   12(2)   13 (3)
     */

    private static class FieldPosition {
        private int x;
        private int y;

        public FieldPosition(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    private static class TachoCount {
        private int baseTachoCount;
        private int elbowTachoCount;

        public TachoCount(int baseTachoCount, int elbowTachoCount) {
            this.baseTachoCount = baseTachoCount;
            this.elbowTachoCount = elbowTachoCount;
        }

        public int getBaseTachoCount() {
            return baseTachoCount;
        }

        public int getElbowTachoCount() {
            return elbowTachoCount;
        }
    }
}
