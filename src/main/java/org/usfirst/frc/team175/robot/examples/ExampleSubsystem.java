package org.usfirst.frc.team175.robot.examples;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team175.robot.util.TalonSRXFactory;
import org.usfirst.frc.team175.robot.util.TalonSRXType;

/**
 * The following java class contains example code that can help you understand
 * how to create a subsystem.
 * <p>
 * ROBOT CODE CONVENTIONS:
 * - When dealing with private instance variables, there must be a prefix of m.
 * EX: private int mInteger;
 * - When dealing with private static variables, there must be a prefix of s.
 * EX: private static int sInteger;
 * - When dealing with all final variables, all letters must be capitalized.
 * EX: private final int INTEGER;
 * - When dealing with all other variables, there is nothing special.
 * EX: public int integer;
 * <p>
 * STEPS TO MAKE A SUBSYSTEM:
 * 1.) Create a constructor to configure all variables..
 * 2.) Override initDefaultCommand() method.
 * 3.) Create
 *
 * @author Arvind
 * @see Subsystem
 */
public class ExampleSubsystem extends Subsystem {

    /* Declarations */
    // TalonSRX
    private TalonSRX mExampleTalonSRX;

    // Talon SR
    private Talon mExampleTalonSR;

    // Relay
    private Relay mExampleRelay;

    // Solenoid
    private Solenoid mExampleSolenoid;

    // Limit Switch
    private DigitalInput mExampleLimitSwitch;

    // Subsystem Instance (Used in Singleton Design Pattern, Optional)
    private static ExampleSubsystem sInstance;

    // The variables below are usually located in the Constants.java file. However, for convenience sakes and easy
    // accessibility, they are placed here.
    private final static int EXAMPLE_TALON_SRX_PORT = 0;
    private final static int EXAMPLE_TALON_SR_PORT = 1;
    private final static int EXAMPLE_RELAY_PORT = 2;
    private final static int EXAMPLE_SOLENOID_PORT = 3;
    private final static int EXAMPLE_SOLENOID_CHANNEL = 4;
    private final static int EXAMPLE_LIMIT_SWITCH_PORT = 5;

    /**
     * OPTIONAL STEP
     * <p>
     * DESCRIPTION:
     * A method used to return the one and only instance of the Example Subsystem. This is used in to comply with the
     * Singleton design pattern.
     */
    public static ExampleSubsystem getInstance() {
        if (sInstance == null) {
            try {
                sInstance = new ExampleSubsystem();
            } catch (Exception e) {
                DriverStation.reportError("Example Subsystem failed to instantiate.\n" + e, true);
            }
        }

        return sInstance;
    }

    /**
     * STEP 1:
     * <p>
     * DESCRIPTION:
     * A method used to instantiate data fields and objects in the subsystem when created in the Robot.java file.
     * <p>
     * NOTE:
     * Constructor can be set to private if Singleton design pattern is used.
     */
    public ExampleSubsystem() {
        // Since the constructor in Subsystem.java has a constructor with no parameters, the super command is not
        // required to be called here.

        /* Instantiations */
        // Below, the electronic and pneumatic objects in this subsystem are instantiated just like how this subsystem
        // will be instantiated in Robot.java.

        // TalonSRXFactory.getSRX(portNum : int, type : String)
        // Note: The 'type' String has two options, 'CTRE' or 'WORCESTER', each referring to two implementations of a
        //       TalonSRX.
        mExampleTalonSRX = TalonSRXFactory.getSRX(EXAMPLE_TALON_SRX_PORT, TalonSRXType.CROSS_THE_ROAD);

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
     * <p>
     * DESCRIPTION:
     * A method that sets the default command for the SRX subsystem.
     */
    @Override
    protected void initDefaultCommand() {
        // Usually left blank.
    }

    // STEP 3:
    // You can create custom methods that use the electronics and pneumatics in the subsystem that can be used by
    // different commands.
    // EX: public void setPower() {}

}
