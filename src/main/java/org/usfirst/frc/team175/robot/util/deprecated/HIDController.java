package org.usfirst.frc.team175.robot.util.deprecated;

import org.usfirst.frc.team175.robot.util.Constants;
import org.usfirst.frc.team175.robot.util.TwoButton;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

@Deprecated
public class HIDController {

    // Joysticks
    private Joystick mDriverStick;
    private Joystick mOperatorStick;

    // Driver Buttons
    private Button mToggleLateralDrive;
    private Button mWindUp;
    private Button mExtendClimber;
    private Button mRetractClimber;
    private Button mShiftToHighGear;
    private Button mToggleClimberAlign;
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
    private static HIDController sInstance;

    public static HIDController getInstance() {
        if (sInstance == null) {
            try {
                sInstance = new HIDController();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return sInstance;
    }

    private HIDController() {
        /* Joystick Instantiation */
        // Joystick(port : int)
        mDriverStick = new Joystick(Constants.LEFT_JOYSTICK_PORT);
        mOperatorStick = new Joystick(Constants.RIGHT_JOYSTICK_PORT);

        /* Button Instantiation */
        // Driver Stick
        // JoystickButton(joystick : GenericHID, buttonNumber : int)
        mToggleLateralDrive = new JoystickButton(mDriverStick, 1); // 1 is the trigger button
        mWindUp = new JoystickButton(mDriverStick, 11);
        mExtendClimber = new JoystickButton(mDriverStick, 7);
        mRetractClimber = new JoystickButton(mDriverStick, 8);
        mShiftToHighGear = new JoystickButton(mDriverStick, 2);
        mToggleClimberAlign = new JoystickButton(mDriverStick, 9);
        // TwoButton(joystick : GenericHID, firstButtonNumber : int, secondButtonNumber : int)
        mWindOut = new TwoButton(mDriverStick, 3, 4);

        // Operator Stick
        mGrabCube = new JoystickButton(mOperatorStick, 1);
        mShootCubeFast = new JoystickButton(mOperatorStick, 3);
        mShootCubeSlow = new JoystickButton(mOperatorStick, 4);
        mToggleGrabber = new JoystickButton(mOperatorStick, 5);
        mCubeGrabbingElevatorPosition = new JoystickButton(mOperatorStick, 2);
        mExchangeElevatorPosition = new JoystickButton(mOperatorStick, 7);
        mSwitchElevatorPosition = new JoystickButton(mOperatorStick, 8);
        mLowScaleElevatorPosition = new JoystickButton(mOperatorStick, 9);
        mHighScaleElevatorPosition = new JoystickButton(mOperatorStick, 10);
        mManualElevator = new JoystickButton(mOperatorStick, 12);
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
