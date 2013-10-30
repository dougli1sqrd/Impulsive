package org.douglass.impulsive.spaceship.systems.doors;

import org.douglass.impulsive.spaceship.parts.ControlConsole;
import org.douglass.impulsive.spaceship.parts.PowerConduit;
import org.douglass.impulsive.spaceship.systems.ShipSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 10/29/13
 * Time: 9:55 PM
 */
public class DoorControlSystem implements ShipSystem {

    private List<DoorSystem> doorSystems;

    private PowerConduit powerLineIn;

    private ControlConsole console;

    public DoorControlSystem(List<DoorSystem> doorSystems, PowerConduit powerIn, ControlConsole console)  {
        this.doorSystems = new ArrayList<DoorSystem>();
        this.doorSystems.addAll(doorSystems);
        this.console = console;
        this.powerLineIn = powerIn;
        for(DoorSystem ds : this.doorSystems)    {
            this.console.connectTo(ds.getSignal());
        }
        this.console.connectTo(powerLineIn);
    }

    @Override
    public boolean isFunctional() {
        return powerLineIn.isFunctional() && console.isFunctional();
    }

    public void closeAllDoors() {
        for(DoorSystem ds : doorSystems)    {
            ds.closeDoor();
        }
    }

    public void openAllDoors()  {
        for(DoorSystem ds : doorSystems)    {
            ds.openDoor();
        }
    }
}
