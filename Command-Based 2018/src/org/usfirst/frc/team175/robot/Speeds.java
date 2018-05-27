package org.usfirst.frc.team175.robot;

/**
 * @author Arvind
 */
@Deprecated
public enum Speeds {
	
	REVERSE_FAST(-1),
	REVESRSE_MEDIUM_FAST(-0.75),
	REVERSE_MEDIUM(-0.5),
	REVERSE_LOW(-0.25),
	IDLE(0),
	FORWARD_LOW(0.25),
	FORWARD_MEDIUM(0.5),
	FORWARD_MEDIUM_FAST(0.75),
	FORWARD_FAST(1);
	
	private final double M_SPEED;
	
	private Speeds(double speed) {
		M_SPEED = speed;
	}
	
	public double getSpeed() {
		return M_SPEED;
	}

}
