package douglass.games.spaceship.core.modules;

import java.util.List;

import douglass.games.spaceship.core.Flammable;
import douglass.games.spaceship.core.Propellant;

public class FuelStorageModule extends InternalModule implements Flammable	{

	public FuelStorageModule(String name, int size, double standardMass, double power, boolean required) {
		super(name, size, standardMass, power, required);
		// TODO Auto-generated constructor stub
	}

	private Propellant fuel;
	/**
	 * This is the fraction of full the tank is.  This is 100 if 100% full, and 0 if totally empty.
	 */
	private double fuelpercent;
	/**
	 * This gives the total amount of fuel left in the tank (in kg)
	 * @return amount of fuel left in kg
	 */
	public double fuelAmount()	{
		
		double capacity = fuel.getDensity()*getVolume();
		return fuelpercent * capacity/100.0;
	}
	
	public double getMass()	{
		
		return fuelAmount() + getBaseMass();
	}
	/**
	 * This calculates the full capacity of this fuel storage module.
	 * @return
	 */
	private double fullCapacity()	{
		
		return fuel.getDensity() * getVolume();
	}
	
	/**
	 * This recalculates the current fuel level given a usage rate and a time that it has been consumed
	 * @param rate rate at which the fuel is consumed, (kg/s)
	 * @param deltat time interval for which the fuel was consumed, (s)
	 * @return returns the amount of fuel actually used.
	 */
	public double useFuel(double rate, double deltat)	{
		
		double fullcapacity = fullCapacity(); //mass
		double used = rate * deltat;
		double fractionused = used/fullcapacity*100;
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
		if(fraction + fuelpercent > 100.0)	{
			
			fuelpercent = 100.0;
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
