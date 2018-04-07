package org.usfirst.frc.team175.robot.subsystems;

import org.usfirst.frc.team175.robot.Constants;
import org.usfirst.frc.team175.robot.RobotMap;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * TODO: Finish programming subsystem.
 */
public class Elevator extends SRXSubsystem {

    private TalonSRX mElevator;
    
    public enum ElevatorPositions {
    	POWER_CUBE_PICKUP(-450),
    	POWER_CUBE_LIFT(-600),
    	EXCHANGE(-1926),
    	SWITCH(-12000),
    	LOW_SCALE(-25555),
    	HIGH_SCALE(-33050);
    	
    	private final double M_COUNTS;
    	
    	private ElevatorPositions(double counts) {
    		M_COUNTS = counts;
    	}
    	
    	public double getHeightInCounts() {
    		return M_COUNTS;
    	}
    }
    
    public Elevator(double kF, double kP, double kI, double kD) {
    	super(RobotMap.ELEVATOR_PORT, Constants.ELEVATOR_CLOSED_LOOP_TYPE, Constants.K_ELEVATOR_PID_LOOP_INDEX,
    			Constants.K_ELEVATOR_TIMEOUT_MS, kF, kP, kI, kD);
    	
    	// Current limiting 
        mElevator.configContinuousCurrentLimit(19, 0); // set current
        mElevator.configPeakCurrentLimit(25, 0); // current limit 
        mElevator.configPeakCurrentDuration(100, 0); // how long you can pass the limit before pushed to default
        mElevator.enableCurrentLimit(true); // on
        
        // Soft limit restricting elevator positioning 
        mElevator.configForwardSoftLimitThreshold(0, 0);
        mElevator.configReverseSoftLimitThreshold(-30000, 0);
        mElevator.configForwardSoftLimitEnable(false, 0); // off
        mElevator.configReverseSoftLimitEnable(false, 0); // off
    	
    	/* Instantiations */
    	/* mElevator = new TalonSRX(RobotMap.ELEVATOR_PORT); */
    	
    	/* SRX Configuration */
    	/* mElevator.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.K_ELEVATOR_PID_LOOP_INDEX, Constants.K_ELEVATOR_TIMEOUT_MS);
    	mElevator.setSensorPhase(true);
    	
    	mElevator.configNominalOutputForward(0, Constants.K_ELEVATOR_TIMEOUT_MS);
    	mElevator.configNominalOutputReverse(0, Constants.K_ELEVATOR_TIMEOUT_MS);
    	mElevator.configPeakOutputForward(1, Constants.K_ELEVATOR_TIMEOUT_MS);
    	mElevator.configPeakOutputReverse(-1, Constants.K_ELEVATOR_TIMEOUT_MS);

    	mElevator.configAllowableClosedloopError(0, Constants.K_ELEVATOR_PID_LOOP_INDEX, Constants.K_ELEVATOR_TIMEOUT_MS);

    	mElevator.config_kF(Constants.K_ELEVATOR_PID_LOOP_INDEX, kF, Constants.K_ELEVATOR_TIMEOUT_MS); // 0
    	mElevator.config_kP(Constants.K_ELEVATOR_PID_LOOP_INDEX, kP, Constants.K_ELEVATOR_TIMEOUT_MS); // 10
    	mElevator.config_kI(Constants.K_ELEVATOR_PID_LOOP_INDEX, kI, Constants.K_ELEVATOR_TIMEOUT_MS); // 0
    	mElevator.config_kD(Constants.K_ELEVATOR_PID_LOOP_INDEX, kD, Constants.K_ELEVATOR_TIMEOUT_MS); // 2

    	mElevator.setSelectedSensorPosition(Constants.ELEVATOR_CLOSED_LOOP_TYPE, Constants.K_ELEVATOR_PID_LOOP_INDEX, Constants.K_ELEVATOR_TIMEOUT_MS); */    
	}

    // TODO: Determine if upper limit if forward limit switch and vice versa
    public boolean isUpperLimitHit() {
    	return mElevator.getSensorCollection().isFwdLimitSwitchClosed();
    }
    
    public boolean isLowerLimitHit() {
    	return mElevator.getSensorCollection().isRevLimitSwitchClosed();
    }
    
    public void countsDrive(ElevatorPositions elevatorPosition) {
    	countsDrive(elevatorPosition.getHeightInCounts());
    }
    
    // TODO: Determine whether this should be integrated into zeroEncoders()
    public void zeroLimitSwitches() {
    	mElevator.configSetParameter(ParamEnum.eClearPositionOnLimitF, 1, 0, 0, 10);
        mElevator.configSetParameter(ParamEnum.eClearPositionOnLimitR, 0, 0, 0, 10);
    }
    
    /*public double getPosition() {
    	return mElevator.getSelectedSensorPosition(Constants.ELEVATOR_CLOSED_LOOP_TYPE);
    }
    
    public void powerDrive(double power) {
    	mElevator.set(ControlMode.PercentOutput, power);
    }
	
    public void countsDrive(double counts) {
    	mElevator.set(ControlMode.Position, counts);
    }
    
    public void setPID(double kF, double kP, double kI, double kD) {
    	mElevator.config_kF(Constants.K_ELEVATOR_PID_LOOP_INDEX, kF, Constants.K_ELEVATOR_TIMEOUT_MS);
    	mElevator.config_kP(Constants.K_ELEVATOR_PID_LOOP_INDEX, kP, Constants.K_ELEVATOR_TIMEOUT_MS);
    	mElevator.config_kI(Constants.K_ELEVATOR_PID_LOOP_INDEX, kI, Constants.K_ELEVATOR_TIMEOUT_MS);
    	mElevator.config_kD(Constants.K_ELEVATOR_PID_LOOP_INDEX, kD, Constants.K_ELEVATOR_TIMEOUT_MS);
    }
    
    public void zeroEncoder() {
    	mElevator.setSelectedSensorPosition(0, Constants.K_ELEVATOR_PID_LOOP_INDEX, Constants.K_ELEVATOR_TIMEOUT_MS);
    }*/
    
    
    public void initDefaultCommand() {
        // TODO: Set the default command, if any, for a subsystem here. Example:
        //		setDefaultCommand(new MySpecialCommand());
    }

}
