package org.usfirst.frc.team175.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team175.robot.RobotMap;
import org.usfirst.frc.team175.robot.Constants;

/**
 * TODO: Make and set default command.
 */
public class LateralDrive extends SRXSubsystem {

	private TalonSRX mLateralDrive;

	// Solenoids
	// Solenoid(int CAN id of PCM, int channel)
	private Solenoid mDeploy;

	public LateralDrive(double kF, double kP, double kI, double kD) {
		super(RobotMap.LATERAL_DRIVE_PORT, Constants.LATERAL_DRIVE_CLOSED_LOOP_TYPE,
				Constants.K_LATERAL_DRIVE_PID_LOOP_INDEX, Constants.K_LATERAL_DRIVE_TIMEOUT_MS, kF, kP, kI, kD);
		mDeploy = new Solenoid(RobotMap.LATERAL_DEPLOY_PORT, RobotMap.LATERAL_DEPLOY_CHANNEL);
	}

	public void set(boolean value) {
		mDeploy.set(value);
	}

	public boolean isEnabled() {
		return mDeploy.get();
	}

	@Override
	public void initDefaultCommand() {
		// TODO: Set the default command, if any, for a subsystem here. Example:
		// setDefaultCommand(new MySpecialCommand());
	}
}
