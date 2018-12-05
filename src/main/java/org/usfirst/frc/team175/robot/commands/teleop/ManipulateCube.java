package org.usfirst.frc.team175.robot.commands.teleop;

import org.usfirst.frc.team175.robot.subsystems.Elevator;
import org.usfirst.frc.team175.robot.subsystems.Grabber;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Arvind
 */
public class ManipulateCube extends Command {

    private Grabber.RollerPosition mPosition;
    private boolean mCanElevatorBeRaised;

    public ManipulateCube(Grabber.RollerPosition position) {
        requires(Elevator.getInstance());
        requires(Grabber.getInstance());
        mPosition = position;
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        Grabber.getInstance().grab(mPosition);
        Grabber.getInstance().setLight(Grabber.getInstance().isPowerCubeGrabbed());

        if (!Grabber.getInstance().isPowerCubeGrabbed())
            mCanElevatorBeRaised = true;

        // TODO: Fix elevator counts before un-commenting this
        /*if (Robot.grabber.isPowerCubeGrabbed() && mCanElevatorBeRaised) {
            mCanElevatorBeRaised = false;
            Robot.elevator.countsDrive(Elevator.ElevatorPositions.POWER_CUBE_LIFT.getHeightInCounts());
        }*/
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Grabber.getInstance().grab(Grabber.RollerPosition.IDLE);
    }

    @Override
    protected void interrupted() {
        end();
    }
    
}
