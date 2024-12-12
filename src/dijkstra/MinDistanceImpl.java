package dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 * Implementation of the MinDistance interface.
 * @author maham
 *
 */

public class MinDistanceImpl implements MinDistance {

	// HashMap to store the minimum distance for each vertex
	private final HashMap<Vertex, Integer> minDistanceHashMap;

	 

	/**
	 * Constructor for the MinDistanceImpl class.
	 */
	public MinDistanceImpl() {
		minDistanceHashMap = new HashMap<>();
		 
	}

	@Override
	public HashMap<Vertex, Integer> getMinDistanceHashMap() {
		return minDistanceHashMap;
	}

	@Override
	public Integer getMinDistanceTo(Vertex v) {
		return this.minDistanceHashMap.get(v);
	}

	@Override
	public void addVertexWithMinDistance(Vertex v, Integer distance) {
		this.minDistanceHashMap.put(v, distance);
		 
	}

	@Override
	public void updateMinDistance(Vertex v, Integer distance) {
		// Update the minimum distance for a given vertex
		this.minDistanceHashMap.replace(v, distance);

		 
	}

	@Override
	public Vertex getNextPivot(ProcessedVertexes processedVertexes) {
		// Get the next vertex to be used as a pivot during Dijkstra's algorithm

		Vertex nextPivotVertex = null;

		// Create a new LinkedHashMap to store the sorted vertices
		LinkedHashMap<Vertex, Integer> sortedMap = new LinkedHashMap<>();

		// Create a new ArrayList to store the minimum distances
		ArrayList<Integer> list = new ArrayList<>();

		// Add the minimum distances to the ArrayList
		for (Map.Entry<Vertex, Integer> entry : minDistanceHashMap.entrySet()) {
			list.add(entry.getValue());
		}
		// Sort the ArrayList in ascending order
		Collections.sort(list);

		// Create a new LinkedHashMap to store the vertices sorted by minimum distance
		for (int num : list) {
			for (Map.Entry<Vertex, Integer> entry : minDistanceHashMap.entrySet()) {
				if (entry.getValue().equals(num)) {
					sortedMap.put(entry.getKey(), num);
				}
			}
		} // Iterate through the sorted vertices and return the first unprocessed vertex
		for (Map.Entry<Vertex, Integer> entry : sortedMap.entrySet()) {
			if (!processedVertexes.isProcessed(entry.getKey())) {
				nextPivotVertex = entry.getKey();
				break;
			}
		}

		return nextPivotVertex;
	}

	 

}
