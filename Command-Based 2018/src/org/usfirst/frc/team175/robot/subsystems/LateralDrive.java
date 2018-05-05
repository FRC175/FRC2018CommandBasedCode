package org.usfirst.frc.team175.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team175.robot.RobotMap;
import org.usfirst.frc.team175.util.SRXSubsystem;
import org.usfirst.frc.team175.robot.Constants;

/**
 * @author Arvind
 * @see SRXSubsystem.java
 */
public class LateralDrive extends SRXSubsystem {

	/* Declarations */
	// Talon SRX
	private TalonSRX mLateralDrive;

	// Solenoid
	private Solenoid mDeploy;

	public LateralDrive(double kF, double kP, double kI, double kD) {
		/* Construct SRXSubsystem */
		super("Lateral Drive", RobotMap.LATERAL_DRIVE_PORT, Constants.LATERAL_DRIVE_POSITION,
				Constants.K_LATERAL_DRIVE_SLOT_INDEX, Constants.K_LATERAL_DRIVE_PID_LOOP_INDEX,
				Constants.K_LATERAL_DRIVE_TIMEOUT_MS, kF, kP, kI, kD, false);

		/* Instantiations */
		// Solenoid(canID : int, channel : int)
		mDeploy = new Solenoid(RobotMap.LATERAL_DEPLOY_PORT, RobotMap.LATERAL_DEPLOY_CHANNEL);
	}

	public void set(boolean enable) {
		mDeploy.set(enable);
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
