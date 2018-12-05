package org.usfirst.frc.team175.robot.commands.teleop;

import org.usfirst.frc.team175.robot.Robot;
import org.usfirst.frc.team175.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Arvind
 */
public class PositionWinch extends Command {

    private Climber.WinchPosition mPosition;

    public PositionWinch(Climber.WinchPosition position) {
        requires(Climber.getInstance());
        mPosition = position;
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        Climber.getInstance().winch(mPosition);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
        Climber.getInstance().winch(Climber.WinchPosition.IDLE);
    }
    
}
