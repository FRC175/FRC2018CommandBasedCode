package org.usfirst.frc.team175.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;

import org.usfirst.frc.team175.robot.util.Constants;
import org.usfirst.frc.team175.robot.util.Diagnosable;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Arvind
 */
public class Climber extends Subsystem implements Diagnosable {

    /* Declarations */
    // Talon SRs
    private Talon mClimbExtend;
    private Talon mWinch;

    // Solenoid
    private Solenoid mClimbAlign;

    // Limit Switches
    private DigitalInput mClimbUpLimit;
    private DigitalInput mClimbDownLimit;

    // Enums
    public enum ClimberPosition {
        EXTEND,
        RETRACT,
        IDLE
    }

    public enum WinchPosition {
        WIND_UP,
        WIND_OUT,
        IDLE
    }

    // Singleton Instance
    private static Climber sInstance;

    // Singleton Static Factory Method
    public static Climber getInstance() {
        if (sInstance == null) {
            try {
                sInstance = new Climber();
            } catch (Exception e) {
                DriverStation.reportError("Climber subsystem failed to instantiate.\n" + e, true);
            }
        }

        return sInstance;
    }

    private Climber() {
        /* Instantiations */
        // Talon(pwmIO : int)
        mClimbExtend = new Talon(Constants.CLIMB_EXTEND_PORT);
        mWinch = new Talon(Constants.WINCH_PORT);

        // Solenoid(canID : int, channel : int)
        mClimbAlign = new Solenoid(Constants.CLIMB_ALIGN_PORT, Constants.CLIMB_ALIGN_CHANNEL);

        // DigitalInput(io : int)
        mClimbUpLimit = new DigitalInput(Constants.CLIMB_UP_LIMIT_PORT);
        mClimbDownLimit = new DigitalInput(Constants.CLIMB_DOWN_LIMIT_PORT);

        // Set climber inwards
        mClimbAlign.set(false);
    }

    public void setPosition(Climber.ClimberPosition climberState) {
        switch (climberState) {
            case EXTEND:
                mClimbExtend.set(!mClimbUpLimit.get() ? 1 : 0);
                // mClimbExtend.set(mClimbDownLimit.get() ? 1 : 0);
                // mClimbExtend.set(1);
                break;
            case RETRACT:
                mClimbExtend.set(!mClimbDownLimit.get() ? -1 : 0);
                // mClimbExtend.set(mClimbUpLimit.get() ? -1 : 0);
                // mClimbExtend.set(-1);
                break;
            case IDLE:
                mClimbExtend.set(0);
                break;
        }
    }

    public void setPower(double speed) {
        mClimbExtend.set(speed);
    }

    public boolean isExtended() {
        return mClimbUpLimit.get();
    }

    public boolean isRetracted() {
        return mClimbDownLimit.get();
    }

    public void winch(Climber.WinchPosition winchState) {
        switch (winchState) {
            case WIND_UP:
                mWinch.set(1);
                break;
            case WIND_OUT:
                mWinch.set(-1);
                break;
            case IDLE:
                mWinch.set(0);
                break;
        }
    }

    public void winchManual(double speed) {
        mWinch.set(speed);
    }

    // TODO: Determine whether this is necessary or not.
    public double getWinchSpeed() {
        return mWinch.get();
    }

    public void align(boolean align) {
        mClimbAlign.set((mWinch.get() == 0) ? align : false);
    }

    public boolean isAligned() {
        return mClimbAlign.get();
    }

    @Override
    public void initDefaultCommand() {
    }

    @Override
    public void outputToSmartDashboard() {
        SmartDashboard.putNumber("Climber Power", mClimbExtend.get());
        SmartDashboard.putNumber("Winch Power", mWinch.get());

        SmartDashboard.putBoolean("Climber aligned?", mClimbAlign.get());
        SmartDashboard.putBoolean("Extended all the way?", mClimbUpLimit.get());
        SmartDashboard.putBoolean("Retracted all the way?", mClimbDownLimit.get());
    }

    @Override
    public boolean runSelfTest() {
        return false;
    }

}
