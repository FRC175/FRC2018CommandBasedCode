package org.usfirst.frc.team175.robot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Arvind
 */
public abstract class DiagnosableSubsystem extends Subsystem implements Diagnosable {

    // Double
    private boolean mSubsystemState;

    // Logger
    // protected Logger mLogger;
    
    // {
    //     mLogger = LoggerFactory.getLogger(getClass());
    // }

    public DiagnosableSubsystem() {
        mSubsystemState = true;
    }

    public void setSubsystemState(boolean enable) {
        mSubsystemState = enable;
    }

    public boolean getSubsystemState() {
        return mSubsystemState;
    }
    
}