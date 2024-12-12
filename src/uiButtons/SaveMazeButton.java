package uiButtons;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import uiMvc.HexagonGridModel;

/**
 * The SaveMazeButton class extends the RoundedButton class to create a button
 * that allows the user to save a maze.
 */
public final class SaveMazeButton extends RoundedButton implements ActionListener {

	private final HexagonGridModel hexagonGridModel; // the hexagon grid model
	private final Frame frame; // the parent frame of the button

	/**
	 * Constructs a new SaveMazeButton object with the specified label, color,
	 * hexagon grid model, and parent frame.
	 *
	 * @param label            the label text for the button
	 * @param color            the color of the button and border
	 * @param hexagonGridModel the hexagon grid model
	 * @param frame            the parent frame of the button
	 */
	public SaveMazeButton(String label, Color color, HexagonGridModel hexagonGridModel, Frame frame) {
		super(label, color);
		this.hexagonGridModel = hexagonGridModel;
		this.frame = frame;
		addActionListener(this);
	}

	/**
	 * Called when the button is clicked. Saves the maze and displays a success or
	 * failure message.
	 *
	 * @param e the action event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// If the current file name is not "untitled", try to save the maze with the
		// current file name.
		if (hexagonGridModel.getCurrentFileName() != "untitled" && hexagonGridModel.getCurrentFileName().length() != 0 ) {
			try {
				hexagonGridModel.saveMaze(hexagonGridModel.getCurrentFileName() + ".maze");
				JOptionPane.showMessageDialog(frame, "Your changes has been saved successfully!");
				return;
			} catch (Exception e2) {
				// If there was an exception while saving the maze, display an error message.
				JOptionPane.showMessageDialog(frame, "Sorry, we could not save your changes!");
				return;
			}
		}
		// If the current file name is "untitled", prompt the user for a new file name.
		String fileName = JOptionPane.showInputDialog(frame, "Enter the maze name:");
		if (fileName != null && fileName.length() != 0) {
			try {
				hexagonGridModel.saveMaze(fileName + ".maze");
				hexagonGridModel.setCurrentFileName(fileName);
				JOptionPane.showMessageDialog(frame, "Your maze file has been saved successfully!");
			} catch (Exception e2) {
				// If there was an exception while saving the maze, display an error message.
				JOptionPane.showMessageDialog(frame, "Sorry, we could not save the file!");
			}
		}
	}
}
