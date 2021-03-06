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

import org.usfirst.frc3824.Competition2017.Constants;
import org.usfirst.frc3824.Competition2017.Robot;

/**
 *
 */
public class ClimberControl extends Command
{
	boolean m_atTop;
	boolean m_limitSwitchActivated = false;
	
	Timer m_Timer;

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
	public ClimberControl()
	{
		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.climber);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        
        // Instantiate a timer
        m_Timer = new Timer();
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		// Initialize to not at top of rope
		m_atTop = false;
		
		// Reset and start the timer
		m_Timer.reset();
		m_Timer.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{		
		// Determine if the climb limit switch is activated
		if (Robot.climber.getClimberLimitSwitch() == true)
		{
			// Limit switch active so must be at the top of the rope
			// Determine if first time switch activation
			if (m_limitSwitchActivated == false)
			{
				// Indicate that the switch activation have been processed
				m_limitSwitchActivated = true;
				
				// Reset the timer
				m_Timer.reset();
			}
			else
			{
				// Allow the motor to runs just past the switch activation
				if (m_Timer.get() > Constants.CLIMBER_AFTER_LIMIT_SWITCH_TIME)
					m_atTop = true;	
			}
		}
		
		// Set the climber motor		
		if (Robot.climber.getClimberCurrent() > Constants.CLIMBER_STOP_CURRENT)
		{
			// High current so must be at the top of the rope
			m_atTop = true;
		}
		
		// Determine if the robot is at the top
		if (m_atTop == true)
		{
			// Hold the robot at the present location
			Robot.climber.climbControl(Constants.CLIMBER_SPEED_HOLD);
		} 
		else 
		{
			// Continue climbing
			Robot.climber.climbControl(Constants.CLIMBER_SPEED_FAST);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		// Return finished when Climber Button Auto is not on
		return !Robot.oi.controllerJoystick.getRawButton(Constants.CLIMBER_BUTTON_AUTO);
	}

	// Called once after isFinished returns true
	protected void end()
	{
		// Stop the climber motors
		Robot.climber.climbControl(Constants.CLIMBER_SPEED_HOLD);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		this.end();
	}
}
