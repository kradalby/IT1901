package org.prosjekt.server;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.prosjekt.database.LogicService;
import org.prosjekt.database.SheepService;
import org.prosjekt.database.repository.LogicRepository;
import org.prosjekt.database.repository.SheepRepository;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.logic.RandomSheepGenerator;
import org.prosjekt.logic.SheepLogic;
import org.prosjekt.logic.WolfLogic;

/**
 * Klasse som kjorer opp prosessene som flytter paa sauer og dreper dem.
 * 
 * @author Kristoffer Dalby <kradalby@kradalby.no>
 *
 */
public class Simulator extends Thread {
	
	private boolean running;
	LogicService ls = new LogicRepository();
	private int timeBetweenMoves = 28800000; // 8 timer
	private int timeBetweenAttacks = 28800000; // 8 timer
//	private int timeBetweenMoves = 60000; //1 min
//	private int timeBetweenAttacks = 120000; // 2 min
	
	public void run() {
		running = true;
		Move move = new Move();
		move.start();
		Kill killer = new Kill();
		killer.start();
	}
	
	/**
	 * Klasse som kjorer opp en traad for aa flytte sauer paa et intervall.
	 * 
	 * @author Kristoffer Dalby <kradalby@kradalby.no>
	 * 
	 *
	 */
	public class Move extends Thread {
		LogicService ls = new LogicRepository();
                
                
		public void run() {
			while (running) {
				try {
                                        List<Farmer> farmers = ls.getAllFarmers();
                                        for (Farmer f : farmers){
                                            RandomSheepGenerator rsg = new RandomSheepGenerator(f.getCoordinates(), f);
                                            if (f.getCoordinates().size() == 0) continue;
                                            SheepLogic.moveSheeps(rsg, f.getSheeps(), ls);
                                            
                                        }

					Thread.sleep(timeBetweenMoves);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Klasse som kjorer opp en traad for aa ta liv av sauer paa et intervall.
	 * 
	 * @author Kristoffer Dalby <kradalby@kradalby.no>
	 *
	 */
	public class Kill extends Thread {
		
		public void run() {
			while (running) {
				try {
					WolfLogic.wolfAttack(WolfLogic.getRandomSheep(ls.getAllSheeps()));
					Thread.sleep(timeBetweenAttacks);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
}

