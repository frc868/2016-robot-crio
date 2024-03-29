/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techhounds.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author 2014 System
 */
public abstract class AngleAdjustSubsystem extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private SpeedController spark;
    private DigitalInput fwdLimitSwitch;
    private DigitalInput revLimitSwitch;
    
    public AngleAdjustSubsystem(SpeedController s, DigitalInput f, DigitalInput r) {
        spark = s;
        fwdLimitSwitch = f;
        revLimitSwitch = r;
    }
    
    public double get() {
        return spark.get();
    }
    
    public void safetyCheck() {
        if(checkFwdLimitSwitch() && get() > 0) set(0);
        if(checkRevLimitSwitch() && get() < 0) set(0);
    }
    
    public void set(double power) {
        //checks for limit switch input and moderates output appropriately
        if(checkFwdLimitSwitch() && power > 0) power = 0;
        if(checkRevLimitSwitch() && power < 0) power = 0;
        spark.set(power);
    }
    
    public boolean checkFwdLimitSwitch() {
        if(fwdLimitSwitch == null) return false;
        return fwdLimitSwitch.get();
    }
    
    public boolean checkRevLimitSwitch() {
        if(revLimitSwitch == null) return false;
        return revLimitSwitch.get();
    }
    
    public void updateSmartDashboard() {
        safetyCheck();
        SmartDashboard.putNumber("Spark.get()", get());
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
