package org.usfirst.frc.team5518.robot.commands.drivetrain;

import org.usfirst.frc.team5518.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Rotate extends Command {
	
	private final double tolerance;
	private double time = 0;

	/**
	 * 
	 * @param tolerance
	 */
    public Rotate(double tolerance) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
        
        this.tolerance = tolerance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	time = Robot.driveTrain.init();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.drive(new double[] { 0, 0.5 }, false);
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
    	end();
    }
}
