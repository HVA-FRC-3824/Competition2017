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
import org.usfirst.frc3824.Competition2017.Robot;

/**
 *
 */
public class TeleopDrive extends Command
{
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
	public TeleopDrive()
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
		// Stop the robot and reset the encoders
		Robot.chassis.resetChassisPIDcontrollersAndEncoders();
		
		// set gear positions back to match switch positions
		Robot.gear.setGrab(Robot.oi.grabGear.get());
		Robot.gear.setRotate(!Robot.oi.rotateGearDown.get());
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		// Drive the robot using the Joystick
		Robot.chassis.driveWithJoystick(Robot.oi.driveJoystick);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		// The chassis drive is always active for teleoperation mode
		return false;
	}

	// Called once after isFinished returns true
	protected void end()
	{
		// Stop the robot and reset the encoders
		Robot.chassis.resetChassisPIDcontrollersAndEncoders();		
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		end();
	}
}
