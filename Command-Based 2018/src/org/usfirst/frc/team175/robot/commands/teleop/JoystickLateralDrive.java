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
    	Robot.drive.setPower(0, 0);
    }

    protected void execute() {
    	Robot.lateralDrive.setPower(Robot.oi.getDriverStickX());
    }

    protected boolean isFinished() {
    	return false;
    }

    protected void end() {
    	Robot.lateralDrive.setPower(0);
    	Robot.lateralDrive.set(false);
    }

    protected void interrupted() {
    	end();
    }
}
