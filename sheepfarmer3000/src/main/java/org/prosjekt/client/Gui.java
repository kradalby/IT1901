package org.prosjekt.client;

import com.sun.security.ntlm.Client;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import static org.prosjekt.client.Gui.currentUser;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.dynamicmaps.CustomMapViewer;
import org.prosjekt.helperclasses.Sheep;


public class Gui implements ActionListener, ItemListener {
	protected JFrame frame;
	private CustomMapViewer kart;
        private JLabel emptyLabel;
	private JMenuBar menuBar;
	private PasswordBox passwordBox;
	private StatisticsPane statPane;
	private FarmerSettingsPane setPane;
	protected JFrame statFrame;
	private JFrame farmerSettingsFrame;
	protected static Farmer currentUser;
        private static Client client;
        private int POPUP_X;
        private int POPUP_Y;
	
	
	

	
	//Lager GUI, praksis kun den GUI'en som vises n�r passordboksen er �pen, resten h�ndteres av passwordConfirmed
	public void createGui(){
		//initialize();
		//Lager en JFrame
		frame = new JFrame("Sheep system 3000");
		emptyLabel = new JLabel("");
		emptyLabel.setPreferredSize(new Dimension(800, 600));
                POPUP_X = 800/2;
                POPUP_Y = 600/2;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
		
		//�pner passordboksen
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
        
        public void createGui(Client client){
		//initialize();
		//Lager en JFrame
		frame = new JFrame("Sheep system 3000");
		emptyLabel = new JLabel("");
		emptyLabel.setPreferredSize(new Dimension(800, 600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
		
                //Lagrer en refenranse til clientet som lager GUI-en
                this.client = client;
                
		//�pner passordboksen
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
	
	
	//Henter GUI
	public JFrame getGui(){
		this.createGui();
		return this.frame;
	}
        
        public final Client getClient(){
            return this.client;
        }
	
	
	//Denne tar seg i praksis av alt, initializer, etter passordet er bekreftet
	protected void passwordConfirmed(Farmer user){
		this.currentUser = user;
		frame.remove(passwordBox);
		kart = new CustomMapViewer(this);
                initializeMap(kart);
		frame.add(kart);
		//Lager en meny og legger til elementer i menyen
		menuBar = new JMenuBar();
		menuBar.add(createFileMenu());
		menuBar.add(createStatisticsMenu());
		menuBar.add(createSettingsMenu());
		menuBar.add(createHelpMenu());
		//Legger til riktig user
		
		statPane = new StatisticsPane();
		//setPane = new FarmerSettingsPane(currentUser);
		
		//Legger menyen til JFrame og gj�r JFrame klar til � vises
		frame.setJMenuBar(menuBar);
		frame.pack();
		frame.setVisible(true);
		//legg til en funksjon som nullstiller passordet i passwordbox
	}
	
	
	
	private void initialize(){		//Forel�pig ikke i bruk
		
		//Denne kan v�re reduntant, da man i praksis f�rst tenger framen n� passordet er godtatt,
		//Hele denne metodens funksjon blir erstattet av passwordConfirmed
		
		//Denne metoden skal brukes til � hente all info fra server
		/*Kan hente og sende data til riktig sted p� denne m�te;
		 * statPane.initialize(server.getFarmer);
		 * setPane.initialize(server.getFarmer);
		 */
		
		//statPane = new StatisticsPane();
		//setPane = new FarmerSettingsPane(currentUser);
		//fillSettingsPane();
	}



	/*
	 * 
	 * Lager alle hovedmenyene, og legger til undermenyene i disse
	 * 
	 */

	//Lager "File" menyen, og legger til undermenyer
	private JMenu createFileMenu(){
		JMenu file = new JMenu("File");
		file.add(createNewMenuItem());
		file.add(createOpenMenuItem());
		file.add(createLogOutMenuItem());
		file.addSeparator();
		file.add(createQuitMenuItem());
		return file;
		
	}
	
	//Lager "statistics"-menyen, og legger til undermenyer
	private JMenu createStatisticsMenu(){
		JMenu statistics = new JMenu("Statistics");
		statistics.add(createSheepListMenuItem());
		statistics.add(createAttackListMenuItem());
		
		
		
		return statistics;
	}
	
	//Lager "settings"-menyen, og legger til undermenyer
	private JMenu createSettingsMenu(){
		JMenu settings = new JMenu("Settings");
		settings.add(createPersonalDataMenuItem());
		settings.add(createChangePasswordMenuItem());
		
		return settings;
		
	}
	
	//Lager "help"-menyen og legger til undermenyer
	private JMenu createHelpMenu(){
		JMenu help = new JMenu("Help");
		help.add(createUserManualMenuItem());
		
		return help;
		
	}
	//Kan ogs� legge til keyboardsnarveier til disse menyelementene
	
	
	
	/*
	 * 
	 * Menyelementer til "settings"-menyen
	 * 
	 */
	
	//Lager elementet Personal Data til menyen
	private JMenuItem createPersonalDataMenuItem(){
		JMenuItem personalData = new JMenuItem("Personal data");
		personalData.addActionListener(this);
		personalData.setActionCommand("personalData");
		return personalData;
	}
	
	//Lager elementet change password til menyen
	private JMenuItem createChangePasswordMenuItem(){
		JMenuItem changePassword = new JMenuItem("Change Password");
		changePassword.addActionListener(this);
		changePassword.setActionCommand("changePassword");
		return changePassword;
	}

	//lager hele "settings"-vinduet
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

	
	
	/*
	 * 
	 * Menyelementer til "help"-menyen
	 * 
	 */
	
	//Lager elementet User Manual til menyen
	private JMenuItem createUserManualMenuItem(){
		JMenuItem userManual = new JMenuItem("User Manual");
		userManual.addActionListener(this);
		userManual.setActionCommand("userManual");
		return userManual;
	}
	
	
	
	/*
	 * 
	 * Menyelementer til "statistics"-menyen
	 * 
	 */
	
	//Lager elementet sheep list til menyen
	private JMenuItem createSheepListMenuItem(){
		JMenuItem sheepList = new JMenuItem("Sheep List");
		sheepList.addActionListener(this);
		sheepList.setActionCommand("sheepList");
		return sheepList;
	}
	
	//Lager elementet attack list til menyen
	private JMenuItem createAttackListMenuItem(){
		JMenuItem attackList = new JMenuItem("Attack List");
		attackList.addActionListener(this);
		attackList.setActionCommand("attackList");
		return attackList;
	}
	//Lager hele statistikkvinduet
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
                newSheep.setActionCommand("newSheep");
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
        
        /*
         * PopupMeny 
         */
        
        public void createPopup(int x, int y){
            //Må endres, gjør ingenting.
            System.out.print("Clicked at x:" + x +" y: " + y + "");
        }
        
        public void createPopup(int x, int y, int id){
            //Må endres, gjør ingenting.
            System.out.print("Clicked at x:" + x +" y: " + y + ". Id = " + id +".");
        }
	
	
	/*
	 * 
	 * Event-h�ndtering
	 * 
	 */
	
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
		else if(command == "attackList"){
			createStatFrame(1);
		}
		else if(command == "personalData"){
			createFarmerSettingsFrame(0);
		}
		else if(command == "changePassword"){
			createFarmerSettingsFrame(1);
		}
		else if(command == "userManual"){
			//Her m� kode inn for � �pen bruksmanualen
		}
                else if(command == "newSheep"){
			createPopup(POPUP_X, POPUP_Y);
		}
		
		
	}
        
        private void initializeMap(CustomMapViewer map){
            for (Sheep sheep : currentUser.getSheeps()){
                map.addSheep(sheep);
            }
            map.addPath((ArrayList<Coordinate>)currentUser.getCoordinates());
        }
	
	
	
	/*
	 * 
	 * Logg ut metode
	 * 
	 */
	
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
