package org.usfirst.frc.team5518.robot.subsystems;

import org.usfirst.frc.team5518.robot.RobotMap;
import org.usfirst.frc.team5518.robot.commands.drivetrain.FineWheelCtrl;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * DriveTrain subsystem handles all of the wheels'
 * speed controllers and CIM motors.
 */
public class DriveTrain extends Subsystem {
	
	public static final String SUBSYSTEM = "DriveTrain";
	
	//Defining Objects
	RobotDrive robotDrive;
	VictorSP flMotor;
	VictorSP rlMotor;
	VictorSP frMotor;
	VictorSP rrMotor;
	
	public DriveTrain() {
		//Init motors and maping
		flMotor = new VictorSP(RobotMap.FRONT_LEFT_MTR);
		rlMotor = new VictorSP(RobotMap.REAR_LEFT_MTR);
		frMotor = new VictorSP(RobotMap.FRONT_RIGHT_MTR);
		rrMotor = new VictorSP(RobotMap.REAR_RIGHT_MTR);
		
		//Make sure that the robot can see the Joysticks as being at zero
		flMotor.enableDeadbandElimination(true);
		rlMotor.enableDeadbandElimination(true);
		frMotor.enableDeadbandElimination(true);
		rrMotor.enableDeadbandElimination(true);
		
		//Define robot drive
		robotDrive = new RobotDrive(flMotor, rlMotor, frMotor, rrMotor);
		robotDrive.setSafetyEnabled(true);
		robotDrive.setExpiration(0.5);
	}
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new FineWheelCtrl());
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
     * Drive the robot according mapped controller controls
     * @param fineCtrl If set, decrease the sensitivity at low speeds.
     */
    public void drive(double[] axis, boolean fineCtrl) {
    	robotDrive.arcadeDrive(axis[0], axis[1], fineCtrl);
    }
    
    /**
     * Drive the robot in opposite directions according to mapped
     * controller controls.
     * @param fineCtrl If set, decrease the sensitivity at low speeds.
     */
    public void invert(double[] axis, boolean fineCtrl) {
    	robotDrive.arcadeDrive(-axis[0], -axis[1], fineCtrl);
    }
    
    /**
     * Log component values.
     */
    public void log() {
    	SmartDashboard.putNumber(SUBSYSTEM + " FL Motor: ", flMotor.get());
    	SmartDashboard.putNumber(SUBSYSTEM + " RL Motor: ", rlMotor.get());
    	SmartDashboard.putNumber(SUBSYSTEM + " FR Motor: ", frMotor.get());
    	SmartDashboard.putNumber(SUBSYSTEM + " RR Motor: ", rrMotor.get());
    }
    
}

