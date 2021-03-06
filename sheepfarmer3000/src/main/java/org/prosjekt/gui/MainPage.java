package org.prosjekt.gui;

import com.google.common.base.Stopwatch;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.net.URI;
import org.prosjekt.dynamicmaps.CustomMapViewer;
import org.prosjekt.helperclasses.Farmer;


/**
 * Klasse som lager hovedvinduet i GUI, dette vinduet inneholder kartet og alle
 * menyvalgene
 *
 * @author Martin H. Bårnes <martin.h.barnes@gmail.com>
 */
public class MainPage extends JFrame implements ActionListener{
	private String backgroundImage = "images/innlogging1.jpg";
	private JMenuBar menuBar;
        public static CustomMapViewer kart;
        private URI userManual;
	//private static Map map;


    /**
     * Konstruktør, åpner hovedvinduet i GUI
     */
	public MainPage(){
		super("Sheep Farmer 3000");
                Stopwatch s1 = new Stopwatch();
                s1.start();
		setContentPane(new BackgroundPanel(backgroundImage));
                setLayout(new BorderLayout());
                try{
                    userManual = new java.net.URI("https","//kradalby.no/sheepfarmer3000brukermanual.pdf", null );
                }
                catch(Exception e){
                    System.out.println(e.getStackTrace());
                }


		createMain();
		pack();

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setVisible(true);

                s1.stop();
                System.out.println("Time to create main window: " + s1.elapsedMillis());
	}

    /**
     * Metode som lager øverstenivå-elementene i MainPage-vinduet, kart og
     * menyen øverst på skjermen
     */
	private void createMain(){
                kart = new CustomMapViewer(Main.getCurrentUser());
                this.add(kart, BorderLayout.CENTER);
		createMenuBar();
	}

    /**
     * Metode som lager menyen på toppen av siden og kaller andre metoder som
     * lager elementene i disse menyene
     */
    private void createMenuBar(){
		menuBar = new JMenuBar();
		menuBar.add(createFileMenu());
		menuBar.add(createFarmerMenu());
		menuBar.add(createSheepMenu());
		menuBar.add(createMapMenu());
		menuBar.add(createHelpMenu());

		this.setJMenuBar(menuBar);
	}

    /**
     * Metode som lager File-menyen og lager elementene i denne menyen
     *
     * @return JMenu-objekt som inneholder File-menyen
     */
	private JMenu createFileMenu(){
		JMenu file = new JMenu("File");

                JMenuItem refresh = new JMenuItem("Refresh");
                refresh.addActionListener(this);
                refresh.setActionCommand("refresh");

		JMenuItem logOut = new JMenuItem("Log out");
		logOut.addActionListener(this);
		logOut.setActionCommand("logOut");

		JMenuItem quit = new JMenuItem("Quit");
		quit.addActionListener(this);
		quit.setActionCommand("quit");
		file.add(refresh);
                file.add(new JSeparator());

		file.add(logOut);
		file.add(new JSeparator());
		file.add(quit);
		return file;

	}

    /**
     * Metode som lager Sheep-menyen og lager elementene i denne menyen
     *
     * @return JMenu-objekt som inneholder Sheep-menyen
     */
	private JMenu createSheepMenu(){
		JMenu sheepMenu = new JMenu("Sheep");

		JMenuItem addSheep = new JMenuItem("add sheep");
		addSheep.addActionListener(this);
		addSheep.setActionCommand("addSheep");

		JMenuItem removeSheep = new JMenuItem("remove sheep");
		removeSheep.addActionListener(this);
		removeSheep.setActionCommand("removeSheep");

		JMenuItem sheepList = new JMenuItem("sheep list");
		sheepList.addActionListener(this);
		sheepList.setActionCommand("sheepList");

		JMenuItem sheepLog = new JMenuItem("sheep log");
		sheepLog.addActionListener(this);
		sheepLog.setActionCommand("sheepLog");



		sheepMenu.add(addSheep);
		sheepMenu.add(removeSheep);
		sheepMenu.add(new JSeparator());
		sheepMenu.add(sheepList);
		sheepMenu.add(sheepLog);


		return sheepMenu;
	}

    /**
     * Metode som lager Farmer-menyen og lager elementene i denne menyen
     *
     * @return JMenu-objekt som inneholder Farmer-menyen
     */
	private JMenu createFarmerMenu(){
		JMenu farmerMenu = new JMenu("Farmer");

		JMenuItem userInformation = new JMenuItem("user information");
		userInformation.addActionListener(this);
		userInformation.setActionCommand("userInformation");

		JMenuItem helperInformation = new JMenuItem("helper information");
		helperInformation.addActionListener(this);
		helperInformation.setActionCommand("helperInformation");

		JMenuItem attackLog = new JMenuItem("attack log");
		attackLog.addActionListener(this);
		attackLog.setActionCommand("attackLog");

		farmerMenu.add(userInformation);
		farmerMenu.add(helperInformation);
		farmerMenu.add(new JSeparator());
		farmerMenu.add(attackLog);


		return farmerMenu;
	}

