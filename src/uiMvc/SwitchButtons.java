/**
*The SwitchButtons class is responsible for creating and managing the toggle buttons
*that allow the user to switch between the different types of hexagons that can be
*added to the hexagonal grid. It extends JPanel and implements the ChangeListener
*interface.
*/
package uiMvc;

import java.awt.Button;
import java.awt.Color;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicButtonUI;

import uiTextAreaComponents.CustomColor;

public class SwitchButtons extends JPanel implements ChangeListener {

	private JToggleButton emptyButton, wallButton, departureButton, arrivalButton;
	private ButtonGroup buttonGroup;
	private final HexagonGridModel hexagonGridModel;
	
	/**
	 * Constructs a new SwitchButtons object.
	 * @param hexagonGridModel The HexagonGridModel that will be used to update the grid.
	 */

	public SwitchButtons(HexagonGridModel hexagonGridModel) {

		super(new FlowLayout(FlowLayout.CENTER));
		this.hexagonGridModel = hexagonGridModel;
		
		// create the buttons and add them to the button group
		emptyButton = new JToggleButton("EMPTY");
		wallButton = new JToggleButton("WALL");
		departureButton = new JToggleButton("DEPARTURE");
		arrivalButton = new JToggleButton("ARRIVAL");
		
		//adding the JToggleButton is a ButtonGroup
		buttonGroup = new ButtonGroup();
		addButton(buttonGroup, emptyButton, Color.DARK_GRAY);
		addButton(buttonGroup, wallButton, CustomColor.WALL_COLOR);
		addButton(buttonGroup, departureButton, CustomColor.DEPARTURE_COLOR);
		addButton(buttonGroup, arrivalButton, CustomColor.ARRIVAL_COLOR);
		
		//setting some preferences 
		wallButton.setSelected(true);
		setSize(new Dimension(400, 50));
		setBackground(CustomColor.DESELECTED_COLOR);
	}
	
	/**
	 * Adds a JToggleButton to a ButtonGroup and sets its color and preferred size.
	 * @param buttons The ButtonGroup to add the button to.
	 * @param button The JToggleButton to add.
	 * @param buttonColor The color to set the button to.
	 */
	public void addButton(ButtonGroup buttons, JToggleButton button, Color buttonColor) {

		button.setForeground(Color.WHITE);
		buttons.add(button);
		button.setPreferredSize(new Dimension(100, 50));
		button.setUI(new CustomButtonUI(buttonColor));
		this.add(button);
	}
	
	
	
	/**
	 * when user click on the hexagon to change it type the view will notify this Jpanel, 
	 * Then, it will call the model to update the 
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		if (emptyButton.isSelected()) {
			hexagonGridModel.upateHexagonToEmpty();
		} else if (wallButton.isSelected()) {
			hexagonGridModel.updateHexagonToWall();
		} else if (departureButton.isSelected()) {
			hexagonGridModel.updateHexagonToDeparture();
		} else {
			hexagonGridModel.updateHexagonToArrival();
		}

	}
	
	/**
	 * The CustomButtonUI class is a nested class that extends BasicButtonUI and 
	 * sets the button color and border depending on whether the button is selected or not.
	 */
	private class CustomButtonUI extends BasicButtonUI {
		private Color selectedColor;
		private final Color deselectedColor = CustomColor.DESELECTED_COLOR;

		public CustomButtonUI(Color selectedColor) {
			this.selectedColor = selectedColor;

		}

		@Override
		public void paint(Graphics g, JComponent c) {
			AbstractButton button = (AbstractButton) c;
			boolean selected = button.isSelected();
			button.setBackground(selected ? selectedColor : deselectedColor);
			button.setBorder(selected ? BorderFactory.createLineBorder(selectedColor, 2) : null);
			super.paint(g, c);
		}

	}

	

}
