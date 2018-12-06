package org.usfirst.frc.team175.robot.util;

/**
 * @author Arvind
 */
public interface Diagnosable {
    
    boolean runSelfTest();
    
    void outputToSmartDashboard();

    void disable(boolean disable);
    
}
