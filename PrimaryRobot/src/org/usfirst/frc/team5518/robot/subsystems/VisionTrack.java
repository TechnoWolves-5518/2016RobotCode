package org.usfirst.frc.team5518.robot.subsystems;

import org.usfirst.frc.team5518.robot.commands.visiontrack.StreamCam;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class VisionTrack extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new StreamCam());
    }
    
    /**
     * 
     * @return Return current system time in milliseconds.
     */
     public long init() {
    	 Runtime.getRuntime().exec("");
    	 
    	 return System.currentTimeMillis();
     }
     
     public void trackTower() {
    	 
     }
     
     /**
      * 
      */
     public void log() {
    	 
     }
    
}

