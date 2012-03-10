package douglass.games.spaceship.modules.test;

import org.junit.*;
import static org.junit.Assert.*;
import douglass.games.spaceship.core.ModuleException;
import douglass.games.spaceship.core.Propellant;
import douglass.games.spaceship.modules.EngineModule;
import douglass.games.spaceship.modules.FuelStorageModule;

public class EngineModuleTest {
	
	public static double DELTA = .00001;

	private EngineModule engine;
	private FuelStorageModule tank;
	
	@Before
	public void setup() throws ModuleException	{
		
		engine = new EngineModule(2, 2000, 300, Propellant.HYDROGEN, .002);
		tank = new FuelStorageModule(engine.getFuelType(), 3, 300);
		tank.addFuel(1000);
		boolean connected = engine.addAdjacent(tank);
		engine.setThrottle(1.0);
		
		if(!connected)	{
			
			throw new ModuleException(tank);
		}
	}
	
	@Test
	public void applyThrustTest()	{
		
		double impulse = engine.applyThrust(1);
		assertEquals(1971360, impulse, DELTA * impulse);
	}
}
