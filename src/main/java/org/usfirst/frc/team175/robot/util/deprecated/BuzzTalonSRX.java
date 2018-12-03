package org.usfirst.frc.team175.robot.util.deprecated;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * This wrapper over the Talon SRX motor controller just simplifies some commonly
 * used methods.
 * 
 * @author Arvind
 */
@Deprecated
public class BuzzTalonSRX extends WPI_TalonSRX {

	private int mSlotIdx;
	private int mPIDIdx;
	private int mTimeoutMs;

	public BuzzTalonSRX(int devicePort) {
		super(devicePort);

		mSlotIdx = 0;
		mPIDIdx = 0;
		mTimeoutMs = 10;

		super.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, mPIDIdx, mTimeoutMs);
		super.configNominalOutputForward(0, mTimeoutMs);
		super.configNominalOutputReverse(0, mTimeoutMs);
		super.configPeakOutputForward(1, mTimeoutMs);
		super.configPeakOutputReverse(-1, mTimeoutMs);
		super.configAllowableClosedloopError(0, mSlotIdx, mTimeoutMs);

		super.setSelectedSensorPosition(0, mPIDIdx, mTimeoutMs);
		super.setNeutralMode(NeutralMode.Brake);
	}

	public ErrorCode config_kF(double value) {
		return super.config_kF(mSlotIdx, value, mTimeoutMs);
	}

	public ErrorCode config_kP(double value) {
		return super.config_kP(mSlotIdx, value, mTimeoutMs);
	}

	public ErrorCode config_kI(double value) {
		return super.config_kI(mSlotIdx, value, mTimeoutMs);
	}

	public ErrorCode config_kD(double value) {
		return super.config_kD(mSlotIdx, value, mTimeoutMs);
	}

	public ErrorCode setSelectedSensorPosition(int pos) {
		return super.setSelectedSensorPosition(pos, mPIDIdx, mTimeoutMs);
	}

	public int getSelectedSensorPosition() {
		return super.getSelectedSensorPosition(mPIDIdx);
	}
	
	public void setNeutralMode(boolean on) {
		super.setNeutralMode(on ? NeutralMode.Coast : NeutralMode.Brake);
	}

	public void setSlotIdx(int slotIdx) {
		mSlotIdx = slotIdx;
	}

	public void setPIDIdx(int pidIdx) {
		mPIDIdx = pidIdx;
	}

	public void setTimeoutMs(int timeoutMs) {
		mTimeoutMs = timeoutMs;
	}

}
