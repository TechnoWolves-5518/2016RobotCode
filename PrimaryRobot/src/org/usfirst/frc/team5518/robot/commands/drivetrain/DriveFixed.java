package org.usfirst.frc.team5518.robot.commands.drivetrain;

import org.usfirst.frc.team5518.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveFixed extends Command {
	
	private final double tolerance;
	
	private double[] axis = new double[2];
	private double time = 0;

	/**
	 * 
	 * @param tolerance
	 */
    public DriveFixed(double tolerance) {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.driveTrain);
    	
    	this.tolerance = tolerance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	time = Robot.driveTrain.init();
    	
    	axis[0] = 0.5; // drive robot forward at half speed
    	axis[1] = 0; // no rotation when driving
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// drive robot forward at half speed
    	Robot.driveTrain.drive(axis, true);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return ((System.currentTimeMillis() - time) >= tolerance);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
