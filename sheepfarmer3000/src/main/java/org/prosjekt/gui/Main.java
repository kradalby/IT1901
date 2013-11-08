package org.prosjekt.gui;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import org.joda.time.DateTime;
import org.prosjekt.client.ClientExample;

import org.prosjekt.helperclasses.Coordinate;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Passhash;
import org.prosjekt.helperclasses.Sheep;
public class Main {
	private static ArrayList<Farmer> farmerList;
	private static Farmer mainCurrentUser;

	public static void main (String args[]){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				init();
			}
		});
                
	}
	public static void init(){
		farmerList = new ArrayList<Farmer>();
		mainCurrentUser = null;
		sheepListSimulator();
		//new MainPage();
		//mainPageSimulator();
		//loggInSimulator();
		new TopLevelGui();	//Dette er den endelige metoden som skal være her
		//new SheepLogFrame(mainCurrentUser);
		//new AttackLogFrame(mainCurrentUser);
	}
	
	//Denne byttes ut med riktig i forhold til henting far server
	public static List<Integer> getFarmerIds(){
		List<Integer> ids = ClientExample.getFarmerIds();
		return ids;
	}
	
	
	private static void sheepListSimulator(){
		Farmer farmer1 = new Farmer(1000, "Ola", "Nordmann", "ola@nordmann.no", "81549300");
		Passhash passord1 = new Passhash(1000);
		passord1.setPasshash("admin1");
		farmer1.setPasshash(passord1);
		List<Sheep> sheepList = new ArrayList<Sheep>();
		Sheep sheep1 = new Sheep("1000", new DateTime(), farmer1.getId(), new Coordinate(1155.111, 156153.5));
		Sheep sheep2 = new Sheep("2000", new DateTime(), farmer1.getId(), new Coordinate(2332.123111, 156153.5));
		
		Coordinate coordinate1 = new Coordinate(100.156, 3551.55);
		Coordinate coordinate2 = new Coordinate(4760.156, 3551.55);
		Coordinate coordinate3 = new Coordinate(100.156, 387851.55);
		Coordinate coordinate4 = new Coordinate(1780.156, 3551.55);
		
		Coordinate coordinate5 = new Coordinate(1467.156, 38851.55);
		Coordinate coordinate6 = new Coordinate(100.156, 768514.55);
		Coordinate coordinate7 = new Coordinate(14768.156, 3551.55);
		Coordinate coordinate8 = new Coordinate(100.156, 8551.55);
		
		Coordinate coordinate9 = new Coordinate(1467.156, 38851.55);
		Coordinate coordinate10 = new Coordinate(10123.156, 768514.55);
		Coordinate coordinate11 = new Coordinate(14768.156, 3551.55);
		Coordinate coordinate12 = new Coordinate(100.156, 8551.55);
		
		List<Coordinate> coordinates1 = new ArrayList<Coordinate>();
		coordinates1.add(coordinate1);
		coordinates1.add(coordinate2);
		coordinates1.add(coordinate3);
		coordinates1.add(coordinate4);
		
		List<Coordinate> coordinates2 = new ArrayList<Coordinate>();
		coordinates2.add(coordinate5);
		coordinates2.add(coordinate6);
		coordinates2.add(coordinate7);
		coordinates2.add(coordinate8);
		
		List<Coordinate> coordinates3 = new ArrayList<Coordinate>();
		coordinates3.add(coordinate9);
		coordinates3.add(coordinate10);
		coordinates3.add(coordinate11);
		coordinates3.add(coordinate12);
		
		
		
		sheep1.setCordinates(coordinates1);
		sheep2.setCordinates(coordinates2);
		sheep1.setAttacks(coordinates3);
		sheep2.setAttacks(coordinates1);
		
		sheepList.add(sheep1);
		sheepList.add(sheep2);
		
		/*
		sheepList.add(sheep3);
		sheepList.add(sheep4);
		sheepList.add(sheep5);
		sheepList.add(sheep6);
		sheepList.add(sheep7);
		sheepList.add(sheep8);
		sheepList.add(sheep9);
		sheepList.add(sheep10);
		sheepList.add(sheep11);
		sheepList.add(sheep12);
		sheepList.add(sheep13);
		sheepList.add(sheep14);
		sheepList.add(sheep15);
		sheepList.add(sheep16);
		sheepList.add(sheep17);
		sheepList.add(sheep18);
		sheepList.add(sheep19);
		sheepList.add(sheep20);
		
		
		*/
		
		
		farmer1.setSheeps(sheepList);
		
		
		
		farmerList.add(farmer1);	//denne brukes ved main test
		
		//mainCurrentUser = farmer1;
		//new SheepListFrame(mainCurrentUser);
		//new AddSheep(currentUser);
		//new RemoveSheep(currentUser);
	}
	
	
	public static Farmer getCurrentUser(){
		return mainCurrentUser;
	}
	public static void updateMainUser(Farmer f){
		//Her burde det også komme et kall til map som oppdaterer
		mainCurrentUser = f;
	}
	public static boolean checkFarmerList(int id){
		boolean tester = false;
                List<Integer> farmerids = ClientExample.getFarmerIds();
		for (Integer i: farmerids){
			if(i==id){
                            tester = true;
                            break;
			}
		}
		return tester;
	}
	
	public static Farmer getFarmerById(int id){
		return ClientExample.getFarmer(id);
	}
        
        
	public static boolean checkPassword(Farmer f, char[] input){
		boolean tester = false;
		Passhash p = f.getPasshash();
		char[] correctPassword = p.toCharArray();
		if (input.length != correctPassword.length) {
			tester = false;
		}
		else {
			tester = Arrays.equals(input, correctPassword);
			if(tester){
				mainCurrentUser = f;
			}
		}
		return tester;
	}
	
	public static boolean saveChangesToFarmer(Farmer f){
		boolean confirmedChange = true;
		
		//confirmedChange må settes til riktig bool når svar fra server er kommet
		//Her må det legges kode som endrer farmer på server
		//foreløpig erstatter bare koden et farmerobjekt med et annet
		mainCurrentUser = f;
		
		return confirmedChange;
	}
	

	

}
