package org.douglass.impulsive.celestial.physics;

import static org.douglass.impulsive.celestial.simulator.SimConstansts.GRAD_EPSILON;
import org.douglass.impulsive.celestial.simulator.Galaxy;
import org.douglass.impulsive.celestial.spaceObjects.Simulatable;
/**
 * This class is the start of the physics engine of the Gravity simulator.  Depending on the 
 * model of Gravitational potential, it will output positions and velocities and model the Galaxy
 * @author ericd
 *
 */
public abstract class Simulator {

	private Galaxy gal;
	
	public Simulator(Galaxy gal)	{
		
		this.gal = gal;
	}
	
	protected Galaxy getGalaxy()	{
		
		return gal;
	}
	
	/**
	 * This calculates the gravitational potential at a particular point in space.
	 * @param r The position vector to the point in space where the potential is calculated
	 * @return The value of the gravitations potential at point r.
	 */
	public abstract double potential(Vector r);
	/**
	 * Calculates the new position and velocity for the simulatable object b.  The argument b is modified 
	 * by the method.
	 * @param b
	 */
	public abstract void calcPosVelocity(Simulatable b);
	
	/**
	 * Numerical Gradient.  This takes 6 sample points from the original r and calculates the
	 * numerical derivative in each of the 3 directions, giving the numerical gradient.
	 * @param r position vector indicating the location where the gradient value should be calculated from
	 * @return the numerical gradient at the position r
	 */
	public Vector grad(Vector r)	{
		
		double[] samplepots = new double[6];
		samplepots[0] = potential(VectorMath.add(r, new Vector(GRAD_EPSILON, 0, 0)));
		samplepots[1] = potential(VectorMath.add(r, new Vector(-1*GRAD_EPSILON, 0, 0)));
		samplepots[2] = potential(VectorMath.add(r, new Vector(0, GRAD_EPSILON, 0)));
		samplepots[3] = potential(VectorMath.add(r, new Vector(0, -1*GRAD_EPSILON, 0)));
		samplepots[4] = potential(VectorMath.add(r, new Vector(0, 0, GRAD_EPSILON)));
		samplepots[5] = potential(VectorMath.add(r, new Vector(0, 0, -1*GRAD_EPSILON)));
		double gradx = (samplepots[0] - samplepots[1])/(2*GRAD_EPSILON);
		double grady = (samplepots[2] - samplepots[3])/(2*GRAD_EPSILON);
		double gradz = (samplepots[4] - samplepots[5])/(2*GRAD_EPSILON);
		return new Vector(gradx, grady, gradz);
	}
	
}
