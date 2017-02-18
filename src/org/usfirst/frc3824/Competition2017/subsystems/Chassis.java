// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc3824.Competition2017.subsystems;

import org.usfirst.frc3824.Competition2017.Robot;
import org.usfirst.frc3824.Competition2017.Constants;
import org.usfirst.frc3824.Competition2017.RobotMap;
import org.usfirst.frc3824.Competition2017.Target;
import org.usfirst.frc3824.Competition2017.commands.*;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The Chassis class contains all the methods and members for the Chassis subassembly
 */
public class Chassis extends Subsystem
{
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController driveLeft = RobotMap.chassisDriveLeft;
    private final SpeedController driveRight = RobotMap.chassisDriveRight;
    private final RobotDrive robotDrive = RobotMap.chassisRobotDrive;
    private final AnalogInput ultrasound = RobotMap.chassisUltrasound;
    private final AnalogGyro gyro = RobotMap.chassisGyro;
    private final Encoder encoderLeft = RobotMap.chassisEncoderLeft;
    private final Encoder encoderRight = RobotMap.chassisEncoderRight;
    private final Solenoid transmission = RobotMap.chassisTransmission;
    private final Compressor compressor = RobotMap.chassisCompressor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	// Parameter used for drive while running under PID Control. The values
	// not set by the controller constructor can be set by a command directly
	private double					m_magnitude;
	private boolean					m_highGear;

	// PID controller for driving based on Gyro
	private PIDController angleGyroPID = new PIDController(Constants.DRIVETRAIN_DRIVE_STRAIGHT_P,
														   Constants.DRIVETRAIN_DRIVE_STRAIGHT_I, 
                                                           Constants.DRIVETRAIN_DRIVE_STRAIGHT_D, 
                                                           gyro, new AnglePIDOutput()
    );
	
	private PIDController angleEncoderPID_Right = new PIDController(Constants.IMAGE_ANGLE_ENCODER_P,
                                                                    Constants.IMAGE_ANGLE_ENCODER_I, 
                                                                    Constants.IMAGE_ANGLE_ENCODER_D, 
                                                                    encoderRight, 
                                                                    new EncoderPIDOutputRight());

	private PIDController angleEncoderPID_Left = new PIDController(Constants.IMAGE_ANGLE_ENCODER_P,
                                                                   Constants.IMAGE_ANGLE_ENCODER_I, 
                                                                   Constants.IMAGE_ANGLE_ENCODER_D, 
                                                                   encoderLeft,
                                                                   new EncoderPIDOutputLeft());
	
	public Chassis() 
	{
		// Configure angleEncoderPIDs
		
		SmartDashboard.putNumber("angleEncoderPID P", angleEncoderPID_Left.getP());
		SmartDashboard.putNumber("angleEncoderPID I", angleEncoderPID_Left.getI());
		SmartDashboard.putNumber("angleEncoderPID D", angleEncoderPID_Left.getD());

		// Set the encoder input value range
		angleEncoderPID_Left.setInputRange(Constants.IMAGE_ANGLE_MINIMUM_INPUT, Constants.IMAGE_ANGLE_MAXIMUM_INPUT);
		angleEncoderPID_Right.setInputRange(Constants.IMAGE_ANGLE_MINIMUM_INPUT, Constants.IMAGE_ANGLE_MAXIMUM_INPUT);

		// Set the encoder output range
		angleEncoderPID_Left.setOutputRange(Constants.IMAGE_ANGLE_MINIMUM_OUTPUT, Constants.IMAGE_ANGLE_MAXIMUM_OUTPUT);
		angleEncoderPID_Right.setOutputRange(Constants.IMAGE_ANGLE_MINIMUM_OUTPUT, Constants.IMAGE_ANGLE_MAXIMUM_OUTPUT);
	}
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand()
	{
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new TeleopDrive());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	// ************************************
	// Methods to call from commands
	// ************************************

	/*
	 * Method to stop the chassis drive motors and disable/reset pids
	 */
	public void resetChassisPIDcontrollersAndEncoders()
	{
		// Initialize the gyro PID controller
		angleGyroPID.disable();
		angleGyroPID.reset();
		angleEncoderPID_Right.disable();
		angleEncoderPID_Right.reset();
		angleEncoderPID_Left.disable();
		angleEncoderPID_Left.reset();
		
		gyro.reset();
		
		m_magnitude = 0;
		
		// Reset the drive encoders
		encoderLeft.reset();
		encoderRight.reset();
		
		// Ensure the robot is stopped
		Robot.chassis.robotDrive.arcadeDrive(0, 0);
	}

	/**
	 * Method to shift the drive train
	 */
	public void shiftGear(boolean gearHigh)
	{
		// Control the gear shift piston
		transmission.set(gearHigh);
		m_highGear = gearHigh;
	}

