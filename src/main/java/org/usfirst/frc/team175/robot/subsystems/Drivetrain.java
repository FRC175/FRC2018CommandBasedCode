package org.usfirst.frc.team175.robot.subsystems;

import org.usfirst.frc.team175.robot.commands.teleop.JoystickArcadeDrive;
import org.usfirst.frc.team175.robot.util.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 * @author Arvind
 */
public class Drivetrain extends Subsystem implements Diagnosable {

    // Talon SRXs
    private TalonSRX mLeftMaster;
    private TalonSRX mRightMaster;
    private TalonSRX mLeftFollower;
    private TalonSRX mRightFollower;

    // Solenoid
    private Solenoid mShifter;

    // Gyro
    private ADXRS450_Gyro mGyro;

    // Other
    private DifferentialDrive mRobotDrive;

    // Singleton Instance
    private static Drivetrain sInstance;

    // Singleton Static Factory Method
    // TODO: Does reportError() need '+ e' after String
    public static Drivetrain getInstance() {
        if (sInstance == null) {
            try {
                sInstance = new Drivetrain();
            } catch (Exception e) {
                DriverStation.reportError("Drivetrain subsystem failed to instantiate.\n" + e, true);
            }
        }

        return sInstance;
    }

    private Drivetrain() {
        /* Instantiation */
        // TalonSRXFactory.getSRX(portNum : int, type : TalonSRXType)
        mLeftMaster = TalonSRXFactory.getSRX(Constants.LEFT_MASTER_PORT, TalonSRXType.WORCESTER);
        mRightMaster = TalonSRXFactory.getSRX(Constants.RIGHT_MASTER_PORT, TalonSRXType.WORCESTER);
        mLeftFollower = TalonSRXFactory.getSRX(Constants.LEFT_FOLLOWER_PORT, TalonSRXType.WORCESTER);
        mRightFollower = TalonSRXFactory.getSRX(Constants.RIGHT_FOLLOWER_PORT, TalonSRXType.WORCESTER);

        // Solenoid(canID : int, channel : int)
        mShifter = new Solenoid(Constants.SHIFT_PORT, Constants.SHIFT_CHANNEL);

        // Gyro
        mGyro = new ADXRS450_Gyro();

        // DifferentialDrive(rightMotorController : SpeedController, leftMotorController : SpeedController)
        mRobotDrive = new DifferentialDrive((WPI_TalonSRX) mRightMaster, (WPI_TalonSRX) mLeftMaster);

        /* General SRX Configuration */
        mLeftFollower.follow(mLeftMaster);
        mRightFollower.follow(mRightMaster);

        mLeftMaster.setInverted(false);
        mRightMaster.setInverted(false);
        mLeftFollower.setInverted(false);
        mRightFollower.setInverted(false);

        /* Left Master Configuration */
        mLeftMaster.setSensorPhase(true);
        TalonSRXController.setPIDF(mLeftMaster, 0, 0.12, 0, 0.0012);

        /* Right Master Configuration */
        mRightMaster.setSensorPhase(false);
        TalonSRXController.setPIDF(mRightMaster, 0, 0.08, 0, 0);
    }

    public void setHighGear(boolean enable) {
        mShifter.set(enable);
    }

    public boolean isCurrentGearHigh() {
        return false;
    }

    public void setPower(double leftPower, double rightPower) {
        TalonSRXController.setPower(mLeftMaster, leftPower);
        TalonSRXController.setPower(mRightMaster, rightPower);
    }

    public void setPosition(double leftPosition, double rightPosition) {
        TalonSRXController.setPosition(mLeftMaster, leftPosition);
        TalonSRXController.setPosition(mRightMaster, rightPosition);
    }

    public void arcadeDrive(double yAxis, double xAxis) {
        mRobotDrive.arcadeDrive(yAxis, xAxis);
    }

    public void tankDrive(double yAxis, double xAxis) {
        mRobotDrive.tankDrive(yAxis, xAxis);
    }

    public double getLeftPosition() {
        return TalonSRXController.getPosition(mLeftMaster);
    }

    public double getRightPosition() {
        return TalonSRXController.getPosition(mRightMaster);
    }

    public void setBrakeMode(boolean on) {
        TalonSRXController.setBrakeMode(mLeftMaster, on);
        TalonSRXController.setBrakeMode(mRightMaster, on);
    }

    public void setLeftPIDF(double kF, double kP, double kI, double kD) {
        TalonSRXController.setPIDF(mLeftMaster, kF, kP, kI, kD);
    }

    public void setRightPIDF(double kF, double kP, double kI, double kD) {
        TalonSRXController.setPIDF(mRightMaster, kF, kP, kI, kD);
    }

    public void resetEncoders() {
        TalonSRXController.resetEncoder(mLeftMaster);
        TalonSRXController.resetEncoder(mRightMaster);
    }

    public double getGyroAngle() {
        return mGyro.getAngle();
    }

    public void resetGyro() {
        mGyro.reset();
    }

    @Override
    public void outputToSmartDashboard() {
        SmartDashboard.putNumber("Left Position", getLeftPosition());
        SmartDashboard.putNumber("Left Voltage", mLeftMaster.getMotorOutputVoltage());
        SmartDashboard.putNumber("Left Current", mLeftMaster.getOutputCurrent());
        SmartDashboard.putNumber("Left Power", mLeftMaster.getMotorOutputPercent());

        SmartDashboard.putNumber("Right Position", getRightPosition());
        SmartDashboard.putNumber("Right Voltage", mRightMaster.getMotorOutputVoltage());
        SmartDashboard.putNumber("Right Current", mRightMaster.getOutputCurrent());
        SmartDashboard.putNumber("Right Power", mRightMaster.getMotorOutputPercent());

        SmartDashboard.putNumber("Gyro Angle", getGyroAngle());

        SmartDashboard.putBoolean("High gear?", isCurrentGearHigh());
    }

    @Override
    public boolean runSelfTest() {
        return false;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new JoystickArcadeDrive(false));
        // setDefaultCommand(new JoystickTankDrive());
    }

}
