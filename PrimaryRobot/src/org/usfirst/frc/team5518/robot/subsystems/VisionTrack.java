package org.usfirst.frc.team5518.robot.subsystems;

import java.io.IOException;

import org.usfirst.frc.team5518.robot.Robot;
import org.usfirst.frc.team5518.robot.commands.visiontrack.StreamCam;
import org.usfirst.frc.team5518.robot.commands.visiontrack.TargetCompute;

import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class VisionTrack extends Subsystem {
	
	private static final int CAM_WIDTH = 320;
	private static final int CAM_HEIGHT = 240;
	private static final double MARGIN_X = CAM_WIDTH*0.05;
	private static final double MARGIN_Y = CAM_HEIGHT*0.05;
	
	private static final double[] defaultVals = new double[0];
	
	private Process process;
	private NetworkTable table;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new StreamCam());
    }
    
    /**
     * Called in the init method of
     * associated commands of the subsystem.
     * 
     * @return Return current system time in milliseconds.
     */
     public long init() {
    	 return System.currentTimeMillis();
     }
     
     /**
      * Runs a script to start mjpg-streamer at
      * http://roborio-5518-frc.local:1180/?action=stream
      */
     public void streamCam() {
    	 try {
 			process = Runtime.getRuntime().exec("sh /home/admin/mjpg.sh");
 		} catch (IOException e) {
 			//e.printStackTrace();
 		}
     }
     
     /**
      * 
      */
     public void targetCompute() {
    	 // get the table GRIP outputs to from NetworkTables
    	 /*table = NetworkTable.getTable("GRIP/myContoursReport");
    	 
    	 // extract the center point of the target found by GRIP
    	 double centerX = table.getNumberArray("centerX", defaultVals)[0];
    	 double centerY = table.getNumberArray("centerY", defaultVals)[0];
    	 
    	 // check whether x-value is in center with a 5% margin
    	 boolean isAlignedX = ((centerX > ((CAM_WIDTH/2) - MARGIN_X)) && 
    			 (centerX < ((CAM_WIDTH/2) + MARGIN_X)));
    	 
    	 // check whether y-value is in center with a 5% margin
    	 boolean isAlignedY = ((centerY > ((CAM_HEIGHT/2) - MARGIN_Y)) && 
    			 (centerY < ((CAM_HEIGHT/2) + MARGIN_Y)));
    	 
    	 // rumble the controller if the target is aligned
    	 if (isAlignedX && isAlignedY)
    		 Robot.oi.setRumble(RumbleType.kRightRumble, 0.75f);*/
     }
     
     /**
      * 
      */
     public void targetComputeAuto() {
     }
     
     /**
      * 
      */
     public void log() {
    	 
     }
     
     /**
      * 
      */
     public void end() {
    	 process.destroy();
     }
    
}

