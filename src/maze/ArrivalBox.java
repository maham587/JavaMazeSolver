package maze;
/**
 * This is a sub-class of MazeBox, represents a an Arrival box
 * @author maham
 *
 */
public class ArrivalBox extends MazeBox{
	
	/**
	 * creates an ArrivalBox using the same constructor of super-class
	 * @param x
	 * @param y
	 */
	public ArrivalBox(int x, int y) {
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
		 
		return true;
	}

	@Override
	public boolean isWall() {
	 
		return false;
	}

	@Override
	public String getBoxLabel() {
		return "A";
	}

	 

}
