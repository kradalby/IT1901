package org.prosjekt.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.prosjekt.client.ClientExample;
import org.prosjekt.helperclasses.Farmer;

public class HelperInformation extends JFrame implements ActionListener{
	private static String OK = "ok";
    private static String CANCEL = "cancel";
    private static String APPLY = "apply";
	
    private Font headerFont = new Font("kalinga", Font.PLAIN, 24);
    private Font smallHeaderFont = new Font("kalinga", Font.PLAIN, 22);
    private Font font = new Font("kalinga", Font.PLAIN, 16);
    private Font fontTextField = new Font("kalinga", Font.PLAIN, 12);
	private Color textColor = new Color(32, 87, 0);
	private Farmer user;
	
	
	public HelperInformation(Farmer user){
		super.setContentPane(new BackgroundPanel(ClientExample.pathToBackGround()));
		setLayout(new BorderLayout());
		this.user = user;
		
		add(createContentPanel(), BorderLayout.CENTER);
		
		
		pack();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(455, 475);
		setVisible(true);
	}
	
	private JPanel createContentPanel(){
		JPanel contentPanel = new JPanel();
		contentPanel.setOpaque(false);
		contentPanel.add(new JLabel(""));
		
		return contentPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
