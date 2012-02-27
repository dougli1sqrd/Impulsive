package douglass.games.spaceship.core;

import java.util.ArrayList;
import java.util.List;

public abstract class Module {

	/**
	 * The list of adjacent modules.  Modules that are adjacent are "connected".  You can only reach 
	 * modules through a series of connections.  A <--> B <--> C, so to get from A to C, you would 
	 * have to traverse A -> B -> C
	 */
	private List<Module> adjacent;
	
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
		//The id will be set once the module gets placed in a spaceship
		this.standardMass = standardMass;
		adjacent = new ArrayList<Module>();
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
		adjacent = new ArrayList<Module>();
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
	 * Attaches this module to a spaceship, connected to a list of other modules.
	 * @param ship
	 * @param connections
	 * @return
	 */
	public boolean attatch(Spaceship ship, List<Module> connections) throws ModuleException	{
		
		if((connections == null) || (connections.size() == 0))	{
			
			throw new ModuleException(this);
		}
		
		ship.addModule(this);
		adjacent = connections;
		
		return true;
	}
	
	/**
	 * This method adds a Module as its neighbor.  This means they are "connected".  
	 * @param neighbor An adjacent module.
	 * @return true if addition is successful, false if not.
	 */
	public boolean addAdjacent(Module neighbor)	{
		
		return adjacent.add(neighbor);
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
