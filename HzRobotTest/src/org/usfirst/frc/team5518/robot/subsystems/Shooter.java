package org.usfirst.frc.team5518.robot.subsystems;

import org.usfirst.frc.team5518.robot.RobotMap;
import org.usfirst.frc.team5518.robot.commands.shooter.Shoot;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends Subsystem {
	
	public static final String SUBSYSTEM = "Shooter";
	
	VictorSP btmShootMtr;
	VictorSP topShootMtr;
		
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
     * 
     */
    public long init() {
    	return System.currentTimeMillis();
    }
    
    /**
     * See section below
     */
    public void shoot(double[] speeds) {
    		btmShootMtr.set(speeds[0]);
    		topShootMtr.set(speeds[1]);
    }

    /**
     * Log component values.
     */
    public void log() {
    	SmartDashboard.putNumber(SUBSYSTEM + "", btmShootMtr.get());
    	SmartDashboard.putNumber(SUBSYSTEM + "", topShootMtr.get());
    }
    
}

