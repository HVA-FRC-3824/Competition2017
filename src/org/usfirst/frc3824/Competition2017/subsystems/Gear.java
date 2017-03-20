// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc3824.Competition2017.subsystems;

import org.usfirst.frc3824.Competition2017.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Gear extends Subsystem
{
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final Solenoid grabber = RobotMap.gearGrabber;
    private final Solenoid rotator = RobotMap.gearRotator;
    private final SpeedController feeder = RobotMap.gearFeeder;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	/**
	 * 
	 */
	public void setRotate(boolean up)
	{
		rotator.set(up);
	}
	
	/**
	 * 
	 */
	public boolean getRotate()
	{
		return rotator.get();
	}

	/**
	 * Method to set the gear grab
	 *    true  - open
	 *    false - close
	 */
	public void setGrab(boolean open)
	{
		grabber.set(open);
	}

	/**
	 * 
	 */
	public boolean getGrab()
	{
		return grabber.get();
	}

	public void Enable_Gear_Feeder(double speed)
	{
		// Set the gear feeder speed
		feeder.set(-speed);
	}
	
	/**
	 * 
	 */
	public void initDefaultCommand()
	{
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
