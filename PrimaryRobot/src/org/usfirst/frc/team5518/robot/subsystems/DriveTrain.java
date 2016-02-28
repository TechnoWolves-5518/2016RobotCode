/** The MIT License (MIT)
*
*
* Copyright (c) 2016 Techno Wolves
*
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
*
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
*
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*/

package org.usfirst.frc.team5518.robot.subsystems;

import org.usfirst.frc.team5518.robot.Robot;
import org.usfirst.frc.team5518.robot.RobotMap;
import org.usfirst.frc.team5518.robot.commands.Drive;
import org.usfirst.frc.team5518.robot.commands.Shoot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
	RobotDrive robotDrive;
	VictorSP mtr0;
	VictorSP mtr1;
	VictorSP mtr2;
	VictorSP mtr3;
	RobotDrive myDrive;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public DriveTrain() {
    	mtr0 = new VictorSP(RobotMap.DriveMtr);
    	mtr1 = new VictorSP(RobotMap.DriveMtr);
    	mtr2 = new VictorSP(RobotMap.DriveMtr);
    	mtr3 = new VictorSP(RobotMap.DriveMtr);
    	myDrive = new RobotDrive(mtr0,mtr1,mtr2,mtr3);
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new Drive());
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    /**
     * Initialize all the necessary component settings for
     * this subsystem and return time in seconds.
     */
    public void initialize() {
    }
    

	public void drive() {
    	myDrive.arcadeDrive(Robot.oi.getAxis(RobotMap.XBOX1_LSTICK),
    			Robot.oi.getAxis(RobotMap.XBOX1_RSTICK));
	}
    
  }

