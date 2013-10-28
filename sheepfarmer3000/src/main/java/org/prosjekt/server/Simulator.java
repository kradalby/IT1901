package org.prosjekt.server;

import java.util.ArrayList;
import java.util.LinkedList;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.prosjekt.database.SheepService;
import org.prosjekt.database.repository.SheepRepository;
import org.prosjekt.helperclasses.Farmer;
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
	SheepService ss = new SheepRepository();
	private int timeBetweenMoves = 600000; //10 min
	private int timeBetweenAttacks = 172800000; // 48 timer
	
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
		
		public void run() {
			while (running) {
				try {
					SheepLogic.moveSheeps();
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
					WolfLogic.wolfAttack(WolfLogic.getRandomSheep(ss.getAllSheeps()));
					Thread.sleep(timeBetweenAttacks);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
}

