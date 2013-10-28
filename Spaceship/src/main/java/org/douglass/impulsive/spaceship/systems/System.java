package org.douglass.impulsive.spaceship.systems;

import org.douglass.impulsive.spaceship.parts.ShipPart;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 10/27/13
 * Time: 1:55 PM
 */
public class System {

    private List<ShipPart>  systemParts;

    private boolean isFunctional()  {
        for(ShipPart part : systemParts)    {
            if(!part.isFunctional())    {
                return false;
            }
        }
        return true;
    }
}
