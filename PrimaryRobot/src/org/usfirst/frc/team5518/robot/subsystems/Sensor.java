package org.usfirst.frc.team5518.robot.subsystems;

import org.usfirst.frc.team5518.robot.RobotMap;
import org.usfirst.frc.team5518.robot.commands.sensor.SensorOut;

import edu.wpi.first.wpilibj.ADXL362;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 *
 */
public class Sensor extends Subsystem {
	
	public static final String SUBSYSTEM = "Sensor";
	
	ADXL362 accel;
	ADXRS450_Gyro gyro;
	
	Ultrasonic forwardUltra;
	Ultrasonic backwardUltra;
	/*Ultrasonic highLeftUltra;
	Ultrasonic highRightUltra;*/
	
	/*AnalogOutput forwardUltraA0; 
	AnalogOutput backwardUltraA1;*/
	
	private static int forwardUltraD = 0;
    private static int backwardUltraD = 0;
	
	//Variables for Calculating Voltage
    private static int intVltMax = 0;
    private static int intVltMin = 5;
    private static int intDistMax = 0;
    private static int intDistMin = 0;
    private static int intVltSlp = ((intDistMax - intDistMin)/(intVltMax - intVltMin));	//Voltage Slope (x = Distance, y = Voltage)
	
    public Sensor() {
    	accel = new ADXL362(Range.k8G);
    	gyro = new ADXRS450_Gyro();
    	
    	forwardUltra = new Ultrasonic(RobotMap.FORWARD_ULTRASONIC_TRIG,
    			RobotMap.FORWARD_ULTRASONIC_ECHO);
    	backwardUltra = new Ultrasonic(RobotMap.BACKWARD_ULTRASONIC_TRIG,
    			RobotMap.BACKWARD_ULTRASONIC_ECHO);
    	forwardUltra.setAutomaticMode(true);
    	backwardUltra.setAutomaticMode(true);
    	
    	/*forwardUltraA0 = new AnalogOutput(RobotMap.ANALOG_OUTPUT_0);
    	backwardUltraA1 = new AnalogOutput(RobotMap.ANALOG_OUTPUT_1);*/
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	//setDefaultCommand(new SensorOut());
    }
    
    /**
     * 
     */
    public void init() {
    	/*gyro.reset();
    	gyro.calibrate();*/
    }
    
    /**
     * Get the all of the ultrasonic values.
     * @return Return forward and backward sensor values in an array.
     */
    public double[] getUltraVals() {
    	double[] ultra = new double[2];
    	ultra[0] = forwardUltra.getRangeInches();
    	ultra[1] = backwardUltra.getRangeInches();
    	
    	/*ultra[0] = forwardUltraA0.getVoltage();
    	ultra[1] = backwardUltraA1.getVoltage();*/
    	
    	return ultra;
    }
    
    /**
     * Read Ultrasonic Range Sensor Attempting to pass varriables when this method is ran
     */
    
    /* This section needs to be finished and put in both Teleop Perioidic and Autonomous Periodic 
     * so that it can read the distance and be used by other classes*/
    public double[] sensorRead() {
    	double[] ultra = new double[2];
    	ultra[0] = forwardUltra.getRangeInches();
    	ultra[1] = backwardUltra.getRangeInches();
    	
    	return ultra;
    }
    
    /**
     * Analog Data to Arduino for LED Indicators
     */
    public void sensorSend() {
    	//Calculate Voltages to Send Out
    	double intVltOut0 = vltSolve(intVltMax, intVltSlp, intDistMax, forwardUltraD); 
    	double intVltOut1 = vltSolve(intVltMax, intVltSlp, intDistMax, backwardUltraD);
    	
    	/*forwardUltraA0.setVoltage(intVltOut0);
    	backwardUltraA1.setVoltage(intVltOut1);*/
    }
    
    /**
     * Log component values.
     */
    public void log() {
    	SmartDashboard.putNumber(SUBSYSTEM + " Accel X: ", accel.getX());
    	SmartDashboard.putNumber(SUBSYSTEM + " Accel Y: ", accel.getY());
    	SmartDashboard.putNumber(SUBSYSTEM + " Accel Z: ", accel.getZ());
    	SmartDashboard.putNumber(SUBSYSTEM + " Gyro: ", gyro.getRate());
    	
    	
    	SmartDashboard.putNumber(SUBSYSTEM + " Forward Ultrasonic: ", forwardUltra.getRangeInches());
    	SmartDashboard.putNumber(SUBSYSTEM + " Backward Ultrasonic: ", backwardUltra.getRangeInches());
    	/*SmartDashboard.putNumber(SUBSYSTEM + " High Left US: ", highLeftUltra.getRangeInches());
    	SmartDashboard.putNumber(SUBSYSTEM + " High Right US: ", highRightUltra.getRangeInches());*/
    
    	/*SmartDashboard.putNumber(SUBSYSTEM + " Forward Ultrasonic Analog Voltage ", forwardUltraA0.getVoltage());
//    	SmartDashboard.putNumber(SUBSYSTEM + " Backward Ultrasonic Analog Voltage ", backwardUltraA1.getVoltage());*/
    }
    
    /**
     * Calculate the needed output voltage
     * @param a
     * @param b
     * @param c
     * @param d
     * @return
     */
    private double vltSolve(int a, int b, int c, double d) {
    	double vlt = ((b*(d-c))+a);
    	return vlt;
    }
    
}

