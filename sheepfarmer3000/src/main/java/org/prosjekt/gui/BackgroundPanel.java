package org.prosjekt.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel{
	
	BufferedImage img;
	
	public BackgroundPanel(String fileAdress){
		try{
			img = ImageIO.read(new File(fileAdress));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		

	}
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		g.drawImage(img, 0,0,getWidth(), getHeight(), null);
	}

}
