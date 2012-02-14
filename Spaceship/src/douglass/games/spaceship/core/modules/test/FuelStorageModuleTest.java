package douglass.games.spaceship.core.modules.test;

import org.junit.*;
import douglass.games.spaceship.core.modules.FuelStorageModule;


public class FuelStorageModuleTest {

	private FuelStorageModule tank;
	
	@Before
	public void setup()	{
		
		tank = new FuelStorageModule(3, 500.0);
	}
	
	@Test
	public void fuelAmountTest()	{
		
		
	}
}
