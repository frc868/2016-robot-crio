/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techhounds.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author cRIO-devel
 */
public class RobotParts {
    
    //
    // PWM allocation
    //
    
    private static final int PWM_RIGHT_MOTOR0 = 1;
    private static final int PWM_RIGHT_MOTOR1 = 2;
    private static final int PWM_LEFT_MOTOR0 = 3;
    private static final int PWM_LEFT_MOTOR1 = 4;
    
    private static final int PWM_SHOOTER_MOTOR = 0;
    
    private static final int PWM_ANGLER_MOTOR = 0;
    
    private static RobotParts instance;
    private DriveSubsystem drive;
    private ShooterSubsystem shooter;
    private AngleAdjuster angler;
    
    private RobotParts() {
    }
    
    public static RobotParts getInstance() {
        if (instance == null) {
            instance = new RobotParts();
        }
        return instance;
    }
    public ShooterSubsystem getShooter(){
        if(shooter == null){
            
            shooter = new ShooterSubsystem(createSpeedController("Shooter", "Shooter Motor", PWM_SHOOTER_MOTOR));
        }
        return shooter;
    }
    public AngleAdjuster getAngler(){
        if(angler == null){
            angler = new AngleAdjuster(createSpeedController("Angler", "Angle Motor", PWM_ANGLER_MOTOR));
        }
        return angler;
    }
    
    public DriveSubsystem getDrive() {
        if (drive == null) {
            String group = "Drive";
            SpeedController left0 = createSpeedController(group, "Left Motor0", PWM_LEFT_MOTOR0);
            SpeedController left1 = createSpeedController(group, "Left Motor1", PWM_LEFT_MOTOR1);
            SpeedController right0 = createSpeedController(group, "Right Motor0", PWM_RIGHT_MOTOR0);
            SpeedController right1 = createSpeedController(group, "Right Motor1", PWM_RIGHT_MOTOR1);
            drive = new DriveSubsystem(left0, left1, right0, right1);
            SmartDashboard.putData(drive);
        }
        return drive;
    }

    private SpeedController createSpeedController(String group, String name, int pwm) {
        Spark sc = new Spark(pwm);
        LiveWindow.addActuator(group, name, sc);
        return sc;
    }
    
    
    
    
    private SpeedController createVictor(String group, String name, int pwm) {
        Victor sc = new Victor(pwm);
        LiveWindow.addActuator(group, name, sc);
        return sc;
    }
}
