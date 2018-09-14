package org.usfirst.frc.team175.robot.examples;

import org.usfirst.frc.team175.robot.Constants;
import org.usfirst.frc.team175.robot.util.SRXSubsystem;

/**
 * The following java class contains example code that can help you understand
 * how to create a subsystem that utilizes the Talon SRX motor controller.
 * 
 * STEPS TO MAKE AN SRX SUBSYSTEM: 
 * 1.) Create constructor that can instantiate super class. 
 * 2.) Override initDefaultCommand() method.
 * 
 * @author Arvind
 * @see SRXSubsystem
 */
public class ExampleSRXSubsytem extends SRXSubsystem {
	
	/* Declarations */
	// The variables below are usually located in the RobotMap.java or 
	// Constants.java files. However, for convenience sakes and easy accessibility,
	// they are placed here.  Also, most values below (minus the Talon SRX port) are 
	// default values.
	// Variables below are typically located in RobotMap.java.
	private final static int EXAMPLE_SUBSYSTEM_PORT = 0;
	private final static int EXAMPLE_SUBSYSTEM_POSITION = 0;
	// Variables below are typically located in Constants.java.
	private final static int K_EXAMPLE_SUBSYSTEM_SLOT_INDEX = 0;
	private final static int K_EXAMPLE_SUBSYSTEM_PID_LOOP_INDEX = 0;
	private final static int K_EXAMPLE_SUBSYSTEM_TIMEOUT_MS = 10;
	private final static boolean EXAMPLE_SUBSYSTEM_IS_INVERTED = false;
	private final static boolean EXAMPLE_SUBSYSTEM_SENSOR_PHASE = false;
	
	// Declare other electronics or pneumatic devices here.
	
	/**
	 * STEP 1:
	 * 
	 * DESCRIPTION:
	 * A method used to instantiate data fields and objects in the
	 * subsystem when created in the Robot.java file.
	 * 
	 * PARAMETERS:
	 * @param kF
	 *            The feed forward coefficient used in a PIDF loop.
	 * @param kP
	 *            The proportional coefficient used in a PIDF loop.
	 * @param kI
	 *            The integral coefficient used in a PIDF loop.
	 * @param kD
	 *            The derivative coefficient used in a PIDF loop.
	 * 
	 */
	public ExampleSRXSubsytem(double kF, double kP, double kI, double kD) {
		// This creates the super class's constructors and thus requires to use place 
		// arguments for all of the parameters in the super class's constructor.
		super("Example Subsystem", EXAMPLE_SUBSYSTEM_PORT, EXAMPLE_SUBSYSTEM_POSITION, K_EXAMPLE_SUBSYSTEM_SLOT_INDEX,
				K_EXAMPLE_SUBSYSTEM_PID_LOOP_INDEX, K_EXAMPLE_SUBSYSTEM_TIMEOUT_MS, kF, kP, kI, kD,
				EXAMPLE_SUBSYSTEM_IS_INVERTED, EXAMPLE_SUBSYSTEM_SENSOR_PHASE);
		
		// Instantiate other electronics or pneumatic devices here.
		// You can also call the getSRX() method to configure the Talon SRX further.
		// EX: Current limiting
	}

	/**
	 * STEP 2:
	 * 
	 * DESCRIPTION:
	 * A method that sets the default command for the SRX subsystem.
	 */
	@Override
	protected void initDefaultCommand() {
		// Usually left blank.
	}
	
	// Below you can create custom methods that can be called in commands for other
	// electronic or pneumatic devices or for Talon SRX features not in the 
	// SRXSubsystem.java file.
	// 
	// EX: public boolean isUpperLimitSwitchHit() { return getSRX().getSensorCollection().isFwdLimitSwitchClosed(); }

}
