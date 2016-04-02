package org.usfirst.frc.team5518.robot.commands.autonomous;

import org.usfirst.frc.team5518.robot.commands.armlifter.LowerArms;
import org.usfirst.frc.team5518.robot.commands.armlifter.RaiseArms;
import org.usfirst.frc.team5518.robot.commands.drivetrain.DriveFixed;
import org.usfirst.frc.team5518.robot.commands.drivetrain.Rotate;
import org.usfirst.frc.team5518.robot.commands.intakemech.PickUpBall;
import org.usfirst.frc.team5518.robot.commands.shooter.ShootAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DrivePortacullis extends CommandGroup {
    
    public  DrivePortacullis() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	
    	addParallel(new PickUpBall());
    	addSequential(new RaiseArms());
    	
    	addSequential(new DriveFixed(1500)); // 1500 ms tolerance
    	
    	// lower arms and drive in parallel
    	addParallel(new LowerArms());
    	addSequential(new DriveFixed(1500)); // 1500 ms tolerance
    	
    	addSequential(new DriveUltra(15.0)); // 15 in. tolerance
    	addSequential(new Rotate(500)); // 500 ms tolerance
    	addSequential(new ShootAuto());

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
