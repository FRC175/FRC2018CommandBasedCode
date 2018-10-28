package org.usfirst.frc.team175.robot.util;

/**
 * @author Arvind
 */
public class PIDFValue {

    private double mKf;
    private double mKp;
    private double mKi;
    private double mKd;

    public PIDFValue(double kF, double kP, double kI, double kD) {
        mKf = kF;
        mKp = kP;
        mKi = kI;
        mKd = kD;
    }

    public double getKf() {
        return mKf;
    }

    public double getKp() {
        return mKp;
    }

    public double getKi() {
        return mKi;
    }

    public double getKd() {
        return mKd;
    }

}
