package org.usfirst.frc.team5518.robot.commands.autonomous;

import org.usfirst.frc.team5518.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Rotate extends Command {
	
	private static double ROTATE_SECONDS = 0.5;
	private double time = 0;

    public Rotate() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
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
        return ((Timer.getFPGATimestamp() - time) >= ROTATE_SECONDS);
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
