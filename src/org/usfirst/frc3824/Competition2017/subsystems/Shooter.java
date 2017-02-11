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

import org.usfirst.frc3824.Competition2017.Constants;
import org.usfirst.frc3824.Competition2017.RobotMap;
import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends Subsystem
{
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final CANTalon shooterA = RobotMap.shooterShooterA;
    private final CANTalon shooterB = RobotMap.shooterShooterB;
    private final CANTalon feeder = RobotMap.shooterFeeder;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public boolean m_shooter_mode_PID = true;
    public boolean m_feeder_mode_PID = true;
    
    public double m_shooter_speed = Constants.DEFAULT_SHOOTER_SPEED;
    public double m_feeder_speed  = Constants.DEFAULT_FEEDER_SPEED;
    
    public Shooter()
	{
		// Set closed loop gains in slot0
		SmartDashboard.putNumber("Shooter P",        Constants.SHOOTER_P * 1000.0);
		SmartDashboard.putNumber("Shooter I",        Constants.SHOOTER_I * 1000.0);
		SmartDashboard.putNumber("Shooter D",        Constants.SHOOTER_D * 1000.0);
		SmartDashboard.putNumber("Shooter F",        Constants.SHOOTER_F * 1000.0);
		SmartDashboard.putNumber("Shooter Setpoint", Constants.DEFAULT_SHOOTER_SPEED);
		
		// Set closed loop gains in slot0
		SmartDashboard.putNumber("Feeder P",        Constants.FEEDER_P * 1000.0);
		SmartDashboard.putNumber("Feeder I",        Constants.FEEDER_I * 1000.0);
		SmartDashboard.putNumber("Feeder D",        Constants.FEEDER_D * 1000.0);
		SmartDashboard.putNumber("Feeder F",        Constants.FEEDER_F * 1000.0);
		SmartDashboard.putNumber("Feeder Setpoint", Constants.DEFAULT_FEEDER_SPEED);
		
		//********************************************************************
		// Configure the Shooter A Talon SRX
		//********************************************************************

		shooterA.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		shooterA.reverseSensor(true);

		// Set the peak and nominal outputs, 12V means full
		shooterA.configNominalOutputVoltage(0.0f, 0.0f);
		shooterA.configPeakOutputVoltage(12.0f, -12.0f);
		shooterA.reverseOutput(false);

		// Set the profile for the PID parameters
		shooterA.setProfile(0);
		shooterA.configEncoderCodesPerRev(4096);
		
		// Use the PID values 
		shooterA.setP(Constants.SHOOTER_P);
		shooterA.setI(Constants.SHOOTER_I);
		shooterA.setD(Constants.SHOOTER_D);
		shooterA.setF(Constants.SHOOTER_F);
		shooterA.changeControlMode(CANTalon.TalonControlMode.Speed);
		
		shooterA.disable();
		shooterA.set(Constants.DEFAULT_SHOOTER_SPEED);

		//********************************************************************
		// Configure the Shooter B Talon SRX
		//********************************************************************

		shooterB.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		shooterB.reverseSensor(true);

		// Set the peak and nominal outputs, 12V means full
		shooterB.configNominalOutputVoltage(0.0f, 0.0f);
		shooterB.configPeakOutputVoltage(12.0f, -12.0f);
		shooterB.reverseOutput(true);
		
		// Set the profile for the PID parameters
		shooterB.setProfile(0);
		shooterB.configEncoderCodesPerRev(4096);
		
		// Use the PID values 
		shooterB.setP(Constants.SHOOTER_P);
		shooterB.setI(Constants.SHOOTER_I);
		shooterB.setD(Constants.SHOOTER_D);
		shooterB.setF(Constants.SHOOTER_F);
		shooterB.changeControlMode(CANTalon.TalonControlMode.Speed);
	
		shooterB.disable();
		shooterB.set(Constants.DEFAULT_SHOOTER_SPEED);
		
		m_shooter_mode_PID = true;
		
		//********************************************************************
		// Configure the Feeder Talon SRX
		//********************************************************************
		feeder.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		feeder.reverseSensor(true);

		// Set the peak and nominal outputs, 12V means full
		feeder.configNominalOutputVoltage(0.0f, 0.0f);
		feeder.configPeakOutputVoltage(12.0f, -12.0f);
		feeder.reverseOutput(false);

		// Set the profile for the PID parameters
		feeder.setProfile(0);
		feeder.configEncoderCodesPerRev(4096);

		// Use the PID values from the SmartDashboard, which were just set
		feeder.setP(Constants.FEEDER_P);
		feeder.setI(Constants.FEEDER_I);
		feeder.setD(Constants.FEEDER_D);
		feeder.setF(Constants.FEEDER_F);
		
		feeder.disable();
		feeder.set(Constants.DEFAULT_FEEDER_SPEED);
		m_feeder_mode_PID = true;
	}

	public void initDefaultCommand()
	{
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	}

	/**
	 * Method to set the Shooter wheel speed
	 */
	public void setShooterSpeed(double speed)
	{
		if (m_shooter_mode_PID == false)
		{
			// Ensure the Talon is in PID speed mode
			shooterA.changeControlMode(CANTalon.TalonControlMode.Speed);
			shooterB.changeControlMode(CANTalon.TalonControlMode.Speed);

			m_shooter_mode_PID = true;
		}
		
		// Update the shooter speed
		m_shooter_speed = speed;
		 
		// Set the desired speed
		shooterA.set(m_shooter_speed);
		shooterB.set(m_shooter_speed);
	}
	
	public void jogShooterSpeed(double speedChange)
	{
		setShooterSpeed(m_shooter_speed + speedChange);
	}
	
	/**
	 * Method to set the Feeder speed
	 */
	public void setFeederSpeed(double speed)
	{
		if (m_feeder_mode_PID = false)
		{
			// Ensure the Talon is in PID speed mode
			feeder.changeControlMode(CANTalon.TalonControlMode.Speed);
			
			m_feeder_mode_PID = true;
		}
		
		// Update the feeder speed
		m_feeder_speed = speed;
		 
		// Set the desired speed
		feeder.set(m_feeder_speed);
	}

	/**
	 * Method to update the Shooter wheel speed
	 */
	public void updateShooterSpeed()
	{
		// Set the desired speed
		shooterA.set(m_shooter_speed);
		shooterB.set(m_shooter_speed);
	}
	
	/**
	 * Method to update the Feeder wheel speed
	 */
	public void updateFeederSpeed()
	{
		// Set the desired speed
		feeder.set(m_feeder_speed);
	}

	/**
	 * Method to enable the shooter PID controller
	 */
	public void enableShooterPIDs()
	{
		// Ensure the Talon is in PID speed mode
		shooterA.changeControlMode(CANTalon.TalonControlMode.Speed);
		shooterB.changeControlMode(CANTalon.TalonControlMode.Speed);
	
		// Enable both shooter PID controllers
		shooterA.enable();
		shooterB.enable();
	}
	
	/**
	 * Method to enable the feeder PID controller
	 */
	public void enableFeederPID()
	{
		// Ensure the Talon is in PID speed mode
		feeder.changeControlMode(CANTalon.TalonControlMode.Speed);
		
		// Enable the feeder PID controller
		feeder.enable();
	}

	/**
	 * Method to disable the Shooter PID controllers
	 */
	public void disableShooterPIDs()
	{
		// Disable both shooter PID controllers
		shooterA.disable();
		shooterB.disable();
	}
	
	/**
	 * Method to disable the Feeder PID controller
	 */
	public void disableFeederPID()
	{
		// Disable the feeder PID controller
		feeder.disable();
	}

	/**
	 * Method to set the Shooter PID parameters
	 */
	public void setShooterPID_Parameters(double F, double P, double I, double D)
	{
		// Ensure the Talon is in PID speed mode
		shooterA.changeControlMode(CANTalon.TalonControlMode.Speed);
		shooterB.changeControlMode(CANTalon.TalonControlMode.Speed);
	
		// Set the F, P, I and D parameters for shooter motor A PID controller
		shooterA.setF(F);
		shooterA.setP(P);
		shooterA.setI(I);
		shooterA.setD(D);
		
		// Set the F, P, I and D parameters for shooter motor B PID controller
		shooterB.setF(F);
		shooterB.setP(P);
		shooterB.setI(I);
		shooterB.setD(D);
	}

	/**
	 * Method to set the Feeder PID parameters
	 */
	public void setFeederPID_Parameters(double F, double P, double I, double D)
	{
		// Ensure the Talon is in PID speed mode
		feeder.changeControlMode(CANTalon.TalonControlMode.Speed);
		
		// Set the F, P, I and D parameters for the feeder PID controller
		feeder.setF(F);
		feeder.setP(P);
		feeder.setI(I);
		feeder.setD(D);
	}
	
	/**
	 * Method to read the shooter PID parameters from the SmartDashboard
	 */
	public void setShooterPID_ParametersFromSmartdashboard()
	{
		// Ensure the Talon is in PID speed mode
		shooterA.changeControlMode(CANTalon.TalonControlMode.Speed);
		shooterB.changeControlMode(CANTalon.TalonControlMode.Speed);
	
		// Update the shooter speed
		m_shooter_speed = SmartDashboard.getNumber("Shooter Setpoint", 0.0);
		
		// Read the F, P, I and D parameters for shooter motor A from the SmartDashboard
		// divide everything by 1000 so SmartDashboard numbers are not really small
		shooterA.setF(SmartDashboard.getNumber("Shooter F", 0.0) / 1000);
		shooterA.setP(SmartDashboard.getNumber("Shooter P", 0.0) / 1000);
		shooterA.setI(SmartDashboard.getNumber("Shooter I", 0.0) / 1000);
		shooterA.setD(SmartDashboard.getNumber("Shooter D", 0.0) / 1000);
		shooterA.set(m_shooter_speed);
		
		// Read the F, P, I and D parameters for shooter motor B from the SmartDashboard
		shooterB.setF(SmartDashboard.getNumber("Shooter F", 0.0) / 1000);
		shooterB.setP(SmartDashboard.getNumber("Shooter P", 0.0) / 1000);
		shooterB.setI(SmartDashboard.getNumber("Shooter I", 0.0) / 1000);
		shooterB.setD(SmartDashboard.getNumber("Shooter D", 0.0) / 1000);
		shooterB.set(m_shooter_speed);
	}

	/**
	 * Method to set the Feeder PID parameters from the SmartDashboard
	 */
	public void setFeederPID_ParametersFromSmartdashboard()
	{
		// Ensure the Talon is in PID speed mode
		feeder.changeControlMode(CANTalon.TalonControlMode.Speed);
		
		// Update the feeder speed
		m_feeder_speed = SmartDashboard.getNumber("Feeder Speed", 0.0);		
		
		// Read the F, P, I and D parameters for the feeder motor from the SmartDashboard
		// divide everything by 1000 so SmartDashboard numbers are not really small
		feeder.setF(SmartDashboard.getNumber("Feeder F", 0.0) / 1000);
		feeder.setP(SmartDashboard.getNumber("Feeder P", 0.0) / 1000);
		feeder.setI(SmartDashboard.getNumber("Feeder I", 0.0) / 1000);
		feeder.setD(SmartDashboard.getNumber("Feeder D", 0.0) / 1000);
		feeder.set(m_feeder_speed);
	}	
	
	/**
	 * Method to set the Shooter speed from the SmartDashboard
	 */
	public void setShooterSpeedFromSmartdashboard()
	{
		// Update the shooter speed
		m_shooter_speed = SmartDashboard.getNumber("Shooter Setpoint", 0.0);
		
		// Set the Shooter speed from the SmartDashboard
		setShooterSpeed(m_shooter_speed);
	}

	/**
	 * Method to set the Feeder speed from the SmartDashboard
	 */
	public void setFeederSpeedFromSmartdashboard()
	{
		// Update the feeder speed
		m_feeder_speed = SmartDashboard.getNumber("Feeder Setpoint", 0.0);		
		
		// Set the Feeder speed from the SmartDashboard
		setFeederSpeed(m_feeder_speed);
	}
	
	/**
	 * Method to set the Shooter Power in volts
	 */
	public void setShooterPower(double power)
	{
		// Set the Talon in voltage mode
		shooterA.changeControlMode(CANTalon.TalonControlMode.Voltage);
		shooterB.changeControlMode(CANTalon.TalonControlMode.Voltage);
		
		// Set the shooter outputs
		shooterA.set(power);
		shooterB.set(power);
		
		m_shooter_mode_PID = false;
	}
	
	/**
	 * Method to set the Feeder Power in volts
	 */
	public void setFeederPower(double power)
	{
		// Set the Talon in voltage mode
		feeder.changeControlMode(CANTalon.TalonControlMode.Voltage);
		feeder.set(power);
		
		m_feeder_mode_PID = false;
	}
	
	/**
	 * Method to return the Shooter A speed
	 */
	public double getShooterASpeed()
	{
		// Assume shooter A and shooter B are set to the same speed
		return shooterA.getSpeed();

	}

	/**
	 * Method to return the Shooter B speed
	 */
	public double getShooterBSpeed()
	{
		// Assume shooter A and shooter B are set to the same speed
		return shooterB.getSpeed();
	}
	
	/**
	 * Method to return the Feeder speed
	 */
	public double getFeederSpeed()
	{
		// Return the feeder speed
		return feeder.getSpeed();
	}
	
	/**
	 * Method to return the Shooter A error
	 */
	public double getShooterAError()
	{
		// Return the Shooter A PID error
		return shooterA.getError();
	}
	
	/**
	 * Method to return the Shooter B error
	 */
	public double getShooterBError()
	{
		// Return the Shooter B PID error
		return shooterB.getError();
	}
	
	/**
	 * Method to 
	 * @return
	 */
	public double getShooterASetpoint()
	{
		return shooterA.getSetpoint();
	}
}
