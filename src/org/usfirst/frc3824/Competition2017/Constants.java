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
	// Shooter constants
	
	public static double SHOOTER_P							= 0.0050;
	public static double SHOOTER_I							= 0.0004;
	public static double SHOOTER_D							= 0.0000;
	public static double SHOOTER_F							= 0.0000;
	
	// ***************************************************************************************
	// Feeder constants
	
	public static double FEEDER_P							= 0.0050;
	public static double FEEDER_I							= 0.0004;
	public static double FEEDER_D							= 0.0000;
	public static double FEEDER_F							= 0.0000;
	
	// ***************************************************************************************
	// Ball Pickup constants
	
	public static double SWEEPER_VOLTAGE					= 5.0;
	public static double TRANSPORT_VOLTAGE					= 5.0;
	
	// ***************************************************************************************
	// Automated aim and shoot constants
	
	public static double IMAGE_ANGLE_ENCODER_P                = 0.3;
	public static double IMAGE_ANGLE_ENCODER_I                = 0.0005;
	public static double IMAGE_ANGLE_ENCODER_D                = 0.0;

	public static double IMAGE_ANGLE_MINIMUM_INPUT            = -1000.0;
	public static double IMAGE_ANGLE_MAXIMUM_INPUT            =  1000.0;	

	public static double IMAGE_ANGLE_MINIMUM_OUTPUT           = -0.4;
	public static double IMAGE_ANGLE_MAXIMUM_OUTPUT           =  0.4;	
	
	public static double IMAGE_ANGLE_JOG_DISTANCE             = 15.0; 

	
	// ***************************************************************************************
	// Ultrasonic constants

	public static double ULTRASONIC_X1						= 0.086669;
	public static double ULTRASONIC_Y1						= 10.0;

	public static double ULTRASONIC_X2						= 0.467592;
	public static double ULTRASONIC_Y2						= 50.0;

	// ***************************************************************************************
	// Target constants
	public static double DEVIATION_FROM_TARGET              = 2; 
}
