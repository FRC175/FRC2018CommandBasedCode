package org.usfirst.frc.team175.robot.commands.teleop;

import org.usfirst.frc.team175.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * @author Arvind
 */
public class ToggleGrabber extends InstantCommand {

	private boolean mRetract;
	
    public ToggleGrabber() {
        super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.grabber);
        mRetract = true;
    }

    // Called once when the command executes
    protected void initialize() {
    	if (!Robot.climber.isAligned()) {
	    	Robot.grabber.set(mRetract);
	    	mRetract = !mRetract;
    	}
    }

}
