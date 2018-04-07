package org.usfirst.frc.team175.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team175.robot.Constants;
import org.usfirst.frc.team175.robot.RobotMap;

/**
 * TODO: Make and set default command.
 */
public class Drive extends Subsystem {

    // Talon SRXs
    // WPI_TalonSRX(int CAN id)
    // No physical difference between WPI_TalonSRX and TalonSRX
    private WPI_TalonSRX mLeftMaster;
    private WPI_TalonSRX mRightMaster;
    private WPI_TalonSRX mLeftFollower;
    private WPI_TalonSRX mRightFollower;

    // Solenoids
    // Solenoid(int CAN id of PCM, int channel)
    private Solenoid mShift;

    public Drive(double leftKF, double leftKP, double leftKI, double leftKD, double rightKF, double rightKP, double rightKI, double rightKD) {
        /* Instantiations */
        mLeftMaster = new WPI_TalonSRX(RobotMap.LEFT_MASTER_PORT);
        mRightMaster = new WPI_TalonSRX(RobotMap.RIGHT_MASTER_PORT);
        mLeftFollower = new WPI_TalonSRX(RobotMap.LEFT_FOLLOWER_PORT);
        mRightFollower = new WPI_TalonSRX(RobotMap.RIGHT_FOLLOWER_PORT);

        mShift = new Solenoid(RobotMap.SHIFT_PORT, RobotMap.SHIFT_CHANNEL);

        /* SRX Configuration */
        mLeftFollower.follow(mLeftMaster);
        mRightFollower.follow(mRightMaster);

        mLeftFollower.setInverted(false);
        mRightFollower.setInverted(false);
        mLeftFollower.setInverted(false);
        mRightFollower.setInverted(false);

        /* Left Master PID Configuration */
        mLeftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.K_DRIVE_PID_LOOP_INDEX, Constants.K_DRIVE_TIMEOUT_MS);
        mLeftMaster.setSensorPhase(true);

        mLeftMaster.configNominalOutputForward(0, Constants.K_DRIVE_TIMEOUT_MS);
        mLeftMaster.configNominalOutputReverse(0, Constants.K_DRIVE_TIMEOUT_MS);
        mLeftMaster.configPeakOutputForward(1, Constants.K_DRIVE_TIMEOUT_MS);
        mLeftMaster.configPeakOutputReverse(-1, Constants.K_DRIVE_TIMEOUT_MS);

        mLeftMaster.configAllowableClosedloopError(0, Constants.K_DRIVE_PID_LOOP_INDEX, Constants.K_DRIVE_TIMEOUT_MS);

        mLeftMaster.config_kF(Constants.K_DRIVE_PID_LOOP_INDEX, leftKF, Constants.K_DRIVE_TIMEOUT_MS); // 0
        mLeftMaster.config_kP(Constants.K_DRIVE_PID_LOOP_INDEX, leftKP, Constants.K_DRIVE_TIMEOUT_MS); // 0.12
        mLeftMaster.config_kI(Constants.K_DRIVE_PID_LOOP_INDEX, leftKI, Constants.K_DRIVE_TIMEOUT_MS); // 0
        mLeftMaster.config_kD(Constants.K_DRIVE_PID_LOOP_INDEX, leftKD, Constants.K_DRIVE_TIMEOUT_MS); // 0.0012

