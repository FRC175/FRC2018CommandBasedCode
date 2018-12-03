package org.usfirst.frc.team175.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team175.robot.util.Constants;
import org.usfirst.frc.team175.robot.util.Diagnosable;

/**
 * @author Arvind
 */
public class Grabber extends Subsystem implements Diagnosable {

    /* Declarations */
    // Talon SRs
    private Talon mGrabRollerL;
    private Talon mGrabRollerR;

    // Solenoid
    private Solenoid mGrabberRetract;

    // Limit Switch
    private DigitalInput mGrabberLimit;

    // Relay
    private Relay mPowerCubeLight;

    // Enum
    public enum RollerState {
        GRAB, 
        RETRACT_FAST, 
        RETRACT_SLOW, 
        IDLE;
    }

    // Singleton Instance
    private static Grabber sInstance;

    // Singleton Static Factory Method
    public static Grabber getInstance() {
        if (sInstance == null) {
            try {
                sInstance = new Grabber();
            } catch (Exception e) {
                DriverStation.reportError("Grabber subsystem failed to instantiate.\n" + e, true);
            }
        }

        return sInstance;
    }

    private Grabber() {
        /* Instantiations */
        // Talon(pwmIO : int)
        mGrabRollerL = new Talon(Constants.GRAB_ROLLER_L_PORT);
        mGrabRollerR = new Talon(Constants.GRAB_ROLLER_R_PORT);

        // Solenoid(canID : int, channel : int)
        mGrabberRetract = new Solenoid(Constants.GRABBER_RETRACT_PORT, Constants.GRABBER_RETRACT_CHANNEL);

        // DigitalInput(io : int)
        mGrabberLimit = new DigitalInput(Constants.GRABBER_LIMIT_PORT);

        // Relay(io : int)
        mPowerCubeLight = new Relay(Constants.POWER_CUBE_LIGHT_PORT);
    }

    public void grab(RollerState rollerState) {
        double speed;

        switch (rollerState) {
        case GRAB:
            speed = !mGrabberLimit.get() ? -1 : 0;
            break;
        case RETRACT_FAST:
            speed = 1;
            break;
        case RETRACT_SLOW:
            speed = 0.3;
            break;
        case IDLE:
            // Default grabber bias
            speed = -0.15;
            break;
        default:
            speed = 0;
            break;
        }

        mGrabRollerL.set(speed);
        mGrabRollerR.set(speed);
    }

    public boolean isPowerCubeGrabbed() {
        return mGrabberLimit.get();
    }

    public void setPosition(boolean retract) {
        mGrabberRetract.set(retract);
    }

    public void setLight(boolean on) {
        mPowerCubeLight.set(on ? Relay.Value.kForward : Relay.Value.kOff);
    }

    @Override
    public void initDefaultCommand() {
    }

    @Override
    public void outputToSmartDashboard() {
        SmartDashboard.putNumber("Left Roller Power", mGrabRollerL.get());
        SmartDashboard.putNumber("Right Roller Power", mGrabRollerR.get());

        SmartDashboard.putBoolean("Grabber State", mGrabberRetract.get());
        SmartDashboard.putBoolean("Cube?", mGrabberLimit.get());
    }

    @Override
    public boolean runSelfTest() {
        return false;
    }

}
