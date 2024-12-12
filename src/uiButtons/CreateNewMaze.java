package uiButtons;

/***
 * extends RoundedButton @see RoundedButton.java 
 * When user click on it to have a new and empty maze, 
 * it shows a diaolg to get the size the maze user want to create
 * It actually displays on the frame the Custum dialog MazeSizeInputDialog @see MazeSizeInputDialog.java
 * to get the new height and lenght of the maze.
 * Then notifies HexagonGridModel to update its attributes 
 */

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import uiMvc.HexagonGridModel;
import uiTextAreaComponents.MazeSizeInputDialog;

public final class CreateNewMaze extends RoundedButton implements ActionListener {
	 
	/**
	 * the hexagonGridModel
	 */
	private final HexagonGridModel hexagonGridModel;
	/**
	 * the maze length given by user
	 */
	private int length;
	/**
	 * the maze height given by user
	 */
	private int height;
	/**
	 * The main frame for displaying the dialog
	 */
	private final Frame frame;
	
	
	/**
	 * creates a the Button using the params above
	 * @param Label
	 * @param color : the color of the button
	 * @param hexagonGridModel
	 * @param frame
	 */
	public CreateNewMaze(String Label, Color color, HexagonGridModel hexagonGridModel, Frame frame) {

		super(Label, color);
		this.hexagonGridModel = hexagonGridModel;
		this.frame = frame;
		addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//opening custom dialog for user
		MazeSizeInputDialog dialog = new MazeSizeInputDialog(frame);
		//getting size
		length = dialog.getLenght();
		height = dialog.getHeight();
		//checking if sizes are valid
		if (length != -1 && height != -1) {
			this.hexagonGridModel.initEmptyMaze(length, height);
		} else {
			//
			JOptionPane.showMessageDialog(frame, "Please, give the lenght and the height of your new maze !");
			//actionPerformed(e);
		}

	}

}
