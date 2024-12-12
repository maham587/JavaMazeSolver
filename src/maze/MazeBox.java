package maze;
import dijkstra.*;
/**
 * represents an mazeBox implementing a Vertex interface 
 * a mazeBox can be empty or wall
 * an Empty mazeBox can declared as a departure or the arrival of the maze
 * this class will be an abstract and the super class of the different types of MazeBox
 * @author Mahamadou TOGOLA
 */
public abstract class MazeBox implements Vertex {
	
	private final int x;
	private final int y;
 
	 /**
	  * A mazeBox is created by two coordinates 
	  * @param x 
	  * @param y
	  */
	
	public MazeBox(int x, int y) {
		this.x = x;
		this.y = y;
		 
	
	}

	public int getX() {
		return x;
	}
	
 

	 
	
	public int getY() {
		return y;
	}
	
 
	
	/**
	 * checks if this mazeBox is empty
	 * returns true in EmptyBox class and false in others classes
	 * @return boolean
	 */
	public abstract boolean isEmpty();
	/**
	 * checks if this mazeBox is an departure box
	 * returns true in DepartureBox class and false in others classes
	 * @return boolean
	 */
	public abstract boolean isDeparture();
	/**
	 * checks if this mazeBox is an arrival box
	 * returns true in ArrivalBox class and false in others classes
	 * @return boolean
	 */
	public abstract boolean isArrival();
	/**
	 * checks if this mazeBox is a wall box
	 * returns true in WallBox class and false in others classes
	 * @return boolean
	 */
	public abstract boolean isWall();

	
	/**
	 * @return a string as label of this mazeBox type 
	 * for example "E" for empty
	 */
	public abstract String getBoxLabel();
	 
	
	 
	
	 
	 
	 
	
}
