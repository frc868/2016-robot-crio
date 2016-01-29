/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techhounds.robot.commands;

import com.techhounds.robot.subsystems.RobotParts;
import com.techhounds.robot.subsystems.ShooterAnglerSubsystem;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author Komodo
 */
public class ZeroAngler extends Command {
    
    ShooterAnglerSubsystem shooterAngler;
    RobotParts robotParts;
    public ZeroAngler() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(shooterAngler);
        robotParts = RobotParts.getInstance();
        shooterAngler = robotParts.getShooterAngler();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        shooterAngler.set(0.15);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return shooterAngler.checkRevLimitSwitch();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
