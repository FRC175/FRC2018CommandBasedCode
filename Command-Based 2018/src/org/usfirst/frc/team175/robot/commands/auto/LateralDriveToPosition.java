package org.usfirst.frc.team175.robot.commands.auto;

import org.usfirst.frc.team175.robot.Constants;
import org.usfirst.frc.team175.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Arvind
 */
public class LateralDriveToPosition extends Command {

	private double mValue;
	
    public LateralDriveToPosition(double value) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.lateralDrive);
    	mValue = value;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.lateralDrive.set(true);
    	Robot.lateralDrive.zeroEncoder();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.lateralDrive.powerDrive(0.75); // Replace with Speed Enum
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (mValue > 0) {
    		return Robot.lateralDrive.getPosition() >= mValue; // Left
    	} else {
    		return Robot.lateralDrive.getPosition() <= mValue; // Right
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.lateralDrive.zeroEncoder();
    	Robot.lateralDrive.powerDrive(0);
    	Robot.lateralDrive.set(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
