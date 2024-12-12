package uiButtons;

/**
 * This button allows users to open a saved maze file.
 * Extends RoundedButton @see RoundedButton.java, it is it own listener.
 * When user clicks it, it opens a dialog showing to user all saved maze. Then, user have to 
 * choose the file he wants to open. Then, it will notify the model to be updated
 */
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import uiMvc.HexagonGridModel;

public final class OpenMazeFile extends RoundedButton implements ActionListener {
	/**
	 * the hexagonGridModel
	 */
	private HexagonGridModel hexagonGridModel;
	/**
	 * MainView Frame to display dialog
	 */
	private final Frame frame;

	/**
	 * creates the Button using the params below
	 * 
	 * @param Label
	 * @param color
	 * @param hexagonGridModel
	 * @param frame
	 */
	public OpenMazeFile(String Label, Color color, HexagonGridModel hexagonGridModel, Frame frame) {

		super(Label, color);
		this.hexagonGridModel = hexagonGridModel;
		this.frame = frame;
		addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// getting all maze files saved by user
		String[] filesName = hexagonGridModel.getMaze().getmazeFilesName();

		if (filesName != null) {
			
			if(filesName.length == 0) {
				// catching errors
				JOptionPane.showMessageDialog(frame, "Sorry, No maze-files-saved found !");
				return;
			}
			// creating JComboBox<String> to display all saved maze names
			JComboBox<String> optionList = new JComboBox<>(filesName);
			// getting the name selected by user
			int result = JOptionPane.showConfirmDialog(frame, optionList, "Select file name",
					JOptionPane.OK_CANCEL_OPTION);
			// checking if the resuld is valid
			if (result == JOptionPane.OK_OPTION) {
				// notifying the model to be updated...
				String selectedOptionString = (String) optionList.getSelectedItem();
				if (selectedOptionString != null && selectedOptionString.length() != 0) {
					hexagonGridModel.OpenMazeFile(selectedOptionString);
					if(hexagonGridModel.getIsopened()) {
						hexagonGridModel.setCurrentFileName(selectedOptionString.replaceAll(".maze", ""));
					}
					else {
						hexagonGridModel.setIsopened(true);
					}
					
				}

			}
		} else  {
			// catching errors
			JOptionPane.showMessageDialog(frame, "Sorry, something went wrong !");
		}

	}

}
