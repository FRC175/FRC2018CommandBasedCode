package org.usfirst.frc.team175.robot.subsystems;

import org.usfirst.frc.team175.robot.Constants;
import org.usfirst.frc.team175.robot.RobotMap;
import org.usfirst.frc.team175.util.SRXSubsystem;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Arvind
 * @see SRXSubsystem.java
 */
public class Elevator extends SRXSubsystem {

	/* Declaration */
	// Enum
	public enum ElevatorPositions {
		POWER_CUBE_PICKUP(225),
		POWER_CUBE_LIFT(600),
		EXCHANGE(1926),
		SWITCH(12000),
		LOW_SCALE(25555),
		HIGH_SCALE(33050);

		private final double M_COUNTS;

		private ElevatorPositions(double counts) {
			M_COUNTS = counts;
		}

		public double getHeightInCounts() {
			return M_COUNTS;
		}
	}

	public Elevator(double kF, double kP, double kI, double kD) {
		/* Construct SRXSubsystem */
		super("Elevator", RobotMap.ELEVATOR_PORT, Constants.ELEVATOR_POSITION, Constants.K_ELEVATOR_SLOT_INDEX,
				Constants.K_ELEVATOR_PID_LOOP_INDEX, Constants.K_ELEVATOR_TIMEOUT_MS, kF, kP, kI, kD, true);

		// Current limiting
		configCurrentLimiting(20, 30, 100, true);
		
		// Soft limit restricting elevator positioning
		getSRX().configForwardSoftLimitThreshold((int) ElevatorPositions.HIGH_SCALE.getHeightInCounts(),
				0); // Limit at high scale position
		getSRX().configForwardSoftLimitEnable(false, 0); // Off
		getSRX().configReverseSoftLimitThreshold(0, 0); // Limit at zero position
		getSRX().configReverseSoftLimitEnable(false, 0); // Off
	}

	// TODO: Determine if upper limit is forward limit switch and vice versa
	public boolean isUpperLimitHit() {
		return getSRX().getSensorCollection().isFwdLimitSwitchClosed();
	}

	public boolean isLowerLimitHit() {
		return getSRX().getSensorCollection().isRevLimitSwitchClosed();
	}

	// TODO: Determine whether this should be integrated into zeroEncoders()
	public void zeroLimitSwitches() {
		getSRX().configSetParameter(ParamEnum.eClearPositionOnLimitF, 1, 0, 0, 10);
		getSRX().configSetParameter(ParamEnum.eClearPositionOnLimitR, 0, 0, 0, 10);
	}

	public void initDefaultCommand() {
		// TODO: Set the default command, if any, for a subsystem here. Example:
		// setDefaultCommand(new MySpecialCommand());
	}

}
