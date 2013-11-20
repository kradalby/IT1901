package org.prosjekt.logic;

import com.google.common.base.Stopwatch;
import java.util.List;


import org.prosjekt.database.LogicService;
import org.prosjekt.database.repository.LogicRepository;
import org.prosjekt.helperclasses.Coordinate;
import org.prosjekt.helperclasses.Farmer;
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
	public static void moveSheeps(RandomSheepGenerator rsg, List<Sheep> sheeps, LogicService ls) {
           

            Stopwatch s1 = new Stopwatch();
            s1.start();
            
            for (Sheep s : sheeps){
                Coordinate newcoord = rsg.generateCoords(1).get(0);
                s.setCurrentCoordinate(newcoord);
            }
            ls.addSheepMovements(sheeps);
            s1.stop();
            System.out.println("\nTime to move sheeps: " + s1.elapsedMillis());
            
	}
        
        /*
         *  Flytter en sau. 
         */
        public static void moveSheep(RandomSheepGenerator rsg, Sheep s, LogicService ls){
            Coordinate newcoord = rsg.generateCoords(1).get(0);
            ls.addSheepMovement(s, newcoord);
        }

   
}
