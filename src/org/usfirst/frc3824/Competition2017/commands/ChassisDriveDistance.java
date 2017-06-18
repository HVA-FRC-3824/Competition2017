// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc3824.Competition2017.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3824.Competition2017.Robot;

/**
 *
 */
public class ChassisDriveDistance extends Command
{
	private Timer	m_Timer;

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
	private double	m_Distance;
	private double	m_Power;
	private boolean	m_HighGear;

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
	public ChassisDriveDistance(double Distance, double Power, boolean HighGear)
	{
		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
		m_Distance = Distance;
		m_Power    = Power;
		m_HighGear = HighGear;

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.chassis);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

		// instantiate a timer
		m_Timer = new Timer();
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		System.out.println("Drive Straight Init: " + m_Power);
		
		// Set the PID up for driving straight
		Robot.chassis.driveStraightPID(m_Power, m_HighGear);

		// reset and start the timer
		m_Timer.reset();
		m_Timer.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		// Slow down when reaching the desired position
		if (Math.abs(m_Distance - Robot.chassis.getEncoderDistance()) < 7.0)
		{
			System.out.println("Change Magnitude");
			Robot.chassis.updateMagnitude(-0.2);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		// Determine if the robot has reached the specified distance
		if (m_Timer.get() > 5.0)
		{
			// fail after 5.0 seconds
			return true;
		}

		// Determine if the robot has reached the desired distance
		// Note: Use absolute values since the distance can be negative
		return Math.abs(Robot.chassis.getEncoderDistance()) > Math.abs(m_Distance);
	}

	// Called once after isFinished returns true
	protected void end()
	{
		// Stop the chassis controllers and reset the encoders
		Robot.chassis.resetChassisPIDcontrollersAndEncoders();
		m_Timer.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		end();
	}
}
