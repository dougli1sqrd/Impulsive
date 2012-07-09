package org.douglass.impulsive.spaceship.core;

import org.junit.*;

import org.douglass.impulsive.celestial.physics.Vector;
import static org.junit.Assert.*;

import org.douglass.impulsive.spaceship.core.Signal;

public class SignalTest {
	
	public double EPSILON = .0000001;

	private Signal signal;
	
	@Before
	public void setup()	{
		
		signal = new Signal(new Vector(3), 100, 0, "hello world");
	}
	
	@Test
	public void signalStrengthTest()	{
		
		double strength1 = signal.signalStrength(new Vector(1, 0, 0));
		double strength2 = signal.signalStrength(new Vector(2, 0, 0));
		double strength3 = signal.signalStrength(new Vector(3, 0, 0));
		assertEquals(strength1/strength2, 4.0, EPSILON*4.0);
		assertEquals(strength1/strength3, 9.0, EPSILON*8.0);
	}
}
