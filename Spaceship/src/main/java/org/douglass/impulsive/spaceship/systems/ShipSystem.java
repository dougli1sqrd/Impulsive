package org.douglass.impulsive.spaceship.systems;

import org.douglass.impulsive.dimensions.Power;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 10/29/13
 * Time: 9:41 PM
 */
public interface ShipSystem {

    boolean isFunctional();

    boolean isPowered();

    Power getRequiredPower();

    Power getCurrentInputPower();

    void powerUp();

    void powerOff();
}
