package org.usfirst.frc.team175.robot.util.deprecated;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Arvind
 */
@Deprecated
public class Logger {

    // Prevent Logger from being instantiated
    private Logger() {
    }

    public static void toRIO(Object o) {
    }

    public static void toConsole(Object o) {
        System.out.println(o);
    }

    public static void toSmartDashboard(String value, String keyInDashboard) {
        SmartDashboard.putString(keyInDashboard, value);
    }

}
