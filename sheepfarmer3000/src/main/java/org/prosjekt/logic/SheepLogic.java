package org.prosjekt.logic;

import java.util.ArrayList;

import javax.net.ssl.SSLContext;

import org.prosjekt.database.SheepService;
import org.prosjekt.database.repository.LogicService;
import org.prosjekt.database.repository.SheepRepository;
import org.prosjekt.helperclasses.Coordinate;
import org.prosjekt.helperclasses.Sheep;


/**
 * Klasse som inneholder logikken om hvordan sauene oppforer seg.
 * 
 * @author Kristoffer Dalby <kradalby@kradalby.no>
 *
 */
public class SheepLogic {
	
	
	/**
	 * Denne metoden flytter paa alle sauene.
	 */
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
