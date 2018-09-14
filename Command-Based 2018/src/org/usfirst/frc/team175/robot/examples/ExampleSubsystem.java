package org.usfirst.frc.team175.robot.examples;

import org.usfirst.frc.team175.robot.RobotMap;
import org.usfirst.frc.team175.robot.util.SRXSubsystem;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The following java class contains example code that can help you understand
 * how to create a subsystem.
 * 
 * ROBOT CODE CONVENTIONS:
 * - When dealing with private instance variables, there must be a prefix of m.
 *      EX: private int mInteger;
 * - When dealing with private static variables, there must be a prefix of s.
 *      EX: private static int sInteger;
 * - When dealing with all final variables, all letters must be capitalized.
 *      EX: private final int INTEGER;
 * - When dealing with all other variables, there is nothing special.
 *      EX: public int integer;
 * 
 * STEPS TO MAKE A SUBSYSTEM: 
 * 1.) Create a constructor to configure all variables.
 * 2.) Override initDefaultCommand() method.
 * 3.) Create Create custom methods that use the electronics and pneumatics in the
 *     subsystem that can be used by different commands.
 * 
 * @author Arvind
 * @see SRXSubsystem
 */
public class ExampleSubsystem extends Subsystem {

	/* Declarations */
	// Talon SR
	private Talon mExampleTalonSR;

	// Relay
	private Relay mExampleRelay;

	// Solenoid
	private Solenoid mExampleSolenoid;

	// Limit Switch
	private DigitalInput mExampleLimitSwitch;

	// The variables below are usually located in the RobotMap.java file. However,
	// for convenience sakes and easy accessibility, they are placed here.
	private final static int EXAMPLE_TALON_SR_PORT = 0;
	private final static int EXAMPLE_RELAY_PORT = 0;
	private final static int EXAMPLE_SOLENOID_PORT = 0;
	private final static int EXAMPLE_SOLENOID_CHANNEL = 0;
	private final static int EXAMPLE_LIMIT_SWITCH_PORT = 0;

	/**
	 * STEP 1:
	 * Create a constructor to configure all variables.
	 * 
	 * DESCRIPTION:
	 * A method used to instantiate data fields and objects in the
	 * subsystem when created in the Robot.java file.
	 */ 
	public ExampleSubsystem() {
		// Since the constructor in Subsystem.java has a constructor with no parameters, the 
		// super command is not required to be called here.
		
		/* Instantiations */
		// Below, the electronic and pneumatic objects in this subsystem are 
		// instantiated just like how this subsystem will be instantiated in Robot.java.
		// Talon(pwmIO : int)
		mExampleTalonSR = new Talon(EXAMPLE_TALON_SR_PORT);

		// Relay(io : int)
		mExampleRelay = new Relay(EXAMPLE_RELAY_PORT);

		// Solenoid(canID : int, channel : int)
		mExampleSolenoid = new Solenoid(EXAMPLE_SOLENOID_PORT, EXAMPLE_SOLENOID_CHANNEL);

		// DigitalInput(io : int)
		mExampleLimitSwitch = new DigitalInput(EXAMPLE_LIMIT_SWITCH_PORT);
		
		// You can further configure the hardware here.
	}

	/**
	 * STEP 2:
	 * Override initDefaultCommand() method.
	 * 
	 * DESCRIPTION:
	 * A method that sets the default command for the SRX subsystem.
	 */
	@Override
	protected void initDefaultCommand() {
		// Usually left blank
	}
	
	// STEP 3:
	// Create custom methods that use the electronics and pneumatics in the
	// subsystem that can be used by different commands.
	//
	// EX: public void setPower(double power) { mExampleTalonSR.set(power); }

}
