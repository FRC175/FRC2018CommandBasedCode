package org.usfirst.frc.team175.robot.subsystems;

import java.util.HashMap;
import java.util.Map;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Solenoid;

import org.usfirst.frc.team175.robot.util.Constants;
import org.usfirst.frc.team175.robot.util.DiagnosableSubsystem;
import org.usfirst.frc.team175.robot.util.TalonSRXController;
import org.usfirst.frc.team175.robot.util.TalonSRXFactory;
import org.usfirst.frc.team175.robot.util.TalonSRXType;

/**
 * @author Arvind
 */
public class LateralDrive extends DiagnosableSubsystem {

    // Talon SRX
    private TalonSRX mLateralDrive;

    // Solenoid
    private Solenoid mDeploy;

    // Digital Inputs
    private Map<String, DigitalInput> mLineSensors;

    // Double
    private int mWantedPosition;

    // Enum
    public enum LineSensorPosition {
        /* Political Spectrum */
        EXTREME_LEFT(-180),
        LEFTIST(-135),
        LEFT(-90),
        CENTER_LEFT(-45),
        CENTER(0),
        CENTER_RIGHT(45),
        RIGHT(90),
        RIGHTIST(135),
        EXTREME_RIGHT(180),
        ERROR(0);

        private final int POSITION;

        private LineSensorPosition(int position) {
            POSITION = position;
        }

        public int positionToMove() {
            return POSITION;
        }
    }

    // Singleton Instance
    private static LateralDrive sInstance;

    // Singleton Static Factory Method
    public static LateralDrive getInstance() {
        if (sInstance == null) {
            try {
                sInstance = new LateralDrive();
            } catch (Exception e) {
                DriverStation.reportError("Lateral drivetrain subsystem failed to instantiate.\n" + e, true);
            }
        }

        return sInstance;
    }

    private LateralDrive() {
        /* Instantiations */
        // TalonSRXFactory.getSRX(portNum : int, type : TalonSRXType)
        mLateralDrive = TalonSRXFactory.getSRX(Constants.LATERAL_DRIVE_PORT, TalonSRXType.CROSS_THE_ROAD);

        // Solenoid(canID : int, channel : int)
        mDeploy = new Solenoid(Constants.LATERAL_DEPLOY_PORT, Constants.LATERAL_DEPLOY_CHANNEL);

        // Digital Inputs
        mLineSensors = new HashMap<String, DigitalInput>() {
            {
                put("LeftTwo", new DigitalInput(Constants.kLeftTwoSensorPort));
                put("LeftOne", new DigitalInput(Constants.kLeftOneSensorPort));
                put("Center", new DigitalInput(Constants.kCenterSensorPort));
                put("RightOne", new DigitalInput(Constants.kRightOneSensorPort));
                put("RightTwo", new DigitalInput(Constants.kRightTwoSensorPort));
            }
        };

        /* SRX Configuration */
        mLateralDrive.setInverted(false);
        mLateralDrive.setSensorPhase(false);
        // TalonSRXController.setPIDF(mLateralDrive, 0, 0, 0, 0); // 0, 10, 0, 2
    }

    public void set(boolean enable) {
        if (super.getSubsystemState()) {
            mDeploy.set(enable);
        }
    }

    public boolean isEnabled() {
        return mDeploy.get();
    }

    public void setPower(double power) {
        if (super.getSubsystemState()) {
            TalonSRXController.setPower(mLateralDrive, power);
        }
    }

    public void setPosition(int position) {
        if (super.getSubsystemState()) {
            TalonSRXController.setPosition(mLateralDrive, position);
        }
    }

    public void setWantedPosition(int wantedPosition) {
        mWantedPosition = wantedPosition;
    }

    public double getPosition() {
        return TalonSRXController.getPosition(mLateralDrive);
    }

    public boolean isAtWantedPosition() {
        return Math.abs(mWantedPosition - getPosition()) <= Constants.kLateralDrivePositionThreshold;
    }

    // TODO: Determine if this necessary
    public void setBrakeMode(boolean on) {
        if (super.getSubsystemState()) {
            TalonSRXController.setBrakeMode(mLateralDrive, on);
        }
    }

    public void setPIDF(double kF, double kP, double kI, double kD) {
        TalonSRXController.setPIDF(mLateralDrive, kF, kP, kI, kD);
    }

    public void resetEncoder() {
        TalonSRXController.resetEncoder(mLateralDrive);
    }

    private String getLineSensorArray() {
        String binarySensorArray = "";
        binarySensorArray += mLineSensors.get("LeftTwo").get() ? "1" : "0";
        binarySensorArray += mLineSensors.get("LeftOne").get() ? "1" : "0";
        binarySensorArray += mLineSensors.get("Center").get() ? "1" : "0";
        binarySensorArray += mLineSensors.get("RightOne").get() ? "1" : "0";
        binarySensorArray += mLineSensors.get("RightTwo").get() ? "1" : "0";

        return binarySensorArray;
    }

    public LineSensorPosition getLineSensorPosition() {
        switch (getLineSensorArray()) {
            case "10000":
                return LineSensorPosition.EXTREME_LEFT;
            case "11000":
                return LineSensorPosition.LEFTIST;
            case "01000":
                return LineSensorPosition.LEFT;
            case "01100":
                return LineSensorPosition.CENTER_LEFT;
            case "00100":
                return LineSensorPosition.CENTER;
            case "00110":
                return LineSensorPosition.CENTER_RIGHT;
            case "00010":
                return LineSensorPosition.RIGHT;
            case "00011":
                return LineSensorPosition.RIGHTIST;
            case "00001":
                return LineSensorPosition.EXTREME_RIGHT;
            default:
                // Robot orientation wrong
                System.out.println("ERROR - ROBOT NOT IN RIGHT POSITION TO ALIGN!");
                return LineSensorPosition.ERROR;
        }
    }

    @Override
    public void outputToSmartDashboard() {

    }

    public void outputToConsole() {
        System.out.println("INFO - Lateral Drive Position: " + getPosition());
        System.out.println("INFO - Line Sensor Array: " + getLineSensorArray());
        System.out.println("INFO - Is At Position? " + isAtWantedPosition());
        System.out.println("INFO - Wanted Position: " + mWantedPosition);
        // mLineSensors.forEach((str, sensor) -> { System.out.printf("INFO - %s State: %s\n", str, sensor.get()); } );
        System.out.println();
    }

    @Override
    public boolean runSelfTest() {
        return false;
    }

    @Override
    protected void initDefaultCommand() {
        // setDefaultCommand();
    }

}