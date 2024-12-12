/**
*MazeSolverStatusTextArea is a custom JTextPane component that displays the current status of 
*the shortest path calculation
*in a hexagonal grid. It listens for changes in the HexagonGridModel and updates its text accordingly. It also adds an
*animation effect by moving the component back and forth horizontally within its parent JPanel.
@author maham
*/
package uiTextAreaComponents;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import uiMvc.HexagonGridModel;

public class MazeSolverStatusTextArea extends JTextPane implements ChangeListener{
	private final  HexagonGridModel hexagonGridModel;
	private final  JPanel contentJPanel;

	/**
	 * Constructs a MazeSolverStatusTextArea object.
	 * @param hexagonGridModel the HexagonGridModel that this component listens to for changes
	 * @param contentJPanel the parent JPanel that this component is added to
	 */
	public MazeSolverStatusTextArea(HexagonGridModel hexagonGridModel, JPanel contentJPanel) {
	    super();
	    
	    this.hexagonGridModel = hexagonGridModel;
	    this.contentJPanel = contentJPanel;
	    setText(hexagonGridModel.getShortestPathStatus());
	    
	    //setting some preferences
	    setPreferredSize(new Dimension(200, 60));
	    setBackground(CustomColor.TURQUOISE_COLOR);
	    setForeground(Color.WHITE);
	    setFont(new Font("serif", Font.PLAIN, 18));
	    
	    //centering the text in the TextArea
	    StyledDocument document = getStyledDocument();
	    SimpleAttributeSet centerAttributeSet = new SimpleAttributeSet();
	    StyleConstants.setAlignment(centerAttributeSet, StyleConstants.ALIGN_CENTER);
	    document.setParagraphAttributes(0, document.getLength(), centerAttributeSet , false);
	    addAnimation();
	}

	/**
	 * Listens for changes in the HexagonGridModel and updates the text of this component accordingly.
	 *
	 * @param e the ChangeEvent that triggered this method
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
	    setText(hexagonGridModel.getShortestPathStatus());
	}

	/**
	 * Adds an animation effect to this component by moving it back and forth horizontally within its parent JPanel.
	 * Then, will have focus on the textArea
	 */
	public void addAnimation() {
	    Timer timer = new Timer(100, new ActionListener() {
	        int x = 0;
	        int y = 0;
	        int dx = 5;
	        int dy = 5;

	        public void actionPerformed(ActionEvent e) {
	            x += dx;
	            y += dy;

	            if (x < 0 || x + getWidth() > contentJPanel.getWidth()) {
	                dx = -dx;
	            }
	            if (y < 0 || y + getHeight() > contentJPanel.getHeight()) {
	                dy = -dy;
	            }

	            setBounds(x, 30, getWidth(), getHeight());
	        }
	    });
	    timer.start();
	}

}