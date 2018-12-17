package org.usfirst.frc.team175.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Solenoid;

import org.usfirst.frc.team175.robot.util.Constants;
import org.usfirst.frc.team175.robot.util.DiagnosableSubsystem;
import org.usfirst.frc.team175.robot.util.TalonSRXController;
import org.usfirst.frc.team175.robot.util.TalonSRXFactory;
import org.usfirst.frc.team175.robot.util.TalonSRXType;

/**
 * @author Arvind
 */
public class LateralDrive extends DiagnosableSubsystem {

    // Talon SRX
    private TalonSRX mLateralDrive;

    // Solenoid
    private Solenoid mDeploy;

    // Singleton Instance
    private static LateralDrive sInstance;

    // Singleton Static Factory Method
    public static LateralDrive getInstance() {
        if (sInstance == null) {
            try {
                sInstance = new LateralDrive();
            } catch (Exception e) {
                DriverStation.reportError("Lateral drivetrain subsystem failed to instantiate.\n" + e, true);
            }
        }

        return sInstance;
    }

    private LateralDrive() {
        /* Instantiations */
        // TalonSRXFactory.getSRX(portNum : int, type : TalonSRXType)
        mLateralDrive = TalonSRXFactory.getSRX(Constants.LATERAL_DRIVE_PORT, TalonSRXType.CROSS_THE_ROAD);

        // Solenoid(canID : int, channel : int)
        mDeploy = new Solenoid(Constants.LATERAL_DEPLOY_PORT, Constants.LATERAL_DEPLOY_CHANNEL);

        /* SRX Configuration */
        mLateralDrive.setInverted(false);
        mLateralDrive.setSensorPhase(true);
        TalonSRXController.setPIDF(mLateralDrive, 0, 10, 0, 2);
    }

    public void set(boolean enable) {
        if (super.getSubsystemState()) {
            mDeploy.set(enable);
        }
    }

    public boolean isEnabled() {
        return mDeploy.get();
    }

    public void setPower(double power) {
        if (super.getSubsystemState()) {
            TalonSRXController.setPower(mLateralDrive, power);
        }
    }

    public void setPosition(double position) {
        if (super.getSubsystemState()) {
            TalonSRXController.setPosition(mLateralDrive, position);
        }
    }

    public double getPosition() {
        return TalonSRXController.getPosition(mLateralDrive);
    }

    // TODO: Determine if this necessary
    public void setBrakeMode(boolean on) {
        if (super.getSubsystemState()) {
            TalonSRXController.setBrakeMode(mLateralDrive, on);
        }
    }

    public void setPIDF(double kF, double kP, double kI, double kD) {
        TalonSRXController.setPIDF(mLateralDrive, kF, kP, kI, kD);
    }

    public void resetEncoder() {
        TalonSRXController.resetEncoder(mLateralDrive);
    }

    @Override
    public void outputToSmartDashboard() {
    }

    @Override
    public boolean runSelfTest() {
        return false;
    }

    @Override
    protected void initDefaultCommand() {
        // setDefaultCommand();
    }

}