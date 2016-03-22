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
	
	VictorSP intakeMtr;
	
	public boolean btnIntakeState = true;
	public boolean blnAlready = false;
	
	public IntakeMech() {
    	intakeMtr = new VictorSP(RobotMap.INTAKE_MTR);
    	
	}
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new IntakeMtr());
        
    }
    
    /**
     * 
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
    		intakeMtr.set(-1);
    	// else intake mtr to variable speed from LT
    	} else {
    		intakeMtr.set(speed);
    	}
    }
    
    /**
     * 
     */
    public void toggleIntakeMtrDir(){
    	if (!blnAlready && (Robot.oi.getBtn(RobotMap.JOYSTICK_ONE,
    			RobotMap.XBOX_LBUMBER))) {
    		blnAlready = true;
    		if (btnIntakeState)
        		btnIntakeState = false;
        	else
	        	btnIntakeState = true;
	    } else if (!Robot.oi.getBtn(RobotMap.JOYSTICK_ONE, RobotMap.XBOX_LBUMBER)) {
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
    	SmartDashboard.putBoolean(SUBSYSTEM + " btnIntakeState: ", btnIntakeState);
    	SmartDashboard.putBoolean(SUBSYSTEM + " blnAlready: ", blnAlready);
    	SmartDashboard.putNumber(SUBSYSTEM + "", intakeMtr.get());	
    }
    
}