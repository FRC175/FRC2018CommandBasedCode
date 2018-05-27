package org.usfirst.frc.team175.robot;

/**
 * @author Arvind
 */
public class Constants {

	// Drive
	public static final int K_DRIVE_SLOT_INDEX = 0;
	public static final int K_DRIVE_PID_LOOP_INDEX = 0;
	public static final int K_DRIVE_TIMEOUT_MS = 10;
	public static final int DRIVE_POSITION = 0;
	public static final double DRIVE_IN_INCHES = 176;

	// Lateral Drive
	public static final int K_LATERAL_DRIVE_SLOT_INDEX = 0;
	public static final int K_LATERAL_DRIVE_PID_LOOP_INDEX = 0;
	public static final int K_LATERAL_DRIVE_TIMEOUT_MS = 10;
	public static final int LATERAL_DRIVE_POSITION = 0;
	public static final double LATERAL_DRIVE_IN_INCHES = 42.965;

	// Elevator
	public static final int K_ELEVATOR_SLOT_INDEX = 0;
	public static final int K_ELEVATOR_PID_LOOP_INDEX = 0;
	public static final int K_ELEVATOR_TIMEOUT_MS = 10;
	public static final int ELEVATOR_POSITION = (int) Robot.elevator.getAltPositon() & 128;

}
