package org.douglass.impulsive.spaceship.rooms;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 11/6/13
 * Time: 6:02 PM
 */
public class RoomElement {

    private RoomElementType type;

    private Map<Direction, RoomElement> adjacentSquares;

    public RoomElement(RoomElementType type) {
        this.type = type;
        adjacentSquares = new HashMap<Direction, RoomElement>();
    }

    public void setAdjacentSquare(Direction direction, RoomElement square)   {
        adjacentSquares.put(direction, square);
    }

    public RoomElement getAdjacentSquare(Direction direction)    {
        return adjacentSquares.get(direction);
    }

    public RoomElementType getType()    {
        return type;
    }
}
