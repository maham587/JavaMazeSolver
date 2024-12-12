package uiMvc;

 
import java.awt.FlowLayout;
import java.awt.Graphics;
import javax.swing.JPanel;
/**
*This class represents a JPanel that displays the hexagon grid of the maze. It uses a HexagonGridModel
*to get the information of the grid, and then draws the hexagons on the panel.
*This class extends the JPanel class, and overrides its paint method to draw the hexagons.
**/

public final  class HexagonGridPanel extends JPanel {
	
	private final HexagonGridModel hexagonGridModel;
	 
	public HexagonGridPanel(HexagonGridModel hexagonGridModel) {
		super(new FlowLayout());
		this.hexagonGridModel = hexagonGridModel;
		setPreferredSize(getPreferredSize());
	}

	/**
	 * This method gets the HexagonGridModel object associated with this panel.
	 * @return the HexagonGridModel object associated with this panel
	 */
	public HexagonGridModel getHexagonGridModel() {
		return hexagonGridModel;
	}

	/**
	 * This method overrides the paint method of JPanel to draw the hexagon grid on the panel.
	 * It calls the paintComponent method of the associated HexagonGridModel to draw the hexagons.
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.hexagonGridModel.paintComponent(g);
	}

	
	
}
