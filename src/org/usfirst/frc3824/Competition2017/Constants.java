package org.usfirst.frc3824.Competition2017;

public class Constants
{
	// ***************************************************************************************
	// Drivetrain constants
	public static double TWIST_MULTIPLIER     				=  0.90;
	public static int    DRIVER_REVERSE_BUTTON				= 11;
	
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
	
	public static double CLIMBER_SPEED_FAST                 = -0.75;
	public static double CLIMBER_SPEED_SLOW                 = -0.45;
	public static double CLIMBER_SPEED_HOLD                 = -0.0;
	public static double CLIMBER_SPEED_JOG                  = -0.5;
	
	public static double CLIMBER_STOP_CURRENT               =  45.0;
	public static double CLIMBER_JOG_TIME                 	=   0.4;
	
	public static double CLIMBER_AFTER_LIMIT_SWITCH_TIME    = 0.25;

	// ***************************************************************************************
	// Gear subsystem constants
	
	public static double GEAR_PLACE_TIME                    =  1.0;
	
	public static double ROTATOR_P						    =  0.01;
	public static double ROTATOR_I						    =  0.000;
	public static double ROTATOR_D						    =  0.0;
	public static double ROTATOR_F						    =  0.0;
	
	public static double SUCKER_P						    =  0.01;
	public static double SUCKER_I						    =  0.000;
	public static double SUCKER_D						    =  0.0;
	public static double SUCKER_F						    =  0.0;
	
	public static double ROTATOR_MIN_INPUT					=  -1000.0;
	public static double ROTATOR_MAX_INPUT					=   1000.0;
	
	public static double ROTATOR_MIN_OUTPUT					=  -0.4;
	public static double ROTATOR_MAX_OUTPUT					=   0.4;
	
	public static int    UP_ROTATOR_POS						=  150;
	public static int    DOWN_ROTATOR_POS					=  50;
	public static int    PLACE_ROTATOR_POS					=  100;
	
	// ***************************************************************************************
	// Ultrasonic constants

	public static double ULTRASONIC_X1						= 0.086669;
	public static double ULTRASONIC_Y1						= 10.0;

	public static double ULTRASONIC_X2						= 0.467592;
	public static double ULTRASONIC_Y2						= 50.0;

	// ***************************************************************************************
	// Automated aim constants
	
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
	
	public static int    IMAGE_ON_TARGET_X_POSITION         =   266;  // 170
	public static int    IMAGE_ON_TARGET_Y_OFFSET           =   -25;
	
	// ***************************************************************************************
	// Camera constants
	public static int    CAMERA_BRIGHTNESS                  = 38;   // 50
	public static int 	 CAMERA_EXPOSURE                    = 38;   // 50
	
	// ***************************************************************************************
	// Gyro constants
	public static int    DEFAULT_GYRO_CENTER                = 2079600; // 2079480; // 2079000;
	
	// ***************************************************************************************
	// Gear placing constants
	public static double UNLOCK_TO_PUSH_TIME                = 2.0;
	public static double PUSHER_OUT_TIME                    = 4.0;
}
