package org.usfirst.frc.team175.robot.commands.automodes;

import org.usfirst.frc.team175.robot.Constants;
import org.usfirst.frc.team175.robot.Speeds;
import org.usfirst.frc.team175.robot.commands.auto.DriveToPosition;
import org.usfirst.frc.team175.robot.commands.auto.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive straight and cross the auto line.
 * 
 * @author Arvind
 */
public class DriveStraight extends CommandGroup {

    public DriveStraight() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addParallel(new TurnToAngle(0, 5, Speeds.FORWARD_MEDIUM_FAST.getSpeed()));
    	addSequential(new DriveToPosition(100 * Constants.DRIVE_IN_INCHES));
    }
    
}
