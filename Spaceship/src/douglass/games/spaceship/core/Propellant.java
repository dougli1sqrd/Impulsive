package douglass.games.spaceship.core;

public enum Propellant {

	HYDROGEN(4440.0, 0.05, 0.07),
	KEROSENE(3459.4, 0.1, 0.81),
	SOLID(2450.0, 0.3, 4.0),
	IONIC(29400.0, 0.03, 3.05),
	URANIUM(60000.0, 0.75, 18.97),
	PLUTONIUM(85000.0, 0.75, 19.74),
	FUSION(1543500.0, 1.5, 1.0),
	ANTIMATTER(100000000.0, .005, 0.001);
	
	/**
	 * This is the velocity of the exhaust that the particular propellant produces.
	 * Units in m/s.
	 */
	private double exhaustVelocity;
	/**
	 * This is the <b>reaction</b> density of the propellant.  As the propellant is reacted and accelerated, this is the density
	 */
	private double rxndensity;
	/**
	 * This is the <b>storage</b> density of the propellant.
	 */
	private double storedensity;
	/**
	 * This represents how good the propellant is.  The grade starts out at 1.0, 
	 * and the velocity increases in proportion to the grade.
	 */
	private double grade;
	
	Propellant(double exvel, double rxndensity, double storedensity)	{
		
		exhaustVelocity = exvel;
		this.rxndensity = rxndensity;
		this.storedensity = storedensity;
		grade = 1.0;
	}
	/**
	 * Multiplies the current grade by a percentage.  The exhaust velocity is then 
	 * multiplied by the new grade.
	 * @param percent Percent that the grade should change by.  50, would reduce
	 * the grade by 50%, and 150 would increase the grade by 50%.
	 */
	public void modifyGrade(double percent)	{
		
		grade *= percent*grade/100.0;
		exhaustVelocity *= grade;
	}
	
	public double getStorageDensity()	{
		
		return storedensity;
	}
	
	public double getExhaustVelocity()	{
		
		return exhaustVelocity;
	}
	
	public double getDensity()	{
		
		return rxndensity;
	}
	
	public static void main(String[] args) {

		//T0 is hydrogen thrust
		//Thrust is density * cross section area * velocity^2
		double T0 = HYDROGEN.getDensity() * 1000 * HYDROGEN.getExhaustVelocity()* HYDROGEN.getExhaustVelocity();
		
		System.out.println("Hydrogen Thrust through 1 m^2 cross section: "+T0);
		
		for(Propellant p : values())	{
			
			double ratio = 1;
			double top = p.getExhaustVelocity() * p.getExhaustVelocity() * p.getDensity() * 1000;
			ratio = top/T0;
			System.out.println(p.name()+": "+ratio+" times Hydrogen thrust.");
		}
	}
}
