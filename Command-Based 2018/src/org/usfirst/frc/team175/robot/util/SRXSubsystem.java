package org.usfirst.frc.team175.robot.util;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team175.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * An object that allows for the creation of a subsystem that has implementation
 * for TalonSRX specific features.
 * 
 * @author Arvind
 */
public abstract class SRXSubsystem extends Subsystem {

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
	 *            Default position to set SRX to.
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
	 * @param inverted
	 *            Whether or not encoder counts are inverted (if forward is negative
	 *            and reverse is positive)
	 * @param sensorPhase
	 *            Whether to invert the phase of the sensor
	 * 
	 */
	public SRXSubsystem(String subsystemName, int srxPort, int sensorPosition, int kSlotIndex, int kPIDLoopIndex,
			int kTimeoutMs, double kF, double kP, double kI, double kD, boolean inverted, boolean sensorPhase) {
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
		mSRX.setInverted(inverted);
		mSRX.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, K_PID_LOOP_INDEX, K_TIMEOUT_MS);
		mSRX.setSensorPhase(sensorPhase);

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

		mSRX.setNeutralMode(NeutralMode.Brake);
	}

	/**
	 * Gives the current position on the SRX (in encoder counts).
	 * 
	 * @return The current encoder count on the SRX
	 */
	public double getPosition() {
		return mSRX.getSelectedSensorPosition(K_PID_LOOP_INDEX);
	}

	/**
	 * Sets the power of the motor to a certain percent.
	 * 
	 * @param power
	 *            The motor power
	 */
	public void setPower(double power) {
		mSRX.set(ControlMode.PercentOutput, power);
	}

	/**
	 * Makes the motor move to a certain position using SRX encoder counts.
	 * 
	 * @param counts
	 *            Position for the SRX to reach
	 */
	public void setPosition(double counts) {
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
	 * Enables or disables brake mode.
	 * 
	 * @param on
	 *            Whether or not to enable brake mode
	 */
	public void setBrakeMode(boolean on) {
		mSRX.setNeutralMode(on ? NeutralMode.Brake : NeutralMode.Coast);
	}

	/**
	 * Configures a way to limit motor current output.
	 * 
	 * @param current
	 *            Default amperes
	 * @param currentLimit
	 *            Amperes to limit
	 * @param currentDuration
	 *            How long you can pass the limit before pushed to default (in ms)
	 * @param enable
	 *            State of current limit
	 */
	public void configCurrentLimiting(int current, int currentLimit, int currentDuration, boolean enable) {
		mSRX.configContinuousCurrentLimit(current, 0);
		mSRX.configPeakCurrentLimit(currentLimit, 0);
		mSRX.configPeakCurrentDuration(currentDuration, 0);
		mSRX.enableCurrentLimit(enable);
	}

	/**
	 * Returns SRX object for child class use.
	 * 
	 * @return The Talon SRX
	 */
	protected TalonSRX getSRX() {
		return mSRX;
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

	/**
	 * Initialize the default command.
	 */
	protected abstract void initDefaultCommand();

}
