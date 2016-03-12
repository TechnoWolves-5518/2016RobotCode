package org.usfirst.frc.team5518.robot.commands.intakemech;

import org.usfirst.frc.team5518.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PickUpBall extends Command {
	
	private static final double TOL_MILLISEC = 1000;
	private double time = 0;

    public PickUpBall() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.intakeMech);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	time = Robot.intakeMech.init();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intakeMech.intake(0.5); // set intake mtr to half speed
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return ((System.currentTimeMillis() - time) >= TOL_MILLISEC);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
