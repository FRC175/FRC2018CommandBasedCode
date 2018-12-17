package org.usfirst.frc.team175.robot.util;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Arvind
 */
public abstract class DiagnosableSubsystem extends Subsystem implements Diagnosable {

    // Double
    private boolean mSubsystemState;

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