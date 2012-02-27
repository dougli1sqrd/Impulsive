package douglass.games.spaceship.core.modules;

import celestial.physics.Vector;
import douglass.games.spaceship.core.Signal;
import douglass.games.spaceship.core.Spaceship;

public class CommunicationsModule extends ExternalModule implements SignalReceiver	{

	/**
	 * This is the minimum amount of power a signal must be recieved to be picked up by this communication module
	 */
	private double sensitivity;
	/**
	 * The maximum amount of power output that the Communication module can use to send signals with.
	 */
	private double maxpower;
	/**
	 * The ComChannel is the abstracted out communication channel that the Communication array is on.  These represent
	 * EM frequencies in some respect, however we will just use integers for simplicity.
	 */
	private int comchannel;
	
	private StringBuffer signalbuffer;
	/**
	 * This is the number of characters in a message that can be sent per delta time period.  Note: This is not per unit time,
	 * this is per delta t.  Every time the simulation comes around to the CommunicationsModule, if it is sending data, will send
	 * <code>sendrate</code> number of characters.
	 */
	private int sendrate;
	
	private Spaceship ship;
	
	public CommunicationsModule(String name, int size, double standardMass, double sensitivity, double maxpower) {
		
		super(name, size, standardMass, 125*size, true);
		this.sensitivity = sensitivity;
		this.maxpower = maxpower;
	}
	
	public void setComChannel(int channel)	{
		
		comchannel = channel;
	}
	
	public int getComChannel()	{
		
		return comchannel;
	}
	
	public void sendSignal(String signal)	{
		
		setPowerUsage(getPowerUsage() + maxpower); //Set the power to maximum while we send the signal
		
		Signal out = new Signal(ship.getPosition(), maxpower, comchannel, signal);
		//The sending will occur at the Spaceship level, where it has visibility to the rest of the Galaxy
		ship.sendSignal(out);
		setPowerUsage(getPowerUsage() - maxpower); //Set it back to listening while we listen for signals.
	}

	@Override
	public void receiveSignal(Signal signal) {
		
		//If the signal is powerful enough and the correct channel, then we can add it to the signal buffer.
		double strength = signal.signalStrength(ship.getPosition());
		if((strength >= sensitivity) && (comchannel == signal.getChannel()))	{
			
			signalbuffer.append(signal.getSignalContents());
		}
	}
	
		
}
