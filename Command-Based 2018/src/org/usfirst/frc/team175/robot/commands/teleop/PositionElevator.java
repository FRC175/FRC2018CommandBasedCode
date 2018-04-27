package org.usfirst.frc.team175.robot.commands.teleop;

import org.usfirst.frc.team175.robot.Robot;
import org.usfirst.frc.team175.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Arvind
 */
public class PositionElevator extends Command {

	Elevator.ElevatorPositions mElevatorPosition;
	
    public PositionElevator(Elevator.ElevatorPositions elevatorPosition) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevator);
    	requires(Robot.climber);
    	mElevatorPosition = elevatorPosition;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!Robot.climber.isAligned()) {
    		Robot.elevator.countsDrive(mElevatorPosition.getHeightInCounts());
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
