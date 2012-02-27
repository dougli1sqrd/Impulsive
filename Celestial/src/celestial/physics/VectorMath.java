package celestial.physics;

public class VectorMath {

	public static Vector scaleMultiple(double scalar, Vector vec)	{
		
		Vector scaled = vec.clone();
		for(int i=0; i<vec.entries(); i++)	{
			
			scaled.setComponent(i, scalar*vec.getComponent(i));
		}
		
		return scaled;
	}
	
	public static Vector add(Vector v1, Vector v2) throws VectorSizeException	{
		
		if(v1.entries() != v2.entries())	{
			
			throw new VectorSizeException(v1, v2, "Cannot add Vectors of two different sizes");
		}
		
		Vector result = new Vector(v1.entries());
		
		for(int i=0; i<v1.entries(); i++)	{
			
			result.setComponent(i, v1.getComponent(i)+v2.getComponent(i));
		}
		return result;
	}
	
	public static Vector subtract(Vector v1, Vector v2) throws VectorSizeException	{
		
		if(v1.entries() != v2.entries())	{
			
			throw new VectorSizeException(v1, v2, "Cannot subtract Vectors of two different sizes");
		}
		
		Vector result = new Vector(v1.entries());
		
		for(int i=0; i<v1.entries(); i++)	{
			
			result.setComponent(i, v1.getComponent(i)-v2.getComponent(i));
		}
		return result;
	}
}
