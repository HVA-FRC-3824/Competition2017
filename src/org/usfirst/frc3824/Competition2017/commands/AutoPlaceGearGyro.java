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
import org.usfirst.frc3824.Competition2017.Robot;

/**
 *
 */
public class AutoPlaceGearGyro extends Command
{
	private Timer	m_Timer = new Timer();
	
	private double  m_UpdateTime;
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
	public AutoPlaceGearGyro()
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
		System.out.println("ChassisDriveDistance initialize");

		// Set the PID up for driving straight
		Robot.chassis.driveStraightPID(-Constants.PLACE_GEAR_AUTO_POWER, false);

		// reset and start the timer
		m_Timer.reset();
		m_Timer.start();
		
		m_UpdateTime = Constants.PLACE_GEAR_AUTO_UPDATE_TIME;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{	
		if(m_Timer.get() > m_UpdateTime)
		{
			m_UpdateTime += Constants.PLACE_GEAR_AUTO_UPDATE_TIME;
						
			double center = SmartDashboard.getNumber("Target Center", -1);
			
			if(center != -1)
			{
				SmartDashboard.putNumber("Update Time", m_UpdateTime);
				
				double theta = getTheta();
				
				double distanceFromTarget = getDistanceFromTarget();
				
				SmartDashboard.putNumber("DistanceFromTarget", distanceFromTarget);
				
				SmartDashboard.putNumber("Theta", theta);
				
				Robot.chassis.updateHeadingPID_Setpoint(theta);
			}
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return Robot.chassis.getUltrasonicDistance() < Constants.PLACE_GEAR_AUTO_WALL_DISTANCE;
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
	
	private double getDistanceFromTarget()
	{
		// x = distanceBetweenTargetsPixels
		double x = Math.abs(SmartDashboard.getNumber("Target A X", -1) - SmartDashboard.getNumber("Target B X", -1));
		
		return  Constants.PLACE_GEAR_AUTO_A * Math.pow(x, Constants.PLACE_GEAR_AUTO_E);
	}
	
	private double getTargetOffsetInches()
	{
		// x - distanceBetweenTargetPixels
		double x = Math.abs(SmartDashboard.getNumber("Target A X", -1) - SmartDashboard.getNumber("Target B X", -1));
		
		double targetOffset = SmartDashboard.getNumber("Target Center", -1) - Constants.PLACE_GEAR_AUTO_CAMERA_CENTER;
		
		double inchesPerPixel = 6 / x;
		
		return inchesPerPixel * targetOffset;
	}
	
	private double getTheta()
	{
		double targetOffsetInches = getTargetOffsetInches();
		double distanceFromTarget = getDistanceFromTarget();
		double angleRadians = Math.atan(targetOffsetInches / distanceFromTarget);
		double angleDegrees = Math.toDegrees(angleRadians);
		
		return angleDegrees;
	}
}
