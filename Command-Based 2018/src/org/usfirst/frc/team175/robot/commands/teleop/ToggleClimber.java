package org.usfirst.frc.team175.robot.commands.teleop;

import org.usfirst.frc.team175.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * @author Arvind
 */
public class ToggleClimber extends InstantCommand {

	private boolean mAlign;
	
    public ToggleClimber() {
    	requires(Robot.climber);
    	requires(Robot.grabber);
    	mAlign = true;
    }

    // Called once when the command executes
    protected void initialize() {
    	if (!mAlign) {
    		Robot.grabber.set(false);
    		Robot.elevator.setPosition(0);
    	}
    	
    	Robot.climber.align(mAlign);
    	
		mAlign = !mAlign;
    }

}
