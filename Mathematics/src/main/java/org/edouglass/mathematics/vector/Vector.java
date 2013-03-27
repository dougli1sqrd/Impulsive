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

    /**
     * Scale this vector by the scaling factor.  This multiplies each component
     * of the vector by the factor; consequently scaling the length of the vector
     * as well.
     *
     * @param factor this amount to scale the vector by
     * @return a new vector whose components are <code>factor</code> times
     * larger than than this vector and whose length is also <code>factor</code>
     * times larger.
     */
    public Vector scale(double factor)  {
        double[] components = new double[this.components.length];
        for(int i=0; i<this.components.length; i++)  {
            components[i] = factor*this.components[i];
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
     * Make a new vector with the vector sum of this vector and <code>v</code>
     * If the vector supplied is of a different size than this vector, then
     * the larger of the two will just fill in zeroes for the entries that
     * do not correspond to the other. For example, [1, 2, 3] summed with
     * [1, 1] is equivalent to [1, 2, 3] + [1, 1, 0]
     * @param v The vector to add to this vector
     */
    public Vector add(Vector v) {

        double[] addedComponents = new double[components.length];
        for(int i=0; i<components.length; i++)  {
            if(i < v.entries()) {
                addedComponents[i] = components[i] + v.getComponent(i);
            } else  {
                addedComponents[i] = components[i];
            }
        }
        return new Vector(addedComponents);
    }
    /**
     * Make a new vector with the vector subtraction of this vector and <code>v</code>.
     * (defined as <code>A - B = A + (-1)*B</code>)
     * If the vector supplied is of a different size than this vector, then
     * the larger of the two will just fill in zeroes for the entries that
     * do not correspond to the other. For example, [1, 2, 3] subtracted with
     * [1, 1] is equivalent to [1, 2, 3] - [1, 1, 0]
     * @param v The vector to add to this vector
     */
    public Vector subtract(Vector v) {
        double[] addedComponents = new double[components.length];
        for(int i=0; i<components.length; i++)  {
            if(i < v.entries()) {
                addedComponents[i] = components[i] - v.getComponent(i);
            } else  {
                addedComponents[i] = components[i];
            }
        }
        return new Vector(addedComponents);
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
