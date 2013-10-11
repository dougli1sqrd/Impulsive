package org.edouglass.mathematics.vector;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 3/22/13
 * Time: 1:29 AM
 * A convenient way of representing vectors in 3-space.  X is the first entry, Y is the second entry, Z is the third
 * entry.
 */
public class Vector3D extends Vector    {

    public Vector3D(double x, double y, double z)   {
        super(x, y, z);
    }

    public double x()    {
        return getComponent(0);
    }

    public double y()    {
        return getComponent(1);
    }

    public double z()    {
        return getComponent(2);
    }
}
