package uiMvc;

/***
 * The hexagonGridModel is responsible for creating a JPanel that contains all the hexagons of the maze 
 * in a length*height grid format. As the view changes, a ChangeListener is used to notify this model 
 * and update its attributes accordingly.
 * @author maham
 *
 */

 
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import dijkstra.*;
import maze.ArrivalBox;
import maze.DepartureBox;
import maze.EmptyBox;
import maze.Maze;
import maze.MazeBox;
import maze.MazeReadingException;
import maze.WallBox;
import uiTextAreaComponents.CustomColor;

public final class HexagonGridModel {

	/**
	 * The length of the current HexagonGridModel
	 */
	private int length;
	/**
	 * The height of the current HexagonGridModel
	 */
	private int height;
	/**
	 * @param The radius of the hexagons
	 */

	private final int hexagonRadius;
	/**
	 * The selectedHexagon of the grid, it is actually the hexagon selected by the
	 * user when clicking
	 */
	private Hexagon selectedHexagon = null;
	/**
	 * the two dimensional array containing all haxagons of the maze
	 */
	private Hexagon[][] hexagonGridMatrix;
	/**
	 * The title or the file name of the maze which is currently handling by user
	 */
	private String currentFileName = "untitled";
	/**
	 * The list of all listeners which will notify the model when something changes
	 */
	private final List<ChangeListener> listeners = new ArrayList<ChangeListener>();
	/**
	 * Is the Maze Object @see maze.Maze.java
	 */
	private Maze maze;
	/**
	 * is the MainView, it is an attribute here to allow the class to get others fields in the MainView
	 */
	private MainView mainView;
	/**
	 * checks if a file has been opened successfully
	 */
	private boolean isOpened = true;
 

	/***
	 * creates a HexagonGridModel using the radius of the hexagons. The method
	 * initializes the maze attribute using the initMaze() method. This is done to
	 * ensure that the user does not see an empty maze when they open the app.
	 * Instead, they will see a solved labyrinth, which makes using the maze very
	 * intuitive for the user.
	 * 
	 * After the maze is loaded, the hexagonGridMatrix is initialized. Because we have 
	 * the initials value of length and height.
	 * 
	 * To summarize, this method creates a HexagonGridModel with hexagons of a
	 * specified radius, initializes the maze attribute using the initMaze() method
	 * to provide the user with a solved labyrinth upon opening the app, and
	 * initializes the hexagonGridMatrix after the maze is loaded.
	 * 
	 * @param hexagonRadius
	 */
	public HexagonGridModel(int hexagonRadius, MainView mainView) {
		 
		this.hexagonRadius = hexagonRadius;
		// initialization of the maze
		initMaze();
		// initialiazion of hexagon two dimentional array
		this.hexagonGridMatrix = new Hexagon[maze.getHeigth()][maze.getLength()];
		
		this.mainView = mainView;
	}

	/**
	 * Initializes the maze from data/labyrinthe.maze
	 * 
	 * @see maze.initFromTextFile(String file)
	 * 
	 */
	public void initMaze() {
		try {
			this.maze = new Maze(Maze.lENGTH_MAXVALUE, Maze.HEIGHT_MAXVALUE);
			this.maze.initFromTextFile("data/labyrinthe.maze");
			//initializing current grid length and height
			this.length = maze.getLength();
			this.height = maze.getHeigth();
		} catch (MazeReadingException e) {
			JOptionPane.showMessageDialog(mainView, "Sorry, something went wrong when opening the maze ! ");
		}
	}

	/***
	 * draws the gridPanel knowing it dimensions, 
	 * at each even line the line of hexagons is shifted by a gap
	 * this is taken into account in the function.
	 * Actually, the state of maze is avaluate according the type of the MazeBoxes
	 * the hexagon is drawn.
	 */

