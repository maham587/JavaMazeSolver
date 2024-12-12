package maze;

import java.io.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dijkstra.*;

/**
 * represents a maze class, a maze is a length*height matrix of mazeBoxes
 * contents all necessary methods and fields to handle an instance of maze
 * 
 * @author maham
 *
 */
public class Maze implements Graph {

	/**
	 * dimensions of the maze : length and height
	 */
	private int length;
	private int heigth;

	/**
	 * the matrix containing all boxes
	 */
	private MazeBox[][] mazeBoxes;

	/**
	 * this a temporary matrix which will be use to store maze when loading. and if
	 * the maze is not modified and everything is right, we will copy it in the real
	 * attributes.
	 */
	private MazeBox[][] tempBoxes;

	/**
	 * the dimensions of the maze are fixed these two static value represent
	 * respectively the high
	 */
	public final static int lENGTH_MAXVALUE = 15;
	public final static int HEIGHT_MAXVALUE = 15;

	/**
	 * creates an instance of maze using a given length and height.
	 * 
	 * @param length
	 * @param heigth
	 */

	public Maze(int length, int heigth) {
		this.length = length;
		this.heigth = heigth;
		this.mazeBoxes = new MazeBox[heigth][length];
	}

	/**
	 * 
	 * @return the matrix containing all boxes
	 */
	public MazeBox[][] getMazeBoxes() {
		return mazeBoxes;
	}

	/**
	 * 
	 * @param mazeBoxes
	 */
	public void setMazeBoxes(MazeBox[][] mazeBoxes) {
		this.mazeBoxes = mazeBoxes;
	}

	/**
	 * 
	 * @return the length of the maze
	 */
	public int getLength() {
		return length;
	}

	/**
	 * 
	 * @return the height of the maze
	 */
	public int getHeigth() {
		return heigth;
	}

	/**
	 * @see class Graph
	 */
	@Override
	public int getVertexNumber() {

		return this.heigth * this.length;
	}

	/**
	 * @see class Graph the super class but this function is not useful here
	 */
	@Override
	public int getEdgeNumber() {

		return 0;
	}

	/**
	 * @see Graph class : the super class
	 * @return List<Vertex> of vertexes of the maze
	 */
	@Override
	public List<Vertex> getAllVertexes() {

		List<Vertex> vertexes = new ArrayList<Vertex>();
		for (int i = 0; i < heigth; i++) {
			for (int j = 0; j < length; j++) {
				vertexes.add(this.mazeBoxes[i][j]);
			}
		}
		return vertexes;

	}

	/**
	 * @see Graph class : the super class
	 * @return a list of all successor vertexes of a given vertex
	 * @return an empty list if the given vertex is a wall
	 * @param vertex : for the vertex we search it successors
	 * 
	 */
	@Override
	public List<Vertex> getSuccessors(Vertex vertex) {

		List<Vertex> vertexes = new ArrayList<>();

		/**
		 * getting the coordinates of the given vertex
		 */
		MazeBox box = (MazeBox) vertex;
		int i, j;
		i = box.getX();
		j = box.getY();

		if (box.isEmpty() || box.isArrival() || box.isDeparture()) {
			/**
			 * the coordinates of successors varies with the parity of the line of the given
			 * vertex so we have to deal with both cases
			 * 
			 * @see README.md for more details
			 */
			if (i % 2 == 0) {
				/***
				 * adding directly the six successors, but each successor will be processed by
				 * the @see addSuccessor(int i, int j) method before adding it in the list
				 */
				addSuccessor(vertexes, i, j + 1);
				addSuccessor(vertexes, i, j - 1);
				addSuccessor(vertexes, i + 1, j);
				addSuccessor(vertexes, i + 1, j - 1);
				addSuccessor(vertexes, i - 1, j);
				addSuccessor(vertexes, i - 1, j - 1);

			}

			else {
				/***
				 * adding directly the six successors, but each successor will be processed by
				 * the @see addSuccessor(int i, int j) method before adding it in the list
				 */
				addSuccessor(vertexes, i, j + 1);
				addSuccessor(vertexes, i, j - 1);
				addSuccessor(vertexes, i + 1, j);
				addSuccessor(vertexes, i + 1, j + 1);
				addSuccessor(vertexes, i - 1, j);
				addSuccessor(vertexes, i - 1, j + 1);

			}
		}
		return vertexes;

	}

