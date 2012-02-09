package douglass.games.spaceship.core;

import java.util.List;

public interface Flammable {

	/**
	 * This method returns a value that represents the amount of damage the fire has done in 
	 * a particular unit of time.
	 * @return amount of damage done due to being on fire in one time unit.
	 */
	public int burn();
	/**
	 * The catch fire method will return true if this Flammable object should catch fire
	 * this turn, so that {@link isBurning()} returns true the next round.
	 * @param surrounding the list of Flammable objects surrounding this Flammable object
	 * @return true if this Flammable object should catch fire, false if not.
	 */
	public boolean catchFire(List<Flammable> surrounding);
	/**
	 * This method returns true if the Flammable object is currently on fire, false if not.
	 * @return
	 */
	public boolean isBurning();
}
