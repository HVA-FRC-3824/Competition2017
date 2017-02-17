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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc3824.Competition2017.Constants;
import org.usfirst.frc3824.Competition2017.RPiDataSource;
import org.usfirst.frc3824.Competition2017.Robot;
import org.usfirst.frc3824.Competition2017.Target;

/**
 *
 */
public class LineUpWithTarget extends Command
{
	private RPiDataSource	rpi = RPiDataSource.getInstance();
	private boolean			isAligned	= false;
	private Timer           timer = new Timer();
	
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
		Robot.chassis.encoderPID(0);
		
		timer.reset();
		timer.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		Target target = rpi.getTarget();
		if (target.isValid())
		{
			System.out.println("Valid Target Out");
			// only update periodically
			if (timer.get() > 1.0) {
		    	Robot.chassis.updateEncoderSetpointWithTarget(target);
	
				double deviationFromTarget = target.deviationFromTarget();

				SmartDashboard.putNumber("Deviation from target", deviationFromTarget);
				
				if (deviationFromTarget > -Constants.DEVIATION_FROM_TARGET
						&& deviationFromTarget < Constants.DEVIATION_FROM_TARGET)
				{
					isAligned = true;
				}
				timer.reset();
			}
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return isAligned;
	}

	// Called once after isFinished returns true
	protected void end()
	{
		Robot.chassis.resetChassisPIDcontrollersAndEncoders();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		end();
	}

}
