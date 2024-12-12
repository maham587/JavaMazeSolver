package maze;
/**
 * This is a sub-class of MazeBox, represents a an Departure box in the maze
 * @author maham
 *
 */
public class DepartureBox extends MazeBox {
	/**
	 * Creates an DepartureBox using the same constructor of the super class
	 * @param x
	 * @param y
	 */
	public DepartureBox(int x, int y) {
		super(x, y);
		
		 
	}

	@Override
	public boolean isEmpty() {
		 
		return false;
	}

	@Override
	public boolean isDeparture() {
		 
		return true;
	}

	@Override
	public boolean isArrival() {
		 
		return false;
	}

	@Override
	public boolean isWall() {
		 
		return false;
	}

	@Override
	public String getBoxLabel() {
		 
		return "D";
	}

	 
	
	

}
