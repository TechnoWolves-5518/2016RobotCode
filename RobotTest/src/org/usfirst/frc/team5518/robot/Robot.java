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

import java.io.IOException;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	private final static String[] GRIP_ARGS = new String[] {
	        "/usr/local/frc/JRE/bin/java", "-jar",
	        "/home/lvuser/grip.jar", "/home/lvuser/project.grip" };
	private final NetworkTable grip = NetworkTable.getTable("grip");
	
	Joystick stick = new Joystick(0); // init input device 0
	Victor mVictor; // variable for Victor
	
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;
    
    NetworkTable mTable;
    
    Ultrasonic mUltra; // global variable for SR04 ultrasonic
    Encoder mEncoder; // variable for encoder
    
    
    public Robot() {
    	// get published table from GRIP for Vision Tracking
    	mTable = NetworkTable.getTable("GRIP/myCountoursReport");
    }
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        
        mVictor = new Victor(4);  // init new Victor at PWM Port 4
        mVictor.enableDeadbandElimination(true); // eliminate deadband
        mVictor.setExpiration(Victor.DEFAULT_SAFETY_EXPIRATION); // set PWM timeout of Victor
        
        mUltra = new Ultrasonic (0,1); // construct Ultrasonic object with DIO port #0 (trig) & port #1 (echo)
        mUltra.setAutomaticMode(true); // set so ultrasonic sensors fire round robin (?)
        
        // construct Encoder with DIO port #8 (channel A) & port #9 (channel B)
        mEncoder = new Encoder(8, 9, false, EncodingType.k4X);
        
        // add sensors and other components to the LiveWindow
        LiveWindow.addActuator("DriveTrain", "victor", mVictor);
        LiveWindow.addSensor("Sensor", "ultrasonic", mUltra);
        LiveWindow.addSensor("Sensor", "encoder", mEncoder);
        
        try {
        	Runtime.getRuntime().exec(GRIP_ARGS);
        } catch (IOException e) {
        	e.printStackTrace();
        }
        
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here   
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    	
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	
    	// run LiveWindow (outputs values for test purposes)
    	LiveWindow.run();
    	
    	// get the value of the ultrasonic sensor in inches and mm
    	String inches = Double.toString(mUltra.getRangeInches());
    	String mm = Double.toString(mUltra.getRangeMM());
    	
    	// set Victor's values to match input axis 0
    	mVictor.set(stick.getRawAxis(0));
    	
    	// log the values
    	SmartDashboard.putString("Ultrasonic SR04 in: ", inches);
    	SmartDashboard.putString("Ultrasonic SR04 mm: ", mm);
    	
    	// log GRIP vision tracking values
    	getGripValues();
    	    
    }
    
    /**
     * This function gets the values from NetworkTable published by GRIP (tool for Vision Tracking)
     * and gets the location of the specified contour(s) to output to the Riolog (Eclipse -> Window -> Show View)
     */
    private void getGripValues() {
    	
    	/* Get published values from GRIP using NetworkTables */
        for (double area : grip.getNumberArray("myCountoursReport/area", new double[0])) {
            System.out.println("Got contour with area = " + area);
        }
    	
        /*double[] defaultValue = new double[0]; // create empty array to store values in
    	double[] areas = mTable.getNumberArray("area", defaultValue); // get the location array of specified contour(s)
    	System.out.println("areas: "); // output to Riolog
   
    	// iterate over location of specified contour(s)
    	for (double area : areas) {
    		System.out.print("area: " + area); 
    	}
    	
    	System.out.println(); // output empty line to Riolog*/
    	
    }
    
}
