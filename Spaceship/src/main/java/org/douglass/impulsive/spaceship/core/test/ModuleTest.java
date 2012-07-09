package org.douglass.impulsive.spaceship.core.test;

import java.util.*;
import org.junit.*;
import org.douglass.impulsive.spaceship.core.*;
import org.douglass.impulsive.spaceship.modules.*;
import static org.junit.Assert.*;

public class ModuleTest {

	CommandModule command;
	
	@Before
	public void setup()	{
		
		//Connect some Modules together
		command = new CommandModule("Cockpit", 2, 10000, 300);
		QuartersModule quarters = new QuartersModule("Quarters", 1, 10000, 100, 4);
		FuelStorageModule fueltank = new FuelStorageModule(Propellant.IONIC, 5, 450);
		EngineModule engine = new EngineModule(2, 10000, 1000, fueltank.getFuelType(), .02);
		
		engine.addAdjacent(fueltank, Direction.NORTH);
		quarters.addAdjacent(fueltank, Direction.SOUTH);
		command.addAdjacent(quarters, Direction.SOUTH);
	}
	
	@Test
	public void listModulesTest()	{
		
		List<Module> modlist = command.listModules();
		assertEquals(4, modlist.size());
	}
}
