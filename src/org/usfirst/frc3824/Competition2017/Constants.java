package org.usfirst.frc3824.Competition2017;

public class Constants
{
	// ***************************************************************************************
	// Drive straight constants
	
	public static double DRIVETRAIN_DRIVE_STRAIGHT_P		= 0.1;
	public static double DRIVETRAIN_DRIVE_STRAIGHT_I		= 0.0;
	public static double DRIVETRAIN_DRIVE_STRAIGHT_D		= 0.0;

	public static double DRIVETRAIN_DRIVE_MINIMUM_OUTPUT	= -1.0;
	public static double DRIVETRAIN_DRIVE_MAXIMUM_OUTPUT	=  1.0;

	public static double DRIVETRAIN_DRIVE_TOLERANCE			= 0.0;

	// ***************************************************************************************
	// Chassis turn constants

	public static double TURN_ANGLE_P						= 0.05;
	public static double TURN_ANGLE_I						= 0.0;
	public static double TURN_ANGLE_D						= 0.0;

	public static double TURN_ANGLE_MINIMUM_OUTPUT			= -0.9;
	public static double TURN_ANGLE_MAXIMUM_OUTPUT			=  0.9;

	public static double TURN_THRESHOLD						= 5.0;
	
	// ***************************************************************************************
	// Climber constants
		
	public static int    CLIMBER_BUTTON_OFF                 =  15;
	public static int    CLIMBER_BUTTON_FAST                =  14;
	
	public static double CLIMBER_SPEED_FAST                 = -0.5;
	public static double CLIMBER_SPEED_SLOW                 = -0.35;
	public static double CLIMBER_SPEED_HOLD                 = -0.2;
	
	public static double CLIMBER_JOG_TIME                 	=  0.25;

	// ***************************************************************************************
	// Gear constants
	
	public static double GEAR_PLACE_TIME                    =  1.0;
	
	// ***************************************************************************************
	// Shooter constants
	
	public static double SHOOTER_P							= 0.0600;
	public static double SHOOTER_I							= 0.0004;
	public static double SHOOTER_D							= 4.0000;
	public static double SHOOTER_F							= 0.0050;
	public static double DEFAULT_SHOOTER_SPEED              = 1500.0;
	
	public static double SHOOTER_SPEED_HIGH                 = 3000.0;
	public static double SHOOTER_SPEED_MED                  = 2000.0;
	public static double SHOOTER_SPEED_LOW                  = 1000.0;
	public static double SHOOTER_JOG_SPEED                  = 200.0;
	
	public static double FEEDER_CLEAR_BALLS_TIME            = 0.5;
	public static double SHOOTER_SPINUP_TIME                = 1.0;
	
	public static double SHOOTER_UNJAM_SPEED                = -600.0;
	
	// ***************************************************************************************
	// Feeder constants
	
	public static double FEEDER_P							=  0.1000;
	public static double FEEDER_I							=  0.0010;
	public static double FEEDER_D							=  4.0000;
	public static double FEEDER_F							=  0.1000;
	public static double DEFAULT_FEEDER_SPEED               =  800.0;
	public static double FEEDER_REVERSE_SPEED               = -200.0;
	
	public static double FEEDER_UNJAM_SPEED                 = -400.0;
	
	// ***************************************************************************************
	// Ball Pickup constants
	
	public static double SWEEPER_VOLTAGE					= 1.0;
	public static double TRANSPORT_VOLTAGE					= 1.5;
	
	// ***************************************************************************************
	// Ultrasonic constants

	public static double ULTRASONIC_X1						= 0.086669;
	public static double ULTRASONIC_Y1						= 10.0;

	public static double ULTRASONIC_X2						= 0.467592;
	public static double ULTRASONIC_Y2						= 50.0;

	// ***************************************************************************************
	// Automated aim and shoot constants
	
	public static double IMAGE_ANGLE_ENCODER_P              =  0.3;
	public static double IMAGE_ANGLE_ENCODER_I              =  0.0005;
	public static double IMAGE_ANGLE_ENCODER_D              =  0.0;

	public static double IMAGE_ANGLE_MINIMUM_INPUT          = -1000.0;
	public static double IMAGE_ANGLE_MAXIMUM_INPUT          =  1000.0;	

	public static double IMAGE_ANGLE_MINIMUM_OUTPUT         = -0.4;
	public static double IMAGE_ANGLE_MAXIMUM_OUTPUT         =  0.4;	
		
	public static double IMAGE_ANGLE_JOG_DISTANCE           =  15.0;

	
	// ***************************************************************************************
	// Target constants
	public static double DEVIATION_FROM_TARGET              = 2;
	public static double CAM_FOV                            = 48.0;
	
	
	public static double DISTANCE_A                           =  0.0839;  //  0.0838;   // x^2
	public static double DISTANCE_B                           = -16.762;  // -16.665;   // x
	public static double DISTANCE_C                           =  967.84;  //  952.25;   // offset
	
	// y = 0.0003x2 - 0.0462x + 183.46
	public static double IMAGE_Y_A                            =   0.0003;  // 0.0001;    //0.00005; //   0.0001;    // x^2
	public static double IMAGE_Y_B                            =  -0.0462;  // 0.0534;    //0.1887;  //   0.0534;    // x
	public static double IMAGE_Y_C                            =   183.46;  // 172.81;    //141.11;  //   172.81;    // offset
	
	//                                                       2016: Thing 1  // Thing 2  // Thing 1
	public static int    IMAGE_ON_TARGET_X_POSITION           =   170;  // 170;        // X location of the "onTarget" position
	public static int    IMAGE_ON_TARGET_Y_OFFSET             =   -25;  // -25;

}
