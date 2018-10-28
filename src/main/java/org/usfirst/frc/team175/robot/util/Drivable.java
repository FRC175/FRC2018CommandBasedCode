package org.usfirst.frc.team175.robot.util;

/**
 * This interface provides commonly used methods in a class that utilizes a
 * Talon SRX motor controller.
 * 
 * @author Arvind
 */
@Deprecated
public interface Drivable {

	int getPosition();

	void setPower(double power);

	void setPosition(double position);

	void setBrakeMode(boolean on);

	void setPIDF(double kF, double kP, double kI, double kD);

	void resetEncoder();

}
