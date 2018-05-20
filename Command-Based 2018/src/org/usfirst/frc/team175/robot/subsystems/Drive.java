package org.usfirst.frc.team175.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team175.robot.Constants;
import org.usfirst.frc.team175.robot.RobotMap;
import org.usfirst.frc.team175.robot.commands.teleop.JoystickDrive;

/**
 * @author Arvind
 */
public class Drive extends Subsystem {

	/* Declarations */
	// Talon SRXs
	// No physical difference between WPI_TalonSRX and TalonSRX
	private WPI_TalonSRX mLeftMaster;
	private WPI_TalonSRX mRightMaster;
	private WPI_TalonSRX mLeftFollower;
	private WPI_TalonSRX mRightFollower;

	// Solenoid
	private Solenoid mShift;

	// Gyro
	ADXRS450_Gyro mGyro;

	// Robot Drive
	private DifferentialDrive mRobotDrive;

	public Drive(double leftKF, double leftKP, double leftKI, double leftKD, double rightKF, double rightKP,
			double rightKI, double rightKD) {
		/* Instantiations */
		// WPI_TalonSRX(canID : int)
		mLeftMaster = new WPI_TalonSRX(RobotMap.LEFT_MASTER_PORT);
		mRightMaster = new WPI_TalonSRX(RobotMap.RIGHT_MASTER_PORT);
		mLeftFollower = new WPI_TalonSRX(RobotMap.LEFT_FOLLOWER_PORT);
		mRightFollower = new WPI_TalonSRX(RobotMap.RIGHT_FOLLOWER_PORT);

		// Solenoid(canID : int, channel : int)
		mShift = new Solenoid(RobotMap.SHIFT_PORT, RobotMap.SHIFT_CHANNEL);
		
		// Gyro
		mGyro = new ADXRS450_Gyro();

		// DifferentialDrive(rightMotorController : SpeedController, leftMotorController : SpeedController)
		mRobotDrive = new DifferentialDrive(mRightMaster, mLeftMaster);

		/* General SRX Configuration */
		mLeftFollower.follow(mLeftMaster);
		mRightFollower.follow(mRightMaster);

		mLeftMaster.setInverted(true);
		mRightMaster.setInverted(true);
		mLeftFollower.setInverted(true);
		mRightFollower.setInverted(true);

		/* Left Master Configuration */
		mLeftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.K_DRIVE_PID_LOOP_INDEX,
				Constants.K_DRIVE_TIMEOUT_MS);
		mLeftMaster.setSensorPhase(true);

		mLeftMaster.configNominalOutputForward(0, Constants.K_DRIVE_TIMEOUT_MS);
		mLeftMaster.configNominalOutputReverse(0, Constants.K_DRIVE_TIMEOUT_MS);
		mLeftMaster.configPeakOutputForward(1, Constants.K_DRIVE_TIMEOUT_MS);
		mLeftMaster.configPeakOutputReverse(-1, Constants.K_DRIVE_TIMEOUT_MS);

		mLeftMaster.configAllowableClosedloopError(0, Constants.K_DRIVE_SLOT_INDEX, Constants.K_DRIVE_TIMEOUT_MS);

		mLeftMaster.config_kF(Constants.K_DRIVE_SLOT_INDEX, leftKF, Constants.K_DRIVE_TIMEOUT_MS); // 0
		mLeftMaster.config_kP(Constants.K_DRIVE_SLOT_INDEX, leftKP, Constants.K_DRIVE_TIMEOUT_MS); // 0.12
		mLeftMaster.config_kI(Constants.K_DRIVE_SLOT_INDEX, leftKI, Constants.K_DRIVE_TIMEOUT_MS); // 0
		mLeftMaster.config_kD(Constants.K_DRIVE_SLOT_INDEX, leftKD, Constants.K_DRIVE_TIMEOUT_MS); // 0.0012

