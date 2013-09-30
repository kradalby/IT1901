package org.prosjekt.logic;

import java.util.ArrayList;

import org.prosjekt.helperclasses.Attack;
import org.prosjekt.helperclasses.Sheep;

/**
 * @author kradalby
 *
 */
public class WolfLogic {

	// Returnerer en vilkaarlig sau utifra en arraylist.
	/**
	 * @param sheeps liste over alle sauer som kan angripes.
	 * @return returnerer en tilfeldig sau.
	 */
	public static Sheep getRandomSheep(ArrayList<Sheep> sheeps) {
		int n = sheeps.size();
		int sheepId= 0 + (int)(Math.random() * (((n-1) - 0) + 1));
		Sheep sheep = sheeps.get(sheepId);
		return sheep;
	}
	
	public static void wolfAttack(Sheep sheep) {
		int chance = (int)(Math.random() * (((100-1) - 0) + 1));
		
		// 70% sjanse for at sauen doer.
		if (chance > 30) {
			sheep.setAlive(false);
			// jeg antar at denne maa dyttes inn i databasen paa en eller annen maate.
			Attack attack = new Attack(sheep, sheep.getCurrentCordinates().getLatitude(), sheep.getCurrentCordinates().getLongitude());
			// Det maa ogsaa legges til et kall mot alert funksjonen naar den eksisterer.
		} 	
	}
}