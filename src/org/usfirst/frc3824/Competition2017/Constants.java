package org.usfirst.frc3824.Competition2017;

public class Constants {
	// ***************************************************************************************
	// Drive straight constants
	public static double DRIVETRAIN_DRIVE_STRAIGHT_P          =  0.1;
	public static double DRIVETRAIN_DRIVE_STRAIGHT_I          =  0.0;
	public static double DRIVETRAIN_DRIVE_STRAIGHT_D          =  0.0;
	
	public static double DRIVETRAIN_DRIVE_MINIMUM_OUTPUT      = -1.0;
	public static double DRIVETRAIN_DRIVE_MAXIMUM_OUTPUT      =  1.0;	
	
	public static double DRIVETRAIN_DRIVE_TOLERANCE           =  0.0;		
	
	// ***************************************************************************************
	// Chassis turn constants
	
	public static double TURN_ANGLE_P                         = 0.05;
	public static double TURN_ANGLE_I                         = 0.0;
	public static double TURN_ANGLE_D                         = 0.0;
	
	public static double TURN_ANGLE_MINIMUM_OUTPUT            = -0.9;
	public static double TURN_ANGLE_MAXIMUM_OUTPUT            =  0.9;	

	public static double TURN_THRESHOLD                       = 5.0;
	
	// ***************************************************************************************
	// Automated aim and shoot constants
	
	public static double IMAGE_ANGLE_ENCODER_P                = 0.3;
	public static double IMAGE_ANGLE_ENCODER_I                = 0.0005;
	public static double IMAGE_ANGLE_ENCODER_D                = 0.0;

	public static double IMAGE_ANGLE_MINIMUM_INPUT            = -1000.0;
	public static double IMAGE_ANGLE_MAXIMUM_INPUT            =  1000.0;	

	public static double IMAGE_ANGLE_MINIMUM_OUTPUT           = -0.4;
	public static double IMAGE_ANGLE_MAXIMUM_OUTPUT           =  0.4;	

	
}