	/***
	 * 
	 * @param list : a given list
	 * @param i    : a given i
	 * @param j    : a given j checks if the box at coordinates i, j is well inside
	 *             the maze and then checks if the corresponding box is not wall and
	 *             then finally add the box in the list
	 */
	public void addSuccessor(List<Vertex> list, int i, int j) {
		if (i >= 0 && j >= 0 && i < this.heigth && j < this.length) {
			MazeBox box = this.mazeBoxes[i][j];
			if (!box.isWall()) {
				list.add(box);
			}

		}

	}

	/***
	 * @see : Graph class : the super class
	 * @return : the distance between two neighbors vertex but here, the distance
	 *         between two neighbors is 1
	 */
	@Override
	public int getDistance(Vertex src, Vertex dst) {
		return 1;
	}

	/**
	 * @see : Graph.java
	 * @return : the previous vertexes in oriented graph not useful in this class
	 */
	@Override
	public Vertex[] getPrevious() {

		return null;
	}

	/**
	 * @see : Graph class : the super class
	 * @return : the next vertexes in oriented graph not useful in this class
	 */
	@Override
	public Vertex[] getNext() {

		return null;
	}
	/***
	 * gets the Departure Box in the current maze
	 * @return the Departure box if found else returns null
	 */
	public MazeBox getDepartureBox() {
		
		for (int i = 0; i < heigth; i++) {
			for (int j = 0; j < length; j++) {
				if (mazeBoxes[i][j].isDeparture()) {
					return mazeBoxes[i][j];
				}
			}
		}
		return null;
	}
	/***
	 * gets the Arrival Box in the current maze
	 * @return the Arrival Box if found else returns null
	 */
	public MazeBox getArrivalBox() {
		for (int i = 0; i < heigth; i++) {
			for (int j = 0; j < length; j++) {
				if (mazeBoxes[i][j].isArrival()) {
					return mazeBoxes[i][j];
				}
			}
		}
		return null;
	}

	/***
	 * saves maze status in file when user decide to save it the contain of the a
	 * 3*3 maze file can look like : AEE EWE EEA it goes through the whole matrix of
	 * the maze and write "E" for emptyBox "D" for DepartureBox "W" for WallBox and
	 * "A" for ArrivalBox it uses PrintWriter to write in the file
	 * 
	 * @param fileName : is the name given by user to save on the maze
	 */
	public final void saveToTextFile(String fileName) {
		PrintWriter pWriter = null;

		try {
			File file = new File("mazeFiles/", fileName);
			if (!file.exists()) {
				file.createNewFile();
			}

			pWriter = new PrintWriter(file);
			for (int i = 0; i < heigth; i++) {
				for (int j = 0; j < length; j++) {
					pWriter.append(mazeBoxes[i][j].getBoxLabel().charAt(0));

				}
				pWriter.println();
			}

		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			pWriter.close();
		}
	}

