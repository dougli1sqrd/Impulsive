package douglass.games.spaceship.core.modules;

import java.util.List;

import douglass.games.spaceship.core.Flammable;
import douglass.games.spaceship.core.Module;
import douglass.games.spaceship.core.Propellant;

public class EngineModule extends Module implements Flammable	{

	
	
	public EngineModule(String name, int size, double standardMass, double power, boolean required) {
		super(name, size, standardMass, power, required);

	}
	
	private Propellant fuel;
	
	private FuelStorageModule fueltank;
	
	/**
	 * This is the amount of propellant consumed per unit time. Units of kg/s
	 */
	private double consumptionRate;
	/**
	 * This burns an incremental amount of propellant, causing that much mass to be expelled at the propellants specified exhause velocity
	 * Although momentum is vector, this assumes the direction of the impulse added is the same in which the current momentum is headed.
	 * @return The increase in momentum to the ship due to expelling propellant. Essentially the impulse.
	 */
	public double applyThrust(double deltat)	{
		
		double thrust = consumptionRate * fuel.getExhaustVelocity();
		double impulse = thrust * deltat;
		//Now we must decrease the amount of fuel in the fuel tank
		fueltank.useFuel(consumptionRate, deltat);
		return impulse;
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
