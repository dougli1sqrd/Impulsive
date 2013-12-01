package org.douglass.impulsive.spaceship.systems;

import org.douglass.impulsive.dimensions.Power;
import org.douglass.impulsive.spaceship.parts.ShipPart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 10/27/13
 * Time: 1:55 PM
 */
public class DumbShipSystem implements ShipSystem {

    private List<ShipPart>  systemParts;

    public DumbShipSystem(List<ShipPart> systemParts)   {
        this.systemParts = new ArrayList<ShipPart>();
        this.systemParts.addAll(systemParts);
    }

    @Override
    public boolean isFunctional()  {
        for(ShipPart part : systemParts)    {
            if(!part.isFunctional())    {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isPowered() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Power getRequiredPower() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Power getCurrentInputPower() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void powerUp() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void powerOff() {
        //To change body of implemented methods use File | Settings | File Templates.
    }


}