	/***
	 * put the the contain of a maze file saved by user in the ArrayList<String>, as
	 * we can not know the the maze size without going through the whole file, we
	 * can not load directly the matrix of the maze. We save then in
	 * ArrayList<String> and we reassure ourselves that the file is not modified and
	 * then we get the the size of the maze and load successfully the maze matrix to
	 * display it on the frame.
	 * 
	 * @see initFromTextFile(String file)
	 * @param file : given by the user
	 * @return the contain of a saved maze as ArrayList<String> each string
	 *         correspond to a line in maze file, for example for the contain such
	 *         below AEE EWE EEA the ArrayList<String> = {"AEE", "EWE", "EEA" }
	 * @throws MazeReadingException : checks if the file is modified
	 * @throws IOException          : if the file is deleted or a problem is
	 *                              encountered while opening the file
	 * @throws MazeReadingException
	 */
	public ArrayList<String> getMazeFileAsStringArrayList(String file) throws MazeReadingException {
		ArrayList<String> stringArrayList = new ArrayList<>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));

			String line = reader.readLine();

			while (line != null) {

				stringArrayList.add(line);

				line = reader.readLine();

			}

		} catch (IOException e) {
			stringArrayList = null;
			// TODO: handle exception
		} catch (Exception e) {

			stringArrayList = null;
			throw new MazeReadingException(e);

		} finally {
			try {
				reader.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

		return stringArrayList;

	}

	/***
	 * opens a maze file previously saved by user and want to open it. uses the
	 * ArrayList<String> from the previous method @see getMazeFileAsStringArrayList(String file).
	 **Then processes the ArrayList<String> to find the size of maze and catches all potential 
	 * exceptions, then keeps the status of the maze in a temporary matrix before finally loads
	 * the current maze matrix.
	 * @param file : is given by user, the name of the maze file user want to open
	 *             it
	 * @throws MazeReadingException : checks if the file is modified
	 * @throws IOException          : if the file is deleted or a problem is
	 *                              encountered while opening the file
	 */

	public void initFromTextFile(String file) throws MazeReadingException {

		ArrayList<String> mazeFileStrings = getMazeFileAsStringArrayList(file);

		
		if (mazeFileStrings != null) {
			
			//the a priori dimensions of the maze 
			int heigth = mazeFileStrings.size();
			int length = mazeFileStrings.get(0).length();
			
			//Ensuring that the dimensions of maze don't overflow
			if (heigth > this.HEIGHT_MAXVALUE || length > this.lENGTH_MAXVALUE) {

				throw new MazeReadingException("the file :  " + file + " had been modified !");
			}

			else {
				
				//Beginning to save the status of the maze in temporary matrix
				tempBoxes = new MazeBox[heigth][length];
				for (int j = 0; j < heigth; j++) {
					String lineString = mazeFileStrings.get(j);
					
					if (lineString.length() != length) {
						throw new MazeReadingException("the file :  " + file + " had been modified !");
					} else {

						for (int i = 0; i < length; i++) {
							addMazeBox(j, i, String.valueOf(lineString.charAt(i)));
						}
					}

				}
				//if any Exception is caught, we update the current maze
				this.heigth = heigth;
				this.length = length;

				this.mazeBoxes = new MazeBox[heigth][length];
				
				for (int i = 0; i < heigth; i++) {
					System.arraycopy(tempBoxes[i], 0, this.mazeBoxes[i], 0, length);
				}

			}
		}

	}
	/***
	 * @param x
	 * @param y
	 * @param label
	 * update a mazeBox at coordinates (x, y) according to it label in the maze file
	 * @throws MazeReadingException 
	 */
	private final void addMazeBox(int x, int y, String label) throws MazeReadingException {
		switch (label) {
		case "E": {
			tempBoxes[x][y] = new EmptyBox(x, y);
			break;

		}
		case "W": {
			tempBoxes[x][y] = new WallBox(x, y);
			break;
		}
		case "D": {
			tempBoxes[x][y] = new DepartureBox(x, y);
			break;
		}
		case "A": {
			tempBoxes[x][y] = new ArrivalBox(x, y);
			break;
		}

		default:
			throw new MazeReadingException("the file has been modified !");
		}
	}

	
	/***
	 * when user want to open a saved maze, this method returns the names of 
	 * all saved maze. They are saved in the directory of the project at "mazeFiles/.."
	 * @return the names of all maze saved as txt files by user but returns null if
	 * there no file saved
	 */
	public String[] getmazeFilesName() {
		
		String[] filesNameStrings = null;
		ArrayList<String> filesName = new ArrayList<String>();
		String directoryPath = "mazeFiles/";
		File directory = new File(directoryPath);
		//getting all files at the directory mazeFiles/ as an array
		File[] files = directory.listFiles();
		
		if (files != null) {
			for (File file : files) {
				if (file.isFile()) {
					filesName.add(file.getName());
				}
			}
			//getting the names of these files
			filesNameStrings = new String[filesName.size()];
			for (int i = 0; i < filesName.size(); i++) {
				filesNameStrings[i] = filesName.get(i);
			}
			return filesNameStrings;
		}
		return null;

	}

}
