package uiButtons;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
/**
 * This is a class called Quit that extends a RoundedButton class and implements the ActionListener interface. 
 * It provides a button that allows the user to quit the application and displays a confirmation dialog box before quitting.
 * The constructor of the Quit class takes in three parameters: label, color, and frame. 
 * label is the text that appears on the button, color is the color of the button, and frame is the main frame of the application.
 * The actionPerformed method is called when the user clicks on the Quit button. It displays a confirmation dialog box that asks the user if they are sure they want to quit the application. 
 * If the user selects "Yes" in the dialog box, the application is exited using the System.exit() method.
 */


public final  class Quit  extends RoundedButton implements ActionListener{
	private final Frame mainFrame;
	public Quit(String label, Color color, Frame frame) {
		super(label, color);
		this.mainFrame = frame;
		addActionListener(this);
		 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 
		// display a confirmation dialog box
        int confirm = JOptionPane.showConfirmDialog(mainFrame, "Are you sure you want to quit ? Please, save your current maze first !");
        if (confirm == JOptionPane.YES_OPTION) {
            // quit the application
            System.exit(0);
        }
	}

}
