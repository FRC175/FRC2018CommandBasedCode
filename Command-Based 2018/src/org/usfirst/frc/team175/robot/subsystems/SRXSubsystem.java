package org.usfirst.frc.team175.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team175.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class SRXSubsystem extends Subsystem {
	
	// Talon SRXs
    // TalonSRX(int CAN id)
	private TalonSRX mSRX;
	
	// Constants
	private final int K_PID_LOOP_INDEX;
	private final int K_TIMEOUT_MS;
	private final int CLOSED_LOOP_TYPE;
	
	public SRXSubsystem(int srxPort, int closedLoopType, int kPIDLoopIndex, int kTimeoutMs, double kF, double kP, double kI, double kD) {
		mSRX = new TalonSRX(srxPort);
		
		CLOSED_LOOP_TYPE = closedLoopType;
		K_PID_LOOP_INDEX = kPIDLoopIndex;
		K_TIMEOUT_MS = kTimeoutMs;
		
		/* SRX Configuration */
		mSRX.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, K_PID_LOOP_INDEX, K_TIMEOUT_MS);
        mSRX.setSensorPhase(true);

        mSRX.configNominalOutputForward(0, K_TIMEOUT_MS);
        mSRX.configNominalOutputReverse(0, K_TIMEOUT_MS);
        mSRX.configPeakOutputForward(1, K_TIMEOUT_MS);
        mSRX.configPeakOutputReverse(-1, K_TIMEOUT_MS);

        mSRX.configAllowableClosedloopError(0, K_PID_LOOP_INDEX, K_TIMEOUT_MS);

        mSRX.config_kF(K_PID_LOOP_INDEX, kF, K_TIMEOUT_MS); // 0
        mSRX.config_kP(K_PID_LOOP_INDEX, kP, K_TIMEOUT_MS); // 10
        mSRX.config_kI(K_PID_LOOP_INDEX, kI, K_TIMEOUT_MS); // 0
        mSRX.config_kD(K_PID_LOOP_INDEX, kD, K_TIMEOUT_MS); // 2

        mSRX.setSelectedSensorPosition(CLOSED_LOOP_TYPE, K_PID_LOOP_INDEX, K_TIMEOUT_MS);

        mSRX.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
	}
	
	public double getPosition() {
    	return mSRX.getSelectedSensorPosition(CLOSED_LOOP_TYPE);
    }
    
    public void powerDrive(double power) {
    	mSRX.set(ControlMode.PercentOutput, power);
    }
    
    public void countsDrive(double counts) {
    	mSRX.set(ControlMode.Position, counts);
    }
    
    public void setPID(double kF, double kP, double kI, double kD) {
    	mSRX.config_kF(K_PID_LOOP_INDEX, kF, K_TIMEOUT_MS);
    	mSRX.config_kP(K_PID_LOOP_INDEX, kP, K_TIMEOUT_MS);
    	mSRX.config_kI(K_PID_LOOP_INDEX, kI, K_TIMEOUT_MS);
    	mSRX.config_kD(K_PID_LOOP_INDEX, kD, K_TIMEOUT_MS);
    }
    
    public void zeroEncoder() {
    	mSRX.setSelectedSensorPosition(0, K_PID_LOOP_INDEX, K_TIMEOUT_MS);
    }
	
	@Override
	protected void initDefaultCommand() {}
	
}
