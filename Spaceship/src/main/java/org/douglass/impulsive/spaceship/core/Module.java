package org.douglass.impulsive.spaceship.core;

import java.util.*;

import org.douglass.impulsive.celestial.physics.Vector;

public abstract class Module {

	/**
	 * This is a Mapping of the six directions of a cube to a Module that is connected to the side of the Direction.
	 */
	private Map<Direction, Module> adjacent; 
	
	private String name;
	
	/**
	 * Unique id number for the module in this spaceship
	 */
	private int id;
	/**
	 * The mass of the module when empty, or not in use. in kg.
	 */
	private final double standardMass;
	/**
	 * The mass of anything in the Module.  The standardMass is a constant, and the fillMass is variable.  Combined, they are 
	 * the total mass of the Module.
	 */
	private double fillMass;
	/**
	 * This is the size of the module.  The minimum size is 1.  At the moment these are arbitrary units.
	 * It might end up that a size of 2 takes up twice as much volume as a Module with size 1.  We'll see
	 * how this ends up.
	 */
	private final int size;
	/**
	 * This is if the Module is turned on or not.  Treat as 'functioning'. Reasons it might be de-activated 
	 * would be: destroyed (damage == 100), and activated means works normally.  Maybe this could be used
	 * as a sort of heat/energy signature that could be picked up by sensors.  If all Modules are off, the 
	 * spaceship would have to completely turn on, and we should say that takes time.  
	 */
	private boolean activated;
	/**
	 * ranges from 0 to 100.  If damage == 100, then the module is completely destroyed and not repairable.
	 * Damage should cause the Module to work less efficiently in some regard.
	 */
	private int damage;
	/**
	 * A module that is required is necessary for a spaceship to function.  If all modules of the same 
	 * required type are destroyed in a spaceship, then the spaceship is no longer functional.
	 */
	private boolean required;
	/**
	 * This represents the amount of power required to power this module.  Later, perhaps implement some kind of
	 * Energy type. In J/s.
	 */
	private double powerusage;
	/**
	 * This is the relative position in space that this module is to the center of mass of the ship.  This will be 
	 * set by the spaceship when this module is added to it.
	 */
	private Vector relativeposition;
	
	/**
	 * Basic constructor for a module.
	 * @param name
	 * @param size
	 * @param standardMass
	 * @param power
	 * @param required
	 */
	public Module(String name, int size, double standardMass, double power, boolean required)	{
		
		this.name = name;
		this.size = size;
		this.required = required;
		adjacent = new HashMap<Direction, Module>();
		this.standardMass = standardMass;
		powerusage = power;
		fillMass = 0;
	}
	/**
	 * Default constructor which uses very basic values.  This would not make a functioning module.  The values should be 
	 * reset in a child constructor.
	 */
	public Module()	{
		
		name = "Module";
		size = 1;
		required = false;
		standardMass = 0;
		powerusage = 0;
		fillMass = 0;
	}
	
	public int getSize()	{
		
		return size;
	}
	
	public void setFillMass(double fill)	{
		
		this.fillMass = fill;
	}
	
	public double getFillMass()	{
		
		return fillMass;
	}
	
	public double getMass()	{
		
		return fillMass + standardMass;
	}
	
	
	/**
	 * This method adds a Module as its neighbor.  This means they are "connected".  Adds the neighbor module in the direction
	 * specified by the direction parameter.
	 * @param neighbor An adjacent module.
	 */
	public boolean addAdjacent(Module neighbor, Direction dir)	{
		
		adjacent.put(dir, neighbor);
		neighbor.addOppositeNeighbor(this, dir.opposite());
		return true;
	}
	
	private void addOppositeNeighbor(Module neighbor, Direction opposite)	{
		
		adjacent.put(opposite, neighbor);
	}
	
	/**
	 * This is a graph search of all connected Modules, returning the list of modules that have a path to this module.
	 * @return The list of Modules that have a path to this module.
	 */
	public List<Module> listModules()	{
		
		ArrayList<Module> mods = new ArrayList<Module>(); //this is also the list of visited nodes.
		//search the graph.
		Queue<Module> queue = new LinkedList<Module>();
		queue.offer(this);
		mods.add(this);
		
		while(!queue.isEmpty())	{
			
			Module m = queue.poll();
			
			//For each direction, get the mapped module and add it to the queue
			for(Module a : m.getAdjacent())	{
				
				if(!mods.contains(a))	{
					//For each attached Module, if it is not in the list of modules visited, then add it.
					mods.add(a);
					queue.offer(a); //add it to the queue, but we know we've visited it now, so it will never be added again
				}
			}
		}
		//At this point, all the Modules should have been visited. And the list of visited nodes is the complete list of
		//connected nodes.  We will return this list.
		return mods;
	}
	
	public ArrayList<Module> getAdjacent() {
		
		ArrayList<Module> list = new ArrayList<Module>();
		
		for(Direction dir : adjacent.keySet())	{
			
			list.add(adjacent.get(dir));
		}
		
		return list;
	}
	public double getVolume()	{
		
		return getStaticVolume(size);
	}
	
	public static double getStaticVolume(int size)	{
		
		double length = (size <= 2) ? size : 2.0*size;
		return Math.pow(length, 3);
	}
	
	public double getBaseMass()	{
		
		return standardMass;
	}
	
	public boolean isActivated()	{
		
		return activated;
	}
	
	public void setActivated(boolean activated)	{
		
		this.activated = activated;
	}
	
	public void setPowerUsage(double power)	{
		
		powerusage = power;
	}
	
	public double getPowerUsage()	{
		
		return powerusage;
	}
	
}
