package douglass.games.spaceship.modules.test;

import org.junit.*;
import static org.junit.Assert.*;
import douglass.games.spaceship.core.Propellant;
import douglass.games.spaceship.modules.FuelStorageModule;


public class FuelStorageModuleTest {
	
	public static final double DELTA = .0000015;

	private FuelStorageModule tank;
	
	@Before
	public void setup()	{
		
		tank = new FuelStorageModule(Propellant.KEROSENE, 3, 500.0);
	}
	
	@Test
	public void emptyFuelAmountTest()	{
		
		//start with 0
		assertEquals(0, tank.fuelAmount(), DELTA);
	}
	
	@Test
	public void partialFuelAmountTest()	{
		
		//fill the tank up some
		tank.addFuel(500);
		assertEquals(.0028578, tank.getFilledPercent(), DELTA*tank.getFilledPercent());
		assertEquals(500, tank.fuelAmount(), DELTA*tank.fuelAmount());
	}
	
	@Test
	public void fullFuelAmountTest()	{
		
		tank.addFuel(174955); //Add just under full capacity
		assertEquals(0.99997, tank.getFilledPercent(), DELTA * tank.getFilledPercent());
		
		tank.addFuel(50);  //Fill beyond full
		assertEquals(1.0, tank.getFilledPercent(), DELTA * tank.getFilledPercent());
		assertEquals(174960, tank.fuelAmount(), DELTA * tank.fuelAmount());
	}
	
	@Test
	public void useZeroFuelTest()	{
		
		tank.addFuel(100000);
		double used = tank.useFuel(0, 3.0);
		assertEquals(0.0, used, DELTA * used);
	}
	
	@Test
	public void useSomeFuelTest()	{
		
		tank.addFuel(100000);
		double used = tank.useFuel(500, 3.0);
		assertEquals(1500.0, used, DELTA * used);
		assertEquals(100000 - 1500.0, tank.fuelAmount(), DELTA * tank.fuelAmount()); //mak
	}
	
	@Test
	public void useAllFuelTest()	{
		
		tank.addFuel(1500);
		double used = tank.useFuel(100, 16); //"used" 1600 kg of fuel in 11 seconds
		assertEquals(1500, used, DELTA * used);
	}
	
	@Test
	public void getEmptyMassTest()	{
		
		//start with no fuel
		assertEquals(4800.6, tank.getMass(), DELTA * tank.getMass());
	}
	
	@Test
	public void getMassTest()	{
		
		tank.addFuel(500);
		assertEquals(4800.6 + 500, tank.getMass(), DELTA * tank.getMass());
	}
}
