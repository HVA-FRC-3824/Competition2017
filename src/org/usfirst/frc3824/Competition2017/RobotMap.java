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
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap
{
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static AnalogInput chassisUltrasound;
    public static AnalogGyro chassisGyro;
    public static Encoder chassisEncoderLeft;
    public static Encoder chassisEncoderRight;
    public static Solenoid chassisTransmission;
    public static Compressor chassisCompressor;
    public static CANTalon chassisLeftFrontMotor;
    public static CANTalon chassisLeftMidMotor;
    public static CANTalon chassisLeftBackMotor;
    public static CANTalon chassisRightFrontMotor;
    public static CANTalon chassisRightMidMotor;
    public static CANTalon chassisRightBackMotor;
    public static SpeedController climberClimberMotorA;
    public static SpeedController climberClimberMotorB;
    public static DigitalInput climberClimberLimitSwitch;
    public static Solenoid gearGrabber;
    public static Solenoid gearRotator;
    public static Solenoid gearGearPusher;
    public static Servo cameraCameraRotator;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    public static RobotDrive chassisRobotDrive;
    
    public static PowerDistributionPanel pdp = new PowerDistributionPanel();

	public static void init()
	{
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        chassisUltrasound = new AnalogInput(1);
        LiveWindow.addSensor("Chassis", "Ultrasound", chassisUltrasound);
        
        chassisGyro = new AnalogGyro(0);
        LiveWindow.addSensor("Chassis", "Gyro", chassisGyro);
        chassisGyro.setSensitivity(0.007);
        chassisEncoderLeft = new Encoder(2, 3, false, EncodingType.k1X);
        LiveWindow.addSensor("Chassis", "Encoder Left", chassisEncoderLeft);
        chassisEncoderLeft.setDistancePerPulse(0.0349854227);
        chassisEncoderLeft.setPIDSourceType(PIDSourceType.kDisplacement);
        chassisEncoderRight = new Encoder(0, 1, false, EncodingType.k1X);
        LiveWindow.addSensor("Chassis", "Encoder Right", chassisEncoderRight);
        chassisEncoderRight.setDistancePerPulse(0.0349854227);
        chassisEncoderRight.setPIDSourceType(PIDSourceType.kDisplacement);
        chassisTransmission = new Solenoid(0, 0);
        LiveWindow.addActuator("Chassis", "Transmission", chassisTransmission);
        
        chassisCompressor = new Compressor(0);
        
        
        chassisLeftFrontMotor = new CANTalon(0);
        LiveWindow.addActuator("Chassis", "Left Front Motor", chassisLeftFrontMotor);
        
        chassisLeftMidMotor = new CANTalon(1);
        LiveWindow.addActuator("Chassis", "Left Mid Motor", chassisLeftMidMotor);
        
        chassisLeftBackMotor = new CANTalon(2);
        LiveWindow.addActuator("Chassis", "Left Back Motor", chassisLeftBackMotor);
        
        chassisRightFrontMotor = new CANTalon(3);
        LiveWindow.addActuator("Chassis", "Right Front Motor", chassisRightFrontMotor);
        
        chassisRightMidMotor = new CANTalon(4);
        LiveWindow.addActuator("Chassis", "Right Mid Motor", chassisRightMidMotor);
        
        chassisRightBackMotor = new CANTalon(5);
        LiveWindow.addActuator("Chassis", "Right Back Motor", chassisRightBackMotor);
        
        climberClimberMotorA = new Talon(4);
        LiveWindow.addActuator("Climber", "Climber Motor A", (Talon) climberClimberMotorA);
        
        climberClimberMotorB = new Talon(5);
        LiveWindow.addActuator("Climber", "Climber Motor B", (Talon) climberClimberMotorB);
        
        climberClimberLimitSwitch = new DigitalInput(4);
        LiveWindow.addSensor("Climber", "Climber Limit Switch", climberClimberLimitSwitch);
        
        gearGrabber = new Solenoid(0, 1);
        LiveWindow.addActuator("Gear", "Grabber", gearGrabber);
        
        gearRotator = new Solenoid(0, 2);
        LiveWindow.addActuator("Gear", "Rotator", gearRotator);
        
        gearGearPusher = new Solenoid(0, 7);
        LiveWindow.addActuator("Gear", "Gear Pusher", gearGearPusher);
        
        cameraCameraRotator = new Servo(6);
        LiveWindow.addActuator("Camera", "Camera Rotator", cameraCameraRotator);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        
        // Set CAN Talons on either side of the drivetrain to mimic the front CAN Talons
        chassisLeftMidMotor.changeControlMode(CANTalon.TalonControlMode.Follower);
        chassisLeftMidMotor.set(chassisLeftFrontMotor.getDeviceID());
        
        chassisLeftBackMotor.changeControlMode(CANTalon.TalonControlMode.Follower);
        chassisLeftBackMotor.set(chassisLeftFrontMotor.getDeviceID());
        
        chassisRightMidMotor.changeControlMode(CANTalon.TalonControlMode.Follower);
        chassisRightMidMotor.set(chassisRightFrontMotor.getDeviceID());
        
        chassisRightBackMotor.changeControlMode(CANTalon.TalonControlMode.Follower);
        chassisRightBackMotor.set(chassisRightFrontMotor.getDeviceID());
        
        chassisRobotDrive = new RobotDrive(chassisLeftFrontMotor, chassisRightFrontMotor);
	}
}
