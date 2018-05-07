package org.usfirst.frc.team175.robot.commands.teleop;

import org.usfirst.frc.team175.robot.Robot;
import org.usfirst.frc.team175.robot.subsystems.Elevator;
import org.usfirst.frc.team175.robot.subsystems.Grabber;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Arvind
 */
public class ManipulateCube extends Command {

	private Grabber.RollerState mRollerState;
	private boolean mCanElevatorBeRaised;
	
    public ManipulateCube(Grabber.RollerState rollerState) {
    	requires(Robot.grabber);
    	requires(Robot.elevator);
    	mRollerState = rollerState;
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.grabber.grab(mRollerState);
    	Robot.grabber.setPowerCubeGrabbedLight(Robot.grabber.isPowerCubeGrabbed());
    	
    	if (!Robot.grabber.isPowerCubeGrabbed()) {
    		mCanElevatorBeRaised = true;
    	}
    	
    	if (Robot.grabber.isPowerCubeGrabbed() && mCanElevatorBeRaised) {
    		mCanElevatorBeRaised = false;
            Robot.elevator.countsDrive(Elevator.ElevatorPositions.POWER_CUBE_LIFT.getHeightInCounts());
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    	Robot.grabber.grab(Grabber.RollerState.IDLE);
    }
}
