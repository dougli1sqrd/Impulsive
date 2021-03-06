package org.douglass.impulsive.spaceship.rooms;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 11/10/13
 * Time: 3:03 PM
 */
public enum RoomElementType {

    FLOOR('+'),
    WALL('#');

    private char typeChar;

    private RoomElementType(char typeChar)    {
        this.typeChar = typeChar;
    }

    public char typeChar()  {
        return typeChar;
    }
}
