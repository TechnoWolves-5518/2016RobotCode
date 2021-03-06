package org.usfirst.frc.team5518.robot.subsystems;

import java.io.IOException;

import org.usfirst.frc.team5518.robot.commands.visiontrack.StreamCam;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class VisionTrack extends Subsystem {
	
	/*private static final int CAM_WIDTH = 640;
	private static final int CAM_HEIGHT = 480;
	private static final int MAX_FPS = 15; // The maximum fps for all of the cameras
	private static final int QUALITY = 30; // The quality of image to push back to the driver station. Lower numbers save more bandwidth (0-100)
	private static final long SLEEP_TIME = 100; // Time to sleep after changing camera views. This is to prevent errors as USBCamera.startCapture() returns before it is ready to be seen
	
	private static final String[] CAM_NAMES = new String[] {
			"cam0", "cam1"
	};*/
	
	/*private static final double MARGIN_X = CAM_WIDTH*0.05;
	private static final double MARGIN_Y = CAM_HEIGHT*0.05;*/
	
	//private static final double[] defaultVals = new double[0];
	
	private Process process;
	private Process process2;
	//private NetworkTable table;
	
	/*private ArrayList<USBCamera> cams; // The list of all attached cameras
	private USBCamera cam; // The current camera we are viewing.
	private int currCam; // The index of the current camera we are looking at
	private Image frame; // The image to push to the CameraServer*/
	
    public void initDefaultCommand() {
        //Set the default command for a subsystem here.
         setDefaultCommand(new StreamCam());
         
    }
    
    /**
     * Called in the init method of
     * associated commands of the subsystem.
     * 
     * @return Return current system time in milliseconds.
     */
     public long init() {
    	 /*Section 2 Test 
    	 cams = new ArrayList<USBCamera>();
    	 currCam = 0;
    	 
    	 frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
    	 CameraServer.getInstance().setQuality(QUALITY);
    	 
    	 for (String s : CAM_NAMES) {
    		 addCamera(s);
    	 }
    	 
    	 cam = cams.get(currCam);
    	 cam.openCamera();
    	 cam.startCapture();*/
    	 
    	 return System.currentTimeMillis();
     }
     
     /**
      * Runs a script to start mjpg-streamer at
      * http://roborio-5518-frc.local:1180/?action=stream
      */
     public void streamCam() {
    	 try {
 			process = Runtime.getRuntime().exec("sh /home/admin/mjpg.sh");
 			process2 = Runtime.getRuntime().exec("sh /home/admin/mjpg2.sh");
    	 } catch (IOException e) {
 			e.printStackTrace();
 			System.out.println("-----------Error while streaming camera-----------");
 			  
 		}
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
//    	 try {
//    		 Runtime.getRuntime().exec("pkill -f './mjpg-streamer'");
			process.destroy();
		    process2.destroy();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
     }
     
     /**
      * Change output camera
      * @param cam Index of camera in ArrayList
      */
     //private void setCam(int camNum) {
     	/*if (camNum != currCam) {
 			new Runnable() {
 				public void run() {
 					try {
 						cam.stopCapture();
 			    		cam.closeCamera();
 			    		currCam = camNum;
 			    		cam = cams.get(camNum);
 			    		cam.openCamera();
 			    		cam.startCapture();
 			    		Thread.sleep(500);
 					} catch (Exception e) {
 						//e.printStackTrace();
 					}
 				}
 			}.run();
     	}*/
     //}
     
     /**
      * Adds the camera to our list to switch between and sets the FPS max
      * @param camName The name of the camera
      */
     /*private void addCamera(String camName){
     	/*USBCamera temp = new USBCamera(camName);
     	//temp.setFPS(MAX_FPS);
     	cams.add(temp);
     	temp = null;
     }*/
     
     
     //public void targetCompute() {
    	 // get the table GRIP outputs to from NetworkTables
    	 /* Need to Figure  out/ May not be needed anymore
    	  * table = NetworkTable.getTable("GRIP/myContoursReport");
    	 
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
     //}
     
}

