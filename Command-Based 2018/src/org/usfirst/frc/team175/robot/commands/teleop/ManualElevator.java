package org.usfirst.frc.team175.robot.commands.teleop;

import org.usfirst.frc.team175.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Arvind
 */
public class ManualElevator extends Command {

    public ManualElevator() {
    	requires(Robot.elevator);
    }

    protected void initialize() {
    }
    
    protected void execute() {
    	// TODO: Perform further testing with elevator staying at position before un-commenting
    	// Robot.elevator.setPower((Robot.oi.getOperatorStickY() > 0) ? Robot.oi.getOperatorStickY() : (Robot.oi.getOperatorStickY() * 0.6));
		Robot.elevator.setPower(Robot.oi.getOperatorStickY());
    	Robot.elevator.setWantedPosition(Robot.elevator.getPosition());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.elevator.setPosition(Robot.elevator.getWantedPosition());
    }

    protected void interrupted() {
    	Robot.elevator.setPosition(Robot.elevator.getWantedPosition());
    }
}
