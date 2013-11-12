package org.douglass.impulsive.spaceship.rooms;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 11/9/13
 * Time: 12:42 PM
 */
public enum Direction {

    NORTH(0, 1),
    SOUTH(0, -1),
    EAST(1, 0),
    WEST(-1, 0);

    private int deltaColumn;

    private int deltaRow;

    private Direction opposite;

    private Direction(int deltaColumn, int deltaRow)   {
        this.deltaColumn = deltaColumn;
        this.deltaRow = deltaRow;
        switch (this)   {
            case NORTH: {
                opposite = SOUTH;
                break;
            }
            case SOUTH: {
                opposite = NORTH;
                break;
            }
            case EAST: {
                opposite = WEST;
            }
            case WEST: {
                opposite = EAST;
            }
        }
    }

    public int getDeltaColumn()   {
        return deltaColumn;
    }

    public int getDeltaRow()    {
        return deltaRow;
    }

    public  Direction opposite()    {
        return opposite;
    }
}
