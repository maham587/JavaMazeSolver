package dijkstra;

import java.util.HashSet;

public interface ProcessedVertexes {
	public boolean isProcessed(Vertex v);
	public void addProcessedVetex(Vertex v);
	public  HashSet<Vertex> getProcessedVertexes();
	public int getProcessedVertexesNumber();
	 
}
