/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techhounds.robot.subsystems;

import com.techhounds.robot.util.InvertableSpeedController;
import edu.wpi.first.wpilibj.DigitalInput;
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
    private static final int PWM_RIGHT_MOTOR2 = 6;
    private static final int PWM_LEFT_MOTOR0 = 3;
    private static final int PWM_LEFT_MOTOR1 = 4;
    private static final int PWM_LEFT_MOTOR2 = 5;
    
    private static final int PWM_SHOOTER_MOTOR = 10;
    
    private static final int PWM_ANGLER_MOTOR = -1;
    
    private static final int PWM_SHOOTER_ANGLER_MOTOR = -1;
    private static final boolean SHOOTER_ANGLER_IS_FLIPPED = false;
    private static final int FWD_LIMIT_SWITCH = -1;
    private static final int REV_LIMIT_SWITCH = -1;
    
    private static RobotParts instance;
    private DriveSubsystem drive;
    private ShooterSubsystem shooter;
    private ShooterAnglerSubsystem shooterAngler;
    private AngleAdjuster angler;
    
    private RobotParts() {
    }
    
    public static RobotParts getInstance() {
        if (instance == null) {
            instance = new RobotParts();
        }
        return instance;
    }
    public ShooterAnglerSubsystem getShooterAngler() {
        if(shooterAngler == null){
            DigitalInput f = new DigitalInput(FWD_LIMIT_SWITCH);
            DigitalInput r = new DigitalInput(REV_LIMIT_SWITCH);
            Spark spark = new Spark(PWM_SHOOTER_ANGLER_MOTOR);
            InvertableSpeedController s = new InvertableSpeedController(spark, SHOOTER_ANGLER_IS_FLIPPED);
            shooterAngler = new ShooterAnglerSubsystem(s, f, r);
            LiveWindow.addSensor("ShooterAnglerSubsystem", "ReverseLimit", r);
            LiveWindow.addSensor("ShooterAnglerSubsystem", "ForwardLimit", f);
            LiveWindow.addSensor("ShooterAnglerSubsystem", "SpeedController", spark);
        }
        return shooterAngler;
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
            SpeedController left2 = createSpeedController(group, "Left Motor2", PWM_LEFT_MOTOR2);
            SpeedController right0 = createSpeedController(group, "Right Motor0", PWM_RIGHT_MOTOR0);
            SpeedController right1 = createSpeedController(group, "Right Motor1", PWM_RIGHT_MOTOR1);
            SpeedController right2 = createSpeedController(group, "Right Motor2", PWM_RIGHT_MOTOR2);
            drive = new DriveSubsystem(left0, left1, left2, right0, right1, right2);
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
