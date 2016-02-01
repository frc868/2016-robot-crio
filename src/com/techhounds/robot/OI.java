
package com.techhounds.robot;

import com.techhounds.robot.commands.DriveDashboard;
import com.techhounds.robot.commands.IncrementShooterSpeed;
import com.techhounds.robot.commands.SetAnglerPower;
import com.techhounds.robot.commands.SetShooterPower;
import com.techhounds.robot.subsystems.RobotParts;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI {

    private static OI instance;
    
    public static ControllerMap driverPad;
    public static ControllerMap operatorPad;
    
    /* DRVIER CONTROLS */
    Button drShooterSetSpeed;           int drShooterSetSpeedButton = ControllerMap.RB;
    Button drShooterAngleZero;          int drShooterAngleZeroButton = ControllerMap.LB;
    
    
    /* OPERATOR CONTROLS */
    Button opAnglerPowUp;               int opAnglerPowUpButton = ControllerMap.Y;
    Button opAnglerPowDown;             int opAnglerPowDownButton = ControllerMap.A;
    
    Button opShooterSpeedIncrement;     int opShooterSpeedIncrementButton = ControllerMap.B;
    Button opShooterSpeedDecrement;     int opShooterSpeedDecrementButton = ControllerMap.X;
    Button opShooterSpeedZero;          int opShooterSpeedZeroButton = ControllerMap.RT;
    
    Button opShooterSetSpeed;           int opShooterSetSpeedButton = ControllerMap.RB;
    Button opShooterAngleZero;          int opShooterAngleZeroButton = ControllerMap.LB;
    
    private OI() {
//        operator = new Joystick(2);

        driverPad = new ControllerMap(new Joystick(1), 0, false);
        operatorPad = new ControllerMap(new Joystick(2), 0, false);
        
        initSmartDashboard();
        initDriver();
        initOperator();
    }
    
    public void initDriver() {
        // TODO: Add Driver Controls
        
        drShooterSetSpeed = driverPad.createButton(drShooterSetSpeedButton);
        drShooterSetSpeed.whenPressed(new SetShooterPower(0.5));
        
        drShooterAngleZero = driverPad.createButton(drShooterAngleZeroButton);
        drShooterAngleZero.whenPressed(new SetShooterPower(0));
    }
    
    public void initOperator(){
        // TODO: Add Operator Controls
        
        opShooterAngleZero = operatorPad.createButton(opShooterAngleZeroButton);
        opShooterAngleZero.whenPressed(new SetAnglerPower(0));

        opAnglerPowUp = operatorPad.createButton(opAnglerPowUpButton);
        opAnglerPowUp.whileHeld(new SetAnglerPower(.25));
        opAnglerPowUp.whenPressed(new SetAnglerPower(0));
        
        opAnglerPowDown = operatorPad.createButton(opAnglerPowDownButton);
        opAnglerPowDown.whenPressed(new SetAnglerPower(-.25));
        opAnglerPowDown.whenReleased(new SetAnglerPower(0));
        
        opShooterSpeedIncrement = operatorPad.createButton(opShooterSpeedIncrementButton);
        opShooterSpeedIncrement.whenPressed(new IncrementShooterSpeed(.1));
        
        opShooterSpeedZero = operatorPad.createButton(opShooterSpeedZeroButton);
        opShooterSpeedZero.whenPressed(new SetShooterPower(0));
        
        opShooterSpeedDecrement = operatorPad.createButton(opShooterSpeedDecrementButton);
        opShooterSpeedDecrement.whenPressed(new IncrementShooterSpeed(.1));
        
        opShooterSetSpeed = operatorPad.createButton(opShooterSetSpeedButton);
        opShooterSetSpeed.whenPressed(new SetShooterPower(.5));
    }
    
    public void initSmartDashboard() {
        SmartDashboard.putNumber("Shooter Speed", RobotParts.getInstance().getShooter().getPower());
//        SmartDashboard.putData(new DriveDashboard());
        SmartDashboard.putNumber("Shooter Angler Power", .4);
    }
    
    public static OI getInstance() {
        if(instance == null)
            instance = new OI();
        return instance;
    }

    public double getTankLeftPower() {
        return -driverPad.getLeftStickY();
        
        //double power = getPower(operator, 2);
        //return -power;
    }

    public double getTankRightPower() {
        return -driverPad.getRightStickY();
        
        //double power = getPower(operator, 4);
        //return -power;
    }
    
    public double getArcadeLeftPower(){
        return rangeCheck(driverPad.getRightStickX() - driverPad.getLeftStickY());
                
        //        -getPower(operator,2) + getPower(operator,3));
    }
    
    public double getArcadeRightPower(){
        return rangeCheck(-driverPad.getRightStickX() - driverPad.getLeftStickY());
        //return rangeCheck(-getPower(operator,2) - getPower(operator,3));
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

//    private double getPower(Joystick source, int axis) {
//        double p = source.getRawAxis(axis);
//        final double minMove = 0.05;
//        if (Math.abs(p) < minMove) {
//            return 0;
//        }
//        
//        // Subtract out dead zone hole
//        p = (p < 0) ? (p + minMove) : (p + minMove);
//        
//        // Expand back to range of [-1, +1]
//        p /= (1 - minMove);
//        return p;
//    }
}