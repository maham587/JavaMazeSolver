/***
 * 
 */
package uiTextAreaComponents;
import java.awt.Color;
import java.awt.Frame;

import javax.swing.*;

import javax.swing.*;

public class MazeSizeInputDialog extends JPanel {
    private JLabel label1, label2;
    private JComboBox<Integer> comboBox1, comboBox2;
    private Frame frame;
    int result = -1;

    public MazeSizeInputDialog(Frame frame) {
       // super(frame, "Enter maze size", true);
    	this.frame = frame;
        Integer[] values = new Integer[16];
        for (int i = 1; i < values.length; i++) {
            values[i] = i;
        }
        
        
        label1 = new JLabel("maze lenght:");
        comboBox1 = new JComboBox<>(values);

        label2 = new JLabel("maze height:");
        comboBox2 = new JComboBox<>(values);

        JPanel panel = new JPanel();
        panel.add(label1);
        panel.add(comboBox1);
        panel.add(label2);
        panel.add(comboBox2);
        result =  JOptionPane.showConfirmDialog(frame, panel, "Select maze size", JOptionPane.PLAIN_MESSAGE);
    }

    public int getLenght() {
       if(result == JOptionPane.OK_OPTION) {
    	   try {
    		   return (int)comboBox1.getSelectedItem();
		} catch (Exception e) {
			return -1;
		}
       }
       return -1;
       
	}
    	
    

    public int getHeight() {
    	if(result == JOptionPane.OK_OPTION) {
     	   try {
     		   return (int)comboBox2.getSelectedItem();
 		} catch (Exception e) {
 			return -1;
 		}
        }
        return -1;
    }
}