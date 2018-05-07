package org.usfirst.frc.team175.robot.commands.teleop;

import org.usfirst.frc.team175.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Arvind
 */
public class JoystickLateralDrive extends Command {

    public JoystickLateralDrive() {
    	requires(Robot.lateralDrive);
    }

    protected void initialize() {
    	Robot.lateralDrive.set(true);
    }

    protected void execute() {
    	Robot.lateralDrive.powerDrive(Robot.oi.getDriverStickX());
    }

    protected boolean isFinished() {
    	// return !Robot.lateralDrive.isEnabled();
    	return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    	Robot.lateralDrive.powerDrive(0);
    	Robot.lateralDrive.set(false);
    }
}
