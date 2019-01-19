package org.usfirst.frc.team175.robot.util;

import org.usfirst.frc.team175.robot.commands.teleop.*;
import org.usfirst.frc.team175.robot.subsystems.Climber;
import org.usfirst.frc.team175.robot.subsystems.Elevator;
import org.usfirst.frc.team175.robot.subsystems.Grabber;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * @author Arvind
 */
public class OI {

    // Joysticks
    private Joystick mDriverStick;
    private Joystick mOperatorStick;

    // Driver Buttons
    private Button mToggleLateralDrive;
    private Button mWindUp;
    private Button mExtendClimber;
    private Button mRetractClimber;
    private Button mShifter;
    private Button mToggleClimber;
    private TwoButton mWindOut;
    private Button mLineAlign;

    // Operator Buttons
    private Button mGrabCube;
    private Button mShootCubeFast;
    private Button mShootCubeSlow;
    private Button mToggleGrabber;
    private Button mCubeGrabbingElevatorPosition;
    private Button mExchangeElevatorPosition;
    private Button mSwitchElevatorPosition;
    private Button mLowScaleElevatorPosition;
    private Button mHighScaleElevatorPosition;
    private Button mManualElevator;

    // Object Instance
    private static OI sInstance;

    public static OI getInstance() {
        if (sInstance == null) {
            try {
                sInstance = new OI();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return sInstance;
    }

    private OI() {
        /* Joystick Instantiation */
        // Joystick(port : int)
        mDriverStick = new Joystick(Constants.DRIVER_STICK_PORT);
        mOperatorStick = new Joystick(Constants.OPERATOR_STICK_PORT);

        /* Button Instantiation */
        // Driver Stick
        // JoystickButton(joystick : GenericHID, buttonNumber : int)
        mToggleLateralDrive = new JoystickButton(mDriverStick, Constants.TOGGLE_LATERAL_DRIVE_TRIGGER); // 1 is the trigger button
        mWindUp = new JoystickButton(mDriverStick, Constants.WIND_UP_BUTTON);
        mExtendClimber = new JoystickButton(mDriverStick, Constants.EXTEND_CLIMBER_BUTTON);
        mRetractClimber = new JoystickButton(mDriverStick, Constants.RETRACT_CLIMBER_BUTTON);
        mShifter = new JoystickButton(mDriverStick, Constants.SHIFT_BUTTON);
        mToggleClimber = new JoystickButton(mDriverStick, Constants.TOGGLE_CLIMBER_BUTTON);
        // TwoButton(joystick : GenericHID, firstButtonNumber : int, secondButtonNumber : int)
        mWindOut = new TwoButton(mDriverStick, Constants.WIND_OUT_BUTTON_ONE, Constants.WIND_OUT_BUTTON_TWO);
        mLineAlign = new JoystickButton(mDriverStick, Constants.kLineAlignButton);

        // Operator Stick
        mGrabCube = new JoystickButton(mOperatorStick, Constants.GRAB_CUBE_TRIGGER);
        mShootCubeFast = new JoystickButton(mOperatorStick, Constants.SHOOT_CUBE_FAST_BUTTON);
        mShootCubeSlow = new JoystickButton(mOperatorStick, Constants.SHOOT_CUBE_SLOW_BUTTON);
        mToggleGrabber = new JoystickButton(mOperatorStick, Constants.TOGGLE_GRABBER_BUTTON);
        mCubeGrabbingElevatorPosition = new JoystickButton(mOperatorStick, Constants.ELEVATOR_POSITION_ONE_BUTTON);
        mExchangeElevatorPosition = new JoystickButton(mOperatorStick, Constants.ELEVATOR_POSITION_TWO_BUTTON);
        mSwitchElevatorPosition = new JoystickButton(mOperatorStick, Constants.ELEVATOR_POSITION_THREE_BUTTON);
        mLowScaleElevatorPosition = new JoystickButton(mOperatorStick, Constants.ELEVATOR_POSITION_FOUR_BUTTON);
        mHighScaleElevatorPosition = new JoystickButton(mOperatorStick, Constants.ELEVATOR_POSITION_FIVE_BUTTON);
        mManualElevator = new JoystickButton(mOperatorStick, Constants.MANUAL_ELEVATOR_BUTTON);

        /* Button Configuration */
        // Driver Strick
        mToggleLateralDrive.whileActive(new ManualLateralDrive());
        mWindUp.whileHeld(new PositionWinch(Climber.WinchPosition.WIND_UP));
        mWindOut.whileHeld(new PositionWinch(Climber.WinchPosition.WIND_OUT));
        mExtendClimber.whileHeld(new PositionClimber(Climber.ClimberPosition.EXTEND));
        mRetractClimber.whileHeld(new PositionClimber(Climber.ClimberPosition.RETRACT));
        mShifter.whileHeld(new ManualArcadeDrive(true));
        mToggleClimber.toggleWhenPressed(new ToggleClimber());
        mLineAlign.whenPressed(new LineAlignment());

        // Operater Stick
        mGrabCube.whileActive(new ManipulateCube(Grabber.RollerPosition.GRAB));
        mShootCubeFast.whileHeld(new ManipulateCube(Grabber.RollerPosition.RETRACT_FAST));
        mShootCubeSlow.whileHeld(new ManipulateCube(Grabber.RollerPosition.RETRACT_SLOW));
        mToggleGrabber.toggleWhenPressed(new ToggleGrabber());
        mCubeGrabbingElevatorPosition.whenPressed(new PositionElevator(Elevator.ElevatorPosition.POWER_CUBE_PICKUP));
        mExchangeElevatorPosition.whenPressed(new PositionElevator(Elevator.ElevatorPosition.EXCHANGE));
        mSwitchElevatorPosition.whenPressed(new PositionElevator(Elevator.ElevatorPosition.SWITCH));
        mLowScaleElevatorPosition.whenPressed(new PositionElevator(Elevator.ElevatorPosition.LOW_SCALE));
        mHighScaleElevatorPosition.whenPressed(new PositionElevator(Elevator.ElevatorPosition.HIGH_SCALE));
        mManualElevator.whileHeld(new ManualElevator());
    }

    public double getDriverStickX() {
        return mDriverStick.getX();
    }

    public double getDriverStickY() {
        return mDriverStick.getY();
    }

    public double getOperatorStickX() {
        return mOperatorStick.getX();
    }

    public double getOperatorStickY() {
        return mOperatorStick.getY();
    }

}
