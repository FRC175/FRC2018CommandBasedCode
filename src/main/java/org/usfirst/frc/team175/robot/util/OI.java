package org.usfirst.frc.team175.robot.util;

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
    private Button mShiftToHighGear;
    private Button mToggleClimber;
    private TwoButton mWindOut;

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
        mShiftToHighGear = new JoystickButton(mDriverStick, Constants.SHIFT_BUTTON);
        mToggleClimber = new JoystickButton(mDriverStick, Constants.TOGGLE_CLIMBER_BUTTON);
        // TwoButton(joystick : GenericHID, firstButtonNumber : int, secondButtonNumber : int)
        mWindOut = new TwoButton(mDriverStick, Constants.WIND_OUT_BUTTON_ONE, Constants.WIND_OUT_BUTTON_TWO);

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
