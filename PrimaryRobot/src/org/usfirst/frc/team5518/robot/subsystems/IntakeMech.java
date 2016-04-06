package org.usfirst.frc.team5518.robot.subsystems;

import org.usfirst.frc.team5518.robot.Robot;
import org.usfirst.frc.team5518.robot.RobotMap;
import org.usfirst.frc.team5518.robot.commands.intakemech.IntakeMtr;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class IntakeMech extends Subsystem {
	
	public static final String SUBSYSTEM = "IntakeMech";
	public static final double FIXED_SPEED = 1.0;
	
	VictorSP intakeMtr;
	
	private boolean btnState = true;
	private boolean blnAlready = false;
	
	public IntakeMech() {
    	intakeMtr = new VictorSP(RobotMap.INTAKE_MTR);
    	
	}
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new IntakeMtr());
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
     * 
     * @param speed
     */
    public void intake(double speed) {
    	// Set intake mtr to full reverse if LB pressed
    	if (Robot.oi.getBtn(RobotMap.JOYSTICK_ONE,
    			RobotMap.XBOX_LBUMBER)) {
    		intakeMtr.set(FIXED_SPEED);
    	// else intake mtr to variable speed from LT
    	} else {
    		intakeMtr.set(-speed);
    	}
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
 
    
    /**
     * 
     */
    //public void invert() {
    	// intakeMtr.setInverted(true);
    //}
    
    /**
     * Log component values.
     */
    public void log() {
    	SmartDashboard.putBoolean(SUBSYSTEM + " btnIntakeState: ", btnState);
    	SmartDashboard.putBoolean(SUBSYSTEM + " blnAlready: ", blnAlready);
    	SmartDashboard.putNumber(SUBSYSTEM + "", intakeMtr.get());	
    }
    
}