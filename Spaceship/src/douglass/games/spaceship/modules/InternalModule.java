package douglass.games.spaceship.modules;

import douglass.games.spaceship.core.Module;

public abstract class InternalModule extends Module	{

	/**
	 * This is the number of crew required to run the internal module.  More than one person can run a Module at a time. 
	 * Can be zero.
	 */
	private final int crew;
	
	public InternalModule(String name, int size, double standardMass, double power, int crew, boolean required) {
		super(name, size, standardMass, power, required);
		this.crew = crew;
	}
	
	public int getCrew()	{
		
		return crew;
	}
	
}
