/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techhounds.robot.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author 2014 System
 */
public class AngleAdjuster extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public SpeedController motor;
    public final boolean INVERTED = false;
    Encoder encoder;
    
    public AngleAdjuster(SpeedController motor){
        this.motor = motor;
        
    }
    
    public void setPower(double power){
        power = INVERTED ? -power : power;
        motor.set(power);
    }
    

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
