package dijkstra;

import java.util.ArrayList;
import java.util.HashMap;
 
public class ShortestPathsImpl implements ShortestPaths{

	public HashMap<Vertex, Vertex> pathsHashMap;
	private ArrayList<Vertex> shortestPathList;
	
	public ShortestPathsImpl( ) {
		 
		this.pathsHashMap = new HashMap<Vertex, Vertex>();
		this.shortestPathList = new ArrayList<>();
	}


	@Override
	public Vertex getPrevious(Vertex v) {
		 
		return pathsHashMap.get(v);
	}

	@Override
	public void setPrevious(Vertex parentVertex, Vertex shildVertex) {
		this.pathsHashMap.put(shildVertex, parentVertex);
		
	}

	@Override
	public boolean isInPath(Vertex v) {
		 
		return this.pathsHashMap.containsKey(v);
	}
	
	
	
	@Override
	public ArrayList<Vertex> getShortestPathList(Vertex Departure, Vertex Arrival){
		boolean thereIsPath = true;
		if(!pathsHashMap.isEmpty() &&isInPath(Arrival)&& this != null) {
			shortestPathList.add(Arrival);
			Vertex currentVertex;
			currentVertex = getPrevious(Arrival);
			System.out.println(pathsHashMap.size());
			int n = pathsHashMap.size();
			int counter = 0;
			if(currentVertex != Departure) {
				do {
					
					shortestPathList.add(currentVertex);
					
					currentVertex = getPrevious(currentVertex);
					counter++;
				}while(currentVertex != Departure && counter < n);
			}
			
			if(currentVertex != Departure) {
				return null;
			}
			else {
				shortestPathList.add(Departure);
				return this.shortestPathList;
			}
			
		}
		else {
			return null;
		}
		
	}


	@Override
	public void updatePrevious(Vertex parentVertex, Vertex shildVertex) {
		 this.pathsHashMap.replace(shildVertex, parentVertex);
		
	}
	public boolean isTherePath() {
		return true;
	}
	


	 

	 



	



 
	

}
