package org.douglass.impulsive.spaceship.parts;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 10/29/13
 * Time: 9:55 PM
 */
public class Door extends BasicShipPart {

    private static final int STRUCTURAL_INTEGRITY = 25;

    private static final int FUNCTIONAL_THRESHOLD = 5;

    private PowerConduit powerIn;

    private SignalConduit signalConduit;

    public Door(Material material) {
        super(material, STRUCTURAL_INTEGRITY, FUNCTIONAL_THRESHOLD);
        connectTo(powerIn);
        connectTo(signalConduit);
    }

    @Override
    public boolean isFunctional() {
        return super.isFunctional() && powerIn.isFunctional() && signalConduit.isFunctional();
    }
}
