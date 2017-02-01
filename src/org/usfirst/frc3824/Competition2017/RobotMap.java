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

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap
{
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static SpeedController chassisDriveLeftA;
    public static SpeedController chassisDriveLeftB;
    public static SpeedController chassisDriveRightA;
    public static SpeedController chassisDriveRightB;
    public static RobotDrive chassisRobotDrive;
    public static SpeedController climberClimberMotor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	public static void init()
	{
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        chassisDriveLeftA = new Talon(0);
        LiveWindow.addActuator("Chassis", "Drive Left A", (Talon) chassisDriveLeftA);
        
        chassisDriveLeftB = new Talon(1);
        LiveWindow.addActuator("Chassis", "Drive Left B", (Talon) chassisDriveLeftB);
        
        chassisDriveRightA = new Talon(4);
        LiveWindow.addActuator("Chassis", "Drive Right A", (Talon) chassisDriveRightA);
        
        chassisDriveRightB = new Talon(5);
        LiveWindow.addActuator("Chassis", "Drive Right B", (Talon) chassisDriveRightB);
        
        chassisRobotDrive = new RobotDrive(chassisDriveLeftA, chassisDriveLeftB,
              chassisDriveRightA, chassisDriveRightB);
        
        chassisRobotDrive.setSafetyEnabled(false);
        chassisRobotDrive.setExpiration(0.1);
        chassisRobotDrive.setSensitivity(0.5);
        chassisRobotDrive.setMaxOutput(1.0);
        chassisRobotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        chassisRobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        chassisRobotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        chassisRobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        climberClimberMotor = new Talon(6);
        LiveWindow.addActuator("Climber", "Climber Motor", (Talon) climberClimberMotor);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
	}
}
