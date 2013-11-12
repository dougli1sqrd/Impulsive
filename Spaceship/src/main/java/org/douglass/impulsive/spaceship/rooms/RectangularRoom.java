package org.douglass.impulsive.spaceship.rooms;

/**
 * Created with IntelliJ IDEA.
 * User: dougli1sqrd
 * Date: 11/4/13
 * Time: 11:10 PM
 */
public class RectangularRoom {

    private RoomElement[][] roomGrid;

    private int columns;

    private int rows;

    public RectangularRoom(int columns, int rows)   {
        this.columns = columns + 2;
        this.rows = rows + 2;
        roomGrid = new RoomElement[this.columns][this.rows];
        createGridSquares();
        wireSquares();
    }

    private void createGridSquares()    {
        for(int i=0; i<columns; i++)  {
            for(int j=0; j<rows; j++) {
                if(isWall(i, j))  {
                    roomGrid[i][j] = new RoomElement(RoomElementType.WALL);
                } else  {
                    roomGrid[i][j] = new RoomElement(RoomElementType.FLOOR);
                }
            }
        }
    }

    private boolean isWall(int i, int j)    {
        return (i==0) || (i==columns-1) || (j==0) || (j==rows-1);
    }

    private void wireSquares()  {
        for(int i=1; i< columns-1; i++) {
            for(int j=1; j< rows-1; j++)  {
                wireAdjacentSquares(i, j);
            }
        }
        wireCorners();
    }

    private void wireCorners()  {
        wireAdjacentSquares(0, 0);
        wireAdjacentSquares(0, rows-1);
        wireAdjacentSquares(columns-1, 0);
        wireAdjacentSquares(columns-1, rows-1);
    }

    private void wireAdjacentSquares(int column, int row)   {
        RoomElement l = roomGrid[column][row];
        for(Direction dir : Direction.values()) {
            if(!isWall(column, row))   {
                RoomElement adjacent = roomGrid[column+dir.getDeltaColumn()][row+dir.getDeltaRow()];
                l.setAdjacentSquare(dir, adjacent);
                adjacent.setAdjacentSquare(dir.opposite(), l);
            }
        }
    }
}
