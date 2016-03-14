package org.usfirst.frc.team5518.robot.subsystems;

import org.usfirst.frc.team5518.robot.RobotMap;
import org.usfirst.frc.team5518.robot.commands.armlifter.MoveArms;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
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
	//private static final double RIGHT_POT_SHIFT = 0;
	
	/*public static double ARM_LEFT_MIN = 0.50; // 1.75  .08 .246
	public static double ARM_LEFT_MAX = 11.855; // 13.50 12.0 11.75
	public static double ARM_RIGHT_MIN = 4.05; // 1.60  .04 0.034 1.9465
	public static double ARM_RIGHT_MAX = 13.685; // 13.60  11.50 13.645*/
	
	public static double ARM_LEFT_MIN = 3000;
	public static double ARM_LEFT_MAX = 50;
	/*public static double ARM_RIGHT_MIN = 2487;
	public static double ARM_RIGHT_MAX = 30;*/
	
	VictorSP leftMtr;
	VictorSP rightMtr;
	Potentiometer leftPot;
	//Potentiometer rightPot;
	
	/*DigitalInput leftMinLimit;
	DigitalInput leftMaxLimit;*/
	DigitalInput rightMinLimit;
	DigitalInput rightMaxLimit;
	
	/*PIDController leftArm;
	PIDController rightArm;*/
    
    public ArmLifter() {
    	//Arm Motor Driver Object Definition
    	leftMtr = new VictorSP(RobotMap.ARM_LEFT_MTR);
    	rightMtr = new VictorSP(RobotMap.ARM_RIGHT_MTR);
    	
    	leftPot = new AnalogPotentiometer(RobotMap.ANALOG_LEFT_POT,
    			POT_FULL, LEFT_POT_SHIFT);
    	/*rightPot = new AnalogPotentiometer(RobotMap.ANALOG_RIGHT_POT,
    			POT_FULL, RIGHT_POT_SHIFT);*/
    	
    	//Define Max and min limit switches
    	/*leftMinLimit = new DigitalInput(RobotMap.DIO_LIMIT_LMIN);
    	leftMaxLimit = new DigitalInput(RobotMap.DIO_LIMIT_LMAX);*/
    	rightMinLimit = new DigitalInput(RobotMap.DIO_LIMIT_RMAX);
    	rightMaxLimit = new DigitalInput(RobotMap.DIO_LIMIT_RMIN);
    	
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
	}
	
	/**
	 * 
	 * @param leftAxis
	 * @param rightAxis
	 */
	public void moveArms(double leftAxis, double rightAxis) {
		leftMtr.set(armInput(leftAxis, true));
		rightMtr.set(armInput(rightAxis, false));
	}
	
	/**
	 * 
	 * @param axis
	 */
	public void moveLeftArm(double axis) {
		leftMtr.set(armInput(axis,true));
	}
	
	/**
	 * 
	 * @param axis
	 */
	public void moveRightArm(double axis) {
		rightMtr.set(armInput(axis, false));
	}
	
	/**
	 * 
	 * @return
	 */
	public double getLeftPot() {
		/*double[] pot = new double[2];
		pot[0] = leftPot.get();
		pot[1] = rightPot.get();*/
		
		return leftPot.get();
	}
	
	/**
	 * 
	 * @return
	 */
	public double getLeftTravel() {
		/*double[] travel = new double[2];
		travel[0] = calcTravel(leftPot.get());
		travel[1] = calcTravel(rightPot.get());	*/
		
		return calcTravel(leftPot.get());
	}
	
	/**
	 * 
	 * @return
	 */
//	public boolean isLeftArmExceeded() {
//		return leftMinLimit.get() ||
//				leftMaxLimit.get();
//	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isRightArmExceeded() {
		return rightMinLimit.get() ||
				rightMaxLimit.get();
	}
	
	/**
	 * 
	 */
	public void log() {
		SmartDashboard.putBoolean(SUBSYSTEM + " Right Min Limit Switch: ", !rightMinLimit.get());
		SmartDashboard.putBoolean(SUBSYSTEM + " Right Max Limit Switch: ", rightMaxLimit.get());
		
		SmartDashboard.putNumber(SUBSYSTEM + " Left Pot: ", leftPot.get());
		//SmartDashboard.putNumber(SUBSYSTEM + " Right Pot: ", rightPot.get());
		SmartDashboard.putNumber(SUBSYSTEM + " Left Arm Travel: ", calcTravel(leftPot.get()));
		//SmartDashboard.putNumber(SUBSYSTEM + " Right Arm Travel: ", calcTravel(rightPot.get()));
	}
	
	/**
	 * Calculates actuator travel in inches from potentiometer value
	 * @param angle Raw angle value of sensor (potentiometer)
	 * @return Calculation in degrees based on 8.76 turns to 12" ratio
	 */
	private double calcTravel(double angle) {
		return ((POT_FULL-angle)/(360*POT_TRAVEL));
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
			// check if left arm is in between limits
			/*aboveMin = !leftMinLimit.get();
			belowMax = !leftMaxLimit.get();*/
			aboveMin = !(leftPot.get() > ARM_LEFT_MIN); 
			belowMax = !(leftPot.get() < ARM_LEFT_MAX);
		} else {
			// check if right arm is in between limits
			aboveMin = !rightMinLimit.get();
			belowMax = !rightMaxLimit.get();
			/*aboveMin = !(leftPot.get() > ARM_RIGHT_MIN); 
			belowMax = !(leftPot.get() < ARM_RIGHT_MAX);*/
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

