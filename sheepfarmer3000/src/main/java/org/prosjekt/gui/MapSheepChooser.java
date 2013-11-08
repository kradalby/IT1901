package org.prosjekt.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Sheep;

public class MapSheepChooser extends JFrame implements ActionListener {
	private Farmer user;
	private String backgroundImage = "images\\bakgrunn 450x450.jpg";
	private JComboBox chooser;
	private Font font = new Font("kalinga", Font.PLAIN, 18);
    private Font headerFont = new Font("kalinga", Font.PLAIN, 22);
	private Color textColor = new Color(32, 87, 0);
	private static String OK = "ok";
    private static String CANCEL = "cancel";
    private Sheep currentSheep;
	
	public MapSheepChooser(Farmer user){
		super("Choose sheep");
		super.setContentPane(new BackgroundPanel(backgroundImage));
		setLayout(new FlowLayout());
		this.user = user;
		
		JLabel header = new JLabel("Choose sheep to view in map");
		header.setFont(headerFont);
		header.setForeground(textColor);
		add(header, BorderLayout.PAGE_START);
		createContentPanel();
		pack();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 150);	//y var opprinnelig 250
		setVisible(true);
	}
	
	private void createContentPanel(){
		JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		contentPanel.setOpaque(false);
		
		JLabel selectSheep = new JLabel("Sheep");
		selectSheep.setFont(font);
		selectSheep.setForeground(textColor);
		
		JButton okButton = new JButton(OK);
		okButton.setOpaque(false);
		okButton.setPreferredSize(new Dimension(60,35));
		okButton.setForeground(textColor);
		okButton.setBackground(textColor);
		okButton.setFont(font);
		okButton.setActionCommand(OK);
		okButton.addActionListener(this);
		
		JButton cancelButton = new JButton(CANCEL);
		cancelButton.setPreferredSize(new Dimension(95,35));
		cancelButton.setOpaque(false);
		cancelButton.setForeground(textColor);
		cancelButton.setBackground(textColor);
		cancelButton.setFont(font);
		cancelButton.setActionCommand(CANCEL);
		cancelButton.addActionListener(this);
		
		contentPanel.add(selectSheep);
		contentPanel.add(createChooser());
		contentPanel.add(okButton);
		contentPanel.add(cancelButton);
		
		add(contentPanel, BorderLayout.CENTER);
		
	}
	
	private JComboBox createChooser(){
		ArrayList<String> sheepArrayList = new ArrayList<String>();
		
		for(Sheep s: user.getSheeps()){
			sheepArrayList.add(s.getId());
		}
		
		String [] sheepArray = new String[user.getSheeps().size()];
		sheepArrayList.toArray(sheepArray);
		
		chooser = new JComboBox(sheepArray);
		
		chooser.addActionListener(this);
		chooser.setAlignmentY(LEFT_ALIGNMENT);
		chooser.setSelectedIndex(0);
		return chooser;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if (cmd.equals(OK)){
                    if (currentSheep == null)
                        return;
                        MainPage.kart.refreshMap();
			MainPage.kart.addPath(currentSheep.getCordinates());
			this.dispose();
		}
		else if (cmd.equals(CANCEL)){
			this.dispose();
		}
                else{
		JComboBox cb = (JComboBox) e.getSource();
		String sheepId = (String)cb.getSelectedItem();
		setCurrentSheep(sheepId);
		}
		
	}
        private void setCurrentSheep(String sheepId){
            
            for (Sheep s: user.getSheeps()){
                if(sheepId.equals(s.getId())){
                    currentSheep = s;
                }
            }
        }

}
