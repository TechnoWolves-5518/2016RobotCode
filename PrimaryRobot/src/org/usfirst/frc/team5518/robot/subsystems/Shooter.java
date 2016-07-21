package org.usfirst.frc.team5518.robot.subsystems;

import org.usfirst.frc.team5518.robot.Robot;
import org.usfirst.frc.team5518.robot.RobotMap;
import org.usfirst.frc.team5518.robot.commands.shooter.Shoot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends Subsystem {
	
	public static final String SUBSYSTEM = "Shooter";
	public static final double FIXED_SPEED = 0.25;
	
	VictorSP btmShootMtr;
	VictorSP topShootMtr;
	
	Preferences prefs;
	
	private boolean btnState = true;
	private boolean blnAlready = false;
		
	public Shooter() {
		btmShootMtr = new VictorSP(RobotMap.BTM_SHOOT_MTR);
    	topShootMtr = new VictorSP(RobotMap.TOP_SHOOT_MTR);
    	btmShootMtr.setInverted(true);
	}
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new Shoot());
    }
    
    /**
     * Called in the init method of
     * associated commands of the subsystem.
     * 
     * @return Return current system time in milliseconds.
     */
    public long init() {
    	prefs = Preferences.getInstance();
    	return System.currentTimeMillis();
    }
    
    /**
     * See section below
     */
    public void shoot(double[] speeds) {
    	// Set intake mtr to full reverse if RB pressed
    	if (Robot.oi.getBtn(RobotMap.JOYSTICK_ONE, 
    			RobotMap.XBOX_RBUMBER)) {
    		btmShootMtr.set(FIXED_SPEED);
    		topShootMtr.set(FIXED_SPEED);
    		// else shooter motors to variable speed from RT
    	} else {
    		
    		double limit = prefs.getDouble("shooter", 0.75);
    		btmShootMtr.set(speeds[0]*limit);
    		topShootMtr.set(speeds[1]*limit);
    	}
    }

    /**
     * Log component values.
     */
    public void log() {
    	SmartDashboard.putNumber(SUBSYSTEM + "", btmShootMtr.get());
    	SmartDashboard.putNumber(SUBSYSTEM + "", topShootMtr.get());
    }
    
    /**
     * 
     * @param btnNum
     */
    private void toggleCtrl(int btnNum){
    	boolean btn = Robot.oi.getBtn(RobotMap.JOYSTICK_ONE,
    			btnNum);
    	
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

