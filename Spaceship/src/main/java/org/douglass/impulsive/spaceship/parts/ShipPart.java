package org.douglass.impulsive.spaceship.parts;

import org.douglass.impulsive.spaceship.damage.Damage;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 10/11/13
 * Time: 12:40 AM
 */
public interface ShipPart {

    boolean isFunctional();

    void repair();

    void damage(Damage damage);

    void connectTo(List<ShipPart> toConnect);

    void connectTo(ShipPart toConnect);

    void disconnect();

    void disconnectFrom(ShipPart p);

    List<ShipPart> listConnectedParts();

    boolean isConnected(ShipPart p);
}
