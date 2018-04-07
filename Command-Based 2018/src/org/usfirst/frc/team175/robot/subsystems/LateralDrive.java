package org.usfirst.frc.team175.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team175.robot.RobotMap;
import org.usfirst.frc.team175.robot.Constants;

/**
 * TODO: Make and set default command.
 */
public class LateralDrive extends SRXSubsystem {
	
    private TalonSRX mLateralDrive;

    // Solenoids
    // Solenoid(int CAN id of PCM, int channel)
    private Solenoid mDeploy;

    public LateralDrive(double kF, double kP, double kI, double kD) {
    	super(RobotMap.LATERAL_DRIVE_PORT, Constants.LATERAL_DRIVE_CLOSED_LOOP_TYPE, Constants.K_LATERAL_DRIVE_PID_LOOP_INDEX,
    			Constants.K_LATERAL_DRIVE_TIMEOUT_MS, kF, kP, kI, kD);
    	mDeploy = new Solenoid(RobotMap.LATERAL_DEPLOY_PORT, RobotMap.LATERAL_DEPLOY_CHANNEL);
    	
    	/* Instantiations */
        /*mLateralDrive = new TalonSRX(RobotMap.LATERAL_DRIVE_PORT);

        mDeploy = new Solenoid(RobotMap.LATERAL_DEPLOY_PORT, RobotMap.LATERAL_DEPLOY_CHANNEL);

         SRX Control 
        mLateralDrive.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.K_LATERAL_DRIVE_PID_LOOP_INDEX, Constants.K_LATERAL_DRIVE_TIMEOUT_MS);
        mLateralDrive.setSensorPhase(true);

        mLateralDrive.configNominalOutputForward(0, Constants.K_LATERAL_DRIVE_TIMEOUT_MS);
        mLateralDrive.configNominalOutputReverse(0, Constants.K_LATERAL_DRIVE_TIMEOUT_MS);
        mLateralDrive.configPeakOutputForward(1, Constants.K_LATERAL_DRIVE_TIMEOUT_MS);
        mLateralDrive.configPeakOutputReverse(-1, Constants.K_LATERAL_DRIVE_TIMEOUT_MS);

        mLateralDrive.configAllowableClosedloopError(0, Constants.K_LATERAL_DRIVE_PID_LOOP_INDEX, Constants.K_LATERAL_DRIVE_TIMEOUT_MS);

        mLateralDrive.config_kF(Constants.K_LATERAL_DRIVE_PID_LOOP_INDEX, kF, Constants.K_LATERAL_DRIVE_TIMEOUT_MS); // 0
        mLateralDrive.config_kP(Constants.K_LATERAL_DRIVE_PID_LOOP_INDEX, kP, Constants.K_LATERAL_DRIVE_TIMEOUT_MS); // 10
        mLateralDrive.config_kI(Constants.K_LATERAL_DRIVE_PID_LOOP_INDEX, kI, Constants.K_LATERAL_DRIVE_TIMEOUT_MS); // 0
        mLateralDrive.config_kD(Constants.K_LATERAL_DRIVE_PID_LOOP_INDEX, kD, Constants.K_LATERAL_DRIVE_TIMEOUT_MS); // 2

        mLateralDrive.setSelectedSensorPosition(Constants.LATERAL_DRIVE_CLOSED_LOOP_TYPE, Constants.K_LATERAL_DRIVE_PID_LOOP_INDEX, Constants.K_LATERAL_DRIVE_TIMEOUT_MS);

        mLateralDrive.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);*/
    }
    
    public void set(boolean value) {
    	mDeploy.set(value);
    }

    public boolean isEnabled() {
        return mDeploy.get();
    }
    
    /*public double getPosition() {
		return mLateralDrive.getSelectedSensorPosition(Constants.LATERAL_DRIVE_CLOSED_LOOP_TYPE);
	}
	
	public void powerDrive(double power) {
	    mLateralDrive.set(ControlMode.PercentOutput, power);
	}
	
	public void countsDrive(double counts) {
	    mLateralDrive.set(ControlMode.Position, counts);
	}
    
    public void zeroEncoder() {
    	mLateralDrive.setSelectedSensorPosition(0, Constants.K_LATERAL_DRIVE_PID_LOOP_INDEX, Constants.K_LATERAL_DRIVE_TIMEOUT_MS);
    }
    
    public void setPID(double kF, double kP, double kI, double kD) {
    	mLateralDrive.config_kF(Constants.K_LATERAL_DRIVE_PID_LOOP_INDEX, kF, Constants.K_LATERAL_DRIVE_TIMEOUT_MS);
        mLateralDrive.config_kP(Constants.K_LATERAL_DRIVE_PID_LOOP_INDEX, kP, Constants.K_LATERAL_DRIVE_TIMEOUT_MS);
        mLateralDrive.config_kI(Constants.K_LATERAL_DRIVE_PID_LOOP_INDEX, kI, Constants.K_LATERAL_DRIVE_TIMEOUT_MS);
        mLateralDrive.config_kD(Constants.K_LATERAL_DRIVE_PID_LOOP_INDEX, kD, Constants.K_LATERAL_DRIVE_TIMEOUT_MS);
    }*/

	@Override
	public void initDefaultCommand() {
	    // TODO: Set the default command, if any, for a subsystem here. Example:
	    //    setDefaultCommand(new MySpecialCommand());
	}
}

