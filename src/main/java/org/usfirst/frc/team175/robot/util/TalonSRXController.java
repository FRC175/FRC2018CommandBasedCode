package org.usfirst.frc.team175.robot.util;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * TODO: Possibly add option for arcade drive.
 *
 * @author Arvind
 */
public class TalonSRXController {

    // Prevent TalonSRXController from being instantiated
    private TalonSRXController() {
    }

    public static void setPIDF(TalonSRX srx, double kF, double kP, double kI, double kD) {
        srx.config_kF(Constants.SLOT_INDEX, kF, Constants.TIMEOUT_MS);
        srx.config_kP(Constants.SLOT_INDEX, kP, Constants.TIMEOUT_MS);
        srx.config_kI(Constants.SLOT_INDEX, kI, Constants.TIMEOUT_MS);
        srx.config_kD(Constants.SLOT_INDEX, kD, Constants.TIMEOUT_MS);
    }

    public static void setBrakeMode(TalonSRX srx, boolean enable) {
        srx.setNeutralMode(enable ? NeutralMode.Brake : NeutralMode.Coast);
    }

    public static void setPower(TalonSRX srx, double power) {
        srx.set(ControlMode.PercentOutput, power);
    }

    public static void setPosition(TalonSRX srx, double position) {
        srx.set(ControlMode.Position, position);
    }

    public static int getPosition(TalonSRX srx) {
        return srx.getSelectedSensorPosition(0);
    }

    public static boolean isForwardLimitHit(TalonSRX srx) {
        return srx.getSensorCollection().isFwdLimitSwitchClosed();
    }

    public static boolean isReverseLimitHit(TalonSRX srx) {
        return srx.getSensorCollection().isRevLimitSwitchClosed();
    }

    public static void resetLimits(TalonSRX srx, int forwardValue, int reverseValue) {
        srx.configSetParameter(ParamEnum.eClearPositionOnLimitF, forwardValue, 0, 0, 10);
        srx.configSetParameter(ParamEnum.eClearPositionOnLimitR, reverseValue, 0, 0, 10);
    }

    public static void resetEncoder(TalonSRX srx) {
        srx.setSelectedSensorPosition(0, Constants.PID_LOOP_INDEX, Constants.TIMEOUT_MS);
    }

    public static void configCurrentLimiting(TalonSRX srx, int current, int currentLimit, int currentDuration, boolean enable) {
        srx.configContinuousCurrentLimit(current, 0);
        srx.configPeakCurrentLimit(currentLimit, 0);
        srx.configPeakCurrentDuration(currentDuration, 0);
        srx.enableCurrentLimit(enable);
    }

    public static void configForwardSoftLimit(TalonSRX srx, int limitPosition, boolean enable) {
        srx.configForwardSoftLimitThreshold(limitPosition, Constants.TIMEOUT_MS);
        srx.configForwardSoftLimitEnable(false, Constants.TIMEOUT_MS);
    }

    public static void configReverseSoftLimit(TalonSRX srx, int limitPosition, boolean enable) {
        srx.configReverseSoftLimitThreshold(limitPosition, Constants.TIMEOUT_MS);
        srx.configReverseSoftLimitEnable(false, Constants.TIMEOUT_MS);
    }

}
