package org.usfirst.frc.team5518.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * CommandGroup to run commands to open and
 * close claw in sequential order.
 */
public class GrabClaw extends CommandGroup {
    
    public  GrabClaw() {
        // Add Commands here:
        addSequential(new OpenClaw());
        addSequential(new CloseClaw());
        // these will run in order.

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
