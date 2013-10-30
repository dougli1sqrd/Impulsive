package org.douglass.impulsive.spaceship.parts;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 10/29/13
 * Time: 10:42 PM
 */
public class PowerConduit extends BasicShipPart {

    private static final int STRUCTURAL_INTEGRITY = 12;

    private static final int FUNCTIONAL_THRESHOLD = 2;

    public PowerConduit() {
        super(MaterialType.GOLD, STRUCTURAL_INTEGRITY, FUNCTIONAL_THRESHOLD);
    }
}
