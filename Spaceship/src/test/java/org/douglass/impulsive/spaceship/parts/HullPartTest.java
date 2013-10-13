package org.douglass.impulsive.spaceship.parts;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 10/12/13
 * Time: 1:14 AM
 */
public class HullPartTest {

    private HullPart hull;

    @Before
    public void setup() {
        hull = new HullPart();
    }

    @Test
    public void isFunctionalTest()  {
        hull.setStructuralIntegrity(HullPart.FUNCTIONAL_THRESHOLD);
        assertTrue(hull.isFunctional());
        hull.setStructuralIntegrity(HullPart.FUNCTIONAL_THRESHOLD - 1);
        assertFalse(hull.isFunctional());
    }

    @Test
    public void damageTest()    {
        hull.setStructuralIntegrity(HullPart.FUNCTIONAL_THRESHOLD);
        hull.damage();
        assertFalse(hull.isFunctional());
    }

    @Test
    public void repairTest()    {
        hull.setStructuralIntegrity(HullPart.FUNCTIONAL_THRESHOLD - 1);
        hull.repair();
        assertTrue(hull.isFunctional());
    }

    @Test
    public void listConnectedPartsTest()    {
        HullPart a = new HullPart();
        List<ShipPart> partList = new ArrayList<ShipPart>();
        partList.add(a);
        hull.connectTo(partList);
        assertEquals(partList, hull.listConnectedParts());
    }

    @Test
    public void connectToPartsTest()    {
        HullPart a = new HullPart();
        HullPart b = new HullPart();
        HullPart c = new HullPart();
        List<ShipPart> partList = new ArrayList<ShipPart>();
        partList.add(a);
        partList.add(b);
        partList.add(c);

        hull.connectTo(partList);
        assertEquals(partList, hull.listConnectedParts());
        assertEquals(hull, a.listConnectedParts().get(0));
        assertEquals(hull, b.listConnectedParts().get(0));
        assertEquals(hull, c.listConnectedParts().get(0));
    }

    @Test
    public void disconnectFromTest()    {
        HullPart a = new HullPart();
        HullPart b = new HullPart();
        List<ShipPart> partList = new ArrayList<ShipPart>();
        partList.add(a);
        partList.add(b);
        hull.connectTo(partList);
        hull.disconnectFrom(a);

        assertTrue(hull.listConnectedParts().contains(b));
        assertFalse(hull.listConnectedParts().contains(a));
    }

    @Test
    public void disconnectAllTest() {
        HullPart a = new HullPart();
        HullPart b = new HullPart();
        List<ShipPart> partList = new ArrayList<ShipPart>();
        partList.add(a);
        partList.add(b);
        hull.connectTo(partList);

        hull.disconnect();

        assertTrue(hull.listConnectedParts().isEmpty());
    }

    @Test
    public void isConnectedTest()   {
        HullPart a = new HullPart();
        HullPart b = new HullPart();
        List<ShipPart> partList = new ArrayList<ShipPart>();
        partList.add(a);
        hull.connectTo(partList);

        assertTrue(hull.isConnected(a));
        assertFalse(hull.isConnected(b));
    }
}
