package org.douglass.impulsive.spaceship.parts;

import org.douglass.impulsive.spaceship.damage.Damage;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 10/25/13
 * Time: 1:28 AM
 */
public interface Material {

    int resolveDamage(Damage hit);
}
