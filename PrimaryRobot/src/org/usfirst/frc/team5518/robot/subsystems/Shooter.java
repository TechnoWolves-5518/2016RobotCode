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

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
	
	VictorSP mtr0;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public Shooter() {
    	mtr0 = new VictorSP(RobotMap.shooterMtr);
	}

    public void initDefaultCommand() {

    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    /**
     * Initialize all the necessary component settings for
     * this subsystem and return time in seconds.
     */
    public void initialize() {
    }
    

	public void shoot() {
    	mtr0.set(Robot.oi.getAxis(RobotMap.XBOX1_LSTICK));
	}
    
  }