package org.usfirst.frc.team175.robot.commands.teleop;

import org.usfirst.frc.team175.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Arvind
 */
public class PositionClimber extends Command {

    private Climber.ClimberPosition mState;

    public PositionClimber(Climber.ClimberPosition climberState) {
        requires(Climber.getInstance());
        mState = climberState;
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
        Climber.getInstance().setPosition(mState);
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
