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

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc3824.Competition2017.Constants;
import org.usfirst.frc3824.Competition2017.Robot;

/**
 *
 */
public class ClimberManualClimb extends Command
{
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
	public ClimberManualClimb()
	{
		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.climber);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		if (Robot.oi.controllerJoystick.getRawButton(Constants.CLIMBER_BUTTON_AUTO))
		{
			// Auto switch on, don't do anything
			return;
		}
		
		if (Robot.oi.controllerJoystick.getRawButton(Constants.CLIMBER_BUTTON_FAST))
		{
			Robot.climber.climbControl(Constants.CLIMBER_SPEED_FAST);
		} 
		else if (Robot.oi.controllerJoystick.getRawButton(Constants.CLIMBER_BUTTON_SLOW))
		{
			// switch off, middle "slow" position
			Robot.climber.climbControl(Constants.CLIMBER_SPEED_SLOW);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		// finished when auto button is on
		return false;
	}

	// Called once after isFinished returns true
	protected void end()
	{
		if (Robot.oi.controllerJoystick.getRawButton(Constants.CLIMBER_BUTTON_AUTO))
		{
			// Auto switch on, don't do anything
			return;
		}
		
		// Turn off climber when done
		Robot.climber.climbControl(0.0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		end();
	}
}
