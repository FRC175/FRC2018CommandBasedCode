package org.usfirst.frc.team175.robot.util;

/**
 * @author Arvind
 */
public class Constants {

    // Prevent Constants from being instantiated
    private Constants() {
    }

    // Talon SRX Ports
    public static final int LEFT_MASTER_PORT = 2;
    public static final int RIGHT_MASTER_PORT = 0;
    public static final int LEFT_FOLLOWER_PORT = 3;
    public static final int RIGHT_FOLLOWER_PORT = 1;
    public static final int LATERAL_DRIVE_PORT = 5;
    public static final int ELEVATOR_PORT = 4;

    // Talon SRX Constants
    public static final int SLOT_INDEX = 0;
    public static final int PID_LOOP_INDEX = 0;
    public static final int TIMEOUT_MS = 10;

    // TalonSR Ports
    public static final int GRAB_ROLLER_L_PORT = 1;
    public static final int GRAB_ROLLER_R_PORT = 2;
    public static final int CLIMB_EXTEND_PORT = 4;
    public static final int WINCH_PORT = 0;

    // Limit Switch Ports
    public static final int GRABBER_LIMIT_PORT = 9;
    public static final int CLIMB_UP_LIMIT_PORT = 4;
    public static final int CLIMB_DOWN_LIMIT_PORT = 3;

    // Solenoid Ports & Channels
    public static final int SHIFT_PORT = 0;
    public static final int SHIFT_CHANNEL = 3;
    public static final int LATERAL_DEPLOY_PORT = 0;
    public static final int LATERAL_DEPLOY_CHANNEL = 2;
    public static final int GRABBER_RETRACT_PORT = 0;
    public static final int GRABBER_RETRACT_CHANNEL = 5;
    public static final int CLIMB_ALIGN_PORT = 0;
    public static final int CLIMB_ALIGN_CHANNEL = 4;

    // Joystick Ports
    public static final int DRIVER_STICK_PORT = 0;
    public static final int OPERATOR_STICK_PORT = 1;

    // Driver Stick Buttons
    public static final int TOGGLE_LATERAL_DRIVE_TRIGGER = 1;
    public static final int WIND_UP_BUTTON = 11;
    public static final int EXTEND_CLIMBER_BUTTON = 7;
    public static final int RETRACT_CLIMBER_BUTTON = 8;
    public static final int SHIFT_BUTTON = 2;
    public static final int TOGGLE_CLIMBER_BUTTON = 9;
    public static final int WIND_OUT_BUTTON_ONE = 3;
    public static final int WIND_OUT_BUTTON_TWO = 4;

    // Operator Stick Buttons
    public static final int GRAB_CUBE_TRIGGER = 1;
    public static final int SHOOT_CUBE_FAST_BUTTON = 3;
    public static final int SHOOT_CUBE_SLOW_BUTTON = 4;
    public static final int TOGGLE_GRABBER_BUTTON = 5;
    public static final int ELEVATOR_POSITION_ONE_BUTTON = 2;
    public static final int ELEVATOR_POSITION_TWO_BUTTON = 7;
    public static final int ELEVATOR_POSITION_THREE_BUTTON = 8;
    public static final int ELEVATOR_POSITION_FOUR_BUTTON = 9;
    public static final int ELEVATOR_POSITION_FIVE_BUTTON = 10;
    public static final int MANUAL_ELEVATOR_BUTTON = 12;

    // Relay Ports
    public static final int POWER_CUBE_LIGHT_PORT = 0;

    // Other
    public static final double DRIVE_TO_INCHES = 176;
    public static final double LATERAL_DRIVE_TO_INCHES = 42.965;

}
