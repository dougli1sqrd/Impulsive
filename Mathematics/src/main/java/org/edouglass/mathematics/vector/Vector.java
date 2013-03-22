package org.edouglass.mathematics.vector;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 3/22/13
 * Time: 12:55 AM
 */
public class Vector	{

    private double[] components;

    /**
     * Creates a vector with N number of components
     */
    public Vector(double...components)	{

        this.components = new double[components.length];
        System.arraycopy(components, 0, this.components, 0, components.length);
    }
    /**
     * Creates a 0 vector with the specified length
     * @param length The number of components this vector should have
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

    /**
     * Converts the vector into an array of numbers
     * @return returns an array of doubles that are the compenents of this vector in order
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

