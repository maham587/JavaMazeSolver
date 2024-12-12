package uiMvc;

/**
 * this is the controller of @see HexagonGridPanel, when user clicks in the grid panel
 * The HexagonGridPanel will be notified to update if necessary the model.
 */

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public final class HexagonGridController implements MouseListener {

	private HexagonGridPanel hexagonGridPanel;
	/**
	 * creates an controller using a HexagonGridPanel
	 * @param hexagonGridPanel
	 */
	public HexagonGridController(HexagonGridPanel hexagonGridPanel) {

		this.hexagonGridPanel = hexagonGridPanel;

	}
	/**
	 * catching the click and sending the notifications to the view and the model
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		hexagonGridPanel.getHexagonGridModel().setPolygonSelected(e.getX(), e.getY());
	}
	@Override
	public void mousePressed(MouseEvent e) {
		return;
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		return;
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		return;
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		return;
		
	}

}
