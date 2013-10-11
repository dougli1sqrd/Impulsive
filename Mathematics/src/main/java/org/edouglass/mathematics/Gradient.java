package org.edouglass.mathematics;

import org.edouglass.mathematics.vector.Vector;
import org.edouglass.mathematics.vector.Vector3D;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 3/29/13
 * Time: 1:44 AM
 */
public class Gradient {

    private ScalarField field;

    private double epsilon;

    public Gradient(ScalarField scalarField) {
        field = scalarField;
    }

    public Vector gradientAt(Vector v)    {

        return new Vector(1);
    }

    private Vector makeDeltaVector(int direction, Vector v) {
        Vector delta = new Vector(v.entries());
        delta.setComponent(direction, epsilon);
        return delta;
    }
}
