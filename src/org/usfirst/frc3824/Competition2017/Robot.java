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

//import edu.wpi.cscore.UsbCamera;
//import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc3824.Competition2017.RobotMap;
import org.usfirst.frc3824.Competition2017.Robot;
import org.usfirst.frc3824.Competition2017.RPiDataSource;
import org.usfirst.frc3824.Competition2017.commands.*;
import org.usfirst.frc3824.Competition2017.subsystems.*;
import org.usfirst.frc3824.Competition2017.utils.RIOCamera;

import com.ctre.CANTalon;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot
{
	Command					autonomousCommand;

	public static OI		oi;
		
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Chassis chassis;
    public static Climber climber;
    public static Gear gear;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
//    public static RPiDataSource rpi;

	public static SendableChooser<String> startingLocationChooser;
	public static SendableChooser<String> commandChooser;
	public static SendableChooser<String> allianceChooser;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit()
	{
		RobotMap.init();
		
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        chassis = new Chassis();
        climber = new Climber();
        gear = new Gear();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
		// OI must be constructed after subsystems. If the OI creates Commands
		// (which it very likely will), subsystems are not guaranteed to be
		// constructed yet. Thus, their requires() statements may grab null
		// pointers. Bad news. Don't move it.
		oi = new OI();
        
		// instantiate the command used for the autonomous period
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

        autonomousCommand = new AutonomousCommand();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        
		// set up the chooser
		commandChooser = new SendableChooser<>();
		commandChooser.addDefault("Nothing",    "Nothing");
		commandChooser.addObject("Baseline",    "Baseline");
		commandChooser.addObject("Gear Simple", "Gear Simple");
		commandChooser.addObject("Gear",        "Gear");
		SmartDashboard.putData("Command", commandChooser);
		
		// set up the chooser for the starting location
		startingLocationChooser = new SendableChooser<>();
		startingLocationChooser.addDefault("Right",        "Right");
		startingLocationChooser.addObject("Center",        "Center");
		startingLocationChooser.addObject("Left",          "Left");
//		startingLocationChooser.addObject("Center No Run", "Center No Run");
		SmartDashboard.putData("Starting Location", startingLocationChooser );
		
		// set up the chooser for the alliance
		allianceChooser = new SendableChooser<>();
		allianceChooser.addDefault("Red", "Red");
		allianceChooser.addObject("Blue", "Blue");
		SmartDashboard.putData("Alliance", allianceChooser);
				
//		rpi = RPiDataSource.getInstance();
//        rpi.start();
        
        SmartDashboard.putNumber("Gyro Center", Constants.DEFAULT_GYRO_CENTER);
        
        RIOCamera.GetThread().start();
        
        Robot.gear.setLock(false);
        Robot.gear.setPush(false);
        
        SmartDashboard.putNumber("Rotator P", 0);
        SmartDashboard.putNumber("Rotator I", 0);
        SmartDashboard.putNumber("Rotator D", 0);
        SmartDashboard.putNumber("Rotator Setpoint", 0);
		
//		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
//
//	    // Setup the camera
//		camera.setResolution(640, 480);
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit()
	{

	}

	public void disabledPeriodic()
	{
		Scheduler.getInstance().run();

		// Update parameters on the SmartDashboard
		updateSmartDashboard();
       
//		// Determine if a button is pressed
//		for (int button = 1; button <= 20; button++)
//			SmartDashboard.putBoolean("Button" + button, Robot.oi.getControllerJoystick().getRawButton(button));
	}

	public void autonomousInit()
	{	
		// set camera to be dark for autonomous
    	SmartDashboard.putBoolean("Camera Bright", false);

		// Determine the starting position from the position chooser
		String startPosition = startingLocationChooser.getSelected();
		String alliance = allianceChooser.getSelected();
		
		// Determine the autonomous command from the command chooser
		switch (commandChooser.getSelected())
		{
		case "Baseline":
			autonomousCommand = new AutonomousBaseline(startPosition);
			break;
		case "Gear":
			autonomousCommand = new AutonomousPlaceGear(startPosition);
			break;
		case "Gear Simple":
			autonomousCommand = new AutonomousGearPlaceCenter();
			break;
		default:
			autonomousCommand = null;
			break;
		}
		
		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during the autonomous period
	 */
	public void autonomousPeriodic()
	{
		Scheduler.getInstance().run();
		
//		// Update the SmartDashboard data about the image processing
//        rpi.updateSmartDashboardData();
//        rpi.updateSmartDashboardActive();
	}

	/**
	 * This function is called just before the operator period
	 */
	public void teleopInit()
	{
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
		// switch camera back to higher brightness so driver can see
		SmartDashboard.putBoolean("Camera Bright", true);
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic()
	{
		Scheduler.getInstance().run();
		
		// Update parameters on the SmartDashboard
		updateSmartDashboard();
		
		SmartDashboard.putNumber("Average Climber Current", Robot.climber.getClimberCurrent());
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic()
	{
		LiveWindow.run();
	}
	
	/**
	 * Method to update parameters to the SmartDashboard in disabled and teleop
	 */
	public void updateSmartDashboard()
	{		
//		// Show the chasses encoders
//		SmartDashboard.putNumber("Chassis Encoder Left",  RobotMap.chassisEncoderLeft.getDistance());
//		SmartDashboard.putNumber("Chassis Encoder Right", RobotMap.chassisEncoderRight.getDistance());
		
		// Show chassis sensor values
		SmartDashboard.putNumber("Ultrasonic Distance", Robot.chassis.getUltrasonicDistance());
		SmartDashboard.putNumber("Chassis Encoder Distance", Robot.chassis.getEncoderDistance());

		// Show the calculated gyro angle and center
		SmartDashboard.putNumber("Gyro Angle", Robot.chassis.getCurrentHeading());	
		SmartDashboard.putNumber("Calculated Gryo Center", RobotMap.chassisGyro.getCenter());
		
		SmartDashboard.putBoolean("Climber Switch", Robot.climber.getClimberLimitSwitch());
		
		SmartDashboard.putNumber("Rotator Encoder", Robot.gear.getRotatorEncoderValue());
		
		SmartDashboard.putNumber("Rotate Output", Robot.gear.getRotator().get());
		SmartDashboard.putNumber("Rotate Error", Robot.gear.getRotator().getError());
		SmartDashboard.putNumber("Rotate Voltage", Robot.gear.getRotator().getOutputVoltage());
		SmartDashboard.putNumber("Rotate Setpoint", Robot.gear.getRotator().getSetpoint());
		SmartDashboard.putNumber("Rotate P", Robot.gear.getRotator().getP());
		SmartDashboard.putNumber("Rotate I", Robot.gear.getRotator().getI());
		SmartDashboard.putNumber("Rotate D", Robot.gear.getRotator().getD());
		SmartDashboard.putNumber("Rotate PID Get", Robot.gear.getRotator().pidGet());
		SmartDashboard.putBoolean("Rotate Enabled", Robot.gear.getRotator().isEnabled());
		SmartDashboard.putNumber("Rotate Control Mode", Robot.gear.getRotator().getControlMode().getValue());
		
		SmartDashboard.putNumber("Position Mode", CANTalon.TalonControlMode.Position.getValue());
		SmartDashboard.putNumber("Speed Mode", CANTalon.TalonControlMode.Speed.getValue());
		
		SmartDashboard.putBoolean("Pusher", Robot.gear.getPush());
		SmartDashboard.putBoolean("Locker", Robot.gear.getLock());
		
//		// Update the SmartDashboard data about the image processing
//        rpi.updateSmartDashboardData();
//        rpi.updateSmartDashboardActive();
	}
}
