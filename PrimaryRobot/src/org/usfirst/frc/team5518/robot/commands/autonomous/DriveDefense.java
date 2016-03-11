package org.usfirst.frc.team5518.robot.commands.autonomous;

import org.usfirst.frc.team5518.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDefense extends Command {
	
	private static double DRIVE_SECONDS = 5.0;
	
	private double[] axis = new double[2];
	private double time = 0;

    public DriveDefense() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	time = Robot.driveTrain.init();
    	
    	axis[0] = 1; // drive robot forward at full speed
    	axis[1] = 0; // no rotation when driving
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// drive robot forward at full speed
    	Robot.driveTrain.drive(axis, false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return ((Timer.getFPGATimestamp() - time) == DRIVE_SECONDS);
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
