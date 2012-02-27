package douglass.games.spaceship.core.modules;

import douglass.games.spaceship.core.Signal;

/**
 * If a class implements the SignalReceiver class, then it automatically is in the set of listeners to receive a Signal.  
 * The implementation of receiving the signal is up to the specific class.  All spaceships will be a SignalReceiver, so that
 * when another spaceship sends a signal, all other SignalReceivers (all other Spaceships) receive the signal and can process them
 * in the appropriate ways.
 * @author Eric Douglass
 *
 */
public interface SignalReceiver {

	/**
	 * Recieves the signal from another source.
	 * @param signal 
	 */
	public void receiveSignal(Signal signal);
}
