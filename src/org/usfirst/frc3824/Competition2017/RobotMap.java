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

import edu.wpi.first.wpilibj.PowerDistributionPanel;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
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
    public static SpeedController chassisDriveLeft;
    public static SpeedController chassisDriveRight;
    public static RobotDrive chassisRobotDrive;
    public static AnalogInput chassisUltrasound;
    public static AnalogGyro chassisGyro;
    public static Encoder chassisEncoderLeft;
    public static Encoder chassisEncoderRight;
    public static SpeedController climberClimberMotor;
    public static CANTalon shooterShooter;
    public static CANTalon shooterFeeder;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    public static PowerDistributionPanel pdp = new PowerDistributionPanel();

	public static void init()
	{
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        chassisDriveLeft = new Talon(0);
        LiveWindow.addActuator("Chassis", "Drive Left", (Talon) chassisDriveLeft);
        
        chassisDriveRight = new Talon(1);
        LiveWindow.addActuator("Chassis", "Drive Right", (Talon) chassisDriveRight);
        
        chassisRobotDrive = new RobotDrive(chassisDriveLeft, chassisDriveRight);
        
        chassisRobotDrive.setSafetyEnabled(true);
        chassisRobotDrive.setExpiration(0.1);
        chassisRobotDrive.setSensitivity(0.5);
        chassisRobotDrive.setMaxOutput(1.0);
        chassisRobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        chassisRobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        chassisUltrasound = new AnalogInput(1);
        LiveWindow.addSensor("Chassis", "Ultrasound", chassisUltrasound);
        
        chassisGyro = new AnalogGyro(0);
        LiveWindow.addSensor("Chassis", "Gyro", chassisGyro);
        chassisGyro.setSensitivity(0.007);
        chassisEncoderLeft = new Encoder(2, 3, false, EncodingType.k1X);
        LiveWindow.addSensor("Chassis", "Encoder Left", chassisEncoderLeft);
        chassisEncoderLeft.setDistancePerPulse(0.0242914979757085);
        chassisEncoderLeft.setPIDSourceType(PIDSourceType.kRate);
        chassisEncoderRight = new Encoder(0, 1, false, EncodingType.k1X);
        LiveWindow.addSensor("Chassis", "Encoder Right", chassisEncoderRight);
        chassisEncoderRight.setDistancePerPulse(0.0242914979757085);
        chassisEncoderRight.setPIDSourceType(PIDSourceType.kRate);
        climberClimberMotor = new Talon(6);
        LiveWindow.addActuator("Climber", "Climber Motor", (Talon) climberClimberMotor);
        
        shooterShooter = new CANTalon(2);
        LiveWindow.addActuator("Shooter", "Shooter", shooterShooter);
        
        shooterFeeder = new CANTalon(1);
        LiveWindow.addActuator("Shooter", "Feeder", shooterFeeder);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
	}
}
