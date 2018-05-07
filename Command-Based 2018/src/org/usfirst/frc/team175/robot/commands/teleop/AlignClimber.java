package org.usfirst.frc.team175.robot.commands.teleop;

import org.usfirst.frc.team175.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * @author Arvind
 */

@Deprecated
public class AlignClimber extends InstantCommand {

	private boolean mAlign;
	
    public AlignClimber() {
    	requires(Robot.climber);
    	requires(Robot.grabber);
    	mAlign = false;
    }

    protected void initialize() {
    	if (!mAlign) {
    		Robot.grabber.set(false);
    		Robot.elevator.countsDrive(0);
    		Robot.climber.align((Robot.climber.getWinchSpeed() == 0) ? mAlign : false);
    		mAlign = !mAlign;
    	}
    }

    protected void execute() {
    	Robot.climber.align((Robot.climber.getWinchSpeed() == 0) ? mAlign : false);
    }

    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    protected void interrupted() {
    }
}