	public void paintComponent(Graphics g) {
		 
		//the horizontalGap between each hexagons at the same line
		int horizontalGap = (int) (2 * hexagonRadius * Math.cos(Math.PI / 6));
		//the verticalGap between hexagons of two successive lines
		int verticalGap = (int) (hexagonRadius + hexagonRadius * Math.sin(Math.PI / 6));
		int y = 40;
		for (int j = 0; j < height; j++) {
			int x = 50;
			if (j % 2 != 0) {
				x = (int) (x + hexagonRadius * Math.cos(Math.PI / 6));
			}

			for (int i = 0; i < length; i++) {

				if (maze.getMazeBoxes()[j][i].isEmpty()) {
					this.hexagonGridMatrix[j][i] = new Hexagon(new Point(x, y), hexagonRadius, null, i, j);
					this.hexagonGridMatrix[j][i].drawHexagon(g);
				} else if (maze.getMazeBoxes()[j][i].isWall()) {
					this.hexagonGridMatrix[j][i] = new Hexagon(new Point(x, y), hexagonRadius, CustomColor.WALL_COLOR, i,
							j);
					this.hexagonGridMatrix[j][i].drawHexagon(g);
				} else if (maze.getMazeBoxes()[j][i].isDeparture()) {
					this.hexagonGridMatrix[j][i] = new Hexagon(new Point(x, y), hexagonRadius,
							CustomColor.DEPARTURE_COLOR, i, j);
					this.hexagonGridMatrix[j][i].drawHexagon(g);
				} else {
					this.hexagonGridMatrix[j][i] = new Hexagon(new Point(x, y), hexagonRadius, CustomColor.ARRIVAL_COLOR,
							i, j);
					this.hexagonGridMatrix[j][i].drawHexagon(g);
				}
				x = x + horizontalGap;
			}
			y = y + verticalGap;
		}

		drawShortestPath(g);

	}
	/**
	 * When user want to change the state of a given hexagon, he clicks on the GridPanel, and the Controller
	 * notify the GridPanel by giving it the coordinates of the mouse.
	 * With these coordinates, the function can successfully find the hexagon which has been selected. 
	 * If it found a the hexagon selected, setSelectedHexagon(Hexagon hexagon)is called to update the selectedHexagon
	 * @param x 
	 * @param y
	 */
	public void setPolygonSelected(int x, int y) {
		Hexagon selectedHexagon = null;
		for (Hexagon[] hexagons : hexagonGridMatrix) {
			for (Hexagon hexagon : hexagons) {
				if (hexagon.getPolygon().contains(new Point(x, y))) {
					selectedHexagon = hexagon;
					setSelectedHexagon(selectedHexagon);
					playSound();
					break;
				}
				;
			}

		}
	}

	/**
	 * updates the selected hexagon and notifies ChangeListeners @see SwitchButtons to
	 * update the view
	 * @param hexagon which has been selected by user
	 */
	public void setSelectedHexagon(Hexagon hexagon) {
		if (hexagon != null) {
			this.selectedHexagon = hexagon;
			 
			stateChanged();
		}

	}
	/**
	 * adds Listeners to this model 
	 * @param listener
	 */
	public void addObserver(ChangeListener listener) {
		this.listeners.add(listener);
	}
	/**
	 * When the state of this model is changed, notifies all ChangeListener to update the View 
	 */
	public void stateChanged() {
		ChangeEvent evt = new ChangeEvent(this);
		for (ChangeListener listener : listeners) {
			listener.stateChanged(evt);
		}

	}

	/**
	 * if user wants to update a current hexagon type to empty, this function will be called by ChangeListeners
	 */
	public void upateHexagonToEmpty() {
		
		if (selectedHexagon != null) {
			int j = selectedHexagon.getI();
			int i = selectedHexagon.getJ();
			maze.getMazeBoxes()[i][j] = new EmptyBox(i, j);
			this.mainView.getHexagonGridPanel().repaint();
		}
	}
	/**
	 * if user wants to update a current hexagon type to wall, this function will be called by ChangeListeners
	 */
	public void updateHexagonToWall() {
		if (selectedHexagon != null) {
			int j = selectedHexagon.getI();
			int i = selectedHexagon.getJ();
			maze.getMazeBoxes()[i][j] = new WallBox(i, j);
			this.mainView.getHexagonGridPanel().repaint();
		}
	}
	/**
	 * if user wants to update a current hexagon type to arrival, this function will be called by ChangeListeners
	 */
	public final void updateHexagonToArrival() {
		if (selectedHexagon != null) {
			int j = selectedHexagon.getI();
			int i = selectedHexagon.getJ();
			if (maze.getArrivalBox() != null) {
				int x = maze.getArrivalBox().getX();
				int y = maze.getArrivalBox().getY();
				maze.getMazeBoxes()[x][y] = new EmptyBox(x, y);
			}
			maze.getMazeBoxes()[i][j] = new ArrivalBox(i, j);
			this.mainView.getHexagonGridPanel().repaint();
		}
	}
	/**
	 * if user wants to update a current hexagon type to arrival, this function will be called by ChangeListeners
	 */
	public final void updateHexagonToDeparture() {
		if (selectedHexagon != null) {
			int j = selectedHexagon.getI();
			int i = selectedHexagon.getJ();
			if (maze.getDepartureBox() != null) {
				int x = maze.getDepartureBox().getX();
				int y = maze.getDepartureBox().getY();
				maze.getMazeBoxes()[x][y] = new EmptyBox(x, y);
			}
			maze.getMazeBoxes()[i][j] = new DepartureBox(i, j);
			this.mainView.getHexagonGridPanel().repaint();
		}
	}
	
	
	/**
	 * checks the shortestPath of the current maze, by cally classes @see dijkstra.Dijkstra.java 
	 * @see ShortestPathsImpl.java. And put all the corresponding hexagons belonging to the shortest path
	 * in a Hexagon List.
	 * @return the List containing all hexagons belonging to the shortest path. 
	 */
	private final ArrayList<Hexagon> getShortestPath() {
		
		ArrayList<Hexagon> hexagons = new ArrayList<>();
		ArrayList<Vertex> vertexes = null;
		Vertex startVertex = maze.getDepartureBox();
		Vertex endVertex = maze.getArrivalBox();
		ShortestPathsImpl shortestPaths = (ShortestPathsImpl) Dijkstra.dijkstra(this.maze, startVertex, endVertex);
		
		if (shortestPaths != null) {
			vertexes = shortestPaths.getShortestPathList(startVertex, endVertex);
		}

		if (vertexes != null) {
			for (Vertex vertex : vertexes) {
				MazeBox box = (MazeBox) vertex;
				hexagons.add(hexagonGridMatrix[box.getX()][box.getY()]);
			}
		} else {
			 
			hexagons = null;
		}
		return hexagons;
	}
	/**
	 * draws the shortest path in the current maze using the hexagons given by @see getShortestPath()
	 * @param g
	 */
	private void drawShortestPath(Graphics g) {

		if (maze.getArrivalBox() != null && maze.getDepartureBox() != null) {
			//getting the hexagons belonging to the the shortest path
			ArrayList<Hexagon> pathHexagons = getShortestPath();
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(CustomColor.YELLOW_COLOR);
			//g2.getStroke(new Stroke(5));
			
			if (pathHexagons != null) {
				for (int i = 0; i < pathHexagons.size() - 1; i++) {
					Point p1 = pathHexagons.get(i).getCenter();
					Point p2 = pathHexagons.get(i + 1).getCenter();

					g2.drawLine(p1.x, p1.y, p2.x, p2.y);

				}

			}

		}

	}
	/**
	 * is called when user want to save the current maze, actually, all files will be saved 
	 * in "mazeFiles/". In this case, the files will be safe.  
	 * @param directory
	 */
	public void saveMaze(String directory) {

		this.maze.saveToTextFile(directory);

	}

