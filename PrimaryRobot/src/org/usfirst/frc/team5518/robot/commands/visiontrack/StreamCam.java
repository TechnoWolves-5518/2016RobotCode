package org.usfirst.frc.team5518.robot.commands.visiontrack;

import org.usfirst.frc.team5518.robot.Robot;
import org.usfirst.frc.team5518.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StreamCam extends Command {
    public StreamCam() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.visionTrack);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.visionTrack.init();
    	Robot.visionTrack.streamCam();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.visionTrack.streamCam();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.visionTrack.end();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
