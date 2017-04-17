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

import org.usfirst.frc3824.Competition2017.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousShoot extends CommandGroup
{
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PARAMETERS
    public AutonomousShoot(String StartPosition) {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PARAMETERS
		addParallel(new ShooterSetSpeedMedHigh());
		addParallel(new ShooterManualShoot(), 10.0);
		
		addSequential(new ChassisDriveDistance(Constants.AUTONOMOUS_DRIVE_FROM_WALL_DISTANCE,
                -Constants.AUTONOMOUS_DRIVE_FROM_WALL_SPEED, false));
		
		if (StartPosition == "Left")
		{
			addSequential(new ChassisTurnAngle(Constants.AUTONOMOUS_TURN_SHOOT_ANGLE, 0.0, false));
		} 
		else if (StartPosition == "Right")
		{
			addSequential(new ChassisTurnAngle(-Constants.AUTONOMOUS_TURN_SHOOT_ANGLE, 0.0, false));
		} 
		
		addSequential(new Delay(2));
		
		if (StartPosition == "Left")
		{
			// Turn and deliver gear blue side
			addSequential(new ChassisTurnAngle(-20.0, 0.0, false));
			addSequential(new ChassisDriveDistance(74.0, -0.8, false));
			addSequential(new ChassisTurnAngle(30.0, 0.0, false));
		} 
		else if (StartPosition == "Right")
		{
			// Turn and deliver gear red side
			addSequential(new ChassisTurnAngle(20.0, 0.0, false));
			addSequential(new ChassisDriveDistance(50.0, -0.8, false));
			addSequential(new ChassisTurnAngle(-30.0, 0.0, false));
		} 
		
		// Deliver the gear
		addSequential(new AutoDeliverGear());
	}
}
