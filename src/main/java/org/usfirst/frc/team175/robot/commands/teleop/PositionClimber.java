package org.usfirst.frc.team175.robot.commands.teleop;

import org.usfirst.frc.team175.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Arvind
 */
public class PositionClimber extends Command {

    private Climber.ClimberPosition mPosition;

    public PositionClimber(Climber.ClimberPosition position) {
        requires(Climber.getInstance());
        mPosition = position;
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        /*if (Climber.getInstance().isRetracted() && mClimberState == Climber.State.EXTEND)
            Climber.getInstance().set(mClimberState);
        else
            Climber.getInstance().set(mClimberState);*/
        Climber.getInstance().setPosition(mPosition);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Climber.getInstance().setPosition(Climber.ClimberPosition.IDLE);
    }

    @Override
    protected void interrupted() {
        end();
    }
}
