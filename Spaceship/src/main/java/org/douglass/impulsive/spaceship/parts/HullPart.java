package org.douglass.impulsive.spaceship.parts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 10/12/13
 * Time: 1:04 AM
 */
public class HullPart implements ShipPart {

    private int structuralIntegrity;

    private List<ShipPart> connectedParts;

    private int id;

    public static final int FUNCTIONAL_THRESHOLD = 6;

    private static int nextId = 1;

    public HullPart()   {
        structuralIntegrity = 20;
        connectedParts = new ArrayList<ShipPart>();
        id = nextId;
        nextId++;
    }

    @Override
    public boolean isFunctional() {
        return structuralIntegrity >= FUNCTIONAL_THRESHOLD;
    }

    @Override
    public void repair() {
        structuralIntegrity++;
    }

    @Override
    public void damage() {
        structuralIntegrity--;
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

    public void setStructuralIntegrity(int integrity)   {
        this.structuralIntegrity = integrity;
    }

    @Override
    public int hashCode() {
        int result = structuralIntegrity;
        result = 31 * result + (connectedParts != null ? connectedParts.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HullPart hullPart = (HullPart) o;

        if (structuralIntegrity != hullPart.structuralIntegrity) return false;
        return !(connectedParts != null ? !connectedParts.equals(hullPart.connectedParts) : hullPart.connectedParts != null);

    }

    @Override
    public String toString() {
        return "Hull Part #"+id;
    }

    private void connectIndividualPart(ShipPart part) {
        connectedParts.add(part);
        List<ShipPart> thisList = new ArrayList<ShipPart>();
        thisList.add(this);
        part.connectTo(thisList);
    }
}
