package org.usfirst.frc.team175.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc.team175.robot.util.Constants;
import org.usfirst.frc.team175.robot.util.DiagnosableSubsystem;
import org.usfirst.frc.team175.robot.util.TalonSRXController;
import org.usfirst.frc.team175.robot.util.TalonSRXFactory;
import org.usfirst.frc.team175.robot.util.TalonSRXType;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Arvind
 */
public class Elevator extends DiagnosableSubsystem {

    /* Declarations */
    // Talon SRX
    private TalonSRX mElevator;

    // Double
    private double mWantedPositon;

    // Enum
    public enum ElevatorPosition {
        POWER_CUBE_PICKUP(-225),
        POWER_CUBE_LIFT(-600),
        EXCHANGE(-1926),
        SWITCH(-12000),
        LOW_SCALE(-25555),
        HIGH_SCALE(-33050);

        private final double COUNTS;

        private ElevatorPosition(double counts) {
            COUNTS = counts;
        }

        public double toCounts() {
            return COUNTS;
        }
    }

    // Singleton Instantiation
    public static Elevator sInstance;

    // Singleton Static Factory Method
    public static Elevator getInstance() {
        if (sInstance == null) {
            try {
                sInstance = new Elevator();
            } catch (Exception e) {
                DriverStation.reportError("Drivetrain subsystem failed to instantiate.\n" + e, true);
            }
        }

        return sInstance;
    }

    private Elevator() {
        /* Instantiation */
        mElevator = TalonSRXFactory.getSRX(Constants.ELEVATOR_PORT, TalonSRXType.CROSS_THE_ROAD);

        /* SRX Configuration */
        TalonSRXController.configCurrentLimiting(mElevator, 20, 30, 100, true);
        // Set limit at high scale
        TalonSRXController.configForwardSoftLimit(mElevator, (int) ElevatorPosition.HIGH_SCALE.toCounts(), false);
        TalonSRXController.configReverseSoftLimit(mElevator, 0, false); // Set limit at zero positon
    }

    public void setPower(double power) {
        if (super.getSubsystemState()) {
            TalonSRXController.setPower(mElevator, power);
        }
    }

    public void setPosition(ElevatorPosition position) {
        TalonSRXController.setPosition(mElevator, position.toCounts());
    }

    public void setPosition(double position) {
        if (super.getSubsystemState()) {
            TalonSRXController.setPosition(mElevator, position);
        }
    }

    public double getPosition() {
        return TalonSRXController.getPosition(mElevator);
    }

    public void setBrakeMode(boolean enable) {
        if (super.getSubsystemState()) {
            TalonSRXController.setBrakeMode(mElevator, enable);
        }
    }

    public void setPIDF(double kF, double kP, double kI, double kD) {
        TalonSRXController.setPIDF(mElevator, kF, kP, kI, kD);
    }

    public void resetEncoder() {
        TalonSRXController.resetEncoder(mElevator);
    }

    // TODO: Determine if upper limit is forward limit switch and vice versa
    public boolean isUpperLimitHit() {
        return TalonSRXController.isForwardLimitHit(mElevator);
    }

    public boolean isLowerLimitHit() {
        return TalonSRXController.isReverseLimitHit(mElevator);
    }

    // TODO: Determine in upper value and lower value options are needed
    public void resetLimits() {
        TalonSRXController.resetLimits(mElevator, 1, 0);
    }

    public double getWantedPosition() {
        return mWantedPositon;
    }

    public void setWantedPosition(double position) {
        if (super.getSubsystemState()) {
            mWantedPositon = position;
        }
    }

    public void setWantedPosition(ElevatorPosition position) {
        if (super.getSubsystemState()) {
            mWantedPositon = position.toCounts();
        }
    }

    @Override
    public boolean runSelfTest() {
        return false;
    }

    @Override
    public void outputToSmartDashboard() {
    }

    @Override
    protected void initDefaultCommand() {
    }

}