		mLeftMaster.setSelectedSensorPosition(Constants.DRIVE_POSITION, Constants.K_DRIVE_PID_LOOP_INDEX,
				Constants.K_DRIVE_TIMEOUT_MS);
		mLeftMaster.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);

		/* Right Master Configuration */
		mRightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.K_DRIVE_PID_LOOP_INDEX,
				Constants.K_DRIVE_TIMEOUT_MS);
		mRightMaster.setSensorPhase(false);

		mRightMaster.configNominalOutputForward(0, Constants.K_DRIVE_TIMEOUT_MS);
		mRightMaster.configNominalOutputReverse(0, Constants.K_DRIVE_TIMEOUT_MS);
		mRightMaster.configPeakOutputForward(1, Constants.K_DRIVE_TIMEOUT_MS);
		mRightMaster.configPeakOutputReverse(-1, Constants.K_DRIVE_TIMEOUT_MS);

		mRightMaster.configAllowableClosedloopError(0, Constants.K_DRIVE_SLOT_INDEX, Constants.K_DRIVE_TIMEOUT_MS);

		mRightMaster.config_kF(Constants.K_DRIVE_SLOT_INDEX, rightKF, Constants.K_DRIVE_TIMEOUT_MS); // 0
		mRightMaster.config_kP(Constants.K_DRIVE_SLOT_INDEX, rightKP, Constants.K_DRIVE_TIMEOUT_MS); // 0.08
		mRightMaster.config_kI(Constants.K_DRIVE_SLOT_INDEX, rightKI, Constants.K_DRIVE_TIMEOUT_MS); // 0
		mRightMaster.config_kD(Constants.K_DRIVE_SLOT_INDEX, rightKD, Constants.K_DRIVE_TIMEOUT_MS); // 0

		mRightMaster.setSelectedSensorPosition(Constants.DRIVE_POSITION, Constants.K_DRIVE_PID_LOOP_INDEX,
				Constants.K_DRIVE_TIMEOUT_MS);

		mRightMaster.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
	}

	public void shift(boolean enable) {
		mShift.set(enable);
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

	public void arcadeDrive(double yAxis, double xAxis) {
		mRobotDrive.arcadeDrive(yAxis, xAxis);
	}

	public double getLeftDrivePosition() {
		return mLeftMaster.getSelectedSensorPosition(Constants.K_DRIVE_PID_LOOP_INDEX);
	}

	public double getRightDrivePosition() {
		return mRightMaster.getSelectedSensorPosition(Constants.K_DRIVE_PID_LOOP_INDEX);
	}

	public void setBrakeMode(boolean on) {
		mLeftMaster.setNeutralMode(on ? com.ctre.phoenix.motorcontrol.NeutralMode.Brake 
				: com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
		mRightMaster.setNeutralMode(on ? com.ctre.phoenix.motorcontrol.NeutralMode.Brake 
				: com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
	}

	public double getGyroAngle() {
		return mGyro.getAngle();
	}

	public void setLeftPID(double kF, double kP, double kI, double kD) {
		mLeftMaster.config_kF(Constants.K_DRIVE_SLOT_INDEX, kF, Constants.K_DRIVE_TIMEOUT_MS);
		mLeftMaster.config_kP(Constants.K_DRIVE_SLOT_INDEX, kP, Constants.K_DRIVE_TIMEOUT_MS);
		mLeftMaster.config_kI(Constants.K_DRIVE_SLOT_INDEX, kI, Constants.K_DRIVE_TIMEOUT_MS);
		mLeftMaster.config_kD(Constants.K_DRIVE_SLOT_INDEX, kD, Constants.K_DRIVE_TIMEOUT_MS);
	}

	public void setRightPID(double kF, double kP, double kI, double kD) {
		mRightMaster.config_kF(Constants.K_DRIVE_SLOT_INDEX, kF, Constants.K_DRIVE_TIMEOUT_MS);
		mRightMaster.config_kP(Constants.K_DRIVE_SLOT_INDEX, kP, Constants.K_DRIVE_TIMEOUT_MS);
		mRightMaster.config_kI(Constants.K_DRIVE_SLOT_INDEX, kI, Constants.K_DRIVE_TIMEOUT_MS);
		mRightMaster.config_kD(Constants.K_DRIVE_SLOT_INDEX, kD, Constants.K_DRIVE_TIMEOUT_MS);
	}

	public void zeroEncoders() {
		mLeftMaster.setSelectedSensorPosition(0, Constants.K_DRIVE_PID_LOOP_INDEX, Constants.K_DRIVE_TIMEOUT_MS);
		mRightMaster.setSelectedSensorPosition(0, Constants.K_DRIVE_PID_LOOP_INDEX, Constants.K_DRIVE_TIMEOUT_MS);
	}

	public void resetGyro() {
		mGyro.reset();
	}

	public void outputToSmartDashboard() {
		SmartDashboard.putNumber("Left Master Encoder Counts",
				mLeftMaster.getSelectedSensorPosition(Constants.DRIVE_POSITION));
		SmartDashboard.putNumber("Left Master Voltage", mLeftMaster.getMotorOutputVoltage());
		SmartDashboard.putNumber("Left Master Current", mLeftMaster.getOutputCurrent());
		SmartDashboard.putNumber("Left Master Percent Power", mLeftMaster.getMotorOutputPercent());

		SmartDashboard.putNumber("Right Master Encoder Counts",
				mRightMaster.getSelectedSensorPosition(Constants.DRIVE_POSITION));
		SmartDashboard.putNumber("Right Master Voltage", mRightMaster.getMotorOutputVoltage());
		SmartDashboard.putNumber("Right Master Current", mRightMaster.getOutputCurrent());
		SmartDashboard.putNumber("Right Master Percent Power", mRightMaster.getMotorOutputPercent());

		SmartDashboard.putNumber("Gyro Angle", mGyro.getAngle());

		SmartDashboard.putBoolean("High Gear?", mShift.get());
	}

	public void initDefaultCommand() {
		// TODO: Set the default command, if any, for a subsystem here. Example:
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new JoystickDrive());
	}

}
