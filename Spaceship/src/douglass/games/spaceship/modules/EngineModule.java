package douglass.games.spaceship.modules;

import java.util.List;

import douglass.games.spaceship.core.Direction;
import douglass.games.spaceship.core.Flammable;
import douglass.games.spaceship.core.Module;
import douglass.games.spaceship.core.Propellant;

public class EngineModule extends Module implements Flammable	{

		
	private Propellant fuel;
	
	private FuelStorageModule fueltank;
		
	/**
	 * This is the maximum fuel consumption (burn rate) allowed for this engine.  This is a calculated value, determined by
	 * the density of the propellant during reaction, the area through which it is passing, and the exhaust velocity.
	 */
	private final double MAXCONSUMPTIONRATE;
	
	/**
	 * This is the percent of the maximum consumption rate that is being utilized currently.  Expressed as a fraction.
	 */
	private double throttle;
	
	
	public EngineModule(int size, double standardMass, double power, Propellant fueltype, double nozzlearea) {
		
		super("Engine", size, standardMass, power, true);
		
		MAXCONSUMPTIONRATE = fueltype.getDensity() * fueltype.getExhaustVelocity() * nozzlearea;
		this.fuel = fueltype;
		
	}
	
	public void setThrottle(double percent)	{
		
		throttle = percent;
	}
	
	public double getThrottle()	{
		
		return throttle;
	}
	
	/**
	 * Engine Modules are only "adjecent" to one other kind of module, namely the <code>FuelStorageModule</code> that feeds to
	 * this <code>EngineModule</code>.  When adding a module to an engine module, it can only be a <code>FuelStorateModule</code>.
	 * In addition, the <code>FuelStorageModule</code> must have the same type of propellant as this <code>EngineModule</code>.
	 * If any of these conditions are not met, in addition to any other reason why adding would fail, then this method will 
	 * return false and the fueltank will not be added.  It is probably safest to check.  Otherwise the spacehship could not run. 
	 */
	public boolean addAdjacent(Module fueltank, Direction dir)	{
		
		if(!(fueltank instanceof FuelStorageModule))	{
			
			return false;
		}
		FuelStorageModule realtank = (FuelStorageModule) fueltank;
		if(!realtank.getFuelType().equals(fuel))	{
			
			return false;
		}
		this.fueltank = realtank;
		super.addAdjacent(fueltank, dir);
		return true;
	}
	
	/**
	 * Calculates the fuel usage rate based on the throttle and the Maximum Fuel Consumption rate rated for this engine.
	 * @return current fuel consumption rate.
	 */
	public double fuelRate()	{
		
		return throttle * MAXCONSUMPTIONRATE;
	}
	
	/**
	 * This burns an incremental amount of propellant, causing that much mass to be expelled at the propellants specified exhause velocity
	 * Although momentum is vector, this assumes the direction of the impulse added is the same in which the current momentum is headed.
	 * This method should only be called higher up, and the return value will be used in the calculation of the ship velocity, etc.
	 * @return The increase in momentum to the ship due to expelling propellant. Essentially the impulse.
	 */
	public double applyThrust(double deltat)	{
		
		double thrust = fuelRate() * fuel.getExhaustVelocity();
		//Now we must decrease the amount of fuel in the fuel tank
		double used = fueltank.useFuel(fuelRate(), deltat); //assume that the fuelrate is constant, not necearilly the deltat
		//We can't assume that the full burst of fuel was burned for the full deltat.  (The tank could be almost empty)
		//So, we must calculate the actual thrust time period based on the amount actually used (returned by Propellant.useFuel()
		//and the fuel usage rate (which is constant).
		double deltat_final = used/fuelRate();
		double impulse = thrust * deltat_final;
		return impulse;
	}
	
	public Propellant getFuelType()	{
		
		return fuel;
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

	@Override
	public double getMass() {
		// TODO Auto-generated method stub
		return 0;
	}

}
