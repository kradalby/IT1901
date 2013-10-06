package org.prosjekt.logic;

import java.util.ArrayList;

import org.prosjekt.helperclasses.Sheep;

public class SheepLogic {
	
	public static void moveSheeps(ArrayList<Sheep> sheeps) {
		final Runnable saueFlytter = new Runnable() { //metoden som skal gjentas
                public void run() { 
                	for(int i=0; i<sheeps.size()-1; i++)
                	{
                		ArrayList<Coordinate> koordinater=sheeps[i].getCurrentCordinates();
                		sheeps[i].cordinates.setLongitude(koordinater[0]+1): //Jeg vet ikke hva slags form koordinatene er på, øker med 1 hittil
                		sheeps[i].cordinates.setLatitude(koordinater[1]+1): //Jeg vet ikke hva slags form koordinatene er på, øker med 1 hittil
                		int randomIndex = generator.nextInt( 100 );
                		if (randomIndex<=2){
                			sheeps[i].alive=False;
                		}
                	}
                }
            };
            
        final ScheduledFuture<?> saueFlytterHaandterer =	 scheduler.scheduleAtFixedRate(saueFlytter, 0, 60*5, SECONDS); 
        //metoden som gjentar "saueFlytter" hvert 5. minutt
        scheduler.schedule(new Runnable() {
                public void run() { saueFlytterHandle.cancel(true); }
            }, 60 * 60, SECONDS); // som test har jeg den kjørende i én time (60*60sek)
	}
}
