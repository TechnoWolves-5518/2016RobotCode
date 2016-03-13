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
	
	/*public static double ARM_LEFT_MIN = 0.50; // 1.75  .08 .246
	public static double ARM_LEFT_MAX = 11.855; // 13.50 12.0 11.75
	public static double ARM_RIGHT_MIN = 4.05; // 1.60  .04 0.034 1.9465
	public static double ARM_RIGHT_MAX = 13.685; // 13.60  11.50 13.645*/
	
	public static double ARM_LEFT_MIN = 3000;
	public static double ARM_LEFT_MAX = 50;
	public static double ARM_RIGHT_MIN = 2487;
	public static double ARM_RIGHT_MAX = 30;
	
	VictorSP leftMtr;
	VictorSP rightMtr;
	Potentiometer leftPot;
	Potentiometer rightPot;
	
	/*PIDController leftArm;
	PIDController rightArm;*/
    
    public ArmLifter() {
    	leftMtr = new VictorSP(RobotMap.ARM_LEFT_MTR);
    	rightMtr = new VictorSP(RobotMap.ARM_RIGHT_MTR);
    	
    	leftPot = new AnalogPotentiometer(RobotMap.ANALOG_LEFT_POT,
    			POT_FULL, LEFT_POT_SHIFT);
    	rightPot = new AnalogPotentiometer(RobotMap.ANALOG_RIGHT_POT,
    			POT_FULL, RIGHT_POT_SHIFT);
    	
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
	 */
	public void moveArms(double leftAxis, double rightAxis) {
		leftMtr.set(armInput(leftAxis,true));
		rightMtr.set(armInput(rightAxis,false));
	}
	
	/**
	 * 
	 * @return
	 */
	public double[] getPotVals() {
		double[] pot = new double[2];
		pot[0] = leftPot.get();
		pot[1] = rightPot.get();
		
		return pot;
	}
	
	/**
	 * 
	 * @return
	 */
	public double[] getTravel() {
		double[] travel = new double[2];
		travel[0] = calcTravel(leftPot.get());
		travel[1] = calcTravel(rightPot.get());
		
		return travel;
	}
	
	/**
	 * 
	 */
	public void log() {
		SmartDashboard.putNumber(SUBSYSTEM + " Left Pot: ", leftPot.get());
		SmartDashboard.putNumber(SUBSYSTEM + " Right Pot: ", rightPot.get());
		SmartDashboard.putNumber(SUBSYSTEM + " Left Arm Travel: ", calcTravel(leftPot.get()));
		SmartDashboard.putNumber(SUBSYSTEM + " Right Arm Travel: ", calcTravel(rightPot.get()));
		
		/*SmartDashboard.putNumber(SUBSYSTEM + "Arm Input left",
				armInput(Robot.oi.getAxis(RobotMap.JOYSTICK_ONE, RobotMap.XBOX_LSTICKY),true));
		SmartDashboard.putNumber(SUBSYSTEM + "Arm Input right",
				armInput(Robot.oi.getAxis(RobotMap.JOYSTICK_ONE, RobotMap.XBOX_RSTICKY),false));*/
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
			// check if left pot/arm are in between limits
			//aboveMin = !(leftPot.get() > ARM_LEFT_MIN); 
			//belowMax = !(leftPot.get() < ARM_LEFT_MAX);
		} else {
			// check if right pot/arm are in between limits
			//aboveMin = !(leftPot.get() > ARM_RIGHT_MIN); 
			//belowMax = !(leftPot.get() < ARM_RIGHT_MAX); 
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

