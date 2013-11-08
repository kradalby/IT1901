package org.prosjekt.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.joda.time.DateTime;
import org.prosjekt.client.ClientExample;
import org.prosjekt.helperclasses.Coordinate;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Sheep;

public class AddSheep extends JFrame implements ActionListener{
	private static String OK = "ok";
    private static String CANCEL = "cancel";
	private Farmer user;
	
	private Font headerFont = new Font("kalinga", Font.PLAIN, 24);
    private Font smallHeaderFont = new Font("kalinga", Font.PLAIN, 22);
    private Font font = new Font("kalinga", Font.PLAIN, 17);
    private Font fontTextField = new Font("kalinga", Font.PLAIN, 12);
	private Color textColor = new Color(32, 87, 0);
	
	private JTextField idField;
	private JTextField longitudeField;
	private JTextField latitudeField;
	private JTextField birthField;
	
	public AddSheep(Farmer user){
		super("Add new sheep");
		super.setContentPane(new BackgroundPanel(ClientExample.pathToBackGround()));
		setLayout(new BorderLayout());
		this.user = user;
		
		createContentPanel();
		pack();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 250);
		setVisible(true);
		
	}
	
	private void createContentPanel(){
		JPanel contentPanel = new JPanel();
		contentPanel.setOpaque(false);
		contentPanel.setLayout(new BorderLayout());
		Dimension emptyBorders = new Dimension(40,30);
		
		
		JLabel emptyTop = new JLabel("");
		emptyTop.setOpaque(false);
		emptyTop.setPreferredSize(emptyBorders);
		
		JLabel emptyLeft = new JLabel("");
		emptyLeft.setOpaque(false);
		emptyLeft.setPreferredSize(emptyBorders);
		
		JLabel emptyRight = new JLabel("");
		emptyRight.setOpaque(false);
		emptyRight.setPreferredSize(emptyBorders);
		
		JLabel emptyBottom = new JLabel("");
		emptyBottom.setOpaque(false);
		emptyBottom.setPreferredSize(emptyBorders);
		
		contentPanel.add(emptyTop, BorderLayout.PAGE_START);
		contentPanel.add(emptyLeft, BorderLayout.LINE_START);
		contentPanel.add(emptyRight, BorderLayout.LINE_END);
		contentPanel.add(emptyBottom, BorderLayout.PAGE_END);
		
		contentPanel.add(createContent());
		
		add(contentPanel);
	}
	
	private JPanel createContent(){
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		content.setOpaque(false);
		
		content.add(createLeftSide(), BorderLayout.LINE_START);
		content.add(createRightSide(), BorderLayout.LINE_END);
		content.add(createButtons(), BorderLayout.PAGE_END);
		
		
		return content;
	}
	
	private JPanel createLeftSide(){
		JPanel leftSide = new JPanel();
		leftSide.setLayout(new BoxLayout(leftSide, BoxLayout.Y_AXIS));
		leftSide.setOpaque(false);
		leftSide.setPreferredSize(new Dimension(180,100));
		
		JLabel idLabel = new JLabel("Sheep ID:");
		idLabel.setFont(font);
		idLabel.setForeground(textColor);
		
		JLabel longitudeLabel = new JLabel("Longitude:");
		longitudeLabel.setFont(font);
		longitudeLabel.setForeground(textColor);
		
		JLabel latitudeLabel = new JLabel("Latitude:");
		latitudeLabel.setFont(font);
		latitudeLabel.setForeground(textColor);
		
		JLabel birthLabel = new JLabel("Birth date:");
		birthLabel.setFont(font);
		birthLabel.setForeground(textColor);
		
		idLabel.setAlignmentY(LEFT_ALIGNMENT);
		leftSide.add(idLabel);
		
		longitudeLabel.setAlignmentY(LEFT_ALIGNMENT);
		leftSide.add(longitudeLabel);
		
		latitudeLabel.setAlignmentY(LEFT_ALIGNMENT);
		leftSide.add(latitudeLabel);
		
		birthLabel.setAlignmentY(LEFT_ALIGNMENT);
		leftSide.add(birthLabel);
		
		
		
		return leftSide;
	}
	
	
	/*
	 * 
	 * textfeltene m� instansieres med det formatet de skal ha
	 * 
	 */
	private JPanel createRightSide(){
		JPanel rightSide = new JPanel();
		rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
		rightSide.setOpaque(false);
		rightSide.setPreferredSize(new Dimension(180,100));
		
		idField = new JTextField();
		idField.setOpaque(false);
		idField.setForeground(textColor);
		idField.setFont(fontTextField);
		idField.setActionCommand(OK);
		idField.addActionListener(this);
		
		longitudeField = new JTextField();
		longitudeField.setOpaque(false);
		longitudeField.setForeground(textColor);
		longitudeField.setFont(fontTextField);
		longitudeField.setActionCommand(OK);
		longitudeField.addActionListener(this);
		
		latitudeField = new JTextField();
		latitudeField.setOpaque(false);
		latitudeField.setForeground(textColor);
		latitudeField.setFont(fontTextField);
		latitudeField.setActionCommand(OK);
		latitudeField.addActionListener(this);
		
		birthField = new JTextField("dd.mm.yyyy");
		birthField.setOpaque(false);
		birthField.setForeground(textColor);
		birthField.setFont(fontTextField);
		birthField.setActionCommand(OK);
		birthField.addActionListener(this);
		
		idField.setAlignmentY(LEFT_ALIGNMENT);
		rightSide.add(idField);
		
		longitudeField.setAlignmentY(LEFT_ALIGNMENT);
		rightSide.add(longitudeField);
		
		latitudeField.setAlignmentY(LEFT_ALIGNMENT);
		rightSide.add(latitudeField);
		
		birthField.setAlignmentY(LEFT_ALIGNMENT);
		rightSide.add(birthField);
		
		
		
		return rightSide;
	}
	
	private JPanel createButtons(){
		JPanel buttons = new JPanel();
		buttons.setLayout(new BorderLayout());
		buttons.setOpaque(false);
		buttons.setPreferredSize(new Dimension(180, 50));
		
		JLabel emptyLeft = new JLabel("");
		emptyLeft.setPreferredSize(new Dimension(130,10));
		
		
		buttons.add(emptyLeft, BorderLayout.LINE_START);
		
		JPanel insideButtons = new JPanel();
		insideButtons.setOpaque(false);
		
		JButton okButton = new JButton(OK);
		okButton.setOpaque(false);
		okButton.setForeground(textColor);
		okButton.setBackground(textColor);
		okButton.setFont(font);
		okButton.setActionCommand(OK);
		okButton.addActionListener(this);
		
		JButton cancelButton = new JButton(CANCEL);
		cancelButton.setOpaque(false);
		cancelButton.setForeground(textColor);
		cancelButton.setBackground(textColor);
		cancelButton.setFont(font);
		cancelButton.setActionCommand(CANCEL);
		cancelButton.addActionListener(this);
		
		insideButtons.add(okButton, BorderLayout.LINE_START);
		insideButtons.add(cancelButton, BorderLayout.LINE_END);
		
		buttons.add(insideButtons, BorderLayout.CENTER);
		
		return buttons;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(OK.equals(cmd)){
			if (!saveChanges("04.11.2013")){	//husk at dataTime antagelig m� legges til her her
				JOptionPane.showMessageDialog(this, "Changes were not saved! Please try again.",
						"", JOptionPane.ERROR_MESSAGE);
				
			}
			else{
				SheepListFrame.updateData();
				Main.updateMainUser(user);
				this.dispose();
			}
			
		}
		else if (CANCEL.equals(cmd)){
			this.dispose();
		}
		
	}
	
	private boolean saveChanges(String date){	//date må byttes ut med datetime
		if (testIfValidId()){
			Coordinate c = null;
			try{
				c = new Coordinate(Double.parseDouble(latitudeField.getText()), 
						Double.parseDouble(longitudeField.getText()));
			}
			catch (NumberFormatException e){
				return false;
			}
			
			Sheep newSheep = new Sheep(idField.getText(),null,user.getId(), c);
			List<Sheep> newSheeps = user.getSheeps();
			newSheeps.add(newSheep);
			
			Farmer tempFarmer = user;
			tempFarmer.setSheeps(newSheeps);
			
			if(Main.saveChangesToFarmer(tempFarmer)){
				user = tempFarmer;
				return true;
			}
			else{
				return false;
			}

		}
		else{
			return false;
		}
		
		//burde kanskje teste om birht er riktig skrevet inn, men dette kan jo takles p� server
		
		//m� her legge til dateTime ved coordinate objektet
	}
	
	private boolean testIfValidId(){
		boolean tester = true;
		for (Sheep s:user.getSheeps()){
			if(s.getId().equals(idField.getText())){
				tester = false;
			}
		}
		return tester;
	}

}
