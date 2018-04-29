package org.usfirst.frc.team175.robot.commands.automodes;

import org.usfirst.frc.team175.robot.Constants;
import org.usfirst.frc.team175.robot.Speeds;
import org.usfirst.frc.team175.robot.commands.auto.DriveToPosition;
import org.usfirst.frc.team175.robot.commands.auto.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive straight and then spin!
 * 
 * @author Arvind
 */
public class CheesyPoofsSpin extends CommandGroup {

    public CheesyPoofsSpin() {
    	addSequential(new DriveToPosition(100 * Constants.DRIVE_IN_INCHES));
    	addSequential(new TurnToAngle(1000, 0, Speeds.FORWARD_FAST.getSpeed()));
    }
    
}
