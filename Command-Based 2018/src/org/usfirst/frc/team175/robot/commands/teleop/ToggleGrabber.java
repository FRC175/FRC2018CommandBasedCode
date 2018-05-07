package org.usfirst.frc.team175.robot.commands.teleop;

import org.usfirst.frc.team175.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * @author Arvind
 */
public class ToggleGrabber extends InstantCommand {

	private boolean mRetract;
	
    public ToggleGrabber() {
        requires(Robot.grabber);
        mRetract = true;
    }

    protected void initialize() {
    	if (!Robot.climber.isAligned()) {
	    	Robot.grabber.set(mRetract);
	    	mRetract = !mRetract;
    	}
    }

}
