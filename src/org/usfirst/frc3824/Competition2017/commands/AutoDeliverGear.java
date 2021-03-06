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
public class AutoDeliverGear extends CommandGroup
{


    public AutoDeliverGear() {
    	this(false);
    }
    public AutoDeliverGear(boolean shouldPushBackOn) {

    	
    	// Move to lift (using ultrasonic sensor)
    	addSequential(new AutoPlaceGearGyro(), 10.0);
    	addSequential(new Delay(0.5));
    	
    	// Turn to lift and rotate up while driving back slightly
		addParallel(new TurnToTarget(), 5.0);
		addParallel(new GearRotateUp(), 1.0);
		addSequential(new Delay(0.5));
		addParallel(new ChassisDriveDistance(2.0, 0.2, false), 2.0);
		addSequential(new Delay(0.5));
		
		// Drive up to lift to place gear
		addSequential(new ChassisDriveRange(5.0, -0.3, false), 2.0);
		
		// Release the gear
		addSequential(new GearRelease(), 1.0);
		addSequential(new Delay(0.75));
		
		// Rotate the gear handler down
		addSequential(new GearRotateDown(), 1.0);
		addSequential(new Delay(0.5));
		
		if (shouldPushBackOn) 
		{
			// Backup to get ready to push the gear on
			addSequential(new ChassisDriveDistance(8.0, 0.5, false), 2.0);
			
			// Grive up again to push back on
			addSequential(new GearRotateUp(), 1.0);
			addSequential(new GearRelease(), 1.0);
			addSequential(new Delay(0.5));
			addSequential(new ChassisDriveDistance(8.0, -0.3, false), 2.0);
		}
		
		// Back away from lift
		addSequential(new ChassisDriveDistance(30.0, 0.5, false), 2.0);
		
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=COMMAND_DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=COMMAND_DECLARATIONS
 
    }
}
