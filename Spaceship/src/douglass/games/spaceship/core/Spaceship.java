package douglass.games.spaceship.core;

import java.util.*;
import celestial.physics.Constants;
import celestial.physics.Vector;
import celestial.physics.VectorMath;
import celestial.simulator.Galaxy;
import celestial.spaceObjects.Simulatable;
import douglass.games.spaceship.modules.*;

public class Spaceship implements SignalReceiver, Simulatable	{

	private List<Module> components;
	
	private CommandModule command;
	
	private CommunicationsModule communications;
		
	private List<EngineModule> engines;
	
	private Vector position;
	
	private Vector velocity;
	
	private Vector pointing;
	
	private Vector angularvelocity;
		
	private Galaxy galaxy;
	
	private int crew;
	
	public Spaceship(List<EngineModule> engines, CommandModule command, CommunicationsModule communications)	{
		
		
	}
		
	public double getMass()	{
		
		double mass = 0;
		for(Module m : components)	{
			
			mass += m.getMass();
		}
		return mass;
	}
	
	public void addModule(Module module) {
		
		components.add(module);
	}
	/**
	 * Adds the commmand module to the list of Modules, and it sets the command field to the given command module.
	 * @param command
	 */
	public void setCommand(CommandModule command)	{
		
		this.command = command;
		components.add(command);
	}
	
	/**
	 * sets the communications field to the given communications module. This assumes that the engine is already in the component list.
	 * @param command
	 */
	public void setCommunications(CommunicationsModule communication)	{
		
		this.communications = communication;
	}
	
	/**
	 * adds an engine in engines field.  This assumes that the engine is already in the component list.
	 * @param command
	 */
	public void addEngine(EngineModule engine)	{
		
		engines.add(engine);
	}

	@Override
	public Vector getPosition() {

		return position;
	}

	@Override
	public void setPosition(Vector pos) {

		position = pos;
	}

	@Override
	public Vector getVelocity() {

		return velocity;
	}

	@Override
	public void setVelocity(Vector vel) {

		velocity = vel;
	}

	@Override
	public Vector getPointing() {

		return pointing;
	}
	
	@Override
	public void setPointing(Vector newpoint) {
		
		this.pointing = newpoint;
	}

	@Override
	public Vector getAngularVelocity() {

		return angularvelocity;
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double localPotential(Vector r) {
		
		double bot = VectorMath.subtract(r, position).norm();
		double top = Constants.G*getMass();
		return top/bot;
	}

	@Override
	public void receiveSignal(Signal signal) {
		
		communications.receiveSignal(signal);
	}
	
	public void sendSignal(Signal signal)	{
		
		for(Simulatable s : galaxy.getSimulatableObjects())	{
			
			if(s instanceof SignalReceiver)	{
				//If the simulatable object s is also an instance of a signal receiver, then give it the signal
				SignalReceiver sr = (SignalReceiver)s;
				sr.receiveSignal(signal);
			}
		}
	}
	
	/**
	 * This method should be called in order to accelerate the spaceship.  This works by calling the EngineModule's applyThrust()
	 * method, which returns a momentum.  The mass of the spacship is divided out to obtain the change in speed given by the 
	 * thrust momentum.  We can vectorize the scalar speed by multiplying it by the pointing unit vector, so that the change in
	 * velocity is always pointing in the direction that the spaceship is pointing.  (Thrust always applies straight.)  Lastly, 
	 * the velocity of the spaceship is set to be the vector addition of its current velocity and the thrust velocity.
	 * @param deltat The incremental time step that the thrust will be calculated for.
	 */
	public void engageEngine(double deltat)	{
		
		double dp = 0.0;
		for(EngineModule engine : engines)	{
			
			dp += engine.applyThrust(deltat);
		}
		//dp is a the magnitude of the momentum added from thrust of the enigine.
		double speedchange = dp/getMass();
		Vector addedVelocity = VectorMath.scaleMultiple(speedchange, pointing);
		velocity = VectorMath.add(addedVelocity, velocity);
	}



}
