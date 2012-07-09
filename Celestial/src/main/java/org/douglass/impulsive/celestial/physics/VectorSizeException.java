package org.douglass.impulsive.celestial.physics;

public class VectorSizeException extends RuntimeException	{

	private static final long serialVersionUID = -3497409528134370449L;

	private Vector v1;
	
	private Vector v2;
	
	public VectorSizeException(Vector v1, Vector v2)	{
		
		super();
		this.v1 = v1;
		this.v2 = v2;
	}
	
	public VectorSizeException(Vector v1, Vector v2, String message)	{
		
		super(message);
		this.v1 = v1;
		this.v2 = v2;
	}
	
	public Vector getV1()	{
		
		return v1;
	}
	
	public Vector getV2()	{
		
		return v2;
	}
}
