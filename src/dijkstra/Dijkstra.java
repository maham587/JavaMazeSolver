/**

This class provides a static implementation of Dijkstra's algorithm for finding the shortest path between two vertices
in a graph. The algorithm takes a Graph object, a start vertex, and an end vertex as inputs, and returns a ShortestPaths
object that contains the shortest path from the start vertex to the end vertex, as well as other information about the
graph and the algorithm.
*/
package dijkstra;
import java.util.Collection;
import java.util.List;
import maze.*;

public class Dijkstra {
	/**
	 * Implements Dijkstra's algorithm to find the shortest path between two vertices in a graph.
	 * @param graph the graph to search
	 * @param startVertex the starting vertex
	 * @param endVertex the destination vertex
	 * @param processedVertexes an object that tracks which vertices have been processed
	 * @param minDistance an object that tracks the minimum distance to each vertex
	 * @param shortestPaths an object that stores the shortest path and other information
	 * @return a ShortestPaths object that contains the shortest path and other information
	 */
	public static ShortestPaths dijkstra(Graph graph, Vertex startVertex, Vertex endVertex,
			ProcessedVertexes processedVertexes, MinDistance minDistance, ShortestPaths shortestPaths) {

		// Initialization
		List<Vertex> vertexes = graph.getAllVertexes();
		Vertex pivotVertex = startVertex;
		int j;
		int n = graph.getVertexNumber();

		for (Vertex vertex : vertexes) {
			minDistance.addVertexWithMinDistance(vertex, Integer.MAX_VALUE);
		}
		minDistance.updateMinDistance(pivotVertex, 0);
		List<Vertex> successorsList = graph.getSuccessors(pivotVertex);
		
		// Main loop
		for (j = 0; j < n - 1; j++) {
			for (Vertex successorVertex : successorsList) {
				if (!processedVertexes.isProcessed(successorVertex)) {
					if (minDistance.getMinDistanceTo(pivotVertex) + 1 < minDistance.getMinDistanceTo(successorVertex)) {
						minDistance.updateMinDistance(successorVertex, minDistance.getMinDistanceTo(pivotVertex) + 1);
						if (!shortestPaths.isInPath(successorVertex)) {
							shortestPaths.setPrevious(pivotVertex, successorVertex);
						} else {
							shortestPaths.updatePrevious(pivotVertex, successorVertex);
						}
					}
				}
			}
			pivotVertex = minDistance.getNextPivot(processedVertexes);
			successorsList = graph.getSuccessors(pivotVertex);
			processedVertexes.addProcessedVetex(pivotVertex);
		}
		
		// Construct the shortest path and return the result
		return shortestPaths;
	}

	/**
	 * A convenience method that calls the dijkstra method with default implementations of ProcessedVertexes, MinDistance,
	 * and ShortestPaths.
	 * @param graph the graph to search
	 * @param startVertex the starting vertex
	 * @param endVertex the destination vertex
	 * @return a ShortestPaths object that contains the shortest path and other information
	 */
	public static ShortestPaths dijkstra(Graph graph, Vertex startVertex, Vertex endVertex) {
		return dijkstra(graph, startVertex, endVertex, new ProcessedVertexesImpl(), new MinDistanceImpl(),
				new ShortestPathsImpl());
	}

}