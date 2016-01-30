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
public class ShooterAnglerSubsystem extends AngleAdjustSubsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    

    public ShooterAnglerSubsystem(SpeedController s, DigitalInput f, DigitalInput r) {
        super(s, f, r);
    }
}