	/**
	 * Method to set the transmission to high gear
	 */
	public void setGearHigh()
	{
		// Set the transmission to high gear
		m_highGear = true;
		transmission.set(m_highGear);
	}

	/**
	 * Method to set the transmission to low gear
	 */
	public void setGearLow()
	{
		// Set the transmission to low gear
		m_highGear = false;
		transmission.set(m_highGear);
	}

	/**
	 * Method to return the state of the transmission
	 * @return
	 */
	public boolean isGearHigh()
	{
		return m_highGear;
	}

	/**
	 * Method to control the drive through the specified joystick
	 */
	public void driveWithJoystick(Joystick stick)
	{
		// Cube twist to decrease sensitivity
		double twist = stick.getTwist();
		twist = Constants.TWIST_MULTIPLIER * twist * twist * twist;

		// Square forward/backward to decrease sensitivity
		double moveValue = stick.getY();
		if (moveValue < 0)
		{
			// remember to preserve direction, it is lost when squaring
			moveValue = -1.0 * (moveValue * moveValue);
		} 
		else
		{
			moveValue = moveValue * moveValue;
		}

		// Drive with arcade control
		Robot.chassis.robotDrive.arcadeDrive(moveValue, twist);
	}

	/**
	 * Method to configure the gyro based turn/drive straight PID controller
	 */
	public void driveStraightPID(double power, boolean highGear)
	{
		// update the drive power
		m_magnitude = power;
		
		// Set the transmission to the specified gear
		if (highGear == true)
		{
			setGearHigh();
		} 
		else
		{
			setGearLow();
		}

		// Drive straight means keep current heading
		startGyroPID(Constants.DRIVETRAIN_DRIVE_STRAIGHT_P, Constants.DRIVETRAIN_DRIVE_STRAIGHT_I,
				     Constants.DRIVETRAIN_DRIVE_STRAIGHT_D, Constants.DRIVETRAIN_DRIVE_MINIMUM_OUTPUT,
				     Constants.DRIVETRAIN_DRIVE_MAXIMUM_OUTPUT,	getCurrentHeading());
	}

	/**
	 * Method to configure the gyro based turn/drive straight PID controller
	 */
	public void turnAnglePID(double desiredHeading, double power, boolean highGear)
	{
		// update the drive power
		m_magnitude = power;
		
		// Set the transmission to the specified gear
		if (highGear == true)
		{
			setGearHigh();
		} 
		else
		{
			setGearLow();
		}

		// Turn to the desired heading current heading
		startGyroPID(Constants.DRIVETRAIN_DRIVE_STRAIGHT_P, Constants.DRIVETRAIN_DRIVE_STRAIGHT_I,
					 Constants.DRIVETRAIN_DRIVE_STRAIGHT_D, Constants.DRIVETRAIN_DRIVE_MINIMUM_OUTPUT,
					 Constants.DRIVETRAIN_DRIVE_MAXIMUM_OUTPUT, desiredHeading);
	}

	/**
	 * method to enable the encoderPID
	 * @param desiredEncoderValue
	 */
	public void encoderPID(double desiredEncoderValue)
	{
		// reset other PIDS
		resetChassisPIDcontrollersAndEncoders();
		
		updateEncoderSetpoint(desiredEncoderValue);

		angleEncoderPID_Left.enable();
		angleEncoderPID_Right.enable();
	}
	
	/**
	 * Method to update the encoder PID target value (heading)
	 */
	public void updateEncoderSetpoint(double desiredEncoderValue)
	{
		angleEncoderPID_Left.setSetpoint(-desiredEncoderValue);
		angleEncoderPID_Right.setSetpoint(desiredEncoderValue);
	}
	
	public void updateEncoderSetpointWithTarget(Target target)
	{
		double encoderPosition = Robot.chassis.getEncoderSetpoint();

		double deviationFromTarget = target.deviationFromTarget();

		if (deviationFromTarget < -Constants.DEVIATION_FROM_TARGET)
		{
			encoderPosition += Constants.IMAGE_ANGLE_JOG_DISTANCE;
		} 
		else if (deviationFromTarget > Constants.DEVIATION_FROM_TARGET)
		{
			encoderPosition -= Constants.IMAGE_ANGLE_JOG_DISTANCE;
		}

		// if the deviation is really large, jog the encoder position a second
		// time
		if (deviationFromTarget < -2 * Constants.DEVIATION_FROM_TARGET)
		{
			encoderPosition += Constants.IMAGE_ANGLE_JOG_DISTANCE;
		} 
		else if (deviationFromTarget > 2 * Constants.DEVIATION_FROM_TARGET)
		{
			encoderPosition -= Constants.IMAGE_ANGLE_JOG_DISTANCE;
		}

		updateEncoderSetpoint(encoderPosition);
	}

