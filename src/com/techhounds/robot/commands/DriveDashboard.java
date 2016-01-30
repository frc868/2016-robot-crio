/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techhounds.robot.commands;

import com.techhounds.robot.subsystems.DriveSubsystem;
import com.techhounds.robot.subsystems.RobotParts;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Command that reads values from smart dashboard for left/right drive motor power.
 * @author cRIO-devel
 */
public class DriveDashboard extends Command {

    private final DriveSubsystem drive;
    private static final String LEFT_POWER = "Drive Left Power";
    private static final String RIGHT_POWER = "Drive Right Power";
    
    public DriveDashboard() {
        super("Drive");
        SmartDashboard.putNumber(LEFT_POWER, SmartDashboard.getNumber(LEFT_POWER, 0.0));
        SmartDashboard.putNumber(RIGHT_POWER, SmartDashboard.getNumber(RIGHT_POWER, 0.0));
        drive = RobotParts.getInstance().getDrive();
        requires(drive);
    }

    protected void initialize() {
    }

    protected void execute() {
        double leftPower = SmartDashboard.getNumber(LEFT_POWER, 0.0);
        double rightPower = SmartDashboard.getNumber(RIGHT_POWER, 0.0);
        drive.setPower(leftPower, rightPower);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        drive.setPower(0, 0);
    }

    protected void interrupted() {
        drive.setPower(0, 0);
    }
    
}
