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
    	requires(Robot.climber);
    	mWinchState = winchState;
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.climber.winch(mWinchState);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    	Robot.climber.winch(Climber.WinchState.IDLE);
    }
}
