package org.usfirst.frc.team175.robot.commands.teleop;

import org.usfirst.frc.team175.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class JoystickLateralDrive extends Command {

    public JoystickLateralDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.lateralDrive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.lateralDrive.set(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.lateralDrive.powerDrive(Robot.oi.getDriverStickX());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return !Robot.lateralDrive.isEnabled();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.lateralDrive.powerDrive(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
