package org.usfirst.frc.team5518.robot.subsystems;

import org.usfirst.frc.team5518.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Hooker extends Subsystem {
	
	public static final String SUBSYSTEM = "Hooker";
	
	DoubleSolenoid solenoid;
	Compressor compressor;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public Hooker() {
		compressor = new Compressor(RobotMap.COMPRESSOR);
    	solenoid = new DoubleSolenoid(RobotMap.SOLENOID_FORWARD,
    			RobotMap.SOLENOID_REVERSE);
    	
    	solenoid.set(Value.kOff);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * Called in the init method of
     * associated commands of the subsystem.
     * 
     * @return Return current system time in milliseconds.
     */
    public long init() {
    	compressor.setClosedLoopControl(true); // turn on the compressor
		solenoid.set(Value.kOff); // set solenoid state to off
		
		return System.currentTimeMillis();
    }
    
    /**
	 * Open the pneumatic cylinders
	 */
	public void openCylinders() {
		solenoid.set(Value.kForward); // set solenoid state to forward
	}
	
	/**
	 * Close the pneumatic cylinders
	 */
	public void closeCylinders() {
		solenoid.set(Value.kReverse); // set solenoid state to reverse
	}
	
	public void log() {
		SmartDashboard.putBoolean(SUBSYSTEM + " Compressor Status: ", compressor.enabled());
		SmartDashboard.putBoolean(SUBSYSTEM + " Pressure Switch: ", compressor.getPressureSwitchValue());
	}
    
    
}

