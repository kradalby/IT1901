package org.prosjekt.GUIClient;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TopLevelGuiTest1 {
	
	
	
	
	/*public static void main (String args[]){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				createGui();
			}
		});
			
	}*/
	public static void createGui(){
		JFrame frame;
		JLabel emptyLabel;
		frame = new JFrame("FrameDemo");
		emptyLabel = new JLabel("");
		emptyLabel.setPreferredSize(new Dimension(175, 100));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}
	

}
