/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techhounds.robot.subsystems;

import com.techhounds.robot.RobotTemplate;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author 2014 System
 */
public class CollectorSubsystem extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    SpeedController motor;
    public CollectorSubsystem(SpeedController m){
        motor = m;
    }
    
    public void set(double power){
        motor.set(RobotTemplate.checkRange(power, -1, 1));
    }
    public double get(){
        return motor.get();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
