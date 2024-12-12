package dijkstra;

import java.util.List;

/**
 * The Graph interface represents a graph data structure.
 */
public interface Graph {

	/**
	 * Returns the number of vertices in the graph.
	 *
	 * @return the number of vertices in the graph.
	 */
	int getVertexNumber();

	/**
	 * Returns the number of edges in the graph.
	 *
	 * @return the number of edges in the graph.
	 */
	int getEdgeNumber();

	/**
	 * Returns a list of all vertices in the graph.
	 *
	 * @return a list of all vertices in the graph.
	 */
	List<Vertex> getAllVertexes();

	/**
	 * Returns a list of all successor vertices of the specified vertex in the
	 * graph.
	 *
	 * @param vertex the vertex whose successors are to be returned.
	 * @return a list of all successor vertices of the specified vertex.
	 */
	List<Vertex> getSuccessors(Vertex vertex);

	/**
	 * Returns the distance between the source vertex and the destination vertex.
	 *
	 * @param src the source vertex.
	 * @param dst the destination vertex.
	 * @return the distance between the source vertex and the destination vertex.
	 */
	int getDistance(Vertex src, Vertex dst);

	/**
	 * Returns an array of previous vertices in the graph.
	 *
	 * @return an array of previous vertices in the graph.
	 */
	Vertex[] getPrevious();

	/**
	 * Returns an array of next vertices in the graph.
	 *
	 * @return an array of next vertices in the graph.
	 */
	Vertex[] getNext();

}
