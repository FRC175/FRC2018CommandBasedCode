package org.usfirst.frc.team175.robot.commands.auto;

import org.usfirst.frc.team175.robot.Constants;
import org.usfirst.frc.team175.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Arvind
 */
public class DriveToPosition extends Command {

	private double mValue; // Inches or Encoder Counts
	
    public DriveToPosition(double value) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	mValue = -value; // Make straight negative and reverse positive
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.zeroEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drive.powerDrive(0.75, -0.75); // Replace with Speed Enum
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (mValue > 0) {
	        return Robot.drive.getLeftDrivePosition() <= mValue && Robot.drive.getRightDrivePosition() <= mValue; // Straight
    	} else {
    		return Robot.drive.getLeftDrivePosition() >= mValue && Robot.drive.getRightDrivePosition() >= mValue; // Reverse
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.zeroEncoders();
    	Robot.drive.powerDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
