
package com.techhounds.robot;

import com.techhounds.robot.commands.DriveDashboard;
import com.techhounds.robot.commands.IncrementShooterSpeed;
import com.techhounds.robot.commands.SetAnglerPower;
import com.techhounds.robot.commands.SetShooterPower;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    private static OI instance;
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    private final Joystick driver;
    public static ControllerMap driverPad;
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
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
 
    private OI() {
        driver = new Joystick(1);
        driverPad = new ControllerMap(driver, 0, false);
        innitDriver();
        
    }
    Button setAnglerPowerUp;
    int anglerPowerUpButton = ControllerMap.Y;
    
    Button setAnglerPowerDown;
    int anglerPowerDownButton = ControllerMap.A;
    
    Button incrementShooterSpeed;
    int incrementShooterSpeedButton = ControllerMap.B;
    Button zeroShooterSpeed;
    int zeroShooterSpeedButton = ControllerMap.RT;
    Button decrementShooterSpeed;
    int decrementShooterButton = ControllerMap.X;
    
    Button setShooterToSpeed;
    int setShooterToSpeedButton = ControllerMap.RB;
    Button setAnglerToZero;
    int setAnglerToZeroButton = ControllerMap.LB;
    public void innitDriver(){
        setAnglerToZero = driverPad.createButton(setAnglerToZeroButton);
        setAnglerToZero.whenPressed(new SetAnglerPower(0));
        setAnglerPowerUp = driverPad.createButton(anglerPowerUpButton);
        setAnglerPowerUp.whileHeld(new SetAnglerPower(.25));
        setAnglerPowerUp.whenPressed(new SetAnglerPower(0));
        
        setAnglerPowerDown = driverPad.createButton(anglerPowerDownButton);
        setAnglerPowerDown.whenPressed(new SetAnglerPower(-.25));
        setAnglerPowerDown.whenReleased(new SetAnglerPower(0));
        
        incrementShooterSpeed = driverPad.createButton(incrementShooterSpeedButton);
        incrementShooterSpeed.whenPressed(new IncrementShooterSpeed(.1));
        
        zeroShooterSpeed = driverPad.createButton(zeroShooterSpeedButton);
        zeroShooterSpeed.whenPressed(new SetShooterPower(0));
        
        decrementShooterSpeed = driverPad.createButton(decrementShooterButton);
        decrementShooterSpeed.whenPressed(new IncrementShooterSpeed(-.1));
        
        setShooterToSpeed = driverPad.createButton(setShooterToSpeedButton);
        setShooterToSpeed.whenPressed(new SetShooterPower(.5));
        
        
    }
    
    
    public static OI getInstance() {
        if (instance == null) {
            instance = new OI();
            instance.addSmartDashboardControls();
        }
        return instance;
    }

    private void addSmartDashboardControls() {
        SmartDashboard.putData(new DriveDashboard());
    }

    public double getTankLeftPower() {
        double power = getPower(driver, 2);
        return -power;
    }

    public double getTankRightPower() {
        double power = getPower(driver, 4);
        return -power;
    }
    public double getArcadeLeftPower(){
        return rangeCheck(-getPower(driver,2) + getPower(driver,3));
    }
    public double getArcadeRightPower(){
        return rangeCheck(-getPower(driver,2) - getPower(driver,3));
    }
    public double rangeCheck(double power){
        if(power > 1){
            return 1;
        }else if(power < -1){
            return -1;
        }else{
            return power;
        }
    }

    private double getPower(Joystick source, int axis) {
        double p = source.getRawAxis(axis);
        final double minMove = 0.05;
        if (Math.abs(p) < minMove) {
            return 0;
        }
        
        // Subtract out dead zone hole
        p = (p < 0) ? (p + minMove) : (p + minMove);
        
        // Expand back to range of [-1, +1]
        p /= (1 - minMove);
        return p;
    }


}

