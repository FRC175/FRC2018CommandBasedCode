package org.usfirst.frc.team175.robot.commands.automodes;

import org.usfirst.frc.team175.robot.commands.auto.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @author Arvind
 */
public class CheesyPoofsSpin extends CommandGroup {

    public CheesyPoofsSpin() {
    	addSequential(new TurnToAngle(1000, 0, 1));
    }
    
}
