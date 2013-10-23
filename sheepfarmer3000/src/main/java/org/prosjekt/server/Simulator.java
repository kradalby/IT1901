package org.prosjekt.server;

import java.util.ArrayList;
import java.util.LinkedList;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.prosjekt.database.SheepService;
import org.prosjekt.database.repository.SheepRepository;
import org.prosjekt.helperclasses.Farmer;
import org.prosjekt.logic.SheepLogic;
import org.prosjekt.logic.WolfLogic;

public class Simulator extends Thread {
	
	private boolean running;
	SheepService ss = new SheepRepository();
	private int timeBetweenMoves = 600000;
	private int timeBetweenAttacks = 172800000;
	
	public void run() {
		running = true;
		Move move = new Move();
		move.start();
		Kill killer = new Kill();
		killer.start();
	}
	
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

