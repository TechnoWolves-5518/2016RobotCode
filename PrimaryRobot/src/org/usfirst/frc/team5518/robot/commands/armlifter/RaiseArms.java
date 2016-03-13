package org.usfirst.frc.team5518.robot.commands.armlifter;

import org.usfirst.frc.team5518.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RaiseArms extends Command {
	
    public RaiseArms() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.armLifter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.armLifter.init();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.armLifter.moveArms(-0.50, -0.50); // move both arms up at same speed
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean leftArm = Robot.armLifter.isLeftArmExceeded();
    	boolean rightArm = Robot.armLifter.isRightArmExceeded();
    	
        return leftArm || rightArm;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
