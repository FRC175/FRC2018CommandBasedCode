package org.usfirst.frc.team175.robot.util;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * TODO: Make this Factory as configurable as possible. Consider adding Builder class.
 *
 * @author Arvind
 */
public class TalonSRXFactory {

    // Prevent TalonSRXFactory from being instantiated
    private TalonSRXFactory() {
    }

    private static void configSRX(TalonSRX srx) {
        srx.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.PID_LOOP_INDEX, Constants.TIMEOUT_MS);
        srx.configNominalOutputForward(0, Constants.TIMEOUT_MS);
        srx.configNominalOutputReverse(0, Constants.TIMEOUT_MS);
        srx.configPeakOutputForward(1, Constants.TIMEOUT_MS);
        srx.configPeakOutputReverse(-1, Constants.TIMEOUT_MS);
        srx.configAllowableClosedloopError(0, Constants.SLOT_INDEX, Constants.TIMEOUT_MS);

        srx.setSelectedSensorPosition(0, Constants.PID_LOOP_INDEX, Constants.TIMEOUT_MS);
        srx.setNeutralMode(NeutralMode.Brake);
    }

    public static TalonSRX getSRX(int portNum, TalonSRXType type) {
        TalonSRX srx = null;

        switch (type) {
            case CROSS_THE_ROAD:
                srx = new TalonSRX(portNum);
                break;
            case WORCESTER:
                srx = new WPI_TalonSRX(portNum);
                break;
        }

        configSRX(srx);

        return srx;
    }

}
