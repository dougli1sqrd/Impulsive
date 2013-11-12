package org.douglass.impulsive.spaceship.rooms;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 11/12/13
 * Time: 1:13 AM
 */
public class ConsoleRoomUI {

    private RectangularRoom room;

    public ConsoleRoomUI(int columns, int rows) {
        room = new RectangularRoom(columns, rows);
    }

    private void run()  {
        System.out.println(room.toString());
    }

    public static void main(String[] args) {
        new ConsoleRoomUI(6, 4).run();
    }
}
