package org.usfirst.frc.team175.robot.commands.teleop;

import org.usfirst.frc.team175.robot.subsystems.Climber;
import org.usfirst.frc.team175.robot.subsystems.Elevator;
import org.usfirst.frc.team175.robot.subsystems.Grabber;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * @author Arvind
 */
public class ToggleClimber extends InstantCommand {

    private boolean mAlign;

    public ToggleClimber() {
        requires(Climber.getInstance());
        requires(Grabber.getInstance());
        requires(Elevator.getInstance());
        mAlign = true;
    }

    @Override
    protected void initialize() {
        if (!mAlign) {
            Grabber.getInstance().setPosition(false);
            Elevator.getInstance().setPosition(0);
        }

        Climber.getInstance().align(mAlign);

        mAlign = !mAlign;
    }

}
