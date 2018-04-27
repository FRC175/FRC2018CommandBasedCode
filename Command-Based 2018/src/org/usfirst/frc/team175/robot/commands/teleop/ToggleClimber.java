package org.usfirst.frc.team175.robot.commands.teleop;

import org.usfirst.frc.team175.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * @author Arvind
 */
public class ToggleClimber extends InstantCommand {

	private boolean mAlign;
	
    public ToggleClimber() {
    	super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.climber);
    	requires(Robot.grabber);
    	mAlign = false;
    }

    // Called once when the command executes
    protected void initialize() {
    	if (!mAlign) {
    		Robot.grabber.set(false);
    		Robot.elevator.countsDrive(0);
    		Robot.climber.align((Robot.climber.getWinchSpeed() == 0) ? mAlign : false);
    		mAlign = !mAlign;
    	}
    }

}
