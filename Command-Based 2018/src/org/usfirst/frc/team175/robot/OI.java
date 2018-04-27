/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team175.robot;

import org.usfirst.frc.team175.robot.commands.teleop.AlignClimber;
import org.usfirst.frc.team175.robot.commands.teleop.JoystickLateralDrive;
import org.usfirst.frc.team175.robot.commands.teleop.ManipulateCube;
import org.usfirst.frc.team175.robot.commands.teleop.ManualElevator;
import org.usfirst.frc.team175.robot.commands.teleop.PositionClimber;
import org.usfirst.frc.team175.robot.commands.teleop.PositionElevator;
import org.usfirst.frc.team175.robot.commands.teleop.PositionWinch;
import org.usfirst.frc.team175.robot.commands.teleop.Shift;
import org.usfirst.frc.team175.robot.commands.teleop.ToggleClimber;
import org.usfirst.frc.team175.robot.commands.teleop.ToggleGrabber;
import org.usfirst.frc.team175.robot.subsystems.Climber;
import org.usfirst.frc.team175.robot.subsystems.Elevator;
import org.usfirst.frc.team175.robot.subsystems.Grabber;
import org.usfirst.frc.team175.util.TwoButton;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * 
 * @author Arvind
 */
public class OI {
    // CREATING BUTTONS
    // One type of button is a joystick button which is any button on a
    // joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	// Joysticks
	private static Joystick driverStick;
	private static Joystick operatorStick;
	
	// Driver Buttons
	public static Trigger lateralDriveToggle; 
	public static Button windUp;
	public static Button windOut;
	public static Button extendClimber;
	public static Button retractClimber;
	public static Button shift;
	public static Button toggleClimber;
	
	// Operator Buttons
	public static Trigger grabCube;
	public static Button retractCubeSlow;
	public static Button retractCubeFast;
	public static Button rotateGrabber;
	public static Button elevatorCubeGrabbingPosition;
	public static Button elevatorExchangePosition;
	public static Button elevatorSwitchPosition;
	public static Button elevatorLowScalePosition;
	public static Button elevatorHighScalePosition;
	public static Button elevatorManual;
	public static Button toggleGrabber;
	
	public OI() {
		/* Joystick Instantiation */
		// Joystick(port : int)
		driverStick = new Joystick(RobotMap.LEFT_JOYSTICK_PORT);
		operatorStick = new Joystick(RobotMap.RIGHT_JOYSTICK_PORT);
		
		/* Button Instantiation */
		// Driver Stick
		// JoystickButton(joystick : GenericHID, buttonNumber : int)
		lateralDriveToggle = new JoystickButton(driverStick, 1); // 1 is the trigger button
		shift = new JoystickButton(driverStick, 2);
		extendClimber = new JoystickButton(driverStick, 7);
		retractClimber = new JoystickButton(driverStick, 8); 
		toggleClimber = new JoystickButton(driverStick, 9);
		windUp = new JoystickButton(driverStick, 11);
		// TwoButton(joystick : GenericHID, firstButtonNumber : int, secondButtonNumber : int)
		windOut = new TwoButton(driverStick, 3, 4);
		

		// Operator Stick
		grabCube = new JoystickButton(operatorStick, 1);
		retractCubeFast = new JoystickButton(operatorStick, 3);
		retractCubeSlow = new JoystickButton(operatorStick, 4);
		elevatorCubeGrabbingPosition = new JoystickButton(operatorStick, 2);
		elevatorExchangePosition = new JoystickButton(operatorStick, 7);
		elevatorSwitchPosition = new JoystickButton(operatorStick, 8);
		elevatorLowScalePosition = new JoystickButton(operatorStick, 9);
		elevatorHighScalePosition = new JoystickButton(operatorStick, 10);
		elevatorManual = new JoystickButton(operatorStick, 12);
		toggleGrabber = new JoystickButton(operatorStick, 5);
		
		/* Button Configuration */
		// Driver Stick
		lateralDriveToggle.whileActive(new JoystickLateralDrive());
		shift.whileHeld(new Shift());
		extendClimber.whileHeld(new PositionClimber(Climber.ClimberState.EXTEND));
		retractClimber.whileHeld(new PositionClimber(Climber.ClimberState.RETRACT)); // Limit switch integration?
		toggleClimber.toggleWhenPressed(new ToggleClimber());
		windUp.whileHeld(new PositionWinch(Climber.WinchState.WIND_UP));
		windOut.whileHeld(new PositionWinch(Climber.WinchState.WIND_OUT));
		
		// Operator Stick
		grabCube.whileActive(new ManipulateCube(Grabber.RollerState.GRAB));
		retractCubeFast.whileHeld(new ManipulateCube(Grabber.RollerState.RETRACT_FAST));
		retractCubeSlow.whileHeld(new ManipulateCube(Grabber.RollerState.RETRACT_SLOW));
		elevatorCubeGrabbingPosition.whenPressed(new PositionElevator(Elevator.ElevatorPositions.POWER_CUBE_PICKUP));
		elevatorExchangePosition.whenPressed(new PositionElevator(Elevator.ElevatorPositions.EXCHANGE));
		elevatorSwitchPosition.whenPressed(new PositionElevator(Elevator.ElevatorPositions.SWITCH));
		elevatorLowScalePosition.whenPressed(new PositionElevator(Elevator.ElevatorPositions.LOW_SCALE));
		elevatorHighScalePosition.whenPressed(new PositionElevator(Elevator.ElevatorPositions.HIGH_SCALE));
		elevatorManual.whileHeld(new ManualElevator());
		toggleGrabber.toggleWhenPressed(new ToggleGrabber()); // If this doesn't work, use .whenPressed(command : Command)
	}
	
	public double getDriverStickX() {
		return driverStick.getX();
	}
	
	public double getDriverStickY() {
		return driverStick.getY();
	}
	
	public double getOperatorStickX() {
		return operatorStick.getX();
	}
	
	public double getOperatorStickY() {
		return operatorStick.getY();
	}
}
