package org.usfirst.frc.team175.robot.commands.features2019;

import org.usfirst.frc.team175.robot.subsystems.Drivetrain;
import org.usfirst.frc.team175.robot.subsystems.LateralDrive;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Arvind
 */
public class LineAlignment extends Command {

    private int mPosition;

    public LineAlignment() {
        requires(LateralDrive.getInstance());
        requires(Drivetrain.getInstance());

        mPosition = 0;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        LateralDrive.getInstance().set(true);
        Drivetrain.getInstance().setPower(0, 0);
        LateralDrive.getInstance().resetEncoder();

        mPosition = LateralDrive.getInstance().getLineSensorPosition().positionToMove();

        LateralDrive.getInstance().setWantedPosition(mPosition);

        System.out.println("INFO - LineAlignment command initialized successfully!");
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        // LateralDrive.getInstance().setPosition(mPosition);   
        if (mPosition >= 0)
            LateralDrive.getInstance().setPower(0.25);
        else
            LateralDrive.getInstance().setPower(-0.25);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return LateralDrive.getInstance().isAtWantedPosition();
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