	/**
	 * open a maze saved file using a name given by user. All the attributes of current maze
	 * will be update in this case.
	 * @param name
	 */
	public void OpenMazeFile(String name) {
		try {
			//opening the file
			this.maze.initFromTextFile("mazeFiles/" + name);
			
			//updating attribute and fields
			this.length = maze.getLength();
			this.height = maze.getHeigth();
			this.selectedHexagon = null;
			this.hexagonGridMatrix = new Hexagon[maze.getHeigth()][maze.getLength()];
			//repaintting ...
			mainView.getHexagonGridPanel().repaint();
		} catch (MazeReadingException e) {
			isOpened = false;
			JOptionPane.showMessageDialog(mainView, "Sorry, the file had been modified, we can't handle it !");
			
		}

		catch (Exception e) {
			isOpened = false;
			e.printStackTrace();
			JOptionPane.showMessageDialog(mainView, "Sorry, something went wrong when opening the file !");
			 
		}

	}
	/**
	 * creates a new maze by the length and height given by user. In this case too, all the attributes will be 
	 * updated.
	 * @param length
	 * @param height
	 */
	public void initEmptyMaze(int length, int height) {
		this.maze = new Maze(length, height);
		this.length = length;
		this.height = height;
		this.currentFileName = "untitled";
		selectedHexagon = null;

		for (int j = 0; j < height; j++) {

			for (int i = 0; i < length; i++) {
				maze.getMazeBoxes()[j][i] = new EmptyBox(j, i);
			}
		}
		this.hexagonGridMatrix = new Hexagon[height][length];
		this.mainView.getHexagonGridPanel().repaint();
		stateChanged();

	}
	
	/**
	 * 
	 * @return the maze of the current model
	 */
	public Maze getMaze() {
		return maze;
	}
	
	/**
	 * 
	 * @return the name of the current maze name
	 */
	public String getCurrentFileName() {
		return currentFileName;
	}
	/**
	 * update the name of the current maze
	 * @param currentFileName
	 */
	public void setCurrentFileName(String currentFileName) {
		this.currentFileName = currentFileName;
		stateChanged();
	}
	
	/**
	 * 
	 * @return a string describing the maze status
	 */
	public String getShortestPathStatus() {
		if (maze.getArrivalBox() == null || maze.getDepartureBox() == null) {
			return "SELECT ARRIVAL AND DEPARTURE BOX";
		} else if (getShortestPath() == null) {
			return "NO SHORTEST PATH FOUND !";
		}

		else {
			return "A SHORTEST PATH HAS BEEN FOUND";
		}
	}
	
	
	
	/**
	 * plays a sound when a hexagon is clicked
	 */
	public void playSound() {
		try {
			// Load the sound file
			File soundFile = new File("music/sound.wav");
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);

			// Get a clip from the audio input stream
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);

			// Play the clip
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean getIsopened() {
		return isOpened;
	}
	
	public void setIsopened(boolean b) {
		isOpened = b;
	}

}
