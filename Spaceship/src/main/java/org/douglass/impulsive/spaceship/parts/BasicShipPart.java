package org.douglass.impulsive.spaceship.parts;

import org.douglass.impulsive.spaceship.damage.Damage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 10/25/13
 * Time: 10:44 AM
 */
public class BasicShipPart implements ShipPart{

    private Material material;

    private List<ShipPart> connectedParts;

    private int currentStructuralIntegrity;

    private int maxStructuralIntegrity;

    private int functionalThreshold;

    private int id;

    private static int nextId = 1;

    public BasicShipPart(Material material, int structuralIntegrity, int functionalThreshold)  {
        this.material = material;
        connectedParts = new ArrayList<ShipPart>();
        currentStructuralIntegrity = structuralIntegrity;
        maxStructuralIntegrity = structuralIntegrity;
        this.functionalThreshold = functionalThreshold;
        id = nextId;
        nextId++;
    }

    @Override
    public boolean isFunctional() {
        return currentStructuralIntegrity > functionalThreshold;
    }

    @Override
    public void repair() {
        if(currentStructuralIntegrity < maxStructuralIntegrity) {
            currentStructuralIntegrity++;
        }
    }

    @Override
    public void damage(Damage damage) {
        int structuralDamage = material.resolveDamage(damage);
        currentStructuralIntegrity -= structuralDamage;
        if(currentStructuralIntegrity <= 0)  {
            disconnect();
        }
    }

    @Override
    public void connectTo(List<ShipPart> toConnect) {
        for(ShipPart part : toConnect)  {
            if(!connectedParts.contains(part))  {
                connectIndividualPart(part);
            }
        }
    }

    @Override
    public void connectTo(ShipPart toConnect) {
        if(!connectedParts.contains(toConnect)) {
            connectIndividualPart(toConnect);
        }
    }

    @Override
    public void disconnect() {
        List<ShipPart> copy = new ArrayList<ShipPart>();
        copy.addAll(connectedParts);
        for(ShipPart part : copy) {
            disconnectFrom(part);
        }
    }

    @Override
    public void disconnectFrom(ShipPart p) {
        if(connectedParts.contains(p))  {
            connectedParts.remove(p);
            p.disconnectFrom(this);
        }
    }

    @Override
    public List<ShipPart> listConnectedParts() {
        return connectedParts;
    }

    @Override
    public boolean isConnected(ShipPart p) {
        return connectedParts.contains(p);
    }

    @Override
    public String toString() {
        return "Hull Part #"+id;
    }

    /**
     * This is only to be used for testing.
     */
    protected void setStructuralIntegrity(int integrity)   {
        currentStructuralIntegrity = integrity;
    }

    /**
     * This is only to be used for testing
     */
    protected int getStructuralIntegrity()  {
        return currentStructuralIntegrity;
    }

    private void connectIndividualPart(ShipPart part) {
        connectedParts.add(part);
        List<ShipPart> thisList = new ArrayList<ShipPart>();
        thisList.add(this);
        part.connectTo(thisList);
    }
}
