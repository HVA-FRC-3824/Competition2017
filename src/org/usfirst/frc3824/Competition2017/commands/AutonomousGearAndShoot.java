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
public class AutonomousGearAndShoot extends CommandGroup
{
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PARAMETERS
    public AutonomousGearAndShoot(String StartPosition) {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PARAMETERS

		if (StartPosition == "Left")
		{
			addSequential(new ChassisDriveDistance(80.0, -0.8, false));
			addSequential(new ChassisTurnAngle(65.0, 0.0, false));
		} 
		else if (StartPosition == "Right")
		{
			addSequential(new ChassisDriveDistance(80.0, -0.8, false));
			addSequential(new ChassisTurnAngle(-65.0, 0.0, false));
		}
		else
		{
			// Start from center so just place gear (no shoot)
			addSequential(new ChassisDriveDistance(40.0, -0.8, false));
		}

		// Deliver the gear
		addSequential(new AutoDeliverGear());
		
		// Don't try to shoot from the center
		if (StartPosition.equals("Center"))
			return;
					
		// Start the shooter early, so it has time to spin up and start shooting
		addParallel(new ShooterSetSpeedMedHigh());
		addParallel(new ShooterManualShoot());

		// go towards the boiler
		if (StartPosition.equals("Right"))
		{
			addSequential(new ChassisTurnAngle(25.0, 0.8, false));
		} 
		else
		{
			addSequential(new ChassisTurnAngle(-25.0, 0.8, false));
		}
		
		addSequential(new ChassisDriveDistance(24.0, 0.4, false));

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=COMMAND_DECLARATIONS
	}
}
