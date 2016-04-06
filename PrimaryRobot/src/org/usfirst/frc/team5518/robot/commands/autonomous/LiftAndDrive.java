package org.usfirst.frc.team5518.robot.commands.autonomous;

import org.usfirst.frc.team5518.robot.commands.armlifter.RaiseArms;
import org.usfirst.frc.team5518.robot.commands.drivetrain.DriveFixed;
import org.usfirst.frc.team5518.robot.commands.drivetrain.Rotate;
import org.usfirst.frc.team5518.robot.commands.intakemech.PickUpBall;
import org.usfirst.frc.team5518.robot.commands.shooter.ShootAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LiftAndDrive extends CommandGroup {
    
    public  LiftAndDrive() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	
    	addSequential(new PickUpBall());
    	addSequential(new DriveFixed(1500));
    	
    	// lift arms and drive in parallel
    	addParallel(new RaiseArms());
    	addSequential(new DriveFixed(1500)); // 1500 ms tolerance
    	
    	addSequential(new DriveFixed(1000)); // 1000 ms tolerance
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
