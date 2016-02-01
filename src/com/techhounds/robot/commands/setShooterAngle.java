/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techhounds.robot.commands;

import com.techhounds.robot.subsystems.RobotParts;
import com.techhounds.robot.subsystems.ShooterAnglerSubsystem;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Komodo
 */
public class setShooterAngle extends Command implements PIDSource, PIDOutput {
    
    ShooterAnglerSubsystem shooterAngler = RobotParts.getInstance().getShooterAngler();
    PIDController pid;
    public setShooterAngle() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(shooterAngler);
        pid = new PIDController(0.0001, 0, 0, this, this);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        double p = SmartDashboard.getNumber("ShooterAngler P", 0.0001);
        double i = SmartDashboard.getNumber("ShooterAngler I", 0.0000);
        double d = SmartDashboard.getNumber("ShooterAngler D", 0.0000);
        
        pid.setPID(p, i, d);
        pid.setSetpoint(SmartDashboard.getNumber("ShooterAngler Setpoint", 0));
        pid.setPercentTolerance(SmartDashboard.getNumber("ShooterAngler PercentTolerance", 0.5));
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return pid.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

    public double pidGet() {
        return shooterAngler.get();
    }

    public void pidWrite(double output) {
        shooterAngler.set(output);
    }
}
