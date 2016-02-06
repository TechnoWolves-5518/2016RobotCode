package org.usfirst.frc.team5518.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team5518.robot.commands.ExampleCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.b
    
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
	Joystick XBOX1 = new Joystick(0);
	Button button0 = new JoystickButton(XBOX1, RobotMap.XBOX1_ABUTTON);
	Button button1 = new JoystickButton(XBOX1, RobotMap.XBOX1_BBUTTON);
	Button button2 = new JoystickButton(XBOX1, RobotMap.XBOX1_XBUTTON);
	Button button3 = new JoystickButton(XBOX1, RobotMap.XBOX1_YBUTTON);
	Button button4 = new JoystickButton(XBOX1, RobotMap.XBOX1_RBUMBER);
	Button button5 = new JoystickButton(XBOX1, RobotMap.XBOX1_LBUMBER);
	Button button6 = new JoystickButton(XBOX1, RobotMap.XBOX1_BACK);
	Button button7 = new JoystickButton(XBOX1, RobotMap.XBOX1_START);
	Button button8 = new JoystickButton(XBOX1, RobotMap.XBOX1_LSTICK);
	Button button9 = new JoystickButton(XBOX1, RobotMap.XBOX1_RSTICK);
}