        mLeftMaster.setSelectedSensorPosition(Constants.DRIVE_CLOSED_LOOP_TYPE, Constants.K_DRIVE_PID_LOOP_INDEX, Constants.K_DRIVE_TIMEOUT_MS);
        mLeftMaster.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);

        /* Right Master PID Configuration */
        mRightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.K_DRIVE_PID_LOOP_INDEX, Constants.K_DRIVE_TIMEOUT_MS);
        mRightMaster.setSensorPhase(false);

        mRightMaster.configNominalOutputForward(0, Constants.K_DRIVE_TIMEOUT_MS);
        mRightMaster.configNominalOutputReverse(0, Constants.K_DRIVE_TIMEOUT_MS);
        mRightMaster.configPeakOutputForward(1, Constants.K_DRIVE_TIMEOUT_MS);
        mRightMaster.configPeakOutputReverse(-1, Constants.K_DRIVE_TIMEOUT_MS);

        mRightMaster.configAllowableClosedloopError(0, Constants.K_DRIVE_PID_LOOP_INDEX, Constants.K_DRIVE_TIMEOUT_MS);

        mRightMaster.config_kF(Constants.K_DRIVE_PID_LOOP_INDEX, rightKF, Constants.K_DRIVE_TIMEOUT_MS); // 0
        mRightMaster.config_kP(Constants.K_DRIVE_PID_LOOP_INDEX, rightKP, Constants.K_DRIVE_TIMEOUT_MS); // 0.08
        mRightMaster.config_kI(Constants.K_DRIVE_PID_LOOP_INDEX, rightKI, Constants.K_DRIVE_TIMEOUT_MS); // 0
        mRightMaster.config_kD(Constants.K_DRIVE_PID_LOOP_INDEX, rightKD, Constants.K_DRIVE_TIMEOUT_MS); // 0

        mRightMaster.setSelectedSensorPosition(Constants.DRIVE_CLOSED_LOOP_TYPE, Constants.K_DRIVE_PID_LOOP_INDEX, Constants.K_DRIVE_TIMEOUT_MS);

        mRightMaster.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
    }

    public void shift(boolean power) {
        mShift.set(power);
    }

    public boolean isShift() {
        return !mShift.get();
    }

    public void powerDrive(double leftPower, double rightPower) {
        mLeftMaster.set(ControlMode.PercentOutput, leftPower);
        mRightMaster.set(ControlMode.PercentOutput, rightPower);
    }

    public void countsDrive(double leftCounts, double rightCounts) {
        mLeftMaster.set(ControlMode.Position, leftCounts);
        mRightMaster.set(ControlMode.Position, rightCounts);
    }

    public double getLeftDrivePosition() {
        return mLeftMaster.getSelectedSensorPosition(Constants.DRIVE_CLOSED_LOOP_TYPE);
    }

    public double getRightDrivePosition() {
        return mRightMaster.getSelectedSensorPosition(Constants.DRIVE_CLOSED_LOOP_TYPE);
    }
    
    public void setLeftPID(double kF, double kP, double kI, double kD) {
    	mLeftMaster.config_kF(Constants.K_DRIVE_PID_LOOP_INDEX, kF, Constants.K_DRIVE_TIMEOUT_MS);
        mLeftMaster.config_kP(Constants.K_DRIVE_PID_LOOP_INDEX, kP, Constants.K_DRIVE_TIMEOUT_MS);
        mLeftMaster.config_kI(Constants.K_DRIVE_PID_LOOP_INDEX, kI, Constants.K_DRIVE_TIMEOUT_MS);
        mLeftMaster.config_kD(Constants.K_DRIVE_PID_LOOP_INDEX, kD, Constants.K_DRIVE_TIMEOUT_MS);
    }

    public void setRightPID(double kF, double kP, double kI, double kD) {
    	mRightMaster.config_kF(Constants.K_DRIVE_PID_LOOP_INDEX, kF, Constants.K_DRIVE_TIMEOUT_MS);
        mRightMaster.config_kP(Constants.K_DRIVE_PID_LOOP_INDEX, kP, Constants.K_DRIVE_TIMEOUT_MS);
        mRightMaster.config_kI(Constants.K_DRIVE_PID_LOOP_INDEX, kI, Constants.K_DRIVE_TIMEOUT_MS);
        mRightMaster.config_kD(Constants.K_DRIVE_PID_LOOP_INDEX, kD, Constants.K_DRIVE_TIMEOUT_MS);
    }
    
    public void zeroEncoders() {
    	mLeftMaster.setSelectedSensorPosition(0, Constants.K_DRIVE_PID_LOOP_INDEX, Constants.K_DRIVE_TIMEOUT_MS);
    	mRightMaster.setSelectedSensorPosition(0, Constants.K_DRIVE_PID_LOOP_INDEX, Constants.K_DRIVE_TIMEOUT_MS);
    }
    
    public void initDefaultCommand() {
        // TODO: Set the default command, if any, for a subsystem here. Example:
        //    setDefaultCommand(new MySpecialCommand());
    }

}

