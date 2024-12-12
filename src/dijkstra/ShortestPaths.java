package dijkstra;

import java.util.ArrayList;
/***
 *This interface represents a ShortestPaths resulting Dijkstra algorithm
 *Contains several methods which will be implemented by others class to handle
 *a ShortestPaths Object.
 * @author maham
 */
public interface ShortestPaths {
	/***
	 * As the shortest path is a directed graph, this function gets the previous of given vertex in the tree
	 * @param v : a given vertex we want to get it previous in the directed tree
	 * @return a Vertex
	 */
	Vertex getPrevious(Vertex v);  
	
	/***
	 * sets the parentVertex as the previous of the shildVertex
	 * @param parentVertex
	 * @param shildVertex
	 */
	void setPrevious(Vertex parentVertex, Vertex shildVertex);
	
	/***
	 * During the course of Dijkstra algorithm, we have to update the parent of the vertex in the shortestPath
	 * tree
	 * @param parentVertex
	 * @param shildVertex
	 * 
	 */
	void updatePrevious(Vertex parentVertex, Vertex shildVertex);
	/***
	 * @param v
	 * checks if a given vertex v is in the shortestPaths resulting in the Dijkstra algorithm
	 * @returns a boolean is the search is positive
	 */
	boolean isInPath(Vertex v);
	/***
	 * gets all the vertexes in the shortestPath from the departure to the arrival
	 * @param Departure
	 * @param Arrival
	 * @return theses vertexes as an ArrayList
	 */
	ArrayList<Vertex> getShortestPathList(Vertex Departure, Vertex Arrival);
	
}
