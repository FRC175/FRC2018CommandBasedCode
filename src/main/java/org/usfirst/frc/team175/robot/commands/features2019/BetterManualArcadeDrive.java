package org.usfirst.frc.team175.robot.commands.features2019;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team175.robot.subsystems.Drivetrain;
import org.usfirst.frc.team175.robot.subsystems.LateralDrive;
import org.usfirst.frc.team175.robot.util.Mathematiques;
import org.usfirst.frc.team175.robot.util.OI;

public class BetterManualArcadeDrive extends Command {

    private boolean mIsHighGear;

    public BetterManualArcadeDrive(boolean isHighGear) {
        requires(Drivetrain.getInstance());
        requires(LateralDrive.getInstance());

        mIsHighGear = isHighGear;
    }

    @Override
    protected void initialize() {
        Drivetrain.getInstance().setHighGear(mIsHighGear);
    }

    @Override
    protected void execute() {
        if (!LateralDrive.getInstance().isEnabled()) {
            double x = Mathematiques.addDeadzone(OI.getInstance().getDriverStickTwist(), 0.05);
            double y = Mathematiques.addDeadzone(OI.getInstance().getDriverStickY(), 0.05);
            Drivetrain.getInstance().betterArcadeDrive(y, x);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Drivetrain.getInstance().setPower(0, 0);
    }

    @Override
    protected void interrupted() {
        end();
    }

}
