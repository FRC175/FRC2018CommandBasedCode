package org.usfirst.frc.team175.util;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

public class TwoButton extends Button {

	private GenericHID mJoystick;
	private int mFirstButtonNumber;
	private int mSecondButtonNumber;
	
	public TwoButton(GenericHID joystick, int firstButtonNumber, int secondButtonNumber) {
		mJoystick = joystick;
		mFirstButtonNumber = firstButtonNumber;
		mSecondButtonNumber = secondButtonNumber;
	}
	
	@Override
	public boolean get() {
		return mJoystick.getRawButton(mFirstButtonNumber) && mJoystick.getRawButton(mSecondButtonNumber);
	}

}
