package org.douglass.impulsive.spaceship.damage;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 10/25/13
 * Time: 1:16 AM
 */
public class DamageData {

    public final DamageType type;

    public final int amount;

    public DamageData(DamageType type, int amount)  {
        this.amount = amount;
        this.type = type;
    }
}
