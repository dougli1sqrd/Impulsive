package org.douglass.impulsive.spaceship.parts;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 10/29/13
 * Time: 11:15 PM
 */
public class SignalConduit extends BasicShipPart {

    private static final int STRUCTURAL_INTEGRITY = 10;

    private static final int FUNCTIONAL_THRESHOLD = 2;

    public SignalConduit() {
        super(MaterialType.COPPER, STRUCTURAL_INTEGRITY, FUNCTIONAL_THRESHOLD);
    }
}