	/**
	 * Method to get the encoder PID target value (heading)
	 */
	public double getEncoderSetpoint()
	{
		return angleEncoderPID_Right.getSetpoint();
	}
	
	/**
	 * 
	 * Method to get the encoder PID error
	 */
	public double getLeftEncoderPIDError()
	{
		return angleEncoderPID_Left.getError();
	}
	
	/**
	 * 
	 * Method to get the encoder PID error
	 */
	public double getRightEncoderPIDError()
	{
		// Return the Right set point
		// Note: The Left set point should just be the negative of the Left
		return angleEncoderPID_Right.getError();
	}
	
	public void setEncoderPID_ParametersFromSmartdashboard() {
		double p = SmartDashboard.getNumber("angleEncoderPID P", angleEncoderPID_Left.getP());
		double i = SmartDashboard.getNumber("angleEncoderPID I", angleEncoderPID_Left.getI());
		double d = SmartDashboard.getNumber("angleEncoderPID D", angleEncoderPID_Left.getD());
		
		angleEncoderPID_Left.setPID(p, i, d);
		angleEncoderPID_Right.setPID(p, i, d);
	}
	
	/**
	 * Method to update output power while under PID control
	 * ie. after startGyroPID() is called
	 * 
	 * @param magnitude
	 */
	public void updateMagnitude(double magnitude)
	{
		// Update the drive magnitude
		m_magnitude = magnitude;
	}

	// ************************************
	// Methods to get values from chassis sensors
	// ************************************

	/**
	 * Method to return the present gyro angle
	 */
	public double getCurrentHeading()
	{
		// Return the present gyro heading
		return gyro.getAngle();
	}

	/**
	 * Method to determine if the gyro angle is within the specified range
	 */
	public boolean gyroWithin(double threshold)
	{
		// Return if the gyro is within the specified range
		return angleGyroPID.getError() < threshold;
	}

	/**
	 * Method to return the maximum of the two chassas wheel encoders
	 */
	public double getEncoderDistance()
	{
		// Return the maximum encoder distance in case the other is not working
		return Math.max(encoderLeft.getDistance(), encoderRight.getDistance());
	}

	/**
	 * Compute the distance based on the ultrasonic sensor
	 */
	public double getUltrasonicDistance()
	{
		// Return the distance in inches
		return ((Constants.ULTRASONIC_Y2 - Constants.ULTRASONIC_Y1) / 
				(Constants.ULTRASONIC_X2 - Constants.ULTRASONIC_X1)) *
				(ultrasound.getVoltage() - Constants.ULTRASONIC_X1) + Constants.ULTRASONIC_Y1;
	}

	// ************************************
	// Private helpers
	// ************************************

	/**
	 * set chassis to be under PID control
	 * 
	 * @param P
	 * @param I
	 * @param D
	 * @param minimumOutput
	 * @param maximumOutput
	 * @param tolerance
	 * @param desiredHeading
	 *            (relative to current heading, 0 is keep current heading)
	 */
	private void startGyroPID(double P, double I, double D, 
			                  double minimumOutput, double maximumOutput, double desiredHeading)
	{
		// reset other PIDS
		resetChassisPIDcontrollersAndEncoders();

		// Initialize the Gryo angle PID parameters
		angleGyroPID.setPID(P, I, D);

		// Set the desired chassis heading
		angleGyroPID.setSetpoint(desiredHeading);

		// Limit the output power when turning
		angleGyroPID.setOutputRange(minimumOutput, maximumOutput);

		// Enable the Gyro PID
		angleGyroPID.enable();
	}

	/**
	 * Class declaration for the PIDOutput
	 */
	private class AnglePIDOutput implements PIDOutput
	{
		/**
		 * Virtual function to receive the PID output and set the drive direction
		 */
		public void pidWrite(double PIDoutput)
		{
			// Drive the robot given the speed and direction
			// Note: The Arcade drive expects a joystick which is negative forward
			robotDrive.arcadeDrive(-m_magnitude, PIDoutput, false);
		}
	}
	
	/**
	 * Class declaration for the PIDOutput
	 */
	public class EncoderPIDOutputRight implements PIDOutput
	{		
		/**
		 * Virtual function to receive the PID output and set the drive direction 
		 */
		public void pidWrite(double PIDoutput)
		{
			driveRight.set(PIDoutput + m_magnitude);
			
			SmartDashboard.putNumber("Right Output", PIDoutput);
		}
	}

	/**
	 * Class declaration for the PIDOutput
	 */
	public class EncoderPIDOutputLeft implements PIDOutput
	{
		/**
		 * Virtual function to receive the PID output and set the drive direction 
		 */
		public void pidWrite(double PIDoutput)
		{
			driveLeft.set(-PIDoutput + m_magnitude);
			
			SmartDashboard.putNumber("Left Output", -PIDoutput);
		}
	}
}
