package dijkstra;

import java.util.HashMap;
import java.util.HashSet;

public interface MinDistance {
	// Returns the minimum distance to the given vertex, or null if the vertex is not in the map
	Integer getMinDistanceTo(Vertex v);
	
	// Adds a new vertex with the given minimum distance to the map
	void addVertexWithMinDistance(Vertex v, Integer distance);
	
	// Updates the minimum distance for the given vertex, if a smaller distance is found
	void updateMinDistance(Vertex v, Integer distance);
	
	// Returns the next pivot vertex for processing, or null if there are no more vertices to process
	Vertex getNextPivot(ProcessedVertexes processedVertexes);
	
	// Returns the map of vertex-to-minimum-distance pairs
	HashMap<Vertex, Integer> getMinDistanceHashMap();
	
	 
}

