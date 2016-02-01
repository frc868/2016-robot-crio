/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techhounds.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
Information from RobotKnowledge.txt
Shooter:
 - Angling System
   - 2 Limit switches
   - 1 Encoder
*/

/**
 *
 * @author Komodo
 */
public class ShooterAnglerSubsystem extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    private ShooterAnglerSubsystem shooterAngler;
    final private SpeedController spark;
    final private DigitalInput fwdLimitSwitch;
    final private DigitalInput revLimitSwitch;
    
    public ShooterAnglerSubsystem(SpeedController s, DigitalInput f, DigitalInput r) {
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
        return fwdLimitSwitch.get();
    }
    
    public boolean checkRevLimitSwitch() {
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
