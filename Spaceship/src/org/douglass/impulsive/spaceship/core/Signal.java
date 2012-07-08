package org.douglass.impulsive.spaceship.core;

import org.douglass.impulsive.celestial.physics.Vector;
import org.douglass.impulsive.celestial.physics.VectorMath;
/**
 * This Class describes a pulse of electromagnetic energy, that contains some kind of text information.  Signals should be able to 
 * be sent by Spaceships, and then objects that are SignalReceivers should pick up this Signal.  The only thing the the 
 * receiver can know is the calculated power per unit area, which is a function of the distance between the source and the 
 * receiver; the contents of the signal, and what channel it was on.
 * @author Eric Douglass
 *
 */
public class Signal {

	/**
	 * This is the position of the source of the Signal.  SignalReceivers who receive a signal should not be able to exploit
	 * knowledge of this field.  
	 */
	private Vector source;
	
	private double power;
	
	private int channel;
	
	private String contents;
	
	public Signal(Vector source, double power, int channel, String contents)	{
		
		this.source = source;
		this.power = power;
		this.channel = channel;
		this.contents = contents;
	}
	
	/**
	 * This caluclates the signal strength as a function of the source of the signal and the position at which it was received.
	 * @param currentpos The vector representing the position when the signal 
	 * @return The strenght of the signal. In W/m^2
	 */
	public double signalStrength(Vector currentpos)	{
		
		double distance = VectorMath.subtract(currentpos, source).norm();
		double strength = power/(4*Math.PI*distance*distance);
		return strength;
	}
	
	public int getChannel()	{
		
		return channel;
	}
	
	public String getSignalContents()	{
		
		return contents;
	}
}
