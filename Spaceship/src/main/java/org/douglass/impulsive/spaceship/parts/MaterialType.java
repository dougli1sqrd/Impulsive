package org.douglass.impulsive.spaceship.parts;

import org.douglass.impulsive.spaceship.damage.Damage;
import org.douglass.impulsive.spaceship.damage.DamageData;
import org.douglass.impulsive.spaceship.damage.DamageType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 10/29/13
 * Time: 9:58 PM
 */
public enum MaterialType implements Material {

    IRON(0.5, 0.3, 0.8, 0.6),
    STEEL(0.4, 0.5, 0.5, 0.6),
    REINFORCED_STEEL(0.3, 0.6, 0.1, 0.5),
    ALUMINUM(1.0, 0.6, 0.8, 0.6),
    CARBON_FIBER(0.6, 1.1, 0.4, 0.3),
    NANOTUBE_ENFORCED_ALUMINUM(0.4, 0.9, 0.2, 0.2),
    COPPER(2, 1, 1, 1),
    GOLD(2.5, 1, 1, 1.3),
    PLASTIC(1, 1, 1, 1);

    private Map<DamageType, Double> damageTypeMultipliers;

    private MaterialType(double concussiveMultiplier, double directedBeamMultiplier, double explosiveMultiplier, double radiationMultiplier) {
        damageTypeMultipliers = new HashMap<DamageType, Double>();
        damageTypeMultipliers.put(DamageType.CONCUSSIVE, concussiveMultiplier);
        damageTypeMultipliers.put(DamageType.DIRECTED_BEAM, directedBeamMultiplier);
        damageTypeMultipliers.put(DamageType.EXPLOSIVE, explosiveMultiplier);
        damageTypeMultipliers.put(DamageType.RADIATION, radiationMultiplier);
    }

    @Override
    public int resolveDamage(Damage hit) {
        int damage = 0;
        for(DamageData d : hit.getDamages())    {
            damage += damageTypeMultipliers.get(d.type) * d.amount;
        }
        return damage;
    }
}
