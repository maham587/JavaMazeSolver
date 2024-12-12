package uiButtons;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JButton;

/**
 * The RoundedButton class extends the JButton class to create a custom rounded button.
 */
public class RoundedButton extends JButton {

    /**
     * Constructs a new RoundedButton object with the specified label and color.
     *
     * @param label the label text for the button
     * @param color the color of the button and border
     */
    public RoundedButton(String label, Color color) {
        super(label);

        // Sets the foreground color of the button text to black.
        setForeground(Color.BLACK);

        // Makes the button background transparent.
        setOpaque(false);

        // Sets the border of the button to a RoundedBorder object with the specified color.
        setBorder(new RoundedBorder(color));

        // Sets the background color of the button to the specified color.
        setBackground(color);

        // Sets the horizontal and vertical alignment of the button to center.
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setAlignmentY(Component.CENTER_ALIGNMENT);

        // Sets the preferred size of the button to 200x50 pixels.
        setPreferredSize(new Dimension(200, 50));
    }
}

