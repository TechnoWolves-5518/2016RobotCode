package org.usfirst.frc.team5518.robot.subsystems;

import java.io.IOException;

import org.usfirst.frc.team5518.robot.commands.visiontrack.StreamCam;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class VisionTrack extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Process process;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new StreamCam());
    }
    
    /**
     * 
     * @return Return current system time in milliseconds.
     */
     public long init() {
    	try {
			process = Runtime.getRuntime().exec("cd /home/admin/mjpg-streamer-182 && "
					+ "export LD_LIBRARY_PATH=./ && "
					+ "./mjpg_streamer -i 'input_uvc.so' -o 'output_http.so -w ./www'");
		} catch (IOException e) {
			e.printStackTrace();
		}
    	 
    	 return System.currentTimeMillis();
     }
     
     public void trackTower() {
    	 
     }
     
     /**
      * 
      */
     public void log() {
    	 
     }
     
     public void end() {
    	 process.destroy();
     }
    
}

