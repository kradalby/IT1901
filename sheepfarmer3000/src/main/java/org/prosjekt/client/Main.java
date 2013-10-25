package org.prosjekt.client;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFrame;
import org.joda.time.DateTime;
import org.prosjekt.helperclasses.Coordinate;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Sheep;

public class Main {
	private static Gui gui;
        public static Farmer farmer;
	private static JFrame guiFrame;
//	protected static TestServer testServer;
	public static void main (String args[]){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				init();
			}
		});
	}
	public static void init(){
            farmer = new Farmer(1);
            ArrayList<Coordinate> coords = new ArrayList<Coordinate>();
            coords.add(new Coordinate(63.419446,10.402676, DateTime.now()));
            coords.add(new Coordinate(63.416278,10.409178, DateTime.now()));    
            coords.add(new Coordinate(63.4155,10.404865, DateTime.now()));    
            coords.add(new Coordinate(63.418438,10.400444, DateTime.now()));
            farmer.setCoordinates(coords);
            ArrayList<Sheep> sheep = new ArrayList<>();
            sheep.add(new Sheep(1, DateTime.now(), farmer));
            sheep.get(0).setMostCurrentCoordinate(coords.get(0));
            sheep.add(new Sheep(2, DateTime.now(), farmer));
            sheep.get(1).setMostCurrentCoordinate(coords.get(1));
            sheep.add(new Sheep(3, DateTime.now(), farmer));
            sheep.get(2).setMostCurrentCoordinate(coords.get(2));
            for (Sheep s : sheep)
                farmer.addSheep(s);
            gui = new Gui();
            guiFrame = gui.getGui();
//		testServer = new TestServer();	//Kontakt med serveren kan legges inn her, trenger kun � sende en liste med alle farmer-objekter for at det skal fungere 

	}
}
