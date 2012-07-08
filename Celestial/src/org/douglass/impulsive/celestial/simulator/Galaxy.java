package org.douglass.impulsive.celestial.simulator;

import java.util.ArrayList;

import org.douglass.impulsive.celestial.physics.ModifiedEulerMethod;
import org.douglass.impulsive.celestial.physics.Simulator;
import org.douglass.impulsive.celestial.physics.Vector;
import org.douglass.impulsive.celestial.spaceObjects.Body;
import org.douglass.impulsive.celestial.spaceObjects.Simulatable;
/**
 * The Galaxy class contains the state of all the Celestial Bodies, and where they are in relation
 * to each other.  The physics.Simulator classes will do the mathematics and physics equations
 * @author ericd
 *
 */
public class Galaxy {

	private ArrayList<Simulatable> bodies;
	
	@Deprecated
	public Galaxy(int num)	{
		
		bodies = new ArrayList<Simulatable>();
		//Distribute the planets
		//TODO
	}
	public Galaxy()	{
		
		bodies = new ArrayList<Simulatable>();	
	}
	
	public ArrayList<Simulatable> getSimulatableObjects()	{
		
		return bodies;
	}
	
	public Simulatable getCelestialBody(long id)	{
		
		for(Simulatable b : bodies)	{
			
			if(b.getId() == id)	{
				
				return b;
			}
		}
		return null;
	}
	
	public long galaxySize()	{
		
		return bodies.size();
	}
	
	/**
	 * Creates a new celestial body, puts it in the list of bodies
	 */
	public void addNewBody(Vector position, Vector velocity, double mass, double radius)	{
		
		Body rock = new Body(mass, radius, position);
		rock.setVelocity(velocity);
		bodies.add(rock);
	}
	
	public double potential(Vector r)	{
		
		double pot = 0;
		for(Simulatable b : bodies)	{
			
			pot += b.localPotential(r);
		}
		return -1*pot;
	}
	
	public void simulateStep(Simulator sim)	{
		
		for(Simulatable b : bodies)	{
			
			sim.calcPosVelocity(b);
		}
	}
	
	public static void main(String[] args) {
		
		Galaxy milky = new Galaxy();
		milky.addNewBody(new Vector(-1.5E11, 0, 0), new Vector(0, 29806, 0), 5.97E24, 6.37E6);
		milky.addNewBody(new Vector(0, 0, 0), new Vector(0, 0, 0), 1.99E33, 6.95E8);
		
		for(int i=0; i<365; i++)	{
			
			milky.simulateStep(new ModifiedEulerMethod(milky));
			System.out.println(milky.getCelestialBody(0).getPosition());
		}
	}
}
