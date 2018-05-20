package org.usfirst.frc.team175.robot.commands.teleop;

import org.usfirst.frc.team175.robot.Robot;
import org.usfirst.frc.team175.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Arvind
 */
public class PositionClimber extends Command {
	
	private Climber.ClimberState mClimberState;
	
    public PositionClimber(Climber.ClimberState climberState) {
    	requires(Robot.climber);
    	mClimberState = climberState;
    }

    protected void initialize() {
    }

    protected void execute() {
    	if (Robot.climber.isRetracted() && mClimberState == Climber.ClimberState.EXTEND)
    		Robot.climber.set(mClimberState);
    	else
    		Robot.climber.set(mClimberState);
    	
    	// Robot.climber.set(mClimberState);
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    	Robot.climber.set(Climber.ClimberState.IDLE);
    }
}
