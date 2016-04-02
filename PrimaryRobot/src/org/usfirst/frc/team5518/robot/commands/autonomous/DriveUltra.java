package org.usfirst.frc.team5518.robot.commands.autonomous;

import org.usfirst.frc.team5518.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveUltra extends Command {
	
	private double tolerance = 5.0;
	
	private double[] axis = new double[2];
	//private double[] ultra = new double[2];

    public DriveUltra(double tolerance) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
        //requires(Robot.sensor);
        
        // set the tolerance limit in inches
        this.tolerance = tolerance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.init();
    	//Robot.sensor.init();
    	
    	axis[0] = 0.5; // drive robot forward at half speed
    	axis[1] = 0; // no rotation when driving
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//ultra = Robot.sensor.sensorRead();
    	
    	// drive robot forward at half speed
    	Robot.driveTrain.drive(axis, true);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	// stop driving when 5 in away
    	//return (ultra[0] <= tolerance);     //<-- To FIX
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.drive(new double[] { 0, 0 }, false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
