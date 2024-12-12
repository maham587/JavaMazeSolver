package uiButtons;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.border.Border;

/**
 * The RoundedBorder class is used to create a custom rounded border for a JButton.
 * This class implements the Border interface to define the custom border behavior.
 */
public class RoundedBorder implements Border {
    /**
     * The color of the border.
     */
    private Color color;

    /**
     * The radius of the border.
     */
    private final int radius = 40;

    /**
     * Constructs a new RoundedBorder object with the specified color.
     *
     * @param color the color of the border
     */
    public RoundedBorder(Color color) {
        this.color = color;
    }

    /**
     * Returns the insets of the border.
     *
     * @param c the component to get the insets for
     * @return the insets of the border
     */
    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(radius, radius + 1, radius + 2, radius);
    }

    /**
     * Returns whether or not the border is opaque.
     *
     * @return false, because the border is not opaque
     */
    @Override
    public boolean isBorderOpaque() {
        return false;
    }

    /**
     * Paints the border for the specified component with the specified position and size.
     *
     * @param c the component to paint the border for
     * @param g the graphics context to use for painting
     * @param x the x position of the border
     * @param y the y position of the border
     * @param width the width of the border
     * @param height the height of the border
     */
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 100));
        g.fillRoundRect(x, y, width - 1, height - 1, radius, radius);
    }
}

