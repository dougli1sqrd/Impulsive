package org.douglass.impulsive.spaceship.systems;

import org.douglass.impulsive.dimensions.Power;
import org.douglass.impulsive.spaceship.parts.ShipPart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 11/30/13
 * Time: 4:26 PM
 */
public class BasicShipSystem implements ShipSystem{

    private List<ShipPart> systemParts;

    private PowerSource powerSource;

    private Power currentPowerInput;

    private Power requiredPowerInput;

    public BasicShipSystem(PowerSource powerSource, Power requiredPowerInput)    {
        this.powerSource = powerSource;
        systemParts = new ArrayList<ShipPart>();
        currentPowerInput = Power.ZERO_POWER;
        this.requiredPowerInput = requiredPowerInput;
    }

    @Override
    public boolean isFunctional() {
        for(ShipPart p : systemParts)   {
            if(!p.isFunctional())   {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isPowered() {
        return currentPowerInput.isGreaterThan(requiredPowerInput) || currentPowerInput.equals(requiredPowerInput);
    }

    @Override
    public Power getRequiredPower() {
        return requiredPowerInput;
    }

    @Override
    public Power getCurrentInputPower() {
        return currentPowerInput;
    }

    @Override
    public void powerUp() {
        currentPowerInput = powerSource.drawPower(this, requiredPowerInput);
    }

    @Override
    public void powerOff() {
        powerSource.replenishPower(this);
        currentPowerInput = Power.ZERO_POWER;
    }
}
