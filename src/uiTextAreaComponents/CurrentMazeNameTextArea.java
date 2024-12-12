
/***
 *Extends a JTextPane, displays the name of the current maze handled by use
 *For example, if user open a maze file named "awesome", the the View will notify
 *this JTextPane to display this text : "Current maze name : awesome".
 *So this allow user to know wich maze he is handling.
 */
package uiTextAreaComponents;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import uiMvc.HexagonGridModel;

public class CurrentMazeNameTextArea extends JTextPane implements ChangeListener{
	/**
	 * it is the HexagonGridModel, because we need it to get the current maze name
	 */
	private final HexagonGridModel hexagonModel;
	/***
	 * creates a textArea using the HexagonGridModel
	 * @param hexagonModel
	 */
	public CurrentMazeNameTextArea(HexagonGridModel hexagonModel) {
		super();
		//initializing the textArea
		setText("Current maze name : \n"+hexagonModel.getCurrentFileName());
		
		//seeting some preferences
		this.hexagonModel = hexagonModel;
		setPreferredSize(new Dimension(200, 60));
		setForeground(Color.BLACK);
		setFont(new Font("serif", Font.PLAIN, 18));
		
		//centering the text in the text area 
		StyledDocument document = getStyledDocument();
		SimpleAttributeSet centerAttributeSet = new SimpleAttributeSet();
		StyleConstants.setAlignment(centerAttributeSet, StyleConstants.ALIGN_CENTER);
		document.setParagraphAttributes(0, document.getLength(), centerAttributeSet , false);
		
		
		
	}
	/**
	 * updates the text Area when user change the maze file
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		setText("Current maze name :  \n" + hexagonModel.getCurrentFileName());
	}
	 
	 
}
