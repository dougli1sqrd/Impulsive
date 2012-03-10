package douglass.games.spaceship.modules;

import java.util.List;

import douglass.games.spaceship.core.Flammable;
import douglass.games.spaceship.core.Propellant;

public class FuelStorageModule extends InternalModule implements Flammable	{

	public static final double WALL_THICKNESS = 0.003175; //in meters
	public static final double WALL_DENSITY = 6000; // in kg/m^3


	private Propellant fuel;
	/**
	 * This is the fraction of full the tank is.  This is 1 if 100% full, and 0 if totally empty.
	 */
	private double fuelpercent;
	
	public FuelStorageModule(Propellant propellant, int size, double power) {
		
		//A fuel module is for the most part automatic and does not require a crew.
		super("Fuel Tank", size, calculateStaticBaseMass(size), power, 0, true);
		fuel = propellant;
	}
	
	public Propellant getFuelType()	{
		
		return fuel;
	}
	
	/**
	 * This gives the total amount of fuel left in the tank (in kg)
	 * @return amount of fuel left in kg
	 */
	public double fuelAmount()	{
		
		double capacity = fuel.getStorageDensity()*getVolume();
		return fuelpercent * capacity;
	}
	
	public double getFilledPercent()	{
		
		return fuelpercent;
	}
	
	private double calculateBaseMass()	{
		
		return calculateStaticBaseMass(getSize());
	}
	
	private static double calculateStaticBaseMass(int size)	{
		
		return 6*Math.pow(getStaticVolume(size), 2.0/3)*WALL_THICKNESS*WALL_DENSITY;
	}
	
	@Override
	public double getMass()	{
		
		return fuelAmount() + getBaseMass();
	}
	/**
	 * This calculates the full capacity of this fuel storage module. (kg)
	 * @return
	 */
	private double fullCapacity()	{
		
		return fuel.getStorageDensity() * getVolume();
	}
	
	/**
	 * This recalculates the current fuel level given a usage rate and a time that it has been consumed
	 * @param rate rate at which the fuel is consumed, (kg/s)
	 * @param deltat time interval for which the fuel was consumed, (s)
	 * @return returns the amount of fuel actually used (kg).
	 */
	public double useFuel(double rate, double deltat)	{
		
		double fullcapacity = fullCapacity(); //mass
		double used = rate * deltat;
		double fractionused = used/fullcapacity;
		if(fuelpercent - fractionused < 0.0)	{
			
			fractionused = fuelpercent;
			used = fractionused * fullCapacity();
			fuelpercent = 0.0;
		}
		fuelpercent -= fractionused;
		return used;
	}
	/**
	 * This method adds an amount of fuel (in kg) to the fuel tank
	 * @param amount amount of fuel in kg to add
	 */
	public void addFuel(double amount)	{
		
		//first find the fractional change this makes.
		double fraction = amount/fullCapacity();
		if(fraction + fuelpercent > 1.0)	{
			
			fuelpercent = 1.0;
		} else	{
			
			fuelpercent += fraction;
		}
		
	}

	@Override
	public int burn() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean catchFire(List<Flammable> surrounding) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBurning() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
