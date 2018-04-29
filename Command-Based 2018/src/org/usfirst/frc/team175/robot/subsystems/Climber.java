package org.usfirst.frc.team175.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

import org.usfirst.frc.team175.robot.RobotMap;
import org.usfirst.frc.team175.robot.Speeds;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Arvind
 */
public class Climber extends Subsystem {

	/* Declarations */
	// Talon SRs
	private Talon mClimbExtend;
	private Talon mWinch;

	// Double Solenoid
	private DoubleSolenoid mClimbRotate;

	// Solenoid
	private Solenoid mClimbAlign;

	// Limit Switches
	private DigitalInput mClimbUpLimit;
	private DigitalInput mClimbDownLimit;

	// Enums
	public enum ClimberState {
		EXTEND, RETRACT, IDLE
	}

	public enum WinchState {
		WIND_UP, WIND_OUT, IDLE
	}

	public Climber() {
		/* Instantiations */
		// Talon(pwmIO : int)
		mClimbExtend = new Talon(RobotMap.CLIMB_EXTEND_PORT);
		mWinch = new Talon(RobotMap.WINCH_PORT);

		// DoubleSolenoid(canID : int, forwardChannel : int, reverseChannel : int)
		mClimbRotate = new DoubleSolenoid(RobotMap.CLIMB_ROTATE_PORT, RobotMap.CLIMB_ROTATE_FORWARD_CHANNEL,
				RobotMap.CLIMB_ROTATE_REVERSE_CHANNEL);

		// Solenoid(canID : int, channel : int)
		mClimbAlign = new Solenoid(RobotMap.CLIMB_ALIGN_PORT, RobotMap.CLIMB_ALIGN_CHANNEL);

		// DigitalInput(io : int)
		mClimbUpLimit = new DigitalInput(RobotMap.CLIMB_UP_LIMIT_PORT);
		mClimbDownLimit = new DigitalInput(RobotMap.CLIMB_DOWN_LIMIT_PORT);

		// Set climber outwards
		mClimbAlign.set(true);
	}

	public void set(ClimberState climberState) {
		switch (climberState) {
			case EXTEND:
				mClimbExtend.set(Speeds.FORWARD_FAST.getSpeed());
				break;
			case RETRACT:
				mClimbExtend.set(Speeds.REVERSE_FAST.getSpeed());
				break;
			case IDLE:
				mClimbExtend.set(Speeds.IDLE.getSpeed());
				break;
		}
	}

	public void setManual(double speed) {
		mClimbExtend.set(speed);
	}

	public boolean isExtended() {
		return mClimbUpLimit.get();
	}

	public boolean isRetracted() {
		return mClimbDownLimit.get();
	}

	public void winch(WinchState winchState) {
		switch (winchState) {
			case WIND_UP:
				mWinch.set(Speeds.FORWARD_FAST.getSpeed());
				break;
			case WIND_OUT:
				mWinch.set(Speeds.REVERSE_FAST.getSpeed());
				break;
			case IDLE:
				mWinch.set(Speeds.IDLE.getSpeed());
				break;
		}
	}

	public void winchManual(double speed) {
		mWinch.set(speed);
	}

	// TODO: Determine whether this is necessary or not
	public double getWinchSpeed() {
		return mWinch.get();
	}

	public void align(boolean align) {
		mClimbAlign.set(align);
	}

	public boolean isAligned() {
		return mClimbAlign.get();
	}

	public void outputToSmartDashboard() {
		SmartDashboard.putNumber("Climber Percent Power", mClimbExtend.get());
		SmartDashboard.putNumber("Winch Percent Power", mWinch.get());

		SmartDashboard.putBoolean("Climber State", mClimbAlign.get());
		SmartDashboard.putBoolean("Extended all the way?", mClimbUpLimit.get());
		SmartDashboard.putBoolean("Retracted all the way?", mClimbDownLimit.get());
	}

	public void initDefaultCommand() {
		// TODO: Set the default command, if any, for a subsystem here. Example:
		// setDefaultCommand(new MySpecialCommand());
	}

}
