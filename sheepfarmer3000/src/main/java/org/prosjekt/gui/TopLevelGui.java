package org.prosjekt.gui;


import java.util.ArrayList;
import java.util.Arrays;

public class TopLevelGui {
	private static MainPage mainPage;
	
	//skal ta seg av instansiering av gui, 
	
	//test passord skal være her, kalles fra loginbox, metoden returnerer en bool, om false skal metoden i loginbox sende en feilmelding

	public TopLevelGui(){
		//new LoginBox();	//OOOOBBBBSSSS!!!!! DENNE SKAL IKKE FJERNES, FOREL�PIG FJERNET FOR Å TESTE ENKLERE
		new LoginBox();
		
		
	}
	
	
	protected static boolean checkPassword(int userid, char[] password){
		boolean loginOK=false;
		if (Main.checkFarmerList(userid)){
			if(Main.checkPassword(Main.getFarmerById(userid), password)){
				loginOK = true;
				mainPage = new MainPage();
			}
		}
		return loginOK;

	}
	
	
	
	

	
	
}
