/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techhounds.robot.util;

import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 * @author
 */
public class InvertableSpeedController implements SpeedController {

    /** The actual SpeedController */
    private final SpeedController delegate;
    /** Whether it should be inverted */
    private final boolean inverted;

    public InvertableSpeedController(SpeedController sc, boolean inverted) {
        delegate = sc;
        this.inverted = inverted;
    }

    /** {@inheritDoc} */
    public double get() {
        return inverted ? -delegate.get() : delegate.get();
    }

    /** {@inheritDoc} */
    public void set(double speed, byte syncGroup) {
        delegate.set(inverted ? -speed : speed, syncGroup);
    }

    /** {@inheritDoc} */
    public void set(double speed) {
        delegate.set(inverted ? -speed : speed);
    }

    /** {@inheritDoc} */
    public void disable() {
        delegate.disable();
    }

    /** {@inheritDoc} */
    public void pidWrite(double output) {
        delegate.pidWrite(inverted ? -output : output);
    }
}
