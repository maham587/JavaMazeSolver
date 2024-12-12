package uiMvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import uiButtons.CreateNewMaze;
import uiButtons.Help;
import uiButtons.OpenMazeFile;
import uiButtons.Quit;
import uiButtons.RoundedButton;
import uiButtons.SaveMazeButton;
import uiTextAreaComponents.CurrentMazeNameTextArea;
import uiTextAreaComponents.CustomColor;
import uiTextAreaComponents.MazeSolverStatusTextArea;

/**
 * This class is the main view of a maze application and extends JFrame. It has
 * a constructor that initializes several components including a
 * HexagonGridModel, a HexagonGridPanel, and several UI buttons. The constructor
 * also sets up the JFrame with a title, size, location, and a left and right
 * JPanel. The setRightJpanel() and setLeftJpanel() methods set up the right and
 * left JPanels respectively. The setRightJpanel() method sets up the right
 * JPanel with a GridBagLayout and adds several UI components including a
 * MazeSolverStatusTextArea, CurrentMazeNameTextArea, and various buttons. The
 * setLeftJpanel() method sets up the left JPanel with a BorderLayout and adds a
 * HexagonGridPanel and a SwitchButtons component.
 * 
 * @author maham
 */

public class MainView extends JFrame {

	private final HexagonGridModel hexagonGridModel;
	private final HexagonGridPanel hexagonGridPanel;
	private final SwitchButtons switchButtons;
	private JPanel leftJPanel;
	private JPanel righJPanel;
	private Container mainContainer;

	public MainView() {
		super();

		//initializing the attributes
		hexagonGridModel = new HexagonGridModel(23, this);
		hexagonGridPanel = new HexagonGridPanel(hexagonGridModel);
		switchButtons = new SwitchButtons(hexagonGridModel);
		hexagonGridModel.addObserver(switchButtons);
		hexagonGridPanel.addMouseListener(new HexagonGridController(hexagonGridPanel));

		//sizing the frame
		setResizable(false);
		setTitle("Maze App");
		setBounds(0, 0, 1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//setting the leftJpanel containing the maze
		setLeftJpanel();
		//setting the rightJpanel containing ui buttons and text areas
		setRightJpanel();
		
		
		//displaying ...
		pack();
		setVisible(true);

	}

	public static void main(String[] args) {
		new MainView();
	}
	/**
	 * Sets up the right panel of the application with buttons and status text areas.
	 */
	public void setRightJpanel() {
	    // creating right JPanel
	    righJPanel.setLayout(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();

	    // Set the position of the first component (MazeSolverStatusTextArea) in the GridBagLayout
	    gbc.gridx = 0;
	    gbc.gridy = 0;

	    // Create a new MazeSolverStatusTextArea and add it to the panel
	    MazeSolverStatusTextArea pathStatusTextArea = new MazeSolverStatusTextArea(hexagonGridModel, righJPanel);
	    hexagonGridModel.addObserver(pathStatusTextArea);
	    righJPanel.add(pathStatusTextArea, gbc);

	    // Set the position of the second component (CurrentMazeNameTextArea) in the GridBagLayout
	    gbc.gridy = 1;
	    gbc.insets = new Insets(5, 0, 50, 0);

	    // Create a new CurrentMazeNameTextArea and add it to the panel
	    CurrentMazeNameTextArea currentMazeLabel = new CurrentMazeNameTextArea(hexagonGridModel);
	    hexagonGridModel.addObserver(currentMazeLabel);
	    righJPanel.add(currentMazeLabel, gbc);

	    // Set the position of the third component (CreateNewMaze button) in the GridBagLayout
	    gbc.gridy = 2;
	    gbc.insets = new Insets(5, 0, 5, 0);

	    // Create a new CreateNewMaze button and add it to the panel
	    righJPanel.add(new CreateNewMaze("Create new maze", CustomColor.YELLOW_COLOR, hexagonGridModel, this), gbc);

	    // Set the position of the fourth component (SaveMazeButton button) in the GridBagLayout
	    gbc.gridy = 3;
	    righJPanel.add(new SaveMazeButton("Save maze", CustomColor.TURQUOISE_COLOR, hexagonGridModel, this), gbc);

	    // Set the position of the fifth component (OpenMazeFile button) in the GridBagLayout
	    gbc.gridy = 4;
	    righJPanel.add(new OpenMazeFile("Open a saved maze", CustomColor.BLUE_COLOR, hexagonGridModel, this), gbc);

	    // Set the position of the sixth component (Help button) in the GridBagLayout
	    gbc.gridy = 5;
	    righJPanel.add(new Help("Help", CustomColor.RED_COLOR, this), gbc);

	    // Set the position of the seventh component (Quit button) in the GridBagLayout
	    gbc.gridy = 6;
	    righJPanel.add(new Quit("Quit App", CustomColor.RED_COLOR, null), gbc);

	    // Add the right panel to the main container in the EAST position
	    mainContainer.add(righJPanel, BorderLayout.EAST);
	}

	/**
	 * Sets up the left panel of the application with the hexagon grid and switch buttons.
	 */
	public void setLeftJpanel() {
	    // Set the main container to the content pane
	    mainContainer = getContentPane();

	    // Set the preferred width of the left panel to 75% of the application width
	    int leftPanelWidth = (int) (getWidth() * 0.75);

	    // Create new panels for the left and right sides
	    leftJPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
	    righJPanel = new JPanel();

	 // Set the preferred size of the left panel and right panel
	    leftJPanel.setPreferredSize(new Dimension(leftPanelWidth, getHeight()));
	    righJPanel.setPreferredSize(new Dimension(getWidth() - leftPanelWidth, getHeight()));

	    // Set the layout of the left panel to BorderLayout
	    leftJPanel.setLayout(new BorderLayout());

	    // Set the preferred size of the hexagonGridPanel and switchButtons
	    int hexagonGridModelHeight = (int) getHeight() - 60;
	    hexagonGridPanel.setPreferredSize(new Dimension(leftPanelWidth, hexagonGridModelHeight));
	    switchButtons.setPreferredSize(new Dimension(leftPanelWidth, 50));

	    // Set the background color of the switchButtons and hexagonGridPanel
	    switchButtons.setBackground(Color.WHITE);
	    hexagonGridPanel.setBackground(Color.WHITE);

	    // Set the layout of the left panel to BoxLayout
	    BoxLayout layout = new BoxLayout(leftJPanel, BoxLayout.Y_AXIS);
	    leftJPanel.setLayout(layout);

	    // Set the insets of the left panel
	    Insets insets = leftJPanel.getInsets();
	    insets.top = 0;
	    insets.left = 0;
	    insets.bottom = 0;
	    insets.right = 0;

	    // Add hexagonGridPanel and switchButtons to the left panel
	    leftJPanel.add(hexagonGridPanel);
	    leftJPanel.add(switchButtons);

	    // Add the left panel to the main container at the WEST position
	    mainContainer.add(leftJPanel, BorderLayout.WEST);
	}


	 
	public HexagonGridPanel getHexagonGridPanel() {
		return hexagonGridPanel;
	}

}
