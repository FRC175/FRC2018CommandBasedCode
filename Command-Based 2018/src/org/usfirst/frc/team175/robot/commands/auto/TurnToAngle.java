package org.usfirst.frc.team175.robot.commands.auto;

import org.usfirst.frc.team175.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Arvind
 */
public class TurnToAngle extends Command {
	
	private double mGoal;
	private double mDeadband;
	private double mPower;

    public TurnToAngle(double goal, double deadband, double power) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	mGoal = goal;
    	mDeadband = deadband;
    	mPower = -power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.drive.getGyroAngle() > mGoal) {
    		Robot.drive.powerDrive(mPower, mPower);
    	} else {
    		Robot.drive.powerDrive(-mPower, -mPower);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.drive.getGyroAngle() >= (mGoal - mDeadband) && Robot.drive.getGyroAngle() < (mGoal + mDeadband);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.powerDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
}
