package com.techhounds.robot.util;

import com.techhounds.robot.RobotTemplate;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * @author Atif Niyaz
 */
public class MultiSpeedController implements SpeedController {

    private SpeedController [] motors;
    private boolean [] inverted;
    //private double [] multiplier;
    
    //multiplier must be -1<=x<=1
    public MultiSpeedController(SpeedController[] motors, boolean [] inverted) {
        this.motors = motors;
        this.inverted = inverted;
        //this.multiplier = multiplier;
    }
    
    public double get() {
        double speed = 0;
        
        for(int i = 0; i < motors.length; i++) {
            speed += get(i);
        }
        
        return speed / motors.length;
    }
    
    public double get(int index) {
        return motors[index].get();
        //return motors[index].get() / multiplier[i];
    }

    public void set(double speed, byte syncGroup) {
        speed = RobotTemplate.checkRange(speed, -1, 1);
        
        for(int i = 0; i < motors.length; i++) {
            if(inverted[i])
                speed *= -1;
            
            motors[i].set(speed, syncGroup);
        }
    }

    public void set(double speed) {
        for(int i = 0; i < motors.length; i++) {
            set(speed, i);
        }
    }
    
    public void set(double speed, int index) {
        speed = RobotTemplate.checkRange(speed, -1, 1);
        
        if(inverted[index])
            speed *= -1;
        
        //speed *= multiplier[index];
        
        motors[index].set(speed);
    }

    public void disable() {
        for(int i = 0; i < motors.length; i++) {
            motors[i].disable();
        }
    }

    public void pidWrite(double output) {
        set(output);
    }
}
