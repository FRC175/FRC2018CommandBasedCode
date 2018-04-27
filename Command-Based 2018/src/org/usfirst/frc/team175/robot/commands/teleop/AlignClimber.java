package org.usfirst.frc.team175.robot.commands.teleop;

import org.usfirst.frc.team175.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * TODO: Is align function only used once? Because if so, then current code is correct.
 */
public class AlignClimber extends InstantCommand {

	private boolean mAlign;
	
    public AlignClimber() {
    	super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.climber);
    	requires(Robot.grabber);
    	mAlign = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (!mAlign) {
    		Robot.grabber.set(false);
    		Robot.elevator.countsDrive(0);
    		Robot.climber.align((Robot.climber.getWinchSpeed() == 0) ? mAlign : false);
    		mAlign = !mAlign;
    	}
    }

    /*// Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.climber.align((Robot.climber.getWinchSpeed() == 0) ? mAlign : false);
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
    }*/
}
