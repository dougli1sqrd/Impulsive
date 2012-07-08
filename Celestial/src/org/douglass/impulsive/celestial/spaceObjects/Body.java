package org.douglass.impulsive.celestial.spaceObjects;

import org.douglass.impulsive.celestial.physics.Constants;
import org.douglass.impulsive.celestial.physics.Vector;
import org.douglass.impulsive.celestial.physics.VectorMath;

/**
 * This Class defines the basic Celestial Body.  This would include stars, planets, etc.
 * @author ericd
 *
 */
public class Body implements Simulatable	{
	/**
	 * This is the field that describes the total number of bodies
	 */
	private static long total = 0;
	/**
	 * This is the Body id.
	 */
	private long id;
	/**
	 * The mass of the object, in kg
	 */
	private double mass;
	/**
	 * The radius of the object, in m
	 */
	private double radius;
	/**
	 * This is a triple representing the location of the center of the body in space.
	 * This is x,y,z coordinates, in m.
	 */
	private Vector position;
	/**
	 * This is a triple representing the velocity of the center of the body in space.
	 * This is x,y,z coordinates, in m/s.
	 */
	private Vector velocity;
		
	public Body(double mass, double radius, double x, double y, double z)	{
		
		this.mass = mass;
		this.radius = radius;
		id = total;
		total++;
		position = new Vector(x, y, z);
		velocity = new Vector(0, 0, 0); //set velocity on creation to the 0 vector
	}
	
	public Body(double mass, double radius, Vector position)	{
		
		this.position = position;
		this.mass = mass;
		this.radius = radius;
		velocity = new Vector(0, 0, 0);
		id = total;
		total++;
	}
	
	@Override
	public double localPotential(Vector r)	{
		
		double bot = VectorMath.subtract(r, position).norm();
		double top = Constants.G*mass;
		return top/bot;
	}
	@Override
	public long getId() {
		return id;
	}
	public double getMass() {
		return mass;
	}
	public void setMass(double mass) {
		this.mass = mass;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}

	public void setPosition(Vector position)	{
		
		this.position = position;
	}
	@Override
	public Vector getPosition()	{
		
		return position;
	}
	public void setVelocity(Vector velocity)	{
		
		this.velocity = velocity;
	}
	@Override
	public Vector getVelocity()	{
		
		return velocity;
	}

	@Override
	public Vector getPointing() {
		// TODO Auto-generated method stub
		return new Vector(0, 0, 0);
	}

	@Override
	public Vector getAngularVelocity() {
		// TODO Auto-generated method stub
		return new Vector(0, 0, 0);
	}

}
