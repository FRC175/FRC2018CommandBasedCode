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
    private Talon mClimberExtend;
    private Talon mWinch;

    // Solenoid
    private Solenoid mClimberAlign;

    // Limit Switches
    private DigitalInput mClimberUpLimit;
    private DigitalInput mClimberDownLimit;

    // Boolean
    private boolean mSubsystemState;

    // Enums
    public enum ClimberPosition {
        EXTEND,
        RETRACT,
        IDLE;
    }

    public enum WinchPosition {
        WIND_UP,
        WIND_OUT,
        IDLE;
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
        mClimberExtend = new Talon(Constants.CLIMB_EXTEND_PORT);
        mWinch = new Talon(Constants.WINCH_PORT);

        // Solenoid(canID : int, channel : int)
        mClimberAlign = new Solenoid(Constants.CLIMB_ALIGN_PORT, Constants.CLIMB_ALIGN_CHANNEL);

        // DigitalInput(io : int)
        mClimberUpLimit = new DigitalInput(Constants.CLIMB_UP_LIMIT_PORT);
        mClimberDownLimit = new DigitalInput(Constants.CLIMB_DOWN_LIMIT_PORT);

        // Boolean
        mSubsystemState = true;

        // Set climber inwards
        mClimberAlign.set(false);
    }

    public void setPosition(Climber.ClimberPosition climberState) {
        if (mSubsystemState) {
            switch (climberState) {
                case EXTEND:
                    mClimberExtend.set(!mClimberUpLimit.get() ? 1 : 0);
                    // mClimberExtend.set(mClimberDownLimit.get() ? 1 : 0);
                    // mClimberExtend.set(1);
                    break;
                case RETRACT:
                    mClimberExtend.set(!mClimberDownLimit.get() ? -1 : 0);
                    // mClimberExtend.set(mClimberUpLimit.get() ? -1 : 0);
                    // mClimberExtend.set(-1);
                    break;
                case IDLE:
                    mClimberExtend.set(0);
                    break;
            }
        }
    }

    @Deprecated
    public void setPower(double speed) {
        if (mSubsystemState) {
            mClimberExtend.set(speed);
        }
    }

    public boolean isExtended() {
        return mClimberUpLimit.get();
    }

    public boolean isRetracted() {
        return mClimberDownLimit.get();
    }

    public void winch(Climber.WinchPosition winchState) {
        if (mSubsystemState) {
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
    }

    public void winchManual(double speed) {
        if (mSubsystemState) {
            mWinch.set(speed);
        }
    }

    // TODO: Determine whether this is necessary or not.
    public double getWinchSpeed() {
        return mWinch.get();
    }

    public void align(boolean align) {
        if (mSubsystemState) {
            mClimberAlign.set((mWinch.get() == 0) ? align : false);
        }
    }

    public boolean isAligned() {
        return mClimberAlign.get();
    }

    @Override
    public void initDefaultCommand() {
    }

    @Override
    public void outputToSmartDashboard() {
        SmartDashboard.putNumber("Climber Power", mClimberExtend.get());
        SmartDashboard.putNumber("Winch Power", mWinch.get());

        SmartDashboard.putBoolean("Climber aligned?", mClimberAlign.get());
        SmartDashboard.putBoolean("Extended all the way?", mClimberUpLimit.get());
        SmartDashboard.putBoolean("Retracted all the way?", mClimberDownLimit.get());
    }

    @Override
    public boolean runSelfTest() {
        return false;
    }

    @Override
    public void setState(boolean enable) {
        mSubsystemState = enable;
    }

}
