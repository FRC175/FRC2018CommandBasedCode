package org.usfirst.frc.team175.robot.commands.auto;

import org.usfirst.frc.team175.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Arvind
 */
public class ElevatorToPosition extends Command {

	private double mValue;
	
    public ElevatorToPosition(double value) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevator);
    	mValue = value;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevator.setPosition(mValue);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.elevator.getPosition() <= (mValue + 10) && Robot.elevator.getPosition() >= (mValue - 10);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
