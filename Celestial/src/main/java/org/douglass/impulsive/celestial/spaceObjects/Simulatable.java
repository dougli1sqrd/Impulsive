package org.douglass.impulsive.celestial.spaceObjects;

import org.douglass.impulsive.celestial.physics.Vector;

public interface Simulatable {

	public Vector getPosition();
	
	public void setPosition(Vector pos);
	
	public Vector getVelocity();
	
	public void setVelocity(Vector vel);
	
	/**
	 * This method returns the unit vector that always points in the direction the object is facing.
	 * @return A unit vector that points in the direction
	 */
	public Vector getPointing();
	
	public Vector getAngularVelocity();
	
	public long getId();
	
	public double localPotential(Vector r);
}
