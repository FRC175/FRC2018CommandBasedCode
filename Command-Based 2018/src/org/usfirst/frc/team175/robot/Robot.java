/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team175.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team175.robot.commands.automodes.CheesyPoofsSpin;
import org.usfirst.frc.team175.robot.commands.automodes.DriveStraight;
import org.usfirst.frc.team175.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 * 
 * @author Arvind
 */
// If you rename or move this class, update the build.properties file in the project root
public class Robot extends TimedRobot {

    // Subsystems
    public static Drive drive;
    public static LateralDrive lateralDrive;
    public static Elevator elevator;
    public static Grabber grabber;
    public static Climber climber;

    // Operator Interface
    public static OI oi;
    
    // FMS Data
    public static String gameData;

    // Other
    private Command autonomousCommand;
    private SendableChooser<Command> chooser = new SendableChooser<>();

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
    	drive = new Drive(0, 0.12, 0, 0.0012, 0, 0.08, 0, 0); // Left & Right PID Values
    	lateralDrive = new LateralDrive(0, 10, 0, 2); // PID Values
    	elevator = new Elevator(0, 1, 0, 0); // PID Values
    	grabber = new Grabber();
    	climber = new Climber();
    	
        oi = new OI();
        
        chooser.addDefault("Cross The Line", new DriveStraight());
        chooser.addObject("Cheesy Poofs Spin", new CheesyPoofsSpin());
        SmartDashboard.putData("Auto Modes", chooser);
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    @Override
    public void disabledInit() {

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
        smartDashboardData();
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString code to get the auto name from the text box below the Gyro
     * <p>
     * <p>You can add additional auto modes by adding additional commands to the
     * chooser code above (like the commented example) or additional comparisons
     * to the switch structure below with additional strings & commands.
     */
    @Override
    public void autonomousInit() {
        autonomousCommand = chooser.getSelected();
        gameData = DriverStation.getInstance().getGameSpecificMessage();

        String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
        /*switch (autoSelected) {
	        case "My Auto":
	            autonomousCommand = new MyAutoCommand();
	            break;
	        case "Default Auto":
	        default:
	            autonomousCommand = new ExampleCommand();
	            break;
    	}*/


        // Schedule the autonomous command (example)
        if (autonomousCommand != null) {
            autonomousCommand.start();
        }
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        smartDashboardData();
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) {
            autonomousCommand.cancel();
        }
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        smartDashboardData();
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
    	smartDashboardData();
    }
    
    public void smartDashboardData() {
    	drive.outputToSmartDashboard();
    	lateralDrive.outputToSmartDashboard();
    	elevator.outputToSmartDashboard();
    	grabber.outputToSmartDashboard();
    	climber.outputToSmartDashboard();
    }
}
