package org.prosjekt.logic;

import java.util.ArrayList;

import org.prosjekt.helperclasses.Sheep;

public class WolfLogic {

	// Returnerer en vilkaarlig sau utifra en arraylist.
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
			Attack attack = new Attack(sheep, sheep.getCurrentCordinates().getLatitude(), sheep.getCurrentCordinates().getLongitude());
		} 
	}
}
