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
import org.usfirst.frc3824.Competition2017.RPiDataSource;
import org.usfirst.frc3824.Competition2017.Robot;

/**
 *
 */
public class LineUpWithTarget extends Command
{
	private boolean			isAligned;
	private RPiDataSource	rpi;

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
	public LineUpWithTarget()
	{
		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.chassis);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		// Assume the robot is not aligned with the target
		isAligned = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		// Try to align the robot to the target
		align();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		// Complete when the robot is aligned
		return isAligned;
	}

	// Called once after isFinished returns true
	protected void end()
	{
		
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		this.end();
	}

	/**
	 * Method to align the robot to the target
	 */
	void align()
	{
		// determine if the RaspberryPi has found a target
		if (rpi.getTarget().isValid())
		{
			double targetXCenter = (double) rpi.getTarget().getXCenter();
			double imgWidth      = (double) rpi.getTarget().getImgWidth();
			double imgXCenter    = imgWidth / 2;

			// Calculate the deviation of the robot to the target
			double deviationFromTarget = targetXCenter - imgXCenter;

			// Determine if the robot is aligned based on the number of pixels from target
			if ((deviationFromTarget < -Constants.DEVIATION_FROM_TARGET) || 
				(deviationFromTarget >  Constants.DEVIATION_FROM_TARGET))
			{
				// Determine if the robot is aligned (set to false during initialization
				while (isAligned == false)
				{
					// Check for turning left
					if (deviationFromTarget <= -2.0)
					{
						new ChassisTurnAngle(-2, 0.8, false);		
					} else if (deviationFromTarget >= 2.0)
					{
						new ChassisTurnAngle(2, 0.8, false);
					}

					// Calculate the deviation of the robot to the target
					deviationFromTarget = targetXCenter - (imgWidth / 2);

					// Determine if the robot is aligned based on the number of pixels from target
					if ((deviationFromTarget > -Constants.DEVIATION_FROM_TARGET) &&
						(deviationFromTarget <  Constants.DEVIATION_FROM_TARGET))
					{
						// Indicate that the robot is aligned
						isAligned = true;
					}
				}
			} else
			{
				// Robot is aligned to target
				isAligned = true;
			}
		}
	}
}
