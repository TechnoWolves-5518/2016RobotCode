package org.usfirst.frc.team5518.robot.subsystems;

import org.usfirst.frc.team5518.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogOutput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 *
 */
public class Sensor extends Subsystem {
	
	public static final String SUBSYSTEM = "Sensor";
	
	// Variables used to set voltage out according to Ultrasonic Sensor ranges
	private static final double VLT_RANGE = 5.0;
	private static final double ultraDistMin = 0.787402;
    private static final double ultraDistMax = 157.48;
	
	/*ADXL362 accel;
	ADXRS450_Gyro gyro;*/
	
	Ultrasonic forwardUltra;
	Ultrasonic backwardUltra;
	
	AnalogOutput fUltraAnalogOut; 
	AnalogOutput bUltraAnalogOut;
	
	private static int forwardUltraD = 0;
    private static int backwardUltraD = 0;
	
	//Variables for Calculating Voltage
    private static int intVltMax = 0;
    private static int intVltMin = 5;
    private static int intDistMax = 0;
    private static int intDistMin = 0;
    private static int intVltSlp = ((intDistMax - intDistMin)/(intVltMax - intVltMin));	//Voltage Slope (x = Distance, y = Voltage)
	
    public Sensor() {
    	/*accel = new ADXL362(Range.k8G);
    	gyro = new ADXRS450_Gyro();*/
    	
    	forwardUltra = new Ultrasonic(RobotMap.FORWARD_ULTRASONIC_TRIG,
    			RobotMap.FORWARD_ULTRASONIC_ECHO);
    	backwardUltra = new Ultrasonic(RobotMap.BACKWARD_ULTRASONIC_TRIG,
    			RobotMap.BACKWARD_ULTRASONIC_ECHO);
    	forwardUltra.setAutomaticMode(true);
    	backwardUltra.setAutomaticMode(true);
    	
    	//Analog Output Definition
    	fUltraAnalogOut = new AnalogOutput(RobotMap.ANALOG_OUTPUT_0);
    	bUltraAnalogOut = new AnalogOutput(RobotMap.ANALOG_OUTPUT_1);
    	
    	//Set Initial Analog Voltage Output
    	fUltraAnalogOut.setVoltage(0);
    	bUltraAnalogOut.setVoltage(0);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	//setDefaultCommand(new SensorOut());
    }
    
    /**
     * Called in the init method of
     * associated commands of the subsystem.
     * 
     * @return Return current system time in milliseconds.
     */
    public long init() {
    	/*gyro.reset();
    	gyro.calibrate();*/
    	return System.currentTimeMillis();
    }
    
    /**
     * Get the all of the ultrasonic values.
     * @return Return forward and backward sensor values in an array.
     */
    public double[] sensorRead() {
    	double[] ultra = new double[2];
    	ultra[0] = forwardUltra.getRangeInches();
    	ultra[1] = backwardUltra.getRangeInches();
    	
    	/*ultra[0] = forwardUltraA0.getVoltage();
    	ultra[1] = backwardUltraA1.getVoltage();*/
    	
    	return ultra;
    }
    
    /**
     * Analog Data to Arduino for LED Indicators
     */
    public void sensorSend() {
    	double[] ultra = sensorRead();
    	
    	//Calculate Voltages to Send Out and Send Voltage Out
    	fUltraAnalogOut.setVoltage(getVltSlope(ultraDistMin, ultraDistMax)*ultra[0]);
    	bUltraAnalogOut.setVoltage(getVltSlope(ultraDistMin, ultraDistMax)*ultra[1]);
    }
    
    /**
     * Log component values.
     */
    public void log() {
    	/*SmartDashboard.putNumber(SUBSYSTEM + " Accel X: ", accel.getX());
    	SmartDashboard.putNumber(SUBSYSTEM + " Accel Y: ", accel.getY());
    	SmartDashboard.putNumber(SUBSYSTEM + " Accel Z: ", accel.getZ());
    	SmartDashboard.putNumber(SUBSYSTEM + " Gyro: ", gyro.getRate());*/
    	
    	double[] ultra = sensorRead();
    	//Display Ultrasonic Variables on Smart Dashboard
    	SmartDashboard.putNumber(SUBSYSTEM + " Forward Ultrasonic: ", ultra[0]);
    	SmartDashboard.putNumber(SUBSYSTEM + " Backward Ultrasonic: ", ultra[1]);
    
    	//Display Analog Voltage Values on Smart Dashboard
    	SmartDashboard.putNumber(SUBSYSTEM + " Forward Ultrasonic Analog Voltage ", fUltraAnalogOut.getVoltage());
    	SmartDashboard.putNumber(SUBSYSTEM + " Backward Ultrasonic Analog Voltage ", bUltraAnalogOut.getVoltage());
    }
    
    /**
     * 
     * @param distMin
     * @param distMax
     * @return
     */
    private double getVltSlope(double distMin, double distMax) {
    	return (VLT_RANGE/(distMax - distMin));
    }
    
}

