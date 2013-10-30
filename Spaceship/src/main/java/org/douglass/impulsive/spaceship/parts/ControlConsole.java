package org.douglass.impulsive.spaceship.parts;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 10/29/13
 * Time: 11:36 PM
 */
public class ControlConsole extends BasicShipPart {

    private static final int STRUCTURAL_INTEGRITY = 18;

    private static final int FUNCTIONAL_THRESHOLD = 6;

    public ControlConsole() {
        super(MaterialType.PLASTIC, STRUCTURAL_INTEGRITY, FUNCTIONAL_THRESHOLD);
    }
}
