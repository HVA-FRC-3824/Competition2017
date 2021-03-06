package org.usfirst.frc3824.Competition2017;

public class Constants
{
	// ***************************************************************************************
	// Drivetrain constants
	public static double TWIST_MULTIPLIER     				=  0.90;
	public static int    DRIVER_REVERSE_BUTTON				= 11;

	public static double BACK_TO_SHOOT_DISTANCE 			= 18.0;
	
	// ***************************************************************************************
	// Drive straight constants

	public static double DRIVETRAIN_DRIVE_STRAIGHT_P		=  0.05;
	public static double DRIVETRAIN_DRIVE_STRAIGHT_I		=  0.00005;
	public static double DRIVETRAIN_DRIVE_STRAIGHT_D		=  0.0;

	public static double DRIVETRAIN_DRIVE_MINIMUM_OUTPUT	= -1.0;
	public static double DRIVETRAIN_DRIVE_MAXIMUM_OUTPUT	=  1.0;

	public static double DRIVETRAIN_DRIVE_TOLERANCE			=  0.0;
	
	// ***************************************************************************************
	// Automatic gear place constants
	
	public static double PLACE_GEAR_AUTO_POWER				=  0.4;
	public static double PLACE_GEAR_AUTO_WALL_DISTANCE		=  30.0;
	public static double PLACE_GEAR_AUTO_UPDATE_TIME		=  0.25;
	
	public static double PLACE_GEAR_AUTO_A					=  6194.3;
	public static double PLACE_GEAR_AUTO_E					=  -1.011;
	
	public static double PLACE_GEAR_AUTO_CAMERA_CENTER		=  300.0;


	// ***************************************************************************************
	// Chassis turn constants

	public static double TURN_ANGLE_P						=  0.07;
	public static double TURN_ANGLE_I						=  0.0;
	public static double TURN_ANGLE_D						=  0.0;

	public static double TURN_ANGLE_MINIMUM_OUTPUT			= -0.4;
	public static double TURN_ANGLE_MAXIMUM_OUTPUT			=  0.4;

	public static double TURN_THRESHOLD						=  5.0;
	
	// ***************************************************************************************
	// Climber constants
		
	public static int    CLIMBER_BUTTON_SLOW                =  14;
	public static int    CLIMBER_BUTTON_FAST                =  15;
	public static int    CLIMBER_BUTTON_AUTO                =   4;
	
	public static double CLIMBER_SPEED_FAST                 = -0.6;
	public static double CLIMBER_SPEED_SLOW                 = -0.45;
	public static double CLIMBER_SPEED_HOLD                 =  0.0;
	
	public static double CLIMBER_STOP_CURRENT               =  28.0;
	public static double CLIMBER_JOG_TIME                 	=  1.0;
	
	public static double CLIMBER_AFTER_LIMIT_SWITCH_TIME    = 0.0;

	// ***************************************************************************************
	// Gear constants
	
	public static double GEAR_PLACE_TIME                    =  1.0;
	
	// ***************************************************************************************
	// Shooter constants
	
	public static double SHOOTER_P							= 0.0600;
	public static double SHOOTER_I							= 0.0004;
	public static double SHOOTER_D							= 4.0000;
	public static double SHOOTER_F							= 0.0050;
	

	public static double SHOOTER_SPEED_HIGH                 = 4500.0; // Max speed
	public static double SHOOTER_SPEED_MEDHIGH              = 3750.0;
	public static double SHOOTER_SPEED_MEDLOW               = 3500.0;
	public static double SHOOTER_SPEED_LOW                  = 3200.0;
	
	public static double DEFAULT_SHOOTER_SPEED              = SHOOTER_SPEED_MEDHIGH;
	
	public static double SHOOTER_B_OFFSET                   = -50.0;
	
	public static double SHOOTER_JOG_SPEED                  = 50.0;
	
	public static double FEEDER_CLEAR_BALLS_TIME            = 0.5;
	public static double SHOOTER_SPINUP_TIME                = 1.0;
	
	public static double SHOOTER_UNJAM_SPEED                = -600.0;
	
	// ***************************************************************************************
	// Feeder constants
	
	// PIDF constants are multiplied by 1000 when displayed on the SmartDashboard
	public static double FEEDER_P							=  0.0400;
	public static double FEEDER_I							=  0.0004;
	public static double FEEDER_D							=  1.6000;
	public static double FEEDER_F							=  0.0400;
	public static double DEFAULT_FEEDER_SPEED               =  800.0;
	public static double FEEDER_REVERSE_SPEED               = -200.0;
	
	public static double FEEDER_UNJAM_SPEED                 = -400.0;
	
	// ***************************************************************************************
	// Ball Pickup constants
	
	public static double SWEEPER_VOLTAGE_PICKUP				= 1.0;
	public static double TRANSPORT_VOLTAGE_PICKUP			= 1.5;
	
	public static double SWEEPER_VOLTAGE_REVERSE			= 1.0;
	public static double TRANSPORT_VOLTAGE_REVERSE			= 1.5;
	
	public static double TRANSPORT_SLOW_VOLTAGE             = 0.5;
	
	// ***************************************************************************************
	// Ultrasonic constants

	public static double ULTRASONIC_X1						= 0.086669;
	public static double ULTRASONIC_Y1						= 10.0;

	public static double ULTRASONIC_X2						= 0.467592;
	public static double ULTRASONIC_Y2						= 50.0;

	// ***************************************************************************************
	// Automated aim and shoot constants
	
	public static double IMAGE_ANGLE_ENCODER_P              =  0.01;
	public static double IMAGE_ANGLE_ENCODER_I              =  0.000;
	public static double IMAGE_ANGLE_ENCODER_D              =  0.0;

	public static double IMAGE_ANGLE_MINIMUM_INPUT          = -1000.0;
	public static double IMAGE_ANGLE_MAXIMUM_INPUT          =  1000.0;	

	public static double IMAGE_ANGLE_MINIMUM_OUTPUT         = -0.4;
	public static double IMAGE_ANGLE_MAXIMUM_OUTPUT         =  0.4;	
		
	public static double IMAGE_ANGLE_JOG_DISTANCE           =  5.0;
	
	// ***************************************************************************************
	// Target constants
	public static double DEVIATION_FROM_TARGET              = 6.0;
	public static double CAM_FOV                            = 48.0;

	public static double LIFT_DISTANCE                      = 20.0;
	
	public static double DISTANCE_A                         =  0.0839;
	public static double DISTANCE_B                         = -16.762;
	public static double DISTANCE_C                         =  967.84;
	
	// y = Ax2 + Bx + C
	public static double IMAGE_Y_A                          =  0.0003;
	public static double IMAGE_Y_B                          = -0.0462;
	public static double IMAGE_Y_C                          =  183.46;
	
	public static int    IMAGE_ON_TARGET_X_POSITION         =   170;
	public static int    IMAGE_ON_TARGET_Y_OFFSET           =   -25;
}
