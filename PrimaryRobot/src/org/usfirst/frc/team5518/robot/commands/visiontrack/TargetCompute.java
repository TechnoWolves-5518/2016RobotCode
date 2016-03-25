package org.usfirst.frc.team5518.robot.commands.visiontrack;

import org.usfirst.frc.team5518.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TargetCompute extends Command {

    public TargetCompute() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.visionTrack);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.visionTrack.init();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.visionTrack.targetCompute();
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
