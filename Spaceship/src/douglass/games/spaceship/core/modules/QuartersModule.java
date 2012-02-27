package douglass.games.spaceship.core.modules;

public class QuartersModule extends InternalModule	{
	
	private int crewsupported;

	public QuartersModule(String name, int size, double standardMass, double power, int crewSupported) {

		super(name, size, standardMass, power, 0, true);
		crewsupported = crewSupported;
	}
	
	public int getCrewSupported()	{
		
		return crewsupported;
	}

}
