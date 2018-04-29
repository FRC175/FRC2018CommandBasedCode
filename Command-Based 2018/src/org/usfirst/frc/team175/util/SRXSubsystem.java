package org.usfirst.frc.team175.util;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team175.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * An object that allows for the creation of a subsystem that has implementation
 * for TalonSRX specific features.
 * 
 * @author Arvind
 */
public class SRXSubsystem extends Subsystem {

	// Talon SRXs
	private TalonSRX mSRX;

	// Constants
	private final int K_SLOT_INDEX;
	private final int K_PID_LOOP_INDEX;
	private final int K_TIMEOUT_MS;
	private final int SENSOR_POSITION;
	private final String SUBSYSTEM_NAME;

	/**
	 * Constructs an subsystem that utilizes a TalonSRX Motor Controller.
	 * 
	 * @param subsystemName
	 *            The name of the subsystem
	 * @param srxPort
	 *            The port that the SRX is plugged into
	 * @param sensorPosition
	 * 
	 * @param kSlotIndex
	 *            The slot constant
	 * @param kPIDLoopIndex
	 *            The closed loop type on the SRX (primary, 0, or auxiliary, 1)
	 * @param kTimeoutMs
	 *            Timeout value in ms.
	 * @param kF
	 *            Feed forward coefficient
	 * @param kP
	 *            Proportional coefficient
	 * @param kI
	 *            Integral coefficient
	 * @param kD
	 *            Derivative coefficient
	 */
	public SRXSubsystem(String subsystemName, int srxPort, int sensorPosition, int kSlotIndex, int kPIDLoopIndex,
			int kTimeoutMs, double kF, double kP, double kI, double kD) {
		/* Instantiations */
		// TalonSRX(canID : int)
		mSRX = new TalonSRX(srxPort);

		/* Definitions */
		SENSOR_POSITION = sensorPosition;
		K_SLOT_INDEX = kSlotIndex;
		K_PID_LOOP_INDEX = kPIDLoopIndex;
		K_TIMEOUT_MS = kTimeoutMs;
		SUBSYSTEM_NAME = subsystemName;

		/* SRX Configuration */
		mSRX.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, K_PID_LOOP_INDEX, K_TIMEOUT_MS);
		mSRX.setSensorPhase(true);

		mSRX.configNominalOutputForward(0, K_TIMEOUT_MS);
		mSRX.configNominalOutputReverse(0, K_TIMEOUT_MS);
		mSRX.configPeakOutputForward(1, K_TIMEOUT_MS);
		mSRX.configPeakOutputReverse(-1, K_TIMEOUT_MS);

		mSRX.configAllowableClosedloopError(0, K_SLOT_INDEX, K_TIMEOUT_MS);

		mSRX.config_kF(K_SLOT_INDEX, kF, K_TIMEOUT_MS);
		mSRX.config_kP(K_SLOT_INDEX, kP, K_TIMEOUT_MS);
		mSRX.config_kI(K_SLOT_INDEX, kI, K_TIMEOUT_MS);
		mSRX.config_kD(K_SLOT_INDEX, kD, K_TIMEOUT_MS);

		mSRX.setSelectedSensorPosition(SENSOR_POSITION, K_PID_LOOP_INDEX, K_TIMEOUT_MS);

		mSRX.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
	}

	/**
	 * Gives the current position on the SRX (in encoder counts).
	 * 
	 * @return The current encoder count on the SRX
	 */
	public double getPosition() {
		return mSRX.getSelectedSensorPosition(SENSOR_POSITION);
	}

	/**
	 * Sets the power of the motor to a certain percent.
	 * 
	 * @param power
	 *            The motor power
	 */
	public void powerDrive(double power) {
		mSRX.set(ControlMode.PercentOutput, power);
	}

	/**
	 * Makes the motor move to a certain position using SRX encoder counts.
	 * 
	 * @param counts
	 *            Position for the SRX to reach
	 */
	public void countsDrive(double counts) {
		mSRX.set(ControlMode.Position, counts);
	}

	/**
	 * Changes the PID values on the SRX.
	 * 
	 * @param kF
	 *            Feed forward coefficient
	 * @param kP
	 *            Proportional coefficient
	 * @param kI
	 *            Integral coefficient
	 * @param kD
	 *            Derivative coefficient
	 */
	public void setPID(double kF, double kP, double kI, double kD) {
		mSRX.config_kF(K_SLOT_INDEX, kF, K_TIMEOUT_MS);
		mSRX.config_kP(K_SLOT_INDEX, kP, K_TIMEOUT_MS);
		mSRX.config_kI(K_SLOT_INDEX, kI, K_TIMEOUT_MS);
		mSRX.config_kD(K_SLOT_INDEX, kD, K_TIMEOUT_MS);
	}

	/**
	 * Resets the encoder on the SRX to 0 counts.
	 */
	public void zeroEncoder() {
		mSRX.setSelectedSensorPosition(0, K_PID_LOOP_INDEX, K_TIMEOUT_MS);
	}

	/**
	 * Outputs SRX encoder counts and motor voltage, current, and power (in percent)
	 * to the SmartDashboard.
	 */
	public void outputToSmartDashboard() {
		SmartDashboard.putNumber(SUBSYSTEM_NAME + " Encoder Counts", mSRX.getSelectedSensorPosition(SENSOR_POSITION));
		SmartDashboard.putNumber(SUBSYSTEM_NAME + " Voltage", mSRX.getMotorOutputVoltage());
		SmartDashboard.putNumber(SUBSYSTEM_NAME + " Current", mSRX.getOutputCurrent());
		SmartDashboard.putNumber(SUBSYSTEM_NAME + " Percent Power", mSRX.getMotorOutputPercent());
	}

	@Override
	protected void initDefaultCommand() {
	}

}
