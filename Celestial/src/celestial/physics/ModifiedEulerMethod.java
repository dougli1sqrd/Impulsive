package celestial.physics;

import static celestial.simulator.SimConstansts.DELTA_T;
import celestial.simulator.Galaxy;
import celestial.spaceObjects.Simulatable;

public class ModifiedEulerMethod extends Simulator	{

	public ModifiedEulerMethod(Galaxy gal)	{
		
		super(gal);
	}

	@Override
	/**
	 * new velocity = old velocity + Del(Phi(r))*dt
	 * new pos = old pos + new velocity * dt
	 */
	public void calcPosVelocity(Simulatable b) {
		
		Vector delphi = grad(b.getPosition());
		Vector deltav = VectorMath.scaleMultiple(-1, VectorMath.scaleMultiple(DELTA_T, delphi));
		Vector newvelocity = VectorMath.add(b.getVelocity(), deltav);
		
		Vector deltar = VectorMath.scaleMultiple(DELTA_T, newvelocity);
		Vector newposition = VectorMath.add(b.getPosition(), deltar);
		
		b.setPosition(newposition);
		b.setVelocity(newvelocity);
	}

	@Override
	/**
	 * This assumes that the speed of gravity is instant.  So do not use this simulator class 
	 * for large distances.  Probably nothing larger than say a light month.
	 */
	public double potential(Vector r) {
		
		double pot = 0;
		for(Simulatable b : getGalaxy().getSimulatableObjects())	{
			
			pot += b.localPotential(r);
		}
		return -1*pot;
	}
}
