package org.prosjekt.gui;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import org.joda.time.DateTime;
import org.prosjekt.client.ClientService;

import org.prosjekt.helperclasses.Coordinate;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.helperclasses.Passhash;
import org.prosjekt.helperclasses.Sheep;
public class Main {
	private static ArrayList<Farmer> farmerList;
	private static Farmer mainCurrentUser;

	public static void main (String args[]) throws URISyntaxException{
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				init();
			}
		});
                
	}
	public static void init(){
		farmerList = new ArrayList<Farmer>();
		mainCurrentUser = null;
		new TopLevelGui();	//Dette er den endelige metoden som skal være her
		//new SheepLogFrame(mainCurrentUser);
		//new AttackLogFrame(mainCurrentUser);
	}
	
	//Denne byttes ut med riktig i forhold til henting far server
	public static List<Integer> getFarmerIds(){
		List<Integer> ids = ClientService.getFarmerIds();
		return ids;
	}
	
	
	public static Farmer getCurrentUser(){
		return mainCurrentUser;
	}
	public static void updateMainUser(Farmer f){
		//Her burde det også komme et kall til map som oppdaterer
		mainCurrentUser = f;
                MainPage.kart.refreshMap();
	}
	public static boolean checkFarmerList(int id){
		boolean tester = false;
                List<Integer> farmerids = ClientService.getFarmerIds();
		for (Integer i: farmerids){
			if(i==id){
                            tester = true;
                            break;
			}
		}
		return tester;
	}
	
	public static Farmer getFarmerById(int id){
		return ClientService.getFarmer(id);
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
                boolean confirmChange = ClientService.updateFarmer(f);
                return confirmChange;
	}
	

	

}
