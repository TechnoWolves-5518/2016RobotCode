package org.usfirst.frc.team5518.robot.commands.intakemech;

import org.usfirst.frc.team5518.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeMtrDir extends Command {

    public IntakeMtrDir() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.intakeMech);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intakeMech.init();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intakeMech.toggleIntakeMtrDir();
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
    	end();
    }
}
