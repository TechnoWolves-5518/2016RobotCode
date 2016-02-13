package org.usfirst.frc.team5518.robot.commands;

import org.usfirst.frc.team5518.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OpenClaw extends Command {
	
	// instance variable to keep track of time elapsed
	private double time = 0.0;

    public OpenClaw() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.claw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// init needed components and get time from init
    	time = Robot.claw.initialize();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.claw.open();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	// finish cmd after 5 seconds have elapsed
        return ((Robot.claw.getTime() - time) == 5.0);
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
