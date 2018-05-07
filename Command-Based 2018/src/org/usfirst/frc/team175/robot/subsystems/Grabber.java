package org.usfirst.frc.team175.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team175.robot.Constants;
import org.usfirst.frc.team175.robot.RobotMap;
import org.usfirst.frc.team175.robot.Speeds;

/**
 * @author Arvind
 */
public class Grabber extends Subsystem {

	/* Declarations */
	// Talon SRs
	private Talon mGrabRollerL;
	private Talon mGrabRollerR;

	// Solenoid
	private Solenoid mGrabberRetract;

	// Limit Switch
	private DigitalInput mGrabberLimit;

	// Relay
	private Relay mPowerCubeGrabbedLight;

	// Enum
	public enum RollerState {
		GRAB, RETRACT_FAST, RETRACT_SLOW, IDLE
	}

	public Grabber() {
		/* Instantiations */
		// Talon(pwmIO : int)
		mGrabRollerL = new Talon(RobotMap.GRAB_ROLLER_L_PORT);
		mGrabRollerR = new Talon(RobotMap.GRAB_ROLLER_R_PORT);

		// Solenoid(canID : int, channel : int)
		mGrabberRetract = new Solenoid(RobotMap.GRABBER_RETRACT_PORT, RobotMap.GRABBER_RETRACT_CHANNEL);

		// DigitalInput(io : int)
		mGrabberLimit = new DigitalInput(RobotMap.GRABBER_LIMIT_PORT);

		// Relay(io : int)
		mPowerCubeGrabbedLight = new Relay(RobotMap.POWER_CUBE_GRABBED_LIGHT_PORT);
	}

	public void grab(RollerState rollerState) {
		switch (rollerState) {
			case GRAB:
				mGrabRollerL.set(!mGrabberLimit.get() ? Speeds.REVERSE_FAST.getSpeed() : Speeds.IDLE.getSpeed());
				mGrabRollerR.set(!mGrabberLimit.get() ? Speeds.REVERSE_FAST.getSpeed() : Speeds.IDLE.getSpeed());
				break;
			case RETRACT_FAST:
				mGrabRollerL.set(Speeds.FORWARD_FAST.getSpeed());
				mGrabRollerR.set(Speeds.FORWARD_FAST.getSpeed());
				break;
			case RETRACT_SLOW:
				mGrabRollerL.set(Speeds.FORWARD_LOW.getSpeed());
				mGrabRollerR.set(Speeds.FORWARD_LOW.getSpeed());
			case IDLE:
				// Default grabber bias
				mGrabRollerL.set(-0.15);
				mGrabRollerR.set(-0.15);
				break;
		}
	}

	// TODO: Determine if this necessary
	public void grab(double speed) {
		mGrabRollerL.set(speed);
		mGrabRollerR.set(speed);
	}

	public boolean isPowerCubeGrabbed() {
		return mGrabberLimit.get();
	}

	public void set(boolean retract) {
		mGrabberRetract.set(retract);
	}

	public void setPowerCubeGrabbedLight(boolean on) {
		mPowerCubeGrabbedLight.set(on ? Relay.Value.kForward : Relay.Value.kOff);
	}

	public void outputToSmartDashboard() {
		SmartDashboard.putNumber("Left Roller Percent Power", mGrabRollerL.get());
		SmartDashboard.putNumber("Right Roller Percent Power", mGrabRollerL.get());

		SmartDashboard.putBoolean("Grabber State", mGrabberRetract.get());
		SmartDashboard.putBoolean("Cube?", mGrabberLimit.get());
	}

	public void initDefaultCommand() {
		// TODO: Set the default command, if any, for a subsystem here. Example:
		// setDefaultCommand(new MySpecialCommand());
	}

}
