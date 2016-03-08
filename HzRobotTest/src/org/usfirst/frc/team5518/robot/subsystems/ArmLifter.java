package org.usfirst.frc.team5518.robot.subsystems;

import org.usfirst.frc.team5518.robot.Robot;
import org.usfirst.frc.team5518.robot.RobotMap;
import org.usfirst.frc.team5518.robot.commands.armlifter.MoveArms;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ArmLifter extends Subsystem {
	
	public static final String SUBSYSTEM = "ArmLifter";
	
	private static final double POT_FULL = 3600;
	private static final double POT_TRAVEL = 0.73;
	private static final double LEFT_POT_SHIFT = 0;
	private static final double RIGHT_POT_SHIFT = 0;
	
	private static double ARM_LEFT_MIN = 4.8;
	private static double ARM_LEFT_MAX = 13.4;
	private static double ARM_RIGHT_MIN = 1.6;
	private static double ARM_RIGHT_MAX = 13.0;
	
	VictorSP leftMtr;
	VictorSP rightMtr;
	Potentiometer leftPot;
	Potentiometer rightPot;
	
	DoubleSolenoid solenoid;
	Compressor compressor;
	
	/*PIDController leftArm;
	PIDController rightArm;*/
    
    public ArmLifter() {
    	compressor = new Compressor(RobotMap.COMPRESSOR);
    	
    	solenoid = new DoubleSolenoid(RobotMap.SOLENOID_FORWARD,
    			RobotMap.SOLENOID_REVERSE);
    	
    	leftMtr = new VictorSP(RobotMap.ARM_LEFT_MTR);
    	rightMtr = new VictorSP(RobotMap.ARM_RIGHT_MTR);
    	
    	leftPot = new AnalogPotentiometer(RobotMap.ANALOG_LEFT_POT,
    			POT_FULL, LEFT_POT_SHIFT);
    	rightPot = new AnalogPotentiometer(RobotMap.ANALOG_RIGHT_POT,
    			POT_FULL, RIGHT_POT_SHIFT);
    	
    	solenoid.set(Value.kOff);
    	
    	leftMtr.enableDeadbandElimination(true);
    	rightMtr.enableDeadbandElimination(true);
    	leftMtr.setInverted(true);
    	rightMtr.setInverted(true);
    	
    	/*leftArm = new PIDController(0.0, 0.0, 0.0, leftPot, leftMtr);
    	rightArm = new PIDController(0.0, 0.0, 0.0, rightPot, rightMtr);*/
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new MoveArms());
    }

	/**
	 * 
	 */
	public void init() {
		compressor.setClosedLoopControl(true); // turn on the compressor
		solenoid.set(Value.kOff); // set solenoid state to off
	}
	
	/**
	 * 
	 */
	public void moveArms() {
		leftMtr.set(
				armInput(Robot.oi.getAxis(RobotMap.JOYSTICK_ONE, RobotMap.XBOX_LSTICKY),true));
		rightMtr.set(//SLOW_FACTOR
				armInput(Robot.oi.getAxis(RobotMap.JOYSTICK_ONE, RobotMap.XBOX_RSTICKY),false));
		//		armInput(Robot.oi.getAxis(RobotMap.JOYSTICK_ONE, RobotMap.XBOX_XSTICKY),false));
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
	
	/**
	 * 
	 */
	public void log() {
		SmartDashboard.putBoolean(SUBSYSTEM + " Compressor Status: ", compressor.enabled());
		SmartDashboard.putBoolean(SUBSYSTEM + " Pressure Switch: ", compressor.getPressureSwitchValue());
		
		SmartDashboard.putNumber(SUBSYSTEM + " Left Pot: ", leftPot.get());
		SmartDashboard.putNumber(SUBSYSTEM + " Right Pot: ", rightPot.get());
		SmartDashboard.putNumber(SUBSYSTEM + " Left Arm Travel: ", calcTravel(leftPot.get()));
		SmartDashboard.putNumber(SUBSYSTEM + " Right Arm Travel: ", calcTravel(rightPot.get()));
		
		SmartDashboard.putNumber(SUBSYSTEM + "Arm Input left",
				armInput(Robot.oi.getAxis(RobotMap.JOYSTICK_ONE, RobotMap.XBOX_LSTICKY),true));
		SmartDashboard.putNumber(SUBSYSTEM + "Arm Input right",
				armInput(Robot.oi.getAxis(RobotMap.JOYSTICK_ONE, RobotMap.XBOX_RSTICKY),false));
	}
	
	/**
	 * Calculates actuator travel in inches from potentiometer value
	 * @param angle Raw angle value of sensor (potentiometer)
	 * @return Calculation in degrees based on 8.76 turns to 12" ratio
	 */
	private double calcTravel(double angle) {
		return (angle/(360*POT_TRAVEL));
	}
	
	/**
	 * 
	 * @param axis
	 * @return
	 */
	private double armInput(double axis, boolean left) {
		boolean aboveMin = false;
		boolean belowMax = false;
		//return axis;
		
		if (left) {
			aboveMin = false;//(calcTravel(leftPot.get()) > ARM_LEFT_MIN); 
			belowMax = false;//(calcTravel(leftPot.get()) < ARM_LEFT_MAX);
		} else {
		// check if left & right pot are above min arm limit
		aboveMin = false; //(calcTravel(rightPot.get()) > ARM_RIGHT_MIN); 
				/*&&
				(calcTravel(rightPot.get()) > ARM_MIN);*/
		
		// check if left & right pot are below max arm limit
		belowMax = false; //(calcTravel(rightPot.get()) < ARM_RIGHT_MAX); 
				/*&&
				(calcTravel(rightPot.get()) < ARM_MAX);*/
		}
		
		/*
		 * Return joystick axis if:
		 * 1. Arm is within limits
		 * 2. Arm is below min limit and joystick moving up
		 * 3. and Arm is above max limit and joystick moving down
		 * else return nothing
		 */
		
		double value = 0;
		if (aboveMin && belowMax) {
			value = axis;
		} else if (!aboveMin && (axis < 0)) {
			value = axis;
		} else if (!belowMax && (axis > 0)) {
			value = axis;
		}
		
		return value;
		
	}
	
}

