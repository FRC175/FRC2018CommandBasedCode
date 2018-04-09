/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team175.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    // TalonSRX Ports
    public static final int LEFT_MASTER_PORT = 0;
    public static final int RIGHT_MASTER_PORT = 1;
    public static final int LEFT_FOLLOWER_PORT = 2;
    public static final int RIGHT_FOLLOWER_PORT = 3;
    public static final int LATERAL_DRIVE_PORT = 4;
    public static final int ELEVATOR_PORT = 5;
    
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
    
    // DoubleSolenoid Ports, Forward Channels, & Reverse Channels
    public static final int CLIMB_ROTATE_PORT = 0;
    public static final int CLIMB_ROTATE_FORWARD_CHANNEL = 0;
    public static final int CLIMB_ROTATE_REVERSE_CHANNEL = 1;

    // Joystick Ports
    public static final int LEFT_JOYSTICK_PORT = 0;
    public static final int RIGHT_JOYSTICK_PORT = 1;
}

