package org.usfirst.frc.team5518.robot.commands.armlifter;

import org.usfirst.frc.team5518.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RaiseArms extends Command {
	
	private double[] travel;

    public RaiseArms() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.armLifter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.armLifter.init();
    	travel = Robot.armLifter.getTravel();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	travel = Robot.armLifter.getTravel();
    	
    	double[] axis = new double[2];
    	axis[0] = 0.75; // left arm speed
    	axis[1] = 0.75; // right arm speed
    	Robot.armLifter.moveArms(axis);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean leftArm = travel[0] >= 
    			((Robot.armLifter.ARM_LEFT_MAX - Robot.armLifter.ARM_LEFT_MIN)/2);
    	boolean rightArm = travel[1] >=
    			((Robot.armLifter.ARM_RIGHT_MAX - Robot.armLifter.ARM_RIGHT_MIN)/2);
    	
        return leftArm && rightArm;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
