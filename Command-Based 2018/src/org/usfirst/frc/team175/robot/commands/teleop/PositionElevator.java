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
    	requires(Robot.elevator);
    	requires(Robot.climber);
    	mElevatorPosition = elevatorPosition;
    }

    protected void initialize() {
    }

    protected void execute() {
    	/*if (!Robot.climber.isAligned())
    		Robot.elevator.setPosition(mElevatorPosition.getHeightInCounts());*/
    	Robot.elevator.setWantedPosition(mElevatorPosition.getHeightInCounts());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
