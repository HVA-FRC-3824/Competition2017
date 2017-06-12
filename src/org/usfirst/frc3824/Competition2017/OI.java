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
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;

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
    public JoystickButton shifterHighGear;
    public JoystickButton shifterLowGear;
    public JoystickButton encoderControl;
    public JoystickButton autoGearPlace;
    public Joystick driveJoystick;
    public JoystickButton autoClimb;
    public JoystickButton manualClimbFast;
    public JoystickButton manualClimbSlow;
    public JoystickButton rotateGearUp;
    public JoystickButton rotateGearDown;
    public JoystickButton grabGear;
    public JoystickButton releaseGear;
    public JoystickButton pushGear;
    public JoystickButton climberJogUp;
    public JoystickButton dONOTPRESS;
    public Joystick controllerJoystick;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	public OI()
	{
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        controllerJoystick = new Joystick(1);
        
        dONOTPRESS = new JoystickButton(controllerJoystick, 19);
        dONOTPRESS.whenPressed(new DONOTPRESS());
        climberJogUp = new JoystickButton(controllerJoystick, 2);
        climberJogUp.whenPressed(new ClimbJog());
        pushGear = new JoystickButton(controllerJoystick, 13);
        pushGear.whenPressed(new GearPush());
        releaseGear = new JoystickButton(controllerJoystick, 16);
        releaseGear.whenPressed(new GearRelease());
        grabGear = new JoystickButton(controllerJoystick, 16);
        grabGear.whenReleased(new GearGrab());
        rotateGearDown = new JoystickButton(controllerJoystick, 5);
        rotateGearDown.whenPressed(new GearRotateDown());
        rotateGearUp = new JoystickButton(controllerJoystick, 5);
        rotateGearUp.whenReleased(new GearRotateUp());
        manualClimbSlow = new JoystickButton(controllerJoystick, 15);
        manualClimbSlow.whileHeld(new ClimberManualClimb());
        manualClimbFast = new JoystickButton(controllerJoystick, 14);
        manualClimbFast.whileHeld(new ClimberManualClimb());
        autoClimb = new JoystickButton(controllerJoystick, 1);
        autoClimb.whenPressed(new ClimberControl());
        driveJoystick = new Joystick(0);
        
        autoGearPlace = new JoystickButton(driveJoystick, 3);
        autoGearPlace.whileHeld(new AutoDeliverGear());
        encoderControl = new JoystickButton(driveJoystick, 8);
        encoderControl.whileHeld(new DriveWithEncoders());
        shifterLowGear = new JoystickButton(driveJoystick, 2);
        shifterLowGear.whenReleased(new ChassisShift(false));
        shifterHighGear = new JoystickButton(driveJoystick, 2);
        shifterHighGear.whenPressed(new ChassisShift(true));


        // SmartDashboard Buttons

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
