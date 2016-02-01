/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techhounds.robot.commands;

import com.techhounds.robot.OI;
import com.techhounds.robot.subsystems.DriveSubsystem;
import com.techhounds.robot.subsystems.RobotParts;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Command that reads values from smart dashboard for left/right drive motor power.
 * @author cRIO-devel
 */
public class DriveGamePadTank extends Command {

    private final DriveSubsystem drive;
    private final OI oi;
    
    public DriveGamePadTank(DriveSubsystem drive) {
        super("Drive Tank Gamepad");
        this.drive = drive;
        requires(drive);
        oi = OI.getInstance();
    }
    
    public DriveGamePadTank() {
        this(RobotParts.getInstance().getDrive());
    }

    protected void initialize() {
    }

    protected void execute() {
        double leftPower = oi.getTankLeftPower();
        double rightPower = oi.getTankRightPower();
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
