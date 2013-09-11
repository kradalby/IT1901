package org.prosjekt.GUIClient;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class Gui implements ActionListener, ItemListener {
	protected JFrame frame;
	private JLabel emptyLabel;
	private JLabel kart;
	private JMenuBar menuBar;
	private PasswordBox passwordBox;
	private StatisticsPane statPane;
	private FarmerSettingsPane setPane;
	protected JFrame statFrame;
	private JFrame farmerSettingsFrame;
	protected static Farmer currentUser;
	
	public JFrame getGui(){
		this.createGui();
		return this.frame;
	}
	
	public void createGui(){
		//initialize();
		//Lager en JFrame
		frame = new JFrame("Sheep system 3000");
		emptyLabel = new JLabel("");
		emptyLabel.setPreferredSize(new Dimension(800, 600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
		
		//åpner passordboksen
		passwordBox = new PasswordBox(this);
		//boolean test = passwordBox.getCorrect(); //trengs denne?
		frame.add(passwordBox);
		frame.addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e){
				passwordBox.resetFocus();
			}
		});
		frame.pack();
		frame.setVisible(true);
		
	}
	protected void passwordConfirmed(Farmer user){
		this.currentUser = user;
		frame.remove(passwordBox);
		kart = new JLabel("Kart");
		kart.setPreferredSize(new Dimension(800, 600));
		frame.add(kart);
		//Lager en meny og legger til elementer i menyen
		menuBar = new JMenuBar();
		menuBar.add(createFileMenu());
		menuBar.add(createStatisticsMenu());
		menuBar.add(createSettingsMenu());
		//Legger til riktig user
		
		statPane = new StatisticsPane();
		//setPane = new FarmerSettingsPane(currentUser);
		
		//Legger menyen til JFrame og gjør JFrame klar til å vises
		frame.setJMenuBar(menuBar);
		frame.pack();
		frame.setVisible(true);
		//legg til en funksjon som nullstiller passordet i passwordbox
	}
	private void initialize(){
		
		//Denne kan være reduntant, da man i praksis først tenger framen nå passordet er godtatt,
		//Hele denne metodens funksjon blir erstattet av passwordConfirmed
		
		//Denne metoden skal brukes til å hente all info fra server
		/*Kan hente og sende data til riktig sted på denne måte;
		 * statPane.initialize(server.getFarmer);
		 * setPane.initialize(server.getFarmer);
		 */
		
		//statPane = new StatisticsPane();
		//setPane = new FarmerSettingsPane(currentUser);
		//fillSettingsPane();
	}


	private JMenu createFileMenu(){
		JMenu file = new JMenu("File");
		file.add(createNewMenuItem());
		file.add(createOpenMenuItem());
		file.add(createLogOutMenuItem());
		file.addSeparator();
		file.add(createQuitMenuItem());
		return file;
		
	}
	private JMenu createStatisticsMenu(){
		JMenu statistics = new JMenu("Statistics");
		statistics.add(createSheepListMenuItem());
		
		
		
		return statistics;
	}
	private JMenu createSettingsMenu(){
		JMenu settings = new JMenu("Settings");
		settings.add(createPersonalDataMenuItem());
		
		return settings;
		
	}
	
	//Kan også legge til keyboardsnarveier til disse menyelementene
	
	
	/*
	 * 
	 * Menyelementer til "settings-menyen
	 * 
	 */
	
	private JMenuItem createPersonalDataMenuItem(){
		JMenuItem personalData = new JMenuItem("Personal data");
		personalData.addActionListener(this);
		personalData.setActionCommand("personalData");
		return personalData;
	}
	
	
	/*
	 * 
	 * Menyelementer til "statistics"-menyen
	 * 
	 */
	
	private JMenuItem createSheepListMenuItem(){
		JMenuItem sheepList = new JMenuItem("Sheep List");
		sheepList.addActionListener(this);
		sheepList.setActionCommand("sheepList");
		return sheepList;
	}
	
	
	/*
	 * 
	 * Menyelementer til "File"-menyen
	 * 
	 */
	
	private JMenuItem createNewMenuItem(){
		JMenu newSubmenu = new JMenu("New");
		newSubmenu.addActionListener(this);
		JMenuItem newSheep = new JMenuItem("New Sheep");
		newSheep.addActionListener(this);
		newSubmenu.add(newSheep);
		return newSubmenu;
	}
	private JMenuItem createOpenMenuItem(){
		JMenu openSubmenu = new JMenu("Open");
		openSubmenu.addActionListener(this);
		JMenuItem openSheep = new JMenuItem("Open Sheep");
		openSheep.addActionListener(this);
		openSubmenu.add(openSheep);
		return openSubmenu;
	}
	private JMenuItem createLogOutMenuItem(){
		JMenuItem logOut = new JMenuItem("Log out");		
		logOut.addActionListener(this);
		logOut.setActionCommand("logOut");
		return logOut;
	}
	private JMenuItem createQuitMenuItem(){
		JMenuItem quit = new JMenuItem("Quit");
		quit.addActionListener(this);
		quit.setActionCommand("quit");
		return quit;
	}
	
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command == "quit"){
			System.exit(0);
		}
		else if (command == "logOut"){
			this.logOut();
		}
		
		
		else if(command == "sheepList"){
			createStatFrame(0);
			
		}
		else if(command == "personalData"){
			createFarmerSettingsFrame(0);
		}
		
		
	}
	private void createStatFrame(int chosenTab){
		statFrame = new JFrame("Statistics");
		JLabel emptyLabel2 = new JLabel("");
		emptyLabel2.setPreferredSize(new Dimension(800, 600));
		statFrame.getContentPane().add(emptyLabel2, BorderLayout.CENTER);
		statPane.tabbedPane.setSelectedIndex(chosenTab);
		statFrame.add(statPane);
		statFrame.pack();
		statFrame.setVisible(true);
	}
	private void createFarmerSettingsFrame(int chosenTab){
		
		farmerSettingsFrame = new JFrame("Settings");
		JLabel emptyLabel3 = new JLabel("");
		emptyLabel3.setPreferredSize(new Dimension(800, 600));
		farmerSettingsFrame.getContentPane().add(emptyLabel3, BorderLayout.CENTER);
		setPane = new FarmerSettingsPane(currentUser, farmerSettingsFrame);
		setPane.tabbedPane.setSelectedIndex(chosenTab);
		
		farmerSettingsFrame.add(setPane);
		farmerSettingsFrame.pack();
		farmerSettingsFrame.setVisible(true);
		
	}

	
	private void logOut(){
		//clear username and password
		//lagre all data fra statPane og setPane til server
		this.frame.dispose();
		this.createGui();
	}
	
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}



}
