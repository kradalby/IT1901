package org.prosjekt.server;

import java.util.ArrayList;
import java.util.LinkedList;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.prosjekt.helperclasses.Farmer;

public class Simulator extends Thread{
	
	private int numberOfSheeps;
	private LinkedList<Coordinate> area = new LinkedList<Coordinate>();
	private ArrayList<Farmer> farmers = new ArrayList<Farmer>();
	
	
	public Simulator(int numberOfSheeps, LinkedList<Coordinate> area, 
			ArrayList<Farmer> farmers){
		this.numberOfSheeps = numberOfSheeps;
		this.area = area;
		this.farmers = farmers;
	}
	
	public void generateTestData() {
		
	}
}