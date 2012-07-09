package org.douglass.impulsive.spaceship.modules.test;

import org.junit.*;
import static org.junit.Assert.*;
import org.douglass.impulsive.spaceship.core.Direction;
import org.douglass.impulsive.spaceship.core.ModuleException;
import org.douglass.impulsive.spaceship.core.Propellant;
import org.douglass.impulsive.spaceship.modules.EngineModule;
import org.douglass.impulsive.spaceship.modules.FuelStorageModule;

public class EngineModuleTest {
	
	public static double DELTA = .00001;

	private EngineModule engine;
	private FuelStorageModule tank;
	
	@Before
	public void setup() throws ModuleException	{
		
		engine = new EngineModule(2, 2000, 300, Propellant.HYDROGEN, .002);
		tank = new FuelStorageModule(engine.getFuelType(), 3, 300);
		tank.addFuel(1000);
		boolean connected = engine.addAdjacent(tank, Direction.NORTH);
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
