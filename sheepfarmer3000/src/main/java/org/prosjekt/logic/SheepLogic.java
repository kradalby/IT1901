package org.prosjekt.logic;

import java.util.ArrayList;

import javax.net.ssl.SSLContext;
import org.joda.time.DateTime;

import org.prosjekt.database.LogicService;
import org.prosjekt.database.repository.LogicRepository;
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
		LogicService ls = new LogicRepository();
		
		Sheep[] sheeps = ls.getAllSheeps();
		for (int i = 0; i < sheeps.length; i++) {
			double currentLong = sheeps[i].getCurrentCordinate().getLon();
			double currentLat = sheeps[i].getCurrentCordinate().getLat();
			double newLong = currentLong + Math.random()/1000;
			double newLat = currentLat + Math.random()/1000;
			Coordinate newCoord = new Coordinate(newLong, newLat, new DateTime());
			
			ls.addSheepMovement(sheeps[i], newCoord);
		}
	}

   
}
