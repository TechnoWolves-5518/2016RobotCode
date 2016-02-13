package org.usfirst.frc.team5518.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This is an example subsystem that won't have any
 * functionality on the robot and only serves reference purposes.
 */
public class Claw extends Subsystem {
	
	// Define components in the subsystem
	VictorSP randomVictor;
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    /**
     * Initialize all the components necessary for
     * this subsystem and return time in seconds.
     */
    public double initialize() {
    	randomVictor = new VictorSP(9); // init VictorSP at PWM Port 9 (put port # in RobotMap.java)
    	randomVictor.enableDeadbandElimination(true); // remove deadband from middle of value range
    	
    	return getTime();
    }
    
    /**
     * Set motor value (speed) at 0.5 to
     * open claw at half speed.
     */
    public void open() {
    	randomVictor.set(0.5);
    }
    
    /**
     * Set motor value (speed) at -0.5 to
     * close claw at half speed.
     */
    public void close() {
    	randomVictor.set(-0.5);
    }
    
    /**
     * Return robot running time in seconds.
     */
    public double getTime() {
    	return Timer.getFPGATimestamp();
    }
    
}

