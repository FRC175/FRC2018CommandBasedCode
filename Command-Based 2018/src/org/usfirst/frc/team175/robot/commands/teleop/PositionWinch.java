package org.usfirst.frc.team175.robot.commands.teleop;

import org.usfirst.frc.team175.robot.Robot;
import org.usfirst.frc.team175.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Arvind
 */
public class PositionWinch extends Command {

	private Climber.WinchState mWinchState;
	
    public PositionWinch(Climber.WinchState winchState) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.climber);
    	mWinchState = winchState;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.climber.winch(mWinchState);
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
    	Robot.climber.winch(Climber.WinchState.IDLE);
    }
}
