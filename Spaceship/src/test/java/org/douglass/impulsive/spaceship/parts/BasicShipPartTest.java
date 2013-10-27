package org.douglass.impulsive.spaceship.parts;

import junit.framework.Assert;
import org.douglass.impulsive.spaceship.damage.Damage;
import org.douglass.impulsive.spaceship.damage.DamageData;
import org.douglass.impulsive.spaceship.damage.DamageType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 10/26/13
 * Time: 7:05 PM
 */
public class BasicShipPartTest {

    private BasicShipPart part;

    private static int functionalThreshhold = 6;
    private static int maxIntegrity = 20;

    private Material testMaterial = new Material() {
        @Override
        public int resolveDamage(Damage hit) {
            int sum = 0;
            for(DamageData d : hit.getDamages())    {
                sum += d.amount;
            }
            return sum;
        }
    };

    @Before
    public void setup() {
        part = new BasicShipPart(testMaterial, maxIntegrity, functionalThreshhold);
    }

    @Test
    public void isFunctionalTest()  {
        assertTrue(part.isFunctional());

        part.setStructuralIntegrity(functionalThreshhold+1);
        assertTrue(part.isFunctional());

        part.setStructuralIntegrity(functionalThreshhold);
        assertFalse(part.isFunctional());
    }

    @Test
    public void repairTest()    {
        part.setStructuralIntegrity(functionalThreshhold);
        assertFalse(part.isFunctional());
        part.repair();
        assertTrue(part.isFunctional());
    }

    @Test
    public void damageDoesDamageTest()    {
        part.damage(new Damage(new DamageData(DamageType.EXPLOSIVE, 5)));
        assertEquals(maxIntegrity-5, part.getStructuralIntegrity());
    }

    @Test
    public void damageBreaksPartTest()  {
        part.setStructuralIntegrity(functionalThreshhold+5);
        part.damage(new Damage(new DamageData(DamageType.EXPLOSIVE, 5)));
        assertFalse(part.isFunctional());
    }

    @Test
    public void damageDisconnectsPartTest() {
        BasicShipPart p = new BasicShipPart(testMaterial, 6, 20);
        p.connectTo(part);
        part.setStructuralIntegrity(5);
        part.damage(new Damage(new DamageData(DamageType.EXPLOSIVE, 5)));
        assertFalse(part.isConnected(p));
    }

    @Test
    public void connectToPartListTest() {
        BasicShipPart a = new BasicShipPart(testMaterial, functionalThreshhold, maxIntegrity);
        BasicShipPart b = new BasicShipPart(testMaterial, functionalThreshhold, maxIntegrity);
        ArrayList<ShipPart> partList = new ArrayList<ShipPart>();
        partList.add(a);
        partList.add(b);
        part.connectTo(partList);
        assertTrue(part.isConnected(a));
        assertTrue(part.isConnected(b));
        assertTrue(a.isConnected(part));
        assertTrue(b.isConnected(part));
    }

    @Test
    public void connectToSinglePartTest()   {
        BasicShipPart p = new BasicShipPart(testMaterial, functionalThreshhold, maxIntegrity);
        part.connectTo(p);
        assertTrue(part.isConnected(p));
        assertTrue(p.isConnected(part));
    }

    @Test
    public void disconnectTest()    {
        BasicShipPart a = new BasicShipPart(testMaterial, functionalThreshhold, maxIntegrity);
        BasicShipPart b = new BasicShipPart(testMaterial, functionalThreshhold, maxIntegrity);
        ArrayList<ShipPart> partList = new ArrayList<ShipPart>();
        partList.add(a);
        partList.add(b);
        part.connectTo(partList);
        part.disconnect();
        assertFalse(part.isConnected(a));
        assertFalse(part.isConnected(b));
    }

    @Test
    public void disconnectOneTest() {
        BasicShipPart a = new BasicShipPart(testMaterial, functionalThreshhold, maxIntegrity);
        BasicShipPart b = new BasicShipPart(testMaterial, functionalThreshhold, maxIntegrity);
        ArrayList<ShipPart> partList = new ArrayList<ShipPart>();
        partList.add(a);
        partList.add(b);
        part.connectTo(partList);
        part.disconnectFrom(a);
        assertFalse(part.isConnected(a));
        assertTrue(part.isConnected(b));
    }

    @Test
    public void listConnectedPartsTest()    {
        BasicShipPart a = new BasicShipPart(testMaterial, functionalThreshhold, maxIntegrity);
        BasicShipPart b = new BasicShipPart(testMaterial, functionalThreshhold, maxIntegrity);
        ArrayList<ShipPart> partList = new ArrayList<ShipPart>();
        partList.add(a);
        partList.add(b);
        part.connectTo(partList);
        assertEquals(partList, part.listConnectedParts());
    }

    @Test
    public void isConnectedTest()   {
        BasicShipPart a = new BasicShipPart(testMaterial, functionalThreshhold, maxIntegrity);
        part.connectTo(a);
        assertTrue(part.isConnected(a));
    }
}
