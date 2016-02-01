/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techhounds.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Komodo
 */
public class EncoderSubsystem extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    Encoder encoder;
    
    public EncoderSubsystem(Encoder e){
        encoder = e;
        encoder.start();
        encoder.setDistancePerPulse(1);
    }
    public void loadPIDValues(){
        encoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kDistance);
    }
    public double getDistance(){
        return encoder.getDistance();
    }
    public double getRate(){
        return encoder.getRate();
    }
    public void reset(){
        encoder.reset();
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
