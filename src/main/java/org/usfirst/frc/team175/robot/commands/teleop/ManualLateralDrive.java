package org.usfirst.frc.team175.robot.commands.teleop;

import org.usfirst.frc.team175.robot.subsystems.Drivetrain;
import org.usfirst.frc.team175.robot.subsystems.LateralDrive;
import org.usfirst.frc.team175.robot.util.OI;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Arvind
 */
public class ManualLateralDrive extends Command {

    public ManualLateralDrive() {
        requires(LateralDrive.getInstance());
        requires(Drivetrain.getInstance());
    }

    @Override
    protected void initialize() {
        LateralDrive.getInstance().set(true);
        Drivetrain.getInstance().setPower(0, 0);
    }

    @Override
    protected void execute() {
        LateralDrive.getInstance().setPower(OI.getInstance().getDriverStickX());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        LateralDrive.getInstance().setPower(0);
        LateralDrive.getInstance().set(false);
    }

    @Override
    protected void interrupted() {
        end();
    }

}
