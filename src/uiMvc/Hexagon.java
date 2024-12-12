package uiMvc;

/***
 * This class, Hexagon, represents a hexagon object that is used to draw the maze on the GUI as a grid of hexagons. 
 * It has a polygon attribute that stores the points of the hexagon obtained by some trigonometric calculations. 
 * The hexagon is created with its center, radius, fill color, and its positions (i, j) in the grid. 
 * The createPolygon() function calculates the points of the hexagon and stores them in the polygon attribute. 
 * The drawHexagon() function uses the polygon attribute to draw the hexagon. 
 * The hexagon will be filled by the LIGHT_GRAY color, and for Wall, Departure, and Arrival box, 
 * a circle filled with the given color will be drawn in the center of the corresponding hexagon. 
 * The Hexagon class also has some getter functions for the center, polygon, 
 * and the positions of the hexagon in the grid.
 * @author maham
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Stroke;
import java.util.ArrayList;

public final class Hexagon {

	private Polygon polygon;
	private final int radius;
	private final Point center;
	private final int i;
	private final int j;
	private final Color hexagonColor;

	/***
	 * creates an instance of hexagon by it center coordinates, its positions i, j
	 * in the grid and its fill color.
	 * 
	 * @param center : the center of the hexagon as a Point Object @see
	 *               java.awt.Point;
	 * @param radius : the radius of the hexagon
	 * @param color  : fill color
	 * @param i      : column number in the grid
	 * @param j      : line number in the grid
	 * 
	 */

	public Hexagon(Point center, int radius, Color color, int i, int j) {
		super();
		this.radius = radius;
		this.center = center;
		this.hexagonColor = color;
		this.i = i;
		this.j = j;
		this.polygon = new Polygon();
		this.polygon = createPolygon();
	}

	/***
	 * gets the six points of the hexagon using some trigonometric calculations.
	 * 
	 * @return these points as an Polygon Object @see java.awt.Polygon
	 */
	private final Polygon createPolygon() {
		Polygon p = new Polygon();
		double theta = Math.PI / 6;
		for (int i = 0; i < 6; i++) {
			int x = (int) (this.center.x + radius * Math.cos(theta));
			int y = (int) (this.center.y + radius * Math.sin(theta));
			p.addPoint(x, y);
			theta = theta + Math.PI / 3;
		}
		return p;

	}

	/***
	 * draws the hexagon using the polygon Object @see Polygon createPolygon(). the
	 * polygon attribute it initialize when the creation of the hexagon object and
	 * this function uses it to draw the hexagon. The hexagon represents actually
	 * the the mazeBox. Each hexagon will be filled by LIGHT_GRAY color. But for
	 * Wall, Departure and Arrival box, a circle filled with the given color when
	 * constructing this hexagon Object will be drawn in the center of the
	 * corresponding hexagon.
	 * 
	 * @param g
	 */
	public final void drawHexagon(Graphics g) {

		Stroke stroke = new BasicStroke(2.5f);
		g.setColor(Color.LIGHT_GRAY);
		((Graphics2D) g).setStroke(stroke);
		g.drawPolygon(this.polygon);
		g.setColor(Color.DARK_GRAY);
		g.fillPolygon(this.polygon);

		//drawing the circle in the center of  hexagon if it type is :
		//arrival, departure or wall.
		if (hexagonColor != null) {
			g.setColor(hexagonColor);
			int radius = 2 * this.radius / 3;
			g.fillOval(center.x - radius, center.y - radius, radius * 2, radius * 2);
		}
	}

	/***
	 * gets the center of the this hexagon
	 * 
	 * @return a center as a Point Object
	 */
	public Point getCenter() {
		return center;
	}

	/***
	 * @return the six points of the hexagon as a Polygon object
	 */
	public Polygon getPolygon() {
		return polygon;
	}

	public int getJ() {
		return j;
	}

	public int getI() {
		return i;
	}

}
