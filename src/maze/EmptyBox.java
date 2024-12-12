package maze;
/**
 * This is a sub-class of MazeBox, represents a an Empty box
 * @author maham
 *
 */
public class EmptyBox extends MazeBox {
	/**
	 * Creates an EmptyBox using the same constructor of the super class
	 * @param x
	 * @param y
	 */
	public EmptyBox(int x, int y) {
		super(x, y);
		 
	}

	@Override
	public boolean isEmpty() {
		 
		return true;
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
	 
		return false;
	}

	@Override
	public String getBoxLabel() {
		 
		return "E";
	}

 

	 

	 
	
	
	
}
