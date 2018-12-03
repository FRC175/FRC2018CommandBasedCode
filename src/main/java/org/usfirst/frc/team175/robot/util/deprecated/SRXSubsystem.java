package org.usfirst.frc.team175.robot.util.deprecated;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An object that allows for the creation of a subsystem that has implementation
 * for TalonSRX specific features.
 *
 * @author Arvind
 * @see Drivable
 */
@Deprecated
public abstract class SRXSubsystem extends Subsystem implements Drivable {

    // Talon SRX
    private BuzzTalonSRX mSRX;

    /**
     * Constructs an subsystem that utilizes a TalonSRX Motor Controller.
     *
     * @param srxPort
     *         The port that the SRX is plugged into
     * @param isInverted
     *         Whether or not encoder counts are inverted (if forward is negative
     *         and reverse is positive)
     * @param sensorPhase
     *         Whether to invert the phase of the sensor
     * @param kF
     *         Feed forward coefficient
     * @param kP
     *         Proportional coefficient
     * @param kI
     *         Integral coefficient
     * @param kD
     *         Derivative coefficient
     */
    public SRXSubsystem(int srxPort, boolean isInverted, boolean sensorPhase, double kF, double kP, double kI,
                        double kD) {
        /* Instantiation */
        mSRX = new BuzzTalonSRX(srxPort);

        /* SRX Configuration */
        mSRX.setInverted(isInverted);
        mSRX.setSensorPhase(sensorPhase);
        mSRX.setNeutralMode(false);

        /* PIDF Configuration */
        mSRX.config_kF(kF);
        mSRX.config_kP(kP);
        mSRX.config_kI(kI);
        mSRX.config_kD(kD);
    }

    /**
     * Gives the current position on the SRX (in encoder counts).
     *
     * @return The current encoder count on the SRX
     */
    @Override
    public int getPosition() {
        return mSRX.getSelectedSensorPosition();
    }

    /**
     * Sets the power of the motor to a certain percent.
     *
     * @param power
     *         The motor power
     */
    @Override
    public void setPower(double power) {
        mSRX.set(ControlMode.PercentOutput, power);
    }

    /**
     * Makes the motor move to a certain position using SRX encoder counts.
     *
     * @param position
     *         Position for the SRX to reach
     */
    @Override
    public void setPosition(double position) {
        mSRX.set(ControlMode.Position, position);
    }

    /**
     * Changes the PID values on the SRX.
     *
     * @param kF
     *         Feed forward coefficient
     * @param kP
     *         Proportional coefficient
     * @param kI
     *         Integral coefficient
     * @param kD
     *         Derivative coefficient
     */
    @Override
    public void setPIDF(double kF, double kP, double kI, double kD) {
        mSRX.config_kF(kF);
        mSRX.config_kP(kP);
        mSRX.config_kI(kI);
        mSRX.config_kD(kD);
    }

    /**
     * Resets the encoder on the SRX to 0 counts.
     */
    @Override
    public void resetEncoder() {
        mSRX.setSelectedSensorPosition(0);
    }

    /**
     * Enables or disables brake mode.
     *
     * @param on
     *         Whether or not to enable brake mode
     */
    @Override
    public void setBrakeMode(boolean on) {
        mSRX.setNeutralMode(on ? NeutralMode.Brake : NeutralMode.Coast);
    }

    /**
     * Initializes the default command.
     */
    protected abstract void initDefaultCommand();

}
