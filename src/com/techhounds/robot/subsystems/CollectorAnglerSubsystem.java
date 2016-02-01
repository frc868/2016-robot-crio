/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techhounds.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 * @author 2014 System
 */
public class CollectorAnglerSubsystem extends AngleAdjustSubsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public CollectorAnglerSubsystem(SpeedController s, DigitalInput f, DigitalInput r){
        super(s,f,r);
    }
}
