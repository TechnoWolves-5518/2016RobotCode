package org.usfirst.frc.team5518.robot.commands.drivetrain;

import org.usfirst.frc.team5518.robot.Robot;
import org.usfirst.frc.team5518.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FineWheelCtrl extends Command {

    public FineWheelCtrl() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.init();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double[] axis = new double[2];
    	axis[0] = -Robot.oi.getAxis(RobotMap.JOYSTICK_ZERO, RobotMap.XBOX_LSTICKY);
    	axis[1] = -Robot.oi.getAxis(RobotMap.JOYSTICK_ZERO, RobotMap.XBOX_RSTICKX);
    	Robot.driveTrain.drive(axis, true);
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
