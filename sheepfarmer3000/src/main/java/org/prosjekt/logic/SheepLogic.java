package org.prosjekt.logic;

import java.util.ArrayList;

import javax.net.ssl.SSLContext;

import org.prosjekt.database.SheepService;
import org.prosjekt.database.repository.LogicService;
import org.prosjekt.database.repository.SheepRepository;
import org.prosjekt.helperclasses.Coordinate;
import org.prosjekt.helperclasses.Sheep;


public class SheepLogic {
	

//	public static void moveSheeps(ArrayList<Sheep> sheeps) {
//		final Runnable saueFlytter = new Runnable() { //metoden som skal gjentas
//                public void run() { 
//                	for(int i=0; i<sheeps.size()-1; i++)
//                	{
//                		ArrayList<Coordinate> koordinater=sheeps[i].getCurrentCordinates();
//                		sheeps[i].cordinates.setLongitude(koordinater[0]+1); //Jeg vet ikke hva slags form koordinatene er på, øker med 1 hittil
//                		sheeps[i].cordinates.setLatitude(koordinater[1]+1); //Jeg vet ikke hva slags form koordinatene er på, øker med 1 hittil
//                		int randomIndex = generator.nextInt( 100 );
//                		if (randomIndex<=2){
//                			//Sauen dør med 2% sannsynlighet
//                			sheeps[i].setAlive(False); //sende en alarm av noe slag?
//                		}
//                	}
//                }
//            };
//            
//        final ScheduledFuture<?> saueFlytterHaandterer =	 scheduler.scheduleAtFixedRate(saueFlytter, 0, 60*5, SECONDS); 
//        //metoden som gjentar "saueFlytter" hvert 5. minutt
//        scheduler.schedule(new Runnable() {
//                public void run() { saueFlytterHandle.cancel(true); }
//            }, 60 * 60, SECONDS); // som test har jeg den kjørende i én time (60*60sek)
//	}
	
	public static void moveSheeps() {
		SheepService ss = new SheepRepository();
		LogicService ls = new LogicRepository();
		
		Sheep[] sheeps = (Sheep[]) ss.getAllSheeps();
		for (int i = 0; i < sheeps.length; i++) {
			double currentLong = sheeps[i].getCurrentCordinate().getLongitude();
			double currentLat = sheeps[i].getCurrentCordinate().getLatitude();
			double newLong = currentLong + Math.random()/1000;
			double newLat = currentLat + Math.random()/1000;
			Coordinate newCoord = new Coordinate(newLong, newLat);
			
			//Dette er to metoder je gikke er sikker paa hva som er forskjellen paa
			//Anyone?
			sheeps[i].addCoordinate(newCoord);
			ls.addSheepMovement(sheeps[i], newCoord);
		}
	}
}
