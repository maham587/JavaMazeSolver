package maze;
/**
 * This is a sub-class of MazeBox, represents a an Wall box in the maze
 * @author maham
 *
 */
public class WallBox extends MazeBox {
	
	/**
	 * creates a Wall box in the maze using the same constructor of the super class
	 * @param x
	 * @param y
	 */
	public WallBox(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean isDeparture() {
		
		return false;
	}

	@Override
	public boolean isArrival() {
		 
		return false;
	}

	@Override
	public boolean isWall() {
		 
		return true;
	}

	@Override
	public String getBoxLabel() {
		 
		return "W";
	}

	 

}
