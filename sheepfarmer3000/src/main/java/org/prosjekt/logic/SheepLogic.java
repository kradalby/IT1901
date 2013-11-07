package org.prosjekt.logic;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import org.joda.time.DateTime;

import org.prosjekt.database.LogicService;
import org.prosjekt.database.repository.LogicRepository;
import org.prosjekt.database.repository.SheepRepository;
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
	public static void moveSheeps() {
            
            LogicService ls = new LogicRepository();
            List<Farmer> farmers = ls.getAllFarmers();

            for (Farmer f : farmers){
                if (f.getCoordinates().size() == 0) continue;
                System.out.println(f + "\n" + f.getCoordinates());
                RandomSheepGenerator rsg = new RandomSheepGenerator(f.getCoordinates(), f);
//                System.out.println(f.getCoordinates());
                for (Sheep s : f.getSheeps()){
                    moveSheep(rsg, s, ls);
                }
            }
            
	}
        
        //N
        
        /*
         *  Flytter en sau. 
         */
        public static void moveSheep(RandomSheepGenerator rsg, Sheep s, LogicService ls){
            Coordinate newcoord = rsg.generateCoords(1).get(0);
            ls.addSheepMovement(s, newcoord);
        }

   
}
