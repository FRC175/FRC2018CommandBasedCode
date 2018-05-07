package org.usfirst.frc.team175.robot.commands.teleop;

import org.usfirst.frc.team175.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Arvind
 */
public class Shift extends Command {

	public Shift() {
		requires(Robot.drive);
    }
	
    protected void initialize() {
    }

    protected void execute() {
    	Robot.drive.shift(true); // True = High Speed; False = Low Speed
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    	Robot.drive.shift(false);
    }

}
