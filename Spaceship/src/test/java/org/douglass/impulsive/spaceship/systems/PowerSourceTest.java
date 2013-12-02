package org.douglass.impulsive.spaceship.systems;

import org.douglass.impulsive.dimensions.Power;
import org.douglass.impulsive.spaceship.damage.Damage;
import org.douglass.impulsive.spaceship.damage.DamageData;
import org.douglass.impulsive.spaceship.damage.DamageType;
import org.douglass.impulsive.spaceship.parts.BasicShipPart;
import org.douglass.impulsive.spaceship.parts.MaterialType;
import org.douglass.impulsive.spaceship.parts.ShipPart;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 12/1/13
 * Time: 6:22 PM
 */
public class PowerSourceTest {

    private PowerSource powerSource;

    private BasicShipPart powerPart;

    private TestSystem system;

    private static final double POWER_RATING = 10000;

    @Before
    public void setup() {
        List<ShipPart> powerParts = new ArrayList<ShipPart>();
        powerPart = new BasicShipPart(MaterialType.STEEL, 20, 5);
        powerParts.add(powerPart);
        powerSource = new PowerSource(new Power(POWER_RATING, "W"), powerParts);
        system = new TestSystem(powerSource, new Power(1000, "W"));
    }

    @Test
    public void getRequiredPowerTest()  {
        assertEquals(Power.ZERO_POWER, powerSource.getRequiredPower());
    }

    @Test
    public void getCurrentInputPower()  {
        assertEquals(Power.ZERO_POWER, powerSource.getCurrentInputPower());
    }

    @Test
    public void powerUpTest()   {
        assertEquals(Power.ZERO_POWER, powerSource.getCurrentPowerOutput());
        powerSource.powerUp();
        assertEquals(new Power(POWER_RATING, "W"), powerSource.getCurrentPowerOutput());
    }

    @Test
    public void powerOffTest()  {
        powerSource.powerUp();
        assertEquals(new Power(POWER_RATING, "W"), powerSource.getCurrentPowerOutput());
        powerSource.powerOff();
        assertEquals(Power.ZERO_POWER, powerSource.getCurrentPowerOutput());
    }

    @Test
    public void drawPowerWhileNotFunctionalTest()   {
        powerSource.powerUp();
        powerPart.damage(new Damage(new DamageData(DamageType.CONCUSSIVE, 50)));
        Power actual = powerSource.drawPower(system, new Power(123, "W"));
        assertEquals(Power.ZERO_POWER, actual);
    }

    @Test
    public void drawMorePowerThanAvailableTest()    {
        powerSource.powerUp();
        Power actual = powerSource.drawPower(system, new Power(POWER_RATING*2, "W"));
        assertEquals(Power.ZERO_POWER, actual);
    }

    @Test
    public void drawPowerTest() {
        powerSource.powerUp();
        Power actual = powerSource.drawPower(system, new Power(200, "W"));
        assertEquals(new Power(200, "W"), actual);
        assertEquals(new Power(POWER_RATING-200, "W"), powerSource.getCurrentPowerOutput());
    }

    @Test
    public void replenishDrawnPowerTest()   {
        powerSource.powerUp();
        powerSource.drawPower(system, new Power(200, "W"));
        powerSource.replenishPower(system);
        Power expectedOutput = new Power(POWER_RATING, "W");
        Power actual = powerSource.getCurrentPowerOutput();
        assertEquals(expectedOutput, actual);
    }

    @Test
    public void replenishNonExistingSystemTest()    {
        powerSource.powerUp();
        powerSource.drawPower(system, new Power(200, "W"));
        TestSystem otherSystem = new TestSystem(powerSource, new Power(500, "W"));
        powerSource.replenishPower(otherSystem);
        assertEquals(new Power(POWER_RATING-200, "W"), powerSource.getCurrentPowerOutput());
    }
}
