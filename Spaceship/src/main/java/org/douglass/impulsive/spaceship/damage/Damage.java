package org.douglass.impulsive.spaceship.damage;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 10/25/13
 * Time: 1:21 AM
 */
public class Damage {

    private Set<DamageData> damageSet;

    public Damage(DamageData... damageMix)  {
        damageSet = new HashSet<DamageData>();
        Collections.addAll(damageSet, damageMix);
    }

    public Set<DamageData> getDamages() {
        return damageSet;
    }
}
