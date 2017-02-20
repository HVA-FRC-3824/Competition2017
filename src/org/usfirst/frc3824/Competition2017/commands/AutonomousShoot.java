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

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousShoot extends CommandGroup
{
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PARAMETERS
    public AutonomousShoot(String StartPosition) {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PARAMETERS

		if (StartPosition == "Left")
		{
			addSequential(new ChassisDriveDistance(56.0, -0.8, false));
			addSequential(new ChassisTurnAngle(45.0, 0.0, false));
		} 
		else if (StartPosition == "Right")
		{
			addSequential(new ChassisDriveDistance(56.0, -0.8, false));
			addSequential(new ChassisTurnAngle(-40.0, 0.0, false));
		} 

		
		addSequential(new ChassisDriveDistance(66.0, 0.6, false));
		addSequential(new ShooterSetSpeedMedHigh());
		addSequential(new ShooterManualShoot());
		    	    	
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=COMMAND_DECLARATIONS
	}
}
