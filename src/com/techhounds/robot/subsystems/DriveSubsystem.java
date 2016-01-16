
package com.techhounds.robot.subsystems;

import com.techhounds.robot.commands.DriveGamePadArcade;
import com.techhounds.robot.commands.DriveGamePadTank;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Simple "tank" style drive system with pairs of motors driving each side of the robot.
 */
public class DriveSubsystem extends Subsystem {

    private final SpeedController left0;
    private final SpeedController left1;
    private final SpeedController right0;
    private final SpeedController right1;
    
    /** Set to true if left motors wired in reverse. */
    private static final boolean LEFT_INVERTED = false;
    
    /** Set to true if right motors wired in reverse. */
    private static final boolean RIGHT_INVERTED = true;

    DriveSubsystem(SpeedController left0, SpeedController left1, SpeedController right0, SpeedController right1) {
        super("Drive Subsystem");
        this.left0 = left0;
        this.left1 = left1;
        this.right0 = right0;
        this.right1 = right1;
    }
    
    /**
     * Set the power on the left and right drive wheels.
     * 
     * @param left Power in range of [-1.0, +1.0] (positive to move forward).
     * @param right Power in range of [-1.0, +1.0] (positive to move forward).
     */
    public void setPower(double left, double right) {
        left = LEFT_INVERTED ? -left : left;
        right = RIGHT_INVERTED ? -right : right;
        left0.set(left);
        left1.set(left);
        right0.set(right);
        right1.set(right);
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
       setDefaultCommand(new DriveGamePadArcade(this));
    }
}

