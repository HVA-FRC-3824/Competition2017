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

import org.usfirst.frc3824.Competition2017.Constants;
import org.usfirst.frc3824.Competition2017.RobotMap;
import edu.wpi.first.wpilibj.SpeedController;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BallPickup extends Subsystem
{
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController sweeper = RobotMap.ballPickupSweeper;
    private final SpeedController transport = RobotMap.ballPickupTransport;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
    
	public void initDefaultCommand()
	{
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	}
	
	/**
	 * Method to enable just the ball transport motor
	 */
	public void setTransport(double speed)
	{
		// Enable the ball transpot motor
		transport.set(speed);
	}

	
	/**
	 * Method to disable just the ball transport motor
	 */
	public void disableTransport()
	{
		// Stop the ball transport motor
		transport.set(0.0);
	}
	
	/**
	 * Method to enable the sweeper and transport motors for the Ball Pickup
	 */
	public void enableBallPickup()
	{
		// Enable the sweeper and transport motors
		sweeper.set(Constants.SWEEPER_VOLTAGE);
		transport.set(Constants.TRANSPORT_VOLTAGE);
	}
	
	/**
	 * Method to enable the sweeper and transport motors for the Ball Pickup IN REVERSE
	 */
	public void enableBallPickupReversed()
	{
		// Enable the sweeper and transport motors in reverse to clear the ball pickup
		sweeper.set(-Constants.SWEEPER_VOLTAGE);
		transport.set(-Constants.TRANSPORT_VOLTAGE);
	}
	
	/**
	 * Method to disable the sweeper and transport motors for the Ball Pickup
	 */
	public void disableBallPickup()
	{
		// Disable the sweeper and transport motors
		sweeper.set(0.0);
		transport.set(0.0);	
	}
}
