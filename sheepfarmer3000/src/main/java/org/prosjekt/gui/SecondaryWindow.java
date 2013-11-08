package org.prosjekt.gui;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import org.prosjekt.helperclasses.Farmer;

public class SecondaryWindow extends JFrame implements ActionListener {
	private String backgroundImage = "images\\bakgrunn 450x450.jpg";
	
	public SecondaryWindow(Farmer user){
		super("Secondary Window");	//HUSK å ENDRE
		//super.setContentPane(new BackgroundPanel(backgroundImage));
		setLayout(new BorderLayout());
		
		add(new SheepListFrame(user), BorderLayout.CENTER);
		
		/*
		//add(createUserInformationHeader(), BorderLayout.PAGE_START);
		
		JLabel emptyLeftSide = new JLabel("");
		emptyLeftSide.setPreferredSize(new Dimension(60,50));	
		add(emptyLeftSide, BorderLayout.LINE_START);
		
		add(new SheepListPanel(user), BorderLayout.CENTER);
		
		JLabel emptyRightSide = new JLabel("");
		emptyRightSide.setPreferredSize(new Dimension(40,50));
		add(emptyRightSide, BorderLayout.LINE_END); 
		
		*/
		pack();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setVisible(true);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
