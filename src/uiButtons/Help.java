package uiButtons;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
/***
 * This is a class called Help that extends a RoundedButton class and implements the ActionListener interface. 
 * It provides a button that displays a help message when clicked.
 * The constructor of the Help class takes in three parameters: label, color, and frame. 
 * label is the text that appears on the button, color is the color of the button, and frame is the main frame of the application.
 * The actionPerformed method is called when the user clicks on the button. It displays a dialog box that provides helpful information to the user about how to use the hexagon grid in the application.
 * This class provides a simple and elegant way for users to access helpful information about the application.
 * @author maham
 *
 */

public final class Help extends RoundedButton implements ActionListener{
	private Frame mainFrame;
	public Help(String label, Color color, Frame frame) {
		super(label, color);
		this.mainFrame = frame;
		addActionListener(this);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		 
		 // display the help message in a dialog box
        JOptionPane.showMessageDialog(mainFrame, "To customize the appearance of the hexagons, simply select the desired type from the switch button below the hexagon grid.\nEach type corresponds to a unique color. To change the type of a specific hexagon, simply click on it.\r\n"
        		+ "\r\n"
        		+ "If a solution to the maze is found, the shortest path will be drawn directly on the hexagon grid.\r\n"
        		+ "\r\n"
        		+ "To save your maze, simply click the 'Save' button. You can then open the saved maze later by clicking the 'Open' button.");
	}
	

}
