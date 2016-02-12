/** The MIT License (MIT)
*
*
* Copyright (c) 2016 Techno Wolves
*
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
*
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
*
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*/

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
