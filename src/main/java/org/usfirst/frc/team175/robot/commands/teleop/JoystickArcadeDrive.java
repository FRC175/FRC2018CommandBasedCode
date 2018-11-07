package org.usfirst.frc.team175.robot.commands.teleop;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team175.robot.subsystems.Drivetrain;
import org.usfirst.frc.team175.robot.subsystems.LateralDrive;
import org.usfirst.frc.team175.robot.util.OI;

/**
 * @author Arvind
 */
public class JoystickArcadeDrive extends Command {

    private boolean mShiftHigh;

    public JoystickArcadeDrive(boolean shiftHigh) {
        mShiftHigh = shiftHigh;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(LateralDrive.getInstance());
        requires(Drivetrain.getInstance());
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        LateralDrive.getInstance().setPower(0);
        LateralDrive.getInstance().set(false);

        Drivetrain.getInstance().setBrakeMode(false);
        Drivetrain.getInstance().setHighGear(mShiftHigh);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        if (!LateralDrive.getInstance().isEnabled())
            Drivetrain.getInstance().arcadeDrive(OI.getInstance().getDriverStickY(), OI.getInstance().getDriverStickX());
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        // return LateralDrive.getInstance().isEnabled();
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Drivetrain.getInstance().setPower(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }

}
