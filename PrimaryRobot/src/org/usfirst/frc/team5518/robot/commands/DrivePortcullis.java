package org.usfirst.frc.team5518.robot.commands;

import org.usfirst.frc.team5518.robot.commands.autonomous.DriveUltra;
import org.usfirst.frc.team5518.robot.commands.drivetrain.DriveDefense;
import org.usfirst.frc.team5518.robot.commands.drivetrain.DriveFixed;
import org.usfirst.frc.team5518.robot.commands.hooker.OpenCylinders;
import org.usfirst.frc.team5518.robot.commands.intakemech.PickUpBall;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DrivePortcullis extends CommandGroup {
    
    public  DrivePortcullis() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	
    	addParallel(new OpenCylinders());
    	addParallel(new PickUpBall());
    	
    	addSequential(new DriveFixed(1500)); // 1500 ms tolerance
    	addSequential(new DriveDefense(1500)); // 1500 ms tolerance
    	addSequential(new DriveUltra(15.0)); // 15 in. tolerance

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
