package org.usfirst.frc.team175.robot.commands.teleop;

import org.usfirst.frc.team175.robot.subsystems.Climber;
import org.usfirst.frc.team175.robot.subsystems.Grabber;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * @author Arvind
 */
public class ToggleGrabber extends InstantCommand {

    private boolean mRetract;

    public ToggleGrabber() {
        requires(Grabber.getInstance());
        requires(Climber.getInstance());
    }

    @Override
    protected void initialize() {
        if (!Climber.getInstance().isAligned()) {
            Grabber.getInstance().setPosition(mRetract);
            mRetract = !mRetract;
        }
    }

}