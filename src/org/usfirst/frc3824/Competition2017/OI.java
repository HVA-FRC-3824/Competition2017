// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc3824.Competition2017;

import org.usfirst.frc3824.Competition2017.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;
import org.usfirst.frc3824.Competition2017.subsystems.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI
{
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton shootTrigger;
    public JoystickButton shifter;
    public Joystick driveJoystick;
    public JoystickButton ballPickupForward;
    public JoystickButton ballPickupReverse;
    public JoystickButton ballPickupForwardDisable;
    public JoystickButton ballPickupReverseDisable;
    public JoystickButton shoot;
    public JoystickButton shooterSpeedHigh;
    public JoystickButton shooterSpeedMed;
    public JoystickButton shooterSpeedLow;
    public JoystickButton shooterSpeedOff;
    public JoystickButton autoClimb;
    public JoystickButton manualClimb;
    public JoystickButton receiveGear;
    public JoystickButton placeGear;
    public Joystick controllerJoystick;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	public OI()
	{
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        controllerJoystick = new Joystick(1);
        
        placeGear = new JoystickButton(controllerJoystick, 16);
        placeGear.whenPressed(new GearPlace());
        receiveGear = new JoystickButton(controllerJoystick, 5);
        receiveGear.whileHeld(new GearReceiveOut());
        manualClimb = new JoystickButton(controllerJoystick, 4);
        manualClimb.whileHeld(new ClimberManualClimb());
        autoClimb = new JoystickButton(controllerJoystick, 1);
        autoClimb.whenPressed(new ClimberControl());
        shooterSpeedOff = new JoystickButton(controllerJoystick, 13);
        shooterSpeedOff.whenPressed(new ShooterSetSpeedOff());
        shooterSpeedLow = new JoystickButton(controllerJoystick, 12);
        shooterSpeedLow.whenPressed(new ShooterSetSpeedLow());
        shooterSpeedMed = new JoystickButton(controllerJoystick, 11);
        shooterSpeedMed.whenPressed(new ShooterSetSpeedMed());
        shooterSpeedHigh = new JoystickButton(controllerJoystick, 10);
        shooterSpeedHigh.whenPressed(new ShooterSetSpeedHigh());
        shoot = new JoystickButton(controllerJoystick, 20);
        shoot.whileHeld(new ShooterManualShoot());
        ballPickupReverseDisable = new JoystickButton(controllerJoystick, 6);
        ballPickupReverseDisable.whenReleased(new BallPickupEnableDisable(false, false));
        ballPickupForwardDisable = new JoystickButton(controllerJoystick, 7);
        ballPickupForwardDisable.whenReleased(new BallPickupEnableDisable(false, false));
        ballPickupReverse = new JoystickButton(controllerJoystick, 6);
        ballPickupReverse.whenPressed(new BallPickupEnableDisable(true, true));
        ballPickupForward = new JoystickButton(controllerJoystick, 7);
        ballPickupForward.whenPressed(new BallPickupEnableDisable(true, false));
        driveJoystick = new Joystick(0);
        
        shifter = new JoystickButton(driveJoystick, 2);
        shifter.whenPressed(new ChassisShift(true));
        shootTrigger = new JoystickButton(driveJoystick, 1);
        shootTrigger.whenPressed(new ShooterManualShoot());


        // SmartDashboard Buttons
        SmartDashboard.putData("Chassis Drive Distance: FiveFeet", new ChassisDriveDistance(60.0, .75, false));
        SmartDashboard.putData("Chassis Turn Angle: Right", new ChassisTurnAngle(90.0, 0.5, false));
        SmartDashboard.putData("Chassis Drive Range: TwelveInches", new ChassisDriveRange(12.0, 0.75, false));
        SmartDashboard.putData("Shooter Control", new ShooterControl());
        SmartDashboard.putData("Shooter Enable Disable: ShooterEnable", new ShooterEnableDisable(true));
        SmartDashboard.putData("Shooter Enable Disable: ShooterDisable", new ShooterEnableDisable(false));
        SmartDashboard.putData("Shooter Manual Shoot", new ShooterManualShoot());
        SmartDashboard.putData("Shooter Update PID Parameters", new ShooterUpdatePIDParameters());
        SmartDashboard.putData("Climber Control", new ClimberControl());
        SmartDashboard.putData("Climber Manual Climb", new ClimberManualClimb());
        SmartDashboard.putData("Line Up With Target", new LineUpWithTarget());
        SmartDashboard.putData("Gear Receive Out", new GearReceiveOut());
        SmartDashboard.putData("Gear Place", new GearPlace());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
	}

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getDriveJoystick() {
        return driveJoystick;
    }

    public Joystick getControllerJoystick() {
        return controllerJoystick;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}
