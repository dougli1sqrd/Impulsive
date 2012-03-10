package douglass.games.spaceship.modules;

import douglass.games.spaceship.core.Module;

public abstract class ExternalModule extends Module	{

	public ExternalModule(String name, int size, double standardMass, double power, boolean required)	{
		
		super(name, size, standardMass, power, required);
	}
}
