package org.prosjekt.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel{
	
	BufferedImage img;
	
	public BackgroundPanel(String fileAdress){
		try{
                    final URL url = Thread.currentThread().getContextClassLoader().getResource(fileAdress);
			img = ImageIO.read(url);
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
