package org.usfirst.frc.team175.robot.commands.teleop;

import org.usfirst.frc.team175.robot.subsystems.Climber;
import org.usfirst.frc.team175.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Arvind
 */
public class PositionElevator extends Command {

    private Elevator.ElevatorPosition mPosition;

    public PositionElevator(Elevator.ElevatorPosition position) {
        requires(Elevator.getInstance());
        requires(Climber.getInstance());
        mPosition = position;
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        if (!Climber.getInstance().isAligned())
            Elevator.getInstance().setPosition(mPosition);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        // Doesn't do anything
        Elevator.getInstance().setWantedPosition(mPosition);
    }

    @Override
    protected void interrupted() {
        end();
    }
}