    /**
     * Metode som lager Map-menyen og lager elementene i denne menyen
     *
     * @return JMenu-objekt som inneholder Map-menyen
     */
	private JMenu createMapMenu(){
		JMenu mapMenu = new JMenu("Map");

		JMenuItem viewAllSheep = new JMenuItem("view all sheep");
		viewAllSheep.addActionListener(this);
		viewAllSheep.setActionCommand("allSheep");

		JMenuItem viewSingleSheep = new JMenuItem("view single sheep");
		viewSingleSheep.addActionListener(this);
		viewSingleSheep.setActionCommand("singleSheep");

		JMenuItem viewAttacks = new JMenuItem("view attack positions");
		viewAttacks.addActionListener(this);
		viewAttacks.setActionCommand("viewAttacks");

		mapMenu.add(viewAllSheep);
		mapMenu.add(viewSingleSheep);
		mapMenu.add(viewAttacks);


		return mapMenu;
	}

    /**
     * Metode som lager Help-menyen og lager elementene i denne menyen
     *
     * @return JMenu-objekt som inneholder Help-menyen
     */
	private JMenu createHelpMenu(){
		JMenu helpMenu = new JMenu("Help");

		JMenuItem about = new JMenuItem("about");
		about.addActionListener(this);
		about.setActionCommand("about");

		JMenuItem userManual = new JMenuItem("user manual");
		userManual.addActionListener(this);
		userManual.setActionCommand("userManual");

		helpMenu.add(about);
		helpMenu.add(new JSeparator());
		helpMenu.add(userManual);

		return helpMenu;
	}




    /**
     * Metode som tar imot en ActionEvent fra menyene og kartet til dette
     * vinduet, og utfører en handling basert på hva slags info ActionEventen
     * har
     *
     * @param e ActionEvent som inneholder informasjon om hva brukeren har
     * trykket
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if (command == "logOut"){
			this.dispose();
			Main.init();
		}
		else if (command == "quit"){
			System.exit(0);
		}
		else if (command == "addSheep"){
			new AddSheep(Main.getCurrentUser());
		}
		else if (command == "removeSheep"){
			new RemoveSheep(Main.getCurrentUser());
		}
		else if (command == "sheepList"){
			new SheepListFrame(Main.getCurrentUser());
		}
		else if (command == "sheepLog"){
			new SheepLogFrame(Main.getCurrentUser());
		}
		
		else if (command == "userInformation"){
			new UserInformation(Main.getCurrentUser());	
		}
		else if (command == "helperInformation"){
			new HelperInformation(Main.getCurrentUser());
		}
		else if (command == "attackLog"){
			new AttackLogFrame(Main.getCurrentUser());
		}
		else if (command == "allSheep"){
			MainPage.kart.showAllAttacks(false);
			MainPage.kart.showSheepPath(null);
                        MainPage.kart.showAllSheep(true);
                        MainPage.kart.refreshMap();
		}
		else if (command == "singleSheep"){
			MapSheepChooser msc = new MapSheepChooser(Main.getCurrentUser());
//                        Sheep s = msc.getCurrentSheep();
//                        Sheep swithcoords = ClientService.getSheepById(s.getId());
//                        MainPage.kart.showSheepPath(swithcoords);
//                        MainPage.kart.showAllAttacks(false);
//                        MainPage.kart.showAllSheep(true);
//                        MainPage.kart.refreshMap();
		}
		else if (command == "viewAttacks"){
			MainPage.kart.showAllAttacks(true);
			MainPage.kart.showSheepPath(null);
                        MainPage.kart.showAllSheep(false);
                        MainPage.kart.refreshMap();
		}
		else if (command == "about"){
			new AboutFrame();
		}
		else if (command == "userManual"){
                    try{
                        java.awt.Desktop.getDesktop().browse(userManual);
                    }
                    catch(Exception exception){
                        System.out.println(exception.getStackTrace());
                    }
			
		}
                else if (command == "refresh"){
                    System.out.println("refresh");
                    Farmer newFarmer = Main.getFarmerById(Main.getCurrentUser().getId());
                    Main.updateMainUser(newFarmer);
                    MainPage.kart.refreshMap();
                }
		
		
	}
	

}
