package org.edouglass.mathematics.vector;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 3/25/13
 * Time: 11:03 PM
 */
public class VectorTest {

    private Vector testVector;

    private static final double relativeDelta = 1.0E-9;

    @Before
    public void setup() {
        testVector = new Vector3D(1, 1, 1);
    }

    @Test
    public void normTest()  {
        double expected = Math.sqrt(3.0);
        assertEquals(expected, testVector.norm(), expected*relativeDelta);
    }

    @Test
    public void scaleTest() {
        Vector scaled = testVector.scale(3.0);
        double expectedLength = 3.0*Math.sqrt(3.0);
        assertEquals(expectedLength, scaled.norm(), expectedLength*relativeDelta);

        assertEquals(3.0, scaled.getComponent(0), 3.0*relativeDelta);
        assertEquals(3.0, scaled.getComponent(1), 3.0*relativeDelta);
        assertEquals(3.0, scaled.getComponent(2), 3.0*relativeDelta);
    }

    @Test
    public void unitTest()  {
        Vector unit = testVector.unit();
        assertEquals(1.0, unit.norm(), 1.0*relativeDelta);
    }

    @Test
    public void addTest()   {
        Vector added = testVector.add(new Vector(1, 0, 0));
        assertEquals(2.0, added.getComponent(0), 2.0*relativeDelta);
        assertEquals(1.0, added.getComponent(1), 1.0*relativeDelta);
        assertEquals(1.0, added.getComponent(2), 1.0*relativeDelta);
    }

    @Test
    public void differentSizedVectorAddTest()   {
        Vector added = testVector.add(new Vector(1, 0));
        assertEquals(2.0, added.getComponent(0), 2.0*relativeDelta);
        assertEquals(1.0, added.getComponent(1), 1.0*relativeDelta);
        assertEquals(1.0, added.getComponent(2), 1.0*relativeDelta);

        Vector added2 = testVector.add(new Vector(1, 0, 0, 50));
        assertEquals(2.0, added2.getComponent(0), 2.0*relativeDelta);
        assertEquals(1.0, added2.getComponent(1), 1.0*relativeDelta);
        assertEquals(1.0, added2.getComponent(2), 1.0*relativeDelta);
    }

    @Test
    public void subtractTest()  {
        Vector subtracted = testVector.subtract(new Vector(1, 0, 0));
        assertEquals(0.0, subtracted.getComponent(0), 0.0);
        assertEquals(1.0, subtracted.getComponent(1), 1.0*relativeDelta);
        assertEquals(1.0, subtracted.getComponent(2), 1.0*relativeDelta);
    }

    @Test
    public void differentSizedVectorSubtractTest()  {
        Vector subtracted1 = testVector.subtract(new Vector(1, 0));
        assertEquals(0.0, subtracted1.getComponent(0), 0.0);
        assertEquals(1.0, subtracted1.getComponent(1), 1.0*relativeDelta);
        assertEquals(1.0, subtracted1.getComponent(2), 1.0*relativeDelta);

        Vector subtracted2 = testVector.subtract(new Vector(1, 0, 0, 50));
        assertEquals(0.0, subtracted2.getComponent(0), 0.0);
        assertEquals(1.0, subtracted2.getComponent(1), 1.0*relativeDelta);
        assertEquals(1.0, subtracted2.getComponent(2), 1.0*relativeDelta);
    }
}
