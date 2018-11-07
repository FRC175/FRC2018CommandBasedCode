package org.usfirst.frc.team175.robot.commands.teleop;

import org.usfirst.frc.team175.robot.subsystems.Elevator;
import org.usfirst.frc.team175.robot.util.OI;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Arvind
 */
public class ManualElevator extends Command {

    public ManualElevator() {
        requires(Elevator.getInstance());
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        // TODO: Perform further testing with elevator staying at position before un-commenting
        // Elevator.getInstance().setPower((Robot.oi.getOperatorStickY() > 0) ? Robot.oi.getOperatorStickY() : (Robot.oi.getOperatorStickY() * 0.6));
        Elevator.getInstance().setPower(OI.getInstance().getOperatorStickY());
        Elevator.getInstance().setWantedPosition(Elevator.getInstance().getPosition());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Elevator.getInstance().setPosition(Elevator.getInstance().getWantedPosition());
    }

    @Override
    protected void interrupted() {
        end();
    }
    
}
