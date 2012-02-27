package celestial.simulator;

public class SimConstansts {

	/**
	 * When taking the numerical gradient of a location, this is the distance it uses to 
	 * determine how far off to pick the sample points in the 3 directions: 2 for each of 
	 * x, y, z
	 */
	public static double GRAD_EPSILON = 1000;
	/**
	 * One day (24 hours)
	 */
	public static double DELTA_T = 9000;
}
