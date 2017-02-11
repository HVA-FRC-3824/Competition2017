package org.usfirst.frc3824.Competition2017;

public class Constants
{
	// ***************************************************************************************
	// Drive straight constants
	public static double DRIVETRAIN_DRIVE_STRAIGHT_P		= 0.1;
	public static double DRIVETRAIN_DRIVE_STRAIGHT_I		= 0.0;
	public static double DRIVETRAIN_DRIVE_STRAIGHT_D		= 0.0;

	public static double DRIVETRAIN_DRIVE_MINIMUM_OUTPUT	= -1.0;
	public static double DRIVETRAIN_DRIVE_MAXIMUM_OUTPUT	= 1.0;

	public static double DRIVETRAIN_DRIVE_TOLERANCE			= 0.0;

	// ***************************************************************************************
	// Chassis turn constants

	public static double TURN_ANGLE_P						= 0.05;
	public static double TURN_ANGLE_I						= 0.0;
	public static double TURN_ANGLE_D						= 0.0;

	public static double TURN_ANGLE_MINIMUM_OUTPUT			= -0.9;
	public static double TURN_ANGLE_MAXIMUM_OUTPUT			= 0.9;

	public static double TURN_THRESHOLD						= 5.0;
	
	// ***************************************************************************************
	// Climber constants
		
	public static int    CLIMBER_BUTTON_OFF                 = 15;
	public static int    CLIMBER_BUTTON_FAST                = 14;
	public static double CLIMBER_SPEED_FAST                 = -0.5;
	public static double CLIMBER_SPEED_SLOW                 = -0.35;
	public static double CLIMBER_SPEED_HOLD                 = -0.2;

	// ***************************************************************************************
	// Shooter constants
	
	public static double SHOOTER_P							= 0.0600;
	public static double SHOOTER_I							= 0.0004;
	public static double SHOOTER_D							= 4.0000;
	public static double SHOOTER_F							= 0.0050;
	public static double DEFAULT_SHOOTER_SPEED              = 1000.0;
	
	public static double SHOOTER_SPEED_HIGH                   = 4000.0;
	public static double SHOOTER_SPEED_MED                    = 3000.0;
	public static double SHOOTER_SPEED_LOW                    = 2000.0;
	
	public static double SHOOTER_SPINUP_TIME                = 1.0;
	public static double FEEDER_REVERSE_TIME                = 0.5;
	
	// ***************************************************************************************
	// Feeder constants
	
	public static double FEEDER_P							= 0.1000;
	public static double FEEDER_I							= 0.0010;
	public static double FEEDER_D							= 4.0000;
	public static double FEEDER_F							= 0.1000;
	public static double DEFAULT_FEEDER_SPEED               = 400.0;
	public static double FEEDER_REVERSE_SPEED               = -200.0;
	
	// ***************************************************************************************
	// Ball Pickup constants
	
	public static double SWEEPER_VOLTAGE					= 3.0;
	public static double TRANSPORT_VOLTAGE					= 3.0;

	// ***************************************************************************************
	// Ultrasonic constants

	public static double ULTRASONIC_X1						= 0.086669;
	public static double ULTRASONIC_Y1						= 10.0;

	public static double ULTRASONIC_X2						= 0.467592;
	public static double ULTRASONIC_Y2						= 50.0;

	// ***************************************************************************************
	// Target constants
	public static double DEVIATION_FROM_TARGET				= 2; 
}
