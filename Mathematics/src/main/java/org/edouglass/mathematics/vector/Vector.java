package org.edouglass.mathematics.vector;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 3/22/13
 * Time: 12:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class Vector	{

    private double[] components;

    /**
     * Specifies N number of components in a vector
     * @param components
     */
    public Vector(double...components)	{

        this.components = new double[components.length];
        for(int i=0; i<components.length; i++)	{

            this.components[i] = components[i];
        }
    }
    /**
     * creates a 0 vector with the specified length
     * @param length
     */
    public Vector(int length)	{

        components = new double[length];
    }

    public double getComponent(int index)	{

        return components[index];
    }

    public void setComponent(int index, double value)	{

        components[index] = value;
    }
    /**
     *
     * @return The number of components in this vector
     */
    public int entries()	{

        return components.length;
    }
    /**
     * This calculates the total length of the vector, also called the "norm"
     * @return the length of the vector, or the square root of the dot product with itself.
     */
    public double norm()	{

        double total = 0;
        for(double d : components)	{

            total += d*d;
        }
        return Math.sqrt(total);
    }

    public Vector scale(double factor)  {
        double[] components = new double[this.components.length];
        for(int i=0; i<this.components.length; i++)  {
            components[i] = factor*components[i];
        }
        return new Vector(components);
    }

    /**
     * This method returns a new vector in the direction of this vector, with a length of 1
     * @return A unit vector in the direction of this vector
     */
    public Vector unit()	{

        double length = norm();
        return scale(1/length);
    }

    @Override
    public Vector clone()	{

        Vector res = new Vector(components.length);
        for(int i=0; i<components.length; i++)	{

            res.setComponent(i, components[i]);
        }
        return res;
    }
    /**
     * Converts the vector into an array of numbers
     * @return
     */
    public double[] toArray()	{

        return components;
    }
    @Override
    public String toString()	{

        String vec = "<";
        for(int i=0; i<components.length-1; i++)	{

            vec += components[i]+", ";
        }
        vec += components[components.length-1]+">";
        return vec;
    }

}

