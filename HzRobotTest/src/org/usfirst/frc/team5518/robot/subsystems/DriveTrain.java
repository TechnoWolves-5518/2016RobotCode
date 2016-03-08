package org.usfirst.frc.team5518.robot.subsystems;

import org.usfirst.frc.team5518.robot.RobotMap;
import org.usfirst.frc.team5518.robot.commands.drivetrain.FineWheelCtrl;
import org.usfirst.frc.team5518.robot.commands.drivetrain.WheelCtrl;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * DriveTrain subsystem handles all of the wheels'
 * speed controllers and CIM motors.
 */
public class DriveTrain extends Subsystem {
	
	public static final String SUBSYSTEM = "DriveTrain";
	
	RobotDrive robotDrive;
	VictorSP flMotor;
	VictorSP rlMotor;
	VictorSP frMotor;
	VictorSP rrMotor;
	
	public DriveTrain() {
		flMotor = new VictorSP(RobotMap.FRONT_LEFT_MTR);
		rlMotor = new VictorSP(RobotMap.REAR_LEFT_MTR);
		frMotor = new VictorSP(RobotMap.FRONT_RIGHT_MTR);
		rrMotor = new VictorSP(RobotMap.REAR_RIGHT_MTR);
		
		flMotor.enableDeadbandElimination(true);
		rlMotor.enableDeadbandElimination(true);
		frMotor.enableDeadbandElimination(true);
		rrMotor.enableDeadbandElimination(true);
		
		flMotor.setSafetyEnabled(true);
		rlMotor.setSafetyEnabled(true);
		frMotor.setSafetyEnabled(true);
		rrMotor.setSafetyEnabled(true);
		
		flMotor.setExpiration(0.5);
		rlMotor.setExpiration(0.5);		
		frMotor.setExpiration(0.5);
		rrMotor.setExpiration(0.5);
		
		robotDrive = new RobotDrive(flMotor, rlMotor, frMotor, rrMotor);
	}
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new FineWheelCtrl());
    }
    
    /**
     * Initialize all necessary components for subsystem
     * and command
     * @return Return system uptime in seconds
     */
    public double init() {
    	return Timer.getFPGATimestamp();
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
    	robotDrive.arcadeDrive(axis[0], axis[1], fineCtrl);
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

