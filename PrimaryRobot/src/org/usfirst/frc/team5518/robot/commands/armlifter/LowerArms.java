package org.usfirst.frc.team5518.robot.commands.armlifter;

import org.usfirst.frc.team5518.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LowerArms extends Command {
	
	private double[] potVals;

    public LowerArms() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.armLifter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.armLifter.init();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	potVals = Robot.armLifter.getPotVals();
    	Robot.armLifter.moveArms(0.75, 0.75);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean leftArm = potVals[0] <= 
    			((Robot.armLifter.ARM_LEFT_MAX - Robot.armLifter.ARM_LEFT_MIN)/4);
    	boolean rightArm = potVals[1] <=
    			((Robot.armLifter.ARM_RIGHT_MAX - Robot.armLifter.ARM_RIGHT_MIN)/4);
    	
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
