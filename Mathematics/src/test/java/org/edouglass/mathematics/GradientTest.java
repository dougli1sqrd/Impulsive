package org.edouglass.mathematics;

import org.edouglass.mathematics.vector.Vector;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 4/3/13
 * Time: 1:16 AM
 */
public class GradientTest {

    public void simpleSlopeGradientTest()   {
        Gradient grad = new Gradient(new SimpleSlopeField());
        Vector actual = grad.gradientAt(new Vector(1, 2, 3));

        Vector expected = new Vector(actual.entries());
        expected.setComponent(0, 1.0);

        assertEquals(expected, actual);
    }

    /**
     * This is a scalar function that returns the value of the x component
     */
    private class SimpleSlopeField implements ScalarField   {

        @Override
        public double evaluate(Vector v) {
            return v.getComponent(0);
        }
    }
}
