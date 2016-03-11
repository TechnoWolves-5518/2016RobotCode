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
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
