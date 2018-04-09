package org.usfirst.frc.team175.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team175.robot.RobotMap;
import org.usfirst.frc.team175.robot.Speeds;

/**
 * TODO: Make and set default command.
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

	// Enum
	public enum GrabberState {
		GRAB, RETRACT, IDLE
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

		// TODO: I am not sure if this should be here or in teleopInit()
		// mGrabberRetract.set(true);
	}

	// TODO: Add custom speed options
	public void grab(GrabberState grabberState) {
		switch (grabberState) {
			case GRAB:
				mGrabRollerL.set(!mGrabberLimit.get() ? Speeds.REVERSE_FAST.getSpeed() : Speeds.IDLE.getSpeed());
				mGrabRollerR.set(!mGrabberLimit.get() ? Speeds.REVERSE_FAST.getSpeed() : Speeds.IDLE.getSpeed());
				break;
			case RETRACT:
				mGrabRollerL.set(Speeds.FORWARD_FAST.getSpeed());
				mGrabRollerR.set(Speeds.FORWARD_FAST.getSpeed());
			case IDLE:
				mGrabRollerL.set(Speeds.IDLE.getSpeed());
				mGrabRollerR.set(Speeds.IDLE.getSpeed());
		}
	}

	public void grabManual(double speed) {
		mGrabRollerL.set(speed);
		mGrabRollerR.set(speed);
	}

	// TODO: Determine whether or not this is necessary
	public boolean isPowerCubeGrabbed() {
		return mGrabberLimit.get();
	}

	public void set(boolean retract) {
		mGrabberRetract.set(retract);
	}

	public void initDefaultCommand() {
		// TODO: Set the default command, if any, for a subsystem here. Example:
		// setDefaultCommand(new MySpecialCommand());
	}

}
