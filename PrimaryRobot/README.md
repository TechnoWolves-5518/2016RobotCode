# 2016RobotCode
## PrimaryRobot

The code for Techno Wolves’ 2016 FIRST Stronghold robot, Reagan, is written entirely in Java using WPILIB. This season, we followed WPI’s command-based approach of a certain set of commands unique to each subsystem. Our control scheme/mapping is based upon inputs from an Xbox One and an Xbox 360 controller.

### Robot.java
The class <em>Robot</em> initializes all of the subsystems and the OI. This class has methods following the FRC game lifecycle and optional methods for Test and Practice mode which can be enabled from the FRC Driver Station (DS). The chosen autonomous command is returned by a <em>SendableChooser</em> from the Java SmartDashboard.

### OI.java
OI stands for Operator Interface. This file is where the joystick(s) and their buttons are initialized. For our purpose, the joysticks and their buttons are stored in their respective arrays. The class OI has five helper functions: <em>getAxis()</em>, <em>getBtn()</em>, <em>setRumble()</em>, <em>setAllRumble()</em>, and <em>isXbox()</em>.

### RobotMap.java
The class <em>RobotMap</em> houses all of the necessary constants (i.e. roboRIO & PCM ports, joystick axis & buttons). This class should only directly be called by Robot.java or the subsystems.

### Subsystems
A subsystem represents a specific part of the robot’s full functionality. All of the logic and direct calls to WPILIB are handled in the subsystem. A subsystem can have multiple commands associated with them via <em>require()</em>, but only one command of a subsystem can be enabled at a time. The issuing of commands is handled by a running Scheduler instance. Default commands are automatically scheduled if set using <em>setDefaultCommand()</em>.

#### ArmLifter <small>(unused since 2<sup>nd</sup> competition of season)</small>
This subsystem deals with the mechanism of lifting the robot entirely off the ground using a high bar for a leverage. <em>ArmLifter</em> consists of two DART Linear Actuators with CIMs and potentiometers. The CIMs on the actuators are controlled by two Victor/Victor SP Speed Controllers. This subsystem was not in use after the second district championship. The commands <em>MoveArms</em> (default), <em>LowerArms</em> (unused), and <em>RaiseArms</em> (unused) are associated with <em>ArmLifter</em>.

#### DriveTrain
The purpose of this subsystem is to provide the robot with maneuverability using the four gearboxes attached to six pneumatic tires. The gearboxes are powered by the four Victor SP Speed Controllers and CIM motors. WPILIB’s <em>arcadeDrive()</em> was supplied with two axis (left and right thumbsticks of an Xbox One controller) for driving and turning inputs. <em>DriveTrain</em> schedules the <em>FineWheelCtrl</em> command by default. The other commands include <em>WheelCtrl</em> (unused), <em>InvertWheelCtrl</em> (unused), <em>DriveFixed</em>, <em>DriveDefense</em>, and <em>Rotate</em> (unused).

#### Hooker
The <em>Hooker</em> subsystem refers to the latching-hook mechanism (later changed to extensions) at both ends of Reagan’s arms. This mechanism is powered by air outflow control to pneumatic cylinders from a double solenoid. The <em>OpenCylinder</em> and <em>CloseCylinder</em> commands are mapped to the A and X buttons of an Xbox 360 controller, respectively.

#### IntakeMech
This subsystem consists of a snowblower motor connected to a Victor. The purpose of this is to allow the robot to intake spherical game pieces, specifically whiffle balls as boulders for FIRST Stronghold. The LB (left bumper) on the Xbox 360 controller is used to take in these balls and RB (right bumper) is used to push the ball out. The <em>IntakeMech</em> subsystem has the following commands: <em>IntakeMtr</em>, <em>IntakeMtrDir</em> (unused), <em>PickUpBall</em> (unused).

#### Sensor <small>(unused)</small>
The intent of the Sensor subsystem was to use it to analyze values collected from ultrasonic (proximity) sensors and a gyroscope and behave the robot accordingly both for autonomous and teleop mode. An additional light signal was planned to be added to signify if the robot was in the “goldilocks position” to shoot into the goal. This subsystem had no commands.

#### Shooter
The Shooter subsystem handles the mechanism responsible for launching boulders (ball game pieces) at the high goal of the opposing alliance’s castle. This mechanism is composed of two Mini CIM motors which rotate the flywheels at high velocities in order to generate enough force to shoot the boulders at the desired location. The RT (right trigger) of the Xbox 360 controller is used to shoot at respective speeds. There are two commands associated with this subsystem: <em>Shoot</em> (default) and <em>ShootAuto</em> (unused).

#### VisionTrack
This subsystem is responsible for calling a script on the roboRIO which executes mjpg-streamer. This program allows the camera(s) plugged into the USB port(s) of the roboRIO to be streamed to the Driver Station Laptop. The <em>StreamCam</em> command is the only command of this subsystem and is scheduled by default.

### Command Groups
Command groups can be used to organize many commands. Multiple commands can be enabled at once for a given subsystem if a parallel command is issued from a command group. Command groups are ideal for designing an autonomous sequence because these commands can be either scheduled sequentially or in parallel.

#### DefaultAuto
The <em>DefaultAuto</em> is the default command group that runs in the autonomous period. It enables the robot to drive over a defense from the neutral zone by driving the robot for half speed, then for full speed, and back at half speed again via the <em>DriveFixed</em> and <em>DriveDefense</em> commands. Both commands take in a tolerance value for how long each command should run, however <em>DriveFixed</em> runs the drivetrain motors at half speed with <em>DriveDefense</em> running it at full speed.

#### DrivePortcullis
The <em>DrivePortcullis</em> command group uses the same technique as <em>DefaultAuto</em> in crossing a defense, but also opens the pneumatic cylinders attached to the arms via the <em>OpenCylinders</em> command at the beginning of the sequence and closes them via the <em>CloseCylinders</em> command at the end of the sequence. This command group was added after the second district event during the NC State Championship.

#### LiftAndDrive <small>(unused)</small>
The <em>LiftAndDrive</em> command group was never used in competitions. Its original intent was to lift the portcullis after driving for a fixed amount at half speed and continuing to drive full speed over the defense like the <em>DefaultAuto</em> command group.

#### PullAndDrive <small>(unused)</small>
The <em>PullAndDrive</em> command group was never used in competitions. Its original intent was to pull the sally port after driving for a fixed amount at half speed and continuing to drive full speed over the defense like the <em>DefaultAuto</em> command group.
