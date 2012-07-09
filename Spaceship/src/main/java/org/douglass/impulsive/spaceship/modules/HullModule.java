package org.douglass.impulsive.spaceship.modules;

public class HullModule extends ExternalModule	{

	/**
	 * Density of the hull plating. kg/m^3. This is effectively increased by the amount of armor the Hull provides
	 */
	public static final double HULL_DENSITY = 6500;
	
	private double armor;
	/**
	 * Constructor for Hull module.  The value for armor should not be less than 1000.  
	 * @param size
	 * @param power
	 * @param armor
	 */
	public HullModule(int size, double power, double armor)	{
		
		super("Hull", size, Math.pow(size, 3)/5 * HULL_DENSITY * armor/1000, power, true);
		this.armor = armor;
	}
	
	public double getArmor()	{
		
		return armor;
	}
}
