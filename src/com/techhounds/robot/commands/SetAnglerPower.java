/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techhounds.robot.commands;

import com.techhounds.robot.subsystems.AngleAdjuster;
import com.techhounds.robot.subsystems.RobotParts;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author 2014 System
 */
public class SetAnglerPower extends Command {
    AngleAdjuster angler;
    double power;
    public SetAnglerPower(double power) {
        angler = RobotParts.getInstance().getAngler();
        power = this.power;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        angler.setPower(power);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        angler.setPower(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
