package douglass.games.spaceship.core;

public enum Direction {

	NORTH,
	SOUTH,
	EAST,
	WEST,
	TOP,
	BOTTOM;
	
	private Direction opposite;
	
	public Direction opposite()	{
		return opposite;
	}
	
	static	{
		
		NORTH.opposite = SOUTH;
		SOUTH.opposite = NORTH;
		EAST.opposite = WEST;
		WEST.opposite = EAST;
		TOP.opposite = BOTTOM;
		BOTTOM.opposite = TOP;
	}
}
