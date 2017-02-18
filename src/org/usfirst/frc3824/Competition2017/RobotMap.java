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
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
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
    public static Solenoid chassisTransmission;
    public static Compressor chassisCompressor;
    public static SpeedController climberClimberMotorA;
    public static SpeedController climberClimberMotorB;
    public static CANTalon shooterShooterA;
    public static CANTalon shooterShooterB;
    public static CANTalon shooterFeeder;
    public static SpeedController ballPickupSweeper;
    public static SpeedController ballPickupTransport;
    public static Solenoid gearGrabber;
    public static Solenoid gearRotator;

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
        
        chassisRobotDrive.setSafetyEnabled(false);
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
        chassisEncoderLeft.setPIDSourceType(PIDSourceType.kDisplacement);
        chassisEncoderRight = new Encoder(0, 1, false, EncodingType.k1X);
        LiveWindow.addSensor("Chassis", "Encoder Right", chassisEncoderRight);
        chassisEncoderRight.setDistancePerPulse(0.0242914979757085);
        chassisEncoderRight.setPIDSourceType(PIDSourceType.kDisplacement);
        chassisTransmission = new Solenoid(0, 0);
        LiveWindow.addActuator("Chassis", "Transmission", chassisTransmission);
        
        chassisCompressor = new Compressor(0);
        
        
        climberClimberMotorA = new Talon(4);
        LiveWindow.addActuator("Climber", "Climber Motor A", (Talon) climberClimberMotorA);
        
        climberClimberMotorB = new Talon(5);
        LiveWindow.addActuator("Climber", "Climber Motor B", (Talon) climberClimberMotorB);
        
        shooterShooterA = new CANTalon(1);
        LiveWindow.addActuator("Shooter", "Shooter A", shooterShooterA);
        
        shooterShooterB = new CANTalon(2);
        LiveWindow.addActuator("Shooter", "Shooter B", shooterShooterB);
        
        shooterFeeder = new CANTalon(3);
        LiveWindow.addActuator("Shooter", "Feeder", shooterFeeder);
        
        ballPickupSweeper = new Talon(2);
        LiveWindow.addActuator("Ball Pickup", "Sweeper", (Talon) ballPickupSweeper);
        
        ballPickupTransport = new Talon(3);
        LiveWindow.addActuator("Ball Pickup", "Transport", (Talon) ballPickupTransport);
        
        gearGrabber = new Solenoid(0, 1);
        LiveWindow.addActuator("Gear", "Grabber", gearGrabber);
        
        gearRotator = new Solenoid(0, 2);
        LiveWindow.addActuator("Gear", "Rotator", gearRotator);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
	}
}
