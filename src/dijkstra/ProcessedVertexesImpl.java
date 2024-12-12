/**

*This class implements the ProcessedVertexes interface to represent a set of vertices that have already been processed
*during Dijkstra's algorithm. It uses a HashSet to store the processed vertices and provides methods to add, check and
*retrieve processed vertices from the set.
*/
package dijkstra;
import java.util.HashSet;

public class ProcessedVertexesImpl implements ProcessedVertexes {
	// A HashSet to store the processed vertices
	private HashSet<Vertex> hashSet; 

	/**
	 * Constructs an instance of ProcessedVertexesImpl and initializes the HashSet to an empty set.
	 */
	public ProcessedVertexesImpl() {
		hashSet = new HashSet<>();
	}

	/**
	 * Checks if a given vertex is in the set of processed vertices.
	 * @param v the vertex to check
	 * @return true if the vertex is in the set, false otherwise
	 */
	@Override
	public boolean isProcessed(Vertex v) {
		return hashSet.contains(v);
	}

	/**
	 * Adds a vertex to the set of processed vertices.
	 * @param v the vertex to add
	 */
	@Override
	public void addProcessedVetex(Vertex v) {
		hashSet.add(v);	
	}

	/**
	 * Returns the set of processed vertices.
	 * @return the set of processed vertices
	 */
	@Override
	public HashSet<Vertex> getProcessedVertexes() {
		return this.hashSet;
	}

	/**
	 * Returns the number of processed vertices in the set.
	 * @return the number of processed vertices
	 */
	@Override
	public int getProcessedVertexesNumber() {
		return hashSet.size();
	}
}
