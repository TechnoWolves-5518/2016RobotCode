package org.usfirst.frc.team5518.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	// Joystick 0 control mapping
	public static int XBOX1_LSTICKY = 1; 
	public static int XBOX1_RSTICKX = 4;
	public static int XBOX1_LTRIGGER = 2;
	public static int XBOX1_RTRIGGER = 3;
	
	public static int XBOX1_YBUTTON = 3;
	public static int XBOX1_XBUTTON = 2;
	public static int XBOX1_ABUTTON = 0;
	public static int XBOX1_BBUTTON = 1; 
	public static int XBOX1_RBUMBER = 5;
	public static int XBOX1_LBUMBER = 4;
	public static int XBOX1_LSTICK = 8;
	public static int XBOX1_RSTICK = 9;
	public static int XBOX1_START = 7;
	public static int XBOX1_BACK = 2;
	
	
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
}
