package org.usfirst.frc.team5518.robot;

import org.usfirst.frc.team5518.robot.commands.hooker.CloseCylinders;
import org.usfirst.frc.team5518.robot.commands.hooker.OpenCylinders;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
	
    Joystick[] ctrlr = new Joystick[] { 
    		new Joystick(RobotMap.JOYSTICK_ZERO),
    		new Joystick(RobotMap.JOYSTICK_ONE) };
    
    Button[] btns0 = new JoystickButton[] { 
    		new JoystickButton(ctrlr[RobotMap.JOYSTICK_ZERO], RobotMap.XBOX_ABUTTON),
    		new JoystickButton(ctrlr[RobotMap.JOYSTICK_ZERO], RobotMap.XBOX_XBUTTON),
    		new JoystickButton(ctrlr[RobotMap.JOYSTICK_ZERO], RobotMap.XBOX_YBUTTON),
    		new JoystickButton(ctrlr[RobotMap.JOYSTICK_ZERO], RobotMap.XBOX_BBUTTON),
    		new JoystickButton(ctrlr[RobotMap.JOYSTICK_ZERO], RobotMap.XBOX_LBUMBER),
    		new JoystickButton(ctrlr[RobotMap.JOYSTICK_ZERO], RobotMap.XBOX_RBUMBER)
    };
    
    Button[] btns1 = new JoystickButton[] {
    		new JoystickButton(ctrlr[RobotMap.JOYSTICK_ONE], RobotMap.XBOX_ABUTTON),
    		new JoystickButton(ctrlr[RobotMap.JOYSTICK_ONE], RobotMap.XBOX_XBUTTON),
    		new JoystickButton(ctrlr[RobotMap.JOYSTICK_ONE], RobotMap.XBOX_YBUTTON),
    		new JoystickButton(ctrlr[RobotMap.JOYSTICK_ONE], RobotMap.XBOX_BBUTTON),
    		new JoystickButton(ctrlr[RobotMap.JOYSTICK_ONE], RobotMap.XBOX_LBUMBER),
    		new JoystickButton(ctrlr[RobotMap.JOYSTICK_ONE], RobotMap.XBOX_RBUMBER)
    };
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    
    public OI() {
    	//btns0[0].toggleWhenPressed(new FineWheelCtrl()); // xbox 1 A btn
    	//btns0[1].toggleWhenPressed(new InvertWheelCtrl()); // xbox 1 X btn
    	
    	btns1[0].toggleWhenPressed(new OpenCylinders()); // xbox 2 A btn
    	btns1[1].toggleWhenPressed(new CloseCylinders()); // xbox 2 X btn
    	
    	/* Test Section (Comment out section in IntakeMtr.java to test)
    	btns1[4].toggleWhenPressed(new IntakeMtrDir()); // xbox 2 LB btn
    	*/
    	
    	//SmartDashboard.putData("Invert Wheel Ctrl", new InvertWheelCtrl());
    	//SmartDashboard.putData("Fine Wheel Ctrl", new FineWheelCtrl());
    }
    
    /**
     * Get the raw axis values of given controller and axis
     * @param idx Controller number of specified controller from Driver Station
     * @param axis Specified axis number of control from respective controller
     * @return 
     */
    public double getAxis(int idx, int axis) {
    	double rawAxis = 0;
    	rawAxis = ctrlr[idx].getRawAxis(axis);
    	
    	return rawAxis;
    }
    
    /**
     * 
     * @param idx Controller number of specified controller from Driver Station
     * @param btn Specified button number of control from respective controller
     * @return
     */
    public boolean getBtn(int idx, int btn) {
    	boolean rawBtn = ctrlr[idx].getRawButton(btn);
    	return rawBtn;
    }
    
    /**
     * 
     * @param idx Controller number of specified controller from Driver Station
     * @param type
     * @param value
     */
    public void setRumble(int idx, Joystick.RumbleType type, float value) {
    	ctrlr[idx].setRumble(type, value);
    }
    
    /**
     * 
     * @param type
     * @param value
     */
    public void setAllRumble(Joystick.RumbleType type, float value) {
    	for (Joystick stick : ctrlr) {
    		stick.setRumble(type, value);
    	}
    }
    
    /**
     * 
     * @param idx Controller number of specified controller from Driver Station
     * @return
     */
    public boolean isXbox(int idx) {
    	return ctrlr[idx].getIsXbox();
    }
    
}

