
package org.usfirst.frc.team5518.robot;

import org.usfirst.frc.team5518.robot.commands.DrivePortcullis;
import org.usfirst.frc.team5518.robot.commands.autonomous.DefaultAuto;
import org.usfirst.frc.team5518.robot.commands.autonomous.PullAndDrive;
import org.usfirst.frc.team5518.robot.subsystems.ArmLifter;
import org.usfirst.frc.team5518.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5518.robot.subsystems.Hooker;
import org.usfirst.frc.team5518.robot.subsystems.IntakeMech;
/*	Beginning of Autonomous Mode Code	
import org.usfirst.frc.team5518.robot.commands.autonomous.Autonomous;
*/
import org.usfirst.frc.team5518.robot.subsystems.Sensor;
import org.usfirst.frc.team5518.robot.subsystems.Shooter;
import org.usfirst.frc.team5518.robot.subsystems.VisionTrack;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/* Start of Autonomous Code
 * import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.command.Command;*/

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	private static final String AUTO_CHOOSER = "AUTONOMOUS MODE SELECTOR";
	private static final String DEFAULT_AUTO = "Default Auto";
	private static final String DISABLE_AUTO = "Disable Auto";
	private static final String PORTCULLIS_AUTO = "Portcullis Auto";
	/*private static final String LIFT_DRIVE_AUTO = "Lift and Drive Auto";	
	private static final String PULL_DRIVE_AUTO = "Pull and Drive Auto";
*/
	public static final DriveTrain driveTrain = new DriveTrain();
	public static final Shooter shooter = new Shooter();
	public static final IntakeMech intakeMech = new IntakeMech();
	public static final ArmLifter armLifter = new ArmLifter();
	public static final Hooker hooker = new Hooker();
	public static final Sensor sensor = new Sensor();
	public static final VisionTrack visionTrack = new VisionTrack();  
	
	public static OI oi;
	
	//	Beginning of Autonomous Mode Code	
    Command autonomousCmd;
    SendableChooser chooser;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	visionTrack.streamCam();
    	
		oi = new OI();
		SmartDashboard.putData(driveTrain);
		SmartDashboard.putData(shooter);
		SmartDashboard.putData(intakeMech);
		SmartDashboard.putData(armLifter);
		SmartDashboard.putData(hooker);
		SmartDashboard.putData(sensor);
		//SmartDashboard.putData(visionTrack);		<-- Zak
		
		//	Beginning of Autonomous Mode Code	
        chooser = new SendableChooser();
        chooser.addDefault(DEFAULT_AUTO, new DefaultAuto());
        chooser.addDefault(DISABLE_AUTO, null);
        /*chooser.addObject(LIFT_DRIVE_AUTO, new LiftAndDrive());
        chooser.addObject(PULL_DRIVE_AUTO, new PullAndDrive());*/
        chooser.addObject(PORTCULLIS_AUTO, new DrivePortcullis());
        SmartDashboard.putData(AUTO_CHOOSER, chooser);
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
    	//	Beginning of Autonomous Mode Code	
    	autonomousCmd = (Command) chooser.getSelected();
        
    	// add command and schedule autonomous
    	if (autonomousCmd != null) {
	    	autonomousCmd.start();
    	}
    	
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        
        new Runnable() {
			public void run() {
		        sensor.log();
			}
		}.run();
    }

    public void teleopInit() {
        if (autonomousCmd != null) autonomousCmd.cancel();   //This line makes sure that autonomous stops runing when teleop start running
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
        new Runnable() {
			public void run() {
				driveTrain.log();
		        shooter.log();
		        //armLifter.log();
		        sensor.log();
				intakeMech.log();
			}
		}.run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
}
