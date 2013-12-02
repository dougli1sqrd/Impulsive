package org.douglass.impulsive.spaceship.systems;

import org.douglass.impulsive.dimensions.Power;
import org.douglass.impulsive.spaceship.parts.ShipPart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 11/30/13
 * Time: 5:27 PM
 */
public class PowerSource implements ShipSystem {

    private List<ShipPart> systemParts;

    private Map<ShipSystem, Power> powerDraws;

    private final Power powerRating;

    private Power currentPowerOutput;

    public PowerSource(Power powerRating, List<ShipPart> systemParts) {
        this.systemParts = new ArrayList<ShipPart>();
        this.systemParts.addAll(systemParts);
        powerDraws = new HashMap<ShipSystem, Power>();
        this.powerRating = powerRating;
        currentPowerOutput = Power.ZERO_POWER;
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
        return isFunctional();
    }

    @Override
    public Power getRequiredPower() {
        return Power.ZERO_POWER;
    }

    @Override
    public Power getCurrentInputPower() {
        return Power.ZERO_POWER;
    }

    @Override
    public void powerUp() {
        if(isFunctional())  {
            currentPowerOutput = powerRating;
        }
    }

    @Override
    public void powerOff() {
        if(isFunctional())  {
            killPowerDrains();
            currentPowerOutput = Power.ZERO_POWER;
        }
    }

    /**
     * This method lets a ShipSystem request the power source for some power to power the system.  If there is enough
     * power, then it reduces the current power output by the requested amount, and returns the requested amount.  If
     * there is not enough power, or there is some other reason why it cannot provide the power, then it returns
     * Power.ZERO_POWER.
     * @param requested The amount of Power a system would like to draw from this Power source.
     * @return The amount of power able to be drawn from the power source.
     */
    public Power drawPower(ShipSystem draw, Power requested) {
        if(!isFunctional()) {
            return Power.ZERO_POWER;
        }
        if(requested.getAmount() <= currentPowerOutput.getAmount()) {
            currentPowerOutput = new Power(currentPowerOutput.getAmount() - requested.getAmount(), "W");
            powerDraws.put(draw, requested);
            return requested;
        } else {
            return Power.ZERO_POWER;
        }
    }

    /**
     * This is for an external system to call on the power source it's attached to when it is powered down.
     * @param draw The system that is powering down.
     */
    public void replenishPower(ShipSystem draw)    {
        if(!powerDraws.containsKey(draw))   {
            return;
        }
        currentPowerOutput = currentPowerOutput.add(powerDraws.get(draw));
        powerDraws.remove(draw);
    }

    /**
     * Cuts all power drains to this PowerSource, replenishing the power output.  Also powers off the connected
     * Systems.
     */
    public void killPowerDrains()  {
       for(ShipSystem draw : powerDraws.keySet())   {
           replenishPower(draw);
           draw.powerOff();
       }
    }

    public Power getCurrentPowerOutput()    {
        return currentPowerOutput;
    }
}
