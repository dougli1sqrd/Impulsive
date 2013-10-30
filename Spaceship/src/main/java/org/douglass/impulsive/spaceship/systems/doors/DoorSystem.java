package org.douglass.impulsive.spaceship.systems.doors;

import org.douglass.impulsive.spaceship.parts.Door;
import org.douglass.impulsive.spaceship.parts.PowerConduit;
import org.douglass.impulsive.spaceship.parts.ShipPart;
import org.douglass.impulsive.spaceship.parts.SignalConduit;
import org.douglass.impulsive.spaceship.systems.ShipSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 10/29/13
 * Time: 11:23 PM
 */
public class DoorSystem implements ShipSystem{

    private Door door;

    private PowerConduit powerIn;

    private SignalConduit signal;

    private boolean doorOpen;

    public DoorSystem(Door door, PowerConduit powerIn, SignalConduit signal) {
        door.connectTo(powerIn);
        door.connectTo(signal);
        this.door = door;
        this.powerIn = powerIn;
        this.signal = signal;
        doorOpen = false;
    }

    @Override
    public boolean isFunctional() {
        return door.isFunctional() && powerIn.isFunctional() && signal.isFunctional();
    }

    public SignalConduit getSignal()    {
        return signal;
    }

    public boolean isDoorOpen() {
        return doorOpen;
    }

    public void openDoor()  {
        if(!isDoorOpen() && isFunctional()) {
            doorOpen = true;
        }
    }

    public void closeDoor() {
        if(isDoorOpen() && isFunctional())  {
            doorOpen = false;
        }
    }
}
