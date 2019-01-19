package org.usfirst.frc.team175.robot.commands.teleop;

import org.usfirst.frc.team175.robot.subsystems.Drivetrain;
import org.usfirst.frc.team175.robot.subsystems.LateralDrive;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Arvind
 */
public class LineAlignment extends Command {

    public LineAlignment() {
        requires(LateralDrive.getInstance());
        requires(Drivetrain.getInstance());
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        LateralDrive.getInstance().set(true);
        LateralDrive.getInstance().resetEncoder();
        Drivetrain.getInstance().setPower(0, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        LateralDrive.getInstance().setPosition(LateralDrive.getInstance().getLineSensorPosition().positionToMove());
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        LateralDrive.getInstance().set(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }

}