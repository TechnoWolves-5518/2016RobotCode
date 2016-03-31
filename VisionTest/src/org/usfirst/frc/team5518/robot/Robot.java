
package org.usfirst.frc.team5518.robot;

import java.util.ArrayList;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.USBCamera;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	private static final int CAM_WIDTH = 640;
	private static final int CAM_HEIGHT = 480;
	private static final int MAX_FPS = 15; // The maximum fps for all of the cameras
	private static final int QUALITY = 30; // The quality of image to push back to the driver station. Lower numbers save more bandwidth (0-100)
	private static final long SLEEP_TIME = 100; // Time to sleep after changing camera views. This is to prevent errors as USBCamera.startCapture() returns before it is ready to be seen
	
	private static final String[] CAM_NAMES = new String[] {
			"cam0", "cam1"
	};
	
	private ArrayList<USBCamera> cams; // The list of all attached cameras
	private USBCamera cam; // The current camera we are viewing.
	private int currCam; // The index of the current camera we are looking at
	private Image frame; // The image to push to the CameraServer
	
	private Joystick stick;
	
	public boolean btnState = true;
	public boolean blnAlready = false;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	stick = new Joystick(0);
    	
    	cams = new ArrayList<USBCamera>();
	   	 currCam = 0;
	   	 
	   	 frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
	   	 CameraServer.getInstance().setQuality(QUALITY);
	   	 
	   	 for (String s : CAM_NAMES) {
	   		 addCamera(s);
	   	 }
	   	 
	   	 cam = cams.get(currCam);
	   	 cam.openCamera();
	   	 cam.startCapture();
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
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	cams.get(currCam).getImage(frame);
    	CameraServer.getInstance().setImage(frame);
   	 
    	if (btnState)
    		setCam(1);
    	else
    		setCam(0);
    	
    	toggleCtrl(8);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    }
    
    /**
     * Change output camera
     * @param cam Index of camera in ArrayList
     */
    public void setCam(int camNum) {
    	if (camNum != currCam) {
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
    	}
    }
    
    /**
     * Adds the camera to our list to switch between and sets the FPS max
     * @param camName The name of the camera
     */
    private void addCamera(String camName){
    	USBCamera temp = new USBCamera(camName);
    	//temp.setFPS(MAX_FPS);
    	cams.add(temp);
    	temp = null;
    }
    
    /**
     * 
     * @param btnNum
     */
    public void toggleCtrl(int btnNum){
    	boolean btn = stick.getRawButton(btnNum);
    	
    	if (!blnAlready && btn) {
    		blnAlready = true;
    		if (btnState)
        		btnState = false;
        	else
	        	btnState = true;
	    } else if (!btn) {
	    		blnAlready = false;
	    }	
    }
    
}
