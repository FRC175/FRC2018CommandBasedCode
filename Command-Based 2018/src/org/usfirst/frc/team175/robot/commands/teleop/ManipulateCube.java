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
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.grabber);
    	requires(Robot.elevator);
    	mRollerState = rollerState;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
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

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.grabber.grab(Grabber.RollerState.IDLE);
    }
}
