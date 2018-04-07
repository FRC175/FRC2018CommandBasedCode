package org.usfirst.frc.team175.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

import org.usfirst.frc.team175.robot.RobotMap;
import org.usfirst.frc.team175.robot.Speeds;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * TODO: Consider making a Talon speed enum (global).
 */
public class Climber extends Subsystem {

	private Talon mClimbExtend;
	private Talon mWinch;
	private DoubleSolenoid mClimbRotate;
	private Solenoid mClimbAlign;
	private DigitalInput mClimbUpLimit;
	private DigitalInput mClimbDownLimit;
	
	public enum ClimberState {
		EXTEND, RETRACT, IDLE
	}
	
	public enum WinchState {
		WIND_UP, WIND_OUT, IDLE
	}
	
	public Climber() {
		mClimbExtend = new Talon(RobotMap.CLIMB_EXTEND_PORT);
		mWinch = new Talon(RobotMap.WINCH_PORT);
		mClimbRotate = new DoubleSolenoid(RobotMap.CLIMB_ROTATE_PORT, RobotMap.CLIMB_ROTATE_FORWARD_CHANNEL, RobotMap.CLIMB_ROTATE_REVERSE_CHANNEL);
		mClimbAlign = new Solenoid(RobotMap.CLIMB_ALIGN_PORT, RobotMap.CLIMB_ALIGN_CHANNEL);
		mClimbUpLimit = new DigitalInput(RobotMap.CLIMB_UP_LIMIT_PORT);
		mClimbDownLimit = new DigitalInput(RobotMap.CLIMB_DOWN_LIMIT_PORT);
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
	
	public void setManual(Speeds speed) {
		mClimbExtend.set(speed.getSpeed());
	}
	
	public boolean isExtended() {
		return mClimbUpLimit.get();
	}
	
	public boolean isRetracted() {
		return mClimbDownLimit.get();
	}
	
	private void winch(WinchState winchState) {
		switch (winchState) {
			case WIND_UP:
				mWinch.set(Speeds.FORWARD_FAST.getSpeed());
				break;
			case WIND_OUT:
				mWinch.set(Speeds.REVERSE_FAST.getSpeed());
				break;
			case IDLE:
				mWinch.set(Speeds.IDLE.getSpeed());
		}
	}
	
	public void winch(WinchState winchState, double power) {
		if (winchState == WinchState.WIND_UP) {
			mClimbExtend.set(power);
		} else {
			winch(winchState);
		}
	}
	
	public void winchManual(double speed) {
		mWinch.set(speed);
	}
	
	public void winchManual(Speeds speed) {
		mWinch.set(speed.getSpeed());
	}
	
	public double getWinchSpeed() {
		return mWinch.get();
	}
	
	public void rotate(boolean rotate) {
		mClimbRotate.set(rotate ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
	}
	
	public void align() {
		mClimbAlign.set(mWinch.get() == 0);
	}
	
    public void initDefaultCommand() {
        // TODO: Set the default command, if any, for a subsystem here. Example:
        //    setDefaultCommand(new MySpecialCommand());
    }

}
