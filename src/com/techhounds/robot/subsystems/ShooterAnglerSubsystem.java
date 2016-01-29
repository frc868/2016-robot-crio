/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techhounds.robot.subsystems;

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
    
    SpeedController spark;
    boolean fwdLimitSwitch;
    boolean revLimitSwitch;
    final boolean IS_INVERTED = false;
    
    public ShooterAnglerSubsystem(SpeedController s) {
        spark = s;
    }
    
    public double get() {
        return spark.get();
    }
    
    public void set(double num) {
        num = (IS_INVERTED ? -num : num);
        spark.set(num);
    }
    
    public void updateSmartDashboard() {
        SmartDashboard.putNumber("Spark.get()", get());
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
