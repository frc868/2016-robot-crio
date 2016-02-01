/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techhounds.robot.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author 2014 System
 */
public class ShooterSubsystem extends Subsystem {
    
    private final SpeedController motor;
    private final Counter counter;
    
    private final PIDController shooterPID;
    
    private final boolean INVERTED = false;
    
    private final double shooterPID_P = 0;
    private final double shooterPID_I = 0;
    private final double shooterPID_D = 0;
    
    public ShooterSubsystem(SpeedController motor){
        this.motor = motor;
        this.counter = new Counter();
        
        shooterPID = new PIDController(
                shooterPID_P, shooterPID_I, shooterPID_D,
                new PIDSource() {
                    public double pidGet() {
                        return counter.getRate();
                    }
                },
                new PIDOutput() {
                    public void pidWrite(double power) {
                        ShooterSubsystem.this.motor.set(INVERTED ? -power : power);
                    }
                });
        shooterPID.setOutputRange(-1, 1);
        shooterPID.setAbsoluteTolerance(2);
        
        // Collector Angler Command
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public double getPower(){
        return INVERTED ? -motor.get() : motor.get();
    }
    
    public void setPower(double power){
        motor.set(INVERTED ? -power : power);
    }
    
    public void setSpeed(double speed) {
        shooterPID.setSetpoint(speed);
        shooterPID.enable();
    }
    
    public boolean speedInTolerance() {
        return shooterPID.onTarget();
    }
    
    public void disableShooterPID() {
        shooterPID.disable();
    }
    
    public void updateSmartDashboard() {
        
    }
}
