package org.usfirst.frc.team175.robot.commands.automodes;

import org.usfirst.frc.team175.robot.Constants;
import org.usfirst.frc.team175.robot.Robot;
import org.usfirst.frc.team175.robot.commands.auto.DriveToPosition;
import org.usfirst.frc.team175.robot.commands.auto.ElevatorToPosition;
import org.usfirst.frc.team175.robot.commands.auto.LateralDriveToPosition;
import org.usfirst.frc.team175.robot.commands.teleop.ManipulateCube;
import org.usfirst.frc.team175.robot.commands.teleop.ToggleClimber;
import org.usfirst.frc.team175.robot.subsystems.Grabber;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * @author Arvind
 */
public class OneCubeSwitch extends CommandGroup {

    public OneCubeSwitch() {
    	addSequential(new DriveToPosition(36 * Constants.DRIVE_IN_INCHES));
    	
    	if (Robot.gameData.charAt(0) == 'L')
    		addSequential(new LateralDriveToPosition(3000));
    	else
    		addSequential(new LateralDriveToPosition(-2600));
    		
    	addSequential(new ElevatorToPosition(7000));
    	addSequential(new DriveToPosition(48.5 * Constants.DRIVE_IN_INCHES));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new ToggleClimber());
    	addSequential(new ManipulateCube(Grabber.RollerState.RETRACT_FAST));
    	
    }
}
